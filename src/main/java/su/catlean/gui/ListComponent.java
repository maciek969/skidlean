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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.ConfigStore;
import su.catlean.config.ItemPreset;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.Component;
import su.catlean.gui.ItemPresetEditor;
import su.catlean.gui.ListComponent$1;
import su.catlean.gui.TitledComponent;
import su.catlean.module.Module;
import su.catlean.module.equip.InventoryManager;
import su.catlean.render.Gradient2D;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ListComponent
extends Component {
    @NotNull
    private final List list;
    @NotNull
    private final Function1 clickAction;
    @NotNull
    private final Function1 clickRemoveAction;
    @Nullable
    private String A;
    private boolean i;
    @NotNull
    private String searchText;
    private boolean C;
    private boolean K;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map g;
    private static final long[] k;
    private static final Integer[] n;
    private static final Map o;
    private static final long p;

    public ListComponent(@NotNull List list, @NotNull Function1 function1, @NotNull Function1 function12) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(function1, "clickAction");
        Intrinsics.checkNotNullParameter(function12, "clickRemoveAction");
        this.list = list;
        this.clickAction = function1;
        this.clickRemoveAction = function12;
        this.searchText = "";
    }

    @NotNull
    public final List getList() {
        return this.list;
    }

    @NotNull
    public final Function1 getClickAction() {
        return this.clickAction;
    }

    @NotNull
    public final Function1 getClickRemoveAction() {
        return this.clickRemoveAction;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, long var2_2, int var4_3, int var5_4) {
        block44: {
            v0 = var2_2;
            var8_5 = v0 ^ 85446347042374L;
            v1 = v0 ^ 21275552979796L;
            var12_6 = (int)(v1 >>> 48);
            var13_7 = (int)(v1 << 16 >>> 48);
            var14_8 = (int)(v1 << 32 >>> 32);
            var15_9 = v0 ^ 42294086766012L;
            var19_10 = v0 ^ 126255758620571L;
            var23_11 = v0 ^ 119233142815521L;
            v2 = TitledComponent.R();
            Intrinsics.checkNotNullParameter(var1_1, "context");
            var43_12 = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - this.getX() / 2.0f;
            var42_13 = v2;
            var44_14 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - this.getY() / 2.0f - 50.0f;
            var45_15 = var43_12 + this.getX() - 25.0f;
            var46_17 = var43_12 + this.getX() - 5.0f;
            var47_20 = var4_3;
            v3 = var45_15 == var47_20 ? 0 : (var45_15 < var47_20 ? -1 : 1);
            if (var42_13 != 0) ** GOTO lbl24
            if (v3 <= 0) {
                v3 = var47_20 == var46_17 ? 0 : (var47_20 < var46_17 ? -1 : 1);
lbl24:
                // 2 sources

                if (var42_13 == 0) {
                    v3 = v3 <= 0 ? (float)true : (float)false;
                }
            } else {
                v3 = (float)false;
            }
            if (v3 == false) ** GOTO lbl-1000
            var45_15 = var44_14 + 28.0f;
            var46_17 = var44_14 + 48.0f;
            var47_20 = var5_4;
            v4 = var45_15 == var47_20 ? 0 : (var45_15 < var47_20 ? -1 : 1);
            if (var42_13 != 0) ** GOTO lbl36
            if (v4 <= 0) {
                v4 = var47_20 == var46_17 ? 0 : (var47_20 < var46_17 ? -1 : 1);
lbl36:
                // 2 sources

                if (var42_13 == 0) {
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
            this.C = v5;
            Fonts.getSF_SB().drawStringRight(var15_9, var1_1, "Select", var43_12 + this.getX() / 2.0f, var44_14 + 3.0f, 10);
            v6 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v6, "pose(...)");
            v7 = this.getX() - 33.0f;
            v8 = Theme.INSTANCE.g().brighter();
            Intrinsics.checkNotNullExpressionValue(v8, "brighter(...)");
            Shapes2D.drawShape$default(v6, (char)var12_6, var43_12 + 5.0f, var44_14 + 28.0f, v7, 20.0f, 4.0f, 0.0f, 0.1f, 1.0f, v8, Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var13_7, 0.0f, var14_8, 0.0f, 14336, null);
            v9 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v9, "pose(...)");
            v10 = Theme.INSTANCE.g().brighter();
            Intrinsics.checkNotNullExpressionValue(v10, "brighter(...)");
            Shapes2D.drawShape$default(v9, (char)var12_6, var43_12 + this.getX() - 24.5f, var44_14 + 28.0f, 20.5f, 20.0f, 4.0f, 0.0f, 0.1f, 1.0f, v10, Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var13_7, 0.0f, var14_8, 0.0f, 14336, null);
            Fonts.getSF_SB().drawStringCentered(var1_1, "+", var43_12 + this.getX() - 15.0f, var44_14 + 35.5f, this.C != false ? Theme.INSTANCE.getTextColor() : Theme.INSTANCE.getInactiveTextColor(), var23_11);
            v11 = Fonts.getSF_SB();
            v12 = this;
            if (var42_13 != 0) ** GOTO lbl63
            if (v12.i) {
                v12 = this;
lbl63:
                // 2 sources

                v13 = v12.searchText + (Globals.now() % 700L > ListComponent.p ? "_" : "");
            } else {
                v13 = "Enter name";
            }
            var32_22 = this.i != false ? Theme.INSTANCE.getTextColor() : Theme.INSTANCE.getInactiveTextColor();
            var33_23 = var44_14 + 29.0f;
            var34_24 = var43_12 + 7.0f;
            v11.drawStringWithShadow(var1_1, (String)v13, var19_10, var34_24, var33_23, var32_22);
            v14 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v14, "pose(...)");
            v15 = this.getX() - 8.0f;
            v16 = this.getY() - 57.0f;
            v17 = Theme.INSTANCE.g().brighter();
            Intrinsics.checkNotNullExpressionValue(v17, "brighter(...)");
            Shapes2D.drawShape$default(v14, (char)var12_6, var43_12 + 4.0f, var44_14 + 53.0f, v15, v16, 4.0f, 2.0f, 0.1f, 1.0f, v17, Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var13_7, 0.0f, var14_8, 0.0f, 14336, null);
            this.A = null;
            this.K = false;
            RenderUtil.INSTANCE.beginScissor(var1_1, new Rect(var43_12, var44_14 + 55.0f, var43_12 + this.getX(), var44_14 + this.getY() - 6.0f));
            var46_18 = this.list;
            var45_16 = ((Iterable)CollectionsKt.sortedWith(var46_18, new ListComponent$1())).iterator();
            var46_19 = 0;
            block0: while (true) {
                v18 = var45_16.hasNext();
                while (v18) {
                    block51: {
                        block50: {
                            block49: {
                                block47: {
                                    block48: {
                                        block46: {
                                            block45: {
                                                var47_21 = var46_19++;
                                                var48_33 = (String)var45_16.next();
                                                var49_34 = var44_14 + 58.0f + (float)var47_21 * 18.0f + this.X();
                                                if (var42_13 != 0) break block44;
                                                cfr_temp_0 = var49_34 - (var44_14 + 40.0f);
                                                v19 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                if (var42_13 != 0) break block45;
                                                if (v19 < 0) break block46;
                                                v20 = var49_34;
                                                v21 = var44_14 + this.getY();
                                                if (var42_13 != 0) break block47;
                                                cfr_temp_1 = v20 - v21;
                                                v19 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1);
                                            }
                                            if (v19 <= 0) break block48;
                                        }
                                        if (var42_13 == 0) continue block0;
                                        Module.zkmSetFlag(new Module[3]);
                                    }
                                    v20 = var43_12;
                                    v21 = this.getX();
                                }
                                var51_37 = v20 + v21;
                                var52_39 = var4_3;
                                v22 = var43_12 == var52_39 ? 0 : (var43_12 < var52_39 ? -1 : 1);
                                if (var42_13 != 0) ** GOTO lbl114
                                if (v22 <= 0) {
                                    v22 = var52_39 == var51_37 ? 0 : (var52_39 < var51_37 ? -1 : 1);
lbl114:
                                    // 2 sources

                                    if (var42_13 == 0) {
                                        v22 = v22 <= 0 ? (float)true : (float)false;
                                    }
                                } else {
                                    v22 = (float)false;
                                }
                                if (v22 == false) ** GOTO lbl-1000
                                cfr_temp_2 = (float)var5_4 - (var44_14 + 58.0f);
                                v23 = cfr_temp_2 == 0.0f ? 0 : (cfr_temp_2 > 0.0f ? 1 : -1);
                                if (var42_13 != 0) break block49;
                                if (v23 <= 0) ** GOTO lbl-1000
                                var51_37 = var49_34 + 17.0f;
                                var52_39 = var5_4;
                                v23 = var49_34 == var52_39 ? 0 : (var49_34 < var52_39 ? -1 : 1);
                            }
                            if (var42_13 != 0) ** GOTO lbl130
                            if (v23 <= 0) {
                                v23 = var52_39 == var51_37 ? 0 : (var52_39 < var51_37 ? -1 : 1);
lbl130:
                                // 2 sources

                                if (var42_13 == 0) {
                                    v23 = v23 <= 0 ? (float)true : (float)false;
                                }
                            } else {
                                v23 = (float)false;
                            }
                            if (v23 != false) {
                                v24 = true;
                            } else lbl-1000:
                            // 3 sources

                            {
                                v24 = var50_35 = false;
                            }
                            if (var42_13 != 0) break block50;
                            if (!var50_35) break block51;
                            this.A = var48_33;
                        }
                        v25 = var1_1.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(v25, "pose(...)");
                        Shapes2D.drawShape$default(v25, (char)var12_6, var43_12 + 6.0f, var49_34 - 2.0f, this.getX() - 12.0f, 20.0f, 4.0f, 2.0f, 0.1f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var13_7, 0.0f, var14_8, 0.0f, 14336, null);
                    }
                    v26 = Fonts.getSF_M2();
                    v27 = var48_33;
                    if (var42_13 == 0) {
                        if (v27.length() > 28) {
                            v28 = var48_33.substring(0, 28);
                            Intrinsics.checkNotNullExpressionValue(v28, "substring(...)");
                            v27 = v28 + "...";
                        } else {
                            v27 = var48_33;
                        }
                    }
                    var32_22 = Intrinsics.areEqual(var48_33, ConfigStore.INSTANCE.getItemPresetConfigFile().getCurrent().getId()) != false ? Theme.INSTANCE.getPrimaryColor() : Theme.INSTANCE.getInactiveTextColor();
                    var33_23 = var49_34 + 1.0f;
                    var34_24 = var43_12 + 10.0f;
                    v26.drawStringWithShadow(var1_1, (String)v27, var19_10, var34_24, var33_23, var32_22);
                    var52_39 = var43_12 + this.getX() - 20.0f;
                    var53_41 = var43_12 + this.getX() - 10.0f;
                    var54_42 = var4_3;
                    v29 = var52_39 == var54_42 ? 0 : (var52_39 < var54_42 ? -1 : 1);
                    if (var42_13 != 0) ** GOTO lbl166
                    if (v29 <= 0) {
                        v29 = var54_42 == var53_41 ? 0 : (var54_42 < var53_41 ? -1 : 1);
lbl166:
                        // 2 sources

                        if (var42_13 == 0) {
                            v29 = v29 <= 0 ? (float)true : (float)false;
                        }
                    } else {
                        v29 = (float)false;
                    }
                    if (v29 == false) ** GOTO lbl-1000
                    var52_39 = var49_34 + 3.0f;
                    var53_41 = var49_34 + 13.0f;
                    var54_42 = var5_4;
                    v30 = var52_39 == var54_42 ? 0 : (var52_39 < var54_42 ? -1 : 1);
                    if (var42_13 != 0) ** GOTO lbl178
                    if (v30 <= 0) {
                        v30 = var54_42 == var53_41 ? 0 : (var54_42 < var53_41 ? -1 : 1);
lbl178:
                        // 2 sources

                        if (var42_13 == 0) {
                            v30 = v30 <= 0 ? (float)true : (float)false;
                        }
                    } else {
                        v30 = (float)false;
                    }
                    if (v30 != false) {
                        v31 = true;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v31 = var51_36 = false;
                    }
                    if (var51_36) {
                        v32 = Color.RED;
                        Intrinsics.checkNotNullExpressionValue(v32, "RED");
                        v33 = RenderUtil.INSTANCE.withAlpha(v32, 0.7f);
                    } else {
                        v34 = Color.RED;
                        Intrinsics.checkNotNullExpressionValue(v34, "RED");
                        v33 = RenderUtil.INSTANCE.withAlpha(v34, 0.5f);
                    }
                    var52_38 = v33;
                    v35 = var43_12 + this.getX() - 20.0f;
                    Intrinsics.checkNotNullExpressionValue(var1_1.getMatrices(), "pose(...)");
                    v36 = var51_36 != false ? 2.0f : 1.0f;
                    if (var51_36) {
                        v37 = Color.RED;
                    } else {
                        v38 = Color.RED;
                        Intrinsics.checkNotNullExpressionValue(v38, "RED");
                        v37 = RenderUtil.INSTANCE.withAlpha(v38, 0.5f);
                    }
                    var53_40 = v37;
                    Intrinsics.checkNotNull(var53_40);
                    var32_22 = var53_40;
                    var33_23 = v36;
                    var35_26 = var52_38;
                    var36_27 = var52_38;
                    var37_28 = var52_38;
                    var38_29 = var52_38;
                    var39_30 = 3.0f;
                    var40_31 = 10.0f;
                    var41_32 = 10.0f;
                    Gradient2D.drawGradientRect(v35, var49_34 + 3.0f, var8_5, var41_32, var40_31, var39_30, var38_29, var37_28, var36_27, var35_26, var34_25, var33_23, var32_22);
                    v18 = this.K;
                    if (var42_13 == 0) {
                        if (v18) continue block0;
                        v18 = var51_36;
                    }
                    if (var42_13 != 0) continue;
                    if (!v18) continue block0;
                    this.K = true;
                    if (var42_13 != 0) break block0;
                    continue block0;
                }
                break;
            }
            RenderUtil.INSTANCE.endScissor(var1_1);
        }
    }

    @Override
    public void updatePosition(long l, int n, int n2) {
        long l2 = l;
        long l3 = l2 ^ 0L;
        long l4 = l2 ^ 0x7DE2C44E9AB0L;
        float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - this.getX() / 2.0f;
        float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - this.getY() / 2.0f - 50.0f;
        if (this.K && this.A != null) {
            String string = this.A;
            Intrinsics.checkNotNull(string);
            this.clickRemoveAction.invoke(string);
            return;
        }
        if (this.C) {
            this.updateFilter(l4);
        }
        float f3 = f2 + 28.0f;
        float f4 = f2 + 48.0f;
        float f5 = n2;
        boolean bl = f3 <= f5 ? f5 <= f4 : false;
        if (bl) {
            f3 = f + 4.0f;
            f4 = f + this.getX() - 33.0f;
            f5 = n;
            boolean bl2 = f3 <= f5 ? f5 <= f4 : false;
            if (bl2) {
                this.i = !this.i;
            }
        }
        String string = this.A;
        if (string != null) {
            String string2 = string;
            String string3 = this.A;
            Intrinsics.checkNotNull(string3);
            this.clickAction.invoke(string3);
        }
        super.updatePosition(l3, n, n2);
    }

    @Override
    public void keyPressed(int n, long l) {
        long l2 = l;
        long l3 = l2 ^ 0x123B6849EA7CL;
        long l4 = l2 ^ 0L;
        switch (n) {
            case 256: {
                this.i = false;
                break;
            }
            case 257: {
                this.updateFilter(l3);
                break;
            }
            case 259: {
                this.searchText = StringsKt.dropLast(this.searchText, 1);
                if (!(((CharSequence)this.searchText).length() == 0)) break;
                this.i = false;
                break;
            }
        }
        super.keyPressed(n, l4);
    }

    private final void updateFilter(long l) {
        block5: {
            int n = (int)(((l = a ^ l) ^ 0x2ADCD193004FL) >>> 32);
            int n2 = TitledComponent.R();
            boolean bl = StringsKt.isBlank(this.searchText);
            if (n2 == 0) {
                bl = !bl;
            }
            if (bl) {
                Object object = ConfigStore.INSTANCE.getItemPresetConfigFile().getData();
                Iterable iterable = object;
                Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(object, 10));
                for (Object t : iterable) {
                    ItemPreset itemPreset = (ItemPreset)t;
                    Collection collection2 = collection;
                    collection2.add(itemPreset.getId());
                    if (n2 == 0) {
                        if (n2 == 0) continue;
                    }
                    break block5;
                }
                if (!((List)collection).contains(this.searchText)) {
                    String string = this.searchText;
                    object = InventoryManager.INSTANCE.getPresetSlots(n, string);
                    ConfigStore.INSTANCE.getItemPresetConfigFile().getData().add(object);
                    ConfigStore.INSTANCE.getItemPresetConfigFile().setCurrent((ItemPreset)object);
                    this.searchText = "";
                    this.i = false;
                    this.setClosing(true);
                    ClickGuiScreen.INSTANCE.setComponent(new ItemPresetEditor());
                }
            }
        }
    }

    @Override
    public void charTyped(char c) {
        if (this.i && this.searchText.length() < 20) {
            this.searchText = this.searchText + c;
            this.Z(0.0f);
        }
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
                            ListComponent.a = ZkmKeys.a(-5344454639855695446L, -8233699189029107586L, MethodHandles.lookup().lookupClass()).a(21027743833956L);
                            ListComponent.g = new HashMap<K, V>(13);
                            var16 = ListComponent.a ^ 93996785590907L;
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
                            var25_3 = new String[14];
                            var23_4 = 0;
                            var22_5 = "\u0001\u00a4\u00c6\u0088p\u00efU\u00f6\u0015.(\u00bcL7\u008c\u00da\u0018\u00f8Q\u00e2\u008c<\u00cbgo\u00f7\u00b5\u0094\u00ff\u00f6\u00a2\u00d9\u008c*\u001a`\u00cc\u00aa~\u00ba\u000e\u0010\u0081\u001a?\u00d3\u00d4Z\u008a\u00cc\u008f#\u0099\r?TP\u00e8\u0010\u0019\u00b832\f{\u00cb\u00aeKe7\u00c8\u00c8\u00bb\u00fcu\u0018\u00c0S\u00e4\u00ab\u00a4\u00b5V\u00ec \u00d2\u00a1%\u00ac\u00a9\u00f0\u00e1\u009e\u0002\u00de\u0086\u00d6\u00cd\u00a4\u009e\u0010bI\u0081\u009d\u009e\u0096\u00c9\u00f5\u00aax\u00d4\u00ddk\u00ab\u00af\u0014\u0010\u009a1@\u00ffk\u00ef\u00c1\u00f9\u00f5\u0011`\u0083\u00d3\u0019\u00f2} T\u008f\u009ae\u001a\u00d6V\u00fc\u0000\u00c08\u0080\u00dfY\u00a3\u00b6@\u00e3JC\u00cd\u00f2\u0082\u0015.\u008b\u00d1Q\u00d9\u00e0)_ \u00f4#\u001c\u0097\u00f5\u00ae\u00fe}q<T\u00c7rj2\u00c2\u0086\u0090\u00e2\u00c2\u0086\u00c1\u001d\u0013\\\u0085ag\u00ff\u00cb\u00b9\u00a6 \u008a \u00ce*\u0004\u00cd.\u0090\u00eb4\u00fa92se\u0084\u00c8\u00cf\u00ff\u0006\u00bf\u00b0yV\u0081~q\u000b\u00dew\u00e8E(\u0087\u00d1\u00d5\u008fI\u00c8\u0084\u00f6\u001a\u0081\u00ea\u00ff\u00a9\u00ack0\u00bb\u00ea\u00d3Z\u0092\u00c8\u00b9/\u00c1\u0015(\u0084m/\u00c1!a\u00a7\u00ae\u00946@L\u00bf\u0010\u00f3\b\u00a4\u0082D\u00d1\u00ab\u001c\u00ef\u00ad&\u00f5;y\u000b\u008d";
                            var24_6 = 291;
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
                                var25_3[var23_4++] = ListComponent.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00bc\u00cd\u0001,\u00ab\u00c0\u000e\u00ab\u00b2\u0093\u00dc\u00e8\u00f3J\u00ef\u0086\u0005\u0080\u00ba\u008d\u00db\u00bc\u00fa\u00fe Dp\u0000\f\u00c5\u00ed\u00ddn\u00a77\f\u00aa\u0001\u0015\u00b3\u0084j\u00ee\u009f\u00d7\u0002\u00fd8P\u001a\u00b9\u00fe\u00a7<\u00125$";
                                var24_6 = 57;
                                var21_7 = 24;
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
                                var25_3[var23_4++] = ListComponent.zkmDecodeUtf8(var26_9).intern();
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
                    ListComponent.c = var25_3;
                    ListComponent.d = new String[14];
                    ListComponent.o = new HashMap<K, V>(13);
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
                    var9_14 = "\u00ba\t\u00e3.!\u0014\u0006\u0080\u000e\u0002%\u00deDv\u009e\u00b4!\u00c1\u009f\u00f6\u00f2/\u00e4c^\"\u0012<cO\u00e4G\u0090\u0007\u009e\u00e3\u00e9\u00dbP\u00c6\u0083lD\u008a\u00d9\u00f8\u001e\u00b6\u00e9\u00af$\u00f8b\u00ff\u0012r\u008b\u0014d\u0007f\u00de\u00d5\u00c2";
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
                        var9_14 = "\u0004u\u00e0AD\u00f2\u00eeD\u0094v\u00dc\u00a46\u0093\u008e\\";
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
            ListComponent.k = var11_12;
            ListComponent.n = new Integer[10];
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
        var4_22 = var0_20.doFinal(new byte[]{13, 66, 53, 79, 56, -48, -48, -1});
        ** while (true)
        ListComponent.p = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7EAB;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/db", exception);
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
            ListComponent.d[n2] = ListComponent.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ListComponent.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/db" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2FB8;
        if (ListComponent.n[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = k[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])o.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    o.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/db", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ListComponent.n[n2] = n3;
        }
        return ListComponent.n[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ListComponent.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/db" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ListComponent.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ListComponent.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

