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

public final class AutoCrystalDamageSyncMode
extends Enum {
    public static final AutoCrystalDamageSyncMode ALWAYS;
    public static final AutoCrystalDamageSyncMode CONDITIONS;
    public static final AutoCrystalDamageSyncMode OFF;
    private static final AutoCrystalDamageSyncMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoCrystalDamageSyncMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoCrystalDamageSyncMode[] values() {
        return (AutoCrystalDamageSyncMode[])$VALUES.clone();
    }

    public static AutoCrystalDamageSyncMode valueOf(String string) {
        return Enum.valueOf(AutoCrystalDamageSyncMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoCrystalDamageSyncMode[] $values() {
        AutoCrystalDamageSyncMode[] autoCrystalDamageSyncModeArray = new AutoCrystalDamageSyncMode[]{ALWAYS, CONDITIONS, OFF};
        return autoCrystalDamageSyncModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(7986536159333297206L, 1974778559131046629L, MethodHandles.lookup().lookupClass()).a(202763486358446L) ^ 0x1ECD3CF109CCL;
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
        String string = "\u00f47C\"\u00ff\u0089\u00f53\u00f1\u00cf8Xw\u009d\u00f4j\b\u00a9\u0090\u00d3\u0098\u0018\u00d27\u0005\b\u001fw\u00b3\u0081\u001e\u00bf\u00d6Q";
        int n2 = 34;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AutoCrystalDamageSyncMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                ALWAYS = new AutoCrystalDamageSyncMode(stringArray[1], 0);
                CONDITIONS = new AutoCrystalDamageSyncMode(stringArray[0], 1);
                OFF = new AutoCrystalDamageSyncMode(stringArray[2], 2);
                $VALUES = AutoCrystalDamageSyncMode.$values();
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

