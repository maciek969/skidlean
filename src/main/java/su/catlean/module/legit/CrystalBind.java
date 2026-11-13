/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.legit;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.world.EntitySpawn;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.MinecraftAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.Timer;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;

public final class CrystalBind
extends Module {
    @NotNull
    public static final CrystalBind INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting crystalDelay$delegate;
    @NotNull
    private static final BindSetting crystalBind$delegate;
    @NotNull
    private static final BooleanSetting changePitch$delegate;
    @NotNull
    private static final BooleanSetting crystalOptimizer$delegate;
    @NotNull
    private static final BooleanSetting switchBack$delegate;
    @NotNull
    private static Vec3d crystalPos;
    @NotNull
    private static Timer timer;
    private static final long a;
    private static final String[] d;
    private static final String[] g;
    private static final Map h;
    private static final long[] i;
    private static final Integer[] j;
    private static final Map k;

    private CrystalBind() {
        super("crystal-bind", Categories.getLegit(), null, null);
    }

    private final int getCrystalDelay() {
        return ((Number)crystalDelay$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final Bind getCrystalBind() {
        return (Bind)crystalBind$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getChangePitch() {
        return (Boolean)changePitch$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getCrystalOptimizer() {
        return (Boolean)crystalOptimizer$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getSwitchBack() {
        return (Boolean)switchBack$delegate.getValue(this, delegatedProperties[4]);
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        var2_2 = CrystalBind.a ^ 49784276739555L;
        var24_3 = (int)((var2_2 ^ 60301008579005L) << 48 >>> 48);
        new int[0];
        if (!(Globals.getMc().crosshairTarget instanceof EntityHitResult)) ** GOTO lbl-1000
        v0 = Globals.getMc().crosshairTarget;
        Intrinsics.checkNotNull(v0, "null cannot be cast to non-null type net.minecraft.world.phys.EntityHitResult");
        if (((EntityHitResult)v0).getEntity() instanceof EndCrystalEntity) {
            v1 = true;
        } else lbl-1000:
        // 2 sources

        {
            v1 = var32_4 = false;
        }
        if (!(Globals.getMc().crosshairTarget instanceof BlockHitResult)) ** GOTO lbl-1000
        v2 = Globals.getWorld();
        v3 = Globals.getMc().crosshairTarget;
        Intrinsics.checkNotNull(v3, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
        if (Intrinsics.areEqual(v2.getBlockState(((BlockHitResult)v3).getBlockPos()).getBlock(), Blocks.OBSIDIAN)) ** GOTO lbl-1000
        v4 = Globals.getWorld();
        v5 = Globals.getMc().crosshairTarget;
        Intrinsics.checkNotNull(v5, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
        if (Intrinsics.areEqual(v4.getBlockState(((BlockHitResult)v5).getBlockPos()).getBlock(), Blocks.BEDROCK)) lbl-1000:
        // 2 sources

        {
            v6 = true;
        } else lbl-1000:
        // 2 sources

        {
            v6 = false;
        }
        var33_5 = v6;
        var37_6 = this.getCrystalBind().getKey();
        var38_7 = (char)var24_3;
        if (KeyboardUtil.isKeyPressed(var38_7, var37_6) && CrystalBind.timer.hasPassed2(this.getCrystalDelay() * (var32_4 != false ? 1 : (var33_5 != false ? 2 : 4)))) {
            var35_8 = new Item[1];
            Intrinsics.checkNotNullExpressionValue(Items.END_CRYSTAL, "END_CRYSTAL");
            var34_10 = InventoryUtil.INSTANCE.findInHotbar(var35_8).getSlot();
            var36_11 = new Block[1];
            Intrinsics.checkNotNullExpressionValue(Blocks.OBSIDIAN, "OBSIDIAN");
            var35_9 = InventoryUtil.INSTANCE.findBlockInHotbar(var36_11).getSlot();
            if (var35_9 == -1 && !var33_5 && !var32_4 || var34_10 == -1 || var34_10 >= 9 || var35_9 >= 9) {
                return;
            }
            if (var32_4) {
                v7 = Globals.getInteractionManager();
                v8 = Globals.getPlayer();
                v9 = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(v9, "null cannot be cast to non-null type net.minecraft.world.phys.EntityHitResult");
                v7.attackEntity(v8, ((EntityHitResult)v9).getEntity());
                Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                return;
            }
            var36_12 = Globals.getPlayer().getInventory().getSelectedSlot();
            if (!var33_5) {
                if (Globals.getPlayer().getInventory().getSelectedSlot() != var35_9) {
                    InventoryUtil.INSTANCE.selectSlot(var35_9);
                }
                v10 = Globals.getMc();
                Intrinsics.checkNotNull(v10, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
                ((MinecraftAccessor)v10).idoItemUse();
                Scheduler.schedule$default(Scheduler.INSTANCE, this.getCrystalDelay(), (byte)0, false, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, onPlayerUpdate$lambda$0(int int ), ()V)((int)var34_10, (int)var36_12), 2, null);
            } else {
                InventoryUtil.INSTANCE.selectSlot(var34_10);
                Scheduler.schedule$default(Scheduler.INSTANCE, this.getCrystalDelay(), (byte)0, false, (Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, onPlayerUpdate$lambda$1(int ), ()V)((int)var36_12), 2, null);
            }
        }
    }

    @Flow
    private final void onEntitySpawn(EntitySpawn entitySpawn) {
        block2: {
            if (!(entitySpawn.getEntity() instanceof EndCrystalEntity) || Intrinsics.areEqual(crystalPos, Vec3d.ZERO) || !(entitySpawn.getEntity().squaredDistanceTo(crystalPos) < 4.0)) break block2;
            Vec3d vec3d = Vec3d.ZERO;
            Intrinsics.checkNotNullExpressionValue(vec3d, "ZERO");
            crystalPos = vec3d;
            if (this.getChangePitch()) {
                ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                Entity entity = entitySpawn.getEntity();
                Vec3d vec3d2 = this.getPos(entity).add(0.0, 0.15, 0.0);
                Intrinsics.checkNotNullExpressionValue(vec3d2, "add(...)");
                clientPlayerEntity.setPitch(Rotation.Companion.to(vec3d2).fixSensitivity().getPitch());
            }
            Globals.getInteractionManager().attackEntity(Globals.getPlayer(), entitySpawn.getEntity());
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            if (this.getCrystalOptimizer()) {
                ClientWorld clientWorld = Globals.getWorld();
                Entity entity = entitySpawn.getEntity();
                Intrinsics.checkNotNull(entity, "null cannot be cast to non-null type net.minecraft.world.entity.boss.enderdragon.EndCrystal");
                clientWorld.removeEntity(((EndCrystalEntity)entity).getId(), Entity.RemovalReason.KILLED);
            }
        }
    }

    private static final void onPlayerUpdate$lambda$0$lambda$0$lambda$0(int n) {
        InventoryUtil.INSTANCE.selectSlot(n);
    }

    private static final void onPlayerUpdate$lambda$0$lambda$0(int n) {
        block1: {
            MinecraftClient minecraftClient = Globals.getMc();
            Intrinsics.checkNotNull(minecraftClient, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
            ((MinecraftAccessor)((Object)minecraftClient)).idoItemUse();
            Object object = Globals.getMc().crosshairTarget;
            if (object == null || (object = ((HitResult)object).getPos()) == null) {
                Vec3d vec3d = Vec3d.ZERO;
                object = vec3d;
                Intrinsics.checkNotNullExpressionValue(vec3d, "ZERO");
            }
            crystalPos = object;
            if (!INSTANCE.getSwitchBack()) break block1;
            Scheduler.schedule$default(Scheduler.INSTANCE, INSTANCE.getCrystalDelay(), (byte)0, false, () -> CrystalBind.onPlayerUpdate$lambda$0$lambda$0$lambda$0(n), 2, null);
        }
    }

    private static final void onPlayerUpdate$lambda$0(int n, int n2) {
        InventoryUtil.INSTANCE.selectSlot(n);
        Scheduler.schedule$default(Scheduler.INSTANCE, INSTANCE.getCrystalDelay(), (byte)0, false, () -> CrystalBind.onPlayerUpdate$lambda$0$lambda$0(n2), 2, null);
    }

    private static final void onPlayerUpdate$lambda$1$lambda$0(int n) {
        InventoryUtil.INSTANCE.selectSlot(n);
    }

    private static final void onPlayerUpdate$lambda$1(int n) {
        block1: {
            MinecraftClient minecraftClient = Globals.getMc();
            Intrinsics.checkNotNull(minecraftClient, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
            ((MinecraftAccessor)((Object)minecraftClient)).idoItemUse();
            Object object = Globals.getMc().crosshairTarget;
            if (object == null || (object = ((HitResult)object).getPos()) == null) {
                Vec3d vec3d = Vec3d.ZERO;
                object = vec3d;
                Intrinsics.checkNotNullExpressionValue(vec3d, "ZERO");
            }
            crystalPos = object;
            if (!INSTANCE.getSwitchBack()) break block1;
            Scheduler.schedule$default(Scheduler.INSTANCE, INSTANCE.getCrystalDelay(), (byte)0, false, () -> CrystalBind.onPlayerUpdate$lambda$1$lambda$0(n), 2, null);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        CrystalBind.a = ZkmKeys.a(-8322813928051460090L, 5770892342167639590L, MethodHandles.lookup().lookupClass()).a(113023663211465L);
                        var20 = CrystalBind.a ^ 134040623585386L;
                        CrystalBind.h = new HashMap<K, V>(13);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[28];
                        var16_4 = 0;
                        var15_5 = "\u0096\u00c5)\u00a7\u00b6\u00cb\u00cf\u00ef\u008c\u0081\u00ddv\u00b6m15\u0097D\u00fb\u00f1:^\u00f7\u00b8\u00a5\u00c2\u00af\u0006\u00979\u0015\f\u0080\u0080\u00bf\u00f8R\u00f9\u00ef\u00cc\u00a3\u00068\u001a\u00a2\u00d2\u00ac|/\u00d9\u00f6G\u00ab l\u001aN4|\u00e2X\u00aa\u00d3v\u0016\u00f7S\u00e2\u00f6\u00a1\u0017]\u00bd-\u00f34Y\u00be2\u00f3\u009e\u00fd\u0005\u00b4\u001e\u00c4\u00c3%O\u00e8\u00d0\u00a8\u00b27\u00fe\u0098d.g\u0082\t\"\u0083%\u00a9\u009b\u0085\f\u0093\u00e1\u00c0\u000f\u00a4]Y\t`x\u0084\u0019\u00b2\u0083\u00d4\u00a6\u00e9\u00be\u0096\u00f1o\u00f4\u00d4\u001e5\u008e\u0083\u00aa}[\u00d6\u00eemZ\u00f2\u00ae\u00d8\u00a6\f\u00d9\u0012\u00c0\u00b9\u00acX*i\u00a7\u000e=o,?(\u0091>#{\u00ac0}E\u00a6'dw\b\u00d4aG\u00f4I4\u00c1\u0007r/-L\u0093#\u00a1\u00c3\u001aO\u0003\u00f4\u0093\u00ea\u00ed\u0017P+\u0099\u0088\u00df\u0013\u001d\r\u00f1\u00a7\u00f2\u00b0y\u00f1\r\u00be\u00ef\u001b\u00e4\u00b0]\u00e0_\f\u0017\u008e:\u0003\u000e\u00e4\u00cb\u00c1\u0017\u00a7^\u00af&iaU\u00f3\u009a\u007febFS\u00a5Oi\u00f1\u009e==[\u0082\u00b6G\u0015\u009e*\u00f6\t\u0088\u008c\u00df\u000b(\u00e2X\u001f&\u00c7\u00a1\u008c\u00f3d\u00ab\u00fb\u0010R\u0002-\u00bf\u00b5\u00d5\u00f8\u00c0\u0018y)\u00a4{}\u00b33\u001e\u00cf/)\u00e0P\u0015?\u00a8\u00fe\u001f\u00a4a\u00e0\u00a6\u00b6\u00b5\u00dbi\u00e8\u00d5\u00c8\u0090\u0019J\u009b\u00d6J\u001b\fPpXv\u00d1}\u0011\u00ed\u00f1~mx\u00d2\u00fc\u0010{\u00f7\u000f 94m\u00a47\b\u00cdm\u00de`_%\u0010\u00c0\b\u007fj\u00a2\u00bd{.\u00fe\u00ed\u00c2\u00a2\u00c5~\u00b2\u00f0 Y8\u00f6\u0081\u00ed\u0015\u00d4F\u00f3\u00eecQ^Ci^U\f\u00fd\u00ec\u00b8h\u0092\u00c8m?\u0095\u00af\u00b7\u00d4\u009d\u00ed\u00100Gw\u0095\u00cb\u00ee\u00af\u001e'z|\u00bd\u00fa\u0013\u00bc8 \rV\u00b8\u0090\u00d4\u00fe\u00b3&\u00caT\u00e6\u0010\u00b7\u00b6\u00df!E\u00e0\u00ce\u00fd\u00dd^{\u00fd\u009ctmk\u00ac \u0087\u00ec\u0018\u009b\u00a0*\u0019\u00b1\u009d\u0011\u008f\u00ce\u0004G\tt,]\u00d5\u0015\u0090[\u0090+\u00cf']\u0018\r\u0017\u0088\u00cc\u0088\u00fe\u0002G&!\u00da\u00c0v\u00e5\u00b3\u00b6\u00c5\u0016\u009f&\u0001\u001e\u0085\u00a8\u0080\u00df\u001cR\u00a4h\u00ccI\u00eb\u0006\u008a\u0005B8\u0092,\u00eeL\u00a8j\u00b8\u0096c\f\u00a1\u00d7\u00b0vaY.\u00b8\u00b3\u00be\u0014\u009b\u00d9e\u008aF\u0093\u00f8\u00acc\u00e9\u0091\u008699\u0012\u00b6\f\u0097v\u0016\u0086X\u0086z\u00ad~\u00f43\u001c\u00ee\u008e\u00a7Y\u00d4b\u00cf\u00b7\u00a6\u00fa\u00da\u00a2\u00e8\bsR&\u0097?\u00a0I\u00cb\u00e8\u0001K;<,\u00d0\u00a4\u00cfp\u00c7\u00ca\u00ed\u00eda\u00ff|\u000e6j\u00e8\u00a7\u001c\u00f0\u00fap\u00feR\u00bd-v\u00eeT\u0000\u008d%\u00c24\u00e2?\u0084\u00de\u00e3 k\u008c\u00d8\u0081\u00b4S\u0010\u0096J\u00fb\u0000\u00ca\u00d9\u000ft\u00ebN\u00b4\u00e4\u00c5pZf\u00d4+\u0002qFwBA\u0014 \fu\f\u00be\u00d9\u00b2\\\u00f7\u0002\u00a4,a\u00c3\u008bO,\u00d5\u00b4\u00a5\u00b03Y\u0011^\u00e2\u00f2eXk\u00a0\u00a8\u00b8 \u008e\u000f\u00a5\u00b1\u0014\u00ec\u00ab\u00c2\u0019\u00ab\u00d6f\u0012?\u00a5@\u00ca\u0089\u0015\u00c1{\u00f2P{\u00cbG\u00f4\u00a4\u008c\u00a9u[(\u00d3\u00fe'Jc\\\u00c4\u00b6\u001c\u00b5\u0082\u00fc\u00df\u0089\u00cf\u00cc\u00d8\u00aekIF'i{\u00a7\"\u00a1\u0084z\u001f\u00d4A\u00f7\u008a\u0006\u009a\u00d7\u00ac\u00db\u00a8 \u00b5\u00b2\u00bb\u0013\u0083\u00a7{h KO\u00fe\u0091\u00c9<\u00d9$\u0011pf\u00fb\u0095\u00d6\u00ff7\u00f2L\u00a1\u00f5\u00132A(\u00b7\u00c2$\u001d\u00c5o^\u008caL\u00f24\u00e5t\u00b7W(\u00fe\u0096\u00f4\u00b0\u000e-\u00d3\u00b6S\u009e6\u0011\u0011$P\u00a5]'\u00bbX\u00d4\u00a6\u00eb \u0010E\u00b1`0-h\u0092of0\u00e6\u00f91&\u00f7\u00e9\u0000Q\u0004\u00bb2\u008bH<\u00cd\u00f4I:\u00c4\u00de\u000bx|W\u00ae\u00c8za\u00a6&\u001e\r\u0001\u00b1'\n\u007f\u00ba\u00e3w\u00f9\u009a\u00b5\u00d2\u0011U\u00e2h\u00a6\u00f2\u00b5\u0014\u000eF=\u00d9\u00ad\u00f0tjZ\u00f2\u00a95\u0012\u00837\u009e\u0097:p\u0085$t\u0003\b\u00a5\u00e2\u00b6\u0098\u00d5\u00e9\u008e\u00ed\u00df\u00e8\u0081[\u00b6\u0003\u00e2\u0017\u00d5\u00e2\u00d7\\\u00d8\u00cc\u00a3s\u00d0\u009e\u00a0&\u00c7x\u00dd5\u00c8\u0004l@fr\u009d\u00bb\u00b0\u00e4f\u0086\u009eR\u00e5\u00bbC\u00ea\u00f9&!\u00a2\u00e9-\u00c7\u009b\u00a7\u00e7\u00b3kt\u00d6\u001dZ\u0090e\u00db+\u00c0\u0018p\u0017g\u0099\u0018\u0017f<\u0011Q\b\u00a8M'\u00ea\u00be\u00fc\u0087\u0088J\u0015\u00ad\u0001\u00b8a\u0007\u00a9\u00fa\u00a5\u001a\u000e\u0017\u00f1|^|\u001cH\u008eS\u00fd\u0014\u0002gi)\u00e7\u00b0\u00fb/\u0006\u001a\u0010\u0011&z\u001f\u009d\u009c\u00dck\u00f2\u00ea\u00a1\u00c1\u00ca\u00c9\u007f\u00b3\u0019k\u00f9\u00ef\u00be\u00b8y\u0003\u00b2t\u0093q\u0099\u00bf\u009d\b\u00be\u00d6\u00eb\\y3\u00d9\u007f\u00d1\u008a|z\u00eb\t\u00a7xh\u0087\u000bn >\u00dc\u0085\u00a9\u0005\u00c8\u001fg\u00a0\u0080\u0006\u0080\u00b6V\u0005\u00f4\u0080F\u00e8\u00dbM\u00d4\u00f2\u00ec\u00e8\u0007@\u00d2\u009ca\u00ff\u0004H\u0018\u0000\u0003\u00cdZ$q\u00a15*G\u001b\u001d\u0015w\u00aa%-Bv\u00aa\u00a4W\u00c3\u0005(.\u009f\u00d3\u0097\r\u0019}\u0098\u001aU\u00a1\u00155@\u00a5Y\u0018\u0019h\u00c7\u00bf\u00c7\u00f0\u00bcM\u00df\"\u00b7\u00feq\u00a7\u00ab\u00d1\u00a7\u0090\u009f\u00d4\u00f7\u00e5\u00f6\u0088\u009e\u00b9v\u00d0\u00ef47\u00a2D\u00005>\u00b2Gz9\u0013+\u00e5qn\u00dd\u00bck\u00f5H2(\u008a\u00ad>t\u00ff\u009cW\u00c7\u00101\u0004\u0003\u00b21\u00aa\f~\u001fgj}v\u00da\u00f1b{\u0087\u001a\u00f69\u00a7\u00e5c\u00b6\u00f2m\u000b\u00f3\u00cb\u00f1l\u0091\u00a7\u001d\u0087\u00b7B\u001b\u001f\u009f\u001dd\rB\u001a\u00e9\u0091\u00f2\u00f1\u00d7\u00ef\u00b5Y\u0086\u00193\u00bb\u00bf\u0089V;\f\\i\u00f63\u007f\u00f0\u00c3`\u0095^r\u009d\u00a4\u009f_\u00cc\u00adS{\u0012\u00a57\f(\f\u0096N(\u0006\u00fb?\u00a8\u00ca\u00f4\u00c8\u009c \u00bc\u00cc\\\u00b1\u00ac$\u00af'\u00f9\u00cb\u00ab\u0095\u00f5\u00cc*\u00e5Y\u008aZ?\u00ac:a\u00a3\u00c0\u00ad\u00c4\u00a9\u001el\u00c310\"\u00ac\u0098\u0081\u0012)P\u00eeS\u001b`\u00f5t\u001d\u00f0\u00a1\u001f\u00ce\u00a7>d\u0084\u009a'q2AhZ\u0089\u0092nZ8A\ntT\u0006C\u00cdeY\u0089\u00f2!\u00d6\u0080";
                        var17_6 = 1433;
                        var14_7 = 32;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = CrystalBind.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ff\u00a8g\b\u00806SO\"\u00f5\u00f6\u00f2\u0007\u00b5T\u00f0\u00b7AU\u00d9=\u00ca\u00ea\u00a6\u00b6#\u00dc|\u00fc\u00bb\b\u0000\u00a7\u0006\u00ff\u00e36\u00cc\u009d{\u0090y6p\r\u00db\u00aa\u00f7\u000b\u001ad[\u00bc\u00ee\u00f38\u00baM\u001a\u0005\u0093f\u0099\u00caU\u00e0X({\u00de\u00a7\u00c4\u00c9_\u00a1\u00e7\u009e>3,\u0012\u0006\u0019^\u00bf\u0082\u0016\u00ac\u00cbI\u0093 y\u00d5Y\u00c9\u001f\u008f\u00e1\u00d7W&\u008f-\u00eb\u00eayBw\n\u00d7\u00f0\u00d0\u00df\u00a5\u00db\u00ce\u00f5g\u00fa\u0089\u00e6m\u00b1\u0080\u0002V\u00ea[\u00aa\u00a2*Kn\u00e4\u00d4\u009c'\u001a\u001fi\u0003\u009c\u00bf\u001cE\u00f8\u009dz\u0004\u00b8\u00e4\u001cNP\u00b6*\u00fc\u009f\u0005O\u0011\u00cb\u00b9\u0081\\\u0098\u0097\u000f\u00de,\u00cbe\u00b2U\u008a\u00d0h\u0003\u0014\u00dcH\u007f\u001d*";
                            var17_6 = 185;
                            var14_7 = 40;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = CrystalBind.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                CrystalBind.d = var18_3;
                CrystalBind.g = new String[28];
                CrystalBind.k = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = "\u00deZ`\u0083\u008d\u00054\u00cb\u0099\u00b4\u00f6\u0089\u0097O\u000bZ\u00ba\u00ee1\u00ea\u00f5\u00ed}\u0090\u0099\u008ek\u0019H\u00ee\u00eck\"\u00f1\u00d4\u0002F\u009cH\u00a4";
                var5_15 = 40;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "[N\u00af\u00c1sTw\u0002=7}W\u0019_\u00e4\u00dd";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl104:
                // 1 sources

                ** continue;
            }
        }
        CrystalBind.i = var6_12;
        CrystalBind.j = new Integer[7];
        var33_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(CrystalBind.class, "crystalDelay", "getCrystalDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(CrystalBind.class, "crystalBind", "getCrystalBind()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(CrystalBind.class, "changePitch", "getChangePitch()Z", 0)), Reflection.property1(new PropertyReference1Impl(CrystalBind.class, "crystalOptimizer", "getCrystalOptimizer()Z", 0)), Reflection.property1(new PropertyReference1Impl(CrystalBind.class, "switchBack", "getSwitchBack()Z", 0))};
        CrystalBind.delegatedProperties = var33_20;
        CrystalBind.INSTANCE = new CrystalBind();
        CrystalBind.crystalDelay$delegate = SettingFactory.intSetting$default(CrystalBind.INSTANCE, "delay", 0, new IntRange(0, 250), null, null, 24, null);
        CrystalBind.crystalBind$delegate = SettingFactory.bindSetting$default(CrystalBind.INSTANCE, "crystal-bind", new Bind(0, false, false, 7, null), null, null, 12, null);
        CrystalBind.changePitch$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalBind.INSTANCE, "change-pitch", false, null, null, 12, null);
        CrystalBind.crystalOptimizer$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalBind.INSTANCE, "optimizer", false, null, null, 12, null);
        CrystalBind.switchBack$delegate = SettingFactory.booleanSetting$default((SettingContainer)CrystalBind.INSTANCE, "switch-back", false, null, null, 12, null);
        v15 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v15, "ZERO");
        CrystalBind.crystalPos = v15;
        CrystalBind.timer = new Timer();
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static String zkmDecodeUtf8(byte[] byArray) {
        int n = 0;
        int n2 = byArray.length;
        char[] cArray = new char[n2];
        for (int i = 0; i < n2; ++i) {
            char c;
            int n3 = 0xFF & byArray[i];
            if (n3 < 192) {
                cArray[n++] = (char)n3;
                continue;
            }
            if (n3 < 224) {
                c = (char)((char)(n3 & 0x1F) << 6);
                n3 = byArray[++i];
                c = (char)(c | (char)(n3 & 0x3F));
                cArray[n++] = c;
                continue;
            }
            if (i >= n2 - 2) continue;
            c = (char)((char)(n3 & 0xF) << 12);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F) << 6);
            n3 = byArray[++i];
            c = (char)(c | (char)(n3 & 0x3F));
            cArray[n++] = c;
        }
        return new String(cArray, 0, n);
    }

    private static String zkmDecryptString(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2169;
        if (g[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qs", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            CrystalBind.g[n2] = CrystalBind.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return g[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalBind.zkmDecryptString(n, l);
        MethodHandle methodHandle = MethodHandles.constant(String.class, string2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return string2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/qs" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x771D;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = i[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qs", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CrystalBind.j[n2] = n3;
        }
        return j[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CrystalBind.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/qs" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalBind.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(CrystalBind.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

