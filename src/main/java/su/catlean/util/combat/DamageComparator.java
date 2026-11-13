package su.catlean.util.combat;

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
import java.util.NoSuchElementException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.attack.AutoCrystalFriendProtectionMode;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.module.player.SpeedMine;
import su.catlean.util.ArmorUtil;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class DamageComparator {
   private static final long a = ZkmKeys.a(-3146236039537320697L, -5361529564068961534L, MethodHandles.lookup().lookupClass()).a(66330673313676L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long e;

   @Nullable
   public static final PlaceResult pickBest(long a, @NotNull CrystalContext configState, @NotNull List clearedList) {
      ax = a ^ ax;
      long var4 = ax ^ 15799116163078L;
      Intrinsics.checkNotNullParameter(configState, "configState");
      Intrinsics.checkNotNullParameter(clearedList, "clearedList");
      if (clearedList.isEmpty()) {
         return null;
      } else {
         Iterable var8 = clearedList;
         Iterator var10 = var8.iterator();
         if (!var10.hasNext()) {
            throw new NoSuchElementException();
         } else {
            Object var11 = var10.next();
            Object var10000;
            if (!var10.hasNext()) {
               var10000 = var11;
            } else {
               PlaceResult var12 = (PlaceResult)var11;
               float var29 = var12.getDamageData().getDamage();

               do {
                  Object var13 = var10.next();
                  PlaceResult var14 = (PlaceResult)var13;
                  float var34 = var14.getDamageData().getDamage();
                  if (Float.compare(var29, var34) < 0) {
                     var11 = var13;
                     var29 = var34;
                  }
               } while (var10.hasNext());

               var10000 = var11;
            }

            PlaceResult var7 = (PlaceResult)var10000;
            if (configState.isMinimizeSelfDamage() && clearedList.size() > 1) {
               for (PlaceResult var9 : clearedList) {
                  if (!Intrinsics.areEqual(var9, var7)) {
                     float var25 = var7.getDamageData().getDamage() - var9.getDamageData().getDamage();
                     float var27 = var7.getDamageData().getSelfDamage() - var9.getDamageData().getSelfDamage();
                     boolean var30 = var7.getDamageData().getSelfDamage() > var9.getDamageData().getSelfDamage();
                     if (var30
                        && var27 > configState.getMaxDamageReduction()
                        && (var25 <= configState.getMaxDamageReduction() || var9.getDamageData().isLethal())
                        && (!var7.getDamageData().isLethal() || var9.getDamageData().isLethal())) {
                        var7 = var9;
                        break;
                     }
                  }
               }
            }

            if (var7.getData() instanceof BlockHitResult && clearedList.size() > 1) {
               var10000 = var7.getData();
               Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
               var10000 = ((BlockHitResult)var10000).getBlockPos();
               Intrinsics.checkNotNullExpressionValue(var10000, "getBlockPos(...)");
               BlockPos var23 = (BlockPos)var10000;
               List var24 = SpeedMine.getMinePositions$default(SpeedMine.INSTANCE, false, 1, null, var4);
               var10000 = configState.getTarget().getBlockPos();
               Intrinsics.checkNotNullExpressionValue(var10000, "blockPosition(...)");
               BlockPos var26 = (BlockPos)var10000;
               boolean var28 = var23.getX() != var26.getX() || var23.getZ() != var26.getZ();
               if (var24.contains(var23) && var28) {
                  Iterable var32 = clearedList;
                  var var16 = new ArrayList();

                  for (Object var19 : var32) {
                     PlaceResult var20 = (PlaceResult)var19;
                     if (var20.getData() instanceof BlockHitResult && !var24.contains(((BlockHitResult)var20.getData()).getBlockPos())) {
                        var16.add(var19);
                     }
                  }

                  var32 = var16;
                  Iterator var15 = var32.iterator();
                  if (!var15.hasNext()) {
                     var10000 = null;
                  } else {
                     Object var35 = var15.next();
                     if (!var15.hasNext()) {
                        var10000 = var35;
                     } else {
                        PlaceResult var17 = (PlaceResult)var35;
                        float var36 = var17.getDamageData().getDamage();

                        do {
                           Object var37 = var15.next();
                           PlaceResult var38 = (PlaceResult)var37;
                           float var39 = var38.getDamageData().getDamage();
                           if (Float.compare(var36, var39) < 0) {
                              var35 = var37;
                              var36 = var39;
                           }
                        } while (var15.hasNext());

                        var10000 = var35;
                     }
                  }

                  PlaceResult var31 = (PlaceResult)var10000;
                  if (var31 != null) {
                     return var31;
                  }
               }
            }

            return var7;
         }
      }
   }

   private static final boolean isSafe(long var0, float var2, float var3, CrystalContext var4) {
      var0 = a ^ var0;
      String var7 = ZkmReferences.zkmBootstrap<"Å">(-5043271808485864156L, var0);
      int var10000 = var4.isOverrideSelfDamage();
      if (var7 == null) {
         if (var10000) {
            float var8 = var4.getTarget().getHealth() + var4.getTarget().getAbsorptionAmount();
            float var9 = Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount();
            float var16;
            var10000 = (var16 = var2 * var4.getLethalMultiplier() - var8) == 0.0F ? 0 : (var16 < 0.0F ? -1 : 1);
            if (var7 == null) {
               var10000 = var10000 > 0 ? 1 : 0;
            }

            int var10 = var10000;
            float var17;
            var10000 = (var17 = var3 - var9) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
            if (var7 == null) {
               var10000 = var10000 < 0 ? 1 : 0;
            }

            int var11 = var10000;
            float var18;
            var10000 = (var18 = var3 - var4.getMaxSelfDamage()) == 0.0F ? 0 : (var18 < 0.0F ? -1 : 1);
            String var10001 = var7;
            if (var0 > 0L) {
               if (var7 == null) {
                  if (var10000 <= 0) {
                     return (boolean)0;
                  }

                  var10000 = var10;
               }

               var10001 = var7;
            }

            if (var0 >= 0L) {
               if (var10001 == null) {
                  if (var10000 == 0) {
                     return (boolean)0;
                  }

                  var10000 = var11;
               }

               var10001 = var7;
            }

            if (var10001 != null) {
               return (boolean)var10000;
            }

            if (var10000 != 0) {
               return (boolean)1;
            }

            return (boolean)0;
         }

         var10000 = 0;
      }

      return (boolean)var10000;
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   private static final boolean isLethal(float var0, long var1, CrystalContext var3) {
      var1 = a ^ var1;
      long var10001 = var1 ^ 123333434724744L;
      int var7 = (int)((var1 ^ 123333434724744L) >>> 32);
      int var8 = (int)((var1 ^ 123333434724744L) << 32 >>> 48);
      int var9 = (int)(var10001 << 48 >>> 48);
      String var10000 = ZkmReferences.zkmBootstrap<"Å">(5962756204772500889L, var1);
      float var17 = var3.getTarget().getHealth() + var3.getTarget().getAbsorptionAmount();
      String var16 = var10000;
      double var45;
      int var29 = (var45 = var0 - 0.6) == 0.0 ? 0 : (var45 < 0.0 ? -1 : 1);
      if (var16 == null) {
         label302:
         if (var29 >= 0) {
            int var30 = var3.isOverrideMinDamage();
            String var37 = var16;
            if (var1 >= 0L) {
               if (var16 == null) {
                  if (var30 == 0) {
                     break label302;
                  }

                  float var46;
                  var30 = (var46 = var0 * var3.getLethalMultiplier() - var17) == 0.0F ? 0 : (var46 < 0.0F ? -1 : 1);
               }

               var37 = var16;
            }

            label357: {
               if (var1 >= 0L) {
                  if (var37 == null) {
                     if (var30 <= 0) {
                        break label357;
                     }

                     float var47;
                     var30 = (var47 = var0 - 4.0F) == 0.0F ? 0 : (var47 < 0.0F ? -1 : 1);
                  }

                  var37 = var16;
               }

               if (var37 != null) {
                  return (boolean)var30;
               }

               if (var30 <= 0) {
                  byte var52 = Aura.INSTANCE.isAttacking();
                  if (var16 != null) {
                     return (boolean)var52;
                  }

                  if (var52 != 0) {
                     break label357;
                  }
               }

               return (boolean)1;
            }

            label271:
            for (ItemStack var19 : ArmorUtil.getArmorItems(var3.getTarget())) {
               int var31 = Intrinsics.areEqual(var19.getItem(), Items.AIR);

               do {
                  String var38 = var16;

                  do {
                     if (var38 == null) {
                        if (var31 != 0) {
                           continue label271;
                        }

                        float var48;
                        var31 = (var48 = (float)(var19.getMaxDamage() - var19.getDamage()) / var19.getMaxDamage() * (int)e - var3.getArmorScale()) == 0.0F
                           ? 0
                           : (var48 < 0.0F ? -1 : 1);
                     }

                     if (var31 >= 0) {
                        continue label271;
                     }

                     var31 = 1;
                     var38 = var16;
                  } while (var1 <= 0L);
               } while (var16 != null);

               return true;
            }

            label253: {
               var32 = var3.isAntiPortal();
               label252:
               if (var16 == null) {
                  if (var32 != 0) {
                     ClientWorld var33 = Globals.getWorld();
                     AntiKnockBack var39 = AntiKnockBack.INSTANCE;
                     Entity var25 = var3.getTarget();
                     var32 = Intrinsics.areEqual(var33.getBlockState(BlockPos.ofFloored(var39.getPos(var25))).getBlock(), Blocks.NETHER_PORTAL);
                     var40 = var16;
                     if (var1 < 0L) {
                        break label253;
                     }

                     if (var16 != null) {
                        break label252;
                     }

                     if (var32 != 0) {
                        return true;
                     }
                  }

                  var32 = var3.isExplodeEChestFarm();
               }

               var40 = var16;
            }

            label242: {
               label241:
               if (var40 == null) {
                  if (var32 != 0) {
                     var32 = AutoCrystal.INSTANCE.findTarget(var7, (short)var8, var9);
                     var41 = var16;
                     if (var1 < 0L) {
                        break label242;
                     }

                     if (var16 != null) {
                        break label241;
                     }

                     if (var32 != 0) {
                        return true;
                     }
                  }

                  var32 = var3.isAntiQuiver();
               }

               var41 = var16;
            }

            String var42;
            if (var41 != null) {
               var42 = var16;
            } else {
               label331: {
                  label228: {
                     if (var32 != 0) {
                        var32 = var3.getTarget().isHolding(Items.BOW);
                        var42 = var16;
                        if (var1 <= 0L) {
                           break label331;
                        }

                        if (var16 != null) {
                           break label228;
                        }

                        if (var32 != 0) {
                           var var27 = var3.getEntities();
                           var var21 = new ArrayList();
                           Iterator var23 = var27.iterator();

                           label319: {
                              label320: {
                                 label220:
                                 while (true) {
                                    if (var23.hasNext()) {
                                       Object var24 = var23.next();
                                       var34 = var24 instanceof ArrowEntity;
                                       if (var1 < 0L || var16 != null) {
                                          break;
                                       }

                                       if (var16 == null) {
                                          if (!var34) {
                                             continue;
                                          }

                                          var21.add(var24);
                                       }

                                       if (var16 == null) {
                                          continue;
                                       }
                                    }

                                    do {
                                       var27 = var21;
                                       var34 = false;
                                       if (var1 <= 0L) {
                                          break label220;
                                       }

                                       var35 = var27;
                                       if (var1 >= 0L) {
                                          if (var16 != null) {
                                             break label320;
                                          }

                                          var34 = var27 instanceof Collection;
                                          break label220;
                                       }

                                       var34 = var27 instanceof ArrowEntity;
                                       if (var1 < 0L || var16 != null) {
                                          break label220;
                                       }

                                       if (var16 == null) {
                                          if (!var34) {
                                             break;
                                          }

                                          var21.add(var27);
                                       }
                                    } while (var16 == null);
                                 }

                                 if (var34) {
                                    var35 = (Collection)var27;
                                    if (var16 != null) {
                                       break label320;
                                    }

                                    if (var35.isEmpty()) {
                                       var36 = 0;
                                       break label319;
                                    }
                                 }

                                 var35 = var27;
                              }

                              Iterator var20 = var35.iterator();

                              label177:
                              while (true) {
                                 if (!var20.hasNext()) {
                                    var36 = 0;
                                    break;
                                 }

                                 Object var28 = var20.next();
                                 ArrowEntity var22 = (ArrowEntity)var28;
                                 var36 = var22.age;

                                 while (true) {
                                    String var43 = var16;

                                    do {
                                       label170: {
                                          label326: {
                                             if (var43 == null) {
                                                if (var36 >= 5) {
                                                   break label326;
                                                }

                                                double var49;
                                                var36 = (var49 = AntiKnockBack.INSTANCE.getPos(var22).y - var3.getTarget().getY()) == 0.0
                                                   ? 0
                                                   : (var49 < 0.0 ? -1 : 1);
                                             }

                                             String var44 = var16;
                                             if (var1 >= 0L) {
                                                if (var16 == null) {
                                                   if (var36 <= 0) {
                                                      break label326;
                                                   }

                                                   double var50;
                                                   var36 = (var50 = var3.getTarget().getEyePos().squaredDistanceTo(AntiKnockBack.INSTANCE.getPos(var22)) - 9.0)
                                                         == 0.0
                                                      ? 0
                                                      : (var50 < 0.0 ? -1 : 1);
                                                }

                                                var44 = var16;
                                             }

                                             if (var44 != null) {
                                                break label170;
                                             }

                                             if (var36 < 0) {
                                                var36 = 1;
                                                break label170;
                                             }
                                          }

                                          var36 = 0;
                                       }

                                       if (var36 == 0) {
                                          continue label177;
                                       }

                                       var36 = 1;
                                       var43 = var16;
                                    } while (var1 < 0L);

                                    if (var16 == null) {
                                       break label177;
                                    }
                                 }
                              }
                           }

                           if (var1 < 0L) {
                              return (boolean)var36;
                           }

                           if (var36 != 0) {
                              return (boolean)1;
                           }
                        }
                     }

                     float var51;
                     var32 = (var51 = var17 - var3.getFacePlaceHp()) == 0.0F ? 0 : (var51 < 0.0F ? -1 : 1);
                  }

                  var42 = var16;
               }
            }

            if (var1 >= 0L) {
               if (var42 == null) {
                  if (var32 > 0) {
                     return (boolean)0;
                  }

                  var32 = Aura.INSTANCE.isAttacking();
               }

               var42 = var16;
            }

            if (var42 != null) {
               return (boolean)var32;
            }

            if (var32 == 0) {
               return (boolean)1;
            }

            return (boolean)0;
         }

         var29 = 0;
      }

      return (boolean)var29;
   }

   @Nullable
   public static final DamageData compareDamages(
      short a,
      @NotNull AutoCrystal $this$compareDamages,
      @NotNull BlockPos pos,
      long a,
      @NotNull CrystalContext configState,
      boolean ignoreMinDamage,
      boolean place,
      boolean basePlace
   ) {
      long var9 = ((long)ax << 48 | axx << 16 >>> 16) ^ a;
      long var10001 = var9 ^ 126703694191892L;
      int var11 = (int)((var9 ^ 126703694191892L) >>> 32);
      int var12 = (int)((var9 ^ 126703694191892L) << 32 >>> 48);
      int var13 = (int)(var10001 << 48 >>> 48);
      long var16 = var9 ^ 8041370241161L;
      long var18 = var9 ^ 72173469376425L;
      int var22 = (int)((var9 ^ 102555018682893L) << 48 >>> 48);
      long var25 = var9 ^ 74216662212660L;
      long var27 = var9 ^ 88196318658240L;
      String var10000 = ZkmReferences.zkmBootstrap<"Å">(8193384549085709970L, var9);
      Intrinsics.checkNotNullParameter($this$compareDamages, zkmBootstrap<"y">(8024, 1892201955277747449L ^ var9));
      String var31 = var10000;
      Intrinsics.checkNotNullParameter(pos, zkmBootstrap<"y">(30104, 7709114065156333119L ^ var9));
      Intrinsics.checkNotNullParameter(configState, zkmBootstrap<"y">(23219, 2445952105987666206L ^ var9));
      Vec3d var52 = pos.toCenterPos().add(0.0, 0.5, 0.0);
      Intrinsics.checkNotNullExpressionValue(var52, zkmBootstrap<"y">(13817, 7370119300189265498L ^ var9));
      Vec3d var32 = var52;
      List var33 = place != 0 ? SpeedMine.getMinePositions$default(SpeedMine.INSTANCE, false, 1, null, var18) : CollectionsKt.emptyList();
      List var34 = basePlace ? CollectionsKt.listOf(pos) : var33;
      DamageUtil var53 = DamageUtil.INSTANCE;
      PlayerEntity var10002 = configState.getTarget();
      int var10003 = place;
      if (var31 == null) {
         var10003 = place != 0 ? $this$compareDamages.getPredictTicks(var27) : 0;
      }

      float var35;
      var35 = var53.getExplosionDamage(var32, var10002, var10003, configState.isIgnoreTerrain(), var34, basePlace);
      var54 = isLethal(var35, var25, configState);
      label199:
      if (var31 == null) {
         if (var54 == 0) {
            float var65;
            var54 = (var65 = var35 - configState.getMinDamage()) == 0.0F ? 0 : (var65 < 0.0F ? -1 : 1);
            if (var31 != null) {
               break label199;
            }

            if (var54 <= 0) {
               var54 = 0;
               break label199;
            }
         }

         var54 = 1;
      }

      label191: {
         int var36 = var54;
         int var55 = var36;
         if (var31 == null) {
            if (var36 != 0) {
               break label191;
            }

            var55 = ignoreMinDamage;
         }

         if (var55 == 0) {
            return null;
         }
      }

      float var37;
      byte var38;
      label184: {
         label204: {
            var37 = CrystalTimers.INSTANCE
               .K(
                  DamageUtil.INSTANCE
                     .getExplosionDamage(
                        var32,
                        Globals.getPlayer(),
                        $this$compareDamages.getPingTicks(var11, (short)var12, (boolean)place, (short)var13),
                        configState.isIgnoreTerrain(),
                        var34,
                        basePlace
                     )
               );
            var38 = isSafe(var16, var35, var37, configState);
            CrystalContext var56 = configState;
            if (var31 == null) {
               if (configState.getFriendProtection() == AutoCrystalFriendProtectionMode.NONE) {
                  break label204;
               }

               var56 = configState;
            }

            Iterable var40 = var56.getEntities();
            Iterable var42 = var40;
            var var43 = new ArrayList();

            label176: {
               for (Object var46 : var40) {
                  boolean var57 = var46 instanceof PlayerEntity;
                  if (var31 != null) {
                     break label176;
                  }

                  if (var31 == null) {
                     if (!var57) {
                        continue;
                     }

                     var43.add(var46);
                  }

                  if (var31 != null) {
                     break;
                  }
               }

               var40 = var43;
               var42 = var40;
               var43 = new ArrayList();
               boolean var58 = false;
            }

            for (Object var51 : var42) {
               PlayerEntity var47 = (PlayerEntity)var51;
               var59 = var47.isAlive();
               if (var31 != null) {
                  break label184;
               }

               label158: {
                  label207: {
                     String var62 = var31;
                     if (ax >= 0) {
                        if (var31 == null) {
                           if (var59 == 0) {
                              break label207;
                           }

                           var59 = Friends.getIsHomie(var47);
                        }

                        var62 = var31;
                     }

                     if (var62 != null) {
                        break label158;
                     }

                     if (var59 != 0) {
                        var59 = 1;
                        break label158;
                     }
                  }

                  var59 = 0;
               }

               if (var59 != 0) {
                  var43.add(var51);
                  if (var31 != null) {
                     break;
                  }
               }
            }

            Iterator var39 = var43.iterator();

            label139:
            do {
               label137:
               while (var39.hasNext()) {
                  PlayerEntity var49 = (PlayerEntity)var39.next();

                  while (true) {
                     var10003 = place;
                     if (var31 == null) {
                        var10003 = place != 0 ? $this$compareDamages.getPredictTicks(var27) : 0;
                     }

                     float var41 = DamageUtil.INSTANCE.getExplosionDamage(var32, var49, var10003, configState.isIgnoreTerrain(), var34, basePlace);
                     var10000 = var31;
                     if (ax < 0) {
                        continue label139;
                     }

                     if (var31 != null) {
                        break;
                     }

                     if (configState.getFriendProtection() != AutoCrystalFriendProtectionMode.ONLY_LETHAL) {
                        var37 = Math.max(var37, var41);
                        break;
                     }

                     if (!(var41 >= var49.getHealth() + var49.getAbsorptionAmount())) {
                        continue label137;
                     }

                     if (ax >= 0) {
                        if (var31 == null) {
                           return null;
                        }

                        return null;
                     }
                  }

                  var10000 = var31;
                  continue label139;
               }
               break;
            } while (var10000 == null);
         }

         var59 = isEfficient(var35, var37, (boolean)var38, configState, (char)var22);
      }

      String var63 = var31;
      if (ax >= 0) {
         if (var31 == null) {
            if (var59 == 0) {
               return null;
            }

            float var66;
            var59 = (var66 = var37 - configState.getMaxSelfDamage()) == 0.0F ? 0 : (var66 < 0.0F ? -1 : 1);
         }

         var63 = var31;
      }

      if (var63 == null) {
         if (var59 <= 0) {
            return new DamageData(var35, var37, (boolean)var38, var35 >= configState.getTarget().getHealth() + configState.getTarget().getAbsorptionAmount());
         }

         var59 = var38;
      }

      return var59 == 0
         ? null
         : new DamageData(var35, var37, (boolean)var38, var35 >= configState.getTarget().getHealth() + configState.getTarget().getAbsorptionAmount());
   }

   private static final boolean isEfficient(float var0, float var1, boolean var2, CrystalContext var3, char var4) {
      byte var10 = 0;
      byte var11 = 0;
      long var5 = ((long)var10 << 32 | (long)var11 << 48 >>> 32 | (long)var4 << 48 >>> 48) ^ a;
      String var9 = ZkmReferences.zkmBootstrap<"Å">(-8032476600358115424L, var5);
      int var10000 = var2;
      if (var9 == null) {
         if (var2 != 0) {
            return true;
         }

         float var12;
         var10000 = (var12 = var1 - (Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount())) == 0.0F ? 0 : (var12 < 0.0F ? -1 : 1);
      }

      String var10001 = var9;
      if (var4 > 0) {
         if (var9 == null) {
            if (var10000 > 0) {
               return false;
            }

            float var13;
            var10000 = (var13 = var0 / var1 - var3.getEfficiencyFactor()) == 0.0F ? 0 : (var13 < 0.0F ? -1 : 1);
         }

         var10001 = var9;
      }

      if (var10001 == null) {
         var10000 = var10000 >= 0 ? 1 : 0;
      }

      return (boolean)var10000;
   }

   static {
      long var5 = a ^ 114195791113657L;
      Cipher var7;
      Cipher var10000 = var7 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var5 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var8 = 1; var8 < 8; var8++) {
         var10003[var8] = (byte)(var5 << var8 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var14 = new String[9];
      int var12 = 0;
      String var11 = "\u009aÀÃjò\u001fmö¦¸\nÅcFª\u009dWYù61ÙË\\Æãï\u0018î;Ý¾¥\u0004²ß\u0013:ø_\u0080\u001cÐ#G\u001beód6\u0014\u0007Oê\u0006\u0005\u0098ÓßWä$¨ÿmb´ØWKÐÉ#\"1^é\u0081s\u008fo\u001a\u001d\u0002ñ\u000fÍ¡#ëeaÙú]Û¶\u0018@ß2\u009eâ P}`$o\u0097\\Òßäá¡þ)$Á\u0090{K\u0017\u001e\u001aàÛ©\u0094\u009a«ÚòÈºõ\u009c½Tñ\b'\u0081á-rµ\u0016)ÃP|·n8*I\u0000Iïê\u000fýõ\u000fÒP'\u0010\u0001m×>¨\u0081Ú\u009fÐÂ¤â4\u000b \u009d õg¾?*x¶¢!j~K»¤|\u0004Y]×\ruþ\u0006y?þt\u008d*ÖLí\u0010Üq¸¸´¡zÔ\u0003½\"®\u0090\u000bÊÂ0eç¾ð \u0018q\u000bX{h\u0084\tEù\u0001\u0089©©ßçN\u0099Gtek/Ü\u0005Ú\u007f3ÀUb\u0011 Ç@\u008cà\u000e\u0091à(\u0099Ô \u0014¸Ó\u0011\u000b\u000f}Ó \u0087%¯ák\u0002è\u001d]UðÈ·Z¾Ô\u0082È]Aq1d";
      short var13 = 318;
      char var10 = '(';
      int var17 = -1;

      label37:
      while (true) {
         String var18 = var11.substring(++var17, var17 + var10);
         byte var10001 = -1;

         while (true) {
            byte[] var15 = var7.doFinal(var18.getBytes("ISO-8859-1"));
            String var25 = zkmDecodeUtf8(var15).intern();
            switch (var10001) {
               case 0:
                  var14[var12++] = var25;
                  if ((var17 += var10) >= var13) {
                     b = var14;
                     c = new String[9];
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var5 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var5 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     byte[] var4 = var0.doFinal(new byte[]{75, 62, 116, -78, -107, 86, 25, -69});
                     e = (var4[0] & 255L) << 56
                        | (var4[1] & 255L) << 48
                        | (var4[2] & 255L) << 40
                        | (var4[3] & 255L) << 32
                        | (var4[4] & 255L) << 24
                        | (var4[5] & 255L) << 16
                        | (var4[6] & 255L) << 8
                        | var4[7] & 255L;
                     return;
                  }

                  var10 = var11.charAt(var17);
                  break;
               default:
                  var14[var12++] = var25;
                  if ((var17 += var10) < var13) {
                     var10 = var11.charAt(var17);
                     continue label37;
                  }

                  var11 = "\u0097&E°¤\u0089Ñ0]5ý¼\u0091ªôÙ]ý\u0098\u000f\u0098n/%ÝØM\u0094j\u0001zv ÷\u0094\u0096ÔPSÔP)\t \u0013í \u009fÒ\u000b\u0004dzM\u009b÷\u001cL\u0082TÂ\u0013\u0090\fi";
                  var13 = 65;
                  var10 = ' ';
                  var17 = -1;
            }

            var18 = var11.substring(++var17, var17 + var10);
            var10001 = 0;
         }
      }
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 23439;
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
            throw new RuntimeException("su/catlean/ot", var10);
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
         throw new RuntimeException("su/catlean/ot" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
