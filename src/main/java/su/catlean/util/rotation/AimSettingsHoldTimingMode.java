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

public final class AimSettingsHoldTimingMode
extends Enum {
    public static final AimSettingsHoldTimingMode BEFORE_RANDOM;
    public static final AimSettingsHoldTimingMode AFTER_RANDOM;
    private static final AimSettingsHoldTimingMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AimSettingsHoldTimingMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AimSettingsHoldTimingMode[] values() {
        return (AimSettingsHoldTimingMode[])$VALUES.clone();
    }

    public static AimSettingsHoldTimingMode valueOf(String string) {
        return Enum.valueOf(AimSettingsHoldTimingMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AimSettingsHoldTimingMode[] $values() {
        AimSettingsHoldTimingMode[] aimSettingsHoldTimingModeArray = new AimSettingsHoldTimingMode[]{BEFORE_RANDOM, AFTER_RANDOM};
        return aimSettingsHoldTimingModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-5847621020693462334L, 3292292918537209915L, MethodHandles.lookup().lookupClass()).a(222727691915551L) ^ 0x59AC6D67925AL;
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
        String string = "Q\u00c5\u0004\u00b5\u00d7\u00d6?P\u00cc_\u00cf\f\u00dd\u008fy\u00d4\u0010\u0092\u00d9j\u009d\u00a0\u00eb\u00da\u00da\u001c\u00a9\u00c9\u0004s\u001d\u00f0\u00c5";
        int n2 = 33;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AimSettingsHoldTimingMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                BEFORE_RANDOM = new AimSettingsHoldTimingMode(stringArray[1], 0);
                AFTER_RANDOM = new AimSettingsHoldTimingMode(stringArray[0], 1);
                $VALUES = AimSettingsHoldTimingMode.$values();
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

