/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.gui;

import java.awt.Color;
import java.lang.invoke.CallSite;
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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.gui.CategoryGroup;
import su.catlean.gui.CategoryPanel$1;
import su.catlean.gui.ClickGui;
import su.catlean.gui.ModuleButton;
import su.catlean.gui.SettingElement;
import su.catlean.gui.SubCategoryTab;
import su.catlean.module.Category;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.render.Rect;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.SettingParent;
import su.catlean.sound.Sounds;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.FpsCounter;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CategoryPanel {
    @NotNull
    private final CategoryGroup category;
    private int offset;
    private int panelX;
    private int panelY;
    private int currentX;
    private int currentY;
    private float n;
    private float panelHeight;
    @NotNull
    private SubCategoryTab selectedTab;
    @NotNull
    private SubCategoryTab previousTab;
    private int t;
    @NotNull
    private final ArrayList tabs;
    @NotNull
    private final ArrayList moduleButtons;
    @NotNull
    private final Animation openAnimation;
    @NotNull
    private final Animation settingsAnimation;
    @NotNull
    private final Animation hoverAnimation;
    @Nullable
    private ModuleButton moduleButton;
    private boolean T;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;
    private static final long[] k;
    private static final Long[] l;
    private static final Map m;

    public CategoryPanel(char c, @NotNull CategoryGroup categoryGroup, long l, int n) {
        long l2 = ((long)c << 48 | l << 16 >>> 16) ^ a;
        long l3 = l2 ^ 0x31F8351DBAC4L;
        Intrinsics.checkNotNullParameter(categoryGroup, "category");
        this.category = categoryGroup;
        this.offset = n;
        this.panelX = (int)((float)Globals.getMc().getWindow().getScaledWidth() / 2.0f + (float)(this.offset - 2) * 125.0f);
        this.panelY = 100;
        this.currentX = this.panelX;
        this.currentY = this.panelY;
        this.n = 120.0f;
        this.panelHeight = 350.0f;
        this.t = -1;
        this.tabs = new ArrayList();
        this.moduleButtons = new ArrayList();
        this.openAnimation = new Animation(Easing.OUT_QUINT, 300L);
        this.settingsAnimation = new Animation(Easing.OUT_EXPO, 400L);
        this.hoverAnimation = new Animation(Easing.OUT_QUINT, 200L);
        this.T = true;
        int n2 = 0;
        Iterable iterable = ModuleManager.INSTANCE.L();
        iterable = CollectionsKt.sortedWith(iterable, new CategoryPanel$1());
        for (Object t : iterable) {
            Module module = (Module)t;
            if (!this.category.getSubCategories().contains(module.getCategory())) continue;
            this.moduleButtons.add(new ModuleButton(this, l3, module, (float)this.panelX + 3.0f, (float)this.panelY + 45.0f + (float)(18 * n2)));
            ++n2;
        }
        if (this.category.getSubCategories().size() == 2) {
            this.tabs.add(new SubCategoryTab(0.0f, 54.0f, (Category)this.category.getSubCategories().get(0), null, null));
            this.tabs.add(new SubCategoryTab(56.0f, 54.0f, (Category)this.category.getSubCategories().get(1), null, null));
        }
        if (this.category.getSubCategories().size() == 3) {
            this.tabs.add(new SubCategoryTab(0.0f, 35.5f, (Category)this.category.getSubCategories().get(0), null, null));
            this.tabs.add(new SubCategoryTab(37.5f, 35.5f, (Category)this.category.getSubCategories().get(1), null, null));
            this.tabs.add(new SubCategoryTab(74.5f, 35.5f, (Category)this.category.getSubCategories().get(2), null, null));
        }
        if (this.category.getSubCategories().size() == 4) {
            this.tabs.add(new SubCategoryTab(0.0f, 27.0f, (Category)this.category.getSubCategories().get(0), null, null));
            this.tabs.add(new SubCategoryTab(28.0f, 27.0f, (Category)this.category.getSubCategories().get(1), null, null));
            this.tabs.add(new SubCategoryTab(56.0f, 27.0f, (Category)this.category.getSubCategories().get(2), null, null));
            this.tabs.add(new SubCategoryTab(83.0f, 27.0f, (Category)this.category.getSubCategories().get(3), null, null));
        }
        Object e = this.tabs.get(0);
        Intrinsics.checkNotNullExpressionValue(e, "get(...)");
        this.previousTab = this.selectedTab = (SubCategoryTab)e;
    }

    @NotNull
    public final CategoryGroup getCategory() {
        return this.category;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final void setOffset(int n) {
        this.offset = n;
    }

    public final int getPanelX() {
        return this.panelX;
    }

    public final void setPanelX(int n) {
        this.panelX = n;
    }

    public final int getPanelY() {
        return this.panelY;
    }

    public final void setPanelY(int n) {
        this.panelY = n;
    }

    public final int getCurrentX() {
        return this.currentX;
    }

    public final void setCurrentX(int n) {
        this.currentX = n;
    }

    public final int getCurrentY() {
        return this.currentY;
    }

    public final void setCurrentY(int n) {
        this.currentY = n;
    }

    public final float X() {
        return this.n;
    }

    public final void M(float f) {
        this.n = f;
    }

    public final float getPanelHeight() {
        return this.panelHeight;
    }

    public final void setPanelHeight(float f) {
        this.panelHeight = f;
    }

    @NotNull
    public final SubCategoryTab getSelectedTab() {
        return this.selectedTab;
    }

    public final void setSelectedTab(@NotNull SubCategoryTab subCategoryTab) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(subCategoryTab, (String)((Object)CategoryPanel.zkmBootstrap("v", (int)8787, (long)(0xA8412DB7A89BB2AL ^ l))));
        this.selectedTab = subCategoryTab;
    }

    @NotNull
    public final ArrayList getTabs() {
        return this.tabs;
    }

    @NotNull
    public final ArrayList getModuleButtons() {
        return this.moduleButtons;
    }

    @Nullable
    public final ModuleButton getModuleButton() {
        return this.moduleButton;
    }

    public final void setModuleButton(@Nullable ModuleButton moduleButton) {
        this.moduleButton = moduleButton;
    }

    public final boolean B() {
        return this.T;
    }

    public final void n(boolean bl) {
        this.T = bl;
    }

    public final void renderHeader(@NotNull DrawContext drawContext, int n, long l, int n2) {
        Color[] colorArray;
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x10EA2D102B24L;
        long l4 = l2 ^ 0x26E2093AE749L;
        long l5 = l2 ^ 0x4D4C4E6266B9L;
        long l6 = l2 ^ 0x73106C61A283L;
        long l7 = l2 ^ 0x2C2F7BE5164BL;
        int n3 = (int)(l7 >>> 32);
        int n4 = (int)(l7 << 32 >>> 48);
        int n5 = (int)(l7 << 48 >>> 48);
        long l8 = l2 ^ 0x7A0B8C72CDB6L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Fonts.getSF_M3().setSize(50.0f);
        Fonts.getSF_M2().setSize(50.0f);
        if (this.T) {
            this.panelX = Globals.getMc().getWindow().getScaledWidth() / 2 + (this.offset - 2) * 125;
            this.panelY = 100;
            this.currentX = this.panelX;
            this.currentY = this.panelY;
            this.T = false;
        }
        this.panelX = MathKt.roundToInt(this.smooth(this.panelX, this.currentX, 13.0f));
        this.panelY = MathKt.roundToInt(this.smooth(this.panelY, this.currentY, 13.0f));
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        float f = this.panelX;
        float f2 = this.panelY;
        Color color = Theme.INSTANCE.g().brighter();
        Intrinsics.checkNotNullExpressionValue(color, "brighter(...)");
        Shapes2D.drawShape(matrix3x2fStack, f, f2, this.n, 25.0f, 7.0f, 2.0f, 0.0f, 1.0f, color, Theme.INSTANCE.getOutlineColor(), 0.0f, l5, 0.0f, 7.0f);
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Shapes2D.drawShape(matrix3x2fStack2, this.panelX, (float)this.panelY + 24.0f, this.n, this.openAnimation.getValue() - 24.0f, 0.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 7.0f, l5, 7.0f, 0.0f);
        FontRenderer fontRenderer = Fonts.getSF_SB();
        DrawContext drawContext2 = drawContext;
        Color[] colorArray2 = this.category.getId();
        if (((CharSequence)colorArray2).length() > 0) {
            char c = colorArray2.charAt(0);
            DrawContext drawContext3 = drawContext2;
            FontRenderer fontRenderer2 = fontRenderer;
            char c2 = Character.toUpperCase(c);
            fontRenderer = fontRenderer2;
            drawContext2 = drawContext3;
            c = c2;
            Color[] colorArray3 = colorArray2;
            int n6 = 1;
            String string = colorArray3.substring(n6);
            Intrinsics.checkNotNullExpressionValue(string, "substring(...)");
            colorArray3 = string;
            colorArray = c + (String)colorArray3;
        } else {
            colorArray = colorArray2;
        }
        int n7 = 10;
        float f3 = (float)this.panelY + 5.0f;
        float f4 = (float)this.panelX + this.n / 2.0f;
        Color[] colorArray4 = colorArray;
        DrawContext drawContext4 = drawContext2;
        fontRenderer.drawStringRight(l4, drawContext4, (String)colorArray4, f4, f3, n7);
        Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
        colorArray2 = new Color[]{RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getOutlineColor(), 0.3f)};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack3, this.panelX, (float)this.panelY + 41.0f, this.n, 1.0f, colorArray2, l8);
        this.settingsAnimation.animate(1.0f);
        RenderUtil.INSTANCE.beginScissor(drawContext, new Rect((float)this.panelX + 1.0f, this.panelY, (float)this.panelX + this.n, (float)this.panelY + this.openAnimation.getValue()));
        if (this.moduleButton == null) {
            this.renderSettings(n, n2, drawContext, l3, this.openAnimation.x());
        } else {
            this.renderModules(n, l6, n2, drawContext);
        }
        Fonts.getSF_M3().clearCache(n3, (char)n4, (short)n5);
        Fonts.getSF_M2().clearCache(n3, (char)n4, (short)n5);
        RenderUtil.INSTANCE.endScissor(drawContext);
    }

    private final void renderModules(int n, long l, int n2, DrawContext drawContext) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x5D7BE5AD90A6L;
        long l4 = l2 ^ 0x35D5FEE39422L;
        long l5 = l2 ^ 0x4B51F7660D62L;
        this.hoverAnimation.animate(1.0f);
        FontRenderer fontRenderer = Fonts.getSF_M3();
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        ModuleButton moduleButton = this.moduleButton;
        Intrinsics.checkNotNull(moduleButton);
        FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, moduleButton.getModule().getDisplayName(), (float)this.panelX + 8.0f + 40.0f * (1.0f - this.hoverAnimation.getValue()), (float)this.panelY + 27.0f, l4, Theme.INSTANCE.getTextColor(), false, null);
        float f = 0.0f;
        Object object = this.moduleButton;
        if (object != null && (object = ((ModuleButton)object).T()) != null) {
            Iterable iterable = (Iterable)object;
            for (Object t : iterable) {
                SettingElement settingElement = (SettingElement)t;
                if (settingElement.getSetting().getGroup() != null) {
                    SettingParent settingParent = settingElement.getSetting().getGroup();
                    Intrinsics.checkNotNull(settingParent);
                    if (!settingParent.isExtended()) continue;
                    SettingParent settingParent2 = settingElement.getSetting().getGroup();
                    Intrinsics.checkNotNull(settingParent2);
                    if (settingParent2.getType() == AuraTargetsMode.BOOLS) continue;
                }
                settingElement.setX((float)this.panelX + 5.0f);
                settingElement.setY((float)this.panelY + 47.0f + f);
                settingElement.render(drawContext, n, n2, l3, this.settingsAnimation.getValue());
                f += settingElement.getHeight() + 2.0f;
            }
        }
        RenderTarget renderTarget = ClickGui.INSTANCE.r();
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        float f2 = this.hoverAnimation.getValue();
        RenderTarget.drawRoundedText$default(renderTarget, (Matrix3x2f)matrix3x2fStack2, (double)((float)this.panelX + this.n) - 15.0, (double)this.panelY + 26.0, 0.0f, 0.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f2), null, 0.0f, 216, l5, null);
        this.openAnimation.animate(49.0f + f);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private final void renderSettings(int var1_1, int var2_2, DrawContext var3_3, long var4_4, boolean var6_5) {
        block19: {
            block18: {
                v0 = var4_4 = CategoryPanel.a ^ var4_4;
                v1 = v0 ^ 48840505026002L;
                var9_6 = (int)(v1 >>> 48);
                var10_7 = (int)(v1 << 16 >>> 48);
                var11_8 = (int)(v1 << 32 >>> 32);
                var16_9 = v0 ^ 91547901855143L;
                v2 = v0 ^ 15507033940297L;
                var18_10 = (int)(v2 >>> 48);
                var19_11 = (int)(v2 << 16 >>> 48);
                var20_12 = (int)(v2 << 32 >>> 32);
                v3 = SettingElement.e();
                this.hoverAnimation.animate(0.0f);
                var22_13 = this.tabs;
                var21_15 = v3;
                for (Object var25_18 : var22_13) {
                    var26_19 = (SubCategoryTab)var25_18;
                    if (var21_15) break;
                    var26_19.getAnimation().animate(Intrinsics.areEqual(var26_19, this.selectedTab) != false ? 1.0f : 0.0f);
                    if ((double)var26_19.getAnimation().getValue() > 0.001) {
                        v4 = var3_3.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(v4, "pose(...)");
                        Shapes2D.drawShape$default(v4, (char)var9_6, (float)this.panelX + var26_19.getX() + 5.0f, (float)this.panelY + 27.0f, var26_19.getW(), 11.5f, 2.0f, 2.0f * var26_19.getAnimation().getValue(), 0.05f * var26_19.getAnimation().getValue(), 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var10_7, 0.0f, var11_8, 0.0f, 14336, null);
                    }
                    var28_20 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveTextColor(), Theme.INSTANCE.getTextColor(), var26_19.getAnimation().getValue());
                    v5 = Fonts.getSF_M3();
                    v6 /* !! */  = var3_3;
                    var29_21 = var26_19.getSubCategory().getId();
                    v7 = ((CharSequence)var29_21).length();
                    if (!var21_15) {
                        v7 = v7 > 0 ? 1 : 0;
                    }
                    if (v7 != 0) {
                        var30_22 = var29_21.charAt(0);
                        var31_24 /* !! */  = v6 /* !! */ ;
                        var32_26 = v5;
                        var34_28 = Character.toUpperCase(var30_22);
                        v5 = var32_26;
                        v6 /* !! */  = var31_24 /* !! */ ;
                        var30_22 = var34_28;
                        var33_27 = var29_21;
                        var35_29 = 1;
                        v8 = var33_27.substring(var35_29);
                        Intrinsics.checkNotNullExpressionValue(v8, "substring(...)");
                        var33_27 = v8;
                        v9 = var30_22 + (String)var33_27;
                    } else {
                        v9 = var29_21;
                    }
                    v5.drawStringCentered(v6 /* !! */ , (String)v9, (float)this.panelX + var26_19.getX() + 5.0f + (float)MathKt.roundToInt(var26_19.getW() / 0.2f) / 10.0f, (float)this.panelY + 31.5f, (Color)var28_20, var16_9);
                    if (!var21_15) continue;
                }
                var22_14 = 0.0f;
                var23_30 = 0.0f;
                var24_17 = 0.0f;
                var27_31 = var25_18 = (Iterable)this.moduleButtons;
                var28_20 = new ArrayList<E>();
                var30_23 = var27_31.iterator();
                while (var30_23.hasNext()) {
                    block20: {
                        block21: {
                            var31_24 /* !! */  = var30_23.next();
                            var32_26 = (ModuleButton)var31_24 /* !! */ ;
                            v10 = Intrinsics.areEqual(var32_26.getModule().getCategory(), this.selectedTab.getSubCategory());
                            if (var21_15) break block18;
                            if (var21_15) break block20;
                            if (v10) ** GOTO lbl-1000
                            v11 = Intrinsics.areEqual(var32_26.getModule().getCategory(), this.previousTab.getSubCategory());
                            if (var21_15) break block21;
                            if (v11 == 0) ** GOTO lbl-1000
                            cfr_temp_0 = this.settingsAnimation.getValue() - 1.0f;
                            v11 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                        }
                        if (!var21_15) {
                            ** if (v11 >= 0) goto lbl-1000
                        }
                        break block20;
lbl-1000:
                        // 2 sources

                        {
                            v11 = 1;
                            ** GOTO lbl76
                        }
lbl-1000:
                        // 2 sources

                        {
                            v11 = 0;
                        }
                    }
                    if (v11 == 0) continue;
                    var28_20.add(var31_24 /* !! */ );
                    if (!var21_15) continue;
                }
                var25_18 = (List)var28_20;
                v10 = false;
            }
            var27_31 = var25_18.iterator();
            while (var27_31.hasNext()) {
                block24: {
                    block22: {
                        block23: {
                            var28_20 = var27_31.next();
                            var29_21 = (ModuleButton)var28_20;
                            if (var21_15) break block19;
                            v12 = Intrinsics.areEqual(var29_21.getModule().getCategory(), this.selectedTab.getSubCategory());
                            if (var21_15) ** GOTO lbl95
                            if (v12 != 0) {
                                v13 = (float)this.panelX - (float)this.t * (this.n * (1.0f - this.settingsAnimation.getValue()));
                            } else {
                                v12 = this.panelX;
lbl95:
                                // 2 sources

                                v13 = (float)v12 + (float)this.t * (this.n * this.settingsAnimation.getValue());
                            }
                            var31_25 = v13;
                            var29_21.setX(var31_25 + 3.0f);
                            v14 = var29_21;
                            if (var21_15) break block22;
                            if (!Intrinsics.areEqual(v14.getModule().getCategory(), this.selectedTab.getSubCategory())) break block23;
                            var29_21.setY((float)this.panelY + 44.0f + var22_14);
                            v15 = var22_14;
                            v16 = var29_21.V() + 2.0f;
                            if (!var21_15) {
                                var22_14 = v15 + v16;
                                v15 = var24_17;
                                v16 = var29_21.z() != false ? 31.0f : 17.0f;
                            }
                            var24_17 = v15 + (v16 + 2.0f);
                            if (!var21_15) break block24;
                        }
                        v14 = var29_21;
                    }
                    v14.setY((float)this.panelY + 44.0f + var23_30);
                    var23_30 += var29_21.V() + 2.0f;
                }
                var29_21.E(var3_3, var1_1, var2_2, (char)var18_10, (short)var19_11, var20_12, this.settingsAnimation.getValue(), var6_5);
                if (!var21_15) continue;
            }
            this.openAnimation.animate(45.0f + var24_17);
        }
    }

    public final void switchTab(@NotNull SubCategoryTab subCategoryTab) {
        block6: {
            block5: {
                boolean bl;
                block4: {
                    boolean bl2 = SettingElement.e();
                    Intrinsics.checkNotNullParameter(subCategoryTab, "selector");
                    this.previousTab = this.selectedTab;
                    bl = bl2;
                    this.selectedTab = subCategoryTab;
                    float f = this.previousTab.getX() - this.selectedTab.getX();
                    float f2 = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                    if (!bl) {
                        f2 = f2 > 0 ? 1.0f : (float)-1;
                    }
                    this.t = (int)f2;
                    if (bl) break block4;
                    if (this.t != 1) break block5;
                    Sounds.INSTANCE.getSwitchRight().play();
                }
                if (!bl) break block6;
            }
            Sounds.INSTANCE.getSwitchLeft().play();
        }
        this.settingsAnimation.setValue(0.0f);
        this.settingsAnimation.animateTo(this.settingsAnimation.getValue());
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void mouseClicked(double var1_1, double var3_2, long var5_3, int var7_4) {
        block37: {
            block29: {
                block46: {
                    block45: {
                        block41: {
                            block44: {
                                block43: {
                                    block42: {
                                        block40: {
                                            block39: {
                                                block38: {
                                                    block30: {
                                                        block31: {
                                                            block28: {
                                                                block27: {
                                                                    v0 = var5_3 = CategoryPanel.a ^ var5_3;
                                                                    var10_5 = v0 ^ 48397816265147L;
                                                                    v1 = v0 ^ 73459045840531L;
                                                                    var12_6 = (int)(v1 >>> 48);
                                                                    var13_7 = (int)(v1 << 16 >>> 32);
                                                                    var14_8 = (int)(v1 << 48 >>> 48);
                                                                    var19_9 = SettingElement.e();
                                                                    v2 = this;
                                                                    if (var19_9) break block30;
                                                                    if (v2.moduleButton != null) break block31;
                                                                    var20_10 = this.tabs;
                                                                    for (Object var23_15 : var20_10) {
                                                                        block32: {
                                                                            block36: {
                                                                                block35: {
                                                                                    block34: {
                                                                                        block33: {
                                                                                            var24_16 = (SubCategoryTab)var23_15;
                                                                                            var26_17 = var24_16.getX() + (float)this.panelX;
                                                                                            cfr_temp_0 = var1_1 - (double)(var24_16.getX() + var24_16.getW() + (float)this.panelX);
                                                                                            v3 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 < 0.0 ? -1 : 1);
                                                                                            if (var19_9) break block27;
                                                                                            if (var19_9) ** GOTO lbl24
                                                                                            if (v3 <= 0) {
                                                                                                cfr_temp_1 = (double)var26_17 - var1_1;
                                                                                                v4 = cfr_temp_1 == 0.0 ? 0 : (cfr_temp_1 < 0.0 ? -1 : 1);
lbl24:
                                                                                                // 2 sources

                                                                                                if (!var19_9) {
                                                                                                    v4 = v4 <= 0 ? 1 : 0;
                                                                                                }
                                                                                            } else {
                                                                                                v4 = 0;
                                                                                            }
                                                                                            if (v4 == 0) break block32;
                                                                                            var26_18 = this.panelY + 27;
                                                                                            var27_21 = this.panelY + 38;
                                                                                            var28_24 = (int)var3_2;
                                                                                            v5 = var26_18;
                                                                                            v6 = var28_24;
                                                                                            if (var19_9) break block33;
                                                                                            if (v5 > v6) break block34;
                                                                                            v5 = var28_24;
                                                                                            if (var19_9) break block35;
                                                                                            v6 = var27_21;
                                                                                        }
                                                                                        v5 = v5 <= v6 ? 1 : 0;
                                                                                        break block35;
                                                                                    }
                                                                                    v5 = 0;
                                                                                }
                                                                                if (v5 == 0) break block32;
                                                                                v7 = this;
                                                                                if (var19_9) break block36;
                                                                                if (Intrinsics.areEqual(v7.selectedTab, var24_16)) break block32;
                                                                                v7 = this;
                                                                            }
                                                                            v7.switchTab((SubCategoryTab)var24_16);
                                                                        }
                                                                        if (!var19_9) continue;
                                                                    }
                                                                    var20_10 = this.moduleButtons;
                                                                    v3 = 0;
                                                                }
                                                                var22_13 = var20_10;
                                                                var23_15 = new ArrayList<E>();
                                                                var25_26 = var22_13.iterator();
                                                                while (var25_26.hasNext()) {
                                                                    var26_19 = var25_26.next();
                                                                    var27_22 = (ModuleButton)var26_19;
                                                                    v8 = Intrinsics.areEqual(var27_22.getModule().getCategory(), this.selectedTab.getSubCategory());
                                                                    if (!var19_9) {
                                                                        if (!var19_9) {
                                                                            if (!v8) continue;
                                                                            var23_15.add(var26_19);
                                                                        }
                                                                        if (!var19_9) continue;
                                                                    }
                                                                    break block28;
                                                                }
                                                                var20_10 = (List)var23_15;
                                                                v8 = false;
                                                            }
                                                            for (Object var23_15 : var20_10) {
                                                                var24_16 = (ModuleButton)var23_15;
                                                                var24_16.D((short)var12_6, var13_7, var1_1, var14_8, var3_2, var7_4);
                                                                if (!var19_9 && !var19_9) continue;
                                                            }
                                                            if (!var19_9) break block37;
                                                        }
                                                        v2 = this;
                                                    }
                                                    var20_11 = v2.panelX + 3;
                                                    var21_27 = this.panelX + (int)this.n - 3;
                                                    var22_14 = (int)var1_1;
                                                    v9 = var20_11;
                                                    v10 = var22_14;
                                                    if (var19_9) break block38;
                                                    if (v9 > v10) break block39;
                                                    v9 = var22_14;
                                                    if (var19_9) break block40;
                                                    v10 = var21_27;
                                                }
                                                v9 = v9 <= v10 ? 1 : 0;
                                                break block40;
                                            }
                                            v9 = 0;
                                        }
                                        if (v9 == 0) break block41;
                                        var20_11 = this.panelY + 25;
                                        var21_27 = this.panelY + 40;
                                        var22_14 = (int)var3_2;
                                        v11 = var20_11;
                                        v12 = var22_14;
                                        if (var19_9) break block42;
                                        if (v11 > v12) break block43;
                                        v11 = var22_14;
                                        if (var19_9) break block44;
                                        v12 = var21_27;
                                    }
                                    v11 = v11 <= v12 ? 1 : 0;
                                    break block44;
                                }
                                v11 = 0;
                            }
                            if (v11 == 0) break block41;
                            this.moduleButton = null;
                            Sounds.INSTANCE.getSettingsClose().play();
                            if (!var19_9) break block37;
                        }
                        v13 = var20_12 = this.moduleButton;
                        if (var19_9) break block45;
                        if (v13 == null) break block37;
                        v13 = var20_12;
                    }
                    v14 /* !! */  = var21_28 = v13.T();
                    if (var19_9) break block46;
                    if (v14 /* !! */  == null) break block37;
                    v14 /* !! */  = var21_28;
                }
                var23_15 = v14 /* !! */ ;
                var25_26 = var23_15;
                var26_20 = new ArrayList<E>();
                var28_25 = var25_26.iterator();
                while (var28_25.hasNext()) {
                    block50: {
                        block47: {
                            block49: {
                                block48: {
                                    var29_29 = var28_25.next();
                                    var30_30 = (SettingElement)var29_29;
                                    v15 = (Boolean)var30_30.getSetting().getVisible().invoke();
                                    if (var19_9) break block29;
                                    if (var19_9) break block47;
                                    if (!v15) break block48;
                                    v16 = var30_30.getSetting().getGroup();
                                    if (var19_9) break block49;
                                    if (v16 == null) ** GOTO lbl-1000
                                }
                                v17 = var30_30.getSetting().getGroup();
                                v16 = v17;
                                Intrinsics.checkNotNull(v17);
                            }
                            if (var19_9) break block50;
                            v18 = v16.isExtended();
                        }
                        if (!v18) ** GOTO lbl-1000
                        v19 = var30_30.getSetting().getGroup();
                        v16 = v19;
                        Intrinsics.checkNotNull(v19);
                    }
                    ** if (v16.getType() == AuraTargetsMode.BOOLS) goto lbl-1000
lbl-1000:
                    // 2 sources

                    {
                        v20 = true;
                        ** GOTO lbl165
                    }
lbl-1000:
                    // 2 sources

                    {
                        v20 = false;
                    }
lbl165:
                    // 2 sources

                    if (!v20) continue;
                    var26_20.add(var29_29);
                    if (!var19_9) continue;
                }
                var23_15 = (List)var26_20;
                v15 = false;
            }
            var25_26 = var23_15.iterator();
            while (var25_26.hasNext()) {
                var26_20 = var25_26.next();
                var27_23 = (SettingElement)var26_20;
                var27_23.mouseClicked(var10_5, var1_1, var3_2, var7_4);
                if (!var19_9 && !var19_9) continue;
            }
            if (!var19_9) {
                // empty if block
            }
        }
    }

    public final void mouseReleased(double d, double d2) {
        block6: {
            Iterable iterable;
            boolean bl;
            block7: {
                Object object;
                Object object2;
                block5: {
                    bl = SettingElement.e();
                    object2 = this.moduleButton;
                    if (bl) break block5;
                    if (object2 == null) break block6;
                    object2 = object = this.moduleButton;
                }
                if (object2 == null) break block6;
                iterable = ((ModuleButton)object).T();
                if (bl) break block7;
                object = iterable;
                if (iterable == null) break block6;
                iterable = (Iterable)object;
            }
            List list = iterable;
            for (Object t : list) {
                SettingElement settingElement = (SettingElement)t;
                settingElement.mouseReleased(d, d2);
                if (!bl && !bl) continue;
            }
            {
                break;
            }
        }
    }

    public final void keyPressed(int n, long l) {
        long l2 = (l = a ^ l) ^ 0x4BAA3A6FDFCAL;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 16 >>> 48);
        int n4 = (int)(l2 << 32 >>> 32);
        Iterable iterable = this.moduleButtons;
        boolean bl = SettingElement.e();
        for (Object t : iterable) {
            ModuleButton moduleButton = (ModuleButton)t;
            moduleButton.R((short)n2, (short)n3, n, n4);
            if (!bl && !bl) continue;
            break;
        }
    }

    public final void charTyped(char c) {
        Iterable iterable = this.moduleButtons;
        Iterator iterator2 = iterable.iterator();
        boolean bl = SettingElement.e();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            ModuleButton moduleButton = (ModuleButton)t;
            moduleButton.l(c);
            if (!bl && !bl) continue;
            break;
        }
    }

    public final boolean mouseScrolled(double d, double d2, double d3, double d4) {
        boolean bl;
        block4: {
            boolean bl2 = false;
            boolean bl3 = SettingElement.e();
            Iterable iterable = this.moduleButtons;
            for (Object t : iterable) {
                ModuleButton moduleButton = (ModuleButton)t;
                if (!bl3) {
                    bl = moduleButton.v(d, d2, d3);
                    if (bl3) break block4;
                    if (bl) {
                        bl2 = true;
                    }
                }
                if (!bl3) continue;
            }
            bl = bl2;
        }
        return bl;
    }

    public final void scroll(double d, double d2) {
        this.currentY += (int)(d * 10.0);
        this.currentX += (int)(d2 * 10.0);
    }

    private final float smooth(float f, float f2, float f3) {
        float f4 = Math.clamp((FpsCounter.INSTANCE.D() > 5 ? 1.0f / (float)FpsCounter.INSTANCE.D() : 0.016f) * f3, 0.0f, 1.0f);
        return (1.0f - f4) * f + f4 * f2;
    }

    public final void closeSettings() {
        block3: {
            Iterable iterable;
            boolean bl;
            block4: {
                Object object;
                bl = SettingElement.e();
                if (bl || (object = this.moduleButton) == null) break block3;
                iterable = ((ModuleButton)object).T();
                if (bl) break block4;
                object = iterable;
                if (iterable == null) break block3;
                iterable = (Iterable)object;
            }
            List list = iterable;
            for (Object t : list) {
                SettingElement settingElement = (SettingElement)t;
                settingElement.close();
                if (!bl && !bl) continue;
                break;
            }
        }
        this.T = true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final float setAlpha(float f) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8775809826433742285L, (long)l);
        int n = Float.isNaN(f);
        if (callSite == false) {
            if (n != 0) {
                return 1.0f;
            }
            n = MathKt.roundToInt((float)MathKt.roundToInt(f * 100.0f) / 100.0f * 10.0f);
        }
        float f2 = (float)n / 10.0f;
        return f2;
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
                                CategoryPanel.a = ZkmKeys.a(-2612193076119182194L, 1906156425104328316L, MethodHandles.lookup().lookupClass()).a(170863618330489L);
                                CategoryPanel.d = new HashMap<K, V>(13);
                                var22 = CategoryPanel.a ^ 26139283733853L;
                                var24_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var22 >>> 56);
                                for (var25_2 = 1; var25_2 < 8; ++var25_2) {
                                    v2 = v2;
                                    v2[var25_2] = (byte)(var22 << var25_2 * 8 >>> 56);
                                }
                                var24_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var31_3 = new String[10];
                                var29_4 = 0;
                                var28_5 = "k\u00ec|\u0087\u00d4\u00a2\u0011\u00cdz\u0004\u00ebX\u00df\u00c3sQ7}\u0092E\u0019+0\u00ab\u0018\u0017\u000e\u00a4\u00d3\u0018\u009dN\u00b5\u000f\u00f1\u00cb\u00f3vHE\u00d6\u00b3\u0006\u00a3K\u00f4t\u00ef\u009b\u0018\u008b\u0002\u00fa>z\u00e2\u0014V\u00ee\u00f5\u00d5\u00dcF_G\u0091\u001a*v\u00a4?p\u00a1M \u0080\u00ae_6\u00c3\u0093\u00cf\u0080\u0000KyE\b\u0005\u00e2\r\u0004E\u0086\u00e3V\u0007g]\u00e7\u00eb\u0082\u0087k\u00d4\u00db6\u0018l\u00807?_\u00bb\u00bf\\\u0010\u00ad\u00e7l\u00b7\u00b1\u00f0\u00c5\u0092&\u0019\u00e7\u00d0G\u0013t J\u001f\u00d0X<\u001b\u009c\u0005\u00b0\u00f5\u00bf\u00f1\u00f6F\u009bJ\u00e9\u00cf%/\u00fdYL\u00a8\u00ab\u0015\u0091h\u00d31\u00f5\u0091\u0010K#\u00ecSP\u00b3\u007fE\u00a3\u0093\u0091\u00e8;\u00b0\u009b\u00b9 i\u009b\u00c7\u00de\u0013\u001a^\u008c!]\u000f\u0001\u00bd\"\u008as\"t!-\u00e9|\u00f9\u0093G\u00b6h\u00b9*\u00ba3\u00ac";
                                var30_6 = 215;
                                var27_7 = 24;
                                var26_8 = -1;
