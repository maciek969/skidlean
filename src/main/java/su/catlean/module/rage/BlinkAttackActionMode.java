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
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class BlinkAttackActionMode
extends Enum {
    public static final BlinkAttackActionMode REPEAT;
    public static final BlinkAttackActionMode OFF;
    public static final BlinkAttackActionMode NONE;
    private static final BlinkAttackActionMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static Module[] F;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private BlinkAttackActionMode() {
        void var2_-1;
        void var1_-1;
    }

    public static BlinkAttackActionMode[] values() {
        return (BlinkAttackActionMode[])$VALUES.clone();
    }

    public static BlinkAttackActionMode valueOf(String string) {
        return Enum.valueOf(BlinkAttackActionMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final BlinkAttackActionMode[] $values() {
        BlinkAttackActionMode[] blinkAttackActionModeArray = new BlinkAttackActionMode[]{REPEAT, OFF, NONE};
        return blinkAttackActionModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2656821758572010543L, -2785104965501049143L, MethodHandles.lookup().lookupClass()).a(159000456412414L) ^ 0x554EED7C9446L;
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
        String string = "(\\P\u0018S\u0099z\u00cd\b\u008c\u00aeQkZ9)\u0096\bRJ\u00ce\u00c2\u00c0o\u0083P";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = BlinkAttackActionMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                REPEAT = new BlinkAttackActionMode(stringArray[0], 0);
                OFF = new BlinkAttackActionMode(stringArray[2], 1);
                NONE = new BlinkAttackActionMode(stringArray[1], 2);
                $VALUES = BlinkAttackActionMode.$values();
                $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        F = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return F;
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

