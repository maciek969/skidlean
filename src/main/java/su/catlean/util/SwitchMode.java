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

public final class SwitchMode
extends Enum {
    public static final SwitchMode Silent;
    public static final SwitchMode Normal;
    public static final SwitchMode Alternative;
    private static final /* synthetic */ SwitchMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static String[] U;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SwitchMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SwitchMode[] values() {
        return (SwitchMode[])$VALUES.clone();
    }

    public static SwitchMode valueOf(String string) {
        return Enum.valueOf(SwitchMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-5544823922492988137L, 8749429005879151083L, MethodHandles.lookup().lookupClass()).a(236928401814027L) ^ 0x591F1ABD4129L;
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
        String string = "7.H\u0094\u00c0 s\u00ec&\u008d7\u00cc8\u0016\u00fa\u00f5\b\u00f8\u0007\u00ca\f\u00e4\u00f6\u0007;\b\u00e3->$\u008f\u001aeN";
        int n2 = 34;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SwitchMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Silent = new SwitchMode(stringArray[2], 0);
                Normal = new SwitchMode(stringArray[1], 1);
                Alternative = new SwitchMode(stringArray[0], 2);
                $VALUES = switchModeArray = new SwitchMode[]{SwitchMode.Silent, SwitchMode.Normal, SwitchMode.Alternative};
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(String[] stringArray) {
        U = stringArray;
    }

    public static String[] zkmGetFlag() {
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

