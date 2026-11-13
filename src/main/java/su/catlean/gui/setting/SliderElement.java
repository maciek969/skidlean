/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
 *  org.lwjgl.glfw.GLFW
 */
package su.catlean.gui.setting;

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
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import org.lwjgl.glfw.GLFW;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.SettingElement;
import su.catlean.module.Module;
import su.catlean.render.Gradient2D;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.Setting;
import su.catlean.setting.SettingOverrideRegistry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.FpsCounter;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public abstract class SliderElement
extends SettingElement {
    @NotNull
    private final Setting setting;
    @NotNull
    private final Animation animation;
    private boolean dragging;
    private float animatedProgress;
    private boolean editing;
    @NotNull
    private String inputText;
    private static Module[] Y;
    private static final long d;
    private static final String[] e;
    private static final String[] f;
    private static final Map h;
    private static final long[] n;
    private static final Integer[] o;
    private static final Map p;
    private static final long q;

    public SliderElement(@NotNull Setting setting2) {
        Intrinsics.checkNotNullParameter(setting2, "setting");
        super(setting2);
        this.setting = setting2;
        this.setHeight(24.0f);
        this.animation = new Animation(Easing.OUT_QUINT, q);
        this.inputText = "-";
    }

    @Override
    @NotNull
    public Setting getSetting() {
        return this.setting;
    }

    public final boolean isDragging() {
        return this.dragging;
    }

    public final void setDragging(boolean bl) {
        this.dragging = bl;
    }

    public final float getAnimatedProgress() {
        return this.animatedProgress;
    }

    public final void setAnimatedProgress(float f) {
        this.animatedProgress = f;
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, long l, float f) {
        long l2 = l;
        long l3 = l2 ^ 0x55CDD452BBF9L;
        long l4 = l2 ^ 0x705C93731A18L;
        long l5 = l2 ^ 0x6C07DDB6D57L;
        long l6 = l2 ^ 0x68AE1B4E0484L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        FontRenderer fontRenderer = Fonts.getSF_M3();
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, this.getSetting().Q(), this.getX(), this.getY(), l6, Theme.INSTANCE.getTextColor(), false, null);
        this.animation.animate(this.dragging || this.isOverSlider(n, n2) ? 1.0f : 0.0f);
        this.animatedProgress = this.smooth(this.animatedProgress, this.getProgress(), 15.0f);
        if (this.isOverSlider(n, n2) && GLFW.glfwGetPlatform() != 393219) {
            GLFW.glfwSetCursor((long)Globals.getMc().getWindow().getHandle(), (long)GLFW.glfwCreateStandardCursor((int)221189));
            ClickGuiScreen.INSTANCE.A(false);
        }
        if (this.isOverValue(n, l4, n2) && GLFW.glfwGetPlatform() != 393219) {
            GLFW.glfwSetCursor((long)Globals.getMc().getWindow().getHandle(), (long)GLFW.glfwCreateStandardCursor((int)221186));
            ClickGuiScreen.INSTANCE.A(false);
        }
        this.drawSlider(l5, drawContext);
        this.drawValue(l3, drawContext);
        this.setFromMouse(n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isOverSlider(int n, int n2) {
        float f = this.getX() + 2.0f;
        float f2 = this.getX() + this.getWidth() - 9.0f;
        float f3 = n;
        if (!(f <= f3)) return false;
        if (!(f3 <= f2)) return false;
        boolean bl = true;
        if (!bl) return false;
        f = this.getY() + 9.0f;
        f2 = this.getY() + 21.0f;
        f3 = n2;
        if (!(f <= f3)) return false;
        if (!(f3 <= f2)) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean isOverValue(int n, long l, int n2) {
        long l2 = (l = d ^ l) ^ 0x1AB35BD8D84L;
        float f = Fonts.getSF_M().getWidth(l2, this.editing ? this.inputText : this.getSetting().getValue().toString());
        float f2 = this.getX() + this.getWidth() - f - 13.0f;
        float f3 = this.getX() + this.getWidth() - f - 13.0f + f + 6.0f;
        float f4 = n;
        if (!(f2 <= f4)) return false;
        if (!(f4 <= f3)) return false;
        boolean bl = true;
        if (!bl) return false;
        f2 = this.getY() + 1.0f;
        f3 = this.getY() + 11.0f;
        f4 = n2;
        if (!(f2 <= f4)) return false;
        if (!(f4 <= f3)) return false;
        return true;
    }

    private final void drawSlider(long l, DrawContext drawContext) {
        long l2 = l = d ^ l;
        long l3 = l2 ^ 0x4765D5B0F258L;
        long l4 = l2 ^ 0x198ACB5E474AL;
        int n = (int)(l4 >>> 48);
        int n2 = (int)(l4 << 16 >>> 48);
        int n3 = (int)(l4 << 32 >>> 32);
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n, this.getX(), this.getY() + 15.0f, this.getWidth() - 7.0f, 4.0f, 2.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        float f = this.getX();
        float f2 = this.getY() + 15.0f;
        Color color = Theme.INSTANCE.getSlidersColor();
        Color color2 = Theme.INSTANCE.getSlidersColor();
        Color color3 = Theme.INSTANCE.getSlidersColor();
        Color color4 = Theme.INSTANCE.getSlidersColor();
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Gradient2D.drawGradientRect(f, f2, l3, (this.getWidth() - 7.0f) * this.animatedProgress, 4.0f, 2.0f, color, color2, color3, color4, matrix3x2fStack2, 1.0f, Theme.INSTANCE.getModuleOutlineColor());
        float f3 = this.getX() - 2.0f + (this.getWidth() - 11.0f) * this.animatedProgress;
        float f4 = this.getY() + 15.0f - 1.5f;
        Color color5 = Theme.INSTANCE.getSlidersColor();
        Color color6 = Theme.INSTANCE.getSlidersColor();
        Color color7 = Theme.INSTANCE.getSlidersColor();
        Color color8 = Theme.INSTANCE.getSlidersColor();
        Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
        Gradient2D.drawGradientRect(f3, f4, l3, 7.0f, 7.0f, 2.5f, color5, color6, color7, color8, matrix3x2fStack3, 1.0f + this.animation.getValue(), Theme.INSTANCE.getModuleOutlineColor());
    }

    private final void drawValue(long l, DrawContext drawContext) {
        long l2 = l = d ^ l;
        long l3 = l2 ^ 0x4A8762D791E4L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x243A729C2C65L;
        long l5 = l2 ^ 0x2B0ACEB43D2BL;
        long l6 = l2 ^ 0x6F539A041F98L;
        int n4 = (int)(l6 >>> 32);
        int n5 = (int)(l6 << 32 >>> 48);
        int n6 = (int)(l6 << 48 >>> 48);
        String string = this.editing ? this.inputText : this.getSetting().getValue().toString();
        Number number = (Number)this.getSetting().getValue();
        String string2 = this.getSetting().getId();
        String string3 = SettingOverrideRegistry.INSTANCE.register(n4, string2, number, string, (char)n5, (short)n6);
        float f = Fonts.getSF_M().getWidth(l4, string3);
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n, this.getX() + this.getWidth() - f - 13.0f, this.getY() + 1.0f, f + 6.0f, 10.0f, 3.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        float f2 = this.editing ? (float)Math.sin((double)System.currentTimeMillis() / 50.0) + 0.5f : 1.0f;
        Color color = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f2);
        float f3 = this.getY() + 0.5f;
        float f4 = this.getX() + this.getWidth() - 10.0f - f;
        Fonts.getSF_M().drawStringWithShadow(drawContext, string3, l5, f4, f3, color);
    }

    public abstract void setFromMouse(int var1);

    public abstract float getProgress();

    @Override
    public void mouseClicked(long l, double d, double d2, int n) {
        long l2 = l ^ 0x7D88BC9C6A41L;
        float f = Fonts.getSF_M().getWidth(l2, this.getSetting().getValue().toString());
        if (n == 0 && this.isOverSlider((int)d, (int)d2)) {
            this.dragging = true;
        }
        if (n == 0) {
            float f2 = this.getX() + this.getWidth() - f - 13.0f;
            boolean bl = d <= (double)(this.getX() + this.getWidth() - 7.0f) ? (double)f2 <= d : false;
            if (bl) {
                f2 = this.getY() + 1.0f;
                boolean bl2 = d2 <= (double)(this.getY() + 11.0f) ? (double)f2 <= d2 : false;
                if (bl2) {
                    this.editing = !this.editing;
                    this.inputText = this.getSetting().getValue().toString();
                }
            }
        }
    }

    @Override
    public void mouseReleased(double d, double d2) {
        this.dragging = false;
    }

    @Override
    public void keyPressed(int n, short s, int n2, char c) {
        if (this.editing) {
            switch (n) {
                case 257: {
                    this.editing = false;
                    try {
                        String string = this.inputText;
                        this.getSetting().setValue(this.parseNumber(string));
                    }
                    catch (Exception exception) {}
                    break;
                }
                case 259: {
                    if (!(((CharSequence)this.inputText).length() > 0)) break;
                    this.inputText = StringsKt.dropLast(this.inputText, 1);
                    break;
                }
            }
        }
    }

    @Override
    public void charTyped(char c) {
        if (this.editing) {
            this.inputText = this.inputText + c;
        }
    }

    @Override
    public boolean mouseScrolled(double d, double d2, double d3) {
        if (this.isOverSlider((int)d, (int)d2)) {
            int n = (int)d3;
            Number number = (Number)this.getSetting().getValue();
            this.getSetting().setValue(this.round(number, n));
            return true;
        }
        return false;
    }

    @NotNull
    public final IntRange getIntRange() {
        Setting setting2 = this.getSetting();
        Intrinsics.checkNotNull(setting2, "null cannot be cast to non-null type su.catlean.api.setting.settings.IntSetting");
        return ((IntSetting)setting2).getRange();
    }

    @NotNull
    public final ClosedFloatingPointRange getFloatRange() {
        Setting setting2 = this.getSetting();
        Intrinsics.checkNotNull(setting2, "null cannot be cast to non-null type su.catlean.api.setting.settings.FloatSetting");
        return ((FloatSetting)setting2).getRange();
    }

    @NotNull
    public abstract Number parseNumber(@NotNull String var1);

    @NotNull
    public abstract Number round(@NotNull Number var1, int var2);

    private final float smooth(float f, float f2, float f3) {
        float f4 = Math.clamp((FpsCounter.INSTANCE.D() > 5 ? 1.0f / (float)FpsCounter.INSTANCE.D() : 0.016f) * f3, 0.0f, 1.0f);
        return (1.0f - f4) * f + f4 * f2;
    }

    @Override
    public void close() {
        this.dragging = false;
        this.editing = false;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        Y = moduleArray;
    }

    public static Module[] su_catlean_module_Module_arr_zkmGetFlag() {
        return Y;
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
                            SliderElement.d = ZkmKeys.a(-6044693024914837947L, 4109558334446878684L, MethodHandles.lookup().lookupClass()).a(164732883170410L);
                            var25 = SliderElement.d ^ 102829605927243L;
                            SliderElement.h = new HashMap<K, V>(13);
                            SliderElement.zkmSetFlag(new Module[3]);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[6];
                            var21_4 = 0;
                            var20_5 = "\u00b8\u00e7c\u000b\u00b3\u0097\u00dbr|\u00e8~g\u00cd\u009e\u00ac\u0001E0\u0002Fb\u00f02\u00b0&\u00a1(J\u0000\u00ef\u00e6\u00c6\u0010\u00d3\u0093\u009f\u0091\u00b3\u00dc\u00ac\u00c1\u00e6\u0018\u00ac\u00cd2\u00b9,u\u0088\u00a2\f\u00f4\u00ac\f\u00b6=\f\u001c\u00aa;\u00b8\u00fd\u0091k+\u00f62\u00c8\u009b\u0081\u00d8VEsYV\u00ed\u00c1u\u00eb#\u00cf\u00b5\u0003\u0000\u0082U\u00b1\u0019\u00b3\u00cc[E?\u00b4\u009d\u00af\u0097\u00cc\u000fR\u007fG3'\u00f4\u00ad\u00bein\u00a9\u00c6\u00b8\u00d1\u00b0\u00e0\u00dc\u00bc\b\r\u0088H\u00d8\u00bc\u0093\u0081\u0015\u0011La\u00ec\u00e6\u0096\u0000\u00b2\u001d\u0092\u00e8\u00f8Mk\u0005\u009aO\u00cd\u00fb\u0014\u00fd\u00e2\u001e\u0004\u0002\u0018\u0017+\u009c\u00ac\u00ab<\u00c3\u00ed|M4/D\u0004\u0015\u0012\u008dDe\u0095NH\u0011\u00fa\u00d8?\u00de\u00c3.\u00b3\u00de\u0089\u0010w\u00fe\u008cT\u0007\u00a1kv\u00e1E%%OB\u008f~";
                            var22_6 = 203;
                            var19_7 = 32;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = SliderElement.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u00a2\u00c5HG\u00f8\u00d3\u0010\u008e\u0012;\u00db#\u00cd\u00c7\u0099\u00c8%\u00e2\u00df\u00c8\u0016\u00a5\u00df\u0096\u0098\u00fd\u00e4'H\u00ca_L\u00f7 >\u00b2S\u0080\"\u00ad\\\u009b\u00ef\u00b5f\u00fe|\u00d4u\u00a2(\u00fa\u00a9Z/\u00e7s\u00cfLM\u00ad\u00c1\u00d9\u00delX\u00da\u00a7T\u00ed\u00be9\u0097\u0091\u00d4\u00a3\u00c2\u001eS\u00a8|\u008a)\u00a0\u0099\u00a2s-\u00e1\u00ec\u008c\\\u009caF\u00df\u00ae#|\u00ab\u00b3\u0004~\u00d8P\u00da\u00cc\u0090G\u0099\u00c1)\u00e9\u00a1q\u001d\u0087A\u00e1\u00a5\u0096\u0093\u00d5#\u00f0@T\u0084\u0018\u008a}\u0082\u009bnt\u00daG\"&\u00c8\u00dd\u00a9'\u001b\u00e7\u001e\u00b0\u00fc]\u00eb\u00c70j";
                                var22_6 = 153;
                                var19_7 = 128;
                                var18_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = SliderElement.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    SliderElement.e = var23_3;
                    SliderElement.f = new String[6];
                    SliderElement.p = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[10];
                    var8_13 = 0;
                    var9_14 = "\u00ea\u00b9\u0082\u009b\u00cc\u00b6\u00a8\u0092\u00c9Er\u001e<\u0085(\u00bfK\u0091X/\u00e8a\u00ec\u0087\u0007\t`<\u00cba/\u001a]#i\u000e\u00d7\u0007\u0017\u00eb\u00b0o\u00adt\u00e8;\u009e\u0007sx%\u00c6<\u009b\u00f2$\u00a5\u00cc\u0011\u00cf\u00f9z\u00b3^";
                    var10_15 = 64;
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
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u00d8\u0094\u0019\u00f2\u00f73\u0018\u0016\u00b4\u0004Q\u00b8\u00d2sM\u000e";
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
lbl92:
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
lbl105:
                    // 1 sources

                    ** continue;
                }
            }
            SliderElement.n = var11_12;
            SliderElement.o = new Integer[10];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{48, -6, 108, 43, -81, -30, 29, 82});
        ** while (true)
        SliderElement.q = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5C78;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/r2", exception);
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
            SliderElement.f[n2] = SliderElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SliderElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/r2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3A6A;
        if (o[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = SliderElement.n[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])p.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    p.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/r2", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SliderElement.o[n2] = n3;
        }
        return o[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SliderElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/r2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SliderElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SliderElement.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

