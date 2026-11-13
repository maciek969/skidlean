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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Setting;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SettingSlot {
    @NotNull
    private final Setting setting;
    private float x;
    private float y;
    private final int index;
    private boolean Z;
    @NotNull
    private String w;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public SettingSlot(@NotNull Setting setting2, float f, float f2, int n) {
        Intrinsics.checkNotNullParameter(setting2, "setting");
        this.setting = setting2;
        this.x = f;
        this.y = f2;
        this.index = n;
        this.w = "-";
    }

    @NotNull
    public final Setting getSetting() {
        return this.setting;
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

    public final int getIndex() {
        return this.index;
    }

    public final void render(long l, @Nullable Matrix3x2fStack matrix3x2fStack, @NotNull DrawContext drawContext) {
        String string;
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x4E9E2CD64E65L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x31B6940E2E10L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
        Shapes2D.drawShape$default(matrix3x2fStack2, (char)n, this.x + 40.0f, this.y + 10.0f + (float)this.index * 10.0f, 15.0f, 8.0f, 2.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getSlidersColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
        Fonts.getSF_M4().drawStringCentered(drawContext, String.valueOf("RGBA".charAt(this.index)), this.x + 34.0f, this.y + 13.5f + (float)this.index * 10.0f, Theme.INSTANCE.getTextColor(), l4);
        FontRenderer fontRenderer = Fonts.getSF_M4();
        if (this.Z) {
            string = this.w;
        } else {
            Integer[] integerArray = new Integer[]{((Color)this.setting.getValue()).getRed(), ((Color)this.setting.getValue()).getGreen(), ((Color)this.setting.getValue()).getBlue(), ((Color)this.setting.getValue()).getAlpha()};
            string = String.valueOf(((Number)CollectionsKt.listOf(integerArray).get(this.index)).intValue());
        }
        float f = this.Z ? (float)Math.sin((double)System.currentTimeMillis() / 50.0) + 0.5f : 1.0f;
        fontRenderer.drawStringCentered(drawContext, string, this.x + 47.0f, this.y + 13.0f + (float)this.index * 10.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getInactiveTextColor(), f), l4);
    }

    public final void isHovered(int n, int n2) {
        float f = this.x + 40.0f;
        float f2 = this.x + 55.0f;
        float f3 = n;
        boolean bl = f <= f3 ? f3 <= f2 : false;
        if (bl) {
            f = this.y + 10.0f + (float)this.index * 10.0f;
            f2 = this.y + 18.0f + (float)this.index * 10.0f;
            f3 = n2;
            boolean bl2 = f <= f3 ? f3 <= f2 : false;
            if (bl2) {
                this.Z = !this.Z;
                Integer[] integerArray = new Integer[]{((Color)this.setting.getValue()).getRed(), ((Color)this.setting.getValue()).getGreen(), ((Color)this.setting.getValue()).getBlue(), ((Color)this.setting.getValue()).getAlpha()};
                this.w = String.valueOf(((Number)CollectionsKt.listOf(integerArray).get(this.index)).intValue());
            }
        }
    }

    public final void charTyped(char c) {
        if (this.w.length() < 3 && this.Z) {
            this.w = this.w + c;
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void keyPressed(byte var1_1, int var2_2) {
        block8: {
            block7: {
                var8_3 = 0L;
                var3_4 = ((long)var1_1 << 56 | var8_3 << 8 >>> 8) ^ SettingSlot.a;
                var5_5 = ZkmReferences.zkmBootstrap("\u00c5", (long)2481776690974942700L, (long)var3_4);
                v0 = this.Z;
                if (var5_5 == null) break block7;
                if (v0 == 0) break block8;
                v0 = var2_2;
            }
            if (var5_5 == null) ** GOTO lbl15
            switch (v0) {
                case 257: {
                    this.Z = false;
                    try {
                        v0 = MathHelper.clamp(Integer.parseInt(this.w), 0, (int)SettingSlot.zkmBootstrap2("s", (int)16988, (long)(7717688668447274309L ^ var3_4)));
lbl15:
                        // 2 sources

                        var6_6 = v0;
                        var7_7 = (Color)this.setting.getValue();
                        this.setting.setValue(new Color(this.index == 0 ? var6_6 : var7_7.getRed(), this.index == 1 ? var6_6 : var7_7.getGreen(), this.index == 2 ? var6_6 : var7_7.getBlue(), this.index == 3 ? var6_6 : var7_7.getAlpha()));
                        break;
                    }
                    catch (Exception v1) {
                        if (var1_1 >= 0 && var5_5 != null) break;
                    }
                }
                case 259: {
                    v2 = ((CharSequence)this.w).length();
                    if (var5_5 != null) {
                        v2 = v2 > 0 ? 1 : 0;
                    }
                    if (v2 == 0) break;
                    v3 = this.w.substring(0, this.w.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(v3, (String)SettingSlot.zkmBootstrap("v", (int)14392, (long)(7533828191792028538L ^ var3_4)));
                    this.w = v3;
                    break;
                }
            }
        }
    }

    public final boolean mouseScrolled(double d, double d2, double d3) {
        float f = this.x + 40.0f;
        float f2 = this.x + 55.0f;
        float f3 = (float)d;
        boolean bl = f <= f3 ? f3 <= f2 : false;
        if (bl) {
            f = this.y + 10.0f + (float)this.index * 10.0f;
            f2 = this.y + 18.0f + (float)this.index * 10.0f;
            f3 = (float)d2;
            boolean bl2 = f <= f3 ? f3 <= f2 : false;
            if (bl2) {
                Iterable iterable;
                Object object = new Integer[]{((Color)this.setting.getValue()).getRed(), ((Color)this.setting.getValue()).getGreen(), ((Color)this.setting.getValue()).getBlue(), ((Color)this.setting.getValue()).getAlpha()};
                List<Integer> list = CollectionsKt.listOf(object);
                Iterable iterable2 = iterable = (Iterable)list;
                Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                int n = 0;
                for (Object t : iterable2) {
                    int n2;
                    if ((n2 = n++) < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    int n3 = ((Number)t).intValue();
                    int n4 = n2;
                    Collection collection2 = collection;
                    collection2.add(n4 == this.index ? MathHelper.clamp(n3 + (int)d3, 0, 255) : n3);
                }
                object = (List)collection;
                this.setting.setValue(new Color(((Number)object.get(0)).intValue(), ((Number)object.get(1)).intValue(), ((Number)object.get(2)).intValue(), ((Number)object.get(3)).intValue()));
                return true;
            }
        }
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SettingSlot.a = ZkmKeys.a(-1535281206626056300L, 4328898319864735754L, MethodHandles.lookup().lookupClass()).a(101460224601739L);
                        SettingSlot.d = new HashMap<K, V>(13);
                        var11 = SettingSlot.a ^ 129482475997912L;
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
                        var20_3 = new String[5];
                        var18_4 = 0;
                        var17_5 = "\u00b71\u0006J\u008e\u00dc;9\u00cb\u0016\u00c3\u0093I]L\u0011\u00106\u009c\u00f7\u00df\u000b\u00c2\u00cd\u00c6:\u001b\u00d7*9\\\u0007\u00ea\u0018;\u00d0\u00c0\u00bc\u00f8;\u00bae\u00fb\u008d\u00e1\u00f4\\\u0094\b#\u0012N\u00f3p\r\u00dc\u001cv";
                        var19_6 = 58;
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
                            var20_3[var18_4++] = SettingSlot.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00f0Q4\u0006\u0091\u00c7L\u00deA\u00e5\u00d8\u0005\u0019\u0083\"?\u0018K\u00dd\u00f1\u00a9\nvU\\\\0\u00db\u000e\u00f6\u00cd\u00f9#\u000b\u00b2\u00ff\u00ba6\u00cbU`";
                            var19_6 = 41;
                            var16_7 = 16;
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
                            var20_3[var18_4++] = SettingSlot.zkmDecodeUtf8(var21_9).intern();
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
                SettingSlot.b = var20_3;
                SettingSlot.c = new String[5];
                SettingSlot.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u00cb\u009c\u00a6\u00e0\u001c\u00b6\u00c1\u00d5\u00dc\u00b3\u00de\u009a0Y\u00a3\u00af";
                var5_15 = 16;
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
                    var4_14 = "\u0013)Q\u00e1\u000b\b\u00fd|\u00dbXqx\u0080\u00c8\u00fb\u00ff";
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
        SettingSlot.e = var6_12;
        SettingSlot.f = new Integer[4];
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x402F;
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
                throw new RuntimeException("su/catlean/b7", exception);
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
            SettingSlot.c[n2] = SettingSlot.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingSlot.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/b7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1471;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/b7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SettingSlot.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SettingSlot.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/b7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingSlot.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SettingSlot.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

