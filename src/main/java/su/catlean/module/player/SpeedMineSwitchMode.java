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

public final class SpeedMineSwitchMode
extends Enum {
    public static final SpeedMineSwitchMode Silent;
    public static final SpeedMineSwitchMode Normal;
    public static final SpeedMineSwitchMode Alternative;
    private static final SpeedMineSwitchMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private SpeedMineSwitchMode() {
        void var2_-1;
        void var1_-1;
    }

    public static SpeedMineSwitchMode[] values() {
        return (SpeedMineSwitchMode[])$VALUES.clone();
    }

    public static SpeedMineSwitchMode valueOf(String string) {
        return Enum.valueOf(SpeedMineSwitchMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final SpeedMineSwitchMode[] $values() {
        SpeedMineSwitchMode[] speedMineSwitchModeArray = new SpeedMineSwitchMode[]{Silent, Normal, Alternative};
        return speedMineSwitchModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(2889817315561399854L, 4571349189029241490L, MethodHandles.lookup().lookupClass()).a(44951483552002L) ^ 0xD61B728DDF4L;
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
        String string = " @\u00fdd\u008e\u00c6u\u00e2\b\u00a3l\u00d3\u00f1!@\u00d8\u00e4\u0010\\\u00da\u001d\u00b8\u0087\u00b3\u00f6\u0099\u000eO\u0014\u00a2\u0006/\u001f\u00a7";
        int n2 = 34;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = SpeedMineSwitchMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                Silent = new SpeedMineSwitchMode(stringArray[1], 0);
                Normal = new SpeedMineSwitchMode(stringArray[0], 1);
                Alternative = new SpeedMineSwitchMode(stringArray[2], 2);
                $VALUES = SpeedMineSwitchMode.$values();
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

