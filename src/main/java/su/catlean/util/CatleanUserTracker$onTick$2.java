/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import java.util.List;
import su.catlean.util.CatleanUserTracker;
import su.catlean.zkm.ZkmKeys;

public final class CatleanUserTracker$onTick$2
implements Runnable {
    final int K;
    private static final long a = ZkmKeys.a(3677195933568187457L, 8375471263312503100L, MethodHandles.lookup().lookupClass()).a(202130605757384L);

    public CatleanUserTracker$onTick$2(int n) {
        this.K = n;
    }

    @Override
    public final void run() {
        Thread.sleep(this.K);
        List list = CatleanUserTracker.INSTANCE.fetchUsers();
        CatleanUserTracker.getUsers().clear();
        CatleanUserTracker.getUsers().addAll(list);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

