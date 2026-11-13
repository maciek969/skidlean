/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

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
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.Scheduler;
import su.catlean.util.SlotResult;
import su.catlean.zkm.ZkmKeys;

public final class InfiniteElytra
extends Module {
    @NotNull
    public static final InfiniteElytra INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;

    private InfiniteElytra() {
        super("infinite-elytra", Categories.getBasic(), null, null);
    }

    @Flow(priority=-10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0x62DBDB15D722L;
        long l2 = l ^ 0x6460D4FFF347L;
        int[] nArray = new int[]{};
        if (!Globals.getPlayer().isGliding()) {
            return;
        }
        if (MovementUtil.INSTANCE.Z() >= 18) {
            Item[] itemArray = new Item[6];
            Intrinsics.checkNotNullExpressionValue(Items.NETHERITE_CHESTPLATE, "NETHERITE_CHESTPLATE");
            Intrinsics.checkNotNullExpressionValue(Items.DIAMOND_CHESTPLATE, "DIAMOND_CHESTPLATE");
            Intrinsics.checkNotNullExpressionValue(Items.IRON_CHESTPLATE, "IRON_CHESTPLATE");
            Intrinsics.checkNotNullExpressionValue(Items.GOLDEN_CHESTPLATE, "GOLDEN_CHESTPLATE");
            Intrinsics.checkNotNullExpressionValue(Items.LEATHER_CHESTPLATE, "LEATHER_CHESTPLATE");
            Intrinsics.checkNotNullExpressionValue(Items.CHAINMAIL_CHESTPLATE, "CHAINMAIL_CHESTPLATE");
            SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar(itemArray);
            if (!slotResult.isFound()) {
                this.disableWithMessage("no-armor-hotbar", l2);
            }
            InventoryUtil.swapSlots$default(InventoryUtil.INSTANCE, slotResult.getSlot(), null);
            Scheduler.schedule$default(Scheduler.INSTANCE, 0, (byte)0, false, () -> InfiniteElytra.onPlayerUpdate$lambda$0(slotResult), 6, null);
            MovementUtil.INSTANCE.I(0);
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        int[] nArray = new int[]{};
        if (receivePacket.getPacket() instanceof PlaySoundS2CPacket && Intrinsics.areEqual(((PlaySoundS2CPacket)receivePacket.getPacket()).getSound().comp_349(), SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA)) {
            receivePacket.cancel();
        }
    }

    private static final void onPlayerUpdate$lambda$0$lambda$0() {
        PacketUtil.sendPacket(new ClientCommandC2SPacket(Globals.getPlayer(), ClientCommandC2SPacket.Mode.START_FALL_FLYING));
        Globals.getPlayer().startGliding();
    }

    private static final void onPlayerUpdate$lambda$0(SlotResult slotResult) {
        long l = a ^ 0x70DF9B8A70F3L;
        long l2 = l ^ 0x766494605496L;
        int[] nArray = new int[]{};
        Item[] itemArray = new Item[1];
        Intrinsics.checkNotNullExpressionValue(Items.ELYTRA, "ELYTRA");
        SlotResult slotResult2 = InventoryUtil.INSTANCE.findInHotbar(itemArray);
        int[] nArray2 = nArray;
        if (!slotResult.isFound()) {
            INSTANCE.disableWithMessage("no-elytra-hotbar", l2);
        }
        InventoryUtil.swapSlots$default(InventoryUtil.INSTANCE, slotResult2.getSlot(), null);
        Scheduler.schedule$default(Scheduler.INSTANCE, 1, (byte)0, false, InfiniteElytra::onPlayerUpdate$lambda$0$lambda$0, 6, null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        InfiniteElytra.a = ZkmKeys.a(-326384636009805114L, 7373203785871281281L, MethodHandles.lookup().lookupClass()).a(214987073862819L);
                        var20 = InfiniteElytra.a ^ 65038909976290L;
                        InfiniteElytra.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[10];
                        var16_4 = 0;
                        var15_5 = "[i\u00e1\u00f8}\u0094,\u00e1\u001f^\u0085s\u00e9<@\u00d3\u00f1\b\u0007\u00b8\u00d6Z\u00c2\u008c\u00c8I\u00f6\u00ba\u00ef\fP\u000f\u00897B8\n\b\u0081=\u0010-h\u00dd\u008d(\u0082+\u00aa9\u0083\u00fd\u00e2\u00a4\u00af.\f0\u00d7X\u009a \u0093\u00c5\u00dd\u00f27`\u00f9\u0096i\u00af\u0088[\u0088\u00e4I_\\\u0086\u0014\u00f3\u00bc\u00a1\u0011Dz\u00f6\u00a6[E\\\nN(\u00af,\u00ce\u0013\u0081LN\u008f\u00cf\u0094%(\u00b4<\u008f7\u00e7.<hH` i\u0086\u0093t\u00cbz\u00a7\u0086\u009b-A\u007f\u008f\u0081\u00e6\u00c5\u001f\u0015\u000b\u0080\u00c8\u00c8\u000b\u00f1?\u00af\u00ebG] \u00e9\u00feX\u00bcoKX\u00f1\r\u00f8\u00dd\u00ef\u00f8?^\u00f6nb\u001a\bgG\u00ee\u0081\u00957\u0017\u00e7#\u0007\u009f\u00ac(\u00fd\u009e\u00bb\u009f\u0010\u00d6\u00bew\u00e3A\u0099\u00b2H(,y\u00de\u007f\u00c8\u007fb-\u00c9\u00eaU~\u008d4\u00c1^/\u0015\u00e27J\u009a\u0096t\u00a3#($\u0010!a\u009a2\u00d7wgDK\u00b8\n\u00e3\u009c\u00cf\u0098\u00ad\u009f\u00bf1f\u00d6\u0000\u00c0\u000e\u00f2\u00f8\u000e\u0092\u0001Ht\u00e0\u009d\u009bM{\u00f3\u00b20\u00a7~\u007f\u00f1\u009a\u00adV\u000b2\u0012t\u0016\u00d3\u00fe\u008c\u00b2\u0003z\u00a0\u00e4\u00afc\u0016\u0093\u00f7G\u00ec\\<\u00d5\u009fp\u00af\u0003\u008bt\u0098\u00cai)\u008f\u00b9\t\u00e0z!\u00c3\u00ca";
                        var17_6 = 311;
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
                            var18_3[var16_4++] = InfiniteElytra.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b9\u0014\u00bc$\u00f9\u0000\u00ed\u0098q\u0010_QE\u00b1r.s\u009a\r\u00db\u00bb\u007fm\u00d6 \u00cdU\u00e6#\u001f\u00f5\u0016!\u00eb5\u0017\u00d0\u00cb!rL#fSEqw\u00ee\u00bfM\u00d9\u00b5\u00a5\u0092e\u00bf\u00ba";
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
                            var18_3[var16_4++] = InfiniteElytra.zkmDecodeUtf8(var19_9).intern();
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
                InfiniteElytra.b = var18_3;
                InfiniteElytra.c = new String[10];
                InfiniteElytra.h = new HashMap<K, V>(13);
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
                var4_14 = "d\u00f6xX(\u00ba\u0015Q\u000fO\u009e\u001b;iU\u0004\u0090\u00b7\u00c3\r\u009cc\u00f7\t";
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x434D;
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
                throw new RuntimeException("su/catlean/_o", exception);
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
            throw new RuntimeException("su/catlean/_o" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2132;
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
                throw new RuntimeException("su/catlean/_o", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            InfiniteElytra.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
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
            throw new RuntimeException("su/catlean/_o" + " : " + string + " : " + methodType.toString(), exception);
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
            return MethodHandles.lookup().findStatic(InfiniteElytra.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

