package su.catlean.gui;

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
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.gui.setting.BindElement;
import su.catlean.gui.setting.BlockListElement;
import su.catlean.gui.setting.BooleanElement;
import su.catlean.gui.setting.ButtonElement;
import su.catlean.gui.setting.ColorElement;
import su.catlean.gui.setting.EntityListElement;
import su.catlean.gui.setting.FloatElement;
import su.catlean.gui.setting.GroupElement;
import su.catlean.gui.setting.IntElement;
import su.catlean.gui.setting.ItemListElement;
import su.catlean.gui.setting.StringElement;
import su.catlean.gui.setting.TextElement;
import su.catlean.module.Module;
import su.catlean.module.attack.AuraTargetsMode;
import su.catlean.module.misc.UI;
import su.catlean.module.misc.UIModuleMode;
import su.catlean.module.misc.UIModuleTextMode;
import su.catlean.render.Rect;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.setting.Bind;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.EntityListValue;
import su.catlean.setting.ItemListValue;
import su.catlean.setting.Setting;
import su.catlean.setting.SettingParent;
import su.catlean.setting.TextValue;
import su.catlean.sound.Sounds;
import su.catlean.util.ActionHolder;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.KeyNames;
import su.catlean.util.KeyboardUtil;
import su.catlean.zkm.ZkmKeys;

public final class ModuleButton {
   @NotNull
   private final CategoryPanel catComponent;
   @NotNull
   private final Module module;
   private float x;
   private float y;
   private float I;
   private float J;
   @NotNull
   private final Animation P;
   @NotNull
   private final Animation F;
   @NotNull
   private final Animation Z;
   @NotNull
   private final Animation v;
   @NotNull
   private final Animation w;
   @NotNull
   private final Animation m;
   @NotNull
   private final Animation E;
   @NotNull
   private final List X;
   private boolean l;
   private boolean a;
   private long r;
   private static final long b = ZkmKeys.a(-7372753313878987426L, 3777541502221453432L, MethodHandles.lookup().lookupClass()).a(64410153257794L);
   private static final String[] c;
   private static final String[] d;
   private static final Map e = new HashMap(13);
   private static final long[] f;
   private static final Integer[] g;
   private static final Map h;
   private static final long[] i;
   private static final Long[] k;
   private static final Map n;

