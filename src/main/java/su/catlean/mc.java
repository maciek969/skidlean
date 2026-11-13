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
import net.minecraft.util.Hand;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.zkm.ZkmKeys;

final class mc
implements Runnable {
    public static final mc J;
    private static final long a;
    private static final long b;

    mc() {
    }

    @Override
    public final void run() {
        InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, (int)b, null);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-5549201535174204098L, 2627237992253666135L, MethodHandles.lookup().lookupClass()).a(31942079806234L);
        long l = a ^ 0x2F783B714A99L;
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(new byte[]{-125, -122, -9, 124, -94, -113, -23, 115});
                b = ((long)byArray3[0] & 0xFFL) << 56 | ((long)byArray3[1] & 0xFFL) << 48 | ((long)byArray3[2] & 0xFFL) << 40 | ((long)byArray3[3] & 0xFFL) << 32 | ((long)byArray3[4] & 0xFFL) << 24 | ((long)byArray3[5] & 0xFFL) << 16 | ((long)byArray3[6] & 0xFFL) << 8 | (long)byArray3[7] & 0xFFL;
                J = new mc();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }
}

