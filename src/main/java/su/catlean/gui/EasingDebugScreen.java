/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.render.RenderUtil;
import su.catlean.render.font.Fonts;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class EasingDebugScreen
extends Screen {
    private final float panelWidth;
    private final float padding;
    @NotNull
    private Easing easing = Easing.LINEAR;
    private long duration = l;
    private boolean a = true;
    private boolean pingPong = true;
    private int samples = 120;
    @NotNull
    private final Color g = new Color(-15724528);
    @NotNull
    private final Color V = new Color(-15329770);
    @NotNull
    private final Color P = new Color(-1);
    @NotNull
    private final Color T = new Color(-5197648);
    @NotNull
    private final Color e = new Color(-14013910);
    @NotNull
    private final Color v = new Color(-14737633);
    @NotNull
    private final Color t = new Color(-13882324);
    private long startTime;
    @NotNull
    private final List easings = CollectionsKt.toList(Easing.get$ENTRIES());
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map h;
    private static final long[] i;
    private static final Integer[] j;
    private static final Map k;
    private static final long l;

    public EasingDebugScreen() {
        super(Text.of("test"));
        this.panelWidth = 210.0f;
        this.padding = 8.0f;
    }

    @NotNull
    public final Easing getEasing() {
        return this.easing;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final void setDuration(long l) {
        this.duration = l;
    }

    public final boolean a() {
        return this.a;
    }

    public final void t(boolean bl) {
        this.a = bl;
    }

    public final boolean isPingPong() {
        return this.pingPong;
    }

    public final void setPingPong(boolean bl) {
        this.pingPong = bl;
    }

    public final int getSamples() {
        return this.samples;
    }

    public final void setSamples(int n) {
        this.samples = n;
    }

    @NotNull
    public final Color l() {
        return this.g;
    }

    @NotNull
    public final Color O() {
        return this.V;
    }

    @NotNull
    public final Color P() {
        return this.P;
    }

    @NotNull
    public final Color j() {
        return this.T;
    }

    @NotNull
    public final Color c() {
        return this.e;
    }

    @NotNull
    public final Color m() {
        return this.v;
    }

    @NotNull
    public final Color v() {
        return this.t;
    }

    @Override
    protected void applyBlur(@NotNull DrawContext drawContext) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    @Override
    public void renderBackground(@NotNull DrawContext drawContext, int n, int n2, float f) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, float f) {
        long l;
        long l2 = l = b ^ 0x586422349ADAL;
        long l3 = l2 ^ 0x153CE1126368L;
        long l4 = l2 ^ 0x71C234D6C12DL;
        long l5 = l2 ^ 0x1D760DFA73B0L;
        long l6 = l2 ^ 0x679357BB3ED6L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        this.startTime = Globals.now();
        if (!this.a) {
            this.startTime = Math.min(this.startTime, this.duration);
        }
        float f2 = Globals.getMc().getWindow().getScaledWidth();
        float f3 = Globals.getMc().getWindow().getScaledHeight();
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Matrix3x2fStack matrix3x2fStack2 = matrix3x2fStack;
        Color[] colorArray = new Color[]{this.g};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, 0.0f, 0.0f, f2, f3, colorArray, l5);
        colorArray = new Color[]{this.V};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, 0.0f, 0.0f, this.panelWidth, f3, colorArray, l5);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "EASING DEBUG", l3, this.padding, this.padding, this.P);
        float f4 = this.padding + 18.0f;
        Fonts.getSF_M().drawStringWithShadow(drawContext, "duration: " + this.duration + "ms", l3, this.padding, f4, this.T);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "loop: " + this.a, l3, this.padding, f4 += 14.0f, this.T);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "pingPong: " + this.pingPong, l3, this.padding, f4 += 14.0f, this.T);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "samples: " + this.samples, l3, this.padding, f4 += 14.0f, this.T);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "Easings:", l3, this.padding, f4 += 18.0f, this.P);
        f4 += 14.0f;
        float f5 = 14.0f;
        Iterator iterator2 = ((Iterable)this.easings).iterator();
        int n3 = 0;
        while (iterator2.hasNext()) {
            int n4 = n3++;
            Easing easing = (Easing)((Object)iterator2.next());
            boolean bl = easing == this.easing;
            float f6 = f4 + (float)n4 * f5;
            if (f6 + f5 > f3 - this.padding) break;
            if (bl) {
                Color[] colorArray2 = new Color[]{this.e};
                RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, 2.0f, f6 - 1.0f, this.panelWidth - 4.0f, f5, colorArray2, l5);
            }
            Color color = bl ? this.P : this.T;
            float f7 = f6;
            float f8 = this.padding;
            Fonts.getSF_M().drawStringWithShadow(drawContext, easing.name(), l3, f8, f7, color);
        }
        float f9 = this.panelWidth + this.padding;
        float f10 = f2 - f9 - this.padding;
        float f11 = f3 - this.padding - this.padding;
        float f12 = f11 * 0.62f;
        float f13 = f11 - f12 - this.padding;
        this.drawGraph(drawContext, f9, this.padding, l4, f10, f12);
        this.drawPreview(drawContext, l6, f9, this.padding + f12 + this.padding, f10, f13);
    }

    private final void drawGraph(DrawContext drawContext, float f, float f2, long l, float f3, float f4) {
        float f5;
        float f6;
        int n;
        long l2 = l = b ^ l;
        long l3 = l2 ^ 0x6A13EE3056DBL;
        long l4 = l2 ^ 0x652352184795L;
        long l5 = l2 ^ 0xA1D7728E116L;
        long l6 = l5 >>> 32;
        int n2 = (int)(l5 << 32 >>> 32);
        long l7 = l2 ^ 0x625902D84603L;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Matrix3x2fStack matrix3x2fStack2 = matrix3x2fStack;
        Color[] colorArray = new Color[]{new Color(-15461356)};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f, f2, f3, f4, colorArray, l7);
        colorArray = new Color[]{this.t};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f, f2, f3, 1.0f, colorArray, l7);
        colorArray = new Color[]{this.t};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f, f2 + f4 - 1.0f, f3, 1.0f, colorArray, l7);
        colorArray = new Color[]{this.t};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f, f2, 1.0f, f4, colorArray, l7);
        colorArray = new Color[]{this.t};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f + f3 - 1.0f, f2, 1.0f, f4, colorArray, l7);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "Curve: " + this.easing.name(), l3, f + this.padding, f2 + this.padding, this.P);
        float f7 = f + this.padding;
        float f8 = f2 + this.padding + 16.0f;
        float f9 = f3 - this.padding * 2.0f;
        float f10 = f4 - (this.padding * 2.0f + 16.0f);
        for (n = 0; n < 11; ++n) {
            f6 = f7 + f9 * ((float)n / 10.0f);
            f5 = f8 + f10 * ((float)n / 10.0f);
            RenderUtil.drawGradientRect$default(l6, RenderUtil.INSTANCE, matrix3x2fStack2, f6, f8, n2, f6, f8 + f10, this.v, null, 64, null);
            RenderUtil.drawGradientRect$default(l6, RenderUtil.INSTANCE, matrix3x2fStack2, f7, f5, n2, f7 + f9, f5, this.v, null, 64, null);
        }
        RenderUtil.drawGradientRect$default(l6, RenderUtil.INSTANCE, matrix3x2fStack2, f7, f8 + f10, n2, f7 + f9, f8 + f10, new Color(-12961222), null, 64, null);
        RenderUtil.drawGradientRect$default(l6, RenderUtil.INSTANCE, matrix3x2fStack2, f7, f8, n2, f7, f8 + f10, new Color(-12961222), null, 64, null);
        n = Math.max(16, this.samples);
        f6 = f7;
        f5 = f8 + f10;
        int n3 = 0;
        if (n3 <= n) {
            while (true) {
                double d = (double)n3 / (double)n;
                double d2 = this.m(((Number)this.easing.a().invoke(d)).doubleValue());
                float f11 = f7 + (float)(d * (double)f9);
                float f12 = f8 + (float)((1.0 - d2) * (double)f10);
                if (n3 > 0) {
                    RenderUtil.drawGradientRect$default(l6, RenderUtil.INSTANCE, matrix3x2fStack2, f6, f5, n2, f11, f12, new Color(-7613441), null, 64, null);
                }
                f6 = f11;
                f5 = f12;
                if (n3 == n) break;
                ++n3;
            }
        }
        Fonts.getSF_M().drawStringWithShadow(drawContext, "0.0", l3, f7, f8 + f10 + 2.0f, new Color(-8355712));
        float f13 = Fonts.getSF_M().getWidth(l4, "1.0");
        Fonts.getSF_M().drawStringWithShadow(drawContext, "1.0", l3, f7 + f9 - f13, f8 + f10 + 2.0f, new Color(-8355712));
    }

    private final void drawPreview(DrawContext drawContext, long l, float f, float f2, float f3, float f4) {
        double d;
        double d2;
        long l2 = l = b ^ l;
        long l3 = l2 ^ 0x7C428D5DA920L;
        long l4 = l2 ^ 0x1C4C14451EEDL;
        long l5 = l4 >>> 32;
        int n = (int)(l4 << 32 >>> 32);
        long l6 = l2 ^ 0x740861B5B9F8L;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Matrix3x2fStack matrix3x2fStack2 = matrix3x2fStack;
        Color[] colorArray = new Color[]{new Color(-15461356)};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f, f2, f3, f4, colorArray, l6);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "Preview (loop)", l3, f + this.padding, f2 + this.padding, this.P);
        float f5 = f + this.padding;
        float f6 = f2 + f4 / 2.0f;
        float f7 = f3 - this.padding * 2.0f;
        RenderUtil.drawGradientRect$default(l5, RenderUtil.INSTANCE, matrix3x2fStack2, f5, f6, n, f5 + f7, f6, new Color(-12961222), null, 64, null);
        double d3 = d2 = this.duration <= 0L ? 1.0 : (double)(this.startTime % this.duration) / (double)this.duration;
        double d4 = !this.pingPong ? d2 : ((d = d2 * 2.0) <= 1.0 ? d : 2.0 - d);
        d = this.m(((Number)this.easing.a().invoke(d4)).doubleValue());
        float f8 = f5 + (float)(d * (double)f7);
        Color[] colorArray2 = new Color[]{new Color(-11930)};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, f8 - 1.0f, f6 - 1.0f, 2.0f, 2.0f, colorArray2, l6);
        Fonts.getSF_M().drawStringWithShadow(drawContext, "t=" + this.format(d4) + "  eased=" + this.format(d), l3, f + this.padding, f2 + f4 - this.padding - 12.0f, this.T);
    }

    @Override
    public boolean mouseClicked(@NotNull Click click, boolean bl) {
        block1: {
            Intrinsics.checkNotNullParameter(click, "click");
            int n = (int)click.comp_4798();
            int n2 = (int)click.comp_4799();
            int n3 = (int)this.padding + 18 + 56 + 18;
            int n4 = 14;
            boolean bl2 = 0 <= n ? n < (int)this.panelWidth : false;
            if (!bl2 || n2 < n3) break block1;
            int n5 = (n2 - n3) / n4;
            boolean bl3 = 0 <= n5 ? n5 < ((Collection)this.easings).size() : false;
            if (bl3) {
                this.easing = (Easing)((Object)this.easings.get(n5));
            }
        }
        return super.mouseClicked(click, bl);
    }

    private final double m(double d) {
        return d;
    }

    private final String format(double d) {
        return String.valueOf((double)MathKt.roundToInt(d * 1000.0) / 1000.0);
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
                            EasingDebugScreen.b = ZkmKeys.a(-5064414432388862209L, -1955017227140488468L, MethodHandles.lookup().lookupClass()).a(81121127181867L);
                            EasingDebugScreen.h = new HashMap<K, V>(13);
                            var16 = EasingDebugScreen.b ^ 82303988061871L;
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
                            var25_3 = new String[20];
                            var23_4 = 0;
                            var22_5 = "88\u00b2\u000b\u00fa\u00c0\u00b5\n\u001f.C\u0098J-\u00ab\u00c8\u0010\u00d3\u00f7\u001c\u00a5\u00bc\u00c3`>/\u00f3\u00b8\u00b2\u00e8\b\u0010\u001a \u00c3\u00d9`e0{d\u0094\u00d3mt\u00a6\u0086Ok,\u00c4\u0011\u00de\u00e4\u0083E\u00d2\u007f\u0085W\u00be\u0087m\u00ab\u0015\u009a\u0010\u00f4\u00d8\"\u009aT\u0019\u00c8P\u009fU\u00e0\u0095y\u007f\u00e3\u00e6\u0010\u00a5T\u00ec\u000fo\u000eG\u00e0\u0010J\u00a4\u00d3@x\u00f7!\u0010P\u00b0\u0014N*\u00c1\u00a1m\u0093\u00144\u00b8\u00adu\u00a30 \u00b5\u0084\u00c6\u00a8\u00c9\u00fe\u00eb\u008f\u00cb\u00bb\f\u0017[\u001d\u009bo\u0082\u00ec\u00eelQ\u00c2S\u000b{O!f\u00cb\u00cf\u00f7d\u0010\u0010_a\u00d1\u0013\u008e_\u00c0M\u0000\\\u009eX\u00f2VD\u0018\u00d6v\u0082\u0011\u00fc@\u00fc\u0081\u00bd|H/&4*\u00ad\u00b8\u00e0\u0091\u00c2\u00e4t\u000e6\u0018\u00dd\u00d38r!\u009b|\u001cuhA\u00f7\u0090\u00f4\u008e\u00e1\u0003\u00b7\u0006\u0080\u00a5_,\u00ef\u0018\u00b8\u0093\u00cb\u00f7\u00ad\u008cnGa\u00f1sS\u0013\u0084\u009a\u00d4\u00edjP\u00ab\u00f5\u009e\u009b\u00f7\u0010<\u00b7h&\u00d3\u00cdb\u001e(\u00ac~\u009e\u00c4\u0012S>\u0010F\u00b2K\u00dbx\n\u00a9\u00a5\u00ae\u00b2F6\u0091 _\u00b0 \u00a5\u00ff\u00aaf\u000f\u0014\u00f8\u00d4\u00b8\u00a9\u00c5\u00d2{\u00b6\u0092Tb\u00e3\u00c1\u0010\u0080\u00d0\u00d6\u00a0\u00ef~\u00bc\u00e7o\u00814%\u0018\u00fe\u0093V8ed\u00a3ZN\b\u00dew\u00d6\u00f0\u001e\u00a9\u00d51\u0096\u00bb\u009fM\u00db\u00d6 o\u0000\u00bb\u0091v\u0082G{\u00f0*$\u00df&~a\u0001'\u00ec\u009c\u00d0\u0007\u00f8WF\u00d1/\u00d75\u0085\u00e6\u00c5\u0014\u0010\u00f4{\u0019\u0085\u00a0\u00f4\u00f3\u00b5V\u00bf$\bM\u00b7w\u0002\u0010\u00ff\t\u0004\u00f3H\u008cZD\u00a60kHa\u008a#\u00a4";
                            var24_6 = 401;
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
                                var25_3[var23_4++] = EasingDebugScreen.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00c6\u00f6\u009ev\r\u00d1\u0098\u0095lF\u001f\u00c0G~w\u00d8 \u0090\u00fb&\u00a3GGL\u00b6\u00bc#\u00ad\u0015\u00e2\u00d6\u00ba\u0018p\u00e2\u00f9,5\u00ab\u001d\u00e7s\u00bf\u0014\u0016\u0081\u00d5U*";
                                var24_6 = 49;
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
                                var25_3[var23_4++] = EasingDebugScreen.zkmDecodeUtf8(var26_9).intern();
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
                    EasingDebugScreen.c = var25_3;
                    EasingDebugScreen.d = new String[20];
                    EasingDebugScreen.k = new HashMap<K, V>(13);
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
                    var11_12 = new long[26];
                    var8_13 = 0;
                    var9_14 = "\u00f90z\u007f]\u00e0H\u00c38Z\u008c\u00fe\u0089\u00fa\u00d5\u00a6\u001e\u0000\u0086R\u000e$\u00da\u001c\u009cC0w\u00c2\u00e9\u00d1\u00a9\u001cm\u00f0#\u00b6\u008f\u00b2\u00a6\u00c9\u00fb\u00f9-\u0002\u00b0\u009c\u0003a\u00a6\u000e\u00bf\u00ca\u00f6\u00bd\u00fa\n\u00be,)~V\u00ae\u000b\u008b\u0017\n\u00f0\u00cc\u00b2\\\u00cb\u00b9\\6\u00eaT%rq\u0085\u0010\u00cd\u00db\u009f\u00ac\u008f\u0015\u00f5f\u00c2\u00d6\u00a9r\u00ea\u00c1\u00dc\u00f9\u00fa~\u00ae\u008b\u00d4c\u00b7#\u00b4a#6\u00e2De\u001b^B\u00c8\u00b3\u0094\u00b8O\u00c69\u0081c\u0098\u00b1'\t\u0018\n\u00f9\n]S-X\u00b8+\u00d7\u00b7\u008a@M\u0092\u00f25b\u00e9M \u00fe\u008e\u00a9\u00a6m1\u00eakL\"\u009d\u00e5oj8\u0097\u00a9\u00dc\u0081@\u001b\u0097\u00bc\u00ef<H\u009f,*\u00a1A\u0080v\u00d2\u00b2\u0017\u00ef\u00f7g\u0003\u00ad";
                    var10_15 = 192;
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
                        var9_14 = "U\u00bb\u00cb\u0004\u00e7\u0091\u0083\u00b9\u00dcvs\u00bb]>\u00f2\u00cf";
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
            EasingDebugScreen.i = var11_12;
            EasingDebugScreen.j = new Integer[26];
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
        var4_22 = var0_20.doFinal(new byte[]{-42, -117, 22, -85, -79, -35, -61, 53});
        ** while (true)
        EasingDebugScreen.l = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7609;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/tf", exception);
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
            EasingDebugScreen.d[n2] = EasingDebugScreen.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = EasingDebugScreen.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/tf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x57E5;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
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
                throw new RuntimeException("su/catlean/tf", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            EasingDebugScreen.j[n2] = n3;
        }
        return j[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = EasingDebugScreen.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/tf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(EasingDebugScreen.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(EasingDebugScreen.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

