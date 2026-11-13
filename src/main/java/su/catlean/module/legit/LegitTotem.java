/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.legit;

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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import net.minecraft.client.Mouse;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Nameable;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.interfaces.IMouse;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.RandomUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.SlotResult;
import su.catlean.zkm.ZkmKeys;

public final class LegitTotem
extends Module {
    @NotNull
    public static final LegitTotem INSTANCE;
    private static int a;
    private static int F;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private LegitTotem() {
        super("legit-totem", Categories.getLegit(), null, null);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block7: {
            int n;
            boolean bl = Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.TOTEM_OF_UNDYING);
            SlotResult slotResult = this.findTotemSlots();
            if (!slotResult.isFound()) {
                return;
            }
            if (a > 0) {
                n = a;
                a = n + 1;
            }
            if (!bl && a == 0) {
                F = RandomUtil.P(2, 4, false, null);
                n = a;
                a = n + 1;
            }
            if (Globals.getMc().currentScreen != null && !bl) {
                float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f - 80.0f;
                float f2 = (float)Globals.getMc().getWindow().getScaledHeight() / 2.0f;
                int n2 = slotResult.getSlot() % 9;
                int n3 = slotResult.getSlot();
                int n4 = 9;
                int n5 = n3 / n4;
                if ((n3 ^ n4) < 0 && n5 * n4 != n3) {
                    --n5;
                }
                int n6 = n5 - 1;
                float f3 = f + (float)(n2 * 20);
                float f4 = f2 + (float)(n6 * 20);
                double d = (double)HudManager.INSTANCE.getMouseX() + (double)(f3 - HudManager.INSTANCE.getMouseX()) * 0.6;
                double d2 = (double)HudManager.INSTANCE.getMouseY() + (double)(f4 - HudManager.INSTANCE.getMouseY()) * 0.6;
                int n7 = Globals.getMc().getWindow().getScaleFactor();
                InputUtil.setCursorParameters(Globals.getMc().getWindow(), 212993, d * (double)n7, d2 * (double)n7);
                Mouse mouse = Globals.getMc().mouse;
                Intrinsics.checkNotNull(mouse, "null cannot be cast to non-null type su.catlean.interfaces.IMouse");
                ((IMouse)((Object)mouse)).setPosition((int)(d * (double)n7), (int)(d2 * (double)n7));
            }
            if (a < F) break block7;
            if (Globals.getMc().currentScreen == null) {
                Globals.getMc().getTutorialManager().onInventoryOpened();
                Globals.getMc().setScreen(new InventoryScreen(Globals.getPlayer()));
                F += RandomUtil.P(2, 4, false, null);
                MovementUtil.INSTANCE.releaseMovementKeys();
            } else {
                InteractionUtil.clickSlot$default(slotResult.getSlot(), 40, SlotActionType.SWAP, false, 8, null);
                Scheduler.schedule$default(Scheduler.INSTANCE, RandomUtil.P(2, 4, false, null), (byte)0, false, LegitTotem::onPlayerUpdate$lambda$0, 6, null);
                a = 0;
            }
        }
    }

    private final SlotResult findTotemSlots() {
        List list = new ArrayList();
        for (int i = 36; -1 < i; --i) {
            ItemStack itemStack;
            Nameable nameable = Globals.getMc().player;
            ItemStack itemStack2 = itemStack = nameable != null && (nameable = ((PlayerEntity)nameable).getInventory()) != null ? ((PlayerInventory)nameable).getStack(i) : null;
            if (!Intrinsics.areEqual(itemStack2 != null ? itemStack2.getItem() : null, Items.TOTEM_OF_UNDYING)) continue;
            ItemStack itemStack3 = itemStack;
            boolean bl = true;
            int n = i < 9 ? i + 36 : i;
            list.add(new SlotResult(n, bl, itemStack3));
        }
        if (!((Collection)list).isEmpty()) {
            return (SlotResult)CollectionsKt.random(list, Random.Default);
        }
        return SlotResult.Companion.notFound();
    }

    private static final void onPlayerUpdate$lambda$0() {
        long l = b ^ 0xB3639DB335FL;
        long l2 = l ^ 0x74CB2B5674F1L;
        Globals.getPlayer().closeScreen();
        Globals.getPlayer().closeHandledScreen();
        Globals.getMc().setScreen(null);
        MovementUtil.INSTANCE.releaseMovementKeys(l2);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    LegitTotem.b = ZkmKeys.a(9099944964559892237L, 7424291585205498834L, MethodHandles.lookup().lookupClass()).a(275873715428091L);
                    var20 = LegitTotem.b ^ 130949037004975L;
                    LegitTotem.e = new HashMap<K, V>(13);
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
                    var18_3 = new String[2];
                    var16_4 = 0;
                    var15_5 = "\u001c\u0090\u00c1X\u00bd\u00ad\u00aa\u00f3P\u00a3[H\u00ee\u00aa\u00c2b\u00b0e\u00e6\u00fd\u00b7\u00edF\u00f9\u0094\u0093}\u00cas0\u00e0~\u00a5v|\u00aeQ\u0090\u00a0O\u00a1\u0096X\u00dbg\u0087\u0086\u00ab<\u00c6[\u0001\u00cfY\u00a9\u0097C\u009a\u0018\u00d8\u00bes\u00cf\u00bc\u00ba\u00e4\u00a9|\u001d\u00cdU\u00ba\u00f4U\u000b\ta\u0018\u0012\u00d7\u0085\u001b\u00f1\u0016\u00f3\u00bd/\u00a8(\u0012qH\u0003\u00fb^\u00c7~\u00ee+\u00ac\u009f0_\u00fc%\u00a4\u00ae\u00f5\u00a7J\u001a\b \u00c2\u00d29\u00db\u00f2\u0093\u00c1\u0083\u00c4iX\u00bf\u00d8\u00dc\u00c2\u0004\u00d8\u0002zD\u0095s\u00e3\u008c\u0016\u0097\u00be\u008c\u00bfZP\u00dc";
                    var17_6 = 145;
                    var14_7 = 112;
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
                        var18_3[var16_4++] = LegitTotem.zkmDecodeUtf8(var19_9).intern();
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
                LegitTotem.c = var18_3;
                LegitTotem.d = new String[2];
                LegitTotem.h = new HashMap<K, V>(13);
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
                var6_12 = new long[10];
                var3_13 = 0;
                var4_14 = "K\f[\u00c1\u00b2\u00dd7\u00a1J\u0006\u00ef\u001d\u0085%\u00a6\u00f5\u00deC\u00eb\u0015\u00a4\u0005\u009a\u008dC}+\u00c4\u00ac\u00145\u00b5\u00ee\u00c37\u001f\u00b9\u0082\u00f8G&\u00ea\u00a7\u009e\u00aaT\u0097\u009f\u00b8\u009a\u0088\u00fdG{\u0097\u00a5\u001b\u0096!\u00danF8\u00df";
                var5_15 = 64;
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
                    var4_14 = "c\b\f;\u001cOgF\\gl\u00bd\u00f4\u0092U\u00e7";
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
        LegitTotem.f = var6_12;
        LegitTotem.g = new Integer[10];
        LegitTotem.INSTANCE = new LegitTotem();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x146;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qj", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            LegitTotem.d[n2] = LegitTotem.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LegitTotem.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3CA;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/qj", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            LegitTotem.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = LegitTotem.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LegitTotem.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(LegitTotem.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

