/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import su.catlean.render.AnimationStyle;
import su.catlean.zkm.ZkmKeys;

public final class BlockHighlight$WhenMappings {
    public static final int[] T;

    /*
     * Unable to fully structure code
     */
    static {
        block27: {
            block26: {
                var11 = ZkmKeys.a(-5799819934080933809L, 8268067155742603775L, MethodHandles.lookup().lookupClass()).a(189016999667405L) ^ 4265537606034L;
                var1_1 = Cipher.getInstance("DES/CBC/NoPadding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var11 >>> 56);
                for (var2_2 = 1; var2_2 < 8; ++var2_2) {
                    v2 = v2;
                    v2[var2_2] = (byte)(var11 << var2_2 * 8 >>> 56);
                }
                var1_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var0_3 = new long[4];
                var4_4 = 0;
                var5_5 = "\u00e80a\u009b \f\u0091\u00e1}\u00137\u00bb\u00a3\u008c\u00d1\u00aa";
                var6_6 = 16;
                var3_7 = 0;
                while (true) {
                    var7_8 = var5_5.substring(var3_7, var3_7 += 8).getBytes("ISO-8859-1");
                    v3 = var0_3;
                    v4 = var4_4++;
                    v5 = ((long)var7_8[0] & 255L) << 56 | ((long)var7_8[1] & 255L) << 48 | ((long)var7_8[2] & 255L) << 40 | ((long)var7_8[3] & 255L) << 32 | ((long)var7_8[4] & 255L) << 24 | ((long)var7_8[5] & 255L) << 16 | ((long)var7_8[6] & 255L) << 8 | (long)var7_8[7] & 255L;
                    v6 = -1;
                    break block26;
                    break;
                }
lbl24:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var3_7 < var6_6) ** continue;
                    var5_5 = "\u00af\u0091\u0087\u009d=\u000eiQ\u0006\u00cd\u00e6\u00ddL\u00a9\u00d9\u0089";
                    var6_6 = 16;
                    var3_7 = 0;
                    while (true) {
                        var7_8 = var5_5.substring(var3_7, var3_7 += 8).getBytes("ISO-8859-1");
                        v3 = var0_3;
                        v4 = var4_4++;
                        v5 = ((long)var7_8[0] & 255L) << 56 | ((long)var7_8[1] & 255L) << 48 | ((long)var7_8[2] & 255L) << 40 | ((long)var7_8[3] & 255L) << 32 | ((long)var7_8[4] & 255L) << 24 | ((long)var7_8[5] & 255L) << 16 | ((long)var7_8[6] & 255L) << 8 | (long)var7_8[7] & 255L;
                        v6 = 0;
                        break block26;
                        break;
                    }
                    break;
                }
lbl37:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var3_7 < var6_6) ** continue;
                    break block27;
                    break;
                }
            }
            var8_9 = v5;
            var10_10 = var1_1.doFinal(new byte[]{(byte)(var8_9 >>> 56), (byte)(var8_9 >>> 48), (byte)(var8_9 >>> 40), (byte)(var8_9 >>> 32), (byte)(var8_9 >>> 24), (byte)(var8_9 >>> 16), (byte)(var8_9 >>> 8), (byte)var8_9});
            v7 = ((long)var10_10[0] & 255L) << 56 | ((long)var10_10[1] & 255L) << 48 | ((long)var10_10[2] & 255L) << 40 | ((long)var10_10[3] & 255L) << 32 | ((long)var10_10[4] & 255L) << 24 | ((long)var10_10[5] & 255L) << 16 | ((long)var10_10[6] & 255L) << 8 | (long)var10_10[7] & 255L;
            switch (v6) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl50:
                // 1 sources

                ** continue;
            }
        }
        var13_11 = new int[AnimationStyle.values().length];
        try {
            var13_11[AnimationStyle.Static.ordinal()] = 1;
        }
        catch (NoSuchFieldError v8) {}
        try {
            var13_11[AnimationStyle.Decrease.ordinal()] = 2;
        }
        catch (NoSuchFieldError v9) {}
        try {
            var13_11[AnimationStyle.Fade.ordinal()] = 3;
        }
        catch (NoSuchFieldError v10) {}
        try {
            var13_11[AnimationStyle.FILL.ordinal()] = 4;
        }
        catch (NoSuchFieldError v11) {}
        try {
            var13_11[AnimationStyle.Flash.ordinal()] = 5;
        }
        catch (NoSuchFieldError v12) {}
        try {
            var13_11[AnimationStyle.Grow.ordinal()] = (int)var0_3[0];
        }
        catch (NoSuchFieldError v13) {}
        try {
            var13_11[AnimationStyle.TNT.ordinal()] = (int)var0_3[1];
        }
        catch (NoSuchFieldError v14) {}
        try {
            var13_11[AnimationStyle.Pull.ordinal()] = (int)var0_3[3];
        }
        catch (NoSuchFieldError v15) {}
        try {
            var13_11[AnimationStyle.Hover.ordinal()] = (int)var0_3[2];
        }
        catch (NoSuchFieldError v16) {}
        BlockHighlight$WhenMappings.T = var13_11;
    }
}

