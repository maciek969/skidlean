/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class TranslatorUrLangMode
extends Enum {
    public static final TranslatorUrLangMode AM;
    public static final TranslatorUrLangMode AR;
    public static final TranslatorUrLangMode EU;
    public static final TranslatorUrLangMode BN;
    public static final TranslatorUrLangMode BG;
    public static final TranslatorUrLangMode CA;
    public static final TranslatorUrLangMode CHR;
    public static final TranslatorUrLangMode HR;
    public static final TranslatorUrLangMode CS;
    public static final TranslatorUrLangMode DA;
    public static final TranslatorUrLangMode NL;
    public static final TranslatorUrLangMode EN;
    public static final TranslatorUrLangMode ET;
    public static final TranslatorUrLangMode FIL;
    public static final TranslatorUrLangMode FI;
    public static final TranslatorUrLangMode FR;
    public static final TranslatorUrLangMode DE;
    public static final TranslatorUrLangMode EL;
    public static final TranslatorUrLangMode GU;
    public static final TranslatorUrLangMode IW;
    public static final TranslatorUrLangMode HI;
    public static final TranslatorUrLangMode HU;
    public static final TranslatorUrLangMode IS;
    public static final TranslatorUrLangMode ID;
    public static final TranslatorUrLangMode IT;
    public static final TranslatorUrLangMode JA;
    public static final TranslatorUrLangMode KN;
    public static final TranslatorUrLangMode KO;
    public static final TranslatorUrLangMode LV;
    public static final TranslatorUrLangMode LT;
    public static final TranslatorUrLangMode MS;
    public static final TranslatorUrLangMode ML;
    public static final TranslatorUrLangMode MR;
    public static final TranslatorUrLangMode NO;
    public static final TranslatorUrLangMode PL;
    public static final TranslatorUrLangMode RO;
    public static final TranslatorUrLangMode RU;
    public static final TranslatorUrLangMode SR;
    public static final TranslatorUrLangMode SK;
    public static final TranslatorUrLangMode SL;
    public static final TranslatorUrLangMode ES;
    public static final TranslatorUrLangMode SW;
    public static final TranslatorUrLangMode SV;
    public static final TranslatorUrLangMode TA;
    public static final TranslatorUrLangMode TE;
    public static final TranslatorUrLangMode TH;
    public static final TranslatorUrLangMode TR;
    public static final TranslatorUrLangMode UR;
    public static final TranslatorUrLangMode UK;
    public static final TranslatorUrLangMode VI;
    public static final TranslatorUrLangMode CY;
    public static final TranslatorUrLangMode CN;
    private static final TranslatorUrLangMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String[] B;
    private static final long a;
    private static final long[] b;
    private static final Integer[] c;
    private static final Map d;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private TranslatorUrLangMode() {
        void var2_-1;
        void var1_-1;
    }

    public static TranslatorUrLangMode[] values() {
        return (TranslatorUrLangMode[])$VALUES.clone();
    }

    public static TranslatorUrLangMode valueOf(String string) {
        return Enum.valueOf(TranslatorUrLangMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final TranslatorUrLangMode[] $values() {
        TranslatorUrLangMode[] translatorUrLangModeArray = new TranslatorUrLangMode[]{AM, AR, EU, BN, BG, CA, CHR, HR, CS, DA, NL, EN, ET, FIL, FI, FR, DE, EL, GU, IW, HI, HU, IS, ID, IT, JA, KN, KO, LV, LT, MS, ML, MR, NO, PL, RO, RU, SR, SK, SL, ES, SW, SV, TA, TE, TH, TR, UR, UK, VI, CY, CN};
        return translatorUrLangModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        TranslatorUrLangMode.a = ZkmKeys.a(-5835851797119798213L, -7030252788014220648L, MethodHandles.lookup().lookupClass()).a(7043315162525L);
                        var20 = TranslatorUrLangMode.a ^ 47501660206718L;
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
                        var11_3 = new String[52];
                        var17_4 = 0;
                        var16_5 = "\u00b3\u00a7\u00f0\u0095sk],\b)\u00c7\u00de\u00d6b?\u00ebn\b\u0099Nq\u00cd\u0004\u00a1*\u00c8\bt\u008d\u00d1\u0088\u000f\u00dd\u00e8\u00c5\b\u009b\u00e1;\u00a5\u008d\u00d1\u00de$\b\u00a3\u0092Z\u00c4Q\u008aER\b@\u00f1\u00b4\u00e2t\u00c7,D\b*\u00b2\u00e0\u00ca\u008ff\u00fa\u00ee\b]S\u00de\u0089\u00d6\u00f3\u00c4\u00c1\b\u0005\u00c0\u00f2\u0091\u00fa\u00ca\u00bbB\b\t\u00e7\u001bJ:\u00f2\u00b4\u0010\b\u00f5VN\u00c9\u00b9\u00c5\u00df\u00f6\b\u00b1\u00cb\u001eR\u00804ET\b\u0095\u00dfSZ]\u00a2\u00bcR\b\u00824\u00e8\u0007\u00bf)\u00b7g\bz\u0095\u0007\u00b3 +\u00d6\u00b6\b\u0083\u00a3\u0080,\u00c3\u00f1\u008d\u00b2\b\r\u0090.\u00f6\u000e\u0018\u000e\u00e1\b\u00c0\u00d0\u0015\u0096\u00e1~;\u00ae\b\u00876z\u00a4\u00cd\u00c4\u00b4\u0015\b\"\u001e\u00ad\u00e9\u009c\fb\u009c\bY\u00bbBI\u0095$\u00e81\bV\u00051F\u00edUb\u0094\b\u00a8\u00ec\u00c6\u00dbz)\t\u00ab\b\u00d8\u0005,A]_\u00fc\u00c1\b\u0081\u00c5\bJF/\u00d3\u00c8\bj\r\u000e\u00d6\u0005\n\u009e\u00f7\bR\u00a7\u00ae\u00ce\u0002I\u00d5s\b\u00f5=\u001e\u00e0>\u00f5Z\u00c6\b\u0082\u001arq\u00f0\u008f0f\b\u0085\u0096Y\u00b1\u0006@f#\bi \u0097w\" \u0007\u009a\bX\u00dc\u00b7\u0000a\u009519\b\u00c1\u00e3\u00d1\u00fba\u0019\u00ea\"\b\u0007JD|P0\u0000\u00fb\b\u0000!\u00f8\u0019#\u00c6\u00e43\bk\u00e8\u00cf\u0004N\u00aa\u00c6\u0007\b\u009c\u001a\u00aa\u00b2#\u00a3\u00d2/\b~\u00cd\u00d4c\u00b8aq\u00ea\b\u0098\u000e!\u00e8U;\u00a23\bO\u0003\u0012UDL\u00f9a\b\u009et\u00c6\u00daR\u00f6\u00b5\u0011\b\u008b\u00d3p5,f\u0004\u0000\b\u00a3-\u00a3\u0089\u00fe\u00a4\u00bbZ\bA\u00a8\u0016\u00c1\u00e8\u00af/\u00e0\b=C\u00c4\u00eb\u00beQi%\b+$P;\u00b3O\u00dd\u00c4\bBM\f\u0015\u00b0%\u00e9\u0004\bU\u001f\u00d3\u00a4\u00af\u00f3NK\bd\u00c3Y\u00bd\u009e\u009bC\u008c";
                        var18_6 = 449;
                        var15_7 = 8;
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
                            var11_3[var17_4++] = TranslatorUrLangMode.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            var16_5 = "\u00bfCN\u0011%\\=\u009c\b\u008an{\u0090\u008f\u008dq[";
                            var18_6 = 17;
                            var15_7 = 8;
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
                            var11_3[var17_4++] = TranslatorUrLangMode.zkmDecodeUtf8(var19_9).intern();
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
                TranslatorUrLangMode.d = new HashMap<K, V>(13);
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
                var6_12 = new long[93];
                var3_13 = 0;
                var4_14 = "z\u00ca\u0090\u00c5\u00f7\u0002\u00c6\u001a\u00b7'Y\u00aa\u00e1\u000f\u00d7\u001el\u00db\u00b4\u00d1~%(\u0088 \u0012\u00f2\u00b05\u00de\u0094\u0000\u00fd\u0018\u0017:\u0096\u0003\u001f\u001d\u00a1\u00c5\u008ew\u009f\u0017\u007f6v\u0096!\u0082\r5\u008b\u00d3>\t\u00bb\u00ce\u0006\u00e7U\u00daRy\u008f%J|\u00e1\u00ac\u00b8\u00ba\u0000\u0010\u009c\u00dd\u007f\u00c7\u0014^\t\u0084\u008b\u00e4!\f\u0080.\u00aco\u0094>5\u008e\u009d\u00b7\u0003Zt\u00f4\u00ef\u00a1\u00a8G>~e\u0093\u0093\u00cf\u00a4\u008bA\u00cd\u0084\u0005\u00d1\t\u009avpt\"\u00efN\u0011%\u009cT\u008a\u00fcE\u008a\u00a4\u0012^\u00ef\u00af\u00d9<Z@^d\u00df\u0095\u00d0,-\u001b\u00e0\u00d0\u0012\u00d9\u00e1:\u00ccV\u00f44l\u00dc\u001b\u001a\u0083@\u007f\u0011op\u00e8*\u00979G%\u00ff\u0094\u00da\u00ef\u00c7\u0095\u00f3m\u00b9?:\u00eb\u00ce!@\u00d7\u00b8v<\u00b5g\u00c2\u00ef\u00ac\u000f\u0098'\u001b\u00fdm\u008b\u0010\u00e6\u008d\u0095\u0005.[\u0085\u00dc\u008c\u00a2\u00f4B\u00a4\u00f8\u00ca\u0086\u00fd\u00a1\u00b4d\u0017D==\u00e3\u00ben]\u00b5]\u00cd\u0000\u00ae\u0014\u0091 \u00a8\u00e3\u00ee8\u00e0Y\u00bbk\u00987\u00df7\u00f3\u00aa\u00de\u00bf\u0097hG\u00d4\u00d6\"I:\u00c6\u0014r\u00b9#\u009fi\u001aD\u001b\u00b1In\u00e7\u00a9-\u00d4\u00f5FIo\u00b8\u000bO\u00b7\u00ba\u0003\u00c4\u00ad\u00a7\u00ca\u00cc\u0087\u00b0\u00fc2\u00d1%\u00ba\u0089X\u009f\u0003;p\u00ba\u00ebb;\u00d0\u00f5\u0017\u00cd<\u00b6c\u0083\u0099\u0010`\"8E\u00e4\u00b7\u00ee\u00a4\u0095t\u000f\u0092\u00a0>;.0?q\u001a\u000f\u00d8^7\u0082\u0018\u00bbX\u00e0AM^;\u00cc\u00bcx\u00c0\u00f0\u00e6\u008f\u00fa\u008caj\u0019\u00a3\u00aa\u00a8<\u00a2!\u008f<\u008fet\u00ea\u00b4\u00f4\u008d\u000e\u00c3W1\u00e6\t\u001be\u00e3\u0005v\u00c0)\u00b1\"[$\u0014\u0083\u008f8\u00dd\"\u00e8,\u0015Iy\u0018\u00e7s\u00f7\u0081~\u0018\u00c8;z\u00a7\u00d8\u008c\u00f8\u00ac4\u00d7\u00ed\u00f7g\u00a06\u00db*\u00d2c\u00bcp\u000f\u0087\u00de\u0099\u00bc\u00fbJ\u0096N\u00c7)1\u00d9\u00f1\u00b2g+\u00cb\u00c0A\u001a\u00c9\u000b\u00f9a\u00abW\u007f\u0096\u00c1\u0018\u009e\u001d\u00078I/w{\u00c6\u00aa3\u00e32\u00d3\u00bc\u00ed\u0007k];R\u0081\u00e0\u00dbi\u009d\u00b9`\n\u00b1K\u00c4y\u00abN\u00c7\u008ce\u00e48(\u00a1]X\u0016\u009fiE\u00e3`q\u00c8~\u00c6\u00b2\u00b4\u00af\u00eb\u0005\u00b8\u0007\u00a4\u008a\u00c7/\u00e43\u0085\u00a3\u0095q\u00ef\u008b\u00c6\u0084\u00ac\u00ab\u00ff\u00b9;\u008eJ\u009c\u00ec\u00a6\u00e6\u00c0|\u0016lY\u00d5}\u0000\u00b7lr\u008d\u00d4K{C\u0017\u00dbh\u00fcn5\u00ac2\u00f8\u00c6N\u0085`)z\u00b6\u0083\u00b3PqQ\u00e9\t\u0005C\u00d1=\u00c1?d;\u00bdj\u00cf\u0084\u00a5\u00e0\u00f3{\u00bb3\\X\u00d7\u00edOz\u00bb`\u009b\u0016 }\u00fe\u00c1\u0098\u009eeX\u0013\u0000\u007f\u00df\u00bbV|\fQ\u0003\u0003Q\u001bY\u00b5D;\u00b6\u00de0#No'WAV\u00c1r2/\u00beoN\u0016\u0019\u009c\u0014v,l\u000f\u00fa\u00d5\u00d5\u00ba\u001bZ\u0015\u00e3\u00d2\u00fb5\u00d9\u00c6f\b\u00b0K 9w\u00e8{\u00df\u0095\u00c4\u0001\u00db\u00ff`(\u00f2>\u00del\u0097\u009bA\u009d\u00fe\u0013\u00d9\\G\u00b7\u00e2\u009c\u0092\u00a8\u008f";
                var5_15 = 728;
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
                    var4_14 = "\u00b4\u00ffX\u00c9@\t\u0099#\\\u0080#T#\u0006\u0007\u0088";
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
        TranslatorUrLangMode.b = var6_12;
        TranslatorUrLangMode.c = new Integer[93];
        TranslatorUrLangMode.AM = new TranslatorUrLangMode(var11_3[13], 0);
        TranslatorUrLangMode.AR = new TranslatorUrLangMode(var11_3[39], 1);
        TranslatorUrLangMode.EU = new TranslatorUrLangMode(var11_3[2], 2);
        TranslatorUrLangMode.BN = new TranslatorUrLangMode(var11_3[10], 3);
        TranslatorUrLangMode.BG = new TranslatorUrLangMode(var11_3[16], 4);
        TranslatorUrLangMode.CA = new TranslatorUrLangMode(var11_3[25], 5);
        TranslatorUrLangMode.CHR = new TranslatorUrLangMode(var11_3[48], 6);
        TranslatorUrLangMode.HR = new TranslatorUrLangMode(var11_3[47], 7);
        TranslatorUrLangMode.CS = new TranslatorUrLangMode(var11_3[35], 8);
        TranslatorUrLangMode.DA = new TranslatorUrLangMode(var11_3[3], 9);
        TranslatorUrLangMode.NL = new TranslatorUrLangMode(var11_3[1], 10);
        TranslatorUrLangMode.EN = new TranslatorUrLangMode(var11_3[30], 11);
        TranslatorUrLangMode.ET = new TranslatorUrLangMode(var11_3[42], 12);
        TranslatorUrLangMode.FIL = new TranslatorUrLangMode(var11_3[51], 13);
        TranslatorUrLangMode.FI = new TranslatorUrLangMode(var11_3[34], 14);
        TranslatorUrLangMode.FR = new TranslatorUrLangMode(var11_3[29], 15);
        TranslatorUrLangMode.DE = new TranslatorUrLangMode(var11_3[24], 16);
        TranslatorUrLangMode.EL = new TranslatorUrLangMode(var11_3[23], 17);
        TranslatorUrLangMode.GU = new TranslatorUrLangMode(var11_3[28], 18);
        TranslatorUrLangMode.IW = new TranslatorUrLangMode(var11_3[43], 19);
        TranslatorUrLangMode.HI = new TranslatorUrLangMode(var11_3[0], 20);
        TranslatorUrLangMode.HU = new TranslatorUrLangMode(var11_3[19], 21);
        TranslatorUrLangMode.IS = new TranslatorUrLangMode(var11_3[20], 22);
        TranslatorUrLangMode.ID = new TranslatorUrLangMode(var11_3[9], 23);
        TranslatorUrLangMode.IT = new TranslatorUrLangMode(var11_3[41], 24);
        TranslatorUrLangMode.JA = new TranslatorUrLangMode(var11_3[31], 25);
        TranslatorUrLangMode.KN = new TranslatorUrLangMode(var11_3[6], 26);
        TranslatorUrLangMode.KO = new TranslatorUrLangMode(var11_3[32], 27);
        TranslatorUrLangMode.LV = new TranslatorUrLangMode(var11_3[17], 28);
        TranslatorUrLangMode.LT = new TranslatorUrLangMode(var11_3[38], 29);
        TranslatorUrLangMode.MS = new TranslatorUrLangMode(var11_3[50], 30);
        TranslatorUrLangMode.ML = new TranslatorUrLangMode(var11_3[45], 31);
        TranslatorUrLangMode.MR = new TranslatorUrLangMode(var11_3[7], 32);
        TranslatorUrLangMode.NO = new TranslatorUrLangMode(var11_3[40], 33);
        TranslatorUrLangMode.PL = new TranslatorUrLangMode(var11_3[5], 34);
        TranslatorUrLangMode.RO = new TranslatorUrLangMode(var11_3[46], 35);
        TranslatorUrLangMode.RU = new TranslatorUrLangMode(var11_3[14], 36);
        TranslatorUrLangMode.SR = new TranslatorUrLangMode(var11_3[22], 37);
        TranslatorUrLangMode.SK = new TranslatorUrLangMode(var11_3[11], 38);
        TranslatorUrLangMode.SL = new TranslatorUrLangMode(var11_3[4], 39);
        TranslatorUrLangMode.ES = new TranslatorUrLangMode(var11_3[33], 40);
        TranslatorUrLangMode.SW = new TranslatorUrLangMode(var11_3[36], 41);
        TranslatorUrLangMode.SV = new TranslatorUrLangMode(var11_3[37], 42);
        TranslatorUrLangMode.TA = new TranslatorUrLangMode(var11_3[18], 43);
        TranslatorUrLangMode.TE = new TranslatorUrLangMode(var11_3[26], 44);
        TranslatorUrLangMode.TH = new TranslatorUrLangMode(var11_3[27], 45);
        TranslatorUrLangMode.TR = new TranslatorUrLangMode(var11_3[44], 46);
        TranslatorUrLangMode.UR = new TranslatorUrLangMode(var11_3[21], 47);
        TranslatorUrLangMode.UK = new TranslatorUrLangMode(var11_3[49], 48);
        TranslatorUrLangMode.VI = new TranslatorUrLangMode(var11_3[8], 49);
        TranslatorUrLangMode.CY = new TranslatorUrLangMode(var11_3[12], 50);
        TranslatorUrLangMode.CN = new TranslatorUrLangMode(var11_3[15], 51);
        TranslatorUrLangMode.$VALUES = TranslatorUrLangMode.$values();
        TranslatorUrLangMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])TranslatorUrLangMode.$VALUES);
    }

    public static void zkmSetFlag(String[] stringArray) {
        B = stringArray;
    }

    public static String[] zkmGetFlag() {
        return B;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1BFB;
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
                throw new RuntimeException("su/catlean/iy", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TranslatorUrLangMode.c[n2] = n3;
        }
        return c[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TranslatorUrLangMode.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/iy" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TranslatorUrLangMode.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

