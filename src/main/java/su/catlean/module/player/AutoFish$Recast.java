/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.util.Hand;
import su.catlean.module.player.AutoFish;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.zkm.ZkmKeys;

final class AutoFish$Recast
implements Runnable {
    public static final AutoFish$Recast T;
    private static final long a;
    private static final long b;

    AutoFish$Recast() {
    }

    @Override
    public final void run() {
        InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, (int)b, null);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        AutoFish.M().reset();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(1437604419555595558L, 8539370777938963589L, MethodHandles.lookup().lookupClass()).a(26819831194817L);
        long l = a ^ 0x67FDF07FA787L;
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(new byte[]{-21, 62, 40, 31, 119, -23, -71, 55});
                b = ((long)byArray3[0] & 0xFFL) << 56 | ((long)byArray3[1] & 0xFFL) << 48 | ((long)byArray3[2] & 0xFFL) << 40 | ((long)byArray3[3] & 0xFFL) << 32 | ((long)byArray3[4] & 0xFFL) << 24 | ((long)byArray3[5] & 0xFFL) << 16 | ((long)byArray3[6] & 0xFFL) << 8 | (long)byArray3[7] & 0xFFL;
                T = new AutoFish$Recast();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }
}

