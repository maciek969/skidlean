/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.ui.hud;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.ui.hud.TargetEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class PlayerListWidget
extends HudElement {
    @NotNull
    public static final PlayerListWidget o;
    @NotNull
    private static final List S;
    @NotNull
    private static final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private PlayerListWidget(byte by, int n, int n2) {
        long l = ((long)by << 56 | (long)n << 32 >>> 8 | (long)n2 << 40 >>> 40) ^ a;
        long l2 = l ^ 0x190AF3FB6E2DL;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 48 >>> 48);
        super((char)n3, "player-list-widget", (char)n4);
    }

    @NotNull
    public final List j() {
        return S;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2) {
        block29: {
            block31: {
                block30: {
                    block28: {
                        block27: {
                            block25: {
                                block24: {
                                    v0 = var1_1;
                                    var12_3 = v0 ^ 22879798888836L;
                                    var21_4 = v0 ^ 130512158954640L;
                                    v1 = v0 ^ 104104530525409L;
                                    var23_5 = (int)(v1 >>> 48);
                                    var24_6 = (int)(v1 << 16 >>> 32);
                                    var25_7 = (int)(v1 << 48 >>> 48);
                                    var26_8 = v0 ^ 128377113661390L;
                                    var28_9 = v0 ^ 20173347401993L;
                                    Intrinsics.checkNotNullParameter(var3_2, "context");
                                    v2 = Globals.getWorld().getPlayers();
                                    Intrinsics.checkNotNullExpressionValue(v2, "players(...)");
                                    var36_10 = v2;
                                    var38_12 = var36_10;
                                    var39_14 = new ArrayList<E>();
                                    var41_15 /* !! */  = var38_12.iterator();
                                    var34_18 = ArrayListEntry.u();
                                    while (var41_15 /* !! */ .hasNext()) {
                                        v3 = var41_15 /* !! */ ;
                                        if (!var34_18) {
                                            var42_19 = v3.next();
                                            var43_21 = (AbstractClientPlayerEntity)var42_19;
                                            v4 = Intrinsics.areEqual(var43_21, Globals.getMc().player);
                                            if (!var34_18) {
                                                v4 = v4 == false;
                                            }
                                            if (!v4) continue;
                                            var39_14.add(var42_19);
                                            if (!var34_18) continue;
                                        }
                                        break block24;
                                    }
                                    v3 = var35_23 = ((List)var39_14).iterator();
                                }
                                while (var35_23.hasNext()) {
                                    block26: {
                                        var36_10 = (AbstractClientPlayerEntity)var35_23.next();
                                        var38_12 = PlayerListWidget.S;
                                        v5 = var38_12;
                                        if (var34_18) break block25;
                                        if (var34_18) ** GOTO lbl49
                                        if (!(v5 instanceof Collection)) ** GOTO lbl-1000
                                        v6 = (Collection)var38_12;
                                        if (!var34_18) {
                                            ** if (!v6.isEmpty()) goto lbl-1000
lbl-1000:
                                            // 1 sources

                                            {
                                                v7 = true;
                                                ** GOTO lbl58
                                            }
                                        }
                                        ** GOTO lbl49
lbl-1000:
                                        // 2 sources

                                        {
                                            v6 = var38_12;
lbl49:
                                            // 4 sources

                                            for (Iterator<T> var41_15 : v6) {
                                                var42_19 = (TargetEntry)var41_15 /* !! */ ;
                                                v8 = Intrinsics.areEqual(var42_19.getEntity(), var36_10);
                                                while (v8) {
                                                    v8 = false;
                                                    if (var34_18) continue;
                                                    break block26;
                                                }
                                            }
                                            v7 = true;
                                        }
                                    }
                                    v9 = var37_24 = v7;
                                    if (!var34_18) {
                                        if (!v9) continue;
                                        Intrinsics.checkNotNull(var36_10);
                                        v9 = PlayerListWidget.S.add(new TargetEntry((PlayerEntity)var36_10, null, false, null));
                                    }
                                    if (!var34_18) continue;
                                }
                                v5 = PlayerListWidget.S;
                            }
                            var35_23 = v5;
                            var37_25 = var35_23;
                            var38_12 = new ArrayList<E>();
                            var40_27 = var37_25.iterator();
                            while (var40_27.hasNext()) {
                                var41_15 /* !! */  = var40_27.next();
                                var42_19 = (TargetEntry)var41_15 /* !! */ ;
                                v10 = Globals.getWorld().getPlayers();
                                Intrinsics.checkNotNullExpressionValue(v10, "players(...)");
                                v11 = CollectionsKt.contains((Iterable)v10, var42_19.getEntity());
                                if (!var34_18) {
                                    if (!var34_18) {
                                        v12 = v11 == false;
                                    }
                                    if (!v12) continue;
                                    var38_12.add(var41_15 /* !! */ );
                                    if (!var34_18) continue;
                                }
                                break block27;
                            }
                            var35_23 = (List)var38_12;
                            v11 = false;
                        }
                        var37_25 = var35_23.iterator();
                        while (var37_25.hasNext()) {
                            var38_12 = var37_25.next();
                            var39_14 = (TargetEntry)var38_12;
                            var39_14.setRemoving(true);
                            if (!var34_18) {
                                if (!var34_18) continue;
                            }
                            break block28;
                        }
                        PlayerListWidget.S.removeIf((Predicate<Object>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, invokePredicate(kotlin.jvm.functions.Function1 java.lang.Object ), (Ljava/lang/Object;)Z)((Function1<TargetEntry, Boolean>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, updateEntry(su.catlean.ui.hud.TargetEntry ), (Lsu/catlean/ui/hud/TargetEntry;)Ljava/lang/Boolean;)()));
                    }
                    var35_23 = "Players";
                    cfr_temp_0 = this.getX() - (float)this.getScreenWidth() / 2.0f;
                    v13 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 > 0.0f ? 1 : -1);
                    if (!var34_18) {
                        v13 = v13 > 0 ? (float)true : (float)false;
                    }
                    var36_11 = v13;
                    v14 = PlayerListWidget.S.isEmpty();
                    if (var34_18) break block30;
                    if (!v14) ** GOTO lbl-1000
                    v14 = Globals.getMc().currentScreen instanceof ChatScreen;
                }
                if (var34_18) break block31;
                if (v14) ** GOTO lbl-1000
                v14 = Globals.getMc().currentScreen instanceof HudEditorScreen;
            }
            if (!v14) {
                v15 = 0.0f;
            } else lbl-1000:
            // 3 sources

            {
                v15 = 1.0f;
            }
            PlayerListWidget.animation.animate(v15);
            this.drawLabel(var3_2, (String)var35_23, PlayerListWidget.animation.getValue(), (boolean)var36_11, var21_4);
            var37_26 = 17.0f;
            var38_13 = Fonts.getSF_M().getWidth(var28_9, (String)var35_23) + 24.0f;
            var39_14 = PlayerListWidget.S.iterator();
            while (var39_14.hasNext()) {
                block32: {
                    var40_27 = (TargetEntry)var39_14.next();
                    var41_16 = Fonts.getSF_M().getWidth(var28_9, var40_27.getInfoText(var12_3));
                    v16 = var41_16 + 13.0f;
                    v17 = var38_13;
                    if (var34_18) break block32;
                    cfr_temp_1 = v16 - v17;
                    v18 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1);
                    if (var34_18) ** GOTO lbl143
                    if (v18 <= 0) continue;
                    v16 = var41_16;
                    v17 = 13.0f;
                }
                var38_13 = v16 + v17;
                if (!var34_18) continue;
            }
            var39_14 = PlayerListWidget.S.iterator();
            do {
                v18 = var39_14.hasNext();
lbl143:
                // 2 sources

                if (v18 == 0) break;
                var40_27 = (TargetEntry)var39_14.next();
                var41_17 = Fonts.getSF_M().getWidth(var28_9, var40_27.getInfoText(var12_3));
                if (var34_18) break block29;
                var42_20 = var36_11 != false ? this.getX() - (1.0f - var40_27.getAnimation().getValue()) * -10.0f - (var41_17 + 0.0f + 13.0f - var38_13) : this.getX() - (1.0f - var40_27.getAnimation().getValue()) * 10.0f;
                v19 = this;
                v20 = var3_2.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v20, "pose(...)");
                HudElement.drawBackground$default(v19, v20, var42_20, (short)var23_5, var24_6, this.getY() + var37_26, var41_17 + 21.0f, 13.0f, (short)var25_7, var40_27.getAnimation().getValue(), false, 64, null);
                var43_22 = var37_26;
                var45_28 = var40_27.getAnimation().getValue();
                this.drawString(var3_2, var40_27.getInfoText(var12_3), var42_20 + 3.0f, var26_8, this.getY() + var43_22 + 1.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), var45_28));
                v21 = var3_2.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v21, "pose(...)");
                this.queueShadow(v21, var42_20 + var41_17 + 6.0f, this.getY() + 2.0f + var43_22, var40_27.getAnimation().getValue() * 0.27f);
                var37_26 += 14.0f * var40_27.getAnimation().getValue();
            } while (!var34_18);
            this.setSize(var38_13, var37_26 - 2.0f);
        }
    }

    @Override
    public void renderOverlay(@NotNull DrawContext drawContext, long l) {
        float f;
        long l2 = l;
        long l3 = l2 ^ 0x69E27E76B76EL;
        int n = (int)(l3 >>> 32);
        int n2 = (int)(l3 << 32 >>> 48);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x56A19D2200D9L;
        long l5 = l2 ^ 0x50367A5FB854L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        String string = "Players";
        boolean bl = this.getX() > (float)this.getScreenWidth() / 2.0f;
        float f2 = 17.0f;
        float f3 = Fonts.getSF_M().getWidth(l5, string) + 24.0f;
        for (TargetEntry targetEntry : S) {
            long l6 = l5;
            f = Fonts.getSF_M().getWidth(l6, targetEntry.getInfoText(l4));
            if (!(f + 13.0f > f3)) continue;
            f3 = f + 13.0f;
        }
        for (TargetEntry targetEntry : S) {
            f = Fonts.getSF_M().getWidth(l5, targetEntry.getInfoText(l4));
            float f4 = bl ? this.getX() - (1.0f - targetEntry.getAnimation().getValue()) * -10.0f - (f + 0.0f + 13.0f - f3) : this.getX() - (1.0f - targetEntry.getAnimation().getValue()) * 10.0f;
            float f5 = f2;
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Matrix3x2f matrix3x2f = (Matrix3x2f)matrix3x2fStack;
            PlayerEntity playerEntity = targetEntry.getEntity();
            Intrinsics.checkNotNull(playerEntity, "null cannot be cast to non-null type net.minecraft.client.player.AbstractClientPlayer");
            Identifier identifier = ((AbstractClientPlayerEntity)playerEntity).getSkin().comp_1626().comp_3627();
            Intrinsics.checkNotNullExpressionValue(identifier, "texturePath(...)");
            float f6 = this.getY() + 2.5f + f5;
            Color color = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
            float f7 = targetEntry.getAnimation().getValue();
            RenderUtil.drawTexture$default(RenderUtil.INSTANCE, matrix3x2f, identifier, f4 + f + 10.0f, f6, 8.0f, 8.0f, RenderUtil.INSTANCE.withAlpha(color, f7), null, n, null, null, 0.125f, (char)n2, 0.125f, 0.25f, (short)n3, 0.25f, false, 17280, null);
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            Matrix3x2f matrix3x2f2 = (Matrix3x2f)matrix3x2fStack2;
            Identifier identifier2 = ((AbstractClientPlayerEntity)targetEntry.getEntity()).getSkin().comp_1626().comp_3627();
            Intrinsics.checkNotNullExpressionValue(identifier2, "texturePath(...)");
            float f8 = this.getY() + 1.5f + f5;
            Color color2 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
            float f9 = targetEntry.getAnimation().getValue();
            RenderUtil.drawTexture$default(RenderUtil.INSTANCE, matrix3x2f2, identifier2, f4 + f + 9.0f, f8, 10.0f, 10.0f, RenderUtil.INSTANCE.withAlpha(color2, f9), null, n, null, null, 0.625f, (char)n2, 0.125f, 0.75f, (short)n3, 0.25f, false, 17280, null);
            f2 += 14.0f * targetEntry.getAnimation().getValue();
        }
    }

    private static final boolean updateEntry(TargetEntry targetEntry) {
        Intrinsics.checkNotNullParameter(targetEntry, "it");
        targetEntry.getAnimation().animate(targetEntry.isRemoving() ? 0.0f : 1.0f);
        return targetEntry.isRemoving() && targetEntry.getAnimation().getValue() <= 0.0f;
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
                            PlayerListWidget.a = ZkmKeys.a(2408774187869706312L, -180406917401825280L, MethodHandles.lookup().lookupClass()).a(84371270245176L);
                            var27 = PlayerListWidget.a ^ 116067560293487L;
                            v0 = var27 ^ 46409194250441L;
                            var31_1 = (int)(v0 >>> 56);
                            var32_2 = (int)(v0 << 8 >>> 32);
                            var33_3 = (int)(v0 << 40 >>> 40);
                            PlayerListWidget.f = new HashMap<K, V>(13);
                            var18_4 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v1 = SecretKeyFactory.getInstance("DES");
                            v2 = new byte[8];
                            v3 = v2;
                            v2[0] = (byte)(var27 >>> 56);
                            for (var19_5 = 1; var19_5 < 8; ++var19_5) {
                                v3 = v3;
                                v3[var19_5] = (byte)(var27 << var19_5 * 8 >>> 56);
                            }
                            var18_4.init(2, (Key)v1.generateSecret(new DESKeySpec(v3)), new IvParameterSpec(new byte[8]));
                            var25_6 = new String[15];
                            var23_7 = 0;
                            var22_8 = "\u00c1\u00dcFKJ\u00b64\u00f0w\u00d3\u009f\u00a6l\u00e5)\u0002\u0010\u00ff\u00aa\u00d4\u0001\u0092\u00b7\u0018m#\u00b6\u00c2k\u0015\u00e6\u00bf\u00f2\u0010\u009d\u00ffP\u00b2\u00dc$\u0083\u00b4\u00fd\u00a2J\u00f5\u00f2F\u00b0+(\u00bd%\u00f7[\u00a74\u00def/n{\u00b4\u00e6\tm\u00b29p\u00d8{(\u00ef\u00e6\u00ee\u00eb\u00da-\u00e9f\u00ac\u00d5:\u0088@\u001d9\t\u00a7h;\u0010)\n\u00f5\u00e5\u00c9Y1\u0088\u00a7\u0086Tn\u00f9\u00b8\u0017U\u0010\u00e1f\u00ce~\u00baf\u009b\u00b4\u0081TQCA\u00ad'\u00f9\u00106\u0007w\u00db\u0011\u0010\u00b0\u00d6|\u0014\u00b0Fs#\u00e1\u00cb\u0018\u0017i\u00f5\u00aa\u000bd\u00c0\u00bc`]\u009b\u00f7U\u00e7\u00a2\\\t\u0000\u00b1L,~\u000f|\u0010D\u00b56\u00a8\fF\u00cf\b\u00de\u0090\u0081\u000e\u00e0N\u0091\u00a2 \t<Y\u00da\u00ceS[q\u00c0\u00a2\u00e5\u00fbhNy\u00c4\u007f\u00fd\u00c0\u0002\u008f\u00e1y\u00d3\u00d0\u00c0\u00f6@~\u00ca 6 \u00c6;\u00b4B\u00ca\u0015@q\u00a9w\u00f8\u00e5\u00c6\f{\u0093U\u00f2\u0081\u00ed\u0081-\u00d3\u00e6\u00cd\"\u0087<\u00a4\u0017$\u00c90\u00c2N\u0089\u00fcq\u00e7\u0096\u009eT\u0080W\u00b8\u00cf\u00c8\u00c4\u00a6\u00f9\u00e99\u00c8\u000e\u00a6\u0007\u00be\u0002 \u000e\u0085>\u0010\u00a7\u008ds\u00ed\\\u0080\u00df\u008f\u00b7\u00f9\u00caC\u00b1\u00ab\u00eeP\u0092y(?\u0014\u00de6#\u00dd\u00a4~\u0003\u00f0\r\u0002\u00fa\u0089\u0005\u008b\u00ba\u0082\u00c0\u008d\u001aH\u00f6\u00d2\u00a9F\u0087\u000f\u00b7\u00f1\u00ebO]\u0000Pn\u008f\u009d{N";
                            var24_9 = 340;
                            var21_10 = 16;
                            var20_11 = -1;
lbl26:
                            // 2 sources

                            while (true) {
                                v4 = ++var20_11;
                                v5 = var22_8.substring(v4, v4 + var21_10);
                                v6 = -1;
                                break block20;
                                break;
                            }
lbl31:
                            // 1 sources

                            while (true) {
                                var25_6[var23_7++] = PlayerListWidget.zkmDecodeUtf8(var26_12).intern();
                                if ((var20_11 += var21_10) < var24_9) {
                                    var21_10 = var22_8.charAt(var20_11);
                                    ** continue;
                                }
                                var22_8 = "E\u0087\u009a\r\u00ca\u00f4\u00b0#\u00ce\u0007\u008b!c\u00b4\u008c\u00d1\u0097\u00e7I\u00ca\u0097\u0083$\u00c8\u0087\u00c14c\u0083\u00c7\u0083\t2g\u0086m\u00f5\u001c \u0006\u00f0\u0091\u00a7\u00f3\u00e4|\u00ac\u0010K{\r4\u0019/\u00f4\u00d0\u0086\u00fa\u00a6\u000f\u00fa\u00a5t\u00aeK\u0014\u0083~\u0082<\u008b\u00b4\u00b5w0,\f\u0084\u0082\u00e0+\u0098}\u00a6\u00ed\u00d6v\u000f\u00afpl\u00c0\u00dd\u00b5$7\u00ac\u00d3\u00c0\u00e8\u00f0.\u00f1\u00b6\u0093\u00b7\u00d4\u009d\u00c5<8F\f\\\u000b\u000f&\u00b5\u008c\u00d7zo\u0003;g\u0096\u008a\u00b1\u00ee\u00b6O\u009e\u0088\u00c4\u00e3BK\u00a25\r\u00f57W8\u0018\u00ff\u0081\u001b\u00db\u00e2C\u0082-\u00ad\u00c5\u0018\u00ba4\u00f9}\u001d\u00bb\u008f\u00f6\u0084E|\u009a\u00a8";
                                var24_9 = 169;
                                var21_10 = 144;
                                var20_11 = -1;
lbl40:
                                // 2 sources

                                while (true) {
                                    v7 = ++var20_11;
                                    v5 = var22_8.substring(v7, v7 + var21_10);
                                    v6 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl45:
                            // 1 sources

                            while (true) {
                                var25_6[var23_7++] = PlayerListWidget.zkmDecodeUtf8(var26_12).intern();
                                if ((var20_11 += var21_10) < var24_9) {
                                    var21_10 = var22_8.charAt(var20_11);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_12 = var18_4.doFinal(v5.getBytes("ISO-8859-1"));
                        switch (v6) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl57:
                            // 1 sources

                            ** continue;
                        }
                    }
                    PlayerListWidget.b = var25_6;
                    PlayerListWidget.c = new String[15];
                    PlayerListWidget.i = new HashMap<K, V>(13);
                    var7_13 = Cipher.getInstance("DES/CBC/NoPadding");
                    v8 = SecretKeyFactory.getInstance("DES");
                    v9 = new byte[8];
                    v10 = v9;
                    v9[0] = (byte)(var27 >>> 56);
                    for (var8_14 = 1; var8_14 < 8; ++var8_14) {
                        v10 = v10;
                        v10[var8_14] = (byte)(var27 << var8_14 * 8 >>> 56);
                    }
                    var7_13.init(2, (Key)v8.generateSecret(new DESKeySpec(v10)), new IvParameterSpec(new byte[8]));
                    var13_15 = new long[8];
                    var10_16 = 0;
                    var11_17 = "\u00d9\u00de\u0014\u009c\u00cf\"[\u00ec\u001a\u00a7\u000b6GS\\:j\u00e7\u00ad\u00a6+\u00c23\u0013B\u00a3\u00bf\b\u0011\u00c9\u00b3\u009f=L\u00d6=G\u001e\u009dI]'4\u0002?:8\u00e0";
                    var12_18 = 48;
                    var9_19 = 0;
                    while (true) {
                        var14_20 = var11_17.substring(var9_19, var9_19 += 8).getBytes("ISO-8859-1");
                        v11 = var13_15;
                        v12 = var10_16++;
                        v13 = ((long)var14_20[0] & 255L) << 56 | ((long)var14_20[1] & 255L) << 48 | ((long)var14_20[2] & 255L) << 40 | ((long)var14_20[3] & 255L) << 32 | ((long)var14_20[4] & 255L) << 24 | ((long)var14_20[5] & 255L) << 16 | ((long)var14_20[6] & 255L) << 8 | (long)var14_20[7] & 255L;
                        v14 = -1;
                        break block22;
                        break;
                    }
lbl84:
                    // 1 sources

                    while (true) {
                        v11[v12] = v15;
                        if (var9_19 < var12_18) ** continue;
                        var11_17 = "\u00d1\u0084\u00c3\u00ef\u00dd\u00c6\u0090 j\u00a0EyDBj\u00b5";
                        var12_18 = 16;
                        var9_19 = 0;
                        while (true) {
                            var14_20 = var11_17.substring(var9_19, var9_19 += 8).getBytes("ISO-8859-1");
                            v11 = var13_15;
                            v12 = var10_16++;
                            v13 = ((long)var14_20[0] & 255L) << 56 | ((long)var14_20[1] & 255L) << 48 | ((long)var14_20[2] & 255L) << 40 | ((long)var14_20[3] & 255L) << 32 | ((long)var14_20[4] & 255L) << 24 | ((long)var14_20[5] & 255L) << 16 | ((long)var14_20[6] & 255L) << 8 | (long)var14_20[7] & 255L;
                            v14 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl97:
                    // 1 sources

                    while (true) {
                        v11[v12] = v15;
                        if (var9_19 < var12_18) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_21 = v13;
                var17_22 = var7_13.doFinal(new byte[]{(byte)(var15_21 >>> 56), (byte)(var15_21 >>> 48), (byte)(var15_21 >>> 40), (byte)(var15_21 >>> 32), (byte)(var15_21 >>> 24), (byte)(var15_21 >>> 16), (byte)(var15_21 >>> 8), (byte)var15_21});
                v15 = ((long)var17_22[0] & 255L) << 56 | ((long)var17_22[1] & 255L) << 48 | ((long)var17_22[2] & 255L) << 40 | ((long)var17_22[3] & 255L) << 32 | ((long)var17_22[4] & 255L) << 24 | ((long)var17_22[5] & 255L) << 16 | ((long)var17_22[6] & 255L) << 8 | (long)var17_22[7] & 255L;
                switch (v14) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl110:
                    // 1 sources

                    ** continue;
                }
            }
            PlayerListWidget.g = var13_15;
            PlayerListWidget.h = new Integer[8];
            var2_23 = Cipher.getInstance("DES/CBC/NoPadding");
            v16 = SecretKeyFactory.getInstance("DES");
            v17 = new byte[8];
            v18 = v17;
            v17[0] = (byte)(var27 >>> 56);
            for (var3_24 = 1; var3_24 < 8; ++var3_24) {
                v18 = v18;
                v18[var3_24] = (byte)(var27 << var3_24 * 8 >>> 56);
            }
            break block24;
lbl124:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_23.init(2, (Key)v16.generateSecret(new DESKeySpec(v18)), new IvParameterSpec(new byte[8]));
        var6_26 = var2_23.doFinal(new byte[]{-1, -33, -70, -54, -91, -91, 121, -115});
        ** while (true)
        var0_25 = ((long)var6_26[0] & 255L) << 56 | ((long)var6_26[1] & 255L) << 48 | ((long)var6_26[2] & 255L) << 40 | ((long)var6_26[3] & 255L) << 32 | ((long)var6_26[4] & 255L) << 24 | ((long)var6_26[5] & 255L) << 16 | ((long)var6_26[6] & 255L) << 8 | (long)var6_26[7] & 255L;
        PlayerListWidget.o = new PlayerListWidget((byte)var31_1, var32_2, var33_3);
        PlayerListWidget.S = new ArrayList<E>();
        PlayerListWidget.animation = new Animation(Easing.OUT_BACK, var0_25);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x327E;
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
                throw new RuntimeException("su/catlean/pb", exception);
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
            PlayerListWidget.c[n2] = PlayerListWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlayerListWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x14E0;
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
                throw new RuntimeException("su/catlean/pb", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PlayerListWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PlayerListWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlayerListWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PlayerListWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

