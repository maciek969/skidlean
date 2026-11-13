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
import su.catlean.gui.SidePanel;
import su.catlean.gui.TextField;
import su.catlean.gui.TextInput;
import su.catlean.gui.ToggleElement;
import su.catlean.gui.WaypointElement;
import su.catlean.gui.WaypointPanel$hoveringComponent$1;
import su.catlean.gui.WaypointPanel$hoveringComponent$2;
import su.catlean.render.Gradient2D;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.Waypoint;
import su.catlean.util.Waypoint$dimension$1;
import su.catlean.util.WaypointManager;
import su.catlean.zkm.ZkmKeys;

public final class WaypointPanel
extends SidePanel {
    @NotNull
    public static final WaypointPanel INSTANCE;
    @NotNull
    private static List waypointElements;
    @Nullable
    private static Element hoveredElement;
    @Nullable
    private static Element V;
    @Nullable
    private static Element focusedElement;
    @Nullable
    private static WaypointElement waypointElement;
    @NotNull
    private static Animation Z;
    @NotNull
    private static Animation m;
    @NotNull
    private static Animation B;
    private static boolean X;
    private static float scroll;
    @NotNull
    private static final TextField L;
    @NotNull
    private static final TextField serverField;
    @NotNull
    private static final TextField n;
    @NotNull
    private static final TextField yField;
    @NotNull
    private static final TextField zField;
    private static final long c;
    private static final String[] e;
    private static final String[] i;
    private static final Map r;
    private static final long[] s;
    private static final Integer[] v;
    private static final Map w;

    private WaypointPanel() {
        super("waypoint-panel", 0.0f, 0.0f, 0.0f, 0.0f, null);
    }

    @NotNull
    public final List getWaypointElements() {
        return waypointElements;
    }

    public final void setWaypointElements(@NotNull List list) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ c;
        Intrinsics.checkNotNullParameter(list, (String)((Object)WaypointPanel.zkmBootstrap("f", (int)2315, (long)(0x305EB043840FFD24L ^ l2))));
        waypointElements = list;
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
    public final WaypointElement getWaypointElement() {
        return waypointElement;
    }

    public final void setWaypointElement(@Nullable WaypointElement waypointElement) {
        WaypointPanel.waypointElement = waypointElement;
    }

    @NotNull
    public final Animation c() {
        return Z;
    }

    public final void K(@NotNull Animation animation) {
        long l = 0L;
        l = c ^ l;
        Intrinsics.checkNotNullParameter(animation, (String)((Object)WaypointPanel.zkmBootstrap("f", (int)10459, (long)(0x552AA00E77F036F5L ^ l))));
        Z = animation;
    }

    @Override
    public void refresh() {
        waypointElements.clear();
        Iterable iterable = WaypointManager.INSTANCE.getWaypoints();
        for (Object t : iterable) {
            Waypoint waypoint = (Waypoint)t;
            waypointElements.add(new WaypointElement(waypoint));
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4) {
        block4: {
            v0 = var4_4;
            var9_5 = v0 ^ 136208856317167L;
            var13_6 = v0 ^ 136208856317167L;
            var15_7 = v0 ^ 129382754651428L;
            var17_8 = v0 ^ 136208856317167L;
            v1 = v0 ^ 22135879901675L;
            var22_9 = (int)(v1 >>> 48);
            var23_10 = (int)(v1 << 16 >>> 48);
            var24_11 = (int)(v1 << 32 >>> 32);
            var27_12 = v0 ^ 27182768661316L;
            var29_13 = v0 ^ 0L;
            var31_14 = v0 ^ 97910750751556L;
            var46_15 = v0 ^ 136208856317167L;
            Intrinsics.checkNotNullParameter(var1_1, "context");
            super.render(var1_1, var2_2, var3_3, var29_13);
            WaypointPanel.Z.animate(1.0f);
            WaypointPanel.m.animate(this.getGuiElement() != null ? 0.5f : 0.0f);
            WaypointPanel.B.animate(WaypointPanel.scroll);
            var64_16 = this.getX() + 3.0f;
            var65_17 = this.getX() + 72.0f;
            var66_20 = var2_2;
            v2 = var64_16 <= var66_20 ? var66_20 <= var65_17 : false;
            if (!v2) ** GOTO lbl-1000
            var64_16 = this.getY() + 5.0f;
            var65_17 = this.getY() + 13.0f;
            var66_20 = var3_3;
            v3 = var64_16 <= var66_20 ? var66_20 <= var65_17 : false;
            if (v3) {
                v4 = true;
            } else lbl-1000:
            // 2 sources

            {
                v4 = false;
            }
            WaypointPanel.X = v4;
            v5 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
            var48_22 = null;
            var49_23 = 14336;
            var50_24 = 0.0f;
            var51_25 = 0.0f;
            var52_26 = 0.0f;
            var53_27 = Theme.INSTANCE.getOutlineColor();
            var54_28 = Theme.INSTANCE.g();
            var55_29 = 1.0f;
            var56_30 = WaypointPanel.X != false ? 0.1f : 0.0f;
            var57_31 = WaypointPanel.X != false ? 2.5f : 2.0f;
            var58_32 = 2.0f;
            var59_33 = 8.0f;
            var60_34 = 69.0f;
            var61_35 = this.getY() + 3.0f;
            var62_36 = this.getX() + 5.0f;
            Shapes2D.drawShape$default(v5, (char)var22_9, var62_36, var61_35, var60_34, var59_33, var58_32, var57_31, var56_30, var55_29, var54_28, var53_27, var52_26, (short)var23_10, var51_25, var24_11, var50_24, var49_23, var48_22);
            v6 = Fonts.getSF_M();
            v7 = this.getY() + 1.0f;
            v8 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v8, "WHITE");
            v6.drawStringWithShadow(var1_1, "+", var15_7, this.getX() + 36.0f, v7, v8);
            v9 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v9, "pose(...)");
            Shapes2D.drawShape$default(v9, (char)var22_9, this.getX() + 76.0f, this.getY(), 6.0f, this.getHeight(), 1.0f, 0.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var23_10, 0.0f, var24_11, 0.0f, 14336, null);
            WaypointPanel.focusedElement = null;
            WaypointPanel.hoveredElement = null;
            var64_16 = 17.0f + WaypointPanel.B.getValue();
            RenderUtil.INSTANCE.beginScissor(var1_1, new Rect(this.getX(), this.getY() + 14.0f, this.getX() + 76.0f, this.getY() + this.getHeight() - 2.0f));
            var65_18 = WaypointPanel.waypointElements;
            for (T var68_38 : var65_18) {
                var69_39 = (WaypointElement)var68_38;
                var69_39.render(var17_8, var1_1, WaypointPanel.INSTANCE.getX(), WaypointPanel.INSTANCE.getY() + var64_16, var2_2, var3_3);
                var64_16 += 29.0f;
            }
            RenderUtil.INSTANCE.endScissor(var1_1);
            if (WaypointPanel.waypointElement != null) {
                var65_19 = (1.0f - WaypointPanel.Z.getValue()) * 10.0f;
                var66_21 = (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, removeSelected(), ()Lkotlin/Unit;)();
                WaypointPanel.L.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 17.0f + var65_19, var2_2, var3_3);
                WaypointPanel.serverField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 32.0f + var65_19, var2_2, var3_3);
                WaypointPanel.n.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 47.0f + var65_19, var2_2, var3_3);
                WaypointPanel.yField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 62.0f + var65_19, var2_2, var3_3);
                WaypointPanel.zField.render(var9_5, var1_1, this.getX() + 84.0f, this.getY() + 77.0f + var65_19, var2_2, var3_3);
                v10 = this.getWidth();
                v11 = WaypointPanel.waypointElement;
                Intrinsics.checkNotNull(v11);
                new ToggleElement(v10, new Waypoint$dimension$1(v11.getPoint()), new WaypointPanel$hoveringComponent$2(this)).render(var46_15, var1_1, this.getX() + 84.0f, this.getY() + 92.0f + var65_19, var2_2, var3_3);
                var71_40 = new WaypointPanel$hoveringComponent$1(this);
                var72_41 = this.getWidth();
                new Button(var66_21, var72_41, var71_40).render(var13_6, var1_1, this.getX() + 84.0f, this.getY() + 107.0f + var65_19, var2_2, var3_3);
            }
            v12 = this.getX();
            v13 = this.getY();
            v14 = this.getWidth();
            v15 = this.getHeight();
            v16 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v16, "BLACK");
            var76_42 = WaypointPanel.m.getValue();
            v17 = RenderUtil.INSTANCE.withAlpha(v16, var76_42);
            v18 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v18, "BLACK");
            var80_43 = WaypointPanel.m.getValue();
            v19 = RenderUtil.INSTANCE.withAlpha(v18, var80_43);
            v20 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v20, "BLACK");
            var84_44 = WaypointPanel.m.getValue();
            v21 = Color.BLACK;
            Intrinsics.checkNotNullExpressionValue(v21, "BLACK");
            var88_45 = WaypointPanel.m.getValue();
            v22 = RenderUtil.INSTANCE.withAlpha(v21, var88_45);
            v23 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v23, "pose(...)");
            Gradient2D.E(v12, v13, v14, v15, 5.0f, v17, v19, var31_14, RenderUtil.INSTANCE.withAlpha(v20, var84_44), v22, v23, 0.0f, null, null);
            v24 = this.getGuiElement();
            if (v24 == null) break block4;
            v24.render(var1_1, this.getX() + this.getWidth() / 2.0f - 60.0f, this.getY() + this.getHeight() / 2.0f - 35.0f, var27_12, var2_2, var3_3);
        }
    }

    /*
     * Unable to fully structure code
     */
    private final void addWaypoint(String var1_1) {
        if (Globals.getMc().player == null || Globals.getMc().world == null) ** GOTO lbl-1000
        v0 = Globals.getMc().getNetworkHandler();
        if ((v0 != null ? v0.getServerInfo() : null) == null) lbl-1000:
        // 2 sources

        {
            var21_2 = new Waypoint(var1_1, 0, 0, 0, null, 0, false, 112, null);
            v1 = WaypointManager.INSTANCE.addWaypoint(var21_2);
        } else {
            v2 = (int)Globals.getPlayer().getX();
            v3 = (int)Globals.getPlayer().getY();
            v4 = (int)Globals.getPlayer().getZ();
            v5 = Globals.getNetworkHandler().getServerInfo();
            Intrinsics.checkNotNull(v5);
            v6 = v5.address;
            Intrinsics.checkNotNullExpressionValue(v6, "ip");
            v7 = Globals.getWorld().getRegistryKey().getValue().getPath();
            Intrinsics.checkNotNullExpressionValue(v7, "getPath(...)");
            var21_3 = new Waypoint(var1_1, v2, v3, v4, v6, WaypointManager.INSTANCE.getDimensionId(v7), false, 64, null);
            v1 = WaypointManager.INSTANCE.addWaypoint(var21_3);
        }
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
        if (X) {
            TextField.Companion.A(null);
            this.setGuiElement(new TextInput(WaypointPanel::mouseClicked$lambda$0));
            return;
        }
        if (this.getGuiElement() != null) {
            GuiElement guiElement = this.getGuiElement();
            Intrinsics.checkNotNull(guiElement);
            if (guiElement.mouseClicked(l4, f, f2)) {
                return;
            }
        }
        this.setGuiElement(null);
        if (hoveredElement != null) {
            V = hoveredElement;
            Element element = hoveredElement;
            Intrinsics.checkNotNull(element);
            element.isHovered(f, f2);
            return;
        }
        V = null;
        if (focusedElement != null) {
            switch (n) {
                case 0: {
                    Element element = focusedElement;
                    Intrinsics.checkNotNull(element);
                    Waypoint waypoint = ((WaypointElement)element).getPoint();
                    Element element2 = focusedElement;
                    Intrinsics.checkNotNull(element2);
                    waypoint.setVisible(!((WaypointElement)element2).getPoint().isVisible());
                    break;
                }
                case 1: {
                    Element element = focusedElement;
                    Intrinsics.checkNotNull(element, "null cannot be cast to non-null type su.catlean.client.features.ui.panel.panels.waypoint.WayPointItem");
                    WaypointElement waypointElement = WaypointPanel.waypointElement = (WaypointElement)element;
                    Intrinsics.checkNotNull(waypointElement);
                    L.setText(waypointElement.getPoint().getId());
                    WaypointElement waypointElement2 = WaypointPanel.waypointElement;
                    Intrinsics.checkNotNull(waypointElement2);
                    serverField.setText(waypointElement2.getPoint().getServer());
                    WaypointElement waypointElement3 = WaypointPanel.waypointElement;
                    Intrinsics.checkNotNull(waypointElement3);
                    WaypointPanel.n.setText(String.valueOf(waypointElement3.getPoint().getX()));
                    WaypointElement waypointElement4 = WaypointPanel.waypointElement;
                    Intrinsics.checkNotNull(waypointElement4);
                    yField.setText(String.valueOf(waypointElement4.getPoint().getY()));
                    WaypointElement waypointElement5 = WaypointPanel.waypointElement;
                    Intrinsics.checkNotNull(waypointElement5);
                    zField.setText(String.valueOf(waypointElement5.getPoint().getZ()));
                    Z.animateTo(0.0f);
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
                scroll = Math.clamp(scroll, (float)waypointElements.size() * -29.0f, 0.0f);
            }
        }
    }

    private static final Unit _clinit_$lambda$0(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = waypointElement;
            if (object == null || (object = ((WaypointElement)object).getPoint()) == null) break block0;
            String string = textField.getText();
            ((Waypoint)object).setId(string);
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$1(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = waypointElement;
            if (object == null || (object = ((WaypointElement)object).getPoint()) == null) break block0;
            ((Waypoint)object).setServer(textField.getText());
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$2(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = waypointElement;
            if (object == null || (object = ((WaypointElement)object).getPoint()) == null) break block0;
            Integer n = StringsKt.toIntOrNull(textField.getText());
            ((Waypoint)object).setX(n != null ? n : 0);
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$3(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = waypointElement;
            if (object == null || (object = ((WaypointElement)object).getPoint()) == null) break block0;
            Integer n = StringsKt.toIntOrNull(textField.getText());
            ((Waypoint)object).setY(n != null ? n : 0);
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$4(TextField textField) {
        block0: {
            Intrinsics.checkNotNullParameter(textField, "it");
            Object object = waypointElement;
            if (object == null || (object = ((WaypointElement)object).getPoint()) == null) break block0;
            Integer n = StringsKt.toIntOrNull(textField.getText());
            ((Waypoint)object).setZ(n != null ? n : 0);
        }
        return Unit.INSTANCE;
    }

    private static final Unit removeSelected() {
        WaypointElement waypointElement = WaypointPanel.waypointElement;
        Intrinsics.checkNotNull(waypointElement);
        Waypoint waypoint = waypointElement.getPoint();
        WaypointManager.INSTANCE.removeWaypoint(waypoint);
        Collection collection = waypointElements;
        WaypointElement waypointElement2 = WaypointPanel.waypointElement;
        TypeIntrinsics.asMutableCollection(collection).remove(waypointElement2);
        WaypointPanel.waypointElement = null;
        return Unit.INSTANCE;
    }

    private static final Unit mouseClicked$lambda$0(String string) {
        Intrinsics.checkNotNullParameter(string, "it");
        INSTANCE.addWaypoint(string);
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
                                WaypointPanel.c = ZkmKeys.a(-1034828290320947124L, 1722824505300924339L, MethodHandles.lookup().lookupClass()).a(84933603646214L);
                                var31 = WaypointPanel.c ^ 136874861407508L;
                                WaypointPanel.r = new HashMap<K, V>(13);
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
                                var29_3 = new String[16];
                                var27_4 = 0;
                                var26_5 = "{\u001f]j\nb\u0014#\u0081\u008e3(\"\u00ffFK\u0010*\u00d5\u00c97\u00d8!\u0093D\u00b1\u0004\u00ed\u00ba\u00c3\u00ad\u00d0\u0019 \u00ef\u00a6\u0094\u0001DM}c\u00aeu\u00e5\u00df\u00d3\u0084\u00f8\u00bc\u008eh\u0088\u00dd\u00b5J$\u00c0\u00f2\u00c2\u0005\u00b2 \u008c\u00a2\u008a\u0010'\u0018\u0082\"**)\u00d0\u00d2(\u0012\u00cc\u001a\u00feR\u0091\u0018j\u00e6\u00deH\u00d73\u00fdO\u00bdP\u00840\u00d0L#\u0099\u0084\u00fc\u0092\\\u00c7\u0099\u00d7\u00c6\u0010\u0019\u007f\u00c0\u0098\u0088@\u00c6C<V\u0099\u00cf\u009b\u00c0\u00ab\u00ef \u00cc.\u00e8M\u0019\u0097\u001a\u00afK\u00ef\u00e0-Zi\u008e3le\u0015\u00a6]\u00f0zW\u0090\u0019J\u00fc\u00d3L\u0094\u00cd\u0010\u00c2es8\u00e1\u00fe\u00e9\u00df\u00d9 \u0015\\\t\u00fa\u00f1\u00c2\u0010\u0011C\u001aE.\u009b\u0083#\u00ce\u00a9\n\u00da\u008b\u00caK\u0004\u0010\u0013\u009e\u0001X1e\u00da^4\u0081L\u00b2\u00f7\u00e1\u0011h\u0010\u00b9\u0091\u0003\u008a\f'\u008a\u00ff/\u00e2\u00dd\u009d\\a}\u00ed\u00a0D\u0013\u00b3\u00f5\u00e2G\u009f\u00b5\u00f1\u00d0\u00a3\u00d56C\u00f1\r\u00b9\u00b8\u009f\u00f5\n]\u0091\u00caYD\u00b8H \u00b4\u00c3W\u009c\u0082\u00dfJ\u0010\u00f2\u00c5\u009a\u0016_\u00e4Fi\n\u00b1IZ5.\u008c@\u000f\rO\u00ebg\u00cd\u00d1\u00d0\u001f\u00c3vi\u00b5\u00efR\u00b0Foy\u00cf.\u00a7#\u0087\u0084\u00b5\u00ae\u00ff\u00e9/\u00c4&x\u009a\u0094\u00a7q4~\u00f3\u00a9\u00d0~8\u0011\u008b\u00837\u009b\u00a0q\u0015>\u00dd\u001e\u00a8M\u000f=\u0097.\u0083@\u00c0\u0018\u00fd\u0018P!\u000f\u00f1B\u0015\u00c3i\u0097p?\u00eew\u00be\u0099\u00d4\u00e7>\u00fc\u00a3D5\u000bZ\u00d1Is\u00ad\u00f349\u00ddn\u00bc\u00dc\u00bc;w\u00ee! \u00b1\u00b3\u00ce\u00c9u\u00f0\u00e0\u00ae*_\u000b\u00d1o\u00fc\u00b38DnY\u00c3\u0017\u00d7n\u00e5\u00f4\u008f\u00c1MoG\u00a9\u00a3\u0010\u00f1K\u00ba\u00b0\u00e8T\u00a4Bj\u00ee\u0015Y\"\u00f29\u00d9";
                                var28_6 = 437;
                                var25_7 = 16;
                                var24_8 = -1;
lbl20:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl25:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = WaypointPanel.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u00fbNDW`G\u0094\u00bdY\u00e5\u00b6\u00eb\u00b5\u008e\u00ae\u0018\u0010T-\u0082\u00da.\u00cb\n\u00a9\t\u000emFj\u00cc\u00f7\u0000";
                                    var28_6 = 33;
                                    var25_7 = 16;
                                    var24_8 = -1;
lbl34:
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
lbl39:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = WaypointPanel.zkmDecodeUtf8(var30_9).intern();
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
lbl51:
                                // 1 sources

                                ** continue;
                            }
                        }
                        WaypointPanel.e = var29_3;
                        WaypointPanel.i = new String[16];
                        WaypointPanel.w = new HashMap<K, V>(13);
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
                        var15_14 = "\u008f\u0087N.\u00ac8q>\u00fa\u00a3f\u008e?\u0093\u0018\u008cV\u00fc\u00d2>\u0011p\u00a7gj\u00de\u000f@\u001b\u00e7\u009c\u0093\u0098\u00b37\u00db\u00b4J\u0080\u00a6D\u00ca;+E?\u00f9\u00c6";
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
lbl78:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "3\u0012+j\u0003Y~\u0088\u008e\u00ca\u00ef\u0090[\u00e7\u00f3\u00a9";
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
                WaypointPanel.s = var17_12;
                WaypointPanel.v = new Integer[8];
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
                var5_24 = "q\u0098\u00df\u009f\u00cd\u0000d\u001a\u00a5\u00fb^\u0002)W\u00ef\u00dd\u00a5\u00fb^\u0002)W\u00ef\u00dd";
                var6_25 = 24;
                var3_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl125:
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
        WaypointPanel.INSTANCE = new WaypointPanel();
        WaypointPanel.waypointElements = new ArrayList<E>();
        WaypointPanel.Z = new Animation(Easing.OUT_QUINT, var0_22[1]);
        WaypointPanel.m = new Animation(Easing.OUT_QUINT, var0_22[2]);
        WaypointPanel.B = new Animation(Easing.OUT_QUINT, var0_22[0]);
        WaypointPanel.L = new TextField("Name", WaypointPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        WaypointPanel.serverField = new TextField("Server", WaypointPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$1(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        WaypointPanel.n = new TextField("X", WaypointPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$2(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        WaypointPanel.yField = new TextField("Y", WaypointPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$3(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        WaypointPanel.zField = new TextField("Z", WaypointPanel.INSTANCE.getWidth(), null, (Function1<TextField, Unit>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$4(su.catlean.gui.TextField ), (Lsu/catlean/gui/TextField;)Lkotlin/Unit;)(), null);
        WaypointPanel.INSTANCE.refresh();
        WaypointPanel.INSTANCE.setX(290.0f);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xCDF;
        if (i[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])r.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    r.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/i7", exception);
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
            WaypointPanel.i[n2] = WaypointPanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return i[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WaypointPanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/i7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4642;
        if (v[n2] == null) {
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
                throw new RuntimeException("su/catlean/i7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            WaypointPanel.v[n2] = n3;
        }
        return v[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = WaypointPanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/i7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WaypointPanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(WaypointPanel.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

