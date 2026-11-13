/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.lang.invoke.MethodHandles;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public abstract class Element {
    private static String A;

    public abstract void render(long var1, @NotNull DrawContext var3, float var4, float var5, float var6, float var7);

    public abstract boolean isHovered(float var1, float var2);

    public static void zkmSetFlag(String string) {
        A = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return A;
    }

    static {
        ZkmKeys.a(-2157639003040347616L, 4819509655162878365L, MethodHandles.lookup().lookupClass()).a(8263599846934L);
    }
}

