/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.llamalad7.mixinextras.sugar.Local
 *  com.mojang.brigadier.ParseResults
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.suggestion.Suggestions
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package su.catlean.mixins.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.suggestion.Suggestions;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.minecraft.client.gui.screen.ChatInputSuggestor;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.command.CommandSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import su.catlean.api.event.events.client.InputSuggestorEvent;

@Mixin(value={ChatInputSuggestor.class})
public abstract class MixinCommandSuggestions {
    @Final
    @Shadow
    TextFieldWidget textField;
    @Shadow
    boolean completingSuggestions;
    @Shadow
    private ParseResults<CommandSource> parse;
    @Shadow
    private CompletableFuture<Suggestions> pendingSuggestions;
    @Shadow
    private ChatInputSuggestor.SuggestionWindow window;

    @Shadow
    protected abstract void showCommandSuggestions();

    @Inject(method={"method_23934"}, at={@At(value="INVOKE", target="Lcom/mojang/brigadier/StringReader;canRead()Z", remap=false)}, cancellable=true)
    public void refreshHook(CallbackInfo ci, @Local StringReader reader) {
        if (InputSuggestorEvent.INSTANCE.call() && reader.canRead(InputSuggestorEvent.INSTANCE.getPrefix().length()) && reader.getString().startsWith(InputSuggestorEvent.INSTANCE.getPrefix(), reader.getCursor())) {
            int cursor;
            reader.setCursor(reader.getCursor() + 1);
            if (this.parse == null) {
                this.parse = Objects.requireNonNull(InputSuggestorEvent.INSTANCE.getDispatcher()).parse(reader, (Object)Objects.requireNonNull(InputSuggestorEvent.INSTANCE.getSource()));
            }
            if (!((cursor = this.textField.getCursor()) < 1 || this.window != null && this.completingSuggestions)) {
                this.pendingSuggestions = Objects.requireNonNull(InputSuggestorEvent.INSTANCE.getDispatcher()).getCompletionSuggestions(this.parse, cursor);
                this.pendingSuggestions.thenRun(() -> {
                    if (this.pendingSuggestions.isDone()) {
                        this.showCommandSuggestions();
                    }
                });
            }
            ci.cancel();
        }
    }
}

