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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.PotionSlot;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.util.rotation.RotationRequest;
import su.catlean.zkm.ZkmKeys;

public final class Quiver
extends Module {
    @NotNull
    public static final Quiver INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private Quiver() {
        super("Quiver", Categories.getProtect(), null, null);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block2: {
            int n;
            block3: {
                boolean bl;
                block1: {
                    bl = PotionSlot.w();
                    n = Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.BOW);
                    if (bl) break block1;
                    if (n == 0) break block2;
                    n = Globals.getPlayer().getItemUseTime();
                }
                if (bl) break block3;
                if (n < 4) break block2;
                n = this.findArrows() ? 1 : 0;
            }
            if (n != 0) {
                RotationManager.INSTANCE.submit(new RotationRequest(new Rotation(Globals.getPlayer().getPitch(), -90.0f, false, null, 12, null), 0, Quiver::onPlayerUpdate$lambda$0));
            }
        }
    }

    private final boolean findArrows() {
        Object e;
        int n;
        Iterable iterable = new IntRange(0, 35);
        Object object32 = iterable;
        Iterator iterator2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Object object2 = object32.iterator();
        while (object2.hasNext()) {
            PotionSlot potionSlot;
            ItemStack itemStack;
            int n2;
            n = n2 = ((IntIterator)object2).nextInt();
            Collection collection = iterator2;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(n), "getItem(...)");
            if (itemStack.getItem() instanceof ArrowItem) {
                Iterable<StatusEffectInstance> iterable2 = itemStack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT).getEffects();
                Intrinsics.checkNotNullExpressionValue(iterable2, "getAllEffects(...)");
                potionSlot = new PotionSlot(n, CollectionsKt.firstOrNull(iterable2));
            } else {
                potionSlot = null;
            }
            collection.add(potionSlot);
        }
        List<Object> list = CollectionsKt.filterNotNull((List)((Object)iterator2));
        if (list.isEmpty()) {
            return false;
        }
        Iterable iterable3 = list;
        iterator2 = iterable3.iterator();
        if (!iterator2.hasNext()) {
            throw new NoSuchElementException();
        }
        Object e2 = iterator2.next();
        if (!iterator2.hasNext()) {
            e = e2;
        } else {
            object2 = (PotionSlot)e2;
            int n3 = ((PotionSlot)object2).getSlot();
            do {
                Object e3;
                PotionSlot potionSlot;
                if (n3 <= (n = (potionSlot = (PotionSlot)(e3 = iterator2.next())).getSlot())) continue;
                e2 = e3;
                n3 = n;
            } while (iterator2.hasNext());
            e = e2;
        }
        int n4 = ((PotionSlot)e).getSlot();
        for (Object object32 : list) {
            if (!this.shootArrow((PotionSlot)object32)) continue;
            if (((PotionSlot)object32).getSlot() != n4) {
                InteractionUtil.clickSlot$default(((PotionSlot)object32).getSlot(), 0, null, false, 14, null);
                InteractionUtil.clickSlot$default(n4, 0, null, false, 14, null);
                InteractionUtil.clickSlot$default(((PotionSlot)object32).getSlot(), 0, null, true, 6, null);
            }
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     */
    private final boolean shootArrow(PotionSlot var1_1) {
        block11: {
            var4_2 = PotionSlot.w();
            if (var4_2) ** GOTO lbl-1000
            v0 = var1_1.getEffect();
            if (v0 != null) {
                v1 = v0.getEffectType();
            } else lbl-1000:
            // 2 sources

            {
                v1 = null;
            }
            if (!Intrinsics.areEqual(v1, StatusEffects.STRENGTH)) break block11;
            v2 = Globals.getMc().player;
            if (v2 != null) {
                v3 = v2.hasStatusEffect(StatusEffects.STRENGTH);
                if (!var4_2) {
                    v3 = v3 == false;
                }
                v4 = v3;
            } else {
                v4 = false;
            }
            if (v4) ** GOTO lbl-1000
        }
        v5 = var1_1.getEffect();
        if (Intrinsics.areEqual(v5 != null ? v5.getEffectType() : null, StatusEffects.SPEED)) {
            v6 = Globals.getMc().player;
            if (v6 != null) {
                v7 = v6.hasStatusEffect(StatusEffects.SPEED);
                if (!var4_2) {
                    v7 = v7 == false;
                }
                v8 = v7;
            } else {
                v8 = false;
            }
            ** if (!v8) goto lbl-1000
        }
        ** GOTO lbl-1000
lbl-1000:
        // 2 sources

        {
            v9 = true;
            ** GOTO lbl34
        }
lbl-1000:
        // 2 sources

        {
            v9 = false;
        }
lbl34:
        // 2 sources

        return v9;
    }

    private static final Unit onPlayerUpdate$lambda$0() {
        PacketUtil.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Globals.getPlayer().getHorizontalFacing()));
        Globals.getPlayer().stopUsingItem();
        return Unit.INSTANCE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    Quiver.a = ZkmKeys.a(-7323972480482300541L, -6484860920814028689L, MethodHandles.lookup().lookupClass()).a(101014859480704L);
                    var20 = Quiver.a ^ 128838224164739L;
                    Quiver.d = new HashMap<K, V>(13);
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
                    var18_3 = new String[3];
                    var16_4 = 0;
                    var15_5 = "\u00d2\u0096\u0081*\u00ab|\u00b37[V\u00d4\u00a1\u00e6v\u001fO|}\u007f\u0098%\u00fd\u00d6I\u00b6\\N\u00cf\u00f3\u0093m\u00a6(\u0088J\u00f4\u00bd\u00da\ts\u00eb\u00af\u00bf\u0080\u00cf\u0099\u0011\u00ab\u00f386\u0005\u0086\u00cf\u000f~f1\u007f\u00e1h\u00e5;\u00d2\u0004_\u00810\u00a9\u00fb\u000e+\u00bd\u0010\u00d2\u00e2\u00c6y\u00fd\u0092\u001c\u009e5b\u00e0\u00f1\u00b3\u00a5S\u00da";
                    var17_6 = 90;
                    var14_7 = 32;
                    var13_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        continue;
                        break;
                    }
