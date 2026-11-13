/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package su.catlean.render.particle;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import net.minecraft.client.render.Camera;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.MatrixUtil;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;

public final class WorldParticle {
    @NotNull
    private ParticleTexture tex;
    @NotNull
    private Vec3d pos;
    private float rotationSpeed;
    private float scale;
    private int lifetime;
    @NotNull
    private Vec3d velocity;
    @NotNull
    private Color color;
    private float gravity;
    private float drag;
    private boolean useAgeFactor;
    private final int prevLifetime;
    @NotNull
    private Vec3d prevPos;
    private float w;
    private float W;
    private int a;
    private static String E;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map k;

    public WorldParticle(@NotNull ParticleTexture particleTexture, @NotNull Vec3d vec3d, float f, float f2, int n, @NotNull Vec3d vec3d2, @NotNull Color color, float f3, float f4, boolean bl) {
        Intrinsics.checkNotNullParameter((Object)particleTexture, "tex");
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Intrinsics.checkNotNullParameter(vec3d2, "velocity");
        Intrinsics.checkNotNullParameter(color, "color");
        this.tex = particleTexture;
        this.pos = vec3d;
        this.rotationSpeed = f;
        this.scale = f2;
        this.lifetime = n;
        this.velocity = vec3d2;
        this.color = color;
        this.gravity = f3;
        this.drag = f4;
        this.useAgeFactor = bl;
        this.prevLifetime = this.lifetime;
        this.prevPos = this.pos;
        this.a = RandomUtil.f(new IntRange(0, 3), false, null);
    }

