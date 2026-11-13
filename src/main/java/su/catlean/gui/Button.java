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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.Element;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.font.Fonts;
import su.catlean.zkm.ZkmKeys;

public final class Button
extends Element {
    @NotNull
    private Function0 clickAction;
    private float width;
    @NotNull
    private KMutableProperty0 hoverRef;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public Button(@NotNull Function0 function0, float f, @NotNull KMutableProperty0 kMutableProperty0) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(function0, (String)((Object)Button.zkmBootstrap("f", (int)2799, (long)(0x48D9DD8BEAE34804L ^ l))));
        Intrinsics.checkNotNullParameter(kMutableProperty0, (String)((Object)Button.zkmBootstrap("f", (int)17911, (long)(0x4B1119BD6823871EL ^ l))));
        this.clickAction = function0;
        this.width = f;
        this.hoverRef = kMutableProperty0;
    }

    public final float getWidth() {
        return this.width;
    }

    public final void setWidth(float f) {
        this.width = f;
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void render(long var1_1, @NotNull DrawContext var3_2, float var4_3, float var5_4, float var6_5, float var7_6) {
        v0 = var1_1;
        v1 = v0 ^ 122872324218116L;
        var8_7 = (int)(v1 >>> 48);
        var9_8 = (int)(v1 << 16 >>> 48);
        var10_9 = (int)(v1 << 32 >>> 32);
        var13_10 = v0 ^ 18592005668209L;
        v2 = Element.java_lang_String_zkmGetFlag();
        Intrinsics.checkNotNullParameter(var3_2, "context");
        var33_11 = v2;
        v3 = var4_3 <= var6_5 ? var6_5 <= var4_3 + (this.width - 88.0f) : false;
        if (!v3) ** GOTO lbl-1000
        v4 = var5_4 <= var7_6 ? var7_6 <= var5_4 + 13.0f : false;
        if (v4) {
            v5 = true;
        } else lbl-1000:
        // 2 sources

        {
            v5 = var34_12 = false;
        }
        if (var34_12) {
            this.hoverRef.set(this);
        }
        v6 = var3_2.getMatrices();
        Intrinsics.checkNotNullExpressionValue(v6, "pose(...)");
        v7 = var34_12 != false ? 2.0f : 1.0f;
        v8 = Color.RED;
        Intrinsics.checkNotNullExpressionValue(v8, "RED");
        v9 = RenderUtil.INSTANCE.withAlpha(v8, 0.5f);
        v10 = Color.RED;
        Intrinsics.checkNotNullExpressionValue(v10, "RED");
        var18_13 = null;
        var19_14 = (int)Button.e;
        var20_15 = 0.0f;
        var21_16 = 0.0f;
        var22_17 = 0.0f;
        var23_18 = v10;
        var24_19 = v9;
        var25_20 = 1.0f;
        var26_21 = 0.1f;
        var27_22 = v7;
        var28_23 = 2.0f;
        var29_24 = 13.0f;
        var30_25 = this.width - 88.0f;
        var31_26 = var5_4;
        var32_27 = var4_3;
        Shapes2D.drawShape$default(v6, (char)var8_7, var32_27, var31_26, var30_25, var29_24, var28_23, var27_22, var26_21, var25_20, var24_19, var23_18, var22_17, (short)var9_8, var21_16, var10_9, var20_15, var19_14, var18_13);
        v11 = Fonts.getSF_M();
        v12 = var4_3 + (this.width - 88.0f) / 2.0f;
        v13 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(v13, "WHITE");
        v11.drawStringCentered(var3_2, "Delete", v12, var5_4 + 5.0f, v13, var13_10);
    }

    @Override
    public boolean isHovered(float f, float f2) {
        this.clickAction.invoke();
        return true;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    Button.a = ZkmKeys.a(1856249456402936117L, 4789881039440383148L, MethodHandles.lookup().lookupClass()).a(123654274608908L);
                    Button.d = new HashMap<K, V>(13);
                    var5 = Button.a ^ 74146133127977L;
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var5 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var5 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[8];
                    var12_4 = 0;
                    var11_5 = "2\u00a6\u00d6s\u00b9t<y\u001b\u00ae\u00b1<\u00f8\u00fa+\u0093\u00f6\u00fc\u0089\u0017\u00ff%sY \u0097\u0014\u00d9\u000f\u0011F\u0013\u00bfqG\u00ee\u00cfta)|\u0005D\u00a6$\u00d3h2\u00aa!\u00dd\u00fc\u0004\u00a5B\u00c7\u0080\u0010\n1\u0014\u0087\u00ed+\u00c3q\u008c\u0081\u00ac\u00c1\u0004\u0087 \u00c5\u0018\u0089A\u00f0\u00cb+\u00f2wub\u00f0LS\u00cb9\u00e8\u00ad\u001c\u007fW\u0095BL\u0090\u00b4\u0010\u00e7\u00ad\u00df\u00e5\u00e3\u0003\u0016\u00adi\u00b6\u00e8\u00b5\u00c1'\u009bs\u0010*\u00c1\u0016`\u00e9J2l\u0088\u0001\u000f\n\u0091\u0098\u00c2\u00ee";
                    var13_6 = 133;
                    var10_7 = 24;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = Button.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "G\u00d4\u00a5\u00da\u00f5\u00b2\u00ab_\u0098\u00d4\u00a8\u00a5\u00b3\u0005\u00a2\u00cd\u0010TR\u00a0>\u00f4n\n\u0080\u00b82\u00cc\u00c9\u00ec\u00ea\u008e\u00d9";
                        var13_6 = 33;
                        var10_7 = 16;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = Button.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            Button.b = var14_3;
            Button.c = new String[8];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var5 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var5 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-41, 67, 74, 23, -70, 17, 59, 44});
        ** while (true)
        Button.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4984;
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
                throw new RuntimeException("su/catlean/fv", exception);
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
            Button.c[n2] = Button.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Button.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Button.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

