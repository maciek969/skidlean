/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 */
package su.catlean.util.combat;

import com.google.common.collect.Queues;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.TrackedPosition;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;
import net.minecraft.network.packet.s2c.common.DisconnectS2CPacket;
import net.minecraft.network.packet.s2c.play.BossBarS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityPositionSyncS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.HealthUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.StopSoundS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.module.attack.Aura;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.render.RenderState;
import su.catlean.util.Globals;
import su.catlean.util.TimedPacket;
import su.catlean.util.rotation.DefaultRotationHandler;
import su.catlean.zkm.ZkmKeys;

public final class Backtrack {
    @NotNull
    public static final Backtrack INSTANCE;
    @NotNull
    private static final ConcurrentLinkedQueue packetQueue;
    @NotNull
    private static final ConcurrentLinkedQueue s;
    @Nullable
    private static PlayerEntity target;
    @Nullable
    private static Vec3d r;
    @Nullable
    private static Vec3d R;
    private static long U;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private Backtrack() {
    }

    @Nullable
    public final PlayerEntity getTarget() {
        return target;
    }

    public final void setTarget(@Nullable PlayerEntity playerEntity) {
        target = playerEntity;
    }

    public final void X(long l, @NotNull Render3DEvent render3DEvent) {
        long l2;
        block3: {
            block2: {
                l2 = (l = a ^ l) ^ 0x18ACBCD1F44BL;
                Intrinsics.checkNotNullParameter(render3DEvent, "event");
                if (target == null || r == null || R == null) break block2;
                PlayerEntity playerEntity = target;
                Intrinsics.checkNotNull(playerEntity);
                Entity entity = playerEntity;
                Vec3d vec3d = AntiKnockBack.INSTANCE.getPos(entity);
                Vec3d vec3d2 = r;
                Intrinsics.checkNotNull(vec3d2);
                if (!(vec3d.squaredDistanceTo(vec3d2) < 1.0)) break block3;
            }
            return;
        }
        int n = (int)((double)System.currentTimeMillis() / 2.0 % 360.0);
        n = n >= 180 ? 360 - n : n;
        Vec3d vec3d = R;
        Intrinsics.checkNotNull(vec3d);
        Vec3d vec3d3 = r;
        Intrinsics.checkNotNull(vec3d3);
        float f = RangesKt.coerceIn((float)(Globals.now() - U) / 100.0f, 0.0f, 1.0f);
        Vec3d vec3d4 = vec3d3;
        Vec3d vec3d5 = vec3d;
        Vec3d vec3d6 = RenderState.INSTANCE.lerpVec(vec3d5, vec3d4, f);
        MatrixStack matrixStack = render3DEvent.getStack();
        PlayerEntity playerEntity = target;
        Intrinsics.checkNotNull(playerEntity);
        RenderState.INSTANCE.drawPlayerHead(matrixStack, vec3d6, playerEntity, l2, (float)n / 360.0f, true);
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void onReceivePacket(@NotNull ReceivePacket var1_1) {
        block38: {
            block37: {
                block36: {
                    block35: {
                        block34: {
                            block33: {
                                var12_2 = DefaultRotationHandler.zkmIntConstant();
                                v0 = var1_1;
                                if (var12_2 == 0) {
                                    Intrinsics.checkNotNullParameter(v0, "e");
                                    if (Backtrack.packetQueue.isEmpty() && Backtrack.s.isEmpty()) {
                                        v1 = Backtrack.target;
                                        if (var12_2 == 0) {
                                            if (v1 == null) return;
                                            v2 = Backtrack.target;
                                            v1 = v2;
                                            Intrinsics.checkNotNull(v2);
                                        }
                                        if (!v1.isAlive()) {
                                            return;
                                        }
                                    }
                                    v0 = var1_1;
                                }
                                var13_3 = v0.getPacket();
                                var14_4 = var13_3;
                                v3 = var14_4 instanceof ChatMessageC2SPacket;
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof GameMessageS2CPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof WorldTimeUpdateS2CPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof CommandExecutionC2SPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof PlaySoundS2CPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof StopSoundS2CPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof OverlayMessageS2CPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof HealthUpdateS2CPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3 != false) return;
                                    v3 = var14_4 instanceof BossBarS2CPacket;
                                }
                                if (var12_2 == 0) {
                                    if (v3) {
                                        return;
                                    }
                                    v3 = var14_4 instanceof PlayerPositionLookS2CPacket;
                                }
                                if (var12_2 != 0) break block33;
                                if (v3) break block34;
                                v3 = var14_4 instanceof DisconnectS2CPacket;
                            }
                            if (!v3) break block35;
                        }
                        this.reset();
                        return;
                    }
                    var14_4 = null;
                    v4 = var13_3 instanceof EntityS2CPacket;
                    if (var12_2 != 0) break block36;
                    if (!v4) break block37;
                    v4 = Intrinsics.areEqual(((EntityS2CPacket)var13_3).getEntity(Globals.getWorld()), Backtrack.target);
                }
                if (v4 == false) return;
                var15_5 = new TrackedPosition();
                v5 = Backtrack.r;
                Intrinsics.checkNotNull(v5);
                var15_5.setPos(v5);
                var14_4 = var15_5.withDelta(((EntityS2CPacket)var13_3).getDeltaX(), ((EntityS2CPacket)var13_3).getDeltaY(), ((EntityS2CPacket)var13_3).getDeltaZ());
                Backtrack.U = Globals.now();
            }
            v6 = var13_3 instanceof EntityPositionS2CPacket;
            if (var12_2 == 0) {
                if (v6) {
                    var15_5 = Backtrack.target;
                    if (var15_5 == null) return;
                    v7 = ((EntityPositionS2CPacket)var13_3).comp_3237();
                    if (var12_2 == 0) {
                        if (v7 != var15_5.getId()) return;
                        v7 = 1;
                    }
                    if (v7 == 0) return;
                    var14_4 = ((EntityPositionS2CPacket)var13_3).comp_3238().comp_3148();
                    if (var12_2 != 0) {
                        return;
                    }
                }
                v6 = var13_3 instanceof EntityPositionSyncS2CPacket;
            }
            if (v6) {
                var15_5 = Backtrack.target;
                if (var15_5 == null) return;
                v8 = ((EntityPositionSyncS2CPacket)var13_3).comp_3223();
                if (var12_2 == 0) {
                    if (v8 != var15_5.getId()) return;
                    v8 = 1;
                }
                if (v8 == 0) return;
                var14_4 = ((EntityPositionSyncS2CPacket)var13_3).comp_3224().comp_3148();
                if (var12_2 != 0) {
                    return;
                }
            }
            v9 = var14_4;
            if (var12_2 != 0) break block38;
            if (v9 == null) ** GOTO lbl-1000
            Backtrack.R = Backtrack.r;
            v9 = var14_4;
        }
        Backtrack.r = v9;
        if (var12_2 != 0) return;
        if (Backtrack.target != null) {
            var16_6 = Globals.getPlayer();
            v10 = AntiKnockBack.INSTANCE.getPos(var16_6).squaredDistanceTo((Vec3d)var14_4);
            var20_7 = Globals.getPlayer();
            v11 = AntiKnockBack.INSTANCE.getPos(var20_7);
            v12 = Backtrack.target;
            Intrinsics.checkNotNull(v12);
            var11_8 = v12;
            cfr_temp_0 = v10 - v11.squaredDistanceTo(AntiKnockBack.INSTANCE.getPos(var11_8));
            v13 = cfr_temp_0 == 0.0 ? 0 : (cfr_temp_0 < 0.0 ? -1 : 1);
            if (var12_2 == 0) {
                if (v13 <= 0) {
                    this.flushPackets(true);
                    return;
                } else {
                    ** GOTO lbl-1000
                }
            }
        } else lbl-1000:
        // 4 sources

