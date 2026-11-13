/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

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

final class BreakEspMode
extends Enum {
    public static final BreakEspMode DEFAULT;
    public static final BreakEspMode GROW;
    public static final BreakEspMode SHRINK;
    public static final BreakEspMode FILL;
    public static final BreakEspMode EMPTY;
    private static final BreakEspMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private BreakEspMode() {
        void var2_-1;
        void var1_-1;
    }

    public static BreakEspMode[] values() {
        return (BreakEspMode[])$VALUES.clone();
    }

    public static BreakEspMode valueOf(String string) {
        return Enum.valueOf(BreakEspMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final BreakEspMode[] $values() {
        BreakEspMode[] breakEspModeArray = new BreakEspMode[]{DEFAULT, GROW, SHRINK, FILL, EMPTY};
        return breakEspModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-184470355444370492L, 67663749564453457L, MethodHandles.lookup().lookupClass()).a(126351036223725L) ^ 58131833536961L;
                var1_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var2_2 = 1; var2_2 < 8; ++var2_2) {
                    v2 = v2;
                    v2[var2_2] = (byte)(var9 << var2_2 * 8 >>> 56);
                }
                var1_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var0_3 = new String[5];
                var6_4 = 0;
                var5_5 = "*L\u0012\u00d9\u001d\u00f7\u00a2{\bN\u0088\u0012\u0092\u00fb\u00d4&{\b\u00e0\u0007cWvP\u0017\u009f";
                var7_6 = 26;
                var4_7 = 8;
                var3_8 = -1;
lbl18:
                // 2 sources

                while (true) {
                    v3 = ++var3_8;
                    v4 = var5_5.substring(v3, v3 + var4_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl23:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = BreakEspMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00ce\u00cbr\u008e\u00ee\u009c\u00d7\u00ea\b'6\u00965\u0096\u00c6\u00cd\u00e4";
                    var7_6 = 17;
                    var4_7 = 8;
                    var3_8 = -1;
lbl32:
                    // 2 sources

                    while (true) {
                        v6 = ++var3_8;
                        v4 = var5_5.substring(v6, v6 + var4_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl37:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = BreakEspMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var1_1.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl49:
                // 1 sources

                ** continue;
            }
        }
        BreakEspMode.DEFAULT = new BreakEspMode(var0_3[3], 0);
        BreakEspMode.GROW = new BreakEspMode(var0_3[1], 1);
        BreakEspMode.SHRINK = new BreakEspMode(var0_3[2], 2);
        BreakEspMode.FILL = new BreakEspMode(var0_3[4], 3);
        BreakEspMode.EMPTY = new BreakEspMode(var0_3[0], 4);
        BreakEspMode.$VALUES = BreakEspMode.$values();
        BreakEspMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])BreakEspMode.$VALUES);
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

