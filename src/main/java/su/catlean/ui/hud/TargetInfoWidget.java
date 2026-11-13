/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.ui.hud;

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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.esp.NameTags;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.GlowTexture;
import su.catlean.render.Gradient2D;
import su.catlean.render.Particle2D;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Animation;
import su.catlean.util.ArmorUtil;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.combat.TargetUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TargetInfoWidget
extends HudElement {
    @NotNull
    public static final TargetInfoWidget Y;
    @NotNull
    private static final List i;
    @Nullable
    private static LivingEntity target;
    @NotNull
    private static final Animation K;
    @NotNull
    private static final Animation healthAnimation;
    private static int Q;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map k;

    private TargetInfoWidget(long l) {
        long l2 = (l = a ^ l) ^ 0xC1E9E1C2391L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "target-info", (char)n2);
    }

    @Nullable
    public final LivingEntity getTarget() {
        return target;
    }

    public final void setTarget(@Nullable LivingEntity livingEntity) {
        target = livingEntity;
    }

    @NotNull
    public final Animation O() {
        return K;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void tick() {
        if (TargetInfoWidget.target == null) ** GOTO lbl-1000
        v0 = TargetInfoWidget.target;
        Intrinsics.checkNotNull(v0);
        if (v0.isDead() && TargetInfoWidget.target instanceof PlayerEntity && !Intrinsics.areEqual(TargetInfoWidget.target, Globals.getPlayer())) {
            var12_1 = TargetInfoWidget.Q;
            TargetInfoWidget.Q = var12_1 + 1;
        } else lbl-1000:
        // 2 sources

        {
            TargetInfoWidget.Q = 0;
        }
        if ((v1 = Aura.INSTANCE.getTarget()) == null) {
            v1 = AutoCrystal.INSTANCE.getEnabled() != false ? AutoCrystal.INSTANCE.getTarget() : null;
        }
        if ((var12_2 = v1) != null && var12_2 instanceof PlayerEntity && ((PlayerEntity)var12_2).getHealth() < 10.0f && ((PlayerEntity)var12_2).age % 6 == 0) {
            TargetInfoWidget.i.add(new Particle2D(this.getX() + 46.0f + 28.0f * (float)Math.random(), this.getY() + 5.0f, (float)Math.random() * 2.0f - 1.0f, (float)Math.random() * 2.0f, 0, 0.0f, 0.0f, false, 224, null));
        }
        var13_3 = TargetInfoWidget.healthAnimation.getValue() / 20.0f;
        var14_4 = Math.clamp(var13_3, 0.0f, 1.0f);
        if (var12_2 != null && var12_2 instanceof LivingEntity) {
            var15_5 = ((LivingEntity)var12_2).hurtTime;
            v2 = 5 <= var15_5 ? var15_5 < 11 : false;
            if (v2) {
                var15_5 = 6;
                for (var16_6 = 0; var16_6 < var15_5; ++var16_6) {
                    TargetInfoWidget.i.add(new Particle2D(TargetInfoWidget.Y.getX() - 2.0f + 114.0f * var14_4, TargetInfoWidget.Y.getY() + 44.5f + (float)Math.random() * 2.0f - 1.0f, (float)Math.random() * 2.0f, (float)Math.random() * 1.0f - 0.5f, 0, 0.0f, 0.0f, true, 96, null));
                }
            }
        }
        TargetInfoWidget.i.removeIf((Predicate<Object>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, invokePredicate(kotlin.jvm.functions.Function1 java.lang.Object ), (Ljava/lang/Object;)Z)((Function1<Particle2D, Boolean>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, tick$lambda$0(su.catlean.render.Particle2D ), (Lsu/catlean/render/Particle2D;)Ljava/lang/Boolean;)()));
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        long l2 = l ^ 0x2E1EB77FB0F8L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        this.renderTarget(drawContext, false, l2);
    }

    @Override
    public void renderOverlay(@NotNull DrawContext drawContext, long l) {
        long l2 = l ^ 0x6C703414B9A5L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        this.renderTarget(drawContext, true, l2);
    }

    /*
     * Unable to fully structure code
     */
    private final void renderTarget(DrawContext var1_1, boolean var2_2, long var3_3) {
        block38: {
            block39: {
                block42: {
                    block40: {
                        block41: {
                            block23: {
                                block24: {
                                    block37: {
                                        block36: {
                                            block35: {
                                                block34: {
                                                    block33: {
                                                        block32: {
                                                            block30: {
                                                                block31: {
                                                                    block29: {
                                                                        block28: {
                                                                            block27: {
                                                                                block26: {
                                                                                    block25: {
                                                                                        v0 = var3_3 = TargetInfoWidget.a ^ var3_3;
                                                                                        var14_4 = v0 ^ 79279856358300L;
                                                                                        var16_5 = v0 ^ 132208688429287L;
                                                                                        v1 = v0 ^ 128301748462876L;
                                                                                        var18_6 = (int)(v1 >>> 32);
                                                                                        var19_7 = (int)(v1 << 32 >>> 48);
                                                                                        var20_8 = (int)(v1 << 48 >>> 48);
                                                                                        v2 = v0 ^ 122439566351974L;
                                                                                        var27_9 = (int)(v2 >>> 48);
                                                                                        var28_10 = (int)(v2 << 16 >>> 32);
                                                                                        var29_11 = (int)(v2 << 48 >>> 48);
                                                                                        v3 = v0 ^ 65266559351360L;
                                                                                        var30_12 = (int)(v3 >>> 56);
                                                                                        var31_13 = v3 << 8 >>> 8;
                                                                                        var33_14 = v0 ^ 105968024251490L;
                                                                                        v4 = v0 ^ 94719609755974L;
                                                                                        var37_15 = (int)(v4 >>> 48);
                                                                                        var38_16 = (int)(v4 << 16 >>> 48);
                                                                                        var39_17 = (int)(v4 << 32 >>> 32);
                                                                                        var43_18 = ArrayListEntry.u();
                                                                                        if (var43_18) break block23;
                                                                                        if (var2_2) break block24;
                                                                                        v5 = Globals.getMc().currentScreen instanceof ChatScreen;
                                                                                        if (var43_18) break block25;
                                                                                        if (v5) break block26;
                                                                                        v5 = Globals.getMc().currentScreen instanceof HudEditorScreen;
                                                                                    }
                                                                                    if (!v5) break block27;
                                                                                }
                                                                                v6 = Globals.getPlayer();
                                                                                break block28;
                                                                            }
                                                                            v6 = Aura.INSTANCE.getTarget();
                                                                            if (v6 != null) break block28;
                                                                            v7 = AutoCrystal.INSTANCE;
                                                                            if (var43_18) ** GOTO lbl44
                                                                            if (v7.getEnabled()) {
                                                                                v7 = AutoCrystal.INSTANCE;
lbl44:
                                                                                // 2 sources

                                                                                v8 = v7.getTarget();
                                                                            } else {
                                                                                v8 = null;
                                                                            }
                                                                            v6 = v8;
                                                                        }
                                                                        v9 = var44_19 = v6;
                                                                        if (var43_18) break block29;
                                                                        if (v9 == null) break block30;
                                                                        v9 = var44_19;
                                                                    }
                                                                    if (var43_18) break block31;
                                                                    if (!(v9 instanceof LivingEntity)) break block30;
                                                                    v9 = var44_19;
                                                                }
                                                                TargetInfoWidget.target = (LivingEntity)v9;
                                                            }
                                                            v10 = var44_19;
                                                            if (var43_18) break block32;
                                                            if (v10 == null) break block33;
                                                            v10 = var44_19;
                                                        }
                                                        v11 = v10.isAlive();
                                                        if (var43_18) break block34;
                                                        if (v11 != 0) ** GOTO lbl-1000
                                                    }
                                                    v11 = TargetInfoWidget.Q;
                                                }
                                                var45_20 = v11;
                                                v12 = 1;
                                                v13 = var45_20;
                                                if (var43_18) break block35;
                                                if (v12 > v13) break block36;
                                                v12 = var45_20;
                                                if (var43_18) break block37;
                                                v13 = 20;
                                            }
                                            v12 = v12 < v13 ? 1 : 0;
                                            break block37;
                                        }
                                        v12 = 0;
                                    }
                                    if (v12 != 0) lbl-1000:
                                    // 2 sources

                                    {
                                        v14 = 1.0f;
                                    } else {
                                        v14 = 0.0f;
                                    }
                                    TargetInfoWidget.K.animate(v14);
                                    if (var43_18) break block23;
                                    if (TargetInfoWidget.K.getValue() <= 0.0f) {
                                        TargetInfoWidget.target = null;
                                    }
                                }
                                var1_1.getMatrices().pushMatrix();
                                var1_1.getMatrices().translate(this.getX() + 60.0f, this.getY() + 28.5f);
                                var1_1.getMatrices().scale(Math.clamp(TargetInfoWidget.K.getValue(), 0.0f, 2.0f), Math.clamp(TargetInfoWidget.K.getValue(), 0.0f, 2.0f));
                                var1_1.getMatrices().translate(-(this.getX() + 60.0f), -(this.getY() + 28.5f));
                            }
                            if (var43_18) break block38;
                            if (TargetInfoWidget.target == null) break block39;
                            v15 = var2_2;
                            if (var43_18) break block40;
                            if (v15) break block41;
                            v16 = this;
                            v17 = var1_1.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v17, "pose(...)");
                            HudElement.drawBackground$default(v16, v17, this.getX(), (short)var27_9, var28_10, this.getY(), 120.0f, 57.0f, (short)var29_11, 0.0f, false, 96, null);
                            v18 = this;
                            v19 = var1_1.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v19, "pose(...)");
                            HudElement.drawBackground$default(v18, v19, this.getX() + 42.0f, (short)var27_9, var28_10, this.getY() + 2.0f, 36.0f, 36.0f, (short)var29_11, 1.0f, false, 64, null);
                            v20 = this;
                            v21 = var1_1.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v21, "pose(...)");
                            HudElement.drawBackground$default(v20, v21, this.getX() + 81.0f, (short)var27_9, var28_10, this.getY() + 2.0f, 36.0f, 17.0f, (short)var29_11, 1.0f, false, 64, null);
                            v22 = this;
                            v23 = var1_1.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v23, "pose(...)");
                            HudElement.drawBackground$default(v22, v23, this.getX() + 81.0f, (short)var27_9, var28_10, this.getY() + 21.0f, 36.0f, 17.0f, (short)var29_11, 1.0f, false, 64, null);
                            v24 = TargetInfoWidget.target instanceof PlayerEntity;
                            if (!var43_18) {
                                if (v24 != 0) {
                                    v25 = TargetInfoWidget.target;
                                    Intrinsics.checkNotNull(v25, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                                    this.drawEffects(var1_1, ArmorUtil.getArmorItems((PlayerEntity)v25), var14_4, var2_2);
                                }
                                v24 = TargetInfoWidget.Q;
                            }
                            if (v24 > 0) {
                                v26 = this;
                                v27 = var1_1.getMatrices();
                                Intrinsics.checkNotNullExpressionValue(v27, "pose(...)");
                                HudElement.drawBackground$default(v26, v27, this.getX(), (short)var27_9, var28_10, this.getY(), 120.0f, 55.0f, (short)var29_11, Math.clamp(((float)TargetInfoWidget.Q + 5.0f + RenderState.INSTANCE.getTickDelta()) / 20.0f, 0.0f, 1.0f), false, 64, null);
                            }
                            this.setSize(120.0f, 55.0f);
                            if (!var43_18) break block39;
                        }
                        this.drawParticles(var18_6, (short)var19_7, (short)var20_8, var1_1);
                        v28 = this;
                        v29 = var1_1;
                        v30 = TargetInfoWidget.target;
                        v31 = v30;
                        Intrinsics.checkNotNull(v30);
                        if (var43_18) break block42;
                        var42_21 = v31;
                        v28.drawHead(v29, (char)var37_15, (char)var38_16, var39_17, var42_21);
                        v15 = TargetInfoWidget.target instanceof PlayerEntity;
                    }
                    if (v15) {
                        v32 = TargetInfoWidget.target;
                        Intrinsics.checkNotNull(v32, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                        this.drawEffects(var1_1, ArmorUtil.getArmorItems((PlayerEntity)v32), var14_4, true);
                        v33 = TargetInfoWidget.target;
                        Intrinsics.checkNotNull(v33, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                        this.drawArmor(var1_1, (PlayerEntity)v33, (byte)var30_12, var31_13);
                    }
                    v28 = this;
                    v29 = var1_1;
                    v34 = TargetInfoWidget.target;
                    v31 = v34;
                    Intrinsics.checkNotNull(v34);
                }
                v28.drawHealthBar(var33_14, v29, v31);
            }
            Fonts.getSF_B().drawStringRight(var16_5, var1_1, StringsKt.dropLast("EZZZZZZZZZZ", 11 - Math.clamp((long)TargetInfoWidget.Q, 0, 11)), this.getX() + 59.0f, this.getY() + 19.0f, 10);
            var1_1.getMatrices().popMatrix();
        }
    }

    /*
     * Unable to fully structure code
     */
    private final void drawArmor(DrawContext var1_1, PlayerEntity var2_2, byte var3_3, long var4_4) {
        var6_5 = ((long)var3_3 << 56 | var4_4 << 8 >>> 8) ^ TargetInfoWidget.a;
        var16_6 = var6_5 ^ 24807341893189L;
        var20_7 = ZkmReferences.zkmBootstrap("\u00c5", (long)-1845360959170297902L, (long)var6_5);
        v0 = TargetInfoWidget.Q;
        if (var20_7 == false) {
            if (v0 > 0) {
                return;
            }
            var1_1.getMatrices().pushMatrix();
            var1_1.getMatrices().translate(this.getX() + 83.0f, this.getY() + 3.5f);
            var1_1.getMatrices().scale(0.8f, 0.8f);
            var1_1.drawItem(var2_2.getMainHandStack(), 0, 0);
            var1_1.drawStackOverlay(Globals.getMc().textRenderer, var2_2.getMainHandStack(), 0, 0);
            var1_1.drawItem(var2_2.getOffHandStack(), (int)TargetInfoWidget.zkmBootstrap2("k", (int)28999, (long)(6031922713560354701L ^ var6_5)), 0);
            var1_1.drawStackOverlay(Globals.getMc().textRenderer, var2_2.getOffHandStack(), (int)TargetInfoWidget.zkmBootstrap2("k", (int)5307, (long)(3067556174476503657L ^ var6_5)), 0);
            var1_1.drawItem(Items.TOTEM_OF_UNDYING.getDefaultStack(), 0, (int)TargetInfoWidget.zkmBootstrap2("k", (int)24528, (long)(4664156023630962971L ^ var6_5)));
            var1_1.getMatrices().popMatrix();
            v0 = TargetUtil.INSTANCE.getPops(var2_2);
        }
        var21_8 = v0;
        v1 = Fonts.getSF_B();
        v2 = var21_8;
        if (var20_7 != false) ** GOTO lbl31
        if (v2 == 0) {
            v3 = "-";
        } else {
            v2 = var21_8;
lbl31:
            // 2 sources

            v3 = "-" + v2;
        }
        v4 = this.getX() + 105.0f;
        v5 = this.getY() + 28.5f;
        v6 = Color.white;
        Intrinsics.checkNotNullExpressionValue(v6, (String)TargetInfoWidget.zkmBootstrap("f", (int)26515, (long)(4722416445737710536L ^ var6_5)));
        v1.drawStringCentered(var1_1, (String)v3, v4, v5, v6, var16_6);
    }

    private final void drawHead(DrawContext drawContext, char c, char c2, int n, LivingEntity livingEntity) {
        long l;
        long l2 = l = ((long)c << 48 | (long)c2 << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x3178EAE91282L;
        long l4 = l2 ^ 0x487E9AFC894AL;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4999775627018489361L, (long)l);
        if (callSite != false) {
            if (!(livingEntity instanceof PlayerEntity)) {
                return;
            }
            drawContext.getMatrices().pushMatrix();
        }
        float f = (10.0f - Math.clamp(healthAnimation.getValue(), 0.0f, 10.0f)) / 2.0f;
        float f2 = f * (float)Math.sin((float)livingEntity.age + RenderState.INSTANCE.getTickDelta());
        float f3 = f * (float)Math.cos((float)livingEntity.age + RenderState.INSTANCE.getTickDelta());
        float f4 = f * f2;
        drawContext.getMatrices().translate(this.getX() + 46.0f + 14.0f + f4, this.getY() + 7.0f + 14.0f - f3);
        drawContext.getMatrices().rotate((float)Math.toRadians(f2));
        drawContext.getMatrices().scale(1.0f - (float)livingEntity.hurtTime / 100.0f, 1.0f - (float)livingEntity.hurtTime / 100.0f);
        drawContext.getMatrices().translate(-(this.getX() + 46.0f + 14.0f + f4), -(this.getY() + 7.0f + 14.0f - f3));
        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
        Intrinsics.checkNotNullExpressionValue(vertexFormat, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)26067, (long)(0x492A23C30F3A1282L ^ l))));
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n2 = 4;
        boolean bl = false;
        int n3 = 2;
        VertexFormat vertexFormat2 = vertexFormat;
        BatchedBuffer batchedBuffer = new BatchedBuffer(vertexFormat2, n3, bl, n2, defaultConstructorMarker);
        GpuTextureView gpuTextureView = Globals.getMc().getTextureManager().getTexture(((AbstractClientPlayerEntity)livingEntity).getSkin().comp_1626().comp_3627()).getGlTextureView();
        Intrinsics.checkNotNullExpressionValue(gpuTextureView, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)30020, (long)(0x3495177D5C480209L ^ l))));
        GpuTextureView gpuTextureView2 = gpuTextureView;
        float f5 = this.getX() + 46.0f;
        float f6 = this.getY() + 7.0f;
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)2251, (long)(0x75EDB7727E867F80L ^ l))));
        RenderUtil.texturedQuad$default(RenderUtil.INSTANCE, batchedBuffer, f5, f6, 28.0f, 28.0f, color, null, null, null, 0.125f, 0.125f, 0.25f, l4, 0.25f, (int)TargetInfoWidget.zkmBootstrap2("k", (int)25677, (long)(0x5794B496C005B987L ^ l)), null);
        float f7 = this.getX() + 44.0f;
        float f8 = this.getY() + 5.0f;
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)2251, (long)(0x75EDB7727E867F80L ^ l))));
        RenderUtil.texturedQuad$default(RenderUtil.INSTANCE, batchedBuffer, f7, f8, 32.0f, 32.0f, color2, null, null, null, 0.625f, 0.125f, 0.75f, l4, 0.25f, (int)TargetInfoWidget.zkmBootstrap2("k", (int)25677, (long)(0x5794B496C005B987L ^ l)), null);
        RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultColoredTexture();
        Framebuffer framebuffer = Globals.getMc().getFramebuffer();
        Intrinsics.checkNotNullExpressionValue(framebuffer, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)11209, (long)(0x29AD1EC096815C80L ^ l))));
        BatchedBuffer.draw$default(l3, batchedBuffer, renderPipeline, framebuffer, null, null, (Matrix3x2f)drawContext.getMatrices(), MapsKt.mapOf(TuplesKt.to(TargetInfoWidget.zkmBootstrap("f", (int)27285, (long)(0x2B478856A12B9DCAL ^ l)), gpuTextureView2)), (int)TargetInfoWidget.zkmBootstrap2("k", (int)3089, (long)(0x2C1CF6DA98A7D1D3L ^ l)), null);
        drawContext.getMatrices().scale(1.0f, 1.0f);
        drawContext.getMatrices().popMatrix();
    }

    private final void drawParticles(int n, short s, short s2, DrawContext drawContext) {
        block19: {
            Particle2D particle2D;
            CallSite callSite;
            Iterable iterable;
            GpuTextureView gpuTextureView;
            BatchedBuffer batchedBuffer;
            long l;
            long l2;
            long l3;
            block18: {
                boolean bl;
                Particle2D particle2D2;
                Object object;
                Iterator iterator2;
                Collection<Object> collection2;
                Iterator iterator3;
                block17: {
                    block16: {
                        boolean bl2;
                        long l4 = l3 = ((long)n << 32 | (long)s << 48 >>> 32 | (long)s2 << 48 >>> 48) ^ a;
                        l2 = l4 ^ 0x13EDDE7C6ED8L;
                        l = l4 ^ 0x6AEBAE69F510L;
                        VertexFormat vertexFormat = VertexFormats.POSITION_TEXTURE_COLOR;
                        Intrinsics.checkNotNullExpressionValue(vertexFormat, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)11134, (long)(0x158F56009A84A072L ^ l3))));
                        DefaultConstructorMarker defaultConstructorMarker = null;
                        int n2 = 4;
                        boolean bl3 = false;
                        int n3 = i.size();
                        VertexFormat vertexFormat2 = vertexFormat;
                        batchedBuffer = new BatchedBuffer(vertexFormat2, n3, bl3, n2, defaultConstructorMarker);
                        GpuTextureView gpuTextureView2 = Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView();
                        Intrinsics.checkNotNullExpressionValue(gpuTextureView2, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)30344, (long)(0x7C9A7D890507D89L ^ l3))));
                        gpuTextureView = gpuTextureView2;
                        iterable = i;
                        callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4125220363732148366L, (long)l3);
                        iterator3 = iterable;
                        collection2 = new ArrayList();
                        iterator2 = iterator3.iterator();
                        while (iterator2.hasNext()) {
                            object = iterator2.next();
                            particle2D2 = (Particle2D)object;
                            if (s2 < 0) {
                                bl2 = particle2D2.isFlame();
                                if (callSite == false) {
                                    if (callSite == false) {
                                        if (!bl2) continue;
                                        collection2.add(object);
                                    }
                                    if (callSite == false) continue;
                                }
                            }
                            break block16;
                        }
                        iterable = (List)collection2;
                        if (s2 < 0) {
                            bl2 = false;
                        }
                    }
                    block1: for (Collection<Object> collection2 : iterable) {
                        particle2D = (Particle2D)((Object)collection2);
                        float f = (1.0f - ((float)particle2D.getTime() + RenderState.INSTANCE.getTickDelta()) / 10.0f) * 0.8f;
                        object = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), f);
                        RenderUtil.texturedQuad$default(RenderUtil.INSTANCE, batchedBuffer, RenderUtil.INSTANCE.lerp(particle2D.getLastX(), particle2D.getX(), RenderState.INSTANCE.getTickDelta()), RenderUtil.INSTANCE.lerp(particle2D.getLastY(), particle2D.getY(), RenderState.INSTANCE.getTickDelta()), 10.0f, 10.0f, object, null, null, null, ParticleTexture.FIREFLY_ALT.o(), ParticleTexture.FIREFLY_ALT.v(), ParticleTexture.FIREFLY_ALT.L(), l, ParticleTexture.FIREFLY_ALT.w(), (int)TargetInfoWidget.zkmBootstrap2("k", (int)6314, (long)(0x2E15030DC84EB934L ^ l3)), null);
                        do {
                            CallSite callSite2 = callSite;
                            if (n >= 0) {
                                if (callSite2 != false) break block17;
                                callSite2 = callSite;
                            }
                            if (callSite2 == false) continue block1;
                        } while (s2 > 0);
                    }
                    iterable = i;
                }
                iterator3 = iterable;
                collection2 = new ArrayList();
                iterator2 = iterator3.iterator();
                while (iterator2.hasNext()) {
                    object = iterator2.next();
                    particle2D2 = (Particle2D)object;
                    if (n >= 0) {
                        bl = particle2D2.isFlame();
                        if (callSite == false) {
                            boolean bl4;
                            if (callSite == false) {
                                bl4 = !bl;
                            }
                            if (!bl4) continue;
                            collection2.add(object);
                            if (callSite == false) continue;
                        }
                    }
                    break block18;
                }
                iterable = (List)collection2;
                if (n >= 0) {
                    bl = false;
                }
            }
            block4: for (Collection<Object> collection2 : iterable) {
                particle2D = (Particle2D)((Object)collection2);
                float f = RenderUtil.INSTANCE.lerp(particle2D.getLastX(), particle2D.getX(), RenderState.INSTANCE.getTickDelta());
                float f2 = RenderUtil.INSTANCE.lerp(particle2D.getLastY(), particle2D.getY(), RenderState.INSTANCE.getTickDelta());
                Color color = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(color, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)6667, (long)(0x6D76124D81DF110DL ^ l3))));
                float f3 = 1.0f - ((float)particle2D.getTime() + RenderState.INSTANCE.getTickDelta()) / 60.0f;
                RenderUtil.texturedQuad$default(RenderUtil.INSTANCE, batchedBuffer, f, f2, 6.0f, 6.0f, RenderUtil.INSTANCE.withAlpha(color, f3), null, null, null, ParticleTexture.DROP.o(), ParticleTexture.DROP.v(), ParticleTexture.DROP.L(), l, ParticleTexture.DROP.w(), (int)TargetInfoWidget.zkmBootstrap2("k", (int)25677, (long)(0x57949603F490C5DDL ^ l3)), null);
                do {
                    CallSite callSite3 = callSite;
                    if (s2 <= 0) {
                        if (callSite3 != false) break block19;
                        callSite3 = callSite;
                    }
                    if (callSite3 == false) continue block4;
                } while (s2 > 0);
            }
            RenderPipeline renderPipeline = Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend();
            Framebuffer framebuffer = Globals.getMc().getFramebuffer();
            Intrinsics.checkNotNullExpressionValue(framebuffer, (String)((Object)TargetInfoWidget.zkmBootstrap("f", (int)31493, (long)(0x3F71FFDDD61DF00FL ^ l3))));
            BatchedBuffer.draw$default(l2, batchedBuffer, renderPipeline, framebuffer, null, null, (Matrix3x2f)drawContext.getMatrices(), MapsKt.mapOf(TuplesKt.to(TargetInfoWidget.zkmBootstrap("f", (int)23563, (long)(0x462DD7E0C25B5703L ^ l3)), gpuTextureView)), (int)TargetInfoWidget.zkmBootstrap2("k", (int)28214, (long)(0x32AE0E5304D4FA2L ^ l3)), null);
        }
    }

    private final void drawHealthBar(long l, DrawContext drawContext, LivingEntity livingEntity) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x329BC471D012L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x4DB37CA9B067L;
        Color color = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 0.8f);
        Color color2 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 0.3f);
        if (livingEntity instanceof PlayerEntity) {
            healthAnimation.animate(NameTags.INSTANCE.getDisplayName((PlayerEntity)livingEntity, NameTags.INSTANCE.getHealthProvider()));
        } else {
            healthAnimation.animate(livingEntity.getHealth() + livingEntity.getAbsorptionAmount());
        }
        float f = 0.0f;
        Iterable iterable = ArmorUtil.getArmorItems(livingEntity);
        for (Object t : iterable) {
            ItemStack itemStack = (ItemStack)t;
            f += (float)(itemStack.getMaxDamage() - itemStack.getDamage());
        }
        float f2 = RangesKt.coerceIn(f / 2035.0f, 0.0f, 1.0f);
        float f3 = healthAnimation.getValue() / 20.0f;
        float f4 = Math.clamp(f3, 0.0f, 1.0f);
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n, this.getX() + 3.0f, this.getY() + 40.0f, 114.0f, 4.0f, 1.0f, 1.0f, 1.0f, 1.0f, color2, color2, 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack2, (char)n, this.getX() + 3.0f, this.getY() + 40.0f, 114.0f * f2, 4.0f, 2.0f, 1.0f, 1.0f, 1.0f, color, color, 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack3, (char)n, this.getX() + 3.0f, this.getY() + 45.0f, 114.0f, 9.0f, 2.0f, 1.0f, 1.0f, 1.0f, color2, color2, 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        Matrix3x2fStack matrix3x2fStack4 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack4, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack4, (char)n, this.getX() + 3.0f, this.getY() + 45.0f, 114.0f * f4, 9.0f, 2.0f, 1.0f, 1.0f, 1.0f, color, color, 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        FontRenderer fontRenderer = Fonts.getSF_M();
        String string = String.valueOf((double)MathKt.roundToInt((double)healthAnimation.getValue() * 10.0) / 10.0);
        float f5 = this.getX() + 59.5f;
        float f6 = this.getY() + 48.5f;
        Color color3 = Color.black;
        Intrinsics.checkNotNullExpressionValue(color3, "black");
        fontRenderer.drawStringCentered(drawContext, string, f5, f6, color3, l4);
        FontRenderer fontRenderer2 = Fonts.getSF_M();
        String string2 = String.valueOf((double)MathKt.roundToInt((double)healthAnimation.getValue() * 10.0) / 10.0);
        float f7 = this.getX() + 59.0f;
        float f8 = this.getY() + 48.0f;
        Color color4 = Color.white;
        Intrinsics.checkNotNullExpressionValue(color4, "white");
        fontRenderer2.drawStringCentered(drawContext, string2, f7, f8, color4, l4);
    }

    private final void drawEffects(DrawContext drawContext, List list, long l, boolean bl) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x38AAC95D1F85L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 32);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x57CCEF6B2543L;
        boolean bl2 = ArrayListEntry.u();
        int n4 = Q;
        if (!bl2) {
            if (n4 > 0) {
                return;
            }
            n4 = 4;
        }
        Object object = new Pair[n4];
        object[0] = new Pair<Float, Float>(Float.valueOf(this.getX() + 3.0f), Float.valueOf(this.getY() + 2.0f));
        object[1] = new Pair<Float, Float>(Float.valueOf(this.getX() + 22.0f), Float.valueOf(this.getY() + 2.0f));
        object[2] = new Pair<Float, Float>(Float.valueOf(this.getX() + 22.0f), Float.valueOf(this.getY() + 21.0f));
        object[3] = new Pair<Float, Float>(Float.valueOf(this.getX() + 3.0f), Float.valueOf(this.getY() + 21.0f));
        List<Pair> list2 = CollectionsKt.listOf(object);
        object = list2;
        int n5 = 0;
        Iterator iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            float f;
            float f2;
            int n6;
            block11: {
                float f3;
                Color color;
                block10: {
                    block9: {
                        Object t = iterator2.next();
                        int n7 = n5++;
                        if (bl2) break;
                        int n8 = n7;
                        if (!bl2) {
                            if (n8 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            n8 = n7;
                        }
                        Pair pair = (Pair)t;
                        n6 = n8;
                        f2 = ((Number)pair.component1()).floatValue();
                        f = ((Number)pair.component2()).floatValue();
                        color = Color.BLACK;
                        f3 = (float)((ItemStack)list.get(3 - n6)).getDamage() / (float)((ItemStack)list.get(3 - n6)).getMaxDamage();
                        if (!Float.isNaN(f3)) {
                            Color color2 = Color.WHITE;
                            Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
                            Color color3 = Color.RED;
                            Intrinsics.checkNotNullExpressionValue(color3, "RED");
                            color = RenderUtil.INSTANCE.blend(color2, color3, (float)((ItemStack)list.get(3 - n6)).getDamage() / (float)((ItemStack)list.get(3 - n6)).getMaxDamage());
                        }
                        Color color4 = color;
                        Intrinsics.checkNotNull(color4);
                        color = RenderUtil.INSTANCE.withAlpha(color4, 0.2f);
                        if (bl2) break block9;
                        if (bl) break block10;
                        HudElement hudElement = Y;
                        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                        HudElement.drawBackground$default(hudElement, matrix3x2fStack, f2, (short)n, n2, f, 17.0f, 17.0f, (short)n3, 0.0f, false, 96, null);
                    }
                    if (!bl2) break block11;
                }
                Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                Gradient2D.E(f2, f + 17.0f * f3, 17.0f, 17.0f * (1.0f - f3), 2.0f, color, color, l4, color, color, matrix3x2fStack, 0.0f, null, null);
            }
            drawContext.getMatrices().pushMatrix();
            drawContext.getMatrices().translate(f2 + 0.5f, f);
            drawContext.drawItem((ItemStack)list.get(3 - n6), 0, 0);
            drawContext.getMatrices().popMatrix();
            if (!bl2) continue;
        }
    }

    private static final boolean tick$lambda$0(Particle2D particle2D) {
        block5: {
            block4: {
                Intrinsics.checkNotNullParameter(particle2D, "it");
                particle2D.setLastX(particle2D.getX());
                particle2D.setLastY(particle2D.getY());
                particle2D.setX(particle2D.getX() + particle2D.getMotionX());
                particle2D.setY(particle2D.getY() + particle2D.getMotionY());
                particle2D.setMotionX(particle2D.getMotionX() * 0.95f);
                particle2D.setMotionY(particle2D.getMotionY() * 0.95f);
                double d = (double)Y.getX() + 46.0;
                double d2 = (double)Y.getX() + 74.0;
                double d3 = particle2D.getX() + 4.0f;
                boolean bl = d <= d3 ? d3 <= d2 : false;
                if (!bl) break block4;
                d = (double)Y.getY() + 5.0;
                d2 = (double)Y.getY() + 33.0;
                d3 = particle2D.getY() + 4.0f;
                if (d <= d3 ? d3 <= d2 : false) break block5;
            }
            if (!particle2D.isFlame()) {
                particle2D.setMotionY(particle2D.getMotionY() + 0.2f);
                particle2D.setMotionX(particle2D.getMotionX() * 0.95f);
            }
        }
        int n = particle2D.getTime();
        particle2D.setTime(n + 1);
        return n > (particle2D.isFlame() ? 10 : 60);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block26: {
            block25: {
                block24: {
                    block23: {
                        block22: {
                            block21: {
                                TargetInfoWidget.a = ZkmKeys.a(-7957233438780448904L, 6258790527258338699L, MethodHandles.lookup().lookupClass()).a(108401461291289L);
                                var31 = TargetInfoWidget.a ^ 53823310052456L;
                                var35_1 = var31 ^ 112436683986290L;
                                TargetInfoWidget.f = new HashMap<K, V>(13);
                                var22_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_3 = 1; var23_3 < 8; ++var23_3) {
                                    v2 = v2;
                                    v2[var23_3] = (byte)(var31 << var23_3 * 8 >>> 56);
                                }
                                var22_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_4 = new String[23];
                                var27_5 = 0;
                                var26_6 = "\u0003\u00dfUA \u00a97\u00c8D\u00f1\u00df\u00ffT\u00eeb\u00d0\u009c5.G\u0080\u0090y\u00d6\t%\u007f\u0010\u009b\u00ed\u00f0\u0010x\u00c7V\u00f5\u00ea\u00dby\u0018-\u00fe\u0007V\u00a0\u00dd:<\u00ea\u00c6\u0006_\u00f4\u009ef\u00e8\u00f1\u00ed\u00bd\u0006\u00e9\u00b2\u0081\u00e5\u0005\u00f1V\u00b07\u00f7\u00e4\u0080T\u00b3\u00cc\u00c4?\u00b98\u0010\u009b yA\"\u00a70vm\u009b:S\u00b2\u0081.\u00d9E\u00f2`\u00f8\u0001\u00820\u0085\u009a{\r/7*\u008c+\u00e2+\u00fe\u00c7\u001c?E\u00a4\u00ed\u00b8Kq\u00b6t\u00c6o$d\u0088\u0095\u00a8\u0011#Z\u0012\u0003g6\u00a6\u001e\u00ffj\u00d4q\u00bf\u00f8\b\u0017\u007f\u00b5e\u0010\u0099C\u00946\u00b8sF\u0083B3\u001f\u00fb\u00c3'\u001a8\u0010r\u0000\u00eb\u00ca\u00d0h\u00ef\u00e4\u00df\u00a4\u00baxm\u00e7\u00a6\u009a0bAh\u0089o\u00cffN\u00c5\u00f7\u008dz>\u00ac\u008f\u00cbF\u00c5(k\u00d1;\u00af\u0019\u00f0\u00aaE\u00e9\u00cfiG\u00fe\u00f1\rf\u0095\u00aa\u00a0\u00ac\u0003\u0003TL\u00f2`\u0006\u0081\u0094\u0010\u00ec+e\u00a7I\u0002CW\u00bd\u00167\u0011\u00e2UVB\u0010\u00e1Z\u008am\u00e9\\[>'\u001c_\u00a5@\u00d4`y\u0018\u00af2\u00ea\u00c1\u0083x\u00b7C\u00b05k8S\u00b9\u00c3\u00e4\u00fa\u00fa<\u00c6\u00ffJ\u0090\u0018\u0010\u009f!\u00d0fKNx\u00f0t\u00bed\u00d7\f5Ym(9\u00feu\u00df\f4\u0088\u001aA\u0089\u00e1.@TcH|\u00f6_4H\u0007q\u00d2mqo2\u0083\u00870\u00cbG\u00a37~\u0001\u00fe\u00e2\u00cd\u0018\u00faW\u00d0\u00c1\u00e4\u00c4S\u00bc\u00b5]\u00a2\u00fd%\u001aN\u00b2\u0085_\u00ed\u00a6\f\u00f3\u0091i\u0018\u0004s\u00d1.\u00a0\u0093O\u00e5\u001a\u0003\u00c9\u00f2^\u00be\u00f3?s\u0002G\u00a4\u0006}?l\u0010\u009a,\u00ef\u00c2\u009a\u00b9\u00b1b\u00b0\u00ad\u00e5\u00a7\u00d2n\u00d4\u00a9\u0018\u00d5\u0011\u000e?A\u00d4\u00f4\u0010\u008f\u0007\u00ec\u008a\u00abk\u00b0\u00bdvN\u009c\u00ed\u00e8\u00a3]\u00fb(\u0082\u0098\b\u0080\u001b\u008e1^\u0085\u00fc\u00a7\u0014\u00ea\u0080\u00e7mm]\u00f4\u0011\u00fcCw\u00fa\u0082\u00e0Ru>`\u00dfbH,`]\u00aa\u00fa\u00ef\r8n\u00ff{\u001d\u00a5\u00ce\u00c4\u00c6c\u009c\u00bdd[\u00c84\u0003\u00e3m\u00c1\u00fa\u00f3\u00baK1\u0016=\u00b5\u00bdV\u00d8\u008f\u0086\u00f53\u0003\u000e\u008e\u00d7\u00acD\u00c4\u00f1W+_\u0089\u00ce.\u000b\u00b6Sk\u00cdh\u0013\u00e6p\u0000\u009e\u00b6%(Q\u00b7\u00bbG\u009eLo\u00e4\u00b8Pp\u00c43\u00c2\"\fjzyn\u00d8\u00f9m\u0085\u0019~\u001e\u00a0\u00c1-\u001d\u00bax\u0096\u009eK\u00e3<}\u00a4Nk\u00b0\u00fdRr\u00cb\u0082\u00a3\u00c2<\u00f4\u0015\u0010tM\u00ff\u0001\u00db\u00d9\u00ba\u00f8\u00c1GL\u0091\u009d\u00fa\u00f0=<\u00a1`\u00f7V\u000e\u00aaQ=Q\u00b5\u0010\u00db\u00ee\u008c\u00e8\u009c\u00e1\u00b5f\u009f\u0016*\u00e4%u\u00f0\u009f[\u008bY\u00c1\u00e2\u00ba4\u00d8\u00e9\u0018m\u0094^6\u00e8\u00cf\u00cd{g\u00eb\u0091\u00f4\u00db\u00a9X\u00b4\u008b\u00d6\u00fe\u00dfR\u00ac\u00c8\u0094(\u00fd\u0080\u00bb\u00d9\u00de\u00c1\u00ea9\u001b\u00825\u00d1\u00f5\u0086\u00cb\u0017\u0005\u009d\u00d5\u008a\u00d3\u0010\u008b\u0085\u0098\u00c5\u0014/\u00ba\u00f3\u00c4\"k\u00f0^/s?\u0092\u00eb\u0010\u00f6\u001fU\u00c7\u008b\u00bf\u00d5\u00ac\u00d3V\u00ba\u00b2_/n\u00b4\u0010\u00ba\u00d2_\u00b0L\u00b7e\u00b9\u008cR\u00cf\u0018\u00cd\u00dfF:";
                                var28_7 = 756;
                                var25_8 = 32;
                                var24_9 = -1;
lbl22:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_9;
                                    v4 = var26_6.substring(v3, v3 + var25_8);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl27:
                                // 1 sources

                                while (true) {
                                    var29_4[var27_5++] = TargetInfoWidget.zkmDecodeUtf8(var30_10).intern();
                                    if ((var24_9 += var25_8) < var28_7) {
                                        var25_8 = var26_6.charAt(var24_9);
                                        ** continue;
                                    }
                                    var26_6 = "\u0085\u00f2W'\u0095\u0088#@\u00edI\u00a7p\u0094\u0082\u0081\u00b78\u00d0\u00afd\u0082Dqp\u008d4\u00df\u00caA\u009f\u0094\u00a2\u0098R\u0000\u0085\nv-3\u0093\u0083\u0006J\u007fwW\u00b6\u00c8}\u0014\u00b4;f\u00f9=\u00175\u008a]\u0010\u0017\u00d3b%x\u00b8\u00fa\u00c6@(4U";
                                    var28_7 = 73;
                                    var25_8 = 16;
                                    var24_9 = -1;
lbl36:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_9;
                                        v4 = var26_6.substring(v6, v6 + var25_8);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl41:
                                // 1 sources

                                while (true) {
                                    var29_4[var27_5++] = TargetInfoWidget.zkmDecodeUtf8(var30_10).intern();
                                    if ((var24_9 += var25_8) < var28_7) {
                                        var25_8 = var26_6.charAt(var24_9);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var30_10 = var22_2.doFinal(v4.getBytes("ISO-8859-1"));
                            switch (v5) {
                                default: {
                                    ** continue;
                                }
                                ** case 0:
lbl53:
                                // 1 sources

                                ** continue;
                            }
                        }
                        TargetInfoWidget.b = var29_4;
                        TargetInfoWidget.c = new String[23];
                        TargetInfoWidget.k = new HashMap<K, V>(13);
                        var11_11 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_12 = 1; var12_12 < 8; ++var12_12) {
                            v9 = v9;
                            v9[var12_12] = (byte)(var31 << var12_12 * 8 >>> 56);
                        }
                        var11_11.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_13 = new long[35];
                        var14_14 = 0;
                        var15_15 = "\u00dc\u00c5\u00a1\u00cf\u009b:p-[\u001e#\u008b\u001e7\u000e\u00d4\u0010\u0092u\u0087\u00c5\u001b\u00dd\u00ec\u0095\u000b\u00f0\u00f7R\u00dew\u00b5\u00bb\u001c\u0084H\u008aD\u00e0f?<\u008cW\b\u00cd\u00bc\u00c9k__\u0005\u00ef\u0011\u009f\u00a0\u009bI\u009c4\u00eb\u00ed\u00d8\u00ce\u0093kYk\u00bcr\u00f6G\u00c1+(\u008b\u0099(\u00c0H*>\u0095ex\u009a\u00d9\u00be\u00edt\\3Ug\u0098\u00cc\\4V(\u00c7#\u0093\u0007!\u00f4.l-\u00fa\u00b1b\u0087@a!`}\u0003\u0016\u000b\"\u00ae\u00f1\u001f\u001b(\u0096\u00fb\u001e\u0007\u00ee\u00e4k6\u00e9j\u0098#\u00eby\u0099\u001a\u00b3m\u008c\u0004\u00eb\u00d1\u008d\u00b4|\u00e4\u00cd\u00bd\u0012M-?\u00dcuc\u00c2\u00e9\u0096\u00a4p\u00f18\u0013K#\u0011\u0010\u0012' E\u00a9X\\H\u009dy8\u00a6\u0093\u000e^\u00ba\u00c8\u00fd\u00e7\u00a1\u00e5\u0082\u00f8<<T\u00dc/7\u00a1\u00c4\u00f9\u00e9\u00f8`\t\u00a8k\u00db\u00f7t\u0094\u00f7\u0000\u008d\u008a\u00e5\u00bc\u009b~\u001b\u00f9\u0081\u00e5\u00e4hG\u0007\u00b8\u00a0G\u00ef\u00faV\u00cc\u007f'\u0004\u00a2\u00a0/\u001f\u00cd\u00f4\u00e4G\u00c4\u0093\u00fb\u00a9\u0015\u008f\u0088\u0004\u00eaQ\u00b7~C\u00ad";
                        var16_16 = 264;
                        var13_17 = 0;
                        while (true) {
                            var18_18 = var15_15.substring(var13_17, var13_17 += 8).getBytes("ISO-8859-1");
                            v10 = var17_13;
                            v11 = var14_14++;
                            v12 = ((long)var18_18[0] & 255L) << 56 | ((long)var18_18[1] & 255L) << 48 | ((long)var18_18[2] & 255L) << 40 | ((long)var18_18[3] & 255L) << 32 | ((long)var18_18[4] & 255L) << 24 | ((long)var18_18[5] & 255L) << 16 | ((long)var18_18[6] & 255L) << 8 | (long)var18_18[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl80:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_17 < var16_16) ** continue;
                            var15_15 = "\u009b\u00c8\u00f7\u00fe-\u0002?\u00c1\u0004\u0017W)7\u001e\u00b3\u00b7";
                            var16_16 = 16;
                            var13_17 = 0;
                            while (true) {
                                var18_18 = var15_15.substring(var13_17, var13_17 += 8).getBytes("ISO-8859-1");
                                v10 = var17_13;
                                v11 = var14_14++;
                                v12 = ((long)var18_18[0] & 255L) << 56 | ((long)var18_18[1] & 255L) << 48 | ((long)var18_18[2] & 255L) << 40 | ((long)var18_18[3] & 255L) << 32 | ((long)var18_18[4] & 255L) << 24 | ((long)var18_18[5] & 255L) << 16 | ((long)var18_18[6] & 255L) << 8 | (long)var18_18[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl93:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_17 < var16_16) ** continue;
                            break block24;
                            break;
                        }
                    }
                    var19_19 = v12;
                    var21_20 = var11_11.doFinal(new byte[]{(byte)(var19_19 >>> 56), (byte)(var19_19 >>> 48), (byte)(var19_19 >>> 40), (byte)(var19_19 >>> 32), (byte)(var19_19 >>> 24), (byte)(var19_19 >>> 16), (byte)(var19_19 >>> 8), (byte)var19_19});
                    v14 = ((long)var21_20[0] & 255L) << 56 | ((long)var21_20[1] & 255L) << 48 | ((long)var21_20[2] & 255L) << 40 | ((long)var21_20[3] & 255L) << 32 | ((long)var21_20[4] & 255L) << 24 | ((long)var21_20[5] & 255L) << 16 | ((long)var21_20[6] & 255L) << 8 | (long)var21_20[7] & 255L;
                    switch (v13) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl106:
                        // 1 sources

                        ** continue;
                    }
                }
                TargetInfoWidget.g = var17_13;
                TargetInfoWidget.h = new Integer[35];
                var1_21 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var2_22 = 1; var2_22 < 8; ++var2_22) {
                    v17 = v17;
                    v17[var2_22] = (byte)(var31 << var2_22 * 8 >>> 56);
                }
                var1_21.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var0_23 = new long[2];
                var4_24 = 0;
                var5_25 = "s\u00e3\u009e\u0093\u0095\u0089:\u00eas\u00e3\u009e\u0093\u0095\u0089:\u00ea";
                var6_26 = 16;
                var3_27 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl127:
                // 1 sources

                while (true) {
                    var0_23[v18] = ((long)var10_30[0] & 255L) << 56 | ((long)var10_30[1] & 255L) << 48 | ((long)var10_30[2] & 255L) << 40 | ((long)var10_30[3] & 255L) << 32 | ((long)var10_30[4] & 255L) << 24 | ((long)var10_30[5] & 255L) << 16 | ((long)var10_30[6] & 255L) << 8 | (long)var10_30[7] & 255L;
                    if (var3_27 < var6_26) ** continue;
                    break block26;
                    break;
                }
            }
            var7_28 = var5_25.substring(var3_27, var3_27 += 8).getBytes("ISO-8859-1");
            v18 = var4_24++;
            var8_29 = ((long)var7_28[0] & 255L) << 56 | ((long)var7_28[1] & 255L) << 48 | ((long)var7_28[2] & 255L) << 40 | ((long)var7_28[3] & 255L) << 32 | ((long)var7_28[4] & 255L) << 24 | ((long)var7_28[5] & 255L) << 16 | ((long)var7_28[6] & 255L) << 8 | (long)var7_28[7] & 255L;
            var10_30 = var1_21.doFinal(new byte[]{(byte)(var8_29 >>> 56), (byte)(var8_29 >>> 48), (byte)(var8_29 >>> 40), (byte)(var8_29 >>> 32), (byte)(var8_29 >>> 24), (byte)(var8_29 >>> 16), (byte)(var8_29 >>> 8), (byte)var8_29});
            ** while (true)
        }
        TargetInfoWidget.Y = new TargetInfoWidget(var35_1);
        TargetInfoWidget.i = new ArrayList<E>();
        TargetInfoWidget.K = new Animation(Easing.OUT_BACK, var0_23[1]);
        TargetInfoWidget.healthAnimation = new Animation(Easing.OUT_QUINT, var0_23[0]);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x338A;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/p3", exception);
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
            TargetInfoWidget.c[n2] = TargetInfoWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TargetInfoWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/p3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1916;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/p3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TargetInfoWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TargetInfoWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/p3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TargetInfoWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TargetInfoWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

