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

public final class ColorMode
extends Enum {
    public static final ColorMode CUSTOM;
    public static final ColorMode SYNC;
    public static final ColorMode RGB;
    public static final ColorMode SKY;
    public static final ColorMode LIGHT_RGB;
    private static final ColorMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String[] W;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ColorMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ColorMode[] values() {
        return (ColorMode[])$VALUES.clone();
    }

    public static ColorMode valueOf(String string) {
        return Enum.valueOf(ColorMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ColorMode[] $values() {
        ColorMode[] colorModeArray = new ColorMode[]{CUSTOM, SYNC, RGB, SKY, LIGHT_RGB};
        return colorModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-6764769074393202073L, 5356158761306579670L, MethodHandles.lookup().lookupClass()).a(200735882615803L) ^ 111353807826257L;
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
                var5_5 = "\u00f9b\u001f~\u00ff`\u00f59\b\u0005C\u0086\u00f7O\u0005\u00d4\u0083\u0010\u0095F\u0016D\u0090\u00f4g\u00b54= \u00c3[r\u0004Y";
                var7_6 = 34;
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
                    var0_3[var6_4++] = ColorMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u0088\u00b6\u0012o\u00d5fi\u00d0\b\u0093\u009d\u008d\u00e6\u00cdU\u000f\u00b8";
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
                    var0_3[var6_4++] = ColorMode.zkmDecodeUtf8(var8_9).intern();
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
        ColorMode.CUSTOM = new ColorMode(var0_3[3], 0);
        ColorMode.SYNC = new ColorMode(var0_3[4], 1);
        ColorMode.RGB = new ColorMode(var0_3[0], 2);
        ColorMode.SKY = new ColorMode(var0_3[1], 3);
        ColorMode.LIGHT_RGB = new ColorMode(var0_3[2], 4);
        ColorMode.$VALUES = ColorMode.$values();
        ColorMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ColorMode.$VALUES);
    }

    public static void zkmSetFlag(String[] stringArray) {
        W = stringArray;
    }

    public static String[] zkmGetFlag() {
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

