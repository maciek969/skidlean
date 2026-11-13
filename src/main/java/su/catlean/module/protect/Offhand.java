/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package su.catlean.module.protect;

import com.google.common.collect.Lists;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.PostTasksProcessEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.ClickSlotEvent;
import su.catlean.api.event.events.player.PostSyncEvent;
import su.catlean.api.event.events.player.UpdateSelectedSlotEvent;
import su.catlean.api.event.events.render.HotBarRenderEventPost;
import su.catlean.api.event.events.render.HotBarRenderEventPre;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.MinecraftAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.equip.ReFiller;
import su.catlean.module.misc.Notifier;
import su.catlean.module.player.NoInteract;
import su.catlean.module.protect.Offhand$WhenMappings;
import su.catlean.module.protect.OffhandGapplePriorityMode;
import su.catlean.module.protect.OffhandOffhandMode;
import su.catlean.module.protect.OffhandRcGapMode;
import su.catlean.module.protect.OffhandSwapMode;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.module.world.FreeCam;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.setting.StringListSetting;
import su.catlean.setting.TextSetting;
import su.catlean.setting.TextValue;
import su.catlean.util.Async;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ItemFilterEntry;
import su.catlean.util.ItemUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.SlotResult;
import su.catlean.util.Timer;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;

public final class Offhand
extends Module {
    @NotNull
    public static final Offhand INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting delayBetweenSwaps$delegate;
    @NotNull
    private static final FloatSetting totemHealth$delegate;
    @NotNull
    private static final FloatSetting totemElytraHealth$delegate;
    @NotNull
    private static final BooleanSetting outdatedSwap$delegate;
    @NotNull
    private static final ModeSetting offhand$delegate;
    @NotNull
    private static final BooleanSetting bindSwap$delegate;
    @NotNull
    private static final BindSetting swapButton$delegate;
    @NotNull
    private static final ModeSetting swapMode$delegate;
    @NotNull
    private static final SettingGroup swapGroup$delegate;
    @NotNull
    private static final StringListSetting notation;
    @NotNull
    private static final TextSetting swap1$delegate;
    @NotNull
    private static final TextSetting swap2$delegate;
    @NotNull
    private static final TextSetting swap3$delegate;
    @NotNull
    private static final TextSetting swap4$delegate;
    @NotNull
    private static final TextSetting swap5$delegate;
    @NotNull
    private static final TextSetting swap6$delegate;
    @NotNull
    private static final TextSetting swap7$delegate;
    @NotNull
    private static final TextSetting swap8$delegate;
    @NotNull
    private static final TextSetting swap9$delegate;
    @NotNull
    private static final BooleanSetting hotBarSwap$delegate;
    @NotNull
    private static final BooleanSetting resetAttackCooldown$delegate;
    @NotNull
    private static final BooleanSetting saveEnchanted$delegate;
    @NotNull
    private static final ModeSetting rcGap$delegate;
    @NotNull
    private static final ModeSetting gapplePriority$delegate;
    @NotNull
    private static final BooleanSetting crappleSpoof$delegate;
    @NotNull
    private static final BooleanSetting deathVerbose$delegate;
    @NotNull
    private static final BooleanSetting containers$delegate;
    @NotNull
    private static final SettingGroup safetyFactors$delegate;
    @NotNull
    private static final BooleanSetting onCrystalInHand$delegate;
    @NotNull
    private static final BooleanSetting onFalling$delegate;
    @NotNull
    private static final BooleanSetting onMace$delegate;
    @NotNull
    private static final BooleanSetting onExlposiveEntities$delegate;
    @NotNull
    private static final BooleanSetting onAnchors$delegate;
    @NotNull
    private static final Identifier U;
    @NotNull
    private static final Identifier I;
    @NotNull
    private static ItemStack displayStack;
    private static int b;
    private static int f;
    @NotNull
    private static AtomicBoolean isSwapping;
    private static int X;
    @NotNull
    private static final Timer swapTimer;
    @Nullable
    private static ItemStack i;
    private static float healthDrop;
    private static float lastHealthDrop;
    private static float lastHealth;
    private static int[] L;
    private static final long ab;
    private static final String[] fb;
    private static final String[] gb;
    private static final Map hb;
    private static final long[] lb;
    private static final Integer[] mb;
    private static final Map nb;
    private static final long[] ob;
    private static final Long[] pb;
    private static final Map qb;

    private Offhand() {
        String[] stringArray = new String[]{"auto-totem", "autototem"};
        super("offhand", Categories.getProtect(), CollectionsKt.listOf(stringArray));
    }

    private final int getDelayBetweenSwaps() {
        return ((Number)delayBetweenSwaps$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final float getTotemHealth() {
        return ((Number)totemHealth$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final float getTotemElytraHealth() {
        return ((Number)totemElytraHealth$delegate.getValue(this, delegatedProperties[2])).floatValue();
    }

    private final boolean getOutdatedSwap() {
        return (Boolean)outdatedSwap$delegate.getValue(this, delegatedProperties[3]);
    }

    private final OffhandOffhandMode getOffhand() {
        return (OffhandOffhandMode)((Object)offhand$delegate.getValue(this, delegatedProperties[4]));
    }

    private final boolean getBindSwap() {
        return (Boolean)bindSwap$delegate.getValue(this, delegatedProperties[5]);
    }

    private final Bind getSwapButton() {
        return (Bind)swapButton$delegate.getValue(this, delegatedProperties[6]);
    }

    private final OffhandSwapMode getSwapMode() {
        return (OffhandSwapMode)((Object)swapMode$delegate.getValue(this, delegatedProperties[7]));
    }

    private final SettingParent getSwapGroup() {
        return (SettingParent)swapGroup$delegate.getValue(this, delegatedProperties[8]);
    }

    private final TextValue getNotation() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return (TextValue)notation.getValue(this, delegatedProperties[Offhand.zkmBootstrap2("e", (int)28709, (long)(0x3450BD8420122F50L ^ l))]);
    }

    private final void setNotation(TextValue textValue) {
        long l = 0L;
        l = ab ^ l;
        notation.setValue(this, delegatedProperties[Offhand.zkmBootstrap2("e", (int)31290, (long)(0x5A1AE5FB82F289B2L ^ l))], textValue);
    }

    private final String getSwap1() {
        return (String)swap1$delegate.getValue(this, delegatedProperties[10]);
    }

    private final void setSwap1(String string) {
        swap1$delegate.setValue(this, delegatedProperties[10], string);
    }

    private final String getSwap2() {
        return (String)swap2$delegate.getValue(this, delegatedProperties[11]);
    }

    private final void setSwap2(String string) {
        swap2$delegate.setValue(this, delegatedProperties[11], string);
    }

    private final String getSwap3() {
        return (String)swap3$delegate.getValue(this, delegatedProperties[12]);
    }

    private final void setSwap3(String string) {
        swap3$delegate.setValue(this, delegatedProperties[12], string);
    }

    private final String getSwap4() {
        return (String)swap4$delegate.getValue(this, delegatedProperties[13]);
    }

    private final void setSwap4(String string) {
        swap4$delegate.setValue(this, delegatedProperties[13], string);
    }

    private final String getSwap5() {
        return (String)swap5$delegate.getValue(this, delegatedProperties[14]);
    }

    private final void setSwap5(String string) {
        swap5$delegate.setValue(this, delegatedProperties[14], string);
    }

    private final String getSwap6() {
        return (String)swap6$delegate.getValue(this, delegatedProperties[15]);
    }

    private final void setSwap6(String string) {
        swap6$delegate.setValue(this, delegatedProperties[15], string);
    }

    private final String getSwap7() {
        return (String)swap7$delegate.getValue(this, delegatedProperties[16]);
    }

    private final void setSwap7(String string) {
        swap7$delegate.setValue(this, delegatedProperties[16], string);
    }

    private final String getSwap8() {
        return (String)swap8$delegate.getValue(this, delegatedProperties[17]);
    }

    private final void setSwap8(String string) {
        swap8$delegate.setValue(this, delegatedProperties[17], string);
    }

    private final String getSwap9() {
        return (String)swap9$delegate.getValue(this, delegatedProperties[18]);
    }

    private final void setSwap9(String string) {
        swap9$delegate.setValue(this, delegatedProperties[18], string);
    }

    private final boolean getHotBarSwap() {
        return (Boolean)hotBarSwap$delegate.getValue(this, delegatedProperties[19]);
    }

    private final boolean getResetAttackCooldown() {
        return (Boolean)resetAttackCooldown$delegate.getValue(this, delegatedProperties[20]);
    }

    private final boolean getSaveEnchanted() {
        return (Boolean)saveEnchanted$delegate.getValue(this, delegatedProperties[21]);
    }

    private final OffhandRcGapMode getRcGap() {
        return (OffhandRcGapMode)((Object)rcGap$delegate.getValue(this, delegatedProperties[22]));
    }

    private final OffhandGapplePriorityMode getGapplePriority() {
        return (OffhandGapplePriorityMode)((Object)gapplePriority$delegate.getValue(this, delegatedProperties[23]));
    }

    private final boolean getCrappleSpoof() {
        return (Boolean)crappleSpoof$delegate.getValue(this, delegatedProperties[24]);
    }

    private final boolean getDeathVerbose() {
        return (Boolean)deathVerbose$delegate.getValue(this, delegatedProperties[25]);
    }

    private final boolean getContainers() {
        return (Boolean)containers$delegate.getValue(this, delegatedProperties[26]);
    }

    private final SettingParent getSafetyFactors() {
        return (SettingParent)safetyFactors$delegate.getValue(this, delegatedProperties[27]);
    }

    private final boolean getOnCrystalInHand() {
        return (Boolean)onCrystalInHand$delegate.getValue(this, delegatedProperties[28]);
    }

    private final boolean getOnFalling() {
        return (Boolean)onFalling$delegate.getValue(this, delegatedProperties[29]);
    }

    private final boolean getOnMace() {
        return (Boolean)onMace$delegate.getValue(this, delegatedProperties[30]);
    }

    private final boolean getOnExlposiveEntities() {
        return (Boolean)onExlposiveEntities$delegate.getValue(this, delegatedProperties[31]);
    }

    private final boolean getOnAnchors() {
        return (Boolean)onAnchors$delegate.getValue(this, delegatedProperties[32]);
    }

    public final int M() {
        return X;
    }

    public final void P(int n) {
        X = n;
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        if (!this.getBindSwap() || this.getSwapMode() != OffhandSwapMode.CUSTOM) {
            return;
        }
        int n = render2DEvent.getContext().getScaledWindowHeight() - 23;
        int n2 = render2DEvent.getContext().getScaledWindowHeight() - 16 - 3;
        int n3 = render2DEvent.getContext().getScaledWindowWidth() / 2 - 91 - 29;
        int n4 = 21;
        List list = this.getSwapItems();
        if (list.isEmpty()) {
            return;
        }
        Iterable iterable = list;
        int n5 = 0;
        for (Object t : iterable) {
            int n6;
            if ((n6 = n5++) < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ItemStack itemStack = (ItemStack)t;
            int n7 = n6;
            int n8 = n3 - n7 * n4;
            render2DEvent.getContext().drawGuiTexture(RenderPipelines.GUI_TEXTURED, I, n8, n, 29, 24);
            if (itemStack.isEmpty()) continue;
            render2DEvent.getContext().drawItem(itemStack, n8 + 3, n2);
        }
        if (b == list.size()) {
            int n9 = b;
            b = n9 + -1;
        }
        int n10 = n3 - (list.size() - b - 1) * n4;
        render2DEvent.getContext().drawGuiTexture(RenderPipelines.GUI_TEXTURED, U, n10 - 1, n, 24, 23);
    }

    @Flow
    private final void onPostTasksProcess(PostTasksProcessEvent postTasksProcessEvent) {
        long l = ab ^ 0x5FC412786DF5L;
        long l2 = l ^ 0x2B4294D0BFCL;
        if (!this.isInGame()) {
            return;
        }
        float f = Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount();
        if (f < lastHealth) {
            lastHealthDrop = healthDrop;
            healthDrop = lastHealth - f;
        }
        lastHealth = f;
        this.swapToOffhand(this.findItemSlot(l2));
    }

    @Flow
    private final void onPostSync(PostSyncEvent postSyncEvent) {
        if (this.shouldRightClickGap() && !Globals.getPlayer().isUsingItem()) {
            MinecraftClient minecraftClient = Globals.getMc();
            Intrinsics.checkNotNull(minecraftClient, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
            ((MinecraftAccessor)((Object)minecraftClient)).idoItemUse();
        }
        int n = X;
        X = n + -1;
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block0: {
            long l = ab ^ 0x2B99164091C2L;
            long l2 = l ^ 0x19991D613DFDL;
            if (!(receivePacket.getPacket() instanceof EntityStatusS2CPacket) || Globals.getMc().player == null || Globals.getMc().world == null || !this.getDeathVerbose() || ((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus() != 3 && ((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus() != 60 || !Intrinsics.areEqual(((EntityStatusS2CPacket)receivePacket.getPacket()).getEntity(Globals.getWorld()), Globals.getPlayer())) break block0;
            this.update(l2);
        }
    }

    @Flow
    private final void onUpdateSelectedSlot(UpdateSelectedSlotEvent updateSelectedSlotEvent) {
        int n;
        char c;
        long l = ab ^ 0x536546FD102L;
        int n2 = (int)((l ^ 0x3A28AB9CFFBL) << 48 >>> 48);
        if (!this.isInGame() || isSwapping.get()) {
            return;
        }
        int n3 = Globals.getPlayer().getInventory().getSelectedSlot();
        if (this.getSwapMode() == OffhandSwapMode.CUSTOM && KeyboardUtil.isKeyPressed(c = (char)n2, n = this.getSwapButton().getKey())) {
            boolean bl;
            List list;
            block8: {
                list = this.getSwapItems();
                if (list.isEmpty()) {
                    return;
                }
                Iterable iterable = new IntRange(0, 8);
                if (iterable instanceof Collection && ((Collection)iterable).isEmpty()) {
                    v0 = false;
                } else {
                    Iterator iterator2 = iterable.iterator();
                    while (iterator2.hasNext()) {
                        int n4 = ((IntIterator)iterator2).nextInt();
                        int n5 = n4;
                        if (!(Globals.getMc().options.hotbarKeys[n5].wasPressed() || Globals.getMc().options.hotbarKeys[n5].isPressed())) continue;
                        v0 = true;
                        break block8;
                    }
                    v0 = bl = false;
                }
            }
            if (bl) {
                b = updateSelectedSlotEvent.getSlot() % list.size();
            } else {
                int n6 = updateSelectedSlotEvent.getSlot();
                int n7 = n6 == (n3 + 1) % 9 ? 1 : (n6 == (n3 + 8) % 9 ? -1 : 0);
                b = n7 != 0 ? this.floorMod(b + n7, list.size()) : this.floorMod(updateSelectedSlotEvent.getSlot(), list.size());
            }
            updateSelectedSlotEvent.cancel();
        }
    }

    @Flow
    private final void onClickSlot(ClickSlotEvent clickSlotEvent) {
        int n;
        char c;
        long l = ab ^ 0x62C3A90677AL;
        int n2 = (int)((l ^ 0xB8E4467983L) << 48 >>> 48);
        if (this.getSwapMode() == OffhandSwapMode.CUSTOM && KeyboardUtil.isKeyPressed(c = (char)n2, n = this.getSwapButton().getKey()) && Globals.getMc().currentScreen != null && clickSlotEvent.getAction() == SlotActionType.PICKUP) {
            int n3 = clickSlotEvent.getId();
            boolean bl = 0 <= n3 ? n3 < 45 : false;
            if (bl) {
                boolean bl2;
                String string;
                block27: {
                    String string2 = Globals.getPlayer().getInventory().getStack(clickSlotEvent.getId() > 35 ? clickSlotEvent.getId() - 36 : clickSlotEvent.getId()).getName().getString();
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    string = string2;
                    if (Intrinsics.areEqual(string, "Air")) {
                        return;
                    }
                    Object object = new String[]{this.getSwap1(), this.getSwap2(), this.getSwap3(), this.getSwap4(), this.getSwap5(), this.getSwap6(), this.getSwap7(), this.getSwap8(), this.getSwap9()};
                    if ((object = (Iterable)CollectionsKt.listOf(object)) instanceof Collection && ((Collection)object).isEmpty()) {
                        v2 = false;
                    } else {
                        Iterator iterator2 = object.iterator();
                        while (iterator2.hasNext()) {
                            Object t = iterator2.next();
                            String string3 = (String)t;
                            if (!Intrinsics.areEqual(string3, string)) continue;
                            v2 = true;
                            break block27;
                        }
                        v2 = bl2 = false;
                    }
                }
                if (Intrinsics.areEqual(this.getSwap1(), string)) {
                    this.setSwap1("");
                }
                if (Intrinsics.areEqual(this.getSwap2(), string)) {
                    this.setSwap2("");
                }
                if (Intrinsics.areEqual(this.getSwap3(), string)) {
                    this.setSwap3("");
                }
                if (Intrinsics.areEqual(this.getSwap4(), string)) {
                    this.setSwap4("");
                }
                if (Intrinsics.areEqual(this.getSwap5(), string)) {
                    this.setSwap5("");
                }
                if (Intrinsics.areEqual(this.getSwap6(), string)) {
                    this.setSwap6("");
                }
                if (Intrinsics.areEqual(this.getSwap7(), string)) {
                    this.setSwap7("");
                }
                if (Intrinsics.areEqual(this.getSwap8(), string)) {
                    this.setSwap8("");
                }
                if (Intrinsics.areEqual(this.getSwap8(), string)) {
                    this.setSwap8("");
                }
                if (bl2) {
                    clickSlotEvent.cancel();
                    return;
                }
                switch (f) {
                    case 0: {
                        this.setSwap1(string);
                        break;
                    }
                    case 1: {
                        this.setSwap2(string);
                        break;
                    }
                    case 2: {
                        this.setSwap3(string);
                        break;
                    }
                    case 3: {
                        this.setSwap4(string);
                        break;
                    }
                    case 4: {
                        this.setSwap5(string);
                        break;
                    }
                    case 5: {
                        this.setSwap6(string);
                        break;
                    }
                    case 6: {
                        this.setSwap7(string);
                        break;
                    }
                    case 7: {
                        this.setSwap8(string);
                        break;
                    }
                    case 8: {
                        this.setSwap9(string);
                        break;
                    }
                }
                int n4 = f;
                f = n4 + 1;
                f %= 9;
                clickSlotEvent.cancel();
            }
        }
    }

    @Flow
    private final void onHotBarRenderEventPre(HotBarRenderEventPre hotBarRenderEventPre) {
        block0: {
            if (Globals.getMc().getCameraEntity() == null || this.getSwapMode() != OffhandSwapMode.CUSTOM || !this.getBindSwap()) break block0;
            ItemStack itemStack = Globals.getPlayer().getOffHandStack();
            Intrinsics.checkNotNullExpressionValue(itemStack, "getOffhandItem(...)");
            displayStack = itemStack;
            Globals.getPlayer().setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
        }
    }

    @Flow
    private final void onHotBarRenderEventPost(HotBarRenderEventPost hotBarRenderEventPost) {
        block0: {
            if (Globals.getMc().getCameraEntity() == null || this.getSwapMode() != OffhandSwapMode.CUSTOM || !this.getBindSwap()) break block0;
            Globals.getPlayer().setStackInHand(Hand.OFF_HAND, displayStack);
        }
    }

    private final void update(long l) {
        Object object;
        boolean bl;
        long l2 = (l = ab ^ l) ^ 0x512543CF5ACL;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        int n2 = ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n, null);
        float f = ServerInfo.INSTANCE.getTpsRounded();
        float f2 = ServerInfo.INSTANCE.getTps();
        long l4 = Globals.now() - 1000000L;
        Item item = Items.TOTEM_OF_UNDYING;
        Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
        Object var17_8 = null;
        boolean bl2 = false;
        Item item2 = item;
        int n3 = InventoryUtil.findItemSlot$default(InventoryUtil.INSTANCE, item2, bl2, var17_8);
        boolean bl3 = Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.TOTEM_OF_UNDYING);
        boolean bl4 = Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.TOTEM_OF_UNDYING);
        boolean bl5 = Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.TOTEM_OF_UNDYING);
        Object object2 = "";
        if (bl4) {
            object2 = (String)object2 + "mainhand ";
        }
        if (bl5) {
            object2 = (String)object2 + "offhand ";
        }
        if (bl3) {
            object2 = (String)object2 + "cursor ";
        }
        boolean bl6 = false;
        boolean bl7 = bl = n2 > 400 || f < 5.0f || Math.abs(f2 - f) > 5.0f || l4 < 450L;
        if (n3 > 0 || bl3 || bl5 || bl4) {
            bl6 = true;
        }
        String string = Formatting.GREEN.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String string2 = string;
        String string3 = Formatting.RED.toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        String string4 = string3;
        String string5 = Formatting.GRAY.toString();
        Intrinsics.checkNotNullExpressionValue(string5, "toString(...)");
        String string6 = string5;
        boolean bl8 = bl6 && healthDrop > lastHealthDrop && l4 < 500L;
        Module module = this;
        String string7 = bl6 ? string4 : string2;
        Object object3 = bl6 ? " (" + n3 + " totems left)" : "";
        if (bl6) {
            String string8 = ((Object)StringsKt.trim((CharSequence)object2)).toString();
            String string9 = ((CharSequence)object2).length() == 0 ? "inventory" : "";
            object = "\n totem locations = (" + string9 + string8 + ")";
        } else {
            object = "";
        }
        String string10 = bl6 ? (bl || this.getElytraTotemThreshold() >= 36.0f ? string2 + "Unstable internet connection / Server lag" : string4 + "OffHand does not have time to take the totem increase minimum health") : string2 + "No totems + skill issue";
        String string11 = string6;
        String string12 = Math.abs(f2 - f) > 5.0f ? string4 + "BAD" : string2 + "OK";
        float f3 = Math.abs(f2 - f);
        String string13 = string6;
        String string14 = l4 < 450L ? string4 + "BAD" : string2 + "OK";
        long l5 = l4;
        String string15 = string6;
        String string16 = f < 5.0f ? string4 + "BAD" : string2 + "OK";
        float f4 = f;
        String string17 = string6;
        String string18 = n2 > 400 ? string4 + "BAD" : string2 + "OK";
        int n4 = n2;
        String string19 = string6;
        boolean bl9 = bl8;
        String string20 = string6;
        float f5 = lastHealthDrop;
        String string21 = string6;
        float f6 = healthDrop;
        String string22 = string6;
        Object object4 = object;
        String string23 = string6;
        Object object5 = object3;
        boolean bl10 = bl6;
        String string24 = string7;
        String string25 = string6;
        String string26 = string4;
        ModuleMessages.S(module, "[" + string26 + "Death Analysis" + string25 + "]\n failed =" + string24 + " " + bl10 + (String)object5 + string23 + (String)object4 + string22 + "\n damage = " + f6 + string21 + "\n prevDamage = " + f5 + string20 + "\n double pop = " + bl9 + " " + string19 + "\n ping = " + n4 + " | " + string18 + string17 + "\n tps = " + f4 + " | " + string16 + string15 + "\n lastPop = " + l5 + " | " + string14 + string13 + "\n tpsSpike = " + f3 + " | " + string12 + string11 + "\n\n Conclusion: " + string10, false, null);
    }

    private final void swapToOffhand(int n) {
        boolean bl;
        if (!this.isInGame()) {
            return;
        }
        int n2 = n;
        boolean bl2 = bl = Globals.getMc().currentScreen instanceof ShulkerBoxScreen || Globals.getMc().currentScreen instanceof GenericContainerScreen;
        if (!this.getContainers() && bl) {
            return;
        }
        if (n2 != -1 && X <= 0) {
            X = this.getDelayBetweenSwaps();
            if (n2 < 9 && this.getHotBarSwap()) {
                isSwapping.set(true);
                InventoryUtil.INSTANCE.switchToSlot(n2);
                PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                InventoryUtil.INSTANCE.restoreSelectedSlot();
                isSwapping.set(false);
                ReFiller.INSTANCE.K(3);
                ReFiller.INSTANCE.i(n2);
                if (this.getResetAttackCooldown()) {
                    Globals.getPlayer().resetTicksSinceLastAttack();
                }
                return;
            }
            if (n2 < 9) {
                n2 += 36;
            }
            if (!Globals.getPlayer().currentScreenHandler.getCursorStack().isEmpty()) {
                InteractionUtil.clickSlot$default(-999, 0, null, false, 14, null);
            }
            if (this.getOutdatedSwap()) {
                InventoryUtil.INSTANCE.swapWithHotbar(n2, 45);
            } else {
                InteractionUtil.clickSlot(n2, 40, SlotActionType.SWAP, !bl);
            }
        }
    }

    private final int findItemSlot(long l) {
        int n;
        char c;
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x547DCB77F39L;
        int n2 = (int)((l2 ^ 0x52F950B6656AL) << 48 >>> 48);
        long l4 = l2 ^ 0x6E998126363L;
        int n3 = (int)(l4 >>> 48);
        int n4 = (int)(l4 << 16 >>> 48);
        int n5 = (int)(l4 << 32 >>> 32);
        long l5 = l2 ^ 0x393BA9135F0CL;
        int n6 = (int)(l5 >>> 48);
        long l6 = l5 << 16 >>> 16;
        if (!this.isInGame()) {
            return -1;
        }
        Object object = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.ENCHANTED_GOLDEN_APPLE, "ENCHANTED_GOLDEN_APPLE");
        SlotResult slotResult = InventoryUtil.INSTANCE.findInInventory((Item[])object, (char)n6, l6);
        Object object2 = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.GOLDEN_APPLE, "GOLDEN_APPLE");
        object = InventoryUtil.INSTANCE.findInInventory((Item[])object2, (char)n6, l6);
        Object object3 = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.TOTEM_OF_UNDYING, "TOTEM_OF_UNDYING");
        object2 = InventoryUtil.INSTANCE.findInInventory((Item[])object3, (char)n6, l6);
        object3 = InventoryUtil.INSTANCE.findInInventory((short)n3, Offhand::isTotem, (short)n4, n5);
        ItemStack itemStack = Globals.getPlayer().getOffHandStack();
        Intrinsics.checkNotNullExpressionValue(itemStack, "getOffhandItem(...)");
        ItemStack itemStack2 = itemStack;
        Item item = itemStack2.getItem();
        Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
        Item item2 = item;
        boolean bl = Intrinsics.areEqual(item2, Items.GOLDEN_APPLE);
        boolean bl2 = Intrinsics.areEqual(item2, Items.ENCHANTED_GOLDEN_APPLE);
        int n7 = -1;
        ItemFilterEntry itemFilterEntry = null;
        switch (Offhand$WhenMappings.v[this.getOffhand().ordinal()]) {
            case 1: {
                itemFilterEntry = this.getWantedItem(item2, l3, bl);
                break;
            }
            case 2: {
                Item item3 = Items.END_CRYSTAL;
                Intrinsics.checkNotNullExpressionValue(item3, "END_CRYSTAL");
                itemFilterEntry = new ItemFilterEntry(item3, false, false, null, 14, null);
                break;
            }
            case 3: {
                if (this.getCrappleSpoof()) {
                    if (Globals.getPlayer().hasStatusEffect(StatusEffects.ABSORPTION)) {
                        StatusEffectInstance statusEffectInstance = Globals.getPlayer().getStatusEffect(StatusEffects.ABSORPTION);
                        Intrinsics.checkNotNull(statusEffectInstance);
                        if (statusEffectInstance.getAmplifier() > 2) {
                            if (((SlotResult)object).isFound() || bl) {
                                Item item4 = Items.GOLDEN_APPLE;
                                Intrinsics.checkNotNullExpressionValue(item4, "GOLDEN_APPLE");
                                itemFilterEntry = new ItemFilterEntry(item4, false, false, null, 14, null);
                                break;
                            }
                            if (!slotResult.isFound() && !bl2) break;
                            Item item5 = Items.ENCHANTED_GOLDEN_APPLE;
                            Intrinsics.checkNotNullExpressionValue(item5, "ENCHANTED_GOLDEN_APPLE");
                            itemFilterEntry = new ItemFilterEntry(item5, false, false, null, 14, null);
                            break;
                        }
                    }
                    if (slotResult.isFound() || bl2) {
                        Item item6 = Items.ENCHANTED_GOLDEN_APPLE;
                        Intrinsics.checkNotNullExpressionValue(item6, "ENCHANTED_GOLDEN_APPLE");
                        itemFilterEntry = new ItemFilterEntry(item6, false, false, null, 14, null);
                        break;
                    }
                    if (!((SlotResult)object).isFound() && !bl) break;
                    Item item7 = Items.GOLDEN_APPLE;
                    Intrinsics.checkNotNullExpressionValue(item7, "GOLDEN_APPLE");
                    itemFilterEntry = new ItemFilterEntry(item7, false, false, null, 14, null);
                    break;
                }
                if (((SlotResult)object).isFound() || bl) {
                    Item item8 = Items.GOLDEN_APPLE;
                    Intrinsics.checkNotNullExpressionValue(item8, "GOLDEN_APPLE");
                    itemFilterEntry = new ItemFilterEntry(item8, false, false, null, 14, null);
                    break;
                }
                if (!slotResult.isFound() && !bl2) break;
                Item item9 = Items.ENCHANTED_GOLDEN_APPLE;
                Intrinsics.checkNotNullExpressionValue(item9, "ENCHANTED_GOLDEN_APPLE");
                itemFilterEntry = new ItemFilterEntry(item9, false, false, null, 14, null);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
        if (this.getHealth() <= this.getElytraTotemThreshold() && (((SlotResult)object2).isFound() || Intrinsics.areEqual(item2, Items.TOTEM_OF_UNDYING))) {
            Item item10 = Items.TOTEM_OF_UNDYING;
            Intrinsics.checkNotNullExpressionValue(item10, "TOTEM_OF_UNDYING");
            itemFilterEntry = new ItemFilterEntry(item10, false, false, null, 14, null);
        }
        if (!Intrinsics.areEqual(itemFilterEntry, Items.TOTEM_OF_UNDYING)) {
            itemFilterEntry = this.isInDanger(itemFilterEntry);
        }
        if (this.shouldRightClickGap() && (this.getRcGap() == OffhandRcGapMode.ALWAYS || this.getRcGap() == OffhandRcGapMode.ONLY_SAFE && this.getHealth() > this.getElytraTotemThreshold())) {
            if (this.getGapplePriority() == OffhandGapplePriorityMode.NORMAL) {
                if (((SlotResult)object).isFound() || bl) {
                    Item item11 = Items.GOLDEN_APPLE;
                    Intrinsics.checkNotNullExpressionValue(item11, "GOLDEN_APPLE");
                    itemFilterEntry = new ItemFilterEntry(item11, false, false, null, 14, null);
                } else if (slotResult.isFound() || bl2) {
                    Item item12 = Items.ENCHANTED_GOLDEN_APPLE;
                    Intrinsics.checkNotNullExpressionValue(item12, "ENCHANTED_GOLDEN_APPLE");
                    itemFilterEntry = new ItemFilterEntry(item12, false, false, null, 14, null);
                }
            } else if (slotResult.isFound() || bl2) {
                Item item13 = Items.ENCHANTED_GOLDEN_APPLE;
                Intrinsics.checkNotNullExpressionValue(item13, "ENCHANTED_GOLDEN_APPLE");
                itemFilterEntry = new ItemFilterEntry(item13, false, false, null, 14, null);
            } else if (((SlotResult)object).isFound() || bl) {
                Item item14 = Items.GOLDEN_APPLE;
                Intrinsics.checkNotNullExpressionValue(item14, "GOLDEN_APPLE");
                itemFilterEntry = new ItemFilterEntry(item14, false, false, null, 14, null);
            }
        }
        if (!(itemFilterEntry == null || !Intrinsics.areEqual(itemFilterEntry.getItem(), Items.TOTEM_OF_UNDYING) || Intrinsics.areEqual(item2, Items.AIR) || Intrinsics.areEqual(item2, Items.TOTEM_OF_UNDYING) && (!itemStack2.hasEnchantments() || !this.getSaveEnchanted()) || Intrinsics.areEqual(item2, Items.FIREWORK_ROCKET) && ElytraTarget.INSTANCE.getEnabled())) {
            i = itemStack2;
        }
        if (this.getOffhand() == OffhandOffhandMode.OFF && this.getBindSwap() && this.getSwapButton().getKey() != -1 && KeyboardUtil.isKeyPressed(c = (char)n2, n = this.getSwapButton().getKey())) {
            i = null;
        }
        if ((itemFilterEntry == null || Intrinsics.areEqual(itemFilterEntry.getItem(), Items.AIR)) && i != null) {
            ItemStack itemStack3 = i;
            i = null;
            int n8 = InventoryUtil.INSTANCE.findInInventory((short)n3, arg_0 -> Offhand.isSameStack(itemStack3, arg_0), (short)n4, n5).getSlot();
            int n9 = InventoryUtil.INSTANCE.findInInventory((short)n3, arg_0 -> Offhand.isSameItem(itemStack3, arg_0), (short)n4, n5).getSlot();
            if (n8 == -1) {
                return n9 >= 36 ? n9 - 36 : n9;
            }
            return n8 >= 36 ? n8 - 36 : n8;
        }
        if (itemFilterEntry == null || Intrinsics.areEqual(itemFilterEntry.getItem(), Items.AIR)) {
            return -1;
        }
        if (Intrinsics.areEqual(itemFilterEntry.getName(), "") && Intrinsics.areEqual(itemFilterEntry.getItem(), Items.TOTEM_OF_UNDYING) && this.getSaveEnchanted() && Intrinsics.areEqual(item2, Items.TOTEM_OF_UNDYING) && itemStack2.hasEnchantments() && ((SlotResult)object3).isFound()) {
            return ((SlotResult)object3).getSlot() >= 36 ? ((SlotResult)object3).getSlot() - 36 : ((SlotResult)object3).getSlot();
        }
        for (int i = 44; 8 < i; --i) {
            ItemStack itemStack4;
            boolean bl3 = ((CharSequence)itemFilterEntry.getName()).length() == 0;
            if (bl3 ? Intrinsics.areEqual(item2, itemFilterEntry.getItem()) && !itemFilterEntry.isAnother() : Intrinsics.areEqual(itemStack2.getName().getString(), itemFilterEntry.getName())) {
                return -1;
            }
            int n10 = i >= 36 ? i - 36 : i;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(n10), "getItem(...)");
            if (!itemStack4.getItem().equals(itemFilterEntry.getItem()) || !(((CharSequence)itemFilterEntry.getName()).length() == 0) && !Intrinsics.areEqual(itemFilterEntry.getName(), itemStack4.getName().getString()) || itemFilterEntry.isEnchanted() && !itemStack4.hasEnchantments()) continue;
            n7 = n10;
            break;
        }
        if (Intrinsics.areEqual(itemFilterEntry, Globals.getPlayer().getMainHandStack().getItem()) && Globals.getMc().options.useKey.isPressed()) {
            return -1;
        }
        return n7;
    }

    private final ItemFilterEntry isInDanger(ItemFilterEntry itemFilterEntry) {
        ItemFilterEntry itemFilterEntry2;
        block15: {
            Object v9;
            block14: {
                itemFilterEntry2 = itemFilterEntry;
                if (this.getOnFalling() && (double)this.getHealth() - ((MovementUtil.INSTANCE.getFallDistance() - 3.0) / 2.0 + 3.5) < 0.5 && !Globals.getPlayer().isGliding() && !FreeCam.INSTANCE.getEnabled()) {
                    Item item = Items.TOTEM_OF_UNDYING;
                    Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
                    itemFilterEntry2 = new ItemFilterEntry(item, false, false, null, 14, null);
                }
                if (this.getOnCrystalInHand()) {
                    for (Entity entity : Async.INSTANCE.getTasks()) {
                        if (Friends.getIsHomie((PlayerEntity)entity) || Intrinsics.areEqual(entity, Globals.getPlayer()) || !(MovementUtil.INSTANCE.getServerPos().squaredDistanceTo(this.getPos(entity)) < 36.0) || !Intrinsics.areEqual(((LivingEntity)entity).getMainHandStack().getItem(), Items.OBSIDIAN) && !Intrinsics.areEqual(((LivingEntity)entity).getMainHandStack().getItem(), Items.END_CRYSTAL) && !Intrinsics.areEqual(((LivingEntity)entity).getOffHandStack().getItem(), Items.OBSIDIAN) && !Intrinsics.areEqual(((LivingEntity)entity).getOffHandStack().getItem(), Items.END_CRYSTAL)) continue;
                        Item item = Items.TOTEM_OF_UNDYING;
                        Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
                        itemFilterEntry2 = new ItemFilterEntry(item, false, false, null, 14, null);
                    }
                }
                if (this.getOnExlposiveEntities()) {
                    Iterator iterator2 = Lists.newArrayList(Globals.getWorld().getEntities()).iterator();
                    Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
                    Iterator iterator3 = iterator2;
                    while (iterator3.hasNext()) {
                        Entity entity;
                        Object e = iterator3.next();
                        Intrinsics.checkNotNullExpressionValue(e, "next(...)");
                        entity = (Entity)e;
                        if (entity instanceof FireballEntity) {
                            Entity entity2 = Globals.getPlayer();
                            if (this.getPos(entity).distanceTo(this.getPos(entity2)) <= 12.0) {
                                Item item = Items.TOTEM_OF_UNDYING;
                                Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
                                itemFilterEntry2 = new ItemFilterEntry(item, false, false, null, 14, null);
                                break;
                            }
                        }
                        if (!entity.isAlive() || MovementUtil.INSTANCE.getServerPos().squaredDistanceTo(this.getPos(entity)) > 144.0 || !(entity instanceof EndCrystalEntity) && !(entity instanceof TntEntity) && !(entity instanceof TntMinecartEntity) && !(entity instanceof CreeperEntity) || !(this.getHealth() - DamageUtil.getExplosionDamage$default(DamageUtil.INSTANCE, this.getPos(entity), null, 0, false, null, false, 62, null) < this.getElytraTotemThreshold())) continue;
                        Item item = Items.TOTEM_OF_UNDYING;
                        Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
                        itemFilterEntry2 = new ItemFilterEntry(item, false, false, null, 14, null);
                        break;
                    }
                }
                if (this.getOnAnchors()) {
                    for (int i = -6; i < 7; ++i) {
                        block3: for (int j = -6; j < 7; ++j) {
                            for (int k = -6; k < 7; ++k) {
                                if (!Intrinsics.areEqual(Globals.getWorld().getBlockState(new BlockPos(i, j, k)).getBlock(), Blocks.RESPAWN_ANCHOR)) continue;
                                Item item = Items.TOTEM_OF_UNDYING;
                                Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
                                itemFilterEntry2 = new ItemFilterEntry(item, false, false, null, 14, null);
                                continue block3;
                            }
                        }
                    }
                }
                if (!this.getOnMace()) break block15;
                List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
                Intrinsics.checkNotNullExpressionValue(list, "players(...)");
                Iterable iterable = list;
                for (Object t : iterable) {
                    AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
                    Entity entity = Globals.getPlayer();
                    Vec3d vec3d = INSTANCE.getPos(entity);
                    Intrinsics.checkNotNull(abstractClientPlayerEntity);
                    if (!(vec3d.distanceTo(INSTANCE.getPos(abstractClientPlayerEntity)) < 12.0 && (abstractClientPlayerEntity.getVelocity().y < (double)-0.1f || abstractClientPlayerEntity.lastY - abstractClientPlayerEntity.getY() > 0.5) && (Intrinsics.areEqual(abstractClientPlayerEntity.getMainHandStack().getItem(), Items.MACE) || Intrinsics.areEqual(abstractClientPlayerEntity.getOffHandStack().getItem(), Items.MACE)) && !Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getPlayer()) && !Friends.getIsHomie(abstractClientPlayerEntity))) continue;
                    v9 = t;
                    break block14;
                }
                v9 = null;
            }
            AbstractClientPlayerEntity abstractClientPlayerEntity = v9;
            if (abstractClientPlayerEntity != null) {
                Notifier.INSTANCE.h(true);
                Notifier.INSTANCE.j().reset();
                Item item = Items.TOTEM_OF_UNDYING;
                Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
                itemFilterEntry2 = new ItemFilterEntry(item, false, false, null, 14, null);
            } else {
                Notifier.INSTANCE.h(false);
            }
        }
        return itemFilterEntry2;
    }

    private final ItemFilterEntry getWantedItem(Item item, long l, boolean bl) {
        int n = (int)(((l = ab ^ l) ^ 0x5EA3395407C9L) << 48 >>> 48);
        ItemFilterEntry itemFilterEntry = null;
        if (this.getBindSwap() && this.getSwapButton().getKey() != -1) {
            if (this.getSwapMode() == OffhandSwapMode.CUSTOM) {
                List list = this.getSwapItems();
                if (!((Collection)list).isEmpty()) {
                    int n2 = (list.size() - b - 1) % list.size();
                    boolean bl2 = 0 <= n2 ? n2 < list.size() : false;
                    if (bl2) {
                        ItemStack itemStack = (ItemStack)list.get(n2);
                        Item item2 = itemStack.getItem();
                        Intrinsics.checkNotNullExpressionValue(item2, "getItem(...)");
                        String string = itemStack.getName().getString();
                        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                        itemFilterEntry = new ItemFilterEntry(item2, true, false, string, 4, null);
                    }
                }
            } else {
                char c = (char)n;
                int n3 = this.getSwapButton().getKey();
                if (KeyboardUtil.isKeyPressed(c, n3) && swapTimer.hasPassed(250)) {
                    swapTimer.reset();
                    itemFilterEntry = switch (Offhand$WhenMappings.a[this.getSwapMode().ordinal()]) {
                        case 1 -> {
                            if (Globals.getPlayer().getOffHandStack().isEmpty() || Intrinsics.areEqual(item, Items.SHIELD)) {
                                Item v3 = Items.PLAYER_HEAD;
                                Intrinsics.checkNotNullExpressionValue(v3, "PLAYER_HEAD");
                                yield new ItemFilterEntry(v3, false, false, null, 14, null);
                            }
                            Item v5 = Items.SHIELD;
                            Intrinsics.checkNotNullExpressionValue(v5, "SHIELD");
                            yield new ItemFilterEntry(v5, false, false, null, 14, null);
                        }
                        case 2 -> {
                            if (Globals.getPlayer().getOffHandStack().isEmpty() || bl) {
                                Item v6 = Items.PLAYER_HEAD;
                                Intrinsics.checkNotNullExpressionValue(v6, "PLAYER_HEAD");
                                yield new ItemFilterEntry(v6, false, false, null, 14, null);
                            }
                            Item v7 = Items.GOLDEN_APPLE;
                            Intrinsics.checkNotNullExpressionValue(v7, "GOLDEN_APPLE");
                            yield new ItemFilterEntry(v7, false, false, null, 14, null);
                        }
                        case 3 -> {
                            if (Globals.getPlayer().getOffHandStack().isEmpty() || Intrinsics.areEqual(item, Items.SHIELD)) {
                                Item v8 = Items.GOLDEN_APPLE;
                                Intrinsics.checkNotNullExpressionValue(v8, "GOLDEN_APPLE");
                                yield new ItemFilterEntry(v8, false, false, null, 14, null);
                            }
                            Item v9 = Items.SHIELD;
                            Intrinsics.checkNotNullExpressionValue(v9, "SHIELD");
                            yield new ItemFilterEntry(v9, false, false, null, 14, null);
                        }
                        case 4 -> {
                            if (Globals.getPlayer().getOffHandStack().isEmpty() || Intrinsics.areEqual(item, Items.TOTEM_OF_UNDYING)) {
                                Item v10 = Items.PLAYER_HEAD;
                                Intrinsics.checkNotNullExpressionValue(v10, "PLAYER_HEAD");
                                yield new ItemFilterEntry(v10, false, false, null, 14, null);
                            }
                            Item v11 = Items.TOTEM_OF_UNDYING;
                            Intrinsics.checkNotNullExpressionValue(v11, "TOTEM_OF_UNDYING");
                            yield new ItemFilterEntry(v11, false, false, null, 14, null);
                        }
                        case 5 -> {
                            Item v12 = Items.TOTEM_OF_UNDYING;
                            Intrinsics.checkNotNullExpressionValue(v12, "TOTEM_OF_UNDYING");
                            yield new ItemFilterEntry(v12, true, true, null, 8, null);
                        }
                        default -> {
                            Item v13 = Items.PLAYER_HEAD;
                            Intrinsics.checkNotNullExpressionValue(v13, "PLAYER_HEAD");
                            yield new ItemFilterEntry(v13, true, false, null, 12, null);
                        }
                    };
                    i = itemFilterEntry.getItem().getDefaultStack();
                }
            }
        }
        return itemFilterEntry;
    }

    private final float getElytraTotemThreshold() {
        return Globals.getPlayer().isGliding() && MovementUtil.INSTANCE.Z() > 15 ? this.getTotemElytraHealth() : this.getTotemHealth();
    }

    /*
     * Unable to fully structure code
     */
    private final boolean shouldRightClickGap() {
        if (Globals.getMc().crosshairTarget instanceof BlockHitResult) {
            v0 = Globals.getWorld();
            v1 = Globals.getMc().crosshairTarget;
            Intrinsics.checkNotNull(v1, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
            v2 = v0.getBlockState(((BlockHitResult)v1).getBlockPos()).getBlock();
        } else {
            v2 = Blocks.DIRT;
        }
        var15_1 = v2;
        Intrinsics.checkNotNull(var15_1);
        var14_3 = var15_1;
        if (this.getRcGap() == OffhandRcGapMode.OFF) ** GOTO lbl-1000
        v3 = Globals.getPlayer().getMainHandStack();
        Intrinsics.checkNotNullExpressionValue(v3, "getMainHandItem(...)");
        if (ItemUtil.isSword(v3) && Globals.getMc().options.useKey.isPressed() && !(Globals.getPlayer().getOffHandStack().getItem() instanceof ShieldItem)) {
            v4 = true;
        } else lbl-1000:
        // 2 sources

        {
            v4 = false;
        }
        var15_2 = v4;
        var16_4 = var14_3 instanceof BlockWithEntity == false && Intrinsics.areEqual(var14_3, Blocks.CRAFTING_TABLE) == false;
        return var15_2 != false && (var16_4 != false || NoInteract.INSTANCE.getEnabled() != false);
    }

    private final float getHealth() {
        return Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount();
    }

    private final int floorMod(int n, int n2) {
        return (n % n2 + n2) % n2;
    }

    private final List getSwapItems() {
        List list = new ArrayList();
        Object object = new String[]{this.getSwap1(), this.getSwap2(), this.getSwap3(), this.getSwap4(), this.getSwap5(), this.getSwap6(), this.getSwap7(), this.getSwap8(), this.getSwap9()};
        object = CollectionsKt.listOf(object);
        Iterator iterator2 = object.iterator();
        block0: while (iterator2.hasNext()) {
            Object t = iterator2.next();
            String string = (String)t;
            if (!(((CharSequence)string).length() > 0) || Intrinsics.areEqual(string, "Air")) continue;
            if (Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getName().getString(), string)) {
                ItemStack itemStack = Globals.getPlayer().getOffHandStack();
                Intrinsics.checkNotNullExpressionValue(itemStack, "getOffhandItem(...)");
                list.add(itemStack);
                continue;
            }
            for (int i = 0; i < 36; ++i) {
                ItemStack itemStack = Globals.getPlayer().getInventory().getStack(i);
                if (!Intrinsics.areEqual(itemStack.getName().getString(), string)) continue;
                Intrinsics.checkNotNull(itemStack);
                list.add(itemStack);
                continue block0;
            }
        }
        return list;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap();
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.OFF && INSTANCE.getBindSwap() && INSTANCE.getSwapMode() == OffhandSwapMode.CUSTOM && INSTANCE.getSwapGroup().isExtended();
    }

    private static final boolean settingVisible15() {
        return INSTANCE.getHotBarSwap();
    }

    private static final boolean settingVisible16() {
        return INSTANCE.getRcGap() != OffhandRcGapMode.OFF;
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getOffhand() == OffhandOffhandMode.GAPPLE;
    }

    private static final boolean isTotem(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return Intrinsics.areEqual(itemStack.getItem(), Items.TOTEM_OF_UNDYING) && !itemStack.hasEnchantments();
    }

    private static final boolean isSameStack(ItemStack itemStack, ItemStack itemStack2) {
        Intrinsics.checkNotNullParameter(itemStack2, "it");
        return Intrinsics.areEqual(itemStack2, itemStack);
    }

    private static final boolean isSameItem(ItemStack itemStack, ItemStack itemStack2) {
        Intrinsics.checkNotNullParameter(itemStack2, "it");
        Item item = itemStack2.getItem();
        ItemStack itemStack3 = itemStack;
        Intrinsics.checkNotNull(itemStack3);
        return Intrinsics.areEqual(item, itemStack3.getItem());
    }

    /*
     * Unable to fully structure code
     */
    static {
        block31: {
            block30: {
                block29: {
                    block28: {
                        block27: {
                            block26: {
                                Offhand.ab = ZkmKeys.a(3883297823962542370L, -5440837163952077615L, MethodHandles.lookup().lookupClass()).a(97205464518645L);
                                var31 = Offhand.ab ^ 39147362590285L;
                                Offhand.hb = new HashMap<K, V>(13);
                                Offhand.zkmSetFlag(null);
                                var22_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                                v0 = SecretKeyFactory.getInstance("DES");
                                v1 = new byte[8];
                                v2 = v1;
                                v1[0] = (byte)(var31 >>> 56);
                                for (var23_2 = 1; var23_2 < 8; ++var23_2) {
                                    v2 = v2;
                                    v2[var23_2] = (byte)(var31 << var23_2 * 8 >>> 56);
                                }
                                var22_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                                var29_3 = new String[163];
                                var27_4 = 0;
                                var26_5 = "\u00c3\u00b3\u00b1\u0003K\u00d4\u00b0\u00f4I\u00ee\u00ae\u008a\u00b0\u00d8\u0015\u00ee}\rk\u001fJ\u00b2z\u00b20A\u009f\u00f4\u00b8r=\u001a\u0081\u008aM\u00e0L\u009d\u009e/Ck\u00aeAJ\u008b\u00e3\u0082\u00e5/ \u0089C\u00a0\u0019\u00b0m\u00efpe\u0018\"\n\u008d|Q\u0095Pm\u001b\u00ca\u00ee[ >\u00e6\u00a4ujY&G\u00d5\u00fa9z8gQ\u00a9\u00cb\u00c9\u00fdl\u00b6\u008bK\u00f7]4\u00aeH%{\u00df\u001b \u008e\u00bb\u00921o\u0018\u00dc\u00bf\u00c9g\u0089\bxr\u0089\u00d3\u00b0u\u00f0?\u00b9\u00d3\u0096t\u00feD\u0016c@\u0010T\u00d5\u0018\f\u00c2\u00ad\u00f8\u00d8\u00d67\u00cb\u001f\u0011+\u00ef\u008f\u0015\u00dcA\u00f0\u00fb\u000bS\u00f4\u00f6\u00d3\"8\u00f1Z\u00e1\u00cc:\u0097\u001e\u00aa\u00d3\u00c3\u00d4\u00b5\u00d9\u00d4\u000e\u00af\u00e7;\u00f6K9<\u00de\u00f8\u00a6\u00ef\bg\u0005\u009d\u0099tL<\u00bf\u001a\u00af;x\u00b4\u00de\u00a4\u001718B\u00c5\u0086\u00ec\u00c55\u0095\f\u0089(\u00bd(\u009aL,u\u0014U\u00fa\u008b\u0004\u00ddz\u00ce\u0086\\b\u0011&\u00a5\u00ca\u00e1\u0094\u00d9]mi\u00c8\u00cd9\u00f2\u009b>;[\u00b2*\u00fc\u00f4\u00f2\u0095\u00e4 [\u00bcg\u00a9\u0098\u00ff$\u00f6\u00fa\u00faa*,\u00b7%\b\u00d0\u00a4\u00d7\u00f4 -\u00c5C7>\u00aa\u00e5\u00cf\u00b4\u00aa\u00f7\u0018\u0084gX)\u001a\u00bew\u00df\u00c3\u001d\"\u00956\u00ba\u00e9\u00a4\u00bfSX\u00ec^Nq}\u0010\u00b5\u001d\u0001l\u00cd)\u0098\u00b97\u009f\u0087\u00ad0\u0091W=\u0010\u00ca\u008c\u00dbh\u00f6\u00f1\u00b9&\u00f1\u00b4\u0095lBs-\u00ba >\u00d7\u00f0*\u00ad\u0000\u00c7\u00f8uQ\u00ffl\u00e4\u0011G:\u00a4v~\u0081\u009d\u00ed\u00da\u001d\u0085z\u00dc\u00d0\u0098\u00eb\u00f4| rlN\\oI\u009e\u00b8>)\u00e8\u008e\u0014\t\u00cd\u00fa\u0016\u00a7\u0017b\u0081\u0017'J\u00f2\t\u0084\u00bb2\u00ea\u00d25(\ry_\u0019\u00b9\u009e\u00ecd\u0091\u00ac\u00a6\u009e\u00ee\"\u0012^\u0012\u00e12\u00dd\u009c\u0099VR%x\u0098w\u00f9\u00f1\u008e\u00d1q\u00ef\u00a2\u000f\u00cd\r\u00da\u001f\u0018\u00d0b\u001f\u009c\u00bd=\u0084\u009e\u0003\u00fbRe\u00dc\u00ea\u007f\u00f6\u0002\u0087\u008e\u0007\u00e1\u0012\u008f\u008a\u0018\u00c6)\u00e31~\u00fc_\nBMR\u0018\u00a9\u00cf\u0097\u00c9\u000f\u0081+o\u00cb_\u00c1*\u0018\b\u0087\u009c+m'\u000e\u00ca\u00ed\u00a4\u001ey\u00f9\u0015\u001e_\u00f5:D|K\u00157\u00c2(\u00c9\u00c3\u00b2E~hi\u00c7\u0086[\u00c1\u00bf\u00c9\u00e6c\u00ea\u0096\n\u00e5\u0097\u00946\u00f8\u0016R\u00b7_\u00abE\u0011\u0091\u00f8\u00ad\u00a5\u00b6\u0090%w\u00ff\u001c\u0010\u00ff];\u00e0\u001cJ\u00ad\u00b1\r\u0006Y\u00b7\u00bbR&\u00bc(\u00b9\u00ee\u00ac\u0099'\u00a5t!\u0015kM;Gf\u0082n\u00aa\u00ab\u001dFp\u00b5\u00c5\u00c0\u00b2)\f6H\u00a6*M\u00cb:\u009f\u00c8\u0091\u00816\u00ba0\u0099\u00c9_\u00b82tah/\u00d7\u00fbZ\u0018\u009da\u00a3\u00a6\u00b0\u001e#k\u00db\u00c3\u0002N\u00fa\u001c\u0088\u0013\u00ef\u0006J\u00ca\u00ea|}\u00c2\u00e7\u00ee\u0016\u0080\u0005\u00af\"\u00e1\u00f4\u00dcW(7\u00d1\u00c6\u00e4V\u009a\u00b1\u00da{\u0082\u0097c\u00a9ZF\u00daN\u00d3\u00a0\u00ffi\u00b7t\u00da\u00f6\u0088sN\u00a1\u0082fk,Y\u008ewkw\u00d5v \u00c9\u00ca\u0017 \u00bb\u00e0\u0087h5\u00d1\u00a66Qj\u0007\u009f\u00cf\u00ea\u00f6q\u0003$\u00c6w\u00c2y\u00de\u00c6n\u0017\u00d2\u008e nI\u00a5x\u00dc\u00f9\u0005uw{&\u0012\u00f0\u00f6\u00e1\u00cc\u00b4A<\u00bb\u00da+\u00bc\u0018\u00d1\u0086\u008a\u00c7\u00aekf\u00b8(\u00be\u0013%\u0014\u00f4\t\u00d7YA0V\u0083\u00ddW0\r\u00025\u00bf\u008eR\r\u00c9.\u000b\u0086v\u00a3K\u00f6G\u00cd\u00ab\u00fe\u00e5\u00ff\u0087\u00da\u00fcE\u0010\u00c0\u00d57\u00aa\u00b2s\u007f\u00d4\u0014\u009f\u00c1\u00fcjX\u0004\u00c2(\u009d\u00ccY\u00d9\u00cf|y\u00deP\u00ab\u0097Y\u00aeR\u001b\u00d6K\u00f4\u00aa\u0096\u0015_D\u0097!X \u00ec\u00fev\u00d9i%\u00b8\f\u00a2=\u007f\u00b4|\u0018u\u0081q\u00b78\u0003\u0002\u009cf\u00ac\u00d4\u0091\u00dcN\u00be\u00f3\u001e\u000fh\u00b2\u00f0\u00c6\u00cbC\u0010\u00d48\u00f6\u0001YGZ\u00e1\u00ba`_\u00e3\u00ba\u00cfG\u00a5 qW\u00a98\u001f-\u001f\u00db\u001b\u009d\u00baK\u00c9\u001d?\u0084\u0085\u00aa^9ty\u007f\u0006jR\u008c\u0082\u00fc\u00f2\u00a8\u0088 (\u00dd\u00f0{\u00b4i\u00a1\u00cd\u0014\u00b4\u0011z\u00cc~\u00e2\u00e1\u000e\u0002\nB0\u00cb_A]MS\u00e3\u00d4\u009c%\u00ca\u0010XEx\u00e1\u0097b\u0092\u0093/\u00a2\u00a9L;H\u009ab \u00161\u0080\u00ff\r\u0011\u00e9z ]K\u001e\u00e4cQ\u009a\u00be\u00da\u00e8\u00f1\u00b2r\u00b8\u00c0\u0089\u009a\u00bfK\u00c4S,\u00f1(\u00cf\u00ee\u00fa\u0087\u000ew\u008d17\u0083\u00ccF/\u00a6\u00ebA\u00af\u00de\u00e9\u0016\u00a0\u00c64\u00ad\u00ad\u00a0;.\u0005\u0016T0\u0002\u00b6cTT\u008c1d\u0010\u00bf%\u00a4\u00b0s\u00c4\u00b1?7\u00e3{\u00be\u0098h*y(\u0085}\u00fc\u00f9\u00bb#+\u00e89P\u00f0\u00e8j\u00b3\u00e5\u00a4v{\u0091-\u00bc\u007f\u00d08)\u00cf\u00e0\u0003\u00f6\u00e9\u00ae\u008aq\u00d2+\u00f7\u00cb>@\u00c38x\u00ee\u00e6}\u0092\u0084\u00c1\u0085\u00f8,V\u00a7\u00f4mw^\u00c4\u00ad\u0015&V\u00a1o\u00a7(\u00f0A\u00c3\u00a4\u00ddA\n\t\u00e8\\\u00b1\u0096\u008e\u00d8\u00c6\u0017\u00d7:\u00be\u00c2\u0083\u0081\u00bd0\u0004\u00fa\u00b4x2e\u00fe \u0095Dm\u0011<\u00b6\u00af\u0010\u00bb\u00c3\u0016q\u00b20?\u00df\u0089\u0015\u001eT\u00ce\u00fc`\u00b1\u001a\u008d\u00eb\u00bf\u00d3$\u0082d0\u00b3\u00ec3\u00e6\u0013Qb@\u00bb\u0081G\u00e4J1\u00ef\u0089\u00df\u00c0\u00d0\u00a8c\u001c\u00da%\t\u000f\u001e\u0018\u00c6`\u0004\u00c0eryR\u00d8\u00d4b7\u00a2\u0016\u008d6\u00ae\u00eb\u0095\u0007 6\u009c\u0081\u0087;.\u0097;\u00aa\u0002\u00e2\u00ae`\u00a2\u00bd\u008383\u0087#\u00bak\u00914\u0006\u0081\u0007({\u00c3\u0082\u00d9\u0010O\u00af\u00f8\u00ef\u00a3\u0006v\u00d4\u00f9\u00fb\t\u009d\u0018\u0019\u0081\u00a4 -\u00e0\u00d7\u00ee\u0096=\u00c3\u0086\t\u00e045\u0089\u00d4i\u00e3\u00de/\u001b?\u00a9\u00bbM\u0002\u009dAq\u0018\u00b1\u00f5Y@\u0010Q\u0087W\u00f5\u00b3\u0018\u0096\u001a\u00f3Z\u00ee\u00cabj\u0012\u00b3\u0018/wu\u00be_\u00a0\u008c\u00e5\u009e\u00e1\u00f7\u00d5\u00f0\u0094Y\u00ee\u000fL\u00ed\u0014\u00d3\u00f1\u00ee\u00c6\u0010\u00c7Q6\u0081\u00e0\u00f5\u000e\u00b1P\u0093d\u0095\u00a1V\u0002]0\u001e%v&\u00e6\u0002\u001e\u00a7\u009f\r\u0019\u00a4I\u0013*EcUaG|\u00cb\nS\u000b\u0096\u00c6\u00c35\u00030W\u0003\u0007\u00b5>\u000f\u00cb\u00fa\u00deh3%L\u00af\u00a8\u00a2\u00f5 5\u00d3\u00ea\u0013\u00d9\u00a9\u00d7\u00b5L\u00b8\u00a3\u00bb\u0015NP\u00dbQ\u00a1\u00ca\u0094+\u00d0?T\u00ef\u0015>\u00d1\u00b2\u00ec.W\u0080\u00edbA\u00aa-\u008c\u00e1\n\u009f\u00c9\u00ad|iiU\u00d4>(&``\u00af\u00beA\u009e\"\u00bd\u00dc 1\u00e7k\u009eJ\u00d6^\u00da\u00f0\u00bdM\u008eC\u0016\u00d46\"3\u00bc\u00a8\u00c1\u00e0!6=\u00ecP\u00c0\u0087\u00c4R\u00c0\u0016\u00dd`\u0094f\u008f=\u009a\u0087\u00ab}\u00f3\u00f9W\u008fEF\t9\u000fyw\u0084da\u0015\u0013)\u0090\t\u00ae{\u001e\u00af\u0010@\u001e\u0002!;\u00fa\u0091\u0018\u00f7\u0018\bgw5\u00c8\u00c6\u00d9\u00a1;\u00fd\u0006\u0019\u0012\u008f@\u00a5'E\u008c6\u00e1\u00c00iSPC\u00a4\u00d2&\u00eaU\u00afZ\u00af\u001fQ\u0083}%=\u0082/\u00c4\u00d7\u00e7\u00db\u0017\u00e6\u00cb\u009dP\u008d\u0089\u0007\u009d&u\u00d2\u00a3\u00e6\u00a5j\u00c3\u00e9\u001er\u00a7j\u00b6=\u0010\u0011p\u00ab]\u00c4\u00f0\u00dc1\u000er\u00a5J-l\u00beE \u00ec\u00ae\u00c3\u009eB\u00e8\u0085\u00ea\u001dn\u00bc\"\u00a4:\u00c4\u0017u\":\u0089\u00e7\u00ffW\u009d\u0002\u009c\u0093,\u0083\u00b6\u00cf\u00ea(\u00f6\u0012=\u00c4B\u00dc\u00e2v\u00ed\u00e2&\u000eZV\u00c7\n\u009e\u009e.\tha\u0013\u00fdO\u00ebZC#\u00bb\u0018\u0091\u001dL\u00fe\u008e\u00f9\u00acna 9\u0003\\\u00db%\u00d0Kjtp+f+\u00c0}yr\f\u008e\u008eo\u0018f\u0013k\u00cc\u00d7\u0014nS\u0098\u001a\u00106\u000f\u00a5~E(\u009d\u00a9!\u0018d\u00f7$\u00dd\u0006H\u0010\u00ce\u00db/{\u00f9%5\u00f3\u009e\u0083i\u009a\u00bfw\u00ccm 2\u0099\u008e;\u00a4\u0002\u0086\u009e\u001ft\u00e2&\u009d\u00aa\u00a2\u0013\u00f1\u0092!\u00a0\u0082\u00c4x]\u0004\u0013u\u00eaX\u00d7a\u00dc\u00102\u00b5\u00a8\u0086\u009f\u00cf\u0082'\u00c8\u00f7`\f\u00ae\u00b4g^0\u00cd?\u0082\u00b1\u00f4\u00c3\u00cc\u009d\u00f9tU\r`\u0016\u00a4+\u00fb.\u007f\u009aS\n\u00d3\u0005n\u00d0\u0003\u00da\u00a2vBGt\u0000Dz\u0085b\u001b~S\u0098yF\u00b24t\u0011\u0010\u0001\u00d0\u00dc\u00cc\u00a9\u0003\u00det\u009d\u00a7\u00b1\u00e0\u00ef\u008d\u0015\u0018\u00101\u00f0yc\u00b8\u00f8=\u000f\u00dc\u00c0\u0003\u00be\u0016v\u008f\u009b !\u0083d\u0014-\f8\u000e8\u0005\u0080\u001b\u00d8\fX+e\u001b\u008d\u001d\u00ff\u0095F\u00c6\u00c2![\u008d\u00dc\u009e60\u0010\u009f\u00a6\u00ef\u00ad\t\u000e\u00c6\u0087\u00c2\u00975\u000e8\u00f6\u00fc+ \u00aas?\u00e2\u00b9u\u00daW\u00dfxpM\u00cc\u0085Da^q$[\u00d5\u0007\u00ea\u00df\u00a1\u009a+\u00e8%\u00ef\u009e\u008a\u0010\u000e\u009c0\u00ed\u0093$N\u00bc\u00e6\u00c7\u00161\u00e1\u00f5]\u0086\u0018z\u00ec\u0019\u00e0\u0080\t\u00c7l\u0092\u00eb\u00f5\u00ed\u00fc.\u008a\u00b61\u0016\u00a21\u00c6\u00b31F(D\u001c\u0087\u0099\u00b6\u0088\u00ef\u00ec\u00f0\u0089\u009d\"\u0082RL\u0082\u00af\u0019\u00daA\u00f7\u00136\u00eb\u008e \u0007\\\u00ddD0\u00e1J\u00cb$\u00bdg/#\u001d(\u00f8\u00d0~^7d|3;\u00d2\u00c4h##\u00d2\u00d5\u001a\u00ec0\u00e2_g\u00d4\u0084;;\u0014\u00d6\u00e4_\u009f=e!\u00d2\u0098\u00e7P\u00cb\u001e\u0010\u00e5O\u001d%\u000b\u001f\u008e>\u00d8`|+L\u00e2\u0013\u00d5(X\u0085\u00eb\u0089N\u0086d\u00c7\u00ccP\u00cc\u00a6%j\u00cb\u00e3$p\f\u00e7t\u00f8\u00b0\u00d0\u00d5\u00e6\u00acX\u00d4;*\u008e\u00dfs\u00ce\u0096\u00e8\u008d\u00baH \u00d2\u00e4o\u0005\u00bb\u00ab\u0093\u00ac\u00c1Q\u00bf\u00a4\u00e0-\u0091\u008a\u009b\u00a6\u00ce\f\u00d7\u0093\u00ae\u00aa\u0083u\u00cc\u0019A\u00bc!\u00af0k\u00a1\u00a5\u00ae\u00b8\u00bc z\u0017\u001f\u00d6\u00a4\u00f4\u000eT\u0012\u00872\"\u00ffL\u0018\u001c\u00e0 \u0004\u00880M2P\u0094P\u00a5\u0016>\u00cc\u0096\u0080\u00e8v_\u0016:b\u00e99o\u0010\u00ba\u00a2\u00a7\u00a7\u00d2&\u0092%\u0013\u0095{\u0093D\u00b2\u00db\u00808\u008b\u00e3_\u0007iZ\u00dc\u0011\u0081@\u00f5\u00d7\u00c9)O\u00d5&\t\u00b6\u00a0l\u0004\u00b6\u0085\u00f6\u00c8\u00c1a\u00ea\u0082a\u00ff\u00b96\u00dd\u00893\u0016\u00f6#\u00e6\u00e6\u00e99\u00d4^\u00dc\u00c9\u001d\u00023\u00ed\u00e4\u0086:v(\u00de1\u00a1\u0087\u00e4\u001a\u00c6\u00d3|\u0098\u00b2\u00c5ACD#\u0088\u00ae\u00ed2\u00b7\u00ef\u0015\u001eF\u009a\u00d4\u000b9p\t\u00ce\\\u0084\u00b4\u00f2\r\u00d5\u0091\u00f1\u0018?\u0080z\u00845\u0007\u00903\u0012\u00a1r\u00a0Sw+y+\u00c16\u00d9\rO\u00d8\u00a70\u001d\u00dcR\u008d\u00c5\u008b%f\u0096r5c\u00d4F\u00a3\u0014P\u00fe\u00d9f\u00ca\u00d1\u008cgA,\u00b4\u00d4\u00a20|\u0092I_I\u00b37;8\u0007/[8\u00ef\u0000\u0012Y\u00db a;r\u0081\u00e8\u00acu\u0081\u00b9V\u00b7X\u00ddx\u00e8>a\u0099r\u0083\u00f3B\u0090\u0093+\u00c9m--C\u00b3\u00ab\u00a0\u008dr\u00de\u0092\u00e9\u00d2\u00c6K\u0013c\u00c8 \u00cb\u0016\u00a2\u00afG\u00ca\u009b.F\u00e1\u009d\u00ec\u00fcq^\u00ff\u00e6\u00ac\u0092\u00ed\u00f4\u00b3\u00ff6\u0086N\u0005\u0098?\u00b8\u00f9\u00bcz\u001fX\u009cFo\u0088t\u00a3t\u00d6q\u00da3]C\u008f\u00f8\u00e5/\u00f4r\u009c\u00a0\u0012um\u00d7<\u00a1\u00a2 \u00e0fZ/\u00ba\u0082\u00f4u\u001e\u00c0P\u000f)\u00fbm?/\u00ca\u00b70\u00b3\u00b6B\u0095\u00dd\u001c\u001d\u00ad\u00c2\u009d\u00da:\u0017\u00bf\u00c0\u00a0!W\u0017\u0089\u00cb\u00c6\u00dc\u00e4\b\n\u001b\u00dc\u009fE=\u0091\u00fa\u00a7\u00e2r\u00cb\u00a8\u00ca\u00f1\u00edm\u009f\u00bdp\u0092\u00a1ziYfs\u00e0\u00f0\u00c5\u001b\u00dc\u00ef\u00e2\u00ae\u00e9V~X(\u0004\u00cd\u00c2\u00ecp\u00915\u00c781U\u00a0 \u00c9\u0006\n\u00d5{N{h\u0006\t\u00dfMQ\u00daY\u00f9|3\u00c6P\u00d5\u008e\u00d4#s\u00b0\u00fd\u0010\u00c5=\u00ee\u00be\u0085\u0085\u0004\u001c\u00f3q&gs\u00cd7\u00c2\u0010\u0005\u00fb\u00eed\u00b1#\u00aa\u0019Kn\u0007\u00d9[\u000e\u008a4@\u00fc\u0010}\u009e\u0013\u00a5\u00f5\u00e4k\u00e7\u00a3\u00c5\u00ee<\u001c\u00df\u00f0u\u001b\u00da\u0014G\u00f0\u00c7\u00fcO\u00ce\u009f\u00a1K'RtM\u0016\u001c\u00111\u00fe\u000e\u0082r}p2\u00b7Q\u00a7I\u00a6JP\u00e3\u0011{q\u00eb\u00f0\u00fa\u00e4t\u00b6f\u00d0 \u0019\u00e8\u00acn\u00b3\u0015\u00f8\u00eb\u00ef\u00cdx\u00ad\u00fa\b+\u0018\u0091]\u00df\u0004\u00b4\u00ab\u00c3\u008e\u00bak\u00d0\u00c0\u00fc0i=@e/\u000e\u00aa\u00f1Q\u008c\u001c+\u00a1\u008ee\u00c8\u0082\u0002\u00a02h\u0087PwM\u00d3\u001d\u00a1c\u0084\u009d:m2\u00bd\u0098\u00d7\u00f29\u001c\u00b8y\u00e4:\u00f5\u00f40\u00efL\u00c7-9\u00a6\u0017~g\u0094y\t\u00f7\u00e3\u0002\u0093\u0019\u00c4\u00c6\u00ad\u0010\u00bf\u0082\u00e1\u008e\u00cc!9\u00d2\u00a9kk.n\u0000h\u0085(\u00dd\u00d1\u00fa\u0089]\r\u00beO\u00ae\u00ba\u0013J\u00d0\u00b6\\\u00d2w\u00d7\u00d7$\u00c2\u0096\u00af\u00b6\u00ea\u000bO\u00b8Q\u0001d^e\u00cb\u0083\u00e5\u00fd`\u00aa\u00e4 ^h\u0005\u0083\u00fcy%\u0086\u0097p[N/\u00b4/\u001e\u00b0\u00a7\t\u0082\u00cdLy\u0019\u00c7\u00f4\u001f\u00cf\u00cd2\u00a1Q \u00d0QM\u00eb\u00b1'\u008fM}Y\\\u0095JO\u001e\u0080\u008d\u00e1\u00f5c\u0018\u00f2*\u00f0\u0081\u001d\u0006U\u00134\u00cd\u00cc\u0018)\u0018\u00e5\u00d8\u00a7\u00ee\u00ef\u0090\u00e2x\u009b\u00c1\u00b8\u00c4\u00c1ee\u00a6\u00e0\u00c3\u001fdj\u000e\u0018\u0007\u00aa\u0084\u00c0\u001d\u00cc(\t\u009a\u00ec\u00be\u00aam\u00c3\u00e6\u0002\u00d8\u0014\u00e6D\nd\u0080_ Z\u00aa\u00845\u00cf\u0011\u00af,\u00ae\u0091\u00d5\\6\u00e0\u00a64\u000f\u0015\u001d\u00a5\u00d9\u00e0p\ngI\u00dc\u00a1\u00df\u00c5\u00f3\u0016(\u0001\u00c5\u00f3\u00e2\u00029z\u00b9\u00c0\u00f7p\u001eg[\u0092\\\u0088\u00abN\u00cf\u0000\u00dex\u00ad2KA\u00f8\u00e5'\u00a1\u00169?\u00e3\u0012\u0018\u00d1.\u00a8\u0018<\u00bd\u0080-\u00de_5v\u00bb\u00d7\u009b \u0089U\u00d0\u008d\u00c17]\u009e\u000e\u001e\u00db7 w\u001f\u00dc4vv1W\u00b1\u00a5\u00a0`^\u00ff?.!\u008ff\u00a1\u00da1\u00e5\u00b1\u0082\u00b6y\u00de\u001f\u0088]\u00bd \u0085[\u009cc0\"\u007f\u00ba\u00a8N\u00e2\u008b\u001a)9\u009da?\u00bb\u00b4\u00bf\u00b2\u0098\u00e3\u00c12H\u008b;\u0015\u00f4\u0084 7\u00ee|X/k)\u0083\u00a3\u007fj\u0015\u00821V\u0096\u001a\u00b2YqU\u00e9fL\u00df\u00cc\u00fbF\u008d \u008ax8}B\u00deY[\u00b0\u007f\u00ed@N#\u00d5\u000fl'\u009e\u0011\u00ea\u0016\u0003{\u0095\u000f\u00b7\u00ef\u00f0Y?\u009b\u008d\u0089,\u00d9iuf\u00ed\u0002\u00e1\u0083f\u008e\u00bcH\u00d1\u00df\u00eb7\u00fb\u00d2\u0006\u00db\u00f3s=C8\u00ec\u00b4\u009e\u00b8\u00bfM\u00ca \u00e3\u00a9\u00df\u00c5t\u00c4\u001bcJ\u009ecO\u00b5\u0097\u009f\u00d3(H\\\u00164!*\u00be{\u00af\u00fc\\\u000bZ\u00ab\u00f5NH\u00b6\u00f6\u0013\u00ec(\u00c8\u00ff\u0084\u00c4_?(\u00db,\u0010W^\u001cm'\u0000\u00ec\u0094'\u00b1\u00fe\u0092\u007fEvn\u0018Z\u00e4<\u0091+\u00c4\u00a7\u00e2<\u001f\\\u0014\u009f\u0005zQ\u00b6\u00f6\u00a0&KZ\u00ac\u00aa0:\u00cd\u00c1A$\"m&\u0016=\u00b9.\u00d3\u00ecl\u00abI\u00e4\u0097\u00c3\u00bau\u001b\u00dc\b\u00ba\u001ab\u00a3I\u009d\u00fc\u0016\u000f\u0093\u00e7\u00c2Q?t\u00a15\u00a71\u00b2\u00e3\u009e\"\u0010\u0010[\u00c7\u0092\u00e4\u00c2\u00de-\u0095\u008b\u0084\u00e8e~p\u00a0\u0018_\u00b8\u008f\u0013\u00fa\u0097\u009e*\u0087\fY<\u00d5\u00fb\u00ed\u0086\u00d1\u00a4&1f\u00b7\u00c9x \u00067\u001dKL\u0007\u00faE9\u00e3h\u0010\u00ccX\u000f\u00d3=\u00e3\u00f7\b\u00b3\u00a1l\u00c2\u00e7\u001d<\u00cfN\u009a\u0015\u0000(\u00be\u008d\u0003\u0080H\t7\u00d7\f\u00840\u00f20\u00ee@\fS\u00b6*I\u0094;blI\u00da\u00f5_\u00ee\u0096\u00b3\u001c\u0001*\u00bco\u00d1?L\u00c1\u0010\u00dc\u00e31\u00a2\u00e9\u00f4\u0085R\u008f\b\u00fb\u00ec\u00e37j\u0088\u0010G\u00f5\u00e5\u001aU8z\u00e4\u00dbQp\u000bqX\u00d2\u00de@\u00de\u00femj\u0091ED\u00fcr\u001a\u00af)\"D\u000f\u00b4m\u00dd\u00b1\u00ab?\u00fb\u001cRj\u0080\u00c2\u00c2\u00f6\u00e5\u001dV\u000b\u00fbY\u00da\u0098\u0001\u00fa\u0014\u00d0\u00a2\u0083\u00c2}\u00d9\u00f2\u00cdz'a\u008d\u0097\u00e7\u00a1D_LcIN\u009fY\u0005 \u00b2\u00d6^\u00a4\u00d1\u009bv\u00eeo2\u0093\u00ee\u00ba\u0015\u00c0p\u00de\u00f9\u008bm8\u0099?B`T\u00a7XKEj\u00b9\u0018\u00049>\u0005Hnl,\u00b7p\ne\u00dc\u00ff\u009c8^\u00806;\u00af36\u009d(\u00a3\u00960\u00aa\u00b6\u0007\u0016\u00e2\u008a&\u00b56PV\u0003\u00f2f\u008fz|\u0087\u0097\u0083\u0002u\u0018\u00d5\u00bdZ\u00a7\u00b1\u00ef\u00caQ\u00a7q\u00e8UM$\u0010\u00d4;h\u00bc\u00c8\u00d0\u00b6'\u00a6\u00aff\u00b4\u0085Q\u0099\u00e0 \u001e\u00ee\u00e5\u0099d:x\u00db\u00b3\u0007\u008c8[;@\u0082V\u00d3\u0084^\u0095\u00aa\u00bc\u00df!\u008fP5\u00930RB(\u00e9\u00b0\u00cf\u0014tesx\u0004\u00e0\u00ab\u00ab\u0004\u00e3/\u0002\u008c=\u00a8\u00a8\u00c5/|u\u00ab\u00b6~M}f<\u00f94\u00c4\u0093\u001d\u00a7?\u0084\u00b6(\u0085X\f\u0013c\u008f\u0091X\u00bb\u0016;\u00a7l\u00f3P^\u00d35\u001a\u00d8z\u00d59\u0018\u00edL35\b^\u00c2\u00ff\u00e8\u00e0IF\u00e3z\u00163\u0018\u00c5N+\u0097\u0092s\u00d4\u0006\u00fd\u00a4\u00ad2\u00b4\u00d4;\u00fa\u00a1\u00c0\u00fao\u001e\u00e0ba >\u00935\u000b\b?\u00a4$\u001b\u00ab\u00fe\u0091\u001c\u0006\u0001xy'I#Z\"\u00ee\u00f3\u00e0\u001e\u000f3\u00b2\u00fc\u00bf\u00ae \u00ff@ \u00e5g}\u00f3\u00ff\u0088\u00fef^\u00a9\u0018\u00c9\\\u009c\u00c6\u008bG\"\u0013L_\u0011\u0002p\u009c\u00ee\u00e5\u00a3\u0095 \u00fdi\u0012m&-\u00fb\u00e8bA\u00c44\u00c7%\u009d\u00e7\u00b6\u0097q\u008f\u00c9\u00eaF\u0001\f\u00ff\u00c8\u00c2B\u0000\u0002\u008a\u0010N\u00d4Z\u00f2_\u0014\u00e5\u00fcq\u00cf\u00aa\u00d7\u0006\u00e6\tC\u0088\u00eaE\u00c5f\u00df\u00ae\u00f4\u00f2\u00e9P\u00e5\u0003\u009d!\u00e6e\u007fc\u0007\u0081\u00c8\u00f9\u00c8\u00a6\u0097\u00fd\f\u0004zn<\u0091n\u00c6\u00cf&\u000b\u001cy\u00cb\u00f2\u00db\u00c7\u0016\u00f8\u00b9\u00f3E\u00bdxT\u00fc\u008b\u00f5\u0090\u00fbC\u00f0\u00fc\u008c\u00f7Z\t\u0081\u001d\u00b1\u0083\u00e3\u00e3d\u0092g<\u00d5\b\u009c\u00a9D\u0089\u00a0\u0004\u00ec\u00e4U,a'\u00bf\u0097\u00d7B\u0088F\u0090\u001b\u00b1\u0011@\u001bz\u00b1[Ym\u0001)\u00f3}W\u00c1\u001a\u0007\u0090Zr\u008d\u00dfl\u00a3\u0081\u00e1\u00bc\"\u00ec\u00f2\u00c5\u00fe\u009c\u00df\u00b7\u00fd\u0004\u00f7\u00ea]\u0090 e\b\u0096$\u00d6\u001c:\u0017;\u00e8\u0091Cqo\u0010\u00be\u0099Z\u008b\u001c\u00e1\u008cF\u0014\u00bfR\u007f\u00f1W\u009d\u0019\u00a9 3\u00c5\u0097\u00cb\u001c\u0013\u0091\u00cbj\u00b4\u00bc\u00bf96\u0004\u00f1{\u00dcXZ\u00fd\u00ad\u008d$\u0093\u00f8c\u001a\u0006\u0013v_\u0010\u00d3\u00ac\u00e3_\u0015\u00ca%\u00aa\u00be\u0085:m\u001c\u00fc27 \u00f8\u00dd\u00b5uY\u0097\u00d0szNE<\u00c2\u00b2q\u00c9\u00ed\u00ae\u009c\u00ed\u007f\u00a3W\u001a\u008f\u0084\u00fc\u00d7\u00dc\u00be\u0005\u00c3\u0018\u008a6\u001bUI\u00b1\u00bb\u0099\u00a43^ciu\u00ef\u00d1\u00ba+\u00d3\u0011\u00ca\u00c6?\u00cf\u0010\u00fc\u00f1\u0082\u00d8\u00f6\u00eb\u0017\u00b6\u00e5\u0011\u0010\u00b1\u00cc\u0089\u008d\u00c2 \u00ca[}}\u0088<\u00d4\u00b41g\u00dd|\u008dT]\u008b>X\u0092 =\u00cczNF\u00ea\u00e0\u00f8\u00f8\u008c\n\u000b\u0010;\u0017\u00b0;\u00b3\u0089\u00f1tv\u00ae\u00826Z\u00edX}8\u0014\n\u00d9K\u008d\u00d7Z\u00f3yq\u00eb\u001dc\u00ce9\u0085#\u00cd\u001e%*n\u00bfb\u00b6\u00bbN\u00a4\u00b8\u0083Q\u0097\u009fy\u00e8/y\u0089\u00d4\u00b0\u00d7\u0082\u0092KQ-\u00058\u0001\u0091}i\u00aa[\u0012\u00ac\u0018tP\u00b2\u00f3)7Mdm\u00cb\u00dea\u00bdld\u00ed'\u008e!\u00ce\u00c4\u00c5B\u00a0 \u008dur\u00ab\u00da7(\u00be\u00c4\u00a3\u009e\u00c97,~k\fI\u00f2cV?Cx\u00bd\u00e8\u00f6y\u0081!\u00dc\u00b90\u0083\f\u00bc\u00e4<\u00b2?j\u00eeG\u00c5\u00a8\u0099\u00e7r\u00a2\u00ec\u00a9dn\u0088KfM\u008d\u00e0\u00972?\u00b6\u00b9u\u00ef\u000f\u00ee\u00feB\u0019R\u0007za\u0091\u00df\u00b6\r\u00cb\u0089\u0010r\u00f7MJ\u0012\u0010w)#\u00e5\u00c2h\u0017\r\u0005\u0086 \u00e2\u00a41\u0004\u0090\u001b\u0018\u0001\u00c4\u008e\u00e2fz\u000e\u00a2\u00fe\u00de`\u0011\u00acG-\u0096#\u00fc3\u009f-$5\u00f4#\u0010\u00a7\u008cX\u0081^\u0084\u00e6\u0014\u00ba2Hf\u0085\n\u008b\u00b0 g\u00bb\u00deQ\u009a\u001d\\\u009dEM^\u0085\u0093\u0015\u00c5\u009f\u000f\u00e1\u001a\u00e7DQ\u00b1\u00bft\u00e5F\n}\u00fe\u0081\u00aa8hF\u00f8\u001a{\u00e0,\u00a0\u0084\u0095#\u00e9+C\u00b5\u00fdjw\u00f2\u00ecx}\u0001\u00d3G\u00f9\u0083\u00ed\u00f6\u00e4gQ\u00b3\u0083<I\u0002 \u00b19\u00ec\u001e\u00b3\u00e7\u001cJ\u00c5\u00ea\u00e6LWq\u0014\u00af\u0013\u00b1p!e?q\u00a4\u0010H\u00d0\u0094j\b*\u00af\u00a4a\u007f\u00bb\u0088H\u00f7~P6si\f\u001b\u00d9L\u0018\u0091i\u001d\u00fbr\u00c3x&\u00c4\u00b6\u00dd4\u0094\u00ab\u0004\u00d49\u00b95\u0012Ue\u0005\u00a5\u00dc#\u00a9\u00b5\u0018;\u007f\u00cb\u0098z\u00e7\u000f\u00be\u008b`\u00f2\u00b0\u0084\u00ea\u00fdi\u0092\u00bd\u000f\u00ee\u0090;7\u00154\u00dc\u008c$\u00f0Z?\u00e1\u00fe\u0094b\u0004\u00f9\u00f7\u008c]L`@P\u008c\u0095\u00ca\u00c3\u00ff\u00af\u00d7\u0092\u00c9 \u00b3w\u001a\u00afG\u00e4\u00ce\u00df\u00ac+\u00da\u00e1{\u00fd\u0000\u001a\u00b5\u001d\u00a5\u008b\u00d5\\\u00af\u007f\u00d8m\u00f21\u00ef\u00ec\u0094\u0018\u0010@d\u00f2c\u00f8\u0085\u000f\u00fc\u0003E\u00fe\u00b5\u001b\u0088\u00cel(\u00a3\u0095\u00d3\t#w9q\u00f6\u008an\u00c3\u00ece\u008a\u00e9K\u0017\u00c3\u00ebed\u0093\u0015,\u0017\u0093\u00f8\u00e0`K\u0001d\u00e6U\u00d2\u00f7\u0001c\u00d8(A\u00a7\u009d\u00a2\u00d0\u00b2`\u009cF\u008a\u0003\u0090\u0007\u0010X\u00fb\n#\u008a\u00f9\u00f0A\u00d0\u00f8-\u00dd\u00b1\u0000\u00b0\u00fe\u0095\u00dcy\u0090ve\u00d2\u0012\u008e\u0082\u0018\u00fd.[\u00bb\u00df`\u0084~?~\u0012\u0088\u00a2\u0095\u00db\u00ba\u0011f\u00a3D\u00bbS\u00cb\u0012 M\u001b7\u001a\u00f0\u001a%\u0084\u00ae\u00fe\u000e\u00c1\u0087\u00c0\u00be\u00a7\u00aedOh\u00bay\u0017\u0098Yh\u0095#\u00ec\u00d5\u00b2\\(\u0003*\u00ed\u00de\u00d6\u00cb\u00few\u009d\u00e3\u00e6U\u00f4e\u00f0\u00fe\u0014\u00dd:\u0082z\u0090\f\u00a5Q\u00e3!\u00cbQ\u0088@\u00cf\u00ef}\u00c9\u00a3m\u0013`p8\b\u00a6\u0017E\u00dd\u00f9J\u009fU\u0015\u00a3$\u00aaBv\u0093\u0003u\u0098\u00b3\u009c\u009f?\u00d8\u0098\u00fd\u008d\u0092S\u0000\u00f9\u00e9\u00a5$\u00d1\u0089CHhW;\u00c5\u008dK\u00a48\u00a9\u00d0\u00a1\u0094\u00fe1\u0015\u0096\u001c$\u0010\u007fb\u00a7\u00bc \u00f6\u0094:\n\u00c9{\u0016#R\u00da+\u0010o\u0002L\u00e1\u00d89a\u0002\u0089\u00fe\u00b7\u00aeB\u00c9\u00f6\u0001(\u00b8z\u00ff\u00f5\u00d1\u00b7R\u00a0\u00c0=%\u0099\u0012Z\u008a\u0092N\u00d0\u0096\u00abdC|\u0011\u0016\u009er\u00eea,_\u009es\u00e6\u00b6 \u00f0\u00b6\u00f8\u00ee(d\u0092g\u00a2\u00ab\u00ceR0&H\u00c7\b\u00ef\u0012\u00c6<.\u007f)7'\u00ff^ \b\u008f\u00a5@\u00aew7\u0010\u0001\u00fc\u00fbq\u009d\u00cf\u00adp\u0018\u00ba\u008d\u00bd\u00f1n\u00f0IB\n\u0090w\u000b'\u0094\u00eb\u0087\u00ac1N)\u007fZ\u00af\u00e3\u0018V\u00ac\u00fc!\u0004\u0087\u00c1\u0084rv\u0085]\u00cc\u00b7\u0082\u00fb\u009f\u0013z\u0093\u00eb\u00c5vZ\u0010\u001d\u00e1b\u00c8\u00046G%\u00a4\u00f7\u008bA\u00a9\u0096t\u0086 ~g5\u0094\f\u00ab$\u00c3\u009d\u00a2-\u00f0\u00baH\u00c3n\u000e\u0010\u00d4\u007f\u0006yVi\u00e5\u0001\u0018K\u00fd\u0015\u00bf\u00ca0\u0006\u008as{.\u00d5\u00e1+\u0080\u008e\u00da?t\u00b2\u00c7)4{\u00aeE\u00c1Su\u0084\u00d4\u00e4\u00de\u00beM^\u009e\u00e9\u0089\u009d\u001a\u0012\u00a0\u00dd\u0001`\u00fbZ\u001dk\r\u00f8\u00af\u00ed 7\r\u00f4d\bI\u00df\u00fa\u00be\u00da8\u00b9(\u000e\u00a4Q\u008e\u00c3\u0082\u00f1z\u00d4\u00ff\u00ce\u00da\u00ff(z4\u00e9\u0004z(\u00f0\u00de\u00c1\u009f&[\u00da\u0088j\u00db\u0012\u0089%\u00e5e\u0085\u0015K\u00cc\u00c3\u00c04\u00d0\u008c,_\u000bnp\u00ce\u00be\u00ab@&F\u00caD\u009a\u0095\u00ef0\u00a5S\u00eb\u00b4\u0091\u00f9\u00b9\t\u0096.\f\u00f1\u0093\u0085\u0016C\u0085|\u00d7\u008av\u00f8\u00df\u008f\u0092\u0017\u00caK\u00ae\u00ac\u009d\u00e9\u00a3\u00bb\u0017o\u00ae\u00eb03\u009a\u000f\u00b8\u0093o\u0084\u0094\u00baPp5\u0095\u001e\u001ft\u00cb4!\u0006d|\u00b5\u0000\"\u00ad\u00b5\u0087\u00ec\u00b5\u00e0\u009a+y\u00c3\u00e6\u00ab\u00d3\u00c1\u00d5\u008f;\u00fbf\u00d1u\u00f8Y\u00f0\u00ea\u0000\u00ed\u00d1\u001cES\u001e\u008eJ8\u00c1\u00f4\u00eeb\u00d7xJ/\u0018R,\u00fa=\u0086\t\u0010\u0081H\u0004\u00a5\u0001\u001c:\u00dbx\u000fP\u008bi\u00168\u00bf\u0097\u00f6\u00cb\u00b9\u0092\b\u0018\u00c8=\u00cea\u0014\nI\u00e5P\u0098\u00c7\u00f7\u00f1hG\u00cb\u00c1b\u008c%\u00e9\u00e6\u00a8&\u00e1E\r\u00b0\u00c7~\u0013\u00ca\u0088\u0086\u00ed;8\u0011\u008a\u00c4uI32y\u00b8\u00f81";
                                var28_6 = 5704;
                                var25_7 = 24;
                                var24_8 = -1;
lbl21:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block26;
                                    break;
                                }
lbl26:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = Offhand.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "\u00ef\u00c1\u001b,\u00c7\u000b\u00e7\u00c4\u00c9\u00e2\u00f5Q\u00a8\u00d3\u0005E\u00cf\u00b4\u001b\u00b9\u00f6\\\u00ba@s\u00e5\u00fc\u00be\u0082S/\u00dd m/\u00d0\u00b1\u00ad1\f\u00d1\u00de\u001e\u0086?xl\u00b4\u0015\u0086C\u00ca\u00b6\f:\u001f\u00c0\u0099E\u00b8\u0082\u001e:\b\b";
                                    var28_6 = 65;
                                    var25_7 = 32;
                                    var24_8 = -1;
lbl35:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block26;
                                        break;
                                    }
                                    break;
                                }
lbl40:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = Offhand.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block27;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        Offhand.fb = var29_3;
                        Offhand.gb = new String[163];
                        Offhand.nb = new HashMap<K, V>(13);
                        var11_10 = Cipher.getInstance("DES/CBC/NoPadding");
                        v7 = SecretKeyFactory.getInstance("DES");
                        v8 = new byte[8];
                        v9 = v8;
                        v8[0] = (byte)(var31 >>> 56);
                        for (var12_11 = 1; var12_11 < 8; ++var12_11) {
                            v9 = v9;
                            v9[var12_11] = (byte)(var31 << var12_11 * 8 >>> 56);
                        }
                        var11_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                        var17_12 = new long[71];
                        var14_13 = 0;
                        var15_14 = "\"\u00ed\u0092A`\u00d5+9\n>,\u00ffm\u00ea\u00c9t\u0093\u00c3\u00fds\u00ba\u00dcnsn\u00b0\u00f9r\u001e/c\u0005\u0086\u0093_`\u00d5\u00ea)c!*\u001b_\u00ccO\u00b9\u00fc\u000f|\u00a1TH\u00e3N\u00eb' \\\u0010\u00d4B`\u00d7\u00b9\u001d\u00d045\u00e9^\u00a1\u00fe\u00de\u00d8,UO\u00e6\u0081\u00b3$\u00f9\n\u0013\u00dfE\u00f9\r7\u00b8;q\u00e3A;\u0011b\u001e?\u00a8\u0081\u00cd\u00c3\u0081\u00da\u00c6\u00da\u0082\u0000c\u00a2\u00f6'\u009cCg\u00af\u0091-\u0099\u00d3q\u00ec\u00de\u00d6\n\u00ee\u0015W\u0099\u00ee\u001f\"\u00e1\u0086\u0016p\u00cf\u00d5`\u00f6YK\"\u0017\u0084\u00a4\u00dfR\u001a2ER\u0019\u00cf\u00b9\u00f7\u0013\u0099\u0090*p\u00f1<V\u00f46\u0006\u0097j6m\u0083R\u008c\u00da\u00ef\u0000\u0017&\u00032\u0004\u00c1Z\u00c2\u00da4\u0018\u00b1\u000f\u0014\u00f8\u0089\u000f\u00eaag\u008a\u00ceqjc\u00d8\u0015\u00c4\u0014/R\u00e1\u00c1f\u00a9\u00f0\u00fcN\u00bb\u009a)\u0085\u00a1Nu\u00c0\u00d8&\u001cAD\u00c7\u0087w\u001e\u00e8\u00d2h\u0095\u00de\u00c9\u00d9%\u00e1\u00b0<x\u00b7Q\u00af\u00aa\u00ba\u008e\u00a1\u0019z\u00a5\u0006\u009bY\u000eH<,\u0082a\u0088<\u00b1\u008fx\u00d4\u007f\u0011}\u00b5<#3i\u0013\u00ba\u0087{\u00bc\u0010vx\u00b8\u00df\u00e2\u00e8\u0080\u00d8\u001e\u008a~0\u00c1\u00bfc3g\u00b5\u001e\u00bfa\u009a\u008e\u00c6\u00cc\u009fH\u00d0\u00df\u0086F\u00f4\u00a2Wo\u00bc!\u001c\u008c\u0081\u009a\u00c3m>jb\u00b0\u001aLSL9\u00a3\u00c6\u0002)\u00939\u0004\u007f\u0014\u00cfc\u00b2\u008d\u0017\u00e9tX\u00bc\b\u001c9\u00179e\u0094\u00e7:\u00b1\u0013\u00e7]\u00d9\u00ccr\u009f:\u00e5\u00e1\u00ce$M\u0003L\u0095\u00e3`\u001d\u001dO\u0080\u00f0g%\u00b2\u00d4\u0092Z\u00c5\u00a4%\u00e1\u0003\u0086\u00d6\u007f\u00da\u007f:\u00a5no~\u00ea\u0019\u00cb\u00bc\u00bd\u00b1F\u00a6\u00cd\u00e2\u00aa\u00ff$\u00e113\u00e6\u00e7\u00a5\u001dh\u00a1\u00e2\u00f3\u0016]1f\u00ddq\u009c\u00aeL\u0090\u00c7\u000ba\u009a\u000e\u00ab\u00d5\u00ee\u00a2n\u0096@\u0092\u001a5j\u00ce\u0094Ye\u0095\u00ec\u00c7\u000e\u009cB\u00b0\u001d\u00d16\u00f4\u0014\u00a7\u0085\u009f\u00b94\r\u00cc7C\u00ddc\u00e9S\u001a~g\u00cb\u0012\u0085\u00ed\u00c3j\u00b5\u0095\u00bc;\u00f6\u00ee\u00ad\u0015\u00dci\u00b5\u00b0\u00bb\u00d1\u00e3\u00e3\u00cc\u00b6^4\u00b9\u00161{\u00fb\b\u0003Ju16R\u00e2Es{R\u0083]\u00ef\u0098\u0091&\u007f\u00b9\f\u0016\u00b8\u00c8\u007fh\u00b9\u000e\u009b\u0093";
                        var16_15 = 552;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block28;
                            break;
                        }
lbl79:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = "\u00ae\u008d\u00d8\u0016\u00aa i\u00c5\u00cco\u00ac\u00a8\u00a9\u00e2\u00c1%";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block28;
                                break;
                            }
                            break;
                        }
lbl92:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block29;
                            break;
                        }
                    }
                    var19_18 = v12;
                    var21_19 = var11_10.doFinal(new byte[]{(byte)(var19_18 >>> 56), (byte)(var19_18 >>> 48), (byte)(var19_18 >>> 40), (byte)(var19_18 >>> 32), (byte)(var19_18 >>> 24), (byte)(var19_18 >>> 16), (byte)(var19_18 >>> 8), (byte)var19_18});
                    v14 = ((long)var21_19[0] & 255L) << 56 | ((long)var21_19[1] & 255L) << 48 | ((long)var21_19[2] & 255L) << 40 | ((long)var21_19[3] & 255L) << 32 | ((long)var21_19[4] & 255L) << 24 | ((long)var21_19[5] & 255L) << 16 | ((long)var21_19[6] & 255L) << 8 | (long)var21_19[7] & 255L;
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
                Offhand.lb = var17_12;
                Offhand.mb = new Integer[71];
                Offhand.qb = new HashMap<K, V>(13);
                var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
                v15 = SecretKeyFactory.getInstance("DES");
                v16 = new byte[8];
                v17 = v16;
                v16[0] = (byte)(var31 >>> 56);
                for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                    v17 = v17;
                    v17[var1_21] = (byte)(var31 << var1_21 * 8 >>> 56);
                }
                var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
                var6_22 = new long[4];
                var3_23 = 0;
                var4_24 = "\u00fa\u00e3'\u00b0|&$.\u00d4\u00b4O\u009a\u00f2\u0094\u00bd\u00fe";
                var5_25 = 16;
                var2_26 = 0;
                while (true) {
                    var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
                    v18 = var6_22;
                    v19 = var3_23++;
                    v20 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
                    v21 = -1;
                    break block30;
                    break;
                }
lbl132:
                // 1 sources

                while (true) {
                    v18[v19] = v22;
                    if (var2_26 < var5_25) ** continue;
                    var4_24 = "\u00a9kr\u0096\u001c'\u00be\u0005W\u00d3\u009cNI\u009e\u00a4\u00bd";
                    var5_25 = 16;
                    var2_26 = 0;
                    while (true) {
                        var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
                        v18 = var6_22;
                        v19 = var3_23++;
                        v20 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
                        v21 = 0;
                        break block30;
                        break;
                    }
                    break;
                }
lbl145:
                // 1 sources

                while (true) {
                    v18[v19] = v22;
                    if (var2_26 < var5_25) ** continue;
                    break block31;
                    break;
                }
            }
            var8_28 = v20;
            var10_29 = var0_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            v22 = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
            switch (v21) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl158:
                // 1 sources

                ** continue;
            }
        }
        Offhand.ob = var6_22;
        Offhand.pb = new Long[4];
        var66_30 = new KProperty[33];
        var66_30[0] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "delayBetweenSwaps", "getDelayBetweenSwaps()I", 0));
        7385583571968264421L ^ var31;
        var66_30[1] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "totemHealth", "getTotemHealth()F", 0));
        var66_30[2] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "totemElytraHealth", "getTotemElytraHealth()F", 0));
        var66_30[3] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "outdatedSwap", "getOutdatedSwap()Z", 0));
        var66_30[4] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "offhand", "getOffhand()Lsu/catlean/w4;", 0));
        var66_30[5] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "bindSwap", "getBindSwap()Z", 0));
        var66_30[6] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "swapButton", "getSwapButton()Lsu/catlean/lj;", 0));
        var66_30[7] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "swapMode", "getSwapMode()Lsu/catlean/b0;", 0));
        var66_30[8] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "swapGroup", "getSwapGroup()Lsu/catlean/h;", 0));
        var66_30[9] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "notation", "getNotation()Lsu/catlean/oi;", 0));
        var66_30[10] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap1", "getSwap1()Ljava/lang/String;", 0));
        var66_30[11] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap2", "getSwap2()Ljava/lang/String;", 0));
        var66_30[12] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap3", "getSwap3()Ljava/lang/String;", 0));
        var66_30[13] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap4", "getSwap4()Ljava/lang/String;", 0));
        var66_30[14] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap5", "getSwap5()Ljava/lang/String;", 0));
        var66_30[15] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap6", "getSwap6()Ljava/lang/String;", 0));
        var66_30[16] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap7", "getSwap7()Ljava/lang/String;", 0));
        var66_30[17] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap8", "getSwap8()Ljava/lang/String;", 0));
        var66_30[18] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(Offhand.class, "swap9", "getSwap9()Ljava/lang/String;", 0));
        var66_30[19] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "hotBarSwap", "getHotBarSwap()Z", 0));
        var66_30[20] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "resetAttackCooldown", "getResetAttackCooldown()Z", 0));
        var66_30[21] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "saveEnchanted", "getSaveEnchanted()Z", 0));
        var66_30[22] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "rcGap", "getRcGap()Lsu/catlean/lk;", 0));
        var66_30[23] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "gapplePriority", "getGapplePriority()Lsu/catlean/gu;", 0));
        var66_30[24] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "crappleSpoof", "getCrappleSpoof()Z", 0));
        var66_30[25] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "deathVerbose", "getDeathVerbose()Z", 0));
        var66_30[26] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "containers", "getContainers()Z", 0));
        var66_30[27] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "safetyFactors", "getSafetyFactors()Lsu/catlean/h;", 0));
        var66_30[28] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "onCrystalInHand", "getOnCrystalInHand()Z", 0));
        var66_30[29] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "onFalling", "getOnFalling()Z", 0));
        var66_30[30] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "onMace", "getOnMace()Z", 0));
        var66_30[31] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "onExlposiveEntities", "getOnExlposiveEntities()Z", 0));
        var66_30[32] = Reflection.property1(new PropertyReference1Impl(Offhand.class, "onAnchors", "getOnAnchors()Z", 0));
        Offhand.delegatedProperties = var66_30;
        Offhand.INSTANCE = new Offhand();
        Offhand.delayBetweenSwaps$delegate = SettingFactory.intSetting$default(Offhand.INSTANCE, "delay-between-swaps", 0, new IntRange(0, 10), null, null, 24, null);
        Offhand.totemHealth$delegate = SettingFactory.floatSetting$default(Offhand.INSTANCE, "totem-hp", 16.0f, RangesKt.rangeTo(0.0f, 36.0f), null, null, 24, null);
        Offhand.totemElytraHealth$delegate = SettingFactory.floatSetting$default(Offhand.INSTANCE, "totem-elytra-hp", 16.0f, RangesKt.rangeTo(0.0f, 36.0f), null, null, 24, null);
        Offhand.outdatedSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "pre-1.16.5-swap", false, null, null, 12, null);
        Offhand.offhand$delegate = SettingFactory.modeSetting$default((SettingContainer)Offhand.INSTANCE, "force-item", (Enum)OffhandOffhandMode.OFF, null, null, 12, null);
        Offhand.bindSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "bind-swap", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        var67_31 = null;
        var68_32 = 4;
        var71_33 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)();
        Offhand.swapButton$delegate = SettingFactory.bindSetting$default(Offhand.INSTANCE, "swap-button", new Bind(0, false, false, 7, null), null, var71_33, var68_32, var67_31);
        Offhand.swapMode$delegate = SettingFactory.modeSetting$default((SettingContainer)Offhand.INSTANCE, "swap", (Enum)OffhandSwapMode.GAPPLE_SHIELD, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swapGroup$delegate = SettingFactory.settingGroup$default(Offhand.INSTANCE, "swap-items", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 2, null);
        var61_34 = null;
        var62_35 = 4;
        var63_36 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)();
        var64_37 = null;
        var65_38 = new TextValue("Hold the bind and click items in inv to add them. Hold the bind and use the hotbar to swap items");
        Offhand.notation = SettingFactory.stringListSetting$default(Offhand.INSTANCE, "notation", var65_38, var64_37, var63_36, var62_35, var61_34);
        Offhand.swap1$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap1", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap2$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap2", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap3$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap3", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap4$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap4", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap5$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap5", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap6$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap6", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap7$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap7", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap8$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap8", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.swap9$delegate = SettingFactory.textSetting$default(Offhand.INSTANCE, "swap9", "", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.hotBarSwap$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "hot-bar-swap", false, null, null, 12, null);
        Offhand.resetAttackCooldown$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "reset-attack-cooldown", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.saveEnchanted$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "save-enchanted", false, null, null, 12, null);
        Offhand.rcGap$delegate = SettingFactory.modeSetting$default((SettingContainer)Offhand.INSTANCE, "right-click-gapple", (Enum)OffhandRcGapMode.OFF, null, null, 12, null);
        Offhand.gapplePriority$delegate = SettingFactory.modeSetting$default((SettingContainer)Offhand.INSTANCE, "gapple-priority", (Enum)OffhandGapplePriorityMode.ENCHANTED, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.crappleSpoof$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "crapple-spoof", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 4, null);
        Offhand.deathVerbose$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "death-verbose", true, null, null, 12, null);
        Offhand.containers$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "containers", true, null, null, 12, null);
        Offhand.safetyFactors$delegate = SettingFactory.settingGroup$default(Offhand.INSTANCE, "safety-factors", AuraTargetsMode.BOOLS, null, 4, null);
        Offhand.onCrystalInHand$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "on-crystal-in-hand", false, Offhand.INSTANCE.getSafetyFactors(), null, 8, null);
        Offhand.onFalling$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "on-falling", true, Offhand.INSTANCE.getSafetyFactors(), null, 8, null);
        Offhand.onMace$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "on-mace", true, Offhand.INSTANCE.getSafetyFactors(), null, 8, null);
        Offhand.onExlposiveEntities$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "on-explosive-entities", true, Offhand.INSTANCE.getSafetyFactors(), null, 8, null);
        Offhand.onAnchors$delegate = SettingFactory.booleanSetting$default((SettingContainer)Offhand.INSTANCE, "on-anchors", true, Offhand.INSTANCE.getSafetyFactors(), null, 8, null);
        v23 = Identifier.ofVanilla("hud/hotbar_selection");
        Intrinsics.checkNotNullExpressionValue(v23, "withDefaultNamespace(...)");
        Offhand.U = v23;
        v24 = Identifier.ofVanilla("hud/hotbar_offhand_left");
        Intrinsics.checkNotNullExpressionValue(v24, "withDefaultNamespace(...)");
        Offhand.I = v24;
        v25 = Items.AIR.getDefaultStack();
        Intrinsics.checkNotNullExpressionValue(v25, "getDefaultInstance(...)");
        Offhand.displayStack = v25;
        Offhand.isSwapping = new AtomicBoolean(false);
        Offhand.swapTimer = new Timer();
    }

    public static void zkmSetFlag(int[] nArray) {
        L = nArray;
    }

    public static int[] int_arr_zkmGetFlag() {
        return L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x65A5;
        if (gb[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])hb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    hb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qb", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = fb[n2].getBytes("ISO-8859-1");
            Offhand.gb[n2] = Offhand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return gb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Offhand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3A30;
        if (mb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = lb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])nb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    nb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qb", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Offhand.mb[n2] = n3;
        }
        return mb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Offhand.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6D74;
        if (pb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = ob[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])qb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    qb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qb", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            Offhand.pb[n2] = l4;
        }
        return pb[n2];
    }

    private static long e(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = Offhand.zkmDecryptLong(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Long.TYPE, l2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return l2;
    }

    private static CallSite zkmBootstrap3(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_2().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/qb" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Offhand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Offhand.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_2() {
        try {
            return MethodHandles.lookup().findStatic(Offhand.class, "e", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

