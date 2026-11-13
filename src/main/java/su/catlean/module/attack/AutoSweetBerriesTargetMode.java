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

public final class AutoSweetBerriesTargetMode
extends Enum {
    public static final AutoSweetBerriesTargetMode AUTO;
    public static final AutoSweetBerriesTargetMode ALL;
    private static final AutoSweetBerriesTargetMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static boolean d;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoSweetBerriesTargetMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoSweetBerriesTargetMode[] values() {
        return (AutoSweetBerriesTargetMode[])$VALUES.clone();
    }

    public static AutoSweetBerriesTargetMode valueOf(String string) {
        return Enum.valueOf(AutoSweetBerriesTargetMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoSweetBerriesTargetMode[] $values() {
        AutoSweetBerriesTargetMode[] autoSweetBerriesTargetModeArray = new AutoSweetBerriesTargetMode[]{AUTO, ALL};
        return autoSweetBerriesTargetModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-5484645126284063236L, -3764033489861790344L, MethodHandles.lookup().lookupClass()).a(120513915867702L) ^ 0x29BED96CA5F2L;
        if (AutoSweetBerriesTargetMode.n()) {
            AutoSweetBerriesTargetMode.zkmSetFlag(true);
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
        String[] stringArray = new String[2];
        int n = 0;
        String string = "\u0088\u00a6\u0082\u0003\u00f3\u0002a\u00b6\bJ4\u00ee *g\u007f\u00c1";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoSweetBerriesTargetMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                AUTO = new AutoSweetBerriesTargetMode(stringArray[0], 0);
                ALL = new AutoSweetBerriesTargetMode(stringArray[1], 1);
                $VALUES = AutoSweetBerriesTargetMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(boolean bl) {
        d = bl;
    }

    public static boolean zkmGetFlag() {
        return d;
    }

    public static boolean n() {
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

