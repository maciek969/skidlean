/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.MoveFixMode;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.zkm.ZkmKeys;

public final class MoveFix
extends Module {
    @NotNull
    public static final MoveFix INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    private static Module[] P;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    private MoveFix() {
        super("move-fix", Categories.getBasic(), null, null);
    }

    @NotNull
    public final MoveFixMode getMode() {
        return (MoveFixMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    MoveFix.b = ZkmKeys.a(-2185901550709602802L, -1557163849045337001L, MethodHandles.lookup().lookupClass()).a(264240335578388L);
                    var16 = MoveFix.b ^ 38743268804392L;
                    MoveFix.e = new HashMap<K, V>(13);
                    MoveFix.zkmSetFlag(new Module[2]);
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var16 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var16 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[4];
                    var12_4 = 0;
                    var11_5 = "OB\u00a8\u00e9\u001d\u0018d\u00a3\u00ea\roj\u00ad\u0087F\u0080\u00e1\u0093\\.\u00ef\u00e1AM\r\u00990d\u001dp+\u00e9\u0010\u00a5\u00fb\u0016\u0093g\u000e\u00d8b\u00d5-\u0098\u0093\u000e'\u00d0,";
                    var13_6 = 49;
                    var10_7 = 32;
                    var9_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = MoveFix.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "(\u00b5A\u00eff\u00a6\u00f5\u0013\u00f5\u00c6B\u000f\u00fe*\u00daX{\u0014\u000e\u0095\u00b0\u00a7\u00e20\u001f\u0002o\u00c8^I\u00ab\u008cVU\f\u0018!^\u00bcf\u0092\u0007\u00ef\u0081\u00dd\u000e\u001b;j\u00ce=.]Zh\u0080\u0010\u0015=BV_5\u0007\u00f3O\r\u00a9\u0082\u0090B\u0097_";
                        var13_6 = 73;
                        var10_7 = 56;
                        var9_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = MoveFix.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
                switch (v5) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl52:
                    // 1 sources

                    ** continue;
                }
            }
            MoveFix.c = var14_3;
            MoveFix.d = new String[4];
            var2_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var16 >>> 56);
            for (var3_11 = 1; var3_11 < 8; ++var3_11) {
                v9 = v9;
                v9[var3_11] = (byte)(var16 << var3_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var6_13 = var2_10.doFinal(new byte[]{58, -96, 117, -87, 27, 21, 123, 78});
        ** while (true)
        var0_12 = ((long)var6_13[0] & 255L) << 56 | ((long)var6_13[1] & 255L) << 48 | ((long)var6_13[2] & 255L) << 40 | ((long)var6_13[3] & 255L) << 32 | ((long)var6_13[4] & 255L) << 24 | ((long)var6_13[5] & 255L) << 16 | ((long)var6_13[6] & 255L) << 8 | (long)var6_13[7] & 255L;
        var23_14 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(MoveFix.class, "mode", "getMode()Lsu/catlean/lu;", 0))};
        MoveFix.delegatedProperties = var23_14;
        MoveFix.INSTANCE = new MoveFix();
        MoveFix.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)MoveFix.INSTANCE, "mode", (Enum)MoveFixMode.FREE, null, null, (int)var0_12, null);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        P = moduleArray;
    }

    public static Module[] su_catlean_module_Module_arr_zkmGetFlag() {
        return P;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x74F3;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_a", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            MoveFix.d[n2] = MoveFix.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MoveFix.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_a" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MoveFix.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

