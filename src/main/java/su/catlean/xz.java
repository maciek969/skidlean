/*
 * Decompiled with CFR 0.152.
 */
package su.catlean;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import su.catlean.module.esp.HoleEspMode;
import su.catlean.zkm.ZkmKeys;

public final class xz {
    public static final int[] h;
    private static String[] E;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static {
        long l = ZkmKeys.a(-6958391975288962547L, -1307215042040371782L, MethodHandles.lookup().lookupClass()).a(210646383646051L) ^ 0xEC72C5C0DB3L;
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
        byte[] byArray3 = cipher.doFinal(new byte[]{113, -124, -16, 127, -78, 32, -15, -30});
        long l2 = ((long)byArray3[0] & 0xFFL) << 56 | ((long)byArray3[1] & 0xFFL) << 48 | ((long)byArray3[2] & 0xFFL) << 40 | ((long)byArray3[3] & 0xFFL) << 32 | ((long)byArray3[4] & 0xFFL) << 24 | ((long)byArray3[5] & 0xFFL) << 16 | ((long)byArray3[6] & 0xFFL) << 8 | (long)byArray3[7] & 0xFFL;
        int[] nArray = new int[HoleEspMode.values().length];
        try {
            nArray[HoleEspMode.CubeFill.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[HoleEspMode.Shader.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[HoleEspMode.CubeOutline.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[HoleEspMode.CubeBoth.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[HoleEspMode.Fade.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            nArray[HoleEspMode.BLOOM.ordinal()] = (int)l2;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        h = nArray;
    }

    public static void zkmSetFlag(String[] stringArray) {
        E = stringArray;
    }

    public static String[] zkmGetFlag() {
        return E;
    }
}

