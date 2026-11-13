package su.catlean.module.rage;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

public enum SpeedMode {
    STRICT_STRAFE,
    MATRIX,
    NCP,
    GRIM_ENTITY,
    FIREWORK,
    OLD_MATRIX,
    GrimBypass;

    private static final SpeedMode[] $VALUES = values();
    private static final EnumEntries<SpeedMode> $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    @NotNull
    public static EnumEntries<SpeedMode> get$ENTRIES() {
        return $ENTRIES;
    }
}