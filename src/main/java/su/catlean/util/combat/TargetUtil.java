/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.Event;
import su.catlean.api.event.events.network.PlayerEntityDeathEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.module.misc.Notifier;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.combat.TargetUtil$1;
import su.catlean.util.combat.TargetUtil$WhenMappings;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TargetUtil {
    @NotNull
    public static final TargetUtil INSTANCE;
    @NotNull
    private static final Map C;
    @NotNull
    private static final Map h;
    private static boolean T;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long i;

    private TargetUtil() {
    }

    @Flow(priority=10)
    private final void onReceivePacket(ReceivePacket receivePacket) {
        Packet<?> packet = receivePacket.getPacket();
        if (packet instanceof EntityStatusS2CPacket && ((EntityStatusS2CPacket)packet).getStatus() == 35) {
            Object object = ((EntityStatusS2CPacket)packet).getEntity(Globals.getWorld());
            PlayerEntity playerEntity = object instanceof PlayerEntity ? (PlayerEntity)object : null;
            if (playerEntity == null) {
                return;
            }
            PlayerEntity playerEntity2 = playerEntity;
            if (Intrinsics.areEqual(playerEntity2, Globals.getMc().player)) {
                return;
            }
            Map map = C;
            object = playerEntity2.getName().getString();
            String string = playerEntity2.getName().getString();
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Integer n = ((Number)C.getOrDefault(string, 0)).intValue() + 1;
            map.put(object, n);
            if (Notifier.INSTANCE.getEnabled()) {
                String string2 = playerEntity2.getName().getString();
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = playerEntity2.getName().getString();
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                Notifier.INSTANCE.onTotemPop(string2, ((Number)C.getOrDefault(string3, 0)).intValue());
            }
        }
    }

    @Flow(priority=10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Object object;
        Object object2;
        Object object3;
        List<AbstractClientPlayerEntity> list = Globals.getWorld().getPlayers();
        Intrinsics.checkNotNullExpressionValue(list, "players(...)");
        Iterable iterable = list;
        Iterable iterable2 = iterable;
        Iterator iterator22 = new ArrayList();
        Object object4 = iterable2.iterator();
        while (object4.hasNext()) {
            object3 = object4.next();
            object2 = (AbstractClientPlayerEntity)object3;
            if (!((((LivingEntity)object2).getHealth() <= 0.0f || !((LivingEntity)object2).isAlive() || ((LivingEntity)object2).isDead()) && !Intrinsics.areEqual(object2, Globals.getPlayer()) && !h.containsKey(((PlayerEntity)object2).getName().getString()))) continue;
            iterator22.add(object3);
        }
        iterable = (List)((Object)iterator22);
        for (Iterator iterator22 : iterable) {
            object = (AbstractClientPlayerEntity)((Object)iterator22);
            object2 = object3 = PlayerEntityDeathEvent.INSTANCE;
            ((PlayerEntityDeathEvent)object2).setPlayer((PlayerEntity)object);
            String string = ((PlayerEntity)object).getName().getString();
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            ((PlayerEntityDeathEvent)object2).setTotemsPopped(((Number)C.getOrDefault(string, 0)).intValue());
            ((Event)object3).call();
            h.put(((PlayerEntity)object).getName().getString(), Globals.now());
            C.remove(((PlayerEntity)object).getName().getString());
        }
        iterable = new ArrayList();
        Object object5 = h;
        iterator22 = object5.entrySet().iterator();
        while (iterator22.hasNext()) {
            object4 = object = (Map.Entry)iterator22.next();
            object2 = (String)object4.getKey();
            long l = ((Number)object4.getValue()).longValue();
            if (Globals.now() - l <= i) continue;
            iterable.add(object2);
        }
        object5 = iterable;
        iterator22 = object5.iterator();
        while (iterator22.hasNext()) {
            object = iterator22.next();
            object4 = (String)object;
            h.remove(object4);
        }
    }

    public final int getPops(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "entity");
        String string = playerEntity.getName().getString();
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return this.getPopsByName(string);
    }

    public final int getPopsByName(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        Integer n = (Integer)C.get(string);
        return n != null ? n : 0;
    }

    @Nullable
    public final PlayerEntity findTarget(long l, float f, @NotNull AutoCrystalTargetSortMode autoCrystalTargetSortMode, boolean bl, boolean bl2) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x6B6EC7BF127BL;
        long l4 = l2 ^ 0x72503DAD4AD5L;
        long l5 = l2 ^ 0x5620CFC035CFL;
        Intrinsics.checkNotNullParameter((Object)autoCrystalTargetSortMode, "targetBy");
        return switch (TargetUtil$WhenMappings.a[autoCrystalTargetSortMode.ordinal()]) {
            case 1 -> this.getClosest(f, bl, bl2, l5);
            case 2 -> this.getClosestFov(l3, f, bl, bl2);
            case 3 -> this.getLowestHealth(f, l4, bl, bl2);
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    public static PlayerEntity Y(char c, TargetUtil targetUtil, float f, AutoCrystalTargetSortMode autoCrystalTargetSortMode, boolean bl, boolean bl2, int n, int n2, char c2, Object object) {
        long l = ((long)c << 48 | (long)n2 << 32 >>> 16 | (long)c2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x18C46552EB41L;
        if ((n & 4) != 0) {
            bl = false;
        }
        if ((n & 8) != 0) {
            bl2 = false;
        }
        return targetUtil.findTarget(l2, f, autoCrystalTargetSortMode, bl, bl2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     */
    private final List getValidTargets(int var1_1, byte var2_2, float var3_3, int var4_4, boolean var5_5, boolean var6_6) {
        block5: {
            var7_7 = ((long)var1_1 << 32 | (long)var2_2 << 56 >>> 32 | (long)var4_4 << 40 >>> 40) ^ TargetUtil.a;
            v0 = ZkmReferences.zkmBootstrap("\u00c5", (long)-433655422762304814L, (long)var7_7);
            v1 = Globals.getWorld().getPlayers();
            Intrinsics.checkNotNullExpressionValue(v1, (String)TargetUtil.zkmBootstrap("x", (int)20358, (long)(3721518490312288116L ^ var7_7)));
            var16_8 = v1;
            var15_9 = v0;
            var18_10 = var16_8;
            var19_11 = new ArrayList<E>();
            for (T var22_13 : var18_10) {
                block17: {
                    block16: {
                        block14: {
                            block15: {
                                block12: {
                                    block13: {
                                        block10: {
                                            block11: {
                                                block8: {
                                                    block9: {
                                                        block6: {
                                                            block7: {
                                                                var23_14 = (AbstractClientPlayerEntity)var22_13;
                                                                if (var1_1 <= 0) break block5;
                                                                v2 = var23_14.isDead();
                                                                if (var15_9 == null) break block5;
                                                                v3 = var15_9;
                                                                if (var2_2 > 0) break block6;
                                                                if (v3 == null) break block7;
                                                                if (v2) ** GOTO lbl-1000
                                                                Intrinsics.checkNotNull(var23_14);
                                                                v4 = Friends.getIsHomie(var23_14);
                                                            }
                                                            v3 = var15_9;
                                                        }
                                                        if (var1_1 < 0) break block8;
                                                        if (v3 == null) break block9;
                                                        if (v4 != 0) ** GOTO lbl-1000
                                                        v4 = Intrinsics.areEqual(var23_14, Globals.getPlayer());
                                                    }
                                                    v3 = var15_9;
                                                }
                                                if (var1_1 < 0) break block10;
                                                if (v3 == null) break block11;
                                                if (v4 != 0) ** GOTO lbl-1000
                                                cfr_temp_0 = Globals.getPlayer().distanceTo(var23_14) - var3_3;
                                                v4 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                            }
                                            v3 = var15_9;
                                        }
                                        if (var2_2 > 0) break block12;
                                        if (v3 == null) break block13;
                                        if (v4 >= 0) ** GOTO lbl-1000
                                        v4 = (int)var5_5;
                                    }
                                    v3 = var15_9;
                                }
                                if (v3 == null) break block14;
                                if (v4 == 0) break block15;
                                v4 = var23_14.getArmor();
                                v5 = var15_9;
                                if (var2_2 > 0) break block16;
                                if (v5 == null) break block14;
                                if (v4 <= 0) ** GOTO lbl-1000
                            }
                            v4 = (int)var6_6;
                        }
                        v5 = var15_9;
                    }
                    if (v5 == null) break block17;
                    if (v4 == 0) ** GOTO lbl-1000
                    v4 = (int)var23_14.isCreative();
                    if (var15_9 != null) {
                        ** if (v4 != 0) goto lbl-1000
                    }
                    break block17;
lbl-1000:
                    // 2 sources

                    {
                        v4 = 1;
                        ** GOTO lbl66
                    }
lbl-1000:
                    // 6 sources

                    {
                        v4 = 0;
                    }
                }
                if (v4 == 0) continue;
                var19_11.add(var22_13);
                if (var15_9 != null) continue;
            }
            var16_8 = (List)var19_11;
            if (var4_4 > 0) {
                return CollectionsKt.sortedWith(var16_8, new TargetUtil$1());
            }
        }
        return CollectionsKt.sortedWith(var16_8, new TargetUtil$1());
    }

    /*
     * WARNING - void declaration
     */
    static List getValidTargets$default(TargetUtil targetUtil, float f, boolean bl, boolean bl2, int n, int n2, Object object, int n3) {
        boolean bl3;
        int n4;
        int n5;
        int n6;
        block6: {
            boolean bl4;
            block5: {
                void var4_5;
                void var7_8;
                void var5_6;
                long l = ((long)var5_6 << 32 | (long)var7_8 << 32 >>> 32) ^ a;
                long l2 = l ^ 0x492469CF514DL;
                n6 = (int)(l2 >>> 32);
                n5 = (int)(l2 << 32 >>> 56);
                n4 = (int)(l2 << 40 >>> 40);
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-496408736275580363L, (long)l);
                bl4 = var4_5 & 2;
                if (callSite != null) {
                    if (bl4) {
                        bl = false;
                    }
                    bl4 = var4_5 & 4;
                }
                if (callSite == null) break block5;
                if (!bl4) break block6;
                bl4 = false;
            }
            bl3 = bl4;
        }
        return targetUtil.getValidTargets(n6, (byte)n5, f, n4, bl, bl3);
    }

    @Nullable
    public final PlayerEntity getLowestHealth(float f, long l, boolean bl, boolean bl2) {
        Object v1;
        long l2 = (l = a ^ l) ^ 0x585431AED3CDL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 56);
        int n3 = (int)(l2 << 40 >>> 40);
        Iterable iterable = this.getValidTargets(n, (byte)n2, f, n3, bl, bl2);
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            v1 = null;
        } else {
            Object t = iterator2.next();
            if (!iterator2.hasNext()) {
                v1 = t;
            } else {
                PlayerEntity playerEntity = (PlayerEntity)t;
                float f2 = Globals.getPlayer().distanceTo(playerEntity);
                do {
                    Object t2 = iterator2.next();
                    PlayerEntity playerEntity2 = (PlayerEntity)t2;
                    float f3 = Globals.getPlayer().distanceTo(playerEntity2);
                    if (Float.compare(f2, f3) <= 0) continue;
                    t = t2;
                    f2 = f3;
                } while (iterator2.hasNext());
                v1 = t;
            }
        }
        return v1;
    }

    /*
     * WARNING - void declaration
     */
    public static PlayerEntity getLowestHealth$default(TargetUtil targetUtil, float f, boolean bl, boolean bl2, int n, Object object, int n2, byte by, int n3) {
        boolean bl3;
        long l;
        block6: {
            boolean bl4;
            block5: {
                void var4_5;
                void var8_9;
                void var7_8;
                void var6_7;
                long l2 = ((long)var6_7 << 32 | (long)var7_8 << 56 >>> 32 | (long)var8_9 << 40 >>> 40) ^ a;
                l = l2 ^ 0x15C2750C1F89L;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3872837459930150550L, (long)l2);
                bl4 = var4_5 & 2;
                if (callSite != null) {
                    if (bl4) {
                        bl = false;
                    }
                    bl4 = var4_5 & 4;
                }
                if (callSite == null) break block5;
                if (!bl4) break block6;
                bl4 = false;
            }
            bl3 = bl4;
        }
        return targetUtil.getLowestHealth(f, l, bl, bl3);
    }

    @Nullable
    public final PlayerEntity getClosestFov(long l, float f, boolean bl, boolean bl2) {
        Object v1;
        long l2 = (l = a ^ l) ^ 0x416ACBBC8B63L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 56);
        int n3 = (int)(l2 << 40 >>> 40);
        Iterable iterable = this.getValidTargets(n, (byte)n2, f, n3, bl, bl2);
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            v1 = null;
        } else {
            Object t = iterator2.next();
            if (!iterator2.hasNext()) {
                v1 = t;
            } else {
                PlayerEntity playerEntity = (PlayerEntity)t;
                float f2 = playerEntity.getHealth() + playerEntity.getAbsorptionAmount();
                do {
                    Object t2;
                    PlayerEntity playerEntity2;
                    float f3;
                    if (Float.compare(f2, f3 = (playerEntity2 = (PlayerEntity)(t2 = iterator2.next())).getHealth() + playerEntity2.getAbsorptionAmount()) <= 0) continue;
                    t = t2;
                    f2 = f3;
                } while (iterator2.hasNext());
                v1 = t;
            }
        }
        return v1;
    }

    /*
     * WARNING - void declaration
     */
    public static PlayerEntity getClosestFov$default(TargetUtil targetUtil, long l, float f, boolean bl, boolean bl2, int n, Object object) {
        boolean bl3;
        long l2;
        block6: {
            boolean bl4;
            block5: {
                void var6_6;
                l2 = (l = a ^ l) ^ 0x13261C7CF35BL;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-9096414744430722326L, (long)l);
                bl4 = var6_6 & 2;
                if (callSite != null) {
                    if (bl4) {
                        bl = false;
                    }
                    bl4 = var6_6 & 4;
                }
                if (callSite == null) break block5;
                if (!bl4) break block6;
                bl4 = false;
            }
            bl3 = bl4;
        }
        return targetUtil.getClosestFov(l2, f, bl, bl3);
    }

    @Nullable
    public final PlayerEntity getClosest(float f, boolean bl, boolean bl2, long l) {
        Object v1;
        long l2 = (l = a ^ l) ^ 0x7C24C3C3ACD7L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 56);
        int n3 = (int)(l2 << 40 >>> 40);
        Iterable iterable = this.getValidTargets(n, (byte)n2, f, n3, bl, bl2);
        Iterator iterator2 = iterable.iterator();
        if (!iterator2.hasNext()) {
            v1 = null;
        } else {
            Object t = iterator2.next();
            if (!iterator2.hasNext()) {
                v1 = t;
            } else {
                PlayerEntity playerEntity = (PlayerEntity)t;
                float f2 = INSTANCE.getFovTo(playerEntity);
                do {
                    Object t2;
                    PlayerEntity playerEntity2;
                    float f3;
                    if (Float.compare(f2, f3 = INSTANCE.getFovTo(playerEntity2 = (PlayerEntity)(t2 = iterator2.next()))) <= 0) continue;
                    t = t2;
                    f2 = f3;
                } while (iterator2.hasNext());
                v1 = t;
            }
        }
        return v1;
    }

    /*
     * WARNING - void declaration
     */
    public static PlayerEntity getClosest$default(TargetUtil targetUtil, long l, float f, boolean bl, boolean bl2, int n, Object object) {
        boolean bl3;
        long l2;
        block6: {
            boolean bl4;
            block5: {
                void var6_6;
                l2 = (l = a ^ l) ^ 0x681EB28A5416L;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)88467301398823443L, (long)l);
                bl4 = var6_6 & 2;
                if (callSite != null) {
                    if (bl4) {
                        bl = false;
                    }
                    bl4 = var6_6 & 4;
                }
                if (callSite == null) break block5;
                if (!bl4) break block6;
                bl4 = false;
            }
            bl3 = bl4;
        }
        return targetUtil.getClosest(f, bl, bl3, l2);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Nullable
    public final PlayerEntity findEntity(float var1_1, int var2_2, int var3_3, short var4_4, float var5_5) {
        block11: {
            block12: {
                block13: {
                    block9: {
                        block10: {
                            block8: {
                                var6_6 = ((long)var2_2 << 32 | (long)var3_3 << 48 >>> 32 | (long)var4_4 << 48 >>> 48) ^ TargetUtil.a;
                                v0 = var6_6 ^ 102978271319188L;
                                var8_7 = (int)(v0 >>> 32);
                                var9_8 = (int)(v0 << 32 >>> 32);
                                var13_9 = TargetUtil.getValidTargets$default(this, var1_1, false, false, (int)TargetUtil.zkmBootstrap2("j", (int)5752, (long)(7892588533288698566L ^ var6_6)), var8_7, null, var9_8);
                                var15_10 = var13_9;
                                var16_11 = new ArrayList<E>();
                                var12_12 = ZkmReferences.zkmBootstrap("\u00c5", (long)-4890262421067530485L, (long)var6_6);
                                var18_13 /* !! */  = var15_10.iterator();
                                while (var18_13 /* !! */ .hasNext()) {
                                    var19_14 /* !! */  = var18_13 /* !! */ .next();
                                    var20_16 = (PlayerEntity)var19_14 /* !! */ ;
                                    if (var2_2 >= 0) {
                                        cfr_temp_0 = TargetUtil.INSTANCE.getFovTo(var20_16) - var5_5;
                                        v1 = cfr_temp_0 == 0.0f ? 0 : (cfr_temp_0 < 0.0f ? -1 : 1);
                                        if (var12_12 != null) {
                                            if (var12_12 != null) {
                                                v2 = v1 < 0;
                                            }
                                            if (!v2) continue;
                                            var16_11.add(var19_14 /* !! */ );
                                            if (var12_12 != null) continue;
                                        }
                                    }
                                    break block8;
                                }
                                var13_9 = (List)var16_11;
                                if (var4_4 < 0) {
                                    v1 = 0;
                                }
                            }
                            var15_10 = var13_9.iterator();
                            v3 = var15_10.hasNext();
                            if (var2_2 < 0 || var12_12 == null) break block9;
                            if (v3) break block10;
                            v4 = null;
                            break block11;
                        }
                        var16_11 = var15_10.next();
                        v5 = var15_10;
                        if (var12_12 == null) break block12;
                        v3 = v5.hasNext();
                    }
                    if (v3) break block13;
                    v4 = var16_11;
                    break block11;
                }
                v5 = var16_11;
            }
            var17_17 = (PlayerEntity)v5;
            var17_18 = TargetUtil.INSTANCE.getFovTo(var17_17);
            block1: while (true) {
                if (Float.compare(var17_18, var19_15 = TargetUtil.INSTANCE.getFovTo((LivingEntity)(var19_14 /* !! */  = (PlayerEntity)(var18_13 /* !! */  = var15_10.next())))) <= 0) ** GOTO lbl57
                v6 = var18_13 /* !! */ ;
                block2: while (true) {
                    var16_11 = v6;
                    do {
                        var17_18 = var19_15;
lbl57:
                        // 2 sources

                        if (var15_10.hasNext()) continue block1;
                        v6 = var12_12;
                        if (var2_2 < 0) continue block2;
                    } while (v6 == null);
                    break;
                }
                break;
            }
            v4 = var16_11;
        }
        return (PlayerEntity)v4;
    }

    @Nullable
    public final PlayerEntity findEntityTarget(float f, long l, @NotNull AutoCrystalTargetSortMode autoCrystalTargetSortMode, @NotNull Function1 function1) {
        long l2 = l = a ^ l;
        long l3 = l2 ^ 0x381A4A92A85AL;
        long l4 = l2 ^ 0x47188F156B2EL;
        long l5 = l2 ^ 0x10C3A70162BFL;
        Intrinsics.checkNotNullParameter((Object)autoCrystalTargetSortMode, (String)((Object)TargetUtil.zkmBootstrap("x", (int)25519, (long)(0x35957DDF91024FD5L ^ l))));
        Intrinsics.checkNotNullParameter(function1, (String)((Object)TargetUtil.zkmBootstrap("x", (int)1122, (long)(0x185F46B542AE2819L ^ l))));
        return switch (TargetUtil$WhenMappings.a[autoCrystalTargetSortMode.ordinal()]) {
            case 1 -> this.getEntityClosestFov(f, l5, function1);
            case 2 -> this.getEntityClosest(f, l3, function1);
            case 3 -> this.getEntityLowestHealth(f, l4, function1);
            default -> throw new NoWhenBranchMatchedException();
        };
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Nullable
    public final PlayerEntity getEntityLowestHealth(float var1_1, long var2_2, @NotNull Function1 var4_3) {
        block11: {
            block12: {
                block13: {
                    block9: {
                        block10: {
                            block8: {
                                v0 = (var2_2 = TargetUtil.a ^ var2_2) ^ 7691334135623L;
                                var5_4 = (int)(v0 >>> 32);
                                var6_5 = (int)(v0 << 32 >>> 32);
                                Intrinsics.checkNotNullParameter(var4_3, (String)TargetUtil.zkmBootstrap("x", (int)7247, (long)(2090220744603536061L ^ var2_2)));
                                var10_6 = TargetUtil.getValidTargets$default(this, var1_1, false, false, (int)TargetUtil.zkmBootstrap2("j", (int)4118, (long)(5489780597599092602L ^ var2_2)), var5_4, null, var6_5);
                                var12_7 = var10_6;
                                var13_8 = new ArrayList<E>();
                                var15_9 /* !! */  = var12_7.iterator();
                                var9_10 = ZkmReferences.zkmBootstrap("\u00c5", (long)-8651107663462611752L, (long)var2_2);
                                while (var15_9 /* !! */ .hasNext()) {
                                    var16_11 /* !! */  = var15_9 /* !! */ .next();
                                    if (var2_2 >= 0L) {
                                        v1 = (Boolean)var4_3.invoke(var16_11 /* !! */ );
                                        if (var9_10 != null) {
                                            if (var9_10 != null) {
                                                if (!v1) continue;
                                                var13_8.add(var16_11 /* !! */ );
                                            }
                                            if (var9_10 != null) continue;
                                        }
                                    }
                                    break block8;
                                }
                                var10_6 = (List)var13_8;
                                if (var2_2 > 0L) {
                                    v1 = false;
                                }
                            }
                            var12_7 = var10_6.iterator();
                            v2 = var12_7.hasNext();
                            if (var2_2 < 0L || var9_10 == null) break block9;
                            if (v2) break block10;
                            v3 = null;
                            break block11;
                        }
                        var13_8 = var12_7.next();
                        v4 = var12_7;
                        if (var9_10 == null) break block12;
                        v2 = v4.hasNext();
                    }
                    if (v2) break block13;
                    v3 = var13_8;
                    break block11;
                }
                v4 = var13_8;
            }
            var14_13 = (PlayerEntity)v4;
            var14_14 = Globals.getPlayer().distanceTo(var14_13);
            block1: while (true) {
                var15_9 /* !! */  = var12_7.next();
                var16_11 /* !! */  = (PlayerEntity)var15_9 /* !! */ ;
                var16_12 = Globals.getPlayer().distanceTo((Entity)var16_11 /* !! */ );
                if (Float.compare(var14_14, var16_12) <= 0) ** GOTO lbl57
                v5 = var15_9 /* !! */ ;
                block2: while (true) {
                    var13_8 = v5;
                    do {
                        var14_14 = var16_12;
lbl57:
                        // 2 sources

                        if (var12_7.hasNext()) continue block1;
                        v5 = var9_10;
                        if (var2_2 <= 0L) continue block2;
                    } while (v5 == null);
                    break;
                }
                break;
            }
            v3 = var13_8;
        }
        return (PlayerEntity)v3;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Nullable
    public final PlayerEntity getEntityClosest(float var1_1, long var2_2, @NotNull Function1 var4_3) {
        block11: {
            block12: {
                block13: {
                    block9: {
                        block10: {
                            block8: {
                                v0 = (var2_2 = TargetUtil.a ^ var2_2) ^ 134123284524083L;
                                var5_4 = (int)(v0 >>> 32);
                                var6_5 = (int)(v0 << 32 >>> 32);
                                Intrinsics.checkNotNullParameter(var4_3, (String)TargetUtil.zkmBootstrap("x", (int)1122, (long)(1756133210536006121L ^ var2_2)));
                                var8_6 = TargetUtil.getValidTargets$default(this, var1_1, false, false, (int)TargetUtil.zkmBootstrap2("j", (int)5752, (long)(7892627754461198945L ^ var2_2)), var5_4, null, var6_5);
                                var7_7 = ZkmReferences.zkmBootstrap("\u00c5", (long)4937467365054023596L, (long)var2_2);
                                var10_8 = var8_6;
                                var11_9 = new ArrayList<E>();
                                var13_10 /* !! */  = var10_8.iterator();
                                while (var13_10 /* !! */ .hasNext()) {
                                    var14_11 = var13_10 /* !! */ .next();
                                    if (var2_2 > 0L) {
                                        v1 = (Boolean)var4_3.invoke(var14_11);
                                        if (var7_7 != null) {
                                            if (var7_7 != null) {
                                                if (!v1) continue;
                                                var11_9.add(var14_11);
                                            }
                                            if (var7_7 != null) continue;
                                        }
                                    }
                                    break block8;
                                }
                                var8_6 = (List)var11_9;
                                if (var2_2 > 0L) {
                                    v1 = false;
                                }
                            }
                            var10_8 = var8_6.iterator();
                            v2 = var10_8.hasNext();
                            if (var2_2 <= 0L || var7_7 == null) break block9;
                            if (v2) break block10;
                            v3 = null;
                            break block11;
                        }
                        var11_9 = var10_8.next();
                        v4 = var10_8;
                        if (var7_7 == null) break block12;
                        v2 = v4.hasNext();
                    }
                    if (v2) break block13;
                    v3 = var11_9;
                    break block11;
                }
                v4 = var11_9;
            }
            var12_13 = (PlayerEntity)v4;
            var12_14 = var12_13.getHealth() + var12_13.getAbsorptionAmount();
            block1: while (true) {
                if (Float.compare(var12_14, var14_12 = (var14_11 = (PlayerEntity)(var13_10 /* !! */  = var10_8.next())).getHealth() + var14_11.getAbsorptionAmount()) <= 0) ** GOTO lbl54
                v5 = var13_10 /* !! */ ;
                block2: while (true) {
                    var11_9 = v5;
                    do {
                        var12_14 = var14_12;
lbl54:
                        // 2 sources

                        if (var10_8.hasNext()) continue block1;
                        v5 = var7_7;
                        if (var2_2 < 0L) continue block2;
                    } while (v5 == null);
                    break;
                }
                break;
            }
            v3 = var11_9;
        }
        return (PlayerEntity)v3;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Nullable
    public final PlayerEntity getEntityClosestFov(float var1_1, long var2_2, @NotNull Function1 var4_3) {
        block11: {
            block12: {
                block13: {
                    block9: {
                        block10: {
                            block8: {
                                v0 = (var2_2 = TargetUtil.a ^ var2_2) ^ 89223368091350L;
                                var5_4 = (int)(v0 >>> 32);
                                var6_5 = (int)(v0 << 32 >>> 32);
                                v1 = ZkmReferences.zkmBootstrap("\u00c5", (long)-8187472992302609079L, (long)var2_2);
                                Intrinsics.checkNotNullParameter(var4_3, (String)TargetUtil.zkmBootstrap("x", (int)1122, (long)(1756158664350112524L ^ var2_2)));
                                var9_6 = v1;
                                var10_7 = TargetUtil.getValidTargets$default(this, var1_1, false, false, (int)TargetUtil.zkmBootstrap2("j", (int)5752, (long)(7892584708882542724L ^ var2_2)), var5_4, null, var6_5);
                                var12_8 = var10_7;
                                var13_9 = new ArrayList<E>();
                                var15_10 /* !! */  = var12_8.iterator();
                                while (var15_10 /* !! */ .hasNext()) {
                                    var16_11 /* !! */  = var15_10 /* !! */ .next();
                                    if (var2_2 > 0L) {
                                        v2 = (Boolean)var4_3.invoke(var16_11 /* !! */ );
                                        if (var9_6 != null) {
                                            if (var9_6 != null) {
                                                if (!v2) continue;
                                                var13_9.add(var16_11 /* !! */ );
                                            }
                                            if (var9_6 != null) continue;
                                        }
                                    }
                                    break block8;
                                }
                                var10_7 = (List)var13_9;
                                if (var2_2 > 0L) {
                                    v2 = false;
                                }
                            }
                            var12_8 = var10_7.iterator();
                            v3 = var12_8.hasNext();
                            if (var2_2 < 0L || var9_6 == null) break block9;
                            if (v3) break block10;
                            v4 = null;
                            break block11;
                        }
                        var13_9 = var12_8.next();
                        v5 = var12_8;
                        if (var9_6 == null) break block12;
                        v3 = v5.hasNext();
                    }
                    if (v3) break block13;
                    v4 = var13_9;
                    break block11;
                }
                v5 = var13_9;
            }
            var14_13 = (PlayerEntity)v5;
            var14_14 = TargetUtil.INSTANCE.getFovTo(var14_13);
            block1: while (true) {
                if (Float.compare(var14_14, var16_12 = TargetUtil.INSTANCE.getFovTo((LivingEntity)(var16_11 /* !! */  = (PlayerEntity)(var15_10 /* !! */  = var12_8.next())))) <= 0) ** GOTO lbl55
                v6 = var15_10 /* !! */ ;
                block2: while (true) {
                    var13_9 = v6;
                    do {
                        var14_14 = var16_12;
lbl55:
                        // 2 sources

                        if (var12_8.hasNext()) continue block1;
                        v6 = var9_6;
                        if (var2_2 < 0L) continue block2;
                    } while (v6 == null);
                    break;
                }
                break;
            }
            v4 = var13_9;
        }
        return (PlayerEntity)v4;
    }

    private final float getFovTo(LivingEntity livingEntity) {
        return Math.abs((float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(livingEntity.getZ() - Globals.getPlayer().getZ(), livingEntity.getX() - Globals.getPlayer().getX())) - 90.0) - MathHelper.wrapDegrees(Globals.getPlayer().getYaw()));
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            TargetUtil.a = ZkmKeys.a(7055298509209557053L, -4486721008109769127L, MethodHandles.lookup().lookupClass()).a(82006569698813L);
                            var25 = TargetUtil.a ^ 50533257735223L;
                            TargetUtil.d = new HashMap<K, V>(13);
                            TargetUtil.zkmSetFlag(true);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[10];
                            var21_4 = 0;
                            var20_5 = "G\u0086qq\u00fe\u00ea\u00ac\u0088\u0096\u00e47\u00f1\u001f\u00b4\u0080.\u00ff\u00e8\u0007p+\u0007\u00e5ToB\u000b\u009c\u00a1\u009cC\u00ef Z\u009c\u00ee\u00bb\u00e7\u0085)\u001b[\u00baFb\u0080_\u0000\u0091\u001e'q\u00bf\u0084\u0004S\u008fK*\u00de[\u001bKC\u00b2\u0018D\u0098d\u00bc\u00a59\t\u00ff\u00a5\u00ef69\u00ec\u00fe{2B\u00d2\u0091\u000fF\f\u009e\u0082\u0010\u00c1\u00a8n\u00df\u0010\u00b8\b\u0002\u00ffDrI\u00b5\u00f27M \u00ea\u00df\u00f7Z\u0080<]\u0094\u0092\u001a\u00cc\u0010pua=\u0099\u00c6\u00a4<\u0080\f\u001a\u008b\u00d7\u00f2\u00b5mQw|M\u0018 o\u0013\u0018\n\u00a7\u0080\u00db%\u009e\u00b3\u00cd7\u009e7!\u00f0\u00cf4\u00b2\u00d2\u00bf_\u0003 U!\u00a5o\u00ae\u00af\u00988H7\u0088\u0093\u0086t\u001b\t\u00c8\u0010S\r\u008a\u00f8E\u00cb/\u0016\u00f0\u0084q!n\u0090\u0010\u00f5\u00ceW\u00db\u0080|\u00e7\u00a1\u001b\u00cb\u00f7\u00b1\u0006\u00da\u0019\r";
                            var22_6 = 215;
                            var19_7 = 32;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = TargetUtil.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u009f\u0001u\u0086\u00eed\u0087\u00af\u00a7w\u0018\u0011\u00de \u00c40\u0011\rG\u00f0\u00f3[\u0005D\u00f73\u0091x\u00d3\u00d3<H \u00a4\u008fk\u00af\u009f\u00b7\u00bf\u0083 \u00e8\u0097*\u009c\u00e1\u00de\u00fe\u00a5\u0004\u00de\u0087\u00b1_\u00fdwD\u00d9Aq\u001dz/\u001b";
                                var22_6 = 65;
                                var19_7 = 32;
                                var18_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = TargetUtil.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
                        switch (v5) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl52:
                            // 1 sources

                            ** continue;
                        }
                    }
                    TargetUtil.b = var23_3;
                    TargetUtil.c = new String[10];
                    TargetUtil.g = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[4];
                    var8_13 = 0;
                    var9_14 = "\u00bf9m\u00ae\u00ce&#\u0094\"\u0018#\u0081g8Z\u00c0";
                    var10_15 = 16;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u00d8\u00d7\u00c8\u0017\u00ce\u00cdt\u0007\u00ba\u0011\u0015\u00f7\u0090@\u00a9&";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl92:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                switch (v13) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl105:
                    // 1 sources

                    ** continue;
                }
            }
            TargetUtil.e = var11_12;
            TargetUtil.f = new Integer[4];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{111, 94, 101, 82, -103, -117, -13, -51});
        ** while (true)
        TargetUtil.i = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
        TargetUtil.INSTANCE = new TargetUtil();
        TargetUtil.C = new LinkedHashMap<K, V>();
        TargetUtil.h = new LinkedHashMap<K, V>();
    }

    public static void zkmSetFlag(boolean bl) {
        T = bl;
    }

    public static boolean zkmGetFlag() {
        return T;
    }

    public static boolean R() {
        return false;
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x11F4;
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
                throw new RuntimeException("su/catlean/dx", exception);
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
            TargetUtil.c[n2] = TargetUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TargetUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2860;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/dx", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TargetUtil.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TargetUtil.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/dx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TargetUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TargetUtil.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

