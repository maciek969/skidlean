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
import java.net.URI;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.gui.RectButton;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class VersionInfoScreen
extends Screen {
    @NotNull
    private final RenderTarget renderTarget = new RenderTarget("warning", 32, 32);
    @NotNull
    private final RectButton rightButton;
    @NotNull
    private final RectButton leftButton;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public VersionInfoScreen() {
        super(Text.of("catlean-version-info-screen"));
        Object[] objectArray = new Object[]{};
        String string = "gui.version.yes";
        this.rightButton = new RectButton(0.0f, 0.0f, 100.0f, 25.0f, I18n.INSTANCE.translate(string, objectArray), VersionInfoScreen::_init_$lambda$0, 0, null, null, 0.0f, 0.0f, 1984, null);
        Object[] objectArray2 = new Object[]{};
        String string2 = "gui.version.no";
        this.leftButton = new RectButton(0.0f, 0.0f, 100.0f, 25.0f, I18n.INSTANCE.translate(string2, objectArray2), VersionInfoScreen::_init_$lambda$1, 0, null, null, 0.0f, 0.0f, 1984, null);
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, float f) {
        Object object;
        long l;
        long l2 = l = a ^ 0x31E08D19FEA6L;
        long l3 = l2 ^ 0x63F7601CA05FL;
        long l4 = l2 ^ 0x181FFE20A13DL;
        long l5 = l2 ^ 0x7518984F7C3DL;
        long l6 = l2 ^ 0x6CA963644126L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        float f2 = (float)this.width / 2.0f;
        float f3 = (float)this.height / 2.0f;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Object object2 = new Color[]{new Color(-1072689136)};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack, 0.0f, 0.0f, this.width, this.height, (Color[])object2, l5);
        Object[] objectArray = new Object[]{};
        String string = "gui.version.confirm";
        String[] stringArray = new String[]{", "};
        object2 = StringsKt.split$default((CharSequence)I18n.INSTANCE.translate(string, objectArray), stringArray, false, 0, 6, null);
        float f4 = 0.0f;
        Iterator iterator2 = object2.iterator();
        while (iterator2.hasNext()) {
            object = (String)iterator2.next();
            FontRenderer fontRenderer = Fonts.getSF_B3();
            Color color = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
            fontRenderer.drawStringCentered(drawContext, (String)object, f2, f3 - 10.0f + f4, RenderUtil.INSTANCE.withAlpha(color, 0.5f), l3);
            f4 += 23.0f;
        }
        int n3 = (int)((double)System.currentTimeMillis() / 3.0 % 360.0);
        n3 = n3 >= 180 ? 360 - n3 : n3;
        Color color = Color.RED;
        Intrinsics.checkNotNullExpressionValue(color, "RED");
        Color color2 = Color.YELLOW;
        Intrinsics.checkNotNullExpressionValue(color2, "YELLOW");
        object = RenderUtil.INSTANCE.blend(color, color2, (float)n3 / 360.0f);
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        RenderTarget.drawRoundedText$default(this.renderTarget, (Matrix3x2f)matrix3x2fStack2, (double)f2 - 16.0, (double)f3 - 60.0, 0.0f, 0.0f, (Color)object, null, 0.0f, 216, l4, null);
        this.leftButton.render(f2 - 110.0f, f3 + 40.0f, n, l6, n2, drawContext);
        this.rightButton.render(f2 + 10.0f, f3 + 40.0f, n, l6, n2, drawContext);
    }

    @Override
    public boolean mouseClicked(@NotNull Click click, boolean bl) {
        Intrinsics.checkNotNullParameter(click, "click");
        double d = click.comp_4798();
        double d2 = click.comp_4799();
        this.rightButton.mouseClicked((float)d, (float)d2);
        this.leftButton.mouseClicked((float)d, (float)d2);
        return super.mouseClicked(click, bl);
    }

    @Override
    public void close() {
    }

    private static final Unit _init_$lambda$0() {
        String string = "https://catlean.su/";
        Util.getOperatingSystem().open(URI.create(string));
        return Unit.INSTANCE;
    }

    private static final Unit _init_$lambda$1() {
        Globals.getMc().close();
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        VersionInfoScreen.a = ZkmKeys.a(3256825738675468887L, -3497458798806108626L, MethodHandles.lookup().lookupClass()).a(255241071892958L);
                        VersionInfoScreen.d = new HashMap<K, V>(13);
                        var11 = VersionInfoScreen.a ^ 76377555746698L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[14];
                        var18_4 = 0;
                        var17_5 = "\b\u00ac\u0090\u00de\u00d7p\u0016^u=\u0000\u00e9_\u00a2\n\u00e5\u000fm#\u00c6h/=\u00e9\u0010\u00f6\u00bf\u00f0V\u00f7\u00e0K\u00ba\u0092\u000f\u0014\u00cd:\u0015N\u009c\u0010!m|\u0015'\u007fb\u00ca\u0017\u0004\u00993\u0088\u00e8`\u008a\u0018 K\u007f\u000fq{\u00c9\u000f\u00f6*\u00d0\u00b5\u0013\u00c2\u00c6\u00c3\u0002Z|\u0001y)'\u0089(P&:\u00ef\u00dd2\u00d5Oe\u0089\u00b8\u0014!\u00f4\u00be6\u0095C\u00bf\\e\b\u0018\u0097\u00d3\u00d8\u00f0v\u00e4md\u00f3\u00eb\u0002k\u00fc\u0096+V\u00b2(m\u00fa\u00c5\u0016zX\u0013#Bf\u00e9\u0096\u009d\u0007\b\u00c2\u009c\u0001\u0006\u0099F,\u009d\u00a7\u0013Q\u008179\u00dd\u0000\u008a\u00b6\u0013\u00c0\u00bb\u0010j\u00c3Q0\u008co+3\u00df\u0090&\u000e\u00ea\u00c7\u00f4\u00a1j\u0018\"\u0082\u00e9vl\u00b1\u00b2Fben\u0095,P\u00fe\u00e7\u0080\u0001\u00c9J\u00adC\u0017f\u00b0o\u00e0\u0088\u0098\u00f5?)\u0096\u001a\u0010\u0018;A\u00ee(>0\u008d\u009c\u000bzsB\u0002g/\u0018a\t\u00186\u00dd%R4\u00b8\u00fc|\u00eefL\u00d4\u00ceP\u0089\u00a6\u00af1\u00e1d\u00b2\u0010!\u00900\u0002\u00efu\u00fa\u008f3fm\u00ea\u00e0}g\u00fb\u0010 8\u0094q\u00b3\u00c4\u00ceA\u0015\r\u00d1S%Y\u00bbE\u0010\u009e\u00cc\u00e0U\u00d7\u00f0<)\u00a6\u0081\u0080H\u00f1T\u0098\u00df";
                        var19_6 = 307;
                        var16_7 = 24;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = VersionInfoScreen.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\r\u00ff\u0003\u00d0+\u0083\u00d1^\u00e9\u009d\u00f9\u001d\u0099\u009f\u00e0e\u0018v\u00c7G\u00bd\u00f7\u00c1K\u008f\u00ea\u00e4.\u0019?B\u0097\u00ac\u0088\u00fc\u00ee\u0087W\u0014\u0089\u009f";
                            var19_6 = 41;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = VersionInfoScreen.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                VersionInfoScreen.b = var20_3;
                VersionInfoScreen.c = new String[14];
                VersionInfoScreen.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[10];
                var3_13 = 0;
                var4_14 = "J\u00df\u0003!-\u00a1\u00d9\u001b\u00ca\u0013\u0095\u009e\u0085'\u0011\u001f \u00e2k\u00e8\u0007\u00a9\u00f4\u0088\u00b7\u00f6\u00e2)\u0003\u00adQ\u00bdK\u00a0\u0089\u00d1'}\u0007\u00a4}\u0084p\u0091\u009a3*P\u00e9\u009ah\u0016W\u00c4\u0019OmHg}\r\u00b7\u00c1\u0011";
                var5_15 = 64;
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00f3ho3\u00bb]\u00a1\u00b0\u0096\u00e2w7{\u00f4\u00ff\u0096";
                    var5_15 = 16;
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        VersionInfoScreen.f = var6_12;
        VersionInfoScreen.g = new Integer[10];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x185F;
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
                throw new RuntimeException("su/catlean/d7", exception);
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
            VersionInfoScreen.c[n2] = VersionInfoScreen.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = VersionInfoScreen.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/d7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3643;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/d7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            VersionInfoScreen.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = VersionInfoScreen.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/d7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(VersionInfoScreen.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(VersionInfoScreen.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

