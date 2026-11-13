/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.EventPlayerTravel;
import su.catlean.api.event.events.player.EventPostPlayerTravel;
import su.catlean.api.event.events.player.FixVelocityEvent;
import su.catlean.api.event.events.player.JumpEvent;
import su.catlean.api.event.events.player.KeyboardInputEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PostInteractItemEvent;
import su.catlean.api.event.events.player.PostSyncEvent;
import su.catlean.api.event.events.player.PreInteractItemEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.api.event.events.render.LookRenderEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.world.FireWorkVectorEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ClientInputAccessor;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.module.basic.ElytraRecast;
import su.catlean.module.basic.MoveFixMode;
import su.catlean.module.cosmetic.FreeLook;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.module.rage.MaceElytraTarget;
import su.catlean.module.world.FreeCam;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.MovementUtil;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.AimSettings;
import su.catlean.util.rotation.LambdaRotationRequest;
import su.catlean.util.rotation.ManagedRotationHandler;
import su.catlean.util.rotation.MoveFix;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class RotationManager
implements Manager {
    @NotNull
    public static final RotationManager INSTANCE;
    @NotNull
    private static ArrayList pendingRequests;
    @NotNull
    private static ArrayList requests;
    @NotNull
    private static Rotation savedRotation;
    @Nullable
    private static Rotation S;
    @Nullable
    private static Rotation l;
    @Nullable
    private static Rotation m;
    @Nullable
    private static Rotation playerRotation;
    @Nullable
    private static RotationRequest currentRequest;
    @Nullable
    private static RotationRequest F;
    private static float bodyYaw;
    private static float savedYaw;
    private static float savedPitch;
    private static Module[] c;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private RotationManager() {
    }

    @Nullable
    public final Rotation c() {
        return l;
    }

    public final void F(@Nullable Rotation rotation) {
        l = rotation;
    }

    @Nullable
    public final Rotation H() {
        return m;
    }

    public final void f(@Nullable Rotation rotation) {
        m = rotation;
    }

    public final void submit(@NotNull RotationRequest rotationRequest) {
        Intrinsics.checkNotNullParameter(rotationRequest, "sequence");
        AntiKnockBack.INSTANCE.L();
        pendingRequests.add(rotationRequest);
    }

    public final void queue(@NotNull RotationRequest rotationRequest) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(rotationRequest, (String)((Object)RotationManager.zkmBootstrap("c", (int)23602, (long)(0x1587095160EEF4D0L ^ l))));
        AntiKnockBack.INSTANCE.L();
        requests.add(rotationRequest);
    }

    public final void rotate(@NotNull Rotation rotation, int n) {
        Intrinsics.checkNotNullParameter(rotation, "rotation");
        AntiKnockBack.INSTANCE.L();
        pendingRequests.add(new RotationRequest(rotation, n, RotationManager::rotate$lambda$0));
    }

    public static void rotate$default(RotationManager rotationManager, Rotation rotation, Object object) {
        int n = 150;
        rotationManager.rotate(rotation, n);
    }

    @NotNull
    public final Rotation hasActiveRequest(boolean bl) {
        Object object;
        Object v0;
        Iterable iterable = pendingRequests;
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            v0 = null;
        } else {
            Object t = iterator2.next();
            if (!iterator2.hasNext()) {
                v0 = t;
            } else {
                RotationRequest rotationRequest = (RotationRequest)t;
                int n = rotationRequest.getPriority();
                do {
                    Object t2;
                    RotationRequest rotationRequest2;
                    int n2;
                    if (n >= (n2 = (rotationRequest2 = (RotationRequest)(t2 = iterator2.next())).getPriority())) continue;
                    t = t2;
                    n = n2;
                } while (iterator2.hasNext());
                v0 = t;
            }
        }
        if ((object = (RotationRequest)v0) == null || (object = ((RotationRequest)object).getRotation()) == null || (object = ((Rotation)object).fixSensitivity()) == null) {
            object = bl ? new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null) : new Rotation(MovementUtil.INSTANCE.getLastYaw(), MovementUtil.INSTANCE.getLastPitch(), false, null, 12, null);
        }
        return object;
    }

    public static Rotation hasActiveRequest$default(RotationManager rotationManager, boolean bl, Object object) {
        bl = false;
        return rotationManager.hasActiveRequest(bl);
    }

    @Flow(priority=20)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (F != null) {
            RotationRequest rotationRequest = F;
            Intrinsics.checkNotNull(rotationRequest);
            rotationRequest.getAction().invoke();
            F = null;
        }
    }

    @Flow(priority=-20)
    private final void onPlayerUpdate2(PlayerUpdateEvent playerUpdateEvent) {
        Object object;
        Object object2;
        Object v0;
        requests.removeIf(arg_0 -> RotationManager.invokePredicate(RotationManager::onPlayerUpdate2$lambda$0, arg_0));
        Iterable iterable = pendingRequests;
        Object object3 = iterable.iterator();
        if (!object3.hasNext()) {
            v0 = null;
        } else {
            object2 = object3.next();
            if (!object3.hasNext()) {
                v0 = object2;
            } else {
                RotationRequest rotationRequest = (RotationRequest)object2;
                int n = rotationRequest.getPriority();
                do {
                    RotationRequest rotationRequest2;
                    int n2;
                    if (n >= (n2 = (rotationRequest2 = (RotationRequest)(object = object3.next())).getPriority())) continue;
                    object2 = object;
                    n = n2;
                } while (object3.hasNext());
                v0 = object2;
            }
        }
        currentRequest = v0;
        if (currentRequest != null) {
            RotationRequest rotationRequest = currentRequest;
            Intrinsics.checkNotNull(rotationRequest);
            Rotation rotation = S = rotationRequest.getRotation();
            Intrinsics.checkNotNull(rotation);
            if (rotation.getYaw() == Globals.getPlayer().getYaw()) {
                Rotation rotation2 = S;
                Intrinsics.checkNotNull(rotation2);
                if (rotation2.getPitch() == Globals.getPlayer().getPitch()) {
                    Object object4;
                    Iterable iterable2 = pendingRequests;
                    object2 = iterable2;
                    Object object5 = new ArrayList();
                    Iterator<Object> iterator2 = object2.iterator();
                    while (iterator2.hasNext()) {
                        Object t = iterator2.next();
                        RotationRequest rotationRequest3 = (RotationRequest)t;
                        if (!(!Intrinsics.areEqual(rotationRequest3, currentRequest))) continue;
                        object5.add(t);
                    }
                    iterable2 = (List)object5;
                    object2 = iterable2.iterator();
                    if (!object2.hasNext()) {
                        object4 = null;
                    } else {
                        object5 = object2.next();
                        if (!object2.hasNext()) {
                            object4 = object5;
                        } else {
                            object = (RotationRequest)object5;
                            int n = ((RotationRequest)object).getPriority();
                            do {
                                RotationRequest rotationRequest4;
                                int n3;
                                if (n >= (n3 = (rotationRequest4 = (RotationRequest)((Object)(iterator2 = object2.next()))).getPriority())) continue;
                                object5 = iterator2;
                                n = n3;
                            } while (object2.hasNext());
                            object4 = object5;
                        }
                    }
                    RotationRequest rotationRequest5 = (RotationRequest)object4;
                    if (rotationRequest5 != null) {
                        object3 = rotationRequest5;
                        S = ((RotationRequest)object3).getRotation();
                    }
                }
            }
            Rotation rotation3 = S;
            Intrinsics.checkNotNull(rotation3);
            S = rotation3.fixSensitivity();
            if (currentRequest instanceof ActionRotationRequest) {
                RotationRequest rotationRequest6 = currentRequest;
                Intrinsics.checkNotNull(rotationRequest6);
                rotationRequest6.getAction().invoke();
                currentRequest = null;
            }
            if (currentRequest instanceof LambdaRotationRequest) {
                RotationRequest rotationRequest7 = currentRequest;
                Intrinsics.checkNotNull(rotationRequest7);
                Rotation rotation4 = rotationRequest7.getRotation();
                RotationRequest rotationRequest8 = currentRequest;
                Intrinsics.checkNotNull(rotationRequest8);
                int n = rotationRequest8.getPriority();
                RotationRequest rotationRequest9 = currentRequest;
                Intrinsics.checkNotNull(rotationRequest9);
                F = new RotationRequest(rotation4, n, rotationRequest9.getAction());
                currentRequest = null;
            }
            pendingRequests.clear();
        }
    }

    @Flow(priority=20)
    private final void onPreSync(PreSyncEvent preSyncEvent) {
        savedRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
        m = l;
        l = null;
        if (S != null) {
            ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
            Rotation rotation = S;
            Intrinsics.checkNotNull(rotation);
            clientPlayerEntity.setYaw(rotation.getYaw());
            ClientPlayerEntity clientPlayerEntity2 = Globals.getPlayer();
            Rotation rotation2 = S;
            Intrinsics.checkNotNull(rotation2);
            clientPlayerEntity2.setPitch(rotation2.getPitch());
            l = S;
            S = null;
        }
    }

    @Flow(priority=-20)
    private final void onPostSync(PostSyncEvent postSyncEvent) {
        if (currentRequest != null) {
            RotationRequest rotationRequest = currentRequest;
            Intrinsics.checkNotNull(rotationRequest);
            rotationRequest.getAction().invoke();
            currentRequest = null;
        }
        Globals.getPlayer().setYaw(savedRotation.getYaw());
        Globals.getPlayer().setPitch(savedRotation.getPitch());
    }

    @Flow
    private final void onLookRender(LookRenderEvent lookRenderEvent) {
        block0: {
            if (l == null || m == null) break block0;
            bodyYaw = lookRenderEvent.getBodyYaw();
            Rotation rotation = m;
            Intrinsics.checkNotNull(rotation);
            float f = rotation.getYaw();
            Rotation rotation2 = l;
            Intrinsics.checkNotNull(rotation2);
            lookRenderEvent.setBodyYaw(MathHelper.wrapDegrees(RenderUtil.INSTANCE.lerp(f, rotation2.getYaw(), RenderState.INSTANCE.getTickDelta())));
            lookRenderEvent.setYaw(0.0f);
            Rotation rotation3 = m;
            Intrinsics.checkNotNull(rotation3);
            float f2 = rotation3.getPitch();
            Rotation rotation4 = l;
            Intrinsics.checkNotNull(rotation4);
            lookRenderEvent.setPitch(MathHelper.wrapDegrees(RenderUtil.INSTANCE.lerp(f2, rotation4.getPitch(), RenderState.INSTANCE.getTickDelta())));
        }
    }

    @Flow
    private final void onJump(JumpEvent jumpEvent) {
        if (this.C() || this.V()) {
            jumpEvent.setYaw(this.getServerYaw());
            jumpEvent.cancel();
        }
    }

    @Flow
    private final void onFixVelocity(FixVelocityEvent fixVelocityEvent) {
        if (this.V()) {
            fixVelocityEvent.setVelocity(this.rotateVector(this.getServerYaw(), fixVelocityEvent.getMovementInput(), fixVelocityEvent.getSpeed()));
            fixVelocityEvent.cancel();
        }
    }

    @Flow
    private final void onKeyboardInput(KeyboardInputEvent keyboardInputEvent) {
        if (this.V()) {
            float f = Globals.getPlayer().input.getMovementInput().y;
            float f2 = Globals.getPlayer().input.getMovementInput().x;
            float f3 = Globals.getPlayer().getYaw();
            Rotation rotation = S;
            Intrinsics.checkNotNull(rotation);
            float f4 = (f3 - rotation.getYaw()) * ((float)Math.PI / 180);
            float f5 = (float)Math.cos(f4);
            float f6 = (float)Math.sin(f4);
            float f7 = MathKt.roundToInt(f * f5 + f2 * f6);
            float f8 = MathKt.roundToInt(f2 * f5 - f * f6);
            Input input = Globals.getPlayer().input;
            Intrinsics.checkNotNull(input, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
            ((ClientInputAccessor)((Object)input)).setMoveVector(new Vec2f(f8, f7));
            Globals.getPlayer().input.playerInput = new PlayerInput(f7 > 0.0f, f7 < 0.0f, f8 > 0.0f, f8 < 0.0f, Globals.getPlayer().input.playerInput.comp_3163(), Globals.getPlayer().input.playerInput.comp_3164(), ManagedRotationHandler.INSTANCE.w() ? false : Globals.getPlayer().input.playerInput.comp_3165());
        }
    }

    @Flow
    private final void onPreInteractItem(PreInteractItemEvent preInteractItemEvent) {
        savedYaw = Globals.getPlayer().getYaw();
        savedPitch = Globals.getPlayer().getPitch();
        Rotation rotation = this.hasActiveRequest(true);
        Globals.getPlayer().setYaw(rotation.getYaw());
        Globals.getPlayer().setPitch(rotation.getPitch());
    }

    @Flow
    private final void onPostInteractItem(PostInteractItemEvent postInteractItemEvent) {
        Globals.getPlayer().setYaw(savedYaw);
        Globals.getPlayer().setPitch(savedPitch);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean V() {
        if (S == null) return false;
        if (!su.catlean.module.basic.MoveFix.INSTANCE.getEnabled()) return false;
        Rotation rotation = S;
        Intrinsics.checkNotNull(rotation);
        if (rotation.getMoveFix() == MoveFix.FORCE) return false;
        if (su.catlean.module.basic.MoveFix.INSTANCE.getMode() == MoveFixMode.FOCUS) return false;
        if (Globals.getPlayer().isRiding()) return false;
        if (Globals.getPlayer().isSwimming()) return false;
        if (FreeCam.INSTANCE.getEnabled()) return false;
        if (FreeLook.INSTANCE.getEnabled()) return false;
        if (ElytraRecast.INSTANCE.getEnabled()) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean C() {
        if (S == null) return false;
        if (!(su.catlean.module.basic.MoveFix.INSTANCE.getEnabled() || su.catlean.module.basic.MoveFix.INSTANCE.getEnabled() && su.catlean.module.basic.MoveFix.INSTANCE.getMode() == MoveFixMode.FOCUS || ElytraTarget.INSTANCE.getEnabled() || MaceElytraTarget.INSTANCE.getEnabled())) {
            Rotation rotation = S;
            Intrinsics.checkNotNull(rotation);
            if (rotation.getMoveFix() != MoveFix.FORCE && !Globals.getPlayer().isRiding() && !Globals.getPlayer().isGliding()) {
                if (!Globals.getPlayer().isSwimming()) return false;
            }
        }
        if (FreeCam.INSTANCE.getEnabled()) return false;
        if (FreeLook.INSTANCE.getEnabled()) return false;
        if (ElytraRecast.INSTANCE.getEnabled()) return false;
        return true;
    }

    @Flow
    private final void onPlayerTravel(EventPlayerTravel eventPlayerTravel) {
        if (this.C()) {
            playerRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
            Rotation rotation = S;
            Globals.getPlayer().setYaw(rotation != null ? rotation.getYaw() : 0.0f);
            Rotation rotation2 = S;
            Globals.getPlayer().setPitch(rotation2 != null ? rotation2.getPitch() : 0.0f);
        }
    }

    @Flow
    private final void onPostPlayerTravel(EventPostPlayerTravel eventPostPlayerTravel) {
        block0: {
            if (!this.C() || playerRotation == null) break block0;
            ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
            Rotation rotation = playerRotation;
            Intrinsics.checkNotNull(rotation);
            clientPlayerEntity.setYaw(rotation.getYaw());
            ClientPlayerEntity clientPlayerEntity2 = Globals.getPlayer();
            Rotation rotation2 = playerRotation;
            Intrinsics.checkNotNull(rotation2);
            clientPlayerEntity2.setPitch(rotation2.getPitch());
        }
    }

    @Flow
    private final void onFireWorkVector(FireWorkVectorEvent fireWorkVectorEvent) {
        block0: {
            if (!su.catlean.module.basic.MoveFix.INSTANCE.getEnabled() && !ElytraTarget.INSTANCE.getEnabled() && !MaceElytraTarget.INSTANCE.getEnabled() || !Globals.getPlayer().isRiding() && !Globals.getPlayer().isGliding() && !Globals.getPlayer().isSwimming() || FreeCam.INSTANCE.getEnabled()) break block0;
            fireWorkVectorEvent.cancel();
            fireWorkVectorEvent.setVector(MovementUtil.INSTANCE.getLookVector(MovementUtil.INSTANCE.getLastPitch(), MovementUtil.INSTANCE.getLastYaw()));
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block0: {
            Rotation rotation = l;
            Rotation rotation2 = m;
            if (rotation == null || rotation2 == null || !Aura.INSTANCE.getEnabled() || !AimSettings.INSTANCE.getClientLook()) break block0;
            Globals.getPlayer().setYaw(RenderUtil.INSTANCE.lerp(rotation2.getYaw(), rotation.getYaw(), RenderState.INSTANCE.getTickDelta()));
            Globals.getPlayer().setPitch(RenderUtil.INSTANCE.lerp(rotation2.getPitch(), rotation.getPitch(), RenderState.INSTANCE.getTickDelta()));
        }
    }

    public final float getServerYaw() {
        Rotation rotation = S;
        return rotation != null ? rotation.getYaw() : 0.0f;
    }

    public final void sendRotation(float f, float f2) {
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        clientPlayerEntity.networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(clientPlayerEntity.getX(), clientPlayerEntity.getY(), clientPlayerEntity.getZ(), f, f2, clientPlayerEntity.isOnGround(), clientPlayerEntity.horizontalCollision));
    }

    @NotNull
    public final Rotation getRotationTo(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "to");
        double d = MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec3d.z - Globals.getPlayer().getZ(), vec3d.x - Globals.getPlayer().getX())) - 90.0 - (double)MovementUtil.INSTANCE.getLastYaw());
        Entity entity = Globals.getPlayer();
        double d2 = -Math.toDegrees(Math.atan2(vec3d.y - (AntiKnockBack.INSTANCE.getPos((Entity)entity).y + (double)Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose())), Math.hypot(vec3d.x - Globals.getPlayer().getX(), vec3d.z - Globals.getPlayer().getZ()))) - (double)MovementUtil.INSTANCE.getLastPitch();
        double d3 = (double)MovementUtil.INSTANCE.getLastYaw() + d;
        double d4 = (double)MovementUtil.INSTANCE.getLastPitch() + d2;
        return new Rotation((float)d3, (float)d4, false, null, 12, null);
    }

    private final Vec3d rotateVector(float f, Vec3d vec3d, float f2) {
        double d = vec3d.lengthSquared();
        if (d < 1.0E-7) {
            Vec3d vec3d2 = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue(vec3d2, "ZERO");
            return vec3d2;
        }
        Vec3d vec3d3 = (d > 1.0 ? vec3d.normalize() : vec3d).multiply(f2);
        Intrinsics.checkNotNullExpressionValue(vec3d3, "scale(...)");
        Vec3d vec3d4 = vec3d3;
        float f3 = (float)Math.sin(f * ((float)Math.PI / 180));
        float f4 = (float)Math.cos(f * ((float)Math.PI / 180));
        return new Vec3d(vec3d4.x * (double)f4 - vec3d4.z * (double)f3, vec3d4.y, vec3d4.z * (double)f4 + vec3d4.x * (double)f3);
    }

    private static final Unit rotate$lambda$0() {
        return Unit.INSTANCE;
    }

    private static final boolean onPlayerUpdate2$lambda$0(RotationRequest rotationRequest) {
        block0: {
            Intrinsics.checkNotNullParameter(rotationRequest, "it");
            INSTANCE.sendRotation(rotationRequest.getRotation().getYaw(), rotationRequest.getRotation().getPitch());
            rotationRequest.getAction().invoke();
            if (!rotationRequest.getRotation().isGrim()) break block0;
            INSTANCE.sendRotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch());
        }
        return true;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        RotationManager.a = ZkmKeys.a(-6489381357325265935L, 4620271491898570590L, MethodHandles.lookup().lookupClass()).a(88327190344314L);
                        var20 = RotationManager.a ^ 102685213202519L;
                        RotationManager.e = new HashMap<K, V>(13);
                        RotationManager.zkmSetFlag(null);
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
                        var18_3 = new String[8];
                        var16_4 = 0;
                        var15_5 = "K\u00ea[\u008e\u00caY\u0094~h\f\u00fc\u0010\u00c4\u0012\u00d3(\u00d7\u0006[lP\u008e\u00e6\u00d0\u00a3\u00e0o\u0005\u00dfF\u00de\u0002\u0010\u0087b\u0015X\u00c0\u0003\u00e3\u0083(\u00c5w@\u00cf\u0015\u00e9\u00af\u0010\u00d6|\u0007-\u00fa6\u0082\u00c7;Mk/\u00b2k\u0004`\u0010\u0083s\u00e7\u0097\u00c4\u00d7\u00a5\u00da\u0089:9<\u00aa\u00cdP\u0082\u0090C\u008c\u0092\u0098\u000e\u001d;/x\u00ca\u0007U\u00d4\u00ae-\u00bc!}\r\u00a4S2\u009c\u00abd.*uy\u00b6\u00a9\u00f8Is{\u0086\u0095\u00c5\u00b2\u00d7\u0015\u00a6\u00db\u00fcf\u00f0\r\u00cb\u00e8\u001c\u0005&\u00a4\u0097,\u00f0\u00fc\u00e8\u008a=\u0091\b\u00e6\u00e0\u00cd\u0085\u00c9\\\u00ccF^\u00faOEa\u00f7=\u00b9\u00f9l\u00ca\u00a6~ha4\u00a1\u00f3Ir\u0098\u0010\u00c3\u0003xB\u00bb2w\u0080\u00f7\u00e67\u00af\u00ae\u009a\u00b3\u00e1hXZj\u00c9\u0015|\u00d8\u001b>\u00fa\u001b\u00df\u008c\u000e\u00ed\u00ac\u00fad\u00d9\u0017J\u00c5M@QG\u00cd]\u00d8u\u00f2::\u0086\u00be k\u0002\u0093\u0094\u000b(V\u00ba\u00bc:.e-\u00b2\u00b9\u00aaX\u00cd\u008d4q\u00dc\u001d\b\u00ee\u008f\u001e\u00e3,\u009aSV";
                        var17_6 = 261;
                        var14_7 = 32;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = RotationManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00de\u00d9\u00e9~\u00b5[\u00c2\u0091\u00f25HA\u0001J\u0011\u0099ns\u00d4\u00a8'%u\u00e2\u0018'\u000f\u0002\u00c7ATu\u009f\u00bf\u00e4\u00cd\u00ae\u0000\u00d3\u00efk\u0013\u00bd|?\u00f1\u00f8|\u009e";
                            var17_6 = 49;
                            var14_7 = 24;
                            var13_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = RotationManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                RotationManager.b = var18_3;
                RotationManager.d = new String[8];
                RotationManager.h = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u0086\u00b9\u00d4\u0090\u00d0\u00b1\u00a2\u00f3\u009e\u0085C%\u00b4\u00e4\u0081\u0016";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u001cUPzb{vN\u00f8u\u00db\u00b9\u00e5\u00d7O\u0014";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl92:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl105:
                // 1 sources

                ** continue;
            }
        }
        RotationManager.f = var6_12;
        RotationManager.g = new Integer[4];
        RotationManager.INSTANCE = new RotationManager();
        RotationManager.pendingRequests = new ArrayList<E>();
        RotationManager.requests = new ArrayList<E>();
        RotationManager.savedRotation = new Rotation(0.0f, 0.0f, false, null, 12, null);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        c = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return c;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1403;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_8", exception);
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
            RotationManager.d[n2] = RotationManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RotationManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4A23;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RotationManager.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RotationManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RotationManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RotationManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

