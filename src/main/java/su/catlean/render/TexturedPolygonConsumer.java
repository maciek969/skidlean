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

public final class TexturedPolygonConsumer
implements VertexConsumer {
    @NotNull
    private final BatchedBuffer polygon;
    @NotNull
    private final Color c;
    @NotNull
    private final float[] h;
    @NotNull
    private final float[] k;
    @NotNull
    private final float[] x;
    @NotNull
    private final float[] g;
    @NotNull
    private final float[] J;
    private int vertexIndex;
    private static final long b = ZkmKeys.a(-7434242412849004936L, 781587174107891465L, MethodHandles.lookup().lookupClass()).a(204322601711376L);
    private static final String color;

    public TexturedPolygonConsumer(@NotNull BatchedBuffer batchedBuffer, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(batchedBuffer, TexturedPolygonConsumer.color);
        Intrinsics.checkNotNullParameter(color, "c");
        this.polygon = batchedBuffer;
        this.c = color;
        this.h = new float[4];
        this.k = new float[4];
        this.x = new float[4];
        this.g = new float[4];
        this.J = new float[4];
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
        this.h[this.vertexIndex] = f;
        this.k[this.vertexIndex] = f2;
        this.x[this.vertexIndex] = f3;
        return this;
    }

    @NotNull
    public TexturedPolygonConsumer S() {
        return this;
    }

    @NotNull
    public TexturedPolygonConsumer U() {
        return this;
    }

    @Override
    @NotNull
    public VertexConsumer texture(float f, float f2) {
        block0: {
            this.g[this.vertexIndex] = f;
            Object var11_3 = null;
            this.J[this.vertexIndex] = f2;
            int n = this.vertexIndex;
            this.vertexIndex = n + 1;
            if (this.vertexIndex != 4) break block0;
            this.polygon.vertex(this.h[0], this.k[0], this.x[0]).texture(this.g[0], this.J[0]).color(this.c).vertex(this.h[1], this.k[1], this.x[1]).texture(this.g[1], this.J[1]).color(this.c).vertex(this.h[2], this.k[2], this.x[2]).texture(this.g[2], this.J[2]).color(this.c).vertex(this.h[3], this.k[3], this.x[3]).texture(this.g[3], this.J[3]).color(this.c);
            this.vertexIndex = 0;
        }
        return this;
    }

    @NotNull
    public TexturedPolygonConsumer L() {
        return this;
    }

    @NotNull
    public TexturedPolygonConsumer q() {
        return this;
    }

    @NotNull
    public TexturedPolygonConsumer A(float f, float f2, float f3) {
        return this;
    }

    @NotNull
    public TexturedPolygonConsumer Q(float f) {
        return this;
    }

    @Override
    public VertexConsumer color(int n, int n2, int n3, int n4) {
        return this.S();
    }

    @Override
    public VertexConsumer color(int n) {
        return this.U();
    }

    @Override
    public VertexConsumer overlay(int n, int n2) {
        return this.L();
    }

    @Override
    public VertexConsumer light(int n, int n2) {
        return this.q();
    }

    @Override
    public VertexConsumer normal(float f, float f2, float f3) {
        return this.A(f, f2, f3);
    }

    @Override
    public VertexConsumer lineWidth(float f) {
        return this.Q(f);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = b ^ 0x16FAF7E8B559L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("J\u000b\u00c8\u00bd\u00e6C\u00f7\u0010".getBytes("ISO-8859-1"));
                color = TexturedPolygonConsumer.zkmDecodeUtf8(byArray3).intern();
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

