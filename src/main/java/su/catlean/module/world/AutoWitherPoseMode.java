/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

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

final class AutoWitherPoseMode
extends Enum {
    public static final AutoWitherPoseMode STANDING;
    public static final AutoWitherPoseMode LYING;
    private static final AutoWitherPoseMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoWitherPoseMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoWitherPoseMode[] values() {
        return (AutoWitherPoseMode[])$VALUES.clone();
    }

    public static AutoWitherPoseMode valueOf(String string) {
        return Enum.valueOf(AutoWitherPoseMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoWitherPoseMode[] $values() {
        AutoWitherPoseMode[] autoWitherPoseModeArray = new AutoWitherPoseMode[]{STANDING, LYING};
        return autoWitherPoseModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2140437615512148705L, -8200713657216822599L, MethodHandles.lookup().lookupClass()).a(58768823647790L) ^ 0x182700384847L;
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
        String string = "~\u0094n|$\u0005\u00aa\u00c0\u00999\u008b\u008f\u00de\u00c6\u0016o\b\u0015\u00c5\u00df\u0084\u0081\u008c\u00e0\u00ae";
        int n2 = 25;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoWitherPoseMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                STANDING = new AutoWitherPoseMode(stringArray[0], 0);
                LYING = new AutoWitherPoseMode(stringArray[1], 1);
                $VALUES = AutoWitherPoseMode.$values();
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

