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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.module.Module;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;

public final class RectButton {
    private float x;
    private float y;
    private float width;
    private float height;
    @NotNull
    private String label;
    @NotNull
    private Function0 clickActionX;
    private final int id;
    @NotNull
    private final Color fill;
    @NotNull
    private final Color outline;
    private final float outlineWidth;
    private final float radius;
    @NotNull
    private final Animation animation;
    private static boolean B;
    private static final long c;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] i;
    private static final Map j;
    private static final long l;

    public RectButton(float f, float f2, float f3, float f4, @NotNull String string, @NotNull Function0 function0, int n, @NotNull Color color, @NotNull Color color2, float f5, float f6) {
        RectButton.R();
        Intrinsics.checkNotNullParameter(string, "label");
        Intrinsics.checkNotNullParameter(function0, "clickActionX");
        Intrinsics.checkNotNullParameter(color, "fill");
        Intrinsics.checkNotNullParameter(color2, "outline");
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
        this.label = string;
        this.clickActionX = function0;
        this.id = n;
        this.fill = color;
        this.outline = color2;
        this.outlineWidth = f5;
        this.radius = f6;
        this.animation = new Animation(Easing.OUT_QUINT, l);
    }

    public /* synthetic */ RectButton(float f, float f2, float f3, float f4, String string, Function0 function0, int n, Color color, Color color2, float f5, float f6, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 0x40) != 0) {
            n = 0;
        }
        if ((n2 & 0x80) != 0) {
            color = Theme.INSTANCE.g();
        }
        if ((n2 & 0x100) != 0) {
            Color color3 = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color3, "WHITE");
            color2 = color3;
        }
        if ((n2 & 0x200) != 0) {
            f5 = 2.0f;
        }
        if ((n2 & 0x400) != 0) {
            f6 = 3.0f;
        }
        this(f, f2, f3, f4, string, function0, n, color, color2, f5, f6);
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

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    public final float getHeight() {
        return this.height;
    }

    public final void setHeight(float f) {
        this.height = f;
    }

    @NotNull
    public final Function0 getClickActionX() {
        return this.clickActionX;
    }

    public final void setClickActionX(@NotNull Function0 function0) {
        long l = 0L;
        l = c ^ l;
        Intrinsics.checkNotNullParameter(function0, (String)((Object)RectButton.zkmBootstrap("s", (int)3247, (long)(0x7B380E08442F00E3L ^ l))));
        this.clickActionX = function0;
    }

    public final int getId() {
        return this.id;
    }

    @NotNull
    public final Color getFill() {
        return this.fill;
    }

    @NotNull
    public final Color getOutline() {
        return this.outline;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final void render(float f, float f2, float f3, long l, float f4, @NotNull DrawContext drawContext) {
        block4: {
            long l2 = l = c ^ l;
            long l3 = l2 ^ 0x7953075F34A1L;
            int n = (int)(l3 >>> 48);
            int n2 = (int)(l3 << 16 >>> 48);
            int n3 = (int)(l3 << 32 >>> 32);
            long l4 = l2 ^ 0x67BBF8754D4L;
            boolean bl = RectButton.R();
            Intrinsics.checkNotNullParameter(drawContext, "context");
            boolean bl2 = bl;
            float f5 = f - -1.0f;
            float f6 = f5 == 0.0f ? 0 : (f5 < 0.0f ? -1 : 1);
            if (bl2) {
                f6 = f6 == false ? 1.0f : 0.0f;
            }
            if (f6 == false) {
                float f7 = f2 - -1.0f;
                float f8 = f7 == 0.0f ? 0 : (f7 < 0.0f ? -1 : 1);
                if (bl2) {
                    f8 = f8 == false ? 1.0f : 0.0f;
                }
                if (f8 == false) {
                    this.x = f;
                    this.y = f2;
                }
            }
            this.animation.animate(this.isHovered(f3, f4) ? 1.0f : 0.0f);
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Shapes2D.drawShape$default(matrix3x2fStack, (char)n, this.x, this.y, this.width, this.height, this.radius, this.outlineWidth, 0.25f + this.animation.getValue() / 3.0f, 1.0f, this.fill, this.outline, 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
            FontRenderer fontRenderer = Fonts.getSF_B();
            float f9 = this.x + this.width / 2.0f;
            float f10 = this.y + this.height / 2.0f;
            Color color = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
            fontRenderer.drawStringCentered(drawContext, this.label, f9, f10, color, l4);
            if (bl2) break block4;
            Module.zkmSetFlag(new Module[4]);
        }
    }

    public final void mouseClicked(float f, float f2) {
        if (this.isHovered(f, f2)) {
            this.clickActionX.invoke();
        }
    }

    /*
     * Unable to fully structure code
     */
    private final boolean isHovered(float var1_1, float var2_2) {
        var4_3 = this.x;
        var3_4 = RectButton.R();
        cfr_temp_0 = var1_1 - (this.x + this.width);
        v0 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
        if (!var3_4) ** GOTO lbl8
        if (v0 <= 0) {
            v0 = var4_3 == var1_1 ? 0 : (var4_3 < var1_1 ? -1 : 1);
lbl8:
            // 2 sources

            if (var3_4) {
                v0 = v0 <= 0 ? (float)true : (float)false;
            }
        } else {
            v0 = (float)false;
        }
        if (v0 == false) ** GOTO lbl-1000
        var4_3 = this.y;
        cfr_temp_1 = var2_2 - (this.y + this.height);
        v1 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
        if (!var3_4) ** GOTO lbl19
        if (v1 <= 0) {
            v1 = var4_3 == var2_2 ? 0 : (var4_3 < var2_2 ? -1 : 1);
lbl19:
            // 2 sources

            if (var3_4) {
                v1 = v1 <= 0 ? (float)true : (float)false;
            }
        } else {
            v1 = (float)false;
        }
        if (v1 != false) {
            v2 = true;
        } else lbl-1000:
        // 2 sources

        {
            v2 = false;
        }
        return v2;
    }

    public static void zkmSetFlag(boolean bl) {
        B = bl;
    }

    public static boolean zkmGetFlag() {
        return B;
    }

    public static boolean R() {
        return true;
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
                            RectButton.c = ZkmKeys.a(451153474426148211L, -6568850384290233581L, MethodHandles.lookup().lookupClass()).a(164181838163341L);
                            var25 = RectButton.c ^ 85965112742613L;
                            RectButton.f = new HashMap<K, V>(13);
                            RectButton.zkmSetFlag(false);
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
                            var23_3 = new String[9];
                            var21_4 = 0;
                            var20_5 = "u\u00c3\u00b2\u008f~\u00c7ze\u00a4 \u00fd\u00f3={\u00c0A\u00e9\u00c0a\u00db\u0094\u00d4\u001d\u00d0\u00fa\u00d6\u00cb+\u00b0\u009e\u00ec\u00f8\u0010o\u00ca\u0001k\u0090\u0098\u0097\u0093\u00e7\u0001m\u00fc\u00d3!\u00d4\u00f7\u0010\u00b1O\u00b4\u00d8\u00a2\u00dc\u00b6\u00b5\u00ff\u00ebG\u00a7\u001b\u0080\u00fe\u0094\u0010\u009b45d\u008b\u0003HE\u00ba\u0005x\u000e\u0002\u00e7N\u00a7 >\u000e\u0098\u00dc0nuF\u0080I\u00aaG\u00c5\u0099\u0096\u00b6Z5\u00abs\u00b2\u00df\u00a8\u001c7\u0013u?\u009b\u00de\u00db\u00eb\u0018\u00feE\u0085\u00db\u00e8\u00bf\u00d3}\u00eb1\u0084\u009cE\u008fj\n \u00dc\u007f\u0090\b\u00100Z\u0010T\u00d0\u00b7\u00b9\u00dd\u00a2\u0096\u00c3\u00e7\u00d5\u0092[\u009e\u001f_\u0000";
                            var22_6 = 158;
                            var19_7 = 32;
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
                                var23_3[var21_4++] = RectButton.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "d\u0097D\u00ab\u00ea\u00b5p\u00ac\n\u00e2\u00f8\u00c6\u001e\u0006.,\u0010\u00a6A\u00b7\u0085\u008aX\u00f0\u00ea\u00c5\"n\u0092\u00d5\u0084\u00bcH";
                                var22_6 = 33;
                                var19_7 = 16;
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
                                var23_3[var21_4++] = RectButton.zkmDecodeUtf8(var24_9).intern();
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
                    RectButton.d = var23_3;
                    RectButton.e = new String[9];
                    RectButton.j = new HashMap<K, V>(13);
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
                    var11_12 = new long[6];
                    var8_13 = 0;
                    var9_14 = "\u001c\u009f\u0006\u00a0\u00d6\u0098\u00d9K\u0010\u0003\u009b\u00bdUW\u00b6\u00c3\u00c02\u00a5E0\u00c2\u00c1-\u00d3\u0018\u00ce\u00b1\t\u008fbP";
                    var10_15 = 32;
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
                        var9_14 = "\u0097\u0010S\u00ad\u00a8 \u00af\u008f\u00d9n48\u001f\u00be)C";
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
            RectButton.g = var11_12;
            RectButton.i = new Integer[6];
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
        var4_22 = var0_20.doFinal(new byte[]{-53, -46, 76, 15, 99, 113, 3, -46});
        ** while (true)
        RectButton.l = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2359;
        if (e[n2] == null) {
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
                throw new RuntimeException("su/catlean/n7", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            RectButton.e[n2] = RectButton.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RectButton.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/n7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3B57;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/n7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RectButton.i[n2] = n3;
        }
        return i[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RectButton.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/n7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RectButton.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RectButton.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

