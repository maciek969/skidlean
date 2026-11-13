/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.TickFactorEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.util.Manager;
import su.catlean.zkm.ZkmKeys;

public final class TimerManager
implements Manager {
    @NotNull
    public static final TimerManager INSTANCE;
    private static float x;
    private static int[] Z;
    private static final long a;

    private TimerManager() {
    }

    public final float C() {
        return x;
    }

    public final void h(float f) {
        x = f;
    }

    public final void x(float f) {
        x = f;
    }

    @Flow(priority=20)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        this.x(1.0f);
    }

    @Flow
    private final void onTickFactor(TickFactorEvent tickFactorEvent) {
        if (x == 1.0f) {
            return;
        }
        tickFactorEvent.setFactor(x);
        tickFactorEvent.cancel();
    }

    static {
        a = ZkmKeys.a(5900562588817714538L, 1933864321093250690L, MethodHandles.lookup().lookupClass()).a(258707700523382L);
        INSTANCE = new TimerManager();
        x = 1.0f;
        TimerManager.zkmSetFlag(new int[5]);
    }

    public static void zkmSetFlag(int[] nArray) {
        Z = nArray;
    }

    public static int[] zkmGetFlag() {
        return Z;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

