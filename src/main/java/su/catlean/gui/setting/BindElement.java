/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.glfw.GLFW
 */
package su.catlean.gui.setting;

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
import org.lwjgl.glfw.GLFW;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.SettingElement;
import su.catlean.module.Module;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Bind;
import su.catlean.setting.Setting;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.KeyNames;
import su.catlean.zkm.ZkmKeys;

public final class BindElement
extends SettingElement {
    @NotNull
    private final Setting setting;
    @NotNull
    private final Animation animation;
    private boolean v;
    private static final long b;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] k;
    private static final Map l;
    private static final long m;

    public BindElement(@NotNull Setting setting2) {
        Intrinsics.checkNotNullParameter(setting2, "setting");
        super(setting2);
        this.setting = setting2;
        this.animation = new Animation(Easing.OUT_QUINT, m);
    }

    @Override
    @NotNull
    public Setting getSetting() {
        return this.setting;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4, float var6_5) {
        block3: {
            v0 = var4_4;
            v1 = v0 ^ 20797107207379L;
            var11_6 = (int)(v1 >>> 48);
            var12_7 = (int)(v1 << 16 >>> 48);
            var13_8 = (int)(v1 << 32 >>> 32);
            var16_9 = v0 ^ 136713714549074L;
            var20_10 = v0 ^ 115096991696004L;
            var24_11 = v0 ^ 120682356120742L;
            v2 = new Module[]{};
            Intrinsics.checkNotNullParameter(var1_1, "context");
            var26_12 = v2;
            var27_13 = this.v != false ? StringsKt.dropLast("...", 3 - (int)(4.0 * ((double)(System.currentTimeMillis() % 700L) / 700.0))) : KeyNames.INSTANCE.getKeyName(((Bind)this.getSetting().getValue()).getKey());
            var28_14 = Fonts.getSF_M4().getWidth(var16_9, KeyNames.INSTANCE.getKeyName(((Bind)this.getSetting().getValue()).getKey()));
            v3 = Fonts.getSF_M3();
            v4 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v4, "pose(...)");
            FontRenderer.drawString$default(v3, v4, this.getSetting().Q(), this.getX(), this.getY(), var20_10, Theme.INSTANCE.getTextColor(), false, null);
            var29_15 = this.getX() + this.getWidth() - var28_14 - 15.0f;
            var30_16 = this.getX() + this.getWidth() - 7.0f;
            var31_17 = var2_2;
            v5 = var29_15 <= var31_17 ? var31_17 <= var30_16 : false;
            if (!v5) ** GOTO lbl-1000
            var29_15 = this.getY();
            var30_16 = this.getY() + 12.0f;
            var31_17 = var3_3;
            v6 = var29_15 <= var31_17 ? var31_17 <= var30_16 : false;
            if (v6) {
                v7 = 1.0f;
            } else lbl-1000:
            // 2 sources

            {
                v7 = 0.0f;
            }
            this.animation.animate(v7);
            v8 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v8, "pose(...)");
            Shapes2D.drawShape$default(v8, (char)var11_6, this.getX() + this.getWidth() - var28_14 - 15.0f, this.getY(), var28_14 + 8.0f, 12.0f, 3.0f, 2.5f + this.animation.getValue(), 0.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getModuleOutlineColor(), 0.0f, (short)var12_7, 0.0f, var13_8, 0.0f, 14336, null);
            Fonts.getSF_M4().drawStringCentered(var1_1, var27_13, this.getX() + this.getWidth() - var28_14 / 2.0f - 11.0f, this.getY() + 5.0f, Theme.INSTANCE.getTextColor(), var24_11);
            var29_15 = this.getX() + this.getWidth() - var28_14 - 15.0f;
            var30_16 = this.getX() + this.getWidth() - 7.0f;
            var31_17 = var2_2;
            v9 = var29_15 <= var31_17 ? var31_17 <= var30_16 : false;
            if (!v9) break block3;
            var29_15 = this.getY();
            var30_16 = this.getY() + 12.0f;
            var31_17 = var3_3;
            v10 = var29_15 <= var31_17 ? var31_17 <= var30_16 : false;
            if (v10 && GLFW.glfwGetPlatform() != 393219) {
                GLFW.glfwSetCursor((long)Globals.getMc().getWindow().getHandle(), (long)GLFW.glfwCreateStandardCursor((int)221188));
                ClickGuiScreen.INSTANCE.A(false);
            }
        }
    }

    @Override
    public void mouseClicked(long l, double d, double d2, int n) {
        long l2 = l ^ 0x7D88BC9C6A41L;
        float f = Fonts.getSF_M3().getWidth(l2, this.getSetting().getId()) + 6.0f;
        Module[] moduleArray = new Module[]{};
        if (this.v) {
            this.getSetting().setValue(new Bind(n, false, true));
            this.v = false;
            return;
        }
        float f2 = this.getX() + this.getWidth() - f - 15.0f;
        boolean bl = d <= (double)(this.getX() + this.getWidth() - 7.0f) ? (double)f2 <= d : false;
        if (bl) {
            f2 = this.getY();
            boolean bl2 = d2 <= (double)(this.getY() + 12.0f) ? (double)f2 <= d2 : false;
            if (bl2) {
                this.v = !this.v;
            }
        }
    }

    @Override
    public void mouseReleased(double d, double d2) {
    }

    @Override
    public void keyPressed(int n, short s, int n2, char c) {
        Module[] moduleArray = new Module[]{};
        if (this.v) {
            switch (n) {
                case 256: 
                case 261: {
                    this.getSetting().setValue(new Bind(0, false, false, 7, null));
                    break;
                }
                default: {
                    this.getSetting().setValue(new Bind(n, false, false));
                    break;
                }
            }
            this.v = false;
        }
    }

    @Override
    public void charTyped(char c) {
    }

    @Override
    public boolean mouseScrolled(double d, double d2, double d3) {
        return false;
    }

    @Override
    public void close() {
        this.v = false;
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
                            BindElement.b = ZkmKeys.a(1649237000031516381L, -794842884603445305L, MethodHandles.lookup().lookupClass()).a(230624422831481L);
                            BindElement.f = new HashMap<K, V>(13);
                            var16 = BindElement.b ^ 106640466634490L;
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
                            var25_3 = new String[5];
                            var23_4 = 0;
                            var22_5 = "\u00fa\u00bf\u001a\u00a3\u0094M\u0092\u00ec\u009a\u001b}\u00ca\u0098\u00fb\u00ff@\u0018\u00ae'\u0005\b)\u00c5l\u00bc\u00d4\u00b3\u00a1Yz\u0095\u00b8\u00dd\u00c6@6Y\u0083@\u00a0\u00f7\u0010\u00f2\u00a6\u008d\u00bcX*v\u001a\u00fe(\u001cG\u00f4\u00af\u00c6m";
                            var24_6 = 58;
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
                                var25_3[var23_4++] = BindElement.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "gb\u008ep\u0091\u00b5\u00e0\u00a6o\u00d1\u00a5\u00f3F\u00db_\u00ce\u0018\u00a5\u008f\u00dfB\u00d9\u0083 \u00b0K\u00a4f^*\u0083\u0083\u0013m\u008f\u00b7\u00e2\u00e8k\u0006\u0090";
                                var24_6 = 41;
                                var21_7 = 16;
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
                                var25_3[var23_4++] = BindElement.zkmDecodeUtf8(var26_9).intern();
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
                    BindElement.d = var25_3;
                    BindElement.e = new String[5];
                    BindElement.l = new HashMap<K, V>(13);
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
                    var11_12 = new long[6];
                    var8_13 = 0;
                    var9_14 = "p\fK\u00fa\u00f7\u00d6\u00f2\u008b\u00d8\u00d0\u0095*M\u0019\u00e7\u0085\u0018\u008ar\u00fb\u00a7\u0090Z_\u00e7\u00adol\u0096\u00a3\u00d2\u00a1";
                    var10_15 = 32;
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
                        var9_14 = "\u0093\u00be4\u00d1\u00bf\u00f2V\u00f8\u0013h\u00bf\u00a2\u00a2S}\u00f6";
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
            BindElement.g = var11_12;
            BindElement.k = new Integer[6];
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
        var4_22 = var0_20.doFinal(new byte[]{-70, 79, 98, 24, 98, -82, 102, 84});
        ** while (true)
        BindElement.m = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7C4B;
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
                throw new RuntimeException("su/catlean/rk", exception);
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
            BindElement.e[n2] = BindElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BindElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3622;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])BindElement.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    BindElement.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/rk", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BindElement.k[n2] = n3;
        }
        return k[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BindElement.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/rk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BindElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BindElement.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

