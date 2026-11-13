/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;

public interface ElytraListener {
    public void onPreSync(long var1, @NotNull PreSyncEvent var3);

    public void onPreElytra(@NotNull PreElytraEvent var1, long var2);

    public void onAfterElytra(@NotNull AfterElytraEvent var1);

    public void onMove(@NotNull MoveEvent var1, long var2);

    public void onAfterSendPacket(long var1, @NotNull AfterSendPacket var3);

    public void onReceivePacket(@NotNull ReceivePacket var1, long var2, char var4);

    public void onPlayerUpdate(int var1, @NotNull PlayerUpdateEvent var2, char var3, char var4);

    public void onEnable(char var1, int var2, short var3);

    public void onDisable();

    public void onFireworkVelocity(@NotNull FireWorkVelocityEvent var1);

    public void onSetPose(@NotNull SetPoseEvent var1);

    public void handlePacket(long var1, @NotNull ReceivePacket var3);
}

