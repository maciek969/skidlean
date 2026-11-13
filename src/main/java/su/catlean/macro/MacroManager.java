/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

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
import java.util.LinkedHashMap;
import java.util.List;
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
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.Registries;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.config.ConfigStore;
import su.catlean.config.ModuleConfig;
import su.catlean.gofra.Flow;
import su.catlean.macro.ChatMacro;
import su.catlean.macro.Macro;
import su.catlean.macro.SettingMacro;
import su.catlean.mixins.accessors.MinecraftAccessor;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.basic.GuiMoveClickBypassMode;
import su.catlean.module.basic.Sprint;
import su.catlean.setting.Setting;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.KeybindsWidget;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyNames;
import su.catlean.util.Manager;
import su.catlean.util.MovementUtil;
import su.catlean.util.RandomUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.SlotResult;
import su.catlean.zkm.ZkmKeys;

public final class MacroManager
implements Manager {
    @NotNull
    public static final MacroManager INSTANCE;
    @Nullable
    private static Function0 Z;
    private static int D;
    private static boolean V;
    @NotNull
    private static final Map F;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private MacroManager() {
    }

    @NotNull
    public final List getMacros() {
        return ConfigStore.INSTANCE.getJsonListFile().getData();
    }

    public final boolean addMacro(@NotNull Macro macro) {
        Intrinsics.checkNotNullParameter(macro, "macro");
        return this.getMacros().add(macro);
    }

    public final boolean removeMacro(@NotNull Macro macro) {
        Intrinsics.checkNotNullParameter(macro, "macro");
        return this.getMacros().remove(macro);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block6: {
            var2_2 = MacroManager.a ^ 126642252491583L;
            var4_3 = var2_2 ^ 71883817864335L;
            var10_4 = ModuleConfig.j();
            if (MacroManager.Z == null) break block6;
            v0 = MacroManager.D;
            if (!var10_4) ** GOTO lbl15
            switch (v0) {
                case 0: {
                    Sprint.INSTANCE.h(true);
                    MacroManager.V = Globals.getPlayer().isSprinting();
                    Globals.getPlayer().setSprinting(false);
                    MovementUtil.INSTANCE.releaseMovementKeys();
                    v0 = MacroManager.D = 1;
lbl15:
                    // 2 sources

                    if (var10_4) break;
                }
                case 1: {
                    Sprint.INSTANCE.h(true);
                    GuiMove.INSTANCE.setMacroActive(true);
                    v1 /* !! */  = MacroManager.Z;
                    if (var10_4) {
                        if (v1 /* !! */  != null) {
                            v1 /* !! */  = v1 /* !! */ .invoke();
                        }
                    }
                    GuiMove.INSTANCE.setMacroActive(false);
                    Sprint.INSTANCE.h(true);
                    Globals.getPlayer().setSprinting(MacroManager.V);
                    MovementUtil.INSTANCE.releaseMovementKeys(var4_3);
                    MacroManager.D = 0;
                    MacroManager.Z = null;
                }
            }
        }
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        Object object;
        if (inputEvent.getKey() == -1 || Globals.getMc().world == null || Globals.getMc().player == null || Globals.getMc().currentScreen != null || inputEvent.getAction() == InputEvent.Action.Hold) {
            return;
        }
        Iterable iterable = this.getMacros();
        Iterable iterable2 = iterable;
        Collection collection2 = new ArrayList();
        Iterator iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            object = iterator2.next();
            Macro macro = (Macro)object;
            if (!(macro.getBind().getKey() == inputEvent.getKey() && macro.isEnabled())) continue;
            collection2.add(object);
        }
        iterable = (List)collection2;
        for (Collection collection2 : iterable) {
            Macro macro = (Macro)((Object)collection2);
            object = INSTANCE;
            boolean bl = inputEvent.getAction() == InputEvent.Action.Press;
            super.execute(macro, bl);
        }
    }

    private final void execute(Macro macro, boolean bl) {
        GuiMove.INSTANCE.setMacroActive(true);
        Macro macro2 = macro;
        if (macro2 instanceof ChatMacro) {
            if (bl) {
                this.runChatMacro((ChatMacro)macro);
            }
        } else if (macro2 instanceof ModuleConfig) {
            if (bl) {
                Scheduler.schedule$default(Scheduler.INSTANCE, 0, (byte)-10, false, () -> MacroManager.execute$lambda$0(macro), 5, null);
            }
        } else if (macro2 instanceof SettingMacro) {
            this.runSettingMacro((SettingMacro)macro, bl);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        GuiMove.INSTANCE.setMacroActive(false);
    }

    private final void runChatMacro(ChatMacro chatMacro) {
        block2: {
            block1: {
                boolean bl;
                block0: {
                    bl = ModuleConfig.j();
                    if (!bl) break block0;
                    if (!StringsKt.startsWith$default(chatMacro.getMessage(), "/", false, 2, null)) break block1;
                    Globals.getPlayer().networkHandler.sendChatCommand(StringsKt.replaceFirst$default(chatMacro.getMessage(), "/", "", false, 4, null));
                }
                if (bl) break block2;
            }
            Globals.getPlayer().networkHandler.sendChatMessage(chatMacro.getMessage());
        }
    }

    /*
     * Unable to fully structure code
     */
    private final void runModuleMacro(ModuleConfig var1_1, long var2_2) {
        block6: {
            block7: {
                block9: {
                    block11: {
                        block10: {
                            block8: {
                                v0 = (var2_2 = MacroManager.a ^ var2_2) ^ 48934626119393L;
                                var32_3 = (int)(v0 >>> 48);
                                var33_4 = v0 << 16 >>> 16;
                                var40_5 = this.runByName(var1_1.getItem());
                                if (var40_5 == null) break block6;
                                var42_6 = new Item[]{var40_5};
                                var41_7 = InventoryUtil.INSTANCE.findInHotbar((Item[])var42_6);
                                var43_8 = new Item[]{var40_5};
                                var42_6 = InventoryUtil.INSTANCE.findInInventory(var43_8, (char)var32_3, var33_4);
                                if (!var41_7.isFound()) break block7;
                                if (!var1_1.isLegit()) break block8;
                                var41_7.select();
                                if (!(var40_5 instanceof ShieldItem)) {
                                    Scheduler.schedule$default(Scheduler.INSTANCE, RandomUtil.f(new IntRange(1, 2), false, null), (byte)0, false, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, useItem(), ()V)(), 6, null);
                                }
                                break block9;
                            }
                            InventoryUtil.INSTANCE.switchToSlot(var41_7.getSlot());
                            if (var40_5 instanceof BlockItem) break block10;
                            v1 = InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
                            break block11;
                        }
                        if (Globals.getMc().crosshairTarget == null || !(Globals.getMc().crosshairTarget instanceof BlockHitResult)) ** GOTO lbl-1000
                        v2 = Globals.getWorld();
                        v3 = Globals.getMc().crosshairTarget;
                        Intrinsics.checkNotNull(v3, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
                        if (v2.isAir(((BlockHitResult)v3).getBlockPos())) lbl-1000:
                        // 2 sources

                        {
                            v1 = InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
                        } else {
                            v4 = Globals.getInteractionManager();
                            v5 = Globals.getPlayer();
                            v6 = Globals.getMc().crosshairTarget;
                            Intrinsics.checkNotNull(v6, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
                            v7 = v4.interactBlock(v5, Hand.MAIN_HAND, (BlockHitResult)v6);
                            v1 = v7;
                            Intrinsics.checkNotNullExpressionValue(v7, "useItemOn(...)");
                        }
                    }
                    Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                    InventoryUtil.INSTANCE.restoreSelectedSlot();
                }
                return;
            }
            if (var42_6.isFound()) {
                MacroManager.Z = (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, swapAndUse(su.catlean.config.ModuleConfig su.catlean.util.SlotResult ), ()Lkotlin/Unit;)((ModuleConfig)var1_1, (SlotResult)var42_6);
                if (!GuiMove.INSTANCE.getEnabled() || !MovementUtil.INSTANCE.isMoving() || GuiMove.INSTANCE.getClickBypass() != GuiMoveClickBypassMode.LEGIT) {
                    v8 = MacroManager.Z;
                    if (v8 != null) {
                        v8.invoke();
                    }
                    MacroManager.Z = null;
                }
            }
        }
    }

    private final void runSettingMacro(SettingMacro settingMacro, boolean bl) {
        Module module;
        Object object;
        Object object2;
        Module module2;
        Object object3;
        Object object4;
        Object object5;
        Object object6;
        block65: {
            Object v0;
            block64: {
                object6 = ModuleManager.INSTANCE.L();
                object5 = object6.iterator();
                while (object5.hasNext()) {
                    object4 = object5.next();
                    object3 = (Module)object4;
                    if (!Intrinsics.areEqual(object3.getId(), settingMacro.getModule())) continue;
                    v0 = object4;
                    break block64;
                }
                v0 = null;
            }
            Module module3 = v0;
            if (module3 == null) {
                return;
            }
            module2 = module3;
            object2 = module2.getSettings();
            object4 = object2.iterator();
            while (object4.hasNext()) {
                object3 = object4.next();
                object = (Setting)object3;
                if (!Intrinsics.areEqual(((Setting)object).getId(), settingMacro.getSetting())) continue;
                module = object3;
                break block65;
            }
            module = null;
        }
        Setting setting2 = (Setting)((Object)module);
        if (setting2 == null) {
            return;
        }
        Setting setting3 = setting2;
        object6 = setting3.getValue();
        if (object6 instanceof Integer) {
            Object object7 = object2 = setting3 instanceof Setting ? setting3 : null;
            if (object2 != null) {
                if (settingMacro.isHold()) {
                    if (bl) {
                        ((Setting)object2).setValue(Integer.parseInt(settingMacro.getPressValue()));
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    } else {
                        ((Setting)object2).setValue(Integer.parseInt(settingMacro.getReleaseValue()));
                        object3 = object5 = (Iterable)KeybindsWidget.J.K();
                        object = new ArrayList();
                        for (Object t : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)t;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            object.add(t);
                        }
                        object5 = (List)object;
                        object3 = object5.iterator();
                        while (object3.hasNext()) {
                            object = object3.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object;
                            arrayListEntry.setRemoving(true);
                        }
                    }
                } else if (bl) {
                    if (((Number)((Setting)object2).getValue()).intValue() == Integer.parseInt(settingMacro.getPressValue())) {
                        ((Setting)object2).setValue(Integer.parseInt(settingMacro.getReleaseValue()));
                        object3 = object5 = (Iterable)KeybindsWidget.J.K();
                        object = new ArrayList();
                        for (Object t : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)t;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            object.add(t);
                        }
                        object5 = (List)object;
                        object3 = object5.iterator();
                        while (object3.hasNext()) {
                            object = object3.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object;
                            arrayListEntry.setRemoving(true);
                        }
                    } else {
                        ((Setting)object2).setValue(Integer.parseInt(settingMacro.getPressValue()));
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    }
                }
            }
        } else if (object6 instanceof Float) {
            Object object8 = object2 = setting3 instanceof Setting ? setting3 : null;
            if (object2 != null) {
                if (settingMacro.isHold()) {
                    if (bl) {
                        ((Setting)object2).setValue(Float.valueOf(Float.parseFloat(settingMacro.getPressValue())));
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    } else {
                        ((Setting)object2).setValue(Float.valueOf(Float.parseFloat(settingMacro.getReleaseValue())));
                        object3 = object5 = (Iterable)KeybindsWidget.J.K();
                        object = new ArrayList();
                        for (Object t : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)t;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            object.add(t);
                        }
                        object5 = (List)object;
                        object3 = object5.iterator();
                        while (object3.hasNext()) {
                            object = object3.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object;
                            arrayListEntry.setRemoving(true);
                        }
                    }
                } else if (bl) {
                    if (((Number)((Setting)object2).getValue()).floatValue() == Float.parseFloat(settingMacro.getPressValue())) {
                        ((Setting)object2).setValue(Float.valueOf(Float.parseFloat(settingMacro.getReleaseValue())));
                        object3 = object5 = (Iterable)KeybindsWidget.J.K();
                        object = new ArrayList();
                        for (Object t : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)t;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            object.add(t);
                        }
                        object5 = (List)object;
                        object3 = object5.iterator();
                        while (object3.hasNext()) {
                            object = object3.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object;
                            arrayListEntry.setRemoving(true);
                        }
                    } else {
                        ((Setting)object2).setValue(Float.valueOf(Float.parseFloat(settingMacro.getPressValue())));
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    }
                }
            }
        } else if (object6 instanceof Boolean) {
            Object object9 = object2 = setting3 instanceof Setting ? setting3 : null;
            if (object2 != null) {
                if (settingMacro.isHold()) {
                    if (bl) {
                        ((Setting)object2).setValue(Boolean.parseBoolean(settingMacro.getPressValue()));
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    } else {
                        ((Setting)object2).setValue(Boolean.parseBoolean(settingMacro.getReleaseValue()));
                        object3 = object5 = (Iterable)KeybindsWidget.J.K();
                        object = new ArrayList();
                        for (Object t : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)t;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            object.add(t);
                        }
                        object5 = (List)object;
                        object3 = object5.iterator();
                        while (object3.hasNext()) {
                            object = object3.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object;
                            arrayListEntry.setRemoving(true);
                        }
                    }
                } else if (bl) {
                    if ((Boolean)((Setting)object2).getValue() == Boolean.parseBoolean(settingMacro.getPressValue())) {
                        ((Setting)object2).setValue(Boolean.parseBoolean(settingMacro.getReleaseValue()));
                        object3 = object5 = (Iterable)KeybindsWidget.J.K();
                        object = new ArrayList();
                        for (Object t : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)t;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            object.add(t);
                        }
                        object5 = (List)object;
                        object3 = object5.iterator();
                        while (object3.hasNext()) {
                            object = object3.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object;
                            arrayListEntry.setRemoving(true);
                        }
                    } else {
                        ((Setting)object2).setValue(Boolean.parseBoolean(settingMacro.getPressValue()));
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    }
                }
            }
        } else if (object6 instanceof Enum) {
            Object object10 = object2 = setting3 instanceof Setting ? setting3 : null;
            if (object2 != null) {
                Enum enum_;
                Object object11;
                Object object122;
                block67: {
                    Object object13;
                    block66: {
                        ?[] objArray = ((Setting)object2).getValue().getClass().getEnumConstants();
                        Intrinsics.checkNotNullExpressionValue(objArray, "getEnumConstants(...)");
                        object3 = objArray;
                        int n = ((?[])object3).length;
                        for (int i = 0; i < n; ++i) {
                            Object object14 = object3[i];
                            object122 = (Enum)object14;
                            if (!StringsKt.equals(((Enum)object122).name(), StringsKt.replace$default(settingMacro.getPressValue(), " ", "_", false, 4, null), true)) continue;
                            object13 = object14;
                            break block66;
                        }
                        object13 = null;
                    }
                    Enum enum_2 = (Enum)object13;
                    if (enum_2 == null) {
                        return;
                    }
                    object5 = enum_2;
                    ?[] objArray = ((Setting)object2).getValue().getClass().getEnumConstants();
                    Intrinsics.checkNotNullExpressionValue(objArray, "getEnumConstants(...)");
                    for (Object object122 : objArray) {
                        object11 = object122;
                        if (!StringsKt.equals(((Enum)object11).name(), StringsKt.replace$default(settingMacro.getReleaseValue(), " ", "_", false, 4, null), true)) continue;
                        enum_ = object122;
                        break block67;
                    }
                    enum_ = null;
                }
                Enum enum_3 = enum_;
                if (enum_3 == null) {
                    return;
                }
                object4 = enum_3;
                if (settingMacro.isHold()) {
                    if (bl) {
                        ((Setting)object2).setValue(object5);
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    } else {
                        ((Setting)object2).setValue(object4);
                        object3 = KeybindsWidget.J.K();
                        Iterator iterator2 = object3;
                        Collection<Object> collection2 = new ArrayList();
                        object122 = iterator2.iterator();
                        while (object122.hasNext()) {
                            object11 = object122.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object11;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            collection2.add(object11);
                        }
                        object3 = (List)collection2;
                        for (Collection<Object> collection2 : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)((Object)collection2);
                            arrayListEntry.setRemoving(true);
                        }
                    }
                } else if (bl) {
                    if (Intrinsics.areEqual(((Setting)object2).getValue(), object5)) {
                        ((Setting)object2).setValue(object4);
                        object3 = KeybindsWidget.J.K();
                        Iterator iterator3 = object3;
                        Collection<Object> collection3 = new ArrayList();
                        object122 = iterator3.iterator();
                        while (object122.hasNext()) {
                            object11 = object122.next();
                            ArrayListEntry arrayListEntry = (ArrayListEntry)object11;
                            if (!Intrinsics.areEqual(arrayListEntry.getName(), settingMacro.getId())) continue;
                            collection3.add(object11);
                        }
                        object3 = (List)collection3;
                        for (Collection<Object> collection3 : object3) {
                            ArrayListEntry arrayListEntry = (ArrayListEntry)((Object)collection3);
                            arrayListEntry.setRemoving(true);
                        }
                    } else {
                        ((Setting)object2).setValue(object5);
                        KeybindsWidget.J.K().add(new ArrayListEntry(settingMacro.getId(), KeyNames.INSTANCE.getKeyName(settingMacro.getBind().getKey()), ((Object)StringsKt.trim((CharSequence)module2.getDisplayName())).toString() + " -> " + setting3.Q() + " -> " + settingMacro.getPressValue(), null, false, 24, null));
                    }
                }
            }
        } else {
            return;
        }
    }

    @Nullable
    public final Item runByName(@NotNull String string) {
        ItemConvertible itemConvertible;
        boolean bl;
        block7: {
            String string2;
            Map map;
            block6: {
                boolean bl2 = ModuleConfig.j();
                Intrinsics.checkNotNullParameter(string, "id");
                bl = bl2;
                map = F;
                string2 = string;
                if (!bl) break block6;
                if (!map.containsKey(string2)) break block7;
                map = F;
                string2 = string;
            }
            return (Item)map.get(string2);
        }
        for (Object e : Registries.BLOCK) {
            Object object = e;
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            while (Intrinsics.areEqual(((AbstractBlock)((Object)(itemConvertible = (Block)object))).getTranslationKey(), "block.minecraft." + string)) {
                F.put(string, ((Block)itemConvertible).asItem());
                object = ((Block)itemConvertible).asItem();
                if (!bl) continue;
                return object;
            }
        }
        Iterator iterator2 = Registries.ITEM.iterator();
        while (iterator2.hasNext()) {
            ItemConvertible itemConvertible2 = itemConvertible = (Item)iterator2.next();
            while (Intrinsics.areEqual(((Item)itemConvertible2).getTranslationKey(), "item.minecraft." + string)) {
                F.put(string, itemConvertible);
                itemConvertible2 = itemConvertible;
                if (!bl) continue;
                return itemConvertible2;
            }
        }
        return null;
    }

    private static final void execute$lambda$0(Macro macro) {
        long l = a ^ 0x357B1E2F21CAL;
        long l2 = l ^ 0x7DB1F708D62EL;
        INSTANCE.runModuleMacro((ModuleConfig)macro, l2);
    }

    private static final void useItem() {
        MinecraftClient minecraftClient = Globals.getMc();
        Intrinsics.checkNotNull(minecraftClient, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
        ((MinecraftAccessor)((Object)minecraftClient)).idoItemUse();
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
    }

    /*
     * Unable to fully structure code
     */
    private static final Unit swapAndUse(ModuleConfig var0, SlotResult var1_1) {
        block3: {
            block2: {
                if (!var0.isLegit()) break block2;
                InteractionUtil.clickSlot$default(var1_1.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, false, 8, null);
                break block3;
            }
            InteractionUtil.clickSlot$default(var1_1.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, false, 8, null);
            if (Globals.getMc().crosshairTarget == null || !(Globals.getMc().crosshairTarget instanceof BlockHitResult)) ** GOTO lbl-1000
            v0 = Globals.getWorld();
            v1 = Globals.getMc().crosshairTarget;
            Intrinsics.checkNotNull(v1, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
            if (v0.isAir(((BlockHitResult)v1).getBlockPos())) lbl-1000:
            // 2 sources

            {
                v2 = InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
            } else {
                v3 = Globals.getInteractionManager();
                v4 = Globals.getPlayer();
                v5 = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(v5, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
                v6 = v3.interactBlock(v4, Hand.MAIN_HAND, (BlockHitResult)v5);
                v2 = v6;
                Intrinsics.checkNotNullExpressionValue(v6, "useItemOn(...)");
            }
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            InteractionUtil.clickSlot$default(var1_1.getSlot(), Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, false, 8, null);
        }
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
                        MacroManager.a = ZkmKeys.a(-4990807395987353533L, 5101663738302633781L, MethodHandles.lookup().lookupClass()).a(239882848715930L);
                        MacroManager.d = new HashMap<K, V>(13);
                        var11 = MacroManager.a ^ 132680317961752L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[15];
                        var18_4 = 0;
                        var17_5 = "\u0099G\u0083H\u00af\u00d4JhW\u0091\u001e\u0087\u0018\u000b\u00ec+\u0098Kr\u00f42\u009f\u00bd\u0013\u00e4]\u008d\u0087\u00b6\nU\u0003\u0095\u00dc\u00e7\u0089,\u00cb\u00db\u0098\u007f\u0005\u0010\u00b6\u0004\u0093\u00c4\f6\u0012\u00e4\tM\f\u00cd\u001f\u00be9\u009a\u00a0N\n\u00ac\u00c1+\u00a2\u008em\u001c\u001bF/#\u0098\u0016aF\u0018U\u00f1Lh\u00f4\u0006\u0082\u001d\u008d\u0007\u00c1\u00a5\u00bd\u00a2\u00e5\u00e5\u00ebA\u0097`\u0089\u00a6\u00e7\u0085\u00d7w\u00bc~OT\u008d`\u0093b\u001fx\u00da\u0089\u00d7\u001b\u009e\u00a6\u000b\u0099l\u00ca\u00c6X\u00b6\u00df \n\u00caY\u00f3\u00a3t\u00deHf\u0014\u00ca\u008a\u0015XT\b\u00e6\u0097\u00dd~\u00897\u00d0}!kf\u00c9\u009ef+\u00fa\u0010\\4m1z\u00f3\u00e1}\u000fg9\u00b7B\u009d\u00cb\u0094\u0018\u00da\u00bcCSe\u00e4\np4c}Uyd\u00be\u00a9\ta\u00d3FY\u00dc\u00a3\t(\u00a3\u00fc\u00f9q$f\u0091^1\u00ab\u00d9\u00efsS\r\u00b28\u00ac,?\u00c9IT\u000b\u00c6\u00a6f\u00a2X\u0011=\u0098\u00f2r$\u0018\u00cd\u00a3_\u0019(\u00ad\u0011\u00d9OS\u00ac\fO\u0011\u00c4\u0094W\u00a0\u0007\u0089\u00c6`\u00dc~\u009d(\u00c4-]\u001d*!;\u009a\u0000\u00fc\u00da\u00a8=\t?EgI\u00c8\u0010\u00b5\u00ad\u00e2\u0083\u0006Z\u00f1\u00eeI\u009f\u00ff\u008e3\u00a0\u00c0\u00ffx\u00e1\u0082U\u00f2\u009c$\u0085\u00feE\u00f7\u00d7\u0018c\u0006I\u00ac\u00c0g\u00f0\u00d0\u0016 \u00d27G\"\u00823\u00f8\u00c2b\u0089i\u00b2\u00be\u00a9\u00898c{\u00f2\u00d5\u00b4o\u0087\u00f2f\u00c8\u0088y\u000b\u0018\u00f3K\u000fM\u00ec\u00ab\u00dfZ\u00dem\u001b[OL3\u00f2U\u0082Q\u0088w\u00be\u0001\u00af\fl\u0096\u0083\u00f2\u00e1e\u00bef\u00b7\u00a7)\u00e4\u0006\u00ec\u00cf\u0087\u00e1\u00b2C\u00cd\u00c5\u00f1\u00adl\u00ea\u00bb\u0004u\u000f\u00ff\u00db\u0098`RfmaB\u00dd@{q\u008b(\u0005\u00b5QEI\u00f1*\u00da\u00d8X\u008f\u00a0\u00dd~dW=\u00bc\u009dMEv\u00d0\u0096\u00fbl\u0017\u00ae\u00d7\u0096?\u00f6\u009a\u00e8W\u0081\u00b2\u00f3\u00bb\u0003 \u007f\u0095P\u00cd\u00dc\u00fe\u0010[\u00b9\u00a5E\u00f4\u00c9\u00c5Wd\u00cd\u00cc\u00eb\u0086\u00ca\u00df`\u00f8\u001e%!\u00eb\u000e\u00e0}X\u0010\u009cK]\u00ce}\u00bb-\u00a9m\u00c9\u00e3\tg&NI\u0088\u00ec\u00f2\u00dd\u00b3g4T\u0012\u0082\u009a7X\u00e0\u00b5\u00dc\u00f7T\u00da\u00f0U\u00d2\u00ca\u00df\u00ef_\b\u00c1\u0080\u00a4^r h}\u00df.\u00e7\u00c6\u009c\u00b1N,\u00a9[\u008be\u00c0;\u0006\u009c\\\u0088\u00ad\u0086\u00d1Q\u0015\u00e9\u0093_\u008d#I\u000e=\u0086\u0098\u009b\u00d6\u0000\u009c\u00fcnm\u00ab\u0099\u00c5\u00ab@V\u0087XVJ\u00ca\u0082\u0010\u00c1\u00f2\u00a8:\u00eb\u0000mD\u000f\u00b3\u00ee7TU\u0080+y\u0090\u00b2\u0096\u00a1\u00c0\u00ed3Fh\u00a9\u0088\u00ad\u0010\u00b6Nz\u00f0\u0095\u0088\u009b#\u009di\u0084:\u00e8*\u00ea\u00acE\u0013$\u0010\u008cr\u00f6\u0017\u00dbe\u00d6M\u0019\u0091;\u00fdzgk\u00a0";
                        var19_6 = 668;
                        var16_7 = 128;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = MacroManager.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u0017kx\u008a\u00e6\u0095\u00d9\u00f6\u0016\u0013\u0099)\u0001\u00918\u00e5\u00b9=\u0084+`\u0090\u008dR\u00f2m\u0005M\u00db\u00d0+\u009b\u0010\u00c3\u0082E\u0080\u00cc\u00bbfm\u001a\u00e8~v4U\u00e3\u00cd";
                            var19_6 = 49;
                            var16_7 = 32;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = MacroManager.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                MacroManager.b = var20_3;
                MacroManager.c = new String[15];
                MacroManager.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "\u00e0\u00b1\u00ad\u00d8\u001d\u001a\u00ee\u0098\u00aa\u0017\u00ef\u00b04\u00c6L\u001f\u00a1<\u008d\u0094\u00a6\f\u009f\u00e3v\u00e8\u00bc>?X\u00ed\u0005\u0015\u00bd@\u00e7\u00d6}r(";
                var5_15 = 40;
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
                    var4_14 = "F\u00c6s\u00c3\u0082+5\u0015\u00ab$\u00a7X\u00dfU\u0004K";
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
        MacroManager.e = var6_12;
        MacroManager.f = new Integer[7];
        MacroManager.INSTANCE = new MacroManager();
        MacroManager.F = new LinkedHashMap<K, V>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x952;
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
                throw new RuntimeException("su/catlean/zg", exception);
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
            MacroManager.c[n2] = MacroManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MacroManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2BFE;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/zg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MacroManager.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MacroManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/zg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MacroManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MacroManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

