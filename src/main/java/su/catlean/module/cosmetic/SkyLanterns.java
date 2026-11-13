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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
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
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.SkyLantern;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.MatrixUtil;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.particle.ParticleManager;
import su.catlean.render.particle.ParticleSprite;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.render.particle.WorldParticle;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;

public final class SkyLanterns
extends Module {
    @NotNull
    public static final SkyLanterns INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ColorSetting fireColor$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    @NotNull
    private static final ColorSetting colorBottom$delegate;
    @NotNull
    private static final IntSetting count$delegate;
    @NotNull
    private static final Color lanternColor;
    @NotNull
    private static final List lanterns;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private SkyLanterns() {
        super("sky-lanterns", Categories.getCosmetic(), null, null);
    }

    private final Color getFireColor() {
        return (Color)fireColor$delegate.getValue(this, delegatedProperties[0]);
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[1]);
    }

    private final Color getColorBottom() {
        return (Color)colorBottom$delegate.getValue(this, delegatedProperties[2]);
    }

    private final int getCount() {
        return ((Number)count$delegate.getValue(this, delegatedProperties[3])).intValue();
    }

    @Override
    public void onEnable(long l) {
        lanterns.clear();
    }

    @Override
    public void onDisable(long l) {
        lanterns.clear();
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        if (!this.isInGame() || Globals.getMc().isPaused()) {
            return;
        }
        while (lanterns.size() < this.getCount()) {
            this.spawnLantern();
        }
        Iterator iterator2 = lanterns.iterator();
        while (iterator2.hasNext()) {
            SkyLantern skyLantern = (SkyLantern)iterator2.next();
            skyLantern.setPrevX(skyLantern.getX());
            skyLantern.setPrevY(skyLantern.getY());
            skyLantern.setPrevZ(skyLantern.getZ());
            skyLantern.setMotionX(skyLantern.getMotionX() + RandomUtil.p(RangesKt.rangeTo(-0.00125f, 0.00125f), false, null));
            skyLantern.setMotionY(skyLantern.getMotionY() + RandomUtil.p(RangesKt.rangeTo(2.0E-4f, 6.0E-4f), false, null));
            skyLantern.setMotionZ(skyLantern.getMotionZ() + RandomUtil.p(RangesKt.rangeTo(-0.00125f, 0.00125f), false, null));
            skyLantern.setMotionX(skyLantern.getMotionX() * 0.99f);
            skyLantern.setMotionY(skyLantern.getMotionY() * 0.99f);
            skyLantern.setMotionZ(skyLantern.getMotionZ() * 0.99f);
            skyLantern.setX(skyLantern.getX() + skyLantern.getMotionX());
            skyLantern.setY(skyLantern.getY() + skyLantern.getMotionY());
            skyLantern.setZ(skyLantern.getZ() + skyLantern.getMotionZ());
            skyLantern.setRotation(skyLantern.getRotation() + skyLantern.getRotationSpeed());
            float f = (float)Math.sin(RenderUtil.INSTANCE.interpolate((float)skyLantern.getAge() - 1.0f, skyLantern.getAge()) / 60.0f);
            float f2 = RenderUtil.INSTANCE.interpolate(skyLantern.getPrevX(), skyLantern.getX()) + f;
            float f3 = RenderUtil.INSTANCE.interpolate(skyLantern.getPrevY(), skyLantern.getY());
            float f4 = RenderUtil.INSTANCE.interpolate(skyLantern.getPrevZ(), skyLantern.getZ()) - f;
            float f5 = (float)(skyLantern.getMaxAge() - skyLantern.getAge()) < 10.0f ? (float)(skyLantern.getMaxAge() - skyLantern.getAge()) / 10.0f : 1.0f;
            Color color = RenderUtil.INSTANCE.blend(this.getFireColor(), this.getColor(), 0.5f);
            ParticleTexture particleTexture = ParticleSprite.FIRE_FLY.getParticleTexture();
            Vec3d vec3d = new Vec3d((double)f2 + (double)(Random.Default.nextFloat() - 0.5f) * 0.06, (double)f3 + 0.5, (double)f4 + (double)(Random.Default.nextFloat() - 0.5f) * 0.06);
            float f6 = Random.Default.nextFloat() * 20.0f;
            Vec3d vec3d2 = new Vec3d((Random.Default.nextDouble() - 0.5) * 0.02 + (double)skyLantern.getMotionX(), Random.Default.nextDouble() * 0.06 + (double)skyLantern.getMotionY(), (Random.Default.nextDouble() - 0.5) * 0.02 + (double)skyLantern.getMotionZ());
            float f7 = RenderUtil.INSTANCE.getAlpha(this.getFireColor()) * f5;
            Color color2 = RenderUtil.INSTANCE.withAlpha(this.getFireColor(), f7);
            ParticleManager.INSTANCE.spawn(new WorldParticle(particleTexture, vec3d, f6, 0.25f, 12, vec3d2, color2, 0.0f, 0.0f, false, 768, null));
            if (skyLantern.getAge() % 2 == 0) {
                particleTexture = ParticleSprite.FIRE_FLY.getParticleTexture();
                vec3d = new Vec3d((double)f2 + RandomUtil.k(RangesKt.rangeTo(-0.03, 0.03), false, null), (double)f3 + 1.0, (double)f4 + RandomUtil.k(RangesKt.rangeTo(-0.03, 0.03), false, null));
                f6 = RandomUtil.p(RangesKt.rangeTo(0.0f, 20.0f), false, null);
                vec3d2 = new Vec3d(skyLantern.getMotionX(), skyLantern.getMotionY(), skyLantern.getMotionZ());
                float f8 = 0.5f * f5;
                color2 = RenderUtil.INSTANCE.withAlpha(color, f8);
                ParticleManager.INSTANCE.spawn(new WorldParticle(particleTexture, vec3d, f6, 1.5f, 2, vec3d2, color2, 0.0f, 0.0f, false, 256, null));
                particleTexture = ParticleSprite.FIRE_FLY.getParticleTexture();
                vec3d = new Vec3d((double)f2 + (double)(Random.Default.nextFloat() - 0.5f) * 0.06, (double)f3 + 1.0, (double)f4 + (double)(Random.Default.nextFloat() - 0.5f) * 0.06);
                f6 = RandomUtil.p(RangesKt.rangeTo(0.0f, 20.0f), false, null);
                vec3d2 = new Vec3d(skyLantern.getMotionX(), skyLantern.getMotionY(), skyLantern.getMotionZ());
                float f9 = 0.2f * f5;
                color2 = RenderUtil.INSTANCE.withAlpha(color, f9);
                ParticleManager.INSTANCE.spawn(new WorldParticle(particleTexture, vec3d, f6, 3.0f, 2, vec3d2, color2, 0.0f, 0.0f, false, 256, null));
            }
            int n = skyLantern.getAge();
            skyLantern.setAge(n + 1);
            if (n <= skyLantern.getMaxAge() && !((double)skyLantern.getY() - Globals.getPlayer().getY() > 20.0)) continue;
            iterator2.remove();
        }
    }

    private final void spawnLantern() {
        Entity entity = Globals.getPlayer();
        Vec3d vec3d = this.getPos(entity).add(Globals.getPlayer().getVelocity().multiply(10.0));
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        Vec3d vec3d2 = vec3d;
        DefaultConstructorMarker defaultConstructorMarker = null;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = (float)(vec3d2.z + (double)RandomUtil.D(-48.0f, 48.0f, false, null));
        lanterns.add(new SkyLantern((float)(vec3d2.x + (double)RandomUtil.D(-48.0f, 48.0f, false, null)), (float)(vec3d2.y + 4.0 + (double)RandomUtil.D(-2.0f, 2.0f, false, null)), f9, f8, f7, f6, f5, f4, f3, f2, f, defaultConstructorMarker));
    }

    @Flow(priority=-10)
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = b ^ 0x17DB295CC839L;
        long l2 = l ^ 0x83F38DA2A55L;
        if (lanterns.isEmpty()) {
            return;
        }
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        VertexFormat vertexFormat = VertexFormats.POSITION_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl = false;
        int n2 = 9 * lanterns.size();
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        Object object2 = lanterns;
        Iterable iterable = object2;
        Collection collection = new ArrayList();
        for (Object t : iterable) {
            SkyLantern skyLantern = (SkyLantern)t;
            if (!(RenderState.INSTANCE.getFrustum().intersectPoint(skyLantern.getX(), (double)skyLantern.getY() + 1.5, skyLantern.getZ()) || RenderState.INSTANCE.getFrustum().intersectPoint(skyLantern.getX(), skyLantern.getY(), skyLantern.getZ()))) continue;
            collection.add(t);
        }
        for (Object object2 : (List)collection) {
            float f = RangesKt.coerceAtLeast(RenderUtil.INSTANCE.interpolate((float)((SkyLantern)object2).getAge() - 1.0f, ((SkyLantern)object2).getAge()), 0.0f);
            float f2 = (float)Math.sin(f / 60.0f);
            float f3 = RenderUtil.INSTANCE.interpolate(((SkyLantern)object2).getPrevX(), ((SkyLantern)object2).getX()) + f2;
            float f4 = RenderUtil.INSTANCE.interpolate(((SkyLantern)object2).getPrevY(), ((SkyLantern)object2).getY());
            float f5 = RenderUtil.INSTANCE.interpolate(((SkyLantern)object2).getPrevZ(), ((SkyLantern)object2).getZ()) - f2;
            render3DEvent.getStack().push();
            render3DEvent.getStack().translate((double)f3 - camera2.getCameraPos().x, (double)f4 - camera2.getCameraPos().y, (double)f5 - camera2.getCameraPos().z);
            render3DEvent.getStack().multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(RenderUtil.INSTANCE.interpolate(((SkyLantern)object2).getRotation() - ((SkyLantern)object2).getRotationSpeed(), ((SkyLantern)object2).getRotation())));
            Matrix4f matrix4f = render3DEvent.getStack().peek().getPositionMatrix();
            Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
            float f6 = ((SkyLantern)object2).getMaxAge();
            float f7 = f;
            float f8 = RenderUtil.INSTANCE.interpolate(((SkyLantern)object2).getRotation() - ((SkyLantern)object2).getRotationSpeed(), ((SkyLantern)object2).getRotation());
            float f9 = f5;
            float f10 = f4;
            float f11 = f3;
            this.drawLantern(batchedBuffer, matrix4f, f11, f10, f9, f8, f7, f6);
            render3DEvent.getStack().pop();
        }
        render3DEvent.getStack().push();
        MatrixStack matrixStack = render3DEvent.getStack();
        Quaternionf quaternionf = RotationAxis.POSITIVE_Y.rotation(-RenderState.INSTANCE.getCameraYaw());
        Intrinsics.checkNotNullExpressionValue(quaternionf, "rotation(...)");
        MatrixUtil.multiply(matrixStack, (Quaternionfc)quaternionf);
        MatrixStack matrixStack2 = render3DEvent.getStack();
        Quaternionf quaternionf2 = RotationAxis.POSITIVE_X.rotation(-RenderState.INSTANCE.getCameraPitch());
        Intrinsics.checkNotNullExpressionValue(quaternionf2, "rotation(...)");
        MatrixUtil.multiply(matrixStack2, (Quaternionfc)quaternionf2);
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultPosColorDepth(), null, null, render3DEvent.getStack().peek().getPositionMatrix(), null, null, 54, null);
        render3DEvent.getStack().pop();
    }

    private final void drawLantern(BatchedBuffer batchedBuffer, Matrix4f matrix4f, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = 0.33f;
        float f8 = 0.08f;
        float f9 = 0.78f;
        float f10 = 1.3299999f;
        float f11 = (0.0f <= f5 ? f5 <= 10.0f : false) ? f5 / 10.0f : (f6 - f5 < 10.0f ? (f6 - f5) / 20.0f : 1.0f);
        float f12 = RenderUtil.INSTANCE.getAlpha(this.getColor()) * f11;
        Color color = RenderUtil.INSTANCE.withAlpha(this.getColor(), f12);
        float f13 = RenderUtil.INSTANCE.getAlpha(this.getColorBottom()) * f11;
        Color color2 = RenderUtil.INSTANCE.withAlpha(this.getColorBottom(), f13);
        float f14 = RenderUtil.INSTANCE.getAlpha(lanternColor) * f11;
        Color color3 = RenderUtil.INSTANCE.withAlpha(lanternColor, f14);
        batchedBuffer.vertex(matrix4f, -0.33f, f9, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, -0.33f, f10, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, f10, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, f9, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, -0.25f, 0.58f, -0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, -0.33f, 0.78f, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, 0.78f, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, 0.25f, 0.58f, -0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, -0.33f, f9, f7).color(color);
        batchedBuffer.vertex(matrix4f, -0.33f, f10, f7).color(color);
        batchedBuffer.vertex(matrix4f, f7, f10, f7).color(color);
        batchedBuffer.vertex(matrix4f, f7, f9, f7).color(color);
        batchedBuffer.vertex(matrix4f, -0.25f, 0.58f, 0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, -0.33f, 0.78f, f7).color(color);
        batchedBuffer.vertex(matrix4f, f7, 0.78f, f7).color(color);
        batchedBuffer.vertex(matrix4f, 0.25f, 0.58f, 0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, -0.33f, f9, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, -0.33f, f10, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, -0.33f, f10, f7).color(color);
        batchedBuffer.vertex(matrix4f, -0.33f, f9, f7).color(color);
        batchedBuffer.vertex(matrix4f, -0.25f, 0.58f, -0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, -0.33f, 0.78f, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, -0.33f, 0.78f, f7).color(color);
        batchedBuffer.vertex(matrix4f, -0.25f, 0.58f, 0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, f7, f9, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, f10, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, f10, f7).color(color);
        batchedBuffer.vertex(matrix4f, f7, f9, f7).color(color);
        batchedBuffer.vertex(matrix4f, 0.25f, 0.58f, -0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, f7, 0.78f, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, 0.78f, f7).color(color);
        batchedBuffer.vertex(matrix4f, 0.25f, 0.58f, 0.25f).color(color2);
        batchedBuffer.vertex(matrix4f, -0.33f, f10, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, f10, -0.33f).color(color);
        batchedBuffer.vertex(matrix4f, f7, f10, f7).color(color);
        batchedBuffer.vertex(matrix4f, -0.33f, f10, f7).color(color);
        double d = Math.toRadians(f4);
        Vec3d vec3d = this.rotateAround(f - f7 + f8, f2 + 0.58f, f3 + f8 - f7, f, f2 + 0.58f, f3, d);
        Vec3d vec3d2 = this.rotateAround(f + f7 - f8, f2 + 0.58f, f3 + f8 - f7, f, f2 + 0.58f, f3, d);
        Vec3d vec3d3 = this.rotateAround(f - f7 + f8, f2 + 0.58f, f3 - f8 + f7, f, f2 + 0.58f, f3, d);
        Vec3d vec3d4 = this.rotateAround(f + f7 - f8, f2 + 0.58f, f3 - f8 + f7, f, f2 + 0.58f, f3, d);
        Vec3d vec3d5 = this.rotateAround(f - f7 + f8, f2 + 0.58f, f3, f, f2 + 0.58f, f3, d);
        Vec3d vec3d6 = this.rotateAround(f + f7 - f8, f2 + 0.58f, f3, f, f2 + 0.58f, f3, d);
        Vec3d vec3d7 = this.rotateAround(f, f2 + 0.58f, f3 + f8 - f7, f, f2 + 0.58f, f3, d);
        Vec3d vec3d8 = this.rotateAround(f, f2 + 0.58f, f3 - f8 + f7, f, f2 + 0.58f, f3, d);
        RenderState.addLine$default(RenderState.INSTANCE, vec3d, vec3d2, color3, null, null);
        RenderState.addLine$default(RenderState.INSTANCE, vec3d3, vec3d4, color3, null, null);
        RenderState.addLine$default(RenderState.INSTANCE, vec3d5, vec3d6, color3, null, null);
        RenderState.addLine$default(RenderState.INSTANCE, vec3d2, vec3d4, color3, null, null);
        RenderState.addLine$default(RenderState.INSTANCE, vec3d, vec3d3, color3, null, null);
        RenderState.addLine$default(RenderState.INSTANCE, vec3d7, vec3d8, color3, null, null);
    }

    private final Vec3d rotateAround(float f, float f2, float f3, float f4, float f5, float f6, double d) {
        double d2 = Math.sin(d);
        double d3 = Math.cos(d);
        float f7 = f - f4;
        float f8 = f3 - f6;
        float f9 = f2 - f5;
        double d4 = (double)f7 * d3 + (double)f8 * d2;
        double d5 = (double)(-f7) * d2 + (double)f8 * d3;
        return new Vec3d((double)f4 + d4, (double)f5 + (double)f9, (double)f6 + d5);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SkyLanterns.b = ZkmKeys.a(-7616602262718149376L, -4904436483192952751L, MethodHandles.lookup().lookupClass()).a(195494187096868L);
                        var20 = SkyLanterns.b ^ 83953863487990L;
                        SkyLanterns.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[22];
                        var16_4 = 0;
                        var15_5 = "}\u0001\u00f4\u00c9\u0091\u00bb\u0088\u00c0kO\u009c\u00c4\u00a0^\u00d0\u00e6o\f\u00a0\u0086L[I\u00e8\u00b0\u0004\u00ba\u00b3d\u0085\u00e3\u00ca\u0086Pz\u00c5%'\u00d1\u0018\u00be\u0089kNE\u0087\u0088\u00dd\u00c9\u00e2\u00faI\t}\u0092$uz\u009c\u00b6\u00b5\f\u0087\u0016\u0018\u00c4%c\u001b\u00d8\u0014$\u0085f\u00cf_\u00d2\u00bc1\u0003\u00f9 \u0019\u00bd.\u0088\u00d1\u00df\u00c1 f\u00e5\u000b\u0097\u00d2Qv\u000bna\u001e\u00fa\r\u0090\u00f8\u00c9\u00c5\u007fB\u0089\u00c66AS\u00b2\u0094|\u001e\u00c3\u00a4\u00f10 \u00db\u00b0[\u00ab;1KA\u00e6\u00fbj\u00f9\u0006 -\u0004\u00a1\u001b\u008f\u00bf\u001a/\u00fe\u00a1g\u008b.7\u008e\u00cf\u00e0\u0017\u0010P0\u001a\u0096+X\u009a\u00fa'},\u00cd;\u00a4\u00a6\u00d9 \u00f2\u00dd\u00c8\u00f1\u00d2\u00e3R;z\u00c2\u00f2H_`$\r]\u0085\u0006\u00d6\u0010\u0019\u008d2\u00cb\u00e1&\u0099\u00d0w\u00c61\u00104_\u0005\u00d1>\u00fbFe\b\u00ffs\u00f5\b\u00cb\"\u00c8\u0018\u00b0\u00b1&\u00ab\b\u0011\u00fb\u008d\u008f}5\u0001\u0012\u00df\u00c1\u00cd2D\u00bd\u00e7\u00f7U\u00c3\u008c\u00100\u0007a\u00de#c\u00a4\u00e6\u00950b-\u0016\u0007\u00c3\u0097  \u000ef\u00ed\u00ba\u00f5\u00e6H\u00b1\u0089\u0006#&I\u00e6E.\u00a9l3\u00dc\u00e0\u009cq[;\u00df\u008bQ\u00c6P]\u0018\u0013\u0013\u009c\u00a0\u0013\u00a6\u00fb\u0098jcP\u00ce\u00c5\u00ccGP\u00c0\u00can\u00d8\u00f0pd\u008c\u0010\u009c\u00fffWUz \u00dbz\u000b\f#C\u00f2=o\u0010\u008a\u00e2\u0006\u00daN(=\u0099\nhF\u00c4\u00d7\u00ee\u0011\u00e2\u0018H#\u00f21\u0011\u00f1\u00e0\u00e7\u0084\u00a5u\u00a9{\u00ed\u00dd5\u0017\u009f\u00e3\u0002\u00e5X\u00f4\u00a5\u0010\u008aR\u0085\t\u00ea\u00a6\u00c9\u000f|\u00ef\u00f5\u00da\u0011@\u001d~\u0010\u001d}\u00a5~\u0014\u0092\u00fb\u0017\u00d6\u00c9CH\u0001`\u00d9\\\u0018R\r9.?d\u000425W\u0090(\u0087\u00d0h\u00e4\u00d7\u00b4\u00fa\u00d3\u00c8,\u00d1m \u008f\u00b6/\u00ad\u00c0\u00a4\u00dd\u00b3\u00b0\u001c\u00c1Ig\u00f3m,t\u00c2\u00f0fJT\u00a1\u0085\u0084\u00a1w\u0091d\u000b\u00d3\u008c(\u00e5\u001d\u0089&\u0002M\u0016\n)\r\u0010\r\u00fe\u0017HfU3\u0017\u00cb\u00a4d\u0090\u000b\u0017\u00b8:\u0084\u00be\u0017\u00c8\u00c4\u0012\u0094\u00b9\\\u00178\u0083\u00a5 \u0001\u00dd\u0080\u00ec\u00db\u0081\u00d8A\rA$\u00eb\u00ca\u0082|\u00d9\u008f\u0090Ww;0Y\u00da\u0082=\u0090\u00e9\u008b\u00aa\u00c6\u00ab";
                        var17_6 = 547;
                        var14_7 = 64;
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
                            var18_3[var16_4++] = SkyLanterns.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00be8\u00e4\u00e7H\u0090\u00e2\u0082\u00fcF\u00d1\u00c63v7\u00a8\n%\u00b9\u00ab\u001eP\u00c6\f\u00fd9Z\u00a4\u00d7F3\u0086\u0011Hzja\r\u00c29UB8W\u009e\u0099\u00ce\u00be\u009b\u001a\u00b0\u00e2\"\u0090\u00b2\u00e98\u009eF\\\u00e0\u00c4\u008bS\u00f8\u00a6\u00d8v\u0087\u00d2\u00c4\u0017qD\u00b2\u00fc\u0007\u00fa\u00b6F#\u0085=\u009b\u00a3\u008eM\u0001)5\u00b1\u009f\u0098:m\u00c7\u00026\u0015\u00a29\u009a\u00f0\u00ae\u00d8\u00de<\u00b5\u0082o\u00a6\u0095\u001f";
                            var17_6 = 113;
                            var14_7 = 56;
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
                            var18_3[var16_4++] = SkyLanterns.zkmDecodeUtf8(var19_9).intern();
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
                SkyLanterns.c = var18_3;
                SkyLanterns.d = new String[22];
                SkyLanterns.h = new HashMap<K, V>(13);
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
                var6_12 = new long[14];
                var3_13 = 0;
                var4_14 = "\u001e\u00fe\u00d3\u0082\u0004\u00130R>\tQ\u00ce\u00ad\u00b2x\u00a09\u00a7\u00cc\u0005\u000b\u0012\u00d5\u00da\u00fa)\u00c4\u009f\u0081!W1\u00ac\u00ea.Ux\u00e2\u0081\u00c2\u00bb4\u00c5N\u00a3'\u008e\u008f\u0093\u0089'#^\u00d4\u00b1\u00e7[\u00f5k/ \u00f2\u00a3\u00edD\u0087\u00be\u00b1\u00a5<\u008bN\u00c7\"\u00b2]6\u007f?\u00d6\u00a3\u00e6b\u00da\u00b1\u00b4\u00dd\u00e6\u00ae\u008c\u001et.u\t\u0094";
                var5_15 = 96;
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
                    var4_14 = "\u0097\u00d6\u00d1FD\u00fb\u00189I\t\u00b3\u00b5>\u00f4\u00a8\u009c";
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
        SkyLanterns.f = var6_12;
        SkyLanterns.g = new Integer[14];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(SkyLanterns.class, "fireColor", "getFireColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(SkyLanterns.class, "color", "getColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(SkyLanterns.class, "colorBottom", "getColorBottom()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(SkyLanterns.class, "count", "getCount()I", 0))};
        SkyLanterns.delegatedProperties = var32_20;
        SkyLanterns.INSTANCE = new SkyLanterns();
        v15 = SkyLanterns.INSTANCE;
        v16 = Color.YELLOW;
        Intrinsics.checkNotNullExpressionValue(v16, "YELLOW");
        SkyLanterns.fireColor$delegate = SettingFactory.colorSetting$default(v15, "fire-color", v16, null, null, 12, null);
        v17 = SkyLanterns.INSTANCE;
        v18 = Color.YELLOW;
        Intrinsics.checkNotNullExpressionValue(v18, "YELLOW");
        var35_21 = null;
        var36_22 = 12;
        var37_23 = null;
        var38_24 = null;
        var39_25 = RenderUtil.INSTANCE.withAlpha(v18, 0.2f);
        var40_26 = "color";
        SkyLanterns.color$delegate = SettingFactory.colorSetting$default(v17, var40_26, var39_25, var38_24, var37_23, var36_22, var35_21);
        v19 = SkyLanterns.INSTANCE;
        v20 = Color.ORANGE;
        Intrinsics.checkNotNullExpressionValue(v20, "ORANGE");
        var44_27 = null;
        var45_28 = 12;
        var46_29 = null;
        var47_30 = null;
        var48_31 = RenderUtil.INSTANCE.withAlpha(v20, 0.2f);
        var49_32 = "bottom-color";
        SkyLanterns.colorBottom$delegate = SettingFactory.colorSetting$default(v19, var49_32, var48_31, var47_30, var46_29, var45_28, var44_27);
        SkyLanterns.count$delegate = SettingFactory.intSetting$default(SkyLanterns.INSTANCE, "count", 256, new IntRange(0, 512), null, null, 24, null);
        SkyLanterns.lanternColor = new Color(70, 70, 70);
        SkyLanterns.lanterns = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5E1D;
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
                throw new RuntimeException("su/catlean/ke", exception);
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
            SkyLanterns.d[n2] = SkyLanterns.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SkyLanterns.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ke" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x15FE;
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
                throw new RuntimeException("su/catlean/ke", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SkyLanterns.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SkyLanterns.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ke" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SkyLanterns.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SkyLanterns.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

