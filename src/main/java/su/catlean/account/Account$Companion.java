/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.account;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.account.AccountSerializer;

public final class Account$Companion {
    private Account$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return AccountSerializer.INSTANCE;
    }

    public Account$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

