/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

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
import su.catlean.zkm.ZkmReferences;

public final class NameTagsSeeCatleanUsersMode
extends Enum {
    public static final NameTagsSeeCatleanUsersMode ON;
    public static final NameTagsSeeCatleanUsersMode ANONYMOUSLY;
    public static final NameTagsSeeCatleanUsersMode OFF;
    private static final NameTagsSeeCatleanUsersMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int L;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NameTagsSeeCatleanUsersMode() {
        void var2_-1;
        void var1_-1;
    }

    public static NameTagsSeeCatleanUsersMode[] values() {
        return (NameTagsSeeCatleanUsersMode[])$VALUES.clone();
    }

    public static NameTagsSeeCatleanUsersMode valueOf(String string) {
        return Enum.valueOf(NameTagsSeeCatleanUsersMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NameTagsSeeCatleanUsersMode[] $values() {
        NameTagsSeeCatleanUsersMode[] nameTagsSeeCatleanUsersModeArray = new NameTagsSeeCatleanUsersMode[]{ON, ANONYMOUSLY, OFF};
        return nameTagsSeeCatleanUsersModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(3930256523858628277L, 2816251230300084115L, MethodHandles.lookup().lookupClass()).a(134990515210451L) ^ 0x666078B684C8L;
        if (NameTagsSeeCatleanUsersMode.zkmIntConstant() == 0) {
            ZkmReferences.zkmBootstrap("\u00c5", (int)117, (long)7615668240544081768L, (long)l);
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
        String string = "\u001ch0K\u0097\u00f9\u00a8&\u0010\u008b\u0017A\"L\u009b\u008b\u00b8\u00cbI\u00e6\u000bk\u0005\u0086\u009d\b\u00d4T\u00f5dF\u0098\b\u00e6";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = NameTagsSeeCatleanUsersMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                ON = new NameTagsSeeCatleanUsersMode(stringArray[2], 0);
                ANONYMOUSLY = new NameTagsSeeCatleanUsersMode(stringArray[1], 1);
                OFF = new NameTagsSeeCatleanUsersMode(stringArray[0], 2);
                $VALUES = NameTagsSeeCatleanUsersMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(int n) {
        L = n;
    }

    public static int zkmGetFlag() {
        return L;
    }

    public static int zkmIntConstant() {
        return 111;
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

