/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.CallSite;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Category;
import su.catlean.module.RotatingModule;
import su.catlean.module.misc.SourceFiller$onPlayerUpdate$1;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.PlaceManager;
import su.catlean.zkm.ZkmKeys;

public final class SourceFiller
extends RotatingModule {
    @NotNull
    public static final SourceFiller INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BlockListSetting blackList$delegate;
    private static final long c;
    private static final String[] h;
    private static final String[] i;
    private static final Map l;
    private static final long[] n;
    private static final Integer[] o;
    private static final Map t;

    private SourceFiller() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 12;
        boolean bl = false;
        int n2 = 0;
        Category category = Categories.getMisc();
        super("source-filler", category, n2, bl, n, defaultConstructorMarker);
    }

    private final BlockListValue getBlackList() {
        return (BlockListValue)blackList$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setBlackList(BlockListValue blockListValue) {
        blackList$delegate.setValue(this, delegatedProperties[0], blockListValue);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        Iterable iterable;
        long l;
        long l2 = l = c ^ 0x586EDA150A40L;
        long l3 = l2 ^ 0x7CBCA12BA193L;
        int n = (int)(l3 >>> 32);
        int n2 = (int)(l3 << 32 >>> 48);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x10951C21D905L;
        BlockPos blockPos = BlockPos.ofFloored(Globals.getPlayer().getEyePos());
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        int n4 = (int)Math.ceil(this.getRange()) + 1;
        List list = new ArrayList();
        Iterable iterable2 = iterable = (Iterable)new IntRange(blockPos2.getX() - n4, blockPos2.getX() + n4);
        Collection collection = new ArrayList();
        Iterator iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            Iterable iterable3;
            int n5;
            int n6 = n5 = ((IntIterator)iterator2).nextInt();
            Iterable iterable4 = iterable3 = (Iterable)new IntRange(blockPos2.getY() - n4, blockPos2.getY() + n4);
            Collection collection2 = new ArrayList();
            Iterator iterator3 = iterable4.iterator();
            while (iterator3.hasNext()) {
                Iterable iterable5;
                int n7;
                int n8 = n7 = ((IntIterator)iterator3).nextInt();
                Iterable iterable6 = iterable5 = (Iterable)new IntRange(blockPos2.getZ() - n4, blockPos2.getZ() + n4);
                Collection collection3 = new ArrayList();
                Iterable iterable7 = iterable6;
                Iterator iterator4 = iterable7.iterator();
                while (iterator4.hasNext()) {
                    Block block;
                    int n9;
                    int n10;
                    int n11 = n10 = (n9 = ((IntIterator)iterator4).nextInt());
                    BlockPos blockPos3 = new BlockPos(n6, n8, n11);
                    Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos3).getBlock(), "getBlock(...)");
                    if (Intrinsics.areEqual(block, Blocks.WATER) || Intrinsics.areEqual(block, Blocks.LAVA)) {
                        FluidState fluidState;
                        Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getFluidState(blockPos3), "getFluidState(...)");
                        if (fluidState.isStill() && INSTANCE.placeAt(blockPos3, n, (short)n2, (short)n3)) {
                            list.add(blockPos3);
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                    collection3.add(unit);
                }
                Iterable iterable8 = (List)collection3;
                CollectionsKt.addAll(collection2, iterable8);
            }
            Iterable iterable9 = (List)collection2;
            CollectionsKt.addAll(collection, iterable9);
        }
        if (!((Collection)list).isEmpty()) {
            iterable = list;
            RotatingModule.s(this, CollectionsKt.sortedWith(iterable, new SourceFiller$onPlayerUpdate$1()), false, false, null, true, 14, l4, null);
        }
    }

    public final boolean placeAt(@NotNull BlockPos blockPos, int n, short s, short s2) {
        long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)s2 << 48 >>> 48) ^ c;
        long l2 = l ^ 0x6716DEE17726L;
        Intrinsics.checkNotNullParameter(blockPos, "pos");
        return PlaceManager.INSTANCE.findPlaceData(blockPos, this.getInteract(), l2, this.getEntityCheck(), this.getRange(), this.getWallRange());
    }

    @Override
    @NotNull
    protected List getBlocks(long l) {
        return this.getBlackList().getBlocks();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        SourceFiller.c = ZkmKeys.a(-3678543627238939688L, 2040876298521781774L, MethodHandles.lookup().lookupClass()).a(31876601137939L);
                        var20 = SourceFiller.c ^ 96445952839412L;
                        SourceFiller.l = new HashMap<K, V>(13);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "\u00e2O\u0012\u00d8\u00a9\u00c0\u00f4\u00b1\u00bc\u0092\u001a1L\t\u00b6\u00b8\u00ca(\"\u00b4\u00c9\u00e1Z\u00f0:\u009e?s\u00ad\u00b6b\u001a\u001eM\u00b3a7\u00d8\bV\u0018\u00028R{;\u00d8\u0093:\u00be\u000f\u00aa\u0018\u00f4\u0015I\u001f\u00d9\u00a3\\\u009d\r\u00cb>\u00d8\u0018X`z\u00cd8!{W\u001aZ\u0092\u00d2\u0001\u00f6Nq\u001c\u00a5F\u0084\u00ea\u00c6\u00d7\u00f0 \u00df\u00c4\u00a0t\u009b\u00d6\u0096B\r\u00aao\u00fb\u009f]\u00d7,\u00ba\u00d4d\u00ef;\u00e0\u00ab\u00e16{Vau3w\u0005\u0010\u00e0\u00dd\u00f1M\u0088X\u00db\u00c0\u00aak\u00c7b\u00a0B\u00d0L \u00a3\u00bb\u0000VZ\u00ab?\u00a5w\u0019\u00b3\u00e0\u0095I\u00af\u00b5,\u0094\u00f4\u00b2\u00e03\u00fb\u00ee\u00b7\"V \u00af9\u00db\u00b0h6\u009eFNNSP|\u0019\u00f13O\u00e4\u00f2\u00e1[\b\u001cg\u00b8\tl\u00bf\u00b6\u008d\bK\n]6N\u0093.\u009b\u0005\u00ddW\u0016S\u00b7\u00b8\u008a\u001a\r\u009c\u00a7\u00caK\u00e4\u00ddP\r\u0099c\\\u0080\u00a4-\u00ddg\u0016\b\u00d11\u00d8x\u00aa\u00197 }\u00a7\u0089(\u0088\u00bc\u00c7\u00ac-\u0099\u00e4\u0091|M\u00c4\u0080\u00bf\u0099\u001f\u00ed\u0017\u0095\u0092\u00bd\u0010\u00a0\u00be\u007f'_\u00ba\u00be\u00a1W";
                        var17_6 = 278;
                        var14_7 = 40;
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
                            var18_3[var16_4++] = SourceFiller.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a3\u00dc\u00c9;\u0087H!\u00ca\u00f9\u00b1`\u00f78o\u0089r]\u00e3\u00d0V\u00eaHV\u00d4 IKEl\u0004\u00f2\u0086\u0081MzD W)\u00c5\u0015W|:\u00d0V\u009b\u0089\r\u0096f\u008a\u000b\u0097\u00f0\u00cds";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = SourceFiller.zkmDecodeUtf8(var19_9).intern();
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
                SourceFiller.h = var18_3;
                SourceFiller.i = new String[9];
                SourceFiller.t = new HashMap<K, V>(13);
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
                var4_14 = "\u00b1o\u008cW3_;\u0011r\u0099\u0015\u00b5O\u0088nL\u0012\u0094d\u00be\u00a3\u00eb\u009d\u009e";
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
        SourceFiller.n = var6_12;
        SourceFiller.o = new Integer[3];
        var34_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(SourceFiller.class, "blackList", "getBlackList()Lsu/catlean/api/setting/values/BlocksValue;", 0))};
        SourceFiller.delegatedProperties = var34_20;
        SourceFiller.INSTANCE = new SourceFiller();
        v11 = SourceFiller.INSTANCE;
        var34_20 = new Block[1];
        Intrinsics.checkNotNullExpressionValue(Blocks.OBSIDIAN, "OBSIDIAN");
        var29_21 = null;
        var31_22 = null;
        var32_23 = null;
        var33_24 = new BlockListValue(CollectionsKt.mutableListOf(var34_20));
        SourceFiller.blackList$delegate = SettingFactory.blockListSetting(v11, "black-list", var33_24, var32_23, var31_22, var29_21);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x35D2;
        if (i[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])SourceFiller.l.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    SourceFiller.l.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_m", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = h[n2].getBytes("ISO-8859-1");
            SourceFiller.i[n2] = SourceFiller.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return i[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SourceFiller.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_m" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7914;
        if (o[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = SourceFiller.n[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])t.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    t.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_m", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SourceFiller.o[n2] = n3;
        }
        return o[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SourceFiller.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_m" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SourceFiller.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SourceFiller.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

