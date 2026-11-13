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

final class TapeMouseMode
extends Enum {
    public static final TapeMouseMode RIGHT;
    public static final TapeMouseMode LEFT;
    private static final TapeMouseMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private TapeMouseMode() {
        void var2_-1;
        void var1_-1;
    }

    public static TapeMouseMode[] values() {
        return (TapeMouseMode[])$VALUES.clone();
    }

    public static TapeMouseMode valueOf(String string) {
        return Enum.valueOf(TapeMouseMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final TapeMouseMode[] $values() {
        TapeMouseMode[] tapeMouseModeArray = new TapeMouseMode[]{RIGHT, LEFT};
        return tapeMouseModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-4802923253770760321L, 8460804849707398100L, MethodHandles.lookup().lookupClass()).a(214420072414797L) ^ 0x678BF01161E6L;
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
        String[] stringArray = new String[2];
        int n = 0;
        String string = "d\u00d6h\u00f8\u00d5\u00f99\u00ef\b/\u00bc\u0086\u00cb\u009bPg\u00c3";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = TapeMouseMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                RIGHT = new TapeMouseMode(stringArray[0], 0);
                LEFT = new TapeMouseMode(stringArray[1], 1);
                $VALUES = TapeMouseMode.$values();
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