lbl22:
                    // 1 sources

                    while (true) {
                        var18_3[var16_4++] = Quiver.zkmDecodeUtf8(var19_9).intern();
                        if ((var13_8 += var14_7) < var17_6) {
                            var14_7 = var15_5.charAt(var13_8);
                            ** continue;
                        }
                        break block12;
                        break;
                    }
                    v3 = ++var13_8;
                    var19_9 = var11_1.doFinal(var15_5.substring(v3, v3 + var14_7).getBytes("ISO-8859-1"));
                    ** while (true)
                }
                Quiver.b = var18_3;
                Quiver.c = new String[3];
                Quiver.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v4 = SecretKeyFactory.getInstance("DES");
                v5 = new byte[8];
                v6 = v5;
                v5[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v6 = v6;
                    v6[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v4.generateSecret(new DESKeySpec(v6)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "\u0016\u0087^\u0010\u0095\u0017\u00b6#.\u00ab\u008b\u00be\u00f2?wyT\u0002G\u00d4'a\u00a9(\u00d9B\u00cf\u00d9\u00acn\u00b1M";
                var5_15 = 32;
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
lbl57:
                // 1 sources

                while (true) {
                    v7[v8] = v11;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "w\u00adK\u00b4\u00df\u0002\u0017x\u00ce9\u0090\u0005\u009d'-\u008a";
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
lbl70:
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
lbl83:
                // 1 sources

                ** continue;
            }
        }
        Quiver.e = var6_12;
        Quiver.f = new Integer[6];
        Quiver.INSTANCE = new Quiver();
    }

    private static NoSuchElementException zkmRethrow(NoSuchElementException noSuchElementException) {
        return noSuchElementException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3EA3;
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
                throw new RuntimeException("su/catlean/qh", exception);
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
            Quiver.c[n2] = Quiver.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Quiver.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6F30;
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
                throw new RuntimeException("su/catlean/qh", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Quiver.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Quiver.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Quiver.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Quiver.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

