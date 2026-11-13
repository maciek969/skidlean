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
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RaycastContext;
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
import su.catlean.module.rage.ElytraPlus;
import su.catlean.ui.NotificationType;
import su.catlean.ui.Notifications;
import su.catlean.util.ElytraListener;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class InfiniteElytra
implements ElytraListener {
    @NotNull
    public static final InfiniteElytra INSTANCE;
    private static boolean p;
    private static float pitchOffset;
    private static float targetPitch;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    private InfiniteElytra() {
    }

    public final boolean I() {
        return p;
    }

    public final void a(boolean bl) {
        p = bl;
    }

    public final float getPitchOffset() {
        return pitchOffset;
    }

    public final void setPitchOffset(float f) {
        pitchOffset = f;
    }

    public final float getTargetPitch() {
        return targetPitch;
    }

    public final void setTargetPitch(float f) {
        targetPitch = f;
    }

    @Override
    public void onPreSync(long l, @NotNull PreSyncEvent preSyncEvent) {
        Intrinsics.checkNotNullParameter(preSyncEvent, "e");
        ItemStack itemStack = Globals.getPlayer().getEquippedStack(EquipmentSlot.CHEST);
        Intrinsics.checkNotNullExpressionValue(itemStack, "getItemBySlot(...)");
        ItemStack itemStack2 = itemStack;
        if (itemStack2.isOf(Items.ELYTRA)) {
            Globals.getPlayer().setPitch(targetPitch);
            if (itemStack2.getDamage() > 380 && Globals.getPlayer().age % 100 == 0) {
                Notifications.push$default(Notifications.INSTANCE, "Elytra+", ElytraPlus.INSTANCE.translate("elytra-durability", new Object[0]), 2, NotificationType.WARNING, null, 16, null);
                ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                Globals.getWorld().playSound((Entity)clientPlayerEntity, clientPlayerEntity.getX(), clientPlayerEntity.getY(), clientPlayerEntity.getZ(), (RegistryEntry<SoundEvent>)SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 10.0f, 1.0f, 0L);
            }
        }
    }

    @Override
    public void onPreElytra(@NotNull PreElytraEvent preElytraEvent, long l) {
        Intrinsics.checkNotNullParameter(preElytraEvent, "e");
        ElytraPlus.INSTANCE.setSavedPitch(Globals.getPlayer().getPitch());
        Globals.getPlayer().setPitch(targetPitch);
    }

    @Override
    public void onAfterElytra(@NotNull AfterElytraEvent afterElytraEvent) {
        Intrinsics.checkNotNullParameter(afterElytraEvent, "e");
        Globals.getPlayer().setPitch(ElytraPlus.INSTANCE.getSavedPitch());
    }

    @Override
    public void onMove(@NotNull MoveEvent moveEvent, long l) {
        Intrinsics.checkNotNullParameter(moveEvent, "e");
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
        long l = (long)n << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48;
        long l2 = l ^ 0x42A6B0668CB7L;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 16 >>> 48);
        int n4 = (int)(l2 << 32 >>> 32);
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        targetPitch = this.computePitch((char)n2, (short)n3, n4);
    }

    @Override
    public void onEnable(char c, int n, short s) {
        long l = (long)c << 48 | (long)n << 32 >>> 16 | (long)s << 48 >>> 48;
        long l2 = l ^ 0x5AB7466E53ADL;
        if (Globals.getPlayer().getY() < (double)ElytraPlus.INSTANCE.getInfiniteMaxHeight()) {
            ElytraPlus.INSTANCE.disableNeedHeight(l2);
        }
        p = false;
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

    private final float computePitch(char c, short s, int n) {
        CallSite callSite;
        int n2;
        int n3;
        int n4;
        long l;
        block20: {
            block19: {
                int n5;
                block17: {
                    block18: {
                        double d;
                        block12: {
                            block13: {
                                CallSite callSite2;
                                block16: {
                                    block14: {
                                        float f;
                                        block15: {
                                            l = ((long)c << 48 | (long)s << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
                                            long l2 = l ^ 0x57216210DB6FL;
                                            n4 = (int)(l2 >>> 48);
                                            n3 = (int)(l2 << 16 >>> 48);
                                            n2 = (int)(l2 << 32 >>> 32);
                                            callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)1944596784758719011L, (long)l);
                                            double d2 = Globals.getPlayer().getY() - (double)ElytraPlus.INSTANCE.getInfiniteMaxHeight();
                                            d = d2 == 0.0 ? 0 : (d2 < 0.0 ? -1 : 1);
                                            if (callSite != null) break block12;
                                            if (d >= 0) break block13;
                                            float f2 = MovementUtil.INSTANCE.getHorizontalSpeed() * 72.0f - 25.0f;
                                            n5 = f2 == 0.0f ? 0 : (f2 < 0.0f ? -1 : 1);
                                            if (callSite != null) break block14;
                                            if (n5 >= 0) break block15;
                                            n5 = p ? 1 : 0;
                                            callSite2 = callSite;
                                            if (s < 0) break block16;
                                            if (callSite2 != null) break block14;
                                            if (n5 == 0) {
                                                p = true;
                                            }
                                        }
                                        n5 = (f = MovementUtil.INSTANCE.getHorizontalSpeed() * 72.0f - 150.0f) == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                                    }
                                    callSite2 = callSite;
                                }
                                if (callSite2 != null) break block17;
                                if (n5 <= 0) break block18;
                                n5 = p ? 1 : 0;
                                if (c < '\u0000' || callSite != null) break block17;
                                if (n5 == 0) break block18;
                                p = false;
                                if (callSite == null) break block18;
                            }
                            d = 1.0;
                        }
                        p = d;
                    }
                    n5 = p;
                }
                if (n5 == 0) break block19;
                pitchOffset += 3.0f;
                if (callSite == null) break block20;
            }
            pitchOffset -= 3.0f;
        }
        BlockHitResult blockHitResult = Globals.getWorld().raycast(new RaycastContext(Globals.getPlayer().getEyePos(), Globals.getPlayer().getEyePos().add(Globals.getPlayer().getVelocity().x * 10.0, Globals.getPlayer().getVelocity().y * 10.0, Globals.getPlayer().getVelocity().z * 10.0), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer()));
        Intrinsics.checkNotNullExpressionValue(blockHitResult, (String)((Object)InfiniteElytra.zkmBootstrap("c", (int)6527, (long)(0xB6FF82A43B1C459L ^ l))));
        BlockHitResult blockHitResult2 = blockHitResult;
        if (callSite == null) {
            if (blockHitResult2.getType() == HitResult.Type.BLOCK) {
                Globals.getPlayer().setPitch(-33.0f);
                ElytraPlus.access$disableWithReason(ElytraPlus.INSTANCE, (short)n4, null, (char)n3, null, n2);
            }
            pitchOffset = Math.clamp(pitchOffset, -40.0f, 40.0f);
        }
        return pitchOffset;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        InfiniteElytra.a = ZkmKeys.a(-6609135483615907518L, -6898001304243865689L, MethodHandles.lookup().lookupClass()).a(68990957462844L);
                        InfiniteElytra.d = new HashMap<K, V>(13);
                        var11 = InfiniteElytra.a ^ 61962917455985L;
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
                        var20_3 = new String[5];
                        var18_4 = 0;
                        var17_5 = "G(\u000bjP\u00b5\u0098[\u0013\u00ab\u0099oF\u00b0\u00a8!(\u00cbxV\u00aa\\X\u00e8\u00ee\u001f\u00ff\u00b8qx\u00f4\u00c5E\\p\u0010\u0000,\u00db\"\u00d5V\u00d9\u00de7\u0000\u00eb\u00e6z\u00c7\u0088\u00fa\u0014L\u00d7\u00a2g \u00d9\u00c7\u00f3r*\u0001\u00ce|p\u00bc\u00eb*\u00a5t.\u00d8}P,\u00e0\t_\u00f5\ra\u0094\u009e\u00f8\u00c8\u0097\u009e\u0010";
                        var19_6 = 90;
                        var16_7 = 16;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = InfiniteElytra.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00daZ\u000f\u0082\u009b\u001e2CIX\u0001\u00ab<6;\u00f00\u00b8M;\u0015\u00d2\u0098\u00c8\u00d4\u0087\u00d2(p\u000b$\u00a9f\u001a\u00b3\u00eb\u00ad\u009b1\u00bb~$+\u0019p\u0019\u00eb}\u00fd\u008a<\"\u00cf\u00a0\u00f6h\u00df\u00cc\u001e\u0084\u009f[B\u0092D";
                            var19_6 = 65;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = InfiniteElytra.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                InfiniteElytra.b = var20_3;
                InfiniteElytra.c = new String[5];
                InfiniteElytra.h = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u001e\u00c06\u0000\u0004sTS\u0000\u0088`\u00d0\u0083WxI7] \u00d3>\u0085\u00a7=";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        InfiniteElytra.e = var6_12;
        InfiniteElytra.f = new Integer[3];
        InfiniteElytra.INSTANCE = new InfiniteElytra();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x46A3;
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
                throw new RuntimeException("su/catlean/l3", exception);
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
            InfiniteElytra.c[n2] = InfiniteElytra.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = InfiniteElytra.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/l3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x153D;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/l3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            InfiniteElytra.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = InfiniteElytra.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/l3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(InfiniteElytra.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(InfiniteElytra.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

