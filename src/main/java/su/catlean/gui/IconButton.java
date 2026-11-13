/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
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
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import su.catlean.gui.Element;
import su.catlean.gui.FriendsPanel;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.TextureRenderer;
import su.catlean.render.TextureUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.FriendManager;
import su.catlean.zkm.ZkmKeys;

public final class IconButton
extends Element {
    @NotNull
    private String name;
    @Nullable
    private Identifier H;
    private boolean f;
    private boolean I;
    @NotNull
    private Animation animation;
    private static String[] C;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map b;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;
    private static final long i;

    public IconButton(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        this.name = string;
        this.animation = new Animation(Easing.OUT_QUINT, i);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)IconButton.zkmBootstrap("d", (int)24819, (long)(0x70E0C78CC482D8D5L ^ l))));
        this.name = string;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2, float var4_3, float var5_4, float var6_5, float var7_6) {
        block5: {
            v0 = var1_1;
            v1 = v0 ^ 122872324218116L;
            var8_7 = (int)(v1 >>> 48);
            var9_8 = (int)(v1 << 16 >>> 48);
            var10_9 = (int)(v1 << 32 >>> 32);
            var13_10 = v0 ^ 15727589290443L;
            var19_11 = v0 ^ 117649217993747L;
            var24_12 = v0 ^ 71473437392130L;
            new String[0];
            Intrinsics.checkNotNullParameter(var3_2, "context");
            var42_13 = var4_3 + 4.0f;
            v2 = var6_5 <= var4_3 + 102.0f ? var42_13 <= var6_5 : false;
            if (!v2) ** GOTO lbl-1000
            v3 = var5_4 <= var7_6 ? var7_6 <= var5_4 + 25.0f : false;
            if (v3) {
                v4 = true;
            } else lbl-1000:
            // 2 sources

            {
                v4 = false;
            }
            this.I = v4;
            this.animation.animate(this.I != false ? 1.0f : 0.0f);
            v5 = var3_2.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
            var26_14 = null;
            var27_15 = 14336;
            var28_16 = 0.0f;
            var29_17 = 0.0f;
            var30_18 = 0.0f;
            var31_19 = Theme.INSTANCE.getOutlineColor();
            var32_20 = Theme.INSTANCE.getWidgetBlurColor();
            var33_21 = 1.0f;
            var34_22 = 0.15f;
            var35_23 = this.I != false ? 2.0f : 0.0f;
            var36_24 = 2.0f;
            var37_25 = 25.0f;
            var38_26 = 102.0f;
            var39_27 = var5_4;
            var40_28 = var4_3 + 4.0f;
            Shapes2D.drawShape$default(v5, (char)var8_7, var40_28, var39_27, var38_26, var37_25, var36_24, var35_23, var34_22, var33_21, var32_20, var31_19, var30_18, (short)var9_8, var29_17, var10_9, var28_16, var27_15, var26_14);
            if (this.H == null) {
                if (!this.f) {
                    this.f = true;
                    this.H = TextureUtil.INSTANCE.getTexture("https://minotar.net/helm/" + this.name + "/32.png");
                    this.f = false;
                }
            } else {
                v6 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(v6, "WHITE");
                v7 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(v7, "WHITE");
                v8 = var3_2.getMatrices();
                v9 = this.H;
                Intrinsics.checkNotNull(v9);
                TextureRenderer.drawTexture(var4_3 + 6.0f, var5_4 + 2.0f, 20.0f, 20.0f, 2.0f, 0.0f, 0.0f, 1.0f, v6, v7, v8, var24_12, v9);
            }
            v10 = Fonts.getSF_M();
            v11 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v11, "WHITE");
            v10.drawStringWithShadow(var3_2, this.name, var13_10, var4_3 + 30.0f, var5_4 + 7.0f, v11);
            if (!(this.animation.getValue() > 0.0f)) break block5;
            v12 = FriendsPanel.INSTANCE.getRenderTarget();
            v13 = var3_2.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v13, "pose(...)");
            v14 = (Matrix3x2f)v13;
            v15 = (double)var4_3 + 85.0 + (double)(10.0f - 10.0f * this.animation.getValue());
            v16 = (double)var5_4 + 2.0;
            v17 = Color.RED;
            Intrinsics.checkNotNullExpressionValue(v17, "RED");
            var44_29 = this.animation.getValue();
            RenderTarget.drawRoundedText$default(v12, v14, v15, v16, 0.0f, 0.0f, RenderUtil.INSTANCE.withAlpha(v17, var44_29), null, 0.0f, 216, var19_11, null);
        }
    }

    @Override
    public boolean isHovered(float f, float f2) {
        if (this.I) {
            FriendManager.INSTANCE.removeFriend(this.name);
            return true;
        }
        return false;
    }

    public static void zkmSetFlag(String[] stringArray) {
        C = stringArray;
    }

    public static String[] java_lang_String_arr_zkmGetFlag() {
        return C;
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
                            IconButton.a = ZkmKeys.a(7966668218756344026L, 9202124005089686150L, MethodHandles.lookup().lookupClass()).a(271496403923358L);
                            var25 = IconButton.a ^ 138495489361584L;
                            IconButton.b = new HashMap<K, V>(13);
                            IconButton.zkmSetFlag(new String[1]);
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
                            var23_3 = new String[10];
                            var21_4 = 0;
                            var20_5 = "\u009c\u0088\u0094\u00d7\u007fX\u008f\u00c4\bY\u00dc[\u00c5\u00a7\u001d\u00f0\u0098*\u00a2\u00d7\u00beBv\u008f\u0010\u00109\u00c5]\n|S\u00c3F\u00df\u00fe\u00a8\u00f3\u0003[\u00d8\u0010\u00f49\u001f;d\u0087\u0089}\u00f4\u001f\u00ee\u00c5aH\u00fe\u00fa\u0010\u001d\u00b8\u00e7\u00816A\u00a5[\u00e0\u0017D\u0016\u00bd\u00e5\u00d2I\u0010:\u009e\u00f1)#@\u00afR\u00b7w\u00bd\u00f7f\u001ce\u00e0\u0010)\u0093\u00a1:\b\u008a\u00ed\u00e9\u00a7\u0012\u00b3\"\u00df\u00b0p3\u0010\u00b0\u00e9f\u00fa\u00a3\u00f31\u009a\u00ca\u00e9\u00c8j\u00df\u00ed\u00ba\u00a5\u0010$\u0094NB\u00f9\u00b5\u00f6\u0093\u009e\u0089\u0012\u00a7\u008fn\u00c5\u0090";
                            var22_6 = 143;
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
                                var23_3[var21_4++] = IconButton.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u00d1\u0010\u009b\u00d1U\u00b3\u00cb\u009e\u009fl\u00cd\u00e4\u0092j\u00af\u0088Z\u00d1\u0007l\u008dTwQ\u00ac$\u00e3{(\u0096\u0089\u00b58, \u0081\u008d\u00f1\u00fb\u009d\u00df?\u0086!\u0082\u00fb\u00a1\u0001\u00ad\u00ca\u00e3;\u00f4\u00df[\u001c\u00ff\u00f0O\u00e2\u001c<v\u0014\u00d9\u00fb\u00a5\u00b1\u0086\u0084\u00b3M\u00a84\u00d3\u00fa\u00e3\u00c7h\u0013\u00de\u00d2\u00c3@\u00bdG\u00c31\u00f1";
                                var22_6 = 89;
                                var19_7 = 32;
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
                                var23_3[var21_4++] = IconButton.zkmDecodeUtf8(var24_9).intern();
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
                    IconButton.c = var23_3;
                    IconButton.d = new String[10];
                    IconButton.h = new HashMap<K, V>(13);
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
                    var11_12 = new long[2];
                    var8_13 = 0;
                    var9_14 = "\u00b2m\u0085[YE-\u0016\u00fd\u0096\u00c3\u0007\u00c1\u00b16\u00b0";
                    var10_15 = 16;
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
            IconButton.e = var11_12;
            IconButton.g = new Integer[2];
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
        var4_22 = var0_20.doFinal(new byte[]{101, -6, 45, -100, 7, 126, -34, -3});
        ** while (true)
        IconButton.i = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3943;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])b.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    b.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/xs", exception);
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
            IconButton.d[n2] = IconButton.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = IconButton.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/xs" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x67CA;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/xs", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            IconButton.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = IconButton.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/xs" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(IconButton.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(IconButton.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

