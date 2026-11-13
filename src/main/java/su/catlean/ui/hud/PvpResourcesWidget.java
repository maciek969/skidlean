/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.InventoryUtil;
import su.catlean.zkm.ZkmKeys;

public final class PvpResourcesWidget
extends HudElement {
    @NotNull
    public static final PvpResourcesWidget N;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting totems$delegate;
    @NotNull
    private static final BooleanSetting crystals$delegate;
    @NotNull
    private static final BooleanSetting gapples$delegate;
    @NotNull
    private static final BooleanSetting crapples$delegate;
    @NotNull
    private static final BooleanSetting fireworks$delegate;
    @NotNull
    private static final BooleanSetting xp$delegate;
    @NotNull
    private static final BooleanSetting obby;
    private static final long a;
    private static final String[] b;
    private static final String[] f;
    private static final Map g;
    private static final long[] i;
    private static final Integer[] k;
    private static final Map l;

    private PvpResourcesWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x247A5BDAF36CL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "pvp-resources-widget", (char)n2);
    }

    private final boolean getTotems() {
        return (Boolean)totems$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getCrystals() {
        return (Boolean)crystals$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getGapples() {
        return (Boolean)gapples$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getCrapples() {
        return (Boolean)crapples$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getFireworks() {
        return (Boolean)fireworks$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getXp() {
        return (Boolean)xp$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean S() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        return (Boolean)obby.getValue(this, delegatedProperties[PvpResourcesWidget.zkmBootstrap2("u", (int)26321, (long)(0x25B4FBC98E86403AL ^ l))]);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2) {
        block20: {
            block22: {
                block21: {
                    v0 = var1_1;
                    var8_3 = v0 ^ 7291883418847L;
                    v1 = v0 ^ 104104530525409L;
                    var18_4 = (int)(v1 >>> 48);
                    var19_5 = (int)(v1 << 16 >>> 32);
                    var20_6 = (int)(v1 << 48 >>> 48);
                    var27_7 = v0 ^ 20173347401993L;
                    Intrinsics.checkNotNullParameter(var3_2, "context");
                    var43_8 = 2;
                    var42_9 = ArrayListEntry.u();
                    var44_10 = new ArrayList<E>();
                    v2 = this.getTotems();
                    if (!var42_9) {
                        if (v2) {
                            v3 = Items.TOTEM_OF_UNDYING;
                            Intrinsics.checkNotNullExpressionValue(v3, "TOTEM_OF_UNDYING");
                            var44_10.add(v3);
                        }
                        v2 = this.getCrystals();
                    }
                    if (!var42_9) {
                        if (v2) {
                            v4 = Items.END_CRYSTAL;
                            Intrinsics.checkNotNullExpressionValue(v4, "END_CRYSTAL");
                            var44_10.add(v4);
                        }
                        v2 = this.getXp();
                    }
                    if (!var42_9) {
                        if (v2) {
                            v5 = Items.EXPERIENCE_BOTTLE;
                            Intrinsics.checkNotNullExpressionValue(v5, "EXPERIENCE_BOTTLE");
                            var44_10.add(v5);
                        }
                        v2 = this.getGapples();
                    }
                    if (!var42_9) {
                        if (v2) {
                            v6 = Items.ENCHANTED_GOLDEN_APPLE;
                            Intrinsics.checkNotNullExpressionValue(v6, "ENCHANTED_GOLDEN_APPLE");
                            var44_10.add(v6);
                        }
                        v2 = this.getCrapples();
                    }
                    if (!var42_9) {
                        if (v2) {
                            v7 = Items.GOLDEN_APPLE;
                            Intrinsics.checkNotNullExpressionValue(v7, "GOLDEN_APPLE");
                            var44_10.add(v7);
                        }
                        v2 = this.getFireworks();
                    }
                    if (var42_9) break block21;
                    if (v2) {
                        v8 = Items.FIREWORK_ROCKET;
                        Intrinsics.checkNotNullExpressionValue(v8, "FIREWORK_ROCKET");
                        var44_10.add(v8);
                    }
                    v9 = this;
                    if (var42_9) break block22;
                    v2 = v9.S();
                }
                if (v2) {
                    v10 = Items.OBSIDIAN;
                    Intrinsics.checkNotNullExpressionValue(v10, "OBSIDIAN");
                    var44_10.add(v10);
                }
                v9 = this;
            }
            v11 = var3_2.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v11, "pose(...)");
            HudElement.drawBackground$default(v9, v11, this.getX(), (short)var18_4, var19_5, this.getY(), 1.0f + 20.0f * (float)var44_10.size(), 29.0f, (short)var20_6, 0.0f, false, 96, null);
            var3_2.getMatrices().pushMatrix();
            var3_2.getMatrices().translate(this.getX(), this.getY());
            for (Item var46_15 : var44_10) {
                if (var42_9) break block20;
                v12 = Intrinsics.areEqual(var46_15, CollectionsKt.last(var44_10));
                if (!var42_9) {
                    if (v12 == 0) {
                        v13 = this;
                        v14 = var3_2.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(v14, "pose(...)");
                        HudElement.queueShadow$default(v13, v14, (float)var43_8 + 17.5f, 7.0f, 0.0f, null);
                    }
                    var3_2.drawItem(var46_15.getDefaultStack(), var43_8, 2);
                    v12 = InventoryUtil.findItemSlot$default(InventoryUtil.INSTANCE, var46_15, false, null);
                }
                v15 = var47_16 = v12;
                if (var42_9) ** GOTO lbl89
                if (v15 > 0) {
                    v15 = var47_16;
lbl89:
                    // 2 sources

                    v16 = String.valueOf(v15);
                } else {
                    v16 = "-";
                }
                var48_17 = v16;
                v17 = Fonts.getSF_M();
                v18 = var3_2.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v18, "pose(...)");
                v19 = (float)var43_8 + 8.0f - Fonts.getSF_M().getWidth(var27_7, var48_17) / 2.0f;
                Intrinsics.checkNotNullExpressionValue(Color.WHITE, "WHITE");
                var37_12 = null;
                var40_13 = false;
                FontRenderer.drawString$default(v17, v18, var48_17, v19, 18.0f, var8_3, var41_14, var40_13, var37_12);
                var43_8 += 20;
                if (!var42_9) continue;
            }
            var3_2.getMatrices().popMatrix();
            this.setSize(1.0f + (float)var44_10.size() * 20.0f, 29.0f);
        }
    }

    @Override
    public void renderOverlay(@NotNull DrawContext drawContext, long l) {
        long l2 = l ^ 0x168DD08623FL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Fonts.getSF_M().clearCache(n, (char)n2, (short)n3);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PvpResourcesWidget.a = ZkmKeys.a(3253173483629988902L, -2862634010910326185L, MethodHandles.lookup().lookupClass()).a(139530684506669L);
                        var20 = PvpResourcesWidget.a ^ 12859609424143L;
                        var24_1 = var20 ^ 129088906336488L;
                        PvpResourcesWidget.g = new HashMap<K, V>(13);
                        var11_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_3 = 1; var12_3 < 8; ++var12_3) {
                            v2 = v2;
                            v2[var12_3] = (byte)(var20 << var12_3 * 8 >>> 56);
                        }
                        var11_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_4 = new String[34];
                        var16_5 = 0;
                        var15_6 = "\u00f5=\u009b\u0016\u00c1z$\u0086\u0001\u009d\u00aa\u00aa\u009e\u00ec\u00eaW\u00de\u0019)\u00d0py:8N'\u00f0\u00a7\u00cb#b'\u0005\u00e6}p\u00b6\u00ea\u00ae\u00e5 \u008a_\u0092/\u0096g\u0080\u008d\u00f8\u001b\u00edS\u00d4&A\u00d4\u00df\u00d9\u008bH\u0098\u00de\u00f3\u001cP\u00d0I\u00a5\u008a\u00d7\n\u00bd\u0018\u0081\u00aa^%\u0084\u00bc\u001a\u00e7\u00ee\rR;G\u009d\u00dd\u00ebv\u001e\u00c7\u009b\u00c8\u00e0\u0014\u00db\u00108\u00d9Qh)\u00e2\u00fc\u00b4\u00d6\u00ff\u00d2fQ\u0004q\u0000\u0010\u00c2\u00d9t\u0002\u0013\u0011m\u00a8\u00a1\u0098N\u0013\u00e5}VI\u0010{\u00d6v\u0081\u00c7\u0004\u00b3)\u009c7\u00f1\u00a3f\u0007]p\u0018\u007fC-L\u00ef\u00b1\u00fe\u0017p\u00b1;lDp\u0086\u0080\u0007\u00ad\u009b{\u00f9\r\u00ce\u00fa\u0018\u0018\u00bb\u00afj\u008b\u00c1DR\u001e\u00ca1\u0019\u00c8\u00f8\u00c0\u00c3\u00bb2\u00e6\u00e4P\u00a0\u0085F\u0010G\u00e3C\u00b9j\u00ca\u0090\u0011\u001f\u00de\u00b3\u00c4\u0082x\u00ba\u00c9 \u0099\u001d\u0011\u0084\u00d6\u008f;\u00b2/;\u0002\u00a8L\u0084\u00e8*\u00db\u00f4\u00fa\u00e7]\u0089\u00f9\u00d7\u00a1\u0085p\u00e9\u0096Xe\u00d4\u0010\u000b\u0017\u00d6\u00a0s\u0092\u00c0\u0092\u001c\u00bf\u00ceG\u0094\u00ab}G \u008a\u00c7m\u00c6\u00d4|\u00b36O\u00c8+\u0094R-\u009d\u00cf\u00be\u00b4^\u000fuL\u00ba\u0099\u00fb\u0004]\b\u0006I\u0012H\u0010>\u00adZI6\u00a1\u009a\u00cb\u007f\u0016[\u00b2\u00c5\u0019(\u009c\u0010\u0085\u0013\u00b3\u00d3\u00deda8\u001b:\u00f7{\u0010gX\u00f8(@\u00de\u00b7\u00a9\u00c1\u009c?0\u0082\u001a\u0085\u00aaP\u000fj1\t\u00bf\u009et%\n\u001b\u00ael\u0097b\u00dcBa)\u00ea\u00c9\u00c4\u00f7\u0006K\u00b1]\u00be\u0018\u00f6\u00d1L\u00e4JaX\u00009\u00fejl\u00e7\u008ac\u000b\u0084\u0082S\u00a4\u001e\u0007{m\u0018\u00d1\u0017\u00d71\u00d5U\u0011\u00a4\n|\u00c1\u0092\u00be\bAf\u0092\u00cd\u00f8>,k^i $\u00b8\u0086\u001f\u00b8\u00e3\u00ca\u009b\u00e1\u00ael\u00ed\u0000\u00f0\u009b\u00c7-\f\u008e\u00bd\u00b0\u00be\u00e0\u0015X\u0089|l\u00b9\r1\u0098\u0018>z\u00e9\u0007\u00db\u00dc\u001fK![_A\u00a7\u00f6\u00f5o\u0080\u0093f\u0093M\u0015\u008a\u00c2\u0010{\u00b1\u008f\u00a0\u00dd\u00f1\u009bz.\u00d5B\u00b2x\u00ea\u00f8\u0094((\u000eg1\u0088\u00cc\u00d7\u00ea\u00b5\u0086\u009d\r&\u00acG\u0010\u00cbx\u00be\u0089\u00ec\u00b4\u00a6\u00f11\u00be\u008a\u00d6M?;x\u00aey,\u00ac+\u00ed\u008e% \u00ba\u00116h\u00f60\u0016\u00af]\u00fe\u00bd\u009c6\u0091]d:\u0016\u00bf\u00fe/\u00cc\u001eDJF\u00ef\u0004\u00d5\u00a3\\1\u00102L=\u00fb0\u00ac_\u00b9S\u008b\u00f3A\tsX2\u0018E7lX\u00dfxR@\u0010\u00ea\u0094>4\u00b3I\u00ea\u009d\u0013f7\u00e6\u00f7\u00b0x \u0007\u00b7\u000f\u009c&\u00e2w\u00a5\u008f\u00f38\u00c3]\u0082?`\u00dc\n\u00c19{y\u00bd\b\u00eb\u00cemS\u0005\u00feDi\u0010\u00ac\u00edL\u00c0*\u00e2\u0082\u00eaSJ\u00a4p*\u00cc\u00833 \u00db d(\u00fc\u00ff$\u00daW\u0016d\u00c7\u0011\u0080o\u008e\u00c4\u00f4\u00afI\u00a1ho=\u0013/B\u00df\u00aa\u00da\u009f\u00d8\u0010\u0085\u00ab\u00c9u\u0007\u00a9Xg\"\u00a2\u00caE!\u00a5\u008c\u0012 \u00a3\u00eb\u00bfTK\t#\u0095^\u00fc\u00ce\u001c,\u00d1\u00c5\u00c6\u00a7\u0003\u00a6J\u00f8\u00ff\u000b\u00ff\u0087<j\u0012\u00e5\u0015?\u0015 !W\u001fC\u0094\u00d5_\u00af\u00e1wj\u00f7\u00d8\u008c\u00c3y\t-pJF\u00ef\u00da{\u00a6\u008d\u00ee\u001b\u0087\u00a1\u0097\u00e3(gy\u000fit\u00cdi\u00de\u00d5z\u00b3\u00cc\u00e8\u00ccu\u0096\u0088\u00dax\u009f.C[\u00ec\u00b1\u00aa\u00b3?\u009a\u009e\u00b1\u00c2\u00f0\u0001\u00b9_@\u00cd\u009b'\u0018t\u001d.a\u009f@\u0093\u00bf\u0004\"\u001b\u009e*>F\u0011-SFU1K\n\u008d";
                        var17_7 = 847;
                        var14_8 = 40;
                        var13_9 = -1;
lbl22:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_9;
                            v4 = var15_6.substring(v3, v3 + var14_8);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl27:
                        // 1 sources

                        while (true) {
                            var18_4[var16_5++] = PvpResourcesWidget.zkmDecodeUtf8(var19_10).intern();
                            if ((var13_9 += var14_8) < var17_7) {
                                var14_8 = var15_6.charAt(var13_9);
                                ** continue;
                            }
                            var15_6 = "\u0001\u00d9\u00def\u00ab\u0086:\u0094\u00ca\u00bd\u00f7lq\u00a2\u0004U\u00c6\u0015/\u00b9W\u0015\u0000\u0017\u00d5\u000b\u0000K\u00f1\u00aa\u00dc\u0013 W\u009f/\u00ed=\u00b2\u00ab\u0091\u00f8\u00b6\u00b7\u00ca\u00f9\u0096\u00bb{\u00be\u00eb\u000et\u0087p\u00c4.\u009bj\u00b9\u00dd\u0080\u00eb/\u0092";
                            var17_7 = 65;
                            var14_8 = 32;
                            var13_9 = -1;
lbl36:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_9;
                                v4 = var15_6.substring(v6, v6 + var14_8);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl41:
                        // 1 sources

                        while (true) {
                            var18_4[var16_5++] = PvpResourcesWidget.zkmDecodeUtf8(var19_10).intern();
                            if ((var13_9 += var14_8) < var17_7) {
                                var14_8 = var15_6.charAt(var13_9);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_10 = var11_2.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl53:
                        // 1 sources

                        ** continue;
                    }
                }
                PvpResourcesWidget.b = var18_4;
                PvpResourcesWidget.f = new String[34];
                PvpResourcesWidget.l = new HashMap<K, V>(13);
                var0_11 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_12 = 1; var1_12 < 8; ++var1_12) {
                    v9 = v9;
                    v9[var1_12] = (byte)(var20 << var1_12 * 8 >>> 56);
                }
                var0_11.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_13 = new long[8];
                var3_14 = 0;
                var4_15 = "\u00e2\u0015\u00d0\u00a1\u00cf\u00b2Sk\u000e\u0013\u00843\u00f0\u007f\u0015)\u00c7\u00fb;VB\u0012\u00d0\u0081%\u00ae!D;.\u00cf*\u00bd\u00e3\u00cc\u009f\u00cf\u00a8\u0012,\u00ed\u007f\u0083+\u007fpj\u00b5";
                var5_16 = 48;
                var2_17 = 0;
                while (true) {
                    var7_18 = var4_15.substring(var2_17, var2_17 += 8).getBytes("ISO-8859-1");
                    v10 = var6_13;
                    v11 = var3_14++;
                    v12 = ((long)var7_18[0] & 255L) << 56 | ((long)var7_18[1] & 255L) << 48 | ((long)var7_18[2] & 255L) << 40 | ((long)var7_18[3] & 255L) << 32 | ((long)var7_18[4] & 255L) << 24 | ((long)var7_18[5] & 255L) << 16 | ((long)var7_18[6] & 255L) << 8 | (long)var7_18[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl80:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_17 < var5_16) ** continue;
                    var4_15 = "\u00bfk\u00d1Q\u0085%\u00a7\u00ec\u0010\u001d\u00ba\u00b7\u00a5\u0088\u009e\r";
                    var5_16 = 16;
                    var2_17 = 0;
                    while (true) {
                        var7_18 = var4_15.substring(var2_17, var2_17 += 8).getBytes("ISO-8859-1");
                        v10 = var6_13;
                        v11 = var3_14++;
                        v12 = ((long)var7_18[0] & 255L) << 56 | ((long)var7_18[1] & 255L) << 48 | ((long)var7_18[2] & 255L) << 40 | ((long)var7_18[3] & 255L) << 32 | ((long)var7_18[4] & 255L) << 24 | ((long)var7_18[5] & 255L) << 16 | ((long)var7_18[6] & 255L) << 8 | (long)var7_18[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl93:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_17 < var5_16) ** continue;
                    break block21;
                    break;
                }
            }
            var8_19 = v12;
            var10_20 = var0_11.doFinal(new byte[]{(byte)(var8_19 >>> 56), (byte)(var8_19 >>> 48), (byte)(var8_19 >>> 40), (byte)(var8_19 >>> 32), (byte)(var8_19 >>> 24), (byte)(var8_19 >>> 16), (byte)(var8_19 >>> 8), (byte)var8_19});
            v14 = ((long)var10_20[0] & 255L) << 56 | ((long)var10_20[1] & 255L) << 48 | ((long)var10_20[2] & 255L) << 40 | ((long)var10_20[3] & 255L) << 32 | ((long)var10_20[4] & 255L) << 24 | ((long)var10_20[5] & 255L) << 16 | ((long)var10_20[6] & 255L) << 8 | (long)var10_20[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl106:
                // 1 sources

                ** continue;
            }
        }
        PvpResourcesWidget.i = var6_13;
        PvpResourcesWidget.k = new Integer[8];
        var26_21 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(PvpResourcesWidget.class, "totems", "getTotems()Z", 0)), Reflection.property1(new PropertyReference1Impl(PvpResourcesWidget.class, "crystals", "getCrystals()Z", 0)), Reflection.property1(new PropertyReference1Impl(PvpResourcesWidget.class, "gapples", "getGapples()Z", 0)), Reflection.property1(new PropertyReference1Impl(PvpResourcesWidget.class, "crapples", "getCrapples()Z", 0)), Reflection.property1(new PropertyReference1Impl(PvpResourcesWidget.class, "fireworks", "getFireworks()Z", 0)), Reflection.property1(new PropertyReference1Impl(PvpResourcesWidget.class, "xp", "getXp()Z", 0)), Reflection.property1(new PropertyReference1Impl(PvpResourcesWidget.class, "obby", "getObby()Z", 0))};
        PvpResourcesWidget.delegatedProperties = var26_21;
        PvpResourcesWidget.N = new PvpResourcesWidget(var24_1);
        PvpResourcesWidget.totems$delegate = SettingFactory.booleanSetting$default(PvpResourcesWidget.N, "totems", true, null, null, 12, null);
        PvpResourcesWidget.crystals$delegate = SettingFactory.booleanSetting$default(PvpResourcesWidget.N, "crystals", true, null, null, 12, null);
        PvpResourcesWidget.gapples$delegate = SettingFactory.booleanSetting$default(PvpResourcesWidget.N, "gapples", true, null, null, 12, null);
        PvpResourcesWidget.crapples$delegate = SettingFactory.booleanSetting$default(PvpResourcesWidget.N, "crapples", false, null, null, 12, null);
        PvpResourcesWidget.fireworks$delegate = SettingFactory.booleanSetting$default(PvpResourcesWidget.N, "fireworks", true, null, null, 12, null);
        PvpResourcesWidget.xp$delegate = SettingFactory.booleanSetting$default(PvpResourcesWidget.N, "xp", true, null, null, 12, null);
        PvpResourcesWidget.obby = SettingFactory.booleanSetting$default(PvpResourcesWidget.N, "obby", false, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6CD3;
        if (f[n2] == null) {
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
                throw new RuntimeException("su/catlean/pl", exception);
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
            PvpResourcesWidget.f[n2] = PvpResourcesWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PvpResourcesWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x64EA;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])PvpResourcesWidget.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    PvpResourcesWidget.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/pl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PvpResourcesWidget.k[n2] = n3;
        }
        return k[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PvpResourcesWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PvpResourcesWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PvpResourcesWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

