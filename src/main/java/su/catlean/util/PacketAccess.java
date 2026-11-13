/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.mixins.accessors.ServerboundInteractPacketAccessor;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PacketAccess {
    private static Module[] X;
    private static final long a;
    private static final String b;

    public static final void setEntityId(@NotNull PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket, int n) {
        block0: {
            int n2 = 0;
            long l = 0L;
            long l2 = ((long)n2 << 48 | l << 16 >>> 16) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)6311463154675973129L, (long)l2);
            Intrinsics.checkNotNullParameter(playerInteractEntityC2SPacket, b);
            CallSite callSite2 = callSite;
            ((ServerboundInteractPacketAccessor)((Object)playerInteractEntityC2SPacket)).setEntityId(n);
            if (callSite2 != null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[3], (long)6276572240414072103L, (long)l2);
        }
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        X = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return X;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(6620207715319971561L, -6228618399305279641L, MethodHandles.lookup().lookupClass()).a(97101883332843L);
        long l = a ^ 0x113C4EB362B6L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("\u00f6\u00bbQ\u0010UG\u00db+".getBytes("ISO-8859-1"));
                b = PacketAccess.zkmDecodeUtf8(byArray3).intern();
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

