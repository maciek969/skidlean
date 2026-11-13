/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.module.esp.NameTags;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.ui.hud.PotionEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class PotionsWidget
extends HudElement {
    @NotNull
    public static final PotionsWidget H;
    @NotNull
    private static final List g;
    @NotNull
    private static final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map k;

    private PotionsWidget(int n, long l) {
        long l2 = ((long)n << 32 | l << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x9A190DA024FL;
        int n2 = (int)(l3 >>> 48);
        int n3 = (int)(l3 << 48 >>> 48);
        super((char)n2, "potions-widget", (char)n3);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        float f;
        String string;
        Object[] objectArray;
        Object object;
        Iterator iterator2;
        Object object2;
        long l2 = l;
        long l3 = l2 ^ 0x76B33ADF9490L;
        long l4 = l2 ^ 0x5EAEB98FBCE1L;
        int n = (int)(l4 >>> 48);
        int n2 = (int)(l4 << 16 >>> 32);
        int n3 = (int)(l4 << 48 >>> 48);
        long l5 = l2 ^ 0x74C22041CFCEL;
        long l6 = l2 ^ 0x1258F934B109L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Object object3 = Globals.getPlayer().getStatusEffects().iterator();
        while (object3.hasNext()) {
            StatusEffectInstance statusEffectInstance;
            boolean bl;
            block11: {
                Intrinsics.checkNotNullExpressionValue(object3.next(), "next(...)");
                object2 = g;
                if (object2 instanceof Collection && ((Collection)object2).isEmpty()) {
                    bl = true;
                } else {
                    iterator2 = object2.iterator();
                    while (iterator2.hasNext()) {
                        Object object4 = iterator2.next();
                        object = (PotionEntry)object4;
                        if (!Intrinsics.areEqual(((PotionEntry)object).getInstance(), statusEffectInstance)) continue;
                        bl = false;
                        break block11;
                    }
                    bl = true;
                }
            }
            if (!bl) continue;
            g.add(new PotionEntry(statusEffectInstance, null, false, null));
        }
        object3 = g;
        object2 = object3.iterator();
        while (object2.hasNext()) {
            boolean bl;
            block12: {
                Object e = object2.next();
                iterator2 = (PotionEntry)e;
                Collection<StatusEffectInstance> collection = Globals.getPlayer().getStatusEffects();
                Intrinsics.checkNotNullExpressionValue(collection, "getActiveEffects(...)");
                object = collection;
                if (object instanceof Collection && ((Collection)object).isEmpty()) {
                    bl = true;
                } else {
                    Iterator iterator3 = object.iterator();
                    while (iterator3.hasNext()) {
                        Object t = iterator3.next();
                        StatusEffectInstance statusEffectInstance = (StatusEffectInstance)t;
                        if (!Intrinsics.areEqual(((PotionEntry)((Object)iterator2)).getInstance(), statusEffectInstance)) continue;
                        bl = false;
                        break block12;
                    }
                    bl = true;
                }
            }
            if (!bl) continue;
            ((PotionEntry)((Object)iterator2)).setRemoving(true);
        }
        g.removeIf(arg_0 -> PotionsWidget.invokePredicate(PotionsWidget::updateEntry, arg_0));
        object3 = "Potions";
        boolean bl = this.getX() > (float)this.getScreenWidth() / 2.0f;
        animation.animate(g.isEmpty() && !(Globals.getMc().currentScreen instanceof ChatScreen) && !(Globals.getMc().currentScreen instanceof HudEditorScreen) ? 0.0f : 1.0f);
        this.drawLabel(drawContext, (String)object3, animation.getValue(), bl, l3);
        float f2 = 17.0f;
        float f3 = Fonts.getSF_M().getWidth(l6, (String)object3) + 24.0f;
        for (Object object4 : g) {
            Intrinsics.checkNotNullExpressionValue(((PotionEntry)object4).getInstance().getTranslationKey(), "getDescriptionId(...)");
            objectArray = new Object[]{};
            object = I18n.INSTANCE.translate(string, objectArray) + (String)(((PotionEntry)object4).getInstance().getAmplifier() > 0 ? " " + (((PotionEntry)object4).getInstance().getAmplifier() + 1) : "");
            f = Fonts.getSF_M().getWidth(l6, (String)object);
            long l7 = l6;
            float f4 = Fonts.getSF_M().getWidth(l7, NameTags.INSTANCE.getPotionText(((PotionEntry)object4).getInstance()));
            if (!(f + f4 + 27.0f > f3)) continue;
            f3 = f + f4 + 27.0f;
        }
        for (Object object4 : g) {
            Intrinsics.checkNotNullExpressionValue(((PotionEntry)object4).getInstance().getTranslationKey(), "getDescriptionId(...)");
            objectArray = new Object[]{};
            object = I18n.INSTANCE.translate(string, objectArray) + (String)(((PotionEntry)object4).getInstance().getAmplifier() > 0 ? " " + (((PotionEntry)object4).getInstance().getAmplifier() + 1) : "");
            f = Fonts.getSF_M().getWidth(l6, (String)object);
            float f5 = Fonts.getSF_M().getWidth(l6, NameTags.INSTANCE.getPotionText(((PotionEntry)object4).getInstance()));
            boolean bl2 = ((PotionEntry)object4).getInstance().getEffectType().comp_349().getCategory() == StatusEffectCategory.HARMFUL;
            float f6 = bl ? this.getX() - (1.0f - ((PotionEntry)object4).getAnimation().getValue()) * -10.0f - (f + f5 + 27.0f - f3) : this.getX() - (1.0f - ((PotionEntry)object4).getAnimation().getValue()) * 10.0f;
            float f7 = 1.0f;
            if (((PotionEntry)object4).getInstance().isDurationBelow(200)) {
                f7 = 1.0f - (float)Math.sin(((PotionEntry)object4).getInstance().getDuration()) * 0.5f;
            }
            HudElement hudElement = this;
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            HudElement.drawBackground$default(hudElement, matrix3x2fStack, f6, (short)n, n2, this.getY() + f2, 13.0f, 13.0f, (short)n3, ((PotionEntry)object4).getAnimation().getValue(), false, 64, null);
            HudElement hudElement2 = this;
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            HudElement.drawBackground$default(hudElement2, matrix3x2fStack2, f6 + 14.0f, (short)n, n2, this.getY() + f2, f + 6.0f, 13.0f, (short)n3, ((PotionEntry)object4).getAnimation().getValue(), false, 64, null);
            HudElement hudElement3 = this;
            Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
            HudElement.drawBackground$default(hudElement3, matrix3x2fStack3, f6 + (f + 21.0f) * ((PotionEntry)object4).getAnimation().getValue(), (short)n, n2, this.getY() + f2, f5 + 6.0f, 13.0f, (short)n3, ((PotionEntry)object4).getAnimation().getValue(), false, 64, null);
            float f8 = this.getY() + f2 + 1.0f;
            Color color = bl2 ? Color.RED : Theme.INSTANCE.getTextColor();
            Intrinsics.checkNotNull(color);
            float f9 = ((PotionEntry)object4).getAnimation().getValue() * f7;
            this.drawString(drawContext, (String)object, f6 + 17.0f, l5, f8, RenderUtil.INSTANCE.withAlpha(color, f9));
            float f10 = ((PotionEntry)object4).getAnimation().getValue() * f7;
            this.drawString(drawContext, NameTags.INSTANCE.getPotionText(((PotionEntry)object4).getInstance()), f6 + (f + 24.0f) * ((PotionEntry)object4).getAnimation().getValue(), l5, this.getY() + f2 + 1.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), f10));
            drawContext.getMatrices().pushMatrix();
            drawContext.getMatrices().translate(f6 + 2.0f, this.getY() + f2 + 2.0f);
            drawContext.getMatrices().scale(0.55f);
            Identifier identifier = InGameHud.getEffectTexture(((PotionEntry)object4).getInstance().getEffectType());
            Color color2 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
            float f11 = ((PotionEntry)object4).getAnimation().getValue() * f7;
            drawContext.drawGuiTexture(RenderPipelines.GUI_TEXTURED, identifier, 0, 0, 18, 18, RenderUtil.INSTANCE.withAlpha(color2, f11).getRGB());
            drawContext.getMatrices().popMatrix();
            f2 += 14.0f * ((PotionEntry)object4).getAnimation().getValue();
        }
        this.setSize(f3, f2 - 2.0f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean updateEntry(PotionEntry potionEntry) {
        boolean bl = ArrayListEntry.u();
        Intrinsics.checkNotNullParameter(potionEntry, "it");
        boolean bl2 = bl;
        potionEntry.getAnimation().animate(potionEntry.isRemoving() ? 0.0f : 1.0f);
        int n = potionEntry.isRemoving();
        if (!bl2) {
            if (n == 0) return 0 != 0;
            float f = potionEntry.getAnimation().getValue() - 0.0f;
            n = f == 0.0f ? 0 : (f < 0.0f ? -1 : 1);
        }
        if (bl2) return n != 0;
        if (n > 0) return 0 != 0;
        return 1 != 0;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
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
                            PotionsWidget.a = ZkmKeys.a(7972252224232676418L, -5853125047384858089L, MethodHandles.lookup().lookupClass()).a(114821091128628L);
                            var27 = PotionsWidget.a ^ 3815587722065L;
                            v0 = var27 ^ 88469613018005L;
                            var29_1 = (int)(v0 >>> 32);
                            var30_2 = v0 << 32 >>> 32;
                            PotionsWidget.f = new HashMap<K, V>(13);
                            var18_3 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v1 = SecretKeyFactory.getInstance("DES");
                            v2 = new byte[8];
                            v3 = v2;
                            v2[0] = (byte)(var27 >>> 56);
                            for (var19_4 = 1; var19_4 < 8; ++var19_4) {
                                v3 = v3;
                                v3[var19_4] = (byte)(var27 << var19_4 * 8 >>> 56);
                            }
                            var18_3.init(2, (Key)v1.generateSecret(new DESKeySpec(v3)), new IvParameterSpec(new byte[8]));
                            var25_5 = new String[11];
                            var23_6 = 0;
                            var22_7 = "\u00ba\u00d4\u00e6\u008e\u00b6L\u00edJ\u00ee\u00cd\u00b8\u00cf\u00d6\u00fe\u00b1Q\u00cd\u00ce\u00a3;\u0086Q\u00c4\u00a4(\fJ6\u00e6R\u00b1\u00d4\u00fd\u00df3;\u00d8\u0001\nqi\u00a7\u001f\\cA\u0016\u00bb\u00b7H\u00cf<P\u0001u\u00f0\u0000n\t\u00a4\u00e0\u00df\u00d4\f\u00b3\u0010}\u000b\u00ea[\u00fe\u001a\u001b\u0097\u00c0\u00de\u00e6W2\u007f\u0082\u00d6 \u00a07\u008a\u00e8N\u00e1PF\r\u00d2~\u00cb!\u009a\u00f8/-\u00d6x\u00d4\u00da=\u00b1\u001b\u00a0\u00b7\u0091XE\u00f97\u0001(\u009d\u00fcT\u008a\u00afDR\u00e4\tn\u00bd\u00f9p\u00f0\u008b\u00ff\u00e4\u001e\u0000\u00e6\u00c9\u00dc\u00ea\u0092\u00c5\u00c2_\u00abcr\u00ce\u00f2d\u00c8\u00cf\u0015D|\u00038\u0010\u008a\u00c4J\u00fa\u0093l\u0015r\u00d9\u00ffY\n\u00ea\n\u00bd\u00160\u0011s[\u00d42.\u008f*\u00cfd-'\u0088\u00b0\u001a\u00ae\u00cf\u00ad1\"\u00f4jI\u00f5\u00974r\u00f2j\u00d5C\u0013\u00c8KY6\u00bf\u0010\u009b$\u00f8m\u00c0=\u00c4(\u0007\u0096\u0010'\u00ac+\u00fc\u00b0\u00f5t\u0018hy\u0010\u00f2\u00eek\u0011d\u0018\u00c4\u0090a\u0011\u00b7\u0006\u00f0p;_\u0005\u00d4\u0085\u0097\u00c9\u0094\u00ed\u0084\u0015\u009aD\u00a0c\u0015";
                            var24_8 = 264;
                            var21_9 = 24;
                            var20_10 = -1;
lbl25:
                            // 2 sources

                            while (true) {
                                v4 = ++var20_10;
                                v5 = var22_7.substring(v4, v4 + var21_9);
                                v6 = -1;
                                break block20;
                                break;
                            }
lbl30:
                            // 1 sources

                            while (true) {
                                var25_5[var23_6++] = PotionsWidget.zkmDecodeUtf8(var26_11).intern();
                                if ((var20_10 += var21_9) < var24_8) {
                                    var21_9 = var22_7.charAt(var20_10);
                                    ** continue;
                                }
                                var22_7 = "6X*.\u00ac\u0088>\u0019\u0012\u00cc\u0001\u008c\u00f8v\u0018\u00ef\u0018D\u0004\u00fa\u00ccEe\u00b1\u0003\u009f\u00df\u009a\u0018c\u000f#e\u0017\u001e^\u00d9\u00b1\r\u0082\u009d";
                                var24_8 = 41;
                                var21_9 = 16;
                                var20_10 = -1;
lbl39:
                                // 2 sources

                                while (true) {
                                    v7 = ++var20_10;
                                    v5 = var22_7.substring(v7, v7 + var21_9);
                                    v6 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl44:
                            // 1 sources

                            while (true) {
                                var25_5[var23_6++] = PotionsWidget.zkmDecodeUtf8(var26_11).intern();
                                if ((var20_10 += var21_9) < var24_8) {
                                    var21_9 = var22_7.charAt(var20_10);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_11 = var18_3.doFinal(v5.getBytes("ISO-8859-1"));
                        switch (v6) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl56:
                            // 1 sources

                            ** continue;
                        }
                    }
                    PotionsWidget.b = var25_5;
                    PotionsWidget.c = new String[11];
                    PotionsWidget.k = new HashMap<K, V>(13);
                    var7_12 = Cipher.getInstance("DES/CBC/NoPadding");
                    v8 = SecretKeyFactory.getInstance("DES");
                    v9 = new byte[8];
                    v10 = v9;
                    v9[0] = (byte)(var27 >>> 56);
                    for (var8_13 = 1; var8_13 < 8; ++var8_13) {
                        v10 = v10;
                        v10[var8_13] = (byte)(var27 << var8_13 * 8 >>> 56);
                    }
                    var7_12.init(2, (Key)v8.generateSecret(new DESKeySpec(v10)), new IvParameterSpec(new byte[8]));
                    var13_14 = new long[8];
                    var10_15 = 0;
                    var11_16 = "\u0019\u00dc\u0085~\u00be\u009a\u001f\u0012/\u00c4\u00f6c\u009c\u00e8\f\u00d27f3\u00f6!\u00ec\u00d64\u00a1\n\u00ba\u00d1\u0085\u001d\u00c1\u00fd\u0017f\u008d\u0007\u00e3\u00e4\u00a1vm\u0002\u00de\u00e6\u0091KL\u00e1";
                    var12_17 = 48;
                    var9_18 = 0;
                    while (true) {
                        var14_19 = var11_16.substring(var9_18, var9_18 += 8).getBytes("ISO-8859-1");
                        v11 = var13_14;
                        v12 = var10_15++;
                        v13 = ((long)var14_19[0] & 255L) << 56 | ((long)var14_19[1] & 255L) << 48 | ((long)var14_19[2] & 255L) << 40 | ((long)var14_19[3] & 255L) << 32 | ((long)var14_19[4] & 255L) << 24 | ((long)var14_19[5] & 255L) << 16 | ((long)var14_19[6] & 255L) << 8 | (long)var14_19[7] & 255L;
                        v14 = -1;
                        break block22;
                        break;
                    }
lbl83:
                    // 1 sources

                    while (true) {
                        v11[v12] = v15;
                        if (var9_18 < var12_17) ** continue;
                        var11_16 = "\u001f\u0095\u00d1>\u00e6\u007f\u00c7\u001b\u0018\rq\u00d9\u00bb`\u00f2\u00b7";
                        var12_17 = 16;
                        var9_18 = 0;
                        while (true) {
                            var14_19 = var11_16.substring(var9_18, var9_18 += 8).getBytes("ISO-8859-1");
                            v11 = var13_14;
                            v12 = var10_15++;
                            v13 = ((long)var14_19[0] & 255L) << 56 | ((long)var14_19[1] & 255L) << 48 | ((long)var14_19[2] & 255L) << 40 | ((long)var14_19[3] & 255L) << 32 | ((long)var14_19[4] & 255L) << 24 | ((long)var14_19[5] & 255L) << 16 | ((long)var14_19[6] & 255L) << 8 | (long)var14_19[7] & 255L;
                            v14 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl96:
                    // 1 sources

                    while (true) {
                        v11[v12] = v15;
                        if (var9_18 < var12_17) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_20 = v13;
                var17_21 = var7_12.doFinal(new byte[]{(byte)(var15_20 >>> 56), (byte)(var15_20 >>> 48), (byte)(var15_20 >>> 40), (byte)(var15_20 >>> 32), (byte)(var15_20 >>> 24), (byte)(var15_20 >>> 16), (byte)(var15_20 >>> 8), (byte)var15_20});
                v15 = ((long)var17_21[0] & 255L) << 56 | ((long)var17_21[1] & 255L) << 48 | ((long)var17_21[2] & 255L) << 40 | ((long)var17_21[3] & 255L) << 32 | ((long)var17_21[4] & 255L) << 24 | ((long)var17_21[5] & 255L) << 16 | ((long)var17_21[6] & 255L) << 8 | (long)var17_21[7] & 255L;
                switch (v14) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl109:
                    // 1 sources

                    ** continue;
                }
            }
            PotionsWidget.h = var13_14;
            PotionsWidget.i = new Integer[8];
            var2_22 = Cipher.getInstance("DES/CBC/NoPadding");
            v16 = SecretKeyFactory.getInstance("DES");
            v17 = new byte[8];
            v18 = v17;
            v17[0] = (byte)(var27 >>> 56);
            for (var3_23 = 1; var3_23 < 8; ++var3_23) {
                v18 = v18;
                v18[var3_23] = (byte)(var27 << var3_23 * 8 >>> 56);
            }
            break block24;
lbl123:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_22.init(2, (Key)v16.generateSecret(new DESKeySpec(v18)), new IvParameterSpec(new byte[8]));
        var6_25 = var2_22.doFinal(new byte[]{91, 25, -91, 97, -91, 1, -30, 29});
        ** while (true)
        var0_24 = ((long)var6_25[0] & 255L) << 56 | ((long)var6_25[1] & 255L) << 48 | ((long)var6_25[2] & 255L) << 40 | ((long)var6_25[3] & 255L) << 32 | ((long)var6_25[4] & 255L) << 24 | ((long)var6_25[5] & 255L) << 16 | ((long)var6_25[6] & 255L) << 8 | (long)var6_25[7] & 255L;
        PotionsWidget.H = new PotionsWidget(var29_1, var30_2);
        PotionsWidget.g = new ArrayList<E>();
        PotionsWidget.animation = new Animation(Easing.OUT_BACK, var0_24);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3C87;
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
                throw new RuntimeException("su/catlean/ph", exception);
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
            PotionsWidget.c[n2] = PotionsWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PotionsWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ph" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x523A;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ph", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PotionsWidget.i[n2] = n3;
        }
        return i[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PotionsWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ph" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PotionsWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PotionsWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

