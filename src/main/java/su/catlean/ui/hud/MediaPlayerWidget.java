/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import dev.redstones.mediaplayerinfo.IMediaSession;
import dev.redstones.mediaplayerinfo.MediaInfo;
import dev.redstones.mediaplayerinfo.MediaPlayerInfo;
import java.awt.Color;
import java.awt.image.BufferedImage;
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
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.Gradient2D;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.TextureRenderer;
import su.catlean.render.TextureUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.Ids;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class MediaPlayerWidget
extends HudElement {
    @NotNull
    public static final MediaPlayerWidget S;
    @Nullable
    private static MediaInfo mediaInfo;
    @NotNull
    private static Identifier coverTexture;
    @NotNull
    private static Vec2f coverSize;
    private static boolean Y;
    @NotNull
    private static String U;
    @NotNull
    private static final Animation O;
    @NotNull
    private static final Animation visibilityAnimation;
    private static boolean J;
    private static int b;
    @NotNull
    private static Timer timer;
    private static final long a;
    private static final String[] c;
    private static final String[] f;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map k;

    private MediaPlayerWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x1C85D43679D2L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "media-player-widget", (char)n2);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2) {
        v0 = var1_1;
        var6_3 = v0 ^ 137466198657546L;
        var12_4 = v0 ^ 32333673308231L;
        var19_5 = v0 ^ 90317468167310L;
        v1 = v0 ^ 104104530525409L;
        var23_6 = (int)(v1 >>> 48);
        var24_7 = (int)(v1 << 16 >>> 32);
        var25_8 = (int)(v1 << 48 >>> 48);
        var26_9 = v0 ^ 37430498539930L;
        var30_10 = v0 ^ 20173347401993L;
        var34_11 = v0 ^ 4180373105917L;
        Intrinsics.checkNotNullParameter(var3_2, "context");
        MediaPlayerWidget.visibilityAnimation.animate(MediaPlayerWidget.J == false && Globals.getMc().currentScreen instanceof ChatScreen == false && Globals.getMc().currentScreen instanceof HudEditorScreen == false ? 0.0f : 1.0f);
        this.setSize(120.0f, 43.0f);
        var3_2.getMatrices().pushMatrix();
        var3_2.getMatrices().translate(this.getX() + 60.0f, this.getY() + 21.5f);
        var3_2.getMatrices().scale(((Number)RangesKt.coerceIn((Comparable)Float.valueOf(MediaPlayerWidget.visibilityAnimation.getValue()), RangesKt.rangeTo(0.0f, 2.0f))).floatValue(), ((Number)RangesKt.coerceIn((Comparable)Float.valueOf(MediaPlayerWidget.visibilityAnimation.getValue()), RangesKt.rangeTo(0.0f, 2.0f))).floatValue());
        var3_2.getMatrices().translate(-(this.getX() + 60.0f), -(this.getY() + 21.5f));
        if (MediaPlayerWidget.mediaInfo == null && (Globals.getMc().currentScreen instanceof HudEditorScreen || Globals.getMc().currentScreen instanceof ChatScreen)) {
            v2 = this;
            v3 = var3_2.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v3, "pose(...)");
            HudElement.drawBackground$default(v2, v3, this.getX(), (short)var23_6, var24_7, this.getY(), 120.0f, 43.0f, (short)var25_8, 0.0f, false, 32, null);
            Fonts.getSF_B2().drawStringCentered(var3_2, this.A(), this.getX() + 60.0f, this.getY() + 21.0f, Theme.INSTANCE.getTextColor(), var34_11);
        }
        if (MediaPlayerWidget.mediaInfo == null) {
            var3_2.getMatrices().popMatrix();
            return;
        }
        v4 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v4);
        v5 = v4.getPosition();
        v6 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v6);
        var43_12 = RangesKt.coerceIn(v5 / (float)v6.getDuration(), 0.0f, 1.0f);
        v7 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v7);
        if (v7.getPosition() <= 1L) ** GOTO lbl-1000
        v8 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v8);
        v9 = v8.getDuration();
        v10 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v10);
        if (v9 - v10.getPosition() > 1L) {
            v11 = true;
        } else lbl-1000:
        // 2 sources

        {
            v11 = false;
        }
        var44_13 = v11;
        v12 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v12);
        MediaPlayerWidget.O.animate(v12.getPlaying() != false && var44_13 != false ? 1.0f : 0.0f);
        v13 = this;
        v14 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v14, "pose(...)");
        HudElement.drawBackground$default(v13, v14, this.getX(), (short)var23_6, var24_7, this.getY(), 120.0f, 43.0f, (short)var25_8, 0.0f, false, 32, null);
        v15 = this.getX() + 4.0f;
        v16 = this.getY() + 4.0f;
        v17 = var44_13 == false ? MediaPlayerWidget.O.getValue() : RangesKt.coerceIn(MediaPlayerWidget.O.getValue() + 0.5f, 0.0f, 1.0f);
        v18 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v18, "WHITE");
        v19 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v19, "WHITE");
        TextureRenderer.drawTexture(v15, v16, 35.0f, 35.0f, 3.0f, 2.0f, 0.0f, v17, v18, v19, var3_2.getMatrices(), var19_5, MediaPlayerWidget.coverTexture);
        v20 = this.getX() + 4.0f;
        v21 = this.getY() + 4.0f;
        v22 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v22, "WHITE");
        v23 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v23, "WHITE");
        TextureRenderer.drawTexture(v20, v21, 35.0f, 35.0f, 3.0f, 2.0f, 0.0f, 1.0f, v22, v23, var3_2.getMatrices(), var19_5, MediaPlayerWidget.coverTexture);
        v24 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v24);
        if (!v24.getPlaying() && MediaPlayerWidget.O.getValue() < 1.0f) {
            v25 = var3_2.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v25, "pose(...)");
            this.drawBackground(v25, this.getX() + 13.0f, this.getY() + 10.0f, 6.0f, var6_3, 22.0f, 1.0f - MediaPlayerWidget.O.getValue(), false);
            v26 = var3_2.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v26, "pose(...)");
            this.drawBackground(v26, this.getX() + 23.0f, this.getY() + 10.0f, 6.0f, var6_3, 22.0f, 1.0f - MediaPlayerWidget.O.getValue(), false);
        }
        v27 = Fonts.getSF_B2();
        v28 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v28);
        var45_14 = v27.getWidth(var30_10, v28.getTitle());
        var46_15 = 74.0f;
        var47_16 = RenderUtil.INSTANCE.lerp((float)Globals.getPlayer().age - 1.0f, (float)Globals.getPlayer().age, RenderUtil.INSTANCE.getTickDelta()) % 120.0f / 120.0f;
        v29 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v29);
        var48_17 = String.valueOf(v29.getPosition() % 60L);
        var50_18 = new Rect(this.getX() + 43.0f, this.getY(), this.getX() + 116.0f, this.getY() + 30.0f);
        var51_19 = var3_2;
        RenderUtil.INSTANCE.beginScissor(var51_19, var50_18);
        v30 = Fonts.getSF_B2();
        v31 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v31);
        v32 = v31.getTitle();
        if (var45_14 > var46_15) {
            v33 = MediaPlayerWidget.mediaInfo;
            Intrinsics.checkNotNull(v33);
            v34 = v33.getTitle();
        } else {
            v34 = "";
        }
        v30.drawStringWithShadow(var3_2, v32 + v34, var12_4, this.getX() + 42.0f - (var45_14 > var46_15 ? var47_16 * var45_14 : 0.0f), this.getY() + 3.0f, Theme.INSTANCE.getTextColor());
        v35 = Fonts.getSF_M4();
        v36 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v36);
        v35.drawStringWithShadow(var3_2, v36.getArtist(), var12_4, this.getX() + 42.0f, this.getY() + 13.0f, Theme.INSTANCE.getTextColor());
        RenderUtil.INSTANCE.endScissor(var3_2);
        v37 = Fonts.getSF_M4();
        v38 = MediaPlayerWidget.mediaInfo;
        Intrinsics.checkNotNull(v38);
        v37.drawStringWithShadow(var3_2, (int)((float)v38.getPosition() / 60.0f) + (var48_17.length() == 1 ? ":0" : ":") + var48_17, var12_4, this.getX() + 42.0f, this.getY() + 29.0f, Theme.INSTANCE.getTextColor());
        var49_20 = Fonts.getSF_M4().getWidth(var30_10, MediaPlayerWidget.U);
        Fonts.getSF_M4().drawStringWithShadow(var3_2, MediaPlayerWidget.U, var12_4, this.getX() + 116.0f - var49_20, this.getY() + 29.0f, Theme.INSTANCE.getTextColor());
        v39 = this.getX() + 42.0f;
        v40 = this.getY() + 23.0f;
        v41 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getModuleColor(), 0.7f);
        v42 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getModuleColor(), 0.7f);
        v43 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getModuleColor(), 0.7f);
        v44 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getModuleColor(), 0.7f);
        v45 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v45, "pose(...)");
        Gradient2D.drawGradientRect(v39, v40, var26_9, 74.0f, 4.0f, 2.0f, v41, v42, v43, v44, v45, 2.0f, Theme.INSTANCE.getModuleOutlineColor());
        v46 = this.getX() + 42.0f;
        v47 = this.getY() + 23.0f;
        v48 = Theme.INSTANCE.getSlidersColor();
        v49 = Theme.INSTANCE.getSlidersColor();
        v50 = Theme.INSTANCE.getSlidersColor();
        v51 = Theme.INSTANCE.getSlidersColor();
        v52 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v52, "pose(...)");
        Gradient2D.drawGradientRect(v46, v47, var26_9, 74.0f * var43_12, 4.0f, 2.0f, v48, v49, v50, v51, v52, 1.0f, Theme.INSTANCE.getModuleOutlineColor());
        v53 = this.getX() - 2.0f + 70.0f * var43_12 + 42.0f;
        v54 = this.getY() + 23.0f - 1.5f;
        v55 = Theme.INSTANCE.getSlidersColor();
        v56 = Theme.INSTANCE.getSlidersColor();
        v57 = Theme.INSTANCE.getSlidersColor();
        v58 = Theme.INSTANCE.getSlidersColor();
        v59 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v59, "pose(...)");
        Gradient2D.drawGradientRect(v53, v54, var26_9, 7.0f, 7.0f, 2.5f, v55, v56, v57, v58, v59, 1.0f, Theme.INSTANCE.getModuleOutlineColor());
        var3_2.getMatrices().popMatrix();
    }

    @Override
    public void tick() {
        block8: {
            int n;
            boolean bl;
            block7: {
                bl = ArrayListEntry.u();
                n = timer.hasPassed(1000);
                if (!bl) {
                    if (n == 0) {
                        return;
                    }
                    n = Y;
                }
                if (bl) break block7;
                if (n == 0) break block8;
                Y = false;
                n = b;
            }
            int n2 = n;
            b = n2 + 1;
            int n4 = n2;
            n4 = 20;
            if (!bl) {
                if (n3 > n4) {
                    J = false;
                }
                int n4 = 0;
                n4 = 0;
            }
            ThreadsKt.thread$default(n3 != 0, n4 != 0, null, null, 0, MediaPlayerWidget::findSession, 31, null);
        }
    }

    private static final Unit findSession() {
        Iterable iterable = MediaPlayerInfo.Instance.getMediaSessions();
        for (Object t : iterable) {
            IMediaSession iMediaSession = (IMediaSession)t;
            mediaInfo = iMediaSession.getMedia();
            U = iMediaSession.getOwner();
            if (iMediaSession.getMedia().getArtwork() != null) {
                BufferedImage bufferedImage;
                RenderUtil.INSTANCE.registerTexture(coverTexture, TextureUtil.INSTANCE.cropSquare(bufferedImage));
                coverSize = new Vec2f(bufferedImage.getWidth(), bufferedImage.getHeight());
            }
            J = true;
            b = 0;
        }
        Y = true;
        timer.reset();
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block26: {
            block25: {
                block24: {
                    block23: {
                        block22: {
                            block21: {
                                MediaPlayerWidget.a = ZkmKeys.a(4221061954764069181L, 7317170931695041761L, MethodHandles.lookup().lookupClass()).a(195108369576288L);
                                var31 = MediaPlayerWidget.a ^ 106170622852343L;
                                var37_1 = var31 ^ 42492522500014L;
                                MediaPlayerWidget.g = new HashMap<K, V>(13);
                                var22_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_3 = 1; var23_3 < 8; ++var23_3) {
                                    v2 = v2;
                                    v2[var23_3] = (byte)(var31 << var23_3 * 8 >>> 56);
                                }
                                var22_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_4 = new String[9];
                                var27_5 = 0;
                                var26_6 = "\u0089-Y\u0097kfz\u00fd2i\u00fc\u00c3\u00d4\u00dcMP<~y<\u0099\u00cb\u00c0\u0084\u0010:T,\u0004FH\u001f\u000f\u00a0\u0012h\u00e9\u00c5\u0018\u00a6\u009e\u00102\u0002\u0093\u00adZY\u00d0\u0015\u00f3]\u00bf\u00926\u0013{\u00d0\u0018\u0098\u00a9\u008a\u00d1/\"N \u00d9\u00cf\u0093H\u001e\u00cc\u009d\u00c9\u009b\u0086vd\u00e0\u00b5%\u008c\u0010\u00b8\u00a0\u0090\u00c2\\\u00dd`\u00cf\u00b8\u00b8^M}g\u00f0\u00be\u0010 ^\u00a6`\u00b2\u0088!)\u0006\u008b\u0098\u00d5Iz\u00caS(\u00e4\u00d1\u0090\u00cf\u00aa\u008a\u008e%4\u00ce;\u0006\u00a0\u00bd\u00a9\u00d4U\u0007\u00d4a\u00f9\u00b7*,\u00f2M\u0013\u0080\u00cdt\u00eb\u00f2o\u00e6\u00e0\u009f\u00fa\u00061\b";
                                var28_7 = 158;
                                var25_8 = 24;
                                var24_9 = -1;
lbl22:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_9;
                                    v4 = var26_6.substring(v3, v3 + var25_8);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl27:
                                // 1 sources

                                while (true) {
                                    var29_4[var27_5++] = MediaPlayerWidget.zkmDecodeUtf8(var30_10).intern();
                                    if ((var24_9 += var25_8) < var28_7) {
                                        var25_8 = var26_6.charAt(var24_9);
                                        ** continue;
                                    }
                                    var26_6 = "P\u0004\u00a2i\u000e9\u0098w\u00d4\u00cc\u009cx\u00d0\u008bCAh\u000f\u00af$\u00eb\u000fc \u009d:\u00aa\u00bc\u00ddY\u00e0\u00df\u0010\u00aa\u0000\u00b3\u0096&\u001e\u00b5)\u00b8x\u00d5o>Wbh";
                                    var28_7 = 49;
                                    var25_8 = 32;
                                    var24_9 = -1;
lbl36:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_9;
                                        v4 = var26_6.substring(v6, v6 + var25_8);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl41:
                                // 1 sources

                                while (true) {
                                    var29_4[var27_5++] = MediaPlayerWidget.zkmDecodeUtf8(var30_10).intern();
                                    if ((var24_9 += var25_8) < var28_7) {
                                        var25_8 = var26_6.charAt(var24_9);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var30_10 = var22_2.doFinal(v4.getBytes("ISO-8859-1"));
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
                        MediaPlayerWidget.c = var29_4;
                        MediaPlayerWidget.f = new String[9];
                        MediaPlayerWidget.k = new HashMap<K, V>(13);
                        var11_11 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_12 = 1; var12_12 < 8; ++var12_12) {
                            v9 = v9;
                            v9[var12_12] = (byte)(var31 << var12_12 * 8 >>> 56);
                        }
                        var11_11.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_13 = new long[7];
                        var14_14 = 0;
                        var15_15 = "\u008dv%2d:\u0093\u00c0\u00d9\u0092\u00e0{pV3\u001b\u0003\u0086\u00e9\u00e1/\u00e4\u0007\"\u00c5\u0083\u00d1\u0005\u00d1#S`\u0087\u009a\rp\u00a09#\u008f";
                        var16_16 = 40;
                        var13_17 = 0;
                        while (true) {
                            var18_18 = var15_15.substring(var13_17, var13_17 += 8).getBytes("ISO-8859-1");
                            v10 = var17_13;
                            v11 = var14_14++;
                            v12 = ((long)var18_18[0] & 255L) << 56 | ((long)var18_18[1] & 255L) << 48 | ((long)var18_18[2] & 255L) << 40 | ((long)var18_18[3] & 255L) << 32 | ((long)var18_18[4] & 255L) << 24 | ((long)var18_18[5] & 255L) << 16 | ((long)var18_18[6] & 255L) << 8 | (long)var18_18[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl80:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_17 < var16_16) ** continue;
                            var15_15 = "l\u001f\u00b6\u00b8\u0097\u0014D\f+0}\u00a6eDk\u008a";
                            var16_16 = 16;
                            var13_17 = 0;
                            while (true) {
                                var18_18 = var15_15.substring(var13_17, var13_17 += 8).getBytes("ISO-8859-1");
                                v10 = var17_13;
                                v11 = var14_14++;
                                v12 = ((long)var18_18[0] & 255L) << 56 | ((long)var18_18[1] & 255L) << 48 | ((long)var18_18[2] & 255L) << 40 | ((long)var18_18[3] & 255L) << 32 | ((long)var18_18[4] & 255L) << 24 | ((long)var18_18[5] & 255L) << 16 | ((long)var18_18[6] & 255L) << 8 | (long)var18_18[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl93:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_17 < var16_16) ** continue;
                            break block24;
                            break;
                        }
                    }
                    var19_19 = v12;
                    var21_20 = var11_11.doFinal(new byte[]{(byte)(var19_19 >>> 56), (byte)(var19_19 >>> 48), (byte)(var19_19 >>> 40), (byte)(var19_19 >>> 32), (byte)(var19_19 >>> 24), (byte)(var19_19 >>> 16), (byte)(var19_19 >>> 8), (byte)var19_19});
                    v14 = ((long)var21_20[0] & 255L) << 56 | ((long)var21_20[1] & 255L) << 48 | ((long)var21_20[2] & 255L) << 40 | ((long)var21_20[3] & 255L) << 32 | ((long)var21_20[4] & 255L) << 24 | ((long)var21_20[5] & 255L) << 16 | ((long)var21_20[6] & 255L) << 8 | (long)var21_20[7] & 255L;
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
                MediaPlayerWidget.h = var17_13;
                MediaPlayerWidget.i = new Integer[7];
                var1_21 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var2_22 = 1; var2_22 < 8; ++var2_22) {
                    v17 = v17;
                    v17[var2_22] = (byte)(var31 << var2_22 * 8 >>> 56);
                }
                var1_21.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var0_23 = new long[2];
                var4_24 = 0;
                var5_25 = "%\u00af\u0089\u00ad\u00ef\u00a6J\u008f\u0086\u0001d`\u00cc\u00e1nz";
                var6_26 = 16;
                var3_27 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl127:
                // 1 sources

                while (true) {
                    var0_23[v18] = ((long)var10_30[0] & 255L) << 56 | ((long)var10_30[1] & 255L) << 48 | ((long)var10_30[2] & 255L) << 40 | ((long)var10_30[3] & 255L) << 32 | ((long)var10_30[4] & 255L) << 24 | ((long)var10_30[5] & 255L) << 16 | ((long)var10_30[6] & 255L) << 8 | (long)var10_30[7] & 255L;
                    if (var3_27 < var6_26) ** continue;
                    break block26;
                    break;
                }
            }
            var7_28 = var5_25.substring(var3_27, var3_27 += 8).getBytes("ISO-8859-1");
            v18 = var4_24++;
            var8_29 = ((long)var7_28[0] & 255L) << 56 | ((long)var7_28[1] & 255L) << 48 | ((long)var7_28[2] & 255L) << 40 | ((long)var7_28[3] & 255L) << 32 | ((long)var7_28[4] & 255L) << 24 | ((long)var7_28[5] & 255L) << 16 | ((long)var7_28[6] & 255L) << 8 | (long)var7_28[7] & 255L;
            var10_30 = var1_21.doFinal(new byte[]{(byte)(var8_29 >>> 56), (byte)(var8_29 >>> 48), (byte)(var8_29 >>> 40), (byte)(var8_29 >>> 32), (byte)(var8_29 >>> 24), (byte)(var8_29 >>> 16), (byte)(var8_29 >>> 8), (byte)var8_29});
            ** while (true)
        }
        MediaPlayerWidget.S = new MediaPlayerWidget(var37_1);
        MediaPlayerWidget.coverTexture = Ids.id("catlean", "temp/cover");
        MediaPlayerWidget.coverSize = new Vec2f(1.0f, 1.0f);
        MediaPlayerWidget.Y = true;
        MediaPlayerWidget.U = "";
        MediaPlayerWidget.O = new Animation(Easing.OUT_QUINT, var0_23[1]);
        MediaPlayerWidget.visibilityAnimation = new Animation(Easing.OUT_BACK, var0_23[0]);
        MediaPlayerWidget.timer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x724B;
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
                throw new RuntimeException("su/catlean/pt", exception);
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
            MediaPlayerWidget.f[n2] = MediaPlayerWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MediaPlayerWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x74B9;
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
                throw new RuntimeException("su/catlean/pt", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MediaPlayerWidget.i[n2] = n3;
        }
        return i[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MediaPlayerWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pt" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MediaPlayerWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MediaPlayerWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

