/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.EntityPose;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.ScreenEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.JumpEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.basic.GuiMoveClickBypassMode;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.module.rage.HighJumpMode;
import su.catlean.render.RenderState;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingOverrides;
import su.catlean.util.DirectionVector;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceMode;
import su.catlean.util.Scheduler;
import su.catlean.util.ServerInfo;
import su.catlean.util.SlotResult;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.MoveFix;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class HighJump
extends Module {
    @NotNull
    public static final HighJump INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final FloatSetting vertical$delegate;
    @NotNull
    private static final FloatSetting horizontal$delegate;
    @NotNull
    private static final BooleanSetting minimizeClicks$delegate;
    @NotNull
    private static final BooleanSetting offHandFirework$delegate;
    @NotNull
    private static final BooleanSetting alternativeFireworkSwap$delegate;
    @NotNull
    private static final IntSetting ticks$delegate;
    private static boolean b;
    private static int U;
    @NotNull
    private static TickTimer jumpTimer;
    private static float savedPitch;
    private static final long d;
    private static final String[] e;
    private static final String[] g;
    private static final Map h;
    private static final long[] j;
    private static final Integer[] k;
    private static final Map l;

    private HighJump() {
        super("high-jump", Categories.getRage(), null, null);
    }

    private final HighJumpMode getMode() {
        return (HighJumpMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final float getVertical() {
        return ((Number)vertical$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final float getHorizontal() {
        return ((Number)horizontal$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final boolean getMinimizeClicks() {
        return (Boolean)minimizeClicks$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getOffHandFirework() {
        return (Boolean)offHandFirework$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getAlternativeFireworkSwap() {
        return (Boolean)alternativeFireworkSwap$delegate.getValue(this, delegatedProperties[5]);
    }

    private final int getTicks() {
        return ((Number)ticks$delegate.getValue(this, delegatedProperties[6])).intValue();
    }

    @Override
    public void onEnable(long l) {
        U = 0;
    }

    @Flow
    private final void onScreen(ScreenEvent screenEvent) {
        if (b) {
            screenEvent.cancel();
            b = false;
        }
    }

    @Flow
    private final void onJump(JumpEvent jumpEvent) {
        if (this.getMode() == HighJumpMode.VANILLA) {
            jumpEvent.cancel();
            jumpEvent.setY(this.getVertical());
            jumpEvent.setXz(this.getHorizontal());
        }
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Object object4;
        long l;
        long l2 = l = d ^ 0x7E1F0AF8BC1DL;
        long l3 = l2 ^ 0x548B61C02FB2L;
        long l4 = l2 ^ 0x67AF9535595FL;
        long l5 = l2 ^ 0x598AC70545ECL;
        int n = (int)(l5 >>> 48);
        long l6 = l5 << 16 >>> 16;
        long l7 = l2 ^ 0x2EFD7D019C77L;
        if (Globals.getPlayer().getAbilities().flying || Globals.getPlayer().isGliding()) {
            return;
        }
        if (this.getMode() == HighJumpMode.GRIM_SHULKER && Globals.getPlayer().age % 5 == 0 && MovementUtil.INSTANCE.isMoving()) {
            Object object2 = object4 = (Iterable)ServerInfo.INSTANCE.V();
            Collection collection = new ArrayList();
            Object object3 = object2.iterator();
            while (object3.hasNext()) {
                Object t = object3.next();
                if (!(t instanceof ShulkerBoxBlockEntity)) continue;
                collection.add(t);
            }
            for (Object object4 : (List)collection) {
                Object object5;
                Object object6;
                boolean bl = ((ShulkerBoxBlockEntity)object4).getAnimationStage() != ShulkerBoxBlockEntity.AnimationStage.OPENING && ((ShulkerBoxBlockEntity)object4).getAnimationStage() != ShulkerBoxBlockEntity.AnimationStage.OPENED;
                Intrinsics.checkNotNullExpressionValue(new Box(((BlockEntity)object4).getPos()).expand(0.5, 1.0, 0.5), "inflate(...)");
                Intrinsics.checkNotNullExpressionValue(new Box(((BlockEntity)object4).getPos()).expand(1.5, 1.0, 1.5), "inflate(...)");
                Color color = Color.WHITE;
                Intrinsics.checkNotNullExpressionValue(color, "WHITE");
                RenderState.addOutlineBox$default(RenderState.INSTANCE, (Box)object2, color, null, null);
                if (!Globals.getPlayer().getBoundingBox().intersects((Box)((Object)collection))) continue;
                if (bl && Globals.getPlayer().getBoundingBox().intersects((Box)((Object)collection))) {
                    BlockPos blockPos = ((BlockEntity)object4).getPos();
                    Intrinsics.checkNotNullExpressionValue(blockPos, "getBlockPos(...)");
                    object6 = PlaceManager.INSTANCE.getVanillaSide(blockPos, PlaceMode.Strict);
                    if (object6 == null) continue;
                    RotationManager.INSTANCE.submit(new ActionRotationRequest(Rotation.Companion.to(((DirectionVector)object6).getVector()), () -> HighJump.onPlayerUpdate$lambda$0((DirectionVector)object6, (ShulkerBoxBlockEntity)object4), null));
                    continue;
                }
                if (!Globals.getPlayer().getBoundingBox().intersects((Box)object2)) continue;
                if (this.getHorizontal() == 0.0f) {
                    object3 = new double[2];
                    object3[0] = Globals.getPlayer().getVelocity().x;
                    object3[1] = Globals.getPlayer().getVelocity().z;
                    object5 = object3;
                } else {
                    object5 = MovementUtil.getMovement$default(MovementUtil.INSTANCE, this.getHorizontal(), false, 0.0f, 0.0f, 0.0f, 30, null);
                }
                object6 = object5;
                Globals.getPlayer().setVelocity((double)object6[0], this.getVertical(), (double)object6[1]);
                PacketUtil.sendPacket(new CloseHandledScreenC2SPacket(Globals.getPlayer().currentScreenHandler.syncId));
            }
        }
        if (this.getMode() == HighJumpMode.ELYTRA) {
            int n2 = InventoryUtil.INSTANCE.findElytra();
            Item[] itemArray = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
            object4 = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n, l6);
            if (n2 < 0 || !((SlotResult)object4).isFound() || Globals.getPlayer().isTouchingWater() || Globals.getPlayer().isInLava()) {
                this.disable(l7);
                return;
            }
            RotationManager.INSTANCE.rotate(new Rotation(Globals.getPlayer().getYaw(), -90.0f, false, MoveFix.FORCE, 4, null), 0);
            if (U > this.getTicks() - 1) {
                int n3 = U;
                U = n3 + 1;
                if (n3 > this.getTicks() + 5) {
                    if (GuiMove.INSTANCE.getEnabled() && GuiMove.INSTANCE.getClickBypass() == GuiMoveClickBypassMode.LEGIT) {
                        MovementUtil.INSTANCE.releaseMovementKeys(l4);
                    }
                    this.disable(l7);
                }
                return;
            }
            if (Globals.getPlayer().isOnGround()) {
                if (!Globals.getMc().options.jumpKey.isPressed()) {
                    Globals.getMc().options.jumpKey.setPressed(true);
                    Scheduler.schedule$default(Scheduler.INSTANCE, 3, (byte)0, false, HighJump::onPlayerUpdate$lambda$1, 6, null);
                }
                return;
            }
            if (GuiMove.INSTANCE.getEnabled() && GuiMove.INSTANCE.getClickBypass() == GuiMoveClickBypassMode.LEGIT && MovementUtil.INSTANCE.isMoving()) {
                MovementUtil.INSTANCE.releaseMovementKeys();
                return;
            }
            if (jumpTimer.hasPassedAndReset(4)) {
                if (n2 >= 36) {
                    GuiMove.INSTANCE.setMacroActive(true);
                    int n4 = n2 - 36;
                    InteractionUtil.clickSlot(6, n4, SlotActionType.SWAP, true);
                    ElytraSwap.INSTANCE.K(true);
                    Globals.getPlayer().startGliding();
                    PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                    if (U == 0) {
                        ElytraTarget.INSTANCE.findFirework(this.getOffHandFirework(), l3, this.getAlternativeFireworkSwap());
                    }
                    InteractionUtil.clickSlot(6, n4, SlotActionType.SWAP, true);
                    GuiMove.INSTANCE.setMacroActive(false);
                } else {
                    GuiMove.INSTANCE.setMacroActive(true);
                    InteractionUtil.clickSlot$default(n2, 0, null, false, 14, null);
                    Object var68_21 = null;
                    int n5 = 14;
                    boolean bl = false;
                    SlotActionType slotActionType = null;
                    int n6 = 0;
                    InteractionUtil.clickSlot$default(6, n6, slotActionType, bl, n5, var68_21);
                    if (!this.getMinimizeClicks()) {
                        InteractionUtil.clickSlot$default(n2, 0, null, true, 6, null);
                    }
                    ElytraSwap.INSTANCE.K(true);
                    Globals.getPlayer().startGliding();
                    PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                    if (U == 0) {
                        ElytraTarget.INSTANCE.findFirework(this.getOffHandFirework(), l3, this.getAlternativeFireworkSwap());
                    }
                    if (!this.getMinimizeClicks()) {
                        InteractionUtil.clickSlot$default(n2, 0, null, false, 14, null);
                    }
                    InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                    InteractionUtil.clickSlot$default(n2, 0, null, true, 6, null);
                    GuiMove.INSTANCE.setMacroActive(false);
                }
                int n7 = U;
                U = n7 + 1;
            }
        }
    }

    @Flow
    private final void onFireWorkVelocity(FireWorkVelocityEvent fireWorkVelocityEvent) {
        if (this.getMode() != HighJumpMode.ELYTRA) {
            return;
        }
        fireWorkVelocityEvent.cancel();
        Vec3d vec3d = Globals.getPlayer().getVelocity();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getDeltaMovement(...)");
        Vec3d vec3d2 = vec3d;
        fireWorkVelocityEvent.setVelocity(Globals.getPlayer().getVelocity().add(-vec3d2.x * 0.5, 0.1 + (1.5 - vec3d2.y) * 0.5, -vec3d2.z * 0.5));
    }

    @Flow
    private final void onPreElytra(PreElytraEvent preElytraEvent) {
        if (this.getMode() != HighJumpMode.ELYTRA) {
            return;
        }
        savedPitch = Globals.getPlayer().getPitch();
        Globals.getPlayer().setPitch(-90.0f);
    }

    @Flow
    private final void onAfterElytra(AfterElytraEvent afterElytraEvent) {
        if (this.getMode() != HighJumpMode.ELYTRA) {
            return;
        }
        Globals.getPlayer().setPitch(savedPitch);
    }

    @Flow
    private final void onSetPose(SetPoseEvent setPoseEvent) {
        if (this.getMode() != HighJumpMode.ELYTRA) {
            return;
        }
        setPoseEvent.cancel();
        setPoseEvent.setPose(EntityPose.STANDING);
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block2: {
            if (this.getMode() != HighJumpMode.ELYTRA) {
                return;
            }
            if (!(receivePacket.getPacket() instanceof PlaySoundS2CPacket)) break block2;
            RegistryEntry<SoundEvent> registryEntry = ((PlaySoundS2CPacket)receivePacket.getPacket()).getSound();
            Intrinsics.checkNotNullExpressionValue(registryEntry, "getSound(...)");
            RegistryEntry<SoundEvent> registryEntry2 = registryEntry;
            if (Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_GOLD) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_IRON) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN) || Intrinsics.areEqual(registryEntry2, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER)) {
                receivePacket.cancel();
            }
        }
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == HighJumpMode.ELYTRA;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == HighJumpMode.ELYTRA;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == HighJumpMode.ELYTRA;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getMode() == HighJumpMode.ELYTRA;
    }

    private static final Unit onPlayerUpdate$lambda$0(DirectionVector directionVector, ShulkerBoxBlockEntity shulkerBoxBlockEntity) {
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, new BlockHitResult(directionVector.getVector(), directionVector.getDir(), shulkerBoxBlockEntity.getPos(), false));
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        b = true;
        return Unit.INSTANCE;
    }

    private static final void onPlayerUpdate$lambda$1() {
        block0: {
            long l = d ^ 0x1B9DFB00513AL;
            int n = (int)((l ^ 0x57CACF5892ADL) << 48 >>> 48);
            if (KeyboardUtil.isKeyPressed((char)n, 32)) break block0;
            Globals.getMc().options.jumpKey.setPressed(false);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        HighJump.d = ZkmKeys.a(-983711160038968419L, 6622404617818079575L, MethodHandles.lookup().lookupClass()).a(186499525355146L);
                        var20 = HighJump.d ^ 137015487688895L;
                        HighJump.h = new HashMap<K, V>(13);
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
                        var18_3 = new String[30];
                        var16_4 = 0;
                        var15_5 = "\u00dfF\u00b8\u00b3^ \u0012\u00d6kn,[\u0001k\u00efSM \u00880\u000f\u0086\u001f\u0098(^\u00fc\u0003\u00d2ZA\u0085\u0003iA(\u0083\u0001\u00d6\n\u00ec]>\u00df{\u00bf\u00f2%]\u009b}\u009f\u00a3\u00c1&\u001c\u00aa\u00ad\u00ef\u00daW\u00adSPE\u0018\u0007\u00e5B\u00d6\u00e2\u00eds4\u00a7!\t\u0017\u0014=.\u00dam\u00d25\u00c9.\u00c9\u00e2\u00a6 \u00e5W\u00e7*`_\u00891\u00f7\u00ca\u00a1\u0087\u00e2\u0097p\u00b2\u009b>7\u00b6UZ\f\u00a6\u00c45,+\u00ad \u0085\u00da ?\u00e1#\u0016\u00c9\u00a8\u001e4\u0010\u00b7{9\u00f9n\u00bf\u00f2\u00d1\u00f1{[\u0095R\u008fD\u009a\u00db\u0090\u00fa\u00feE\u00b5p \u00c96w*\u00a6\u00c1p\u00ba\u009f\u0010\u00d4gh\u00a0\b\u00cf\u0097\u00c1\u001e\u00cd\u00c8\u00f3S\u00c2\u0095\u00b0\u00a9\u00a3k\u00d2P\u00c8 \u00af\u0019EK\u0000Wk9~\u00d8\u00c2z \u00ca\u0007\u008c\u00c8c\u001c*\t\u00c0K\u008c\u00f9\u0098\u00ceT\u00dd?\u0010\u0096\u0010P\n\u0082D-\u00169\u001bv\u00f7$S%%^\u00c8 \u001ek\f\u00c2B\u009d\u001f\u00e3\u00e3\u00f1EC\u00bf\u00aa\u0089\u0096\u00dbc\u00aa\u00ae\u000b\u00fbM\u00dc>)\u00bf\u0011I\u00bep\u008d\u0010\u00bd&\u00e9\u0004\u00d2u;\u0017f\u0091\u0005*=\u001e\u0005'(\u009b\u00f3\u00d5\u0014\f\u00cab \u009e\u0007\u00c7\u00cc\u00b0\u0003aiQ\u0001*\u00c7\u00a6}\u00a4\u00ed\u00c7\u00d4\u00c5\u0018\u008726c\u00ba\u0092R\u00936\u00d1\u001f( 7M\u00fe)v\u00ef2cu\u0090X\u0011\u00b5\u00f8\u0083\u00e16\u00beF\u008b\u0083E\u00f7T&\u00b7E\u000f*}$= PEm\u008e\u009a\u0089>O\u00c7T\u00e0\u00100\u00cc^\u00bf\u0094:E\u008e\u000f\u00dd^\u00d8C\u0088Z\u0080\u00beUl\u00a5 %/\u0006\u0096\u00b6\u00a0\u0013\u00d62\u00b9/\u009d\u00f4q\u00f6\u00e2\u008f\u008cvU@\u001ar\u009c\u0088\u00cfK\u00eb\u0086\u00e1\u0010\u00cc0\u00e2\t\u009d\u00db%\u00b50\u00de\u00e4,(\u00b2C\u00c3\u001e\u00c3&4\u00af$\u00bb\u00e4uvk~\u00bb#\u00b7\u00e1j\u00b7M\u00f3!6\u00da\u00e1\u000e4\u00d3\u00d6i-\u00e9&,Z\u0018\u0092\u0080\u00d9q\u00fe\u008c\u009b\fv\u00e9\u00c4\u0001\u00c4\u0001\u0090\u00c3\u00b1\u00a7\u00c3\u00b5\u008c/\u00fev(\u00ddW\u0017\u00e8\u00eb\u0016\u00a8\u0003\u0092Z\u00e3`\\/\u0093?\u00d4J\u00938W\u00d2Q}\u0099\u0082+\u00a5\u00a7\u00b7\u00d8-\u00f2\u00a1\u00a4\u00d2\u0089Bb\u0017 \u009e\u0016\u00ec\u00df\u00f5\t\u00c3g\u00c2k\u0007\u00fc\u00cfu\fU\u00afH\u00b1\f\u000f'\u00990kl\u00bc\u00ea\u0090\u0087\u00fc\u00ba H\u00ce\u008a\u00ec\u00b6\u00983N\u00fa[\u00e8\u00c8\u00e4p\u00fc\u00e4\u00c7\u0094\u00d4cK(\u00d1~\u0014\u00ac:D\u000e(5M 3\u00e7\u00cb\u00d5\u00b8\u00e2t\u0016\r&\u008b\u001d\u00e7H\u00eddDc4\u00e6u|\f.b\u00f58\u00b4\u00fb\u009c&\u00f9\u0010\u00b1:h<\u0080\t\u00ff+\u007f\u00fe\u0090\u0093^\u00e7\u009f\u00ef(\u00d2\u00fb\u0001>\u00df\u00da\u00d3\b{\u00e6\u00b9\u009b\u00b3\u00f6\u00cb\u00f6\u001c\u0097\u0095\u00f8\u00fa\u0091\u00d4\u00d3'k\u00ff\u00e6d\u001b~\u00a1a\u00c0\u00de\u000e,\u00db\u00f2/8\u0094\u00bbZ\u00ff\u00a3$\u00c8\u007f\u00ff0\u00c3{\u0096\u0010\u0016jQ\u0005\u001cs\u001a\n\u0007\u00dc\u0004\u00d1\u008c\u00e1\u00a5\u00c7K\u008c\u0011\u00d7,\u00d5eb\u00dcg5i\u00cc\u009a\u00e7\u00c2\u0011\u0088Rx\u001et\u00cc{@\u00be\u0018\u00c8*\u0013\u0096x\u00e5<\u00c2\rZ\u00d2\u00a1\u00ff_\u009a}j\u00d0p\u0003_\u0004\u000f\u00a1 =\u0086\u0084\u001f\u00b6Y\u0091\n\u0086\u0007\u000ee\u00d1\u00d1U\u00adz\b-\u009c\u008a\u00e3?l\t\u00c1p\u00e8\u0094\u0087\u00bbu( LF\u00c7N (\u007f\u00c0\u00ad\u0097?j\u00a0P\u00cf\u00ca\u00a2L\u000bP\u0002s~\u009b\u00abc\u00c2pO\u00d3\u00b6+W\u00ec\u00f1z\u00b2\u00ec\u00b1\u0010\u00cdv\u00b1^\u008e\u00d1\u009f\u00b1\u0007\u00a3qP9Y\u00f2\u00f9\u0010O\t\u00c9|\u0003\u00e6\u0097\u00c2\u0082-\u00deK\u00dc\u00ed]\u00f5";
                        var17_6 = 891;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = HighJump.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "jdM\u00a86\u0011\u00f0\u00af\u00ac\u00d4q\u009bP*\u008f'ci\u0091F\u00c6WP\u008b\u00e3\u0097\u0089\u00974,\u00f3cn\u0083\u00c0\u00d2\u0081\u00d4Ts \u00deHh\u0098\u00c7\u00b6IY\u00c5\u0091G6\u00ad\u00d9-#\u0012\u00d8\u00dcW\u00fe\u00af\u00d0W\u0092\u00bbS\u009aD\u00b7.\u00a1";
                            var17_6 = 73;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = HighJump.zkmDecodeUtf8(var19_9).intern();
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
                HighJump.e = var18_3;
                HighJump.g = new String[30];
                HighJump.l = new HashMap<K, V>(13);
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
                var6_12 = new long[14];
                var3_13 = 0;
                var4_14 = "_M\u0019\u0081T2\u0087\u001a\u0081=\u00d5\u0086\u00b6H\u00bcH\u008c\u00bc\u00cb<\u0017\u00fe\u0005y\u00ac\u00d8i^\u00a8\u008cD9|x\u00b5\u00de\rD\u0089\u009e^\u00d9\u00d9\u00e1\u009e\u00e7\u00b3\u00fc\u0015>\u00d4lZh\u00be\r@\u0013N\u00dc\u00f8\u0089l\u00bf\u0083V\u00e3\u00bd\u00e9d4N\u00c6\u00ad\u00f2\u0094\u000f/s\u0098\u0080\u00a1\u00b4\u00e3\u00c9\u00f8\u00a6\u00c7\u00dd\u0099\u00aeL\u00feY<\u00d9";
                var5_15 = 96;
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
                    var4_14 = "\u009d\u00ba\u00a2\u0018vH\u00e3\u0002/*\u0018\u00cd\u0016\u00ec\u00e3\u00d9";
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
        HighJump.j = var6_12;
        HighJump.k = new Integer[14];
        var36_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(HighJump.class, "mode", "getMode()Lsu/catlean/le;", 0)), Reflection.property1(new PropertyReference1Impl(HighJump.class, "vertical", "getVertical()F", 0)), Reflection.property1(new PropertyReference1Impl(HighJump.class, "horizontal", "getHorizontal()F", 0)), Reflection.property1(new PropertyReference1Impl(HighJump.class, "minimizeClicks", "getMinimizeClicks()Z", 0)), Reflection.property1(new PropertyReference1Impl(HighJump.class, "offHandFirework", "getOffHandFirework()Z", 0)), Reflection.property1(new PropertyReference1Impl(HighJump.class, "alternativeFireworkSwap", "getAlternativeFireworkSwap()Z", 0)), Reflection.property1(new PropertyReference1Impl(HighJump.class, "ticks", "getTicks()I", 0))};
        HighJump.delegatedProperties = var36_20;
        HighJump.INSTANCE = new HighJump();
        HighJump.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)HighJump.INSTANCE, "mode", (Enum)HighJumpMode.VANILLA, null, null, 12, null);
        HighJump.vertical$delegate = SettingFactory.floatSetting$default(HighJump.INSTANCE, "vertical", 0.42f, RangesKt.rangeTo(0.1f, 20.0f), null, null, 24, null);
        HighJump.horizontal$delegate = SettingOverrides.withNameValOverride(SettingFactory.floatSetting$default(HighJump.INSTANCE, "horizontal", 0.0f, RangesKt.rangeTo(0.0f, 5.0f), null, null, 24, null), 0.0f, "copy-from-player");
        HighJump.minimizeClicks$delegate = SettingFactory.booleanSetting$default((SettingContainer)HighJump.INSTANCE, "minimize-clicks", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        HighJump.offHandFirework$delegate = SettingFactory.booleanSetting$default((SettingContainer)HighJump.INSTANCE, "offHand-firework", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        HighJump.alternativeFireworkSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)HighJump.INSTANCE, "alt-firework-swap", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        HighJump.ticks$delegate = SettingFactory.intSetting$default(HighJump.INSTANCE, "ticks", 2, new IntRange(1, 15), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 8, null);
        HighJump.jumpTimer = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x930;
        if (g[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ur", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = e[n2].getBytes("ISO-8859-1");
            HighJump.g[n2] = HighJump.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return g[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HighJump.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ur" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x352C;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])HighJump.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    HighJump.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ur", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            HighJump.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = HighJump.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ur" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HighJump.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(HighJump.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

