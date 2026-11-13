package su.catlean.module.attack;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InterpolationTracker;
import su.catlean.util.combat.TargetUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BowAimbot extends Module {
   @NotNull
   public static final BowAimbot INSTANCE;
   static final KProperty[] delegatedProperties;
   @NotNull
   private static final BooleanSetting silent$delegate;
   @NotNull
   private static final ModeSetting predict$delegate;
   @NotNull
   private static final IntSetting predictTicks$delegate;
   @NotNull
   private static final BooleanSetting ignoreNaked$delegate;
   @NotNull
   private static final BooleanSetting ignoreCreative$delegate;
   private static final long a = ZkmKeys.a(717985874428662954L, 7631229677846113430L, MethodHandles.lookup().lookupClass()).a(157918839428978L);
   private static final String[] c;
   private static final String[] d;
   private static final Map e = new HashMap(13);
   private static final long[] f;
   private static final Integer[] g;
   private static final Map h;

   private BowAimbot() {
      super("bow-aimbot", Categories.getAttack(), null, null);
   }

   private final boolean getSilent() {
      return (Boolean)silent$delegate.getValue(this, delegatedProperties[0]);
   }

   private final void setSilent(boolean var1) {
      silent$delegate.setValue(this, delegatedProperties[0], var1);
   }

   private final BowAimbotPredictMode getPredict() {
      return (BowAimbotPredictMode)predict$delegate.getValue(this, delegatedProperties[1]);
   }

   private final void setPredict(BowAimbotPredictMode var1) {
      predict$delegate.setValue(this, delegatedProperties[1], var1);
   }

   private final int getPredictTicks() {
      return ((Number)predictTicks$delegate.getValue(this, delegatedProperties[2])).intValue();
   }

   private final void setPredictTicks(int var1) {
      predictTicks$delegate.setValue(this, delegatedProperties[2], var1);
   }

   private final boolean getIgnoreNaked() {
      return (Boolean)ignoreNaked$delegate.getValue(this, delegatedProperties[3]);
   }

   private final void setIgnoreNaked(boolean var1) {
      ignoreNaked$delegate.setValue(this, delegatedProperties[3], var1);
   }

   private final boolean getIgnoreCreative() {
      return (Boolean)ignoreCreative$delegate.getValue(this, delegatedProperties[4]);
   }

   private final void setIgnoreCreative(boolean var1) {
      ignoreCreative$delegate.setValue(this, delegatedProperties[4], var1);
   }

   @Flow
   private final void onSendPacket(SendPacket var1) {
      System.out.println(var1.getPacket().getClass().getSimpleName());
   }

   @Flow
   private final void onReceivePacket(ReceivePacket var1) {
      System.out.println(var1.getPacket().getClass().getSimpleName());
   }

   @Flow
   private final void onPlayerUpdate(PlayerUpdateEvent var1) {
      long var2 = a ^ 106909107873817L;
      long var19 = var2 ^ 6271116945127L;
      PlayerEntity var10000 = TargetUtil.INSTANCE.findTarget(var19, 256.0F, AutoCrystalTargetSortMode.FOV, this.getIgnoreNaked(), this.getIgnoreCreative());
      if (var10000 != null) {
         PlayerEntity var24 = var10000;

         Vec3d var25 = switch (BowAimbotPredictMode$WhenMappings.U[this.getPredict().ordinal()]) {
            case 1 -> {
               InterpolationTracker var42 = InterpolationTracker.INSTANCE;
               int var26 = this.getPredictTicks();
               yield var42.getPredictedCenter(var24, var26);
            }
            case 2 -> {
               InterpolationTracker var41 = InterpolationTracker.INSTANCE;
               int var30 = this.getPredictTicks();
               yield var41.getPredictedCenter(var24, var30);
            }
            case 3 -> this.getPos(var24);
            default -> throw new NoWhenBranchMatchedException();
         };
         Vec3d var43 = var25.add(0.0, 0.9, 0.0);
         Intrinsics.checkNotNullExpressionValue(var43, "add(...)");
         var25 = var43;
         RotationManager var44 = RotationManager.INSTANCE;
         Rotation var10001 = Rotation.Companion.to(var25);
         Object var34 = null;
         Rotation var37 = var10001;
         RotationManager var38 = var44;
         RotationManager.rotate$default(var38, var37, var34);
      }
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   public final void z(char a, @NotNull Item item, int a, float yaw, short a) {
      long var6;
      String[] var19;
      double var20;
      double var22;
      double var24;
      double var27;
      double var29;
      double var31;
      float var69;
      label296: {
         var6 = ((long)ax << 48 | (long)axxx << 32 >>> 16 | (long)axx << 48 >>> 48) ^ a;
         String[] var10000 = ZkmReferences.zkmBootstrap<"Å">(8276488670752623810L, var6);
         Intrinsics.checkNotNullParameter(item, zkmBootstrap<"p">(20103, 5475388987788197828L ^ var6));
         var19 = var10000;
         var20 = Globals.getPlayer().getX();
         var22 = Globals.getPlayer().getY() + Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose());
         var24 = Globals.getPlayer().getZ();
         float var26 = RenderUtil.INSTANCE.interpolate(Globals.getPlayer().lastPitch, Globals.getPlayer().getPitch());
         var27 = -((float)Math.sin(yaw / 180.0F * (float) Math.PI)) * (float)Math.cos(var26 / 180.0F * (float) Math.PI);
         var29 = -((float)Math.sin(var26 / 180.0F * 3.141593F));
         var31 = (float)Math.cos(yaw / 180.0F * (float) Math.PI) * (float)Math.cos(var26 / 180.0F * (float) Math.PI);
         float var33 = RenderUtil.INSTANCE
               .interpolate(Math.max(0.0F, Globals.getPlayer().getItemUseTime() - 1.0F), (float)Globals.getPlayer().getItemUseTime())
            / 20.0F;
         var69 = (var33 * var33 + var33 * 2.0F) / 3.0F;
         float var74 = var69;
         if (var19 == null) {
            if (!(var69 > 1.0F)) {
               float var86;
               int var75 = (var86 = var69 - 0.0F) == 0.0F ? 0 : (var86 < 0.0F ? -1 : 1);
               if (var19 == null) {
                  var75 = !var75;
               }

               if (!var75) {
                  break label296;
               }
            }

            var74 = 1.0F;
         }

         var69 = var74;
      }

      float var34 = MathHelper.sqrt((float)(var27 * var27 + var29 * var29 + var31 * var31));
      var27 /= var34;
      var29 /= var34;
      var31 /= var34;
      float var35 = var69 * 2.0F;
      var27 *= var35;
      var29 *= var35;
      var31 *= var35;
      var27 += Globals.getPlayer().getVelocity().x;
      var29 += Globals.getPlayer().getVelocity().y;
      var31 += Globals.getPlayer().getVelocity().z;
      Vec3d var36 = null;
      int var37 = 0;

      label288:
      while (true) {
         int var76 = var37;

         label286:
         while (true) {
            if (var76 < zkmBootstrap2<"y">(22733, 4398009064654368275L ^ var6)) {
               var36 = new Vec3d(var20, var22, var24);
               var20 += var27;
               var22 += var29;
               var24 += var31;
               var27 *= 0.99;
               var29 *= 0.99;
               var31 *= 0.99;
               var29 -= 0.05;
            } else if (axx >= 0) {
               return;
            }

            label284:
            do {
               Vec3d var38 = new Vec3d(var20, var22, var24);
               BlockHitResult var77 = Globals.getWorld().raycast(new RaycastContext(var36, var38, ShapeType.COLLIDER, FluidHandling.NONE, Globals.getPlayer()));
               Intrinsics.checkNotNullExpressionValue(var77, zkmBootstrap<"p">(28592, 699184387679608562L ^ var6));
               BlockHitResult var39 = var77;
               Iterable var78;
               if (axx >= 0 && var39.getType() != Type.BLOCK) {
                  var78 = Globals.getWorld().getEntities();
                  Intrinsics.checkNotNullExpressionValue(var78, zkmBootstrap<"p">(22128, 5942305084731778859L ^ var6));
               } else {
                  if (var19 == null) {
                     continue;
                  }

                  ZkmReferences.zkmBootstrap<"Å">(new Module[2], 8219285622870407213L, var6);
                  var78 = Globals.getWorld().getEntities();
                  Intrinsics.checkNotNullExpressionValue(var78, zkmBootstrap<"p">(22128, 5942305084731778859L ^ var6));
               }

               Iterable var42;
               byte var70;
               do {
                  Iterable var41 = var78;
                  var var44 = new ArrayList();

                  label274:
                  for (Object var47 : var41) {
                     Entity var48 = (Entity)var47;
                     var76 = var48 instanceof ProjectileEntity;
                     if (var19 != null) {
                        continue label286;
                     }

                     while (true) {
                        String[] var10001 = var19;
                        if (ax >= 0) {
                           label302: {
                              if (axxx > 0) {
                                 if (var19 == null) {
                                    if (var76 != 0) {
                                       var76 = 0;
                                       break label302;
                                    }

                                    var76 = Intrinsics.areEqual(var48, Globals.getPlayer());
                                    var10001 = var19;
                                 } else {
                                    var10001 = var19;
                                 }
                              }

                              if (ax >= 0) {
                                 if (var10001 == null) {
                                    if (var76 != 0) {
                                       var76 = 0;
                                       break label302;
                                    }

                                    double var87;
                                    var76 = (var87 = var48.squaredDistanceTo(var38) - 25.0) == 0.0 ? 0 : (var87 < 0.0 ? -1 : 1);
                                    var10001 = var19;
                                 } else {
                                    var10001 = var19;
                                 }
                              }

                              if (var10001 == null) {
                                 if (var76 >= 0) {
                                    double var88;
                                    var76 = (var88 = var48.squaredDistanceTo(var36) - 25.0) == 0.0 ? 0 : (var88 < 0.0 ? -1 : 1);
                                    if (var19 == null) {
                                       var76 = var76 < 0 ? 1 : 0;
                                    }
                                 } else {
                                    var76 = 1;
                                 }
                              }
                           }

                           if (var76 != 0) {
                              var44.add(var47);
                              if (var19 != null) {
                                 break label274;
                              }
                           }
                           break;
                        }

                        if (var19 != null) {
                           continue label286;
                        }
                     }
                  }

                  List var40 = var44;
                  var70 = 0;
                  var42 = var40;
                  var78 = var42;
               } while (ax < 0);

               Iterator var71 = var42.iterator();

               String[] var84;
               while (true) {
                  if (var71.hasNext()) {
                     Object var45 = var71.next();
                     Entity var72 = (Entity)var45;
                     Box var79 = var72.getBoundingBox();
                     Intrinsics.checkNotNullExpressionValue(var79, zkmBootstrap<"p">(3598, 7221586305617674068L ^ var6));
                     Box var73 = var79;
                     Optional var49 = var73.raycast(var36, var38);
                     String[] var80 = var19;
                     if (axxx >= 0) {
                        if (var19 == null) {
                           var76 = var49.isPresent();
                           var84 = var19;
                           if (axxx < 0) {
                              break;
                           }

                           if (var19 != null) {
                              var84 = var19;
                              break;
                           }

                           if (var76 != 0) {
                              if (var19 == null) {
                                 if (var70 == 0) {
                                    RenderState var82 = RenderState.INSTANCE;
                                    Object var10002 = var49.get();
                                    Intrinsics.checkNotNullExpressionValue(var10002, zkmBootstrap<"p">(8516, 8358974967231418395L ^ var6));
                                    var10002 = (Vec3d)var10002;
                                    Color var10003 = Theme.INSTANCE.getGradientColor(var37);
                                    int var10006 = zkmBootstrap2<"y">(32330, 5687245257953855634L ^ var6);
                                    Object var50 = null;
                                    int var51 = var10006;
                                    boolean var54 = false;
                                    Object var55 = null;
                                    Color var56 = var10003;
                                    Vec3d var57 = (Vec3d)var10002;
                                    RenderState.addLine$default(var82, var36, var57, var56, (Color)var55, var54, var51, var50);
                                    var70 = 1;
                                 }
                              } else {
                                 var70 = var70;
                              }
                           }

                           var80 = var19;
                        } else {
                           var80 = var19;
                        }
                     }

                     if (var80 == null) {
                        continue;
                     }
                  }

                  if (axx <= 0) {
                     if (var19 == null) {
                        continue label288;
                     }
                     continue label284;
                  }

                  var76 = var70;
                  var84 = var19;
                  break;
               }

               if (ax >= 0) {
                  if (var84 == null) {
                     if (var76 != 0) {
                        if (var19 == null) {
                           continue;
                        }

                        double var89;
                        var76 = (var89 = var22 - -65.0) == 0.0 ? 0 : (var89 < 0.0 ? -1 : 1);
                        var84 = var19;
                     } else {
                        double var90;
                        var76 = (var90 = var22 - -65.0) == 0.0 ? 0 : (var90 < 0.0 ? -1 : 1);
                        var84 = var19;
                     }
                  } else {
                     var84 = var19;
                  }
               }

               if (ax >= 0) {
                  if (var84 == null) {
                     if (var76 <= 0) {
                        continue;
                     }

                     double var91;
                     var76 = (var91 = var27 - 0.0) == 0.0 ? 0 : (var91 < 0.0 ? -1 : 1);
                     if (var19 == null) {
                        var76 = var76 == 0 ? 1 : 0;
                     }
                  } else if (var19 == null) {
                     var76 = var76 == 0 ? 1 : 0;
                  }
               } else if (var84 == null) {
                  var76 = var76 == 0 ? 1 : 0;
               }

               if (ax >= 0) {
                  if (var76 == 0) {
                     var37++;
                     if (var19 == null) {
                        continue label288;
                     }
                     continue;
                  }

                  double var92;
                  var76 = (var92 = var29 - 0.0) == 0.0 ? 0 : (var92 < 0.0 ? -1 : 1);
                  if (var19 == null) {
                     var76 = var76 == 0 ? 1 : 0;
                  }
               } else if (var19 == null) {
                  var76 = var76 == 0 ? 1 : 0;
               }

               if (axx > 0) {
                  if (var76 != 0) {
                     double var93;
                     var76 = (var93 = var31 - 0.0) == 0.0 ? 0 : (var93 < 0.0 ? -1 : 1);
                     if (var19 == null) {
                        var76 = !var76;
                     }

                     if (var76) {
                     }
                  }
               } else {
                  if (var19 == null) {
                     var76 = var76 == 0 ? 1 : 0;
                  }

                  if (var76 != 0) {
                  }
               }

               var37++;
               if (var19 == null) {
                  continue label288;
               }
            } while (axx < 0);

            return;
         }
      }
   }

   private static final boolean settingVisible() {
      return INSTANCE.getPredict() == BowAimbotPredictMode.CUSTOM;
   }

   static {
      long var20 = a ^ 70002917144546L;
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[22];
      int var16 = 0;
      String var15 = "¾ÛG£\u009d\u0085áÏ¤\u008d3¶\u008a¯æ×Ñ&\u0015w,Å¡yþ\u0091üwýÀiO\u0010ß\u0010-nkÑÙ\u0010\f\u0086í\u0083\u008d\u0010»\u008dv0Ü4!åþT(yFHæòj\u0004*\u0090\u0095~Ó\u0090ÙÑ\u0016\u0092k]käÇþÂ¶§4\u0018GÑÿ\u008d9ç)w&:ùÜ qbé¾U\n3\u009e\u0005.'óú]Uôh\u0085IµËo.\u001fßd´ñð}±\u0081(d\u0087±Ød0\u009d¢\nä\u009fSÊØÉõ\u009fú°þA\u0004´\u0006\u0013M¿¯ò¬\u0094úÚ\u0096 <\tr[³ B\u0014x¤!ä`\tWg\rf`J²tç\u008eOÃ\u009a7\u00951ÚôÌG8[¹è\u0018.\u001c[NÐ\u009a9MpÃz~j<Íì%öô¼\u0096u3ó\u0010zÁQb\u008aó\u0011;ËÆ=\u001d=\u0010\n\u009e hÕhÎ(ßÐ\u0017Ú±J1C\u0088\u000bc1C\u0010\u0000õEÚ¤éÕgùyuÜ\u009f\u0018Å\u0094¹|nïq¶k\u008c\u008fÿàY\u0090i~\u0019\u000b\u0001¼\u0098E\u009487Ù\\±/9§â\u001cõæí=\u0095ËG¸UúÅDÐ{\u0090\n\u0010\u0080d\u0005©è\u0090\u0084fý\u009dL©jÝ/\u0012ð¹b\u001eFìNgg\u0010\u0007\u0083\u0083\u0001 ºdý¨;\u0013\u001d1h÷±ÿ\u0082ègi¹ì§ø°=\u0089C/\u0007×ÒJ.Ç\u0081 ¸\u008cñ°îF\u000fä\n\u001e\u001e\u00870\"\u008c\u0094Ý]\u009eõ°¸\u0094*»Í°\u0094»OLç\u0010\u001cÙØÞ¬Ó£\u000eï tê\u001cxÖv\u0010c\u001b\u001eãqaÚE\u0092/£\u0087!1ËI /¨:©\u0092\u007fÉ[FQø\u001dÈÕüE\u0096M^h+f\u0011¢l\u000fX?[0áï\u0018ÿ¾`\u0005ú\u0018o*{1ÂÍî_\u0099Þ¶H\u000bï³LAe ]è\u0006~2zyRW\u0090D1èì\u001al\u008c\"/\u0091è\u0087×\u0016:Ø¼<\b ñ\u008d\u0010I¯={îR\u009aH¨¾Y\u0018²§rô\u0018\fW\u009eûðÒ«ë?\u0091´ò²\u0004Å\u00163\u000fZ*\u0098\u0092-£";
      short var17 = 595;
      char var14 = '(';
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
                     c = var18;
                     d = new String[22];
                     h = new HashMap(13);
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
                     String var4 = "Þ\u001aá\u0099'¼\u008a\\Ñ·ä`þò¶¿\u0096ÍOR6\u0015cßj¾(´\tó^\u009c";
                     byte var5 = 32;
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
                                    f = var6;
                                    g = new Integer[6];
                                    KProperty[] var30 = new KProperty[]{
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(BowAimbot.class, "silent", "getSilent()Z", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(BowAimbot.class, "predict", "getPredict()Lsu/catlean/ar;", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(BowAimbot.class, "predictTicks", "getPredictTicks()I", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(BowAimbot.class, "ignoreNaked", "getIgnoreNaked()Z", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(BowAimbot.class, "ignoreCreative", "getIgnoreCreative()Z", 0)
                                       )
                                    };
                                    delegatedProperties = var30;
                                    INSTANCE = new BowAimbot();
                                    silent$delegate = SettingFactory.booleanSetting$default(INSTANCE, "silent", true, null, null, 12, null);
                                    predict$delegate = SettingFactory.modeSetting$default(INSTANCE, "predict", BowAimbotPredictMode.SMART, null, null, 12, null);
                                    predictTicks$delegate = SettingFactory.intSetting$default(
                                       INSTANCE, "predict-ticks", 1, new IntRange(1, 20), null, BowAimbot::settingVisible, 8, null
                                    );
                                    ignoreNaked$delegate = SettingFactory.booleanSetting$default(INSTANCE, "ignore-naked", true, null, null, 12, null);
                                    ignoreCreative$delegate = SettingFactory.booleanSetting$default(INSTANCE, "ignore-creative", true, null, null, 12, null);
                                    return;
                                 }
                                 break;
                              default:
                                 var37[var10001] = var54;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "<DcG8é¥G?ùim\u00adN7g";
                                 var5 = 16;
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

                  var15 = "\u0006\bW¨Óûná uM(Ü²²mïé\u0082dÿçhØ\u009bû\f\u001e¥G\u0090\u0095\u00949b½YsPÉ8m|æN\\\u0000u¥OgXn\u001e±\u008es(\u0002'¢\u00893â\u0016\u0001U¾È\u009cIà¢6Ë4'\f}°Ú8\u009dtªe@-ü5Ëi\bð!¦å";
                  var17 = 97;
                  var14 = '(';
                  var33 = -1;
            }

            var34 = var15.substring(++var33, var33 + var14);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 17017;
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
            throw new RuntimeException("su/catlean/uf", var10);
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
         throw new RuntimeException("su/catlean/uf" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 17900;
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
         Object[] var9 = (Object[])h.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               h.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/uf", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         g[var3] = var15;
      }

      return g[var3];
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
         throw new RuntimeException("su/catlean/uf" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
