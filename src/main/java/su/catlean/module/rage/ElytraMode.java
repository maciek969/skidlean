/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

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

final class ElytraMode
extends Enum {
    public static final ElytraMode Vanilla;
    public static final ElytraMode Creative;
    public static final ElytraMode MATRIX_JUMP;
    public static final ElytraMode GRIM_GLIDE;
    private static final ElytraMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ElytraMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ElytraMode[] values() {
        return (ElytraMode[])$VALUES.clone();
    }

    public static ElytraMode valueOf(String string) {
        return Enum.valueOf(ElytraMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ElytraMode[] $values() {
        ElytraMode[] elytraModeArray = new ElytraMode[]{Vanilla, Creative, MATRIX_JUMP, GRIM_GLIDE};
        return elytraModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-7185450343715966961L, 2113565741700202873L, MethodHandles.lookup().lookupClass()).a(208607582065856L) ^ 124638081348036L;
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
                var0_3 = new String[4];
                var6_4 = 0;
                var5_5 = "jC\u00fa*\u001bI\u0093\u00cb<\u0093\u00ce\u001fW+(\u0004\u0010\u00fb\u001am\u00cc\u00c0\u00c4:\u0094\u0091Y|k\u0014\u00af\u009b\u00c0";
                var7_6 = 33;
                var4_7 = 16;
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
                    var0_3[var6_4++] = ElytraMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00f0\u0081\u00e8a\u00a2\u00bf\u00f7Ck\u000b\u00efT]\u00ae\b\u00af\b\u0005\u0082\u00c1\u00c5\u008a33\u001f";
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
                    var0_3[var6_4++] = ElytraMode.zkmDecodeUtf8(var8_9).intern();
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
        ElytraMode.Vanilla = new ElytraMode(var0_3[3], 0);
        ElytraMode.Creative = new ElytraMode(var0_3[2], 1);
        ElytraMode.MATRIX_JUMP = new ElytraMode(var0_3[1], 2);
        ElytraMode.GRIM_GLIDE = new ElytraMode(var0_3[0], 3);
        ElytraMode.$VALUES = ElytraMode.$values();
        ElytraMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ElytraMode.$VALUES);
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

