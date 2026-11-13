/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import kotlin.jvm.internal.Intrinsics;
import su.catlean.util.JoinedPlayer;
import su.catlean.util.PlayerListUtil;
import su.catlean.zkm.ZkmKeys;

public final class PlayerListUtil$onAfterReceivePacket$1
implements Runnable {
    final int H;
    final JoinedPlayer joinedPlayer;
    final String[] v;
    private static final long a = ZkmKeys.a(5319287408544337369L, 1526305187217338417L, MethodHandles.lookup().lookupClass()).a(242160210000506L);

    public PlayerListUtil$onAfterReceivePacket$1(int n, JoinedPlayer joinedPlayer, String[] stringArray) {
        this.H = n;
        this.joinedPlayer = joinedPlayer;
        this.v = stringArray;
    }

    @Override
    public final void run() {
        block0: {
            Thread.sleep(this.H);
            String string = this.joinedPlayer.getDisplayName();
            Intrinsics.checkNotNull(string);
            String string2 = string;
            PlayerListUtil playerListUtil = PlayerListUtil.INSTANCE;
            if (!PlayerListUtil.access$normalizeName(playerListUtil, string2) || !PlayerListUtil.getCopyOnWriteArrayList().add(this.joinedPlayer)) break block0;
            PlayerListUtil.access$onPlayerList(PlayerListUtil.INSTANCE, this.v);
        }
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