   public ModuleButton(@NotNull CategoryPanel catComponent, long a, @NotNull Module module, float x, float y) {
      ax = b ^ ax;
      long var10001 = ax ^ 73619947565203L;
      int var10 = (int)((ax ^ 73619947565203L) >>> 48);
      int var11 = (int)((ax ^ 73619947565203L) << 16 >>> 48);
      int var12 = (int)(var10001 << 32 >>> 32);
      long var32 = ax ^ 3971805774247L;
      Intrinsics.checkNotNullParameter(catComponentx, "catComponent");
      Intrinsics.checkNotNullParameter(modulex, "module");
      super();
      this.catComponent = catComponentx;
      this.module = modulex;
      this.x = xx;
      this.y = yx;
      this.I = 120.0F;
      this.J = 17.0F;
      this.P = new Animation(Easing.OUT_QUINT, 200L);
      this.F = new Animation(Easing.OUT_QUINT, 200L);
      this.Z = new Animation(Easing.OUT_QUINT, 300L);
      this.v = new Animation(Easing.OUT_QUINT, 200L);
      this.w = new Animation(Easing.OUT_QUINT, 200L);
      this.m = new Animation(Easing.OUT_QUINT, 200L);
      this.E = new Animation(Easing.OUT_QUINT, 200L);
      this.X = new ArrayList();

      for (Object var42 : this.module.getSettings()) {
         Setting var43 = (Setting)var42;
         Object var45 = var43.getValue();
         SettingElement var10000;
         if (var45 instanceof Boolean) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<kotlin.Boolean>");
            var10000 = new BooleanElement(var43);
         } else if (var45 instanceof Float) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<kotlin.Float>");
            var10000 = new FloatElement(var43);
         } else if (var45 instanceof Integer) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<kotlin.Int>");
            var10000 = new IntElement(var43);
         } else if (var45 instanceof Bind) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<su.catlean.api.setting.values.Bind>");
            var10000 = new BindElement(var43);
         } else if (var45 instanceof String) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<kotlin.String>");
            var10000 = new StringElement(var43);
         } else if (var45 instanceof Color) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<java.awt.Color>");
            var10000 = new ColorElement(var43);
         } else if (var45 instanceof SettingParent) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<su.catlean.api.setting.Group>");
            var10000 = new GroupElement(var43, this, var32);
         } else if (var45 instanceof BlockListValue) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<su.catlean.api.setting.values.BlocksValue>");
            var10000 = new BlockListElement(var43);
         } else if (var45 instanceof EntityListValue) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<su.catlean.api.setting.values.EntitiesValue>");
            var10000 = new EntityListElement(var43);
         } else if (var45 instanceof ItemListValue) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<su.catlean.api.setting.values.ItemsValue>");
            var10000 = new ItemListElement(var43);
         } else if (var45 instanceof ActionHolder) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<su.catlean.api.setting.values.ActionValue>");
            var10000 = new ButtonElement(var43);
         } else if (var45 instanceof TextValue) {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<su.catlean.api.setting.values.NotationValue>");
            var10000 = new TextElement(var43);
         } else {
            Intrinsics.checkNotNull(var43, "null cannot be cast to non-null type su.catlean.api.setting.Setting<kotlin.Enum<*>>");
            var10000 = new SettingGroupElement((short)var10, (char)var11, var43, var12);
         }

         SettingElement var46 = var10000;
         if (!Intrinsics.areEqual(var43.getId(), "enabled") && !Intrinsics.areEqual(var43.getId(), "bind")) {
            this.X.add(var46);
         }
      }
   }

   @NotNull
   public final Module getModule() {
      return this.module;
   }

   public final float getX() {
      return this.x;
   }

   public final void setX(float <set-?>) {
      this.x = <set-?>;
   }

   public final float getY() {
      return this.y;
   }

   public final void setY(float <set-?>) {
      this.y = <set-?>;
   }

   public final float P() {
      return this.I;
   }

   public final void e(float <set-?>) {
      this.I = <set-?>;
   }

   public final float V() {
      return this.J;
   }

   public final void x(float <set-?>) {
      this.J = <set-?>;
   }

   @NotNull
   public final List T() {
      Iterable var2 = this.X;
      boolean var10000 = SettingElement.e();
      var var5 = new ArrayList();
      Iterator var7 = var2.iterator();
      boolean var1 = var10000;

      while (var7.hasNext()) {
         Object var8 = var7.next();
         SettingElement var9 = (SettingElement)var8;
         var10000 = (Boolean)var9.getSetting().getVisible().invoke();
         if (!var1) {
            if (!var10000) {
               continue;
            }

            var5.add(var8);
         }

         if (var1) {
            break;
         }
      }

      return var5;
   }

   public final void m(@NotNull List value) {
      long var2 = 0L;
      var2 = b ^ var2;
      Intrinsics.checkNotNullParameter(value, zkmBootstrap<"s">(24364, 4256817241053333068L ^ var2));
      this.X.addAll(value);
   }

   public final boolean z() {
      return this.l;
   }

   public final void G(boolean <set-?>) {
      this.l = <set-?>;
   }

   public final void E(@NotNull DrawContext context, int mouseX, int mouseY, char a, short a, int a, float factor, boolean hAnimation) {
      long var15;
      long var19;
      int var23;
      long var27;
      int var35;
      int var36;
      int var37;
      long var42;
      boolean var44;
      int var90;
      label267: {
         long var9 = ((long)axxx << 48 | (long)ax << 48 >>> 16 | (long)axx << 32 >>> 32) ^ b;
         var15 = var9 ^ 61668449675624L;
         var19 = var9 ^ 31692093097511L;
         var23 = (int)((var9 ^ 82634963213552L) << 48 >>> 48);
         var27 = var9 ^ 63863362080413L;
         long var10001 = var9 ^ 112797863280240L;
         var35 = (int)((var9 ^ 112797863280240L) >>> 48);
         var36 = (int)((var9 ^ 112797863280240L) << 16 >>> 48);
         var37 = (int)(var10001 << 32 >>> 32);
         var42 = var9 ^ 8984481874929L;
         boolean var10000 = SettingElement.e();
         Intrinsics.checkNotNullParameter(context, "context");
         var44 = var10000;
         this.J = this.l ? 31.0F : 17.0F;
         this.P.animate(this.module.getEnabled() ? 1.0F : 0.0F);
         float var45 = this.x;
         float var46 = this.x + this.I - 6.0F;
         float var47 = mouseX;
         float var105;
         var90 = (var105 = var45 - var47) == 0.0F ? 0 : (var105 < 0.0F ? -1 : 1);
         if (!var44) {
            if (var90 > 0) {
               var90 = 0;
               break label267;
            }

            float var106;
            var90 = (var106 = var47 - var46) == 0.0F ? 0 : (var106 < 0.0F ? -1 : 1);
         }

         if (!var44) {
            var90 = var90 <= 0 ? 1 : 0;
         }
      }

      label261: {
         if (var90 != 0) {
            label258: {
               float var58 = this.y;
               float var60 = this.y + this.J;
               float var62 = mouseY;
               float var107;
               var90 = (var107 = var58 - var62) == 0.0F ? 0 : (var107 < 0.0F ? -1 : 1);
               if (!var44) {
                  if (var90 > 0) {
                     var90 = 0;
                     break label258;
                  }

                  float var108;
                  var90 = (var108 = var62 - var60) == 0.0F ? 0 : (var108 < 0.0F ? -1 : 1);
               }

               if (!var44) {
                  var90 = var90 <= 0 ? 1 : 0;
               }
            }

            if (var90 != 0 && !this.l) {
               var92 = 0.5F;
               break label261;
            }
         }

         var92 = 0.0F;
      }

      Color var61;
      label271: {
         Color var59;
         ModuleButton var74;
         label272: {
            label273: {
               this.F.animate(var92);
               this.Z.animate(this.l ? 1.0F : 0.0F);
               this.E.animate(KeyboardUtil.isKeyPressed((char)var23, 342) ? 0.0F : 1.0F);
               this.J = 17.0F + 14.0F * this.Z.getValue();
               var59 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), this.P.getValue());
               var61 = RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveModuleOutlineColor(), Theme.INSTANCE.getModuleOutlineColor(), this.P.getValue());
               UIModuleMode var71 = UI.INSTANCE.getModuleMode();
               UIModuleMode var93 = UIModuleMode.DEFAULT;
               if (!var44) {
                  if (var71 == UIModuleMode.DEFAULT) {
                     Matrix3x2fStack var72 = context.getMatrices();
                     Intrinsics.checkNotNullExpressionValue(var72, "pose(...)");
                     Shapes2D.drawShape$default(
                        var72,
                        (char)var35,
                        this.x,
                        this.y,
                        this.I - 6.0F,
                        17.0F + 14.0F * this.Z.getValue(),
                        4.0F,
                        2.5F + this.F.getValue(),
                        1.0F,
                        1.0F,
                        var59,
                        var61,
                        0.0F,
                        (short)var36,
                        0.0F,
                        var37,
                        0.0F,
                        14336,
                        null
                     );
                     if (!var44) {
                        break label271;
                     }
                  }

                  var74 = UI.INSTANCE;
                  if (var44) {
                     break label273;
                  }

                  var71 = UI.INSTANCE.getModuleMode();
                  var93 = UIModuleMode.RECT;
               }

               if (var71 == var93) {
                  List var63 = this.catComponent.getModuleButtons();
                  ListIterator var49 = var63.listIterator(var63.size());

                  label228:
                  while (var49.hasPrevious()) {
                     Object var50 = var49.previous();
                     var74 = (ModuleButton)var50;

                     do {
                        ModuleButton var51 = var74;
                        if (!Intrinsics.areEqual(var51.module.getCategory(), this.catComponent.getSelectedTab().getSubCategory())) {
                           continue label228;
                        }

                        var74 = (ModuleButton)var50;
                     } while (var44);

                     if (Intrinsics.areEqual(((ModuleButton)var50).module.getId(), this.module.getId())) {
                        Matrix3x2fStack var76 = context.getMatrices();
                        Intrinsics.checkNotNullExpressionValue(var76, "pose(...)");
                        Shapes2D.drawShape(
                           var76,
                           this.x,
                           this.y,
                           this.I - 6.0F,
                           17.0F + 14.0F * this.Z.getValue(),
                           0.0F,
                           2.5F + this.F.getValue(),
                           1.0F,
                           1.0F,
                           var59,
                           var61,
                           4.0F,
                           var15,
                           4.0F,
                           0.0F
                        );
                        if (!var44) {
                           break label271;
                        }
                     }

                     Matrix3x2fStack var77 = context.getMatrices();
                     Intrinsics.checkNotNullExpressionValue(var77, "pose(...)");
                     Shapes2D.drawShape$default(
                        var77,
                        (char)var35,
                        this.x,
                        this.y,
                        this.I - 6.0F,
                        17.0F + 14.0F * this.Z.getValue(),
                        0.0F,
                        2.5F + this.F.getValue(),
                        1.0F,
                        1.0F,
                        var59,
                        var61,
                        0.0F,
                        (short)var36,
                        0.0F,
                        var37,
                        0.0F,
                        14336,
                        null
                     );
                     break label271;
                  }

                  throw new NoSuchElementException("List contains no element matching the predicate.");
               }

               var74 = this;
               if (var44) {
                  break label272;
               }

               var74 = this.module;
            }

            if (!var74.getEnabled()) {
               break label271;
            }

            var74 = this;
         }

         List var64 = var74.catComponent.getModuleButtons();
         ListIterator var66 = var64.listIterator(var64.size());

         label215:
         while (true) {
            if (!var66.hasPrevious()) {
               throw new NoSuchElementException("List contains no element matching the predicate.");
            }

            Object var68 = var66.previous();
            var74 = (ModuleButton)var68;

            do {
               ModuleButton var70 = var74;
               if (!Intrinsics.areEqual(var70.module.getCategory(), this.catComponent.getSelectedTab().getSubCategory())) {
                  continue label215;
               }

               var74 = (ModuleButton)var68;
            } while (var44);

            if (Intrinsics.areEqual(((ModuleButton)var68).module.getId(), this.module.getId())) {
               Matrix3x2fStack var79 = context.getMatrices();
               Intrinsics.checkNotNullExpressionValue(var79, "pose(...)");
               Shapes2D.drawShape(
                  var79, this.x - 3.0F, this.y, this.I, 20.0F + 14.0F * this.Z.getValue(), 0.0F, 0.0F, 1.0F, 1.0F, var59, var61, 7.0F, var15, 7.0F, 0.0F
               );
               if (!var44) {
                  break;
               }
            }

            Matrix3x2fStack var80 = context.getMatrices();
            Intrinsics.checkNotNullExpressionValue(var80, "pose(...)");
            Shapes2D.drawShape$default(
               var80,
               (char)var35,
               this.x - 3.0F,
               this.y,
               this.I,
               19.0F + 14.0F * this.Z.getValue(),
               0.0F,
               0.0F,
               1.0F,
               1.0F,
               var59,
               var61,
               0.0F,
               (short)var36,
               0.0F,
               var37,
               0.0F,
               14336,
               null
            );
            break;
         }
      }

      RenderUtil var81 = RenderUtil.INSTANCE;
      Color var94 = Color.BLACK;
      Intrinsics.checkNotNullExpressionValue(Color.BLACK, "BLACK");
      Color var65 = var81.blend(var94, Theme.INSTANCE.getTextColor(), factor);
      float var109;
      int var82 = (var109 = this.F.getValue() - 0.0F) == 0.0F ? 0 : (var109 < 0.0F ? -1 : 1);
      if (!var44) {
         var82 = !var82;
      }

      label202: {
         label201:
         if (!var82) {
            var83 = KeyboardUtil.isKeyPressed((char)var23, 341);
            if (!var44) {
               if (!var83) {
                  break label201;
               }

               var83 = KeyboardUtil.isKeyPressed((char)var23, 340);
            }

            if (var44) {
               break label202;
            }

            if (var83) {
               var83 = true;
               break label202;
            }
         }

         var83 = false;
      }

      label279: {
         boolean var48 = var83;
         long var84 = System.currentTimeMillis() - this.r;
         if (!var44) {
            if (var84 >= 400L && !var48) {
               var85 = 0.0F;
               break label279;
            }

            var84 = System.currentTimeMillis();
         }

         var85 = Math.sin(var84 / 15.0) * (var48 ? 0.5F : 1.0F);
      }

      float var102;
      String var10002;
      float var10003;
      float var10004;
      label183: {
         label280: {
            Object var67 = var85;
            var86 = Fonts.getSF_M2();
            var95 = context.getMatrices();
            Intrinsics.checkNotNullExpressionValue(var95, "pose(...)");
            var10002 = this.module.getDisplayName();
            var10003 = this.x + 4.0F + ((Number)var67).floatValue();
            var10004 = (this.I - 8.0F) / 2.0F - Fonts.getSF_M2().getWidth(var42, this.module.getDisplayName()) / 2.0F;
            Object var10005 = UI.INSTANCE;
            if (!var44) {
               if (UI.INSTANCE.getModuleText() == UIModuleTextMode.LEFT) {
                  var102 = 0.0F;
                  break label183;
               }

               var101 = this;
               if (var44) {
                  break label280;
               }

               var10005 = this.module;
            }

            if (((Module)var10005).getBind().getKey() == -1) {
               var102 = 1.0F;
               break label183;
            }

            var101 = this;
         }

         var102 = var101.E.getValue();
      }

      label282: {
         var10003 += var10004 * var102;
         var10004 = this.y + this.J / 2.0F - 7.0F;
         RenderUtil var103 = RenderUtil.INSTANCE;
         float var53 = 1.0F - this.Z.getValue();
         FontRenderer.drawString$default(var86, var95, var10002, var10003, var10004, var19, var103.withAlpha(var65, var53), false, null);
         float var110;
         var87 = (var110 = this.E.getValue() - 1.0F) == 0.0F ? 0 : (var110 < 0.0F ? -1 : 1);
         label169:
         if (!var44) {
            if (var87 < 0) {
               var87 = this.l;
               if (var44) {
                  break label169;
               }

               if (!this.l) {
                  String var69 = KeyNames.INSTANCE.getKeyName(this.module.getBind().getKey());
                  var87 = this.module.getBind().getKey();
                  if (var44) {
                     break label169;
                  }

                  if (var87 != -1) {
                     FontRenderer var88 = Fonts.getSF_M2();
                     Matrix3x2fStack var96 = context.getMatrices();
                     Intrinsics.checkNotNullExpressionValue(var96, "pose(...)");
                     var10003 = this.x + this.I - 10.0F - Fonts.getSF_M2().getWidth(var42, var69);
                     var10004 = this.y + this.J / 2.0F - 7.0F;
                     RenderUtil var104 = RenderUtil.INSTANCE;
                     Color var10006 = Theme.INSTANCE.getInactiveTextColor();
                     float var57 = 1.0F - this.E.getValue();
                     FontRenderer.drawString$default(var88, var96, var69, var10003, var10004, var19, var104.withAlpha(var10006, var57), false, null);
                  }
               }
            }

            var89 = this;
            if (var44) {
               break label282;
            }

            float var111;
            var87 = (var111 = this.Z.getValue() - 0.0F) == 0.0F ? 0 : (var111 < 0.0F ? -1 : 1);
         }

         if (var87 <= 0) {
            return;
         }

         var89 = this;
      }

      var89.p(context, mouseX, mouseY, var65, var27, var61);
   }

   private final void p(DrawContext var1, int var2, int var3, Color var4, long var5, Color var7) {
      int var12;
      int var13;
      int var14;
      long var17;
      long var21;
      float var48;
      label69: {
         var5 = b ^ var5;
         long var10001 = var5 ^ 26819030862342L;
         var12 = (int)((var5 ^ 26819030862342L) >>> 48);
         var13 = (int)((var5 ^ 26819030862342L) << 16 >>> 48);
         var14 = (int)(var10001 << 32 >>> 32);
         var17 = var5 ^ 134045504464585L;
         var21 = var5 ^ 107891284456017L;
         float var31 = this.x + 58.0F;
         float var32 = this.x + 58.0F + this.I / 2.0F - 6.0F;
         float var33 = var2;
         if (var31 <= var33 ? var33 <= var32 : false) {
            var31 = this.y + 15.0F;
            var32 = this.y + 27.0F;
            var33 = var3;
            if (var31 <= var33 ? var33 <= var32 : false) {
               var48 = 1.0F;
               break label69;
            }
         }

         var48 = 0.0F;
      }

      label62: {
         this.w.animate(var48);
         float var38 = this.x + 2.0F;
         float var41 = this.x + 2.0F + this.I / 2.0F - 6.0F;
         float var44 = var2;
         if (var38 <= var44 ? var44 <= var41 : false) {
            var38 = this.y + 15.0F;
            var41 = this.y + 27.0F;
            var44 = var3;
            if (var38 <= var44 ? var44 <= var41 : false) {
               var48 = 1.0F;
               break label62;
            }
         }

         var48 = 0.0F;
      }

      this.v.animate(var48);
      this.m.animate(this.a ? 1.0F : 0.0F);
      RenderUtil.INSTANCE.beginScissor(var1, new Rect(this.x, this.y, this.x + this.I, this.y + this.J));
      FontRenderer var10000 = Fonts.getSF_M2();
      Matrix3x2fStack var50 = var1.getMatrices();
      Intrinsics.checkNotNullExpressionValue(var50, "pose(...)");
      String var10002 = StringsKt.dropLast("Press any key                    ...", 3 - (int)(4.0 * (System.currentTimeMillis() % 700L / 700.0)));
      float var10003 = this.x + 3.0F + 60.0F * (1.0F - this.Z.getValue());
      float var10004 = this.y + 2.0F;
      RenderUtil var10005 = RenderUtil.INSTANCE;
      float var35 = this.Z.getValue() - 0.1F;
      FontRenderer.drawString$default(var10000, var50, var10002, var10003, var10004, var21, var10005.withAlpha(var4, var35), false, null);
      Matrix3x2fStack var46 = var1.getMatrices();
      Intrinsics.checkNotNullExpressionValue(var46, "pose(...)");
      Shapes2D.drawShape$default(
         var46,
         (char)var12,
         this.x + 2.0F,
         this.y + 17.0F,
         this.I / 2.0F - 6.0F,
         12.0F,
         3.0F,
         2.5F + this.v.getValue() / 2.0F,
         1.0F - this.m.getValue(),
         1.0F,
         RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), 1.0F - this.m.getValue()),
         var7,
         0.0F,
         (short)var13,
         0.0F,
         var14,
         0.0F,
         14336,
         null
      );
      Fonts.getSF()
         .drawStringWithShadow(
            var1,
            "Toggle",
            var17,
            this.x + 15.0F,
            this.y + 17.0F,
            RenderUtil.INSTANCE
               .blend(Theme.INSTANCE.getInactiveTextColor(), Theme.INSTANCE.getTextColor(), this.v.getValue() + (1.0F - this.m.getValue()) / 2.0F)
         );
      Matrix3x2fStack var47 = var1.getMatrices();
      Intrinsics.checkNotNullExpressionValue(var47, "pose(...)");
      Shapes2D.drawShape$default(
         var47,
         (char)var12,
         this.x + 58.0F,
         this.y + 17.0F,
         this.I / 2.0F - 6.0F,
         12.0F,
         3.0F,
         2.5F + this.w.getValue() / 2.0F,
         this.m.getValue(),
         1.0F,
         RenderUtil.INSTANCE.blend(Theme.INSTANCE.i(), Theme.INSTANCE.getModuleColor(), this.m.getValue()),
         var7,
         0.0F,
         (short)var13,
         0.0F,
         var14,
         0.0F,
         14336,
         null
      );
      Fonts.getSF()
         .drawStringWithShadow(
            var1,
            "Hold",
            var17,
            this.x + 76.0F,
            this.y + 17.0F,
            RenderUtil.INSTANCE.blend(Theme.INSTANCE.getInactiveTextColor(), Theme.INSTANCE.getTextColor(), this.w.getValue() + this.m.getValue() / 2.0F)
         );
      RenderUtil.INSTANCE.endScissor(var1);
   }

   public final void D(short a, int a, double mouseX, int a, double mouseY, int button) {
      long var9 = ((long)ax << 48 | (long)axxx << 32 >>> 16 | (long)axx << 48 >>> 48) ^ b;
      long var13 = var9 ^ 56722196773611L;
      int var19 = (int)((var9 ^ 22714845446275L) << 48 >>> 48);
      long var20 = var9 ^ 8924772868990L;
      boolean var26 = SettingElement.e();
      ModuleButton var10000 = this;
      if (!var26) {
         label210:
         if (this.l) {
            label242: {
               float var27 = this.x + 58.0F;
               float var28 = this.x + 58.0F + this.I / 2.0F - 6.0F;
               float var29 = (float)mouseX;
               float var50;
               var41 = (var50 = var27 - var29) == 0.0F ? 0 : (var50 < 0.0F ? -1 : 1);
               if (!var26) {
                  if (var41 > 0) {
                     var41 = 0;
                     break label242;
                  }

                  float var51;
                  var41 = (var51 = var29 - var28) == 0.0F ? 0 : (var51 < 0.0F ? -1 : 1);
               }

               if (!var26) {
                  var41 = var41 <= 0 ? 1 : 0;
               }
            }

            float var36;
            float var39;
            label236: {
               if (var41 != 0) {
                  label233: {
                     float var30 = this.y + 15.0F;
                     var36 = this.y + 27.0F;
                     var39 = (float)mouseY;
                     float var52;
                     var42 = (var52 = var30 - var39) == 0.0F ? 0 : (var52 < 0.0F ? -1 : 1);
                     if (!var26) {
                        if (var42 > 0) {
                           var42 = 0;
                           break label233;
                        }

                        float var53;
                        var42 = (var53 = var39 - var36) == 0.0F ? 0 : (var53 < 0.0F ? -1 : 1);
                     }

                     if (!var26) {
                        var42 = var42 <= 0 ? 1 : 0;
                     }
                  }

                  if (var42 != 0) {
                     var43 = button;
                     if (var26) {
                        break label236;
                     }

                     if (button == 0) {
                        this.a = true;
                        if (!var26) {
                           break label210;
                        }
                     }
                  }
               }

               float var31 = this.x + 2.0F;
               var36 = this.x + 2.0F + this.I / 2.0F - 6.0F;
               var39 = (float)mouseX;
               float var54;
               var43 = (var54 = var31 - var39) == 0.0F ? 0 : (var54 < 0.0F ? -1 : 1);
            }

            label223: {
               if (!var26) {
                  if (var43 > 0) {
                     var43 = 0;
                     break label223;
                  }

                  float var55;
                  var43 = (var55 = var39 - var36) == 0.0F ? 0 : (var55 < 0.0F ? -1 : 1);
               }

               if (!var26) {
                  var43 = var43 <= 0 ? 1 : 0;
               }
            }

            if (var43 != 0) {
               label215: {
                  float var32 = this.y + 15.0F;
                  var36 = this.y + 27.0F;
                  var39 = (float)mouseY;
                  float var56;
                  var44 = (var56 = var32 - var39) == 0.0F ? 0 : (var56 < 0.0F ? -1 : 1);
                  if (!var26) {
                     if (var44 > 0) {
                        var44 = 0;
                        break label215;
                     }

                     float var57;
                     var44 = (var57 = var39 - var36) == 0.0F ? 0 : (var57 < 0.0F ? -1 : 1);
                  }

                  if (!var26) {
                     var44 = var44 <= 0 ? 1 : 0;
                  }
               }

               if (var44 != 0 && button == 0) {
                  this.a = false;
                  if (!var26) {
                     break label210;
                  }
               }
            }

            this.module.setBind(new Bind(button, this.a, true));
            this.l = false;
         }

         var10000 = this;
      }

      label202: {
         float var33 = var10000.x;
         double var58;
         var45 = (var58 = mouseX - (this.x + this.I - 6.0F)) == 0.0 ? 0 : (var58 < 0.0 ? -1 : 1);
         if (!var26) {
            if (var45 > 0) {
               var45 = 0;
               break label202;
            }

            double var59;
            var45 = (var59 = var33 - mouseX) == 0.0 ? 0 : (var59 < 0.0 ? -1 : 1);
         }

         if (!var26) {
            var45 = var45 <= 0 ? 1 : 0;
         }
      }

      if (var45 != 0) {
         label194: {
            float var34 = this.y;
            double var60;
            var46 = (var60 = mouseY - (this.y + this.J)) == 0.0 ? 0 : (var60 < 0.0 ? -1 : 1);
            if (!var26) {
               if (var46 > 0) {
                  var46 = 0;
                  break label194;
               }

               double var61;
               var46 = (var61 = var34 - mouseY) == 0.0 ? 0 : (var61 < 0.0 ? -1 : 1);
            }

            if (!var26) {
               var46 = var46 <= 0 ? 1 : 0;
            }
         }

         if (var46 != 0) {
            int var47 = this.l;
            if (!var26) {
               if (this.l) {
                  return;
               }

               var47 = KeyboardUtil.isKeyPressed((char)var19, 341);
            }

            label182:
            if (!var26) {
               if (var47 != 0) {
                  var47 = KeyboardUtil.isKeyPressed((char)var19, 340);
                  if (var26) {
                     break label182;
                  }

                  if (var47 != 0) {
                     Module var48 = this.module;
                     if (!var26) {
                        if (this.module.getEnabled()) {
                           this.module.disable(var20);
                        }

                        var48 = this.module;
                     }

                     for (Setting var38 : var48.getSettings()) {
                        var38.reset();
                     }

                     return;
                  }
               }

               var47 = KeyboardUtil.isKeyPressed((char)var19, 342);
            }

            if (!var26) {
               if (var47 != 0) {
                  this.l = true;
                  if (!var26) {
                     return;
                  }
               }

               var47 = button;
            }

            switch (var47) {
               case 0:
                  this.module.toggle(var13);
                  if (!var26) {
                     break;
                  }
               case 1:
                  boolean var49 = this.X.isEmpty();
                  if (!var26) {
                     var49 = !var49;
                  }

                  if (var49) {
                     Sounds.INSTANCE.getSettingsOpen().play();
                     this.catComponent.setModuleButton(this);
                     if (!var26) {
                        break;
                     }
                  }

                  this.j();
                  if (!var26) {
                     break;
                  }
               case 2:
                  this.l = true;
            }
         }
      }
   }

   public final void j() {
      Sounds.INSTANCE.getNoSettings().play();
      this.r = System.currentTimeMillis();
   }

   public final void R(short a, short a, int key, int a) {
      long var5 = ((long)axxx << 48 | (long)ax << 48 >>> 16 | (long)axx << 32 >>> 32) ^ b;
      long var10001 = var5 ^ 112449341166161L;
      int var9 = (int)((var5 ^ 112449341166161L) >>> 48);
      int var10 = (int)((var5 ^ 112449341166161L) << 16 >>> 32);
      int var11 = (int)(var10001 << 48 >>> 48);
      if (Intrinsics.areEqual(this.catComponent.getModuleButton(), this)) {
         for (Object var16 : this.X) {
            SettingElement var17 = (SettingElement)var16;
            var17.keyPressed(key, (short)var9, var10, (char)var11);
         }
      }

      if (this.l) {
         switch (key) {
            case 256:
            case 261:
               this.module.setBind(new Bind(-1, this.a, false));
               break;
            default:
               this.module.setBind(new Bind(key, this.a, false));
         }

         this.l = false;
      }
   }

   public final void l(char char) {
      boolean var4 = SettingElement.e();
      ModuleButton var10000 = this.catComponent.getModuleButton();
      if (!var4) {
         if (!Intrinsics.areEqual(var10000, this)) {
            return;
         }

         var10000 = this;
      }

      for (Object var8 : var10000.X) {
         SettingElement var9 = (SettingElement)var8;
         var9.charTyped(char);
         if (var4 || var4) {
            break;
         }
      }
   }

   public final boolean v(double mouseX, double mouseY, double verticalAmount) {
      boolean var11 = false;
      if (Intrinsics.areEqual(this.catComponent.getModuleButton(), this)) {
         for (Object var15 : this.X) {
            SettingElement var16 = (SettingElement)var15;
            if (var16.getSetting().getGroup() != null) {
               SettingParent var10000 = var16.getSetting().getGroup();
               Intrinsics.checkNotNull(var10000);
               if (!var10000.isExtended()) {
                  continue;
               }

               var10000 = var16.getSetting().getGroup();
               Intrinsics.checkNotNull(var10000);
               if (var10000.getType() == AuraTargetsMode.BOOLS) {
                  continue;
               }
            }

            if ((Boolean)var16.getSetting().getVisible().invoke() && var16.mouseScrolled(mouseX, mouseY, verticalAmount)) {
               var11 = true;
            }
         }
      }

      return var11;
   }

   public final float M(@NotNull SettingParent group) {
      Intrinsics.checkNotNullParameter(group, "group");
      if (!group.isExtended() && group.getType() != AuraTargetsMode.BOOLS) {
         return 13.0F;
      } else {
         float var3 = 13.0F;
         Iterable var5 = this.X;
         var var8 = new ArrayList();

         for (Object var11 : var5) {
            SettingElement var12 = (SettingElement)var11;
            if (Intrinsics.areEqual(var12.getSetting().getGroup(), group) && (Boolean)var12.getSetting().getVisible().invoke()) {
               var8.add(var11);
            }
         }

         List var4 = var8;

         for (Object var14 : var4) {
            SettingElement var9 = (SettingElement)var14;
            var3 += var9.getHeight() + 2.0F;
         }

         return var3;
      }
   }

   static {
      long var22 = b ^ 70540637732069L;
      Cipher var24;
      Cipher var10000 = var24 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var25 = 1; var25 < 8; var25++) {
         var10003[var25] = (byte)(var22 << var25 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var31 = new String[28];
      int var29 = 0;
      String var28 = "Ç5@õà¶*¶{ò\u0011¬g¨ÚßSZÏ>¬Ø\u001dh>ñ<C\u008a#K2³\u0083\u0012'Î& \u009cË\u0095S\u001a+4\u0000\u008eÚ'\u00ad,®\u0094ÖâÏ\u0002\u0093t\u001b\u009a¦/ ¶=ãLî\u0091°gìþ\u00ad8\\£\u00900ÂÉìD]\u0086'ñ]÷Ãi\u0098¼\u0086{Ð\u0016\u001f9\u0082nû\u0094\u00059\u009aÿ®\u0010Y\u0012Êÿ\u009fÌÐ¯W\u0095ÔmrÉ\u00ad\u0096\u0084J\u009c\u0000?\u0016ÛÌÔ\u0010·('@n6ôExkGû)Ç^ª\u0010¹BÌ\u0010\u000f\u008c\u009d8E7\u001dÔ\u001d\u008e¯û\u0010¯\u0003©®bÍÍøÐv ü!ñ\u0003m¨»\u0017\u0003öqîK»úòÇºïé\u008bP¦3tu!ì½áu×À\u008f?áð¢ºæ#\u0095ëJxô\u0097ópÞ\u000e\u0017Íâ\u0019¨\u009e¥Â*ã\n:|\b¡üË0³ +Àñp ñû89Øéè\u0091\u0001>àc\u0082`ýÂ\"Û\u0094Ü\u008f\fK\u008f\t\u0013Ë\r@\u001cÕßÞ\u0096/Sò·\\q\u0097ä=ÚìþèÝÏyõâ¤\u0080ÕX+N£\u0005=iJHçè¬\u001e\u0098\u0003¸;ù©zú(\u00122c&e\u0004ÔuÑ»Í)é²\u000e\u008c¿\u0018Û®Â°Ï¾ï \u0080\u001b\u0004ÌÛ\u008aÜ\u0092\u0094Ò\n[\u001c¹\u008b½M\u007f§q\u0089Î\u001a£æ\u000224,×s\fùòÊ\u009b{µæ\u009fÏñ^5LÇù[9nH¥²Q¾öÂÆE§Gê\u0084¡¿|ÞIÑY[|E7«#é\u0015\u0091\r{#[:³\u009drFu\u009a\u000eã+\fæ\u0087ÒÆ_v\u000f=ïÃ\u0089\u0093\u000bö¢Â4Z]\u009c\u009c\u0013Y\u001ad\u0087\u008d0¯\u0002&÷Û\u0010@_Ûì\\\fr\u000b·þÿ\bbûÑÔý'\u0096£±¤è\u009f\u0013¬ÛT\u001c]\u001a\u0098V\nC³\u0080\u000bï\bÓÕO,¨\f!~Ó\u008bu\u0005>i\u0002<è×¯9«SQÆôdÈû\t\u0088¢ñ½Ð\u0085¹\u0085Un\u001dòßøðNDìéE÷çÀ\u0083ÝÅ4\u000e\rþt<Å ÷»\u0006\u0098Íª\u009bx\u0098%´\u0089+<\u0092[l¸Ôz3¼\u0007¤hÆ\u0005ÄkÙb ®A\u008cL¬Á\u0090\u0001SÆA\fü\u0018K\u0014UI\u00adÁ+Ù¥>9÷¹\u008dx´õÔ?b-Ag¤nÈ´_\u0001\u0080¯yµg\u009c%\u0010±\u009fðUÜ\u0013{©þ±#.$6Æ\u0088ï°Ø°1e!\fCS\u0005@¦OÏ\u0089/Õ\u0090\u0010òCAÓÿóü÷)¾ªÒØ8Ù\u0004+x\t«þ\u0090\u007fezÌé«ºKª~\u000e®Èd\u0013$M\u008dá\u0087Ï\rUï>\u008a¿â9Â¬¨p\u0093 CÃa-\u000e\u0015\u001eñT´@9â?\u000ePØ\u0095WãìÕ\u0010øØGª(]ú\u0087\u001c\u0010ÊÌ\u0087÷¨\u0012\u00ad\u0010\u0088}ì4ÁáI\u0082ÀÐÇ\nIG\u0088d\u0005ÁôÃ½]X\fÆ\u008do\u008d¢lgr\u007fÔÖ¼s*|´`?\u0002^!yë8\u000bÝø[\u009fµ\u0096\u0016\u007fð.~Üªziúk\u0001nÁ\u0002\u0093d\u0089 Ñø\u0081°u®I\\¢*\u0085\u001cáÅËc@\u001f¡^Ai²\u001f\u0095\u0095~O&N\u0085\u0005¥ó\u0093&×ª\u0091èqc¶Á¬\u0016¹3|²£\u001d\u0014\u0016\u0001\u009e\u007f\u009c»=¿¶ñÃÿM÷\u000bmin¹ó\byu\u0091\u0085\fíTý|\u007f\u001f¿¯\u0006]x÷\\f\u0087\u0088\u009d\u0012\u009aº@Ô{\u0099&4Óh®øÁÁ\u009a3ÒÍ\u009d°R'\u0013\u001bÖ\u000fSkjÿ\u0010 \u009f\u0082-±R-,pû\u009b\u009b\u009dûÔ\u001d áR?¢à\u0098|\u0095o´)¸Â}¬®~i\u0081é\u000eÛu\u0019ÆÎºk2åt\u0085\u0088w\rÎÁßß¼f\u0005,2<ÅKøûSR \u009ej/Ãá\u0096\u0090\u0003\u0081\u001eS×¥:wFÑ\u0089bÏ¥tÛô\u0001J\u0082\u008b\t?Iñ+³g\u000b\u008d×\u007fv\u008b·´/Í\"\u001f\u0089ã\u0013HÐÅ\u0087{ô¡;G\u0007S^ª#øò_¸ÿ(?øÑ+Û<\bF8ÿ\u009fº!CÒ\u0019\u0091Ú»ñ¼ñ]ÉFÐ\u0090Ç\u0084ÏÖùHÊZÅ¹©(8ÕLa\f\u0087\u0017Ì\u0018\u0006h*ÖF>Pj9\u001e% ¶Cê\u0086\u0000y\u009cRk\u0019\u0019þ\u0010+YÕzÿö¶ìÛLFÄViôZ°9ù¾ñ?sÞ£\u000b[&\u009a\u0003S03\t÷\u0084«JNò\u008f:½Zµ\u0098ÑÐ y\u0082\u008e\u0019`<\u001f¹ãM\u0086\u0088Ð\u008c\u0015áHàf\u001dâTÕ\u0095\u000b\tw¬\u0012 \u0098§¦\u0081ædBîÍ«\u001c\u0012D\";í\u008d\u0001Ó¥WpÐJ\u008e£¦¶ô(Ýé.\u009d/tªhÏ\u001e\u0005Ñ¦l»æÞÖ´$üÁ²\u0082\u0012TRthgÉ± 0TçåÜ¼eÏIÎrÚwÄ'\u0006\u0082\u000f}é\u0090¥:\u0082\u008eà\u0002Ú\u00162\u000ej\u0011G\u0097\u0093*\u0099F\u000bñ\u0018\u0087Ï\u0005é¬\u009e¢àëX±Z\u0090%5(\u0002ëT÷\u001eòðx\u001e§¥;%£ð\u0004*Xí\u0014\u009e¤¢º!æ7?Ì¢þ\u0016t±\u000fùÉrjg paÆ\u009câßÙrU&ô\u001aò\u008a´}ð\u0099ÄÜù\u00915!ì±µf\u0088é\u0000+\u0015Ë~OÀr«\u0000\u009c QD$TÆµ¨\n\u0083\u001fZ$S1ìE\u009fÕ7ÅP»I~\u00825XûW'¸\u0016PÉÌñÀ\u00071z{W\u0091$/\u001a\u001e¾:#Á\rUÕ\u0092Êéf\u0003'å6\u0086\u009aÈ\u0095\u008f\u0019\u0006V=É¼\u0000\u0091;àÀ\u0018\u0085/\u009fï7«\u0089Bû\u0084Kì \u007f4ð\u000buð\u0017\u008e©\f\u0093ð \fÏæ£NDfÛ\u0082,ð\u009a|ðp\u008c)\u009e9¯ÚZªê¶Å¥\u008e:ì³\u009e=Ôÿv`¹'.ç\u0090ùðç\u0012ÆÔ\u00ad©¾\u0084-\u0005%\u0010Ñ\u0094ÏJO\u001fN\u0006\u0018¦\u0019Âû\u0097ØsX\u000bË#¼#\u0099\u0088\u0001ªòÆAôÜ\u009e[l\"\u0015<@g\u0002\u008fÙE\u0093\u001eÝµ·t@·©p?.©\u0093Ö<L\u0002\u008eî|\u0095W\u001f\u0090ªÏ\u008d^ÙÀm[\u0007ô¬ãp\u0091tüOYÃo\u00956§ë³\u0093t¶\u0018:º\u008f¿ðS2Q\u0080c\u0083d\r\u0088\u0090·\\\n\u0080Ãg°\u0087ñ7>%±\u0099\u0085`$\"(?\t«\u008bÆÙò \u009d\u001eìDs¿\u0013\u0014¤0ij\u008e0ZH´Êªýt¸\u000eÚ&ÁY<\u0005Üÿ¡(ñ!¹\u0083ï£\u008e ÎØ:û&²ÚÎf!\u0087§zÇoÆ7×÷½\u0000\u0099\b{g\b÷\u0097\r!àóY\u001a\u009f\u0012ªôÀ¢0\u001c\u0098y)±pÄÓwí%.×\u0080±¸\u009aaTùD «\u009f\u0001ú\u0087\u000eu2ÊÛ¢\u0090Rúø¨\\\u008f\u0088\u009fg\u0096\u009aÌ¥·\u001f¸\u0010jÞÁð\u0091£ØóÚ\u008c[\u0002\u001czSê¼V\u0012¶*~\u0097yaFñTÀ\u008e\u0012\u008e\u009b¨~1C>,©úv×Gò\u0010¡À¦)å$\u0094NÔSâØl<Î\u0007\u009d\u008c±v|ù\u0015ì\u0081Ò:2:A}s\\Ñ\u0018'u©k#ëÒ\r\u0095Î\u0010#è\u0087÷\u0091{Âë\u0015m\r@\u001a\u008eÝô\u0010Ý.\u000e×+¡{^aWk\u0081)\u0014\u009b.\u0090$#¬&`RTÞ\u0093âq\u0016\u0004$Câ\u00848þã\u0017î\u0010¶>Rò\u008dÀ?jï´\u007føJ\u0018\"w\u0098òy\u00810Q¤|}L\r\u0098|n)úx\u000eM\u0019©\u000b¢Þ_ dÙ\u0002'u\u007fâºXmñã6«ûDÀ\u0082½5/àLAhø\u0013yZ_\u0011\n¤<Ú\r\u0015.=×óÌ¦¸I\u0018\u0088Å\ts\u0005ó²l\u001bf\u009fç\u0094rÜH¢Ýã\u008f\u0087ÚBûÐÓ\u008fâSuJø\u0001";
      short var30 = 2209;
      char var27 = 136;
      int var36 = -1;

      label81:
      while (true) {
         String var37 = var28.substring(++var36, var36 + var27);
         int var10001 = -1;

         while (true) {
            byte[] var32 = var24.doFinal(var37.getBytes("ISO-8859-1"));
            String var53 = zkmDecodeUtf8(var32).intern();
            switch (var10001) {
               case 0:
                  var31[var29++] = var53;
                  if ((var36 += var27) >= var30) {
                     c = var31;
                     d = new String[28];
                     h = new HashMap(13);
                     Cipher var11;
                     var10000 = var11 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var12 = 1; var12 < 8; var12++) {
                        var10003[var12] = (byte)(var22 << var12 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var17 = new long[14];
                     int var14 = 0;
                     String var15 = ";\u0010\u0015\n\u008a\u009c\u008bð»Ä6\\rw&»I\u0018ï\u0088\u009e¿5qø¨ìLýp;\b^±å=°\u001c]\u0087n\u001dê=\u0017\u0003\u001e×Z%C\u008e\u0099é@\u009eúu+:\u0081®ò\u0002½\u0088ÐGû¢½J\u0011þÇÎ\u0086\u0095ß5\u0092<Ü\u0093\u0011ÿ\u0081È\u0098¹üÁËÑVw";
                     byte var16 = 96;
                     byte var13 = 0;

                     label63:
                     while (true) {
                        var10001 = var13;
                        var13 += 8;
                        byte[] var18 = var15.substring(var10001, var13).getBytes("ISO-8859-1");
                        long[] var40 = var17;
                        var10001 = var14++;
                        long var57 = (var18[0] & 255L) << 56
                           | (var18[1] & 255L) << 48
                           | (var18[2] & 255L) << 40
                           | (var18[3] & 255L) << 32
                           | (var18[4] & 255L) << 24
                           | (var18[5] & 255L) << 16
                           | (var18[6] & 255L) << 8
                           | var18[7] & 255L;
                        byte var63 = -1;

                        while (true) {
                           long var19 = var57;
                           byte[] var21 = var11.doFinal(
                              new byte[]{
                                 (byte)(var19 >>> 56),
                                 (byte)(var19 >>> 48),
                                 (byte)(var19 >>> 40),
                                 (byte)(var19 >>> 32),
                                 (byte)(var19 >>> 24),
                                 (byte)(var19 >>> 16),
                                 (byte)(var19 >>> 8),
                                 (byte)var19
                              }
                           );
                           long var68 = (var21[0] & 255L) << 56
                              | (var21[1] & 255L) << 48
                              | (var21[2] & 255L) << 40
                              | (var21[3] & 255L) << 32
                              | (var21[4] & 255L) << 24
                              | (var21[5] & 255L) << 16
                              | (var21[6] & 255L) << 8
                              | var21[7] & 255L;
                           switch (var63) {
                              case 0:
                                 var40[var10001] = var68;
                                 if (var13 >= var16) {
                                    f = var17;
                                    g = new Integer[14];
                                    n = new HashMap(13);
                                    Cipher var0;
                                    var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                                    var10002 = SecretKeyFactory.getInstance("DES");
                                    var10003 = new byte[]{(byte)(var22 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                                    for (int var1 = 1; var1 < 8; var1++) {
                                       var10003[var1] = (byte)(var22 << var1 * 8 >>> 56);
                                    }

                                    var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                    long[] var6 = new long[4];
                                    int var3 = 0;
                                    String var4 = "\u00946âÏ\u0014y\u008b\u0017\u0004<P\u0018b¶\u00036";
                                    byte var5 = 16;
                                    byte var2 = 0;

                                    label47:
                                    while (true) {
                                       int var49 = var2;
                                       var2 += 8;
                                       byte[] var7 = var4.substring(var49, var2).getBytes("ISO-8859-1");
                                       long[] var42 = var6;
                                       var49 = var3++;
                                       long var60 = (var7[0] & 255L) << 56
                                          | (var7[1] & 255L) << 48
                                          | (var7[2] & 255L) << 40
                                          | (var7[3] & 255L) << 32
                                          | (var7[4] & 255L) << 24
                                          | (var7[5] & 255L) << 16
                                          | (var7[6] & 255L) << 8
                                          | var7[7] & 255L;
                                       byte var66 = -1;

                                       while (true) {
                                          long var8 = var60;
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
                                          var68 = (var10[0] & 255L) << 56
                                             | (var10[1] & 255L) << 48
                                             | (var10[2] & 255L) << 40
                                             | (var10[3] & 255L) << 32
                                             | (var10[4] & 255L) << 24
                                             | (var10[5] & 255L) << 16
                                             | (var10[6] & 255L) << 8
                                             | var10[7] & 255L;
                                          switch (var66) {
                                             case 0:
                                                var42[var49] = var68;
                                                if (var2 >= var5) {
                                                   i = var6;
                                                   k = new Long[4];
                                                   return;
                                                }
                                                break;
                                             default:
                                                var42[var49] = var68;
                                                if (var2 < var5) {
                                                   continue label47;
                                                }

                                                var4 = "\u008bo\u001b ó\t\u0084¬Xêó\u0019®k\u009d\u0089";
                                                var5 = 16;
                                                var2 = 0;
                                          }

                                          byte var51 = var2;
                                          var2 += 8;
                                          var7 = var4.substring(var51, var2).getBytes("ISO-8859-1");
                                          var42 = var6;
                                          var49 = var3++;
                                          var60 = (var7[0] & 255L) << 56
                                             | (var7[1] & 255L) << 48
                                             | (var7[2] & 255L) << 40
                                             | (var7[3] & 255L) << 32
                                             | (var7[4] & 255L) << 24
                                             | (var7[5] & 255L) << 16
                                             | (var7[6] & 255L) << 8
                                             | var7[7] & 255L;
                                          var66 = 0;
                                       }
                                    }
                                 }
                                 break;
                              default:
                                 var40[var10001] = var68;
                                 if (var13 < var16) {
                                    continue label63;
                                 }

                                 var15 = "ôY\u0017Ó¢1\u00ad\u0090O\u001bÒþãÏsÇ";
                                 var16 = 16;
                                 var13 = 0;
                           }

                           byte var48 = var13;
                           var13 += 8;
                           var18 = var15.substring(var48, var13).getBytes("ISO-8859-1");
                           var40 = var17;
                           var10001 = var14++;
                           var57 = (var18[0] & 255L) << 56
                              | (var18[1] & 255L) << 48
                              | (var18[2] & 255L) << 40
                              | (var18[3] & 255L) << 32
                              | (var18[4] & 255L) << 24
                              | (var18[5] & 255L) << 16
                              | (var18[6] & 255L) << 8
                              | var18[7] & 255L;
                           var63 = 0;
                        }
                     }
                  }

                  var27 = var28.charAt(var36);
                  break;
               default:
                  var31[var29++] = var53;
                  if ((var36 += var27) < var30) {
                     var27 = var28.charAt(var36);
                     continue label81;
                  }

                  var28 = "\u009a\u0015Ql\u001a?JC\\²e¨¼\u001cl{\u0003ÜÎRHÂ°¤\u009a2ÚC\u008d\tÍ\u0007j$a\u0083hú«Ø·ÞÍd\u00802äï,HýÝ\u0086\u009f_çYÊÇ¢{¥M\u000b\u0016öÓS´/,w\u0015|bGf\u0084ôicRIS/Î\u0014ª\u0096´úÁ\u0081Æi\u0084Ätâ\u0015¦\u0097êÝ\u0013ßN6 \u0004bàt¦8@vû\u008cD6-ã\u000eØ@Fp\u0086RT\u0091 ¨ö5Í÷?\u001ff(\u001a\u0084 \u0098ë\u0017xäÁÞ1Ê\u0092\u009e\nàç}\u001fH\u0097Ç\u0096c\u0005\rÄGÉ\u0085!ÿì\u0019\u007fyH%î!®|ð\u0090\biã7yX\u0095\u0089S»)\u009f\u0091#\u008d\u0095\fû\\\u0090$Î<°è¡Ñ\u008fðm&àDcîâ+b¼\u008c+&®vX\u001b\u0007d\u008bIÓv\u008c\u0014\u0011\u001fÜB¡j\\Ù\u0093¡\u008aÅêÑWê ÝüÃ^ù+xäúÄ\u0096@C÷\t¸Oç\u007f\u000bð\u0004{\u009c\u0005\u0087¡lwâ\rx³yiímÞÙcë4÷_Z\u0001ï¸";
                  var30 = 305;
                  var27 = 144;
                  var36 = -1;
            }

            var37 = var28.substring(++var36, var36 + var27);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 16666;
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
            throw new RuntimeException("su/catlean/tw", var10);
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
         throw new RuntimeException("su/catlean/tw" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 7403;
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
            throw new RuntimeException("su/catlean/tw", var14);
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
         throw new RuntimeException("su/catlean/tw" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static long zkmDecryptLong(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 17854;
      if (k[var3] == null) {
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
         Object[] var9 = (Object[])n.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               n.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/tw", var14);
         }

         long var15 = (var10[0] & 255L) << 56
            | (var10[1] & 255L) << 48
            | (var10[2] & 255L) << 40
            | (var10[3] & 255L) << 32
            | (var10[4] & 255L) << 24
            | (var10[5] & 255L) << 16
            | (var10[6] & 255L) << 8
            | var10[7] & 255L;
         k[var3] = var15;
      }

      return k[var3];
   }

   private static long c(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
      int var4 = (Integer)var3[0];
      long var5 = (Long)var3[1];
      long var7 = zkmDecryptLong(var4, var5);
      MethodHandle var9 = MethodHandles.constant(long.class, var7);
      var1.setTarget(MethodHandles.dropArguments(var9, 0, int.class, long.class));
      return var7;
   }

   private static CallSite zkmBootstrap3(Lookup var0, String var1, MethodType var2) {
      MutableCallSite var3 = new MutableCallSite(var2);

      try {
         var3.setTarget(
            MethodHandles.explicitCastArguments(
               MethodHandles.insertArguments("c".asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("su/catlean/tw" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
