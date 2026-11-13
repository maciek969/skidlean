/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.setting.SettingOverride;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SettingOverrideRegistry {
    @NotNull
    public static final SettingOverrideRegistry INSTANCE;
    @NotNull
    private static final List M;
    private static Module[] g;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private SettingOverrideRegistry() {
    }

    @NotNull
    public final List v() {
        return M;
    }

    @NotNull
    public final String register(int n, @NotNull String string, @NotNull Number number, @NotNull String string2, char c, short s) {
        Object object;
        block7: {
            Module[] moduleArray;
            long l;
            block6: {
                block5: {
                    Object v12;
                    block4: {
                        l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)s << 48 >>> 48) ^ a;
                        Intrinsics.checkNotNullParameter(string, "id");
                        Module[] moduleArray2 = SettingOverrideRegistry.zkmGetFlag();
                        Intrinsics.checkNotNullParameter(number, "minValue");
                        moduleArray = moduleArray2;
                        Intrinsics.checkNotNullParameter(string2, "orElse");
                        Iterable iterable = M;
                        for (Object v12 : iterable) {
                            Object var13_11;
                            SettingOverride settingOverride;
                            while (Intrinsics.areEqual((settingOverride = (SettingOverride)(var13_11 = v12)).getName(), string) && Intrinsics.areEqual(number, settingOverride.getValue())) {
                                v12 = var13_11;
                                if (s < 0) continue;
                                break block4;
                            }
                        }
                        v12 = null;
                    }
                    if ((object = (SettingOverride)v12) == null) break block5;
                    object = ((SettingOverride)object).getOverrideName();
                    if (c < '\u0000') break block6;
                    object = object;
                    if (object != null) break block6;
                }
                object = string2;
            }
            if (n >= 0) break block7;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)moduleArray, (long)239134545422518636L, (long)l);
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(3301654830408783113L, -36196659397426358L, MethodHandles.lookup().lookupClass()).a(19701932531467L);
        long l = a ^ 0x11807A5145F0L;
        d = new HashMap(13);
        SettingOverrideRegistry.zkmSetFlag(null);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[3];
        int n = 0;
        String string = "c\u0080]\u00b1D\u00f4f\u000f\u0010\u00e6\u00c7x\u009f\u00fb\u008d\u001d\u0010\u00ba\u0010u\u00d2%\u00b0naeI\u0092\u00ce\u001b\u00fa<+ u\u00c4\u00a5\u00dd=Qr\b\u009c\u00a5:\u0012*\u0098\u009c`\u009d\u0099\u00a7D\u00e4b-)\u0006x\u00ca\u00e0\u00f2\u00eb1\u0017";
        int n2 = 66;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SettingOverrideRegistry.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                INSTANCE = new SettingOverrideRegistry();
                M = new ArrayList();
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        g = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return g;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static String zkmDecodeUtf8(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static String zkmDecryptString(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4F20;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/oo", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = b[n2].getBytes("ISO-8859-1");
            SettingOverrideRegistry.c[n2] = SettingOverrideRegistry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingOverrideRegistry.zkmDecryptString(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/oo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingOverrideRegistry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

