/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

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

public final class ConfigType
extends Enum {
    public static final ConfigType MODULES;
    public static final ConfigType WIDGETS;
    private static final /* synthetic */ ConfigType[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static boolean c;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ConfigType() {
        void var2_-1;
        void var1_-1;
    }

    public static ConfigType[] values() {
        return (ConfigType[])$VALUES.clone();
    }

    public static ConfigType valueOf(String string) {
        return Enum.valueOf(ConfigType.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(8602056568126115225L, -1025180388145894059L, MethodHandles.lookup().lookupClass()).a(95021656136352L) ^ 0x175A46FB6F99L;
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
        String string = "Y\u0006(O|\u0004\u0097*\b\u00c8\u00dc~\u0004\u00b90\u00df\u00b0";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ConfigType.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                MODULES = new ConfigType(stringArray[0], 0);
                WIDGETS = new ConfigType(stringArray[1], 1);
                $VALUES = configTypeArray = new ConfigType[]{ConfigType.MODULES, ConfigType.WIDGETS};
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(boolean bl) {
        c = bl;
    }

    public static boolean zkmGetFlag() {
        return c;
    }

    public static boolean V() {
        return true;
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

