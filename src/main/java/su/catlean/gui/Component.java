/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import su.catlean.gui.ClickGui;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.ItemPresetEditor;
import su.catlean.module.Module;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public class Component {
    @NotNull
    private final Animation animation = new Animation(Easing.OUT_QUINT, G);
    private float x = 200.0f;
    private float y = 250.0f;
    private float E;
    private boolean closing;
    private static boolean l;
    private static final long b;
    private static final String[] f;
    private static final String[] h;
    private static final Map j;
    private static final long[] t;
    private static final Integer[] x_obf;
    private static final Map z;
    private static final long G;

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
    }

    public final float getX() {
        return this.x;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final float getY() {
        return this.y;
    }

    public final void setY(float f) {
        this.y = f;
    }

    public final float X() {
        return this.E;
    }

    public final void Z(float f) {
        this.E = f;
    }

    public final boolean isClosing() {
        return this.closing;
    }

    public final void setClosing(boolean bl) {
        this.closing = bl;
    }

    /*
     * Unable to fully structure code
     */
    public final void render(@NotNull DrawContext var1_1, int var2_2, int var3_3, long var4_4) {
        block17: {
            block19: {
                block20: {
                    block18: {
                        block16: {
                            block15: {
                                block14: {
                                    v0 = var4_4 = Component.b ^ var4_4;
                                    var8_5 = v0 ^ 104154702343303L;
                                    v1 = v0 ^ 85641409853395L;
                                    var10_6 = (int)(v1 >>> 48);
                                    var11_7 = (int)(v1 << 16 >>> 48);
                                    var12_8 = (int)(v1 << 32 >>> 32);
                                    var15_9 = v0 ^ 80418282641092L;
                                    var17_10 = v0 ^ 39740011465668L;
                                    v2 = Component.d();
                                    Intrinsics.checkNotNullParameter(var1_1, "context");
                                    var19_11 = v2;
                                    this.animation.animate(this.closing == false ? 1.0f : 0.0f);
                                    var20_12 = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - this.x / 2.0f;
                                    var21_13 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - this.y / 2.0f - 50.0f;
                                    var1_1.getMatrices().pushMatrix();
                                    var1_1.getMatrices().translate(var20_12 + this.x / 2.0f, var21_13 + this.y / 2.0f);
                                    var1_1.getMatrices().scale(Math.clamp(this.animation.getValue(), 0.0f, 2.0f), Math.clamp(this.animation.getValue(), 0.0f, 2.0f));
                                    var1_1.getMatrices().translate(-(var20_12 + this.x / 2.0f), -(var21_13 + this.y / 2.0f));
                                    v3 = var1_1.getMatrices();
                                    Intrinsics.checkNotNullExpressionValue(v3, "pose(...)");
                                    Shapes2D.drawShape$default(v3, (char)var10_6, var20_12, var21_13, this.x, this.y, 7.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var11_7, 0.0f, var12_8, 0.0f, 14336, null);
                                    v4 = var1_1.getMatrices();
                                    Intrinsics.checkNotNullExpressionValue(v4, "pose(...)");
                                    var22_14 = new Color[]{new Color(0x44FFFFFF, true)};
                                    RenderUtil.INSTANCE.drawGradient(v4, var20_12 + 6.0f, var21_13 + 24.0f, this.x - 12.0f, 0.5f, var22_14, var17_10);
                                    var22_15 = var20_12 + this.x - 25.0f;
                                    var23_16 = var20_12 + this.x - 5.0f;
                                    var24_17 = var2_2;
                                    v5 = var22_15 == var24_17 ? 0 : (var22_15 < var24_17 ? -1 : 1);
                                    if (var19_11) ** GOTO lbl39
                                    if (v5 <= 0) {
                                        v5 = var24_17 == var23_16 ? 0 : (var24_17 < var23_16 ? -1 : 1);
lbl39:
                                        // 2 sources

                                        if (!var19_11) {
                                            v5 = v5 <= 0 ? (float)true : (float)false;
                                        }
                                    } else {
                                        v5 = (float)false;
                                    }
                                    if (v5 == false) break block14;
                                    var22_15 = var21_13 + 5.0f;
                                    var23_16 = var21_13 + 25.0f;
                                    var24_17 = var3_3;
                                    v6 = var22_15 == var24_17 ? 0 : (var22_15 < var24_17 ? -1 : 1);
                                    if (var19_11) ** GOTO lbl51
                                    if (v6 <= 0) {
                                        v6 = var24_17 == var23_16 ? 0 : (var24_17 < var23_16 ? -1 : 1);
lbl51:
                                        // 2 sources

                                        if (!var19_11) {
                                            v6 = v6 <= 0 ? (float)true : (float)false;
                                        }
                                    } else {
                                        v6 = (float)false;
                                    }
                                    if (v6 == false) break block14;
                                    v7 = var1_1.getMatrices();
                                    Intrinsics.checkNotNullExpressionValue(v7, "pose(...)");
                                    v8 = Theme.INSTANCE.g().brighter();
                                    Intrinsics.checkNotNullExpressionValue(v8, "brighter(...)");
                                    Shapes2D.drawShape$default(v7, (char)var10_6, var20_12 + this.x - 25.0f, var21_13 + 5.0f, 20.0f, 15.0f, 4.0f, 2.0f, 0.0f, 1.0f, v8, Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var11_7, 0.0f, var12_8, 0.0f, 14336, null);
                                    v9 = ClickGui.INSTANCE.r();
                                    v10 = var1_1.getMatrices();
                                    Intrinsics.checkNotNullExpressionValue(v10, "pose(...)");
                                    RenderTarget.drawRoundedText$default(v9, (Matrix3x2f)v10, (double)(var20_12 + this.x) - 19.5, (double)var21_13 + 5.5, 0.0f, 0.0f, Theme.INSTANCE.getTextColor(), null, 0.0f, 216, var15_9, null);
                                    if (!var19_11) break block15;
                                    Module.zkmSetFlag(new Module[1]);
                                }
                                v11 = var1_1.getMatrices();
                                Intrinsics.checkNotNullExpressionValue(v11, "pose(...)");
                                Shapes2D.drawShape$default(v11, (char)var10_6, var20_12 + this.x - 25.0f, var21_13 + 5.0f, 20.0f, 15.0f, 4.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.g(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)var11_7, 0.0f, var12_8, 0.0f, 14336, null);
                                v12 = ClickGui.INSTANCE.r();
                                v13 = var1_1.getMatrices();
                                Intrinsics.checkNotNullExpressionValue(v13, "pose(...)");
                                RenderTarget.drawRoundedText$default(v12, (Matrix3x2f)v13, (double)(var20_12 + this.x) - 19.5, (double)var21_13 + 5.5, 0.0f, 0.0f, Theme.INSTANCE.getInactiveTextColor(), null, 0.0f, 216, var15_9, null);
                            }
                            this.render(var1_1, var8_5, var2_2, var3_3);
                            var1_1.getMatrices().popMatrix();
                            v14 = this.closing;
                            if (var19_11) break block16;
                            if (v14 == 0) break block17;
                            v15 = this;
                            if (var19_11) break block18;
                            cfr_temp_0 = v15.animation.getValue() - 0.0f;
                            v14 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                        }
                        if (v14 > 0) break block17;
                        v16 = ItemPresetEditor.Companion;
                        if (var19_11) break block19;
                        v15 = v16.G();
                    }
                    if (v15 != null) break block20;
                    ClickGuiScreen.INSTANCE.setComponent(null);
                    if (!var19_11) break block17;
                }
                v16 = ItemPresetEditor.Companion;
            }
            v16.X(null);
        }
    }

    public void render(@NotNull DrawContext drawContext, long l, int n, int n2) {
        Intrinsics.checkNotNullParameter(drawContext, "context");
    }

    public void updatePosition(long l, int n, int n2) {
        block1: {
            float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - this.x / 2.0f;
            float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f - this.y / 2.0f - 50.0f;
            float f3 = f + this.x - 25.0f;
            float f4 = f + this.x - 5.0f;
            float f5 = n;
            boolean bl = f3 <= f5 ? f5 <= f4 : false;
            if (!bl) break block1;
            f3 = f2 + 5.0f;
            f4 = f2 + 25.0f;
            f5 = n2;
            boolean bl2 = f3 <= f5 ? f5 <= f4 : false;
            if (bl2) {
                this.closing = true;
            }
        }
    }

    public void close() {
    }

    public void keyPressed(int n, long l) {
        block0: {
            if (n != 256) break block0;
            this.closing = true;
        }
    }

    public void charTyped(char c) {
    }

    public void mouseScrolled(double d, double d2, double d3) {
        this.E += (float)d3 * 10.0f;
        this.E = Math.min(this.E, 0.0f);
    }

    public static void zkmSetFlag(boolean bl) {
        l = bl;
    }

    public static boolean boolean_zkmGetFlag() {
        return l;
    }

    public static boolean d() {
        return false;
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
                            Component.b = ZkmKeys.a(8999904684272948522L, 4959297707352954089L, MethodHandles.lookup().lookupClass()).a(4488487208104L);
                            var25 = Component.b ^ 42669825080177L;
                            Component.j = new HashMap<K, V>(13);
                            Component.zkmSetFlag(true);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[5];
                            var21_4 = 0;
                            var20_5 = "O\u00cd\u00e0\u0013\u000e\u000e0\tm3>\u00df\u007fg2\u008c\u0089B\u00b1\u0097]6?\u00a1 z\u00d8\u00fc\u00ba\u00bb\u0010=L\f(\u0002\u00eai\u00bf\u0004x6eOV}\u00b2b{\u00da\u00d6x\u0099(!\u007f\u009c Y\u0098\u0002\u0093\u00e8\u00b1P\u00bf\u0087\u0086\u00b5\u0000xU\u00f8}\u00b8'e'<\u00e9\u00b9\u0080\u00c8\u001a\u008bv\u00fd\u00a4\u0004\u00db";
                            var22_6 = 90;
                            var19_7 = 24;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = Component.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u00a0\u00c7\u00b5\u0007\u00c7\u00d1\u00aa, \u00b2\u00b5kp\bu\u00beg\u0005\u00a4\u00a3\u00b4@\u0017.\u0088s\u001a\u0096\u00fc\u0006\u0083\u00c8\u0010\u008a\fx\u000fb\u00e0N6\u00e4\u000ex\u008cl\u001d\u0004\u0080";
                                var22_6 = 49;
                                var19_7 = 32;
                                var18_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = Component.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
                        switch (v5) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl52:
                            // 1 sources

                            ** continue;
                        }
                    }
                    Component.f = var23_3;
                    Component.h = new String[5];
                    Component.z = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[11];
                    var8_13 = 0;
                    var9_14 = "\u00c8\u00f9\u008b\u00fe\u00e09CL\u00f2\u00885C\u0014\u0091Q\u00cf_\u00cb\u00ffOGbv\u00b9`K\u0098C(\u0013\u00a3e\u00eb5\u00b0\u00e0Q\u00ca\u00f0\u00eb\u00e4\n^\u00c2\r\u0091\u00c22\u001c\u0002\u00fe\u0091'z\u00d6\u001e\u00b5\u00c2\u009f\u009b\u0001<D^\u0090\u0094L\u00c2b_\u0019,";
                    var10_15 = 72;
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
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u00fae'\u00f8K\u00adWe\u00d1\u00bad\u00ed(JV\u00c5";
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
lbl92:
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
lbl105:
                    // 1 sources

                    ** continue;
                }
            }
            Component.t = var11_12;
            Component.x_obf = new Integer[11];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-22, 62, 41, 85, 27, -39, 25, 61});
        ** while (true)
        Component.G = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x39C7;
        if (h[n2] == null) {
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
                throw new RuntimeException("su/catlean/dj", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = f[n2].getBytes("ISO-8859-1");
            Component.h[n2] = Component.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Component.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x492A;
        if (x_obf[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = t[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])z.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    z.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Component.x_obf[n2] = n3;
        }
        return x_obf[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Component.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/dj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Component.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Component.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

