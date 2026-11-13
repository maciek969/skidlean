/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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

final class AutoLeavePlayersMode
extends Enum {
    public static final AutoLeavePlayersMode None;
    public static final AutoLeavePlayersMode Command;
    public static final AutoLeavePlayersMode Leave;
    private static final AutoLeavePlayersMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoLeavePlayersMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoLeavePlayersMode[] values() {
        return (AutoLeavePlayersMode[])$VALUES.clone();
    }

    public static AutoLeavePlayersMode valueOf(String string) {
        return Enum.valueOf(AutoLeavePlayersMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoLeavePlayersMode[] $values() {
        AutoLeavePlayersMode[] autoLeavePlayersModeArray = new AutoLeavePlayersMode[]{None, Command, Leave};
        return autoLeavePlayersModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(8034880349829556259L, 8835471348562177625L, MethodHandles.lookup().lookupClass()).a(93420930583801L) ^ 0x6F8427BC572AL;
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
        String string = "T+4\u008b\u00f3\u00cd\u0091\u00c0\b@!\u00fc\\2\u00d2w\u00ce\b/\u00ec\u00abD<\u0093\u0090j";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoLeavePlayersMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                None = new AutoLeavePlayersMode(stringArray[1], 0);
                Command = new AutoLeavePlayersMode(stringArray[0], 1);
                Leave = new AutoLeavePlayersMode(stringArray[2], 2);
                $VALUES = AutoLeavePlayersMode.$values();
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

