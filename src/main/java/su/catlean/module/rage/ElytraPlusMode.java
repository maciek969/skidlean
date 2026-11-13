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
import su.catlean.module.rage.elytra.BoostElytra;
import su.catlean.module.rage.elytra.ControlElytra;
import su.catlean.module.rage.elytra.FireWorkElytra;
import su.catlean.module.rage.elytra.InfiniteElytra;
import su.catlean.module.rage.elytra.PacketElytra;
import su.catlean.module.rage.elytra.SilentFireWorkElytra;
import su.catlean.util.ElytraListener;
import su.catlean.zkm.ZkmKeys;

public final class ElytraPlusMode
extends Enum {
    @NotNull
    private final ElytraListener elytraListener;
    public static final ElytraPlusMode FIRE_WORK;
    public static final ElytraPlusMode BOOST;
    public static final ElytraPlusMode CONTROL;
    public static final ElytraPlusMode INFINITE;
    public static final ElytraPlusMode PACKET;
    public static final ElytraPlusMode SILENT_FIRE_WORK;
    private static final ElytraPlusMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static final long a;
    private static final long b;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private ElytraPlusMode() {
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.elytraListener = var3_2;
    }

    @NotNull
    public final ElytraListener getElytraListener() {
        return this.elytraListener;
    }

    public static ElytraPlusMode[] values() {
        return (ElytraPlusMode[])$VALUES.clone();
    }

    public static ElytraPlusMode valueOf(String string) {
        return Enum.valueOf(ElytraPlusMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final ElytraPlusMode[] $values() {
        ElytraPlusMode[] elytraPlusModeArray = new ElytraPlusMode[(int)b];
        elytraPlusModeArray[0] = FIRE_WORK;
        elytraPlusModeArray[1] = BOOST;
        elytraPlusModeArray[2] = CONTROL;
        elytraPlusModeArray[3] = INFINITE;
        elytraPlusModeArray[4] = PACKET;
        elytraPlusModeArray[5] = SILENT_FIRE_WORK;
        return elytraPlusModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ElytraPlusMode.a = ZkmKeys.a(-7743637682022269349L, -4965175275003478585L, MethodHandles.lookup().lookupClass()).a(31003046970029L);
                    var14 = ElytraPlusMode.a ^ 94637348281511L;
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
                    var10_5 = "\u001aHR\u00e4G\u00dc:\u0003\u0010\u00fc\u00d8\u00035H\u00bf\u00b7\u00ce\u00adX\u00eb8J\u00c6\u0004\u00f1\u0010#\u00f2\u0099\u00e5r\u0087@4\u00fb ID|\u00ec\u0088\u00fc\u0018\u009a\u00d2\n\fV\u00b3\u00e6\u0098\u00f7\u00d18\n\u00f9\u00c80\u00c2g\u00ed~\u00afA\u0019ov";
                    var12_6 = 67;
                    var9_7 = 8;
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
                        var5_3[var11_4++] = ElytraPlusMode.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        var10_5 = "\t\u0084\u009c\u001cUJ\u0087\u00a5\b\u00856 %\u00aa\u00b2+\u0004";
                        var12_6 = 17;
                        var9_7 = 8;
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
                        var5_3[var11_4++] = ElytraPlusMode.zkmDecodeUtf8(var13_9).intern();
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
        var4_12 = var0_10.doFinal(new byte[]{-54, -98, -26, -8, -74, -54, -13, 98});
        ** while (true)
        ElytraPlusMode.b = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        ElytraPlusMode.FIRE_WORK = new ElytraPlusMode(var5_3[1], 0, (ElytraListener)FireWorkElytra.INSTANCE);
        ElytraPlusMode.BOOST = new ElytraPlusMode(var5_3[5], 1, (ElytraListener)BoostElytra.INSTANCE);
        ElytraPlusMode.CONTROL = new ElytraPlusMode(var5_3[0], 2, (ElytraListener)ControlElytra.INSTANCE);
        ElytraPlusMode.INFINITE = new ElytraPlusMode(var5_3[2], 3, (ElytraListener)InfiniteElytra.INSTANCE);
        ElytraPlusMode.PACKET = new ElytraPlusMode(var5_3[4], 4, (ElytraListener)PacketElytra.INSTANCE);
        ElytraPlusMode.SILENT_FIRE_WORK = new ElytraPlusMode(var5_3[3], 5, (ElytraListener)SilentFireWorkElytra.INSTANCE);
        ElytraPlusMode.$VALUES = ElytraPlusMode.$values();
        ElytraPlusMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])ElytraPlusMode.$VALUES);
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

