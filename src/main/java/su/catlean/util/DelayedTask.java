/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import kotlin.jvm.functions.Function0;

public final class DelayedTask
implements Runnable {
    final int d;
    final Function0 T;

    public DelayedTask(int n, Function0 function0) {
        this.d = n;
        this.T = function0;
    }

    @Override
    public final void run() {
        Thread.sleep(this.d);
        this.T.invoke();
    }
}

