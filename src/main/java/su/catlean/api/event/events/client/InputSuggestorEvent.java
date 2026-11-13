/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 */
package su.catlean.api.event.events.client;

import com.mojang.brigadier.CommandDispatcher;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;

public final class InputSuggestorEvent
extends Event {
    @NotNull
    public static final InputSuggestorEvent INSTANCE = new InputSuggestorEvent();
    @NotNull
    private static String prefix = "";
    @Nullable
    private static CommandDispatcher<CommandSource> dispatcher;
    @Nullable
    private static ClientCommandSource source;

    private InputSuggestorEvent() {
    }

    @NotNull
    public final String getPrefix() {
        return prefix;
    }

    public final void setPrefix(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        prefix = string;
    }

    @Nullable
    public final CommandDispatcher<CommandSource> getDispatcher() {
        return dispatcher;
    }

    public final void setDispatcher(@Nullable CommandDispatcher<CommandSource> commandDispatcher) {
        dispatcher = commandDispatcher;
    }

    @Nullable
    public final ClientCommandSource getSource() {
        return source;
    }

    public final void setSource(@Nullable ClientCommandSource clientCommandSource) {
        source = clientCommandSource;
    }
}

