package su.catlean.module.basic;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.AirBlock;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket.Full;
import net.minecraft.network.packet.s2c.common.CommonPingS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ClientboundExplodePacketAccessor;
import su.catlean.mixins.accessors.ClientboundSetEntityMotionPacketAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class AntiKnockBack extends Module {
   @NotNull
   public static final AntiKnockBack INSTANCE;
   static final KProperty[] delegatedProperties;
   @NotNull
   private static final ModeSetting mode$delegate;
   @NotNull
   private static final BooleanSetting oldBreak$delegate;
   @NotNull
   private static final BooleanSetting pauseInWater$delegate;
   @NotNull
   private static final BooleanSetting explosions$delegate;
   @NotNull
   private static final BooleanSetting cc$delegate;
   @NotNull
   private static final BooleanSetting onlyWalls$delegate;
   @NotNull
   private static final BooleanSetting onlyGround$delegate;
   @NotNull
   private static final BooleanSetting notMoving$delegate;
   @NotNull
   private static final BooleanSetting ignoreRotations;
   @NotNull
   private static final FloatSetting vertical$delegate;
   @NotNull
   private static final FloatSetting horizontal$delegate;
   private static boolean k;
   private static boolean a;
   private static int X;
   private static boolean O;
   private static final long b = ZkmKeys.a(-6031132881689212375L, -1660236390689321593L, MethodHandles.lookup().lookupClass()).a(265243028793865L);
   private static final String[] c;
   private static final String[] f;
   private static final Map g = new HashMap(13);
   private static final long[] i;
   private static final Integer[] l;
   private static final Map m;
   private static final long[] n;
   private static final Long[] o;
   private static final Map w;

   private AntiKnockBack() {
      super("anti-knock-back", Categories.getBasic(), null, null);
   }

   private final AntiKnockBackMode getMode() {
      return (AntiKnockBackMode)mode$delegate.getValue(this, delegatedProperties[0]);
   }

   private final boolean getOldBreak() {
      return (Boolean)oldBreak$delegate.getValue(this, delegatedProperties[1]);
   }

   private final void setOldBreak(boolean var1) {
      oldBreak$delegate.setValue(this, delegatedProperties[1], var1);
   }

   private final boolean getPauseInWater() {
      return (Boolean)pauseInWater$delegate.getValue(this, delegatedProperties[2]);
   }

   private final void setPauseInWater(boolean var1) {
      pauseInWater$delegate.setValue(this, delegatedProperties[2], var1);
   }

   private final boolean getExplosions() {
      return (Boolean)explosions$delegate.getValue(this, delegatedProperties[3]);
   }

   private final void setExplosions(boolean var1) {
      explosions$delegate.setValue(this, delegatedProperties[3], var1);
   }

   private final boolean getCc() {
      return (Boolean)cc$delegate.getValue(this, delegatedProperties[4]);
   }

   private final void setCc(boolean var1) {
      cc$delegate.setValue(this, delegatedProperties[4], var1);
   }

   private final boolean getOnlyWalls() {
      return (Boolean)onlyWalls$delegate.getValue(this, delegatedProperties[5]);
   }

   private final void setOnlyWalls(boolean var1) {
      onlyWalls$delegate.setValue(this, delegatedProperties[5], var1);
   }

   private final boolean getOnlyGround() {
      return (Boolean)onlyGround$delegate.getValue(this, delegatedProperties[6]);
   }

   private final void setOnlyGround(boolean var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      onlyGround$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"f">(3681, 8365636241386651695L ^ var4)], var1);
   }

   private final boolean getNotMoving() {
      return (Boolean)notMoving$delegate.getValue(this, delegatedProperties[7]);
   }

   private final void setNotMoving(boolean var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      notMoving$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"f">(4415, 3497074608483746815L ^ var4)], var1);
   }

   private final boolean r() {
      long var3 = 0L;
      var3 = b ^ var3;
      return (Boolean)ignoreRotations.getValue(this, delegatedProperties[zkmBootstrap2<"f">(18093, 4353916691222662546L ^ var3)]);
   }

   private final void l(boolean var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      ignoreRotations.setValue(this, delegatedProperties[zkmBootstrap2<"f">(18093, 4353928069387859432L ^ var4)], var1);
   }

   private final float getVertical() {
      return ((Number)vertical$delegate.getValue(this, delegatedProperties[9])).floatValue();
   }

   private final void setVertical(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      vertical$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"f">(29405, 1894008967086945530L ^ var4)], var1);
   }

   private final float getHorizontal() {
      return ((Number)horizontal$delegate.getValue(this, delegatedProperties[10])).floatValue();
   }

   private final void setHorizontal(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      horizontal$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"f">(23166, 2161834884119845942L ^ var4)], var1);
   }

   @Flow
   private final void onReceivePacket(ReceivePacket var1) {
      boolean var18;
      Packet var19;
      int var23;
      int var10000 = AntiKnockBackMode.w();
      var19 = var1.getPacket();
      var18 = (boolean)var10000;
      var23 = var19 instanceof EntityVelocityUpdateS2CPacket;
      label189:
      if (var18) {
         if (var23 != 0) {
            var23 = ((EntityVelocityUpdateS2CPacket)var19).getEntityId();
            if (!var18) {
               break label189;
            }

            if (var23 == Globals.getPlayer().getId()) {
               var10000 = this.g();
               if (var18) {
                  if (var10000 == 0) {
                     return;
                  }

                  var10000 = AntiKnockBack$WhenMappings.D[this.getMode().ordinal()];
               }

               switch (var10000) {
                  case 1:
                     ((ClientboundSetEntityMotionPacketAccessor)var19)
                        .setMovement(
                           new Vec3d(
                              ((EntityVelocityUpdateS2CPacket)var19).getVelocity().x * this.getHorizontal() / 100.0,
                              ((EntityVelocityUpdateS2CPacket)var19).getVelocity().y * this.getVertical() / 100.0,
                              ((EntityVelocityUpdateS2CPacket)var19).getVelocity().z * this.getHorizontal() / 100.0
                           )
                        );
                     if (var18) {
                        break;
                     }
                  case 2:
                     var1.cancel();
                     if (var18) {
                        break;
                     }
                  case 3:
                     var1.cancel();
                     X = 6;
                     if (var18) {
                        break;
                     }
                  case 4:
                     long var33;
                     var23 = (var33 = MovementUtil.INSTANCE.getTimeSinceSetback() - 200L) == 0L ? 0 : (var33 < 0L ? -1 : 1);
                     if (!var18) {
                        break label189;
                     }

                     if (var23 > 0) {
                        var1.cancel();
                        k = true;
                        if (!var18) {
                           throw new NoWhenBranchMatchedException();
                        }
                     }
                     break;
                  case 5:
                     var1.cancel();
                     k = true;
                     break;
                  default:
                     throw new NoWhenBranchMatchedException();
               }
            }
         }

         var23 = var19 instanceof ExplosionS2CPacket;
      }

      label178: {
         label177: {
            if (var18) {
               if (var23 == 0) {
                  break label177;
               }

               var25 = this;
               if (!var18) {
                  break label178;
               }

               var23 = this.getExplosions();
            }

            if (var23 != 0 && !WindHop.INSTANCE.getEnabled()) {
               Packet var26 = var1.getPacket();
               Intrinsics.checkNotNull(var26, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientboundExplodePacketAccessor");
               ClientboundExplodePacketAccessor var20 = (ClientboundExplodePacketAccessor)var26;
               var10000 = this.g();
               if (var18) {
                  if (!var10000) {
                     return;
                  }

                  var10000 = ((ExplosionS2CPacket)var19).comp_2884().isPresent();
               }

               if (var18) {
                  if (!var10000) {
                     return;
                  }

                  double var34;
                  var10000 = (var34 = ((ExplosionS2CPacket)var19).comp_2884().get().x - 0.0) == 0.0 ? 0 : (var34 < 0.0 ? -1 : 1);
               }

               if (var18) {
                  var10000 = !var10000;
               }

               if (var10000) {
                  double var35;
                  var10000 = (var35 = ((ExplosionS2CPacket)var19).comp_2884().get().y - 0.0) == 0.0 ? 0 : (var35 < 0.0 ? -1 : 1);
                  if (var18) {
                     var10000 = !var10000;
                  }

                  if (var10000) {
                     double var36;
                     var10000 = (var36 = ((ExplosionS2CPacket)var19).comp_2884().get().z - 0.0) == 0.0 ? 0 : (var36 < 0.0 ? -1 : 1);
                     if (var18) {
                        var10000 = !var10000;
                     }

                     if (var10000) {
                        return;
                     }
                  }
               }

               switch (AntiKnockBack$WhenMappings.D[this.getMode().ordinal()]) {
                  case 2:
                     var20.setPlayerKnockback(Optional.of(Vec3d.ZERO));
                     if (var18) {
                        break;
                     }
                  case 1:
                     Object var30 = ((ExplosionS2CPacket)var19).comp_2884();
                     if (var18) {
                        if (!var30.isPresent()) {
                           break;
                        }

                        var30 = ((ExplosionS2CPacket)var19).comp_2884().get();
                        Intrinsics.checkNotNullExpressionValue(var30, "get(...)");
                     }

                     Vec3d var21 = (Vec3d)var30;
                     var20.setPlayerKnockback(
                        Optional.of(
                           new Vec3d(var21.x * this.getHorizontal() / 100.0, var21.z * this.getVertical() / 100.0, var21.y * this.getHorizontal() / 100.0)
                        )
                     );
                     if (var18) {
                        break;
                     }
                  case 4:
                     long var37;
                     var10000 = (var37 = MovementUtil.INSTANCE.getTimeSinceSetback() - 200L) == 0L ? 0 : (var37 < 0L ? -1 : 1);
                     if (var18) {
                        if (var10000 <= 0) {
                           break;
                        }

                        var20.setPlayerKnockback(Optional.of(Vec3d.ZERO));
                        var10000 = 1;
                     }

                     k = (boolean)var10000;
                     if (var18) {
                        break;
                     }
                  case 5:
                     var20.setPlayerKnockback(Optional.of(Vec3d.ZERO));
                     k = true;
                     if (var18) {
                        break;
                     }
                  case 3:
                     if (!var18) {
                        throw new NoWhenBranchMatchedException();
                     }
                     break;
                  default:
                     throw new NoWhenBranchMatchedException();
               }
            }
         }

         var25 = this;
      }

      label132: {
         if (var25.getMode() == AntiKnockBackMode.OldGrim) {
            var10000 = var19 instanceof CommonPingS2CPacket;
            if (!var18) {
               break label132;
            }

            if (var10000 != 0) {
               var10000 = X;
               if (!var18) {
                  break label132;
               }

               if (X > 0) {
                  var1.cancel();
                  int var22 = X;
                  X = var22 + -1;
               }
            }
         }

         var10000 = var19 instanceof PlayerPositionLookS2CPacket;
      }

      if (var18) {
         if (var10000 == 0) {
            return;
         }

         var10000 = 0;
      }

      a = (boolean)var10000;
   }

   private final boolean g() {
      Entity var20 = Globals.getPlayer();
      BlockPos var10000 = BlockPos.ofFloored(this.getPos(var20));
      Intrinsics.checkNotNullExpressionValue(var10000, "containing(...)");
      BlockPos var19 = var10000;
      if (Globals.getWorld().getBlockState(var19).getBlock() instanceof AirBlock || this.getOnlyGround() && !Globals.getPlayer().isOnGround()) {
         if (this.getNotMoving() && !MovementUtil.INSTANCE.isMoving()) {
            a = true;
            return true;
         } else {
            return (Globals.getPlayer().isTouchingWater() || Globals.getPlayer().isSubmergedInWater() || Globals.getPlayer().isInLava())
                  && this.getPauseInWater()
               ? false
               : !this.getNotMoving() && !this.getOnlyWalls();
         }
      } else {
         return true;
      }
   }

   @Flow(
      priority = -10
   )
   private final void onPlayerUpdate(PlayerUpdateEvent var1) {
      boolean var27;
      int var10000;
      label100: {
         label103: {
            var27 = AntiKnockBackMode.w();
            var10000 = Globals.getPlayer().isTouchingWater();
            if (var27) {
               if (var10000 == 0) {
                  var10000 = Globals.getPlayer().isSubmergedInWater();
                  if (!var27) {
                     break label100;
                  }

                  if (var10000 == 0) {
                     break label103;
                  }
               }

               var10000 = this.getPauseInWater();
            }

            if (!var27) {
               break label100;
            }

            if (var10000 != 0) {
               return;
            }
         }

         var10000 = MovementUtil.INSTANCE.isMoving();
      }

      if (var27) {
         if (var10000 != 0) {
            a = false;
         }

         var10000 = AntiKnockBack$WhenMappings.D[this.getMode().ordinal()];
      }

      label86:
      if (var27) {
         if (var10000 == 4 || var10000 == 5) {
            var10000 = k ? 1 : 0;
            if (!var27) {
               break label86;
            }

            if (k) {
               label80: {
                  label79: {
                     label78: {
                        boolean var32 = Globals.getPlayer().isCrawling();
                        if (var27) {
                           if (var32) {
                              break label78;
                           }

                           var33 = this;
                           if (!var27) {
                              break label79;
                           }

                           var32 = this.getOldBreak();
                        }

                        if (!var32) {
                           var34 = Globals.getPlayer().getBlockPos().up();
                           break label80;
                        }
                     }

                     var33 = this;
                  }

                  Entity var31 = Globals.getPlayer();
                  var34 = BlockPos.ofFloored(var33.getPos(var31));
               }

               BlockPos var30 = var34;
               Intrinsics.checkNotNull(var30);
               boolean var35 = Globals.getWorld().isAir(var30);
               if (var27) {
                  if (var35) {
                     PacketUtil.sendPacket(
                        new Full(
                           Globals.getPlayer().getX(),
                           Globals.getPlayer().getY(),
                           Globals.getPlayer().getZ(),
                           MovementUtil.INSTANCE.getLastYaw(),
                           MovementUtil.INSTANCE.getLastPitch(),
                           Globals.getPlayer().isOnGround(),
                           Globals.getPlayer().horizontalCollision
                        )
                     );
                     PacketUtil.sendSequencedPacket(AntiKnockBack::onPlayerUpdate$lambda$0);
                  }

                  var35 = false;
               }

               k = var35;
            }
         }

         var10000 = X;
      }

      if (var27) {
         if (var10000 <= 0) {
            return;
         }

         var10000 = X;
      }

      int var28 = var10000;
      X = var28 + -1;
   }

   @Flow
   private final void onSendPacket(SendPacket var1) {
      if (this.getNotMoving() && a && var1.getPacket() instanceof PlayerMoveC2SPacket) {
         var1.cancel();
      }
   }

   public final void L() {
      boolean var5 = AntiKnockBackMode.w();
      boolean var10000 = this.r();
      if (var5) {
         if (!var10000) {
            return;
         }

         var10000 = false;
      }

      a = var10000;
   }

   @Override
   public void onEnable(long var1) {
      X = 0;
   }

   private static final boolean settingVisible() {
      return INSTANCE.getMode() == AntiKnockBackMode.GrimNew || INSTANCE.getMode() == AntiKnockBackMode.GrimBypass;
   }

   private static final boolean settingVisible2() {
      return INSTANCE.getOnlyWalls();
   }

   private static final boolean settingVisible3() {
      return INSTANCE.getNotMoving();
   }

   private static final boolean settingVisible4() {
      return INSTANCE.getMode() == AntiKnockBackMode.Custom;
   }

   private static final boolean settingVisible5() {
      return INSTANCE.getMode() == AntiKnockBackMode.Custom;
   }

   private static final Packet onPlayerUpdate$lambda$0(int var0) {
      PlayerActionC2SPacket var10000 = new PlayerActionC2SPacket;
      Action var10002 = Action.STOP_DESTROY_BLOCK;
      BlockPos var11;
      if (!Globals.getPlayer().isCrawling() && !INSTANCE.getOldBreak()) {
         var11 = Globals.getPlayer().getBlockPos().up();
      } else {
         AntiKnockBack var10003 = INSTANCE;
         Entity var10 = Globals.getPlayer();
         var11 = BlockPos.ofFloored(var10003.getPos(var10));
      }

      var10000./* $VF: Unable to resugar constructor */<init>(var10002, var11, Direction.DOWN, var0);
      return var10000;
   }

   static {
      long var31 = b ^ 2105606807897L;
      zkmSetFlag(true);
      Cipher var22;
      Cipher var10000 = var22 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var23 = 1; var23 < 8; var23++) {
         var10003[var23] = (byte)(var31 << var23 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var29 = new String[37];
      int var27 = 0;
      String var26 = "Ð\u0012Ú¡ÊßEÅ¤\u0000\u0099\u009e\u0018<è®\u008bQ]±\u0082\u001eÕÆ\f\u0090z\t\u0097yIüÓ\u0016Èù\u0005\u001bH` û\u0080,ç6\u0005\u0011 äkèÌ\u0002\u00ad\u0098Õ³0½\u0088\u000b/Åúª\u0084x¥\u0019$\u0083, \u008a\u001e@ \u0083æ-\u008e\u008dQ\u0080\u0001aÜ:<\u009f\b\u0007oâþa¢\u000b.IbP\u008f\u009cK\u0018àÃGßúÆÇU¿J;\u009a\u001a\u0083-Å\u0093þ¶\u0002Íï´µ\u0018Ì×º:©OÊ½\u0085Ãò%\u0083\u009fÒC®¥ÑÜ°\u0082\u0010\u0000 ö\u0015ïða9\u000b¹!NëÛ\u0007yÜº\u0004Ü\u009e\u0000ôPr\u008e(0K\u0092ïû»k\u0018ô(Åõ\u0004CÛ\u001e/$ì\u009dýSß¦y¢\u0097¶[¬ã\u009c\u0018,I\u0099MW]=s\u0080\u0089l\tmÀ\u0007Ú¡½f\u0080:§M¬\u0018ÙÖ\u00106\u00943¶Çá]D-Ì\u0086Â\u0087_\u0003\u001d×©(¶®(\u008cú®\u0012\u0014{+<]\u0095\u009axf©Y!C9hÏ\u0087\nT\u0094àîjüÐ\u001f\u0014G\u000e¹\u009e \u0018\u0017½Ú\u0018cóÄ*L\u0084\u001bL'0\u0006\u008d³µÔ\u0000\u008b3ÙöÕç\u0089x0\u009b\u0098\u0091í\u0084ô\u0090,bs\tçÚÇ\u001e\fp(ÔP\u001d\bãÞëvË]$PtâÎ1\u0085\u0013?ý\u001c[UÐÛ/\u0090Rkz \u0015!¥Û%:ÉO©«%þ\u0098¢ÐMòAB#«\u00946Æª\u0000ËÑh\u0081øQ\u0018|5¶{\u0092\u0005\n·\u00191É_'Uä¢\u00801:{\u0011Ü\u0088` µ\nÖjJ\u008bR×\ng-\u001e¦!Ó\u008c@¯5ù!\u00ad\u0097\u0087Æ\u008e¾}Jj-È ç\u0011\u001f\b1\u0018E\u0001Y\u0089¥Si¼\u0080ä9À÷\u0010AÃ2\u0010+\"$f[(\u0081é ãÚßìíG6\u0003å¬\u0002ÒÂÝ\u0000\u0090\u008aîgÙ\u0094WfI\u0018°R|É¾4s(ØV-#\u0010\u0016¶#¢á\u009c÷,\u0098\u001b\n´JS¥l5K\bÓû¼1ºe\u0089U°¶[ÿÿ\u0093A²(\u0081½à\u0098_=T\u009cG\u0014\u009bÃ\"àNtd)\u0001\u0005x\u001fÝ\u009fÎ§_¸¨þÿ\u0019ì\u0098×g[Õâ1(e,>)V{\u0099§A+ ¢$Áø\u0007wÜ5¢¯É\u0083yníÓ\u009czfí\\\u0015£\u008f\u008dê\nÝY ]Á\u001e§]Ñ\u00883¨\f&\u0082Ü\u001e]ê\u0097TY\u0016[/13RVN{yý²\u0014\u0010#Ì \u0000Üa6bHcv\u0088Ü5\u0013½ Pæ\u0095\u009b`¢q¦ÿ\u0084ø\u009dÎæ Ø%\u001b?çÒU¨©\bIê\u001a²\u008clf \u008b»\u007flr\nì§\u0014¡\u0093Yøì2~¿\u0088\u0011^E&6éî \u001eåY¥\u0014§ j]ÿJª!\u0002\u009a\u0089ÑÐðÜ\u0014GNÕá\u0080\u0006Å#{û!Þÿ\u001c\u0004È\u00862(ÛµÌ\u0019pk1@¢lK{tý[Ù\u009eÃÒ\u000eÑîÛmêc\u0006\tÀ~âÑ\bãÆi\u001cÒ0þ\u0010Jwb]~þØð`5vQ\u0019\u0013Öi ¢s\u0098ùÌÈï®\u0094´,¡ \rE`\u0092\u0010Ï_;µ³n¦\u009eÅ4)B¼@ \u008fCàNÆx\u000b\u0003fdÓúß¶Æ¨1®)\u008c\u0087\u0004\u0006\u0090\u0003>FSº^ªGâÇ³\u00ad(>\u0006Àjû\u0001\u001eOì\u0093ÞÚØ=ú@\u0090ÃM\u0017)uö!d`é\u0015Ñ\u001c¡ä\u0081\u007f\u0080\u0087ÿ)KÈiïäu\u0087LíÒ\r\u0003ª£V¨ÂòE~\u0091(cÐê¯\t!È\u0092¬eoÉ]D\u009b¿[Ïfnjÿ\u0016ÃlQÊÿÅ\u009d\n\u0002\u0080S\u008aÊN1Ï[\u001bF¦ó\\\u0080\u00adÊ\u008e\u001a¬dÀó-phã²£¸!\u001c\u0018\u0093¾d(ùå§uO¦ü4WQ¢}\u0012õ'ï\u0017nÄ\u0089 Ó!}áÍºE\u0096z\u0005ù\u000ftä´T\u0099±+uJ³@çóu\u0080Í\fãÌ\u008c ^C\u00849Â)\u0000\u007f÷ÌäÌ|æiÃ#ç|B&w\u009báó^G±\u009fÏ_\u0004(\u0007½mpA)\u007f\u0092@üB%T³á¿Ö ýàÜ$}W\u009e\u0092Îç\u0095Pê(\u0014\u0011âÃ\u000f0uä\u0010eqi\u0004µ\u0090Wp_ëH\u0017Úó\f\u0088 Án>!jäM¾#×¬Y\u001aa÷Ü\u0019þ¾ã=\u001a\u007f\u0012°MÕ2°S-)";
      short var28 = 1242;
      char var25 = '(';
      int var44 = -1;

      label72:
      while (true) {
         String var45 = var26.substring(++var44, var44 + var25);
         int var10001 = -1;

         while (true) {
            byte[] var30 = var22.doFinal(var45.getBytes("ISO-8859-1"));
            String var59 = zkmDecodeUtf8(var30).intern();
            switch (var10001) {
               case 0:
                  var29[var27++] = var59;
                  if ((var44 += var25) >= var28) {
                     c = var29;
                     f = new String[37];
                     m = new HashMap(13);
                     Cipher var11;
                     var10000 = var11 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var12 = 1; var12 < 8; var12++) {
                        var10003[var12] = (byte)(var31 << var12 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var17 = new long[13];
                     int var14 = 0;
                     String var15 = "åë\u0091ço¼\u00130X2»àíÖéèhü*Æ\fÒ\u001eÊòÊB¢æ¡ËÔ\u001dnÆ\u0014X\u000fèÒ\u0086e]\u001b;Fð\u0081ü3\re\u0085¼`\u0093µ¦<\u0096~tÛÎ\u0083H¹»¶\u0013#%Ú\u0091z@M\u0090\u008a\u0098\u009dM\u008cówL\u0015ê";
                     byte var16 = 88;
                     byte var13 = 0;

                     label54:
                     while (true) {
                        var10001 = var13;
                        var13 += 8;
                        byte[] var18 = var15.substring(var10001, var13).getBytes("ISO-8859-1");
                        long[] var48 = var17;
                        var10001 = var14++;
                        long var63 = (var18[0] & 255L) << 56
                           | (var18[1] & 255L) << 48
                           | (var18[2] & 255L) << 40
                           | (var18[3] & 255L) << 32
                           | (var18[4] & 255L) << 24
                           | (var18[5] & 255L) << 16
                           | (var18[6] & 255L) << 8
                           | var18[7] & 255L;
                        byte var67 = -1;

                        while (true) {
                           long var19 = var63;
                           byte[] var21 = var11.doFinal(
                              new byte[]{
                                 (byte)(var19 >>> 56),
                                 (byte)(var19 >>> 48),
                                 (byte)(var19 >>> 40),
                                 (byte)(var19 >>> 32),
                                 (byte)(var19 >>> 24),
                                 (byte)(var19 >>> 16),
                                 (byte)(var19 >>> 8),
                                 (byte)var19
                              }
                           );
                           long var70 = (var21[0] & 255L) << 56
                              | (var21[1] & 255L) << 48
                              | (var21[2] & 255L) << 40
                              | (var21[3] & 255L) << 32
                              | (var21[4] & 255L) << 24
                              | (var21[5] & 255L) << 16
                              | (var21[6] & 255L) << 8
                              | var21[7] & 255L;
                           switch (var67) {
                              case 0:
                                 var48[var10001] = var70;
                                 if (var13 >= var16) {
                                    i = var17;
                                    l = new Integer[13];
                                    w = new HashMap(13);
                                    Cipher var0;
                                    var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                                    var10002 = SecretKeyFactory.getInstance("DES");
                                    var10003 = new byte[]{(byte)(var31 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                                    for (int var1 = 1; var1 < 8; var1++) {
                                       var10003[var1] = (byte)(var31 << var1 * 8 >>> 56);
                                    }

                                    var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                    long[] var6 = new long[2];
                                    int var3 = 0;
                                    String var4 = "'ûÐÜXs\u0080&Pô·u&¨W$";
                                    byte var5 = 16;
                                    byte var2 = 0;

                                    do {
                                       int var56 = var2;
                                       var2 += 8;
                                       byte[] var7 = var4.substring(var56, var2).getBytes("ISO-8859-1");
                                       var56 = var3++;
                                       long var8 = (var7[0] & 255L) << 56
                                          | (var7[1] & 255L) << 48
                                          | (var7[2] & 255L) << 40
                                          | (var7[3] & 255L) << 32
                                          | (var7[4] & 255L) << 24
                                          | (var7[5] & 255L) << 16
                                          | (var7[6] & 255L) << 8
                                          | var7[7] & 255L;
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
                                       var6[var56] = (var10[0] & 255L) << 56
                                          | (var10[1] & 255L) << 48
                                          | (var10[2] & 255L) << 40
                                          | (var10[3] & 255L) << 32
                                          | (var10[4] & 255L) << 24
                                          | (var10[5] & 255L) << 16
                                          | (var10[6] & 255L) << 8
                                          | var10[7] & 255L;
                                    } while (var2 < var5);

                                    n = var6;
                                    o = new Long[2];
                                    KProperty[] var41 = new KProperty[]{
                                       Reflection.property1(new PropertyReference1Impl(AntiKnockBack.class, "mode", "getMode()Lsu/catlean/zn;", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "oldBreak", "getOldBreak()Z", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(AntiKnockBack.class, "pauseInWater", "getPauseInWater()Z", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "explosions", "getExplosions()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "cc", "getCc()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "onlyWalls", "getOnlyWalls()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "onlyGround", "getOnlyGround()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "notMoving", "getNotMoving()Z", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(AntiKnockBack.class, "ignoreRotations", "getIgnoreRotations()Z", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "vertical", "getVertical()F", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AntiKnockBack.class, "horizontal", "getHorizontal()F", 0))
                                    };
                                    delegatedProperties = var41;
                                    INSTANCE = new AntiKnockBack();
                                    mode$delegate = SettingFactory.modeSetting$default(INSTANCE, "mode", AntiKnockBackMode.GrimNew, null, null, 12, null);
                                    oldBreak$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "old-break", false, null, AntiKnockBack::settingVisible, 4, null
                                    );
                                    pauseInWater$delegate = SettingFactory.booleanSetting$default(INSTANCE, "pause-in-liquids", false, null, null, 12, null);
                                    explosions$delegate = SettingFactory.booleanSetting$default(INSTANCE, "explosions", true, null, null, 12, null);
                                    cc$delegate = SettingFactory.booleanSetting$default(INSTANCE, "pause-on-flag", false, null, null, 12, null);
                                    onlyWalls$delegate = SettingFactory.booleanSetting$default(INSTANCE, "only-walls", false, null, null, 12, null);
                                    onlyGround$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "only-ground", false, null, AntiKnockBack::settingVisible2, 4, null
                                    );
                                    notMoving$delegate = SettingFactory.booleanSetting$default(INSTANCE, "not-moving", false, null, null, 12, null);
                                    ignoreRotations = SettingFactory.booleanSetting$default(
                                       INSTANCE, "ignore-rotations", false, null, AntiKnockBack::settingVisible3, 4, null
                                    );
                                    vertical$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "vertical", 0.0F, RangesKt.rangeTo(0.0F, 100.0F), null, AntiKnockBack::settingVisible4, 8, null
                                    );
                                    horizontal$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "horizontal", 0.0F, RangesKt.rangeTo(0.0F, 100.0F), null, AntiKnockBack::settingVisible5, 8, null
                                    );
                                    return;
                                 }
                                 break;
                              default:
                                 var48[var10001] = var70;
                                 if (var13 < var16) {
                                    continue label54;
                                 }

                                 var15 = "*Uò\u0016 q\u0016w³ê¢vj?¾\u008c";
                                 var16 = 16;
                                 var13 = 0;
                           }

                           byte var55 = var13;
                           var13 += 8;
                           var18 = var15.substring(var55, var13).getBytes("ISO-8859-1");
                           var48 = var17;
                           var10001 = var14++;
                           var63 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           var67 = 0;
                        }
                     }
                  }

                  var25 = var26.charAt(var44);
                  break;
               default:
                  var29[var27++] = var59;
                  if ((var44 += var25) < var28) {
                     var25 = var26.charAt(var44);
                     continue label72;
                  }

                  var26 = "¿`7¼\u009493ÏxMd&\u0088Û\u001d4'\u0088ê\u0099c\u008döì\u009f\u0091>\u009a=j®ú ûH\u0099}\u0086,*x\u008fvEÕ¦:Ôø³\u0092ÉÕâµúy`+«bó0g{";
                  var28 = 65;
                  var25 = ' ';
                  var44 = -1;
            }

            var45 = var26.substring(++var44, var44 + var25);
            var10001 = 0;
         }
      }
   }

   public static void zkmSetFlag(boolean var0) {
      O = var0;
   }

   public static boolean zkmGetFlag() {
      return O;
   }

   public static boolean i() {
      return false;
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 1203;
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
            throw new RuntimeException("su/catlean/_x", var10);
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
         throw new RuntimeException("su/catlean/_x" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 12396;
      if (l[var3] == null) {
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
         long var5 = i[var3];
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
         Object[] var9 = (Object[])m.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               m.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/_x", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         l[var3] = var15;
      }

      return l[var3];
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
         throw new RuntimeException("su/catlean/_x" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static long zkmDecryptLong(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 15988;
      if (o[var3] == null) {
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
         long var5 = n[var3];
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
         Object[] var9 = (Object[])w.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               w.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/_x", var14);
         }

         long var15 = (var10[0] & 255L) << 56
            | (var10[1] & 255L) << 48
            | (var10[2] & 255L) << 40
            | (var10[3] & 255L) << 32
            | (var10[4] & 255L) << 24
            | (var10[5] & 255L) << 16
            | (var10[6] & 255L) << 8
            | var10[7] & 255L;
         o[var3] = var15;
      }

      return o[var3];
   }

   private static long e(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
      int var4 = (Integer)var3[0];
      long var5 = (Long)var3[1];
      long var7 = zkmDecryptLong(var4, var5);
      MethodHandle var9 = MethodHandles.constant(long.class, var7);
      var1.setTarget(MethodHandles.dropArguments(var9, 0, int.class, long.class));
      return var7;
   }

   private static CallSite zkmBootstrap3(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments("e".asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("su/catlean/_x" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
