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
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.CatLeanKt;
import su.catlean.account.Account;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.render.RenderUtil;
import su.catlean.render.TextureRenderer;
import su.catlean.render.TextureUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class WatermarkWidget
extends HudElement {
    @NotNull
    public static final WatermarkWidget m;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private WatermarkWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x223F6B416B85L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "watermark-widget", (char)n2);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        float f;
        block6: {
            DrawContext drawContext2;
            WatermarkWidget watermarkWidget;
            float f2;
            long l2;
            block4: {
                block5: {
                    Object object;
                    long l3 = l;
                    l2 = l3 ^ 0x1322F7EE11BEL;
                    long l4 = l3 ^ 0x5224AC9B6C8EL;
                    long l5 = l3 ^ 0x5EAEB98FBCE1L;
                    int n = (int)(l5 >>> 48);
                    int n2 = (int)(l5 << 16 >>> 32);
                    int n3 = (int)(l5 << 48 >>> 48);
                    long l6 = l3 ^ 0x74C22041CFCEL;
                    long l7 = l3 ^ 0x1258F934B109L;
                    boolean bl = ArrayListEntry.u();
                    Intrinsics.checkNotNullParameter(drawContext, "context");
                    boolean bl2 = bl;
                    String string = " " + Formatting.GRAY + "v" + CatLeanKt.getModContainer().getMetadata().getVersion();
                    String string2 = Globals.getPlayer().getGameProfile().name();
                    if (bl2 || (object = Globals.getNetworkHandler().getServerInfo()) == null || (object = ((ServerInfo)object).address) == null) {
                        object = "singleplayer";
                    }
                    String string3 = object;
                    f = Fonts.getSF_M().getWidth(l7, "CatLean " + string);
                    FontRenderer fontRenderer = Fonts.getSF_M();
                    Intrinsics.checkNotNull(string2);
                    float f3 = fontRenderer.getWidth(l7, string2);
                    float f4 = Fonts.getSF_M().getWidth(l7, string3);
                    f2 = this.getX();
                    HudElement hudElement = this;
                    Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                    HudElement.drawBackground$default(hudElement, matrix3x2fStack, f2 + 30.0f, (short)n, n2, this.getY(), f + 10.0f, 13.0f, (short)n3, 0.0f, false, 96, null);
                    HudElement hudElement2 = this;
                    Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                    HudElement.drawBackground$default(hudElement2, matrix3x2fStack2, f2 + 30.0f, (short)n, n2, this.getY() + 14.0f, f3 + 6.0f, 13.0f, (short)n3, 0.0f, false, 96, null);
                    HudElement hudElement3 = this;
                    Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
                    HudElement.drawBackground$default(hudElement3, matrix3x2fStack3, f2 + 31.0f + f3 + 6.0f, (short)n, n2, this.getY() + 14.0f, f4 + 7.0f, 13.0f, (short)n3, 0.0f, false, 96, null);
                    this.drawString(drawContext, string, f2 + 33.0f + Fonts.getSF_B2().getWidth(l7, "CatLean"), l6, this.getY() + 1.0f, Theme.INSTANCE.getTextColor());
                    this.drawString(drawContext, string2, f2 + 33.0f, l6, this.getY() + 15.0f, Theme.INSTANCE.getTextColor());
                    watermarkWidget = this;
                    drawContext2 = drawContext;
                    if (bl2) break block4;
                    watermarkWidget.drawString(drawContext2, string3, f2 + 40.5f + f3, l6, this.getY() + 15.0f, Theme.INSTANCE.getTextColor());
                    if (Intrinsics.areEqual(DiscordRpcSettings.INSTANCE.Q(), DiscordRpcSettings.INSTANCE.G())) break block5;
                    float f5 = this.getY() - 1.0f;
                    Color color = Color.WHITE;
                    Intrinsics.checkNotNullExpressionValue(color, "WHITE");
                    TextureRenderer.drawTexture(f2 + 1.0f, f5, 27.0f, 27.0f, 3.0f, 2.0f, 0.0f, 1.0f, color, Theme.INSTANCE.getWidgetBlurColor(), drawContext.getMatrices(), l4, TextureUtil.INSTANCE.getTexture(Account.getAvatarUrl$default(DiscordRpcSettings.INSTANCE.Q(), true, null)));
                    if (!bl2) break block6;
                }
                watermarkWidget = this;
                drawContext2 = drawContext;
            }
            watermarkWidget.drawLogo(drawContext2, f2, l2);
        }
        this.setSize(f + 40.0f, 28.0f);
    }

    @Override
    public void renderOverlay(@NotNull DrawContext drawContext, long l) {
        long l2 = l ^ 0x739004C9163EL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Fonts.getSF_B2().drawString(drawContext, "CatLean", l2, this.getX() + 33.0f, this.getY() + 0.5f, 10);
    }

    private final void drawLogo(DrawContext drawContext, float f, long l) {
        long l2 = (l = a ^ l) ^ 0x39A756851E03L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        drawContext.getMatrices().pushMatrix();
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Matrix3x2f matrix3x2f = (Matrix3x2f)matrix3x2fStack;
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity, "null cannot be cast to non-null type net.minecraft.client.player.AbstractClientPlayer");
        Identifier identifier = ((AbstractClientPlayerEntity)clientPlayerEntity).getSkin().comp_1626().comp_3627();
        Intrinsics.checkNotNullExpressionValue(identifier, "texturePath(...)");
        float f2 = this.getY() + 1.0f;
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color, "WHITE");
        RenderUtil.drawTexture$default(RenderUtil.INSTANCE, matrix3x2f, identifier, f + 3.0f, f2, 24.0f, 24.0f, color, null, n, null, null, 0.125f, (char)n2, 0.125f, 0.25f, (short)n3, 0.25f, false, 17280, null);
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Matrix3x2f matrix3x2f2 = (Matrix3x2f)matrix3x2fStack2;
        ClientPlayerEntity clientPlayerEntity2 = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity2, "null cannot be cast to non-null type net.minecraft.client.player.AbstractClientPlayer");
        Identifier identifier2 = ((AbstractClientPlayerEntity)clientPlayerEntity2).getSkin().comp_1626().comp_3627();
        Intrinsics.checkNotNullExpressionValue(identifier2, "texturePath(...)");
        float f3 = this.getY() - 1.0f;
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
        RenderUtil.drawTexture$default(RenderUtil.INSTANCE, matrix3x2f2, identifier2, f + 1.0f, f3, 28.0f, 28.0f, color2, null, n, null, null, 0.625f, (char)n2, 0.125f, 0.75f, (short)n3, 0.25f, false, 17280, null);
        drawContext.getMatrices().popMatrix();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        WatermarkWidget.a = ZkmKeys.a(-247199576849033109L, 1780895264669328685L, MethodHandles.lookup().lookupClass()).a(9484570757181L);
                        var20 = WatermarkWidget.a ^ 46744505311324L;
                        var22_1 = var20 ^ 90243623698770L;
                        WatermarkWidget.f = new HashMap<K, V>(13);
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
                        var18_4 = new String[15];
                        var16_5 = 0;
                        var15_6 = "\u00dc\u00ac5\u00f8\"\u00a3O\u00d6\u00c6\u0080\u0010\u00dc\u00b8\u00d6\u00c5\u00a4\u00a7P\u0015b*\"\u00ed\u00d5\u00e7\u0092\u00f60O\u008fl6\u00f7ct\u00f9\u0087\u0010\u00ed>\u00ca\u00c55g\u00c9:Oh\u009c \u00f3\u00d3\u00c3?0D\u00a9\u0014\u00c2\u0010\u0015%N\u001d\u00c7Z\u0092buc\u00f0xb\u008c\u001d\u008e\u00fc\u0089\u008cDa\u0093+\u00d6\u00a8\u00e1<\u008e\u00de\u00e8B\u008a\u00be\u00d8\u00bc\u0099\u00fb\u00b4y\u0099\u0098\u00167w\u00fb\u0095\u00cf\u00e4\u009f-\u00cc0\u00a4\u00ca\u00ddx\u00a1\u00a6?\u00c2\u00f1w\u00f6\u00f0c\u0093;\u008f6\u00ca^\u00c8C)\u00d7\u00e1\u00c9\u00f2\u00e0\u00aa\u0096\u0010\u00ff\u00cc \u00ed\u00c4\u00bc\u001d\u00e4.\u0081\u008fz\u00d2\u0082\u001b\u00b0|\u00a9\u00e6\u0090L=-\u00e3y\u009bw\u00cbs\u00bbeYm\u00eb\u001d\u0010\u00ef\u00d9\u00cc]\u00d8\u008f\u0012\u0096\u00b2\u009c\u00bf\u00c1\u00b6\u007fA\u0094 \u00a3\u00e1\u00a8j\u000f\u0096\u00b8\u00da\u00f2\u00fb\u008e5\u00e3\u00caY\u0005\u00e1\u00b4L\u00d7\u00af\u00e9\bW\u00ea$\u00b6\u0005J\u0007Q,(M\u00d4\u00f1\u00fd\u009dk\u00b9\"T\u00fc\u00d0b\u0002j\u00f3\u00b2V\u00db\u00e04\u00bbKK\u00f8\u00d1AX\t\u000f\u00a0\u0091MIV\u00d6b\u00d5\u0097V1 e\u008d\u00fcwA\\\u00bf\u00a4\u0097=TZ\u00f3\u00e6\u00f1\u0095\u00b6\u00fd~{\u000eG\u00cb\"\u00c6\u00a4\u0095\u00e5+\u0015\u00a3>\u0010\u00a9t>st\u00d8\u0089\u00a2$!\u0002\u0017\u00feA\u0014m\u0080\u001ch\u0086\u00f2w\u00c6\u00bf4_\u00e06\u0081\u00a5\u00d0a/\u0094g$\u0017Aw.P'\u000e\u00df\u00a0\u001ar\u009a\u00d5\u00aa;\u00bb\u001c\u00fe\u009fE\u00dd\u00a9\u0000\u0090\u00f9\u0017\u00bd\u00c1\u00cc[D\u001e\u0005\u0006\u0019\u0094\u00f6\u00a0\u00b2z\u00fe\u00ed^\u00df\u00f0i\u00ca\u008an\u00f8'\u00f3\u00dd\u00e9\u009d\u0098\u0097\u00d4\u00a7E\u00c2\u0089)2\u00a6H!\u00cf\u00a87$\u000f\u001e,0E\u00bb\u0085c\u00fd\u00b4\u00ef\u0085\u00b7\u00eeCI\u0081u\u00e4-\u0019/\u00da6\u00b1\u0017\u00fa#\u001e\u008ds&\u001dK\u00e8\n\u00fe9\u0018\u00da\u009a+tou\u0091:\u00ba\u009b\u00d6{\u00a9\u00b3\u00b4\u00b5&\u00cc\u0099\u00e6c\u0015U%\u0010\u00bb:-\u009f\u00ae\u00ea\u00ab\u00b4\u00dd55\u001e#\u0004\u00c0[\u0010?$\u001a\u00a8y\u00aa\u00e0pR\u00c4\u00b0\u00a5\u00c1\r0#(\u000b\u0080\u00db?\u00f6H\u00bf\u00a6\u00f5\u000f\u00f2\u0083\u00aa\u00a4\u00cf\u00fd\u00c9\ri'\u000b\u00ec\u00a3p8+aoB:\u00db\u00d7\u0099\u00cb\u009b\u0097\u00d85\u001fk\u0010\u00eae\u00eaE\u0017\u008d\u00e0v{\u00e9!_\u0090v\u00866";
                        var17_7 = 564;
                        var14_8 = 144;
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
                            var18_4[var16_5++] = WatermarkWidget.zkmDecodeUtf8(var19_10).intern();
                            if ((var13_9 += var14_8) < var17_7) {
                                var14_8 = var15_6.charAt(var13_9);
                                ** continue;
                            }
                            var15_6 = "df'B\"\u008bmH\u00fb\u009c\u00b5\u00dd\u0002\u00f7\u00aa\u00e0\u00a6P\u001a\u00db\u00c8\u00c8\u00f9\u00b2\u00bf\u00e2\u00d1\u00d7\u00b6\u00d6@S\u0082M|UPd{e\u0010\u0016\"+\t\u00b9\u008b\u0085=\u009d\nXR\u0082nS\u001a";
                            var17_7 = 57;
                            var14_8 = 40;
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
                            var18_4[var16_5++] = WatermarkWidget.zkmDecodeUtf8(var19_10).intern();
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
                WatermarkWidget.b = var18_4;
                WatermarkWidget.c = new String[15];
                WatermarkWidget.i = new HashMap<K, V>(13);
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
                var6_13 = new long[5];
                var3_14 = 0;
                var4_15 = "\u00bb\u0094\u00fc-\u00afE\"\u008at\u00bf\u00d6\u00a3>~i\u00e1\u00d8\u000f\u000bq2Q\u00e16";
                var5_16 = 24;
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
                    var4_15 = "jV\u00cd\u0098nw\u00c8\u00f4G^\u0005\u0094\u00f6\u00df\u00d8&";
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
        WatermarkWidget.g = var6_13;
        WatermarkWidget.h = new Integer[5];
        WatermarkWidget.m = new WatermarkWidget(var22_1);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x33C1;
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
                throw new RuntimeException("su/catlean/pc", exception);
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
            WatermarkWidget.c[n2] = WatermarkWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WatermarkWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x795E;
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
                throw new RuntimeException("su/catlean/pc", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            WatermarkWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = WatermarkWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pc" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WatermarkWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(WatermarkWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

