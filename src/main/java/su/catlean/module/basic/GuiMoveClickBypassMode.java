/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

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

public final class GuiMoveClickBypassMode
extends Enum {
    public static final GuiMoveClickBypassMode NONE;
    public static final GuiMoveClickBypassMode DISABLE_CLICKS;
    public static final GuiMoveClickBypassMode STRICT_NCP;
    public static final GuiMoveClickBypassMode STRICT_NCP_2;
    public static final GuiMoveClickBypassMode MATRIX_NCP;
    public static final GuiMoveClickBypassMode LEGIT;
    private static final GuiMoveClickBypassMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int[] b;
    private static final long a;
    private static final long c;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private GuiMoveClickBypassMode() {
        void var2_-1;
        void var1_-1;
    }

    public static GuiMoveClickBypassMode[] values() {
        return (GuiMoveClickBypassMode[])$VALUES.clone();
    }

    public static GuiMoveClickBypassMode valueOf(String string) {
        return Enum.valueOf(GuiMoveClickBypassMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final GuiMoveClickBypassMode[] $values() {
        GuiMoveClickBypassMode[] guiMoveClickBypassModeArray = new GuiMoveClickBypassMode[(int)c];
        guiMoveClickBypassModeArray[0] = NONE;
        guiMoveClickBypassModeArray[1] = DISABLE_CLICKS;
        guiMoveClickBypassModeArray[2] = STRICT_NCP;
        guiMoveClickBypassModeArray[3] = STRICT_NCP_2;
        guiMoveClickBypassModeArray[4] = MATRIX_NCP;
        guiMoveClickBypassModeArray[5] = LEGIT;
        return guiMoveClickBypassModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    GuiMoveClickBypassMode.a = ZkmKeys.a(-4406643182448525154L, 6278846469353377371L, MethodHandles.lookup().lookupClass()).a(259725957479237L);
                    var14 = GuiMoveClickBypassMode.a ^ 128218432040569L;
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
                    var10_5 = "\f\u008eI/\u00ab\u00ca\u009f7\u0010\u0003{\u001d$=&T\u00f5\u00d6@\u00e2\u00a5\u00ac\u00e4\u001b\u00ec\u0010Z\u0019\u00f2\u00b9\u00fe\u0012\u00b2cI\u00ac\u00fd\u00cd\u00f8\u00e0=\u00ef\u0010\u0003{\u001d$=&T\u00f5\u0097\u00deN^\u0094\u00ba\u00ee\u00ce";
                    var12_6 = 59;
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
                        var5_3[var11_4++] = GuiMoveClickBypassMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        var10_5 = "\u00c5k=\u00d2a\u009cA4}\u00b0\u00e3\u008c\u0001X:y\b\u00fd\u00c6\u008e\u00e5QH\u00f0\u00ce";
                        var12_6 = 25;
                        var9_7 = 16;
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
                        var5_3[var11_4++] = GuiMoveClickBypassMode.zkmDecodeUtf8(var13_9).intern();
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
        var4_12 = var0_10.doFinal(new byte[]{73, 75, -47, -111, 47, 103, -72, -14});
        ** while (true)
        GuiMoveClickBypassMode.c = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        GuiMoveClickBypassMode.NONE = new GuiMoveClickBypassMode(var5_3[5], 0);
        GuiMoveClickBypassMode.DISABLE_CLICKS = new GuiMoveClickBypassMode(var5_3[2], 1);
        GuiMoveClickBypassMode.STRICT_NCP = new GuiMoveClickBypassMode(var5_3[3], 2);
        GuiMoveClickBypassMode.STRICT_NCP_2 = new GuiMoveClickBypassMode(var5_3[1], 3);
        GuiMoveClickBypassMode.MATRIX_NCP = new GuiMoveClickBypassMode(var5_3[4], 4);
        GuiMoveClickBypassMode.LEGIT = new GuiMoveClickBypassMode(var5_3[0], 5);
        GuiMoveClickBypassMode.$VALUES = GuiMoveClickBypassMode.$values();
        GuiMoveClickBypassMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])GuiMoveClickBypassMode.$VALUES);
    }

    public static void zkmSetFlag(int[] nArray) {
        b = nArray;
    }

    public static int[] zkmGetFlag() {
        return b;
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

