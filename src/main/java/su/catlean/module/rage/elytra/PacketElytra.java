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
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
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
import su.catlean.module.rage.ElytraPlusNcpStrictMode;
import su.catlean.module.rage.elytra.PacketElytra$WhenMappings;
import su.catlean.util.ElytraListener;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class PacketElytra
implements ElytraListener {
    @NotNull
    public static final PacketElytra INSTANCE;
    private static String[] E;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private PacketElytra() {
    }

    @Override
    public void onPreSync(long l, @NotNull PreSyncEvent preSyncEvent) {
        block2: {
            Intrinsics.checkNotNullParameter(preSyncEvent, "e");
            if (this.isOnGround() && ElytraPlus.INSTANCE.getStopOnGround() || !ElytraPlus.INSTANCE.isWearingElytra()) break block2;
            if (ElytraPlus.INSTANCE.getInfDurability() || !Globals.getPlayer().isGliding()) {
                ElytraSwap.INSTANCE.K(true);
                PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
            }
            if (Globals.getPlayer().age % 3 != 0 && ElytraPlus.INSTANCE.getNcpStrictMode() == ElytraPlusNcpStrictMode.Motion) {
                preSyncEvent.cancel();
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
        Globals.getPlayer().getAbilities().flying = false;
        Globals.getPlayer().getAbilities().setFlySpeed(0.05f);
        if (this.isOnGround() && ElytraPlus.INSTANCE.getStopOnGround() || !Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(38).getItem(), Items.ELYTRA)) {
            return;
        }
        Globals.getPlayer().getAbilities().flying = true;
        PlayerAbilities playerAbilities = Globals.getPlayer().getAbilities();
        double d2 = ElytraPlus.INSTANCE.getXzSpeed() / 15.0f;
        if (ElytraPlus.INSTANCE.getAccelerate()) {
            float f = ElytraPlus.INSTANCE.getAccelerateFactor();
            double d3 = d2;
            PlayerAbilities playerAbilities2 = playerAbilities;
            ElytraPlus.INSTANCE.setCurrentSpeed(ElytraPlus.INSTANCE.getCurrentSpeed() + f);
            float f2 = ElytraPlus.INSTANCE.getCurrentSpeed();
            playerAbilities = playerAbilities2;
            d2 = d3;
            double d4 = (double)f2 / 100.0;
            double d5 = 1.0;
            d = Math.min(d4, d5);
        } else {
            d = 1.0;
        }
        playerAbilities.setFlySpeed((float)(d2 * d));
        moveEvent.cancel();
        if (Globals.getPlayer().age % 3 == 0 && ElytraPlus.INSTANCE.getNcpStrictMode() == ElytraPlusNcpStrictMode.Motion) {
            moveEvent.setX(0.0);
            moveEvent.setY(0.0);
            moveEvent.setZ(0.0);
            return;
        }
        if (Math.abs(moveEvent.getX()) < 0.05) {
            moveEvent.setX(0.0);
        }
        if (Math.abs(moveEvent.getZ()) < 0.05) {
            moveEvent.setZ(0.0);
        }
        moveEvent.setY(ElytraPlus.INSTANCE.getVertical() ? (Globals.getMc().options.jumpKey.isPressed() ? (double)ElytraPlus.INSTANCE.getYSpeed() : (Globals.getMc().options.sneakKey.isPressed() ? -((double)ElytraPlus.INSTANCE.getYSpeed()) : 0.0)) : 0.0);
        switch (PacketElytra$WhenMappings.j[ElytraPlus.INSTANCE.getNcpStrictMode().ordinal()]) {
            case 1: {
                moveEvent.setY(-1.000088900582341E-12);
                break;
            }
            case 2: {
                moveEvent.setY(-4.000355602329364E-12);
                break;
            }
            case 3: {
                moveEvent.setY(2.0E-4 - (Globals.getPlayer().age % 2 == 0 ? 0.0 : 1.0E-6) + Math.random() * 9.0E-7);
            }
        }
        if (Globals.getPlayer().horizontalCollision && (ElytraPlus.INSTANCE.getNcpStrictMode() == ElytraPlusNcpStrictMode.New || ElytraPlus.INSTANCE.getNcpStrictMode() == ElytraPlusNcpStrictMode.Motion) && Globals.getPlayer().age % 2 == 0) {
            moveEvent.setY(-0.07840000152587923);
        }
        if ((ElytraPlus.INSTANCE.getInfDurability() || ElytraPlus.INSTANCE.getNcpStrictMode() == ElytraPlusNcpStrictMode.Motion) && !MovementUtil.INSTANCE.isMoving() && Math.abs(moveEvent.getX()) < 0.121 && Math.abs(moveEvent.getZ()) < 0.121) {
            float f = (float)Math.toRadians(4.5 * (double)(Globals.getPlayer().age % 80));
            moveEvent.setX(Math.sin(f) * 0.12);
            moveEvent.setZ(Math.cos(f) * 0.12);
        }
    }

    @Override
    public void onAfterSendPacket(long l, @NotNull AfterSendPacket afterSendPacket) {
        Intrinsics.checkNotNullParameter(afterSendPacket, "event");
    }

    @Override
    public void onReceivePacket(@NotNull ReceivePacket receivePacket, long l, char c) {
        Intrinsics.checkNotNullParameter(receivePacket, "e");
        if (receivePacket.getPacket() instanceof EntityTrackerUpdateS2CPacket) {
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket");
            EntityTrackerUpdateS2CPacket entityTrackerUpdateS2CPacket = (EntityTrackerUpdateS2CPacket)packet;
            if (entityTrackerUpdateS2CPacket.comp_1127() == Globals.getPlayer().getId()) {
                if (entityTrackerUpdateS2CPacket.comp_1128().isEmpty()) {
                    return;
                }
                receivePacket.cancel();
            }
        }
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
        Globals.getPlayer().getAbilities().flying = false;
        Globals.getPlayer().getAbilities().setFlySpeed(0.05f);
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

    public final boolean isOnGround() {
        return Globals.getWorld().getBlockCollisions(Globals.getPlayer(), Globals.getPlayer().getBoundingBox().expand(-0.25, 0.0, -0.25).offset(0.0, -0.3, 0.0)).iterator().hasNext();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(943275813236047888L, 5024495033521770593L, MethodHandles.lookup().lookupClass()).a(91233300279404L);
        long l = a ^ 0x5F4236E27C49L;
        d = new HashMap(13);
        PacketElytra.zkmSetFlag(null);
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
        String[] stringArray = new String[2];
        int n = 0;
        String string = "K\u00ee\u00dc\u008a\fv\u00bfNU\u0092\u008c(\u00f6\u0080\u00a9P` V\\#sf`\u00b4\u00c6U\u00b0\u00dc\u00bbU\u00e41\u00e0\u0015\u00ab!-#\u009b\u00a4k\u00d0\u00fe\u0089\u001a\u00e2\u00f6\u000e\u00cb\u00c4\u0098\u009cKZy\u00bb\u00e0>\u00ba\u00b2\u0013\u00ce\u00a5\u00ff-\u00f5\u00eb\u0099\u0099\u00a7\u00c3\u000f\u00c7\u00d5\u00aa\u00ad\u009dn\u00d3\u00c7\t\u00c2\u00e9\u00968f\u00066\u0012\u0094\u00ec\u0007\u00f0M\u008evu\u00e0{\u00c4d]\u00bc\u00dbU\u0006\u00fe\u009d\u001eN\f%\u00d6\u0094\u00ccl\u00ac]v\u00ca\u0014\u00a8\f.\u00d8\u00c3\u00f6\u00c7\u000e\u00e6\u00aa kp\u00dag+H\u0084\u00b8\u0090\u00ae\u008e\u00c9m\u009a\u00e7Y\u00cb~\u0087]\u0001C\u0081\\\u00ee\u00d4\u00da\u0010Y\u0017+O!\u00c0\u00ad\u00b9\u00c9\u0015\u00df\u00a5\u0003\u00a3S\u009a";
        int n2 = 177;
        int n3 = 160;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = PacketElytra.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[2];
        g = new HashMap(13);
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
        long[] lArray = new long[2];
        int n6 = 0;
        String string2 = "M\u009ah_>5\u00e0\u0093I=\u00a6>\u00a6\u00a6uV";
        int n7 = 16;
        int n8 = 0;
        do {
            byte[] byArray6 = string2.substring(n8, n8 += 8).getBytes("ISO-8859-1");
            int n9 = n6++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n9] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n8 < n7);
        e = lArray;
        f = new Integer[2];
        INSTANCE = new PacketElytra();
    }

    public static void zkmSetFlag(String[] stringArray) {
        E = stringArray;
    }

    public static String[] zkmGetFlag() {
        return E;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x70EA;
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
                throw new RuntimeException("su/catlean/tx", exception);
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
            PacketElytra.c[n2] = PacketElytra.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PacketElytra.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/tx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x64A;
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
                throw new RuntimeException("su/catlean/tx", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PacketElytra.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PacketElytra.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/tx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PacketElytra.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PacketElytra.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

