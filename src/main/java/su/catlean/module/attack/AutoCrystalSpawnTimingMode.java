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

public final class AutoCrystalSpawnTimingMode
extends Enum {
    public static final AutoCrystalSpawnTimingMode PACKET;
    public static final AutoCrystalSpawnTimingMode ADD_TO_WORLD;
    private static final AutoCrystalSpawnTimingMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoCrystalSpawnTimingMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoCrystalSpawnTimingMode[] values() {
        return (AutoCrystalSpawnTimingMode[])$VALUES.clone();
    }

    public static AutoCrystalSpawnTimingMode valueOf(String string) {
        return Enum.valueOf(AutoCrystalSpawnTimingMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoCrystalSpawnTimingMode[] $values() {
        AutoCrystalSpawnTimingMode[] autoCrystalSpawnTimingModeArray = new AutoCrystalSpawnTimingMode[]{PACKET, ADD_TO_WORLD};
        return autoCrystalSpawnTimingModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2858430058926751124L, -1142730457971126755L, MethodHandles.lookup().lookupClass()).a(216803091501600L) ^ 0x43FDDE39662CL;
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
        String string = "_\u00e1\u00f3\u00edw\u00f1W\u00c7\u00b3\u00d7\u0012|\u0013\u00e5\u000eg\b\u0012\f\u0089\u00d1,\u009a\u00ca\u0087";
        int n2 = 25;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoCrystalSpawnTimingMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                PACKET = new AutoCrystalSpawnTimingMode(stringArray[1], 0);
                ADD_TO_WORLD = new AutoCrystalSpawnTimingMode(stringArray[0], 1);
                $VALUES = AutoCrystalSpawnTimingMode.$values();
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

