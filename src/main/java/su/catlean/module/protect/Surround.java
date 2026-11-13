/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.network.AfterReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.world.WorldUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.RotatingModule;
import su.catlean.module.protect.Surround$1;
import su.catlean.module.protect.Surround$EntriesMappings;
import su.catlean.module.protect.Surround$onAfterReceivePacket$1;
import su.catlean.module.protect.SurroundCenterMode;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketAccess;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.ServerInfo;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class Surround
extends RotatingModule {
    @NotNull
    public static final Surround INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting yDisable$delegate;
    @NotNull
    private static final BooleanSetting async$delegate;
    @NotNull
    private static final IntSetting asyncDelay$delegate;
    @NotNull
    private static final BlockListSetting selectedBlocks$delegate;
    @NotNull
    private static final ModeSetting center$delegate;
    @NotNull
    private static final BooleanSetting forceCenter$delegate;
    private static boolean w;
    @NotNull
    private static Timer t;
    @NotNull
    private static Timer F;
    private static final long h;
    private static final String[] m;
    private static final String[] o;
    private static final Map I;
    private static final long[] W;
    private static final Integer[] ob;
    private static final Map pb;

    private Surround() {
        super("surround", Categories.getProtect(), 200, false);
    }

    private final boolean getYDisable() {
        return (Boolean)yDisable$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getAsync() {
        return (Boolean)async$delegate.getValue(this, delegatedProperties[1]);
    }

    private final int getAsyncDelay() {
        return ((Number)asyncDelay$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    private final BlockListValue getSelectedBlocks() {
        return (BlockListValue)selectedBlocks$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setSelectedBlocks(BlockListValue blockListValue) {
        selectedBlocks$delegate.setValue(this, delegatedProperties[3], blockListValue);
    }

    private final SurroundCenterMode getCenter() {
        return (SurroundCenterMode)((Object)center$delegate.getValue(this, delegatedProperties[4]));
    }

    private final void setCenter(SurroundCenterMode surroundCenterMode) {
        center$delegate.setValue(this, delegatedProperties[4], (Object)surroundCenterMode);
    }

    private final boolean getForceCenter() {
        return (Boolean)forceCenter$delegate.getValue(this, delegatedProperties[5]);
    }

    private final void setForceCenter(boolean bl) {
        forceCenter$delegate.setValue(this, delegatedProperties[5], bl);
    }

    @Override
    public void onEnable(long l) {
        if (this.getCenter() != SurroundCenterMode.OFF) {
            Entity entity = Globals.getPlayer();
            BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            BlockPos blockPos2 = blockPos;
            ClosedFloatingPointRange<Double> closedFloatingPointRange = RangesKt.rangeTo((double)blockPos2.getX() + 0.301, (double)blockPos2.getX() + 0.699);
            ClosedFloatingPointRange<Double> closedFloatingPointRange2 = RangesKt.rangeTo((double)blockPos2.getZ() + 0.301, (double)blockPos2.getZ() + 0.699);
            double d = this.getCenter() == SurroundCenterMode.DEFAULT ? blockPos2.toCenterPos().x : ((Number)RangesKt.coerceIn((Comparable)Double.valueOf(Globals.getPlayer().getX()), closedFloatingPointRange)).doubleValue();
            double d2 = this.getCenter() == SurroundCenterMode.DEFAULT ? blockPos2.toCenterPos().z : ((Number)RangesKt.coerceIn((Comparable)Double.valueOf(Globals.getPlayer().getZ()), closedFloatingPointRange2)).doubleValue();
            Entity entity2 = Globals.getPlayer();
            Globals.getPlayer().setPosition(new Vec3d(d, this.getPos((Entity)entity2).y, d2));
            if (this.getForceCenter()) {
                ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                clientPlayerEntity.networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(d, clientPlayerEntity.getY(), d2, clientPlayerEntity.getYaw(), clientPlayerEntity.getPitch(), clientPlayerEntity.isOnGround(), clientPlayerEntity.horizontalCollision));
            }
        }
        w = false;
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        long l = h ^ 0x75DB3AA822E7L;
        long l2 = l ^ 0x5D67C28150FBL;
        if (Globals.getMc().player != null && Globals.getPlayer().getY() > Math.floor(Globals.getPlayer().lastY) + (double)0.9f && this.getYDisable()) {
            this.disable(l2);
        }
    }

    @Flow
    private final void onWorldUpdate(WorldUpdateEvent worldUpdateEvent) {
        long l;
        long l2 = l = h ^ 0x73F3B89DFC3DL;
        long l3 = l2 ^ 0x42AE637EB967L;
        long l4 = l2 ^ 0x7F61378B372CL;
        long l5 = l4 >>> 16;
        int n = (int)(l4 << 48 >>> 48);
        long l6 = l2 ^ 0x381EEE002882L;
        if (this.getAsync() && t.hasPassed2(this.getAsyncDelay()) && F.hasPassed((int)Math.ceil((double)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l5, 1, (char)n, null) * 1.5))) {
            RotatingModule rotatingModule = this;
            BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getX(), Globals.getPlayer().getY() - Math.floor(Globals.getPlayer().getY()) > 0.8 ? Math.floor(Globals.getPlayer().getY()) + 1.0 : Math.floor(Globals.getPlayer().getY()), Globals.getPlayer().getZ());
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            RotatingModule.s(rotatingModule, this.getSurroundPositions(blockPos, l3, new ArrayList()), true, false, null, false, 28, l6, null);
            w = true;
        }
    }

    @Flow(priority=10)
    private final void onAfterReceivePacket(AfterReceivePacket afterReceivePacket) {
        long l;
        long l2 = l = h ^ 0x6B8A577CA7DL;
        long l3 = l2 ^ 0x37E57E948F27L;
        long l4 = l2 ^ 0x4D55F3EA1EC2L;
        if (this.getAsync() && afterReceivePacket.getPacket() instanceof EntitySpawnS2CPacket) {
            Packet<?> packet = afterReceivePacket.getPacket();
            Vec3d vec3d = new Vec3d(((EntitySpawnS2CPacket)packet).getX(), ((EntitySpawnS2CPacket)packet).getY(), ((EntitySpawnS2CPacket)packet).getZ());
            if (Intrinsics.areEqual(((EntitySpawnS2CPacket)packet).getEntityType(), EntityType.END_CRYSTAL) && vec3d.squaredDistanceTo(Globals.getPlayer().getEyePos()) < 9.0) {
                BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getX(), Globals.getPlayer().getY() - Math.floor(Globals.getPlayer().getY()) > 0.8 ? Math.floor(Globals.getPlayer().getY()) + 1.0 : Math.floor(Globals.getPlayer().getY()), Globals.getPlayer().getZ());
                Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
                List list = this.getSurroundPositions(blockPos, l3, new ArrayList());
                if (!((Collection)list).isEmpty()) {
                    PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket = PlayerInteractEntityC2SPacket.attack(Globals.getPlayer(), Globals.getPlayer().isSneaking());
                    Intrinsics.checkNotNullExpressionValue(playerInteractEntityC2SPacket, "createAttackPacket(...)");
                    PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket2 = playerInteractEntityC2SPacket;
                    PacketAccess.setEntityId(playerInteractEntityC2SPacket2, ((EntitySpawnS2CPacket)packet).getEntityId());
                    PacketUtil.sendPacket(playerInteractEntityC2SPacket2);
                    PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
                    Iterable iterable = list;
                    RotatingModule.s(this, CollectionsKt.sortedWith(iterable, new Surround$onAfterReceivePacket$1(vec3d)), true, false, null, false, 28, l4, null);
                    w = true;
                    afterReceivePacket.cancel();
                }
            }
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block1: {
            long l;
            long l2 = l = h ^ 0x7C1FA943F7B0L;
            long l3 = l2 ^ 0x4D4272A0B2EAL;
            long l4 = l2 ^ 0x37F2FFDE230FL;
            if (w) {
                w = false;
                return;
            }
            RotatingModule rotatingModule = this;
            BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getX(), Globals.getPlayer().getY() - Math.floor(Globals.getPlayer().getY()) > 0.8 ? Math.floor(Globals.getPlayer().getY()) + 1.0 : Math.floor(Globals.getPlayer().getY()), Globals.getPlayer().getZ());
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            if (!RotatingModule.s(rotatingModule, this.getSurroundPositions(blockPos, l3, new ArrayList()), false, false, null, false, 30, l4, null)) break block1;
            F.reset();
        }
    }

    private final List getSurroundPositions(BlockPos blockPos, long l, List list) {
        Object object;
        Object object22;
        long l2 = l = h ^ l;
        long l3 = l2 ^ 0x3B80D00B9282L;
        long l4 = l2 ^ 0x1AB49770AEEL;
        List list2 = new ArrayList();
        Object object3 = object22 = (Iterable)Surround$EntriesMappings.enumEntries;
        Object object42 = new ArrayList();
        Object object5 = object3.iterator();
        while (object5.hasNext()) {
            object = object5.next();
            Direction direction = (Direction)object;
            if (!(direction != Direction.UP)) continue;
            object42.add(object);
        }
        for (Object object22 : (List)object42) {
            BlockPos blockPos2;
            Intrinsics.checkNotNullExpressionValue(blockPos.offset((Direction)object22), "relative(...)");
            if (list.contains(blockPos2) || !Globals.getWorld().getBlockState(blockPos2).isReplaceable()) continue;
            if (this.isBlockedByEntity(blockPos2)) {
                list.add(blockPos);
                list2.addAll(this.getSurroundPositions(blockPos2, l3, list));
                continue;
            }
            if (PlaceManager.place$default(PlaceManager.INSTANCE, blockPos2, 0, this.getInteract(), this.getPlaceMode(), EntityCheck.ONLY_CRYSTALS, 6.0f, 6.0f, this.getPriority(), l4, this.a(), null, false, 1536, null) == null) {
                object5 = object42 = (Iterable)Surround$EntriesMappings.enumEntries;
                object = new ArrayList();
                Iterator iterator2 = object5.iterator();
                while (iterator2.hasNext()) {
                    Object t = iterator2.next();
                    Direction direction = (Direction)t;
                    if (!(direction != Direction.UP)) continue;
                    object.add(t);
                }
                for (Object object42 : (List)object) {
                    BlockPos blockPos3;
                    if (list.contains(blockPos2) || !Globals.getWorld().getBlockState(blockPos2).isReplaceable()) continue;
                    Intrinsics.checkNotNullExpressionValue(blockPos2.offset((Direction)object42), "relative(...)");
                    if (PlaceManager.place$default(PlaceManager.INSTANCE, blockPos3, 0, this.getInteract(), this.getPlaceMode(), EntityCheck.ONLY_CRYSTALS, 6.0f, 6.0f, this.getPriority(), l4, this.a(), null, false, 1536, null) == null) continue;
                    list2.add(blockPos3);
                    list2.add(blockPos2);
                }
                continue;
            }
            list2.add(blockPos2);
        }
        Object object6 = MovementUtil.getMovement$default(MovementUtil.INSTANCE, 0.5f, true, 0.0f, 0.0f, 0.0f, 28, null);
        object22 = list2;
        return CollectionsKt.sortedWith(object22, new Surround$1((double[])object6));
    }

    @Override
    @NotNull
    protected List getBlocks(long l) {
        return this.getSelectedBlocks().getBlocks();
    }

    private static final boolean settingVisible() {
        return INSTANCE.getAsync();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getCenter() != SurroundCenterMode.OFF;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Surround.h = ZkmKeys.a(-3998563069585926341L, -7283649374402881105L, MethodHandles.lookup().lookupClass()).a(78911273403758L);
                        var20 = Surround.h ^ 97641888017991L;
                        Surround.I = new HashMap<K, V>(13);
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
                        var18_3 = new String[27];
                        var16_4 = 0;
                        var15_5 = "\u00a1+\u00ff\u00d0\u00ee\u009b\u0084\u0090\n\u00c5I\u0086\u0092\u0092sbx\u0019\u00c3\u00f4\u00c3\u00a6\u00e1\u00c22`u\u0019C\u00f6\u0002\u0082\u00b93\u0007\u00fe\u001f{7)\u0018\u00ceN,\u0006K\u00d8c\u00f4\u00dci OB\u00fe\u008ff%=\u0014\u0096\u00a9\t\u008d\u0089 \u00ff\u00e9\u009b\"D5\u00f1\u00fb\u00f5\u00f6\u0084N\u00cf\u00c1\u00b4T\u00c8\\\u00a1\u00a6\u00b3\u0080\u00f9\u0098T]5\u00c1\u0089\u00a2a\u00b6 c-<\u00a8Fh~w}\u00a7\u00d4`\u00e5H\u00bf\u00be/\u009c\u00d1\u00a6\u00de\u0005\u009b\u00b5\u00a8\u00b1\u001dE\u009fv\u001aV8ZX\u00d0\u001f*\u00da\u0082\u00cfo\u001d#;\u0084J\u0017T9\u0098/\u00e7UyE\u00b4\u00e9\u00f2lh{\u00a0\"p\u0000\u00e0_\u0084{\u0095Z\u00b2\u0012\u0000\u0014\u001d\u00b0\u0099\u00c3\u00e4Z\u0014\u009a\u00ee\u00e8zU\u0092\u0018\u00a7\u00bb\u0013\u00d4\u00c3\u00822\u00df\u00da\\d\u00cfqp;B\u00d5\u00a7(\u00dc/, Z(#\u000b}\u00db;\u00a9-\u00d8l\u00d0\u00ec\u00b0\u0005\u008aY \u00e3\u0092\u0011N\u00a1\u00d8\u001f\u0016\u0084\u00ec>\u00fc\u00b0r\u001cl\u00f7?\u00f1\u00d4\u00e2\u008f'\u00f2\u0010\u0091Ghr\b\u0092\u00e4\u0003\\\u0006P\u0086\u00af\u00b1\u0005\u00c3 \u0098\u0003\u001dv\u00ce^\u00d5\u0095\u0098\u00f9\u00e9\u0013\u00b8\u00e8]c\u00f7\u0096\u00be^O;9\u00de?N\u00df\u009e\n\u00ba\u00d1\u00ab\u0018P&\u00ca\\\u0018Ar\u009b?E\u00de\u00f9\u00d9\u00be\u00ee\u00f4\u00bb>\u00b7\u001f76\u00ad*\u0018\u00b6HS\u00ca\u0005\u00bc\u00fdE\u0018#\b\u00e5\u008b\u00e6\u009ePE\u00a0q\u00c2d\u00e1!\u00c0\u0010\u00d2\u00be;)\u00011\u00e9\u0093\u00e8M\u00d6\u00efq\u0085u\u00d8 V\u00b2\u00ba\u00f4=v\u0094\u00b2[\u0018\u00b2a\u0012\u00d3\u008b\u00fa\u00a8k\u009e6R\u000bY\u00dcqI\u0006c!@\u00db\u00b9(,]\u00af\u00c8\u00bd\u00a7z\u00a6\u0088]\u0017\u001c)~k\u00dd\u0083\u008c\u0083i\u00b4QN\u00e9dWu\u009d\u00a1\u00b03\u00d4~a\u0091\u00aa\u00fe\u00b4o\u00e4\u0010\u0011Zb\u00fa+\u00ed7\u00e2\u008a%\u00f6\u00a5\u0097\u00f9\u0006\u0098\u0010\u000b\u00f9\u001c\u0093\u00c0ckR\u0012Lth\u00d7\u00bafA\u0018\u00faKQ\u00a6/Onh0\u00d4\u00e8}\u00f3T\u00e3\u00c8\u00bbX\u0095\u00d4\u00dd\u00c3\u001c\u0094 a\u00b2\u0097\u0006D/7\u00ca'\u00c7\u00af\u00d9\u00c8}r\u00d3\u00be\u00ac\u0089\u00cc\u001a\f\u00c5\r;C8\u009a\u008d@\tk \u0012\u0015\u00ab\u00f8\u0081\u00c8\u00d4b\u0097 9\u00f8\u00e98L\u00c7\u00f3\u00d0|\u00ae\u0082\u00d8\u00eb\u0085z\u000e\u00aey]hD\u00b8\u0018\u0085\u00ee\u008cT\u008e\u0080L\u00d3\u00cfZ\u009c\u00c1\u0090\u00ac#\u0005\u0090\u0090\u0082Rp\u001ah\u00a7 \t\u00ac\u00eb\u00aa\u00c6teA\u00b8?\u00ac\u00e6\u00e2F\u0015T\u00f4\u0085\u00c1\u00c1\u00f9\u007f@\u00ed\u001e\u00c9\u0097fZ\u00a5\u0016\u0094\u0010\tP}\u00df\u0010j|\u00eb\u00e9[\u00f2\u00bb!&R9 \u00f4\n\u008b\u00ddG\u0083,\u00f0\u007f\u00da\u0016\u001d\u00e5R\u00b7\u00e8\u0017\u0083\u008fL5u\u00c4-\u0011\u00fe\u00c6\u00b4\u00c9\u00dc#\u009e@\u0094(g\u00cc\u00e9\u0016\u00e2\u0099\u0018}\u0088\u001c\u00be\u00f5\u008c\u00eb(<\u00ce\u0090o\u00f9\bz\u0086\u0017\u0017{\u00c3\u00812\u00e0\u00b5\u00cbz\u0017\u00a0;\n-YU\\{\u00bb\u001f\u00f0\u0015#n\u0082O[a\u00ac_\u00e2\u00aaZ\u009b\u00b4D\u00c0\u00c5 \u00d5(\u0089\u00fdc\u00feJ\u009a#U\u00b5\u0098\u00dc\u00b1\u001dX\u00fc\u00a8\u00be\u0087>\b\u00e8t\b\u00dd\\\u00bf\u00948x\u00d5";
                        var17_6 = 776;
                        var14_7 = 40;
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
                            var18_3[var16_4++] = Surround.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00e1C\u00a8\u00fcI\u00fc\u00b73w\u0082\u00e74\u009a\u0096\u00c5&\u00f730\u00fe\u00cb\u00ed\u00fa= \r\u00ca7\u00b4\u00db\u00f7\u009f_j\n\u00c2\u0016;\u00d4\u00f8]G\u00df:\u0082\u008e9\u00ffI\u00c9\u00f3\u0089\u0089\u00b1\u00a6;\u00b6";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = Surround.zkmDecodeUtf8(var19_9).intern();
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
                Surround.m = var18_3;
                Surround.o = new String[27];
                Surround.pb = new HashMap<K, V>(13);
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
                var6_12 = new long[11];
                var3_13 = 0;
                var4_14 = "\u00e5\u00f5\u00e0\u0007+\u0099\u00be\u00ffV\u009f\u0014\u00bb\u00b40J\u0080)(\u00bdi\u0097\u0094N0*A\u00b8\u00c2\b\u00aft\u008d@:\u00ee\u00ead\u00911\b\u00c3\u00d3Y\u00dd.\u00fc\u001d\u008c\u0091\u0092\\\u0087\u00d2\u009c\b\u00adT4\u00ba\u0088\u0089\u00f3x\u00a3\u00ed6|\u0019\u0090\u0085\u0002\u00ed";
                var5_15 = 72;
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
                    var4_14 = "@\u00ab\u00b8_\u0013\u00ccY\u00a8P\u00b3;\u009f\u001b\u00c9b}";
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
        Surround.W = var6_12;
        Surround.ob = new Integer[11];
        var39_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Surround.class, "yDisable", "getYDisable()Z", 0)), Reflection.property1(new PropertyReference1Impl(Surround.class, "async", "getAsync()Z", 0)), Reflection.property1(new PropertyReference1Impl(Surround.class, "asyncDelay", "getAsyncDelay()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Surround.class, "selectedBlocks", "getSelectedBlocks()Lsu/catlean/dg;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Surround.class, "center", "getCenter()Lsu/catlean/rs;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Surround.class, "forceCenter", "getForceCenter()Z", 0))};
        Surround.delegatedProperties = var39_20;
        Surround.INSTANCE = new Surround();
        Surround.yDisable$delegate = SettingFactory.booleanSetting$default((SettingContainer)Surround.INSTANCE, "y disable", true, null, null, 12, null);
        Surround.async$delegate = SettingFactory.booleanSetting$default((SettingContainer)Surround.INSTANCE, "async", true, null, null, 12, null);
        Surround.asyncDelay$delegate = SettingFactory.intSetting$default(Surround.INSTANCE, "async-delay", 0, new IntRange(0, 250), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        v15 = Surround.INSTANCE;
        var39_20 = new Block[3];
        Intrinsics.checkNotNullExpressionValue(Blocks.OBSIDIAN, "OBSIDIAN");
        Intrinsics.checkNotNullExpressionValue(Blocks.ENDER_CHEST, "ENDER_CHEST");
        Intrinsics.checkNotNullExpressionValue(Blocks.NETHERITE_BLOCK, "NETHERITE_BLOCK");
        var34_21 = null;
        var36_22 = null;
        var37_23 = null;
        var38_24 = new BlockListValue(CollectionsKt.mutableListOf(var39_20));
        Surround.selectedBlocks$delegate = SettingFactory.blockListSetting(v15, "blocks", var38_24, var37_23, var36_22, var34_21);
        Surround.center$delegate = SettingFactory.modeSetting$default((SettingContainer)Surround.INSTANCE, "center", (Enum)SurroundCenterMode.OFF, null, null, 12, null);
        Surround.forceCenter$delegate = SettingFactory.booleanSetting$default((SettingContainer)Surround.INSTANCE, "force-center", false, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 4, null);
        Surround.t = new Timer();
        Surround.F = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2D92;
        if (o[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])I.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    I.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_9", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = m[n2].getBytes("ISO-8859-1");
            Surround.o[n2] = Surround.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return o[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Surround.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5D4D;
        if (ob[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = W[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])pb.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    pb.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_9", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Surround.ob[n2] = n3;
        }
        return ob[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Surround.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Surround.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Surround.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

