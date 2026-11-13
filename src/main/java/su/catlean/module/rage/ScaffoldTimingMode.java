/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

package su.catlean.module.rage;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

enum ScaffoldTimingMode {
    SEQUENTIAL,
    VANILLA,
    GRIM,
    GRIM_BYPASS;

    private static final ScaffoldTimingMode[] $VALUES = values();
    private static final EnumEntries<ScaffoldTimingMode> $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    @NotNull
    public static EnumEntries<ScaffoldTimingMode> get$ENTRIES() {
        return $ENTRIES;
    }
}

