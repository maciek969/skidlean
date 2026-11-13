package su.catlean.module.player;

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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.ArmorUtil;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.HoleManager;
import su.catlean.util.InterpolationTracker;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.ServerInfo;
import su.catlean.util.SlotResult;
import su.catlean.util.Timer;
import su.catlean.util.combat.PlaceResult;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class AutoMend extends Module {
   @NotNull
   public static final AutoMend INSTANCE;
   static final KProperty[] delegatedProperties;
   @NotNull
   private static final IntSetting minDurability$delegate;
   @NotNull
   private static final IntSetting maxDurability$delegate;
   @NotNull
   private static final BooleanSetting overrideOnLoot$delegate;
   @NotNull
   private static final IntSetting xpFactor$delegate;
   @NotNull
   private static final SettingGroup pauseGroup$delegate;
   @NotNull
   private static final BooleanSetting ifMove$delegate;
   @NotNull
   private static final BooleanSetting ifMining$delegate;
   @NotNull
   private static final BooleanSetting ifEating$delegate;
   @NotNull
   private static final BooleanSetting ifNotInHole$delegate;
   @NotNull
   private static final Timer timer;
   private static boolean V;
   private static final long a = ZkmKeys.a(3405732608584912864L, -764973721978237715L, MethodHandles.lookup().lookupClass()).a(84265506057186L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long[] f;
   private static final Integer[] h;
   private static final Map j;

   private AutoMend() {
      super("auto-mend", Categories.getPlayer(), null, null);
   }

   private final int getMinDurability() {
      return ((Number)minDurability$delegate.getValue(this, delegatedProperties[0])).intValue();
   }

   private final int getMaxDurability() {
      return ((Number)maxDurability$delegate.getValue(this, delegatedProperties[1])).intValue();
   }

   private final boolean getOverrideOnLoot() {
      return (Boolean)overrideOnLoot$delegate.getValue(this, delegatedProperties[2]);
   }

   private final int getXpFactor() {
      return ((Number)xpFactor$delegate.getValue(this, delegatedProperties[3])).intValue();
   }

   private final SettingParent getPauseGroup() {
      return (SettingParent)pauseGroup$delegate.getValue(this, delegatedProperties[4]);
   }

   private final boolean getIfMove() {
      return (Boolean)ifMove$delegate.getValue(this, delegatedProperties[5]);
   }

   private final boolean getIfMining() {
      return (Boolean)ifMining$delegate.getValue(this, delegatedProperties[6]);
   }

   private final boolean getIfEating() {
      return (Boolean)ifEating$delegate.getValue(this, delegatedProperties[7]);
   }

   private final boolean getIfNotInHole() {
      return (Boolean)ifNotInHole$delegate.getValue(this, delegatedProperties[8]);
   }

   @NotNull
   public final Timer getTimer() {
      return timer;
   }

   public final boolean x() {
      return V;
   }

   public final void I(boolean <set-?>) {
      V = <set-?>;
   }

   @Override
   public void onDisable(long var1) {
      V = false;
   }

   @Flow(
      priority = -10
   )
   private final void onPlayerUpdate(PlayerUpdateEvent var1) {
      long var45;
      int var47;
      int var60;
      SlotResult var61;
      int var103;
      long var2 = a ^ 54321599286953L;
      var45 = (var2 ^ 29810045851167L) >>> 16;
      var47 = (int)((var2 ^ 29810045851167L) << 48 >>> 48);
      var103 = ArmorUtil.zkmIntConstant();
      InventoryUtil var10001 = InventoryUtil.INSTANCE;
      Item[] var62 = new Item[1];
      Item var10004 = Items.EXPERIENCE_BOTTLE;
      Intrinsics.checkNotNullExpressionValue(Items.EXPERIENCE_BOTTLE, "EXPERIENCE_BOTTLE");
      var62[0] = var10004;
      var61 = var10001.findInHotbar(var62);
      var60 = var103;
      var103 = this.getIfMining();
      label384:
      if (var60 == 0) {
         if (var103) {
            boolean var104 = Globals.getInteractionManager().isBreakingBlock();
            if (var60 == 0) {
               if (var104) {
                  return;
               }

               var104 = SpeedMine.INSTANCE.getMiningQueue().isEmpty();
            }

            if (var60 == 0) {
               var104 = !var104;
            }

            if (var104) {
               var103 = SpeedMine.INSTANCE.getResetOnSwitch();
               if (var60 != 0) {
                  break label384;
               }

               if (var103) {
                  return;
               }
            }
         }

         var103 = timer.hasPassed(500);
      }

      if (var60 == 0) {
         if (!var103) {
            return;
         }

         var103 = this.getIfMove();
      }

      label365:
      if (var60 == 0) {
         if (var103) {
            var103 = MovementUtil.INSTANCE.isMoving();
            if (var60 != 0) {
               break label365;
            }

            if (var103) {
               return;
            }
         }

         var103 = this.getIfEating();
      }

      label358:
      if (var60 == 0) {
         if (var103) {
            var103 = Globals.getPlayer().isUsingItem();
            if (var60 != 0) {
               break label358;
            }

            if (var103) {
               return;
            }
         }

         var103 = this.getIfNotInHole();
      }

      label389: {
         label390: {
            label391: {
               label348:
               if (var60 == 0) {
                  if (var103) {
                     HoleManager var105 = HoleManager.INSTANCE;
                     Entity var74 = Globals.getPlayer();
                     BlockPos var129 = BlockPos.ofFloored(this.getPos(var74));
                     Intrinsics.checkNotNullExpressionValue(var129, "containing(...)");
                     var103 = var105.isHole(var129);
                     if (var60 != 0) {
                        break label348;
                     }

                     if (!var103) {
                        return;
                     }
                  }

                  var106 = AutoCrystal.INSTANCE;
                  if (var60 != 0) {
                     break label391;
                  }

                  var103 = AutoCrystal.INSTANCE.getEnabled();
               }

               if (!var103) {
                  break label390;
               }

               var106 = AutoCrystal.INSTANCE;
            }

            Object var107 = var106.getTarget();
            if (var60 == 0) {
               if (var107 == null) {
                  break label390;
               }

               var107 = AutoCrystal.INSTANCE.getPlaceResultRef().get();
            }

            if (var60 == 0) {
               if (var107 == null) {
                  break label390;
               }

               var107 = AutoCrystal.INSTANCE.getPlaceResultRef().get();
               Intrinsics.checkNotNull(var107);
            }

            float var132;
            var103 = (var132 = ((PlaceResult)var107).getDamageData().getDamage() - 4.0F) == 0.0F ? 0 : (var132 < 0.0F ? -1 : 1);
            if (var60 != 0) {
               break label389;
            }

            if (var103 > 0) {
               var103 = 0;
               break label389;
            }
         }

         Iterable var109 = Globals.getWorld().getEntities();
         Intrinsics.checkNotNullExpressionValue(var109, "entitiesForRendering(...)");
         Iterable var63 = var109;
         var var66 = new ArrayList();
         Iterator var68 = var63.iterator();

         label393: {
            label394: {
               while (true) {
                  if (var68.hasNext()) {
                     Object var69 = var68.next();
                     var110 = var69 instanceof ItemEntity;
                     if (var60 != 0) {
                        break;
                     }

                     if (var60 == 0) {
                        if (!var110) {
                           continue;
                        }

                        var66.add(var69);
                     }

                     if (var60 == 0) {
                        continue;
                     }
                  }

                  var63 = var66;
                  var111 = var63;
                  if (var60 != 0) {
                     break label394;
                  }

                  var110 = var63 instanceof Collection;
                  break;
               }

               if (var110) {
                  var111 = (Collection)var63;
                  if (var60 != 0) {
                     break label394;
                  }

                  if (var111.isEmpty()) {
                     var103 = 0;
                     break label393;
                  }
               }

               var111 = var63;
            }

            Iterator var65 = var111.iterator();

            label297:
            while (true) {
               if (!var65.hasNext()) {
                  var103 = 0;
                  break;
               }

               Object var90 = var65.next();
               ItemEntity var67 = (ItemEntity)var90;
               var103 = Intrinsics.areEqual(var67.getStack().getItem(), Items.EXPERIENCE_BOTTLE);

               while (true) {
                  label293: {
                     label399: {
                        if (var60 == 0) {
                           if (var103 == 0) {
                              break label399;
                           }

                           Vec3d var113 = INSTANCE.getPos(var67);
                           AutoMend var130 = INSTANCE;
                           Entity var78 = Globals.getPlayer();
                           double var133;
                           var103 = (var133 = var113.distanceTo(var130.getPos(var78)) - 1.0) == 0.0 ? 0 : (var133 < 0.0 ? -1 : 1);
                        }

                        if (var60 != 0) {
                           break label293;
                        }

                        if (var103 < 0) {
                           var103 = 1;
                           break label293;
                        }
                     }

                     var103 = 0;
                  }

                  if (var103 == 0) {
                     break;
                  }

                  var103 = 1;
                  if (var60 == 0) {
                     break label297;
                  }
               }
            }
         }

         label275: {
            var87 = var103;
            var103 = var87;
            label274:
            if (var60 == 0) {
               if (var87 != 0) {
                  var103 = this.getOverrideOnLoot();
                  if (var60 != 0) {
                     break label274;
                  }

                  if (var103 != 0) {
                     var115 = 100.0F;
                     break label275;
                  }
               }

               var103 = this.getMaxDurability();
            }

            var115 = var103;
         }

         label265: {
            var88 = var115;
            var103 = var87;
            label264:
            if (var60 == 0) {
               if (var87 != 0) {
                  var103 = this.getOverrideOnLoot();
                  if (var60 != 0) {
                     break label264;
                  }

                  if (var103 != 0) {
                     var117 = 100.0F;
                     break label265;
                  }
               }

               var103 = this.getMinDurability();
            }

            var117 = var103;
         }

         float var64 = var117;
         Iterable var91 = ArmorUtil.getArmorItems(Globals.getPlayer());
         var var95 = new ArrayList();

         for (Object var72 : var91) {
            ItemStack var73 = (ItemStack)var72;
            RegistryKey var131 = Enchantments.MENDING;
            Intrinsics.checkNotNullExpressionValue(Enchantments.MENDING, "MENDING");
            boolean var118 = EnchantUtil.hasEnchantment(var73, var131);
            if (var60 == 0) {
               if (!var118) {
                  continue;
               }

               var95.add(var72);
            }

            if (var60 != 0) {
               break;
            }
         }

         List var89;
         var89 = var95;
         var103 = V;
         label244:
         if (var60 == 0) {
            if (!V) {
               label240: {
                  label402: {
                     label403: {
                        var68 = var89.iterator();
                        Object var120 = var68;
                        if (var60 == 0) {
                           if (!var68.hasNext()) {
                              var121 = null;
                              break label403;
                           }

                           var120 = var68.next();
                        }

                        ItemStack var96 = (ItemStack)var120;
                        float var97 = INSTANCE.S(var96);

                        while (var68.hasNext()) {
                           ItemStack var70 = (ItemStack)var68.next();
                           float var100 = INSTANCE.S(var70);
                           var97 = Math.min(var97, var100);
                           if (var60 != 0) {
                              break label402;
                           }

                           if (var60 != 0) {
                              break;
                           }
                        }

                        var121 = var97;
                     }

                     if (var121 != null) {
                        var122 = var121;
                        break label240;
                     }
                  }

                  var122 = 100.0F;
               }

               float var134;
               var103 = (var134 = var122 - var64) == 0.0F ? 0 : (var134 < 0.0F ? -1 : 1);
               if (var60 != 0) {
                  break label244;
               }

               if (var103 < 0) {
                  V = true;
               }
            }

            var103 = V;
         }

         label216:
         if (var60 == 0) {
            if (var103 != 0) {
               label212: {
                  label406: {
                     label407: {
                        var68 = var89.iterator();
                        Object var123 = var68;
                        if (var60 == 0) {
                           if (!var68.hasNext()) {
                              var124 = null;
                              break label407;
                           }

                           var123 = var68.next();
                        }

                        ItemStack var98 = (ItemStack)var123;
                        float var99 = INSTANCE.S(var98);

                        while (var68.hasNext()) {
                           ItemStack var101 = (ItemStack)var68.next();
                           float var102 = INSTANCE.S(var101);
                           var99 = Math.min(var99, var102);
                           if (var60 != 0) {
                              break label406;
                           }

                           if (var60 != 0) {
                              break;
                           }
                        }

                        var124 = var99;
                     }

                     if (var124 != null) {
                        var125 = var124;
                        break label212;
                     }
                  }

                  var125 = 100.0F;
               }

               float var135;
               var103 = (var135 = var125 - var88) == 0.0F ? 0 : (var135 < 0.0F ? -1 : 1);
               if (var60 != 0) {
                  break label216;
               }

               if (var103 >= 0) {
                  V = false;
               }
            }

            var103 = Globals.getPlayer().isGliding();
         }

         label188: {
            label187: {
               label186:
               if (var60 == 0) {
                  label184:
                  if (var103 == 0) {
                     ClientWorld var126 = Globals.getWorld();
                     Entity var82 = Globals.getPlayer();
                     var127 = var126.getBlockState(BlockPos.ofFloored(this.getPos(var82)).down()).isReplaceable();
                     if (var60 == 0) {
                        if (var127) {
                           ClientWorld var128 = Globals.getWorld();
                           Entity var86 = Globals.getPlayer();
                           var103 = var128.getBlockState(BlockPos.ofFloored(this.getPos(var86)).down(2)).isReplaceable();
                           if (var60 != 0) {
                              break label186;
                           }

                           if (var103 != 0) {
                              break label184;
                           }
                        }

                        var127 = var61.isFound();
                     }

                     if (var60 != 0) {
                        break label188;
                     }

                     if (var127) {
                        break label187;
                     }
                  }

                  var103 = 0;
               }

               V = (boolean)var103;
            }

            var127 = V;
         }

         if (var127) {
            Vec3d var92 = InterpolationTracker.INSTANCE
               .getPredictedCenter(
                  Globals.getPlayer(), (int)((float)Math.ceil(ServerInfo.getPing$default(ServerInfo.INSTANCE, null, var45, 1, (char)var47, null) / 50.0F))
               );
            RotationManager.INSTANCE.submit(new RotationRequest(Rotation.Companion.to(var92), 150, AutoMend::onPlayerUpdate$lambda$0));
         }

         return;
      }

      V = (boolean)var103;
   }

   private final float S(ItemStack var1) {
      int var2 = ArmorUtil.zkmIntConstant();
      int var10000 = var1.isEmpty();
      if (var2 == 0) {
         if (var10000 != 0) {
            return 100.0F;
         }

         var10000 = var1.getMaxDamage();
      }

      if (var2 == 0) {
         if (var10000 == 0) {
            return 100.0F;
         }

         var10000 = var1.getMaxDamage() - var1.getDamage();
      }

      return (float)var10000 / var1.getMaxDamage() * 100.0F;
   }

   private static final Unit onPlayerUpdate$lambda$0(SlotResult var0) {
      InventoryUtil.INSTANCE.swapSlots(var0.getSlot(), INSTANCE.getXpFactor());
      return Unit.INSTANCE;
   }

   static {
      long var20 = a ^ 91099159584202L;
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[32];
      int var16 = 0;
      String var15 = "Ï\u0082\u0093¨²\u0010¿\u0084@\u008c\u0090Aó þ;z\u0015;\u0083©\rrû`¤\u009eëáÕ\u0090t¤\u009d\u000b\u00078\u0017íñ0Ü®`\u000f×`â\u0084\u001f¿G\u0018\u001a§>\u0089î|ä\t\u0000+ÅÀ÷\u008a÷_\u008d\rÛ2¶çðáëÆö\u0084#A\u0096\u0083Ç%z\u0090 í\u0007½Îdm\u00adÔhþ\u0015<uX©×\u001cäýSMævË6Bñ\u000fjc¸© \u009a\u0004\u0088\u001fÿÛÑ¸*\u001b\u008fð_9>¸)`ûð±\u001d|;\u0013¤e\u008c²g¨ü\u0018xåÌ\u008cKm\u009a&\tù\u0004ëz¡\u008e\u0019\u0090jfëâ{D- \u009bÀ\u0005Ïª\u0018c(ª\u008b\u0016è\u0087ÃÉhV¬r\u009c\u0017\u001dvÌWM6r[Má$ Õ5ÿyC£'\nFh«\r\\¥I6Ia\u000eb\\[\u000b\u0017ð\u0080\u00adÂ'ÚE°\u0018>é\u0014\u0094Mþ\u0099\u0083,â\u0016íX|¸ÂaLé0åzQ¹8\r*Ø\u0002{ZdWÃ¿ \u0088<d\u0014û\u0080\u0018:\u0085GÞ5wÌ:NsË\u009b~¾ÐÑ\u008aT{\u0003ãÅÁnåa\u0018j6û\u0093\u008e\u000b\u0007ªéÃ\u0092\u0018\u0089¤\u007f\u00ad\u009dqGÛ\u0016Dg_Û®û \u0015\u0097\u000eÐ\u0097\u0005\u0080¯\u0018\u0082\u009f\u0085\tD7JvÆÜ¢\u0087}y\u009aÇ\u0087\u009at\bÆ³>? `\u009bv\u0003\u0095\n?\u0017\r]«3ò¬_¦HÑ\u009f>Tß66\u0081\b\u009eüT_;´ \r\u00881©\u000bíp\fr\u009aû\u0006[\u0012Ü[\u009bðV®JÒ\u0087\u0081\u0081Ø¢\\ðK£ú W>\u008bÂ¨¹Ë©_\u0013UN>\u0001#^¸Çú/\u0096$tg½\u00157B¹HrK\u0018ÛÓã\u008b\u0090k\u0012\tÌÿ2vj§åê\u0098«Êºjt\u008a\u00910Â³ Ë\u0006\u0004Ü \u008ao\u001eØ¦kà\u0018òOsCè^ó\u009d\u009bA¬\b(\u0019Ó|\u0099ñR]dºa9Ã¸¿\u008bÒJq\n\u0018\u0016åb\u008cN\u0082\r\u0016\u009b÷T?o_á²Y\u0002\u0018ï¢\"m| Zs\u0015\u0095jîÐ\u0091M3¨<)ì\u0085#.NqE\u001dy\neF.×Í½úïí\u00183ù\u0092\r\u0005E\u0017\u0098©Ó@¡\u001b\u0010×\u00ad\u0013]\u009cN\u0081;À\u00ad ÍU!w}pÎ©ìý.\u001aî\u0016ýí6\u0011àd×ÂÝ\u001d\u0004.õè«\u008eÂ\u0099\u0010löBqTS¯ûçBÆ\u0096é3\u0010q\u0010dëeÈ9£ÅeÉ\u008dîë¿\u0004÷É(à~Ü{Í=Ö5pº\u008eÑ|\u0001\bgÀ[Yr-\u0095Y7\bà®\u0018EoËá¬\u0017\u00adÐ§é\u0087\u0084 A6È£°úÒ\u0081\u0011;\u000b\fF^öÿY}U8RãëV\u009e\u009b\u0081\u0090ùèÒ6\u0010å\u001a_Y\u0000\u001f´þ¤±W\u0017qÚëv(¥¾J¶\u0087FéI'\u008b\u0013Ò\b\u009fC,°hÆÅ\u0093·\u008eDâ\u001bº\u0006c~»\u0013gF`\u0015\u008d\u001b+f(Æ\u00962\u0087Æ9Ý#\u0082\u0082ù\u0000\b\u001e-\u0086êß÷\u001fQ9Ðæ\u0092h\u0081= ':t\u0003³\u0096ä\u0098ÌeÄ(}á\u001f\u0003·IV\u0004%\u00078Bâs\nwFþw}\u0007]N§îÐ)!e²¯9Ûvg\u0098p;V\u0018\u0010}\u009f\u00060-\u0082\\m&=c,f\u0016ÕL \u001d\u0090`É_åòM©/ÈT\u008c\u0091á\u009a\u009e'ÅD\u0001wT¶\u0003\u0082Ø\u0080\u0094¬,\u0094";
      short var17 = 965;
      char var14 = '(';
      int var36 = -1;

      label54:
      while (true) {
         String var37 = var15.substring(++var36, var36 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var37.getBytes("ISO-8859-1"));
            String var48 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var48;
                  if ((var36 += var14) >= var17) {
                     b = var18;
                     c = new String[32];
                     j = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[17];
                     int var3 = 0;
                     String var4 = "\u009c+ÑCÀÑ\t\u0091F\u0086\u0011îïì\u008b\u009b,âÄkýê\u0080Ö\u0084\n\u007fÿ¡ÆÊ2¼ï\u0000&Ç¶ðu½Dº1\u0081\f\u008dÇ_²ñ\u009fpþ/.\u0010újé\f\u0002\u000f\u00ado\u001d¥¶,,Ú\u008fºEÄðú\u0092¸éJ ,\u0083o\u001f>\u008fPp\fÇ\u0099Ù\u0091âó\u009cãÀ<EÑª\u0000r*_\u0010B\u0094ö I\u0001ö\u0017©B\u0015";
                     byte var5 = 120;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var40 = var6;
                        var10001 = var3++;
                        long var52 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var55 = -1;

                        while (true) {
                           long var8 = var52;
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
                           long var57 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var55) {
                              case 0:
                                 var40[var10001] = var57;
                                 if (var2 >= var5) {
                                    f = var6;
                                    h = new Integer[17];
                                    KProperty[] var33 = new KProperty[]{
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "minDurability", "getMinDurability()I", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "maxDurability", "getMaxDurability()I", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "overrideOnLoot", "getOverrideOnLoot()Z", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "xpFactor", "getXpFactor()I", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "pauseGroup", "getPauseGroup()Lsu/catlean/h;", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "ifMove", "getIfMove()Z", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "ifMining", "getIfMining()Z", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "ifEating", "getIfEating()Z", 0)),
                                       Reflection.property1(new PropertyReference1Impl(AutoMend.class, "ifNotInHole", "getIfNotInHole()Z", 0))
                                    };
                                    delegatedProperties = var33;
                                    INSTANCE = new AutoMend();
                                    minDurability$delegate = SettingFactory.intSetting$default(
                                       INSTANCE, "min-durability", 30, new IntRange(0, 100), null, null, 24, null
                                    );
                                    maxDurability$delegate = SettingFactory.intSetting$default(
                                       INSTANCE, "max-durability", 90, new IntRange(0, 100), null, null, 24, null
                                    );
                                    overrideOnLoot$delegate = SettingFactory.booleanSetting$default(INSTANCE, "override-on-loot", true, null, null, 12, null);
                                    xpFactor$delegate = SettingFactory.intSetting$default(INSTANCE, "xp-factor", 1, new IntRange(1, 16), null, null, 24, null);
                                    pauseGroup$delegate = SettingFactory.settingGroup$default(INSTANCE, "pause", AuraTargetsMode.BOOLS, null, 4, null);
                                    ifMove$delegate = SettingFactory.booleanSetting$default(INSTANCE, "if-move", true, INSTANCE.getPauseGroup(), null, 8, null);
                                    ifMining$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "if-mining", false, INSTANCE.getPauseGroup(), null, 8, null
                                    );
                                    ifEating$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "if-eating", false, INSTANCE.getPauseGroup(), null, 8, null
                                    );
                                    ifNotInHole$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "if-not-in-hole", false, INSTANCE.getPauseGroup(), null, 8, null
                                    );
                                    timer = new Timer();
                                    return;
                                 }
                                 break;
                              default:
                                 var40[var10001] = var57;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "åØ§tâ\u0092C»þa®½¸Ð\u008c\u0015";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var46 = var2;
                           var2 += 8;
                           var7 = var4.substring(var46, var2).getBytes("ISO-8859-1");
                           var40 = var6;
                           var10001 = var3++;
                           var52 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var55 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var36);
                  break;
               default:
                  var18[var16++] = var48;
                  if ((var36 += var14) < var17) {
                     var14 = var15.charAt(var36);
                     continue label54;
                  }

                  var15 = "=\u0089G\t\u001fp\u0017\u0098,c#úävA¿UEì©¶\u00adÃHþ\u00942bÉ£f\u0086\u0018\u009dJï8µ\u0081ZmD]Ê~\u0080Âû©íÕYJc$Dy";
                  var17 = 57;
                  var14 = ' ';
                  var36 = -1;
            }

            var37 = var15.substring(++var36, var36 + var14);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 5337;
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
            throw new RuntimeException("su/catlean/eg", var10);
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
         throw new RuntimeException("su/catlean/eg" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 12552;
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
         Object[] var9 = (Object[])j.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               j.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/eg", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         h[var3] = var15;
      }

      return h[var3];
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
         throw new RuntimeException("su/catlean/eg" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
