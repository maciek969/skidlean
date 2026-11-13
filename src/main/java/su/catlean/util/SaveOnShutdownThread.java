/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.MethodHandles;
import su.catlean.config.ConfigStore;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class SaveOnShutdownThread
extends Thread {
    private static boolean O;
    private static final long a;

    @Override
    public void run() {
        block0: {
            long l = a ^ 0x5E9628D07651L;
            long l2 = l ^ 0x6E97CD176E98L;
            boolean bl = SaveOnShutdownThread.q();
            ConfigStore.INSTANCE.save(l2);
            boolean bl2 = bl;
            DiscordRpcSettings.INSTANCE.sendPresence();
            super.run();
            if (bl2) break block0;
            Module.zkmSetFlag(new Module[5]);
        }
    }

    public static void zkmSetFlag(boolean bl) {
        O = bl;
    }

    public static boolean zkmGetFlag() {
        return O;
    }

    public static boolean q() {
        return true;
    }

    static {
        a = ZkmKeys.a(451139238856524211L, -3838081566582568328L, MethodHandles.lookup().lookupClass()).a(6182769701399L);
        if (!SaveOnShutdownThread.q()) {
            SaveOnShutdownThread.zkmSetFlag(true);
        }
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

