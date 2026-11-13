/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;

public interface GuiElement {
    public void render(@NotNull DrawContext var1, float var2, float var3, long var4, float var6, float var7);

    public void charTyped(char var1);

    public void keyPressed(int var1, short var2, int var3, int var4);

    public boolean mouseClicked(long var1, float var3, float var4);
}

