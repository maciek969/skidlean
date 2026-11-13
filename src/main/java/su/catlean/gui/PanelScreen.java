/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.glfw.GLFW
 */
package su.catlean.gui;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.ArraysKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.input.CharInput;
import net.minecraft.client.input.KeyInput;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import su.catlean.gui.ConfigPanel;
import su.catlean.gui.Element;
import su.catlean.gui.FriendsPanel;
import su.catlean.gui.MacroPanel;
import su.catlean.gui.ProxyPanel;
import su.catlean.gui.SidePanel;
import su.catlean.gui.TabBar;
import su.catlean.gui.TextField;
import su.catlean.gui.WaypointPanel;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;

public final class PanelScreen
extends Screen {
    @NotNull
    public static final PanelScreen INSTANCE;
    @NotNull
    private static Set G;
    @Nullable
    private static Element element;
    @Nullable
    private static SidePanel sidePanel;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long f;

    private PanelScreen() {
        super(Text.of("catlean-panel-screen"));
    }

    @NotNull
    public final Set r() {
        return G;
    }

    public final void s(@NotNull Set set) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(set, (String)((Object)PanelScreen.zkmBootstrap("j", (int)19403, (long)(0x7C049ECC4F0EA664L ^ l))));
        G = set;
    }

    @Nullable
    public final Element getElement() {
        return element;
    }

    public final void setElement(@Nullable Element element) {
        PanelScreen.element = element;
    }

    @Nullable
    public final SidePanel getSidePanel() {
        return sidePanel;
    }

    public final void setSidePanel(@Nullable SidePanel sidePanel) {
        PanelScreen.sidePanel = sidePanel;
    }

    @Override
    protected void init() {
        Iterable iterable = G;
        for (Object t : iterable) {
            SidePanel sidePanel = (SidePanel)t;
            sidePanel.open();
        }
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, float f) {
        long l;
        long l2 = l = a ^ 0x149A4B9DF5E5L;
        long l3 = l2 ^ 0x74111F321FC1L;
        long l4 = l2 ^ 0x47D619441FEEL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        if (Globals.getMc().world == null) {
            this.renderBackground(drawContext, n, n2, f);
        }
        TabBar.INSTANCE.render(drawContext, n, n2, l4);
        element = null;
        Iterable iterable = G;
        for (Object t : iterable) {
            SidePanel sidePanel = (SidePanel)t;
            sidePanel.render(drawContext, n, n2, l3);
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean mouseClicked(@NotNull Click click, boolean bl) {
        SidePanel sidePanel;
        long l = a ^ 0x3AEBDB326E1AL;
        long l2 = l ^ 0x239DAD8DDCF2L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(click, "click");
        double d = click.comp_4798();
        double d2 = click.comp_4799();
        int n4 = click.button();
        if (TabBar.INSTANCE.mouseClicked(d, d2, n4)) {
            return true;
        }
        Iterable iterable = G;
        for (Object t : iterable) {
            sidePanel = (SidePanel)t;
            sidePanel.mouseClicked((float)d, (float)d2, (short)n, n4, n2, (char)n3);
        }
        if (element != null && element instanceof TextField) {
            boolean bl2;
            block7: {
                iterable = G;
                if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                    bl2 = true;
                } else {
                    for (Object t : iterable) {
                        sidePanel = (SidePanel)t;
                        if (sidePanel.getGuiElement() == null) continue;
                        bl2 = false;
                        break block7;
                    }
                    bl2 = true;
                }
            }
            if (bl2) {
                Element element = PanelScreen.element;
                Intrinsics.checkNotNull(element, "null cannot be cast to non-null type su.catlean.client.features.ui.panel.components.EditableLine");
                TextField.Companion.A((TextField)element);
                return super.mouseClicked(click, bl);
            }
        }
        element = null;
        return super.mouseClicked(click, bl);
    }

    @Override
    public boolean mouseReleased(@NotNull Click click) {
        Intrinsics.checkNotNullParameter(click, "click");
        double d = click.comp_4798();
        double d2 = click.comp_4799();
        click.button();
        Iterable iterable = G;
        for (Object t : iterable) {
            SidePanel sidePanel = (SidePanel)t;
            sidePanel.resetState();
        }
        TabBar.INSTANCE.r(d, d2);
        return super.mouseReleased(click);
    }

    @Override
    public boolean mouseScrolled(double d, double d2, double d3, double d4) {
        Iterable iterable = G;
        for (Object t : iterable) {
            SidePanel sidePanel = (SidePanel)t;
            sidePanel.mouseScrolled(d, d2, d4);
        }
        return super.mouseScrolled(d, d2, d3, d4);
    }

    @Override
    public boolean keyPressed(@NotNull KeyInput keyInput) {
        Object object;
        long l;
        long l2 = l = a ^ 0x647372896D33L;
        long l3 = l2 ^ 0x2610D00A1A5L;
        int n = (int)((l2 ^ 0x3D68F056047CL) << 48 >>> 48);
        Intrinsics.checkNotNullParameter(keyInput, "input");
        int n2 = keyInput.comp_4795();
        keyInput.comp_4796();
        keyInput.comp_4797();
        Iterable iterable = G;
        Object object2 = iterable.iterator();
        while (object2.hasNext()) {
            object = object2.next();
            SidePanel sidePanel = (SidePanel)object;
            sidePanel.keyPressed(n2, l3);
        }
        switch (n2) {
            case 259: {
                TextField textField = TextField.Companion.T();
                if (textField != null) {
                    object = object2 = textField;
                    ((TextField)object).setText(StringsKt.dropLast(((TextField)object).getText(), 1));
                }
                break;
            }
            case 258: {
                TextField textField = TextField.Companion.T();
                if (textField != null) {
                    textField.applySuggestion();
                }
                break;
            }
            case 257: {
                TextField.Companion.A(null);
                break;
            }
            case 86: {
                if (!KeyboardUtil.isKeyPressed((char)n, (int)f)) break;
                String string = GLFW.glfwGetClipboardString((long)Globals.getMc().getWindow().getHandle());
                if (string == null) {
                    string = "";
                }
                String string2 = string;
                Object object3 = object2 = TextField.Companion.T();
                if (object3 == null) break;
                ((TextField)object3).setText(((TextField)object2).getText() + string2);
                break;
            }
        }
        return super.keyPressed(keyInput);
    }

    @Override
    public boolean charTyped(@NotNull CharInput charInput) {
        Intrinsics.checkNotNullParameter(charInput, "input");
        String string = charInput.asString();
        Intrinsics.checkNotNullExpressionValue(string, "codepointAsString(...)");
        char[] cArray = string.toCharArray();
        Intrinsics.checkNotNullExpressionValue(cArray, "toCharArray(...)");
        Character c = ArraysKt.firstOrNull(cArray);
        if (c == null) {
            return super.charTyped(charInput);
        }
        char c2 = c.charValue();
        Object object = G;
        Iterator iterator2 = object.iterator();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            SidePanel sidePanel = (SidePanel)t;
            sidePanel.charTyped(c2);
        }
        Object object2 = object = TextField.Companion.T();
        if (object2 != null) {
            ((TextField)object2).setText(((TextField)object).getText() + c2);
        }
        TabBar.INSTANCE.charTyped(c2);
        return super.charTyped(charInput);
    }

    @Override
    protected void applyBlur(@NotNull DrawContext drawContext) {
        Intrinsics.checkNotNullParameter(drawContext, "guiGraphics");
    }

    @Override
    public void renderBackground(@NotNull DrawContext drawContext, int n, int n2, float f) {
        Intrinsics.checkNotNullParameter(drawContext, "guiGraphics");
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    PanelScreen.a = ZkmKeys.a(841528641852823978L, 3006050852065778642L, MethodHandles.lookup().lookupClass()).a(229985054087224L);
                    var14 = PanelScreen.a ^ 98275170870866L;
                    PanelScreen.e = new HashMap<K, V>(13);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[12];
                    var10_4 = 0;
                    var9_5 = "\u001c\u00d3\u00a4\u00f2\u00dd\u00d7\u001aM\u00824\u00aaQ\u0089<\u009d\u009d\u00ac\u0099\u00f7\u00a7J\u00c4Nd\u00e5\u00ab\u00b8v\u00b0\u009a@\u00b3q\u00c7sL\u00eb\u00b3\u001a\u008b/2\u00f6L\u0005u\u0083A\u00a6@\u0013\rggb\u001c\u00c4[\u00c8\u0013\r\u00f7[\\\\s\u000f\u00b5\b\u007f\u0090{\u0019\fo\u00ec\u0091\u0098\u00e2\u00d1j\u00b20\u00fd\u00b4\u008c\u0086&\u00f8={t\u0080\u00d4q\u00deG\u009b{\u00ac\u00c5\u00f7\u0085A\u00c1\u00f0&\u009f\u00d3n\t\u0010\u009e\u00d8F\u000b8\u00b7\u0092B\u0090\u00fd\u00a0\u00f7\u0007\u0086k:+\n\u008c+\u00b7\u00f6\u0090\u001f?\u009c3J\u00f6\u00f0\u00ce\u0098\u00c1\u00bc\u0016\u00bd\u00d4\u0014\u00f0)\u00bb\u001b`\u00da\u001c>\u00fc\f(\u00d0BKR\u009c\u0099\u00e4r\u00d6\u007f\u0014\u0014\u00af\u00b1#\\]\u001b\u00c0\u0080\u00f5\\\u00f7p\u008c\u0081\u009b@\u0012\u0014s\u00e1\u00a4\u0085/4\u008c\u00a3O\u00da(A\u001a\u00a3\u0092,9IX\u001d\u00e6\u009b\u00bbU\u001a\u00f2\u0095L\u0080\u00e9^\u00e1\u009fz\u00ea\u00bcP\u0092\u00b8\u001dcI\"K\u00f03g\u0000\u00cf\u009a\u00d8\u00181\u00b2I\u009aI\u0093\u00e8WN\u0084=\u008b#\u00a5S\u00e6\u00d1\u00da.zO\u00e0fS(>\u00c5;\u009a\f\u00ae\u001c\u00e4\u00bdB\u0015\u00eer,\u00d3|\u00a6dT\u00a8\u00f6\u00df\u00c1\u00f1\u00fe<\u00ef\u0019w\u000ejt\u00ff\u00c5\u008aT\u0097\u0016\u0092]\u0010\u00b9Y\u00a6P\u0011Rz\u00dc\u00d6zs\u0014V\u0089\u008a\u00dc\u0010\u0087\u0015G\u0085c\u00b5O\u0090I\u00a5e\u0097;*\u000f\u00a2\u0010n\u0011\u00e3\u000b|3\u00bf\t\u00a6\u00d1\u00a8\u008c\u00c1tg\u00b8\u0018%\u00ed\u00e8\u0001SO\u00f5L 7#\u001b\u00d4\u00cb9\u00fa\u007f\u00bf\u00a2\u009eA\f.\u00d8\u0010AD\u0092\u007f\u00da\u00a5'\u0092lPY\u00a2\u00dc\u0088\f\u00ee";
                    var11_6 = 401;
                    var8_7 = 160;
                    var7_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = PanelScreen.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00d0\u007fK\u00bf\u0003\u0013@\u00b3\u00f0\u00cc\u0082\u0081@\u00c0\u00bc\u0091\u0010\u00c1\u00db\u009e\u00b4_\u0017\u00fa\u008f\u00bes\u009e\u00cce\u00ae\u00c3K";
                        var11_6 = 33;
                        var8_7 = 16;
                        var7_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = PanelScreen.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            PanelScreen.b = var12_3;
            PanelScreen.c = new String[12];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-38, 29, 55, 37, 80, 104, 37, -73});
        ** while (true)
        PanelScreen.f = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        PanelScreen.INSTANCE = new PanelScreen();
        var19_13 = new SidePanel[]{ConfigPanel.INSTANCE, MacroPanel.INSTANCE, WaypointPanel.INSTANCE, ProxyPanel.INSTANCE, FriendsPanel.INSTANCE};
        PanelScreen.G = SetsKt.setOf(var19_13);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x757E;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/s1", exception);
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
            PanelScreen.c[n2] = PanelScreen.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PanelScreen.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/s1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PanelScreen.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

