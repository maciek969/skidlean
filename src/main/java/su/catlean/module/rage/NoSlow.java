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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ClearClickMapEvent;
import su.catlean.api.event.events.network.SlowDownEvent;
import su.catlean.api.event.events.player.KeyboardInputEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.ShouldStopSprintingEvent;
import su.catlean.api.event.events.world.SlipperinessEvent;
import su.catlean.api.event.events.world.StepOnSlimeEvent;
import su.catlean.api.event.events.world.SweetBerryBushCollisionEvent;
import su.catlean.api.event.events.world.VelocityMultiplierEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ClientInputAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.basic.Sprint;
import su.catlean.module.rage.NoSlow$WhenMappings;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.AntiCheatMode;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class NoSlow
extends Module {
    @NotNull
    public static final NoSlow INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final BooleanSetting limitPackets$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final BooleanSetting mainHand$delegate;
    @NotNull
    private static final SettingGroup selection$delegate;
    @NotNull
    private static final BooleanSetting food$delegate;
    @NotNull
    private static final BooleanSetting projectiles$delegate;
    @NotNull
    private static final BooleanSetting shield$delegate;
    @NotNull
    private static final BooleanSetting soulSand$delegate;
    @NotNull
    private static final BooleanSetting honey$delegate;
    @NotNull
    private static final BooleanSetting slime$delegate;
    @NotNull
    private static final BooleanSetting ice$delegate;
    @NotNull
    private static final BooleanSetting sweetBerryBush$delegate;
    @NotNull
    private static final BooleanSetting sneak$delegate;
    @NotNull
    private static final BooleanSetting crawl$delegate;
    private static boolean U;
    private static boolean L;
    private static boolean i;
    private static boolean u;
    private static boolean k;
    private static boolean h;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map l;

    private NoSlow() {
        super("no-slow", Categories.getRage(), null, null);
    }

    @NotNull
    public final AntiCheatMode getMode() {
        return (AntiCheatMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final boolean getLimitPackets() {
        return (Boolean)limitPackets$delegate.getValue(this, delegatedProperties[1]);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final boolean getMainHand() {
        return (Boolean)mainHand$delegate.getValue(this, delegatedProperties[3]);
    }

    private final SettingParent getSelection() {
        return (SettingParent)selection$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getFood() {
        return (Boolean)food$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getProjectiles() {
        return (Boolean)projectiles$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getShield() {
        return (Boolean)shield$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getSoulSand() {
        return (Boolean)soulSand$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getHoney() {
        return (Boolean)honey$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getSlime() {
        return (Boolean)slime$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getIce() {
        return (Boolean)ice$delegate.getValue(this, delegatedProperties[11]);
    }

    private final boolean getSweetBerryBush() {
        return (Boolean)sweetBerryBush$delegate.getValue(this, delegatedProperties[12]);
    }

    private final boolean getSneak() {
        return (Boolean)sneak$delegate.getValue(this, delegatedProperties[13]);
    }

    private final boolean getCrawl() {
        return (Boolean)crawl$delegate.getValue(this, delegatedProperties[14]);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block15: {
            block17: {
                block16: {
                    block14: {
                        NoSlow.i = NoSlow.L;
                        var18_2 = AntiCheatMode.f();
                        v0 = NoSlow.L;
                        if (var18_2 == 0) {
                            v0 = v0 == false;
                        }
                        NoSlow.L = v0;
                        v1 = NoSlow.u;
                        if (var18_2 == 0) {
                            if (v1 != 0) {
                                NoSlow.L = true;
                                NoSlow.u = false;
                            }
                            v1 = NoSlow.U;
                        }
                        if (var18_2 == 0) {
                            if (v1 != 0) {
                                Globals.getMc().options.sneakKey.setPressed(false);
                                Globals.getPlayer().setSprinting(false);
                                NoSlow.U = false;
                            }
                            v1 = Globals.getPlayer().isUsingItem();
                        }
                        if (var18_2 != 0) break block14;
                        if (v1 == 0) break block15;
                        v1 = Globals.getPlayer().isRiding();
                    }
                    if (var18_2 != 0) break block16;
                    if (v1 != 0) break block15;
                    v1 = Globals.getPlayer().isGliding();
                }
                if (var18_2 != 0) break block17;
                if (v1 != 0) break block15;
                v1 = NoSlow$WhenMappings.V[this.getMode().ordinal()];
            }
            switch (v1 ? 1 : 0) {
                case 1: {
                    PacketUtil.sendPacket(new UpdateSelectedSlotC2SPacket(Globals.getPlayer().getInventory().getSelectedSlot()));
                    if (var18_2 == 0) break;
                }
                case 2: {
                    if (Globals.getPlayer().getActiveHand() == Hand.OFF_HAND) {
                        PacketUtil.sendPacket(new UpdateSelectedSlotC2SPacket(Globals.getPlayer().getInventory().getSelectedSlot() % 8 + 1));
                        PacketUtil.sendPacket(new UpdateSelectedSlotC2SPacket(Globals.getPlayer().getInventory().getSelectedSlot()));
                        if (var18_2 == 0) break;
                    }
                    v2 = this.getMainHand();
                    if (var18_2 == 0) {
                        if (v2 == 0) break;
                        v2 = Globals.getPlayer().getItemUseTime();
                    }
                    v3 = 3;
                    if (var18_2 != 0) ** GOTO lbl49
                    if (v2 <= v3) ** GOTO lbl54
                    v2 = Globals.getPlayer().age;
                    v3 = 2;
lbl49:
                    // 2 sources

                    v4 = v2 % v3;
                    if (var18_2 != 0) ** GOTO lbl53
                    if (v4 == 0) ** GOTO lbl54
                    v4 = (int)this.getLimitPackets();
lbl53:
                    // 2 sources

                    if (v4 != 0) break;
lbl54:
                    // 3 sources

                    InteractionUtil.useItem$default(Hand.OFF_HAND, 0.0f, 0.0f, 6, null);
                    break;
                }
            }
        }
    }

    @Flow
    private final void onSlipperiness(SlipperinessEvent slipperinessEvent) {
        block9: {
            boolean bl;
            block10: {
                block11: {
                    int n;
                    block8: {
                        block6: {
                            block7: {
                                n = AntiCheatMode.f();
                                bl = this.getSlime();
                                if (n != 0) break block6;
                                if (!bl) break block7;
                                bl = Intrinsics.areEqual(slipperinessEvent.getBlock(), Blocks.SLIME_BLOCK);
                                if (n != 0) break block6;
                                if (bl) {
                                    slipperinessEvent.cancel();
                                }
                            }
                            bl = this.getIce();
                        }
                        if (n != 0) break block8;
                        if (!bl) break block9;
                        bl = Intrinsics.areEqual(slipperinessEvent.getBlock(), Blocks.ICE);
                    }
                    if (n != 0) break block10;
                    if (bl) break block11;
                    bl = Intrinsics.areEqual(slipperinessEvent.getBlock(), Blocks.PACKED_ICE);
                    if (n != 0) break block10;
                    if (bl) break block11;
                    bl = Intrinsics.areEqual(slipperinessEvent.getBlock(), Blocks.BLUE_ICE);
                    if (n != 0) break block10;
                    if (bl) break block11;
                    bl = Intrinsics.areEqual(slipperinessEvent.getBlock(), Blocks.FROSTED_ICE);
                    if (n != 0) break block10;
                    if (!bl) break block9;
                }
                bl = Globals.getMc().options.jumpKey.isPressed();
            }
            if (!bl) {
                slipperinessEvent.cancel();
            }
        }
    }

    @Flow
    private final void onVelocityMultiplier(VelocityMultiplierEvent velocityMultiplierEvent) {
        if (this.getSoulSand() && Intrinsics.areEqual(velocityMultiplierEvent.getBlock(), Blocks.SOUL_SAND) || this.getHoney() && Intrinsics.areEqual(velocityMultiplierEvent.getBlock(), Blocks.HONEY_BLOCK)) {
            velocityMultiplierEvent.cancel();
        }
    }

    @Flow
    private final void onStepOnSlime(StepOnSlimeEvent stepOnSlimeEvent) {
        block0: {
            if (!this.getSlime()) break block0;
            stepOnSlimeEvent.cancel();
        }
    }

    @Flow
    private final void onClearClickMap(ClearClickMapEvent clearClickMapEvent) {
        block0: {
            if (!h) break block0;
            clearClickMapEvent.cancel();
        }
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Flow
    private final void onKeyboardInput(KeyboardInputEvent var1_1) {
        block17: {
            block20: {
                block19: {
                    block18: {
                        block13: {
                            block11: {
                                block12: {
                                    block16: {
                                        block14: {
                                            block15: {
                                                var10_2 = AntiCheatMode.f();
                                                v0 = this;
                                                if (var10_2 != 0) break block11;
                                                if (v0.getMode() != AntiCheatMode.MATRIX) break block12;
                                                v1 = Globals.getPlayer().isUsingItem();
                                                if (var10_2 != 0) break block13;
                                                if (!v1) break block12;
                                                v1 = Globals.getPlayer().isGliding();
                                                if (var10_2 != 0) break block13;
                                                if (v1) break block12;
                                                v2 = Globals.getPlayer().input;
                                                Intrinsics.checkNotNull(v2, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
                                                ((ClientInputAccessor)v2).setMoveVector(new Vec2f(Globals.getPlayer().input.getMovementInput().x * 5.0f, Globals.getPlayer().input.getMovementInput().y * 5.0f));
                                                v3 = Globals.getPlayer().isOnGround();
                                                if (var10_2 != 0) break block14;
                                                if (!v3) break block15;
                                                cfr_temp_0 = Globals.getPlayer().input.getMovementInput().y - 0.0f;
                                                v4 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                                if (var10_2 == 0) {
                                                    v4 = v4 == false ? (float)true : (float)false;
                                                }
                                                if (v4 != false) ** GOTO lbl-1000
                                                cfr_temp_1 = Globals.getPlayer().input.getMovementInput().x - 0.0f;
                                                v5 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 < 0.0f ? -1 : 1);
                                                if (var10_2 == 0) {
                                                    v5 = v5 == false ? (float)true : (float)false;
                                                }
                                                if (v5 == false) {
                                                    v6 = 0.35f;
                                                } else lbl-1000:
                                                // 2 sources

                                                {
                                                    v6 = 0.5f;
                                                }
                                                break block16;
                                            }
                                            cfr_temp_2 = Globals.getPlayer().input.getMovementInput().y - 0.0f;
                                            v3 = cfr_temp_2 == 0.0f ? 0 : (cfr_temp_2 < 0.0f ? -1 : 1);
                                        }
                                        if (var10_2 == 0) {
                                            v3 = v3 == false;
                                        }
                                        if (v3) ** GOTO lbl-1000
                                        cfr_temp_3 = Globals.getPlayer().input.getMovementInput().x - 0.0f;
                                        v7 = cfr_temp_3 == 0.0f ? 0 : (cfr_temp_3 < 0.0f ? -1 : 1);
                                        if (var10_2 == 0) {
                                            v7 = v7 == false ? (float)true : (float)false;
                                        }
                                        if (v7 == false) {
                                            v6 = 0.47f;
                                        } else lbl-1000:
                                        // 2 sources

                                        {
                                            v6 = 0.67f;
                                        }
                                    }
                                    var11_3 = v6;
                                    v8 = Globals.getPlayer().input;
                                    Intrinsics.checkNotNull(v8, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
                                    ((ClientInputAccessor)v8).setMoveVector(new Vec2f(Globals.getPlayer().input.getMovementInput().x * var11_3, Globals.getPlayer().input.getMovementInput().y * var11_3));
                                    if (var10_2 == 0) break block17;
                                }
                                v0 = this;
                            }
                            v1 = v0.shouldBypass(true);
                        }
                        if (var10_2 != 0) break block18;
                        if (!v1) break block17;
                        v1 = Globals.getPlayer().isUsingItem();
                    }
                    if (var10_2 != 0) break block19;
                    if (!v1) break block17;
                    v9 = Globals.getPlayer();
                    if (var10_2 != 0) break block20;
                    v1 = v9.isGliding();
                }
                if (v1) break block17;
                v9 = Globals.getPlayer();
            }
            v10 = v9.input;
            Intrinsics.checkNotNull(v10, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
            ((ClientInputAccessor)v10).setMoveVector(new Vec2f(Globals.getPlayer().input.getMovementInput().x * 5.0f, Globals.getPlayer().input.getMovementInput().y * 5.0f));
        }
    }

    @Flow
    private final void onSlowDown(SlowDownEvent slowDownEvent) {
        block3: {
            block2: {
                boolean bl;
                block0: {
                    block1: {
                        int n = AntiCheatMode.f();
                        bl = Globals.getPlayer().isCrawling();
                        if (n != 0) break block0;
                        if (!bl) break block1;
                        bl = this.getCrawl();
                        if (n != 0) break block0;
                        if (bl) break block2;
                    }
                    bl = this.getSneak();
                }
                if (!bl) break block3;
            }
            slowDownEvent.cancel();
        }
    }

    @Flow
    private final void onShouldStopSprinting(ShouldStopSprintingEvent shouldStopSprintingEvent) {
        block0: {
            if (!this.shouldBypass(false) || !Globals.getPlayer().isUsingItem()) break block0;
            shouldStopSprintingEvent.cancel();
        }
    }

    @Flow
    private final void onSweetBerryBushCollision(SweetBerryBushCollisionEvent sweetBerryBushCollisionEvent) {
        block0: {
            if (!this.getSweetBerryBush()) break block0;
            sweetBerryBushCollisionEvent.cancel();
        }
    }

    private final boolean shouldBypass(boolean bl) {
        if (this.getMode() == AntiCheatMode.MATRIX) {
            return false;
        }
        if (!this.getFood() && Globals.getPlayer().getActiveItem().getComponents().contains(DataComponentTypes.FOOD)) {
            return false;
        }
        if (!this.getShield() && Intrinsics.areEqual(Globals.getPlayer().getActiveItem().getItem(), Items.SHIELD)) {
            return false;
        }
        if (!this.getProjectiles() && (Intrinsics.areEqual(Globals.getPlayer().getActiveItem().getItem(), Items.CROSSBOW) || Intrinsics.areEqual(Globals.getPlayer().getActiveItem().getItem(), Items.BOW) || Intrinsics.areEqual(Globals.getPlayer().getActiveItem().getItem(), Items.TRIDENT))) {
            return false;
        }
        if (!this.getMainHand() && Globals.getPlayer().getActiveHand() == Hand.MAIN_HAND) {
            return false;
        }
        if (this.getMode() == AntiCheatMode.GRIM_2) {
            if (Globals.getPlayer().isUsingItem()) {
                if (Globals.getPlayer().getItemUseTime() < this.getDelay()) {
                    if (Globals.getPlayer().getItemUseTime() <= 2) {
                        h = true;
                        InteractionUtil.clickSlot$default(1, 0, null, true, 6, null);
                        h = false;
                    }
                } else {
                    return true;
                }
            }
            return false;
        }
        if (this.getMode() == AntiCheatMode.GRIM_3) {
            if (Globals.getPlayer().isUsingItem()) {
                switch (Globals.getPlayer().getItemUseTime()) {
                    case 1: {
                        k = Globals.getPlayer().isSprinting();
                        Globals.getPlayer().setSprinting(false);
                        Sprint.INSTANCE.h(true);
                        return false;
                    }
                    case 2: {
                        h = true;
                        InteractionUtil.clickSlot$default(1, 0, null, true, 6, null);
                        h = false;
                        Sprint.INSTANCE.h(true);
                        return false;
                    }
                    case 3: {
                        Globals.getPlayer().setSprinting(k);
                        Sprint.INSTANCE.h(true);
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        if (this.getMode() == AntiCheatMode.GRIM_V3) {
            if (!Globals.getMc().options.sneakKey.isPressed()) {
                if (Globals.getPlayer().getItemUseTime() < 3) {
                    return false;
                }
                if (Globals.getPlayer().isOnGround()) {
                    if (Globals.getMc().options.jumpKey.isPressed()) {
                        if (i) {
                            return true;
                        }
                        u = true;
                        return false;
                    }
                    return Globals.getPlayer().age % 2 == 0;
                }
                return !L;
            }
            return false;
        }
        return !Globals.getPlayer().getOffHandStack().getComponents().contains(DataComponentTypes.FOOD) && !Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.SHIELD) || this.getMode() != AntiCheatMode.GRIM || Globals.getPlayer().getActiveHand() != Hand.MAIN_HAND;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == AntiCheatMode.GRIM;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == AntiCheatMode.GRIM_2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        NoSlow.a = ZkmKeys.a(6582026244826530436L, 2955564986140426933L, MethodHandles.lookup().lookupClass()).a(34341136588424L);
                        var20 = NoSlow.a ^ 81621406217125L;
                        NoSlow.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[48];
                        var16_4 = 0;
                        var15_5 = "ce\u0006\u00f3\u00f3Z\u0098\u00c9(\u00d8\u00d7\u00029\u009aL\u00fa\u00f0\u009e\u000fW\u009c}\u00c3\u00d0\u00e7\u0082\u0085b^\u0086\u00a2\u00840\u00a0\u008a \\E:,\u00ea\u00e2`\u00d3\u00a0\u00a4'Q\u00135=\u00a8\u00a8nc\u0002\u009e\u0006\u00bb\u0015\u008eT\u009c\u00d0\u00d4\u00d6\u00bdDG\"\u00d3L}\u008a|\u0089\u00b8#5\u00a0\u00a0 +\u008c\u0090\u008f\u00e0\u0013\u00c0\u00afRK\u00c9V\u0098G\u00a1\u00b6z]\u00d4a\u00d64m\u0003\u00f1&T\u00d4\u00eb\u00ad\u00e9k 6>\u0010\u00d1\u00fd\u00db8A\u00e90&<QRU\u00bc2\u00fb\u00be\u0086\u00a0\u00ac4\u00bb\u001d\u0098&t|\u0004\t\u00d8 V\u00af\u0093\u00bc\u00ae\u009e\u0003L\u00d4o\u0093\u00a7\u0004\u00a1<\u00075P\u00fa\u0018\u00c2\u00ec\u00e4\r\u00e1A\u007f\u00a7\u008f\u00c0\u00a3\u007f\u00180\u00cff_\u00f0\u00acF\u00b3q\u0082\f-\u00a9\u008d_\u0002\u0006-\u00e8\u0084\u00c3\u00c5\u00ac\u00c1\u0018p\u00db\u008d\u00cd\u00ce\u00d7];\u009b\u00caG\u0010\u0018\u009d1M\u00d8\u009e\u0089\u001fY\u00ab\u00f1\u00e2\u0010RW\u0096%\u0007\u00e8k\u00a4\u00cc\u0015\u00cb\u009d\u00c1\u009d\u0083V\u0010\u00884mo\u0005\u00df\u00ed\u0099\u00b8X0\u0002\u00bb\u00b7A/\u0010\u00ce5\u00ae\u009c\u00f1\u00d9H&\u009e.u\u00b6\u00cenJ\u00f203|\u00eb\u00c5\u0018\u00b1h\u00bd\u009c<\u00a9Sq\u00fae(:\u00f3$\u00ffLQ\u0007~\u009449\u00c5\u00f8N\u009a\u008eJ\u0002\u00d1\u00dc\u0006{\u009d\u0087 \u0007\u0096\u00fd\u0007\u00b9\u008d\u00dc \u0010\u00de\u00d9\u00ca1[\u009a/C\u00bd\u00f1\u0012\u00bc\u008b=\u00e9\u00ac\u00c9\u00c4\u00c3\u00c6+t\u0099\u0018g\u00cd\u00af\u00ab:<\u00dd\u00104\t\u0087\u00b6\u0018\u00a4c\u00a3\u008a\u008e\u009f\u0012\u000e-|\u0010\u0010y\u000bP)-\u00c3\u00a4\u00a3\u009f\u00b1R\u0017\u0096\u008dj\u009e\u00104\u00d3\u007fg\u0017u\u00b2,\u00a7=5tT\t\u00ad\u00a9\u0018\u00cf\u0015\u00a0\u0082C\u0094\u00ac\u00e5\u00fd[\u00c9\u0000^\u00b9\u008b.\u00e6\u00d3WR\u0010\u00e3g\u00b0 \u00dc\u0089\u00cf\u0002\u0082\u00d4\u009bP\u00cc\u00f3\u0092W\u00c5\u00c1\u00af<\f\u00d7\u00ec\u0090\u008d\u0098\u00b0=\u00b8\u00e1r\u001ckNG= <\u00ba\t$7\u00c9?\u00b1\u00b5\u0007\u00da\u0093g\u00ec\u00e1\u00f8U?\u00b4x\u0095*\u00e5\n\u0095\u00da\u001d2$\u00bd\u00ac\u00a3 \u0000\u00e4\u0085\u00f4\u00a3\u001c)$\u00d8\n\u00c1\u00ed\u00aa\u00e2p8\u0019qS\u00e7\u00b3\u0012\u0006\u00ea\u00bd-({\u00a2\u00f8\u00d6\u00a1 \u00ae\u00f8\u00da\u00e2\u00cb\u0086\u007f\u0091]\u0089A\u009c\u00fa\u00ff\u00dfN\u00ed*D\u00c3`UA\u00b6\u0095\u009bq\u0093\u008f@1? L\u00fc\u001b\u00920\n\u00eby\u00c1\u00e3<LR\u00e8L\u00e6\u00ef\u00fao\u00b1\u0085\u0094u=\u00b2\u00aaB\u00db\u00c8\u00a0\u0091\u0085\u0018\u00fb\u00a1\u00adX\u00ab\u0082\u008a\rz-\b\u00f2\u008f\u00c4n@\u00bb\u0094>%\u00a8\u001d\u001a\u0083\u0018\u00b46%`\u00f1\u00898*\u00d6g\u0096\u00e0\u00cf-\u0017\u00c8\u0096@\u00bf%N\u001a\u0010O0\u00cf\u0093fz\u00da\u00f2\u008c\u00c0\u0006\u00f3\u0012\tU\u00c8\u000b\u0088sZ\u00f1\u00ba_\u0081\u00a98Y?\u00ba\u0013\u00e7?\u0095\\\u00cf\u00ed@-P\u00a6+\u007f\u00d6\u00df\u00f5r\u0006S\u0095M\u0010_h\u00b5\u00c0\u001c\u00d2\u000b\u00b2\u00ca\u0098\b\u0006\u00dce\u0004\r Ld\u00e5\u0001\u008di\u00ee\u009a\u00ba\u00d3\u00fb\u009e\u0082,\u00be\u00b6\u008a<\u0005\u0017\u009c<O\u0099a\u00d7\u00c2l\u00e6^\u0083\u00b3 \u00ab\u0099\u008b%,\u00bc\u00fa\u00c8H-\u0081q\u00f0\u0002\u00a9,*='\u0091\u00bf;\u00c8u\u00b6#_)%\u00b1j\u000b\u0010f\u00cf\u00cf\u009e\u00af\u00a3 Y\u001e\u00fe\u00f9\u00e0\u008f\r\u00f9\u00b0(Z\u00d3\u00f95P%\u0094\u0089\u00a2P\u00d3\u0011\u00cdp\u00f4\u00ba&Km\u00bc@\u00ad\b\"\u0089m\u00fa\u00f6\u0011\u00a0\u00bdb\u001c\r\n\u00f6\u00d6\u00c9\u00e5\u00f9\u0010>\u00aad\u00a5\u00a9\u009e\u00f2D\b\u009b<\u008b\u00ef\u0013\u00cf\u0011\u0010\u000f\u00db\u00cd\u00e2\u00e1\u00e4G\u0003\u00ed\u0099\u00c9\u0095\u0007\u0002\u008b\u00a6\u0010\u0084\u00a9\u0095\u008b\u0016s\u0080\u00a2\u00e4O\u00f1*\u00fcn\u00c7f \u00ab4,J\u00990\u009313p\u00ab\u008f\\t\u00af\u00cc\u0091\u00acE4\u00da\u00e3<D\u00f7~\u00bas\u0095\u00b3<O\u0010B#\u009b*x\u0013\u00c8K\u00fc\u0081\u0086\u0013\u00f2n\u00a3\u00d7\u0010\u00fe\u0080\u0006\u00fc\u009f\u00a4\u00d7_a\u001b\u00bap\u001e%\u0006P \u0099\u00c0\u000e\u00c3Y\u007f\u00e0g\u00d8\u001b\u00cd\u00aa\u00f5q\u0081\u009e\u00e5\u0018\u0094\u0087;K\u0087V\u00815f8t\u0018&\u00d3\u0010\u00b2@ux\u00be\u00c4[d\u00bb\u00ef\u00dc\u0005E9\u001b\u00f1(\"52<?w+\u00b7\"\u0010Z\u00a5\u00cdq\u00eb*\u00be\u00bc\u0002\u0007\u0013\u0083[\u001a@Sv\u00de2\u00e7<)pzg\u00e3\u00dd\u00a91%(\u00b6O\u00b5\u0002\u00d5CA.\u0000k\u00f0\u00b2\u00f2\u00b0\u009e\u00b3\u00acu_\u0012\u00f2i\u00f4\u0012}cOQ\u0096\u00f9>\u00b8\u00d0\u0081\u0084\u00a8\u0082UD\u008b\u0090\u00dcT2\u00e8n9%\u00c3Cv\u00e8\u00fbK\u00a7\u008b=)\u00bb\u00e8\u0087\u0001\u00ca/\u0010\u00f3\u008d\u00ba\u00ed\u0080h\u00f9\u00ee/\u0087a\u0087]\u00c2N\u0002\u00ea\u00e5p\u00c00\u00a0\u0099\u00ab\u00a8f\u009e>NcH\u009b\u00be\u0082;G~\u00ad\u000e\u0086e\u00c0\u0088\u00b5H'T\r\u00de\u00be\u0090\u00a1\u00f6\u0017)\u0089\bO\u00ddw\u00fb\u00e1{4\u00fe\u008f\u00b2HL\u00fd\u00d3C\u00a7\u0086\u0084\u0098'\u00cf\u00d2qu*\u000f\u00c4\u00d2L\u00ae\u008e\u00ba\u00a5}\u00c5.\u0011\u00a0\u0003r\u00a6\u00a0\u00a8\u009au\u00a4G2hU\u00bc\u00bd7s\u009b\u00a8Ry\u00e6.\u00d0\u00a9\u00d8\u0010Ov\u00b8I\u001b\u0099\u00c8\u00d9y\u00bfX\u00973D}.\u0010@\u00b1\u008fND\u00df4P\u00e1\u00b0\u00bc!\u00d5\u0085L\u00d5\u0088(\u0013\u00ca\u00f7Q\u0005\u00b4X5/\u009b3\\\u00c2\u0092F\u00b7\u001b+\u0004{\u00d0W\u00cb-\u00c6\u008dSwJ\u00e1GE\fPN\u000e\u00d9\u0098\u0017z*V\u00e38fe\u0015\u00e8\u00c8\fh\u0015p\u009c\u00dc\u0012y^\u00d3\u0011;\u00e3>\u008c\u0094X`~\u0091ZC\u0003\u00ac3k\u00e8\u00cdZ>\u00a5\u00e6\u00f0\u00d4\u00dc\u00b4\u0082~-\u00b3 \u0092|\u00a1RqyM\u0087\u00ec\r\u00e8\u0014u\u001b\u00d2\u008bk\u00ff\u00cen\u00b6b\u00bc'\u0000\u00f3\u00b0\u0014\u00cfT\u0081\u00b3\u0017\r\u0000\u00aeI\u00b7wrI\u00d0\u00ca\u0010{\u0010\u00af\u0098z\u0080\u00d7\u00af(\u00b7\u0084,I\b\u0003\u00c2>\u00f2 MD)\b\u008c\u00d3\u00c43\u00f5&\u00c9p\u00cd\u00fe\u00eas\u0099\u00c9m8\u0005s\u00fb\u001c\u00ec\u00c7\u001e\u00843\u0097t\u00cd\u0010e\u00b7\u00d1\u000e\u0080\u00a3\u00b4\u00b8\u00826\u0080\u0007\u00e2w\u00e4]";
                        var17_6 = 1477;
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
                            var18_3[var16_4++] = NoSlow.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0095\u00b3\r\u00c6P\u00ffU\u00ac\u00bf\u00ae\u00d1Q+\u000fS\u0003 \u00ad\u00a6\u00ea\u00aa\u00a6\u00cc \u00b5\u00fc\u00be\u00e8\u0019\u0006\f\u001d^\u00da\u00fe\u00c2\u00fe\u0084_:\u00d48ao\u00c8\u00bf\u00d8\u00fb\u00b7";
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
                            var18_3[var16_4++] = NoSlow.zkmDecodeUtf8(var19_9).intern();
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
                NoSlow.b = var18_3;
                NoSlow.c = new String[48];
                NoSlow.l = new HashMap<K, V>(13);
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
                var6_12 = new long[19];
                var3_13 = 0;
                var4_14 = "\u0089\u001ch\u00bf\u00f2\u00f4;dO\u00bb\u00d8O\u00c1\u00a0%\u0090\u0099]\u00c8\u00ce\u008c\u00cb#[\u00e8\u0080Q\u00ba`\u00bd\u0092AM\u0084}5\u00fd R\u0086\u0089\u008fmW\u00e9J5\u00b3\u00baG\u00f4\u00fb\u00b5Z\u0013P\\R\u0015`ZC\u00e8i\u008bO\u008f\u00a8\u00aa\u0092\u00d8\u0003\u00f9\u008aP/a\u0094\u00e4x\u00efNR]\u00cc\u00feNE\u00be\u00f9\u008a\u00b9T\u008e\u00f8U\u008d\u00df\u00d5$\u0090\u001d\u00a4\u007fx\u00bb\u00af\u00d4\u0015\u00f5#Z\u00de1D~\u0006 ?z\u00a4u\u00ad\u00c2z6\u00a5\u0018V\u009c8R\u00f8\u00f2\u0001]";
                var5_15 = 136;
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
                    var4_14 = "\u0012hB\u00aa\t\u00bbAp\u001a\u0085\u00be\u00a2\u00ac\u0091\u0080e";
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
        NoSlow.e = var6_12;
        NoSlow.g = new Integer[19];
        var35_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(NoSlow.class, "mode", "getMode()Lsu/catlean/l_;", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "limitPackets", "getLimitPackets()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "mainHand", "getMainHand()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "selection", "getSelection()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "food", "getFood()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "projectiles", "getProjectiles()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "shield", "getShield()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "soulSand", "getSoulSand()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "honey", "getHoney()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "slime", "getSlime()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "ice", "getIce()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "sweetBerryBush", "getSweetBerryBush()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "sneak", "getSneak()Z", 0)), Reflection.property1(new PropertyReference1Impl(NoSlow.class, "crawl", "getCrawl()Z", 0))};
        NoSlow.delegatedProperties = var35_20;
        NoSlow.INSTANCE = new NoSlow();
        NoSlow.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)NoSlow.INSTANCE, "mode", (Enum)AntiCheatMode.VANILLA, null, null, 12, null);
        NoSlow.limitPackets$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "limit-packets", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        NoSlow.delay$delegate = SettingFactory.intSetting$default(NoSlow.INSTANCE, "delay", 3, new IntRange(1, 10), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        NoSlow.mainHand$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "main-hand", true, null, null, 12, null);
        NoSlow.selection$delegate = SettingFactory.settingGroup$default(NoSlow.INSTANCE, "selection", AuraTargetsMode.BOOLS, null, 4, null);
        NoSlow.food$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "food", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.projectiles$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "projectiles", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.shield$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "shield", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.soulSand$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "soul-sand", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.honey$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "honey", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.slime$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "slime", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.ice$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "ice", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.sweetBerryBush$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "sweet-berry-bush", true, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.sneak$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "sneak", false, NoSlow.INSTANCE.getSelection(), null, 8, null);
        NoSlow.crawl$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoSlow.INSTANCE, "crawl", false, NoSlow.INSTANCE.getSelection(), null, 8, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2D2D;
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
                throw new RuntimeException("su/catlean/ug", exception);
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
            NoSlow.c[n2] = NoSlow.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoSlow.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ug" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3AA4;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])NoSlow.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    NoSlow.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ug", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            NoSlow.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = NoSlow.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ug" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoSlow.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(NoSlow.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

