/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import java.awt.Color;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ParticleTrailPoint {
    private final float posX;
    private final float posY;
    private final float posZ;
    private int age;
    @NotNull
    private final Color color;
    private static final long a = ZkmKeys.a(3872253167715393531L, 5390006120305610838L, MethodHandles.lookup().lookupClass()).a(12828209212602L);
    private static final String b;

    public ParticleTrailPoint(float f, float f2, float f3, int n, @NotNull Color color) {
        int n2 = 0;
        long l = 0L;
        long l2 = ((long)n2 << 48 | l << 16 >>> 16) ^ a;
        ZkmReferences.zkmBootstrap("\u00c5", (long)4869464287066375372L, (long)l2);
        Intrinsics.checkNotNullParameter(color, b);
        this.posX = f;
        this.posY = f2;
        this.posZ = f3;
        this.age = n;
        this.color = color;
        if (ZkmReferences.zkmBootstrap("\u00c5", (long)4885278609586937178L, (long)l2) != null) {
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new String[4], (long)4831673250781811244L, (long)l2);
        }
    }

    public final float getPosX() {
        return this.posX;
    }

    public final float getPosY() {
        return this.posY;
    }

    public final float getPosZ() {
        return this.posZ;
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int n) {
        this.age = n;
    }

    @NotNull
    public final Color getColor() {
        return this.color;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x7C8485484882L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u001d\u00e9\u00e7L\u0015$\u00e6Q".getBytes("ISO-8859-1"));
                b = ParticleTrailPoint.zkmDecodeUtf8(byArray3).intern();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
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
}

