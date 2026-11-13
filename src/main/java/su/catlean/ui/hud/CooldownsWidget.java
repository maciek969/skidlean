/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import su.catlean.mixins.accessors.CooldownInstanceAccessor;
import su.catlean.mixins.accessors.ItemCooldownsAccessor;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.CooldownEntry;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class CooldownsWidget
extends HudElement {
    @NotNull
    public static final CooldownsWidget N;
    @NotNull
    private static final List y;
    @NotNull
    private static final Animation animation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private CooldownsWidget(int n, char c, char c2) {
        long l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x6B542B13145L;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        super((char)n2, "cooldowns-widget", (char)n3);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2) {
        block51: {
            block45: {
                v0 = var1_1;
                var20_3 = v0 ^ 130512158954640L;
                v1 = v0 ^ 104104530525409L;
                var22_4 = (int)(v1 >>> 48);
                var23_5 = (int)(v1 << 16 >>> 32);
                var24_6 = (int)(v1 << 48 >>> 48);
                var27_7 = v0 ^ 128377113661390L;
                var31_8 = v0 ^ 20173347401993L;
                Intrinsics.checkNotNullParameter(var3_2, "context");
                v2 = Globals.getPlayer().getItemCooldownManager();
                Intrinsics.checkNotNull(v2, "null cannot be cast to non-null type su.catlean.mixins.accessors.ItemCooldownsAccessor");
                v3 = ((ItemCooldownsAccessor)v2).getCooldowns();
                Intrinsics.checkNotNullExpressionValue(v3, "getCooldowns(...)");
                for (Map.Entry<Identifier, ?> var41_10 : v3.entrySet()) {
                    block44: {
                        block43: {
                            block50: {
                                block49: {
                                    block48: {
                                        Intrinsics.checkNotNullExpressionValue(Registries.ITEM.get(var41_10.getKey()), "getValue(...)");
                                        var43_14 = CooldownsWidget.y;
                                        break block48;
                                        catch (Throwable v4) {
                                            throw v4;
                                        }
                                    }
                                    if (!(var43_14 instanceof Collection)) break block43;
                                    break block49;
                                    catch (Throwable v5) {
                                        throw v5;
                                    }
                                }
                                break block50;
                                catch (Throwable v6) {
                                    throw v6;
                                }
                            }
                            if (!((Collection)var43_14).isEmpty()) break block43;
                            v8 = true;
                            break block44;
                        }
                        var45_16 = var43_14.iterator();
                        while (var45_16.hasNext()) {
                            var46_17 /* !! */  = var45_16.next();
                            var47_18 = (CooldownEntry)var46_17 /* !! */ ;
                            if (!Intrinsics.areEqual(var47_18.getInstance(), var42_12)) continue;
                            v8 = false;
                            break block44;
                        }
                        v8 = true;
                    }
                    if (!v8) continue;
                    v9 = var41_10.getValue();
                    Intrinsics.checkNotNull(v9, "null cannot be cast to non-null type su.catlean.mixins.accessors.CooldownInstanceAccessor");
                    CooldownsWidget.y.add(new CooldownEntry((Item)var42_12, ((CooldownInstanceAccessor)v9).getEndTime(), null, false, null));
                }
                var40_9 = CooldownsWidget.y;
                var42_12 = var40_9.iterator();
                while (var42_12.hasNext()) {
                    var43_14 = var42_12.next();
                    var44_19 = (CooldownEntry)var43_14;
                    v10 = Globals.getPlayer().getItemCooldownManager().isCoolingDown(var44_19.getInstance().getDefaultStack());
                    if (v10) continue;
                    var44_19.setRemoving(true);
                }
                CooldownsWidget.y.removeIf((Predicate<Object>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, invokePredicate(kotlin.jvm.functions.Function1 java.lang.Object ), (Ljava/lang/Object;)Z)((Function1<CooldownEntry, Boolean>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, updateEntry(su.catlean.ui.hud.CooldownEntry ), (Lsu/catlean/ui/hud/CooldownEntry;)Ljava/lang/Boolean;)()));
                var40_9 = "Cooldowns";
                if (!(this.getX() > (float)this.getScreenWidth() / 2.0f)) break block45;
                v15 = true;
                break block51;
            }
            v15 = false;
        }
        var41_11 = v15;
        v16 = CooldownsWidget.animation;
        if (CooldownsWidget.y.isEmpty()) {
        }
        ** GOTO lbl-1000
        v18 = Globals.getMc().currentScreen instanceof ChatScreen;
        if (!v18) {
        }
        ** GOTO lbl-1000
        v21 = Globals.getMc().currentScreen instanceof HudEditorScreen;
        if (!v21) {
            v23 = 0.0f;
        } else lbl-1000:
        // 3 sources

        {
            v23 = 1.0f;
        }
        v16.animate(v23);
        this.drawLabel(var3_2, (String)var40_9, CooldownsWidget.animation.getValue(), var41_11, var20_3);
        var42_13 = 17.0f;
        var43_15 = Fonts.getSF_M().getWidth(var31_8, (String)var40_9) + 24.0f;
        for (Object var45_16 : CooldownsWidget.y) {
            v24 = var45_16.getEndTick();
            v25 = Globals.getPlayer().getItemCooldownManager();
            Intrinsics.checkNotNull(v25, "null cannot be cast to non-null type su.catlean.mixins.accessors.ItemCooldownsAccessor");
            var46_17 /* !! */  = this.formatTicks(v24 - ((ItemCooldownsAccessor)v25).getTickCount());
            Intrinsics.checkNotNullExpressionValue(var45_16.getInstance().getTranslationKey(), "getDescriptionId(...)");
            var37_20 = new Object[]{};
            var47_18 = I18n.INSTANCE.translate(var38_21, var37_20);
            var48_22 = Fonts.getSF_M().getWidth(var31_8, (String)var47_18);
            var49_23 = Fonts.getSF_M().getWidth(var31_8, (String)var46_17 /* !! */ );
            cfr_temp_0 = var48_22 + var49_23 + 27.0f - var43_15;
            v26 = cfr_temp_0 == 0.0f ? '\u0000' : (cfr_temp_0 > 0.0f ? '\u0001' : '\uffffffff');
            if (v26 <= 0) continue;
            var43_15 = var48_22 + var49_23 + 27.0f;
        }
        for (Object var45_16 : CooldownsWidget.y) {
            block47: {
                block52: {
                    block46: {
                        v29 = var45_16.getEndTick();
                        v30 = Globals.getPlayer().getItemCooldownManager();
                        Intrinsics.checkNotNull(v30, "null cannot be cast to non-null type su.catlean.mixins.accessors.ItemCooldownsAccessor");
                        var46_17 /* !! */  = this.formatTicks(v29 - ((ItemCooldownsAccessor)v30).getTickCount());
                        Intrinsics.checkNotNullExpressionValue(var45_16.getInstance().getTranslationKey(), "getDescriptionId(...)");
                        var37_20 = new Object[]{};
                        var47_18 = I18n.INSTANCE.translate(var38_21, var37_20);
                        var48_22 = Fonts.getSF_M().getWidth(var31_8, (String)var47_18);
                        var49_23 = Fonts.getSF_M().getWidth(var31_8, (String)var46_17 /* !! */ );
                        if (!var41_11) break block46;
                        v32 = this.getX() - (1.0f - var45_16.getAnimation().getValue()) * -10.0f - (var48_22 + var49_23 + 27.0f - var43_15);
                        break block52;
                    }
                    v32 = this.getX() - (1.0f - var45_16.getAnimation().getValue()) * 10.0f;
                }
                var50_24 = v32;
                v33 = this;
                v34 = var3_2.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v34, "pose(...)");
                HudElement.drawBackground$default(v33, v34, var50_24, (short)var22_4, var23_5, this.getY() + var42_13, 13.0f, 13.0f, (short)var24_6, var45_16.getAnimation().getValue(), false, 64, null);
                v35 = this;
                v36 = var3_2.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v36, "pose(...)");
                HudElement.drawBackground$default(v35, v36, var50_24 + 14.0f, (short)var22_4, var23_5, this.getY() + var42_13, var48_22 + 6.0f, 13.0f, (short)var24_6, var45_16.getAnimation().getValue(), false, 64, null);
                v37 = this;
                v38 = var3_2.getMatrices();
                Intrinsics.checkNotNullExpressionValue(v38, "pose(...)");
                HudElement.drawBackground$default(v37, v38, var50_24 + (var48_22 + 21.0f) * var45_16.getAnimation().getValue(), (short)var22_4, var23_5, this.getY() + var42_13, var49_23 + 6.0f, 13.0f, (short)var24_6, var45_16.getAnimation().getValue(), false, 64, null);
                var52_25 = var45_16.getAnimation().getValue();
                this.drawString(var3_2, (String)var47_18, var50_24 + 17.0f, var27_7, this.getY() + var42_13 + 1.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), var52_25));
                var56_26 = var45_16.getAnimation().getValue();
                this.drawString(var3_2, (String)var46_17 /* !! */ , var50_24 + (var48_22 + 24.0f) * var45_16.getAnimation().getValue(), var27_7, this.getY() + var42_13 + 1.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getTextColor(), var56_26));
                var3_2.getMatrices().pushMatrix();
                var3_2.getMatrices().translate(var50_24 + 2.0f, this.getY() + var42_13 + 2.0f);
                var3_2.getMatrices().scale(0.55f);
                if (!(var45_16.getAnimation().getValue() > 0.8f)) break block47;
                var3_2.drawItem(var45_16.getInstance().getDefaultStack(), 0, 0);
            }
            var3_2.getMatrices().popMatrix();
            var42_13 += 14.0f * var45_16.getAnimation().getValue();
        }
        this.setSize(var43_15, var42_13 - 2.0f);
    }

    @NotNull
    public final String formatTicks(int n) {
        int n2 = n / 1200;
        String string = "%02d";
        Object[] objectArray = new Object[]{n % 1200 / 20};
        String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue(string2, "format(...)");
        return n2 + ":" + string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static final boolean updateEntry(CooldownEntry cooldownEntry) {
        boolean bl = ArrayListEntry.u();
        Intrinsics.checkNotNullParameter(cooldownEntry, "it");
        boolean bl2 = bl;
        cooldownEntry.getAnimation().animate(cooldownEntry.isRemoving() ? 0.0f : 1.0f);
        char n = cooldownEntry.isRemoving();
        if (!bl2) {
            if (n == '\u0000') return '\u0000' != '\u0000';
            float f = cooldownEntry.getAnimation().getValue() - 0.0f;
            n = f == 0.0f ? (char)'\u0000' : (f < 0.0f ? (char)'\uffffffff' : '\u0001');
        }
        if (bl2) return n != '\u0000';
        if (n > '\u0000') return '\u0000' != '\u0000';
        return '\u0001' != '\u0000';
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
                            CooldownsWidget.a = ZkmKeys.a(3667193777786092288L, -7098458865151111616L, MethodHandles.lookup().lookupClass()).a(128086613349322L);
                            var27 = CooldownsWidget.a ^ 69653142136126L;
                            v0 = var27 ^ 109142597115632L;
                            var31_1 = (int)(v0 >>> 32);
                            var32_2 = (int)(v0 << 32 >>> 48);
                            var33_3 = (int)(v0 << 48 >>> 48);
                            CooldownsWidget.f = new HashMap<K, V>(13);
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
                            var22_8 = "\u00eeoR\u00dc;\u0018\u00d4\u00a5\u00fev\u001c\u00ffr\u00a6\u00b4\u0010wh\u00d9\u00fe\u00fex\u001ekr\u00d5\u0086d\u0080(\u00f3\u00e6\u0010\u00f8\u00b1\u00d3\u00c1b\u00a4\u00df\u0017\u00bc\u00ff\u0019\u00a3\u00b9Fo\u00a2\u00a0\u00bev\u00ad\u0080\u00b4\u00bb\u00b6\u0083\u00cc|\u00fbh\u00d8\u00a4\u0004\u00ec\u009c\fe\u0013\u00f6\u00a1\u00df+\u00e4\u00f4\u00fe\u00ff?\u0014\u009dpH\u000b,\u00dc\u008e\u0096\u008bb\u00db\u00cd\u00bf4\"\u00b3_\u00b3\u0082\u00b8\u00e7LO\u00e8\u0084A\u00ddor\u008c\u001a=\u00c7\u00f9o2\u00f4\u00137\u00a0\u0093\u00ac\u00f6\u0095\u0088\n\u00fd\u001d\u00ec\u00c7\u0087\u001e\u00caG*\u0092\u00af\u00c9\u0086\u000b\u00b4\u00c2\u00bb\u00f4\u0089\u001d\u00a7\u001d\u00ef)\u009f\u00da\u0085\u00c8\u00bct\u00e8BJ\u00a9\\}'<\u00f2\u0016\u00c9\u00ce\u00d2\u0086}\u00bf\u00df\u00e0AO\u00bc\u001f\u00cb\u0002\u00cd\u00d2{\u009b\"\u00c3\u0087\u0001\u00c70\u00c5\u0018\u00c7\u00df\u008b\u00c91\u00d6z\u009a\u00ed\u00c7\u00cf]S\u0083=R\u008e\u00bf E\u00e3\u001b9\u009cw\u00d6f\u000e\u0092 y\u00e0\u00e8\u00cd\u0018\r\u009a\u0096\u0083(\u00ad\u0084\u009d\u00d4\\\u0081\u00f6\u0098L\u00a3\u00d5 \u00dd\u00ce\u009f\u00e7\u0092\u00f2\u0001\u00ddX\u0006$F\u00e8\u000fJM\u0098\u00ba\u0002\u0014\u0019\u00b2LH\u00d0$\u008b7N\u00ab\u00e6\u00c8(.\u00cb\u00d3r\u00d6r\u0098\u0091b\u0097\u00cf\u009f\u00b7\u0080\u0017\u00e0\u0098\u009c\u00e5\u00edD\u0099\u00c9\u008c\u0012qNOZ\u00d9\\\u00be\u000b3\u00af\u00aa\u00bf\u0093\u009c\u001b\u00107CyNm\u00b6VY\u007f\u009c\u00be\u00d9YrA\u00b1(\u00c06\u00eaS\u00a9\u00ecN\u00ae\u009b\f\u0094\u00e4\u001b#\u00ad\u00b3\u00a6\u000f\u00fdU\u009e\u00a1(\u00a7;?\u00ad\u0082gj\u0012\u0080\u007fg\u0001gO\u0019J\u009d\u0010\u00d8\u007f\u008b\u000f\u00f3T)\u0090\u00f1\u00a2\"\u00e94\u0098\u001a\u00f0\u001846g\u00bc\u00e3\u0098\u00f9LY9-\u0092\u0090\u00bc\u00a0d\u0085g\u00d9\u00ac\u0011\"\u00bd\t(\u00f3\"z!b \u0096\u0087\u001b(\u00d8\u00efT8\u00f3\u0084\u0019e\u00cc\u00aa\u0012\n\u009c\u00a7\u00c6\f\u00cbg\u00f0\u00c0\u00b9\u00bc\u00fa6\u00c2V\u00d7\u00e7]\u00a7 \u00eb5\u00c1b8Q`\u00a0\u00d3\u0086\u008eU\u0015\u00d99\u00f1b\u00de\u0010\u00d6\u0098\u00fc\u00f1\u00ac\u00e5\u0015\u00fa/\rv\u00bb\u009c\u0088Y=\u00cb\u00f7$`D\u00da\u000fh\u00c2\u008fK\u00ac\u00ead\u000e\u00c7\fD\u00a1\u0013\u0090\u0089\u00c9\u00e1\u009fdn\u00de\u00ba[\r?\u00bc\u0091\u0003w\u00c5]\u00e1\u001bX\u00f2\u00f6\u00d931\u00b7g4=\u00eb\u00e1c^\u00cc\u0092\u00c9\u0003gP\u001d\u00de[\u00c5\u00d3i\u00c8\u0093\b\u009f\u00f0\u00e3\u00b6.\u00e5\u0002\u00df\u00d0S\u00edZ\u00fcX\u00ed\u000f\u00dao\u00f5\u001f\b\u00b7\u00ea\u00d1\u008d\u00b5\u0014 '\u00ad-\u0005\u00c2szU\u00b8I\u00e4\u0011\u00c6C%p\u0006i6\u001db()1-\u0001\u001e\u001c \u00d5\r\u009eE\u00ac\u00c5\u001ed";
                            var24_9 = 628;
                            var21_10 = 32;
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
                                var25_6[var23_7++] = CooldownsWidget.zkmDecodeUtf8(var26_12).intern();
                                if ((var20_11 += var21_10) < var24_9) {
                                    var21_10 = var22_8.charAt(var20_11);
                                    ** continue;
                                }
                                var22_8 = "\u007f\u0001|\u00a8\u001d\u00afb\u00a74hU\u00a7P\u00c3 \u00feT\u00a7X\u00e0\u00ef\u0005\u008ew\u0088\u00c2/PL\u00f4\u00cb\u0084\r\u00bc(\u00a0K\u00aefJ\u0006<\u001d!\u00ea\u00d4\u007f\u0012\u009c\u0001\u00da\u00ef\u0000,\u00fc\fu2\u0012\u009d\u00be\u00fcbd1\u001f\u0001$\u007f\u00be\u008f\u00cb\u00df\u00cb\u008fW\u00bd\u0004Mn|\b\u0094\u00fdT\u0005\u00d5\u00ba?\u00f0T\u008b\u00d8\u00b6\u00e1T%\u009a\u00e58\\~\u0084)\u00aa'\u00a4\u00bb0N(\u0092\u000f\u00a1b\u00e2\u00e1\u00a2\u00fe\u0015\u009a\u00ec\u00ffY\u00a3\u00efjs\u00a6\u00c5\u008f\u00a0\u00a1qqA\u00c3\u00e9C\u00b7\u00b8t\u001b'\u0084\u0010\u00ca\u00b2\u00c8jnh^\u00fb^\u00e4\u00faK\u00d2\u00ab\u0010";
                                var24_9 = 161;
                                var21_10 = 24;
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
                                var25_6[var23_7++] = CooldownsWidget.zkmDecodeUtf8(var26_12).intern();
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
                    CooldownsWidget.b = var25_6;
                    CooldownsWidget.c = new String[15];
                    CooldownsWidget.i = new HashMap<K, V>(13);
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
                    var11_17 = "^T\u008a\u009b\u00b5\u00da\u00ca\u00ad\u0087U7\u00e4n\u0097\u0005\u00b9xR\u009f7\u0089\u0003\u00ea\u00ff+,Vr\u00d4\u00b8\u00de\u00c3\"\u00e6\u008f;\u007f\u001f\u00a8\u00ff\u00f2\u00c1\u00aadZ\u00c5\u0014\u00e8";
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
                        var11_17 = ",*\u00c1\u00d0x\u009d\u00bb\u0090\u001b\"\u00ec\u000e$\u00061o";
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
            CooldownsWidget.g = var13_15;
            CooldownsWidget.h = new Integer[8];
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
        var6_26 = var2_23.doFinal(new byte[]{57, -10, 73, -113, -73, -56, -77, -40});
        ** while (true)
        var0_25 = ((long)var6_26[0] & 255L) << 56 | ((long)var6_26[1] & 255L) << 48 | ((long)var6_26[2] & 255L) << 40 | ((long)var6_26[3] & 255L) << 32 | ((long)var6_26[4] & 255L) << 24 | ((long)var6_26[5] & 255L) << 16 | ((long)var6_26[6] & 255L) << 8 | (long)var6_26[7] & 255L;
        CooldownsWidget.N = new CooldownsWidget(var31_1, (char)var32_2, (char)var33_3);
        CooldownsWidget.y = new ArrayList<E>();
        CooldownsWidget.animation = new Animation(Easing.OUT_BACK, var0_25);
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5E4F;
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
                throw new RuntimeException("su/catlean/ga", exception);
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
            CooldownsWidget.c[n2] = CooldownsWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CooldownsWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ga" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6EEA;
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
                throw new RuntimeException("su/catlean/ga", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CooldownsWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CooldownsWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ga" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CooldownsWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CooldownsWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

