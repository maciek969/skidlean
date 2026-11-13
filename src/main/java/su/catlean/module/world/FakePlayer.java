/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package su.catlean.module.world;

import com.mojang.authlib.GameProfile;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ButtonSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.util.ActionHolder;
import su.catlean.util.Globals;
import su.catlean.util.PositionSnapshot;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;

public final class FakePlayer
extends Module {
    @NotNull
    public static final FakePlayer INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ButtonSetting recordButton$delegate;
    @NotNull
    private static final ButtonSetting button$delegate;
    @NotNull
    private static final BooleanSetting copyInventory$delegate;
    @NotNull
    private static final TextSetting name$delegate;
    @Nullable
    private static OtherClientPlayerEntity fakePlayer;
    @NotNull
    private static final List recordedPositions;
    private static int C;
    private static boolean U;
    private static boolean n;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] h;
    private static final Integer[] j;
    private static final Map k;

    private FakePlayer() {
        super("fake-player", Categories.getWorld(), null, null);
    }

    private final ActionHolder getRecordButton() {
        return (ActionHolder)recordButton$delegate.getValue(this, delegatedProperties[0]);
    }

    private final ActionHolder getButton() {
        return (ActionHolder)button$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getCopyInventory() {
        return (Boolean)copyInventory$delegate.getValue(this, delegatedProperties[2]);
    }

    private final String getName() {
        return (String)name$delegate.getValue(this, delegatedProperties[3]);
    }

    @Nullable
    public final OtherClientPlayerEntity getFakePlayer() {
        return fakePlayer;
    }

    public final void setFakePlayer(@Nullable OtherClientPlayerEntity otherClientPlayerEntity) {
        fakePlayer = otherClientPlayerEntity;
    }

    @Override
    public void onEnable(long l) {
        if (Globals.getMc().player == null) {
            return;
        }
        OtherClientPlayerEntity otherClientPlayerEntity = fakePlayer = new OtherClientPlayerEntity(Globals.getWorld(), new GameProfile(UUID.fromString("66123666-6666-6666-6666-613376666600"), this.getName()));
        Intrinsics.checkNotNull(otherClientPlayerEntity);
        otherClientPlayerEntity.copyPositionAndRotation(Globals.getPlayer());
        if (this.getCopyInventory()) {
            OtherClientPlayerEntity otherClientPlayerEntity2 = fakePlayer;
            Intrinsics.checkNotNull(otherClientPlayerEntity2);
            otherClientPlayerEntity2.setStackInHand(Hand.MAIN_HAND, Globals.getPlayer().getMainHandStack().copy());
            OtherClientPlayerEntity otherClientPlayerEntity3 = fakePlayer;
            Intrinsics.checkNotNull(otherClientPlayerEntity3);
            otherClientPlayerEntity3.setStackInHand(Hand.OFF_HAND, Globals.getPlayer().getOffHandStack().copy());
            int n = 4;
            int n2 = 0;
            while (n2 < n) {
                int n3 = n2++;
                OtherClientPlayerEntity otherClientPlayerEntity4 = fakePlayer;
                Intrinsics.checkNotNull(otherClientPlayerEntity4);
                otherClientPlayerEntity4.getInventory().setStack(35 + n3, Globals.getPlayer().getInventory().getStack(35 + n3).copy());
            }
            OtherClientPlayerEntity otherClientPlayerEntity5 = fakePlayer;
            Intrinsics.checkNotNull(otherClientPlayerEntity5);
            otherClientPlayerEntity5.getAttributes().setFrom(Globals.getPlayer().getAttributes());
        }
        ClientWorld clientWorld = Globals.getWorld();
        OtherClientPlayerEntity otherClientPlayerEntity6 = fakePlayer;
        Intrinsics.checkNotNull(otherClientPlayerEntity6);
        clientWorld.addEntity(otherClientPlayerEntity6);
        OtherClientPlayerEntity otherClientPlayerEntity7 = fakePlayer;
        Intrinsics.checkNotNull(otherClientPlayerEntity7);
        otherClientPlayerEntity7.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 9999, 2));
        OtherClientPlayerEntity otherClientPlayerEntity8 = fakePlayer;
        Intrinsics.checkNotNull(otherClientPlayerEntity8);
        otherClientPlayerEntity8.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 9999, 4));
        OtherClientPlayerEntity otherClientPlayerEntity9 = fakePlayer;
        Intrinsics.checkNotNull(otherClientPlayerEntity9);
        otherClientPlayerEntity9.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 9999, 1));
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        OtherClientPlayerEntity otherClientPlayerEntity = fakePlayer;
        if (receivePacket.getPacket() instanceof ExplosionS2CPacket && otherClientPlayerEntity != null && otherClientPlayerEntity.hurtTime == 0) {
            otherClientPlayerEntity.onDamaged(Globals.getWorld().getDamageSources().generic());
            float f = otherClientPlayerEntity.getHealth();
            float f2 = otherClientPlayerEntity.getAbsorptionAmount();
            Vec3d vec3d = ((ExplosionS2CPacket)receivePacket.getPacket()).comp_2883();
            Intrinsics.checkNotNullExpressionValue(vec3d, "center(...)");
            otherClientPlayerEntity.setHealth(f + (f2 - DamageUtil.getExplosionDamage$default(DamageUtil.INSTANCE, vec3d, otherClientPlayerEntity, 0, false, null, false, 60, null)));
            if (otherClientPlayerEntity.isDead()) {
                otherClientPlayerEntity.setHealth(10.0f);
                OtherClientPlayerEntity otherClientPlayerEntity2 = fakePlayer;
                Intrinsics.checkNotNull(otherClientPlayerEntity2);
                EntityStatusS2CPacket entityStatusS2CPacket = new EntityStatusS2CPacket(otherClientPlayerEntity2, 35);
                new ReceivePacket(entityStatusS2CPacket).call();
            }
        }
    }

    @Flow
    private final void onPreSync(PreSyncEvent preSyncEvent) {
        block7: {
            long l;
            block6: {
                long l2 = a ^ 0x459F950B733L;
                l = l2 ^ 0x4DD097909942L;
                if (U) {
                    recordedPositions.add(new PositionSnapshot(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ(), Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch()));
                    return;
                }
                if (fakePlayer == null) break block6;
                OtherClientPlayerEntity otherClientPlayerEntity = fakePlayer;
                Intrinsics.checkNotNull(otherClientPlayerEntity);
                if (otherClientPlayerEntity.age % 8 == 0) {
                    OtherClientPlayerEntity otherClientPlayerEntity2 = fakePlayer;
                    Intrinsics.checkNotNull(otherClientPlayerEntity2);
                    OtherClientPlayerEntity otherClientPlayerEntity3 = otherClientPlayerEntity2;
                    otherClientPlayerEntity3.setHealth(otherClientPlayerEntity3.getHealth() + 0.5f);
                }
                OtherClientPlayerEntity otherClientPlayerEntity4 = fakePlayer;
                Intrinsics.checkNotNull(otherClientPlayerEntity4);
                Entity entity = otherClientPlayerEntity4;
                Entity entity2 = Globals.getPlayer();
                if (this.getPos(entity).squaredDistanceTo(this.getPos(entity2)) > 400.0) {
                    this.disable(l);
                    return;
                }
                if (n && !((Collection)recordedPositions).isEmpty()) {
                    int n = C;
                    if ((C = n + 1) >= recordedPositions.size()) {
                        C = 0;
                        return;
                    }
                    PositionSnapshot positionSnapshot = (PositionSnapshot)recordedPositions.get(C);
                    OtherClientPlayerEntity otherClientPlayerEntity5 = fakePlayer;
                    Intrinsics.checkNotNull(otherClientPlayerEntity5);
                    otherClientPlayerEntity5.setYaw(positionSnapshot.getYaw());
                    OtherClientPlayerEntity otherClientPlayerEntity6 = fakePlayer;
                    Intrinsics.checkNotNull(otherClientPlayerEntity6);
                    otherClientPlayerEntity6.setPitch(positionSnapshot.getPitch());
                    Intrinsics.checkNotNull(fakePlayer);
                    fakePlayer.headYaw = positionSnapshot.getYaw();
                    OtherClientPlayerEntity otherClientPlayerEntity7 = fakePlayer;
                    Intrinsics.checkNotNull(otherClientPlayerEntity7);
                    otherClientPlayerEntity7.updateTrackedPosition(positionSnapshot.getX(), positionSnapshot.getY(), positionSnapshot.getZ());
                    OtherClientPlayerEntity otherClientPlayerEntity8 = fakePlayer;
                    Intrinsics.checkNotNull(otherClientPlayerEntity8);
                    otherClientPlayerEntity8.updateTrackedPositionAndAngles(new Vec3d(positionSnapshot.getX(), positionSnapshot.getY(), positionSnapshot.getZ()), positionSnapshot.getYaw(), positionSnapshot.getPitch());
                } else {
                    C = 0;
                }
                OtherClientPlayerEntity otherClientPlayerEntity9 = fakePlayer;
                Intrinsics.checkNotNull(otherClientPlayerEntity9);
                if (Intrinsics.areEqual(otherClientPlayerEntity9.getOffHandStack().getItem(), Items.TOTEM_OF_UNDYING)) break block7;
                OtherClientPlayerEntity otherClientPlayerEntity10 = fakePlayer;
                Intrinsics.checkNotNull(otherClientPlayerEntity10);
                otherClientPlayerEntity10.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.TOTEM_OF_UNDYING));
                break block7;
            }
            this.disable(l);
        }
    }

    @Override
    public void onDisable(long l) {
        if (fakePlayer == null) {
            return;
        }
        OtherClientPlayerEntity otherClientPlayerEntity = fakePlayer;
        Intrinsics.checkNotNull(otherClientPlayerEntity);
        otherClientPlayerEntity.setRemoved(Entity.RemovalReason.KILLED);
        OtherClientPlayerEntity otherClientPlayerEntity2 = fakePlayer;
        Intrinsics.checkNotNull(otherClientPlayerEntity2);
        otherClientPlayerEntity2.onRemoved();
        fakePlayer = null;
        recordedPositions.clear();
    }

    private static final Unit _clinit_$lambda$0() {
        U = !U;
        return Unit.INSTANCE;
    }

    private static final boolean settingVisible() {
        return !n;
    }

    private static final Unit _clinit_$lambda$2() {
        n = !n;
        return Unit.INSTANCE;
    }

    private static final boolean settingVisible2() {
        return !U;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        FakePlayer.a = ZkmKeys.a(-9034549554807465080L, -4840536040974324175L, MethodHandles.lookup().lookupClass()).a(228462466825021L);
                        var20 = FakePlayer.a ^ 102903668308766L;
                        FakePlayer.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[16];
                        var16_4 = 0;
                        var15_5 = "Q\u00e9\u0005\u00fe\u00e6\u00bf7\u0086]{\u00fat\u000f\u00a52\u00a7\u00bf\u00a0`s\u00ff\u00b0\u00c8\u00b9\u0010\u0096\u00fc\u00c8p;\u00f5\u00c3\u00c0 W7f\u00ba\u00a6>m(6\u00fb,~\u0019A\u0081\u0000o\u00f4\u0012\u0081r\u008f\u00c0A\u00176\u00b5=\u00a1b\u00d5*\u0004\u00cd\u00e5#\u00c6\u0091P\u0092#W\u00bf\u0012\u0083\u008f\u00b8\u0002 >\u00f0[\u00a1\u0085\u00b5\u00e9\u0081\u0086\u00d0\u00033?\u0017\u009aH\u00ed\u0018\u00c6\u00dat\u00cf\u00b8\u00f0\u00e6\u008b(\u00a9\u00f0\u00ee\u00ec\u00a2\u0018\u00e4f\u00bc\u00ff\u00e8\u00cf\u00be\u00c0v\u0002V\u008b\u00fbx\u0093\u008b\u0082{\u00dd\u00d2\u00ad=\u00c4D\u0018\u00c3y\u00de\u008c\u0085!3\u00fe\u0081\u0014r@\u00f5k\u008d\u0018\u00c4\u0090[\u0099\u00dd\u00c5Kt \u00aa\u00e9}\u0084/\u009fx\u00f7x1\u00e3\u00d0\u00ce\u0012\u0017P\u0081y^dM}\u00b8\u0085t\u00eb\u00f2\u0010H:Z\u00c0@\u00a3\u001b\u00f4\u00fd\u0098\u00e0R\u00c6\u00ec>\u00d4]\u0082\u00b5\u009a\u0084!i\f\u0084\u00c1\u00ca\u0083\"\u00a3\u001d\u0019\fHo,\u000b\u00f6\u001bI?\na\u000b-2$^\u00ce\u0006\u00e8\u008dyx0B\u00b5\u00b4}f\u00af\u00f3`\u00e4s\u00e6\u00db\u008b\u00cd8\u008a\u00a5\u0004\u00bf@\u0017\u00f4\u00df\u00ca|\u0015\u0087\u000b([!e\u001e]\u000f\u0019|\u001fe0\u00be\u00b8&\u00a3\u00139\u00d2\u0004\u00a5\u009e\u0090nX\u00a7'\u00faX\u00a0^/\u001f\u00d6h8\u00b7\u0000\u00e5\u00a3\u00b4\u00f8#\u0010lC\u00f6\u00a4\u00f3?@m\u00d9D\u001f\u0019\u00ea\u001f\u0089\u00dd0\u0016\u0080jb\u0091\u00f0\u00b3\u00ee\u00c8j\u00c6]\u00f0\u000e8\u00ad=\u0086x\u00bbtk\u00fbFU\u00abn\u00fa\u00a6\u00e1\u00b0f\u00ae\u0016#\u00cc\u00a1N\u00a3c\u001d\u00bc\u001b.R\u00a5x\u0088\u0010`\u00eaQ#\u00ad\u00c19\u0087\u008f\u00a5\u00a4\u00bb\u0091z\u0085>@U\u00d7\u00b1>\u00ba\u00fe)\u00e2Ns\u00e3\u00d6lj\u00f7kd\u0018\u00f1]\u008f\u0082\u0010\u00b5V\u00bb\u00fb\u0093\u00fe\u00f7#\u00eb2-\u00e9&(;\u009d\u00a9m&j\u00fa\u001aC\u00d8F@{\u00ed\u00bb9\u00b5\u00c6\u009f\u00c2::l\u0087\u00c6\u00f5\u00ea h\u00b9\u00eb\u00c5\u00ae\u00ea n\u008fG\u00b4r\u0093\u00da\u00e2UG\u0019\u00c9\t<\u00d0*\u0099\u00f3\u00e8\u008b_\u0085\u00b6\u0000r";
                        var17_6 = 501;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = FakePlayer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b9\u00c7\u001c\u00ef\u001e`Y\u00f3'\u0095\u00bb\u0012^x\u00bdj\u00c8\u00d3q&\u00ac4\u00ba^\u00ed=\\\u00cck\u0019T\u00a9\u0010\u00af\u0004W\u00d4m\u0097\u00b4\u0007:\u00d84\u00fc\u001bf,q";
                            var17_6 = 49;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = FakePlayer.zkmDecodeUtf8(var19_9).intern();
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
                FakePlayer.b = var18_3;
                FakePlayer.c = new String[16];
                FakePlayer.k = new HashMap<K, V>(13);
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
                var6_12 = new long[8];
                var3_13 = 0;
                var4_14 = "!\u00b95\u00b9/L\u00b0Y\u00a6\u0096\u0086\u00b7\u00c2|\u008d\u0095\u0001\u008a\u00dbMt\u00e2\u0082\u0084l\u00fc\u00bes\u00d6!\u00b2\u0086\u00c3b{Co\u00ce\u00a8'\u00fd\u0002\u00d1\u0010\u00a9\u00ac\\\u0099";
                var5_15 = 48;
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
                    var4_14 = "}s\u00f0\u00ee\u0088\u00b8\u000e\u00b0{\u0004\u00f2\u0006\u0081ry\u00ef";
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
        FakePlayer.h = var6_12;
        FakePlayer.j = new Integer[8];
        var30_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(FakePlayer.class, "recordButton", "getRecordButton()Lsu/catlean/id;", 0)), Reflection.property1(new PropertyReference1Impl(FakePlayer.class, "button", "getButton()Lsu/catlean/id;", 0)), Reflection.property1(new PropertyReference1Impl(FakePlayer.class, "copyInventory", "getCopyInventory()Z", 0)), Reflection.property1(new PropertyReference1Impl(FakePlayer.class, "name", "getName()Ljava/lang/String;", 0))};
        FakePlayer.delegatedProperties = var30_20;
        FakePlayer.INSTANCE = new FakePlayer();
        FakePlayer.recordButton$delegate = SettingFactory.buttonSetting$default(FakePlayer.INSTANCE, "record-/-stop", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)(), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        FakePlayer.button$delegate = SettingFactory.buttonSetting$default(FakePlayer.INSTANCE, "play-/-stop", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$2(), ()Lkotlin/Unit;)(), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        FakePlayer.copyInventory$delegate = SettingFactory.booleanSetting$default((SettingContainer)FakePlayer.INSTANCE, "copy-inventory", true, null, null, 12, null);
        FakePlayer.name$delegate = SettingFactory.textSetting$default(FakePlayer.INSTANCE, "name", "OLD_GOD", null, null, 12, null);
        FakePlayer.recordedPositions = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5474;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/e2", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = b[n2].getBytes("ISO-8859-1");
            FakePlayer.c[n2] = FakePlayer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FakePlayer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xB64;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
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
                throw new RuntimeException("su/catlean/e2", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FakePlayer.j[n2] = n3;
        }
        return j[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FakePlayer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/e2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FakePlayer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FakePlayer.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

