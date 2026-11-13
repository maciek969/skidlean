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
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.config.ConfigIO;
import su.catlean.config.ConfigStore;
import su.catlean.config.ConfigType;
import su.catlean.config.HudConfig;
import su.catlean.config.ProfileConfig;
import su.catlean.gui.ConfigPanel$WhenMappings;
import su.catlean.gui.GuiElement;
import su.catlean.gui.PanelType;
import su.catlean.gui.SidePanel;
import su.catlean.module.Module;
import su.catlean.render.Gradient2D;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.sound.Sounds;
import su.catlean.ui.hud.LayoutEntry;
import su.catlean.ui.hud.PanelState;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class ConfigPanel
extends SidePanel {
    @NotNull
    public static final ConfigPanel INSTANCE;
    @NotNull
    private static final List entries;
    @NotNull
    private static ConfigType configType;
    @Nullable
    private static LayoutEntry layoutEntry;
    @NotNull
    private static final Timer timer;
    @Nullable
    private static PanelState panelState;
    @NotNull
    private static final RenderTarget J;
    @NotNull
    private static final RenderTarget v;
    @NotNull
    private static final Animation animation;
    private static final long b;
    private static final String[] c;
    private static final String[] e;
    private static final Map i;
    private static final long[] m;
    private static final Integer[] n;
    private static final Map s;

    private ConfigPanel() {
        super("config-panel", 0.0f, 0.0f, 0.0f, 0.0f, null);
    }

    @Nullable
    public final PanelState getPanelState() {
        return panelState;
    }

    public final void setPanelState(@Nullable PanelState panelState) {
        ConfigPanel.panelState = panelState;
    }

    @Override
    public void refresh() {
        entries.clear();
        for (Object object : ConfigStore.INSTANCE.getProfileConfigFile().getData()) {
            entries.add(new LayoutEntry(((ProfileConfig)object).getId(), 0.0f, 0.0f, ConfigType.MODULES, null));
        }
        for (Object object : ConfigStore.INSTANCE.getHudConfigFile().K()) {
            entries.add(new LayoutEntry(((HudConfig)object).getId(), 0.0f, 0.0f, ConfigType.WIDGETS, null));
        }
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, long l) {
        block14: {
            long l2 = l;
            long l3 = l2 ^ 0x2410053AA064L;
            int n3 = (int)(l3 >>> 32);
            int n4 = (int)(l3 << 32 >>> 48);
            int n5 = (int)(l3 << 48 >>> 48);
            long l4 = l2 ^ 0x1421E94221EBL;
            int n6 = (int)(l4 >>> 48);
            int n7 = (int)(l4 << 16 >>> 48);
            int n8 = (int)(l4 << 32 >>> 32);
            long l5 = l2 ^ 0x18B8FB452344L;
            long l6 = l2 ^ 0L;
            long l7 = l2 ^ 0x590C9F84AB44L;
            long l8 = l2 ^ 0x75FB1B023A74L;
            long l9 = l2 ^ 0x6B09519A419EL;
            long l10 = l2 ^ 0x10E1CFA640FCL;
            Intrinsics.checkNotNullParameter(drawContext, "context");
            super.render(drawContext, n, n2, l6);
            animation.animate(this.getGuiElement() != null ? 0.5f : 0.0f);
            this.renderHeader(l8, drawContext);
            layoutEntry = null;
            int n9 = (int)Math.floor(this.getWidth() / 46.0f);
            float f = 0.0f;
            float f2 = 15.0f;
            float f3 = Math.max(3.0f, (this.getWidth() - 6.0f - (float)n9 * 40.0f) / (float)n9);
            for (LayoutEntry layoutEntry : entries) {
                Object object;
                Color color;
                Color color2;
                if (configType != layoutEntry.getType()) continue;
                float f4 = f + this.getX() + 4.0f;
                float f5 = f + this.getX() + 4.0f + 40.0f;
                float f6 = n;
                boolean bl = f4 <= f6 ? f6 <= f5 : false;
                if (bl) {
                    f4 = f2 + this.getY() + 17.0f;
                    f5 = f2 + this.getY() + 17.0f + 40.0f;
                    f6 = n2;
                    boolean bl2 = f4 <= f6 ? f6 <= f5 : false;
                    if (bl2) {
                        ConfigPanel.layoutEntry = layoutEntry;
                    }
                }
                Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                float f7 = f + this.getX() + 4.0f;
                float f8 = f2 + this.getY() + 17.0f;
                float f9 = Intrinsics.areEqual(ConfigPanel.layoutEntry, layoutEntry) ? 2.0f : 0.0f;
                Intrinsics.checkNotNullExpressionValue(Theme.INSTANCE.getWidgetBlurColor().darker(), "darker(...)");
                Intrinsics.checkNotNullExpressionValue(Theme.INSTANCE.getWidgetBlurColor().darker(), "darker(...)");
                Object var37_22 = null;
                int n10 = 14336;
                float f10 = 0.0f;
                float f11 = 0.0f;
                float f12 = 0.0f;
                float f13 = 1.0f;
                float f14 = 0.0f;
                float f15 = f9;
                float f16 = 4.0f;
                float f17 = 40.0f;
                float f18 = 40.0f;
                float f19 = f8;
                float f20 = f7;
                Shapes2D.drawShape$default(matrix3x2fStack, (char)n6, f20, f19, f18, f17, f16, f15, f14, f13, color2, color, f12, (short)n7, f11, n8, f10, n10, var37_22);
                if (layoutEntry.getName().length() > 10) {
                    String string = layoutEntry.getName().substring(0, 7);
                    Intrinsics.checkNotNullExpressionValue(string, "substring(...)");
                    object = string + "...";
                } else {
                    object = layoutEntry.getName();
                }
                String string = object;
                Fonts.getSF_M().drawStringCentered(drawContext, string, f + this.getX() + 4.0f + 20.0f, f2 + this.getY() + 17.0f + 20.0f + 12.0f, Theme.INSTANCE.getWidgetBlurColor(), l9);
                RenderTarget renderTarget = switch (ConfigPanel$WhenMappings.i[layoutEntry.getType().ordinal()]) {
                    case 1 -> J;
                    case 2 -> v;
                    default -> throw new NoWhenBranchMatchedException();
                };
                boolean bl3 = false;
                if (layoutEntry.getType() == ConfigType.MODULES) {
                    bl3 = Intrinsics.areEqual(layoutEntry.getName(), ConfigStore.INSTANCE.getProfileConfigFile().getCurrent().getId());
                }
                if (layoutEntry.getType() == ConfigType.WIDGETS) {
                    bl3 = Intrinsics.areEqual(layoutEntry.getName(), ConfigStore.INSTANCE.getHudConfigFile().getCurrent().getId());
                }
                if (bl3) {
                    Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                    RenderTarget.drawRoundedText$default(renderTarget, (Matrix3x2f)matrix3x2fStack2, (double)(f + this.getX()) + 10.0, (double)(f2 + this.getY()) + 18.0, 0.0f, 0.0f, Theme.INSTANCE.getGradientColor(0), Theme.INSTANCE.getGradientColor(180), 0.0f, 152, l10, null);
                } else {
                    Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
                    RenderTarget.drawRoundedText$default(renderTarget, (Matrix3x2f)matrix3x2fStack3, (double)(f + this.getX()) + 10.0, (double)(f2 + this.getY()) + 18.0, 0.0f, 0.0f, null, null, 0.0f, 248, l10, null);
                }
                if (!((f += 40.0f + f3) + 40.0f + f3 > this.getWidth())) continue;
                f = 0.0f;
                f2 += 43.0f;
            }
            float f21 = this.getX();
            float f22 = this.getY();
            float f23 = this.getWidth();
            float f24 = this.getHeight();
            Color color = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(color, "BLACK");
            float f25 = animation.getValue();
            Color color3 = RenderUtil.INSTANCE.withAlpha(color, f25);
            Color color4 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(color4, "BLACK");
            float f26 = animation.getValue();
            Color color5 = RenderUtil.INSTANCE.withAlpha(color4, f26);
            Color color6 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(color6, "BLACK");
            float f27 = animation.getValue();
            Color color7 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(color7, "BLACK");
            float f28 = animation.getValue();
            Color color8 = RenderUtil.INSTANCE.withAlpha(color7, f28);
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Gradient2D.E(f21, f22, f23, f24, 5.0f, color3, color5, l7, RenderUtil.INSTANCE.withAlpha(color6, f27), color8, matrix3x2fStack, 0.0f, null, null);
            GuiElement guiElement = this.getGuiElement();
            if (guiElement != null) {
                guiElement.render(drawContext, this.getX() + this.getWidth() / 2.0f - 60.0f, this.getY() + this.getHeight() / 2.0f - 35.0f, l5, n, n2);
            }
            PanelState panelState = ConfigPanel.panelState;
            if (panelState == null) break block14;
            panelState.render(n3, drawContext, n, n2, n4, n5);
        }
    }

    private final void renderHeader(long l, DrawContext drawContext) {
        Color color;
        Color color2;
        long l2 = l = b ^ l;
        long l3 = l2 ^ 0x4D20808F73B8L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x3208385713CDL;
        long l5 = l2 ^ 0x24E7C004CFAFL;
        float f = 16.0f;
        float f2 = 3.5f;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        float f3 = this.getY() + f;
        float f4 = this.getWidth() - 7.0f;
        Color color3 = Theme.INSTANCE.getWidgetBlurColor().darker();
        Intrinsics.checkNotNullExpressionValue(color3, "darker(...)");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n, this.getX() + f2, f3, f4, 13.0f, 2.0f, 0.0f, 0.0f, 1.0f, color3, Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Object object = new Color[]{new Color(0x44FFFFFF, true)};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, this.getX() + (this.getWidth() - 7.0f) * 0.5f + f2, this.getY() + 2.0f + f, 0.5f, 9.0f, (Color[])object, l5);
        FontRenderer fontRenderer = Fonts.getSF_M();
        float f5 = this.getX() + (this.getWidth() - 7.0f) * 0.25f + f2;
        float f6 = this.getY() + 5.0f + f;
        if (configType != ConfigType.MODULES) {
            Color color4 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color4, "WHITE");
            color2 = RenderUtil.INSTANCE.withAlpha(color4, 0.5f);
        } else {
            color2 = Color.WHITE;
        }
        object = color2;
        Intrinsics.checkNotNull(object);
        fontRenderer.drawStringCentered(drawContext, "Modules", f5, f6, (Color)object, l4);
        FontRenderer fontRenderer2 = Fonts.getSF_M();
        float f7 = this.getX() + (this.getWidth() - 7.0f) * 0.75f + f2;
        float f8 = this.getY() + 5.0f + f;
        if (configType != ConfigType.WIDGETS) {
            Color color5 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color5, "WHITE");
            color = RenderUtil.INSTANCE.withAlpha(color5, 0.5f);
        } else {
            color = Color.WHITE;
        }
        object = color;
        Intrinsics.checkNotNull(object);
        fontRenderer2.drawStringCentered(drawContext, "Hud", f7, f8, (Color)object, l4);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void mouseClicked(float var1_1, float var2_2, short var3_3, int var4_4, int var5_5, char var6_6) {
        block12: {
            block11: {
                block9: {
                    block10: {
                        v0 = var7_7 = (long)var3_3 << 48 | (long)var5_5 << 32 >>> 16 | (long)var6_6 << 48 >>> 48;
                        v1 = v0 ^ 0L;
                        var9_8 = (int)(v1 >>> 48);
                        var10_9 = (int)(v1 << 16 >>> 32);
                        var11_10 = (int)(v1 << 48 >>> 48);
                        var12_11 = v0 ^ 38836403100948L;
                        v2 = v0 ^ 76359761823462L;
                        var16_12 = (int)(v2 >>> 32);
                        var17_13 = (int)(v2 << 32 >>> 48);
                        var18_14 = (int)(v2 << 48 >>> 48);
                        v3 = v0 ^ 132701681991706L;
                        var25_15 = (int)(v3 >>> 32);
                        var26_16 = (int)(v3 << 32 >>> 48);
                        var27_17 = (int)(v3 << 48 >>> 48);
                        new Module[0];
                        super.mouseClicked(var1_1, var2_2, (short)var9_8, var4_4, var10_9, (char)var11_10);
                        v4 = this.getGuiElement();
                        v5 = v4 != null ? v4.mouseClicked(var12_11, var1_1, var2_2) : false;
                        if (v5) {
                            return;
                        }
                        v6 = ConfigPanel.panelState;
                        if (v6 != null) {
                            v6.getHoveredIndex(var16_12, var1_1, var2_2, (short)var17_13, (short)var18_14);
                        }
                        if (var4_4 != 1) break block9;
                        if (ConfigPanel.layoutEntry == null) break block10;
                        v7 = ConfigPanel.layoutEntry;
                        Intrinsics.checkNotNull(v7);
                        v8 = v7.getName();
                        v9 = ConfigPanel.layoutEntry;
                        Intrinsics.checkNotNull(v9);
                        v10 = new PanelState(PanelType.Config, var1_1, var2_2, v8, v9.getType());
                        break block11;
                    }
                    var29_18 = this.getX();
                    v11 = var1_1 <= this.getX() + this.getWidth() ? var29_18 <= var1_1 : false;
                    if (!v11) ** GOTO lbl-1000
                    var29_18 = this.getY();
                    v12 = var2_2 <= this.getY() + this.getHeight() ? var29_18 <= var2_2 : false;
                    if (v12) {
                        v10 = new PanelState(PanelType.Window, var1_1, var2_2, null, ConfigPanel.configType, null);
                    } else lbl-1000:
                    // 2 sources

                    {
                        v10 = null;
                    }
                    break block11;
                }
                v10 = ConfigPanel.panelState = null;
            }
            if (var4_4 == 0 && ConfigPanel.layoutEntry != null) {
                if (!ConfigPanel.timer.hasPassed(400)) {
                    v13 = ConfigPanel.layoutEntry;
                    Intrinsics.checkNotNull(v13);
                    v14 = v13.getName();
                    v15 = ConfigPanel.layoutEntry;
                    Intrinsics.checkNotNull(v15);
                    ConfigIO.load(v14, v15.getType(), var25_15, (short)var26_16, var27_17);
                    Sounds.INSTANCE.getConfigLoad().play();
                } else {
                    ConfigPanel.timer.reset();
                }
                return;
            }
            var29_18 = this.getY() + 16.0f;
            v16 = var2_2 <= this.getY() + 29.0f ? var29_18 <= var2_2 : false;
            if (!v16) break block12;
            var29_18 = this.getX() + 3.5f;
            v17 = var1_1 <= this.getX() + 3.5f + (this.getWidth() - 7.0f) * 0.5f ? var29_18 <= var1_1 : false;
            if (v17) {
                ConfigPanel.configType = ConfigType.MODULES;
            }
            var29_18 = this.getX() + 3.5f + (this.getWidth() - 7.0f) * 0.5f;
            v18 = var1_1 <= this.getX() + 3.5f + (this.getWidth() - 7.0f) ? var29_18 <= var1_1 : false;
            if (v18) {
                ConfigPanel.configType = ConfigType.WIDGETS;
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            ConfigPanel.b = ZkmKeys.a(425705382796610816L, -128053098784300283L, MethodHandles.lookup().lookupClass()).a(180578683749118L);
                            var27 = ConfigPanel.b ^ 116974108974201L;
                            ConfigPanel.i = new HashMap<K, V>(13);
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var27 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var27 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[16];
                            var23_4 = 0;
                            var22_5 = ".\u0004\u00e6*Q\u00b7\u00de$\u0004\u000e\u00a1\u00e6\u00ea\u00a8p\"(\u00ecd\u00cd~\u00e6\u0088\u009f\u008dE\u0017\u00a6\u00f4\u00f0b\u00e4\u008d\u00e6\u00cb\u008a\u00f7\u009f\u00dc\u00ea\u008185\u008c\u00ef\u001dM\u0081<\u00a8\u00a6P\u0082\u0007\u00c8N\u00a6 \u00c0(\u0011\u00ae4^S\n\u0083^\u00edd*K\u00a9%F\u00db\u00f6z\u009e!\u0093-\u00da-t~\u00b0l\u001a\u00f0\u0010\rU\u0006\u0083\u0097\f8f\r\u00a0\u0086\u00d7\u00fcq\u00cdW\u0018\u00a1}\u00f7R\u0015\u0000\u0018\u0019qI\u00b4\u00b8cek@\u00c0\u00eemF{\u0018*\u00dc\u0010\u00f6\u001c\u0010\u00c7\u00c2\u00b6\u0003{\u000fMo\u00ceG2\u0016` \u00bf\u00960JF\u0099\u0096\u0000\u0000b?P`\u00c9\u00e4p\u00dd\u00f0\u0000A\u00df\u00f7\u00f7\u00ef[e{)\u00ea\u0088\u00afq \u0019\u001d4W.\bU\u000f>\u00c5\u008f\u0010pV\u0007\u000b\u00d9\u0090&\u00c3'\u00c4M\u00c1\u008f\u0099\u00b3\u00a8\u00e7\u00fc=\u00c7\u0010ye\u00a8g\u00e8\u00c1)Ud\u008a\b\u00fa2z\u007f\u0006\u0010\u00a0\u0080\u001d\u00da\u00a2\u00983\u00da\u00d2\u00b5\u001b1B\u00a2\u0091T \u008f;\u0005\u000b>\r\u00a5\u0019h\u000f\u0081\u00c51\u00f9RQv\u00e2\u00cd7h\u00e5\u00beH\u000e\u00a4\u00b3P\u00ea\u00ad\u00e3c\u0010nQpN&\u00e2l\u008e\u00c8@\u00a5Z\u0089\u00d4\"\u0098(g\u0010\u00f4\u00d5\u00c4G\u00d50{x\u00b4\u007f')\u00f7`%\u0081\u00dc3 Q\u00f3\u001f\u00ed\u00das\u00c8)L\u0003\u00bc\u00f24\u00f09\u00c8q\u00a7\u0088\u0010\u0099\u00b6\u00a1r\u00c9Lv\u00d8\u0092\u0090\u00b3\u00da3\u00a2|\u00b6";
                            var24_6 = 357;
                            var21_7 = 16;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = ConfigPanel.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "2e\t\u00f9\u00e4\u00ef\u00fd(l\u00b2\u0081\u009a\u0005\u0013t2\u0018V'\u001c\u00fc[eV.#\u0099o\u00eb@\u00e1\u0000\u00b1qV\u0083[t0\u0086\u00e3";
                                var24_6 = 41;
                                var21_7 = 16;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = ConfigPanel.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    ConfigPanel.c = var25_3;
                    ConfigPanel.e = new String[16];
                    ConfigPanel.s = new HashMap<K, V>(13);
                    var7_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var27 >>> 56);
                    for (var8_11 = 1; var8_11 < 8; ++var8_11) {
                        v9 = v9;
                        v9[var8_11] = (byte)(var27 << var8_11 * 8 >>> 56);
                    }
                    var7_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var13_12 = new long[20];
                    var10_13 = 0;
                    var11_14 = "\u00d97s\u001cT Y~{\u00fd\u00aa[\u00f5/\u00db\u00b3\u00d6\u00933\u0003\u0097\u00da\u00a8\u00d1\u00e2\u0019`\u00029Y\u00f4\u00db\u00bd\u00d9\u0004]\u00a4\u00caa\u0018\u00fa\u00ddLa\u00fak\u0096\u00e28\u0098\u009eK\u00e65\u007f\u00e0iuF\u00a5\u00af~%c\u00a0\u00eb\u0088}\u0007\u0099\u00f0)\u001e\u00b8\u00e0X(\u0082\u00ecc\u00c4g\u0003\u00f2\u001a\u0084O\f\u00d01\u001b\u00d9g\u00ebs+\u00ff%\u00f7Cv\u00ddr~\u00d5M\u0093\u001d-wf\u00d6\u00d9O\b0\u001c\u00e7\u00fb\u00a5\u00b4\u008fC\u00b0\u00c0\u0003G~\u00b0\u0096\u0015\u000e~\u00fe!z\u00d7\u00cb;\u00998\u008d\u00ec5";
                    var12_15 = 144;
                    var9_16 = 0;
                    while (true) {
                        var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                        v10 = var13_12;
                        v11 = var10_13++;
                        v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        var11_14 = "\u0086'\u009cw,!\u0097\u0089\u00c5\u00dbZ({I\r\u00c4";
                        var12_15 = 16;
                        var9_16 = 0;
                        while (true) {
                            var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                            v10 = var13_12;
                            v11 = var10_13++;
                            v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl91:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_18 = v12;
                var17_19 = var7_10.doFinal(new byte[]{(byte)(var15_18 >>> 56), (byte)(var15_18 >>> 48), (byte)(var15_18 >>> 40), (byte)(var15_18 >>> 32), (byte)(var15_18 >>> 24), (byte)(var15_18 >>> 16), (byte)(var15_18 >>> 8), (byte)var15_18});
                v14 = ((long)var17_19[0] & 255L) << 56 | ((long)var17_19[1] & 255L) << 48 | ((long)var17_19[2] & 255L) << 40 | ((long)var17_19[3] & 255L) << 32 | ((long)var17_19[4] & 255L) << 24 | ((long)var17_19[5] & 255L) << 16 | ((long)var17_19[6] & 255L) << 8 | (long)var17_19[7] & 255L;
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
            ConfigPanel.m = var13_12;
            ConfigPanel.n = new Integer[20];
            var2_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var27 >>> 56);
            for (var3_21 = 1; var3_21 < 8; ++var3_21) {
                v17 = v17;
                v17[var3_21] = (byte)(var27 << var3_21 * 8 >>> 56);
            }
            break block24;
lbl118:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_23 = var2_20.doFinal(new byte[]{-126, 36, 12, 2, -105, -6, -83, -15});
        ** while (true)
        var0_22 = ((long)var6_23[0] & 255L) << 56 | ((long)var6_23[1] & 255L) << 48 | ((long)var6_23[2] & 255L) << 40 | ((long)var6_23[3] & 255L) << 32 | ((long)var6_23[4] & 255L) << 24 | ((long)var6_23[5] & 255L) << 16 | ((long)var6_23[6] & 255L) << 8 | (long)var6_23[7] & 255L;
        ConfigPanel.INSTANCE = new ConfigPanel();
        ConfigPanel.entries = new ArrayList<E>();
        ConfigPanel.configType = ConfigType.MODULES;
        ConfigPanel.timer = new Timer();
        ConfigPanel.J = new RenderTarget("panels/modules-config", 28, 28);
        ConfigPanel.v = new RenderTarget("panels/hud-config", 28, 28);
        ConfigPanel.animation = new Animation(Easing.OUT_QUINT, var0_22);
        ConfigPanel.INSTANCE.refresh();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3BE9;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])i.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ir", exception);
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
            ConfigPanel.e[n2] = ConfigPanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ConfigPanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ir" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x237F;
        if (ConfigPanel.n[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = m[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])s.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    s.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ir", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ConfigPanel.n[n2] = n3;
        }
        return ConfigPanel.n[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ConfigPanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ir" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ConfigPanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ConfigPanel.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

