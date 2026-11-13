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

final class AutoArmorHeadMode
extends Enum {
    public static final AutoArmorHeadMode BLAST;
    public static final AutoArmorHeadMode PROTECTION;
    private static final AutoArmorHeadMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoArmorHeadMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoArmorHeadMode[] values() {
        return (AutoArmorHeadMode[])$VALUES.clone();
    }

    public static AutoArmorHeadMode valueOf(String string) {
        return Enum.valueOf(AutoArmorHeadMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoArmorHeadMode[] $values() {
        AutoArmorHeadMode[] autoArmorHeadModeArray = new AutoArmorHeadMode[]{BLAST, PROTECTION};
        return autoArmorHeadModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(8943397254949724369L, 5930667632742806794L, MethodHandles.lookup().lookupClass()).a(280362877206633L) ^ 0x54DD21B1FA4BL;
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
        String string = "\u00f5\u00a5\u00f7Q\u00bb\u00f5\u0081\u00bb\u00ee$`\u00973\u0081\u00c3\u0005\b\u00c1\u001e\u00cf#\u00e6;\u00bfF";
        int n2 = 25;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoArmorHeadMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                BLAST = new AutoArmorHeadMode(stringArray[1], 0);
                PROTECTION = new AutoArmorHeadMode(stringArray[0], 1);
                $VALUES = AutoArmorHeadMode.$values();
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

