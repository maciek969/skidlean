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

public final class NameTagsHealthMode
extends Enum {
    public static final NameTagsHealthMode Number;
    public static final NameTagsHealthMode Hearts;
    public static final NameTagsHealthMode Dots;
    private static final NameTagsHealthMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NameTagsHealthMode() {
        void var2_-1;
        void var1_-1;
    }

    public static NameTagsHealthMode[] values() {
        return (NameTagsHealthMode[])$VALUES.clone();
    }

    public static NameTagsHealthMode valueOf(String string) {
        return Enum.valueOf(NameTagsHealthMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NameTagsHealthMode[] $values() {
        NameTagsHealthMode[] nameTagsHealthModeArray = new NameTagsHealthMode[]{Number, Hearts, Dots};
        return nameTagsHealthModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(1846145089549973952L, 6524750754293588900L, MethodHandles.lookup().lookupClass()).a(58150557025145L) ^ 0x3576070AE825L;
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
        String string = "\u00c2\u00aa*\u00b0;\b\u00d6\u00b5\b\u00c9\u00a0WpY\u00e3vE\b\u00c1\u00d9\u0091`X\u00a2\u00b5\u00bb";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = NameTagsHealthMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Number = new NameTagsHealthMode(stringArray[0], 0);
                Hearts = new NameTagsHealthMode(stringArray[1], 1);
                Dots = new NameTagsHealthMode(stringArray[2], 2);
                $VALUES = NameTagsHealthMode.$values();
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

