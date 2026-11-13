/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

final class BreakingBlock {
    @NotNull
    private final BlockPos pos;
    private float progress;
    private float prevProgress;
    private static final long a = ZkmKeys.a(-5474669734960199274L, -8774629665145894539L, MethodHandles.lookup().lookupClass()).a(146412538928237L);
    private static final String b;

    public BreakingBlock(@NotNull BlockPos blockPos, float f, float f2) {
        Intrinsics.checkNotNullParameter(blockPos, b);
        this.pos = blockPos;
        this.progress = f;
        this.prevProgress = f2;
    }

    public BreakingBlock(BlockPos blockPos, float f, float f2, DefaultConstructorMarker defaultConstructorMarker) {
        f = 0.0f;
        f2 = 0.0f;
        this(blockPos, f, f2);
    }

    @NotNull
    public final BlockPos getPos() {
        return this.pos;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final void setProgress(float f) {
        this.progress = f;
    }

    public final float getPrevProgress() {
        return this.prevProgress;
    }

    public final void setPrevProgress(float f) {
        this.prevProgress = f;
    }

    public final boolean tick() {
        this.prevProgress = this.progress;
        this.progress += 0.025f;
        if (Globals.getWorld().isAir(this.pos)) {
            return true;
        }
        return this.progress > 2.0f;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x7A88A3FAA60FL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal(".Q\u00bc^+\u00e5q\u007f".getBytes("ISO-8859-1"));
                b = BreakingBlock.zkmDecodeUtf8(byArray3).intern();
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

