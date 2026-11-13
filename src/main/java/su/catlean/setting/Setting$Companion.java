/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.setting.Setting;

public final class Setting$Companion {
    private Setting$Companion() {
    }

    @NotNull
    public final Json getJson() {
        return Setting.getJson();
    }

    public Setting$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

