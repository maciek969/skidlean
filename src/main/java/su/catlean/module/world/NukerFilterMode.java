/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

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

final class NukerFilterMode
extends Enum {
    public static final NukerFilterMode Select;
    public static final NukerFilterMode All;
    public static final NukerFilterMode BlackList;
    public static final NukerFilterMode WhiteList;
    private static final NukerFilterMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NukerFilterMode() {
        void var2_-1;
        void var1_-1;
    }

    public static NukerFilterMode[] values() {
        return (NukerFilterMode[])$VALUES.clone();
    }

    public static NukerFilterMode valueOf(String string) {
        return Enum.valueOf(NukerFilterMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NukerFilterMode[] $values() {
        NukerFilterMode[] nukerFilterModeArray = new NukerFilterMode[]{Select, All, BlackList, WhiteList};
        return nukerFilterModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(5170981708187696684L, -1579051143705660869L, MethodHandles.lookup().lookupClass()).a(142410184547896L) ^ 20149370402434L;
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
                var5_5 = "\u0007\u00f6w\u0095 (\\\u0095P1<\u00c3\u00be\u009d\u0016\u00b7\u0010\u008f\u00ec\u001e-\u00ca\u00bd\u00fa\u001e}\u000f.&\u00f0\u00b3\u00f8\u0093";
                var7_6 = 33;
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
                    var0_3[var6_4++] = NukerFilterMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u000f\u00bf\u00ee\u00e2\u0010\u00ad\u00ec\u00c0\b\u0005I@d\u0014\b\u00e6\u00e8";
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
                    var0_3[var6_4++] = NukerFilterMode.zkmDecodeUtf8(var8_9).intern();
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
        NukerFilterMode.Select = new NukerFilterMode(var0_3[2], 0);
        NukerFilterMode.All = new NukerFilterMode(var0_3[3], 1);
        NukerFilterMode.BlackList = new NukerFilterMode(var0_3[0], 2);
        NukerFilterMode.WhiteList = new NukerFilterMode(var0_3[1], 3);
        NukerFilterMode.$VALUES = NukerFilterMode.$values();
        NukerFilterMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])NukerFilterMode.$VALUES);
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

