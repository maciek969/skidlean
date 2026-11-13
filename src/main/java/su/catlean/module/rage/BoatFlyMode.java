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

public final class BoatFlyMode
extends Enum {
    public static final BoatFlyMode Packet;
    public static final BoatFlyMode Motion;
    private static final BoatFlyMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static String k;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private BoatFlyMode() {
        void var2_-1;
        void var1_-1;
    }

    public static BoatFlyMode[] values() {
        return (BoatFlyMode[])$VALUES.clone();
    }

    public static BoatFlyMode valueOf(String string) {
        return Enum.valueOf(BoatFlyMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final BoatFlyMode[] $values() {
        BoatFlyMode[] boatFlyModeArray = new BoatFlyMode[]{Packet, Motion};
        return boatFlyModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(393082867867123630L, -8635098697841574814L, MethodHandles.lookup().lookupClass()).a(67191362617707L) ^ 0x8411183D0B3L;
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
        String string = "\u00e4M\u00e9\u00a7\u00f1\u0083\u00e1\u00d7\b\u0014\u00ca\u00d52S\u00b4\u001b ";
        int n2 = 17;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = BoatFlyMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Packet = new BoatFlyMode(stringArray[1], 0);
                Motion = new BoatFlyMode(stringArray[0], 1);
                $VALUES = BoatFlyMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(String string) {
        k = string;
    }

    public static String zkmGetFlag() {
        return k;
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

