/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.discord.PresenceDraft;
import su.catlean.discord.PresenceDrafts;
import su.catlean.zkm.ZkmKeys;

public final class PresenceCycler {
    @NotNull
    public static final PresenceCycler INSTANCE;
    @NotNull
    private static final List drafts;
    private static boolean N;
    private static int k;
    private static String[] W;
    private static final long a;

    private PresenceCycler() {
    }

    @NotNull
    public final List getDrafts() {
        return drafts;
    }

    public final boolean F() {
        return N;
    }

    public final void B(boolean bl) {
        N = bl;
    }

    public final int E() {
        return k;
    }

    public final void Z(int n) {
        k = n;
    }

    public final void cycleDraft() {
        if (N) {
            PresenceDrafts.setDraft(DiscordRpcSettings.INSTANCE, ((PresenceDraft)drafts.get(k)).getDraftId());
            k = (k + 1) % drafts.size();
        }
    }

    static {
        a = ZkmKeys.a(4960750192193325778L, -3953806531375182387L, MethodHandles.lookup().lookupClass()).a(245530227423405L);
        INSTANCE = new PresenceCycler();
        PresenceCycler.zkmSetFlag(new String[5]);
        drafts = new ArrayList();
    }

    public static void zkmSetFlag(String[] stringArray) {
        W = stringArray;
    }

    public static String[] zkmGetFlag() {
        return W;
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

