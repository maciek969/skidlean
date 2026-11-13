package su.catlean.module.cosmetic;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.ProjectionType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
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
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.WindowFramebuffer;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec2f;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2f;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;
import su.catlean.api.event.GofraState;
import su.catlean.api.event.events.render.FrameBufferEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.api.event.events.render.RenderHandEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.GameRendererAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.BatchedBuffer;
import su.catlean.render.BlurPass;
import su.catlean.render.ChamsColorMode;
import su.catlean.render.Pipelines;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.ShaderMode;
import su.catlean.render.uniform.ChamsUniformStorageA;
import su.catlean.render.uniform.ChamsUniformStorageB;
import su.catlean.render.uniform.ChamsUniformStorageC;
import su.catlean.render.uniform.ChamsUniformStorageD;
import su.catlean.render.uniform.ChamsUniformStorageE;
import su.catlean.render.uniform.ChamsUniforms;
import su.catlean.render.uniform.GlowUniforms;
import su.catlean.render.uniform.GradientUniforms;
import su.catlean.render.uniform.LineUniforms;
import su.catlean.render.uniform.OutlineUniforms;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class HandChams extends Module {
   @NotNull
   public static final HandChams INSTANCE;
   static final KProperty[] delegatedProperties;
   @NotNull
   private static final ModeSetting handsMode$delegate;
   @NotNull
   private static final ColorSetting handsColor$delegate;
   @NotNull
   private static final BooleanSetting glass$delegate;
   @NotNull
   private static final FloatSetting alpha$delegate;
   @NotNull
   private static final BooleanSetting borders$delegate;
   @NotNull
   private static final FloatSetting radiusValue$delegate;
   @NotNull
   private static final FloatSetting distanceValue$delegate;
   @NotNull
   private static final FloatSetting glowAlphaValue$delegate;
   @NotNull
   private static final FloatSetting lineWithValue$delegate;
   @NotNull
   private static final ModeSetting colorModeValue$delegate;
   @NotNull
   private static final ColorSetting secondColor$delegate;
   @NotNull
   private static final IntSetting glowQualityValue$delegate;
   @NotNull
   private static final FloatSetting glowRadiusValue$delegate;
   @NotNull
   private static final FloatSetting glowAlphaMultValue$delegate;
   @NotNull
   private static final FloatSetting fillAlphaMultValue$delegate;
   @NotNull
   private static final FloatSetting saturation$delegate;
   @NotNull
   private static final FloatSetting brightness$delegate;
   @Nullable
   private static WindowFramebuffer w;
   @Nullable
   private static WindowFramebuffer y;
   @Nullable
   private static WindowFramebuffer B;
   private static boolean D;
   private static boolean k;
   @NotNull
   private static final float[] u;
   private static int A;
   private static int l;
   private static final long b = ZkmKeys.a(5320219153522008638L, 4081742540418562671L, MethodHandles.lookup().lookupClass()).a(40106061994746L);
   private static final String[] c;
   private static final String[] e;
   private static final Map g = new HashMap(13);
   private static final long[] i;
   private static final Integer[] m;
   private static final Map C;

   private HandChams() {
      super("hand-chams", Categories.getCosmetic(), null, null);
   }

   private final ShaderMode getHandsMode() {
      return (ShaderMode)handsMode$delegate.getValue(this, delegatedProperties[0]);
   }

   private final void setHandsMode(ShaderMode var1) {
      handsMode$delegate.setValue(this, delegatedProperties[0], var1);
   }

   private final Color getHandsColor() {
      return (Color)handsColor$delegate.getValue(this, delegatedProperties[1]);
   }

   private final void setHandsColor(Color var1) {
      handsColor$delegate.setValue(this, delegatedProperties[1], var1);
   }

   private final boolean getGlass() {
      return (Boolean)glass$delegate.getValue(this, delegatedProperties[2]);
   }

   private final void setGlass(boolean var1) {
      glass$delegate.setValue(this, delegatedProperties[2], var1);
   }

   private final float getAlpha() {
      return ((Number)alpha$delegate.getValue(this, delegatedProperties[3])).floatValue();
   }

   private final void setAlpha(float var1) {
      alpha$delegate.setValue(this, delegatedProperties[3], var1);
   }

   private final boolean getBorders() {
      return (Boolean)borders$delegate.getValue(this, delegatedProperties[4]);
   }

   private final void setBorders(boolean var1) {
      borders$delegate.setValue(this, delegatedProperties[4], var1);
   }

   private final float getRadiusValue() {
      return ((Number)radiusValue$delegate.getValue(this, delegatedProperties[5])).floatValue();
   }

   private final void setRadiusValue(float var1) {
      radiusValue$delegate.setValue(this, delegatedProperties[5], var1);
   }

   private final float getDistanceValue() {
      return ((Number)distanceValue$delegate.getValue(this, delegatedProperties[6])).floatValue();
   }

   private final void setDistanceValue(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      distanceValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(5451, 8244694153846666708L ^ var4)], var1);
   }

   private final float getGlowAlphaValue() {
      return ((Number)glowAlphaValue$delegate.getValue(this, delegatedProperties[7])).floatValue();
   }

   private final void setGlowAlphaValue(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      glowAlphaValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(9004, 7691049156592729380L ^ var4)], var1);
   }

   private final float getLineWithValue() {
      return ((Number)lineWithValue$delegate.getValue(this, delegatedProperties[8])).floatValue();
   }

   private final void setLineWithValue(float var1) {
      byte var6 = 0;
      byte var7 = 0;
      byte var8 = 0;
      long var2 = ((long)var6 << 32 | (long)var7 << 48 >>> 32 | (long)var8 << 48 >>> 48) ^ b;
      lineWithValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(29922, 7302929648029968672L ^ var2)], var1);
   }

   private final ChamsColorMode getColorModeValue() {
      return (ChamsColorMode)colorModeValue$delegate.getValue(this, delegatedProperties[9]);
   }

   private final void setColorModeValue(ChamsColorMode var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      colorModeValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(12045, 2929413059809106610L ^ var4)], var1);
   }

   private final Color getSecondColor() {
      return (Color)secondColor$delegate.getValue(this, delegatedProperties[10]);
   }

   private final void setSecondColor(Color var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      secondColor$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(32767, 8269364633314136259L ^ var4)], var1);
   }

   private final int getGlowQualityValue() {
      return ((Number)glowQualityValue$delegate.getValue(this, delegatedProperties[11])).intValue();
   }

   private final void setGlowQualityValue(int var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      glowQualityValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(14926, 3913871499464789743L ^ var4)], var1);
   }

   private final float getGlowRadiusValue() {
      return ((Number)glowRadiusValue$delegate.getValue(this, delegatedProperties[12])).floatValue();
   }

   private final void setGlowRadiusValue(float var1) {
      byte var6 = 0;
      byte var7 = 0;
      byte var8 = 0;
      long var2 = ((long)var6 << 48 | (long)var7 << 32 >>> 16 | (long)var8 << 48 >>> 48) ^ b;
      glowRadiusValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(7851, 9051401301616369193L ^ var2)], var1);
   }

   private final float getGlowAlphaMultValue() {
      return ((Number)glowAlphaMultValue$delegate.getValue(this, delegatedProperties[13])).floatValue();
   }

   private final void setGlowAlphaMultValue(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      glowAlphaMultValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(24975, 2394987992215424016L ^ var4)], var1);
   }

   private final float getFillAlphaMultValue() {
      return ((Number)fillAlphaMultValue$delegate.getValue(this, delegatedProperties[14])).floatValue();
   }

   private final void setFillAlphaMultValue(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      fillAlphaMultValue$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(19398, 3820017301055777141L ^ var4)], var1);
   }

   private final float getSaturation() {
      return ((Number)saturation$delegate.getValue(this, delegatedProperties[15])).floatValue();
   }

   private final void setSaturation(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      saturation$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(6921, 4480613607323372006L ^ var4)], var1);
   }

   private final float getBrightness() {
      return ((Number)brightness$delegate.getValue(this, delegatedProperties[16])).floatValue();
   }

   private final void setBrightness(float var1) {
      long var4 = 0L;
      var4 = b ^ var4;
      brightness$delegate.setValue(this, delegatedProperties[zkmBootstrap2<"n">(18742, 6125391840855747478L ^ var4)], var1);
   }

   @Flow
   private final void onFrameBuffer(FrameBufferEvent var1) {
      int var4 = ShaderMode.zkmIntConstant();
      if (var4 == 0) {
         if (!k) {
            return;
         }

         var1.setFrameBuffer(w);
      }

      var1.cancel();
   }

   @Flow(
      priority = -10
   )
   private final void onRenderHand(RenderHandEvent var1) {
      label16: {
         int var4 = ShaderMode.zkmIntConstant();
         boolean var10000 = k;
         if (var4 == 0) {
            if (k) {
               break label16;
            }

            var10000 = D;
         }

         if (!var10000) {
            return;
         }
      }

      var1.cancel();
   }

   @Flow(
      priority = -10
   )
   private final void onRender2D(Render2DEvent var1) {
      long var2 = b ^ 51202198518773L;
      long var13 = var2 ^ 125690318109841L;
      if (w != null && y != null && B != null) {
         GofraState.INSTANCE.setModifyBuffer(true);
         k = true;
         ShaderMode var10001 = this.getHandsMode();
         Color var10002 = this.getHandsColor();
         WindowFramebuffer var10003 = w;
         Intrinsics.checkNotNull(w);
         WindowFramebuffer var10004 = y;
         Intrinsics.checkNotNull(y);
         this.d(
            var10001,
            var10002,
            var10003,
            var10004,
            this.getGlass(),
            this.getBorders(),
            this.getRadiusValue(),
            this.getDistanceValue(),
            this.getGlowAlphaValue(),
            this.getLineWithValue(),
            this.getColorModeValue().ordinal(),
            this.getHandsColor(),
            this.getSecondColor(),
            this.getGlowQualityValue(),
            var13,
            this.getGlowRadiusValue(),
            this.getGlowAlphaMultValue(),
            this.getFillAlphaMultValue(),
            this.getSaturation(),
            this.getBrightness(),
            this.getAlpha()
         );
         k = false;
         GofraState.INSTANCE.setModifyBuffer(false);
      }
   }

   @Flow(
      priority = -10
   )
   public final void onRender3D(@NotNull Render3DEvent event) {
      Intrinsics.checkNotNullParameter(event, "event");
      GofraState.INSTANCE.setModifyBuffer(true);
      ShaderMode.zkmIntConstant();
      k = true;
      this.n();
      MatrixStack var12 = event.getStack();
      this.X(var12);
      k = false;
      GofraState.INSTANCE.setModifyBuffer(false);
   }

   public final void d(
      @Nullable ShaderMode mode,
      @NotNull Color color,
      @NotNull WindowFramebuffer frameBuffer,
      @NotNull WindowFramebuffer secondary,
      boolean glass,
      boolean borders,
      float radiusValue,
      float distanceValue,
      float glowAlphaValue,
      float lineWithValue,
      int colorModev,
      @NotNull Color color1v,
      @NotNull Color color2v,
      int glowQualityV,
      long a,
      float glowRadiusV,
      float glowAlphaMultV,
      float fillAlphaMultV,
      float saturationV,
      float brightnessV,
      float alpha
   ) {
      a = b ^ a;
      long var23 = a ^ 113938497400140L;
      long var25 = a ^ 74926566457762L;
      long var27 = a ^ 30891499889584L;
      long var29 = a ^ 71610349058647L;
      long var31 = a ^ 88001217547906L;
      Intrinsics.checkNotNullParameter(color, "color");
      Intrinsics.checkNotNullParameter(frameBuffer, "frameBuffer");
      Intrinsics.checkNotNullParameter(secondary, "secondary");
      Intrinsics.checkNotNullParameter(color1v, "color1v");
      Intrinsics.checkNotNullParameter(color2v, "color2v");
      switch (mode == null ? -1 : HandChams$WhenMappings.K[mode.ordinal()]) {
         case 1:
            this.b(frameBuffer, color, borders, glass, alpha, var25);
            break;
         case 2:
            this.j(color, lineWithValue, frameBuffer, var29);
            break;
         case 3:
            this.B(var31, color, frameBuffer, secondary, radiusValue, distanceValue, glowAlphaValue, lineWithValue);
            break;
         case 4:
            this.o(colorModev, var27, color1v, color2v, glowQualityV, glowRadiusV, glowAlphaMultV, fillAlphaMultV, saturationV, brightnessV, frameBuffer);
            break;
         case 5:
            Color var10004 = color1v.darker().darker();
            Intrinsics.checkNotNullExpressionValue(var10004, "darker(...)");
            Color var10005 = color2v.darker().darker();
            Intrinsics.checkNotNullExpressionValue(var10005, "darker(...)");
            this.F(color1v, var23, color2v, var10004, var10005, lineWithValue, frameBuffer);
            break;
         default:
            this.l(color, frameBuffer);
      }
   }

   private final void l(Color var1, WindowFramebuffer var2) {
   }

   private final void B(long var1, Color var3, WindowFramebuffer var4, WindowFramebuffer var5, float var6, float var7, float var8, float var9) {
      var1 = b ^ var1;
      long var14 = var1 ^ 15017602736926L;
      long var18 = var1 ^ 8616333166714L;
      this.j(var3, var9, var4, var18);
      int var23 = (int)var6;
      if (A != var23) {
         for (int var25 = var23; -1 < var25; var25--) {
            u[var25] = this.W(var25);
         }

         A = var23;
      }

      Matrix4f var28 = new Matrix4f().set(u[0], u[1], u[2], u[3], u[4], u[5], u[6], u[7], u[8], u[9], u[10], u[11], u[12], u[13], u[14], u[15]);
      BatchedBuffer var10001 = new BatchedBuffer(null, 0, false, 7, null).fullscreenQuad();
      RenderPipeline var10002 = Pipelines.INSTANCE.getProgramPostBloom();
      Framebuffer var10003 = var5;
      DynamicUniformStorage var10004 = ChamsUniformStorageA.INSTANCE.getDynamicUniformStorage();
      Vec2f var10007 = new Vec2f(1.0F / Globals.getMc().getWindow().getScaledWidth(), 1.0F / Globals.getMc().getWindow().getScaledHeight());
      Vec2f var10008 = new Vec2f(var7, 0.0F);
      float var10009 = var23;
      Intrinsics.checkNotNull(var28);
      GpuBufferSlice var32 = var10004.write(new GlowUniforms(var10007, var10008, var10009, var8, var28, var3));
      GpuTextureView var34 = var4.getColorAttachmentView();
      Intrinsics.checkNotNull(var34);
      BatchedBuffer.draw$default(var14, var10001, var10002, var10003, var32, null, null, MapsKt.mapOf(TuplesKt.to("Sampler1", var34)), 24, null);
      var10001 = new BatchedBuffer(null, 0, false, 7, null).fullscreenQuad();
      var10002 = Pipelines.INSTANCE.getProgramPostBloom();
      var10003 = Globals.getMc().getFramebuffer();
      Intrinsics.checkNotNullExpressionValue(var10003, "getMainRenderTarget(...)");
      GpuBufferSlice var33 = ChamsUniformStorageA.INSTANCE
         .getDynamicUniformStorage()
         .write(
            new GlowUniforms(
               new Vec2f(1.0F / Globals.getMc().getWindow().getScaledWidth(), 1.0F / Globals.getMc().getWindow().getScaledHeight()),
               new Vec2f(0.0F, var7),
               var23,
               var8,
               var28,
               var3
            )
         );
      Pair[] var26 = new Pair[2];
      GpuTextureView var10010 = var4.getColorAttachmentView();
      Intrinsics.checkNotNull(var10010);
      var26[0] = TuplesKt.to("Sampler0", var10010);
      var10010 = var5.getColorAttachmentView();
      Intrinsics.checkNotNull(var10010);
      var26[1] = TuplesKt.to("Sampler1", var10010);
      BatchedBuffer.draw$default(var14, var10001, var10002, var10003, var33, null, null, MapsKt.mapOf(var26), 24, null);
   }

   private final void n() {
      if (w == null) {
         w = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      label38: {
         int var10000 = Globals.getMc().getFramebuffer().textureWidth;
         WindowFramebuffer var10001 = w;
         Intrinsics.checkNotNull(w);
         if (var10000 == var10001.textureWidth) {
            var10000 = Globals.getMc().getFramebuffer().textureHeight;
            var10001 = w;
            Intrinsics.checkNotNull(w);
            if (var10000 == var10001.textureHeight) {
               break label38;
            }
         }

         WindowFramebuffer var8 = w;
         Intrinsics.checkNotNull(w);
         var8.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      CommandEncoder var9 = RenderSystem.getDevice().createCommandEncoder();
      Intrinsics.checkNotNullExpressionValue(var9, "createCommandEncoder(...)");
      CommandEncoder var6 = var9;
      WindowFramebuffer var17 = w;
      Intrinsics.checkNotNull(w);
      GpuTexture var18 = var17.getColorAttachment();
      Intrinsics.checkNotNull(var18);
      WindowFramebuffer var10003 = w;
      Intrinsics.checkNotNull(w);
      GpuTexture var27 = var10003.getDepthAttachment();
      Intrinsics.checkNotNull(var27);
      var6.clearColorAndDepthTextures(var18, 0, var27, 1.0);
      if (y == null) {
         y = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      label32: {
         int var10 = Globals.getMc().getFramebuffer().textureWidth;
         var17 = y;
         Intrinsics.checkNotNull(y);
         if (var10 == var17.textureWidth) {
            int var11 = Globals.getMc().getFramebuffer().textureHeight;
            var17 = y;
            Intrinsics.checkNotNull(y);
            if (var11 == var17.textureHeight) {
               break label32;
            }
         }

         WindowFramebuffer var12 = y;
         Intrinsics.checkNotNull(y);
         var12.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      var17 = y;
      Intrinsics.checkNotNull(y);
      GpuTexture var22 = var17.getColorAttachment();
      Intrinsics.checkNotNull(var22);
      var10003 = y;
      Intrinsics.checkNotNull(y);
      GpuTexture var29 = var10003.getDepthAttachment();
      Intrinsics.checkNotNull(var29);
      var6.clearColorAndDepthTextures(var22, 0, var29, 1.0);
      if (B == null) {
         B = new WindowFramebuffer(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      label26: {
         int var13 = Globals.getMc().getFramebuffer().textureWidth;
         var17 = B;
         Intrinsics.checkNotNull(B);
         if (var13 == var17.textureWidth) {
            int var14 = Globals.getMc().getFramebuffer().textureHeight;
            var17 = B;
            Intrinsics.checkNotNull(B);
            if (var14 == var17.textureHeight) {
               break label26;
            }
         }

         WindowFramebuffer var15 = B;
         Intrinsics.checkNotNull(B);
         var15.resize(Globals.getMc().getFramebuffer().textureWidth, Globals.getMc().getFramebuffer().textureHeight);
      }

      var17 = B;
      Intrinsics.checkNotNull(B);
      GpuTexture var26 = var17.getColorAttachment();
      Intrinsics.checkNotNull(var26);
      var10003 = B;
      Intrinsics.checkNotNull(B);
      GpuTexture var31 = var10003.getDepthAttachment();
      Intrinsics.checkNotNull(var31);
      var6.clearColorAndDepthTextures(var26, 0, var31, 1.0);
   }

   private final void X(MatrixStack var1) {
      int var10000 = ShaderMode.zkmIntConstant();
      RenderSystem.backupProjectionMatrix();
      int var14 = var10000;
      boolean var15 = false;
      if (var14 == 0) {
         D = false;
         Camera var10001 = Globals.getMc().gameRenderer.getCamera();
         Intrinsics.checkNotNullExpressionValue(var10001, "getMainCamera(...)");
         float var10002 = RenderState.INSTANCE.getTickDelta();
         Matrix4f var10003 = var1.peek().getPositionMatrix();
         Intrinsics.checkNotNullExpressionValue(var10003, "pose(...)");
         this.v(var10001, var10002, var10003);
         var15 = this.getHandsMode() == ShaderMode.MIRROR;
      }

      D = var15;
      RenderSystem.restoreProjectionMatrix();
   }

   private final void o(
      int var1, long var2, Color var4, Color var5, int var6, float var7, float var8, float var9, float var10, float var11, WindowFramebuffer var12
   ) {
      var2 = b ^ var2;
      long var17 = var2 ^ 72265296186924L;
      BatchedBuffer var10001 = new BatchedBuffer(null, 0, false, 7, null).fullscreenQuad();
      RenderPipeline var10002 = Pipelines.INSTANCE.getProgramPostDoubleglow();
      Framebuffer var10003 = Globals.getMc().getFramebuffer();
      Intrinsics.checkNotNullExpressionValue(var10003, "getMainRenderTarget(...)");
      GpuBufferSlice var10004 = ChamsUniformStorageE.INSTANCE
         .getDynamicUniformStorage()
         .write(
            new ChamsUniforms(
               Math.abs(RenderUtil.INSTANCE.lerp((Globals.getPlayer().age - 1) / 6.0F, Globals.getPlayer().age / 6.0F, RenderUtil.INSTANCE.getTickDelta())),
               var4,
               var5,
               var1,
               var6,
               var7,
               var8,
               1,
               var9,
               new Vec2f(1.0F / var12.textureWidth, 1.0F / var12.textureHeight),
               var10,
               var11
            )
         );
      GpuTextureView var10008 = var12.getColorAttachmentView();
      Intrinsics.checkNotNull(var10008);
      BatchedBuffer.draw$default(var17, var10001, var10002, var10003, var10004, null, null, MapsKt.mapOf(TuplesKt.to("Sampler0", var10008)), 24, null);
   }

   private final void j(Color var1, float var2, WindowFramebuffer var3, long var4) {
      var4 = b ^ var4;
      long var10 = var4 ^ 31340288628683L;
      BatchedBuffer var10001 = new BatchedBuffer(null, 0, false, 7, null).fullscreenQuad();
      RenderPipeline var10002 = Pipelines.INSTANCE.getProgramPostDefault();
      Framebuffer var10003 = Globals.getMc().getFramebuffer();
      Intrinsics.checkNotNullExpressionValue(var10003, "getMainRenderTarget(...)");
      GpuBufferSlice var10004 = ChamsUniformStorageD.INSTANCE
         .getDynamicUniformStorage()
         .write(new LineUniforms(1.0F / var3.textureWidth, 1.0F / var3.textureHeight, var1, var2));
      GpuTextureView var10008 = var3.getColorAttachmentView();
      Intrinsics.checkNotNull(var10008);
      BatchedBuffer.draw$default(var10, var10001, var10002, var10003, var10004, null, null, MapsKt.mapOf(TuplesKt.to("Sampler0", var10008)), 24, null);
   }

   private final void F(Color var1, long var2, Color var4, Color var5, Color var6, float var7, WindowFramebuffer var8) {
      var2 = b ^ var2;
      long var16 = var2 ^ 63778922578128L;
      BatchedBuffer var10001 = new BatchedBuffer(null, 0, false, 7, null).fullscreenQuad();
      RenderPipeline var10002 = Pipelines.INSTANCE.getProgramPostCamouflage();
      Framebuffer var10003 = Globals.getMc().getFramebuffer();
      Intrinsics.checkNotNullExpressionValue(var10003, "getMainRenderTarget(...)");
      DynamicUniformStorage var10004 = ChamsUniformStorageB.INSTANCE.getDynamicUniformStorage();
      float var10007 = 1.0F / var8.textureWidth;
      float var10008 = 1.0F / var8.textureHeight;
      float var24 = Math.abs(
         RenderUtil.INSTANCE.lerp((Globals.getPlayer().age - 1) / 1000.0F, Globals.getPlayer().age / 1000.0F, RenderUtil.INSTANCE.getTickDelta())
      );
      float var32 = var10008;
      float var33 = var10007;
      GpuBufferSlice var35 = var10004.write(new GradientUniforms(var33, var32, var1, var4, var5, var6, var7, var24));
      GpuTextureView var36 = var8.getColorAttachmentView();
      Intrinsics.checkNotNull(var36);
      BatchedBuffer.draw$default(var16, var10001, var10002, var10003, var35, null, null, MapsKt.mapOf(TuplesKt.to("Sampler0", var36)), 24, null);
   }

   private final void b(WindowFramebuffer var1, Color var2, boolean var3, boolean var4, float var5, long var6) {
      var6 = b ^ var6;
      long var12 = var6 ^ 28058430958654L;
      BatchedBuffer var10000 = new BatchedBuffer(null, 0, false, 7, null).fullscreenQuad();
      RenderPipeline var10001 = Pipelines.INSTANCE.getProgramPostMirror();
      Framebuffer var10002 = Globals.getMc().getFramebuffer();
      Intrinsics.checkNotNullExpressionValue(var10002, "getMainRenderTarget(...)");
      DynamicUniformStorage var10003 = ChamsUniformStorageC.INSTANCE.getDynamicUniformStorage();
      Vec2f var10006 = new Vec2f(0.0F, var4 ? 0.0F : 0.5F);
      Vec2f var10007 = new Vec2f(1.0F / var1.textureWidth, 1.0F / var1.textureHeight);
      float var20 = var3 ? 1.0F : 0.0F;
      GpuBufferSlice var30 = var10003.write(new OutlineUniforms(var10006, var10007, var2, var20, var5, var5));
      Pair[] var27 = new Pair[2];
      GpuTextureView var10009 = var1.getColorAttachmentView();
      Intrinsics.checkNotNull(var10009);
      var27[0] = TuplesKt.to("Sampler0", var10009);
      var27[1] = TuplesKt.to("Sampler1", BlurPass.INSTANCE.getOutput());
      Map var31 = MapsKt.mapOf(var27);
      Object var18 = null;
      byte var19 = 24;
      Map var29 = var31;
      Object var21 = null;
      Object var22 = null;
      GpuBufferSlice var23 = var30;
      Framebuffer var24 = var10002;
      RenderPipeline var25 = var10001;
      BatchedBuffer var26 = var10000;
      BatchedBuffer.draw$default(var12, var26, var25, var24, var23, (Matrix4f)var22, (Matrix3x2f)var21, var29, var19, var18);
   }

   private final void v(Camera var1, float var2, Matrix4f var3) {
      int var10 = ShaderMode.zkmIntConstant();
      Matrix4f var15;
      if (Aspect.INSTANCE.getEnabled()) {
         var15 = new Matrix4f();
         GameRenderer var10001 = Globals.getMc().gameRenderer;
         Intrinsics.checkNotNull(var10001, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
         var15 = var15.perspective(
            ((GameRendererAccessor)var10001).iGetFov(var1, var2, false) * (float) (Math.PI / 180.0),
            (float)Globals.getMc().getWindow().getFramebufferWidth() / Globals.getMc().getWindow().getFramebufferHeight(),
            0.05F,
            Math.max(
               Globals.getMc().gameRenderer.getViewDistanceBlocks() * 4.0F,
               (float)(Globals.getMc().options.getCloudRenderDistance().getValue().intValue() * 16)
            )
         );
      } else {
         GameRenderer var16 = Globals.getMc().gameRenderer;
         GameRenderer var26 = Globals.getMc().gameRenderer;
         Intrinsics.checkNotNull(var26, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
         var15 = var16.getBasicProjectionMatrix(((GameRendererAccessor)var26).iGetFov(var1, var2, false));
      }

      Matrix4f var11 = var15;
      GameRenderer var17 = Globals.getMc().gameRenderer;
      Intrinsics.checkNotNull(var17, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
      RenderSystem.setProjectionMatrix(((GameRendererAccessor)var17).getLevelProjectionMatrixBuffer().set(var11), ProjectionType.PERSPECTIVE);
      MatrixStack var12 = new MatrixStack();
      var12.push();
      var12.multiplyPositionMatrix((Matrix4fc)var3.invert(new Matrix4f()));
      Matrix4fStack var18 = RenderSystem.getModelViewStack();
      Intrinsics.checkNotNullExpressionValue(var18, "getModelViewStack(...)");
      Matrix4fStack var13 = var18;
      var13.pushMatrix().mul((Matrix4fc)var3);
      GameRenderer var19 = Globals.getMc().gameRenderer;
      Intrinsics.checkNotNull(var19, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
      ((GameRendererAccessor)var19).itiltViewWhenHurt(var12, var2);
      boolean var20 = Globals.getMc().options.getBobView().getValue();
      if (var10 == 0) {
         if (var20) {
            GameRenderer var21 = Globals.getMc().gameRenderer;
            Intrinsics.checkNotNull(var21, "null cannot be cast to non-null type su.catlean.mixins.accessors.GameRendererAccessor");
            ((GameRendererAccessor)var21).ibobView(var12, var2);
         }

         var20 = Globals.getMc().getCameraEntity() instanceof LivingEntity;
      }

      label61: {
         label65: {
            if (var10 == 0) {
               if (!var20) {
                  break label65;
               }

               Entity var22 = Globals.getMc().getCameraEntity();
               Intrinsics.checkNotNull(var22, "null cannot be cast to non-null type net.minecraft.world.entity.LivingEntity");
               var20 = ((LivingEntity)var22).isSleeping();
            }

            if (var10 != 0) {
               break label61;
            }

            if (var20) {
               var20 = true;
               break label61;
            }
         }

         var20 = false;
      }

      boolean var14 = var20;
      var23 = Globals.getMc();
      label50:
      if (var10 == 0) {
         label48:
         if (var23.options.getPerspective().isFirstPerson()) {
            boolean var24 = var14;
            if (var10 == 0) {
               if (var14) {
                  break label48;
               }

               var23 = Globals.getMc();
               if (var10 != 0) {
                  break label50;
               }

               var24 = var23.options.hudHidden;
            }

            if (!var24) {
               var23 = Globals.getMc();
               if (var10 != 0) {
                  break label50;
               }

               ClientPlayerInteractionManager var25 = var23.interactionManager;
               Intrinsics.checkNotNull(var23.interactionManager);
               if (var25.getCurrentGameMode() != GameMode.SPECTATOR) {
                  Globals.getMc()
                     .gameRenderer
                     .firstPersonRenderer
                     .renderItem(
                        var2,
                        var12,
                        Globals.getMc().gameRenderer.getEntityRenderCommandQueue(),
                        Globals.getPlayer(),
                        Globals.getMc().getEntityRenderDispatcher().getLight(Globals.getPlayer(), var2)
                     );
               }
            }
         }

         Globals.getMc().gameRenderer.getEntityRenderDispatcher().render();
         Globals.getMc().getBufferBuilders().getEntityVertexConsumers().draw();
         var23 = Globals.getMc();
      }

      var23.gameRenderer.getEntityRenderCommandQueue().clear();
      var13.popMatrix();
      var12.pop();
   }

   private final float W(int var1) {
      return (float)(0.06649038007 * Math.exp(-(var1 * var1) / 72.0));
   }

   private static final boolean settingVisible() {
      return INSTANCE.getHandsMode() == ShaderMode.MIRROR;
   }

   private static final boolean settingVisible2() {
      return INSTANCE.getHandsMode() == ShaderMode.MIRROR;
   }

   private static final boolean settingVisible3() {
      return INSTANCE.getHandsMode() == ShaderMode.MIRROR;
   }

   private static final boolean settingVisible4() {
      return INSTANCE.getHandsMode() == ShaderMode.BLOOM;
   }

   private static final boolean settingVisible5() {
      return INSTANCE.getHandsMode() == ShaderMode.BLOOM;
   }

   private static final boolean settingVisible6() {
      return INSTANCE.getHandsMode() == ShaderMode.BLOOM;
   }

   private static final boolean settingVisible7() {
      int var5 = ShaderMode.zkmIntConstant();
      ShaderMode var10000 = INSTANCE.getHandsMode();
      ShaderMode var10001 = ShaderMode.DEFAULT;
      if (var5 == 0) {
         if (var10000 == ShaderMode.DEFAULT) {
            return true;
         }

         var10000 = INSTANCE.getHandsMode();
         var10001 = ShaderMode.BLOOM;
      }

      if (var5 == 0) {
         if (var10000 == var10001) {
            return true;
         }

         var10000 = INSTANCE.getHandsMode();
         var10001 = ShaderMode.CAMOUFLAGE;
      }

      return var10000 == var10001;
   }

   private static final boolean settingVisible8() {
      return INSTANCE.getHandsMode() == ShaderMode.DOUBLE;
   }

   private static final boolean settingVisible9() {
      int var5 = ShaderMode.zkmIntConstant();
      ShaderMode var10000 = INSTANCE.getHandsMode();
      ShaderMode var10001 = ShaderMode.DOUBLE;
      if (var5 == 0) {
         if (var10000 == ShaderMode.DOUBLE) {
            return true;
         }

         var10000 = INSTANCE.getHandsMode();
         var10001 = ShaderMode.CAMOUFLAGE;
      }

      return var10000 == var10001;
   }

   private static final boolean settingVisible10() {
      return INSTANCE.getHandsMode() == ShaderMode.DOUBLE;
   }

   private static final boolean settingVisible11() {
      return INSTANCE.getHandsMode() == ShaderMode.DOUBLE;
   }

   private static final boolean settingVisible12() {
      return INSTANCE.getHandsMode() == ShaderMode.DOUBLE;
   }

   private static final boolean settingVisible13() {
      return INSTANCE.getHandsMode() == ShaderMode.DOUBLE;
   }

   private static final boolean settingVisible14() {
      return INSTANCE.getHandsMode() == ShaderMode.DOUBLE;
   }

   private static final boolean settingVisible15() {
      return INSTANCE.getHandsMode() == ShaderMode.DOUBLE;
   }

   static {
      long var20 = b ^ 68224145163156L;
      zkmSetFlag(111);
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[73];
      int var16 = 0;
      String var15 = "\u0013Ì\u001bÒ4\u0080Ý´\u0013â½\u001b\u0007(}iY´\u0087µqß\u0082À Lpjá¦»@ð\u0096Ô¹5Ò\u000e\neF\u008b¯\u0003Ä\u00ad¨¦|\u0013sÈ&öâ\u0083\u0010\u00825qÆÊL\\\u0097Øc1tM\u0005ê4 \\³ø\u0012ñ\u0014ßGBµ;yrRÙÛØ1ÑþH°\u0094<íH\u0097E\u008c\ráù\u0018\u0005 ¿#³\u0003>f§ÁZMU\u001f\u0002R\u0095Î{ªfÝ÷Ò(/çw>º\u001dà-\u008bt\txßI\b\u0000w¼\u008e\u008f\u0094j\u009aè°k¨÷ÇÔ©.\n¾Â6\nJ}D\u0010ä#\u001cò\u009c¥ãüW¦þ\u0093fkÖå\u0018\u0085C¥¶1'\u009e¢Ë0û(\u0017\u0005\u0004èùÛoCð¶\u008a]\u0010Çn·\t\u0093\u007f\u0018BpßÎ(\u000e÷\u0088\b(N\u009dÙ9$\u009dþp¿Wxµ\u0017\u0018\u0099tk©9ª²Ë¢ÓR\u0005¥\u0091ß\u009cuÄ\u009e=\u009c·\u0091Ï\u000bå\u0018V°\u001eÃõ3\u0001_\n\u007frnDÅ7\u0093yí}*Ö\u0095J'\u0010ÀäÌ½±pâOJ\u007fk\rÖË)]\u0018¢Àm©ÎM-9\u007f\u0012@B·£zÅÊ5>\u0081±\u001ciÊ0\u0004[ËÖÌÃÇ*°\u0004CxßÉY\"ÌÿýøïÇv\u001cÒ}¨æJÅ¥\u0015bp»zûC·ÄªÐ\u0004O¥Þ\u0010Ò(îÂD\u0014TÅ*Ð¿5Ð\u0092z`ò[ÏU©\u0001\u001a\f+p'\u007fÔ\u0018[Eéùù9æ\u0014\u0018èr* =Ãa1\u008c-\u0099|\u0085\u008cé,[uz\u009cø-\u0093n\u0088¬\u001f\u009cAò<Z\u0006f2\u0093@x\u0093\u008d\u009eÁ`ø?c?°Þ}\u0098$Û \u009b\u0094\u001fÜ7|¨5=\u000b¨Cº\u0017ó\u000f)Í\u008dÐ6ãCV£º\u0001ø)2wÿ\u0012±zãøÄâ/Ç\u001bÿ#A´Î(Ü;\u0017\u008f÷lÐzõIuR\u0015°\u0095xvNaA3\u0084ó\u0005¯\u0004Oÿ½ÄÄ-¨+À¤³ø1? LÂä\u0096\u0097\u0017ô×y\"Ð÷¸\u0018<>ã÷KÐ\u0019ÇÝgþ´¹\u009fû|÷\u0080\u0018W7G3Ì\u0004?\u0004ÛÁÓ®A^xG!Ý\u0096c%/\u0005æ\u0018ê\u0001\u0093l\u009b´\\`£%>£C\u0090ØÒ\u009eBì(³û\bè ú0Ú\u0017 ±]ôÉ\u0018Õ*Ð¦9su\u000e\u000b\u001bÙPq\u0090QÐØ¥\u000bÆÍÏ0V\u0001,+è%\u0096ü\u000f-(\u0089\u001b\u0093Ar\u0093&Õ\u0001\u001ao\u0017#¹ï\u001a4áMÖÈ\rHùÀ\u000eý4ýym\u001dÉZè\u0083¾(6ZG#\u0089dmi#¿\u0011\u0091Ð¸épw<\u0086b\u008bóésH\u008d&i\u0082LíÞp\u0014åî\u0015\r\fÌ %\u0093\u0000×²}úÔ\u001a\u00806kâ\u0095f\u0089ô\u001e\u001a±#¼\u000f\u0096\u001bG,\u000b%mè\u0093 c\u009b3ÑØA±©'V\u0091»6é²Õ?\u001ea\u000em|\u009ePO§sM~¤©É(òÉ¨é\u00adQô\u000b·Ú\u00146\u0013á-f\u009dÓ\t\u0005\rF¥Ï\u0002çY@H\u001e\u0014\u0005Ú\u0016°z 'ôâ ºÙ9\u0095Â\u001c.qßqÀG\u0019\u008d\u009d¨rmpàK\u0085^2Ék\u0098\u008bÏ~\u0005á@\u0087EDLó\u0004\u0088Szá\u0002\u0085Ó\r\u0013E¯IáÄCé«áb\\8¯6ÿ\u0001i5íÓ;\u001b\u0001N\r³g/kÍ~Hä\\\u0001\u0093ù0\u0001^X²\u0013f\u0001|\u0015¿\f \u0094#ú\u000f\u0002¿Î\u0087\u001cy®Ø\u0006S¦¡\u0082Z³û+¶\n3r|æÑæo\u0094êx#gT\u009e\u0089\u0097_\nèLBÄÿ\u0012Bét\u0088\u008f'\rS\u0002ÿÔ'Q\u0015\"gqxÝkb¿W\r\u0090×á\të\u0007,e¨\u0017\f6ú9vj?\u0085)ÔÏ)±®$_°±¯g9ÖÜ\u0090²D ×Êâ´á\u0004\u0018\u0013û\u000bÏ\u0004¶§JMjß¹s\u009a+h·\u000e\u0002\f$ÜeVø\u0006;ÎrìÏ¾Y0F\u0094÷\u0091 &<\u008f\u0094°MQy{\u000389ÚøG³O\u0087\u0016»\u00941/k!0¸ÚsÂ\\\r0&èGW67\u001cä\u001b£7\u008f\u0083Äý©\bæO\u0013\u001a3[\u000emT³ø<\u0001\u0017ª\u0018\u0084:ÍùÍ3öü®)û\u009cû¦å(a\r5Ìáî§æ\u009aT=\fÕ\u00adÌ\\\u009bÌ¼³\u0003T\u0096éâ#\u007f\u009eo`Î\u0086D`\\\u009fÛì·ã \u0088EP¸Õi\u0015±Ë\u000f¨~\u0091;}A`Ç\u0002¼\u0097\u0006æÖéÑQ\u0001\u0004¬\u000f`\u0018\u0096ì\u0083\u0007H¼b)Ö\"Sô\u008a\u008cì\u0080#\u0096K\u008e.âÈÛ\u0010í\u0090\u0010\u0091©\u000e\u000b.\u0098â\u0087Zõû{À\u0010Ä\u0002ú=\f%TÐ%#ÓIEð\u007fÖ \u0016ø\u001b<é9\u00ad¿\u0015µîØz\u0092iÐ$Sò[\u001cë*öÌ\u007fzp\u0088Tö8\u0018ë©?2\u0005K `à\u009e\u008eî¿\u001fêY\u009b?\u001fí\u009a:u8\u0010Ü}öH±\u00821\nO0ÃË+¨5v8Ã\u0002\u0016çwR®B\u001bv`¹hÔ>DÜYC\u0007A¤»ÆÂeÓñlö9\u0088¶ÜÌÉ´Ä/¾\u001c\u0094¦:\u0004HS\u0082\u0015\u009eÄ,½¼!´ \u001a\\~î9@ÊÕ\u0091\b\u0005t\u0094eO\t*\u0083¢b\u0090d¤Ú9bz\u008cÃ|\\L òÏâi7ÌÜâJÁKôç\u00015\u001b\u00938¦B\u0016\u008etJR´Ó\u008a\u0018Þ\u0099\u001b xxÏö/Ó\u0093vCOÑÒ\f%T\u000eW¾n\u0019N\u009c¢\u009c46I;Î`µ< V:Ï©¯r\u0012hVyz¨Kc\u001b6k ñìæ\u000ew\u0012\u009bÕSäoÌ#Ú0u\u0097 \"ãZ{Ë>h)õ\u0010è\u0017ÝôGM\u0097ïþ\u0089>\u009c\u0001\u0087&r\u0004øÓö\u0092à\u00ad\u008bi\u008dÚz³WB\u0010v\u0099\u007f \u008cI\u0096à*w<·\u0017HHáF*\u0013|Ë@¬£\u009d¦FÓyº{\u001f\\XÛÎ\u0010.¬\u008c\u0082µ¼\u0010h\u0015îW½ÎÃ²C :A\u0094\t\u0090t\f\u0006\u0002\u0002\u0011\u00aduÌ\u001c\u0097kÂÎ?\u0093\u000f¡f\u0086&c8\u0002hñ*8{^\u009dN©\u0002\u0006\bø\u0084\u001d/ð\u001c`D\n\u0003Sö\u0082à\u0016\u0011\u0006¯ ´\u001cj£µsyÝ¶¸¬Ü9\u0091+34#$+\u008b§ë\u0005VÓô\u0016\u0087\u0018îï\u009a\u008d\u0097K\u0007ñ¼`f\r|\u0014\u0001õK,1\u0013ä\u0081\u000fç\u0088ÃÍ\u0092NQÕ0ËÍ\u0090DÙö\u001c½\u008c×l»\u0006\u001eÀËá\u001362\u0081½7õpc\u0093\u008apýeLsÛ¨q\u0094»©,\u001e\u000f\u0080U´zm¶$\u009aë»\u0003\u0017âl0\u001fÿ¶o«¶A©Òª\u008e2bnÐ\u0081\u008d·\u0085ù\u008e\u0081\u0084²Õï'qÍ3HZæ¯Ö`Ò×ÝwI\nò(ÒÀ\"\u0098\u008ayNT\u0081û\u0096\u0016âÍ.ÏB\u009fsN¹\u0097\u0002Ý%l¦j\u0010n×XWÌøÂÑÎú\u0093Å$w,±\u0010\u0080\u0098\u0089lzg¬/R[\nöù~ØA\u0018Û\u0011±Z¯KäÎ'¿¢\u008e Þ\u0090\t[î÷üÜï\u0017\u001d\u0010þb\u0080?#-\u0087#ÞÏZ-ÿô£\u009d\u0090b\u0004Épæ¬àgQ÷¶K\u0001Çe·æ·[ôß.¸\u0011A?D\u009d,Ñ\u0098fÈ±\u009e U\u0087\u0092bê*\u0091-¹í3«Fö·\u0019²Ýý\u0081Úø³Ý4ièi.>\u009fê\rVý´\u001c!\u009e\u0088\u009cýÍ³¢\u0082|Ð\u0082DAèºg\u0082¨C¬_Æ³I\u008eCØ\u0098\u0018Hj^\u0014h_vboñÇYnæSqÚÔ\u0001,\u009e\u0082î¦¦@9'\u009b\u0006q\u009bé+Á\u009e\u009b_?s  è5_\u0095h©³Þqé\u001a^ÂÎ\u0016ñðù?Õ×#v±\u0084ÙL\u000be+ã'(q{2¥\u0093z\u0098§\"ëO\u0010y0a\u000f?\u009cä±\u0088¨Q¿\u008b\b*\u007fóu~¯yloq×¨k8\u0018a\u0005\u0003dl9;cCÎÕCÝþåà\u009bs\u001b¥(\u0014V\u008c0ð¨3\u009fcþ\u0004ù\u0094Hì\u0014ç\u008d¤[YÕÉÚE+*\u0096¯\u0098\n\r)T±2\u0084\u0002ÔøùþÁÇE\u0080\u00adªQ\u001ayA\u0018&\u0097qÜ]P\u00adÐSô\u0088\u0098Ê\ns\u008bk©\u0083\u0090 ,dE\u0010yü\rÕê\u00adJü\u0082§õü\u0093\r/®8\u0014I6x\u009cjè\u0089d\b%\u001c*î²È3\"ðæ¨\u0084\u0006Ö§.âÝ\b*\u0016´ß«q¼?Þ\u009a0O\fG\u009f\u0091ó§á\u0002½\u007f\u009214¼ª8t\u001c\u0010µ&Óõ\u009dãºDQJ\u0091±Î\u00adÏêk\u0093¥4\u0002L\u009b\u0097¡å\u008e\u001fÏÓúG!\bîþiÐD»®¡×;ä(\u0005³ÊÌúD±(\u009cnNÏ\u0084\u0082\u008dqZ\u001f¦k\u0000\u00143\u0093X³Z\nÕö²zäODãÜ/;|Ã¶Äâ\f¯\u000eå\u0010dò·f\u0005ø:\u0001!6\"±\u001bpõ¡ ?Ã\u001d\u000e\u000ed?Â°$µVÏs\u0096\u008d\u001f\\Sw\u001c½î£_\u0085c8\ts\u0082\u0002(\u009eI|Z\nÓþ\u0005\u009d\"\u0097¯5m\u0099J-\u008bí\u0016Þ=`\u0016c\u001c\u0089¶\u0081\u0099\b\u0086×:\u0099Ì÷É\u0083\r\u0018\u0010\u000f¤dvk\u0087\u000e'\u0095×¡ìC\u000f\u008euì>vAÓkå";
      short var17 = 2646;
      char var14 = 24;
      int var38 = -1;

      label54:
      while (true) {
         String var39 = var15.substring(++var38, var38 + var14);
         int var10001 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var39.getBytes("ISO-8859-1"));
            String var50 = zkmDecodeUtf8(var19).intern();
            switch (var10001) {
               case 0:
                  var18[var16++] = var50;
                  if ((var38 += var14) >= var17) {
                     c = var18;
                     e = new String[73];
                     C = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[29];
                     int var3 = 0;
                     String var4 = "\u009f½Ô«\u008b2ÁHÒH@\u009c\u008eâ£v\u0014YQã\u0015Û6ñ©ø¥$Û\u0001\u0012ª\u008b\t¼\u008b\u0083Û7\u009d\u0083P \u0015~]b|3»\u0090\u009bòM?ÛM|ùÚv:zÍO\u008dô¸ÐÌ\u0094Íj¢Ü\u0093ºEµÿÂQ\b«ÐKù\u009b\u0080Ûq»\u0086ØÜËz|\u009297\u0091)\u008c¬öÕ?Íå\u009b\u008cKU\u0012Wå\u0091¨tG\u008c57\u000b/Ô<Ã\u0096N·ÿu~:!/Tt\u0000)\u0084\u0004\u007f\u0085ÉMüÒ'kFÊø\u001a×\u00032(Ýx¨vî\u0017æ\u0012²+£1%\u0083°\u0092?c{y*â\u0096°Ý<\u0081t\u008fX\u001bT²Ö.Í\u0095ïÂJz{\u0018$7[ù±]\u000e\u0003\u0085þï)\u0087";
                     short var5 = 216;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        var10001 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var10001, var2).getBytes("ISO-8859-1");
                        long[] var42 = var6;
                        var10001 = var3++;
                        long var54 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var57 = -1;

                        while (true) {
                           long var8 = var54;
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
                           long var59 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var57) {
                              case 0:
                                 var42[var10001] = var59;
                                 if (var2 >= var5) {
                                    i = var6;
                                    m = new Integer[29];
                                    KProperty[] var35 = new KProperty[]{
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "handsMode", "getHandsMode()Lsu/catlean/rw;", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "handsColor", "getHandsColor()Ljava/awt/Color;", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(HandChams.class, "glass", "getGlass()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(HandChams.class, "alpha", "getAlpha()F", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(HandChams.class, "borders", "getBorders()Z", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(HandChams.class, "radiusValue", "getRadiusValue()F", 0)),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "distanceValue", "getDistanceValue()F", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "glowAlphaValue", "getGlowAlphaValue()F", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "lineWithValue", "getLineWithValue()F", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "colorModeValue", "getColorModeValue()Lsu/catlean/sa;", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "secondColor", "getSecondColor()Ljava/awt/Color;", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "glowQualityValue", "getGlowQualityValue()I", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "glowRadiusValue", "getGlowRadiusValue()F", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "glowAlphaMultValue", "getGlowAlphaMultValue()F", 0)
                                       ),
                                       Reflection.mutableProperty1(
                                          new MutablePropertyReference1Impl(HandChams.class, "fillAlphaMultValue", "getFillAlphaMultValue()F", 0)
                                       ),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(HandChams.class, "saturation", "getSaturation()F", 0)),
                                       Reflection.mutableProperty1(new MutablePropertyReference1Impl(HandChams.class, "brightness", "getBrightness()F", 0))
                                    };
                                    delegatedProperties = var35;
                                    INSTANCE = new HandChams();
                                    handsMode$delegate = SettingFactory.modeSetting$default(INSTANCE, "mode", ShaderMode.MIRROR, null, null, 12, null);
                                    handsColor$delegate = SettingFactory.colorSetting$default(INSTANCE, "color", new Color(200, 200, 255), null, null, 12, null);
                                    glass$delegate = SettingFactory.booleanSetting$default(INSTANCE, "glass", false, null, HandChams::settingVisible, 4, null);
                                    alpha$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "alpha", 0.2F, RangesKt.rangeTo(0.0F, 2.0F), null, HandChams::settingVisible2, 8, null
                                    );
                                    borders$delegate = SettingFactory.booleanSetting$default(
                                       INSTANCE, "border", false, null, HandChams::settingVisible3, 4, null
                                    );
                                    radiusValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "radius", 6.0F, RangesKt.rangeTo(0.0F, 15.0F), null, HandChams::settingVisible4, 8, null
                                    );
                                    distanceValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "distance", 1.3F, RangesKt.rangeTo(0.0F, 15.0F), null, HandChams::settingVisible5, 8, null
                                    );
                                    glowAlphaValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "glow-alpha", 1.7F, RangesKt.rangeTo(1.0F, 5.0F), null, HandChams::settingVisible6, 8, null
                                    );
                                    lineWithValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "line-width", 4.0F, RangesKt.rangeTo(1.0F, 4.0F), null, HandChams::settingVisible7, 8, null
                                    );
                                    colorModeValue$delegate = SettingFactory.modeSetting$default(
                                       INSTANCE, "color-mode", ChamsColorMode.RGB, null, HandChams::settingVisible8, 4, null
                                    );
                                    secondColor$delegate = SettingFactory.colorSetting$default(
                                       INSTANCE, "second-color", new Color(200, 200, 255), null, HandChams::settingVisible9, 4, null
                                    );
                                    glowQualityValue$delegate = SettingFactory.intSetting$default(
                                       INSTANCE, "glow-quality", 2, new IntRange(1, 4), null, HandChams::settingVisible10, 8, null
                                    );
                                    glowRadiusValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "glow-radius", 4.0F, RangesKt.rangeTo(1.0F, 16.0F), null, HandChams::settingVisible11, 8, null
                                    );
                                    glowAlphaMultValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "glow-alpha-multiplier", 2.0F, RangesKt.rangeTo(0.0F, 4.0F), null, HandChams::settingVisible12, 8, null
                                    );
                                    fillAlphaMultValue$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "fill-alpha-multiplier", 0.6F, RangesKt.rangeTo(0.0F, 1.0F), null, HandChams::settingVisible13, 8, null
                                    );
                                    saturation$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "saturation", 0.7F, RangesKt.rangeTo(0.0F, 5.0F), null, HandChams::settingVisible14, 8, null
                                    );
                                    brightness$delegate = SettingFactory.floatSetting$default(
                                       INSTANCE, "brightness", 2.0F, RangesKt.rangeTo(0.0F, 45.0F), null, HandChams::settingVisible15, 8, null
                                    );
                                    u = new float[16];
                                    A = -1;
                                    return;
                                 }
                                 break;
                              default:
                                 var42[var10001] = var59;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "ÿ\u00195\rww®ñ\u0012¹\u0001t\u000e-I]";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var48 = var2;
                           var2 += 8;
                           var7 = var4.substring(var48, var2).getBytes("ISO-8859-1");
                           var42 = var6;
                           var10001 = var3++;
                           var54 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var57 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var38);
                  break;
               default:
                  var18[var16++] = var50;
                  if ((var38 += var14) < var17) {
                     var14 = var15.charAt(var38);
                     continue label54;
                  }

                  var15 = "¯ºkÄ§s\\r°Ô÷\u0081ßä\u0093ÏJ~CÕ\\¥»ÿÇ0#\u0087¢£\u0082U\u0017ÉÐ!:v\"gÉ\u001br\u0080\u001e.\u0003x\u0018©rÇè\u0011º\u008c]é\u0010ýYÇ\u0081öA\u008f·q\u008d»¹\t\u0095";
                  var17 = 73;
                  var14 = '0';
                  var38 = -1;
            }

            var39 = var15.substring(++var38, var38 + var14);
            var10001 = 0;
         }
      }
   }

   public static void zkmSetFlag(int var0) {
      l = var0;
   }

   public static int zkmGetFlag() {
      return l;
   }

   public static int zkmIntConstant() {
      return 0;
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 29857;
      if (e[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().threadId();
            var4 = (Object[])g.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               g.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("su/catlean/kg", var10);
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
         e[var5] = zkmDecodeUtf8(((Cipher)var4[0]).doFinal(var9));
      }

      return e[var5];
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
         throw new RuntimeException("su/catlean/kg" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 1692;
      if (m[var3] == null) {
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
         long var5 = i[var3];
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
         Object[] var9 = (Object[])C.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               C.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/kg", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         m[var3] = var15;
      }

      return m[var3];
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
         throw new RuntimeException("su/catlean/kg" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
