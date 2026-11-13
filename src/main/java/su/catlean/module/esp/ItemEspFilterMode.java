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

final class ItemEspFilterMode
extends Enum {
    public static final ItemEspFilterMode NONE;
    public static final ItemEspFilterMode WHITE_LIST;
    public static final ItemEspFilterMode BLACK_LIST;
    private static final ItemEspFilterMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ItemEspFilterMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ItemEspFilterMode[] values() {
        return (ItemEspFilterMode[])$VALUES.clone();
    }

    public static ItemEspFilterMode valueOf(String string) {
        return Enum.valueOf(ItemEspFilterMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ItemEspFilterMode[] $values() {
        ItemEspFilterMode[] itemEspFilterModeArray = new ItemEspFilterMode[]{NONE, WHITE_LIST, BLACK_LIST};
        return itemEspFilterModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2156196764533259870L, 1043553272528788290L, MethodHandles.lookup().lookupClass()).a(40606834420834L) ^ 0x703A67A43E8EL;
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
        String string = "\u00ce\u0092\u00a0\u00d4\u00e4\u00e1\u00a3\u00a6\u00e7/\u008f+e\u00ae \u00b5\u0010\u00b5\u00d3\u00ad\n\u00b6\u00ed\u00c4\u009d\u009f\u00b7\u00e7\"\u0084>\u00dc\u0001\bF\u00a9\u00a2K\u008e\u0097\u00ff\u00d5";
        int n2 = 42;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ItemEspFilterMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                NONE = new ItemEspFilterMode(stringArray[2], 0);
                WHITE_LIST = new ItemEspFilterMode(stringArray[1], 1);
                BLACK_LIST = new ItemEspFilterMode(stringArray[0], 2);
                $VALUES = ItemEspFilterMode.$values();
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

