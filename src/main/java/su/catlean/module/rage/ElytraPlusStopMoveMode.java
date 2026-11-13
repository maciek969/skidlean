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

public final class ElytraPlusStopMoveMode
extends Enum {
    public static final ElytraPlusStopMoveMode OFF;
    public static final ElytraPlusStopMoveMode TIMER;
    public static final ElytraPlusStopMoveMode BLOCK;
    private static final ElytraPlusStopMoveMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ElytraPlusStopMoveMode() {
        void var2_-1;
        void var1_-1;
    }

    public static ElytraPlusStopMoveMode[] values() {
        return (ElytraPlusStopMoveMode[])$VALUES.clone();
    }

    public static ElytraPlusStopMoveMode valueOf(String string) {
        return Enum.valueOf(ElytraPlusStopMoveMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ElytraPlusStopMoveMode[] $values() {
        ElytraPlusStopMoveMode[] elytraPlusStopMoveModeArray = new ElytraPlusStopMoveMode[]{OFF, TIMER, BLOCK};
        return elytraPlusStopMoveModeArray;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(-2991934829130037773L, 93750557635976987L, MethodHandles.lookup().lookupClass()).a(126685996171477L) ^ 0xA6F1EDE6663L;
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
        String string = "D\u00a0\u001d\u0018n\u0006\u00eb\u0085\bU\u0092\u00fav~\u00bb\u0001z\b\u00d8\u00c0[\u0087\u00a9`\u00eb\u008a";
        int n2 = 26;
        int n3 = 8;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ElytraPlusStopMoveMode.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                OFF = new ElytraPlusStopMoveMode(stringArray[1], 0);
                TIMER = new ElytraPlusStopMoveMode(stringArray[2], 1);
                BLOCK = new ElytraPlusStopMoveMode(stringArray[0], 2);
                $VALUES = ElytraPlusStopMoveMode.$values();
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

