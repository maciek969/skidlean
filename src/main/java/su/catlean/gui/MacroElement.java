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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ModuleConfig;
import su.catlean.gui.Element;
import su.catlean.gui.MacroPanel;
import su.catlean.macro.ChatMacro;
import su.catlean.macro.Macro;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.KeyNames;
import su.catlean.zkm.ZkmKeys;

public final class MacroElement
extends Element {
    @NotNull
    private Macro macros;
    @NotNull
    private final Animation animation;
    private static boolean o;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;
    private static final long i;

    public MacroElement(@NotNull Macro macro) {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(macro, (String)((Object)MacroElement.zkmBootstrap("g", (int)9797, (long)(0x383E45F7BBED173L ^ l))));
        this.macros = macro;
        this.animation = new Animation(Easing.OUT_QUINT, i);
    }

    @NotNull
    public final Macro getMacros() {
        return this.macros;
    }

    public final void setMacros(@NotNull Macro macro) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(macro, (String)((Object)MacroElement.zkmBootstrap("g", (int)27145, (long)(0x7AC495DDBECD41B2L ^ l))));
        this.macros = macro;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2, float var4_3, float var5_4, float var6_5, float var7_6) {
        v0 = var1_1;
        v1 = v0 ^ 122872324218116L;
        var10_7 = (int)(v1 >>> 48);
        var11_8 = (int)(v1 << 16 >>> 48);
        var12_9 = (int)(v1 << 32 >>> 32);
        var17_10 = v0 ^ 15727589290443L;
        var23_11 = v0 ^ 18592005668209L;
        Intrinsics.checkNotNullParameter(var3_2, "context");
        v2 = var4_3 <= var6_5 ? var6_5 <= var4_3 + 70.0f : false;
        if (!v2) ** GOTO lbl-1000
        v3 = var5_4 <= var7_6 ? var7_6 <= var5_4 + 25.0f : false;
        if (v3) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = false;
        }
        var29_12 = v4;
        MacroPanel.INSTANCE.setFocusedElement(var29_12 != false ? (Element)this : MacroPanel.INSTANCE.getFocusedElement());
        this.animation.animate(var29_12 != false ? 1.0f : 0.0f);
        var30_13 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), this.macros.isEnabled() != false ? 1.0f : 0.0f);
        var31_14 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveModuleOutlineColor(), Theme.INSTANCE.getModuleOutlineColor(), this.macros.isEnabled() != false ? 1.0f : 0.0f);
        v5 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
        Shapes2D.drawShape$default(v5, (char)var10_7, var4_3 + 4.0f, var5_4, 70.0f, 25.0f, 2.0f, 2.5f + this.animation.getValue(), 1.0f, 1.0f, var30_13, var31_14, 0.0f, (short)var11_8, 0.0f, var12_9, 0.0f, 14336, null);
        v6 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v6, "pose(...)");
        Shapes2D.drawShape$default(v6, (char)var10_7, var4_3 + 54.0f, var5_4 + 5.0f, 15.0f, 15.0f, 2.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var11_8, 0.0f, var12_9, 0.0f, 14336, null);
        var32_15 = var4_3 + 54.0f;
        v7 = var6_5 <= var4_3 + 69.0f ? var32_15 <= var6_5 : false;
        if (v7) {
            var32_15 = var5_4 + 5.0f;
            v8 = var7_6 <= var5_4 + 20.0f ? var32_15 <= var7_6 : false;
            if (v8) {
                MacroPanel.INSTANCE.t(true);
            }
        }
        v9 = Fonts.getSF_M();
        v10 = this.macros.getId();
        v11 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v11, "WHITE");
        v9.drawStringWithShadow(var3_2, v10, var17_10, var4_3 + 6.0f, var5_4, v11);
        var33_17 = this.macros;
        var32_16 = var33_17 instanceof ChatMacro != false ? "chat" : (var33_17 instanceof ModuleConfig != false ? "item" : "setting");
        v12 = Fonts.getSF_M();
        v13 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v13, "WHITE");
        v12.drawStringWithShadow(var3_2, var32_16, var17_10, var4_3 + 6.0f, var5_4 + 11.0f, RenderUtil.INSTANCE.withAlpha(v13, 0.7f));
        var3_2.getMatrices().pushMatrix();
        var34_18 = var33_17 = KeyNames.INSTANCE.getKeyName(this.macros.getBind().getKey());
        if (var33_17.length() > 1) {
            var3_2.getMatrices().translate(var4_3 + 54.0f + 7.5f, var5_4 + 5.0f + 7.5f);
            var3_2.getMatrices().scale(0.5f, 0.5f);
            var3_2.getMatrices().translate(-(var4_3 + 54.0f + 7.5f), -(var5_4 + 5.0f + 7.5f));
            if (var33_17.length() > 5) {
                v14 = var33_17.substring(0, 4);
                Intrinsics.checkNotNullExpressionValue(v14, "substring(...)");
                var34_18 = v14;
            }
        }
        v15 = Fonts.getSF_M2();
        v16 = Intrinsics.areEqual(MacroPanel.INSTANCE.getEditingMacro(), this) != false ? StringsKt.dropLast("...", 3 - (int)(4.0 * ((double)(System.currentTimeMillis() % 700L) / 700.0))) : var34_18;
        v17 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v17, "WHITE");
        v15.drawStringCentered(var3_2, (String)v16, var4_3 + 61.5f, var5_4 + 11.0f, RenderUtil.INSTANCE.withAlpha(v17, 0.7f), var23_11);
        var3_2.getMatrices().popMatrix();
    }

    @Override
    public boolean isHovered(float f, float f2) {
        return false;
    }

    public static void zkmSetFlag(boolean bl) {
        o = bl;
    }

    public static boolean boolean_zkmGetFlag() {
        return o;
    }

    public static boolean B() {
        return true;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block19: {
            block18: {
                block17: {
                    block16: {
                        block15: {
                            MacroElement.a = ZkmKeys.a(-4546518024628420997L, 3817716180005358337L, MethodHandles.lookup().lookupClass()).a(252118035970812L);
                            var25 = MacroElement.a ^ 6647273870432L;
                            MacroElement.e = new HashMap<K, V>(13);
                            MacroElement.zkmSetFlag(false);
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
                            var23_3 = new String[12];
                            var21_4 = 0;
                            var20_5 = "\u0081\u0098\u00cd\u00fe\u0000kR\u0014\u00b3\u00ac\u0003\u0080\u00f7''O@\u0087M\u00b1[&\u00dd\u009a m\u0013}\u00f7\u00f8\u00bf\"\u00a0^7\u009b05\u008eG\u00d3\u008d\u0084$BK\u0014\u00f1\u0089\u00baN\u00d6\u00b2\u00e0\u009c\u0080\u00f5\u0010\u00edWvb\u0081H)\u00f3]\u009c\u00b7\u00f6\u00e1\u00c7`,\u0010\u00c88\u00d2g\u00fa\u0017q/\u0089s\u00d8S\u009f\u00f8\u00e9\u0002\u0010N/l\u00bc\u00bc\u0002$\u00f3 \u00f0\u00e8~\u0000C\u008a\u00e3\u0018\u00d8\u00e0X%O\u009cM<\u00b2\u00a1\u00db\u00d1(BA\u0094c\u0091aK!'\u00f9\u00b1\u0010\u0005$\u00b0\u0011\u0019\u00f5XQ<\b\u00a8\u0096\u00e8\u00b2Ek\u0010\u00f6L\u00f2S]\u0080\u00a4\u000e\u00d0!VS\u00f3\u00183\u00ef\u0010\u00bc\u0088`)\u00f5\u00a6\u00b3\u00b7\u00da8\u0087O\u00b0k\u0094P\u0010\u00f2\u009d\u00c4\u00f4\u0082 V:?\u0012\u00a3q\u00f0o\u0017b";
                            var22_6 = 201;
                            var19_7 = 24;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block15;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = MacroElement.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "[!\u00fbk\u00e3\u0086c\u001b\u0000\u0019\u0092\u00ff\u00ab\u00eag}\u0010\r\u009d\u0082\u0006f\u00a2\u001a\u00ff\u00da/\u00f6I\u00b1D\u00b3\u00b9";
                                var22_6 = 33;
                                var19_7 = 16;
                                var18_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block15;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = MacroElement.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block16;
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
                    MacroElement.c = var23_3;
                    MacroElement.d = new String[12];
                    MacroElement.h = new HashMap<K, V>(13);
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
                    var11_12 = new long[3];
                    var8_13 = 0;
                    var9_14 = "Ti{\u009f\u00e5%\u00dcI\u00cf[\u00f4S\u00bb\u00bd\u00f3\u0004\u0097\u00ac\u00ca\u0018q\u007f\u00a4l";
                    var10_15 = 24;
                    var7_16 = 0;
                    while (true) {
                        break block17;
                        break;
                    }
lbl74:
                    // 1 sources

                    while (true) {
                        var11_12[v10] = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                        if (var7_16 < var10_15) ** continue;
                        break block18;
                        break;
                    }
                }
                var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                v10 = var8_13++;
                var13_18 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                ** while (true)
            }
            MacroElement.f = var11_12;
            MacroElement.g = new Integer[3];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v11 = SecretKeyFactory.getInstance("DES");
            v12 = new byte[8];
            v13 = v12;
            v12[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v13 = v13;
                v13[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block19;
lbl97:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v11.generateSecret(new DESKeySpec(v13)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{35, -49, 118, -120, 20, 104, -121, 74});
        ** while (true)
        MacroElement.i = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4965;
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
                throw new RuntimeException("su/catlean/xj", exception);
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
            MacroElement.d[n2] = MacroElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MacroElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/xj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x74B0;
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
                throw new RuntimeException("su/catlean/xj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MacroElement.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MacroElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/xj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MacroElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MacroElement.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

