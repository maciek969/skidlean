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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
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
import su.catlean.util.ElytraListener;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.TimerManager;
import su.catlean.zkm.ZkmKeys;

public final class ControlElytra
implements ElytraListener {
    @NotNull
    public static final ControlElytra INSTANCE;
    private static final String a;
    private static final long[] b;
    private static final Integer[] c;
    private static final Map d;

    private ControlElytra() {
    }

    @Override
    public void onPreSync(long l, @NotNull PreSyncEvent preSyncEvent) {
        Intrinsics.checkNotNullParameter(preSyncEvent, "e");
        if (Globals.getPlayer().isOnGround()) {
            ElytraPlus.INSTANCE.setIsReady(true);
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
        if (!Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA) || !Globals.getPlayer().isGliding()) {
            return;
        }
        MovementUtil movementUtil = MovementUtil.INSTANCE;
        float f = ElytraPlus.INSTANCE.getXzSpeed();
        if (ElytraPlus.INSTANCE.getAccelerate()) {
            float f2 = ElytraPlus.INSTANCE.getAccelerateFactor();
            float f3 = f;
            MovementUtil movementUtil2 = movementUtil;
            ElytraPlus.INSTANCE.setCurrentSpeed(ElytraPlus.INSTANCE.getCurrentSpeed() + f2);
            float f4 = ElytraPlus.INSTANCE.getCurrentSpeed();
            movementUtil = movementUtil2;
            f = f3;
            double d2 = f4 / 100.0f;
            double d3 = 1.0;
            d = Math.min(d2, d3);
        } else {
            d = 1.0;
        }
        double[] dArray = MovementUtil.getMovement$default(movementUtil, f * (float)d, false, 0.0f, 0.0f, 0.0f, 30, null);
        moveEvent.setX(dArray[0]);
        moveEvent.setY(Globals.getMc().options.jumpKey.isPressed() ? (double)ElytraPlus.INSTANCE.getUpSpeed() : (Globals.getMc().options.sneakKey.isPressed() ? -((double)ElytraPlus.INSTANCE.getSneakDownSpeed()) : (ElytraPlus.INSTANCE.getPitchControl() && MovementUtil.INSTANCE.isMoving() ? (double)Globals.getPlayer().getPitch() / 90.0 * (double)(-ElytraPlus.INSTANCE.getUpSpeed()) * (double)Math.signum(Globals.getPlayer().input.getMovementInput().y) : 0.0) - 0.08 * (double)ElytraPlus.INSTANCE.getDownFactor()));
        moveEvent.setZ(dArray[1]);
        if (!MovementUtil.INSTANCE.isMoving()) {
            ElytraPlus.INSTANCE.setCurrentSpeed(0.0f);
        }
        Globals.getPlayer().setVelocity(moveEvent.getX(), moveEvent.getY(), moveEvent.getZ());
        moveEvent.cancel();
    }

    @Override
    public void onAfterSendPacket(long l, @NotNull AfterSendPacket afterSendPacket) {
        Intrinsics.checkNotNullParameter(afterSendPacket, a);
    }

    @Override
    public void onReceivePacket(@NotNull ReceivePacket receivePacket, long l, char c) {
        Intrinsics.checkNotNullParameter(receivePacket, "e");
    }

    @Override
    public void onPlayerUpdate(int n, @NotNull PlayerUpdateEvent playerUpdateEvent, char c, char c2) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
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
        Intrinsics.checkNotNullParameter(receivePacket, "e");
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(8822416560424700861L, -2284376559956725315L, MethodHandles.lookup().lookupClass()).a(80909883216423L) ^ 0x221EE07563CAL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        byte[] byArray3 = cipher.doFinal("'\u000b\u00ad\u00c8|\u009d\u00854".getBytes("ISO-8859-1"));
        a = ControlElytra.zkmDecodeUtf8(byArray3).intern();
        d = new HashMap(13);
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray5 = byArray5;
            byArray5[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
        long[] lArray = new long[3];
        int n = 0;
        String string = "\u00ab\u00c6\u00fc\u00add,M\u00f8\u008fJ~\u00e40\u00bd\u00cb#p\u00de1S\u001d\u0092\u00d5\u00fb";
        int n2 = 24;
        int n3 = 0;
        do {
            byte[] byArray6 = string.substring(n3, n3 += 8).getBytes("ISO-8859-1");
            int n4 = n++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n4] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n3 < n2);
        b = lArray;
        c = new Integer[3];
        INSTANCE = new ControlElytra();
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

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x64D2;
        if (c[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = b[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])d.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/sn", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ControlElytra.c[n2] = n3;
        }
        return c[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ControlElytra.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/sn" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ControlElytra.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

