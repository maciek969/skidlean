/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.cosmetic.BodyPart;
import su.catlean.module.cosmetic.Dismemberment;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class FlyingBodyPart {
    @NotNull
    private Identifier texture;
    @NotNull
    private PlayerEntityModel model;
    @NotNull
    private BodyPart part;
    private double x;
    private double y;
    private double z;
    private double motionX;
    private double motionY;
    private double motionZ;
    private double spinX;
    private double spinY;
    private double spinZ;
    private double posY;
    private double prevSpinY;
    private double prevSpinZ;
    private double v;
    private double Y;
    private double O;
    private int B;
    private double prevX;
    private double prevY;
    private double prevZ;
    private static final long b;
    private static final String[] d;
    private static final String[] f;
    private static final Map h;
    private static final long[] i;
    private static final Integer[] j;
    private static final Map l;

    public FlyingBodyPart(@NotNull Identifier identifier, @NotNull PlayerEntityModel playerEntityModel, @NotNull BodyPart bodyPart, double d, double d2, double d3) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ b;
        Intrinsics.checkNotNullParameter(identifier, (String)((Object)FlyingBodyPart.zkmBootstrap("q", (int)31483, (long)(0x136BDD6932677B91L ^ l))));
        Intrinsics.checkNotNullParameter(playerEntityModel, (String)((Object)FlyingBodyPart.zkmBootstrap("q", (int)5912, (long)(0x41EDC81358EE167EL ^ l))));
        Intrinsics.checkNotNullParameter((Object)bodyPart, (String)((Object)FlyingBodyPart.zkmBootstrap("q", (int)18647, (long)(0x99EAF7BA66249B5L ^ l))));
        this.texture = identifier;
        this.model = playerEntityModel;
        this.part = bodyPart;
        this.x = d;
        this.y = d2;
        this.z = d3;
        this.motionX = this.randomVelocity();
        this.motionY = this.randomVelocity();
        this.motionZ = this.randomVelocity();
        this.spinX = this.randomSpin() * 5.0;
        this.spinY = this.randomSpin() * 5.0;
        this.spinZ = this.randomSpin() * 5.0;
        this.posY = this.spinX;
        this.prevSpinY = this.spinY;
        this.prevSpinZ = this.spinZ;
        this.v = this.randomSpin();
        this.Y = this.randomSpin();
        this.O = this.randomSpin();
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
    }

    @NotNull
    public final Identifier getTexture() {
        return this.texture;
    }

    public final void setTexture(@NotNull Identifier identifier) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(identifier, (String)((Object)FlyingBodyPart.zkmBootstrap("q", (int)12916, (long)(0x6058FF7CC57F33D6L ^ l))));
        this.texture = identifier;
    }

    @NotNull
    public final PlayerEntityModel getModel() {
        return this.model;
    }

    public final void setModel(@NotNull PlayerEntityModel playerEntityModel) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(playerEntityModel, (String)((Object)FlyingBodyPart.zkmBootstrap("q", (int)12916, (long)(0x605889C3D9FFB04CL ^ l))));
        this.model = playerEntityModel;
    }

    @NotNull
    public final BodyPart getPart() {
        return this.part;
    }

    public final void setPart(@NotNull BodyPart bodyPart) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ b;
        Intrinsics.checkNotNullParameter((Object)bodyPart, (String)((Object)FlyingBodyPart.zkmBootstrap("q", (int)3944, (long)(0x5A59320DC638E642L ^ l))));
        this.part = bodyPart;
    }

    public final double getX() {
        return this.x;
    }

    public final void setX(double d) {
        this.x = d;
    }

    public final double getY() {
        return this.y;
    }

    public final void setY(double d) {
        this.y = d;
    }

    public final double getZ() {
        return this.z;
    }

    public final void setZ(double d) {
        this.z = d;
    }

    public final double randomVelocity() {
        return Math.random() * 0.3 - 0.15;
    }

    public final double randomSpin() {
        return Math.random() * 5.0 - 2.5;
    }

    public final double getMotionX() {
        return this.motionX;
    }

    public final void setMotionX(double d) {
        this.motionX = d;
    }

    public final double getMotionY() {
        return this.motionY;
    }

    public final void setMotionY(double d) {
        this.motionY = d;
    }

    public final double getMotionZ() {
        return this.motionZ;
    }

    public final void setMotionZ(double d) {
        this.motionZ = d;
    }

    public final double getSpinX() {
        return this.spinX;
    }

    public final void setSpinX(double d) {
        this.spinX = d;
    }

    public final double getSpinY() {
        return this.spinY;
    }

    public final void setSpinY(double d) {
        this.spinY = d;
    }

    public final double getSpinZ() {
        return this.spinZ;
    }

    public final void setSpinZ(double d) {
        this.spinZ = d;
    }

    public final double getPosY() {
        return this.posY;
    }

    public final void setPosY(double d) {
        this.posY = d;
    }

    public final double getPrevSpinY() {
        return this.prevSpinY;
    }

    public final void setPrevSpinY(double d) {
        this.prevSpinY = d;
    }

    public final double getPrevSpinZ() {
        return this.prevSpinZ;
    }

    public final void setPrevSpinZ(double d) {
        this.prevSpinZ = d;
    }

    public final double U() {
        return this.v;
    }

    public final void F(double d) {
        this.v = d;
    }

    public final double A() {
        return this.Y;
    }

    public final void W(double d) {
        this.Y = d;
    }

    public final double F() {
        return this.O;
    }

    public final void K(double d) {
        this.O = d;
    }

    public final int H() {
        return this.B;
    }

    public final void Y(int n) {
        this.B = n;
    }

    public final double getPrevX() {
        return this.prevX;
    }

    public final void setPrevX(double d) {
        this.prevX = d;
    }

    public final double getPrevY() {
        return this.prevY;
    }

    public final void setPrevY(double d) {
        this.prevY = d;
    }

    public final double getPrevZ() {
        return this.prevZ;
    }

    public final void setPrevZ(double d) {
        this.prevZ = d;
    }

    public final boolean update() {
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        String string = BodyPart.zkmGetFlag();
        this.prevSpinY = this.spinY;
        this.prevSpinZ = this.spinZ;
        this.posY = this.spinX;
        this.motionY -= 0.08;
        BlockPos blockPos = BlockPos.ofFloored(this.x, this.y - 0.3, this.z);
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        BlockState blockState = Globals.getWorld().getBlockState(blockPos2);
        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
        BlockState blockState2 = blockState;
        this.motionX *= 0.91;
        this.motionY *= 0.98;
        this.motionZ *= 0.91;
        this.v *= 0.91;
        this.Y *= 0.91;
        this.O *= 0.91;
        if (blockState2.getBlock() instanceof FluidBlock) {
            this.motionY = 0.31;
        } else if (!blockState2.isAir()) {
            this.motionY = 0.0;
            this.y = (double)blockPos2.getY() + 1.0;
            this.motionX *= 0.8;
            this.motionZ *= 0.8;
            this.spinX += (-90.0 - this.spinX % 360.0) / 2.0;
            this.v = 0.0;
            this.Y = 0.0;
            this.O = 0.0;
        }
        Iterable<Entity> iterable = Globals.getWorld().getEntities();
        Intrinsics.checkNotNullExpressionValue(iterable, "entitiesForRendering(...)");
        Object object = iterable;
        Iterable<Entity> iterable2 = object;
        Collection collection = new ArrayList();
        Iterator<Entity> iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            Entity entity;
            Entity entity2 = entity = iterator2.next();
            Intrinsics.checkNotNull(entity2);
            if (!(EntityUtil.getPos(entity2).squaredDistanceTo(this.x, this.y, this.z) < 0.1)) continue;
            collection.add(entity);
        }
        for (Object e : (List)collection) {
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            object = (Entity)e;
            double d = this.x - ((Entity)object).getX();
            double d2 = this.z - ((Entity)object).getZ();
            this.motionX = d * 0.5;
            this.motionZ = d2 * 0.5;
        }
        this.x += this.motionX;
        this.y += this.motionY;
        this.z += this.motionZ;
        this.spinX += this.v;
        this.spinY += this.Y;
        this.spinZ += this.O;
        int n = this.B;
        this.B = n + 1;
        return n > Dismemberment.INSTANCE.getLifeTime() * 20;
    }

    public final void render(@NotNull MatrixStack matrixStack, long l) {
        long l2 = l = b ^ l;
        long l3 = l2 ^ 0x51E29CC8D424L;
        long l4 = l2 ^ 0x7110B086AC69L;
        Intrinsics.checkNotNullParameter(matrixStack, "stack");
        String string = BodyPart.zkmGetFlag();
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        String string2 = string;
        boolean bl = this.motionY == 0.0;
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(this.texture).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        Color color = null;
        int n = 4;
        boolean bl2 = false;
        int n2 = 12;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl2, n, (DefaultConstructorMarker)((Object)color));
        double d = RenderUtil.INSTANCE.interpolate(this.prevX, this.x) - (double)((float)camera2.getCameraPos().x);
        double d2 = RenderUtil.INSTANCE.interpolate(this.prevY, this.y) + (bl ? this.part.c() : this.part.J()) - (double)((float)camera2.getCameraPos().y);
        double d3 = RenderUtil.INSTANCE.interpolate(this.prevZ, this.z) - (double)((float)camera2.getCameraPos().z);
        double d4 = RenderUtil.INSTANCE.interpolate(this.posY, this.spinX) + 180.0;
        double d5 = RenderUtil.INSTANCE.interpolate(this.prevSpinY, this.spinY);
        double d6 = RenderUtil.INSTANCE.interpolate(this.prevSpinZ, this.spinZ);
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
        float f = (float)RangesKt.coerceIn(Dismemberment.INSTANCE.getLifeTime() * 20 - this.B, 0, 3) / 3.0f;
        color = RenderUtil.INSTANCE.withAlpha(color2, f);
        BodyPart bodyPart = this.part;
        double d7 = d6;
        double d8 = d5;
        double d9 = d4;
        double d10 = d3;
        double d11 = d2;
        double d12 = d;
        BatchedBuffer batchedBuffer2 = batchedBuffer;
        PlayerEntityModel playerEntityModel = this.model;
        Dismemberment.INSTANCE.renderBodyPart(matrixStack, l4, playerEntityModel, batchedBuffer2, d12, d11, d10, d9, d8, d7, bodyPart, color);
        BatchedBuffer.draw$default(l3, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureCullDepth(), null, null, new MatrixStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2)), 22, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        FlyingBodyPart.b = ZkmKeys.a(5678269027890003128L, 8501764410288743392L, MethodHandles.lookup().lookupClass()).a(28708935639791L);
                        FlyingBodyPart.h = new HashMap<K, V>(13);
                        var11 = FlyingBodyPart.b ^ 116350064207330L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[15];
                        var18_4 = 0;
                        var17_5 = "\u00b3{\u001f\u00a8\u0003\u00aa2,\u001d\u0090\u00bb,sr\u00c7D\u009c\u000b\u0090&\u00bb\u00adHr\u00da\u00d1\u000f\u00d1\u00df\u000b\u0093\rS:}X\u00c0\u0098\u0089F\u0010\u00a2\u001b\u00d3\u0086\u00ba\u00d8\u00f8G\u0088\u00bc9\u00c8\u0092\u0016\u00b8\u00ec\u0010D\u00c2\u008e\u00da\u0095\u00e0\u00d8\u00f9\u00c4\u0019\u00f5\u00a8\u00b93\u00bdW\u0010\u0089\u00be!\u00f1\u0017\u00ab\u0015\u008f\u00ca\t\u00a4>\u001b\u0094\u0000d \u00d7\u00ee\u00cf\u00d3p\u00e07NM_m\u0019\u00a4\u00ab\u00aaPh\u00a8\u00b1\u00ab\u00ea\u000b\u00de\u00a2\u009c\u007f\u0091\u00b5\u008dR\u00de\u00bc\u0010)%i\u00ae%\u0002bf\u00b5\u0081\u00ea0\u00e2{\u008c\u0099('wyye\u00d5\u008b\u00c2\u00b0\"\u00b0lU\u00d8\u00d6\u00d3NJ\u00fcx\u000fV\u00b9\u00009Z\u00ee\u00f2\u00ea\u009f\u00fd\u00dc{\u00a6\u0081\u00b4}.,t \u009cO\u00d7\u00cf\u00f4}\u0088\u0000\u0006:E\u00d7p\u00d1C\u00e4\u00b3\u0018\u00bdX\u00a1\u00ac\u0083\u0015#$\u00d5DFS\u0011\u00c1(\u0091\u0019t\u00db\u00f2\u008b\u00b4%\u0000\u00fc\u00ee#\u00bav\u00b8\u008ay1\u001f\u0091u\u009dG\u00fe\u00faI\u00ce`\u00c0\u0085\u00af\u00af\u00b1\\|\u008f\u00de\u00b4Sn\u0010\u0092\u00f0\u009b\u00eb\u008b\u00c2\u0090Q\u008b\"Hi\u0019\u00f8c\u00ed\u0018\u00c8\u00ed\u00bd\u00db\u009e\u0001\u00d0\u00d7\u001f\u0099\u00c0\u00e9&\f\u00d4U\u00dcDT\u00af\u001b\u00b9s\u009d\u0010\u0011\u008b\u0005C\u00b9\u00f8\u00a9Y\u0083\u009e\u00bd\u00a4\b\u00d7N\u00a6(,\u00a1\u008c~U\u009aw\u00c4u\u0000\u00fb\u00df\u00a1%\u00e3\u00a2&\u00e5%-\u009f\u00db\u00a0\u00e9\u00b3\u00c5\u00e8f\u00c1\u0089\u0010@\u00cd#\u00f7c/\u0084n\u00c5";
                        var19_6 = 356;
                        var16_7 = 40;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = FlyingBodyPart.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "dc\u0085 #\u00c1D\u00b2\u00e2MW\u001dQ\u00f9\u0016\u00dd-\u001ba\u008b6\u00a9Y\u00ce\u00da/\u0080\u00c8\u00b3\u00e08p\u00ba\u00ea\u00f7xq\u00ceT\u0080\u00dc\u009f1u\u00c1DO\u00e2U\u00a3+\u008c\u00fc\u0090\u00bbk\u0010\u00df\u00e2\u0084&\u00d1\u00b4\u008dT\u00bd\u000b\u00d0\u00c7\u00f9\u0012\u0083\u0094";
                            var19_6 = 73;
                            var16_7 = 56;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = FlyingBodyPart.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                FlyingBodyPart.d = var20_3;
                FlyingBodyPart.f = new String[15];
                FlyingBodyPart.l = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "2\u00f2\u0097\u008d-\u00ef|:\u0094\u009e7\u00b1\u00cb\u008bN\u00a9";
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00be\u008a\u00f6\u0097\u00cb\u009c\u008c\u00d5\u00df\u00f3\u0019{P~\u00c2\u00fd";
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        FlyingBodyPart.i = var6_12;
        FlyingBodyPart.j = new Integer[4];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6999;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/j1", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            FlyingBodyPart.f[n2] = FlyingBodyPart.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FlyingBodyPart.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/j1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5CD;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])FlyingBodyPart.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    FlyingBodyPart.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/j1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FlyingBodyPart.j[n2] = n3;
        }
        return j[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FlyingBodyPart.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/j1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FlyingBodyPart.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FlyingBodyPart.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

