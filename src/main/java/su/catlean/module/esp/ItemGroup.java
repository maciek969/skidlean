package su.catlean.module.esp;

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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.Grouping;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.render.RenderUtil;
import su.catlean.util.EntityUtil;
import su.catlean.zkm.ZkmKeys;

public final class ItemGroup {
   @NotNull
   private final List items;
   private static int U;
   private static final long a = ZkmKeys.a(-5929392097109375847L, -8985781271031890826L, MethodHandles.lookup().lookupClass()).a(117868564433088L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);

   public ItemGroup(@NotNull List items) {
      long var4 = 0L;
      byte var6 = 0;
      long var2 = (var4 << 32 | (long)var6 << 32 >>> 32) ^ a;
      Intrinsics.checkNotNullParameter(itemsx, zkmBootstrap<"b">(8384, 6037771023692895896L ^ var2));
      super();
      this.items = itemsx;
   }

   @NotNull
   public final List getItems() {
      return this.items;
   }

   @NotNull
   public final Vec3d f() {
      if (this.items.isEmpty()) {
         Vec3d var10000 = Vec3d.ZERO;
         Intrinsics.checkNotNullExpressionValue(Vec3d.ZERO, "ZERO");
         return var10000;
      } else {
         double var6 = 0.0;
         double var8 = 0.0;
         double var10 = 0.0;

         for (ItemEntity var13 : this.items) {
            RenderUtil var10001 = RenderUtil.INSTANCE;
            double var10002 = var13.lastX;
            double var14 = EntityUtil.getPos(var13).x;
            double var16 = var10002;
            var6 += var10001.interpolate(var16, var14);
            var10001 = RenderUtil.INSTANCE;
            var10002 = var13.lastY;
            double var20 = EntityUtil.getPos(var13).y;
            double var22 = var10002;
            var8 += var10001.interpolate(var22, var20);
            var10001 = RenderUtil.INSTANCE;
            var10002 = var13.lastZ;
            double var26 = EntityUtil.getPos(var13).z;
            double var28 = var10002;
            var10 += var10001.interpolate(var28, var26);
         }

         int var30 = this.items.size();
         return new Vec3d(var6 / var30, var8 / var30 + 0.7, var10 / var30);
      }
   }

   @NotNull
   public final List r() {
      Iterable var2 = this.items;
      int var1 = zkmIntConstant();
      Grouping var24 = new ItemGroup$grouping$1(var2);
      Integer var3 = 0;
      Grouping var7 = var24;
      Map var8 = new LinkedHashMap();
      Iterator var10 = var24.sourceIterator();

      Map var10000;
      while (true) {
         if (var10.hasNext()) {
            Object var11 = var10.next();
            Object var12 = var7.keyOf(var11);
            Object var13 = var8.get(var12);
            var10000 = var8;
            if (var1 == 0) {
               break;
            }

            boolean var10004;
            label48: {
               if (var13 == null) {
                  var10004 = var8.containsKey(var12);
                  if (var1 == 0) {
                     break label48;
                  }

                  if (!var10004) {
                     var10004 = true;
                     break label48;
                  }
               }

               var10004 = false;
            }

            boolean var14 = var10004;
            var10000 = (Map)(var14 ? var3 : var13);
            ItemEntity var20 = (ItemEntity)var11;
            int var21 = ((Number)var10000).intValue();
            Integer var30 = var21 + var20.getStack().getCount();
            var8.put(var12, var30);
            if (var1 != 0) {
               continue;
            }

            Module.zkmSetFlag(new Module[3]);
         }

         var10000 = var8;
         break;
      }

      Map var25 = var10000;
      Collection var5 = new ArrayList(var25.size());
      Iterator var26 = var25.entrySet().iterator();

      while (true) {
         if (var26.hasNext()) {
            Entry var27 = (Entry)var26.next();
            var32 = var5;
            if (var1 == 0) {
               break;
            }

            String var28 = (String)var27.getKey();
            int var29 = ((Number)var27.getValue()).intValue();
            var5.add(var28 + " x" + var29);
            if (var1 != 0) {
               continue;
            }
         }

         var32 = var5;
         break;
      }

      return (List)var32;
   }

   @NotNull
   public final List getItems2() {
      return this.items;
   }

   @NotNull
   public final ItemGroup copy(@NotNull List items) {
      long var5 = 0L;
      var5 = a ^ var5;
      Intrinsics.checkNotNullParameter(itemsx, zkmBootstrap<"b">(26191, 6410765195118887767L ^ var5));
      return new ItemGroup(itemsx);
   }

   public static ItemGroup copy$default(ItemGroup var0, List var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.items;
      }

      return var0.copy(var1);
   }

   @NotNull
   @Override
   public String toString() {
      return "ItemCluster(items=" + this.items + ")";
   }

   @Override
   public int hashCode() {
      return this.items.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object other) {
      int var4 = zkmIntConstant();
      Object var10000 = this;
      if (var4 != 0) {
         if (this == other) {
            return true;
         }

         var10000 = other;
      }

      if (var4 != 0) {
         if (!(var10000 instanceof ItemGroup)) {
            return false;
         }

         var10000 = other;
      }

      ItemGroup var5 = (ItemGroup)var10000;
      boolean var6 = Intrinsics.areEqual(this.items, var5.items);
      if (var4 != 0) {
         if (!var6) {
            return false;
         }

         var6 = true;
      }

      return var6;
   }

   public static void zkmSetFlag(int var0) {
      U = var0;
   }

   public static int zkmGetFlag() {
      return U;
   }

   public static int zkmIntConstant() {
      return 72;
   }

   static {
      long var9 = a ^ 139494718555152L;
      zkmSetFlag(0);
      Cipher var0;
      Cipher var10000 = var0 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var9 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var1 = 1; var1 < 8; var1++) {
         var10003[var1] = (byte)(var9 << var1 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var7 = new String[5];
      int var5 = 0;
      String var4 = "\u001f\u007f\u008däýûxÇöÏ\u00103E\u0091Ç\u0082\u0010\u0013ãâ\u0015\u0015È>{²´ë\u0018\u009cS~j\u0010±S\u000e}Ê`ë]P\rTø£\u008cÿË";
      byte var6 = 50;
      char var3 = 16;
      int var12 = -1;

      label27:
      while (true) {
         String var13 = var4.substring(++var12, var12 + var3);
         byte var10001 = -1;

         while (true) {
            byte[] var8 = var0.doFinal(var13.getBytes("ISO-8859-1"));
            String var19 = zkmDecodeUtf8(var8).intern();
            switch (var10001) {
               case 0:
                  var7[var5++] = var19;
                  if ((var12 += var3) >= var6) {
                     b = var7;
                     c = new String[5];
                     return;
                  }

                  var3 = var4.charAt(var12);
                  break;
               default:
                  var7[var5++] = var19;
                  if ((var12 += var3) < var6) {
                     var3 = var4.charAt(var12);
                     continue label27;
                  }

                  var4 = "\u0083Uú\u009b®ë\r5\u009b&¯\u0087\u0003ôÃÌ(G\u0083tS\u008c§ñRËüt\u0004¡ÇG\u0083\u00023\n\u008d[\b\u009cúaCÚ@Gâ¼zD\u008b%Íê¥\u0082@";
                  var6 = 57;
                  var3 = 16;
                  var12 = -1;
            }

            var13 = var4.substring(++var12, var12 + var3);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 28452;
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
            throw new RuntimeException("su/catlean/iz", var10);
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
         throw new RuntimeException("su/catlean/iz" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