        {
            v13 = Backtrack.packetQueue.add(new TimedPacket(var13_3, Globals.now()));
        }
        var1_1.cancel();
    }

    /*
     * Unable to fully structure code
     */
    public final void update() {
        block7: {
            block6: {
                var6_1 = DefaultRotationHandler.zkmIntConstant();
                v0 = Aura.INSTANCE;
                if (var6_1 != 0) break block6;
                if (!v0.getEnabled()) ** GOTO lbl-1000
                v0 = Aura.INSTANCE;
            }
            v1 = v0.getTarget();
            if (var6_1 != 0) break block7;
            if (v1 == null) ** GOTO lbl-1000
            v1 = Aura.INSTANCE.getTarget();
        }
        if (var6_1 != 0) ** GOTO lbl17
        if (v1 instanceof PlayerEntity) {
            v2 = Aura.INSTANCE.getTarget();
            v1 = v2;
            Intrinsics.checkNotNull(v2, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
lbl17:
            // 2 sources

            v3 = (PlayerEntity)v1;
        } else lbl-1000:
        // 3 sources

        {
            v3 = null;
        }
        v4 = var7_2 = v3;
        if (var6_1 == 0) {
            if (v4 == null) {
                this.reset();
                return;
            }
            v4 = var7_2;
        }
        if (var6_1 == 0) {
            if (!Intrinsics.areEqual(v4, Backtrack.target)) {
                this.reset();
                Backtrack.r = AntiKnockBack.INSTANCE.getPos(var7_2);
            }
            v4 = var7_2;
        }
        Backtrack.target = v4;
    }

    public final void shouldDelay() {
        block10: {
            block9: {
                PlayerEntity playerEntity;
                int n;
                block8: {
                    block6: {
                        block7: {
                            block4: {
                                block5: {
                                    n = DefaultRotationHandler.zkmIntConstant();
                                    if (n != 0) break block4;
                                    if (Globals.getMc().world == null) break block5;
                                    playerEntity = Globals.getMc().player;
                                    if (n != 0) break block6;
                                    if (playerEntity != null) break block7;
                                }
                                packetQueue.clear();
                                target = null;
                                r = null;
                            }
                            return;
                        }
                        playerEntity = target;
                    }
                    if (n != 0) break block8;
                    if (playerEntity == null) break block9;
                    PlayerEntity playerEntity2 = target;
                    playerEntity = playerEntity2;
                    Intrinsics.checkNotNull(playerEntity2);
                }
                if (!playerEntity.isAlive()) break block9;
                Backtrack.flushPackets$default(this, false, 1, null);
                if (n == 0) break block10;
            }
            this.reset();
        }
        s.removeIf(arg_0 -> Backtrack.invokePredicate(Backtrack::applyPacket, arg_0));
    }

    public final void flushPackets(boolean bl) {
        packetQueue.removeIf(arg_0 -> Backtrack.invokePredicate2(arg_0 -> Backtrack.flushPackets$lambda$0(bl, arg_0), arg_0));
    }

    /*
     * WARNING - void declaration
     */
    public static void flushPackets$default(Backtrack backtrack, boolean bl, int n, Object object) {
        void var2_3;
        int n2 = DefaultRotationHandler.zkmIntConstant();
        boolean bl2 = var2_3 & 1;
        if (n2 == 0) {
            bl2 = false;
        }
        boolean bl3 = bl2;
        backtrack.flushPackets(bl3);
    }

    public final void reset() {
        packetQueue.removeIf(arg_0 -> Backtrack.invokePredicate3(Backtrack::reset$lambda$0, arg_0));
        target = null;
        r = null;
    }

    private static final boolean applyPacket(Packet packet) {
        Backtrack backtrack = INSTANCE;
        try {
            Object object = backtrack;
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.Packet<net.minecraft.network.protocol.game.ClientGamePacketListener>");
            packet.apply((PacketListener)Globals.getNetworkHandler());
            object = Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Object object = Result.constructor-impl(ResultKt.createFailure(throwable));
        }
        return true;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean flushPackets$lambda$0(boolean bl, TimedPacket timedPacket) {
        boolean bl2 = true;
        s.add(timedPacket.getPacket());
        return bl2;
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean reset$lambda$0(TimedPacket timedPacket) {
        Backtrack backtrack = INSTANCE;
        try {
            Object object = backtrack;
            Packet packet = timedPacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.Packet<net.minecraft.network.protocol.game.ClientGamePacketListener>");
            packet.apply((PacketListener)Globals.getNetworkHandler());
            object = Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Object object = Result.constructor-impl(ResultKt.createFailure(throwable));
        }
        return true;
    }

    private static final boolean invokePredicate3(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        Backtrack.a = ZkmKeys.a(170663042456987575L, -7070276741611711763L, MethodHandles.lookup().lookupClass()).a(209365014410064L);
                        var20 = Backtrack.a ^ 13446951276270L;
                        Backtrack.d = new HashMap<K, V>(13);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[6];
                        var16_4 = 0;
                        var15_5 = "\u00e6ViU\u00c9\u00f6a\u00a3\u001c\u00af\u00ac\u001c\u00bcx\u00d9\u00ad\u00a8\u0081jx!\u00c8\u00d0\t\u0089d\u00b0b\u00e4i\u0080\u00d7\u0090\u00ec\u00b1\u00fb[Cx\u00c67]?v\u00a2\u001cJ/8\u00a8\u0005\u00f3\u0005c\u0095_}1p\u00d7\u007fJ\u00b4av\u00cb0\u001e\u0014\u00a3\u00db\u00b4\u00f5\u00fa\u00de9\u00a7\u00ecT\u00ef\u0019\u0014E\u00ac\u0083\u00f7m\u00d9\bQ\u00a55,\u0017p\u001d7\u00e0-r\u00d8uQ\u00f8\u00aa\u00e0feZ\u008e\u0083\u00f1\u00e3j*\u00ae\u0088\u00edZR\u00a1\u00bbm\u00c7\u009f\u00f5\u00a6bCj\u00a6\u00f4\u00c0\u0087\u0017N\u0092\u00a9\u00e6\u00e8\u0005|\u00f6\u00dd\u00f8\u008c\u00b0G\u00fc\u00aa\u00da\u0085\u001c\u00d0$\f\u0097j\fB\u008b\u00d1C\"r\u008f1\u00f7\u00a1\u00a1Rk&^\u0018/!j\u00b2\u00cd-\u00a4\u00d5]D\u00fa\u009ff#\u00c3\u00e9\u008e#\u00d1\u00a2\u00cf3pZ4\u00e3\u0080\u0098_[T\u00c6\u00a4\u00c7\u0080iVb\u0005\u00d6\u00ccc2jvX\\p\u0016%\u0012\t\u00f4\u009b\u00a4\u0089\u0084\u00f1\u001d\u00d2\u00c0Dl\u00b52\u008b\u00ed&\u00ee\u00ebdw\u00b8\u00c0>\u00b6\u0081\u0080}B\u00e9\u00c8\u00865:\u00b9d\u00cf\u0094\u000e\u00ac$9\u00d8$3\u00d3\u00f4I\u00bfy\u00d1M+\u008a\n\u00b1\u0096\u001c\u0095\u00a8OVU]\u00e2\u00d5\u0002Zx;!w\u00b9\u00e3\u0017\u009b\u00b9\u00bb\u00e8\u000bE\u0088\u00846FV\u0016\u001dP\u00d5\u00abs\u0012:\u0093\u001f/\u0004\u0019\u00c5\u00f9/\u000b\u009b\u00e0R\u009a\u00de\u0004P?\u0080\u00f0\u00ba\u009a3\u00ff\u00976\u00af\u00d8\u0012\u00a0\u00db\u008b\u00b2\u00e9\u00ec\u00c1\u00e6\u00d6\u00ae\u0085w\u0019\u00b4\u0015\u0003\u0099\u0006A!I\u008a\"\u00d1Q\u001a\u00f8\u008d\u00f3\n\u00d07\u0003\u0006,\u0081\u00b4\u009e+\u0003\u00f8LW\u00d9\u00c4\u0093P\u00af[\u00e0+k\u00ad\u00a6\u00a8cJ\u0098U\u0016\u001e\u00da\u00f3\u0090\u00eb\u0081wWRz\u00a0\u00a1\u00e1\u001e7\u00a6\u0099\u00dcKe7pO\u0095p\u001f\u001a\u0089\u00f3\u008dJ\u00c7PW\u00f4\u00e4eX\n,\u00cc\u00cd\u00b0(Y9\u001f\u00b4\u00a8\u00b1\u00a4p5\u0007\u00f6\u008a\u00b1\u000b\u00e5\u00a9\u00a3\u00daX\u0002\u008fw\u00a4y\r\u00a5k\u001d\u008f{\u0086B\tOM\u0097\u00bb\u0089\u00c6B\u00b1\u00ab;\u009f!\u00c49\u00bbZ{\u0092\u00c8.j5\u00a6\u0013\u00bf\u00a3N\u0084\u00d3\t\u0011\u00b7#\u0087\u0005\u00a2z\u00d2id\u00c5)\u00d4kT\u00fe\u0081\u00f9\u0017\u00ba\u000f)\u0016\u00e1#\u00d4U\u0087\u00ff(\u00b9\u0010%I\u00f1F:NG+";
                        var17_6 = 555;
                        var14_7 = 48;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Backtrack.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "Q\u00b5\u00db\u00af77\u00dc\u00de\u00dd\u00f9{l\u0001\u00fa\u00ccHx\u00d7\u00baf\u00d2\"\u00dau\t\u0001\u00d6\u00c4\u00dbw\u0080\u00e5\u00c2\n\u0005\u00c3JLI\u00e9w\u0018\u0000<[\u0085\u00ddI\u0001\u00bbB\u00f8\u00ed\u0004-\u001c\u00a9x\u00d6\u0089\u00c6r(\u00a5\u00cf\u00c1\u00f3\u00aa0\u00ec\"\u0080\u00e2\u00c8\u00f9Y\u0007\u007f\u008f\u00e2E\n\u00163\u00c4\u00fa\u0011C\u0081\u00f0\u00104S\u00f1\u00e7\u00d9\u00d3\u0089\u0096S\u0086\u007f>\u0002\u0014y\u00c6W\u0084\u00e9W\u0094%Z\b\u00f8\u0090\u00e95\u00dc*\u008a\u00ca\u00e3\u00c8\u00eft\u00d8\u00f7Z\u00a1\u00bff\u00c5\u00a6\u0018n";
                            var17_6 = 137;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Backtrack.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                Backtrack.b = var18_3;
                Backtrack.c = new String[6];
                Backtrack.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "x\u00cd\u00a2$\u0084\u0088\u00d3\u00b4\u00c9\u00e6\u0094\u00d8-\u00caX\u0085\u00a7\u0087'\u0001\u0001F\u00b6\u00c2";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        Backtrack.e = var6_12;
        Backtrack.f = new Integer[3];
        Backtrack.INSTANCE = new Backtrack();
        v11 = Queues.newConcurrentLinkedQueue();
        Intrinsics.checkNotNullExpressionValue(v11, "newConcurrentLinkedQueue(...)");
        Backtrack.packetQueue = v11;
        v12 = Queues.newConcurrentLinkedQueue();
        Intrinsics.checkNotNullExpressionValue(v12, "newConcurrentLinkedQueue(...)");
        Backtrack.s = v12;
        Backtrack.U = Globals.now();
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static String zkmDecodeUtf8(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static String zkmDecryptString(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6461;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/bv", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = b[n2].getBytes("ISO-8859-1");
            Backtrack.c[n2] = Backtrack.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Backtrack.zkmDecryptString(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/bv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1699;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/bv", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Backtrack.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Backtrack.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/bv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Backtrack.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(Backtrack.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

