/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientPlayerEntity;
import su.catlean.util.Globals;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class TickTimer {
    private int E;
    private static String[] I;
    private static final long a;

    public TickTimer() {
        this.reset();
    }

    public final boolean hasPassed(short s, int n) {
        int n2 = Timer.zkmGetFlag();
        if (this.getElapsedTicks() < 0) {
            this.reset();
        }
        int n3 = this.getElapsedTicks();
        int n4 = n2;
        if (s >= 0) {
            n4 = n;
        }
        return n3 >= n4;
    }

    public final boolean hasPassedAndReset(int n) {
        int n2;
        boolean bl = Timer.n();
        int n3 = this.getElapsedTicks();
        if (bl) {
            if (n3 < 0) {
                this.reset();
            }
            n3 = this.getElapsedTicks();
        }
        if (bl) {
            n3 = n3 >= n ? 1 : 0;
        }
        int n4 = n2 = n3;
        if (bl) {
            if (n4 != 0) {
                this.reset();
            }
            n4 = n2;
        }
        return n4 != 0;
    }

    /*
     * Unable to fully structure code
     */
    public final void setTicks(int var1_1) {
        var4_2 = Timer.n();
        v0 = Globals.getMc().player;
        if (!var4_2) ** GOTO lbl10
        if (v0 == null) {
            v1 = 0;
        } else {
            v2 = Globals.getMc().player;
            v0 = v2;
            Intrinsics.checkNotNull(v2);
lbl10:
            // 2 sources

            v1 = v0.age;
        }
        this.E = v1 + var1_1;
    }

    public final void reset() {
        int n;
        if (Globals.getMc().player == null) {
            n = 0;
        } else {
            ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
            Intrinsics.checkNotNull(clientPlayerEntity);
            n = clientPlayerEntity.age;
        }
        this.E = n;
    }

    public final int getTicks() {
        return this.getElapsedTicks();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final int getElapsedTicks() {
        boolean bl = Timer.n();
        ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
        if (bl) {
            if (clientPlayerEntity == null) {
                return 0;
            }
            ClientPlayerEntity clientPlayerEntity2 = Globals.getMc().player;
            clientPlayerEntity = clientPlayerEntity2;
            Intrinsics.checkNotNull(clientPlayerEntity2);
        }
        int n = clientPlayerEntity.age - this.E;
        return n;
    }

    public static void zkmSetFlag(String[] stringArray) {
        I = stringArray;
    }

    public static String[] zkmGetFlag() {
        return I;
    }

    static {
        a = ZkmKeys.a(6433273369730990850L, 2705423016150637613L, MethodHandles.lookup().lookupClass()).a(150567668461993L);
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

