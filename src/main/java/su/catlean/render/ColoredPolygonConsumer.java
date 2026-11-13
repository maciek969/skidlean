/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.awt.Color;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.render.VertexConsumer;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.BatchedBuffer;
import su.catlean.zkm.ZkmKeys;

public final class ColoredPolygonConsumer
implements VertexConsumer {
    @NotNull
    private final BatchedBuffer polygon;
    @NotNull
    private final Color c;
    private static final long a = ZkmKeys.a(9142289781137877925L, -3782846691724570767L, MethodHandles.lookup().lookupClass()).a(236401166593230L);
    private static final String b;

    public ColoredPolygonConsumer(@NotNull BatchedBuffer batchedBuffer, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(batchedBuffer, b);
        Intrinsics.checkNotNullParameter(color, "c");
        this.polygon = batchedBuffer;
        this.c = color;
    }

    @NotNull
    public final BatchedBuffer getPolygon() {
        return this.polygon;
    }

    @NotNull
    public final Color getC() {
        return this.c;
    }

    @Override
    @NotNull
    public VertexConsumer vertex(float f, float f2, float f3) {
        this.polygon.vertex(f, f2, f3);
        return this;
    }

    @NotNull
    public ColoredPolygonConsumer y() {
        return this;
    }

    @Override
    @NotNull
    public VertexConsumer color(int n) {
        return this;
    }

    @Override
    @NotNull
    public VertexConsumer texture(float f, float f2) {
        this.polygon.texture(f, f2).color(this.c);
        return this;
    }

    @NotNull
    public ColoredPolygonConsumer F() {
        return this;
    }

    @NotNull
    public ColoredPolygonConsumer H() {
        return this;
    }

    @NotNull
    public ColoredPolygonConsumer e(float f, float f2, float f3) {
        return this;
    }

    @NotNull
    public ColoredPolygonConsumer S(float f) {
        return this;
    }

    @Override
    public VertexConsumer color(int n, int n2, int n3, int n4) {
        return this.y();
    }

    @Override
    public VertexConsumer overlay(int n, int n2) {
        return this.F();
    }

    @Override
    public VertexConsumer light(int n, int n2) {
        return this.H();
    }

    @Override
    public VertexConsumer normal(float f, float f2, float f3) {
        return this.e(f, f2, f3);
    }

    @Override
    public VertexConsumer lineWidth(float f) {
        return this.S(f);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0xC73B3F1046CL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u00bf\u0081\u00d5\u0019\u00f0\u00184F".getBytes("ISO-8859-1"));
                b = ColoredPolygonConsumer.zkmDecodeUtf8(byArray3).intern();
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

