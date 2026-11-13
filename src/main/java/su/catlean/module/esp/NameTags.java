/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Ordering
 *  org.joml.Matrix3x2f
 *  org.joml.Matrix3x2fStack
 *  org.joml.Vector4d
 */
package su.catlean.module.esp;

import com.google.common.collect.Ordering;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.scoreboard.ReadableScoreboardScore;
import net.minecraft.scoreboard.ScoreHolder;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.scoreboard.ScoreboardDisplaySlot;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.number.StyledNumberFormat;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fStack;
import org.joml.Vector4d;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.RenderNameTagEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.esp.ArmorDisplayMode;
import su.catlean.module.esp.NameSource;
import su.catlean.module.esp.NameTagItemType;
import su.catlean.module.esp.NameTags$WhenMappings;
import su.catlean.module.esp.NameTags$onRender2D$1;
import su.catlean.module.esp.NameTagsHealthMode;
import su.catlean.module.esp.NameTagsNameMode;
import su.catlean.module.esp.NameTagsSeeCatleanUsersMode;
import su.catlean.render.Render2D;
import su.catlean.render.RenderTarget;
import su.catlean.render.RenderUtil;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.Animation;
import su.catlean.util.ArmorUtil;
import su.catlean.util.CatleanUserTracker;
import su.catlean.util.Easing;
import su.catlean.util.FriendManager;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.ServerInfo;
import su.catlean.util.TextUtil;
import su.catlean.util.combat.TargetUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class NameTags
extends Module {
    @NotNull
    public static final NameTags INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting seeCatleanUsers$delegate;
    @NotNull
    private static final ModeSetting nameMode$delegate;
    @NotNull
    private static final SettingGroup components$delegate;
    @NotNull
    private static final BooleanSetting gamemode$delegate;
    @NotNull
    private static final BooleanSetting ping$delegate;
    @NotNull
    private static final BooleanSetting hp$delegate;
    @NotNull
    private static final BooleanSetting distance$delegate;
    @NotNull
    private static final BooleanSetting pops$delegate;
    @NotNull
    private static final BooleanSetting enchantss$delegate;
    @NotNull
    private static final BooleanSetting onlyHands$delegate;
    @NotNull
    private static final BooleanSetting potions$delegate;
    @NotNull
    private static final BooleanSetting shulkers$delegate;
    @NotNull
    private static final SettingGroup selection$delegate;
    @NotNull
    private static final BooleanSetting spawners$delegate;
    @NotNull
    private static final BooleanSetting entityOwner$delegate;
    @NotNull
    private static final ColorSetting fillColorA$delegate;
    @NotNull
    private static final ColorSetting outlineColor$delegate;
    @NotNull
    private static final ColorSetting fillColorF$delegate;
    @NotNull
    private static final ColorSetting friendOutlineColor$delegate;
    @NotNull
    private static final ModeSetting armorMode$delegate;
    @NotNull
    private static final ModeSetting health$delegate;
    @NotNull
    private static final ModeSetting healthProvider$delegate;
    @NotNull
    private static final Map D;
    @NotNull
    private static final RenderTarget renderTarget;
    @NotNull
    private static Animation animation;
    @NotNull
    private static final Render2D render2D;
    @NotNull
    private static final Render2D l;
    @NotNull
    private static final Map f;
    @NotNull
    private static String G;
    private static int[] E;
    private static final long g;
    private static final String[] h;
    private static final String[] i;
    private static final Map w;
    private static final long[] B;
    private static final Integer[] C;
    private static final Map L;

    private NameTags() {
        super("name-tags", Categories.getEsp(), null, null);
    }

    @NotNull
    public final NameTagsSeeCatleanUsersMode getSeeCatleanUsers() {
        return (NameTagsSeeCatleanUsersMode)((Object)seeCatleanUsers$delegate.getValue(this, delegatedProperties[0]));
    }

    private final NameTagsNameMode getNameMode() {
        return (NameTagsNameMode)((Object)nameMode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final SettingParent getComponents() {
        return (SettingParent)components$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getGamemode() {
        return (Boolean)gamemode$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getPing() {
        return (Boolean)ping$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getHp() {
        return (Boolean)hp$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getDistance() {
        return (Boolean)distance$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getPops() {
        return (Boolean)pops$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getEnchantss() {
        return (Boolean)enchantss$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getOnlyHands() {
        return (Boolean)onlyHands$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getPotions() {
        return (Boolean)potions$delegate.getValue(this, delegatedProperties[10]);
    }

    private final boolean getShulkers() {
        return (Boolean)shulkers$delegate.getValue(this, delegatedProperties[11]);
    }

    private final SettingParent getSelection() {
        return (SettingParent)selection$delegate.getValue(this, delegatedProperties[12]);
    }

    private final boolean getSpawners() {
        return (Boolean)spawners$delegate.getValue(this, delegatedProperties[13]);
    }

    private final boolean getEntityOwner() {
        return (Boolean)entityOwner$delegate.getValue(this, delegatedProperties[14]);
    }

    private final Color getFillColorA() {
        return (Color)fillColorA$delegate.getValue(this, delegatedProperties[15]);
    }

    private final Color getOutlineColor() {
        return (Color)outlineColor$delegate.getValue(this, delegatedProperties[16]);
    }

    private final Color getFillColorF() {
        return (Color)fillColorF$delegate.getValue(this, delegatedProperties[17]);
    }

    private final Color getFriendOutlineColor() {
        return (Color)friendOutlineColor$delegate.getValue(this, delegatedProperties[18]);
    }

    private final ArmorDisplayMode getArmorMode() {
        return (ArmorDisplayMode)((Object)armorMode$delegate.getValue(this, delegatedProperties[19]));
    }

    private final NameTagsHealthMode getHealth() {
        return (NameTagsHealthMode)((Object)health$delegate.getValue(this, delegatedProperties[20]));
    }

    @NotNull
    public final NameSource getHealthProvider() {
        return (NameSource)((Object)healthProvider$delegate.getValue(this, delegatedProperties[21]));
    }

    @Flow
    private final void onRenderNameTag(RenderNameTagEvent renderNameTagEvent) {
        if (renderNameTagEvent.getState() instanceof PlayerEntityRenderState) {
            renderNameTagEvent.cancel();
        }
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        long l;
        long l2 = l = g ^ 0x490FA22CAFB3L;
        long l3 = l2 ^ 0x74FF8AE9053BL;
        long l4 = l2 ^ 0x5F91DE57835CL;
        long l5 = l2 ^ 0x79CA997FFCAL;
        long l6 = l2 ^ 0x6CC0504E7FECL;
        long l7 = l2 ^ 0x56E4E9E5A293L;
        int n = (int)(l7 >>> 32);
        int n2 = (int)(l7 << 32 >>> 32);
        long l8 = l2 ^ 0x1A36462A1CE1L;
        int n3 = (int)(l8 >>> 32);
        int n4 = (int)(l8 << 32 >>> 48);
        int n5 = (int)(l8 << 48 >>> 48);
        long l9 = l2 ^ 0x4B68E17DC68AL;
        long l10 = l2 ^ 0x47CC57F30F5EL;
        long l11 = l2 ^ 0x543CAF2FDEBDL;
        long l12 = l2 ^ 0x3C6B0A133B37L;
        long l13 = l2 ^ 0x2115D7D21A1CL;
        DrawContext drawContext = render2DEvent.getContext();
        animation.animate(Globals.getMc().currentScreen instanceof ChatScreen || Globals.getMc().currentScreen instanceof HudEditorScreen ? 1.0f : 0.0f);
        G = "";
        List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
        Intrinsics.checkNotNullExpressionValue(list, "players(...)");
        Object object22 = list;
        for (Object object22 : CollectionsKt.sortedWith(object22, new NameTags$onRender2D$1())) {
            if (Intrinsics.areEqual(object22, Globals.getMc().player) && Globals.getMc().options.getPerspective().isFirstPerson()) continue;
            Intrinsics.checkNotNull(object22);
            Object object3 = (Entity)object22;
            float f = this.getHealth() == NameTagsHealthMode.Number ? 0.4f : 0.55f;
            Vector4d vector4d = RenderUtil.INSTANCE.worldToScreen((Entity)object3, f);
            if (vector4d == null) continue;
            String string = this.getNameText(l10, (PlayerEntity)object22);
            float f2 = Fonts.getSF_B2().getWidth(l9, string);
            float f3 = (float)(vector4d.x + (double)((float)(vector4d.z - vector4d.x) / 2.0f) - (double)(f2 / 2.0f));
            if (Float.isNaN(f3)) continue;
            float f4 = this.drawItems((PlayerEntity)object22, drawContext, vector4d.x, vector4d.y, l3);
            boolean bl = false;
            float f5 = f3 - 2.0f;
            float f6 = f3 - 2.0f + f2 + 4.0f;
            float f7 = HudManager.INSTANCE.getMouseX();
            boolean bl2 = f5 <= f7 ? f7 <= f6 : false;
            if (bl2) {
                f5 = (float)(vector4d.y - 13.0);
                f6 = (float)(vector4d.y - 13.0) + 12.0f;
                f7 = HudManager.INSTANCE.getMouseY();
                boolean bl3 = f5 <= f7 ? f7 <= f6 : false;
                if (bl3 && Globals.getMc().currentScreen != null) {
                    Intrinsics.checkNotNullExpressionValue(((PlayerEntity)object22).getGameProfile().name(), "name(...)");
                    bl = true;
                }
            }
            if (Friends.getIsHomie((PlayerEntity)object22)) {
                Matrix3x2fStack matrix3x2fStack = render2DEvent.getContext().getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                NameTags.l.drawRoundedRect(matrix3x2fStack, f3 - 2.0f, (float)(vector4d.y - 13.0), f2 + 4.0f, 12.0f, bl ? 0.5f : 1.0f);
            } else {
                Matrix3x2fStack matrix3x2fStack = render2DEvent.getContext().getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                render2D.drawRoundedRect(matrix3x2fStack, f3 - 2.0f, (float)(vector4d.y - 13.0), f2 + 4.0f, 12.0f, bl ? 0.5f : 1.0f);
            }
            FontRenderer fontRenderer = Fonts.getSF_B2();
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            ItemStack itemStack = null;
            boolean bl4 = false;
            Color color = new Color(-1);
            FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, string, f3, (float)vector4d.y - 13.0f, l4, color, bl4, itemStack);
            if (this.getHealth() != NameTagsHealthMode.Number) {
                drawContext.getMatrices().pushMatrix();
                this.drawEquipment(l6, vector4d.x, vector4d.y, drawContext, (PlayerEntity)object22, (float)((LivingEntity)object22).getAttributeValue(EntityAttributes.MAX_HEALTH), MathHelper.ceil(((LivingEntity)object22).getHealth()), MathHelper.ceil(((PlayerEntity)object22).getAbsorptionAmount()));
                drawContext.getMatrices().popMatrix();
            }
            if (this.getPotions()) {
                f4 -= this.drawPotions(l11, drawContext, (float)vector4d.x, (float)(vector4d.y + (double)f4 - 55.0), (PlayerEntity)object22);
            }
            Intrinsics.checkNotNullExpressionValue(((LivingEntity)object22).getMainHandStack().getItem(), "getItem(...)");
            if (this.getShulkers() && Globals.getMc().currentScreen == null) {
                Item item;
                drawContext.getMatrices().translate((float)vector4d.x - 97.0f, (float)vector4d.y + f4 - 20.0f);
                ItemStack itemStack2 = item instanceof BlockItem && ((BlockItem)item).getBlock() instanceof ShulkerBoxBlock ? ((LivingEntity)object22).getMainHandStack() : ((LivingEntity)object22).getOffHandStack();
                Intrinsics.checkNotNull(itemStack2);
                itemStack = itemStack2;
                object3 = drawContext;
                this.drawShulkerPreview((DrawContext)object3, itemStack);
                drawContext.getMatrices().translate(-((float)vector4d.x - 97.0f), -((float)vector4d.y + f4 - 20.0f));
            }
            if (Globals.getMc().currentScreen instanceof ChatScreen || Globals.getMc().currentScreen instanceof HudEditorScreen || !(animation.getValue() == 0.0f)) {
                Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                RenderTarget.drawRoundedText$default(renderTarget, (Matrix3x2f)matrix3x2fStack2, (double)f3 - 18.0, vector4d.y - 16.0, 0.0f, 0.0f, null, null, animation.getValue(), 120, l13, null);
            }
            drawContext.getMatrices().popMatrix();
        }
        if (this.getSpawners()) {
            this.renderPlayerTags(l5, drawContext);
        }
        if (this.getEntityOwner()) {
            this.renderEntityTags(l12, drawContext);
        }
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
        render2D.drawGradientBackground(matrix3x2fStack, this.getFillColorA(), n, this.getOutlineColor(), n2, true);
        Matrix3x2fStack matrix3x2fStack3 = drawContext.getMatrices();
        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack3, "pose(...)");
        NameTags.l.drawGradientBackground(matrix3x2fStack3, this.getFillColorF(), n, this.getFriendOutlineColor(), n2, true);
        Fonts.getSF_B2().clearCache(n3, (char)n4, (short)n5);
        Fonts.getSF_M().clearCache(n3, (char)n4, (short)n5);
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        block4: {
            block6: {
                boolean bl;
                block7: {
                    InputEvent inputEvent2;
                    int n;
                    block5: {
                        n = NameTagsSeeCatleanUsersMode.zkmIntConstant();
                        boolean bl2 = StringsKt.isBlank(G);
                        if (n != 0) {
                            bl2 = !bl2;
                        }
                        if (!bl2 || !(Globals.getMc().currentScreen instanceof ChatScreen)) break block4;
                        inputEvent2 = inputEvent;
                        if (n == 0) break block5;
                        if (inputEvent2.getAction() != InputEvent.Action.Press) break block4;
                        inputEvent2 = inputEvent;
                    }
                    if (inputEvent2.getDevice() != InputEvent.Device.Mouse) break block4;
                    bl = FriendManager.INSTANCE.isFriend(G);
                    if (n == 0) break block6;
                    if (!bl) break block7;
                    FriendManager.INSTANCE.removeFriend(G);
                    ModuleMessages.S(this, Formatting.RED + G + Formatting.GRAY + " " + ModuleMessages.i18n$default(this, "removed", new Object[0], false, 4, null), false, null);
                    if (n != 0) break block4;
                }
                bl = FriendManager.INSTANCE.addFriend(G);
            }
            ModuleMessages.S(this, Formatting.GREEN + G + Formatting.GRAY + " " + ModuleMessages.i18n$default(this, "added", new Object[0], false, 4, null), false, null);
        }
    }

    private final float drawItems(PlayerEntity playerEntity, DrawContext drawContext, double d, double d2, long l) {
        float f;
        block19: {
            int n;
            ArrayList<ItemStack> arrayList;
            long l2;
            block18: {
                ArmorDisplayMode armorDisplayMode;
                ArmorDisplayMode armorDisplayMode2;
                block17: {
                    l2 = (l = g ^ l) ^ 0x658C5315F33CL;
                    arrayList = new ArrayList<ItemStack>();
                    f = 0.0f;
                    n = NameTagsSeeCatleanUsersMode.zkmIntConstant();
                    armorDisplayMode2 = this.getArmorMode();
                    armorDisplayMode = ArmorDisplayMode.Durability;
                    if (n == 0) break block17;
                    if (armorDisplayMode2 != armorDisplayMode) {
                        arrayList.add(playerEntity.getOffHandStack());
                    }
                    arrayList.add((ItemStack)ArmorUtil.getArmorItems(playerEntity).get(0));
                    arrayList.add((ItemStack)ArmorUtil.getArmorItems(playerEntity).get(1));
                    arrayList.add((ItemStack)ArmorUtil.getArmorItems(playerEntity).get(2));
                    arrayList.add((ItemStack)ArmorUtil.getArmorItems(playerEntity).get(3));
                    if (n == 0) break block18;
                    armorDisplayMode2 = this.getArmorMode();
                    armorDisplayMode = ArmorDisplayMode.Durability;
                }
                if (armorDisplayMode2 != armorDisplayMode) {
                    arrayList.add(playerEntity.getMainHandStack());
                }
                drawContext.getMatrices().pushMatrix();
            }
            float f2 = 0.0f;
            if (this.getArmorMode() == ArmorDisplayMode.None) break block19;
            Iterator iterator2 = arrayList.iterator();
            Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
            Iterator iterator3 = iterator2;
            block0: while (true) {
                boolean bl = iterator3.hasNext();
                block1: while (bl) {
                    block20: {
                        float f3;
                        block30: {
                            boolean bl2;
                            block31: {
                                ItemStack itemStack;
                                block29: {
                                    block22: {
                                        Color color;
                                        int n2;
                                        block25: {
                                            int n3;
                                            block28: {
                                                block27: {
                                                    int n4;
                                                    block26: {
                                                        block23: {
                                                            block24: {
                                                                block21: {
                                                                    itemStack = (ItemStack)iterator3.next();
                                                                    if (n == 0) continue block0;
                                                                    if (itemStack.isEmpty()) break block20;
                                                                    if (this.getArmorMode() != ArmorDisplayMode.Full) break block21;
                                                                    drawContext.getMatrices().pushMatrix();
                                                                    drawContext.getMatrices().translate((float)d - 55.0f + f2, (float)d2 - 33.0f);
                                                                    drawContext.getMatrices().scale(1.1f, 1.1f);
                                                                    drawContext.drawItem(itemStack, 0, 0);
                                                                    drawContext.drawStackOverlay(Globals.getMc().textRenderer, itemStack, 0, 0);
                                                                    drawContext.getMatrices().popMatrix();
                                                                    if (n != 0) break block22;
                                                                }
                                                                f3 = itemStack.getMaxDamage() - itemStack.getDamage();
                                                                n3 = n2 = (int)(f3 / (float)itemStack.getMaxDamage() * 100.0f);
                                                                n4 = 33;
                                                                if (n == 0) break block23;
                                                                if (n3 >= n4) break block24;
                                                                color = Color.RED;
                                                                break block25;
                                                            }
                                                            n3 = 34;
                                                            n4 = n2;
                                                        }
                                                        if (n == 0) break block26;
                                                        if (n3 > n4) break block27;
                                                        n3 = n2;
                                                        if (n == 0) break block28;
                                                        n4 = 66;
                                                    }
                                                    n3 = n3 < n4 ? 1 : 0;
                                                    break block28;
                                                }
                                                n3 = 0;
                                            }
                                            color = n3 != 0 ? Color.YELLOW : Color.GREEN;
                                        }
                                        Color color2 = color;
                                        FontRenderer fontRenderer = Fonts.getSF_M();
                                        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                                        Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                                        String string = n2 + "%";
                                        float f4 = (float)d - 35.0f + f2;
                                        float f5 = (float)d2 - 22.0f;
                                        Intrinsics.checkNotNull(color2);
                                        FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, string, f4, f5, l2, color2, false, null);
                                    }
                                    f3 = 0.0f;
                                    Intrinsics.checkNotNullExpressionValue(EnchantmentHelper.getEnchantments(itemStack), "getEnchantmentsForCrafting(...)");
                                    if (n == 0) continue block0;
                                    if (!this.getEnchantss()) break block20;
                                    bl2 = this.getOnlyHands();
                                    if (n == 0) break block29;
                                    if (!bl2) break block30;
                                    bl2 = Intrinsics.areEqual(itemStack, playerEntity.getOffHandStack());
                                }
                                if (n == 0) break block31;
                                if (bl2) break block30;
                                bl2 = Intrinsics.areEqual(itemStack, playerEntity.getMainHandStack());
                            }
                            if (!bl2) break block20;
                        }
                        for (RegistryKey registryKey : D.keySet()) {
                            ItemEnchantmentsComponent itemEnchantmentsComponent;
                            Set<RegistryEntry<Enchantment>> set = itemEnchantmentsComponent.getEnchantments();
                            if (n != 0) {
                                bl = set.contains(Globals.getWorld().getRegistryManager().getOptional(registryKey.getRegistryRef()).get().getEntry(registryKey.getValue()).get());
                                if (n == 0) continue block1;
                                if (!bl) continue;
                                set = D.get(registryKey);
                            }
                            String string = (String)((Object)set);
                            int n5 = itemEnchantmentsComponent.getLevel(Globals.getWorld().getRegistryManager().getOptional(registryKey.getRegistryRef()).get().getEntry(registryKey.getValue()).get());
                            String string2 = string + n5;
                            FontRenderer fontRenderer = Fonts.getSF_M();
                            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                            Object var25_21 = null;
                            boolean bl3 = false;
                            Color color = new Color(-1);
                            FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, string2, (float)d - 50.0f + f2, (float)d2 - 45.0f + f3, l2, color, bl3, var25_21);
                            f3 -= 8.0f;
                            float f6 = f;
                            if (n != 0) {
                                if (!(f6 > f3)) continue;
                                f6 = f = f3;
                            }
                            if (n != 0) continue;
                        }
                    }
                    f2 += 18.0f;
                    if (n != 0) continue block0;
                }
                break;
            }
        }
        return f;
    }

    private final String getNameText(long l, PlayerEntity playerEntity) {
        Object object;
        long l2 = (l = g ^ l) ^ 0x3B89E8C4AB26L;
        String string = playerEntity.getGameProfile().name();
        Intrinsics.checkNotNullExpressionValue(string, "name(...)");
        Object object2 = object = CatleanUserTracker.INSTANCE.isCatleanUser(string) ? Formatting.GRAY + "\u01ac " + Formatting.RESET : "";
        if (this.getPing()) {
            object = (String)object + this.getPopsText(this.getPing(playerEntity), l2) + this.getPing(playerEntity) + "ms " + Formatting.WHITE;
        }
        if (this.getGamemode()) {
            object = (String)object + this.getGameModeText(this.getGameMode(playerEntity)) + " ";
        }
        String string2 = switch (NameTags$WhenMappings.s[this.getNameMode().ordinal()]) {
            case 1 -> {
                Text v2 = playerEntity.getDisplayName();
                Intrinsics.checkNotNullExpressionValue(v2, "getDisplayName(...)");
                yield TextUtil.getToLegacy(v2);
            }
            case 2 -> playerEntity.getDisplayName().getString();
            case 3 -> playerEntity.getGameProfile().name();
            default -> throw new NoWhenBranchMatchedException();
        };
        Intrinsics.checkNotNull(string2);
        Object[] objectArray = (Object[])string2;
        Object object3 = object;
        Object[] objectArray2 = objectArray;
        Collection collection = new ArrayList(objectArray.length());
        for (int i = 0; i < objectArray2.length(); ++i) {
            char c;
            char c2 = c = objectArray2.charAt(i);
            Collection collection2 = collection;
            Character c3 = (Character)f.get(Character.valueOf(c2));
            collection2.add(Character.valueOf(c3 != null ? c3.charValue() : c2));
        }
        object = (String)object3 + CollectionsKt.joinToString$default((List)collection, "", null, null, 0, null, null, 62, null) + " ";
        if (this.getHp() && this.getHealth() == NameTagsHealthMode.Number) {
            object = (String)object + this.getHealthText(this.getDisplayName(playerEntity, this.getHealthProvider())) + this.formatNumber(this.getDisplayName(playerEntity, this.getHealthProvider())) + " " + Formatting.WHITE;
        }
        if (this.getDistance()) {
            objectArray = new Object[]{Float.valueOf(Globals.getPlayer().distanceTo(playerEntity))};
            object = (String)object + String.format("%.1f", objectArray) + "m ";
        }
        if (this.getPops() && TargetUtil.INSTANCE.getPops(playerEntity) != 0) {
            object = (String)object + Formatting.RESET + TargetUtil.INSTANCE.getPops(playerEntity);
        }
        return object;
    }

    private final void renderPlayerTags(long l, DrawContext drawContext) {
        long l2 = l = g ^ l;
        long l3 = l2 ^ 0x2164F414C1BL;
        long l4 = l2 ^ 0x16EF706B09CDL;
        for (BlockEntity blockEntity : ServerInfo.INSTANCE.V()) {
            Vector4d vector4d;
            if (!(blockEntity instanceof MobSpawnerBlockEntity) || (vector4d = RenderUtil.INSTANCE.worldToScreen((double)((MobSpawnerBlockEntity)blockEntity).getPos().getX() + 0.5, (double)((MobSpawnerBlockEntity)blockEntity).getPos().getY() + 1.5, (double)((MobSpawnerBlockEntity)blockEntity).getPos().getZ() + 0.5)) == null || ((MobSpawnerBlockEntity)blockEntity).getLogic() == null || ((MobSpawnerBlockEntity)blockEntity).getLogic().getRenderedEntity(Globals.getWorld(), ((MobSpawnerBlockEntity)blockEntity).getPos()) == null) continue;
            Entity entity = ((MobSpawnerBlockEntity)blockEntity).getLogic().getRenderedEntity(Globals.getWorld(), ((MobSpawnerBlockEntity)blockEntity).getPos());
            Intrinsics.checkNotNull(entity);
            String string = entity.getName().getString();
            String string2 = "%.1f";
            Object[] objectArray = new Object[]{Float.valueOf((float)((MobSpawnerBlockEntity)blockEntity).getLogic().spawnDelay / 20.0f)};
            String string3 = String.format(string2, Arrays.copyOf(objectArray, objectArray.length));
            Intrinsics.checkNotNullExpressionValue(string3, "format(...)");
            String string4 = string + " " + string3 + "s";
            if (((MobSpawnerBlockEntity)blockEntity).getLogic().getRotation() == ((MobSpawnerBlockEntity)blockEntity).getLogic().getLastRotation() && ((MobSpawnerBlockEntity)blockEntity).getLogic().getRotation() == 0.0 && (float)((MobSpawnerBlockEntity)blockEntity).getLogic().spawnDelay / 20.0f == 1.0f) {
                Entity entity2 = ((MobSpawnerBlockEntity)blockEntity).getLogic().getRenderedEntity(Globals.getWorld(), ((MobSpawnerBlockEntity)blockEntity).getPos());
                Intrinsics.checkNotNull(entity2);
                string4 = entity2.getName().getString() + " loot!";
            }
            float f = Fonts.getSF_B2().getWidth(l3, string4);
            float f2 = (float)(vector4d.x + (double)((float)(vector4d.z - vector4d.x) / 2.0f) - (double)(f / 2.0f));
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Render2D.drawRoundedRect$default(render2D, matrix3x2fStack, f2 - 2.0f, (float)(vector4d.y - 13.0), f + 4.0f, 11.0f, 0.0f, null);
            FontRenderer fontRenderer = Fonts.getSF_B2();
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            Object var17_6 = null;
            boolean bl = false;
            Color color = new Color(-1);
            FontRenderer.drawString$default(fontRenderer, matrix3x2fStack2, string4, f2, (float)vector4d.y - 12.5f, l4, color, bl, var17_6);
        }
    }

    private final void renderEntityTags(long l, DrawContext drawContext) {
        long l2 = l = g ^ l;
        long l3 = l2 ^ 0x39E1ECC588E6L;
        long l4 = l2 ^ 0x2D18D3EFCD30L;
        Iterator<Entity> iterator2 = Globals.getWorld().getEntities().iterator();
        int n = NameTagsSeeCatleanUsersMode.zkmIntConstant();
        while (iterator2.hasNext()) {
            Vector4d vector4d;
            Entity entity;
            String string;
            block8: {
                block12: {
                    LivingEntity livingEntity;
                    block14: {
                        block13: {
                            boolean bl;
                            block11: {
                                block9: {
                                    block10: {
                                        block5: {
                                            block6: {
                                                Entity entity2;
                                                block7: {
                                                    Intrinsics.checkNotNullExpressionValue(iterator2.next(), "next(...)");
                                                    string = "";
                                                    bl = entity instanceof ProjectileEntity;
                                                    if (n == 0) break block5;
                                                    if (!bl) break block6;
                                                    entity2 = ((ProjectileEntity)entity).getOwner();
                                                    if (n == 0) break block7;
                                                    if (entity2 == null) break block8;
                                                    Entity entity3 = ((ProjectileEntity)entity).getOwner();
                                                    entity2 = entity3;
                                                    Intrinsics.checkNotNull(entity3);
                                                }
                                                Text text = entity2.getDisplayName();
                                                Intrinsics.checkNotNull(text);
                                                String string2 = text.getString();
                                                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                                                string = string2;
                                                if (n != 0) break block8;
                                            }
                                            bl = entity instanceof HorseEntity;
                                        }
                                        if (n == 0) break block9;
                                        if (!bl) break block10;
                                        LivingEntity livingEntity2 = ((HorseEntity)entity).getOwner();
                                        if ((livingEntity2 != null ? livingEntity2.getUuid() : null) == null) break block8;
                                        LivingEntity livingEntity3 = ((HorseEntity)entity).getOwner();
                                        Intrinsics.checkNotNull(livingEntity3);
                                        String string3 = livingEntity3.getUuid().toString();
                                        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
                                        string = string3;
                                        if (n != 0) break block8;
                                    }
                                    bl = entity instanceof TameableEntity;
                                }
                                if (n == 0) break block11;
                                if (!bl) break block12;
                                livingEntity = (TameableEntity)entity;
                                if (n == 0) break block13;
                                bl = ((TameableEntity)livingEntity).isTamed();
                            }
                            if (!bl) break block12;
                            livingEntity = ((TameableEntity)entity).getOwner();
                        }
                        if (n == 0) break block14;
                        if (livingEntity == null) break block12;
                        LivingEntity livingEntity4 = ((TameableEntity)entity).getOwner();
                        livingEntity = livingEntity4;
                        Intrinsics.checkNotNull(livingEntity4);
                    }
                    Text text = livingEntity.getDisplayName();
                    Intrinsics.checkNotNull(text);
                    String string4 = text.getString();
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                    string = string4;
                    if (n != 0) break block8;
                }
                if (n != 0) continue;
            }
            int n2 = ((CharSequence)string).length();
            if (n != 0) {
                n2 = n2 == 0 ? 1 : 0;
            }
            if (n2 != 0 && n != 0 || (vector4d = RenderUtil.worldToScreen$default(RenderUtil.INSTANCE, entity, 0.0f, null)) == null) continue;
            String string5 = Formatting.GRAY + "by " + Formatting.RESET + " " + string;
            float f = Fonts.getSF_B2().getWidth(l3, string5);
            float f2 = (float)((vector4d.x + (double)((float)(vector4d.z - vector4d.x) / 2.0f) - (double)(f / 2.0f)) * 1.0);
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Render2D.drawRoundedRect$default(render2D, matrix3x2fStack, f2 - 2.0f, (float)(vector4d.y - 13.0), f + 4.0f, 12.0f, 0.0f, null);
            FontRenderer fontRenderer = Fonts.getSF_B2();
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            Object var16_7 = null;
            boolean bl = false;
            Color color = new Color(-1);
            FontRenderer.drawString$default(fontRenderer, matrix3x2fStack2, string5, f2, (float)vector4d.y - 12.0f, l4, color, bl, var16_7);
            if (n != 0) continue;
        }
    }

    public final int getPing(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "entity");
        Object object = Globals.getMc().getNetworkHandler();
        if (object == null || (object = ((ClientPlayNetworkHandler)object).getPlayerListEntry(playerEntity.getUuid())) == null) {
            return 0;
        }
        return ((PlayerListEntry)object).getLatency();
    }

    private final GameMode getGameMode(PlayerEntity playerEntity) {
        if (playerEntity == null) {
            return null;
        }
        ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
        Intrinsics.checkNotNull(clientPlayNetworkHandler);
        PlayerListEntry playerListEntry = clientPlayNetworkHandler.getPlayerListEntry(playerEntity.getUuid());
        return playerListEntry != null ? playerListEntry.getGameMode() : null;
    }

    private final String getGameModeText(GameMode gameMode) {
        if (gameMode == null) {
            return "[BOT]";
        }
        GameMode gameMode2 = gameMode;
        return switch (NameTags$WhenMappings.R[gameMode2.ordinal()]) {
            case 1 -> "[S]";
            case 2 -> "[C]";
            case 3 -> "[SP]";
            case 4 -> "[A]";
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    public final float getDisplayName(@NotNull PlayerEntity playerEntity, @NotNull NameSource nameSource) {
        Intrinsics.checkNotNullParameter(playerEntity, "ent");
        Intrinsics.checkNotNullParameter((Object)nameSource, "healthProvider");
        switch (NameTags$WhenMappings.m[nameSource.ordinal()]) {
            case 1: {
                ScoreboardObjective scoreboardObjective;
                String string = "";
                float f = 0.0f;
                Scoreboard scoreboard = playerEntity.getEntityWorld().getScoreboard();
                Intrinsics.checkNotNullExpressionValue(scoreboard, "getScoreboard(...)");
                Scoreboard scoreboard2 = scoreboard;
                ScoreboardObjective scoreboardObjective2 = scoreboardObjective = scoreboard2.getObjectiveForSlot(ScoreboardDisplaySlot.BELOW_NAME);
                if (scoreboardObjective2 != null) {
                    ScoreboardObjective scoreboardObjective3;
                    ScoreboardObjective scoreboardObjective4 = scoreboardObjective3 = scoreboardObjective2;
                    String string2 = ReadableScoreboardScore.getFormattedScore(scoreboard2.getScore(playerEntity, scoreboardObjective4), scoreboardObjective4.getNumberFormatOr(StyledNumberFormat.EMPTY)).getString();
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    string = string2;
                }
                try {
                    f = Float.parseFloat(string);
                }
                catch (NumberFormatException numberFormatException) {}
                return f;
            }
            case 2: {
                ScoreboardObjective scoreboardObjective;
                ScoreboardObjective scoreboardObjective5 = scoreboardObjective = Globals.getWorld().getScoreboard().getObjectiveForSlot(ScoreboardDisplaySlot.LIST);
                if ((scoreboardObjective5 != null ? scoreboardObjective5.getRenderType() : null) == ScoreboardCriterion.RenderType.HEARTS) {
                    Integer n;
                    ReadableScoreboardScore readableScoreboardScore = Globals.getWorld().getScoreboard().getScore(ScoreHolder.fromProfile(playerEntity.getGameProfile()), scoreboardObjective);
                    Integer n2 = n = readableScoreboardScore != null ? Integer.valueOf(readableScoreboardScore.getScore()) : null;
                    if (n != null) {
                        return n.intValue();
                    }
                }
                return 0.0f;
            }
        }
        return playerEntity.getHealth() + playerEntity.getAbsorptionAmount();
    }

    private final void drawEquipment(long l, double d, double d2, DrawContext drawContext, PlayerEntity playerEntity, float f, int n, int n2) {
        block11: {
            long l2 = (l = g ^ l) ^ 0x2C00B8D0F16EL;
            int n3 = (int)(l2 >>> 32);
            long l3 = l2 << 32 >>> 32;
            drawContext.getMatrices().pushMatrix();
            drawContext.getMatrices().translate((float)d - 44.0f, (float)d2 + 1.0f);
            drawContext.getMatrices().scale(1.1f, 1.1f);
            int n4 = MathHelper.ceil((double)f / 2.0);
            int n5 = MathHelper.ceil((double)n2 / 2.0);
            int n6 = NameTagsSeeCatleanUsersMode.zkmIntConstant();
            int n7 = n4 * 2;
            int n8 = 0;
            int n9 = n4 + n5 - 1;
            while (-1 < n9) {
                block14: {
                    block13: {
                        DrawContext drawContext2;
                        NameTagItemType nameTagItemType;
                        int n10;
                        int n11;
                        PlayerEntity playerEntity2;
                        int n12;
                        int n13;
                        int n14;
                        int n15;
                        block12: {
                            int n16 = n9 % 10;
                            n15 = n16 * 8;
                            if (n6 == 0) break block11;
                            int n17 = n8++;
                            int n18 = 10;
                            if (n6 != 0) {
                                if (n17 < n18) {
                                    this.drawItem(n3, l3, drawContext, NameTagItemType.CONTAINER, n15, false, playerEntity);
                                }
                                n17 = n9;
                                n18 = 2;
                            }
                            n13 = n14 = n17 * n18;
                            n12 = n;
                            if (n6 != 0) {
                                if (n13 < n12) {
                                    int n19 = n14 + 1;
                                    if (n6 != 0) {
                                        n19 = n19 == n ? 1 : 0;
                                    }
                                    playerEntity2 = playerEntity;
                                    n11 = n19;
                                    n10 = n15;
                                    nameTagItemType = NameTagItemType.NORMAL;
                                    drawContext2 = drawContext;
                                    this.drawItem(n3, l3, drawContext2, nameTagItemType, n10, n11 != 0, playerEntity2);
                                }
                                n13 = n9;
                                n12 = n4;
                            }
                            if (n6 == 0) break block12;
                            if (n13 < n12) break block13;
                            n13 = n14;
                            n12 = n7;
                        }
                        int n20 = n13 - n12;
                        if (n6 == 0) break block14;
                        if (n14 - n7 < n2) {
                            drawContext.getMatrices().pushMatrix();
                            int n21 = n20 + 1;
                            if (n6 != 0) {
                                n21 = n21 == n2 ? 1 : 0;
                            }
                            playerEntity2 = playerEntity;
                            n11 = n21;
                            n10 = n15;
                            nameTagItemType = NameTagItemType.ABSORBING;
                            drawContext2 = drawContext;
                            this.drawItem(n3, l3, drawContext2, nameTagItemType, n10, n11 != 0, playerEntity2);
                            drawContext.getMatrices().popMatrix();
                        }
                    }
                    --n9;
                }
                if (n6 != 0) continue;
            }
            drawContext.getMatrices().popMatrix();
        }
    }

    private final void drawItem(int n, long l, DrawContext drawContext, NameTagItemType nameTagItemType, int n2, boolean bl, PlayerEntity playerEntity) {
        block5: {
            block2: {
                Color[] colorArray;
                int n3;
                long l2;
                block6: {
                    NameTagItemType nameTagItemType2;
                    NameTagItemType nameTagItemType3;
                    Color color;
                    block3: {
                        block4: {
                            long l3 = ((long)n << 32 | l << 32 >>> 32) ^ g;
                            l2 = l3 ^ 0xCD25923499EL;
                            n3 = NameTagsSeeCatleanUsersMode.zkmIntConstant();
                            if (this.getHealth() != NameTagsHealthMode.Dots) break block2;
                            color = Friends.getIsHomie(playerEntity) ? this.getFillColorF() : this.getFillColorA();
                            nameTagItemType3 = nameTagItemType;
                            nameTagItemType2 = NameTagItemType.CONTAINER;
                            if (n3 == 0) break block3;
                            if (nameTagItemType3 != nameTagItemType2) break block4;
                            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                            colorArray = new Color[]{color};
                            RenderUtil.INSTANCE.drawGradient(matrix3x2fStack, n2, 0.0f, 7.0f, 3.0f, colorArray, l2);
                            if (n3 != 0) break block5;
                        }
                        nameTagItemType3 = nameTagItemType;
                        nameTagItemType2 = NameTagItemType.NORMAL;
                    }
                    if (nameTagItemType3 != nameTagItemType2) break block5;
                    if (!bl) break block6;
                    Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                    colorArray = new Color[]{this.getHealthColor(playerEntity.getHealth() + playerEntity.getAbsorptionAmount())};
                    RenderUtil.INSTANCE.drawGradient(matrix3x2fStack, n2, 0.0f, 3.0f, 3.0f, colorArray, l2);
                    Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
                    Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
                    colorArray = new Color[]{color};
                    RenderUtil.INSTANCE.drawGradient(matrix3x2fStack2, (float)n2 + 3.0f, 0.0f, 4.0f, 3.0f, colorArray, l2);
                    if (n3 != 0) break block5;
                }
                Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
                Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
                colorArray = new Color[]{this.getHealthColor(playerEntity.getHealth() + playerEntity.getAbsorptionAmount())};
                RenderUtil.INSTANCE.drawGradient(matrix3x2fStack, n2, 0.0f, 7.0f, 3.0f, colorArray, l2);
                if (n3 != 0) break block5;
            }
            drawContext.drawGuiTexture(RenderPipelines.GUI_TEXTURED, nameTagItemType.Z(bl), n2, 0, 9, 9);
        }
    }

    /*
     * Unable to fully structure code
     */
    private final String getHealthText(float var1_1) {
        var2_2 = NameTagsSeeCatleanUsersMode.zkmIntConstant();
        cfr_temp_0 = var1_1 - 15.0f;
        v0 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
        if (var2_2 != 0) {
            if (v0 <= 0) {
                cfr_temp_1 = var1_1 - 7.0f;
                v0 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1);
                if (var2_2 != 0) {
                    if (v0 > 0) {
                        return "" + Formatting.YELLOW;
                    } else {
                        ** GOTO lbl-1000
                    }
                }
            } else lbl-1000:
            // 3 sources

            {
                v0 = (cfr_temp_2 = var1_1 - 15.0f) == 0.0f ? 0 : (cfr_temp_2 > 0.0f ? 1 : -1);
            }
        }
        if (v0 > 0) {
            return "" + Formatting.GREEN;
        }
        return "" + Formatting.RED;
    }

    private final String getPopsText(int n, long l) {
        long l2 = (l = g ^ l) ^ 0x4CBA45AD2703L;
        long l3 = l2 >>> 16;
        int n2 = (int)(l2 << 48 >>> 48);
        if (n > ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n2, null)) {
            return "" + Formatting.GREEN;
        }
        if (n <= ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n2, null) && Math.abs(n - ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n2, null)) < 15) {
            return "" + Formatting.YELLOW;
        }
        return "" + Formatting.RED;
    }

    /*
     * Unable to fully structure code
     */
    private final Color getHealthColor(float var1_1) {
        var2_2 = NameTagsSeeCatleanUsersMode.zkmIntConstant();
        cfr_temp_0 = var1_1 - 15.0f;
        v0 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
        if (var2_2 != 0) {
            if (v0 <= 0) {
                cfr_temp_1 = var1_1 - 7.0f;
                v0 = cfr_temp_1 == 0.0f ? 0 : (cfr_temp_1 > 0.0f ? 1 : -1);
                if (var2_2 != 0) {
                    if (v0 > 0) {
                        v1 = Color.YELLOW;
                        Intrinsics.checkNotNullExpressionValue(v1, "YELLOW");
                        return v1;
                    } else {
                        ** GOTO lbl-1000
                    }
                }
            } else lbl-1000:
            // 3 sources

            {
                v0 = (cfr_temp_2 = var1_1 - 15.0f) == 0.0f ? 0 : (cfr_temp_2 > 0.0f ? 1 : -1);
            }
        }
        if (v0 > 0) {
            v2 = Color.GREEN;
            Intrinsics.checkNotNullExpressionValue(v2, "GREEN");
            return v2;
        }
        v3 = Color.RED;
        Intrinsics.checkNotNullExpressionValue(v3, "RED");
        return v3;
    }

    public final float formatNumber(float f) {
        if (Float.isNaN(f) || Float.isInfinite(f)) {
            return 1.0f;
        }
        return new BigDecimal(f).setScale(1, RoundingMode.HALF_UP).floatValue();
    }

    private final float drawPotions(long l, DrawContext drawContext, float f, float f2, PlayerEntity playerEntity) {
        long l2 = l = g ^ l;
        long l3 = l2 ^ 0x51B649F96D6CL;
        long l4 = l2 ^ 0x454F76D328BAL;
        float f3 = f;
        ArrayList<StatusEffectInstance> arrayList = new ArrayList<StatusEffectInstance>(playerEntity.getStatusEffects());
        if (arrayList.isEmpty()) {
            return 0.0f;
        }
        f3 += (float)arrayList.size() * 12.5f;
        float f4 = 0.0f;
        for (StatusEffectInstance statusEffectInstance : Ordering.natural().reverse().sortedCopy((Iterable)arrayList)) {
            f3 -= 25.0f;
            String string = "";
            switch (statusEffectInstance.getAmplifier()) {
                case 0: {
                    string = "I";
                    break;
                }
                case 1: {
                    string = "II";
                    break;
                }
                case 2: {
                    string = "III";
                    break;
                }
                case 3: {
                    string = "IV";
                    break;
                }
                case 4: {
                    string = "V";
                }
            }
            drawContext.getMatrices().pushMatrix();
            drawContext.getMatrices().translate(f3, f2);
            drawContext.drawGuiTexture(RenderPipelines.GUI_TEXTURED, InGameHud.getEffectTexture(statusEffectInstance.getEffectType()), 0, 0, 18, 18, -1);
            Intrinsics.checkNotNull(statusEffectInstance);
            String string2 = this.getPotionText(statusEffectInstance);
            FontRenderer fontRenderer = Fonts.getSF_M();
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Object var16_12 = null;
            boolean bl = false;
            Color color = new Color(-1);
            FontRenderer.drawString$default(fontRenderer, matrix3x2fStack, string2, 9.0f - Fonts.getSF_M().getWidth(l3, string2) / 2.0f, -8.0f, l4, color, bl, var16_12);
            FontRenderer fontRenderer2 = Fonts.getSF_M();
            Matrix3x2fStack matrix3x2fStack2 = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack2, "pose(...)");
            var16_12 = null;
            bl = false;
            color = new Color(-1);
            FontRenderer.drawString$default(fontRenderer2, matrix3x2fStack2, string, 9.0f - Fonts.getSF_M().getWidth(l3, string) / 2.0f, -16.0f, l4, color, bl, var16_12);
            drawContext.getMatrices().popMatrix();
            f4 = 40.0f;
        }
        return f4;
    }

    private final void drawShulkerPreview(DrawContext drawContext, ItemStack itemStack) {
        int n;
        ContainerComponent containerComponent;
        int n2;
        block14: {
            block15: {
                block13: {
                    block12: {
                        ContainerComponent containerComponent2;
                        block11: {
                            block10: {
                                n2 = NameTagsSeeCatleanUsersMode.zkmIntConstant();
                                if (n2 == 0) break block10;
                                containerComponent2 = itemStack.get(DataComponentTypes.CONTAINER);
                                if (containerComponent2 != null) break block11;
                            }
                            return;
                        }
                        containerComponent = containerComponent2;
                        Item item = itemStack.getItem();
                        Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
                        Item item2 = item;
                        n = item2 instanceof BlockItem;
                        if (n2 == 0) break block12;
                        if (n == 0) break block13;
                        n = ((BlockItem)item2).getBlock() instanceof ShulkerBoxBlock;
                    }
                    if (n2 == 0) break block14;
                    if (n != 0) break block15;
                }
                return;
            }
            n = containerComponent.stream().toList().isEmpty();
        }
        if (n2 != 0) {
            if (n != 0) {
                return;
            }
            Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Render2D.drawRoundedRect$default(render2D, matrix3x2fStack, 12.0f, -79.0f, 169.0f, 60.0f, 0.0f, null);
            n = 0;
        }
        int n3 = n;
        int n4 = 0;
        for (ItemStack itemStack2 : containerComponent.stream().toList()) {
            drawContext.drawItem(itemStack2, 16 + n4 * 18, -75 + n3 * 18);
            drawContext.drawStackOverlay(Globals.getMc().textRenderer, itemStack2, 16 + n4 * 18, -75 + n3 * 18);
            int n5 = ++n4;
            if (n2 != 0) {
                if (n5 < 9) continue;
                n5 = 0;
            }
            n4 = n5;
            ++n3;
            if (n2 != 0) continue;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @NotNull
    public final String getPotionText(@NotNull StatusEffectInstance statusEffectInstance) {
        CallSite callSite;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ g;
        CallSite callSite2 = ZkmReferences.zkmBootstrap("\u00c5", (long)1920862302914142924L, (long)l);
        Intrinsics.checkNotNullParameter(statusEffectInstance, (String)((Object)NameTags.zkmBootstrap("t", (int)9686, (long)(0x5FBE70F90510B84L ^ l))));
        CallSite callSite3 = callSite2;
        int n4 = statusEffectInstance.isInfinite();
        if (callSite3 == false) {
            if (n4 != 0) {
                callSite = NameTags.zkmBootstrap("t", (int)1493, (long)(0xE53AA72C842BE7L ^ l));
                return callSite;
            }
            n4 = statusEffectInstance.getDuration() / NameTags.zkmBootstrap2("u", (int)7004, (long)(0x432DDD648A969D6FL ^ l));
        }
        CallSite callSite4 = NameTags.zkmBootstrap("t", (int)32164, (long)(0x5B82A3811566539CL ^ l));
        Object[] objectArray = new Object[]{statusEffectInstance.getDuration() % NameTags.zkmBootstrap2("u", (int)15382, (long)(0x37EE824732793A2EL ^ l)) / NameTags.zkmBootstrap2("u", (int)17082, (long)(0x1DD1B271D6744F2L ^ l))};
        String string = String.format((String)((Object)callSite4), Arrays.copyOf(objectArray, objectArray.length));
        Intrinsics.checkNotNullExpressionValue(string, (String)((Object)NameTags.zkmBootstrap("t", (int)25879, (long)(0x8DC5C4DFE1E4B58L ^ l))));
        callSite = n4 + ":" + string;
        return callSite;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getEnchantss();
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
                            NameTags.g = ZkmKeys.a(-3695841417763776280L, 4059309004257792429L, MethodHandles.lookup().lookupClass()).a(90005078074973L);
                            var27 = NameTags.g ^ 91108401090889L;
                            NameTags.w = new HashMap<K, V>(13);
                            NameTags.zkmSetFlag(new int[3]);
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var27 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var27 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[112];
                            var23_4 = 0;
                            var22_5 = "\u0012\u00aa\u00b3n\u0017\u00c8N+\u0011`;\n\u00b3\u0005`\u0088\u00df\u00a0\u001f\u0015\u00ad\u001c\u0012X.\u00ac\u00d4.m]\u00d2\u009e\u0010\u00ef>\u00c6\u00fc\u00a4\r\u00164U-\u0001\u00e1\u001f\u0002\u00ff>0\u00aa\u00fe\u0096k\u0006\u00c3\u00ee\u00dc\u0098\u00daV(^\u0083\u00ec\u001dhG5\u00dbh\u00ddb\u00d3\u00d34u_\u0004\u00af\u00cdk9\u00b6\u00c2\u009a\r\u00f2\u009e \\(\f\u00903\u000bc) >\u00a7(\u0092'\u008b\u0085\u00e7\u0006\u009d\u00ccd\u00e2\u00b3\u00f5\u00f1e\u00bc\u0003\u0082B\u00a4hQQ\u009f\u00ef\u0017<\u00cbn\u0017 \u008e\u00c0\u00f1\u000f\u00919 p\u001dZ\u00fbf4\u0085l\u00bbY_`\u00bfo\u00f1?C\u001d\u0005\\\u00e1gP\u00bec \u0003\u00be8\u0090\u00c3.m\u000f\u00be\u008eJ\u00a9i\u00a2\u00a7\u00eb\u00b6\\SE\u00cey\u00f4@\u0098\u0088zF\u00dc'\u00b0\u000b {b\u0080\u0082\u00d3R\u00dc\u0090s\u00fa\u0081c\u0095\u0005\u0081'\u008a\u0014\r+?\u00f78\u0004\u00b8\u0093\u009c\u0082\u00daz=\u00e6@\u0013\u00afG\u0013\u00d8\u00fc6\u00163\u0005\u009clJLM\u00c2\u00ff\u00e9\u009a,w,\u0015\u00ee\u00b2\u00e1l@X\u00cd\u00b0\u0088\u0085M@\u00e7]\n\u00a6Mps\u00be\u008c~*\u00d9u\u00ce\u00c8\u0011Mh&\u00ae\u00ed'Fi\u0088X\u008f \u00a5\u0010W\u00ceC\u00b3\u0003\u009e\u00f8\u00d5\\\u0011\u00a3\u00e8\u0088\u00ad\u00fe\u000f \u00bbN\u00c9TJ\u00ff&\u0016\u0094\u00b3U\u00eep\u00f9\n\u0087L=f\u00d1bv\u0090Yz\r\u00ff\u00d1\u00fd\u00c0\u00adB\u0018\u001fDc\u00fb\u00a0g.\u00d2Kz\u0004\u0014\u00a6\u0012{5\u00b2\u00d5\u0093\u00b6\u00d0\u009f\u00e9\u00a1\u0010c\u00f6|\u00d9\u00cb\u00e0\u00d0;\u00ae]t\u00edS\u00e0Dl($\u00c8\u00d8\u00eb\u001c\r\u0012\u00f9\t\u00b8\u00cd:8\u008b\u0087e\u0086\u00c2\u0091k\u00f0Y\b_\u001d0?\u0081\u0005\u0002\u00aaKB>^\u0084,\u008a\u00f7\u001f\u0010\u00bfF\u00ab\u00a92\u0084f=\u00a0\u00c5\u0019\u00a0\u00d2\u00de\u0095\u00ed\u0010\u00c9\u00d2\u00a4K?\u00b3an\u00d2\u001e\u0092\u008e\u0016{iO \u00f8\u00f3\b\u00c38\u00f3\u00b1\u00c9\u00c1\u008a\u008fg\u00c0;n\u001b\u0015\u0010\u00a2\u00ce\b \u001e>\u00c5l\f\u00d5\u0014\u00b4`Q@\u00e5\u00e6+\u0007~\u00cf\u00c3@\u00fa\u00cb\u00ee\u008c\u00cb \u0080'\u00d9\u009f\u0082\u00c3\u00d6\u00a8hS\u0012\u001f\u00faH\u00bc\u00c82\u00ec`A+1/U\u0016\u00b0\u00b2fN;\u00ed\u00d5\u00db2\u00f3\u00c9\u00121\u0087\u001a\u001d5/\u0099\u00fb\u00cf\u0005 \u000f\u00cb\u0018\u00b6\r\u00a78\u00a4\u00f7\u0083\u00a0`b\u00d7\u0001M\\h\u008bT\u0011\u00dc\u00b8^\u0010tj0\u00c2\u0096\u00a3\u0011I_?'L\u00cel\u00db\u0086cG\\03H\u00c0\u00cfc\u00f2\u00fb\u0015\b\u00e2\u00de\u00f7HV,\u00c8%\u00df\f\u00fb\u00b5\u0013\u00f0\u00dd\u0017%\u00b1\u0097\u009cx{\u0018}.\u00f0O`\u00c8\u001bI\u00fa\u00e8\u00a0r\u00cd\u0003)a\u00cd\u00e8\u00cey+4\u0082\u00cd@E\u0098\u00c3?`!cKK9a\u008d\u00a6\u00dd\u00cd\u00f9m+\u0012O\u000f\u00f9.9\u0095\f\u009e\u00dfC0sd\u00b4\u00b5\u0010\u00a0\u001b5\u00cf\u000eZ\u00f5\u00c1\u00ef\u00a2\u00e2P\u0097\u00e0\u00be\u00b8\u0011\u0010B\u009dd\u00d0<\u00c7\u00e0nW\u00f6\u008f8\f\u00c3\u009c\u0019\u00a8\u00c1\u008c\u00ac\u00b4\u0091\u00e6\u00ee\u007f\u0081\u00ce.\u00fa\u0099\u00ea\u00c3\u00ba3\u00b4j\u0013\u009b\u0081\u00ce\u00b3\u00f4N*!\u009e\u0014\u00b9F\u0092\u00ed\u00f1\u009f\u0083\u0010L\u00bc8=\u0011\u00df\u00c3coU\u009d\u00b83\u00107\bY\u009f\u001c*LAf%\f\u00e8\u00efo\u0081\r\u0010\u00a5q\u009e\u0088m\u00d8&\u008dqW\u0000G\u008a\u00b1\u00d9=\u0010\u00b7\u000e\u00a3\u00ec\u00f2\u008a\u00d8\u00ae#u\u0004\u00a2h/\b\u00b3\u0010j\u00ed\n\u00a3\u0099\u009a\f\"8bk\u00dcFV\u0001k\u0010+\u001f\u00dd\u0085U\u00ddd\u001c\u00b9\u00df\u00f18\u00cdm\u00e2\u00a6 N\u0005E\u0007\u00e7A\u0095\u00da`E\u00a8P\u009f\u00ce\u008d\u00c8\u00ec\u0082A\u0007w'H\u0000vd|\u00a3\u00c1\u00b2d\u00b5\u0018a\u0091_k\u00dc\u00ab\u00df\u00f7\u00fa\u00fd<\u00aaI\u00cc?\u00ddy\u00d5A*\u00cc\u009e\u001a\u00aa8d\u0089\u00a0\u00d7^\u00c3\u00d9\u00b8\u0005\\{V\u008cq\u00b4M=\u00c9\u000f\u0015+\u00b5\u00aeMq\u0005\u00f9\u000b\u0019\u0007\u00b9\u00a3\u00a7\u00a0\u00beE\u00977;\u00c01k\u00fd\u00e7\u00b3\u00b9\u001a\u0005\u008b\u00c5\u001b?%:L\u001e\u0018\u0097\u00d3\u008ec\u00ec\u00b8\u00ae\u00ba\u00eaH@\u0081\u00f7\u0082\u00e2\u00db1\u008a.s_\u0099%t\u0010\u0002B^\b( Bw\u00a8\u001e\u00caB\u00d6\u0087\u00ec\u0002\u0010\u00c6\u000e4yM%s\u00cc\u0082Cf\u008bB0\u00d9\u007f X\u00f9\u00a0\u00f4$h\u0088\u00c5\u00ab\u00a5COe\u0002I\u0013\u00fb\u00edACZ\u00fdB\u0000erg$\u0002G\u001c\u0081\u0010E\u00a9\u00de\u0015\u0012\u00a8\u00e7i\u00ff\u00d1X\u009f'\u0001\u00e6\u0096\u0010\u0004\u00cd\u00e5\u0019\u001fG\u0019\u00f6\u00e9\u001d\b\u001b\r\u00a8\u00c8\u0016\u0018\u000f\u0018\u008c\u0091\u00cf\u00ee\u00f2\u001b\u00ed\u008a\u00b9vaR%\u00e8\u009bgf5T\u0090\u00c7\u0090\u0018K+\u0099X\n\u00cbj;C\u00e3\u008b\u00e8\u00c0\u00dcc\u00cd\u0098\u00cfx\u000b\u001f@\u0018\u009e\u0018\u00d5\u00c1\u00c3r\u00baN\u00b4Z\u0017\u00c1c\u00f7up\u00b3\u0096\u00ea\u0098\u00b3\u00a3\u0094\u00a1\u00a7\u00f4\u0010\u00c9\u0011\u00d9\u009b\u001d\u0012\u00d8T\u00c6\u00f7\u00ef*\u00f7z\u00b9\u00b9(!\u00fb\u0016\u008fM\u00f6`\u0085\u0007\u00c2q\u00ca\u0017\u00f4-M\u00c05\u00a8\u008f\u00f5\u00e0\u0002v\u00ce4\u00dd\u00eb\u0087$m\u009e\u00e8N\u00ec\u0094{\u00ed\u00dcz \u0097J\u0002#\u00c7^<w\u0006`\u00be\u00fa\u0097\u00af\u0086\u00bc\u0014\u00ca\u008cn\u00fc\u000f\u00bd.8\u00d8}\u0015\u0010\u009dft\u0018\u00d4\u0080\u00e6\u00f4\u0002\u001f\u001d\u00a2+\u0010\u00ea\u00d6S\u0000\u00b1\u0094\u00a7j\u0002\u00b6 \u00e1z! \u00cc\u0096\u000e\u00f0hj\u00fb\u00d8-S&\u0094\u00dd2\u000b\u0007\u00e0>W\u0084L\u0080D=9\u00df!\u0013\u00cd\u0092r\u0004 \u008b\u00a3\u0087\u008a\u00c1\u00f53=\u00e7\u0096~\u009cx\u00fb>\u009f\u00ef\u0012\u00ed\u0089\u008d\u00f4\u008b\u00c17-\u00129\u00cf\u00fe\u00ec\u00b9\u0010?Ir<\u00dd\u0086\u00ca\u00c1<2\u00e4\u00aa\r\u00bcc\u00c6\u0010\u0091\u00b1v\u00c8\u008f\u00b7v\u00da\u0089\u00f3\u0018\u0095Aqjf\u0010\u009e\u00a7b\u0012\u008c@\u00f5\u00c9\u00b3A\u00d9\u00e0\u0095\u00f3b\u0084\u0010 E\u00fc\u00ff\u00ef\u008a$\u00f3\u00a3t\u00c5\u0004\u00b2<\u00f2\u008d S\u00f6\u00b3\u00c2p;\u00e0\u00b8<\u0007E\u0010Y\u0015jQ\u0018\u00f1:\t\u009b8L\u00b1\u00f5\u00f8VK\u00dd~\u00101 \u00af\u00b6\u00fd\u00ba\u00c0\u0004\u00b8\u0007X\u00fb\u0003\u00f8 l\u0093F$\u00df\u009b+\u00ff\u00b4w\t.\u00d2LfR\u00db\t' \u00f9\u009d\u00c7g\u00faf:i\b\u00b2\u0093\u0097j\u00e7|\u00de\u00ca\u0098c\u0019lL\u0007\u00bb\u00ff\u00ac\u009d\u0013B\u00fb\u00ed\u001b\u0010d\u001b9<\u0097\u00c8\u00d7\tI\u00a4\u00eec\u0099\u00ddO\u009d([%\u00b5hM\u00c6\u00efj|Q\u009a\u0003\u0001wH\u00dfO\t\u00dd\u00b0\u00bd\u0005\u00d5\u00e3w\u00a3\u00d4U\u001d\nW\u00b5\u00f5\u00db\u0017No\u001c\u00ee\u00c28ZA\f\u0088\u008f\u00b2=\u00d0\u0084\u00f5`\u00cd2F\u001d\u0019\t/\u00f9\u00c0n\u007f\u008d\u0082\u00b2\u00b2\u0099w\u008f\r\u00ea\u00b4\u0013\u001c\u0095\u0099TF\u00e9\u00d1X\u008c\u00fb\u00b3\u00de\u00e0d:hu\u0007\u00ba\u00ad\u007f\u00f16(\u00dd\u00c7b2\u00fd\u00cf\u00ffj\u00b1\rwR\f\u008d\u0096\u00ed\u0086\u00b6\u00feU<\u00ca\u0099n\u00ad\t\u00d9\u00f38\u00e3\u00bdV\u00dc\u00a2*l#\u00e6\u0002\u009e\u0018E\u00f2N\b\u00bbU\u00f60\u0084\u0004e\u0019\u008a\u008bI\u0000L\u00bf\u00ba5\u00ef\u00cc\u00bb\u00cb\u0018\u000b\u00c0\u00cd\u009c\u0018\u00d8\u0095z(\u0083k\u0018\u008c\u009f\u00f8\u00b8\u00fa\u0087\u00a5\u00d7\u00b0\u0017\u00ecs(\u00c9\rz\u00c3\u001e\u00e4\u0004\u001ebcQb\u00fd\u00c6vx\u00fb\u00ff\u008a?Y\u00d7\u00df\u00e4Q\u0000\u00cc\u00b2\u00d4\u00af\u0019\u00e4\u000b^\u00fe\u00f4Z\u0006PP\u0010\u00eb]@\u00d5,\u00c32C\u00ec0,\u00e0\u0086\u00b5\u009b\u00ff\u0010%X\u00b4\u00d0\u0097\u00e6\u00a8\u0086\u00abca\u00f5G=yZ \u00b6<\u00c9%F\u00b9\u0014\u00c3\u00db\u0095\u00fc\u00f3]\u00b6\u00b39\u000b4\u0092\u00ce\u00ae\u00145\u00e2\u00b4\u0005\u00eb\u0001f\u00faKf \f\u0097\u008a\u009e!\u00dec\u00ba\u00af\u00d7+y\u00b7\u0019\u008d\u0019\u0011\u00e7e\u00b2 \u00ec\u009c\u00e2\u00ed\u008c\u009d\u00e5,\u00e9\u00d5^8\u00c5\u00a1\u0006P\u0087\u0081\u00c86\u00ac\u0018\u0091 \u0017^'%\u00eb'\u00f3\u00b1e\u0098\bFC\u00ed\u00b7\u00cdK0\u00e3\u00ab\"\u00a050\u00d2\u00b6P\u00a3\u00a0Y#*\u00ba\u00c0\u0083\u00c5\u009f\u00ba\fh(\u00ab \u00c8\u0010-Z \u00d5[\u0096UQ\u009a\u00dd\u009c\u0088\u0093\t\u00a1j\u0018]\u0019\u00f8\u008fErTw\u00c9\n_&\u00de\u00ec\u00ff\u00e7uK\u00c9\u00b1k\u00edv\u00ee y\u00a3\u00a7\u0017\u00e7\u00d7\u00c1\u00a2[pr\u001d\u0096\t\u00fc\u0099\u00e2\u00b0\u00c7,K\u00b4\u00df\u00cc\u0089HM\u00e0\u00fc\u00e7\u00d3\u00c7\u0010\\\u0088)\u0085\u00d0\u001dF\u009c`\u0017\u0097\u00d6\tY`\u00d9\u0010+\u0084H\u00a4\u0007@H\u009ewG8\u0000\u00a2\u00e4B\u00af\u0010\u00b8\u0004I}\u001b\u00e5\u00f6\u009a\u0006\u007f\u00bfL\t\u00f5\u00c4K \u00c1\u008bD\u00ff\u000e\u0012\u00fa\u00dd/\u00a7\u00e2\u001a/}\u0010\u00e39\u00c4\u00c0\u009d \u00e2\u00e6l\n\u00133\b\u00d5i\u00b3\u00ad \u0015|*(\u00cf\u00c6p\u00d1\u0082\u00a4r\u00b6\u00ab\u00ad}\u0014sg\u00e1\u00f2p\u00ed\u0089\u00a5O<Q\u00a7\u009b\u00e8Z\u00aa\u0018x\u00f1\u00f8:\u00c2;\u009c\u008ds\u0005\u0080GP\u00e7\u00e4gzt\u0085\u0004\u00b1,\u0006\u0085\u0010\u00ec#\u00aa\u00d8\u00a6\u008c\u00b1?Zm;#\b\u00fd!P\u0010P9\u00c3d\u00d8 \u00e6\u00a8\u0098\u00f2\\\u00109\u00bcSV\u0010\u00f4\u00ad\u00cbt\u00d1M\u00aa\u00f8\u008a]\u0094hE\f\u00fe\u00f6 \f\u00c2\u0006`\u001f\u00a6\u001fND\u0014}\u00189{\u0088b\u0012b\u0085\u00e8\u0092\u00abw\\\u00d2`\u00ca\u00e3\u001e\u009e)^ *\ba\u009b\u00ad/r\u00b9\u0087\u00d2*i~KI\u00d8\u00b4b\u0015\u00e9\u00cf0\"\u001a\u00d8=v,7\u00a8\u00bb\u00db\u0018A\u00f8\u00f1]\u0094A\u0007er\u00de\u00d3M\u00cc/\u008e\u00b1\u00ed\u008e\u0099a\u00c3\u00e9/\u00bc\u0010\u0016~\u00c4F$D\u00ee/8i:~\u001a\u0002(9 p0Z\u00bfB\u0096)\u00c2\u00f7a.\u00fbNJn\u0000\u00ca\u009dK\u001c\u00eb\u008cy\u00e8\u00aeS\u007f\u00a4\u007fj\u0095\u00198/[\u00fc}\u0018 \u00bb\">\u001b\u00c5\u00fa\u00d5\u00c8D\u00f8\u00f4RGh\u00f5\r \u00d4S\u00ca\u00f1<\u0092\u0084\u00bd\u00db\u0014\u00d4\u00bf\u008e\u000b\u0086c\u00dc\u00a3\u00ab\u008cj\u00b7*@g\u00b0b\u0001\u00a5O:\u00ad\u0005 \u0016\u00be\u00c2\u00a7\u00a0\u00d4\u00a4\u0081\u00de\u00e9E\u00e6d\u0085\u000b\u00dd\u00b0N\u00da2\u0083\u00c8ezSR\u0094\u00ef\u0086\u00ed\u00ee<\u0010\u00cd\u00d3z\u0011\u00e6Y\u0091>s\u0000\u00f7\u00ec\u00fc\u00b1\u00e2c\u0010^\u0083\u00f6\u00fc{\u00153}6\u00f2H\u00a5\u008b\u00c5\u00981\u0018\u00a8/\u00fc\u00f5\u000fv\u00a9\u009dxR!\u0010\u00d8\u009b\u00d6h\u001d\u00b1\u00819\u00e6\u00cbEL AP+S\u00d8\u00c1\u001f\u00d7\u00b4\u00fe\u009aFkQ\u009e\u0088'\u009bBXO\u0095\u0003\u000b\u00d1n\u00ed\u0094\u0090\u00a9_\u0015 \u00afq\u00d6\u00a0g\u00f0\u00ae\u00c2\u00f2[\r\u008d\u00a9\u0007\u0099\u008dQ\u00fa\u008a\u00ac\u001a3V\u00db;\u008a\u0016F\\\u00a0S- \u00bf\u00c3\u00b7lEc\u0095\u00ee\u00ffV_\u00d3\u00c2Na\u00b9:\u00a8\u0080^\u0085d\u00b9\u00e6\u00b8q\u00f3\u00c6\u00bf\f\u0015\u0005\u0018b&\u00ee\u00f2|E\u007fv^\u00bb\u00a9\u0092W\u009e\u00d0\u0097\u009f\u00b7C\u00df$\u000fP\u00d2\u0018\u0006\u0016\u0093\u0085\u00f0\u008f\u0004\u0004\u00e2\u00a4\u008cu\u0097\u008c<\u001b\u00a9\u00c7\u00e5\u0092\u00b8i}\u00a9(\u008ei\u00a44\u00d45\u00a7j\u00ac\u00b1<\\\u00ad\u001cy\\\u0004\u009a\u00a3n\u00ceKch\u00b7&#\u00baEO\u009d\u00d1E?C\u009a~\u00c8\u001a\u00be \u00ce\u00f8/\u00ff*\u0006\u00dd\u00eb\u008f\r\u0099N#~\u00dbN{\u009f\u0012\u0094$\u00bemh\u00ca\u00a9p\u0090\u0019\u00b6\u007f# \u007f=\u00fc\u00e3\u00a6{\u00d6G/\u00da\u0099\u0096\u0099\u0080\u009dV\u00bf\u008e[By\u0083\u0093\u001ea\u0097\u00c74:\u0012S\u00e4\u0010\u001fD\u0088I\u00ab\u00cf\u00a7%\u00b9\u0017\u00ba\u00ef\u00c2q\u008e\u00b3\u0018`\u00a3\u00e3fOx\u00e5;\u0087\u00c0\u0013\u00d09o\u00f5\u00927\u00efy\u007f>\u00c3\u00c7,\u0010\u000e\u00f6\u00b8\u0007^0\u0089DN\u00ec\u0013Y>Q\u007f9\u0018\u00bc\u00c2Lk!(\u0098\u00c92\nd\\\u0089_\u0018\u00e3j*\u00c5\u00c6\u0087\u001b\u0088\u0016\u0010\u001aP\u007f\u00a0\u0000\u008a\u0088W\u00b9\u00e4\u0015\u008c\u001e\u00f7\u0019[ f\u00b0\u0013= \u001cw\u00ce\u00d73\u00af\u009e\u00d7*L\u00dc\fg\f\u001a\u000e)\u001e#\u00ba\u00a9\u00e8\u00cf\u00f9\u00ff.\u00b0 \u00ae\u0019}\u0019\u0085\u00dd#\u0097\u00edi\u0010q\u00eb\u00d7\u00d6\tb\u00fc\u00e1e\u009d \u00f4\u008c\u0003)3\u00a2\u00f0\u0089\u0000\u0000\u0010\u00a3H\u00c1\u00c0S\u00e3\u0085\u00cf\u00e0\u0095\u000f\u00dd\r9=\u00fb8u=\u0092\u00fbMRh\u00e7\u00df=!\u009f8\u009e\u0097&t\u00ee>\u009c\u00cd\u008e\u0097*\u00bc\u00cc\u00b6\u00d0\u00a2\u0004\u00be\u00c9\u00d8\u0098\u00cf\u00e3\u00e7\u00fe\u00d6\u00fc+\n4\u0016h\u00e3\u00e9\u00d9\u00d9\u0091\u00da\u0080\u00a1\u00bc\u000b\u00a8 \u009a\u00fe\u00cd}d\u00a5I=\u00d1\n\u00f2GA Yt\u00fd19dN\u0006o\u00c2\u0018\u00a6\u00a0\u00db\u001b/\u0098\u0007\u00108\u00ae\u0094\u00e1\u00d4N\u00e0\u0002\u00d7\u00deR\u0098O\u00f3\u00c2\u009e@\f\u00bd\u009eA\r\u00f2E'$E\u00db\u00a2\u0001z}\u009dj-\u0000\u0015\u00ff\u008ds\u00da\u00d5:8\u009c\u0017\u009e\u00f0\f\u0000\u00b1\u0089\u0098%?t.\u00b6\u00b6\u00ca\u0003\u001b\u00e1H\u00ea\u00a5\u00aeb\u00a6\u00fc\u00f0\u00dc\u00eej56\u00bc\u00f2\u00aeg\u00b3\u0018b*\r\u00e7Gvk4\u00ad9\u0081\u00b6\u009f\u00bc\u00dfp\u0097\u001c\u00ef\u0080T\t\u00e0\u0010 \u001b\u00daD%v]\u00f0\u00b5uL\u0011\r<f\u00a4\u00e9tiRX\u00a0?p\u00c2\u00a6\u008e\u00a4w\u0085(s\u009a \u00f8R-\u00cct\u0017v-|\u00b5\u00c6\u00e7G\u007f\u00dfn\u00c8V\u00c7\u00f1DS\u00ad\u00de\u00c0\u00eb\u00eaC\u00f7*\u00b5\u00a6\u0018\u0012\u00ee\u0015DpKMu\u00ce\u00a2\b\u00d6\u00aa\\\u0083\u0000p\u00c9\u0099\u0014^\u00a3\u0089e";
                            var24_6 = 3221;
                            var21_7 = 32;
                            var20_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = NameTags.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u009d\u0086\u0017\t\u0098\u00eb\u007f\u009b2f\u00126\u008a\u00a0\u00ae\u00c1\u00022g\u00af\u00bbc\u00d9?\u00a5M\u009d]o\u00e1\u0001b\u0018;\u00b7\u009a)\u009c\u0087\u008b\u00ca\u0081\u000f.\u00e6\u00fd\u0002gfI\u00f3\u0098*\u00e3\u0006\u00cf\u0094";
                                var24_6 = 57;
                                var21_7 = 32;
                                var20_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = NameTags.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    NameTags.h = var25_3;
                    NameTags.i = new String[112];
                    NameTags.L = new HashMap<K, V>(13);
                    var7_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var27 >>> 56);
                    for (var8_11 = 1; var8_11 < 8; ++var8_11) {
                        v9 = v9;
                        v9[var8_11] = (byte)(var27 << var8_11 * 8 >>> 56);
                    }
                    var7_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var13_12 = new long[106];
                    var10_13 = 0;
                    var11_14 = "{h\u00a8BM\u00f2\u00d4#\u00b7\u0090X\u00e1Q\u0000\u001d\u009d\u00a4]iE|&.ad\u0011\u00a6A\u00d5|\u00c5\u00f0\u0089\u00c2\u009ev+\u00da\u00d4\u0081\u008cw\u0088\u00cbV\u00a2\u0007}\u00a6\u0007\u0096K$\u00cc_\u00da%$\u00d1\u00d2\u001a\u008dx\u00e1NW7KK\u00ec\u00f9\u00bec\u00f9\u00be\u00c2aT\u00b0\u00a2\u001f\u00fd\u00b9\u00e6\u0007A\u00ba\u00d1+d\u00ae3\u00c0\u00b7D\f\u00f0\u000e\u00fa\b\u00a4\u00d4\u00d8\u00883hI\u00dc\u0016\u00c6\u001f\u00d7\u00ac\\o\u0091\u00dc\u00d4Uu:\u00c6<\u00d9\u0090\u009br\u0081[a\u00f0I\u00f5\u00d3\u008cJ\u00c5\u00ba\u00c9\u00c7\u00cc+\u00b9\u008a\u00f2\u0099\u001b\u001d;2\u00e7\u0092\u0085\u00eapx\u00fc0?\u0011\u0084\u00f7\u00d9'(\u00b8\u00a5z\u00e4\u00e4\u00d0\u00b8\u0087L\u0093xT\u00f2O\u00d70\u0086\u0011\u00a0\u0017\u00a7\u008f\u00c4\u0086\u00e7o\u00ff\u00a5\u00f9\u008b\u00d7,.\u0085zm\u00b2N\u00a4L\n \u0004y\u00b5)2\u00f0<\u009c\u0002\u0002\r\u00b9\u00ea\u0010\u0082\u0089\u00c9H\t+\u00f5\u0010'\u00ac|x\u00d5\u001dS\rn\u00d4\u00ff\u00e3\u00e0 \u0090>\u008e\t;QF*\u00f9a\u00e4\u00f8\u009b\u00a7\u00f5Ni\u0014\u00c8\u00ea\u00e7\u001dy?P$\u00de|\u00a5\u00ec\u00ad\td\u00ccm\u00b4G\u00fa\u00e1$\u00e2$\u00c8\u00e2\u00ec\u00a3\u0003\u00dd\u0091V\u00e3\u00ed\u00c9a\u00bc$;\u00dc|\u0083WD\u00bb\u00b7IN\u00e8\u009c\u0004\u00c0\u0099\u00d2G\u00df\u00a3p\bJRC@\u00a2\u0019\u00d8\u00df\u00dc\u00cd\u008eR\u0011\u00f9\u00a3\u008a!\u00f5\u0095%t\u00a5Z\u00a4\u0097\u009c(w\u009al\u00a2;\u00a8\u00c4o\u0083\u009ah\u0010\b\b\"e\u00a3\u00e2\u009eK5)\u00d6\u008a\u0013\u00f6\u00887-(\u00de\u00b2\r\u00dc\u00a6\u00e4\u0084\u001c\u00b5\u00db;\u008d6L\u008ct\u00aa0\u00dbQ\u00c6\u008d\b#u\u00b5\u00afVR\u00ed6\u0085\u00a7\u00c1/{\u00ef\u009a&\u00bd31\u00ca=8\u008e\\\u0001\u0015\"\u00ac\u000eF\u00cb\u0092X\u00e6F\u00e8[\u00ac^\u00ebl\u000b\u00f0\u00a6\u00b0\f\u0086w\u0088+3l\u00ac\u0019iR\u0089\u00e9\u00d1\u0019\u0005\u0091\u0004\u0080P\u00ff\u00c77\u00f9\u00db\u00d1k\u009f\u00cb\u0085\u0016\u0017\u00fe\u00f0\u00a2\u00e2>\u00e2\u007f\u00f1\u00f5\u00d1\u00c6@KU\u00dc\u0081\u00a0\u0091\u00b1\u0090\u009f\u008cr\u00ea_\u0097\u00f27\u0087<\u00a9\u00e9\u00c0\u00b4\u00ba\u0092\u0090\u00c4\f\u00c0\u00dan5\u008a7f=\u00c66M\u000e\u00d3\u00f2Y(\u00dap\u0016,\u00b5\u0084U\u00f8\u001d\u009f\u00a7B\u00a7\u00c5\u0014j\u00af\u0005\u0097seH\u00f9\u00c8E\u00be\u008b\u00db\u00cc\u00a4*\u00e7<\u00f4\u00eey\u00ed\u000b\u00aaO\u00f9O\u00ab\u0089\u009f=\u00d2\u00d4{\u00ce\u00f1<\u00b8\u0001`\u0082Gr>\u0017w\u00ef$\u00d2C\bE\u0006W\u0088\u00dc\u0006\u000e\u0011\u0001o8\u001a;F\u00b9\u00cc\u00a2pD\u00cfI\u00b3T`=\u001e\u00d3\u00d6\u0013\u00cbh\u0093\u00f1\u00c8\u00aa!*\u00a1\u0099{\u0005Vl\u00fcd\u00bd\u00e29\u00bd\u001bl\u001d\u0083o4\u00a2\u00aegp\u00bd\u00d2^%h\u00c7\u00bb\u00e8\u00af^\u00f9|1\u0082\u0002|2\u008f\u00de\u000b\u0011\u00e9\u00c9\u00fe\u0097\u0099\u00a4\u0019\u00c9\u0011\u009c\u000e\u00f6\u007f\u00ed\u00b3&\u00d5\fv\u00da\u00ac\u001d\u00c0\u001c\u00ee=\u00dd\u0085\u0015'\u0094\u00e0\u0013\u001c\u00a0+\u0007+\u0084@\u009b\u0014:/\u00d1<6Qy\u0012x\u00b8eo\u000f\u0014\u008c-F\u00f3\u0095_\u00b3(\u00fa\u00b5\u0083\u00b8Bd\u00d36\r\n\u00a2\u0016v\u0086T\u00ab\u00e6bC\u00ce\u00bc\u00ef\u00e6<\u00c7\u0001\u0018&\r\u00e3(\u00b3'\u0012f\u00d2\u00fc\u00b5\u001f\u0097n\u00c2|Y\u00af\u00de\u00b2\u0006EyM6!r\u00da\u0007gT\u00dc\u00b8\u000f\u0004\u008eE1\u00f4\u00db\u00fd\u00de\u00fb\u0013\u00a3a\u0081qT\n\u00fc\u00bf)@\u0097\u00af\u00e6\u0094\u001d\u00ea\u00b7L\u001c";
                    var12_15 = 832;
                    var9_16 = 0;
                    while (true) {
                        var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                        v10 = var13_12;
                        v11 = var10_13++;
                        v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        var11_14 = "\u00fa\u00ae\\\u0095-\u00a0\u00fbo\u0094}\u00ea;\u0014n5\u00be";
                        var12_15 = 16;
                        var9_16 = 0;
                        while (true) {
                            var14_17 = var11_14.substring(var9_16, var9_16 += 8).getBytes("ISO-8859-1");
                            v10 = var13_12;
                            v11 = var10_13++;
                            v12 = ((long)var14_17[0] & 255L) << 56 | ((long)var14_17[1] & 255L) << 48 | ((long)var14_17[2] & 255L) << 40 | ((long)var14_17[3] & 255L) << 32 | ((long)var14_17[4] & 255L) << 24 | ((long)var14_17[5] & 255L) << 16 | ((long)var14_17[6] & 255L) << 8 | (long)var14_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl92:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var9_16 < var12_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var15_18 = v12;
                var17_19 = var7_10.doFinal(new byte[]{(byte)(var15_18 >>> 56), (byte)(var15_18 >>> 48), (byte)(var15_18 >>> 40), (byte)(var15_18 >>> 32), (byte)(var15_18 >>> 24), (byte)(var15_18 >>> 16), (byte)(var15_18 >>> 8), (byte)var15_18});
                v14 = ((long)var17_19[0] & 255L) << 56 | ((long)var17_19[1] & 255L) << 48 | ((long)var17_19[2] & 255L) << 40 | ((long)var17_19[3] & 255L) << 32 | ((long)var17_19[4] & 255L) << 24 | ((long)var17_19[5] & 255L) << 16 | ((long)var17_19[6] & 255L) << 8 | (long)var17_19[7] & 255L;
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
            NameTags.B = var13_12;
            NameTags.C = new Integer[106];
            var2_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var27 >>> 56);
            for (var3_21 = 1; var3_21 < 8; ++var3_21) {
                v17 = v17;
                v17[var3_21] = (byte)(var27 << var3_21 * 8 >>> 56);
            }
            break block24;
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var6_23 = var2_20.doFinal(new byte[]{-40, 28, 41, 113, -77, -31, 124, -118});
        ** while (true)
        var0_22 = ((long)var6_23[0] & 255L) << 56 | ((long)var6_23[1] & 255L) << 48 | ((long)var6_23[2] & 255L) << 40 | ((long)var6_23[3] & 255L) << 32 | ((long)var6_23[4] & 255L) << 24 | ((long)var6_23[5] & 255L) << 16 | ((long)var6_23[6] & 255L) << 8 | (long)var6_23[7] & 255L;
        var51_24 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(NameTags.class, "seeCatleanUsers", "getSeeCatleanUsers()Lsu/catlean/at;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "nameMode", "getNameMode()Lsu/catlean/al;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "components", "getComponents()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "gamemode", "getGamemode()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "ping", "getPing()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "hp", "getHp()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "distance", "getDistance()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "pops", "getPops()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "enchantss", "getEnchantss()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "onlyHands", "getOnlyHands()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "potions", "getPotions()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "shulkers", "getShulkers()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "selection", "getSelection()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "spawners", "getSpawners()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "entityOwner", "getEntityOwner()Z", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "fillColorA", "getFillColorA()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "outlineColor", "getOutlineColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "fillColorF", "getFillColorF()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "friendOutlineColor", "getFriendOutlineColor()Ljava/awt/Color;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "armorMode", "getArmorMode()Lsu/catlean/yu;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "health", "getHealth()Lsu/catlean/m7;", 0)), Reflection.property1(new PropertyReference1Impl(NameTags.class, "healthProvider", "getHealthProvider()Lsu/catlean/_j;", 0))};
        NameTags.delegatedProperties = var51_24;
        NameTags.INSTANCE = new NameTags();
        NameTags.seeCatleanUsers$delegate = SettingFactory.modeSetting$default((SettingContainer)NameTags.INSTANCE, "see-catlean-users", (Enum)NameTagsSeeCatleanUsersMode.ON, null, null, 12, null);
        NameTags.nameMode$delegate = SettingFactory.modeSetting$default((SettingContainer)NameTags.INSTANCE, "name-mode", (Enum)NameTagsNameMode.FULL, null, null, 12, null);
        NameTags.components$delegate = SettingFactory.settingGroup$default(NameTags.INSTANCE, "components", AuraTargetsMode.BOOLS, null, 4, null);
        NameTags.gamemode$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "gamemode", false, NameTags.INSTANCE.getComponents(), null, 8, null);
        NameTags.ping$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "ping", false, NameTags.INSTANCE.getComponents(), null, 8, null);
        NameTags.hp$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "h-p", true, NameTags.INSTANCE.getComponents(), null, 8, null);
        NameTags.distance$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "distance", true, NameTags.INSTANCE.getComponents(), null, 8, null);
        NameTags.pops$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "totem-pops", true, NameTags.INSTANCE.getComponents(), null, 8, null);
        NameTags.enchantss$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "enchants", true, NameTags.INSTANCE.getComponents(), null, 8, null);
        var52_25 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)();
        var53_26 = NameTags.INSTANCE.getComponents();
        var54_27 = false;
        var55_28 = "only-hands-enchants";
        var56_29 = NameTags.INSTANCE;
        NameTags.onlyHands$delegate = SettingFactory.booleanSetting(var56_29, var55_28, var54_27, var53_26, var52_25);
        NameTags.potions$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "potions", true, NameTags.INSTANCE.getComponents(), null, 8, null);
        NameTags.shulkers$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "shulkers", true, NameTags.INSTANCE.getComponents(), null, 8, null);
        NameTags.selection$delegate = SettingFactory.settingGroup$default(NameTags.INSTANCE, "selection", AuraTargetsMode.BOOLS, null, 4, null);
        NameTags.spawners$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "spawners", false, NameTags.INSTANCE.getSelection(), null, 8, null);
        NameTags.entityOwner$delegate = SettingFactory.booleanSetting$default((SettingContainer)NameTags.INSTANCE, "entity-owner", false, NameTags.INSTANCE.getSelection(), null, 8, null);
        NameTags.fillColorA$delegate = SettingFactory.colorSetting$default(NameTags.INSTANCE, "fill", new Color(150, 150, 150, 255), null, null, 12, null);
        NameTags.outlineColor$delegate = SettingFactory.colorSetting$default(NameTags.INSTANCE, "outline", new Color(-1), null, null, 12, null);
        NameTags.fillColorF$delegate = SettingFactory.colorSetting$default(NameTags.INSTANCE, "friend-fill", new Color(150, 150, 150, 255), null, null, 12, null);
        NameTags.friendOutlineColor$delegate = SettingFactory.colorSetting$default(NameTags.INSTANCE, "friend-outline", new Color(0, 255, 0, 255), null, null, 12, null);
        NameTags.armorMode$delegate = SettingFactory.modeSetting$default((SettingContainer)NameTags.INSTANCE, "armor-mode", (Enum)ArmorDisplayMode.Full, null, null, 12, null);
        NameTags.health$delegate = SettingFactory.modeSetting$default((SettingContainer)NameTags.INSTANCE, "health", (Enum)NameTagsHealthMode.Number, null, null, 12, null);
        NameTags.healthProvider$delegate = SettingFactory.modeSetting$default((SettingContainer)NameTags.INSTANCE, "health-provider", (Enum)NameSource.Entity, null, null, 12, null);
        NameTags.D = new HashMap<K, V>();
        NameTags.renderTarget = new RenderTarget("add-friend", 15, 15);
        NameTags.animation = new Animation(Easing.OUT_QUINT, var0_22);
        7961869207756909160L ^ var27;
        NameTags.render2D = new Render2D();
        NameTags.l = new Render2D();
        var51_24 = new Pair[]{TuplesKt.to(Character.valueOf('\u1d00'), Character.valueOf('A')), TuplesKt.to(Character.valueOf('\u0299'), Character.valueOf('B')), TuplesKt.to(Character.valueOf('\u1d04'), Character.valueOf('C')), TuplesKt.to(Character.valueOf('\u1d05'), Character.valueOf('D')), TuplesKt.to(Character.valueOf('\u1d07'), Character.valueOf('E')), TuplesKt.to(Character.valueOf('\ua730'), Character.valueOf('F')), TuplesKt.to(Character.valueOf('\u0262'), Character.valueOf('G')), TuplesKt.to(Character.valueOf('\u029c'), Character.valueOf('H')), TuplesKt.to(Character.valueOf('\u026a'), Character.valueOf('I')), TuplesKt.to(Character.valueOf('\u1d0a'), Character.valueOf('J')), TuplesKt.to(Character.valueOf('\u1d0b'), Character.valueOf('K')), TuplesKt.to(Character.valueOf('\u029f'), Character.valueOf('L')), TuplesKt.to(Character.valueOf('\u1d0d'), Character.valueOf('M')), TuplesKt.to(Character.valueOf('\u0274'), Character.valueOf('N')), TuplesKt.to(Character.valueOf('\u1d0f'), Character.valueOf('O')), TuplesKt.to(Character.valueOf('\u1d18'), Character.valueOf('P')), TuplesKt.to(Character.valueOf('\ua7af'), Character.valueOf('Q')), TuplesKt.to(Character.valueOf('\u0280'), Character.valueOf('R')), TuplesKt.to(Character.valueOf('\ua731'), Character.valueOf('S')), TuplesKt.to(Character.valueOf('\u1d1b'), Character.valueOf('T')), TuplesKt.to(Character.valueOf('\u1d1c'), Character.valueOf('U')), TuplesKt.to(Character.valueOf('\u1d20'), Character.valueOf('V')), TuplesKt.to(Character.valueOf('\u1d21'), Character.valueOf('W')), TuplesKt.to(Character.valueOf('\u028f'), Character.valueOf('Y')), TuplesKt.to(Character.valueOf('\u1d22'), Character.valueOf('Z'))};
        NameTags.f = MapsKt.mapOf(var51_24);
        NameTags.G = "";
        NameTags.D.put(Enchantments.BLAST_PROTECTION, "B");
        NameTags.D.put(Enchantments.PROTECTION, "P");
        NameTags.D.put(Enchantments.SHARPNESS, "S");
        NameTags.D.put(Enchantments.EFFICIENCY, "E");
        NameTags.D.put(Enchantments.UNBREAKING, "U");
        NameTags.D.put(Enchantments.POWER, "PO");
        NameTags.D.put(Enchantments.THORNS, "T");
    }

    public static void zkmSetFlag(int[] nArray) {
        E = nArray;
    }

    public static int[] int_arr_zkmGetFlag() {
        return E;
    }

    private static RuntimeException zkmRethrow(RuntimeException runtimeException) {
        return runtimeException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3566;
        if (i[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])w.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    w.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ko", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = h[n2].getBytes("ISO-8859-1");
            NameTags.i[n2] = NameTags.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return i[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NameTags.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ko" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1D78;
        if (C[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = B[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])L.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    L.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ko", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            NameTags.C[n2] = n3;
        }
        return C[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = NameTags.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ko" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NameTags.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(NameTags.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

