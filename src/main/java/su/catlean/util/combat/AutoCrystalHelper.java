/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import java.lang.invoke.MethodHandles;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.PacketAccess;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class AutoCrystalHelper {
    @NotNull
    public static final AutoCrystalHelper INSTANCE;
    private static int c;
    @NotNull
    private static final Timer timer;
    private static final long a;

    private AutoCrystalHelper() {
    }

    public final void switchSlot(int n, int n2, int n3, boolean bl) {
        if (timer.hasPassed(n3)) {
            timer.reset();
            int n4 = n;
            if (n4 <= n2) {
                while (true) {
                    int n5 = c + n4;
                    Entity entity = Globals.getWorld().getEntityById(n5);
                    if (entity == null || entity instanceof EndCrystalEntity) {
                        PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket = PlayerInteractEntityC2SPacket.attack(Globals.getPlayer(), Globals.getPlayer().isSneaking());
                        Intrinsics.checkNotNull(playerInteractEntityC2SPacket);
                        PacketAccess.setEntityId(playerInteractEntityC2SPacket, n5);
                        Globals.getNetworkHandler().sendPacket(playerInteractEntityC2SPacket);
                        if (!bl || n4 == n2) {
                            Globals.getNetworkHandler().sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
                        }
                    }
                    if (n4 == n2) break;
                    ++n4;
                }
            }
        }
    }

    @Nullable
    public final Unit updateMax(int n) {
        Unit unit;
        if (n > c) {
            c = n;
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit;
    }

    static {
        a = ZkmKeys.a(3186670333198473225L, -1842691825879256328L, MethodHandles.lookup().lookupClass()).a(57427629114191L);
        INSTANCE = new AutoCrystalHelper();
        c = -1;
        timer = new Timer();
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

