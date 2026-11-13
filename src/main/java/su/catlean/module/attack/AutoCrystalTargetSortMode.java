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

public final class AutoCrystalTargetSortMode
extends Enum {
    public static final AutoCrystalTargetSortMode DISTANCE;
    public static final AutoCrystalTargetSortMode FOV;
    public static final AutoCrystalTargetSortMode HEALTH;
    private static final AutoCrystalTargetSortMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static boolean P;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoCrystalTargetSortMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoCrystalTargetSortMode[] values() {
        return (AutoCrystalTargetSortMode[])$VALUES.clone();
    }

    public static AutoCrystalTargetSortMode valueOf(String string) {
        return Enum.valueOf(AutoCrystalTargetSortMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoCrystalTargetSortMode[] $values() {
        AutoCrystalTargetSortMode[] autoCrystalTargetSortModeArray = new AutoCrystalTargetSortMode[]{DISTANCE, FOV, HEALTH};
        return autoCrystalTargetSortModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-8089621578697062869L, -3515601153509769730L, MethodHandles.lookup().lookupClass()).a(227058087978971L) ^ 0x51420DD4E37FL;
        if (AutoCrystalTargetSortMode.s()) {
            AutoCrystalTargetSortMode.zkmSetFlag(true);
        }
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
        String string = "v~\u00c4\u0013\u00e5\u00d5\u00da!\r\u00ddLy\u00af\u00f4\u00ae\u00f0\b\u001a8\u00bc\u009fg\u00c1L\u00cd\b'\u0094\u009d\u0004\u00ffM\u00e6\u00f4";
        int n2 = 34;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoCrystalTargetSortMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                DISTANCE = new AutoCrystalTargetSortMode(stringArray[0], 0);
                FOV = new AutoCrystalTargetSortMode(stringArray[2], 1);
                HEALTH = new AutoCrystalTargetSortMode(stringArray[1], 2);
                $VALUES = AutoCrystalTargetSortMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(boolean bl) {
        P = bl;
    }

    public static boolean zkmGetFlag() {
        return P;
    }

    public static boolean s() {
        return false;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
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

