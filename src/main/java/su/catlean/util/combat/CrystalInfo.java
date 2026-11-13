/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.combat;

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
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class CrystalInfo {
    @NotNull
    private final EndCrystalEntity crystal;
    private final boolean block;
    private static boolean O;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long f;

    public CrystalInfo(@NotNull EndCrystalEntity endCrystalEntity, boolean bl) {
        Intrinsics.checkNotNullParameter(endCrystalEntity, "crystal");
        this.crystal = endCrystalEntity;
        this.block = bl;
    }

    public /* synthetic */ CrystalInfo(EndCrystalEntity endCrystalEntity, boolean bl, DefaultConstructorMarker defaultConstructorMarker) {
        bl = false;
        this(endCrystalEntity, bl);
    }

    @NotNull
    public final EndCrystalEntity getCrystal() {
        return this.crystal;
    }

    public final boolean isBlock() {
        return this.block;
    }

    @NotNull
    public final EndCrystalEntity getCrystal2() {
        return this.crystal;
    }

    public final boolean isBlock2() {
        return this.block;
    }

    @NotNull
    public final CrystalInfo copy(@NotNull EndCrystalEntity endCrystalEntity, boolean bl) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(endCrystalEntity, (String)((Object)CrystalInfo.zkmBootstrap("f", (int)13495, (long)(0x28AE249A0EDB0610L ^ l2))));
        return new CrystalInfo(endCrystalEntity, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ CrystalInfo copy$default(CrystalInfo crystalInfo, EndCrystalEntity endCrystalEntity, boolean bl, int n, Object object) {
        boolean bl2;
        CallSite callSite;
        long l;
        block7: {
            boolean bl3;
            block6: {
                void var3_4;
                l = 0L;
                l = a ^ l;
                callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-4527916293822533615L, (long)l);
                bl3 = var3_4 & 1;
                if (callSite != false) {
                    if (bl3) {
                        endCrystalEntity = crystalInfo.crystal;
                    }
                    bl3 = var3_4 & 2;
                }
                if (callSite == false) break block6;
                if (!bl3) break block7;
                bl3 = crystalInfo.block;
            }
            bl2 = bl3;
        }
        CrystalInfo crystalInfo2 = crystalInfo.copy(endCrystalEntity, bl2);
        if (ZkmReferences.zkmBootstrap("\u00c5", (long)-4480812119399669951L, (long)l) != null) {
            ZkmReferences.zkmBootstrap("\u00c5", (callSite == false ? 1 : 0) != 0, (long)-4535516510246330966L, (long)l);
        }
        return crystalInfo2;
    }

    @NotNull
    public String toString() {
        return "CrystalWrapper(crystal=" + this.crystal + ", block=" + this.block + ")";
    }

    public int hashCode() {
        int n;
        block0: {
            n = this.crystal.hashCode();
            boolean bl = CrystalInfo.K();
            n = n * (int)f + Boolean.hashCode(this.block);
            if (!bl) break block0;
            Module.zkmSetFlag(new Module[2]);
        }
        return n;
    }

    public boolean equals(@Nullable Object object) {
        boolean bl = CrystalInfo.K();
        Object object2 = this;
        if (!bl) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (!bl) {
            if (!(object2 instanceof CrystalInfo)) {
                return false;
            }
            object2 = object;
        }
        CrystalInfo crystalInfo = (CrystalInfo)object2;
        boolean bl2 = Intrinsics.areEqual(this.crystal, crystalInfo.crystal);
        if (!bl) {
            if (!bl2) {
                return false;
            }
            bl2 = this.block;
        }
        if (!bl) {
            if (bl2 != crystalInfo.block) {
                return false;
            }
            bl2 = true;
        }
        return bl2;
    }

    public static void zkmSetFlag(boolean bl) {
        O = bl;
    }

    public static boolean zkmGetFlag() {
        return O;
    }

    public static boolean K() {
        return false;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    CrystalInfo.a = ZkmKeys.a(-834247996626952122L, -7857121743543482226L, MethodHandles.lookup().lookupClass()).a(232916167405904L);
                    var14 = CrystalInfo.a ^ 32202209199079L;
                    CrystalInfo.e = new HashMap<K, V>(13);
                    CrystalInfo.zkmSetFlag(true);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[4];
                    var10_4 = 0;
                    var9_5 = "\u00a3A\u00d2'I\u009d\u00c7\u009e\u00c0\u00f3o\u00a7YX\u0082\u00a2 \b\u00f8\u00a0\u00cf+\u0085\u00c3\u00c0\u00b2\u00bd\u0086O\u008b\u00b9\u0095\u0000\u0006\u0082x',\u00fdAc\u000bA\u00fa,v\u009d.\u0085";
                    var11_6 = 49;
                    var8_7 = 16;
                    var7_8 = -1;
lbl21:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl26:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = CrystalInfo.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "v\u0019\u00a8\u0093VM\u00ad\u00bam\u0019\"#|\u00e5\u00c8\u00f4(Q\u0087\u0001\u00e0\u009c\u00d9\u00ca<(M\u00ec\u00fb\b\u009a\u0005\u0081\u0083Z\u00e7\u00b1Q\u00cajP\u009ay\u00db\u00f4\u0018\u00cd\u00d3dV\u008d`\u00f5_\u00ec>w";
                        var11_6 = 57;
                        var8_7 = 16;
                        var7_8 = -1;
lbl35:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl40:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = CrystalInfo.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            CrystalInfo.b = var12_3;
            CrystalInfo.c = new String[4];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl66:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{4, -104, 56, -51, -109, 115, 109, 8});
        ** while (true)
        CrystalInfo.f = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2112;
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
                throw new RuntimeException("su/catlean/nw", exception);
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
            CrystalInfo.c[n2] = CrystalInfo.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CrystalInfo.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nw" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CrystalInfo.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

