/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import su.catlean.module.rage.NoFallFallDistanceMode;
import su.catlean.module.rage.NoFallMode;
import su.catlean.zkm.ZkmKeys;

public final class NoFall$WhenMappings {
    public static final int[] W;
    public static final int[] n;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static {
        long l = ZkmKeys.a(980086695060823966L, -6792406465240181909L, MethodHandles.lookup().lookupClass()).a(20156106000369L) ^ 0x671E9402E6DFL;
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        byte[] byArray3 = cipher.doFinal(new byte[]{-115, -30, 23, 46, 67, -22, -51, 36});
        long l2 = ((long)byArray3[0] & 0xFFL) << 56 | ((long)byArray3[1] & 0xFFL) << 48 | ((long)byArray3[2] & 0xFFL) << 40 | ((long)byArray3[3] & 0xFFL) << 32 | ((long)byArray3[4] & 0xFFL) << 24 | ((long)byArray3[5] & 0xFFL) << 16 | ((long)byArray3[6] & 0xFFL) << 8 | (long)byArray3[7] & 0xFFL;
        int[] nArray = new int[NoFallMode.values().length];
        try {
            nArray[NoFallMode.Rubberband.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NoFallMode.Items.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NoFallMode.MatrixOffGround.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NoFallMode.Vanilla.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NoFallMode.GRIM_NEW.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NoFallMode.GrimOld.ordinal()] = (int)l2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NoFallMode.GrimBypass.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        W = nArray;
        nArray = new int[NoFallFallDistanceMode.values().length];
        try {
            nArray[NoFallFallDistanceMode.Custom.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[NoFallFallDistanceMode.Calc.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        n = nArray;
    }
}

