/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
 *  org.lwjgl.glfw.GLFW
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
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import org.lwjgl.glfw.GLFW;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.ModeOption;
import su.catlean.gui.SettingElement;
import su.catlean.gui.SettingGroupElement$1;
import su.catlean.module.Module;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Setting;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class SettingGroupElement
extends SettingElement {
    @NotNull
    private final Setting setting;
    private boolean expanded;
    @NotNull
    private final Animation animation;
    @NotNull
    private List options;
    private float nextY;
    private boolean O;
    private static final long d;
    private static final String[] e;
    private static final String[] f;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] k;
    private static final Map l;
    private static final long m;

    public SettingGroupElement(short s, char c, @NotNull Setting setting2, int n) {
        long l = ((long)s << 48 | (long)c << 48 >>> 16 | (long)n << 32 >>> 32) ^ d;
        long l2 = l ^ 0x72DBB8EC15B7L;
        Intrinsics.checkNotNullParameter(setting2, "setting");
        super(setting2);
        this.setting = setting2;
        this.animation = new Animation(Easing.OUT_QUINT, m);
        this.options = new ArrayList();
        // Speed mode: force list layout so every enum constant (incl. GrimBypass) is visible
        if (this.getSetting().getValue() != null
                && this.getSetting().getValue().getClass().getName().endsWith("SpeedMode")) {
            this.O = true;
        }
        if (this.O) {
            ?[] objArray = this.getSetting().getValue().getClass().getEnumConstants();
            Intrinsics.checkNotNullExpressionValue(objArray, "getEnumConstants(...)");
            for (Object obj : objArray) {
                Enum enum_ = (Enum)obj;
                float f = this.getWidth() - 11.0f;
                Intrinsics.checkNotNull(enum_);
                this.options.add(new ModeOption(1.0f, this.nextY, f, enum_, null, null));
                this.nextY += 15.0f;
            }
        } else {
            float f = 2.0f;
            ?[] objArray = this.getSetting().getValue().getClass().getEnumConstants();
            Intrinsics.checkNotNullExpressionValue(objArray, "getEnumConstants(...)");
            Object object = objArray;
            object = ArraysKt.sortedWith(object, new SettingGroupElement$1());
            Iterator iterator2 = object.iterator();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                Enum enum_ = (Enum)t;
                String string = enum_.name();
                float f2 = Fonts.getSF_M4().getWidth(l2, string) + 4.0f;
                if (f + f2 > this.getWidth() - 10.0f) {
                    this.nextY += 12.0f;
                    f = 2.0f;
                }
                Intrinsics.checkNotNull(enum_);
                this.options.add(new ModeOption(f, this.nextY, f2, enum_, null, null));
                f += f2 + 2.0f;
            }
        }
    }

    @Override
    @NotNull
    public Setting getSetting() {
        return this.setting;
    }

    @NotNull
    public final List getOptions() {
        return this.options;
    }

    public final void setOptions(@NotNull List list) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ d;
        Intrinsics.checkNotNullParameter(list, (String)((Object)SettingGroupElement.zkmBootstrap("e", (int)778, (long)(0x6A4E65120344FE50L ^ l))));
        this.options = list;
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, long l, float f) {
        long l2 = l;
        long l3 = l2 ^ 0x68AE1B4E0484L;
        long l4 = l2 ^ 0x12EA342FFCD3L;
        int n3 = (int)(l4 >>> 48);
        int n4 = (int)(l4 << 16 >>> 48);
        int n5 = (int)(l4 << 32 >>> 32);
        long l5 = l2 ^ 0x7C5724644152L;
        long l6 = l2 ^ 0x6DC28CF79CA6L;
        long l7 = l2 ^ 0x14A810E00D9FL;
        int n6 = (int)(l7 >>> 48);
        long l8 = l7 << 16 >>> 16;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        this.setHeight(this.O ? (this.expanded ? this.nextY + 15.0f : 14.0f) : (this.expanded ? this.nextY + 28.0f : 14.0f));
        this.animation.animate(this.expanded ? 1.0f : 0.0f);
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n3, this.getX(), this.getY() - 1.0f, this.getWidth() - 7.0f, this.animation.getValue() * (this.getHeight() - 14.0f) + 14.0f - 1.0f, 3.0f, 2.5f, 0.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        RenderUtil.INSTANCE.beginScissor(drawContext, new Rect(this.getX(), this.getY() - 1.0f, this.getX() + this.getWidth() - 7.0f, this.getY() - 1.0f + (this.animation.getValue() * (this.getHeight() - 14.0f) + 14.0f - 1.0f)));
        float f2 = Fonts.getSF_M3().getWidth(l5, this.getSetting().Q()) / 2.0f;
        float f3 = ((this.getWidth() - 16.0f) / 2.0f - f2) * this.animation.getValue();
        FontRenderer fontRenderer = Fonts.getSF_M3();
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        FontRenderer.drawString$default(fontRenderer, matrix3x2fStack2, this.getSetting().Q(), this.getX() + 3.0f + f3, this.getY(), l3, Theme.INSTANCE.getTextColor(), false, null);
        String string = ((Enum)this.getSetting().getValue()).name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string, "toLowerCase(...)");
        String[] stringArray = new String[]{"_"};
        String string2 = ((Object)StringsKt.trim((CharSequence)CollectionsKt.joinToString$default(StringsKt.split$default((CharSequence)string, stringArray, false, 0, 6, null), " ", null, null, 0, null, SettingGroupElement::render$lambda$0, 30, null))).toString();
        float f4 = Fonts.getSF_M3().getWidth(l5, string2);
        FontRenderer fontRenderer2 = Fonts.getSF_M3();
        Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
        float f5 = 1.0f - this.animation.getValue();
        FontRenderer.drawStringWithShadow$default(fontRenderer2, matrix3x2fStack3, string2, (short)n6, this.getX() + this.getWidth() - 11.0f - f4 + f3, this.getY(), l8, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), f5), false, 0, 96, null);
        if (this.expanded) {
            Iterable iterable = this.options;
            for (Object t : iterable) {
                ModeOption modeOption = (ModeOption)t;
                modeOption.getAnimation().animate(Intrinsics.areEqual(modeOption.getValue(), this.getSetting().getValue()) ? 1.0f : 0.0f);
                Color color = RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), modeOption.getAnimation().getValue()).brighter();
                Color color2 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveModuleOutlineColor(), Theme.INSTANCE.getModuleOutlineColor(), modeOption.getAnimation().getValue()).brighter();
                if (this.O) {
                    Matrix3x2fStack matrix3x2fStack4 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack4, "pose(...)");
                    float f6 = this.getY() + modeOption.getY() + 13.0f;
                    float f7 = modeOption.getW();
                    Intrinsics.checkNotNull(color);
                    Intrinsics.checkNotNull(color2);
                    Shapes2D.drawShape$default(matrix3x2fStack4, (char)n3, this.getX() + modeOption.getX() + 1.0f, f6, f7, 13.0f, 2.0f, 2.5f, 0.8f, 1.0f, color, color2, 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
                    FontRenderer fontRenderer3 = Fonts.getSF_M3();
                    String string3 = modeOption.getValue().name().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(string3, "toLowerCase(...)");
                    var49_27 = new String[]{"_"};
                    fontRenderer3.drawStringCentered(drawContext, ((Object)StringsKt.trim((CharSequence)CollectionsKt.joinToString$default(StringsKt.split$default((CharSequence)string3, var49_27, false, 0, 6, null), " ", null, null, 0, null, SettingGroupElement::render$lambda$1, 30, null))).toString(), this.getX() + modeOption.getX() + 1.0f + modeOption.getW() / 2.0f, this.getY() + modeOption.getY() + 18.0f, Theme.INSTANCE.getTextColor(), l6);
                } else {
                    Matrix3x2fStack matrix3x2fStack5 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack5, "pose(...)");
                    float f8 = this.getY() + modeOption.getY() + 13.0f;
                    float f9 = modeOption.getW();
                    Intrinsics.checkNotNull(color);
                    Intrinsics.checkNotNull(color2);
                    Shapes2D.drawShape$default(matrix3x2fStack5, (char)n3, this.getX() + modeOption.getX() + 1.0f, f8, f9, 10.0f, 2.0f, 2.5f, 0.8f, 1.0f, color, color2, 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
                    FontRenderer fontRenderer4 = Fonts.getSF_M4();
                    String string4 = modeOption.getValue().name().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(string4, "toLowerCase(...)");
                    var49_27 = new String[]{"_"};
                    fontRenderer4.drawStringCentered(drawContext, ((Object)StringsKt.trim((CharSequence)CollectionsKt.joinToString$default(StringsKt.split$default((CharSequence)string4, var49_27, false, 0, 6, null), " ", null, null, 0, null, SettingGroupElement::render$lambda$2, 30, null))).toString(), this.getX() + modeOption.getX() + 1.0f + modeOption.getW() / 2.0f, this.getY() - 1.0f + modeOption.getY() + 18.0f, Theme.INSTANCE.getTextColor(), l6);
                }
                float f10 = this.getX() + modeOption.getX() + 1.0f;
                float f11 = this.getX() + modeOption.getX() + 1.0f + modeOption.getW();
                float f12 = n;
                boolean bl = f10 <= f12 ? f12 <= f11 : false;
                if (!bl) continue;
                f10 = this.getY() - 1.0f + modeOption.getY() + 14.0f;
                f11 = this.getY() + modeOption.getY() + 23.0f;
                f12 = n2;
                boolean bl2 = f10 <= f12 ? f12 <= f11 : false;
                if (!bl2 || GLFW.glfwGetPlatform() == 393219) continue;
                GLFW.glfwSetCursor((long)Globals.getMc().getWindow().getHandle(), (long)GLFW.glfwCreateStandardCursor((int)221188));
                ClickGuiScreen.INSTANCE.A(false);
            }
        }
        RenderUtil.INSTANCE.endScissor(drawContext);
    }

    @Override
    public void mouseClicked(long l, double d, double d2, int n) {
        float f = this.getX();
        boolean bl = d <= (double)(this.getX() + this.getWidth() - 7.0f) ? (double)f <= d : false;
        if (bl) {
            f = this.getY();
            boolean bl2 = d2 <= (double)(this.getY() + 12.0f) ? (double)f <= d2 : false;
            if (bl2) {
                boolean bl3 = this.expanded = !this.expanded;
            }
        }
        if (this.expanded) {
            Iterable iterable = this.options;
            for (Object t : iterable) {
                ModeOption modeOption = (ModeOption)t;
                float f2 = this.getX() + modeOption.getX() + 1.0f;
                boolean bl4 = d <= (double)(this.getX() + modeOption.getX() + 1.0f + modeOption.getW()) ? (double)f2 <= d : false;
                if (!bl4) continue;
                f2 = this.getY() - 1.0f + modeOption.getY() + 14.0f;
                boolean bl5 = d2 <= (double)(this.getY() + modeOption.getY() + 23.0f) ? (double)f2 <= d2 : false;
                if (!bl5) continue;
                this.getSetting().setValue(modeOption.getValue());
            }
        }
    }

    @Override
    public void mouseReleased(double d, double d2) {
    }

    @Override
    public void keyPressed(int n, short s, int n2, char c) {
    }

    @Override
    public void charTyped(char c) {
    }

    @Override
    public boolean mouseScrolled(double d, double d2, double d3) {
        return false;
    }

    @Override
    public void close() {
    }

    private static final CharSequence render$lambda$0(String string) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "s");
        String string3 = string;
        if (((CharSequence)string3).length() > 0) {
            char c = string3.charAt(0);
            StringBuilder stringBuilder = new StringBuilder();
            String string4 = String.valueOf(c);
            Intrinsics.checkNotNull(string4, "null cannot be cast to non-null type java.lang.String");
            String string5 = string4.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string5, "toUpperCase(...)");
            StringBuilder stringBuilder2 = stringBuilder.append((Object)string5);
            String string6 = string3;
            int n = 1;
            String string7 = string6.substring(n);
            Intrinsics.checkNotNullExpressionValue(string7, "substring(...)");
            string2 = stringBuilder2.append(string7).toString();
        } else {
            string2 = string3;
        }
        return string2;
    }

    private static final CharSequence render$lambda$1(String string) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "s");
        String string3 = string;
        if (((CharSequence)string3).length() > 0) {
            char c = string3.charAt(0);
            StringBuilder stringBuilder = new StringBuilder();
            String string4 = String.valueOf(c);
            Intrinsics.checkNotNull(string4, "null cannot be cast to non-null type java.lang.String");
            String string5 = string4.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string5, "toUpperCase(...)");
            StringBuilder stringBuilder2 = stringBuilder.append((Object)string5);
            String string6 = string3;
            int n = 1;
            String string7 = string6.substring(n);
            Intrinsics.checkNotNullExpressionValue(string7, "substring(...)");
            string2 = stringBuilder2.append(string7).toString();
        } else {
            string2 = string3;
        }
        return string2;
    }

    private static final CharSequence render$lambda$2(String string) {
        String string2;
        Intrinsics.checkNotNullParameter(string, "s");
        String string3 = string;
        if (((CharSequence)string3).length() > 0) {
            char c = string3.charAt(0);
            StringBuilder stringBuilder = new StringBuilder();
            String string4 = String.valueOf(c);
            Intrinsics.checkNotNull(string4, "null cannot be cast to non-null type java.lang.String");
            String string5 = string4.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(string5, "toUpperCase(...)");
            StringBuilder stringBuilder2 = stringBuilder.append((Object)string5);
            String string6 = string3;
            int n = 1;
            String string7 = string6.substring(n);
            Intrinsics.checkNotNullExpressionValue(string7, "substring(...)");
            string2 = stringBuilder2.append(string7).toString();
        } else {
            string2 = string3;
        }
        return string2;
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
                            SettingGroupElement.d = ZkmKeys.a(-7932014170667442152L, 84409540271182523L, MethodHandles.lookup().lookupClass()).a(114073669680644L);
                            SettingGroupElement.g = new HashMap<K, V>(13);
                            var16 = SettingGroupElement.d ^ 59613837250086L;
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var16 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var16 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[15];
                            var23_4 = 0;
                            var22_5 = "\u0080\u00bc\u00af\u00dbe\u0080\u00c4\u00b1\u00b2\u00b2;\u00b08\u00d3\u00e0=J\u0092\u00b1\u000f\u00b8\u00c8\u0083d\u0010h\u00bc-\u00f5E\u00e1\r\u00b0\u00b9\u009d)\u0013\u008e\u00eb|mXj\u00c5\u00b5\u00f8e\u00d3D\u00bf2W\u0014\u0082\u00adl\u00d6\u00f3\u00ee\u0003\u00bfg\u00c7G}M\u0019C@\u00a1\u008e\u00cb\u00c3\u00a4\u0011K\u00fa\u00aa\u00bd^MH\u00ad'\u00e5D\u00c5\u009f\u00fa\u00e8E\u00df\u0082\u00c1\u00f3\u00f5\u00a8h\u009d\u0083c\u0015\u00d7\u00e7L,\u00a96\u001a\u00c6\u00c0\u00d1\u0090\u0004\u007f\u00dak\u00bd\u009d\u0017o\u000b\u0092e\u0002\t\u008e\u00cc\u00f9\u00f7(D\u00f3\u00e4\u00d3\u0016+]\u00a6\u00cd_\u001c\u00be\u00cf\u00af\u00d6\u00cd\u00c1g\u0016\u00ad\u00ea\u0015dr\u00fcI\u00c71\u0013w\u0015\u008a\u00a6%p%+\u0004\u00de\u00f9(|\u0095\ns8\u00c7\u0082\u00f2\u001d\u00b2\u001b\u00d7\u00c7_\u00fb\u00bc~@\u0007\u00f9A\u0095voXk`t\u00d8\u00e3\u0085!,sYI\u0083\u00c9\u001e'(U\u0012\u0007'^K\u00bd\u00d2\u00d1\u0003\u00a9hi\u00f7\u0095Z\u007f\u00f8\u0095\u00b42\u001ac\u00b9\u00c9\u001a@\u0081\u00bc\u00d4}?\u0012\u00d8\u009bS\u0085\u00b0\u001au\u0010\u00be\u00825+c \u0093\u0001i\u00a1\u00f0\u00b1J\u00e37\u0097 \u00f50\u0015\u007f\u00a1\u00fe\u00c6K{\u00d1O\u00e8W\u00bd\u001a\u00c26\u0095\u00db\u00ba\u00ecE\u000b\u00a5\u00c7\u00f2\u00c0OfP?\u00c50U\u00dc\u0098jO0\u009cy\\\u0014\u00b33\u00eaWlA\u00d7K\u00af\u00b3\r\u00f6v'\u00fbO\u0014\u00ff\u008a:\u0091\u00f8\u0011\u0015y-\u009b\u00ca[{\u0091\u009a\u00a4\u00df\u00e8\u00d39ZX\u009a$,#\u000f\u000f\u00cb\u0090\u00c1\u00c9\u0088|\u00db\u00ff\u008a\u00b5K\u0002\u00cc\\\u00db\u0089\u00b4\u00bb\u00ae@\u0094'\u00e2?\u00cc,x\u0089\u00d3\u00d0\u0085\u009d\u00e5h\n\u0006N\u00a3\u0080\u00d7Pq\t^X\u00a7@4\u0006'\u0083\u0093s\u00eb\u0007\u00dd\u00d8S\u000ek\u000e\u00b6\u00e0\u00dcn\u00c5\u00b4<V\u00b7c\tT\u00d4\u0086\u00d1S\u00f4t\u00b8p\u00f3\u0018jm\u001f\u00cf\u00ce\u00c6\u00f1\u00f9\u00fc]r\u0098\u0097\u00aa\u00ec9\u00a2o,\u00d1\u00a6\u00c0\u00db\u0003(\u00c1\u008172/l\f\u0090\u00ae\u00d5\u0088\u0090\u00b3\u00ceQ\u0098@,\u0087\u00ee.\u00a3\u0084\u00f0\u00a8G\u0090vH\u00d1\f\u0089\n\u00f6G\nd\u00ce\u008ad\u0018C\u00e8\u001f\u000f\u00f9\u0095\u00f8\u007f\t\u00f6\u00af\u0012W\u0096\u00e8\u00f0\u00e5\u0017\u00e2k\u00cf^{W";
                            var24_6 = 532;
                            var21_7 = 24;
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
                                var25_3[var23_4++] = SettingGroupElement.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u0095|Bo3\u00a8X\u00acR\u0087\u000f\u00ae\u0091\u00855\u00c3(\u00fe}I\u001a\u00dbJ\r\t\u0092\u00932L\u00f4C0\u00ac\u009d\u00b3U{UxU\u00cf\u000b\u0082\u0087\u00a0G\u00c2&\u0017\u00f3\u00ab\u0093\u009c\u00a7{\u00b4+";
                                var24_6 = 57;
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
                                var25_3[var23_4++] = SettingGroupElement.zkmDecodeUtf8(var26_9).intern();
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
                    SettingGroupElement.e = var25_3;
                    SettingGroupElement.f = new String[15];
                    SettingGroupElement.l = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var16 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var16 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[13];
                    var8_13 = 0;
                    var9_14 = "w\u00bbV\u00dc\"\u0094\u00bf\u00d8\t\u00a2[\u00e1\u000e\u0093\u0003c\u00c0,\u00f0ar\u0086Pw\u00bct;*Q\u00990\u008d<\u00ab\u00c6\b(:\u00c8\u00e2d\u00fa\u00e8\u0013FX^\u00fd\u0015PS\u0012l4J\u0092\u001euI\u0011\u0001&\u00ad\u00a8>\u00a6\u008eLa,\u00f3\u00de\u00d0gc\t\u0005\u00db\u00db\u0013\u001a9\u00b0*\u00fb\u00ab-C";
                    var10_15 = 88;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u001e\u00970\u00cd]\u00b6k\u00f8Z'\u0015\u008c\r\u00a8B\u00ca";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
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
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
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
            SettingGroupElement.h = var11_12;
            SettingGroupElement.k = new Integer[13];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var16 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var16 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl118:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-5, -9, -45, -80, -24, -30, -25, -34});
        ** while (true)
        SettingGroupElement.m = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x72F0;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/rf", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = e[n2].getBytes("ISO-8859-1");
            SettingGroupElement.f[n2] = SettingGroupElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingGroupElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x274;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])SettingGroupElement.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    SettingGroupElement.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/rf", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SettingGroupElement.k[n2] = n3;
        }
        return k[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SettingGroupElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/rf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingGroupElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SettingGroupElement.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

