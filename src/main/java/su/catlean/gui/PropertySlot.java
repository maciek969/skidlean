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
import kotlin.reflect.KMutableProperty0;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.Fonts;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PropertySlot {
    @NotNull
    private KMutableProperty0 setting;
    private float x;
    private float y;
    private final int index;
    private boolean Y;
    @NotNull
    private String w;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    public PropertySlot(@NotNull KMutableProperty0 kMutableProperty0, float f, float f2, int n) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 56 | (long)n3 << 32 >>> 8 | (long)n4 << 40 >>> 40) ^ a;
        Intrinsics.checkNotNullParameter(kMutableProperty0, (String)((Object)PropertySlot.zkmBootstrap("k", (int)8563, (long)(0x35C8B8C7AA801828L ^ l))));
        this.setting = kMutableProperty0;
        this.x = f;
        this.y = f2;
        this.index = n;
        this.w = "-";
    }

    @NotNull
    public final KMutableProperty0 getSetting() {
        return this.setting;
    }

    public final void setSetting(@NotNull KMutableProperty0 kMutableProperty0) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(kMutableProperty0, (String)((Object)PropertySlot.zkmBootstrap("k", (int)6038, (long)(0x4E417934ADF3BE0AL ^ l))));
        this.setting = kMutableProperty0;
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

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void render(char var1_1, @Nullable Matrix3x2fStack var2_2, int var3_3, char var4_4, @NotNull DrawContext var5_5) {
        v0 = var6_6 = ((long)var1_1 << 48 | (long)var3_3 << 32 >>> 16 | (long)var4_4 << 48 >>> 48) ^ PropertySlot.a;
        v1 = v0 ^ 73961972826175L;
        var8_7 = (int)(v1 >>> 48);
        var9_8 = (int)(v1 << 16 >>> 48);
        var10_9 = (int)(v1 << 32 >>> 32);
        var13_10 = v0 ^ 66435187892298L;
        v2 = ZkmReferences.zkmBootstrap("\u00c5", (long)-6853791873141161074L, (long)var6_6);
        Intrinsics.checkNotNullParameter(var5_5, (String)PropertySlot.zkmBootstrap("k", (int)543, (long)(9142972847293827873L ^ var6_6)));
        var19_11 = v2;
        v3 = var5_5.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v3, (String)PropertySlot.zkmBootstrap("k", (int)21633, (long)(7019432514030755256L ^ var6_6)));
        Shapes2D.drawShape$default(v3, (char)var8_7, this.x + 40.0f, this.y + 10.0f + (float)this.index * 10.0f, 15.0f, 8.0f, 2.0f, 2.0f, 0.0f, 1.0f, Theme.INSTANCE.getModuleColor(), Theme.INSTANCE.getSlidersColor(), 0.0f, (short)var9_8, 0.0f, var10_9, 0.0f, (int)PropertySlot.zkmBootstrap2("g", (int)10167, (long)(5200957712016370939L ^ var6_6)), null);
        Fonts.getSF_M4().drawStringCentered(var5_5, String.valueOf(PropertySlot.zkmBootstrap("k", (int)4255, (long)(512665452872702375L ^ var6_6)).charAt(this.index)), this.x + 34.0f, this.y + 13.5f + (float)this.index * 10.0f, Theme.INSTANCE.getTextColor(), var13_10);
        v4 = Fonts.getSF_M4();
        v5 = this.Y;
        if (var19_11 == null) ** GOTO lbl23
        if (v5 != 0) {
            v6 = this.w;
        } else {
            v5 = 4;
lbl23:
            // 2 sources

            var20_12 = new Integer[v5];
            var20_12[0] = ((Color)this.setting.get()).getRed();
            var20_12[1] = ((Color)this.setting.get()).getGreen();
            var20_12[2] = ((Color)this.setting.get()).getBlue();
            var20_12[3] = ((Color)this.setting.get()).getAlpha();
            v6 = String.valueOf(((Number)CollectionsKt.listOf(var20_12).get(this.index)).intValue());
        }
        var18_13 = this.Y != false ? (float)Math.sin((double)System.currentTimeMillis() / 50.0) + 0.5f : 1.0f;
        v4.drawStringCentered(var5_5, v6, this.x + 47.0f, this.y + 13.0f + (float)this.index * 10.0f, RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getInactiveTextColor(), var18_13), var13_10);
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
                this.Y = !this.Y;
                Integer[] integerArray = new Integer[]{((Color)this.setting.get()).getRed(), ((Color)this.setting.get()).getGreen(), ((Color)this.setting.get()).getBlue(), ((Color)this.setting.get()).getAlpha()};
                this.w = String.valueOf(((Number)CollectionsKt.listOf(integerArray).get(this.index)).intValue());
            }
        }
    }

    public final void charTyped(char c) {
        block3: {
            block4: {
                PropertySlot propertySlot;
                int n;
                block2: {
                    int n2 = 0;
                    int n3 = 0;
                    int n4 = 0;
                    long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
                    CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2766213397107098896L, (long)l);
                    n = this.w.length();
                    if (callSite == null) break block2;
                    if (n >= 3) break block3;
                    propertySlot = this;
                    if (callSite == null) break block4;
                    n = propertySlot.Y ? 1 : 0;
                }
                if (n == 0) break block3;
                propertySlot = this;
            }
            propertySlot.w = this.w + c;
        }
    }

    public final void keyPressed(int n) {
        if (this.Y) {
            switch (n) {
                case 257: {
                    this.Y = false;
                    try {
                        int n2 = MathHelper.clamp(Integer.parseInt(this.w), 0, 255);
                        Color color = (Color)this.setting.get();
                        this.setting.set(new Color(this.index == 0 ? n2 : color.getRed(), this.index == 1 ? n2 : color.getGreen(), this.index == 2 ? n2 : color.getBlue(), this.index == 3 ? n2 : color.getAlpha()));
                    }
                    catch (Exception exception) {}
                    break;
                }
                case 259: {
                    if (!(((CharSequence)this.w).length() > 0)) break;
                    String string = this.w.substring(0, this.w.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(string, "substring(...)");
                    this.w = string;
                    break;
                }
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void mouseScrolled(double var1_1, long var3_2, double var5_3, double var7_4) {
        block13: {
            var3_2 = PropertySlot.a ^ var3_2;
            var10_5 = this.x + 40.0f;
            var11_7 = this.x + 55.0f;
            var9_9 = ZkmReferences.zkmBootstrap("\u00c5", (long)-2377821276167259028L, (long)var3_2);
            var12_10 = (float)var1_1;
            v0 = var10_5 == var12_10 ? 0 : (var10_5 < var12_10 ? -1 : 1);
            if (var9_9 == null) ** GOTO lbl10
            if (v0 <= 0) {
                v0 = var12_10 == var11_7 ? 0 : (var12_10 < var11_7 ? -1 : 1);
lbl10:
                // 2 sources

                if (var9_9 != null) {
                    v0 = v0 <= 0 ? (float)true : (float)false;
                }
            } else {
                v0 = (float)false;
            }
            if (v0 == false) break block13;
            var10_5 = this.y + 10.0f + (float)this.index * 10.0f;
            var11_7 = this.y + 18.0f + (float)this.index * 10.0f;
            var12_10 = (float)var5_3;
            v1 = var10_5 == var12_10 ? 0 : (var10_5 < var12_10 ? -1 : 1);
            v2 = var9_9;
            if (var3_2 < 0L) ** GOTO lbl25
            if (v2 == null) ** GOTO lbl24
            if (v1 <= 0) {
                v1 = var12_10 == var11_7 ? 0 : (var12_10 < var11_7 ? -1 : 1);
lbl24:
                // 2 sources

                v2 = var9_9;
lbl25:
                // 2 sources

                if (v2 != null) {
                    v1 = v1 <= 0 ? (float)true : (float)false;
                }
            } else {
                v1 = (float)false;
            }
            if (v1 == false) break block13;
            var11_8 = new Integer[]{((Color)this.setting.get()).getRed(), ((Color)this.setting.get()).getGreen(), ((Color)this.setting.get()).getBlue(), ((Color)this.setting.get()).getAlpha()};
            var10_6 = CollectionsKt.listOf(var11_8);
            var14_12 = var12_11 = (Iterable)var10_6;
            var15_13 = new ArrayList<E>(CollectionsKt.collectionSizeOrDefault(var12_11, (int)PropertySlot.zkmBootstrap2("g", (int)25493, (long)(2184088911504004922L ^ var3_2))));
            var17_14 = 0;
            block0: for (Collection<Object> v3 : var14_12) {
                do {
                    block14: {
                        var19_16 = v3;
                        v3 = var15_13;
                        var20_17 = var17_14++;
                        if (var9_9 == null) break block0;
                        v4 = var20_17;
                        v5 = var9_9;
                        if (var3_2 > 0L) {
                            if (v5 != null) {
                                if (v4 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                v4 = var20_17;
                            }
                            v5 = var19_16;
                        }
                        var21_18 = ((Number)v5).intValue();
                        var22_19 = v4;
                        var24_20 = v3;
                        v6 = var22_19;
                        if (var3_2 < 0L) break block14;
                        v7 = this.index;
                        if (var9_9 == null) ** GOTO lbl59
                        if (v6 == v7) {
                            v8 = var21_18 + (int)var7_4;
                            v7 = 0;
lbl59:
                            // 2 sources

                            v6 = MathHelper.clamp(v8, v7, (int)PropertySlot.zkmBootstrap2("g", (int)20333, (long)(3830206510903753664L ^ var3_2)));
                        } else {
                            v6 = var21_18;
                        }
                    }
                    var24_20.add(v6);
                    if (var9_9 != null) continue block0;
                    v3 = var15_13;
                } while (var3_2 <= 0L);
            }
            var11_8 = (List)v3;
            this.setting.set(new Color(((Number)var11_8.get(0)).intValue(), ((Number)var11_8.get(1)).intValue(), ((Number)var11_8.get(2)).intValue(), ((Number)var11_8.get(3)).intValue()));
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PropertySlot.a = ZkmKeys.a(-697669283160861921L, 4089222421860816994L, MethodHandles.lookup().lookupClass()).a(89414752554512L);
                        PropertySlot.d = new HashMap<K, V>(13);
                        var11 = PropertySlot.a ^ 32681342880950L;
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
                        var20_3 = new String[6];
                        var18_4 = 0;
                        var17_5 = "\u0005\u00c9\b|\u00a4m\u00e0\u009fe\u00a1\u00b0\u00bc\u00b6\u00f2z\u0007\u0010>\u00f9\u00d4\u00cbR\u00cd\u00b3\u0081\u00e1%j\u009b\u0090A\u00bd\u00bf\u0010\u00b7\u00c7\u00e0\u00dc\u00a3+\u001b\u0086\u00ce\u0091l\u00e70\u0006[4\u0018\u00b3\u0082'BR\u009b\u008d\u0007\u00c3\u00d2\u00e7\u00cd\u009dzf\u00a7\u0019+\u00e6\u0000\u0004z\u001b\u00a4";
                        var19_6 = 75;
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
                            var20_3[var18_4++] = PropertySlot.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00bb!\u00db\u0086\u0003\u009fy{XH\u00e2\u0006x3;\u00a8y\u00e5\"\u00ec\u0015[\u00d4Y\u0018.\u000e\u00f2\u00fc\u009e\u00a6?rC\u00a0\u00aaz~\u0018\u00e2o\t`\u00d7|\u00ae>,\u00e1";
                            var19_6 = 49;
                            var16_7 = 24;
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
                            var20_3[var18_4++] = PropertySlot.zkmDecodeUtf8(var21_9).intern();
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
                PropertySlot.b = var20_3;
                PropertySlot.c = new String[6];
                PropertySlot.h = new HashMap<K, V>(13);
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
                var4_14 = "V\u00c9\u00c1O\u00f3\u00b5PY\u0083\u0006\u00bePt~\u00f0\u00d4";
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
                    var4_14 = "n\u0092h\u00f2l\u00e6\u0018n\u0088\u00a2\u00ed\u0017\u00c5T=g";
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
        PropertySlot.e = var6_12;
        PropertySlot.f = new Integer[4];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x28E0;
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
                throw new RuntimeException("su/catlean/ig", exception);
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
            PropertySlot.c[n2] = PropertySlot.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PropertySlot.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ig" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A92;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/ig", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PropertySlot.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PropertySlot.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ig" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PropertySlot.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PropertySlot.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

