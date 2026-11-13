package su.catlean.module.world;

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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.Rotation$Companion;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class AutoLand extends Module {
   @NotNull
   public static final AutoLand INSTANCE;
   static final KProperty[] delegatedProperties;
   @NotNull
   private static final ModeSetting switchMode$delegate;
   @NotNull
   private static final IntSetting delay$delegate;
   @NotNull
   private static final SettingGroup group$delegate;
   @NotNull
   private static final BooleanSetting wheat$delegate;
   @NotNull
   private static final BooleanSetting carrot$delegate;
   @NotNull
   private static final BooleanSetting potato$delegate;
   @NotNull
   private static final BooleanSetting melon$delegate;
   @NotNull
   private static final BooleanSetting pumpkin$delegate;
   @NotNull
   private static final BooleanSetting beetRoot$delegate;
   @NotNull
   private static final BooleanSetting sugarCane$delegate;
   @NotNull
   private static final BooleanSetting netherWart$delegate;
   @NotNull
   private static final TickTimer tickTimer;
   private static final long c = ZkmKeys.a(4533212411249400744L, -1052339863790756425L, MethodHandles.lookup().lookupClass()).a(275190483200967L);
   private static final String[] e;
   private static final String[] f;
   private static final Map g = new HashMap(13);
   private static final long[] h;
   private static final Integer[] j;
   private static final Map l;

   private AutoLand() {
      super("auto-land", Categories.getWorld(), null, null);
   }

   private final SwapMode getSwitchMode() {
      return (SwapMode)switchMode$delegate.getValue(this, delegatedProperties[0]);
   }

   private final void setSwitchMode(SwapMode var1) {
      switchMode$delegate.setValue(this, delegatedProperties[0], var1);
   }

   private final int getDelay() {
      return ((Number)delay$delegate.getValue(this, delegatedProperties[1])).intValue();
   }

   private final void setDelay(int var1) {
      delay$delegate.setValue(this, delegatedProperties[1], var1);
   }

   private final SettingParent getGroup() {
      return (SettingParent)group$delegate.getValue(this, delegatedProperties[2]);
   }

   private final boolean getWheat() {
      return (Boolean)wheat$delegate.getValue(this, delegatedProperties[3]);
   }

   private final void setWheat(boolean var1) {
      wheat$delegate.setValue(this, delegatedProperties[3], var1);
   }

   private final boolean getCarrot() {
      return (Boolean)carrot$delegate.getValue(this, delegatedProperties[4]);
   }

   private final void setCarrot(boolean var1) {
      carrot$delegate.setValue(this, delegatedProperties[4], var1);
   }

   private final boolean getPotato() {
      return (Boolean)potato$delegate.getValue(this, delegatedProperties[5]);
   }

   private final void setPotato(boolean var1) {
      potato$delegate.setValue(this, delegatedProperties[5], var1);
   }

   private final boolean getMelon() {
      return (Boolean)melon$delegate.getValue(this, delegatedProperties[6]);
   }

   private final void setMelon(boolean var1) {
      long var4 = 0L;
      var4 = c ^ var4;
      melon$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"j">(25345, 6930151623309121021L ^ var4)], var1);
   }

   private final boolean getPumpkin() {
      return (Boolean)pumpkin$delegate.getValue(this, delegatedProperties[7]);
   }

   private final void setPumpkin(boolean var1) {
      long var4 = 0L;
      var4 = c ^ var4;
      pumpkin$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"j">(23154, 4813213849160472845L ^ var4)], var1);
   }

   private final boolean getBeetRoot() {
      return (Boolean)beetRoot$delegate.getValue(this, delegatedProperties[8]);
   }

   private final void setBeetRoot(boolean var1) {
      long var4 = 0L;
      var4 = c ^ var4;
      beetRoot$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"j">(3828, 4774603913574004597L ^ var4)], var1);
   }

   private final boolean getSugarCane() {
      return (Boolean)sugarCane$delegate.getValue(this, delegatedProperties[9]);
   }

   private final void setSugarCane(boolean var1) {
      byte var6 = 0;
      byte var7 = 0;
      byte var8 = 0;
      long var2 = ((long)var6 << 32 | (long)var7 << 48 >>> 32 | (long)var8 << 48 >>> 48) ^ c;
      sugarCane$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"j">(27308, 7152395942421523271L ^ var2)], var1);
   }

   private final boolean getNetherWart() {
      return (Boolean)netherWart$delegate.getValue(this, delegatedProperties[10]);
   }

   private final void setNetherWart(boolean var1) {
      byte var6 = 0;
      byte var7 = 0;
      byte var8 = 0;
      long var2 = ((long)var6 << 32 | (long)var7 << 48 >>> 32 | (long)var8 << 48 >>> 48) ^ c;
      netherWart$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"j">(32108, 8184852908661363590L ^ var2)], var1);
   }

   @Flow
   private final void onPlayerUpdate(PlayerUpdateEvent var1) {
      long var2 = c ^ 97840224258471L;
      long var4 = var2 ^ 135995930341275L;
      long var6 = var2 ^ 92144217238305L;
      long var8 = var2 ^ 38201339866146L;
      if (tickTimer.hasPassedAndReset(this.getDelay())) {
         if (this.W(var4)) {
            return;
         }

         if (this.getSugarCane() && this.M(var8)) {
            return;
         }

         if (this.getNetherWart()) {
            this.Q(var6);
         }
      }
   }

   private final boolean W(long var1) {
      var1 = c ^ var1;
      long var15 = var1 ^ 13358124190374L;
      Iterable var23 = PlaceManager.INSTANCE.findReachableBlocks();
      int var21 = AutoLand$2.c();
      Iterable var25 = var23;
      var var26 = new ArrayList();
      Iterator var28 = var23.iterator();

      while (true) {
         if (var28.hasNext()) {
            Object var29 = var28.next();
            BlockPos var30 = (BlockPos)var29;
            Block var10000 = Globals.getWorld().getBlockState(var30).getBlock();
            Intrinsics.checkNotNullExpressionValue(var10000, "getBlock(...)");
            Block var32 = var10000;
            int var40 = Intrinsics.areEqual(var32, Blocks.FARMLAND);
            if (var21 != 0) {
               break;
            }

            label78: {
               label88: {
                  if (var21 == 0) {
                     if (var40 == 0) {
                        break label88;
                     }

                     double var45;
                     var40 = (
                              var45 = Globals.getPlayer().getEyePos().distanceTo(var30.toCenterPos().add(0.0, 0.4375, 0.0))
                                 - Globals.getPlayer().getBlockInteractionRange()
                           )
                           == 0.0
                        ? 0
                        : (var45 < 0.0 ? -1 : 1);
                  }

                  if (var21 == 0) {
                     if (var40 >= 0) {
                        break label88;
                     }

                     var40 = Globals.getWorld().getBlockState(var30.up()).isAir();
                  }

                  if (var21 != 0) {
                     break label78;
                  }

                  if (var40 != 0) {
                     var40 = 1;
                     break label78;
                  }
               }

               var40 = 0;
            }

            if (var40 == 0) {
               continue;
            }

            var26.add(var29);
            if (var21 == 0) {
               continue;
            }
         }

         var23 = var26;
         var25 = var23;
         var26 = new ArrayList(CollectionsKt.collectionSizeOrDefault(var23, 10));
         boolean var41 = false;
         break;
      }

      var28 = var25.iterator();

      while (true) {
         if (var28.hasNext()) {
            Object var38 = var28.next();
            BlockPos var39 = (BlockPos)var38;
            var26.add(new BlockHitResult(var39.toCenterPos().add(0.0, 0.4375, 0.0), Direction.UP, var39, false));
            if (var21 != 0) {
               break;
            }

            if (var21 == 0) {
               continue;
            }
         }

         var23 = var26;
         break;
      }

      List var22 = CollectionsKt.sortedWith(var23, new AutoLand$1());
      List var35 = this.i();
      SlotResult var24 = InventoryUtil.INSTANCE.findItem(var15, AutoLand::W, this.getSwitchMode());
      boolean var42 = var22.isEmpty();
      if (var21 == 0) {
         label45:
         if (!var42) {
            Object var43 = var24;
            if (var21 == 0) {
               if (!var24.isFound()) {
                  break label45;
               }

               var43 = CollectionsKt.first(var22);
            }

            BlockHitResult var36 = (BlockHitResult)var43;
            RotationManager var44 = RotationManager.INSTANCE;
            Rotation$Companion var10003 = Rotation.Companion;
            Vec3d var10004 = var36.getPos();
            Intrinsics.checkNotNullExpressionValue(var10004, "getLocation(...)");
            var44.submit(new ActionRotationRequest(var10003.to(var10004), AutoLand::G, null));
            return true;
         }

         var42 = false;
      }

      return var42;
   }

   private final boolean Q(long var1) {
      var1 = c ^ var1;
      long var13 = var1 ^ 39976825196060L;
      Iterable var21 = PlaceManager.INSTANCE.findReachableBlocks();
      int var10000 = AutoLand$2.c();
      Iterable var23 = var21;
      var var24 = new ArrayList();
      Iterator var26 = var21.iterator();
      int var19 = var10000;

      while (true) {
         if (var26.hasNext()) {
            Object var27 = var26.next();
            BlockPos var28 = (BlockPos)var27;
            Block var37 = Globals.getWorld().getBlockState(var28).getBlock();
            Intrinsics.checkNotNullExpressionValue(var37, "getBlock(...)");
            Block var30 = var37;
            var10000 = Intrinsics.areEqual(var30, Blocks.SOUL_SAND);
            if (var19 != 0) {
               break;
            }

            label78: {
               label88: {
                  if (var19 == 0) {
                     if (var10000 == 0) {
                        break label88;
                     }

                     double var43;
                     var10000 = (
                              var43 = Globals.getPlayer().getEyePos().distanceTo(var28.toCenterPos().add(0.0, 0.5, 0.0))
                                 - Globals.getPlayer().getBlockInteractionRange()
                           )
                           == 0.0
                        ? 0
                        : (var43 < 0.0 ? -1 : 1);
                  }

                  if (var19 == 0) {
                     if (var10000 >= 0) {
                        break label88;
                     }

                     var10000 = Globals.getWorld().getBlockState(var28.up()).isAir();
                  }

                  if (var19 != 0) {
                     break label78;
                  }

                  if (var10000 != 0) {
                     var10000 = 1;
                     break label78;
                  }
               }

               var10000 = 0;
            }

            if (var10000 == 0) {
               continue;
            }

            var24.add(var27);
            if (var19 == 0) {
               continue;
            }
         }

         var21 = var24;
         var23 = var21;
         var24 = new ArrayList(CollectionsKt.collectionSizeOrDefault(var21, 10));
         boolean var39 = false;
         break;
      }

      var26 = var23.iterator();

      while (true) {
         if (var26.hasNext()) {
            Object var35 = var26.next();
            BlockPos var36 = (BlockPos)var35;
            var24.add(new BlockHitResult(var36.toCenterPos().add(0.0, 0.5, 0.0), Direction.UP, var36, false));
            if (var19 != 0) {
               break;
            }

            if (var19 == 0) {
               continue;
            }
         }

         var21 = var24;
         break;
      }

      List var20 = CollectionsKt.sortedWith(var21, new AutoLand$3());
      SlotResult var33 = InventoryUtil.INSTANCE.findItem(var13, AutoLand::X, this.getSwitchMode());
      boolean var40 = var20.isEmpty();
      if (var19 == 0) {
         label45:
         if (!var40) {
            Object var41 = var33;
            if (var19 == 0) {
               if (!var33.isFound()) {
                  break label45;
               }

               var41 = CollectionsKt.first(var20);
            }

            BlockHitResult var22 = (BlockHitResult)var41;
            RotationManager var42 = RotationManager.INSTANCE;
            Rotation$Companion var10003 = Rotation.Companion;
            Vec3d var10004 = var22.getPos();
            Intrinsics.checkNotNullExpressionValue(var10004, "getLocation(...)");
            var42.submit(new ActionRotationRequest(var10003.to(var10004), AutoLand::P, null));
            return true;
         }

         var40 = false;
      }

      return var40;
   }

   private final boolean M(long var1) {
      var1 = c ^ var1;
      long var13 = var1 ^ 93631804066079L;
      Iterable var21 = PlaceManager.INSTANCE.findReachableBlocks();
      var var24 = new ArrayList();

      for (Object var27 : var21) {
         boolean var52;
         label98: {
            BlockPos var28 = (BlockPos)var27;
            Block var10000 = Globals.getWorld().getBlockState(var28).getBlock();
            Intrinsics.checkNotNullExpressionValue(var10000, "getBlock(...)");
            Block var30 = var10000;
            if (Intrinsics.areEqual(var30, Blocks.SAND)
               || Intrinsics.areEqual(var30, Blocks.DIRT)
               || Intrinsics.areEqual(var30, Blocks.GRASS_BLOCK)
               || Intrinsics.areEqual(var30, Blocks.RED_SAND)) {
               Iterable var31 = AutoLand$EntriesMappings.enumEntries;
               var var34 = new ArrayList();

               for (Object var37 : var31) {
                  Direction var38 = (Direction)var37;
                  if (var38 != Direction.UP && var38 != Direction.DOWN) {
                     var34.add(var37);
                  }
               }

               var31 = var34;
               if (var31 instanceof Collection && ((Collection)var31).isEmpty()) {
                  var52 = false;
               } else {
                  Iterator var33 = var31.iterator();

                  while (true) {
                     if (!var33.hasNext()) {
                        var52 = false;
                        break;
                     }

                     Object var50 = var33.next();
                     Direction var35 = (Direction)var50;
                     if (Intrinsics.areEqual(Globals.getWorld().getBlockState(var28.offset(var35)).getBlock(), Blocks.WATER)) {
                        var52 = true;
                        break;
                     }
                  }
               }

               if (var52
                  && Globals.getPlayer().getEyePos().distanceTo(var28.toCenterPos().add(0.0, 0.5, 0.0)) < Globals.getPlayer().getBlockInteractionRange()
                  && Globals.getWorld().getBlockState(var28.up()).isAir()) {
                  var52 = true;
                  break label98;
               }
            }

            var52 = false;
         }

         if (var52) {
            var24.add(var27);
         }
      }

      var21 = var24;
      var24 = new ArrayList(CollectionsKt.collectionSizeOrDefault(var21, 10));

      for (Object var47 : var21) {
         BlockPos var48 = (BlockPos)var47;
         var24.add(new BlockHitResult(var48.toCenterPos().add(0.0, 0.5, 0.0), Direction.UP, var48, false));
      }

      var21 = var24;
      List var20 = CollectionsKt.sortedWith(var21, new AutoLand$2());
      SlotResult var44 = InventoryUtil.INSTANCE.findItem(var13, AutoLand::r, this.getSwitchMode());
      if (!var20.isEmpty() && var44.isFound()) {
         BlockHitResult var22 = CollectionsKt.first(var20);
         RotationManager var53 = RotationManager.INSTANCE;
         Rotation$Companion var10003 = Rotation.Companion;
         Vec3d var10004 = var22.getPos();
         Intrinsics.checkNotNullExpressionValue(var10004, "getLocation(...)");
         var53.submit(new ActionRotationRequest(var10003.to(var10004), AutoLand::H, null));
         return true;
      } else {
         return false;
      }
   }

   private final List i() {
      int var10000 = AutoLand$2.c();
      List var16 = new ArrayList();
      int var15 = var10000;
      boolean var17 = this.getWheat();
      if (var15 == 0) {
         if (var17) {
            Item var10001 = Items.WHEAT_SEEDS;
            Intrinsics.checkNotNullExpressionValue(Items.WHEAT_SEEDS, "WHEAT_SEEDS");
            var16.add(var10001);
         }

         var17 = this.getCarrot();
      }

      if (var15 == 0) {
         if (var17) {
            Item var18 = Items.CARROT;
            Intrinsics.checkNotNullExpressionValue(Items.CARROT, "CARROT");
            var16.add(var18);
         }

         var17 = this.getPotato();
      }

      if (var15 == 0) {
         if (var17) {
            Item var19 = Items.POTATO;
            Intrinsics.checkNotNullExpressionValue(Items.POTATO, "POTATO");
            var16.add(var19);
         }

         var17 = this.getPumpkin();
      }

      if (var15 == 0) {
         if (var17) {
            Item var20 = Items.PUMPKIN_SEEDS;
            Intrinsics.checkNotNullExpressionValue(Items.PUMPKIN_SEEDS, "PUMPKIN_SEEDS");
            var16.add(var20);
         }

         var17 = this.getMelon();
      }

      if (var15 == 0) {
         if (var17) {
            Item var21 = Items.MELON_SEEDS;
            Intrinsics.checkNotNullExpressionValue(Items.MELON_SEEDS, "MELON_SEEDS");
            var16.add(var21);
         }

         var17 = this.getBeetRoot();
      }

      if (var15 == 0 && var17) {
         Item var22 = Items.BEETROOT_SEEDS;
         Intrinsics.checkNotNullExpressionValue(Items.BEETROOT_SEEDS, "BEETROOT_SEEDS");
         var16.add(var22);
      }

      return var16;
   }

   private static final boolean W(List var0, ItemStack var1) {
      Intrinsics.checkNotNullParameter(var1, "it");
      return var0.contains(var1.getItem());
   }

   private static final Unit B(BlockHitResult var0) {
      Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, var0);
      Globals.getPlayer().swingHand(Hand.MAIN_HAND);
      return Unit.INSTANCE;
   }

   private static final Unit G(SlotResult var0, BlockHitResult var1) {
      InventoryUtil var10000 = InventoryUtil.INSTANCE;
      int var10001 = var0.getSlot();
      SwapMode var10002 = INSTANCE.getSwitchMode();
      Function0 var8 = AutoLand::B;
      SwapMode var9 = var10002;
      int var10 = var10001;
      var10000.swapAndRun(var10, var9, var8);
      return Unit.INSTANCE;
   }

   private static final boolean X(ItemStack var0) {
      Intrinsics.checkNotNullParameter(var0, "it");
      return Intrinsics.areEqual(var0.getItem(), Items.NETHER_WART);
   }

   private static final Unit f(BlockHitResult var0) {
      Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, var0);
      Globals.getPlayer().swingHand(Hand.MAIN_HAND);
      return Unit.INSTANCE;
   }

   private static final Unit P(SlotResult var0, BlockHitResult var1) {
      InventoryUtil var10000 = InventoryUtil.INSTANCE;
      int var10001 = var0.getSlot();
      SwapMode var10002 = INSTANCE.getSwitchMode();
      Function0 var8 = AutoLand::f;
      SwapMode var9 = var10002;
      int var10 = var10001;
      var10000.swapAndRun(var10, var9, var8);
      return Unit.INSTANCE;
   }

   private static final boolean r(ItemStack var0) {
      Intrinsics.checkNotNullParameter(var0, "it");
      return Intrinsics.areEqual(var0.getItem(), Items.SUGAR_CANE);
   }

   private static final Unit P(BlockHitResult var0) {
      Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, var0);
      Globals.getPlayer().swingHand(Hand.MAIN_HAND);
      return Unit.INSTANCE;
   }

   private static final Unit H(SlotResult var0, BlockHitResult var1) {
      InventoryUtil var10000 = InventoryUtil.INSTANCE;
      int var10001 = var0.getSlot();
      SwapMode var10002 = INSTANCE.getSwitchMode();
      Function0 var8 = AutoLand::P;
      SwapMode var9 = var10002;
      int var10 = var10001;
      var10000.swapAndRun(var10, var9, var8);
      return Unit.INSTANCE;
   }

   static {
      long var20 = c ^ 93203169786113L;
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[46];
      int var16 = 0;
      String var15 = "I\u00977°ÿûeÌ\u0002o $-â[\n¼<Þ\u000bR,©d  \u0080=ÑÙ\u0080\b¤ÐJ¢¸çù$.\u007fÇ¿Ï\u0004\u00150±¾áß\u0016ã\u0094\u0093|(\u008f¬ô\u007fûØ.©È6\u0086Í\u0090]\u00ad\u009b<R\u008fßÓ\u0084A\u0003\u0005.\t\u0000\u0005\u0091`\u000eÎË¡\u0013\u0096\u0003Î\f0#È¥\bÞcNéGýpÍ\u0086õ\u0003^\u008a½vÄ|ÁB\u009cêÊ^&ó\u0014µm»ÄÛ\u009bM(Ùé\u008d¥\u008cõ¿9±L\u0010|\rF\u00adålÉSU\u008d\u0096äÉä\u0010S\u0010\u001bs\f¸Ð$\fÝXÛÅcWðÝ{ Ûþ\u0096\u0083\u0096YJ\u0081§\u0006.¤\t[H=U\u0091áÆ¨÷gwÏoü¡0f¿d\u0018\u000fo\u0091f\u001e\u008d\u0086(Q\u0091`åp/¯j³ÆY\u001d¸GE_\u0018\u009fO]æáZª,Á\u001c)\u0080\u009dz@\u008fð»R\u0007!DQ\u0081\u0010R\u0091Ø\tÐ\u0091\u0088±ÕÃgC\u0004\u0012\u000b´\u0010è¢¾·÷¶Äd\u0019\u007f,îôdP\u0004\u0018Ðulg[¹ÿ\u0010¦\u0013¶¯ëÄ\u0090\u000fcOoûß\u009bÀ\u001d æÕ)P`E\bÿú\u0002Îô\u0081¸@;\f\u0081?ÝÐ\u00138ÕÊþ\u009añ\u008ayìt\u0010\n6J©&\u0086\u0001{²\u000e\u0085ñt_v=(\u0011;\u0088Ü~\fÎ3G½zC\u000bª¢¥õV¥\u008eÁQ\u0090cè¥õ\f õ$~¾üp\u009få-42\u00106(Ê\u0017Ù\u009a¡\u0018ÿ#\u0015Mª(X)\u0010ëUË\u0011\u008ao\u0094\u001f²CÂ#¹\u008e+x\u0010\u0093V3ø\u0098db@¤=\u009a\u001eY§oS ·c5yäwsm¬ò\u001fX'±Gm¼ëÚaaX\u0095\u008aæßÇ\u008a`Èn \u0010ü¾\u0094úß\"æ¸\u0007;Á\u0088-¬\t} Ö¿¯]ÌÍ\u0082\u008bÎ_^·HÝ\u0093æx»:W¥c#ão8\u0094U\u0087V#\u0019\u0018Mqa±=ãÀ/à·¯·y\f7ÛT\u00189²\u0097\u0002²\u008f\u0018Ýl¹6\u0010áMwÖ\u0096*v½\u00adj¥i\u0002¾\u009c\u0099HKP ;\u001e\u0019ØÛ\u008c÷%®\u0010È[Áo\u0085ÔÆ\u008d¨\u009aá \u0011\u009eê¼G\u0086Ýçd4\u0018\u009d²ovô\u001cÌj/dc:~atB\u00164\u008cT\u0095ð;ß\u0018jã\u007fÙãÖ\u000bÌËñtf Yü\u008bJ|7âÊ'a\u008a8:fáÙ\u0088©Ö\u0091Q¦ù\u00058´\u0010\u0088\u0094ê\u0084\u0086\u0014\u0011&\u0091v½ç´\u0016uº¨s»ÐïÉ¢²å\u008c\r:KJ©¡\u0001^æNe\u0003¥ft \b\u0010\u0089K\u0018èjÑ\u0099nËR\u008eÜ\u0001\u0018\u0081jÑ>\u000eæxk\u009er(1ê\u0094i\u009f\u0010\"\u0097/¢ÐÕÄªø\u0001\u0017\u001bÜ\nrw\u0010 ®ì\u008f\f±»¦e\u0000ùu¥¬|¢\u0010à\u0090\\ªRðåbË\toÀ ¿?É\u0018Î·\u001e\u0011ÚûZõ5TsT\u0001¦AÆn-\u0010ôKó\u0007ç ×DÜ\u0000¹ywWþäò¹Òi1\u009c\u008dÔ'#ÆÊ\u0086Å~óP@\u001cÌæð ÙD\u00862Âª\u0086\u009f[û,w}³\u009a\u0095\u0003>K¸(\u009cÌL)\u0006v\bØ0L¡ \u000bÞ<ï|\u0096\u0019h\u0084j\fÅ\u0082%\rM¶éU¹Uÿ\t¥áºçÂ¾O\u0085z\u0010\u0016ì²!fµ¨Z#¤®Á\u0081<\u009fc\u0010SSÀÿökwZoë¼FÈ\u009d\u0019«\u0018Bkë\u0014½Ç4ßW(2\u0092Ö=°\u0080÷W\u001eà\u009fÐÊ\u0010\u0018?m @TM\u0087;*\u009b\u001b\u000bÄ]õ\u0097jÿâBØÔ²Y\u0010°K\u0010\u0014ï¶µD\bh\u0004V\u001bà\u0002æ\u0010\u0016]VªÔúhqhås\u0018\u0095\u009f£®\u0010Ó¾\u001b\u008eN°ä\r¥ï¯\u0080é\u009cmÕ\u0010\tÂ\u00adï\\ËT\u0010s\u009aÙÆÞ\u009d\"è \u008fML\u0094\u0002Ò-³óÉ\u0003\u009có [\u000f5&ê\u009fó\u0091èÙ\u0099\u0094º\u0095ú<\u001cM";
      short var17 = 1131;
      char var14 = 24;
      int var41 = -1;

      label54:
      while (true) {
         String var42 = var15.substring(++var41, var41 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var42.getBytes("ISO-8859-1"));
            String var53 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var53;
                  if ((var41 += var14) >= var17) {
                     e = var18;
                     f = new String[46];
                     l = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[13];
                     int var3 = 0;
                     String var4 = "\u0099´ñ\u008cöb\u001c\u0080\u00adç¹ÜjÚZJ~ü:(«ýÓXc4sÂÉqTóÐø3\u0082Õ\u0013\u0092å¿\u0018ìó\u0082/á(g¶îëõë\u001b`¹ýmCÇnÈuP\u0080\u001cG\u001eÁ\u0015\u0088àBw\u0016Ø ·½~L»ù®e\u0082¾";
                     byte var5 = 88;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var45 = var6;
                        var10001 = var3++;
                        long var57 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var60 = -1;

                        while (true) {
                           long var8 = var57;
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
                           long var62 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var60) {
                              case 0:
                                 var45[var10001] = var62;
                                 if (var2 >= var5) {
                                    h = var6;
                                    j = new Integer[13];
                                    KProperty[] var38 = new KProperty[]{
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(AutoLand.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "delay", "getDelay()I", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoLand.class, "group", "getGroup()Lsu/catlean/h;", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "wheat", "getWheat()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "carrot", "getCarrot()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "potato", "getPotato()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "melon", "getMelon()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "pumpkin", "getPumpkin()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "beetRoot", "getBeetRoot()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "sugarCane", "getSugarCane()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLand.class, "netherWart", "getNetherWart()Z", 0))
                                    };
                                    delegatedProperties = var38;
                                    INSTANCE = new AutoLand();
                                    switchMode$delegate = SettingFactory.modeSetting$default(INSTANCE, "switch", SwapMode.SILENT_FULL, null, null, 12, null);
                                    delay$delegate = SettingFactory.intSetting$default(INSTANCE, "delay", 0, new IntRange(0, 10), null, null, 24, null);
                                    group$delegate = SettingFactory.settingGroup$default(INSTANCE, "selection", AuraTargetsMode.BOOLS, null, 4, null);
                                    wheat$delegate = SettingFactory.booleanSetting$default(INSTANCE, "wheat", true, INSTANCE.getGroup(), null, 8, null);
                                    carrot$delegate = SettingFactory.booleanSetting$default(INSTANCE, "carrot", true, INSTANCE.getGroup(), null, 8, null);
                                    potato$delegate = SettingFactory.booleanSetting$default(INSTANCE, "potato", true, INSTANCE.getGroup(), null, 8, null);
                                    melon$delegate = SettingFactory.booleanSetting$default(INSTANCE, "melon", false, INSTANCE.getGroup(), null, 8, null);
                                    pumpkin$delegate = SettingFactory.booleanSetting$default(INSTANCE, "pumpkin", false, INSTANCE.getGroup(), null, 8, null);
                                    beetRoot$delegate = SettingFactory.booleanSetting$default(INSTANCE, "beetroot", false, INSTANCE.getGroup(), null, 8, null);
                                    sugarCane$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "sugar-cane", false, INSTANCE.getGroup(), null, 8, null
                                    );
                                    netherWart$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "nether-wart", false, INSTANCE.getGroup(), null, 8, null
                                    );
                                    tickTimer = new TickTimer();
                                    return;
                                 }
                                 break;
                              default:
                                 var45[var10001] = var62;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "ªØÝv\ra6\u008a\u0092e¦\u009b\u0015Ú\u0099j";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var51 = var2;
                           var2 += 8;
                           var7 = var4.substring(var51, var2).getBytes("ISO-8859-1");
                           var45 = var6;
                           var10001 = var3++;
                           var57 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var60 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var41);
                  break;
               default:
                  var18[var16++] = var53;
                  if ((var41 += var14) < var17) {
                     var14 = var15.charAt(var41);
                     continue label54;
                  }

                  var15 = "´\u0000´qBm\u0002Ü&¡ÒðëøÅ \u001e£ñÇ~UléÚ^\u0005Ë\u008dÍ7\u0012(çÛ\u0089\u0006=[\u009cõíM\u0011¹:\u00ad¤Ý\u001dÕ-ov\u0084Z6\u00909\u0002lmó\"D\u0087eÉ¶½Uò÷";
                  var17 = 73;
                  var14 = ' ';
                  var41 = -1;
            }

            var42 = var15.substring(++var41, var41 + var14);
            var10001 = 0;
         }
      }
   }

   private static NumberFormatException zkmRethrow(NumberFormatException var0) {
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 9761;
      if (f[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().threadId();
            var4 = (Object[])g.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               g.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("su/catlean/e0", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = e[var5].getBytes("ISO-8859-1");
         f[var5] = zkmDecodeUtf8(((Cipher)var4[0]).doFinal(var9));
      }

      return f[var5];
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
         throw new RuntimeException("su/catlean/e0" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 32551;
      if (j[var3] == null) {
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
         long var5 = h[var3];
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
         Object[] var9 = (Object[])l.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               l.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/e0", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         j[var3] = var15;
      }

      return j[var3];
   }

   private static int c(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
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
               MethodHandles.insertArguments("c".asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("su/catlean/e0" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
