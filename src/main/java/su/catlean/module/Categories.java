/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Category;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class Categories {
    @NotNull
    private static final Category protect;
    @NotNull
    private static final Category attack;
    @NotNull
    private static final Category legit;
    @NotNull
    private static final Category basic;
    @NotNull
    private static final Category rage;
    @NotNull
    private static final Category cosmetic;
    @NotNull
    private static final Category esp;
    @NotNull
    private static final Category world;
    @NotNull
    private static final Category equip;
    @NotNull
    private static final Category player;
    @NotNull
    private static final Category misc;
    private static Module[] k;

    @NotNull
    public static final Category getProtect() {
        return protect;
    }

    @NotNull
    public static final Category getAttack() {
        return attack;
    }

    @NotNull
    public static final Category getLegit() {
        return legit;
    }

    @NotNull
    public static final Category getBasic() {
        return basic;
    }

    @NotNull
    public static final Category getRage() {
        return rage;
    }

    @NotNull
    public static final Category getCosmetic() {
        return cosmetic;
    }

    @NotNull
    public static final Category getEsp() {
        return esp;
    }

    @NotNull
    public static final Category getWorld() {
        return world;
    }

    @NotNull
    public static final Category getEquip() {
        return equip;
    }

    @NotNull
    public static final Category getPlayer() {
        return player;
    }

    @NotNull
    public static final Category getMisc() {
        return misc;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-1411855101450747916L, 3514975155890691561L, MethodHandles.lookup().lookupClass()).a(68634786437873L) ^ 137543174313079L;
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
                var5_5 = "\u008c\u00aad\u009f\u00b5\u008cd\u0084\b\u00d9)<\u00a3P\u00e4V\u0095\u0010z\u00c8\u00bf\u00ce*\u0090\u00f4\u00f5\u0093\u009bdp\u00f7w1\u00d6\bS/\u007f\n\u00a2\u000b\u00fa\u00e3\b\u00b8\u00b0\u00be\u001aZ\u00c3T\u0010\b\u00f9l\u00de\u00b0\u0006\u008a\u00cc\u0096\b$\u007f\u00de\u00cf\u00b3\u00f0w\u000f\b\u00ca\u00fcD\r\u00dbxI\u00d1\bZ\u0012\u00b0\u001b\u00aa\u00eaC\u009e";
                var7_6 = 88;
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
                    var0_3[var6_4++] = Categories.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "u\u00ae\u00f2\u0005\\\u0099\u00ce\u00a7\b\u001e\u00ac,\u00a0a\u00feb\u00a9";
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
                    var0_3[var6_4++] = Categories.zkmDecodeUtf8(var8_9).intern();
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
        Categories.protect = new Category(var0_3[0]);
        Categories.attack = new Category(var0_3[10]);
        Categories.legit = new Category(var0_3[7]);
        Categories.basic = new Category(var0_3[4]);
        Categories.rage = new Category(var0_3[9]);
        Categories.cosmetic = new Category(var0_3[2]);
        Categories.esp = new Category(var0_3[3]);
        Categories.world = new Category(var0_3[1]);
        Categories.equip = new Category(var0_3[5]);
        Categories.player = new Category(var0_3[6]);
        Categories.misc = new Category(var0_3[8]);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        k = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return k;
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

