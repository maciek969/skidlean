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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SettingOverride {
    @NotNull
    private final String name;
    @NotNull
    private final Number value;
    @NotNull
    private final String overrideName;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public SettingOverride(@NotNull String string, @NotNull Number number, @NotNull String string2) {
        Intrinsics.checkNotNullParameter(string, "name");
        Intrinsics.checkNotNullParameter(number, "value");
        Intrinsics.checkNotNullParameter(string2, "overrideName");
        this.name = string;
        this.value = number;
        this.overrideName = string2;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Number getValue() {
        return this.value;
    }

    @NotNull
    public final String getOverrideName() {
        return this.overrideName;
    }

    @NotNull
    public final String getName2() {
        return this.name;
    }

    @NotNull
    public final Number getValue2() {
        return this.value;
    }

    @NotNull
    public final String getOverrideName2() {
        return this.overrideName;
    }

    @NotNull
    public final SettingOverride copy(@NotNull String string, @NotNull Number number, @NotNull String string2) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)SettingOverride.zkmBootstrap("w", (int)16224, (long)(0x488171D33DA78520L ^ l))));
        Intrinsics.checkNotNullParameter(number, (String)((Object)SettingOverride.zkmBootstrap("w", (int)6004, (long)(0x1D526EB6E248AD3DL ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)SettingOverride.zkmBootstrap("w", (int)20510, (long)(0x6DC65E210294EA5AL ^ l))));
        return new SettingOverride(string, number, string2);
    }

    public static SettingOverride copy$default(SettingOverride settingOverride, String string, Number number, String string2, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2983619049443554289L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                string = settingOverride.name;
            }
            n2 = n & 2;
        }
        if (callSite == null) {
            if (n2 != 0) {
                number = settingOverride.value;
            }
            n2 = n & 4;
        }
        if (n2 != 0) {
            string2 = settingOverride.overrideName;
        }
        return settingOverride.copy(string, number, string2);
    }

    @NotNull
    public String toString() {
        String string = this.overrideName;
        Number number = this.value;
        String string2 = this.name;
        return "SettingNameOverride(name=" + string2 + ", value=" + number + ", overrideName=" + string + ")";
    }

    public int hashCode() {
        int n = this.name.hashCode();
        n = n * 31 + this.value.hashCode();
        n = n * 31 + this.overrideName.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SettingOverride)) {
            return false;
        }
        SettingOverride settingOverride = (SettingOverride)object;
        if (!Intrinsics.areEqual(this.name, settingOverride.name)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.value, settingOverride.value)) {
            return false;
        }
        return Intrinsics.areEqual(this.overrideName, settingOverride.overrideName);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        SettingOverride.a = ZkmKeys.a(1394539461176179732L, 7348031326951668882L, MethodHandles.lookup().lookupClass()).a(114291661978187L);
                        SettingOverride.d = new HashMap<K, V>(13);
                        var11 = SettingOverride.a ^ 74438599845711L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[9];
                        var18_4 = 0;
                        var17_5 = "\u00c1\u0082\u00c5\u00d1\u0097\u00e6\u0017\u00ef\u00b5'5\u00bf\u00c6w\u00fe\u0018\u00a0\u00bdg\u00a8/\u00a7\u00c6\u0094\u00dfC5\u0000\u00bc\u00d1\u00d8\u001e\u0010\u00c0&-r\u008e\u00fc\u00af\u00954/L\u00c8\u009c\u0013Fa\u0010\u009e\u00f8\u0084\u00e5\u0082\u00fb\u00eb\u00fe\u00dez\rc\u00bc5\u007f)\u0018\u00ca_]Z\u0003\u00a1\u008e\u00bd\u0001\u009f\u00dd\u00eb\u00cf\u00c6\u00d8E\u00b1\u00c2k%f\u00fa\u001bP S\u0097\u00c5&\u00cf\u00ea\u00c6\u00d54\u00dfO\u00dd\u001bA\u00ed:\u00a0\u00eeP\u00f7\u00e9p\u00d3\u00ce\u00d3\u00e7\u009d\u00ec\u00ae\u00d8\u0088Z S\u00db\u00bd2\u000e4\u00d9\u00d9\u00f8o)\u0096)\u00a9+\u001e\u00c6ZK\u00e0\u00e8\u0080\u00c5\u00b2\u00c9j,\u0018\u00f4W\u00ee\u00c5@\u000f!\u0018X\u00ee\u00a0\u00eb\u00d1\u00d5)\u008e\u00a2\u0084\u001ffKT\u00d7\u008a\u00b3\u0011Ge\u00d2\u00aek\u00f8\u001f\u0092\u0082I*z3\u00eb\rg\u0097*\u00a2\u00ca\u0000FP\u008e\u00a5\u00cdm\u00c3\u0017\u001d\u0087t\u00e9\u00c8\u0097\u00ea\u008azT\u00e7[\n@";
                        var19_6 = 222;
                        var16_7 = 32;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = SettingOverride.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u001c:m\u0080\u00b8\u0086m\u00fc\u00dew\u00a2\u00fc\u00fc\b\u0004N\u0010Sm*\u00a5[\u00f1\u00b9A\u0083\u00d9B}\u0097\u00c6\u00c3e";
                            var19_6 = 33;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = SettingOverride.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                SettingOverride.b = var20_3;
                SettingOverride.c = new String[9];
                SettingOverride.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "H\u0016R\u00f4\u0099\u00f5\u0080v\u00aa\u00da\u00ea\u0098\u008f\u00b7\u00d5\u0016";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        SettingOverride.e = var6_12;
        SettingOverride.f = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1112;
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
                throw new RuntimeException("su/catlean/s6", exception);
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
            SettingOverride.c[n2] = SettingOverride.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingOverride.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/s6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A3E;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/s6", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SettingOverride.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SettingOverride.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/s6" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingOverride.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(SettingOverride.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

