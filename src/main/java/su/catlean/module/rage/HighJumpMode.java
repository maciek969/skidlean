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

final class HighJumpMode
extends Enum {
    public static final HighJumpMode VANILLA;
    public static final HighJumpMode GRIM_SHULKER;
    public static final HighJumpMode ELYTRA;
    private static final HighJumpMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private HighJumpMode() {
        void var2_-1;
        void var1_-1;
    }

    public static HighJumpMode[] values() {
        return (HighJumpMode[])$VALUES.clone();
    }

    public static HighJumpMode valueOf(String string) {
        return Enum.valueOf(HighJumpMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final HighJumpMode[] $values() {
        HighJumpMode[] highJumpModeArray = new HighJumpMode[]{VANILLA, GRIM_SHULKER, ELYTRA};
        return highJumpModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(6524892069237538197L, -4345720280945069824L, MethodHandles.lookup().lookupClass()).a(208078984543041L) ^ 0x161EA829C3C0L;
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
        String string = "\u00b9\u0088l\u0000a\u009d5>\u00c1\u001bA\u00c6c1\u0005[\b\u00abf\u00b8Z\u00b4\u00ca/\u00f6\bg\u00b3\u00c1>\u00eaw\u00b6j";
        int n2 = 34;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = HighJumpMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                VANILLA = new HighJumpMode(stringArray[2], 0);
                GRIM_SHULKER = new HighJumpMode(stringArray[0], 1);
                ELYTRA = new HighJumpMode(stringArray[1], 2);
                $VALUES = HighJumpMode.$values();
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

