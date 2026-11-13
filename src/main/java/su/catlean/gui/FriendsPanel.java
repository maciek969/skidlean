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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ConfigStore;
import su.catlean.gui.GuiElement;
import su.catlean.gui.IconButton;
import su.catlean.gui.SidePanel;
import su.catlean.gui.TextField;
import su.catlean.gui.TextInput;
import su.catlean.render.Rect;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.FriendManager;
import su.catlean.zkm.ZkmKeys;

public final class FriendsPanel
extends SidePanel {
    @NotNull
    public static final FriendsPanel INSTANCE;
    @NotNull
    private static final RenderTarget renderTarget;
    @NotNull
    private static final List friendButtons;
    private static boolean c;
    @NotNull
    private static Animation J;
    @NotNull
    private static Animation B;
    private static float scroll;
    private static final long b;
    private static final String[] e;
    private static final String[] i;
    private static final Map j;
    private static final long[] n;
    private static final Integer[] q;
    private static final Map r;

    private FriendsPanel() {
        super("homies-panel", 0.0f, 0.0f, 0.0f, 0.0f, null);
    }

    @NotNull
    public final RenderTarget getRenderTarget() {
        return renderTarget;
    }

    @Override
    public void refresh() {
        friendButtons.clear();
        Iterable iterable = ConfigStore.INSTANCE.getJsonListFile2().getData();
        for (Object t : iterable) {
            String string = (String)t;
            friendButtons.add(new IconButton(string));
        }
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4) {
        block3: {
            v0 = var4_4;
            var6_5 = v0 ^ 136208856317167L;
            v1 = v0 ^ 22135879901675L;
            var8_6 = (int)(v1 >>> 48);
            var9_7 = (int)(v1 << 16 >>> 48);
            var10_8 = (int)(v1 << 32 >>> 32);
            var11_9 = v0 ^ 27182768661316L;
            var15_10 = v0 ^ 0L;
            var17_11 = v0 ^ 129382754651428L;
            Intrinsics.checkNotNullParameter(var1_1, "context");
            new String[0];
            this.setWidth(110.0f);
            super.render(var1_1, var2_2, var3_3, var15_10);
            var41_12 = this.getX() + 3.0f;
            var42_13 = this.getX() + 27.0f;
            var43_15 = var2_2;
            v2 = var41_12 <= var43_15 ? var43_15 <= var42_13 : false;
            if (!v2) ** GOTO lbl-1000
            var41_12 = this.getY() + 5.0f;
            var42_13 = this.getY() + 13.0f;
            var43_15 = var3_3;
            v3 = var41_12 <= var43_15 ? var43_15 <= var42_13 : false;
            if (v3) {
                v4 = true;
            } else lbl-1000:
            // 2 sources

            {
                v4 = false;
            }
            FriendsPanel.c = v4;
            FriendsPanel.J.animate(FriendsPanel.scroll);
            FriendsPanel.B.animate(this.getGuiElement() != null ? 0.5f : 0.0f);
            v5 = var1_1.getMatrices();
            Intrinsics.checkNotNullExpressionValue(v5, "pose(...)");
            var25_16 = null;
            var26_17 = 14336;
            var27_18 = 0.0f;
            var28_19 = 0.0f;
            var29_20 = 0.0f;
            var30_21 = Theme.INSTANCE.getOutlineColor();
            var31_22 = Theme.INSTANCE.g();
            var32_23 = 1.0f;
            var33_24 = FriendsPanel.c != false ? 0.1f : 0.0f;
            var34_25 = FriendsPanel.c != false ? 2.5f : 2.0f;
            var35_26 = 2.0f;
            var36_27 = 8.0f;
            var37_28 = 22.0f;
            var38_29 = this.getY() + 3.0f;
            var39_30 = this.getX() + 5.0f;
            Shapes2D.drawShape$default(v5, (char)var8_6, var39_30, var38_29, var37_28, var36_27, var35_26, var34_25, var33_24, var32_23, var31_22, var30_21, var29_20, (short)var9_7, var28_19, var10_8, var27_18, var26_17, var25_16);
            v6 = Fonts.getSF_M();
            v7 = this.getY() + 1.0f;
            v8 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(v8, "WHITE");
            v6.drawStringWithShadow(var1_1, "+", var17_11, this.getX() + 13.5f, v7, v8);
            RenderUtil.INSTANCE.beginScissor(var1_1, new Rect(this.getX(), this.getY() + 14.0f, this.getX() + this.getWidth() - 4.0f, this.getY() + this.getHeight() - 3.0f));
            var41_12 = 17.0f + FriendsPanel.J.getValue();
            var42_14 = FriendsPanel.friendButtons;
            for (T var45_32 : var42_14) {
                var46_33 = (IconButton)var45_32;
                var46_33.render(var6_5, var1_1, FriendsPanel.INSTANCE.getX(), FriendsPanel.INSTANCE.getY() + var41_12, var2_2, var3_3);
                var41_12 += 29.0f;
            }
            RenderUtil.INSTANCE.endScissor(var1_1);
            v9 = this.getGuiElement();
            if (v9 == null) break block3;
            v9.render(var1_1, this.getX() + this.getWidth() / 2.0f - 60.0f, this.getY() + this.getHeight() / 2.0f - 35.0f, var11_9, var2_2, var3_3);
        }
    }

    @Override
    public void mouseClicked(float f, float f2, short s, int n, int n2, char c) {
        long l;
        long l2 = l = (long)s << 48 | (long)n2 << 32 >>> 16 | (long)c << 48 >>> 48;
        long l3 = l2 ^ 0L;
        int n3 = (int)(l3 >>> 48);
        int n4 = (int)(l3 << 16 >>> 32);
        int n5 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x23524E02DD14L;
        super.mouseClicked(f, f2, (short)n3, n, n4, (char)n5);
        GuiElement guiElement = this.getGuiElement();
        boolean bl = guiElement != null ? guiElement.mouseClicked(l4, f, f2) : false;
        if (bl) {
            return;
        }
        if (FriendsPanel.c) {
            TextField.Companion.A(null);
            this.setGuiElement(new TextInput(FriendsPanel::addFriend));
            return;
        }
        boolean bl2 = false;
        Iterable iterable = friendButtons;
        for (Object t : iterable) {
            IconButton iconButton = (IconButton)t;
            if (!iconButton.isHovered(f, f2)) continue;
            bl2 = true;
        }
        if (bl2) {
            this.refresh();
        }
        this.setGuiElement(null);
    }

    @Override
    public void mouseScrolled(double d, double d2, double d3) {
        block1: {
            float f = this.getX();
            boolean bl = d <= (double)(this.getX() + 76.0f) ? (double)f <= d : false;
            if (!bl) break block1;
            f = this.getY();
            boolean bl2 = d2 <= (double)(this.getY() + this.getHeight() - 2.0f) ? (double)f <= d2 : false;
            if (bl2) {
                scroll += (float)d3 * 7.0f;
                scroll = Math.clamp(scroll, (float)friendButtons.size() * -29.0f, 0.0f);
            }
        }
    }

    private static final Unit addFriend(String string) {
        Intrinsics.checkNotNullParameter(string, "it");
        FriendManager.INSTANCE.addFriend(string);
        INSTANCE.refresh();
        INSTANCE.setGuiElement(null);
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
                                FriendsPanel.b = ZkmKeys.a(3419684410584018462L, -4289339138080883700L, MethodHandles.lookup().lookupClass()).a(165092007176461L);
                                var31 = FriendsPanel.b ^ 80980243709989L;
                                FriendsPanel.j = new HashMap<K, V>(13);
                                var22_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_2 = 1; var23_2 < 8; ++var23_2) {
                                    v2 = v2;
                                    v2[var23_2] = (byte)(var31 << var23_2 * 8 >>> 56);
                                }
                                var22_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_3 = new String[6];
                                var27_4 = 0;
                                var26_5 = "\u0006r6q\u0006\u0087\b\u009f^\u00ac\u00e6\u001c\u0091n\u00f9\u0098 ]\u00bb\u00f68\u00e9\u00e7I\u00c5\u00dbu\u0006\u00edw\u0017\u001dM$\u00fdm\u00aakGp\u009d\u009c7n\u00de\u00e7\u0007\u0019\u00c5\u0018#@\u0085T&g3\u0091\u008f`\u0089@5Q\u0004\u0084\u00faX\u00d0\u00e3s\u008d\u009f\u00f5 \u0099KV\u0085f\u00e5\u008d\u0000r\u00a3\u00f9n\u00f8\u0098\u00f3\u00c0\u00fb\u0089\u00bd\u0086\u0089\u008bj\u00f8\u0014\u001b\u00ed@\u00c6Q\u000fG";
                                var28_6 = 107;
                                var25_7 = 16;
                                var24_8 = -1;
lbl20:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl25:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = FriendsPanel.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u00cb\u000b\u00b19P\u00d2\u00b5\u00b8\u00cc\u0090\u008e\u008e\t\u00f2\u00cd\u008e\u0010\u00cc[\u00c5\u00e4\u00c3\u00f1\u00cfSKT\u00b60\u00ceY\u0085\u00f6";
                                    var28_6 = 33;
                                    var25_7 = 16;
                                    var24_8 = -1;
lbl34:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl39:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = FriendsPanel.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        FriendsPanel.e = var29_3;
                        FriendsPanel.i = new String[6];
                        FriendsPanel.r = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var31 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[4];
                        var14_13 = 0;
                        var15_14 = "\u00f6lO6l\u00fe\u008b3\u00dcSEI\u00a8\u00f1\u00c5\u000b";
                        var16_15 = 16;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl78:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "\u00df\u009b.\u0093@!O\u00b0R\u008fY\u0091J\u0092\u00a0\u00fa";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl91:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block24;
                            break;
                        }
                    }
                    var19_18 = v12;
                    var21_19 = var11_10.doFinal(new byte[]{(byte)(var19_18 >>> 56), (byte)(var19_18 >>> 48), (byte)(var19_18 >>> 40), (byte)(var19_18 >>> 32), (byte)(var19_18 >>> 24), (byte)(var19_18 >>> 16), (byte)(var19_18 >>> 8), (byte)var19_18});
                    v14 = ((long)var21_19[0] & 255L) << 56 | ((long)var21_19[1] & 255L) << 48 | ((long)var21_19[2] & 255L) << 40 | ((long)var21_19[3] & 255L) << 32 | ((long)var21_19[4] & 255L) << 24 | ((long)var21_19[5] & 255L) << 16 | ((long)var21_19[6] & 255L) << 8 | (long)var21_19[7] & 255L;
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
                FriendsPanel.n = var17_12;
                FriendsPanel.q = new Integer[4];
                var1_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var2_21 = 1; var2_21 < 8; ++var2_21) {
                    v17 = v17;
                    v17[var2_21] = (byte)(var31 << var2_21 * 8 >>> 56);
                }
                var1_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var0_22 = new long[2];
                var4_23 = 0;
                var5_24 = "]`}\u00d0\u00e6w\u00d3\u00f7\u001f\u00a2!\u00bc\u00177\u00bf\u00aa";
                var6_25 = 16;
                var3_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl125:
                // 1 sources

                while (true) {
                    var0_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var3_26 < var6_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var5_24.substring(var3_26, var3_26 += 8).getBytes("ISO-8859-1");
            v18 = var4_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var1_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        FriendsPanel.INSTANCE = new FriendsPanel();
        FriendsPanel.renderTarget = new RenderTarget("panels/trash", 19, 19);
        FriendsPanel.friendButtons = new ArrayList<E>();
        FriendsPanel.J = new Animation(Easing.OUT_QUINT, var0_22[0]);
        FriendsPanel.B = new Animation(Easing.OUT_QUINT, var0_22[1]);
        FriendsPanel.INSTANCE.setY(240.0f);
        FriendsPanel.INSTANCE.setX(290.0f);
        FriendsPanel.INSTANCE.setWidth(110.0f);
        FriendsPanel.INSTANCE.refresh();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7182;
        if (i[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])j.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ib", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = e[n2].getBytes("ISO-8859-1");
            FriendsPanel.i[n2] = FriendsPanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return i[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FriendsPanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ib" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2250;
        if (q[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = FriendsPanel.n[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])r.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    r.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ib", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FriendsPanel.q[n2] = n3;
        }
        return q[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FriendsPanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ib" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FriendsPanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FriendsPanel.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

