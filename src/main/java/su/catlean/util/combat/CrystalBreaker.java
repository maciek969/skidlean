package su.catlean.util.combat;

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
import java.util.NoSuchElementException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.basic.MoveFix;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.RandomUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CrystalBreaker {
   @NotNull
   public static final CrystalBreaker INSTANCE;
   @Nullable
   private static EndCrystalEntity endCrystalEntity;
   private static final long a = ZkmKeys.a(6708098567996275847L, 6427450408264301711L, MethodHandles.lookup().lookupClass()).a(107661336642689L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long e;

   private CrystalBreaker() {
   }

   @Nullable
   public final EndCrystalEntity getEndCrystalEntity() {
      return endCrystalEntity;
   }

   public final void setEndCrystalEntity(@Nullable EndCrystalEntity <set-?>) {
      endCrystalEntity = <set-?>;
   }

   @Nullable
   public final CrystalInfo V(@NotNull AutoCrystal $this$getCrystalToExplode, @NotNull CrystalContext configState, long a) {
      ax = a ^ ax;
      long var5 = ax ^ 131252024350905L;
      long var10001 = ax ^ 128336879339340L;
      int var10 = (int)((ax ^ 128336879339340L) >>> 48);
      int var11 = (int)((ax ^ 128336879339340L) << 16 >>> 48);
      int var12 = (int)(var10001 << 32 >>> 32);
      long var17 = ax ^ 18638055976301L;
      Intrinsics.checkNotNullParameter($this$getCrystalToExplode, "<this>");
      Intrinsics.checkNotNullParameter(configState, "configState");
      if (endCrystalEntity != null) {
         EndCrystalEntity var21 = endCrystalEntity;
         endCrystalEntity = null;
         Intrinsics.checkNotNull(var21);
         Entity var19 = var21;
         if (this.u($this$getCrystalToExplode, $this$getCrystalToExplode.getPos(var19), configState, var17, true, var21.age) != null) {
            return new CrystalInfo(var21, true);
         }
      }

      PlaceResult var10000 = DamageComparator.pickBest(var5, configState, this.S($this$getCrystalToExplode, (short)var10, configState, (char)var11, var12));
      if (var10000 != null) {
         EndCrystalEntity var24 = (EndCrystalEntity)var10000.getData();
         if (var24 != null) {
            EndCrystalEntity var22 = var24;
            return new CrystalInfo(var22, false, null);
         }
      }

      return null;
   }

   private final List S(AutoCrystal var1, short var2, CrystalContext var3, char var4, int var5) {
      long var6 = ((long)var2 << 48 | (long)var4 << 48 >>> 16 | (long)var5 << 32 >>> 32) ^ a;
      long var11 = var6 ^ 27588241547462L;
      long var15 = var6 ^ 43303537617873L;
      int var10000 = ZkmReferences.zkmBootstrap<"Å">(1987577153486583080L, var6);
      Iterable var20 = var3.getEntities();
      boolean var19 = (boolean)var10000;
      Iterable var22 = var20;
      var var23 = new ArrayList();
      Iterator var25 = var20.iterator();

      while (true) {
         if (var25.hasNext()) {
            Object var26 = var25.next();
            if (var5 < 0) {
               break;
            }

            var10000 = var26 instanceof EndCrystalEntity;
            if (var19) {
               break;
            }

            if (!var19) {
               if (!var10000) {
                  continue;
               }

               var23.add(var26);
            }

            if (!var19) {
               continue;
            }
         }

         var20 = var23;
         var22 = var20;
         var23 = new ArrayList();
         if (var5 >= 0) {
            var10000 = (boolean)0;
         }
         break;
      }

      var25 = var22.iterator();

      while (true) {
         if (var25.hasNext()) {
            Object var40 = var25.next();
            EndCrystalEntity var27 = (EndCrystalEntity)var40;
            if (var4 <= 0) {
               break;
            }

            var10000 = var27.isRemoved();
            if (var19) {
               break;
            }

            label115: {
               label148: {
                  boolean var10001 = var19;
                  if (var5 >= 0) {
                     if (!var19) {
                        if (var10000 != 0) {
                           break label148;
                        }

                        var10000 = CrystalTracker.INSTANCE.hasFailedBreak(var27.getId(), var11);
                     }

                     var10001 = var19;
                  }

                  if (var4 >= 0) {
                     if (!var10001) {
                        if (var10000 != 0) {
                           break label148;
                        }

                        var10000 = CrystalTracker.INSTANCE.hasSpawned(var27.getId());
                     }

                     var10001 = var19;
                  }

                  if (var2 >= 0) {
                     if (!var10001) {
                        if (var10000 != 0) {
                           break label148;
                        }

                        Entity var36 = Globals.getPlayer();
                        double var49;
                        var10000 = (var49 = var1.getPos(var36).squaredDistanceTo(var1.getPos(var27)) - 49.0) == 0.0 ? 0 : (var49 < 0.0 ? -1 : 1);
                     }

                     var10001 = var19;
                  }

                  if (var10001) {
                     break label115;
                  }

                  if (var10000 <= 0) {
                     var10000 = 1;
                     break label115;
                  }
               }

               var10000 = 0;
            }

            if (var10000 == 0) {
               continue;
            }

            var23.add(var40);
            if (!var19) {
               continue;
            }
         }

         var20 = var23;
         var22 = var20;
         var23 = new ArrayList();
         if (var5 > 0) {
            boolean var45 = false;
         }
         break;
      }

      Iterator var41 = var22.iterator();

      while (true) {
         if (var41.hasNext()) {
            Object var28 = var41.next();
            EndCrystalEntity var31 = (EndCrystalEntity)var28;
            boolean var46 = var19;
            if (var4 > 0) {
               if (var19) {
                  break;
               }

               var46 = var19;
            }

            label72: {
               if (!var46) {
                  DamageData var47 = INSTANCE.u(var1, var1.getPos(var31), var3, var15, false, var31.age);
                  if (var47 != null) {
                     DamageData var33 = var47;
                     var48 = new PlaceResult(var31, var33);
                     break label72;
                  }
               }

               var48 = null;
            }

            if (var48 != null) {
               PlaceResult var35 = var48;
               var23.add(var35);
            }

            if (!var19) {
               continue;
            }
         }

         if (var2 >= 0) {
         }
         break;
      }

      return var23;
   }

   @Nullable
   public final Vec3d S(@NotNull Vec3d vec, @NotNull CrystalContext configState, @NotNull Vec3d eyes) {
      Intrinsics.checkNotNullParameter(vec, "vec");
      int var10000 = CrystalPlacer.D();
      Intrinsics.checkNotNullParameter(configState, "configState");
      boolean var10 = (boolean)var10000;
      Vec3d var25 = eyes;
      if (!var10) {
         Intrinsics.checkNotNullParameter(eyes, "eyes");
         if (configState.isRayTrace()) {
            ArrayList var11 = new ArrayList();
            Vec3d var26 = vec.add(-1.0, 0.0, -1.0);
            Intrinsics.checkNotNullExpressionValue(var26, "add(...)");
            Vec3d var12 = var26;
            int var13 = 0;

            label103:
            while (true) {
               var10000 = var13;

               label101:
               while (true) {
                  if (var10000 >= 3) {
                     break label103;
                  }

                  int var14 = 0;

                  while (var14 < 3) {
                     var10000 = 0;
                     if (var10) {
                        continue label101;
                     }

                     int var15 = 0;

                     while (true) {
                        if (var15 < 3) {
                           var11.add(new Vec3d(var12.x + var13, var12.y + var14, var12.z + var15));
                           var15++;
                           if (var10) {
                              break;
                           }

                           if (!var10) {
                              continue;
                           }
                        }

                        var14++;
                        break;
                     }

                     if (var10) {
                        break;
                     }
                  }

                  var13++;
                  if (var10) {
                     break label103;
                  }
                  break;
               }
            }

            Iterable var23 = var11;
            var var17 = new ArrayList();
            Iterator var19 = var23.iterator();

            while (true) {
               if (var19.hasNext()) {
                  Object var20 = var19.next();
                  Vec3d var21 = (Vec3d)var20;
                  var10000 = configState.isGrimBreakRange();
                  if (var10) {
                     break;
                  }

                  label69: {
                     if (!var10) {
                        if (var10000 != 0) {
                           var10000 = CrystalOffsets.INSTANCE.findBestOffset(vec, var21);
                           break label69;
                        }

                        double var32;
                        var10000 = (var32 = eyes.distanceTo(var21) - configState.getExplodeRange()) == 0.0 ? 0 : (var32 < 0.0 ? -1 : 1);
                     }

                     if (!var10) {
                        var10000 = var10000 <= 0 ? 1 : 0;
                     }
                  }

                  if (!var10) {
                     if (var10000 == 0) {
                        continue;
                     }

                     var17.add(var20);
                  }

                  if (!var10) {
                     continue;
                  }
               }

               var23 = var17;
               boolean var29 = false;
               break;
            }

            label56:
            for (Vec3d var30 : CollectionsKt.sortedWith(var23, new CrystalBreaker$1(eyes))) {
               do {
                  var24 = var30;
                  if (DamageUtil.raycastExplosion$default(DamageUtil.INSTANCE, eyes, var24, false, null, false, (int)e, null) != null) {
                     continue label56;
                  }

                  var30 = var24;
               } while (var10);

               return var24;
            }

            return null;
         }

         double var10003 = vec.x - 1.0;
         double var10002 = RangesKt.coerceIn(Globals.getPlayer().getEyePos().x, var10003, vec.x + 1.0);
         var10003 = RangesKt.coerceIn(Globals.getPlayer().getEyePos().y, vec.y, vec.y + 2.0);
         double var10005 = vec.z - 1.0;
         var25 = new Vec3d(var10002, var10003, RangesKt.coerceIn(Globals.getPlayer().getEyePos().z, var10005, vec.z + 1.0));
      }

      return var25;
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   @Nullable
   public final DamageData u(
      @NotNull AutoCrystal $this$canAttackCrystal, @NotNull Vec3d pos, @NotNull CrystalContext configState, long a, boolean secondaryCrystal, int age
   ) {
      ax = a ^ ax;
      int var14 = (int)((ax ^ 118194968494987L) >>> 48);
      long var15 = (ax ^ 118194968494987L) << 16 >>> 16;
      Intrinsics.checkNotNullParameter($this$canAttackCrystal, "<this>");
      Intrinsics.checkNotNullParameter(pos, "pos");
      Intrinsics.checkNotNullParameter(configState, "configState");
      int var10000 = CrystalPlacer.D();
      BlockPos var10001 = BlockPos.ofFloored(pos.add(0.0, -0.1, 0.0));
      Intrinsics.checkNotNullExpressionValue(var10001, "containing(...)");
      BlockPos var32 = var10001;
      boolean var31 = (boolean)var10000;
      var10000 = $this$canAttackCrystal.getRandomizeDelay();
      if (!var31) {
         if (var10000 != 0) {
            label103: {
               int var55 = $this$canAttackCrystal.Y() % 4;
               if (!var31) {
                  if (var55 == 0) {
                     var56 = 2;
                     break label103;
                  }

                  var55 = 0;
               }

               var56 = RandomUtil.P(var55, 1, false, null);
            }

            if (age < var56) {
               return null;
            }
         }

         var10000 = configState.isRayTrace();
      }

      if (!var31) {
         if (var10000 != 0) {
            if (E(this, pos, configState, null, null) == null) {
               return null;
            }

            return DamageComparator.compareDamages((short)var14, $this$canAttackCrystal, var32, var15, configState, secondaryCrystal, false, false);
         }

         var10000 = MoveFix.INSTANCE.getEnabled();
      }

      if (!var31) {
         var10000 = var10000 != 0 ? var32.up().getY() : var32.getY();
      }

      int var33 = var10000;
      Vec3d var34 = pos;
      double var58;
      var10000 = (var58 = Globals.getPlayer().getEyePos().y - var33) == 0.0 ? 0 : (var58 < 0.0 ? -1 : 1);
      if (!var31) {
         if (var10000 > 0) {
            Iterable var36 = CrystalPlacer.N();
            Iterator var38 = var36.iterator();
            Object var50 = var38;
            if (!var31) {
               if (!var38.hasNext()) {
                  throw new NoSuchElementException();
               }

               var50 = var38.next();
            }

            label114: {
               Object var39 = var50;
               Object var51 = var38;
               if (!var31) {
                  if (!var38.hasNext()) {
                     var54 = var39;
                     break label114;
                  }

                  var51 = var39;
               }

               Vec3d var40 = (Vec3d)var51;
               MovementUtil var52 = MovementUtil.INSTANCE;
               Vec3d var30 = new Vec3d(var32.getX() + var40.x, var32.getY() + var40.y + 1.0, var32.getZ() + var40.z);
               float var46 = var52.distanceTo(var30);

               label82:
               while (true) {
                  Object var41 = var38.next();
                  Vec3d var42 = (Vec3d)var41;
                  MovementUtil var53 = MovementUtil.INSTANCE;
                  var30 = new Vec3d(var32.getX() + var42.x, var32.getY() + var42.y + 1.0, var32.getZ() + var42.z);
                  float var47 = var53.distanceTo(var30);
                  if (Float.compare(var46, var47) > 0) {
                     var39 = var41;
                     var46 = var47;
                  }

                  while (!var38.hasNext()) {
                     if (!var31) {
                        var54 = var39;
                        break label82;
                     }

                     var46 = var47;
                  }
               }
            }

            Vec3d var35 = (Vec3d)var54;
            var34 = new Vec3d(var32.getX() + var35.x, var32.getY() + 1.0, var32.getZ() + var35.z);
         }

         var10000 = configState.isGrimBreakRange();
      }

      if (!var31) {
         if (var10000 != 0) {
            if (!CrystalOffsets.INSTANCE.findBestOffset(pos, var34)) {
               return null;
            }

            return DamageComparator.compareDamages((short)var14, $this$canAttackCrystal, var32, var15, configState, secondaryCrystal, false, false);
         }

         PlaceManager var57 = PlaceManager.INSTANCE;
         Entity var43 = Globals.getPlayer();
         double var59;
         var10000 = (var59 = var34.squaredDistanceTo(var57.getEyePos(var43)) - configState.getPlaceRange() * configState.getPlaceRange()) == 0.0
            ? 0
            : (var59 < 0.0 ? -1 : 1);
      }

      return var10000 > 0
         ? null
         : DamageComparator.compareDamages((short)var14, $this$canAttackCrystal, var32, var15, configState, secondaryCrystal, false, false);
   }

   static {
      long var5 = a ^ 117437952888936L;
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
      String var11 = "C,`^=s#¿©\u0011\u0096Nt\u0089\u008e~æ²ÊQïò\u0013\\ \fÅÿi\u0098\\\u008e\bôáÓÉæ7\u0084.ßû¿h·P>g2\bõ|\u0002SÂº\u0010ò\u009d¢\u0086Í´?\t\u0082ÇÔ\u0099\u0094¡Q¬\u0010éZ\u008e¨ÂÞ¤\u008dRç\u000bs Zód\u0010íB\"'\u0012¾8ò¡àè×êï±þ\u0010TÀü`3Í£@gÞ\tdÐ¡¾\u0087 ó\u000ekÓ\u0017!Û\u0014\u0086wSãÍ½UÅä,·ë\u0098\u009a.Å=¡÷=,¤\u0018n";
      short var13 = 158;
      char var10 = 24;
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
                     byte[] var4 = var0.doFinal(new byte[]{86, 77, 121, -113, -121, -17, 93, 21});
                     e = (var4[0] & 255L) << 56
                        | (var4[1] & 255L) << 48
                        | (var4[2] & 255L) << 40
                        | (var4[3] & 255L) << 32
                        | (var4[4] & 255L) << 24
                        | (var4[5] & 255L) << 16
                        | (var4[6] & 255L) << 8
                        | var4[7] & 255L;
                     INSTANCE = new CrystalBreaker();
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

                  var11 = "[\\FQ\u008f4\u009a\u0092äq&\u0082Bîrm(×7¨\u0096viµ\u00ad\u0080\u008eE\u0002zgÅ\u0010a9\u0007Ù\u0087¿®¸\u00009þ\u007f\u0015´Úz";
                  var13 = 49;
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 26164;
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
            throw new RuntimeException("su/catlean/oa", var10);
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
         throw new RuntimeException("su/catlean/oa" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
