/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.esp;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.zkm.ZkmKeys;

public final class TracersMode
extends Enum {
    @NotNull
    private final ParticleTexture texture;
    public static final TracersMode ARROWS_1;
    public static final TracersMode ARROWS_2;
    public static final TracersMode ARROWS_3;
    public static final TracersMode ARROWS_4;
    public static final TracersMode ARROWS_5;
    public static final TracersMode LINES;
    private static final TracersMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static int E;
    private static final long b;
    private static final long c;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private TracersMode() {
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.texture = var3_2;
    }

    @NotNull
    public final ParticleTexture getTexture() {
        return this.texture;
    }

    public static TracersMode[] values() {
        return (TracersMode[])$VALUES.clone();
    }

    public static TracersMode valueOf(String string) {
        return Enum.valueOf(TracersMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final TracersMode[] $values() {
        TracersMode[] tracersModeArray = new TracersMode[(int)c];
        tracersModeArray[0] = ARROWS_1;
        tracersModeArray[1] = ARROWS_2;
        tracersModeArray[2] = ARROWS_3;
        tracersModeArray[3] = ARROWS_4;
        tracersModeArray[4] = ARROWS_5;
        tracersModeArray[5] = LINES;
        return tracersModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    TracersMode.b = ZkmKeys.a(-8278140984827572398L, -6535161193327663952L, MethodHandles.lookup().lookupClass()).a(162052609500082L);
                    var14 = TracersMode.b ^ 106701321027087L;
                    var6_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var7_2 = 1; var7_2 < 8; ++var7_2) {
                        v2 = v2;
                        v2[var7_2] = (byte)(var14 << var7_2 * 8 >>> 56);
                    }
                    var6_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var5_3 = new String[6];
                    var11_4 = 0;
                    var10_5 = "\u00c0;\u00b0Cb\u00e8a\u00ef\u00a6\u00fam6~\u00a5\u0010\u00b2\b<\u0016\u0007\u000e@\u00d4\u009b\u00a5\u0010CN\u00f27\u0018\u0016:\u00ce\u00d8>\u00e3Z$ox\u008f\u0010\u0017\u00e7\u00a3^\u000e\u0019\u00aa.\u0010\u00d3R\u00bd\u0093\u00d7mM";
                    var12_6 = 59;
                    var9_7 = 16;
                    var8_8 = -1;
lbl19:
                    // 2 sources

                    while (true) {
                        v3 = ++var8_8;
                        v4 = var10_5.substring(v3, v3 + var9_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl24:
                    // 1 sources

                    while (true) {
                        var5_3[var11_4++] = TracersMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        var10_5 = "\u008dmX\u00e5@\u008bG\u00c8\u00e0\u0013V\b\u00d5\u00b0\u00f2Y\u0010oz\u00e0\u001c\u00d1\u0085\u00f3\u0099\u0086}\u00bc2\u000f!\u0092\u00c8";
                        var12_6 = 33;
                        var9_7 = 16;
                        var8_8 = -1;
lbl33:
                        // 2 sources

                        while (true) {
                            v6 = ++var8_8;
                            v4 = var10_5.substring(v6, v6 + var9_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl38:
                    // 1 sources

                    while (true) {
                        var5_3[var11_4++] = TracersMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var6_1.doFinal(v4.getBytes("ISO-8859-1"));
                switch (v5) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl50:
                    // 1 sources

                    ** continue;
                }
            }
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl62:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-108, 29, 35, 109, -93, 9, -123, -42});
        ** while (true)
        TracersMode.c = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        TracersMode.ARROWS_1 = new TracersMode(var5_3[3], 0, ParticleTexture.TRIANGLE1);
        TracersMode.ARROWS_2 = new TracersMode(var5_3[0], 1, ParticleTexture.TRIANGLE2);
        TracersMode.ARROWS_3 = new TracersMode(var5_3[2], 2, ParticleTexture.TRIANGLE3);
        TracersMode.ARROWS_4 = new TracersMode(var5_3[4], 3, ParticleTexture.TRIANGLE4);
        TracersMode.ARROWS_5 = new TracersMode(var5_3[5], 4, ParticleTexture.TRIANGLE5);
        TracersMode.LINES = new TracersMode(var5_3[1], 5, ParticleTexture.TRIANGLE1);
        TracersMode.$VALUES = TracersMode.$values();
        TracersMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])TracersMode.$VALUES);
    }

    public static void zkmSetFlag(int n) {
        E = n;
    }

    public static int zkmGetFlag() {
        return E;
    }

    public static int zkmIntConstant() {
        return 0;
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

