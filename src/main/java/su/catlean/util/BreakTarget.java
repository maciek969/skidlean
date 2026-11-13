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
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BreakTarget {
    @NotNull
    private final BlockPos pos;
    private int progress;
    private boolean head;
    private static String[] U;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public BreakTarget(@NotNull BlockPos blockPos, int n, boolean bl) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 48 | (long)n3 << 32 >>> 16 | (long)n4 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)BreakTarget.zkmBootstrap("b", (int)26679, (long)(0x4EB6AB56F1B2478L ^ l))));
        this.pos = blockPos;
        this.progress = n;
        this.head = bl;
    }

    public /* synthetic */ BreakTarget(BlockPos blockPos, int n, boolean bl, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 2) != 0) {
            n = 0;
        }
        if ((n2 & 4) != 0) {
            bl = false;
        }
        this(blockPos, n, bl);
    }

    @NotNull
    public final BlockPos getPos() {
        return this.pos;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final void setProgress(int n) {
        this.progress = n;
    }

    public final boolean isHead() {
        return this.head;
    }

    public final void setHead(boolean bl) {
        this.head = bl;
    }

    @NotNull
    public final BlockPos getPos2() {
        return this.pos;
    }

    public final int getProgress2() {
        return this.progress;
    }

    public final boolean isHead2() {
        return this.head;
    }

    @NotNull
    public final BreakTarget copy(@NotNull BlockPos blockPos, int n, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)BreakTarget.zkmBootstrap("b", (int)20241, (long)(0xB25FC88C37D389DL ^ l))));
        return new BreakTarget(blockPos, n, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ BreakTarget copy$default(BreakTarget breakTarget, BlockPos blockPos, int n, short s, boolean bl, int n2, int n3, Object object) {
        BreakTarget breakTarget2;
        block10: {
            boolean bl2;
            long l;
            block9: {
                boolean bl3;
                block8: {
                    void var5_6;
                    void var6_7;
                    int n4 = 0;
                    l = ((long)s << 48 | (long)n4 << 48 >>> 16 | (long)var6_7 << 32 >>> 32) ^ a;
                    CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8080777558833918829L, (long)l);
                    bl3 = var5_6 & 1;
                    if (callSite == null) {
                        if (bl3) {
                            blockPos = breakTarget.pos;
                        }
                        bl3 = var5_6 & 2;
                    }
                    CallSite callSite2 = callSite;
                    if (var6_7 >= 0) {
                        if (callSite2 == null) {
                            if (bl3) {
                                n = breakTarget.progress;
                            }
                            bl3 = var5_6 & 4;
                        }
                        callSite2 = callSite;
                    }
                    if (callSite2 != null) break block8;
                    if (!bl3) break block9;
                    bl3 = breakTarget.head;
                }
                bl2 = bl3;
            }
            breakTarget2 = breakTarget.copy(blockPos, n, bl2);
            if (s < 0 || ZkmReferences.zkmBootstrap("\u00c5", (long)8111773534004866563L, (long)l) == null) break block10;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new String[4], (long)8118850304741620093L, (long)l);
        }
        return breakTarget2;
    }

    @NotNull
    public String toString() {
        boolean bl = this.head;
        int n = this.progress;
        BlockPos blockPos = this.pos;
        return "BreakingPos(pos=" + blockPos + ", progress=" + n + ", head=" + bl + ")";
    }

    public int hashCode() {
        int n = this.pos.hashCode();
        n = n * 31 + Integer.hashCode(this.progress);
        n = n * 31 + Boolean.hashCode(this.head);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BreakTarget)) {
            return false;
        }
        BreakTarget breakTarget = (BreakTarget)object;
        if (!Intrinsics.areEqual(this.pos, breakTarget.pos)) {
            return false;
        }
        if (this.progress != breakTarget.progress) {
            return false;
        }
        return this.head == breakTarget.head;
    }

    public static void zkmSetFlag(String[] stringArray) {
        U = stringArray;
    }

    public static String[] zkmGetFlag() {
        return U;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        BreakTarget.a = ZkmKeys.a(-2405089973857922847L, 2080877093164971565L, MethodHandles.lookup().lookupClass()).a(64848200016545L);
                        var20 = BreakTarget.a ^ 119799302267064L;
                        BreakTarget.d = new HashMap<K, V>(13);
                        BreakTarget.zkmSetFlag(null);
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
                        var18_3 = new String[5];
                        var16_4 = 0;
                        var15_5 = "+Y\u00cf\rJ\u009b:\u00e5\"\u00f3FTSR\u00eb\u00e0\u0010\u00c3\u0096\u0093\u00b8)\u0000a( \u008f\u00db\u00b6D\u0013W' S\u00db\u00bb\u0015\u008b\u00b4NO\u008f\u00dd\u00faD\u0006\u00a7\u0012C \u00cb\u00c7\u00de\u00ea\u00b4(\u00bbi\u00d8\u00a5{\u00dd\u0087\u0093l";
                        var17_6 = 66;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = BreakTarget.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00far\u00df\u00beG\u008d\u00f2_\u009c\u00d4*\u00d8\u0098\u00cf\u00b6\u00ea-\u00c0\u008f8\u00a2\u00c4\u00da\u00f2\u0099\u001c6\u00c7\u00dbx\u00e4_\u00dbq\u0092K\u0089)<\u00f1v\u00ece\u0088\u0012g\u00a4\u00a0\u0010\u00f2\u00a5!\u00894vw\u009d\u00be\u00a7\u00e1\u00d4\u0000\u008a\u0082\r";
                            var17_6 = 65;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = BreakTarget.zkmDecodeUtf8(var19_9).intern();
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
                BreakTarget.b = var18_3;
                BreakTarget.c = new String[5];
                BreakTarget.g = new HashMap<K, V>(13);
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
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "\b@\u00ab\u00c6\u00e5\u00aaW\u00d0\u00ef\u009b\u00feQ\u00e7JT@";
                var5_15 = 16;
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
        BreakTarget.e = var6_12;
        BreakTarget.f = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4441;
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
                throw new RuntimeException("su/catlean/xe", exception);
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
            BreakTarget.c[n2] = BreakTarget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BreakTarget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/xe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x19BC;
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
                throw new RuntimeException("su/catlean/xe", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BreakTarget.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BreakTarget.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/xe" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BreakTarget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(BreakTarget.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