    public WorldParticle(ParticleTexture particleTexture, Vec3d vec3d, float f, float f2, int n, Vec3d vec3d2, Color color, float f3, float f4, boolean bl, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 0x40) != 0) {
            Color color2 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(color2, "BLACK");
            color = color2;
        }
        if ((n2 & 0x80) != 0) {
            f3 = 0.03f;
        }
        if ((n2 & 0x100) != 0) {
            f4 = 0.99f;
        }
        if ((n2 & 0x200) != 0) {
            bl = true;
        }
        this(particleTexture, vec3d, f, f2, n, vec3d2, color, f3, f4, bl);
    }

    @NotNull
    public final ParticleTexture getTex() {
        return this.tex;
    }

    public final void setTex(@NotNull ParticleTexture particleTexture) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter((Object)particleTexture, (String)((Object)WorldParticle.zkmBootstrap("v", (int)24, (long)(0x5419A3D786B43D90L ^ l))));
        this.tex = particleTexture;
    }

    @NotNull
    public final Vec3d getPos() {
        return this.pos;
    }

    public final void setPos(@NotNull Vec3d vec3d) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)WorldParticle.zkmBootstrap("v", (int)31859, (long)(0x3A4966A7875F04B1L ^ l))));
        this.pos = vec3d;
    }

    public final float getScale() {
        return this.scale;
    }

    public final void setScale(float f) {
        this.scale = f;
    }

    @NotNull
    public final Vec3d getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(@NotNull Vec3d vec3d) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)WorldParticle.zkmBootstrap("v", (int)31859, (long)(0x3A492650ECD3A274L ^ l))));
        this.velocity = vec3d;
    }

    @NotNull
    public final Color getColor() {
        return this.color;
    }

    public final void setColor(@NotNull Color color) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(color, (String)((Object)WorldParticle.zkmBootstrap("v", (int)31859, (long)(0x3A49588F55E46F01L ^ l))));
        this.color = color;
    }

    public final boolean tick() {
        int n = this.lifetime;
        Object var1_2 = null;
        this.lifetime = n + -1;
        if (this.lifetime < 0) {
            return true;
        }
        this.prevPos = this.pos;
        Vec3d vec3d = this.pos.add(this.velocity);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        this.pos = vec3d;
        Vec3d vec3d2 = this.velocity.multiply(this.drag);
        Intrinsics.checkNotNullExpressionValue(vec3d2, "scale(...)");
        this.velocity = vec3d2;
        Vec3d vec3d3 = this.velocity.add(0.0, -((double)this.gravity), 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d3, "add(...)");
        this.velocity = vec3d3;
        this.W = this.w;
        this.w += this.rotationSpeed;
        return false;
    }

    public final void render(@NotNull MatrixStack matrixStack, @NotNull Quaternionfc quaternionfc, @NotNull BatchedBuffer batchedBuffer) {
        Intrinsics.checkNotNullParameter(matrixStack, "matrices");
        Intrinsics.checkNotNullParameter(quaternionfc, "base");
        Object var26_4 = null;
        Intrinsics.checkNotNullParameter(batchedBuffer, "polygon");
        matrixStack.push();
        float f = RenderUtil.INSTANCE.interpolate((float)this.lifetime + 1.0f, this.lifetime);
        float f2 = this.useAgeFactor ? f / (float)this.prevLifetime : 1.0f;
        float f3 = this.scale * f2;
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        Color color = Intrinsics.areEqual(this.color, Color.BLACK) ? Theme.INSTANCE.getGradientColor(this.lifetime * 2) : this.color;
        Vec3d vec3d = RenderState.lerpVec$default(RenderState.INSTANCE, this.prevPos, this.pos, 0.0f, null).subtract(camera2.getCameraPos());
        Intrinsics.checkNotNullExpressionValue(vec3d, "subtract(...)");
        Vec3d vec3d2 = vec3d;
        matrixStack.translate(vec3d2.x, vec3d2.y, vec3d2.z);
        MatrixUtil.multiply(matrixStack, quaternionfc);
        if (!(this.w == 0.0f) && !(this.W == 0.0f)) {
            Quaternionf quaternionf = RotationAxis.POSITIVE_Z.rotationDegrees(RenderUtil.INSTANCE.interpolate(this.W, this.w));
            Intrinsics.checkNotNullExpressionValue(quaternionf, "rotationDegrees(...)");
            MatrixUtil.multiply(matrixStack, (Quaternionfc)quaternionf);
        }
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
        Matrix4f matrix4f2 = matrix4f;
        Color color2 = !this.useAgeFactor ? color : RenderUtil.INSTANCE.withAlpha(color, f2);
        float f4 = this.tex.o();
        float f5 = this.tex.L();
        float f6 = this.tex.v();
        float f7 = this.tex.w();
        matrixStack.translate((double)(-f3) / 2.0, (double)(-f3) / 2.0, -0.01);
        batchedBuffer.vertex(matrix4f2, 0.0f, f3, 0.0f).texture(f5, f6).color(color2);
        batchedBuffer.vertex(matrix4f2, f3, f3, 0.0f).texture(f4, f6).color(color2);
        batchedBuffer.vertex(matrix4f2, f3, 0.0f, 0.0f).texture(f4, f7).color(color2);
        batchedBuffer.vertex(matrix4f2, 0.0f, 0.0f, 0.0f).texture(f5, f7).color(color2);
        matrixStack.pop();
    }

    public static void zkmSetFlag(String string) {
        E = string;
    }

    public static String zkmGetFlag() {
        return E;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        WorldParticle.b = ZkmKeys.a(270678252329196635L, -7554518609380639848L, MethodHandles.lookup().lookupClass()).a(280052282091776L);
                        var20 = WorldParticle.b ^ 53591139172290L;
                        WorldParticle.e = new HashMap<K, V>(13);
                        WorldParticle.zkmSetFlag("ryXZi");
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[17];
                        var16_4 = 0;
                        var15_5 = "\u00e7\u00d9\u0093\u00d0a\u001d7mp\u00d3o\u00caf\u00e3(I\u0018\u00c3\u00d4\u00c3\u00a7V\u0006\u00e3\u00b5\u0087U\u00df\u00fe\u00c4\u0004\u0018\u0004~fU\u00bbq\u00e4y\u0012(\u0092\u00ac\u009b\u0090\u00c9W\u00a0\u008d\u00f2R\u00e6\u00de\u00ad\u00c73)\u00d5\u00c4\u00ab\u00eb\u0002\u00f3>g\u00fe%\u0019\u0094\u00c5\u009b\u0091xa+t:kT\u00d5{\u00100\n,\u00dbf\u00cc)\u0084\u00a8\u0086\u0006\u0090Fv\u00c9\u007f\u0010\u00e5\u00b9[\u0000J\u0090\u00f6\u0003`\u0083{\u0092\u0094\u0017\u00cb\u00a4\u0010b\u00bc|t\u00c6\u00ab\u000b\n\u00aer\u00f5\u000e\u00d3\u00cf\u00dbU \u00d5\u00f3\u008bQ\u00de\u0095\u0004\u0018\u009089\u0087\u00a6i\u00db\u00ac\u00d9\u00e4\u00d5\u00f4:\u00aaA\u00fee\"\u00bcI7A1\u00cf\u0010t\u00fbV\u00f0y\u00b5\u00c1\u00f9e\u00b7\u00cdN\u0096\u00c9\u0083\u00db \u00ec\\A\u00fb\u00d0\u00df\u00b0!\u00f5\u0093\u00e7JWn\u0010m\u0003'\u00ef\u0083\u00d3l%y\u00e7\u00b8\u00ca\u00da\u00cb\u0089Dz(\u00dd\n\u0011\u00ef\u0081k\u00ea\u0015\u009e\u00b0\u00ea\u00fc\u00a9\u00a7\u00f3\u0097\u000f9\r\u00d8\u001d\u00e71y\u0097\u008e!x\u000fb\u00d0\u00ee\u00ff,i\u0092\u00d5\u00c5\u00c1\u0089 \u0006{\u0000\u00cd\u00a0p\u00dd\u00a8\u0092n\u00bb9\u00ef\n]\u0099\u00bc[\u00d3c\u00b4\u00cbx\u00b9H\u0017\u0090\u00e0\u00c3\fEJ [)\u00cc\u00c2\u009e\u00b4o\u001e2\u00e9\u00d2Y\u00f9\u00a1\u00af\u0011\r=\u0094\u00ee\u00d9\u00d1\u0006\u0083\u00bbw\u00d68\u008d\u00db\u00e7\u00f1\u0010\u0093\u0014\u000bL[F\u0001\u00bf\u00fd\u00ca+`'|&\u00bd\u0010\u001cy\u00eeX\u00a5\u008f\u00d7y\u00e9@1D\u00d6Uc\u001a \u00c4k\u009e\u00957\u001d\u00b3(\u0004'\u00a5\n3\u009a\u00f9\u009fl\f\u00c8\u00df\u0091}u\u00a9\u0099\u00f0\u00029\u00e3\u009d\u00b5o";
                        var17_6 = 390;
                        var14_7 = 16;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = WorldParticle.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "@\u009c\u00ed\u0097k\u00bdC\u0015T\u00d1c\u00a9d1\u00bcB V\u00d8\n\u00ab\u00fa\u00ae\u00862\u00b2;\u00adso\u00d2\u00dd;>\u00f4/\u001aJZ\u00f6\u00ab\u0003\u00d5\u00ea\u00b1`\u00da\u0090\u00ef";
                            var17_6 = 49;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = WorldParticle.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                WorldParticle.c = var18_3;
                WorldParticle.d = new String[17];
                WorldParticle.k = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "`_\u00edG\u00ca\u0096\u0004\u00b5\u00c2\u0001\u00eb\n<\u00c5\u00eap";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u0002\u00d0\u00a2\u00b97\u00dd\u00c3\u0007\u0012\u00a9\u00ee\u0093\u00cd8&^";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl92:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl105:
                // 1 sources

                ** continue;
            }
        }
        WorldParticle.f = var6_12;
        WorldParticle.g = new Integer[4];
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

    private static String zkmDecryptString(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x76BA;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/bk", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            WorldParticle.d[n2] = WorldParticle.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WorldParticle.zkmDecryptString(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/bk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5BC8;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/bk", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            WorldParticle.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = WorldParticle.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/bk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WorldParticle.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(WorldParticle.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

