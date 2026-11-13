/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import su.catlean.util.JoinedPlayer;
import su.catlean.util.PlayerListUtil;
import su.catlean.zkm.ZkmKeys;

public final class PlayerListUtil$onPlayerUpdate$1
implements Comparator {
    private static final long a = ZkmKeys.a(-8546052113798930415L, -6499725517617054273L, MethodHandles.lookup().lookupClass()).a(156785441641116L);

    public final int compare(Object object, Object object2) {
        JoinedPlayer joinedPlayer = (JoinedPlayer)object;
        Intrinsics.checkNotNull(joinedPlayer);
        Comparable comparable = Integer.valueOf(PlayerListUtil.access$getSortKey(PlayerListUtil.INSTANCE, joinedPlayer));
        joinedPlayer = (JoinedPlayer)object2;
        Comparable comparable2 = comparable;
        Intrinsics.checkNotNull(joinedPlayer);
        return ComparisonsKt.compareValues(comparable2, PlayerListUtil.access$getSortKey(PlayerListUtil.INSTANCE, joinedPlayer));
    }
}

