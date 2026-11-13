/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.account;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.account.AuthResultSerializer;

public final class AuthResult$Companion {
    private AuthResult$Companion() {
    }

    @NotNull
    public final KSerializer serializer() {
        return AuthResultSerializer.INSTANCE;
    }

    public AuthResult$Companion(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}

