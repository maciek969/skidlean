package su.catlean.module.basic;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

public enum AntiKnockBackMode {
    Cancel,
    Custom,
    OldGrim,
    GrimNew,
    GrimBypass;

    private static final AntiKnockBackMode[] $VALUES = values();
    private static final EnumEntries<AntiKnockBackMode> $ENTRIES = EnumEntriesKt.enumEntries($VALUES);
    private static boolean b;

    @NotNull
    public static EnumEntries<AntiKnockBackMode> get$ENTRIES() {
        return $ENTRIES;
    }

    public static void zkmSetFlag(boolean bl) {
        b = bl;
    }

    public static boolean zkmGetFlag() {
        return b;
    }

    public static boolean w() {
        return true;
    }
}

