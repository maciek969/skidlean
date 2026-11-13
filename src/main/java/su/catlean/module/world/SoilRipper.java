/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.CallSite;
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
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.world.SoilRipper$onPlayerUpdate$1;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.SlotResult;
import su.catlean.util.SwapMode;
import su.catlean.util.TickTimer;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class SoilRipper
extends Module {
    @NotNull
    public static final SoilRipper INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting switchMode$delegate;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final TickTimer tickTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    private SoilRipper() {
        super("soil-ripper", Categories.getWorld(), null, null);
    }

    private final SwapMode getSwitchMode() {
        return (SwapMode)((Object)switchMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setSwitchMode(SwapMode swapMode) {
        switchMode$delegate.setValue(this, delegatedProperties[0], (Object)swapMode);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final void setDelay(int n) {
        delay$delegate.setValue(this, delegatedProperties[1], n);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x7AEF8067EEA4L;
        long l2 = l ^ 0x45A01EC054F9L;
        if (tickTimer.hasPassedAndReset(this.getDelay())) {
            BlockPos blockPos;
            Object object = PlaceManager.INSTANCE.findReachableBlocks();
            Iterable iterable = object;
            Collection collection = new ArrayList();
            for (Object t : iterable) {
                Block block;
                Block block2;
                blockPos = (BlockPos)t;
                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos).getBlock(), "getBlock(...)");
                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos.up()).getBlock(), "getBlock(...)");
                if (!((Intrinsics.areEqual(block2, Blocks.GRASS_BLOCK) || Intrinsics.areEqual(block2, Blocks.DIRT) || Intrinsics.areEqual(block2, Blocks.DIRT_PATH)) && Intrinsics.areEqual(block, Blocks.AIR) && Globals.getPlayer().getEyePos().distanceTo(blockPos.toCenterPos().add(0.0, 0.5, 0.0)) < Globals.getPlayer().getBlockInteractionRange())) continue;
                collection.add(t);
            }
            object = (List)collection;
            iterable = object;
            collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(object, 10));
            for (Object t : iterable) {
                blockPos = (BlockPos)t;
                Collection collection2 = collection;
                collection2.add(new BlockHitResult(blockPos.toCenterPos().add(0.0, 0.5, 0.0), Direction.UP, blockPos, false));
            }
            object = (List)collection;
            List list = CollectionsKt.sortedWith(object, new SoilRipper$onPlayerUpdate$1());
            object = InventoryUtil.INSTANCE.findItem(l2, SoilRipper::onPlayerUpdate$lambda$0, this.getSwitchMode());
            if (list.isEmpty() || !((SlotResult)object).isFound()) {
                return;
            }
            BlockHitResult blockHitResult = (BlockHitResult)CollectionsKt.first(list);
            Vec3d vec3d = blockHitResult.getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d, "getLocation(...)");
            RotationManager.INSTANCE.submit(new ActionRotationRequest(Rotation.Companion.to(vec3d), () -> SoilRipper.onPlayerUpdate$lambda$1((SlotResult)object, blockHitResult), null));
        }
    }

    private static final boolean onPlayerUpdate$lambda$0(ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "it");
        return itemStack.getItem() instanceof HoeItem;
    }

    private static final Unit onPlayerUpdate$lambda$1$lambda$0(BlockHitResult blockHitResult) {
        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, blockHitResult);
        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
        return Unit.INSTANCE;
    }

    private static final Unit onPlayerUpdate$lambda$1(SlotResult slotResult, BlockHitResult blockHitResult) {
        Function0<Unit> function0 = () -> SoilRipper.onPlayerUpdate$lambda$1$lambda$0(blockHitResult);
        SwapMode swapMode = INSTANCE.getSwitchMode();
        int n = slotResult.getSlot();
        InventoryUtil.INSTANCE.swapAndRun(n, swapMode, function0);
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SoilRipper.a = ZkmKeys.a(6399819449303119250L, 8299625213245895197L, MethodHandles.lookup().lookupClass()).a(164289998049550L);
                        var20 = SoilRipper.a ^ 137425063108877L;
                        SoilRipper.d = new HashMap<K, V>(13);
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
                        var15_5 = "\u00c7~\u009fzy*0!b#Zo\u0090#E\u00d2\u00ba\u00c5\u00bf\u00cd\u00ec\u001a!w\u0090\u0019\u00dcK\u00e0\u007ft\u00a5\u009c\u00e8J\u008d\u00e2\u00cfm\u00e4Xl8\u0088|\u00c7\u00bb\u00ba\u0080y\u00aeV\u0004\u00d7\u001as\u0010&\u00cd&\u00bc\u00f7z\u0083!\u00d6-G\u008eZ\u00f6\u00fey(\u00e4'<o\u00b9\u0093\u00c6\ty\u0081\u00bcL%\u00cd\u00cf\u0091/\u007f$\u00ae\u009d\u0012\u00d24\u0015m\u0004'5\u0083%k\u00f1Ck\u000b2\u00efRi\u0018\u00f6\"\u0015}\u0015v\u0087\u00d4\u00c6\u00f3\u00a8\u007fQ\u00a8/\u00ded\u00c1\u00e9h4\u00d2\u009c\u00c5\u0018+Q\u00d1\u00fc7;\u008a\u001d\u00d1\u00ae\u00b5~n\u00f6\u009d\u0006@\u000f!\u0018\u00e4gB\u0098\u0010,)\u001b\u00ab\u00fd:\u0086t!\u00e2\u001d\u00b9\u0002\u001cr\u00c9\u0010\u00a5\u00cbq\u001f\u001b\u00c7\u00f6\u00c5\u00dbR\u00a1tZ\u0005j\u0083 \u0004\nG\u00fb)\u0010\u0086\u0012\u0087\u00e6\u0083\u00f4(;Pd\u008d\u00b7\u000f0\u00fd\u00afC\u00ea~\u00a9\u00bb\u001f=\u00eb\u00be\u0082 0l\f1\u008e\u00abz\u00ea6\u00d4\u0002\u008a$\u00e3\u00aazz\u0088nw\u00a6\u009f\u00b2\u0089X=\u00ee\u0080\u00d6+H\u0014";
                        var17_6 = 264;
                        var14_7 = 56;
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
                            var18_3[var16_4++] = SoilRipper.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00dfCQ\u001e\u0094\u00e5\u00b7\u00c0\u00cc\"w\u00b9\u0085X\u00c3\u009a 7\u001a\"\u0098\u00e4b\u00bd\u00ce\u00a5\u008aX\u00f3nG\u00c3\u00f3mqb\u00cd\u00b1#\u0002kE\u0087h\u00891g\u00d9n";
                            var17_6 = 49;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = SoilRipper.zkmDecodeUtf8(var19_9).intern();
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
                SoilRipper.b = var18_3;
                SoilRipper.c = new String[11];
                SoilRipper.h = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u0086|\u0014\u00b7\u00fd\u00e5\u0003\u00e3\u00ec\u0004\b\u00bc#\u00dc\u009b?";
                var5_15 = 16;
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
                    var4_14 = "\u00f5\u0095\u001d\u00f38\r\u0005\u0093\u009f]!\u00c9MV[\u00a1";
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
        SoilRipper.e = var6_12;
        SoilRipper.g = new Integer[4];
        var31_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoilRipper.class, "switchMode", "getSwitchMode()Lsu/catlean/f9;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SoilRipper.class, "delay", "getDelay()I", 0))};
        SoilRipper.delegatedProperties = var31_20;
        SoilRipper.INSTANCE = new SoilRipper();
        SoilRipper.switchMode$delegate = SettingFactory.modeSetting$default((SettingContainer)SoilRipper.INSTANCE, "switch", (Enum)SwapMode.SILENT_FULL, null, null, 12, null);
        SoilRipper.delay$delegate = SettingFactory.intSetting$default(SoilRipper.INSTANCE, "delay", 0, new IntRange(0, 10), null, null, 24, null);
        SoilRipper.tickTimer = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4656;
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
                throw new RuntimeException("su/catlean/kq", exception);
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
            SoilRipper.c[n2] = SoilRipper.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SoilRipper.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4790;
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
                throw new RuntimeException("su/catlean/kq", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SoilRipper.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SoilRipper.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SoilRipper.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SoilRipper.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

