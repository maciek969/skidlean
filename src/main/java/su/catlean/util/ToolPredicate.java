/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import su.catlean.util.ItemPredicate;
import su.catlean.util.ItemUtil;
import su.catlean.zkm.ZkmKeys;

public final class ToolPredicate
implements ItemPredicate {
    public static final ToolPredicate O;
    private static final long a;
    private static final String b;

    @Override
    public final boolean isTool(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, b);
        Object var8_2 = null;
        return ItemUtil.isSword(itemStack) || ItemUtil.isPickaxe(itemStack) || itemStack.getItem() instanceof AxeItem || itemStack.getItem() instanceof ShovelItem;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(1431061559134902667L, 4073669698222776954L, MethodHandles.lookup().lookupClass()).a(157623333199920L);
        long l = a ^ 0x1F2274D4A067L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\"|e\u00ef\u00f7-\u0004\u0011".getBytes("ISO-8859-1"));
                b = ToolPredicate.zkmDecodeUtf8(byArray3).intern();
                O = new ToolPredicate();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
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

