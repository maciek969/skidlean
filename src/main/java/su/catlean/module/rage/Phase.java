/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ScaffoldingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.CollisionEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.PhaseMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceMode;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.Timer;
import su.catlean.util.combat.DamageUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class Phase
extends Module {
    @NotNull
    public static final Phase INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final BooleanSetting onlyOnGround$delegate;
    @NotNull
    private static final BooleanSetting autoDisable$delegate;
    @NotNull
    private static final BooleanSetting antiSwim$delegate;
    @NotNull
    private static final BooleanSetting fill$delegate;
    @NotNull
    private static final BooleanSetting grim$delegate;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final Timer timer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map j;

    private Phase() {
        super("phase", Categories.getRage(), null, null);
    }

    private final PhaseMode getMode() {
        return (PhaseMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final boolean getOnlyOnGround() {
        return (Boolean)onlyOnGround$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getAutoDisable() {
        return (Boolean)autoDisable$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getAntiSwim() {
        return (Boolean)antiSwim$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getFill() {
        return (Boolean)fill$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getGrim() {
        return (Boolean)grim$delegate.getValue(this, delegatedProperties[5]);
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[6]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        switchMode$delegate.setValue(this, delegatedProperties[Phase.zkmBootstrap2("u", (int)22327, (long)(0x4F970DCA3B3718FDL ^ l))], (Object)swapMode);
    }

    @Flow
    private final void onCollision(CollisionEvent collisionEvent) {
        Entity entity = Globals.getPlayer();
        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        Object var14_4 = null;
        if (this.getMode() == PhaseMode.VANILLA && (!Intrinsics.areEqual(collisionEvent.getPos(), blockPos2.down()) || Globals.getMc().options.sneakKey.isPressed())) {
            BlockState blockState = Blocks.AIR.getDefaultState();
            Intrinsics.checkNotNullExpressionValue(blockState, "defaultBlockState(...)");
            collisionEvent.setState(blockState);
        }
    }

    @Override
    public void onEnable(long l) {
        if (Globals.getMc().player != null && Globals.getPlayer().isOnGround() && this.getMode() == PhaseMode.STEP) {
            double[] dArray = MovementUtil.INSTANCE.getMotionForSpeed(0.44);
            float f = Globals.getPlayer().getYaw() % 90.0f;
            boolean bl = 35.0f <= f ? f <= 55.0f : false;
            PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_SPRINTING));
            if (bl) {
                double[] dArray2 = MovementUtil.INSTANCE.getMotionForSpeed(0.51);
                INSTANCE.canPhase(dArray, dArray2);
                INSTANCE.phaseStep(dArray, dArray2);
            } else {
                double[] dArray3 = MovementUtil.INSTANCE.getMotionForSpeed(0.57);
                INSTANCE.canPhase(dArray, dArray3);
                Globals.getPlayer().setPosition(Globals.getPlayer().getX() + dArray3[0], Globals.getPlayer().getY(), Globals.getPlayer().getZ() + dArray3[1]);
                PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ(), true, Globals.getPlayer().horizontalCollision));
                INSTANCE.phaseStep(dArray, dArray3);
            }
        }
    }

    private final void canPhase(double[] dArray, double[] dArray2) {
        Vec3d vec3d = Globals.getPlayer().getEyePos();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        Vec3d vec3d2 = Globals.getPlayer().getEyePos().add(dArray[0], 0.0, dArray[1]);
        Intrinsics.checkNotNullExpressionValue(vec3d2, "add(...)");
        BlockHitResult blockHitResult = DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, vec3d, vec3d2, false, null, false, 28, null);
        int n = (blockHitResult != null ? blockHitResult.equals((Object)HitResult.Type.MISS) : false) ? 1 : 2;
        Globals.getPlayer().setPosition(Globals.getPlayer().getX() + dArray2[0], Globals.getPlayer().getY() + (double)n, Globals.getPlayer().getZ() + dArray2[1]);
        PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ(), true, Globals.getPlayer().horizontalCollision));
    }

    private final void phaseStep(double[] dArray, double[] dArray2) {
        Entity entity = Globals.getPlayer();
        int n = Globals.getWorld().isAir(BlockPos.ofFloored(this.getPos(entity).add(dArray[0], -2.0, dArray[1]))) ? 2 : 1;
        Globals.getPlayer().setPosition(Globals.getPlayer().getX() + dArray2[0], Globals.getPlayer().getY() - (double)n, Globals.getPlayer().getZ() + dArray2[1]);
        PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ(), true, Globals.getPlayer().horizontalCollision));
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block5: {
            boolean bl;
            long l = a ^ 0x470F63167AF7L;
            long l2 = l ^ 0x27B5814C4241L;
            if (this.getMode() != PhaseMode.PEARL) {
                return;
            }
            boolean bl2 = this.getAntiSwim() && Globals.getPlayer().getPose() == EntityPose.SWIMMING;
            boolean bl3 = bl = timer.hasPassed(500) && !Globals.getPlayer().getItemCooldownManager().isCoolingDown(Items.ENDER_PEARL.getDefaultStack()) && Globals.getPlayer().age > 60;
            if (Globals.getPlayer().isOnGround() && bl2) {
                Globals.getPlayer().jump();
            }
            if (!Globals.getPlayer().horizontalCollision || this.findPhasePos() || !bl || !(MovementUtil.INSTANCE.getFallDistance() > 0.0) && bl2 || !Globals.getPlayer().isOnGround() && this.getOnlyOnGround() && !bl2) break block5;
            Entity entity = Globals.getPlayer();
            if (Globals.getWorld().getBlockState(BlockPos.ofFloored(this.getPos(entity))).getBlock() instanceof ScaffoldingBlock) {
                Entity entity2 = Globals.getPlayer();
                Globals.getInteractionManager().attackBlock(BlockPos.ofFloored(this.getPos(entity2)), Direction.UP);
            }
            double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, 0.5f, true, 0.0f, 0.0f, 0.0f, 28, null);
            BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getX() + dArray[0], Globals.getPlayer().getY(), Globals.getPlayer().getZ() + dArray[1]);
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            BlockPos blockPos2 = blockPos;
            if (Globals.getMc().options.sneakKey.isPressed()) {
                return;
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Vec3d vec3d = blockPos2.toCenterPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getCenter(...)");
            objectRef.element = Rotation.Companion.to(vec3d);
            objectRef.element = new Rotation(((Rotation)objectRef.element).getYaw(), this.getPhasePitch(), false, null, 12, null);
            SlotResult slotResult = InventoryUtil.INSTANCE.findItem(l2, Phase::onPlayerUpdate$lambda$0, this.getSwitchMode());
            if (slotResult.isFound()) {
                RotationManager.INSTANCE.submit(new RotationRequest((Rotation)objectRef.element, 201, () -> Phase.onPlayerUpdate$lambda$1(blockPos2, objectRef, slotResult)));
            }
        }
    }

    private final void placeObsidian(BlockPos blockPos, long l) {
        block2: {
            long l2 = l = a ^ l;
            long l3 = l2 ^ 0xE5AE3D056F9L;
            long l4 = l2 ^ 0xBE1D46B6E5DL;
            BlockState blockState = Globals.getWorld().getBlockState(blockPos);
            Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
            BlockState blockState2 = blockState;
            if (!blockState2.isReplaceable()) break block2;
            SlotResult slotResult = InventoryUtil.INSTANCE.findItem(l4, Phase::isObsidian, this.getSwitchMode());
            if (slotResult.isFound()) {
                Object var18_7 = null;
                List list = null;
                float f = (float)Globals.getPlayer().getBlockInteractionRange();
                float f2 = (float)Globals.getPlayer().getBlockInteractionRange();
                EntityCheck entityCheck = EntityCheck.ALL;
                PlaceMode placeMode = this.getGrim() ? PlaceMode.GRIM : PlaceMode.Strict;
                BlockHitResult blockHitResult = PlaceManager.calcPlaceData$default(PlaceManager.INSTANCE, blockPos, -1, l3, placeMode, entityCheck, f2, f, list, var18_7);
                if (blockHitResult != null) {
                    Function0<Unit> function0 = () -> Phase.placeBlock(blockHitResult);
                    SwapMode swapMode = this.getSwitchMode();
                    int n = slotResult.getSlot();
                    InventoryUtil.INSTANCE.swapAndRun(n, swapMode, function0);
                }
            }
        }
    }

    private final float getPhasePitch() {
        if (Math.abs(Globals.getPlayer().getY()) - Math.floor(Globals.getPlayer().getY()) > 0.1) {
            return 81.0f;
        }
        Entity entity = Globals.getPlayer();
        if (Intrinsics.areEqual(Globals.getWorld().getBlockState(BlockPos.ofFloored(this.getPos(entity))), Blocks.LADDER)) {
            return 80.0f;
        }
        return 85.0f;
    }

    private final boolean findPhasePos() {
        BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getX(), MathKt.roundToInt(Globals.getPlayer().getY()), Globals.getPlayer().getZ());
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        BlockState blockState = Globals.getWorld().getBlockState(blockPos2);
        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
        BlockState blockState2 = blockState;
        Object var5_3 = null;
        BlockState blockState3 = Globals.getWorld().getBlockState(blockPos2);
        Intrinsics.checkNotNullExpressionValue(blockState3, "getBlockState(...)");
        BlockState blockState4 = blockState3;
        boolean bl = !Intrinsics.areEqual(blockState2.getCollisionShape(Globals.getWorld(), blockPos2), VoxelShapes.empty());
        boolean bl2 = !Intrinsics.areEqual(blockState4.getCollisionShape(Globals.getWorld(), blockPos2.up()), VoxelShapes.empty());
        return bl && !bl2 && !Intrinsics.areEqual(blockState2.getBlock(), Blocks.LADDER);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == PhaseMode.PEARL;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == PhaseMode.PEARL;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == PhaseMode.PEARL;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getMode() == PhaseMode.PEARL;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getMode() == PhaseMode.PEARL && INSTANCE.getFill();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getMode() == PhaseMode.PEARL;
    }

    private static final boolean onPlayerUpdate$lambda$0(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.ENDER_PEARL);
    }

    private static final Unit onPlayerUpdate$lambda$1$lambda$0() {
        InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        return Unit.INSTANCE;
    }

    private static final Unit onPlayerUpdate$lambda$1(BlockPos blockPos, Ref.ObjectRef objectRef, SlotResult slotResult) {
        long l;
        long l2 = l = a ^ 0x2761B13D5C49L;
        long l3 = l2 ^ 0x3EA7DB8DE8EL;
        long l4 = l2 ^ 0x7B8D63AB68FBL;
        if (INSTANCE.getFill()) {
            BlockPos blockPos2 = blockPos.down();
            Intrinsics.checkNotNullExpressionValue(blockPos2, "below(...)");
            INSTANCE.placeObsidian(blockPos2, l3);
            INSTANCE.placeObsidian(blockPos, l3);
        }
        Entity entity = MovementUtil.INSTANCE.raycastEntity(((Rotation)objectRef.element).getYaw(), ((Rotation)objectRef.element).getPitch(), 3.0f, false, false);
        if (entity != null) {
            Entity entity2 = entity;
            if (entity2 instanceof ItemFrameEntity && !((ItemFrameEntity)entity2).getHeldItemStack().isEmpty()) {
                Globals.getInteractionManager().attackEntity(Globals.getPlayer(), entity2);
                PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
            }
            if (entity2 instanceof ItemFrameEntity || entity2 instanceof EndCrystalEntity) {
                Globals.getInteractionManager().attackEntity(Globals.getPlayer(), entity2);
                PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
            }
        }
        Function0<Unit> function0 = Phase::onPlayerUpdate$lambda$1$lambda$0;
        SwapMode swapMode = INSTANCE.getSwitchMode();
        int n = slotResult.getSlot();
        InventoryUtil.INSTANCE.swapAndRun(n, swapMode, function0);
        if (INSTANCE.getAutoDisable()) {
            INSTANCE.disable(l4);
        }
        timer.reset();
        return Unit.INSTANCE;
    }

    private static final boolean isObsidian(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.OBSIDIAN);
    }

    private static final Unit placeBlock(BlockHitResult blockHitResult) {
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, blockHitResult);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Phase.a = ZkmKeys.a(-6936183447961913769L, 7191527392490522757L, MethodHandles.lookup().lookupClass()).a(14901899939729L);
                        var20 = Phase.a ^ 132679081937428L;
                        Phase.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[33];
                        var16_4 = 0;
                        var15_5 = ")\u00ccb\u0087\u00db\u0086\u00a7rx\u00ed[\u00d1d\u0082\u00d2\ba8\u0013\u00d3Fa:+\u00d5B*qO\u0006\u00f0q(\u00c5\u00acT\u00b0\u0003T\u00f7\u008d\u00db\u00af\u00f0\u0001&\u00d2\u00fa\u00e4\u00cc\u00f5\u00ad\u0004!\u00b5\u00dc\u0005\u00c3N\u0091]\u00a5\u0096\u000b\u00b0?\u00c2\u00c7\u00e2P\\\u0096\u00d9\u0010\u00bc\u00e3*\u00f1\u00e4\t\u00cc\u00c0\u00a7\u008eN\n(m\u001dt\u0010\u0019\u00df\u0016Qz)\u009b?\u00c3\u00c9\u0094vZ\u00a1\u00d6U\u0018\u00da\u00f8\u00a0e)\u0014-f\u0019\u0084\u0082\u0007\u00c2U\u00eeV/\u0088\u0004tM3\u00a7\u00da \u0088\u00ce\u00ef\u00f29\u0007\u0096Hs/\t\u00c0Q'\u00c1\u009f\u00f3\u00ab\u0083\u00a6H)bG\u00f4\u0084g\u0091\u00dfH\u000e\u00ca I\u0007$\u00c7\u0096\bx=s\u001d\u000e\u00dd\u000b\u009a\u0081'\u00e5\u00cao\u0010i\u00f8Ui\u00a9\u001d\u00af9\u00900\u00df\u00ab\u0018~\u001f\u00994t\u001c\u00cf<\u00d2\u00ed\u00ea\u00c4c\u00cc\u00de\u00aaM\u000bO\u0083\u00f9\u00fe\u00df\u009f T8\u009a\u0000\u00c0\u00e6ojV\u00bc\u0082\u00b2\u00ea\u00e8\u00e9\u00c5Y\u00adXs\u00e5r\u00f5\bE\u0011\u00fc\u00b6*\u00a0.I(+\u00e9\u00df\u0012,\u00aa1\u009ca6\u00a0aB{, S\u00cc\u00e3R\u000f\u00a2\u000e\u00ebQs\u00fa\u00b2\u00b2\u008c\u009aB7V\u00b5\n\u00daO\u0012\u008a ?\u0084\u0011\u00d1\u00b3\u00adm\u0001\u001eU\u0091!\u00c2&+^\u0005\u00c2\u00f4\u00ab\u00b8\u00e1\u0099\u00b3[\u0081\u0084X\u00bf\u00f7:\u00cd(6\u00bd\u001c\u00d94\u0081\u00bdx\u00f9\u000f\u00e8\u00a9i\u00ed\u000e\u008c\u0095\u0016\u00a3\u00c5\u0099}\u00ecBHW\u00ed\u00996b\u00d8H\u0004\u0082\u00cfM@\u00e2\u0018\u00e0 \u009b@\n\u00ce,\u001e\u00a6\u00e2\u00d6\u0098\u00fd\u00cc\u00fb\u00d1\u00ee\u00b0_\u00fd#\u007f\u0087\u0098\u00fa\u009eW\u00ea\u0098h\u00d3\u00d4\u00e4\u00ff\u0010\u0006\u00b6\u00df\u00eeb\u00a1\u00f14\u00d0\u00fc2\u00c4\u00d3)\u00d3\u00998\u00fe:y\u00ab\u00b0M\u00f2\u00ab\u0019\u00f0\u00fdxJ\u00a1\u00f3u3\u00fd\u008a\u00ad\u00cfP@\u00e9\u0004F=h\u0017\u0019\u00d9`\u00e7\u0083kO\u0094\u00c7\u0001w\u007f\u0092\u00a82\u00f4\u00c8\u00bc\u00050\u00d3\u00ca\u0019\u0003\u00b4\u00f7\u0088 X\u00e6\u00d8&z\u00ffh)<\u0081\u00c8\u00973:\u00c1\u00bf\u001at\u00dc\u0014\u008f\u0092\u00f6\u00cf[\u00cd\u0087;\u0095n\u00ed)\u0010\u00af\nfo\u009b,[\u00f4\u0080\u0006\u000e\u00f1\u0086\u009b2/8\r\u00f8I/\u00b2?\u00d8\u00e6\u00c2\u00d6~\u001b\u00ab\u00ba\u00b0Iz\u001c3\u00c0\u00e3\u0091\u0097\u00b7`\u00db,\u007f\u00e5\u0002\u00d9\u0088-\u0007\u0082\u00fa(\\\u00e3ge[\u00b8\u00b4+-\u008a\u008b\u00ce\u00fa\u00f6\u00ba\u00f7\u00c3\u001c\u008c 73T\u00c7\u00d7c\f\u0094.\u0095\u00bc\u00a0\u00d5a2J7\u0013\u0099\u00e4~\u0084I\u00a4\u009e\u0090<\u0087.V\u0018\u00a0\u0010\u00178\u00a4\u00d9\u00aaw\u00e2&\u00b7\u0090#^U\u00ea\u00a7\u00bb <{\u00eev8\u00d4>\u00e2\u00c0\u00c8_\u00a8M\u00fcMn\u0012\u00cf\u00e0\u0001!\u00f6\b\u001c\u00d7\u00deD\u00cb\u008dzD\u00de\u0010\u0099\u00f3\u0092M\u00cd\u007f\u00f6\u00d34o^\u0004S\b\u00c5\u00d6(|\u009f\u00ffT\u00dd\u008a\u00bc\u00a5\u008ch\u0092\u0003LPoDs\u00fap\u0088-\u00bc[\u00ef\u00fdq\u00b6\u0086\u00d9\u00beGOc\u001a\u0001y$\u0002&>0\u00e1\u00c3\u0091\u00c0B\u00aa\u00ebR|\u0005\u00d5\u00cez\u00ac\u0083`ID\"\u008dL\u0006\u00cc\u0014\u00cb.\u00e5\u00c4\u0019\u00f8C\u00a0\u00f9\u0002\u001b3\u00a6\u00b6\u009c\u00bd\u00bc{;\u00f1\u009c\u009aG\u000f\u0018\u00a6\u00cf\u008e\n\u00e9VO\u0000\u00fc>CU\u0082\u00001T\u00fdi}+_\u00c5\u00c6=\u0010\u00b9i\u000f\u00a2\u00a6\u0088\u0012\u0096>\u007f\u00b3\u00f6\u00b0\u00e1\u00b6\u00e8\u0018'\u00a2$>\u00f8\u00a7\u0002\u00a8\u008et]\u0084nO\u0004T\u007f\u00a1Cx\u0003J\u0013\u00f8\u0010&\u0000\u00b1v\u00fd\u00d1\u0091\u00eaa\u0013\u00f49*\u008b\u008e\u000b\u0010\u0019|\u00a9Bk\u00f5\u00a1l\u00f4\u0001\u0080\u0098\u00dd\u00c5\u00e3\u00e6(`\u00ac\u00e8\u00ba\u0013u\u00d6i\u008f\u00e7\u00cd\u00e8\u001c\u00c3\u00c8\u00c8\u00f3\u001eZ\u00ae\u00c2#\u00a3\u0097\u0002\u000e{\u00e8\u00bf\u00ec\u00cb[\u00ba\u00edz\u00c1\u00faR\u008d\u0085\u0018x\u009d\u00d25\u0012\u00b8\u00da\r)j\u0090Zt\u00f8\u00c11\u00e9\u0091\u00f8n\u00f5\u0089\u00f69";
                        var17_6 = 942;
                        var14_7 = 32;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Phase.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00054\u0011\u0013\u00fcKJ\u00b3\u00c4\u00d3\u00e6\u00ef\u0096D\u0095Gz\u0018O\u008a\u00b7\u0018\u00b0\u00d9\u00eb*s6K\u0012H0\u0010\u0099Xz\u00d3t\u009c\u00b8\u008f\u0089O\u0013\u00d2\u00b7\u0019XK";
                            var17_6 = 49;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
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
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Phase.zkmDecodeUtf8(var19_9).intern();
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
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                Phase.b = var18_3;
                Phase.c = new String[33];
                Phase.j = new HashMap<K, V>(13);
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
                var6_12 = new long[12];
                var3_13 = 0;
                var4_14 = "\u009b\u0013\u000b\u00c4\u0001\u00c8\fE\u008cD\u008b\u00dc\u00b4k\u008c\u0007\u008b\u00ef\u00bbk8\u00b1\u0016\u00c2P*\u000e\u008e\u00a3\u00bc\u00b8\u00c1k_\u00fb\u0019\u00e7\u0013\t\u001e\"\u0011\u00a4\u00d0\u00d6\u00a0J,^\u007f\u00f9y\u00f9~\u00e7=\u009c\u00c3^61>\u00bd\u00abB,N\u00bf\u009b\u0000\u0015\u001f\u00adc\"c\u00d9\u00cb2\u00f2";
                var5_15 = 80;
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "B>bq~\b25\u00fe\u00e5S\u00ce\u0005\u009c\u00f8,";
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        Phase.g = var6_12;
        Phase.h = new Integer[12];
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Phase.class, "mode", "getMode()Lsu/catlean/wm;", 0)), Reflection.property1(new PropertyReference1Impl(Phase.class, "onlyOnGround", "getOnlyOnGround()Z", 0)), Reflection.property1(new PropertyReference1Impl(Phase.class, "autoDisable", "getAutoDisable()Z", 0)), Reflection.property1(new PropertyReference1Impl(Phase.class, "antiSwim", "getAntiSwim()Z", 0)), Reflection.property1(new PropertyReference1Impl(Phase.class, "fill", "getFill()Z", 0)), Reflection.property1(new PropertyReference1Impl(Phase.class, "grim", "getGrim()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Phase.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0))};
        Phase.delegatedProperties = var29_20;
        Phase.INSTANCE = new Phase();
        Phase.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Phase.INSTANCE, "mode", (Enum)PhaseMode.VANILLA, null, null, 12, null);
        Phase.onlyOnGround$delegate = SettingFactory.booleanSetting$default((SettingContainer)Phase.INSTANCE, "only-on-ground", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Phase.autoDisable$delegate = SettingFactory.booleanSetting$default((SettingContainer)Phase.INSTANCE, "auto-disable", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        Phase.antiSwim$delegate = SettingFactory.booleanSetting$default((SettingContainer)Phase.INSTANCE, "anti-swim", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        Phase.fill$delegate = SettingFactory.booleanSetting$default((SettingContainer)Phase.INSTANCE, "fill", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        Phase.grim$delegate = SettingFactory.booleanSetting$default((SettingContainer)Phase.INSTANCE, "grim", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        Phase.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)Phase.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        Phase.timer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6AFE;
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
                throw new RuntimeException("su/catlean/u3", exception);
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
            Phase.c[n2] = Phase.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Phase.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7CAD;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])j.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    j.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Phase.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Phase.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Phase.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Phase.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

