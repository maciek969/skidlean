/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

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

public final class SpeedMineTimingMode
extends Enum {
    public static final SpeedMineTimingMode VANILLA;
    public static final SpeedMineTimingMode SEQUENTIAL;
    public static final SpeedMineTimingMode GRIM;
    private static final SpeedMineTimingMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpeedMineTimingMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpeedMineTimingMode[] values() {
        return (SpeedMineTimingMode[])$VALUES.clone();
    }

    public static SpeedMineTimingMode valueOf(String string) {
        return Enum.valueOf(SpeedMineTimingMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpeedMineTimingMode[] $values() {
        SpeedMineTimingMode[] speedMineTimingModeArray = new SpeedMineTimingMode[]{VANILLA, SEQUENTIAL, GRIM};
        return speedMineTimingModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-5851459525380704418L, -623182249837506890L, MethodHandles.lookup().lookupClass()).a(50124946930697L) ^ 0x348D3471EADCL;
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
        String string = "\u00a48\u00ce\u0018^\u001f\u00bf\u0016\u0002\u00e9\u0006\u00ab\u00c8\u009e\u0092\u00cd\bm\u00acI5\u00d5\u00ac\u007f\u00ad\b\u00f8\u000f\u0011\u0090-\u0007\u00d2]";
        int n2 = 34;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SpeedMineTimingMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                VANILLA = new SpeedMineTimingMode(stringArray[2], 0);
                SEQUENTIAL = new SpeedMineTimingMode(stringArray[0], 1);
                GRIM = new SpeedMineTimingMode(stringArray[1], 2);
                $VALUES = SpeedMineTimingMode.$values();
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

