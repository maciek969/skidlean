/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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

public final class InventoryManagerCleanerMode
extends Enum {
    public static final InventoryManagerCleanerMode INVENTORY;
    public static final InventoryManagerCleanerMode ALWAYS;
    public static final InventoryManagerCleanerMode NOT_IN_INVENTORY;
    public static final InventoryManagerCleanerMode OFF;
    private static final InventoryManagerCleanerMode[] $VALUES;
    private static final EnumEntries $ENTRIES;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private InventoryManagerCleanerMode() {
        void var2_-1;
        void var1_-1;
    }

    public static InventoryManagerCleanerMode[] values() {
        return (InventoryManagerCleanerMode[])$VALUES.clone();
    }

    public static InventoryManagerCleanerMode valueOf(String string) {
        return Enum.valueOf(InventoryManagerCleanerMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final InventoryManagerCleanerMode[] $values() {
        InventoryManagerCleanerMode[] inventoryManagerCleanerModeArray = new InventoryManagerCleanerMode[]{INVENTORY, ALWAYS, NOT_IN_INVENTORY, OFF};
        return inventoryManagerCleanerModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-6067065945642038472L, -3805465629103244954L, MethodHandles.lookup().lookupClass()).a(45041308095109L) ^ 70064781364105L;
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
                var5_5 = "z\u00b8q\u00f5\u00f6(\u00b9R\u0010\u00ceJ\u00a0\u00fb\u0013\u008d\u0089\u00ea,I\u00f1X\u00d1&M^";
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
                    var0_3[var6_4++] = InventoryManagerCleanerMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "f\u000e\u00aa\u009f\u00bf\u00b2\u00ac@\u0018\u00c8=\u0003\u0002\u000bk\u0083\u00f7I\u00cfjD\u0000\u0013a\u009b\u00b5\u00f4\u00fd\u00c9\u0088P+h";
                    var7_6 = 33;
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
                    var0_3[var6_4++] = InventoryManagerCleanerMode.zkmDecodeUtf8(var8_9).intern();
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
        InventoryManagerCleanerMode.INVENTORY = new InventoryManagerCleanerMode(var0_3[1], 0);
        InventoryManagerCleanerMode.ALWAYS = new InventoryManagerCleanerMode(var0_3[2], 1);
        InventoryManagerCleanerMode.NOT_IN_INVENTORY = new InventoryManagerCleanerMode(var0_3[3], 2);
        InventoryManagerCleanerMode.OFF = new InventoryManagerCleanerMode(var0_3[0], 3);
        InventoryManagerCleanerMode.$VALUES = InventoryManagerCleanerMode.$values();
        InventoryManagerCleanerMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])InventoryManagerCleanerMode.$VALUES);
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

