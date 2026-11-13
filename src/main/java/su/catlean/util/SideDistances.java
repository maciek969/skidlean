/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SideDistances {
    private final double westDelta;
    private final double eastDelta;
    private final double northDelta;
    private final double southDelta;
    private final double upDelta;
    private final double downDelta;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public SideDistances(double d, double d2, double d3, double d4, double d5, double d6) {
        this.westDelta = d;
        this.eastDelta = d2;
        this.northDelta = d3;
        this.southDelta = d4;
        this.upDelta = d5;
        this.downDelta = d6;
    }

    public final double getWestDelta() {
        return this.westDelta;
    }

    public final double getEastDelta() {
        return this.eastDelta;
    }

    public final double getNorthDelta() {
        return this.northDelta;
    }

    public final double getSouthDelta() {
        return this.southDelta;
    }

    public final double getUpDelta() {
        return this.upDelta;
    }

    public final double getDownDelta() {
        return this.downDelta;
    }

    public final double getWestDelta2() {
        return this.westDelta;
    }

    public final double getEastDelta2() {
        return this.eastDelta;
    }

    public final double getNorthDelta2() {
        return this.northDelta;
    }

    public final double getSouthDelta2() {
        return this.southDelta;
    }

    public final double getUpDelta2() {
        return this.upDelta;
    }

    public final double getDownDelta2() {
        return this.downDelta;
    }

    @NotNull
    public final SideDistances copy(double d, double d2, double d3, double d4, double d5, double d6) {
        return new SideDistances(d, d2, d3, d4, d5, d6);
    }

    public static SideDistances copy$default(SideDistances sideDistances, double d, double d2, long l, double d3, double d4, double d5, double d6, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-3274402256061193354L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                d = sideDistances.westDelta;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    d2 = sideDistances.eastDelta;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    d3 = sideDistances.northDelta;
                }
                n2 = n & SideDistances.zkmBootstrap2("n", (int)9068, (long)(0x5093C66689A8650EL ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    d4 = sideDistances.southDelta;
                }
                n2 = n & SideDistances.zkmBootstrap2("n", (int)24810, (long)(0x79A06760034DA689L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                d5 = sideDistances.upDelta;
            }
            n2 = n & SideDistances.zkmBootstrap2("n", (int)15075, (long)(0xFEA67649D3FFC84L ^ l));
        }
        if (n2 != 0) {
            d6 = sideDistances.downDelta;
        }
        return sideDistances.copy(d, d2, d3, d4, d5, d6);
    }

    @NotNull
    public String toString() {
        double d = this.downDelta;
        double d2 = this.upDelta;
        double d3 = this.southDelta;
        double d4 = this.northDelta;
        double d5 = this.eastDelta;
        double d6 = this.westDelta;
        return "DirectionDeltas(westDelta=" + d6 + ", eastDelta=" + d5 + ", northDelta=" + d4 + ", southDelta=" + d3 + ", upDelta=" + d2 + ", downDelta=" + d + ")";
    }

    public int hashCode() {
        int n = Double.hashCode(this.westDelta);
        n = n * 31 + Double.hashCode(this.eastDelta);
        n = n * 31 + Double.hashCode(this.northDelta);
        n = n * 31 + Double.hashCode(this.southDelta);
        n = n * 31 + Double.hashCode(this.upDelta);
        n = n * 31 + Double.hashCode(this.downDelta);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SideDistances)) {
            return false;
        }
        SideDistances sideDistances = (SideDistances)object;
        if (Double.compare(this.westDelta, sideDistances.westDelta) != 0) {
            return false;
        }
        if (Double.compare(this.eastDelta, sideDistances.eastDelta) != 0) {
            return false;
        }
        if (Double.compare(this.northDelta, sideDistances.northDelta) != 0) {
            return false;
        }
        if (Double.compare(this.southDelta, sideDistances.southDelta) != 0) {
            return false;
        }
        if (Double.compare(this.upDelta, sideDistances.upDelta) != 0) {
            return false;
        }
        return Double.compare(this.downDelta, sideDistances.downDelta) == 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SideDistances.a = ZkmKeys.a(-3239512913051586763L, -7393143654429478324L, MethodHandles.lookup().lookupClass()).a(26495476384043L);
                        SideDistances.d = new HashMap<K, V>(13);
                        var11 = SideDistances.a ^ 131004363331857L;
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
                        var20_3 = new String[6];
                        var18_4 = 0;
                        var17_5 = "N2\u00b6\u000f\u00ec\u00a9\u0007_\u008a_\u001ac\u009b\u00e3G,\u0012v\u00f5m\u00c0/j\u0082\u00ca*\u00a1\u001a\u00054\u008e\u00cc0\u00cd\u0082l\u0083\u009f*\u00df\u00e1]\u00fd\u00c0\r\u008d\u00a3+5-\u00f3\u00a7K\r\u00d027\u00b2&\u00d4\u00bdz@\u00a7\u00ac\u00d7\u00db#\u00c4\u00c7\u00bb\u00f2\u009aK\u00b0{\u00ae\\\u00c8ne\u0018\u00bb`\u000e=\u00a2I\u00e0\u0014\u00a8\u00da\u00a9\u00b8\u00d8H0\u00fb`\u008d\u00c2\u0081J\u00fa$p R-\u00b1\u0088m\u00dc\u00e4e\u00cb\u00176\u00ad\u00d7h\u00d1\u001a\u001a\u00a0\u00e8\u00fb(\u00cci\u00cb&sw[\u00f0\u001f\u00b0\u00b0";
                        var19_6 = 139;
                        var16_7 = 32;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = SideDistances.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u009a\u001c\u0087.#\u00a2[\u009e\u0003\u00f4\u00e9L\u001b\u00f1UG\u00ed\u00e2N\u00b8\u00d9\u00e0\u009a\u00a0\u00c8V\u009eAS\u00ff>n (\u0099:1\u0094\u00f1\u00e8\u0007\u008ek\u00a7\u00a8:\u00f9\u009eI\u00fd\u00ab\u00fc\u008f\u00b2\u00ca{\u00d7UB\"\u00e6+\u00df\u008a\u009d";
                            var19_6 = 65;
                            var16_7 = 32;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = SideDistances.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
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
                SideDistances.b = var20_3;
                SideDistances.c = new String[6];
                SideDistances.h = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "Iad\u00e5\u00b0MX\u00b3\u007f\bH\u0080[9\u0091\u00ac@\u00f7\tM9_\u00b4-";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u0087c\u00f4.y\u00efJOh.\u00de\u00d7-\\1\u00c6";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl104:
                // 1 sources

                ** continue;
            }
        }
        SideDistances.f = var6_12;
        SideDistances.g = new Integer[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xC05;
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
                throw new RuntimeException("su/catlean/cg", exception);
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
            SideDistances.c[n2] = SideDistances.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SideDistances.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/cg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x15A4;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/cg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SideDistances.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SideDistances.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/cg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SideDistances.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SideDistances.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

