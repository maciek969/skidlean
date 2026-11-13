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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

final class NameTagItemType
extends Enum {
    @NotNull
    private final Identifier icon;
    @NotNull
    private final Identifier secondaryIcon;
    public static final NameTagItemType CONTAINER;
    public static final NameTagItemType NORMAL;
    public static final NameTagItemType ABSORBING;
    private static final NameTagItemType[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static final long a;

    /*
     * WARNING - void declaration
     */
    private NameTagItemType() {
        void var4_1;
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.icon = var3_2;
        this.secondaryIcon = var4_1;
    }

    @NotNull
    public final Identifier Z(boolean bl) {
        return bl ? this.secondaryIcon : this.icon;
    }

    public static NameTagItemType[] values() {
        return (NameTagItemType[])$VALUES.clone();
    }

    public static NameTagItemType valueOf(String string) {
        return Enum.valueOf(NameTagItemType.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final NameTagItemType[] $values() {
        NameTagItemType[] nameTagItemTypeArray = new NameTagItemType[]{CONTAINER, NORMAL, ABSORBING};
        return nameTagItemTypeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                NameTagItemType.a = ZkmKeys.a(1932610023855144807L, 3324433405844299484L, MethodHandles.lookup().lookupClass()).a(99569550897541L);
                var9 = NameTagItemType.a ^ 118735757526252L;
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
                var0_3 = new String[11];
                var6_4 = 0;
                var5_5 = "\u00bf\u009awks\u00f7\r\u00f5\u00f4n\u0014$/V\u008f#\u000e\u00df\u0007\u008c9\u009egS\u00c3y\u00cdI9\u00dc\u00bc|\bZH\u00f5\u00d4\u00fa*\u00bfN\u0010\u00bf\u009awks\u00f7\r\u00f5\u00ebG\u00f8\u00b3+J\u00a1u\u0018\u00bf\u009awks\u00f7\r\u00f5\u00f6U\u00a1\u0086\u00ad7e\u008b\u008e,g\u00ac\u0086gl\u00b8\u0010\u00d4\u0016\u0080\u0001\u00fd\u00b7\u00b9?\u00b2\u00d1afZ\u0010\u0004\u00e1\u0010\u00bf\u009awks\u00f7\r\u00f5W\u00e8B\n\u00b7\u0097\u009f\u00de \u00bf\u009awks\u00f7\r\u00f5\u00f4n\u0014$/V\u008f#\u00f2\u0094\u00f1\u00a3\u00fd\u0005{5\u00ce\u00b0\u0097\u0098r\u00ab\u008c\u00e7\u0010\u0084\u00e5\u00db\u00c8\u00ba\u00b4\u00dd~\u009bp\f\u0018\u00eePK\u0002\u0018\u00bf\u009awks\u00f7\r\u00f5\u00f6U\u00a1\u0086\u00ad7e\u008b\u008e,g\u00ac\u0086gl\u00b8";
                var7_6 = 192;
                var4_7 = 32;
                var3_8 = -1;
lbl19:
                // 2 sources

                while (true) {
                    v3 = ++var3_8;
                    v4 = var5_5.substring(v3, v3 + var4_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl24:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = NameTagItemType.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "w\u0085\"\u00f1\u00fc?\u00f6v\u0090\u00e70|Ve\u00c5\u0095\u008d\u00ee\u008f\u00e64\u0013\r3\u00b6ZUZ\u00a3\u0086\u00afg w\u0085\"\u00f1\u00fc?\u00f6v\u0090\u00e70|Ve\u00c5\u0095\u008d\u00ee\u008f\u00e64\u0013\r3\u00b6ZUZ\u00a3\u0086\u00afg";
                    var7_6 = 65;
                    var4_7 = 32;
                    var3_8 = -1;
lbl33:
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
lbl38:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = NameTagItemType.zkmDecodeUtf8(var8_9).intern();
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
lbl50:
                // 1 sources

                ** continue;
            }
        }
        v7 = var0_3[7];
        v8 = Identifier.ofVanilla(var0_3[3]);
        Intrinsics.checkNotNullExpressionValue(v8, var0_3[10]);
        v9 = Identifier.ofVanilla(var0_3[8]);
        Intrinsics.checkNotNullExpressionValue(v9, var0_3[9]);
        NameTagItemType.CONTAINER = new NameTagItemType(v7, 0, v8, v9);
        v10 = var0_3[1];
        v11 = Identifier.ofVanilla(var0_3[2]);
        Intrinsics.checkNotNullExpressionValue(v11, var0_3[9]);
        v12 = Identifier.ofVanilla(var0_3[5]);
        Intrinsics.checkNotNullExpressionValue(v12, var0_3[9]);
        NameTagItemType.NORMAL = new NameTagItemType(v10, 1, v11, v12);
        v13 = var0_3[4];
        v14 = Identifier.ofVanilla(var0_3[6]);
        Intrinsics.checkNotNullExpressionValue(v14, var0_3[9]);
        v15 = Identifier.ofVanilla(var0_3[0]);
        Intrinsics.checkNotNullExpressionValue(v15, var0_3[9]);
        NameTagItemType.ABSORBING = new NameTagItemType(v13, 2, v14, v15);
        NameTagItemType.$VALUES = NameTagItemType.$values();
        NameTagItemType.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])NameTagItemType.$VALUES);
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

