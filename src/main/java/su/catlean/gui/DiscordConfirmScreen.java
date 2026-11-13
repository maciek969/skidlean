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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.account.Account;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.gui.RectButton;
import su.catlean.module.misc.RPC;
import su.catlean.render.RenderUtil;
import su.catlean.render.TextureRenderer;
import su.catlean.render.TextureUtil;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.util.Ids;
import su.catlean.zkm.ZkmKeys;

public final class DiscordConfirmScreen
extends Screen {
    @NotNull
    private final Identifier e = Ids.id("catlean", "textures/exclude/discord_confirm.png");
    @NotNull
    private final RectButton W;
    @NotNull
    private final RectButton Y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public DiscordConfirmScreen() {
        super(Text.of("catlean-discord-confirm"));
        Object[] objectArray = new Object[]{};
        String string = "gui.discord.yes";
        this.W = new RectButton(0.0f, 0.0f, 100.0f, 25.0f, I18n.INSTANCE.translate(string, objectArray), DiscordConfirmScreen::_init_$lambda$0, 0, null, null, 0.0f, 0.0f, 1984, null);
        Object[] objectArray2 = new Object[]{};
        String string2 = "gui.discord.no";
        this.Y = new RectButton(0.0f, 0.0f, 100.0f, 25.0f, I18n.INSTANCE.translate(string2, objectArray2), DiscordConfirmScreen::_init_$lambda$1, 0, null, null, 0.0f, 0.0f, 1984, null);
    }

    @Override
    public void render(@NotNull DrawContext drawContext, int n, int n2, float f) {
        long l;
        long l2 = l = a ^ 0x272681ED9D74L;
        long l3 = l2 ^ 0x5055E8D3F0C3L;
        long l4 = l2 ^ 0x5F6554FBE18DL;
        long l5 = l2 ^ 0x41C040402D37L;
        long l6 = l2 ^ 0x572FB813F155L;
        long l7 = l2 ^ 0x1029BD7C2D44L;
        long l8 = l2 ^ 0x4E9E4338CC4EL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        float f2 = (float)this.width / 2.0f;
        float f3 = (float)this.height / 2.0f;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        Color[] colorArray = new Color[]{new Color(-1072689136)};
        RenderUtil.INSTANCE.drawGradient(matrix3x2fStack, 0.0f, 0.0f, this.width, this.height, colorArray, l6);
        float f4 = Fonts.getSF_B3().getWidth(l3, "...") / 2.0f;
        FontRenderer fontRenderer = Fonts.getSF_B3();
        Color color = Color.GRAY;
        Intrinsics.checkNotNullExpressionValue(color, "GRAY");
        fontRenderer.drawStringWithShadow(drawContext, "...", l4, f2 - f4, f3 - 94.0f, color);
        FontRenderer fontRenderer2 = Fonts.getSF_B3();
        String string = StringsKt.dropLast("...", 3 - (int)(4.0 * ((double)Globals.now() % 1200.0 / 1200.0)));
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
        fontRenderer2.drawStringWithShadow(drawContext, string, l4, f2 - f4, f3 - 94.0f, color2);
        FontRenderer fontRenderer3 = Fonts.getSF_B3();
        Object[] objectArray = new Object[]{};
        String string2 = "gui.discord.confirm";
        String string3 = I18n.INSTANCE.translate(string2, objectArray);
        Color color3 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color3, "WHITE");
        fontRenderer3.drawStringCentered(drawContext, string3, f2, f3 - 10.0f, RenderUtil.INSTANCE.withAlpha(color3, 0.5f), l5);
        if (!Intrinsics.areEqual(DiscordRpcSettings.INSTANCE.Q(), DiscordRpcSettings.INSTANCE.G())) {
            Color color4 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color4, "WHITE");
            Color color5 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color5, "WHITE");
            TextureRenderer.drawTexture(f2 - 85.0f, f3 - 108.0f, 65.0f, 65.0f, 32.5f, 2.0f, 0.0f, 1.0f, color4, color5, drawContext.getMatrices(), l7, TextureUtil.INSTANCE.getTexture(Account.getAvatarUrl$default(DiscordRpcSettings.INSTANCE.Q(), true, null)));
        } else {
            Color color6 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color6, "WHITE");
            Color color7 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color7, "WHITE");
            TextureRenderer.drawTexture(f2 - 85.0f, f3 - 108.0f, 65.0f, 65.0f, 32.5f, 2.0f, 0.0f, 1.0f, color6, color7, drawContext.getMatrices(), l7, TextureUtil.INSTANCE.getTexture("https://i.imgur.com/y5rOVPP.png"));
        }
        Color color8 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color8, "WHITE");
        Color color9 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color9, "WHITE");
        TextureRenderer.drawTexture(f2 + 20.0f, f3 - 108.0f, 65.0f, 65.0f, 32.5f, 2.0f, 0.0f, 1.0f, color8, color9, drawContext.getMatrices(), l7, this.e);
        this.Y.render(f2 - 110.0f, f3 + 40.0f, n, l8, n2, drawContext);
        this.W.render(f2 + 10.0f, f3 + 40.0f, n, l8, n2, drawContext);
    }

    @Override
    public boolean mouseClicked(@NotNull Click click, boolean bl) {
        Intrinsics.checkNotNullParameter(click, "click");
        double d = click.comp_4798();
        double d2 = click.comp_4799();
        this.W.mouseClicked((float)d, (float)d2);
        this.Y.mouseClicked((float)d, (float)d2);
        return super.mouseClicked(click, bl);
    }

    @Override
    protected void applyBlur(@NotNull DrawContext drawContext) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    @Override
    public void renderBackground(@NotNull DrawContext drawContext, int n, int n2, float f) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    private static final Unit _init_$lambda$0() {
        RPC.INSTANCE.setEnabled(true);
        Globals.getMc().setScreen(new TitleScreen());
        return Unit.INSTANCE;
    }

    private static final Unit _init_$lambda$1() {
        RPC.INSTANCE.setEnabled(false);
        Globals.getMc().setScreen(new TitleScreen());
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        DiscordConfirmScreen.a = ZkmKeys.a(5236209869090529453L, -3483411034988702709L, MethodHandles.lookup().lookupClass()).a(250483077177830L);
                        DiscordConfirmScreen.d = new HashMap<K, V>(13);
                        var11 = DiscordConfirmScreen.a ^ 2495366296705L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[16];
                        var18_4 = 0;
                        var17_5 = "\u00db\u00b0Q4\u0095l<OhE\u00ac\u00be\u000b\u00f2E?\u0018\u000b\u00d6/\\\u00c2\u00c8\u0087e^\u0012@\u00e3\u00e8t\u00a5CG#\u00fe\u0098\u00f0.v;\u0010\u007f\u00a7\u008e\u0013\u00da!B\u0080\u00b9,\u00bcW\u007fL\u00b9\u00bc\u0010\u00d0\u00bf\u0016\u00afg\u00a0\u00d0\u00f5Q\u00a4C2T9+\u001b\u0010\u00eb;\u009f\u00a7+ad5\u00a7\u00f7\u008fd\u00f9\u00e0\u00df\u0017(9\u00c4j\\F\n\u00e5\u000f\u0016\u00d0{`\u00ca\u00fc%LK\u00b9\u00b5\u00a9\u0017\u0013:\u0092=\u008f{i\u001a\u00fc\u00ef\"o<\u00fd\u00beh\u000f[N\u0010W$\u00cfJpse\u00c1\u007f\u00fb5O\u009b\u00a7\u0095\u00e4\u0010;\u00ddZ1\u0004\u008e0,\u00b3$\u001b\n\u00f3/y\u008c\u0018$w\u00bcD\u009f\fIT\u0080\u00d2\u00a4\u007f\u00af\u0098\u008br\u0000 BEy\u00a1\u00f7]0\u00a0\u00e1\u0011 \u00d4\u00a8\u00c1\u00146\u00e6\u009b\u00d1\u00d8's\t4\u0089\u00e8\u00a1\u00d0\u00b8\u0085\u0085\u00ff\u0014eIu\u00aa\u00f4\u00a69\u00d4\u00f3t\u00a5\u0018v\u00f1\u00a4\u0090\u00ee\u009e\u0001\u00e2\u00e08\u0010i\u00a9\u00a2\u00b2\u0002&\u0095MW91\u00e4\u009d\u00ac\u00b4\u00ba\u0010\u00fa.\u00f4_\u00d5\u00f6\u0012\u00e5\u000b\u00b7\u0081\u00df\u00b2\u00f1q\u00ac\u0010\u008f\u00ca\u00e4u\u0002\u00cd\u00e1Zl\u00ea\u00db-E\u00ba\u00bcL(\u00e4`\u00a3]b\u0007h\u00c4-\u00a6\u00a6D\u00cb\u00c20\u0016\u00cb\u00d9\u00fa\u00f1A\u0018\u00ba\u00cc\u00fe\u00faB\u00c2\u009e-\u000b\u00a6\u00adm\u0088-\u001co\u00e55";
                        var19_6 = 333;
                        var16_7 = 16;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = DiscordConfirmScreen.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00a0\u00ac\u001d\u0015\u001d\u00d2.=}\u00c5\u00d5W\u0089\u00e6\t\u0098\u00d1\u00e5\u009c>\u0002P*n\\\u00e8\u00cb\u00d7\u00eb\u00e6\u00e0\u008b\u001c\u0084\u00ffWs\u00bb-\u009e\u000f\u00de\u009ay\u00c1\u00ed\tM\u0013\u0092\u000e\u009a\u00c7\u00ea$\u00a3\u001aA\u00f3\u00ca\u00f4\u00a5qe\u0018\u0082\u0006\u00f9\u00ff(\u001d\u00f9\u00ba#\u0001\u00cfG \u00b2$\u00cf\u00a0KnD\u000f\u00f0\u00c9[";
                            var19_6 = 89;
                            var16_7 = 64;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = DiscordConfirmScreen.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                DiscordConfirmScreen.b = var20_3;
                DiscordConfirmScreen.c = new String[16];
                DiscordConfirmScreen.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u008b\u001c\u0012\u00fc\u00db\u00bf*\u009d\u00bb\u00a0q\u0011Qe\u00feK\u00a0\u0004eo\u00ef\u00f0\u0097\u0093";
                var5_15 = 24;
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "JCrke\u001b\u00d9[W\u0015i{\u00c4\u00ec\u00c3\u0099";
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        DiscordConfirmScreen.f = var6_12;
        DiscordConfirmScreen.g = new Integer[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x375B;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ie", exception);
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
            DiscordConfirmScreen.c[n2] = DiscordConfirmScreen.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DiscordConfirmScreen.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ie" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5E3B;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ie", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            DiscordConfirmScreen.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = DiscordConfirmScreen.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ie" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DiscordConfirmScreen.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(DiscordConfirmScreen.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

