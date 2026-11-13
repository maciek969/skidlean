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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class AutoPotionBrewMode
extends Enum {
    @NotNull
    private final Item D;
    @NotNull
    private final Item o;
    @NotNull
    private final Item y;
    public static final AutoPotionBrewMode SPEED;
    public static final AutoPotionBrewMode STRENGTH;
    public static final AutoPotionBrewMode FIRE_RESISTANCE;
    public static final AutoPotionBrewMode INVISIBILITY;
    private static final /* synthetic */ AutoPotionBrewMode[] $VALUES;
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static int E;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AutoPotionBrewMode(Item item3) {
        void var5_3;
        void var4_2;
        void var2_-1;
        void var1_-1;
        this.D = item3;
        this.o = var4_2;
        this.y = var5_3;
    }

    @NotNull
    public final Item e() {
        return this.D;
    }

    @NotNull
    public final Item D() {
        return this.o;
    }

    @NotNull
    public final Item Q() {
        return this.y;
    }

    public static AutoPotionBrewMode[] values() {
        return (AutoPotionBrewMode[])$VALUES.clone();
    }

    public static AutoPotionBrewMode valueOf(String string) {
        return Enum.valueOf(AutoPotionBrewMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final /* synthetic */ AutoPotionBrewMode[] $values() {
        AutoPotionBrewMode[] autoPotionBrewModeArray = new AutoPotionBrewMode[]{SPEED, STRENGTH, FIRE_RESISTANCE, INVISIBILITY};
        return autoPotionBrewModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(5425629107657822773L, -8076617250336727802L, MethodHandles.lookup().lookupClass()).a(129589830581030L) ^ 96006362818641L;
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
                var0_3 = new String[15];
                var6_4 = 0;
                var5_5 = "\u00ce\u0007~u;UE\u0090\u00e9?\u00b7}\u00f0\u0081\u0083\u001a\b\u001et\u00b4\u00ae\u0082\u0083\u00e89\u0010`QX\u00e2\u0083\u008eJ\u00ed2\u009a\u0004\u00c5[\u00b6\u00a0\u00b7\u00105\u008a\u00f4\u00a7\u00c1\u00e3\u00fbJp\u00ab\u00e7vv2\u0002\u00be\u0010\u00c6\u0094\u00c7\u008a\u00a2\u00cb2*\\\u00ba\u0019\u00d1I\u00f8$A\u0010\u00a4\u00c9N\u0011H\u00f2\u00a7\u00129\b7^\u00b8mP\u00b4\u0018l\u00f4u\bC\u0019\u001a\u00e3`B\u0083{\u0095\u00aa\u00c1\u00c2\u00e6wa\u0012\u00af>\u0017\u00f7\u0010\u00da\u0094J=\u001f\u00ea\u00c1\u00df\u00e5ap\u0091\u00ea3\u00d8M\u0010Fq\u0006\u008f\u00b82\u00e5q\u00cdm\u00a2\u00cc;e\u00b5\u00b8\u0010\u00ce\u0007~u;UE\u0090\u00e9?\u00b7}\u00f0\u0081\u0083\u001a\b\u00f10\u00b7UA\u00a0t}\u0010`QX\u00e2\u0083\u008eJ\u00ed2\u009a\u0004\u00c5[\u00b6\u00a0\u00b7\u0010\u00b4\u00e4!\u00ef.\u00c7,D\u00c9\u009e\u008f\u00ca`\u0098l\u001e";
                var7_6 = 212;
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
                    var0_3[var6_4++] = AutoPotionBrewMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00b4\u001a\u009f\u00a1\u0012\u00fe\u00cc\u008a\b\u00f10\u00b7UA\u00a0t}";
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
                    var0_3[var6_4++] = AutoPotionBrewMode.zkmDecodeUtf8(var8_9).intern();
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
        v7 = var0_3[1];
        v8 = Items.SUGAR;
        Intrinsics.checkNotNullExpressionValue(v8, var0_3[13]);
        v9 = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(v9, var0_3[10]);
        v10 = Items.GLOWSTONE_DUST;
        Intrinsics.checkNotNullExpressionValue(v10, var0_3[2]);
        AutoPotionBrewMode.SPEED = new AutoPotionBrewMode(v7, 0, v8, v9, v10);
        v11 = var0_3[4];
        v12 = Items.BLAZE_POWDER;
        Intrinsics.checkNotNullExpressionValue(v12, var0_3[8]);
        v13 = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(v13, var0_3[14]);
        v14 = Items.GLOWSTONE_DUST;
        Intrinsics.checkNotNullExpressionValue(v14, var0_3[11]);
        AutoPotionBrewMode.STRENGTH = new AutoPotionBrewMode(v11, 1, v12, v13, v14);
        v15 = var0_3[7];
        v16 = Items.MAGMA_CREAM;
        Intrinsics.checkNotNullExpressionValue(v16, var0_3[5]);
        v17 = Items.AIR;
        Intrinsics.checkNotNullExpressionValue(v17, var0_3[14]);
        v18 = Items.REDSTONE;
        Intrinsics.checkNotNullExpressionValue(v18, var0_3[9]);
        AutoPotionBrewMode.FIRE_RESISTANCE = new AutoPotionBrewMode(v15, 2, v16, v17, v18);
        v19 = var0_3[3];
        v20 = Items.GOLDEN_CARROT;
        Intrinsics.checkNotNullExpressionValue(v20, var0_3[12]);
        v21 = Items.FERMENTED_SPIDER_EYE;
        Intrinsics.checkNotNullExpressionValue(v21, var0_3[6]);
        v22 = Items.REDSTONE;
        Intrinsics.checkNotNullExpressionValue(v22, var0_3[0]);
        AutoPotionBrewMode.INVISIBILITY = new AutoPotionBrewMode(v19, 3, v20, v21, v22);
        AutoPotionBrewMode.$VALUES = AutoPotionBrewMode.$values();
        AutoPotionBrewMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AutoPotionBrewMode.$VALUES);
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

