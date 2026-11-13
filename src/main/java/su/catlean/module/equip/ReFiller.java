/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.PanelScreen;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.TickTimer;
import su.catlean.zkm.ZkmKeys;

public final class ReFiller
extends Module {
    @NotNull
    public static final ReFiller INSTANCE;
    @NotNull
    private static final TickTimer refillTimer;
    private static int t;
    private static int E;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    private ReFiller() {
        super("re-filler", Categories.getEquip(), null, null);
    }

    public final int C() {
        return t;
    }

    public final void K(int n) {
        t = n;
    }

    public final int j() {
        return E;
    }

    public final void i(int n) {
        E = n;
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        int n;
        long l;
        long l2 = l = a ^ 0x6D736EF423C2L;
        long l3 = l2 ^ 0x5442A26F5CCCL;
        int n2 = (int)(l3 >>> 48);
        long l4 = l3 << 16 >>> 16;
        int n3 = (int)((l2 ^ 0x4DC27ED0FB75L) >>> 48);
        if (!(Globals.getMc().currentScreen == null || Globals.getMc().currentScreen instanceof ClickGuiScreen || Globals.getMc().currentScreen instanceof PanelScreen || Globals.getMc().currentScreen instanceof HudEditorScreen)) {
            t = 0;
            return;
        }
        if (Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.TOTEM_OF_UNDYING)) {
            t = 3;
            E = Globals.getPlayer().getInventory().getSelectedSlot();
        } else if (t > 0) {
            if (!Intrinsics.areEqual(Globals.getPlayer().getInventory().getStack(E).getItem(), Items.TOTEM_OF_UNDYING)) {
                Item[] itemArray = new Item[1];
                Intrinsics.checkNotNullExpressionValue(Items.TOTEM_OF_UNDYING, "TOTEM_OF_UNDYING");
                SlotResult slotResult = InventoryUtil.INSTANCE.findInInventory(itemArray, (char)n2, l4);
                if (slotResult.isFound()) {
                    InteractionUtil.clickSlot(slotResult.getSlot(), E, SlotActionType.SWAP, true);
                    t = 0;
                }
            }
            n = t;
            t = n + -1;
        }
        if (!refillTimer.hasPassed((short)n3, 10)) {
            return;
        }
        for (n = 0; n < 9; ++n) {
            if (!this.refillSlot(n)) continue;
            return;
        }
    }

    private final boolean refillSlot(int n) {
        ItemStack itemStack = Globals.getPlayer().getInventory().getStack(n);
        Intrinsics.checkNotNullExpressionValue(itemStack, "getItem(...)");
        ItemStack itemStack2 = itemStack;
        if (itemStack2.isEmpty() || !itemStack2.isStackable()) {
            return false;
        }
        if (itemStack2.getMaxCount() == 16 && itemStack2.getCount() > 8) {
            return false;
        }
        if (itemStack2.getMaxCount() == 64 && itemStack2.getCount() > 32) {
            return false;
        }
        for (int i = 9; i < 36; ++i) {
            boolean bl;
            ItemStack itemStack3;
            Intrinsics.checkNotNullExpressionValue(Globals.getPlayer().getInventory().getStack(i), "getItem(...)");
            if (itemStack3.isEmpty() || !this.canStack(itemStack2, itemStack3)) continue;
            boolean bl2 = bl = itemStack3.getCount() + itemStack2.getCount() > itemStack3.getMaxCount();
            if (itemStack3.getMaxCount() == itemStack3.getCount()) {
                InteractionUtil.clickSlot(i, n, SlotActionType.SWAP, true);
            } else {
                InteractionUtil.clickSlot$default(i, 0, null, false, 14, null);
                Object var12_6 = null;
                int n2 = 6;
                boolean bl3 = !bl;
                SlotActionType slotActionType = null;
                int n3 = 0;
                InteractionUtil.clickSlot$default(n < 9 ? 36 + n : n, n3, slotActionType, bl3, n2, var12_6);
                if (bl) {
                    InteractionUtil.clickSlot$default(i, 0, null, true, 6, null);
                }
            }
            refillTimer.reset();
            return true;
        }
        return false;
    }

    private final boolean canStack(ItemStack itemStack, ItemStack itemStack2) {
        return Intrinsics.areEqual(itemStack.getItem(), itemStack2.getItem()) && Intrinsics.areEqual(itemStack.getName(), itemStack2.getName());
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ReFiller.a = ZkmKeys.a(-1233932940099521615L, 5294163762440540995L, MethodHandles.lookup().lookupClass()).a(73569966399332L);
                        var20 = ReFiller.a ^ 25919847638605L;
                        ReFiller.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[4];
                        var16_4 = 0;
                        var15_5 = "\u009e\u00d4\u00de:\u00d2\u0090\u00cd\u00f0\u00a7\u00fe1\u0092\u000bi\u0005\u001dj\u0013\u00c9b\u00dei\u00ac\u00f5\u008fO\u0095\u00b1\u00f5M\u00bb\u00b1\u00d3\u00cd3\u00ed\u00d9\u00e3\u00dc\u0002D,\u00c0.#*\u0016t \u00e5j\u00a5d\u00e9\u00a8\u008e\u001cf\u00f1\u00ef\u00de\u00a4&-\u00e5\u0012m\u0007\u00f7m\u0093p\u00ef\u00cabH\u00cf\"jQ\u0087";
                        var17_6 = 81;
                        var14_7 = 48;
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
                            var18_3[var16_4++] = ReFiller.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u008d.\u009d\u00a4y\u00a2:\u00c6\u00d5\u0004\u0003\u00c7rF\u001b\u00d6\u0088\u00b5z\u00e2Jp\u00fc\u00ca6`\u00aa\u0094\u00d9D\u00c4\u00fa <\u00d3\f\u00b6\u0014z\u0004%c\u00ce-c/1A\u00fe\u0000p\u00f6H8$r\u00d6K\u00d7\u000e\u0096t\u0098\u00d0\u00bd";
                            var17_6 = 65;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = ReFiller.zkmDecodeUtf8(var19_9).intern();
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
                ReFiller.b = var18_3;
                ReFiller.c = new String[4];
                ReFiller.h = new HashMap<K, V>(13);
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
                var6_12 = new long[13];
                var3_13 = 0;
                var4_14 = "\u0004p\u00bb\u001fg\u00dao\u00e7;uU\u00bc+\u009d\u00a3\u0013A\u00ae\u00b1\u0089\u00b8\u00ea\u00d7\u001dsT\u008fb\u0086\u00a5v\u00c5\"\r\u00d7\u00c0\u0006\u0006R\u00e3\u0011H\u0092\u00a6\u0017u\u00f4.\u00f0\u00b4\u00e0\u0017\u00bf\u00a7\u00a9_\u0019E\u0083\u001euox\u008f\u00dc\u0010\u0094\u0098\u0095O]Pi\u0007,\u00d4D\u00e7\u009e\u00caYu\u00f0\u00dc\u00f1\u00b3\u00c8\u00b9";
                var5_15 = 88;
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
                    var4_14 = "\u0087\u00a1\u00d2\u00c4\u00f4\u00d1t\u00e5c\u009btc\u00daA\u0017\u00ae";
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
        ReFiller.e = var6_12;
        ReFiller.g = new Integer[13];
        ReFiller.INSTANCE = new ReFiller();
        ReFiller.refillTimer = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1610;
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
                throw new RuntimeException("su/catlean/qu", exception);
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
            ReFiller.c[n2] = ReFiller.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ReFiller.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4D3D;
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
                throw new RuntimeException("su/catlean/qu", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ReFiller.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ReFiller.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ReFiller.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ReFiller.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

