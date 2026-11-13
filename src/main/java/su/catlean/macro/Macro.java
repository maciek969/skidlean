/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ModuleConfig;
import su.catlean.macro.ChatMacro;
import su.catlean.macro.MacroSerializerProvider;
import su.catlean.macro.SettingMacro;
import su.catlean.setting.Bind;

@Serializable
public sealed interface Macro
permits ChatMacro, ModuleConfig, SettingMacro {
    @NotNull
    public static final MacroSerializerProvider macroSerializerProvider = MacroSerializerProvider.INSTANCE;

    @NotNull
    public String getId();

    public void setId(@NotNull String var1);

    @NotNull
    public Bind getBind();

    public void setBind(@NotNull Bind var1);

    public boolean isEnabled();

    public void setEnabled(boolean var1);
}

