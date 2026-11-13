/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Quaternionfc
 */
package su.catlean.ui.hud;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Items;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionfc;
import su.catlean.module.basic.ElytraRecast;
import su.catlean.module.esp.Tracers;
import su.catlean.module.rage.ElytraPlus;
import su.catlean.module.rage.ElytraPlusMode;
import su.catlean.module.rage.elytra.InfiniteElytra;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;

public final class FlightWidget
extends HudElement {
    @NotNull
    public static final FlightWidget x;
    @NotNull
    private static final ArrayDeque arrayDeque;
    @NotNull
    private static final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private FlightWidget(long l) {
        long l2 = (l = a ^ l) ^ 0xA95229B7CF1L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "flight-widget", (char)n2);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2) {
        block16: {
            block15: {
                block13: {
                    block14: {
                        block12: {
                            block10: {
                                block11: {
                                    v0 = var1_1;
                                    var6_3 = v0 ^ 137466198657546L;
                                    var24_4 = v0 ^ 128377113661390L;
                                    v1 = v0 ^ 100947255473036L;
                                    var26_5 = (int)(v1 >>> 32);
                                    var27_6 = (int)(v1 << 32 >>> 40);
                                    var28_7 = (int)(v1 << 56 >>> 56);
                                    var29_8 = v0 ^ 23238624456863L;
                                    v2 = ArrayListEntry.u();
                                    Intrinsics.checkNotNullParameter(var3_2, "context");
                                    var37_9 = v2;
                                    v3 = Globals.getPlayer().isGliding();
                                    if (var37_9) break block10;
                                    if (!v3) break block11;
                                    v3 = ElytraRecast.INSTANCE.getEnabled();
                                    if (var37_9) break block10;
                                    if (!v3) ** GOTO lbl-1000
                                }
                                v3 = Globals.getMc().currentScreen instanceof ChatScreen;
                            }
                            if (var37_9) break block12;
                            if (v3) ** GOTO lbl-1000
                            v3 = Globals.getMc().currentScreen instanceof HudEditorScreen;
                        }
                        if (v3) lbl-1000:
                        // 3 sources

                        {
                            v4 = 1.0f;
                        } else {
                            v4 = 0.0f;
                        }
                        FlightWidget.animation.animate(v4);
                        v5 = FlightWidget.animation.getValue();
                        v6 = 0.0f;
                        if (!var37_9) {
                            if (v5 <= v6) {
                                return;
                            }
                            var3_2.getMatrices().pushMatrix();
                            var3_2.getMatrices().translate(this.getX() + 60.0f, this.getY() + 28.5f);
                            var3_2.getMatrices().scale(Math.clamp(FlightWidget.animation.getValue(), 0.0f, 2.0f), Math.clamp(FlightWidget.animation.getValue(), 0.0f, 2.0f));
                            var3_2.getMatrices().translate(-(this.getX() + 60.0f), -(this.getY() + 28.5f));
                            v7 = var3_2.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v7, "pose(...)");
                            this.drawBackground(v7, this.getX(), this.getY(), 135.0f, var6_3, 57.0f, 1.0f, false);
                            v8 = var3_2.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v8, "pose(...)");
                            this.drawBackground(v8, this.getX() + 2.0f, this.getY() + 2.0f, 53.0f, var6_3, 53.0f, 0.6f, false);
                            v9 = var3_2.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(v9, "pose(...)");
                            this.drawBackground(v9, this.getX() + 57.0f, this.getY() + 2.0f, 8.0f, var6_3, 53.0f, 0.6f, false);
                            v5 = this.getX();
                            v6 = 27.5f;
                        }
                        var38_10 = v5 + v6;
                        var39_11 = this.getY() + 27.5f;
                        var40_12 = Globals.getPlayer().getYaw() + 90.0f;
                        var41_13 = new MatrixStack();
                        var41_13.push();
                        var41_13.translate(this.getX() + 60.0f, this.getY() + 28.5f, 0.0f);
                        var41_13.scale(Math.clamp(FlightWidget.animation.getValue(), 0.0f, 2.0f), Math.clamp(FlightWidget.animation.getValue(), 0.0f, 2.0f), 0.0f);
                        var41_13.translate(-(this.getX() + 60.0f), -(this.getY() + 28.5f), 0.0f);
                        var41_13.translate(var38_10 + 1.0f, var39_11 + 1.0f, 0.0f);
                        var41_13.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(var40_12));
                        var41_13.translate(-var38_10 - 1.0f, -var39_11 - 1.0f, 0.0f);
                        Tracers.INSTANCE.drawTracer(var26_5, var27_6, var41_13, var38_10 + 1.0f, (byte)var28_7, var39_11, 15.0f, Theme.INSTANCE.getGradientColor(0));
                        var41_13.pop();
                        var42_14 = Globals.getPlayer().getPitch() / 90.0f * 26.5f + 26.5f;
                        v10 = Items.FIREWORK_ROCKET;
                        Intrinsics.checkNotNullExpressionValue(v10, "FIREWORK_ROCKET");
                        var33_15 = null;
                        var35_16 = false;
                        var36_17 = v10;
                        var43_18 = InventoryUtil.findItemSlot$default(InventoryUtil.INSTANCE, var36_17, var35_16, var33_15);
                        v11 = Items.ELYTRA;
                        Intrinsics.checkNotNullExpressionValue(v11, "ELYTRA");
                        var33_15 = null;
                        var35_16 = false;
                        var36_17 = v11;
                        var44_19 = InventoryUtil.findItemSlot$default(InventoryUtil.INSTANCE, var36_17, var35_16, var33_15);
                        var45_20 = 0;
                        var46_21 = 0.0f;
                        var47_22 = (float)((int)(MovementUtil.INSTANCE.getHorizontalSpeed() * 72.0f * 10.0f)) / 10.0f;
                        var48_23 = (float)((int)((Globals.getPlayer().getY() - Globals.getPlayer().lastY) * 72.0 * 10.0)) / 10.0f;
                        v12 = Globals.getPlayer().getEquippedStack(EquipmentSlot.CHEST);
                        Intrinsics.checkNotNullExpressionValue(v12, "getItemBySlot(...)");
                        var49_24 = v12;
                        v13 = var49_24.isOf(Items.ELYTRA);
                        if (var37_9) break block13;
                        if (v13 == 0) break block14;
                        v14 = ((Collection)FlightWidget.arrayDeque).isEmpty();
                        if (!var37_9) {
                            v14 = v14 == false;
                        }
                        if (!v14) break block14;
                        v15 = Enchantments.UNBREAKING;
                        Intrinsics.checkNotNullExpressionValue(v15, "UNBREAKING");
                        var50_25 = EnchantUtil.getEnchantmentLevel(var49_24, v15);
                        var45_20 = var49_24.getMaxDamage() - var49_24.getDamage();
                        var46_21 = (float)((double)var45_20 * CollectionsKt.averageOfFloat(FlightWidget.arrayDeque));
                        var46_21 = (float)((int)(var46_21 * 10.0f)) / 10.0f;
                        v13 = var50_25;
                        if (var37_9) break block13;
                        if (v13 > 0) {
                            var46_21 *= (float)(var50_25 - 1);
                        }
                    }
                    this.drawString(var3_2, "X", this.getX() + 25.5f, var24_4, this.getY() + 1.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "z", this.getX() + 4.0f, var24_4, this.getY() + 22.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "x", this.getX() + 25.5f, var24_4, this.getY() + 44.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "Z", this.getX() + 49.0f, var24_4, this.getY() + 22.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "Fireworks: " + var43_18, this.getX() + 67.0f, var24_4, this.getY(), Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "Durability: " + var45_20, this.getX() + 67.0f, var24_4, this.getY() + 9.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "Res (km): " + var46_21, this.getX() + 67.0f, var24_4, this.getY() + 18.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "Elytras: " + var44_19, this.getX() + 67.0f, var24_4, this.getY() + 27.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "XZ Speed: " + var47_22, this.getX() + 67.0f, var24_4, this.getY() + 36.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(var3_2, "Y Speed: " + var48_23, this.getX() + 67.0f, var24_4, this.getY() + 45.0f, Theme.INSTANCE.getTextColor());
                    v16 = ElytraPlus.INSTANCE;
                    if (var37_9) break block15;
                    v13 = (int)v16.getEnabled();
                }
                if (v13 == 0) break block16;
                v16 = ElytraPlus.INSTANCE;
            }
            if (v16.getMode() == ElytraPlusMode.INFINITE) {
                var50_26 = InfiniteElytra.INSTANCE.getTargetPitch();
                var51_28 = var50_26 / 90.0f * 26.5f + 26.5f;
                v17 = var3_2.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v17, "pose(...)");
                v18 = this.getX() + 57.0f;
                v19 = this.getY() + var51_28;
                var52_29 = new Color[1];
                Intrinsics.checkNotNullExpressionValue(Color.RED, "RED");
                RenderUtil.INSTANCE.drawGradient(v17, v18, v19, 8.0f, 1.0f, var52_29, var29_8);
            }
        }
        v20 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v20, "pose(...)");
        var50_27 = new Color[]{new Color(0x44FFFFFF, true)};
        RenderUtil.INSTANCE.drawGradient(v20, this.getX() + 57.0f, this.getY() + var42_14, 8.0f, 1.0f, var50_27, var29_8);
        var3_2.getMatrices().popMatrix();
        this.setSize(120.0f, 55.0f);
    }

    @Override
    public void tick() {
        boolean bl = ArrayListEntry.u();
        int n = arrayDeque.size();
        if (!bl) {
            if (n >= 400) {
                arrayDeque.removeFirst();
            }
            n = arrayDeque.add(Float.valueOf(MovementUtil.INSTANCE.getHorizontalSpeed() * 20.0f)) ? 1 : 0;
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
                            FlightWidget.a = ZkmKeys.a(1705647231680096880L, -2094026497548188876L, MethodHandles.lookup().lookupClass()).a(178387568216919L);
                            var27 = FlightWidget.a ^ 129394002539005L;
                            var31_1 = var27 ^ 41319142819719L;
                            FlightWidget.f = new HashMap<K, V>(13);
                            var18_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var27 >>> 56);
                            for (var19_3 = 1; var19_3 < 8; ++var19_3) {
                                v2 = v2;
                                v2[var19_3] = (byte)(var27 << var19_3 * 8 >>> 56);
                            }
                            var18_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_4 = new String[15];
                            var23_5 = 0;
                            var22_6 = "I\u00a4I\u00dd\u001d\u00c8\u00fe\u0011\u00b0\u00f3\u00bfR\u00f9\u0080\u00e65\u008fI\u00d2|\u00d9.[\u0080\u0090\u00e0;Jj\u00cd\u00df\u0017\u0010\u0094J\u00b1\u00ea)Id\u00a6\u00bc\u00f2\u009e\u0082\u00c7\u00dc\u00f7\u00d7(\u00b6{\u00bc\u001d\u0016\u009a_\u00ed6\u00da\u009ag\u0012\u00b6\u001a\u0013R\u00f0\u00f2[T0\u00ecV\u009c\u00d7C\u00a8\u00f6\u0005\u00d7w\u00ae\u001d\u0014\u00caW\u007f\u0004\u00ca\u0018\u00d1\u00a5;oL]j\u0018\u00c3\u00c8\u00ce\u00de\u008bJ:\fN\u00f1\r\u00fc\u00d7F\u001a\u000b\u0018\u008b\r\u00b2\u0094\u00f3/P\u00e7\u00f3\u00df%bqAX\u0089\u000b\u000bB\u00cb\t\u00f3\u00b3\u00f2\u0018\u0019\u008d\u00e4\u00c3jI7\u00e8!\u0019\u00f21F\u001d\u00149U5]\u00d2\u001c\u00aa\u00ba\u00fe\u0010\u001f\u0018\\\u00adX\u00d0m~\u0014B\f`v\u00d6T\u00d0 r:V+\n>\u00ac5k\u0014\u0095x\u00f2\u0015\u009dF\u0087\u0006\u0010\u0080[\u00bc(q(\u00ba\u00b0\u00d6x\u00ba\u0089\u00af\u0010\u00a6\u0003dP\u00ce|\u0083\u009fM\u00e2-d\\\u00ef\u0083\u00be \u00d6\u000b\u0013%\u0016_\u00d8\u00d0\u00c6\u008a%\u00f9\u0096\u00c4||\u00070\u00ddV\u00f4\u00ce\u0096\u00b6\t{|\u0010cv\u00c8\u0092 \u000b\u00fdRsL\u0018\u00ea\u0087\u0091v\u00c2\u00c4\u00d1\u00b6K\u00ba\u00b01\u00141*p[\u00a0E\u0011.\t\u00ba\u00e7\u0089\u00ee\u00183\u0013\u00a2\u00b0\u0012\u00b1\u00dd\u00cf6\u00caGp\u00d7\u00fa\u00d3\u00cf9\u001eEE\u00cd\u00ce[\u0081 \u001e\u008b\u00ba,\u00cf\u00d5k\u00bd\u00ed|\u00ce|\u0001\u00c5pv\u0085A\u00e9\u0092\u0017\u00fd\u00e5\u00d2+\u007f\u00b8\u00d0o\u00ec\u00ae\u00c5";
                            var24_7 = 356;
                            var21_8 = 32;
                            var20_9 = -1;
lbl22:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_9;
                                v4 = var22_6.substring(v3, v3 + var21_8);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl27:
                            // 1 sources

                            while (true) {
                                var25_4[var23_5++] = FlightWidget.zkmDecodeUtf8(var26_10).intern();
                                if ((var20_9 += var21_8) < var24_7) {
                                    var21_8 = var22_6.charAt(var20_9);
                                    ** continue;
                                }
                                var22_6 = "\u00f0\u00ef_\u00f3\u008f\u00ba%xB\u00cdB\u00a4lc\u00f1\u00fe\u0001\u0014N\u0089/\u00c2i\u00a2\u001d\u009f\u00a7G\u00ed\u00fbt\u008c\u0018\u0099\u0019\u00a2\rQ\u00aa\u00fcCG\u00e4~\u0006\u00cf\u00d5o\u009c \u0000\u00e6\u00b4\u00cc5\u00b3\u00a9";
                                var24_7 = 57;
                                var21_8 = 32;
                                var20_9 = -1;
lbl36:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_9;
                                    v4 = var22_6.substring(v6, v6 + var21_8);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl41:
                            // 1 sources

                            while (true) {
                                var25_4[var23_5++] = FlightWidget.zkmDecodeUtf8(var26_10).intern();
                                if ((var20_9 += var21_8) < var24_7) {
                                    var21_8 = var22_6.charAt(var20_9);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_10 = var18_2.doFinal(v4.getBytes("ISO-8859-1"));
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
                    FlightWidget.b = var25_4;
                    FlightWidget.c = new String[15];
                    FlightWidget.i = new HashMap<K, V>(13);
                    var7_11 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var27 >>> 56);
                    for (var8_12 = 1; var8_12 < 8; ++var8_12) {
                        v9 = v9;
                        v9[var8_12] = (byte)(var27 << var8_12 * 8 >>> 56);
                    }
                    var7_11.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var13_13 = new long[5];
                    var10_14 = 0;
                    var11_15 = "\u00ec\u00dc\u00f4$Npd\u00ec\u00adO{Z\u00ec8+\u00ff\u00ac[\u001bYgf\u0004U";
                    var12_16 = 24;
                    var9_17 = 0;
                    while (true) {
                        var14_18 = var11_15.substring(var9_17, var9_17 += 8).getBytes("ISO-8859-1");
                        v10 = var13_13;
                        v11 = var10_14++;
                        v12 = ((long)var14_18[0] & 255L) << 56 | ((long)var14_18[1] & 255L) << 48 | ((long)var14_18[2] & 255L) << 40 | ((long)var14_18[3] & 255L) << 32 | ((long)var14_18[4] & 255L) << 24 | ((long)var14_18[5] & 255L) << 16 | ((long)var14_18[6] & 255L) << 8 | (long)var14_18[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl80:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_17 < var12_16) ** continue;
                        var11_15 = "k\u00d6\u00a3}\u0088\u00d8\u00ac1\u00f3\u0095\u00aa\u00c6\u00ec\u0014h\u00ad";
                        var12_16 = 16;
                        var9_17 = 0;
                        while (true) {
                            var14_18 = var11_15.substring(var9_17, var9_17 += 8).getBytes("ISO-8859-1");
                            v10 = var13_13;
                            v11 = var10_14++;
                            v12 = ((long)var14_18[0] & 255L) << 56 | ((long)var14_18[1] & 255L) << 48 | ((long)var14_18[2] & 255L) << 40 | ((long)var14_18[3] & 255L) << 32 | ((long)var14_18[4] & 255L) << 24 | ((long)var14_18[5] & 255L) << 16 | ((long)var14_18[6] & 255L) << 8 | (long)var14_18[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl93:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_17 < var12_16) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_19 = v12;
                var17_20 = var7_11.doFinal(new byte[]{(byte)(var15_19 >>> 56), (byte)(var15_19 >>> 48), (byte)(var15_19 >>> 40), (byte)(var15_19 >>> 32), (byte)(var15_19 >>> 24), (byte)(var15_19 >>> 16), (byte)(var15_19 >>> 8), (byte)var15_19});
                v14 = ((long)var17_20[0] & 255L) << 56 | ((long)var17_20[1] & 255L) << 48 | ((long)var17_20[2] & 255L) << 40 | ((long)var17_20[3] & 255L) << 32 | ((long)var17_20[4] & 255L) << 24 | ((long)var17_20[5] & 255L) << 16 | ((long)var17_20[6] & 255L) << 8 | (long)var17_20[7] & 255L;
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
            FlightWidget.g = var13_13;
            FlightWidget.h = new Integer[5];
            var2_21 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var27 >>> 56);
            for (var3_22 = 1; var3_22 < 8; ++var3_22) {
                v17 = v17;
                v17[var3_22] = (byte)(var27 << var3_22 * 8 >>> 56);
            }
            break block24;
lbl120:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_21.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_24 = var2_21.doFinal(new byte[]{71, -35, -109, -22, -68, 51, 36, 31});
        ** while (true)
        var0_23 = ((long)var6_24[0] & 255L) << 56 | ((long)var6_24[1] & 255L) << 48 | ((long)var6_24[2] & 255L) << 40 | ((long)var6_24[3] & 255L) << 32 | ((long)var6_24[4] & 255L) << 24 | ((long)var6_24[5] & 255L) << 16 | ((long)var6_24[6] & 255L) << 8 | (long)var6_24[7] & 255L;
        FlightWidget.x = new FlightWidget(var31_1);
        FlightWidget.arrayDeque = new ArrayDeque<E>(400);
        FlightWidget.animation = new Animation(Easing.OUT_BACK, var0_23);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4EC8;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/gz", exception);
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
            FlightWidget.c[n2] = FlightWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FlightWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/gz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5AAA;
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
                throw new RuntimeException("su/catlean/gz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FlightWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FlightWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/gz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FlightWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FlightWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

