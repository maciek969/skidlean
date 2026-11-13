/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render.font;

import java.awt.Font;
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
import java.util.Objects;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.CatLean;
import su.catlean.render.font.FontRenderer;
import su.catlean.zkm.ZkmKeys;

public final class Fonts {
    public static FontRenderer SF_M;
    public static FontRenderer SF_M2;
    public static FontRenderer SF_M3;
    public static FontRenderer SF_M4;
    public static FontRenderer SF_B;
    public static FontRenderer SF_B2;
    public static FontRenderer SF_B3;
    public static FontRenderer SF_B4;
    public static FontRenderer SF_SB;
    public static FontRenderer SF;
    @NotNull
    private static final List R;
    private static String[] J;
    private static final long b;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;

    @NotNull
    public static final FontRenderer getSF_M() {
        FontRenderer fontRenderer = SF_M;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_M");
        return null;
    }

    public static final void setSF_M(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_M = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_M2() {
        FontRenderer fontRenderer = SF_M2;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_M2");
        return null;
    }

    public static final void setSF_M2(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_M2 = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_M3() {
        FontRenderer fontRenderer = SF_M3;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_M3");
        return null;
    }

    public static final void setSF_M3(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_M3 = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_M4() {
        FontRenderer fontRenderer = SF_M4;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_M4");
        return null;
    }

    public static final void setSF_M4(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_M4 = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_B() {
        FontRenderer fontRenderer = SF_B;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_B");
        return null;
    }

    public static final void setSF_B(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_B = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_B2() {
        FontRenderer fontRenderer = SF_B2;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_B2");
        return null;
    }

    public static final void setSF_B2(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_B2 = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_B3() {
        FontRenderer fontRenderer = SF_B3;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_B3");
        return null;
    }

    public static final void setSF_B3(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_B3 = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_B4() {
        FontRenderer fontRenderer = SF_B4;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_B4");
        return null;
    }

    public static final void setSF_B4(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_B4 = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF_SB() {
        FontRenderer fontRenderer = SF_SB;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF_SB");
        return null;
    }

    public static final void setSF_SB(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF_SB = fontRenderer;
    }

    @NotNull
    public static final FontRenderer getSF() {
        FontRenderer fontRenderer = SF;
        if (fontRenderer != null) {
            return fontRenderer;
        }
        Intrinsics.throwUninitializedPropertyAccessException("SF");
        return null;
    }

    public static final void setSF(@NotNull FontRenderer fontRenderer) {
        Intrinsics.checkNotNullParameter(fontRenderer, "<set-?>");
        SF = fontRenderer;
    }

    @NotNull
    public static final List b() {
        return R;
    }

    @NotNull
    public static final Font loadFont(float f, @NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        Font font = Font.createFont(0, Objects.requireNonNull(CatLean.class.getClassLoader().getResourceAsStream("assets/catlean/fonts/" + string + ".ttf"))).deriveFont(0, f);
        Intrinsics.checkNotNullExpressionValue(font, "deriveFont(...)");
        return font;
    }

    @NotNull
    public static final FontRenderer createRenderer(float f, @NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        FontRenderer fontRenderer = new FontRenderer(Fonts.loadFont(f, string), Fonts.loadFont(f, "noto"), f);
        R.add(fontRenderer);
        return fontRenderer;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                Fonts.b = ZkmKeys.a(-322321680349859716L, -196398282025928332L, MethodHandles.lookup().lookupClass()).a(191101755471682L);
                var9 = Fonts.b ^ 54978020664727L;
                Fonts.f = new HashMap<K, V>(13);
                Fonts.zkmSetFlag(new String[1]);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[18];
                var5_4 = 0;
                var4_5 = "\f\u00f2fA\u00ddI\u00d8jU\u009a\u00fe\u00b4\u00ab\u00fce\u00fe\u0010\u00a2\u00d5\u00dc\u0083'\u008e\u00da?\u00c3\u00e5\u0088\u00d6\u00861f\u00d7\u0010&0`\u0000\u0089\u0016(-@.\u00ca\u00afC\u0001n1\u0010\u00cbA\u00d5nf\u00b5\u0015X\u001e\u00e7\u00c3\u0089-x\u0001Y\u0010\u00ca\u00f9\u001e3\nr\u00aa>%\u00d4\u009c\u00e2\u000e\u00858z\u0010\u00ddJM{\u00c4\u0091P\u008c.\u00b0\u00c3G\u0001\u00e8\u00adY\u0010\u00f0\u0085\f\u0085\u00d96s\u00a4k\u00a8\u000fbhK\u009b\u00b5\u0010\u00d7P\u00fb\u0011v\b)\u0016\u00fb\u00f8]8\u00f9\u00cfB\u00e7(@DM\u0088Y$\u0018\u00cc\u00c1\u001e\u00fe\u00cf\u008bX\u0089Aj\u0004r|3\u00cd\u00a3\u000f\u00cc+\u001c\u00a3\u001e\u00aa\u009fa \u00ab\u00cfn\u007f\u00be\u00d0a\u0010z\u00ecKE\u0003\u00f3\u0010\u008b\u00a90\u00c2\u00f5\u00fc\u00a5\u00f1I u\u0097_s\u00ab\"'\u00a7r\u00db\u009f\u00dab\u00b4\u00b5\t\u001e\u0094e\u00fao.\u00ed\u0083\u008d,(\u0016;?IT\u0010\u0019\u00a46\u00d5!W@\u00e7\u00f1\u00bc\u00a0\u0087\u0086\u00de=\u00fa\u0010\u00b6L\u00df\u00f8\u00d5\u00e7u\u00e8\u0007\u009f+$SA\u00f6y\u0010\u00b4!S:\u0014\u0085\u0018\u00a2\u00c9\u001a\u000e\u00a45\u0011e\b\u0010\u00b2r1S\u007f\u00dd\u00de\u0090CYk\u0096\u00a8\u001eJD\u0010\u00f6T\u00d7\u00f7\u00f97\u001fbuK\u001c\u00ed\u00ef\u00b4\u00a8\u007f";
                var6_6 = 311;
                var3_7 = 16;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = Fonts.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u0011k\u00b3q\u00fa\u00ca\u00e1o\u0010J\u001cv\u00d6\u00f6\u00d3U\u0010*m9\u0002\u0083:|\u00eb\u00c7\u000b'\u00bf\u0081)\u00fbL";
                    var6_6 = 33;
                    var3_7 = 16;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = Fonts.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        Fonts.d = var7_3;
        Fonts.e = new String[18];
        Fonts.R = new ArrayList<E>();
    }

    public static void zkmSetFlag(String[] stringArray) {
        J = stringArray;
    }

    public static String[] zkmGetFlag() {
        return J;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x54BB;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/b8", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            Fonts.e[n2] = Fonts.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Fonts.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/b8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Fonts.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

