/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.rage.elytra;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterSendPacket;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.AfterElytraEvent;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.PreElytraEvent;
import su.catlean.api.event.events.player.PreSyncEvent;
import su.catlean.api.event.events.player.SetPoseEvent;
import su.catlean.api.event.events.world.FireWorkVelocityEvent;
import su.catlean.module.equip.ElytraSwap;
import su.catlean.module.rage.ElytraPlus;
import su.catlean.module.rage.ElytraPlusMode;
import su.catlean.render.RenderUtil;
import su.catlean.util.ElytraListener;
import su.catlean.util.Globals;
import su.catlean.util.KeyboardUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.Timer;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class BoostElytra
implements ElytraListener {
    @NotNull
    public static final BoostElytra INSTANCE;
    @NotNull
    private static final Timer k;
    @NotNull
    private static final Timer boostTimer;
    private static final String[] a;
    private static final String[] b;
    private static final Map c;
    private static final long[] d;
    private static final Integer[] e;
    private static final Map f;

    private BoostElytra() {
    }

    @NotNull
    public final Timer k() {
        return k;
    }

    @Override
    public void onPreSync(long l, @NotNull PreSyncEvent preSyncEvent) {
        Intrinsics.checkNotNullParameter(preSyncEvent, "e");
        if (ElytraPlus.INSTANCE.getTwoBee() && ElytraPlus.INSTANCE.getMode() == ElytraPlusMode.BOOST) {
            return;
        }
        if (Globals.getPlayer().isOnGround()) {
            ElytraPlus.INSTANCE.setIsReady(true);
        }
        if (!ElytraPlus.INSTANCE.getCruiseControl()) {
            ElytraPlus.INSTANCE.setBoostSpeed((float)Globals.getPlayer().getY());
        }
        if (!Globals.getPlayer().isGliding() && ElytraPlus.INSTANCE.isWearingElytra()) {
            if (ElytraPlus.INSTANCE.isReady() && !Globals.getPlayer().isOnGround() && MovementUtil.INSTANCE.getFallDistance() > 0.0 && ElytraPlus.INSTANCE.kC()) {
                TimerManager.INSTANCE.x(0.3f);
            }
            if (!Globals.getPlayer().isOnGround() && ElytraPlus.INSTANCE.kC() && Globals.getPlayer().getVelocity().getY() < 0.0) {
                if (!ElytraPlus.INSTANCE.getRedeployTimer().hasPassed((int)(1000.0f * ElytraPlus.INSTANCE.getRedeployDelay()))) {
                    return;
                }
                ElytraPlus.INSTANCE.getRedeployTimer().reset();
                ElytraSwap.INSTANCE.K(true);
                PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                ElytraPlus.INSTANCE.setIsReady(false);
                ElytraPlus.INSTANCE.getBoostTimer().reset();
            }
        }
    }

    @Override
    public void onPreElytra(@NotNull PreElytraEvent preElytraEvent, long l) {
        Intrinsics.checkNotNullParameter(preElytraEvent, "e");
    }

    @Override
    public void onAfterElytra(@NotNull AfterElytraEvent afterElytraEvent) {
        Intrinsics.checkNotNullParameter(afterElytraEvent, "e");
    }

    @Override
    public void onMove(@NotNull MoveEvent moveEvent, long l) {
        double d;
        Intrinsics.checkNotNullParameter(moveEvent, "e");
        if (!Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA) || !Globals.getPlayer().isGliding() || Globals.getPlayer().isTouchingWater() || Globals.getPlayer().isInLava()) {
            return;
        }
        float f = Globals.getPlayer().input.getMovementInput().y;
        if (ElytraPlus.INSTANCE.getCruiseControl()) {
            if (Globals.getMc().options.jumpKey.isPressed()) {
                var48_4 = ElytraPlus.INSTANCE.getBoostSpeed();
                ElytraPlus.INSTANCE.setBoostSpeed(var48_4 + 1.0f);
            } else if (Globals.getMc().options.sneakKey.isPressed()) {
                var48_4 = ElytraPlus.INSTANCE.getBoostSpeed();
                ElytraPlus.INSTANCE.setBoostSpeed(var48_4 + -1.0f);
            }
            if (ElytraPlus.INSTANCE.getForceHeight()) {
                ElytraPlus.INSTANCE.setBoostSpeed(ElytraPlus.INSTANCE.getManualHeight());
            }
            if (ElytraPlus.INSTANCE.getTwoBee()) {
                if (MovementUtil.INSTANCE.getHorizontalSpeed() >= ElytraPlus.INSTANCE.getMinUpSpeed()) {
                    Globals.getPlayer().setPitch((float)MathHelper.clamp(MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(((double)ElytraPlus.INSTANCE.getBoostSpeed() - Globals.getPlayer().getY()) * -1.0, 10.0))), -50.0, 50.0));
                } else {
                    Globals.getPlayer().setPitch(0.25f);
                }
            } else {
                double d2 = 1.0 - Math.sqrt(MathHelper.clamp((double)MovementUtil.INSTANCE.getHorizontalSpeed() / 1.7, 0.0, 1.0));
                if (MovementUtil.INSTANCE.getHorizontalSpeed() >= ElytraPlus.INSTANCE.getMinUpSpeed() && ElytraPlus.INSTANCE.getRedeployTimer().hasPassed((int)(2000.0f * ElytraPlus.INSTANCE.getRedeployInterval()))) {
                    double d3 = -(44.4 * d2 + 0.6);
                    double d4 = ((double)(ElytraPlus.INSTANCE.getBoostSpeed() + 1.0f) - Globals.getPlayer().getY()) * 2.0;
                    d = -Math.toDegrees(Math.atan2(Math.abs(d4), (double)MovementUtil.INSTANCE.getHorizontalSpeed() * 30.0)) * Math.signum(d4);
                    Globals.getPlayer().setPitch((float)(d3 + (d - d3) * MathHelper.clamp(Math.abs(d4), 0.0, 1.0)));
                } else {
                    Globals.getPlayer().setPitch(0.25f);
                    f = 1.0f;
                }
            }
        }
        if (ElytraPlus.INSTANCE.getTwoBee()) {
            if (Globals.getMc().options.jumpKey.isPressed() || !ElytraPlus.INSTANCE.getOnlySpace() || ElytraPlus.INSTANCE.getCruiseControl()) {
                double d5 = (double)ElytraPlus.INSTANCE.Fl() / 10.0;
                double[] dArray = MovementUtil.INSTANCE.getMotionForSpeed(d5);
                moveEvent.setX(moveEvent.getX() + dArray[0]);
                moveEvent.setZ(moveEvent.getZ() + dArray[1]);
                moveEvent.setY(moveEvent.getY() + 0.003);
            }
        } else {
            Vec3d vec3d = Globals.getPlayer().getRotationVec(RenderUtil.INSTANCE.getTickDelta());
            Intrinsics.checkNotNullExpressionValue(vec3d, "getViewVector(...)");
            Vec3d vec3d2 = vec3d;
            double d6 = Math.hypot(vec3d2.x, vec3d2.z);
            double d7 = Math.hypot(moveEvent.getX(), moveEvent.getZ());
            float f2 = (float)(Math.pow(Math.cos(Math.toRadians(Globals.getPlayer().getPitch())), 2.0) * Math.min(1.0, vec3d2.length() / 0.4));
            moveEvent.setY(moveEvent.getY() + (-0.08 + (double)f2 * 0.06));
            if (moveEvent.getY() < 0.0 && d6 > 0.0) {
                d = moveEvent.getY() * -0.1 * (double)f2;
                moveEvent.setY(moveEvent.getY() + d);
                moveEvent.setX(moveEvent.getX() + vec3d2.x * d / d6);
                moveEvent.setZ(moveEvent.getZ() + vec3d2.z * d / d6);
            }
            if (Globals.getPlayer().getPitch() < 0.0f) {
                d = d7 * -Math.sin(Math.toRadians(Globals.getPlayer().getPitch())) * 0.04;
                moveEvent.setY(moveEvent.getY() + d * 3.2);
                moveEvent.setX(moveEvent.getX() - vec3d2.x * d / d6);
                moveEvent.setZ(moveEvent.getZ() - vec3d2.z * d / d6);
            }
            if (d6 > 0.0) {
                moveEvent.setX(moveEvent.getX() + (vec3d2.x / d6 * d7 - moveEvent.getX()) * 0.1);
                moveEvent.setZ(moveEvent.getZ() + (vec3d2.z / d6 * d7 - moveEvent.getZ()) * 0.1);
            }
            if (Globals.getPlayer().getPitch() > 0.0f && moveEvent.getY() < 0.0 && ElytraPlus.INSTANCE.isWearingElytra()) {
                if (!(f == 0.0f) && ElytraPlus.INSTANCE.getRedeployTimer().hasPassed((int)(2000.0f * ElytraPlus.INSTANCE.getRedeployInterval())) && k.hasPassed((int)(1000.0f * ElytraPlus.INSTANCE.getRedeployTimeOut()))) {
                    if (ElytraPlus.INSTANCE.getStopMotion()) {
                        moveEvent.setX(0.0);
                        moveEvent.setZ(0.0);
                    }
                    ElytraPlus.INSTANCE.getRedeployTimer().reset();
                    PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                } else if (!ElytraPlus.INSTANCE.getRedeployTimer().hasPassed((int)(2000.0f * ElytraPlus.INSTANCE.getRedeployInterval()))) {
                    moveEvent.setX(moveEvent.getX() - (double)f * Math.sin(Math.toRadians(Globals.getPlayer().getYaw())) * (double)ElytraPlus.INSTANCE.Fl() / 20.0);
                    moveEvent.setZ(moveEvent.getZ() + (double)f * Math.cos(Math.toRadians(Globals.getPlayer().getYaw())) * (double)ElytraPlus.INSTANCE.Fl() / 20.0);
                    k.reset();
                }
            }
        }
        double d8 = Math.hypot(moveEvent.getX(), moveEvent.getZ());
        if (ElytraPlus.INSTANCE.Fg() && d8 > (double)ElytraPlus.INSTANCE.getMaxSpeed()) {
            moveEvent.setX(moveEvent.getX() * (double)ElytraPlus.INSTANCE.getMaxSpeed() / d8);
            moveEvent.setZ(moveEvent.getZ() * (double)ElytraPlus.INSTANCE.getMaxSpeed() / d8);
        }
        Globals.getPlayer().setVelocity(moveEvent.getX(), moveEvent.getY(), moveEvent.getZ());
        moveEvent.cancel();
    }

    @Override
    public void onAfterSendPacket(long l, @NotNull AfterSendPacket afterSendPacket) {
        Intrinsics.checkNotNullParameter(afterSendPacket, "event");
    }

    @Override
    public void onReceivePacket(@NotNull ReceivePacket receivePacket, long l, char c) {
        Intrinsics.checkNotNullParameter(receivePacket, "e");
    }

    @Override
    public void onPlayerUpdate(int n, @NotNull PlayerUpdateEvent playerUpdateEvent, char c, char c2) {
        int n2;
        char c3;
        long l = (long)n << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48;
        int n3 = (int)((l ^ 0x762A765A9D26L) << 48 >>> 48);
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        if (ElytraPlus.INSTANCE.getTwoBee() && KeyboardUtil.isKeyPressed(c3 = (char)n3, n2 = ElytraPlus.INSTANCE.getLift().getKey()) && MovementUtil.INSTANCE.getFallDistance() > 0.0) {
            if (!Globals.getPlayer().isGliding()) {
                ElytraSwap.INSTANCE.K(true);
                PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                Globals.getPlayer().startGliding();
            }
            double d = 0.05 * (double)Math.min((float)(Globals.now() - boostTimer.getElapsed()) / 100.0f, 1.0f);
            Globals.getPlayer().setVelocity(0.0, Math.min(Globals.getPlayer().getVelocity().y + d, 1.0), 0.0);
        } else {
            boostTimer.reset();
        }
    }

    @Override
    public void onEnable(char c, int n, short s) {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onFireworkVelocity(@NotNull FireWorkVelocityEvent fireWorkVelocityEvent) {
        Intrinsics.checkNotNullParameter(fireWorkVelocityEvent, "e");
    }

    @Override
    public void onSetPose(@NotNull SetPoseEvent setPoseEvent) {
        Intrinsics.checkNotNullParameter(setPoseEvent, "e");
    }

    @Override
    public void handlePacket(long l, @NotNull ReceivePacket receivePacket) {
        block1: {
            int n;
            char c;
            int n2 = (int)((l ^ 0x3617072CE90CL) << 48 >>> 48);
            Intrinsics.checkNotNullParameter(receivePacket, "e");
            if (!(receivePacket.getPacket() instanceof EntityTrackerUpdateS2CPacket) || ((EntityTrackerUpdateS2CPacket)receivePacket.getPacket()).comp_1127() != Globals.getPlayer().getId() || !KeyboardUtil.isKeyPressed(c = (char)n2, n = ElytraPlus.INSTANCE.getLift().getKey())) break block1;
            List<DataTracker.SerializedEntry<?>> list = ((EntityTrackerUpdateS2CPacket)receivePacket.getPacket()).comp_1128();
            Intrinsics.checkNotNullExpressionValue(list, "packedItems(...)");
            if (!((Collection)list).isEmpty()) {
                receivePacket.cancel();
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    BoostElytra.c = new HashMap<K, V>(13);
                    var11 = ZkmKeys.a(-3608008337857735485L, -258073531758580433L, MethodHandles.lookup().lookupClass()).a(140953315821104L) ^ 136716533412049L;
                    var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var11 >>> 56);
                    for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                        v2 = v2;
                        v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                    }
                    var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var20_3 = new String[3];
                    var18_4 = 0;
                    var17_5 = "\u00c7\u0086\u00a3LD+zqM\u00bf\u0016\u0010\u0016\t/\u00e2\u00ac\u0087\u00b5\u00ed\u00cf\u00c2\u0087\u00f2\f\u00ea`\u008b\u00c9'jP\u008e\u00e20&\u0086\u0090\t\u00a4(\u001e\u00fe\u0019t\u000f\u00f5\u00a5\u0094m\u0019\u00b1\u007fN\u00c9Pd\u001e--\u0083hxH\u001a\u00c8kx9;\u00b8\u00b0JG\u00c5#2/{\u0095o\u0010}[\u008f\u00e7\u00af\u009a`\u00d3\u00ec\u00a7\u0087v]\u00c9c\u00d6";
                    var19_6 = 98;
                    var16_7 = 40;
                    var15_8 = -1;
lbl19:
                    // 2 sources

                    while (true) {
                        continue;
                        break;
                    }
lbl21:
                    // 1 sources

                    while (true) {
                        var20_3[var18_4++] = BoostElytra.zkmDecodeUtf8(var21_9).intern();
                        if ((var15_8 += var16_7) < var19_6) {
                            var16_7 = var17_5.charAt(var15_8);
                            ** continue;
                        }
                        break block12;
                        break;
                    }
                    v3 = ++var15_8;
                    var21_9 = var13_1.doFinal(var17_5.substring(v3, v3 + var16_7).getBytes("ISO-8859-1"));
                    ** while (true)
                }
                BoostElytra.a = var20_3;
                BoostElytra.b = new String[3];
                BoostElytra.f = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v4 = SecretKeyFactory.getInstance("DES");
                v5 = new byte[8];
                v6 = v5;
                v5[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v6 = v6;
                    v6[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v4.generateSecret(new DESKeySpec(v6)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u00adTd,\u0097\u001b\u0090\u00bc4sL\n\u00aewGi\u00ac\u00de\u000b7\u00e0\u0006\u008b\u001b";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v7 = var6_12;
                    v8 = var3_13++;
                    v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v10 = -1;
                    break block13;
                    break;
                }
lbl56:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u001eQ]\u00f4\u00ce\u00fd\u00cev\u00b3FJ\t\u0081\u00a6\u00c0\u00fe";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v7 = var6_12;
                        v8 = var3_13++;
                        v9 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v10 = 0;
                        break block13;
                        break;
                    }
                    break;
                }
lbl69:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var2_16 < var5_15) ** continue;
                    break block14;
                    break;
                }
            }
            var8_18 = v9;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v11 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v10) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl82:
                // 1 sources

                ** continue;
            }
        }
        BoostElytra.d = var6_12;
        BoostElytra.e = new Integer[5];
        BoostElytra.INSTANCE = new BoostElytra();
        BoostElytra.k = new Timer();
        BoostElytra.boostTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x25C6;
        if (b[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])c.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    c.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/pu", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = a[n2].getBytes("ISO-8859-1");
            BoostElytra.b[n2] = BoostElytra.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return b[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BoostElytra.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4408;
        if (e[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = d[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])f.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/pu", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BoostElytra.e[n2] = n3;
        }
        return e[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BoostElytra.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BoostElytra.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BoostElytra.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

