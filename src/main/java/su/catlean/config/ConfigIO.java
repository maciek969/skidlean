package su.catlean.config;

import java.io.File;
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
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.BindCommand;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.KeyNames;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ConfigIO {
   private static final long a = ZkmKeys.a(3446334409530900906L, 7232028930879722839L, MethodHandles.lookup().lookupClass()).a(152291902004432L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);

   // $VF: Irreducible bytecode was duplicated to produce valid code
   public static final void load(@NotNull String id, @NotNull ConfigType type, int a, short a, int a) {
      long var5;
      Module[] var42;
      label130: {
         var5 = ((long)axxx << 32 | (long)ax << 48 >>> 32 | (long)axx << 48 >>> 48) ^ a;
         long var10001 = var5 ^ 72840544043441L;
         int var15 = (int)((var5 ^ 72840544043441L) >>> 48);
         int var16 = (int)((var5 ^ 72840544043441L) << 16 >>> 32);
         int var17 = (int)(var10001 << 48 >>> 48);
         Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(800282861839994323L, var5);
         Intrinsics.checkNotNullParameter(id, zkmBootstrap<"g">(17422, 6330383588215856441L ^ var5));
         var42 = var10000;
         Intrinsics.checkNotNullParameter(type, zkmBootstrap<"g">(31509, 6678947653835268646L ^ var5));
         switch (ConfigIO$WhenMappings.N[type.ordinal()]) {
            case 1:
               ModuleManager var46 = ModuleManager.INSTANCE;
               ConfigStore.INSTANCE.getProfileConfigFile().findById(var46.L(), ConfigStore.INSTANCE.getConfigManager().getData().getModulesConfig());
               ProfileConfig var48 = ConfigStore.INSTANCE.getProfileConfigFile().getCurrent();
               ConfigStore.INSTANCE.getConfigManager().getData().setModulesConfig(id);
               Iterable var49 = ConfigStore.INSTANCE.getProfileConfigFile().getData();
               ProfileConfigFile var50 = ConfigStore.INSTANCE.getProfileConfigFile();
               Iterator var52 = var49.iterator();

               label125:
               while (true) {
                  if (!var52.hasNext()) {
                     var10000 = null;
                     break;
                  }

                  var10000 = (Module[])var52.next();

                  while (true) {
                     Object var53 = var10000;
                     var10000 = (Module[])var53;

                     while (true) {
                        ProfileConfig var54 = (ProfileConfig)var10000;
                        if (!Intrinsics.areEqual(var54.getId(), id)) {
                           continue label125;
                        }

                        var10000 = (Module[])var53;
                        if (axxx < 0) {
                           break;
                        }

                        if (var42 != null) {
                           break label125;
                        }
                     }
                  }
               }

               ProfileConfig var72 = (ProfileConfig)var10000;
               if ((ProfileConfig)var10000 == null) {
                  return;
               }

               var50.setCurrent(var72);
               var46.E();
               var46.n((char)var15, var16, (char)var17, var48);
               if (axx <= 0) {
                  break label130;
               }

               if (var42 != null) {
                  break;
               }
            case 2:
               HudManager var59 = HudManager.INSTANCE;
               ConfigStore.INSTANCE.getHudConfigFile().findById(var59.getElements(), ConfigStore.INSTANCE.getConfigManager().getData().getWidgetsConfig());
               ConfigStore.INSTANCE.getConfigManager().getData().setWidgetsConfig(id);
               Iterable var60 = ConfigStore.INSTANCE.getHudConfigFile().K();
               HudConfigFile var61 = ConfigStore.INSTANCE.getHudConfigFile();
               Iterator var51 = var60.iterator();

               label105:
               while (true) {
                  if (!var51.hasNext()) {
                     var10000 = null;
                     break;
                  }

                  var10000 = (Module[])var51.next();

                  while (true) {
                     Object var65 = var10000;
                     var10000 = (Module[])var65;

                     while (true) {
                        HudConfig var66 = (HudConfig)var10000;
                        if (!Intrinsics.areEqual(var66.getId(), id)) {
                           continue label105;
                        }

                        var10000 = (Module[])var65;
                        if (ax >= 0) {
                           break;
                        }

                        if (var42 != null) {
                           break label105;
                        }
                     }
                  }
               }

               HudConfig var73 = (HudConfig)var10000;
               if (ax < 0 && (HudConfig)var10000 == null) {
                  return;
               }

               var61.setCurrent(var73);
               var59.updateAll();
               if (axx <= 0) {
                  break label130;
               }

               if (var42 != null) {
                  break;
               }

               throw new NoWhenBranchMatchedException();
            default:
               throw new NoWhenBranchMatchedException();
         }

         BindCommand.INSTANCE.sendMessage(Formatting.WHITE + zkmBootstrap<"g">(14271, 1321651483649666701L ^ var5));
      }

      Iterable var44 = ModuleManager.INSTANCE.L();
      var var47 = new ArrayList();
      Iterator var62 = var44.iterator();

      Object var69;
      label80:
      while (true) {
         if (var62.hasNext()) {
            var69 = var62;
            if (axx > 0) {
               if (var42 == null) {
                  break;
               }

               var69 = var62.next();
            }
         } else {
            var69 = var47;
            if (axxx >= 0) {
               var69 = var69.iterator();
               break;
            }
         }

         do {
            Object var63 = var69;
            Module var64 = (Module)var63;
            int var70 = var64.getBind().getKey();
            if (var42 != null) {
               var70 = var70 != -1 ? 1 : 0;
            }

            if (var70 == 0) {
               continue label80;
            }

            var47.add(var63);
            if (var42 != null) {
               continue label80;
            }

            var69 = var47;
         } while (axxx < 0);

         var69 = var47.iterator();
         break;
      }

      Object var43 = var69;

      while (var43.hasNext()) {
         Module var58 = (Module)var43.next();
         BindCommand var71 = BindCommand.INSTANCE;
         String var74 = var58.getDisplayName();
         Formatting var10002 = Formatting.WHITE;
         String var56 = KeyNames.INSTANCE.getKeyName(var58.getBind().getKey());
         Formatting var57 = var10002;
         var71.sendMessage(var74 + zkmBootstrap<"g">(1982, 3432875230008401544L ^ var5) + var57 + var56);
         if (var42 == null) {
            break;
         }
      }
   }

   public static final void delete(int a, byte a, @NotNull String id, @NotNull ConfigType type, int a) {
      long var5 = ((long)ax << 32 | (long)axxx << 56 >>> 32 | (long)axx << 40 >>> 40) ^ a;
      long var10001 = var5 ^ 33672318406991L;
      int var9 = (int)((var5 ^ 33672318406991L) >>> 48);
      int var10 = (int)((var5 ^ 33672318406991L) << 16 >>> 32);
      int var11 = (int)(var10001 << 48 >>> 48);
      var10001 = var5 ^ 75361554480159L;
      int var19 = (int)((var5 ^ 75361554480159L) >>> 32);
      int var20 = (int)((var5 ^ 75361554480159L) << 32 >>> 48);
      int var21 = (int)(var10001 << 48 >>> 48);
      Intrinsics.checkNotNullParameter(id, "id");
      Module[] var10000 = new Module[0];
      Intrinsics.checkNotNullParameter(type, "type");
      switch (ConfigIO$WhenMappings.N[type.ordinal()]) {
         case 1:
            if (Intrinsics.areEqual(id, ConfigStore.INSTANCE.getProfileConfigFile().getCurrent().getId())) {
               load(CollectionsKt.<ProfileConfig>first(ConfigStore.INSTANCE.getProfileConfigFile().getData()).getId(), type, var19, (short)var20, var21);
            } else if (ConfigStore.INSTANCE.getProfileConfigFile().getData().size() == 1) {
               save("default", type, (char)var9, var10, (short)var11);
               load("default", type, var19, (short)var20, var21);
            }

            ConfigStore.INSTANCE.getProfileConfigFile().getData().removeIf(ConfigIO::invokePredicate);
            new File(ConfigStore.INSTANCE.getProfileConfigFile().U(), id + ".ctlon").delete();
            break;
         case 2:
            if (Intrinsics.areEqual(id, ConfigStore.INSTANCE.getHudConfigFile().getCurrent().getId())) {
               load(CollectionsKt.<HudConfig>first(ConfigStore.INSTANCE.getHudConfigFile().K()).getId(), type, var19, (short)var20, var21);
            } else if (ConfigStore.INSTANCE.getHudConfigFile().K().size() == 1) {
               save("default", type, (char)var9, var10, (short)var11);
               load("default", type, var19, (short)var20, var21);
            }

            ConfigStore.INSTANCE.getHudConfigFile().K().removeIf(ConfigIO::invokePredicate2);
            new File(ConfigStore.INSTANCE.getHudConfigFile().U(), id + ".ctlon").delete();
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public static final void save(@NotNull String id, @NotNull ConfigType type, char a, int a, short a) {
      long var5 = ((long)axxx << 48 | (long)ax << 32 >>> 16 | (long)axx << 48 >>> 48) ^ a;
      int var9 = (int)((var5 ^ 21955982885208L) >>> 32);
      int var10 = (int)((var5 ^ 21955982885208L) << 32 >>> 40);
      int var12 = (int)((var5 ^ 21955982885208L) >>> 32);
      int var13 = (int)((var5 ^ 21955982885208L) << 32 >>> 40);
      Module[] var10000 = ZkmReferences.zkmBootstrap<"Å">(-1852255909587017597L, var5);
      Intrinsics.checkNotNullParameter(id, zkmBootstrap<"g">(17422, 6330460465850577001L ^ var5));
      Intrinsics.checkNotNullParameter(type, zkmBootstrap<"g">(31509, 6678901557967933302L ^ var5));
      Module[] var17 = var10000;
      ConfigStore var20;
      switch (ConfigIO$WhenMappings.N[type.ordinal()]) {
         case 1:
            var20 = ConfigStore.INSTANCE;
            if (ax <= 0) {
               break;
            }

            ProfileConfigFile var18 = ConfigStore.INSTANCE.getProfileConfigFile();
            var18.findById(ModuleManager.INSTANCE.L(), id);
            var18.load(var9, var10);
            if (var17 != null) {
               return;
            }
         case 2:
            var20 = ConfigStore.INSTANCE;
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }

      HudConfigFile var19 = var20.getHudConfigFile();
      var19.findById(HudManager.INSTANCE.getElements(), id);
      var19.load(var12, var13);
      if (var17 == null) {
         throw new NoWhenBranchMatchedException();
      }
   }

   private static final boolean delete$lambda$0(String var0, ProfileConfig var1) {
      Intrinsics.checkNotNullParameter(var1, "it");
      return Intrinsics.areEqual(var1.getId(), var0);
   }

   private static final boolean invokePredicate(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }

   private static final boolean delete$lambda$2(String var0, HudConfig var1) {
      Intrinsics.checkNotNullParameter(var1, "it");
      return Intrinsics.areEqual(var1.getId(), var0);
   }

   private static final boolean invokePredicate2(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }

   static {
      long var0 = a ^ 95362099258879L;
      Cipher var2;
      Cipher var10000 = var2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var0 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var3 = 1; var3 < 8; var3++) {
         var10003[var3] = (byte)(var0 << var3 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var9 = new String[12];
      int var7 = 0;
      String var6 = "CÊÕ\u0003\u0085ì\u001arÄ\u0093Ýy\u007f8\u0000T\u0010\u0088H\u008dJ(\u001b\u0095Ùð\u0081«£\u0090÷¯\u000f Ô\f\u009e³\u00853¯\u000eU\u0018\r\u0000I\u001eTÃhÛ.?\u0097qÕÿ\u0088clÜïú\u0016!\u0010¨\u0017¶äUcÏ/G;º\u00818ñ`9\u0010L$zùú²\u001e\u0004-~,\u008a'¿´J\u00103ÐwW5\n!¤/ÑU\u008d2I0\u009b\u0010\u008cP~\u009aâÊÃ£I\u001a\u0001!ªBd\u0097\u0010\u0015HöÞDP$2Å[\u008a\u0001S\u001fÀM\u0010|kä£g\u000e\u0013R\u0005\u0086ÇÇ\u000eTçÇ\u0010`ýìl0ó\u008eï]wSZ\"´\u008cz";
      short var8 = 185;
      char var5 = 16;
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
                     c = new String[12];
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

                  var6 = "4l_SéÙ\u008cpAô\u000e\\\u0087ó]k\u0003wh\u0007E¹®Ë\u0010\u009b\u0012fy!¬Û{&Ï\u001b\u00887\\,õ";
                  var8 = 41;
                  var5 = 24;
                  var12 = -1;
            }

            var13 = var6.substring(++var12, var12 + var5);
            var10001 = 0;
         }
      }
   }

   private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException var0) {
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 21340;
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
            throw new RuntimeException("su/catlean/io", var10);
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
         throw new RuntimeException("su/catlean/io" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
