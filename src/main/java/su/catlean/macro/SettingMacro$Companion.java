/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.macro.SettingMacro$$serializer;

public final class SettingMacro$Companion {
    private SettingMacro$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return SettingMacro$$serializer.INSTANCE;
    }

    public SettingMacro$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

