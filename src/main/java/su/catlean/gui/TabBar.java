/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import su.catlean.gui.ClickGui;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.PanelScreen;
import su.catlean.gui.Tab;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Setting;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;

public final class TabBar {
    @NotNull
    public static final TabBar INSTANCE;
    @NotNull
    private static Tab n;
    @NotNull
    private static Tab w;
    private static boolean O;
    private static boolean y;
    @NotNull
    private static final List tabs;
    @NotNull
    private static final Animation animation;
    @NotNull
    private static String V;
    @Nullable
    private static Function0 pendingAction;
    @NotNull
    private static final String k;
    @NotNull
    private static final String m;
    private static String Q;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private TabBar() {
    }

    public final boolean B() {
        return y;
    }

    public final void c(boolean bl) {
        y = bl;
    }

    /*
     * Unable to fully structure code
     */
    public final void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4) {
        block12: {
            v0 = var4_4 = TabBar.a ^ var4_4;
            v1 = v0 ^ 24424801766223L;
            var8_5 = (int)(v1 >>> 48);
            var9_6 = (int)(v1 << 16 >>> 48);
            var10_7 = (int)(v1 << 32 >>> 32);
            var13_8 = v0 ^ 64954647113277L;
            var17_9 = v0 ^ 115579440229178L;
            var19_10 = v0 ^ 96483187743809L;
            Intrinsics.checkNotNullParameter(var1_1, "context");
            if (!Intrinsics.areEqual(Globals.getMc().currentScreen, ClickGuiScreen.INSTANCE)) {
                TabBar.y = false;
            }
            TabBar.animation.animate(TabBar.y != false ? 1.0f : 0.0f);
            var26_11 = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f;
            var27_12 = 5.0f;
            var28_13 = 220.0f;
            var29_14 = 16.0f;
            v2 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v2, "pose(...)");
            Shapes2D.drawShape$default(v2, (char)var8_5, var26_11 - 110.0f, var27_12, var28_13, var29_14, 5.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_6, 0.0f, var10_7, 0.0f, 14336, null);
            var30_15 = TabBar.tabs;
            var32_16 = 0;
            for (T var34_19 : var30_15) {
                if ((var35_20 = var32_16++) < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                var36_21 = (Tab)var34_19;
                var37_22 = var35_20;
                switch (var37_22) {
                    case 0: {
                        v3 = Intrinsics.areEqual(Globals.getMc().currentScreen, ClickGuiScreen.INSTANCE);
                        break;
                    }
                    case 1: {
                        v3 = Intrinsics.areEqual(Globals.getMc().currentScreen, PanelScreen.INSTANCE);
                        break;
                    }
                    case 2: {
                        v3 = Intrinsics.areEqual(Globals.getMc().currentScreen, HudEditorScreen.INSTANCE);
                        break;
                    }
                    case 3: {
                        v3 = TabBar.O;
                        break;
                    }
                    case 4: {
                        v3 = TabBar.y;
                        break;
                    }
                    default: {
                        v3 = false;
                    }
                }
                var39_23 = v3;
                var36_21.getAnimation().animate(var39_23 != false ? 1.0f : 0.0f);
                var40_24 = var26_11 - 110.0f + var36_21.getX() + 2.0f;
                var41_25 = var37_22 == 4 ? 1.0f : 1.0f - TabBar.animation.getValue();
                v4 = var1_1.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v4, "pose(...)");
                Shapes2D.drawShape$default(v4, (char)var8_5, var40_24, 7.0f, 40.0f, 12.0f, 4.0f, 2.0f * var36_21.getAnimation().getValue(), var36_21.getAnimation().getValue() / 5.0f, var41_25, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_6, 0.0f, var10_7, 0.0f, 14336, null);
                v5 = Fonts.getSF_M3();
                if (var37_22 != 4) ** GOTO lbl-1000
                if (TabBar.y) {
                    v6 = "Back";
                } else lbl-1000:
                // 2 sources

                {
                    v6 = var36_21.getName();
                }
                v7 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(v7, "WHITE");
                var24_18 = Intrinsics.areEqual(var36_21.getName(), "Search") != false && Intrinsics.areEqual(Globals.getMc().currentScreen, ClickGuiScreen.INSTANCE) == false ? 0.3f : var41_25;
                v5.drawStringCentered(var1_1, v6, var40_24 + 20.0f, 12.0f, RenderUtil.INSTANCE.withAlpha(v7, var24_18), var17_9);
            }
            this.renderSearch(var1_1, var2_2, var3_3, var19_10, var26_11, var27_12, var28_13, var29_14);
            if (!TabBar.O) break block12;
            ClickGui.INSTANCE.render(var1_1, var2_2, var13_8, var3_3);
        }
    }

    private final void renderSearch(DrawContext drawContext, int n, int n2, long l, float f, float f2, float f3, float f4) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x7026D115FD85L;
        int n3 = (int)(l3 >>> 48);
        int n4 = (int)(l3 << 16 >>> 48);
        int n5 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x11AB7D76514AL;
        pendingAction = null;
        if (y) {
            FontRenderer fontRenderer = Fonts.getSF_M3();
            String string = V + ((double)System.currentTimeMillis() % 1000.0 > 500.0 ? "" : "_");
            Color color = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
            float f5 = animation.getValue();
            fontRenderer.drawStringWithShadow(drawContext, string, l4, f - f3 / 2.0f + 6.0f, f2 + 2.0f, RenderUtil.INSTANCE.withAlpha(color, f5));
            Map map = this.search();
            if (!map.isEmpty()) {
                Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                Shapes2D.drawShape$default(matrix3x2fStack, (char)n3, f - f3 / 2.0f, f2 + f4 + 4.0f, f3, (float)map.size() * 14.0f + 4.0f, 5.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
                Iterable iterable = CollectionsKt.withIndex(map.entrySet());
                Iterator iterator2 = iterable.iterator();
                while (iterator2.hasNext()) {
                    IndexedValue indexedValue;
                    IndexedValue indexedValue2 = indexedValue = iterator2.next();
                    int n6 = indexedValue2.component1();
                    Map.Entry entry = (Map.Entry)indexedValue2.component2();
                    String string2 = (String)entry.getKey();
                    Function0 function0 = (Function0)entry.getValue();
                    float f6 = f - f3 / 2.0f + 6.0f;
                    float f7 = f2 + f4 + 6.0f + 14.0f * (float)n6;
                    float f8 = f6 - 2.0f;
                    float f9 = f6 + f3 - 4.0f;
                    float f10 = n;
                    boolean bl = f8 <= f10 ? f10 <= f9 : false;
                    if (bl) {
                        f8 = f7 - 2.0f;
                        f9 = f7 + 11.0f;
                        f10 = n2;
                        boolean bl2 = f8 <= f10 ? f10 <= f9 : false;
                        if (bl2) {
                            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                            Shapes2D.drawShape$default(matrix3x2fStack2, (char)n3, f6 - 4.0f, f7, f3 - 4.0f, 14.0f, 4.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
                            pendingAction = function0;
                        }
                    }
                    FontRenderer fontRenderer2 = Fonts.getSF_M3();
                    Color color2 = Color.WHITE;
                    Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
                    float f11 = animation.getValue();
                    fontRenderer2.drawStringWithShadow(drawContext, string2, l4, f6, f7 + 1.0f, RenderUtil.INSTANCE.withAlpha(color2, f11));
                }
            }
        }
    }

    private final Map search() {
        Map map = new LinkedHashMap();
        if (((CharSequence)V).length() == 0) {
            return map;
        }
        String string = StringsKt.replace$default(V, " ", "-", false, 4, null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string, "toLowerCase(...)");
        String string2 = string;
        Object object = string2;
        int n = ((String)object).length();
        for (int i = 0; i < n; ++i) {
            char c = ((String)object).charAt(i);
            if (!StringsKt.contains$default((CharSequence)"\u0439\u0446\u0443\u043a\u0435\u043d\u0433\u0448\u0449\u0437\u0445\u044a\u0444\u044b\u0432\u0430\u043f\u0440\u043e\u043b\u0434\u0436\u044d\u044f\u0447\u0441\u043c\u0438\u0442\u044c\u0431\u044e", c, false, 2, null)) continue;
            string2 = StringsKt.replace$default(string2, c, "qwertyuiop[]asdfghjkl;'zxcvbnm,.".charAt(StringsKt.indexOf$default((CharSequence)"\u0439\u0446\u0443\u043a\u0435\u043d\u0433\u0448\u0449\u0437\u0445\u044a\u0444\u044b\u0432\u0430\u043f\u0440\u043e\u043b\u0434\u0436\u044d\u044f\u0447\u0441\u043c\u0438\u0442\u044c\u0431\u044e", c, 0, false, 6, null)), false, 4, null);
        }
        Iterator iterator2 = ModuleManager.INSTANCE.L().iterator();
        Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
        object = iterator2;
        while (object.hasNext()) {
            Object v3;
            Object object2;
            Module module;
            block6: {
                Setting setting2;
                Object e = object.next();
                Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                module = (Module)e;
                if (map.size() >= 10) break;
                object2 = module.getSettings();
                Map map2 = map;
                Iterable iterable = object2;
                Object object3 = new ArrayList();
                for (Object t : iterable) {
                    setting2 = (Setting)t;
                    if (!(StringsKt.contains$default((CharSequence)setting2.getId(), string2, false, 2, null) || StringsKt.contains$default((CharSequence)StringsKt.replace$default(setting2.getId(), "-", "", false, 4, null), string2, false, 2, null))) continue;
                    object3.add(t);
                }
                object2 = (List)object3;
                iterable = object2;
                object3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(object2, 10));
                for (Object t : iterable) {
                    setting2 = (Setting)t;
                    Collection collection = object3;
                    collection.add(TuplesKt.to(module.getDisplayName() + " \u2192 " + setting2.Q(), () -> TabBar.search$lambda$0(module)));
                }
                MapsKt.putAll(map2, (List)object3);
                Iterable iterable2 = module.getAliases();
                for (Object e2 : iterable2) {
                    String string3 = (String)e2;
                    if (!StringsKt.contains$default((CharSequence)string3, string2, false, 2, null)) continue;
                    v3 = e2;
                    break block6;
                }
                v3 = null;
            }
            object2 = v3;
            if (!StringsKt.contains$default((CharSequence)module.getId(), string2, false, 2, null) && object2 == null && !StringsKt.contains$default((CharSequence)StringsKt.replace$default(module.getId(), "-", "", false, 4, null), string2, false, 2, null)) continue;
            map.put(object2 == null ? module.getDisplayName() : module.getDisplayName() + " (" + (String)object2 + ")", () -> TabBar.search$lambda$1(module));
        }
        return map;
    }

    public final boolean mouseClicked(double d, double d2, int n) {
        block3: {
            float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - 108.0f;
            Iterable iterable = tabs;
            for (Object t : iterable) {
                Tab tab = (Tab)t;
                if (y && !Intrinsics.areEqual(tab.getName(), "Search")) continue;
                float f2 = f + tab.getX();
                boolean bl = (double)f2 <= d ? d <= (double)(f2 + 40.0f) : false;
                if (!bl) continue;
                boolean bl2 = 7.0 <= d2 ? d2 <= 19.0 : false;
                if (!bl2) continue;
                tab.getAction().invoke();
                return true;
            }
            if (pendingAction != null) {
                Function0 function0 = pendingAction;
                if (function0 != null) {
                    function0.invoke();
                }
                return true;
            }
            y = false;
            if (!O) break block3;
            ClickGui.INSTANCE.mouseClicked(d, d2, n);
        }
        return false;
    }

    public final void r(double d, double d2) {
        block0: {
            if (!O) break block0;
            ClickGui.INSTANCE.mouseReleased(d, d2);
        }
    }

    public final void keyPressed(long l, int n) {
        block3: {
            block2: {
                int n2 = (int)(((l = a ^ l) ^ 0x6AE4B9B0A922L) << 48 >>> 48);
                if (n == 70 && KeyboardUtil.isKeyPressed((char)n2, 341)) {
                    boolean bl = y = !y;
                }
                if (y && n == 256) {
                    y = false;
                }
                if (!y || n != 259) break block2;
                V = StringsKt.dropLast(V, 1);
                break block3;
            }
            if (!O) break block3;
            ClickGui.INSTANCE.keyPressed(n);
        }
    }

    public final void charTyped(char c) {
        block1: {
            block0: {
                if (!y || V.length() >= 20) break block0;
                V = V + c;
                break block1;
            }
            if (!O) break block1;
            ClickGui.INSTANCE.charTyped(c);
        }
    }

    public final boolean b(double d, double d2, double d3) {
        if (O) {
            return ClickGui.INSTANCE.mouseScrolled(d, d2, d3);
        }
        return false;
    }

    private static final Unit _clinit_$lambda$0() {
        Globals.getMc().setScreen(ClickGuiScreen.INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$1() {
        Globals.getMc().setScreen(PanelScreen.INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$2() {
        Globals.getMc().setScreen(HudEditorScreen.INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$3() {
        O = !O;
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$4() {
        y = !y;
        V = "";
        return Unit.INSTANCE;
    }

    private static final Unit search$lambda$0(Module module) {
        ClickGuiScreen.INSTANCE.findModuleButton(module);
        return Unit.INSTANCE;
    }

    private static final Unit search$lambda$1(Module module) {
        ClickGuiScreen.INSTANCE.openModule(module);
        return Unit.INSTANCE;
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
                            TabBar.a = ZkmKeys.a(-482408673196424408L, 8158223245272477230L, MethodHandles.lookup().lookupClass()).a(107818970027006L);
                            var27 = TabBar.a ^ 125124238212079L;
                            TabBar.d = new HashMap<K, V>(13);
                            TabBar.zkmSetFlag("gogdhb");
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
                            var25_3 = new String[22];
                            var23_4 = 0;
                            var22_5 = "^S\u00da\u00d5JzGD\u00dc\u00f6\u00cc\u0087\u00ac\u00f5\u00bc\u00d5\u0017R\u00dc\u00d2\u00ab\u0087\u0011'\u00ea\u00e9/O\u0017\u008e\\\u00b4L\u00b3Z\u00be+ \u0091|\u0018u\u0019\u00f1\u00c8\u00cd\u00f7\u0011\u0006\u00e5\u0092\u00bes\u0005\u00edO\u00ebF\u00fd\u00914\u00b2\u00a0\u00ad\u00d2\u0010\u00ceu\u00e1UU_@\u000f\u008a7\u00a00J\u0097\u00d8\u0000\u0018\u008a\u0017/\u00c9f\u00e9\u00ad\u00f4\f\n\u0019\u0090\u00b3\u0014\u00e6\u00cb\u009c\u0095h\u00c1Me\u00eb\u00f5\u0010\u00f9\u00e9\nIX\u00b6C\u00c3D r\u00d0w8@\u00e1p\u00ca\u00e6\u0094\u00b1C17\u0080\u0088\u0017\u00c74MM\u00a18Ov6\u000f\u00cf\u008a\u00fb\u008a\u00ec\u00ec3\u007fHWE\u00e3\u00a0\u00fb\u00d6q\u000exG\"{\u001dk\u00a7\u00c9\r\u00cf>\u00d4\u00ef_\u0092R\u008a\u00aaq\u000e0\u0005\u0015(*\u00d1\u0088\u00b7eNO\u0012\u00cf(\u00e3\u00d8\u00ae\t\u00bcSJrA\u00d6(\u00ad\u00cd\u00c1&\u00c6\u00a7*-\u00efQN\u0018c\u00a1`\r\n+zq\u00de\u0083\u00cd\u00fe<\u00e3\u00c2\u00f9\u00f1\u00a6h\u00cco!'\u00b1\u00e5@\u0019\u00b7Izq\u00b3\u00a11\u0094i\u001e\u00d1\u001a\u0083bd\u000f\u00ddQ\u0001\u00b5\u00cb\u00d0\u00f5t\u00c5\u0007^\u00d1\u00c3\u00f0N\u0092{\u001f\u0092\u00b2\u00eb7\u0003a\u00d3#\u0086\u00969\u0000\u00b3\u00e4c[DS\u00bc-\u001e\u00ad\u00a2}R\u001d\u00e7W]\u001a\u00d0\u0012L\u00a1\u00e5[\u00e5\u00c8\u00b2\u008f~\u0096\u00be|N *\u00a5[7\u001a\u00c6\u00ad\u00d5\u0095\u00e2\u00f8\u00c1\u0017\u00ec^mH\n\u0013\u0019\u0010`\u00cd'\u000b\u0011\u0000D\u0004,4\u00e1\u0018\u00e9\u008e\u00b0+\u00da\u0089\u00b6)\u0016\u00e2mG\u00e4\u00ab\u00cf\u0098{\u000f\u0015\u00b8\u00d6\u00dc\u00cd\u008dh\u00da\u00a1\r\u00a4\u0087\u00fdf\u0095it\u00e1\u00b7\u000f\u0007s\u00bf\u00e7\u00bcdY!H\u00ba\u00cb$n\u00b1\u00afl\u00e2\u0090 Rz\u00ba\u008bC\u009d\u00c4\u00d5\u0005\u00dc0u^\u00c1t\u009fq\u00a5\u00a1aJR9\u00f1\u00f6\"\u00caQfs\u0091\u0007\u0010\u009d\u0095s\u00e7}\n\u0018\u001b\u00e5\u00acM\u00ed\u00e0\u001e\b3@\u00cd,\u000b\u00c5N\u000e\u00eaCk\u00c6X-\u00ab\u0091\u0002Me\u009e1\u00ca\u0099oKp\u00bb\u00a3$p\u00e7\u007fi\u0084\u0019\u0087\u0086_\u00180\u0017\u00e1\u0011Z\u009fhK\u009a\u00a1\u00e2\nhm\u0007\u00eb\u00e5\u00af\u001dx\u0083\u00e0\u00e3\u00e7h\u00cb\u0007\u00105\u00f1\u0087\u00e1Pf\u0002\u0003\u00e9}\u00af\u00c6cVx\u001e\u0010\u0099x'L\u0000\u0085\u00ea\u0094\u00cb\u00a0\u0005\u00d9\u0003!&\u00e5x\u0010|\u00ddsM\u0018E')\u00fe\u001cf\u00e9^\u00a4\u00c7\u0003\u009ar\u00a8\u00a0\u00f7\u000f4s\u0014\u009a\u000b4\u00bcA\u00bf\u00cb\u00dcrX\u00dc\u0091\u00e4<O\u0000\u0001\u00c0\u0014\u00a8\u0001)qh7\u008c\u00b1\u00c4\u0014\u00a2\u00df\u0098\u0015\u00f0\u007f\u00b7\u009f\u0019\u0011_\u00e0\u0083\u009f79\u00a6m\u00dd2\u0093\u00fb\u00cc\u001dQY\u00d59\u00fa\u0087{\u00b3\u0095\u0000\r\u00a9{\u00cd\u00a0\u008c\u00f7\u00aa\u001de\u009aC\u00871\u0081\u00cc{\u00b5\u00d6\u00f7\u00d2\b%`E\u0018\u00c8\u008c\u00e8\u00df\u00e3\u001851\u00cd\u0086\u0004\u00f3C*;R\u00be\u00ba\t\u00bf\u00df\u00f9\u001c\u001f\f\u009d\u00b4\u000e\u00f7@\u0010\u009c\u0014/\u00bd>\u00d3K\u00ca\u001c\u00e7e\u00d6_\u00b6C|\u0010\u001c\u000bF\u0089oE2jgCZoLL\u00d7\u009d\u0010\u0087<\u00ea,\u00e2\u0013\u0080\u0018\u00c6\u0088\u00cc\u0000\u00f1\u0083\u00a7\u00cd\u0010(\u00cf\u00ea.\u0010\u00a5\u00e7\u0005\u0000h\u0088\n\u00bfB\u001e\u00e2\u0010\u0017\u00c0(nB\u001e\u00fdVM[\u0019E\u0092\u00ec\u0012\u00ef";
                            var24_6 = 795;
                            var21_7 = 40;
                            var20_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = TabBar.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00deE\u0006\u0089\u008c\u0018\u00eey\u00dc\u0017\u00e0\u00d9\u00e4/\u00ab\u00a5\u0010dM\u00d0\u0018\u00b4\u008c\u001e{p\u00bcQ\u00e9\u00cd\u008eIB";
                                var24_6 = 33;
                                var21_7 = 16;
                                var20_8 = -1;
lbl35:
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
lbl40:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = TabBar.zkmDecodeUtf8(var26_9).intern();
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
lbl52:
                            // 1 sources

                            ** continue;
                        }
                    }
                    TabBar.b = var25_3;
                    TabBar.c = new String[22];
                    TabBar.m = "\u0439\u0446\u0443\u043a\u0435\u043d\u0433\u0448\u0449\u0437\u0445\u044a\u0444\u044b\u0432\u0430\u043f\u0440\u043e\u043b\u0434\u0436\u044d\u044f\u0447\u0441\u043c\u0438\u0442\u044c\u0431\u044e";
                    TabBar.k = "qwertyuiop[]asdfghjkl;'zxcvbnm,.";
                    TabBar.g = new HashMap<K, V>(13);
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
                    var13_12 = new long[10];
                    var10_13 = 0;
                    var11_14 = "\u00fc\u00e3w\\J}n\u0086\u00be\u0004\u00b6\tnE\u00f9\u008b\u00a9\u00c8\u0090\u00df\u00f6\u000em\u00c4X\u0098\u00e1\u0016\u009c5\u00f1\u0080T\u00e2\u00dc\u00b1\u00e3P\u00e2\u00b2nP\u00d5m\u0001\u0006\u00e3\u00dbB\u00e4\u0012\u001e\u001ex\u00b4\u0080L\u008c\u009b;\u00a4\u00d9\u00d1\u00ad";
                    var12_15 = 64;
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
lbl81:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        var11_14 = "\u0091\u00e8!\t\u0091\u00d1\u00844\u009eWK\u00ad8q\u0005\u00cc";
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
lbl94:
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
lbl107:
                    // 1 sources

                    ** continue;
                }
            }
            TabBar.e = var13_12;
            TabBar.f = new Integer[10];
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
lbl121:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_23 = var2_20.doFinal(new byte[]{-67, -74, 92, 76, 26, 119, 23, 84});
        ** while (true)
        var0_22 = ((long)var6_23[0] & 255L) << 56 | ((long)var6_23[1] & 255L) << 48 | ((long)var6_23[2] & 255L) << 40 | ((long)var6_23[3] & 255L) << 32 | ((long)var6_23[4] & 255L) << 24 | ((long)var6_23[5] & 255L) << 16 | ((long)var6_23[6] & 255L) << 8 | (long)var6_23[7] & 255L;
        TabBar.INSTANCE = new TabBar();
        TabBar.animation = new Animation(Easing.OUT_EXPO, var0_22);
        TabBar.V = "";
        var33_24 = new Tab[]{new Tab(0.0f, "UI", null, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)(), null), new Tab(44.0f, "Windows", null, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$1(), ()Lkotlin/Unit;)(), null), new Tab(88.0f, "Hud", null, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$2(), ()Lkotlin/Unit;)(), null), new Tab(132.0f, "Theme", null, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$3(), ()Lkotlin/Unit;)(), null), new Tab(176.0f, "Search", null, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$4(), ()Lkotlin/Unit;)(), null)};
        TabBar.tabs = CollectionsKt.mutableListOf(var33_24);
        TabBar.n = (Tab)TabBar.tabs.get(0);
        TabBar.w = TabBar.n;
    }

    public static void zkmSetFlag(String string) {
        Q = string;
    }

    public static String zkmGetFlag() {
        return Q;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7481;
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
                throw new RuntimeException("su/catlean/tm", exception);
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
            TabBar.c[n2] = TabBar.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TabBar.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/tm" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x38F6;
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
                throw new RuntimeException("su/catlean/tm", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TabBar.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TabBar.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/tm" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TabBar.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TabBar.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

