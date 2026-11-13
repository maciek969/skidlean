package su.catlean.render;

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
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.EntityUtil;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ChamsRenderer {
   @NotNull
   public static final ChamsRenderer INSTANCE;
   private static final long a = ZkmKeys.a(-8706407952680489308L, 4127609483980001596L, MethodHandles.lookup().lookupClass()).a(100345552919754L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);

   private ChamsRenderer() {
   }

   public final void g(@NotNull BoxRenderMode mode, @NotNull Color fillColor, @NotNull Color lineColor, int vDistance, int hDistance) {
      Vec3d var39;
      label79: {
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(fillColor, "fillColor");
         Intrinsics.checkNotNullParameter(lineColor, "lineColor");
         Entity var10000 = Globals.getMc().getCameraEntity();
         if (var10000 != null) {
            var39 = EntityUtil.getPos(var10000);
            if (var39 != null) {
               break label79;
            }
         }

         var39 = EntityUtil.getPos(Globals.getPlayer());
      }

      Vec3d var24 = var39;
      Iterable var40 = Globals.getWorld().getEntities();
      Intrinsics.checkNotNullExpressionValue(var40, "entitiesForRendering(...)");
      Iterable var25 = var40;
      var var28 = new ArrayList();

      for (Object var31 : var25) {
         if (var31 instanceof EndCrystalEntity) {
            var28.add(var31);
         }
      }

      var25 = var28;
      var28 = new ArrayList();

      for (Object var38 : var25) {
         EndCrystalEntity var32 = (EndCrystalEntity)var38;
         if (Math.hypot(var24.x - var32.getX(), var24.z - var32.getZ()) < hDistance && Math.abs(var24.y - var32.getY()) < vDistance) {
            var28.add(var38);
         }
      }

      for (Object var36 : var28) {
         EndCrystalEntity var29 = (EndCrystalEntity)var36;
         if (mode == BoxRenderMode.BOTH || mode == BoxRenderMode.FILL) {
            RenderState var41 = RenderState.INSTANCE;
            Box var10001 = var29.getBoundingBox();
            Intrinsics.checkNotNullExpressionValue(var10001, "getBoundingBox(...)");
            RenderState.addFilledBox$default(var41, var10001, fillColor, null, null);
         }

         if (mode == BoxRenderMode.BOTH || mode == BoxRenderMode.OUTLINE) {
            RenderState var42 = RenderState.INSTANCE;
            Box var43 = var29.getBoundingBox();
            Intrinsics.checkNotNullExpressionValue(var43, "getBoundingBox(...)");
            Object var18 = null;
            Object var20 = null;
            Box var22 = var43;
            RenderState.addOutlineBox$default(var42, var22, lineColor, (Color)var20, var18);
         }
      }
   }

   public final void E(
      @NotNull BoxRenderMode mode,
      @NotNull Color fillColor,
      @NotNull Color lineColor,
      @NotNull Color friendFillColor,
      @NotNull Color friendLineColor,
      int vDistance,
      int hDistance,
      @NotNull Color sFillColor,
      @NotNull Color sLineColor,
      @NotNull Color sFriendFillColor,
      @NotNull Color sFriendLineColor
   ) {
      boolean var35;
      Vec3d var53;
      label150: {
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(fillColor, "fillColor");
         Intrinsics.checkNotNullParameter(lineColor, "lineColor");
         Intrinsics.checkNotNullParameter(friendFillColor, "friendFillColor");
         Intrinsics.checkNotNullParameter(friendLineColor, "friendLineColor");
         Intrinsics.checkNotNullParameter(sFillColor, "sFillColor");
         boolean var10000 = ModelRenderer.v();
         Intrinsics.checkNotNullParameter(sLineColor, "sLineColor");
         var35 = var10000;
         Intrinsics.checkNotNullParameter(sFriendFillColor, "sFriendFillColor");
         Intrinsics.checkNotNullParameter(sFriendLineColor, "sFriendLineColor");
         Entity var52 = Globals.getMc().getCameraEntity();
         if (!var35) {
            if (var52 != null) {
               var53 = EntityUtil.getPos(var52);
               if (var53 != null) {
                  break label150;
               }
            }

            var52 = Globals.getPlayer();
         }

         var53 = EntityUtil.getPos(var52);
      }

      Vec3d var36 = var53;
      Iterable var54 = Globals.getWorld().getEntities();
      Intrinsics.checkNotNullExpressionValue(var54, "entitiesForRendering(...)");
      Iterable var37 = var54;
      var var40 = new ArrayList();
      Iterator var42 = var37.iterator();

      while (true) {
         if (var42.hasNext()) {
            Object var43 = var42.next();
            Entity var44 = (Entity)var43;
            int var55 = var44 instanceof PlayerEntity;
            if (var35) {
               break;
            }

            label137: {
               label154: {
                  if (!var35) {
                     if (var55 == 0) {
                        break label154;
                     }

                     var55 = Intrinsics.areEqual(var44, Globals.getPlayer());
                  }

                  if (!var35) {
                     if (var55 != 0 && Globals.getMc().options.getPerspective() == Perspective.FIRST_PERSON) {
                        break label154;
                     }

                     double var66;
                     var55 = (var66 = Math.hypot(var36.x - ((PlayerEntity)var44).getX(), var36.z - ((PlayerEntity)var44).getZ()) - hDistance) == 0.0
                        ? 0
                        : (var66 < 0.0 ? -1 : 1);
                  }

                  if (!var35) {
                     if (var55 >= 0) {
                        break label154;
                     }

                     double var67;
                     var55 = (var67 = Math.abs(var36.y - ((PlayerEntity)var44).getY()) - vDistance) == 0.0 ? 0 : (var67 < 0.0 ? -1 : 1);
                  }

                  if (var35) {
                     break label137;
                  }

                  if (var55 < 0) {
                     var55 = 1;
                     break label137;
                  }
               }

               var55 = 0;
            }

            if (var55 == 0) {
               continue;
            }

            var40.add(var43);
            if (!var35) {
               continue;
            }
         }

         var37 = var40;
         boolean var56 = false;
         break;
      }

      for (Object var48 : var37) {
         Entity var41 = (Entity)var48;
         Box var57 = var41.getBoundingBox();
         Intrinsics.checkNotNullExpressionValue(var57, "getBoundingBox(...)");
         Box var49 = var57;
         Camera var58 = Globals.getMc().getEntityRenderDispatcher().camera;
         Intrinsics.checkNotNull(var58);
         var53 = var58.getCameraPos();
         Intrinsics.checkNotNullExpressionValue(var53, "position(...)");
         Vec3d var51 = var53;
         RenderState var60 = RenderState.INSTANCE;
         Intrinsics.checkNotNull(var41, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
         var53 = RenderState.getInterpolatedPos$default(var60, (PlayerEntity)var41, 0.0F, null).add(var51.x, var51.y, var51.z);
         Intrinsics.checkNotNullExpressionValue(var53, "add(...)");
         Vec3d var45 = var53;
         var53 = var45.add(-var49.getLengthX() / 2.0, 0.0, -var49.getLengthZ() / 2.0);
         Intrinsics.checkNotNullExpressionValue(var53, "add(...)");
         Vec3d var46 = var53;
         var53 = var45.add(var49.getLengthX() / 2.0, var49.getLengthY(), var49.getLengthZ() / 2.0);
         Intrinsics.checkNotNullExpressionValue(var53, "add(...)");
         Vec3d var47 = var53;
         var49 = new Box(var46, var47);
         if (var35) {
            break;
         }

         BoxRenderMode var10001;
         label106: {
            label156: {
               label157: {
                  var64 = mode;
                  var10001 = BoxRenderMode.BOTH;
                  if (!var35) {
                     if (mode == BoxRenderMode.BOTH) {
                        break label157;
                     }

                     var64 = mode;
                     var10001 = BoxRenderMode.FILL;
                  }

                  if (var35) {
                     break label106;
                  }

                  if (var64 != var10001) {
                     break label156;
                  }
               }

               RenderState.INSTANCE
                  .addFilledBox(
                     var49,
                     Friends.getIsHomie((PlayerEntity)var41) ? friendFillColor : fillColor,
                     Friends.getIsHomie((PlayerEntity)var41) ? sFriendFillColor : sFillColor
                  );
            }

            var64 = mode;
            var10001 = BoxRenderMode.BOTH;
         }

         label158: {
            label92: {
               if (!var35) {
                  if (var64 == var10001) {
                     break label92;
                  }

                  var64 = mode;
                  var10001 = BoxRenderMode.OUTLINE;
               }

               if (var64 != var10001) {
                  break label158;
               }
            }

            RenderState var65 = RenderState.INSTANCE;
            Color var10002 = Friends.getIsHomie((PlayerEntity)var41) ? friendLineColor : lineColor;
            Color var32 = Friends.getIsHomie((PlayerEntity)var41) ? sFriendLineColor : sLineColor;
            Color var33 = var10002;
            var65.addOutlineBox(var49, var33, var32);
         }

         if (var35) {
            break;
         }
      }
   }

   static {
      long var0 = a ^ 26743130293335L;
      Cipher var2;
      Cipher var10000 = var2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var3 = 1; var3 < 8; var3++) {
         var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var9 = new String[20];
      int var7 = 0;
      String var6 = "«¶\u000eïÁålý@ÿUmiZÇ\u0010Kg\b;\u0083\u009f\u001aã\u0018à´îA\u0081%õJ\u001f8]Ü.\u0004Å³²\r;\u001c`G´\u0091\u0080\u009ft\u0086À\u001fþ·c\\\u0084¤MÊ»¦é4\u000foúàÓ\u001b\tx~ñ¾MG\u0098ë\u0087±ÛB9\u0011\u009fiõ\u0012\u009e¾°íÒÈ\u0080/b\u0082[íà+ÑºaÖö\u0002q]fª;\u0093s\u0019{¿óü\u000f\u0010\u008b\u009aüUPÍ;\r\u008eiuUEÓ\u0093µ¢{§\u008aÃ«\"¡Wue2Ï\u0093á/\u008a{\u0006ñ\u008eÒ(Ü\u0017=%b«Ø\u001eú\u009eb\u0015Ù Mª=±z\u0097´4ïÔ\u0091r¤(ä\u0011¸q\u0092À\u008dÍr\u008c÷\u0095\u0098¨\u001f\bù\u0098\u0018¡ÿ¡Vì6Z2pïÆî\u009b:&3\u001d§^q)r¨{\u0018ZkK.X&\u0011·¿u7|1Äô¥\u00183³à\u000e÷7\u000e(¥\u008baÛz\u009fF\u009d\u0010E\u0005\u0007_E?ÉÑ\u001f\u001e<\u009a\\Q$\u0018U\u0096kw^\u009dÍ\b*Ìk\u0017Ñ\u0000P e\u0083]\u008bòã\u0004ì&\u0004\u001b¯\u0003¾VØ¡1¨:Ï-âÿ¬ e¦\f\u001f:\u0085(\u0018\u0081nWþ«\u0000\u0090\u00836\u0014A\u001cj\u009a±\u000e\u0083éÃ¿ñ§Næ\u0095°\u009d¨tÉàCÐç\\%½\u0003\u0013 {q¼Ì\u000bÙ\u000b0M\u0085¾`\u00adÖ\u009bó³½4\u0016?î|Jæ\u0006/\u0006\u008e¡\"© &ÀVe\u009aVÖ\u0085Ùpd}E\u009cðº,\u0019_£\u0097PÙÈpdÃ¹L\u008dgW nì\u0086OIû\u008b^ÐÝÀ\u008f?ò6\u0014\u00886\u0096Á[Í\u009d\u0010Ì]§uóI\u0097i8¤\u0087êó¿\u0096ÑÍæ×¦9\u009f»,A\u0098\u0010Ðked©\u0003'5@iÌ\u0017¦ÆÌ[2\u001c/\u009dRE\u0089Î+Áå8Anæ\u001bÚ\u009fÐMPW ç2Ø\u009cg¾±fÌâ$Â\u008fAûGkaª\u0097ì÷Ä5\u008cz\u00addÐ8\u0099\u00908\u001fÜýDá0l\u0088L\u008c\u0006©#Ù×\u0006ëË*\u0094\u001f\béÎ\u001c\u00adA¯\u008fxOï\u0019µ\u008eë\u0085Þ`s\u008e\u0000\u0089!æ\u009cHBrÂëMÛ\fÀ\u0080\u0018H\u0016²\u000bÜ\u001acN\bKÍ\u0095ÿ\u0004\n&¡mÁ5y11g(Ã¹\u008cò`ü\u009a\u0019YS¢\u0004ä0áÁ\r\u0090½çÑK{V\u009eíNãÂÊÐtÑ\u009c&r¹#\u0095ø(ÊÂüþ\u000e¬J¡\u001e\u008e¤\u008e8\u001c\u0013ÌÂ\u0016\u00adóH\u0005sÇ7Í\u0098K\u0013)¿yÏ\u009ct\u0087G¥ùH";
      short var8 = 729;
      char var5 = 24;
      int var12 = -1;

      label27:
      while (true) {
         String var13 = var6.substring(++var12, var12 + var5);
         byte var10001 = -1;

         while (true) {
            byte[] var10 = var2.doFinal(var13.getBytes("ISO-8859-1"));
            String var19 = zkmDecodeUtf8(var10).intern();
            switch (var10001) {
               case 0:
                  var9[var7++] = var19;
                  if ((var12 += var5) >= var8) {
                     b = var9;
                     c = new String[20];
                     INSTANCE = new ChamsRenderer();
                     return;
                  }

                  var5 = var6.charAt(var12);
                  break;
               default:
                  var9[var7++] = var19;
                  if ((var12 += var5) < var8) {
                     var5 = var6.charAt(var12);
                     continue label27;
                  }

                  var6 = "\u0013pHHÞ\u0011Îæá¿y,b+sÖ\u0010\u000f\u0004\u0098û8\rÒ\u0004DÓ°ôú<\u009aÉ";
                  var8 = 33;
                  var5 = 16;
                  var12 = -1;
            }

            var13 = var6.substring(++var12, var12 + var5);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 19977;
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
            throw new RuntimeException("su/catlean/j_", var10);
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
         throw new RuntimeException("su/catlean/j_" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
