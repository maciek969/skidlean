package su.catlean;

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
import java.util.stream.Collectors;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.util.RandomUtil;
import su.catlean.util.rotation.AimMode;
import su.catlean.util.rotation.JitterMode;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class t3 extends AimMode {
   @NotNull
   public static final t3 INSTANCE;
   private static double l;
   private static int H;
   private static int F;
   private static double y;
   @NotNull
   private static ArrayList samples;
   @NotNull
   private static ArrayList c;
   private static double g;
   private static final long a = ZkmKeys.a(-3441898825856632967L, -1414880216948657166L, MethodHandles.lookup().lookupClass()).a(206634219364825L);
   private static final String[] b;
   private static final String[] d;
   private static final Map e = new HashMap(13);
   private static final long[] f;
   private static final Integer[] i;
   private static final Map j;

   private t3() {
   }

   @NotNull
   @Override
   public Rotation calcRotation(@NotNull Entity target, long a, @NotNull Rotation currentRotation, boolean attackTick) {
      long var10001 = ax ^ 56047677749854L;
      int var17 = (int)((ax ^ 56047677749854L) >>> 48);
      int var18 = (int)((ax ^ 56047677749854L) << 16 >>> 32);
      int var19 = (int)(var10001 << 48 >>> 48);
      JitterMode.zkmGetFlag();
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(currentRotation, "currentRotation");
      double var23 = target.age / 4.0;
      double var25 = target.getBoundingBox().getLengthX() / 2.3 * Math.sin(Math.toRadians(var23));
      double var27 = target.getBoundingBox().getLengthY() / 10.0 * Math.sin(Math.toRadians(var23 + 10.0));
      double var29 = target.getBoundingBox().getLengthZ() / 2.3 * -Math.cos(Math.toRadians(var23));
      Vec3d var10000 = EntityUtil.getPos(target).add(var25, target.getHeight() / 2.0 + var27, var29);
      Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
      Vec3d var31 = var10000;
      double var32 = MathHelper.wrapDegrees(
         Math.toDegrees(Math.atan2(var31.z - Globals.getPlayer().getZ(), var31.x - Globals.getPlayer().getX())) - 90.0 - currentRotation.getYaw()
      );
      double var34 = -Math.toDegrees(
            Math.atan2(
               var31.y - (EntityUtil.getPos(Globals.getPlayer()).y + Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose())),
               Math.hypot(var31.x - Globals.getPlayer().getX(), var31.z - Globals.getPlayer().getZ())
            )
         )
         - currentRotation.getPitch();
      if (Math.abs(l) > 300.0 || g > 100.0) {
         l = 0.0;
         H = RandomUtil.randomInt(1, 3, false);
         y = 0.0;
         g = 0.0;
      }

      if (H > 0) {
         int var60 = H;
         H = var60 + -1;
         y = 0.0;
         float var61 = RandomUtil.randomFloat(0.15F, 0.4F, false);
         return new Rotation(currentRotation.getYaw() + var61, currentRotation.getPitch() - var61 / 2.0F, false, null, 12, null);
      } else {
         boolean var36 = F > 20 && var32 > 60.0;
         if (var36) {
            F = 0;
         }

         ClosedFloatingPointRange var37 = RangesKt.rangeTo(25.0F, 35.0F);
         double var38 = var36 ? 84.0 + RandomUtil.randomFloat(RangesKt.rangeTo(-1.0F, 1.0F), false) : RandomUtil.randomFloat(var37, true);
         ClosedRange var66 = RangesKt.rangeTo(-5.1, 5.1);
         boolean var49 = true;
         ClosedRange var51 = var66;
         double var40 = Math.abs(RandomUtil.randomDouble(var51, var49));
         if (Globals.getPlayer().age % RandomUtil.randomInt(2, 10, false) == 0) {
            var38 /= 10.0;
         }

         var32 = RangesKt.coerceIn(var32, -var38, var38);
         var34 = RangesKt.coerceIn(var34, -var40, var40);
         var34 += var32 / 20.0;
         float var42 = currentRotation.getYaw() + (float)var32;
         float var43 = currentRotation.getPitch() + (float)var34;
         boolean var44 = target.getEyePos().distanceTo(Globals.getPlayer().getEyePos()) < 1.5;
         float var45 = var44 ? -40.0F + Globals.getPlayer().age % 100 / 25.0F + RandomUtil.randomFloat(-0.1F, 0.1F, false) : -90.0F;
         float var46 = var44 ? 60.0F + Globals.getPlayer().age % 100 / 25.0F + RandomUtil.randomFloat(-0.1F, 0.1F, false) : 90.0F;
         l += var32;
         if (var32 < 20.0) {
            int var47 = F++;
         } else {
            int var64 = F;
            F = var64 + -1;
         }

         var42 += RandomUtil.randomFloat(RangesKt.rangeTo(-2.0F, 2.0F), false);
         var43 += RandomUtil.randomFloat(RangesKt.rangeTo(-0.5F, 0.5F), false);
         samples.add(var42);
         c.add((float)var32);
         if (samples.size() >= 25) {
            float var65 = this.median(this.filterOutliers(samples, (char)var17, var18, var19));
            g += var65;
            samples.clear();
         }

         double var67 = y;
         ClosedRange var68 = RangesKt.rangeTo(0.1, 0.3);
         boolean var54 = false;
         ClosedRange var56 = var68;
         y = var67 + RandomUtil.randomDouble(var56, var54);
         return new Rotation(var42, RangesKt.coerceIn(var43, var45, var46), false, null, 12, null);
      }
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   private final List filterOutliers(List var1, char var2, int var3, int var4) {
      long var5 = ((long)var2 << 48 | (long)var3 << 32 >>> 16 | (long)var4 << 48 >>> 48) ^ a;
      String var10000 = ZkmReferences.zkmBootstrap<"Å">(-8304035160081981671L, var5);
      List var8 = new ArrayList();
      String var7 = var10000;
      Iterator var9 = var1.iterator();

      label120:
      while (true) {
         if (var9.hasNext()) {
            float var10 = ((Number)var9.next()).floatValue();

            do {
               if (var2 >= 0) {
                  var16 = var8.add(var10);
                  if (var7 != null) {
                     break label120;
                  }
               }

               if (var7 == null) {
                  continue label120;
               }
            } while (var4 <= 0);
         }

         var16 = 0;
         break;
      }

      int var14 = var16;

      while (true) {
         while (var14 < zkmBootstrap2<"v">(31307, 4575132926137337583L ^ var5) || var2 < 0) {
            label98:
            while (true) {
               List var15 = new ArrayList();
               float var11 = Float.MIN_VALUE;
               if (var7 != null) {
                  return var8;
               }

               Iterator var12 = var8.iterator();

               label95:
               while (true) {
                  float var13;
                  int var17;
                  if (var12.hasNext()) {
                     var13 = ((Number)var12.next()).floatValue();
                     float var20;
                     var17 = (var20 = var11 - (Float.MIN_VALUE)) == 0.0F ? 0 : (var20 < 0.0F ? -1 : 1);
                     if (var7 != null) {
                        continue label98;
                     }
                  } else {
                     var8 = new ArrayList(var15);
                     var14++;
                     var10000 = var7;
                     if (var2 >= 0) {
                        break;
                     }

                     var13 = ((Number)var7).floatValue();
                     float var21;
                     var17 = (var21 = var11 - (Float.MIN_VALUE)) == 0.0F ? 0 : (var21 < 0.0F ? -1 : 1);
                     if (var7 != null) {
                        continue label98;
                     }
                  }

                  while (true) {
                     while (var4 < 0) {
                        if (var7 != null) {
                           continue label98;
                        }
                     }

                     if (var7 == null) {
                        var17 = var17 == 0 ? 1 : 0;
                     }

                     if (var17 != 0) {
                        var11 = var13;
                        if (var3 >= 0) {
                           if (var7 == null) {
                              break;
                           }

                           var15.add((float)Math.abs(Math.abs((double)var13) - Math.abs((double)var13)));
                           var11 = var13;
                           if (var7 == null) {
                              break;
                           }
                        } else if (var7 == null) {
                           break;
                        }
                     } else {
                        var15.add((float)Math.abs(Math.abs((double)var13) - Math.abs((double)var11)));
                        var11 = var13;
                        if (var7 == null) {
                           break;
                        }
                     }

                     var8 = new ArrayList(var15);
                     var14++;
                     var10000 = var7;
                     if (var2 >= 0) {
                        break label95;
                     }

                     var13 = ((Number)var7).floatValue();
                     float var22;
                     var17 = (var22 = var11 - (Float.MIN_VALUE)) == 0.0F ? 0 : (var22 < 0.0F ? -1 : 1);
                     if (var7 != null) {
                        continue label98;
                     }
                  }
               }

               if (var10000 != null && var2 >= 0) {
                  break;
               }
            }

            return var8;
         }

         return var8;
      }
   }

   private final float median(List var1) {
      JitterMode.zkmGetFlag();
      List var3 = var1.stream().sorted().collect(Collectors.toList());
      int var4 = var3.size();
      float var5 = 0.0F;

      for (int var6 = 0; var6 < var4; var6++) {
         float var10000 = (var6 + 1.0F) / var4;
         Object var10001 = var3.get(var6);
         Intrinsics.checkNotNullExpressionValue(var10001, "get(...)");
         float var7 = Math.abs(var10000 - ((Number)var10001).floatValue());
         var5 = Math.max(var5, var7);
      }

      return var5;
   }

   static {
      long var20 = a ^ 136193875155883L;
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[4];
      int var16 = 0;
      String var15 = "³ðg6A°vï\u0012ôNÀAYÙrü~ácâ¸üß\u0018þel<gm\u0002çJg÷Â\u00018\u0016C\u0004\u0095å\u00adM©m ";
      byte var17 = 49;
      char var14 = 24;
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
                     b = var18;
                     d = new String[4];
                     j = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[9];
                     int var3 = 0;
                     String var4 = "¾Z~!\u008c?K\"\u0000w>®cuÑ\u000f\u0018\u009a£\u0002\u001bs<¥\u0012¥=.ÑU\u009b¹\u00ad\u009eÇl\u0087&\u0003\f\u009b\u0088\u0013Í¢K$/Åç\u0003Â¢\u0092^î";
                     byte var5 = 56;
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
                                    f = var6;
                                    i = new Integer[9];
                                    INSTANCE = new t3();
                                    samples = new ArrayList();
                                    c = new ArrayList();
                                    return;
                                 }
                                 break;
                              default:
                                 var28[var10001] = var45;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "G\u0007\u0085\u008f÷nð)õ¿óÆ°ËÓ\u001b";
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

                  var15 = "j\t¦gÅy6Â\u0082\u008e{\u0098\bO ; \u009a,,ÏN`Q ·]CiÔ÷leU\u0095\u009fµ¸\u00ad\u008c\u0084ÄÖ\\æ¶\nY¬";
                  var17 = 49;
                  var14 = 16;
                  var24 = -1;
            }

            var25 = var15.substring(++var24, var24 + var14);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 22590;
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
            throw new RuntimeException("su/catlean/t3", var10);
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
         throw new RuntimeException("su/catlean/t3" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 28960;
      if (i[var3] == null) {
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
            throw new RuntimeException("su/catlean/t3", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         i[var3] = var15;
      }

      return i[var3];
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
         throw new RuntimeException("su/catlean/t3" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
