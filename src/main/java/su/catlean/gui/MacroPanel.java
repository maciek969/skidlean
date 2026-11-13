/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

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
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import net.minecraft.block.Block;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.command.Suggestions;
import su.catlean.config.ModuleConfig;
import su.catlean.config.ModuleConfig$legit$1;
import su.catlean.gui.Button;
import su.catlean.gui.Element;
import su.catlean.gui.GuiElement;
import su.catlean.gui.MacroElement;
import su.catlean.gui.MacroPanel$hoveringComponent$1;
import su.catlean.gui.MacroPanel$hoveringComponent$2;
import su.catlean.gui.MacroPanel$hoveringComponent$3;
import su.catlean.gui.MacroPanel$hoveringComponent$4;
import su.catlean.gui.MacroPanel$hoveringComponent$5;
import su.catlean.gui.SidePanel;
import su.catlean.gui.SwitchElement;
import su.catlean.gui.TextBox;
import su.catlean.gui.TextField;
import su.catlean.macro.ChatMacro;
import su.catlean.macro.Macro;
import su.catlean.macro.MacroManager;
import su.catlean.macro.SettingMacro;
import su.catlean.macro.SettingMacro$hold$1;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.render.Gradient2D;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Bind;
import su.catlean.setting.Setting;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;

public final class MacroPanel
extends SidePanel {
    @NotNull
    public static final MacroPanel INSTANCE;
    @NotNull
    private static final List macroElements;
    @Nullable
    private static Element hoveredElement;
    @Nullable
    private static Element F;
    @Nullable
    private static Element focusedElement;
    private static boolean B;
    @Nullable
    private static MacroElement selectedMacro;
    @Nullable
    private static MacroElement editingMacro;
    @NotNull
    private static final Animation y;
    @NotNull
    private static final Animation e;
    @NotNull
    private static final Animation J;
    private static boolean H;
    private static float scroll;
    @NotNull
    private static final TextField idField;
    @NotNull
    private static final TextField messageField;
    @NotNull
    private static final TextField itemField;
    @NotNull
    private static final TextField b;
    @NotNull
    private static final TextField settingField;
    @NotNull
    private static final TextField pressValueField;
    @NotNull
    private static final TextField releaseValueField;
    private static String[] Q;
    private static final long c;
    private static final String[] i;
    private static final String[] m;
    private static final Map n;
    private static final long[] s;
    private static final Integer[] t;
    private static final Map w;

    private MacroPanel() {
        super("macro-panel", 0.0f, 0.0f, 0.0f, 0.0f, null);
    }

    @NotNull
    public final List getMacroElements() {
        return macroElements;
    }

    @Nullable
    public final Element getHoveredElement() {
        return hoveredElement;
    }

    public final void setHoveredElement(@Nullable Element element) {
        hoveredElement = element;
    }

    @Nullable
    public final Element getFocusedElement() {
        return focusedElement;
    }

    public final void setFocusedElement(@Nullable Element element) {
        focusedElement = element;
    }

    public final boolean L() {
        return B;
    }

    public final void t(boolean bl) {
        B = bl;
    }

    @Nullable
    public final MacroElement getSelectedMacro() {
        return selectedMacro;
    }

    public final void setSelectedMacro(@Nullable MacroElement macroElement) {
        selectedMacro = macroElement;
    }

    @Nullable
    public final MacroElement getEditingMacro() {
        return editingMacro;
    }

    public final void setEditingMacro(@Nullable MacroElement macroElement) {
        editingMacro = macroElement;
    }

    @NotNull
    public final Animation g() {
        return y;
    }

    @Override
    public void refresh() {
        macroElements.clear();
        Iterable iterable = MacroManager.INSTANCE.getMacros();
        for (Object t : iterable) {
            Macro macro = (Macro)t;
            macroElements.add(new MacroElement(macro));
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4) {
        block24: {
            block17: {
                block18: {
                    block23: {
                        block21: {
                            block22: {
                                block19: {
                                    block20: {
                                        block16: {
                                            v0 = var4_4;
                                            var9_5 = v0 ^ 136208856317167L;
                                            var13_6 = v0 ^ 136208856317167L;
                                            var17_7 = v0 ^ 129382754651428L;
                                            v1 = v0 ^ 22135879901675L;
                                            var28_8 = (int)(v1 >>> 48);
                                            var29_9 = (int)(v1 << 16 >>> 48);
                                            var30_10 = (int)(v1 << 32 >>> 32);
                                            var31_11 = v0 ^ 27182768661316L;
                                            var33_12 = v0 ^ 0L;
                                            var37_13 = v0 ^ 136208856317167L;
                                            var39_14 = v0 ^ 97910750751556L;
                                            var51_15 = v0 ^ 136208856317167L;
                                            v2 = MacroElement.B();
                                            Intrinsics.checkNotNullParameter(var1_1, "context");
                                            var71_16 = v2;
                                            super.render(var1_1, var2_2, var3_3, var33_12);
                                            v3 = MacroPanel.y;
                                            v4 = 1.0f;
                                            if (var71_16) {
                                                v3.animate(v4);
                                                v3 = MacroPanel.e;
                                                v4 = this.getGuiElement() != null ? 0.5f : 0.0f;
                                            }
                                            v3.animate(v4);
                                            MacroPanel.J.animate(MacroPanel.scroll);
                                            var72_17 = this.getX() + 3.0f;
                                            var73_18 = this.getX() + 72.0f;
                                            var74_21 = var2_2;
                                            v5 = var72_17 == var74_21 ? 0 : (var72_17 < var74_21 ? -1 : 1);
                                            if (!var71_16) ** GOTO lbl35
                                            if (v5 <= 0) {
                                                v5 = var74_21 == var73_18 ? 0 : (var74_21 < var73_18 ? -1 : 1);
lbl35:
                                                // 2 sources

                                                if (var71_16) {
                                                    v5 = v5 <= 0 ? (float)true : (float)false;
                                                }
                                            } else {
                                                v5 = (float)false;
                                            }
                                            if (v5 == false) ** GOTO lbl-1000
                                            var72_17 = this.getY() + 5.0f;
                                            var73_18 = this.getY() + 13.0f;
                                            var74_21 = var3_3;
                                            v6 = var72_17 == var74_21 ? 0 : (var72_17 < var74_21 ? -1 : 1);
                                            if (!var71_16) ** GOTO lbl47
                                            if (v6 <= 0) {
                                                v6 = var74_21 == var73_18 ? 0 : (var74_21 < var73_18 ? -1 : 1);
lbl47:
                                                // 2 sources

                                                if (var71_16) {
                                                    v6 = v6 <= 0 ? (float)true : (float)false;
                                                }
                                            } else {
                                                v6 = (float)false;
                                            }
                                            if (v6 != false) {
                                                v7 = true;
                                            } else lbl-1000:
                                            // 2 sources

                                            {
                                                v7 = false;
                                            }
                                            MacroPanel.H = v7;
                                            v8 = var1_1.getMatrices();
                                            Intrinsics.checkNotNullExpressionValue(v8, "pose(...)");
                                            var56_23 = null;
                                            var57_24 = 14336;
                                            var58_25 = 0.0f;
                                            var59_26 = 0.0f;
                                            var60_27 = 0.0f;
                                            var61_28 = Theme.INSTANCE.getOutlineColor();
                                            var62_29 = Theme.INSTANCE.g();
                                            var63_30 = 1.0f;
                                            var64_31 = MacroPanel.H != false ? 0.1f : 0.0f;
                                            var65_32 = MacroPanel.H != false ? 2.5f : 2.0f;
                                            var66_33 = 2.0f;
                                            var67_34 = 8.0f;
                                            var68_35 = 69.0f;
                                            var69_36 = this.getY() + 3.0f;
                                            var70_37 = this.getX() + 5.0f;
                                            Shapes2D.drawShape$default(v8, (char)var28_8, var70_37, var69_36, var68_35, var67_34, var66_33, var65_32, var64_31, var63_30, var62_29, var61_28, var60_27, (short)var29_9, var59_26, var30_10, var58_25, var57_24, var56_23);
                                            v9 = Fonts.getSF_M();
                                            v10 = this.getY() + 1.0f;
                                            v11 = Color.WHITE;
                                            Intrinsics.checkNotNullExpressionValue(v11, "WHITE");
                                            v9.drawStringWithShadow(var1_1, "+", var17_7, this.getX() + 36.0f, v10, v11);
                                            v12 = var1_1.getMatrices();
                                            Intrinsics.checkNotNullExpressionValue(v12, "pose(...)");
                                            Shapes2D.drawShape$default(v12, (char)var28_8, this.getX() + 76.0f, this.getY(), 6.0f, this.getHeight(), 1.0f, 0.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var29_9, 0.0f, var30_10, 0.0f, 14336, null);
                                            MacroPanel.focusedElement = null;
                                            MacroPanel.hoveredElement = null;
                                            MacroPanel.B = false;
                                            var72_17 = 17.0f + MacroPanel.J.getValue();
                                            RenderUtil.INSTANCE.beginScissor(var1_1, new Rect(this.getX(), this.getY() + 14.0f, this.getX() + 76.0f, this.getY() + this.getHeight() - 2.0f));
                                            var73_19 = MacroPanel.macroElements;
                                            for (T var76_39 : var73_19) {
                                                var77_40 = (MacroElement)var76_39;
                                                var77_40.render(var37_13, var1_1, MacroPanel.INSTANCE.getX(), MacroPanel.INSTANCE.getY() + var72_17, var2_2, var3_3);
                                                var72_17 += 29.0f;
                                                if (var71_16) {
                                                    if (var71_16) continue;
                                                }
                                                break block16;
                                            }
                                            RenderUtil.INSTANCE.endScissor(var1_1);
                                        }
                                        if (!var71_16) break block17;
                                        if (MacroPanel.selectedMacro == null) break block18;
                                        var73_20 = (1.0f - MacroPanel.y.getValue()) * 10.0f;
                                        var74_22 = (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, removeSelected(), ()Lkotlin/Unit;)();
                                        MacroPanel.idField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 17.0f + var73_20, var2_2, var3_3);
                                        v13 = MacroPanel.selectedMacro;
                                        Intrinsics.checkNotNull(v13);
                                        var75_38 = v13.getMacros();
                                        v14 = var75_38 instanceof ChatMacro;
                                        if (!var71_16) break block19;
                                        if (!v14) break block20;
                                        MacroPanel.messageField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 32.0f + var73_20, var2_2, var3_3);
                                        var79_41 = new MacroPanel$hoveringComponent$1(this);
                                        var80_42 = this.getWidth();
                                        new Button(var74_22, var80_42, var79_41).render(var13_6, var1_1, this.getX() + 84.0f, this.getY() + 47.0f + var73_20, var2_2, var3_3);
                                        if (var71_16) break block18;
                                    }
                                    v14 = var75_38 instanceof ModuleConfig;
                                }
                                if (!var71_16) break block21;
                                if (!v14) break block22;
                                MacroPanel.itemField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 32.0f + var73_20, var2_2, var3_3);
                                v15 = this.getWidth();
                                v16 = MacroPanel.selectedMacro;
                                Intrinsics.checkNotNull(v16);
                                v17 = v16.getMacros();
                                Intrinsics.checkNotNull(v17, "null cannot be cast to non-null type su.catlean.client.services.client.macro.ItemMacro");
                                var83_43 = new MacroPanel$hoveringComponent$4(this);
                                var84_44 = new ModuleConfig$legit$1((ModuleConfig)v17);
                                var85_45 = "Legit";
                                var86_46 = "Rage";
                                new SwitchElement(v15, var86_46, var85_45, var84_44, var83_43).render(var51_15, var1_1, this.getX() + 84.0f, this.getY() + 47.0f + var73_20, var2_2, var3_3);
                                var90_47 = new MacroPanel$hoveringComponent$2(this);
                                var91_48 = this.getWidth();
                                new Button(var74_22, var91_48, var90_47).render(var13_6, var1_1, this.getX() + 84.0f, this.getY() + 62.0f + var73_20, var2_2, var3_3);
                                if (var71_16) break block18;
                            }
                            v14 = var75_38 instanceof SettingMacro;
                        }
                        if (!v14) break block23;
                        MacroPanel.b.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 32.0f + var73_20, var2_2, var3_3);
                        MacroPanel.settingField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 47.0f + var73_20, var2_2, var3_3);
                        MacroPanel.pressValueField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 62.0f + var73_20, var2_2, var3_3);
                        MacroPanel.releaseValueField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 77.0f + var73_20, var2_2, var3_3);
                        v18 = this.getWidth();
                        v19 = MacroPanel.selectedMacro;
                        Intrinsics.checkNotNull(v19);
                        v20 = v19.getMacros();
                        Intrinsics.checkNotNull(v20, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
                        var94_49 = new MacroPanel$hoveringComponent$5(this);
                        var95_50 = new SettingMacro$hold$1((SettingMacro)v20);
                        var96_51 = "Hold";
                        var97_52 = "Toggle";
                        new SwitchElement(v18, var97_52, var96_51, var95_50, var94_49).render(var51_15, var1_1, this.getX() + 84.0f, this.getY() + 92.0f + var73_20, var2_2, var3_3);
                        var101_53 = new MacroPanel$hoveringComponent$3(this);
                        var102_54 = this.getWidth();
                        new Button(var74_22, var102_54, var101_53).render(var13_6, var1_1, this.getX() + 84.0f, this.getY() + 107.0f + var73_20, var2_2, var3_3);
                        if (var71_16) break block18;
                    }
                    throw new NoWhenBranchMatchedException();
                }
                v21 = this.getX();
                v22 = this.getY();
                v23 = this.getWidth();
                v24 = this.getHeight();
                v25 = Color.BLACK;
                Intrinsics.checkNotNullExpressionValue(v25, "BLACK");
                var106_55 = MacroPanel.e.getValue();
                v26 = RenderUtil.INSTANCE.withAlpha(v25, var106_55);
                v27 = Color.BLACK;
                Intrinsics.checkNotNullExpressionValue(v27, "BLACK");
                var110_56 = MacroPanel.e.getValue();
                v28 = RenderUtil.INSTANCE.withAlpha(v27, var110_56);
                v29 = Color.BLACK;
                Intrinsics.checkNotNullExpressionValue(v29, "BLACK");
                var114_57 = MacroPanel.e.getValue();
                v30 = Color.BLACK;
                Intrinsics.checkNotNullExpressionValue(v30, "BLACK");
                var118_58 = MacroPanel.e.getValue();
                v31 = RenderUtil.INSTANCE.withAlpha(v30, var118_58);
                v32 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v32, "pose(...)");
                Gradient2D.E(v21, v22, v23, v24, 5.0f, v26, v28, var39_14, RenderUtil.INSTANCE.withAlpha(v29, var114_57), v31, v32, 0.0f, null, null);
            }
            if (!var71_16) break block24;
            v33 = this.getGuiElement();
            if (v33 != null) {
                v33.render(var1_1, this.getX() + this.getWidth() / 2.0f - 60.0f, this.getY() + this.getHeight() / 2.0f - 35.0f, var31_11, var2_2, var3_3);
            }
        }
    }

    @Override
    public void keyPressed(int n, long l) {
        long l2 = l ^ 0L;
        if (editingMacro != null) {
            Object object = editingMacro;
            if (object != null && (object = ((MacroElement)object).getMacros()) != null) {
                object.setBind(new Bind(n, false, false, 6, null));
            }
            editingMacro = null;
            return;
        }
        super.keyPressed(n, l2);
    }

    @Override
    public void mouseClicked(float f, float f2, short s, int n, int n2, char c) {
        long l;
        long l2 = l = (long)s << 48 | (long)n2 << 32 >>> 16 | (long)c << 48 >>> 48;
        long l3 = l2 ^ 0L;
        int n3 = (int)(l3 >>> 48);
        int n4 = (int)(l3 << 16 >>> 32);
        int n5 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x23524E02DD14L;
        super.mouseClicked(f, f2, (short)n3, n, n4, (char)n5);
        GuiElement guiElement = this.getGuiElement();
        boolean bl = guiElement != null ? guiElement.mouseClicked(l4, f, f2) : false;
        if (bl) {
            return;
        }
        if (H) {
            TextField.Companion.A(null);
            this.setGuiElement(new TextBox());
            return;
        }
        this.setGuiElement(null);
        if (editingMacro != null) {
            MacroElement macroElement = editingMacro;
            Intrinsics.checkNotNull(macroElement);
            macroElement.getMacros().setBind(new Bind(n, false, true, 2, null));
            editingMacro = null;
            return;
        }
        if (hoveredElement != null) {
            F = hoveredElement;
            Element element = hoveredElement;
            Intrinsics.checkNotNull(element);
            element.isHovered(f, f2);
            return;
        }
        F = null;
        if (focusedElement != null) {
            switch (n) {
                case 0: {
                    if (B) {
                        Element element = focusedElement;
                        Intrinsics.checkNotNull(element);
                        editingMacro = (MacroElement)element;
                        return;
                    }
                    Element element = focusedElement;
                    Intrinsics.checkNotNull(element);
                    Macro macro = ((MacroElement)element).getMacros();
                    Element element2 = focusedElement;
                    Intrinsics.checkNotNull(element2);
                    macro.setEnabled(!((MacroElement)element2).getMacros().isEnabled());
                    break;
                }
                case 1: {
                    Element element = focusedElement;
                    Intrinsics.checkNotNull(element, "null cannot be cast to non-null type su.catlean.client.features.ui.panel.panels.macro.MacroItem");
                    MacroElement macroElement = selectedMacro = (MacroElement)element;
                    Intrinsics.checkNotNull(macroElement);
                    idField.setText(macroElement.getMacros().getId());
                    MacroElement macroElement2 = selectedMacro;
                    Intrinsics.checkNotNull(macroElement2);
                    if (macroElement2.getMacros() instanceof ChatMacro) {
                        MacroElement macroElement3 = selectedMacro;
                        Intrinsics.checkNotNull(macroElement3);
                        Macro macro = macroElement3.getMacros();
                        Intrinsics.checkNotNull(macro, "null cannot be cast to non-null type su.catlean.client.services.client.macro.ChatMacro");
                        messageField.setText(((ChatMacro)macro).getMessage());
                    }
                    MacroElement macroElement4 = selectedMacro;
                    Intrinsics.checkNotNull(macroElement4);
                    if (macroElement4.getMacros() instanceof ModuleConfig) {
                        MacroElement macroElement5 = selectedMacro;
                        Intrinsics.checkNotNull(macroElement5);
                        Macro macro = macroElement5.getMacros();
                        Intrinsics.checkNotNull(macro, "null cannot be cast to non-null type su.catlean.client.services.client.macro.ItemMacro");
                        itemField.setText(((ModuleConfig)macro).getItem());
                    }
                    MacroElement macroElement6 = selectedMacro;
                    Intrinsics.checkNotNull(macroElement6);
                    if (macroElement6.getMacros() instanceof SettingMacro) {
                        MacroElement macroElement7 = selectedMacro;
                        Intrinsics.checkNotNull(macroElement7);
                        Macro macro = macroElement7.getMacros();
                        Intrinsics.checkNotNull(macro, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
                        b.setText(((SettingMacro)macro).getModule());
                        MacroElement macroElement8 = selectedMacro;
                        Intrinsics.checkNotNull(macroElement8);
                        Macro macro2 = macroElement8.getMacros();
                        Intrinsics.checkNotNull(macro2, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
                        settingField.setText(((SettingMacro)macro2).getSetting());
                        MacroElement macroElement9 = selectedMacro;
                        Intrinsics.checkNotNull(macroElement9);
                        Macro macro3 = macroElement9.getMacros();
                        Intrinsics.checkNotNull(macro3, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
                        pressValueField.setText(((SettingMacro)macro3).getPressValue());
                        MacroElement macroElement10 = selectedMacro;
                        Intrinsics.checkNotNull(macroElement10);
                        Macro macro4 = macroElement10.getMacros();
                        Intrinsics.checkNotNull(macro4, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
                        releaseValueField.setText(((SettingMacro)macro4).getReleaseValue());
                    }
                    y.animateTo(0.0f);
                    break;
                }
                case 2: {
                    Element element = focusedElement;
                    Intrinsics.checkNotNull(element);
                    editingMacro = (MacroElement)element;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseScrolled(double d, double d2, double d3) {
        block1: {
            float f = this.getX();
            boolean bl = d <= (double)(this.getX() + 76.0f) ? (double)f <= d : false;
            if (!bl) break block1;
            f = this.getY();
            boolean bl2 = d2 <= (double)(this.getY() + this.getHeight() - 2.0f) ? (double)f <= d2 : false;
            if (bl2) {
                scroll += (float)d3 * 7.0f;
                scroll = Math.clamp(scroll, (float)macroElements.size() * -29.0f, 0.0f);
            }
        }
    }

    private static final Unit _clinit_$lambda$0(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = selectedMacro;
            if (object == null || (object = ((MacroElement)object).getMacros()) == null) break block0;
            object.setId(textField.getText());
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$1(TextField textField) {
        Intrinsics.checkNotNullParameter(textField, "it");
        MacroElement macroElement = selectedMacro;
        if (!((macroElement != null ? macroElement.getMacros() : null) instanceof ChatMacro)) {
            return Unit.INSTANCE;
        }
        MacroElement macroElement2 = selectedMacro;
        Macro macro = macroElement2 != null ? macroElement2.getMacros() : null;
        Intrinsics.checkNotNull(macro, "null cannot be cast to non-null type su.catlean.client.services.client.macro.ChatMacro");
        ((ChatMacro)macro).setMessage(textField.getText());
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    private static final Unit _clinit_$lambda$2(TextField var0) {
        v0 = MacroElement.B();
        Intrinsics.checkNotNullParameter(var0, "it");
        var6_1 = v0;
        if (!var6_1) ** GOTO lbl-1000
        v1 = MacroPanel.selectedMacro;
        if (v1 != null) {
            v2 = v1.getMacros();
        } else lbl-1000:
        // 2 sources

        {
            v2 = null;
        }
        if (!(v2 instanceof ModuleConfig)) {
            return Unit.INSTANCE;
        }
        if (!var6_1) ** GOTO lbl-1000
        v3 = MacroPanel.selectedMacro;
        if (v3 != null) {
            v4 = v3.getMacros();
        } else lbl-1000:
        // 2 sources

        {
            v4 = null;
        }
        Intrinsics.checkNotNull(v4, "null cannot be cast to non-null type su.catlean.client.services.client.macro.ItemMacro");
        var7_2 = var0.getText();
        ((ModuleConfig)v4).setItem(var7_2);
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    private static final Unit _clinit_$lambda$3(TextField var0) {
        v0 = MacroElement.B();
        Intrinsics.checkNotNullParameter(var0, "it");
        var5_1 = v0;
        if (!var5_1) ** GOTO lbl-1000
        v1 = MacroPanel.selectedMacro;
        if (v1 != null) {
            v2 = v1.getMacros();
        } else lbl-1000:
        // 2 sources

        {
            v2 = null;
        }
        if (!(v2 instanceof SettingMacro)) {
            return Unit.INSTANCE;
        }
        if (!var5_1) ** GOTO lbl-1000
        v3 = MacroPanel.selectedMacro;
        if (v3 != null) {
            v4 = v3.getMacros();
        } else lbl-1000:
        // 2 sources

        {
            v4 = null;
        }
        Intrinsics.checkNotNull(v4, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
        ((SettingMacro)v4).setModule(var0.getText());
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    private static final Unit _clinit_$lambda$4(TextField var0) {
        block13: {
            block11: {
                v0 = MacroElement.B();
                Intrinsics.checkNotNullParameter(var0, "it");
                var9_1 = v0;
                if (!var9_1) ** GOTO lbl-1000
                v1 = MacroPanel.selectedMacro;
                if (v1 != null) {
                    v2 = v1.getMacros();
                } else lbl-1000:
                // 2 sources

                {
                    v2 = null;
                }
                if (!(v2 instanceof SettingMacro)) {
                    return Unit.INSTANCE;
                }
                if (!var9_1) ** GOTO lbl-1000
                v3 = MacroPanel.selectedMacro;
                if (v3 != null) {
                    v4 = v3.getMacros();
                } else lbl-1000:
                // 2 sources

                {
                    v4 = null;
                }
                Intrinsics.checkNotNull(v4, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
                var10_2 = (SettingMacro)v4;
                var12_3 = ModuleManager.INSTANCE.L();
                var13_4 = var0;
                for (T var16_6 : var12_3) {
                    v5 = var16_6;
                    while (Intrinsics.areEqual((var17_7 = (Module)v5).getId(), var10_2.getModule())) {
                        v5 = var16_6;
                        if (!var9_1) continue;
                        break block11;
                    }
                }
                v5 = null;
            }
            v6 = var13_4;
            v7 = var20_8 = (Module)v5;
            if (!var9_1) break block13;
            if (v7 == null) ** GOTO lbl-1000
            v7 = var20_8;
        }
        v8 = var12_3 = v7.getSettings();
        if (!var9_1) ** GOTO lbl42
        if (v8 != null) {
            block12: {
                v8 = var12_3;
lbl42:
                // 2 sources

                var15_5 = v8;
                var13_4 = v6;
                var17_7 = var15_5;
                var18_9 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var15_5, 10));
                var22_10 = var17_7.iterator();
                while (var22_10.hasNext()) {
                    var23_12 = var22_10.next();
                    v9 = var18_9;
                    var24_13 = (Setting)var23_12;
                    if (var9_1) {
                        var19_11 = v9;
                        var19_11.add(var24_13.getId());
                        if (var9_1) continue;
                    }
                    break block12;
                }
                v9 = var18_9;
            }
            v10 = (List)v9;
            v6 = var13_4;
        } else lbl-1000:
        // 2 sources

        {
            v10 = CollectionsKt.emptyList();
        }
        var26_14 = v10;
        v6.setSuggestions(new Suggestions(var26_14));
        var10_2.setSetting(var0.getText());
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$5(TextField textField) {
        Intrinsics.checkNotNullParameter(textField, "it");
        MacroElement macroElement = selectedMacro;
        if (!((macroElement != null ? macroElement.getMacros() : null) instanceof SettingMacro)) {
            return Unit.INSTANCE;
        }
        MacroElement macroElement2 = selectedMacro;
        Macro macro = macroElement2 != null ? macroElement2.getMacros() : null;
        Intrinsics.checkNotNull(macro, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
        String string = textField.getText();
        ((SettingMacro)macro).setPressValue(string);
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$6(TextField textField) {
        Intrinsics.checkNotNullParameter(textField, "it");
        MacroElement macroElement = selectedMacro;
        if (!((macroElement != null ? macroElement.getMacros() : null) instanceof SettingMacro)) {
            return Unit.INSTANCE;
        }
        MacroElement macroElement2 = selectedMacro;
        Macro macro = macroElement2 != null ? macroElement2.getMacros() : null;
        Intrinsics.checkNotNull(macro, "null cannot be cast to non-null type su.catlean.client.services.client.macro.SettingMacro");
        ((SettingMacro)macro).setReleaseValue(textField.getText());
        return Unit.INSTANCE;
    }

    private static final Unit removeSelected() {
        MacroElement macroElement = selectedMacro;
        Intrinsics.checkNotNull(macroElement);
        Macro macro = macroElement.getMacros();
        MacroManager.INSTANCE.removeMacro(macro);
        Collection collection = macroElements;
        MacroElement macroElement2 = selectedMacro;
        TypeIntrinsics.asMutableCollection(collection).remove(macroElement2);
        selectedMacro = null;
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    static {
        block29: {
            block28: {
                block27: {
                    block26: {
                        block25: {
                            block24: {
                                MacroPanel.c = ZkmKeys.a(-8280496583591170835L, 1725934197546740757L, MethodHandles.lookup().lookupClass()).a(207187093390126L);
                                var31 = MacroPanel.c ^ 120120577414307L;
                                MacroPanel.n = new HashMap<K, V>(13);
                                MacroPanel.zkmSetFlag(null);
                                var22_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_2 = 1; var23_2 < 8; ++var23_2) {
                                    v2 = v2;
                                    v2[var23_2] = (byte)(var31 << var23_2 * 8 >>> 56);
                                }
                                var22_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_3 = new String[33];
                                var27_4 = 0;
                                var26_5 = "asO\u00d9#\u00c2\u000e6dS\u00fe\u00b0\u0089\u00f9\u001f|1\u00c2\b{\u00fc\u0092\u00d7`\u0083g|$\u008c\u00a1\u00d8\u0095\u0010\u008bPo\u00fc\u0082=\u00f5\u00b763\u00b2;\u001d\f?\u00f1\u0010\u0012\u00f7\u00d0'\u0012\"\u009f\u00ff\u001dwQ\u008e/s\u00d5\u00d9\u0018-#\u0090\u00da\u0086O?*\u00df\f\u001c\u009f\u00b8%.\u0099>SE\u00a2\u0003\u0005\u00e0<\u0010[Q\u0090\u00e6\u00ca\u0085L+\u0085\u008c\u00be\u0000\u00d3\u0099%\u00d7(\u0015\u001e\u0087U}\u00bfo8\u0088\u009fJsm\u0002Z\u00eev\u00e5\u0081\u0018c\u009e\u008c\u00a2\u00c2\u00e5\u00d45g\u00e0\u00ab\u00feIO\u0094\u00efh3\u00e3\u00a8\u0010j4,!\u00fd\u0010\u0013\u001dl\u00fe^\u00c3D\u00b8\u0019\t\u0010\u0000\u00c6\u0096>\u00fb\u00f4VHl;+L\u00d3\u00ab\u00eb\u00de\u0010h9\u00f96q\u0097\u00ea\u00dd\u00a4\u00fc\u00c6c\u00af\u00a2\u001d\u0097\u0010\u00d8\u0092\r\u00f3n\u00dd7P\u00e0,\u00a0\u00fc\u00b3\u00c3\u00c00\u0090\u00b7\u0017\u00db\u0002M\u00b6\u0084\u0084\u00bf\u001bu\u00f9\u0006\u00d3\u00b4\u00b8\u00b5\u00a6N~k\u008c\u00b7\u00b7\u001cEO|\u008f7:B@\u009aY@\u0094\u00a5,p\u00b8~\u0001\u00c8z/6\u0085}\u008e\u0010\u0007\u0004/\u0000\u00abz\u009fP\u0089\u00104\u00d0\u0098!\u0093B]a\u00aaO\u00ca\u00b8\u0084\u0099:\u00e2 [\u00ab\u0096\u0000\u00c5\u0085\u00a9\u00c4zF\u00b7\u008f\f\u0001\u008dZA\u00b9\u00dcT8\u0002\u00e4\u0011\u00a8\u00e8\bv\bz\u00be\u008e\u0000[\u009d\"A\"\u00b9\u00a6\u0090)\u00bex\u00b1\u00c0R\u00aa\u00aa\u0017\u001ae\u00ad\u00ee'Y&\u009e=\u008d\u0090r\u0092\u00a9\u00d1\u00d6\u0080g\u00a55\u0001R\u00c9A\u007f\u00f5\u0080\u0092Y$T\u00d5\u0082\u00af\u00d8P;\u008aJ\u00c4Qh\u00f8+\u00b2}\u0018|\u008eL#X,\u00e8\u00c7<pO\u001c\u00fe\u007f\u00bfN\u0019|\u001e\u00a0P\u0007\u0014\u001a\u0083bY_g\u00a1s\u00ed\u00bb\u0003\u00fa\r\u00dfc$#l\u00c5\"\u00ad\u0095\u00ffh\u0018\u00fdt\u00f7\u000b,4\u00b8\u00b3\u00ed\u00eb\u0094\u009dl\u001aj\u001aC\r\u00ad\u0005\u009fw\u00af\u00c4@\u0006'\u0010-\u0080\u009a\u00b7\u00a8G'\u00cb\u0091\u00dc\u0081\u00e8\u00df\u00fa\u0084\u000es,\u00c9\u00cd\u00cfn\u0088\u00f5y\\\r\u0018\u00ca\u00cd\u00c8\u009cq\u00b7\u0011-\u008b\u00f7\u00bb\u00f6\u00cf\u00f2x\u008b\u009b(\u00da\u009d\t\u00b6\"\u00bb\t\u00b8\u00e7B\u00d1\u00bb\u00c6\u008b\u00ee\u00d5qI\\@\u0018\u008f\u00ed\u0004\u00e6\u0000\u00ba\u00f9\u00dc\u00e2G\u0014<\u00bf\u0087\u00d2\u0019iW({\u0017\u00cf?<\u00c6\u009f;\u00f5\u00fb\u00e0\u00b0\u00c7\u00de\u00f8\\\u0094\\tnY@\u00d9\u00e4\u00fa\u00b8!I\u00f1=(\u00e7G\u00d8}Jd\u0017\u0084\u0081W'{}\u00c1\u00cd\u00f5\u00bb$\b\u008bu\u00b2\u00bc\u00ae\"\u00cd\u00c8\u001c\u00ac\u00a04\u00ac\u0096^\u00d6\u001a\u00f5[\u00f6\u009e\u0001~\u0010\u0091\u00ea\u00b1\u0099{j\u00cbo\u00e78\rp\r\u00c7\u00c1r \u00ab\u00bf)\u0091\u00bc\u00024\u00ead\u00a6\u0014\u00c0\u008f\u00cfM\u009bD\u00de\r\u00e2\u00d5M\u0014nE+??\u0013\u0092\u008a\u00e5\u0010\u00ce1\u009f\u00d7dZiM\u00e0\u00ca\u00a7,W\u0015!C(W^!(\u0081<\u00b9dj\u00bf\u008f\u00ef\u0014\u00f6Y\u00af\u00c9D\u00cbr\u0090\u00ab\u000bI\u00e1cX\u00cb\u00ee\u00ad\u00a2\u0086\u00f5\u009d\u008f\u00b0<\u00b4LX\u0010<\u0091]\u00cc\u00b5\u009bN\u00bdL\u0096.\u0096[tb\u00ef\u0010\u00e3\u0006]x&\u0001/\u0004x\u00fa\u0084Y@BP\u0002\u0010\u0083X\u008e\\\u00f2\u00d7m\u00cb\to\u00cdb\u00bc\u00b6+\u00b8\u0098\u00a8\u00e0\u0000\u00e4\u0001f\u0094v\u00eb\u0002\u00e9e\u00d6^0\u001f\tP-}_\u00e5\u009cC\u0080\u0093ZYd\u00c7\u00ae{h\u00ea\u0000\u00fe\u0094w\u00db\u00f3\u00fc\u00c9&/\u00e2\u00eb\u00d2\u00d7G\u0014C\u00e5A\u001c\u0003\u0095\u00a1#\u0090\u00bd+\u00ce\u00ff{\u00cb\u0088\u00f3\u00a2V\u001c2k\u00d6\u0001:g\u00b2\u00bf\u0081\u00f7\u00fenS\u00d3\u0092\u00df\u0087pA\u0010\u00d2\u00e8B\u0084\u00eaU9\u00ba\u0002\u00ec\u00bc\u0096xo}\u00a1\u0091)LL\u00ff\u00db!\u00b1s\u0086l\u007fg\u008ft\u00ee\u00aap\u0091{\u00d8IV\u00a1!\u00b2\u0001\u00ebPd\u0010\u00a6mK((\u00a3\u00b5Bs\u00fbl\u0094\u00a0\u0001\u0098\u0010U\u009a\u00df\u00b6Ek\u009fQJ'\u00c4\u00b1\u00db\u00f5q\u00b5\u0010\u00ca\u0097zW~ l\u00a2\u0082\u00d7\u00c9\u00fd(i0\u00cf\u0010\u0006U;\u009c\\Tlg\u001d\u0015\u00176tJ\u00e9f\u0010lD\u00fc\u0099\u0080\u0000\u00b3\u00eb\u00dc\u0086\u00f8/\u0081\u00e5\u00c0\u00ca\u0010\u00eef\u00d0a2\u00cc\u001egD\u00dd\u00f5\u009a^O]\u00f6\u0010{\u00d8\u00d0`\u00ea\u001b\u008b\u0089\u0086\u0088\u00ca\u00ea\u00a5\u00f9}\u0095\u0090\u00e9R:)\u00b0c\u0017@B\u008fi2\u00f7\u00ea\u00f1\u00a7\u00c6\u0083\u0006\u0086&3\u00a5\u0015\u00ba\u0080LH\u00187\u00ff\u0080\u00b7[\u0011r\"0\u00be\u00b9E6\u00c9^lv\u00b2K\u00c06\u00fb\u00ad\u0018U-\u00a7@\u00af\u00f8\u00ae\u00a7\u008a\u00e6\u00a4\u0016L3\t\u008d\u00d5A\u00b6\u009ek\u00a5\u0088:\u00b6\u00bc\u00ef%=r\u0016)\u00d0_\u00df\u00ad\u00c9\u00a6'>\u00d8\u008cc\u0097\u0096\u00bb;\u009a\u00146\u0003\u00a3\b\u00f6/1\u00d1@|\u00d2\u0017\u0011\u009e\u0003d\u001e\u00d1\u000e\u00cc\u00a5Q\u0002\u00c3\u00fa\u00bb\u00f7p\u00e8\u008a $A\u009b\u00bb\u0092C\u00be\u0093,\u001f\u0082\u0088\u00acg,\u00f8\u00afL,\u00ad\u0093\u00c4\u00c5\u00ef){\u00a9\u00c4\u00c8=}\u0096\u00d2\u008f\u00bd~\u00d1\u001bie~\u0081\u00e9\u0093\u00a2\u00a1K\u00c15\u00c0xu@l-\u0005\u00a8\u00b85\u0089\u009d\u00bf:\u00bc\u0084i\u00cdl\u001f%R\u00dda\u00fc\u00db\u0097\u00f0Y\u00e0\u00d9\u00e0\n\u0093\u00f4TwWh\u0092\u0098\u00c5A\u00ea\u009c=6\u00b9\u00f7\u00f4R\u00f8O\u00fd#,#\u00b5\u00a1k\u0005)h\"\u00f8\u0097\u00fb{\bu\u00e6aq\u00c7X1\u00c3i\u00c9\u00aa\u00e5\u0001=\u0001\u00cf0\u00b4\u00a5\u0083\u00e8\u00cf&\u0002\u00d4-,LJ\u0080 v\u008f\u0099\u00d7:\u00d3RcQ\u001a\u00c5Y&|\u00b2\u000f\u009aO\\\u0081\u008a\u0091\u00c8\u00c2|\b\u00a76\u00c7\u0012\u00ffp\u0010\u0005\u00b2Q\u00d4\u00c0Fs\u0013\u00e9\u00f6\u0000[\u00a6\u008f\u00b0\u00f3";
                                var28_6 = 1374;
                                var25_7 = 32;
                                var24_8 = -1;
lbl21:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block24;
                                    break;
                                }
lbl26:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = MacroPanel.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\\\u00d0\u0000\u0087\u00ed\u00c0+\u0018\u0015\u00e0R\u00d1h\u00b3\u0084\u00e2\u00f2\u0002\u00ec\u00ca&\"\u00d6\u00e3\u0080\u0089\u00da\u00fe?\u001d7\u0093\u00cf\u00dc\u00ef\u008c\u0095T\u008a\u00df\u00e9Z\u00a5#\u00f4\u00ed\u00de\u00e2\u00f1\u0084\u009a\u00fb\nY\u00f7\u00ea\u00b4\u00c8}\u00b8w\u00cd\u001cN>8T\u00cf\u00bb\u0094g\u009c\u008726\u0096n\u0001\u00fb\u008b\u0013\u00ad;}\u00fej\u00afL\u00c6r\u00a1\u0098\u00fc-\u00d7\u009eA\u00e9>\u00fa\u0013\u0088\"\u00cb\u00a8\u00a9<F\u008e\u0094\u0086\u001a}2\u00d3\u0081K+\u0014=\u00ad\u0013\u00f4\u00d8`\u00ea\u00d0\u00d2lE\u00caE\u00cd .aF8\u0017\r\u00a8<W\u0083\u0007\u00fc\u0093\u00f7U\u00d1v\u00d5\u00c4";
                                    var28_6 = 153;
                                    var25_7 = 24;
                                    var24_8 = -1;
lbl35:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block24;
                                        break;
                                    }
                                    break;
                                }
lbl40:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = MacroPanel.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block25;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        MacroPanel.i = var29_3;
                        MacroPanel.m = new String[33];
                        MacroPanel.w = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var31 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[9];
                        var14_13 = 0;
                        var15_14 = "\u00b2t\u0017\u00cb[4P\u00f4\u008e\u009eIgX\u00a9}\u00ab\u00a0K\u0093\u00a8k\u00de\u00be\u00c7*\t\u009eU&u\u00e8\u009b\u00fb\u00e7\u00b2\u00d2?(\u00fe\u00e3\u001c}\u00db\u00a1\u0084O\u00ed\u0004$u\u00a7\u0010\u009f\u00fd\u00f6\u00b1";
                        var16_15 = 56;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block26;
                            break;
                        }
lbl79:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "\u00c9\u00d8\u00fdP\u00e0\u00ee\u0082\u0000]\u009e\u0010LB\u00dat\u00f1";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block26;
                                break;
                            }
                            break;
                        }
lbl92:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block27;
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
lbl105:
                        // 1 sources

                        ** continue;
                    }
                }
                MacroPanel.s = var17_12;
                MacroPanel.t = new Integer[9];
                var1_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var2_21 = 1; var2_21 < 8; ++var2_21) {
                    v17 = v17;
                    v17[var2_21] = (byte)(var31 << var2_21 * 8 >>> 56);
                }
                var1_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var0_22 = new long[3];
                var4_23 = 0;
                var5_24 = "7RM3\u0083W\u008c\u00ba\u00f0?\u009b\u00869\u00819R\u00f0?\u009b\u00869\u00819R";
                var6_25 = 24;
                var3_26 = 0;
                while (true) {
                    break block28;
                    break;
                }
lbl126:
                // 1 sources

                while (true) {
                    var0_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var3_26 < var6_25) ** continue;
                    break block29;
                    break;
                }
            }
            var7_27 = var5_24.substring(var3_26, var3_26 += 8).getBytes("ISO-8859-1");
            v18 = var4_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var1_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        MacroPanel.INSTANCE = new MacroPanel();
        MacroPanel.macroElements = new ArrayList<E>();
        MacroPanel.y = new Animation(Easing.OUT_QUINT, var0_22[2]);
        MacroPanel.e = new Animation(Easing.OUT_QUINT, var0_22[1]);
        MacroPanel.J = new Animation(Easing.OUT_QUINT, var0_22[0]);
        MacroPanel.idField = new TextField("Name", MacroPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        MacroPanel.messageField = new TextField("Text", MacroPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$1(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        var45_30 = new ArrayList<E>();
        var46_31 = var45_30;
        var60_32 = MacroPanel.INSTANCE.getWidth();
        var59_33 = "Item";
        v19 = Registries.BLOCK;
        Intrinsics.checkNotNullExpressionValue(v19, "BLOCK");
        var48_34 = v19;
        var49_35 = var46_31;
        var51_36 /* !! */  = var48_34;
        var52_37 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var48_34, 10));
        for (T var55_39 : var51_36 /* !! */ ) {
            var56_40 = (Block)var55_39;
            var57_41 = var52_37;
            v20 = var56_40.getTranslationKey();
            Intrinsics.checkNotNullExpressionValue(v20, "getDescriptionId(...)");
            var57_41.add(StringsKt.replace$default(v20, "block.minecraft.", "", false, 4, null));
        }
        var49_35.addAll((List)var52_37);
        v21 = Registries.ITEM;
        Intrinsics.checkNotNullExpressionValue(v21, "ITEM");
        var48_34 = v21;
        var49_35 = var46_31;
        var51_36 /* !! */  = var48_34;
        var52_37 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var48_34, 10));
        for (T var55_39 : var51_36 /* !! */ ) {
            var56_40 = (Item)var55_39;
            var57_41 = var52_37;
            v22 = var56_40.getTranslationKey();
            Intrinsics.checkNotNullExpressionValue(v22, "getDescriptionId(...)");
            var57_41.add(StringsKt.replace$default(v22, "item.minecraft.", "", false, 4, null));
        }
        var49_35.addAll((List)var52_37);
        4642614092219606829L ^ var31;
        var62_42 = var45_30;
        var63_43 = (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$2(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)();
        var64_44 = new Suggestions(var62_42);
        var65_45 = var60_32;
        var66_46 = var59_33;
        MacroPanel.itemField = new TextField(var66_46, var65_45, var64_44, var63_43);
        var45_30 = ModuleManager.INSTANCE.L();
        var60_32 = MacroPanel.INSTANCE.getWidth();
        var59_33 = "Module";
        var47_47 = var45_30;
        var48_34 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var45_30, 10));
        for (Iterable var51_36 : var47_47) {
            var52_37 = (Module)var51_36 /* !! */ ;
            var61_49 = var48_34;
            var61_49.add(var52_37.getId());
        }
        var61_49 = (List)var48_34;
        var67_50 = var61_49;
        var68_51 = (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$3(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)();
        var69_52 = new Suggestions(var67_50);
        var70_53 = var60_32;
        var71_54 = var59_33;
        MacroPanel.b = new TextField(var71_54, var70_53, var69_52, var68_51);
        MacroPanel.settingField = new TextField("Setting", MacroPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$4(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        MacroPanel.pressValueField = new TextField("Press", MacroPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$5(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        MacroPanel.releaseValueField = new TextField("Release", MacroPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$6(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        MacroPanel.INSTANCE.refresh();
        MacroPanel.INSTANCE.setY(240.0f);
    }

    public static void zkmSetFlag(String[] stringArray) {
        Q = stringArray;
    }

    public static String[] java_lang_String_arr_zkmGetFlag() {
        return Q;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3B21;
        if (m[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])MacroPanel.n.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    MacroPanel.n.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ih", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = i[n2].getBytes("ISO-8859-1");
            MacroPanel.m[n2] = MacroPanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return m[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MacroPanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ih" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x432F;
        if (t[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = s[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])w.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    w.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ih", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MacroPanel.t[n2] = n3;
        }
        return t[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MacroPanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ih" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MacroPanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MacroPanel.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

