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

public final class AuraRandomizeDelayMode
extends Enum {
    public static final AuraRandomizeDelayMode OFF;
    public static final AuraRandomizeDelayMode VULCAN;
    public static final AuraRandomizeDelayMode DEFAULT;
    private static final AuraRandomizeDelayMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AuraRandomizeDelayMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AuraRandomizeDelayMode[] values() {
        return (AuraRandomizeDelayMode[])$VALUES.clone();
    }

    public static AuraRandomizeDelayMode valueOf(String string) {
        return Enum.valueOf(AuraRandomizeDelayMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AuraRandomizeDelayMode[] $values() {
        AuraRandomizeDelayMode[] auraRandomizeDelayModeArray = new AuraRandomizeDelayMode[]{OFF, VULCAN, DEFAULT};
        return auraRandomizeDelayModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-3762964432659866167L, 3582390213947192588L, MethodHandles.lookup().lookupClass()).a(122756228468641L) ^ 0x6C00998AFC16L;
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
        String string = "\u00e3\u008a8\u000fG\u0015\u00a8z\b\u00b6\u0003z(\u00a9\u00f2\u0095\u00ed\bp'\u00b8&[-\u00cap";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AuraRandomizeDelayMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                OFF = new AuraRandomizeDelayMode(stringArray[0], 0);
                VULCAN = new AuraRandomizeDelayMode(stringArray[1], 1);
                DEFAULT = new AuraRandomizeDelayMode(stringArray[2], 2);
                $VALUES = AuraRandomizeDelayMode.$values();
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

