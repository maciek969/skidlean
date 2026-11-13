/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.SettingEntry$$serializer;
import su.catlean.config.SettingEntry$Companion;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class SettingEntry {
    @NotNull
    public static final SettingEntry$Companion Companion;
    @NotNull
    private final String id;
    @NotNull
    private final String value;
    private static int k;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long g;

    public SettingEntry(@NotNull String string, @NotNull String string2) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(string2, "value");
        this.id = string;
        this.value = string2;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final String getValue2() {
        return this.value;
    }

    @NotNull
    public final SettingEntry copy(@NotNull String string, @NotNull String string2) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)SettingEntry.zkmBootstrap("i", (int)17180, (long)(0x4A2DA16C6AEF13A7L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)SettingEntry.zkmBootstrap("i", (int)23500, (long)(0x15A10774D3FA8B70L ^ l))));
        return new SettingEntry(string, string2);
    }

    public static SettingEntry copy$default(SettingEntry settingEntry, String string, String string2, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3680591182648473052L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                string = settingEntry.id;
            }
            n2 = n & 2;
        }
        if (n2 != 0) {
            string2 = settingEntry.value;
        }
        return settingEntry.copy(string, string2);
    }

    @NotNull
    public String toString() {
        return "SettingValue(id=" + this.id + ", value=" + this.value + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * (int)g + this.value.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SettingEntry)) {
            return false;
        }
        SettingEntry settingEntry = (SettingEntry)object;
        if (!Intrinsics.areEqual(this.id, settingEntry.id)) {
            return false;
        }
        return Intrinsics.areEqual(this.value, settingEntry.value);
    }

    @JvmStatic
    public static final void write$Self(SettingEntry settingEntry, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, settingEntry.id);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, settingEntry.value);
    }

    public SettingEntry(int n, String string, String string2, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 3, SettingEntry$$serializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.value = string2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    SettingEntry.a = ZkmKeys.a(2521332337147926580L, 4148699431164448361L, MethodHandles.lookup().lookupClass()).a(38866830842349L);
                    var14 = SettingEntry.a ^ 39442853854328L;
                    SettingEntry.e = new HashMap<K, V>(13);
                    SettingEntry.zkmSetFlag(0);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[6];
                    var10_4 = 0;
                    var9_5 = "\u00e78\u00e8\u00ef\u00b7\r\u0012\u0082;\u00b4\u00ed\u0085\u001e*\u00b1\u00acc\\\u00c8\u00f7;Ux\u00e1\u009b\u00c3\u00db\u00b6\n\u0010!yyI\u00eaT\b\u008f\u00fa\u00e3\u0010P\u0099\u001fO\u008d8w\u00d0\"A?\u0088\u00ff\u0006\u00e7\u00e4 \n\u0081a\u007f\u00e9\u00e8\u0097\u00d6\u0004\u00e67\u0089\u00d3\u00cd\u001d[9Su\u009f\u00e5\u00f0?8\u008d\u00ae7\u0087\u00cb\u00cen\u00db\u0010v\u00da\u0003L\u00b3}\u00df\u00d6\u00ffM\u0081z 6\u00d0\u0094";
                    var11_6 = 107;
                    var8_7 = 40;
                    var7_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = SettingEntry.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00de6\u00c7\u001b\u00b8[\u00beu\u001d\u008c\u00de\u00fb\u009a\u00b9u\u00e2\u0010\"\"\u0099dP\u00c5\u00a9\u00ae\u00e4\b\"<\u00a9\u008f\u00f4\u00e6";
                        var11_6 = 33;
                        var8_7 = 16;
                        var7_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = SettingEntry.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            SettingEntry.b = var12_3;
            SettingEntry.c = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-88, -37, -42, 121, -94, -67, -22, 60});
        ** while (true)
        SettingEntry.g = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        SettingEntry.Companion = new SettingEntry$Companion(null);
    }

    public static void zkmSetFlag(int n) {
        k = n;
    }

    public static int zkmGetFlag() {
        return k;
    }

    public static int zkmIntConstant() {
        return 56;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6DE3;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/sh", exception);
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
            SettingEntry.c[n2] = SettingEntry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingEntry.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/sh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingEntry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

