/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import su.catlean.zkm.ZkmKeys;

final class AutoCrystalRender$FadeEntry {
    private int age;
    private static final long a = ZkmKeys.a(3242951441362517114L, -8823181283307768657L, MethodHandles.lookup().lookupClass()).a(198596334574532L);
    private static final long b;

    public AutoCrystalRender$FadeEntry(int n) {
        this.age = n;
    }

    public AutoCrystalRender$FadeEntry(DefaultConstructorMarker defaultConstructorMarker) {
        int n = (int)b;
        this(n);
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int n) {
        this.age = n;
    }

    public AutoCrystalRender$FadeEntry() {
        this(null);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x7CEED125BD03L;
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(new byte[]{-53, -50, -106, -56, 91, 31, 10, -80});
                b = ((long)byArray3[0] & 0xFFL) << 56 | ((long)byArray3[1] & 0xFFL) << 48 | ((long)byArray3[2] & 0xFFL) << 40 | ((long)byArray3[3] & 0xFFL) << 32 | ((long)byArray3[4] & 0xFFL) << 24 | ((long)byArray3[5] & 0xFFL) << 16 | ((long)byArray3[6] & 0xFFL) << 8 | (long)byArray3[7] & 0xFFL;
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }
}

