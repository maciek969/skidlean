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

public final class NameTagsNameMode
extends Enum {
    public static final NameTagsNameMode DEFAULT;
    public static final NameTagsNameMode FULL;
    public static final NameTagsNameMode SEMI;
    private static final NameTagsNameMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NameTagsNameMode() {
        void var2_-1;
        void var1_-1;
    }

    public static NameTagsNameMode[] values() {
        return (NameTagsNameMode[])$VALUES.clone();
    }

    public static NameTagsNameMode valueOf(String string) {
        return Enum.valueOf(NameTagsNameMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NameTagsNameMode[] $values() {
        NameTagsNameMode[] nameTagsNameModeArray = new NameTagsNameMode[]{DEFAULT, FULL, SEMI};
        return nameTagsNameModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-5106880044350635469L, -2800041082836635694L, MethodHandles.lookup().lookupClass()).a(52159362502024L) ^ 0x12D792DA64DBL;
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
        String string = "\u0000EJO\u007f\u00c5\u00f6\u0012\b<\u008f\u00c0<\u001c\u001d(\u001f\b_#F\u00ee]m\u000e\u00b6";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = NameTagsNameMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                DEFAULT = new NameTagsNameMode(stringArray[2], 0);
                FULL = new NameTagsNameMode(stringArray[0], 1);
                SEMI = new NameTagsNameMode(stringArray[1], 2);
                $VALUES = NameTagsNameMode.$values();
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

