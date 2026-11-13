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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.Absorption$WhenMappings;
import su.catlean.module.cosmetic.AbsorptionEntry;
import su.catlean.module.cosmetic.AbsorptionOrb;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.ColorMode;
import su.catlean.render.GlowTexture;
import su.catlean.render.MatrixUtil;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Absorption
extends Module {
    @NotNull
    public static final Absorption INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting lmode$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    private static final ThreadLocalRandom random;
    @NotNull
    private static final List entries;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private Absorption() {
        super("absorption", Categories.getCosmetic(), null, null);
    }

    private final ColorMode getLmode() {
        return (ColorMode)((Object)lmode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[1]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Iterable iterable = entries;
        for (Object t : iterable) {
            AbsorptionEntry absorptionEntry = (AbsorptionEntry)t;
            Iterator iterator2 = absorptionEntry.getParticles().iterator();
            while (iterator2.hasNext()) {
                AbsorptionOrb absorptionOrb;
                block4: {
                    block3: {
                        absorptionOrb = (AbsorptionOrb)iterator2.next();
                        int n = absorptionOrb.getAge();
                        absorptionOrb.setAge(n + 1);
                        absorptionOrb.setPrevYaw(absorptionOrb.getYaw());
                        absorptionOrb.setYaw(absorptionOrb.getYaw() + absorptionOrb.getSpinSpeed());
                        absorptionOrb.setPrevAlpha(absorptionOrb.getAlpha());
                        if (!absorptionOrb.isFadeIn()) break block3;
                        absorptionOrb.setAlpha(absorptionOrb.getAlpha() + 0.2f);
                        if (!(absorptionOrb.getAlpha() >= 1.0f)) break block4;
                        absorptionOrb.setAlpha(1.0f);
                        if (random.nextInt(1000) >= 5) break block4;
                        absorptionOrb.setFadeIn(false);
                        break block4;
                    }
                    absorptionOrb.setAlpha(absorptionOrb.getAlpha() - 0.2f);
                    if (absorptionOrb.getAlpha() <= 0.0f) {
                        iterator2.remove();
                        continue;
                    }
                }
                if (absorptionOrb.getAge() <= absorptionOrb.getLifeTime()) continue;
                absorptionOrb.setFadeIn(false);
            }
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        Object object;
        Object object2;
        long l = a ^ 0x73D9876C7EAEL;
        long l2 = l ^ 0x294F0D15AD9CL;
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        Object object3 = Globals.getMc().world;
        if (object3 == null || (object3 = ((ClientWorld)object3).getPlayers()) == null) {
            return;
        }
        Object object4 = object3;
        Iterator iterator2 = object4.iterator();
        while (iterator2.hasNext()) {
            Object v2;
            Object object5;
            Iterable iterable;
            int n;
            block17: {
                object2 = (AbstractClientPlayerEntity)iterator2.next();
                if (Intrinsics.areEqual(object2, Globals.getMc().player)) continue;
                float f = RangesKt.coerceIn(((PlayerEntity)object2).getAbsorptionAmount() / 16.0f, 0.0f, 1.0f);
                n = (int)(60.0f * f);
                iterable = entries;
                for (Object t : iterable) {
                    object5 = (AbsorptionEntry)t;
                    if (!(((AbsorptionEntry)object5).getPlayerId() == ((Entity)object2).getId())) continue;
                    v2 = t;
                    break block17;
                }
                v2 = null;
            }
            object = v2;
            if (object == null) {
                iterable = new ArrayList();
                for (int i = 0; i < n; ++i) {
                    ((Collection)iterable).add(new AbsorptionOrb(random.nextFloat() * 360.0f, 0.0f, random.nextFloat() * 360.0f, 1.0f + random.nextFloat() * 0.2f, 0.0f, 0.0f, false, 0.0f, null));
                }
                Collection collection = entries;
                collection.add(new AbsorptionEntry(((Entity)object2).getId(), (List)iterable));
                continue;
            }
            if (((AbsorptionEntry)object).getParticles().size() < n) {
                iterable = ((AbsorptionEntry)object).getParticles();
                iterable.add(new AbsorptionOrb(random.nextFloat() * 360.0f, 0.0f, random.nextFloat() * 360.0f, 1.0f + random.nextFloat() * 0.2f, 0.0f, 0.0f, false, 0.0f, null));
                continue;
            }
            if (((AbsorptionEntry)object).getParticles().size() <= n) continue;
            int n2 = ((AbsorptionEntry)object).getParticles().size() - n;
            int n3 = 0;
            Iterable iterable2 = ((AbsorptionEntry)object).getParticles();
            object5 = iterable2.iterator();
            while (object5.hasNext()) {
                Object e = object5.next();
                AbsorptionOrb absorptionOrb = (AbsorptionOrb)e;
                if (n3 >= n2) continue;
                absorptionOrb.setAge(absorptionOrb.getAge() + 5);
                ++n3;
            }
        }
        render3DEvent.getStack().push();
        MatrixStack matrixStack = render3DEvent.getStack();
        Quaternionf quaternionf = RotationAxis.POSITIVE_Y.rotationDegrees(-camera2.getYaw() + 180.0f);
        Intrinsics.checkNotNullExpressionValue(quaternionf, "rotationDegrees(...)");
        MatrixUtil.multiply(matrixStack, (Quaternionfc)quaternionf);
        MatrixStack matrixStack2 = render3DEvent.getStack();
        Quaternionf quaternionf2 = RotationAxis.POSITIVE_X.rotationDegrees(-camera2.getPitch());
        Intrinsics.checkNotNullExpressionValue(quaternionf2, "rotationDegrees(...)");
        MatrixUtil.multiply(matrixStack2, (Quaternionfc)quaternionf2);
        int n = 0;
        object2 = entries;
        Object object62 = object2.iterator();
        while (object62.hasNext()) {
            object = object62.next();
            AbsorptionEntry absorptionEntry = object;
            n += absorptionEntry.getParticles().size();
        }
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n4 = 4;
        boolean bl = false;
        int n5 = n;
        VertexFormat vertexFormat2 = vertexFormat;
        object2 = new BatchedBuffer(vertexFormat2, n5, bl, n4, defaultConstructorMarker);
        for (Object object62 : entries) {
            if (Globals.getWorld().getEntityById(((AbsorptionEntry)object62).getPlayerId()) == null) continue;
            double d = RenderUtil.INSTANCE.interpolate(((Entity)object).lastX, ((Entity)object).getX());
            double d2 = RenderUtil.INSTANCE.interpolate(((Entity)object).lastY, ((Entity)object).getY()) + 1.0;
            double d3 = RenderUtil.INSTANCE.interpolate(((Entity)object).lastZ, ((Entity)object).getZ());
            for (AbsorptionOrb absorptionOrb : ((AbsorptionEntry)object62).getParticles()) {
                Matrix4f matrix4f;
                double d4 = Math.toRadians(RenderUtil.INSTANCE.interpolate(absorptionOrb.getPrevYaw(), absorptionOrb.getYaw()));
                double d5 = Math.toRadians(absorptionOrb.getPitch());
                double d6 = d + Math.cos(d4) * (double)absorptionOrb.getRadius() * Math.cos(d5);
                double d7 = d2 + Math.sin(d5) * (double)absorptionOrb.getRadius();
                double d8 = d3 + Math.sin(d4) * (double)absorptionOrb.getRadius() * Math.cos(d5);
                double d9 = d6 - d;
                double d10 = d7 - d2;
                double d11 = d8 - d3;
                float f = (float)(-Math.toDegrees(Math.atan2(d11, d9))) + 90.0f;
                double d12 = Math.sqrt(d9 * d9 + d11 * d11);
                float f2 = (float)Math.toDegrees(Math.atan2(d10, d12));
                Color color = switch (Absorption$WhenMappings.S[this.getLmode().ordinal()]) {
                    case 1 -> this.getColor();
                    case 2 -> Theme.INSTANCE.getGradientColor(absorptionOrb.getAge());
                    case 3 -> RenderUtil.INSTANCE.rainbow(18, absorptionOrb.getAge());
                    case 4 -> RenderUtil.INSTANCE.getRainbow(18, absorptionOrb.getAge(), 0.6f, 1.0f);
                    case 5 -> RenderUtil.INSTANCE.getRainbow(18, absorptionOrb.getAge(), 1.0f, 1.0f);
                    default -> throw new NoWhenBranchMatchedException();
                };
                float f3 = RenderUtil.INSTANCE.interpolate(absorptionOrb.getPrevAlpha(), absorptionOrb.getAlpha());
                float f4 = 0.4f * f3;
                Color color2 = RenderUtil.INSTANCE.withAlpha(color, f3);
                MatrixStack matrixStack3 = new MatrixStack();
                matrixStack3.push();
                Quaternionf quaternionf3 = RotationAxis.POSITIVE_X.rotationDegrees(camera2.getPitch());
                Intrinsics.checkNotNullExpressionValue(quaternionf3, "rotationDegrees(...)");
                MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf3);
                Quaternionf quaternionf4 = RotationAxis.POSITIVE_Y.rotationDegrees(camera2.getYaw() + 180.0f);
                Intrinsics.checkNotNullExpressionValue(quaternionf4, "rotationDegrees(...)");
                MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf4);
                matrixStack3.translate(d6 - camera2.getCameraPos().x, d7 - camera2.getCameraPos().y, d8 - camera2.getCameraPos().z);
                Quaternionf quaternionf5 = RotationAxis.POSITIVE_Y.rotationDegrees(f);
                Intrinsics.checkNotNullExpressionValue(quaternionf5, "rotationDegrees(...)");
                MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf5);
                Quaternionf quaternionf6 = RotationAxis.POSITIVE_X.rotationDegrees(-f2);
                Intrinsics.checkNotNullExpressionValue(quaternionf6, "rotationDegrees(...)");
                MatrixUtil.multiply(matrixStack3, (Quaternionfc)quaternionf6);
                ParticleTexture particleTexture = ParticleTexture.HEART;
                matrixStack3.translate((double)(-f4) / 2.0, (double)(-f4) / 2.0, 0.0);
                Intrinsics.checkNotNullExpressionValue(matrixStack3.peek().getPositionMatrix(), "pose(...)");
                ((BatchedBuffer)object2).vertex(matrix4f, 0.0f, f4, 0.0f).texture(particleTexture.L(), particleTexture.v()).color(color2);
                ((BatchedBuffer)object2).vertex(matrix4f, f4, f4, 0.0f).texture(particleTexture.o(), particleTexture.v()).color(color2);
                ((BatchedBuffer)object2).vertex(matrix4f, f4, 0.0f, 0.0f).texture(particleTexture.o(), particleTexture.w()).color(color2);
                ((BatchedBuffer)object2).vertex(matrix4f, 0.0f, 0.0f, 0.0f).texture(particleTexture.L(), particleTexture.w()).color(color2);
                matrixStack3.pop();
            }
        }
        BatchedBuffer.draw$default(l2, (BatchedBuffer)object2, Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlendDepthtest(), null, null, render3DEvent.getStack().peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView())), 22, null);
        render3DEvent.getStack().pop();
    }

    private static final boolean settingVisible() {
        return INSTANCE.getLmode() == ColorMode.CUSTOM;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Absorption.a = ZkmKeys.a(2681422077177707976L, -6682528004809586270L, MethodHandles.lookup().lookupClass()).a(167340836421334L);
                        var20 = Absorption.a ^ 1110460417283L;
                        Absorption.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[14];
                        var16_4 = 0;
                        var15_5 = "\u00a3\u00c1,\u0086\t\u00a4\u009d8A< \u00b7\u00976]\u00bbjD7Q\u000fUA\u00aa\n6\u00ecK\u00e0\u009d-,\u009f\u0010\u009f-\u00b49\u00d4K(W\u008fS\u00fc\u00e5\u00d1q\u00b1\u00f27F\u001dcy\u00f5eS\u001dh\u00c3\u00195\u0011X\u001ey\u009a\u00d5t\u00de\u00d9\u00c7\u00f8q\u0084\u00be\u0006\u00bfD0\u0010Y8sE\u001c\u00d9e\u00ef\u0088k1\u0094E\u00137H\u0098\u00c2\u0095|*U\u00a7\u008d8\u00dde\u0017g\u0013v\t\u008fY\u00ca\u00b4\u00f6\u0013597:\u00f4\u00c1iI\"\u00e7yQ0\u00ed\u00a4\fBi.\u0005/\u0089\u00d4q\u00c70=JF&\u00fe\u00a1\u00a683FQ\u00f2E3\u00e3\u00b5\t\u0003\u00f7b4\u00eb\u00feO\u0084\u00fb\u0010nOR\u0005\u009f[\u00bemkL\u0015\u00acH\u00aax f\u00a2fp\u0095'\u0016X\u0088\u000e\u000eSY\u00b2\u0083\u00f8\u00d0\u0082\u00f5iZ\u00fa\u009dRp\u0086\u00e9\u0018\\d\u000b\u00c8!<\u00acE,\u0003\u00e0cP?\u00ae\u001dL))\u000b\u008d2\u00e4\u00c3\u00a0\u00a3B\u00c7\u007f\u0085\u00c2\u0013\u00cf\u0082 m\u00a0\u009fM\u00d8\u0018|\u00db\u00af\u00dc\u008e*\u00f6H\u00ad\u001d\u00a2\u0089c?\u0013\u00ad!\u0080R*bV\u0086+\u00f1\u00da\u0010\u00d1N\u00df\u00a7X\u007f\u00ce\u00de3\u00c5\u00b2\u0098u\u00bf^\u00c9\u0018\u00d9$\u0084N\u00ff\u00ee=\u00a5\u00c9G:\u00c0zg2\u00eeL\u0016\u009eH\u00f9\u0086\n\u00fd\u0010\u008b\u00c67\u00f2\u00f5\u00e2\u00fe\u0000\u00af\t\u00ed\u0091\u001cD\u0093\u0014\u0010\u00f8\u00e7\u00b5\u00a4\u0001eG\u0096\u00ad9\u00cc\u00bd\u00af\u001a\u00f0\n\u0018\u00eb)\u00ac\u0097j[\u0007\u001b\u001dP\u001d\u00bd\f~\u00ff\u00fbH\u000b\u00b1\u00d8,\u00f5|\u00c48\u00e4k\u0090}\u00e5u\u00a3\u009e<\u00f0\u00e5\u00c6\u0012UQnA\u008b\u00e0\u0015|!,+\u00c5\f$\u00dbG\u0017\u00d5d\u00fa\u00be\u00cb\u0089\u00a4$\u00169\u009aQG\u009fv\u00b0\u00fbY0HT\u0014\u00a4B5' \u009c\u00c5\u00b7K\u00b2\u00fe\u00cc\u00e7\u0084\u00d7\u007f;\f*\u000brh\u0083\u00c1\u0005\u00a2\u00cd\u00bdx5$\u00c1gO\u0018\u0088\u00a1";
                        var17_6 = 475;
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
                            var18_3[var16_4++] = Absorption.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u001f\u007f\u0087\u00ae/Z<\u0093\u0016\u00ea\u001f@\u0086\u00f6.\u00c9\u00c9\u0080\u00d9\u001e\u00a81U<lIPj\u0098\u00e9w\u00f5\t6\u00e9\u00f5\u00a8\f\u001a>\u00c1\u00f1-\u00b19H\u0089\u00f70R\u0004n\u008e\u001cp+RbiW\u00f6T\u00f9\u00c3\u00e5\u00d0\u00e6z\u00de\u00a4p\u00c3\u009ap\b\u00c0\u0016d\u009a\u00df\u008e\u00c5s\u00a7/\u00e9\u00a2\u00f12\u00cb\u00b1\u0084\u00cf\u00d6\u00a2\u00e6\u00e1";
                            var17_6 = 97;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = Absorption.zkmDecodeUtf8(var19_9).intern();
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
                Absorption.b = var18_3;
                Absorption.c = new String[14];
                Absorption.g = new HashMap<K, V>(13);
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
                var6_12 = new long[8];
                var3_13 = 0;
                var4_14 = "\u00b3\u0081J\u00c5\u0089\u00f2\u00ec\u0087n\u0005\u00fe\u00c3ra%e\u00a4^w\u00f6B\u00f9l\u00d1i\u0092:p\u00e9\u00bb\u00e7\u0012\u0003\u0098\u00d5\u00c2\u00e0\u001aU\u0087\u00925\u00a5O\u0081?.\u0084";
                var5_15 = 48;
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
                    var4_14 = "\u00ac\u0087{6\u00e9\u0093\u007fUl\u0082\u00eb\u0018&\u00ff\u000eT";
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
        Absorption.e = var6_12;
        Absorption.f = new Integer[8];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Absorption.class, "lmode", "getLmode()Lsu/catlean/client/features/modules/visual/cosmetic/particles/Particles$ColorMode;", 0)), Reflection.property1(new PropertyReference1Impl(Absorption.class, "color", "getColor()Ljava/awt/Color;", 0))};
        Absorption.delegatedProperties = var30_20;
        Absorption.INSTANCE = new Absorption();
        Absorption.lmode$delegate = SettingFactory.modeSetting$default((SettingContainer)Absorption.INSTANCE, "color-mode", (Enum)ColorMode.CUSTOM, null, null, 12, null);
        v15 = Absorption.INSTANCE;
        v16 = Color.ORANGE;
        Intrinsics.checkNotNullExpressionValue(v16, "ORANGE");
        Absorption.color$delegate = SettingFactory.colorSetting$default(v15, "color", v16, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Absorption.random = ThreadLocalRandom.current();
        Absorption.entries = new ArrayList<E>();
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x516C;
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
                throw new RuntimeException("su/catlean/kw", exception);
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
            Absorption.c[n2] = Absorption.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Absorption.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1765;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kw", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Absorption.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Absorption.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Absorption.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Absorption.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

