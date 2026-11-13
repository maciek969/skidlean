package su.catlean.render;

import com.mojang.blaze3d.vertex.VertexFormat;
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
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2f;
import org.joml.Matrix3x2fc;
import org.joml.Vector4d;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ChamsGlowRenderer {
   @NotNull
   public static final ChamsGlowRenderer INSTANCE;
   private static final long b = ZkmKeys.a(-1892577867384747402L, -8191973748843455894L, MethodHandles.lookup().lookupClass()).a(1059541003579L);
   private static final String[] c;
   private static final String[] d;
   private static final Map e = new HashMap(13);
   private static final long[] f;
   private static final Integer[] g;
   private static final Map h;

   private ChamsGlowRenderer() {
   }

   public final void Y(@NotNull DrawContext context, @NotNull Color lineColor, long a, byte a) {
      long var6 = (a << 8 | (long)ax << 56 >>> 56) ^ b;
      long var12 = var6 ^ 95032910417263L;
      int var10000 = ModelRenderer.v();
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(lineColor, "lineColor");
      Iterable var10001 = Globals.getWorld().getEntities();
      Intrinsics.checkNotNullExpressionValue(var10001, "entitiesForRendering(...)");
      Iterable var27 = var10001;
      boolean var25 = (boolean)var10000;
      var var30 = new ArrayList();

      for (Object var33 : var27) {
         var10000 = var33 instanceof EndCrystalEntity;
         if (!var25) {
            if (!var10000) {
               continue;
            }

            var30.add(var33);
         }

         if (var25) {
            break;
         }
      }

      List var26 = var30;
      VertexFormat var10002 = VertexFormats.POSITION_COLOR;
      Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
      int var10003 = var26.size() * 8;
      Object var20 = null;
      byte var21 = 4;
      boolean var22 = false;
      int var23 = var10003;
      VertexFormat var24 = var10002;
      BatchedBuffer var40 = new BatchedBuffer(var24, var23, var22, var21, (DefaultConstructorMarker)var20);
      Iterable var28 = var26;
      Iterator var41 = var28.iterator();

      label68:
      while (true) {
         var10000 = var41.hasNext();

         label65:
         while (var10000 != 0) {
            Object var31 = var41.next();
            EndCrystalEntity var42 = (EndCrystalEntity)var31;
            Vec3d[] var34 = RenderUtil.INSTANCE.getBoxCorners(var42);
            Vector4d var35 = null;
            int var36 = 0;
            if (var25) {
               return;
            }

            int var37 = var34.length;

            while (var36 < var37) {
               Vec3d var38 = var34[var36];
               Vec3d var39 = RenderUtil.INSTANCE.toCameraRelative(new Vec3d(var38.x, var38.y, var38.z));
               if (!var25) {
                  double var51;
                  var10000 = (var51 = var39.z - 0.0) == 0.0 ? 0 : (var51 < 0.0 ? -1 : 1);
                  if (var25) {
                     continue label65;
                  }

                  if (var10000 > 0 && var39.z < 1.0) {
                     Vector4d var49 = var35;
                     if (!var25) {
                        if (var35 == null) {
                           var35 = new Vector4d(var39.x, var39.y, var39.z, 0.0);
                        }

                        var35.x = Math.min(var39.x, var35.x);
                        var35.y = Math.min(var39.y, var35.y);
                        var35.z = Math.max(var39.x, var35.z);
                        var49 = var35;
                     }

                     var49.w = Math.max(var39.y, var35.w);
                  }

                  var36++;
               }

               if (var25) {
                  break;
               }
            }

            label49: {
               Vector4d var50 = var35;
               if (!var25) {
                  if (var35 == null) {
                     break label49;
                  }

                  var50 = var35;
               }

               float var43 = (float)var50.x;
               float var44 = (float)var35.y;
               float var45 = (float)var35.z;
               float var46 = (float)var35.w;
               RenderUtil.INSTANCE.outlineQuad(var40, var43, var44, var45, var46, lineColor);
            }

            if (!var25) {
               continue label68;
            }
            break;
         }

         BatchedBuffer.draw$default(
            var12, var40, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, null, new Matrix3x2f((Matrix3x2fc)context.getMatrices()), null, 46, null
         );
         return;
      }
   }

   public final void w(long a, @NotNull DrawContext context, @NotNull Color lineColor, @NotNull Color friendLineColor) {
      a = b ^ a;
      long var12 = a ^ 85837401051500L;
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(lineColor, "lineColor");
      Intrinsics.checkNotNullParameter(friendLineColor, "friendLineColor");
      Iterable var10000 = Globals.getWorld().getEntities();
      Intrinsics.checkNotNullExpressionValue(var10000, "entitiesForRendering(...)");
      Iterable var31 = var10000;
      var var34 = new ArrayList();
      boolean var29 = ModelRenderer.v();

      for (Object var37 : var31) {
         label101: {
            label110: {
               Entity var38 = (Entity)var37;
               var54 = var38 instanceof PlayerEntity;
               if (!var29) {
                  if (!var54) {
                     break label110;
                  }

                  var54 = Intrinsics.areEqual(var38, Globals.getPlayer());
               }

               if (var29) {
                  break label101;
               }

               if (!var54 || Globals.getMc().options.getPerspective() != Perspective.FIRST_PERSON) {
                  var54 = true;
                  break label101;
               }
            }

            var54 = false;
         }

         if (var54) {
            var34.add(var37);
            if (var29) {
               break;
            }
         }
      }

      List var30 = var34;
      VertexFormat var10002 = VertexFormats.POSITION_COLOR;
      Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
      int var10003 = var30.size() * 8;
      Object var24 = null;
      byte var25 = 4;
      boolean var26 = false;
      int var27 = var10003;
      VertexFormat var28 = var10002;
      BatchedBuffer var46 = new BatchedBuffer(var28, var27, var26, var25, (DefaultConstructorMarker)var24);
      Iterable var32 = var30;
      Iterator var47 = var32.iterator();

      label81:
      while (true) {
         int var55 = var47.hasNext();

         label78:
         while (var55 != 0) {
            Object var35 = var47.next();
            Entity var48 = (Entity)var35;
            RenderUtil var56 = RenderUtil.INSTANCE;
            Intrinsics.checkNotNull(var48);
            Vec3d[] var49 = var56.getBoxCorners(var48);
            Vector4d var39 = null;
            int var40 = 0;
            if (var29) {
               return;
            }

            int var41 = var49.length;

            while (var40 < var41) {
               Vec3d var42 = var49[var40];
               Vec3d var43 = RenderUtil.INSTANCE.toCameraRelative(new Vec3d(var42.x, var42.y, var42.z));
               if (!var29) {
                  double var60;
                  var55 = (var60 = var43.z - 0.0) == 0.0 ? 0 : (var60 < 0.0 ? -1 : 1);
                  if (var29) {
                     continue label78;
                  }

                  if (var55 > 0 && var43.z < 1.0) {
                     Vector4d var57 = var39;
                     if (!var29) {
                        if (var39 == null) {
                           var39 = new Vector4d(var43.x, var43.y, var43.z, 0.0);
                        }

                        var39.x = Math.min(var43.x, var39.x);
                        var39.y = Math.min(var43.y, var39.y);
                        var39.z = Math.max(var43.x, var39.z);
                        var57 = var39;
                     }

                     var57.w = Math.max(var43.y, var39.w);
                  }

                  var40++;
               }

               if (var29) {
                  break;
               }
            }

            label115: {
               Vector4d var58 = var39;
               if (!var29) {
                  if (var39 == null) {
                     break label115;
                  }

                  var58 = var39;
               }

               float var50 = (float)var58.x;
               float var51 = (float)var39.y;
               float var52 = (float)var39.z;
               float var53 = (float)var39.w;
               RenderUtil var59 = RenderUtil.INSTANCE;
               var24 = Friends.getIsHomie((PlayerEntity)var48) ? friendLineColor : lineColor;
               var59.outlineQuad(var46, var50, var51, var52, var53, (Color)var24);
            }

            if (!var29) {
               continue label81;
            }
            break;
         }

         BatchedBuffer.draw$default(
            var12, var46, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, null, new Matrix3x2f((Matrix3x2fc)context.getMatrices()), null, 46, null
         );
         return;
      }
   }

   static {
      long var11 = b ^ 48464439052171L;
      Cipher var13;
      Cipher var10000 = var13 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var14 = 1; var14 < 8; var14++) {
         var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var20 = new String[9];
      int var18 = 0;
      String var17 = "~UOàFòÞVXPp\u0000¿M\u000b\r (Þ\u0018Ú/#\u0082$\u0015\u000e`\u0010,Uì=4\u0000-]¹\u0083@±®\u009f\rÉ\u001d'r\u00990PRÊ;»ÌÛî.\u0097·ÿ~pû\u0018\u0007\u008f\u001bÅB\u009e\u0096¨K\u0007±\u009d;½J\u0086\u0081Î-ú÷ä¹2\u00876g#&\u008a\u008a!\u0010\u0001ú6Ø\u008f\u0001ð\u009aì\u001fZéC¤6Ú ¡e\u009c¦óæ\u0088ÄTI$e;ýfI´äÒ¢ÇjÝüOÓ±N]´\u0019\u0091 ûâ1I°õ5ûUé\u0087\u009d\rOÃè\u00ad´ü¸\u0091\u0014àó÷\u0005\u0090\u009at\u0088K\u0097\u0018\u000fæoÚìç÷\u0007³sXTÎ\u0017\r©D\\rê¯u|«";
      short var19 = 206;
      char var16 = 16;
      int var24 = -1;

      label54:
      while (true) {
         String var25 = var17.substring(++var24, var24 + var16);
         int var10001 = -1;

         while (true) {
            byte[] var21 = var13.doFinal(var25.getBytes("ISO-8859-1"));
            String var36 = zkmDecodeUtf8(var21).intern();
            switch (var10001) {
               case 0:
                  var20[var18++] = var36;
                  if ((var24 += var16) >= var19) {
                     c = var20;
                     d = new String[9];
                     h = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[4];
                     int var3 = 0;
                     String var4 = "vF·ª\u0002Ü\u001eUÚÎJz\u008c $\u0094";
                     byte var5 = 16;
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
                                    g = new Integer[4];
                                    INSTANCE = new ChamsGlowRenderer();
                                    return;
                                 }
                                 break;
                              default:
                                 var28[var10001] = var45;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "{\u0015eÍGhqóYgãÎ%\u000e T";
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

                  var16 = var17.charAt(var24);
                  break;
               default:
                  var20[var18++] = var36;
                  if ((var24 += var16) < var19) {
                     var16 = var17.charAt(var24);
                     continue label54;
                  }

                  var17 = "ûë¥'8ûJNTB½\u0001\u0088\u001fÍé6à\u008aïL¾ûóåeQ\u0090ã}7ñ8\u008d\\\u008cuj\u0015Ç:\u0095H\u001a Ð\u007f\u0012¯Hü#ýÔ§ìÃ6äXD¼ÈÉ\u0094ö\u0086â%Ï+Û\u000búÿ-\u001d@ÒnÌ_\u008f\u0086õåj:3";
                  var19 = 89;
                  var16 = ' ';
                  var24 = -1;
            }

            var25 = var17.substring(++var24, var24 + var16);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 16864;
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
            throw new RuntimeException("su/catlean/fr", var10);
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
         throw new RuntimeException("su/catlean/fr" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 10216;
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
            throw new RuntimeException("su/catlean/fr", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         g[var3] = var15;
      }

      return g[var3];
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
         throw new RuntimeException("su/catlean/fr" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
