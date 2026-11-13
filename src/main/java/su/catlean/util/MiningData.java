package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.BlockState;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.player.SpeedMine;
import su.catlean.module.player.SpeedMineDoubleMinePacketOrderMode;
import su.catlean.module.player.SpeedMineMode;
import su.catlean.module.player.SpeedMinePauseEatMode;
import su.catlean.module.player.SpeedMineRebreakMode;
import su.catlean.module.player.SpeedMineStartMode;
import su.catlean.module.player.SpeedMineSwapTimingMode;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.LambdaRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class MiningData {
   @NotNull
   private final BlockPos pos;
   private float x;
   private float u;
   @NotNull
   private final ArrayDeque l;
   private int b;
   @NotNull
   private final Timer timer;
   private static int t;
   private static final long a = ZkmKeys.a(-2729110602017252816L, 1742512876529886977L, MethodHandles.lookup().lookupClass()).a(201082147273540L);
   private static final String[] c;
   private static final String[] d;
   private static final Map e = new HashMap(13);
   private static final long[] g;
   private static final Integer[] h;
   private static final Map i;

   public MiningData(@NotNull BlockPos pos, @Nullable Direction direction) {
      Intrinsics.checkNotNullParameter(posx, "pos");
      super();
      this.pos = posx;
      this.l = new ArrayDeque();
      this.timer = new Timer();
      this.l(direction);
   }

   @NotNull
   public final BlockPos getPos() {
      return this.pos;
   }

   public final float O() {
      return this.x;
   }

   public final float G() {
      return this.u;
   }

   public final int u() {
      return this.b;
   }

   public final void U(int <set-?>) {
      this.b = <set-?>;
   }

   public final void l(@Nullable Direction direction) {
      Direction var10000 = direction;
      if (direction == null) {
         var10000 = Globals.getPlayer().getHorizontalFacing();
         Intrinsics.checkNotNullExpressionValue(var10000, "getDirection(...)");
      }

      Direction var27 = var10000;
      int var28 = Globals.getPlayer().getInventory().getSelectedSlot();
      BlockPos var31 = this.pos;
      SpeedMine var33 = SpeedMine.INSTANCE;
      int var29 = SpeedMine.access$getToolSlot(var33, var31);
      if (SpeedMine.access$getSwapTiming(SpeedMine.INSTANCE) == SpeedMineSwapTimingMode.ALWAYS) {
         if (var29 == -1) {
            return;
         }

         SpeedMine.access$switchToTool(SpeedMine.INSTANCE, var29, -1);
      }

      if (SpeedMine.INSTANCE.getDoubleMine()) {
         if (SpeedMine.access$getDoubleMinePacketOrder(SpeedMine.INSTANCE) == SpeedMineDoubleMinePacketOrderMode.NEW) {
            PacketUtil.sendSequencedPacket(MiningData::h);
            PacketUtil.sendPacket(new PlayerActionC2SPacket(Action.ABORT_DESTROY_BLOCK, this.pos.add(0, 500, 0), Direction.DOWN));
         } else {
            PacketUtil.sendSequencedPacket(MiningData::m);
            PacketUtil.sendSequencedPacket(MiningData::a);
         }

         PacketUtil.sendSequencedPacket(MiningData::M);
      } else {
         PacketUtil.sendSequencedPacket(MiningData::N);
         if (SpeedMine.access$getStartMode(SpeedMine.INSTANCE) != SpeedMineStartMode.NONE) {
            if (SpeedMine.access$getStartMode(SpeedMine.INSTANCE) == SpeedMineStartMode.ABORT) {
               PacketUtil.sendPacket(new PlayerActionC2SPacket(Action.ABORT_DESTROY_BLOCK, this.pos, Direction.DOWN));
            } else {
               PacketUtil.sendSequencedPacket(MiningData::s);
            }
         }
      }

      if (SpeedMine.access$getSwapTiming(SpeedMine.INSTANCE) == SpeedMineSwapTimingMode.ALWAYS) {
         SpeedMine.INSTANCE.getAtomicBoolean().set(true);
         if (SpeedMine.access$getSwapDelay(SpeedMine.INSTANCE)) {
            Scheduler.schedule$default(Scheduler.INSTANCE, 1, (byte)0, false, MiningData::c, 6, null);
         } else {
            SpeedMine.access$switchToTool(SpeedMine.INSTANCE, var28, var29);
         }
      }
   }

   // $VF: Could not properly define all variable types!
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public final boolean x(long a, boolean async) {
      int var56;
      Direction var10000;
      label354: {
         ax = a ^ ax;
         int var20 = (int)((ax ^ 123808590381602L) >>> 32);
         int var21 = (int)((ax ^ 123808590381602L) << 32 >>> 32);
         var56 = zkmIntConstant();
         var10000 = CollectionsKt.firstOrNull(PlaceManager.INSTANCE.getPlaceSides(var20, this.pos, var21));
         Direction var10001 = var10000;
         if (var56 != 0) {
            if (var10000 != null) {
               break label354;
            }

            var10000 = Globals.getPlayer().getHorizontalFacing();
            var10001 = var10000;
         }

         Intrinsics.checkNotNullExpressionValue(var10001, "getDirection(...)");
      }

      Direction var57 = var10000;
      Iterable var59 = SpeedMine.INSTANCE.getMiningQueue();
      Iterator var61 = var59.iterator();

      label347:
      while (true) {
         if (var61.hasNext()) {
            Object var62 = var61.next();
            var10000 = (Direction)var62;

            while (true) {
               MiningData var63 = (MiningData)var10000;
               boolean var87 = Globals.getWorld().isAir(var63.pos);
               if (var56 != 0) {
                  var87 = !var87;
               }

               if (!var87) {
                  continue label347;
               }

               var10000 = (Direction)var62;
               if (var56 != 0) {
                  break label347;
               }
            }
         }

         var10000 = null;
         break;
      }

      MiningData var58;
      int var60;
      var58 = (MiningData)var10000;
      var75 = this.v();
      BlockPos var68 = this.pos;
      SpeedMine var70 = SpeedMine.INSTANCE;
      var60 = SpeedMine.access$getToolSlot(var70, var68);
      var88 = this.b;
      label336:
      if (var56 != 0) {
         if (this.b > 0) {
            var88 = SpeedMine.INSTANCE.getDoubleMine();
            if (var56 == 0) {
               break label336;
            }

            if (var88 != 0) {
               var88 = Intrinsics.areEqual(this.pos, SpeedMine.getCurrentPos());
               if (var56 == 0) {
                  break label336;
               }

               if (var88 == 0) {
                  this.T();
                  return true;
               }
            }
         }

         BlockPos var73 = this.pos;
         var88 = SpeedMine.access$getBreakTime(SpeedMine.INSTANCE, var73);
      }

      if (var56 != 0) {
         if (var88 == 0) {
            this.T();
            return true;
         }

         var88 = Globals.getWorld().isAir(this.pos);
      }

      if (var56 != 0) {
         if (var88 != 0) {
            this.x = 0.0F;
            this.u = -1.0F;
            this.l.add(Globals.getPlayer().isOnGround());
            int var101 = Intrinsics.areEqual(this.pos, SpeedMine.getCurrentPos());
            if (var56 != 0) {
               if (var101 != 0) {
                  return (boolean)0;
               }

               var101 = SpeedMine.INSTANCE.getMiningQueue().size();
            }

            if (var56 == 0) {
               return (boolean)var101;
            }

            if (var101 > 1) {
               return (boolean)1;
            }

            return (boolean)0;
         }

         float var111;
         var88 = (var111 = this.x - 0.0F) == 0.0F ? 0 : (var111 < 0.0F ? -1 : 1);
      }

      if (var56 != 0) {
         var88 = var88 == 0 ? 1 : 0;
      }

      label360: {
         byte var102;
         label399: {
            label362: {
               label363: {
                  label385: {
                     if (var88 != 0) {
                        float var112;
                        int var89 = (var112 = this.u - -1.0F) == 0.0F ? 0 : (var112 < 0.0F ? -1 : 1);
                        if (var56 != 0) {
                           var89 = !var89;
                        }

                        if (var89 && SpeedMine.INSTANCE.getMode() == SpeedMineMode.PACKET) {
                           var90 = this.timer.hasPassed2(500);
                           if (var56 == 0) {
                              break label385;
                           }

                           label307:
                           if (var90 != 0) {
                              int var91 = this.b;
                              if (var56 != 0) {
                                 if (this.b > 0 && SpeedMine.INSTANCE.getRebreak() == SpeedMineRebreakMode.GRIM) {
                                    var90 = Intrinsics.areEqual(this.pos, SpeedMine.getCurrentPos());
                                    if (var56 == 0) {
                                       break label385;
                                    }

                                    if (var90 != 0) {
                                       break label307;
                                    }

                                    var90 = SpeedMine.INSTANCE.getMiningQueue().size();
                                    var102 = 1;
                                    if (var56 == 0) {
                                       break label399;
                                    }

                                    if (var90 <= 1) {
                                       break label307;
                                    }
                                 }

                                 var92 = this;
                                 if (var56 == 0) {
                                    break label363;
                                 }

                                 var91 = this.l.isEmpty();
                              }

                              if (var91 == 0 && SpeedMine.INSTANCE.getRebreak() == SpeedMineRebreakMode.NCP_AIR) {
                                 break label362;
                              }

                              var92 = this;
                              break label363;
                           }
                        }
                     }

                     var90 = var60;
                  }

                  if (var56 == 0) {
                     break label360;
                  }

                  var102 = -1;
                  break label399;
               }

               var92.l(var57);
               Globals.getPlayer().swingHand(Hand.MAIN_HAND);
               if (var56 != 0) {
                  return false;
               }

               Module.zkmSetFlag(new Module[1]);
            }

            boolean var93 = this.l.isEmpty();
            if (var56 != 0) {
               var93 = !var93;
            }

            if (var93) {
               for (Object var64 : this.l) {
                  Boolean var65 = (Boolean)var64;
                  this.u = this.x;
                  float var103 = this.x;
                  SpeedMine var10002 = SpeedMine.INSTANCE;
                  BlockState var10003 = Globals.getWorld().getBlockState(this.pos);
                  Intrinsics.checkNotNullExpressionValue(var10003, "getBlockState(...)");
                  BlockPos var10004 = this.pos;
                  Intrinsics.checkNotNull(var65);
                  this.x = var103 + SpeedMine.access$getBreakDelta(var10002, var10003, var10004, var60, var65);
                  if (var56 == 0 || var56 == 0) {
                     return false;
                  }
               }
            }

            return false;
         }

         if (var90 == var102) {
            return false;
         }

         var90 = Globals.getPlayer().isUsingItem();
      }

      label369: {
         if (var56 != 0) {
            if (var90 != 0 && SpeedMine.access$getPauseEat(SpeedMine.INSTANCE) == SpeedMinePauseEatMode.GRIM) {
               this.u = this.x;
               float var106 = this.x;
               SpeedMine var108 = SpeedMine.INSTANCE;
               BlockState var110 = Globals.getWorld().getBlockState(this.pos);
               Intrinsics.checkNotNullExpressionValue(var110, "getBlockState(...)");
               this.x = var106 + SpeedMine.access$getBreakDelta(var108, var110, this.pos, var60, Globals.getPlayer().isOnGround());
               return false;
            }

            var94 = this;
            if (var56 == 0) {
               break label369;
            }

            float var113;
            var90 = (var113 = this.x - 1.0F) == 0.0F ? 0 : (var113 < 0.0F ? -1 : 1);
         }

         if (var90 < 0 && !var75) {
            this.u = this.x;
            float var104 = this.x;
            SpeedMine var107 = SpeedMine.INSTANCE;
            BlockState var109 = Globals.getWorld().getBlockState(this.pos);
            Intrinsics.checkNotNullExpressionValue(var109, "getBlockState(...)");
            this.x = var104 + SpeedMine.access$getBreakDelta(var107, var109, this.pos, var60, Globals.getPlayer().isOnGround());
            return false;
         }

         var94 = var58;
      }

      label262: {
         if (var56 != 0) {
            if (var94 == null) {
               break label262;
            }

            var94 = var58;
         }

         if (Intrinsics.areEqual(var94, this)) {
            label371: {
               var77 = SpeedMine.INSTANCE.getMiningQueue();
               var95 = var77 instanceof Collection;
               label254:
               if (var56 != 0) {
                  if (var95 != 0) {
                     var95 = var77.isEmpty();
                     if (var56 == 0) {
                        break label254;
                     }

                     if (var95 != 0) {
                        var96 = 0;
                        break label371;
                     }
                  }

                  var95 = 0;
               }

               int var81 = var95;

               for (Object var85 : var77) {
                  MiningData var66 = (MiningData)var85;
                  var96 = Globals.getWorld().isAir(var66.pos);
                  if (var56 == 0) {
                     break label371;
                  }

                  if (var56 != 0) {
                     var96 = var96 == 0 ? 1 : 0;
                  }

                  if (var96 != 0) {
                     var81++;
                     if (var56 != 0) {
                        if (var81 >= 0) {
                           continue;
                        }

                        CollectionsKt.throwCountOverflow();
                     }

                     if (var56 == 0) {
                        break;
                     }
                  }
               }

               var96 = var81;
            }

            if (var96 == 2) {
               return false;
            }
         }
      }

      BlockState var97 = Globals.getWorld().getBlockState(this.pos);
      Intrinsics.checkNotNullExpressionValue(var97, "getBlockState(...)");
      BlockState var78 = var97;
      boolean var98 = var78.isAir();
      if (var56 == 0) {
         return var98;
      } else {
         if (!var98) {
            boolean var114 = var78.isLiquid();
            if (var56 == 0) {
               return var114;
            }

            if (!var114) {
               boolean var115 = SpeedMine.getRecentBreaks().containsKey(this.pos);
               if (var56 != 0) {
                  if (var115) {
                     boolean var116 = SpeedMine.access$getAwait(SpeedMine.INSTANCE);
                     if (var56 == 0) {
                        return var116;
                     }

                     if (var116) {
                        return false;
                     }
                  }

                  var115 = SpeedMine.access$getRotate(SpeedMine.INSTANCE);
               }

               if (var56 != 0) {
                  if (var115) {
                     Function0 var79 = MiningData::C;
                     Object var105;
                     Intrinsics.checkNotNullExpressionValue(var105, "add(...)");
                     <unknown> var99;
                     Rotation var82 = var99.to((Vec3d)var105);

                     this.pos.toCenterPos().add(0.0, 0.5, 0.0);
                     RotationRequest var84 = switch (MiningData$WhenMappings.h[SpeedMine.INSTANCE.getTiming().ordinal()]) {
                        case 1 -> (RotationRequest)(new ActionRotationRequest(var82, 150, var79));
                        case 2 -> new RotationRequest(var82, 150, var79);
                        case 3 -> (RotationRequest)(new LambdaRotationRequest(var82, 150, var79));
                        default -> throw new NoWhenBranchMatchedException();
                     };
                     RotationManager.INSTANCE.submit(var84);
                  }

                  var115 = async;
               }

               label374: {
                  if (var56 != 0) {
                     if (var115) {
                        break label374;
                     }

                     var115 = SpeedMine.access$getRotate(SpeedMine.INSTANCE);
                  }

                  if (var56 == 0) {
                     return var115;
                  }

                  if (var115) {
                     return false;
                  }
               }

               this.S(var60, Globals.getPlayer().getInventory().getSelectedSlot(), var57);
            }
         }

         return false;
      }
   }

   public static boolean Y(short var0, int var1, MiningData var2, boolean var3, int var4, Object var5, short var6) {
      long var7 = ((long)var0 << 48 | (long)var1 << 32 >>> 16 | (long)var6 << 48 >>> 48) ^ a;
      long var9 = var7 ^ 139348867122429L;
      int var11 = zkmIntConstant();
      int var10000 = var4 & 1;
      if (var11 != 0) {
         var3 = false;
         var10000 = var2.x(var9, var3);
      }

      return (boolean)var10000;
   }

   public final boolean v() {
      int var11 = zkmIntConstant();
      if (this.b > 0) {
         SpeedMine var10000 = SpeedMine.INSTANCE;
         if (var11 != 0) {
            if (SpeedMine.INSTANCE.getRebreak() != SpeedMineRebreakMode.GRIM) {
               return (boolean)0;
            }

            var10000 = SpeedMine.INSTANCE;
         }

         var12 = SpeedMine.access$isAutoCrystalTarget(var10000, this.pos);
         label50:
         if (var11 != 0) {
            if (var12 != 0) {
               var12 = SpeedMine.access$getAwaitAutoCrystal(SpeedMine.INSTANCE);
               if (var11 == 0) {
                  break label50;
               }

               if (var12 != 0) {
                  var12 = PendingBlocks.INSTANCE.isPending(this.pos);
                  if (var11 == 0) {
                     break label50;
                  }

                  if (var12 == 0) {
                     return (boolean)0;
                  }
               }
            }

            var12 = Intrinsics.areEqual(this.pos, SpeedMine.getCurrentPos());
         }

         if (var11 == 0) {
            return (boolean)var12;
         }

         if (var12 != 0) {
            return (boolean)1;
         }

         int var13 = SpeedMine.INSTANCE.getMiningQueue().size();
         if (var11 == 0) {
            return (boolean)var13;
         }

         if (var13 <= 1) {
            return (boolean)1;
         }
      }

      return (boolean)0;
   }

   private final void S(int var1, int var2, Direction var3) {
      SpeedMine.access$switchToTool(SpeedMine.INSTANCE, var1, -1);
      int var10000 = zkmIntConstant();
      SpeedMine.INSTANCE.getAtomicBoolean().set(true);
      int var19 = var10000;
      PacketUtil.sendSequencedPacket(MiningData::T);
      SpeedMineRebreakMode var22 = SpeedMine.INSTANCE.getRebreak();
      if (var19 != 0) {
         if (var22 == SpeedMineRebreakMode.NCP_AIR) {
            PacketUtil.sendPacket(new PlayerActionC2SPacket(Action.ABORT_DESTROY_BLOCK, this.pos, Direction.DOWN));
            PacketUtil.sendSequencedPacket(MiningData::Q);
            PacketUtil.sendSequencedPacket(MiningData::o);
         }

         AutoCrystal.INSTANCE.getMinedBlocks().put(this.pos, Globals.now());
      }

      label50: {
         label54: {
            this.l.clear();
            PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
            var10000 = SpeedMine.INSTANCE.getDoubleMine();
            if (var19 != 0) {
               if (var10000 != 0) {
                  var10000 = this.b;
                  if (var19 == 0) {
                     break label50;
                  }

                  if (this.b < 1) {
                     break label54;
                  }
               }

               var10000 = SpeedMine.access$getSwapDelay(SpeedMine.INSTANCE);
            }

            if (var19 == 0) {
               break label50;
            }

            if (var10000 == 0) {
               var10000 = 0;
               break label50;
            }
         }

         var10000 = 1;
      }

      label36: {
         label35: {
            int var20 = var10000;
            if (var19 != 0) {
               if (var20 == 0) {
                  break label35;
               }

               Scheduler.schedule$default(Scheduler.INSTANCE, var20, (byte)0, false, MiningData::N, 6, null);
            }

            if (var19 != 0) {
               break label36;
            }
         }

         SpeedMine.access$switchToTool(SpeedMine.INSTANCE, var2, var1);
      }

      SpeedMine.getTimer().reset();
      SpeedMine.a(this.pos);
      int var21 = this.b++;
      SpeedMine.getRecentBreaks().put(this.pos, Globals.now());
      this.u = 0.0F;
      this.x = this.u;
   }

   public final void a(long a, @NotNull MiningPauseReason reason) {
      int var18;
      Direction var21;
      label39: {
         ax = a ^ ax;
         int var4 = (int)((ax ^ 43421741896988L) >>> 32);
         int var5 = (int)((ax ^ 43421741896988L) << 32 >>> 32);
         int var10000 = zkmIntConstant();
         Intrinsics.checkNotNullParameter(reason, "reason");
         var18 = var10000;
         this.l.clear();
         var21 = CollectionsKt.firstOrNull(PlaceManager.INSTANCE.getPlaceSides(var4, this.pos, var5));
         Direction var10001 = var21;
         if (var18 != 0) {
            if (var21 != null) {
               break label39;
            }

            var21 = Globals.getPlayer().getHorizontalFacing();
            var10001 = var21;
         }

         Intrinsics.checkNotNullExpressionValue(var10001, "getDirection(...)");
      }

      Direction var19;
      label33: {
         var19 = var21;
         if (reason == MiningPauseReason.SWITCH && SpeedMine.INSTANCE.getRebreak() == SpeedMineRebreakMode.NCP_AIR) {
            var22 = Globals.getWorld().isAir(this.pos);
            if (var18 == 0) {
               break label33;
            }

            if (var22) {
               this.x = 0.0F;
               this.u = this.x;
               PacketUtil.sendSequencedPacket(MiningData::v);
               return;
            }
         }

         float var23;
         var22 = (var23 = this.x - 0.0F) == 0.0F ? 0 : (var23 < 0.0F ? -1 : 1);
      }

      if (var18 != 0) {
         var22 = !var22;
      }

      if (!var22) {
         this.x = 0.0F;
         this.u = this.x;
         PacketUtil.sendPacket(new PlayerActionC2SPacket(Action.ABORT_DESTROY_BLOCK, this.pos, Direction.DOWN));
         this.l(var19);
      }
   }

   public final void T() {
      int var10000 = zkmIntConstant();
      this.l.clear();
      int var5 = var10000;
      float var7;
      var10000 = (var7 = this.x - 0.0F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
      if (var5 != 0) {
         var10000 = !var10000;
      }

      if (!var10000) {
         PacketUtil.sendPacket(new PlayerActionC2SPacket(Action.ABORT_DESTROY_BLOCK, this.pos, Direction.DOWN));
      }
   }

   private static final Packet h(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.START_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final Packet m(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final Packet a(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.START_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final Packet M(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final Packet N(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.START_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final Packet s(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final void c(int var0, int var1) {
      SpeedMine.access$switchToTool(SpeedMine.INSTANCE, var0, var1);
      SpeedMine.INSTANCE.getAtomicBoolean().set(false);
   }

   private static final Unit C(boolean var0, MiningData var1, int var2, Direction var3) {
      return Unit.INSTANCE;
   }

   private static final Packet T(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final Packet Q(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.STOP_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final Packet o(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.START_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   private static final void N(int var0, int var1) {
      SpeedMine.access$switchToTool(SpeedMine.INSTANCE, var0, var1);
   }

   private static final Packet v(MiningData var0, Direction var1, int var2) {
      return new PlayerActionC2SPacket(Action.START_DESTROY_BLOCK, var0.pos, var1, var2);
   }

   public static void zkmSetFlag(int var0) {
      t = var0;
   }

   public static int zkmGetFlag() {
      return t;
   }

   public static int zkmIntConstant() {
      return 114;
   }

   static {
      long var20 = a ^ 79451546707292L;
      zkmSetFlag(0);
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
      String var15 = "\u0091\u0005ÝBCÆ+v\r+ü\u001bTl\u0015!üÎ½aX\u008dN\\£êLN\u009aÈ\u008e³Ñc\u000eÈÅì\u0017\u00ad(c»ü\u0083KØGþ\f(\u0097ÑF}\u0089\u0092\u0019±jOK£òb®+å«åÒK}a>ªé_¶µ²\u0010\u00ad>\u0014Ðö\\\u0001É\"óÉµ-\n\u000f©(M-t\u0080ý6\u009d®a@´îÖà\u009awtÀ@\u0017C\u001eæäXÊg\u0005\u001bTt³Ä¼\u0000º\u0019A®\u0018 ÑPya<ë\u0087á×Ü-ªNà\\|Õ=\u009b\\7\u0003Ji\u0013\u0005\u0000Z\tj\u0093Õ";
      short var17 = 172;
      char var14 = '(';
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
                     c = var18;
                     d = new String[7];
                     i = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[6];
                     int var3 = 0;
                     String var4 = "ÎÌådê«\u0018ã$ÚÄ\u009b\u001f,Ì\u0019\u0003gÁT\u0010\u0012\u0005¦5tj³\u000eÝÄf";
                     byte var5 = 32;
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
                                    g = var6;
                                    h = new Integer[6];
                                    return;
                                 }
                                 break;
                              default:
                                 var28[var10001] = var45;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "\u009cû3½\u0087{\u00977!öi\u0017¸*\u000f¯";
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

                  var15 = "óÜ«ýF\u008a\t¼\u0000hÙ,\u001e(¼k(ú\u0084\u009c~\u009b\u008e>0m1¡\u001aÃ\u009aD\u00932Û-³Ù\u0086\u008a\u009cG\u0082VºÌ$ä«ÎBã<ùni½";
                  var17 = 57;
                  var14 = 16;
                  var24 = -1;
            }

            var25 = var15.substring(++var24, var24 + var14);
            var10001 = 0;
         }
      }
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 32485;
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
            throw new RuntimeException("su/catlean/sy", var10);
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
         throw new RuntimeException("su/catlean/sy" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 6770;
      if (h[var3] == null) {
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
         long var5 = g[var3];
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
            throw new RuntimeException("su/catlean/sy", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         h[var3] = var15;
      }

      return h[var3];
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
         throw new RuntimeException("su/catlean/sy" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
