/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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

public final class NotifierPastEffectsMode
extends Enum {
    public static final NotifierPastEffectsMode OFF;
    public static final NotifierPastEffectsMode CHAT;
    public static final NotifierPastEffectsMode NOTIFICATION;
    public static final NotifierPastEffectsMode BOTH;
    private static final NotifierPastEffectsMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NotifierPastEffectsMode() {
        void var2_-1;
        void var1_-1;
    }

    public static NotifierPastEffectsMode[] values() {
        return (NotifierPastEffectsMode[])$VALUES.clone();
    }

    public static NotifierPastEffectsMode valueOf(String string) {
        return Enum.valueOf(NotifierPastEffectsMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NotifierPastEffectsMode[] $values() {
        NotifierPastEffectsMode[] notifierPastEffectsModeArray = new NotifierPastEffectsMode[]{OFF, CHAT, NOTIFICATION, BOTH};
        return notifierPastEffectsModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-779966600652859937L, 6966998855783515954L, MethodHandles.lookup().lookupClass()).a(105036832911912L) ^ 4490124221476L;
                var1_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var2_2 = 1; var2_2 < 8; ++var2_2) {
                    v2 = v2;
                    v2[var2_2] = (byte)(var9 << var2_2 * 8 >>> 56);
                }
                var1_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var0_3 = new String[4];
                var6_4 = 0;
                var5_5 = "C\u00c7T\u00d1i\u00fc\u0081\u00c8\b\u00e9\u00bd\u00b8\u00d3hx6c";
                var7_6 = 17;
                var4_7 = 8;
                var3_8 = -1;
lbl18:
                // 2 sources

                while (true) {
                    v3 = ++var3_8;
                    v4 = var5_5.substring(v3, v3 + var4_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl23:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = NotifierPastEffectsMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "%_\u00ca 0\u00ee\u00f2n\u0010\u009b\u00d9\u00a7\u000eY\u00db\"\u00b9S\u00eag\t\u001b)~\u0086";
                    var7_6 = 25;
                    var4_7 = 8;
                    var3_8 = -1;
lbl32:
                    // 2 sources

                    while (true) {
                        v6 = ++var3_8;
                        v4 = var5_5.substring(v6, v6 + var4_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl37:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = NotifierPastEffectsMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var1_1.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl49:
                // 1 sources

                ** continue;
            }
        }
        NotifierPastEffectsMode.OFF = new NotifierPastEffectsMode(var0_3[2], 0);
        NotifierPastEffectsMode.CHAT = new NotifierPastEffectsMode(var0_3[0], 1);
        NotifierPastEffectsMode.NOTIFICATION = new NotifierPastEffectsMode(var0_3[3], 2);
        NotifierPastEffectsMode.BOTH = new NotifierPastEffectsMode(var0_3[1], 3);
        NotifierPastEffectsMode.$VALUES = NotifierPastEffectsMode.$values();
        NotifierPastEffectsMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])NotifierPastEffectsMode.$VALUES);
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

