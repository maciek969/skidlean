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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

final class DamageNumber {
    @NotNull
    private final Entity entity;
    private final float hp;
    private final double posX;
    private final double posY;
    private final double posZ;
    private int ticks;
    private static final long a = ZkmKeys.a(-4367063019617745768L, 6073599645286526085L, MethodHandles.lookup().lookupClass()).a(48227527715650L);
    private static final String b;
    private static final long c;

    public DamageNumber(@NotNull Entity entity, float f, double d, double d2, double d3, int n) {
        Intrinsics.checkNotNullParameter(entity, b);
        this.entity = entity;
        this.hp = f;
        this.posX = d;
        this.posY = d2;
        this.posZ = d3;
        this.ticks = n;
    }

    public DamageNumber(Entity entity, float f, double d, double d2, double d3, DefaultConstructorMarker defaultConstructorMarker) {
        int n = 0;
        this(entity, f, d, d2, d3, n);
    }

    @NotNull
    public final Entity getEntity() {
        return this.entity;
    }

    public final float getHp() {
        return this.hp;
    }

    public final double getPosX() {
        return this.posX;
    }

    public final double getPosY() {
        return this.posY;
    }

    public final double getPosZ() {
        return this.posZ;
    }

    public final int getTicks() {
        return this.ticks;
    }

    public final void setTicks(int n) {
        this.ticks = n;
    }

    public final float getAlpha() {
        float f;
        block0: {
            long l = 0L;
            l = a ^ l;
            ZkmReferences.zkmBootstrap("\u00c5", (long)3408422373891462010L, (long)l);
            f = RenderUtil.INSTANCE.interpolate(RangesKt.coerceAtLeast((float)this.ticks - 1.0f, 0.0f), this.ticks);
            if (ZkmReferences.zkmBootstrap("\u00c5", (long)3399849089144074687L, (long)l) == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[4], (long)3354647753938035602L, (long)l);
        }
        return f;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x6CF345D06E52L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        byte[] byArray3 = cipher.doFinal("\u0089\u00d0S\u00e6c\u00bbVq".getBytes("ISO-8859-1"));
        b = DamageNumber.zkmDecodeUtf8(byArray3).intern();
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{44, 74, -16, 118, 4, -117, -15, -25});
                c = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                return;
            }
            byArray5 = byArray5;
            byArray5[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
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

