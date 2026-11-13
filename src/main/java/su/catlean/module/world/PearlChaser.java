/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.world.AfterEntitySpawn;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.SwapMode;
import su.catlean.util.Timer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class PearlChaser
extends Module {
    @NotNull
    public static final PearlChaser INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting onlyTarget$delegate;
    @NotNull
    private static final ModeSetting switch$delegate;
    @NotNull
    private static final Timer cooldown;
    @Nullable
    private static BlockPos targetPos;
    private static int k;
    private static int C;
    @NotNull
    private static final HashMap seenPearls;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;
    private static final long i;

    private PearlChaser() {
        super("pearl-chaser", Categories.getWorld(), null, null);
    }

    private final boolean getOnlyTarget() {
        return (Boolean)onlyTarget$delegate.getValue(this, delegatedProperties[0]);
    }

    private final SwapMode getSwitch() {
        return (SwapMode)((Object)switch$delegate.getValue(this, delegatedProperties[1]));
    }

    @Flow
    private final void onAfterEntitySpawn(AfterEntitySpawn afterEntitySpawn) {
        block0: {
            if (!(afterEntitySpawn.getEntity() instanceof EnderPearlEntity)) break block0;
            Globals.getWorld().getPlayers().stream().min(Comparator.comparingDouble(arg_0 -> PearlChaser.onAfterEntitySpawn$lambda$1(arg_0 -> PearlChaser.onAfterEntitySpawn$lambda$0(afterEntitySpawn, arg_0), arg_0))).ifPresent(arg_0 -> PearlChaser.onAfterEntitySpawn$lambda$3(arg_0 -> PearlChaser.onAfterEntitySpawn$lambda$2(afterEntitySpawn, arg_0), arg_0));
        }
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        float f;
        float f2;
        Object object;
        block22: {
            block21: {
                Object object2;
                targetPos = null;
                if (this.getOnlyTarget()) {
                    Object object3;
                    if (Aura.INSTANCE.getTarget() != null && Aura.INSTANCE.getEnabled() && Aura.INSTANCE.getTarget() instanceof PlayerEntity) {
                        Entity entity = Aura.INSTANCE.getTarget();
                        Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                        object3 = (PlayerEntity)entity;
                        if (!seenPearls.containsKey(object3)) {
                            ((Map)seenPearls).put(object3, Globals.now());
                        }
                    }
                    if (AutoCrystal.INSTANCE.getTarget() != null && AutoCrystal.INSTANCE.getEnabled() && AutoCrystal.INSTANCE.getTarget() != null) {
                        PlayerEntity playerEntity = AutoCrystal.INSTANCE.getTarget();
                        Intrinsics.checkNotNull(playerEntity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                        object3 = playerEntity;
                        if (!seenPearls.containsKey(object3)) {
                            ((Map)seenPearls).put(object3, Globals.now());
                        }
                    }
                    object3 = new HashMap(seenPearls);
                    object2 = object3.entrySet().iterator();
                    while (object2.hasNext()) {
                        object = object2.next();
                        Map.Entry entry = object;
                        long l = Globals.now();
                        Object v = entry.getValue();
                        Intrinsics.checkNotNullExpressionValue(v, "<get-value>(...)");
                        if (l - ((Number)v).longValue() <= i) continue;
                        seenPearls.remove(entry.getKey());
                    }
                }
                if (Globals.getPlayer().getHealth() < 5.0f) {
                    return;
                }
                if (!cooldown.hasPassed(500)) {
                    return;
                }
                for (Object e : Globals.getWorld().getEntities()) {
                    Object object3;
                    Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                    Entity entity = (Entity)e;
                    if (!(entity instanceof EnderPearlEntity) || ((EnderPearlEntity)entity).getId() == k || ((EnderPearlEntity)entity).getId() == C) continue;
                    List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
                    Intrinsics.checkNotNullExpressionValue(list, "players(...)");
                    object = list;
                    Iterator iterator2 = object;
                    Object object5 = new ArrayList();
                    Iterator iterator3 = iterator2.iterator();
                    while (iterator3.hasNext()) {
                        Object t = iterator3.next();
                        AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
                        if (!((seenPearls.containsKey(abstractClientPlayerEntity) || !INSTANCE.getOnlyTarget()) && !Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getPlayer()))) continue;
                        object5.add(t);
                    }
                    object = (List)object5;
                    iterator2 = object.iterator();
                    if (!iterator2.hasNext()) {
                        object3 = null;
                    } else {
                        object5 = iterator2.next();
                        if (!iterator2.hasNext()) {
                            object3 = object5;
                        } else {
                            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)object5;
                            double d = abstractClientPlayerEntity.squaredDistanceTo(INSTANCE.getPos(entity));
                            do {
                                AbstractClientPlayerEntity abstractClientPlayerEntity2;
                                double d2;
                                if (Double.compare(d, d2 = (abstractClientPlayerEntity2 = (AbstractClientPlayerEntity)((Object)(iterator3 = iterator2.next()))).squaredDistanceTo(INSTANCE.getPos(entity))) <= 0) continue;
                                object5 = iterator3;
                                d = d2;
                            } while (iterator2.hasNext());
                            object3 = object5;
                        }
                    }
                    if ((object2 = (AbstractClientPlayerEntity)object3) == null) continue;
                    Vec3d vec3d2 = INSTANCE.getPos(entity);
                    vec3d2 = ((EnderPearlEntity)entity).getVelocity();
                    Intrinsics.checkNotNullExpressionValue(vec3d2, "getDeltaMovement(...)");
                    targetPos = INSTANCE.simulatePearl(vec3d, vec3d2);
                    k = ((EnderPearlEntity)entity).getId();
                }
                if (targetPos == null) {
                    return;
                }
                ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                BlockPos blockPos = targetPos;
                Intrinsics.checkNotNull(blockPos);
                if (clientPlayerEntity.squaredDistanceTo(blockPos.toCenterPos()) < 49.0) {
                    return;
                }
                BlockPos blockPos2 = targetPos;
                Intrinsics.checkNotNull(blockPos2);
                Vec3d vec3d = blockPos2.toCenterPos();
                Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
                f2 = Rotation.Companion.to(vec3d).getYaw();
                BlockPos blockPos3 = targetPos;
                Intrinsics.checkNotNull(blockPos3);
                f = this.findThrowAngle(blockPos3, f2);
                if (Float.isNaN(f)) {
                    return;
                }
                object2 = this.simulateThrow(f2, f);
                if (object2 == null) break block21;
                BlockPos blockPos4 = targetPos;
                Intrinsics.checkNotNull(blockPos4);
                if (!(blockPos4.getSquaredDistance(((BlockPos)object2).toCenterPos()) > 36.0)) break block22;
            }
            return;
        }
        object = new Rotation(f2, Math.clamp(f, -89.0f, 89.0f), false, null, 12, null);
        RotationManager.INSTANCE.submit(new RotationRequest((Rotation)object, 150, () -> PearlChaser.swapAndThrow((Rotation)object)));
        targetPos = null;
        cooldown.reset();
    }

    private final float findThrowAngle(BlockPos blockPos, float f) {
        Object v1;
        Iterable iterable;
        Object object;
        Vec3d vec3d = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        Vec3d vec3d2 = vec3d;
        ArrayList<Pair<Object, Float>> arrayList = new ArrayList<Pair<Object, Float>>();
        int n = (int)f - 10;
        int n2 = (int)f + 10;
        if (n <= n2) {
            while (true) {
                for (int i = 10; -86 < i; --i) {
                    object = this.simulatePearl(vec3d2, this.getDirection(i, n));
                    if (object == null || !(((BlockPos)object).toCenterPos().distanceTo(blockPos.toCenterPos()) < 5.0)) continue;
                    arrayList.add(TuplesKt.to(object, Float.valueOf(i)));
                }
                if (n == n2) break;
                ++n;
            }
        }
        if (!(object = (iterable = (Iterable)arrayList).iterator()).hasNext()) {
            v1 = null;
        } else {
            Object object2 = object.next();
            if (!object.hasNext()) {
                v1 = object2;
            } else {
                Pair pair = (Pair)object2;
                double d = ((BlockPos)pair.getFirst()).toCenterPos().distanceTo(blockPos.toCenterPos());
                do {
                    Object e;
                    Pair pair2;
                    double d2;
                    if (Double.compare(d, d2 = ((BlockPos)(pair2 = (Pair)(e = object.next())).getFirst()).toCenterPos().distanceTo(blockPos.toCenterPos())) <= 0) continue;
                    object2 = e;
                    d = d2;
                } while (object.hasNext());
                v1 = object2;
            }
        }
        Pair pair = v1;
        return pair != null ? ((Number)pair.getSecond()).floatValue() : Float.NaN;
    }

    private final BlockPos simulateThrow(float f, float f2) {
        Vec3d vec3d = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        return this.simulatePearl(vec3d, this.getDirection(f2, f));
    }

    private final BlockPos simulatePearl(Vec3d vec3d, Vec3d vec3d2) {
        Vec3d vec3d3 = vec3d2;
        Vec3d vec3d4 = vec3d;
        Vec3d vec3d5 = vec3d;
        for (int i = 0; i < 201; ++i) {
            BlockHitResult blockHitResult;
            Vec3d vec3d6 = vec3d3.add(0.0, -0.03, 0.0);
            Intrinsics.checkNotNullExpressionValue(vec3d6, "add(...)");
            vec3d3 = vec3d6;
            vec3d3 = this.applyDrag(vec3d3, vec3d4);
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().raycast(new RaycastContext(vec3d5, vec3d4, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, Globals.getPlayer())), "clip(...)");
            if (blockHitResult.getType() != HitResult.Type.MISS) {
                return blockHitResult.getBlockPos();
            }
            Iterator<Entity> iterator2 = Globals.getWorld().getEntities().iterator();
            while (iterator2.hasNext()) {
                Optional<Vec3d> optional;
                Vec3d vec3d7;
                Entity entity;
                Intrinsics.checkNotNullExpressionValue(iterator2.next(), "next(...)");
                if (!(entity.squaredDistanceTo(vec3d4) < 64.0) || Intrinsics.areEqual(entity, Globals.getPlayer())) continue;
                Vec3d vec3d8 = vec3d4;
                Intrinsics.checkNotNullExpressionValue(vec3d4.add(vec3d3), "add(...)");
                Intrinsics.checkNotNullExpressionValue(entity.getBoundingBox().raycast(vec3d8, vec3d7), "clip(...)");
                if (optional.isEmpty()) continue;
                return BlockPos.ofFloored(optional.get());
            }
            vec3d5 = vec3d4;
            Intrinsics.checkNotNullExpressionValue(vec3d4.add(vec3d3), "add(...)");
        }
        return null;
    }

    private final Vec3d applyDrag(Vec3d vec3d, Vec3d vec3d2) {
        Vec3d vec3d3 = vec3d.multiply(Globals.getWorld().getBlockState(BlockPos.ofFloored(vec3d2)).getBlock() instanceof FluidBlock ? 0.8 : 0.99);
        Intrinsics.checkNotNullExpressionValue(vec3d3, "scale(...)");
        return vec3d3;
    }

    private final Vec3d getDirection(float f, float f2) {
        double d = Math.PI / 180;
        double d2 = -Math.sin((double)f2 * d) * Math.cos((double)f * d);
        double d3 = -Math.sin((double)f * d);
        double d4 = Math.cos((double)f2 * d) * Math.cos((double)f * d);
        Vec3d vec3d = new Vec3d(d2, d3, d4).multiply(1.5).add(Globals.getPlayer().getMovement().x, Globals.getPlayer().isOnGround() ? 0.0 : Globals.getPlayer().getMovement().y, Globals.getPlayer().getMovement().z);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        return vec3d;
    }

    private static final double onAfterEntitySpawn$lambda$0(AfterEntitySpawn afterEntitySpawn, AbstractClientPlayerEntity abstractClientPlayerEntity) {
        Entity entity = afterEntitySpawn.getEntity();
        return abstractClientPlayerEntity.squaredDistanceTo(INSTANCE.getPos(entity));
    }

    private static final double onAfterEntitySpawn$lambda$1(Function1 function1, Object object) {
        return ((Number)function1.invoke(object)).doubleValue();
    }

    private static final Unit onAfterEntitySpawn$lambda$2(AfterEntitySpawn afterEntitySpawn, AbstractClientPlayerEntity abstractClientPlayerEntity) {
        Intrinsics.checkNotNullParameter(abstractClientPlayerEntity, "it");
        if (Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getPlayer())) {
            C = afterEntitySpawn.getEntity().getId();
        }
        return Unit.INSTANCE;
    }

    private static final void onAfterEntitySpawn$lambda$3(Function1 function1, Object object) {
        function1.invoke(object);
    }

    private static final Unit swapAndThrow$lambda$0(Rotation rotation) {
        InteractionUtil.useItem(Hand.MAIN_HAND, rotation.getYaw(), rotation.getPitch());
        PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        return Unit.INSTANCE;
    }

    private static final Unit swapAndThrow(Rotation rotation) {
        long l = a ^ 0xFD41CF31D2AL;
        long l2 = l ^ 0x7E3C3A3236AEL;
        Item item = Items.ENDER_PEARL;
        Intrinsics.checkNotNullExpressionValue(item, "ENDER_PEARL");
        InventoryUtil.INSTANCE.swapToItemAndRun(item, INSTANCE.getSwitch(), l2, () -> PearlChaser.swapAndThrow$lambda$0(rotation));
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            PearlChaser.a = ZkmKeys.a(-4762326402042322715L, 2126441121924025221L, MethodHandles.lookup().lookupClass()).a(111996500078028L);
                            var25 = PearlChaser.a ^ 134799434327638L;
                            PearlChaser.e = new HashMap<K, V>(13);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[24];
                            var21_4 = 0;
                            var20_5 = "\u00e7\u00c6S\u00df\u00b0R\u00bd\u00dc\u00b1\u009a\"\u00c4l\u008f\u0019\u00b4\u008cst%\u00aa\u0091\u00ff\u00cf\u00fb\u00b4\u00b3#\u00c9\u00e6\n%\u00a1\u00db\u009a\u000f\u008b\u0003\u009ai\u0018\u00f4\u00a6\u0082\u00df\u00fa\u00e7\u00faO\u00f2\u001f\u00df\u00a8\u00dd\u00d7\u00cdV#\u00c6jlt\u0012\u00f4e \u00dc_\u00e9cg\u00bf0\u00c3\u0099\"\u00ff\u00e2\u0088R\u0084d\u00cf\u0017'\u0015\u001f\u00f1}\u00ecA\u00ee\u008a\u00aa\u00e7\u00f4\u00d9z \u00db\u00aa\u00e8\u00d6V\u001c\u0092\u0011Y\u00b0a\u00f2\u0087\u00a7\u00f9b\u00d6\r=\u00b5\u008b\u000e\u00ee,\f\u0091<\u0095\u00a7W\u00f5\u00e5 G%\u0081\u00ca\u009c\u00ac\u0013\u008dQ}\u0091\u00b5\u008d\u00e0\u00f9\u008e\u0083\u00ea\u00dd\f+\u0010\u00a9\u0016]\u00fb\u001d\u00e9\u00b4\u0018G\u00de\u0018\u00cbiI\u0088\u0011\u00de\u0015\u00a8\u00e8\u0080+\u00d7\u00fdw\u00fd\u00fc\u0096\u00a1$\u0080\u00e6\u0012\u0013\u0016\u0010\u00f5QH]\u00a3q\u00c0\u00b4\u0015\u00bde\u001d\u0002\u00f0C\u00f0 \u00ben\u0093R\u0087)\u0095\u00e6\u00e5\u00e9Y\u0080\u00be\u00a2b\u00e4\u00c8\u0007B\u0085\u00a0\u0088[\u00eao\u0006D\u00f3u\u001c\u00ef\u008d\u0010\u00e6\u00fcwMV\u00efu\u0083\u0005M\u00ea\u00b3\u00d6\u00f1\u00cd\u0003\u0018se]\u00a0\u00c7\u00e3\u00c6Y\t\u00ed1\u0017\u008ev\u00ce\u00e9\u00f9az\u00f9\b\u00e8\u00c3^\u0018\fM\u00a1k\u00c2\u00ff]\u00ab\u00c9\u001c\u0080\u0006\u00c9K\u0082\u00a2\u00b4\u0017\u00cd\u00ddn\u00c3\u0013K(\u00ba\u00f8n7#;\u001a\u008d+\u00f5\u00ac\u00e0\u0004\u0099\u00a5\u0085\u00a5k\u00ddJ\u00c9\u00b2\u00aaT.\u0095\u009b\u00b9)\u0091\",+\u0097\u00a1\u0081\u00b7\u0017c\u00b8 \u00e2w\u0081\u00f2y[\u00fb\u00cc\u008dK\u0089\u00f3\u00f5-\u0002$\u00932\u0013\u00be\u00c8*}\u0087\u001cR\u008b\u00da>\u00e7\u0013\u0081x'F\u00aegk\u00cc\u00cd3\u001c\u00ec\u00f4\u008f\u00e4\u0081\u0004\u00b4\u00d8\u00d5Wz\u00fcU\u001d\u000b\u00fe\u009d16\u00f5`\u00da\u0088\u00e1\u0011wD\u00c0$\u00146\u008b\u00bf6\u000b=8\u00ba\u00a2\u00cc\u00f08\u009a\u000e\u00d2\u00f6\u0086=a\u00e7S\u00bfFe3\u00e9R.\u008eP|u+\u00e6\u0094\u00ad&\u00e5\u00e5\u00d1\u00ec\u009e\u00b2s/\u00cc\u0096\u00f7\u00bai\u00ea\u00c8\u00a9\u001a/g,{\u00f81\u00f5n\u00d6\u00b4\u00e8\u0083 F4T\u00f0\u00b0\u0093\u00d8\u000f\u00fa\u0083\u00ff\u00fcE)(\u0001\u000b\u0082\u0016a\u00ea\u00dcm\u00b8\\\u00c9\u00e1!\u00b9/Xagp\u00be\u0010\u00edD\u009c[\u00bf\u001f\u00ad\u00b9>\u001b\u00df\u009fR\u00b6\u0091\u00b4\u0094\u00b7\u001c8\u008e\u00a8h\u00c4\u00cd\u009c35\u0092\u00e2\u008a,\u00bd\u0085\u00a2M\u0090<\u00fb\u00e7B~2\u00b5>lw\b_n\u0019\u008d\u00bap\\h\u00b9c\u00f4;\u00cak\fH5\u0003j1\\\u00c1t\u00bd\u00aa\u00a5%\u00e7(\"\bJ\u00e6(7\u00f9\u008a\u0080\u009a<\u00b2\u009e\u00b3z\u0018(\u0089\u00ed\u00b7\u00fb\u00c4@\u008f}:\u00a8\u0000V\u00d1kl\u00b3k\u00fa\u0002\u0097\u00e4R\u00e4\u0080\u001a\u00ef+\t\u0080P\u008c\u001a\u00ca\u0003O*aV\u00d3\u00fa\u0088\u00cc\u0095^\u00eaq\u00ee\t\u00141x\u00ef\u0081Y\u0099\u00d7\u00c4\u00c6\u000e\u00ad\u0019\u009b\"\u00a5\u0019\u0019\u00dc\u00c9\u00d1\u00e3\u00dah\u00cd\u009c\u00c6F\u008c\u00a5r\u00d4\u0097\u00f1\u00b0\u0084\u00f0\u001a\u0089\u001dGS\u0081r\u00f7Ah\u00ab[\u00ef\u00d1\u00df\u0017\u00c8\u00e2\u00a0>\u0000S\u00a0\u00d3\u00a3\u009d\u00e6X:K\u00d5\u0086\u009e\u0084Pq\u00b64\u00ce~\u00f1U\u009ci_\u00ba\u0007\u0012\u0088\u0080\u00db\u009b\u00b0Q\u00c8\u0005\u0012N?\u00cf\u00ea\u00f8\u0082{\u00e5\u00ec\r \u00bd\\\u00a6)-r\u00d8\u00a3\u009c;s\u00f6\u00f4\u00e5\u00ff\u00f7y\u00032X\u0002\u0016\u00e6\r\u00ca\u0015oe\u0001|\u0099\u00e0\u0018\u00ac\u0007\u0095\u0011X\u00e4.t\u00cb#\u0014\r\u0081ea\u0012\u00dc]\u008aTx%\u0097\u0097 \b\u00bf\u00bf\u00055{\u00c3L\u00a1I58\u00c0T,\u00b6\u001a\u00a0/c\u000e\u0087\u009aS\t3\u00fdj\u00ea,\\m \u00ady\u001e\u00b06\u008bO\u00ba\u00c5J\u00ac\u00b3\u00d2`\u00e3\f#\u008et\u00f0ag\u001d\u00b2\u00c4\u0086l\u00e3c\u009amD";
                            var22_6 = 893;
                            var19_7 = 40;
                            var18_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = PearlChaser.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "4$uX\u00d8\u00ee\u0007\u008f<U%\u0010r\u00fa-\u00ff(\u00f1\u001a|]!*\u00cc\u00c4\u00b0\u00de\bO\u00c4\u001dK\"!D=`2q\u00c6\u001a\u00eex}\u00d5\u00d2\u009dtP\u007f\u00a6\u00f3\u00fe\u00f2\u00c6\t\u00ea";
                                var22_6 = 57;
                                var19_7 = 16;
                                var18_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = PearlChaser.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    PearlChaser.b = var23_3;
                    PearlChaser.c = new String[24];
                    PearlChaser.h = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[8];
                    var8_13 = 0;
                    var9_14 = "\u00a7h\u00ff2.6r^)\u00e8;kfJ\u0089/UG\u008dgw\u00bd\u008c/\u0000\u00d3g\u00d9Fq\u009a\u00967\u0080\u008a\u00a2-\u00af\u008c\u00e1+\u00beP\u0011\u00e5\u00a2K\u00aa";
                    var10_15 = 48;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u00e4#=}\u00a8\u00cb\u0087\u00f7B\u00cf}\u00af\u009a3\u0000\u000e";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl91:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                switch (v13) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl104:
                    // 1 sources

                    ** continue;
                }
            }
            PearlChaser.f = var11_12;
            PearlChaser.g = new Integer[8];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl118:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-55, 29, 15, -122, -30, 77, -112, -81});
        ** while (true)
        PearlChaser.i = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
        var33_23 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(PearlChaser.class, "onlyTarget", "getOnlyTarget()Z", 0)), Reflection.property1(new PropertyReference1Impl(PearlChaser.class, "switch", "getSwitch()Lsu/catlean/f9;", 0))};
        PearlChaser.delegatedProperties = var33_23;
        PearlChaser.INSTANCE = new PearlChaser();
        PearlChaser.onlyTarget$delegate = SettingFactory.booleanSetting$default((SettingContainer)PearlChaser.INSTANCE, "only-target", false, null, null, 12, null);
        PearlChaser.switch$delegate = SettingFactory.modeSetting$default((SettingContainer)PearlChaser.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, 12, null);
        PearlChaser.cooldown = new Timer();
        PearlChaser.seenPearls = new HashMap<K, V>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3093;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/ez", exception);
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
            PearlChaser.c[n2] = PearlChaser.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PearlChaser.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ez" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1456;
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
                throw new RuntimeException("su/catlean/ez", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PearlChaser.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PearlChaser.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ez" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PearlChaser.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PearlChaser.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

