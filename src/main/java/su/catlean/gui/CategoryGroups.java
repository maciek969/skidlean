/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.CategoryGroup;
import su.catlean.module.Categories;
import su.catlean.module.Category;
import su.catlean.zkm.ZkmKeys;

public final class CategoryGroups {
    @NotNull
    private static final CategoryGroup r;
    @NotNull
    private static final CategoryGroup q;
    @NotNull
    private static final CategoryGroup H;
    @NotNull
    private static final CategoryGroup F;

    @NotNull
    public static final CategoryGroup m() {
        return r;
    }

    @NotNull
    public static final CategoryGroup J() {
        return q;
    }

    @NotNull
    public static final CategoryGroup d() {
        return H;
    }

    @NotNull
    public static final CategoryGroup h() {
        return F;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                var9 = ZkmKeys.a(-3477903525195447015L, -7720611515454701748L, MethodHandles.lookup().lookupClass()).a(13110237613992L) ^ 69741202846868L;
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
                var5_5 = "P\"\u008b\u00c3w\u00bbz\u007f\u00df\u001a\u00b0[\u00b9\u00ae{c\bojhd\u00c1\u00c0\u001c\u00c4";
                var7_6 = 25;
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
                    var0_3[var6_4++] = CategoryGroups.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u00c9+\u00198\u0006\u00daSC\b\u00e2\u00cb\nt\u00df\u0089Ke";
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
                    var0_3[var6_4++] = CategoryGroups.zkmDecodeUtf8(var8_9).intern();
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
        var13_10 = new Category[]{Categories.getAttack(), Categories.getLegit(), Categories.getProtect()};
        CategoryGroups.r = new CategoryGroup(var0_3[2], CollectionsKt.arrayListOf(var13_10));
        var13_10 = new Category[]{Categories.getBasic(), Categories.getRage()};
        CategoryGroups.q = new CategoryGroup(var0_3[0], CollectionsKt.arrayListOf(var13_10));
        var13_10 = new Category[]{Categories.getCosmetic(), Categories.getEsp()};
        CategoryGroups.H = new CategoryGroup(var0_3[1], CollectionsKt.arrayListOf(var13_10));
        var13_10 = new Category[]{Categories.getWorld(), Categories.getEquip(), Categories.getPlayer(), Categories.getMisc()};
        CategoryGroups.F = new CategoryGroup(var0_3[3], CollectionsKt.arrayListOf(var13_10));
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

