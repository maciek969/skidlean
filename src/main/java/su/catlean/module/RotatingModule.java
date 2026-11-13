/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module;

import java.awt.Color;
import java.lang.invoke.CallSite;
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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.s2c.play.OpenScreenS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Category;
import su.catlean.module.Module;
import su.catlean.module.RotatingModuleTimingMode;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.player.AutoMend;
import su.catlean.module.player.BoxShrinker;
import su.catlean.module.player.SpeedMine;
import su.catlean.module.rage.Blink;
import su.catlean.render.AnimationStyle;
import su.catlean.render.BlockHighlightRenderer;
import su.catlean.render.RenderUtil;
import su.catlean.render.ShapeRenderMode;
import su.catlean.render.Theme;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.InteractMode;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketAccess;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.PlaceMode;
import su.catlean.util.SlotResult;
import su.catlean.util.SwitchMode;
import su.catlean.util.Timer;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public abstract class RotatingModule
extends Module {
    static final KProperty[] delegatedProperties;
    private final int priority;
    private boolean withRange;
    @NotNull
    private final ModeSetting timing$delegate;
    @NotNull
    private final IntSetting blocksPerAction$delegate;
    @NotNull
    private final IntSetting delayPerAction$delegate;
    @NotNull
    private final BooleanSetting rotate$delegate;
    @NotNull
    private final BooleanSetting grimSilent$delegate;
    @NotNull
    private final ModeSetting placeMode$delegate;
    @NotNull
    private final ModeSetting interact$delegate;
    @NotNull
    private final ModeSetting E;
    @NotNull
    private final FloatSetting range$delegate;
    @NotNull
    private final FloatSetting wallRange$delegate;
    @NotNull
    private final SettingGroup pauseGroup$delegate;
    @NotNull
    private final BooleanSetting mining$delegate;
    @NotNull
    private final BooleanSetting eating$delegate;
    @NotNull
    private final BooleanSetting inventory$delegate;
    @NotNull
    private final BooleanSetting blink$delegate;
    @NotNull
    private final ModeSetting g;
    @NotNull
    private final ModeSetting V;
    @NotNull
    private final BooleanSetting syncColors$delegate;
    @NotNull
    private final ColorSetting fillColor$delegate;
    @NotNull
    private final ColorSetting outlineColor$delegate;
    @NotNull
    private final Timer placeTimer;
    private int placesLeft;
    private static int[] u;
    private static final long ab;
    private static final String[] fb;
    private static final String[] gb;
    private static final Map hb;
    private static final long[] lb;
    private static final Integer[] mb;
    private static final Map nb;

    public RotatingModule(@NotNull String string, @NotNull Category category, int n, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(category, "category");
        super(string, category, null, null);
        this.priority = n;
        this.withRange = bl;
        this.timing$delegate = SettingFactory.modeSetting$default((SettingContainer)this, "timing", (Enum)RotatingModuleTimingMode.Vanilla, null, null, 12, null);
        this.blocksPerAction$delegate = SettingFactory.intSetting$default(this, "blocks-per-action", 6, new IntRange(2, 20), null, () -> RotatingModule._init_$lambda$0(this), 8, null);
        this.delayPerAction$delegate = SettingFactory.intSetting$default(this, "delay-per-action", 0, new IntRange(0, 10), null, () -> RotatingModule._init_$lambda$1(this), 8, null);
        this.rotate$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "rotate", true, null, null, 12, null);
        this.grimSilent$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "grim-silent", false, null, () -> RotatingModule._init_$lambda$2(this), 4, null);
        this.placeMode$delegate = SettingFactory.modeSetting$default((SettingContainer)this, "place-mode", (Enum)InteractMode.Normal, null, null, 12, null);
        this.interact$delegate = SettingFactory.modeSetting$default((SettingContainer)this, "interact", (Enum)PlaceMode.Strict, null, null, 12, null);
        this.E = SettingFactory.modeSetting$default((SettingContainer)this, "switch", (Enum)SwitchMode.Silent, null, null, 12, null);
        this.range$delegate = SettingFactory.floatSetting$default(this, "range", 4.0f, RangesKt.rangeTo(2.0f, 10.0f), null, () -> RotatingModule.access$getWithRange(this), 8, null);
        this.wallRange$delegate = SettingFactory.floatSetting$default(this, "wall-range", 4.0f, RangesKt.rangeTo(2.0f, 10.0f), null, () -> RotatingModule.access$getWithRange2(this), 8, null);
        this.pauseGroup$delegate = SettingFactory.settingGroup$default(this, "pause", AuraTargetsMode.BOOLS, null, 4, null);
        this.mining$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "mining", false, this.getPauseGroup(), null, 8, null);
        this.eating$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "eating", false, this.getPauseGroup(), null, 8, null);
        this.inventory$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "inventory", false, this.getPauseGroup(), null, 8, null);
        this.blink$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "blink", false, this.getPauseGroup(), null, 8, null);
        this.g = SettingFactory.modeSetting$default((SettingContainer)this, "render", (Enum)ShapeRenderMode.BOTH, null, null, 12, null);
        this.V = SettingFactory.modeSetting$default((SettingContainer)this, "animation", (Enum)AnimationStyle.Fade, null, null, 12, null);
        this.syncColors$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "sync-colors", false, null, null, 12, null);
        SettingContainer settingContainer = this;
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color, "WHITE");
        this.fillColor$delegate = SettingFactory.colorSetting$default(settingContainer, "fill-color", color, null, () -> RotatingModule._init_$lambda$5(this), 4, null);
        SettingContainer settingContainer2 = this;
        Color color2 = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue(color2, "WHITE");
        this.outlineColor$delegate = SettingFactory.colorSetting$default(settingContainer2, "outline-color", color2, null, () -> RotatingModule._init_$lambda$6(this), 4, null);
        this.placeTimer = new Timer();
    }

    public RotatingModule(String string, Category category, int object, boolean bl, int n, DefaultConstructorMarker defaultConstructorMarker) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 48 | (long)n3 << 48 >>> 16 | (long)n4 << 32 >>> 32) ^ ab;
        if ((n & 4) != 0) {
            object = RotatingModule.zkmBootstrap2("c", (int)17776, (long)(0x511E46DA3267EC2EL ^ l));
        }
        if ((n & RotatingModule.zkmBootstrap2("c", (int)20669, (long)(0x27D9325EC53F9FDL ^ l))) != 0) {
            bl = true;
        }
        this(string, category, (int)object, bl);
    }

    public final int getPriority() {
        return this.priority;
    }

    private final RotatingModuleTimingMode getTiming() {
        return (RotatingModuleTimingMode)((Object)this.timing$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setTiming(RotatingModuleTimingMode rotatingModuleTimingMode) {
        this.timing$delegate.setValue(this, delegatedProperties[0], (Object)rotatingModuleTimingMode);
    }

    private final int getBlocksPerAction() {
        return ((Number)this.blocksPerAction$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final void setBlocksPerAction(int n) {
        this.blocksPerAction$delegate.setValue(this, delegatedProperties[1], n);
    }

    private final int getDelayPerAction() {
        return ((Number)this.delayPerAction$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final void setDelayPerAction(int n) {
        this.delayPerAction$delegate.setValue(this, delegatedProperties[2], n);
    }

    protected final boolean getRotate() {
        return (Boolean)this.rotate$delegate.getValue(this, delegatedProperties[3]);
    }

    protected final void setRotate(boolean bl) {
        this.rotate$delegate.setValue(this, delegatedProperties[3], bl);
    }

    protected final boolean getGrimSilent() {
        return (Boolean)this.grimSilent$delegate.getValue(this, delegatedProperties[4]);
    }

    protected final void setGrimSilent(boolean bl) {
        this.grimSilent$delegate.setValue(this, delegatedProperties[4], bl);
    }

    @NotNull
    protected final InteractMode getPlaceMode() {
        return (InteractMode)((Object)this.placeMode$delegate.getValue(this, delegatedProperties[5]));
    }

    protected final void setPlaceMode(@NotNull InteractMode interactMode) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter((Object)interactMode, (String)((Object)RotatingModule.zkmBootstrap("y", (int)9627, (long)(0x492385F785BDFE3FL ^ l))));
        this.placeMode$delegate.setValue(this, delegatedProperties[5], (Object)interactMode);
    }

    @NotNull
    protected final PlaceMode getInteract() {
        return (PlaceMode)((Object)this.interact$delegate.getValue(this, delegatedProperties[6]));
    }

    protected final void setInteract(@NotNull PlaceMode placeMode) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        Intrinsics.checkNotNullParameter((Object)placeMode, (String)((Object)RotatingModule.zkmBootstrap("y", (int)9627, (long)(0x4923893693C08C79L ^ l))));
        this.interact$delegate.setValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)26364, (long)(0x3F3D2123FE97DC38L ^ l))], (Object)placeMode);
    }

    @NotNull
    protected final SwitchMode a() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        return (SwitchMode)((Object)this.E.getValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)29869, (long)(0x6C1AFA6A1E88C17CL ^ l))]));
    }

    protected final void a(@NotNull SwitchMode switchMode) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ ab;
        Intrinsics.checkNotNullParameter((Object)switchMode, (String)((Object)RotatingModule.zkmBootstrap("y", (int)15372, (long)(0x55F0DC85427C503EL ^ l))));
        this.E.setValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)27053, (long)(0x6E87DDC8894B9684L ^ l))], (Object)switchMode);
    }

    protected final float getRange() {
        return ((Number)this.range$delegate.getValue(this, delegatedProperties[8])).floatValue();
    }

    protected final void setRange(float f) {
        long l = 0L;
        l = ab ^ l;
        this.range$delegate.setValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)31704, (long)(0x576DBD141BC29496L ^ l))], Float.valueOf(f));
    }

    protected final float getWallRange() {
        return ((Number)this.wallRange$delegate.getValue(this, delegatedProperties[9])).floatValue();
    }

    protected final void setWallRange(float f) {
        long l = 0L;
        l = ab ^ l;
        this.wallRange$delegate.setValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)24671, (long)(0x38398D7A0EDBAB04L ^ l))], Float.valueOf(f));
    }

    @NotNull
    protected final SettingParent getPauseGroup() {
        return (SettingParent)this.pauseGroup$delegate.getValue(this, delegatedProperties[10]);
    }

    protected final boolean getMining() {
        return (Boolean)this.mining$delegate.getValue(this, delegatedProperties[11]);
    }

    protected final boolean getEating() {
        return (Boolean)this.eating$delegate.getValue(this, delegatedProperties[12]);
    }

    protected final boolean getInventory() {
        return (Boolean)this.inventory$delegate.getValue(this, delegatedProperties[13]);
    }

    protected final boolean getBlink() {
        return (Boolean)this.blink$delegate.getValue(this, delegatedProperties[14]);
    }

    @NotNull
    protected final ShapeRenderMode D() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ ab;
        return (ShapeRenderMode)((Object)this.g.getValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)2994, (long)(0x53A8B49743742043L ^ l))]));
    }

    protected final void J(@NotNull ShapeRenderMode shapeRenderMode) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter((Object)shapeRenderMode, (String)((Object)RotatingModule.zkmBootstrap("y", (int)9627, (long)(0x4923B6187AE9EFAAL ^ l))));
        this.g.setValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)20474, (long)(0x2F1727AEEEC096F9L ^ l))], (Object)shapeRenderMode);
    }

    @NotNull
    protected final AnimationStyle H() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)n3 << 56 >>> 56) ^ ab;
        return (AnimationStyle)((Object)this.V.getValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)18956, (long)(0x68082D6FD4250C97L ^ l))]));
    }

    protected final void I(@NotNull AnimationStyle animationStyle) {
        long l = 0L;
        l = ab ^ l;
        Intrinsics.checkNotNullParameter((Object)animationStyle, (String)((Object)RotatingModule.zkmBootstrap("y", (int)9627, (long)(0x4923E8B4EA20501FL ^ l))));
        this.V.setValue(this, delegatedProperties[RotatingModule.zkmBootstrap2("c", (int)20575, (long)(0x1FD5E786E1C8B6F5L ^ l))], (Object)animationStyle);
    }

    protected final boolean getSyncColors() {
        return (Boolean)this.syncColors$delegate.getValue(this, delegatedProperties[17]);
    }

    @NotNull
    protected final Color getFillColor() {
        return (Color)this.fillColor$delegate.getValue(this, delegatedProperties[18]);
    }

    @NotNull
    protected final Color getOutlineColor() {
        return (Color)this.outlineColor$delegate.getValue(this, delegatedProperties[19]);
    }

    @NotNull
    protected final Timer getPlaceTimer() {
        return this.placeTimer;
    }

    @Flow
    protected final void onReceivePacket(@NotNull ReceivePacket receivePacket) {
        block0: {
            String string = HudElement.zkmGetFlag();
            Intrinsics.checkNotNullParameter(receivePacket, "e");
            String string2 = string;
            if (!(receivePacket.getPacket() instanceof OpenScreenS2CPacket) || this.placeTimer.hasPassed(250)) break block0;
            PacketUtil.sendPacket(new CloseHandledScreenC2SPacket(((OpenScreenS2CPacket)receivePacket.getPacket()).getSyncId()));
            receivePacket.cancel();
        }
    }

    protected final boolean placeAll(@NotNull List list, long l, boolean bl, boolean bl2, @NotNull List list2, boolean bl3) {
        Collection<Item> collection;
        long l2 = (l = ab ^ l) ^ 0x5CDB69C896A3L;
        Intrinsics.checkNotNullParameter(list, "positions");
        String string = HudElement.zkmGetFlag();
        Intrinsics.checkNotNullParameter(list2, "blocks");
        String string2 = string;
        Iterable iterable = list2;
        boolean bl4 = bl2;
        boolean bl5 = bl;
        List list3 = list;
        RotatingModule rotatingModule = this;
        Iterable iterable2 = iterable;
        Collection collection2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Object t : iterable2) {
            Block block = (Block)t;
            collection = collection2;
            Item item = block.asItem();
            Intrinsics.checkNotNullExpressionValue(item, "asItem(...)");
            collection.add(item);
        }
        collection = (List)collection2;
        return rotatingModule.placeBlocks(list3, bl5, l2, bl4, (List)collection, bl3);
    }

    public static boolean s(RotatingModule rotatingModule, List list, boolean bl, boolean bl2, List list2, boolean bl3, int n, long l, Object object) {
        long l2 = l = ab ^ l;
        long l3 = l2 ^ 0x65784CDC326AL;
        long l4 = l2 ^ 0x501864F3BF69L;
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: place");
        }
        if ((n & 2) != 0) {
            bl = false;
        }
        if ((n & 4) != 0) {
            bl2 = false;
        }
        if ((n & 8) != 0) {
            list2 = rotatingModule.getBlocks(l4);
        }
        if ((n & 0x10) != 0) {
            bl3 = false;
        }
        return rotatingModule.placeAll(list, l3, bl, bl2, list2, bl3);
    }

    protected final boolean placeBlocks(@NotNull List list, boolean bl, long l, boolean bl2, @NotNull List list2, boolean bl3) {
        long l2 = (l = ab ^ l) ^ 0x4939F9E37FC0L;
        Intrinsics.checkNotNullParameter(list, "positions");
        String string = HudElement.zkmGetFlag();
        Intrinsics.checkNotNullParameter(list2, "blocks");
        String string2 = string;
        if (this.placesLeft > 0) {
            int n = this.placesLeft;
            this.placesLeft = n + -1;
            return false;
        }
        if (list.isEmpty()) {
            return false;
        }
        int n = this.getBlocksPerAction();
        boolean bl4 = false;
        for (BlockPos blockPos : list) {
            if (n <= 0) break;
            if (!this.placeBlock(l2, blockPos, list2, bl, bl2, bl3)) continue;
            bl4 = true;
            if (this.getTiming() != RotatingModuleTimingMode.Instant) break;
            --n;
            this.placesLeft = this.getDelayPerAction();
        }
        return bl4;
    }

    public static boolean placeBlocks$default(RotatingModule rotatingModule, List list, boolean bl, boolean bl2, long l, List list2, boolean bl3, int n, Object object) {
        long l2 = (l = ab ^ l) ^ 0x468E2FC25491L;
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeItems");
        }
        bl = false;
        if ((n & 4) != 0) {
            bl2 = false;
        }
        if ((n & 8) != 0) {
            list2 = rotatingModule.getPlaceBlocks();
        }
        if ((n & 0x10) != 0) {
            bl3 = false;
        }
        return rotatingModule.placeBlocks(list, bl, l2, bl2, list2, bl3);
    }

    private final boolean placeBlock(long l, BlockPos blockPos, List list, boolean bl, boolean bl2, boolean bl3) {
        SlotResult slotResult;
        long l2 = (l = ab ^ l) ^ 0x3001C8F5D280L;
        SlotResult slotResult2 = slotResult = bl3 ? InventoryUtil.INSTANCE.findInHotbar(arg_0 -> RotatingModule.isValidBlockItem(list, arg_0)) : InventoryUtil.INSTANCE.findInHotbar(list);
        if (!slotResult.isFound()) {
            return false;
        }
        if (this.isReady()) {
            return false;
        }
        RotationRequest rotationRequest = PlaceManager.place$default(PlaceManager.INSTANCE, blockPos, slotResult.getSlot(), bl2 ? PlaceMode.Vanilla : this.getInteract(), this.getPlaceMode(), this.getEntityCheck(), this.getRange(), this.getWallRange(), this.priority, l2, this.a(), null, this.getGrimSilent(), 512, null);
        if (rotationRequest != null) {
            RotationRequest rotationRequest2 = rotationRequest;
            Entity entity = this.attackCrystalsAt(blockPos);
            if (entity != null) {
                if (this.shouldSkipEntity(entity)) {
                    PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket = PlayerInteractEntityC2SPacket.attack(Globals.getPlayer(), Globals.getPlayer().isSneaking());
                    Intrinsics.checkNotNullExpressionValue(playerInteractEntityC2SPacket, "createAttackPacket(...)");
                    PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket2 = playerInteractEntityC2SPacket;
                    PacketAccess.setEntityId(playerInteractEntityC2SPacket2, entity.getId());
                    PacketUtil.sendPacket(playerInteractEntityC2SPacket2);
                    Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                } else {
                    return false;
                }
            }
            if (this.getRotate()) {
                if (this.getTiming() == RotatingModuleTimingMode.Instant) {
                    RotationManager.INSTANCE.queue(rotationRequest);
                } else {
                    RotationManager.INSTANCE.submit(rotationRequest);
                }
            }
            if (bl || !this.getRotate()) {
                if (this.getRotate() && this.getGrimSilent() && this.getTiming() == RotatingModuleTimingMode.Instant) {
                    RotationManager.INSTANCE.sendRotation(rotationRequest2.getRotation().getYaw(), rotationRequest2.getRotation().getPitch());
                }
                rotationRequest.getAction().invoke();
                if (this.getRotate() && this.getGrimSilent() && this.getTiming() == RotatingModuleTimingMode.Instant) {
                    RotationManager.INSTANCE.sendRotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch());
                }
                rotationRequest.setAction(RotatingModule::placeBlock$lambda$1);
            }
            if (this.D() != ShapeRenderMode.NONE) {
                ShapeRenderMode shapeRenderMode = this.D();
                AnimationStyle animationStyle = this.H();
                Color color = this.getSyncColors() ? RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 1.0f) : this.getFillColor();
                Color color2 = this.getSyncColors() ? RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getPrimaryColor(), 1.0f) : this.getOutlineColor();
                BlockHighlightRenderer.INSTANCE.add(blockPos, color2, color, animationStyle, shapeRenderMode);
            }
            AutoMend.INSTANCE.getTimer().reset();
            this.placeTimer.reset();
            return true;
        }
        return false;
    }

    static boolean placeBlock$default(long l, RotatingModule rotatingModule, BlockPos blockPos, List list, boolean bl, boolean bl2, boolean bl3, int n, Object object) {
        long l2 = (l = ab ^ l) ^ 0x4E29E4962F0CL;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)717630557503706603L, (long)l);
        if (object != null) {
            throw new UnsupportedOperationException((String)((Object)RotatingModule.zkmBootstrap("y", (int)23462, (long)(0x570B25451AF19191L ^ l))));
        }
        boolean bl4 = n & RotatingModule.zkmBootstrap2("c", (int)20575, (long)(0x1FD59CB512EF090DL ^ l));
        if (callSite == null) {
            if (bl4) {
                bl3 = false;
            }
            bl4 = rotatingModule.placeBlock(l2, blockPos, list, bl, bl2, bl3);
        }
        return bl4;
    }

    protected boolean shouldSkipEntity(@NotNull Entity entity) {
        String string = HudElement.zkmGetFlag();
        Intrinsics.checkNotNullParameter(entity, "crystal");
        String string2 = string;
        return !this.isReady();
    }

    private final Entity attackCrystalsAt(BlockPos blockPos) {
        Object v2;
        block1: {
            String string = HudElement.zkmGetFlag();
            List<Entity> list = Globals.getWorld().getNonSpectatingEntities(Entity.class, new Box(blockPos));
            Intrinsics.checkNotNullExpressionValue(list, "getEntitiesOfClass(...)");
            Iterable iterable = list;
            String string2 = string;
            for (Object t : iterable) {
                Entity entity = (Entity)t;
                if (!(entity instanceof EndCrystalEntity)) continue;
                v2 = t;
                break block1;
            }
            v2 = null;
        }
        return v2;
    }

    protected final boolean isBlockedByEntity(@NotNull BlockPos blockPos) {
        String string = HudElement.zkmGetFlag();
        Intrinsics.checkNotNullParameter(blockPos, "bp");
        String string2 = string;
        List<LivingEntity> list = Globals.getWorld().getNonSpectatingEntities(LivingEntity.class, BoxShrinker.INSTANCE.getEnabled() ? new Box(blockPos).expand(-1.0E-12, 0.0, -1.0E-12) : new Box(blockPos));
        Intrinsics.checkNotNullExpressionValue(list, "getEntitiesOfClass(...)");
        return !((Collection)list).isEmpty();
    }

    @NotNull
    protected List getBlocks(long l) {
        Block[] blockArray = new Block[]{Blocks.OBSIDIAN, Blocks.ENDER_CHEST, Blocks.NETHERITE_BLOCK, Blocks.RESPAWN_ANCHOR};
        return CollectionsKt.listOf(blockArray);
    }

    @NotNull
    protected List getPlaceBlocks() {
        return CollectionsKt.emptyList();
    }

    @NotNull
    protected EntityCheck getEntityCheck() {
        return EntityCheck.ONLY_CRYSTALS;
    }

    protected boolean isReady() {
        if (Globals.getInteractionManager().isBreakingBlock() && this.getMining()) {
            return true;
        }
        if (Blink.INSTANCE.getEnabled() && this.getBlink()) {
            return true;
        }
        if (Globals.getPlayer().isUsingItem() && this.getEating()) {
            return true;
        }
        if (SpeedMine.INSTANCE.getAtomicBoolean().get()) {
            return true;
        }
        return MovementUtil.INSTANCE.I() && this.getInventory();
    }

    private static final boolean _init_$lambda$0(RotatingModule rotatingModule) {
        return rotatingModule.getTiming() == RotatingModuleTimingMode.Instant;
    }

    private static final boolean _init_$lambda$1(RotatingModule rotatingModule) {
        return rotatingModule.getTiming() == RotatingModuleTimingMode.Instant;
    }

    private static final boolean _init_$lambda$2(RotatingModule rotatingModule) {
        return rotatingModule.getRotate() && rotatingModule.getTiming() == RotatingModuleTimingMode.Instant;
    }

    private static final boolean access$getWithRange(RotatingModule rotatingModule) {
        return rotatingModule.withRange;
    }

    private static final boolean access$getWithRange2(RotatingModule rotatingModule) {
        return rotatingModule.withRange;
    }

    private static final boolean _init_$lambda$5(RotatingModule rotatingModule) {
        return !rotatingModule.getSyncColors();
    }

    private static final boolean _init_$lambda$6(RotatingModule rotatingModule) {
        return !rotatingModule.getSyncColors();
    }

    private static final boolean isValidBlockItem(List list, ItemStack itemStack) {
        String string = HudElement.zkmGetFlag();
        Intrinsics.checkNotNullParameter(itemStack, "it");
        String string2 = string;
        return itemStack.getItem() instanceof BlockItem && !list.contains(itemStack.getItem());
    }

    private static final Unit placeBlock$lambda$1() {
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
                        RotatingModule.ab = ZkmKeys.a(-2385895560131446595L, -5478889964095762468L, MethodHandles.lookup().lookupClass()).a(20760250163599L);
                        var20 = RotatingModule.ab ^ 107889819955667L;
                        RotatingModule.hb = new HashMap<K, V>(13);
                        RotatingModule.zkmSetFlag(null);
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
                        var18_3 = new String[80];
                        var16_4 = 0;
                        var15_5 = "'\u00f2v\u00be\u000b\u0097)m\u00cb\u00a5p\\/x\u00b8\u009a\u0010\u0002\u00a3\n\u00f5\u00dbL\u00de=\u001458\u0093z\u00b9h\u00ed \u008cx~P\u0006\u00a3\u00f3\u0004\u00c7\u00b6\u00beau\u00c9W\u00c9\r\u0091\u00e8!j\u00cd\u00ab\fk\u00d2\u00c47\u00a2qV\u00de\u0018\u0088]SS\u008a&\u008d\u0084Y\u00c3Fb2\u00cd\b\u00ca\u00b4\u001e|\u00bf\"\u00d7%\u00e5\u0010l\u00b3\u008e \u00e5\u00f0\u00d0w\\\u00ecCf\u00a2`;\\\u0010\u008e\u00f8q\u0093n\u00b6K\u0011\u008a\u00f6\u001e\u00a0\u00c0\u00e9\u00a5\u00c4@\u00b3\u0089u.\u00d4\u00b4\u009c\u00d9J\u00ac\u008d\u0085\u00aa\u00f6}\u00d0A\u00b8{\u0011\u00f5=\u00bb\u008a\u008b\u0086Xs\u00b1\u00d6\u009c\u00a4\u00c3\u00c2\u0002\u00ad\u00f6\u0099\u00b9dq\u00c0\u00ecW\u00d5z\u008eC\t\u008c\u0014`\u008dX\u00fd\u008aSp\u00e9\u0089\u009a\u00a1E\u00ef \u007ffx-\u00d1GTL\u00b5\u0082]\u00d3oWF`\u00f7\u00bc\u00da\u00fah\u00e3T\u00e5\u001e\u00d5\u00c8\u00cf\u00c5\u0001\u00ca\u00cd(\u00c1\u00fc\u0011\f\u00a5\u000f\u00c7\u00b1b\u00d6\u00bd\u008d\u0088\u0085D,)\u00a4$k;L\u00db\u0010\u00e9j\u000f\u00eb\u00ae\fk\u0019f.\u00ad\u00ac\u00acLd\u0012(\u00f6\u0084\u00a85|\u00d4\u00f3\u00a4\u0014\u00f4~\u00af<v6\u00b7\u0014\u0001\u008c\u0090\u00d4\u0088\u00faN\u00e2\u00f6\u00e5h\u00a8\u00f0\u00f4m\r5E=\u00b2Fg\u009d\u0010\u0007\u0013N\u008e\u00e1\u00b6W\u00ff\u00a1\u0096<S\u00b4.\u001e\u00030\u00bd\u00ca\u0005\u00ea\u0006\u001f\u00f3\u00d4l\n\u00aa\u00af\u00f6&\u00d9\u0082W\u00d0\u000fV\u00a1\u00a8[\u008c\u00f8\u00ea\u00c1)\u00cc\u00881\u008d\u00b1^r.3\u00df\u00e1\u00b2\u00bb\f=\u0011\u0081\u00c9k\u0015\u0010\u009d\u00a1}K\u00ef\u00b3\u00ddd\u00f2\u00d4\u00f0+'\u00e0|\u00a4\u0088\u00acC\u00cc9\u00a9\u00dc{\u00af\u0091\u00cc[\u00a7\u0080#\u0003\u00b0\u00cd\u0099\u00a6k\u00e6t8\u00a4z\u0006\u00ba7\u0010\u00adM\t\n\u00d90qE\u00c9\u00a7\u008f[x\u00f1\u00862Y\u00c8 \u0000\u00cf\u00b67\u00d2\u0016\u00e6\u009fbZ\u0019\u00e9\u0083\u00c0\u0011\u0085'\u00f4\"\u00e1q\u0018\u009bs\u00b6M\u00c7\f\u0013\u0083}\u007f\u00c7\u00e9\u001cv\u001b\u00b7\u00c8\u00ac\u00c7\u008d\u00d1l\u0080@\\\r\u00ee*x\u00ac(SI\u00eao\u0001\u00d7\u00f6\u00a9m>\u00b0\u00c8N\u00c3a\u00fb\u00a1\u000f\u00ce\u001e\u00045\u00bdM\u00a6\u0096aT\u00a7\u00b8\u00c7\u00d9,0\u0004 2\u00a3\u00a6\u00dbiXJi\u00dbH\u0099J\u00c2p1\u0086?\u0098\u009b\u00e5\u0012\u00e3\u0003\u0081P\u00a9&\u00aeF\u00d5u\u00f1\u0018\u00dc2\u00bd -\u00b7\u00ec\r\u0093\\\u00e7\u009e\u0086\u0095\u00fe\u00bacT\u00c3?%O\u0017\u00e68=f1\u00a9\u0092\u0093(\u00dd\u00d3\u00aa\u00e3\u00e6Z\u001cl\u00b4\u009d\u0090\u00b8\u0098\u00dat\u0018\u00eb\u0096\u00c2\u00b6^\u009f\u00d2\\\n\f\u001fFR\u0082W5\u00e6\u00ea{\ts\u00fbx\u00de\u001c\u009aw\u00d0\u009f)\r6\u00d8({(9ufk\u0005S\u0085kQ\n\u00e6\u0000\u00fe/\u00f2\u0007\"\u00b8\u008f\u00f8\u00ae\u008b|\u00a9\u0013b\u00baS\u00dffW\u00ef_\u00e2T\u00f0\u008f\u001a(DF\u00d3s\u009d\u0081@q\u0088\u00ff\u00e9Z\u0094^,\u00e1\u00e1\"/\u0017q\u00b7r\u0000Du8\u0013\u00d0G{j\u00d2\u00fcH};\u00c7\u0096\u00c9 \u0007W\u0098|$\u00c3\u00aa\u00b8\u00b80V\u0005\u00c5\u00e3T\u0016]\u0090\u00dam\u00a6;/\u0097\u00cc\u00e9y\u0018\u00b8Cpe \u00fc\u0096d\u0016k\u001e\u008f\u00f2\u0099\u00cfaW\u008e\u001f\u0098k~\u00d0\u00c6\u0005\u00b9c%Mq\u0005\u00b4\u00e6\u0085\u009aF\u00ca \u00ad\u00e2SnbZ\u000e\u0012\u008a/$e\u00aaFB\u00a8\u00ad}\u00b9\"\u00d6\n\u00b4\u00ce\u00b0A\u00a6\u00ee\b\u001eR\u00bb\u0010\u00c2Hk\u0002Qj\u00a2\u00e1RY\u00b0/\u00d8\u00f6O\u00eb ]\u00dbB\u0098g\u00a1\u00fc\bZ\t\u00df\u00b1\u0019\u00de\u0007\u00e0\u00f5\u009c}\u0083T\u009dN^\u00a0A\u0015\t\u00fa\u007f\r\u0084\u0018;/\u00dbf<\u008chm\u00aeojAs9\u00c21(\u0092\bF,j \u00e3\u0018\u00ceNVD}dR'\u0091\u00d1\bB\u00f2\t-2G\u0081\u00ed \u00e3\u00a5F\u00aa \u008e\u009a\u00c6V`\u00f0\u00fb>M\u00f8m\u00f1\u00dd\u008b\u00dc\u0019j\u00c2DR\u00030\u00aa\u008c\nTAO\u00ef\b\u00bbn\u0010\u008e\u00d5t\u00e2\u00c6\u00d8)D\u0005r\u00d594I\u0094\u00b4 \u009fV\u00f04\u0080\u00b7\u008bJ\u00e1-\u0081\u00e6\u0090\u00eb\u00d0\u00e6,\u008f\u0005\u0096\u00fc\u00a0\u0087\u00bc\u0012\u00d4|\u001c\u0083\u00c1#\u00d5 \u008f\u00fb\u008aD\u0012\u0087\u00b9\u00fe\u00bf7\u0097\u00b6\u00aa\u00fb\u00ef\u00be\u00a6U\u001f\u00cd\u0002#I\u00ed\u00a5\u00f8\u00ab\u00a6{\u0082\u00c7\u00e28\u00bf\u008d\u00a7\u00d1\u000e\u00ec\u0014\u007f\u0006\u0089\u0097\u000b\u00d0\u000b,V\u0011\u0004D\t\u00cc$&\u0010x\u00cd\u00fb\u00e2\u00c6\u008c]\u0081\u00a9j)R\u00cd\u001e\u00a3W\u00dbf\u00dd\u00ab\u00e8u_\u0090\u001dx4M\u008f\u0013\u0002\u00db\u0010+4\u00de\u00e2CP\u00e9\u00f6\u00bd\u008a'p\u00b5\u00e9FQ\u00103\u009b\u0098n\u0007\u0090\u00a5;3z\u000f\u0090\u0085\r\u009fg8\n\u0017\u00c3\u00f0k\u00ecN\u008e\u0087R\u00b1|\u00d2\u0016W\u0091\u0089\u00e8\u008aU\u008a\n\u00aeS\u0002\u0089\u00e47C\u00d3\u00fdZ\u00b8>=\u0003\u00fawT\u00f1\u00a5\u00d1\u00ddk \u0092\u00f9\t\u009d\u0001\u0089ljS\u00b3(\u0018;\u00d2(Q=9\u00b2\u00b7\u001c\u00c7\u00b9\u00ff\u008f\u0014\u00a3\u00aeC \u00c6W\u0012\u0007u\u00b1\u0010?k\u00cays*\u00c3\u0006\u0097\u00e0\u0099\u0000\u00a6\u00e4\u00cb\u00cd(3C\u00c1wKs\u00f8Q\u0085\u00ccy\t\u00d5ER\u00a4\u00fdDe\u00e03}\u00e5\u0012\u0000\u00af\u008e\u0018d\u00d6\u00fb\u00e9\u00ae\u0012E\u00e1S\u00b0\u00fc\u00a4\u0010kc\u00c53\u0089\u0084u\u00beKBH\u00d9\u00c3u\u0087?\u0010HyS8!\u00ef\u00b0\u00c9 \u0094\u009b$2\u00c0\u001f>\u0010\u00c3x\b\u00b6\u0015K\u0012\u0086\u00db\u00f7\u00edm\u00c9\u009c\u00ac\u009e \u0085\u00d7\"h\u0019\u00f2\u00014\u0003\u00aeD\u0084\u000e\u00ad\u00c4.\u00ff\u00f6eVU\u008eY\u00f3`\u00df\u0014\u00e0u@)\u00ab8\u00ea`\u00a5\u00a8Nhm\u007f\r\u00b1\u00f1\u00bb\u001d\u00bd:\u00be\u00ff\u00a6-\u00acd\u009b\u00a1\u00ad\u00db\u00d6\u008f\u0010l\u009eG\u00ed.\u0004\u00eb\u00bd\u0087\u00cd\u008a\b\u0010\u00ffW\u001c\u0085B\u0018>\u00ee\u0098~\u00ea+\u0003I\u0000(T\u00d5H:\u00a6(2\u0013\u0094)>\u00af\u00f9\u00ea\u00bb\u0089\u00d54\u00df^\u00cb\u00a3y\u00bf\u0003J\u00d7zgX7Tp\u00af\u00da\u0002\u0094\u00c6\u00b8\u00e4 \u00d6\u001c\tO\u000e\u00b1\u00c4\u000b4O\u0091\b\u00c0\u0093\"K7B\u00d2\u00c8Q!}\u00fc\u00c4\u0019B\u00b4~<F\u00f6\u0010\u0087\u00a3\u0088\u00ce\u00fa\u00d7wd0\u00dd\u00dc\u00e2\u0094\u00cfBV\u0010\u00b6\u008f\u00a1\u00e7\u0001\u00e8\u0084'-\u00e38\u00a2\u00ea\u00fdeY(\u00fcB!A|\u00a9\u00e4\u00ca\u008e+n\u009f\u0019\u00bb\u00f1\u00ac\u00f90\u00ccp\u00e9\u0081R\u001fa3\u00d8\u00b3\u00ad\u00b1\u00ed\u00ed\u001b\u0094\u00ae{\u000ff\u00bc\u00b3 5\u00c3\u00a6\u00f8\u0019\u00b9\u00d6\u00115\u00cf\u0014p\u00fa\u0010\u00a1%}G\u008a\u0004\u0097\u0090\u0094\u008b\u0092\u00d1\u00bbW\u00d1{\u00a7\u00da\u0018\u0018\u00e6\u001d\u00d13\u00c3\"\u00a8)\u00c9z\u000b\u0097J\u0085\u00de\u007fv\u00b8\u00cb;\u00ba\u00df\u0012\u0018\u0088\u00f9\u00cbn5;\u00b6\u0088\u00009\u001e\u00c3$\u00e3\u00db\u0089\u00f9\u0089m\u009d\u0086n\u0083\u00d7\u0010\r\u00d3kIT\u00d1j\u00fe\u0097\u00ff\u00fa\u008aR\u00f3X\u00f4H!y\u00f4e{>\u00dd\u009c\f\"N\u00edU\u0010 :\u00f1\u0088'\u001b\u009a\n\u0082;3FO\u00ca\u00c8U.3\u0006\u00cb\r\u0012\u00cb\u0018Iqkn-)\u0000.\u0083U\u00e5*&R\ba\u00ee\u008b}x\u00b2B\u00a6\r\u0001\u00e6ld\u00a5\u00804\u00a0\u000e3\u0090TN\u00e2_'\u0004\u00d4\u00863\u00dbP\u00a8\u00f5\u00ca\u0080\u00f9\u00aa\u0085P*\u00ed=I\u009c_\u00be`\u00b3\u00dac\u0011\u00fd\u00e9\u00d4\u0007\u00d5\u0084\u00c4\u00f03\u00b2i:\r\u008a\u00ed\u00ae\u00a3<\u0081\u00fb\u00a1@\u00c4\u00cd\u001aE\u009e\u00ef\u00cd\u008bB)\u00bd\u0082\u00914(\u00f0;MgMZ\u00d0\u00195j\u0097\u0084\u00dd7\u00e8}\u0016\r6@\u00baX\u00d9E\u00c5d\u00d6\u00e7\u0081\u00c0\u001a\u00bd,5\u0018NRy{`_\u00d0T\u00ee\u00877\u000f\u00cauz\u00a4f\u00c0\u0017\u00ac&\u00a5\t\u00ad\u0098\u00b1c\u00a1\u001d2\u0095\u000e\u00dd\u00b5\u0089\u00ac\u00f3\u00b4\u0000\u0093\u0089\u0010\u00b7M[\u001d{EC\u00f9I\u00a8\u00e7\u00e0.\u00c2o\u00ea\u0010{\u008a\u0086\u00f3h\u00c3/xZ\n\u0015LE\u00fb\u009a# \u001c\u008d\u00e7\u00815\u001f'~\u00b5]\u000f\u00d8\u0090Q\u00e1(M\u00f4\u00de\u00c8 \u0092\u00a3\u00afQt\u00be\u00a3\u00d1\u00bdkm\u0018j\u008be\u00c7\u00dc\u00f0kc\u00dbl\u0003\u00ac\u00c7MV\u0097\u00f7\u007f\u0085FD\u0017\u0093\u00ad \u00d4\u00c7\u0007\u0007\u00ccRd\u0085\u00e6 g\u00af\u0007\u00a2lg\u0087\u00b29.\u00aa\u00b7\u00adN\u000fkO\u00ff'\u0014\u00f4\u00f5 :9\u0094*H\u00f2l\u000b\u008d\u00e6m\u00c3#\u0085b\u00cd@\u00aaW\u00a0\u00e4\u001ateO*\u00d89\u0005\u008b\u00df\u00e8\u0018[#\u009b9\u001c\u001dw\u00ef\u00e2`\u008f\u001b]\r\u00db\u00e9LI6A\\~\u0080\u00c9 \u00d7\b\u00f0\u00f9J\u00c96\u00ee\u0085j`\u0087\u0019\u0004\u00d7>\u00a2\u00d9\u008aU\u007fp\u009a\u00b8\u00ccb\u00faI1K\u00da\u0086\u0018\u00dfw\u00e4\u00d6\u00ac\u001cKr\u00d9\u00ee\u00e0w'$p\u0013\u009f*\u00f9M\u00e1\u0016;\u00db \u0092v\u009d\u0088p\u00e4B0\u0090\u00f60JB\n\u00cau\u0087\u00fd\u00d1\u00c3g\u00b3\u0084\u00abcH\u00d9a\u00e0\u009b]\u008e8-\u0011S\u009a\u0082<\u00e1qu\u0002\u00b1\u00e3Y\u0012;#\u00aa\u00c7\u0090\u00a1%\u00c1\u0097cp+.pC\u0085%%\u0082\u00eb\u00be5\u00c4+\u00bbj,\u009c$B\u008e\u00ba<\u007f@\u00dd.\u00fa\u0091\u00a6\u00ee\u00a2\u00106\u00bb\u00f9AM\u0016U\u00e9H\u00a1\u00e5p\u00b4o\u001f\u00bd(>P\u00cd.\u00b6\u00e4\u00f6/\u00a7\u0019\u00e2G\u00c6\u00e2\u0087\u00a7\u00a6w\u00c1\u00fa\u009b\u00fe\u0019?\u00ef\u00a8\u0084\u00c0\u0018\u0004O\u0081\u00ef\u00b1~\u00ec\u009a\u00c5\u0011J P\u0096\u00eb\u00eeU\u00ca9Exn\u00faR\u00ba\u00fe\u00a6=>6T\u00aa\u0003jD\u008c<\u00c9\u00c1\u00a0X\u00c7\u00c0\u00bb\u0010\u00c5\u00fd\u00fa\u00ea\u00cb^\u00ce\u000b\u0083\u00df\u00ba\u00d3Ea?O \u00b2\u00a7\te\u00f2\u00a4\u00f9L\u0082\u0091/\u00dc&\u009eV\u00c2\u0012\u00eao\u00b7\u009cU9^\u00a8\u00af4F\u0081\u00f6\u001d\u00eb \u0097`\u00a0\"W\u00cb\u0003:X\u00c5~#\u00d6\u009f\u009d\u00f2\u0011\u00ce\u00f1[\u008c\u009f\u0094\u00b0\u0005SZ\u00eav\u00fb(\u00b2\u0010f\r@\b#\u00ca\u00a9\u008ejt\u009eM\u00c8\u00e8!O\u0010\t\u00f5\u0014?0|\u00ef\u0010\u00b0}\u0003\u00a2\u0081_\u00050 \u0084\u00e1?@\u0098\u00b9\"ml\u0099,q\u00e1\u00be\u009a\u001d5\u00fdiX|\u0081\u0019\u008du\u0090\u009e\u0093OP\u00e2! \u00a2k\u00c6\u0083O4\u00f47\u0012\u00c7\u00feI2\rY\u0098\u00f3\u0015\u0097,\u0013\u00d9\u00fc\u00cc\u00ba\u00da\u00f9/\u00056\u0019\u00070\u00af`\u0014_\u00d6~9\u00fbI\u00e3e\u00bb1\u00c3H\u00f4\u00a64\u00f3r\u00f8\u00c1\u00ee\u0098\u0012\u0096\u009e;y\u00d9\u00a2\u009da\u009b\u001d\u0093\u0099\u00b7\u0005r!\u00cd\u001b\u001ay2\u00be\u008c\u0088\u0088\u00f1\u00b0\b\u00e8\u0085\u001c[\u0095a{n\u00dc\u00b3\u00f1(\u0082\u001cL\u00b9@\u00b7\u008d\u00ca\u00fc\u00d7{z\u001a\u00c5(\u00fe\u00c5\u00127\u00e2\u00a6\u00de\u0092\u0013\u00999I-u\u00fdS0\u00b7)\u0091\u00bf6\u00c4\u0017\u0088\u00e6(\u00adr\u00a7J\u00f9\"e\u00b6Y\u00c3.\\\u00c5\u0091\u0016x\u00a6\u008e\u0091o\t\u00f1\u0081\u00b8\u00ed\u00b1\u00a9\u0084\u00f0\u0010!\u0099\u00b2Z\u000f\u00bc\u0095H?UVx\u00b9\u00cdN\u00ee\u00cf;n\u00ac\u0085p\u001bY\u00f1\u0010\u0091\u00e2\u00d4\u00cb\u0083\u00f4\u00d1\u0080\u0088\u00b9\u00a2b\u00b0\u00d0\u00c2E\u00a6X\u00aa/B\u0094\u0010n_\u00d5\u00db\u00b3a\u00beQs\u009d\u001aLa/\u00a02\u0010\u00cd\u00a0\u00193\u0088\u000f3\u0016<Ir\u00c6\u0099\u0082\u0002\u00d6";
                        var17_6 = 2701;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = RotatingModule.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00d75\u00ac\u0005P\u0099\u00fbW\bq\u0095~\u0018\u00d7Nj\u00caCy]9d\u00f5c\u0012\u000ev\u00db9\u0011)P\u00a2\u0085\u00b6\u00c8\u0015\u00bd\u009f\u00dbSs\u00aa\u00e9f+\t\u00db\u000b\u00adX!7\u00bfHj\u0010\u0019\u001eU\u00db2_M\u0016m\u00a9Z\u00ca\u0013\u00dd#\u00af";
                            var17_6 = 73;
                            var14_7 = 56;
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
                            var18_3[var16_4++] = RotatingModule.zkmDecodeUtf8(var19_9).intern();
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
                RotatingModule.fb = var18_3;
                RotatingModule.gb = new String[80];
                RotatingModule.nb = new HashMap<K, V>(13);
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
                var6_12 = new long[33];
                var3_13 = 0;
                var4_14 = "\r\u00d3\u00a7\u00dd!M}\u00c1\u009b\u00e3DJ\u008f\u0012\u00fa\u00a4~\u0083\u0012\u00d5\u00f5\u00a3\u00df\u00d1\u00e9\u00a0H\u00ba\u00e5\u00d5\u0088s\u000f^\u007f\u0000\u00b9\u00e0**Fy\u0089\u00f4|\u00bd\u0019\u00a0\u00be\u00d7Y\u00a9o\u0015\u00b6'\u00d0%\u00fd\u00d6\u00fe\u00ba\u00f0\u008f\u00d0\u0096c\u00ecfr\u00b9\r_r\u0092\u001a|+\u0005\u00d7T\u00d2\u00c3\u0091(j\u001c\u00aa\u00c7+\u00d8\u00a5X\u0095\u0016\u001051\u00e8\u00b8W{eK\u0004\u009d\t\u00f9\u00b8_\u00e1\u00c8+o\u007f\u009f-\u00a5}A~\u00bf\u00e9\u00cb\u00848d\u00d3\\\u00fagd>\u00c77\u00a0|t\u00ce\n,6\u00b1\u009c\u00eb\u009e5\u00d8\u00e6\u008c7\u00bf<\u0019\u008a\u0010\u00034:F\u0013J\u00fb\u00a9A\u00c4\u0082\"\u00a7\u0092\u00c9J\u00a6\u00ec\u009f:\u00cd:>+~\u00c1\u008eI)4\u00e6v\u0002\u00ca\u00fce\u00a3\u009a[\u00d3\u00d2*n\u00aax\u00d3\u00f6,P6\u0000\u00fd-\u00cc\u00f0\u00ae\u0098\u00f1x$\u0088/\u00f6\u00bf\u00d0\u00bd\u00b5\u00bb\u00fc\u00af\u00edYr\u00e8v/9?O\u00a7s\u008e\u00bdK\u0014'\u00e9\u00ee^P\u009c6";
                var5_15 = 248;
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
                    var4_14 = "\u00d9\u00e6\u0014X\u0005q\u00daVG\u0015#\u00f5\u00ac^\u008d\u00c7";
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
        RotatingModule.lb = var6_12;
        RotatingModule.mb = new Integer[33];
        var22_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "timing", "getTiming()Lsu/catlean/xl;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "blocksPerAction", "getBlocksPerAction()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "delayPerAction", "getDelayPerAction()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "rotate", "getRotate()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "grimSilent", "getGrimSilent()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "placeMode", "getPlaceMode()Lsu/catlean/zr;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "interact", "getInteract()Lsu/catlean/xx;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "switch", "getSwitch()Lsu/catlean/yi;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "range", "getRange()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "wallRange", "getWallRange()F", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "pauseGroup", "getPauseGroup()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "mining", "getMining()Z", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "eating", "getEating()Z", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "inventory", "getInventory()Z", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "blink", "getBlink()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "render", "getRender()Lsu/catlean/lr;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(RotatingModule.class, "renderAnimation", "getRenderAnimation()Lsu/catlean/og;", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "syncColors", "getSyncColors()Z", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "fillColor", "getFillColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(RotatingModule.class, "outlineColor", "getOutlineColor()Ljava/awt/Color;", 0))};
        RotatingModule.delegatedProperties = var22_20;
    }

    public static void zkmSetFlag(int[] nArray) {
        u = nArray;
    }

    public static int[] int_arr_zkmGetFlag() {
        return u;
    }

    private static UnsupportedOperationException zkmRethrow(UnsupportedOperationException unsupportedOperationException) {
        return unsupportedOperationException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4220;
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
                throw new RuntimeException("su/catlean/_3", exception);
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
            RotatingModule.gb[n2] = RotatingModule.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return gb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = RotatingModule.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5107;
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
                throw new RuntimeException("su/catlean/_3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            RotatingModule.mb[n2] = n3;
        }
        return mb[n2];
    }

    private static int e(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = RotatingModule.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(RotatingModule.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(RotatingModule.class, "e", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

