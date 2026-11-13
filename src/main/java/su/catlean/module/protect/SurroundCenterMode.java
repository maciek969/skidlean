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

final class SurroundCenterMode
extends Enum {
    public static final SurroundCenterMode OFF;
    public static final SurroundCenterMode DEFAULT;
    public static final SurroundCenterMode SMART;
    private static final SurroundCenterMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SurroundCenterMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SurroundCenterMode[] values() {
        return (SurroundCenterMode[])$VALUES.clone();
    }

    public static SurroundCenterMode valueOf(String string) {
        return Enum.valueOf(SurroundCenterMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SurroundCenterMode[] $values() {
        SurroundCenterMode[] surroundCenterModeArray = new SurroundCenterMode[]{OFF, DEFAULT, SMART};
        return surroundCenterModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2135667542459917032L, 5114967076879423836L, MethodHandles.lookup().lookupClass()).a(14972836155279L) ^ 0x4009C1CE1B80L;
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
        String string = "i'\u001c\u0011x\u000f\u008f1\b\u00cd\u00024\u0092\u00ef\u00c5\u00c8\u00fd\b\u00ceo\u00b3\\3\u00fe\u00de\u0099";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SurroundCenterMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                OFF = new SurroundCenterMode(stringArray[0], 0);
                DEFAULT = new SurroundCenterMode(stringArray[2], 1);
                SMART = new SurroundCenterMode(stringArray[1], 2);
                $VALUES = SurroundCenterMode.$values();
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

