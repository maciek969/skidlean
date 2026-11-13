/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Quaternionf
 *  org.joml.Quaternionfc
 */
package su.catlean.module.esp;

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
import java.util.HashMap;
import java.util.Map;
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
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.esp.HoleEspMode;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.GlowTexture;
import su.catlean.render.MatrixUtil;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.Hole;
import su.catlean.util.HoleManager;
import su.catlean.util.HoleSafety;
import su.catlean.util.MovementUtil;
import su.catlean.xz;
import su.catlean.zkm.ZkmKeys;

public final class HoleEsp
extends Module {
    @NotNull
    public static final HoleEsp INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final IntSetting rangeXZ$delegate;
    @NotNull
    private static final IntSetting rangeY$delegate;
    @NotNull
    private static final FloatSetting height$delegate;
    @NotNull
    private static final BooleanSetting rangeFade$delegate;
    @NotNull
    private static final ColorSetting bedrockColor$delegate;
    @NotNull
    private static final ColorSetting indestrictibleColor$delegate;
    @NotNull
    private static final BooleanSetting bloomAnimation$delegate;
    @NotNull
    private static final FloatSetting bloomSkew$delegate;
    @NotNull
    private static final FloatSetting distribution$delegate;
    @NotNull
    private static final IntSetting amount$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private HoleEsp() {
        super("hole-esp", Categories.getEsp(), null, null);
    }

    private final HoleEspMode getMode() {
        return (HoleEspMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    public final int getRangeXZ() {
        return ((Number)rangeXZ$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    public final int getRangeY() {
        return ((Number)rangeY$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final float getHeight() {
        return ((Number)height$delegate.getValue(this, delegatedProperties[3])).floatValue();
    }

    private final boolean getRangeFade() {
        return (Boolean)rangeFade$delegate.getValue(this, delegatedProperties[4]);
    }

    private final Color getBedrockColor() {
        return (Color)bedrockColor$delegate.getValue(this, delegatedProperties[5]);
    }

    private final Color getIndestrictibleColor() {
        return (Color)indestrictibleColor$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getBloomAnimation() {
        return (Boolean)bloomAnimation$delegate.getValue(this, delegatedProperties[7]);
    }

    private final float getBloomSkew() {
        return ((Number)bloomSkew$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final float getDistribution() {
        return ((Number)distribution$delegate.getValue(this, delegatedProperties[9])).floatValue();
    }

    private final int getAmount() {
        return ((Number)amount$delegate.getValue(this, delegatedProperties[10])).intValue();
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        long l = a ^ 0x1044C3DAD205L;
        long l2 = l ^ 0x712B5261B4FEL;
        if (this.getMode() == HoleEspMode.BLOOM) {
            this.renderHoles(render3DEvent.getStack(), l2);
            return;
        }
        for (Hole hole : HoleManager.INSTANCE.getHoles()) {
            Box box = hole.getRenderBox(this.getHeight());
            Color color = hole.getSafety() == HoleSafety.UNSAFE ? this.getIndestrictibleColor() : this.getBedrockColor();
            switch (xz.h[this.getMode().ordinal()]) {
                case 1: {
                    Object var39_14 = null;
                    Color color2 = null;
                    Color color3 = HoleEsp.getFadedColor$default(this, box, color, null);
                    Box box2 = box;
                    RenderState renderState = RenderState.INSTANCE;
                    boolean bl = RenderState.addFilledBox$default(renderState, box2, color3, color2, var39_14);
                    break;
                }
                case 2: {
                    boolean bl = RenderState.addBox$default(RenderState.INSTANCE, box, HoleEsp.getFadedColor$default(this, box, color, null), null, null);
                    break;
                }
                case 3: {
                    Object var47_19 = null;
                    Color color4 = null;
                    Color color5 = HoleEsp.getFadedColor$default(this, box, color, null);
                    Box box3 = box;
                    boolean bl = RenderState.addOutlineBox$default(RenderState.INSTANCE, box3, color5, color4, var47_19);
                    break;
                }
                case 4: {
                    Object var54_23 = null;
                    Color color6 = null;
                    Color color7 = HoleEsp.getFadedColor$default(this, box, color, null);
                    Box box4 = box;
                    RenderState.addOutlineBox$default(RenderState.INSTANCE, box4, color7, color6, var54_23);
                    Object var61_27 = null;
                    Color color8 = null;
                    Color color9 = HoleEsp.getFadedColor$default(this, box, color, null);
                    Box box5 = box;
                    RenderState renderState = RenderState.INSTANCE;
                    boolean bl = RenderState.addFilledBox$default(renderState, box5, color9, color8, var61_27);
                    break;
                }
                case 5: {
                    RenderState.INSTANCE.addFilledBox(box, this.getFadedColor(box, color, 60), this.getFadedColor(box, color, 0));
                    Color color10 = HoleEsp.getFadedColor$default(this, box, color, null);
                    Direction direction = Direction.DOWN;
                    Box box6 = box;
                    boolean bl = RenderState.INSTANCE.addFace2(box6, direction, color10);
                    break;
                }
                case 6: {
                    return;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
    }

    private final void renderHoles(MatrixStack matrixStack, long l) {
        Object object2;
        long l2 = (l = a ^ l) ^ 0x76F1D15230ACL;
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 4;
        boolean bl = false;
        int n2 = 100 * HoleManager.INSTANCE.getHoles().size();
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n2, bl, n, defaultConstructorMarker);
        MatrixStack matrixStack2 = new MatrixStack();
        Quaternionf quaternionf = new Quaternionf().rotateYXZ(-RenderState.INSTANCE.getCameraYaw2(), RenderState.INSTANCE.getCameraPitch(), 0.0f);
        Quaternionf quaternionf2 = RotationAxis.POSITIVE_X.rotation(RenderState.INSTANCE.getCameraPitch());
        Intrinsics.checkNotNullExpressionValue(quaternionf2, "rotation(...)");
        MatrixUtil.multiply(matrixStack2, (Quaternionfc)quaternionf2);
        Quaternionf quaternionf3 = RotationAxis.POSITIVE_Y.rotation(RenderState.INSTANCE.getCameraYaw());
        Intrinsics.checkNotNullExpressionValue(quaternionf3, "rotation(...)");
        MatrixUtil.multiply(matrixStack2, (Quaternionfc)quaternionf3);
        Object var53_12 = null;
        for (Object object2 : HoleManager.INSTANCE.getHoles()) {
            Camera camera;
            Color color;
            Box box = ((Hole)object2).getRenderBox(this.getHeight());
            Color color2 = color = ((Hole)object2).getSafety() == HoleSafety.UNSAFE ? this.getIndestrictibleColor() : this.getBedrockColor();
            if (!RenderState.INSTANCE.getFrustum().isVisible(box)) continue;
            Color color3 = HoleEsp.getFadedColor$default(this, box, color, null);
            Intrinsics.checkNotNullExpressionValue(Globals.getMc().gameRenderer.getCamera(), "getMainCamera(...)");
            double d = box.getCenter().x - camera.getCameraPos().x;
            double d2 = box.minY - camera.getCameraPos().y;
            double d3 = box.getCenter().z - camera.getCameraPos().z;
            double d4 = 1.0;
            float f = 0.16f;
            ParticleTexture particleTexture = ParticleTexture.FIREFLY;
            block1: for (int i = 0; i < 6; ++i) {
                int n3 = 0;
                int n4 = 48;
                while (true) {
                    float f2;
                    float f3;
                    float f4;
                    float f5;
                    float f6;
                    float f7 = RenderUtil.INSTANCE.getAlpha(color3);
                    if (this.getBloomAnimation()) {
                        f6 = RenderUtil.INSTANCE.interpolate((float)Globals.getPlayer().age - 1.0f, Globals.getPlayer().age);
                        f5 = 49.0f / (float)this.getAmount();
                        float f8 = (f6 + (float)n3) / f5;
                        f8 -= (float)Math.floor(f8);
                        float f9 = -this.getBloomSkew();
                        f4 = Math.abs(f8 - f9);
                        f4 = Math.min(f4, 1.0f - f4);
                        f3 = f4 / this.getDistribution();
                        f2 = (float)Math.exp(-(f3 * f3) / 2.0f);
                        f7 = RangesKt.coerceIn(f2, 0.0f, 1.0f) * f7;
                    }
                    if (!(f7 < 0.01f)) {
                        Matrix4f matrix4f;
                        float f10;
                        double d5 = ((double)((float)n3 / 1.5f) + d4) * 11.0 % 360.0 / 360.0;
                        f6 = 0.45f * (float)box.getLengthX();
                        f4 = 4.0f * (f6 + (f5 = 0.45f * (float)box.getLengthZ()));
                        f3 = (float)d5 * f4;
                        if (f3 < 2.0f * f6) {
                            f2 = -f6 + f3;
                            f10 = f5;
                        } else if (f3 < 2.0f * f6 + 2.0f * f5) {
                            f2 = f6;
                            f10 = f5 - (f3 -= 2.0f * f6);
                        } else if (f3 < 4.0f * f6 + 2.0f * f5) {
                            f2 = f6 - (f3 -= 2.0f * f6 + 2.0f * f5);
                            f10 = -f5;
                        } else {
                            f2 = -f6;
                            f10 = -f5 + (f3 -= 4.0f * f6 + 2.0f * f5);
                        }
                        matrixStack2.push();
                        matrixStack2.translate(d + (double)f2, d2 + (double)((float)i / 15.0f), d3 + (double)f10);
                        Intrinsics.checkNotNull(quaternionf);
                        MatrixUtil.multiply(matrixStack2, (Quaternionfc)quaternionf);
                        Intrinsics.checkNotNullExpressionValue(matrixStack2.peek().getPositionMatrix(), "pose(...)");
                        float f11 = 1.0f / (float)(i + 1) * f7;
                        Color color4 = RenderUtil.INSTANCE.withAlpha(color3, f11);
                        batchedBuffer.vertex(matrix4f, -0.16f, f, 0.0f).texture(particleTexture.o(), particleTexture.w()).color(color4);
                        batchedBuffer.vertex(matrix4f, f, f, 0.0f).texture(particleTexture.L(), particleTexture.w()).color(color4);
                        batchedBuffer.vertex(matrix4f, f, -0.16f, 0.0f).texture(particleTexture.L(), particleTexture.v()).color(color4);
                        batchedBuffer.vertex(matrix4f, -0.16f, -0.16f, 0.0f).texture(particleTexture.o(), particleTexture.v()).color(color4);
                        matrixStack2.pop();
                    }
                    if (n3 == n4) continue block1;
                    ++n3;
                }
            }
        }
        Camera camera = Globals.getMc().gameRenderer.getCamera();
        Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
        Camera camera2 = camera;
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
        object2 = gpuTextureView;
        matrixStack.push();
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-camera2.getYaw() + 180.0f));
        matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-camera2.getPitch()));
        BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend(), null, null, matrixStack.peek().getPositionMatrix(), null, MapsKt.mapOf(TuplesKt.to("Sampler0", object2)), 22, null);
        matrixStack.pop();
    }

    private final Color getFadedColor(Box box, Color color, int n) {
        if (!this.getRangeFade()) {
            float f = (float)n / 255.0f;
            return RenderUtil.INSTANCE.withAlpha(color, f);
        }
        float f = (float)this.getRangeXZ() * 0.5f;
        float f2 = MovementUtil.INSTANCE.horizontalDistance(box.getCenter().getX(), box.getCenter().getZ());
        float f3 = f2 <= f ? 1.0f : RangesKt.coerceIn(1.0f - (f2 - f) / f, 0.0f, 1.0f);
        float f4 = (float)((double)f3 * ((double)n / 255.0));
        return RenderUtil.INSTANCE.withAlpha(color, f4);
    }

    static Color getFadedColor$default(HoleEsp holeEsp, Box box, Color color, Object object) {
        int n = color.getAlpha();
        return holeEsp.getFadedColor(box, color, n);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getBloomAnimation();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getBloomAnimation();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getBloomAnimation();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        HoleEsp.a = ZkmKeys.a(4696067619984066282L, 3508764399116935613L, MethodHandles.lookup().lookupClass()).a(86927432214438L);
                        var20 = HoleEsp.a ^ 120737098428936L;
                        HoleEsp.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[42];
                        var16_4 = 0;
                        var15_5 = "P\u00f2\u00af\u00bfx>\u00a0Z9\u00a3\u00d3(\n(w\u009d\u0010\u000f:^\u000e,:\u0007\u00a0m\u00a6\u00b8}\u00fa\u00d3\u0088R\u000b\u00b5\u00d5\u00eb<\u00e7\u0092 \u00c9\u0094\\\u0096\u001bc\u00e8]Y\u00c1+\u00adx\u00d3\u00a8\u00f6\u0017\u0091\u00e7]\u00c6h/\u00c1\u007f\u0003\u00bc\u00f7|\u00ae\u0004\u0017\u0018UA\u00e3\u00f0_pD\u00f2B\u0003}\u009264\u008b\u00aal\u00ad\u00d7\u00f1=S\u00e6\u00d3 \u00a5\u00e4K\u00b6\u009f\u00cd\u00dcV\u00b1d\u00b9\u0000\u009c\u00ab\u00a4*;\u00a0A\u009b\u00cd\u00d2.\"\u00e2\u001d\u0081\u0085_\u00c0\u00e4\u00e5\u0010@\u0099\u009f\u00f6\u00f7\u0085>\u00d3\u0094tj\u00e4\u00f0\u00a2\u00d6\u001a(~\u00ff\u00fan\u00a3x\u0096\u00e9xr\u00a3\u001f\u00d0G\"\u00a8\u00db\u00f3\u0015)\u0016|\u00cd\u0001\u0098j\u00d5W^\u0013\u0088\u00baD\u00e8\u00deZ\u00a6?v\u00c8(\u00e5Z\u00c9\u00bb\u0011gK\u0093\u0012\u00fa\u0003\u0012\u0080\u00e5\u00a5,\u00e7uw\u00d4\u00b9\u0019\u008ci\u0098_TD\u0099\u0094\u00af\u00c8M$<\u0097B\u008e%\u00af\u0010\u00b7wa\u00c0\u00fc\u0090@e\u0090Kiw\u00dc\u00b8&:\u0018\u00cc\u008c\u0094`Q\u00d7l^>\rjYFR\u0098\u00c5#A\u008d\u00a3D\u00bf\u00fdK\u00104\u00a4\u009c\u00f4\u00da\u00ce<2\u000b\u00c6\u00f6E\u0017\u0016\u001b\u008b\u0010\u0090\u009f\u00b1V\u0011m\u00dc\u0086\"\u00e5\u0014\u00e7\u00dd\u00e8</8\u008a\u00b8\u00f1\u000b\u00e7ACB\u0019>{\u00e5\u008e\u00d9\u00b2f\u001aQB\\\u0097\u00b5\u008a{(\u00dc\u00d4D\u008a\u00bfS\u00ac\u00be\u009f\u009b\u00e9$\u00d21\u00fe\u00e6?SS\u00eb\u00dfT\u00a9~\u008c\u0082\u0091w\u008eQb \u00be\u0098\u0083\u00ca\u00f0\u0016\u00d9-\u0081\u00f5\u009c\u001fEI\u00f8\u0099\u001du.LJ@\u00f2\u00c9\u00fc8\u00d2\u00f9tb5A(\u00e5\u000b\u00b8\u00b81\u00e2\u00f7\u00b0\u00e7\u00fd\u00dd\u0094\u0004\u0091\u00dd\u00dd\u00d0\u0085I\u00d3N\u0093g\u00b6O\u0012\u0096\u0087\u00c2UF1\u0005O\u00c3\u00d6\u0093\u001e\u00ab\u00aa \u001e\u000e$b\u009eu\u00e0\u00ef\u0003\u001f+@\u00f9\u00e0\u00f1\u00fe\u0084\u00adl\u0003\u00c7\u0093B\u00bd\u0019\u00c2\u00ba\u00c3\u00b7B\b\u008d\u0010\u00efHeD\u00edl\u00e0\u00ccI\u0097%\u00ebA\u00c0\u009b| \u00eb\u00fb\u0006\u00ac_24\u00e2\u00e8B\u00f84/\u009a\u00acvpVN6\u0087\u0018\u00be\u0086\"\u008f]5\u00ee1~\u0003 \u0001\u00b8p\t\u00a4Z\u008f,\nY<`\u00af!\u00c36)O\u0005\"0\u0086\u00bd\u00ad<s\u00cc\u00a3\u0081\u00f6\u00ae\u0085 \u00ad\u00b5\u00b2E\u00c2\u0098M&b\f6OY\u00eb\u00f1\u00d6\u001aV\u001e\u00eb\u0000[K\u00b4\u008a\u001d\u00ec;=\u00e0\u00c5\u008a jI\u001b%\u00e6m\u0016\u00e54\u0088\u0010\u00e1\u00f9\u0018\b\u00fd\u00ee\u0013\u00c5\u00f7\u00cc\u00d3\u00d7\u00e0|L\u00e80\n\u00f6{\u0082\u0010\u0099n\u0007\u0092\f\u00del\u00a9\u00b0W\u00ad&\u00fa\u00fe\u001a\u00c1@\u0011\u0081w\u00b0\u001d\u0088\u00de7D\u001b\u0011I\u00f5\u00a1*\u00f3}%h2\u00eb0\u00b04\u00f5\u001b\u001ah\u0088\u00a8\u00b3\u0089\u0086\u00e6\u00d9\u00c3\u00ec\u00ee\u00ce\u00b5\u000e-T8\b\u001b\u0001\u00b30E\u00cd\u008f{A\f{\u00e9)\u00b76\u00b7v\u00f9^ \u0016\u0082\u00fe%\u0082\u0097\u00ffk8\u00cd\u00fd\u00d5wS\u00df\u00df`\u000ee\r\u00ech\nIMB\u00f6\u00bf\u00b9\u00b5\u00e9\u00dd \u009a\u0087\u00dd\u0014\u0014\u0002\u00d2\u0097\u0005\u00dcz\u0007~\u0019v\u0085\u0017\u00d0\u00ff\u00d3\u00e5\u00f3\u00aa\u00ddC<\u0084\u00b6\u00b1\u000b6\u000f\u0018\\_\u00ff\u00e6)\u0005\u00f4\u0082)ae\u00d4\u00c9\u0016i\u00d3,\u00f3\u00e2\u0087\u00e8\u00d7\u00bb\u00e8 \u0095j\u00e5B\u00d0\u00a1\u00a0\u00fc9l\u0000\u0003U#x\u00ce'\u00c3T\u00fe\u0004p\u0016t\u00f1\u00bf\u00db\u00c1\u00a3\u0094V\u00ce \bb\u009ca\u00b5\u00df\u0094z%%Jd\u00dc-\"\r\u0001\u008f\u008a\u001a\u00bf\u00a5!<#6\u00c1\u00c3\u00f4\u00efr\u0086\u0018U\u00f03m\u00b3bzy\u00ca\u001d\f\\\u00995\u00e5%\u00e7\u00f6\u0084\u00ff\u00c2Q\\\u00b8\u0018\u00b2\u00b7\u00e6\u00ee\u0092\u00ab\u00d4\n\u00d6\u00d4\u00d0j-\u00848\u00c09\u0095~N\u00958\u000ec H\u008a\u0012P\u00bae\u00cd%\u0085}dK\u0098\u00c1S\u001f5\u00af\u00b4/\u0080\u001e\u007f\u00c2\u0081l\u00e2\u00c28\u00c6\u00f2\u00f1\u0010~O\u00c2\u00d1\u00ca\u0015\u00e5\u0005\u0001\u00e5\u00e9\u00cat\u001e,\u00b6\u0010+1\u0084\u00f2\u00bc SJ\u00a4\u008c\u0094.\u0085\u00a9+\u00f4 s\u00d4\u00b4#\r\u0099\u00ab\n\u00e3\u00ff;\n\u00ee\u00f5\u0007j0\u0000[\u00d5BW\u0010\u00d9\u00dc1&\u0092\u009bMvlP\u00b3-\u0018\u00d2B=\u00cd{)\u0089\u008e\u0019\u00eb0`/t\u00a6=\u00a9^\"\u0084T\u00f4\u00cb\u00c3\u00f9\u00e1\u00f4x\u00a7\u00f5\u00b1L\u00e8\u009f\u00a7.?ec\tI\u00ad/Cpz\u00eb4\u008d@D\u00b2\fQ`\u00b3\u00a5\u00bdy\u0091\u00cb\\ \u0017\"\u00f7\u00e1\u00b4\u008b\u00bbn\u001c\u00f3\u008e/\u001d\u00e7 \u0016\u0015\u00ccv<\u00b8\u008b\u00b3\u008b2\u0019\u0095\u0017\u00ab\u0089@N\u0010\u00c0!I\u008eR\u00cd\u00ees\u00c5\u0090GY!o(\u009a\u00e5\u00f3\u00c0\u00f1:\u00bb3`'\u00f3\t\u00b3E\u00f6\u00982\u00ab_\u009a;\u008d\u00e1'\u00ee\u00d9w\u00b6|MN\u00ads\ni\u00cbFc\u00a8\u00de \u0088\u00c3\u00a1_\u001c\u00dc\u0091;F\u00e6Uo`\u0017\u0018\u0084<\u0098\u001a\u00836\u00deAS;\u0017\u00b2<NP+O\u0010z\u0018yM\u000e\u0081U\u00f3\u0003\u00e6k\u00b0\u00c2H\u0016L\u0010h\u0082zM*k\u00eeN\u00c5i\u00f3\u00b4:\u0005\n\u001b\u0010\u00b4Y?V\u00e0k\u00f0+\u0094\u00cdA\u001bV\u00d5\u008d\u00b6";
                        var17_6 = 1247;
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
                            var18_3[var16_4++] = HoleEsp.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "q\u00e0\u00ac\u0006x\u00c1>{J@>\u00a6\u000bN\u00b8\u0098\u00e1 \u008doG6'\u00bf\u0013\u00cf\u0015\u00bff\u001c4\u00c2 \u00d4k\u00c8b\u00c4}\u00e8#\u009b8D+Y\u00ff&\u00b0\u00be\u00f9I\u0018\u0098\u00fa\u00a1\u00e3\u00ca3\u0017\u009e\u00f5\u00ad\u001d\u009c";
                            var17_6 = 65;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = HoleEsp.zkmDecodeUtf8(var19_9).intern();
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
                HoleEsp.b = var18_3;
                HoleEsp.d = new String[42];
                HoleEsp.i = new HashMap<K, V>(13);
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
                var6_12 = new long[24];
                var3_13 = 0;
                var4_14 = "Y\u001c\u0088\u0097\u000f\u00eck\u00e7\u00fe\u00b3\f\u00f4\u00d6\u00bce\u00dd\u0099\u0013'\u00fc\u00b3\u0093\u00ca&\u0015\u000b\u00e2\u0016\u009eR?hb\u00c0\u00d2 1e\u00e0\u00ac\u00c8^\b\u00cal\"\u001f]\u00b0S`\u00d7Xw\u00b6)v\u00ed,V\u001e\u00e9\u00fb\u00cc\u001aD\u0081iO\u0093\u0018)\u00be\u00f1\u009f\u009f\bh%Q\u0011\u00c6\u009a\u0017\u0016\u009f\u00f6\u00ff\u00c9Js\u0015\u00de>\u00aaK\u00e4\u00c93\u00e0\u00c8;\u0013\u00c0\u008c\u00dab<\u00af\u00f4(\u0094\u00f3\u00ee\u00cc\u00a9\u00d2O\u00ad8\r\u0090p\u00c8\u00b0\u00cd\u00b8\u00d6\u0019\u00d0\u00b8.\u0000\u00f0U\u0085\u00a1\u001cu\u00a1\u00fd\u00bbt\u00bc\u00d4\u00991[\u001d]\u0094\u00fax\u00bcK\u008b\\\u00fcrJ%\u00b6\u0080\u00eb\u0013\u00a2\u008d\\\u00d7\u00d8J\u00b3\u00fa\u00efDA";
                var5_15 = 176;
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
                    var4_14 = "+\u00c2&\u000f\u009e\u00ba\u00a4\u00fe\\\u00cb\u0098\u0005/\u00fa\u00d3\u0012";
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
        HoleEsp.g = var6_12;
        HoleEsp.h = new Integer[24];
        var35_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "mode", "getMode()Lsu/catlean/xh;", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "rangeXZ", "getRangeXZ()I", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "rangeY", "getRangeY()I", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "height", "getHeight()F", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "rangeFade", "getRangeFade()Z", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "bedrockColor", "getBedrockColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "indestrictibleColor", "getIndestrictibleColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "bloomAnimation", "getBloomAnimation()Z", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "bloomSkew", "getBloomSkew()F", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "distribution", "getDistribution()F", 0)), Reflection.property1(new PropertyReference1Impl(HoleEsp.class, "amount", "getAmount()I", 0))};
        HoleEsp.delegatedProperties = var35_20;
        HoleEsp.INSTANCE = new HoleEsp();
        HoleEsp.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)HoleEsp.INSTANCE, "mode", (Enum)HoleEspMode.CubeOutline, null, null, 12, null);
        HoleEsp.rangeXZ$delegate = SettingFactory.intSetting$default(HoleEsp.INSTANCE, "range--x-y", 10, new IntRange(1, 32), null, null, 24, null);
        HoleEsp.rangeY$delegate = SettingFactory.intSetting$default(HoleEsp.INSTANCE, "range--y", 5, new IntRange(1, 32), null, null, 24, null);
        HoleEsp.height$delegate = SettingFactory.floatSetting$default(HoleEsp.INSTANCE, "height", 1.0f, RangesKt.rangeTo(0.01f, 5.0f), null, null, 24, null);
        HoleEsp.rangeFade$delegate = SettingFactory.booleanSetting$default((SettingContainer)HoleEsp.INSTANCE, "range-fade", true, null, null, 12, null);
        HoleEsp.bedrockColor$delegate = SettingFactory.colorSetting$default(HoleEsp.INSTANCE, "safe", new Color(65361), null, null, 12, null);
        HoleEsp.indestrictibleColor$delegate = SettingFactory.colorSetting$default(HoleEsp.INSTANCE, "unsafe", new Color(65361), null, null, 12, null);
        HoleEsp.bloomAnimation$delegate = SettingFactory.booleanSetting$default((SettingContainer)HoleEsp.INSTANCE, "bloom-animation", true, null, null, 12, null);
        HoleEsp.bloomSkew$delegate = SettingFactory.floatSetting$default(HoleEsp.INSTANCE, "skew", 0.8f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        HoleEsp.distribution$delegate = SettingFactory.floatSetting$default(HoleEsp.INSTANCE, "distribution", 0.4f, RangesKt.rangeTo(0.0f, 1.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        HoleEsp.amount$delegate = SettingFactory.intSetting$default(HoleEsp.INSTANCE, "amount", 2, new IntRange(1, 10), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2ECC;
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
                throw new RuntimeException("su/catlean/k0", exception);
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
            HoleEsp.d[n2] = HoleEsp.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HoleEsp.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/k0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x11D9;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/k0", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HoleEsp.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HoleEsp.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/k0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HoleEsp.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HoleEsp.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

