/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Matrix3x2fc
 *  org.lwjgl.glfw.GLFW
 */
package su.catlean.ui.hud;

import java.awt.Color;
import java.lang.invoke.CallSite;
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
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Matrix3x2fc;
import org.lwjgl.glfw.GLFW;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.module.Toggleable;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.draw.ImageCommand;
import su.catlean.render.draw.ShapeCommand2;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.PositionSetting;
import su.catlean.setting.Setting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.hud.CoordWidget;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudManager;
import su.catlean.ui.hud.HudPosition;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public abstract class HudElement
implements SettingContainer,
Toggleable {
    static final KProperty[] delegatedProperties;
    @NotNull
    private final String id;
    @NotNull
    private final List A;
    @NotNull
    private final List M;
    @NotNull
    private final RenderTarget checkboxTexture;
    @NotNull
    private final BooleanSetting enabled$delegate;
    @NotNull
    private final PositionSetting pos$delegate;
    @NotNull
    private RenderTarget renderTarget;
    private boolean w;
    private float C;
    private float dragOffsetY;
    private float T;
    private float X;
    private boolean settingsOpen;
    private float height;
    private float width;
    @NotNull
    private final String t;
    private static String W;
    private static final long u;
    private static final String[] Z;
    private static final String[] ab;
    private static final Map bb;
    private static final long[] cb;
    private static final Integer[] db;
    private static final Map eb;

    public HudElement(char c, @NotNull String string, char c2) {
        block5: {
            StringBuilder stringBuilder;
            int n = 0;
            long l = ((long)c << 48 | (long)n << 32 >>> 16 | (long)c2 << 48 >>> 48) ^ u;
            Intrinsics.checkNotNullParameter(string, "id");
            this.id = string;
            this.A = new ArrayList();
            this.M = new ArrayList();
            this.checkboxTexture = new RenderTarget("check", (int)HudElement.zkmBootstrap2("b", (int)32164, (long)(0xAEB0CDCFDE9BACBL ^ l)), (int)HudElement.zkmBootstrap2("b", (int)6094, (long)(0x73487D1F18D450AFL ^ l)));
            this.enabled$delegate = SettingFactory.booleanSetting$default(this, "enabled", false, null, null, (int)HudElement.zkmBootstrap2("b", (int)6094, (long)(0x73487D1F18D450AFL ^ l)), null);
            this.pos$delegate = SettingFactory.positionSetting$default(this, "position", new HudPosition(0.5f, 0.5f), null, null, (int)HudElement.zkmBootstrap2("b", (int)6094, (long)(0x73487D1F18D450AFL ^ l)), null);
            this.renderTarget = new RenderTarget(this.id, (int)HudElement.zkmBootstrap2("b", (int)10755, (long)(0x461803A7CA91ED68L ^ l)), (int)HudElement.zkmBootstrap2("b", (int)19208, (long)(0x5C74A821192D0C6BL ^ l)));
            StringBuilder stringBuilder2 = stringBuilder = new StringBuilder();
            HudElement hudElement = this;
            Object object = new String[]{"-"};
            object = StringsKt.split$default((CharSequence)this.id, (String[])object, false, 0, (int)HudElement.zkmBootstrap2("b", (int)31240, (long)(0x2BB1C264955FBD6DL ^ l)), null);
            Iterator iterator2 = object.iterator();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                String string2 = (String)t;
                if (c >= '\u0000') {
                    String string3;
                    StringBuilder stringBuilder3 = stringBuilder2;
                    String string4 = string2;
                    int n2 = ((CharSequence)string4).length();
                    if (c2 > '\u0000') {
                        n2 = n2 > 0 ? 1 : 0;
                    }
                    if (n2 != 0) {
                        char c3 = string4.charAt(0);
                        StringBuilder stringBuilder4 = new StringBuilder();
                        StringBuilder stringBuilder5 = stringBuilder3;
                        String string5 = String.valueOf(c3);
                        Intrinsics.checkNotNull(string5, "null cannot be cast to non-null type java.lang.String");
                        String string6 = string5.toUpperCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(string6, "toUpperCase(...)");
                        CharSequence charSequence = string6;
                        stringBuilder3 = stringBuilder5;
                        StringBuilder stringBuilder6 = stringBuilder4.append((Object)charSequence);
                        String string7 = string4;
                        int n3 = 1;
                        String string8 = string7.substring(n3);
                        Intrinsics.checkNotNullExpressionValue(string8, "substring(...)");
                        string3 = stringBuilder6.append(string8).toString();
                    } else {
                        string3 = string4;
                    }
                    stringBuilder3.append(string3);
                    stringBuilder2.append((char)HudElement.zkmBootstrap2("b", (int)11271, (long)(0x567B7140251C6B65L ^ l)));
                    StringsKt.trim(stringBuilder2);
                    if (c2 > '\u0000') continue;
                    break;
                }
                break block5;
            }
            hudElement.t = stringBuilder.toString();
        }
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Override
    @NotNull
    public List getSettings() {
        return this.A;
    }

    @Override
    @NotNull
    public List u() {
        return this.M;
    }

    public final boolean getEnabled() {
        return (Boolean)this.enabled$delegate.getValue(this, delegatedProperties[0]);
    }

    public final void setEnabled(boolean bl) {
        this.enabled$delegate.setValue(this, delegatedProperties[0], bl);
    }

    @NotNull
    public final HudPosition getPos() {
        return (HudPosition)this.pos$delegate.getValue(this, delegatedProperties[1]);
    }

    public final void setPos(@NotNull HudPosition hudPosition) {
        long l = 0L;
        l = u ^ l;
        Intrinsics.checkNotNullParameter(hudPosition, (String)((Object)HudElement.zkmBootstrap("a", (int)22747, (long)(0x1225E790B5392F29L ^ l))));
        this.pos$delegate.setValue(this, delegatedProperties[1], hudPosition);
    }

    @NotNull
    public final RenderTarget getRenderTarget() {
        return this.renderTarget;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    @NotNull
    public final String A() {
        return this.t;
    }

    @Override
    public void toggle(long l) {
        long l2 = l;
        long l3 = l2 ^ 0x3B8B5F891995L;
        long l4 = l2 ^ 0x3D5CF9CB0B0CL;
        int n = (int)(l4 >>> 56);
        long l5 = l4 << 8 >>> 8;
        if (this.getEnabled()) {
            this.disable(l3);
        } else {
            this.enable((byte)n, l5);
        }
    }

    @Override
    public void enable(byte by, long l) {
        this.setEnabled(true);
    }

    @Override
    public void disable(long l) {
        this.setEnabled(false);
    }

    public abstract void render(long var1, @NotNull DrawContext var3);

    public void renderOverlay(@NotNull DrawContext drawContext, long l) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    public void tick() {
    }

    public final void renderWithSnapping(long l, @NotNull DrawContext drawContext) {
        block22: {
            float f;
            float f2;
            float f3;
            float f4;
            long l2 = l = u ^ l;
            long l3 = l2 ^ 0x1292AFBD4AC1L;
            long l4 = l2 ^ 0x729C36A5FD0CL;
            long l5 = l4 >>> 32;
            int n = (int)(l4 << 32 >>> 32);
            long l6 = l2 ^ 0x731F03DEE60EL;
            int n2 = (int)(l6 >>> 48);
            int n3 = (int)(l6 << 16 >>> 48);
            int n4 = (int)(l6 << 32 >>> 32);
            long l7 = l2 ^ 0x1DA213955B8FL;
            long l8 = l2 ^ 0xFFAEAA1EA86L;
            long l9 = l2 ^ 0x77DF253A8719L;
            String string = HudElement.zkmGetFlag();
            Intrinsics.checkNotNullParameter(drawContext, "context");
            String string2 = string;
            this.render(l8, drawContext);
            for (HudElement hudElement : HudManager.INSTANCE.getElements()) {
                if (Intrinsics.areEqual(hudElement, this) || !hudElement.getEnabled()) continue;
                f4 = hudElement.getX();
                f3 = hudElement.width + hudElement.getX();
                f2 = this.getX();
                boolean bl = f4 <= f2 ? f2 <= f3 : false;
                if (bl) {
                    f4 = hudElement.getY();
                    f3 = hudElement.getY() + hudElement.height;
                    f2 = this.getY();
                    boolean bl2 = f4 <= f2 ? f2 <= f3 : false;
                    if (bl2) {
                        f4 = (hudElement.getX() + hudElement.width + 2.0f) / (float)this.getScreenWidth();
                        f3 = (hudElement.getY() + hudElement.height + 2.0f) / (float)this.getScreenHeight();
                        f2 = Math.min(5.0f / (float)this.getScreenHeight(), 1.0f - this.height / (float)this.getScreenHeight());
                        f = Math.max(5.0f / (float)this.getScreenHeight(), 1.0f - this.height / (float)this.getScreenHeight());
                        if (!(hudElement.getX() == this.getX()) && hudElement.getY() == this.getY()) {
                            this.getPos().setX(RangesKt.coerceIn(f4, 5.0f / (float)this.getScreenWidth(), 1.0f));
                        }
                        if (!(hudElement.getY() == this.getY()) && (hudElement.getX() == this.getX() || hudElement.getX() + hudElement.width == this.getX() + this.width)) {
                            this.getPos().setY(RangesKt.coerceIn(f3, f2, f));
                        }
                    }
                }
                if (hudElement.getY() == this.getY() && Math.abs(hudElement.getX() + hudElement.width - this.getX()) < 10.0f) {
                    f4 = (hudElement.getX() + hudElement.width + 2.0f) / (float)this.getScreenWidth();
                    this.getPos().setX(RangesKt.coerceIn(f4, 5.0f / (float)this.getScreenWidth(), 1.0f));
                }
                if (!(hudElement.getX() == this.getX()) && !(hudElement.getX() + hudElement.width == this.getX() + this.width) || !(Math.abs(hudElement.getY() + hudElement.height - this.getY()) < 10.0f)) continue;
                f4 = (hudElement.getY() + hudElement.height + 2.0f) / (float)this.getScreenHeight();
                f3 = Math.min(5.0f / (float)this.getScreenHeight(), 1.0f - this.height / (float)this.getScreenHeight());
                f2 = Math.max(5.0f / (float)this.getScreenHeight(), 1.0f - this.height / (float)this.getScreenHeight());
                this.getPos().setY(RangesKt.coerceIn(f4, f3, f2));
            }
            if (this.getX() + this.width > (float)Globals.getMc().getWindow().getScaledWidth() - 5.0f || Math.abs(this.getX() + this.width - (float)Globals.getMc().getWindow().getScaledWidth() - 5.0f) < 60.0f) {
                float f5 = ((float)Globals.getMc().getWindow().getScaledWidth() - this.width - 5.0f) / (float)this.getScreenWidth();
                this.getPos().setX(RangesKt.coerceIn(f5, 5.0f / (float)this.getScreenWidth(), 1.0f));
            }
            if (Globals.getMc().currentScreen instanceof ChatScreen || Globals.getMc().currentScreen instanceof HudEditorScreen) {
                float f6;
                if (this.w) {
                    float f7 = (this.getMouseX() - this.C) / (float)this.getScreenWidth();
                    float f8 = (this.getMouseY() - this.dragOffsetY) / (float)this.getScreenHeight();
                    this.getPos().setX(RangesKt.coerceIn(f7, 5.0f / (float)this.getScreenWidth(), 1.0f - (this.width + 5.0f) / (float)this.getScreenWidth()));
                    f4 = Math.min(5.0f / (float)this.getScreenHeight(), 1.0f - this.height / (float)this.getScreenHeight());
                    f3 = Math.max(5.0f / (float)this.getScreenHeight(), 1.0f - this.height / (float)this.getScreenHeight());
                    this.getPos().setY(RangesKt.coerceIn(f8, f4, f3));
                    f2 = 0.0f;
                    f = 0.0f;
                    f6 = 0.0f;
                    for (Object object2 : HudManager.INSTANCE.getElements()) {
                        if (Intrinsics.areEqual(object2, this) || !((HudElement)object2).getEnabled()) continue;
                        float f9 = this.getX() + this.width;
                        float f10 = ((HudElement)object2).getX() + ((HudElement)object2).width;
                        if (this.isNear(f10, f9)) {
                            f2 = ((HudElement)object2).getX() + ((HudElement)object2).width - this.width;
                            f = ((HudElement)object2).getX() + ((HudElement)object2).width;
                        } else {
                            float f11 = this.getX();
                            float f12 = ((HudElement)object2).getX();
                            if (this.isNear(f12, f11)) {
                                f = f2 = ((HudElement)object2).getX();
                            }
                        }
                        float f13 = this.getY();
                        float f14 = ((HudElement)object2).getY();
                        if (this.isNear(f14, f13)) {
                            f6 = ((HudElement)object2).getY();
                        }
                        if (!(f == 0.0f)) {
                            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                            float f15 = (float)this.getScreenHeight() - 5.0f;
                            Color color = Color.WHITE;
                            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
                            RenderUtil.drawGradientRect$default(l5, RenderUtil.INSTANCE, matrix3x2fStack, f, 5.0f, n, f, f15, color, null, 64, null);
                            this.getPos().setX(f2 / (float)this.getScreenWidth());
                        }
                        if (f6 == 0.0f) continue;
                        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                        Color color = Color.WHITE;
                        Intrinsics.checkNotNullExpressionValue(color, "WHITE");
                        RenderUtil.drawGradientRect$default(l5, RenderUtil.INSTANCE, matrix3x2fStack, 5.0f, f6, n, (float)this.getScreenWidth() - 5.0f, f6, color, null, 64, null);
                        this.getPos().setY(f6 / (float)this.getScreenHeight());
                    }
                }
                if (this.settingsOpen) {
                    Object object;
                    Float f16;
                    Iterable iterable;
                    float f17 = 0.0f;
                    Iterable iterable2 = iterable = (Iterable)this.getSettings();
                    Iterator iterator2 = new ArrayList();
                    for (Object object2 : iterable2) {
                        Setting setting2 = (Setting)object2;
                        if (!(!Intrinsics.areEqual(setting2.getId(), "enabled") && !Intrinsics.areEqual(setting2.getId(), "position"))) continue;
                        iterator2.add(object2);
                    }
                    List list = (List)((Object)iterator2);
                    if (!(iterator2 = ((Iterable)list).iterator()).hasNext()) {
                        f16 = null;
                    } else {
                        Setting setting3 = (Setting)iterator2.next();
                        f6 = Fonts.getSF_M3().getWidth(l7, setting3.Q());
                        while (iterator2.hasNext()) {
                            object = (Setting)iterator2.next();
                            float f18 = Fonts.getSF_M3().getWidth(l7, ((Setting)object).Q());
                            f6 = Math.max(f6, f18);
                        }
                        f16 = Float.valueOf(f6);
                    }
                    float f19 = (f16 != null ? f16.floatValue() : 0.0f) + 23.0f;
                    Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                    Shapes2D.drawShape$default(matrix3x2fStack, (char)n2, this.T, this.X, f19, 14.0f * (float)list.size() + 6.0f, 3.0f, 1.5f, 0.0f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n3, 0.0f, n4, 0.0f, 14336, null);
                    Iterable iterable3 = list;
                    for (Object t : iterable3) {
                        object = (Setting)t;
                        Fonts.getSF_M3().drawStringWithShadow(drawContext, ((Setting)object).Q(), l3, this.T + 3.0f, this.X + f17 + 3.0f, Theme.INSTANCE.getTextColor());
                        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                        Shapes2D.drawShape$default(matrix3x2fStack2, (char)n2, this.T + f19 - 14.0f, this.X + 3.5f + f17, 11.0f, 11.0f, 3.0f, 2.5f, 0.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)n3, 0.0f, n4, 0.0f, 14336, null);
                        if (Intrinsics.areEqual(((Setting)object).getValue(), true)) {
                            Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
                            RenderTarget.drawRoundedText$default(this.checkboxTexture, (Matrix3x2f)matrix3x2fStack3, (double)(this.T + f19) - 14.5, (double)(this.X + f17) + 2.0, 0.0f, 0.0f, Theme.INSTANCE.getPrimaryColor(), null, 0.0f, 216, l9, null);
                        }
                        f17 += 14.0f;
                    }
                }
            }
            if ((!this.isHovered() || !(Globals.getMc().currentScreen instanceof ChatScreen)) && !(Globals.getMc().currentScreen instanceof HudEditorScreen)) break block22;
            this.updateCursor();
            HudManager.INSTANCE.setSelectedElement(this);
        }
    }

    public final void drawText(@NotNull DrawContext drawContext, long l, @NotNull String string) {
        long l2 = l = u ^ l;
        long l3 = l2 ^ 0x6CDD77788DD5L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 32);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x46B1EEB6FEFAL;
        long l5 = l2 ^ 0x202B37C3803DL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(string, "text");
        float f = Fonts.getSF_M().getWidth(l5, string);
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        HudElement.drawBackground$default(this, matrix3x2fStack, this.getX(), (short)n, n2, this.getY(), f + 19.0f, 13.0f, (short)n3, 0.0f, false, 96, null);
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Object var24_10 = null;
        float f2 = 0.0f;
        float f3 = this.getY() + 3.0f;
        float f4 = this.getX() + (float)14;
        Matrix3x2fStack matrix3x2fStack3 = matrix3x2fStack2;
        HudElement hudElement = this;
        HudElement.queueShadow$default(hudElement, matrix3x2fStack3, f4, f3, f2, var24_10);
        Matrix3x2fStack matrix3x2fStack4 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack4, "pose(...)");
        HudElement.queueImage$default(this, this.renderTarget, matrix3x2fStack4, this.getX() + 2.0f, this.getY(), 10.0f, 10.0f, 0.0f, null);
        this.drawString(drawContext, string, this.getX() + 16.0f, l4, this.getY() + 1.0f, Theme.INSTANCE.getTextColor());
        this.width = f + 20.0f;
        String string2 = HudElement.zkmGetFlag();
        this.height = 13.0f;
    }

    public final void drawLabel(@NotNull DrawContext drawContext, @NotNull String string, float f, boolean bl, long l) {
        long l2 = l = u ^ l;
        long l3 = l2 ^ 0x70294DB48DB4L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 32);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x5A45D47AFE9BL;
        long l5 = l2 ^ 0x3CDF0D0F805CL;
        String string2 = HudElement.zkmGetFlag();
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(string, "header");
        float f2 = Fonts.getSF_M().getWidth(l5, string);
        String string3 = string2;
        float f3 = f2 + 24.0f;
        float f4 = this.getX() + (bl ? this.width - f3 : 0.0f);
        drawContext.getMatrices().pushMatrix();
        drawContext.getMatrices().translate(f4 + f3 / 2.0f, this.getY() + 8.0f);
        drawContext.getMatrices().scale(((Number)RangesKt.coerceIn((Comparable)Float.valueOf(f), RangesKt.rangeTo(0.0f, 2.0f))).floatValue(), ((Number)RangesKt.coerceIn((Comparable)Float.valueOf(f), RangesKt.rangeTo(0.0f, 2.0f))).floatValue());
        drawContext.getMatrices().translate(-(f4 + f3 / 2.0f), -(this.getY() + 8.0f));
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        HudElement.drawBackground$default(this, matrix3x2fStack, f4, (short)n, n2, this.getY(), f3, 16.0f, (short)n3, 0.0f, false, 96, null);
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        HudElement.queueImage$default(this, this.renderTarget, matrix3x2fStack2, f4 + 2.0f, this.getY() + 1.5f, 10.0f, 10.0f, 0.0f, null);
        Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
        Object var28_15 = null;
        float f5 = 0.0f;
        float f6 = this.getY() + 4.0f;
        float f7 = f4 + (float)14;
        Matrix3x2fStack matrix3x2fStack4 = matrix3x2fStack3;
        HudElement hudElement = this;
        HudElement.queueShadow$default(hudElement, matrix3x2fStack4, f7, f6, f5, var28_15);
        this.drawString(drawContext, string, f4 + 18.0f, l4, this.getY() + 2.0f, Theme.INSTANCE.getTextColor());
        drawContext.getMatrices().popMatrix();
    }

    public void onMouseButton(char c, short s, int n, int n2, @NotNull InputEvent.Action action) {
        long l = ((long)c << 48 | (long)s << 48 >>> 16 | (long)n2 << 32 >>> 32) ^ u;
        long l2 = l ^ 0x4B9CDC436466L;
        Intrinsics.checkNotNullParameter((Object)action, "action");
        if (n == 0) {
            if (this.settingsOpen && action == InputEvent.Action.Press) {
                Float f;
                Iterable iterable;
                Iterable iterable2 = iterable = (Iterable)this.getSettings();
                Object object = new ArrayList();
                for (Object object2 : iterable2) {
                    Setting setting2 = (Setting)object2;
                    if (!(!Intrinsics.areEqual(setting2.getId(), "enabled") && !Intrinsics.areEqual(setting2.getId(), "position"))) continue;
                    object.add(object2);
                }
                List list = (List)object;
                if (!(object = ((Iterable)list).iterator()).hasNext()) {
                    f = null;
                } else {
                    Setting setting3 = (Setting)object.next();
                    float f2 = Fonts.getSF_M3().getWidth(l2, setting3.Q());
                    while (object.hasNext()) {
                        Object object3 = (Setting)object.next();
                        float f3 = Fonts.getSF_M3().getWidth(l2, ((Setting)object3).Q());
                        f2 = Math.max(f2, f3);
                    }
                    f = Float.valueOf(f2);
                }
                float f4 = (f != null ? f.floatValue() : 0.0f) + 23.0f;
                float f5 = 0.0f;
                iterable2 = list;
                for (Object object3 : iterable2) {
                    Object object2;
                    object2 = (Setting)object3;
                    float f6 = this.T + f4 - 14.0f;
                    float f7 = this.T + f4 - 3.0f;
                    float f8 = HudManager.INSTANCE.getMouseX();
                    boolean bl = f6 <= f8 ? f8 <= f7 : false;
                    if (bl) {
                        f6 = this.X + 3.5f + f5;
                        f7 = this.X + 14.5f + f5;
                        f8 = HudManager.INSTANCE.getMouseY();
                        boolean bl2 = f6 <= f8 ? f8 <= f7 : false;
                        if (bl2 && ((Setting)object2).getValue() instanceof Boolean) {
                            Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type su.catlean.api.setting.Setting<kotlin.Boolean>");
                            Object t = object2;
                            ((Setting)t).setValue((Boolean)((Setting)t).getValue() == false);
                        }
                    }
                    f5 += 14.0f;
                }
                return;
            }
            if (action == InputEvent.Action.Release) {
                this.w = false;
                HudManager.INSTANCE.setDraggedElement(null);
            } else if (this.isHovered() && action == InputEvent.Action.Press && HudManager.INSTANCE.getDraggedElement() == null) {
                this.onClick();
            }
        } else if (action == InputEvent.Action.Press && this.isHovered()) {
            boolean bl;
            block18: {
                Iterable iterable = this.getSettings();
                if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                    bl = false;
                } else {
                    for (Object t : iterable) {
                        Setting setting4 = (Setting)t;
                        if (!(!Intrinsics.areEqual(setting4.getId(), "enabled") && !Intrinsics.areEqual(setting4.getId(), "position"))) continue;
                        bl = true;
                        break block18;
                    }
                    bl = false;
                }
            }
            if (bl) {
                this.settingsOpen = !this.settingsOpen;
                this.T = HudManager.INSTANCE.getMouseX();
                this.X = HudManager.INSTANCE.getMouseY();
            }
        }
    }

    protected final void onClick() {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ u;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)1613011060449757820L, (long)l);
        HudElement hudElement = this;
        if (callSite == null) {
            if (hudElement instanceof CoordWidget) {
                GLFW.glfwSetClipboardString((long)Globals.getMc().getWindow().getHandle(), (CharSequence)((int)Globals.getPlayer().getX() + " " + (int)Globals.getPlayer().getY() + " " + (int)Globals.getPlayer().getZ()));
            }
            this.C = HudManager.INSTANCE.getMouseX() - this.getX();
            this.dragOffsetY = HudManager.INSTANCE.getMouseY() - this.getY();
            hudElement = this;
        }
        hudElement.w = true;
        HudManager.INSTANCE.setDraggedElement(this);
    }

    public final float getX() {
        return this.getPos().getX() * (float)this.getScreenWidth();
    }

    public final float getY() {
        return this.getPos().getY() * (float)this.getScreenHeight();
    }

    protected final float getMouseX() {
        return HudManager.INSTANCE.getMouseX();
    }

    protected final float getMouseY() {
        return HudManager.INSTANCE.getMouseY();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected final boolean isHovered() {
        float f = this.getX();
        float f2 = this.getX() + this.width;
        float f3 = this.getMouseX();
        String string = HudElement.zkmGetFlag();
        if (!(f <= f3)) return false;
        if (!(f3 <= f2)) return false;
        boolean bl = true;
        if (!bl) return false;
        f = this.getY();
        f2 = this.getY() + this.height;
        f3 = this.getMouseY();
        if (!(f <= f3)) return false;
        if (!(f3 <= f2)) return false;
        return true;
    }

    protected final void updateCursor() {
        int n = HudManager.INSTANCE.getDraggedElement() != null ? 221193 : 221188;
        GLFW.glfwSetCursor((long)Globals.getMc().getWindow().getHandle(), (long)GLFW.glfwCreateStandardCursor((int)n));
    }

    protected final void setSize(float f, float f2) {
        this.width = f;
        this.height = f2;
    }

    protected final int getScreenHeight() {
        return Globals.getMc().getWindow().getScaledHeight();
    }

    protected final int getScreenWidth() {
        return Globals.getMc().getWindow().getScaledWidth();
    }

    protected final boolean isNear(float f, float f2) {
        return Math.abs(f - f2) < 2.0f;
    }

    protected final void drawBackground(@NotNull Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, long l, float f4, float f5, boolean bl) {
        long l2 = (l = u ^ l) ^ 0x59D7706B2347L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 48);
        int n3 = (int)(l2 << 32 >>> 32);
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "m");
        if (bl) {
            HudManager.INSTANCE.getRender2D().drawRoundedRect(matrix3x2fStack, f, f2, f3, f4, f5);
        } else {
            Shapes2D.drawShape$default(matrix3x2fStack, (char)n, f, f2, f3, f4, 3.0f, 1.5f, 0.0f, f5, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        }
    }

    public static void drawBackground$default(HudElement hudElement, Matrix3x2fStack matrix3x2fStack, float f, short s, int n, float f2, float f3, float f4, short s2, float f5, boolean bl, int n2, Object object) {
        long l = ((long)s << 48 | (long)n << 32 >>> 16 | (long)s2 << 48 >>> 48) ^ u;
        long l2 = l ^ 0x7B9C209B932EL;
        String string = HudElement.zkmGetFlag();
        if (object != null) {
            throw new UnsupportedOperationException((String)((Object)HudElement.zkmBootstrap("a", (int)13513, (long)(0x2282E14570988E50L ^ l))));
        }
        int n3 = n2 & HudElement.zkmBootstrap2("b", (int)9284, (long)(0x3BABB0CC1CAFB821L ^ l));
        String string2 = string;
        if (s >= 0) {
            if (n3 != 0) {
                f5 = 1.0f;
            }
            n3 = n2 & HudElement.zkmBootstrap2("b", (int)19510, (long)(0x3961803E9EED5059L ^ l));
            string2 = string;
        }
        if (n3 != 0) {
            bl = true;
        }
        hudElement.drawBackground(matrix3x2fStack, f, f2, f3, l2, f4, f5, bl);
    }

    protected final void queueShadow(@NotNull Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "m");
        Matrix3x2f matrix3x2f = new Matrix3x2f((Matrix3x2fc)matrix3x2fStack);
        HudManager.INSTANCE.getShapeCommands().add(new ShapeCommand2(matrix3x2f, f, f2, 0.5f, 8.5f, f3));
    }

    public static void queueShadow$default(HudElement hudElement, Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: divider");
        }
        f3 = 1.0f;
        hudElement.queueShadow(matrix3x2fStack, f, f2, f3);
    }

    protected final void queueImage(@NotNull RenderTarget renderTarget, @NotNull Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, float f4, float f5) {
        Intrinsics.checkNotNullParameter(renderTarget, "image");
        Intrinsics.checkNotNullParameter(matrix3x2fStack, "m");
        Matrix3x2f matrix3x2f = new Matrix3x2f((Matrix3x2fc)matrix3x2fStack);
        HudManager.INSTANCE.getImageCommands().add(new ImageCommand(renderTarget, matrix3x2f, f, f2, f3, f4, f5));
    }

    public static void queueImage$default(HudElement hudElement, RenderTarget renderTarget, Matrix3x2fStack matrix3x2fStack, float f, float f2, float f3, float f4, float f5, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: icon");
        }
        f5 = 1.0f;
        hudElement.queueImage(renderTarget, matrix3x2fStack, f, f2, f3, f4, f5);
    }

    protected final void drawString(@NotNull DrawContext drawContext, @NotNull String string, float f, long l, float f2, @NotNull Color color) {
        long l2 = (l = u ^ l) ^ 0x2A5728BB9ED4L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Intrinsics.checkNotNullParameter(string, "s");
        Intrinsics.checkNotNullParameter(color, "color");
        FontRenderer fontRenderer = Fonts.getSF_M();
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, string, f, f2, l2, color, false, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        HudElement.u = ZkmKeys.a(4051785881732570208L, 7133493794591131875L, MethodHandles.lookup().lookupClass()).a(221274452969961L);
                        var20 = HudElement.u ^ 49269217096539L;
                        HudElement.bb = new HashMap<K, V>(13);
                        HudElement.zkmSetFlag(null);
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
                        var18_3 = new String[28];
                        var16_4 = 0;
                        var15_5 = "1\u0002\u00ffC\u00be\u00e7\u00ca\u00a4\u00d6\u0085\u00c37\u00c1@\u00982\u0087\u00d2=h\u00e6\u00dcN\u00eeY\u00be<\u0089T0\u00b6\u00df\u0010m\u00ee\u00a6`\u000e\u00b1\u00f33\u00ea\u00b4\u008c\u00a0Ni\u00bcQ\u0010\u00a3\u00e3\f\u009f\u0004w:\u00d8-\n\u00e4\u00bc\u00b3v(\u00a6\u0010\u00e5\u00e3\u00b8\u00f8\u00da:\u0093Y\u00a2|&c\u0098^\u001b]\u0010\u00ff\u009e\u00d4\u0006\u00b5T\u001b\u0091h\u0006l 8y3\u00ab\u0010\u0093\u008cCf\u00e4\u00b92B\u00a1u\u0090\"p\u00ea\u00ef!\u0010\u00ea\u00cfm\u001f\u00aa\u0090A\u0006\u00f4\u008f\u00a0e\u00e7\u00a1\u00c8\u009e\u0010\u00c3\u008c\u00c0~T\u001e\u00a0\u00e3\u00b0\u0012\u0084\u00fd\u0094\u000f\u00c2y\u0010\u008e\u00c4\u00e5\u00db,\u00b2\u00ef5z}\rUd\u00d0\u0004\u00950A\u00c6\u0002\u00ef\u00d5\u0089-8v\u001f\u008e\u008a\u0019\u001a\u009b(\u00ac\u00d8.\u00b1K{\u001b\u00118\u000f\u00f3\f\u00b6&\u00d7\u00e6(\u00b8_\u00f3If\u000f\u00b5\u0097\u00a3\u00ca\u0000<\\+M\u0010\u0082\u001d}q\u00c45\u00adG\f\u0089\u00b8\u00fa\u00a3\u009af\u00a3\u0018\u008f\u00d2\u00da6>\u0001!\u00be\u00d4\u001d\u0085\u00c8!\u001c\u0088\u0083\u00ab=h\u00ef\u0018\u008a=\u008e\u0018s\u00fd\u0088\fj\u00d1l|\u0084iDfz\t\u001cR\u00a1\u00e5\u000f\u0094\u00df\u0018\u009c\u009b \u00b3\u00ac\n$\u00ec\u00faD\"\u00e9q\u00ba\u00c7f~\f\u0012\u001dyb\\\u00a9,\u00db`\u00ee\u00103\u00f6~D\u0002\r\u0010\r\u00d7F\u00cc7\u00b6\u008d\u000bf\u00eb\u009b\u0018\u0000\t6\u00f7\u0088h\u00a0\u00b4h\u00af\u00fc\u00c9:\u00fa\u0081\u0016G\u00c0\u00d5k\u00a2X\u00c2\u00e7Fm\u00c7\u0019#\u00ea\u001e]\u00b34Dv?\u007f\u00d3\u00d5\u00d9 J\r\u0098\u00d78h\u00bf@\u00ca\u00db\u0083e\u00cc\u00e1@$t\u008bbA\u008b%\u009e \u0011\u00deF\u00da\u0093\u00ff\u00a9\u00b2\u00b2\u00f6\u00b3OqL\u00b9.Z\u00bb\u00cb\u00d7D\u00fc\u000es\u00cc\u00ac\u001d\u0010v\u00ed\u00bf:l\u00ec5\u0098\u0095\u00df2\u00b6\r\u00f28+G\u0019'\u001er\u00c3\u0085H\u00eb\u0087u&\u00d2\u00dd&\u00e9\u001e$i\u0014)\u00c4\u001e\u00d9\u000f\u00b2\u00dd\u00da\u00cf\u00b1\u00a2\u0080\u0085(v5\u00e3\u00e4\u0007\u00d3\u00bf\u00a1*G\u0090D\u000f\u00a6\u00e6\u00e0\u00be\"\u00d3'1\u0004\u0088\u0083\u00a2j.\u00a1\u00ecH\u0003Dwi)\u001c\u00e3\u00d5I\u0086\u00fc\u00bf\u00fb\u00a6K$\u001f\u00fd\u0095ZE\u00b7\u008f\u001c\u008a\u00dd\u009c\u0095\u0081\u00f8.\u001d\u00c3\u00d4;|?^`V}\u008b\u0005_\u001d\u0003\u00b9\u0088\u00a1\u00e6\u00fe,f\u0083\u000f$\u009dv\u0010\u00f3\u008e\u00dd\u00fcV\u0013\u00f4w]\u00ca\u00b5\u0085qZF%\u00ab\u00de\u0080\u0098\u00f4\u0000\u001c\"p\u0099\u00a3\u0015Z\u008c^\u00e5 \u001c\u00cf\u00cc\u0005P+\u00ae\u0019s\u00f1\u0011\u00b6\u00fd\u00b5\u00a1\u001a\u00b4\u00d6\u0012\u00a9\u000e;\u001b\u000e\u00ac\u00c8\u00b3u\u00b4n\u0000\u00c6\u000ea\u00c4A\u0095M\u00e9\u00a8\u00f4\u00c8\u00e3G\u00da\fj\u008fr\u00f8R>5e2\u0083,c\u00ae|T\u008e\u0088Rq\u009d'\u00e8\u008ce\u00df\u00cf.{\u0080\u00dc6\u008ar\u00b5\u00c5\u00e6\u00b9XQ(\u0088\u00b6\u0089]\u00c8\u0013]\u00b55\u009e92\n\u0097\u00a0\u00c2\u00f5\u0011\u00c8\u00db\"\u00b6\u00847\u0004\u0003K=\u00f7\u0092%\u0098\u0099/\u009f\u0011\u00b1n\u00ef\u0017\u0010\u0016\u0085<\u0084\u00c6\u00f3\u00f4*\u0083\u00df\u00deh\u007few\u000b\u0010\u0096\u000ei\u00e6\u001bV9\u00c3\u000f\u009b\u00c5\u000e%k\u0091\u00e8\u0010XJ\u00ec\u0093\u0096\u00a4\u0019ovlE:\u00aa2\u00bb\u0088\u0010e\u001d\u001f\u00f1]\u00804\u00e1\u0012/\u00f7\u00c4'\u00f9\u001d\u00d7\u0018\u00ea4\u000b\u001d\u0010\u00e5\u0081\u00cb\u00df\u000bb\u0015\u00ef\u00c8J\u00a2\u009cg\u00f4\u0010\u00c3\u0002\u00b4\u00e4\u0010g\u00a8$E\u00e8\u007fQ\u0001^\u00d6\u00a0Ts%\u00cc1\u0088/`\u00cff\u009d\u00aak\u00a1\u00a7\u0095)$\u00ff\u00e2\u00ad\u00e4 \u0091\u001bn\u00c5\u00d0\u00b3\u0080cA\u00eb\u00e8\u00d3;\u00fb\u00a0c\u0096\b\u00e7v\u00f2`Pu\u008e3\u00ae\u00d0f\u00dc>\u00f9\u00c0\u00a4\u0095\u00bc\u001b5+\u00e3\u00adN!4N\u0083\u00a9\u001c\u0095=\u00f5\u008ej\u0099\u00c0)\f\u00ab3BCk\u00e4b\u00e7@5\u00c9\u00ee2\u00a6\u00e5\u00c6\u00f3\u0093\u00810\u0015\u0082\u00ab\u0081\r.C\r\u00ee\u0016\u00b5\u009a$\u0090\u00f8\u00d5G\u00be-\u0012u\u001e\u008f\\'\u00ec.c\u00ae\u0086\u00a8\u00b4\u00bb\u00a9_\u00d1y1\u00ea\u00f9\u00e6\u00ed";
                        var17_6 = "1\u0002\u00ffC\u00be\u00e7\u00ca\u00a4\u00d6\u0085\u00c37\u00c1@\u00982\u0087\u00d2=h\u00e6\u00dcN\u00eeY\u00be<\u0089T0\u00b6\u00df\u0010m\u00ee\u00a6`\u000e\u00b1\u00f33\u00ea\u00b4\u008c\u00a0Ni\u00bcQ\u0010\u00a3\u00e3\f\u009f\u0004w:\u00d8-\n\u00e4\u00bc\u00b3v(\u00a6\u0010\u00e5\u00e3\u00b8\u00f8\u00da:\u0093Y\u00a2|&c\u0098^\u001b]\u0010\u00ff\u009e\u00d4\u0006\u00b5T\u001b\u0091h\u0006l 8y3\u00ab\u0010\u0093\u008cCf\u00e4\u00b92B\u00a1u\u0090\"p\u00ea\u00ef!\u0010\u00ea\u00cfm\u001f\u00aa\u0090A\u0006\u00f4\u008f\u00a0e\u00e7\u00a1\u00c8\u009e\u0010\u00c3\u008c\u00c0~T\u001e\u00a0\u00e3\u00b0\u0012\u0084\u00fd\u0094\u000f\u00c2y\u0010\u008e\u00c4\u00e5\u00db,\u00b2\u00ef5z}\rUd\u00d0\u0004\u00950A\u00c6\u0002\u00ef\u00d5\u0089-8v\u001f\u008e\u008a\u0019\u001a\u009b(\u00ac\u00d8.\u00b1K{\u001b\u00118\u000f\u00f3\f\u00b6&\u00d7\u00e6(\u00b8_\u00f3If\u000f\u00b5\u0097\u00a3\u00ca\u0000<\\+M\u0010\u0082\u001d}q\u00c45\u00adG\f\u0089\u00b8\u00fa\u00a3\u009af\u00a3\u0018\u008f\u00d2\u00da6>\u0001!\u00be\u00d4\u001d\u0085\u00c8!\u001c\u0088\u0083\u00ab=h\u00ef\u0018\u008a=\u008e\u0018s\u00fd\u0088\fj\u00d1l|\u0084iDfz\t\u001cR\u00a1\u00e5\u000f\u0094\u00df\u0018\u009c\u009b \u00b3\u00ac\n$\u00ec\u00faD\"\u00e9q\u00ba\u00c7f~\f\u0012\u001dyb\\\u00a9,\u00db`\u00ee\u00103\u00f6~D\u0002\r\u0010\r\u00d7F\u00cc7\u00b6\u008d\u000bf\u00eb\u009b\u0018\u0000\t6\u00f7\u0088h\u00a0\u00b4h\u00af\u00fc\u00c9:\u00fa\u0081\u0016G\u00c0\u00d5k\u00a2X\u00c2\u00e7Fm\u00c7\u0019#\u00ea\u001e]\u00b34Dv?\u007f\u00d3\u00d5\u00d9 J\r\u0098\u00d78h\u00bf@\u00ca\u00db\u0083e\u00cc\u00e1@$t\u008bbA\u008b%\u009e \u0011\u00deF\u00da\u0093\u00ff\u00a9\u00b2\u00b2\u00f6\u00b3OqL\u00b9.Z\u00bb\u00cb\u00d7D\u00fc\u000es\u00cc\u00ac\u001d\u0010v\u00ed\u00bf:l\u00ec5\u0098\u0095\u00df2\u00b6\r\u00f28+G\u0019'\u001er\u00c3\u0085H\u00eb\u0087u&\u00d2\u00dd&\u00e9\u001e$i\u0014)\u00c4\u001e\u00d9\u000f\u00b2\u00dd\u00da\u00cf\u00b1\u00a2\u0080\u0085(v5\u00e3\u00e4\u0007\u00d3\u00bf\u00a1*G\u0090D\u000f\u00a6\u00e6\u00e0\u00be\"\u00d3'1\u0004\u0088\u0083\u00a2j.\u00a1\u00ecH\u0003Dwi)\u001c\u00e3\u00d5I\u0086\u00fc\u00bf\u00fb\u00a6K$\u001f\u00fd\u0095ZE\u00b7\u008f\u001c\u008a\u00dd\u009c\u0095\u0081\u00f8.\u001d\u00c3\u00d4;|?^`V}\u008b\u0005_\u001d\u0003\u00b9\u0088\u00a1\u00e6\u00fe,f\u0083\u000f$\u009dv\u0010\u00f3\u008e\u00dd\u00fcV\u0013\u00f4w]\u00ca\u00b5\u0085qZF%\u00ab\u00de\u0080\u0098\u00f4\u0000\u001c\"p\u0099\u00a3\u0015Z\u008c^\u00e5 \u001c\u00cf\u00cc\u0005P+\u00ae\u0019s\u00f1\u0011\u00b6\u00fd\u00b5\u00a1\u001a\u00b4\u00d6\u0012\u00a9\u000e;\u001b\u000e\u00ac\u00c8\u00b3u\u00b4n\u0000\u00c6\u000ea\u00c4A\u0095M\u00e9\u00a8\u00f4\u00c8\u00e3G\u00da\fj\u008fr\u00f8R>5e2\u0083,c\u00ae|T\u008e\u0088Rq\u009d'\u00e8\u008ce\u00df\u00cf.{\u0080\u00dc6\u008ar\u00b5\u00c5\u00e6\u00b9XQ(\u0088\u00b6\u0089]\u00c8\u0013]\u00b55\u009e92\n\u0097\u00a0\u00c2\u00f5\u0011\u00c8\u00db\"\u00b6\u00847\u0004\u0003K=\u00f7\u0092%\u0098\u0099/\u009f\u0011\u00b1n\u00ef\u0017\u0010\u0016\u0085<\u0084\u00c6\u00f3\u00f4*\u0083\u00df\u00deh\u007few\u000b\u0010\u0096\u000ei\u00e6\u001bV9\u00c3\u000f\u009b\u00c5\u000e%k\u0091\u00e8\u0010XJ\u00ec\u0093\u0096\u00a4\u0019ovlE:\u00aa2\u00bb\u0088\u0010e\u001d\u001f\u00f1]\u00804\u00e1\u0012/\u00f7\u00c4'\u00f9\u001d\u00d7\u0018\u00ea4\u000b\u001d\u0010\u00e5\u0081\u00cb\u00df\u000bb\u0015\u00ef\u00c8J\u00a2\u009cg\u00f4\u0010\u00c3\u0002\u00b4\u00e4\u0010g\u00a8$E\u00e8\u007fQ\u0001^\u00d6\u00a0Ts%\u00cc1\u0088/`\u00cff\u009d\u00aak\u00a1\u00a7\u0095)$\u00ff\u00e2\u00ad\u00e4 \u0091\u001bn\u00c5\u00d0\u00b3\u0080cA\u00eb\u00e8\u00d3;\u00fb\u00a0c\u0096\b\u00e7v\u00f2`Pu\u008e3\u00ae\u00d0f\u00dc>\u00f9\u00c0\u00a4\u0095\u00bc\u001b5+\u00e3\u00adN!4N\u0083\u00a9\u001c\u0095=\u00f5\u008ej\u0099\u00c0)\f\u00ab3BCk\u00e4b\u00e7@5\u00c9\u00ee2\u00a6\u00e5\u00c6\u00f3\u0093\u00810\u0015\u0082\u00ab\u0081\r.C\r\u00ee\u0016\u00b5\u009a$\u0090\u00f8\u00d5G\u00be-\u0012u\u001e\u008f\\'\u00ec.c\u00ae\u0086\u00a8\u00b4\u00bb\u00a9_\u00d1y1\u00ea\u00f9\u00e6\u00ed".length();
                        var14_7 = 32;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = HudElement.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00bd\u00dcA\u0015\u008a\u00e8\u00f7\u00d3\u009c\u008fz(6\u00eb\u0092\u00fdIR\u0000\u00e69\u00af\u0091\u008d\u00c1:\u00f6\u00b2\u0002V\u008aH\r\u00b6\u0095\u0002DQ\u0015*\u00ba\u0080\u00c8s%\u001d6\u00cc\u00aa\u00dd\u0019M\u00ae\u00c69\u00a0p\u001cM\u0019F)R\u000f\u00f1-\u0084\u00a5\u00bb\u009aD\u00c41\u00a9\u00c9\t\u008e\u00d04W\u00c6\u00f9\u00dfD\u00a38\u001c\u00a5$u\u00c2\u00d5\u00941\u00d3\u00be8h\\\u00de\u008a8\u00f3|\u00e6\u008co\u00b2\u0012\u0097E\u00d8\u00f9\u00bfC~\u00b9\u0012\u00e3\u000fj\u008b\u0084\u0098\u00f1\u009d/\u0094\u00b3\u0012*\u00e5\u00e2*\u00ec\u00f9 7\u00d8^Zq$\u00bcs\u00b5\u00b0v\u00c1$\u008e\u001f\u00da\u0083\u00c5\u0084}\u009e\u000f\u0000\u00bfB\u00bc\u0001O\u00edR\u0019\u00ac";
                            var17_6 = "\u00bd\u00dcA\u0015\u008a\u00e8\u00f7\u00d3\u009c\u008fz(6\u00eb\u0092\u00fdIR\u0000\u00e69\u00af\u0091\u008d\u00c1:\u00f6\u00b2\u0002V\u008aH\r\u00b6\u0095\u0002DQ\u0015*\u00ba\u0080\u00c8s%\u001d6\u00cc\u00aa\u00dd\u0019M\u00ae\u00c69\u00a0p\u001cM\u0019F)R\u000f\u00f1-\u0084\u00a5\u00bb\u009aD\u00c41\u00a9\u00c9\t\u008e\u00d04W\u00c6\u00f9\u00dfD\u00a38\u001c\u00a5$u\u00c2\u00d5\u00941\u00d3\u00be8h\\\u00de\u008a8\u00f3|\u00e6\u008co\u00b2\u0012\u0097E\u00d8\u00f9\u00bfC~\u00b9\u0012\u00e3\u000fj\u008b\u0084\u0098\u00f1\u009d/\u0094\u00b3\u0012*\u00e5\u00e2*\u00ec\u00f9 7\u00d8^Zq$\u00bcs\u00b5\u00b0v\u00c1$\u008e\u001f\u00da\u0083\u00c5\u0084}\u009e\u000f\u0000\u00bfB\u00bc\u0001O\u00edR\u0019\u00ac".length();
                            var14_7 = 136;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = HudElement.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                HudElement.Z = var18_3;
                HudElement.ab = new String[28];
                HudElement.eb = new HashMap<K, V>(13);
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
                var6_12 = new long[20];
                var3_13 = 0;
                var4_14 = "\u00d6\u0089yQ\u0086\u00a6i\u00c7\u00aeX\u0088]iE\u000e\u00deq\u00c3\u00e9Y\u00b4\u0098\u008d\u0089q\u00ac{\u00934\u001f\u00c3\u00aa\u00bd\u00f55\u00e3x|x:\u0089(\u00b8i{h\u0002F\u00c4\u00ee|L^\u0080\u00e16\u00b1\u00a8/\u00d6: \u008a\u00acW\u00d7Uj3\u00ec]l\u0005[\u00ddd\u00fb^\u00d4 W=\u00e4\u00ce\u0095\u00d3\u00ffu\u009b\u0015\u00c3\u00b8\u00f1\u0089n\u00cb\u00aa\u00b9.h\u00d5\u00a5#\u00b2\u000b`\u0005\u00daa\u00bfI\u0015\u00d8\u0013\f+\u00a2\u0003\u00e2\u00cc1is\u00a6\n?\u00ca\u00de\u0081\u00c1h2Au\u008d]\u00bat~G\u00a6\u009bh7";
                var5_15 = "\u00d6\u0089yQ\u0086\u00a6i\u00c7\u00aeX\u0088]iE\u000e\u00deq\u00c3\u00e9Y\u00b4\u0098\u008d\u0089q\u00ac{\u00934\u001f\u00c3\u00aa\u00bd\u00f55\u00e3x|x:\u0089(\u00b8i{h\u0002F\u00c4\u00ee|L^\u0080\u00e16\u00b1\u00a8/\u00d6: \u008a\u00acW\u00d7Uj3\u00ec]l\u0005[\u00ddd\u00fb^\u00d4 W=\u00e4\u00ce\u0095\u00d3\u00ffu\u009b\u0015\u00c3\u00b8\u00f1\u0089n\u00cb\u00aa\u00b9.h\u00d5\u00a5#\u00b2\u000b`\u0005\u00daa\u00bfI\u0015\u00d8\u0013\f+\u00a2\u0003\u00e2\u00cc1is\u00a6\n?\u00ca\u00de\u0081\u00c1h2Au\u008d]\u00bat~G\u00a6\u009bh7".length();
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "7\u0088H\u00f5\u0004\u0000u\u007f\u009d}\u000b\u0083d\u00b25U";
                    var5_15 = "7\u0088H\u00f5\u0004\u0000u\u007f\u009d}\u000b\u0083d\u00b25U".length();
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        HudElement.cb = var6_12;
        HudElement.db = new Integer[20];
        var22_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(HudElement.class, "enabled", "getEnabled()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(HudElement.class, "pos", "getPos()Lsu/catlean/d_;", 0))};
        HudElement.delegatedProperties = var22_20;
    }

    public static void zkmSetFlag(String string) {
        W = string;
    }

    public static String zkmGetFlag() {
        return W;
    }

    private static UnsupportedOperationException zkmRethrow(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xECC;
        if (ab[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])bb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    bb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/gf", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = Z[n2].getBytes("ISO-8859-1");
            HudElement.ab[n2] = HudElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return ab[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HudElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2822;
        if (db[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = cb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])eb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    eb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/gf", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HudElement.db[n2] = n3;
        }
        return db[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HudElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/gf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HudElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HudElement.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

