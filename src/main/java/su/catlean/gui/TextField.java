/*
 * Decompiled with CFR 0.152.
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
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.command.Suggestions;
import su.catlean.gui.Element;
import su.catlean.gui.PanelScreen;
import su.catlean.gui.SidePanel;
import su.catlean.gui.TextField$Companion;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class TextField
extends Element {
    @NotNull
    public static final TextField$Companion Companion;
    @NotNull
    private final String name;
    private float width;
    @NotNull
    private Suggestions suggestions;
    @NotNull
    private final Function1 onChange;
    @NotNull
    private String text;
    @Nullable
    private static TextField focused;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map i;
    private static final long j;

    public TextField(@NotNull String string, float f, @NotNull Suggestions suggestions, @NotNull Function1 function1) {
        Intrinsics.checkNotNullParameter(string, "name");
        Intrinsics.checkNotNullParameter(suggestions, "suggestions");
        Intrinsics.checkNotNullParameter(function1, "onChange");
        this.name = string;
        this.width = f;
        this.suggestions = suggestions;
        this.onChange = function1;
        this.text = "";
    }

    public TextField(String string, float f, Suggestions suggestions, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        suggestions = new Suggestions(CollectionsKt.emptyList());
        this(string, f, suggestions, function1);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    @NotNull
    public final Suggestions getSuggestions() {
        return this.suggestions;
    }

    public final void setSuggestions(@NotNull Suggestions suggestions) {
        Intrinsics.checkNotNullParameter(suggestions, "<set-?>");
        this.suggestions = suggestions;
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    public final void setText(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "value");
        this.text = string;
        this.onChange.invoke(this);
    }

    /*
     * Could not resolve type clashes
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2, float var4_3, float var5_4, float var6_5, float var7_6) {
        block9: {
            v0 = var1_1;
            v1 = v0 ^ 122872324218116L;
            var10_7 = (int)(v1 >>> 48);
            var11_8 = (int)(v1 << 16 >>> 48);
            var12_9 = (int)(v1 << 32 >>> 32);
            var13_10 = v0 ^ 15727589290443L;
            var17_11 = v0 ^ 18592005668209L;
            var19_12 = v0 ^ 6627968639251L;
            v2 = Element.java_lang_String_zkmGetFlag();
            Intrinsics.checkNotNullParameter(var3_2, "context");
            var39_13 = v2;
            v3 = var4_3 <= var6_5 ? var6_5 <= var4_3 + (this.width - 88.0f) : false;
            if (!v3) ** GOTO lbl-1000
            v4 = var5_4 <= var7_6 ? var7_6 <= var5_4 + 13.0f : false;
            if (!v4) ** GOTO lbl-1000
            var41_14 /* !! */  = (Color[])PanelScreen.INSTANCE.r();
            if (var41_14 /* !! */  instanceof Collection && ((Collection)var41_14 /* !! */ ).isEmpty()) {
                v5 = true;
            } else {
                for (T var44_17 : var41_14 /* !! */ ) {
                    var45_18 = (SidePanel)var44_17;
                    if (var45_18.getGuiElement() == null) continue;
                    v5 = false;
                    break block9;
                }
                v5 = true;
            }
        }
        if (v5) {
            v6 = true;
        } else lbl-1000:
        // 3 sources

        {
            v6 = var40_19 = false;
        }
        if (var40_19) {
            PanelScreen.INSTANCE.setElement(this);
        }
        v7 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v7, "pose(...)");
        v8 = var40_19 != false ? 2.0f : 0.0f;
        v9 = Theme.INSTANCE.getWidgetBlurColor().darker();
        Intrinsics.checkNotNullExpressionValue(v9, "darker(...)");
        var24_20 = null;
        var25_21 = 14336;
        var26_22 = 0.0f;
        var27_23 = 0.0f;
        var28_24 = 0.0f;
        var29_25 = Theme.INSTANCE.getOutlineColor();
        var30_26 = v9;
        var31_27 = 1.0f;
        var32_28 = 0.0f;
        var33_29 = v8;
        var34_30 = 2.0f;
        var35_31 = 13.0f;
        var36_32 = this.width - 88.0f;
        var37_33 = var5_4;
        var38_34 = var4_3;
        Shapes2D.drawShape$default(v7, (char)var10_7, var38_34, var37_33, var36_32, var35_31, var34_30, var33_29, var32_28, var31_27, var30_26, var29_25, var28_24, (short)var11_8, var27_23, var12_9, var26_22, var25_21, var24_20);
        v10 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v10, "pose(...)");
        var41_14 /* !! */  = new Color[]{new Color(0x44FFFFFF, true)};
        RenderUtil.INSTANCE.drawGradient(v10, var4_3 + 30.0f, var5_4 + 2.0f, 0.5f, 9.0f, var41_14 /* !! */ , var19_12);
        v11 = Fonts.getSF_M();
        v12 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v12, "WHITE");
        v11.drawStringCentered(var3_2, this.name, var4_3 + 14.0f, var5_4 + 5.5f, v12, var17_11);
        var41_14 /* !! */  = this.suggestions.suggest(this.getText());
        var42_35 = (CharSequence)var41_14 /* !! */ ;
        if (!(var42_35 == null || var42_35.length() == 0)) {
            v13 = Fonts.getSF_M();
            v14 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v14, "WHITE");
            v13.drawStringWithShadow(var3_2, (String)var41_14 /* !! */ , var13_10, var4_3 + 38.0f, var5_4, RenderUtil.INSTANCE.withAlpha(v14, 0.5f));
        }
        if (this.getText().length() > 26) {
            v15 = this.getText().substring(this.getText().length() - 27, this.getText().length() - 1);
            v16 = v15;
            Intrinsics.checkNotNullExpressionValue(v15, "substring(...)");
        } else {
            v16 = this.getText();
        }
        var42_35 = v16;
        var43_16 = Intrinsics.areEqual(TextField.focused, this) != false && Globals.now() % 700L > TextField.j;
        v17 = Fonts.getSF_M();
        v18 = var40_19 == false && (StringsKt.contains$default((CharSequence)this.name, "Login", false, 2, null) != false || StringsKt.contains$default((CharSequence)this.name, "Pass", false, 2, null) != false) ? "**************" : (String)var42_35 + (var43_16 != false ? "|" : "");
        v19 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v19, "WHITE");
        v17.drawStringWithShadow(var3_2, (String)v18, var13_10, var4_3 + 38.0f, var5_4 + 0.5f, v19);
    }

    @Override
    public boolean isHovered(float f, float f2) {
        return false;
    }

    @Nullable
    public final Unit applySuggestion() {
        Unit unit;
        String string = this.suggestions.suggest(this.getText());
        if (string != null) {
            String string2 = string;
            this.setText(string2);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit;
    }

    public static final TextField getFocused() {
        return focused;
    }

    public static final void setFocused(TextField textField) {
        focused = textField;
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
                            TextField.b = ZkmKeys.a(5052450183283551086L, 5507643534843729265L, MethodHandles.lookup().lookupClass()).a(193059715013021L);
                            TextField.e = new HashMap<K, V>(13);
                            var16 = TextField.b ^ 72928682163331L;
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
                            var22_5 = "\u0000\u0014\u001a\u00b3\u00cbE\u00b1q\u008c\u0085\u00a1O\u009eXM\u00ee \u008b\u0088=\u00b9\u00e9\u00bb\u0016\u0004\u00bf/4\u00c8\u00af\u009f\u00a0\u00e3\u000b\u0014U\u00a8w\u009fM!\u00e3\u0011\u0016\u00a5\u00f2\u00f41\u00c7\u0010X\u0083!ZaL\u009d\u00ab\u00fb7'=\t\u00f7\tx \u00fd\u00eaj=\u0097\u000fs\u00ac\t\u008c\u00f9\u009a\u00e8\u00b1*y\u009f\u00b6\u00a4\u00fa\u0085\u00b5P\u00d9\u0099\u0085\u00f2Cw~%\u0011\u0018\u00e4\u00b4\u00d1N\u00ad\u00bd\u00a9\u0080\u0006\u00df\u00f5\u00f5\u0013\u00b8\u0082\u00f2(<p\u008fr:\u00a7/\u0010\u00caD\u00d5\fY\u00falr\u00e9\u00e0A\u001e\u009bj\u0015&\u0010\u00cb\u00ca\u00eb\u0010\u009d[\u00e0\u00a5g\u0018\u0011\u00c9\u0082\u00bad] \u00b1\u0010\u00c8\u0081\u00e6\u00d7\u00e5k/W\u00c2\u008c\t\u00e3L\u0082\u00cd\u00d7F\u001b\u0083\u00ccKxE\u0006\u00ee\r2sMn\u0018\u0091&\u0016\u00cb\u00af\u0015/wi\"fY\u00fa\u00bfv\u00acQ\u00be\u00f2\u000b\u00adpB\u0086 L]\u00fb\u00e9\u00ef\u00f0.\u00d1a\u00a7\u00e6RG\u00dd9b\b\u0014\u0016\u009f\u00bf\u0000\n\u00ffZ\u00f3+\u0000\u00db'_\n\u0010[\u00b4q\u009bz\u008e\u00fd\u001csy\u00d6\u00df\u00a3j\u0099\u00d7\u0010c\u008e\u00cc\u00f7F\u00b7\u001e)\u00d1v\u0085\u00b6\u007f!\u00d1`\u0010^\u00ea\u00d4\u00de\u0015\u00b1a\u00e8Jf\u00a8J\u0091\u007f\u00e4\u0095";
                            var24_6 = 300;
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
                                var25_3[var23_4++] = TextField.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00fbk\u00c7\u00fc\u0080\\5\u0093\u0005\u0099\u0013m11R\u00bas\u00bb\u00c8?\u00b0K\u0092d\u00c8\u00d0\u001c\u00da\u0080{\u009at\u0010\u00bc$\u00f6\u009e\u00aa+r}\u00c0/s\u00ef\u00bd\u00b7b\u0001";
                                var24_6 = 49;
                                var21_7 = 32;
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
                                var25_3[var23_4++] = TextField.zkmDecodeUtf8(var26_9).intern();
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
                    TextField.c = var25_3;
                    TextField.d = new String[15];
                    TextField.i = new HashMap<K, V>(13);
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
                    var11_12 = new long[5];
                    var8_13 = 0;
                    var9_14 = "k\u00c2\u001f\u00bc\u0099\u0093\u00a1\u00d8\u00ae\u0018\u00e08ac\u00ab\u00b5\u0005\u00f0\u0014\u00b0\u00d1Q\u00be=";
                    var10_15 = 24;
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
                        var9_14 = "\u00bc\u009fM\u0087h*\u00f8l\u0095^hO.\u00e8A.";
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
            TextField.f = var11_12;
            TextField.g = new Integer[5];
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
        var4_22 = var0_20.doFinal(new byte[]{-71, -77, 44, -39, 104, -36, 79, -60});
        ** while (true)
        TextField.j = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
        TextField.Companion = new TextField$Companion(null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1D7C;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/fz", exception);
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
            TextField.d[n2] = TextField.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TextField.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x685C;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/fz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TextField.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TextField.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/fz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TextField.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TextField.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

