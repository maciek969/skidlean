package su.catlean.module.basic;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.common.CommonPongC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.network.packet.s2c.common.CommonPingS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.EventPlayerTravel;
import su.catlean.api.event.events.player.EventPostPlayerTravel;
import su.catlean.api.event.events.player.FallFlyingEvent;
import su.catlean.api.event.events.player.JumpEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.player.ShouldStopSprintingEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.KeyMappingAccessor;
import su.catlean.mixins.accessors.LivingEntityAccessor;
import su.catlean.mixins.accessors.LocalPlayerAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class ElytraRecast extends Module {
   @NotNull
   public static final ElytraRecast INSTANCE;
   static final KProperty[] delegatedProperties;
   @NotNull
   private static final BooleanSetting grim$delegate;
   @NotNull
   private static final BooleanSetting tunnelsOffset$delegate;
   @NotNull
   private static final ModeSetting bypass$delegate;
   @NotNull
   private static final BooleanSetting grimDurability$delegate;
   @NotNull
   private static final BooleanSetting changePitch$delegate;
   @NotNull
   private static final FloatSetting pitchValue$delegate;
   @NotNull
   private static final BooleanSetting allowBroken$delegate;
   private static float savedPitch;
   private static float a;
   private static float savedYaw;
   private static float recastPitch;
   private static float recastYaw;
   private static boolean V;
   private static boolean L;
   private static boolean f;
   private static boolean K;
   private static int b;
   @NotNull
   private static final Queue l;
   @NotNull
   private static final Queue delayedPings;
   @NotNull
   private static final LinkedList delayedPongs;
   @NotNull
   private static final LinkedList N;
   private static boolean B;
   private static double lastY;
   private static final long e = ZkmKeys.a(-470473023837798775L, 2556780326439791241L, MethodHandles.lookup().lookupClass()).a(166764164087599L);
   private static final String[] h;
   private static final String[] i;
   private static final Map n = new HashMap(13);
   private static final long[] o;
   private static final Integer[] t;
   private static final Map u;

   private ElytraRecast() {
      super("elytra-recast", Categories.getBasic(), null, null);
   }

   private final boolean getGrim() {
      return (Boolean)grim$delegate.getValue(this, delegatedProperties[0]);
   }

   private final void setGrim(boolean var1) {
      grim$delegate.setValue(this, delegatedProperties[0], var1);
   }

   private final boolean getTunnelsOffset() {
      return (Boolean)tunnelsOffset$delegate.getValue(this, delegatedProperties[1]);
   }

   private final void setTunnelsOffset(boolean var1) {
      tunnelsOffset$delegate.setValue(this, delegatedProperties[1], var1);
   }

   private final ElytraRecastBypassMode getBypass() {
      return (ElytraRecastBypassMode)bypass$delegate.getValue(this, delegatedProperties[2]);
   }

   private final void setBypass(ElytraRecastBypassMode var1) {
      bypass$delegate.setValue(this, delegatedProperties[2], var1);
   }

   private final boolean getGrimDurability() {
      return (Boolean)grimDurability$delegate.getValue(this, delegatedProperties[3]);
   }

   private final void setGrimDurability(boolean var1) {
      grimDurability$delegate.setValue(this, delegatedProperties[3], var1);
   }

   private final boolean getChangePitch() {
      return (Boolean)changePitch$delegate.getValue(this, delegatedProperties[4]);
   }

   private final void setChangePitch(boolean var1) {
      changePitch$delegate.setValue(this, delegatedProperties[4], var1);
   }

   private final float getPitchValue() {
      return ((Number)pitchValue$delegate.getValue(this, delegatedProperties[5])).floatValue();
   }

   private final void setPitchValue(float var1) {
      pitchValue$delegate.setValue(this, delegatedProperties[5], var1);
   }

   private final boolean getAllowBroken() {
      return (Boolean)allowBroken$delegate.getValue(this, delegatedProperties[6]);
   }

   private final void setAllowBroken(boolean var1) {
      long var4 = 0L;
      var4 = e ^ var4;
      allowBroken$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"g">(21493, 3832395207604329L ^ var4)], var1);
   }

   @Override
   public void onEnable(long var1) {
      int var10000 = ModeSetting.Y();
      K = false;
      int var7 = var10000;
      b = 0;
      if (var7 == 0) {
         if (Globals.getMc().player != null) {
            recastYaw = Globals.getPlayer().getYaw();
         }

         delayedPongs.clear();
         N.clear();
      }
   }

   @Flow(
      priority = -10
   )
   private final void onPreElytra(PreElytraEvent var1) {
      a = Globals.getPlayer().getYaw();
      if ((this.getChangePitch() || this.getGrim()) && Globals.getPlayer().isGliding()) {
         savedPitch = Globals.getPlayer().getPitch();
         Globals.getPlayer().setPitch(this.getPitchValue());
         if (this.getGrim()) {
            Globals.getPlayer().setYaw(recastYaw);
         }
      }
   }

   @Flow(
      priority = -10
   )
   private final void onAfterElytra(AfterElytraEvent var1) {
      if ((this.getChangePitch() || this.getGrim()) && Globals.getPlayer().isGliding()) {
         Globals.getPlayer().setPitch(savedPitch);
         Globals.getPlayer().setYaw(a);
      }
   }

   @Flow
   public final void onPlayerTravel(@NotNull EventPlayerTravel e) {
      Intrinsics.checkNotNullParameter(ex, "e");
      if (this.getGrim() && this.isInGame()) {
         savedYaw = Globals.getPlayer().getYaw();
         recastPitch = Globals.getPlayer().getPitch();
         Globals.getPlayer().setPitch(this.getPitchValue());
         Globals.getPlayer().setYaw(recastYaw);
      }
   }

   @Flow
   public final void onPostPlayerTravel(@NotNull EventPostPlayerTravel e) {
      Intrinsics.checkNotNullParameter(ex, "e");
      if (this.getGrim() && this.isInGame()) {
         Globals.getPlayer().setYaw(savedYaw);
         Globals.getPlayer().setPitch(recastPitch);
      }
   }

   @Flow(
      priority = -10
   )
   private final void onJump(JumpEvent var1) {
      int var6 = ModeSetting.Y();
      if (var6 == 0) {
         if (!this.getGrim()) {
            return;
         }

         var1.setYaw(recastYaw);
      }

      var1.cancel();
   }

   @Flow
   private final void onFallFlying(FallFlyingEvent var1) {
      long var2 = e ^ 106649191671103L;
      int var6 = (int)((var2 ^ 112503142666330L) >>> 32);
      long var7 = (var2 ^ 112503142666330L) << 32 >>> 32;
      if (this.getGrim()) {
         if (V) {
            var1.setValue(true);
            var1.cancel();
         }
      } else {
         boolean var10 = var1.getValue();
         if (V && !var10) {
            var1.setValue(this.shouldRecast(var6, var7));
            var1.cancel();
         }

         V = var10;
      }
   }

   @Flow
   private final void onShouldStopSprinting(ShouldStopSprintingEvent var1) {
      if (this.canRecast()) {
         var1.cancel();
      }
   }

   @Flow
   private final void onSetPose(SetPoseEvent var1) {
      int var10 = ModeSetting.Y();
      boolean var10000 = this.canRecast();
      if (var10 == 0) {
         if (!var10000) {
            return;
         }

         var10000 = this.getGrimDurability();
      }

      if (var10 == 0) {
         if (!var10000) {
            return;
         }

         var10000 = this.getGrim();
      }

      if (var10000) {
         var1.cancel();
         var1.setPose(EntityPose.STANDING);
      }
   }

   @Flow
   private final void onPlayerUpdate(PlayerUpdateEvent var1) {
      long var2 = e ^ 87673057571046L;
      int var19 = (int)((var2 ^ 15091195501431L) << 32 >>> 48);
      int var31 = (int)((var2 ^ 80341247608195L) >>> 32);
      long var32 = (var2 ^ 80341247608195L) << 32 >>> 32;
      if (this.getChangePitch() || this.getGrim()) {
         RotationManager.INSTANCE
            .rotate(new Rotation(this.getGrim() ? recastYaw : Globals.getPlayer().getYaw(), this.getPitchValue(), false, null, 12, null), 201);
      }

      if (b > 0) {
         int var36 = b;
         b = var36 + -1;
         Globals.getMc().options.jumpKey.setPressed(false);
         Globals.getMc().options.forwardKey.setPressed(false);
         Globals.getMc().options.sprintKey.setPressed(false);
         this.flushQueuedPackets();
         this.flushPingsOnThread();
         this.flushPings(var19);
         this.flushPongs();
         recastYaw = Globals.getPlayer().getYaw();
         V = false;
         L = false;
         if (Globals.getPlayer().isGliding()) {
            Globals.getPlayer().stopGliding();
         }
      } else if (!this.canRecast()) {
         this.flushQueuedPackets();
         this.flushPingsOnThread();
         V = false;
         f = false;
      } else {
         Globals.getPlayer().setSprinting(true);
         Globals.getMc().options.jumpKey.setPressed(true);
         Globals.getMc().options.forwardKey.setPressed(true);
         Globals.getMc().options.sprintKey.setPressed(true);
         float var35 = MovementUtil.INSTANCE.getFallDistance() > 1.0 ? 0.0F : (float)MovementUtil.INSTANCE.getFallDistance();
         if (Globals.getPlayer().isOnGround()) {
            f = false;
         }

         if (var35 > 0.0F) {
            if (!f && this.useGrimDurability()) {
               if (delayedPings.size() > 20) {
                  this.flushPingsOnThread();
                  Globals.getPlayer().stopGliding();
                  V = false;
               }

               this.flushQueuedPackets();
               f = true;
            }

            if (!Globals.getPlayer().isGliding() && !V && this.shouldRecast(var31, var32)) {
               V = true;
            }
         }

         ClientPlayerEntity var10000 = Globals.getPlayer();
         Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type su.catlean.mixins.accessors.LivingEntityAccessor");
         ((LivingEntityAccessor)var10000).setLastJumpCooldown(0);
      }
   }

   @Flow
   private final void onReceivePacket(ReceivePacket var1) {
      if (var1.getPacket() instanceof PlayerPositionLookS2CPacket) {
         V = false;
         L = false;
         b = 60;
         this.flushQueuedPackets();
         this.flushPingsOnThread();
         Globals.getPlayer().stopGliding();
      }

      if (var1.getPacket() instanceof EntityTrackerUpdateS2CPacket && Globals.getPlayer().isGliding() && V && this.getGrimDurability()) {
         var1.cancel();
      }

      if (var1.getPacket() instanceof CommonPingS2CPacket && Globals.getPlayer().isGliding() && V && this.useGrimDurability()) {
         var1.cancel();
         Queue var10000 = delayedPings;
         Packet var10001 = var1.getPacket();
         Intrinsics.checkNotNull(var10001, "null cannot be cast to non-null type net.minecraft.network.protocol.common.ClientboundPingPacket");
         var10000.add((CommonPingS2CPacket)var10001);
      }
   }

   @Flow
   private final void onSendPacket(SendPacket var1) {
      int var7;
      int var18;
      boolean var23;
      label271: {
         long var2 = e ^ 74644043903406L;
         var7 = (int)((var2 ^ 2087950981695L) << 32 >>> 48);
         var18 = ModeSetting.Y();
         ElytraRecast var10000 = this;
         if (var18 == 0) {
            if (this.getBypass() == ElytraRecastBypassMode.FULL) {
               var23 = Globals.getPlayer().isGliding();
               if (var18 != 0) {
                  break label271;
               }

               if (var23) {
                  var23 = V;
                  if (var18 != 0) {
                     break label271;
                  }

                  if (V) {
                     var23 = K;
                     if (var18 != 0) {
                        break label271;
                     }

                     if (!K) {
                        var23 = this.useGrimDurability();
                        if (var18 != 0) {
                           break label271;
                        }

                        if (var23) {
                           var1.cancel();
                           l.add(var1.getPacket());
                        }
                     }
                  }
               }
            }

            var10000 = this;
         }

         var23 = var10000.useGrimDurability();
      }

      if (var18 == 0) {
         if (var23) {
            return;
         }

         var23 = K;
      }

      if (!var23) {
         double var19;
         label247: {
            label275: {
               ClientPlayerEntity var24 = Globals.getPlayer();
               Intrinsics.checkNotNull(var24, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
               var19 = ((LocalPlayerAccessor)var24).getLastBaseY();
               var23 = B;
               if (var18 == 0) {
                  if (B) {
                     break label275;
                  }

                  double var32;
                  var23 = (var32 = this.fraction(var19) - 0.4) == 0.0 ? 0 : (var32 < 0.0 ? -1 : 1);
               }

               if (var18 != 0) {
                  break label247;
               }

               if (var23 >= 0) {
                  var23 = 1;
                  break label247;
               }
            }

            var23 = 0;
         }

         int var21;
         label276: {
            var21 = var23;
            var23 = var1.getPacket() instanceof CommonPongC2SPacket;
            label235:
            if (var18 == 0) {
               label233:
               if (var23 != 0) {
                  label287: {
                     var23 = V;
                     if (var18 == 0) {
                        if (!V) {
                           break label287;
                        }

                        var23 = Globals.getPlayer().isGliding();
                     }

                     if (var18 == 0) {
                        if (var23 == 0) {
                           break label287;
                        }

                        var23 = Globals.getWorld().isSpaceEmpty(Globals.getPlayer().getBoundingBox().offset(0.0, -1.0, 0.0));
                     }

                     label200:
                     if (var18 == 0) {
                        label218:
                        if (var23 == 0) {
                           label216: {
                              label280: {
                                 var23 = B;
                                 if (var18 == 0) {
                                    if (B) {
                                       double var33;
                                       var23 = (var33 = this.fraction(var19) - 0.8) == 0.0 ? 0 : (var33 < 0.0 ? -1 : 1);
                                       if (var18 != 0) {
                                          break label216;
                                       }

                                       if (var23 < 0) {
                                          break label280;
                                       }
                                    }

                                    var23 = B;
                                 }

                                 if (var18 != 0) {
                                    break label200;
                                 }

                                 if (var23 != 0) {
                                    break label218;
                                 }

                                 double var34;
                                 var23 = (var34 = this.fraction(var19) - 0.4) == 0.0 ? 0 : (var34 < 0.0 ? -1 : 1);
                                 if (var18 != 0) {
                                    break label200;
                                 }

                                 if (var23 >= 0) {
                                    break label218;
                                 }
                              }

                              LinkedList var28 = delayedPongs;
                              Packet var10001 = var1.getPacket();
                              Intrinsics.checkNotNull(
                                 var10001, "null cannot be cast to non-null type net.minecraft.network.protocol.common.ServerboundPongPacket"
                              );
                              var28.add((CommonPongC2SPacket)var10001);
                           }

                           var1.cancel();
                        }

                        var23 = var21;
                     }

                     if (var18 != 0) {
                        break label235;
                     }

                     if (var23 == 0) {
                        break label233;
                     }

                     this.flushPongs();
                     if (var18 == 0) {
                        break label233;
                     }
                  }

                  this.flushPongs();
               }

               var29 = var1.getPacket();
               if (var18 != 0) {
                  break label276;
               }

               var23 = var29 instanceof PlayerMoveC2SPacket;
            }

            if (var23 == 0) {
               return;
            }

            var29 = var1.getPacket();
            Intrinsics.checkNotNull(var29, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ServerboundMovePlayerPacket");
         }

         label288: {
            PlayerMoveC2SPacket var22 = (PlayerMoveC2SPacket)var29;
            var23 = V;
            if (var18 == 0) {
               if (!V) {
                  break label288;
               }

               var23 = Globals.getPlayer().isGliding();
            }

            if (var18 == 0) {
               if (var23 == 0) {
                  break label288;
               }

               double var35;
               var23 = (var35 = var22.getY(lastY) - lastY) == 0.0 ? 0 : (var35 < 0.0 ? -1 : 1);
            }

            label171: {
               label282: {
                  if (var18 == 0) {
                     if (var23 < 0) {
                        B = true;
                        if (var18 == 0) {
                           break label282;
                        }
                     }

                     double var36;
                     var23 = (var36 = var22.getY(lastY) - lastY) == 0.0 ? 0 : (var36 < 0.0 ? -1 : 1);
                  }

                  if (var18 != 0) {
                     break label171;
                  }

                  if (var23 > 0) {
                     B = false;
                  }
               }

               lastY = var22.getY(lastY);
               var23 = Globals.getWorld().isSpaceEmpty(Globals.getPlayer().getBoundingBox().offset(0.0, -1.0, 0.0));
            }

            label140:
            if (var18 == 0) {
               label158:
               if (var23 == 0) {
                  label156: {
                     label284: {
                        var23 = B;
                        if (var18 == 0) {
                           if (B) {
                              double var37;
                              var23 = (var37 = this.fraction(var22.getY(var19)) - 0.8) == 0.0 ? 0 : (var37 < 0.0 ? -1 : 1);
                              if (var18 != 0) {
                                 break label156;
                              }

                              if (var23 < 0) {
                                 break label284;
                              }
                           }

                           var23 = B;
                        }

                        if (var18 != 0) {
                           break label140;
                        }

                        if (var23 != 0) {
                           break label158;
                        }

                        double var38;
                        var23 = (var38 = this.fraction(var19) - 0.4) == 0.0 ? 0 : (var38 < 0.0 ? -1 : 1);
                        if (var18 != 0) {
                           break label140;
                        }

                        if (var23 >= 0) {
                           break label158;
                        }
                     }

                     N.add(var22);
                  }

                  var1.cancel();
               }

               var23 = var21;
            }

            if (var23 == 0) {
               return;
            }

            this.flushPings(var7);
            if (var18 == 0) {
               return;
            }
         }

         this.flushPings(var7);
      }
   }

   private final double fraction(double var1) {
      return var1 - Math.floor(var1);
   }

   @Override
   public void onDisable(long var1) {
      int var8 = (int)((var1 ^ 29281619393040L) << 32 >>> 48);
      int var16 = (int)((var1 ^ 44030506242343L) << 48 >>> 48);
      KeyBinding var10000 = Globals.getMc().options.forwardKey;
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
      int var24 = ((KeyMappingAccessor)var10000).getKey().getCode();
      char var10002 = (char)var16;
      int var18 = var24;
      char var19 = var10002;
      if (!KeyboardUtil.isKeyPressed(var19, var18)) {
         Globals.getMc().options.forwardKey.setPressed(false);
      }

      var10000 = Globals.getMc().options.jumpKey;
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
      int var26 = ((KeyMappingAccessor)var10000).getKey().getCode();
      var10002 = (char)var16;
      int var22 = var26;
      char var23 = var10002;
      if (!KeyboardUtil.isKeyPressed(var23, var22)) {
         Globals.getMc().options.jumpKey.setPressed(false);
      }

      V = false;
      L = false;
      this.flushQueuedPackets();
      this.flushPingsOnThread();
      this.flushPings(var8);
      this.flushPongs();
   }

   private final boolean shouldRecast(int var1, long var2) {
      long var4 = ((long)var1 << 32 | var2 << 32 >>> 32) ^ e;
      int var12 = (int)((var4 ^ 108742344674400L) >>> 48);
      long var13 = (var4 ^ 108742344674400L) << 16 >>> 16;
      int var23 = ModeSetting.Y();
      int var10000 = this.canRecast();
      if (var23 == 0) {
         if (var10000) {
            int var24;
            byte var10001;
            var24 = InventoryUtil.INSTANCE.findElytra();
            var10000 = var24;
            var10001 = -2;
            label107:
            if (var23 == 0) {
               label105: {
                  if (var24 != -2) {
                     var10000 = var24;
                     var10001 = -1;
                     if (var23 != 0) {
                        break label107;
                     }

                     if (var24 != -1) {
                        var10000 = this.getGrimDurability();
                        if (var23 != 0) {
                           break label105;
                        }

                        if (var10000 != 0) {
                           var10000 = this.getGrim();
                           if (var23 != 0) {
                              break label105;
                           }

                           if (var10000 != 0) {
                              InteractionUtil.clickSlot$default(var24, 0, null, false, 14, null);
                              InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                           }
                        }
                     }
                  }

                  ElytraSwap.INSTANCE.K(true);
                  Globals.getPlayer().startGliding();
                  PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), Mode.START_FALL_FLYING));
                  var10000 = var24;
               }

               var10001 = -2;
            }

            label91:
            if (var23 == 0) {
               label89: {
                  if (var10000 != var10001) {
                     var10000 = var24;
                     var10001 = -1;
                     if (var23 != 0) {
                        break label91;
                     }

                     if (var24 != -1) {
                        var10000 = this.getGrimDurability();
                        if (var23 != 0) {
                           break label89;
                        }

                        if (var10000 != 0) {
                           var10000 = this.getGrim();
                           if (var23 != 0) {
                              break label89;
                           }

                           if (var10000 != 0) {
                              InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                              InteractionUtil.clickSlot$default(var24, 0, null, true, 6, null);
                           }
                        }
                     }
                  }

                  var10000 = var24;
               }

               if (var23 != 0) {
                  return (boolean)var10000;
               }

               var10001 = -2;
            }

            if (var10000 == var10001) {
               boolean var34 = this.getGrimDurability();
               if (var23 != 0) {
                  return var34;
               }

               if (var34) {
                  var34 = this.getGrim();
                  if (var23 != 0) {
                     return var34;
                  }

                  if (var34) {
                     InventoryUtil var28 = InventoryUtil.INSTANCE;
                     Item[] var26 = new Item[6];
                     Item var10003 = Items.NETHERITE_CHESTPLATE;
                     Intrinsics.checkNotNullExpressionValue(Items.NETHERITE_CHESTPLATE, "NETHERITE_CHESTPLATE");
                     var26[0] = var10003;
                     var10003 = Items.DIAMOND_CHESTPLATE;
                     Intrinsics.checkNotNullExpressionValue(Items.DIAMOND_CHESTPLATE, "DIAMOND_CHESTPLATE");
                     var26[1] = var10003;
                     var10003 = Items.IRON_CHESTPLATE;
                     Intrinsics.checkNotNullExpressionValue(Items.IRON_CHESTPLATE, "IRON_CHESTPLATE");
                     var26[2] = var10003;
                     var10003 = Items.GOLDEN_CHESTPLATE;
                     Intrinsics.checkNotNullExpressionValue(Items.GOLDEN_CHESTPLATE, "GOLDEN_CHESTPLATE");
                     var26[3] = var10003;
                     var10003 = Items.LEATHER_CHESTPLATE;
                     Intrinsics.checkNotNullExpressionValue(Items.LEATHER_CHESTPLATE, "LEATHER_CHESTPLATE");
                     var26[4] = var10003;
                     var10003 = Items.CHAINMAIL_CHESTPLATE;
                     Intrinsics.checkNotNullExpressionValue(Items.CHAINMAIL_CHESTPLATE, "CHAINMAIL_CHESTPLATE");
                     var26[5] = var10003;
                     SlotResult var25 = var28.findInInventory(var26, (char)var12, var13);
                     var34 = var25.isFound();
                     if (var23 != 0) {
                        return var34;
                     }

                     if (var34) {
                        InteractionUtil.clickSlot$default(var25.getSlot(), 0, null, false, 14, null);
                        InteractionUtil.clickSlot$default(6, 0, null, false, 14, null);
                        InteractionUtil.clickSlot$default(var25.getSlot(), 0, null, true, 6, null);
                     }
                  }
               }
            }

            return (boolean)1;
         }

         var10000 = 0;
      }

      return (boolean)var10000;
   }

   private final boolean canRecast() {
      int var13 = ModeSetting.Y();
      int var10000 = Globals.getPlayer().getAbilities().flying;
      if (var13 == 0) {
         if (!var10000) {
            var10000 = Globals.getPlayer().hasVehicle();
            if (var13 != 0) {
               return var10000;
            }

            if (!var10000) {
               var10000 = Globals.getPlayer().isTouchingWater();
               if (var13 != 0) {
                  return var10000;
               }

               if (!var10000) {
                  var10000 = Globals.getPlayer().hasStatusEffect(StatusEffects.LEVITATION);
                  if (var13 != 0) {
                     return var10000;
                  }

                  if (!var10000) {
                     float var17;
                     var10000 = (var17 = Globals.getPlayer().getHungerManager().getFoodLevel() - 6.0F) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
                     if (var13 != 0) {
                        return (boolean)var10000;
                     }

                     if (var10000 > 0) {
                        ItemStack var15 = Globals.getPlayer().getEquippedStack(EquipmentSlot.CHEST);
                        Intrinsics.checkNotNullExpressionValue(var15, "getItemBySlot(...)");
                        ItemStack var14 = var15;
                        var10000 = var14.isOf(Items.ELYTRA);
                        label76:
                        if (var13 == 0) {
                           if (var10000 != 0) {
                              byte var18 = this.hasDurability(var14);
                              if (var13 != 0) {
                                 return (boolean)var18;
                              }

                              if (var18 != 0) {
                                 return (boolean)1;
                              }

                              var10000 = this.getAllowBroken();
                              if (var13 != 0) {
                                 break label76;
                              }

                              if (var10000 != 0) {
                                 var18 = this.getGrim();
                                 if (var13 != 0) {
                                    return (boolean)var18;
                                 }

                                 if (var18 == 0) {
                                    return (boolean)1;
                                 }
                              }
                           }

                           var10000 = this.getGrimDurability();
                        }

                        if (var13 == 0) {
                           if (var10000 == 0) {
                              return (boolean)0;
                           }

                           var10000 = InventoryUtil.INSTANCE.findElytra();
                        }

                        if (var13 != 0) {
                           return (boolean)var10000;
                        }

                        if (var10000 != -1) {
                           return (boolean)1;
                        }

                        return (boolean)0;
                     }
                  }
               }
            }
         }

         var10000 = 0;
      }

      return (boolean)var10000;
   }

   public final boolean hasDurability(@NotNull ItemStack s) {
      int var10000 = ModeSetting.Y();
      Intrinsics.checkNotNullParameter(s, "s");
      int var2 = var10000;
      var10000 = s.getDamage();
      if (var2 == 0) {
         if (var10000 >= s.getMaxDamage() - 1) {
            return (boolean)0;
         }

         var10000 = s.contains(DataComponentTypes.GLIDER);
      }

      if (var2 != 0) {
         return (boolean)var10000;
      } else {
         return (boolean)(var10000 != 0 ? 1 : 0);
      }
   }

   private final boolean useGrimDurability() {
      return this.getGrimDurability() || this.getTunnelsOffset();
   }

   private final void flushQueuedPackets() {
      K = true;

      while (!l.isEmpty()) {
         Object var10000 = l.poll();
         Intrinsics.checkNotNullExpressionValue(var10000, "poll(...)");
         PacketUtil.sendPacket((Packet)var10000);
      }

      K = false;
   }

   private final void flushPingsOnThread() {
      if (Globals.getMc().isOnThread()) {
         while (!delayedPings.isEmpty()) {
            ((CommonPingS2CPacket)delayedPings.poll()).apply(Globals.getNetworkHandler());
         }
      }
   }

   private final void flushPongs() {
      K = true;

      while (!delayedPongs.isEmpty()) {
         Object var10000 = delayedPongs.poll();
         Intrinsics.checkNotNullExpressionValue(var10000, "poll(...)");
         PacketUtil.sendPacket((Packet)var10000);
      }

      K = false;
      recastYaw = Globals.getPlayer().getYaw();
   }

   private final void flushPings(int var1) {
      byte var7 = 0;
      byte var8 = 0;
      long var2 = ((long)var7 << 32 | (long)var1 << 48 >>> 32 | (long)var8 << 48 >>> 48) ^ e;
      K = true;

      while (!N.isEmpty()) {
         Object var10000 = N.poll();
         Intrinsics.checkNotNullExpressionValue(var10000, zkmBootstrap<"v">(25150, 4412535570189575774L ^ var2));
         PacketUtil.sendPacket((Packet)var10000);
         if (var1 >= 0) {
         }
      }

      K = false;
   }

   private static final boolean settingVisible() {
      return INSTANCE.getGrim();
   }

   private static final boolean settingVisible2() {
      return INSTANCE.getGrim();
   }

   private static final boolean settingVisible3() {
      return !INSTANCE.getGrim();
   }

   private static final boolean settingVisible4() {
      int var6 = ModeSetting.Y();
      boolean var10000 = INSTANCE.getChangePitch();
      if (var6 == 0) {
         if (!var10000) {
            return false;
         }

         var10000 = INSTANCE.getGrim();
      }

      return var6 != 0 ? var10000 : !var10000;
   }

   private static final boolean settingVisible5() {
      int var4 = ModeSetting.Y();
      boolean var10000 = INSTANCE.getGrim();
      if (var4 == 0) {
         var10000 = !var10000;
      }

      return var10000;
   }

   static {
      long var20 = e ^ 85132381447997L;
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[38];
      int var16 = 0;
      String var15 = "\u0082\u0017¿À\u0094ì\u009c$N¶Aû}i=»dt¡Ã\u001eÞY\u0080ÓÆ_+*z¡\u0006C\u001dd9kæNÉ$á)\r_ÓiÐ{\bèáûò\u000eÜñ\f\bû\u008cÒþ\u0089\u001c\u0011\u0019JO0Í\u00adßØÁ\u0087,\u0080 ê\u0089\u0013rÂdo\n\u001e\u009b\u0085\u0086bÔ+Vú\u0092>/3\u001d\u0015n\u0098\u001cþí|ÇVwlÈÛÙ\u001f:8¯µ¦ê\f\u0098)\u0098\r`\u0098«\b\u00167/k\u0098 \u008fN\u0095\u0095:ô\u0014Ün×¢N+b\\8#<ÆÎ\u0010\u0082JÿÎ÷ÃZ,\u0083\n\u0098\u0018Ó±\u0088\u008a!wðÛO\u00879Öê\u0089Ü»^\u000fáñ\u0085\u0094];¨ÄÝb\u009ecX\f\u00ad\u0098\u0090+®TS\u000fò'\u0091ub$ÛJHÚ$ý\u0010z\u0015q\u008aÙÈPÇiÑû C;8üqÃ¿¤õ|®Üóí{9ÄO$Þ§hkB\u000e\u0003×'\u0095\u000bïC¬\u0093Ð\u000b²Z\u008d\u009d¹íâ\u0096\u009f\u009be\u0005\u008f2RRry©¯=w»\u007fÚÊU\u0001\u0000ç\u0097\u000f2|W\u001c\b;´¼¢Î$ 2å/Ú¤ì´ásÔî.U¦WG\u0083Û\u00adÄy¯\u0014F\u0098\u0011 ´\tl\f×QdÇZ)}v\u0017 Y.\u000bns\u0085\u0014 H\u0083×\u0090ýíeä\u001a$ìX×·Ëü¬o«JÍ³\u0096\u0086\u000bö²\u007f8b~Ä\u00881`ÙRTì¤%îVgU\rÇ¨¯\u00112+\u001c\u0095Ö9\u008c:]îê\u009f»j\u0003(þ¤\u0092\u0099\u00ad`E|Ó¸ª\u007f©\u0004Eßß\u009e*\u0098 \u0081\u009aJØË¯\u001eüÐ©È_h|{ÑùwÛ]¨üã¿8ìáñ\u001cÞnÆ\u0003\r\u0082ÈRb\u0001\u009c\u0081ïó·\u000f¶CÈXÃ~\u0015Tº¢¶Y<\u0088:aý+OÛL\u0081ý\u000e£ÃÔ¯+æ\u0082Õ¡\u0092É\u009d\u0016\u0090!¢BoL)¹'\u0018\u0094\u001cµ6l6\u0016é\u0014fÙ\u008dEEL\u0083ÔO\u0003Sbõâèf\u0002\u0096¸=MÞ\u0099Ï\u001fD\u0092gAÁ6èõx\u0017ç`Ñm\u0090\u008d+ëq×ö3ï\u0019Á\fÝ\u0018Í\u000b °\u009275ÄãmÑ¹\u0002\u00824§?$\u0080y\u0081£C=Ãþ¥jð¨ÒëÆu©aé¥©\u00146¼ÙÄ\r¿Qò¿Ë\u000ee\u0002áC\n\u0089\u0001e,Ó\b\u000ez\u0093Æt\u0088µ\f\u0003^\u0017\u0018*Æõr\rm\u0097\\:\u0011R~\u0097)N°H\u000b\"Tº\u0007¶a\u0018Æ|y\u00adróÖ\u009c\u000fúè\u0016]QÖ|©\u0015³þIxÏé\u0090\u0081MÉÉý\u00ad ²· +2øhQæWö)ó3\u00ad®Õ÷\u0012µ¢ÛBÎÿ¾¬ÿî\u00ad¶°¦í\u0082\u0003½oq.\nÿz`\u0010\\\u001c'GÎrd´Ý<iî\r1\u0088ù$xS]!Òv´\u0091¡\u001e\u0006Ï\u001dFE\u0095nº`e¸0õ\u0085\u007fãÛ\u000eaèé$·ë÷·\u009e\t)t¢Ýâ\f@ö\u0011ÿfº#\n\u0081º\u009dÌ&\\¾TC\u0014¶ %ª¢8\u001a3e\u0098Uv\u001a(¥\u0084{ÙÒ.\u0005h{rA\u0088\u0014Ôb\u0084¥\u0088¥)¸N+øÐCÂ\u0017¥\u0088cãu\rIèû\u0093÷\b\u0010\u009b¶\u0091\u008d\u0085Ò$1ð\u009bgÂb\u0001\u000f\\(YÃ§2¢Pù$7«Yàlc3ØÈ, Rä±\u0004x\u0003SYÀí\u0090e¥FÇò\u0019Ãy¯Á *\u000fàæT:¡ñ \u009b9Ø\u001a\u008fpbàk»¼\t\u009cdË3zNÿÛ[wí(é\u008b°9ÊAëþõhìÝ\u0083S\u008a\u000füôûà,P\u008f4\u009eO\u009b\\d3\u0093åØC\u0095LZ\u001f0p(Þ*CNxÛç|\n\u009dÞ\u0095w\u0005úf\u0004n\u001a\u008dA\u0015è÷f\u009bÀ&\u0081\"Û2uù0b~Ésg ¦\nª¬ó6íw¥\u009eej*°Óº°\\ºn4un5õ\u0089\u0098K\u000ePË\u00978w#Ò\u009b\u009aék\u0004¬fqÆ¨Sl¦\u0012«r\u001f@¶ÏÈ§Ã+Ï\u0094\u0000ÿ¾ÉO#óTÓ*?\u0092<±È\u000b\u0080\u0095½\u0004®^\u0099ÃòÐ\u0099 NWiä\u009d\u007fõ.h\u0016k\u0016æý¢%á5\u0004Ð\u0005\u0082\u0007|¬<C\u0094jìÒ\u0006\u0010\u0093:ñ\b\u008a4\u0087\u0099\u0089õú\u0091\u008c\u0017#\u0099 @Í\u00027\u008fãµP0ªX¤Ír¼\u0005\nI\u001d\u009ck[\u0083ô\u000b¡(Ê\u001bëê\u0080 %l:\u0093\u001eèàÔ¯\u0011R\u0019\u0095¸úë\fDx\u0091\u0018uA¶X@\u000bx\u000f3¿®\u007f,¬\u007fNÎµ+K\n\u0004\u0014°q»ô4m\u0082Á[áµå5]Ø\u0004\u008d×þ\u009cC5\u00059pLµ\r´û\u0081ç®\u0002í\f\u0084á\u0089go,Æ¼L½>:{\r\føë\u00ad~\fÎÔ\u000bjõíY\u0083g!Ò\u0087Xd¸¯~_óÑtsþ-\u008dJÙmk\u009b\u009aòí}°e\u008b~M¸èð\u0002Óx\u0011mkó\u000bþ\u0094.Bç\u00047¦MÉ\u0010ÑuBÆ1+UÖø¨\u0010\u0007\u001c;µq ¹YX§/ÁõÊ\rvÏÐ´Mg~¼\u0018\në-\u009bNÛíWjô-\u0010PY \u001fõ\u0011òDq\u0099b÷]_<D-#Õz«\u008b\u000f¹d/Â5Ü_\u0087»U¢½0\u0018]Î£!Êk±×h¬}Ezï:Ø\u009a\u0086(PrsÏ&\u008a¢\u0090\u000fÆÊUöM E\u009b@jð\u0082ýo1\u008f\u0000\u001c®\u0010\f<Õ\u0017o¿(7\u000e\u0002\u008b£½Â\u0096T(ÿ\n8kÕ\u0005îL??¨G\u0087ÂP\u008cº\u009f-\u001a\u0016äÐ\u000fÓA?«÷\u0006\t(\"ï\r\\¯¼p\u0000\u0018R÷>ñx5ù¤§\u0092DÄI\bVùì\u0014ï~\u0088Ò<¦ ú\u008cÝXÍ#\u008a\u001d:{I=õq.\u0013\u0000Dõ7ÁÁà¬Ä?\u00010\f¡a$Í\u009cí\u0003íÈÕZw\u0016¬u³§\u0094%\n\u008a\u0005\u0004\u0096\u0096¯\u0002,y\u0083Å\u008b³+\u0099.Ço±ú\u001b\u0005ü2ôù=Îì6\u009aÄ\"ãï\u0081_\u0096(\nBÐÔ5Åý\u0085:Ò×5\u0017w×O¨ºO\u007faM\u0089\u0011ìJ\u001c\u009d¨¥H´\u0096\u000eL½\u0088û\u0081ÏÚ\u008b9Ö\u009d%+À\u0085þÇÆ\u008cî\u0082Áv:X\u009bë& \u001dóKS\u008b\u001aÍ\u0080ò\u0018êéaÔ\"âZ»L\u0015\u0014+¹o\u0081JÖ\u008a§\u009aû\u0005Ox\u0010Yuë\u0007_ÄÐ|¾\u001a/h}Úêî\u0018Ôä¦ù§Î\u0010sàß\u001cÙj¼½î\u0015É@>\u001døk½(\u0093é\u0099i\u0099À\u0094#r\u009aCÁ\u0006\rrrÄïùn°`R\u0081ÎgC}\u0089¾´,´îåÆ\u0095j\u0003\u0086(¡ÞéCE©ëÀCn\u0098Ï~*|èþ½\u0097ýR\u0084ò²»ä\u0081M±½÷Øe\u001cf\u0013#É\u008bh(tÞbh\n\u008e§'\u0007Æf\u00adb4ìºsjÒJ¿\t}ÐJÎ\u008eW>¼©ù0<\u009d\u0087oâï\u008d";
      int var17 = "\u0082\u0017¿À\u0094ì\u009c$N¶Aû}i=»dt¡Ã\u001eÞY\u0080ÓÆ_+*z¡\u0006C\u001dd9kæNÉ$á)\r_ÓiÐ{\bèáûò\u000eÜñ\f\bû\u008cÒþ\u0089\u001c\u0011\u0019JO0Í\u00adßØÁ\u0087,\u0080 ê\u0089\u0013rÂdo\n\u001e\u009b\u0085\u0086bÔ+Vú\u0092>/3\u001d\u0015n\u0098\u001cþí|ÇVwlÈÛÙ\u001f:8¯µ¦ê\f\u0098)\u0098\r`\u0098«\b\u00167/k\u0098 \u008fN\u0095\u0095:ô\u0014Ün×¢N+b\\8#<ÆÎ\u0010\u0082JÿÎ÷ÃZ,\u0083\n\u0098\u0018Ó±\u0088\u008a!wðÛO\u00879Öê\u0089Ü»^\u000fáñ\u0085\u0094];¨ÄÝb\u009ecX\f\u00ad\u0098\u0090+®TS\u000fò'\u0091ub$ÛJHÚ$ý\u0010z\u0015q\u008aÙÈPÇiÑû C;8üqÃ¿¤õ|®Üóí{9ÄO$Þ§hkB\u000e\u0003×'\u0095\u000bïC¬\u0093Ð\u000b²Z\u008d\u009d¹íâ\u0096\u009f\u009be\u0005\u008f2RRry©¯=w»\u007fÚÊU\u0001\u0000ç\u0097\u000f2|W\u001c\b;´¼¢Î$ 2å/Ú¤ì´ásÔî.U¦WG\u0083Û\u00adÄy¯\u0014F\u0098\u0011 ´\tl\f×QdÇZ)}v\u0017 Y.\u000bns\u0085\u0014 H\u0083×\u0090ýíeä\u001a$ìX×·Ëü¬o«JÍ³\u0096\u0086\u000bö²\u007f8b~Ä\u00881`ÙRTì¤%îVgU\rÇ¨¯\u00112+\u001c\u0095Ö9\u008c:]îê\u009f»j\u0003(þ¤\u0092\u0099\u00ad`E|Ó¸ª\u007f©\u0004Eßß\u009e*\u0098 \u0081\u009aJØË¯\u001eüÐ©È_h|{ÑùwÛ]¨üã¿8ìáñ\u001cÞnÆ\u0003\r\u0082ÈRb\u0001\u009c\u0081ïó·\u000f¶CÈXÃ~\u0015Tº¢¶Y<\u0088:aý+OÛL\u0081ý\u000e£ÃÔ¯+æ\u0082Õ¡\u0092É\u009d\u0016\u0090!¢BoL)¹'\u0018\u0094\u001cµ6l6\u0016é\u0014fÙ\u008dEEL\u0083ÔO\u0003Sbõâèf\u0002\u0096¸=MÞ\u0099Ï\u001fD\u0092gAÁ6èõx\u0017ç`Ñm\u0090\u008d+ëq×ö3ï\u0019Á\fÝ\u0018Í\u000b °\u009275ÄãmÑ¹\u0002\u00824§?$\u0080y\u0081£C=Ãþ¥jð¨ÒëÆu©aé¥©\u00146¼ÙÄ\r¿Qò¿Ë\u000ee\u0002áC\n\u0089\u0001e,Ó\b\u000ez\u0093Æt\u0088µ\f\u0003^\u0017\u0018*Æõr\rm\u0097\\:\u0011R~\u0097)N°H\u000b\"Tº\u0007¶a\u0018Æ|y\u00adróÖ\u009c\u000fúè\u0016]QÖ|©\u0015³þIxÏé\u0090\u0081MÉÉý\u00ad ²· +2øhQæWö)ó3\u00ad®Õ÷\u0012µ¢ÛBÎÿ¾¬ÿî\u00ad¶°¦í\u0082\u0003½oq.\nÿz`\u0010\\\u001c'GÎrd´Ý<iî\r1\u0088ù$xS]!Òv´\u0091¡\u001e\u0006Ï\u001dFE\u0095nº`e¸0õ\u0085\u007fãÛ\u000eaèé$·ë÷·\u009e\t)t¢Ýâ\f@ö\u0011ÿfº#\n\u0081º\u009dÌ&\\¾TC\u0014¶ %ª¢8\u001a3e\u0098Uv\u001a(¥\u0084{ÙÒ.\u0005h{rA\u0088\u0014Ôb\u0084¥\u0088¥)¸N+øÐCÂ\u0017¥\u0088cãu\rIèû\u0093÷\b\u0010\u009b¶\u0091\u008d\u0085Ò$1ð\u009bgÂb\u0001\u000f\\(YÃ§2¢Pù$7«Yàlc3ØÈ, Rä±\u0004x\u0003SYÀí\u0090e¥FÇò\u0019Ãy¯Á *\u000fàæT:¡ñ \u009b9Ø\u001a\u008fpbàk»¼\t\u009cdË3zNÿÛ[wí(é\u008b°9ÊAëþõhìÝ\u0083S\u008a\u000füôûà,P\u008f4\u009eO\u009b\\d3\u0093åØC\u0095LZ\u001f0p(Þ*CNxÛç|\n\u009dÞ\u0095w\u0005úf\u0004n\u001a\u008dA\u0015è÷f\u009bÀ&\u0081\"Û2uù0b~Ésg ¦\nª¬ó6íw¥\u009eej*°Óº°\\ºn4un5õ\u0089\u0098K\u000ePË\u00978w#Ò\u009b\u009aék\u0004¬fqÆ¨Sl¦\u0012«r\u001f@¶ÏÈ§Ã+Ï\u0094\u0000ÿ¾ÉO#óTÓ*?\u0092<±È\u000b\u0080\u0095½\u0004®^\u0099ÃòÐ\u0099 NWiä\u009d\u007fõ.h\u0016k\u0016æý¢%á5\u0004Ð\u0005\u0082\u0007|¬<C\u0094jìÒ\u0006\u0010\u0093:ñ\b\u008a4\u0087\u0099\u0089õú\u0091\u008c\u0017#\u0099 @Í\u00027\u008fãµP0ªX¤Ír¼\u0005\nI\u001d\u009ck[\u0083ô\u000b¡(Ê\u001bëê\u0080 %l:\u0093\u001eèàÔ¯\u0011R\u0019\u0095¸úë\fDx\u0091\u0018uA¶X@\u000bx\u000f3¿®\u007f,¬\u007fNÎµ+K\n\u0004\u0014°q»ô4m\u0082Á[áµå5]Ø\u0004\u008d×þ\u009cC5\u00059pLµ\r´û\u0081ç®\u0002í\f\u0084á\u0089go,Æ¼L½>:{\r\føë\u00ad~\fÎÔ\u000bjõíY\u0083g!Ò\u0087Xd¸¯~_óÑtsþ-\u008dJÙmk\u009b\u009aòí}°e\u008b~M¸èð\u0002Óx\u0011mkó\u000bþ\u0094.Bç\u00047¦MÉ\u0010ÑuBÆ1+UÖø¨\u0010\u0007\u001c;µq ¹YX§/ÁõÊ\rvÏÐ´Mg~¼\u0018\në-\u009bNÛíWjô-\u0010PY \u001fõ\u0011òDq\u0099b÷]_<D-#Õz«\u008b\u000f¹d/Â5Ü_\u0087»U¢½0\u0018]Î£!Êk±×h¬}Ezï:Ø\u009a\u0086(PrsÏ&\u008a¢\u0090\u000fÆÊUöM E\u009b@jð\u0082ýo1\u008f\u0000\u001c®\u0010\f<Õ\u0017o¿(7\u000e\u0002\u008b£½Â\u0096T(ÿ\n8kÕ\u0005îL??¨G\u0087ÂP\u008cº\u009f-\u001a\u0016äÐ\u000fÓA?«÷\u0006\t(\"ï\r\\¯¼p\u0000\u0018R÷>ñx5ù¤§\u0092DÄI\bVùì\u0014ï~\u0088Ò<¦ ú\u008cÝXÍ#\u008a\u001d:{I=õq.\u0013\u0000Dõ7ÁÁà¬Ä?\u00010\f¡a$Í\u009cí\u0003íÈÕZw\u0016¬u³§\u0094%\n\u008a\u0005\u0004\u0096\u0096¯\u0002,y\u0083Å\u008b³+\u0099.Ço±ú\u001b\u0005ü2ôù=Îì6\u009aÄ\"ãï\u0081_\u0096(\nBÐÔ5Åý\u0085:Ò×5\u0017w×O¨ºO\u007faM\u0089\u0011ìJ\u001c\u009d¨¥H´\u0096\u000eL½\u0088û\u0081ÏÚ\u008b9Ö\u009d%+À\u0085þÇÆ\u008cî\u0082Áv:X\u009bë& \u001dóKS\u008b\u001aÍ\u0080ò\u0018êéaÔ\"âZ»L\u0015\u0014+¹o\u0081JÖ\u008a§\u009aû\u0005Ox\u0010Yuë\u0007_ÄÐ|¾\u001a/h}Úêî\u0018Ôä¦ù§Î\u0010sàß\u001cÙj¼½î\u0015É@>\u001døk½(\u0093é\u0099i\u0099À\u0094#r\u009aCÁ\u0006\rrrÄïùn°`R\u0081ÎgC}\u0089¾´,´îåÆ\u0095j\u0003\u0086(¡ÞéCE©ëÀCn\u0098Ï~*|èþ½\u0097ýR\u0084ò²»ä\u0081M±½÷Øe\u001cf\u0013#É\u008bh(tÞbh\n\u008e§'\u0007Æf\u00adb4ìºsjÒJ¿\t}ÐJÎ\u008eW>¼©ù0<\u009d\u0087oâï\u008d"
         .length();
      char var14 = 136;
      int var33 = -1;

      label54:
      while (true) {
         String var34 = var15.substring(++var33, var33 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var34.getBytes("ISO-8859-1"));
            String var45 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var45;
                  if ((var33 += var14) >= var17) {
                     h = var18;
                     i = new String[38];
                     u = new HashMap(13);
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
                     String var4 = "`]\u009d¢¦Ñ}\u009deÇ?K\u0000ø\u009a\"\u009e\u0012\u008b\u009dÜb`Ò=\\jLeÝrçrû@¼q\u0089ÁÉ^q\u0098\u0004.|%n±z|¾³+h\u0003\u000e\u0015PèU·6ú5\u0019rwíp\u008bCMåÝ\u0017pA\u001fp\u0097!`$ùóB¡";
                     int var5 = "`]\u009d¢¦Ñ}\u009deÇ?K\u0000ø\u009a\"\u009e\u0012\u008b\u009dÜb`Ò=\\jLeÝrçrû@¼q\u0089ÁÉ^q\u0098\u0004.|%n±z|¾³+h\u0003\u000e\u0015PèU·6ú5\u0019rwíp\u008bCMåÝ\u0017pA\u001fp\u0097!`$ùóB¡"
                        .length();
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var37 = var6;
                        var10001 = var3++;
                        long var49 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var52 = -1;

                        while (true) {
                           long var8 = var49;
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
                           long var54 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var52) {
                              case 0:
                                 var37[var10001] = var54;
                                 if (var2 >= var5) {
                                    o = var6;
                                    t = new Integer[13];
                                    KProperty[] var30 = new KProperty[]{
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(ElytraRecast.class, "grim", "getGrim()Z", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(ElytraRecast.class, "tunnelsOffset", "getTunnelsOffset()Z", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(ElytraRecast.class, "bypass", "getBypass()Lsu/catlean/t6;", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(ElytraRecast.class, "grimDurability", "getGrimDurability()Z", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(ElytraRecast.class, "changePitch", "getChangePitch()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(ElytraRecast.class, "pitchValue", "getPitchValue()F", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(ElytraRecast.class, "allowBroken", "getAllowBroken()Z", 0))
                                    };
                                    delegatedProperties = var30;
                                    INSTANCE = new ElytraRecast();
                                    grim$delegate = SettingFactory.booleanSetting$default(INSTANCE, "grim", true, null, null, 12, null);
                                    tunnelsOffset$delegate = SettingFactory.booleanSetting$default(INSTANCE, "tunnels-offset", true, null, null, 12, null);
                                    bypass$delegate = SettingFactory.modeSetting$default(
                                       INSTANCE, "bypass", ElytraRecastBypassMode.SEMI, null, ElytraRecast::settingVisible, 4, null
                                    );
                                    grimDurability$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "grim-durability", true, null, ElytraRecast::settingVisible2, 4, null
                                    );
                                    changePitch$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "change-pitch", true, null, ElytraRecast::settingVisible3, 4, null
                                    );
                                    pitchValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "pitch", 87.0F, RangesKt.rangeTo(0.0F, 90.0F), null, ElytraRecast::settingVisible4, 8, null
                                    );
                                    allowBroken$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "allow-broken", true, null, ElytraRecast::settingVisible5, 4, null
                                    );
                                    l = new LinkedList();
                                    delayedPings = new LinkedList();
                                    delayedPongs = new LinkedList();
                                    N = new LinkedList();
                                    B = true;
                                    return;
                                 }
                                 break;
                              default:
                                 var37[var10001] = var54;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "¯\u009f3Xg°âT»)\u009c\bõ]â¼";
                                 var5 = "¯\u009f3Xg°âT»)\u009c\bõ]â¼".length();
                                 var2 = 0;
                           }

                           byte var43 = var2;
                           var2 += 8;
                           var7 = var4.substring(var43, var2).getBytes("ISO-8859-1");
                           var37 = var6;
                           var10001 = var3++;
                           var49 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var52 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var33);
                  break;
               default:
                  var18[var16++] = var45;
                  if ((var33 += var14) < var17) {
                     var14 = var15.charAt(var33);
                     continue label54;
                  }

                  var15 = "ÿò@\u0083\u009aÞÉ\b\u0095Q¢þ´\u0095\u0091\u0086²+|ÇôäÄ\"(³\\\u0085¥el½\u0089\u001dK\u009a'TD÷ 3¬Y\u00164,¸¬óãõ.\u00156À²/\u0081Ft\u009c`Õ·Þ\u0015\u008eÑÝÁ%,";
                  var17 = "ÿò@\u0083\u009aÞÉ\b\u0095Q¢þ´\u0095\u0091\u0086²+|ÇôäÄ\"(³\\\u0085¥el½\u0089\u001dK\u009a'TD÷ 3¬Y\u00164,¸¬óãõ.\u00156À²/\u0081Ft\u009c`Õ·Þ\u0015\u008eÑÝÁ%,"
                     .length();
                  var14 = '(';
                  var33 = -1;
            }

            var34 = var15.substring(++var33, var33 + var14);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 12265;
      if (i[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().threadId();
            var4 = (Object[])n.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               n.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("su/catlean/_0", var10);
         }

         byte[] var6 = new byte[8];
         var6[0] = (byte)(var1 >>> 56);

         for (int var7 = 1; var7 < 8; var7++) {
            var6[var7] = (byte)(var1 << var7 * 8 >>> 56);
         }

         DESKeySpec var11 = new DESKeySpec(var6);
         SecretKey var8 = ((SecretKeyFactory)var4[1]).generateSecret(var11);
         ((Cipher)var4[0]).init(2, var8, (IvParameterSpec)var4[2]);
         byte[] var9 = h[var5].getBytes("ISO-8859-1");
         i[var5] = zkmDecodeUtf8(((Cipher)var4[0]).doFinal(var9));
      }

      return i[var5];
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
         throw new RuntimeException("su/catlean/_0" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 727;
      if (t[var3] == null) {
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
         long var5 = o[var3];
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
         Object[] var9 = (Object[])u.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               u.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/_0", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         t[var3] = var15;
      }

      return t[var3];
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
         throw new RuntimeException("su/catlean/_0" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
