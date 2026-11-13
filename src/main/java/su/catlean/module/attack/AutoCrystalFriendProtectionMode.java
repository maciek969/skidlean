/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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

public final class AutoCrystalFriendProtectionMode
extends Enum {
    public static final AutoCrystalFriendProtectionMode NONE;
    public static final AutoCrystalFriendProtectionMode ONLY_LETHAL;
    public static final AutoCrystalFriendProtectionMode FULL;
    private static final AutoCrystalFriendProtectionMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String[] x;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoCrystalFriendProtectionMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoCrystalFriendProtectionMode[] values() {
        return (AutoCrystalFriendProtectionMode[])$VALUES.clone();
    }

    public static AutoCrystalFriendProtectionMode valueOf(String string) {
        return Enum.valueOf(AutoCrystalFriendProtectionMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoCrystalFriendProtectionMode[] $values() {
        AutoCrystalFriendProtectionMode[] autoCrystalFriendProtectionModeArray = new AutoCrystalFriendProtectionMode[]{NONE, ONLY_LETHAL, FULL};
        return autoCrystalFriendProtectionModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-4405215554472099033L, 620361660512433241L, MethodHandles.lookup().lookupClass()).a(208946230006268L) ^ 0x16F935CEFA08L;
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
        String string = "MD\u00a3\u00dc\u009d\u00daD\u00b3\b.\u0087Xb1k\u00c83\u0010\u00dds\u00ac\u00e1\u00b6Zl\u0097LR\u0015\u0092\u00f8\u008f^\u00b2";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoCrystalFriendProtectionMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                NONE = new AutoCrystalFriendProtectionMode(stringArray[0], 0);
                ONLY_LETHAL = new AutoCrystalFriendProtectionMode(stringArray[2], 1);
                FULL = new AutoCrystalFriendProtectionMode(stringArray[1], 2);
                $VALUES = AutoCrystalFriendProtectionMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(String[] stringArray) {
        x = stringArray;
    }

    public static String[] zkmGetFlag() {
        return x;
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

