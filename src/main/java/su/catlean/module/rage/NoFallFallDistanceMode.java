/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

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

public final class NoFallFallDistanceMode
extends Enum {
    public static final NoFallFallDistanceMode Calc;
    public static final NoFallFallDistanceMode Custom;
    private static final NoFallFallDistanceMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NoFallFallDistanceMode() {
        void var2_-1;
        void var1_-1;
    }

    public static NoFallFallDistanceMode[] values() {
        return (NoFallFallDistanceMode[])$VALUES.clone();
    }

    public static NoFallFallDistanceMode valueOf(String string) {
        return Enum.valueOf(NoFallFallDistanceMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NoFallFallDistanceMode[] $values() {
        NoFallFallDistanceMode[] noFallFallDistanceModeArray = new NoFallFallDistanceMode[]{Calc, Custom};
        return noFallFallDistanceModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(725316229809152294L, 6437019199915753738L, MethodHandles.lookup().lookupClass()).a(220814760615297L) ^ 0x3BC9A47E42BEL;
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
        String string = "\u00d0d\u0015\u00963\u00b7\u0083\u00b0\b\u00c4\u00ea\u0006\u00af\u0005\u00b1\u0088H";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = NoFallFallDistanceMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Calc = new NoFallFallDistanceMode(stringArray[0], 0);
                Custom = new NoFallFallDistanceMode(stringArray[1], 1);
                $VALUES = NoFallFallDistanceMode.$values();
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

