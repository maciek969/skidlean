/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

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
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Globals;
import su.catlean.util.ServerInfo;
import su.catlean.zkm.ZkmKeys;

public final class CoordWidget
extends HudElement {
    @NotNull
    public static final CoordWidget I;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting secondaryCoords$delegate;
    @NotNull
    private static final BooleanSetting onlyNether$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private CoordWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x3B137B3195FEL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "coord-widget", (char)n2);
    }

    private final boolean getSecondaryCoords() {
        return (Boolean)secondaryCoords$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setSecondaryCoords(boolean bl) {
        secondaryCoords$delegate.setValue(this, delegatedProperties[0], bl);
    }

    private final boolean getOnlyNether() {
        return (Boolean)onlyNether$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setOnlyNether(boolean bl) {
        onlyNether$delegate.setValue(this, delegatedProperties[1], bl);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        StringBuilder stringBuilder;
        long l2 = l ^ 0x6A47001394F1L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        boolean bl = ServerInfo.INSTANCE.getDimensionName();
        float f = !bl ? 0.125f : 8.0f;
        boolean bl2 = this.getSecondaryCoords() && (!this.getOnlyNether() || bl);
        String string = (int)Globals.getPlayer().getX() + "  " + (int)Globals.getPlayer().getY() + "  " + (int)Globals.getPlayer().getZ();
        Object object = "[" + (int)(Globals.getPlayer().getX() * (double)f) + "  " + (int)(Globals.getPlayer().getZ() * (double)f) + "]";
        if (Globals.getPlayer().age < 100) {
            string = "wait " + (100 - Globals.getPlayer().age) / 20 + " seconds";
            object = "";
        }
        StringBuilder stringBuilder2 = stringBuilder = new StringBuilder();
        stringBuilder2.append(string);
        if (bl2) {
            stringBuilder2.append(" " + object);
        }
        String string2 = stringBuilder.toString();
        this.drawText(drawContext, l2, string2);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        CoordWidget.a = ZkmKeys.a(2932481237323591918L, -8199712963885439176L, MethodHandles.lookup().lookupClass()).a(236653600314699L);
                        var20 = CoordWidget.a ^ 53700821519339L;
                        var24_1 = var20 ^ 89523569874078L;
                        CoordWidget.f = new HashMap<K, V>(13);
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
                        var18_4 = new String[12];
                        var16_5 = 0;
                        var15_6 = "DW\n\u00c3p^\u00a1\u009c\u00be\u00d6\u00d56/\u00f9\b\u00feN\u00bb\u00e0{\u00fb\\T\"\u008by\u0080qBO\u00e5\u00bf\u00108[\u007f\u00f6\u00c9\u00ca<\u0085\u009c\tY\u00c5\u00b4(\u00f2.\u0018:\u00ef\u007f\u00c4\u00e9\u0010HE[\u00ff\u008b\u00d5u(C(\u009a\u001d\\E\u00ec\u00b1\u00e3\u00ee >\u008eQL\u00c0\n\u00f2xu\u00ce\u00a3\u0017\n\u00f8\u00eecI9\u00bd\u0088x\u001b\u00e8\u00cf\u0080<\u00f5\u00bb\u00ef\u0007c\u00ac {\u00a3li.\u00c0\u00aa\u001e\b\u009a\u00d5\u0005^($+\u008c\u00ccs3J;nwu\u00e8\u00b8@>X@\u0087 \u008d\u001b\u0015\u00c7\u000fD\u001d\u00d2r\"\n\u00ee\u00d4\"7\u0085\u00c7\\\u00d1\u00fcqsn\u00b9U<D\u00d0\u00e3!\u00ab\u00ba\u0010\u00d9\u00a3\u0013\u00e1?\u00baL\u00db~\u0098\u00bd\u00af\u00e1\u00f0\u00db#\u0010\u007f\u0095\u00f2\u0003\u0019\u00d6&\u00dc&\u00bd(\u00d7+q\u008e\u00b0 9p\u0090\u00b0S\u00e0=\u00db\u000bYw\u00bei\u00a5\u00a1\u001d2\u0002\u00d5\u00d6\u00ab\u00b4\u00fc\u00c6\u00b2\u00f1\u0012jk\u001cL'\u0010\u00d4\u00d8\u00dc\u00a2\r\u00b1\u00bd=\u00e2\u00c1\u0087W\u00dcQ\u0012$";
                        var17_7 = 257;
                        var14_8 = 32;
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
                            var18_4[var16_5++] = CoordWidget.zkmDecodeUtf8(var19_10).intern();
                            if ((var13_9 += var14_8) < var17_7) {
                                var14_8 = var15_6.charAt(var13_9);
                                ** continue;
                            }
                            var15_6 = "\u00a1\u00eb1L\u00b9_\\\u00f3\u00a7l\u00ff\u00be{6\u00a8\u00dc\u00bf>\u00bd\u00d5\u00abh\u00c6oY\u00b2Z\u00f2}\u00bau\u007fW\u00d5y\u00daN\u00acn6\u0018\u00ef\u00e3\u0006\u0003p\u00b1\u00f9\u0003S\u0094\u00dd\u0082\u00dcY\f?\u00d4\u008e*\u00e3\u00acp\u00e3\u0093";
                            var17_7 = 65;
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
                            var18_4[var16_5++] = CoordWidget.zkmDecodeUtf8(var19_10).intern();
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
                CoordWidget.b = var18_4;
                CoordWidget.c = new String[12];
                CoordWidget.i = new HashMap<K, V>(13);
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
                var4_15 = "e\u0000\u00aa*\u00c6ke%`\u00d3lT\"9\u0015\u0013)\u0085\u00f9\u00c9\u00eb\u00fc'v";
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
                    var4_15 = "/\u00ff\n\u001e\u0097\u00f8Rb\u00f4\u0095\u00c70\u00b2\u0088\u0081\u00f6";
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
        CoordWidget.g = var6_13;
        CoordWidget.h = new Integer[5];
        var26_21 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoordWidget.class, "secondaryCoords", "getSecondaryCoords()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoordWidget.class, "onlyNether", "getOnlyNether()Z", 0))};
        CoordWidget.delegatedProperties = var26_21;
        CoordWidget.I = new CoordWidget(var24_1);
        CoordWidget.secondaryCoords$delegate = SettingFactory.booleanSetting$default(CoordWidget.I, "secondary", true, null, null, 12, null);
        CoordWidget.onlyNether$delegate = SettingFactory.booleanSetting$default(CoordWidget.I, "only-nether", true, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6547;
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
                throw new RuntimeException("su/catlean/g1", exception);
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
            CoordWidget.c[n2] = CoordWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CoordWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/g1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1EAF;
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
                throw new RuntimeException("su/catlean/g1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CoordWidget.h[n2] = n3;
        }
        return h[n2];
    }

    private static int d(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CoordWidget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/g1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CoordWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CoordWidget.class, "d", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

