/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Blocks;
import net.minecraft.network.packet.c2s.play.TeleportConfirmC2SPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.Particles;
import su.catlean.util.Globals;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class PortalGodMode
extends Module {
    @NotNull
    public static final PortalGodMode INSTANCE;
    @NotNull
    private static final Timer timer;
    private static final long a;
    private static final String b;
    private static final long c;

    private PortalGodMode() {
        super(b, Categories.getPlayer(), null, null);
    }

    @Flow
    public final void onSendPacket(@NotNull SendPacket sendPacket) {
        block0: {
            Intrinsics.checkNotNullParameter(sendPacket, "e");
            if (!(sendPacket.getPacket() instanceof TeleportConfirmC2SPacket) || timer.getElapsed() >= c) break block0;
            sendPacket.cancel();
        }
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        int n = (int)(Globals.getPlayer().getX() - 2.0);
        int n2 = Particles.zkmIntConstant();
        int n3 = (int)(Globals.getPlayer().getX() + 2.0);
        block0: while (true) {
            int n4 = n;
            block1: while (n4 < n3) {
                int n5 = (int)(Globals.getPlayer().getZ() - 2.0);
                int n6 = (int)(Globals.getPlayer().getZ() + 2.0);
                block2: while (true) {
                    int n7 = n5;
                    block3: while (n7 < n6) {
                        n4 = (int)(Globals.getPlayer().getY() + 2.0);
                        if (n2 == 0) continue block1;
                        int n8 = n4;
                        for (int i = (int)(Globals.getPlayer().getY() - 2.0); i < n8; ++i) {
                            n7 = Intrinsics.areEqual(Globals.getWorld().getBlockState(BlockPos.ofFloored(n, i, n5)).getBlock(), Blocks.NETHER_PORTAL) ? 1 : 0;
                            if (n2 == 0) continue block3;
                            if (n7 == 0) continue;
                            timer.reset();
                            if (n2 != 0) continue;
                            Module.zkmSetFlag(new Module[1]);
                            break;
                        }
                        ++n5;
                        if (n2 != 0) continue block2;
                    }
                    break;
                }
                ++n;
                if (n2 != 0) continue block0;
            }
            break;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-4692500715715064216L, -1083886641505589772L, MethodHandles.lookup().lookupClass()).a(40918005135536L);
        long l = a ^ 0x5EB9508F6A5BL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        byte[] byArray3 = cipher.doFinal("'\u0006\u0014^U\u00d3\u008f \u00f7=;\u0017\u0081\u00f66\u00f0".getBytes("ISO-8859-1"));
        b = PortalGodMode.zkmDecodeUtf8(byArray3).intern();
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray5 = byArray5;
            byArray5[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
        byte[] byArray6 = cipher2.doFinal(new byte[]{-21, -62, -19, 71, -43, -102, -20, 66});
        long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
        Cipher cipher3 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory3 = SecretKeyFactory.getInstance("DES");
        byte[] byArray7 = new byte[8];
        byte[] byArray8 = byArray7;
        byArray7[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher3.init(2, (Key)secretKeyFactory3.generateSecret(new DESKeySpec(byArray8)), new IvParameterSpec(new byte[8]));
                byte[] byArray9 = cipher3.doFinal(new byte[]{50, -48, 64, -20, 19, -51, 126, -103});
                c = ((long)byArray9[0] & 0xFFL) << 56 | ((long)byArray9[1] & 0xFFL) << 48 | ((long)byArray9[2] & 0xFFL) << 40 | ((long)byArray9[3] & 0xFFL) << 32 | ((long)byArray9[4] & 0xFFL) << 24 | ((long)byArray9[5] & 0xFFL) << 16 | ((long)byArray9[6] & 0xFFL) << 8 | (long)byArray9[7] & 0xFFL;
                INSTANCE = new PortalGodMode();
                timer = new Timer();
                timer.setTime((int)l2);
                return;
            }
            byArray8 = byArray8;
            byArray8[n] = (byte)(l << n * 8 >>> 56);
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

