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

public final class ReverseStepMode
extends Enum {
    public static final ReverseStepMode Timer;
    public static final ReverseStepMode Motion;
    private static final ReverseStepMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String U;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ReverseStepMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ReverseStepMode[] values() {
        return (ReverseStepMode[])$VALUES.clone();
    }

    public static ReverseStepMode valueOf(String string) {
        return Enum.valueOf(ReverseStepMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ReverseStepMode[] $values() {
        ReverseStepMode[] reverseStepModeArray = new ReverseStepMode[]{Timer, Motion};
        return reverseStepModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(9150629636292994747L, -959455985995795487L, MethodHandles.lookup().lookupClass()).a(26452318950616L) ^ 0x4AADB132864FL;
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
        String string = "wv\u00d9\u00ff\u0094\u00d2\u00c2\u00b2\b8 \u00e9\u00b0\u00eb\u0080\u008b\u00f1";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ReverseStepMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Timer = new ReverseStepMode(stringArray[0], 0);
                Motion = new ReverseStepMode(stringArray[1], 1);
                $VALUES = ReverseStepMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(String string) {
        U = string;
    }

    public static String zkmGetFlag() {
        return U;
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

