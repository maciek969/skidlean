/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

import java.awt.Color;
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
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItem;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.render.Render3DEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.player.NoDelay;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ColorSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AirPlace
extends Module {
    @NotNull
    public static final AirPlace INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting bypass$delegate;
    @NotNull
    private static final FloatSetting range$delegate;
    @NotNull
    private static final ColorSetting color$delegate;
    @Nullable
    private static BlockHitResult targetHit;
    private static int T;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    private AirPlace() {
        super("air-place", Categories.getPlayer(), null, null);
    }

    public final boolean getBypass() {
        boolean bl;
        block0: {
            long l = 0L;
            int n = 0;
            long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)1839659887091561685L, (long)l2);
            bl = (Boolean)bypass$delegate.getValue(this, delegatedProperties[0]);
            if (callSite != null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new Module[1], (long)1752692932059665007L, (long)l2);
        }
        return bl;
    }

    private final float getRange() {
        return ((Number)range$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final Color getColor() {
        return (Color)color$delegate.getValue(this, delegatedProperties[2]);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (T > 0) {
            int n = T;
            T = n + -1;
        }
        Entity entity = Globals.getMc().getCameraEntity();
        Intrinsics.checkNotNull(entity);
        HitResult hitResult = entity.raycast(this.getRange(), 0.0f, false);
        Intrinsics.checkNotNullExpressionValue(hitResult, "pick(...)");
        HitResult hitResult2 = hitResult;
        if (!(hitResult2 instanceof BlockHitResult)) {
            return;
        }
        targetHit = (BlockHitResult)hitResult2;
        ClientWorld clientWorld = Globals.getWorld();
        BlockHitResult blockHitResult = targetHit;
        Intrinsics.checkNotNull(blockHitResult);
        if (!clientWorld.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(Blocks.AIR)) {
            return;
        }
        boolean bl = Globals.getPlayer().getMainHandStack().getItem() instanceof BlockItem;
        boolean bl2 = Globals.getPlayer().getOffHandStack().getItem() instanceof BlockItem;
        if (Globals.getMc().options.useKey.isPressed() && (bl || bl2) && T <= 0) {
            if (this.getBypass()) {
                InteractionUtil.useItem$default(bl ? Hand.MAIN_HAND : Hand.OFF_HAND, 0.0f, 0.0f, 6, null);
                PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
                ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                Hand hand = bl ? Hand.MAIN_HAND : Hand.OFF_HAND;
                BlockHitResult blockHitResult2 = targetHit;
                Intrinsics.checkNotNull(blockHitResult2);
                clientPlayerInteractionManager.interactBlock(clientPlayerEntity, hand, blockHitResult2);
                Globals.getPlayer().swingHand(bl ? Hand.MAIN_HAND : Hand.OFF_HAND);
                PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
                Globals.getPlayer().swingHand(bl ? Hand.MAIN_HAND : Hand.OFF_HAND);
            } else {
                ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getInteractionManager();
                ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
                Hand hand = bl ? Hand.MAIN_HAND : Hand.OFF_HAND;
                BlockHitResult blockHitResult3 = targetHit;
                Intrinsics.checkNotNull(blockHitResult3);
                clientPlayerInteractionManager.interactBlock(clientPlayerEntity, hand, blockHitResult3);
                Globals.getPlayer().swingHand(bl ? Hand.MAIN_HAND : Hand.OFF_HAND);
            }
            T = NoDelay.INSTANCE.getEnabled() && (NoDelay.INSTANCE.getBlocks() || NoDelay.INSTANCE.getAll()) ? 0 : 4;
        }
    }

    @Flow
    private final void onRender3D(Render3DEvent render3DEvent) {
        block1: {
            BlockHitResult blockHitResult = targetHit;
            if (blockHitResult == null) break block1;
            BlockHitResult blockHitResult2 = blockHitResult;
            boolean bl = Globals.getPlayer().getMainHandStack().getItem() instanceof BlockItem;
            boolean bl2 = Globals.getPlayer().getOffHandStack().getItem() instanceof BlockItem;
            Box box = new Box(blockHitResult2.getBlockPos());
            if (!Globals.getWorld().getBlockState(blockHitResult2.getBlockPos()).getBlock().equals(Blocks.AIR) || !bl && !bl2) {
                return;
            }
            Object var24_6 = null;
            Color color = null;
            Color color2 = INSTANCE.getColor();
            Box box2 = box;
            RenderState renderState = RenderState.INSTANCE;
            RenderState.addFilledBox$default(renderState, box2, color2, color, var24_6);
            Object var32_11 = null;
            Color color3 = null;
            Color color4 = RenderUtil.INSTANCE.withAlpha(INSTANCE.getColor(), 1.0f);
            Box box3 = box;
            RenderState.addOutlineBox$default(RenderState.INSTANCE, box3, color4, color3, var32_11);
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
                        AirPlace.a = ZkmKeys.a(-1836675845498752649L, -3731528914218140755L, MethodHandles.lookup().lookupClass()).a(52720611767574L);
                        var20 = AirPlace.a ^ 9552620376485L;
                        AirPlace.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[11];
                        var16_4 = 0;
                        var15_5 = "\u00e6E\u00015/\u00df\u00eb!\u0093J'\u00ff\u00cf\u0017\\|\u0018\u00c6r\u00b4\u00f6\u0012Y\u0089\u008fe\u0090\u00ff\u001cY/\u00ff\u0089)\u000f,^\u007f\u00b8\u0019\u0007\u0018n[\u00f7,i3\u00c0\b\u000b.\u001e\u00d4\u00e8\u00ad\u0095qZrs\u00d5~$F\u00be\u0010\t\u00ec\u0083\u0089\u00ea\u00be\u00ea\u00c7\u00a5E\u00f7n\u00c1\u00c6A\u00e4\u0018\u00cd#\u009c*\u0007\u0098\u00c8\u00f9(\u0090\u008bD\u0081\f\u008c)\u0094\u0093\u001b\u00d6\u0000\u009fh\r\u0010P6y\u0004,E=J\u00bf\u00f7\u008d\u00dd\u0001V\b\u00bf8I\u00ae\u009bj-\u00c7\u009b\u00be\u00a4]\u008cs<)m\u008c6s`R\u00ff\u00ec\u00f0}SF\u008b=\u0098\u00f6\u009a\u0086&\u008e\u0080\u00c6\u0083\u00caF\u00bbq\u00a7\u00bd\u0089\u00d1\u00fe\u00aa \u00f3/\u00c6\u009c\u00b9.\u00dd\u00a1 8\u009c\u00ed\u00b0\u00cd\u000e\u00c9\u00ee\u00ce\u0097\u00f3;\u00a5\u00a3E\u0083\u009f\u001d\f\u001f\u0086\u00e12g\u00aeu\u00dc\u0085w\u00f2\u0098O\u0010\u00cb\u00c6{\u0089\u00b0\u0012ts\u00b9\u00d8\u0099\u009ao\u00fcCN";
                        var17_6 = 232;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = AirPlace.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = ":\u00ec\u00bf\u0099\u00df\u00d8\u00b3f7\\\u00b2/\u00be\u00a3\u00e1\u0015\u00e8\u009e\u00c0\u0091Y+\u00e2\u00c8 \u0095\"\u00fc\tQ\u00b5+\u00daoKZ\u00ca\u00ff\u0085WK\u00fd\u008f2\u0015\u00c1\u008c5Q\u00d7\n\"\u0017G\u00de\u00d2\u0015";
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
                            var18_3[var16_4++] = AirPlace.zkmDecodeUtf8(var19_9).intern();
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
                AirPlace.b = var18_3;
                AirPlace.c = new String[11];
                AirPlace.h = new HashMap<K, V>(13);
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
                var4_14 = "\u0086T\u0015g\u00d4\u00acN\u00df\u00897\u0081\u0016\u001e\u009c\u00a8\u0019R\u00e4\u00cfw\u001a\u00a7sw\u00138\u00de\u0082\u001bZ\u009f\u00be\u00ef\u00b7\u00f9:\u00e2d$%\u0015\u00ffx\u00c3\u0017UaV";
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
                    var4_14 = "W\u0086\u0086\u00c6\u000f\u00c4\u001aq%S\u00ff<\u00b4\u000f0\u00d9";
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
        AirPlace.e = var6_12;
        AirPlace.g = new Integer[8];
        var32_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AirPlace.class, "bypass", "getBypass()Z", 0)), Reflection.property1(new PropertyReference1Impl(AirPlace.class, "range", "getRange()F", 0)), Reflection.property1(new PropertyReference1Impl(AirPlace.class, "color", "getColor()Ljava/awt/Color;", 0))};
        AirPlace.delegatedProperties = var32_20;
        AirPlace.INSTANCE = new AirPlace();
        AirPlace.bypass$delegate = SettingFactory.booleanSetting$default((SettingContainer)AirPlace.INSTANCE, "2b2t", true, null, null, 12, null);
        AirPlace.range$delegate = SettingFactory.floatSetting$default(AirPlace.INSTANCE, "range", 5.0f, RangesKt.rangeTo(0.0f, 6.0f), null, null, 24, null);
        AirPlace.color$delegate = SettingFactory.colorSetting$default(AirPlace.INSTANCE, "fill--color", new Color(100, 50, 255, 50), null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2706;
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
                throw new RuntimeException("su/catlean/ep", exception);
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
            AirPlace.c[n2] = AirPlace.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AirPlace.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ep" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6C3C;
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
                throw new RuntimeException("su/catlean/ep", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AirPlace.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AirPlace.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ep" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AirPlace.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AirPlace.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

