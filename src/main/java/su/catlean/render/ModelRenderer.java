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
import net.minecraft.client.option.Perspective;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EndCrystalEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.EndCrystalEntityRenderState;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionfc;
import su.catlean.mixins.accessors.EndCrystalRendererAccessor;
import su.catlean.module.cosmetic.CrystalChams;
import su.catlean.module.esp.LogoutSpots;
import su.catlean.util.EntityUtil;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ModelRenderer {
   @NotNull
   public static final ModelRenderer INSTANCE;
   private static boolean q;
   private static final long a = ZkmKeys.a(5495117789964301002L, -1040850817570672223L, MethodHandles.lookup().lookupClass()).a(213472768619827L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long[] e;
   private static final Integer[] f;
   private static final Map g;

   private ModelRenderer() {
   }

   public final void w(
      int a,
      @NotNull MatrixStack stack,
      byte a,
      @NotNull BoxRenderMode mode,
      int a,
      @NotNull Color fillColor,
      @NotNull Color lineColor,
      int vDistance,
      int hDistance
   ) {
      long var25;
      boolean var39;
      Vec3d var71;
      label136: {
         long var10 = ((long)ax << 32 | (long)axxx << 56 >>> 32 | (long)axx << 40 >>> 40) ^ a;
         var25 = var10 ^ 44645101033412L;
         boolean var10000 = v();
         Intrinsics.checkNotNullParameter(stack, "stack");
         var39 = var10000;
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(fillColor, "fillColor");
         Intrinsics.checkNotNullParameter(lineColor, "lineColor");
         Entity var70 = Globals.getMc().getCameraEntity();
         if (!var39) {
            if (var70 != null) {
               var71 = EntityUtil.getPos(var70);
               if (var71 != null) {
                  break label136;
               }
            }

            var70 = Globals.getPlayer();
         }

         var71 = EntityUtil.getPos(var70);
      }

      Vec3d var40 = var71;
      Iterable var72 = Globals.getWorld().getEntities();
      Intrinsics.checkNotNullExpressionValue(var72, "entitiesForRendering(...)");
      Iterable var42 = var72;
      Iterable var44 = var42;
      var var45 = new ArrayList();
      Iterator var47 = var42.iterator();

      while (true) {
         if (var47.hasNext()) {
            Object var48 = var47.next();
            boolean var73 = var48 instanceof EndCrystalEntity;
            if (var39) {
               break;
            }

            if (!var39) {
               if (!var73) {
                  continue;
               }

               var45.add(var48);
            }

            if (!var39) {
               continue;
            }
         }

         var42 = var45;
         var44 = var42;
         var45 = new ArrayList();
         boolean var74 = false;
         break;
      }

      for (Object var69 : var44) {
         label112: {
            label141: {
               EndCrystalEntity var49 = (EndCrystalEntity)var69;
               double var84;
               var75 = (var84 = Math.hypot(var40.x - var49.getX(), var40.z - var49.getZ()) - hDistance) == 0.0 ? 0 : (var84 < 0.0 ? -1 : 1);
               if (!var39) {
                  if (var75 >= 0) {
                     break label141;
                  }

                  double var85;
                  var75 = (var85 = Math.abs(var40.y - var49.getY()) - vDistance) == 0.0 ? 0 : (var85 < 0.0 ? -1 : 1);
               }

               if (var39) {
                  break label112;
               }

               if (var75 < 0) {
                  var75 = 1;
                  break label112;
               }
            }

            var75 = 0;
         }

         if (var75 != 0) {
            var45.add(var69);
            if (var39) {
               break;
            }
         }
      }

      List var41 = var45;
      if (!var41.isEmpty()) {
         BoxRenderMode var10001;
         label95: {
            label142: {
               label143: {
                  var76 = mode;
                  var10001 = BoxRenderMode.BOTH;
                  if (!var39) {
                     if (mode == BoxRenderMode.BOTH) {
                        break label143;
                     }

                     var76 = mode;
                     var10001 = BoxRenderMode.FILL;
                  }

                  if (var39) {
                     break label95;
                  }

                  if (var76 != var10001) {
                     break label142;
                  }
               }

               VertexFormat var10002 = VertexFormats.POSITION_COLOR;
               Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
               int var10003 = var41.size() * 72;
               Object var34 = null;
               byte var35 = 4;
               boolean var36 = false;
               int var37 = var10003;
               VertexFormat var38 = var10002;
               BatchedBuffer var60 = new BatchedBuffer(var38, var37, var36, var35, (DefaultConstructorMarker)var34);

               for (Object var46 : var41) {
                  EndCrystalEntity var67 = (EndCrystalEntity)var46;
                  ModelRenderer var77 = INSTANCE;
                  Entity var79 = var67;
                  BoxRenderMode var10004 = BoxRenderMode.FILL;
                  float var50 = CrystalChams.INSTANCE.getAnimatedScale(var67.age + RenderState.INSTANCE.getTickDelta());
                  var77.I(stack, var79, fillColor, var10004, var60, var50);
                  if (var39 || var39) {
                     break;
                  }
               }

               BatchedBuffer.draw$default(
                  var25, var60, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, stack.peek().getPositionMatrix(), null, null, 54, null
               );
            }

            var76 = mode;
            var10001 = BoxRenderMode.BOTH;
         }

         label72: {
            if (!var39) {
               if (var76 == var10001) {
                  break label72;
               }

               var76 = mode;
               var10001 = BoxRenderMode.OUTLINE;
            }

            if (var76 != var10001) {
               return;
            }
         }

         VertexFormat var80 = VertexFormats.POSITION_COLOR;
         Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
         int var82 = var41.size() * 144;
         boolean var56 = true;
         int var57 = var82;
         VertexFormat var58 = var80;
         BatchedBuffer var61 = new BatchedBuffer(var58, var57, var56);

         for (Object var65 : var41) {
            EndCrystalEntity var68 = (EndCrystalEntity)var65;
            ModelRenderer var78 = INSTANCE;
            Entity var81 = var68;
            BoxRenderMode var83 = BoxRenderMode.OUTLINE;
            float var54 = CrystalChams.INSTANCE.getAnimatedScale(var68.age + RenderState.INSTANCE.getTickDelta());
            var78.I(stack, var81, lineColor, var83, var61, var54);
            if (var39 || var39) {
               break;
            }
         }

         BatchedBuffer.draw$default(
            var25, var61, Pipelines.INSTANCE.getProgramDefaultPosColorLines(), null, null, stack.peek().getPositionMatrix(), null, null, 54, null
         );
      }
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   public final void Z(
      @NotNull MatrixStack stack,
      @NotNull BoxRenderMode mode,
      @NotNull Color fillColor,
      @NotNull Color lineColor,
      @NotNull Color friendFillColor,
      @NotNull Color friendLineColor,
      long a,
      int vDistance,
      int hDistance
   ) {
      long var17;
      boolean var35;
      Vec3d var57;
      label151: {
         ax = a ^ ax;
         var17 = ax ^ 122537873995028L;
         boolean var10000 = v();
         Intrinsics.checkNotNullParameter(stack, "stack");
         Intrinsics.checkNotNullParameter(mode, "mode");
         var35 = var10000;
         Intrinsics.checkNotNullParameter(fillColor, "fillColor");
         Intrinsics.checkNotNullParameter(lineColor, "lineColor");
         Intrinsics.checkNotNullParameter(friendFillColor, "friendFillColor");
         Intrinsics.checkNotNullParameter(friendLineColor, "friendLineColor");
         Entity var56 = Globals.getMc().getCameraEntity();
         if (!var35) {
            if (var56 != null) {
               var57 = EntityUtil.getPos(var56);
               if (var57 != null) {
                  break label151;
               }
            }

            var56 = Globals.getPlayer();
         }

         var57 = EntityUtil.getPos(var56);
      }

      Vec3d var36 = var57;
      Iterable var58 = Globals.getWorld().getEntities();
      Intrinsics.checkNotNullExpressionValue(var58, "entitiesForRendering(...)");
      Iterable var38 = var58;
      var var41 = new ArrayList();

      for (Object var44 : var38) {
         label140: {
            label155: {
               Entity var45 = (Entity)var44;
               var59 = var45 instanceof PlayerEntity;
               if (!var35) {
                  if (var59 == 0) {
                     break label155;
                  }

                  var59 = Intrinsics.areEqual(var45, Globals.getPlayer());
               }

               if (!var35) {
                  if (var59 != 0 && Globals.getMc().options.getPerspective() == Perspective.FIRST_PERSON) {
                     break label155;
                  }

                  double var66;
                  var59 = (var66 = Math.hypot(var36.x - ((PlayerEntity)var45).getX(), var36.z - ((PlayerEntity)var45).getZ()) - hDistance) == 0.0
                     ? 0
                     : (var66 < 0.0 ? -1 : 1);
               }

               if (!var35) {
                  if (var59 >= 0) {
                     break label155;
                  }

                  double var67;
                  var59 = (var67 = Math.abs(var36.y - ((PlayerEntity)var45).getY()) - vDistance) == 0.0 ? 0 : (var67 < 0.0 ? -1 : 1);
               }

               if (var35) {
                  break label140;
               }

               if (var59 < 0) {
                  var59 = 1;
                  break label140;
               }
            }

            var59 = 0;
         }

         if (var59 != 0) {
            var41.add(var44);
            if (var35) {
               break;
            }
         }
      }

      List var37 = var41;
      if (!var37.isEmpty()) {
         label111: {
            label156: {
               BoxRenderMode var10001;
               label157: {
                  label158: {
                     var60 = mode;
                     var10001 = BoxRenderMode.BOTH;
                     if (!var35) {
                        if (mode == BoxRenderMode.BOTH) {
                           break label158;
                        }

                        var60 = mode;
                        var10001 = BoxRenderMode.FILL;
                     }

                     if (var35) {
                        break label157;
                     }

                     if (var60 != var10001) {
                        var60 = mode;
                        var10001 = BoxRenderMode.BOTH;
                        break label157;
                     }
                  }

                  VertexFormat var10002 = VertexFormats.POSITION_COLOR;
                  Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
                  int var10003 = var37.size() * 144;
                  Object var30 = null;
                  byte var31 = 4;
                  boolean var32 = false;
                  int var33 = var10003;
                  VertexFormat var34 = var10002;
                  var50 = new BatchedBuffer(var34, var33, var32, var31, (DefaultConstructorMarker)var30);
                  Iterable var39 = var37;
                  Iterator var52 = var39.iterator();

                  while (true) {
                     if (var52.hasNext()) {
                        Object var42 = var52.next();
                        Entity var54 = (Entity)var42;
                        ModelRenderer var61 = INSTANCE;
                        Intrinsics.checkNotNull(var54);
                        boolean var64 = Friends.getIsHomie((PlayerEntity)var54);
                        if (var35) {
                           z(var61, stack, var54, var64 ? friendLineColor : lineColor, BoxRenderMode.OUTLINE, var50, 0.0F, null);
                           if (var35) {
                              break label111;
                           }
                           break label156;
                        }

                        z(var61, stack, var54, var64 ? friendFillColor : fillColor, BoxRenderMode.FILL, var50, 0.0F, null);
                        if (!var35) {
                           continue;
                        }
                     }

                     BatchedBuffer.draw$default(
                        var17, var50, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, stack.peek().getPositionMatrix(), null, null, 54, null
                     );
                     var60 = mode;
                     var10001 = BoxRenderMode.BOTH;
                     break;
                  }
               }

               if (!var35 ? var60 != var10001 && mode != BoxRenderMode.OUTLINE : var60 != var10001) {
                  return;
               }

               VertexFormat var63 = VertexFormats.POSITION_COLOR;
               Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
               int var65 = var37.size() * 288;
               boolean var47 = true;
               int var48 = var65;
               VertexFormat var49 = var63;
               var50 = new BatchedBuffer(var49, var48, var47);
            }

            for (Object var53 : var37) {
               Entity var55 = (Entity)var53;
               if (var35) {
                  break;
               }

               ModelRenderer var62 = INSTANCE;
               Intrinsics.checkNotNull(var55);
               z(var62, stack, var55, Friends.getIsHomie((PlayerEntity)var55) ? friendLineColor : lineColor, BoxRenderMode.OUTLINE, var50, 0.0F, null);
               if (var35) {
                  break;
               }
            }
         }

         BatchedBuffer.draw$default(
            var17, var50, Pipelines.INSTANCE.getProgramDefaultPosColorLines(), null, null, stack.peek().getPositionMatrix(), null, null, 54, null
         );
      }
   }

   public final void B(
      @NotNull MatrixStack stack,
      @NotNull BoxRenderMode mode,
      @NotNull Color fillColor,
      @NotNull Color lineColor,
      long a,
      @NotNull ArrayList list,
      int vDistance,
      int hDistance
   ) {
      long var16;
      Vec3d var54;
      label78: {
         ax = a ^ ax;
         var16 = ax ^ 12308369354878L;
         Intrinsics.checkNotNullParameter(stack, "stack");
         Intrinsics.checkNotNullParameter(mode, "mode");
         Intrinsics.checkNotNullParameter(fillColor, "fillColor");
         Intrinsics.checkNotNullParameter(lineColor, "lineColor");
         Intrinsics.checkNotNullParameter(list, "list");
         Entity var10000 = Globals.getMc().getCameraEntity();
         if (var10000 != null) {
            var54 = EntityUtil.getPos(var10000);
            if (var54 != null) {
               break label78;
            }
         }

         var54 = EntityUtil.getPos(Globals.getPlayer());
      }

      Vec3d var32 = var54;
      Iterable var34 = list;
      var var37 = new ArrayList();

      for (Object var40 : var34) {
         PlayerEntity var41 = (PlayerEntity)var40;
         if (!Intrinsics.areEqual(var41, Globals.getPlayer())
            || Globals.getMc().options.getPerspective() != Perspective.FIRST_PERSON
               && Math.hypot(var32.x - var41.getX(), var32.z - var41.getZ()) < hDistance
               && Math.abs(var32.y - var41.getY()) < vDistance) {
            var37.add(var40);
         }
      }

      List var33 = var37;
      if (!var33.isEmpty()) {
         if (mode == BoxRenderMode.BOTH || mode == BoxRenderMode.FILL) {
            VertexFormat var10002 = VertexFormats.POSITION_COLOR;
            Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
            int var10003 = list.size() * 144;
            Object var26 = null;
            byte var27 = 4;
            boolean var28 = false;
            int var29 = var10003;
            VertexFormat var30 = var10002;
            BatchedBuffer var46 = new BatchedBuffer(var30, var29, var28, var27, (DefaultConstructorMarker)var26);

            for (Object var38 : var33) {
               PlayerEntity var52 = (PlayerEntity)var38;
               INSTANCE.Q(stack, var52, fillColor, BoxRenderMode.FILL, var46, 1.0F);
            }

            BatchedBuffer.draw$default(
               var16, var46, Pipelines.INSTANCE.getProgramDefaultPosColor(), null, null, stack.peek().getPositionMatrix(), null, null, 54, null
            );
         }

         if (mode == BoxRenderMode.BOTH || mode == BoxRenderMode.OUTLINE) {
            VertexFormat var55 = VertexFormats.POSITION_COLOR;
            Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_COLOR, "POSITION_COLOR");
            int var56 = list.size() * 288;
            boolean var43 = true;
            int var44 = var56;
            VertexFormat var45 = var55;
            BatchedBuffer var47 = new BatchedBuffer(var45, var44, var43);

            for (Object var51 : var33) {
               PlayerEntity var53 = (PlayerEntity)var51;
               INSTANCE.Q(stack, var53, lineColor, BoxRenderMode.OUTLINE, var47, 1.0F);
            }

            BatchedBuffer.draw$default(
               var16, var47, Pipelines.INSTANCE.getProgramDefaultPosColorLines(), null, null, stack.peek().getPositionMatrix(), null, null, 54, null
            );
         }
      }
   }

   private final void Q(MatrixStack var1, Entity var2, Color var3, BoxRenderMode var4, BatchedBuffer var5, float var6) {
      if (var2 instanceof PlayerEntity) {
         PolygonConsumer var22 = new PolygonConsumer(var5, var4, var3);
         EntityRenderer var10000 = Globals.getMc().getEntityRenderDispatcher().getRenderer(var2);
         Intrinsics.checkNotNull(
            var10000,
            "null cannot be cast to non-null type net.minecraft.client.renderer.entity.player.AvatarRenderer<in @[EnhancedNullability] net.minecraft.world.entity.player.Player>"
         );
         EntityModel var33 = ((PlayerEntityRenderer)var10000).getModel();
         Intrinsics.checkNotNullExpressionValue(var33, "getModel(...)");
         PlayerEntityModel var23 = (PlayerEntityModel)var33;
         EntityRenderState var34 = Globals.getMc()
            .getEntityRenderDispatcher()
            .getRenderer(var2)
            .getAndUpdateRenderState(var2, RenderState.INSTANCE.getTickDelta());
         Intrinsics.checkNotNull(var34, "null cannot be cast to non-null type net.minecraft.client.renderer.entity.state.AvatarRenderState");
         PlayerEntityRenderState var24 = (PlayerEntityRenderState)var34;
         var1.push();
         Vec3d var25 = RenderState.INSTANCE.getInterpolatedPos((PlayerEntity)var2, var6);
         double var26 = var25.x;
         double var28 = var25.y;
         double var30 = var25.z;
         Camera var35 = Globals.getMc().gameRenderer.getCamera();
         Intrinsics.checkNotNullExpressionValue(var35, "getMainCamera(...)");
         Camera var32 = var35;
         var1.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-var32.getYaw() + 180.0F));
         var1.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-var32.getPitch()));
         var1.translate((float)var26, (float)var28, (float)var30);
         var1.scale(var24.baseScale, var24.baseScale, var24.baseScale);
         LogoutSpots.INSTANCE.applyModelRotation(var24, var1, var24.bodyYaw);
         var1.scale(-1.0F, -1.0F, 1.0F);
         var1.scale(0.9375F, 0.9375F, 0.9375F);
         var1.translate(0.0F, ((PlayerEntity)var2).isSneaking() ? -1.37F : -1.501F, 0.0F);
         var23.setAngles(var24);
         var23.jacket.visible = false;
         var23.leftSleeve.visible = false;
         var23.rightSleeve.visible = false;
         var23.leftPants.visible = false;
         var23.rightPants.visible = false;
         var23.hat.visible = false;
         var23.render(var1, var22, 15728880, 0);
         var1.pop();
      }
   }

   static void z(ModelRenderer var0, MatrixStack var1, Entity var2, Color var3, BoxRenderMode var4, BatchedBuffer var5, float var6, Object var7) {
      var6 = RenderState.INSTANCE.getTickDelta();
      var0.Q(var1, var2, var3, var4, var5, var6);
   }

   public final void h(
      @NotNull MatrixStack stack,
      @NotNull PlayerEntityModel model,
      @NotNull PlayerEntityRenderState state,
      @NotNull Vec3d pos,
      @NotNull Color color,
      @NotNull BoxRenderMode mode,
      @NotNull BatchedBuffer polygon
   ) {
      Intrinsics.checkNotNullParameter(stack, "stack");
      Intrinsics.checkNotNullParameter(model, "model");
      Intrinsics.checkNotNullParameter(state, "state");
      Intrinsics.checkNotNullParameter(pos, "pos");
      Intrinsics.checkNotNullParameter(color, "color");
      Intrinsics.checkNotNullParameter(mode, "mode");
      Intrinsics.checkNotNullParameter(polygon, "polygon");
      PolygonConsumer var16 = new PolygonConsumer(polygon, mode, color);
      stack.push();
      Camera var10000 = Globals.getMc().getEntityRenderDispatcher().camera;
      Intrinsics.checkNotNull(var10000);
      Vec3d var25 = var10000.getCameraPos();
      Intrinsics.checkNotNullExpressionValue(var25, "position(...)");
      Vec3d var17 = var25;
      double var18 = pos.x - var17.x;
      double var20 = pos.y - var17.y;
      double var22 = pos.z - var17.z;
      var10000 = Globals.getMc().gameRenderer.getCamera();
      Intrinsics.checkNotNullExpressionValue(var10000, "getMainCamera(...)");
      Camera var24 = var10000;
      stack.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-var24.getYaw() + 180.0F));
      stack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-var24.getPitch()));
      stack.translate((float)var18, (float)var20, (float)var22);
      stack.scale(state.baseScale, state.baseScale, state.baseScale);
      LogoutSpots.INSTANCE.applyModelRotation(state, stack, state.bodyYaw);
      stack.scale(-1.0F, -1.0F, 1.0F);
      stack.scale(0.9375F, 0.9375F, 0.9375F);
      stack.translate(0.0F, -1.501F, 0.0F);
      model.setAngles(state);
      model.jacket.visible = false;
      model.leftSleeve.visible = false;
      model.rightSleeve.visible = false;
      model.leftPants.visible = false;
      model.rightPants.visible = false;
      model.hat.visible = false;
      model.render(stack, var16, 15728880, 0);
      stack.pop();
   }

   private final void I(MatrixStack var1, Entity var2, Color var3, BoxRenderMode var4, BatchedBuffer var5, float var6) {
      if (var2 instanceof EndCrystalEntity) {
         PolygonConsumer var14 = new PolygonConsumer(var5, var4, var3);
         EntityRenderer var10000 = Globals.getMc().getEntityRenderDispatcher().getRenderer(var2);
         Intrinsics.checkNotNullExpressionValue(var10000, "getRenderer(...)");
         EntityRenderer var15 = var10000;
         EndCrystalEntityModel var16 = ((EndCrystalRendererAccessor)((EndCrystalEntityRenderer)var15)).getModel();
         EntityRenderState var26 = ((EndCrystalEntityRenderer)var15).getAndUpdateRenderState(var2, RenderState.INSTANCE.getTickDelta());
         Intrinsics.checkNotNullExpressionValue(var26, "createRenderState(...)");
         EndCrystalEntityRenderState var17 = (EndCrystalEntityRenderState)var26;
         Camera var27 = Globals.getMc().getEntityRenderDispatcher().camera;
         Intrinsics.checkNotNull(var27);
         Vec3d var28 = var27.getCameraPos();
         Intrinsics.checkNotNullExpressionValue(var28, "position(...)");
         Vec3d var18 = var28;
         double var19 = ((EndCrystalEntity)var2).getX() - var18.x;
         double var21 = ((EndCrystalEntity)var2).getY() - var18.y;
         double var23 = ((EndCrystalEntity)var2).getZ() - var18.z;
         var1.push();
         Camera var29 = Globals.getMc().gameRenderer.getCamera();
         Intrinsics.checkNotNullExpressionValue(var29, "getMainCamera(...)");
         Camera var25 = var29;
         var1.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-var25.getYaw() + 180.0F));
         var1.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-var25.getPitch()));
         var1.translate(var19, var21, var23);
         var1.scale(var6, var6, var6);
         var1.scale(2.0F, 2.0F, 2.0F);
         var1.translate(0.0F, -0.5F, 0.0F);
         var16.setAngles(var17);
         var16.render(var1, var14, 15728880, 0);
         var1.pop();
      }
   }

   static {
      long var20 = a ^ 133055295838957L;
      zkmSetFlag(true);
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[29];
      int var16 = 0;
      String var15 = "»²è4yËöæ\u0017¶Å\u0000\nâ\u001fØ\u001fë\u0013w\u0005/\u009c¥¢î]\u0016:\u0012ðêï\u0099ÿ\røLay\u0010Ø\u0006-¯JpJ@i¿èH\u008aiË>\u0010Ç\u0012\u00981\u0003£ñÒ\u0004x{¦òÞ×\u0013 \u0090e\nÌ.\u0094ã\u000føIÞ\u0017H\u0097\u001cÜ\u009dÈÚ³\u0091ü¡Ìö\u0004\t\u0093ú\u0094PG +\n\u009f\u0087¢6é0¢MÕTÛh\u0001 Ð\f§îßm\u0080Ïchr\u0098]\u0015¡Ý(Ì\u009f7©,M\nLl3Mño_eôÆc6\u0084%¬Zâ½Ñ'ùÓÈ0Kb~\u0012Ê\u0098Y!±\u0018µFf{ÂEåopËÐ¾U\u0001±g/ï?¹\f\u0094\u0094¡8DåÐÎ6FÂ|P\u0095ÖA}çìØs\u0094º¨\u008dé^\u00adúS»ð\u009b³î\u0097\u007f\f¶ÂYñTz\u0018\u0096\u0084@ï\u0004\u0092\u001bÃGùJR÷2S\u0010Ít\u0088\u0097G\u008e\u008cz\u0096;-ûÂ\u0018¾  )~\u0097¦r\u0081«Ï8\tÍP\u0017\u0011\râ¨@\u0010g«\u009d\u001a\u0017iJr\u009f¸É«.\u009a\u008býî\u0092w2+x\u008cÑBØ¨Ò\rR=\u0083ìQíã®4.Ûc}aÖ±¬\u0092)¼¼Ñì\u0002«[\tàZÈEÃ\u00186z\u0001¦¥\u0004<$\u0097øTìàÞ\u0002X\u0088B7è9/³\u0012\u001dÛ\\Ïa[¶\u000b]\u008b\u009d\u001bZ\u001eJÿj\u0019\u008b\\À>äú©\u000f<\u001f=:5½{vMyÐ\u0007Ý¼Nà,¡Ù\u0081Ê0\t'ú\u0081»iÍ \u0090\u0081\u0016\u0012%\u0095ÊÇÕ^0.qÞ´jJÚæ`\u0007¬éT\tL\u0090¦üÎµ\u008d ô>\u0018\u0080\u0081J1\f1ÄÏà\u001bM\u008dþ\u0015\u0004'EÀ\u008fÏ\u0088\u008dö\u0093búH?^(0\u0087¿|õ\u0094¨Ôc\u008aÜ¥RÐM%Ó8)]\u0005ÇO\u0082e£\u0011>\u009e\u0091Û\u0092 ö±'ünêa |áeN/\u0098ììjØ¿ó\u008cc\u0092ÂmP¾\u0083°D±¿Ü\u0094ý]¶'q\u0082\u0018\rC\u001fâ Âp\u0088°d)\u0002{t|\u0015^\u001b3¯;Æø=\u0010\u007fàM\nGÕ«\u001c=\u0003¿\bÑìµ\u0095 hN.çS£á;®c¨\u001bú\u0004;Ä»\u000bþà¸¹\u0080ßåp\u0091\u0094í\u0012³\u0015 bSï0EI 4ùË?Ã\u00826iç·\u0012Çá\u0006Zí\u0018ð\u0088\u000eK\f`{»\u0010hÆ~\t\b\b\u0005\u0005¨\u0091¬|\u0091\u0095A¶\u0010\u0091>a¹\u008bC³&LÌJ<0ò\u0011^ ñ\u008a\u0015xksÚ7ntÍ»2\u0088¤\u0015·¯ \u008f\u00804ÉBCÍ\u0004.\u0095ËrÕ\u0010\u0004#0\u001dýPÆ×[îk\u0006°×\u0004S0\u0083\u0092Jªa\u0019ÑÊ\u008b\u0019NÇGÀl\u0097Ø¥UÁ)¬\u0019°»YÑ!\u0088;ª\u001fK\u008c-T\u0012\u0099ëCª\u008bÆ\u0015Á,Br\u0010àå\u008d8¾òæò\u0012ÐÆx6\u0095°ÝĀ©õµÀô\u008e´AÚ¯õL\"¸^@4z¸\u000bþ¡Ò\u0017Ã1°!¿êC¥3v³¨\u008eùl\u008acÖ©ä\u0011\u001bþIìë=\tA\u001a±¨N ¼Íã\u0080nÙ±\u008d(\u000fHdÑµ:\u000bú¥=×\u0002Lx%â\u0003ãy\u001a\u000e$|UE\n`Ð°KtD»ô\u008d\u0002\u0094År{\u0085ï\u008bý\u009dÐÔ\u007f\u0004J\u00adG) õnÞ\u0096sÓ\u0006¶\u0081ë\u0011¯\t\fÄD¬Ò\u00069zô$6\u000f±\u0096)\u0097µª\u008eY\u0088\u0015Î\u0088\"\u0005S¨Ì³ä±'Õ\u007fç\u0011\t%\u007fóp3O0\u0097#~\u009fl\u001d\u001f|ÃÓErZ½Ó\u0099|\u0088\\\u0007\u0007ük³û?{ü5H&ò\u0092¾æê,[_\u0082\nëL_Ä.*\u0089\t\u0080¥\u0088ZC¬¯±~uÎ\u001d\u001aúÒ¥4ÙÏÔ\u0095\u0013¨R\u0082Ñ2z ç%¿\u0015£\u009b8Úñí\u0083\u009b-\u001fÏqËK\u009d>¦\u00927I\u0003\u0016]gõ\u009a²\u008d(ÜÂx\u001d\u0019·èËët)ï\u008aW5\u001aõMà\u0089Ã\u009aú/\u0019\u0099\u008f¨|Ú¦¾\u0012\u009c;0\u001d\fRÛ";
      short var17 = 1170;
      char var14 = '(';
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
                     c = new String[29];
                     g = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[11];
                     int var3 = 0;
                     String var4 = "lö\u009e¶\u0011Ò18Á·b#ÔsF¿#\u0081FBÛA\u0092Õ¾r\u0093\u0017%Ðs\u00947\u009f\u0000&ÈH»¼1Àùòk\u001c\u0095\u000b}òUñ#ð?B\u0010`\u0012\u0015åÃ\u0090M $ícs/\t\u009b";
                     byte var5 = 72;
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
                                    e = var6;
                                    f = new Integer[11];
                                    INSTANCE = new ModelRenderer();
                                    return;
                                 }
                                 break;
                              default:
                                 var28[var10001] = var45;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "Ñ¾ÓIN\u0095EroXû°òly\u0000";
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

                  var15 = "\u0015\u0080êÈ\u0080\u001dP\u00074Ë\u009cw^\"n\u0080\u0010z\u000b\u001c\rßko±e\u000b'=ìo*ë";
                  var17 = 33;
                  var14 = 16;
                  var24 = -1;
            }

            var25 = var15.substring(++var24, var24 + var14);
            var10001 = 0;
         }
      }
   }

   public static void zkmSetFlag(boolean var0) {
      q = var0;
   }

   public static boolean zkmGetFlag() {
      return q;
   }

   public static boolean v() {
      return false;
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 32700;
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
            throw new RuntimeException("su/catlean/nn", var10);
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
         throw new RuntimeException("su/catlean/nn" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 24897;
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
            throw new RuntimeException("su/catlean/nn", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         f[var3] = var15;
      }

      return f[var3];
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
         throw new RuntimeException("su/catlean/nn" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
