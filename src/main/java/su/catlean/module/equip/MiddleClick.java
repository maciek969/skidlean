/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
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
import kotlin.reflect.KProperty;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PickUpBlockEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.Trajectories;
import su.catlean.module.equip.MiddleClickOnBlockMode;
import su.catlean.module.equip.MiddleClickThrowThread;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.ArmorUtil;
import su.catlean.util.FriendManager;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.MovementUtil;
import su.catlean.util.Timer;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class MiddleClick
extends Module {
    @NotNull
    public static final MiddleClick INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting onBlock$delegate;
    @NotNull
    private static final ModeSetting onAir$delegate;
    @NotNull
    private static final ModeSetting onEntity$delegate;
    @NotNull
    private static final ModeSetting onFlying$delegate;
    @NotNull
    private static final BooleanSetting silent$delegate;
    @NotNull
    private static final BooleanSetting inventory$delegate;
    @NotNull
    private static final IntSetting swapDelay$delegate;
    @NotNull
    private static final BooleanSetting antiWaste;
    @NotNull
    private static final IntSetting stopOn;
    @NotNull
    private static final IntSetting xpFactor$delegate;
    @NotNull
    private static final BooleanSetting pearlOnRelease$delegate;
    @NotNull
    private static final Timer cooldownTimer;
    private static boolean m;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map i;

    private MiddleClick() {
        super("middle-click", Categories.getEquip(), null, null);
    }

    private final MiddleClickOnBlockMode getOnBlock() {
        return (MiddleClickOnBlockMode)((Object)onBlock$delegate.getValue(this, delegatedProperties[0]));
    }

    private final MiddleClickOnBlockMode getOnAir() {
        return (MiddleClickOnBlockMode)((Object)onAir$delegate.getValue(this, delegatedProperties[1]));
    }

    private final MiddleClickOnBlockMode getOnEntity() {
        return (MiddleClickOnBlockMode)((Object)onEntity$delegate.getValue(this, delegatedProperties[2]));
    }

    private final MiddleClickOnBlockMode getOnFlying() {
        return (MiddleClickOnBlockMode)((Object)onFlying$delegate.getValue(this, delegatedProperties[3]));
    }

    private final boolean getSilent() {
        return (Boolean)silent$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getInventory() {
        return (Boolean)inventory$delegate.getValue(this, delegatedProperties[5]);
    }

    private final int getSwapDelay() {
        return ((Number)swapDelay$delegate.getValue(this, delegatedProperties[6])).intValue();
    }

    private final boolean R() {
        long l = 0L;
        l = a ^ l;
        return (Boolean)antiWaste.getValue(this, delegatedProperties[MiddleClick.zkmBootstrap2("z", (int)27011, (long)(0x12B39E52EBB382DDL ^ l))]);
    }

    private final int P() {
        long l = 0L;
        l = a ^ l;
        return ((Number)stopOn.getValue(this, delegatedProperties[MiddleClick.zkmBootstrap2("z", (int)23890, (long)(0xB78057771261F01L ^ l))])).intValue();
    }

    private final int getXpFactor() {
        return ((Number)xpFactor$delegate.getValue(this, delegatedProperties[9])).intValue();
    }

    private final boolean getPearlOnRelease() {
        return (Boolean)pearlOnRelease$delegate.getValue(this, delegatedProperties[10]);
    }

    @Flow
    private final void onPickUpBlock(PickUpBlockEvent pickUpBlockEvent) {
        pickUpBlockEvent.cancel();
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block3: {
            block15: {
                Object object;
                block18: {
                    boolean bl;
                    HitResult hitResult;
                    int n;
                    block16: {
                        block17: {
                            block13: {
                                block14: {
                                    block12: {
                                        block4: {
                                            block5: {
                                                block10: {
                                                    Object object2;
                                                    block11: {
                                                        boolean bl2;
                                                        block8: {
                                                            block9: {
                                                                block6: {
                                                                    block7: {
                                                                        MinecraftClient minecraftClient;
                                                                        block2: {
                                                                            n = MiddleClickThrowThread.zkmIntConstant();
                                                                            minecraftClient = Globals.getMc();
                                                                            if (n != 0) break block2;
                                                                            if (minecraftClient.currentScreen != null) break block3;
                                                                            minecraftClient = Globals.getMc();
                                                                        }
                                                                        hitResult = minecraftClient.crosshairTarget;
                                                                        bl = Globals.getMc().options.pickItemKey.isPressed();
                                                                        if (n != 0) break block4;
                                                                        if (!bl) break block5;
                                                                        m = true;
                                                                        bl2 = Globals.getPlayer().isGliding();
                                                                        if (n != 0) break block6;
                                                                        if (!bl2) break block7;
                                                                        this.getOnFlying().getAction().invoke();
                                                                        if (n == 0) break block3;
                                                                    }
                                                                    bl2 = hitResult instanceof EntityHitResult;
                                                                }
                                                                if (n != 0) break block8;
                                                                if (!bl2) break block9;
                                                                bl2 = ((EntityHitResult)hitResult).getEntity() instanceof PlayerEntity;
                                                                if (n != 0) break block8;
                                                                if (!bl2) break block9;
                                                                this.getOnEntity().getAction().invoke();
                                                                if (n == 0) break block3;
                                                            }
                                                            object2 = hitResult;
                                                            if (n != 0) break block10;
                                                            bl2 = object2 instanceof BlockHitResult;
                                                        }
                                                        if (!bl2) break block11;
                                                        object2 = Globals.getWorld();
                                                        if (n != 0) break block10;
                                                        if (object2.isAir(((BlockHitResult)hitResult).getBlockPos())) break block11;
                                                        this.getOnBlock().getAction().invoke();
                                                        if (n == 0) break block3;
                                                    }
                                                    object2 = this.getOnAir().getAction().invoke();
                                                }
                                                if (n == 0) break block3;
                                            }
                                            bl = m;
                                        }
                                        if (n != 0) break block12;
                                        if (!bl) break block3;
                                        bl = Globals.getPlayer().isGliding();
                                    }
                                    if (n != 0) break block13;
                                    if (!bl) break block14;
                                    this.getOnFlying().getCondition().invoke();
                                    if (n == 0) break block15;
                                }
                                bl = hitResult instanceof EntityHitResult;
                            }
                            if (n != 0) break block16;
                            if (!bl) break block17;
                            bl = ((EntityHitResult)hitResult).getEntity() instanceof PlayerEntity;
                            if (n != 0) break block16;
                            if (!bl) break block17;
                            this.getOnEntity().getCondition().invoke();
                            if (n == 0) break block15;
                        }
                        object = hitResult;
                        if (n != 0) break block15;
                        bl = object instanceof BlockHitResult;
                    }
                    if (!bl) break block18;
                    object = Globals.getWorld();
                    if (n != 0) break block15;
                    if (object.isAir(((BlockHitResult)hitResult).getBlockPos())) break block18;
                    this.getOnBlock().getCondition().invoke();
                    if (n == 0) break block15;
                }
                object = this.getOnAir().getCondition().invoke();
            }
            m = false;
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block2: {
            long l;
            int n;
            block6: {
                MiddleClickOnBlockMode middleClickOnBlockMode;
                MiddleClickOnBlockMode middleClickOnBlockMode2;
                block8: {
                    int n2;
                    block7: {
                        block5: {
                            MiddleClick middleClick;
                            block4: {
                                boolean bl;
                                block3: {
                                    MinecraftClient minecraftClient;
                                    block1: {
                                        long l2 = a ^ 0x68ED5B19EB5DL;
                                        long l3 = l2 ^ 0x4AE595EFD10FL;
                                        n = (int)(l3 >>> 48);
                                        l = l3 << 16 >>> 16;
                                        n2 = MiddleClickThrowThread.zkmIntConstant();
                                        minecraftClient = Globals.getMc();
                                        if (n2 != 0) break block1;
                                        if (minecraftClient.currentScreen != null) break block2;
                                        minecraftClient = Globals.getMc();
                                    }
                                    bl = minecraftClient.options.pickItemKey.isPressed();
                                    if (n2 != 0) break block3;
                                    if (!bl) break block2;
                                    middleClick = this;
                                    if (n2 != 0) break block4;
                                    bl = middleClick.getPearlOnRelease();
                                }
                                if (!bl) break block2;
                                middleClick = this;
                            }
                            middleClickOnBlockMode2 = middleClick.getOnBlock();
                            middleClickOnBlockMode = MiddleClickOnBlockMode.PEARL;
                            if (n2 != 0) break block5;
                            if (middleClickOnBlockMode2 == middleClickOnBlockMode) break block6;
                            middleClickOnBlockMode2 = this.getOnAir();
                            middleClickOnBlockMode = MiddleClickOnBlockMode.PEARL;
                        }
                        if (n2 != 0) break block7;
                        if (middleClickOnBlockMode2 == middleClickOnBlockMode) break block6;
                        middleClickOnBlockMode2 = this.getOnEntity();
                        middleClickOnBlockMode = MiddleClickOnBlockMode.PEARL;
                    }
                    if (n2 != 0) break block8;
                    if (middleClickOnBlockMode2 == middleClickOnBlockMode) break block6;
                    middleClickOnBlockMode2 = this.getOnFlying();
                    middleClickOnBlockMode = MiddleClickOnBlockMode.PEARL;
                }
                if (middleClickOnBlockMode2 != middleClickOnBlockMode) break block2;
            }
            Item[] itemArray = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.ENDER_PEARL, "ENDER_PEARL");
            boolean bl = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n, l).isFound();
            if (bl) {
                PlayerEntity playerEntity = Globals.getPlayer();
                Item item = Items.ENDER_PEARL;
                Intrinsics.checkNotNullExpressionValue(item, "ENDER_PEARL");
                float f = Globals.getPlayer().getYaw();
                Item item2 = item;
                PlayerEntity playerEntity2 = playerEntity;
                Trajectories.INSTANCE.simulateTrajectory(playerEntity2, item2, f);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    private final boolean canUse(int var1_1, int var2_2) {
        block15: {
            block17: {
                block16: {
                    var25_3 = 0;
                    var3_4 = ((long)var1_1 << 32 | (long)var2_2 << 40 >>> 32 | (long)var25_3 << 56 >>> 56) ^ MiddleClick.a;
                    var19_5 = ZkmReferences.zkmBootstrap("\u00c5", (long)8138339876346598669L, (long)var3_4);
                    var20_6 = new Item[1];
                    Intrinsics.checkNotNullExpressionValue(Items.EXPERIENCE_BOTTLE, (String)MiddleClick.zkmBootstrap("w", (int)25398, (long)(5050524802124267599L ^ var3_4)));
                    v0 = InventoryUtil.INSTANCE.findInHotbar((Item[])var20_6).isFound();
                    if (var19_5 != false) break block16;
                    if (v0) break block17;
                    v0 = false;
                }
                return v0;
            }
            v1 = var20_6 = (Iterable)ArmorUtil.getArmorItems(Globals.getPlayer());
            if (var19_5 != false) ** GOTO lbl23
            if (!(v1 instanceof Collection)) ** GOTO lbl-1000
            v1 = (Collection)var20_6;
            if (var19_5 == false) {
                ** if (!v1.isEmpty()) goto lbl-1000
lbl-1000:
                // 1 sources

                {
                    v2 = false;
                    ** GOTO lbl41
                }
            }
            ** GOTO lbl23
lbl-1000:
            // 2 sources

            {
                v1 = var20_6;
lbl23:
                // 3 sources

                var22_7 = v1.iterator();
                block0: while (var22_7.hasNext()) {
                    var23_8 = var22_7.next();
                    var24_9 = (ItemStack)var23_8;
                    cfr_temp_0 = MovementUtil.INSTANCE.getDurabilityFraction(var24_9) - (float)MiddleClick.INSTANCE.P();
                    v3 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                    do {
                        v4 = var19_5;
                        do {
                            if (v4 == false) {
                                v3 = v3 < 0 ? (float)true : (float)false;
                            }
                            if (v3 == false) continue block0;
                            v5 = true;
                            v4 = var19_5;
                        } while (var2_2 < 0);
                    } while (v4 != false);
                    break block15;
                }
                v2 = false;
            }
        }
        if (var1_1 >= 0) {
            if (v2) {
                return true;
            }
            v2 = this.R();
        }
        if (var19_5 == false) {
            v2 = v2 == false;
        }
        return v2;
    }

    private final void onMiddleClickAir(long l) {
        block4: {
            long l2;
            int n;
            block14: {
                int n2;
                int n3;
                int n4;
                int n5;
                block15: {
                    block11: {
                        block12: {
                            block13: {
                                block5: {
                                    block6: {
                                        Item[] itemArray;
                                        block10: {
                                            block8: {
                                                int n6;
                                                block9: {
                                                    block7: {
                                                        long l3 = (l = a ^ l) ^ 0x30BEBF28700AL;
                                                        n = (int)(l3 >>> 48);
                                                        l2 = l3 << 16 >>> 16;
                                                        n5 = MiddleClickThrowThread.zkmIntConstant();
                                                        if (!cooldownTimer.hasPassed2(250)) break block4;
                                                        itemArray = new Item[1];
                                                        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
                                                        n4 = InventoryUtil.INSTANCE.findInHotbar(itemArray).getSlot();
                                                        n3 = this.getSilent();
                                                        if (n5 != 0) break block5;
                                                        if (n3 == 0) break block6;
                                                        n6 = this.getInventory();
                                                        if (n5 != 0) break block7;
                                                        if (n6 == 0) break block8;
                                                        n6 = this.getInventory();
                                                    }
                                                    if (n5 != 0) break block9;
                                                    if (n6 == 0) break block10;
                                                    n6 = n4;
                                                }
                                                if (n6 == -1) break block10;
                                            }
                                            InventoryUtil.INSTANCE.swapSlots(n4, this.getXpFactor());
                                            if (n5 == 0) break block4;
                                        }
                                        itemArray = new Item[1];
                                        Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
                                        InventoryUtil.INSTANCE.swapToOffhand(InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n, l2).getSlot());
                                        if (n5 == 0) break block4;
                                    }
                                    n3 = this.getInventory();
                                }
                                if (n5 != 0) break block11;
                                if (n3 == 0) break block12;
                                n3 = this.getInventory() ? 1 : 0;
                                if (n5 != 0) break block13;
                                if (n3 == 0) break block14;
                                n3 = n4;
                            }
                            n2 = -1;
                            if (n5 != 0) break block15;
                            if (n3 == n2) break block14;
                        }
                        n3 = n4;
                    }
                    n2 = -1;
                }
                if (n3 == n2) break block4;
                new MiddleClickThrowThread(n4, Globals.getPlayer().getInventory().getSelectedSlot(), this.getSwapDelay(), false).start();
                if (n5 == 0) break block4;
            }
            Item[] itemArray = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.FIREWORK_ROCKET, "FIREWORK_ROCKET");
            int n7 = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n, l2).getSlot();
            if (n7 != -1) {
                new MiddleClickThrowThread(n7, Globals.getPlayer().getInventory().getSelectedSlot(), this.getSwapDelay(), true).start();
            }
        }
    }

    private final void onMiddleClickBlock() {
        block3: {
            EntityHitResult entityHitResult;
            block6: {
                boolean bl;
                block7: {
                    int n;
                    block5: {
                        HitResult hitResult;
                        block4: {
                            boolean bl2;
                            block2: {
                                n = MiddleClickThrowThread.zkmIntConstant();
                                bl2 = cooldownTimer.hasPassed2(800);
                                if (n != 0) break block2;
                                if (!bl2) break block3;
                                hitResult = Globals.getMc().crosshairTarget;
                                if (n != 0) break block4;
                                bl2 = hitResult instanceof EntityHitResult;
                            }
                            if (!bl2) break block3;
                            HitResult hitResult2 = Globals.getMc().crosshairTarget;
                            hitResult = hitResult2;
                            Intrinsics.checkNotNull(hitResult2, "null cannot be cast to non-null type net.minecraft.world.phys.EntityHitResult");
                        }
                        entityHitResult = (EntityHitResult)hitResult;
                        bl = entityHitResult.getEntity() instanceof PlayerEntity;
                        if (n != 0) break block5;
                        if (!bl) break block3;
                        Entity entity = entityHitResult.getEntity();
                        Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                        bl = Friends.getIsHomie((PlayerEntity)entity);
                    }
                    if (n != 0) break block6;
                    if (!bl) break block7;
                    Entity entity = entityHitResult.getEntity();
                    Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                    FriendManager.INSTANCE.removeFriend((PlayerEntity)entity);
                    ModuleMessages.S(this, Formatting.RED + entityHitResult.getEntity().getName().getString() + Formatting.GRAY + " " + ModuleMessages.i18n$default(this, "removed", new Object[0], false, 4, null), false, null);
                    if (n == 0) break block3;
                }
                Entity entity = entityHitResult.getEntity();
                Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                bl = FriendManager.INSTANCE.isFriend((PlayerEntity)entity);
            }
            ModuleMessages.S(this, Formatting.GREEN + entityHitResult.getEntity().getName().getString() + Formatting.GRAY + " " + ModuleMessages.i18n$default(this, "added", new Object[0], false, 4, null), false, null);
        }
    }

    private final void throwPearl(char c, int n, short s) {
        block0: {
            int n2;
            int n3;
            block2: {
                int n4;
                int n5;
                block1: {
                    long l = ((long)c << 48 | (long)n << 32 >>> 16 | (long)s << 48 >>> 48) ^ a;
                    long l2 = l ^ 0x12D249F21FC7L;
                    int n6 = (int)(l2 >>> 48);
                    long l3 = l2 << 16 >>> 16;
                    int n7 = MiddleClickThrowThread.zkmIntConstant();
                    if (!cooldownTimer.hasPassed2(500)) break block0;
                    Item[] itemArray = new Item[1];
                    Intrinsics.checkNotNullExpressionValue(Items.ENDER_PEARL, "ENDER_PEARL");
                    n3 = InventoryUtil.INSTANCE.findInHotbar(itemArray).getSlot();
                    Item[] itemArray2 = new Item[1];
                    Intrinsics.checkNotNullExpressionValue(Items.ENDER_PEARL, "ENDER_PEARL");
                    n2 = InventoryUtil.INSTANCE.findInInventory(itemArray2, (char)n6, l3).getSlot();
                    n5 = n3;
                    n4 = -1;
                    if (n7 != 0) break block1;
                    if (n5 != n4) break block2;
                    n5 = n2;
                    n4 = -1;
                }
                if (n5 == n4) break block0;
            }
            RotationManager.INSTANCE.submit(new ActionRotationRequest(new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null), 200, () -> MiddleClick.useItemSlot(n3, n2)));
        }
    }

    private final void useRotated(long l) {
        block0: {
            long l2 = (l = a ^ l) ^ 0x1EB64AB97C56L;
            int n = (int)(l2 >>> 32);
            int n2 = (int)(l2 << 32 >>> 40);
            if (!this.canUse(n, n2)) break block0;
            RotationManager.INSTANCE.submit(new RotationRequest(new Rotation(Globals.getPlayer().getYaw(), 90.0f, false, null, 12, null), 200, MiddleClick::useRotated$lambda$0));
        }
    }

    private static final boolean settingVisible() {
        int n = MiddleClickThrowThread.zkmIntConstant();
        boolean bl = INSTANCE.getSilent();
        if (n == 0) {
            bl = !bl;
        }
        return bl;
    }

    private static final Unit useItemSlot(int n, int n2) {
        block10: {
            int n3;
            int n4;
            block15: {
                block13: {
                    block14: {
                        int n5;
                        int n6;
                        block11: {
                            block12: {
                                block4: {
                                    block5: {
                                        long l;
                                        int n7;
                                        block9: {
                                            block7: {
                                                int n8;
                                                block8: {
                                                    block6: {
                                                        long l2 = a ^ 0x58CEE23515EEL;
                                                        long l3 = l2 ^ 0x7AC62CC32FBCL;
                                                        n7 = (int)(l3 >>> 48);
                                                        l = l3 << 16 >>> 16;
                                                        n6 = MiddleClickThrowThread.zkmIntConstant();
                                                        n5 = INSTANCE.getSilent();
                                                        if (n6 != 0) break block4;
                                                        if (n5 == 0) break block5;
                                                        n8 = INSTANCE.getInventory();
                                                        if (n6 != 0) break block6;
                                                        if (n8 == 0) break block7;
                                                        n8 = INSTANCE.getInventory();
                                                    }
                                                    if (n6 != 0) break block8;
                                                    if (n8 == 0) break block9;
                                                    n8 = n;
                                                }
                                                if (n8 == -1) break block9;
                                            }
                                            InventoryUtil.INSTANCE.swapSlots(n, INSTANCE.getXpFactor());
                                            if (n6 == 0) break block10;
                                        }
                                        Item[] itemArray = new Item[1];
                                        Intrinsics.checkNotNullExpressionValue(Items.ENDER_PEARL, "ENDER_PEARL");
                                        InventoryUtil.INSTANCE.swapToOffhand(InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n7, l).getSlot());
                                        if (n6 == 0) break block10;
                                    }
                                    n5 = INSTANCE.getInventory();
                                }
                                if (n6 != 0) break block11;
                                if (n5 == 0) break block12;
                                n4 = INSTANCE.getInventory();
                                if (n6 != 0) break block13;
                                if (n4 == 0) break block14;
                                int n3 = n;
                                n3 = -1;
                                if (n6 != 0) break block15;
                                if (n4 == n3) break block14;
                            }
                            n5 = n;
                        }
                        if (n5 == -1) break block10;
                        new MiddleClickThrowThread(n, Globals.getPlayer().getInventory().getSelectedSlot(), INSTANCE.getSwapDelay(), false).start();
                        if (n6 == 0) break block10;
                    }
                    n4 = n2;
                }
                n3 = -1;
            }
            if (n4 != n3) {
                new MiddleClickThrowThread(n2, Globals.getPlayer().getInventory().getSelectedSlot(), INSTANCE.getSwapDelay(), true).start();
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit useRotated$lambda$0() {
        Item[] itemArray = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.EXPERIENCE_BOTTLE, "EXPERIENCE_BOTTLE");
        InventoryUtil.INSTANCE.swapSlots(InventoryUtil.INSTANCE.findInHotbar(itemArray).getSlot(), INSTANCE.getXpFactor());
        return Unit.INSTANCE;
    }

    public static final void access$onMiddleClickAir(int n, MiddleClick middleClick, short s, int n2) {
        long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)n2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x7C192F4AEA88L;
        middleClick.onMiddleClickAir(l2);
    }

    public static final void access$onMiddleClickBlock(MiddleClick middleClick) {
        middleClick.onMiddleClickBlock();
    }

    public static final boolean access$getPearlOnRelease(MiddleClick middleClick) {
        return middleClick.getPearlOnRelease();
    }

    public static final void access$throwPearl(MiddleClick middleClick, long l) {
        long l2 = (l = a ^ l) ^ 0x1E2E5BCAF0D8L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        middleClick.throwPearl((char)n, n2, (short)n3);
    }

    public static final void access$useRotated(MiddleClick middleClick, long l) {
        long l2 = (l = a ^ l) ^ 0x5547F671B6AEL;
        middleClick.useRotated(l2);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        MiddleClick.a = ZkmKeys.a(8412661908427240085L, -2868034301487351901L, MethodHandles.lookup().lookupClass()).a(117956561957485L);
                        var20 = MiddleClick.a ^ 130657401033271L;
                        MiddleClick.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[45];
                        var16_4 = 0;
                        var15_5 = "U\u00c4\u0003\u00c1\u00b0%)mZ\n\u0081\u0082\n\u0092K\u0083`\u00eb'z\u00b7\u009c\u00bf\u009c]\u0014'\u00c6b\u0092\u00c4H8\u00de\u001eu\u00b0a\u00fdZ\u00a5r\u001a\u00c8\u008f\u000e\u00f1xE\u00d6\u0080\u00e1\u0007;b\u00f7\u00b8\u008c\u00d4\u00ae\u0006\u008dug-r(\u00d5\u001c3\u00f3\u00bf\u00f2\u00f7\u001a\u0094[\u00ce\u00a2\u00b2\u00e5\u00f7\u00cdH8d[\u00fd\u00d0\u0010\u0092J!R\u0087E\u007fH\u00cf;\u00e6\u0086\u00e2\u00e7_- \u00ee4d\u00f90\u0005\u0099\u007f\u00d6\u0094'G\u00ed\u00e4\u00cd\u00074\f,\u00bc\u00df\u00cf\u00c2\u00fc\u0081\u00fa\u008dS\u00a0\u00e2\u009d\b\u0018\u00fbE\u0012#h\u001f\u00d8\u0084\u0083K\u0003\u00e9\u00e7D\u001e\u00cb\u00c2,y\u0080)\u009dA\u00ff\u0010\u00b5\u00e1K1%\u00e5a0\u00d0Ai\u0092'\u0019\u00834\u0018d\u00ac.[X\u00e3\u00d8\"\u00b5\u00ef\u00bcl\u00a9k\u00e4\u00eb\u00d9\f?\u00c5\u00ef\u0081\u00a5\f 4x\u00e9d\u0085\"\u0012\u00b3\u00f7\u00d8\u0089\u00f5p\u00e6x=R)J\u00cb/\u00ecJp\u00c5kJ\u00ca\u00b1\u00e5>\u00c6\u0010)|\u00e9\u0015QU\u0089\u0080\u00e4a\u0013\u009bwJ\u001a\u001d\u0018\u009d\u00ba\u00a2\u00d1\u00df\u00de\u00d4\u00df\u00eas\u00e5\u0096\u00e7\u0011O1T\u00d5\u00e5\u00ef\u00f5,\u001c\u00fe \u0019Fy\u00d2=\u0093\u00b4\u00da\u001d\u00a8Qw\u00bdC0M\u00ba#\u00fbN\u0099\u00be\u00a9XKT\u00d0\u008e*_\u00c8\u00b0(\u00e1\u0091\u00c2\u00f5\u0081\u00db\u00a4@\u008d\u00b1\u00c4\u00c9@\u00e0$\u00ad\u008c\u00b9\u00f2\u00e9\u00ea\u0007;B\u00f0E\u00b8Wu\u00f5\u00d9c\u00a5b*\u0088\u00d5]T\u0006\u0010\u0084\u00a9\u0010[\u00ec-r\u0019Iw\u00c8\u0081\u00e4OEPxY\u00bcC\u00d0)\u0017TGqP\u00fe ,\u00ae\u00e2\u00c6\u00cf\u00abr\u0012\u0003Y3\u0019\u001b\u00bc\u00bc\u00aexc\u00a2%e\u00a3@4`.\u009363\u00dc\u00a7\u00f1\u000e\u00dcG\u00be\u00f6UndZW[|\u00c5\u00e1!\u00b8\u001f\u00a5\u00b7h\u00d7\u00ca4\u00be\u008b\u00cc\u00d39\u0093\u00d5\u0082M\u00c0\u0084\u00ae\u0098\u0098\u009dfrn\u00c1KNs/\u001f\u008bF\u00de\u00e6\u00c0\u00f3Q\u000e\u00c6\u0088\u008a\u00ea\u00a3\u0099\b\u00e4\u00cf?JYg\u00fc\u00dc`\u0002\u00b8\u009b\t\u00b2xH\u0091\u0014E\u00bc\u0092\u009a\u00f9\u0014\u00a6\u0097C}J\u0001cQ\u001bE\u00a1\u009e\u00e4l\u00fa\u00bc\u00f5\u00fa\u0091)N\u00bf\u00d0\u00a4b\u00e6\u00ffR\u00aeh\u00e9\u00f0\u00b1\u00fa\u00bf\u00b6\u00b8\u00c0-\u0099\u00fb\u00cb\u00d8\u00e5mG\u008d\u00ec\u00a9\u00e1?\u0005;\u00c4\u00d3\u00a7\u00bd\u00fe5^\u00af{\u00b386o\u00e4\u001fJ\u000e\u0013.\u00d1\u00ff\u00e8)\u00c1\u009aU\u0015\u00da\u0012\u00ac/\u00bd\u00cb\u00a0\u00c9\u0004\u00b3zN\u0088\u00923\u0084\u00d0\u0095\u00b3E\u00c8e\u00ee\u0098^\u00b5\u009c\u0090,\u008f\u00e0\u0018f\u00e6V\u009a\u00943\u00a2\u00cbn\u00f5E\u00e7\tI\u00db5..w\u0093.{a\u00f2 \u00fc\u00ddO\u00a6W\u008e\u009c\u0012\u00e5\u00dc\bc\\\u0083\u001d\u00b2.W.\u00c6\u00e9T\n\u00e6\u0018\u00c0LU\u00f1\u0015\u009a\u00e68> \u00bd#v\u00f0I\u001b\u008e+\u00ab\u001b\u00bd[\u00b4\u00c6\u00cc\u0003s\u00ec^\u00fb\u001d\u00b3[ei\u00156\u00ff\u0081\u00132v!1\u00a5_('\u00e53z\u00b6i\u00d9\u008e\u00e0\u0017\u0018i/\u00a3\u00cdI\u00f0\u00102e\u0013\u0083\u00f6k\u00b7\u009f\u00d6\u00d8\u0083\u00ec \u0088\u00e8\u00db \u000e\u00f4\u00d9\u007f\u00f0\u00be\u0014\u0086\u0004I\u008d1j\u0082W\u00e9\u00042\u00edgr\u00aeV\u00ff\u00f1\u0086(\u0084E\u00cd\u00f5E\u0018\u00d9>\u0081dq\u008bAj\u00d8 \u009c!\u00e5\n%I\u0081\u00a2\u00fbO\u00f9\u00a8<< \u00de\u00f3W8\u00e2_\u00eb\u009f\u008bx\u00c9j\u00ff\u001c\u00f9h\u00d3j82\u00e7\u0016-#e\u0002\u009e\u00ab\u000f\u0010\u00fb\u00e5p\u00f8\u00d5\u00e2\u0007\fT\u00db\u009c\u00b14\u0000\u0080wd\u00f0\u00b2\u00b6\u00b0\u00ba\u000e\u0096\u00b3]\u00e5\u00e1\u00e1:\u00eb\u00b8\u00a3\u009bO\u00f5I~\u00c4*>y\u0001Xs\u00a8!\u00e8\u00a0*\u00bf\u0012\u000b\u00b5\u00f8-\u00a5[\u001euv\u0018\u00ff\u00b2@\u00e7\u0001\u00e1\u00c2{\u00f02\u00b5>u\u0003\u00d0\u00be\u00dd\u00d0sy\u00c1\u00c7\u00fa)\u001e\u00f6\u0005H`G\u00f6\u00df\u00ba\u00ec\u00d8t\u00ca\u00d2\u00a3\u000e\u0099y\u001c\nip\u001b\u00feG.m\u0093\u00ac\u0018T\u00a0\u00d4\u00f6\u00f3H0\u00c5\u00ae~\u0080\u007f\u00fb\u00b1\u00f5Mxc=Fg\u00b6\u00be\u0017\u0010#IG_\u00a4\u00c4\u00a5Q\u0086\u00ba\u00f9S\u0096\u009b\u00a84 \u0003\u0092\u0099\u0017z\u0097\u00e9f\f\u00cb%\u00f7`m\u00a9,\u00e0\u00f6:\u00beP).\u00e6\u00dd\u00b5\u0013&w\u00dd]g\u0018P\u001a\u00de\u00b8\u00b4\u00e5\u0081\u00eb\u0005D\u0002r\u00ba\u0098,\u00b2\u00e5\u00ee\u00e8>\u00bd\u0090?p\u0018\u007f\u0085\u00e9\u0096\u008b\u00bbah\u00e4\u00e3\u0007\u00d8Vh\u0083\u00b7\u00f0@'I\u008fJ\u00cd0\u0018\u0098\u00d6\u009b\u0087E\u00a8#+0B\u000fCT\u00e6\u00aa\u00be\u00c2\u009b\u00d5\u0019P\u0005\u00fd\u00a2 Ml\u0089\u0094J\r@f\u00ff\u00bb\u00fd\u0007d\u0098e-\u0096\u0005jO`[\u00bdy\u00c0\u00fa\u0082\u00d8`;cn\u0010\u00ec\u00de\u0016\u00df\u0085v\u0091\u00b2Y\u00b2\u0082\u0081\u000e`\u00aa\u0094 \"P\u0012\u00abZtv3\u00da\u0018:\u0098?\u0098c\u00c7\u0011\u008f\u008fh\u00cc\u00b3\u00a1\u0015\u008fB\u00dfhWFM\u00ab(\u00b5\u00dc3\u00d4\f\u0013\u00a1\u00a4\u009e\u0016$\u00a6\u0093\u00da\u00d9\u0003;\u001c\u00dd\u00b9\u001b\u001c\u000e\u00d4\u00cb^:tF\u008aI\u00de\u008e7pq%\u00ac'\u00c6 \u00fc&\u00b2B\u001a\u00cc\u00bc\t\u0092\u00d3\u00bd\u0094\u00f9\u00f4\u0090Y\u00f8A\u0083W$L\u0096\u00c2l\u00c8i\u00bf\u00b1\u00c6QJ @\u00ba\u00c6\u0096_\u00ecw}\u008b\u00b2\u008f\u009c\u00ae\u00c0\u008c\u00a8fLk\u00ef\u00bb\u00cc\u00f6IG\u0012dqpK\u00fe) b\u001b~\u0089\u008eU\u00a0\u007f\u0000p\u00b1o\u009b\u001a\u00e0\u0012i\u000e\u00aaF*\u001c\u00cd\u00bbx\u00f1`Os\u0090<s\u0010K\u0080\u00e2z\u00d5]\u0001\u001a\u00cf\u00ea\u00cb\u0099y\u00ab,\u0018(x\u009a\u0093\u00fb\u00a3\u00ed\u008a\u00dc\u00b9\u00fa\u00d4\u00f1\u00cc\u0092T\\\u00b1\u00e2\u00fb\u00f5\u0005\u0086\u0095\t\u00bb\u0091\u0090\u0087\u0010X\u0099\u00fe\u00a7\u00a4.\u00bc=\u0093\u0090\u00fa8\u00acX\u0002\u0096\u00bcy\u001e\u00c6\u0006\u008d\u00b1\u00d6,usd\u00fe\rs}\u00a7q\u00a9K<\f\u00b5H>Q\u00de\u0092\u00d5\u0000\fn~L*\u00aaXUeP\\o\u00d3\u00c9\u0092\u00fa\u0082\u00fe\u0011\u00c4\u0012\u00928\u00bb\u0014\u00c4\u00e8\u001f\u008e\u00e1\u0091\u00c1\u0015q\u0001\u00f2v4\u00e9\u00df\u00be\u00e5Ao\u008e\t2\u00eenP\u0084\u00b1\u0004\u00a0h\u00fb\u001bLy#v\u001a\u0004\u00f0\u00fcN,\u001fg\u0095\u00d3N\u000e\u00e0\u001d~\u00f2\u00b7\u00a8(\u00de\u00cb\u0017\u00bc\u000e\u009cnWF\u0004\u0088\u00b2\u008e\u0007\u00ec\u00e3z\u001f\u00c7\u00061\u0005\u00d9\u00de\u00e8\u00c9\u0082\u008d[\u00f5\u00f9\u00d8\u000e>i\f\u00fd4o\\ \u00b07\f\u00b1k\u008e\u00ba[\u00d4[I\u00ce\u00f2\u00c9\u00be\u0090@l\u009a.\u00e4P\u00bb&('P\u00e5\u000f\u00f1\u0097] \u00e9k\u001b\u00b2\u00bb[\"ZQ\u0001\u00c5J\f\t\u001dusX\u0001\u009e\u00c8l7\u00930Vs\u00a7\u00fe\u0006\u007fe";
                        var17_6 = 1602;
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
                            var18_3[var16_4++] = MiddleClick.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u008b\u00ccY\u00a9\u0004\u009d\u00bf$Ok\u00cf9\u00d5\u00e4i\u00db\u0086\u0086\u00bcEQ)_\u00f5\u00b3=l\u0016\u001c?\"6\u008f.\u00dfY\u00be\u0081\u00eb\u00c8\u0018\u00b1\u00c3\u0015zP\u0018\u0094\u00a1\u00efv\u0006\u0084%}eD\u0083\u00d8\u0013\u00c6f\u0084\u00b1\t";
                            var17_6 = 65;
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
                            var18_3[var16_4++] = MiddleClick.zkmDecodeUtf8(var19_9).intern();
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
                MiddleClick.b = var18_3;
                MiddleClick.c = new String[45];
                MiddleClick.i = new HashMap<K, V>(13);
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
                var6_12 = new long[25];
                var3_13 = 0;
                var4_14 = "\u00f6\u00f5#\u00b1\u00a5On\u008b\u00af\u00c4\u0001\u00bcY\u00a6\u00a3\u000b\u00ff\u00bc\u009dov\u00edKq`\u00ee\r\u00abqL\u00e4\u0011\u00ff\u0089.\u00b09W\u000b\u008b1\u00a6\u0095w\u007f\u009a\u0011}5\u00ca\u0013`\u00d8i\u00efc\u0091\u00d9\u00bd\"\u0004\tK\u00b07\u0005\u00c0\u0086W\u00e8\u00f1g\u00d7{\u00d5\u001d\u00fe\u00fe\u00ec\u00f7\u00bf\u0099\u00f8fr?A\u00e3\u00c6\u00e6\u00b7p\u008c\u00f3\u0017\u00baTW\u00e4\u00fb\u00eaA3\u00de\u009a\u00d4\u008b\u0083\u00b7U\u0082#\u00ferD\u00b7PQN\u00d8\u00a5\u00cc\u00e21\u00ef\u00b9\u00ce\u00e1\u0005\u0000Z\u009e\u00c20\u00abSf\u0002(\u0006\u00cdT\u00adW\u0085\u009aE\u00c3i9d\u0010\r\u00ed-\u00ea\u00d8\u00de19\u00e3\u00a8QL\u00e3\u00d5\u00df\u0091\u0019\u00c1\u00af\u0019MA\u00fc\u0084uAd\u000f\u00fc>Ig";
                var5_15 = 184;
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
                    var4_14 = "3\u00c5v\u00df)j\u0015>\u009f\u0018\u00d2\u00ce\u00fa\u0004\u00aa\u0014";
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
        MiddleClick.f = var6_12;
        MiddleClick.g = new Integer[25];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "onBlock", "getOnBlock()Lsu/catlean/cn;", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "onAir", "getOnAir()Lsu/catlean/cn;", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "onEntity", "getOnEntity()Lsu/catlean/cn;", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "onFlying", "getOnFlying()Lsu/catlean/cn;", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "silent", "getSilent()Z", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "inventory", "getInventory()Z", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "swapDelay", "getSwapDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "antiWaste", "getAntiWaste()Z", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "durability", "getDurability()I", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "xpFactor", "getXpFactor()I", 0)), Reflection.property1(new PropertyReference1Impl(MiddleClick.class, "pearlOnRelease", "getPearlOnRelease()Z", 0))};
        MiddleClick.delegatedProperties = var30_20;
        MiddleClick.INSTANCE = new MiddleClick();
        MiddleClick.onBlock$delegate = SettingFactory.modeSetting$default((SettingContainer)MiddleClick.INSTANCE, "on-block", (Enum)MiddleClickOnBlockMode.XP, null, null, 12, null);
        MiddleClick.onAir$delegate = SettingFactory.modeSetting$default((SettingContainer)MiddleClick.INSTANCE, "on-air", (Enum)MiddleClickOnBlockMode.PEARL, null, null, 12, null);
        MiddleClick.onEntity$delegate = SettingFactory.modeSetting$default((SettingContainer)MiddleClick.INSTANCE, "on-entity", (Enum)MiddleClickOnBlockMode.FRIEND, null, null, 12, null);
        MiddleClick.onFlying$delegate = SettingFactory.modeSetting$default((SettingContainer)MiddleClick.INSTANCE, "on-elytra", (Enum)MiddleClickOnBlockMode.FIREWORK, null, null, 12, null);
        MiddleClick.silent$delegate = SettingFactory.booleanSetting$default((SettingContainer)MiddleClick.INSTANCE, "silent", true, null, null, 12, null);
        MiddleClick.inventory$delegate = SettingFactory.booleanSetting$default((SettingContainer)MiddleClick.INSTANCE, "inventory", true, null, null, 12, null);
        MiddleClick.swapDelay$delegate = SettingFactory.intSetting$default(MiddleClick.INSTANCE, "swap-delay", 100, new IntRange(0, 1000), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        MiddleClick.antiWaste = SettingFactory.booleanSetting$default((SettingContainer)MiddleClick.INSTANCE, "anti-waste", true, null, null, 12, null);
        MiddleClick.stopOn = SettingFactory.intSetting$default(MiddleClick.INSTANCE, "stop-on", 90, new IntRange(0, 100), null, null, 24, null);
        MiddleClick.xpFactor$delegate = SettingFactory.intSetting$default(MiddleClick.INSTANCE, "xp-factor", 1, new IntRange(1, 16), null, null, 24, null);
        MiddleClick.pearlOnRelease$delegate = SettingFactory.booleanSetting$default((SettingContainer)MiddleClick.INSTANCE, "pearl-on-release", true, null, null, 12, null);
        MiddleClick.cooldownTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x52D7;
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
                throw new RuntimeException("su/catlean/q5", exception);
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
            MiddleClick.c[n2] = MiddleClick.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MiddleClick.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2290;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/q5", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MiddleClick.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MiddleClick.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MiddleClick.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MiddleClick.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

