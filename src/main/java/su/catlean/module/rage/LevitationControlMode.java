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

public final class LevitationControlMode
extends Enum {
    public static final LevitationControlMode NCP;
    public static final LevitationControlMode DEFAULT;
    public static final LevitationControlMode ANTI;
    private static final LevitationControlMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int[] C;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private LevitationControlMode() {
        void var2_-1;
        void var1_-1;
    }

    public static LevitationControlMode[] values() {
        return (LevitationControlMode[])$VALUES.clone();
    }

    public static LevitationControlMode valueOf(String string) {
        return Enum.valueOf(LevitationControlMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final LevitationControlMode[] $values() {
        LevitationControlMode[] levitationControlModeArray = new LevitationControlMode[]{NCP, DEFAULT, ANTI};
        return levitationControlModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(5928394995408356025L, -7046884351538614701L, MethodHandles.lookup().lookupClass()).a(164389713824525L) ^ 0x2544AEA00F2L;
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
        String string = "\u008dgW\u0000!`\u0088\t\b]\u0001q|\u00e9\u009ar;\b]\u00ab%\u0017\u00bf\u00d2\u0014J";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = LevitationControlMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                NCP = new LevitationControlMode(stringArray[2], 0);
                DEFAULT = new LevitationControlMode(stringArray[0], 1);
                ANTI = new LevitationControlMode(stringArray[1], 2);
                $VALUES = LevitationControlMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(int[] nArray) {
        C = nArray;
    }

    public static int[] zkmGetFlag() {
        return C;
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

