package su.catlean.module.misc;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.SelectMerchantTradeC2SPacket;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.basic.GuiMove;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ItemListSetting;
import su.catlean.setting.ItemListValue;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingParent;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.Rotation$Companion;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class AutoTrader extends Module {
   @NotNull
   public static final AutoTrader INSTANCE;
   static final KProperty[] delegatedProperties;
   @NotNull
   private static final BooleanSetting buy$delegate;
   @NotNull
   private static final ItemListSetting buyItem$delegate;
   @NotNull
   private static final BooleanSetting sell$delegate;
   @NotNull
   private static final ItemListSetting sellItem$delegate;
   @NotNull
   private static final IntSetting interactDelay$delegate;
   @NotNull
   private static final IntSetting tradeDelay$delegate;
   private static int B;
   private static int o;
   private static int t;
   @NotNull
   private static final Map i;
   private static final long a = ZkmKeys.a(-138202280622548393L, -2141329995444132828L, MethodHandles.lookup().lookupClass()).a(46941064862638L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long[] e;
   private static final Integer[] f;
   private static final Map g;

   private AutoTrader() {
      super("auto-trader", Categories.getMisc(), null, null);
   }

   private final boolean getBuy() {
      return (Boolean)buy$delegate.getValue(this, delegatedProperties[0]);
   }

   private final ItemListValue getBuyItem() {
      return (ItemListValue)buyItem$delegate.getValue(this, delegatedProperties[1]);
   }

   private final void setBuyItem(ItemListValue var1) {
      buyItem$delegate.setValue(this, delegatedProperties[1], var1);
   }

   private final boolean getSell() {
      return (Boolean)sell$delegate.getValue(this, delegatedProperties[2]);
   }

   private final ItemListValue getSellItem() {
      return (ItemListValue)sellItem$delegate.getValue(this, delegatedProperties[3]);
   }

   private final void setSellItem(ItemListValue var1) {
      sellItem$delegate.setValue(this, delegatedProperties[3], var1);
   }

   private final int getInteractDelay() {
      return ((Number)interactDelay$delegate.getValue(this, delegatedProperties[4])).intValue();
   }

   private final void setInteractDelay(int var1) {
      interactDelay$delegate.setValue(this, delegatedProperties[4], var1);
   }

   private final int getTradeDelay() {
      return ((Number)tradeDelay$delegate.getValue(this, delegatedProperties[5])).intValue();
   }

   private final void setTradeDelay(int var1) {
      tradeDelay$delegate.setValue(this, delegatedProperties[5], var1);
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   @Flow
   private final void onPlayerUpdate(PlayerUpdateEvent var1) {
      boolean var25 = HudEditorScreen.p();
      int var10000 = B;
      if (var25) {
         if (B > 0) {
            int var26 = B;
            B = var26 + -1;
         }

         var10000 = o;
      }

      if (var25) {
         if (var10000 > 0) {
            int var37 = o;
            o = var37 + -1;
            return;
         }

         i.entrySet().removeIf(AutoTrader::invokePredicate);
         var10000 = Globals.getMc().currentScreen instanceof MerchantScreen;
      }

      if (var25) {
         if (var10000 != 0) {
            Screen var46 = Globals.getMc().currentScreen;
            Intrinsics.checkNotNull(var46, "null cannot be cast to non-null type net.minecraft.client.gui.screens.inventory.MerchantScreen");
            MerchantScreen var35 = (MerchantScreen)var46;
            ScreenHandler var47 = var35.getScreenHandler();
            Intrinsics.checkNotNullExpressionValue(var47, "getMenu(...)");
            MerchantScreenHandler var27 = (MerchantScreenHandler)var47;
            TradeOfferList var48 = var27.getRecipes();
            Intrinsics.checkNotNullExpressionValue(var48, "getOffers(...)");
            TradeOfferList var28 = var48;
            int var29 = 0;
            int var30 = var28.size();

            while (true) {
               if (var29 < var30) {
                  Object var49 = var28.get(var29);
                  Intrinsics.checkNotNullExpressionValue(var49, "get(...)");
                  TradeOffer var31 = (TradeOffer)var49;
                  if (!var25) {
                     break;
                  }

                  boolean var50 = this.u(var31);
                  if (var25) {
                     if (var50) {
                        var27.switchTo(var29);
                        var27.setRecipeIndex(var29);
                        PacketUtil.sendPacket(new SelectMerchantTradeC2SPacket(var29));
                        GuiMove.INSTANCE.setMacroActive(true);
                        InteractionUtil.clickSlot$default(2, 0, SlotActionType.QUICK_MOVE, false, 8, null);
                        GuiMove.INSTANCE.setMacroActive(false);
                        o = this.getTradeDelay();
                        return;
                     }

                     var50 = var27.getSlot(0).getStack().isEmpty();
                  }

                  label176: {
                     if (var25) {
                        if (!var50) {
                           GuiMove.INSTANCE.setMacroActive(true);
                           InteractionUtil.clickSlot$default(0, 0, SlotActionType.QUICK_MOVE, false, 8, null);
                           GuiMove.INSTANCE.setMacroActive(false);
                           o = this.getTradeDelay();
                           return;
                        }

                        ItemStack var51 = var27.getSlot(1).getStack();
                        if (!var25) {
                           break label176;
                        }

                        var50 = var51.isEmpty();
                     }

                     if (!var50) {
                        GuiMove.INSTANCE.setMacroActive(true);
                        InteractionUtil.clickSlot$default(1, 0, SlotActionType.QUICK_MOVE, false, 8, null);
                        GuiMove.INSTANCE.setMacroActive(false);
                        o = this.getTradeDelay();
                        return;
                     }

                     i.put(t, Globals.getPlayer().age);
                  }

                  var29++;
                  if (var25) {
                     continue;
                  }
               }

               Globals.getPlayer().closeHandledScreen();
               break;
            }

            if (var25) {
               return;
            }
         }

         var10000 = B;
      }

      if (var25) {
         if (var10000 > 0) {
            return;
         }

         var10000 = Globals.getMc().currentScreen instanceof ClickGuiScreen;
      }

      if (var10000 == 0) {
         Iterable var52 = Globals.getWorld().getEntities();
         Intrinsics.checkNotNullExpressionValue(var52, "entitiesForRendering(...)");
         Iterable var38 = var52;
         var var40 = new ArrayList();

         label134: {
            for (Object var33 : var38) {
               Entity var34 = (Entity)var33;
               var10000 = var34 instanceof VillagerEntity;
               if (!var25) {
                  break label134;
               }

               label130: {
                  label180: {
                     if (var25) {
                        if (var10000 == 0) {
                           break label180;
                        }

                        double var59;
                        var10000 = (var59 = Globals.getPlayer().squaredDistanceTo(var34) - 16.0) == 0.0 ? 0 : (var59 < 0.0 ? -1 : 1);
                     }

                     if (var25) {
                        if (var10000 >= 0) {
                           break label180;
                        }

                        var10000 = i.containsKey(((VillagerEntity)var34).getId());
                     }

                     if (!var25) {
                        break label130;
                     }

                     if (var10000 == 0) {
                        var10000 = 1;
                        break label130;
                     }
                  }

                  var10000 = 0;
               }

               if (var10000 != 0) {
                  var40.add(var33);
                  if (!var25) {
                     break;
                  }
               }
            }

            var38 = (Iterable & List)var40;
            boolean var54 = false;
         }

         label181: {
            Iterator var39;
            label182: {
               var39 = var38.iterator();
               boolean var55 = var39.hasNext();
               if (var25) {
                  if (!var55) {
                     var57 = null;
                     break label181;
                  }

                  var40 = var39.next();
                  var56 = var39;
                  if (!var25) {
                     break label182;
                  }

                  var55 = var39.hasNext();
               }

               if (!var55) {
                  var57 = var40;
                  break label181;
               }

               var56 = var40;
            }

            Entity var41 = (Entity)var56;
            float var42 = var41.distanceTo(Globals.getPlayer());

            label98:
            while (true) {
               Object var43 = var39.next();
               Entity var44 = (Entity)var43;
               float var45 = var44.distanceTo(Globals.getPlayer());
               if (Float.compare(var42, var45) > 0) {
                  var40 = var43;
                  var42 = var45;
               }

               while (!var39.hasNext()) {
                  if (var25) {
                     var57 = var40;
                     break label98;
                  }

                  var42 = var45;
               }
            }
         }

         Entity var36 = (Entity)var57;
         if (var36 != null) {
            RotationManager var58 = RotationManager.INSTANCE;
            Rotation$Companion var10003 = Rotation.Companion;
            Vec3d var10004 = var36.getEyePos().add(Math.random() * 0.2, 0.0, Math.random() * 0.2);
            Intrinsics.checkNotNullExpressionValue(var10004, "add(...)");
            var58.submit(new RotationRequest(var10003.to(var10004), AutoTrader::onPlayerUpdate$lambda$2, null));
         }
      }
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   private final boolean u(TradeOffer var1) {
      int var10000 = HudEditorScreen.p();
      Iterator var21 = this.getBuyItem().getItems().iterator();
      boolean var20 = (boolean)var10000;

      while (true) {
         byte var23;
         label226: {
            if (var21.hasNext()) {
               Item var22 = (Item)var21.next();
               var23 = Intrinsics.areEqual(var1.getSellItem().getItem(), var22);
               var10000 = var1.getDisplayedFirstBuyItem().getCount();
               if (!var20) {
                  break label226;
               }

               InventoryUtil var10001 = InventoryUtil.INSTANCE;
               Item var10002 = var1.getDisplayedFirstBuyItem().getItem();
               Intrinsics.checkNotNullExpressionValue(var10002, "getItem(...)");
               Object var16 = null;
               boolean var18 = false;
               Item var19 = var10002;
               int var45 = InventoryUtil.findItemSlot$default(var10001, var19, var18, var16);
               if (var20) {
                  var10000 = var10000 <= var45 ? 1 : 0;
                  break label226;
               }

               var10000 = var10000 <= var45 ? 1 : 0;
            } else {
               var21 = this.getSellItem().getItems().iterator();
               if (!var21.hasNext()) {
                  var10000 = 0;
                  break;
               }

               Item var32 = (Item)var21.next();
               var23 = Intrinsics.areEqual(var1.getDisplayedFirstBuyItem().getItem(), var32);
               var10000 = var1.getDisplayedFirstBuyItem().getCount();
               if (!var20) {
                  break;
               }

               if (var20) {
                  InventoryUtil var46 = InventoryUtil.INSTANCE;
                  Item var48 = var1.getDisplayedFirstBuyItem().getItem();
                  Intrinsics.checkNotNullExpressionValue(var48, "getItem(...)");
                  Object var26 = null;
                  boolean var28 = false;
                  Item var30 = var48;
                  var10000 = var10000 <= InventoryUtil.findItemSlot$default(var46, var30, var28, var26) ? 1 : 0;
               }
            }

            while (true) {
               int var34 = var10000;
               var10000 = var23;
               if (var20) {
                  label205:
                  if (var23 != 0) {
                     var10000 = var1.isDisabled();
                     if (var20) {
                        if (var10000 == 0) {
                           var10000 = this.getSell();
                           if (var20) {
                              var10000 = var10000 != 0 ? 1 : 0;
                           }
                        } else {
                           var10000 = 0;
                        }
                     } else if (var20) {
                        var10000 = var10000 != 0 ? 1 : 0;
                     }

                     int var35 = var10000;
                     var10000 = var35;
                     label115:
                     if (var20) {
                        if (var35 != 0) {
                           var10000 = var34;
                           if (!var20) {
                              break label115;
                           }

                           if (var34 == 0 && var20) {
                              break label205;
                           }
                        }

                        var10000 = var35;
                     }

                     if (!var20) {
                        return (boolean)var10000;
                     }

                     if (var10000 != 0) {
                        return (boolean)1;
                     }
                  } else {
                     label238: {
                        byte var36 = 0;
                        var10000 = var36;
                        label143:
                        if (var20) {
                           if (var36 != 0) {
                              var10000 = var34;
                              if (!var20) {
                                 break label143;
                              }

                              if (var34 == 0 && var20) {
                                 break label238;
                              }
                           }

                           var10000 = var36;
                        }

                        if (!var20) {
                           return (boolean)var10000;
                        }

                        if (var10000 != 0) {
                           return (boolean)1;
                        }
                     }
                  }
               } else {
                  label239: {
                     if (var20) {
                        if (var23 == 0) {
                           var10000 = this.getSell();
                           if (var20) {
                              var10000 = var10000 != 0 ? 1 : 0;
                           }
                        } else {
                           var10000 = 0;
                        }
                     } else if (var20) {
                        var10000 = var23 != 0 ? 1 : 0;
                     }

                     int var37 = var10000;
                     var10000 = var37;
                     label185:
                     if (var20) {
                        if (var37 != 0) {
                           var10000 = var34;
                           if (!var20) {
                              break label185;
                           }

                           if (var34 == 0 && var20) {
                              break label239;
                           }
                        }

                        var10000 = var37;
                     }

                     if (!var20) {
                        return (boolean)var10000;
                     }

                     if (var10000 != 0) {
                        return (boolean)1;
                     }
                  }
               }

               if (!var21.hasNext()) {
                  return (boolean)0;
               }

               Item var33 = (Item)var21.next();
               var23 = Intrinsics.areEqual(var1.getDisplayedFirstBuyItem().getItem(), var33);
               var10000 = var1.getDisplayedFirstBuyItem().getCount();
               if (!var20) {
                  return (boolean)var10000;
               }

               if (var20) {
                  InventoryUtil var47 = InventoryUtil.INSTANCE;
                  Item var49 = var1.getDisplayedFirstBuyItem().getItem();
                  Intrinsics.checkNotNullExpressionValue(var49, "getItem(...)");
                  Object var27 = null;
                  boolean var29 = false;
                  Item var31 = var49;
                  var10000 = var10000 <= InventoryUtil.findItemSlot$default(var47, var31, var29, var27) ? 1 : 0;
               }
            }
         }

         int var24;
         label97: {
            label240: {
               var24 = var10000;
               var40 = var23;
               if (var20) {
                  if (var23 == 0) {
                     break label240;
                  }

                  var40 = var1.isDisabled();
               }

               if (var20) {
                  if (var40 != 0) {
                     break label240;
                  }

                  var40 = this.getBuy();
               }

               if (!var20) {
                  break label97;
               }

               if (var40 != 0) {
                  var40 = 1;
                  break label97;
               }
            }

            var40 = 0;
         }

         byte var25 = var40;
         var10000 = var25;
         label81:
         if (var20) {
            if (var25 != 0) {
               var10000 = var24;
               if (!var20) {
                  break label81;
               }

               if (var24 == 0 && var20) {
                  continue;
               }
            }

            var10000 = var25;
         }

         if (var20) {
            if (var10000 == 0) {
               continue;
            }

            var10000 = 1;
         }

         return (boolean)var10000;
      }

      return (boolean)var10000;
   }

   private static final boolean onPlayerUpdate$lambda$0(Entry var0) {
      Intrinsics.checkNotNullParameter(var0, "<destruct>");
      int var10000 = HudEditorScreen.p();
      int var6 = ((Number)var0.getValue()).intValue();
      boolean var5 = (boolean)var10000;
      var10000 = Globals.getPlayer().age - var6;
      if (var5) {
         var10000 = var10000 > 160 ? 1 : 0;
      }

      return (boolean)var10000;
   }

   private static final boolean invokePredicate(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }

   private static final Unit onPlayerUpdate$lambda$2(Entity var0) {
      Globals.getInteractionManager().interactEntity(Globals.getPlayer(), var0, Hand.MAIN_HAND);
      t = var0.getId();
      B = INSTANCE.getInteractDelay();
      return Unit.INSTANCE;
   }

   static {
      long var20 = a ^ 140279868252631L;
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[28];
      int var16 = 0;
      String var15 = "Ê1á\u000eýY\u0091\u008a×}[c\u0004\u0094È¹\u009f\u001af\u0094\u000e\u0007\u0094ð\u0010eÙ[¶Ï\u0019#$qeY\u0000\u0010\u0097\u0090o\u0010ÜÕ\u001e\u0091ÆöÙäY{ÙÖB¹î4\u0018Å/\bb@h²\u0085aÝ»\u009eIXÛ\u0012\u0085Ó\\¹½\u0005\u0081. üu\u009b¬3\u008d`\u0097\u0099\u0014¬.ÈL&\u001a\u008c$¾@Ã}ÞëKú¡\u0080û¶\u001b²\u0018oünÉgÌ\u0001áöT\u0088]mà\u009d¼/\u009c\u008dp\u001bZ\u007f+\u0018³½È\u008ct\u009fãj9\u0097±u\u009d8\u0005\u0082\u008f¼gØ>\u007f\u0088\u0011 Ágóç\u0000þ\u0010Ý\u0081\u001f2Çª'\u000b(\u0004\u0088í!\u0010±mâdëáàHò<ðEõ\u0019 É?Ù\u001bElÑ18ó9$\u0004È];Awol¯âw5aÿ\u0081ë\u009fý`\u0092¦ \u0000ÕÇ¤\u001c\u0090ÀO§Hª¸æÚúÒWî\u0013\u0003nZØ\u0012¦R<\u009c=1p\u0090O]\u001f4ë°\u0086áu\u0090À\u008e\u0081©¤M4î»hä£î°\u0090\u009a#<èÌp9xað\nYyf¢Â\u0081ÏÙ\u0001¡üJÙ\u0015õVé\u009aò-7\u00028PïEë³\u0011êþý\u009aK}éîv\\\u0083×Å\u008d\r¨\f\u0099Ä½B¼rvP\u0018´Ò¯x¾e=X\u0005\u0002<\u0087\u00028¢DÀ8eµ:ó2Ø BÄm¸mh\rÖÖ\u0091\u009d®\u000bUñdð»Êç)½ÄÖêü\u009d\u001d¨³\u0010Ý ³\u008b\u001dÃ´hËx«ó\u000e\u0094í\u0019³BM$\u009e\b\u009aÛ÷\u008cf÷H\u0013íAXY HcÖ\u001c&gØ\u0097©ºÑÍ\u0000TÍÚø¤UÖÁ\u0096<5uíß\u008b\u0084\u008b\u001dÙ\u0018\u0015\u008a¥G\u001e3\u0093ô\u009dr¯\u008eïûGÅo\u001ayaJgO\u008d\u0018\u009by³ë>ïc¾\u001fí\f\u001cÈe0\u009fq:\u001c¹WÛÒ\u0089 \u000e\u0014ç\u00996G\u0085P\u0094\u0080±\u0099\u0016\u001c\u0097\u009a\u0000K\u0091¯$g\u008d^\u000eÿ>cp7ùø\u0018a\u008f\u0081\\æÖsçrúL{%/r\u0011ï\"\u0007Î\u0093EÊN\u0018C\n\u0017\u009d\u0087 Æ\u0012eõ¦\r \u0000\u001e\u0083\u0097\u0091a'Ï¿¢\u001c\u0010L´_\u0014·¨0\f£\r\u00188åt\u00059 \u009c\u0080Ò5a\t\u008d#V\u0083ÔàzÐÒã\u0019¤)ì÷¾é7ÞöVx\u00916\u001b}\u0018d;åÙ\n\u009aÆ?\u0004éÈ\u0090X\u0013ëc\u00043¸¨_ \u008b\u0016(¾ý\u0097W\u0090qÏ¬ùNîtYw¸\u000fªxfd\"\u000bseû\u001d&ß§~¿EØ þ\u0018FAø\u009c \u009a\u0088\u0019±óÒ[\u009eò6ÒM S\u008c\u001e\u0014cC\u008b.kg\u000b8A\u0003\u0014ø*xß QKì}\r\u009e\u001e\u0005Ö>ÛH¾Å\u0086mgnp&\u0082K\u0004¿\u0098½ðÈj\u0001ÔÒ0B±2¼AAíÍf4¶0&` \u009f\u001a\u0007ª;~QÖäâü\u0005\u009f\u007f-\u007fÇ½Î5cm\u0002tráÍþ\u001c\u0091í2C\u0010\u0086r0ý\u0084ÁÞ\u0011°\u0091ìõD¶Ï¿(º\u0090\u008eêp\u000bE!¼\u0088Cé\u0080ÐnÏi@:à\u001e\u000e\u008ds'M\u001a¡G\u0095\u0019é\u0010sú2j¨2ñ";
      short var17 = 905;
      char var14 = 24;
      int var51 = -1;

      label54:
      while (true) {
         String var52 = var15.substring(++var51, var51 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var52.getBytes("ISO-8859-1"));
            String var65 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var65;
                  if ((var51 += var14) >= var17) {
                     b = var18;
                     c = new String[28];
                     g = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[10];
                     int var3 = 0;
                     String var4 = "Ê¢5÷¶\u0094obE>\n:ô\u007fëå\f\u008eiúJçá\u008fIKäÛÉòö\u0089\u0081\u001d¼®ß¨`\u0016\u0006f\u009cÁS°\\CãàA¸¾\"\u0018ñ\u0013Äxø\u0090\u0004+\u0012";
                     byte var5 = 64;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var55 = var6;
                        var10001 = var3++;
                        long var69 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var74 = -1;

                        while (true) {
                           long var8 = var69;
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
                           long var76 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var74) {
                              case 0:
                                 var55[var10001] = var76;
                                 if (var2 >= var5) {
                                    e = var6;
                                    f = new Integer[10];
                                    KProperty[] var32 = new KProperty[]{
                                       Reflection.property1(new PropertyReference1Impl(AutoTrader.class, "buy", "getBuy()Z", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(AutoTrader.class, "buyItem", "getBuyItem()Lsu/catlean/nv;", 0)
                                       ),
                                       Reflection.property1(new PropertyReference1Impl(AutoTrader.class, "sell", "getSell()Z", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(AutoTrader.class, "sellItem", "getSellItem()Lsu/catlean/nv;", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(AutoTrader.class, "interactDelay", "getInteractDelay()I", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoTrader.class, "tradeDelay", "getTradeDelay()I", 0))
                                    };
                                    delegatedProperties = var32;
                                    INSTANCE = new AutoTrader();
                                    buy$delegate = SettingFactory.booleanSetting$default(INSTANCE, "buy", true, null, null, 12, null);
                                    SettingContainer var56 = INSTANCE;
                                    ItemListValue var71 = new ItemListValue(null, null);
                                    Object var33 = null;
                                    byte var34 = 12;
                                    Object var35 = null;
                                    Object var36 = null;
                                    ItemListValue var37 = var71;
                                    String var38 = "buy-item";
                                    SettingContainer var39 = var56;
                                    buyItem$delegate = SettingFactory.itemListSetting$default(
                                       var39, var38, var37, (SettingParent)var36, (Function0)var35, var34, var33
                                    );
                                    sell$delegate = SettingFactory.booleanSetting$default(INSTANCE, "sell", false, null, null, 12, null);
                                    SettingContainer var57 = INSTANCE;
                                    ItemListValue var72 = new ItemListValue(null, null);
                                    Object var42 = null;
                                    byte var43 = 12;
                                    Object var44 = null;
                                    Object var45 = null;
                                    ItemListValue var46 = var72;
                                    String var47 = "sell-item";
                                    SettingContainer var48 = var57;
                                    sellItem$delegate = SettingFactory.itemListSetting$default(
                                       var48, var47, var46, (SettingParent)var45, (Function0)var44, var43, var42
                                    );
                                    interactDelay$delegate = SettingFactory.intSetting$default(
                                       INSTANCE, "open-delay", 12, new IntRange(0, 40), null, null, 24, null
                                    );
                                    tradeDelay$delegate = SettingFactory.intSetting$default(
                                       INSTANCE, "trade-delay", 3, new IntRange(0, 40), null, null, 24, null
                                    );
                                    i = new LinkedHashMap();
                                    return;
                                 }
                                 break;
                              default:
                                 var55[var10001] = var76;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "»¼ bu,!\u009faÞårQ·&\r";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var63 = var2;
                           var2 += 8;
                           var7 = var4.substring(var63, var2).getBytes("ISO-8859-1");
                           var55 = var6;
                           var10001 = var3++;
                           var69 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var74 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var51);
                  break;
               default:
                  var18[var16++] = var65;
                  if ((var51 += var14) < var17) {
                     var14 = var15.charAt(var51);
                     continue label54;
                  }

                  var15 = "ôá2¼\u007fx8Zâ>\u0095Á¾Ð\u0011\u008fLF³Wï×¯Ýë\u0086ßS¢\u0087\u0099ÒqZ\u008d\u0011³\u0002¢ö«\u0011\u0005<,³êÖ\u008a\u0091½|¨\u0000Z\u0092\u0010\u009dN9ÅèÝÍAÔâ\u0088%W\u0002¬\u0094";
                  var17 = 73;
                  var14 = '8';
                  var51 = -1;
            }

            var52 = var15.substring(++var51, var51 + var14);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 24891;
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
            throw new RuntimeException("su/catlean/qn", var10);
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
         throw new RuntimeException("su/catlean/qn" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 3227;
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
            throw new RuntimeException("su/catlean/qn", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         f[var3] = var15;
      }

      return f[var3];
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
         throw new RuntimeException("su/catlean/qn" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
