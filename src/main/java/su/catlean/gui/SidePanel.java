/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.gui;

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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import su.catlean.gui.GuiElement;
import su.catlean.gui.PanelScreen;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public abstract class SidePanel {
    @NotNull
    private String name;
    private float x;
    private float y;
    private float width;
    private float height;
    private float prevWidth;
    private float prevHeight;
    @Nullable
    private GuiElement guiElement;
    private float dragOffsetX;
    private float dragOffsetY;
    private boolean x_obf;
    private boolean A;
    private boolean p;
    private boolean o;
    private static int[] d;
    private static final long g;
    private static final String[] R;
    private static final String[] T;
    private static final Map ab;
    private static final long[] bb;
    private static final Integer[] cb;
    private static final Map db;

    public SidePanel(@NotNull String string, float f, float f2, float f3, float f4) {
        Intrinsics.checkNotNullParameter(string, "name");
        this.name = string;
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
        this.prevWidth = this.width;
        this.prevHeight = this.height;
    }

    public SidePanel(String string, float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        f = 50.0f;
        f2 = 50.0f;
        f3 = 230.0f;
        f4 = 180.0f;
        this(string, f, f2, f3, f4);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final void setName(@NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ g;
        Intrinsics.checkNotNullParameter(string, (String)((Object)SidePanel.zkmBootstrap("e", (int)24151, (long)(0x72862D68B92A99C3L ^ l))));
        this.name = string;
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    @Nullable
    public final GuiElement getGuiElement() {
        return this.guiElement;
    }

    public final void setGuiElement(@Nullable GuiElement guiElement) {
        this.guiElement = guiElement;
    }

    public final void open() {
        this.o = true;
    }

    public abstract void refresh();

    public void render(@NotNull DrawContext drawContext, int n, int n2, long l) {
        long l2 = l;
        long l3 = l2 ^ 0x1421E94221EBL;
        int n3 = (int)(l3 >>> 48);
        int n4 = (int)(l3 << 16 >>> 48);
        int n5 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x6B09519A419EL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        if (this.o) {
            this.refresh();
            this.o = false;
        }
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n3, this.x, this.y, this.width, this.height, 5.0f, 1.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        RenderUtil.INSTANCE.beginScissor(drawContext, new Rect(this.x + 2.0f, this.y + 14.0f, this.x + this.width - 2.0f, this.y + this.height));
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack2, (char)n3, this.x + 1.0f, this.y + 12.0f, this.width - 2.0f, this.height - 14.0f, 5.0f, 1.0f, 0.1f, 0.5f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        RenderUtil.INSTANCE.endScissor(drawContext);
        Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack3, (char)n3, this.x + this.width - 10.0f, this.y + 3.0f, 8.0f, 8.0f, 4.0f, 0.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        Matrix3x2fStack matrix3x2fStack4 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack4, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack4, (char)n3, this.x + this.width - 20.0f, this.y + 3.0f, 8.0f, 8.0f, 4.0f, 0.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        Matrix3x2fStack matrix3x2fStack5 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack5, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack5, (char)n3, this.x + this.width - 30.0f, this.y + 3.0f, 8.0f, 8.0f, 4.0f, 0.0f, 0.1f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n4, 0.0f, n5, 0.0f, 14336, null);
        Fonts.getSF_M().drawStringCentered(drawContext, this.name, this.x + this.width / 2.0f, this.y + 6.0f, Theme.INSTANCE.getWidgetBlurColor(), l4);
        if (this.x_obf) {
            this.x = (float)n - this.dragOffsetX;
            this.y = (float)n2 - this.dragOffsetY;
        }
        if (this.A) {
            this.width = Math.max(this.prevWidth, (float)n - this.dragOffsetX);
        }
        if (this.p) {
            this.height = Math.max(this.prevHeight, (float)n2 - this.dragOffsetY);
        }
    }

    /*
     * Unable to fully structure code
     */
    public void mouseClicked(float var1_1, float var2_2, short var3_3, int var4_4, int var5_5, char var6_6) {
        block48: {
            block47: {
                block45: {
                    block46: {
                        block43: {
                            block44: {
                                block42: {
                                    block41: {
                                        block40: {
                                            block39: {
                                                block38: {
                                                    block37: {
                                                        var7_7 = (long)var3_3 << 48 | (long)var5_5 << 32 >>> 16 | (long)var6_6 << 48 >>> 48;
                                                        var10_8 = this.x;
                                                        var9_9 = ZkmReferences.zkmBootstrap("\u00c5", (long)-2746228687453747716L, (long)var7_7);
                                                        cfr_temp_0 = var1_1 - (this.x + this.width);
                                                        v0 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                        if (var9_9 != null) ** GOTO lbl9
                                                        if (v0 <= 0) {
                                                            v0 = var10_8 == var1_1 ? 0 : (var10_8 < var1_1 ? -1 : 1);
lbl9:
                                                            // 2 sources

                                                            if (var9_9 == null) {
                                                                v0 = v0 <= 0 ? (float)true : (float)false;
                                                            }
                                                        } else {
                                                            v0 = (float)false;
                                                        }
                                                        if (v0 == false) break block37;
                                                        var10_8 = this.y;
                                                        cfr_temp_1 = var2_2 - (this.y + (float)SidePanel.zkmBootstrap2("a", (int)28546, (long)(6312784366785225802L ^ var7_7)));
                                                        v1 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                                                        v2 = var9_9;
                                                        if (var5_5 < 0) ** GOTO lbl23
                                                        if (v2 != null) ** GOTO lbl22
                                                        if (v1 <= 0) {
                                                            v1 = var10_8 == var2_2 ? 0 : (var10_8 < var2_2 ? -1 : 1);
lbl22:
                                                            // 2 sources

                                                            v2 = var9_9;
lbl23:
                                                            // 2 sources

                                                            if (v2 == null) {
                                                                v1 = v1 <= 0 ? (float)true : (float)false;
                                                            }
                                                        } else {
                                                            v1 = (float)false;
                                                        }
                                                        if (v1 == false) break block37;
                                                        v3 = PanelScreen.INSTANCE.getSidePanel();
                                                        if (var9_9 != null) break block38;
                                                        if (v3 == null) {
                                                            this.x_obf = true;
                                                            this.dragOffsetX = var1_1 - this.x;
                                                            this.dragOffsetY = var2_2 - this.y;
                                                        }
                                                    }
                                                    v3 = this;
                                                }
                                                var10_8 = v3.x;
                                                cfr_temp_2 = var1_1 - (this.x + this.width);
                                                v4 = cfr_temp_2 == 0.0f ? 0 : (cfr_temp_2 < 0.0f ? -1 : 1);
                                                v5 = var9_9;
                                                if (var6_6 <= '\u0000') ** GOTO lbl46
                                                if (v5 != null) ** GOTO lbl45
                                                if (v4 <= 0) {
                                                    v4 = var10_8 == var1_1 ? 0 : (var10_8 < var1_1 ? -1 : 1);
lbl45:
                                                    // 2 sources

                                                    v5 = var9_9;
lbl46:
                                                    // 2 sources

                                                    if (v5 == null) {
                                                        v4 = v4 <= 0 ? (float)true : (float)false;
                                                    }
                                                } else {
                                                    v4 = (float)false;
                                                }
                                                if (v4 == false) break block39;
                                                var10_8 = this.y + this.height - 5.0f;
                                                cfr_temp_3 = var2_2 - (this.y + this.height);
                                                v6 = cfr_temp_3 == 0.0f ? 0 : (cfr_temp_3 < 0.0f ? -1 : 1);
                                                v7 = var9_9;
                                                if (var5_5 <= 0) ** GOTO lbl60
                                                if (v7 != null) ** GOTO lbl59
                                                if (v6 <= 0) {
                                                    v6 = var10_8 == var2_2 ? 0 : (var10_8 < var2_2 ? -1 : 1);
lbl59:
                                                    // 2 sources

                                                    v7 = var9_9;
lbl60:
                                                    // 2 sources

                                                    if (v7 == null) {
                                                        v6 = v6 <= 0 ? (float)true : (float)false;
                                                    }
                                                } else {
                                                    v6 = (float)false;
                                                }
                                                if (v6 == false) break block39;
                                                v8 = PanelScreen.INSTANCE.getSidePanel();
                                                if (var9_9 != null) break block40;
                                                if (v8 == null) {
                                                    this.p = true;
                                                    this.dragOffsetY = var2_2 - this.height;
                                                }
                                            }
                                            v8 = this;
                                        }
                                        var10_8 = v8.x + this.width - 5.0f;
                                        cfr_temp_4 = var1_1 - (this.x + this.width);
                                        v9 = cfr_temp_4 == 0.0f ? 0 : (cfr_temp_4 < 0.0f ? -1 : 1);
                                        v10 = var9_9;
                                        if (var6_6 < '\u0000') ** GOTO lbl82
                                        if (v10 != null) ** GOTO lbl81
                                        if (v9 <= 0) {
                                            v9 = var10_8 == var1_1 ? 0 : (var10_8 < var1_1 ? -1 : 1);
lbl81:
                                            // 2 sources

                                            v10 = var9_9;
lbl82:
                                            // 2 sources

                                            if (v10 == null) {
                                                v9 = v9 <= 0 ? (float)true : (float)false;
                                            }
                                        } else {
                                            v9 = (float)false;
                                        }
                                        if (v9 == false) break block41;
                                        var10_8 = this.y;
                                        cfr_temp_5 = var2_2 - (this.y + this.height);
                                        v11 = cfr_temp_5 == 0.0f ? 0 : (cfr_temp_5 < 0.0f ? -1 : 1);
                                        v12 = var9_9;
                                        if (var3_3 < 0) ** GOTO lbl96
                                        if (v12 != null) ** GOTO lbl95
                                        if (v11 <= 0) {
                                            v11 = var10_8 == var2_2 ? 0 : (var10_8 < var2_2 ? -1 : 1);
lbl95:
                                            // 2 sources

                                            v12 = var9_9;
lbl96:
                                            // 2 sources

                                            if (v12 == null) {
                                                v11 = v11 <= 0 ? (float)true : (float)false;
                                            }
                                        } else {
                                            v11 = (float)false;
                                        }
                                        if (v11 == false) break block41;
                                        v13 = PanelScreen.INSTANCE.getSidePanel();
                                        if (var3_3 < 0 || var9_9 != null) break block42;
                                        if (v13 == null) {
                                            this.A = true;
                                            this.dragOffsetX = var1_1 - this.width;
                                        }
                                    }
                                    v13 = this;
                                }
                                v14 = v13.x_obf;
                                v15 = var9_9;
                                if (var5_5 < 0) break block43;
                                if (v15 != null) break block44;
                                if (v14) break block45;
                                v14 = this.A;
                            }
                            if (var3_3 < 0) break block46;
                            v15 = var9_9;
                        }
                        if (v15 != null) break block46;
                        if (v14) break block45;
                        v14 = this.p;
                    }
                    if (!v14) break block47;
                }
                PanelScreen.INSTANCE.setSidePanel(this);
            }
            if (var3_3 < 0 || ZkmReferences.zkmBootstrap("\u00c5", (long)-2768060796630939900L, (long)var7_7) == null) break block48;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[3], (long)-2791124986525046089L, (long)var7_7);
        }
    }

    public void charTyped(char c) {
        block0: {
            GuiElement guiElement = this.guiElement;
            if (guiElement == null) break block0;
            guiElement.charTyped(c);
        }
    }

    public void keyPressed(int n, long l) {
        block0: {
            long l2 = l ^ 0x63D521170912L;
            int n2 = (int)(l2 >>> 48);
            int n3 = (int)(l2 << 16 >>> 32);
            int n4 = (int)(l2 << 48 >>> 48);
            GuiElement guiElement = this.guiElement;
            if (guiElement == null) break block0;
            guiElement.keyPressed(n, (short)n2, n3, n4);
        }
    }

    public void mouseScrolled(double d, double d2, double d3) {
    }

    public final void resetState() {
        this.x_obf = false;
        this.A = false;
        this.p = false;
        PanelScreen.INSTANCE.setSidePanel(null);
    }

    public static void zkmSetFlag(int[] nArray) {
        d = nArray;
    }

    public static int[] int_arr_zkmGetFlag() {
        return d;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SidePanel.g = ZkmKeys.a(-8080473127445216268L, 2374458864467190491L, MethodHandles.lookup().lookupClass()).a(276704876498223L);
                        var20 = SidePanel.g ^ 51344730835733L;
                        SidePanel.ab = new HashMap<K, V>(13);
                        SidePanel.zkmSetFlag(null);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[5];
                        var16_4 = 0;
                        var15_5 = "\u008d\u0098N\u00e4\u009f\u00b7\u0085s\u008f\u001fE8\u00d9\u00db\u0095\u0005\u00c0Zs{V7\u00bc\u008e\u00a2h~\u00be\u00c2\u00a0\u0082T\u0010\u009e2\u00acua\u00cf\u00a5?\u0080\u0081\u00a2O\u00c0\u00be\u009e4\u0010\u00f0\u0000\u00db\u00fa\u00d8\u0003\u00ac3v\u0082AK\u00ef\u0096hD";
                        var17_6 = 66;
                        var14_7 = 32;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = SidePanel.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0092[\u00b9\u0004\u00bf\u00fc\u00fce\u00b4\u00be\u001a\u0095[\u00ae\u00ach K\u00a6G\u00e3\u00dd\u00eb\u00e0\u00e7w\u00c3bo\u00ca\u0082\u00aciz8H\u00fcm\u00db\u00a6\u00a1\u0083\\\u00c8\u0088\u001a{\u00c1\u0015";
                            var17_6 = 49;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = SidePanel.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                SidePanel.R = var18_3;
                SidePanel.T = new String[5];
                SidePanel.db = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[10];
                var3_13 = 0;
                var4_14 = "\u00b9\u0018\u0081\u00d6X)\u00cd\u00e2#v\u00a4\u0018\u008c\u00b6N\u0087\u0000\u0010B\"\u0000\u00bcc\u00c6\u00b0\u00bfy\u00c6vj\u00c9\u00c6\u00e5\u0017\u00b99\n=,\u00a8\u009c\u00a81\u00a5\u0005.\u0014\u008ev \u0082\u00b4E\u00f1\tI\u00a4\u00ba\u00b3B8\u000bF\u000b";
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00e9\u009a\u00ff\u0084-\u00ec\u0098\u00e6\u00e7K\u00ab\u00bcKW\u00cb\u0001";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        SidePanel.bb = var6_12;
        SidePanel.cb = new Integer[10];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7801;
        if (T[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])ab.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    ab.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/i8", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = R[n2].getBytes("ISO-8859-1");
            SidePanel.T[n2] = SidePanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return T[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SidePanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/i8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5304;
        if (cb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = bb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])db.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    db.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/i8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SidePanel.cb[n2] = n3;
        }
        return cb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SidePanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/i8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SidePanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SidePanel.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

