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

public final class AuraSwitchMode
extends Enum {
    public static final AuraSwitchMode NORMAL;
    public static final AuraSwitchMode NONE;
    public static final AuraSwitchMode SILENT;
    private static final AuraSwitchMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AuraSwitchMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AuraSwitchMode[] values() {
        return (AuraSwitchMode[])$VALUES.clone();
    }

    public static AuraSwitchMode valueOf(String string) {
        return Enum.valueOf(AuraSwitchMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AuraSwitchMode[] $values() {
        AuraSwitchMode[] auraSwitchModeArray = new AuraSwitchMode[]{NORMAL, NONE, SILENT};
        return auraSwitchModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-4971455597248057978L, -7403916565539765632L, MethodHandles.lookup().lookupClass()).a(151520578350456L) ^ 0x40ACFD31E59CL;
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
        String string = "\u0012\u0001hU\u00c2\u0003x\u00dc\b\u000fJ\u008c\u00a8\u00d2p5\r\b0\u0098\u00d4\u00cat\u0014\u00d2\u00fe";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AuraSwitchMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                NORMAL = new AuraSwitchMode(stringArray[0], 0);
                NONE = new AuraSwitchMode(stringArray[2], 1);
                SILENT = new AuraSwitchMode(stringArray[1], 2);
                $VALUES = AuraSwitchMode.$values();
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

