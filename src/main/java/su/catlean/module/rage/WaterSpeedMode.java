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

public final class WaterSpeedMode
extends Enum {
    public static final WaterSpeedMode DolphinGrace;
    public static final WaterSpeedMode Vanilla;
    public static final WaterSpeedMode Legit;
    private static final WaterSpeedMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int[] w;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private WaterSpeedMode() {
        void var2_-1;
        void var1_-1;
    }

    public static WaterSpeedMode[] values() {
        return (WaterSpeedMode[])$VALUES.clone();
    }

    public static WaterSpeedMode valueOf(String string) {
        return Enum.valueOf(WaterSpeedMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final WaterSpeedMode[] $values() {
        WaterSpeedMode[] waterSpeedModeArray = new WaterSpeedMode[]{DolphinGrace, Vanilla, Legit};
        return waterSpeedModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(6216400581323690239L, -3266457304470007553L, MethodHandles.lookup().lookupClass()).a(240451194362858L) ^ 0x63414C7B7EF4L;
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
        String string = "x\u0011\u0099\u00d6\u0081Le\u00f3\u00f6\u0086\u00992\t\u00c8O\u001e\b\u009a\u00ce\u00e2\u00e8\u00f0\u00cb0Z\bi\u0015\u00f0\u00c7\r\u0097\u0016\u00a8";
        int n2 = 34;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = WaterSpeedMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                DolphinGrace = new WaterSpeedMode(stringArray[0], 0);
                Vanilla = new WaterSpeedMode(stringArray[1], 1);
                Legit = new WaterSpeedMode(stringArray[2], 2);
                $VALUES = WaterSpeedMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(int[] nArray) {
        w = nArray;
    }

    public static int[] zkmGetFlag() {
        return w;
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

