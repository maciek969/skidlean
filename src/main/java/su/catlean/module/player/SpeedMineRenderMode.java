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

public final class SpeedMineRenderMode
extends Enum {
    public static final SpeedMineRenderMode Block;
    public static final SpeedMineRenderMode Shrink;
    public static final SpeedMineRenderMode Grow;
    private static final SpeedMineRenderMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpeedMineRenderMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpeedMineRenderMode[] values() {
        return (SpeedMineRenderMode[])$VALUES.clone();
    }

    public static SpeedMineRenderMode valueOf(String string) {
        return Enum.valueOf(SpeedMineRenderMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpeedMineRenderMode[] $values() {
        SpeedMineRenderMode[] speedMineRenderModeArray = new SpeedMineRenderMode[]{Block, Shrink, Grow};
        return speedMineRenderModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-6994048443976957303L, 1408750333046290536L, MethodHandles.lookup().lookupClass()).a(172252026095913L) ^ 0x6C32157ED528L;
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
        String string = "_\u00bb\u00cfZK \u00fcT\bwI\u00b1\b\f~\u009dz\b\u00efj\u00a3Q\u00cfy\u00f4\u0006";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SpeedMineRenderMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Block = new SpeedMineRenderMode(stringArray[2], 0);
                Shrink = new SpeedMineRenderMode(stringArray[1], 1);
                Grow = new SpeedMineRenderMode(stringArray[0], 2);
                $VALUES = SpeedMineRenderMode.$values();
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

