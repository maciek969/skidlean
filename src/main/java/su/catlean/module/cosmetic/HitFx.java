/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package su.catlean.module.cosmetic;

import com.google.common.collect.Lists;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.Criticals;
import su.catlean.module.cosmetic.HitEffect;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.GlowTexture;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.particle.ParticleManager;
import su.catlean.render.particle.ParticleSprite;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.render.particle.WorldParticle;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.RandomUtil;
import su.catlean.util.Timer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class HitFx
extends Module {
    @NotNull
    public static final HitFx INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting smoke$delegate;
    @NotNull
    private static final BooleanSetting particles$delegate;
    @NotNull
    private static final ModeSetting particle$delegate;
    @NotNull
    private static final FloatSetting size$delegate;
    @NotNull
    private static final IntSetting duration$delegate;
    @NotNull
    private static final IntSetting count$delegate;
    @NotNull
    private static final FloatSetting gravity$delegate;
    @NotNull
    private static final FloatSetting velocityXZ$delegate;
    @NotNull
    private static final FloatSetting velocityY$delegate;
    @NotNull
    private static final List effects;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map k;

    private HitFx() {
        super("hit-fx", Categories.getCosmetic(), null, null);
    }

    private final boolean getSmoke() {
        return (Boolean)smoke$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setSmoke(boolean bl) {
        smoke$delegate.setValue(this, delegatedProperties[0], bl);
    }

    private final boolean getParticles() {
        return (Boolean)particles$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setParticles(boolean bl) {
        particles$delegate.setValue(this, delegatedProperties[1], bl);
    }

    private final ParticleSprite getParticle() {
        return (ParticleSprite)((Object)particle$delegate.getValue(this, delegatedProperties[2]));
    }

    private final void setParticle(ParticleSprite particleSprite) {
        particle$delegate.setValue(this, delegatedProperties[2], (Object)particleSprite);
    }

    private final float getSize() {
        return ((Number)size$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    private final void setSize(float f) {
        size$delegate.setValue(this, delegatedProperties[3], Float.valueOf(f));
    }

    private final int getDuration() {
        return ((Number)duration$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final void setDuration(int n) {
        duration$delegate.setValue(this, delegatedProperties[4], n);
    }

    private final int getCount() {
        return ((Number)count$delegate.getValue(this, delegatedProperties[5])).intValue();
    }

    private final void setCount(int n) {
        count$delegate.setValue(this, delegatedProperties[5], n);
    }

    private final float getGravity() {
        return ((Number)gravity$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    private final void setGravity(float f) {
        long l = 0L;
        l = a ^ l;
        gravity$delegate.setValue(this, delegatedProperties[HitFx.zkmBootstrap2("u", (int)16946, (long)(0x720BF26E2AF0AECCL ^ l))], Float.valueOf(f));
    }

    private final float getVelocityXZ() {
        return ((Number)velocityXZ$delegate.getValue(this, delegatedProperties[7])).floatValue();
    }

    private final void setVelocityXZ(float f) {
        long l = 0L;
        l = a ^ l;
        velocityXZ$delegate.setValue(this, delegatedProperties[HitFx.zkmBootstrap2("u", (int)21680, (long)(0x4F82E8CD54286CFBL ^ l))], Float.valueOf(f));
    }

    private final float getVelocityY() {
        return ((Number)velocityY$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final void setVelocityY(float f) {
        long l = 0L;
        l = a ^ l;
        velocityY$delegate.setValue(this, delegatedProperties[HitFx.zkmBootstrap2("u", (int)23708, (long)(0x39DF9B6A284B3300L ^ l))], Float.valueOf(f));
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        Globals.getMc().executeSync(() -> HitFx.onSendPacket2(sendPacket));
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l;
        long l2 = l = a ^ 0xF40DDA1DADFL;
        long l3 = l2 ^ 0x64C8E839B81FL;
        long l4 = l2 ^ 0x2911A2D7AEF9L;
        ArrayList arrayList = Lists.newArrayList((Iterable)effects);
        Intrinsics.checkNotNullExpressionValue(arrayList, "newArrayList(...)");
        ArrayList arrayList2 = arrayList;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl = false;
        int n2 = arrayList2.size() * 2;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        Object object = arrayList2;
        Iterator iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            HitEffect hitEffect = (HitEffect)t;
            int n3 = RangesKt.coerceIn((int)((float)hitEffect.getLife().getElapsed() / 40.0f), 0, 8);
            float f = (ParticleTexture.SMOKE.L() - ParticleTexture.SMOKE.o()) / 3.0f;
            float f2 = (ParticleTexture.SMOKE.w() - ParticleTexture.SMOKE.v()) / 3.0f;
            float f3 = ParticleTexture.SMOKE.o() + (float)(n3 % 3) * f;
            float f4 = ParticleTexture.SMOKE.v() + (float)(n3 / 3) * f2;
            float f5 = (float)hitEffect.getLife().getElapsed() / 450.0f;
            RenderState.drawBillboard$default(RenderState.INSTANCE, GlowTexture.getIdentifier(), hitEffect.getVec(), hitEffect.getYaw(), hitEffect.getPitch(), 0.0f, 1.0f + f5, l4, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(10), 1.0f), false, batchedBuffer, f3, f4, f3 + f, f4 + f2, true, 128, null);
        }
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        object = gpuTextureView;
        RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend();
        Framebuffer framebuffer = Globals.getMc().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue(framebuffer, "getMainRenderTarget(...)");
        BatchedBuffer.draw$default(l3, batchedBuffer, renderPipeline, framebuffer, null, render3DEvent.getStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", object)), 20, null);
        effects.removeIf(arg_0 -> HitFx.invokePredicate(HitFx::onRender3D$lambda$0, arg_0));
    }

    private static final boolean settingVisible() {
        return INSTANCE.getParticles();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getParticles();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getParticles();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getParticles();
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getParticles();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getParticles();
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getParticles();
    }

    private static final void onSendPacket2(SendPacket sendPacket) {
        PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket;
        if (sendPacket.getPacket() instanceof PlayerInteractEntityC2SPacket && Criticals.INSTANCE.getInteractType(playerInteractEntityC2SPacket = (PlayerInteractEntityC2SPacket)sendPacket.getPacket()) == PlayerInteractEntityC2SPacket.InteractType.ATTACK) {
            Rotation rotation;
            Vec3d vec3d;
            Vec3d vec3d2;
            Vec3d vec3d3 = MovementUtil.INSTANCE.getEyeVector(MovementUtil.INSTANCE.getLastYaw(), MovementUtil.INSTANCE.getLastPitch(), Aura.INSTANCE.getAttackRange());
            if (vec3d3 == null) {
                Box box;
                Entity entity = Criticals.INSTANCE.getTargetEntity((PlayerInteractEntityC2SPacket)sendPacket.getPacket());
                vec3d3 = entity != null && (box = entity.getBoundingBox()) != null ? box.getCenter() : null;
            }
            if ((vec3d2 = vec3d3) == null && (vec3d = MovementUtil.INSTANCE.getEyeVector((rotation = RotationManager.hasActiveRequest$default(RotationManager.INSTANCE, false, null)).getYaw(), rotation.getPitch(), Aura.INSTANCE.getAttackRange())) == null) {
                Object object = Criticals.INSTANCE.getTargetEntity((PlayerInteractEntityC2SPacket)sendPacket.getPacket());
                vec3d = object != null && (object = ((Entity)object).getBoundingBox()) != null ? ((Box)object).getCenter() : (vec3d2 = null);
            }
            if (vec3d2 != null) {
                if (INSTANCE.getSmoke()) {
                    effects.add(new HitEffect(vec3d2, -MovementUtil.INSTANCE.getLastYaw(), MovementUtil.INSTANCE.getLastPitch(), new Timer()));
                }
                if (INSTANCE.getParticles()) {
                    int n = INSTANCE.getCount();
                    for (int i = 0; i < n; ++i) {
                        Object var56_24 = null;
                        boolean bl = false;
                        float f = INSTANCE.getVelocityXZ();
                        float f2 = -INSTANCE.getVelocityXZ();
                        Object var63_28 = null;
                        boolean bl2 = false;
                        float f3 = INSTANCE.getVelocityY();
                        float f4 = -INSTANCE.getVelocityY();
                        Object var70_11 = null;
                        boolean bl3 = false;
                        float f5 = INSTANCE.getVelocityXZ();
                        float f6 = -INSTANCE.getVelocityXZ();
                        DefaultConstructorMarker defaultConstructorMarker = null;
                        int n2 = 832;
                        boolean bl4 = false;
                        float f7 = 0.0f;
                        float f8 = INSTANCE.getGravity();
                        Color color = null;
                        Vec3d vec3d4 = new Vec3d(RandomUtil.D(f2, f, bl, var56_24), RandomUtil.D(f4, f3, bl2, var63_28), RandomUtil.D(f6, f5, bl3, var70_11));
                        int n3 = INSTANCE.getDuration();
                        float f9 = INSTANCE.getSize();
                        ParticleManager.INSTANCE.spawn(new WorldParticle(INSTANCE.getParticle().getParticleTexture(), vec3d2, (float)(Math.random() * 50.0), f9, n3, vec3d4, color, f8, f7, bl4, n2, defaultConstructorMarker));
                    }
                }
            }
        }
    }

    private static final boolean onRender3D$lambda$0(HitEffect hitEffect) {
        Intrinsics.checkNotNullParameter(hitEffect, "it");
        return hitEffect.getLife().hasPassed(360);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        HitFx.a = ZkmKeys.a(-7003897176433897630L, 2477269595516142226L, MethodHandles.lookup().lookupClass()).a(203595004427975L);
                        var20 = HitFx.a ^ 65433859242015L;
                        HitFx.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[34];
                        var16_4 = 0;
                        var15_5 = "\u00ba?\u00ea\u00b1^\u00abb9\u00c0\u00ff\u0001Ys7\u009a\u000f\u00f7\u0003\u0088\u0015C:\u0003b\u00d2@\u00a3[\u00a1\u00bc\u0016\u009d\u0010\u00da#q\u0085\u001f\u00d9\u0000 \b\u00b84\u00a5rAM\u008f\u0010U|\u0084\u00e735\u008d\u00c5v!\u00b7.&%\u00cd\u00e2\u0018\u00b7\u009c]\u00df6$\u00cf?\u00f8\u00cd%\u00012M\u00aci`\u00c0Z\\\u00b4\u0094\u0012-8M{\u00ef\u0001\u00b5.j\u0080Q\u0018J'\u0081\u00a7\u00c5\u00a7\u00c1N6\u001e\u009e\u00a7\u00f6\u009c[\u009a\u00c1\u00a181\u0019\u008f\u00df\u00b3\u00d9F`\u00fb\u00c7\u00e8f\u00881h\u001a\u00b3a\u00a02\u000b\u00cc0\u00f0\u00d9K\u00d28\u0088u\u0011\u001b\u0086\u00ea{\u001f\u00ef\u00f6\u00d3\u00dd\u00d4\u009b\u0094F\u00e2\u00ec\u00b2\fa\u00f3\fF\u00c5\u00fa\u009cw\u0018,\f\u00c3\u00c6ZJZ7G\u00feU\u0081\fV\u00d0\u00f5zA\u00820\\\u00ed\u00b1\u00dbv\u00ac_(\u00f7\u00a2\u00bb\u00bb$\u0082\u00f7\u00dc\u001a\u00fc\u00c0\u009b\u001c\u001c\u00ba\u00a4A\u009cNh\u001atx\u00dbg\b{n\u009d\u00be\r;\u00e9\u001b\u00ab\u007f7S(\u00fb(\u00fe\u00114\u00ad\u00d7\u00e2\u001d\u00b7\u007f\u00e1\u00e5P\u0081\u00b3.\u0092\u0080\u008b\u00fdF\u00cb^\u00ad\u00e5\u009c\u007f\u00b6\u00e96\u00c59\u00e0\u0086\u00e2ds\u009f:c\\\u0010\u0080\u009e\u00d8\u00d7,\u00e7\u0082N\u009d\u001al\u00c2\u00c74~\r\u0018x\u00ab!\u00a6\u00fe\u0007\u00a5\"d\u00d5GT\u00f7\u00a8x(-9A\u00cc\u001dr\u0012` \u00d7\u00c2~\u00d0\u00d4\u0087\u00b1\u00adj,I:\u00dc\u0096H\u00dc7\u00a6\u00c3\u00b4\u0083(\u0002\u00d6\u00e4\u00d3\u000e|\u00f0\u0086\u00dc\u0015\u0010\rW\u00ae\b\u0093\u00b8n\u0013\u0016\u0087p\u008d\u00bd<\u0001\u00ba \u0091\u00a7\u0004-\u000e}?\u00e5\u001e~P\u0006\u00fe\u00fe!7J\u00f3\u00ac\u0005O'VP\u0085\"H\u00cfE\t v a\u00c8\u00cfL\u00f2\u0003\u00f6\u00dfD\u00caR8\u00acA\u00b3\u0086f[S\u0012\u00f2)_Q\nB\u0014\u00c3\u0010T\u009b\u00ad(\u00cb\u00dar\u008b\u00fca\u0000\u00af\u00b8/g\u00a7&\u0014\u0015\u0082\u00c9\u008c\u00c1\u008d\u00f2\u00e8\u00fa\u00bb)\u0085\u009a\r\u0087\u00f3\u00e5\u00b9\u000f,\u00c7\u00f1\u00d7;\u00fb\u00e0\u0018\u00a5\u00b7\u00b94\u00fc\u009d\u0005\u009b\u0092\u00cfBx?\u00b3\u00b5\u00b8]\u00a4\u00b6\u00eel\u00db5\u00db\u00183c\u008d\u00fc\u009a\u0085\u00fd_\u00ab\u00ec\u00e2\u0083\u00b1Ux\u00bc8\u00b79G\u0006\u00a9\u008c \u0010PZ\u000f&b\u00d4\u00b7\u00cf\u008e\u0081HY\u00dfi\u00d4@\u0010>qK\u00b8\u00cc)/s\u0013\u00b8\rf^\u001d\u000f\u00b5 :]U\u0099\u0087\u00e9\u00d0\u00fbz\u008d\u00b8q\u00bf<\u00f1\u0086\u001e9\u0081\u00ff\u0093\u00c5%\u00aa\u00e5\u0002-\u00f1\nG\u00e6\u0097 \u00c0\u00e4j\u00de\u0088I\\\u00a4\u0007\u0004\u00dd\u0083!p\u0014\u00e6\u00db\u00e8F\u008eYH7n\u00b2\u0015H\fmW\u00b1\u0081\u0018\u00d0]\u0002J\u008b\"\u001f1\u00a8\u00e5h\u0016Y\u00ed\u00beh\u0086r\u001c\u00e0\u0099\u00af\"\u00af(C\u00c8\u00c6\u008fW\u0006(\u0097\u0019\u00c7h\u00cc\"\u00eb\u00aa_\u0097EEG2\u00de\u00fd\u00d2\u00ed\u0012%\u00cc\u0000\u001d\u00e3\t\u00d2*\u00e7~*}e* i\u00e7\u00a6\u008f\u0083\u0003\u0001\u00d5'\u00a7 *\u00b4\u00d2\u00aaq\u00dc\u0085\u00c47W?\u00f5L\u00b8$\u00c1j\u00ccT\u00cb\u00b2 A\u0093+`[t\u00be\u0005l98\u00aa\u00a1\u00baG\u00d7\"\u009fMyZ'\u00f53\u0081\u00d5\u0000\u0097&\u00fa\u000e\u000b \u00a4\u00ac\u000f\u00fe\u00ba5B\u0000^\u00e8\u00ac\u00d5\u00fb\u00aa1\u00c3IYn\u000eP+\u00e3\u00a4eul\u00db}X\u00e6\u0001\u0010\u00f9\u0083O&\u00b0P\u0011p\u00ef\u00c3S\u00c9owS~\u0010\"\u00b5\u00ccF\u00d23\u00be\u00deW\u00e1\u009e\u001d\u0004\u0018\u00a3g\u0010f_N\u00ce\u00ecs;\u00c2\u00cdq<{\u00d5%\u00d5,\u0018\u00cc\u009f5\u0005\u00be\u00d4U\u0094\u00877~\u008a\u00e2\u00ff5\u00c8\u001e\u00b6\b7\n\u00c7\u0017O(f\u000f_\u0004\u00e3\u00d6L\u00d6\u001cG\u00f2\u008bi\u0016\u00d9\u00db\u009c\u00a0{\u00be%\u00a8<\u00b5\u0083\u0090g\u0015^\u001a\u008cw\n\u00dc1\u0018\u00f21cM 7\u00c7@\u0015\u00ed\u00c2y\u00be8\u00ef\u0088Q\u008eK\u001c\u0012_\u0007\fD\u00bb\u009c\u008d;\u0002\u000b;\u00be\u00f8\u00ff>\u00b9";
                        var17_6 = 951;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = HitFx.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "1_\u0014\u0099o\u009a\u0098\u0086\u00a8:'\u0087\u0085<\u00bf\u00b6 \u0001w>9a\u000enB \u00bb\t\u0094\u00f6\u00d5\u00dbi\u00f42\u008e\u0012\u00beb\u00a2\u00f9\u00fee\u00caE[\u00d2)\u00ae";
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
                            var18_3[var16_4++] = HitFx.zkmDecodeUtf8(var19_9).intern();
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
                HitFx.c = var18_3;
                HitFx.d = new String[34];
                HitFx.k = new HashMap<K, V>(13);
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
                var4_14 = "W\u0095\u009em\u00b3\u0098bE\u008a\u0010GU\u001f\u00b48\u00c95\u00dcDF\u00ef\u00da\u00cc\u00ba;[\u00bae\u00d7\u00d8\u00f6J\u00c6\u00ac\u00842\u00f5\u0015\u0004\u000f\u00e2\u001b|\u0094U\u008d_\u0093y\u001c'\u00e6q&\u00ac\u009b\u000e5\u00e9\u0088K\t\u0081?\u00ad\u008c\u00ab\u00d18\u00b6\u00be\u00f1\u00e2\u00c3\u00f7\u0097\u00d2wh\u00ff\u00e3\u00b1\u001f\u009a\u00b5\u0010\u00d0\u00e1y(\u00cc\u0085\u00ce\u00a86\u009f8\u00a99\u00b3 &\u001d\u00deF)a6|\u00fd\u00f2\u00f0\u00c143\u00cbO\u00ff*j\u008a\u0003yu\u00c1A\u00a3\u00ce";
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00b4?\u00e5~B\u000f$\u00c3\u00fe:\u0082\u0013\u00f2\u00d9\u001a3";
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
        HitFx.h = var6_12;
        HitFx.i = new Integer[18];
        var32_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "smoke", "getSmoke()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "particles", "getParticles()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "particle", "getParticle()Lsu/catlean/fo;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "size", "getSize()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "duration", "getDuration()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "count", "getCount()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "gravity", "getGravity()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "velocityXZ", "getVelocityXZ()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HitFx.class, "velocityY", "getVelocityY()F", 0))};
        HitFx.delegatedProperties = var32_20;
        HitFx.INSTANCE = new HitFx();
        HitFx.smoke$delegate = SettingFactory.booleanSetting$default((SettingContainer)HitFx.INSTANCE, "smoke", true, null, null, 12, null);
        HitFx.particles$delegate = SettingFactory.booleanSetting$default((SettingContainer)HitFx.INSTANCE, "particles", true, null, null, 12, null);
        HitFx.particle$delegate = SettingFactory.modeSetting$default((SettingContainer)HitFx.INSTANCE, "particle", (Enum)ParticleSprite.HEART, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        HitFx.size$delegate = SettingFactory.floatSetting$default(HitFx.INSTANCE, "size", 0.2f, RangesKt.rangeTo(0.01f, 0.5f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        HitFx.duration$delegate = SettingFactory.intSetting$default(HitFx.INSTANCE, "duration", 15, new IntRange(5, 60), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
        HitFx.count$delegate = SettingFactory.intSetting$default(HitFx.INSTANCE, "count", 15, new IntRange(5, 60), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 8, null);
        HitFx.gravity$delegate = SettingFactory.floatSetting$default(HitFx.INSTANCE, "gravity", 0.03f, RangesKt.rangeTo(-0.15f, 0.15f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 8, null);
        HitFx.velocityXZ$delegate = SettingFactory.floatSetting$default(HitFx.INSTANCE, "horizontal-motion", 0.25f, RangesKt.rangeTo(0.01f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 8, null);
        HitFx.velocityY$delegate = SettingFactory.floatSetting$default(HitFx.INSTANCE, "vertical-motion", 0.5f, RangesKt.rangeTo(0.01f, 2.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 8, null);
        HitFx.effects = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5D74;
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
                throw new RuntimeException("su/catlean/k3", exception);
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
            HitFx.d[n2] = HitFx.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HitFx.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x49BF;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
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
                throw new RuntimeException("su/catlean/k3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HitFx.i[n2] = n3;
        }
        return i[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HitFx.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HitFx.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HitFx.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

