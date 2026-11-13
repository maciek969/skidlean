/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class SwapMode
extends Enum {
    public static final SwapMode NORMAL;
    public static final SwapMode SILENT;
    public static final SwapMode INVENTORY_SILENT;
    public static final SwapMode INVENTORY_NORMAL;
    public static final SwapMode SILENT_FULL;
    public static final SwapMode NORMAL_FULL;
    private static final /* synthetic */ SwapMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static int[] W;
    private static final long a;
    private static final long b;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SwapMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SwapMode[] values() {
        return (SwapMode[])$VALUES.clone();
    }

    public static SwapMode valueOf(String string) {
        return Enum.valueOf(SwapMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ SwapMode[] $values() {
        SwapMode[] swapModeArray = new SwapMode[(int)b];
        swapModeArray[0] = NORMAL;
        swapModeArray[1] = SILENT;
        swapModeArray[2] = INVENTORY_SILENT;
        swapModeArray[3] = INVENTORY_NORMAL;
        swapModeArray[4] = SILENT_FULL;
        swapModeArray[5] = NORMAL_FULL;
        return swapModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    SwapMode.a = ZkmKeys.a(-2460490841605179726L, 3010727246982738471L, MethodHandles.lookup().lookupClass()).a(138189253279460L);
                    var14 = SwapMode.a ^ 139451424496508L;
                    new int[0];
                    var6_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var7_2 = 1; var7_2 < 8; ++var7_2) {
                        v2 = v2;
                        v2[var7_2] = (byte)(var14 << var7_2 * 8 >>> 56);
                    }
                    var6_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var5_3 = new String[6];
                    var11_4 = 0;
                    var10_5 = "\u00e0\u00c9\u0084\u009a\u00ce@7\u00af\u0018\u00e5f\u0081\u00a0;\u0087\u0005s$`\t\"n\u00fa\u00e2\u00fbf>\u00f1\u00e0\u00fbm\u0007\u00fd\u0010\u00c2\u00dbk\u00ff\u00aaQ\u00bao\u00b0\u00a5\u00d4@\u00a8\u000f\u0080\u00d3\u0018\u00e5f\u0081\u00a0;\u0087\u0005s\u00c6\u00c7\u0081sn\u00e2\u00ee\u00b9\u00cf\u00faf|\u009d\u00b9\u00f8\u00a5";
                    var12_6 = 75;
                    var9_7 = 8;
                    var8_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var8_8;
                        v4 = var10_5.substring(v3, v3 + var9_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var5_3[var11_4++] = SwapMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        var10_5 = "\u00e0\u00d8\u008fih\u00ca\u0092\u00f3\u0010\u0002\u0010(\u00e0-F\u00c3]\t[\u008f\u00f86_\u00ac\u0089";
                        var12_6 = 25;
                        var9_7 = 8;
                        var8_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var8_8;
                            v4 = var10_5.substring(v6, v6 + var9_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var5_3[var11_4++] = SwapMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var6_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl64:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{15, -21, -19, -84, 14, -89, 36, 90});
        ** while (true)
        SwapMode.b = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        SwapMode.NORMAL = new SwapMode(var5_3[4], 0);
        SwapMode.SILENT = new SwapMode(var5_3[0], 1);
        SwapMode.INVENTORY_SILENT = new SwapMode(var5_3[3], 2);
        SwapMode.INVENTORY_NORMAL = new SwapMode(var5_3[1], 3);
        SwapMode.SILENT_FULL = new SwapMode(var5_3[5], 4);
        SwapMode.NORMAL_FULL = new SwapMode(var5_3[2], 5);
        SwapMode.$VALUES = SwapMode.$values();
        SwapMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])SwapMode.$VALUES);
    }

    public static void zkmSetFlag(int[] nArray) {
        W = nArray;
    }

    public static int[] zkmGetFlag() {
        return W;
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
}

