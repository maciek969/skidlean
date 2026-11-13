/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

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

public final class SpeedBoostAngleMode
extends Enum {
    public static final SpeedBoostAngleMode CLIENT;
    public static final SpeedBoostAngleMode SERVER;
    public static final SpeedBoostAngleMode PREDICT;
    private static final SpeedBoostAngleMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int b;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpeedBoostAngleMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpeedBoostAngleMode[] values() {
        return (SpeedBoostAngleMode[])$VALUES.clone();
    }

    public static SpeedBoostAngleMode valueOf(String string) {
        return Enum.valueOf(SpeedBoostAngleMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpeedBoostAngleMode[] $values() {
        SpeedBoostAngleMode[] speedBoostAngleModeArray = new SpeedBoostAngleMode[]{CLIENT, SERVER, PREDICT};
        return speedBoostAngleModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-215940243364797820L, -2928266778495009530L, MethodHandles.lookup().lookupClass()).a(8704444188403L) ^ 0x225C189EC737L;
        if (SpeedBoostAngleMode.zkmIntConstant() != 0) {
            SpeedBoostAngleMode.zkmSetFlag(29);
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
        String string = "\u00aeA\u00d7Q\u00e6\u00c2\u00ad\u00a3\bQ=\f\u00c6Qyj\u00a3\b\u008f\u0090t\u00e1\u00abz\u00d3\u0005";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SpeedBoostAngleMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                CLIENT = new SpeedBoostAngleMode(stringArray[2], 0);
                SERVER = new SpeedBoostAngleMode(stringArray[0], 1);
                PREDICT = new SpeedBoostAngleMode(stringArray[1], 2);
                $VALUES = SpeedBoostAngleMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(int n) {
        b = n;
    }

    public static int zkmGetFlag() {
        return b;
    }

    public static int zkmIntConstant() {
        return 0;
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

