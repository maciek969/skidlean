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
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.config.ModuleConfig;
import su.catlean.macro.Macro;
import su.catlean.macro.MacroManager;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyNames;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;

public final class MacroWidget
extends HudElement {
    @NotNull
    public static final MacroWidget G;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private MacroWidget(byte by, int n, int n2) {
        long l = ((long)by << 56 | (long)n << 32 >>> 8 | (long)n2 << 40 >>> 40) ^ a;
        long l2 = l ^ 0x431DE2D60E78L;
        int n3 = (int)(l2 >>> 48);
        int n4 = (int)(l2 << 48 >>> 48);
        super((char)n3, "macro-widget", (char)n4);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        Object object4;
        long l2 = l;
        int n = (int)((l2 ^ 0x5154D64DAE08L) << 48 >>> 48);
        long l3 = l2 ^ 0x5EAEB98FBCE1L;
        int n2 = (int)(l3 >>> 48);
        int n3 = (int)(l3 << 16 >>> 32);
        int n4 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x7CE5E97F0C88L;
        int n5 = (int)(l4 >>> 48);
        int n6 = (int)(l4 << 16 >>> 48);
        int n7 = (int)(l4 << 32 >>> 32);
        long l5 = l2 ^ 0x3A962FE8946EL;
        int n8 = (int)(l5 >>> 48);
        long l6 = l5 << 16 >>> 16;
        long l7 = l2 ^ 0x74C22041CFCEL;
        long l8 = l2 ^ 0x1258F934B109L;
        long l9 = l2 ^ 0x1522A9F4B09FL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        float f = this.getX();
        float f2 = this.getY();
        float f3 = 0.0f;
        Object object2 = object4 = (Iterable)MacroManager.INSTANCE.getMacros();
        Collection collection = new ArrayList();
        Object object3 = object2.iterator();
        while (object3.hasNext()) {
            Object t = object3.next();
            Macro macro = (Macro)t;
            if (!(macro.isEnabled() && macro instanceof ModuleConfig)) continue;
            collection.add(t);
        }
        for (Object object4 : (List)collection) {
            Intrinsics.checkNotNull(object4, "null cannot be cast to non-null type su.catlean.client.services.client.macro.ItemMacro");
            ModuleConfig moduleConfig = (ModuleConfig)object4;
            if (MacroManager.INSTANCE.runByName(moduleConfig.getItem()) == null) continue;
            Item[] itemArray = new Item[]{object2};
            boolean bl = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n8, l6).isFound();
            int n9 = moduleConfig.getBind().getKey();
            char c = (char)n;
            boolean bl2 = KeyboardUtil.isKeyPressed(c, n9);
            object3 = KeyNames.INSTANCE.getKeyName(moduleConfig.getBind().getKey());
            float f4 = Fonts.getSF_M().getWidth(l8, (String)object3) + 22.0f;
            boolean bl3 = Globals.getPlayer().getItemCooldownManager().isCoolingDown(((Item)object2).getDefaultStack());
            HudElement hudElement = this;
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            HudElement.drawBackground$default(hudElement, matrix3x2fStack, f, (short)n2, n3, f2, f4, 16.0f, (short)n4, 0.0f, false, 96, null);
            if (bl3) {
                float f5 = Globals.getPlayer().getItemCooldownManager().getCooldownProgress(((Item)object2).getDefaultStack(), RenderUtil.INSTANCE.getTickDelta());
                Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                char c2 = (char)n5;
                Color color = Color.RED;
                Intrinsics.checkNotNullExpressionValue(color, "RED");
                Shapes2D.drawShape$default(matrix3x2fStack2, c2, f, f2 + 16.0f * (1.0f - f5), f4, 16.0f * f5, 3.0f, 0.0f, 0.0f, 1.0f, color, Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n6, 0.0f, n7, 0.0f, 14336, null);
            }
            drawContext.getMatrices().pushMatrix();
            drawContext.getMatrices().translate(f + 2.0f, f2 + 2.0f);
            drawContext.getMatrices().scale(0.7f);
            drawContext.drawItem(((Item)object2).getDefaultStack(), 0, 0);
            drawContext.getMatrices().popMatrix();
            Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
            Object object5 = new Color[]{new Color(0x44FFFFFF, true)};
            RenderUtil.INSTANCE.drawGradient(matrix3x2fStack3, f + 14.0f, f2 + 4.0f, 0.5f, 9.0f, (Color[])object5, l9);
            object5 = bl2 ? Theme.INSTANCE.getInactiveTextColor() : (bl ? Theme.INSTANCE.getTextColor() : Color.RED);
            Intrinsics.checkNotNull(object5);
            this.drawString(drawContext, (String)object3, f + 18.0f, l7, f2 + 3.0f, (Color)object5);
            f += f4 + 2.0f;
            f3 += f4 + 2.0f;
        }
        this.setSize(f3, 16.0f);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        MacroWidget.a = ZkmKeys.a(-4030466965672635098L, 5267453608353006125L, MethodHandles.lookup().lookupClass()).a(137591095079909L);
                        var20 = MacroWidget.a ^ 35488116036715L;
                        v0 = var20 ^ 63723483664536L;
                        var22_1 = (int)(v0 >>> 56);
                        var23_2 = (int)(v0 << 8 >>> 32);
                        var24_3 = (int)(v0 << 40 >>> 40);
                        MacroWidget.f = new HashMap<K, V>(13);
                        var11_4 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v1 = SecretKeyFactory.getInstance("DES");
                        v2 = new byte[8];
                        v3 = v2;
                        v2[0] = (byte)(var20 >>> 56);
                        for (var12_5 = 1; var12_5 < 8; ++var12_5) {
                            v3 = v3;
                            v3[var12_5] = (byte)(var20 << var12_5 * 8 >>> 56);
                        }
                        var11_4.init(2, (Key)v1.generateSecret(new DESKeySpec(v3)), new IvParameterSpec(new byte[8]));
                        var18_6 = new String[6];
                        var16_7 = 0;
                        var15_8 = "\u0017\u00de9L>\u00b1Z\u00d3\u00f6\u00bf\u00e9\u0007X\u00b4\u001c'\u00d2\u00ee\u0098\u0088\u0082\u0096\u00ef\u0002\u00c5\u00bd<\u00d9\u00d0Y\u00b9\u0089\u0010\u00ddR\u00f6\u00d6V.068\u0018V\u00e2\u00dc\u0084\u0080a\u0088\u00e6\f_\u00bb\u0091\u00fe\u0000\u001f5\u000f>\u0087j\u00af*9\u00d8\u00a2\u0012\u009d\\\u0003\u0011\u0091\u00ff\u0015\u0080A]\u001dd=I\u000b\u00faa\u00c9\u00cb@\u00a6\u00fe\u00a6\u00b4\u0092f\u0007w8^e\u00d1\u009bj<\u00a2\u00c1\u00c2S\u00c3\u001f\u00c1\u00a3\u009cP\u00a1\u00d4&\u008cz\u00e5z\u00aa\n7\u00b8\u00c4\u0084\u00ef\u00bc\u0096\u00b1:\u008a\u0016\u00ee\u0002\u00c2\u00e4\u0016-d\u00a6<$z\u00bb\u0097~\u00bf\u0085\u0019\u00d6\u00b5NF\u00d7\u0019\u0082\u0080K+\u008d\u00c4K\u0003\u008aR\u00aa\u00e4\u001e\u00b3L\u0013\u00811\u00a0\u00adh>\u00b1[\u00adm\u00cb\u00d6{\u0010\u001e\u0014l\u00a2U\u00e1J\u0014\u00c7\u00a8_b#\u00af\u00e4\u00ee";
                        var17_9 = 203;
                        var14_10 = 32;
                        var13_11 = -1;
lbl26:
                        // 2 sources

                        while (true) {
                            v4 = ++var13_11;
                            v5 = var15_8.substring(v4, v4 + var14_10);
                            v6 = -1;
                            break block18;
                            break;
                        }
lbl31:
                        // 1 sources

                        while (true) {
                            var18_6[var16_7++] = MacroWidget.zkmDecodeUtf8(var19_12).intern();
                            if ((var13_11 += var14_10) < var17_9) {
                                var14_10 = var15_8.charAt(var13_11);
                                ** continue;
                            }
                            var15_8 = "\u00f9\u0097\u0088\u0096\u001f\u0010f\t\u00e5\u000fr\u0011l\u00df\u00e0(\u009f\u00be1{\u0091\u0000\u00e2C\u00f6\u00d4x\u000bS\u00a5\u00ae+\u0018\u0012\u007f\u00bb\u0097J\u00eew\u0093\u00acGq\u00bf\u00a3\u00fcf5'uq\u00ae\u0004.O\u00f1";
                            var17_9 = 57;
                            var14_10 = 32;
                            var13_11 = -1;
lbl40:
                            // 2 sources

                            while (true) {
                                v7 = ++var13_11;
                                v5 = var15_8.substring(v7, v7 + var14_10);
                                v6 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl45:
                        // 1 sources

                        while (true) {
                            var18_6[var16_7++] = MacroWidget.zkmDecodeUtf8(var19_12).intern();
                            if ((var13_11 += var14_10) < var17_9) {
                                var14_10 = var15_8.charAt(var13_11);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_12 = var11_4.doFinal(v5.getBytes("ISO-8859-1"));
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
                MacroWidget.b = var18_6;
                MacroWidget.c = new String[6];
                MacroWidget.i = new HashMap<K, V>(13);
                var0_13 = Cipher.getInstance("DES/CBC/NoPadding");
                v8 = SecretKeyFactory.getInstance("DES");
                v9 = new byte[8];
                v10 = v9;
                v9[0] = (byte)(var20 >>> 56);
                for (var1_14 = 1; var1_14 < 8; ++var1_14) {
                    v10 = v10;
                    v10[var1_14] = (byte)(var20 << var1_14 * 8 >>> 56);
                }
                var0_13.init(2, (Key)v8.generateSecret(new DESKeySpec(v10)), new IvParameterSpec(new byte[8]));
                var6_15 = new long[4];
                var3_16 = 0;
                var4_17 = "\u00f26D\u001d\u00e8\u00b5\u00f5\u00c7F`\u0005&f\u000f\u00dc\u00a1";
                var5_18 = 16;
                var2_19 = 0;
                while (true) {
                    var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                    v11 = var6_15;
                    v12 = var3_16++;
                    v13 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                    v14 = -1;
                    break block20;
                    break;
                }
lbl84:
                // 1 sources

                while (true) {
                    v11[v12] = v15;
                    if (var2_19 < var5_18) ** continue;
                    var4_17 = "\u00fd\u00f39\u00c9i\u00f4;y'\u00c9\u00f3\u00f6\u00ec\u000e\u00a0\u0012";
                    var5_18 = 16;
                    var2_19 = 0;
                    while (true) {
                        var7_20 = var4_17.substring(var2_19, var2_19 += 8).getBytes("ISO-8859-1");
                        v11 = var6_15;
                        v12 = var3_16++;
                        v13 = ((long)var7_20[0] & 255L) << 56 | ((long)var7_20[1] & 255L) << 48 | ((long)var7_20[2] & 255L) << 40 | ((long)var7_20[3] & 255L) << 32 | ((long)var7_20[4] & 255L) << 24 | ((long)var7_20[5] & 255L) << 16 | ((long)var7_20[6] & 255L) << 8 | (long)var7_20[7] & 255L;
                        v14 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl97:
                // 1 sources

                while (true) {
                    v11[v12] = v15;
                    if (var2_19 < var5_18) ** continue;
                    break block21;
                    break;
                }
            }
            var8_21 = v13;
            var10_22 = var0_13.doFinal(new byte[]{(byte)(var8_21 >>> 56), (byte)(var8_21 >>> 48), (byte)(var8_21 >>> 40), (byte)(var8_21 >>> 32), (byte)(var8_21 >>> 24), (byte)(var8_21 >>> 16), (byte)(var8_21 >>> 8), (byte)var8_21});
            v15 = ((long)var10_22[0] & 255L) << 56 | ((long)var10_22[1] & 255L) << 48 | ((long)var10_22[2] & 255L) << 40 | ((long)var10_22[3] & 255L) << 32 | ((long)var10_22[4] & 255L) << 24 | ((long)var10_22[5] & 255L) << 16 | ((long)var10_22[6] & 255L) << 8 | (long)var10_22[7] & 255L;
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
        MacroWidget.g = var6_15;
        MacroWidget.h = new Integer[4];
        MacroWidget.G = new MacroWidget((byte)var22_1, var23_2, var24_3);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3390;
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
                throw new RuntimeException("su/catlean/pw", exception);
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
            MacroWidget.c[n2] = MacroWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MacroWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4555;
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
                throw new RuntimeException("su/catlean/pw", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MacroWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MacroWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MacroWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MacroWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

