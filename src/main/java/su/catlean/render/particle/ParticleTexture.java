/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render.particle;

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
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class ParticleTexture
extends Enum {
    private final float C;
    private final float B;
    private final float w;
    private final float U;
    public static final ParticleTexture SMOKE;
    public static final ParticleTexture CIRCLE;
    public static final ParticleTexture CRACK;
    public static final ParticleTexture CLOUD;
    public static final ParticleTexture DOLLAR;
    public static final ParticleTexture DROP;
    public static final ParticleTexture FIREFLY;
    public static final ParticleTexture FIREFLY_ALT;
    public static final ParticleTexture HEART;
    public static final ParticleTexture SNOWFLAKE;
    public static final ParticleTexture STAR;
    public static final ParticleTexture TRIANGLE1;
    public static final ParticleTexture TRIANGLE2;
    public static final ParticleTexture TRIANGLE3;
    public static final ParticleTexture TRIANGLE4;
    public static final ParticleTexture HALO;
    public static final ParticleTexture TRIANGLE5;
    private static final /* synthetic */ ParticleTexture[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static int[] r;
    private static final long a;
    private static final long[] b;
    private static final Integer[] c;
    private static final Map d;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ParticleTexture(float f3, float f4) {
        void var6_4;
        void var5_3;
        void var2_-1;
        void var1_-1;
        this.C = f3;
        this.B = f4;
        this.w = var5_3;
        this.U = var6_4;
    }

    public final float o() {
        return this.C;
    }

    public final float v() {
        return this.B;
    }

    public final float L() {
        return this.w;
    }

    public final float w() {
        return this.U;
    }

    public static ParticleTexture[] values() {
        return (ParticleTexture[])$VALUES.clone();
    }

    public static ParticleTexture valueOf(String string) {
        return Enum.valueOf(ParticleTexture.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ ParticleTexture[] $values() {
        ParticleTexture[] particleTextureArray = new ParticleTexture[]{SMOKE, CIRCLE, CRACK, CLOUD, DOLLAR, DROP, FIREFLY, FIREFLY_ALT, HEART, SNOWFLAKE, STAR, TRIANGLE1, TRIANGLE2, TRIANGLE3, TRIANGLE4, HALO, TRIANGLE5};
        return particleTextureArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ParticleTexture.a = ZkmKeys.a(-3732756354189874883L, -8004455321125896098L, MethodHandles.lookup().lookupClass()).a(30233294075805L);
                        var20 = ParticleTexture.a ^ 112704465528274L;
                        new int[0];
                        var12_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var13_2 = 1; var13_2 < 8; ++var13_2) {
                            v2 = v2;
                            v2[var13_2] = (byte)(var20 << var13_2 * 8 >>> 56);
                        }
                        var12_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var11_3 = new String[17];
                        var17_4 = 0;
                        var16_5 = "\u00f0\u00f0\r\u00e8\u0012u\u009f\u00fc\u0010\u0099\t\u00a2Y\u00c8\u00e9\u00a8\u00d7\u001a1$\u0002\u00808qf\b\u00dbS+\u008f\u00d0|k\u0005\b\u00f8\u00fc2\u0084\u00ac\u00f6\u000e(\bk\u00ec\u00c9\u00b8\u00e7\u00ca\u00f5T\b\u0089{wDw\u00ee\u00ba\u00d1\u0010\u0099\t\u00a2Y\u00c8\u00e9\u00a8\u00d7\u00ceg\u0083\u00a5\u00c7U\u00b1u\bv\u00853@\u0003\u008a\u00c4W\b.\u0089\f-x\u00983\u0004\b\u0091\u001d\u009aGs\u00ef\u008f|\u0010\u0099\t\u00a2Y\u00c8\u00e9\u00a8\u00d7\u00f9a\u00ccV(\u0011\u00d4a\b\u00cd\u00af\u00b9\u00ae\u00b5\u008f\u00aa\u00a6\b$\u0097\u0093\u0089\u0082OI4\u0010T&Z\u00914Cz\u009a\u0093\u00f4G\u001fg5V\u00b9\u0010\u0099\t\u00a2Y\u00c8\u00e9\u00a8\u00d7\u0013\u001a\u00f6\u00bc\u0089\u00aa\u00f7e";
                        var18_6 = 174;
                        var15_7 = 8;
                        var14_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var14_8;
                            v4 = var16_5.substring(v3, v3 + var15_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = ParticleTexture.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            var16_5 = "\u0011\b#\u00bb\u008b(\u0089\u0081\u00ea\u009d\u0004\u00fa\u00e1\u00c7\u0010<\u0010\u0099\t\u00a2Y\u00c8\u00e9\u00a8\u00d7]\u00ef\u009f[\u00f7>\nG";
                            var18_6 = 33;
                            var15_7 = 16;
                            var14_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var14_8;
                                v4 = var16_5.substring(v6, v6 + var15_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = ParticleTexture.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var12_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                ParticleTexture.d = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[23];
                var3_13 = 0;
                var4_14 = "s\u00d9\t\u001c\u00b0\u00059\u00c5@\u00b2\u00d1\u001ep\u00ec*\u0086}.\u00aaZW\u00d4t\u00c0\u00ec\u00ec\u001e\u00c7a\u00cfN\u00ac\u00eb\u00d0\u00dd\u00b8\u00c7\u00ee\u00e74\u00d8\u00cb\u00e4\u0080W\f\u0016\u00adcT\u00d9\u00c4+\u00ff\u00af\u00cc\u009b\u00f8R\u00ff\u0093AJ\u00d2\u00fa\u00c5\u0089\u000b\u000b\u00a0|\u00a7x\u00e8HV\u00e7\u00c5\u007f\u00f1m;\u0011Z>u\u00fd\u0082\u00f6#\u000e\u00f9\u00ed\u00cf\u00c0=&W\u00ae\u00d9\u0089b\u00ddI\u00f4\u0000pvH\u0018W\u00e1a\u0098\u00d82d\u00bd|M\b\u0010\u00f8\u001c\u00beVDVTAG\u00b6\u0098\u0006\u001b\u00d0'\u0088\u00a2=\bN\u00be\u00d5\b\u009c\u00d7\u0091!o\u00b2n\u00b7\u009fQ\u00eb\f\u0001!\u00e0\u0082\u00f4Y\u0007\u00a7&\u0091\u00e8";
                var5_15 = 168;
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
lbl77:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "&\u00a0\u0086`\u00a3\u008f\u0000\u0018\u00c7c\u00bd!\u00d1\u0007\u00f1!";
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
lbl90:
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
lbl103:
                // 1 sources

                ** continue;
            }
        }
        ParticleTexture.b = var6_12;
        ParticleTexture.c = new Integer[23];
        ParticleTexture.SMOKE = new ParticleTexture(var11_3[9], 0, 0.0f, 0.0f, 0.3906f, 0.3906f);
        ParticleTexture.CIRCLE = new ParticleTexture(var11_3[5], 1, 0.3906f, 0.0f, 0.6406f, 0.25f);
        ParticleTexture.CRACK = new ParticleTexture(var11_3[2], 2, 0.6406f, 0.0f, 0.8906f, 0.25f);
        ParticleTexture.CLOUD = new ParticleTexture(var11_3[0], 3, 0.8906f, 0.0f, 0.9531f, 0.0625f);
        ParticleTexture.DOLLAR = new ParticleTexture(var11_3[7], 4, 0.8906f, 0.0625f, 0.9531f, 0.125f);
        ParticleTexture.DROP = new ParticleTexture(var11_3[3], 5, 0.8906f, 0.125f, 0.9531f, 0.1875f);
        ParticleTexture.FIREFLY = new ParticleTexture(var11_3[4], 6, 0.8906f, 0.1875f, 0.9531f, 0.25f);
        ParticleTexture.FIREFLY_ALT = new ParticleTexture(var11_3[13], 7, 0.3906f, 0.25f, 0.4531f, 0.3125f);
        ParticleTexture.HEART = new ParticleTexture(var11_3[11], 8, 0.4531f, 0.25f, 0.5156f, 0.3125f);
        ParticleTexture.SNOWFLAKE = new ParticleTexture(var11_3[15], 9, 0.5156f, 0.25f, 0.5781f, 0.3125f);
        ParticleTexture.STAR = new ParticleTexture(var11_3[12], 10, 0.5781f, 0.25f, 0.6406f, 0.3125f);
        ParticleTexture.TRIANGLE1 = new ParticleTexture(var11_3[14], 11, 0.6406f, 0.25f, 0.7031f, 0.3125f);
        ParticleTexture.TRIANGLE2 = new ParticleTexture(var11_3[16], 12, 0.7031f, 0.25f, 0.7656f, 0.3125f);
        ParticleTexture.TRIANGLE3 = new ParticleTexture(var11_3[6], 13, 0.7656f, 0.25f, 0.8281f, 0.3125f);
        ParticleTexture.TRIANGLE4 = new ParticleTexture(var11_3[1], 14, 0.8281f, 0.25f, 0.8906f, 0.3125f);
        ParticleTexture.HALO = new ParticleTexture(var11_3[8], 15, 0.8906f, 0.25f, 0.9531f, 0.3096f);
        ParticleTexture.TRIANGLE5 = new ParticleTexture(var11_3[10], 16, 0.3906f, 0.3125f, 0.4531f, 0.3701f);
        ParticleTexture.$VALUES = ParticleTexture.$values();
        ParticleTexture.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ParticleTexture.$VALUES);
    }

    public static void zkmSetFlag(int[] nArray) {
        r = nArray;
    }

    public static int[] zkmGetFlag() {
        return r;
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

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1831;
        if (c[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = b[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])d.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/gi", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ParticleTexture.c[n2] = n3;
        }
        return c[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ParticleTexture.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/gi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ParticleTexture.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

