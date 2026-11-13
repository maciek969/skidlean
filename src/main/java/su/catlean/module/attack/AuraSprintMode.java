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

public final class AuraSprintMode
extends Enum {
    public static final AuraSprintMode LEGIT;
    public static final AuraSprintMode LEGIT_EXTRA;
    public static final AuraSprintMode NORMAL;
    public static final AuraSprintMode HVH;
    private static final /* synthetic */ AuraSprintMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AuraSprintMode() {
        void var2_-1;
        void var1_-1;
    }

    public static AuraSprintMode[] values() {
        return (AuraSprintMode[])$VALUES.clone();
    }

    public static AuraSprintMode valueOf(String string) {
        return Enum.valueOf(AuraSprintMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ AuraSprintMode[] $values() {
        AuraSprintMode[] auraSprintModeArray = new AuraSprintMode[]{LEGIT, LEGIT_EXTRA, NORMAL, HVH};
        return auraSprintModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-6974713882722719076L, 8221851925633384623L, MethodHandles.lookup().lookupClass()).a(184949068906915L) ^ 102926965418776L;
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
                var5_5 = "\u00ce@\u0094E\u00ac\\p\u00bc\b*\u00b7s\u008d?M\u00fa\u009e";
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
                    var0_3[var6_4++] = AuraSprintMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00109W\u00e6O\u00c7\u0000\u00e0\u0010]\u00a0\u00e4$\u00fbX\u009b&\u00c4H'\u009b\u0000\b0\u00a5";
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
                    var0_3[var6_4++] = AuraSprintMode.zkmDecodeUtf8(var8_9).intern();
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
        AuraSprintMode.LEGIT = new AuraSprintMode(var0_3[1], 0);
        AuraSprintMode.LEGIT_EXTRA = new AuraSprintMode(var0_3[3], 1);
        AuraSprintMode.NORMAL = new AuraSprintMode(var0_3[2], 2);
        AuraSprintMode.HVH = new AuraSprintMode(var0_3[0], 3);
        AuraSprintMode.$VALUES = AuraSprintMode.$values();
        AuraSprintMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AuraSprintMode.$VALUES);
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

