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
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.interfaces.Resolvable;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AuraSortMode;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.module.rage.MaceElytraTarget;
import su.catlean.util.ArmorUtil;
import su.catlean.util.EntityUtil;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.ItemUtil;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TargetManager {
   @NotNull
   public static final TargetManager INSTANCE;
   @NotNull
   private static final Set u;
   private static final long a = ZkmKeys.a(-4464292581146452936L, -7821336938314189425L, MethodHandles.lookup().lookupClass()).a(71815905138206L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long[] e;
   private static final Integer[] f;
   private static final Map g;

   private TargetManager() {
   }

   @NotNull
   public final Set d() {
      return u;
   }

   @Nullable
   public final Entity y(int a, short a, short a, @Nullable Entity currentTraget) {
      long var5 = ((long)axxx << 32 | (long)ax << 48 >>> 32 | (long)axx << 48 >>> 48) ^ a;
      long var7 = (var5 ^ 60769064109749L) >>> 8;
      int var9 = (int)((var5 ^ 60769064109749L) << 56 >>> 56);
      long var10 = var5 ^ 123446667839610L;
      boolean var10000 = ItemUtil.V();
      Entity var15 = this.R(var10);
      boolean var14 = var10000;
      Entity var16 = currentTraget;
      if (var14) {
         if (currentTraget != null && Aura.INSTANCE.getSort() != AuraSortMode.FOV) {
            if (!var14) {
               return var15;
            }

            if (!(var15 instanceof ProjectileEntity) && !this.r(currentTraget, var7, (byte)var9)) {
               return currentTraget;
            }
         }

         var16 = var15;
      }

      return var16;
   }

   private final Entity R(long var1) {
      var1 = a ^ var1;
      long var7 = var1 ^ 129403197313304L;
      long var13 = (var1 ^ 63076392354867L) >>> 8;
      int var15 = (int)((var1 ^ 63076392354867L) << 56 >>> 56);
      List var21 = new ArrayList();

      for (Object var10000 : Globals.getWorld().getEntities()) {
         Intrinsics.checkNotNullExpressionValue(var10000, "next(...)");
         Entity var23 = (Entity)var10000;
         if ((var23 instanceof ShulkerBulletEntity || var23 instanceof FireballEntity)
            && ((ProjectileEntity)var23).isAlive()
            && this.e(var7, var23)
            && Aura.INSTANCE.getProjectiles()) {
            return var23;
         }

         if (!this.r(var23, var13, (byte)var15) && var23 instanceof LivingEntity) {
            var21.add(var23);
         }
      }
      return switch (TargetManager$WhenMappings.i[Aura.INSTANCE.getSort().ordinal()]) {
         case 1 -> {
            Iterable var35 = var21;
            Iterator var37 = var35.iterator();
            Object var55;
            if (!var37.hasNext()) {
               var55 = null;
            } else {
               Object var39 = var37.next();
               if (!var37.hasNext()) {
                  var55 = var39;
               } else {
                  LivingEntity var43 = (LivingEntity)var39;
                  double var44 = Globals.getPlayer().squaredDistanceTo(EntityUtil.getPos(var43));

                  do {
                     Object var46 = var37.next();
                     LivingEntity var50 = (LivingEntity)var46;
                     double var51 = Globals.getPlayer().squaredDistanceTo(EntityUtil.getPos(var50));
                     if (Double.compare(var44, var51) > 0) {
                        var39 = var46;
                        var44 = var51;
                     }
                  } while (var37.hasNext());

                  var55 = var39;
               }
            }

            yield (Entity)var55;
         }
         case 2 -> {
            Iterable var34 = var21;
            Iterator var36 = var34.iterator();
            Object var54;
            if (!var36.hasNext()) {
               var54 = null;
            } else {
               Object var38 = var36.next();
               if (!var36.hasNext()) {
                  var54 = var38;
               } else {
                  LivingEntity var41 = (LivingEntity)var38;
                  float var42 = INSTANCE.i(var41);

                  do {
                     Object var45 = var36.next();
                     LivingEntity var48 = (LivingEntity)var45;
                     float var49 = INSTANCE.i(var48);
                     if (Float.compare(var42, var49) > 0) {
                        var38 = var45;
                        var42 = var49;
                     }
                  } while (var36.hasNext());

                  var54 = var38;
               }
            }

            yield (Entity)var54;
         }
         case 3 -> {
            Iterable var24 = var21;
            Iterator var26 = var24.iterator();
            Object var52;
            if (!var26.hasNext()) {
               var52 = null;
            } else {
               Object var27 = var26.next();
               if (!var26.hasNext()) {
                  var52 = var27;
               } else {
                  LivingEntity var28 = (LivingEntity)var27;
                  float var40 = var28.getHealth() + var28.getAbsorptionAmount();

                  do {
                     Object var30 = var26.next();
                     LivingEntity var31 = (LivingEntity)var30;
                     float var47 = var31.getHealth() + var31.getAbsorptionAmount();
                     if (Float.compare(var40, var47) > 0) {
                        var27 = var30;
                        var40 = var47;
                     }
                  } while (var26.hasNext());

                  var52 = var27;
               }
            }

            yield (Entity)var52;
         }
         default -> throw new NoWhenBranchMatchedException();
      };
   }

   private final boolean r(Entity var1, long var2, byte var4) {
      long var5 = (var2 << 8 | (long)var4 << 56 >>> 56) ^ a;
      long var18 = var5 ^ 87100473749042L;
      long var26 = var5 ^ 55747788939223L;
      long var32 = var5 ^ 69250937066043L;
      byte var34 = ZkmReferences.zkmBootstrap<"Å">(-7616811938230501700L, var5);
      int var10000 = this.z(var1, var18);
      if (var34 == 0) {
         if (var10000) {
            return false;
         }

         var10000 = var1 instanceof LivingEntity;
      }

      if (var34 == 0) {
         if (!var10000) {
            return true;
         }

         var10000 = ((LivingEntity)var1).isDead();
      }

      if (var34 == 0) {
         label243:
         if (!var10000) {
            var10000 = ((LivingEntity)var1).isAlive();
            int var10001 = var34;
            if (var4 >= 0) {
               if (var34 == 0) {
                  if (var10000 == 0) {
                     break label243;
                  }

                  var10000 = var1 instanceof ArmorStandEntity;
               }

               var10001 = var34;
            }

            if (var4 > 0) {
               if (var10001 == 0) {
                  if (var10000 != 0) {
                     return true;
                  }

                  var10000 = var1 instanceof CatEntity;
               }

               var10001 = var34;
            }

            if (var4 >= 0) {
               if (var10001 == 0) {
                  if (var10000 != 0) {
                     return true;
                  }

                  var10000 = var1.age;
               }

               var10001 = var34;
            }

            if (var10001 == 0) {
               if (var10000 >= 0) {
                  int var45;
                  label223: {
                     var36 = MovementUtil.INSTANCE;
                     var39 = EntityUtil.getPos(var1);
                     Aura var10002 = Aura.INSTANCE;
                     if (var34 == 0) {
                        if (Aura.INSTANCE.getFov() == 1) {
                           var45 = zkmBootstrap2<"s">(32202, 7644860556738955630L ^ var5);
                           break label223;
                        }

                        var10002 = Aura.INSTANCE;
                     }

                     var45 = var10002.getFov();
                  }

                  var10000 = var36.getYawTo(var39, var45);
                  if (var34 == 0) {
                     if (var10000 == 0) {
                        return true;
                     }

                     var10000 = this.f(var32, var1);
                  }

                  var10001 = var34;
                  if (var4 >= 0) {
                     if (var34 == 0) {
                        if (var10000 != 0) {
                           return true;
                        }

                        var10000 = var1 instanceof PlayerEntity;
                     }

                     var10001 = var34;
                  }

                  label212: {
                     label211:
                     if (var10001 == 0) {
                        if (var10000 != 0) {
                           boolean var38 = Intrinsics.areEqual(var1, Globals.getPlayer());
                           if (var34 != 0) {
                              return var38;
                           }

                           if (var38) {
                              return true;
                           }

                           var10000 = Friends.getIsHomie((PlayerEntity)var1);
                           var10001 = var34;
                           if (var4 > 0) {
                              if (var34 == 0) {
                                 if (var10000 != 0) {
                                    return true;
                                 }

                                 var10000 = ((PlayerEntity)var1).isCreative();
                              }

                              var10001 = var34;
                           }

                           label175: {
                              label174:
                              if (var10001 == 0) {
                                 if (var10000 != 0) {
                                    var10000 = Aura.INSTANCE.Bo();
                                    var10001 = var34;
                                    if (var4 <= 0) {
                                       break label175;
                                    }

                                    if (var34 != 0) {
                                       break label174;
                                    }

                                    if (var10000 != 0) {
                                       return true;
                                    }
                                 }

                                 var10000 = ((PlayerEntity)var1).getArmor();
                              }

                              var10001 = var34;
                           }

                           label205:
                           if (var10001 == 0) {
                              if (var10000 == 0) {
                                 var10000 = Aura.INSTANCE.r();
                                 if (var34 != 0) {
                                    break label205;
                                 }

                                 if (var10000 != 0) {
                                    return true;
                                 }
                              }

                              var10000 = ((PlayerEntity)var1).isInvisible();
                           }

                           label198: {
                              label197:
                              if (var34 == 0) {
                                 if (var10000 != 0) {
                                    var10000 = Aura.INSTANCE.B0();
                                    var10001 = var34;
                                    if (var4 <= 0) {
                                       break label198;
                                    }

                                    if (var34 != 0) {
                                       break label197;
                                    }

                                    if (var10000 != 0) {
                                       return true;
                                    }
                                 }

                                 var10000 = ((PlayerEntity)var1).getTeamColorValue();
                              }

                              var10001 = var34;
                           }

                           if (var4 > 0) {
                              if (var10001 != 0) {
                                 break label211;
                              }

                              var10001 = Globals.getPlayer().getTeamColorValue();
                           }

                           if (var10000 == var10001) {
                              var10000 = Aura.INSTANCE.K();
                              if (var34 != 0) {
                                 break label211;
                              }

                              if (var10000 != 0) {
                                 var10000 = Globals.getPlayer().getTeamColorValue();
                                 var10001 = var34;
                                 if (var4 < 0) {
                                    break label212;
                                 }

                                 if (var34 != 0) {
                                    break label211;
                                 }

                                 if (var10000 != zkmBootstrap2<"s">(14674, 1402068653985615351L ^ var5)) {
                                    return true;
                                 }
                              }
                           }
                        }

                        var10000 = this.e(var26, var1);
                     }

                     var10001 = var34;
                  }

                  if (var10001 == 0) {
                     if (var10000 != 0) {
                        byte var46 = var1.hasCustomName();
                        if (var34 == 0) {
                           if (var46 == 0) {
                              return (boolean)0;
                           }

                           var46 = Aura.INSTANCE.Bn();
                        }

                        if (var34 != 0) {
                           return (boolean)var46;
                        }

                        if (var46 == 0) {
                           return (boolean)0;
                        }
                     }

                     var10000 = 1;
                  }

                  return (boolean)var10000;
               }

               var10000 = 1;
            }

            return (boolean)var10000;
         }

         var10000 = 1;
      }

      return (boolean)var10000;
   }

   private final boolean z(Entity var1, long var2) {
      boolean var13;
      int var10000;
      boolean var10001;
      label57: {
         var2 = a ^ var2;
         var13 = ZkmReferences.zkmBootstrap<"Å">(-5582892822541182350L, var2);
         var10000 = var1 instanceof ShulkerBulletEntity;
         label56:
         if (!var13) {
            if (var10000 == 0) {
               var10000 = var1 instanceof FireballEntity;
               var10001 = var13;
               if (var2 <= 0L) {
                  break label57;
               }

               if (var13) {
                  break label56;
               }

               if (var10000 == 0) {
                  return (boolean)0;
               }
            }

            var10000 = ((ProjectileEntity)var1).isAlive();
         }

         var10001 = var13;
      }

      if (var2 >= 0L) {
         if (!var10001) {
            if (var10000 == 0) {
               return (boolean)0;
            }

            MovementUtil var16 = MovementUtil.INSTANCE;
            Vec3d var14 = EntityUtil.getPos(var1);
            float var17;
            var10000 = (var17 = var16.distanceTo(var14) - this.l()) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
         }

         var10001 = var13;
      }

      if (var2 >= 0L) {
         if (!var10001) {
            if (var10000 >= 0) {
               return (boolean)0;
            }

            var10000 = Aura.INSTANCE.getProjectiles();
         }

         var10001 = var13;
      }

      if (var10001) {
         return (boolean)var10000;
      } else {
         return (boolean)(var10000 != 0 ? 1 : 0);
      }
   }

   private final boolean f(long var1, Entity var3) {
      long var11;
      boolean var23;
      boolean var10000;
      boolean var10001;
      label196: {
         var1 = a ^ var1;
         var11 = var1 ^ 74029414627546L;
         var23 = ZkmReferences.zkmBootstrap<"Å">(-8174907148865116549L, var1);
         var10000 = var3 instanceof HostileEntity;
         label195:
         if (!var23) {
            if (var10000) {
               var10000 = Aura.INSTANCE.t();
               var10001 = var23;
               if (var1 < 0L) {
                  break label196;
               }

               if (var23) {
                  break label195;
               }

               if (!var10000) {
                  return true;
               }
            }

            var10000 = Aura.INSTANCE.Y();
         }

         var10001 = var23;
      }

      label185: {
         label184:
         if (!var10001) {
            if (var10000) {
               label180: {
                  var10000 = var3 instanceof EndermanEntity;
                  label179:
                  if (!var23) {
                     if (var10000) {
                        var10000 = ((EndermanEntity)var3).isAngry();
                        var10001 = var23;
                        if (var1 < 0L) {
                           break label180;
                        }

                        if (var23) {
                           break label179;
                        }

                        if (!var10000) {
                           return true;
                        }
                     }

                     var10000 = var3 instanceof ZombifiedPiglinEntity;
                  }

                  var10001 = var23;
               }

               label169: {
                  label168:
                  if (!var10001) {
                     if (var10000) {
                        var10000 = ((ZombifiedPiglinEntity)var3).isAttacking();
                        var10001 = var23;
                        if (var1 <= 0L) {
                           break label169;
                        }

                        if (var23) {
                           break label168;
                        }

                        if (!var10000) {
                           return true;
                        }
                     }

                     var10000 = var3 instanceof WolfEntity;
                  }

                  var10001 = var23;
               }

               if (var10001) {
                  break label184;
               }

               if (var10000) {
                  var10000 = ((WolfEntity)var3).isAttacking();
                  var10001 = var23;
                  if (var1 <= 0L) {
                     break label185;
                  }

                  if (var23) {
                     break label184;
                  }

                  if (!var10000) {
                     return true;
                  }
               }
            }

            var10000 = var3 instanceof PlayerEntity;
         }

         var10001 = var23;
      }

      label149: {
         label148:
         if (!var10001) {
            if (var10000) {
               var10000 = Aura.INSTANCE.F();
               var10001 = var23;
               if (var1 <= 0L) {
                  break label149;
               }

               if (var23) {
                  break label148;
               }

               if (!var10000) {
                  return true;
               }
            }

            var10000 = var3 instanceof PlayerEntity;
         }

         var10001 = var23;
      }

      label138: {
         label137:
         if (!var10001) {
            if (var10000) {
               var10000 = Aura.INSTANCE.s();
               if (var23) {
                  break label137;
               }

               if (var10000) {
                  var10000 = this.Q((PlayerEntity)var3, var11);
                  var10001 = var23;
                  if (var1 <= 0L) {
                     break label138;
                  }

                  if (var23) {
                     break label137;
                  }

                  if (var10000) {
                     return true;
                  }
               }
            }

            var10000 = var3 instanceof VillagerEntity;
         }

         var10001 = var23;
      }

      label124: {
         label123:
         if (!var10001) {
            if (var10000) {
               var10000 = Aura.INSTANCE.j();
               var10001 = var23;
               if (var1 <= 0L) {
                  break label124;
               }

               if (var23) {
                  break label123;
               }

               if (!var10000) {
                  return true;
               }
            }

            var10000 = var3 instanceof MobEntity;
         }

         var10001 = var23;
      }

      label113: {
         label112:
         if (!var10001) {
            if (var10000) {
               var10000 = Aura.INSTANCE.B3();
               var10001 = var23;
               if (var1 <= 0L) {
                  break label113;
               }

               if (var23) {
                  break label112;
               }

               if (!var10000) {
                  return true;
               }
            }

            var10000 = var3 instanceof AnimalEntity;
         }

         var10001 = var23;
      }

      if (var1 > 0L) {
         if (!var10001) {
            if (!var10000) {
               return false;
            }

            var10000 = Aura.INSTANCE.x();
         }

         var10001 = var23;
      }

      return var10001 ? var10000 : !var10000;
   }

   private final boolean e(long var1, Entity var3) {
      var1 = a ^ var1;
      long var4 = var1 ^ 87574342969287L;
      boolean var13 = ItemUtil.V();
      MovementUtil var10000 = MovementUtil.INSTANCE;
      Vec3d var14 = Aura.INSTANCE.getRotationMode().getAimMode().getAimPoint(var4, var3);
      float var17;
      int var16 = (var17 = var10000.distanceTo(var14) - this.l()) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
      if (var13) {
         var16 = var16 <= 0 ? 1 : 0;
      }

      return (boolean)var16;
   }

   private final float i(LivingEntity var1) {
      return (float)Math.abs(
         MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(var1.getZ() - Globals.getPlayer().getZ(), var1.getX() - Globals.getPlayer().getX())) - 90.0)
            - MathHelper.wrapDegrees(Globals.getPlayer().getYaw())
      );
   }

   private final float l() {
      float var10 = Aura.INSTANCE.getAttackRange() + 2.0F;
      if (Globals.getPlayer().isGliding() || MaceElytraTarget.INSTANCE.getEnabled()) {
         var10 += 64.0F;
      }

      if (!Aura.INSTANCE.isElytraTargeting().getAimMode().i()) {
         var10 = Aura.INSTANCE.getAttackRange();
      }

      return var10;
   }

   public final void L(@NotNull Function0 action, long a) {
      ax = a ^ ax;
      long var4 = ax ^ 38797378271343L;
      Intrinsics.checkNotNullParameter(action, "action");
      boolean var10000 = ItemUtil.V();
      List var10001 = Globals.getWorld().getPlayers();
      Intrinsics.checkNotNullExpressionValue(var10001, "players(...)");
      Iterable var12 = var10001;
      var var15 = new ArrayList();
      Iterator var17 = var12.iterator();
      boolean var10 = var10000;

      while (var17.hasNext()) {
         Object var18 = var17.next();
         var10000 = var18 instanceof OtherClientPlayerEntity;
         if (var10) {
            if (!var10000) {
               continue;
            }

            var15.add(var18);
         }

         if (!var10) {
            Module.zkmSetFlag(new Module[4]);
            break;
         }
      }

      List var11 = var15;
      var12 = var11;
      Iterator var14 = var12.iterator();

      while (true) {
         if (var14.hasNext()) {
            Object var22 = var14.next();
            OtherClientPlayerEntity var16 = (OtherClientPlayerEntity)var22;
            INSTANCE.q(var16, var4);
            if (!var10) {
               break;
            }

            if (var10) {
               continue;
            }
         }

         action.invoke();
         var12 = var11;
         break;
      }

      for (Object var23 : var12) {
         OtherClientPlayerEntity var24 = (OtherClientPlayerEntity)var23;
         INSTANCE.S(var24);
         if (!var10 || !var10) {
            break;
         }
      }
   }

   private final void q(PlayerEntity var1, long var2) {
      var2 = a ^ var2;
      long var8 = var2 ^ 58377656640851L;
      boolean var14 = ItemUtil.V();
      int var10000 = this.i(var1);
      if (var14) {
         if (var10000 == 0) {
            Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type su.catlean.interfaces.Resolvable");
            ((Resolvable)var1).savePosition(Vec3d.ZERO);
            if (Intrinsics.areEqual(Aura.INSTANCE.getTarget(), var1)) {
               ElytraTarget var24 = ElytraTarget.INSTANCE;
               Box var10001 = var1.getBoundingBox();
               Intrinsics.checkNotNullExpressionValue(var10001, "getBoundingBox(...)");
               var24.setTargetBox(var10001);
            }

            return;
         }

         Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type su.catlean.interfaces.Resolvable");
         ((Resolvable)var1).savePosition(EntityUtil.getPos(var1));
         var10000 = var1.isGliding();
      }

      if (var14) {
         if (var10000 == 0) {
            return;
         }

         var10000 = ElytraTarget.INSTANCE.getEnabled();
      }

      label48: {
         if (var14) {
            if (var10000 == 0) {
               return;
            }

            var23 = ElytraTarget.INSTANCE.getTargetSpeed(var8);
            if (!var14) {
               break label48;
            }

            float var25;
            var10000 = (var25 = var23 - 0.0F) == 0.0F ? 0 : (var25 < 0.0F ? -1 : 1);
         }

         if (var10000 <= 0) {
            return;
         }

         var23 = ElytraTarget.INSTANCE.getTargetSpeed(var8);
      }

      float var15 = var23;
      double var16 = var1.getX() + (var1.getX() - var1.lastX) * var15;
      double var18 = var1.getY() + (var1.getY() - var1.lastY) * var15;
      double var20 = var1.getZ() + (var1.getZ() - var1.lastZ) * var15;
      var1.setPosition(var16, var18, var20);
      if (Intrinsics.areEqual(Aura.INSTANCE.getTarget(), var1)) {
         ElytraTarget.INSTANCE.setTargetBox(new Box(var16 - 0.3, var18 + 0.0, var20 - 0.3, var16 + 0.3, var18 + 0.6, var20 + 0.3));
      }
   }

   private final void S(PlayerEntity var1) {
      boolean var10000 = ItemUtil.V();
      Intrinsics.checkNotNull(var1, "null cannot be cast to non-null type su.catlean.interfaces.Resolvable");
      boolean var2 = var10000;
      Resolvable var3 = (Resolvable)var1;
      if (var2) {
         if (Intrinsics.areEqual(var3.getSavedPosition(), Vec3d.ZERO)) {
            return;
         }

         var1.setPosition(((Resolvable)var1).getSavedPosition());
         var3 = (Resolvable)var1;
      }

      var3.savePosition(Vec3d.ZERO);
   }

   private final boolean Q(PlayerEntity var1, long var2) {
      var2 = a ^ var2;
      boolean var8 = ZkmReferences.zkmBootstrap<"Å">(-4884241495776509392L, var2);
      PlayerEntity var10000 = var1;
      if (var8) {
         if (Intrinsics.areEqual(var1, Globals.getPlayer())) {
            return false;
         }

         var10000 = var1;
      }

      List var9;
      label213: {
         var9 = CollectionsKt.toList(ArmorUtil.getArmorItems(var10000));
         var var11 = var9;
         var28 = var11;
         label208:
         if (var8) {
            if (var11 instanceof Collection) {
               var28 = (Collection)var11;
               if (!var8) {
                  break label208;
               }

               if (var28.isEmpty()) {
                  var29 = true;
                  break label213;
               }
            }

            var28 = var11;
         }

         Iterator var13 = var28.iterator();

         label199:
         while (true) {
            if (!var13.hasNext()) {
               var29 = true;
               break;
            }

            Object var14 = var13.next();
            ItemStack var15 = (ItemStack)var14;
            var29 = Intrinsics.areEqual(var15.getItem(), Items.AIR);

            while (true) {
               boolean var10001 = var8;

               do {
                  label192: {
                     label217: {
                        if (var10001) {
                           if (var29) {
                              break label217;
                           }

                           var29 = var15.isDamaged();
                        }

                        if (!var8) {
                           break label192;
                        }

                        if (!var29) {
                           var29 = true;
                           break label192;
                        }
                     }

                     var29 = false;
                  }

                  if (var29) {
                     continue label199;
                  }

                  var29 = false;
                  var10001 = var8;
               } while (var2 <= 0L);

               if (var8) {
                  break label199;
               }
            }
         }
      }

      boolean var10;
      label218: {
         var10 = var29;
         var var12 = var9;
         var30 = var12;
         label171:
         if (var8) {
            if (var12 instanceof Collection) {
               var30 = (Collection)var12;
               if (!var8) {
                  break label171;
               }

               if (var30.isEmpty()) {
                  var31 = true;
                  break label218;
               }
            }

            var30 = var12;
         }

         Iterator var23 = var30.iterator();

         label162:
         while (true) {
            if (!var23.hasNext()) {
               var31 = true;
               break;
            }

            Object var26 = var23.next();
            ItemStack var16 = (ItemStack)var26;
            var31 = var16.isEnchantable();

            while (!var31) {
               var31 = false;
               if (var2 >= 0L && var8) {
                  break label162;
               }
            }
         }
      }

      boolean var20;
      boolean var21;
      label220: {
         var20 = var31;
         var21 = Intrinsics.areEqual(var1.getOffHandStack().getItem(), Items.AIR);
         var var24 = var9;
         var32 = var24;
         label149:
         if (var8) {
            if (var24 instanceof Collection) {
               var32 = (Collection)var24;
               if (!var8) {
                  break label149;
               }

               if (var32.isEmpty()) {
                  var33 = false;
                  break label220;
               }
            }

            var32 = var24;
         }

         Iterator var27 = var32.iterator();

         label140:
         while (true) {
            if (!var27.hasNext()) {
               var33 = false;
               break;
            }

            Object var17 = var27.next();
            ItemStack var18 = (ItemStack)var17;
            var33 = u.contains(var18.getItem());

            while (var33) {
               var33 = true;
               if (var2 > 0L && var8) {
                  break label140;
               }
            }
         }
      }

      boolean var22 = var33;
      boolean var34 = Intrinsics.areEqual(var1.getMainHandStack().getItem(), Items.AIR);
      if (var8) {
         var34 = !var34;
      }

      boolean var25 = var34;
      boolean var35 = var10;
      boolean var36 = var8;
      if (var2 >= 0L) {
         if (var8) {
            if (!var10) {
               return false;
            }

            var35 = var20;
         }

         var36 = var8;
      }

      if (var2 >= 0L) {
         if (var36) {
            if (!var35) {
               return false;
            }

            var35 = var21;
         }

         var36 = var8;
      }

      if (var2 >= 0L) {
         if (var36) {
            if (!var35) {
               return false;
            }

            var35 = var22;
         }

         var36 = var8;
      }

      if (var2 > 0L) {
         if (var36) {
            if (!var35) {
               return false;
            }

            var35 = var25;
         }

         var36 = var8;
      }

      return !var36 ? var35 : var35;
   }

   public final boolean i(@NotNull PlayerEntity player) {
      Intrinsics.checkNotNullParameter(player, "player");
      return player.getX() != player.lastX || player.getY() != player.lastY || player.getZ() != player.lastZ;
   }

   static {
      long var20 = a ^ 27199068484574L;
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[7];
      int var16 = 0;
      String var15 = "\u0095Ðwi8ÚÌ\u0097W\u0099\u0019M\u0003Ä\u009bc\u008bÂ:Uò]çk Ô¾\u001aWD\u0085\u0017×æÌ/Æ\u008eÓ\u0095\u0084M\u0003\u008c\u009d',\u0085\u009aKØÜOë[Ï(p\u0003\u001d\u0011\u0007å\u0015ïZÊ/\u0090¶\u001e\u0093\u0081ùûÇM¼Ú?Çk\u0086\u0018\n\u0006R\u0015gê7å~/ðJ\u0013Áÿñ\u0016-\u0013öz\u0090\u009c0ÒSÊª[Ó¦¸\u0085cãÙ*ê\u0093Ùg(¨~S£>eP¦é\u007f\u0017[\u001a/\u0006R\u0003ØÃd\u0091ÿÙ,äF\u0014\u0084½W|ï\b\u00054\u0004\u0018\bT[84ÄW(\u0086x-Ô8xrM\u00815½wwý{<_<ëÈä ù\u0098Y%)ªsJ$\u00834)\u0006Q8\u0097\"×\u0010®KÜ\u008ct\u0013Â¯\u0001Õ}s\u0082çd\u009d";
      short var17 = 228;
      char var14 = 24;
      int var25 = -1;

      label54:
      while (true) {
         String var26 = var15.substring(++var25, var25 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var26.getBytes("ISO-8859-1"));
            String var37 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var37;
                  if ((var25 += var14) >= var17) {
                     b = var18;
                     c = new String[7];
                     g = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[5];
                     int var3 = 0;
                     String var4 = "¦¤\u008dÀm\u000e\u0085bF\u0088;\u000f\u009c{\flyd/\\xc\u001eÕ";
                     byte var5 = 24;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var29 = var6;
                        var10001 = var3++;
                        long var41 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var44 = -1;

                        while (true) {
                           long var8 = var41;
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
                           long var46 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var44) {
                              case 0:
                                 var29[var10001] = var46;
                                 if (var2 >= var5) {
                                    e = var6;
                                    f = new Integer[5];
                                    INSTANCE = new TargetManager();
                                    Item[] var22 = new Item[]{
                                       Items.LEATHER_BOOTS,
                                       Items.LEATHER_LEGGINGS,
                                       Items.LEATHER_CHESTPLATE,
                                       Items.LEATHER_HELMET,
                                       Items.IRON_BOOTS,
                                       Items.IRON_LEGGINGS,
                                       Items.IRON_CHESTPLATE,
                                       Items.IRON_HELMET
                                    };
                                    u = SetsKt.setOf(var22);
                                    return;
                                 }
                                 break;
                              default:
                                 var29[var10001] = var46;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "ÂzÕóï\u0080è\u0080ï«Q\u0006\u0094j!8";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var35 = var2;
                           var2 += 8;
                           var7 = var4.substring(var35, var2).getBytes("ISO-8859-1");
                           var29 = var6;
                           var10001 = var3++;
                           var41 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var44 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var25);
                  break;
               default:
                  var18[var16++] = var37;
                  if ((var25 += var14) < var17) {
                     var14 = var15.charAt(var25);
                     continue label54;
                  }

                  var15 = "IujîvÇÎEö\u001c\u0000\u001dÄ÷\u009a\u0017Õhê8-Ã¾o\u0007BeùV¬ç)\u0088Õ\u008fkFò\u0087Á&[bjÄ·²µ\u0085\u001c\u0004BÏ]\u0088ùØªa+\u008a\fù&-ÔL·çjª\u009dÅÇxÝfñÈ7Ïgøí×>£\u0016i\u0001\u0089ð3 \u001d\u001cü\u0001ÑÇ\u000eB\u008aè®¸ÿôiªòÃÏWx@ª:`á\u0010±ne®Ñ#\u00ad\u008céôÖ]Í\u0010ÝÀ";
                  var17 = 137;
                  var14 = 'x';
                  var25 = -1;
            }

            var26 = var15.substring(++var25, var25 + var14);
            var10001 = 0;
         }
      }
   }

   private static Exception zkmRethrow(Exception var0) {
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 13553;
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
            throw new RuntimeException("su/catlean/sx", var10);
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
         throw new RuntimeException("su/catlean/sx" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 25412;
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
            throw new RuntimeException("su/catlean/sx", var14);
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
         throw new RuntimeException("su/catlean/sx" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
