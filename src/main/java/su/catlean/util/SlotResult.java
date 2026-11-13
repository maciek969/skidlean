/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.SlotResult$Companion;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class SlotResult {
    @NotNull
    public static final SlotResult$Companion Companion;
    private final int slot;
    private final boolean found;
    @NotNull
    private final ItemStack stack;
    @NotNull
    private static final SlotResult y;
    private static Module[] F;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public SlotResult(int n, boolean bl, @NotNull ItemStack itemStack) {
        Intrinsics.checkNotNullParameter(itemStack, "stack");
        this.slot = n;
        this.found = bl;
        this.stack = itemStack;
    }

    public final int getSlot() {
        return this.slot;
    }

    public final boolean isFound() {
        return this.found;
    }

    @NotNull
    public final ItemStack getStack2() {
        return this.stack;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final boolean getStack() {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2642328766093645140L, (long)l);
        ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
        if (callSite == null) {
            if (clientPlayerEntity == null) {
                return 0 != 0;
            }
            clientPlayerEntity = Globals.getPlayer();
        }
        int n = clientPlayerEntity.getInventory().getSelectedSlot();
        if (callSite != null) return n != 0;
        if (n != this.slot) return 0 != 0;
        return 1 != 0;
    }

    private final boolean isHotbar() {
        return this.slot < 9;
    }

    public final void select() {
        block0: {
            if (!this.found || !this.isHotbar()) break block0;
            InventoryUtil.INSTANCE.selectSlot(this.slot);
        }
    }

    public final void ifFound(@NotNull Function1 function1) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)5946329270225838973L, (long)l2);
        Intrinsics.checkNotNullParameter(function1, (String)((Object)SlotResult.zkmBootstrap("v", (int)5728, (long)(0x8B9EA255151CA8L ^ l2))));
        CallSite callSite2 = callSite;
        SlotResult slotResult = this;
        if (callSite2 == null && slotResult.isFound()) {
            slotResult = function1.invoke(this);
        }
    }

    public final int getSlot2() {
        return this.slot;
    }

    public final boolean isFound2() {
        return this.found;
    }

    @NotNull
    public final ItemStack getStack3() {
        return this.stack;
    }

    @NotNull
    public final SlotResult copy(int n, boolean bl, @NotNull ItemStack itemStack) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(itemStack, (String)((Object)SlotResult.zkmBootstrap("v", (int)6680, (long)(0x255C4BE54372102CL ^ l))));
        return new SlotResult(n, bl, itemStack);
    }

    public static SlotResult copy$default(SlotResult slotResult, int n, boolean bl, ItemStack itemStack, int n2, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5808371006358004068L, (long)l);
        int n3 = n2 & 1;
        if (callSite == null) {
            if (n3 != 0) {
                n = slotResult.slot;
            }
            n3 = n2 & 2;
        }
        if (callSite == null) {
            if (n3 != 0) {
                bl = slotResult.found;
            }
            n3 = n2 & 4;
        }
        if (n3 != 0) {
            itemStack = slotResult.stack;
        }
        return slotResult.copy(n, bl, itemStack);
    }

    @NotNull
    public String toString() {
        ItemStack itemStack = this.stack;
        boolean bl = this.found;
        int n = this.slot;
        return "SearchInvResult(slot=" + n + ", found=" + bl + ", stack=" + itemStack + ")";
    }

    public int hashCode() {
        int n = Integer.hashCode(this.slot);
        n = n * 31 + Boolean.hashCode(this.found);
        n = n * 31 + this.stack.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof SlotResult)) {
            return false;
        }
        SlotResult slotResult = (SlotResult)object;
        if (this.slot != slotResult.slot) {
            return false;
        }
        if (this.found != slotResult.found) {
            return false;
        }
        return Intrinsics.areEqual(this.stack, slotResult.stack);
    }

    public static final SlotResult X() {
        return y;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        SlotResult.a = ZkmKeys.a(-2131163273576256315L, 5036752660355681584L, MethodHandles.lookup().lookupClass()).a(271579057654007L);
                        var20 = SlotResult.a ^ 41354294163240L;
                        SlotResult.d = new HashMap<K, V>(13);
                        SlotResult.zkmSetFlag(null);
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
                        var15_5 = "{\u0002\u00c6<,7O\u00c2\u00ae\u00c9\u00d3\u00fb\u00b2\u00bek8\u00fe\u008c\u00cdQ\u00f8\u00d3n\u00ac\u0010\u00e8d\u00d8\u00e3`\u00f5\u0016.\u0015v\u0007\u00c1\u009b\"\u00e3\u00c30\u00c8\u000f\u008d/\u0010{>\u001cjd\u0017Z`\u00a8\n\u00edE\t\u00d0C\u0014\u00c7\u0000'\u00a3\u00e6\u00dd6\u000b*J\u00deo\u00ee\u00b23\u009e\u00fb\u00d3\u00ca\u00874\u00c8\u00acD\u0019\u00dcP \u00b9\u00bf\u00a7\u0006R\u008a\u00c67'\u008f)|!f5\u00df\u0007\u009b\u00ee\u00f8L6\u008e+\u00ce\u007f\u00fa\u00ec\u0002:\u00e9\u008e\u0010\u0081\u00a8D\u00a0\u00eb\u00b4|\u0015Zj\u009fJ\u00dc>>s";
                        var17_6 = 140;
                        var14_7 = 24;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = SlotResult.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a79\u009e\u0012\u0091\u00a2\u00d5\u00b8\u00bc\u00ee\bH\u00ebA\u0098S\u0010:$\u00b9\u00ea<)\u00bdC\u00bd\u00a0\u00c8T\u00df\u001c7L";
                            var17_6 = 33;
                            var14_7 = 16;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = SlotResult.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                SlotResult.b = var18_3;
                SlotResult.c = new String[7];
                SlotResult.g = new HashMap<K, V>(13);
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
                var4_14 = " \u00eb\u00f3\u00d0\u001f\\+\u00d39\u00ebt\u00ba!\u00e7\u00a8V\u00a2\u0090\u00a9\u00bcC0\u00a6w";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl74:
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
        SlotResult.e = var6_12;
        SlotResult.f = new Integer[3];
        SlotResult.Companion = new SlotResult$Companion(null);
        v11 = ItemStack.EMPTY;
        Intrinsics.checkNotNullExpressionValue(v11, "EMPTY");
        SlotResult.y = new SlotResult(-1, false, v11);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        F = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return F;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5900;
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
                throw new RuntimeException("su/catlean/fg", exception);
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
            SlotResult.c[n2] = SlotResult.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SlotResult.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x49B;
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
                throw new RuntimeException("su/catlean/fg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SlotResult.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SlotResult.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/fg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SlotResult.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SlotResult.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

