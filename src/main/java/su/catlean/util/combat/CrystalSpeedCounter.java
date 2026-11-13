/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.ArrayDeque;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class CrystalSpeedCounter {
    @NotNull
    public static final CrystalSpeedCounter INSTANCE;
    @NotNull
    private static final ArrayDeque arrayDeque;
    private static int Y;
    private static final long a;
    private static final long b;

    private CrystalSpeedCounter() {
    }

    public final int q() {
        return Y;
    }

    public final void recordPlace() {
        long l = Globals.now();
        arrayDeque.addLast(l);
        while (true) {
            long l2;
            Long l3 = (Long)arrayDeque.firstOrNull();
            boolean bl = l3 != null ? (l2 = ((Number)l3).longValue()) + (long)((int)b) < l : false;
            if (!bl) break;
            arrayDeque.removeFirst();
        }
        Y = arrayDeque.size();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-1401122628156748365L, -8725047003296678295L, MethodHandles.lookup().lookupClass()).a(137495720706535L);
        long l = a ^ 0x59E162DF1795L;
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(new byte[]{-27, -46, -7, 110, -56, -42, -21, 96});
                b = ((long)byArray3[0] & 0xFFL) << 56 | ((long)byArray3[1] & 0xFFL) << 48 | ((long)byArray3[2] & 0xFFL) << 40 | ((long)byArray3[3] & 0xFFL) << 32 | ((long)byArray3[4] & 0xFFL) << 24 | ((long)byArray3[5] & 0xFFL) << 16 | ((long)byArray3[6] & 0xFFL) << 8 | (long)byArray3[7] & 0xFFL;
                INSTANCE = new CrystalSpeedCounter();
                arrayDeque = new ArrayDeque();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

