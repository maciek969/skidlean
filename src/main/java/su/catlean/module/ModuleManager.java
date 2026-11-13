package su.catlean.module;

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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.config.ConfigStore;
import su.catlean.config.ProfileConfig;
import su.catlean.config.SettingEntry;
import su.catlean.config.SettingsConfig;
import su.catlean.gofra.Flow;
import su.catlean.gofra.Gofra;
import su.catlean.gui.CategoryGroup;
import su.catlean.gui.CategoryGroups;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.module.attack.AntiPhase;
import su.catlean.module.attack.AttributeSwap;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AutoBasePlace;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.attack.AutoDripstope;
import su.catlean.module.attack.AutoMine;
import su.catlean.module.attack.AutoNetheriteScrap;
import su.catlean.module.attack.AutoSweetBerries;
import su.catlean.module.attack.AutoWeb;
import su.catlean.module.attack.Criticals;
import su.catlean.module.attack.HoleFiller;
import su.catlean.module.attack.MaceKiller;
import su.catlean.module.attack.MoreKnockback;
import su.catlean.module.attack.Trap;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.module.basic.Avoid;
import su.catlean.module.basic.ElytraRecast;
import su.catlean.module.basic.GuiMove;
import su.catlean.module.basic.HoleAnchor;
import su.catlean.module.basic.InfiniteElytra;
import su.catlean.module.basic.LegitStrafe;
import su.catlean.module.basic.MoveFix;
import su.catlean.module.basic.NoPush;
import su.catlean.module.basic.Sprint;
import su.catlean.module.basic.WindHop;
import su.catlean.module.cosmetic.Absorption;
import su.catlean.module.cosmetic.Aspect;
import su.catlean.module.cosmetic.BuffEffect;
import su.catlean.module.cosmetic.CrystalChams;
import su.catlean.module.cosmetic.DamageParticles;
import su.catlean.module.cosmetic.DamageTint;
import su.catlean.module.cosmetic.Dismemberment;
import su.catlean.module.cosmetic.FreeLook;
import su.catlean.module.cosmetic.FullBright;
import su.catlean.module.cosmetic.HandChams;
import su.catlean.module.cosmetic.HitFx;
import su.catlean.module.cosmetic.NoCameraClip;
import su.catlean.module.cosmetic.Particles;
import su.catlean.module.cosmetic.PopChams;
import su.catlean.module.cosmetic.SkyLanterns;
import su.catlean.module.cosmetic.SwingAnimations;
import su.catlean.module.cosmetic.ThrowPetard;
import su.catlean.module.cosmetic.TotemAnimation;
import su.catlean.module.cosmetic.Trajectories;
import su.catlean.module.cosmetic.ViewModel;
import su.catlean.module.cosmetic.WorldRenderer;
import su.catlean.module.equip.AutoArmor;
import su.catlean.module.equip.AutoTool;
import su.catlean.module.equip.ChestStealer;
import su.catlean.module.equip.EcExploit;
import su.catlean.module.equip.ElytraReplace;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.module.equip.InventoryManager;
import su.catlean.module.equip.ItemScroller;
import su.catlean.module.equip.MiddleClick;
import su.catlean.module.equip.NoServerSlot;
import su.catlean.module.equip.ReFiller;
import su.catlean.module.equip.ToolSaver;
import su.catlean.module.equip.XCarry;
import su.catlean.module.esp.BreakEsp;
import su.catlean.module.esp.HoleEsp;
import su.catlean.module.esp.ItemEsp;
import su.catlean.module.esp.LogoutSpots;
import su.catlean.module.esp.NameTags;
import su.catlean.module.esp.PearlPhaseEsp;
import su.catlean.module.esp.PlayerEsp;
import su.catlean.module.esp.ProjectilePredict;
import su.catlean.module.esp.Search;
import su.catlean.module.esp.StorageEsp;
import su.catlean.module.esp.Tracers;
import su.catlean.module.esp.VoidEsp;
import su.catlean.module.esp.XRay;
import su.catlean.module.legit.AnchorBind;
import su.catlean.module.legit.AutoBuff;
import su.catlean.module.legit.BowMinecart;
import su.catlean.module.legit.BowSpam;
import su.catlean.module.legit.CrystalBind;
import su.catlean.module.legit.LegitTotem;
import su.catlean.module.legit.Reach;
import su.catlean.module.misc.AntiResourcePack;
import su.catlean.module.misc.Assistant;
import su.catlean.module.misc.AutoEat;
import su.catlean.module.misc.AutoEz;
import su.catlean.module.misc.AutoLeave;
import su.catlean.module.misc.AutoPotionBrew;
import su.catlean.module.misc.AutoSoup;
import su.catlean.module.misc.AutoTrader;
import su.catlean.module.misc.ExtraTab;
import su.catlean.module.misc.FastLatency;
import su.catlean.module.misc.MessageAppend;
import su.catlean.module.misc.NameProtect;
import su.catlean.module.misc.Notifier;
import su.catlean.module.misc.PingSpoof;
import su.catlean.module.misc.RPC;
import su.catlean.module.misc.SourceFiller;
import su.catlean.module.misc.Spammer;
import su.catlean.module.misc.TapeMouse;
import su.catlean.module.misc.TeamCommunication;
import su.catlean.module.misc.Test;
import su.catlean.module.misc.Translator;
import su.catlean.module.misc.UI;
import su.catlean.module.player.ActionRecorder;
import su.catlean.module.player.AirPlace;
import su.catlean.module.player.AntiAim;
import su.catlean.module.player.AntiAttack;
import su.catlean.module.player.AntiHunger;
import su.catlean.module.player.AutoAuth;
import su.catlean.module.player.AutoFish;
import su.catlean.module.player.AutoMend;
import su.catlean.module.player.AutoRespawn;
import su.catlean.module.player.AutoTpaAccept;
import su.catlean.module.player.BoxShrinker;
import su.catlean.module.player.ChorusDelay;
import su.catlean.module.player.MultiTask;
import su.catlean.module.player.NoDelay;
import su.catlean.module.player.NoInteract;
import su.catlean.module.player.NoServerRotate;
import su.catlean.module.player.PerfectDelay;
import su.catlean.module.player.PortalGodMode;
import su.catlean.module.player.SpeedMine;
import su.catlean.module.player.TraceThrough;
import su.catlean.module.protect.AutoGapple;
import su.catlean.module.protect.Blocker;
import su.catlean.module.protect.Burrow;
import su.catlean.module.protect.MainhandTotem;
import su.catlean.module.protect.Offhand;
import su.catlean.module.protect.Quiver;
import su.catlean.module.protect.SelfTrap;
import su.catlean.module.protect.Surround;
import su.catlean.module.rage.AntiVoid;
import su.catlean.module.rage.AntiWeb;
import su.catlean.module.rage.Blink;
import su.catlean.module.rage.BoatFly;
import su.catlean.module.rage.ElytraFreeze;
import su.catlean.module.rage.ElytraPlus;
import su.catlean.module.rage.ElytraTarget;
import su.catlean.module.rage.EntitySpeed;
import su.catlean.module.rage.Flight;
import su.catlean.module.rage.HighJump;
import su.catlean.module.rage.LevitationControl;
import su.catlean.module.rage.MaceElytraTarget;
import su.catlean.module.rage.NoFall;
import su.catlean.module.rage.NoSlow;
import su.catlean.module.rage.Phase;
import su.catlean.module.rage.ReverseStep;
import su.catlean.module.rage.Scaffold;
import su.catlean.module.rage.Speed;
import su.catlean.module.rage.Spider;
import su.catlean.module.rage.Step;
import su.catlean.module.rage.Timer;
import su.catlean.module.rage.TridentBoost;
import su.catlean.module.rage.WaterSpeed;
import su.catlean.module.world.AutoBoneMeal;
import su.catlean.module.world.AutoLand;
import su.catlean.module.world.AutoShear;
import su.catlean.module.world.AutoSign;
import su.catlean.module.world.AutoWither;
import su.catlean.module.world.FakePlayer;
import su.catlean.module.world.FreeCam;
import su.catlean.module.world.NoRender;
import su.catlean.module.world.Nuker;
import su.catlean.module.world.PearlChaser;
import su.catlean.module.world.ServerHelper;
import su.catlean.module.world.SoilRipper;
import su.catlean.module.world.TpsSync;
import su.catlean.module.world.Zoom;
import su.catlean.setting.Setting;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ModuleManager implements Manager {
   @NotNull
   public static final ModuleManager INSTANCE;
   @NotNull
   private static final ArrayList k;
   @NotNull
   private static ArrayList e;
   @NotNull
   private static final ArrayList K;
   private static int L;
   private static final long b = ZkmKeys.a(-1282272677367790005L, -1581970564411779349L, MethodHandles.lookup().lookupClass()).a(125164507286546L);
   private static final String[] c;
   private static final String[] d;
   private static final Map f = new HashMap(13);
   private static final long[] g;
   private static final Integer[] h;
   private static final Map i;

   private ModuleManager() {
   }

   @NotNull
   public final ArrayList C() {
      return k;
   }

   @NotNull
   public final ArrayList L() {
      return e;
   }

   public final void t(@NotNull ArrayList <set-?>) {
      long var2 = 0L;
      var2 = b ^ var2;
      Intrinsics.checkNotNullParameter(<set-?>, zkmBootstrap<"q">(22245, 8447262242581645997L ^ var2));
      e = <set-?>;
   }

   @NotNull
   public final ArrayList u() {
      return K;
   }

   private final boolean A() {
      ArrayList var10000 = e;
      Module[] var1 = new Module[]{
         ElytraTarget.INSTANCE,
         BowMinecart.INSTANCE,
         ElytraFreeze.INSTANCE,
         Dismemberment.INSTANCE,
         ExtraTab.INSTANCE,
         ThrowPetard.INSTANCE,
         AntiAim.INSTANCE,
         VoidEsp.INSTANCE,
         Aspect.INSTANCE,
         Test.INSTANCE,
         NameProtect.INSTANCE,
         AnchorBind.INSTANCE,
         Spider.INSTANCE,
         Assistant.INSTANCE,
         AutoBoneMeal.INSTANCE,
         PearlPhaseEsp.INSTANCE,
         AutoSweetBerries.INSTANCE,
         MaceElytraTarget.INSTANCE,
         DamageParticles.INSTANCE,
         InfiniteElytra.INSTANCE,
         MoreKnockback.INSTANCE,
         EcExploit.INSTANCE,
         AntiAttack.INSTANCE,
         MainhandTotem.INSTANCE,
         ServerHelper.INSTANCE,
         BoatFly.INSTANCE,
         Zoom.INSTANCE,
         WindHop.INSTANCE,
         LegitStrafe.INSTANCE,
         DamageTint.INSTANCE,
         FreeLook.INSTANCE,
         AutoNetheriteScrap.INSTANCE,
         FastLatency.INSTANCE,
         PingSpoof.INSTANCE,
         AutoMend.INSTANCE,
         Blocker.INSTANCE,
         AutoShear.INSTANCE,
         SoilRipper.INSTANCE,
         SwingAnimations.INSTANCE,
         Particles.INSTANCE,
         AutoTrader.INSTANCE,
         BuffEffect.INSTANCE,
         ActionRecorder.INSTANCE,
         AutoPotionBrew.INSTANCE,
         AutoLand.INSTANCE,
         AntiKnockBack.INSTANCE,
         ChestStealer.INSTANCE,
         NoCameraClip.INSTANCE,
         Avoid.INSTANCE,
         BreakEsp.INSTANCE,
         XRay.INSTANCE,
         ElytraRecast.INSTANCE,
         AutoBasePlace.INSTANCE,
         GuiMove.INSTANCE,
         MoveFix.INSTANCE,
         NoPush.INSTANCE,
         Sprint.INSTANCE,
         AttributeSwap.INSTANCE,
         AntiVoid.INSTANCE,
         AntiWeb.INSTANCE,
         ElytraPlus.INSTANCE,
         Flight.INSTANCE,
         LevitationControl.INSTANCE,
         Blink.INSTANCE,
         PopChams.INSTANCE,
         NoFall.INSTANCE,
         NoSlow.INSTANCE,
         Phase.INSTANCE,
         ReverseStep.INSTANCE,
         Speed.INSTANCE,
         Step.INSTANCE,
         Timer.INSTANCE,
         WaterSpeed.INSTANCE,
         Aura.INSTANCE,
         AutoCrystal.INSTANCE,
         Absorption.INSTANCE,
         SourceFiller.INSTANCE,
         AutoWeb.INSTANCE,
         MessageAppend.INSTANCE,
         Criticals.INSTANCE,
         HoleFiller.INSTANCE,
         AutoBuff.INSTANCE,
         BowSpam.INSTANCE,
         Reach.INSTANCE,
         AutoWither.INSTANCE,
         HighJump.INSTANCE,
         SkyLanterns.INSTANCE,
         AntiPhase.INSTANCE,
         AutoGapple.INSTANCE,
         Burrow.INSTANCE,
         LegitTotem.INSTANCE,
         Offhand.INSTANCE,
         Surround.INSTANCE,
         AutoArmor.INSTANCE,
         AutoTool.INSTANCE,
         ElytraSwap.INSTANCE,
         InventoryManager.INSTANCE,
         ItemScroller.INSTANCE,
         MiddleClick.INSTANCE,
         AutoMine.INSTANCE,
         NoServerSlot.INSTANCE,
         ReFiller.INSTANCE,
         ToolSaver.INSTANCE,
         XCarry.INSTANCE,
         AntiResourcePack.INSTANCE,
         UI.INSTANCE,
         Trap.INSTANCE,
         SelfTrap.INSTANCE,
         TeamCommunication.INSTANCE,
         Notifier.INSTANCE,
         RPC.INSTANCE,
         Spammer.INSTANCE,
         TapeMouse.INSTANCE,
         Translator.INSTANCE,
         AirPlace.INSTANCE,
         ElytraReplace.INSTANCE,
         AntiHunger.INSTANCE,
         AutoAuth.INSTANCE,
         AutoFish.INSTANCE,
         AutoRespawn.INSTANCE,
         AutoTpaAccept.INSTANCE,
         ChorusDelay.INSTANCE,
         MultiTask.INSTANCE,
         NoDelay.INSTANCE,
         TraceThrough.INSTANCE,
         NoServerRotate.INSTANCE,
         PerfectDelay.INSTANCE,
         PortalGodMode.INSTANCE,
         SpeedMine.INSTANCE,
         AutoSign.INSTANCE,
         FakePlayer.INSTANCE,
         FreeCam.INSTANCE,
         NoRender.INSTANCE,
         Nuker.INSTANCE,
         PearlChaser.INSTANCE,
         Scaffold.INSTANCE,
         CrystalChams.INSTANCE,
         FullBright.INSTANCE,
         HandChams.INSTANCE,
         HitFx.INSTANCE,
         TotemAnimation.INSTANCE,
         Trajectories.INSTANCE,
         ViewModel.INSTANCE,
         WorldRenderer.INSTANCE,
         HoleAnchor.INSTANCE,
         CrystalBind.INSTANCE,
         Search.INSTANCE,
         HoleEsp.INSTANCE,
         EntitySpeed.INSTANCE,
         ItemEsp.INSTANCE,
         LogoutSpots.INSTANCE,
         AutoDripstope.INSTANCE,
         TpsSync.INSTANCE,
         BoxShrinker.INSTANCE,
         AutoLeave.INSTANCE,
         AutoSoup.INSTANCE,
         AutoEat.INSTANCE,
         NameTags.INSTANCE,
         ProjectilePredict.INSTANCE,
         PlayerEsp.INSTANCE,
         StorageEsp.INSTANCE,
         Tracers.INSTANCE,
         NoInteract.INSTANCE,
         AutoEz.INSTANCE,
         MaceKiller.INSTANCE,
         TridentBoost.INSTANCE,
         Quiver.INSTANCE
      };
      return var10000.addAll(CollectionsKt.listOf(var1));
   }

   public final void E() {
      for (Object var8 : e) {
         Module var9 = (Module)var8;

         for (Object var14 : var9.getSettings()) {
            Setting var15 = (Setting)var14;
            Iterable var17 = ConfigStore.INSTANCE.getProfileConfigFile().getCurrent().getModules();
            Iterator var18 = var17.iterator();

            Object var10000;
            while (true) {
               if (!var18.hasNext()) {
                  var10000 = null;
                  break;
               }

               Object var19 = var18.next();
               SettingsConfig var20 = (SettingsConfig)var19;
               if (Intrinsics.areEqual(var20.getId(), var9.getId())) {
                  var10000 = (Logger)var19;
                  break;
               }
            }

            var10000 = (SettingsConfig)var10000;
            if ((SettingsConfig)var10000 != null) {
               SettingsConfig var22 = (SettingsConfig)var10000;
               Iterable var30 = var22.getSettings();
               Iterator var31 = var30.iterator();

               while (true) {
                  if (var31.hasNext()) {
                     Object var32 = var31.next();
                     SettingEntry var21 = (SettingEntry)var32;
                     if (!Intrinsics.areEqual(var21.getId(), var15.getId())) {
                        continue;
                     }

                     var10000 = var32;
                  } else {
                     var10000 = null;
                  }

                  var10000 = (SettingEntry)var10000;
                  if ((SettingEntry)var10000 != null) {
                     SettingEntry var24 = (SettingEntry)var10000;

                     try {
                        var15.loadFromString(var24.getValue());
                     } catch (Exception var29) {
                        var15.reset();
                        var10000 = Globals.getLogger();
                        String var10001 = var15.Q();
                        String var10002 = var9.getDisplayName();
                        String var26 = var29.getMessage();
                        String var27 = var10002;
                        String var28 = var10001;
                        var10000.warn("Could not parse setting: " + var28 + " in " + var27 + ": " + var26);
                     }
                  }
                  break;
               }
            }
         }
      }
   }

   public final void n(char a, int a, char a, @Nullable ProfileConfig prevConfig) {
      long var5 = ((long)a << 48 | (long)ax << 32 >>> 16 | (long)axx << 48 >>> 48) ^ b;
      long var7 = var5 ^ 123279471351034L;
      long var9 = var5 ^ 96422444147632L;

      for (Object var16 : e) {
         Module var17 = (Module)var16;
         if (a < 0) {
            break;
         }

         Boolean var41;
         label118: {
            label117: {
               label126: {
                  ProfileConfig var10000 = prevConfig;
                  if (a >= 0) {
                     if (prevConfig == null) {
                        break label126;
                     }

                     var10000 = prevConfig;
                  }

                  List var19 = var10000.getModules();
                  var41 = var19;
                  if (a >= 0) {
                     if (var19 == null) {
                        break label126;
                     }

                     var41 = var19;
                  }

                  List var20 = var41;
                  Iterator var22 = var20.iterator();

                  label114:
                  while (true) {
                     if (var22.hasNext()) {
                        Object var23 = var22.next();
                        var41 = (Boolean)var23;

                        while (true) {
                           SettingsConfig var24 = (SettingsConfig)var41;
                           if (!Intrinsics.areEqual(var17.getId(), var24.getId())) {
                              continue label114;
                           }

                           var41 = (Boolean)var23;
                           if (ax >= 0) {
                              break label114;
                           }
                        }
                     }

                     var41 = null;
                     break;
                  }

                  SettingsConfig var26 = (SettingsConfig)var41;
                  SettingsConfig var36 = var26;
                  if (a >= 0) {
                     if (var26 == null) {
                        break label126;
                     }

                     var36 = var26;
                  }

                  var20 = var36.getSettings();
                  var41 = var20;
                  if (ax >= 0) {
                     if (var20 == null) {
                        break label126;
                     }

                     var41 = var20;
                  }

                  Object var31 = var41;
                  Iterator var33 = var31.iterator();

                  label104:
                  while (true) {
                     if (var33.hasNext()) {
                        Object var25 = var33.next();
                        var41 = (Boolean)var25;

                        do {
                           SettingEntry var27 = (SettingEntry)var41;
                           if (!Intrinsics.areEqual(var27.getId(), zkmBootstrap<"q">(23460, 8190091732134892529L ^ var5))) {
                              continue label104;
                           }

                           var41 = (Boolean)var25;
                        } while (axx <= 0);
                     } else {
                        var41 = null;
                     }

                     SettingEntry var21 = (SettingEntry)var41;
                     SettingEntry var39 = var21;
                     if (axx > 0) {
                        if (var21 == null) {
                           break;
                        }

                        var39 = var21;
                     }

                     String var32 = var39.getValue();
                     var40 = var32;
                     if (axx <= 0) {
                        break label117;
                     }

                     if (var32 != null) {
                        var40 = var32;
                        break label117;
                     }
                     break;
                  }
               }

               var41 = null;
               break label118;
            }

            var41 = Boolean.parseBoolean(var40);
         }

         Boolean var29 = var41;
         Gofra.INSTANCE.unplug(var17);
         if (axx >= 0 && var17.getEnabled()) {
            Gofra.INSTANCE.plug(var17);
            if (a >= 0 && !Intrinsics.areEqual(var17.getEnabled(), var29)) {
               var17.onEnable(var7);
            }
         } else if (axx >= 0 && prevConfig != null && ax > 0 && !Intrinsics.areEqual(var17.getEnabled(), var29)) {
            var17.onDisable(var9);
         }

         if (a < 0) {
            break;
         }
      }
   }

   public static void M(short var0, int var1, char var2, ModuleManager var3, ProfileConfig var4, Object var5) {
      long var6 = ((long)var0 << 48 | (long)var1 << 32 >>> 16 | (long)var2 << 48 >>> 48) ^ b;
      long var10001 = var6 ^ 64630222683866L;
      int var8 = (int)((var6 ^ 64630222683866L) >>> 48);
      int var9 = (int)((var6 ^ 64630222683866L) << 16 >>> 32);
      int var10 = (int)(var10001 << 48 >>> 48);
      Object var11 = null;
      var3.n((char)var8, var9, (char)var10, (ProfileConfig)var11);
   }

   // $VF: Irreducible bytecode was duplicated to produce valid code
   public final void D(@NotNull String moduleId, long a, @NotNull String id) {
      a = b ^ a;
      Intrinsics.checkNotNullParameter(moduleId, zkmBootstrap<"q">(13665, 4004208588877104410L ^ a));
      Intrinsics.checkNotNullParameter(id, zkmBootstrap<"q">(29629, 7827315236230448065L ^ a));
      int[] var10000 = ZkmReferences.zkmBootstrap<"Å">(-4020481855380492209L, a);
      Iterable var6 = e;
      int[] var5 = var10000;
      var var9 = new ArrayList();
      Iterator var11 = var6.iterator();

      label75:
      while (true) {
         if (var11.hasNext()) {
            Object var12 = var11.next();
            var10000 = (int[])var12;

            do {
               Module var13 = (Module)var10000;
               if (a < 0L) {
                  break label75;
               }

               boolean var20 = Intrinsics.areEqual(var13.getId(), moduleId);
               if (var5 != null) {
                  break label75;
               }

               if (var5 == null) {
                  if (!var20) {
                     continue label75;
                  }

                  var9.add(var12);
               }

               if (var5 == null) {
                  continue label75;
               }

               var10000 = (int[])(new Module[5]);
            } while (a < 0L);

            ZkmReferences.zkmBootstrap<"Å">(var10000, -3964400324356160826L, a);
         }

         var6 = (Iterable & List)var9;
         boolean var21 = false;
         break;
      }

      Iterator var8 = var6.iterator();

      label53:
      while (true) {
         if (var8.hasNext()) {
            var9 = var8.next();
         } else if (a > 0L) {
            return;
         }

         do {
            Module var10 = (Module)var9;
            Iterable var17 = var10.getSettings();
            if (var5 != null) {
               return;
            }

            Iterator var18 = var17.iterator();

            label48:
            while (true) {
               if (var18.hasNext()) {
                  var10000 = (int[])var18.next();
               } else {
                  var10000 = var5;
                  if (a >= 0L) {
                     break;
                  }
               }

               while (true) {
                  Object var14 = var10000;
                  Setting var15 = (Setting)var14;
                  if (!Intrinsics.areEqual(var15.getId(), id)) {
                     break;
                  }

                  var10000 = var5;
                  if (a >= 0L) {
                     break label48;
                  }
               }
            }

            if (var10000 == null) {
               continue label53;
            }
         } while (a <= 0L);

         return;
      }
   }

   @Nullable
   public final Module R(@NotNull String id) {
      Intrinsics.checkNotNullParameter(id, "id");
      Iterable var3 = e;
      Iterator var4 = var3.iterator();

      Object var10000;
      while (true) {
         if (var4.hasNext()) {
            Object var5 = var4.next();
            Module var6 = (Module)var5;
            if (!Intrinsics.areEqual(var6.getId(), id)) {
               continue;
            }

            var10000 = var5;
            break;
         }

         var10000 = null;
         break;
      }

      return (Module)var10000;
   }

   @Flow
   private final void onInput(InputEvent var1) {
      long var2 = b ^ 109803832095917L;
      long var8 = var2 ^ 33612433733519L;
      int var10 = (int)((var2 ^ 39371846139011L) >>> 56);
      long var11 = (var2 ^ 39371846139011L) << 8 >>> 8;
      long var13 = var2 ^ 40790892293658L;
      if (var1.getKey() != -1 && var1.getAction() != InputEvent.Action.Hold) {
         if (var1.getDevice() == InputEvent.Device.Mouse) {
            if (var1.getAction() == InputEvent.Action.Press) {
               K.add(var1.getKey());
            } else {
               K.clear();
            }
         }

         if (var1.getKey() == UI.INSTANCE.getBind().getKey() && var1.getAction() == InputEvent.Action.Press && Globals.getMc().currentScreen == null) {
            Globals.getMc().setScreen(ClickGuiScreen.INSTANCE);
         }

         Iterable var16 = e;
         var var19 = new ArrayList();

         for (Object var22 : var16) {
            Module var23 = (Module)var22;
            if (var23.getBind().getKey() == var1.getKey() && !Intrinsics.areEqual(var23, UI.INSTANCE)) {
               var19.add(var22);
            }
         }

         for (Object var25 : var19) {
            Module var20 = (Module)var25;
            if (!var20.getBind().isHold()) {
               if (var1.getAction() == InputEvent.Action.Press && Globals.getMc().currentScreen == null) {
                  var20.toggle(var8);
               }
            } else if (var1.getAction() == InputEvent.Action.Press) {
               var20.enable((byte)var10, var11);
            } else {
               var20.disable(var13);
            }
         }
      }
   }

   static {
      long var20 = b ^ 125150597177549L;
      long var10001 = var20 ^ 121895766611402L;
      int var24 = (int)((var20 ^ 121895766611402L) >>> 48);
      int var25 = (int)((var20 ^ 121895766611402L) << 16 >>> 32);
      int var26 = (int)(var10001 << 48 >>> 48);
      zkmSetFlag(0);
      Cipher var11;
      Cipher var10000 = var11 = Cipher.getInstance("DES/CBC/PKCS5Padding");
      SecretKeyFactory var10002 = SecretKeyFactory.getInstance("DES");
      byte[] var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

      for (int var12 = 1; var12 < 8; var12++) {
         var10003[var12] = (byte)(var20 << var12 * 8 >>> 56);
      }

      var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
      String[] var18 = new String[8];
      int var16 = 0;
      String var15 = "c\u0087¬d_Ê©3)qSØ\u009c£,ï8ü §\u0090ÊY\u009f\u0090G\u009bºÓ\u0002\bP}\u0090*5¬\u009b\u0088´½ÌK°ÛfÁk?\u000b`\u0013K]È\u0017-åð(\u008dÿ\u001b*(\u0017k\u009d|(;òM\u0018\u0094Ògµ`\r×\u0005TL0±\u0001@M\u0005\u0087/CÄ\u0093çã\u0083\u0010ÎiøCÃµ\u0004\b yº¾\u0097\u0006\u0001Õ\u0010*\u001el\u0080\t7\u007fIþv ñQ(}¼\u0010.\u0088\u009f¥)gÕ\u001f\u001f\u0089.æ\n\u001bÀ\u0007";
      short var17 = 149;
      char var14 = 16;
      int var32 = -1;

      label54:
      while (true) {
         String var33 = var15.substring(++var32, var32 + var14);
         byte var37 = -1;

         while (true) {
            byte[] var19 = var11.doFinal(var33.getBytes("ISO-8859-1"));
            String var45 = zkmDecodeUtf8(var19).intern();
            switch (var37) {
               case 0:
                  var18[var16++] = var45;
                  if ((var32 += var14) >= var17) {
                     c = var18;
                     d = new String[8];
                     i = new HashMap(13);
                     Cipher var0;
                     var10000 = var0 = Cipher.getInstance("DES/CBC/NoPadding");
                     var10002 = SecretKeyFactory.getInstance("DES");
                     var10003 = new byte[]{(byte)(var20 >>> 56), 0, 0, 0, 0, 0, 0, 0};

                     for (int var1 = 1; var1 < 8; var1++) {
                        var10003[var1] = (byte)(var20 << var1 * 8 >>> 56);
                     }

                     var10000.init(2, var10002.generateSecret(new DESKeySpec(var10003)), new IvParameterSpec(new byte[8]));
                     long[] var6 = new long[162];
                     int var3 = 0;
                     String var4 = "Ð_æ,\u0097Áæ~¥Ïrc\u0014ñðÈÓb\u0001g®!Q¡\u008f\u007f\u0099\u0004\tR\u001f3\r¢¿gÈ¼Êõ%$ý{§6¸3e\u0086\u000bôKm\u0094ý¤9Q\"¹GYTàã\u0098¡ÎÎ¶ýâS\rô>öXûâ#\u0086\u001e\u0083t\u0000?a\u001cÃÓÄ\u009fç'ÿ\u0012\u0019;\u001aÕæº\u0092ÍÅýL\u008aªv\u00ad7Z\f\u0003¨·\u0087f;\\B\u008e¹E\u0003æ|\u0094\u000f#\u0007h@®àº_=@aÏñvè\u0017oÝê\u0099\u0010ÕjQ\"j\u0093\u0014\u0001?¡\f¨3é¯Ãxe;Úç\u0086Û\u008c\u009c\u007f\u0016]£\u0096\\xìÀ\\ÈÙ\u0088\u0004\u001eo\u0082i¢ÙÌ§h,\u009f\u0011g\bG¶Î£üº\u001c¦!à\u001e&bÇ\"yø°üGµáu\u0091\u0002üñÇu\u0000BÝ¢\u008c\u001dJ\u0003lýc\u00013ïr\fT\"S\u001f$Eã\u0080a§i\n\u0095\u008d\bÇK\u0004\"õ;}Pg\\<7Ä\u001c»ÎsUhÑ\u0080T2\u0001\u0007:0$¬.¼s\u0012\u000b+\u008eo^¦d\u001e½\u001aÏq\u0093\u0007tüþ<ä\u0080\u000b\u0087÷Aõ±\u0093qü/\r\f°\u008fún'½\u0011¨»\u009cÓe\u009d²oE%\u001b\u0011ò\u0098\u0099\u0083\u008cE&\rS\u0082ü÷jw\u00120KÓÓKÆ7|©Ï«÷ê\u008a\u000fyí\u0013§\u0010Ì¨8Q'\u001aH¶\u0086\u0018\th\b`%G\u001aÂg.³\\Ä¨\u001c\u0086Bóý|5Z4ëR\u008cá/¦L\u0003\u00840ñTX\u0092áñ×\u0002q\u0097\u008bÂ1µÿU¸«N\u0003.¤e\u0011~ù\u0084W-Gç\u0016@\u008d5\u0000i2Áw¹Ö;û¾Úÿ\u0015¥ô\u001eØë·\u009fK#FÆ\u0016\tÓþ\u00ad5íÀu\u0002,>ð\u007fP\u0007uW=ðª×\u0089êâ\u0019ñ\u008f±\båÔ\u001dW0ñ\u0095ªpÐ\n¯ôçëi!º/[²ä\bkïóàô\u00adl:í5l·%;,\u0000¥\u0081\u009a\u0089í\u0000{%øI{UÅÈ\u001b\u009a\u009c^N²\u008aÅó\u009d8TN¹°[öÀ\u0089lcåDàa\u0096\\X48\u0013\u000fô\u0096\u008e`\u0080\u007f\u0005Üsi{¸\u0014÷pÓ\u008bë\u008b\"4\u0018oÿDwÖ \u0013óÁ\u0000sõ\u0091X+\u0094Ø\u0017ËS§]¶à\u0083GN\bìï\u008f\u0005â`Xú\u0007\u0081¿i\u0004\\¤®Ìèg×0E\u001c£ï\nø3AY÷×ïË\n}\u0090\u0091¤\u0007\u0000äõ[d\f°±¹Y\u0015§Áó\u009fØeÎþþo¿^\u000bö#@ãÂ\u0094Ô\u0083\u0005ÀPÅÉ\u0092ü7é\u0002 T½ky\u0090\u0096f\u000eÂf@\u0081\u000b«!¹î¡ïY\u0018\u0089,o\f\u0090è´\\\u000b®Æ\u0007P`\u0094ÈÚÞ\u0001¥\u0085}\u001f,<\u0095áæ\u0085y\u001a¢\t\u000e2<Ä*îÏ\u0099H¿´µÓ,µ'\u0095\u0086â¯å\u0012\u0093±ª\n\u0095\u001e\u0092ÁëÐÑE\\u¾Z\u001e\u008a\u001a-\b:\u0082¨\u0012\u0011)Î£\u0011\u0096/¼Æ\u00814\u0011\tkGd¡\nGÎäñ\u0097\u008c\u0096nw òi\u0089'SÁç\u0004\u000fbË¤ú\u008dõ\u0013Z¶³:M^h\u000f\u000bçrâp9yîá\u0091îÍé\u009d\u0096®cec\u0083sL\u008cÞ\u000b0$Ç\u008d{ü&Cµ,?ëù\u0084Ç\u008f\u0006èdÈï=\u009eíÔ¤X'©m)Ê?D3ðe\u00ad{\u0096+þl\u008biÊÎ¨Ê\\\u0003òo¡¿){\u008a\u0014<\u0095øöOå'ë;\u0083gÇxRØT6¹\u001a%\u00adJ\u000f\u0095cyÑ^\u0011(\u000b¬,\u00832\u0098A£RR\u0086¤Ú\u001f$\u008eeâOnCc_¦Ô\u0083Ô³ª\u008cU\u0097éQè¯e\u0082ç\u0091\u0080[OÎ²æÏ\u0092Ñ/Û}2Dë±t5è\u009b6\u000fò$\bKSµ£R¾vTüî¥Ð}Ñ\u008bRDAø\u0005ð\u00adê\u0094àøÁÆ\u008eÅE\u000e©É\u0001à\u000f(\u0003¥9ÓÒ\nË/\u0084F\u0085\u0085\u0085*\u0015GJ\u001c\u0000yÙý\u008ap(Qè·\u0087ô]÷}W\u00961 )\u0003YlË\u001aÜ\u0088;¶è×\u000f\u0099Ð\u001cjC\u0085úäÞÁùôÉc\u000e\u000e#ÙL\u000b\u000báÀ\u007fWÓ*\t:`J\t!Ùø\u008añ«\u0083ôR\u000b°Ð³|\u0016\u0097\u0085\u0016ÝÆæ3åY\u0087X\u009bXÃó§\u009blªÀzÝÙ~~Ö\u0083¼Û'#òÀ4\u0016¡fj\u0010Ù6¡O5'É\u009eÀÙy³'\u0084Â[¡k\u0086ê\u00adÃh\u0095\u000e¡Ý¤¤)139BØD²Á±";
                     short var5 = 1280;
                     byte var2 = 0;

                     label36:
                     while (true) {
                        byte var41 = var2;
                        var2 += 8;
                        byte[] var7 = var4.substring(var41, var2).getBytes("ISO-8859-1");
                        long[] var36 = var6;
                        int var42 = var3++;
                        long var49 = (var7[0] & 255L) << 56
                           | (var7[1] & 255L) << 48
                           | (var7[2] & 255L) << 40
                           | (var7[3] & 255L) << 32
                           | (var7[4] & 255L) << 24
                           | (var7[5] & 255L) << 16
                           | (var7[6] & 255L) << 8
                           | var7[7] & 255L;
                        byte var52 = -1;

                        while (true) {
                           long var8 = var49;
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
                           long var54 = (var10[0] & 255L) << 56
                              | (var10[1] & 255L) << 48
                              | (var10[2] & 255L) << 40
                              | (var10[3] & 255L) << 32
                              | (var10[4] & 255L) << 24
                              | (var10[5] & 255L) << 16
                              | (var10[6] & 255L) << 8
                              | var10[7] & 255L;
                           switch (var52) {
                              case 0:
                                 var36[var42] = var54;
                                 if (var2 >= var5) {
                                    g = var6;
                                    h = new Integer[162];
                                    INSTANCE = new ModuleManager();
                                    CategoryGroup[] var29 = new CategoryGroup[]{CategoryGroups.m(), CategoryGroups.J(), CategoryGroups.d(), CategoryGroups.h()};
                                    k = CollectionsKt.arrayListOf(var29);
                                    e = new ArrayList();
                                    K = new ArrayList();
                                    INSTANCE.A();
                                    INSTANCE.E();
                                    M((short)var24, var25, (char)var26, INSTANCE, null, null);
                                    return;
                                 }
                                 break;
                              default:
                                 var36[var42] = var54;
                                 if (var2 < var5) {
                                    continue label36;
                                 }

                                 var4 = "É.zXÛ\u0010\u008d\u0017|FX\u009b×\u0000:ñ";
                                 var5 = 16;
                                 var2 = 0;
                           }

                           byte var43 = var2;
                           var2 += 8;
                           var7 = var4.substring(var43, var2).getBytes("ISO-8859-1");
                           var36 = var6;
                           var42 = var3++;
                           var49 = (var7[0] & 255L) << 56
                              | (var7[1] & 255L) << 48
                              | (var7[2] & 255L) << 40
                              | (var7[3] & 255L) << 32
                              | (var7[4] & 255L) << 24
                              | (var7[5] & 255L) << 16
                              | (var7[6] & 255L) << 8
                              | var7[7] & 255L;
                           var52 = 0;
                        }
                     }
                  }

                  var14 = var15.charAt(var32);
                  break;
               default:
                  var18[var16++] = var45;
                  if ((var32 += var14) < var17) {
                     var14 = var15.charAt(var32);
                     continue label54;
                  }

                  var15 = "\u0000Æ\bOÆeòh=t\u008c©ábt°\u0010\u008eÕJºrÎ\u001f×â¤T^ð;\u0001\u008a";
                  var17 = 33;
                  var14 = 16;
                  var32 = -1;
            }

            var33 = var15.substring(++var32, var32 + var14);
            var37 = 0;
         }
      }
   }

   public static void zkmSetFlag(int var0) {
      L = var0;
   }

   public static int zkmGetFlag() {
      return L;
   }

   public static int zkmIntConstant() {
      return 47;
   }

   private static Exception zkmRethrow(Exception var0) {
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
      int var5 = var0 ^ (int)(var1 & 32767L) ^ 31141;
      if (d[var5] == null) {
         Object[] var4;
         try {
            Long var3 = Thread.currentThread().threadId();
            var4 = (Object[])f.get(var3);
            if (var4 == null) {
               var4 = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
               f.put(var3, var4);
            }
         } catch (Exception var10) {
            throw new RuntimeException("su/catlean/iq", var10);
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
         throw new RuntimeException("su/catlean/iq" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }

   private static int zkmDecryptInt(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 25371;
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
            throw new RuntimeException("su/catlean/iq", var14);
         }

         int var15 = (var10[4] & 255) << 24 | (var10[5] & 255) << 16 | (var10[6] & 255) << 8 | var10[7] & 255;
         h[var3] = var15;
      }

      return h[var3];
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
         throw new RuntimeException("su/catlean/iq" + " : " + var1 + " : " + var2.toString(), var5);
      }
   }
}
