/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class NotificationType
extends Enum {
    @NotNull
    private final Formatting T;
    public static final NotificationType SUCCESS;
    public static final NotificationType INFO;
    public static final NotificationType WARNING;
    public static final NotificationType ERROR;
    public static final NotificationType ENABLED;
    public static final NotificationType DISABLED;
    private static final /* synthetic */ NotificationType[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static String g;
    private static final long a;
    private static final long b;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private NotificationType() {
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.T = var3_2;
    }

    @NotNull
    public final Formatting F() {
        return this.T;
    }

    public static NotificationType[] values() {
        return (NotificationType[])$VALUES.clone();
    }

    public static NotificationType valueOf(String string) {
        return Enum.valueOf(NotificationType.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ NotificationType[] $values() {
        NotificationType[] notificationTypeArray = new NotificationType[(int)b];
        notificationTypeArray[0] = SUCCESS;
        notificationTypeArray[1] = INFO;
        notificationTypeArray[2] = WARNING;
        notificationTypeArray[3] = ERROR;
        notificationTypeArray[4] = ENABLED;
        notificationTypeArray[5] = DISABLED;
        return notificationTypeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    NotificationType.a = ZkmKeys.a(-2295633224315276900L, -1860336450316868268L, MethodHandles.lookup().lookupClass()).a(184002270856504L);
                    var14 = NotificationType.a ^ 117951188915796L;
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
                    var10_5 = "\u00881s\u00c8F\u009f\u00dc\b\b\u00b3\u00de\u0006\u0007\u00f2\u00b2W\u00e9\b\u0089>@Q.OJx\b}\u0088\u00a7#\u00f77MF";
                    var12_6 = 35;
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
                        var5_3[var11_4++] = NotificationType.zkmDecodeUtf8(var13_9).intern();
                        if ((var8_8 += var9_7) < var12_6) {
                            var9_7 = var10_5.charAt(var8_8);
                            ** continue;
                        }
                        var10_5 = "|\u00d6\u008a\u00c0\u00d0\u008d.\u0090\u0010j%\u00a6'`o\u00b7P\u00fd\u00b5\u00c0\u0098\u00aa~n\u00c7";
                        var12_6 = 25;
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
                        var5_3[var11_4++] = NotificationType.zkmDecodeUtf8(var13_9).intern();
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
        var4_12 = var0_10.doFinal(new byte[]{-5, -83, -28, 15, -10, -73, -92, 59});
        ** while (true)
        NotificationType.b = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        NotificationType.SUCCESS = new NotificationType(var5_3[2], 0, Formatting.GREEN);
        NotificationType.INFO = new NotificationType(var5_3[3], 1, Formatting.AQUA);
        NotificationType.WARNING = new NotificationType(var5_3[4], 2, Formatting.GOLD);
        NotificationType.ERROR = new NotificationType(var5_3[1], 3, Formatting.RED);
        NotificationType.ENABLED = new NotificationType(var5_3[0], 4, Formatting.DARK_GREEN);
        NotificationType.DISABLED = new NotificationType(var5_3[5], 5, Formatting.DARK_RED);
        NotificationType.$VALUES = NotificationType.$values();
        NotificationType.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])NotificationType.$VALUES);
    }

    public static void zkmSetFlag(String string) {
        g = string;
    }

    public static String zkmGetFlag() {
        return g;
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

