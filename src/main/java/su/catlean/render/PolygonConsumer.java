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
import su.catlean.render.BoxRenderMode;
import su.catlean.render.ModelRenderer;
import su.catlean.zkm.ZkmKeys;

public final class PolygonConsumer
implements VertexConsumer {
    @NotNull
    private final BatchedBuffer polygon;
    @NotNull
    private final BoxRenderMode m;
    @NotNull
    private final Color c;
    @NotNull
    private final float[] N;
    @NotNull
    private final float[] O;
    @NotNull
    private final float[] color;
    private int vertexIndex;
    private static final long a = ZkmKeys.a(6926736157159243436L, -9151925333158037746L, MethodHandles.lookup().lookupClass()).a(10325118395933L);
    private static final String b;

    public PolygonConsumer(@NotNull BatchedBuffer batchedBuffer, @NotNull BoxRenderMode boxRenderMode, @NotNull Color color) {
        Intrinsics.checkNotNullParameter(batchedBuffer, b);
        Intrinsics.checkNotNullParameter((Object)boxRenderMode, "m");
        Intrinsics.checkNotNullParameter(color, "c");
        this.polygon = batchedBuffer;
        this.m = boxRenderMode;
        this.c = color;
        this.N = new float[4];
        this.O = new float[4];
        this.color = new float[4];
    }

    @NotNull
    public final BatchedBuffer getPolygon() {
        return this.polygon;
    }

    @NotNull
    public final BoxRenderMode getM() {
        return this.m;
    }

    @NotNull
    public final Color getC() {
        return this.c;
    }

    @Override
    @NotNull
    public VertexConsumer vertex(float f, float f2, float f3) {
        VertexConsumer vertexConsumer;
        block5: {
            block6: {
                block8: {
                    PolygonConsumer polygonConsumer;
                    BoxRenderMode boxRenderMode;
                    BoxRenderMode boxRenderMode2;
                    block7: {
                        this.N[this.vertexIndex] = f;
                        this.O[this.vertexIndex] = f2;
                        this.color[this.vertexIndex] = f3;
                        boolean bl = ModelRenderer.v();
                        int n = this.vertexIndex;
                        this.vertexIndex = n + 1;
                        vertexConsumer = this;
                        if (bl) break block5;
                        if (vertexConsumer.vertexIndex != 4) break block6;
                        boxRenderMode2 = this.m;
                        boxRenderMode = BoxRenderMode.FILL;
                        if (bl) break block7;
                        if (boxRenderMode2 == boxRenderMode) {
                            this.polygon.vertex(this.N[0], this.O[0], this.color[0]).color(this.c).vertex(this.N[1], this.O[1], this.color[1]).color(this.c).vertex(this.N[2], this.O[2], this.color[2]).color(this.c).vertex(this.N[3], this.O[3], this.color[3]).color(this.c);
                        }
                        polygonConsumer = this;
                        if (bl) break block8;
                        boxRenderMode2 = polygonConsumer.m;
                        boxRenderMode = BoxRenderMode.OUTLINE;
                    }
                    if (boxRenderMode2 == boxRenderMode) {
                        this.polygon.vertex(this.N[0], this.O[0], this.color[0]).color(this.c).vertex(this.N[1], this.O[1], this.color[1]).color(this.c).vertex(this.N[1], this.O[1], this.color[1]).color(this.c).vertex(this.N[2], this.O[2], this.color[2]).color(this.c).vertex(this.N[2], this.O[2], this.color[2]).color(this.c).vertex(this.N[3], this.O[3], this.color[3]).color(this.c).vertex(this.N[0], this.O[0], this.color[0]).color(this.c).vertex(this.N[0], this.O[0], this.color[0]).color(this.c);
                    }
                    polygonConsumer = this;
                }
                polygonConsumer.vertexIndex = 0;
            }
            vertexConsumer = this;
        }
        return vertexConsumer;
    }

    @NotNull
    public PolygonConsumer C() {
        return this;
    }

    @NotNull
    public PolygonConsumer q() {
        return this;
    }

    @NotNull
    public PolygonConsumer S(float f, float f2) {
        return this;
    }

    @NotNull
    public PolygonConsumer R() {
        return this;
    }

    @NotNull
    public PolygonConsumer P() {
        return this;
    }

    @NotNull
    public PolygonConsumer b(float f, float f2, float f3) {
        return this;
    }

    @NotNull
    public PolygonConsumer k(float f) {
        return this;
    }

    @Override
    public VertexConsumer color(int n, int n2, int n3, int n4) {
        return this.C();
    }

    @Override
    public VertexConsumer color(int n) {
        return this.q();
    }

    @Override
    public VertexConsumer texture(float f, float f2) {
        return this.S(f, f2);
    }

    @Override
    public VertexConsumer overlay(int n, int n2) {
        return this.R();
    }

    @Override
    public VertexConsumer light(int n, int n2) {
        return this.P();
    }

    @Override
    public VertexConsumer normal(float f, float f2, float f3) {
        return this.b(f, f2, f3);
    }

    @Override
    public VertexConsumer lineWidth(float f) {
        return this.k(f);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = a ^ 0x5D08F8D50CC1L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u00fb)\u0096\\\u00d3!\u0002&".getBytes("ISO-8859-1"));
                b = PolygonConsumer.zkmDecodeUtf8(byArray3).intern();
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

