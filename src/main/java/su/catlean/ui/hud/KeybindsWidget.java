package su.catlean.ui.hud;

import java.awt.Color;
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
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.module.misc.UI;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.ui.ArrayListEntry;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.util.Globals;
import su.catlean.util.KeyNames;
import su.catlean.zkm.ZkmKeys;

public final class KeybindsWidget extends HudElement {
   @NotNull
   public static final KeybindsWidget J;
   @NotNull
   private static final List E;
   @NotNull
   private static final Animation animation;
   private static final long a = ZkmKeys.a(1619890557309310833L, -330321123597920234L, MethodHandles.lookup().lookupClass()).a(84612379644631L);
   private static final String[] b;
   private static final String[] c;
   private static final Map f = new HashMap(13);
   private static final long[] g;
   private static final Integer[] h;
   private static final Map i;

   private KeybindsWidget(short var1, short var2, int var3) {
      long var4 = ((long)var1 << 48 | (long)var2 << 48 >>> 16 | (long)var3 << 32 >>> 32) ^ a;
      int var6 = (int)((var4 ^ 46431451187230L) >>> 48);
      int var8 = (int)((var4 ^ 46431451187230L) << 48 >>> 48);
      super((char)var6, "keybinds-widget", (char)var8);
   }

   @NotNull
   public final List K() {
      return E;
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   @Override
   public void render(long a, @NotNull DrawContext context) {
      long var22 = ax ^ 130512158954640L;
      long var10001 = ax ^ 104104530525409L;
      int var24 = (int)((ax ^ 104104530525409L) >>> 48);
      int var25 = (int)((ax ^ 104104530525409L) << 16 >>> 32);
      int var26 = (int)(var10001 << 48 >>> 48);
      long var29 = ax ^ 128377113661390L;
      long var31 = ax ^ 20173347401993L;
      Intrinsics.checkNotNullParameter(context, "context");
      Iterable var39 = ModuleManager.INSTANCE.L();
      int var10000 = ArrayListEntry.u();
      var var42 = new ArrayList();
      boolean var37 = (boolean)var10000;
      Iterator var44 = var39.iterator();

      while (true) {
         if (var44.hasNext()) {
            var81 = var44;
            if (var37) {
               break;
            }

            Object var45 = var44.next();
            Module var46 = (Module)var45;
            var10000 = Intrinsics.areEqual(var46, UI.INSTANCE);
            if (!var37) {
               var10000 = !var10000;
            }

            if (!var10000) {
               continue;
            }

            var42.add(var45);
            if (!var37) {
               continue;
            }
         }

         var81 = var42.iterator();
         break;
      }

      Iterator var38 = var81;

      while (true) {
         if (var38.hasNext()) {
            Iterable var41;
            label297: {
               var60 = (Module)var38.next();
               var41 = E;
               var83 = var41;
               label274:
               if (!var37) {
                  boolean var84 = var41 instanceof Collection;
                  if (var37) {
                     break;
                  }

                  if (var84) {
                     var83 = (Collection)var41;
                     if (var37) {
                        break label274;
                     }

                     if (var83.isEmpty()) {
                        var10000 = (byte)1;
                        break label297;
                     }
                  }

                  var83 = var41;
               }

               Iterator var43 = var83.iterator();

               label266:
               while (true) {
                  if (!var43.hasNext()) {
                     var10000 = (byte)1;
                     break;
                  }

                  Object var67 = var43.next();
                  ArrayListEntry var72 = (ArrayListEntry)var67;
                  var10000 = Intrinsics.areEqual(var72.getName(), var60.getDisplayName());

                  while (var10000 != 0) {
                     var10000 = (byte)0;
                     if (!var37) {
                        break label266;
                     }
                  }
               }
            }

            byte var40 = var10000;
            var10000 = var60.getBind().getKey();
            label253:
            if (!var37) {
               if (var10000 != -1) {
                  var10000 = var60.getEnabled();
                  if (var37) {
                     break label253;
                  }

                  if (var10000 != 0) {
                     var10000 = var40;
                     if (var37) {
                        break label253;
                     }

                     if (var40 != 0) {
                        E.add(new ArrayListEntry(var60.getDisplayName(), KeyNames.INSTANCE.getKeyName(var60.getBind().getKey()), null, null, false, 28, null));
                        if (!var37) {
                           continue;
                        }
                     }
                  }
               }

               var10000 = var40;
            }

            if (!var37) {
               if (var10000 != 0) {
                  continue;
               }

               var10000 = var60.getBind().getKey();
            }

            label241:
            if (!var37) {
               if (var10000 != -1) {
                  boolean var87 = var60.getEnabled();
                  if (var37) {
                     break label241;
                  }

                  if (var87) {
                     continue;
                  }
               }

               var41 = E;
               boolean var88 = false;
            }

            var var68 = new ArrayList();

            label233: {
               for (Object var47 : var41) {
                  ArrayListEntry var48 = (ArrayListEntry)var47;
                  boolean var89 = Intrinsics.areEqual(var48.getName(), var60.getDisplayName());
                  if (var37) {
                     break label233;
                  }

                  label229: {
                     label303: {
                        if (!var37) {
                           if (!var89) {
                              break label303;
                           }

                           var89 = Intrinsics.areEqual(var48.getExtra(), "");
                        }

                        if (var37) {
                           break label229;
                        }

                        if (var89) {
                           var89 = true;
                           break label229;
                        }
                     }

                     var89 = false;
                  }

                  if (var89) {
                     var68.add(var47);
                     if (var37) {
                        break;
                     }
                  }
               }

               var41 = var68;
               boolean var90 = false;
            }

            for (Object var69 : var41) {
               ArrayListEntry var73 = (ArrayListEntry)var69;
               var73.setRemoving(true);
               if (var37 || var37) {
                  break;
               }
            }

            if (!var37) {
               continue;
            }
         }

         E.removeIf(KeybindsWidget::invokePredicate);
         break;
      }

      String var59 = "Key Binds";
      float var114;
      var10000 = (var114 = this.getX() - this.getScreenWidth() / 2.0F) == 0.0F ? 0 : (var114 < 0.0F ? -1 : 1);
      if (!var37) {
         var10000 = var10000 > 0 ? 1 : 0;
      }

      label191: {
         label305: {
            var61 = var10000;
            var92 = animation;
            boolean var98 = E.isEmpty();
            if (!var37) {
               if (!var98) {
                  break label305;
               }

               var98 = Globals.getMc().currentScreen instanceof ChatScreen;
            }

            if (!var37) {
               if (var98) {
                  break label305;
               }

               var98 = Globals.getMc().currentScreen instanceof HudEditorScreen;
            }

            if (!var98) {
               var99 = 0.0F;
               break label191;
            }
         }

         var99 = 1.0F;
      }

      var92.animate(var99);
      this.drawLabel(context, var59, animation.getValue(), (boolean)var61, var22);
      float var62 = 17.0F;
      float var63 = Fonts.getSF_M().getWidth(var31, var59) + 24.0F;
      Iterator var64 = E.iterator();

      label175: {
         ArrayListEntry var66;
         CharSequence var74;
         while (true) {
            if (var64.hasNext()) {
               var66 = (ArrayListEntry)var64.next();
               var93 = Fonts.getSF_M();
               var74 = var66.getExtra();
               var100 = var74.length();
               if (var37) {
                  break;
               }

               if (!var37) {
                  var100 = var100 == 0 ? 1 : 0;
               }

               Object var101;
               if (var100 != 0) {
                  FontRenderer var50 = var93;
                  var101 = StringsKt.trim(var66.getName()).toString();
                  var93 = var50;
               } else {
                  var101 = var74;
               }

               float var70 = var93.getWidth(var31, (String)var101);
               float var75 = Fonts.getSF_M().getWidth(var31, var66.getBind());
               float var94 = var70 + var75 + 13.0F;
               float var102 = var63;
               if (!var37) {
                  if (!(var94 > var63)) {
                     continue;
                  }

                  var94 = var70 + var75;
                  var102 = 13.0F;
               }

               var63 = var94 + var102;
               if (!var37) {
                  continue;
               }

               var64 = E.iterator();
            } else {
               var64 = E.iterator();
            }

            if (!var64.hasNext()) {
               break label175;
            }

            var66 = (ArrayListEntry)var64.next();
            if (var37) {
               return;
            }

            var93 = Fonts.getSF_M();
            var74 = var66.getExtra();
            var100 = var74.length();
            break;
         }

         while (true) {
            if (!var37) {
               var100 = var100 == 0 ? 1 : 0;
            }

            Object var103;
            if (var100 != 0) {
               FontRenderer var80 = var93;
               var103 = StringsKt.trim(var66.getName()).toString();
               var93 = var80;
            } else {
               var103 = var74;
            }

            float var71 = var93.getWidth(var31, (String)var103);
            float var76 = Fonts.getSF_M().getWidth(var31, var66.getBind());
            float var78 = var61 != 0
               ? this.getX() - (1.0F - var66.getAnimation().getValue()) * -10.0F - (var71 + var76 + 13.0F - var63)
               : this.getX() - (1.0F - var66.getAnimation().getValue()) * 10.0F;
            HudElement var95 = this;
            Matrix3x2fStack var104 = context.getMatrices();
            Intrinsics.checkNotNullExpressionValue(var104, "pose(...)");
            HudElement.drawBackground$default(
               var95,
               var104,
               var78,
               (short)var24,
               var25,
               this.getY() + var62,
               var71 + 6.0F,
               13.0F,
               (short)var26,
               var66.getAnimation().getValue(),
               false,
               64,
               null
            );
            HudElement var96 = this;
            Matrix3x2fStack var105 = context.getMatrices();
            Intrinsics.checkNotNullExpressionValue(var105, "pose(...)");
            HudElement.drawBackground$default(
               var96,
               var105,
               var78 + (var71 + 7.0F) * var66.getAnimation().getValue(),
               (short)var24,
               var25,
               this.getY() + var62,
               var76 + 8.0F,
               13.0F,
               (short)var26,
               var66.getAnimation().getValue(),
               false,
               64,
               null
            );
            KeybindsWidget var97 = this;
            DrawContext var106 = context;
            CharSequence var79 = var66.getExtra();
            int var10002 = var79.length();
            if (!var37) {
               var10002 = var10002 == 0 ? 1 : 0;
            }

            Object var107;
            if (var10002 != 0) {
               String var52 = StringsKt.trim(var66.getName()).toString();
               var97 = this;
               var106 = context;
               var107 = var52;
            } else {
               var107 = var79;
            }

            String var108 = (String)var107;
            float var10003 = var78 + 3.0F;
            float var10005 = this.getY() + var62 + 1.0F;
            RenderUtil var10006 = RenderUtil.INSTANCE;
            Color var10007 = Theme.INSTANCE.getTextColor();
            float var54 = var66.getAnimation().getValue();
            var97.drawString(var106, var108, var10003, var29, var10005, var10006.withAlpha(var10007, var54));
            String var109 = var66.getBind();
            var10003 = var78 + (var71 + 11.0F) * var66.getAnimation().getValue();
            var10005 = this.getY() + var62 + 1.0F;
            var10006 = RenderUtil.INSTANCE;
            var10007 = Theme.INSTANCE.getTextColor();
            float var58 = var66.getAnimation().getValue();
            this.drawString(context, var109, var10003, var29, var10005, var10006.withAlpha(var10007, var58));
            var62 += 14.0F * var66.getAnimation().getValue();
            if (var37 || !var64.hasNext()) {
               break;
            }

            var66 = (ArrayListEntry)var64.next();
            if (var37) {
               return;
            }

            var93 = Fonts.getSF_M();
            var74 = var66.getExtra();
            var100 = var74.length();
         }
      }

      this.setSize(var63, var62 - 2.0F);
   }

   private static final boolean render$lambda$0(ArrayListEntry var0) {
      Intrinsics.checkNotNullParameter(var0, "it");
      var0.getAnimation().animate(var0.isRemoving() ? 0.0F : 1.0F);
      return var0.isRemoving() && var0.getAnimation().getValue() <= 0.0F;
   }

   private static final boolean invokePredicate(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }

   static {
      long var27 = a ^ 22721176433672L;
      long var10001 = var27 ^ 110222152611485L;
      int var31 = (int)((var27 ^ 110222152611485L) >>> 48);
      int var32 = (int)((var27 ^ 110222152611485L) << 16 >>> 48);
      int var33 = (int)(var10001 << 32 >>> 32);
      Cipher var18;
      Cipher var10000 = var18 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var27 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var19 = 1; var19 < 8; var19++) {
         var10003[var19] = (byte)(var27 << var19 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var25 = new String[6];
      int var23 = 0;
      String var22 = "B à°òÜh\u00172£IOd¥su yÎ\u0083C\u0097\u0091\u009aq$Ê\u009c\u008a1wd\u0017ÈÍ\f!Jñ\u0092éÄ_ \u0085a4\u008d\u0093\u0018\u0016¹m´5S\u0010ÖÆÈÀ\u0092\u0017Tp5x-\u0090Ut3f\u008f\u0010äªÎÔåPSb\u000e'+Ì\u001a\u008aá6";
      byte var24 = 91;
      char var21 = 16;
      int var36 = -1;

      label64:
      while (true) {
         String var37 = var22.substring(++var36, var36 + var21);
         byte var42 = -1;

         while (true) {
            byte[] var26 = var18.doFinal(var37.getBytes("ISO-8859-1"));
            String var50 = zkmDecodeUtf8(var26).intern();
            switch (var42) {
               case 0:
                  var25[var23++] = var50;
                  if ((var36 += var21) >= var24) {
                     b = var25;
                     c = new String[6];
                     i = new HashMap(13);
                     Cipher var7;
                     var10000 = var7 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var27 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var8 = 1; var8 < 8; var8++) {
                        var10003[var8] = (byte)(var27 << var8 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var13 = new long[5];
                     int var10 = 0;
                     String var11 = "T×\u001cl\ni±ä9\u00188\u0082P\u0092½\u000fê½ñ\u000bÌÁ\u0010\u0016";
                     byte var12 = 24;
                     byte var9 = 0;

                     label46:
                     while (true) {
                        byte var46 = var9;
                        var9 += 8;
                        byte[] var14 = var11.substring(var46, var9).getBytes("ISO-8859-1");
                        long[] var40 = var13;
                        int var47 = var10++;
                        long var54 = (var14[0] & 255L) << 56
                           | (var14[1] & 255L) << 48
                           | (var14[2] & 255L) << 40
                           | (var14[3] & 255L) << 32
                           | (var14[4] & 255L) << 24
                           | (var14[5] & 255L) << 16
                           | (var14[6] & 255L) << 8
                           | var14[7] & 255L;
                        byte var58 = -1;

                        while (true) {
                           long var15 = var54;
                           byte[] var17 = var7.doFinal(
                              new byte[]{
                                 (byte)(var15 >>> 56),
                                 (byte)(var15 >>> 48),
                                 (byte)(var15 >>> 40),
                                 (byte)(var15 >>> 32),
                                 (byte)(var15 >>> 24),
                                 (byte)(var15 >>> 16),
                                 (byte)(var15 >>> 8),
                                 (byte)var15
                              }
                           );
                           long var61 = (var17[0] & 255L) << 56
                              | (var17[1] & 255L) << 48
                              | (var17[2] & 255L) << 40
                              | (var17[3] & 255L) << 32
                              | (var17[4] & 255L) << 24
                              | (var17[5] & 255L) << 16
                              | (var17[6] & 255L) << 8
                              | var17[7] & 255L;
                           switch (var58) {
                              case 0:
                                 var40[var47] = var61;
                                 if (var9 >= var12) {
                                    g = var13;
                                    h = new Integer[5];
                                    Cipher var2;
                                    var10000 = var2 = Cipher.getInstance("DES/CBC/NoPadding");
                                    var10002 = SecretKeyFactory.getInstance("DES");
                                    var10003 = new byte[]{(byte)(var27 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                                    for (int var3 = 1; var3 < 8; var3++) {
                                       var10003[var3] = (byte)(var27 << var3 * 8 >>> 56);
                                    }

                                    var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                                    byte[] var6 = var2.doFinal(new byte[]{78, 60, -95, 52, -51, -90, 94, 90});
                                    long var0 = (var6[0] & 255L) << 56
                                       | (var6[1] & 255L) << 48
                                       | (var6[2] & 255L) << 40
                                       | (var6[3] & 255L) << 32
                                       | (var6[4] & 255L) << 24
                                       | (var6[5] & 255L) << 16
                                       | (var6[6] & 255L) << 8
                                       | var6[7] & 255L;
                                    J = new KeybindsWidget((short)var31, (short)var32, var33);
                                    E = new ArrayList();
                                    animation = new Animation(Easing.OUT_BACK, var0);
                                    return;
                                 }
                                 break;
                              default:
                                 var40[var47] = var61;
                                 if (var9 < var12) {
                                    continue label46;
                                 }

                                 var11 = "-\u0082³7\nµ¯¦w\u0082Y\u009aÓ\u001f\tW";
                                 var12 = 16;
                                 var9 = 0;
                           }

                           byte var48 = var9;
                           var9 += 8;
                           var14 = var11.substring(var48, var9).getBytes("ISO-8859-1");
                           var40 = var13;
                           var47 = var10++;
                           var54 = (var14[0] & 255L) << 56
                              | (var14[1] & 255L) << 48
                              | (var14[2] & 255L) << 40
                              | (var14[3] & 255L) << 32
                              | (var14[4] & 255L) << 24
                              | (var14[5] & 255L) << 16
                              | (var14[6] & 255L) << 8
                              | var14[7] & 255L;
                           var58 = 0;
                        }
                     }
                  }

                  var21 = var22.charAt(var36);
                  break;
               default:
                  var25[var23++] = var50;
                  if ((var36 += var21) < var24) {
                     var21 = var22.charAt(var36);
                     continue label64;
                  }

                  var22 = "\u0089\u0017ó\u0094ç¡\u008aUZW¶æ`ÏæH÷\u0097þ}¿¾r\u000f\u007f²Ö·¦ø!\u007f \u0096\u001e¸1ùù\u0095p\u009dü\u0010\u0014W\u0019åñ\u008bÂ\u0088è\u009b³]á\u0084î{,Ò\u0088\u0095\u0083";
                  var24 = 65;
                  var21 = ' ';
                  var36 = -1;
            }

            var37 = var22.substring(++var36, var36 + var21);
            var42 = 0;
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 20342;
      if (c[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().threadId();
            var4 = (Object[])f.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               f.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("su/catlean/pj", var10);
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
         throw new RuntimeException("su/catlean/pj" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 18023;
      if (h[var3] == null) {
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
         long var5 = g[var3];
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
         Object[] var9 = (Object[])i.get(var8);

         byte[] var10;
         try {
            if (var9 == null) {
               var9 = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               i.put(var8, var9);
            }

            DESKeySpec var11 = new DESKeySpec(var4);
            SecretKey var12 = ((SecretKeyFactory)var9[1]).generateSecret(var11);
            Cipher var13 = (Cipher)var9[0];
            var13.init(2, var12, (IvParameterSpec)var9[2]);
            var10 = var13.doFinal(var7);
         } catch (Exception var14) {
            throw new RuntimeException("su/catlean/pj", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         h[var3] = var15;
      }

      return h[var3];
   }

   private static int d(Lookup var0, MutableCallSite var1, String var2, Object[] var3) {
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
               MethodHandles.insertArguments("d".asCollector(Object[].class, var2.parameterCount()), 0, var0, var3, var1), var2
            )
         );
         return var3;
      } catch (Exception var5) {
         throw new RuntimeException("su/catlean/pj" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
