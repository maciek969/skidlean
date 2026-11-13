package su.catlean.module.rage;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

public enum NoFallMode {
    Rubberband,
    Items,
    MatrixOffGround,
    Vanilla,
    GrimOld,
    GRIM_NEW,
    GrimBypass;

    private static final NoFallMode[] $VALUES = values();
    private static final EnumEntries<NoFallMode> $ENTRIES = EnumEntriesKt.enumEntries($VALUES);
    private static String g;

    @NotNull
    public static EnumEntries<NoFallMode> get$ENTRIES() {
        return $ENTRIES;
    }

    public static void zkmSetFlag(String string) {
        g = string;
    }

    public static String zkmGetFlag() {
        return g;
    }
}

