/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix4f
 *  org.joml.Quaternionfc
 */
package su.catlean.module.esp;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.world.EntityRemove;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.esp.ProjectilePredictLineType;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.GlowTexture;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderQueue;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.particle.ParticleManager;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.render.particle.WorldParticle;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.util.PingPredictor;
import su.catlean.zkm.ZkmKeys;

public final class ProjectilePredict
extends Module {
    @NotNull
    public static final ProjectilePredict INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final SettingGroup group$delegate;
    @NotNull
    private static final BooleanSetting pearls$delegate;
    @NotNull
    private static final BooleanSetting arrows$delegate;
    @NotNull
    private static final BooleanSetting tridents$delegate;
    @NotNull
    private static final BooleanSetting eggs$delegate;
    @NotNull
    private static final BooleanSetting windcharges$delegate;
    @NotNull
    private static final BooleanSetting snowBalls$delegate;
    @NotNull
    private static final BooleanSetting potions$delegate;
    @NotNull
    private static final BooleanSetting drawCircle$delegate;
    @NotNull
    private static final BooleanSetting items$delegate;
    @NotNull
    private static final BooleanSetting drawPlayer$delegate;
    @NotNull
    private static final BooleanSetting drawHit$delegate;
    @NotNull
    private static final ModeSetting lineType$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private ProjectilePredict() {
        super("projectile-predict", Categories.getEsp(), null, null);
    }

    private final SettingParent getGroup() {
        return (SettingParent)group$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getPearls() {
        return (Boolean)pearls$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getArrows() {
        return (Boolean)arrows$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getTridents() {
        return (Boolean)tridents$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getEggs() {
        return (Boolean)eggs$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getWindcharges() {
        return (Boolean)windcharges$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getSnowBalls() {
        return (Boolean)snowBalls$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getPotions() {
        return (Boolean)potions$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getDrawCircle() {
        return (Boolean)drawCircle$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getItems() {
        return (Boolean)items$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getDrawPlayer() {
        return (Boolean)drawPlayer$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getDrawHit() {
        return (Boolean)drawHit$delegate.getValue(this, delegatedProperties[11]);
    }

    private final ProjectilePredictLineType getLineType() {
        return (ProjectilePredictLineType)((Object)lineType$delegate.getValue(this, delegatedProperties[12]));
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        VertexFormat vertexFormat;
        int n;
        Object var34_13;
        BatchedBuffer batchedBuffer;
        Object object;
        long l;
        long l2 = l = a ^ 0xCA83C16AFECL;
        long l3 = l2 ^ 0x4A3DB901E257L;
        long l4 = l2 ^ 0x13202D71B66BL;
        int n2 = (int)(l4 >>> 56);
        long l5 = l4 << 8 >>> 8;
        render3DEvent.getStack().push();
        Iterable<Entity> iterable = Globals.getWorld().getEntities();
        Intrinsics.checkNotNullExpressionValue(iterable, "entitiesForRendering(...)");
        Object object2 = object = iterable;
        Object object3 = new ArrayList();
        Object object4 = object2.iterator();
        while (object4.hasNext()) {
            Entity entity;
            Entity entity2 = entity = object4.next();
            if (!((INSTANCE.getPearls() && entity2 instanceof EnderPearlEntity || INSTANCE.getArrows() && entity2 instanceof PersistentProjectileEntity && ((PersistentProjectileEntity)entity2).isPushedByFluids() || INSTANCE.getTridents() && entity2 instanceof TridentEntity && ((TridentEntity)entity2).isPushedByFluids() || INSTANCE.getWindcharges() && entity2 instanceof WindChargeEntity || INSTANCE.getEggs() && entity2 instanceof EggEntity || INSTANCE.getSnowBalls() && entity2 instanceof SnowballEntity || INSTANCE.getPotions() && entity2 instanceof PotionEntity || INSTANCE.getItems() && entity2 instanceof ItemEntity) && !entity2.isOnGround())) continue;
            object3.add(entity);
        }
        List list = (List)object3;
        if (this.getLineType() == ProjectilePredictLineType.DEFAULT) {
            batchedBuffer = null;
        } else {
            VertexFormat vertexFormat2 = VertexFormats.POSITION_TEXTURE_COLOR;
            Intrinsics.checkNotNullExpressionValue(vertexFormat2, "POSITION_TEX_COLOR");
            var34_13 = null;
            n = 4;
            boolean bl = false;
            int n3 = list.size() * 100;
            vertexFormat = vertexFormat2;
            BatchedBuffer batchedBuffer2 = new BatchedBuffer(vertexFormat, n3, bl, n, var34_13);
            batchedBuffer = batchedBuffer2;
        }
        object = batchedBuffer;
        Object object5 = list;
        object3 = object5.iterator();
        while (object3.hasNext()) {
            Object e = object3.next();
            object4 = (Entity)e;
            MatrixStack matrixStack = render3DEvent.getStack();
            Intrinsics.checkNotNull(object4);
            INSTANCE.simulateProjectile(matrixStack, (Entity)object4, (BatchedBuffer)object, (byte)n2, l5);
        }
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        object5 = gpuTextureView;
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        object2 = camera;
        render3DEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-((Camera)object2).getYaw() + 180.0f));
        render3DEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-((Camera)object2).getPitch()));
        if (this.getLineType() == ProjectilePredictLineType.DOT) {
            Object object6 = object;
            if (object6 != null) {
                RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend();
                Framebuffer framebuffer = Globals.getMc().getFramebuffer();
                Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
                var34_13 = null;
                n = 20;
                Map<String, Object> map = MapsKt.mapOf(TuplesKt.to("Sampler0", object5));
                Matrix3x2f matrix3x2f = null;
                vertexFormat = render3DEvent.getStack().peek().getPositionMatrix();
                GpuBufferSlice gpuBufferSlice = null;
                Framebuffer framebuffer2 = framebuffer;
                RenderPipeline renderPipeline2 = renderPipeline;
                Object object7 = object6;
                BatchedBuffer.draw$default(l3, (BatchedBuffer)object7, renderPipeline2, framebuffer2, gpuBufferSlice, (Matrix4f)vertexFormat, matrix3x2f, map, n, var34_13);
            }
        }
        render3DEvent.getStack().pop();
    }

    @Flow
    private final void onEntityRemove(EntityRemove entityRemove) {
        block4: {
            int n;
            boolean bl;
            block5: {
                block3: {
                    bl = PingPredictor.Y();
                    n = this.getDrawHit();
                    if (!bl) break block3;
                    if (n == 0) break block4;
                    n = entityRemove.getEntity() instanceof EnderPearlEntity;
                }
                if (!bl) break block5;
                if (n == 0) break block4;
                n = 25;
            }
            int n2 = n;
            int n3 = 0;
            while (n3 < n2) {
                Entity entity;
                int n4 = n3++;
                Intrinsics.checkNotNull(entityRemove.getEntity());
                ParticleManager.INSTANCE.spawn(new WorldParticle(ParticleTexture.FIREFLY, INSTANCE.getPos(entity), 0.0f, 0.25f, 12, new Vec3d(Math.random() / 2.0 - 0.25, Math.random() / 4.0, Math.random() / 2.0 - 0.25), Theme.INSTANCE.getGradientColor(n4 * 10), 0.0f, 0.0f, false, 896, null));
                if (bl) continue;
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private final void simulateProjectile(MatrixStack var1_1, Entity var2_2, BatchedBuffer var3_3, byte var4_4, long var5_5) {
        v0 = var7_6 = ((long)var4_4 << 56 | var5_5 << 8 >>> 8) ^ ProjectilePredict.a;
        var23_7 = v0 ^ 90779596422897L;
        var29_8 = v0 ^ 76755819845942L;
        v1 = PingPredictor.Y();
        v2 = var2_2.getVelocity();
        Intrinsics.checkNotNullExpressionValue(v2, "getDeltaMovement(...)");
        var42_9 = v2;
        var43_10 = this.getPos(var2_2);
        var44_11 = 0;
        var41_13 = v1;
        for (var46_12 = 0; var46_12 < 300; ++var46_12) {
            block34: {
                block33: {
                    block24: {
                        block30: {
                            block32: {
                                block31: {
                                    block29: {
                                        block27: {
                                            block28: {
                                                block26: {
                                                    block25: {
                                                        block23: {
                                                            block22: {
                                                                block21: {
                                                                    block20: {
                                                                        block19: {
                                                                            var45_20 = var43_10;
                                                                            v3 = Intrinsics.areEqual(Globals.getWorld().getBlockState(BlockPos.ofFloored(var43_10)).getBlock(), Blocks.WATER);
                                                                            if (!var41_13) break block19;
                                                                            if (!v3) break block20;
                                                                            v3 = var2_2 instanceof TridentEntity;
                                                                        }
                                                                        if (!var41_13) ** GOTO lbl24
                                                                        if (v3) {
                                                                            v4 = 0.99;
                                                                        } else {
                                                                            v3 = var2_2 instanceof EnderPearlEntity;
lbl24:
                                                                            // 2 sources

                                                                            v4 = v3 ? 0.8 : 0.6;
                                                                        }
                                                                        break block21;
                                                                    }
                                                                    v4 = 0.99;
                                                                }
                                                                var47_21 = v4;
                                                                v5 = var42_9.add(0.0, -var2_2.getFinalGravity(), 0.0);
                                                                Intrinsics.checkNotNullExpressionValue(v5, "add(...)");
                                                                var42_9 = v5;
                                                                v6 = var42_9.multiply(var47_21);
                                                                Intrinsics.checkNotNullExpressionValue(v6, "scale(...)");
                                                                var42_9 = v6;
                                                                Intrinsics.checkNotNullExpressionValue(var43_10.add(var42_9), "add(...)");
                                                                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().raycast(new RaycastContext(var45_20, var43_10, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer())), "clip(...)");
                                                                v7 = var49_22.getType();
                                                                v8 = HitResult.Type.BLOCK;
                                                                if (!var41_13) break block22;
                                                                if (v7 == v8) break block23;
                                                                v7 = var49_22.getType();
                                                                v8 = HitResult.Type.ENTITY;
                                                            }
                                                            if (v7 != v8) break block24;
                                                        }
                                                        v9 = this;
                                                        if (!var41_13) break block25;
                                                        if (v9.getLineType() != ProjectilePredictLineType.DOT) break block26;
                                                        v9 = this;
                                                    }
                                                    v10 = v9.getDrawCircle();
                                                    if (!var41_13) break block27;
                                                    if (!v10) break block28;
                                                    v10 = var2_2.isOnGround();
                                                    if (!var41_13) break block27;
                                                    if (v10) break block28;
                                                    var50_23 = RenderState.INSTANCE;
                                                    var51_25 = GlowTexture.getIdentifier();
                                                    Intrinsics.checkNotNullExpressionValue(var49_22.getPos().add(0.0, 0.01, 0.0), "add(...)");
                                                    var53_28 = Theme.INSTANCE.getGradientColor(0);
                                                    var54_29 = ParticleTexture.CIRCLE.o();
                                                    var55_30 = ParticleTexture.CIRCLE.L();
                                                    var56_31 = ParticleTexture.CIRCLE.v();
                                                    var57_32 = ParticleTexture.CIRCLE.w();
                                                    RenderState.drawBillboard$default(var50_23, var51_25, var52_27, 0.0f, 90.0f, 0.0f, 1.0f, var29_8, var53_28, false, null, var54_29, var56_31, var55_30, var57_32, false, 8576, null);
                                                    if (var41_13) break block28;
                                                }
                                                v11 = var49_22.getPos().add(0.0, 0.01, 0.0);
                                                Intrinsics.checkNotNullExpressionValue(v11, "add(...)");
                                                RenderState.addLine$default(RenderState.INSTANCE, var45_20, v11, Theme.INSTANCE.getGradientColor(var46_12 * 10), null, false, 24, null);
                                            }
                                            v10 = this.getDrawPlayer();
                                        }
                                        if (!var41_13) break block29;
                                        if (!v10) break block30;
                                        v12 = var2_2;
                                        if (!var41_13) break block31;
                                        v10 = v12 instanceof EnderPearlEntity;
                                    }
                                    if (!v10) break block30;
                                    v12 = ((EnderPearlEntity)var2_2).getOwner();
                                }
                                if (!var41_13) break block32;
                                if (v12 == null) break block30;
                                v12 = ((EnderPearlEntity)var2_2).getOwner();
                            }
                            if (v12 instanceof PlayerEntity) {
                                Intrinsics.checkNotNullExpressionValue(var49_22.getPos(), "getLocation(...)");
                                v13 = ((EnderPearlEntity)var2_2).getOwner();
                                Intrinsics.checkNotNull(v13);
                                var38_17 = ((float)var2_2.age + RenderState.INSTANCE.getTickDelta()) / (float)(var2_2.age + var44_11);
                                var39_18 = (PlayerEntity)v13;
                                this.renderLanding(var1_1, var23_7, var40_19, var39_18, var38_17);
                            }
                        }
                        if (var41_13) break;
                    }
                    v14 = (cfr_temp_0 = var43_10.y - -65.0) == 0.0 ? 0 : (cfr_temp_0 < 0.0 ? -1 : 1);
                    if (var41_13) {
                        if (v14 <= 0) break;
                        cfr_temp_1 = var2_2.getVelocity().x - 0.0;
                        v14 = cfr_temp_1 == 0.0 ? 0 : (cfr_temp_1 < 0.0 ? -1 : 1);
                    }
                    if (var41_13) {
                        v14 = v14 == false ? (double)true : (double)false;
                    }
                    if (v14 != false) {
                        cfr_temp_2 = var2_2.getVelocity().y - 0.0;
                        v15 = cfr_temp_2 == 0.0 ? 0 : (cfr_temp_2 < 0.0 ? -1 : 1);
                        if (var41_13) {
                            v15 = v15 == false ? (double)true : (double)false;
                        }
                        if (v15 != false) {
                            cfr_temp_3 = var2_2.getVelocity().z - 0.0;
                            v16 = cfr_temp_3 == 0.0 ? 0 : (cfr_temp_3 < 0.0 ? -1 : 1);
                            if (var41_13) {
                                v16 = v16 == false ? (double)true : (double)false;
                            }
                            if (v16 != false) continue;
                        }
                    }
                    ++var44_11;
                    var50_24 = ((Number)RangesKt.coerceIn((Comparable)Float.valueOf((float)var46_12 / 8.0f), RangesKt.rangeTo(0.0f, 1.0f))).floatValue();
                    var51_26 = ((Number)RangesKt.coerceIn((Comparable)Float.valueOf((float)(var46_12 + 1) / 8.0f), RangesKt.rangeTo(0.0f, 1.0f))).floatValue();
                    if (!var41_13) break block33;
                    if (this.getLineType() != ProjectilePredictLineType.DOT) break block34;
                    RenderState.drawBillboard$default(RenderState.INSTANCE, GlowTexture.getIdentifier(), var43_10, 0.0f, 0.0f, 0.0f, 0.3f, var29_8, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(var46_12 * 10), var50_24), false, var3_3, ParticleTexture.FIREFLY.o(), ParticleTexture.FIREFLY.v(), ParticleTexture.FIREFLY.L(), ParticleTexture.FIREFLY.w(), false, 8320, null);
                }
                if (var41_13) continue;
            }
            var58_33 = null;
            var59_34 = 16;
            var62_35 = false;
            var63_36 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(var46_12 * 10), var51_26);
            var64_14 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(var46_12 * 10), var50_24);
            var65_15 = var43_10;
            var66_16 = var45_20;
            RenderState.addLine$default(RenderState.INSTANCE, var66_16, var65_15, var64_14, var63_36, var62_35, var59_34, var58_33);
            if (var41_13) continue;
        }
    }

    private final void renderLanding(MatrixStack matrixStack, long l, Vec3d vec3d, PlayerEntity playerEntity, float f) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x116A79D381L;
        long l4 = l2 ^ 0xB8DD1E8AACDL;
        long l5 = l2 ^ 0x21568C873F13L;
        boolean bl = PingPredictor.Y();
        if (bl) {
            if (Globals.getPlayer().squaredDistanceTo(vec3d) < 1.0) {
                return;
            }
            RenderState.Q(RenderState.INSTANCE, matrixStack, vec3d, playerEntity, 0.45f, false, null, l5);
        }
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl2 = false;
        int n2 = 6;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl2, n, defaultConstructorMarker);
        float f2 = 1.8f;
        float f3 = f * f2;
        Box box = new Box(vec3d.x - (double)0.6f, vec3d.y + (double)f3, vec3d.z - (double)0.6f, vec3d.x + (double)0.6f, vec3d.y + (double)1.8f + 0.5, vec3d.z + (double)0.6f);
        Color color = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color, "BLACK");
        Color color2 = Color.BLACK;
        Intrinsics.checkNotNullExpressionValue(color2, "BLACK");
        RenderQueue.INSTANCE.drawFilledBox(batchedBuffer, box, color, color2);
        BatchedBuffer.draw$default(l4, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColorAltBlendDepth(), null, null, matrixStack.peek().getPositionMatrix(), null, null, 54, null);
        RenderState.INSTANCE.drawPlayerHead(matrixStack, vec3d, playerEntity, l3, 1.0f, true);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getPearls();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getPearls();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ProjectilePredict.a = ZkmKeys.a(-6278799848982746941L, -927332521761449636L, MethodHandles.lookup().lookupClass()).a(266574597023335L);
                        var20 = ProjectilePredict.a ^ 40830073068044L;
                        ProjectilePredict.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[55];
                        var16_4 = 0;
                        var15_5 = "+%])~\u001f\u00cd\fmX\u0081\u0003q\u00e6^\u00ab:d\u00e9H\u0017RUu\u009a\u001b\u0086\u00e1\u00a6@\u0019\u008fJ\u00ae\u00df |\u00e9\u00d0\u00e8\u0010\u00d7q\u00ed\u0082\u00025aZp\u00b5\u00f2\u00c4\u00c6\n\u00c9S \u00d2\u0011%F<Z&\u00d6\u00a5\u00b1\u009f\u00d4t\u00e1\u00fd\u00d2sa\u00fb]\u00eb\u00cai}\u0007R?OF\u00b6\f\u00d2 \u00a9\u008c\u00dd\u009f4X\u00cd\u001b\u000f\u00e8S\u009e\u00eb}\u00cd|\u001bn\u00af\u00b6P \u00a2\u00f5\u00a6\u00bf\u0080QG\u00de\u00f5\u0011 M\t5\u00d1\u00a9\u001d\u00da9\u00a5d5u$]\u009b\u00e8\u00d0\u00cc0\u00e9\u008aY\u0007\u0019\u00a7\u00a4!J\u00f4\u00db\u00adF \t\u00c6\u00ee3k\u00e3\u00b7?\u009f<\u00ad2\u00a1\u00e5\u00aaA \u00b2\u0002\u009c).\u00cbg=:'\u00c7\u000f\u0092\u00c4\u0099\u0010\u00fc\u00af\u00cd\u00ff\u00a4!s\u00b0\u00d6)\u00bff\u00c8\u00b6\u00a0\u00d8 \u00d7\u00e6B\u00b9I\u00e3%\u00a0\u00dcb\u0093-\u00a2mRp\u009e\u001f\u00df\u008cuj@\u0007x\u00a3\u00a9+\u00b4\u00ad\u0099\u00a28R\u00e5T\te\u0090A\u00a6{\u00112\u0015s\u00bc\u00e7\u00a8\u00ad\u0013\u00d6\u00d6\u001e\u00bc\u00e5\u00d8\u009a\u00ea\u00be\u0000E\"\u00fba\u00c1\u00efA?\u00d5\u00da\u00a9\u0086\u00bf3y\u00a8\u00fc]\u00fbU>\u0018\u00ef\u001e\u00ae%\u00c5?\u0018\u0002]\\\u0080g\u0083\u00ccR1\u00bd\u00a5\u00b8\u00cd\u0083\u00bf\u00e5\u00d0\u0097\u00f5\u00e9\u00e5\u0085j_\u0010:\u00c1\u00a4hW\u0096K\u00fd_\rP\u0089;&\u008d[\u0010\u009e\u008b\u00e6\u00fa\u00f3\u008b\u00ceB\u008d\u00e5\u00ba\u00bc\u00ae$\u00acF\u0018\u008a\u001drF\u009fe/\u00c0\u0098\u00db\u00a9Q\u0010C\u00cdaa\u00ff\u0092\b\u00b5\u00b4\u001b\u0006 \u0001\u00c0 \u00c9\t!\u0093\u009e\u00f3\u00ae\u00eb\u0095\u0005\u00d5\u000e,\u008e\u00c2\u00bb\u00b8\u0005\u009eV\u00dd\u00d8-9\u00bc\u009e}0\u0092\u0010\u00c3\u001c\u00e0\u00c6=7\u001dLl\\\u00ff\u00d1\u00ea\u0081sb\u0018\u008b8\u00d5~\u0082D\u00f0\u001b\u00b3\u00ae\u00d0D\fh!\u00b7\u00ad\u0096\u00c6\f\u0004\r\u00c2\u00c7\u0018uE\u00f8\u00e7\u00edE\u0085\u0014\u0019\u0006\u0010\u00fd\u00ae\u00ed\u0091\u00fa\u00c8\u00f7i\u000f\u001e\u000e\u0092\u00a3\u0010\u00afW\u008630\u008c}58\u00c9\u00a8\u000b\u00b5\u0007W\u0090\u0018cA\u00a7\u00967\u00d9\u00c2\u000b\u00d52U\u0084\u0093\u0088\u008aW\u00afA\u00d9\u0080iexs(\u001fT\u001f\u00b8\u00b0\u00ff\u00f3US!ov|But\u00c9\u00b8G\u00de\u0017K,\u00e8\u00a0.\u00f9\u00de\u00fa\u00d9_o\u00ef\u00f0N\u00cc\u00b8\u00d6\u00a2\u00f4(\u00e74$|\u0086@\u00ad\u008a;\u0083\u00e1G\u001cPz\u001cx=\u008f`\u00e9\u00d0V\u00bb\u00a5.z\u00fd\u0097{\u009c>\u00ee\u0007?;\u00d8\u0095\u00fb\u00fa8!\u00b8\u00d5\u00b2\u00e0\u00ca\u00d6\u00a4\u00b9K\u009f\u0084r\u00e2\u00ab\u00d5\u0085l\u00e77#\u00e6&\u00fe\u00cd\u00d7\u00d6D\u0093\u00edj\u00bb\u00d1\u00f87\u00d8\u00ac\u00a4\u0019\t\u000e\u00d9\u00c9z\u00ff\u00e9\u0097~9\u0096\u00ab\u00a2\u00b7\u0007\u001a\u0015\u0010\u0097\u0001\u00f3\u00e30\u00c0\u0080\"Gd\u00a9\u00ef\u00c8d1H \u0080\u0010\u00c7\u001c\u0018iO\u00eb\\\u00f9\u00a9\u00cfA\u00ae:\u00e9\u00af\u0010\u0097\u0087\u00a3\u00a6\u00f9\u001c\u0002\u00fe\u00f3\u00b8\u00b1\u0007\\# \u0012\u001f\u008bEA\u00e5%\u00aa\u00fa\u00fc2Z\u0086S\u008b\u00b5\u0012\u00adORZ\u009d4\u00d8\fQ}\u00a3\u00d1\u00a2\u0091\u00df\u0018\u0086\u00cc\u001e\u0095\u00cc;|_\u00845\u00af\u00a1`\u00e5)\u00eeB.\u00d42\u008d\u00c8CG(\u00c1,\u0005n\u0011\u00b3\nbr\u00cd\u0007\u009e\u0003\u00c7\u00ed\u00bflM0\u0086yT\u00b0[\u00d6\u00f7\u00c9\b\u00e6H':\u009c\u0004\u001f\u00e5\u00b7x:\u00ac\u0010-\u00fd\u0084\u00aa.\u00c2\u0007\u00fcg4Z'\u00c4\u0004\u00e9\u0096 \r2X\u00f8\u00bc\u009f)\u00c5\u0091YK\u00c58\rK0\u00f7yQ\u00c9\u000fm~\u000e\u0095\u00a4\u00db\u008a\\R\u0091\u00c5\u0010U)\u0019 9\u00e3A\u00f40\u00e2T\u00d0\u00fe\fb\b\u0018\u00eb\\G\u00ed\u00b6\u00ee\u00c0U\u00cb\u00fd\u000f\u00dd\u00a2\u0001\u00c7\u0018H\u00cc\u0013\u00aa\u001a\u00c1\u0088\u0000\u0018\u000b\u00dex\u0099-\u001d\u0000j\u0004)\u0099YQ\u00ads+M\u00a4n=\u00b7\u00a5\u00a3\u0095(\u0092\u00c3F\u00af^w\u008b\u00a4\u00d7\u00c8WS\u0087\u00cc\u00a0\u00ca\u0018\u00fe\u00a6\u00f2}\u00e8\u0000\u00ad\u00a5\u0094x\u0017\u009e\u00b4\u00d07Ln\u009a\u0019\u00ffi\u008d\u008a \u00cd\u00d2\u000e z-PV\u00e2\u00bf[\u00f2\u00de\u0080\u00ff\u00fc<\u00a5\u00a0t\u0010.f\u0080\u00f09Y\u00b1\u00eb\u00b83f\u00a0\u0007\u0007\u00d3\u00c8\u00d8Y2D\u00e8\u0003\u001b/7\u00a9\u0013\u001cWc\u00a2{\u00c1\u00eb\u00f4\u00bd\u00bf\u00a2g\u0006\u0004\u00ba\u0085>\u00d4\u00a3\u00c1\u00e4\u00d6\u0085\u00a8\u00fd~\u00eaqx6\u0083\u00fd\u0014\u00ee\u00b1@m\u001e\u00ea\u00f1\u0007X\u00b8bMi\u00adq\u00e6\u00c6\u0015\u00db@\u00c0\u0098\u0095_[\u001aQW5\u00f6\u00ee\\f\u00c3Ufd4u\u0088\u00bd-g~\u009c\u00a1\u001b\u00f2\u0095SD\u00d9\u00c68\u00b3\u00a3m\u0017\u0003\u00f7\u0006\u00eaP\u0098\u0019\fQ\u00e3m\u00f5\u0000!\u00bf\u00a1\u00d1\u00d6l\u0012\u00d6\u00e1G%\u00fe\u00c2\u0014\u0088\u00e5\u00fb*\u0013$\u00a9\u0095\u00f8\u009e\u00ba)\u0095`\u000e\u000b\u0088\u001c\u001fAwq\u00df\u00a0\u0096\u009c\u00fe\u0010m\u00a2k\u00faV2,p\u008dy#SFN\u00b3#(\u001d\u00b8\u0005F\u008d\t\u00f8\u00adCA\u00a1\u00e3\u009e\u00fb\u00f7\u0014\u00e4\u00ac\u00c00i\u00cd\u00db\u0082\u00f1.\u0084>\u0086\u0090\u00a1\u00bc.\u00d6f\u000ekRnh(\u00a4\u00d8\u00d7I\u00b6\u00017\u00df\u001c\r\u00b1im\u00ea\t\u00e9\u009c\u00f7n`6\u00dd\u00c8\u0089=\u00e0O\u00c2\u008d|\u00d8\u00d6\u00ab\u00e079\u00a1\u00057\u00f1(\u00f4z5\u0081$\u00f0F\u00bc\u00bbq\u00a8\u00cakHf\u0010\u0091\t\u00feCN\u00d0#;\u0001\u00a8\u0002*\u00aaX\u0083\u00b5\u001f\u00c4\u00e4\u0093\u00f0_\u00bb\u0096\u0010\b\u00c0E+\u00fa\u0090j\u00c5\u00bb7\u0082\u008c\u00ffZ\"3\u0010\u00133\u00c6\u008c\u00c8k' b\u00a1sZ\u00f2\u00c8*n\u0010\u008f0\u00de\u00a8\u00bb\u001b\u0004%^h\u00a9h\u00e7P\u0087\u00bb \u0091\u00a9\u000e\u00b8\u00b6\u00df\u007f\u0000\u00cd%1\u0096>\u00f3e\u00d0$\u008e\u00c0\u00ae\u00d7rU\u0018'\u00d9*\u007fs\u00d9G\u00d1\u0018\u000f1\fC\u00cc\u00ea\u00e3\u00d8\u00c18\u009e\u00f9\u00a6\u00a8\u0089\t\u00a7A\u00c0\u00a9\u0087%\u001e\u00ed /\u00d9Q\u0004\u008f\u0099\u000e\u00c7\u0001\u00a1\u00f8\u0015\u00a2F\u008a\u00cb\u00b20\u00c3\u00e8V\u00c7\u00e4\u00c8\u00f0\u0018\u00ee\u00f4Vj\u00a5\u00d6\u0018\t\u0001@\u00a2\u00dd\u0099D\u0002)\u00a6[\u00e8!44\u00af\u009f\u00856V\u00ea\u00eag\u0081 \u008fz\u0089w\u0084\u00d0\u008aW\u00b1\f\u0087\u008d\u0093\u00f5P\u00f1\u00c0\u0010'\u00c5%\u00df:\u00df\u009at\u00c2\u0018(\u00f28\u00b38\u00eaK+\u00fb\u0003\u0017p\u00e0yB,bXlw\u00dah\u00cc\u0088\u0098\u00cd\u00e7?\u00a7\u00bd\u0087\u00d0l\u00b6\u0092\u00a9\u0082\u0088\u0002H\u00b8w\u0014\u00c9~\u008e\u00afV\u00a2C\u00a0\u00daH\u00f0\u00c8w\u00c5\u0014\u001eh\u00b4 \u00bcq\u00c6\u00fc<\u00dfMja\u00e2\u00b9\u00eeI\b\u00fd\u00d4Jp\u00b5\u0016\u00c7\u00d5\u00f8`BX\u001f\u00e0B\u0084\u00af\u00b3 \u00c7\u0012\u00a2\u00bd\u0097\\hRA\u00ff\u0080\u0018\u00ba\u00cb,\u00ef\u00f5\u00cb\u0018\u00a5\u00b1;\u00df\u00fa^\u00f4\u00a2\u00fc\u00b0\u0002\u0080\u00ce(zxG\u00f2\u00f8nN\u00efx\u00b2\u00ad\u00e4\u0091\u00f8P\u00f7\u0094\u00a7\u00f1\u00df\u00ef\u00dc\u0016_g\u0086\u00a8\u0001\u00a9s'\u0098b\u0096W\u00f3\u00f9\u00ccu\u0012\u0018\u00c8\u00d4r\u00c2Ba<\u0006\u0092)2\u00a2\u0010\u00d2\u00c5\u0003S\u00f19\u0015\u00d7cV^ ,\u0092\u00a6\u001bY\u00f3!\u00e7,(\u00a4\u00ee5\u0014\u001a\u0099>\u008c\u0017\u0014\u00fb\u00c7\u00b2B\u001776\f\u00fb\u00e89\u00c1";
                        var17_6 = 1724;
                        var14_7 = 40;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ProjectilePredict.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "5\t\u00c5B\\\u008dr\u007f\u0090\u0002C\u00fe\u00c8'\u0081\u001e k\u0080pq\u00f4\u00aa\u000b\u00b3\u00d4\u00e5\u009arcu<\"\u00ac\u008dX`\u0016x0\u00f6\u00b7\u00a4\u001er\\\u00d9\u0085\u0088";
                            var17_6 = 49;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
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
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ProjectilePredict.zkmDecodeUtf8(var19_9).intern();
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
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                ProjectilePredict.b = var18_3;
                ProjectilePredict.c = new String[55];
                ProjectilePredict.h = new HashMap<K, V>(13);
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
                var6_12 = new long[26];
                var3_13 = 0;
                var4_14 = "XU$C\u00a9\u00cf\u00b5\u00ac\u0002\u00a7\u000b\u008fe\u00d2\u0091\u0007\u00a2\u009b9\u00a0\u00ad\u001f\u00b3V}\u00d8\u008d\u0087\u00c3\u00a2\u00a1\u000f\u008a\u00f9\u00cdW\u00d3Ue\u00a6\f%\u0012l\u009a\u0012\u0001D\u00a7K\u00ef\u00fah\u00b0\b/\u0095e\u0019\u0017\u00dd\u000f\u00d7\u0010\u000bp6\u008f\tu\u009d\u00b0K\u008byK\u0003\u0081\u0094\u0015\u00d4l\u00fb\u00a4\u00ac\u00ed3\u0097\u00eeV-\u008e5\u00ff\u00001{\u00d6\u00b8\u009b\u001f\u00c0\u00e9\u00e4y\u0095\u008e\u00c3\u00fc1\u00d3\u00073\u00a5\u0082\u00979T\u00b6\u0092\u00f1h+\u00cbZd\u0085f\u00bb\u0014\u009d\n\u0085@\u0098n\u00cc\u00a9\u008f\u00e4\u00ea\u00ba\u00cfA\u000f\u00f9(\u0010\u00da\u00f3J}\u00d1@\u00c0\u00e5k/\u0089\u0082\u009c\u00db\u00bf\u0018\u008f\u00fb\u00d7\u0082\b\u00a4g\u00cd\u00adpp\u0093\u00cf\u00f3\u0014\u009d\u0001\u00dc\u00f4\u00c9eW\u008eI\u00de\u00e8\u00dd\f";
                var5_15 = 192;
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
                    var4_14 = "yM\u00f3\u00a4\u00a7\u00e3\u0002}\u0016\u00ab\u00c3\u00aa*\u0081\u001f]";
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
        ProjectilePredict.f = var6_12;
        ProjectilePredict.g = new Integer[26];
        var33_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "group", "getGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "pearls", "getPearls()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "arrows", "getArrows()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "tridents", "getTridents()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "eggs", "getEggs()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "windcharges", "getWindcharges()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "snowBalls", "getSnowBalls()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "potions", "getPotions()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "drawCircle", "getDrawCircle()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "items", "getItems()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "drawPlayer", "getDrawPlayer()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "drawHit", "getDrawHit()Z", 0)), Reflection.property1(new PropertyReference1Impl(ProjectilePredict.class, "lineType", "getLineType()Lsu/catlean/client/features/modules/visual/esp/pearlpredict/ProjectilePredict$LineType;", 0))};
        ProjectilePredict.delegatedProperties = var33_20;
        ProjectilePredict.INSTANCE = new ProjectilePredict();
        ProjectilePredict.group$delegate = SettingFactory.settingGroup$default(ProjectilePredict.INSTANCE, "selection", AuraTargetsMode.BOOLS, null, 4, null);
        ProjectilePredict.pearls$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "pearls", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.arrows$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "arrows", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.tridents$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "tridents", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.eggs$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "eggs", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.windcharges$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "windcharges", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.snowBalls$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "snowballs", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.potions$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "potions", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.drawCircle$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "draw-circle", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.items$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "items", true, ProjectilePredict.INSTANCE.getGroup(), null, 8, null);
        ProjectilePredict.drawPlayer$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "draw-player", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        ProjectilePredict.drawHit$delegate = SettingFactory.booleanSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "draw-hit", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        ProjectilePredict.lineType$delegate = SettingFactory.modeSetting$default((SettingContainer)ProjectilePredict.INSTANCE, "line-type", (Enum)ProjectilePredictLineType.DOT, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x34D1;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ka", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = b[n2].getBytes("ISO-8859-1");
            ProjectilePredict.c[n2] = ProjectilePredict.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProjectilePredict.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ka" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3F28;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ka", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ProjectilePredict.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ProjectilePredict.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ka" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProjectilePredict.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ProjectilePredict.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

