package su.catlean.util.rotation;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import net.minecraft.block.AirBlock;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AuraRandomizeDelayMode$WhenMappings;
import su.catlean.module.rage.MaceElytraTarget;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PingPredictor;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class DefaultRotationHandler implements RotationHandler {
   @NotNull
   public static final DefaultRotationHandler INSTANCE;
   private static int b;
   private static int u;
   private static int c;
   private static int U;
   private static final long a = ZkmKeys.a(-6245659716847199657L, 8328145373008717844L, MethodHandles.lookup().lookupClass()).a(191144222059247L);
   private static final String d;
   private static final long[] e;
   private static final Integer[] f;
   private static final Map g;

   private DefaultRotationHandler() {
   }

   public final int y() {
      return b;
   }

   public final void A(int <set-?>) {
      b = <set-?>;
   }

   public final int j() {
      return u;
   }

   public final void r(int <set-?>) {
      u = <set-?>;
   }

   public final int u() {
      return c;
   }

   public final void H(int <set-?>) {
      c = <set-?>;
   }

   @Override
   public void preAttack(int var1, long var2) {
   }

   @Override
   public void postRotate(@NotNull Rotation rotation, short a, int a, int a) {
      long var5 = (long)axxx << 48 | (long)ax << 32 >>> 16 | (long)axx << 48 >>> 48;
      long var10 = var5 ^ 132427939850213L;
      int var10000 = ZkmReferences.zkmBootstrap<"Å">(-2859712109970658601L, var5);
      Intrinsics.checkNotNullParameter(rotation, d);
      int var17 = (int)(20.0F / Aura.INSTANCE.Bj()) - 1;
      int var18 = (int)(20.0F / Aura.INSTANCE.L()) - 1;
      int var19 = Math.min(var17, var18);
      int var16 = var10000;
      int var20 = Math.max(var17, var18);
      int var21 = c++;
      var10000 = Aura.INSTANCE.getOldDelay();
      if (var16 != 0) {
         var10000 = var10000 != 0
            ? RangesKt.coerceIn(
               (int)(
                  RangesKt.random(new IntRange(var19, var20), Random.Default)
                     + Math.sin(Globals.now() % zkmBootstrap<"k">(31743, 9209843914663829181L ^ var5) / 100.0 * 2.0 * Math.PI) * 1.5
                     + 1.5
               ),
               var19,
               var20
            )
            : this.V(var10);
      }

      b = var10000;
      u = b;
      var10000 = var16;
      if (axx >= 0) {
         if (var16 != 0) {
            return;
         }

         var10000 = 3;
      }

      ZkmReferences.zkmBootstrap<"Å">(new Module[var10000], -2832550130498809203L, var5);
   }

   @Override
   public void reset() {
      int var1 = b;
      b = var1 + -1;
   }

   public final boolean b(long a, int offset) {
      ax = a ^ ax;
      long var6 = ax ^ 33680716585513L;
      return (this.R(var6, offset) || Aura.INSTANCE.getOldDelay()) && b - offset <= 0;
   }

   public static boolean k(DefaultRotationHandler var0, int var1, long var2, Object var4) {
      var2 = a ^ var2;
      long var5 = var2 ^ 34518990913436L;
      int var7 = zkmIntConstant();
      int var10000 = var1 & 1;
      if (var7 == 0) {
         byte var8 = 0;
         var10000 = var0.b(var5, var8);
      }

      return (boolean)var10000;
   }

   public final boolean z(long var1) {
      var1 = a ^ var1;
      long var3 = var1 ^ 62381482796064L;
      int var7 = zkmIntConstant();
      int var10000 = d(var3, this, 1, null);
      if (var7 == 0) {
         if (var10000 == 0) {
            return (boolean)0;
         }

         double var9;
         var10000 = (var9 = Globals.getPlayer().getAttackCooldownProgress(0.5F) - 0.93) == 0.0 ? 0 : (var9 < 0.0 ? -1 : 1);
      }

      if (var7 != 0) {
         return (boolean)var10000;
      } else {
         return (boolean)(var10000 > 0 ? 1 : 0);
      }
   }

   private final boolean R(long var1, int var3) {
      int var9;
      int var10;
      int var11;
      int var18;
      int var10000;
      label202: {
         label205: {
            var1 = a ^ var1;
            long var10001 = var1 ^ 38180199068669L;
            var9 = (int)((var1 ^ 38180199068669L) >>> 32);
            var10 = (int)((var1 ^ 38180199068669L) << 32 >>> 56);
            var11 = (int)(var10001 << 40 >>> 40);
            var18 = zkmIntConstant();
            var10000 = Globals.getPlayer().isOnGround();
            label200:
            if (var18 == 0) {
               label198:
               if (var10000 == 0) {
                  double var30 = MovementUtil.INSTANCE.getFallDistance();
                  if (var18 == 0) {
                     if (!(var30 > 0.0)) {
                        double var40;
                        var10000 = (var40 = Globals.getPlayer().getVelocity().y - 0.0031) == 0.0 ? 0 : (var40 < 0.0 ? -1 : 1);
                        if (var18 != 0) {
                           break label200;
                        }

                        if (var10000 >= 0) {
                           break label198;
                        }

                        var10000 = var3;
                        if (var18 != 0) {
                           break label200;
                        }

                        if (var3 == 0) {
                           break label198;
                        }
                     }

                     var30 = MovementUtil.INSTANCE.getFallDistance();
                  }

                  label186: {
                     double var20 = var30;
                     double var41;
                     var10000 = (var41 = 1.13 - var20) == 0.0 ? 0 : (var41 < 0.0 ? -1 : 1);
                     if (var18 == 0) {
                        if (var10000 > 0) {
                           var10000 = 0;
                           break label186;
                        }

                        double var42;
                        var10000 = (var42 = var20 - 1.146) == 0.0 ? 0 : (var42 < 0.0 ? -1 : 1);
                     }

                     if (var18 == 0) {
                        var10000 = var10000 <= 0 ? 1 : 0;
                     }
                  }

                  if (var10000 == 0) {
                     break label205;
                  }
               }

               var10000 = Globals.getMc().options.jumpKey.isPressed();
            }

            if (var18 != 0) {
               break label202;
            }

            if (var10000 != 0) {
               var10000 = 0;
               break label202;
            }
         }

         var10000 = 1;
      }

      int var19 = var10000;
      boolean var32 = Globals.getPlayer().isTouchingWater();
      if (var18 == 0) {
         if (!var32) {
            var32 = Globals.getPlayer().hasStatusEffect(StatusEffects.BLINDNESS);
            label150:
            if (var18 == 0) {
               if (!var32) {
                  var32 = Globals.getPlayer().hasStatusEffect(StatusEffects.LEVITATION);
                  if (var18 != 0) {
                     break label150;
                  }

                  if (!var32) {
                     var32 = Globals.getPlayer().isSubmergedIn(FluidTags.WATER);
                     if (var18 != 0) {
                        break label150;
                     }

                     label158:
                     if (!var32) {
                        var32 = Globals.getPlayer().isInLava();
                        if (var18 == 0) {
                           if (var32) {
                              var32 = Globals.getPlayer().isSubmergedIn(FluidTags.LAVA);
                              if (var18 != 0) {
                                 break label150;
                              }

                              if (var32) {
                                 break label158;
                              }
                           }

                           var32 = PingPredictor.INSTANCE.isInHole(Globals.getPlayer(), var9, (byte)var10, var11);
                        }

                        if (var18 != 0) {
                           break label150;
                        }

                        if (!var32) {
                           var32 = PingPredictor.INSTANCE.isSurrounded(Globals.getPlayer());
                           if (var18 != 0) {
                              break label150;
                           }

                           if (!var32) {
                              var32 = Globals.getPlayer().isHoldingOntoLadder();
                              if (var18 != 0) {
                                 break label150;
                              }

                              if (!var32) {
                                 var32 = Globals.getPlayer().getAbilities().flying;
                                 if (var18 != 0) {
                                    break label150;
                                 }

                                 if (!var32) {
                                    var32 = false;
                                    break label150;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               var32 = true;
            }

            boolean var29 = var32;
            if (var18 != 0) {
               return var29;
            }

            if (!var29) {
               return (boolean)var19;
            }

            return true;
         }

         var32 = Globals.getPlayer().isSwimming();
      }

      label132: {
         label212: {
            if (var18 == 0) {
               if (var32) {
                  break label212;
               }

               ClientWorld var33 = Globals.getWorld();
               Aura var38 = Aura.INSTANCE;
               Entity var22 = Globals.getPlayer();
               var32 = var33.getBlockState(BlockPos.ofFloored(var38.getPos(var22))).getBlock() instanceof FluidBlock;
            }

            if (var18 == 0) {
               if (!var32) {
                  break label212;
               }

               ClientWorld var34 = Globals.getWorld();
               Aura var39 = Aura.INSTANCE;
               Entity var26 = Globals.getPlayer();
               var32 = var34.getBlockState(BlockPos.ofFloored(var39.getPos(var26)).up()).getBlock() instanceof AirBlock;
            }

            if (var18 != 0) {
               break label132;
            }

            if (var32) {
               var32 = true;
               break label132;
            }
         }

         var32 = false;
      }

      boolean var28 = var32;
      var32 = var28;
      label115:
      if (var18 == 0) {
         if (var28) {
            var32 = Globals.getPlayer().getAbilities().flying;
            if (var18 != 0) {
               break label115;
            }

            if (!var32) {
               var32 = false;
               break label115;
            }
         }

         var32 = true;
      }

      boolean var21 = var32;
      var32 = Globals.getPlayer().isSwimming();
      label106:
      if (var18 == 0) {
         if (!var32) {
            var32 = Globals.getMc().options.jumpKey.isPressed();
            if (var18 != 0) {
               break label106;
            }

            if (!var32) {
               return true;
            }
         }

         var32 = var21;
      }

      if (var18 != 0) {
         return var32;
      } else {
         return (boolean)(!var32 ? var19 : 1);
      }
   }

   static boolean d(long var0, DefaultRotationHandler var2, int var3, Object var4) {
      var0 = a ^ var0;
      long var5 = var0 ^ 59253218540274L;
      int var7 = zkmIntConstant();
      int var10000 = var3 & 1;
      if (var7 == 0) {
         byte var8 = 0;
         var10000 = var2.R(var5, var8);
      }

      return (boolean)var10000;
   }

   private final int V(long var1) {
      var1 = a ^ var1;
      int var9 = (int)((var1 ^ 43279934365660L) >>> 56);
      int var14 = ZkmReferences.zkmBootstrap<"Å">(3001924836073953110L, var1);
      int var10000 = MaceElytraTarget.INSTANCE.hasWindBurst((byte)var9);
      if (var14 == 0) {
         if (var10000 == 0) {
            double var15 = Globals.getPlayer().getAttributeValue(EntityAttributes.ATTACK_SPEED);
            int var17 = (int)(1.0 / Globals.getPlayer().getAttributeValue(EntityAttributes.ATTACK_SPEED) * zkmBootstrap<"k">(5849, 768262728574042855L ^ var1));
            var17 = Math.max(var17, zkmBootstrap<"k">(313, 8078094554825430274L ^ var1));
            double var29;
            var10000 = (var29 = var15 - 4.0) == 0.0 ? 0 : (var29 < 0.0 ? -1 : 1);
            if (var14 == 0) {
               var10000 = var10000 == 0 ? 1 : 0;
            }

            label195: {
               label194:
               if (var1 > 0L) {
                  if (var10000 == 0) {
                     label190: {
                        double var30;
                        var10000 = (var30 = 4.4 - var15) == 0.0 ? 0 : (var30 < 0.0 ? -1 : 1);
                        int var10001 = var14;
                        if (var1 > 0L) {
                           if (var14 == 0) {
                              if (var10000 > 0) {
                                 var10000 = 0;
                                 break label190;
                              }

                              double var31;
                              var10000 = (var31 = var15 - 4.401) == 0.0 ? 0 : (var31 < 0.0 ? -1 : 1);
                           }

                           var10001 = var14;
                        }

                        if (var10001 == 0) {
                           var10000 = var10000 <= 0 ? 1 : 0;
                        }
                     }

                     if (var1 <= 0L) {
                        break label194;
                     }

                     if (var10000 == 0) {
                        label180: {
                           double var32;
                           var10000 = (var32 = 4.8 - var15) == 0.0 ? 0 : (var32 < 0.0 ? -1 : 1);
                           int var25 = var14;
                           if (var1 > 0L) {
                              if (var14 == 0) {
                                 if (var10000 > 0) {
                                    var10000 = 0;
                                    break label180;
                                 }

                                 double var33;
                                 var10000 = (var33 = var15 - 4.801) == 0.0 ? 0 : (var33 < 0.0 ? -1 : 1);
                              }

                              var25 = var14;
                           }

                           if (var25 == 0) {
                              var10000 = var10000 <= 0 ? 1 : 0;
                           }
                        }

                        if (var10000 == 0) {
                           break label195;
                        }
                     }
                  }

                  var10000 = zkmBootstrap<"k">(313, 8078094554825430274L ^ var1);
               }

               var17 = var10000;
            }

            int var26;
            label168: {
               var23 = Aura.INSTANCE.Z();
               label167:
               if (var14 == 0) {
                  if (var23 != 0) {
                     var23 = Globals.getMc().options.jumpKey.isPressed();
                     if (var14 != 0) {
                        break label167;
                     }

                     if (var23 == 0) {
                        var23 = Globals.getPlayer().isOnGround();
                        var26 = var14;
                        if (var1 < 0L) {
                           break label168;
                        }

                        if (var14 != 0) {
                           break label167;
                        }

                        if (var23 != 0) {
                           var17 = zkmBootstrap<"k">(31019, 6405919572055026971L ^ var1);
                        }
                     }
                  }

                  var23 = Globals.getPlayer().isOnGround();
               }

               var26 = var14;
            }

            label154: {
               label205: {
                  if (var1 > 0L) {
                     if (var26 == 0) {
                        if (var23 == 0) {
                           break label205;
                        }

                        var23 = Globals.getMc().options.jumpKey.isPressed();
                     }

                     var26 = var14;
                  }

                  if (var26 != 0) {
                     break label154;
                  }

                  if (var23 == 0) {
                     var23 = 1;
                     break label154;
                  }
               }

               var23 = 2;
            }

            byte var18 = var23;
            var10000 = AuraRandomizeDelayMode$WhenMappings.Y[Aura.INSTANCE.BH().ordinal()];
            var26 = var14;
            if (var1 >= 0L) {
               if (var14 == 0) {
                  switch (var10000) {
                     case 1:
                        var10000 = c % zkmBootstrap<"k">(31748, 2546318840405780540L ^ var1);
                        break;
                     case 2:
                        var10000 = c % 5;
                        var26 = var14;
                        if (var1 > 0L) {
                           if (var14 == 0) {
                              if (var10000 == 0) {
                                 var10000 = Globals.getPlayer().isOnGround();
                                 if (var14 == 0) {
                                    label121:
                                    if (var1 > 0L) {
                                       if (var10000 != 0) {
                                          var10000 = Globals.getMc().options.jumpKey.isPressed();
                                          if (var14 != 0) {
                                             return var10000;
                                          }

                                          if (var1 <= 0L) {
                                             break label121;
                                          }

                                          if (var10000 == 0) {
                                             return zkmBootstrap<"k">(10488, 8649573201173527745L ^ var1);
                                          }
                                       }

                                       var10000 = 16632;
                                    }

                                    var10000 = zkmBootstrap<"k">(var10000, 1671373144471524549L ^ var1);
                                 }

                                 return var10000;
                              }

                              var10000 = c % zkmBootstrap<"k">(18576, 2237728272986168490L ^ var1);
                           }

                           var26 = var14;
                        }

                        if (var1 >= 0L) {
                           if (var26 != 0) {
                              return var10000;
                           }

                           var26 = 3;
                        }

                        return var10000 == var26 ? var17 + var18 : var17;
                     default:
                        return var17;
                  }
               }

               var26 = var14;
            }

            if (var1 > 0L) {
               if (var26 != 0) {
                  return var10000;
               }

               var26 = 3;
            }

            return var10000 == var26 ? var17 + var18 : var17;
         }

         var10000 = zkmBootstrap<"k">(2520, 5878585967354093028L ^ var1);
      }

      return var10000;
   }

   static {
      long var14 = a ^ 140679782591647L;
      if (zkmIntConstant() != 0) {
         zkmSetFlag(22);
      }

      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var14 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var14 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      byte[] var13 = var11.doFinal("»Æäß³\u0005°<\u009bÒÜì\u0091ûå&".getBytes("ISO-8859-1"));
      d = zkmDecodeUtf8(var13).intern();
      g = new HashMap(13);
      Cipher var0;
      var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
      var10002 = SecretKeyFactory.getInstance("DES");
      var10003 = new byte[]{(byte)(var14 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var1 = 1; var1 < 8; var1++) {
         var10003[var1] = (byte)(var14 << var1 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      long[] var6 = new long[9];
      int var3 = 0;
      String var4 = "\u009d¿É\u0011¤\u00138õÑ)rXW\u0080mmhü\u00811Sz0]¤\u0084\u0095Ó\u008a¯í-_\u000e¼onâõ|Ï\u0019;\u0087g 3oïÎ\u0099¦Z\u0091<!";
      byte var5 = 56;
      byte var2 = 0;

      label33:
      while (true) {
         int var10001 = var2;
         var2 += 8;
         byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
         long[] var18 = var6;
         var10001 = var3++;
         long var22 = (var7[0] & 255L) << 56
            | (var7[1] & 255L) << 48
            | (var7[2] & 255L) << 40
            | (var7[3] & 255L) << 32
            | (var7[4] & 255L) << 24
            | (var7[5] & 255L) << 16
            | (var7[6] & 255L) << 8
            | var7[7] & 255L;
         byte var25 = -1;

         while (true) {
            long var8 = var22;
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
            long var27 = (var10[0] & 255L) << 56
               | (var10[1] & 255L) << 48
               | (var10[2] & 255L) << 40
               | (var10[3] & 255L) << 32
               | (var10[4] & 255L) << 24
               | (var10[5] & 255L) << 16
               | (var10[6] & 255L) << 8
               | var10[7] & 255L;
            switch (var25) {
               case 0:
                  var18[var10001] = var27;
                  if (var2 >= var5) {
                     e = var6;
                     f = new Integer[9];
                     INSTANCE = new DefaultRotationHandler();
                     return;
                  }
                  break;
               default:
                  var18[var10001] = var27;
                  if (var2 < var5) {
                     continue label33;
                  }

                  var4 = "_{§E\u0083}Ü\tÙ\u0084â\u0097¡¤¿\t";
                  var5 = 16;
                  var2 = 0;
            }

            byte var20 = var2;
            var2 += 8;
            var7 = var4.substring(var20, var2).getBytes("ISO-8859-1");
            var18 = var6;
            var10001 = var3++;
            var22 = (var7[0] & 255L) << 56
               | (var7[1] & 255L) << 48
               | (var7[2] & 255L) << 40
               | (var7[3] & 255L) << 32
               | (var7[4] & 255L) << 24
               | (var7[5] & 255L) << 16
               | (var7[6] & 255L) << 8
               | var7[7] & 255L;
            var25 = 0;
         }
      }
   }

   public static void zkmSetFlag(int var0) {
      U = var0;
   }

   public static int zkmGetFlag() {
      return U;
   }

   public static int zkmIntConstant() {
      return 0;
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

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 15570;
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
            throw new RuntimeException("su/catlean/f2", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         f[var3] = var15;
      }

      return f[var3];
   }

   private static int a(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
      int var4 = (Integer)var3[0];
      long var5 = (Long)var3[1];
      int var7 = zkmDecryptInt(var4, var5);
      MethodHandle var8 = MethodHandles.constant(int.class, var7);
      var1.setTarget(MethodHandles.dropArguments(var8, 0, int.class, long.class));
      return var7;
   }

   private static CallSite zkmBootstrap(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments("a".asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("su/catlean/f2" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
