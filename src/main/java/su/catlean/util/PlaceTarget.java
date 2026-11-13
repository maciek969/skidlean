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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PlaceTarget {
    @NotNull
    private final BlockPos position;
    @NotNull
    private final Direction facing;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public PlaceTarget(@NotNull BlockPos blockPos, @NotNull Direction direction) {
        Intrinsics.checkNotNullParameter(blockPos, "position");
        Intrinsics.checkNotNullParameter(direction, "facing");
        this.position = blockPos;
        this.facing = direction;
    }

    @NotNull
    public final BlockPos getPosition() {
        return this.position;
    }

    @NotNull
    public final Direction getFacing() {
        return this.facing;
    }

    @NotNull
    public final BlockPos getPosition2() {
        return this.position;
    }

    @NotNull
    public final Direction getFacing2() {
        return this.facing;
    }

    @NotNull
    public final PlaceTarget copy(@NotNull BlockPos blockPos, @NotNull Direction direction) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)PlaceTarget.zkmBootstrap("j", (int)22099, (long)(0x3EA351187752B3F6L ^ l))));
        Intrinsics.checkNotNullParameter(direction, (String)((Object)PlaceTarget.zkmBootstrap("j", (int)14165, (long)(0x6BEBC20755B2D2F6L ^ l))));
        return new PlaceTarget(blockPos, direction);
    }

    public static PlaceTarget copy$default(PlaceTarget placeTarget, BlockPos blockPos, Direction direction, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)2989415047897208964L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                blockPos = placeTarget.position;
            }
            n2 = n & 2;
        }
        if (n2 != 0) {
            direction = placeTarget.facing;
        }
        return placeTarget.copy(blockPos, direction);
    }

    @NotNull
    public String toString() {
        return "BlockPosWithFacing(position=" + this.position + ", facing=" + this.facing + ")";
    }

    public int hashCode() {
        int n = this.position.hashCode();
        n = n * (int)e + this.facing.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof PlaceTarget)) {
            return false;
        }
        PlaceTarget placeTarget = (PlaceTarget)object;
        if (!Intrinsics.areEqual(this.position, placeTarget.position)) {
            return false;
        }
        return this.facing == placeTarget.facing;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    PlaceTarget.a = ZkmKeys.a(172878911480562013L, 2275872102176327809L, MethodHandles.lookup().lookupClass()).a(181153028627339L);
                    PlaceTarget.d = new HashMap<K, V>(13);
                    var5 = PlaceTarget.a ^ 136723335459696L;
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var5 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var5 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[6];
                    var12_4 = 0;
                    var11_5 = "\u0081\u008d!\u0098\u00e7(\u00dd\u0002\u0083\u00ee*\u007fV\u0095\u00c8\u00b7\u0018]\u00f2\u009d\u009fR\u00f2\u00fb\u0015\u001aP(\u00f2^F\u00a15\u00ce\n\u00dc_\u00156\u00c4\u00ca \u0096\u00ec\u001b\u00d0\u0002\u0011\u00f7~*\u00d3\u00b4OF\u0081\u0081\u0002\u00ceX\u0081\u00cf\u00c0\u0088<\u0000\u00a8\u00c9^X\u00efK\u00d5\u00c3\u0018\u0095(\u0089\u0017\u00fe\u00f6\u008c\u00dc6l\u00b8}j\u00ce\u0089M\u00ef\u0092\u0098\u00d1\u009b\u0005y\u00d0";
                    var13_6 = 99;
                    var10_7 = 16;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = PlaceTarget.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "Uu\u001b\u00ce\u0012\u009e\u008bY>\u0096\u008a\u00bc\u00f2\u00fd\u00c9\u0099\u00e2jn\u00eai\u001e\u0080\u00f1\u0000Q\u00c8J(\u001a\u00c8q\u00bf\u00e4\u00a9(\u0010\u0084#\u009a\u0098\u00e4\u00b1\u008d\u0011p\u00db\u00e8\u00d1\u0003\u001d/\u008b\u0080#j\u0010\u001a\u00fa\u00f3\u000ej\u00dav\u0085Q\u00c81\u00ab\u0092\u0004\u009eP";
                        var13_6 = 73;
                        var10_7 = 56;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = PlaceTarget.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            PlaceTarget.b = var14_3;
            PlaceTarget.c = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var5 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var5 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{116, 54, 51, -38, 56, 15, 3, 47});
        ** while (true)
        PlaceTarget.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x694C;
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
                throw new RuntimeException("su/catlean/zl", exception);
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
            PlaceTarget.c[n2] = PlaceTarget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlaceTarget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlaceTarget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

