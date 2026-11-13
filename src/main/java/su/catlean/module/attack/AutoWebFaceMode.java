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

public final class AutoWebFaceMode
extends Enum {
    public static final AutoWebFaceMode OFF;
    public static final AutoWebFaceMode ON;
    public static final AutoWebFaceMode ONLY;
    private static final AutoWebFaceMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoWebFaceMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoWebFaceMode[] values() {
        return (AutoWebFaceMode[])$VALUES.clone();
    }

    public static AutoWebFaceMode valueOf(String string) {
        return Enum.valueOf(AutoWebFaceMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoWebFaceMode[] $values() {
        AutoWebFaceMode[] autoWebFaceModeArray = new AutoWebFaceMode[]{OFF, ON, ONLY};
        return autoWebFaceModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-1398857615602257380L, -6617791783889949732L, MethodHandles.lookup().lookupClass()).a(200531890710964L) ^ 0x634A9BDB7E3CL;
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
        String string = "\u00eb\u007f\u00f4\u0007#\u00fc\u0003\u00d6\bp,_x\u0084\u0099_*\b\u00b5\u00ad\u0019\u00e5\u00d9\u00c2\u00ec\u00ee";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoWebFaceMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                OFF = new AutoWebFaceMode(stringArray[0], 0);
                ON = new AutoWebFaceMode(stringArray[1], 1);
                ONLY = new AutoWebFaceMode(stringArray[2], 2);
                $VALUES = AutoWebFaceMode.$values();
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

