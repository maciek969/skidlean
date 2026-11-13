/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class Burrow
extends Module {
    @NotNull
    public static final Burrow INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting once$delegate;
    @Nullable
    private static BlockPos startPos;
    @NotNull
    private static final Timer cooldown;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    private Burrow() {
        super("burrow", Categories.getProtect(), null, null);
    }

    private final boolean getOnce() {
        return (Boolean)once$delegate.getValue(this, delegatedProperties[0]);
    }

    @Override
    public void onEnable(long l) {
        Entity entity = Globals.getPlayer();
        startPos = BlockPos.ofFloored(this.getPos(entity));
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        int n;
        long l = a ^ 0x6DE2DB67843FL;
        long l2 = l ^ 0x50B521968371L;
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        Entity entity = Globals.getPlayer();
        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        if (!this.getOnce() && !Intrinsics.areEqual(blockPos2, startPos)) {
            this.disable(l2);
            return;
        }
        Object object = new Block[1];
        Intrinsics.checkNotNullExpressionValue(Blocks.OBSIDIAN, "OBSIDIAN");
        SlotResult slotResult = InventoryUtil.INSTANCE.findBlockInHotbar((Block[])object);
        Block[] blockArray = new Block[1];
        Intrinsics.checkNotNullExpressionValue(Blocks.ENDER_CHEST, "ENDER_CHEST");
        object = InventoryUtil.INSTANCE.findBlockInHotbar(blockArray);
        int n2 = n = !slotResult.isFound() ? ((SlotResult)object).getSlot() : slotResult.getSlot();
        if (n == -1 || Globals.getMc().isInSingleplayer()) {
            this.disable(l2);
            return;
        }
        if (cooldown.hasPassed(1000)) {
            float f = Globals.getPlayer().getPitch();
            float f2 = 90.0f - f;
            this.sendPosition(0.42, f + f2 * 0.25f);
            this.sendPosition(0.75, f + f2 * 0.5f);
            this.sendPosition(1.01, f + f2 * 0.75f);
            this.sendPosition(1.16, f + f2);
            InventoryUtil.INSTANCE.switchToSlot(n);
            PacketUtil.sendSequencedPacket(arg_0 -> Burrow.onPlayerUpdate$lambda$0(blockPos2, arg_0));
            PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
            InventoryUtil.INSTANCE.restoreSelectedSlot();
            PacketUtil.sendPacket(new PlayerMoveC2SPacket.Full(Globals.getPlayer().getX(), Globals.getPlayer().getY() + 1.0, Globals.getPlayer().getZ(), Globals.getPlayer().getYaw(), 90.0f, true, Globals.getPlayer().horizontalCollision));
            cooldown.reset();
            if (this.getOnce()) {
                this.disable(l2);
            }
        }
    }

    private final void sendPosition(double d, float f) {
        PacketUtil.sendPacket(new PlayerMoveC2SPacket.Full(Globals.getPlayer().getX(), Globals.getPlayer().getY() + d, Globals.getPlayer().getZ(), Globals.getPlayer().getYaw(), f, false, Globals.getPlayer().horizontalCollision));
    }

    private static final Packet onPlayerUpdate$lambda$0(BlockPos blockPos, int n) {
        return new PlayerInteractBlockC2SPacket(Hand.MAIN_HAND, new BlockHitResult(blockPos.toCenterPos().add(0.0, -0.5, 0.0), Direction.UP, blockPos.down(), false), n);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        Burrow.a = ZkmKeys.a(6052444199711677721L, 4870263944490121535L, MethodHandles.lookup().lookupClass()).a(113119130375600L);
                        var20 = Burrow.a ^ 14482307244927L;
                        Burrow.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[7];
                        var16_4 = 0;
                        var15_5 = "\u0089`\u00be\u00ad\u001e^\u00be\u001f\u00f7\u0010\u00b6B)7\u00b3\u00e5qs\u0016\u00dec\u00f56\u00a1 \u00c3\u000b\u0083]}9Y\u00c9M,\u0019\u00f3\u00dd\u0092x\u00a8D{\u00c7\u0016v\f\u00c4\u001c\r\u0018\u00aa_\u00e4\u00f7\u00ca\u0018\u0010(\u00b7\u00cd\tz\u00ab\u00f3\u00c9\u001f\u00e1O\u00d8zye\u00a9\u0010\u00f2\u00d3;\u00b2\u00a2\t\u0004$\u0099\u00c0\u00fc\u001d\u00c7\u00b4\u00d4A\u0018\u0003Q~\u0092\u00c4\u00aaBM\u00fb\rb\u0081\u0012\u00ce~\u00c1@\u00d3ed\nK7\u00e8";
                        var17_6 = 116;
                        var14_7 = 24;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Burrow.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "}.\u00cb\u00df\u00ef\u00e2g\u008aR\u00d2\u00ce\u008d\u0019\u0082\u0002Tn\u00da\u0087\u0082b\u00fe\u00a4Q\u00b3\u00fb\u00c9\u00ba\u0099XI6\u0010\u001d\u000b\u0016\u0004=\u009f\u000b\u0094`\u0014!\u00b2\u00df\u00db~\u00c7";
                            var17_6 = 49;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Burrow.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                Burrow.b = var18_3;
                Burrow.c = new String[7];
                Burrow.h = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u0093\u001c\u00e0k;6@6\u00e6@\u00a1\u00b1\u0007\u00a5\u008b\u00b9pW\u0088`,*\u00b7r";
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
        Burrow.e = var6_12;
        Burrow.g = new Integer[3];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Burrow.class, "once", "getOnce()Z", 0))};
        Burrow.delegatedProperties = var26_20;
        Burrow.INSTANCE = new Burrow();
        Burrow.once$delegate = SettingFactory.booleanSetting$default((SettingContainer)Burrow.INSTANCE, "once", true, null, null, 12, null);
        Burrow.cooldown = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x102A;
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
                throw new RuntimeException("su/catlean/q8", exception);
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
            Burrow.c[n2] = Burrow.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Burrow.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x28A9;
        if (g[n2] == null) {
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
                throw new RuntimeException("su/catlean/q8", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Burrow.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Burrow.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Burrow.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Burrow.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

