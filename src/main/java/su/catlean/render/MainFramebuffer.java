package su.catlean.render;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.render.FrameBufferEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.GameRendererAccessor;
import su.catlean.module.cosmetic.HandChams;
import su.catlean.render.particle.ParticleTexture;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class MainFramebuffer {
   @NotNull
   public static final MainFramebuffer INSTANCE;
   private static boolean G;
   @Nullable
   private static WindowFramebuffer windowFramebuffer;
   private static final long a = ZkmKeys.a(-6917757038483336241L, -5311254499861508910L, MethodHandles.lookup().lookupClass()).a(169292131484378L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long[] e;
   private static final Integer[] f;
   private static final Map g;

   private MainFramebuffer() {
   }

   @Nullable
   public final WindowFramebuffer getWindowFramebuffer() {
      return windowFramebuffer;
   }

   public final void setWindowFramebuffer(@Nullable WindowFramebuffer <set-?>) {
      windowFramebuffer = <set-?>;
   }

   public final void renderEntityOutline(@NotNull MatrixStack stack, @NotNull Entity target) {
      Intrinsics.checkNotNullParameter(stack, "stack");
      Intrinsics.checkNotNullParameter(target, "target");
      Camera var10000 = Globals.getMc().gameRenderer.getCamera();
      Intrinsics.checkNotNullExpressionValue(var10000, "getMainCamera(...)");
      Camera var11 = var10000;
      this.ensureFramebuffer();
      double var12 = RenderState.INSTANCE.getTickDelta();
      double var14 = MathHelper.lerp(var12, target.lastRenderX, target.getX()) - var11.getCameraPos().x;
      double var16 = MathHelper.lerp(var12, target.lastRenderY, target.getY()) - var11.getCameraPos().y;
      double var18 = MathHelper.lerp(var12, target.lastRenderZ, target.getZ()) - var11.getCameraPos().z;
      G = true;
      var10000 = Globals.getMc().options.getEntityShadows().getValue();
      Intrinsics.checkNotNullExpressionValue(var10000, "get(...)");
      boolean var20 = (Boolean)var10000;
      Globals.getMc().options.getEntityShadows().setValue(false);
      RenderSystem.backupProjectionMatrix();
      GameRenderer var24 = Globals.getMc().gameRenderer;
      Intrinsics.checkNotNull(var24, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
      RenderSystem.setProjectionMatrix(
         ((GameRendererAccessor)var24).getLevelProjectionMatrixBuffer().set(RenderState.INSTANCE.getProjection()), RenderSystem.getProjectionType()
      );
      GofraState.INSTANCE.setModifyBuffer(true);
      EntityRenderState var25 = Globals.getMc()
         .getEntityRenderDispatcher()
         .getRenderer(target)
         .getAndUpdateRenderState(target, RenderState.INSTANCE.getTickDelta());
      Intrinsics.checkNotNullExpressionValue(var25, "createRenderState(...)");
      EntityRenderState var21 = var25;
      GameRenderer var26 = Globals.getMc().gameRenderer;
      Intrinsics.checkNotNull(var26, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
      CameraRenderState var27 = ((GameRendererAccessor)var26).getLevelRenderState().cameraRenderState;
      Intrinsics.checkNotNullExpressionValue(var27, "cameraRenderState");
      CameraRenderState var22 = var27;
      Globals.getMc().getEntityRenderDispatcher().render(var21, var22, var14, var16, var18, stack, Globals.getMc().gameRenderer.getEntityRenderCommandQueue());
      Globals.getMc().gameRenderer.getEntityRenderDispatcher().render();
      Globals.getMc().getBufferBuilders().getEntityVertexConsumers().draw();
      Globals.getMc().gameRenderer.getEntityRenderCommandQueue().clear();
      GofraState.INSTANCE.setModifyBuffer(false);
      Globals.getMc().options.getEntityShadows().setValue(var20);
      RenderSystem.restoreProjectionMatrix();
      G = false;
   }

   public final void beginCapture(long var1, int var3) {
      long var4 = (var1 << 32 | (long)var3 << 32 >>> 32) ^ a;
      long var6 = var4 ^ 75707709163054L;
      if (windowFramebuffer != null) {
         G = true;
         GofraState.INSTANCE.setModifyBuffer(true);
         HandChams var10000 = HandChams.INSTANCE;
         Color var10002 = RenderUtil.INSTANCE.withAlpha(Theme.INSTANCE.getGradientColor(0), 0.4F);
         WindowFramebuffer var10003 = windowFramebuffer;
         Intrinsics.checkNotNull(windowFramebuffer);
         WindowFramebuffer var10004 = windowFramebuffer;
         Intrinsics.checkNotNull(windowFramebuffer);
         Color var10012 = Color.WHITE;
         Intrinsics.checkNotNullExpressionValue(Color.WHITE, "WHITE");
         Color var10013 = Color.WHITE;
         Intrinsics.checkNotNullExpressionValue(Color.WHITE, "WHITE");
         var10000.d(
            null, var10002, var10003, var10004, false, false, 0.0F, 0.0F, 0.0F, 4.0F, 0, var10012, var10013, 0, var6, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F
         );
         GofraState.INSTANCE.setModifyBuffer(false);
         G = false;
      }
   }

   public final void renderEntityGlow(@NotNull MatrixStack matrix, long a, @NotNull Entity target, char a, float alpha) {
      long var7 = (axx << 16 | (long)ax << 48 >>> 48) ^ a;
      long var22 = var7 ^ 121582378581551L;
      int var10000 = ZkmReferences.zkmBootstrap<"Å">(3026311926383827274L, var7);
      Intrinsics.checkNotNullParameter(matrix, zkmBootstrap<"r">(24804, 6736600770572627772L ^ var7));
      Intrinsics.checkNotNullParameter(target, zkmBootstrap<"r">(26097, 3924183194201008686L ^ var7));
      Camera var10001 = Globals.getMc().gameRenderer.getCamera();
      Intrinsics.checkNotNullExpressionValue(var10001, zkmBootstrap<"r">(17130, 2271884579943451967L ^ var7));
      Camera var41 = var10001;
      GpuTextureView var74 = Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView();
      Intrinsics.checkNotNullExpressionValue(var74, zkmBootstrap<"r">(22227, 152897602152012057L ^ var7));
      GpuTextureView var42 = var74;
      double var43 = RenderUtil.INSTANCE.lerp(target.lastX, target.getX(), (double)RenderState.INSTANCE.getTickDelta()) - var41.getCameraPos().x;
      double var45 = RenderUtil.INSTANCE.lerp(target.lastY, target.getY(), (double)RenderState.INSTANCE.getTickDelta()) - var41.getCameraPos().y;
      double var47 = RenderUtil.INSTANCE.lerp(target.lastZ, target.getZ(), (double)RenderState.INSTANCE.getTickDelta()) - var41.getCameraPos().z;
      double var49 = RenderUtil.INSTANCE.lerp(target.age - 1.0, (double)target.age, (double)RenderState.INSTANCE.getTickDelta());
      VertexFormat var10003 = VertexFormats.POSITION_TEXTURE_COLOR;
      Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_TEXTURE_COLOR, zkmBootstrap<"r">(13953, 4405351946196023637L ^ var7));
      int var10004 = zkmBootstrap2<"l">(19123, 1958165467887754636L ^ var7);
      Object var31 = null;
      int var32 = 4;
      boolean var33 = false;
      int var34 = var10004;
      VertexFormat var35 = var10003;
      BatchedBuffer var51 = new BatchedBuffer(var35, var34, var33, var32, (DefaultConstructorMarker)var31);
      int var40 = var10000;
      boolean var52 = Globals.getPlayer().canSee(target);
      int var53 = 0;

      while (true) {
         if (var53 < 3) {
            if (var40 == 0) {
               break;
            }

            int var54 = 0;

            label39: {
               label38:
               while (var54 < zkmBootstrap2<"l">(16677, 8470590394551260688L ^ var7)) {
                  double var55 = Math.toRadians(
                     (
                           (var54 / 1.5F + var49) * zkmBootstrap2<"l">(20552, 7701562082753267578L ^ var7)
                              + var53 * zkmBootstrap2<"l">(14542, 7446239520383086584L ^ var7)
                        )
                        % zkmBootstrap2<"l">(11829, 4685577712596760838L ^ var7)
                  );
                  double var57 = Math.sin(Math.toRadians(var49 * 2.5 + var54 * (var53 + 1)) * 3.0) / 1.8F;
                  float var59 = (float)var54 / zkmBootstrap2<"l">(23221, 2000827166886186376L ^ var7);
                  MatrixStack var60 = new MatrixStack();
                  var60.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(var41.getPitch()));
                  var60.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(var41.getYaw() + 180.0F));
                  var60.translate(var43 + Math.cos(var55) * target.getWidth(), var45 + 1.0 + var57, var47 + Math.sin(var55) * target.getWidth());
                  var60.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-var41.getYaw()));
                  var60.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(var41.getPitch()));
                  Matrix4f var72 = var60.peek().getPositionMatrix();
                  Intrinsics.checkNotNullExpressionValue(var72, zkmBootstrap<"r">(8915, 5209173114654452992L ^ var7));
                  Matrix4f var61 = var72;
                  RenderUtil var73 = RenderUtil.INSTANCE;
                  Color var75 = Theme.INSTANCE.getGradientColor((int)(zkmBootstrap2<"l">(24834, 8300818092314752574L ^ var7) * var59));
                  float var66 = var59 * alpha;
                  Color var62 = var73.withAlpha(var75, var66);
                  float var63 = (float)Math.max((double)(0.24F * var59), 0.2);
                  ParticleTexture var64 = ParticleTexture.FIREFLY;
                  var51.vertex(var61, -var63, var63, 0.0F).texture(var64.o(), var64.w()).color(var62);
                  var51.vertex(var61, var63, var63, 0.0F).texture(var64.L(), var64.w()).color(var62);
                  var51.vertex(var61, var63, -var63, 0.0F).texture(var64.L(), var64.v()).color(var62);
                  var51.vertex(var61, -var63, -var63, 0.0F).texture(var64.o(), var64.v()).color(var62);
                  var54++;

                  while (true) {
                     if (var40 == 0) {
                        break label39;
                     }

                     if (var40 != 0) {
                        break;
                     }

                     if (ax > 0) {
                        break label38;
                     }
                  }
               }

               var53++;
            }

            if (var40 != 0) {
               continue;
            }
         }

         matrix.push();
         matrix.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-var41.getYaw() + 180.0F));
         matrix.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-var41.getPitch()));
         break;
      }

      RenderPipeline var76 = var52
         ? Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlendDepthtest()
         : Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend();
      Matrix4f var77 = matrix.peek().getPositionMatrix();
      Map var10006 = MapsKt.mapOf(TuplesKt.to(zkmBootstrap<"r">(14250, 1054804545536161914L ^ var7), var42));
      int var10007 = zkmBootstrap2<"l">(19373, 2625840902639377562L ^ var7);
      var31 = null;
      var32 = var10007;
      Map var69 = var10006;
      Object var70 = null;
      Matrix4f var71 = var77;
      Object var36 = null;
      Object var37 = null;
      RenderPipeline var38 = var76;
      BatchedBuffer.draw$default(var22, var51, var38, (Framebuffer)var37, (GpuBufferSlice)var36, var71, (Matrix3x2f)var70, var69, var32, var31);
      matrix.pop();
   }

   public static void renderEntityGlow$default(
      MainFramebuffer var0, MatrixStack var1, Entity var2, float var3, int var4, char var5, int var6, short var7, Object var8
   ) {
      long var9 = ((long)var5 << 48 | (long)var6 << 32 >>> 16 | (long)var7 << 48 >>> 48) ^ a;
      long var11 = (var9 ^ 76158855950356L) >>> 16;
      int var13 = (int)((var9 ^ 76158855950356L) << 48 >>> 48);
      if ((var4 & 4) != 0) {
         var3 = 1.0F;
      }

      var0.renderEntityGlow(var1, var11, var2, (char)var13, var3);
   }

   public final void renderEntityChams(@NotNull MatrixStack matrix, int a, char a, @NotNull Entity target, char a, float alpha) {
      long var7 = ((long)ax << 32 | (long)axxx << 48 >>> 32 | (long)axx << 48 >>> 48) ^ a;
      long var22 = var7 ^ 24967321675806L;
      Intrinsics.checkNotNullParameter(matrix, zkmBootstrap<"r">(29703, 5674939938276453887L ^ var7));
      Intrinsics.checkNotNullParameter(target, zkmBootstrap<"r">(26097, 3924051394235087903L ^ var7));
      int var10000 = ZkmReferences.zkmBootstrap<"Å">(-4625506293232676997L, var7);
      Camera var10001 = Globals.getMc().gameRenderer.getCamera();
      Intrinsics.checkNotNullExpressionValue(var10001, zkmBootstrap<"r">(17130, 2271822866328394510L ^ var7));
      Camera var41 = var10001;
      int var40 = var10000;
      GpuTextureView var78 = Globals.getMc().getTextureManager().getTexture(GlowTexture.getIdentifier()).getGlTextureView();
      Intrinsics.checkNotNullExpressionValue(var78, zkmBootstrap<"r">(10391, 261223814623031662L ^ var7));
      GpuTextureView var42 = var78;
      double var43 = RenderUtil.INSTANCE.lerp(target.lastX, target.getX(), (double)RenderState.INSTANCE.getTickDelta()) - var41.getCameraPos().x;
      double var45 = RenderUtil.INSTANCE.lerp(target.lastY, target.getY(), (double)RenderState.INSTANCE.getTickDelta()) - var41.getCameraPos().y;
      double var47 = RenderUtil.INSTANCE.lerp(target.lastZ, target.getZ(), (double)RenderState.INSTANCE.getTickDelta()) - var41.getCameraPos().z;
      double var49 = RenderUtil.INSTANCE.lerp(target.age - 1.0, (double)target.age, (double)RenderState.INSTANCE.getTickDelta());
      VertexFormat var10002 = VertexFormats.POSITION_TEXTURE_COLOR;
      Intrinsics.checkNotNullExpressionValue(VertexFormats.POSITION_TEXTURE_COLOR, zkmBootstrap<"r">(23253, 1583939710510564153L ^ var7));
      int var10003 = zkmBootstrap2<"l">(19883, 4784586625732332708L ^ var7);
      Object var31 = null;
      int var32 = 4;
      boolean var33 = false;
      int var34 = var10003;
      VertexFormat var35 = var10002;
      BatchedBuffer var51 = new BatchedBuffer(var35, var34, var33, var32, (DefaultConstructorMarker)var31);
      boolean var52 = Globals.getPlayer().canSee(target);
      int var53 = zkmBootstrap2<"l">(17350, 5156721460518240973L ^ var7);
      int var54 = 0;

      while (true) {
         if (var54 < zkmBootstrap2<"l">(18189, 7431796430828226056L ^ var7)) {
            var10000 = var40;
            if (ax >= 0) {
               if (var40 == 0) {
                  break;
               }

               var10000 = 0;
            }

            int var55 = var10000;

            label56: {
               label55:
               while (true) {
                  if (var55 < var53) {
                     double var56 = (double)var55 / var53 * (Math.PI * 2);
                     double var58 = Math.cos(var56) * target.getWidth();
                     double var60 = Math.sin(var56) * target.getWidth();
                     double var62 = Math.sin(Math.toRadians((var49 - var54 / 2.0F) * 2.5) * 3.0) / 1.2F;
                     float var64 = (float)var55 / (var53 + 1);
                     MatrixStack var65 = new MatrixStack();
                     var65.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(var41.getPitch()));
                     var65.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(var41.getYaw() + 180.0F));
                     var65.translate(var43 + var58, var45 + 1.0 + var62, var47 + var60);
                     var65.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-var41.getYaw()));
                     var65.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(var41.getPitch()));
                     Matrix4f var80 = var65.peek().getPositionMatrix();
                     Intrinsics.checkNotNullExpressionValue(var80, zkmBootstrap<"r">(32561, 8026952425980798683L ^ var7));
                     Matrix4f var66 = var80;
                     RenderUtil var81 = RenderUtil.INSTANCE;
                     Color var83 = Theme.INSTANCE.getGradientColor((int)(zkmBootstrap2<"l">(20040, 330989982154968905L ^ var7) * var64));
                     float var71 = alpha / (var54 + 1);
                     Color var67 = var81.withAlpha(var83, var71);
                     float var68 = 0.12F;
                     ParticleTexture var69 = ParticleTexture.FIREFLY;
                     var51.vertex(var66, -0.12F, var68, 0.0F).texture(var69.o(), var69.w()).color(var67);
                     var51.vertex(var66, var68, var68, 0.0F).texture(var69.L(), var69.w()).color(var67);
                     var51.vertex(var66, var68, -0.12F, 0.0F).texture(var69.L(), var69.v()).color(var67);
                     var51.vertex(var66, -0.12F, -0.12F, 0.0F).texture(var69.o(), var69.v()).color(var67);
                     var55++;

                     do {
                        var10000 = var40;
                        if (axxx <= 0) {
                           break label56;
                        }

                        if (var40 == 0) {
                           break label55;
                        }

                        if (var40 != 0) {
                           continue label55;
                        }
                     } while (axx <= 0);
                  }

                  var54++;
                  break;
               }

               var10000 = var40;
            }

            if (var10000 != 0) {
               continue;
            }
         }

         matrix.push();
         matrix.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(-var41.getYaw() + 180.0F));
         matrix.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(-var41.getPitch()));
         if (axx >= 0) {
         }
         break;
      }

      RenderPipeline var84 = var52
         ? Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlendDepthtest()
         : Pipelines.INSTANCE.getProgramDefaultColoredTextureAltBlend();
      Matrix4f var10004 = matrix.peek().getPositionMatrix();
      Map var10006 = MapsKt.mapOf(TuplesKt.to(zkmBootstrap<"r">(9781, 8674132778255122392L ^ var7), var42));
      int var10007 = zkmBootstrap2<"l">(25296, 6415916483485131728L ^ var7);
      var31 = null;
      var32 = var10007;
      Map var74 = var10006;
      Object var75 = null;
      Matrix4f var76 = var10004;
      Object var36 = null;
      Object var37 = null;
      RenderPipeline var38 = var84;
      BatchedBuffer.draw$default(var22, var51, var38, (Framebuffer)var37, (GpuBufferSlice)var36, var76, (Matrix3x2f)var75, var74, var32, var31);
      matrix.pop();
      if (ax >= 0 && ZkmReferences.zkmBootstrap<"Å">(-4676828161143692919L, var7) != null) {
         ZkmReferences.zkmBootstrap<"Å">(++var40, -4704855356402646283L, var7);
      }
   }

   public static void renderEntityChams$default(MainFramebuffer var0, long var1, MatrixStack var3, Entity var4, float var5, int var6, Object var7) {
      var1 = a ^ var1;
      long var10001 = var1 ^ 8713413409112L;
      int var8 = (int)((var1 ^ 8713413409112L) >>> 32);
      int var9 = (int)((var1 ^ 8713413409112L) << 32 >>> 48);
      int var10 = (int)(var10001 << 48 >>> 48);
      if ((var6 & 4) != 0) {
         var5 = 1.0F;
      }

      var0.renderEntityChams(var3, var8, (char)var9, var4, (char)var10, var5);
   }

   private final void ensureFramebuffer() {
      if (windowFramebuffer == null) {
         windowFramebuffer = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      label17: {
         int var10000 = Globals.getMc().getFramebuffer().textureWidth;
         WindowFramebuffer var10001 = windowFramebuffer;
         Intrinsics.checkNotNull(windowFramebuffer);
         if (var10000 == var10001.textureWidth) {
            var10000 = Globals.getMc().getFramebuffer().textureHeight;
            var10001 = windowFramebuffer;
            Intrinsics.checkNotNull(windowFramebuffer);
            if (var10000 == var10001.textureHeight) {
               break label17;
            }
         }

         WindowFramebuffer var2 = windowFramebuffer;
         Intrinsics.checkNotNull(windowFramebuffer);
         var2.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      CommandEncoder var3 = RenderSystem.getDevice().createCommandEncoder();
      WindowFramebuffer var5 = windowFramebuffer;
      Intrinsics.checkNotNull(windowFramebuffer);
      GpuTexture var6 = var5.getColorAttachment();
      Intrinsics.checkNotNull(var6);
      WindowFramebuffer var10003 = windowFramebuffer;
      Intrinsics.checkNotNull(windowFramebuffer);
      GpuTexture var7 = var10003.getDepthAttachment();
      Intrinsics.checkNotNull(var7);
      var3.clearColorAndDepthTextures(var6, 0, var7, 1.0);
   }

   @Flow
   private final void onFrameBuffer(FrameBufferEvent var1) {
      if (windowFramebuffer != null && G) {
         var1.setFrameBuffer(windowFramebuffer);
         var1.cancel();
      }
   }

   static {
      long var11 = a ^ 98819290004390L;
      Cipher var13;
      Cipher var10000 = var13 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var14 = 1; var14 < 8; var14++) {
         var10003[var14] = (byte)(var11 << var14 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var20 = new String[22];
      int var18 = 0;
      String var17 = "Â-\u0001\u0004=Ú®36\u0086¨\u000f\u008cÌ\u0005\u008b ýM\u0006\u00adpÓXëS2¨\u00951\bËS\u008bè3}r\u0000\u0004Òãè&þ\u0012\fC\u008b\u0010Ößßãé7Ê9vÈ\u0087;\u0012\u009b4²(Ä8òp\u009b!¬0µ]¸/ÕmV\u0082ãí\u001e\u009b@\u000bVcyIæ\u001ck&VFÄ\u000f\"\u0083ó1\u001aZ(\u001b\u00984Ó=¢×Ú\u007fßÆÇà-©À\u001bf\u008c\u0088h9Er0`7ÑIÅÏ\u0081\u0012\u000f¸OË¶5Ô\u0010;Áæä\u0089Ä©Kïf²Ókù¤¦\u0018·\u000fR\u0088É´ñª8\u001dq¯É\u009a\u0096Ã\u0095°\u001bò\u0015ôqT(»<~Ä\u0082\u008c\nö@\u007f\u0002M\u008f\u0094\u007fH\u0091©áá(\u001aXk8bÝ\u0096¶Lvb!\u0098ÎÜ\u0014Ñë\u0088\u0010¨À\u008b\u008f\rFö\u0091/ZÂû¶\u0018\u001eæ M£»ýY(Q¶v>L\u0082ÖäêÑJãö¨´GMË\u009aï6Å/Ð\u008ck\u0018\u0093\u0091\\\b\u0095\u0092e\u007fâ\u0015j/{=q'åð¿\t\u001fÝa\u0007\u00105}\u0082æÂ\\^Ðï(¤\u000b\u0081!2ª\u0088dß ®Õ\"wJ\u001aP\\bã%6p\u000e\u009fo\u0087\u0080a$(\u009cGÅ©i\r]Ö\u009bþ\u008fSÚ\u0084\u0014ò\u001bGôÄH\u0018ØèÀÓ:\u0015r\u0083eÉÓ\u0007.ôY$\u0097ïHÊ(Ô\u0013r\u001cA\u009bç'<wÇYá\u0001ÅÜÐù»×+ 2\r\u0085\u0096Íñýî¦\u0085çÊ_c\u009f¯Ì\u0096õ\u0005g\u0096w^\u0090ï\u0090Ø\u008c§íÂbÎ\u0099\u009e\u0089\r\u0007trb\u0001\u001a\u0091\u0012\u000f 3\u0014\u0095m\u0095oE9:\u000fY\u0083ÞÅ\u0011ð\u008d~Áü\u009eQ\u0088\bù\u0092ïÓpv¶!(\u0015Úc<3ÐÌùä\t]%M4ãú\u0015e¯NÓ>9ôy\u008f,\u0086¾\u0019¤5wH1¢\u0098\r¾Ó(ðvç¤!¢ÈÉË\u009ar\u0087i\u0002\u000b¯&ì\u0012\u008cC\u009d{½\u008b§:ø9¤þ´ãèÔ\u001b±äÌ´(%¶\u0095\u0014¾S\r#\u0001É\u0098e,\u0000\u008d\u0098%(\u0003\bç\u0084\u0007yøf78P+\u0088ó¸Vjë¯8¹\u000e\u0010ÿäw\u0089\u0080H.\u0099Tº,>âëÈ\u001e0\u001e\u008cå\u008f¦\u009dV.¦ïèÕñ4ò&æÇ¡ \u0091YN4º\u0099\u001e¦m\u0014\u009b\u0098r0\b8Å\u008b£[+Ú=[\\D\u0002è\u0010×·\u009ea\u008c\u001aÌiQ\u0099Á\u001c[U\f\u0005";
      short var19 = 699;
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
                     b = var20;
                     c = new String[22];
                     g = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var11 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var11 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[13];
                     int var3 = 0;
                     String var4 = "\u0080v\u0085¦º©\u000ei\u009dÍb¯o®Û\u0084£Ê\u0095~d|v'\u0085ÚØä\u0083\u0093»Kw7\u000fá¸\u0003x\u009d¢þ\t\fí\u0088*°ûr¢\u000eú\u0088\u008d=Z\u008e°h[<j\u0011E\u009c\u0081o\b\u009aî\u0093<¥{óñ¹<#ªe¤\u0015`:Á\u0018";
                     byte var5 = 88;
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
                                    f = new Integer[13];
                                    INSTANCE = new MainFramebuffer();
                                    return;
                                 }
                                 break;
                              default:
                                 var28[var10001] = var45;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "/IG¦\u0081f7ÆjÇ\u0095º\u0002Íì1";
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

                  var17 = "\ry\u00adr¯jB.à\n\u0092C\u0090Ï)ySª¡U\u0001\u0006¤äPA\u0089\u009dc\u000e*\u0013H71¬\u0013\u000b%\u001aT7\u0003\u001bí\u0019HÇïÞµ/]\u0011ÄÍ¿\b\ru\u008f\u0011\u0007\u0005\u0003\u0086\u001e|\"gÕeix|'Éa\u001cËYP\\\u0096ÒUÑ\u001e_1³î\u0084\u0013\u0019¦@©\f\u0084\ng0Ù&ÓÔÓ$Zd\u0080\bBÓl\u0000¨íLF\u0092N\u000e=Ão\u0089\u0018¡j9Ý¸u½8æ\b\u0088ÕÁ\u0011\u001cÑy\u0087)*Ö¬\u001f\u0012";
                  var19 = 153;
                  var16 = 128;
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 26530;
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
            throw new RuntimeException("su/catlean/zw", var10);
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
         throw new RuntimeException("su/catlean/zw" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 16206;
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
            throw new RuntimeException("su/catlean/zw", var14);
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
         throw new RuntimeException("su/catlean/zw" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
