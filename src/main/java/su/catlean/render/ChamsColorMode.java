/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

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

public final class ChamsColorMode
extends Enum {
    public static final ChamsColorMode SINGLE;
    public static final ChamsColorMode RGB;
    public static final ChamsColorMode DOUBLE;
    public static final ChamsColorMode DOUBLE_UP;
    public static final ChamsColorMode TV;
    private static final ChamsColorMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ChamsColorMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ChamsColorMode[] values() {
        return (ChamsColorMode[])$VALUES.clone();
    }

    public static ChamsColorMode valueOf(String string) {
        return Enum.valueOf(ChamsColorMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ChamsColorMode[] $values() {
        ChamsColorMode[] chamsColorModeArray = new ChamsColorMode[]{SINGLE, RGB, DOUBLE, DOUBLE_UP, TV};
        return chamsColorModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(4430562677826445128L, 131152190543300301L, MethodHandles.lookup().lookupClass()).a(103681074798373L) ^ 84674039231332L;
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
                var5_5 = "\u0012IF\u001b\u00efwv\u00a7\b\u00f8\u00e4\u00d0\u001c\u00c2bu\u00b0\b3R\u00f3\u001a\u0096\u00c6\u00dd\u0095";
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
                    var0_3[var6_4++] = ChamsColorMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00d4`\u00a0\u0003\u00b0\u00d4\u00b3o^\u00d5F0\u00cen$\u008f\b\u00a6\u008bH\u00e3\u0005(\u0087&";
                    var7_6 = 25;
                    var4_7 = 16;
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
                    var0_3[var6_4++] = ChamsColorMode.zkmDecodeUtf8(var8_9).intern();
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
        ChamsColorMode.SINGLE = new ChamsColorMode(var0_3[2], 0);
        ChamsColorMode.RGB = new ChamsColorMode(var0_3[1], 1);
        ChamsColorMode.DOUBLE = new ChamsColorMode(var0_3[0], 2);
        ChamsColorMode.DOUBLE_UP = new ChamsColorMode(var0_3[3], 3);
        ChamsColorMode.TV = new ChamsColorMode(var0_3[4], 4);
        ChamsColorMode.$VALUES = ChamsColorMode.$values();
        ChamsColorMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ChamsColorMode.$VALUES);
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

