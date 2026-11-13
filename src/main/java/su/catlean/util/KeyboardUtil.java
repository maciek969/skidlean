/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import com.mojang.blaze3d.systems.RenderSystem;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputUtil;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;

public final class KeyboardUtil {
    private static Module[] O;
    private static final long a;
    private static final long b;

    public static final boolean isKeyPressed(char c, int n) {
        int n2;
        block5: {
            String string;
            String string2;
            block6: {
                block4: {
                    string2 = RandomUtil.zkmGetFlag();
                    if (!RenderSystem.isOnRenderThread() || n == -1) break block4;
                    n2 = Globals.getMc().currentScreen instanceof ChatScreen;
                    string = string2;
                    if (c < '\u0000') break block5;
                    if (n2 == 0) break block6;
                }
                return false;
            }
            n2 = n;
            string = string2;
        }
        if (n2 < (int)b) {
            return ModuleManager.INSTANCE.u().contains(n);
        }
        return InputUtil.isKeyPressed(Globals.getMc().getWindow(), n);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        O = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return O;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(6259692815043206833L, 7492812825357708346L, MethodHandles.lookup().lookupClass()).a(243122606885064L);
        long l = a ^ 0x71B5171CD2B5L;
        Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                long l2 = -3562735855658840421L;
                byte[] byArray3 = cipher.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
                b = ((long)byArray3[0] & 0xFFL) << 56 | ((long)byArray3[1] & 0xFFL) << 48 | ((long)byArray3[2] & 0xFFL) << 40 | ((long)byArray3[3] & 0xFFL) << 32 | ((long)byArray3[4] & 0xFFL) << 24 | ((long)byArray3[5] & 0xFFL) << 16 | ((long)byArray3[6] & 0xFFL) << 8 | (long)byArray3[7] & 0xFFL;
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
}

