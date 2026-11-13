/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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

final class ChestStealerSortMode
extends Enum {
    public static final ChestStealerSortMode NONE;
    public static final ChestStealerSortMode WHITE_LIST;
    public static final ChestStealerSortMode BLACK_LIST;
    private static final ChestStealerSortMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ChestStealerSortMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ChestStealerSortMode[] values() {
        return (ChestStealerSortMode[])$VALUES.clone();
    }

    public static ChestStealerSortMode valueOf(String string) {
        return Enum.valueOf(ChestStealerSortMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ChestStealerSortMode[] $values() {
        ChestStealerSortMode[] chestStealerSortModeArray = new ChestStealerSortMode[]{NONE, WHITE_LIST, BLACK_LIST};
        return chestStealerSortModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2256986737403996747L, -9071631990665175722L, MethodHandles.lookup().lookupClass()).a(243234246589586L) ^ 0x38E76CAF0CE0L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[3];
        int n = 0;
        String string = "y2\u0094\u00fc\u00cd\u00e4H\u00a7m\u00d6\u00f9\u00d9\u00c2\u007fIC\b]\u0089\u0081\u0081\u00bby\u00894\u0010\u009e\u00act\u00b8\u00e1#\u00fe\u00d1\u00dd\u00b8\u00b4=}(@'";
        int n2 = 42;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ChestStealerSortMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                NONE = new ChestStealerSortMode(stringArray[1], 0);
                WHITE_LIST = new ChestStealerSortMode(stringArray[2], 1);
                BLACK_LIST = new ChestStealerSortMode(stringArray[0], 2);
                $VALUES = ChestStealerSortMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
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

