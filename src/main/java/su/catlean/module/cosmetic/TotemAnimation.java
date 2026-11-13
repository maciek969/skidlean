/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Quaternionfc
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.ItemCommandEvent;
import su.catlean.api.event.events.render.RenderFloatingItemEvent;
import su.catlean.api.event.events.render.ShowFloatingItemEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.TotemAnimation$WhenMappings;
import su.catlean.module.cosmetic.TotemAnimationMode;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.ColoredPolygonConsumer;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderUtil;
import su.catlean.render.SingleBufferProvider2;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class TotemAnimation
extends Module {
    @NotNull
    public static final TotemAnimation INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final IntSetting speed$delegate;
    @Nullable
    private static ItemStack totemItem;
    private static int G;
    @Nullable
    private static VertexConsumerProvider vertexConsumers;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    private TotemAnimation() {
        super("totem-animation", Categories.getCosmetic(), null, null);
    }

    private final TotemAnimationMode getMode() {
        return (TotemAnimationMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final int getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    @Flow
    private final void onShowFloatingItem(ShowFloatingItemEvent showFloatingItemEvent) {
        totemItem = showFloatingItemEvent.getItem();
        G = this.getAnimationDuration();
        showFloatingItemEvent.cancel();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block0: {
            if (G <= 0 || (G += -1) != 0) break block0;
            totemItem = null;
        }
    }

    @Flow
    private final void onRenderFloatingItem(RenderFloatingItemEvent renderFloatingItemEvent) {
        long l = a ^ 0x536D7C1DC6AEL;
        long l2 = l ^ 0x7B142F39467EL;
        if (totemItem != null && G > 0 && this.getMode() != TotemAnimationMode.Off) {
            int n = Globals.getMc().getWindow().getScaledWidth();
            int n2 = Globals.getMc().getWindow().getScaledHeight();
            int n3 = this.getAnimationDuration() - G;
            float f = ((float)n3 + RenderUtil.INSTANCE.getTickDelta()) / (float)this.getAnimationDuration();
            float f2 = f * f;
            float f3 = f * f2;
            float f4 = 10.25f * f3 * f2 - 24.95f * f2 * f2 + 25.5f * f3 - 13.8f * f2 + 4.0f * f;
            float f5 = f4 * (float)Math.PI;
            MatrixStack matrixStack = new MatrixStack();
            matrixStack.push();
            float f6 = (float)n3 + RenderUtil.INSTANCE.getTickDelta();
            float f7 = 50.0f + 175.0f * (float)Math.sin(f5);
            switch (TotemAnimation$WhenMappings.g[this.getMode().ordinal()]) {
                case 1: {
                    float f8 = (float)(Math.sin(f6 * 112.0f / 180.0f) * 100.0);
                    float f9 = (float)(Math.cos(f6 * 112.0f / 180.0f) * 50.0);
                    matrixStack.translate((float)(n / 2) + f8, (float)(n2 / 2) + f9, -50.0f);
                    matrixStack.scale(f7, -f7, f7);
                    break;
                }
                case 2: {
                    matrixStack.translate(n / 2, n2 / 2, -50.0f);
                    matrixStack.scale(f7, -f7, f7);
                    break;
                }
                case 3: {
                    matrixStack.translate(n / 2, n2 / 2, -50.0f);
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(f6 * 2.0f));
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(f6 * 2.0f));
                    matrixStack.scale(200.0f - f6 * 1.5f, -200.0f + f6 * 1.5f, 200.0f - f6 * 1.5f);
                    break;
                }
                case 4: {
                    matrixStack.translate(n / 2, n2 / 2, -50.0f);
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(f6 * 3.0f));
                    matrixStack.scale(200.0f - f6 * 1.5f, -200.0f + f6 * 1.5f, 200.0f - f6 * 1.5f);
                    break;
                }
                case 5: {
                    float f8 = (float)(Math.pow(f6, 3.0) * (double)0.2f);
                    matrixStack.translate(n / 2, (float)(n2 / 2) + f8, -50.0f);
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(f6 * 5.0f));
                    matrixStack.scale(200.0f - f6 * 1.5f, -200.0f + f6 * 1.5f, 200.0f - f6 * 1.5f);
                    break;
                }
                case 6: {
                    float f8 = (float)(Math.pow(f6, 3.0) * (double)0.2f) - 20.0f;
                    matrixStack.translate(n / 2, (float)(n2 / 2) - f8, -50.0f);
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(f6 * (float)G * 2.0f));
                    matrixStack.scale(200.0f - f6 * 1.5f, -200.0f + f6 * 1.5f, 200.0f - f6 * 1.5f);
                    break;
                }
                case 7: {
                    float f8 = (float)(Math.pow(f6, 2.0) * 4.5);
                    matrixStack.translate((float)(n / 2) + f8, n2 / 2, -50.0f);
                    matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(f6 * 40.0f));
                    matrixStack.scale(200.0f - f6 * 1.5f, -200.0f + f6 * 1.5f, 200.0f - f6 * 1.5f);
                    break;
                }
                case 8: {
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
            VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
            Intrinsics.checkNotNullExpressionValue(vertexFormat, "POSITION_TEX_COLOR");
            DefaultConstructorMarker defaultConstructorMarker = null;
            int n4 = 4;
            boolean bl = false;
            int n5 = 1024;
            VertexFormat vertexFormat2 = vertexFormat;
            BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n5, bl, n4, defaultConstructorMarker);
            Color color = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
            float f10 = 1.0f - f;
            Color color2 = RenderUtil.INSTANCE.withAlpha(color, f10).darker();
            Intrinsics.checkNotNullExpressionValue(color2, "darker(...)");
            Color color3 = color2;
            BatchedBuffer batchedBuffer2 = batchedBuffer;
            ColoredPolygonConsumer coloredPolygonConsumer = new ColoredPolygonConsumer(batchedBuffer2, color3);
            vertexConsumers = new SingleBufferProvider2(coloredPolygonConsumer);
            ItemRenderState itemRenderState = new ItemRenderState();
            ItemModelManager itemModelManager = Globals.getMc().getItemModelManager();
            ItemStack itemStack = totemItem;
            Intrinsics.checkNotNull(itemStack);
            itemModelManager.clearAndUpdate(itemRenderState, itemStack.getItem().getDefaultStack(), ItemDisplayContext.FIXED, Globals.getWorld(), null, 0);
            itemRenderState.render(matrixStack, Globals.getMc().gameRenderer.getEntityRenderCommandQueue(), 0xF000F0, OverlayTexture.DEFAULT_UV, 0);
            Globals.getMc().gameRenderer.getEntityRenderDispatcher().render();
            Globals.getMc().gameRenderer.getEntityRenderCommandQueue().clear();
            GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(SpriteAtlasTexture.ITEMS_ATLAS_TEXTURE).getGlTextureView();
            Intrinsics.checkNotNullExpressionValue(gpuTextureView, "getTextureView(...)");
            GpuTextureView gpuTextureView2 = gpuTextureView;
            BatchedBuffer.draw$default(l2, batchedBuffer, Pipelines.INSTANCE.getProgramDefaultColoredTexture(), null, null, new MatrixStack().peek().getPositionMatrix(), (Matrix3x2f)new Matrix3x2fStack(), MapsKt.mapOf(TuplesKt.to("Sampler0", gpuTextureView2)), 6, null);
            vertexConsumers = null;
            matrixStack.pop();
        }
    }

    @Flow
    private final void onItemCommand(ItemCommandEvent itemCommandEvent) {
        if (vertexConsumers != null) {
            itemCommandEvent.setProvider(vertexConsumers);
            itemCommandEvent.cancel();
        }
    }

    private final int getAnimationDuration() {
        int n = 101 - this.getSpeed();
        if (this.getMode() == TotemAnimationMode.FadeOut) {
            return n / 4;
        }
        if (this.getMode() == TotemAnimationMode.Insert) {
            return n / 2;
        }
        return n;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        TotemAnimation.a = ZkmKeys.a(4589183338756116592L, -88129097220505376L, MethodHandles.lookup().lookupClass()).a(39078240765253L);
                        var20 = TotemAnimation.a ^ 120499116154764L;
                        TotemAnimation.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[12];
                        var16_4 = 0;
                        var15_5 = "\u00adfmDI&W&\u00f0\u00e9/\u001d\u00ff\u001e\u0081#0\u00e4\u00cd\u0081j\u0082t\u00e5\"\u0090 AW4gb\u00d0-\u00eb\u0089~\u00d7!6(4\u00ec\u0095}b$\u00af\u00f6*\u00d3/\u008e\u00bc\u00b3k\u00c0jKNa\u00ab\u0085\u00d1\u00act\u00d1\u00e9\u00a7O\u009b\u00a9\u00dd\u00dbC\u0012\u00be*\u0099\u00a6\u00b8\u0010\u00e0O\u00ae\u0098\u00dd\u00cd\u00c1XI\u00f8\u00cb)=\u00f2Z\u00980\b\u00d3?\u008c\u0090n\u0097\u009c`\u009cO\u0089\u00cd3\u00f1\u00afKD*{\u00cdG\u00a3@\u0080?5\u00d5\u00ca\u00fdP\n\u00b9/Y\u00b1\u008c0\u008c\u00c8\u00f7WX\u00d3\u00c9)g\u00f7 \u00c9\u00dd\u0095\u00bb\u00c7\u007f\u00d9\u00ee\u00f4\u00fd\"\u00e5\u00f5\u0095J\u0013n\u00a7~.>\u001f\u0090{X\u0010\u00c7\u00f5\u00ff\u00d5\u00f6T\u0010\u000e\u00ba6\u00b5\u008a\u00cf\u0012\u00e3?\u00cd\u0095Q\u0003\u00e9\u00c7(\u0010\u001fq\u00cb\u00a6f\u00f1\u0093\u00f7\u008eE\u00fc\u000b\u0093\u00fa'\u00f7\u0018\u00a1\u00a8V\u00b7Z,W\u00c6\u00ec\u00c5\u00dcgXsqi\u00dd\u00a9\u00aa#*\bE\\\u0010;-\u00e2\\\u00de[>\u00bb(\u0015K\u0086\t\u00a2\u00cc\u0019\u0010\u00c9\u00a0\u008c\u0014eY\u00e9\u0080\u008f\u0004qG;cQD";
                        var17_6 = 273;
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
                            var18_3[var16_4++] = TotemAnimation.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ee\u0093$N\u00a6\u00da\u000f\u00eb\u008aS\u00c2\u0099\u0088\u00a1-O\u00d6\u00a7\u00d1p\u009c\u00f0\u00e0\u00f3 \u0018\u0088.f8\u00e2\u0081+\r\u0015\u00f5M\u0011\u0089\u00ab\u00b0\u0013\u0014\u00b0}\u0004\u0001s\u0097Zl}\u00b7\u00e0\u00aaN\b";
                            var17_6 = 57;
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
                            var18_3[var16_4++] = TotemAnimation.zkmDecodeUtf8(var19_9).intern();
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
                TotemAnimation.b = var18_3;
                TotemAnimation.c = new String[12];
                TotemAnimation.h = new HashMap<K, V>(13);
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
                var4_14 = "\u00f3\u009d\u00b3\u0007d\u00b0\u008a\u00e1\u0091\u00d7\b-)\u001d\u00e5`|\u00d6\u0094[|\u00cb\u00bc\u00c9\u00e9\u00f5\u00f1~\u00fe\u001a\u0096a6\u00bc\u009dY\u0093\n\u00c8\u000f\u001a\u0092\u00ab\u0010\u00bdrl\u00e7\u00a6\u00f1\u0019\u0090=\t\f\u00e8\u00a6\u00c1\u00fb\u00b2\u00fb\u00de\u00f0\u00a3\u00cc\u001c\u0003\u008d\u00a3\u0092\u00e3\u001dL\u000f\u0005\u00be_\u00db\u00fdVZ6^\u0094\u00a5\u001e.\u0013:\u008f \u00cfQ\u00f5\u00e4\u00fb\u00c2\u00dc#\u009b\u008ej\u00d0$\u008bM\u00e2\u00ef\u008c\u0090\u00fa\u0011&\u00d3T\u00edS4Q\u008dM<\u00f9q_\u00bc\u00eak";
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
                    var4_14 = "\u0018`~\u00ad\u00f0r\u00ed\u0005q\u00c2\u009f\u00f6\u00e5O~\u00ac";
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
        TotemAnimation.e = var6_12;
        TotemAnimation.f = new Integer[18];
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(TotemAnimation.class, "mode", "getMode()Lsu/catlean/ok;", 0)), Reflection.property1(new PropertyReference1Impl(TotemAnimation.class, "speed", "getSpeed()I", 0))};
        TotemAnimation.delegatedProperties = var29_20;
        TotemAnimation.INSTANCE = new TotemAnimation();
        TotemAnimation.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)TotemAnimation.INSTANCE, "mode", (Enum)TotemAnimationMode.FadeOut, null, null, 12, null);
        TotemAnimation.speed$delegate = SettingFactory.intSetting$default(TotemAnimation.INSTANCE, "speed", 40, new IntRange(1, 100), null, null, 24, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7919;
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
                throw new RuntimeException("su/catlean/ki", exception);
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
            TotemAnimation.c[n2] = TotemAnimation.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TotemAnimation.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ki" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x469;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/ki", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TotemAnimation.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TotemAnimation.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ki" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TotemAnimation.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TotemAnimation.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

