/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

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

public final class MoveFix
extends Enum {
    public static final MoveFix NONE;
    public static final MoveFix SILENT;
    public static final MoveFix FORCE;
    private static final /* synthetic */ MoveFix[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static boolean w;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private MoveFix() {
        void var2_-1;
        void var1_-1;
    }

    public static MoveFix[] values() {
        return (MoveFix[])$VALUES.clone();
    }

    public static MoveFix valueOf(String string) {
        return Enum.valueOf(MoveFix.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-3675638091934078602L, 3240287982813831777L, MethodHandles.lookup().lookupClass()).a(106176044848639L) ^ 0x632848F9B0BCL;
        if (MoveFix.u()) {
            MoveFix.zkmSetFlag(true);
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
        String string = "\u00c1\u0010\u0091\u00f6\u00c8\u00a0\"d\b\u00f67\u00bdP\u008f>RU\bk5k7\u00a4y\u00d2?";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = MoveFix.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                NONE = new MoveFix(stringArray[0], 0);
                SILENT = new MoveFix(stringArray[2], 1);
                FORCE = new MoveFix(stringArray[1], 2);
                $VALUES = moveFixArray = new MoveFix[]{MoveFix.NONE, MoveFix.SILENT, MoveFix.FORCE};
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(boolean bl) {
        w = bl;
    }

    public static boolean zkmGetFlag() {
        return w;
    }

    public static boolean u() {
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

