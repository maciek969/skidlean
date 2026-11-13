package su.catlean.util.combat;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoBasePlace;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.module.basic.MoveFix;
import su.catlean.util.Globals;
import su.catlean.util.InterpolationTracker;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.SlotResult;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CrystalPlacer {
   private static final double h = 0.05;
   private static final double U = 0.95;
   private static final double b = 0.5;
   @NotNull
   private static final Map l;
   @NotNull
   private static final List E;
   private static boolean N;
   private static final long a = ZkmKeys.a(-181175479539052100L, 3244234991542762975L, MethodHandles.lookup().lookupClass()).a(140063211212728L);
   private static final String[] c;
   private static final String[] d;
   private static final Map e = new HashMap(13);
   private static final long[] f;
   private static final Integer[] g;
   private static final Map i;

   @NotNull
   public static final Map W() {
      return l;
   }

   @NotNull
   public static final List N() {
      return E;
   }

   @Nullable
   public static final PlaceResult calcPosition(@NotNull AutoCrystal $this$calcPosition, long a, @NotNull CrystalContext configState) {
      ax = a ^ ax;
      long var4 = ax ^ 137890815064519L;
      long var6 = ax ^ 61102753187219L;
      Intrinsics.checkNotNullParameter($this$calcPosition, "<this>");
      Intrinsics.checkNotNullParameter(configState, "configState");
      long var10 = Globals.now();
      Pair var8 = TuplesKt.to(
         DamageComparator.pickBest(var4, configState, W($this$calcPosition, configState, false, false, var6, 6, null)), Globals.now() - var10
      );
      PlaceResult var9 = (PlaceResult)var8.component1();
      var10 = ((Number)var8.component2()).longValue();
      $this$calcPosition.setBreakDelay(var10);
      return var9;
   }

   @Nullable
   public static final BlockHitResult calcBasePosition(@NotNull AutoCrystal $this$calcBasePosition, boolean onlyNoPositions, long a, boolean sponges) {
      ax = a ^ ax;
      long var5 = ax ^ 119381095225204L;
      long var9 = ax ^ 66051051376398L;
      int var10000 = D();
      Intrinsics.checkNotNullParameter($this$calcBasePosition, "<this>");
      boolean var11 = (boolean)var10000;
      float var12 = 0.0F;
      float var13 = 0.0F;
      CrystalContext var14 = $this$calcBasePosition.recalculate();
      if (!var11) {
         PlaceResult var21 = DamageComparator.pickBest(var5, var14, h($this$calcBasePosition, var14, var9, false, sponges));
         if (var21 != null) {
            PlaceResult var16 = var21;
            var13 = var16.getDamageData().getDamage();
         }
      }

      label71: {
         var10000 = onlyNoPositions;
         if (!var11) {
            if (!onlyNoPositions) {
               break label71;
            }

            float var28;
            var10000 = (var28 = var13 - 0.0F) == 0.0F ? 0 : (var28 < 0.0F ? -1 : 1);
         }

         if (!var11) {
            var10000 = !var10000;
         }

         if (!var10000) {
            return null;
         }
      }

      PlaceResult var23 = DamageComparator.pickBest(var5, var14, h($this$calcBasePosition, var14, var9, true, sponges));
      BlockHitResult var24;
      if (var23 != null) {
         PlaceResult var18 = var23;
         var12 = var18.getDamageData().getDamage();
         var24 = (BlockHitResult)var18.getData();
      } else {
         var24 = null;
      }

      BlockHitResult var15;
      float var27;
      label60: {
         var15 = var24;
         if (!var11) {
            PlaceResult var10001 = (PlaceResult)$this$calcBasePosition.getPlaceResultRef().get();
            if (var10001 != null) {
               DamageData var26 = var10001.getDamageData();
               if (var26 != null) {
                  var27 = var26.getDamage();
                  break label60;
               }
            }
         }

         var27 = 0.0F;
      }

      float var29;
      var10000 = (var29 = var12 - var27) == 0.0F ? 0 : (var29 < 0.0F ? -1 : 1);
      if (!var11) {
         if (var10000 <= 0) {
            return null;
         }

         float var30;
         var10000 = (var30 = var13 - var12) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
      }

      if (!var11) {
         if (var10000 >= 0) {
            return null;
         }

         float var31;
         var10000 = (var31 = Math.abs(var12 - var13) - 1.0F) == 0.0F ? 0 : (var31 < 0.0F ? -1 : 1);
      }

      return var10000 > 0 ? var15 : null;
   }

   private static final List h(AutoCrystal var0, CrystalContext var1, long var2, boolean var4, boolean var5) {
      var2 = a ^ var2;
      long var10001 = var2 ^ 99854087012007L;
      int var10 = (int)((var2 ^ 99854087012007L) >>> 48);
      int var11 = (int)((var2 ^ 99854087012007L) << 16 >>> 32);
      int var12 = (int)(var10001 << 48 >>> 48);
      BlockPos var10000 = BlockPos.ofFloored(Globals.getPlayer().getEyePos());
      Intrinsics.checkNotNullExpressionValue(var10000, "containing(...)");
      BlockPos var19 = var10000;
      Vec3d var20 = InterpolationTracker.INSTANCE.getPredictedCenter(Globals.getPlayer(), 1);
      int var21 = (int)((float)Math.ceil(var1.getPlaceRange()));
      IntRange var22 = new IntRange(var19.getX() - var21, var19.getX() + var21);
      boolean var61 = D();
      IntRange var23 = new IntRange(var19.getY() - var21, var19.getY() + var21);
      IntRange var24 = new IntRange(var19.getZ() - var21, var19.getZ() + var21);
      boolean var18 = var61;
      Iterable var25 = var22;
      var var28 = new ArrayList();
      Iterator var30 = var25.iterator();

      label50:
      while (true) {
         Iterator var62 = var30;

         label47:
         while (var62.hasNext()) {
            int var31 = ((IntIterator)var30).nextInt();
            int var32 = var31;
            Iterable var34 = var23;
            var var63 = new ArrayList();
            if (var18) {
               return var63;
            }

            ArrayList var37 = var63;
            Iterator var39 = var34.iterator();

            label44:
            while (var39.hasNext()) {
               int var40 = ((IntIterator)var39).nextInt();
               int var41 = var40;
               Iterable var43 = var24;
               var var46 = new ArrayList();
               var62 = var43.iterator();
               if (var18) {
                  continue label47;
               }

               Iterator var50 = var62;

               while (true) {
                  if (var50.hasNext()) {
                     int var51 = ((IntIterator)var50).nextInt();
                     if (!var18) {
                        BlockPos var17 = new BlockPos(var32, var41, var51);
                        PlaceResult var64 = getPlaceData(var0, (short)var10, var17, var11, var20, var1, (short)var12, var4, var5);
                        if (var64 != null) {
                           PlaceResult var56 = var64;
                           var46.add(var56);
                        }

                        if (!var18) {
                           continue;
                        }
                     }
                  }

                  var var58 = var46;
                  CollectionsKt.addAll(var37, var58);
                  if (var18) {
                     break label44;
                  }
                  break;
               }
            }

            var var59 = var37;
            CollectionsKt.addAll(var28, var59);
            if (!var18) {
               continue label50;
            }
            break;
         }

         return var28;
      }
   }

   static List W(AutoCrystal var0, CrystalContext var1, boolean var2, boolean var3, long var4, int var6, Object var7) {
      var4 = a ^ var4;
      long var8 = var4 ^ 122508958998250L;
      boolean var10 = D();
      int var10000 = var6 & 2;
      if (!var10) {
         var2 = false;
         var10000 = var6 & 4;
      }

      if (!var10) {
         if (var10000 == 0) {
            return h(var0, var1, var8, var2, (boolean)var3);
         }

         var10000 = 0;
      }

      var3 = var10000;
      return h(var0, var1, var8, var2, (boolean)var3);
   }

   @Nullable
   public static final PlaceResult getPlaceData(
      @NotNull AutoCrystal $this$getPlaceData,
      short a,
      @NotNull BlockPos bp,
      int a,
      @NotNull Vec3d predictedPlayerPos,
      @NotNull CrystalContext configState,
      short a,
      boolean basePlace,
      boolean sponges
   ) {
      long var9 = ((long)ax << 48 | (long)axxx << 32 >>> 16 | (long)axx << 48 >>> 48) ^ a;
      long var14 = var9 ^ 58944123093231L;
      int var18 = (int)((var9 ^ 76598718304626L) >>> 48);
      long var19 = (var9 ^ 76598718304626L) << 16 >>> 16;
      int var24 = (int)((var9 ^ 138662459428843L) >>> 32);
      int var25 = (int)((var9 ^ 138662459428843L) << 32 >>> 32);
      long var26 = var9 ^ 15188047097504L;
      long var34 = var9 ^ 121834545431785L;
      int var10000 = ZkmReferences.zkmBootstrap<"Å">(-2067996115480343056L, var9);
      Intrinsics.checkNotNullParameter($this$getPlaceData, zkmBootstrap<"i">(13172, 8273520772626737969L ^ var9));
      Intrinsics.checkNotNullParameter(bp, zkmBootstrap<"i">(13102, 8955292248537918328L ^ var9));
      byte var42 = (byte)var10000;
      Intrinsics.checkNotNullParameter(predictedPlayerPos, zkmBootstrap<"i">(28698, 2182915503418058844L ^ var9));
      Intrinsics.checkNotNullParameter(configState, zkmBootstrap<"i">(28581, 4617940382924257259L ^ var9));
      var10000 = CrystalTracker.INSTANCE.hasFailedPlace(var26, bp);
      if (var42 == 0) {
         if (var10000 != 0) {
            return null;
         }

         var10000 = configState.isCalcShields();
      }

      label293:
      if (var42 == 0) {
         if (var10000 != 0) {
            var10000 = L(bp, configState.getTarget());
            if (ax < 0 || var42 != 0) {
               break label293;
            }

            if (var10000 != 0) {
               return null;
            }
         }

         Entity var51 = configState.getTarget();
         double var83;
         var10000 = (var83 = $this$getPlaceData.getPos(var51).distanceTo(bp.toCenterPos().add(0.0, 0.5, 0.0)) - 12.0) == 0.0 ? 0 : (var83 < 0.0 ? -1 : 1);
      }

      if (var10000 > 0) {
         return null;
      } else {
         BlockState var43;
         byte var10001;
         label283: {
            BlockState var60 = Globals.getWorld().getBlockState(bp);
            Intrinsics.checkNotNullExpressionValue(var60, zkmBootstrap<"i">(15416, 5258554887421748340L ^ var9));
            var43 = var60;
            Block var61 = var43.getBlock();
            Intrinsics.checkNotNullExpressionValue(var61, zkmBootstrap<"i">(31434, 227605200259588746L ^ var9));
            Block var44 = var61;
            var62 = Intrinsics.areEqual(var44, Blocks.OBSIDIAN);
            label282:
            if (var42 == 0) {
               if (!var62) {
                  var62 = Intrinsics.areEqual(var44, Blocks.BEDROCK);
                  if (var42 != 0) {
                     break label282;
                  }

                  if (!var62) {
                     var62 = basePlace;
                     var10001 = var42;
                     if (axxx <= 0) {
                        break label283;
                     }

                     if (var42 != 0) {
                        break label282;
                     }

                     if (!basePlace) {
                        return null;
                     }
                  }
               }

               var62 = basePlace;
            }

            var10001 = var42;
         }

         label269: {
            label268: {
               label299: {
                  label266:
                  if (var10001 == 0) {
                     if (var62) {
                        var62 = var43.isReplaceable();
                        var10001 = var42;
                        if (ax < 0) {
                           break label299;
                        }

                        if (var42 != 0) {
                           break label266;
                        }

                        if (!var62) {
                           return null;
                        }
                     }

                     var62 = basePlace;
                  }

                  if (axxx <= 0) {
                     break label268;
                  }

                  var10001 = var42;
               }

               if (var10001 == 0) {
                  if (!var62) {
                     break label269;
                  }

                  var62 = AutoBasePlace.INSTANCE.placeAt(bp, var34);
               }
            }

            if (!var62) {
               return null;
            }
         }

         BlockState var45;
         boolean var46;
         label249: {
            BlockState var63 = Globals.getWorld().getBlockState(bp.up());
            Intrinsics.checkNotNullExpressionValue(var63, zkmBootstrap<"i">(13030, 6457447288202259127L ^ var9));
            var45 = var63;
            var46 = false;
            var64 = configState.isStringWaterRemove();
            label248:
            if (var42 == 0) {
               label246:
               if (var64) {
                  label302: {
                     var64 = Intrinsics.areEqual(var45.getBlock(), Blocks.LAVA);
                     var10001 = var42;
                     if (ax >= 0) {
                        if (var42 == 0) {
                           if (var64) {
                              break label302;
                           }

                           var64 = Intrinsics.areEqual(var45.getBlock(), Blocks.TRIPWIRE);
                        }

                        var10001 = var42;
                     }

                     if (axx < 0) {
                        if (var10001 == 0) {
                           if (var64) {
                              break label302;
                           }

                           var64 = Intrinsics.areEqual(var45.getBlock(), Blocks.REDSTONE_WIRE);
                        }

                        var10001 = var42;
                     }

                     if (axx <= 0) {
                        if (var10001 == 0) {
                           if (var64) {
                              break label302;
                           }

                           var64 = Intrinsics.areEqual(var45.getBlock(), Blocks.TORCH);
                        }

                        var10001 = var42;
                     }

                     if (axx >= 0) {
                        break label249;
                     }

                     if (var10001 != 0) {
                        break label248;
                     }

                     if (!var64) {
                        break label246;
                     }
                  }

                  label219: {
                     InventoryUtil var65 = InventoryUtil.INSTANCE;
                     Item[] var48 = new Item[1];
                     Item var10003 = Items.STRING;
                     Intrinsics.checkNotNullExpressionValue(Items.STRING, zkmBootstrap<"i">(11972, 7437490969014302336L ^ var9));
                     var48[0] = var10003;
                     SlotResult var47 = var65.findInHotbar(var48);
                     InventoryUtil var66 = InventoryUtil.INSTANCE;
                     Item[] var49 = new Item[1];
                     var10003 = Items.REDSTONE;
                     Intrinsics.checkNotNullExpressionValue(Items.REDSTONE, zkmBootstrap<"i">(4307, 2375088845700843675L ^ var9));
                     var49[0] = var10003;
                     SlotResult var54 = var66.findInHotbar(var49);
                     InventoryUtil var67 = InventoryUtil.INSTANCE;
                     Item[] var50 = new Item[1];
                     var10003 = Items.TORCH;
                     Intrinsics.checkNotNullExpressionValue(Items.TORCH, zkmBootstrap<"i">(30898, 4799544530492629245L ^ var9));
                     var50[0] = var10003;
                     SlotResult var56 = var67.findInHotbar(var50);
                     boolean var68 = var47.isFound();
                     if (var42 == 0) {
                        if (var68) {
                           var69 = var47;
                           break label219;
                        }

                        var69 = var54;
                        if (var42 != 0) {
                           break label219;
                        }

                        var68 = var54.isFound();
                     }

                     var69 = var68 ? var54 : var56;
                  }

                  SlotResult var58 = var69;
                  var46 = var58.isFound();
               }

               var64 = var45.isAir();
            }

            var10001 = var42;
         }

         label206:
         if (var10001 == 0) {
            label204:
            if (!var64) {
               var64 = Intrinsics.areEqual(var45.getBlock(), Blocks.FIRE);
               var10001 = var42;
               if (axxx > 0) {
                  if (var42 == 0) {
                     if (var64) {
                        var64 = configState.isBreakFire();
                        if (var42 != 0) {
                           break label206;
                        }

                        if (var64) {
                           break label204;
                        }
                     }

                     var64 = sponges;
                  }

                  var10001 = var42;
               }

               if (axx < 0) {
                  if (var10001 == 0) {
                     if (var64) {
                        var64 = Intrinsics.areEqual(var45.getBlock(), Blocks.WATER);
                        if (var42 != 0) {
                           break label206;
                        }

                        if (var64) {
                           break label204;
                        }
                     }

                     var64 = var46;
                  }

                  var10001 = var42;
               }

               if (var10001 != 0) {
                  break label206;
               }

               if (!var64) {
                  var64 = false;
                  break label206;
               }
            }

            var64 = true;
         }

         boolean var53 = var64;
         int var70 = var53;
         var10001 = var42;
         if (axx < 0) {
            if (var42 == 0) {
               if (!var53) {
                  return null;
               }

               var70 = configState.isOldVer();
            }

            var10001 = var42;
         }

         label306: {
            label175:
            if (var10001 == 0) {
               if (var70) {
                  var70 = Globals.getWorld().isAir(bp.up(2));
                  if (ax < 0 || var42 != 0) {
                     break label175;
                  }

                  if (!var70) {
                     return null;
                  }
               }

               var71 = $this$getPlaceData;
               var80 = bp;
               byte var10002 = var42;
               if (ax >= 0) {
                  if (var42 != 0) {
                     break label306;
                  }

                  var10002 = 1;
               }

               byte var37 = var10002;
               var70 = isPositionBlockedByEntity($this$getPlaceData, var14, bp, (boolean)var37, configState);
            }

            if (var70) {
               return null;
            }

            var71 = $this$getPlaceData;
            var80 = bp;
         }

         boolean var52 = true;
         boolean var38 = false;
         BlockPos var40 = var80;
         AutoCrystal var41 = var71;
         if (ax >= 0) {
            DamageData var72 = DamageComparator.compareDamages((short)var18, var41, var40, var19, configState, var38, var52, basePlace);
            if (var72 != null) {
               DamageData var55 = var72;
               var70 = var42;
               if (axxx >= 0) {
                  if (var42 != 0) {
                     return null;
                  }

                  var70 = var24;
               }

               BlockHitResult var74 = D(var70, bp, configState, var25, basePlace);
               if (var74 != null) {
                  BlockHitResult var57 = var74;
                  Vec3d var75 = var57.getPos();
                  Intrinsics.checkNotNullExpressionValue(var75, zkmBootstrap<"i">(521, 8959533839977994816L ^ var9));
                  if (!w(var75, bp, predictedPlayerPos, configState)) {
                     return null;
                  }

                  return new PlaceResult(var57, var55);
               }

               return null;
            }
         }

         return null;
      }
   }

   public static PlaceResult getPlaceData$default(
      AutoCrystal var0, BlockPos var1, long var2, Vec3d var4, CrystalContext var5, boolean var6, boolean var7, int var8, Object var9
   ) {
      var2 = a ^ var2;
      long var10001 = var2 ^ 78240960013558L;
      int var10 = (int)((var2 ^ 78240960013558L) >>> 48);
      int var11 = (int)((var2 ^ 78240960013558L) << 16 >>> 32);
      int var12 = (int)(var10001 << 48 >>> 48);
      boolean var13 = ZkmReferences.zkmBootstrap<"Å">(9056209784625859056L, var2);
      int var10000 = var8 & zkmBootstrap2<"j">(7646, 1691391838674414532L ^ var2);
      if (var13) {
         if (var10000 != 0) {
            var6 = false;
         }

         var10000 = var8 & zkmBootstrap2<"j">(12238, 6273464189327104465L ^ var2);
      }

      if (var13) {
         if (var10000 == 0) {
            return getPlaceData(var0, (short)var10, var1, var11, var4, var5, (short)var12, var6, (boolean)var7);
         }

         var10000 = 0;
      }

      var7 = var10000;
      return getPlaceData(var0, (short)var10, var1, var11, var4, var5, (short)var12, var6, (boolean)var7);
   }

   @Nullable
   public static final BlockHitResult D(int a, @NotNull BlockPos bp, @NotNull CrystalContext configState, int a, boolean basePlace) {
      long var5 = ((long)ax << 32 | (long)axx << 32 >>> 32) ^ a;
      long var7 = var5 ^ 8200281328223L;
      long var9 = var5 ^ 68866561157866L;
      boolean var11 = ZkmReferences.zkmBootstrap<"Å">(-2975991064226618643L, var5);
      BlockPos var10000 = bp;
      if (var11) {
         Intrinsics.checkNotNullParameter(bp, zkmBootstrap<"i">(13102, 8955299934449000023L ^ var5));
         Intrinsics.checkNotNullParameter(configState, zkmBootstrap<"i">(28581, 4617934827451155140L ^ var5));
         if (configState.isRayTrace()) {
            return L(bp, var9, configState, basePlace);
         }

         var10000 = bp;
      }

      return r(var10000, var7, configState);
   }

   public static BlockHitResult f(char var0, BlockPos var1, int var2, CrystalContext var3, short var4, boolean var5, int var6, Object var7) {
      long var8 = ((long)var0 << 48 | (long)var2 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      int var10 = (int)((var8 ^ 18153519534358L) >>> 32);
      int var11 = (int)((var8 ^ 18153519534358L) << 32 >>> 32);
      boolean var12 = ZkmReferences.zkmBootstrap<"Å">(-166511264047164659L, var8);
      int var10000 = var6 & 4;
      if (!var12) {
         if (var10000 == 0) {
            return D(var10, var1, var3, var11, (boolean)var5);
         }

         var10000 = 0;
      }

      var5 = var10000;
      return D(var10, var1, var3, var11, (boolean)var5);
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   private static final BlockHitResult r(BlockPos var0, long var1, CrystalContext var3) {
      var1 = a ^ var1;
      int var4 = (int)((var1 ^ 103314408339407L) >>> 32);
      int var5 = (int)((var1 ^ 103314408339407L) << 32 >>> 32);
      float var20 = var3.getPlaceRange();
      int var10000 = ZkmReferences.zkmBootstrap<"Å">(-6414963064125241276L, var1);
      Direction var21 = null;
      boolean var19 = (boolean)var10000;
      var10000 = MoveFix.INSTANCE.getEnabled();
      if (!var19) {
         var10000 = var10000 != 0 ? var0.up().getY() : var0.getY();
      }

      boolean var75;
      label186: {
         int var22 = var10000;
         double var78;
         var10000 = (var78 = Globals.getPlayer().getEyePos().y - var22) == 0.0 ? 0 : (var78 < 0.0 ? -1 : 1);
         label185:
         if (!var19) {
            if (var10000 > 0) {
               Iterable var24 = E;
               Iterator var26 = var24.iterator();
               Object var59 = var26;
               if (!var19) {
                  if (!var26.hasNext()) {
                     throw new NoSuchElementException();
                  }

                  var59 = var26.next();
               }

               label191: {
                  Object var27 = var59;
                  Object var60 = var26;
                  if (!var19) {
                     if (!var26.hasNext()) {
                        var64 = var27;
                        break label191;
                     }

                     var60 = var27;
                  }

                  Vec3d var28 = (Vec3d)var60;
                  MovementUtil var61 = MovementUtil.INSTANCE;
                  Vec3d var18 = new Vec3d(var0.getX() + var28.x, var0.getY() + var28.y + 1.0, var0.getZ() + var28.z);
                  float var50 = var61.distanceTo(var18);

                  label179:
                  while (true) {
                     Object var29 = var26.next();
                     Vec3d var30 = (Vec3d)var29;
                     MovementUtil var62 = MovementUtil.INSTANCE;
                     var18 = new Vec3d(var0.getX() + var30.x, var0.getY() + var30.y + 1.0, var0.getZ() + var30.z);
                     float var54 = var62.distanceTo(var18);
                     if (Float.compare(var50, var54) > 0) {
                        var27 = var29;
                        var50 = var54;
                     }

                     while (true) {
                        boolean var63 = var26.hasNext();

                        do {
                           if (var63) {
                              continue label179;
                           }

                           var63 = var19;
                        } while (var1 < 0L);

                        if (!var19) {
                           var64 = var27;
                           break label179;
                        }

                        var50 = var54;
                     }
                  }
               }

               Vec3d var23 = (Vec3d)var64;
               Vec3d var43 = new Vec3d(var0.getX() + var23.x, var0.getY() + 1.0, var0.getZ() + var23.z);
               PlaceManager var10001 = PlaceManager.INSTANCE;
               Entity var31 = Globals.getPlayer();
               double var79;
               var10000 = (var79 = var43.squaredDistanceTo(var10001.getEyePos(var31)) - var3.getPlaceRange() * var3.getPlaceRange()) == 0.0
                  ? 0
                  : (var79 < 0.0 ? -1 : 1);
               var75 = var19;
               if (var1 < 0L) {
                  break label186;
               }

               if (var19) {
                  break label185;
               }

               if (var10000 <= 0) {
                  return new BlockHitResult(var43, Direction.UP, var0, false);
               }
            }

            double var80;
            var10000 = (var80 = Globals.getPlayer().getEyePos().y - var0.getY()) == 0.0 ? 0 : (var80 < 0.0 ? -1 : 1);
         }

         var75 = var19;
      }

      label151: {
         if (!var75) {
            if (var10000 >= 0) {
               break label151;
            }

            var10000 = Globals.getWorld().isAir(var0.down());
         }

         if (var10000 != 0) {
            Iterable var44 = E;
            Iterator var47 = var44.iterator();
            Object var65 = var47;
            if (!var19) {
               if (!var47.hasNext()) {
                  throw new NoSuchElementException();
               }

               var65 = var47.next();
            }

            label194: {
               Object var49 = var65;
               Object var66 = var47;
               if (!var19) {
                  if (!var47.hasNext()) {
                     var70 = var49;
                     break label194;
                  }

                  var66 = var49;
               }

               Vec3d var51 = (Vec3d)var66;
               MovementUtil var67 = MovementUtil.INSTANCE;
               Vec3d var38 = new Vec3d(var0.getX() + var51.x, var0.getY() + var51.y, var0.getZ() + var51.z);
               float var52 = var67.distanceTo(var38);

               label142:
               while (true) {
                  Object var53 = var47.next();
                  Vec3d var55 = (Vec3d)var53;
                  MovementUtil var68 = MovementUtil.INSTANCE;
                  var38 = new Vec3d(var0.getX() + var55.x, var0.getY() + var55.y, var0.getZ() + var55.z);
                  float var56 = var68.distanceTo(var38);
                  if (Float.compare(var52, var56) > 0) {
                     var49 = var53;
                     var52 = var56;
                  }

                  while (true) {
                     boolean var69 = var47.hasNext();

                     do {
                        if (var69) {
                           continue label142;
                        }

                        var69 = var19;
                     } while (var1 <= 0L);

                     if (!var19) {
                        var70 = var49;
                        break label142;
                     }

                     var52 = var56;
                  }
               }
            }

            Vec3d var40 = (Vec3d)var70;
            Vec3d var45 = new Vec3d(var0.getX() + var40.x, var0.getY(), var0.getZ() + var40.z);
            PlaceManager var76 = PlaceManager.INSTANCE;
            Entity var35 = Globals.getPlayer();
            if (var45.squaredDistanceTo(var76.getEyePos(var35)) <= var3.getPlaceRange() * var3.getPlaceRange()) {
               return new BlockHitResult(var45, Direction.DOWN, var0, false);
            }
         }
      }

      Iterator var41 = PlaceManager.INSTANCE.getPlaceSides(var4, var0, var5).iterator();

      label119:
      while (true) {
         boolean var71 = var41.hasNext();

         while (var71) {
            Direction var46 = (Direction)var41.next();
            var72 = var46;
            if (var1 <= 0L || var19) {
               break label119;
            }

            Direction var77 = Direction.UP;
            if (!var19) {
               if (var46 == Direction.UP) {
                  continue label119;
               }

               var72 = var46;
               var77 = Direction.DOWN;
            }

            if (var72 == var77) {
               continue label119;
            }

            Vec3d var25 = new Vec3d(var0.getX() + 0.5 + var46.getVector().getX() * 0.5, var0.getY() + 0.99, var0.getZ() + 0.5 + var46.getVector().getZ() * 0.5);
            var71 = Globals.getWorld().getBlockState(var0.offset(var46)).isReplaceable();
            if (!var19) {
               if (!var71) {
                  continue label119;
               }

               float var48 = MovementUtil.INSTANCE.distanceTo(var25);
               if (var1 >= 0L) {
                  float var73 = var20;
                  if (!var19) {
                     if (!(var20 > var48)) {
                        continue label119;
                     }

                     var21 = var46;
                     var73 = var48;
                  }

                  var20 = var73;
               }

               if (!var19) {
                  continue label119;
               }
               break;
            }
         }

         var72 = var21;
         break;
      }

      if (var72 == null) {
         return null;
      } else {
         Vec3d var74 = var0.toCenterPos().add(var21.getVector().getX() * 0.5, var21.getVector().getY() * 0.5, var21.getVector().getZ() * 0.5);
         Intrinsics.checkNotNullExpressionValue(var74, zkmBootstrap<"i">(16819, 4627721939940596813L ^ var1));
         Vec3d var42 = var74;
         return new BlockHitResult(var42, var21, var0, false);
      }
   }

   private static final BlockHitResult L(BlockPos var0, long var1, CrystalContext var3, boolean var4) {
      var1 = a ^ var1;
      boolean var10000 = ZkmReferences.zkmBootstrap<"Å">(-8746715782810930493L, var1);
      Map var10001 = l;
      Comparator var10002 = Comparator.comparing(CrystalPlacer::b);
      Intrinsics.checkNotNullExpressionValue(var10002, zkmBootstrap<"i">(23990, 223378140408045813L ^ var1));
      Iterator var16 = MapsKt.toSortedMap(var10001, var10002).entrySet().iterator();
      boolean var15 = var10000;

      label76:
      while (true) {
         var10000 = var16.hasNext();

         label73:
         while (var10000) {
            Entry var17 = (Entry)var16.next();
            Vec3d var18 = new Vec3d(
               var0.getX() + ((Vec3d)var17.getKey()).x, var0.getY() + ((Vec3d)var17.getKey()).y - 0.01, var0.getZ() + ((Vec3d)var17.getKey()).z
            );

            BlockHitResult var19;
            do {
               if (var1 >= 0L && MovementUtil.INSTANCE.distanceTo(var18) > var3.getPlaceRange()) {
                  var10000 = var15;
                  if (var1 <= 0L) {
                     continue label73;
                  }

                  if (var15) {
                     continue label76;
                  }
               }

               label83: {
                  DamageUtil var23 = DamageUtil.INSTANCE;
                  PlaceManager var25 = PlaceManager.INSTANCE;
                  Entity var20 = Globals.getPlayer();
                  var19 = var23.raycastExplosion(var25.getEyePos(var20), var18, false, var4 ? CollectionsKt.listOf(var0) : CollectionsKt.emptyList(), var4);
                  BlockHitResult var24 = var19;
                  boolean var26 = var15;
                  if (var1 > 0L) {
                     if (var15) {
                        if (var19 == null) {
                           break label83;
                        }

                        var24 = var19;
                     }

                     var26 = var15;
                  }

                  if (var1 >= 0L) {
                     if (var26) {
                        if (var24.getType() != Type.BLOCK) {
                           break label83;
                        }

                        var24 = var19;
                     }

                     var26 = var15;
                  }

                  if (!var26) {
                     return var24;
                  }

                  if (Intrinsics.areEqual(var24.getBlockPos(), var0)) {
                     return var19;
                  }
               }

               if (var15) {
                  continue label76;
               }
            } while (var1 <= 0L);

            return var19;
         }

         return null;
      }
   }

   private static final boolean w(Vec3d var0, BlockPos var1, Vec3d var2, CrystalContext var3) {
      int var10000 = D();
      Vec3d var10001 = var2.add(0.0, Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose()), 0.0);
      Intrinsics.checkNotNullExpressionValue(var10001, "add(...)");
      Vec3d var11 = var10001;
      boolean var10 = (boolean)var10000;
      var10000 = var3.isRayTrace();
      if (!var10) {
         if (var10000 != 0) {
            CrystalBreaker var14 = CrystalBreaker.INSTANCE;
            var10001 = var1.toCenterPos().add(0.0, 0.5, 0.0);
            Intrinsics.checkNotNullExpressionValue(var10001, "add(...)");
            return (boolean)(var14.S(var10001, var3, var11) != null ? 1 : 0);
         }

         var10000 = var3.isGrimBreakRange();
      }

      if (!var10) {
         if (var10000 != 0) {
            CrystalOffsets var13 = CrystalOffsets.INSTANCE;
            var10001 = var1.toCenterPos().add(0.0, 0.5, 0.0);
            Intrinsics.checkNotNullExpressionValue(var10001, "add(...)");
            return var13.findBestOffset(var10001, var0);
         }

         double var17;
         var10000 = (var17 = var11.distanceTo(var0) - var3.getExplodeRange()) == 0.0 ? 0 : (var17 < 0.0 ? -1 : 1);
      }

      if (!var10) {
         var10000 = var10000 <= 0 ? 1 : 0;
      }

      return (boolean)var10000;
   }

   public static final boolean isPositionBlockedByEntity(
      @NotNull AutoCrystal $this$isPositionBlockedByEntity, long a, @NotNull BlockPos base, boolean calcPhase, @NotNull CrystalContext configState
   ) {
      long var13;
      long var15;
      long var17;
      boolean var19;
      Box var38;
      label326: {
         label325: {
            ax = a ^ ax;
            var13 = ax ^ 20905227330101L;
            var15 = ax ^ 49986440685858L;
            var17 = ax ^ 103982540281510L;
            Intrinsics.checkNotNullParameter($this$isPositionBlockedByEntity, "<this>");
            boolean var10000 = D();
            Intrinsics.checkNotNullParameter(base, "base");
            var19 = var10000;
            Intrinsics.checkNotNullParameter(configState, "configState");
            ServerInfo var36 = Globals.getNetworkHandler().getServerInfo();
            if (!var19) {
               if (var36 == null) {
                  break label325;
               }

               var36 = Globals.getNetworkHandler().getServerInfo();
               Intrinsics.checkNotNull(var36);
            }

            String var37 = var36.address;
            Intrinsics.checkNotNullExpressionValue(var36.address, "ip");
            if (StringsKt.contains$default(var37, "crystalpvp.cc", false, 2, null)) {
               var38 = new Box(base.up());
               break label326;
            }
         }

         var38 = new Box(base.up()).expand(0.0, 1.0, 0.0);
      }

      Box var20;
      label317: {
         label329: {
            Box var21 = var38;
            Intrinsics.checkNotNull(var21);
            var20 = var21;
            if ($this$isPositionBlockedByEntity.getPlaceResultRef().get() != null) {
               label330: {
                  var var22 = CrystalPlacer$EntriesMappings.enumEntries;
                  var39 = var22;
                  label312:
                  if (!var19) {
                     if (var22 instanceof Collection) {
                        var39 = (Collection)var22;
                        if (var19) {
                           break label312;
                        }

                        if (var39.isEmpty()) {
                           var40 = false;
                           break label330;
                        }
                     }

                     var39 = var22;
                  }

                  Iterator var24 = var39.iterator();

                  label305:
                  while (true) {
                     if (!var24.hasNext()) {
                        var40 = false;
                        break;
                     }

                     Object var25 = var24.next();
                     Direction var26 = (Direction)var25;
                     boolean var41 = false;

                     while (true) {
                        BlockPos var10001;
                        label301: {
                           label300: {
                              if (!var19) {
                                 PlaceResult var42 = (PlaceResult)$this$isPositionBlockedByEntity.getPlaceResultRef().get();
                                 if (var42 != null) {
                                    BlockHitResult var43 = (BlockHitResult)var42.getData();
                                    if (var43 != null) {
                                       var44 = var43.getBlockPos();
                                       var10001 = var44;
                                       if (var19) {
                                          break label301;
                                       }

                                       if (var44 != null) {
                                          break label300;
                                       }
                                    }
                                 }
                              }

                              var44 = BlockPos.ORIGIN;
                              Intrinsics.checkNotNullExpressionValue(BlockPos.ORIGIN, "ZERO");
                           }

                           var10001 = base.offset(var26);
                        }

                        if (!Intrinsics.areEqual(var44, var10001)) {
                           break;
                        }

                        var40 = true;
                        if (!var19) {
                           break label305;
                        }
                     }
                  }
               }

               if (var40) {
                  break label329;
               }
            }

            label333: {
               var var29 = CrystalPlacer$EntriesMappings.enumEntries;
               var45 = var29;
               label278:
               if (!var19) {
                  if (var29 instanceof Collection) {
                     var45 = (Collection)var29;
                     if (var19) {
                        break label278;
                     }

                     if (var45.isEmpty()) {
                        var46 = false;
                        break label333;
                     }
                  }

                  var45 = var29;
               }

               Iterator var31 = var45.iterator();

               label271:
               while (true) {
                  if (!var31.hasNext()) {
                     var46 = false;
                     break;
                  }

                  Object var33 = var31.next();
                  Direction var35 = (Direction)var33;
                  var46 = $this$isPositionBlockedByEntity.getPlacedPositions().contains(base.offset(var35));

                  while (var46) {
                     var46 = true;
                     if (!var19) {
                        break label271;
                     }
                  }
               }
            }

            if (!var46) {
               var47 = false;
               break label317;
            }
         }

         var47 = true;
      }

      label336: {
         boolean var28 = var47;
         boolean var48 = $this$isPositionBlockedByEntity.getMinedBlocks().containsKey(base.up());
         if (!var19) {
            if (!var48) {
               break label336;
            }

            var48 = var28;
         }

         if (var19) {
            return var48;
         }

         if (!var48) {
            return true;
         }
      }

      Iterator var30 = configState.getEntities().iterator();

      while (true) {
         Entity var23;
         while (true) {
            while (true) {
               while (true) {
                  while (true) {
                     if (!var30.hasNext()) {
                        return false;
                     }

                     label343: {
                        label344: {
                           var23 = (Entity)var30.next();
                           Entity var49 = var23;
                           if (!var19) {
                              if (var23 == null) {
                                 break label344;
                              }

                              var49 = var23;
                           }

                           var50 = var49.isAlive();
                           if (!var19) {
                              if (var50 == 0) {
                                 break label344;
                              }

                              double var51;
                              var50 = (var51 = var23.squaredDistanceTo(base.getX() + 0.5, base.getY() + 0.5, base.getZ() + 0.5) - 16.0) == 0.0
                                 ? 0
                                 : (var51 < 0.0 ? -1 : 1);
                           }

                           if (var19) {
                              break;
                           }

                           if (var50 <= 0) {
                              break label343;
                           }
                        }

                        if (!var19) {
                           continue;
                        }

                        Module.zkmSetFlag(new Module[3]);
                     }

                     var50 = var23 instanceof PlayerEntity;
                     break;
                  }

                  label165:
                  if (!var19) {
                     if (var50 != 0) {
                        int var32 = 0;
                        int var34 = $this$isPositionBlockedByEntity.getPredictTicks(var17);
                        var50 = var32;
                        if (var19) {
                           break label165;
                        }

                        if (var32 <= var34) {
                           do {
                              if (InterpolationTracker.INSTANCE.getBox((PlayerEntity)var23, var32).intersects(var20)) {
                                 var50 = 1;
                                 if (!var19) {
                                    return true;
                                 }
                              } else {
                                 var50 = var32;
                              }

                              if (var19) {
                                 break label165;
                              }

                              if (var50 == var34) {
                                 break;
                              }

                              var32++;
                           } while (!var19);
                        }
                     }

                     var50 = var23.getBoundingBox().intersects(var20);
                  }

                  if (var19) {
                     break;
                  }

                  if (var50 != 0) {
                     var50 = var23 instanceof ExperienceOrbEntity;
                     break;
                  }
               }

               if (var19) {
                  break;
               }

               if (var50 == 0 || var19) {
                  var50 = var23 instanceof ItemEntity;
                  break;
               }
            }

            if (var19) {
               break;
            }

            label218:
            if (var50 != 0) {
               label347: {
                  var50 = ((ItemEntity)var23).isOnFire();
                  if (!var19) {
                     if (var50 != 0) {
                        break label347;
                     }

                     var50 = CrystalTracker.INSTANCE.hasSpawned(((ItemEntity)var23).getId());
                  }

                  if (!var19) {
                     if (var50 != 0) {
                        break label347;
                     }

                     var50 = $this$isPositionBlockedByEntity.getMinedBlocks().containsKey(base.up());
                  }

                  if (!var19) {
                     if (var50 != 0) {
                        break label347;
                     }

                     var50 = Intrinsics.areEqual(((ItemEntity)var23).getStack().getItem(), Items.STRING);
                  }

                  if (var19) {
                     break;
                  }

                  if (var50 == 0) {
                     break label218;
                  }
               }

               if (!var19) {
                  continue;
               }
            }

            var50 = var23 instanceof EndCrystalEntity;
            break;
         }

         if (var19) {
            break;
         }

         label241:
         if (var50 != 0) {
            var50 = CrystalTracker.INSTANCE.hasSpawned(((EndCrystalEntity)var23).getId());
            if (!var19) {
               if (var50 != 0 && !var19) {
                  continue;
               }

               var50 = CrystalTracker.INSTANCE.hasFailedBreak(((EndCrystalEntity)var23).getId(), var13);
            }

            if (!var19) {
               if (var50 != 0) {
                  return true;
               }

               var50 = calcPhase;
            }

            if (!var19) {
               if (var50 != 0) {
                  if (CrystalBreaker.INSTANCE
                        .u(
                           $this$isPositionBlockedByEntity,
                           $this$isPositionBlockedByEntity.getPos(var23),
                           configState,
                           var15,
                           true,
                           ((EndCrystalEntity)var23).age
                        )
                     == null) {
                     break label241;
                  }

                  if (!var19) {
                     continue;
                  }
               }

               double var52;
               var50 = (var52 = $this$isPositionBlockedByEntity.getPos(var23).squaredDistanceTo(var20.getCenter()) - 0.3) == 0.0 ? 0 : (var52 < 0.0 ? -1 : 1);
            }

            if (var19) {
               break;
            }

            if (var50 > 0) {
               CrystalBreaker.INSTANCE.setEndCrystalEntity((EndCrystalEntity)var23);
            }
         }

         var50 = 1;
         break;
      }

      return (boolean)var50;
   }

   public static final boolean L(@NotNull BlockPos bp, @NotNull PlayerEntity target) {
      boolean var5;
      PlayerEntity var9;
      label38: {
         boolean var10000 = D();
         Intrinsics.checkNotNullParameter(bp, "bp");
         var5 = var10000;
         Intrinsics.checkNotNullParameter(target, "target");
         boolean var8 = target.isUsingItem();
         if (!var5) {
            if (!var8) {
               return false;
            }

            var9 = target;
            if (var5) {
               break label38;
            }

            var8 = Intrinsics.areEqual(target.getOffHandStack().getItem(), Items.SHIELD);
         }

         if (!var8) {
            var9 = target;
            if (var5) {
               break label38;
            }

            if (!Intrinsics.areEqual(target.getMainHandStack().getItem(), Items.SHIELD)) {
               return false;
            }
         }

         var9 = target;
      }

      Vec3d var10 = var9.getRotationVector(0.0F, target.headYaw);
      Intrinsics.checkNotNullExpressionValue(var10, "calculateViewVector(...)");
      Vec3d var6 = var10;
      Vec3d var11 = bp.toCenterPos().add(0.0, 0.5, 0.0).relativize(AntiKnockBack.INSTANCE.getPos(target));
      Intrinsics.checkNotNullExpressionValue(var11, "vectorTo(...)");
      Vec3d var7 = var11;
      double var13;
      int var12 = (var13 = new Vec3d(var7.x, 0.0, var7.z).normalize().dotProduct(var6) - 0.0) == 0.0 ? 0 : (var13 < 0.0 ? -1 : 1);
      if (!var5) {
         var12 = var12 < 0 ? 1 : 0;
      }

      return (boolean)var12;
   }

   private static final Float D(BlockPos var0, Vec3d var1) {
      MovementUtil var10000 = MovementUtil.INSTANCE;
      Vec3d var7 = new Vec3d(var0.getX() + var1.x, var0.getY() + var1.y, var0.getZ() + var1.z);
      return var10000.distanceTo(var7);
   }

   private static final Float b(Function1 var0, Object var1) {
      return (Float)var0.invoke(var1);
   }

   static {
      long var20 = a ^ 102806594955871L;
      zkmSetFlag(true);
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[25];
      int var16 = 0;
      String var15 = "Ù//VrÃÀ·u²ç/c+\u0084,zÁT'¡¶%3\u0010¨\u009a\u000e\u0012\u0015mP\u009eÄª;ø\u0088z2ø 6·\u0011TÜ\u0089\u0007òA\u0087ãq! \u001b\u0082.ÇIê\u0093êÄªôæ\u0002ä\u0088\u0089pª\u0010ïÎ!\r$W8|\u0088\u0005¸g\u000bÈ×\u001f(G>Ø\u0089û>mfÏ\u0000ñ¿\u000e\u0012½×C½\u0013P³BX]Â\u0011aü¡ý\u000enª}AÙ¢¸å9\u0018q·A\u0083#Ó\tw\u0012.cÞ$\u0087X{áß\u0097¤dÿ« \u00109\u009b\u0097iz\u0015µN5»µH}V-Ó\u0010¼O¶AuÓJ{á´:\u0090\u0017E³7 Åä\u0097ÿM\u0000\u0086\fõè\u008a\u0012¯c\u009cC\u0002kVl$ì\u001be*\u0015\u008dÚî\u0016IÐ\u0018\u0000Õ;Ò\u0003ÐFÓúÏ6öe%\u009a÷2¤lÉÉð2# ¹\u0098^Ê´«ÆtD\u0082&\u009dµ+ª\u0013Ô¹ö\n¿Küµ\u008b\u0086\u0084V·0i\u00ad(xn\u0090¹¦8É¥\f¢þñ\u0090'üj\u0097d\u0012Ü\u0018 \u008c\u008d\u009f\u0010\u001dÒA\fÊ2õy.vÄ\u009b\u009e[\u0018FIAyWk¦ÏW\u009e.¦Y\nãýÚq\u001dñ6y\u0098\u0019\u0010\u0007Ë¦ñü%Õ23Ìbb\u00013³^(>\u008eä\u009e\u0000V4Ëbr\r[\u001böq\u0085\u008df®.¸¯7O`µ\u0003³ÑÅ>ûÝ²oHzÉ\u0007ö\u0010i\u0085âä\u0097\u0002ä\u0000]Ì1Ö\u0087ïäD\u0010/\u0095«>Î¸J\u009b\u009cÆþÍ*øí28MV,Íó\u001d\u0092\u0094Y¶Ê ½£ð1\u001fÎtj\u0095ð\u008c±\u0093'\u007f¾\u0097\"}¥(æ\u001a{D\u0082t]Ý[f\u0013³c¨ß<\u0099p\u0084ª\u001c}\u009c\u0018W¦/\u0083êÌÁ/zwæµ\u008d0 =j&b¦\u000bA\u0011\u0080(Y6ýÐ\u0014Be\u008fLÚú\u0091³z©»&o\u0015\u000b\u0006E,\u0001\u0092Çb§\u0015»8\u008eO\u0082¢\u0006.r\u0080Þ\u0010\u00146µ\u0005^LvN¢£2\u001dJTßõ\u0010¬IUÑÝÝ¥\u009f:\u008bêW\\\u0091\u0019G [á\"\u001bÏÜFö¨Ä\u0007H\\º\teÑÝ¸ÇQx\u001eÚ;\u0010Ï\u000fJ\u0092\u0093\u000b";
      short var17 = 630;
      char var14 = 24;
      int var25 = -1;

      label54:
      while (true) {
         String var27 = var15.substring(++var25, var25 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var27.getBytes("ISO-8859-1"));
            String var38 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var38;
                  if ((var25 += var14) >= var17) {
                     c = var18;
                     d = new String[25];
                     i = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[14];
                     int var3 = 0;
                     String var4 = "\u0010\u008cØ|ÂY\u001ca\u0080n\u001f\u009e\u0098£CÕ\u0082±å\u00177C\u0018\u0017\u009d\"µ\u001cIYÞ;Mò\u0007¡\u001fú|\u0088\u0017%÷î\u0003r¼ûBíýkqê-:ëñ²\u0004¼°,÷/Pæ\u0095 \u008e\u008d@\u009c\\þìºYÝoª\u0015¨K5n\u0087èb#7;Ú§w\"";
                     byte var5 = 96;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var30 = var6;
                        var10001 = var3++;
                        long var42 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var45 = -1;

                        while (true) {
                           long var8 = var42;
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
                           long var47 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var45) {
                              case 0:
                                 var30[var10001] = var47;
                                 if (var2 >= var5) {
                                    f = var6;
                                    g = new Integer[14];
                                    Pair[] var22 = new Pair[]{
                                       TuplesKt.to(new Vec3d(0.5, 1.0, 0.5), true),
                                       TuplesKt.to(new Vec3d(0.5, 0.0, 0.5), true),
                                       TuplesKt.to(new Vec3d(0.5, 0.5, 0.0), true),
                                       TuplesKt.to(new Vec3d(0.5, 0.5, 1.0), true),
                                       TuplesKt.to(new Vec3d(0.0, 0.5, 0.5), true),
                                       TuplesKt.to(new Vec3d(1.0, 0.5, 0.5), true),
                                       TuplesKt.to(new Vec3d(0.05, 0.05, 0.05), false),
                                       TuplesKt.to(new Vec3d(0.05, 0.05, 0.95), false),
                                       TuplesKt.to(new Vec3d(0.05, 0.95, 0.05), false),
                                       TuplesKt.to(new Vec3d(0.05, 0.95, 0.95), false),
                                       TuplesKt.to(new Vec3d(0.95, 0.05, 0.05), false),
                                       TuplesKt.to(new Vec3d(0.95, 0.05, 0.95), false),
                                       TuplesKt.to(new Vec3d(0.95, 0.95, 0.05), false),
                                       TuplesKt.to(new Vec3d(0.95, 0.95, 0.95), false)
                                    };
                                    l = MapsKt.mapOf(var22);
                                    Vec3d[] var26 = new Vec3d[]{
                                       new Vec3d(0.51, 0.0, 0.49),
                                       new Vec3d(0.05, 0.0, 0.05),
                                       new Vec3d(0.05, 0.0, 0.95),
                                       new Vec3d(0.95, 0.0, 0.05),
                                       new Vec3d(0.95, 0.0, 0.95),
                                       new Vec3d(0.475, 0.0, 0.95),
                                       new Vec3d(0.475, 0.0, 0.05),
                                       new Vec3d(0.95, 0.0, 0.025),
                                       new Vec3d(0.05, 0.0, 0.475)
                                    };
                                    E = CollectionsKt.listOf(var26);
                                    return;
                                 }
                                 break;
                              default:
                                 var30[var10001] = var47;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "\u0018G^Êm\\2\u009dîß³ï\u0083»äª";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var36 = var2;
                           var2 += 8;
                           var7 = var4.substring(var36, var2).getBytes("ISO-8859-1");
                           var30 = var6;
                           var10001 = var3++;
                           var42 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var45 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var25);
                  break;
               default:
                  var18[var16++] = var38;
                  if ((var25 += var14) < var17) {
                     var14 = var15.charAt(var25);
                     continue label54;
                  }

                  var15 = "¾\u0087±VHÌ¥^ôa»9\u0004.8þ¶#l\u0095{\u0012\u0013\u000f¹J\u0090û_Üá+\u0010«Î\u009f:\t\u0097ÊJ\u0098¼È\u001b\u001b\u00062ü";
                  var17 = 49;
                  var14 = ' ';
                  var25 = -1;
            }

            var27 = var15.substring(++var25, var25 + var14);
            var10001 = 0;
         }
      }
   }

   public static void zkmSetFlag(boolean var0) {
      N = var0;
   }

   public static boolean zkmGetFlag() {
      return N;
   }

   public static boolean D() {
      return false;
   }

   private static NoSuchElementException zkmRethrow(NoSuchElementException var0) {
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 14885;
      if (d[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().threadId();
            var4 = (Object[])e.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               e.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("su/catlean/rb", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = c[var5].getBytes("ISO-8859-1");
         d[var5] = zkmDecodeUtf8(((Cipher)var4[0]).doFinal(var9));
      }

      return d[var5];
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
         throw new RuntimeException("su/catlean/rb" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 2636;
      if (g[var3] == null) {
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
         long var5 = f[var3];
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
         Object[] var9 = (Object[])i.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               i.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/rb", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         g[var3] = var15;
      }

      return g[var3];
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
         throw new RuntimeException("su/catlean/rb" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
