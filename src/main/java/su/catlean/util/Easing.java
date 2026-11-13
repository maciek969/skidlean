/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class Easing
extends Enum {
    @NotNull
    private final Function1 l;
    public static final Easing LINEAR;
    public static final Easing IN_QUAD;
    public static final Easing OUT_QUAD;
    public static final Easing IN_OUT_QUAD;
    public static final Easing IN_CUBIC;
    public static final Easing OUT_CUBIC;
    public static final Easing IN_QUART;
    public static final Easing OUT_QUART;
    public static final Easing IN_OUT_QUART;
    public static final Easing IN_QUINT;
    public static final Easing OUT_QUINT;
    public static final Easing IN_OUT_QUINT;
    public static final Easing IN_SINE;
    public static final Easing OUT_SINE;
    public static final Easing IN_OUT_SINE;
    public static final Easing IN_EXPO;
    public static final Easing OUT_EXPO;
    public static final Easing IN_OUT_EXPO;
    public static final Easing IN_CIRC;
    public static final Easing OUT_CIRC;
    public static final Easing IN_OUT_CIRC;
    public static final Easing SIGMOID;
    public static final Easing SIGMOID_2;
    public static final Easing SIGMOID_3;
    public static final Easing SIGMOID_4;
    public static final Easing SIGMOID_5;
    public static final Easing SIGMOID_6;
    public static final Easing SIGMOID_7;
    public static final Easing OUT_ELASTIC;
    public static final Easing IN_BACK;
    public static final Easing OUT_BACK;
    public static final Easing DECELERATE;
    public static final Easing IN_OUT_CUBIC;
    private static final /* synthetic */ Easing[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static int C;
    private static final long a;
    private static final long[] b;
    private static final Integer[] c;
    private static final Map d;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private Easing() {
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.l = var3_2;
    }

    @NotNull
    public final Function1 a() {
        return this.l;
    }

    public static Easing[] values() {
        return (Easing[])$VALUES.clone();
    }

    public static Easing valueOf(String string) {
        return Enum.valueOf(Easing.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final double _clinit_$lambda$0(double d) {
        return d;
    }

    private static final double _clinit_$lambda$1(double d) {
        return Math.pow(d, 2.0);
    }

    private static final double _clinit_$lambda$2(double d) {
        return d * (2.0 - d);
    }

    private static final double _clinit_$lambda$3(double d) {
        return d < 0.5 ? 2.0 * Math.pow(d, 2.0) : -1.0 + (4.0 - 2.0 * d) * d;
    }

    private static final double _clinit_$lambda$4(double d) {
        return Math.pow(d, 3.0);
    }

    private static final double _clinit_$lambda$5(double d) {
        return Math.pow(d - 1.0, 3.0) + 1.0;
    }

    private static final double _clinit_$lambda$6(double d) {
        return Math.pow(d, 4.0);
    }

    private static final double _clinit_$lambda$7(double d) {
        return 1.0 - Math.pow(d - 1.0, 4.0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static final double _clinit_$lambda$8(double d) {
        double d2;
        int n = Easing.O();
        double d3 = d;
        double d4 = 0.5;
        if (n == 0) {
            if (d3 < d4) {
                d2 = 8.0 * d * d * d * d;
                return d2;
            }
            d3 = 1.0;
            d4 = Math.pow(-2.0 * d + 2.0, 4.0) / 2.0;
        }
        d2 = d3 - d4;
        return d2;
    }

    private static final double _clinit_$lambda$9(double d) {
        return Math.pow(d, 5.0);
    }

    private static final double _clinit_$lambda$10(double d) {
        return 1.0 + Math.pow(d - 1.0, 5.0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static final double _clinit_$lambda$11(double d) {
        double d2;
        int n = Easing.O();
        double d3 = d;
        double d4 = 0.5;
        if (n == 0) {
            if (d3 < d4) {
                d2 = 16.0 * d * d * d * d * d;
                return d2;
            }
            d3 = 1.0;
            d4 = Math.pow(-2.0 * d + 2.0, 5.0) / 2.0;
        }
        d2 = d3 - d4;
        return d2;
    }

    private static final double _clinit_$lambda$12(double d) {
        return 1.0 - Math.cos(d * Math.PI / 2.0);
    }

    private static final double _clinit_$lambda$13(double d) {
        return Math.sin(d * Math.PI / 2.0);
    }

    private static final double _clinit_$lambda$14(double d) {
        return 1.0 - Math.cos(Math.PI * d / 2.0);
    }

    private static final double _clinit_$lambda$15(double d) {
        int n = Easing.O();
        double d2 = d - 0.0;
        double d3 = d2 == 0.0 ? 0 : (d2 < 0.0 ? -1 : 1);
        if (n == 0) {
            d3 = d3 == false ? 1.0 : 0.0;
        }
        return d3 != false ? 0.0 : Math.pow(2.0, 10.0 * d - 10.0);
    }

    private static final double _clinit_$lambda$16(double d) {
        int n = Easing.O();
        double d2 = d - 1.0;
        double d3 = d2 == 0.0 ? 0 : (d2 < 0.0 ? -1 : 1);
        if (n == 0) {
            d3 = d3 == false ? 1.0 : 0.0;
        }
        return d3 != false ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * d);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static final double _clinit_$lambda$17(double d) {
        double d2;
        int n;
        block8: {
            block7: {
                n = Easing.O();
                double d3 = d - 0.0;
                double d4 = d3 == 0.0 ? 0 : (d3 < 0.0 ? -1 : 1);
                if (n == 0) {
                    d4 = d4 == false ? 1.0 : 0.0;
                }
                if (d4 != false) break block7;
                double d5 = d - 1.0;
                double d6 = d5 == 0.0 ? 0 : (d5 < 0.0 ? -1 : 1);
                if (n == 0) {
                    d6 = d6 == false ? 1.0 : 0.0;
                }
                if (d6 == false) break block8;
            }
            d2 = d;
            return d2;
        }
        double d7 = d - 0.5;
        double d8 = d7 == 0.0 ? 0 : (d7 < 0.0 ? -1 : 1);
        if (n == 0) {
            if (d8 < 0) {
                d2 = Math.pow(2.0, 20.0 * d - 10.0) / 2.0;
                return d2;
            }
            d8 = 2;
        }
        d2 = ((double)d8 - Math.pow(2.0, -20.0 * d + 10.0)) / 2.0;
        return d2;
    }

    private static final double _clinit_$lambda$18(double d) {
        return 1.0 - Math.sqrt(1.0 - Math.pow(d, 2.0));
    }

    private static final double _clinit_$lambda$19(double d) {
        return Math.sqrt(1.0 - Math.pow(d - 1.0, 2.0));
    }

    private static final double _clinit_$lambda$20(double d) {
        double d2;
        if (d < 0.5) {
            double d3 = 2.0 * d;
            d2 = (1.0 - Math.sqrt(1.0 - d3 * d3)) / 2.0;
        } else {
            double d4 = -2.0 * d + 2.0;
            d2 = (Math.sqrt(1.0 - d4 * d4) + 1.0) / 2.0;
        }
        return d2;
    }

    private static final double _clinit_$lambda$21(double d) {
        return 1.0 / (1.0 + Math.exp(-10.0 * (d - 0.5)));
    }

    private static final double _clinit_$lambda$22(double d) {
        return 1.0 / (1.0 + Math.exp(-8.0 * (d - 0.5)));
    }

    private static final double _clinit_$lambda$23(double d) {
        return 1.0 / (1.0 + Math.exp(-12.0 * (d - 0.5)));
    }

    private static final double _clinit_$lambda$24(double d) {
        return 1.0 / (1.0 + Math.exp(-6.0 * (d - 0.5)));
    }

    private static final double _clinit_$lambda$25(double d) {
        return 1.0 / (1.0 + Math.exp(-14.0 * (d - 0.5)));
    }

    private static final double _clinit_$lambda$26(double d) {
        return 1.0 / (1.0 + Math.exp(-4.0 * (d - 0.5)));
    }

    private static final double _clinit_$lambda$27(double d) {
        return 1.0 / (1.0 + Math.exp(-16.0 * (d - 0.5)));
    }

    private static final double _clinit_$lambda$28(double d) {
        double d2 = d;
        return d2 == 0.0 ? 0.0 : (d2 == 1.0 ? 1.0 : Math.pow(2.0, -10.0 * d) * Math.sin((d * 10.0 - 0.75) * 2.0943951023931953) * 0.5 + 1.0);
    }

    private static final double _clinit_$lambda$29(double d) {
        return 2.70158 * Math.pow(d, 3.0) - 1.70158 * Math.pow(d, 2.0);
    }

    private static final double _clinit_$lambda$30(double d) {
        return 1.0 + 2.70158 * Math.pow(d - 1.0, 3.0) + 1.70158 * Math.pow(d - 1.0, 2.0);
    }

    private static final double _clinit_$lambda$31(double d) {
        return 1.0 - Math.pow(d - 1.0, 2.0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static final double _clinit_$lambda$32(double d) {
        double d2;
        int n = Easing.O();
        double d3 = d;
        double d4 = 0.5;
        if (n == 0) {
            if (d3 < d4) {
                d2 = 4.0 * d * d * d;
                return d2;
            }
            d3 = 1.0;
            d4 = Math.pow(-2.0 * d + 2.0, 3.0) / 2.0;
        }
        d2 = d3 - d4;
        return d2;
    }

    private static final /* synthetic */ Easing[] $values() {
        Easing[] easingArray = new Easing[]{LINEAR, IN_QUAD, OUT_QUAD, IN_OUT_QUAD, IN_CUBIC, OUT_CUBIC, IN_QUART, OUT_QUART, IN_OUT_QUART, IN_QUINT, OUT_QUINT, IN_OUT_QUINT, IN_SINE, OUT_SINE, IN_OUT_SINE, IN_EXPO, OUT_EXPO, IN_OUT_EXPO, IN_CIRC, OUT_CIRC, IN_OUT_CIRC, SIGMOID, SIGMOID_2, SIGMOID_3, SIGMOID_4, SIGMOID_5, SIGMOID_6, SIGMOID_7, OUT_ELASTIC, IN_BACK, OUT_BACK, DECELERATE, IN_OUT_CUBIC};
        return easingArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Easing.a = ZkmKeys.a(-9013688604926029903L, 4599953073514562494L, MethodHandles.lookup().lookupClass()).a(60604735909866L);
                        var20 = Easing.a ^ 66978240592258L;
                        var12_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var13_2 = 1; var13_2 < 8; ++var13_2) {
                            v2 = v2;
                            v2[var13_2] = (byte)(var20 << var13_2 * 8 >>> 56);
                        }
                        var12_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var11_3 = new String[33];
                        var17_4 = 0;
                        var16_5 = "\u009f\u00f7\u00a5\u00dciW\u00c1ldQ\u00e2\u00c9\u009a\u00fbc3\u0010,\u00ae>\u0014S\u0094\u00ec\u00d2\u00980\u00c5+#pH<\b\u00f8\u00cf\u00b6_V\u00a5\u00b9e\u0010\u0099\u00b5+GZM\u0000t\u00c9\u00b9\u00a8\u0018=Bor\u0010\u0000\u009c\u00ca@!\u0094\u000e;\fQ\u0096\u000f\u000f\u00dbj\u00e4\bx\u0080m\u00b5T\u000b\u00c9\u0017\u0010\u00fb,\u00f9\r{@\u0003o\u00e4\u00ae\u00a9[%9\u0093\u0016\br\u0083\u00f9\u00cdV\"\u0094\f\u0010;\u001c\u00e83\u00fd<ff\u00a5\u00c2nr\u00fc\u00ea\u00a8j\u0010\u0012\u0090\u0005\u00d3\u00c2a\u00ed\u00dfO\u0090fw\u00b9\u0096\u00d4\u00ab\u0010\u00ec\u00fa5\u00bf\u0088\u0090\u00fa\u008a]s\u00f5\u00fe\u0094\u00ddYj\u0010uQ\u00df1]`\u0002\u0088ri6\u00adR\u00f3\u00ff\u00c8\b\u0085\u0010e\u008f\u00ca\u001f\u00c9\u00ce\u0010|\u00ba\u00f1\u00df\u00f2 ^B\u00f1\u00e8\u00c7b\u001a\u00db\u00b52\u0010\u00ec\u00fa5\u00bf\u0088\u0090\u00fa\u008a\u0018g\\\u00f25\u00fabH\u0010\u0099\u00b5+GZM\u0000t\u00cd\u00c6D|}\u0098\u00944\u0010K\u00e8\u0003_\u00a5\u0091u\u00e1]\u00d2u\u00d3U\u00f9{8\u0010A+{\u00b8\u00b7\u00cf*\u00b1\u000eH\u00fc\u00c63\u00cc\u009f$\u0010\u0099\u00b5+GZM\u0000tm\u0085\u0080\u000b\u00e9\u00df\u00b1\u00c4\u0010\u009a\u00a2\u00d2\u0012\u001e\u00aaz\u009d\u00e1\f5Y\u00c8\u008c\u008b\u0093\u0010t\u0011)\u0018h\u0087\u00fb\u0019\u00ca\u0084Q\u00a4(\u00fdU]\b\u00d6\u00a9\u00e23 \u00b0\u00fd\u00ab\u0010\u0099\u00b5+GZM\u0000t\u00bdL\u00d8\u0018\u00ce\u0081\u00ed\u0013\u0010\u00a1|\u00f1\u00d2\u00e9'ry\u00c4q\u0011\u00d2\u0019\u00e0\u00cb:\u0010\u00dc4L,\u008e \u00b0\u0084\u00ecR\u00a8\u00e3\u00d2\u001al~\b\u009cq\u008d\u00bb\u0013\u00fc\u00be^\u0010\u00ec\u00fa5\u00bf\u0088\u0090\u00fa\u008a\u00e5G\u008eGl\u0018Xd\u0010B\u00b5\"\u00e4Y\u00cd\u00ae~E\u0006{:O\u00fdPh\u0010\u0099\u00b5+GZM\u0000t\u0089,AUc\u001df?\u0010<\u00eb\u0010n \u00bdl\u001cuL\u001c\u0007\u0015O<\u0016\u0010\u009f\u00f7\u00a5\u00dciW\u00c1l\u0096g\u00a1^U\u00ce*\u0015";
                        var18_6 = 478;
                        var15_7 = 16;
                        var14_8 = -1;
lbl19:
                        // 2 sources

                        while (true) {
                            v3 = ++var14_8;
                            v4 = var16_5.substring(v3, v3 + var15_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl24:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = Easing.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            var16_5 = "\u0099\u00b5+GZM\u0000t\u00b4\u0094gW\fU\u000b\u008c\b&\u00c5\u00e0\u00be\b\u0002\bp";
                            var18_6 = 25;
                            var15_7 = 16;
                            var14_8 = -1;
lbl33:
                            // 2 sources

                            while (true) {
                                v6 = ++var14_8;
                                v4 = var16_5.substring(v6, v6 + var15_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl38:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = Easing.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var12_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl50:
                        // 1 sources

                        ** continue;
                    }
                }
                Easing.d = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[58];
                var3_13 = 0;
                var4_14 = "\u00a5\b\u008a\u001b\u00bd\u00ec\u00ddL\u001exST\u009bw7\u00fe\u00a9\u0085|\u00f3\u0019\u00af\f\u00db\u0084\u0004\u0098qc%\u0096\u0091\to\b/\u00a0Q3\u0091\u00b3\u00e8\u00f6\u0089\u0003V\u00e2x$\u00d0\u001d\u00bb\u00ca\rLI\u00e3\u0097h\u000fkS&ICk~}H&\u00027\u008e;k\u009f\u00c1\u00e1\u0082<X&\u008d\u00031z\u00e8\u0015\u009cq\u0015\u0017]\f\u001c\u00dd\u00e0\u00bc\u00c7\u00c1c~\u00eb\u00bb\u0000\u00a6U\u00cb\u00a4s(Z\u008cC\u00c8Vv+\u00d2\u0007)\u0016\u001d\u00f24w\u00e2)\u001fON>\u00d8\u00dd\u0091\u00ef8\u00d3\u0088\u00bf\u00be\u0091\u0098=Ym\u00e0W\u00a0\u008e\u00e5\u00d8\u00db\u0096\u0097\u00f70\u00cc\u0012|\u00d9\u00d0I\u00bf\u00ff#\u009f\u00e4\u001a\u00cfUZv\u00c4\u00c1\u00af\f\u0085\u0092\u00bd8\u001d\b^\u00f1}\u0007\u000e<\u0014^?-\u00a8\u00fb\u00b5\u0014\u00056J\u00bc\u00ec\u0001y\u0016O++\u00b9;\u008c\u00ec\u000f\u0000p+\t\u00d13\u00cd\r\u00c7\u0094\u00e3(\u001cZ\u00f8A6\u0095\u009f/\u008b]e-\u00cc\u0087\b\u0006\u001e\u00a8\u0005\u00c3\u00a7*\u0017\u00b2\u00e1\u0097\u0088\u00b2\u00cb\u001b@\u00f0\u00b3u\u001f\u0092\r\u007f\u009f\u0013\u00e5\u00da\u00c7\u00f6h@\u00ba\u00e0Md~\u0007dw\u00d9\u0089\u00a1R\u00e3\u00a2m\u009ct\u001d\u0010\f\u0095{c3\u00fa\u000f\u00ff\u0089\u00a3\u00c6=\u001b%bx\u00bfL\u0019@\u00ba\u00a4\u0013\u00acn\u00de\u00d0$C{fmw\u0010\u00e6l0\u009f\u00ac\u00bc\u00b1\u00e14Y\u0083\u00a04\u001d\u008f\u00b9E\u0013\u00c2\u00d4\u00ed\u009e1qkq\u0012\u00a3J\u0011\u0011\u00a5\u008f\u00f3.\u0002e\u001es\u00a4\u00d6z\u00cc,u\u00b1\u00be*1\u00f5\u00d4\u008d>\u00f7E\u00bf\u00c3r\u001a\u00fez\u00dc\u00f5\u00a1\u00fd\u0012\u001f\u009e.\"\u0005\u00e9e\u00deN&?\u00c3\u008e\u00c5#\u00b9\u00eby\u0093\u00be\u0083\u000e_%\u00d2N\tK\u00e0fz\u0091\u00a9\u00e0\u00d3\u0013i\u00fa\u00a6\f\u00f7z?\u00b6?\u00fbhD\u00e6\u00e8\u00bc\u00e9\u00b3\u0084,\u00a5d8";
                var5_15 = 448;
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
lbl75:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00ee\u009bt<\u009a\u00f3\u00cd6;\u009dv\u00d3\u00de\u0014)G";
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
lbl88:
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
lbl101:
                // 1 sources

                ** continue;
            }
        }
        Easing.b = var6_12;
        Easing.c = new Integer[58];
        Easing.LINEAR = new Easing(var11_3[32], 0, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_QUAD = new Easing(var11_3[21], 1, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$1(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_QUAD = new Easing(var11_3[29], 2, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$2(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_OUT_QUAD = new Easing(var11_3[26], 3, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$3(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_CUBIC = new Easing(var11_3[1], 4, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$4(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_CUBIC = new Easing(var11_3[4], 5, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$5(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_QUART = new Easing(var11_3[20], 6, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$6(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_QUART = new Easing(var11_3[16], 7, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$7(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_OUT_QUART = new Easing(var11_3[10], 8, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$8(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_QUINT = new Easing(var11_3[19], 9, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$9(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_QUINT = new Easing(var11_3[9], 10, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$10(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_OUT_QUINT = new Easing(var11_3[14], 11, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$11(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_SINE = new Easing(var11_3[25], 12, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$12(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_SINE = new Easing(var11_3[8], 13, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$13(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_OUT_SINE = new Easing(var11_3[27], 14, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$14(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_EXPO = new Easing(var11_3[7], 15, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$15(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_EXPO = new Easing(var11_3[13], 16, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$16(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_OUT_EXPO = new Easing(var11_3[6], 17, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$17(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_CIRC = new Easing(var11_3[2], 18, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$18(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_CIRC = new Easing(var11_3[17], 19, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$19(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_OUT_CIRC = new Easing(var11_3[30], 20, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$20(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.SIGMOID = new Easing(var11_3[5], 21, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$21(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.SIGMOID_2 = new Easing(var11_3[31], 22, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$22(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.SIGMOID_3 = new Easing(var11_3[28], 23, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$23(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.SIGMOID_4 = new Easing(var11_3[18], 24, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$24(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.SIGMOID_5 = new Easing(var11_3[22], 25, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$25(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.SIGMOID_6 = new Easing(var11_3[15], 26, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$26(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.SIGMOID_7 = new Easing(var11_3[3], 27, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$27(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_ELASTIC = new Easing(var11_3[11], 28, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$28(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_BACK = new Easing(var11_3[12], 29, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$29(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.OUT_BACK = new Easing(var11_3[24], 30, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$30(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.DECELERATE = new Easing(var11_3[23], 31, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$31(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.IN_OUT_CUBIC = new Easing(var11_3[0], 32, (Function1<Double, Double>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$32(double ), (Ljava/lang/Double;)Ljava/lang/Double;)());
        Easing.$VALUES = Easing.$values();
        Easing.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])Easing.$VALUES);
    }

    public static void zkmSetFlag(int n) {
        C = n;
    }

    public static int zkmGetFlag() {
        return C;
    }

    public static int O() {
        return 0;
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

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x556B;
        if (c[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = b[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])d.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_s", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Easing.c[n2] = n3;
        }
        return c[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Easing.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/_s" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Easing.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

