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

public final class AutoCrystalAutoSwitchMode
extends Enum {
    public static final AutoCrystalAutoSwitchMode NONE;
    public static final AutoCrystalAutoSwitchMode NORMAL;
    public static final AutoCrystalAutoSwitchMode SILENT;
    public static final AutoCrystalAutoSwitchMode INVENTORY;
    private static final AutoCrystalAutoSwitchMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoCrystalAutoSwitchMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AutoCrystalAutoSwitchMode[] values() {
        return (AutoCrystalAutoSwitchMode[])$VALUES.clone();
    }

    public static AutoCrystalAutoSwitchMode valueOf(String string) {
        return Enum.valueOf(AutoCrystalAutoSwitchMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AutoCrystalAutoSwitchMode[] $values() {
        AutoCrystalAutoSwitchMode[] autoCrystalAutoSwitchModeArray = new AutoCrystalAutoSwitchMode[]{NONE, NORMAL, SILENT, INVENTORY};
        return autoCrystalAutoSwitchModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-7779809547838907164L, -2393714349183405439L, MethodHandles.lookup().lookupClass()).a(116347499001166L) ^ 43072353083453L;
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
                var5_5 = "\u00af\u00ac\u00a5\u00c9\u00c0\u00f5\u00f5\u00ecZ\u009b\u0002\u001b\u009d\u0007\u00eaj\b\fGuG\u00a3\n\u00dd\u00ff";
                var7_6 = 25;
                var4_7 = 16;
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
                    var0_3[var6_4++] = AutoCrystalAutoSwitchMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = ".G\u008a\u0015w\u00c5\u00e7\u00bd\b\u0006\u0002\u00c1v\u00a3\u00a4^3";
                    var7_6 = 17;
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
                    var0_3[var6_4++] = AutoCrystalAutoSwitchMode.zkmDecodeUtf8(var8_9).intern();
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
        AutoCrystalAutoSwitchMode.NONE = new AutoCrystalAutoSwitchMode(var0_3[2], 0);
        AutoCrystalAutoSwitchMode.NORMAL = new AutoCrystalAutoSwitchMode(var0_3[3], 1);
        AutoCrystalAutoSwitchMode.SILENT = new AutoCrystalAutoSwitchMode(var0_3[1], 2);
        AutoCrystalAutoSwitchMode.INVENTORY = new AutoCrystalAutoSwitchMode(var0_3[0], 3);
        AutoCrystalAutoSwitchMode.$VALUES = AutoCrystalAutoSwitchMode.$values();
        AutoCrystalAutoSwitchMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AutoCrystalAutoSwitchMode.$VALUES);
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

