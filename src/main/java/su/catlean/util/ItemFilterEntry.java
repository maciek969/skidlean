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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ItemFilterEntry {
    @NotNull
    private final Item item;
    private final boolean another;
    private final boolean enchanted;
    @NotNull
    private final String name;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public ItemFilterEntry(@NotNull Item item, boolean bl, boolean bl2, @NotNull String string) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 32 | (long)n << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(item, (String)((Object)ItemFilterEntry.zkmBootstrap("h", (int)18339, (long)(0x5CD9989726AEA06CL ^ l2))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)ItemFilterEntry.zkmBootstrap("h", (int)9829, (long)(0x770D268AD6C5C1ACL ^ l2))));
        this.item = item;
        this.another = bl;
        this.enchanted = bl2;
        this.name = string;
    }

    public ItemFilterEntry(Item item, boolean bl, boolean bl2, String string, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 2) != 0) {
            bl = false;
        }
        if ((n & 4) != 0) {
            bl2 = false;
        }
        if ((n & 8) != 0) {
            string = "";
        }
        this(item, bl, bl2, string);
    }

    @NotNull
    public final Item getItem() {
        return this.item;
    }

    public final boolean isAnother() {
        return this.another;
    }

    public final boolean isEnchanted() {
        return this.enchanted;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Item getItem2() {
        return this.item;
    }

    public final boolean isAnother2() {
        return this.another;
    }

    public final boolean isEnchanted2() {
        return this.enchanted;
    }

    @NotNull
    public final String getName2() {
        return this.name;
    }

    @NotNull
    public final ItemFilterEntry copy(@NotNull Item item, boolean bl, boolean bl2, @NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(item, (String)((Object)ItemFilterEntry.zkmBootstrap("h", (int)17914, (long)(0x485F82B3A7554D35L ^ l))));
        Intrinsics.checkNotNullParameter(string, (String)((Object)ItemFilterEntry.zkmBootstrap("h", (int)11231, (long)(0x5C42A2EE218EA317L ^ l))));
        return new ItemFilterEntry(item, bl, bl2, string);
    }

    public static ItemFilterEntry copy$default(ItemFilterEntry itemFilterEntry, Item item, boolean bl, boolean bl2, String string, int n, Object object, long l) {
        ItemFilterEntry itemFilterEntry2;
        block9: {
            Module[] moduleArray;
            block8: {
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-7430724463738608902L, (long)l);
                int n2 = n & 1;
                if (callSite == null) {
                    if (n2 != 0) {
                        item = itemFilterEntry.item;
                    }
                    n2 = n & 2;
                }
                CallSite callSite2 = callSite;
                if (l > 0L) {
                    if (callSite2 == null) {
                        if (n2 != 0) {
                            bl = itemFilterEntry.another;
                        }
                        n2 = n & 4;
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == null) {
                    if (n2 != 0) {
                        bl2 = itemFilterEntry.enchanted;
                    }
                    n2 = n & ItemFilterEntry.zkmBootstrap2("j", (int)27807, (long)(0x48D71187DEE56B37L ^ l));
                }
                if (n2 != 0) {
                    string = itemFilterEntry.name;
                }
                itemFilterEntry2 = itemFilterEntry.copy(item, bl, bl2, string);
                moduleArray = ZkmReferences.zkmBootstrap("\u00c5", (long)-7430224299516721549L, (long)l);
                if (l <= 0L) break block8;
                if (moduleArray == null) break block9;
                moduleArray = new Module[5];
            }
            ZkmReferences.zkmBootstrap("\u00c5", (Object)moduleArray, (long)-7470048661362843048L, (long)l);
        }
        return itemFilterEntry2;
    }

    @NotNull
    public String toString() {
        String string = this.name;
        boolean bl = this.enchanted;
        boolean bl2 = this.another;
        Item item = this.item;
        return "RequiredItem(item=" + item + ", another=" + bl2 + ", enchanted=" + bl + ", name=" + string + ")";
    }

    public int hashCode() {
        int n = this.item.hashCode();
        n = n * 31 + Boolean.hashCode(this.another);
        n = n * 31 + Boolean.hashCode(this.enchanted);
        n = n * 31 + this.name.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ItemFilterEntry)) {
            return false;
        }
        ItemFilterEntry itemFilterEntry = (ItemFilterEntry)object;
        if (!Intrinsics.areEqual(this.item, itemFilterEntry.item)) {
            return false;
        }
        if (this.another != itemFilterEntry.another) {
            return false;
        }
        if (this.enchanted != itemFilterEntry.enchanted) {
            return false;
        }
        return Intrinsics.areEqual(this.name, itemFilterEntry.name);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ItemFilterEntry.a = ZkmKeys.a(-3736976923122249786L, 8312295258252565815L, MethodHandles.lookup().lookupClass()).a(222137449833498L);
                        ItemFilterEntry.d = new HashMap<K, V>(13);
                        var11 = ItemFilterEntry.a ^ 67298655242357L;
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
                        var20_3 = new String[8];
                        var18_4 = 0;
                        var17_5 = "2\u00ab\u001euT\u00b1E\u00b7p\u00a4\u0098\u009c\u00a6n\u0089\u0089iJ\u008fT\u00c7O\u00f9\u00c5\u00ea\u00b96k\u00d9\u00f0\u000e\u00dcpG5\u00f9:'NV\u0010\u00d5\u00eeZ\u00ceG\u00b0\u00f4\u00d7\u00d4\u00c0K\u0081v\u00b88\u009d\u0010@i\u00e5\u00a4\u009f\u008e\u00b8\u001d\u0090\u0095\u00fbE\u0000*\u00e6\u001f\u0010\u00f9D\u00f9O0\u0084\u0085C\u00ca\u0092\u0016P\u00822I\u00bb\u0010\u00a1\u0001m\u00a0\u0092`\u00d9\u00ab\u00a2\u00c5\u00c3\u00be\u00f7\u009f\u00d1\u00d8 \u00dbGp\u00a7\u0082U+\u00d8\u00d5\u00ad\u00c6\u00d2\u00cdo=\u00e9R\u00fb\u00b6\u008ck\u009c\u0018\u00a5r\u00d4\u0015\u00a6)\u00e1|\u00f9";
                        var19_6 = 141;
                        var16_7 = 40;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = ItemFilterEntry.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u001f \u0001\u001a\u008a\u00e8\tiB\u0000\u00f7>.\u0092\u009dy\u0018}\u00bc\u00b0c;\u00e1\u00fb\u007f\u0090\u00b5\u00cc\u00d7\u00f4\u009f:\u00a2\u001d\u008aPw\u00eb\u0015\u00912";
                            var19_6 = 41;
                            var16_7 = 16;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = ItemFilterEntry.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
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
                ItemFilterEntry.b = var20_3;
                ItemFilterEntry.c = new String[8];
                ItemFilterEntry.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u0003mH\u0018\u00b6'\u00bd\u00c1\u00be\u001a\u00b4\u0084\u001fE$\u00e2";
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
                    var4_14 = "D\u0002\u0000\u00d4\u001c\u00bb\u0001\u00c1\u00f5\u001b\u00e5\u00bb\u00f3\u009cZ\u00e5";
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
        ItemFilterEntry.e = var6_12;
        ItemFilterEntry.f = new Integer[4];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5811;
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
                throw new RuntimeException("su/catlean/bz", exception);
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
            ItemFilterEntry.c[n2] = ItemFilterEntry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemFilterEntry.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/bz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1E19;
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
                throw new RuntimeException("su/catlean/bz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ItemFilterEntry.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ItemFilterEntry.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/bz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemFilterEntry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ItemFilterEntry.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

