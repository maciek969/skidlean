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
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.gui.Button;
import su.catlean.gui.Element;
import su.catlean.gui.GuiElement;
import su.catlean.gui.ProxyElement;
import su.catlean.gui.ProxyPanel$hoveringComponent$1;
import su.catlean.gui.ProxyPanel$hoveringComponent$2;
import su.catlean.gui.SidePanel;
import su.catlean.gui.SwitchElement;
import su.catlean.gui.TextField;
import su.catlean.gui.TextInput;
import su.catlean.render.Gradient2D;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Proxy;
import su.catlean.util.Proxy$socks4$1;
import su.catlean.util.ProxyManager;
import su.catlean.zkm.ZkmKeys;

public final class ProxyPanel
extends SidePanel {
    @NotNull
    public static final ProxyPanel INSTANCE;
    @NotNull
    private static final List proxyElements;
    @Nullable
    private static Element hoveredElement;
    @Nullable
    private static Element t;
    @Nullable
    private static Element focusedElement;
    @Nullable
    private static ProxyElement proxyElement;
    @NotNull
    private static Animation w;
    @NotNull
    private static Animation F;
    @NotNull
    private static Animation S;
    private static boolean s;
    private static float scroll;
    @NotNull
    private static final TextField idField;
    @NotNull
    private static final TextField ipField;
    @NotNull
    private static final TextField portField;
    @NotNull
    private static final TextField userField;
    @NotNull
    private static final TextField passwordField;
    private static String v;
    private static final long b;
    private static final String[] e;
    private static final String[] j;
    private static final Map m;
    private static final long[] n;
    private static final Integer[] q;
    private static final Map y;

    private ProxyPanel() {
        super("proxy-panel", 0.0f, 0.0f, 0.0f, 0.0f, null);
    }

    @NotNull
    public final List getProxyElements() {
        return proxyElements;
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

    @Nullable
    public final ProxyElement getProxyElement() {
        return proxyElement;
    }

    public final void setProxyElement(@Nullable ProxyElement proxyElement) {
        ProxyPanel.proxyElement = proxyElement;
    }

    @NotNull
    public final Animation E() {
        return w;
    }

    public final void G(@NotNull Animation animation) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ b;
        Intrinsics.checkNotNullParameter(animation, (String)((Object)ProxyPanel.zkmBootstrap("k", (int)4726, (long)(0x70A4AC2445FCD941L ^ l2))));
        w = animation;
    }

    @Override
    public void refresh() {
        proxyElements.clear();
        Iterable iterable = ProxyManager.INSTANCE.getProxies();
        for (Object t : iterable) {
            Proxy proxy = (Proxy)t;
            proxyElements.add(new ProxyElement(proxy));
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4) {
        block4: {
            v0 = var4_4;
            var11_5 = v0 ^ 136208856317167L;
            var17_6 = v0 ^ 136208856317167L;
            var19_7 = v0 ^ 129382754651428L;
            v1 = v0 ^ 22135879901675L;
            var26_8 = (int)(v1 >>> 48);
            var27_9 = (int)(v1 << 16 >>> 48);
            var28_10 = (int)(v1 << 32 >>> 32);
            var29_11 = v0 ^ 136208856317167L;
            var31_12 = v0 ^ 27182768661316L;
            var33_13 = v0 ^ 0L;
            var35_14 = v0 ^ 97910750751556L;
            var45_15 = v0 ^ 136208856317167L;
            Intrinsics.checkNotNullParameter(var1_1, "context");
            super.render(var1_1, var2_2, var3_3, var33_13);
            ProxyPanel.w.animate(1.0f);
            ProxyPanel.F.animate(this.getGuiElement() != null ? 0.5f : 0.0f);
            ProxyPanel.S.animate(ProxyPanel.scroll);
            var63_16 = this.getX() + 3.0f;
            var64_17 = this.getX() + 72.0f;
            var65_20 = var2_2;
            v2 = var63_16 <= var65_20 ? var65_20 <= var64_17 : false;
            if (!v2) ** GOTO lbl-1000
            var63_16 = this.getY() + 5.0f;
            var64_17 = this.getY() + 13.0f;
            var65_20 = var3_3;
            v3 = var63_16 <= var65_20 ? var65_20 <= var64_17 : false;
            if (v3) {
                v4 = true;
            } else lbl-1000:
            // 2 sources

            {
                v4 = false;
            }
            ProxyPanel.s = v4;
            v5 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
            var47_22 = null;
            var48_23 = 14336;
            var49_24 = 0.0f;
            var50_25 = 0.0f;
            var51_26 = 0.0f;
            var52_27 = Theme.INSTANCE.getOutlineColor();
            var53_28 = Theme.INSTANCE.g();
            var54_29 = 1.0f;
            var55_30 = ProxyPanel.s != false ? 0.1f : 0.0f;
            var56_31 = ProxyPanel.s != false ? 2.5f : 2.0f;
            var57_32 = 2.0f;
            var58_33 = 8.0f;
            var59_34 = 69.0f;
            var60_35 = this.getY() + 3.0f;
            var61_36 = this.getX() + 5.0f;
            Shapes2D.drawShape$default(v5, (char)var26_8, var61_36, var60_35, var59_34, var58_33, var57_32, var56_31, var55_30, var54_29, var53_28, var52_27, var51_26, (short)var27_9, var50_25, var28_10, var49_24, var48_23, var47_22);
            v6 = Fonts.getSF_M();
            v7 = this.getY() + 1.0f;
            v8 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v8, "WHITE");
            v6.drawStringWithShadow(var1_1, "+", var19_7, this.getX() + 36.0f, v7, v8);
            v9 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v9, "pose(...)");
            Shapes2D.drawShape$default(v9, (char)var26_8, this.getX() + 76.0f, this.getY(), 6.0f, this.getHeight(), 1.0f, 0.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var27_9, 0.0f, var28_10, 0.0f, 14336, null);
            ProxyPanel.focusedElement = null;
            ProxyPanel.hoveredElement = null;
            var63_16 = 17.0f + ProxyPanel.S.getValue();
            RenderUtil.INSTANCE.beginScissor(var1_1, new Rect(this.getX(), this.getY() + 14.0f, this.getX() + 76.0f, this.getY() + this.getHeight() - 2.0f));
            var64_18 = ProxyPanel.proxyElements;
            for (T var67_38 : var64_18) {
                var68_39 = (ProxyElement)var67_38;
                var68_39.render(var29_11, var1_1, ProxyPanel.INSTANCE.getX(), ProxyPanel.INSTANCE.getY() + var63_16, var2_2, var3_3);
                var63_16 += 29.0f;
            }
            RenderUtil.INSTANCE.endScissor(var1_1);
            if (ProxyPanel.proxyElement != null) {
                var64_19 = (1.0f - ProxyPanel.w.getValue()) * 10.0f;
                var65_21 = (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, removeSelected(), ()Lkotlin/Unit;)();
                ProxyPanel.idField.render(var11_5, var1_1, this.getX() + 84.0f, this.getY() + 17.0f + var64_19, var2_2, var3_3);
                ProxyPanel.ipField.render(var11_5, var1_1, this.getX() + 84.0f, this.getY() + 32.0f + var64_19, var2_2, var3_3);
                ProxyPanel.portField.render(var11_5, var1_1, this.getX() + 84.0f, this.getY() + 47.0f + var64_19, var2_2, var3_3);
                ProxyPanel.userField.render(var11_5, var1_1, this.getX() + 84.0f, this.getY() + 62.0f + var64_19, var2_2, var3_3);
                ProxyPanel.passwordField.render(var11_5, var1_1, this.getX() + 84.0f, this.getY() + 77.0f + var64_19, var2_2, var3_3);
                v10 = this.getWidth();
                v11 = ProxyPanel.proxyElement;
                Intrinsics.checkNotNull(v11);
                var69_40 = new ProxyPanel$hoveringComponent$1(this);
                var70_41 = new Proxy$socks4$1(v11.getProxy());
                var71_42 = "Socks4";
                var72_43 = "Socks5";
                new SwitchElement(v10, var72_43, var71_42, var70_41, var69_40).render(var45_15, var1_1, this.getX() + 84.0f, this.getY() + 92.0f + var64_19, var2_2, var3_3);
                var76_44 = new ProxyPanel$hoveringComponent$2(this);
                var77_45 = this.getWidth();
                new Button(var65_21, var77_45, var76_44).render(var17_6, var1_1, this.getX() + 84.0f, this.getY() + 107.0f + var64_19, var2_2, var3_3);
            }
            v12 = this.getX();
            v13 = this.getY();
            v14 = this.getWidth();
            v15 = this.getHeight();
            v16 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v16, "BLACK");
            var81_46 = ProxyPanel.F.getValue();
            v17 = RenderUtil.INSTANCE.withAlpha(v16, var81_46);
            v18 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v18, "BLACK");
            var85_47 = ProxyPanel.F.getValue();
            v19 = RenderUtil.INSTANCE.withAlpha(v18, var85_47);
            v20 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v20, "BLACK");
            var89_48 = ProxyPanel.F.getValue();
            v21 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v21, "BLACK");
            var93_49 = ProxyPanel.F.getValue();
            v22 = RenderUtil.INSTANCE.withAlpha(v21, var93_49);
            v23 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v23, "pose(...)");
            Gradient2D.E(v12, v13, v14, v15, 5.0f, v17, v19, var35_14, RenderUtil.INSTANCE.withAlpha(v20, var89_48), v22, v23, 0.0f, null, null);
            v24 = this.getGuiElement();
            if (v24 == null) break block4;
            v24.render(var1_1, this.getX() + this.getWidth() / 2.0f - 60.0f, this.getY() + this.getHeight() / 2.0f - 35.0f, var31_12, var2_2, var3_3);
        }
    }

    private final void addProxy(String string) {
        ProxyManager.INSTANCE.addProxy(new Proxy(string, "", 0, "", "", false, 0, 64, null));
        this.refresh();
        this.setGuiElement(null);
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
        if (ProxyPanel.s) {
            TextField.Companion.A(null);
            this.setGuiElement(new TextInput(ProxyPanel::mouseClicked$lambda$0));
            return;
        }
        this.setGuiElement(null);
        if (hoveredElement != null) {
            t = hoveredElement;
            Element element = hoveredElement;
            Intrinsics.checkNotNull(element);
            element.isHovered(f, f2);
            return;
        }
        t = null;
        if (focusedElement != null) {
            switch (n) {
                case 0: {
                    Proxy proxy = ProxyManager.INSTANCE.getProxy();
                    Element element = focusedElement;
                    Intrinsics.checkNotNull(element);
                    if (Intrinsics.areEqual(proxy, ((ProxyElement)element).getProxy())) {
                        ProxyManager.INSTANCE.setProxy(null);
                        break;
                    }
                    Element element2 = focusedElement;
                    Intrinsics.checkNotNull(element2);
                    ProxyManager.INSTANCE.setProxy(((ProxyElement)element2).getProxy());
                    break;
                }
                case 1: {
                    Element element = focusedElement;
                    Intrinsics.checkNotNull(element, "null cannot be cast to non-null type su.catlean.client.features.ui.panel.panels.proxy.ProxyItem");
                    ProxyElement proxyElement = ProxyPanel.proxyElement = (ProxyElement)element;
                    Intrinsics.checkNotNull(proxyElement);
                    idField.setText(proxyElement.getProxy().getId());
                    ProxyElement proxyElement2 = ProxyPanel.proxyElement;
                    Intrinsics.checkNotNull(proxyElement2);
                    ipField.setText(proxyElement2.getProxy().getIp());
                    ProxyElement proxyElement3 = ProxyPanel.proxyElement;
                    Intrinsics.checkNotNull(proxyElement3);
                    portField.setText(String.valueOf(proxyElement3.getProxy().getPort()));
                    ProxyElement proxyElement4 = ProxyPanel.proxyElement;
                    Intrinsics.checkNotNull(proxyElement4);
                    passwordField.setText(proxyElement4.getProxy().getPassword());
                    ProxyElement proxyElement5 = ProxyPanel.proxyElement;
                    Intrinsics.checkNotNull(proxyElement5);
                    userField.setText(proxyElement5.getProxy().getUser());
                    w.animateTo(0.0f);
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
                scroll = Math.clamp(scroll, (float)proxyElements.size() * -29.0f, 0.0f);
            }
        }
    }

    private static final Unit _clinit_$lambda$0(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = proxyElement;
            if (object == null || (object = ((ProxyElement)object).getProxy()) == null) break block0;
            ((Proxy)object).setId(textField.getText());
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$1(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = proxyElement;
            if (object == null || (object = ((ProxyElement)object).getProxy()) == null) break block0;
            String string = textField.getText();
            ((Proxy)object).setIp(string);
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$2(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = proxyElement;
            if (object == null || (object = ((ProxyElement)object).getProxy()) == null) break block0;
            Integer n = StringsKt.toIntOrNull(textField.getText());
            ((Proxy)object).setPort(Math.abs(n != null ? n : 25565));
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$3(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = proxyElement;
            if (object == null || (object = ((ProxyElement)object).getProxy()) == null) break block0;
            String string = textField.getText();
            ((Proxy)object).setUser(string);
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$4(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = proxyElement;
            if (object == null || (object = ((ProxyElement)object).getProxy()) == null) break block0;
            ((Proxy)object).setPassword(textField.getText());
        }
        return Unit.INSTANCE;
    }

    private static final Unit removeSelected() {
        ProxyElement proxyElement = ProxyPanel.proxyElement;
        Intrinsics.checkNotNull(proxyElement);
        ProxyManager.INSTANCE.removeProxy(proxyElement.getProxy());
        Collection collection = proxyElements;
        ProxyElement proxyElement2 = ProxyPanel.proxyElement;
        TypeIntrinsics.asMutableCollection(collection).remove(proxyElement2);
        ProxyPanel.proxyElement = null;
        return Unit.INSTANCE;
    }

    private static final Unit mouseClicked$lambda$0(String string) {
        Intrinsics.checkNotNullParameter(string, "it");
        INSTANCE.addProxy(string);
        return Unit.INSTANCE;
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
                                ProxyPanel.b = ZkmKeys.a(-7662111121247854744L, 6747433453880005797L, MethodHandles.lookup().lookupClass()).a(211758816005306L);
                                var31 = ProxyPanel.b ^ 121234865053920L;
                                ProxyPanel.m = new HashMap<K, V>(13);
                                ProxyPanel.zkmSetFlag(null);
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
                                var29_3 = new String[18];
                                var27_4 = 0;
                                var26_5 = "\u00a4\u00c1\u0004U\u00de\u00e34\u000f\u00a0\u00a2\u00926\u00d2mBW\u00108\u00b3\u009e\u00dd\u00fb?z\u00e5\u0012O\u0001A\u00a1\u00cci\u0093\u0010\u00b7\u00e3e\u00c0\u0019m\u0016\u009b\u00c9?\u00b5\u00fc\u00aa\u0081)*\u0010_\u00e5\u0099\u00a8\u00c5%\u00c6\u00e8]:\u00e4\u0097\u00af1\u00d4\u00f3\u0010Y\n\rz\u009f\u00d4\u009a\u00c7\u008c\u0019h.O\u0080\u00eb1\u0010\u00c6?\u009b-\u00cb\u0007\u0000\u00af\u0004?\u00ae\u00f6\u00adZ\u00d8.\u0010R2\u0015O\u00a4\u0002\u00ac\u0018\u0089\u00bd\u00c7\u0081\u00aa\u0016%\u00ac u\u00de\u00ea\u00cc\u007f/;\u0095\u00df#jO\u00b0\u0089\u00d4 \u00d0a\u00c0\u0084\f\u00ff\u0007\u00a7\u00d2\u008a\u00a0P0\u00d3v1\u0010O\u0017\u000f3\u00c1\u00e6\u00b9\u000bx\u00f6\u00f8/\u00ed\u00f6\u00cc\u00fe\u0090\u00ad=\u00dd\u00dd`\u00eaf\u00fd\u0000\u00ea\u00b4V\u00c7\u00e2\u00a8H\u00f2I\u000e\u00ef\u0002\u0089\u00e4\u009b\u00144E8\u00d6\u008b\u00fa\u008a\u00b4]\u00d4\u00fe;E\u00d9\u00ff\u00a80\u00a1\u00df$\u00c7f\u00e5\u00a0/2\u00dd7\u00d5\u0019\u00ac\u0017/AC(\u0010\u0097\u0018.\u00e2g\r\u00c6\u00aa\u00cf\u00bdF\u001a7~\u001a\u00b6\u009as\u00ddJ\u0084\u0010\u00b4\u0083\u00f0\n<\u00a9\u0004\u0000\u00a5\u00ed\u00ab\u00f69\u008bG\u0010\u00af5\u00f9\u00c4\u00b66\u00f7n\u0089#B\u00a5\u0014QK\u00e3<\u00a5\u0087\u00e3\u00c3Il\u008fc\u00da\u00d1 \u00f7\u00f5\u00d8\f\u00cfA\u0081\u001a\u00d9\u0015\u0098o\u0086\u00d2\u008bx \u00ef\u00f7\u00b5\u0084B#\u000f\u00b0:v\u00f6\u008e\u00f0\u0082!\u00a8\u00fa\u0097\u00d8\u00e1\u00ab\u00fe\u00c7E1\u0083\u00ffY\u00ad\u009c\u00a6\u00ea\u0010\u008aM\u000e(\u0097D\u00f4~!\u00b9xc\u00f2\u001d\u00cf\u00b8\u0010\u0018497.\u008e\u00940\u00f8O$W\u00cc\u000e\u00ee\u00e0\u0018\u0083V\u001e=\u00bad\u008eg\u00cf\u0013\u00fay\"\u00f2\n\u0013f>Mp\u00ffi\u0010h\u0010\u00f5SO\u0089\u00d6\u009b\u00a7\u00aev\u00a8%c\u0016'al\u00107cQnH\u008d\u00bd\u008fq\u0013o=\u00d0\u009f\u0086o";
                                var28_6 = 439;
                                var25_7 = 16;
                                var24_8 = -1;
lbl21:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl26:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = ProxyPanel.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u00db\u00ba\u009f\u0018\u00fe\u00e00\u00a4m\u00a82\t\u00e2\u0095w3\u0010H\u00ae\u00f5r4\u00d3\u000b\u00b9s\u008a\u00cc$t\u0015N\u00dd";
                                    var28_6 = 33;
                                    var25_7 = 16;
                                    var24_8 = -1;
lbl35:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl40:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = ProxyPanel.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block22;
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
                        ProxyPanel.e = var29_3;
                        ProxyPanel.j = new String[18];
                        ProxyPanel.y = new HashMap<K, V>(13);
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
                        var17_12 = new long[8];
                        var14_13 = 0;
                        var15_14 = "\u00f9h\u00be}\u001f\u0018E\u00bb5\u00ad\u00a7\u008d\u000508se\u008e.\u0095\u0013\u00ea`S}c\u00f3\u00c1\u008f\u00c4\u0090q\u00bc \u00afi\u00bc3v\u00da\u009a\u00b7\u00a8\u0082Q\u0088\u00f2\u00b8";
                        var16_15 = 48;
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
lbl79:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "\u00c9]\r5|\u00c9i\u00a0\u0015C\u0082&\u00fa\u00f8\u001a\u0088";
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
lbl92:
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
lbl105:
                        // 1 sources

                        ** continue;
                    }
                }
                ProxyPanel.n = var17_12;
                ProxyPanel.q = new Integer[8];
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
                var5_24 = "\u00ec3\u00ee\u0014x_\u00e3\u001c\u0095\u009c\u000fB\u00e8\r\u007f\u00de\u00ec3\u00ee\u0014x_\u00e3\u001c";
                var6_25 = 24;
                var3_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl126:
                // 1 sources

                while (true) {
                    var0_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var3_26 < var6_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var5_24.substring(var3_26, var3_26 += 8).getBytes("ISO-8859-1");
            v18 = var4_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var1_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        ProxyPanel.INSTANCE = new ProxyPanel();
        ProxyPanel.proxyElements = new ArrayList<E>();
        ProxyPanel.w = new Animation(Easing.OUT_QUINT, var0_22[0]);
        ProxyPanel.F = new Animation(Easing.OUT_QUINT, var0_22[2]);
        ProxyPanel.S = new Animation(Easing.OUT_QUINT, var0_22[1]);
        ProxyPanel.idField = new TextField("Name", ProxyPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        ProxyPanel.ipField = new TextField("IP", ProxyPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$1(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        ProxyPanel.portField = new TextField("Port", ProxyPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$2(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        ProxyPanel.userField = new TextField("Login", ProxyPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$3(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        ProxyPanel.passwordField = new TextField("Pass", ProxyPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$4(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        ProxyManager.INSTANCE.getActiveProxy();
        ProxyPanel.INSTANCE.refresh();
        ProxyPanel.INSTANCE.setX(530.0f);
    }

    public static void zkmSetFlag(String string) {
        v = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return v;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x441C;
        if (j[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])m.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    m.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/il", exception);
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
            ProxyPanel.j[n2] = ProxyPanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return j[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProxyPanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/il" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7106;
        if (q[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = ProxyPanel.n[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])y.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    y.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/il", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ProxyPanel.q[n2] = n3;
        }
        return q[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ProxyPanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/il" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProxyPanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ProxyPanel.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

