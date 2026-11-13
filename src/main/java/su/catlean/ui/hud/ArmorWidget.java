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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.render.Gradient2D;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.ArmorUtil;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ArmorWidget
extends HudElement {
    @NotNull
    public static final ArmorWidget E;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private ArmorWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x4DD5B9E4EE7AL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "armor-widget", (char)n2);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        long l2 = l;
        long l3 = l2 ^ 0x5EAEB98FBCE1L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 32);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x220AF791B99AL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        int n4 = 2;
        HudElement hudElement = this;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        HudElement.drawBackground$default(hudElement, matrix3x2fStack, this.getX(), (short)n, n2, this.getY(), 81.0f, 22.0f, (short)n3, 1.0f, false, 64, null);
        drawContext.getMatrices().pushMatrix();
        drawContext.getMatrices().translate(this.getX(), this.getY());
        for (ItemStack itemStack : ArmorUtil.getArmorItems(Globals.getPlayer())) {
            if (n4 < 43) {
                HudElement hudElement2 = this;
                Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                HudElement.queueShadow$default(hudElement2, matrix3x2fStack2, (float)n4 + 18.0f, 7.0f, 0.0f, null);
            }
            if (!itemStack.isEmpty()) {
                drawContext.drawItem(itemStack, n4, 0);
            }
            Color color = Theme.INSTANCE.getGradientColor(n4);
            Color color2 = Theme.INSTANCE.getGradientColor(n4 * 2);
            Color color3 = RenderUtil.INSTANCE.withAlpha(color, 0.4f);
            Color color4 = RenderUtil.INSTANCE.withAlpha(color2, 0.4f);
            float f = n4;
            Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
            Gradient2D.drawGradientRect(f, 15.0f, l4, 16.0f, 4.0f, 1.5f, color3, color3, color4, color4, matrix3x2fStack3, 1.0f, color2);
            float f2 = MathHelper.clamp(16.0f - (float)itemStack.getDamage() * 16.0f / (float)itemStack.getMaxDamage() + 2.0f, 0.0f, 16.0f);
            float f3 = n4;
            Matrix3x2fStack matrix3x2fStack4 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack4, "pose(...)");
            Gradient2D.drawGradientRect(f3, 15.0f, l4, f2, 4.0f, 1.5f, color, color, color2, color2, matrix3x2fStack4, 1.0f, color2);
            n4 += 20;
        }
        drawContext.getMatrices().popMatrix();
        this.setSize(81.0f, 22.0f);
    }

    @Override
    public void renderOverlay(@NotNull DrawContext drawContext, long l) {
        long l2 = l ^ 0x606474FAB0C7L;
        boolean bl = ArrayListEntry.u();
        Intrinsics.checkNotNullParameter(drawContext, "context");
        boolean bl2 = bl;
        int n = 2;
        for (ItemStack itemStack : ArmorUtil.getArmorItems(Globals.getPlayer())) {
            Color color = Theme.INSTANCE.getGradientColor(n);
            Color color2 = Theme.INSTANCE.getGradientColor(n * 2);
            Color color3 = RenderUtil.INSTANCE.withAlpha(color, 0.4f);
            Color color4 = RenderUtil.INSTANCE.withAlpha(color2, 0.4f);
            float f = this.getX() + (float)n;
            float f2 = this.getY() + 15.0f;
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Gradient2D.drawGradientRect(f, f2, l2, 16.0f, 4.0f, 1.5f, color3, color3, color4, color4, matrix3x2fStack, 1.0f, color2);
            float f3 = MathHelper.clamp(16.0f - (float)itemStack.getDamage() * 16.0f / (float)itemStack.getMaxDamage() + 2.0f, 0.0f, 16.0f);
            float f4 = this.getX() + (float)n;
            float f5 = this.getY() + 15.0f;
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            Gradient2D.drawGradientRect(f4, f5, l2, f3, 4.0f, 1.5f, color, color, color2, color2, matrix3x2fStack2, 1.0f, color2);
            n += 20;
            if (!bl2) continue;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        ArmorWidget.a = ZkmKeys.a(1298231476749915300L, -4847344134490140125L, MethodHandles.lookup().lookupClass()).a(188377721223637L);
                        var20 = ArmorWidget.a ^ 131444658977476L;
                        var22_1 = var20 ^ 106611759717941L;
                        ArmorWidget.f = new HashMap<K, V>(13);
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
                        var18_4 = new String[5];
                        var16_5 = 0;
                        var15_6 = "\u001c\u00a8)k\t\u0084\u00c6\u0013\u0019\u001b\n=\u00e6\u0092P\u00c0\u0018\u0082\u0012Y\u00ea\u009c\u00c1\u00f1\u00ea\u00dd\u000eSa\u0016\u0091\u00b6\u001a\u00f8F\u000bayb\u009b\u0005\u0010F\u00bcp\u00ab\u00c6\u00e19\u0083\u0016N[\u0081'\u001f,\u00a4";
                        var17_7 = 58;
                        var14_8 = 16;
                        var13_9 = -1;
lbl22:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_9;
                            v4 = var15_6.substring(v3, v3 + var14_8);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl27:
                        // 1 sources

                        while (true) {
                            var18_4[var16_5++] = ArmorWidget.zkmDecodeUtf8(var19_10).intern();
                            if ((var13_9 += var14_8) < var17_7) {
                                var14_8 = var15_6.charAt(var13_9);
                                ** continue;
                            }
                            var15_6 = "\u00a7\u00ac=4\u00a3\u00dd\u00d0'\u0011t!?x\u009b@{\u00ce\u008f\u00c6J\u00dc\u00bdF\u00d2\u00c3\u00df\u00d3\u00f6\u001a\u00ba\b\u00b7 \u00d7\u00f2^\u008f\u000f\u00ad\u0015\u00a9O\u009b\u00fb\u00c99\u009e\u001aZ\u00c3\u0019\u00f1\u0087\u0087H0\u0010\u000f\u0089\u0095Ac\u00e7(\u009c";
                            var17_7 = 65;
                            var14_8 = 32;
                            var13_9 = -1;
lbl36:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_9;
                                v4 = var15_6.substring(v6, v6 + var14_8);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl41:
                        // 1 sources

                        while (true) {
                            var18_4[var16_5++] = ArmorWidget.zkmDecodeUtf8(var19_10).intern();
                            if ((var13_9 += var14_8) < var17_7) {
                                var14_8 = var15_6.charAt(var13_9);
                                ** continue;
                            }
                            break block14;
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
                ArmorWidget.b = var18_4;
                ArmorWidget.c = new String[5];
                ArmorWidget.i = new HashMap<K, V>(13);
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
                var6_13 = new long[3];
                var3_14 = 0;
                var4_15 = "q\u0019\u00d0\u009el3\u00a8\r\u00ab\u00ddf\u00be\u00e5\u00ed\u0084\u00a1\u00eb-0\u00ed\"\t\u0099\u00d4";
                var5_16 = 24;
                var2_17 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl75:
                // 1 sources

                while (true) {
                    var6_13[v10] = ((long)var10_20[0] & 255L) << 56 | ((long)var10_20[1] & 255L) << 48 | ((long)var10_20[2] & 255L) << 40 | ((long)var10_20[3] & 255L) << 32 | ((long)var10_20[4] & 255L) << 24 | ((long)var10_20[5] & 255L) << 16 | ((long)var10_20[6] & 255L) << 8 | (long)var10_20[7] & 255L;
                    if (var2_17 < var5_16) ** continue;
                    break block16;
                    break;
                }
            }
            var7_18 = var4_15.substring(var2_17, var2_17 += 8).getBytes("ISO-8859-1");
            v10 = var3_14++;
            var8_19 = ((long)var7_18[0] & 255L) << 56 | ((long)var7_18[1] & 255L) << 48 | ((long)var7_18[2] & 255L) << 40 | ((long)var7_18[3] & 255L) << 32 | ((long)var7_18[4] & 255L) << 24 | ((long)var7_18[5] & 255L) << 16 | ((long)var7_18[6] & 255L) << 8 | (long)var7_18[7] & 255L;
            var10_20 = var0_11.doFinal(new byte[]{(byte)(var8_19 >>> 56), (byte)(var8_19 >>> 48), (byte)(var8_19 >>> 40), (byte)(var8_19 >>> 32), (byte)(var8_19 >>> 24), (byte)(var8_19 >>> 16), (byte)(var8_19 >>> 8), (byte)var8_19});
            ** while (true)
        }
        ArmorWidget.g = var6_13;
        ArmorWidget.h = new Integer[3];
        ArmorWidget.E = new ArmorWidget(var22_1);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1AD9;
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
                throw new RuntimeException("su/catlean/go", exception);
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
            ArmorWidget.c[n2] = ArmorWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ArmorWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/go" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x41E9;
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
                throw new RuntimeException("su/catlean/go", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ArmorWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ArmorWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/go" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ArmorWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ArmorWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

