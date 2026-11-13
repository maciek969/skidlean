/*
 * Decompiled with CFR 0.152.
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
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.gui.CategoryGroup;
import su.catlean.gui.CategoryPanel;
import su.catlean.gui.ClickGuiScreen$1;
import su.catlean.gui.Component;
import su.catlean.gui.ModuleButton;
import su.catlean.gui.SubCategoryTab;
import su.catlean.gui.TabBar;
import su.catlean.module.Category;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.render.RenderUtil;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.sound.Sounds;
import su.catlean.util.Animation;
import su.catlean.util.Async;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ClickGuiScreen
extends Screen {
    @NotNull
    public static final ClickGuiScreen INSTANCE;
    private static boolean w;
    @NotNull
    private static final List categoryPanels;
    @Nullable
    private static Component component;
    @NotNull
    private static Animation openAnimation;
    @NotNull
    private static Animation scaleAnimation;
    private static boolean I;
    private static boolean i;
    private static int X;
    private static int R;
    private static int n;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private ClickGuiScreen() {
        long l = 0L;
        l = a ^ l;
        super(Text.of((String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)4574, (long)(0x1770957F6A20D5C8L ^ l)))));
    }

    public final boolean V() {
        return w;
    }

    public final void A(boolean bl) {
        w = bl;
    }

    @Nullable
    public final Component getComponent() {
        return component;
    }

    public final void setComponent(@Nullable Component component) {
        ClickGuiScreen.component = component;
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, float f) {
        block0: {
            long l = a ^ 0x52A0E4173900L;
            long l2 = l ^ 0x1C82EC8C364FL;
            Intrinsics.checkNotNullParameter(drawContext, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)7574, (long)(0x679DBF84072B0693L ^ l))));
            X = n;
            R = n2;
            String string = ClickGuiScreen$1.zkmGetFlag();
            if (string == null || component == null) break block0;
            INSTANCE.renderBackground(drawContext, l2);
        }
    }

    public final void renderBackground(@NotNull DrawContext drawContext, long l) {
        String string;
        long l2;
        long l3;
        block16: {
            int n;
            long l4;
            long l5;
            block21: {
                block22: {
                    block20: {
                        block19: {
                            block18: {
                                boolean bl;
                                block17: {
                                    long l6 = l = a ^ l;
                                    l5 = l6 ^ 0x4BFCB31E8F68L;
                                    l4 = l6 ^ 0x509BE1C9605AL;
                                    l3 = l6 ^ 0xD97F0490263L;
                                    l2 = l6 ^ 0x22159DB7B0B3L;
                                    String string2 = ClickGuiScreen$1.zkmGetFlag();
                                    Intrinsics.checkNotNullParameter(drawContext, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)27699, (long)(0x3560CA3CF43E4A7L ^ l))));
                                    string = string2;
                                    bl = i;
                                    if (string == null) break block17;
                                    if (bl) break block18;
                                    bl = I;
                                }
                                if (string != null && !bl) {
                                    openAnimation.animateTo(0.0f);
                                    bl = i = true;
                                }
                            }
                            if (Globals.getMc().world == null) {
                                this.renderBackground(drawContext, X, R, 1.0f);
                            }
                            openAnimation.animate(0.1f);
                            drawContext.getMatrices().pushMatrix();
                            drawContext.getMatrices().translate((float)this.width / 2.0f, (float)this.height / 2.0f);
                            n = i;
                            if (string != null) {
                                if (n != 0) {
                                    drawContext.getMatrices().scale(1.1f - openAnimation.getValue());
                                }
                                n = I;
                            }
                            if (string == null) break block19;
                            if (n == 0) break block20;
                            scaleAnimation.animate(1.0f);
                            drawContext.getMatrices().scale(1.0f + scaleAnimation.getValue());
                            RenderUtil.INSTANCE.m(1.0f - scaleAnimation.getValue());
                            float f = scaleAnimation.getValue() - 0.999f;
                            n = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                        }
                        if (string == null) break block21;
                        if (n < 0) break block22;
                        I = false;
                        super.close();
                        if (string != null) break block22;
                        Module.zkmSetFlag(new Module[1]);
                    }
                    scaleAnimation.animateTo(0.0f);
                }
                drawContext.getMatrices().translate((float)(-this.width) / 2.0f, (float)(-this.height) / 2.0f);
                n = 1;
            }
            w = n;
            Iterable iterable = categoryPanels;
            for (Object t : iterable) {
                CategoryPanel categoryPanel = (CategoryPanel)t;
                categoryPanel.renderHeader(drawContext, X, l4, R);
                if (string != null) {
                    if (string != null) continue;
                }
                break block16;
            }
            drawContext.getMatrices().popMatrix();
            TabBar.INSTANCE.render(drawContext, X, R, l5);
        }
        if (string != null) {
            Component component = ClickGuiScreen.component;
            if (component != null) {
                component.render(drawContext, X, R, l2);
            }
        }
        FontRenderer fontRenderer = Fonts.getSF_M();
        CallSite callSite = ClickGuiScreen.zkmBootstrap("w", (int)16189, (long)(0x51F54A19745737AEL ^ l));
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)23859, (long)(0x63F0C485CB7ED5A3L ^ l))));
        fontRenderer.drawStringWithShadow(drawContext, (String)((Object)callSite), l3, 5.0f, 5.0f, color);
        FontRenderer fontRenderer2 = Fonts.getSF_M();
        CallSite callSite2 = ClickGuiScreen.zkmBootstrap("w", (int)27092, (long)(0x7AEDBF3A42FEE14BL ^ l));
        float f = (float)Globals.getMc().getWindow().getScaledHeight() - 10.0f;
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)9822, (long)(0x1F880C9B77D12EC3L ^ l))));
        fontRenderer2.drawStringWithShadow(drawContext, (String)((Object)callSite2), l3, (float)Globals.getMc().getWindow().getScaledWidth() - 85.0f, f, color2);
        RenderUtil.INSTANCE.m(1.0f);
    }

    @Override
    public void tick() {
        CategoryPanel categoryPanel;
        Iterable iterable;
        long l = a ^ 0x377A37045501L;
        int n = (int)((l ^ 0x7DF0AAE373BEL) << 48 >>> 48);
        String string = ClickGuiScreen$1.zkmGetFlag();
        super.tick();
        String string2 = string;
        if (component != null) {
            return;
        }
        boolean bl = KeyboardUtil.isKeyPressed((char)n, (int)ClickGuiScreen.zkmBootstrap2("l", (int)26365, (long)(0x61D792DC0D612828L ^ l)));
        if (string2 != null) {
            if (bl) {
                iterable = categoryPanels;
                for (Object t : iterable) {
                    categoryPanel = (CategoryPanel)t;
                    categoryPanel.setCurrentX(categoryPanel.getCurrentX() - ClickGuiScreen.zkmBootstrap2("l", (int)10731, (long)(0x3D65EB61DF68E738L ^ l)));
                    if (string2 != null && string2 != null) continue;
                }
            }
            bl = KeyboardUtil.isKeyPressed((char)n, (int)ClickGuiScreen.zkmBootstrap2("l", (int)14975, (long)(0x634FB215FB0BF4A9L ^ l)));
        }
        if (string2 != null) {
            if (bl) {
                iterable = categoryPanels;
                for (Object t : iterable) {
                    categoryPanel = (CategoryPanel)t;
                    categoryPanel.setCurrentX(categoryPanel.getCurrentX() + ClickGuiScreen.zkmBootstrap2("l", (int)10731, (long)(0x3D65EB61DF68E738L ^ l)));
                    if (string2 != null && string2 != null) continue;
                }
            }
            bl = KeyboardUtil.isKeyPressed((char)n, (int)ClickGuiScreen.zkmBootstrap2("l", (int)6199, (long)(0x27B98A55E70B56E5L ^ l)));
        }
        if (string2 != null) {
            if (bl) {
                iterable = categoryPanels;
                for (Object t : iterable) {
                    categoryPanel = (CategoryPanel)t;
                    categoryPanel.setCurrentY(categoryPanel.getCurrentY() - ClickGuiScreen.zkmBootstrap2("l", (int)10731, (long)(0x3D65EB61DF68E738L ^ l)));
                    if (string2 != null && string2 != null) continue;
                }
            }
            bl = KeyboardUtil.isKeyPressed((char)n, (int)ClickGuiScreen.zkmBootstrap2("l", (int)1033, (long)(0x60DE540CA75ACAD9L ^ l)));
        }
        if (bl) {
            iterable = categoryPanels;
            for (Object t : iterable) {
                categoryPanel = (CategoryPanel)t;
                categoryPanel.setCurrentY(categoryPanel.getCurrentY() + ClickGuiScreen.zkmBootstrap2("l", (int)10731, (long)(0x3D65EB61DF68E738L ^ l)));
                if (string2 != null && string2 != null) continue;
                break;
            }
        }
    }

    private final void selectCategory(Category category) {
        Iterable iterable = categoryPanels;
        Iterator iterator2 = iterable.iterator();
        String string = ClickGuiScreen$1.zkmGetFlag();
        block0: while (true) {
            boolean bl = iterator2.hasNext();
            block1: while (bl) {
                Object t = iterator2.next();
                CategoryPanel categoryPanel = (CategoryPanel)t;
                if (string == null) break block0;
                if (string == null || !categoryPanel.getCategory().getSubCategories().contains(category)) continue block0;
                Iterable iterable2 = categoryPanel.getTabs();
                for (Object t2 : iterable2) {
                    SubCategoryTab subCategoryTab = (SubCategoryTab)t2;
                    if (string != null) {
                        bl = Intrinsics.areEqual(subCategoryTab.getSubCategory(), category);
                        if (string == null) continue block1;
                        if (bl) {
                            categoryPanel.setModuleButton(null);
                            Sounds.INSTANCE.getSettingsClose().play();
                            if (string != null && !Intrinsics.areEqual(categoryPanel.getSelectedTab().getSubCategory(), category)) {
                                categoryPanel.switchTab(subCategoryTab);
                            }
                        }
                    }
                    if (string != null) continue;
                }
                if (string != null) continue block0;
            }
            break;
        }
    }

    public final void findModuleButton(@NotNull Module module) {
        long l = 0L;
        l = a ^ l;
        String string = ClickGuiScreen$1.zkmGetFlag();
        Intrinsics.checkNotNullParameter(module, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)8001, (long)(0x75535AC4F3C3DC8AL ^ l))));
        Iterable iterable = categoryPanels;
        Iterator iterator2 = iterable.iterator();
        String string2 = string;
        block0: while (true) {
            boolean bl = iterator2.hasNext();
            block1: while (bl) {
                Object t = iterator2.next();
                CategoryPanel categoryPanel = (CategoryPanel)t;
                Iterable iterable2 = categoryPanel.getModuleButtons();
                if (string2 == null) break block0;
                for (Object t2 : iterable2) {
                    ModuleButton moduleButton = (ModuleButton)t2;
                    if (string2 != null) {
                        bl = Intrinsics.areEqual(moduleButton.getModule(), module);
                        if (string2 == null) continue block1;
                        if (bl) {
                            categoryPanel.setModuleButton(moduleButton);
                        }
                    }
                    if (string2 != null) continue;
                }
                if (string2 != null) continue block0;
            }
            break;
        }
    }

    public final void openModule(@NotNull Module module) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(module, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)19700, (long)(0x3E84B7145F8E4220L ^ l))));
        this.selectCategory(module.getCategory());
        Async async = Async.INSTANCE;
        CallSite callSite = ClickGuiScreen.zkmBootstrap2("l", (int)20167, (long)(0x187F381F6381F9CCL ^ l));
        async.getExecutor().execute(new ClickGuiScreen$1((int)callSite, module));
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean mouseClicked(@NotNull Click click, boolean bl) {
        String string;
        int n;
        double d;
        double d2;
        long l;
        block7: {
            Component component;
            long l2;
            block6: {
                long l3;
                long l4 = l3 = a ^ 0x1941813FF734L;
                l2 = l4 ^ 0xF014E66AAA1L;
                l = l4 ^ 0x6E0680826227L;
                Intrinsics.checkNotNullParameter(click, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)16806, (long)(0x2A9F5B01D9B69494L ^ l3))));
                d2 = click.comp_4798();
                d = click.comp_4799();
                n = click.button();
                string = ClickGuiScreen$1.zkmGetFlag();
                component = ClickGuiScreen.component;
                if (string == null) break block6;
                if (component == null) break block7;
                Component component2 = ClickGuiScreen.component;
                component = component2;
                Intrinsics.checkNotNull(component2);
            }
            component.updatePosition(l2, (int)d2, (int)d);
            return false;
        }
        boolean bl2 = TabBar.INSTANCE.mouseClicked(d2, d, n);
        if (string != null) {
            if (!bl2) {
                Iterable iterable = categoryPanels;
                for (Object t : iterable) {
                    CategoryPanel categoryPanel = (CategoryPanel)t;
                    categoryPanel.mouseClicked(d2, d, l, n);
                    if (string != null && string != null) continue;
                }
            }
            bl2 = super.mouseClicked(click, bl);
        }
        return bl2;
    }

    @Override
    public boolean mouseReleased(@NotNull Click click) {
        boolean bl;
        block3: {
            long l;
            block2: {
                l = a ^ 0x12A3D7F5AAE2L;
                Intrinsics.checkNotNullParameter(click, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)21760, (long)(0x33B57B8168EBDDEFL ^ l))));
                double d = click.comp_4798();
                String string = ClickGuiScreen$1.zkmGetFlag();
                double d2 = click.comp_4799();
                click.button();
                Iterable iterable = categoryPanels;
                for (Object t : iterable) {
                    CategoryPanel categoryPanel = (CategoryPanel)t;
                    categoryPanel.mouseReleased(d, d2);
                    if (string != null) {
                        if (string != null) continue;
                    }
                    break block2;
                }
                TabBar.INSTANCE.r(d, d2);
            }
            bl = super.mouseReleased(click);
            if (ZkmReferences.zkmBootstrap("\u00c5", (long)-8183103076139101954L, (long)l) == null) break block3;
            ClickGuiScreen$1.zkmSetFlag("ZKIqm");
        }
        return bl;
    }

    @Override
    public boolean keyPressed(@NotNull KeyInput keyInput) {
        block6: {
            String string;
            int n;
            long l;
            long l2;
            block8: {
                Component component;
                long l3;
                block7: {
                    long l4;
                    long l5 = l4 = a ^ 0x20837EB662A5L;
                    l3 = l5 ^ 0x591A1DE84FFCL;
                    l2 = l5 ^ 0x56CBF5449F07L;
                    l = l5 ^ 0x313D6277C7B3L;
                    Intrinsics.checkNotNullParameter(keyInput, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)2562, (long)(0x679ED73ED2D5CAA5L ^ l4))));
                    n = keyInput.comp_4795();
                    string = ClickGuiScreen$1.zkmGetFlag();
                    keyInput.comp_4796();
                    keyInput.comp_4797();
                    component = ClickGuiScreen.component;
                    if (string == null) break block7;
                    if (component == null) break block8;
                    Component component2 = ClickGuiScreen.component;
                    component = component2;
                    Intrinsics.checkNotNull(component2);
                }
                component.keyPressed(n, l3);
                return false;
            }
            Iterable iterable = categoryPanels;
            for (Object t : iterable) {
                CategoryPanel categoryPanel = (CategoryPanel)t;
                categoryPanel.keyPressed(n, l2);
                if (string != null) {
                    if (string != null) continue;
                }
                break block6;
            }
            TabBar.INSTANCE.keyPressed(l, n);
        }
        return super.keyPressed(keyInput);
    }

    @Override
    public boolean charTyped(@NotNull CharInput charInput) {
        block10: {
            char c;
            String string;
            block14: {
                Component component;
                block13: {
                    Character c2;
                    block12: {
                        block11: {
                            long l = a ^ 0x6684DB93CB99L;
                            String string2 = ClickGuiScreen$1.zkmGetFlag();
                            Intrinsics.checkNotNullParameter(charInput, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)27646, (long)(0x4DFC1AE57988026CL ^ l))));
                            string = string2;
                            if (string == null) break block11;
                            String string3 = charInput.asString();
                            Intrinsics.checkNotNullExpressionValue(string3, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)9262, (long)(0x1D9AA56222B5CDB6L ^ l))));
                            char[] cArray = string3.toCharArray();
                            Intrinsics.checkNotNullExpressionValue(cArray, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)1200, (long)(0xF8559B0882AED20L ^ l))));
                            c2 = ArraysKt.firstOrNull(cArray);
                            if (c2 != null) break block12;
                        }
                        return super.charTyped(charInput);
                    }
                    c = c2.charValue();
                    component = ClickGuiScreen.component;
                    if (string == null) break block13;
                    if (component == null) break block14;
                    Component component2 = ClickGuiScreen.component;
                    component = component2;
                    Intrinsics.checkNotNull(component2);
                }
                component.charTyped(c);
                return super.charTyped(charInput);
            }
            Iterable iterable = categoryPanels;
            for (Object t : iterable) {
                CategoryPanel categoryPanel = (CategoryPanel)t;
                categoryPanel.charTyped(c);
                if (string != null) {
                    if (string != null) continue;
                }
                break block10;
            }
            TabBar.INSTANCE.charTyped(c);
        }
        return super.charTyped(charInput);
    }

    @Override
    public boolean mouseScrolled(double d, double d2, double d3, double d4) {
        boolean bl;
        CategoryPanel categoryPanel;
        Iterable iterable;
        String string;
        block13: {
            block15: {
                Component component;
                block14: {
                    string = ClickGuiScreen$1.zkmGetFlag();
                    component = ClickGuiScreen.component;
                    if (string == null) break block14;
                    if (component == null) break block15;
                    Component component2 = ClickGuiScreen.component;
                    component = component2;
                    Intrinsics.checkNotNull(component2);
                }
                component.mouseScrolled(d, d2, d4);
                return super.mouseScrolled(d, d2, d3, d4);
            }
            boolean bl2 = TabBar.INSTANCE.b(d, d2, d4);
            if (string != null) {
                if (bl2) {
                    return super.mouseScrolled(d, d2, d3, d4);
                }
                bl2 = false;
            }
            boolean bl3 = bl2;
            iterable = categoryPanels;
            for (Object t : iterable) {
                categoryPanel = (CategoryPanel)t;
                if (string != null) {
                    bl = categoryPanel.mouseScrolled(d, d2, d4, d3);
                    if (string == null) break block13;
                    if (bl) {
                        bl3 = true;
                    }
                }
                if (string != null) continue;
            }
            bl = bl3;
        }
        if (string != null) {
            if (!bl) {
                iterable = categoryPanels;
                for (Object t : iterable) {
                    categoryPanel = (CategoryPanel)t;
                    categoryPanel.scroll(d4, d3);
                    if (string != null && string != null) continue;
                }
            }
            bl = super.mouseScrolled(d, d2, d3, d4);
        }
        return bl;
    }

    @Override
    public void close() {
        block2: {
            Iterable iterable = categoryPanels;
            Iterator iterator2 = iterable.iterator();
            String string = ClickGuiScreen$1.zkmGetFlag();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                CategoryPanel categoryPanel = (CategoryPanel)t;
                categoryPanel.closeSettings();
                if (string != null) {
                    if (string != null) continue;
                }
                break block2;
            }
            w = false;
            I = true;
            i = false;
        }
    }

    @Override
    protected void applyBlur(@NotNull DrawContext drawContext) {
        long l = a ^ 0x7ADA6EAE0BEEL;
        Intrinsics.checkNotNullParameter(drawContext, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)7574, (long)(0x679D97FE8D92347DL ^ l))));
    }

    @Override
    public void renderBackground(@NotNull DrawContext drawContext, int n, int n2, float f) {
        long l = a ^ 0x6C5287A03F01L;
        Intrinsics.checkNotNullParameter(drawContext, (String)((Object)ClickGuiScreen.zkmBootstrap("w", (int)7574, (long)(0x679D8176649C0092L ^ l))));
    }

    public static final List getCategoryPanels() {
        return categoryPanels;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block28: {
            block27: {
                block26: {
                    block25: {
                        block24: {
                            block23: {
                                ClickGuiScreen.a = ZkmKeys.a(-5088834823080897051L, 1229873288916935859L, MethodHandles.lookup().lookupClass()).a(86875423871911L);
                                var31 = ClickGuiScreen.a ^ 107832039035801L;
                                v0 = var31 ^ 109020214640157L;
                                var33_1 = (int)(v0 >>> 48);
                                var34_2 = v0 << 16 >>> 16;
                                ClickGuiScreen.d = new HashMap<K, V>(13);
                                ClickGuiScreen.zkmSetFlag(0);
                                var22_3 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v1 = SecretKeyFactory.getInstance("DES");
                                v2 = new byte[8];
                                v3 = v2;
                                v2[0] = (byte)(var31 >>> 56);
                                for (var23_4 = 1; var23_4 < 8; ++var23_4) {
                                    v3 = v3;
                                    v3[var23_4] = (byte)(var31 << var23_4 * 8 >>> 56);
                                }
                                var22_3.init(2, (Key)v1.generateSecret(new DESKeySpec(v3)), new IvParameterSpec(new byte[8]));
                                var29_5 = new String[15];
                                var27_6 = 0;
                                var26_7 = "w_\u00a6\u0090uV\u00b7r4gY\u000f\u00eb\u0095\u00c0\u00df\u0010\u0097\u00e9~\u0006\u00bbi]\u0093\u00a4\u0086\u0003\u008er\u0003\u00b9\u0096\u0010\u00fe\u0096\u00e7\u00beXH\u00cc\u00cb\u00b9\u00a8|'=4\u0014\u00f3(\u00bd*\u00ef\u00f7T\u00179\u00aak\u00b4\u00e3\u00d7\u009e(zV\u009fw\u009d\u00fa\u00ae_\u0004s\u00f6\u00c8OF\u000e*aP\u00e1{\u00d1N,G\u00ae\u00f7\u0010h\u00b09lz\u00b3\"nJUc\r\u00e7g\u00114\u0010X\u0002\u00870\u009d\u00fdd\u00c8F\u009d\u0004;F\\\u00cc\u00cf(j \u001c\u00a9q\u00d9\u00d0G:\u00e7\u00ee\u0087\u00cbfmb\u00f4;\u00dd8\u00b0\u00e3\u00cc\u00f3rL\u00d4\u00bbMb\b\u00ff\u00ef7T\u0082{\u0091p\u00dd\u01086\u00ef\u00c6\u0087\u00a1\u0011}\u000e\u00c6\t)\u001b\u00e7_2\u00af\u0006K\u00bc\u000b\u00a2\u00f9\u00eb\u000b\u0007k\u0082\u00f6q\u00f4\u0089\u0017\u008a-\u0094@\\\u00bb\u00e4$W:\u00bd2\u0098\u00a3\u0081\u00a3\u00c8A0T\u00cc?m}o^Q\u001a\u00ec\u00c7l\u0095`\u00fb\u00d0\u00ca\u00db&\u00f9f\u0080w|?\u00f7\u00e7=96\u001bLwr\u00c7\u00a08\u009b\u00aa\u00bc\u0089bV\u0094\u00ee\u0091\u0004lj4\u0019\u000b\n\u009d\u00cd\u00946\u008b\u00d6t\u00eb\u00ba\u00f2\r\u00da\u00d7\u00fd\u00ba\u00007[\u0001\u00d35\u0001|\u00f1\u009b\u00bd\u0098!\u0001\u00dcs \u00a1\u00a6\u0013~QK\u00f8L\u00c3\u000f\u0016\u001e!\u0091\r\u00aaanw[\u00de~+\u00f6M\u00c5\u0017\u00d3\u00feW\u00e2!\u008e\u001d\u00f0\u0000*q=\u00a7Tp\u00c5\u008c\u00e0=\u008a\u00d3_\u00f5\u00e1i\u00e7\u00a9\u00fc\u009f\u00f1\u0083I\u00fauq\u008d\u00a5\u0015b\u0013c\u00ce~\u0019\u000b2D`\u00b2\u00bd\u009bS\u00b6\u00cf)y\u00a0\u00d0\u00e0\u0014V\u00aaw\u00b0\u00ff\u0081\u00d3q\u00a3\u00ee\f{3\u00f8\u00dcI\u00e8%\u00ed%\u00ed\u0018\u00f4l\u0083 IR\u00d8\u0005\u00b6Y\u001c\u00df\u00ee}\u0087Ze\u0088\u001f\u0010\u000b\u00e0\u00b7\u00c40\u0014\u00cf\u00d2\u00fa\u00fa\u009d\u00c3c\u00deQ\u00f3\u0010V\u00de\u00cc\u0007D*\u00a5j\u009b2\u00b8\u0082\u008a*\rp\u0010!\u00db{\u00e1!\u00b9\u001a\u0091$\u00d6\u00808\u00d8d\u0098\u00ff8\u008c\u008f\u00fe\u0002N\u00cf(U\u00afOX#\u00e0{\u000e\u00f5\u00fbF\u00ecZL\u00b5hu-Y*QQ\u00a5\u00fbY\u008d\u00ed\u009bc\u00d4\u00aa\u0087\u0093\u00b9G:\u00caf\u001f\u00cfK\u0084)\u00c2]3*\u000e\u00b3\u0010>QM\u00e95\u00af\u0080\u00ac\u00a1\u00a4\u00c4\u0007\u00f3\u00ef3\\";
                                var28_8 = 556;
                                var25_9 = 16;
                                var24_10 = -1;
lbl26:
                                // 2 sources

                                while (true) {
                                    v4 = ++var24_10;
                                    v5 = var26_7.substring(v4, v4 + var25_9);
                                    v6 = -1;
                                    break block23;
                                    break;
                                }
lbl31:
                                // 1 sources

                                while (true) {
                                    var29_5[var27_6++] = ClickGuiScreen.zkmDecodeUtf8(var30_11).intern();
                                    if ((var24_10 += var25_9) < var28_8) {
                                        var25_9 = var26_7.charAt(var24_10);
                                        ** continue;
                                    }
                                    var26_7 = "\u00df^LB\u00c4\u00c5\u00fe\u00a1\u00eb]\u001c{5\u00d8\u00db\u00ec(\u00a6\u00fc\u000f\u001ez\u00ac\u00a6\u001bL\u00a1\u0098s\u00c7sx\u00ea\u00d9W\u0095hV\u00de\u0005\u00b6&\u00a4n\b\u00b3@\u00f2\u00aazW\u001c7\u00cf\u008c\u00f1\u008f";
                                    var28_8 = 57;
                                    var25_9 = 16;
                                    var24_10 = -1;
lbl40:
                                    // 2 sources

                                    while (true) {
                                        v7 = ++var24_10;
                                        v5 = var26_7.substring(v7, v7 + var25_9);
                                        v6 = 0;
                                        break block23;
                                        break;
                                    }
                                    break;
                                }
lbl45:
                                // 1 sources

                                while (true) {
                                    var29_5[var27_6++] = ClickGuiScreen.zkmDecodeUtf8(var30_11).intern();
                                    if ((var24_10 += var25_9) < var28_8) {
                                        var25_9 = var26_7.charAt(var24_10);
                                        ** continue;
                                    }
                                    break block24;
                                    break;
                                }
                            }
                            var30_11 = var22_3.doFinal(v5.getBytes("ISO-8859-1"));
                            switch (v6) {
                                default: {
                                    ** continue;
                                }
                                ** case 0:
lbl57:
                                // 1 sources

                                ** continue;
                            }
                        }
                        ClickGuiScreen.b = var29_5;
                        ClickGuiScreen.c = new String[15];
                        ClickGuiScreen.g = new HashMap<K, V>(13);
                        var11_12 = Cipher.getInstance("DES/CBC/NoPadding");
                        v8 = SecretKeyFactory.getInstance("DES");
                        v9 = new byte[8];
                        v10 = v9;
                        v9[0] = (byte)(var31 >>> 56);
                        for (var12_13 = 1; var12_13 < 8; ++var12_13) {
                            v10 = v10;
                            v10[var12_13] = (byte)(var31 << var12_13 * 8 >>> 56);
                        }
                        var11_12.init(2, (Key)v8.generateSecret(new DESKeySpec(v10)), new IvParameterSpec(new byte[8]));
                        var17_14 = new long[7];
                        var14_15 = 0;
                        var15_16 = "\u001c\u00f53\u00f8\n\u00c2y\u00ab\u00a5\u00b6\u00f8\\\u009c\u00f4do\u0088;)F\u00e8\u00a6\u000e\u0099\u0007W\u0089\u00c9\u001d\u00e5\u00991 \u008f\u00d0\u00aa\u00d1py~";
                        var16_17 = 40;
                        var13_18 = 0;
                        while (true) {
                            var18_19 = var15_16.substring(var13_18, var13_18 += 8).getBytes("ISO-8859-1");
                            v11 = var17_14;
                            v12 = var14_15++;
                            v13 = ((long)var18_19[0] & 255L) << 56 | ((long)var18_19[1] & 255L) << 48 | ((long)var18_19[2] & 255L) << 40 | ((long)var18_19[3] & 255L) << 32 | ((long)var18_19[4] & 255L) << 24 | ((long)var18_19[5] & 255L) << 16 | ((long)var18_19[6] & 255L) << 8 | (long)var18_19[7] & 255L;
                            v14 = -1;
                            break block25;
                            break;
                        }
lbl84:
                        // 1 sources

                        while (true) {
                            v11[v12] = v15;
                            if (var13_18 < var16_17) ** continue;
                            var15_16 = "\u00c2R\u008fz}\u0098\u00b7\u00b3M:\u00c0\u00bfz5\u00fb\u00df";
                            var16_17 = 16;
                            var13_18 = 0;
                            while (true) {
                                var18_19 = var15_16.substring(var13_18, var13_18 += 8).getBytes("ISO-8859-1");
                                v11 = var17_14;
                                v12 = var14_15++;
                                v13 = ((long)var18_19[0] & 255L) << 56 | ((long)var18_19[1] & 255L) << 48 | ((long)var18_19[2] & 255L) << 40 | ((long)var18_19[3] & 255L) << 32 | ((long)var18_19[4] & 255L) << 24 | ((long)var18_19[5] & 255L) << 16 | ((long)var18_19[6] & 255L) << 8 | (long)var18_19[7] & 255L;
                                v14 = 0;
                                break block25;
                                break;
                            }
                            break;
                        }
lbl97:
                        // 1 sources

                        while (true) {
                            v11[v12] = v15;
                            if (var13_18 < var16_17) ** continue;
                            break block26;
                            break;
                        }
                    }
                    var19_20 = v13;
                    var21_21 = var11_12.doFinal(new byte[]{(byte)(var19_20 >>> 56), (byte)(var19_20 >>> 48), (byte)(var19_20 >>> 40), (byte)(var19_20 >>> 32), (byte)(var19_20 >>> 24), (byte)(var19_20 >>> 16), (byte)(var19_20 >>> 8), (byte)var19_20});
                    v15 = ((long)var21_21[0] & 255L) << 56 | ((long)var21_21[1] & 255L) << 48 | ((long)var21_21[2] & 255L) << 40 | ((long)var21_21[3] & 255L) << 32 | ((long)var21_21[4] & 255L) << 24 | ((long)var21_21[5] & 255L) << 16 | ((long)var21_21[6] & 255L) << 8 | (long)var21_21[7] & 255L;
                    switch (v14) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl110:
                        // 1 sources

                        ** continue;
                    }
                }
                ClickGuiScreen.e = var17_14;
                ClickGuiScreen.f = new Integer[7];
                var1_22 = Cipher.getInstance("DES/CBC/NoPadding");
                v16 = SecretKeyFactory.getInstance("DES");
                v17 = new byte[8];
                v18 = v17;
                v17[0] = (byte)(var31 >>> 56);
                for (var2_23 = 1; var2_23 < 8; ++var2_23) {
                    v18 = v18;
                    v18[var2_23] = (byte)(var31 << var2_23 * 8 >>> 56);
                }
                var1_22.init(2, (Key)v16.generateSecret(new DESKeySpec(v18)), new IvParameterSpec(new byte[8]));
                var0_24 = new long[2];
                var4_25 = 0;
                var5_26 = "\u0002o\u0085\u001a\u00ac\"/\u008a\u00ef\u00a3\u00d5Z\u0006\u0012od";
                var6_27 = 16;
                var3_28 = 0;
                while (true) {
                    break block27;
                    break;
                }
lbl131:
                // 1 sources

                while (true) {
                    var0_24[v19] = ((long)var10_31[0] & 255L) << 56 | ((long)var10_31[1] & 255L) << 48 | ((long)var10_31[2] & 255L) << 40 | ((long)var10_31[3] & 255L) << 32 | ((long)var10_31[4] & 255L) << 24 | ((long)var10_31[5] & 255L) << 16 | ((long)var10_31[6] & 255L) << 8 | (long)var10_31[7] & 255L;
                    if (var3_28 < var6_27) ** continue;
                    break block28;
                    break;
                }
            }
            var7_29 = var5_26.substring(var3_28, var3_28 += 8).getBytes("ISO-8859-1");
            v19 = var4_25++;
            var8_30 = ((long)var7_29[0] & 255L) << 56 | ((long)var7_29[1] & 255L) << 48 | ((long)var7_29[2] & 255L) << 40 | ((long)var7_29[3] & 255L) << 32 | ((long)var7_29[4] & 255L) << 24 | ((long)var7_29[5] & 255L) << 16 | ((long)var7_29[6] & 255L) << 8 | (long)var7_29[7] & 255L;
            var10_31 = var1_22.doFinal(new byte[]{(byte)(var8_30 >>> 56), (byte)(var8_30 >>> 48), (byte)(var8_30 >>> 40), (byte)(var8_30 >>> 32), (byte)(var8_30 >>> 24), (byte)(var8_30 >>> 16), (byte)(var8_30 >>> 8), (byte)var8_30});
            ** while (true)
        }
        ClickGuiScreen.INSTANCE = new ClickGuiScreen();
        ClickGuiScreen.w = true;
        var42_33 = var40_32 = (Iterable)ModuleManager.INSTANCE.C();
        var43_34 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var40_32, (int)ClickGuiScreen.zkmBootstrap2("l", (int)27955, (long)(2897949821205325178L ^ var31))));
        var45_35 = 0;
        for (T var47_37 : var42_33) {
            if ((var48_38 = var45_35++) < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            var49_39 = (CategoryGroup)var47_37;
            var50_40 = var48_38;
            var52_41 = var43_34;
            var52_41.add(new CategoryPanel((char)var33_1, var49_39, var34_2, var50_40));
        }
        ClickGuiScreen.categoryPanels = (List)var43_34;
        ClickGuiScreen.openAnimation = new Animation(Easing.OUT_BACK, var0_24[1]);
        ClickGuiScreen.scaleAnimation = new Animation(Easing.IN_QUAD, var0_24[0]);
    }

    public static void zkmSetFlag(int n) {
        ClickGuiScreen.n = n;
    }

    public static int zkmGetFlag() {
        return n;
    }

    public static int zkmFlagCheck() {
        int n = ClickGuiScreen.zkmGetFlag();
        if (n == 0) {
            return 121;
        }
        return 0;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7DB;
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
                throw new RuntimeException("su/catlean/fi", exception);
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
            ClickGuiScreen.c[n2] = ClickGuiScreen.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ClickGuiScreen.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3E0D;
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
                throw new RuntimeException("su/catlean/fi", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ClickGuiScreen.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ClickGuiScreen.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/fi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ClickGuiScreen.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ClickGuiScreen.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

