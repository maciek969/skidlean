/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Quaternionfc
 */
package su.catlean.module.cosmetic;

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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.DamageVector;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.GlowTexture;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;

public final class DamageTint
extends Module {
    @NotNull
    public static final DamageTint INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting vector$delegate;
    @NotNull
    private static final ColorSetting vectorColor$delegate;
    @NotNull
    private static final IntSetting vectorSize$delegate;
    @NotNull
    private static final BooleanSetting tint$delegate;
    @NotNull
    private static final ColorSetting tintColor$delegate;
    @NotNull
    private static final Identifier texture;
    @Nullable
    private static Vec3d n;
    @Nullable
    private static Vec3d knockbackVelocity;
    @NotNull
    private static final List vectors;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map j;

    private DamageTint() {
        super("damage-tint", Categories.getCosmetic(), null, null);
    }

    private final boolean getVector() {
        return (Boolean)vector$delegate.getValue(this, delegatedProperties[0]);
    }

    private final Color getVectorColor() {
        return (Color)vectorColor$delegate.getValue(this, delegatedProperties[1]);
    }

    private final int getVectorSize() {
        return ((Number)vectorSize$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final boolean getTint() {
        return (Boolean)tint$delegate.getValue(this, delegatedProperties[3]);
    }

    private final Color getTintColor() {
        return (Color)tintColor$delegate.getValue(this, delegatedProperties[4]);
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        long l;
        long l2 = l = a ^ 0x407DBDEF46E7L;
        long l3 = l2 ^ 0x5BE0C879725FL;
        long l4 = l3 >>> 16;
        int n = (int)(l3 << 48 >>> 48);
        long l5 = l2 ^ 0x61D1EC0B4AEEL;
        int n2 = (int)(l5 >>> 32);
        int n3 = (int)(l5 << 32 >>> 48);
        int n4 = (int)(l5 << 48 >>> 48);
        if (Globals.getPlayer().hurtTime > 0) {
            if (DamageTint.n != null) {
                Vec3d vec3d = DamageTint.n;
                Intrinsics.checkNotNull(vec3d);
                vectors.add(new DamageVector(vec3d, null, null));
                DamageTint.n = null;
            } else if (knockbackVelocity != null) {
                Vec3d vec3d = knockbackVelocity;
                Intrinsics.checkNotNull(vec3d);
                vectors.add(new DamageVector(vec3d, null, null));
                knockbackVelocity = null;
            }
        }
        if (this.getTint()) {
            this.renderTint(l4, 10.0f, 2.0f, (short)n);
        }
        if (this.getVector()) {
            for (DamageVector damageVector : vectors) {
                damageVector.getAnimation().animate(1.0f);
                float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f;
                float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f;
                MatrixStack matrixStack = new MatrixStack();
                float f3 = Rotation.Companion.to(damageVector.getVec()).getYaw() - Globals.getPlayer().getYaw();
                matrixStack.translate(f, f2, 0.0f);
                matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(f3));
                matrixStack.translate(-f, -f2, 0.0f);
                float f4 = 1.0f - damageVector.getAnimation().getValue();
                this.drawDirectionVector(matrixStack, f, f2, n2, this.getVectorSize(), n3, (char)n4, RenderUtil.INSTANCE.withAlpha(this.getVectorColor(), f4));
            }
        }
        vectors.removeIf(arg_0 -> DamageTint.invokePredicate(DamageTint::onRender2D$lambda$0, arg_0));
    }

    public final void drawDirectionVector(@NotNull MatrixStack matrixStack, float f, float f2, int n, float f3, int n2, char c, @NotNull Color color) {
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)c << 48 >>> 48) ^ a;
        long l2 = l ^ 0x6A45B6C2EC59L;
        Intrinsics.checkNotNullParameter(matrixStack, "matrices");
        Intrinsics.checkNotNullParameter(color, "color");
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        Intrinsics.checkNotNullExpressionValue(matrix4f, "pose(...)");
        Matrix4f matrix4f2 = matrix4f;
        RenderUtil.INSTANCE.drawTexture3D(matrix4f2, GlowTexture.getIdentifier(), f, f2, f3, f3, color, color, color, color, true, ParticleTexture.HALO.o(), ParticleTexture.HALO.v(), ParticleTexture.HALO.L(), l2, ParticleTexture.HALO.w());
    }

    public final void renderTint(long l, float f, float f2, short s) {
        long l2 = (l << 16 | (long)s << 48 >>> 48) ^ a;
        long l3 = l2 ^ 0xC03357F62EFL;
        float f3 = Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount();
        boolean bl = f3 <= f;
        float f4 = Math.abs((bl ? f3 / f : 1.0f) - 1.0f) * f2;
        if (f3 <= 4.0f) {
            f4 += Math.abs(0.2f * (float)Math.sin((float)Math.toRadians((double)Globals.getPlayer().age * 16.0 + (double)RenderUtil.INSTANCE.getTickDelta())));
        }
        f4 = RangesKt.coerceIn(f4, 0.0f, 1.0f);
        Color color = new Color((int)((1.0f - RenderUtil.INSTANCE.getRed(this.getTintColor())) * f4 * 255.0f), (int)((1.0f - RenderUtil.INSTANCE.getGreen(this.getTintColor())) * f4 * 255.0f), (int)((1.0f - RenderUtil.INSTANCE.getBlue(this.getTintColor())) * f4 * 255.0f), 255);
        float f5 = Globals.getMc().getWindow().getScaledWidth();
        float f6 = Globals.getMc().getWindow().getScaledHeight();
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(texture).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        GpuTextureView gpuTextureView2 = gpuTextureView;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 6;
        boolean bl2 = false;
        int n2 = 0;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl2, n, defaultConstructorMarker);
        BatchedBuffer.vertex$default(batchedBuffer, 0.0f, f6, 0.0f, null).texture(0.0f, 1.0f).color(color);
        BatchedBuffer.vertex$default(batchedBuffer, f5, f6, 0.0f, null).texture(1.0f, 1.0f).color(color);
        BatchedBuffer.vertex$default(batchedBuffer, f5, 0.0f, 0.0f, null).texture(1.0f, 0.0f).color(color);
        BatchedBuffer.vertex$default(batchedBuffer, 0.0f, 0.0f, 0.0f, null).texture(0.0f, 0.0f).color(color);
        BatchedBuffer.draw$default(l3, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend2(), null, null, null, null, MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2)), 30, null);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        Packet<?> packet;
        if (receivePacket.getPacket() instanceof EntityVelocityUpdateS2CPacket && ((EntityVelocityUpdateS2CPacket)(packet = receivePacket.getPacket())).getEntityId() == Globals.getPlayer().getId() && !(((EntityVelocityUpdateS2CPacket)packet).getVelocity().x == 0.0) && !(((EntityVelocityUpdateS2CPacket)packet).getVelocity().z == 0.0)) {
            Entity entity = Globals.getPlayer();
            knockbackVelocity = new Vec3d(-((EntityVelocityUpdateS2CPacket)packet).getVelocity().x, ((EntityVelocityUpdateS2CPacket)packet).getVelocity().y, -((EntityVelocityUpdateS2CPacket)packet).getVelocity().z).add(this.getPos(entity));
        }
        if (receivePacket.getPacket() instanceof ExplosionS2CPacket) {
            packet = receivePacket.getPacket();
            n = new Vec3d(((ExplosionS2CPacket)packet).comp_2883().x, ((ExplosionS2CPacket)packet).comp_2883().y, ((ExplosionS2CPacket)packet).comp_2883().z);
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getVector();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getVector();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getTint();
    }

    private static final boolean onRender2D$lambda$0(DamageVector damageVector) {
        Intrinsics.checkNotNullParameter(damageVector, "it");
        return damageVector.getAnimation().x();
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
                        DamageTint.a = ZkmKeys.a(-7427343753510430064L, -7004497900810951094L, MethodHandles.lookup().lookupClass()).a(173270329073630L);
                        var20 = DamageTint.a ^ 126034206619642L;
                        DamageTint.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[27];
                        var16_4 = 0;
                        var15_5 = "RFR\u0011\u0017\u00be\u001e!u\u00c1\u0093\u00aa\u00da\u00d5?;\u0098\u00a3\u00ae\u0004\u00b0\u00f8|\u00c1\u0081iv\u001cE\u0088T\u00ac P\u00d9~f\u00aa|B\u0001\u0089xX\u00c4\u0012;i\u008f\u00fe\u001c\u0096\u0083BDc\u0005\u00e8\n\u00b3\u00d5\u008cH\u0019\u00f3 \u00ab\u00fd\u008b\u00da\u00c0{*\u001c3\u0085\u00ff5p\u00de\u0013b\u0006\u001f{\u00ad\u0006/\u009e\u009e\u00dd\u0098U\u0011\u00f6ogD \u00cd\u0001xZ\u00b5a7U\u0086\u00eaqq\u00e5\u00c8\u00dc,\u00db/\u00a9\u00d2\u001c\u0083_\u0090R\u00c0Y\u00a8\u00ff\u00ed\u0091\u00f68\u0082\u0007\u0080\u00f4?\u0095^\u00a5\u00f9\u0019l\u0099\u0019`\u0086\u0094\u00ff\n\u0016\u000bO\u00ac\u008f\u00b5d\u00f0;\u00a1\u00a7M[\u00f9\u00be\u0013\u00fe3d\t\u00a9A\u00d2`}^\u00c0wmNQC\u00936\u0019\u00c1G\u008a\u0018\u0098:p\u00b2\u000f.\u00e0\u00ec\u00d6\u00a9s\u00a9\u0090\u00ee\u00ff\u0005\u00d7\u00e7\u00ec\u00cd\u00a6\u008a\u00a9;(\u00a3`r\u0017\u0098\u00d3K\u0085{-Z\u0095\u00e3\u009e}N\\U\u00d2\u00f2\u00b6\u00e7\u008d0\u00b95\u00ca/V\u00b7DJ\u00e0\u00ab\u00c9\u00e0\u0007p\u00d2'\u0010a\u00d0\u00d8\u008b\u00e7\u00ef\"\u0010\u00a2\u00f4\b\u00b1F\u00e0\u0088\u00fc8c\u0007\u00d7\u008b?\u0013\u00b9\u00ba\u00cd]\u00f6Y\u008b_\u00e4Jaw\u00f7C\u00f4\u00aa\u00e1Q\u009c^\u00f8\u00d4\u00c9\u00bcS\u008b\u0089h\u00ebo\u00d9{\u00f4\u0095\u00d5\u0098B\u00f2\u0089\u00bb\u00e9\u00a1d\u0081b\u00d125\u0007\u00da(g\u00ec\u0016vJ\u00b6,c\u0016\u001bXg\u00a9\u0093\u0088dU:|\u00ba?\u00c6\u00ee\u00abw\u00ffa\u00d5\u001eE\u00bb>\u00dd\u0084\u00a4\u009c\u00aex\u00cb\u00ff\u0010e\u0011\u00ae\u00e3=i\u0017\u0010\u001ac\u00899\u000fd\u009az \bSP+\u0080\u00e8Gi7[d'1\u00be\u00de7R\u00a5*\u0005\u00e9\u00c6\u000b\u00fdRhf\u00c3\th\u00af\u009c\u0018\u00cd\u0019\u0096P\u00b0_\u0002;=\u00017U\u0015r\u00b4\u00afq\u00ca\b\u009c<kts(\u008a\u00f9\u00b0~\n\u00e0\u0097)\u000fb;\u0095\u00cb\u00a3\u00a4B \u00a3\u00b6AxM\u00a28\u00a3\n\u009c\u00de!\u00c2\u00cdtr{\u00c8\u00e5N`\u0005\u0013\u00107\u00e1\\\u00a4\u00a5H\u00d3@2\u00bb\u00f5\u001b\u00d8J+| @\u00ba\u00a5\u001d\u00d4\u00f6\u00f1\u00b6 Ue\u00bd.SW\u0091\u001c\u00fb\u00bd\u001b\u00bf\u00eah\u0000i\u0089\u007f\u0086a[\u00fd\u001e\u0018\u0090w\u00c6w;/&\u00c7S\u0006\u00fb\u00d2V\u0006\u000bR\u009b=\u0000\u00bc\u00efT\u0011\u00fc\u0010\u0081M\u00b7\u009a\u009bD\u0080\u0019}P\u00b1\u008a\u00e0\u00ed\u0082q@x\u00f8+\u00f4=\"9\u00d6\u0016\u00dd\u00de\u0080\u00ef\u00d7\u0000\u00d4w\u00f7\r\u0083{c\u00acR\u00ac\u0091\u00e0{\u00ab\u00a9&\u008f*\u0012\u0085\u00dc\u0090\u0006\u00c8\u0095\u009b\u00f3\u00d59x\u0087O\u0080Oe\u00c8Y|\u0012\u001f\u00dfB\u00db\u00fb\b\u00a0\u00f9\u00ef  \u008f\u00d2*\u00caY\u0015\u0011\u008eR\u00c2\u00a1\u0098\u00c8\u001fL0.\u00b5\t\u009c\u00d7\u00bc\u008e]\u0011\u00d9*o<)\u00b3\u00188\u001e,d\u00f7\u00e5\u00f9 \u0091\u009a\u00efd\t\u0089\u00cb\u0090\u00c9\u00d5\u0012\r\u00d5\u00ff\u00a9\u00fcE\u00d9\u0001\u009fPI$5\u00b9\u00da\u0013\u00fa\u00d2\nb\u00ae\u00a4P\u00f7C(\fo\u00db\u00ab\u0017Nz\u00cb%i\u00a1\u00c0\u0010X\u00cdF\u0095\u00c2\u000fU\u00aa\u00fe\u00e0\u00a4\u00f8c\u0002\u0094\u00d9\u0010sn\u00e5\u001a\u0007\u0003\u0018\u00f0\u00c3g vR\u00d5\u00d4y\u0010\u00f1\u009c\u0000\u00c4\u00b1=\u00c9h.\u00a6^|]\u00f3\u00a1\u00d5 \u00c2\u00c9p,\u009d\u00052\u00e7\u00e6\u00dd\u00fb\r]\u00a2\u00bf\u00d2,\u00e0H\u00e8\u00dfM\u0089\u0014\u0085\u0091\u001d\u00b9\u000e\u009c\u00ca+";
                        var17_6 = 816;
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
                            var18_3[var16_4++] = DamageTint.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00cc\n\u00e5\u00fa\u0005BQ \u00b1\u00c1\u00a8\u00aeG\u00dak\u00aaNa~&E\u0093\u00e2=\u0010\u00d5\u00c9\u00a3\u00f0\u0004\u00c5\u00c3\u001e\u0082\u009db\u0086p&\u00cfc";
                            var17_6 = 41;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = DamageTint.zkmDecodeUtf8(var19_9).intern();
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
                DamageTint.c = var18_3;
                DamageTint.d = new String[27];
                DamageTint.j = new HashMap<K, V>(13);
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
                var6_12 = new long[10];
                var3_13 = 0;
                var4_14 = "Z\u0005\u00f9\u00e3\u00e1<\u0080\u00bd{\u0012\u00bf\u00f8\u00da\f\u0094\u008a\u0084Ec\u00fb~\u00dcK\u001d\u00b5G>_\u00ffU\u009a\u0089}8~\tw\u0010\u009eMM!\u00c7e\u00c8\u00e7\u0011\u00a5\u00bdF\u0010\u00e1nR\u00d3nE0\u00ecj\u00cbG\u008c!";
                var5_15 = 64;
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
                    var4_14 = "\u00eePP=\u00d5\u00a3\u00c7\u00f5m\u00da\u00da\u00e7\u001c\u009f\u0095\u00f6";
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
        DamageTint.g = var6_12;
        DamageTint.h = new Integer[10];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(DamageTint.class, "vector", "getVector()Z", 0)), Reflection.property1(new PropertyReference1Impl(DamageTint.class, "vectorColor", "getVectorColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(DamageTint.class, "vectorSize", "getVectorSize()I", 0)), Reflection.property1(new PropertyReference1Impl(DamageTint.class, "tint", "getTint()Z", 0)), Reflection.property1(new PropertyReference1Impl(DamageTint.class, "tintColor", "getTintColor()Ljava/awt/Color;", 0))};
        DamageTint.delegatedProperties = var32_20;
        DamageTint.INSTANCE = new DamageTint();
        DamageTint.vector$delegate = SettingFactory.booleanSetting$default((SettingContainer)DamageTint.INSTANCE, "vector", true, null, null, 12, null);
        v15 = DamageTint.INSTANCE;
        v16 = Color.RED;
        Intrinsics.checkNotNullExpressionValue(v16, "RED");
        DamageTint.vectorColor$delegate = SettingFactory.colorSetting$default(v15, "vector-color", v16, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        DamageTint.vectorSize$delegate = SettingFactory.intSetting$default(DamageTint.INSTANCE, "vector-size", 100, new IntRange(10, 500), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        DamageTint.tint$delegate = SettingFactory.booleanSetting$default((SettingContainer)DamageTint.INSTANCE, "tint", true, null, null, 12, null);
        v17 = DamageTint.INSTANCE;
        v18 = Color.RED;
        Intrinsics.checkNotNullExpressionValue(v18, "RED");
        DamageTint.tintColor$delegate = SettingFactory.colorSetting$default(v17, "tint-color", v18, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        v19 = Identifier.ofVanilla("textures/misc/vignette.png");
        Intrinsics.checkNotNullExpressionValue(v19, "withDefaultNamespace(...)");
        DamageTint.texture = v19;
        DamageTint.vectors = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5716;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kb", exception);
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
            DamageTint.d[n2] = DamageTint.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DamageTint.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x70C8;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/kb", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            DamageTint.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = DamageTint.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DamageTint.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(DamageTint.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

