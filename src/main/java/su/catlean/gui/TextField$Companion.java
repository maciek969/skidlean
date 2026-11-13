/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
import su.catlean.gui.TextField;

public final class TextField$Companion {
    private TextField$Companion() {
    }

    @Nullable
    public final TextField T() {
        return TextField.getFocused();
    }

    public final void A(@Nullable TextField textField) {
        TextField.setFocused(textField);
    }

    public TextField$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

