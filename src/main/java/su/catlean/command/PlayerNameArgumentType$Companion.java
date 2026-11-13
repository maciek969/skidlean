/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.command;

import java.lang.invoke.MethodHandles;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.PlayerNameArgumentType;
import su.catlean.zkm.ZkmKeys;

public final class PlayerNameArgumentType$Companion {
    private static int[] T;

    private PlayerNameArgumentType$Companion() {
    }

    @NotNull
    public final PlayerNameArgumentType create() {
        return new PlayerNameArgumentType();
    }

    public PlayerNameArgumentType$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static void zkmSetFlag(int[] nArray) {
        T = nArray;
    }

    public static int[] zkmGetFlag() {
        return T;
    }

    static {
        ZkmKeys.a(-6996991034192595003L, -5070519483118889662L, MethodHandles.lookup().lookupClass()).a(239622729392465L);
    }
}

