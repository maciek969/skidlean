/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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

public final class MiningPauseReason
extends Enum {
    public static final MiningPauseReason SWITCH;
    public static final MiningPauseReason TOGGLE;
    public static final MiningPauseReason EATING;
    private static final MiningPauseReason[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private MiningPauseReason() {
        void var2_-1;
        void var1_-1;
    }

    public static MiningPauseReason[] values() {
        return (MiningPauseReason[])$VALUES.clone();
    }

    public static MiningPauseReason valueOf(String string) {
        return Enum.valueOf(MiningPauseReason.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final MiningPauseReason[] $values() {
        MiningPauseReason[] miningPauseReasonArray = new MiningPauseReason[]{SWITCH, TOGGLE, EATING};
        return miningPauseReasonArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2871795143353586668L, -8154784940214194967L, MethodHandles.lookup().lookupClass()).a(51457858978219L) ^ 0x324552F0D696L;
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
        String string = "\u0003\u0005j\u009di\u00c7d\u00df\b\u001ac\u0084]\u00fa\u00a3\u00d7\u009b\b\u0004,\u0010\u0081\u00816Q|";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = MiningPauseReason.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                SWITCH = new MiningPauseReason(stringArray[0], 0);
                TOGGLE = new MiningPauseReason(stringArray[2], 1);
                EATING = new MiningPauseReason(stringArray[1], 2);
                $VALUES = MiningPauseReason.$values();
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

