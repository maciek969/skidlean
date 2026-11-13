/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package su.catlean.module.cosmetic;

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
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.AmbientParticle;
import su.catlean.module.cosmetic.ParticleTrailPoint;
import su.catlean.module.cosmetic.Particles$WhenMappings;
import su.catlean.module.cosmetic.ParticlesMode;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.ColorMode;
import su.catlean.render.GlowTexture;
import su.catlean.render.MatrixUtil;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.particle.ParticleManager;
import su.catlean.render.particle.ParticleSprite;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.render.particle.WorldParticle;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;

public final class Particles
extends Module {
    @NotNull
    public static final Particles INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final ModeSetting lmode$delegate;
    @NotNull
    private static final ModeSetting physics$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    @NotNull
    private static final IntSetting amount$delegate;
    @NotNull
    private static final IntSetting lifetime$delegate;
    @NotNull
    private static final FloatSetting size$delegate;
    @NotNull
    private static final ArrayList particles;
    @NotNull
    private static final ArrayList trailPoints;
    private static int J;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map i;

    private Particles() {
        super("particles", Categories.getCosmetic(), null, null);
    }

    private final ParticleSprite getMode() {
        return (ParticleSprite)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final ColorMode getLmode() {
        return (ColorMode)((Object)lmode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final ParticlesMode getPhysics() {
        return (ParticlesMode)((Object)physics$delegate.getValue(this, delegatedProperties[2]));
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[3]);
    }

    private final int getAmount() {
        return ((Number)amount$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final int getLifetime() {
        return ((Number)lifetime$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final float getSize() {
        return ((Number)size$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        particles.removeIf(arg_0 -> Particles.invokePredicate(Particles::onPlayerUpdate$lambda$0, arg_0));
        trailPoints.removeIf(arg_0 -> Particles.invokePredicate2(Particles::onPlayerUpdate$lambda$2, arg_0));
        Entity entity = Globals.getPlayer();
        Vec3d vec3d = this.getPos(entity).add(Globals.getPlayer().getVelocity().multiply(10.0));
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        Vec3d vec3d2 = vec3d;
        int n = this.getAmount();
        for (int i = 0; i < n; ++i) {
            AmbientParticle ambientParticle;
            if (INSTANCE.getPhysics() == ParticlesMode.RAIN) {
                float f = INSTANCE.getSize();
                float f2 = RandomUtil.D(3.0f, 10.0f, false, null);
                float f3 = RandomUtil.D(0.0f, 360.0f, false, null);
                float f4 = 0.0f;
                float f5 = RandomUtil.D(-0.3f, -0.1f, false, null);
                float f6 = 0.0f;
                float f7 = (float)(vec3d2.z + (double)RandomUtil.D(-48.0f, 48.0f, false, null));
                float f8 = (float)(vec3d2.y + 24.0 + (double)RandomUtil.D(-2.0f, 2.0f, false, null));
                float f9 = (float)(vec3d2.x + (double)RandomUtil.D(-48.0f, 48.0f, false, null));
                ambientParticle = new AmbientParticle(f9, f8, f7, f6, f5, f4, f3, f2, f);
            } else {
                float f = (float)(vec3d2.x + (double)(RandomUtil.D(-64.0f, 64.0f, false, null) * 2.0f));
                float f10 = (float)(vec3d2.y + (double)RandomUtil.D(2.0f, 48.0f, false, null));
                float f11 = (float)(vec3d2.z + (double)(RandomUtil.D(-64.0f, 64.0f, false, null) * 2.0f));
                float f12 = switch (Particles$WhenMappings.G[INSTANCE.getPhysics().ordinal()]) {
                    case 1 -> 0.0f;
                    case 2 -> RandomUtil.D(-0.4f, 0.4f, false, null);
                    default -> RandomUtil.D(0.1f, 0.25f, false, null);
                };
                float f13 = switch (Particles$WhenMappings.G[INSTANCE.getPhysics().ordinal()]) {
                    case 1 -> RandomUtil.D(-0.2f, -0.05f, false, null);
                    case 2 -> RandomUtil.D(-0.1f, 0.1f, false, null);
                    default -> RandomUtil.D(-0.4f, -0.1f, false, null);
                };
                float f14 = switch (Particles$WhenMappings.G[INSTANCE.getPhysics().ordinal()]) {
                    case 1 -> 0.0f;
                    case 2 -> RandomUtil.D(-0.4f, 0.4f, false, null);
                    default -> RandomUtil.D(0.1f, 0.25f, false, null);
                };
                float f15 = INSTANCE.getSize();
                float f16 = 0.0f;
                float f17 = 0.0f;
                float f18 = f14;
                float f19 = f13;
                float f20 = f12;
                float f21 = f11;
                float f22 = f10;
                float f23 = f;
                ambientParticle = new AmbientParticle(f23, f22, f21, f20, f19, f18, f17, f16, f15);
            }
            AmbientParticle ambientParticle2 = ambientParticle;
            particles.add(ambientParticle2);
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = a ^ 0x19E93DFF7159L;
        long l2 = l ^ 0x54DF28856FA0L;
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        render3DEvent.getStack().push();
        MatrixStack matrixStack = render3DEvent.getStack();
        Quaternionf quaternionf = RotationAxis.POSITIVE_Y.rotation(-RenderState.INSTANCE.getCameraYaw());
        Intrinsics.checkNotNullExpressionValue(quaternionf, "rotation(...)");
        MatrixUtil.multiply(matrixStack, (Quaternionfc)quaternionf);
        MatrixStack matrixStack2 = render3DEvent.getStack();
        Quaternionf quaternionf2 = RotationAxis.POSITIVE_X.rotation(-RenderState.INSTANCE.getCameraPitch());
        Intrinsics.checkNotNullExpressionValue(quaternionf2, "rotation(...)");
        MatrixUtil.multiply(matrixStack2, (Quaternionfc)quaternionf2);
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl = false;
        int n2 = particles.size() + trailPoints.size();
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        MatrixStack matrixStack3 = new MatrixStack();
        Quaternionf quaternionf3 = RotationAxis.POSITIVE_X.rotation(RenderState.INSTANCE.getCameraPitch());
        Intrinsics.checkNotNullExpressionValue(quaternionf3, "rotation(...)");
        MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf3);
        Quaternionf quaternionf4 = RotationAxis.POSITIVE_Y.rotation(RenderState.INSTANCE.getCameraYaw());
        Intrinsics.checkNotNullExpressionValue(quaternionf4, "rotation(...)");
        MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf4);
        Quaternionf quaternionf5 = new Quaternionf().rotateYXZ(-RenderState.INSTANCE.getCameraYaw2(), RenderState.INSTANCE.getCameraPitch(), 0.0f);
        Iterable iterable = particles;
        for (Object object : iterable) {
            AmbientParticle ambientParticle = (AmbientParticle)object;
            Intrinsics.checkNotNull(quaternionf5);
            INSTANCE.renderParticle(ambientParticle, matrixStack3, (Quaternionfc)quaternionf5, batchedBuffer);
        }
        iterable = new Quaternionf().rotateYXZ(-RenderState.INSTANCE.getCameraYaw2(), 1.5708f, 0.0f);
        Iterable iterable2 = trailPoints;
        for (AmbientParticle ambientParticle : iterable2) {
            Matrix4f matrix4f;
            ParticleTrailPoint particleTrailPoint = (ParticleTrailPoint)((Object)ambientParticle);
            if (!RenderState.INSTANCE.getFrustum().intersectPoint(particleTrailPoint.getPosX(), particleTrailPoint.getPosY(), particleTrailPoint.getPosZ())) continue;
            matrixStack3.push();
            matrixStack3.translate((double)particleTrailPoint.getPosX() - camera2.getCameraPos().x, (double)particleTrailPoint.getPosY() - camera2.getCameraPos().y, (double)particleTrailPoint.getPosZ() - camera2.getCameraPos().z);
            Intrinsics.checkNotNull(iterable);
            MatrixUtil.multiply(matrixStack3, (Quaternionfc)iterable);
            Intrinsics.checkNotNullExpressionValue(matrixStack3.peek().getPositionMatrix(), "pose(...)");
            float f = RenderUtil.INSTANCE.interpolate(Math.max(1, particleTrailPoint.getAge() - 1), particleTrailPoint.getAge());
            float f2 = 1.0f - f / 15.0f;
            Color color = RenderUtil.INSTANCE.withAlpha(particleTrailPoint.getColor(), f2);
            float f3 = 0.5f + 1.0f * (f / 15.0f);
            ParticleTexture particleTexture = ParticleTexture.CIRCLE;
            matrixStack3.translate((double)(-f3) / 2.0, (double)(-f3) / 2.0, -0.01);
            batchedBuffer.vertex(matrix4f, 0.0f, f3, 0.0f).texture(particleTexture.L(), particleTexture.v()).color(color);
            batchedBuffer.vertex(matrix4f, f3, f3, 0.0f).texture(particleTexture.o(), particleTexture.v()).color(color);
            batchedBuffer.vertex(matrix4f, f3, 0.0f, 0.0f).texture(particleTexture.o(), particleTexture.w()).color(color);
            batchedBuffer.vertex(matrix4f, 0.0f, 0.0f, 0.0f).texture(particleTexture.L(), particleTexture.w()).color(color);
            matrixStack3.pop();
        }
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlendDepthtest(), null, null, render3DEvent.getStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView())), 22, null);
        render3DEvent.getStack().pop();
    }

    private final boolean updateParticle(AmbientParticle ambientParticle) {
        int n;
        if (Globals.getPlayer().squaredDistanceTo(ambientParticle.getPosX(), ambientParticle.getPosY(), ambientParticle.getPosZ()) > 4096.0 || ambientParticle.r()) {
            ambientParticle.setMaxAge(ambientParticle.getMaxAge() - 16);
        } else {
            n = ambientParticle.getMaxAge();
            ambientParticle.setMaxAge(n + -1);
        }
        if (ambientParticle.getMaxAge() < 0) {
            return true;
        }
        ambientParticle.setPrevPosX(ambientParticle.getPosX());
        ambientParticle.setPrevPosY(ambientParticle.getPosY());
        ambientParticle.setPrevPosZ(ambientParticle.getPosZ());
        ambientParticle.setPosX(ambientParticle.getPosX() + ambientParticle.getMotionX());
        ambientParticle.setPosY(ambientParticle.getPosY() + ambientParticle.getMotionY());
        ambientParticle.setPosZ(ambientParticle.getPosZ() + ambientParticle.getMotionZ());
        ambientParticle.setPrevRot(ambientParticle.getRot());
        ambientParticle.setRot(ambientParticle.getRot() + ambientParticle.getRotSpeed());
        if (this.getPhysics() != ParticlesMode.FALLING_STARS) {
            ambientParticle.setMotionX(ambientParticle.getMotionX() * 0.9f);
            ambientParticle.setMotionZ(ambientParticle.getMotionZ() * 0.9f);
        }
        if (this.getPhysics() == ParticlesMode.FLY || this.getPhysics() == ParticlesMode.RAIN && ambientParticle.r()) {
            ambientParticle.setMotionY(ambientParticle.getMotionY() * 0.9f);
        }
        ambientParticle.setMotionY(ambientParticle.getMotionY() - (this.getPhysics() == ParticlesMode.RAIN && ambientParticle.r() ? 0.08f : (this.getPhysics() == ParticlesMode.RAIN ? 0.02f : 0.001f)));
        if (ambientParticle.getPosY() - this.getSize() < ambientParticle.getGroundY() && !ambientParticle.r() && this.getPhysics() == ParticlesMode.RAIN) {
            ambientParticle.setMotionY(-ambientParticle.getMotionY() / 4.0f);
            ambientParticle.K(true);
            ambientParticle.setMaxAge(128);
            ambientParticle.setMotionX(RandomUtil.D(-0.2f, 0.2f, false, null));
            ambientParticle.setMotionZ(RandomUtil.D(-0.2f, 0.2f, false, null));
            n = 5;
            int n2 = 0;
            while (n2 < n) {
                int n3 = n2++;
                ParticleManager.INSTANCE.spawn(new WorldParticle(ParticleTexture.FIREFLY, new Vec3d(ambientParticle.getPosX(), ambientParticle.getGroundY(), ambientParticle.getPosZ()), 0.0f, 0.1f, 8, new Vec3d(Math.random() / 2.0 - 0.25, Math.random() / 4.0, Math.random() / 2.0 - 0.25), Theme.INSTANCE.getGradientColor(n3 * 10), 0.0f, 0.0f, false, 896, null));
            }
            ambientParticle.setRotSpeed(RandomUtil.p(RangesKt.rangeTo(-20.0f, 20.0f), false, null));
            trailPoints.add(new ParticleTrailPoint(ambientParticle.getPosX(), ambientParticle.getGroundY(), ambientParticle.getPosZ(), 0, ambientParticle.getColor()));
        }
        ambientParticle.k(Math.hypot((double)ambientParticle.getPosX() - Globals.getPlayer().getX(), (double)ambientParticle.getPosZ() - Globals.getPlayer().getZ()) < (double)(this.getPhysics() == ParticlesMode.RAIN ? 1024.0f : 2304.0f));
        return false;
    }

    private final void renderParticle(AmbientParticle ambientParticle, MatrixStack matrixStack, Quaternionfc quaternionfc, BatchedBuffer batchedBuffer) {
        if (!ambientParticle.t() || !RenderState.INSTANCE.getFrustum().intersectPoint(ambientParticle.getPosX(), ambientParticle.getPosY(), ambientParticle.getPosZ())) {
            return;
        }
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        double d = RenderUtil.INSTANCE.interpolate(ambientParticle.getPrevPosX(), ambientParticle.getPosX());
        double d2 = RenderUtil.INSTANCE.interpolate(ambientParticle.getPrevPosY(), ambientParticle.getPosY());
        double d3 = RenderUtil.INSTANCE.interpolate(ambientParticle.getPrevPosZ(), ambientParticle.getPosZ());
        matrixStack.push();
        matrixStack.translate(d - camera2.getCameraPos().x, d2 - camera2.getCameraPos().y, d3 - camera2.getCameraPos().z);
        MatrixUtil.multiply(matrixStack, quaternionfc);
        if (this.getPhysics() == ParticlesMode.RAIN) {
            Quaternionf quaternionf = RotationAxis.POSITIVE_Z.rotationDegrees(RenderUtil.INSTANCE.interpolate(ambientParticle.getPrevRot(), ambientParticle.getRot()));
            Intrinsics.checkNotNullExpressionValue(quaternionf, "rotationDegrees(...)");
            MatrixUtil.multiply(matrixStack, (Quaternionfc)quaternionf);
        }
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
        Matrix4f matrix4f2 = matrix4f;
        float f = (float)ambientParticle.getMaxAge() / (float)ambientParticle.getAge();
        Color color = this.getPhysics() != ParticlesMode.RAIN || ambientParticle.r() ? RenderUtil.INSTANCE.withAlpha(ambientParticle.getColor(), f) : ambientParticle.getColor();
        matrixStack.translate((double)(-this.getSize()) / 2.0, (double)(-this.getSize()) / 2.0, -0.01);
        ParticleTexture particleTexture = this.getMode().getParticleTexture();
        batchedBuffer.vertex(matrix4f2, 0.0f, this.getSize(), 0.0f).texture(particleTexture.L(), particleTexture.v()).color(color);
        batchedBuffer.vertex(matrix4f2, this.getSize(), this.getSize(), 0.0f).texture(particleTexture.o(), particleTexture.v()).color(color);
        batchedBuffer.vertex(matrix4f2, this.getSize(), 0.0f, 0.0f).texture(particleTexture.o(), particleTexture.w()).color(color);
        batchedBuffer.vertex(matrix4f2, 0.0f, 0.0f, 0.0f).texture(particleTexture.L(), particleTexture.w()).color(color);
        matrixStack.pop();
        if (!ambientParticle.r() && this.getPhysics() == ParticlesMode.RAIN) {
            float f2 = RangesKt.coerceIn((float)ambientParticle.getMaxAge() / 20.0f, 0.0f, 1.0f);
            float f3 = f2 / 3.0f;
            RenderState.INSTANCE.addLine(new Vec3d(d, d2, d3), new Vec3d(d, d2 + 3.0, d3), RenderUtil.INSTANCE.withAlpha(color, f3), RenderUtil.INSTANCE.withAlpha(color, 0.0f));
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getLmode() == ColorMode.CUSTOM;
    }

    private static final boolean onPlayerUpdate$lambda$0(AmbientParticle ambientParticle) {
        Intrinsics.checkNotNullParameter(ambientParticle, "it");
        return INSTANCE.updateParticle(ambientParticle);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean onPlayerUpdate$lambda$2(ParticleTrailPoint particleTrailPoint) {
        Intrinsics.checkNotNullParameter(particleTrailPoint, "it");
        int n = particleTrailPoint.getAge();
        particleTrailPoint.setAge(n + 1);
        return n > 15;
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    public static final ParticlesMode access$getPhysics(Particles particles) {
        return particles.getPhysics();
    }

    public static final int access$getLifetime(Particles particles) {
        return particles.getLifetime();
    }

    public static final ColorMode access$getLmode(Particles particles) {
        return particles.getLmode();
    }

    public static final Color access$getColor(Particles particles) {
        return particles.getColor();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Particles.a = ZkmKeys.a(4915798448623905153L, -6097011542707585148L, MethodHandles.lookup().lookupClass()).a(104992542069783L);
                        var20 = Particles.a ^ 61579862930044L;
                        Particles.d = new HashMap<K, V>(13);
                        Particles.zkmSetFlag(0);
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
                        var18_3 = new String[35];
                        var16_4 = 0;
                        var15_5 = "\u0090\u00da\u00fa\u0010\u008f\u0002&\u00fd\u00a2\u00f5\u001b\u00b1\u0094c\u00b2\u00e98\u00db\u00fby\u00b7\u00c1\u001e\u00eb_$\u00b3\u00a2m\u0081\u00ees\u008d+\u00c0\u00aaW0\u0093\u00d4R\u00e6E\u007f4\u0088\u00a9\u00c9\u00c9\u00aa\u00c2\u00e0r^s\u00c7]S\u009f}\u00c5q\u00f0\u00bf\u0005-\u0017\"\u00e6c\u00f2\u00d7\u00ff \u0012P\u00e4\u00f0\u00a0\u00b0\u0016\u00c8\u009c\u00f8p\u00a9C\u0018:\u00ea%\u00c5\"\u00e9\u00dea\u00f5\u00be1\u00fao\u00d7K\u0082U\u00e0(\rPh\u001b\u0007\u00fa)\u0095<\u0002\u00cdo9\u00e0:\u00a0\u008d\u00ea\u00ea\u008e\u001a\u00b7\u00c5\u00d2{\u00dc\u00c2\u00dc\u00bf\u0012yl.\u00c9\u00b4_\u00f1|\u00e7\u0010 g\u00b22\u00d0\u00c1 \u00da\u0087*\u0014\u00c8j\"\u00b5j\u00ca=\u00e0\u00da\u0099~\u009d3z\u001d\f\u0004x\u00da\u0012\u0093\u00b4\u0010\u009a2\u00ee\u00f2\t\u0095,\fH9-\u000bbw$\u0082 d\u00b0g<\u0082:I\u00c0\u00d1<q\u001eP\u00f1a\u00a2\u00de#?b\u00bb\u00b9Qe\u001b\u00baq\u0002\u000b\u00ab\u00d6X\u0010\u00ba\u00d8\u0018\u000e\u00bfm\u001a\u00a8\u00ea\u00a00\u0005\u0098!%\u008e z\u00d1~rb\u00aef\u00e1\u00ae\u00a1_\u0006$W\u0083\u009e4#\u008c\u0012*m:\u008a\u0018\u000ez\u00eaO)\fy8_\u0086!\u0096\u0099\u001b\np\u0011\u00eb?\u00fe\u00a1&3Dy<\u001d\u001di4\u00ab\u0084\u0003\u0007\u00e1\u0083\u00ab\u000b\u00df\u0005\u008d\u00c9V8\u00dd\u00f3\u009e\u0000k\u00bb\u00d2MTL,\t\u00a3\u00a7 \u00aeOC\u00c6m\u0010\u00b25a' \u00d4\u0006\n\u00ff\u00ec\u0005\u001e-\u00f8k\u009a\u0010;\u00c7\u00ffg?\f\u00d2\u00e6\u00b0\u00ea\u00e3[\u0007\u009f\u00af\u001d\u0010g\u0096Ph\u00c6'^d\u00c9(\u001c\u00f2\u00d2T.\u00de\u0018!:\u0090\u009f\u0099C\u0007\u00a8\u0096\u00de\u0082\u0001\u00a6\u009e\u00a4^-{\u00dd\u00f3x\u00d0X\u00af \u00c7\u00f9:\u0003e_\u008e\u0096\u00e3\u00b6=F\u00ca\u00cf\n\u009a\u00da\u00fd\u00e9L\u00c4\u0082>}}\u00bd\u00a7R\u00fb\u00de )8\u00d3\u00c2\u00b8\u00ce\u0095\u00e9\u00cf\u00d6\u00a3\u00f1A\u0087\u00a1A\t\u008d\u00d3\u00ea\u00b1\u0093L\u0084\u00cf4^l\u00ce\u0094\u0014j\u009a\u00d5\u00e2bluE=\u0002\u00eb\u0011\u00a5\u0011\u0010\u00bd\u0081e%\u00bd\u00a2\u00fbp\u0089\u001et\u009c \u00fbr:wy\u00df\u00b1@B?\u00d2Rh\u00d5\u00e8\\\u008f\u0018g\u00b9\u00e5=H\u00ec\u00f5\u007f\u0098n{\u008dB\u00be \u008a\u0091\u00c2#\u00f4K9)<\u00df\u0094\u001d\u0010\u00aff\u00df\u00057\u00b1\u009d\u0087\u0015\u0018g\u00abaq\u0099\u00b5\u00e0d!(\u00f6\rDP\u0001\u00c0\u00ee\u009a@\u00f7\u00ab\u0011pE/\u00db{\u00a4\u0084\u000f\u008e\u00fcI(\u001d\u00bd>%\u00b6\u0082#N\u00d8\u00d8\u00d6\u00c3-\u0012\u0086;(\u009b\u00f4\u00864\u00bc\u00a0\u00a4`\u00c9\u00b3'\u001e#P\u0095\u00bcB\u00ad\u00d2\u0085\u00f1=c.)\u00ea$\u0002\u008f\u0096\u00ffTO1a\u00dc\u0096\n/)\u0010s\u00b8sR\u001f\u00fc\u0006\u00ed\u00b1=\u0017\u0091c\u00a4\u0085\u008d(]\u00ae7\u00c2.|2f\r5\u009c\u009d\u00aa6C:\u00d7n\u00cd\u0015f{\u0000\u007f\u00e3\u00a2\u00f7\u00a6\u009f\u0092o9\u00c7-T&\u0003\u009b\u0010\u0015\u0018g\u00a5\u00b0\u0004\u009c\u0001U|x\u00f5\u00a9\u00b9\u00bem\u00fa9\tO\u0013\u008ay\u00b2[s m\u00ba\b-\u00d0\u00a7\u0097\u00de\u00b8\u0018>N\u0007\u00a4\u00ac`I\u0093^\u001c\u001a\u00e8\u00c6\u0091\u00b6S\u009e\u00e7\u0003\u00c9)\u009b\u0010vGR|+\u009f\u00ee\u00ff0\n\u001f\u00b7g\u009b\u0017\b\u0010e\u001e\u00e1\u001c_JPf\u008a_\u00a3\u0019\u00da$\u00ca\u00d6\u0010u\u00fd?6MNP\u0097V\u00b2 3N^D\u0081\u0010[BfR\u00a6\u00fb&\u009b<Q\u00a7\u0005m\u00867\u0013\u0090\u0096\u00ed\u00e7\u0018\u00c6:\u0087]\u008b\u00a3\u001d\u00a1P\u00ec$\u00e5\u00ad\u00a9\u00fd\u001b.'?\u0086T\u00f4\u009en<\u00f8\u00ff\u00faKW\u0004\u00d3\u000f\u00d6?\u00d8\u00e0\u0013r\u00da*\u00b9Cee\u0092\u009b\u00ccp\u00a9M\u0091\u00fb\u00b13\u001c\\\u00ac\u00dadJs\u0095\u0011\u0099\f\u00c1DP\u00e2\u00ed\u007fDe^op{\u0006\u00b2S\u00d6\u00d7\u00efX \u00c7D\u00ee\u0010ny\u00f6\u00c537\u00f4>i=>:*g\u0085N\u0084\u00f9\u0090\u008fU\u00c8T\u00bcd\u0094\u00c3cQ.\u00e5+^\u00af\u00c5_\u001c\u0019\u0088}\u00a6I\u009c\u00a5Re\u0014\u00f8\u000b\u00bd\u0010\u0092\u0082\u00ac\u00bcFy&\u000b\u00c2J\u00a21j&\f$\u0018N\u0005\u00f7\u00b1\u0093\u00fb$\u00aeQ\u00f81\u009cf#Q\u00eej\u0017\u00b2\u0091\u000f`\u000fB\u0010\u00f0\u0018\u00f5#\u00e1{\u00ff\u00a0\u00b9~C\u00e6\u00e7\u00f4\u00fa\u001a\u0010\u00a0+\u00bc\u00d5\u00b0\u00a5\u00d8BL,j\u009bh.\u009c\u001f";
                        var17_6 = 1056;
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
                            var18_3[var16_4++] = Particles.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00d0QD\u0081\u001d\u000e[\u00dc\u001c\u00fc\u00a5\u0018\u009b\u0084\u0006q\u00c3\u0088C\u00d1\u0091U\u00e6\u00de\u0010P\\\u00a2\u00ee\u00d3w\u00be\u00c3lj-p\u00fbE\\[";
                            var17_6 = 41;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = Particles.zkmDecodeUtf8(var19_9).intern();
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
                Particles.b = var18_3;
                Particles.c = new String[35];
                Particles.i = new HashMap<K, V>(13);
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
                var6_12 = new long[18];
                var3_13 = 0;
                var4_14 = "z\u008e\u00deC\u008eEO\u00e8\u00a38\u0090\u00ee\u00ba\u00f5\u00d6\u0082Un\u0086\u00b8\u00b5{Q\u00dcQ\u00ab\u0013\u00d4\u00ff\n\u00d8\u0099\u001d\u00f1\u0012N\u00c9eA\u0019\u00ab\u00e8\u00e5\u0084\u00a6<\u00b5[1\u0017t\box\u0085!C\u00c1\u00b7\u0006\u0083=\u00e7=\u00bc\u0096B<\u0016\u00fa\u00e8D\u000f&k\u007f\u00ed\u0011<\u00b1$\u00dd\u008f\u009e\u00b9uK\u0080\u00fe\u0001v=\u00c6\u001671\u009e\u00b6\u00d8\u001e: \u00d6\b\u00d7\u00f5\u00c9\u0094\\c\u0099\u00a1\u00a0\u0088\u0096\u00da\u0015\u0089Dh\u00abLu\u00ddsO\u00fb0";
                var5_15 = 128;
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
                    var4_14 = "\u000f\u00f6v\u0014\u0014%4*%\u00d8\u001d/M\u008bLc";
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
        Particles.f = var6_12;
        Particles.g = new Integer[18];
        var34_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Particles.class, "mode", "getMode()Lsu/catlean/fo;", 0)), Reflection.property1(new PropertyReference1Impl(Particles.class, "lmode", "getLmode()Lsu/catlean/client/features/modules/visual/cosmetic/particles/Particles$ColorMode;", 0)), Reflection.property1(new PropertyReference1Impl(Particles.class, "physics", "getPhysics()Lsu/catlean/j;", 0)), Reflection.property1(new PropertyReference1Impl(Particles.class, "color", "getColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(Particles.class, "amount", "getAmount()I", 0)), Reflection.property1(new PropertyReference1Impl(Particles.class, "lifetime", "getLifetime()I", 0)), Reflection.property1(new PropertyReference1Impl(Particles.class, "size", "getSize()F", 0))};
        Particles.delegatedProperties = var34_20;
        Particles.INSTANCE = new Particles();
        Particles.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Particles.INSTANCE, "mode", (Enum)ParticleSprite.SNOW_FLAKE, null, null, 12, null);
        Particles.lmode$delegate = SettingFactory.modeSetting$default((SettingContainer)Particles.INSTANCE, "color-mode", (Enum)ColorMode.SYNC, null, null, 12, null);
        Particles.physics$delegate = SettingFactory.modeSetting$default((SettingContainer)Particles.INSTANCE, "physics", (Enum)ParticlesMode.FLY, null, null, 12, null);
        v15 = Particles.INSTANCE;
        v16 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v16, "WHITE");
        Particles.color$delegate = SettingFactory.colorSetting$default(v15, "color", v16, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Particles.amount$delegate = SettingFactory.intSetting$default(Particles.INSTANCE, "amount", 10, new IntRange(1, 50), null, null, 24, null);
        Particles.lifetime$delegate = SettingFactory.intSetting$default(Particles.INSTANCE, "time", 256, new IntRange(5, 80), null, null, 24, null);
        Particles.size$delegate = SettingFactory.floatSetting$default(Particles.INSTANCE, "size", 1.0f, RangesKt.rangeTo(0.1f, 1.5f), null, null, 24, null);
        Particles.particles = new ArrayList<E>();
        Particles.trailPoints = new ArrayList<E>();
    }

    public static void zkmSetFlag(int n) {
        J = n;
    }

    public static int int_zkmGetFlag() {
        return J;
    }

    public static int zkmIntConstant() {
        return 17;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6305;
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
                throw new RuntimeException("su/catlean/kc", exception);
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
            Particles.c[n2] = Particles.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Particles.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x33AF;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Particles.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Particles.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Particles.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Particles.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

