/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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

final class ElytraSwapHotBarSwapMode
extends Enum {
    public static final ElytraSwapHotBarSwapMode DEFAULT;
    public static final ElytraSwapHotBarSwapMode V1_17;
    private static final ElytraSwapHotBarSwapMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ElytraSwapHotBarSwapMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ElytraSwapHotBarSwapMode[] values() {
        return (ElytraSwapHotBarSwapMode[])$VALUES.clone();
    }

    public static ElytraSwapHotBarSwapMode valueOf(String string) {
        return Enum.valueOf(ElytraSwapHotBarSwapMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ElytraSwapHotBarSwapMode[] $values() {
        ElytraSwapHotBarSwapMode[] elytraSwapHotBarSwapModeArray = new ElytraSwapHotBarSwapMode[]{DEFAULT, V1_17};
        return elytraSwapHotBarSwapModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-1181328539446902369L, -1809443190147566237L, MethodHandles.lookup().lookupClass()).a(100558438428492L) ^ 0x665FD7C6BC51L;
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
        String string = "P\u0085\u0088\u00ae\u0095\u008e\u0092d\b\u00dbN\t\u00f8A\u00d6\u008e@";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ElytraSwapHotBarSwapMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                DEFAULT = new ElytraSwapHotBarSwapMode(stringArray[0], 0);
                V1_17 = new ElytraSwapHotBarSwapMode(stringArray[1], 1);
                $VALUES = ElytraSwapHotBarSwapMode.$values();
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

