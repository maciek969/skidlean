/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;

public final class LegacyTextConverter {
    @NotNull
    public static final LegacyTextConverter INSTANCE;
    private static final char y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private LegacyTextConverter() {
    }

    @NotNull
    public final String toLegacy(@NotNull Text text) {
        Intrinsics.checkNotNullParameter(text, "t");
        StringBuilder stringBuilder = new StringBuilder();
        int[] nArray = new int[]{Integer.MIN_VALUE};
        int[] nArray2 = nArray;
        text.visit((arg_0, arg_1) -> LegacyTextConverter.appendStyled(nArray2, stringBuilder, arg_0, arg_1), Style.EMPTY);
        stringBuilder.append('\u00a7').append('r');
        String string = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final String colorCode(int n) {
        Character c = this.toLegacyChar(n);
        if (c != null) {
            return "\u00a7" + c;
        }
        return this.toHex(n);
    }

    private final String toHex(int n) {
        Locale locale = Locale.ROOT;
        String string = "%06x";
        Object[] objectArray = new Object[]{n & 0xFFFFFF};
        String string2 = String.format(locale, string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue(string2, "format(...)");
        String string3 = string2;
        StringBuilder stringBuilder = new StringBuilder(14);
        stringBuilder.append('\u00a7').append('x');
        int n2 = string3.length();
        for (int i = 0; i < n2; ++i) {
            char c = string3.charAt(i);
            stringBuilder.append('\u00a7').append(c);
        }
        String string4 = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
        return string4;
    }

    @Nullable
    public final Character toLegacyChar(int n) {
        return switch (n & 0xFFFFFF) {
            case 0 -> Character.valueOf('0');
            case 170 -> Character.valueOf('1');
            case 43520 -> Character.valueOf('2');
            case 43690 -> Character.valueOf('3');
            case 0xAA0000 -> Character.valueOf('4');
            case 0xAA00AA -> Character.valueOf('5');
            case 0xFFAA00 -> Character.valueOf('6');
            case 0xAAAAAA -> Character.valueOf('7');
            case 0x555555 -> Character.valueOf('8');
            case 0x5555FF -> Character.valueOf('9');
            case 0x55FF55 -> Character.valueOf('a');
            case 0x55FFFF -> Character.valueOf('b');
            case 0xFF5555 -> Character.valueOf('c');
            case 0xFF55FF -> Character.valueOf('d');
            case 0xFFFF55 -> Character.valueOf('e');
            case 0xFFFFFF -> Character.valueOf('f');
            default -> null;
        };
    }

    private static final Optional appendStyled(int[] nArray, StringBuilder stringBuilder, Style style, String string) {
        int n;
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(string, "asString");
        if (((CharSequence)string).length() == 0) {
            return Optional.empty();
        }
        TextColor textColor = style.getColor();
        int n2 = n = textColor != null ? textColor.getRgb() & 0xFFFFFF : -1;
        if (n != nArray[0]) {
            StringBuilder stringBuilder2 = n == -1 ? stringBuilder.append('\u00a7').append('r') : stringBuilder.append(INSTANCE.colorCode(n));
            nArray[0] = n;
        }
        stringBuilder.append(string);
        return Optional.empty();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        LegacyTextConverter.a = ZkmKeys.a(-7902933317316973469L, 6459911424360205231L, MethodHandles.lookup().lookupClass()).a(204650246606868L);
                        LegacyTextConverter.d = new HashMap<K, V>(13);
                        var11 = LegacyTextConverter.a ^ 131993427793433L;
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
                        var17_5 = ">\u00b6B\u00a9\u009e\u0017\u00a7\u00e7&\u00c7\u00fa\u00a3\u00b1kP\u00cf\u0012}\u00e5y\u009b\u000f\r\u00a6\u0018\u0019wN\u0018|T\u00ef\u0096\u0006\u00b9V%\u00cd\u00020\u00cb\u001f\u00a9\u00bd\u0094\u0084=R\u008d\u0010=\u0007\t\u00be\u0003\u00ce\\*\u00d5Y\u00e2\u00d9\u00d5\u009cA\u00c8\u0010\u0011\u0002{\u00d3%>\u0096\u00f2w\u00a2\u00a48\u00d6\u0095z\u00b2";
                        var19_6 = 83;
                        var16_7 = 24;
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
                            var20_3[var18_4++] = LegacyTextConverter.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "G\u0094\u00bfZf\n\u00c6\u00ec\u00f5\u00c2UgL\u00d1 \u00f1\u00d795Y\u00cf\u00ae\u00e1\u007f \u007fA\u0007\u001fh\u00f1P\u00b8\u00e0\u009e\u00d5\u00e4\u0018\u00acXn\u000e?@\u00c2\u000e\u0093\u00e3\u0007\u00f5\u00c4P?cN\u00e9/";
                            var19_6 = 57;
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
                            var20_3[var18_4++] = LegacyTextConverter.zkmDecodeUtf8(var21_9).intern();
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
                LegacyTextConverter.b = var20_3;
                LegacyTextConverter.c = new String[6];
                LegacyTextConverter.g = new HashMap<K, V>(13);
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
                var6_12 = new long[26];
                var3_13 = 0;
                var4_14 = "\u00cc\r'\u008fM\u00f2d`\u00c2\u00ac\u0084\u001cH\u009e\u00ef\u00f7I\u0097\u00da\u00c6\u007f\u001a1\u00abZ&\u0081(\"3x\"\u00e0<tY9\u0015\u00b8\u00card\u0015\u00fc\u00f6\u0010\u0082\u00e6\u00ff\u00074N\u00dfXc3qs\u00e1L\u00b5:\u009c\u00f0\u00adfC)\u00d7\u009d\u00fc\u00aau\u00f6\u00db\u0089\u00ec\u00f7<\u00dd\u00ea(\u0001c\u00f3~6\u00052j\u00e5\u00e9\u00e9\u00a3\u00ef\u00a3\u00c5\u0094\u0006\u00a3WU\u00e5\u00ea\u00c3^\u0099\u00fb_\u00e23m\\\u00a7NX\tZ\u00e1:\u00a2Z}\u0096\u00cb\u0082\u00fd\u00c2C\u0011\u0006\u001f\u00f2\u0007\u00e6w\n\u0085\u00e3\u0014\u00c4\u001a\u0083\u0007h\u008b\u008dt\u008e\u00f3\u000e_\u00ec\u0080\u008a\u008a\u00efzy\u008a\u001c\u00c5\u00d8\u0086!T7`4\r\u00cc2J\u00d3\u00d6S\u00b8\u00ca\u00e4]\u00c0\u00ed\u00d5\u00952-A\u0015_\u00d4Cx";
                var5_15 = 192;
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
                    var4_14 = "\u0095\u00c6\u0085\u00c0\u0081D\u00a2,\u00b9/PQ\u00b2\u00b3\u001d\u008e";
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
        LegacyTextConverter.e = var6_12;
        LegacyTextConverter.f = new Integer[26];
        LegacyTextConverter.y = (char)167;
        LegacyTextConverter.INSTANCE = new LegacyTextConverter();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A43;
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
                throw new RuntimeException("su/catlean/mi", exception);
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
            LegacyTextConverter.c[n2] = LegacyTextConverter.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LegacyTextConverter.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/mi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x62B7;
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
                throw new RuntimeException("su/catlean/mi", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            LegacyTextConverter.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = LegacyTextConverter.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/mi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LegacyTextConverter.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(LegacyTextConverter.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