lbl20:
                                // 2 sources

                                while (true) {
                                    v3 = ++var26_8;
                                    v4 = var28_5.substring(v3, v3 + var27_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl25:
                                // 1 sources

                                while (true) {
                                    var31_3[var29_4++] = CategoryPanel.zkmDecodeUtf8(var32_9).intern();
                                    if ((var26_8 += var27_7) < var30_6) {
                                        var27_7 = var28_5.charAt(var26_8);
                                        ** continue;
                                    }
                                    var28_5 = "\u00ee>|\u001d\u00ef\u00fe\u0097\u00ca(@\u0084\u00e3\u008cs~\u0016\u008f\u00bc{\u00a1\u00e0\u0080Ha\u00da\u0012\u0098\u00a8\u0019\u00edg\u000e\u0010\u0080\u009cn,\u00f2\u00b1\u0084\u0016v\u00b3\u00c3\u00fe\u000e\u00a3GN";
                                    var30_6 = 49;
                                    var27_7 = 32;
                                    var26_8 = -1;
lbl34:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var26_8;
                                        v4 = var28_5.substring(v6, v6 + var27_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl39:
                                // 1 sources

                                while (true) {
                                    var31_3[var29_4++] = CategoryPanel.zkmDecodeUtf8(var32_9).intern();
                                    if ((var26_8 += var27_7) < var30_6) {
                                        var27_7 = var28_5.charAt(var26_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var32_9 = var24_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        CategoryPanel.b = var31_3;
                        CategoryPanel.c = new String[10];
                        CategoryPanel.i = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var22 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var22 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[15];
                        var14_13 = 0;
                        var15_14 = "\u00c6/D\u0081m\u0003\u00c9\u0081\u0098\u0080\u0095\tu\b=\u00f0x\u00d3\u0002\u000fD\u00b16\u0019\u00ecn-\u0018*\u00b2r\u008f\u0001j^\u00cbm\u00ff\u009e\u00d8\u0015e\u00fd\u00d3\u00c7\u00cf0\u00a1\u00c9\u009e\u0001\u00e7\u00c4\u0090Y1\u00aa\u00ebB^]\u00fc\"5M\u00c2\u00d5\u0087\u00e8XxV\u000e\u008d\u0001\u00d7\u0017\u00b5i\u008d\u0015\u00c0um\u00c1\u00d5D'\u00ab\u00a9\u009c\u0080p\u0087\u008e\u00c4\u00a8\u0003\u0000\u00fb\u00d8|\u00ca\u00b3";
                        var16_15 = 104;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl78:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "\u0012Q\u00f8\t^wu\u00e6\u0081<\u001f\u00e2\u00a8q\u0097\u00c0";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl91:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block24;
                            break;
                        }
                    }
                    var19_18 = v12;
                    var21_19 = var11_10.doFinal(new byte[]{(byte)(var19_18 >>> 56), (byte)(var19_18 >>> 48), (byte)(var19_18 >>> 40), (byte)(var19_18 >>> 32), (byte)(var19_18 >>> 24), (byte)(var19_18 >>> 16), (byte)(var19_18 >>> 8), (byte)var19_18});
                    v14 = ((long)var21_19[0] & 255L) << 56 | ((long)var21_19[1] & 255L) << 48 | ((long)var21_19[2] & 255L) << 40 | ((long)var21_19[3] & 255L) << 32 | ((long)var21_19[4] & 255L) << 24 | ((long)var21_19[5] & 255L) << 16 | ((long)var21_19[6] & 255L) << 8 | (long)var21_19[7] & 255L;
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
                CategoryPanel.f = var17_12;
                CategoryPanel.h = new Integer[15];
                CategoryPanel.m = new HashMap<K, V>(13);
                var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var22 >>> 56);
                for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                    v17 = v17;
                    v17[var1_21] = (byte)(var22 << var1_21 * 8 >>> 56);
                }
                var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var6_22 = new long[3];
                var3_23 = 0;
                var4_24 = "\u00bf\u00c3\u009d\u00cd\u00ad@h;\u0092\u0019(\u00c5z\u00cc\u00ces\u00d1;2\u0080\u009b\u0092\u0011\u008f";
                var5_25 = 24;
                var2_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl126:
                // 1 sources

                while (true) {
                    var6_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var2_26 < var5_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
            v18 = var3_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var0_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        CategoryPanel.k = var6_22;
        CategoryPanel.l = new Long[3];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4E44;
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
                throw new RuntimeException("su/catlean/ry", exception);
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
            CategoryPanel.c[n2] = CategoryPanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CategoryPanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ry" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x58F1;
        if (h[n2] == null) {
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
                throw new RuntimeException("su/catlean/ry", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CategoryPanel.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CategoryPanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ry" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7218;
        if (CategoryPanel.l[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = k[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])m.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    m.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ry", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            CategoryPanel.l[n2] = l4;
        }
        return CategoryPanel.l[n2];
    }

    private static long c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = CategoryPanel.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap3(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_2().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/ry" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CategoryPanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CategoryPanel.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_2() {
        try {
            return MethodHandles.lookup().findStatic(CategoryPanel.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

