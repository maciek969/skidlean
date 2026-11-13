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
import su.catlean.gui.GuiElement;
import su.catlean.gui.MacroElement;
import su.catlean.gui.MacroPanel;
import su.catlean.macro.ChatMacro;
import su.catlean.macro.Macro;
import su.catlean.macro.MacroManager;
import su.catlean.macro.SettingMacro;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Bind;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TextBox
implements GuiElement {
    @NotNull
    private String text = "";
    private int cursor;
    private boolean q;
    private boolean k;
    private boolean e;
    private boolean M;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;
    private static final long j;

    @NotNull
    public final String getText() {
        return this.text;
    }

    public final void setText(@NotNull String string) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextBox.zkmBootstrap("z", (int)29906, (long)(0x64B9EAA33807548CL ^ l))));
        this.text = string;
    }

    public final int getCursor() {
        return this.cursor;
    }

    public final void setCursor(int n) {
        this.cursor = n;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void render(@NotNull DrawContext var1_1, float var2_2, float var3_3, long var4_4, float var6_5, float var7_6) {
        block39: {
            v0 = var4_4;
            v1 = v0 ^ 13851571978927L;
            var8_7 = (int)(v1 >>> 48);
            var9_8 = (int)(v1 << 16 >>> 48);
            var10_9 = (int)(v1 << 32 >>> 32);
            var11_10 = v0 ^ 119935861042784L;
            var15_11 = v0 ^ 127206913172186L;
            var17_12 = v0 ^ 111455786286776L;
            v2 = MacroElement.B();
            Intrinsics.checkNotNullParameter(var1_1, "context");
            var22_13 = v2;
            var23_14 = var2_2 + 43.0f;
            cfr_temp_0 = var6_5 - (var2_2 + 77.0f);
            v3 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl20
            if (v3 <= 0) {
                v3 = var23_14 == var6_5 ? 0 : (var23_14 < var6_5 ? -1 : 1);
lbl20:
                // 2 sources

                if (var22_13) {
                    v3 = v3 <= 0 ? (float)true : (float)false;
                }
            } else {
                v3 = (float)false;
            }
            if (v3 == false) ** GOTO lbl-1000
            var23_14 = var3_3 + 45.0f;
            cfr_temp_1 = var7_6 - (var3_3 + 58.0f);
            v4 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl31
            if (v4 <= 0) {
                v4 = var23_14 == var7_6 ? 0 : (var23_14 < var7_6 ? -1 : 1);
lbl31:
                // 2 sources

                if (var22_13) {
                    v4 = v4 <= 0 ? (float)true : (float)false;
                }
            } else {
                v4 = (float)false;
            }
            if (v4 != false) {
                v5 = true;
            } else lbl-1000:
            // 2 sources

            {
                v5 = false;
            }
            this.q = v5;
            v6 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v6, "pose(...)");
            Shapes2D.drawShape$default(v6, (char)var8_7, var2_2, var3_3, 120.0f, 66.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
            v7 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v7, "pose(...)");
            Shapes2D.drawShape$default(v7, (char)var8_7, var2_2 + 43.0f, var3_3 + 45.0f, 34.0f, 13.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
            v8 = Fonts.getSF_M();
            v9 = this.text;
            if (var22_13) {
                v9 = StringsKt.isBlank(v9) != false ? "Cancel" : "Create";
            }
            v10 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v10, "WHITE");
            v8.drawStringWithShadow(var1_1, v9, var11_10, var2_2 + 48.0f, var3_3 + 46.0f, v10);
            v11 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v11, "pose(...)");
            Shapes2D.drawShape$default(v11, (char)var8_7, var2_2 + 5.0f, var3_3 + 5.0f, 110.0f, 13.0f, 3.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
            v12 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v12, "pose(...)");
            var23_15 = new Color[]{new Color(0x44FFFFFF, true)};
            RenderUtil.INSTANCE.drawGradient(v12, var2_2 + 33.0f, var3_3 + 9.0f, 0.5f, 7.0f, var23_15, var17_12);
            v13 = Fonts.getSF_M();
            v14 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v14, "WHITE");
            v13.drawStringWithShadow(var1_1, "Name", var11_10, var2_2 + 8.0f, var3_3 + 6.0f, v14);
            v15 = Fonts.getSF_M();
            v16 = this.text + (System.currentTimeMillis() % 700L > TextBox.j ? "" : "|");
            v17 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v17, "WHITE");
            v15.drawStringWithShadow(var1_1, v16, var11_10, var2_2 + 37.0f, var3_3 + 6.0f, RenderUtil.INSTANCE.withAlpha(v17, 0.8f));
            v18 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v18, "pose(...)");
            Shapes2D.drawShape$default(v18, (char)var8_7, var2_2 + 5.0f, var3_3 + 25.0f, 110.0f, 13.0f, 3.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, 14336, null);
            var23_16 = var2_2 + 5.0f;
            cfr_temp_2 = var6_5 - (var2_2 + 41.0f);
            v19 = cfr_temp_2 == 0.0f ? 0 : (cfr_temp_2 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl81
            if (v19 <= 0) {
                v19 = var23_16 == var6_5 ? 0 : (var23_16 < var6_5 ? -1 : 1);
lbl81:
                // 2 sources

                if (var22_13) {
                    v19 = v19 <= 0 ? (float)true : (float)false;
                }
            } else {
                v19 = (float)false;
            }
            if (v19 == false) ** GOTO lbl-1000
            var23_16 = var3_3 + 25.0f;
            cfr_temp_3 = var7_6 - (var3_3 + 38.0f);
            v20 = cfr_temp_3 == 0.0f ? 0 : (cfr_temp_3 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl92
            if (v20 <= 0) {
                v20 = var23_16 == var7_6 ? 0 : (var23_16 < var7_6 ? -1 : 1);
lbl92:
                // 2 sources

                if (var22_13) {
                    v20 = v20 <= 0 ? (float)true : (float)false;
                }
            } else {
                v20 = (float)false;
            }
            if (v20 != false) {
                v21 = true;
            } else lbl-1000:
            // 2 sources

            {
                v21 = false;
            }
            this.k = v21;
            var23_16 = var2_2 + 42.0f;
            cfr_temp_4 = var6_5 - (var2_2 + 77.0f);
            v22 = cfr_temp_4 == 0.0f ? 0 : (cfr_temp_4 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl107
            if (v22 <= 0) {
                v22 = var23_16 == var6_5 ? 0 : (var23_16 < var6_5 ? -1 : 1);
lbl107:
                // 2 sources

                if (var22_13) {
                    v22 = v22 <= 0 ? (float)true : (float)false;
                }
            } else {
                v22 = (float)false;
            }
            if (v22 == false) ** GOTO lbl-1000
            var23_16 = var3_3 + 25.0f;
            cfr_temp_5 = var7_6 - (var3_3 + 38.0f);
            v23 = cfr_temp_5 == 0.0f ? 0 : (cfr_temp_5 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl118
            if (v23 <= 0) {
                v23 = var23_16 == var7_6 ? 0 : (var23_16 < var7_6 ? -1 : 1);
lbl118:
                // 2 sources

                if (var22_13) {
                    v23 = v23 <= 0 ? (float)true : (float)false;
                }
            } else {
                v23 = (float)false;
            }
            if (v23 != false) {
                v24 = true;
            } else lbl-1000:
            // 2 sources

            {
                v24 = false;
            }
            this.e = v24;
            var23_16 = var2_2 + 78.0f;
            cfr_temp_6 = var6_5 - (var2_2 + 100.0f);
            v25 = cfr_temp_6 == 0.0f ? 0 : (cfr_temp_6 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl133
            if (v25 <= 0) {
                v25 = var23_16 == var6_5 ? 0 : (var23_16 < var6_5 ? -1 : 1);
lbl133:
                // 2 sources

                if (var22_13) {
                    v25 = v25 <= 0 ? (float)true : (float)false;
                }
            } else {
                v25 = (float)false;
            }
            if (v25 == false) ** GOTO lbl-1000
            var23_16 = var3_3 + 25.0f;
            cfr_temp_7 = var7_6 - (var3_3 + 38.0f);
            v26 = cfr_temp_7 == 0.0f ? 0 : (cfr_temp_7 < 0.0f ? -1 : 1);
            if (!var22_13) ** GOTO lbl144
            if (v26 <= 0) {
                v26 = var23_16 == var7_6 ? 0 : (var23_16 < var7_6 ? -1 : 1);
lbl144:
                // 2 sources

                if (var22_13) {
                    v26 = v26 <= 0 ? (float)true : (float)false;
                }
            } else {
                v26 = (float)false;
            }
            if (v26 != false) {
                v27 = true;
            } else lbl-1000:
            // 2 sources

            {
                v27 = false;
            }
            this.M = v27;
            v28 = Fonts.getSF_M();
            if (this.cursor == 0) {
                v29 = Color.WHITE;
            } else {
                v30 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(v30, "WHITE");
                v29 = RenderUtil.INSTANCE.withAlpha(v30, 0.3f);
            }
            var23_17 /* !! */  = v29;
            Intrinsics.checkNotNull(var23_17 /* !! */ );
            v28.drawStringCentered(var1_1, "Chat", var2_2 + 23.0f, var3_3 + 30.0f, (Color)var23_17 /* !! */ , var15_11);
            v31 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v31, "pose(...)");
            var23_17 /* !! */  = new Color[]{new Color(0x44FFFFFF, true)};
            RenderUtil.INSTANCE.drawGradient(v31, var2_2 + 41.0f, var3_3 + 29.0f, 0.5f, 7.0f, var23_17 /* !! */ , var17_12);
            v32 = Fonts.getSF_M();
            if (this.cursor == 1) {
                v33 = Color.WHITE;
            } else {
                v34 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(v34, "WHITE");
                v33 = RenderUtil.INSTANCE.withAlpha(v34, 0.3f);
            }
            var23_17 /* !! */  = v33;
            Intrinsics.checkNotNull(var23_17 /* !! */ );
            v32.drawStringCentered(var1_1, "Item", var2_2 + 58.0f, var3_3 + 30.0f, (Color)var23_17 /* !! */ , var15_11);
            v35 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v35, "pose(...)");
            var23_17 /* !! */  = new Color[]{new Color(0x44FFFFFF, true)};
            RenderUtil.INSTANCE.drawGradient(v35, var2_2 + 77.0f, var3_3 + 29.0f, 0.5f, 7.0f, var23_17 /* !! */ , var17_12);
            v36 = Fonts.getSF_M();
            if (this.cursor == 2) {
                v37 = Color.WHITE;
            } else {
                v38 = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(v38, "WHITE");
                v37 = RenderUtil.INSTANCE.withAlpha(v38, 0.3f);
            }
            var23_17 /* !! */  = v37;
            Intrinsics.checkNotNull(var23_17 /* !! */ );
            v36.drawStringCentered(var1_1, "Setting", var2_2 + 94.0f, var3_3 + 30.0f, (Color)var23_17 /* !! */ , var15_11);
            if (var22_13) break block39;
            Module.zkmSetFlag(new Module[4]);
        }
    }

    @Override
    public void charTyped(char c) {
        block3: {
            block2: {
                boolean bl = MacroElement.B();
                TextBox textBox = this;
                if (!bl) break block2;
                if (textBox.text.length() >= 10) break block3;
                textBox = this;
            }
            textBox.text = this.text + c;
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void keyPressed(int var1_1, short var2_2, int var3_3, int var4_4) {
        var5_5 = (long)var2_2 << 48 | (long)var3_3 << 32 >>> 16 | (long)var4_4 << 48 >>> 48;
        var9_6 = ZkmReferences.zkmBootstrap("\u00c5", (long)-5850966365129331228L, (long)var5_5);
        if (var9_6 != false) ** GOTO lbl7
        switch (var1_1) {
            case 259: {
                this.text = StringsKt.dropLast(this.text, 1);
lbl7:
                // 2 sources

                if (var4_4 <= 0 || var9_6 == false) break;
            }
            case 257: {
                this.submit();
                break;
            }
        }
    }

    @Override
    public boolean mouseClicked(long l, float f, float f2) {
        if (this.q) {
            this.submit();
            return true;
        }
        if (this.k) {
            this.cursor = 0;
            return true;
        }
        if (this.e) {
            this.cursor = 1;
            return true;
        }
        if (this.M) {
            this.cursor = 2;
            return true;
        }
        return false;
    }

    private final void submit() {
        boolean bl = MacroElement.B();
        boolean bl2 = StringsKt.isBlank(this.text);
        if (bl) {
            bl2 = !bl2;
        }
        if (bl2) {
            Macro macro = switch (this.cursor) {
                case 0 -> {
                    String var14_2 = "";
                    boolean var15_4 = true;
                    Bind var16_6 = new Bind(0, false, false, 7, null);
                    yield new ChatMacro(this.text, var16_6, var15_4, var14_2);
                }
                case 1 -> {
                    boolean var14_3 = false;
                    String var15_5 = "";
                    boolean var16_7 = true;
                    Bind var17_8 = new Bind(0, false, false, 7, null);
                    yield new ModuleConfig(this.text, var17_8, var16_7, var15_5, var14_3);
                }
                default -> new SettingMacro(this.text, new Bind(0, false, false, 7, null), true, "", "", "0", "0", false);
            };
            MacroManager.INSTANCE.addMacro(macro);
            MacroPanel.INSTANCE.refresh();
        }
        MacroPanel.INSTANCE.setGuiElement(null);
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
                            TextBox.b = ZkmKeys.a(-3019501740128103345L, -6939195157626118818L, MethodHandles.lookup().lookupClass()).a(17211695638550L);
                            TextBox.f = new HashMap<K, V>(13);
                            var16 = TextBox.b ^ 46991067440584L;
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
                            var25_3 = new String[12];
                            var23_4 = 0;
                            var22_5 = "\u00b7\u00ff}\u00c2m8\u0081u\u000b9\u00de\u0084J\u00f5T\u00d8 G\u00ab,J=M\u00f4\u00d0\u00f2}\u00b3B\u00f4\u00a3\u0087 n\u00fbA^\nC\u00ca\u00e0!\u0083\u00f7>}\u0090g\u0000\u0018\u009c\u00be\u00d8\u00d9]\u00e6\u00cagP\\4\u00a1\u009f\u00a5^\u00ba\u00d4\u00cf\u0015\u00e6 \u00e7\u00e7\u008c\u0010\u0010S;\u00aa\u009ce/zG\u00f4\u00d2\u0000\n\u00f7 y\u0010k\u00f6\u008e\u001fp\u0003H\u0080\u0089\u0091\u0004\u001d\u00d5\u0086\u00cdp\u0010\u00f5\u00a2\u0019\u008aq{\u0086\u00ed\u001a3'\u0090\u00c5H\u0081\u008a\u0010\u00c5\u0097\u009b\u0000c\u00c9\u00a2p\u00d2\u00ebP\u00f5\u00f8\u00ab\u0017G\u0010\u000eP\u00f6\u0018%\u00eb1j\u000f\u00f0\u00e7\r\u00f5O\u00f3:\u0010\u00a6\u0081\u00ce\u00ea\u0084\u00c8_\u00fd\u001f\u00e9W\u00a2\u00ef\u00aa+\u0091\u0010\u0017\u0004\u00c3za[&\u00c0\u00e0=\u007f\u0082\u00a8\u0000p\u00e7";
                            var24_6 = 193;
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
                                var25_3[var23_4++] = TextBox.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00d2at\f\u0085\u00b0\u00d9e\u0018\u0095vA\u00c1f\u0091\u001d!r\u0005-\u00d5\u00dcl\u0012\u0001\u009d\u00db\u00e6\u00d1\u00bd\u00ea\u000e\u0010\u00c6\u00ed\u00bez\u0096\u009c\u00a7\u00a3\u00ba\u0018\u00db\u00ef;M\u0005\u00b4";
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
                                var25_3[var23_4++] = TextBox.zkmDecodeUtf8(var26_9).intern();
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
                    TextBox.c = var25_3;
                    TextBox.d = new String[12];
                    TextBox.i = new HashMap<K, V>(13);
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
                    var11_12 = new long[10];
                    var8_13 = 0;
                    var9_14 = "\u00a9\u0092ax\u00aa\u00b5$u\u0098@\u00cb\u00d1\u00ab\u00e5\u00af\u0092\u00cb\u00c7\u0005\u00ea8\u0011{\u00fb*a\u00d1\u0087WX\u00aa\u00ef\u0013\u00f8\u00df \u00d0\u000b{\u007f\u00cdh,\u00ff\u00d1\u00c1\u00cf\u0013\u00d5#\u009aG\u007f\u001e;\u00d7\u0000;\u00e8pI-\u00beo";
                    var10_15 = 64;
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
                        var9_14 = "[o\u008e\u001a3K\u00bb8-\u00c2%\u00f0\u000f\u00ddG\u00f3";
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
            TextBox.g = var11_12;
            TextBox.h = new Integer[10];
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
        var4_22 = var0_20.doFinal(new byte[]{102, -83, -61, 114, -24, -68, 15, 29});
        ** while (true)
        TextBox.j = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x60BB;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/ge", exception);
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
            TextBox.d[n2] = TextBox.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TextBox.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ge" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xBF6;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
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
                throw new RuntimeException("su/catlean/ge", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TextBox.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TextBox.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ge" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TextBox.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TextBox.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

