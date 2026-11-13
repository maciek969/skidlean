/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

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

public final class OffhandRcGapMode
extends Enum {
    public static final OffhandRcGapMode OFF;
    public static final OffhandRcGapMode ALWAYS;
    public static final OffhandRcGapMode ONLY_SAFE;
    private static final OffhandRcGapMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private OffhandRcGapMode() {
        void var2_-1;
        void var1_-1;
    }

    public static OffhandRcGapMode[] values() {
        return (OffhandRcGapMode[])$VALUES.clone();
    }

    public static OffhandRcGapMode valueOf(String string) {
        return Enum.valueOf(OffhandRcGapMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final OffhandRcGapMode[] $values() {
        OffhandRcGapMode[] offhandRcGapModeArray = new OffhandRcGapMode[]{OFF, ALWAYS, ONLY_SAFE};
        return offhandRcGapModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(8221992601869048853L, -7586399405623357540L, MethodHandles.lookup().lookupClass()).a(233820741312798L) ^ 0x3A517A1C7A6DL;
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
        String string = "\u00e3m\u00bd0\u0006\u00ffk\u00ca\b\u009a\u00a1O\u009f\u00b6\u0012&>\u0010\u00b7\u000f\u00e9\u00c12\u00a0v\u00b6\u0010KD\u008a\u0088\u008f\u0016\u00c3";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = OffhandRcGapMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                OFF = new OffhandRcGapMode(stringArray[1], 0);
                ALWAYS = new OffhandRcGapMode(stringArray[0], 1);
                ONLY_SAFE = new OffhandRcGapMode(stringArray[2], 2);
                $VALUES = OffhandRcGapMode.$values();
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

