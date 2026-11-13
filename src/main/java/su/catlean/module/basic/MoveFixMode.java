/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

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

public final class MoveFixMode
extends Enum {
    public static final MoveFixMode FREE;
    public static final MoveFixMode FOCUS;
    private static final MoveFixMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String M;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private MoveFixMode() {
        void var2_-1;
        void var1_-1;
    }

    public static MoveFixMode[] values() {
        return (MoveFixMode[])$VALUES.clone();
    }

    public static MoveFixMode valueOf(String string) {
        return Enum.valueOf(MoveFixMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final MoveFixMode[] $values() {
        MoveFixMode[] moveFixModeArray = new MoveFixMode[]{FREE, FOCUS};
        return moveFixModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-1612781149014954849L, 385752184262550171L, MethodHandles.lookup().lookupClass()).a(99517531690846L) ^ 0x2172BAF93587L;
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
        String string = "vB\u0084\u00cf\u0095\u00ac\u00f1\u00f5\b\u009b\u008e\u00bf\u00bfV\u00bf\u0016\u00fa";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = MoveFixMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                FREE = new MoveFixMode(stringArray[1], 0);
                FOCUS = new MoveFixMode(stringArray[0], 1);
                $VALUES = MoveFixMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(String string) {
        M = string;
    }

    public static String zkmGetFlag() {
        return M;
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

