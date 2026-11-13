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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.util.PlaceMode;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

final class TradeOffer {
    @NotNull
    private final Item item;
    private final int price;
    private final int lowestPrice;
    private final int id;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    public TradeOffer(@NotNull Item item, int n, int n2, int n3) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.item = item;
        this.price = n;
        this.lowestPrice = n2;
        this.id = n3;
    }

    @NotNull
    public final Item getItem() {
        return this.item;
    }

    public final int getPrice() {
        return this.price;
    }

    public final int getLowestPrice() {
        return this.lowestPrice;
    }

    public final int getId() {
        return this.id;
    }

    @NotNull
    public final Item getItem2() {
        return this.item;
    }

    public final int getPrice2() {
        return this.price;
    }

    public final int getLowestPrice2() {
        return this.lowestPrice;
    }

    public final int getId2() {
        return this.id;
    }

    @NotNull
    public final TradeOffer copy(@NotNull Item item, int n, int n2, int n3) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(item, (String)((Object)TradeOffer.zkmBootstrap("q", (int)3877, (long)(0xFF9CAA080945D72L ^ l))));
        return new TradeOffer(item, n, n2, n3);
    }

    public static TradeOffer copy$default(TradeOffer tradeOffer, Item item, int n, int n2, char c, int n3, int n4, int n5, Object object, int n6) {
        TradeOffer tradeOffer2;
        block13: {
            long l;
            block12: {
                int n7;
                block11: {
                    l = ((long)n2 << 32 | (long)c << 48 >>> 32 | (long)n6 << 48 >>> 48) ^ a;
                    CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2319282930472959921L, (long)l);
                    n7 = n5 & 1;
                    if (callSite == null) {
                        if (n7 != 0) {
                            item = tradeOffer.item;
                        }
                        n7 = n5 & 2;
                    }
                    CallSite callSite2 = callSite;
                    if (n6 > 0) {
                        if (callSite2 == null) {
                            if (n7 != 0) {
                                n = tradeOffer.price;
                            }
                            n7 = n5 & 4;
                        }
                        callSite2 = callSite;
                    }
                    if (n2 >= 0) {
                        if (callSite2 == null) {
                            if (n7 != 0) {
                                n3 = tradeOffer.lowestPrice;
                            }
                            n7 = n5 & TradeOffer.zkmBootstrap2("c", (int)1627, (long)(0x3C9339924E9449D3L ^ l));
                        }
                        callSite2 = callSite;
                    }
                    if (callSite2 != null) break block11;
                    if (n7 == 0) break block12;
                    n7 = tradeOffer.id;
                }
                n4 = n7;
            }
            tradeOffer2 = tradeOffer.copy(item, n, n3, n4);
            if (c < '\u0000' || ZkmReferences.zkmBootstrap("\u00c5", (long)2371018731476369014L, (long)l) == null) break block13;
            ZkmReferences.zkmBootstrap("\u00c5", "cMDTFb", (long)2371080981428557184L, (long)l);
        }
        return tradeOffer2;
    }

    @NotNull
    public String toString() {
        int n = this.id;
        int n2 = this.lowestPrice;
        int n3 = this.price;
        Item item = this.item;
        return "AucItem(item=" + item + ", price=" + n3 + ", lowestPrice=" + n2 + ", id=" + n + ")";
    }

    public int hashCode() {
        int n = this.item.hashCode();
        n = n * 31 + Integer.hashCode(this.price);
        n = n * 31 + Integer.hashCode(this.lowestPrice);
        n = n * 31 + Integer.hashCode(this.id);
        String string = PlaceMode.zkmGetFlag();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof TradeOffer)) {
            return false;
        }
        TradeOffer tradeOffer = (TradeOffer)object;
        if (!Intrinsics.areEqual(this.item, tradeOffer.item)) {
            return false;
        }
        if (this.price != tradeOffer.price) {
            return false;
        }
        if (this.lowestPrice != tradeOffer.lowestPrice) {
            return false;
        }
        return this.id == tradeOffer.id;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        TradeOffer.a = ZkmKeys.a(8455087992799926054L, -4395316051070275781L, MethodHandles.lookup().lookupClass()).a(60683251114727L);
                        TradeOffer.e = new HashMap<K, V>(13);
                        var11 = TradeOffer.a ^ 126333328909003L;
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
                        var20_3 = new String[6];
                        var18_4 = 0;
                        var17_5 = "\u0016\\T\u0085\u00b6\u008ckJ\u009dK\u0097\u00b4\u009b\u00bd\u00d5\u0016\u008e\u0091\u00d0,\u00b1\u00f2\u00b4\u00f2A\u0092\u00ae\u0084g\u00c4~Q\u0018]\u00e7\u008bDh\u00ab\u00b6\u001f\u00b0\u00978|\u009f\u00e6\u009f;\u00f9ca\r&\u00b2\u009a\u00f4\u0010\u00a4\u00b3\u0088 \u000fK\u001b\u00b6\u001e\u00ca'\u0012q\u00abM]\u0010\u00c0\u00f0\u001e\u00d2\u00eez\u0002P\u009c\u00f9\u00ab\u00c1ay\u00a0|";
                        var19_6 = 91;
                        var16_7 = 32;
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
                            var20_3[var18_4++] = TradeOffer.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00d7@w\"\u00bb\u00aa\u0091\u00ab\u00b3D\u000f\u00b0N(\u00b0E \u0000\u0089\u0002\u00a1\"\u0001k\u00bf\u0015\u0084\u00c3\u00da\u00e6\u00caX0\u00e4\u00fc\u00e8\u00ef\u00e0\u00be\u00ad\u008f\u0091/0\u00165A\u00954";
                            var19_6 = 49;
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
                            var20_3[var18_4++] = TradeOffer.zkmDecodeUtf8(var21_9).intern();
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
                TradeOffer.b = var20_3;
                TradeOffer.c = new String[6];
                TradeOffer.i = new HashMap<K, V>(13);
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
                var4_14 = "`\u00c6k\u00b6\u000b\u00dd\u00bc\u008f\u00cc\u0085\u009d\u0093\u00ad:q8\u0098\u0096\fCr+\u009f\u008c";
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
        TradeOffer.f = var6_12;
        TradeOffer.h = new Integer[3];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x157A;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/wo", exception);
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
            TradeOffer.c[n2] = TradeOffer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TradeOffer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6E3C;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/wo", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TradeOffer.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TradeOffer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/wo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TradeOffer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TradeOffer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

