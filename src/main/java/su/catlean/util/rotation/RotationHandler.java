/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

import org.jetbrains.annotations.NotNull;
import su.catlean.util.rotation.Rotation;

public interface RotationHandler {
    public void preAttack(int var1, long var2);

    public void postRotate(@NotNull Rotation var1, short var2, int var3, int var4);

    public void reset();
}

