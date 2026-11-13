/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class Language
extends Enum {
    @NotNull
    private final String displayName;
    @NotNull
    private final Map translations;
    public static final Language EN;
    public static final Language RU;
    public static final Language PL;
    private static final Language[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - void declaration
     */
    private Language() {
        void var4_1;
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.displayName = var3_2;
        this.translations = var4_1;
    }

    @NotNull
    public final String getDisplayName() {
        return this.displayName;
    }

    @NotNull
    public final Map getTranslations() {
        return this.translations;
    }

    public static Language[] values() {
        return (Language[])$VALUES.clone();
    }

    public static Language valueOf(String string) {
        return Enum.valueOf(Language.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final Language[] $values() {
        Language[] languageArray = new Language[]{EN, RU, PL};
        return languageArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(1268896906261118318L, -8345833253705726917L, MethodHandles.lookup().lookupClass()).a(24725270067053L) ^ 70496827022608L;
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
                var0_3 = new String[6];
                var6_4 = 0;
                var5_5 = "t\u0086S\u001d\u00b8\u0007\u0092\u00dc\b\u00cc\u00f78~\u0081}\u009a\u00d1\b\u00fe&\"f\u001a\u0000\u0083\u00ca\bm\u00ce\u009c\u00df\u001d\u00b3K=";
                var7_6 = 35;
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
                    var0_3[var6_4++] = Language.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "^4\u009a\u00a4\u0089!\u00eda\bc\u0015A\u00c7\u0016\u0087\u00c0\u00ed";
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
                    var0_3[var6_4++] = Language.zkmDecodeUtf8(var8_9).intern();
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
        Language.EN = new Language(var0_3[3], 0, var0_3[1], (Map)new LinkedHashMap<K, V>());
        Language.RU = new Language(var0_3[2], 1, var0_3[4], (Map)new LinkedHashMap<K, V>());
        Language.PL = new Language(var0_3[5], 2, var0_3[0], (Map)new LinkedHashMap<K, V>());
        Language.$VALUES = Language.$values();
        Language.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])Language.$VALUES);
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

