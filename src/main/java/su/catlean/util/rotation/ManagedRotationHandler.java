package su.catlean.util.rotation;

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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import org.jetbrains.annotations.NotNull;
import su.catlean.mixins.accessors.KeyMappingAccessor;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AuraSprintMode;
import su.catlean.module.basic.Sprint;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.Scheduler;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ManagedRotationHandler implements RotationHandler {
   @NotNull
   public static final ManagedRotationHandler INSTANCE;
   private static boolean e;
   private static boolean k;
   private static final long a = ZkmKeys.a(-1107915982818841974L, -1462049736569999676L, MethodHandles.lookup().lookupClass()).a(108566854776481L);
   private static final String[] b;
   private static final String[] c;
   private static final Map d = new HashMap(13);
   private static final long f;

   private ManagedRotationHandler() {
   }

   public final boolean w() {
      return k;
   }

   public final void f(boolean <set-?>) {
      k = <set-?>;
   }

   @Override
   public void preAttack(int var1, long var2) {
      int var14;
      boolean var10000;
      label71: {
         label74: {
            long var4 = (long)var1 << 32 | var2 << 32 >>> 32;
            var14 = ZkmReferences.zkmBootstrap<"Å">(8258463305824275557L, var4);
            var10000 = MovementUtil.INSTANCE.J();
            if (var14 == 0) {
               if (!var10000) {
                  break label74;
               }

               var10000 = Globals.getPlayer().isSwimming();
            }

            if (var14 != 0) {
               break label71;
            }

            if (!var10000) {
               var10000 = true;
               break label71;
            }
         }

         var10000 = false;
      }

      label76: {
         boolean var15 = var10000;
         var10000 = var15;
         if (var14 == 0) {
            if (!var15) {
               return;
            }

            var17 = Aura.INSTANCE;
            if (var1 < 0 || var14 != 0) {
               break label76;
            }

            var10000 = Aura.INSTANCE.getOnHitSwap();
         }

         if (var10000) {
            return;
         }

         var17 = Aura.INSTANCE;
      }

      label46: {
         AuraSprintMode var18 = var17.getSprintMode();
         AuraSprintMode var10001 = AuraSprintMode.NORMAL;
         if (var14 == 0) {
            if (var18 == AuraSprintMode.NORMAL) {
               break label46;
            }

            var18 = Aura.INSTANCE.getSprintMode();
            var10001 = AuraSprintMode.HVH;
         }

         if (var18 != var10001) {
            return;
         }
      }

      var10000 = Globals.getPlayer().isOnGround();
      if (var14 == 0) {
         if (var10000) {
            return;
         }

         var10000 = true;
      }

      e = var10000;
      Globals.getPlayer().setSprinting(false);
      Globals.getNetworkHandler().sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), Mode.STOP_SPRINTING));
   }

   @Override
   public void postRotate(@NotNull Rotation rotation, short a, int a, int a) {
      long var5 = (long)axxx << 48 | (long)ax << 32 >>> 16 | (long)axx << 48 >>> 48;
      long var12 = var5 ^ 51433702095905L;
      int var10000 = ZkmReferences.zkmBootstrap<"Å">(-2859712109970658601L, var5);
      Intrinsics.checkNotNullParameter(rotation, zkmBootstrap<"a">(23435, 4173186407438872847L ^ var5));
      int var20 = var10000;
      boolean var22 = e;
      if (var20 != 0) {
         if (e) {
            Globals.getPlayer().setSprinting(true);
            Globals.getNetworkHandler().sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), Mode.START_SPRINTING));
            e = false;
         }

         var22 = false;
      }

      label58: {
         AuraSprintMode var10001;
         k = var22;
         var23 = Aura.INSTANCE.getSprintMode();
         var10001 = AuraSprintMode.LEGIT_EXTRA;
         label53:
         if (var20 != 0) {
            if (var23 != AuraSprintMode.LEGIT_EXTRA) {
               var23 = Aura.INSTANCE.getSprintMode();
               var10001 = AuraSprintMode.LEGIT;
               if (ax <= 0 || var20 == 0 || axx < 0) {
                  break label53;
               }

               if (var23 != AuraSprintMode.LEGIT && (axx < 0 || !this.Z())) {
                  break label58;
               }
            }

            var23 = Aura.INSTANCE.getSprintMode();
            var10001 = AuraSprintMode.LEGIT_EXTRA;
         }

         if (var23 == var10001) {
            MovementUtil.INSTANCE.releaseMovementKeys(var12);
         }

         ClientPlayerEntity var24 = Globals.getPlayer();
         Window var25 = Globals.getMc().getWindow();
         KeyBinding var10002 = Globals.getMc().options.forwardKey;
         Intrinsics.checkNotNull(var10002, zkmBootstrap<"a">(11590, 6711875497808299969L ^ var5));
         var24.setSprinting(InputUtil.isKeyPressed(var25, ((KeyMappingAccessor)var10002).getKey().getCode()));
      }

      if (axxx >= 0 && ZkmReferences.zkmBootstrap<"Å">(-2826320950094477737L, var5) != null) {
         ZkmReferences.zkmBootstrap<"Å">(++var20, -2833482568319413200L, var5);
      }
   }

   @Override
   public void reset() {
   }

   public final void i(boolean hvh) {
      ClientPlayerEntity var10;
      label26: {
         long var7 = 0L;
         var7 = a ^ var7;
         int var4 = ZkmReferences.zkmBootstrap<"Å">(7497032961441142516L, var7);
         boolean var10000 = hvh;
         if (var4 == 0) {
            if (!hvh) {
               return;
            }

            var10 = Globals.getPlayer();
            if (var4 != 0) {
               break label26;
            }

            var10000 = var10.isSwimming();
         }

         if (var10000) {
            return;
         }

         var10 = Globals.getPlayer();
      }

      ClientPlayerEntity var5 = var10;
      var5.setVelocity(var5.getVelocity().x / 0.6, var5.getVelocity().y, var5.getVelocity().z / 0.6);
      var5.setSprinting(true);
   }

   public final boolean Z() {
      long var8 = 0L;
      byte var10 = 0;
      long var1 = (var8 << 32 | (long)var10 << 32 >>> 32) ^ a;
      int var7 = ZkmReferences.zkmBootstrap<"Å">(-1655875206967993470L, var1);
      int var10000 = Aura.INSTANCE.getOnHitSwap();
      if (var7 != 0) {
         if (var10000 == 0) {
            return (boolean)0;
         }

         var10000 = AuraRotationHandler.INSTANCE.findBestWeapon();
      }

      if (var7 == 0) {
         return (boolean)var10000;
      } else {
         return (boolean)(var10000 != -1 ? 1 : 0);
      }
   }

   public final boolean J(boolean can, int a, short a, int a) {
      long var5 = ((long)axxx << 32 | (long)ax << 48 >>> 32 | (long)axx << 48 >>> 48) ^ a;
      long var14 = var5 ^ 126468313953150L;
      boolean var25 = can;
      boolean var26 = Aura.INSTANCE.getSprintMode() == AuraSprintMode.LEGIT_EXTRA;
      if (DefaultRotationHandler.INSTANCE.b(var14, 1)
         && Globals.getPlayer().getVelocity().y < -0.07
         && (Globals.getPlayer().isSprinting() || this.Z() && Globals.getMc().options.jumpKey.isPressed())
         && (Aura.INSTANCE.getSprintMode() == AuraSprintMode.LEGIT || Aura.INSTANCE.getSprintMode() == AuraSprintMode.LEGIT_EXTRA || this.Z())) {
         if (var26) {
            MovementUtil.INSTANCE.releaseMovementKeys();
         }

         Globals.getPlayer().setSprinting(false);
         Sprint.INSTANCE.h(true);
         var25 = false;
         Globals.getMc().options.sprintKey.setPressed(false);
         k = true;
         AuraRotationHandler.INSTANCE.swapOnHit();
         Scheduler.schedule$default(Scheduler.INSTANCE, 3, (byte)0, false, ManagedRotationHandler::O, (int)f, null);
      }

      return var25;
   }

   private static final void O(boolean var0) {
      k = false;
      KeyBinding var10000 = Globals.getMc().options.sprintKey;
      Window var10001 = Globals.getMc().getWindow();
      KeyBinding var10002 = Globals.getMc().options.sprintKey;
      Intrinsics.checkNotNull(var10002, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
      var10000.setPressed(InputUtil.isKeyPressed(var10001, ((KeyMappingAccessor)var10002).getKey().getCode()));
   }

   static {
      long var5 = a ^ 15185953413877L;
      Cipher var7;
      Cipher var10000 = var7 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var5 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var8 = 1; var8 < 8; var8++) {
         var10003[var8] = (byte)(var5 << var8 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var14 = new String[3];
      int var12 = 0;
      String var11 = "½Æ\u008b\u0012ÌÈß&\u0095[j«\u008ak2\u000fl\r\u0089ôQÁ×*ågg\u0001Ö²&×é¿yqüâ\u000e\u008d,73_Ëy¶¼U\u0018¾Á,\u008d\u0098ªdô\ruXÖ \u001f0Ñ\n»*FÚÇxTD\u0015×KÁÒ\u009f\u0084Ãw\u009e\u0083\u009fò5Q¢½\u00163\rvs\u0006/\u0086ìÔÆ\u008b&cqyN\u007fþAÏýJ\n¡º\u008eËÌ\u009aù\u0004µíÇ~z\u0005S$A\u001a¡É\u0018\u0015\fÇå±«,)ÿÃ;!\u0010\u00919ô}'ìþYïUÈ\u0088\u0005ÜÏOd\u001bx\u0092\u001cÙ>õÇ,g¬\u0086Izÿ3\u0083UÂCäßó&\u0081\u009e\u0080\u001eW<¬M\u0086Áµð_\u0018°\u0011)ß\"\u0083¹§\u0019\u0016\u0010*ù\u0084n=\u0082C¼ÔtK\u0089gõÞ½²\u0087°\u008b`\u0001\u009f»j\u0090]õÔ\u0013\u0092öÈÄ\u0004\u009d\u0098\rò\u0003\u0015\u009dm?ïÞ[~UÁ9\u0082\u0011X0çòÇ³¡=-Â\u0011£uõeû¤ÚAòÑ¡u\nÁ÷\u009ew7";
      short var13 = 298;
      char var10 = 136;
      int var9 = -1;

      while (true) {
         byte[] var15 = var7.doFinal(var11.substring(++var9, var9 + var10).getBytes("ISO-8859-1"));
         var14[var12++] = zkmDecodeUtf8(var15).intern();
         if ((var9 += var10) >= var13) {
            b = var14;
            c = new String[3];
            Cipher var0;
            var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
            var10002 = SecretKeyFactory.getInstance("DES");
            var10003 = new byte[]{(byte)(var5 >>> 56), 0, 0, 0, 0, 0, 0, 0};

            for (int var1 = 1; var1 < 8; var1++) {
               var10003[var1] = (byte)(var5 << var1 * 8 >>> 56);
            }

            var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
            byte[] var4 = var0.doFinal(new byte[]{11, 70, -108, -98, 28, -94, -42, 33});
            f = (var4[0] & 255L) << 56
               | (var4[1] & 255L) << 48
               | (var4[2] & 255L) << 40
               | (var4[3] & 255L) << 32
               | (var4[4] & 255L) << 24
               | (var4[5] & 255L) << 16
               | (var4[6] & 255L) << 8
               | var4[7] & 255L;
            INSTANCE = new ManagedRotationHandler();
            return;
         }

         var10 = var11.charAt(var9);
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 29458;
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
            throw new RuntimeException("su/catlean/ja", var10);
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
         throw new RuntimeException("su/catlean/ja" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
