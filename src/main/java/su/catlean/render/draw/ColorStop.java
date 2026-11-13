/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 */
package su.catlean.render.draw;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import su.catlean.zkm.ZkmKeys;

public final class ColorStop {
    private final float atX;
    private final float atY;
    private final float r;
    private final float g;
    private final float b;
    private final float Z;
    private final int w;
    private final int h;
    private final int ow;
    private final int oh;
    private final int u;
    private final int v;
    private final float z;
    @NotNull
    private final Matrix3x2f matrix;
    private static final long a = ZkmKeys.a(-1323630252890563535L, -4327020380130851021L, MethodHandles.lookup().lookupClass()).a(155679556902218L);
    private static final String c;

    public ColorStop(float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5, int n6, float f7, @NotNull Matrix3x2f matrix3x2f) {
        Intrinsics.checkNotNullParameter(matrix3x2f, c);
        this.atX = f;
        this.atY = f2;
        this.r = f3;
        this.g = f4;
        this.b = f5;
        this.Z = f6;
        this.w = n;
        this.h = n2;
        this.ow = n3;
        this.oh = n4;
        this.u = n5;
        this.v = n6;
        this.z = f7;
        this.matrix = matrix3x2f;
    }

    public final float getAtX() {
        return this.atX;
    }

    public final float getAtY() {
        return this.atY;
    }

    public final float getR() {
        return this.r;
    }

    public final float getG() {
        return this.g;
    }

    public final float getB() {
        return this.b;
    }

    public final float s() {
        return this.Z;
    }

    public final int getW() {
        return this.w;
    }

    public final int getH() {
        return this.h;
    }

    public final int getOw() {
        return this.ow;
    }

    public final int getOh() {
        return this.oh;
    }

    public final int getU() {
        return this.u;
    }

    public final int getV() {
        return this.v;
    }

    public final float getZ() {
        return this.z;
    }

    @NotNull
    public final Matrix3x2f getMatrix() {
        return this.matrix;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x298796E8C4L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u008c\u00cd\u00b6\u00d4\u009cSb\u007f".getBytes("ISO-8859-1"));
                c = ColorStop.zkmDecodeUtf8(byArray3).intern();
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

