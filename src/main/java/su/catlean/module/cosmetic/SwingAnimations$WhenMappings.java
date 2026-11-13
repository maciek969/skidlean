/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import su.catlean.module.cosmetic.SwingAnimationsMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SwingAnimations$WhenMappings {
    public static final int[] U;
    private static int[] S;

    /*
     * Unable to fully structure code
     */
    static {
        block32: {
            block31: {
                var11 = ZkmKeys.a(8812510347309605629L, -8638373890432316000L, MethodHandles.lookup().lookupClass()).a(12842099158326L) ^ 64580000981265L;
                if (ZkmReferences.zkmBootstrap("\u00c5", (long)288612428818374819L, (long)var11) != null) {
                    ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[5], (long)340905215072903697L, (long)var11);
                }
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
                var0_3 = new long[6];
                var4_4 = 0;
                var5_5 = "\u00ea]qI\u00b6\u00bfV\u00d4\u00d9\u000e\u008b\u00fa\u00c6\u0097\u00b2\u00ca5Z\u0092%xy\u00ed\u00b5\u00dc\u00b6\u00ee\u00daV\u009d\u00d8X";
                var6_6 = 32;
                var3_7 = 0;
                while (true) {
                    var7_8 = var5_5.substring(var3_7, var3_7 += 8).getBytes("ISO-8859-1");
                    v3 = var0_3;
                    v4 = var4_4++;
                    v5 = ((long)var7_8[0] & 255L) << 56 | ((long)var7_8[1] & 255L) << 48 | ((long)var7_8[2] & 255L) << 40 | ((long)var7_8[3] & 255L) << 32 | ((long)var7_8[4] & 255L) << 24 | ((long)var7_8[5] & 255L) << 16 | ((long)var7_8[6] & 255L) << 8 | (long)var7_8[7] & 255L;
                    v6 = -1;
                    break block31;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var3_7 < var6_6) ** continue;
                    var5_5 = "\u0010\u00833@7\u00f3k\u000b\u0017\u00d5\u00f6]ZC\u00ea\u0004";
                    var6_6 = 16;
                    var3_7 = 0;
                    while (true) {
                        var7_8 = var5_5.substring(var3_7, var3_7 += 8).getBytes("ISO-8859-1");
                        v3 = var0_3;
                        v4 = var4_4++;
                        v5 = ((long)var7_8[0] & 255L) << 56 | ((long)var7_8[1] & 255L) << 48 | ((long)var7_8[2] & 255L) << 40 | ((long)var7_8[3] & 255L) << 32 | ((long)var7_8[4] & 255L) << 24 | ((long)var7_8[5] & 255L) << 16 | ((long)var7_8[6] & 255L) << 8 | (long)var7_8[7] & 255L;
                        v6 = 0;
                        break block31;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var3_7 < var6_6) ** continue;
                    break block32;
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
lbl52:
                // 1 sources

                ** continue;
            }
        }
        var13_11 = new int[SwingAnimationsMode.values().length];
        try {
            var13_11[SwingAnimationsMode.Default.ordinal()] = 1;
        }
        catch (NoSuchFieldError v8) {}
        try {
            var13_11[SwingAnimationsMode.Bonk.ordinal()] = 2;
        }
        catch (NoSuchFieldError v9) {}
        try {
            var13_11[SwingAnimationsMode.Rubber.ordinal()] = 3;
        }
        catch (NoSuchFieldError v10) {}
        try {
            var13_11[SwingAnimationsMode.Flip.ordinal()] = 4;
        }
        catch (NoSuchFieldError v11) {}
        try {
            var13_11[SwingAnimationsMode.Snap.ordinal()] = 5;
        }
        catch (NoSuchFieldError v12) {}
        try {
            var13_11[SwingAnimationsMode.Hook.ordinal()] = (int)var0_3[4];
        }
        catch (NoSuchFieldError v13) {}
        try {
            var13_11[SwingAnimationsMode.Swipe.ordinal()] = (int)var0_3[3];
        }
        catch (NoSuchFieldError v14) {}
        try {
            var13_11[SwingAnimationsMode.Tap.ordinal()] = (int)var0_3[1];
        }
        catch (NoSuchFieldError v15) {}
        try {
            var13_11[SwingAnimationsMode.Inject.ordinal()] = (int)var0_3[2];
        }
        catch (NoSuchFieldError v16) {}
        try {
            var13_11[SwingAnimationsMode.Slap.ordinal()] = (int)var0_3[5];
        }
        catch (NoSuchFieldError v17) {}
        try {
            var13_11[SwingAnimationsMode.Akrien.ordinal()] = (int)var0_3[0];
        }
        catch (NoSuchFieldError v18) {}
        SwingAnimations$WhenMappings.U = var13_11;
    }

    public static void zkmSetFlag(int[] nArray) {
        S = nArray;
    }

    public static int[] zkmGetFlag() {
        return S;
    }
}

