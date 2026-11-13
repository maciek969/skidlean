package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.InteractionManagerAccessor;
import su.catlean.module.Module;
import su.catlean.module.basic.GuiMove;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class InventoryUtil implements Manager {
   @NotNull
   public static final InventoryUtil INSTANCE;
   private static int B;
   private static int O;
   private static int r;
   private static final long a = ZkmKeys.a(6481707318787404420L, -7861560322843911412L, MethodHandles.lookup().lookupClass()).a(137461647105705L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long[] e;
   private static final Integer[] f;
   private static final Map g;

   private InventoryUtil() {
   }

   @Flow
   private final void onSendPacket(SendPacket var1) {
      if (var1.getPacket() instanceof UpdateSelectedSlotC2SPacket) {
         B = ((UpdateSelectedSlotC2SPacket)var1.getPacket()).getSelectedSlot();
      }
   }

   public final int findItemSlot(@NotNull Item item, boolean offHand) {
      Intrinsics.checkNotNullParameter(item, "item");
      int var8 = 0;

      for (int var9 = 0; var9 < 45; var9++) {
         ClientPlayerEntity var10000 = Globals.getMc().player;
         Intrinsics.checkNotNull(var10000);
         ItemStack var11 = var10000.getInventory().getStack(var9);
         Intrinsics.checkNotNullExpressionValue(var11, "getItem(...)");
         ItemStack var10 = var11;
         if (Intrinsics.areEqual(var10.getItem(), item)) {
            var8 += var10.getCount();
         }
      }

      if (Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.getCursorStack().getItem(), item)) {
         O++;
      }

      if (offHand && Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), item)) {
         var8 += Globals.getPlayer().getOffHandStack().getCount();
      }

      return var8;
   }

   public static int findItemSlot$default(InventoryUtil var0, Item var1, boolean var2, Object var3) {
      var2 = false;
      return var0.findItemSlot(var1, var2);
   }

   public final int findInOpenScreen(@NotNull Item item) {
      Intrinsics.checkNotNullParameter(item, "item");
      int var7 = 0;
      int var8 = 0;

      for (int var9 = Globals.getPlayer().currentScreenHandler.slots.size(); var8 < var9; var8++) {
         ItemStack var10000 = Globals.getPlayer().currentScreenHandler.getSlot(var8).getStack();
         Intrinsics.checkNotNullExpressionValue(var10000, "getItem(...)");
         ItemStack var10 = var10000;
         if (Intrinsics.areEqual(var10.getItem(), item)) {
            var7++;
         }
      }

      if (Intrinsics.areEqual(Globals.getPlayer().currentScreenHandler.getCursorStack().getItem(), item)) {
         var7++;
      }

      return var7;
   }

   public final int findSlot(@NotNull ItemPredicate searcher, long a) {
      ax = a ^ ax;
      Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(-5633472591590899671L, ax);
      Intrinsics.checkNotNullParameter(searcher, zkmBootstrap<"q">(31109, 3279643746575419625L ^ ax));
      int var7 = 0;
      Module[] var6 = var10000;
      int var8 = 0;

      while (true) {
         if (var8 < zkmBootstrap2<"g">(11178, 2187790321288190822L ^ ax)) {
            ItemStack var11 = Globals.getPlayer().getInventory().getStack(var8);
            Intrinsics.checkNotNullExpressionValue(var11, zkmBootstrap<"q">(24361, 7245111314403700292L ^ ax));
            ItemStack var9 = var11;
            var10000 = var6;
            if (ax > 0L) {
               if (var6 == null) {
                  var13 = searcher.isTool(var9);
                  if (var6 != null) {
                     break;
                  }

                  if (var13 != 0) {
                     var7 += var9.getCount();
                  }

                  var8++;
               }

               var10000 = var6;
            }

            if (var10000 == null) {
               continue;
            }
         }

         var13 = var7;
         break;
      }

      return var13;
   }

   @NotNull
   public final SlotResult findInHotbar(@NotNull ItemPredicate searcher) {
      Intrinsics.checkNotNullParameter(searcher, "searcher");

      for (int var7 = 0; var7 < 9; var7++) {
         ItemStack var10;
         label23: {
            ClientPlayerEntity var10000 = Globals.getMc().player;
            if (var10000 != null) {
               PlayerInventory var9 = var10000.getInventory();
               if (var9 != null) {
                  var10 = var9.getStack(var7);
                  break label23;
               }
            }

            var10 = null;
         }

         ItemStack var8 = var10;
         if (var8 != null && searcher.isTool(var8)) {
            return new SlotResult(var7, true, var8);
         }
      }

      return SlotResult.Companion.notFound();
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   @NotNull
   public final SlotResult findInInventory(short a, @NotNull ItemPredicate searcher, short a, int a) {
      long var5 = ((long)axxx << 48 | (long)ax << 48 >>> 16 | (long)axx << 32 >>> 32) ^ a;
      Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(-5411362759790541538L, var5);
      Intrinsics.checkNotNullParameter(searcher, zkmBootstrap<"q">(10459, 3511868434711993484L ^ var5));
      Module[] var11 = var10000;
      int var12 = zkmBootstrap2<"g">(340, 779892486411400361L ^ var5);

      ItemStack var13;
      label59:
      while (true) {
         if (var12 < 0 && axx < 0) {
            return SlotResult.Companion.notFound();
         }

         do {
            ItemStack var16;
            if (ax > 0) {
               ClientPlayerEntity var14 = Globals.getMc().player;
               if (var14 != null) {
                  PlayerInventory var15 = var14.getInventory();
                  var16 = var15 != null ? var15.getStack(var12) : null;
               } else {
                  var16 = null;
               }
            } else {
               var16 = null;
            }

            var13 = var16;
            var10000 = var11;
            if (axxx >= 0) {
               if (var11 == null) {
                  if (var13 != null) {
                     var18 = searcher.isTool(var13);
                     if (axxx < 0 || var11 != null) {
                        break label59;
                     }

                     if (var18 != 0) {
                        var18 = var12;
                        break label59;
                     }
                  }

                  var12--;
               }

               var10000 = var11;
            }

            if (var10000 == null) {
               continue label59;
            }
         } while (axx >= 0);

         return SlotResult.Companion.notFound();
      }

      if (var18 < zkmBootstrap2<"g">(8828, 7804462944362567560L ^ var5)) {
         var12 += 36;
      }

      return new SlotResult(var12, true, var13);
   }

   @NotNull
   public final SlotResult findInHotbar(@NotNull List items) {
      Intrinsics.checkNotNullParameter(items, "items");
      return this.findInHotbar(InventoryUtil::findInHotbar$lambda$0);
   }

   @NotNull
   public final SlotResult findInHotbar(@NotNull Item[] items) {
      byte var6 = 0;
      byte var7 = 0;
      byte var8 = 0;
      long var2 = ((long)var6 << 32 | (long)var7 << 48 >>> 32 | (long)var8 << 48 >>> 48) ^ a;
      Intrinsics.checkNotNullParameter(items, zkmBootstrap<"q">(19060, 4005904866845484089L ^ var2));
      return this.findInHotbar(InventoryUtil::findInHotbar$lambda$0);
   }

   @NotNull
   public final SlotResult findAnyInInventory(@NotNull List items, short a, int a, char a) {
      long var5 = ((long)axxx << 48 | (long)ax << 32 >>> 16 | (long)axx << 48 >>> 48) ^ a;
      long var10001 = var5 ^ 114977898525058L;
      int var7 = (int)((var5 ^ 114977898525058L) >>> 48);
      int var8 = (int)((var5 ^ 114977898525058L) << 16 >>> 48);
      int var9 = (int)(var10001 << 32 >>> 32);
      Intrinsics.checkNotNullParameter(items, zkmBootstrap<"q">(20083, 8861407125440002938L ^ var5));
      return this.findInInventory((short)var7, InventoryUtil::findAnyInInventory$lambda$0, (short)var8, var9);
   }

   @NotNull
   public final SlotResult findInInventory(@NotNull Item[] items, char a, long a) {
      long var5 = ((long)axx << 48 | ax << 16 >>> 16) ^ a;
      long var10001 = var5 ^ 91050808020138L;
      int var7 = (int)((var5 ^ 91050808020138L) >>> 48);
      int var8 = (int)((var5 ^ 91050808020138L) << 16 >>> 48);
      int var9 = (int)(var10001 << 32 >>> 32);
      Intrinsics.checkNotNullParameter(items, zkmBootstrap<"q">(19060, 4005888669917053510L ^ var5));
      return this.findInInventory((short)var7, InventoryUtil::findInInventory$lambda$0, (short)var8, var9);
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   @NotNull
   public final SlotResult findAllSlots(long a, @NotNull List blocks) {
      ax = a ^ ax;
      Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(-3792166690596439385L, ax);
      Intrinsics.checkNotNullParameter(blocks, zkmBootstrap<"q">(32442, 7302747256325829968L ^ ax));
      Iterable var7 = blocks;
      Module[] var6 = var10000;
      var var10 = new ArrayList(CollectionsKt.collectionSizeOrDefault(var7, zkmBootstrap2<"g">(4355, 9166691904713120589L ^ ax)));
      Iterator var12 = var7.iterator();

      label22:
      while (true) {
         if (var12.hasNext()) {
            var10000 = (Module[])var12.next();
         } else {
            var10000 = var10;
            if (ax >= 0L) {
               return this.findInHotbar(var10);
            }
         }

         do {
            Object var13 = var10000;
            Block var14 = (Block)var13;
            if (var6 != null) {
               return this.findInHotbar(var10);
            }

            var10.add(var14.asItem());
            if (var6 == null) {
               continue label22;
            }

            var10000 = var10;
         } while (ax < 0L);

         return this.findInHotbar(var10);
      }
   }

   @NotNull
   public final SlotResult findBlockInHotbar(@NotNull Block[] blocks) {
      Intrinsics.checkNotNullParameter(blocks, "blocks");
      Block[] var7 = blocks;
      var var8 = new ArrayList(blocks.length);
      int var10 = 0;

      for (int var11 = blocks.length; var10 < var11; var10++) {
         Block var12 = var7[var10];
         var8.add(var12.asItem());
      }

      return this.findInHotbar(var8);
   }

   @NotNull
   public final SlotResult findItems(byte a, int a, @NotNull List blocks, int a) {
      long var5 = ((long)axxx << 56 | (long)ax << 32 >>> 8 | (long)axx << 40 >>> 40) ^ a;
      long var10001 = var5 ^ 126957511235558L;
      int var7 = (int)((var5 ^ 126957511235558L) >>> 48);
      int var8 = (int)((var5 ^ 126957511235558L) << 16 >>> 32);
      int var9 = (int)(var10001 << 48 >>> 48);
      Intrinsics.checkNotNullParameter(blocks, "blocks");
      Iterable var11 = blocks;
      var var14 = new ArrayList(CollectionsKt.collectionSizeOrDefault(var11, 10));

      for (Object var17 : var11) {
         Block var18 = (Block)var17;
         var14.add(var18.asItem());
      }

      return this.findAnyInInventory(var14, (short)var7, var8, (char)var9);
   }

   @NotNull
   public final SlotResult findBlock(int a, short a, @NotNull Block[] blocks, int a) {
      long var5 = ((long)axxx << 32 | (long)ax << 48 >>> 32 | (long)axx << 48 >>> 48) ^ a;
      long var10001 = var5 ^ 85795162953136L;
      int var7 = (int)((var5 ^ 85795162953136L) >>> 48);
      int var8 = (int)((var5 ^ 85795162953136L) << 16 >>> 32);
      int var9 = (int)(var10001 << 48 >>> 48);
      Intrinsics.checkNotNullParameter(blocks, zkmBootstrap<"q">(32442, 7302807956485992155L ^ var5));
      Block[] var13 = blocks;
      Collection var14 = new ArrayList(blocks.length);
      int var16 = 0;
      Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(-3398849203419340500L, var5);
      int var17 = blocks.length;
      Module[] var10 = var10000;

      while (true) {
         if (var16 < var17) {
            Block var18 = var13[var16];
            var23 = var14;
            if (var10 != null) {
               break;
            }

            var14.add(var18.asItem());
            var16++;
            if (var10 == null) {
               continue;
            }
         }

         var23 = var14;
         if (axx >= 0) {
         }
         break;
      }

      return this.findAnyInInventory((List)var23, (short)var7, var8, (char)var9);
   }

   public final void saveSelectedSlot() {
      O = Globals.getPlayer().getInventory().getSelectedSlot();
   }

   public final void restoreSelectedSlot() {
      if (O != -1) {
         this.selectSlot(O);
      }

      O = -1;
   }

   public final void switchToSlot(int slot) {
      this.saveSelectedSlot();
      this.selectSlot(slot);
   }

   @NotNull
   public final SlotResult findItem(long a, @NotNull ItemPredicate searcher, @NotNull SwapMode mode) {
      ax = a ^ ax;
      int var7 = (int)((ax ^ 28513782028597L) >>> 48);
      int var8 = (int)((ax ^ 28513782028597L) << 16 >>> 48);
      byte var10001;
      int var9 = (int)(var10001 << 32 >>> 32);
      Intrinsics.checkNotNullParameter(searcher, "searcher");
      Intrinsics.checkNotNullParameter(mode, "mode");

      long var13 = ax ^ 28513782028597L;
      return switch (InventoryUtil$WhenMappings.E[mode.ordinal()]) {
         case 1, 2 -> this.findInHotbar(searcher);
         case 3, 4 -> this.findInInventory((short)var7, searcher, (short)var8, var9);
         case 5, 6 -> {
            SlotResult var11 = this.findInHotbar(searcher);
            yield var11.isFound() ? var11 : this.findInInventory((short)var7, searcher, (short)var8, var9);
         }
         default -> throw new NoWhenBranchMatchedException();
      };
   }

   public final boolean swapToMatchAndRun(@NotNull ItemPredicate searcher, @NotNull SwapMode mode, long a, @NotNull Function0 action) {
      ax = a ^ ax;
      long var10001 = ax ^ 84880836276643L;
      int var8 = (int)((ax ^ 84880836276643L) >>> 48);
      int var9 = (int)((ax ^ 84880836276643L) << 16 >>> 48);
      int var10 = (int)(var10001 << 32 >>> 32);
      Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(3855321052173354104L, ax);
      Intrinsics.checkNotNullParameter(searcher, zkmBootstrap<"q">(10459, 3511833076359683562L ^ ax));
      Module[] var13 = var10000;
      Intrinsics.checkNotNullParameter(mode, zkmBootstrap<"q">(18472, 7687297470392581383L ^ ax));
      Intrinsics.checkNotNullParameter(action, zkmBootstrap<"q">(19970, 1419974328244452154L ^ ax));
      switch (InventoryUtil$WhenMappings.E[mode.ordinal()]) {
         case 1:
         case 2:
            SlotResult var18 = this.findInHotbar(searcher);
            boolean var22 = var18.isFound();
            if (var13 == null) {
               if (!var22) {
                  return false;
               }

               this.swapAndRun(var18.getSlot(), mode, action);
               var22 = true;
            }

            return var22;
         case 3:
         case 4:
            SlotResult var17 = this.findInInventory((short)var8, searcher, (short)var9, var10);
            boolean var21 = var17.isFound();
            if (var13 == null) {
               if (!var21) {
                  return false;
               }

               this.swapAndRun(var17.getSlot(), mode, action);
               var21 = true;
            }

            return var21;
         default:
            SlotResult var14 = this.findInHotbar(searcher);
            SlotResult var19 = var14;
            if (var13 == null) {
               if (var14.isFound()) {
                  this.swapAndRun(var14.getSlot(), mode, action);
                  return true;
               }

               var19 = this.findInInventory((short)var8, searcher, (short)var9, var10);
            }

            SlotResult var15 = var19;
            boolean var20 = var15.isFound();
            if (var13 == null) {
               if (!var20) {
                  return false;
               }

               this.swapAndRun(var15.getSlot(), mode, action);
               var20 = true;
            }

            return var20;
      }
   }

   public final boolean swapToItemAndRun(@NotNull Item item, @NotNull SwapMode mode, long a, @NotNull Function0 action) {
      ax = a ^ ax;
      int var11 = (int)((ax ^ 30546807178305L) >>> 48);
      long var12 = (ax ^ 30546807178305L) << 16 >>> 16;
      Intrinsics.checkNotNullParameter(item, "item");
      Intrinsics.checkNotNullParameter(mode, "mode");
      Intrinsics.checkNotNullParameter(action, "action");
      switch (InventoryUtil$WhenMappings.E[mode.ordinal()]) {
         case 1:
         case 2:
            Item[] var23 = new Item[]{item};
            SlotResult var20 = this.findInHotbar(var23);
            if (!var20.isFound()) {
               return false;
            }

            this.swapAndRun(var20.getSlot(), mode, action);
            return true;
         case 3:
         case 4:
            Item[] var22 = new Item[]{item};
            SlotResult var19 = this.findInInventory(var22, (char)var11, var12);
            if (!var19.isFound()) {
               return false;
            }

            this.swapAndRun(var19.getSlot(), mode, action);
            return true;
         default:
            Item[] var16 = new Item[]{item};
            SlotResult var15 = this.findInHotbar(var16);
            if (var15.isFound()) {
               this.swapAndRun(var15.getSlot(), mode, action);
               return true;
            } else {
               Item[] var17 = new Item[]{item};
               SlotResult var21 = this.findInInventory(var17, (char)var11, var12);
               if (!var21.isFound()) {
                  return false;
               } else {
                  this.swapAndRun(var21.getSlot(), mode, action);
                  return true;
               }
            }
      }
   }

   public final void swapAndRun(int slot, @NotNull SwapMode mode, @NotNull Function0 action) {
      Intrinsics.checkNotNullParameter(mode, "mode");
      Intrinsics.checkNotNullParameter(action, "action");
      GuiMove.INSTANCE.setMacroActive(true);
      switch (InventoryUtil$WhenMappings.E[mode.ordinal()]) {
         case 1:
            if (Globals.getPlayer().getInventory().getSelectedSlot() != slot) {
               this.selectSlot(slot);
            }

            action.invoke();
            break;
         case 2:
            int var17 = Globals.getPlayer().getInventory().getSelectedSlot();
            if (B != slot) {
               this.selectSlot(slot);
            }

            action.invoke();
            if (B != var17) {
               this.selectSlot(var17);
            }
            break;
         case 3:
            Globals.getInteractionManager()
               .clickSlot(
                  Globals.getPlayer().currentScreenHandler.syncId,
                  slot,
                  Globals.getPlayer().getInventory().getSelectedSlot(),
                  SlotActionType.SWAP,
                  Globals.getPlayer()
               );
            action.invoke();
            break;
         case 4:
            Globals.getInteractionManager()
               .clickSlot(
                  Globals.getPlayer().currentScreenHandler.syncId,
                  slot,
                  Globals.getPlayer().getInventory().getSelectedSlot(),
                  SlotActionType.SWAP,
                  Globals.getPlayer()
               );
            action.invoke();
            Globals.getInteractionManager()
               .clickSlot(
                  Globals.getPlayer().currentScreenHandler.syncId,
                  slot,
                  Globals.getPlayer().getInventory().getSelectedSlot(),
                  SlotActionType.SWAP,
                  Globals.getPlayer()
               );
            break;
         case 5:
            SwapMode var18 = slot >= 9 ? SwapMode.INVENTORY_NORMAL : SwapMode.NORMAL;
            this.swapAndRun(slot, var18, action);
            break;
         case 6:
            SwapMode var14 = slot >= 9 ? SwapMode.INVENTORY_SILENT : SwapMode.SILENT;
            this.swapAndRun(slot, var14, action);
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      GuiMove.INSTANCE.setMacroActive(false);
   }

   public final void clickSlot(int slot) {
      Globals.getInteractionManager()
         .clickSlot(
            Globals.getPlayer().currentScreenHandler.syncId,
            slot < 9 ? slot + 36 : slot,
            Globals.getPlayer().getInventory().getSelectedSlot(),
            SlotActionType.SWAP,
            Globals.getPlayer()
         );
   }

   public final void selectSlot(int slot) {
      if ((0 <= slot ? slot < 9 : false) && Globals.getPlayer().getInventory().getSelectedSlot() != slot) {
         Globals.getPlayer().getInventory().setSelectedSlot(slot);
         ClientPlayerInteractionManager var10000 = Globals.getMc().interactionManager;
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type su.catlean.mixins.accessors.InteractionManagerAccessor");
         ((InteractionManagerAccessor)var10000).setCarriedIndex(slot);
         this.syncSelectedSlot(slot);
      }
   }

   public final void syncSelectedSlot(int slot) {
      if (B != slot) {
         ClientPlayNetworkHandler var10000 = Globals.getMc().getNetworkHandler();
         if (var10000 != null) {
            var10000.sendPacket(new UpdateSelectedSlotC2SPacket(slot));
         }
      }
   }

   public final int findElytra() {
      for (ItemStack var7 : ArmorUtil.getArmorItems(Globals.getPlayer())) {
         if (Intrinsics.areEqual(var7.getItem(), Items.ELYTRA) && var7.getDamage() < 430) {
            return -2;
         }
      }

      int var9 = -1;

      for (int var10 = 0; var10 < 36; var10++) {
         ItemStack var10000 = Globals.getPlayer().getInventory().getStack(var10);
         Intrinsics.checkNotNullExpressionValue(var10000, "getItem(...)");
         ItemStack var8 = var10000;
         if (Intrinsics.areEqual(var8.getItem(), Items.ELYTRA) && var8.getDamage() < 430) {
            var9 = var10;
            break;
         }
      }

      if (var9 < 9 && var9 != -1) {
         var9 += 36;
      }

      return var9;
   }

   @NotNull
   public final SlotResult R() {
      if (Globals.getMc().player == null) {
         return SlotResult.Companion.notFound();
      } else {
         int var12 = -1;
         float var13 = 1.0F;

         for (int var14 = 0; var14 < 9; var14++) {
            ItemStack var22;
            label33: {
               ClientPlayerEntity var10000 = Globals.getMc().player;
               if (var10000 != null) {
                  PlayerInventory var21 = var10000.getInventory();
                  if (var21 != null) {
                     var22 = var21.getStack(var14);
                     break label33;
                  }
               }

               var22 = null;
            }

            ItemStack var15 = var22;
            if (var15 != null && ItemUtil.isSword(var15)) {
               var22 = var15.getItem().getComponents().get(DataComponentTypes.MAX_DAMAGE);
               Intrinsics.checkNotNull(var22);
               float var16 = ((Number)var22).intValue();
               var16 += EnchantmentHelper.getLevel(
                  Globals.getWorld()
                     .getRegistryManager()
                     .getOptional(Enchantments.SHARPNESS.getRegistryRef())
                     .get()
                     .getEntry(Enchantments.SHARPNESS.getValue())
                     .get(),
                  var15
               );
               if (var16 > var13) {
                  var13 = var16;
                  var12 = var14;
               }
            }
         }

         if (var12 == -1) {
            return SlotResult.Companion.notFound();
         } else {
            ItemStack var10004 = Globals.getPlayer().getInventory().getStack(var12);
            Intrinsics.checkNotNullExpressionValue(var10004, "getItem(...)");
            ItemStack var17 = var10004;
            boolean var18 = true;
            return new SlotResult(var12, var18, var17);
         }
      }
   }

   @NotNull
   public final SlotResult b() {
      if (Globals.getMc().player == null) {
         return SlotResult.Companion.notFound();
      } else {
         ClientPlayerEntity var10000 = Globals.getMc().player;
         Intrinsics.checkNotNull(var10000);
         ItemStack var16 = var10000.getMainHandStack();
         Intrinsics.checkNotNullExpressionValue(var16, "getMainHandItem(...)");
         ItemStack var12 = var16;
         if (!ItemUtil.isSword(var12) && !ItemUtil.isPickaxe(var12) && !(var12.getItem() instanceof AxeItem) && !(var12.getItem() instanceof ShovelItem)) {
            return this.findInHotbar(InventoryUtil::isTool);
         } else {
            ClientPlayerEntity var10002 = Globals.getMc().player;
            Intrinsics.checkNotNull(var10002);
            int var17 = var10002.getInventory().getSelectedSlot();
            ClientPlayerEntity var10004 = Globals.getMc().player;
            Intrinsics.checkNotNull(var10004);
            ItemStack var18 = var10004.getMainHandStack();
            Intrinsics.checkNotNullExpressionValue(var18, "getMainHandItem(...)");
            ItemStack var13 = var18;
            boolean var14 = true;
            int var15 = var17;
            return new SlotResult(var15, var14, var13);
         }
      }
   }

   @NotNull
   public final SlotResult g() {
      if (Globals.getMc().player == null) {
         return SlotResult.Companion.notFound();
      } else {
         int var8 = -1;
         float var9 = 1.0F;

         for (int var10 = 0; var10 < 9; var10++) {
            ItemStack var18;
            label38: {
               ClientPlayerEntity var10000 = Globals.getMc().player;
               if (var10000 != null) {
                  PlayerInventory var17 = var10000.getInventory();
                  if (var17 != null) {
                     var18 = var17.getStack(var10);
                     break label38;
                  }
               }

               var18 = null;
            }

            ItemStack var11 = var18;
            if (var11 != null && var11.getItem() instanceof AxeItem) {
               Item var19 = var11.getItem();
               Intrinsics.checkNotNull(var19, "null cannot be cast to non-null type net.minecraft.world.item.AxeItem");
               AxeItem var12 = (AxeItem)var19;
               Integer var20 = var12.getComponents().get(DataComponentTypes.MAX_DAMAGE);
               float var13 = (float)(var20 != null ? var20 : 0)
                  + EnchantmentHelper.getLevel(Globals.getWorld().getRegistryManager().getOptionalEntry(Enchantments.SHARPNESS).get(), var11);
               if (var13 > var9) {
                  var9 = var13;
                  var8 = var10;
               }
            }
         }

         if (var8 == -1) {
            return SlotResult.Companion.notFound();
         } else {
            ClientPlayerEntity var10004 = Globals.getMc().player;
            Intrinsics.checkNotNull(var10004);
            ItemStack var21 = var10004.getInventory().getStack(var8);
            Intrinsics.checkNotNullExpressionValue(var21, "getItem(...)");
            ItemStack var14 = var21;
            boolean var15 = true;
            return new SlotResult(var8, var15, var14);
         }
      }
   }

   public final void swapSlots(int slot, int factor) {
      int var12 = Globals.getPlayer().getInventory().getSelectedSlot();
      if (slot != -1) {
         this.selectSlot(slot);
         if (Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(slot).getItem(), Items.EXPERIENCE_BOTTLE)) {
            int var13 = 1;
            if (var13 <= factor) {
               while (true) {
                  InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0F, 0.0F, 6, null);
                  if (var13 == factor) {
                     break;
                  }

                  var13++;
               }
            }
         } else {
            InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0F, 0.0F, 6, null);
         }

         PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
         this.selectSlot(var12);
      }
   }

   public static void swapSlots$default(InventoryUtil var0, int var1, Object var2) {
      byte var7 = 1;
      var0.swapSlots(var1, var7);
   }

   public final void swapToOffhand(int slot) {
      byte var13 = 0;
      byte var14 = 0;
      byte var15 = 0;
      long var2 = ((long)var13 << 32 | (long)var14 << 48 >>> 32 | (long)var15 << 48 >>> 48) ^ a;
      Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(8193654499917463629L, var2);
      GuiMove.INSTANCE.setMacroActive(true);
      Module[] var12 = var10000;
      if (var12 == null) {
         if (slot != -1) {
            InteractionUtil.clickSlot$default(
               slot,
               Globals.getPlayer().getInventory().getSelectedSlot(),
               SlotActionType.SWAP,
               false,
               zkmBootstrap2<"g">(10147, 6194611116646913806L ^ var2),
               null
            );
            InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0F, 0.0F, zkmBootstrap2<"g">(4509, 1078833670573089080L ^ var2), null);
            PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
            InteractionUtil.clickSlot$default(
               slot,
               Globals.getPlayer().getInventory().getSelectedSlot(),
               SlotActionType.SWAP,
               false,
               zkmBootstrap2<"g">(10147, 6194611116646913806L ^ var2),
               null
            );
         }

         GuiMove.INSTANCE.setMacroActive(false);
      }
   }

   public final void swapWithHotbar(int from, int to) {
      int var12 = Globals.getPlayer().getInventory().getSelectedSlot() % 8 + 1;
      boolean var13 = Globals.getMc().currentScreen instanceof ShulkerBoxScreen || Globals.getMc().currentScreen instanceof GenericContainerScreen;
      InteractionUtil.clickSlot$default(from, var12, SlotActionType.SWAP, false, 8, null);
      InteractionUtil.clickSlot$default(to, var12, SlotActionType.SWAP, false, 8, null);
      InteractionUtil.clickSlot(from, var12, SlotActionType.SWAP, !var13);
   }

   private static final boolean findInHotbar$lambda$0(List var0, ItemStack var1) {
      Intrinsics.checkNotNullParameter(var1, "it");
      return var0.contains(var1.getItem());
   }

   private static final boolean findInHotbar$lambda$0(Item[] var0, ItemStack var1) {
      Intrinsics.checkNotNullParameter(var1, "it");
      return ArraysKt.contains(var0, var1.getItem());
   }

   private static final boolean findAnyInInventory$lambda$0(List var0, ItemStack var1) {
      Intrinsics.checkNotNullParameter(var1, "it");
      return var0.contains(var1.getItem());
   }

   private static final boolean findInInventory$lambda$0(Item[] var0, ItemStack var1) {
      Intrinsics.checkNotNullParameter(var1, "it");
      return ArraysKt.contains(var0, var1.getItem());
   }

   private static final boolean isTool(ItemStack var0) {
      Intrinsics.checkNotNullParameter(var0, "itemStack");
      return ItemUtil.isSword(var0) || ItemUtil.isPickaxe(var0) || var0.getItem() instanceof AxeItem || var0.getItem() instanceof ShovelItem;
   }

   static {
      long var20 = a ^ 91196441282260L;
      zkmSetFlag(0);
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[21];
      int var16 = 0;
      String var15 = "zP8°\u0007àXc\u008c\u000fGM¸\u001adH\u0010æzÅ½Ì\t;pè]f\u0005p\u0088B\b \u0011à-Ô\u009eXû_øªýCÜ¿oVX xuæ^Þ0\u0006\u0011ÂçäæÀ\u000f\u0010Î§\u0098'ía\u008e/¤H\u0002(\u008a*.0\u0010\u0003|Ùä\u007fM[\u008dù\u0084×¤üq\u009aü\u0018N²\u001b}\u008cè\u0016¡¿6D\u001bþ¾gÕ>ºßÔïHêó\u0018<\u0091\u0087«\u0081\u0018<±\u00adß\u0089®JaÁ(Z\u0016\u0092\f\u0015D-X\u0018\u008c\u0011Ô_\no\u001e~?Îßq¡ÅôRâ\u0010ùe.KRæ((Ü\u008a\u0015\u0098à¼\u001dK\u0092t\u008dê J\u0018\u001c\u0081ØçöwÒö\t\u0097\u0001Üë\u0000\u00137¶3ªFi\u0095\u0005î\u00104\u0096ÿ\u0097\u008cß¿*Á\u009diFÒª\u0096æ a¸ÊÚSñSyýN\u00037åd;ù*x\u00ad¢;#\u009dà\u0092%\u0091°$þ0¡\u0010Ý\u0011MÂIIB\u009cjKæ\u0087Æ¦\u001a\t\u0010ã\u001esä\u0094ØB\n\u0013©¬\u0097Ý>ÿ\u0081(õ,âZ¦Æ\u0016ü«yüù'\u0002¹ ÕE\u008bO£ûÕoÀo\u0099×þÏ\u0080¥ß\u000f2\u0007|ZP\u0010\u0010q°×w\u0080µ¯adÕÈÍ.Á¢]\u0098^`×âgÙF'>|P\u007f9ýè\u0001\u0082 \u009aun?m,ÜçÁÙfT\u0082ð\u0019\\LÕ}C>ÓKÚÛ~\u0006x\u007f¯v¹b\u0010\u00999\u0002ðÕº\u008fç]?Éj\u0000mÊôRµ,¢Â:4\u001fjúpù@§©K\u009f \u0012È\fÏ¹c\n½\u0094ãLæ\u0017Ce\u0004\u008f&èY\u0086\u008bº\u000b&³ùÄ\u0083\u0089ì\u0002\u0004³\u0081\u009cpXû\u009d}Õ:\u000f\u009d\u0096ê\u0087*@mÜÖ¤¨E|xÙtð,\rµ\u001e$\u0010ek~\u0014À\u001b/\u0088}\u0082%pF'O^\u0010\u008a\bI\u0086\\5Î4Ç7\u009c\u0089?³\n\u008bp\u001en\u0007Þ \u0000JÛÚWô{Ë¡\u000e¦sêY²ZS/G\u0013ñ\u001fk¦mÞ¶hô¦{ý®\u0093É+P¹m\u0010Ë\u009eh\u008bÀ%\u0089E\u001crÎûFx\u0012èï\t9b\f¸æ%Åz\u009c\u0016\u0004\u0010¾0è\u001fo³ÂªFã¢\u0002¾ß~`z°\u0080Ê3ß\u008d{\u0092µ@{Ú\u008d°PÈ½W®Ú";
      short var17 = 658;
      char var14 = 16;
      int var24 = -1;

      label54:
      while (true) {
         String var25 = var15.substring(++var24, var24 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var25.getBytes("ISO-8859-1"));
            String var36 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var36;
                  if ((var24 += var14) >= var17) {
                     b = var18;
                     c = new String[21];
                     g = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[15];
                     int var3 = 0;
                     String var4 = "Ø_|Á¬½\u00912\u0086÷~ñq\u0019ñI*\u009e\u0007Á\u0084:f.\u00196bÕê.\u00931ÿ\u00ad\txüË¾\u0090°\u000eÂIÛ\u009aNl÷.áð¡\u0085ÀÙ<Il\u0089Hxß´\u0086\u0099[t\\\u00858\u0097@m\u0097'\u0092\u0085?\u0087%\u0081òñ¿\u001d¥öKê3Á\u009c3\u0089á¾\u009aÜC\u0010T\u008a\u0096";
                     byte var5 = 104;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var28 = var6;
                        var10001 = var3++;
                        long var40 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var43 = -1;

                        while (true) {
                           long var8 = var40;
                           byte[] var10 = var0.doFinal(
                              new byte[]{
                                 (byte)(var8 >>> 56),
                                 (byte)(var8 >>> 48),
                                 (byte)(var8 >>> 40),
                                 (byte)(var8 >>> 32),
                                 (byte)(var8 >>> 24),
                                 (byte)(var8 >>> 16),
                                 (byte)(var8 >>> 8),
                                 (byte)var8
                              }
                           );
                           long var45 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var43) {
                              case 0:
                                 var28[var10001] = var45;
                                 if (var2 >= var5) {
                                    e = var6;
                                    f = new Integer[15];
                                    INSTANCE = new InventoryUtil();
                                    B = -1;
                                    O = -1;
                                    return;
                                 }
                                 break;
                              default:
                                 var28[var10001] = var45;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "\u00ad\u0085¸\u0005b\u0083Óþ\b}\u0002\\M\u008aAñ";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var34 = var2;
                           var2 += 8;
                           var7 = var4.substring(var34, var2).getBytes("ISO-8859-1");
                           var28 = var6;
                           var10001 = var3++;
                           var40 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var43 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var24);
                  break;
               default:
                  var18[var16++] = var36;
                  if ((var24 += var14) < var17) {
                     var14 = var15.charAt(var24);
                     continue label54;
                  }

                  var15 = "Ë\u009fZåÚ\u0086\u0003\u009c-P/§2ÙdC\u0010\u0081\u0019¼2¼\u0090´Ä\u001a«ôÜ\u0087M\u0002\u0097";
                  var17 = 33;
                  var14 = 16;
                  var24 = -1;
            }

            var25 = var15.substring(++var24, var24 + var14);
            var10001 = 0;
         }
      }
   }

   public static void zkmSetFlag(int var0) {
      r = var0;
   }

   public static int zkmGetFlag() {
      return r;
   }

   public static int zkmIntConstant() {
      return 96;
   }

   private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException var0) {
      return var0;
   }

   private static String zkmDecodeUtf8(byte[] var0) {
      int var1 = 0;
      int var2;
      char[] var3 = new char[var2 = var0.length];

      for (int var4 = 0; var4 < var2; var4++) {
         int var5;
         if ((var5 = 255 & var0[var4]) < 192) {
            var3[var1++] = (char)var5;
         } else if (var5 < 224) {
            char var6 = (char)((char)(var5 & 31) << 6);
            byte var8 = var0[++var4];
            var6 = (char)(var6 | (char)(var8 & 63));
            var3[var1++] = var6;
         } else if (var4 < var2 - 2) {
            char var12 = (char)((char)(var5 & 15) << '\f');
            byte var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63) << 6);
            var9 = var0[++var4];
            var12 = (char)(var12 | (char)(var9 & 63));
            var3[var1++] = var12;
         }
      }

      return new String(var3, 0, var1);
   }

   private static String zkmDecryptString(int var0, long var1) {
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 16882;
      if (c[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().threadId();
            var4 = (Object[])d.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               d.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("su/catlean/gg", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = b[var5].getBytes("ISO-8859-1");
         c[var5] = zkmDecodeUtf8(((Cipher)var4[0]).doFinal(var9));
      }

      return c[var5];
   }

   private static Object zkmDispatch(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
      int var4 = (Integer)var3[0];
      long var5 = (Long)var3[1];
      String var7 = zkmDecryptString(var4, var5);
      MethodHandle var8 = MethodHandles.constant(String.class, var7);
      var1.setTarget(MethodHandles.dropArguments(var8, 0, int.class, long.class));
      return var7;
   }

   private static CallSite zkmBootstrap(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments("zkmDispatch".asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("su/catlean/gg" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 81;
      if (f[var3] == null) {
         byte[] var4 = new byte[]{
            (byte)(var1 >>> 56),
            (byte)(var1 >>> 48),
            (byte)(var1 >>> 40),
            (byte)(var1 >>> 32),
            (byte)(var1 >>> 24),
            (byte)(var1 >>> 16),
            (byte)(var1 >>> 8),
            (byte)var1
         };
         long var5 = e[var3];
         byte[] var7 = new byte[]{
            (byte)(var5 >>> 56),
            (byte)(var5 >>> 48),
            (byte)(var5 >>> 40),
            (byte)(var5 >>> 32),
            (byte)(var5 >>> 24),
            (byte)(var5 >>> 16),
            (byte)(var5 >>> 8),
            (byte)var5
         };
         Long var8 = Thread.currentThread().threadId();
         Object[] var9 = (Object[])g.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               g.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/gg", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         f[var3] = var15;
      }

      return f[var3];
   }

   private static int b(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
      int var4 = (Integer)var3[0];
      long var5 = (Long)var3[1];
      int var7 = zkmDecryptInt(var4, var5);
      MethodHandle var8 = MethodHandles.constant(int.class, var7);
      var1.setTarget(MethodHandles.dropArguments(var8, 0, int.class, long.class));
      return var7;
   }

   private static CallSite zkmBootstrap2(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments("b".asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("su/catlean/gg" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
