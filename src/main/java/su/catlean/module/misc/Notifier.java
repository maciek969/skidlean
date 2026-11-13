/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.module.misc;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.SplashPotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.ItemPickupAnimationS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldEventS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import su.catlean.api.event.events.network.PlayerEntityDeathEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.world.EntityRemove;
import su.catlean.api.event.events.world.EntitySpawn;
import su.catlean.config.ConfigFiles;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.Assistant;
import su.catlean.module.misc.NotifierHelper;
import su.catlean.module.misc.NotifierPastEffectsMode;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.font.Fonts;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.sound.Sounds;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.ui.hud.PickupEntry;
import su.catlean.util.ArmorUtil;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.util.ModuleMessages;
import su.catlean.util.MovementUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.TextUtil;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class Notifier
extends Module {
    @NotNull
    public static final Notifier INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting totemPops$delegate;
    @NotNull
    private static final BooleanSetting lowArmor$delegate;
    @NotNull
    private static final BooleanSetting rangeEnter$delegate;
    @NotNull
    private static final BooleanSetting rangeLeave$delegate;
    @NotNull
    private static final BooleanSetting lag$delegate;
    @NotNull
    private static final BooleanSetting rubberBand$delegate;
    @NotNull
    private static final BooleanSetting stash$delegate;
    @NotNull
    private static final IntSetting minChests$delegate;
    @NotNull
    private static final IntSetting minShulkers$delegate;
    @NotNull
    private static final BooleanSetting mention$delegate;
    @NotNull
    private static final ModeSetting pastEffects$delegate;
    @NotNull
    private static final ModeSetting splashBuff$delegate;
    @NotNull
    private static final BooleanSetting ftBypass$delegate;
    @NotNull
    private static final BooleanSetting self$delegate;
    @NotNull
    private static final BooleanSetting old$delegate;
    @NotNull
    private static final ModeSetting itemCollect$delegate;
    @NotNull
    private static final BooleanSetting onlyDon$delegate;
    @NotNull
    private static final ModeSetting useTracker$delegate;
    @NotNull
    private static final RenderTarget D;
    @NotNull
    private static final RenderTarget T;
    @NotNull
    private static final RenderTarget k;
    @NotNull
    private static final RenderTarget i;
    @NotNull
    private static final RenderTarget N;
    @NotNull
    private static final RenderTarget X;
    @NotNull
    private static final RenderTarget U;
    @NotNull
    private static final Timer assistantCooldown30s;
    @NotNull
    private static final Timer assistantCooldown10s;
    @NotNull
    private static final Timer assistantCooldown60s;
    @NotNull
    private static final Timer assistantCooldown2500ms;
    @NotNull
    private static final Timer assistantCooldown3s;
    @NotNull
    private static final Timer I;
    @NotNull
    private static final Timer mentionTimer;
    @NotNull
    private static final Timer assistantCooldown2s;
    @NotNull
    private static final Timer O;
    @NotNull
    private static final List notificationEntries;
    @NotNull
    private static final List blockEntities;
    @NotNull
    private static final ConcurrentHashMap pickups;
    private static boolean n;
    private static int d;
    private static final long c;
    private static final String[] ab;
    private static final String[] fb;
    private static final Map gb;
    private static final long[] hb;
    private static final Integer[] lb;
    private static final Map mb;
    private static final long[] nb;
    private static final Long[] ob;
    private static final Map pb;

    private Notifier() {
        super("notifier", Categories.getMisc(), null, null);
    }

    private final boolean getTotemPops() {
        return (Boolean)totemPops$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getLowArmor() {
        return (Boolean)lowArmor$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getRangeEnter() {
        return (Boolean)rangeEnter$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getRangeLeave() {
        return (Boolean)rangeLeave$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getLag() {
        return (Boolean)lag$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getRubberBand() {
        return (Boolean)rubberBand$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getStash() {
        return (Boolean)stash$delegate.getValue(this, delegatedProperties[6]);
    }

    private final int getMinChests() {
        return ((Number)minChests$delegate.getValue(this, delegatedProperties[7])).intValue();
    }

    private final int getMinShulkers() {
        return ((Number)minShulkers$delegate.getValue(this, delegatedProperties[8])).intValue();
    }

    private final boolean getMention() {
        return (Boolean)mention$delegate.getValue(this, delegatedProperties[9]);
    }

    private final NotifierPastEffectsMode getPastEffects() {
        return (NotifierPastEffectsMode)((Object)pastEffects$delegate.getValue(this, delegatedProperties[10]));
    }

    private final NotifierPastEffectsMode getSplashBuff() {
        return (NotifierPastEffectsMode)((Object)splashBuff$delegate.getValue(this, delegatedProperties[11]));
    }

    private final boolean getFtBypass() {
        return (Boolean)ftBypass$delegate.getValue(this, delegatedProperties[12]);
    }

    private final boolean getSelf() {
        return (Boolean)self$delegate.getValue(this, delegatedProperties[13]);
    }

    private final boolean getOld() {
        return (Boolean)old$delegate.getValue(this, delegatedProperties[14]);
    }

    private final NotifierPastEffectsMode getItemCollect() {
        return (NotifierPastEffectsMode)((Object)itemCollect$delegate.getValue(this, delegatedProperties[15]));
    }

    private final boolean getOnlyDon() {
        return (Boolean)onlyDon$delegate.getValue(this, delegatedProperties[16]);
    }

    private final NotifierPastEffectsMode getUseTracker() {
        return (NotifierPastEffectsMode)((Object)useTracker$delegate.getValue(this, delegatedProperties[17]));
    }

    @NotNull
    public final Timer j() {
        return I;
    }

    public final boolean x() {
        return n;
    }

    public final void h(boolean bl) {
        n = bl;
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        Object[] objectArray;
        long l = c ^ 0x61A2DBFFE824L;
        long l2 = l ^ 0x50617349C509L;
        if (!I.hasPassed(3000) && n) {
            this.renderNotification(render2DEvent.getContext(), X, U, l2, ModuleMessages.i18n$default(this, "air-alarm-header", new Object[0], false, 4, null), ModuleMessages.i18n$default(this, "air-alarm", new Object[0], false, 4, null));
        }
        if (this.getLowArmor()) {
            for (ItemStack itemStack : ArmorUtil.getArmorItems(Globals.getPlayer())) {
                if (itemStack.isEmpty() || this.getDurabilityPercent(itemStack) >= 20 || itemStack.getMaxDamage() == 0) continue;
                this.renderNotification(render2DEvent.getContext(), D, T, l2, ModuleMessages.i18n$default(this, "low-armor-title", new Object[0], false, 4, null), ModuleMessages.i18n$default(this, "low-armor-text", new Object[0], false, 4, null));
                if (assistantCooldown60s.hasPassed2(60000) && Assistant.INSTANCE.getEnabled()) {
                    Sounds.INSTANCE.getArmorAlert().play();
                }
                return;
            }
        }
        if (this.getLag() && ServerInfo.INSTANCE.getTimeSinceLastPacket() > 5000L && !Globals.getMc().isInSingleplayer() && Globals.getPlayer().age > 60) {
            objectArray = new Object[]{this.formatSeconds(ServerInfo.INSTANCE.getTimeSinceLastPacket())};
            Object var36_6 = null;
            int n = 4;
            boolean bl = false;
            Object[] objectArray2 = objectArray;
            String string = "server-error-text";
            Module module = this;
            this.renderNotification(render2DEvent.getContext(), k, i, l2, ModuleMessages.i18n$default(this, "server-error-title", new Object[0], false, 4, null), ModuleMessages.i18n$default(module, string, objectArray2, bl, n, var36_6));
            if (assistantCooldown30s.hasPassed2(30000) && Assistant.INSTANCE.getEnabled()) {
                Sounds.INSTANCE.getLag().play();
            }
            return;
        }
        if (this.getRubberBand() && MovementUtil.INSTANCE.getTimeSinceSetback() < 3000L && Globals.getPlayer().age > 60) {
            objectArray = new Object[]{this.formatSeconds(MovementUtil.INSTANCE.getTimeSinceSetback())};
            Object var45_12 = null;
            int n = 4;
            boolean bl = false;
            Object[] objectArray3 = objectArray;
            String string = "flag-text";
            Module module = this;
            this.renderNotification(render2DEvent.getContext(), N, N, l2, ModuleMessages.i18n$default(this, "flag-title", new Object[0], false, 4, null), ModuleMessages.i18n$default(module, string, objectArray3, bl, n, var45_12));
            if (assistantCooldown10s.hasPassed2(10000) && Assistant.INSTANCE.getEnabled()) {
                Sounds.INSTANCE.getLagBack().play();
            }
            return;
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        block0: {
            if (!(sendPacket.getPacket() instanceof ChatMessageC2SPacket)) break block0;
            mentionTimer.reset();
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        try {
            Object object;
            Object object2;
            Object object3;
            Object object4;
            if (receivePacket.getPacket() instanceof EntityTrackerUpdateS2CPacket) {
                Entity entity = Globals.getWorld().getEntityById(((EntityTrackerUpdateS2CPacket)receivePacket.getPacket()).comp_1127());
                if (entity == null) {
                    return;
                }
                object4 = entity;
                if (!(object4 instanceof PlayerEntity) || Intrinsics.areEqual(object4, Globals.getPlayer())) {
                    return;
                }
                List<DataTracker.SerializedEntry<?>> list = ((EntityTrackerUpdateS2CPacket)receivePacket.getPacket()).comp_1128();
                Intrinsics.checkNotNullExpressionValue(list, "packedItems(...)");
                object3 = list;
                object2 = object3.iterator();
                while (object2.hasNext()) {
                    PickupEntry pickupEntry;
                    Object object5;
                    Object object6;
                    Object object7;
                    object = object2.next();
                    DataTracker.SerializedEntry serializedEntry = (DataTracker.SerializedEntry)object;
                    if (!Intrinsics.areEqual(String.valueOf(serializedEntry.comp_1115()), "8")) continue;
                    if (Intrinsics.areEqual(serializedEntry.comp_1117().toString(), "1") || Intrinsics.areEqual(serializedEntry.comp_1117().toString(), "3")) {
                        boolean bl = Intrinsics.areEqual(serializedEntry.comp_1117().toString(), "3");
                        Intrinsics.checkNotNullExpressionValue(((PlayerEntity)object4).getStackInHand(bl ? Hand.OFF_HAND : Hand.MAIN_HAND), "getItemInHand(...)");
                        if (object7 == null || !((ItemStack)object7).getComponents().contains(DataComponentTypes.FOOD) && !((ItemStack)object7).getComponents().contains(DataComponentTypes.POTION_CONTENTS)) continue;
                        object6 = (Object[])pickups;
                        Text text = ((PlayerEntity)object4).getName();
                        Intrinsics.checkNotNullExpressionValue(text, "getName(...)");
                        String string = TextUtil.getToLegacy(text);
                        Text text2 = ((ItemStack)object7).copy().getName();
                        Intrinsics.checkNotNullExpressionValue(text2, "getHoverName(...)");
                        String string2 = TextUtil.getToLegacy(text2);
                        ItemStack itemStack = ((ItemStack)object7).copy();
                        Intrinsics.checkNotNullExpressionValue(itemStack, "copy(...)");
                        object5 = new PickupEntry(string2, itemStack, Globals.now());
                        object6.put(string, object5);
                        continue;
                    }
                    Text text = ((PlayerEntity)object4).getName();
                    Intrinsics.checkNotNullExpressionValue(text, "getName(...)");
                    if ((PickupEntry)pickups.remove(TextUtil.getToLegacy(text)) == null) {
                        return;
                    }
                    if (Globals.now() - pickupEntry.getStartTime() >= 1000L) {
                        Module module = INSTANCE;
                        object6 = new Object[2];
                        Text text3 = ((PlayerEntity)object4).getName();
                        Intrinsics.checkNotNullExpressionValue(text3, "getName(...)");
                        object6[0] = TextUtil.getToLegacy(text3);
                        object6[1] = pickupEntry.getItemName();
                        object7 = ModuleMessages.i18n$default(module, "used", (Object[])object6, false, 4, null);
                        if (INSTANCE.getUseTracker() == NotifierPastEffectsMode.NOTIFICATION || INSTANCE.getUseTracker() == NotifierPastEffectsMode.BOTH) {
                            Notifications.INSTANCE.push("", (String)object7, 5, NotificationType.INFO, pickupEntry.getI());
                        }
                        if (INSTANCE.getUseTracker() == NotifierPastEffectsMode.CHAT || INSTANCE.getUseTracker() == NotifierPastEffectsMode.BOTH) {
                            ModuleMessages.S(INSTANCE, (String)object7, false, null);
                        }
                    }
                    List<AbstractClientPlayerEntity> list2 = Globals.getWorld().getPlayers();
                    Intrinsics.checkNotNullExpressionValue(list2, "players(...)");
                    object5 = object6 = (Iterable)list2;
                    Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(object6, 10));
                    Iterator iterator2 = object5.iterator();
                    while (iterator2.hasNext()) {
                        Object t = iterator2.next();
                        AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)t;
                        Collection collection2 = collection;
                        Text text4 = abstractClientPlayerEntity.getName();
                        Intrinsics.checkNotNullExpressionValue(text4, "getName(...)");
                        collection2.add(TextUtil.getToLegacy(text4));
                    }
                    object7 = (List)collection;
                    pickups.entrySet().removeIf(arg_0 -> Notifier.invokePredicate(arg_0 -> Notifier.onReceivePacket$lambda$0((List)object7, arg_0), arg_0));
                }
            }
            if (receivePacket.getPacket() instanceof WorldEventS2CPacket && (((WorldEventS2CPacket)receivePacket.getPacket()).getEventId() == 2007 || ((WorldEventS2CPacket)receivePacket.getPacket()).getEventId() == 2002)) {
                float f;
                float f2;
                int n = ((WorldEventS2CPacket)receivePacket.getPacket()).getData();
                float f3 = (float)(n >> 16 & 0xFF) / 255.0f;
                object = new Color(f3, f2 = (float)(n >> 8 & 0xFF) / 255.0f, f = (float)(n >> 0 & 0xFF) / 255.0f);
                if (((Color)object).getRGB() == -13083194) {
                    return;
                }
                d = ((Color)object).getRGB();
            }
            if (this.getMention() && receivePacket.getPacket() instanceof GameMessageS2CPacket && mentionTimer.hasPassed(500)) {
                object4 = receivePacket.getPacket();
                String string = ((GameMessageS2CPacket)object4).content.getString();
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                CharSequence charSequence = string;
                String string3 = Globals.getPlayer().getGameProfile().name();
                Intrinsics.checkNotNullExpressionValue(string3, "name(...)");
                if (StringsKt.contains$default(charSequence, string3, false, 2, null)) {
                    Object var59_24 = null;
                    int n = 16;
                    ItemStack itemStack = null;
                    NotificationType notificationType = NotificationType.WARNING;
                    int n2 = 3;
                    String string4 = ModuleMessages.i18n$default(this, "mention", new Object[0], false, 4, null);
                    String string5 = this.getDisplayName();
                    Notifications notifications = Notifications.INSTANCE;
                    Notifications.push$default(notifications, string5, string4, n2, notificationType, itemStack, n, var59_24);
                    if (assistantCooldown2500ms.hasPassed2(2500) && Assistant.INSTANCE.getEnabled()) {
                        Sounds.INSTANCE.getChatMention().play();
                    }
                }
            }
            if (this.getItemCollect() != NotifierPastEffectsMode.OFF && receivePacket.getPacket() instanceof ItemPickupAnimationS2CPacket) {
                object4 = Globals.getWorld().getEntityById(((ItemPickupAnimationS2CPacket)receivePacket.getPacket()).getEntityId());
                object3 = Globals.getWorld().getEntityById(((ItemPickupAnimationS2CPacket)receivePacket.getPacket()).getCollectorEntityId());
                if (object4 instanceof ItemEntity && object3 != null) {
                    ItemStack itemStack = ((ItemEntity)object4).getStack().copy();
                    Intrinsics.checkNotNullExpressionValue(itemStack, "copy(...)");
                    ItemStack itemStack2 = itemStack;
                    if (!itemStack2.isEmpty()) {
                        if (this.getOnlyDon()) {
                            String string = itemStack2.getName().getString();
                            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                            String string6 = string.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(string6, "toLowerCase(...)");
                            if (!StringsKt.contains$default((CharSequence)string6, "\u043a\u0440\u0443\u0448", false, 2, null)) {
                                String string7 = itemStack2.getName().getString();
                                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                                if (!StringsKt.contains$default((CharSequence)string7, "\u2605", false, 2, null)) {
                                    return;
                                }
                            }
                        }
                        if (((Entity)object3).getId() == Globals.getPlayer().getId()) {
                            Module module = this;
                            object = new Object[2];
                            Text text = itemStack2.getName();
                            Intrinsics.checkNotNullExpressionValue(text, "getHoverName(...)");
                            object[0] = TextUtil.getToLegacy(text);
                            object[1] = itemStack2.getCount();
                            v20 = ModuleMessages.i18n$default(module, "collect", (Object[])object, false, 4, null);
                        } else {
                            Module module = this;
                            object = new Object[3];
                            Text text = ((Entity)object3).getName();
                            Intrinsics.checkNotNullExpressionValue(text, "getName(...)");
                            object[0] = TextUtil.getToLegacy(text);
                            Text text5 = itemStack2.getName();
                            Intrinsics.checkNotNullExpressionValue(text5, "getHoverName(...)");
                            object[1] = TextUtil.getToLegacy(text5);
                            object[2] = itemStack2.getCount();
                            v20 = object2 = ModuleMessages.i18n$default(module, "collector", (Object[])object, false, 4, null);
                        }
                        if (this.getItemCollect() == NotifierPastEffectsMode.NOTIFICATION || this.getItemCollect() == NotifierPastEffectsMode.BOTH) {
                            Notifications.INSTANCE.push("", (String)object2, 5, NotificationType.INFO, itemStack2);
                        }
                        if (this.getItemCollect() == NotifierPastEffectsMode.CHAT || this.getItemCollect() == NotifierPastEffectsMode.BOTH) {
                            ModuleMessages.S(this, (String)object2, false, null);
                        }
                    }
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Flow
    private final void onEntitySpawn(EntitySpawn entitySpawn) {
        block1: {
            if (!this.getRangeEnter() || !(entitySpawn.getEntity() instanceof PlayerEntity) || Intrinsics.areEqual(entitySpawn.getEntity(), Globals.getPlayer())) break block1;
            Module module = this;
            Module module2 = this;
            Object[] objectArray = new Object[1];
            Intrinsics.checkNotNullExpressionValue(entitySpawn.getEntity().getName().getString(), "getString(...)");
            ModuleMessages.S(module, ModuleMessages.i18n$default(module2, "range-enter", objectArray, false, 4, null), false, null);
            if (assistantCooldown2s.hasPassed2(2000) && Globals.getPlayer().age > 20 && Assistant.INSTANCE.getEnabled()) {
                Sounds.INSTANCE.getRangeIn().play();
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onEntityRemove(EntityRemove var1_1) {
        block23: {
            block22: {
                new int[0];
                if (this.getSplashBuff() == NotifierPastEffectsMode.OFF || var1_1.getEntity() == null || !(var1_1.getEntity() instanceof SplashPotionEntity)) break block22;
                v0 = var1_1.getEntity();
                Intrinsics.checkNotNull(v0, "null cannot be cast to non-null type net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion");
                var41_2 = (Object[])v0;
                v1 = Globals.getWorld().getPlayers();
                Intrinsics.checkNotNullExpressionValue(v1, "players(...)");
                var42_3 = v1;
                var44_4 = var42_3;
                var45_5 = new ArrayList<E>();
                var47_6 = var44_4.iterator();
                while (var47_6.hasNext()) {
                    var48_7 = var47_6.next();
                    var49_9 = (AbstractClientPlayerEntity)var48_7;
                    if (!(Intrinsics.areEqual(var49_9, Globals.getPlayer()) == false || Notifier.INSTANCE.getSelf() != false)) continue;
                    var45_5.add(var48_7);
                }
                var42_3 = (List)var45_5;
                for (Collection<T> var45_5 : var42_3) {
                    var46_13 = (AbstractClientPlayerEntity)var45_5;
                    v2 = Notifier.INSTANCE.getOld() != false ? var41_2.squaredDistanceTo(var46_13) : var41_2.getBoundingBox().squaredMagnitude(var46_13.getBoundingBox().expand(ProjectileUtil.getToleranceMargin((Entity)var41_2)));
                    var48_8 = v2;
                    if (!(var48_8 < 16.0)) continue;
                    if (!Notifier.INSTANCE.getFtBypass()) ** GOTO lbl-1000
                    v3 = var41_2.getStack().getName();
                    Intrinsics.checkNotNullExpressionValue(v3, "getHoverName(...)");
                    v4 = TextUtil.getToLegacy(v3).toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(v4, "toLowerCase(...)");
                    if (StringsKt.contains$default((CharSequence)v4, "\u043d\u0435\u0441\u043e\u0437\u0434\u0430\u0432\u0430\u0435\u043c\u043e\u0435", false, 2, null)) {
                        v5 = NotifierHelper.INSTANCE.getColorName(Notifier.d);
                    } else lbl-1000:
                    // 2 sources

                    {
                        v6 = var41_2.getStack();
                        v5 = v6;
                        Intrinsics.checkNotNullExpressionValue(v6, "getItem(...)");
                    }
                    var50_14 = v5;
                    var51_15 = 1.0 - Math.sqrt(var48_8) / 4.0;
                    var53_16 = (int)(100.0 * var51_15);
                    Intrinsics.checkNotNullExpressionValue((var53_16 > 80 ? Formatting.GREEN : (var53_16 > 50 ? Formatting.YELLOW : Formatting.RED)).toString(), "toString(...)");
                    v7 = Notifier.INSTANCE;
                    var55_18 = new Object[4];
                    v8 = var46_13.getName();
                    Intrinsics.checkNotNullExpressionValue(v8, "getName(...)");
                    var55_18[0] = TextUtil.getToLegacy(v8);
                    v9 = var50_14.getName();
                    Intrinsics.checkNotNullExpressionValue(v9, "getHoverName(...)");
                    var55_18[1] = TextUtil.getToLegacy(v9);
                    var55_18[2] = var54_17;
                    var55_18[3] = var53_16;
                    var56_19 = ModuleMessages.i18n$default(v7, "splash-buff", (Object[])var55_18, false, 4, null) + "%";
                    if (Notifier.INSTANCE.getSplashBuff() == NotifierPastEffectsMode.NOTIFICATION || Notifier.INSTANCE.getSplashBuff() == NotifierPastEffectsMode.BOTH) {
                        Notifications.INSTANCE.push("", var56_19, 5, NotificationType.WARNING, var50_14);
                    }
                    if (Notifier.INSTANCE.getSplashBuff() != NotifierPastEffectsMode.CHAT && Notifier.INSTANCE.getSplashBuff() != NotifierPastEffectsMode.BOTH) continue;
                    ModuleMessages.S(Notifier.INSTANCE, var56_19, false, null);
                    v10 = var50_14.get(DataComponentTypes.POTION_CONTENTS);
                    if (v10 == null) {
                        return;
                    }
                    var55_18 = v10;
                    Intrinsics.checkNotNullExpressionValue(var55_18.getEffects(), "getAllEffects(...)");
                    var58_21 = var57_20.iterator();
                    while (var58_21.hasNext()) {
                        Intrinsics.checkNotNullExpressionValue(var58_21.next(), "next(...)");
                        switch (var59_22.getAmplifier() + 1) {
                            case 1: {
                                v11 = "I";
                                break;
                            }
                            case 2: {
                                v11 = "II";
                                break;
                            }
                            case 3: {
                                v11 = "III";
                                break;
                            }
                            case 4: {
                                v11 = "IV";
                                break;
                            }
                            case 5: {
                                v11 = "V";
                                break;
                            }
                            case 6: {
                                v11 = "VI";
                                break;
                            }
                            case 7: {
                                v11 = "VII";
                                break;
                            }
                            case 8: {
                                v11 = "VIII";
                                break;
                            }
                            case 9: {
                                v11 = "IX";
                                break;
                            }
                            case 10: {
                                v11 = "X";
                                break;
                            }
                            default: {
                                v11 = String.valueOf(var59_22.getAmplifier() + 1);
                            }
                        }
                        var60_23 = v11;
                        var61_24 = (int)((double)var59_22.getDuration() * var51_15);
                        if (var59_22.isInfinite()) {
                            v12 = "*:*";
                        } else {
                            v13 = var61_24 / 1200;
                            var62_25 = "%02d";
                            var63_26 = new Object[]{var61_24 % 1200 / 20};
                            v14 = String.format(var62_25, Arrays.copyOf(var63_26, var63_26.length));
                            Intrinsics.checkNotNullExpressionValue(v14, "format(...)");
                            v12 = v13 + ":" + v14;
                        }
                        var64_10 = v12;
                        v15 = Globals.getPlayer();
                        v16 = var59_22.getEffectType().comp_349().getCategory() == StatusEffectCategory.HARMFUL ? Formatting.RED : "";
                        Intrinsics.checkNotNullExpressionValue(var59_22.getEffectType().comp_349().getTranslationKey(), "getDescriptionId(...)");
                        var38_11 = new Object[]{};
                        v15.sendMessage(Text.of("\u2022" + v16 + " " + I18n.INSTANCE.translate(var39_12, var38_11) + " " + var60_23 + " " + Formatting.RESET + var64_10), false);
                    }
                }
            }
            if (!this.getRangeLeave() || var1_1.getEntity() == null || !(var1_1.getEntity() instanceof PlayerEntity) || Intrinsics.areEqual(var1_1.getEntity(), Globals.getPlayer())) break block23;
            v17 = this;
            v18 = this;
            var41_2 = new Object[1];
            v19 = var1_1.getEntity();
            Intrinsics.checkNotNull(v19);
            Intrinsics.checkNotNullExpressionValue(v19.getName().getString(), "getString(...)");
            ModuleMessages.S(v17, ModuleMessages.i18n$default(v18, "range-leave", var41_2, false, 4, null), false, null);
            if (Notifier.assistantCooldown2s.hasPassed2(2000) && Globals.getPlayer().age > 20 && Assistant.INSTANCE.getEnabled()) {
                Sounds.INSTANCE.getRangeOut().play();
            }
        }
    }

    @Flow
    private final void onPlayerEntityDeath(PlayerEntityDeathEvent playerEntityDeathEvent) {
        if (this.getTotemPops()) {
            Module module = this;
            PlayerEntity playerEntity = playerEntityDeathEvent.getPlayer();
            Intrinsics.checkNotNull(playerEntity);
            Object[] objectArray = new Object[]{playerEntityDeathEvent.getTotemsPopped()};
            ModuleMessages.S(module, playerEntity.getName().getString() + " " + ModuleMessages.i18n$default(this, "death", objectArray, false, 4, null), false, null);
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Object object;
        Object object2;
        Object object3;
        Object object4;
        if (this.getStash()) {
            for (WorldChunk worldChunk : ServerInfo.INSTANCE.getLoadedChunks()) {
                if (notificationEntries.contains(worldChunk)) continue;
                object4 = worldChunk.getBlockEntities().values().stream().toList();
                int n = 0;
                int n2 = 0;
                object3 = object4.iterator();
                while (object3.hasNext()) {
                    object2 = (BlockEntity)object3.next();
                    if (object2 instanceof ChestBlockEntity) {
                        ++n;
                    }
                    if (!(object2 instanceof ShulkerBoxBlockEntity)) continue;
                    ++n2;
                }
                if (n < this.getMinChests() || n2 < this.getMinShulkers()) continue;
                notificationEntries.add(worldChunk);
                int n3 = n2;
                int n4 = n;
                int n5 = worldChunk.getPos().getCenterZ();
                int n6 = worldChunk.getPos().getCenterX();
                object3 = "Stash: X: " + n6 + " Z: " + n5 + " Chests: " + n4 + " Shulkers: " + n3;
                Notifications.push$default(Notifications.INSTANCE, "Stash Logger", (String)object3, 5, NotificationType.SUCCESS, null, 16, null);
                ModuleMessages.S(this, (String)object3, false, null);
                if (O.hasPassed2(5000)) {
                    Sounds.INSTANCE.getFoundStash().play();
                }
                object2 = "unknown_server";
                ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
                Intrinsics.checkNotNull(clientPlayNetworkHandler);
                if (clientPlayNetworkHandler.getServerInfo() != null) {
                    ClientPlayNetworkHandler clientPlayNetworkHandler2 = Globals.getMc().getNetworkHandler();
                    Intrinsics.checkNotNull(clientPlayNetworkHandler2);
                    net.minecraft.client.network.ServerInfo serverInfo = clientPlayNetworkHandler2.getServerInfo();
                    Intrinsics.checkNotNull(serverInfo);
                    if (serverInfo.address != null) {
                        ClientPlayNetworkHandler clientPlayNetworkHandler3 = Globals.getMc().getNetworkHandler();
                        Intrinsics.checkNotNull(clientPlayNetworkHandler3);
                        net.minecraft.client.network.ServerInfo serverInfo2 = clientPlayNetworkHandler3.getServerInfo();
                        Intrinsics.checkNotNull(serverInfo2);
                        String string = serverInfo2.address;
                        Intrinsics.checkNotNullExpressionValue(string, "ip");
                        object2 = StringsKt.replace$default(string, ':', '_', false, 4, null);
                    }
                }
                try {
                    object = new BufferedWriter(new FileWriter(new File(ConfigFiles.getStashesDir(), object2 + ".txt"), true));
                    ((Writer)object).append("\n" + (String)object3);
                    ((BufferedWriter)object).close();
                }
                catch (Exception exception) {}
            }
        }
        if (this.getPastEffects() != NotifierPastEffectsMode.OFF) {
            Iterable iterable;
            Iterable<BlockEntity> iterable2 = blockEntities;
            object4 = iterable2;
            Collection collection4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            object3 = object4.iterator();
            while (object3.hasNext()) {
                object2 = object3.next();
                object = (StatusEffectInstance)object2;
                iterable = collection4;
                iterable.add(((StatusEffectInstance)object).getTranslationKey());
            }
            Iterable iterable3 = (List)collection4;
            Collection<StatusEffectInstance> collection = Globals.getPlayer().getStatusEffects();
            Intrinsics.checkNotNullExpressionValue(collection, "getActiveEffects(...)");
            iterable2 = collection;
            iterable = iterable3;
            object4 = iterable2;
            Collection collection2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            object3 = object4.iterator();
            while (object3.hasNext()) {
                object2 = object3.next();
                object = (StatusEffectInstance)object2;
                Collection collection3 = collection2;
                collection3.add(((StatusEffectInstance)object).getTranslationKey());
            }
            iterable2 = CollectionsKt.minus(iterable, CollectionsKt.toSet((List)collection2));
            for (Object e : iterable2) {
                String string = (String)e;
                if (INSTANCE.getPastEffects() == NotifierPastEffectsMode.NOTIFICATION || INSTANCE.getPastEffects() == NotifierPastEffectsMode.BOTH) {
                    Module module = INSTANCE;
                    Module module2 = INSTANCE;
                    object2 = new Object[1];
                    Intrinsics.checkNotNull(string);
                    Object[] objectArray = new Object[]{};
                    String string2 = string;
                    object2[0] = I18n.INSTANCE.translate(string2, objectArray);
                    Object var63_42 = null;
                    int n = 4;
                    boolean bl = false;
                    Object object5 = object2;
                    String string3 = "past-effect";
                    Module module3 = module2;
                    ItemStack itemStack = Items.POTION.getDefaultStack();
                    int n7 = 2;
                    NotificationType notificationType = NotificationType.WARNING;
                    String string4 = ModuleMessages.i18n$default(module3, string3, object5, bl, n, var63_42);
                    Module module4 = module;
                    ModuleMessages.notify(module4, string4, notificationType, n7, itemStack);
                }
                if (INSTANCE.getPastEffects() != NotifierPastEffectsMode.CHAT && INSTANCE.getPastEffects() != NotifierPastEffectsMode.BOTH) continue;
                Module module = INSTANCE;
                Module module5 = INSTANCE;
                object2 = new Object[1];
                Intrinsics.checkNotNull(string);
                Object[] objectArray = new Object[]{};
                String string5 = string;
                object2[0] = I18n.INSTANCE.translate(string5, objectArray);
                Object var77_29 = null;
                int n = 4;
                boolean bl = false;
                Object object6 = object2;
                String string6 = "past-effect";
                Module module6 = module5;
                ModuleMessages.S(module, ModuleMessages.i18n$default(module6, string6, object6, bl, n, var77_29), false, null);
            }
            blockEntities.clear();
            Collection<StatusEffectInstance> collection5 = Globals.getPlayer().getStatusEffects();
            Intrinsics.checkNotNullExpressionValue(collection5, "getActiveEffects(...)");
            blockEntities.addAll(collection5);
        }
    }

    private final String formatSeconds(long l) {
        String string = "%.1f";
        Object[] objectArray = new Object[]{Float.valueOf((float)l / 1000.0f)};
        String string2 = String.format(string, Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue(string2, "format(...)");
        return string2;
    }

    private final void renderNotification(DrawContext drawContext, RenderTarget renderTarget, RenderTarget renderTarget2, long l, String string, String string2) {
        long l2 = l = c ^ l;
        long l3 = l2 ^ 0x7D46EC4C314FL;
        long l4 = l2 ^ 0x727650642001L;
        long l5 = l2 ^ 0x173BDAE3EDD9L;
        float f = Math.max(Fonts.getSF_M2().getWidth(l3, string2), Fonts.getSF_SB().getWidth(l3, string));
        float f2 = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - f / 2.0f - 17.0f;
        int n = (int)((double)System.currentTimeMillis() / 3.0 % 360.0);
        n = n >= 180 ? 360 - n : n;
        Color color = Color.RED;
        Intrinsics.checkNotNullExpressionValue(color, "RED");
        Color color2 = Color.YELLOW;
        Intrinsics.checkNotNullExpressionValue(color2, "YELLOW");
        Color color3 = RenderUtil.INSTANCE.blend(color, color2, (float)n / 360.0f);
        Fonts.getSF_SB().drawStringWithShadow(drawContext, string, l4, f2, 8.0f, color3);
        Fonts.getSF_M2().drawStringWithShadow(drawContext, string2, l4, f2, 20.0f, color3);
        RenderTarget renderTarget3 = n > 90 ? renderTarget : renderTarget2;
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        RenderTarget.drawRoundedText$default(renderTarget3, (Matrix3x2f)matrix3x2fStack, (double)(f2 + 5.0f) + (double)f, 5.0, 0.0f, 0.0f, color3, null, 0.0f, 216, l5, null);
    }

    public final void onTotemPop(@NotNull String string, int n) {
        block1: {
            Intrinsics.checkNotNullParameter(string, "name");
            if (!this.getTotemPops()) break block1;
            Object[] objectArray = new Object[]{n};
            ModuleMessages.S(this, string + " " + ModuleMessages.i18n$default(this, "totem", objectArray, false, 4, null), false, null);
            if (assistantCooldown3s.hasPassed2(3000) && Assistant.INSTANCE.getEnabled()) {
                Sounds.INSTANCE.getTotemPop().play();
            }
        }
    }

    private final int getDurabilityPercent(ItemStack itemStack) {
        return (int)((double)(itemStack.getMaxDamage() - itemStack.getDamage()) / Math.max(0.1, (double)itemStack.getMaxDamage()) * 100.0);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getStash();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getStash();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getSplashBuff() != NotifierPastEffectsMode.OFF;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getSplashBuff() != NotifierPastEffectsMode.OFF;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getSplashBuff() != NotifierPastEffectsMode.OFF;
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getItemCollect() != NotifierPastEffectsMode.OFF;
    }

    private static final boolean onReceivePacket$lambda$0(List list, Map.Entry entry) {
        Intrinsics.checkNotNullParameter(entry, "<destruct>");
        Object k = entry.getKey();
        Intrinsics.checkNotNullExpressionValue(k, "component1(...)");
        String string = (String)k;
        return !list.contains(string);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block26: {
            block25: {
                block24: {
                    block23: {
                        block22: {
                            block21: {
                                Notifier.c = ZkmKeys.a(8727518763717427279L, 4851239753951672957L, MethodHandles.lookup().lookupClass()).a(144039010805440L);
                                var31 = Notifier.c ^ 11129531296214L;
                                Notifier.gb = new HashMap<K, V>(13);
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
                                var29_3 = new String[131];
                                var27_4 = 0;
                                var26_5 = ";\u0091\f\u0005\u00e2W\u00e0\f\u0001)\u00b6z8\u00fe\u0098\u00d1\u00ad\u009e(\u00ba\u00d12\u00acU\u00d2_\u00ea\u0082m5\u00fe\u00af \u009e\u0091\u0094Z\u00f5\u00fa\u00del\u00c9<Cu\u00bf\u0013\u00de#;\u00db#\u009f\u0087\u00b9\u00bfc\u0015\u0091\"\u0095\\\u0094\u00bf\u00cf\u0010\"\u00aei=\u00c8\u00fb\u00d9-\f\u00e7\u0015\u001dU\u00f2\u00ea,\u0010\u001a\u00f14\u00f2\u0002Pz\u00f9\u00bc\u00b4\u0090_\u0012\u00deQ\u00b7 \u0093\u00e5\u00b4~o\u00c3\u001f\u008c\u00ce3d\u0099\u0019\u00edQ\u00de\u008e\u009d\u0085\u00cd'h\n\u00f7.Ye\u00c8h\u00cb\u00ca+\u0010\u00d9\u00c71\u0091\u00a2Z\u0019\u00b4\u00a6+\u001b&\u00ab\u00e4\u00c7u\u0018\u00d2t5\u00a3\u00f4LR\u0016\u00bew\u00ef\u00d4\u00fft\u00d3\u00be\u0085\u009cM[\u0086\u0012Zw\u0010\u008d\u00e5V\u009a\r\u00d6\u0018\u00b9A\u0082\u00a0\u009a\u0096\u0001m\u00df\u0010\u0088B\u0088\u00b9O\u009f4H\b\u00a8\u009c\u0003H\u00a7\u00db\u00a0\u0010\u0011\u0093\u0092\u00c8\u00bd\u0094\u007f4O\u00d8>\u00df]\u00dc[\u00c3\u0018\u00afJ\u000b\u00d0\u00a8K\rXp\u00c3\"5\u00fa\u0013\u00b1\u0096\u00a1 k\u00b9PD \u0089\u00c0\u0018\u00fa\u00db\u00df\u008cj?\u001b\u001b\\\u0003+<}\u008b;P\u00a0\u00b3\u00855w\u00c0<\u00bfid\u008f\u00a3\u00d3\u00a4\n\u00d6\u009b\u0089W4\u00c0\u001f\u00bb.\u00f3\u009a\tX%\u008aj\u00b6z\u0083\u00e1\u00e1@\u0092j#\u00f6^\u0015\u00c5n*G\u0096\u0097\u0015\u0003\u00ae\u0086RU\u0084\u00d2[\u0085D\u0093p\u00eb\u00d6Z\u00f8T\u00ec\u0093\u00b9\u0006\u00ee\u0080\u00d5-\u00bfa\u0099N\u00d4uN\u0001\u00a0\u00ee'\u00d7\u0092?c\u00800\u00ac\u00e1h\u0007\u00dd\u00d3\u00aa\u00d9\u0083\u008f\u0013\u001f8\u00f7\u0086RT)\u008c\u00b0\u00b4\u00ccD\u00adF\u00b1\u00c7\u00ba\u008f\u0011\u00d2\u0013\u00ba\u00ad\u00da\u00c2f\u0095\\)\u008b\u00cf\u000eZ\u00d5\u00bb)\u00f9\")\u00e1\u00de@\u0095\u00a67\u0096\u0094\f/\u00a7-N\u000bl\u00e02\u00f0\u00b5\u00f4z3\u0082\u0086,\u00ce\u00ff\u0081|\u008bg\u000b\u008b\u0010\u00dc\u0013\u00fb\u00c1\u00dc2k\u009c\u008b\u008c\u00b4\u00a0r\u00d2\u008e\u00fe\u0018v\u00a2u:\u000e\u001e\u009d_\u000f\u00dbn<\u00f1\u00e2\u00b08\u00a8\u0086\u00e4o{\u0007\u00d0\u0018\u0018\u00dbp_\u0099\u00ef\f\u00c9\u0095\u00ddS*\u00ea\u00f3\u00c3\u00d6\u0000\u000b\u00b0(b,L\u00ee2\u0018\u008e\u00e0\u009d\u00f9\t\u00a7\u00cc\u00d5{\u00c4c\u00a9w^#\u00ac!\u0088\u000bs{\u009a.\u00a4(\u00d1\u00d0\u008b\u00c0\u00be\u00dd\u0080B~\u00cb\u00c5R-{\u00ec[p\u007f0r\"\u008e6E\u0014\u00cb[\n\u0095a\u00e8\u00cc\u00cf\u00c03(\u00e5\u00e0\u0001\u00f5 \u00b3]\u0096.\u00c1f\u00f6A\u0016q\u00e9}63p9<\u0094\u00d3\u0014'84\u00d7U\u00a5\u0088b|Z\u00dd\u0092 &[i\u009c\u000fN\u0012\u00ac\u000b\u00d8\u0085U\u00fe\u0080\u00ba\u00ef\u008b\u0015\u0001^h\u0017\u00e9\u00a3b\u0097\u00a4\t\u00bb#\u00bd\u00b9 \u00e9z\u0006\\\u00be\u009cq\u00cdd6D\u00fc\u00a5\u00fd\u009am\u00e3\t\u00ce\u00b1!6\u008f4W\u00c8\u00b1\u00f2\u00bc\u00102\u00d9\u0010\u00b02\u00e0\u0019\u00db\u00b9\u0088\u00e1\u0000f\u0090)J,I\u00c4 \u00e5\u0095~r\u0081\u008d\u00cb\u00d4\u00c7\u0086b\\L\u008d\u0089\u00bb\u00de\u00edz\u00c7\u00da\u0003\u00bb\u00d0\u00e8a\u00a7]\u00bb\u0088\u00a5s(\u0082\u00c9Jp\u00ad\u00f0\u0003\u0006\u00f3\u00bd\u009bg0Q1\u0088Ke\u0012\u00c1\u00c5A\u00ee\u00e5;G\n=\u0015\u00d7\u00e0\u008c\u00c8,0\u00c2\u001a\u00ec'\u00d2(\u00f7\u00dc0\u00df\u00ee\u0002\u00ee\u0084h\u009c\u00d4\u00bb\u00f7f_\u00d4\u00d0\u00a9\u00fd\u001a`\u00bc\u00ee\u008f\u000f\u0001\u00e0\u00d3r/3VV\u00b6X\u008f\u00b5\u00b2Ic =g\u00cb\u00a8.\u00d9\u009b\u00b5\u00c7\u0098uo\\!K\u0099\u0003\u00ac$\u00df\r\u009c\u00ea\u00df\u00e7\u00a8\u0019v\u0092N\u008a\u00dd0\u009a\u0092u9\u0000\u0096\u0096;\u00c7s}i\u00ac\u00b6\u00e1/\u001a\u00e1\u00d0\u00fe\u00d0\u00cb\u0010\u000fC\u00a8\u00ef\u00aa~\u0017\u00af?{\u00d0\u00f5\u001e\u008a\u0004\u0080_\u00a1|1\u0019\u00e4)L\u00f7 Hr\u0018\u00a1\u00c6\u00bbo$\u00e7 \u00bb\u00e8\u00c6\u00fb8\u00e5\u00f3\u0007Ls\u00bb\u0086\u00de\u00d0\u00bb\u009eUv7c\u00b7\u00ab\u0018E\u00cee\u00b0\u00eb\u00c7u.\u00f9\u00c3\u0098\u00f0/\u00ed\rY\u00de;\u00e4\u00e1x\u00aad\u00f5 T\u00f9\u00c1\u001eJ\u00fe\u00edt-\u00a3&k\u00b5\u00f8\u00a5\u00ba\u0082m\u0093^s\u0015\u00c9=Og\u00de.fF\u00a6\u00b7 \u00df4\u001d\u00b0d\u0010]_\u00cd\u0011\u00a9\u008b1\u00cc\u00ac\u0092\u0089qey\\Wa\u00e3i2\u00cc\u009e\u00b2\u00ef\u008a\u00ea\u0010\n\u00be|Z\u00be\u0007\t\u0099{X\u001a$i\u008cpt\u0010X\u0096\u00ecZ\u00dci\u0086\u00c1a\u00b9W\u000bl\u0081\u00a9u\u0010\u00ad\u00c5\u0002\u00e7\u00db@\u00a5\u00cf/\u00be\u00de4x*O\u0080\u0010V\u007f2\u00ff\u00d7i.\u00ab\u00fb\b\u00df\u001d%\u00afv\u00c4\u0018\"[d5\u00b5\u001f\u00c8\u00d1F\u00c3I\u00e5~\u00a6\u000f\u0018\u0013\u00eb\u00aa$S\u00fd:\u00e6(\u008f\u00b3\u0003\u00a7\u0086\u0097(\u0093h\u00be\u0088\u009a\u00fa\u00c9q\u00c3\u00b3x\u00d6\u000b\u00d4\u0018R\u0011'\u008a4\u001b\u00a0\u00fe:\u00ab\u00a2\u00e1\u0019\u009d\u00aea\u00e1\\\u0010\u009fkK\u00daavX\u00ff\u00ed\u009b\u0091S\u00bc\u00bd\u00e0\u001f \u0005EJqY\u008eD8|4x;%(\u009b\"\u009e\u00dc<\u00e4s\r\u00d8m\u001f\u00d8\u0088\u00ad\u00f2\u0095|\u00dd \u0004!\u00f2&\u00b3\u00f2\u0081\u00a0A\u0084\u00af\u00e2\u00c4=\u0086\u00a6\u00a1\u000f\u00ea(\u00d2\u00d2(\u00cc0(\u008a\u00ad\u00f4u\u00cf\u0001 [\u0095\u00f6\u00ab\u00be\u00ad\u0093l|\u009a+\u001d\u00d7xJ\u00f7\u0094\u00d6v\u00d4\u00fd\u001e\u001eG\\Jt \u00e5`\u00ce\u00060R}\tF[#~\u007f\u00e2\u00cf\u0090i\u00a1\u0093BK\u00b2W\u00d0)>\u00aa!\u0011\u00f3p\u00ba\u0012\u00de\u00fe\u00e9\u00cf\u00b5B\u0015]\u00f5\\Wc\u00c4~D\u0098\u00fe\u00d1\u0001F \u00d0\u00a4e\u0016\u00fd]G\u001d\u0095\u00fd\u00a3fK\u0087F${\u00a2+}\u0099\u00c0\u00da\u00eaf=\u00e2\u0085\u001b\u00aeQ\u009d \u00f6S'93.r\u00e5\u001b\u00b7\u0082F\u00d72\u00aaW\u00eb\u009b\u0013p\u009e\u00c4\u00f2\u00c1\u00b1\u00be\u000f\u00fdT\u00c9\u00abr\u0018\u00a6\u00c6\u00e6\u00c0\u00f7\u0088\u00f1-H\u00d5\u00aa\u0091Q\u0082\u0092\u00b1`\u0090\u0083[&\u0089\u00a6\u00ab oz?\u00af25.0%\u00b7\u0015a-\u0083\u00a8\u00dfg\u00da\u00bc\u00a5\u00eb\u00a8e\u00cc\u000b\u00d9\u009f\u00d4\u00b8\u008f\u00d8e(\u00a7\u00a8\u00fe\u00fe\u0092~\u00da\u00f4\u00aa\t\u000fb\u00df\u0012\u00d1\u00886<\u00ae\u008f\u00ba\u0000\u00c09\u00d9B\u00f5e@+K\u0016\u0011\u0089\u0096+G\u00ca\u00dex\u0018wi\u00cb\u00bd\u0085\u009de'\u00be\u0095\u00bc(r\u0000\u0095[\u00d0\u00d9[\u00ec\u00bbVc\u008a\u0010\u00e9jO#E\u00f5c\u00a6\u00e2\u00b7p\u008b\u00b4\u00c9\u00d8\u001a \u0087gz\u009ad8\u000f\u00b3\u00b5\u00c2\u00d5\u00ba\"\u00f9\u00ec\u00ee\u0005\u00a0\u00be\u00e3K\u009e\u00a6\u00b7\u0012)I\u00f9A\u00df*\u008d \u00ddk\u00f0T<\rI\u00e2\u0092\u00f8\u001a\u00984\u009f_o\u00aa\u0000\u008cu\u00f2\u00da^\u00c3\u00c7\u00de\u0099\u009f\u00df\u00be\u00a6\u00f2(\u008c\u009ca\u00a6d\u00f9E\u00c4\u0085S\u0080\u009bo\u00d5\u00c5l\u0085s\u00e0\u0010\u008d\u00d8\u001a\u00a1\u00d2\u00ab\u00e8\u00cb\u009e\u00a0\u0086\u00f4\u00c6\u00a7!\u00cb9nv2(\u0003\u0010\u0002\u008a\u00b8\u0010>x\u00d9\u00bd\u00df\u00aaC\u00f9\u00ee\u0015\u0019Y\u00f9\u00d3\u00fc)\u001d\u00fc\u009c\u00c1\u00bb\u009c\u00ba\u00fcD\u00c4X\u0001\u008b`\u00d2\u0095(< \u0005\u0095\u00f0K\u00c3^\u0016\u001bp\u00ec\u00d3+\u009b\u00f5\u008ex\u009f\u000eI\u0001\u00ae\u001dc\u0002\u00da\u00f2\\\u008e\u00df$\u0093@\u0018\u00d9\u0014!\u00b9aW\u00fb\u00ca:\u007f\u00fc\u00da\u00af\u00e6fZ\u00f3\u0082\u009d\u00f8\u00a6\u00e5Cy \u00d3\u00eb\u000b\u00ebx\u00191\u0082\u0000POlk \u00b2~\u00ebk\u009b\u00e4\u00a2>v\u0091\u0012\u0011t\u00b2\u00e4x\"q\u0018\u000foX\\\u0095p*\u0087\u00e9\u0091A\u0093\u00a2TJ\u00ce\"u\u0005\u00c4\u001d\u00c1i\u00d3 \u00c6]J\u0090\u0004\u00b7\u00c3\u00b9\u00e2\u001f2p[\u00d0\u0011\u0087\u00ff\u00cd\u00e4-x0\u0096\u001dqe\u00ff28\u00f9\u0092\u00cf\u0010\u00883Z\tW\u00c03\u0086\u00d2\u00f3\u00d4\u00ae\u00cf\u00fc\u00a7\u00978\u00c1\u00a1\u00b7QI\u00f7\u00a1\u008an|\u00e8\u00c5\u00f8t\u00fbC\u008fF\u0091\u001a5\u008e\u009b8\u0019T\u0092ri<\u0084\u0001\b\u0012a\u00b0\u00a0\u00a51OLuU\u0088\u00b3\u00f1@8\u008d\u0014\u00d0\u00ba\u00f9v\u0092D \u0096\u00a4t\u00e4\u00c9\u0083\u00e2j\u00a6[~\u00ad\u009e\u0015VO\r\u001e\u00a0\u0010J\u00ce{\u0017\u000bW\u001aY\u00b6\u0083&&\u0010v\u00ef-\b]\u00e7h\u00e43Z\n:\\a\u00d1\u00ae\u0010e\u00b3Sp\u008a\u00cd\u00b5?\u00fb0\u00a7\u0001\u0095\u00cf\u00d3\u00b0\u0018\u00ad\u0016\u00eb\u00dc<|lJ1Q\u00d7\u00afBE\u008a\u0005&\u0000\u00ed\u00da\u00cc\u0091)\u0007\u0018C\u00bb\u001e9T\u00e6\u001fe\u000b\u00c5g\u00ba\u0001\u00c3\u00b7%\u0001\u00c1\u0093\u00f6\u0012\u00e5\u00ed7(h<\u0089t\u00ed\u00823B\r\u0004\u00145\u00d9\u00d6\u008f\u00cd&j_v\u00fb\u001cbe2\u00faUM\u00810/PH\u00f8\u0082\u0010\u000e2)=\u0010}\u00fb\"\u00d3\u00e5\u00f1\u00c2)v\u0017M\u00ef6\u00f6E\u0094\u0018\u00e6\u00d2\u00f9\u00be^v\u00c2\u00bd\u00a2\u00f4{]~\u0011yp\u00e1\u00b0X\u00a8\u00ffq\u00a6~\u0010\u00cevO\u00e0G\u0006<_\u001c\"\u00f3\u00f0nA\u0086'\u0018\u009f5\u008e\u001d\u00f4w\u0000\b\u00d4\u001fwf\u0086h\u00eb\u00d9z\u001d'D@\u00ee\u0016&8\u00c0\u00c8\u00cda\u000bJ\u0087B\u0092\u008f\u00f2D\u0000\u00de\t\u0011\u000b\u00fa\u0091p\u00dcL\u00e5\u0097\u00c09\u000e\u00bf\u00ca\u00a5\u00bd\u00d5e3\u0001t\u00b6`-\u0090O}\u0082\u00cb<3\u0096\u0089W\u00fb*1\u009a\u00b1\u00df\u00e4 \b\u0002\u00ac\u00f8f\u0005\u00cd\u00aeFR\u001a\u001d\u00e0\u00c2\u000b\u0011\u0099\u00b4\u00ff\u00e8\u0013\u00d4\u00d5\u0019\u00e4\u0003z\u00d5\u0002\u00e0\u009f\u00e1(G\u0081\u00e9\u00e0\u0018bm\u00c0^\u00fc\u008b\u00bc\u00a27\u009a\u00c5nj\u00c5.\u00b4\u00f7]\u00aa\u00aa3(\u00f7\u0016:\u00a4\u00c7\u00f8\u0086\u00e3u\u0010}\u00d3\u00eb\u0018\u00bf\u0097f\u00f2e\u00e5T\u00ac\u00e9\u00c7\u000e\u008b\u00d1p3\u0006\u00d4\u00d1L\u00d4\u0081q\u00d8\u0085 \u0090T%&*8^7\u00f3k{o\u0081\u00c45\u00f1\u008b\u00c8\u0082\u00fc\u000e\u00a6\\\\)\u008b\u00e9)|+\u00c3s\u0010:\u0083;pG\u00fc\u0015~x\u00a1\u00a9 e\u0095T\u00f9\u0010\u00be\u0085\u00fef\u00e5[\u008d\u00af\u00e4\u00a3y\u00f1\u00e66\u00daH\u0010\u00c8^l\u0089\u00f3O\u0082e\u0006\u009a\u00e2er\u00a8\u00d0r\u0018\u00c6\u00f96\u0005\u00a3\u00ad\u00b6\u00e5/\u00fd\u0095\u0017?E\u00de\u008bX\u00d1N\u00a0\u00bf\u001d\u001a\u00d4 }u\u00a6\u009d \u00c4\u0095\u001c\u00e2\u00b6$Cs\u0000\u00c4\u008a\u00ea\u00c5\u00c4\u008d\u00cc/e\u0082\u00f9J\u008dN\u0097U\u00a7\u0087\u0018\u00b61\u0015\u00ea\u0011z\u00b8\u00c7!\u00de\u00ac\u00dc!\u0098\u00eb\u00aa\u00c6#\"\u00d6\u0018\u00f5-\u0097(\\\u00c5\u00e0\u0084\u00ae\u00d3|\u00c6\u0086\u00e8\u0090b\u0014\u0088 \u00b6\u0096\u00bbo\u00a8\u0082\u00e9fCv\u00c0\u00d9a]ZK\u00ea\u00c0\u00dfau\u0087\u00ed\u008d\u0006\u0018x\u00c6h\u009f\u00eeD_\u00c2\u00e5udf\u0001\u00ff5f\u0080d\u00e6\u00d5\u008e\u00b4I\u00ee8@}\u00dcl\u000bjD\u00ab\u00d2.\u00ac\u00a6\u00d6|O\u0087\u00d0K%1|\u008d\u00aa\u00aa\u0093AZ\u00c1N\u00d2-\u00f1\u001d\u00dc\u007f\u00d3\u0016a[\u00bck\u00d8\u00cb&h\u00dcB\u0016\u00d4\u00bb$\u00cfx\u0015]\u001c\u0018\u0011*\u009av\u0003\u00c6\u00a8\u00d8\u00faR\\\u008c6\u00e3Be\u00de&\u00e4\u00bb\u00bb!2\u0096 KgPIZKO\u0012\u00f7\u0083\u0004\fH\u008b(\u0018\u009ay\u0085\u001ab/k\r\u001e\u00c5\f\u0092\u0018w\u00159\u0010x\u0086\u00bb\u00a0\u001c\u0086j\u00f8F\u00ad7\u0089d\u00181 \u0010\u000b\u00ad\u001a\u00b0\u001c\u00e1\u00e2\b\u0000\u00df\u00bf\u00d0\u0090\u001f\u0091#\u0018>\u00b85-Xkd]<\u0018\u00a7\u0089\u00f7\u00df\u00d9.\u0005\u00a9\u00f6\u00f2D\u00dct\u0085 \u008e|\u001a\u00f1\u00ed\n\u00ba=\u00aa\u00c1\u00cd\u009b\u0083\u00cd\u008f\u00e4C\u0016\u000fi\u00e7\u00a0\u00b9\u00e1\u00b2\u0000\u0016\u00fcx\u00c1_\u001a\u0010\u000e\u001f\u0017}\u00f5O(\u00a6\u00ebnWu>\u00f6\u0019\u009c w\u00dc\u0015H\u008e/\u00af\u0083\u0010\u0081\u001d\u00d2d\u0014\u008f|U\u00fbE\u00a8\u00c22\u0094\u00c7\u00c2c\u00c3\u0089P\u00fb(\u00fa \u0080h_v\u00ed\u00a17\u00bfzj\u00b2'\u00e0\u00ee\u00ce\u00ef\u000e\u00c0$3o\u0002\u00a4\u0087 \u00eem\u008f\u0000X\u00ac@(\u00f4\u0081\u00cfw\u00d5|\u001e\u0005\u00cb\u0096\u0081?5Z\u00e8\u00ca#\u0019\f\u00ce3\u00e0\u00b7)\u00aa\u00e3\u00dcD\u00ac\u00f9\u0096\u0082I<d\u00dda\u00b7T@\u0010TL\u00ab\u00d9\u00e6-v\u00b7H\u00aa\u00bf\u0006\u00d7\u0080\u00ec\u001a8|98\\\u00f4\u0095/\u00fb\u00a6\u00b60\u00c3\u008a\u00d6\u0094^\u008a\u00b8DJ\u00a4\u009a)\u00b9\u0011E\u0087\u00dc\u00c0\"\u0093\u0000\u0099\u00b6!I\u009f\u008a\u00ae/\u0085h8<3\u00bd\u00daPu\u0083\u00bf\u0003-g\u008c\u0087\u00104\u00eb\u00ba\u001a\u0082\u00b7t\u00eb\u000b2\u00aeo\u00d4\u00aa\u00f5n\u0018&\u00fa\u00ca\u00d6\u00c7\u00ea\u00ad\u00cf\u00c2z\u00a2F\u00cb\u00b8$\u0094\u0086\u00dfh#\u008e\u00b28<\u0010\u00d0\u0006\u009b\u009c\fI\u00e9\u00f8\u008c\u00a2\u0007R\u008b\u0090\u00d9\u001e \u0093|K\u00bd\u008f\u009al\u00e9\u0091+f\u00d8\u00a5\n\u00ac\u0097\u00f2\u00fc\u0080\u00e4\u0006\u0002\u00c1\u00b0vu\fa\u00f3+o\u0000 \u00e6Q\u00a8\u008e\u007f\u00c4\u0013?{\u00db\u00d3\u009a\u0019\u008a\u00e2%\u008f'{)\u00b3\u0083b8/\u00fcA|\u00e9\u00f3\u00b1\u00b1(\u00bc\u00ba\u00cd\u001eBf~\u00ab\u0012\u00e0\u00f97\u00d8\u00b3\u001a\u00ed@p\u0014\u00a5t\u00ad\u000e\u00ee\u00e3\u0006V\u00d9\u00e6\u00c3\u00daD\u00b6N=\u00d4W\u001b;\u00c1\u0018q\u00d3y@\u00ef{\u00b1KW\u00fe\u00e4\u0012\u0010\u00be\u00f8F\u00f2\u00e1\u00af\u001f,H\u0013\b(\u00cd\u00a2m\u00fb\u0089R\u00aa\u0007\u0088L:z]C\u00e5\u00a1\u0016\u00b4\u000fON\u0000\u00f6\u00c5{Vu\u00c4\u00b9\u009c\u00b4\u00c3!\u0005\u00b5\u00c1\u00eeO\u00a6\u00f1(\u00e6t\u00a0\u00ac0\u001d_C\u00fce\u0090\u000f\u008dJ{q\u00cd\u00b94\b\u00cc\u00ad9\u00ec2\u0088_\u00ed\u00c9\u00f9\u00d2\u00f4VF\u00c5}\u0014n!v 3\u00cb\u00f6\u00e6\u00bd\u0005-\bv\u00a1z{NS\u00ee<\u009eA#f\u00d5\u00b2\u00fd\u00a60f\u00ab\u009f\u00ff\u00e2\u00d3\u0010\u0010e\u00daQ\u00b3\u0019}\u0098\u00f3\u00ed(=\u00d1,\u00a7\f\u0087 \u00c4F\u00f8WQ\u00cc\u00ca\u00a8E\u00a7\u0087\u0016\u0097\u001d)%\u00f3g\u0019\u0085q\u0012Y\u00b7\u00e1\u00c5\u00cf\u009e-bF_\u0018\u00cf4\u00d7\u00fd\u00b2\u0015\u00b5\u0098;_\u00a2\u000b\u00ce\u001ff'\u00f0\u0083:{\u00ea\u00de\t\u001e \u00ab6*q\u0080\u0015\u0001tC\u0096\u00f3\u00daO\u00a6\u00fcgD\u00fbQ6\u00b7 \u0002\u0083b\u00dc\u0012;X\u00c9\u00bf!\u0018:\u00cbYr\u00d3M\u0005N\u0017\u00d2pbHjH@\u00db\u00df\u0011\u0096\u009bV\u0004\u0097 \u00ba\u00aa-=\u00cb<\u00d0\u00cc\u00fe\u00d4\u00b0\u00de\u00d5E\u009dT, m\u00a5\u0084X\u00da\u00e8\u009f\u00f9\u00ba\u0004\u00d2K\u00f2H |\u00ba\u0005T\u00d9\u0099\u00ce5\u0088\u00c0\"\u0093\u00a0\u0004 \u00f3\u00c3G\u00a4\u00ea\u0005j'\u00c4\u00da5\u00fe\u00a6\u00ea|(h\u0018AX\u00cc\u00f1\u00d3\u0092\u008a\u00c9\u0084\u0097\u00ae1Y\u00a8s3\u00aa\u0096g9\u00c4\u001e\u0099\u00ce(\u00e1\u0006y/\u00d2\u00dc\u00ec\u00d9\u00fa\u00e7\u00b2I\u00e4\u00ec\u0080\u0087\u00c4\u0098OS\u00dc\u00e7s\u00d5%#\u00070\u00beXi\u00edn`J\f\u0002\u0084$'8\u00b5%<wpj\u0012\u008b\u00e3c\u00e4\u001c\u0095\u00a8\u00d7\u00f5\u0017^\u000b\f\u00bf6\u0086-\u00a0\u008d\u009c\u00b0\u00c1\u00a7\u00a1Z::0\u00a7;V\n`et&\u0014w\u00eb3\u0017\u00b0|\u00f1\u00e8\u00e3K\u00d9\u0087 \u001fAP\u009c\u000b\u00f9\u0016\u00c2\r\u00cbj`@\u00fa\b\u00aal\u00d2\u00d7\u001fw\u00d09\u00c5\u00e1Cf\u00ee\u00d0U0\u00c7\u0018\u0007\u0088\u00eed\u00b9\u00e1{l\u00b0\u008a\u001b\u0088\u009e\u0002\u00a4\u0004\u00c1\u00bf\u00b8\u00f7+\u00ca\u00ac\u00c6\u0010\u00e7u\u0006\u00a5e\u00bd\u00bd\u00a0\u00eeBA\u00a3\u000f\u00f0\u00a6C\u0010\u00b5\u00c2\u00a3\u00c9]\u00d6!\u00e9\u00d3\u00d8\u0015=\u00e7\u00e1?2 *\u0090/5\u0006\u00da\u00a2\u00f4\u0010\u008b\u008b|\u00edB\u00be\u0001\u00c06Cy\u00d4\u00ee\u009a?\u00fe\u00ba\u00caI\u00d4x\u0096\u00ad\u0010\u000fyh\b\u000b\u00f0\u00c1\u0086~\u0092\u00bb\u00bcu\u0001)6 o\u008dh*\u0081\u00c7Yu\u00af\u00f7\u00f6\u00c5\u0004\u0091\u00e5\u00f0\u00c3\u00a9\u00be\u0095A\u00e2(\u00f6\u0092\u00a3)8\u00e9\u00bd\u007f\u00b2\u0018\u00c6\u00cb>h\u00e3Q\u00e7\u00ddscOY~\u00a3\u0096\u00bb\u00d2\u00d3\u0098\u0089\u00856\u0085K\u0018\u008a\u00e5u|\u00d7j\u008ac\u008b]\u00b3!rS\u00f1\u00d3r\u00a7`\u00be\u00c2h\u0088( \u00b3D\u00c5\u008bL_\u009d\u00cf\u00bes\u008d+\u0006\u00fe+\u00b8\u00b8\u009b\u00d3\u00b7L\u00fa=\u00ffr\u00de\u00d6\u00f0\u00ff\u00d7\u001f\u00f3\u0010\u00acW\u00a5\u00b7\u0001\u0096Y\u00bdb\u0086\u00a7\u00ec\u0092\u00a5\t\u0013(X<\u009b\u00cb'\u0085q)\u0015\u00c1\u00d5\u00ee?F$\u0016(0\u007f\u00d1\u00a3\u00ac\u0087: \u00ca]\u00ca\u00a8\n\u00e4\u00b8\u0004Z\u00d6\u00fe@N\u00ac\u00cc \u0005\u00d2S\u0090Y\u0001m.Z\u0093\u00ab\u00cf\u00dcc\u00ec\u00cf\u0005\u00a5\u00b6\u00ec\u0092\u0003K:\u00c7\u0011y\u009cK\u00df\u00ea\u00c7 {\u00b4\u00f0\u0018\u00d3\u009b\u000e\u00a8\u00b6/\u00f4qKd\f7G\u00dd\u0086\u0098\u00f2i\u00f1\u007fq\fR[p=cf";
                                var28_6 = 3936;
                                var25_7 = 32;
                                var24_8 = -1;
lbl20:
                                // 2 sources

                                while (true) {
                                    v3 = ++var24_8;
                                    v4 = var26_5.substring(v3, v3 + var25_7);
                                    v5 = -1;
                                    break block21;
                                    break;
                                }
lbl25:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = Notifier.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    var26_5 = "yf\u00af\u0011\u00b9@\u0087M\u00e7\u009f\u0089p\u00e8|\u0010\u00a0\u00d2b5\u00f6\u0001\u00e2\u00c9\u00f5Y\u0015`\u00a7\u0015\u00e1\u00ca\u0083 4\u00be\u00d8\u00ab\u0002\u0087p\u0013\u00f4\rd_\u009e\u00a4\u0015/\u00a6\u00e8;\u00c4iuG\u00e1\u00e7\u00aa\u00a4S\u00e7\u00a3\u00e5\u00a0";
                                    var28_6 = 65;
                                    var25_7 = 32;
                                    var24_8 = -1;
lbl34:
                                    // 2 sources

                                    while (true) {
                                        v6 = ++var24_8;
                                        v4 = var26_5.substring(v6, v6 + var25_7);
                                        v5 = 0;
                                        break block21;
                                        break;
                                    }
                                    break;
                                }
lbl39:
                                // 1 sources

                                while (true) {
                                    var29_3[var27_4++] = Notifier.zkmDecodeUtf8(var30_9).intern();
                                    if ((var24_8 += var25_7) < var28_6) {
                                        var25_7 = var26_5.charAt(var24_8);
                                        ** continue;
                                    }
                                    break block22;
                                    break;
                                }
                            }
                            var30_9 = var22_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                        Notifier.ab = var29_3;
                        Notifier.fb = new String[131];
                        Notifier.mb = new HashMap<K, V>(13);
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
                        var17_12 = new long[60];
                        var14_13 = 0;
                        var15_14 = "\u00a4\u00a2\u000f2\u00f9}T\u00e8\u00c2\u00c1!)\u00cdaZ\u0098#\u00f8\u00f7\u00e2\u00a3,9\u00c0\b\u00ed\u00b6w\u000b D\u00b6D\u0005A\u00ca\u00e4\u00df\u00b9\u00a1N\u00beT\u00d3\u00d9I\u00f2\u00b9*\u00dc\u0084pN\u00d9\u00ae\\\u00f6\u00edp\u00ac;H\u00f0\u00f094C\u00f4=\u00ab\bL(\u00ba\u00c5wD\u00ba\u0082\u0086*J\u00a0\u0087\u00f3O\u00e5A\u00b4\u00fadS\u001cZ\u00cd\u00a7\u001b\u00ea\u00b1\fM\u00bb\u008b,\r\u008e\u0017\u0096\u0005Fr\u0013\u009a\u00fau\u00c7\u0084\u00b7\u00e3\u00ce\u0000\u00ccOV\u00a7M\\\u0013\u00cbk\u008b9!\u0014\u00e5\u00da$\u0013\u000f\u00e7\u0087\u00de\u00b3\u001f\u0012\u00bd4y\u00b4\u0097\u009f\u00f0\u00f1\"\u00bdF\u0016@\u00fc:g\\D\u00a2\u00fa\u00fa\u00f0\u00d2v\u0014d\u00ec\u00b7~\u00df\u00aaSe\u00bcR\u00e7\u00ec\u00a9@\u0013\u000f\u00ec\u00e9\u00b9\u00fd\u00dev\u00b1ph\u00e1U\u00cf*\u00d1V\u00cf\u0094\u00a8\u0017l\u00c2A\u00cb&\u00c3\u00e1\u00e5X\u00be2\u0098\u009b\u00c3\u00c1\u00e1\u001a5}a\u00cf\u0005\u00a0\u00d0gu\u00d2\u0092\u00c0N\u00b9!\u00ba\u00dc\u00ee\u0087$P\u008e\u000fi\u0096\u00f0\u00e5\u00e1\u00fb\u00b30+\u00dd&\u00ddZ%wb(!\u0017q?#)\u0001\u00e7\u00ad<\u00b3E\u0094M\u00cd>\u0099\u000fe\u0085s\f8\u000f1\u00a22\u0081\u00b5,\u0096\u00de\u00f0\u00fe\u0084\t\u00ca'W\u00c0Q\u00d1$\u00ff\u00d2*\u00ab\u0010\\A\u00c3\u00e9@y\u008a\u0089\u00bd\u00c9\u00a3\u00f02\u0084f\u007f\u0080Y\u00f9\u0080\u001e\u00edm\u00d6<)\u00e6\u00d2\u00ae\tV.\u00ccAxsOBWck?\u00e6|I\u00b2B\u008f)\u00c6\u00ba\u009fq?~\u00dd\u00a3%b@=,\u00c9\u0093\u00a4\u0085I6\u00f09\u0010\u0089(]\u008c\u00f3\u00b8\u00b3\u00d0\u00f6\u00ed\u00a1n\u00f2\u00e2\u00a7\u00aa~j\u0090\u00b9B\u00b5dX1z\u0085\u009aO\u00bd\u00bb:\u0086\u00b7\u00c5,u\u00e2?_\bC'\u00d7\u00e8Js\u009eG\u0081\u00905\u00f2R\u009fC\u00ea.F\r\u0014~\u00e0e\u00a1\u008c\u0085\u0001\u00e8s\u008d\u0013;S\u00a0#\u0017`\u00e8\b\u0093\"\u00ac";
                        var16_15 = 464;
                        var13_16 = 0;
                        while (true) {
                            var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                            v10 = var17_12;
                            v11 = var14_13++;
                            v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                            v13 = -1;
                            break block23;
                            break;
                        }
lbl78:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            var15_14 = ";\u00d9\u00d8\u00ce\u00a0y\u0099\u00f2CN\u00afP\u00c5\u00a6_^";
                            var16_15 = 16;
                            var13_16 = 0;
                            while (true) {
                                var18_17 = var15_14.substring(var13_16, var13_16 += 8).getBytes("ISO-8859-1");
                                v10 = var17_12;
                                v11 = var14_13++;
                                v12 = ((long)var18_17[0] & 255L) << 56 | ((long)var18_17[1] & 255L) << 48 | ((long)var18_17[2] & 255L) << 40 | ((long)var18_17[3] & 255L) << 32 | ((long)var18_17[4] & 255L) << 24 | ((long)var18_17[5] & 255L) << 16 | ((long)var18_17[6] & 255L) << 8 | (long)var18_17[7] & 255L;
                                v13 = 0;
                                break block23;
                                break;
                            }
                            break;
                        }
lbl91:
                        // 1 sources

                        while (true) {
                            v10[v11] = v14;
                            if (var13_16 < var16_15) ** continue;
                            break block24;
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
lbl104:
                        // 1 sources

                        ** continue;
                    }
                }
                Notifier.hb = var17_12;
                Notifier.lb = new Integer[60];
                Notifier.pb = new HashMap<K, V>(13);
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
                var6_22 = new long[3];
                var3_23 = 0;
                var4_24 = "\u00c0\u00fb\u00b8`S\u00d1 h$_\u0019\u0099$\u00b7\u008d\u00f4\u00a0=:\u001d\u000f4\u00b2a";
                var5_25 = 24;
                var2_26 = 0;
                while (true) {
                    break block25;
                    break;
                }
lbl126:
                // 1 sources

                while (true) {
                    var6_22[v18] = ((long)var10_29[0] & 255L) << 56 | ((long)var10_29[1] & 255L) << 48 | ((long)var10_29[2] & 255L) << 40 | ((long)var10_29[3] & 255L) << 32 | ((long)var10_29[4] & 255L) << 24 | ((long)var10_29[5] & 255L) << 16 | ((long)var10_29[6] & 255L) << 8 | (long)var10_29[7] & 255L;
                    if (var2_26 < var5_25) ** continue;
                    break block26;
                    break;
                }
            }
            var7_27 = var4_24.substring(var2_26, var2_26 += 8).getBytes("ISO-8859-1");
            v18 = var3_23++;
            var8_28 = ((long)var7_27[0] & 255L) << 56 | ((long)var7_27[1] & 255L) << 48 | ((long)var7_27[2] & 255L) << 40 | ((long)var7_27[3] & 255L) << 32 | ((long)var7_27[4] & 255L) << 24 | ((long)var7_27[5] & 255L) << 16 | ((long)var7_27[6] & 255L) << 8 | (long)var7_27[7] & 255L;
            var10_29 = var0_20.doFinal(new byte[]{(byte)(var8_28 >>> 56), (byte)(var8_28 >>> 48), (byte)(var8_28 >>> 40), (byte)(var8_28 >>> 32), (byte)(var8_28 >>> 24), (byte)(var8_28 >>> 16), (byte)(var8_28 >>> 8), (byte)var8_28});
            ** while (true)
        }
        Notifier.nb = var6_22;
        Notifier.ob = new Long[3];
        var43_30 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Notifier.class, "totemPops", "getTotemPops()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "lowArmor", "getLowArmor()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "rangeEnter", "getRangeEnter()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "rangeLeave", "getRangeLeave()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "lag", "getLag()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "rubberBand", "getRubberBand()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "stash", "getStash()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "minChests", "getMinChests()I", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "minShulkers", "getMinShulkers()I", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "mention", "getMention()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "pastEffects", "getPastEffects()Lsu/catlean/cm;", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "splashBuff", "getSplashBuff()Lsu/catlean/cm;", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "ftBypass", "getFtBypass()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "self", "getSelf()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "old", "getOld()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "itemCollect", "getItemCollect()Lsu/catlean/cm;", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "onlyDon", "getOnlyDon()Z", 0)), Reflection.property1(new PropertyReference1Impl(Notifier.class, "useTracker", "getUseTracker()Lsu/catlean/cm;", 0))};
        Notifier.delegatedProperties = var43_30;
        Notifier.INSTANCE = new Notifier();
        Notifier.totemPops$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "totem-pops", false, null, null, 12, null);
        Notifier.lowArmor$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "low-armor", false, null, null, 12, null);
        Notifier.rangeEnter$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "range-enter", false, null, null, 12, null);
        Notifier.rangeLeave$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "range-leave", false, null, null, 12, null);
        Notifier.lag$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "lag", false, null, null, 12, null);
        Notifier.rubberBand$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "rubber-band", false, null, null, 12, null);
        Notifier.stash$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "stash", false, null, null, 12, null);
        Notifier.minChests$delegate = SettingFactory.intSetting$default(Notifier.INSTANCE, "min-chests", 5, new IntRange(0, 100), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        Notifier.minShulkers$delegate = SettingFactory.intSetting$default(Notifier.INSTANCE, "min-shulkers", 0, new IntRange(0, 100), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        Notifier.mention$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "mention", false, null, null, 12, null);
        Notifier.pastEffects$delegate = SettingFactory.modeSetting$default((SettingContainer)Notifier.INSTANCE, "past-effects", (Enum)NotifierPastEffectsMode.BOTH, null, null, 12, null);
        Notifier.splashBuff$delegate = SettingFactory.modeSetting$default((SettingContainer)Notifier.INSTANCE, "splash-buff", (Enum)NotifierPastEffectsMode.BOTH, null, null, 12, null);
        Notifier.ftBypass$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "ft-bypass", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        Notifier.self$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "self", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 4, null);
        Notifier.old$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "pre-1.21", true, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)(), 4, null);
        Notifier.itemCollect$delegate = SettingFactory.modeSetting$default((SettingContainer)Notifier.INSTANCE, "item-collect", (Enum)NotifierPastEffectsMode.BOTH, null, null, 12, null);
        Notifier.onlyDon$delegate = SettingFactory.booleanSetting$default((SettingContainer)Notifier.INSTANCE, "only-don", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)(), 4, null);
        Notifier.useTracker$delegate = SettingFactory.modeSetting$default((SettingContainer)Notifier.INSTANCE, "use-tracker", (Enum)NotifierPastEffectsMode.BOTH, null, null, 12, null);
        Notifier.D = new RenderTarget("armor", 30, 30);
        Notifier.T = new RenderTarget("empty-armor", 30, 30);
        Notifier.k = new RenderTarget("wifi", 30, 30);
        Notifier.i = new RenderTarget("wifi-off", 30, 30);
        Notifier.N = new RenderTarget("rewind", 30, 30);
        Notifier.X = new RenderTarget("loudspeaker", 30, 30);
        Notifier.U = new RenderTarget("loudspeaker-empty", 30, 30);
        Notifier.assistantCooldown30s = new Timer();
        2340522854975236811L ^ var31;
        Notifier.assistantCooldown10s = new Timer();
        Notifier.assistantCooldown60s = new Timer();
        Notifier.assistantCooldown2500ms = new Timer();
        Notifier.assistantCooldown3s = new Timer();
        Notifier.I = new Timer();
        Notifier.mentionTimer = new Timer();
        Notifier.assistantCooldown2s = new Timer();
        Notifier.O = new Timer();
        Notifier.notificationEntries = new ArrayList<E>();
        Notifier.blockEntities = new ArrayList<E>();
        Notifier.pickups = new ConcurrentHashMap<K, V>();
        Notifier.d = -999;
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4A65;
        if (fb[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])gb.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    gb.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/q1", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = ab[n2].getBytes("ISO-8859-1");
            Notifier.fb[n2] = Notifier.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return fb[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Notifier.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6115;
        if (lb[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = hb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])mb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    mb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/q1", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Notifier.lb[n2] = n3;
        }
        return lb[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Notifier.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static long zkmDecryptLong(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5C7;
        if (ob[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = nb[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])pb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    pb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/q1", exception);
            }
            long l4 = ((long)byArray[0] & 0xFFL) << 56 | ((long)byArray[1] & 0xFFL) << 48 | ((long)byArray[2] & 0xFFL) << 40 | ((long)byArray[3] & 0xFFL) << 32 | ((long)byArray[4] & 0xFFL) << 24 | ((long)byArray[5] & 0xFFL) << 16 | ((long)byArray[6] & 0xFFL) << 8 | (long)byArray[7] & 0xFFL;
            Notifier.ob[n2] = l4;
        }
        return ob[n2];
    }

    private static long e(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        long l2 = Notifier.zkmDecryptLong(n, l);
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
            throw new RuntimeException("su/catlean/q1" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Notifier.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Notifier.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
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
            return MethodHandles.lookup().findStatic(Notifier.class, "e", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)J", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

