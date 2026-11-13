/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
import su.catlean.gui.Component;
import su.catlean.gui.ItemPresetEditor;

public final class ItemPresetEditor$Companion {
    private ItemPresetEditor$Companion() {
    }

    @Nullable
    public final Component G() {
        return ItemPresetEditor.getComponent();
    }

    public final void X(@Nullable Component component) {
        ItemPresetEditor.setComponent(component);
    }

    public ItemPresetEditor$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

