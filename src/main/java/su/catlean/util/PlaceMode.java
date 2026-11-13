/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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

public final class PlaceMode
extends Enum {
    public static final PlaceMode Vanilla;
    public static final PlaceMode Strict;
    public static final PlaceMode AirPlace;
    public static final PlaceMode GRIM;
    private static final /* synthetic */ PlaceMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static String D;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private PlaceMode() {
        void var2_-1;
        void var1_-1;
    }

    public static PlaceMode[] values() {
        return (PlaceMode[])$VALUES.clone();
    }

    public static PlaceMode valueOf(String string) {
        return Enum.valueOf(PlaceMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ PlaceMode[] $values() {
        PlaceMode[] placeModeArray = new PlaceMode[]{Vanilla, Strict, AirPlace, GRIM};
        return placeModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(3335129741400859212L, -1976340919752615847L, MethodHandles.lookup().lookupClass()).a(250642316908530L) ^ 23736009575425L;
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
                var5_5 = "J\u0092\u0092\u00daw\u00f5|)\u0010\u00d4\u00d0\u00a0\u00e5\u00f8{\u0084\u0096\u001d<\u0001\u001em\u00f9\u00a8\u00ff";
                var7_6 = 25;
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
                    var0_3[var6_4++] = PlaceMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "x\u00c3\u00bf1y\u0095\u00f5H\b\u001bG\u00adD(\u00da\u0010\u00d1";
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
                    var0_3[var6_4++] = PlaceMode.zkmDecodeUtf8(var8_9).intern();
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
        PlaceMode.Vanilla = new PlaceMode(var0_3[0], 0);
        PlaceMode.Strict = new PlaceMode(var0_3[3], 1);
        PlaceMode.AirPlace = new PlaceMode(var0_3[1], 2);
        PlaceMode.GRIM = new PlaceMode(var0_3[2], 3);
        PlaceMode.$VALUES = PlaceMode.$values();
        PlaceMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])PlaceMode.$VALUES);
    }

    public static void zkmSetFlag(String string) {
        D = string;
    }

    public static String zkmGetFlag() {
        return D;
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

