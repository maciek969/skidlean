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
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ServerboundMovePlayerPacketAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.rage.NoFall$WhenMappings;
import su.catlean.module.rage.NoFallFallDistanceMode;
import su.catlean.module.rage.NoFallMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.InteractMode;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceMode;
import su.catlean.util.SlotResult;
import su.catlean.util.SwitchMode;
import su.catlean.util.Timer;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class NoFall
extends Module {
    @NotNull
    public static final NoFall INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final ModeSetting fallDistance$delegate;
    @NotNull
    private static final IntSetting fallDistanceValue$delegate;
    @NotNull
    private static final BooleanSetting waterBucket$delegate;
    @NotNull
    private static final BooleanSetting retrieve$delegate;
    @NotNull
    private static final BooleanSetting enderPearl$delegate;
    @NotNull
    private static final BooleanSetting cobweb$delegate;
    @NotNull
    private static final BooleanSetting twistingVines$delegate;
    @NotNull
    private static final BooleanSetting grim$delegate;
    @NotNull
    private static final Timer fallTimer;
    private static boolean e;
    private static int u;
    private static int k;
    private static boolean j;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map f;
    private static final long[] l;
    private static final Integer[] m;
    private static final Map n;

    private NoFall() {
        super("no-fall", Categories.getRage(), null, null);
    }

    private final NoFallMode getMode() {
        return (NoFallMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final NoFallFallDistanceMode getFallDistance() {
        return (NoFallFallDistanceMode)((Object)fallDistance$delegate.getValue(this, delegatedProperties[1]));
    }

    private final int getFallDistanceValue() {
        return ((Number)fallDistanceValue$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final boolean getWaterBucket() {
        return (Boolean)waterBucket$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getRetrieve() {
        return (Boolean)retrieve$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getEnderPearl() {
        return (Boolean)enderPearl$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getCobweb() {
        return (Boolean)cobweb$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getTwistingVines() {
        return (Boolean)twistingVines$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getGrim() {
        return (Boolean)grim$delegate.getValue(this, delegatedProperties[8]);
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        block22: {
            block21: {
                long l = a ^ 0x767027D346D3L;
                long l2 = l ^ 0x223DA1428790L;
                int n = (int)(l2 >>> 32);
                int n2 = (int)(l2 << 32 >>> 32);
                Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
                if (Globals.getPlayer().age < 5) {
                    k = 0;
                }
                if (u == 1) {
                    u = 2;
                    this.stopMovement();
                    return;
                }
                if (u == 2) {
                    Globals.getPlayer().jump();
                    u = 3;
                    this.stopMovement();
                    return;
                }
                if (u == 3) {
                    j = false;
                    u = 0;
                    this.stopMovement();
                    return;
                }
                if (!this.shouldActivate()) break block21;
                switch (NoFall$WhenMappings.W[this.getMode().ordinal()]) {
                    case 1: {
                        PacketUtil.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true, Globals.getPlayer().horizontalCollision));
                        break block22;
                    }
                    case 2: {
                        Item[] itemArray;
                        Entity entity = Globals.getPlayer();
                        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
                        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
                        BlockPos blockPos2 = blockPos;
                        int n3 = 5;
                        for (int i = 0; i < n3; ++i) {
                            int n4 = i;
                            Intrinsics.checkNotNullExpressionValue(blockPos2.down(n4), "below(...)");
                            if (Globals.getWorld().getBlockState((BlockPos)itemArray).isReplaceable()) continue;
                            Intrinsics.checkNotNullExpressionValue(itemArray.up(), "above(...)");
                        }
                        Object object = new Item[1];
                        Intrinsics.checkNotNullExpressionValue(Items.ENDER_PEARL, "ENDER_PEARL");
                        SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar((Item[])object);
                        Object object2 = new Item[1];
                        Intrinsics.checkNotNullExpressionValue(Items.COBWEB, "COBWEB");
                        object = InventoryUtil.INSTANCE.findInHotbar((Item[])object2);
                        Object object3 = new Item[1];
                        Intrinsics.checkNotNullExpressionValue(Items.TWISTING_VINES, "TWISTING_VINES");
                        object2 = InventoryUtil.INSTANCE.findInHotbar((Item[])object3);
                        itemArray = new Item[1];
                        Intrinsics.checkNotNullExpressionValue(Items.WATER_BUCKET, "WATER_BUCKET");
                        object3 = InventoryUtil.INSTANCE.findInHotbar(itemArray);
                        if (((SlotResult)object3).isFound() && this.getWaterBucket() && fallTimer.hasPassed(500)) {
                            this.rotateDown((SlotResult)object3);
                            e = this.getRetrieve();
                        } else if (slotResult.isFound() && this.getEnderPearl() && fallTimer.hasPassed(500)) {
                            this.rotateDown(slotResult);
                            fallTimer.reset();
                        } else if (((SlotResult)object).isFound() && this.getCobweb()) {
                            this.placeBelow((SlotResult)object, n, blockPos2, n2);
                        } else if (((SlotResult)object2).isFound() && this.getTwistingVines()) {
                            this.placeBelow((SlotResult)object2, n, blockPos2, n2);
                        }
                        break block22;
                    }
                    case 3: 
                    case 4: {
                        j = true;
                        break block22;
                    }
                    case 5: {
                        j = true;
                        this.stopMovement();
                        break block22;
                    }
                    case 7: {
                        j = true;
                        this.stopMovement();
                        break block22;
                    }
                    case 6: {
                        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                        PacketUtil.sendPacket(new PlayerMoveC2SPacket.Full(clientPlayerEntity.getX(), clientPlayerEntity.getY() + 1.0E-9, clientPlayerEntity.getZ(), clientPlayerEntity.getYaw(), clientPlayerEntity.getPitch(), false, clientPlayerEntity.horizontalCollision));
                        clientPlayerEntity.onLanding();
                        break block22;
                    }
                    default: {
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
            if (e) {
                Item[] itemArray = new Item[1];
                Intrinsics.checkNotNullExpressionValue(Items.BUCKET, "BUCKET");
                SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar(itemArray);
                if (slotResult.isFound()) {
                    this.rotateDown(slotResult);
                }
                e = false;
            }
        }
    }

    private final void rotateDown(SlotResult slotResult) {
        RotationManager.INSTANCE.submit(new RotationRequest(new Rotation(Globals.getPlayer().getYaw(), 90.0f, false, null, 12, null), () -> NoFall.useItem(slotResult), null));
    }

    private final void placeBelow(SlotResult slotResult, int n, BlockPos blockPos, int n2) {
        block0: {
            long l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ a;
            long l2 = l ^ 0x64BB14058EF7L;
            RotationRequest rotationRequest = PlaceManager.place$default(PlaceManager.INSTANCE, blockPos, slotResult.getSlot(), this.getGrim() ? PlaceMode.GRIM : PlaceMode.Strict, InteractMode.Normal, EntityCheck.ALL, (float)Globals.getPlayer().getBlockInteractionRange(), 0.0f, 200, l2, SwitchMode.Silent, null, false, 1536, null);
            if (rotationRequest == null) break block0;
            RotationManager.INSTANCE.submit(rotationRequest);
            fallTimer.reset();
        }
    }

    private final boolean shouldActivate() {
        if (Globals.getMc().player == null || Globals.getPlayer().isGliding() || Globals.getPlayer().getAbilities().flying) {
            return false;
        }
        if (this.getMode() == NoFallMode.GrimOld || this.getMode() == NoFallMode.GrimBypass) {
            return MovementUtil.INSTANCE.getFallDistance() > 3.0;
        }
        return switch (NoFall$WhenMappings.n[this.getFallDistance().ordinal()]) {
            case 1 -> {
                if (MovementUtil.INSTANCE.getFallDistance() > (double)this.getFallDistanceValue()) {
                    yield true;
                }
                yield false;
            }
            case 2 -> {
                if ((MovementUtil.INSTANCE.getFallDistance() - 3.0) / 2.0 + 3.5 > (double)(Globals.getPlayer().getHealth() / 3.0f)) {
                    yield true;
                }
                yield false;
            }
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    @Flow
    public final void onSendPacket(@NotNull SendPacket sendPacket) {
        Intrinsics.checkNotNullParameter(sendPacket, "e");
        if (sendPacket.getPacket() instanceof PlayerMoveC2SPacket) {
            boolean bl;
            boolean bl2 = bl = (this.getMode() == NoFallMode.GRIM_NEW || this.getMode() == NoFallMode.GrimBypass) && k < 11;
            if (j) {
                if (bl) {
                    Packet<?> packet = sendPacket.getPacket();
                    Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type su.catlean.mixins.accessors.ServerboundMovePlayerPacketAccessor");
                    ((ServerboundMovePlayerPacketAccessor)((Object)packet)).setGround(!Globals.getPlayer().isOnGround());
                    int n = k;
                    k = n + 1;
                } else {
                    Packet<?> packet = sendPacket.getPacket();
                    Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type su.catlean.mixins.accessors.ServerboundMovePlayerPacketAccessor");
                    ((ServerboundMovePlayerPacketAccessor)((Object)packet)).setGround(false);
                }
                if (Globals.getPlayer().isOnGround() && u == 0 && (this.getMode() == NoFallMode.GRIM_NEW || this.getMode() == NoFallMode.GrimBypass) && !bl) {
                    u = 1;
                }
            }
        }
    }

    private final void stopMovement() {
        Globals.getPlayer().setSprinting(false);
        Globals.getMc().options.forwardKey.setPressed(false);
        Globals.getMc().options.backKey.setPressed(false);
        Globals.getMc().options.leftKey.setPressed(false);
        Globals.getMc().options.rightKey.setPressed(false);
        Globals.getMc().options.jumpKey.setPressed(false);
    }

    @Override
    public void onEnable(long l) {
        j = false;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getFallDistance() == NoFallFallDistanceMode.Custom;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == NoFallMode.Items;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == NoFallMode.Items && INSTANCE.getWaterBucket();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getMode() == NoFallMode.Items;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getMode() == NoFallMode.Items;
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getMode() == NoFallMode.Items;
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getMode() == NoFallMode.Items;
    }

    private static final Unit useItem(SlotResult slotResult) {
        InventoryUtil.INSTANCE.saveSelectedSlot();
        slotResult.select();
        ActionResult actionResult = InteractionUtil.useItem(Hand.MAIN_HAND, Globals.getPlayer().getYaw(), 90.0f);
        if (!Intrinsics.areEqual(actionResult, ActionResult.PASS)) {
            fallTimer.reset();
        }
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        InventoryUtil.INSTANCE.restoreSelectedSlot();
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
                        NoFall.a = ZkmKeys.a(2583107197582518083L, 377041133615913263L, MethodHandles.lookup().lookupClass()).a(13217624884272L);
                        var20 = NoFall.a ^ 68673379558980L;
                        NoFall.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[38];
                        var16_4 = 0;
                        var15_5 = "\u00da\u00b5\u00d5\u008695HG\u00da\u00d5\u00bdJ\u00a9ia\u000fXH\u0013\u0085\u00f0DL\u00f2\u0090v\u00bf\u00a8,,\u00e7\u000e\u00a0\u00baJT%i\u00fd\u00e0UN\u00f5\u00014\u00b21\u008b\u0093\u008f\u00d5)\u00cbv\u00a8#m\u00a8:#z\u0011\u00dc\u009f\u00bf\u00a6\u00fd\u00f7\u00a7\u00f0[\u00f7.\u00c5\u00d1A\u00e7\u009d`\u00a0\u00d4\u00f0\u009f\u00c0{$oP\u00b8\u0088\u00e7\u00dd\u00d6\u009c:\u0016\ruT\u0086\u0095gOc3)\u00b6\u00e5V\u0003\u0016\u00c6M`ynl9z\u00fc6xg\u0006\u00f5\u00f03\u0017'v\u007f\u0088\u0083\"\u0093\u00b4\u001d|\u00e4z\u009d\u0093\u008e\u00a4\u001c9\u008f\u00b7\u00d7+\u00cf\u0092h.p%\u00f8\u0017<\u0085\u0087E\u00b2\u00a4\u008e\u00e5\u0099Ww\u00a7\u00f7\u0005\u00b8\u00a8j\u00aeSr#\u00ba\u00f7\f\u00ec\u008a\u00a1\u00a0\u00aa\u009b[!}\u00b2K\u0010\u00a8H\u00c3 M1\u0084\u008e\u00af\u0091\u00cf\u0018\\\u001e\u0015\u008a\u0010<a\u0093\u00b8\u0014!}\u00f5K\u0094,\u00bb\u0019c\u00ab( b\u00cc\u00a7!\u00ed\u0083?\u00e4\u0091\u00cf*\u00aa\u00f0\u00f7\u00c2\u00ce\u00e3o\u0016l'\u001c\u00e8\u00b0\u000b\u00efF.\u009dr\u00e2\u001f\u0018\u0096\u001da\u00dd\u00e6-\u00b2x>_\u00cei\u00c4=\u001fj\u008cn\u00ba\u00a7\u00a9(\u0092\u001a\u0018\u00fa\u001f?\u00a2e\u0090\u0080~\u00a7'\u0087_M\u00e2\u0090\u00f2ez\u0086\u0097\u00d1t\u00f8\u00aa \u00de\u009a\u00d1V\u00ff\u001d\u009f\u008e\u00b7\u0091\u0013f/\u0093K\\\u00e5v\u00f5QV\u009b\u00aa\u00d4xV^\u00e3\u0089\u00b2A\u00ae \u00c4\u00c3\u00dci\u00e8\u009b\u001e!n\u0099\u001b\u00c3#\u00d8\u0003\u00a7'\u0018\u00b70\u00c4\u00e7\u00bc\u00f1\u0081\u001a\u009b\\\u00e9\u0082\u00efC \r\u000e7\u00b6\u00e6\fp\u00ca\u00b0,\u00c9\u008a\u0000\u00d8~\u009b\u00ea\u00b2\u00d9m\u0097\u0086\u00df\u00e0\u007f'}\u00b8{\u00f8\u00f3\u0080 \u00fdv\u00aa\u00bdb\u0006\u00d3\b\u00cb\u00da\u00f0\n\u00e9!\u00dc\u00ac\u00b2\u00e3\u00daMFPb;\u00d3\u00f14\u001f\u0002\u00ad\u00d2.(0\u0000\u00966\u009e\u00b7\u0010\u0007\u00bb\u00e9\u00b2\u0003\u00ba\u00cc\u00b7s\u00d7:\u00ff\u00a8Rh\u0015\u009b\u00fa\u00ac\\\u00f8w\u00c3\u008d\u000b\u0084\u008e\u00d8\u00f5\u00c0\u00dce\u00ce\u00186\u00ef\u00d9\u00c8\u0093T\u0086\u00f2\bd`\u00a4d\u00f2.\u008f\u00f8\u008bp2\u00b5j\u00e6\u0092 \u00b6\u008bsE\u00a2:\u00e3\u00d0~p\u00a4/\u00a5\u0000\u00ce\u008b\\\t\u00e1\u00d4\u00a0\u00aen^\u00ca\b\u0005\u0013\u00f2\u00a6\u00f3F\u0018S\u008a/\u00d6I\u007f\u00efmd2u\u00d2v4\u00b1g,}\u00aa\u0088B\u00fc\u00db\u00f3 \u00d4\u0096J?\u009c\u00ab.`a\u00ad\u00a7\u0091\u00d6J\u00eb\u0010\u009c\u00e6\u00abZ#\u00ef\u00a4\u00d4\u000e\u00ac\u008c\u00e5\u0019\u00f2`s8\u00ca\u009f\u00d2\u0099\u000fH\u0017\u00cd\u0091\u0012\u00aa3>\u0003\u00a5\u00d1\u00b3\u00e3\u00af/\u00a7\u00c5\u00a6\u000eR^s\u00f8W}G\bz3'?\u00a9,R\u00d7\u00c7\u0091\u00e6h\u00acU|\u00ee\u00f00,\u0000\u001a'3~\u0010C\u00ac\u00f8\u0004\u00c6\u00c8BH\u00ad \u00e3z\u00c0r\u0099\u0010(R\u0013\u009b1\u00ef?9r\u0099\u00e9\u00a8T\u000f\u0099M\u00ab\u000b'\u00ea\u00c7\u0003\u00ee\u00ecc\u000f\u00aa\u00b0+\u000f\u00c8C\u008cQ\u0000\u00be\u0085\u009a\u0080\u00dd1\u00a0h_JOg\u00da\\\u00d3\u00df\u00fb\u009b\u0001+\u00c5>\u0098\u00b0\u0098C\u00bd\u00af\u0092e\u009d\u00e3\u0090\u00a1\u009d^\u00bfa\u00bf\u00e9x\u00b7\u00f5\u009c\u00f7\u00f1\u0094$/T\u0011\u0013_\u00a0\u000f\u00b5\u00beF\u00ba*\u00fa\u00d3H3\u00ab\u00a5\u0093t\u00a1i\u00e6L\u00ba\u0085Y\u00b1\u00c5\u009b\u008d\u00c53|rb\u00ee\r\u00cc\u0002=JX\u0097\u00e0\u0016\u00d3\u00e11\u0085G\u008aq\u008f\u0017\u00ae\u00c2\u007f\u0093\u00dcSk^\u00dc:\u00bc\u00c6\u00b5o\u00absC\u007f~?\u00858\u000b.\u00e6F\u009f#u\u00a9L\u0084\u0092\u007f\u0091\u00b4:\u007f\u0013\u00b9\u0080\u00d3\u0097\u00a4\u00b6':\u00e9\u00a7!u@\u00e2\u00ffVu\u0001\u00a4d\u0098\u00e1\u00a0(\u00e0\u0018\u00cf#|\u0096\u0007HD<\\\u00a3\u00ce\u00a7\u00f9\u00ff\u0083IR\u009e\u00db\u00ea\u00f0M\u00e5\f\u0010\u00c7%\u00b5\u00abP\u0007\u001b=y\u00d8\u00c5\u001b\u0097lr\u0095(>{m\u00a0C\u00d3\u00a17\u000e\u008a\u00a6\u00b3\u00d1\u0098^D\u00b8'\u008f\u0085\u00ffWsl\u00e2\u00f7\u0019\u0096unv&.7\u00d8\u00f9Q\u009b\u008b\u00de \u00a1\u008f\u00f8\u0005\u0087\u00fb\u001d\u00d7brf\u00f5\u00eaw\u00e4\\.\u0013W\u00bd~\u00f9*4\u00a6Hb3\u00d7]U\u00ad\u0010\u000b\u00f5d\u00d4b\u00b5wN\u00d3b\u00f3\u00bc\u00ca\u00fc8\u00a6 \u00058\\]t\u00f9\u00ab2^sH5\u0015<\u00d0\u00f8\u0099\u001a\u008d\u00c0\u0000\u008dG\u00db\u00ceP\u0005 1{\u0015\u00c0\u0010\u00e1r\u0011\u001aW\u00ac(\u00a7\u0084\u0086g\u00eb\u00e0]\u00a6\u00b7\u0010\u0017\u0006\u00acWV\u008c8\u00b6\b\u00b5\u0083\\\u008bwf_\u0018\\k\u0016XZ\u001d4:\u0095\u00e3#\u0090\u00cf5\u00bc\u00e8zd\u00d1\u00eb\u00c3 O7 \u00be\u00b4\u000e3\u000b#\u00edD\u00df\u009dg\u00b6Fc\u0003t\u00e1$\u0000W\u009e>\u00f2=\u00f7\u0092\u0013\u008c2\u00a2\u00eeX\u0010\u00bc9\u00d4\u0011\u00cc\u00ee\u00c1wM\u0010\u00cd\u001b\u00e9\u00dc\u00b5\u00c6(O\u00bexA<:\u00e9\u00cd9ZL\u009dS\u00e4\u009e\\\u00beRze\u000b[@\u0094cf:I\"N\u0086\u00deo\u0088\u00df\u00ec\nq\u0095-H\u00e9\u008c*\u00fd\u00dc\u0006u\u00e8c\u00c6\u0083Z[\u00e4<\u00f0@\u008eL0\u0098\u0005\u00d7\u00e5\u0081\u008e]\u00bd\u00dd\u009a\u000b\u00cc\u00e8K\u00d6\u00da\u00ba\u001c\u00a4\u00a2\u00d8M3;\u008d@\u00f1\u00ca#B\u00a7S\u00b5/\u00c7\u00a3\u0004\u009a\u00f0\u001ev\u0096\u00ef\u00fa\u009e\u008d\u00aaq\u00842\u0004\u00f7\u0018G\u00e1f\u00ed\u00fe\u00d9\u0011F\u00e6\u00034\f`(\u00b4 \u00d5:u\rmG[\u0092(<\u0098\u009a\u00b0\u0098\"\u00f5-j\u0011\u00e9BC\b\u00b4e. \u0082\u00af.\u00c0KA\u00d9\u00ebr\u008a\u00ac\u00e9/\u00f0\t\u00bfi%\b\u00dc\u001e\u00e5(\u009b\u001f\u0002#|\u00fb\u0097\u00e8\u00cf\u00f9\u009f4#\u00c3\u001e\u00ec\u0085\n\u00ee\u008d\u00a44\u00ea\u00be\u00b4Kx\u0081\u0005l\u00a1UYS\u00bbc&\u00fd\u008em";
                        var17_6 = 1371;
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
                            var18_3[var16_4++] = NoFall.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "O\u00db\u00e7?\u00adR\u00f4\u009a8t\u00ff\u00e0o\u00a3\u00eb\u00b5 \u00b4t\u0097\u0013\u0001\u00f5N3\u00bf7I\u00c0\u00de\u008b\u008a\u00a8H\u00ffQ[\u0012\u00f0\u00fat\u00ae\u0081\u001b\u009c4v\u00da]";
                            var17_6 = 49;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = NoFall.zkmDecodeUtf8(var19_9).intern();
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
                NoFall.b = var18_3;
                NoFall.d = new String[38];
                NoFall.n = new HashMap<K, V>(13);
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
                var6_12 = new long[18];
                var3_13 = 0;
                var4_14 = "\u00ccL\u001a3\u009e\u00f5\u0087\u00b8\u00dewqq,X\u00a6h\u0019U\u00ed (\u00b9;b\u0093\u009d\u00b3\u0019\u00de\u0087^GtL\u000bO\u0015Cr\u00fc\u00deF\u00f0-\u00fc_\u00cf\u00e4\u00cfLb\u001e\u008d\u00ad|xS\u00f6\u001e\u0003|B\u00deJ\u00b7\u0090lz\u00d2P\u000f\u00d5\u00ebs\u00c9\u00cb\t\"\"\u00c1mb\u00ef\b|\u00b3\u00b98\u00cb\u008d\u00be-\t\u00d0\u0015\u001f\u00a8\u009f'\u00fc\u00a8\u0080_gh\u00f1o\u00ca\u00dc\u00e8\u00d3\u00dfU\u00e0:I0j\u00cc\u009d34\u00c7\u0002qi`;";
                var5_15 = 128;
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
                    var4_14 = "\u0012Ocm\u0089\u0019\u00f5\u00b1\u00c0\u0018qD\u0019)\u00f8\u008a";
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
        NoFall.l = var6_12;
        NoFall.m = new Integer[18];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(NoFall.class, "mode", "getMode()Lsu/catlean/lh;", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "fallDistance", "getFallDistance()Lsu/catlean/mo;", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "fallDistanceValue", "getFallDistanceValue()I", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "waterBucket", "getWaterBucket()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "retrieve", "getRetrieve()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "enderPearl", "getEnderPearl()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "cobweb", "getCobweb()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "twistingVines", "getTwistingVines()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoFall.class, "grim", "getGrim()Z", 0))};
        NoFall.delegatedProperties = var30_20;
        NoFall.INSTANCE = new NoFall();
        NoFall.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)NoFall.INSTANCE, "mode", (Enum)NoFallMode.Rubberband, null, null, 12, null);
        NoFall.fallDistance$delegate = SettingFactory.modeSetting$default((SettingContainer)NoFall.INSTANCE, "fall-distance", (Enum)NoFallFallDistanceMode.Calc, null, null, 12, null);
        NoFall.fallDistanceValue$delegate = SettingFactory.intSetting$default(NoFall.INSTANCE, "fall-distance-val", 10, new IntRange(2, 100), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        NoFall.waterBucket$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoFall.INSTANCE, "water-bucket", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        NoFall.retrieve$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoFall.INSTANCE, "retrieve", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        NoFall.enderPearl$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoFall.INSTANCE, "ender-pearl", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        NoFall.cobweb$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoFall.INSTANCE, "cobweb", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        NoFall.twistingVines$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoFall.INSTANCE, "twisting-vines", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        NoFall.grim$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoFall.INSTANCE, "grim", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 4, null);
        NoFall.fallTimer = new Timer();
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x146A;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u7", exception);
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
            NoFall.d[n2] = NoFall.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoFall.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x162A;
        if (m[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = NoFall.l[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])NoFall.n.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    NoFall.n.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            NoFall.m[n2] = n3;
        }
        return m[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = NoFall.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoFall.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(NoFall.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

