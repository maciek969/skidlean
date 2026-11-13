/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import su.catlean.gui.GuiElement;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TextInput
implements GuiElement {
    @NotNull
    private final Function1 action;
    @NotNull
    private String n;
    private int C;
    private boolean R;
    private static Module[] E;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public TextInput(@NotNull Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "action");
        this.action = function1;
        this.n = "";
    }

    @NotNull
    public final Function1 getAction() {
        return this.action;
    }

    @NotNull
    public final String s() {
        return this.n;
    }

    public final void G(@NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextInput.zkmBootstrap("o", (int)10205, (long)(0x2BC753A0F94C82AEL ^ l))));
        this.n = string;
    }

    public final int Y() {
        return this.C;
    }

    public final void X(int n) {
        this.C = n;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, float var2_2, float var3_3, long var4_4, float var6_5, float var7_6) {
        v0 = var4_4;
        v1 = v0 ^ 13851571978927L;
        var8_7 = (int)(v1 >>> 48);
        var9_8 = (int)(v1 << 16 >>> 48);
        var10_9 = (int)(v1 << 32 >>> 32);
        var11_10 = v0 ^ 119935861042784L;
        var15_11 = v0 ^ 127206913172186L;
        var17_12 = v0 ^ 111455786286776L;
        Intrinsics.checkNotNullParameter(var1_1, "context");
        var22_13 = null;
        var23_14 = var2_2 + 43.0f;
        v2 = var6_5 <= var2_2 + 77.0f ? var23_14 <= var6_5 : false;
        if (!v2) ** GOTO lbl-1000
        var23_14 = var3_3 + 25.0f;
        v3 = var7_6 <= var3_3 + 38.0f ? var23_14 <= var7_6 : false;
        if (v3) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = false;
        }
        this.R = v4;
        v5 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
        Shapes2D.drawShape$default(v5, (char)var8_7, var2_2, var3_3, 120.0f, 60.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
        v6 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v6, "pose(...)");
        Shapes2D.drawShape$default(v6, (char)var8_7, var2_2 + 43.0f, var3_3 + 25.0f, 34.0f, 13.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
        v7 = Fonts.getSF_M();
        v8 = StringsKt.isBlank(this.n) != false ? "Cancel" : "Create";
        v9 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v9, "WHITE");
        v7.drawStringWithShadow(var1_1, v8, var11_10, var2_2 + 48.0f, var3_3 + 26.0f, v9);
        v10 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v10, "pose(...)");
        Shapes2D.drawShape$default(v10, (char)var8_7, var2_2 + 5.0f, var3_3 + 5.0f, 110.0f, 13.0f, 3.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
        v11 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v11, "pose(...)");
        var23_15 = new Color[]{new Color(0x44FFFFFF, true)};
        RenderUtil.INSTANCE.drawGradient(v11, var2_2 + 33.0f, var3_3 + 9.0f, 0.5f, 7.0f, var23_15, var17_12);
        v12 = Fonts.getSF_M();
        v13 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v13, "WHITE");
        v12.drawStringWithShadow(var1_1, "Name", var11_10, var2_2 + 8.0f, var3_3 + 6.0f, v13);
        v14 = Fonts.getSF_M();
        v15 = this.n + (System.currentTimeMillis() % 700L > TextInput.h ? "" : "|");
        v16 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v16, "WHITE");
        v14.drawStringWithShadow(var1_1, v15, var11_10, var2_2 + 37.0f, var3_3 + 6.0f, RenderUtil.INSTANCE.withAlpha(v16, 0.8f));
        v17 = Fonts.getSF_M();
        v18 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v18, "WHITE");
        v17.drawStringCentered(var1_1, "Or drag file there", var2_2 + 60.0f, var3_3 + 46.0f, RenderUtil.INSTANCE.withAlpha(v18, 0.4f), var15_11);
    }

    @Override
    public void charTyped(char c) {
        if (this.n.length() < 16) {
            this.n = this.n + c;
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void keyPressed(int var1_1, short var2_2, int var3_3, int var4_4) {
        var5_5 = (long)var2_2 << 48 | (long)var3_3 << 32 >>> 16 | (long)var4_4 << 48 >>> 48;
        var14_6 = (int)((var5_5 ^ 102103657852107L) << 48 >>> 48);
        var15_7 = ZkmReferences.zkmBootstrap("\u00c5", (long)-5841969019304716604L, (long)var5_5);
        if (var15_7 != null) ** GOTO lbl10
        switch (var1_1) {
            case 259: {
                this.n = StringsKt.dropLast(this.n, 1);
lbl10:
                // 2 sources

                v0 = var15_7;
                if (var3_3 >= 0) {
                    if (v0 == null) break;
                }
                ** GOTO lbl17
            }
            case 257: {
                this.submit();
                v0 = var15_7;
lbl17:
                // 2 sources

                if (v0 == null) break;
            }
            case 86: {
                if (!KeyboardUtil.isKeyPressed((char)var14_6, (int)TextInput.zkmBootstrap2("o", (int)6430, (long)(3946043247827098570L ^ var5_5)))) break;
                v1 = GLFW.glfwGetClipboardString((long)Globals.getMc().getWindow().getHandle());
                if (v1 == null) {
                    v1 = "";
                }
                var16_8 = v1;
                this.n = this.n + var16_8;
            }
        }
    }

    @Override
    public boolean mouseClicked(long l, float f, float f2) {
        if (this.R) {
            this.submit();
            return true;
        }
        return false;
    }

    private final void submit() {
        block1: {
            int n = 0;
            long l = 0L;
            long l2 = ((long)n << 48 | l << 16 >>> 16) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7940698560804328986L, (long)l2);
            boolean bl = StringsKt.isBlank(this.n);
            if (callSite == null) {
                bl = !bl;
            }
            if (!bl) break block1;
            this.action.invoke(this.n);
        }
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        E = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return E;
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
                            TextInput.a = ZkmKeys.a(-2530563701017029691L, 6667586799132665232L, MethodHandles.lookup().lookupClass()).a(99086573779598L);
                            var25 = TextInput.a ^ 36475892370747L;
                            TextInput.d = new HashMap<K, V>(13);
                            TextInput.zkmSetFlag(null);
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
                            var23_3 = new String[11];
                            var21_4 = 0;
                            var20_5 = "\u00c4x\u0007\u008e\u00d9\u0084Nxe\u00dd\u00b9R\u00eb\u0086{\u00de\u0010y\u00c4\u001e\u00cd\u00167\u00d0:\u00c1\u00d5f\u0088\u00d1T\u00dec ^\n(\u00cf\u008e\u00cb\u00b1a`/+bry\u0096\u00fdu\u00ed\u00fc\u0081\u00a2!\u0083\u00e8\u00c8\u00b8\\\u009a\u00a3\u00f5 \u00f6\u0010e)\u00b5\u00d3{\u0097\u00a6\u00db06\u00d5N\u00db\u0097\u00cf\u00da\u0010\u00ff8 \u00b9\u00a3-\u009b\u001c\u00a3u\u00aap\u00f0i\u00f9\u00ee\u0010\"\u00c5\u00c7\u00acL+\u00f0\u00cetM \u0081\u00c6\u00da\u0001\u00b7\u0010\u00f4\u0006\u00deA\u008f\u00d1\u0013\u00e4\u00c7\u00eb\u00b4\u00ad]\u009e\u00f6I\u0018\u0005\u00e7=-9\u00f2\u0006\u00ee\u00b7,5\u00e0[\u00feFI\u00fa\u009c\u009a\u0091\u00cd\u0016T\u0015\u0010k\u00b7\u0005\u00e2\u00ab_\u0082\u00be\u00f0\u000e\u000eji\u00fc\t\u0082";
                            var22_6 = 176;
                            var19_7 = 16;
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
                                var23_3[var21_4++] = TextInput.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u0093\f\u00ec$\u00c5\u00deT\u00a2\u00f2\f\u00ec\u00de\u0016\u00d5\u00b0\u008d(\u001e\u008cd\n\u0011:\u00a9\u00e6\u00de#\u00de\u001eXZ\u00e5\u00b2l,{)p\u00b1\u00f0\u000b\u00e6z\u00cd`<\u0088\u00e2\"\u00cdf\u00d6\u00e9Z\u001cm\u00e8";
                                var22_6 = 57;
                                var19_7 = 16;
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
                                var23_3[var21_4++] = TextInput.zkmDecodeUtf8(var24_9).intern();
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
                    TextInput.b = var23_3;
                    TextInput.c = new String[11];
                    TextInput.g = new HashMap<K, V>(13);
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
                    var11_12 = new long[7];
                    var8_13 = 0;
                    var9_14 = "\u0096&\u00d8Vz\u00d6hu\u00bd\u0007\u00a2\u00c2\u00af3\u00e2\u0081)n\u00c6\u009b\u0005\rQ'\u008a\u00e15(\u00c7\u00fc\u00fa\u00d6\u000f\u00fd.\u0019\u00a7\u00181{";
                    var10_15 = 40;
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
                        var9_14 = "\u00ccN\u00b9\u00bf|(M\u00a9\u00ca\u008d\u0087\u0086\u0018S$\u008d";
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
            TextInput.e = var11_12;
            TextInput.f = new Integer[7];
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
        var4_22 = var0_20.doFinal(new byte[]{43, 66, -28, 11, 99, -13, -6, 59});
        ** while (true)
        TextInput.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x48DC;
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
                throw new RuntimeException("su/catlean/rd", exception);
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
            TextInput.c[n2] = TextInput.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TextInput.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2D7F;
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
                throw new RuntimeException("su/catlean/rd", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TextInput.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TextInput.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/rd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TextInput.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TextInput.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

