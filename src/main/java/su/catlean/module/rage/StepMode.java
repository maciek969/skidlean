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

public final class StepMode
extends Enum {
    public static final StepMode NCP;
    public static final StepMode VANILLA;
    public static final StepMode STRICT_NCP;
    public static final StepMode NCP_2;
    public static final StepMode GRIM_BYPASS;
    private static final StepMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static boolean R;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private StepMode() {
        void var2_-1;
        void var1_-1;
    }

    public static StepMode[] values() {
        return (StepMode[])$VALUES.clone();
    }

    public static StepMode valueOf(String string) {
        return Enum.valueOf(StepMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final StepMode[] $values() {
        StepMode[] stepModeArray = new StepMode[]{NCP, VANILLA, STRICT_NCP, NCP_2, GRIM_BYPASS};
        return stepModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block12: {
            block11: {
                var9 = ZkmKeys.a(2381825671182051710L, 1346934818306096760L, MethodHandles.lookup().lookupClass()).a(55629206515553L) ^ 98157579779710L;
                if (StepMode.p()) {
                    StepMode.zkmSetFlag(true);
                }
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
                var5_5 = "\u00e9yK.vn\u0094V\b\u009d\u009c\u00bc\u001b\u00f8x\u00e0 ";
                var7_6 = 17;
                var4_7 = 8;
                var3_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var3_8;
                    v4 = var5_5.substring(v3, v3 + var4_7);
                    v5 = -1;
                    break block11;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = StepMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00a3\u009b\u00eb\u00bboq\u00cc9I6\u001b|\u00b4\u00fb\u00d6\f\b8\u00f2T\u00f4\u00d3\u00c5\u009dl";
                    var7_6 = 25;
                    var4_7 = 16;
                    var3_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var3_8;
                        v4 = var5_5.substring(v6, v6 + var4_7);
                        v5 = 0;
                        break block11;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = StepMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    break block12;
                    break;
                }
            }
            var8_9 = var1_1.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl51:
                // 1 sources

                ** continue;
            }
        }
        StepMode.NCP = new StepMode(var0_3[1], 0);
        StepMode.VANILLA = new StepMode(var0_3[0], 1);
        StepMode.STRICT_NCP = new StepMode(var0_3[2], 2);
        StepMode.NCP_2 = new StepMode(var0_3[3], 3);
        StepMode.GRIM_BYPASS = new StepMode("GRIM_BYPASS", 4);
        StepMode.$VALUES = StepMode.$values();
        StepMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])StepMode.$VALUES);
    }

    public static void zkmSetFlag(boolean bl) {
        R = bl;
    }

    public static boolean zkmGetFlag() {
        return R;
    }

    public static boolean p() {
        return false;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
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

