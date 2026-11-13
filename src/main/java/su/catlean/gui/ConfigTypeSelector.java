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
import su.catlean.config.ConfigIO;
import su.catlean.config.ConfigType;
import su.catlean.gui.ConfigPanel;
import su.catlean.gui.GuiElement;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.sound.Sounds;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ConfigTypeSelector
implements GuiElement {
    @NotNull
    private ConfigType type;
    @NotNull
    private String inputText;
    private boolean focused;
    private boolean C;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public ConfigTypeSelector(@NotNull ConfigType configType) {
        Intrinsics.checkNotNullParameter((Object)configType, "type");
        this.type = configType;
        this.inputText = "";
    }

    @NotNull
    public final ConfigType getType() {
        return this.type;
    }

    public final void setType(@NotNull ConfigType configType) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter((Object)configType, (String)((Object)ConfigTypeSelector.zkmBootstrap("l", (int)29968, (long)(0x29D73543A82B798EL ^ l))));
        this.type = configType;
    }

    @NotNull
    public final String getInputText() {
        return this.inputText;
    }

    public final void setInputText(@NotNull String string) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ConfigTypeSelector.zkmBootstrap("l", (int)10863, (long)(0x14614C192DDE08A5L ^ l2))));
        this.inputText = string;
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
        var15_11 = v0 ^ 111455786286776L;
        new Module[0];
        Intrinsics.checkNotNullParameter(var1_1, "context");
        var21_12 = var2_2 + 43.0f;
        v2 = var6_5 <= var2_2 + 77.0f ? var21_12 <= var6_5 : false;
        if (!v2) ** GOTO lbl-1000
        var21_12 = var3_3 + 25.0f;
        v3 = var7_6 <= var3_3 + 38.0f ? var21_12 <= var7_6 : false;
        if (v3) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = this.focused = false;
        }
        v5 = var2_2 <= var6_5 ? var6_5 <= var2_2 + 120.0f : false;
        if (!v5) ** GOTO lbl-1000
        v6 = var3_3 <= var7_6 ? var7_6 <= var3_3 + 46.0f : false;
        if (v6) {
            v7 = true;
        } else lbl-1000:
        // 2 sources

        {
            v7 = false;
        }
        this.C = v7;
        v8 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v8, "pose(...)");
        Shapes2D.drawShape$default(v8, (char)var8_7, var2_2, var3_3, 120.0f, 46.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
        v9 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v9, "pose(...)");
        Shapes2D.drawShape$default(v9, (char)var8_7, var2_2 + 43.0f, var3_3 + 25.0f, 34.0f, 13.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
        v10 = Fonts.getSF_M();
        v11 = StringsKt.isBlank(this.inputText) != false ? "Cancel" : "Create";
        v12 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v12, "WHITE");
        v10.drawStringWithShadow(var1_1, v11, var11_10, var2_2 + 48.0f, var3_3 + 26.0f, v12);
        v13 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v13, "pose(...)");
        Shapes2D.drawShape$default(v13, (char)var8_7, var2_2 + 5.0f, var3_3 + 5.0f, 110.0f, 13.0f, 3.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
        v14 = var1_1.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v14, "pose(...)");
        var21_13 = new Color[]{new Color(0x44FFFFFF, true)};
        RenderUtil.INSTANCE.drawGradient(v14, var2_2 + 33.0f, var3_3 + 8.0f, 0.5f, 7.0f, var21_13, var15_11);
        v15 = Fonts.getSF_M();
        v16 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v16, "WHITE");
        v15.drawStringWithShadow(var1_1, "Name", var11_10, var2_2 + 8.0f, var3_3 + 6.0f, v16);
        v17 = Fonts.getSF_M();
        v18 = this.inputText + (System.currentTimeMillis() % 700L > ConfigTypeSelector.h ? "" : "|");
        v19 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v19, "WHITE");
        v17.drawStringWithShadow(var1_1, v18, var11_10, var2_2 + 37.0f, var3_3 + 6.0f, RenderUtil.INSTANCE.withAlpha(v19, 0.8f));
    }

    @Override
    public void charTyped(char c) {
        if (this.inputText.length() < 10) {
            this.inputText = this.inputText + c;
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void keyPressed(int var1_1, short var2_2, int var3_3, int var4_4) {
        var5_5 = (long)var2_2 << 48 | (long)var3_3 << 32 >>> 16 | (long)var4_4 << 48 >>> 48;
        var7_6 = var5_5 ^ 132650143508248L;
        var9_7 = ZkmReferences.zkmBootstrap("\u00c5", (long)-5887596853467668436L, (long)var5_5);
        if (var9_7 == null) ** GOTO lbl9
        switch (var1_1) {
            case 259: {
                this.inputText = StringsKt.dropLast(this.inputText, 1);
lbl9:
                // 2 sources

                v0 = var9_7;
                if (var4_4 >= 0) {
                    if (v0 != null) break;
                }
                ** GOTO lbl16
            }
            case 257: {
                this.save(var7_6);
                v0 = var9_7;
lbl16:
                // 2 sources

                if (v0 != null) break;
            }
            case 256: {
                ConfigPanel.INSTANCE.setGuiElement(null);
                break;
            }
        }
    }

    @Override
    public boolean mouseClicked(long l, float f, float f2) {
        block1: {
            long l2 = l ^ 0x4746628D4D60L;
            if (this.focused) {
                this.save(l2);
                return true;
            }
            if (this.C) break block1;
            ConfigPanel.INSTANCE.setGuiElement(null);
        }
        return false;
    }

    private final void save(long l) {
        long l2 = (l = a ^ l) ^ 0x6B6C6BA6D547L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        if (!StringsKt.isBlank(this.inputText)) {
            ConfigIO.save(this.inputText, this.type, (char)n, n2, (short)n3);
            Sounds.INSTANCE.getConfigSave().play();
            ConfigPanel.INSTANCE.refresh();
        }
        ConfigPanel.INSTANCE.setGuiElement(null);
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
                            ConfigTypeSelector.a = ZkmKeys.a(8442883526280536207L, -7603210911256864848L, MethodHandles.lookup().lookupClass()).a(189589156367652L);
                            ConfigTypeSelector.d = new HashMap<K, V>(13);
                            var16 = ConfigTypeSelector.a ^ 127403922560123L;
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
                            var25_3 = new String[11];
                            var23_4 = 0;
                            var22_5 = "\u0096\u0098\u00ceW\u00b1\u00e1C/H\u00f8~\u008ci[!\u00b4\u0010\u00af\u00b8 \u00f5;\u00a4y\u00ae\u0099w!,\u0089\u0094\u00ca\u00c5\u0010\u0088[\u009aV%\u00ecp\u00cc\u00ec[\u0014uv\u00ab\u00c8\u0084\u0010\u00b0\u00fc\"\u00fd\u00fd\u0091\u00e7\u00b8\u0000\u00cc\u0003\u00d76DP\u0086 \u008f\u008d\u00a0\u0007V\u008f\u00bb\u00f4\u00aaF\u00a0VXbED2g\u0090]\\\u00a4\u00a9\u00e1\u00a3v\u00a1\b\u009c.1\u0018\u0010J\u0081\u00beJ*\u00fb\u00b6\u00b7z|0\u00cd\u001d\u00d4\u00b0\u00d2\u0010d\u0095\u0095\u0091\u00b5\u00a1\u0010H\u00b0\u00a8\u00e6}k\u00feWb\u0010\u00f4\u001bly\u0004w\u008d\u00b7\u0092L\u0006\u00dd\u0013\u0082\f\u00eb\u0010t\u00feQ;\u00a3\u0092\u0083\u00d3g\u00ac\u009ex\u0099\u00b0\u0000x";
                            var24_6 = 168;
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
                                var25_3[var23_4++] = ConfigTypeSelector.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "u\\\"Nt\u00e7\u000e\u00a0\u00fa\u0082B\u0005G\u00181\u00b4\u0083p\u00d9\u00ee-!J\u00ea.j\u00e5\u00ec&.\u00c2\u00b0\u0010\u008e!\u00e0\n\u001a\u00b6i\u0013V\u00ae\u00b0!\u009dUK\u00d0";
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
                                var25_3[var23_4++] = ConfigTypeSelector.zkmDecodeUtf8(var26_9).intern();
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
                    ConfigTypeSelector.b = var25_3;
                    ConfigTypeSelector.c = new String[11];
                    ConfigTypeSelector.g = new HashMap<K, V>(13);
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
                    var9_14 = "y\u00c4W\u0004\u001e\u00fb\u0095\u00c1\u00bc\u00f0\u00fd\u009a\u0085P,\u00c6_\u00c4\u00bc\u00b7\u00e0\u0098\u00f4e\u001f\t\u00cd#\u00ab\u00c1\u00a3\u0090";
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
                        var9_14 = "w\u00b1\u0012\u0002/\u00ed\u00d0\bG\u0083\u00cf\u0096\u00cc<\u00ad\u0080";
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
            ConfigTypeSelector.e = var11_12;
            ConfigTypeSelector.f = new Integer[6];
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
        var4_22 = var0_20.doFinal(new byte[]{-82, 43, -100, -37, 15, -120, 113, -75});
        ** while (true)
        ConfigTypeSelector.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5514;
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
                throw new RuntimeException("su/catlean/p9", exception);
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
            ConfigTypeSelector.c[n2] = ConfigTypeSelector.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ConfigTypeSelector.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/p9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1E74;
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
                throw new RuntimeException("su/catlean/p9", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ConfigTypeSelector.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ConfigTypeSelector.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/p9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ConfigTypeSelector.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ConfigTypeSelector.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

