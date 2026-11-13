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

public final class SpeedMineStartMode
extends Enum {
    public static final SpeedMineStartMode ABORT;
    public static final SpeedMineStartMode STOP;
    public static final SpeedMineStartMode NONE;
    private static final SpeedMineStartMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpeedMineStartMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpeedMineStartMode[] values() {
        return (SpeedMineStartMode[])$VALUES.clone();
    }

    public static SpeedMineStartMode valueOf(String string) {
        return Enum.valueOf(SpeedMineStartMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpeedMineStartMode[] $values() {
        SpeedMineStartMode[] speedMineStartModeArray = new SpeedMineStartMode[]{ABORT, STOP, NONE};
        return speedMineStartModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(2333150209197491511L, -7041395281831925357L, MethodHandles.lookup().lookupClass()).a(276536688198736L) ^ 0x78BBCEA65287L;
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
        String string = "\u00cd\u00a8v\u00fc\b\u0083\u00df\u00b7\b\u009d5!\u00c8$7\u00ef\u00a5\bN\u00da\u009a*Kro\u00c8";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SpeedMineStartMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                ABORT = new SpeedMineStartMode(stringArray[0], 0);
                STOP = new SpeedMineStartMode(stringArray[1], 1);
                NONE = new SpeedMineStartMode(stringArray[2], 2);
                $VALUES = SpeedMineStartMode.$values();
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

