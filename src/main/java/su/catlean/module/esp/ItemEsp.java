/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix3x2fc
 *  org.joml.Vector4d
 */
package su.catlean.module.esp;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
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
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.ItemEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import org.joml.Vector4d;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.render.FrameBufferEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.GameRendererAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.HandChams;
import su.catlean.module.esp.ItemEsp$WhenMappings;
import su.catlean.module.esp.ItemEsp$onRender2D$1;
import su.catlean.module.esp.ItemEspFilterMode;
import su.catlean.module.esp.ItemEspShaderMode;
import su.catlean.module.esp.ItemGroup;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.EspRenderMode;
import su.catlean.render.Pipelines;
import su.catlean.render.Render2D;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.ShaderMode;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ItemListSetting;
import su.catlean.setting.ItemListValue;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ItemEsp
extends Module {
    @NotNull
    public static final ItemEsp INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting highlight$delegate;
    @NotNull
    private static final ModeSetting shaderMode$delegate;
    @NotNull
    private static final BooleanSetting merge$delegate;
    @NotNull
    private static final BooleanSetting onePlate$delegate;
    @NotNull
    private static final BooleanSetting shulkerLoot$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    @NotNull
    private static final ColorSetting outlineColor$delegate;
    @NotNull
    private static final ColorSetting highLightColor$delegate;
    @NotNull
    private static final FloatSetting lineWithValue$delegate;
    @NotNull
    private static final ModeSetting filter$delegate;
    @NotNull
    private static final ItemListSetting items$delegate;
    @Nullable
    private static WindowFramebuffer windowFramebuffer;
    private static boolean m;
    @NotNull
    private static final Render2D render2D;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private ItemEsp() {
        super("item-esp", Categories.getEsp(), null, null);
    }

    private final EspRenderMode getHighlight() {
        return (EspRenderMode)((Object)highlight$delegate.getValue(this, delegatedProperties[0]));
    }

    private final ItemEspShaderMode getShaderMode() {
        return (ItemEspShaderMode)((Object)shaderMode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final boolean getMerge() {
        return (Boolean)merge$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getOnePlate() {
        return (Boolean)onePlate$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getShulkerLoot() {
        return (Boolean)shulkerLoot$delegate.getValue(this, delegatedProperties[4]);
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[5]);
    }

    private final Color getOutlineColor() {
        return (Color)outlineColor$delegate.getValue(this, delegatedProperties[6]);
    }

    private final Color getHighLightColor() {
        return (Color)highLightColor$delegate.getValue(this, delegatedProperties[7]);
    }

    private final float getLineWithValue() {
        return ((Number)lineWithValue$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    private final void setLineWithValue(float f) {
        long l = 0L;
        l = a ^ l;
        lineWithValue$delegate.setValue(this, delegatedProperties[ItemEsp.zkmBootstrap2("f", (int)8871, (long)(0x4A6224AF25B4A984L ^ l))], Float.valueOf(f));
    }

    private final ItemEspFilterMode getFilter() {
        return (ItemEspFilterMode)((Object)filter$delegate.getValue(this, delegatedProperties[9]));
    }

    private final ItemListValue getItems() {
        return (ItemListValue)items$delegate.getValue(this, delegatedProperties[10]);
    }

    @Flow
    private final void onFrameBuffer(FrameBufferEvent frameBufferEvent) {
        block0: {
            if (windowFramebuffer == null || !m) break block0;
            frameBufferEvent.setFrameBuffer(windowFramebuffer);
            frameBufferEvent.cancel();
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        if (this.getHighlight() != EspRenderMode.OFF) {
            if (this.getHighlight() == EspRenderMode.SHADER) {
                this.ensureFramebuffer();
                this.renderItems(render3DEvent.getStack());
            }
            if (this.getHighlight() != EspRenderMode.SHADER) {
                for (ItemEntity itemEntity : this.getItemEntities()) {
                    switch (ItemEsp$WhenMappings.w[this.getHighlight().ordinal()]) {
                        case 1: {
                            Object var28_14 = null;
                            Color color = null;
                            Color color2 = this.getHighLightColor();
                            Box box = RenderState.INSTANCE.getRenderBox(itemEntity);
                            RenderState renderState = RenderState.INSTANCE;
                            RenderState.addFilledBox$default(renderState, box, color2, color, var28_14);
                            Object var36_5 = null;
                            Color color3 = null;
                            Color color4 = RenderUtil.INSTANCE.withAlpha(this.getHighLightColor(), 1.0f);
                            Box box2 = RenderState.INSTANCE.getRenderBox(itemEntity);
                            boolean bl = RenderState.addOutlineBox$default(RenderState.INSTANCE, box2, color4, color3, var36_5);
                            break;
                        }
                        case 2: {
                            Object var43_9 = null;
                            Color color = null;
                            Color color5 = RenderUtil.INSTANCE.withAlpha(this.getHighLightColor(), 1.0f);
                            Box box = RenderState.INSTANCE.getRenderBox(itemEntity);
                            boolean bl = RenderState.addOutlineBox$default(RenderState.INSTANCE, box, color5, color, var43_9);
                            break;
                        }
                        default: {
                            return;
                        }
                    }
                }
            }
        }
    }

    /*
     * Iterators could be improved
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Flow
    private final void onRender2D(Render2DEvent var1_1) {
        v0 = var2_2 = ItemEsp.a ^ 40215505063798L;
        var10_3 = v0 ^ 88725097034106L;
        v1 = v0 ^ 98702045089973L;
        var14_4 = (int)(v1 >>> 32);
        var15_5 = (int)(v1 << 32 >>> 32);
        v2 = v0 ^ 23186172915399L;
        var23_6 = (int)(v2 >>> 32);
        var24_7 = (int)(v2 << 32 >>> 48);
        var25_8 = (int)(v2 << 48 >>> 48);
        var36_9 = v0 ^ 75079598634156L;
        var42_10 = v0 ^ 73608213992197L;
        var55_11 = v0 ^ 34444846907033L;
        var66_12 = this.groupItems();
        var68_13 = var66_12;
        for (Iterator<E> var68_13 : CollectionsKt.sortedWith(var68_13, new ItemEsp$onRender2D$1())) {
            var69_15 = Math.min((float)((ItemEntity)CollectionsKt.first(var68_13.getItems())).age / 15.0f, 1.0f);
            var70_17 = RenderUtil.INSTANCE.worldToScreen(var68_13.f());
            var71_18 = var68_13.r();
            if (var70_17 == null) continue;
            var72_19 = 0.0f;
            for (Iterator<E> var74_27 : var71_18) {
                var75_31 = Fonts.getSF_B2().getWidth(var36_9, (String)var74_27);
                if (!(var75_31 > var72_19)) continue;
                var72_19 = var75_31;
            }
            var73_23 = (float)(var70_17.x + (double)((float)(var70_17.z - var70_17.x) / 2.0f) - (double)(var72_19 / 2.0f));
            if (this.getOnePlate()) {
                v3 = var1_1.getContext().getMatrices();
                Intrinsics.checkNotNullExpressionValue(v3, "pose(...)");
                ItemEsp.render2D.drawRoundedRect(v3, var73_23 - 3.0f, (float)(var70_17.y - 1.0 - (double)(12.0f * (float)var71_18.size())), var72_19 + 5.0f, 12.0f * (float)var71_18.size(), var69_15);
            }
            var74_27 = var71_18;
            var76_35 = 0;
            var77_36 = var74_27.iterator();
            while (var77_36.hasNext()) {
                var78_37 = var77_36.next();
                if ((var79_38 = var76_35++) < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                var80_39 = (String)var78_37;
                var81_40 = var79_38;
                if (!ItemEsp.INSTANCE.getOnePlate()) {
                    v4 = var1_1.getContext().getMatrices();
                    Intrinsics.checkNotNullExpressionValue(v4, "pose(...)");
                    ItemEsp.render2D.drawRoundedRect(v4, var73_23 - 3.0f + var72_19 / 2.0f - Fonts.getSF_B2().getWidth(var36_9, (String)var80_39) / 2.0f, (float)(var70_17.y - 13.0 - (double)(12.0f * (float)var81_40)), Fonts.getSF_B2().getWidth(var36_9, (String)var80_39) + 5.0f, 12.0f, var69_15);
                }
                v5 = Fonts.getSF_B2();
                v6 = var1_1.getContext().getMatrices();
                Intrinsics.checkNotNullExpressionValue(v6, "pose(...)");
                v7 = var73_23 - 1.0f + var72_19 / 2.0f - Fonts.getSF_B2().getWidth(var36_9, (String)var80_39) / 2.0f;
                v8 = (float)var70_17.y - 13.0f - 12.0f * (float)var81_40;
                v9 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(v9, "WHITE");
                FontRenderer.drawString$default(v5, v6, (String)var80_39, v7, v8, var10_3, RenderUtil.INSTANCE.withAlpha(v9, var69_15), false, null);
            }
            if (!this.getShulkerLoot()) continue;
            var77_36 = var75_32 = (Iterable)var68_13.getItems();
            var78_37 = new ArrayList<E>();
            var80_39 = var77_36.iterator();
            while (var80_39.hasNext()) {
                var81_41 = var80_39.next();
                var82_42 = (ItemEntity)var81_41;
                if (!(var82_42.getStack().getItem() instanceof BlockItem)) ** GOTO lbl-1000
                v10 = var82_42.getStack().getItem();
                Intrinsics.checkNotNull(v10, "null cannot be cast to non-null type net.minecraft.world.item.BlockItem");
                if (((BlockItem)v10).getBlock() instanceof ShulkerBoxBlock) {
                    v11 = true;
                } else lbl-1000:
                // 2 sources

                {
                    v11 = false;
                }
                if (!v11) continue;
                var78_37.add(var81_41);
            }
            var74_27 = ((List)var78_37).iterator();
            while (var74_27.hasNext()) {
                var75_32 = (ItemEntity)var74_27.next();
                var1_1.getContext().getMatrices().pushMatrix();
                var1_1.getContext().getMatrices().translate((float)var70_17.x - 58.2f, (float)var70_17.y - 4.0f);
                var1_1.getContext().getMatrices().scale(0.6f);
                v12 = var1_1.getContext();
                v13 = var75_32.getStack();
                Intrinsics.checkNotNullExpressionValue(v13, "getItem(...)");
                this.drawContainerPreview(v12, v13, 1.0f);
                var1_1.getContext().getMatrices().popMatrix();
            }
        }
        v14 = var1_1.getContext().getMatrices();
        Intrinsics.checkNotNullExpressionValue(v14, "pose(...)");
        ItemEsp.render2D.drawGradientBackground(v14, this.getColor(), var14_4, this.getOutlineColor(), var15_5, true);
        Fonts.getSF_B2().clearCache(var23_6, (char)var24_7, (short)var25_8);
        if (this.getHighlight() == EspRenderMode.FRAME) {
            v15 = VertexFormats.POSITION_COLOR;
            Intrinsics.checkNotNullExpressionValue(v15, "POSITION_COLOR");
            var60_43 = null;
            var61_44 = 4;
            var62_45 = false;
            var63_46 = this.getItemEntities().size() * 8;
            var64_47 = v15;
            var67_14 /* !! */  = new BatchedBuffer(var64_47, var63_46, var62_45, var61_44, var60_43);
            for (ItemEntity var69_16 : this.getItemEntities()) {
                var70_17 = RenderUtil.INSTANCE.getBoxCorners(var69_16);
                var71_18 = null;
                for (Vec3d var74_29 : var70_17) {
                    var75_33 = RenderUtil.INSTANCE.toCameraRelative(new Vec3d(var74_29.x, var74_29.y, var74_29.z));
                    if (!(var75_33.z > 0.0) || !(var75_33.z < 1.0)) continue;
                    if (var71_18 == null) {
                        var71_18 = new Vector4d(var75_33.x, var75_33.y, var75_33.z, 0.0);
                    }
                    var71_18.x = Math.min(var75_33.x, var71_18.x);
                    var71_18.y = Math.min(var75_33.y, var71_18.y);
                    var71_18.z = Math.max(var75_33.x, var71_18.z);
                    var71_18.w = Math.max(var75_33.y, var71_18.w);
                }
                if (var71_18 == null) continue;
                var72_22 = (float)var71_18.x;
                var73_26 = (float)var71_18.y;
                var74_30 = (float)var71_18.z;
                var75_34 = (float)var71_18.w;
                var83_48 = this.getHighLightColor();
                var84_49 = var75_34;
                var85_50 = var74_30;
                var86_51 = var73_26;
                RenderUtil.INSTANCE.outlineQuad((BatchedBuffer)var67_14 /* !! */ , var72_22, var86_51, var85_50, var84_49, var83_48);
            }
            BatchedBuffer.draw$default(var55_11, var67_14 /* !! */ , Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, null, new Matrix3x2f((Matrix3x2fc)var1_1.getContext().getMatrices()), null, 46, null);
        }
        if (ItemEsp.windowFramebuffer != null && this.getHighlight() == EspRenderMode.SHADER) {
            var67_14 /* !! */  = this.getShaderMode() == ItemEspShaderMode.DEFAULT ? ShaderMode.DEFAULT : null;
            v16 = this.getHighLightColor();
            v17 = ItemEsp.windowFramebuffer;
            Intrinsics.checkNotNull(v17);
            v18 = ItemEsp.windowFramebuffer;
            Intrinsics.checkNotNull(v18);
            v19 = this.getLineWithValue();
            v20 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v20, "WHITE");
            v21 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v21, "WHITE");
            HandChams.INSTANCE.d((ShaderMode)var67_14 /* !! */ , v16, v17, v18, false, false, 0.0f, 0.0f, 0.0f, v19, 0, v20, v21, 0, var42_10, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private final List groupItems() {
        List list;
        block6: {
            List list2 = new ArrayList();
            List list3 = this.getItemEntities();
            int n = ItemGroup.zkmIntConstant();
            List list4 = new ArrayList();
            for (ItemEntity itemEntity : list3) {
                block7: {
                    boolean bl;
                    list = list4;
                    if (n == 0) break block6;
                    if (list.contains(itemEntity) && n != 0) continue;
                    List list5 = new ArrayList();
                    ItemGroup itemGroup = new ItemGroup(list5);
                    itemGroup.getItems().add(itemEntity);
                    for (ItemEntity itemEntity2 : list3) {
                        double d;
                        ItemEntity itemEntity3 = itemEntity;
                        Entity entity = itemEntity2;
                        if (n != 0) {
                            bl = Intrinsics.areEqual(itemEntity3, entity);
                            if (n == 0) break block7;
                            if (bl) continue;
                            itemEntity3 = itemEntity;
                            entity = itemEntity2;
                        }
                        double d2 = (d = itemEntity3.squaredDistanceTo(entity) - (this.getMerge() ? 9.0 : 0.0)) == 0.0 ? 0 : (d < 0.0 ? -1 : 1);
                        if (n != 0) {
                            if (d2 >= 0) continue;
                            d2 = (double)list4.contains(itemEntity2);
                        }
                        if (n != 0) {
                            if (d2 != false) continue;
                            itemGroup.getItems().add(itemEntity2);
                            d2 = (double)list4.add(itemEntity2);
                        }
                        if (n != 0) continue;
                    }
                    bl = list2.add(itemGroup);
                }
                if (n != 0) continue;
            }
            list = CollectionsKt.distinct(list2);
        }
        return list;
    }

    private final void drawContainerPreview(DrawContext drawContext, ItemStack itemStack, float f) {
        ContainerComponent containerComponent = itemStack.get(DataComponentTypes.CONTAINER);
        if (containerComponent == null) {
            return;
        }
        ContainerComponent containerComponent2 = containerComponent;
        Item item = itemStack.getItem();
        Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
        Item item2 = item;
        if (!(item2 instanceof BlockItem) || !(((BlockItem)item2).getBlock() instanceof ShulkerBoxBlock)) {
            return;
        }
        if (containerComponent2.stream().toList().isEmpty()) {
            return;
        }
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        render2D.drawRoundedRect(matrix3x2fStack, 12.0f, -79.0f, 169.0f, 60.0f, 2.0f);
        int n = 0;
        int n2 = 0;
        for (ItemStack itemStack2 : containerComponent2.stream().toList()) {
            drawContext.drawItem(itemStack2, 16 + n2 * 18, -75 + n * 18);
            drawContext.drawStackOverlay(Globals.getMc().textRenderer, itemStack2, 16 + n2 * 18, -75 + n * 18);
            if (++n2 < 9) continue;
            n2 = 0;
            ++n;
        }
    }

    private final void renderItems(MatrixStack matrixStack) {
        boolean bl;
        boolean bl2;
        block2: {
            int n = ItemGroup.zkmIntConstant();
            Camera camera = Globals.getMc().gameRenderer.getCamera();
            Intrinsics.checkNotNullExpressionValue(camera, "getMainCamera(...)");
            Camera camera2 = camera;
            double d = RenderState.INSTANCE.getTickDelta();
            int n2 = n;
            Boolean bl3 = Globals.getMc().options.getEntityShadows().getValue();
            Intrinsics.checkNotNullExpressionValue(bl3, "get(...)");
            bl2 = bl3;
            Globals.getMc().options.getEntityShadows().setValue(false);
            GofraState.INSTANCE.setModifyBuffer(true);
            m = true;
            RenderSystem.backupProjectionMatrix();
            GameRenderer gameRenderer = Globals.getMc().gameRenderer;
            Intrinsics.checkNotNull(gameRenderer, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
            RenderSystem.setProjectionMatrix(((GameRendererAccessor)((Object)gameRenderer)).getLevelProjectionMatrixBuffer().set(RenderState.INSTANCE.getProjection()), RenderSystem.getProjectionType());
            for (ItemEntity itemEntity : this.getItemEntities()) {
                bl = Globals.getMc().getEntityRenderDispatcher().shouldRender((Entity)itemEntity, RenderState.INSTANCE.getFrustum(), camera2.getCameraPos().x, camera2.getCameraPos().y, camera2.getCameraPos().z);
                if (n2 != 0) {
                    if (!bl && n2 != 0) continue;
                    double d2 = MathHelper.lerp(d, itemEntity.lastRenderX, itemEntity.getX()) - camera2.getCameraPos().x;
                    double d3 = MathHelper.lerp(d, itemEntity.lastRenderY, itemEntity.getY()) - camera2.getCameraPos().y;
                    double d4 = MathHelper.lerp(d, itemEntity.lastRenderZ, itemEntity.getZ()) - camera2.getCameraPos().z;
                    Object object = Globals.getMc().getEntityRenderDispatcher().getRenderer((Entity)itemEntity).getAndUpdateRenderState((Entity)itemEntity, RenderState.INSTANCE.getTickDelta());
                    Intrinsics.checkNotNull(object, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.state.ItemEntityRenderState");
                    ItemEntityRenderState itemEntityRenderState = (ItemEntityRenderState)object;
                    GameRenderer gameRenderer2 = Globals.getMc().gameRenderer;
                    Intrinsics.checkNotNull(gameRenderer2, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
                    CameraRenderState cameraRenderState = ((GameRendererAccessor)((Object)gameRenderer2)).getLevelRenderState().cameraRenderState;
                    Intrinsics.checkNotNullExpressionValue(cameraRenderState, "cameraRenderState");
                    object = cameraRenderState;
                    Globals.getMc().getEntityRenderDispatcher().render((EntityRenderState)itemEntityRenderState, (CameraRenderState)object, d2, d3, d4, matrixStack, Globals.getMc().gameRenderer.getEntityRenderCommandQueue());
                    if (n2 != 0) continue;
                }
                break block2;
            }
            Globals.getMc().gameRenderer.getEntityRenderDispatcher().render();
            Globals.getMc().getBufferBuilders().getEntityVertexConsumers().draw();
            Globals.getMc().gameRenderer.getEntityRenderCommandQueue().clear();
            RenderSystem.restoreProjectionMatrix();
            bl = false;
        }
        m = bl;
        GofraState.INSTANCE.setModifyBuffer(false);
        Globals.getMc().options.getEntityShadows().setValue(bl2);
    }

    private final void ensureFramebuffer() {
        block3: {
            block5: {
                block4: {
                    block2: {
                        if (windowFramebuffer != null) break block2;
                        windowFramebuffer = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
                        break block3;
                    }
                    int n = Globals.getMc().getFramebuffer().textureWidth;
                    WindowFramebuffer windowFramebuffer = ItemEsp.windowFramebuffer;
                    Intrinsics.checkNotNull(windowFramebuffer);
                    if (n != windowFramebuffer.textureWidth) break block4;
                    int n2 = Globals.getMc().getFramebuffer().textureHeight;
                    WindowFramebuffer windowFramebuffer2 = ItemEsp.windowFramebuffer;
                    Intrinsics.checkNotNull(windowFramebuffer2);
                    if (n2 == windowFramebuffer2.textureHeight) break block5;
                }
                WindowFramebuffer windowFramebuffer = ItemEsp.windowFramebuffer;
                Intrinsics.checkNotNull(windowFramebuffer);
                windowFramebuffer.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
            }
            CommandEncoder commandEncoder = RenderSystem.getDevice().createCommandEncoder();
            WindowFramebuffer windowFramebuffer = ItemEsp.windowFramebuffer;
            Intrinsics.checkNotNull(windowFramebuffer);
            GpuTexture gpuTexture = windowFramebuffer.getColorAttachment();
            Intrinsics.checkNotNull(gpuTexture);
            WindowFramebuffer windowFramebuffer3 = ItemEsp.windowFramebuffer;
            Intrinsics.checkNotNull(windowFramebuffer3);
            GpuTexture gpuTexture2 = windowFramebuffer3.getDepthAttachment();
            Intrinsics.checkNotNull(gpuTexture2);
            commandEncoder.clearColorAndDepthTextures(gpuTexture, 0, gpuTexture2, 1.0);
        }
    }

    private final List getItemEntities() {
        Iterable iterable;
        Iterable iterable2 = Globals.getWorld().getEntities();
        Intrinsics.checkNotNullExpressionValue(iterable2, "entitiesForRendering(...)");
        Iterable iterable3 = iterable = iterable2;
        Collection collection = new ArrayList();
        for (Entity entity : iterable3) {
            if (!(entity instanceof ItemEntity)) continue;
            collection.add(entity);
        }
        iterable3 = iterable = (Iterable)((List)collection);
        collection = new ArrayList();
        for (Entity entity : iterable3) {
            ItemEntity itemEntity = (ItemEntity)entity;
            ItemStack itemStack = itemEntity.getStack();
            Intrinsics.checkNotNullExpressionValue(itemStack, "getItem(...)");
            if (!INSTANCE.matchesFilter(itemStack)) continue;
            collection.add(entity);
        }
        return (List)collection;
    }

    private final boolean matchesFilter(ItemStack itemStack) {
        boolean bl;
        block6: {
            int n;
            block5: {
                n = ItemGroup.zkmIntConstant();
                bl = ItemEsp$WhenMappings.l[this.getFilter().ordinal()];
                if (n == 0) break block5;
                switch (bl) {
                    case 1: {
                        bl = true;
                        break block6;
                    }
                    case 2: {
                        bl = this.getItems().getItems().contains(itemStack.getItem());
                        break block6;
                    }
                    default: {
                        bl = this.getItems().getItems().contains(itemStack.getItem());
                    }
                }
            }
            if (n != 0) {
                bl = !bl;
            }
        }
        return bl;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getHighlight() == EspRenderMode.SHADER;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMerge();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean settingVisible3() {
        int n = ItemGroup.zkmIntConstant();
        ItemEsp itemEsp = INSTANCE;
        if (n != 0) {
            if (itemEsp.getShaderMode() == ItemEspShaderMode.DEFAULT) return true;
            itemEsp = INSTANCE;
        }
        if (itemEsp.getHighlight() != EspRenderMode.SHADER) return false;
        return true;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getFilter() != ItemEspFilterMode.NONE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ItemEsp.a = ZkmKeys.a(6183139388381466131L, -1542040225599438767L, MethodHandles.lookup().lookupClass()).a(211021558191093L);
                        var20 = ItemEsp.a ^ 82877304273015L;
                        ItemEsp.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[49];
                        var16_4 = 0;
                        var15_5 = "\u00d9\u00ffn\u00c7wD\u00ad\u00f8i\u00a9\u0000o\u00c9\u00b9\u0003\u00172k\u009aBx\u00fc\u0087\u00b8 \u00a0\u00c6A\u00ef\u00c7\u00dd1>.\u00dc\u00dbX\u00a4\u00d2\u00fa5\u00ce,\u008d\u00ea\u00cf\u00e3;\u00c6\u000e\u001c\u00f7Q\u00969R\u00b70Jv\u00d9-\u00c5\u00c3\u00e4\u00d4[D\u0004\u0086lOf\n\u00a0\u00dd-\u00ef\u0085\u00c1A\u0012a\u00182\u0099\u009fr\u00c4\u00b4\u00e2w\u0014\u00bb0\u00f1-!sJ\u00ba\u009a\u0007\u0011e\u0019 0\u00bafdV\u00bb\u008f\u00f8m%z\u00f0x\u00f4\u00c2Z\u00d0\u00ef^\u00cew\u00cd\u009c\u0087 2s\u00ce \u0001\u00ad\u00ff\u0010\u001bR\u0017\u00dbf\u00ab0\u00127m\b\u0003\u00c0\u00fe\u0099\u0098\u0018\u00ad\u00e3\rf1\u00db\u00f2\u00c1\u000b\u008dz\u00906r\u00bf\u00fej\u0006\u00acY\u0011D\u00adG \u00c0\u00d5X4\"\u0098l\u0090\u0015\u00da\u00f0\u00dd1E\u0082x\u00d8\u00f9\u0012`\u00d8F\u00d2\u00d0`z5\\\u0082\u00eb\u008d\u0019\u0018J\u00e5\u00f6\u008cU[D\u00f0N\u00c2\u00da{K\u00d9\u00a9\u00156\u00f6Q\u00cd\r\u00fa!](H\u00e3\u00ff\u008e\u0083\u0095\r2\u00d4\u0019>\u00d3\u0085\u00c1j\u00ac2\u00ef\u00cc\u0082\u00aa\u00ccnM\u00d4\u00a7C!]L\u0092j\u00ad\u00ea\u00b3&\u00a3\u00da\u00bf\u0083\u0010`\u00f5\"\u00e6\u00aaVL\u001d\u00ef\u0003\u00ca|\u0004\u0084@\u00c30(\u000fU\u00f1\u00e6\u007fx?\u0098\u00d6\u00bct\u00e3a4\u0086\u00ab\u00d6\u00a4#\u00f3^\u0016\u0006\u00e7M\u00d8\u008c\u00e9_W\u00ef\u0095>?\u0010y\u000e\u00aeB\u00e9\u000b\u0006L\u00bd\u00c0\u0082\u0000\u0018\u00f6\u0081\u00ed\u00c2\fe\u0016X`\u00a8\u00dcKn\u0000[h\u008a\u00e0\u00b0~\u00fe\u00dc\u00ebt8\u00e1\u0017\u00a8\u00b4\"\u00e0g\t\u0016#\u00abd\"\u00a8\u00ac\u0099\u00b9\u0081?\u00ad\\k\u00b2\u00eb#\u00cd\u0011\u00a7\u008d\u00df\u008da:$\u0013dJm\u00e83\u00f2\u00f3`\u0017-V\u00ec\u00c5\u00c4\u0019Pp\u00a6\t'^\u0010\u009am\u00e3\u00b1\u009f\u00d7\u00cbf\u0013\u00974\u0090\u00deOu\u00d3 \u001e+9}\u00c8\u00e6\u00d9\u0000\u00d3N\u00bfW\u00fa\ni\u00ec9e=\n\u0085\u00b4#\u0093$g]*\u00de\u00b2\u00f5\u0012 \u00ec!\u00fd\u00b6\u00e8M\u00f0D\u0083\u00a6\u00b5\u0089}\u00a4\u00f0s\u000e\u0090\u0006^\u00f1\u00f0L\u00cbs\f\u00cd\u0005\u008192? \u0091\u0095\u00f5\u00ebc\u00184~\u0019\b\u00e9\u008c\u00c2'p7\u00f2#\u00be\u00c0\u00de\u00d8\u00b7\u00fb\u0099\t\u00db\u0091&\u00fd|\u00c8 \u00a2\u007f\u008d\u00bc\u0098\u00c5\u008e*\u00bcI\u00df<NK\u00ff\u00c8\u0080h\fMB\u00055\"C\u00e5\u00f6\u00ce\u0089\u00af\u0019\u00ac \u00e9\t\u00c6K\b\u0015\u00bc\u00d4\u00a96\u0094\u00ff\u00ca;3\b1\u0003v\u00cb\u00ce(\u00b2Ss8P\u0091\u0098\u008e(\u00c8\u0018*\u0017_e\u0094\u00ffakW\u00d9\u00d3\u0084\u0083&\u00d1\u0010\u009f\u001b\f\u00f4\u0082\u00c82\u00f0\u0018\u00e6&\u0084\u0002\u001a\u0096\u00fb\u00d3\u0095\u0085\u0013B\u008a\u00c0L\u00ecN\u0081\u00f8p?\u00ec\u0081B 3\u0083T\u00bd,phTT\n6a \u007f\u00be\u001b?Yv+P\u001b\u00f9\u00c7I\u00f5\u00bd\u00e3\u009bcBf\u0018V\u00f0\u0007\u00f6\u0017\u000e//\u00d65\u00e6@\u0003\u00ba\u00ee\u00c8\u00ad\u00a6\u00f2`\u00cf\u0082\u0090\u0015\u0010\u00b4v\u00ce\u00f8)\u00f0\u00fd\u00b94\u0095\u0017b{\u0086\u00ad\u008b\u00a0\u0010\u00c5\u009f}\u00cc\u00ec\u0091wG\u00e1\u00e7nI\u00be/\u00d0\u00e4e\u00cc\u0006-\u00bf=\u00d7\f\u00cb\u00ef\u00ab>\u0084\u00a3@F\u0095\u00e2\u001dv\u00c3\u00c2i\u009bV\u0082vV\u00a06>\u00f4\u0001\u0097b\u00abB\u0016\u00c5\u0098\u00f4\u00db:\u0019\u00ae\u0087\u00bbB\u0097\u00c8\u00efjQ\u00a1e\u0019\u00f7\u008b\u00ee=\u00e4\u008bC]\u00aehu\u00ed|\u00b0\u00b1\u0089\u0088\u00db!\u00a2\u00f0EJ\u0095\u00fa\u000f\u0006\u0081\u00c9\u00d1X3\u0004\u00f3\u00a2\u00bf\u00150\u00ba\u001c\u00d5<\u00eb\u000b\u0000\u0082\u00d4!`\u0084\u00c1z\u00b4\u009f\u00faX\u0094#\u008e\u00b2\u00f1\u00e7=\u00e5N\u001c\u00f7\u00bf-\u00ab\u0011\u009e\u00f9\u00dbr\u001f\u00dc\t\u00ff\u00c1$\u0089\u00f2\u00bf1:@8\u00b5\u00c2FJ\u00eb\u00ac\u00d0\u00bc\u00eb\u00d9\u00fc\u009d\u0089\u00a0\u00c0\u00cb\u00a4^\u009b0>\u00b4Nl\u00fd?\u00efs[<\n\u00cf@C\u0000 \u008a+\u00ba^\u0002\u00d7\u00f5\u00ea\u00b7\u00b1\u00d1\u00dd&l\u0086@\u00cb\u0011\u00b4E \u00b3\u00a4.\u0015h\u001dO\u00dbp\u00d2__\f4\u00f55G\u008a\u009bZ\u00d7'CW\u008b\u00cbh\u009dw\u00acR/\u0010O\u00f9\u0003y\u00c9\u00ea$\u00f7\u00d9\u00f9q\u001d\u00acN\u0094\u00e4 \u0086^\u00d2\u0080\u00d8\u00aa\u000b!)\u0095\u00ef\u00f41\u00f7\u00f9\u0088\u00ee\u00fbB\u001e\u00b9s&f\u00dd\u00c7\u00b4&\u00ca\u009d\u0099\u00b5 \u009e!\u0016Lxm\u00bc\u00d1\u0014T]\u008a\\\u00ad*J\u00ebR\u00c5j\\\u0001\u00d5\u0011\u008eI!\u00e1S\r~\u00fd\u0018\u00ech\u00a2|\u00ad\u0097{\u0096\u00aa\u00d4\u001f\u0019\u00f4\u00b0\u00a5@\u00d4\u00a6>!\u00ce\u00c4\u009e\u008b\u0010b\u001eb\u00b5H0Z2\u00ab\u00175\u00fc'\u0007K*(\u00b7\u00b3\u00e8\u00c2[k\u00e0\b-\u00dbu\u00f0\u00a5\u00ee?\u009d\u00f9\u008e(\u0098\u00b1J\u00b9\u0003\u00ban\u00ac\u00d6t\u00d06\b\u0002l\u00fcRt\\E\u00b8 CZ\u00f2`\fkjD\u0095\u00c7:\u00cd\u00c2\u0005\u009cb\u00b4'\u00ef\u00f4\u00b4r\u0080\u00d8\u0086\u0001p\u0003\u00f3\u0092\u00f6\u00da0\u00b7\u00a0k\u000f\u00e5z\u0098\u008f\u0095+U\u0080y\u009f\u00ee\u00f2a\u0003\u00f1+\u009d\u0013\u00d9\u00bd\u00f8\u0007\rm\u00e3\u00d9\u00e3\u009aS\u00a5\u00b4\u0004\nw\u00b2n\u00d3\u00a15\u001c\u00d9;\u000fw(\u00b1\u00c5`\u008a#\u00de\u009f\u00ab\u00c3\u00a5\u0099\u008e\u00b6e$\u00e9\u008a4L\u00fci\u00b1\u0098@\u00ecc\u00d4nYPddi@B\u00dd\u00a5)r\u001c \\\u00a6;\u00c8\u00c9\u00df4P\u00ba\u00f1\u00c2K\u00c7At*\u00d9Z\u00b0<9 \u00d3\u00cf\u00ab\u0004\f\u00c7\u00c4\u00a7=%\u0010\u00beB:8-\u00fb\u00e7\u00e5\u00c3v\u00b7\u00b6Zg\u00f8\u00e0\u0090\u00a1(\u0018\u00cd\u00ae(\u00e2\u008b\u0013\b\u00a7\u009b\u00cb\u001b\u00f5\u00ca\u00ce\u00cb\u007f\u00e7B5\u001b\u00075\"\u0002\u00e9\u008dG]o!\u0006\u00fd\u00ec\u00c4\u007f\u00ec\u0002\u00f7#\u00f0\u0002bB\u00b1\u00cd;\u00f37y\u00bb\u0098'\u00cb\u00b2\u00f5\u00d2n-\u00f2-\u00d6\u00f7\u00edDzY\u008f`\u00deh=59.\u00df\u0097\u00e1\u0090\u00f3yO\u009ak\u00acN\u0015\u0092\u0089\u00d8\u00bd\u001f\u0012\u008f\u00d6|\u0090\u00b5c\u00aa\u00b6\u0012^\u0014\r\u001dP\u001e\u00f2I\u0000\u00e1Pl\u00d2t\u00f3\u00be\u0003\u00e44=V\u00db\u00c2\u00b0\u0097&2\u00d7\u00b52[\u00cf\u00b8\u008c\u00df\u00c8\u0089\u00d1\u0085\u0086@\u00fc\b.\u00c0*\u001d\u0083#\u001e.\u00ce\u009dvm\u00b2\u0095\u001e\u00e9;>\u0010\u00ef\n\u0018\u0006P\t\u00f4t\u00e0s\u0010\u00e8\u00d3\u00b2\u001e\u00f4\r\u00c5\u007f\u0019\u009cq\u0093}2\u009e\b\u00b5\u0084=a:\u00d4\f\u0013B\u00c7s\u000f\u00fa\u00e9i\u00e4p\u0012\u000f\u001f@\u00fcv+\u00d3E\u00a7z\u00f3d\u0016<\u00f6R\u00a9\u009d\u00f2\u00ff\u008a\u00e1]\u0004\u00ee\u00b1\u00ad\u0083@\u00aa\u0016\u00d4\u009c\u001b\u00edX\u0081\u00ff\u0088.2\u00fb\u0094\u00a0L\u0085\u0014tct\u0011+\u009e\u00bd\u009e\u00c6K\u008c\u00f7\t\ro\u00a7'\u00da\u0019>\u0006\u0012]\\6\u0080\u0097\u00f0\u008d\u00d6\u0014\u00f4J\u00a8\u00c0o|!\u0091\u00fbl\u00dal\u0002@\u009a\u001e\u00a08\u001a\u00adfZ\u0088\u00f8\u001a\u0004\u00194\u00c0\u0017\u00dc\u0012\u00030\u0096\u0085\u00fe\u00a5\u000e/r\u00fe\u00cee{\u00c7\u0000\u00fa\u00cf#\u001b8\u0012\u009c\u00ac\u0003\u00c2o\u0001ne\u009ba\u00815\u0014\u00cb=I\u0083\u00c3\u00dem\u00af\u00eb\u00c6\u00f49$\u0011\u0003o\u0010#5\u00a3\u00bcp\u0081\u00eb\t\u0085\u00c3\u00f2*Y\b\u0092y\u0010z\u0010\u00bd\u00e7\u00ae\u00f5\u00bc\u00ce\u00f8\u00de\u00c6\u0087F%\u00c5\u00c7\u0080y\b31\u0094\u008c\u000f\u0088\u00d67\u00bb\u0092\u0016\u00c9\u00160\u001e\u00a0m\f9\u00dc\u0015\n\u00d8\u00a4\u00cc\u0002\u001cl(W\u00a1\u00c93\u00c9\u00a3\u00fd\u00d9\u0089[=\u0082\u0091A\u00c5\u0096!\u00b8\u00ffE,7d7F\u009c\u00ff\u00fd}\u00dd\u00c5>\u00c6V\u00bc\u00ac\u00be\u00ca\u007f\u00b5I\u0082oS\u00b6\u00e6\u00e3\u0080\u009c\u0088\u0088*\u00c3\u0080}a!\u00820\u0096\u00c2\u0099a8\u0088\u008b.Y\u0088J\u00a7\u00ef\u009c\u00b2\u00dbQ\u008e\u0010\u007f<\u00de\u007f\u0085t\u00ec\u00c0TZmLqsa\r5=v@I\u0006\u00fcQ\\0f\u00b9\u00aa^\u008d\u00de\u0083^ \u0088\u00c6D\u00d0\u00c3\u0014\u00ab\u0002\u00dc\u008e\u00f8\u00fa\u001c\u00bf8\u00ed\u00fb6*\u0086\u00bc\u0084\u00b4\u00a9\u00a8+e\u00cc\u008b\u00ea?e\n\u001eB\u00f8v\u00a2\u00b4\u00b2\u0007\u0019`8\u00ec^\u00e7\u0085\u00dd\u0010\u00a1\u001c\u00fdM\u00dd\u00d9E\u00a9Xo\u00ea\u0080-(A^";
                        var17_6 = 1942;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = ItemEsp.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c0\u00fe\u0011\u00a3;\b\u00c3\u00bc\u008e\u0081\u0017\u00d4\u00b7\u00fa\u00a1@\u0092\u00ae\u0091\u00fe\u00d1\u00c0U\u008f\u00a0\u00b1\u00bd\u008e\u0014\u009eg>\u00b1\u00a9O\u00a9A\u00b4\u009d\u009b\u0010\u001d\u00f6YB\u009a\u00b0\u0004\u0082x\u00c8\u00bc\u00ffK9eA";
                            var17_6 = 57;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = ItemEsp.zkmDecodeUtf8(var19_9).intern();
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
                ItemEsp.b = var18_3;
                ItemEsp.c = new String[49];
                ItemEsp.g = new HashMap<K, V>(13);
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
                var4_14 = "\u00c7u\u00b1\u00f9\u00c0\u0095f\u00ae\u00a1\u0086'\u009d\u0015\u00d5C\u00ae\u00da]\u00a1T8\u00f24\u0001\u00b1k\u0083\u0005f}\u001b\u0015,\u0081\u00a3\u00b8\u00e2B..\u00bf\u00ea\u009f\u00e4c\u001d\u000b\u00da(\u00b0\u00e5\u0088\u00d0?\u00c01\u00c2Mt\u0006\u0082\u00be^`l\u0097S5\t13C\u00ca\u000fN1t\u00d7\u000e\u0016\u00e2\u0083\u00ef\u00e4?\u00e1\u00abar#\u00e0A\u0096\u00ed\u00fdy[\u00caA\u00deb\u00f7\u0081\u00dbF\u00ba\u00aa\u00af(\u0093\u0082V\u00e7\u0097z\u00f71je\u00e3\u00acX\u00bc\u008e\u00de\u00a2\u00f4%\u00a9Cvj\u00f50\u00a2q\u00d7\u0083D\u00f1\u001c\u00eb\u00fd\u00fe\u00b09\u00f9\u00daC\u00f3\u00d6\u0000\u00b7BS-\u00dd\u0087\f{\u00a7\u0097\u0096\u00feu\u00b6p\u00b7\u009e\u00cf6\u000e\u00d7\u00b0,\u00bd";
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
                    var4_14 = "\u00a8\u009eM\u00e35\u0094\u008e[PL\u00d7E\u00de\u000f%\u009a";
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
        ItemEsp.e = var6_12;
        ItemEsp.f = new Integer[24];
        var37_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "highlight", "getHighlight()Lsu/catlean/w;", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "shaderMode", "getShaderMode()Lsu/catlean/r3;", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "merge", "getMerge()Z", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "onePlate", "getOnePlate()Z", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "shulkerLoot", "getShulkerLoot()Z", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "color", "getColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "outlineColor", "getOutlineColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "highLightColor", "getHighLightColor()Ljava/awt/Color;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ItemEsp.class, "lineWithValue", "getLineWithValue()F", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "filter", "getFilter()Lsu/catlean/gh;", 0)), Reflection.property1(new PropertyReference1Impl(ItemEsp.class, "items", "getItems()Lsu/catlean/nv;", 0))};
        ItemEsp.delegatedProperties = var37_20;
        ItemEsp.INSTANCE = new ItemEsp();
        ItemEsp.highlight$delegate = SettingFactory.modeSetting$default((SettingContainer)ItemEsp.INSTANCE, "highlight", (Enum)EspRenderMode.OFF, null, null, 12, null);
        ItemEsp.shaderMode$delegate = SettingFactory.modeSetting$default((SettingContainer)ItemEsp.INSTANCE, "shader-mode", (Enum)ItemEspShaderMode.DEFAULT, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        ItemEsp.merge$delegate = SettingFactory.booleanSetting$default((SettingContainer)ItemEsp.INSTANCE, "merge", true, null, null, 12, null);
        ItemEsp.onePlate$delegate = SettingFactory.booleanSetting$default((SettingContainer)ItemEsp.INSTANCE, "one-plate", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        ItemEsp.shulkerLoot$delegate = SettingFactory.booleanSetting$default((SettingContainer)ItemEsp.INSTANCE, "shulkers", true, null, null, 12, null);
        ItemEsp.color$delegate = SettingFactory.colorSetting$default(ItemEsp.INSTANCE, "fill", new Color(150, 150, 150, 255), null, null, 12, null);
        ItemEsp.outlineColor$delegate = SettingFactory.colorSetting$default(ItemEsp.INSTANCE, "outline", new Color(-1), null, null, 12, null);
        ItemEsp.highLightColor$delegate = SettingFactory.colorSetting$default(ItemEsp.INSTANCE, "highlight-color", new Color(-1), null, null, 12, null);
        ItemEsp.lineWithValue$delegate = SettingFactory.floatSetting$default(ItemEsp.INSTANCE, "line-width", 4.0f, RangesKt.rangeTo(1.0f, 4.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 8, null);
        ItemEsp.filter$delegate = SettingFactory.modeSetting$default((SettingContainer)ItemEsp.INSTANCE, "filter", (Enum)ItemEspFilterMode.NONE, null, null, 12, null);
        var38_21 = null;
        var39_22 = 4;
        var40_23 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)();
        var41_24 = null;
        var42_25 = new ItemListValue(null, null);
        var43_26 = "items";
        var44_27 = ItemEsp.INSTANCE;
        ItemEsp.items$delegate = SettingFactory.itemListSetting$default(var44_27, var43_26, var42_25, var41_24, var40_23, var39_22, var38_21);
        ItemEsp.render2D = new Render2D();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x46D7;
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
                throw new RuntimeException("su/catlean/kn", exception);
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
            ItemEsp.c[n2] = ItemEsp.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemEsp.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kn" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3666;
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
                throw new RuntimeException("su/catlean/kn", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ItemEsp.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ItemEsp.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kn" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemEsp.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ItemEsp.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

