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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class TextFormatting {
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    @NotNull
    public static final MutableText applyFormatting(@NotNull MutableText mutableText, @NotNull String string, @NotNull Formatting formatting) {
        Intrinsics.checkNotNullParameter(mutableText, "<this>");
        Intrinsics.checkNotNullParameter(string, "addition");
        Intrinsics.checkNotNullParameter(formatting, "formatting");
        MutableText mutableText2 = mutableText.append(Text.literal(string).formatted(formatting));
        Intrinsics.checkNotNullExpressionValue(mutableText2, "append(...)");
        return mutableText2;
    }

    @NotNull
    public static final MutableText black(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312C33B2F9ADE13L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8992118A82130CEL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.BLACK);
    }

    @NotNull
    public static final MutableText green(@NotNull MutableText mutableText, @NotNull String string) {
        Intrinsics.checkNotNullParameter(mutableText, "<this>");
        Intrinsics.checkNotNullParameter(string, "addition");
        return TextFormatting.applyFormatting(mutableText, string, Formatting.GREEN);
    }

    @NotNull
    public static final MutableText white(@NotNull MutableText mutableText, @NotNull String string) {
        Intrinsics.checkNotNullParameter(mutableText, "<this>");
        Intrinsics.checkNotNullParameter(string, "addition");
        return TextFormatting.applyFormatting(mutableText, string, Formatting.WHITE);
    }

    @NotNull
    public static final MutableText gray(@NotNull MutableText mutableText, @NotNull String string) {
        Intrinsics.checkNotNullParameter(mutableText, "<this>");
        Intrinsics.checkNotNullParameter(string, "addition");
        return TextFormatting.applyFormatting(mutableText, string, Formatting.GRAY);
    }

    @NotNull
    public static final MutableText aqua(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312BD8B21BE93E0L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8995FA8A6057D3DL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.AQUA);
    }

    @NotNull
    public static final MutableText darkBlue(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312BE78F3F538DDL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8995C5B744ED600L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.DARK_BLUE);
    }

    @NotNull
    public static final MutableText darkGreen(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312A998C08CF634L ^ l2))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8994BBB473718E9L ^ l2))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.DARK_GREEN);
    }

    @NotNull
    public static final MutableText darkAqua(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312E481227A40F0L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x89906A2A5C1AE2DL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.DARK_AQUA);
    }

    @NotNull
    public static final MutableText darkRed(@NotNull MutableText mutableText, @NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312FD1F5D33FDDFL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8991F3CDA881302L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.DARK_RED);
    }

    @NotNull
    public static final MutableText darkPurple(@NotNull MutableText mutableText, @NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)27722, (long)(0x5860A81EEBDC9A72L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)19, (long)(0x4183AC2B3775F62AL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.DARK_PURPLE);
    }

    @NotNull
    public static final MutableText gold(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x731291D068986587L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x89973F3EF238B5AL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.GOLD);
    }

    @NotNull
    public static final MutableText darkGray(@NotNull MutableText mutableText, @NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312BFD46C3233AAL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8995DF7EB89DD77L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.DARK_GRAY);
    }

    @NotNull
    public static final MutableText blue(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x73128AF7DFC5F225L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x89968D4587E1CF8L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.BLUE);
    }

    @NotNull
    public static final MutableText red(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312A495B6CF5D4BL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x89946B63174B396L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.RED);
    }

    @NotNull
    public static final MutableText lightPurple(@NotNull MutableText mutableText, @NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312F44D83138F6FL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x899166E04A861B2L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.LIGHT_PURPLE);
    }

    @NotNull
    public static final MutableText yellow(@NotNull MutableText mutableText, @NotNull String string) {
        int n = 0;
        int n2 = 0;
        long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312B56835BB03BAL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x899574BB200ED67L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.YELLOW);
    }

    @NotNull
    public static final MutableText obf(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312B8B2A24D6716L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8995A9125F689CBL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.OBFUSCATED);
    }

    @NotNull
    public static final MutableText bold(@NotNull MutableText mutableText, @NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x73129CDF76806286L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8997EFCF13B8C5BL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.BOLD);
    }

    @NotNull
    public static final MutableText strikethrough(@NotNull MutableText mutableText, @NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312DD1ADC0B0798L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x8993F395BB0E945L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.STRIKETHROUGH);
    }

    @NotNull
    public static final MutableText underline(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312D0E6A8F7C0FDL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x89932C52F4C2E20L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.UNDERLINE);
    }

    @NotNull
    public static final MutableText italic(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312D1CEE87A2561L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x89933ED6FC1CBBCL ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.ITALIC);
    }

    @NotNull
    public static final MutableText reset(@NotNull MutableText mutableText, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(mutableText, (String)((Object)TextFormatting.zkmBootstrap("g", (int)6301, (long)(0x7312A502CB52E6EFL ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)TextFormatting.zkmBootstrap("g", (int)30273, (long)(0x89947214CE90832L ^ l))));
        return TextFormatting.applyFormatting(mutableText, string, Formatting.RESET);
    }

    @NotNull
    public static final MutableText emptyText() {
        MutableText mutableText = Text.empty();
        Intrinsics.checkNotNullExpressionValue(mutableText, "empty(...)");
        return mutableText;
    }

    @NotNull
    public static final Text getText(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<this>");
        Text text = Text.of(string);
        Intrinsics.checkNotNullExpressionValue(text, "nullToEmpty(...)");
        return text;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                TextFormatting.a = ZkmKeys.a(6788125218847003900L, -7433906626263918650L, MethodHandles.lookup().lookupClass()).a(8335460878030L);
                TextFormatting.d = new HashMap<K, V>(13);
                var0 = TextFormatting.a ^ 25100464840929L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[8];
                var7_4 = 0;
                var6_5 = "\u00fa\u00c1;\u00aa\u0080\u00b6\u0081\u000e3\u0084\u00c2\u0005U2\u00ad\u00f6 \u001d\u00ed\u00063\u0089J\u001b345\u000f\u0018\u0001\u00bb\u00f5\u0095\u00ca\u00bc5\u00cf\u00c6\u00c0\u0019\u00c2^\u00c3\u00be\u0095\u00f2\u0004r[\u0018\u0013\u0003w\u008dON\u00d4\u00cc\u0003K6\u001f\u0011;3U\u00ca`/q\u00a9:\u0080\u00df \u00a9\u009bxk\u0007\u00b1{\u00e7Mjn\u001c\u00dc\u0090\u00ac-@?\u00b5'\u00a70\u00a2X\u00d5,\u00ac\u0090\u00cc\\\u0017D(\u0097\u008d\u0014j<Z\u00d3o\u008e\u00a7f\u0000)\u00d6(\ts\u00be\u00d2U\u00a9\u00b6\u00abb\u00c9\u0013\u0096\u009a[a&:\u0017e\u00c6J\u000e\u00ed\u00f8\u00ef \u000ea\u00ba\u00da\u009ehi\u00faiED\u00bd\u0083\u0013\u00d5\u00aa\u008c\u0015\u008aP\u00ce\u00c0\u0006=g|\u00dd\u00a8\u00efsM\b";
                var8_6 = 181;
                var5_7 = 16;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = TextFormatting.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "V;\u00913me\u007f\u00e7\u00b6\u00e0\u008cu\u00fc\u001c\u00cb\n\u0012!\" \u00c8\u00df\u00db\u008axlxq\u00b4\u0085)\u001b\u0010\u00fb\u009aQ\u0084>\u00aed\u00c4\u0005\u0089\u000e_\nY\u00fc9";
                    var8_6 = 49;
                    var5_7 = 32;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = TextFormatting.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        TextFormatting.b = var9_3;
        TextFormatting.c = new String[8];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6367;
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
                throw new RuntimeException("su/catlean/zm", exception);
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
            TextFormatting.c[n2] = TextFormatting.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TextFormatting.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zm" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TextFormatting.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

