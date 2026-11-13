/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.gui;

import java.awt.Color;
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
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import su.catlean.gui.Component;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public abstract class TitledComponent
extends Component {
    @NotNull
    private final String title;
    @Nullable
    private Object hoveredOption;
    private boolean W;
    private boolean a;
    @NotNull
    private String searchText;
    private static int p;
    private static final long c;
    private static final String[] i;
    private static final String[] m;
    private static final Map n;
    private static final long[] u;
    private static final Integer[] A;
    private static final Map B;
    private static final long C;

    public TitledComponent(@NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)n3 << 56 >>> 56) ^ c;
        Intrinsics.checkNotNullParameter(string, (String)((Object)TitledComponent.zkmBootstrap("n", (int)10593, (long)(0x364F7CED2E1F352CL ^ l))));
        this.title = string;
        this.W = true;
        this.searchText = "";
    }

    @NotNull
    protected final String getSearchText() {
        return this.searchText;
    }

    protected final void setSearchText(@NotNull String string) {
        long l = 0L;
        l = c ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)TitledComponent.zkmBootstrap("n", (int)11483, (long)(0x1FFFB4829755DA78L ^ l))));
        this.searchText = string;
    }

    @Override
    public void render(@NotNull DrawContext drawContext, long l, int n, int n2) {
        long l2 = l;
        long l3 = l2 ^ 0x7DF6CA2D7F9BL;
        int n3 = (int)(l3 >>> 32);
        int n4 = (int)(l3 << 32 >>> 48);
        int n5 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x26775C048DBCL;
        long l5 = l2 ^ 0x2F7AB7227945L;
        long l6 = l2 ^ 0x69BCEB83230DL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - this.getX() / 2.0f;
        float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - this.getY() / 2.0f - 50.0f;
        Fonts.getSF_SB().drawStringRight(l4, drawContext, this.title, f + this.getX() / 2.0f, f2 + 3.0f, 10);
        this.drawHeader(drawContext, f, f2, l5);
        this.drawSearch(drawContext, f, f2, l6);
        this.drawList(drawContext, n3, f, f2, n, (char)n4, (short)n5, n2);
    }

    private final void drawHeader(DrawContext drawContext, float f, float f2, long l) {
        long l2 = l = c ^ l;
        long l3 = l2 ^ 0x745581D34BB1L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x15D82DB0E77EL;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        float f3 = this.getX() - 8.0f;
        Color color = Theme.INSTANCE.g().darker();
        Intrinsics.checkNotNullExpressionValue(color, "darker(...)");
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n, f + 4.0f, f2 + 28.0f, f3, 20.0f, 4.0f, 0.0f, 0.1f, 1.0f, color, Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        Color color2 = this.a ? Theme.INSTANCE.getTextColor() : Theme.INSTANCE.getInactiveTextColor();
        float f4 = f2 + 29.5f;
        float f5 = f + 8.0f;
        Fonts.getSF_SB().drawStringWithShadow(drawContext, (String)(this.a ? this.searchText + (Globals.now() % 700L > C ? "_" : "") : "Search"), l4, f5, f4, color2);
    }

    private final void drawSearch(DrawContext drawContext, float f, float f2, long l) {
        long l2 = l = c ^ l;
        long l3 = l2 ^ 0x3293DD7211F9L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x4DBB65AA718CL;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        float f3 = this.getX() - 8.0f;
        Color color = Theme.INSTANCE.g().darker();
        Intrinsics.checkNotNullExpressionValue(color, "darker(...)");
        Object var13_9 = null;
        int n4 = 14336;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        Color color2 = Theme.INSTANCE.getOutlineColor();
        Color color3 = color;
        float f7 = 1.0f;
        float f8 = 0.1f;
        float f9 = 2.0f;
        float f10 = 4.0f;
        float f11 = 17.0f;
        float f12 = f3;
        float f13 = f2 + 52.0f;
        float f14 = f + 4.0f;
        Shapes2D.drawShape$default(matrix3x2fStack, (char)n, f14, f13, f12, f11, f10, f9, f8, f7, color3, color2, f6, (short)n2, f5, n3, f4, n4, var13_9);
        if (this.W) {
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            Shapes2D.drawShape$default(matrix3x2fStack2, (char)n, f + 6.0f, f2 + 54.0f, this.getX() / 2.0f - 8.0f, 13.0f, 3.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        } else {
            Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
            var13_9 = null;
            n4 = 14336;
            f4 = 0.0f;
            f5 = 0.0f;
            f6 = 0.0f;
            color2 = Theme.INSTANCE.getOutlineColor();
            color3 = Theme.INSTANCE.g();
            f7 = 1.0f;
            f8 = 0.1f;
            f9 = 2.0f;
            f10 = 3.0f;
            f11 = 13.0f;
            f12 = this.getX() / 2.0f - 8.0f;
            f13 = f2 + 54.0f;
            f14 = f + 6.0f + this.getX() / 2.0f - 4.0f;
            Shapes2D.drawShape$default(matrix3x2fStack3, (char)n, f14, f13, f12, f11, f10, f9, f8, f7, color3, color2, f6, (short)n2, f5, n3, f4, n4, var13_9);
        }
        Fonts.getSF_M2().drawStringCentered(drawContext, "All", f + this.getX() / 4.0f, f2 + 60.5f, this.W ? Theme.INSTANCE.getPrimaryColor() : Theme.INSTANCE.getInactiveTextColor(), l4);
        Fonts.getSF_M2().drawStringCentered(drawContext, "Selected", f + 3.0f * this.getX() / 4.0f, f2 + 60.5f, !this.W ? Theme.INSTANCE.getPrimaryColor() : Theme.INSTANCE.getInactiveTextColor(), l4);
    }

    @NotNull
    protected abstract List getOptions();

    @NotNull
    protected abstract String getOptionLabel(Object var1);

    protected abstract boolean isSelected(Object var1);

    protected abstract void select(Object var1);

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     */
    private final void drawList(DrawContext var1_1, int var2_2, float var3_3, float var4_4, int var5_5, char var6_6, short var7_7, int var8_8) {
        v0 = var9_9 = ((long)var2_2 << 32 | (long)var6_6 << 48 >>> 32 | (long)var7_7 << 48 >>> 48) ^ TitledComponent.c;
        v1 = v0 ^ 42717692054895L;
        var13_10 = (int)(v1 >>> 48);
        var14_11 = (int)(v1 << 16 >>> 48);
        var15_12 = (int)(v1 << 32 >>> 32);
        var16_13 = v0 ^ 78427457577376L;
        this.hoveredOption = null;
        var33_14 = ZkmReferences.zkmBootstrap("\u00c5", (long)-1343118676194442401L, (long)var9_9);
        RenderUtil.INSTANCE.beginScissor(var1_1, new Rect(var3_3, var4_4 + 71.0f, var3_3 + this.getX(), var4_4 + this.getY()));
        var35_15 = this.getOptions();
        var37_16 = var35_15;
        var38_18 /* !! */  = new ArrayList<E>();
        block0: for (T v2 : var37_16) {
            do {
                block37: {
                    block36: {
                        block35: {
                            block33: {
                                block34: {
                                    var42_24 = var41_22 = v2 /* !! */ ;
                                    v3 = this.W;
                                    if (var33_14 != false) break block33;
                                    if (v3 != 0) break block34;
                                    v3 = this.isSelected(var42_24);
                                    v4 = var33_14;
                                    if (var2_2 < 0) break block35;
                                    if (v4 != false) break block33;
                                    if (v3 == 0) ** GOTO lbl-1000
                                }
                                v3 = ((CharSequence)this.searchText).length();
                            }
                            v4 = var33_14;
                        }
                        if (v4 == false) {
                            v3 = v3 == 0 ? 1 : 0;
                        }
                        if (var2_2 < 0) break block36;
                        if (v3 != 0) ** GOTO lbl-1000
                        v3 = (int)StringsKt.contains((CharSequence)this.getOptionLabel(var42_24), this.searchText, true);
                    }
                    if (var33_14 == false) {
                        ** if (v3 == 0) goto lbl-1000
                    }
                    break block37;
lbl-1000:
                    // 2 sources

                    {
                        v3 = 1;
                        ** GOTO lbl43
                    }
lbl-1000:
                    // 2 sources

                    {
                        v3 = 0;
                    }
                }
                if (v3 == 0) continue block0;
                var38_18 /* !! */ .add(var41_22);
                if (var33_14 == false) continue block0;
                var34_21 = (List)var38_18 /* !! */ ;
                v2 /* !! */  = ((Iterable)var34_21).iterator();
            } while (var7_7 > 0);
        }
        var35_15 = v2 /* !! */ ;
        var36_26 = 0;
        while (var35_15.hasNext()) {
            block49: {
                block48: {
                    block47: {
                        block46: {
                            block45: {
                                block44: {
                                    block42: {
                                        block43: {
                                            block41: {
                                                block39: {
                                                    block40: {
                                                        block38: {
                                                            var37_17 = var36_26++;
                                                            var38_18 /* !! */  = var35_15.next();
                                                            var39_30 = var4_4 + 78.0f + (float)var37_17 * 20.0f + this.X();
                                                            if (var6_6 <= '\u0000') return;
                                                            v5 /* !! */  = var33_14;
                                                            if (var7_7 <= 0) {
                                                                if (v5 /* !! */  != false) return;
                                                                cfr_temp_0 = var39_30 - (var4_4 + (float)TitledComponent.zkmBootstrap2("k", (int)1385, (long)(5031788513208316519L ^ var9_9)));
                                                                v5 /* !! */  = (CallSite)(cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1));
                                                            }
                                                            if (var33_14 != false) break block38;
                                                            if (v5 /* !! */  < 0) continue;
                                                            v6 = var39_30;
                                                            if (var2_2 <= 0) break block39;
                                                            v7 = var4_4 + this.getY();
                                                            if (var33_14 != false) break block40;
                                                            cfr_temp_1 = v6 - v7;
                                                            v5 /* !! */  = (CallSite)(cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1));
                                                        }
                                                        if (v5 /* !! */  > 0) continue;
                                                        v8 = var3_3;
                                                        v7 = this.getX();
                                                    }
                                                    v6 = v8 + v7;
                                                }
                                                var41_23 = v6;
                                                var42_25 = var5_5;
                                                v9 = var3_3 == var42_25 ? 0 : (var3_3 < var42_25 ? -1 : 1);
                                                v10 = var33_14;
                                                if (var7_7 >= 0) ** GOTO lbl87
                                                if (v10 != false) ** GOTO lbl86
                                                if (v9 <= 0) {
                                                    v9 = var42_25 == var41_23 ? 0 : (var42_25 < var41_23 ? -1 : 1);
lbl86:
                                                    // 2 sources

                                                    v10 = var33_14;
lbl87:
                                                    // 2 sources

                                                    if (v10 == false) {
                                                        v9 = v9 <= 0 ? (float)true : (float)false;
                                                    }
                                                } else {
                                                    v9 = (float)false;
                                                }
                                                if (var2_2 <= 0) break block41;
                                                if (v9 == false) ** GOTO lbl-1000
                                                cfr_temp_2 = (float)var8_8 - (var4_4 + 74.0f);
                                                v9 = cfr_temp_2 == 0.0f ? 0 : (cfr_temp_2 > 0.0f ? 1 : -1);
                                            }
                                            v11 = var33_14;
                                            if (var6_6 <= '\u0000') break block42;
                                            if (v11 != false) break block43;
                                            if (v9 <= 0) ** GOTO lbl-1000
                                            var41_23 = var39_30 + (float)TitledComponent.zkmBootstrap2("k", (int)20004, (long)(5660023678444861741L ^ var9_9));
                                            var42_25 = var8_8;
                                            v9 = var39_30 == var42_25 ? 0 : (var39_30 < var42_25 ? -1 : 1);
                                        }
                                        v11 = var33_14;
                                    }
                                    if (var2_2 <= 0) ** GOTO lbl111
                                    if (v11 != false) ** GOTO lbl110
                                    if (v9 <= 0) {
                                        v9 = var42_25 == var41_23 ? 0 : (var42_25 < var41_23 ? -1 : 1);
lbl110:
                                        // 2 sources

                                        v11 = var33_14;
lbl111:
                                        // 2 sources

                                        if (v11 == false) {
                                            v9 = v9 <= 0 ? (float)true : (float)false;
                                        }
                                    } else {
                                        v9 = (float)false;
                                    }
                                    if (var6_6 > '\u0000') {
                                        ** if (v9 == false) goto lbl-1000
lbl-1000:
                                        // 1 sources

                                        {
                                            v9 = (float)true;
                                            ** GOTO lbl121
                                        }
                                    }
                                    break block44;
lbl-1000:
                                    // 3 sources

                                    {
                                        v9 = (float)false;
                                    }
                                }
                                var40_20 = v9;
                                v12 /* !! */  = var33_14;
                                if (var2_2 <= 0) break block45;
                                if (v12 /* !! */  != false) break block46;
                                v12 /* !! */  = (CallSite)var40_20;
                            }
                            if (v12 /* !! */  == false) break block47;
                            this.hoveredOption = var38_18 /* !! */ ;
                        }
                        v13 = var1_1.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(v13, (String)TitledComponent.zkmBootstrap("n", (int)24681, (long)(5846068543964890205L ^ var9_9)));
                        Shapes2D.drawShape$default(v13, (char)var13_10, var3_3 + 6.0f, var39_30 - 2.0f, this.getX() - 12.0f, 20.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var14_11, 0.0f, var15_12, 0.0f, (int)TitledComponent.zkmBootstrap2("k", (int)4176, (long)(8077183246532716380L ^ var9_9)), null);
                    }
                    v14 = Fonts.getSF_M2();
                    v15 = this.getOptionLabel(var38_18 /* !! */ );
                    v16 /* !! */  = TitledComponent.zkmBootstrap2("k", (int)14151, (long)(6752162928239214669L ^ var9_9));
                    if (var6_6 < '\u0000') break block48;
                    v15 = StringsKt.take(v15, (int)v16 /* !! */ );
                    v17 = this.getOptionLabel(var38_18 /* !! */ );
                    if (var33_14 != false) break block49;
                    v16 /* !! */  = (CallSite)v17.length();
                }
                v17 = v16 /* !! */  > TitledComponent.zkmBootstrap2("k", (int)28942, (long)(32452312331011585L ^ var9_9)) ? TitledComponent.zkmBootstrap("n", (int)22055, (long)(8116770523112534556L ^ var9_9)) : "";
            }
            var30_27 = this.isSelected(var38_18 /* !! */ ) != false ? Theme.INSTANCE.getPrimaryColor() : Theme.INSTANCE.getInactiveTextColor();
            var31_28 = var39_30 + 1.5f;
            var32_29 = var3_3 + 27.0f;
            v14.drawStringWithShadow(var1_1, v15 + (String)v17, var16_13, var32_29, var31_28, var30_27);
            this.renderOption(var1_1, var3_3, var39_30, var38_18 /* !! */ );
            if (var6_6 <= '\u0000') return;
            if (var33_14 == false) continue;
        }
        RenderUtil.INSTANCE.endScissor(var1_1);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void updatePosition(long var1_1, int var3_2, int var4_3) {
        block22: {
            block23: {
                block21: {
                    var7_4 = var1_1 ^ 0L;
                    var12_5 = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - this.getX() / 2.0f;
                    var13_6 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - this.getY() / 2.0f - 50.0f;
                    var14_7 = var12_5 + 6.0f;
                    var11_8 = TitledComponent.R();
                    var15_9 = var12_5 + this.getX() / 2.0f - 2.0f;
                    var16_10 = var3_2;
                    v0 = var14_7 == var16_10 ? 0 : (var14_7 < var16_10 ? -1 : 1);
                    if (var11_8 != 0) ** GOTO lbl13
                    if (v0 <= 0) {
                        v0 = var16_10 == var15_9 ? 0 : (var16_10 < var15_9 ? -1 : 1);
lbl13:
                        // 2 sources

                        if (var11_8 == 0) {
                            v0 = v0 <= 0 ? (float)true : (float)false;
                        }
                    } else {
                        v0 = (float)false;
                    }
                    if (v0 == false) break block21;
                    var14_7 = var13_6 + 54.0f;
                    var15_9 = var13_6 + 67.0f;
                    var16_10 = var4_3;
                    v1 = var14_7 == var16_10 ? 0 : (var14_7 < var16_10 ? -1 : 1);
                    if (var11_8 != 0) ** GOTO lbl25
                    if (v1 <= 0) {
                        v1 = var16_10 == var15_9 ? 0 : (var16_10 < var15_9 ? -1 : 1);
lbl25:
                        // 2 sources

                        if (var11_8 == 0) {
                            v1 = v1 <= 0 ? (float)true : (float)false;
                        }
                    } else {
                        v1 = (float)false;
                    }
                    if (v1 == false) break block21;
                    this.W = true;
                    this.Z(0.0f);
                    if (var11_8 == 0) break block22;
                }
                var14_7 = var12_5 + this.getX() / 2.0f;
                var15_9 = var12_5 + this.getX() - 12.0f;
                var16_10 = var3_2;
                v2 = var14_7 == var16_10 ? 0 : (var14_7 < var16_10 ? -1 : 1);
                if (var11_8 != 0) ** GOTO lbl41
                if (v2 <= 0) {
                    v2 = var16_10 == var15_9 ? 0 : (var16_10 < var15_9 ? -1 : 1);
lbl41:
                    // 2 sources

                    if (var11_8 == 0) {
                        v2 = v2 <= 0 ? (float)true : (float)false;
                    }
                } else {
                    v2 = (float)false;
                }
                if (v2 == false) break block23;
                var14_7 = var13_6 + 54.0f;
                var15_9 = var13_6 + 67.0f;
                var16_10 = var4_3;
                v3 = var14_7 == var16_10 ? 0 : (var14_7 < var16_10 ? -1 : 1);
                if (var11_8 != 0) ** GOTO lbl53
                if (v3 <= 0) {
                    v3 = var16_10 == var15_9 ? 0 : (var16_10 < var15_9 ? -1 : 1);
lbl53:
                    // 2 sources

                    if (var11_8 == 0) {
                        v3 = v3 <= 0 ? (float)true : (float)false;
                    }
                } else {
                    v3 = (float)false;
                }
                if (v3 == false) break block23;
                this.W = false;
                this.Z(0.0f);
                if (var11_8 == 0) break block22;
            }
            var14_7 = var13_6 + 28.0f;
            var15_9 = var13_6 + 48.0f;
            var16_10 = var4_3;
            v4 = var14_7 == var16_10 ? 0 : (var14_7 < var16_10 ? -1 : 1);
            if (var11_8 != 0) ** GOTO lbl69
            if (v4 <= 0) {
                v4 = var16_10 == var15_9 ? 0 : (var16_10 < var15_9 ? -1 : 1);
lbl69:
                // 2 sources

                if (var11_8 == 0) {
                    v4 = v4 <= 0 ? (float)true : (float)false;
                }
            } else {
                v4 = (float)false;
            }
            if (v4 == false) break block22;
            var14_7 = var12_5 + 4.0f;
            var15_9 = var12_5 + 4.0f + this.getX() - 8.0f;
            var16_10 = var3_2;
            v5 = var14_7 == var16_10 ? 0 : (var14_7 < var16_10 ? -1 : 1);
            if (var11_8 != 0) ** GOTO lbl81
            if (v5 <= 0) {
                v5 = var16_10 == var15_9 ? 0 : (var16_10 < var15_9 ? -1 : 1);
lbl81:
                // 2 sources

                if (var11_8 == 0) {
                    v5 = v5 <= 0 ? (float)true : (float)false;
                }
            } else {
                v5 = (float)false;
            }
            if (v5 != false) {
                v6 = this.a;
                if (var11_8 == 0) {
                    v6 = v6 == false;
                }
                this.a = v6;
            }
        }
        v7 = this.hoveredOption;
        if (v7 != null) {
            var16_11 = v7;
            this.select(var16_11);
        }
        super.updatePosition(var7_4, var3_2, var4_3);
    }

    @Override
    public void keyPressed(int n, long l) {
        long l2 = l ^ 0L;
        int n2 = TitledComponent.R();
        if (n2 == 0) {
            switch (n) {
                case 256: 
                case 257: {
                    this.a = false;
                    if (n2 == 0) break;
                }
                case 259: {
                    this.searchText = StringsKt.dropLast(this.searchText, 1);
                    int n3 = ((CharSequence)this.searchText).length();
                    if (n2 == 0) {
                        n3 = n3 == 0 ? 1 : 0;
                    }
                    if (n3 == 0) break;
                    this.a = false;
                    break;
                }
            }
            super.keyPressed(n, l2);
        }
    }

    @Override
    public void charTyped(char c) {
        if (this.a && this.searchText.length() < 20) {
            this.searchText = this.searchText + c;
            this.Z(0.0f);
        }
    }

    public void renderOption(@NotNull DrawContext drawContext, float f, float f2, Object object) {
        long l = 0L;
        Intrinsics.checkNotNullParameter(drawContext, (String)((Object)TitledComponent.zkmBootstrap("n", (int)29865, (long)(0x41890687782DA90CL ^ l))));
    }

    public static void zkmSetFlag(int n) {
        p = n;
    }

    public static int int_zkmGetFlag() {
        return p;
    }

    public static int R() {
        return 0;
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
                            TitledComponent.c = ZkmKeys.a(-9219217513324587150L, 942296700733091177L, MethodHandles.lookup().lookupClass()).a(247475559679938L);
                            var25 = TitledComponent.c ^ 47266396716796L;
                            TitledComponent.n = new HashMap<K, V>(13);
                            TitledComponent.zkmSetFlag(18);
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
                            var20_5 = "\u0099\u0003Ua\u00b0U\u001b\u00db\u0084\u00d3Z\u00d7\u001a\u001c\u0099\u0017\u0018\u00c1\u009e\u00c8L\u009f\u007f)Q\u00cd\u0093\u00b0r\u00actG\u00f0\u0091\u00f5\u00a1\u0010\u00da\u00dc\u0086\u00c9 \u00a04\u00e7p\u00b5\u00af\\m\u0088\u00b1\u00af)\u00c0C\u0003z\u0014k81(S\u00f7\u00b7\u00c0G\u00b3>`Nu\u0015 \u00d7Q\u00e5\n\u00ba\u00b1\u008d~\u0001\u00efP\u00ab+\u00e9\u0001\u00a1\u000fOH`|\u00d0A5'\u0014\u00fc\n\u00a1\u0000n\u008f\u0010L6\u001a\u0003|oT\u00f1\u00a0\u009e\u00ee\\\u00dd\u00e5hs\u0018~\u0003a\u00a3\u0092[\u0005\u00eaQ\u00e4\u00d2\u00a2 \u0096\u00fa\u00be\u0016\u0001En\u009d\u0000\u0000\u008b \u00e9\u001c\u00b9n\u00c3\u0081$\u00f49K\u00eds\u0006\u001b0\u00ff\u00a0y\u0014h\u00b6\u00a6T\u00bdL\u001c\u00e7-\u0002\u009b\u00b5\u009d\u0010S\u0096i[\u00b0\u0081\u00ee+1\u00f2*\u00eb\u00a97\"{\u0010V\u0012\u00c24$\u0017\u00e8\u0014\u0003\u00f2\u00c2\u0010\u00a7#\u00da\f\u0010\u00d2Xj\u00c5\u00ca\u00f0j\u00984eNI\u00f8<\u0001L";
                            var22_6 = 233;
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
                                var23_3[var21_4++] = TitledComponent.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u00d8\u001ax\u0083\u00b9\u00fboD\u00fa\u00dd \u00a4)>\u00e2\u00c4\u0010\\c\u00fc\u00aa\u00ec(\u001d\u00c7@x\r#k\u00d1V\u0005";
                                var22_6 = 33;
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
                                var23_3[var21_4++] = TitledComponent.zkmDecodeUtf8(var24_9).intern();
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
                    TitledComponent.i = var23_3;
                    TitledComponent.m = new String[12];
                    TitledComponent.B = new HashMap<K, V>(13);
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
                    var11_12 = new long[9];
                    var8_13 = 0;
                    var9_14 = "\u00bcHq4\u00cf\u0002\u0014\u00a1q\u00897\u00f1\b\u00acl\u0018\u00a7\u0096\u00edL\u008bu\u00f2~\u0007a\u00db\u0017\u008d\u0015\u00a5aW\u0087B~\u00f1\u00f1\u0097{\u00ce\u00ddR\u0095\u00a0\u0093pv\u008d#\u00bb\u00c4\u0001\u00ae\u00b6\u00db";
                    var10_15 = 56;
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
                        var9_14 = "\u00a17J\u00c1\u0000u\u008c\u00bf\u001eh\u00de0\u00bbjD\u001f";
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
            TitledComponent.u = var11_12;
            TitledComponent.A = new Integer[9];
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
        var4_22 = var0_20.doFinal(new byte[]{60, 38, -57, 62, -79, -82, -36, 105});
        ** while (true)
        TitledComponent.C = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x48BE;
        if (m[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])TitledComponent.n.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    TitledComponent.n.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dt", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = i[n2].getBytes("ISO-8859-1");
            TitledComponent.m[n2] = TitledComponent.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return m[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TitledComponent.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5B81;
        if (A[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = u[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])B.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    B.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dt", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TitledComponent.A[n2] = n3;
        }
        return A[n2];
    }

    private static int e(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TitledComponent.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/dt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TitledComponent.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TitledComponent.class, "e", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

