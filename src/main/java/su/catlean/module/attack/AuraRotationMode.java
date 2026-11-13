/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.rotation.AimMode;
import su.catlean.util.rotation.AimSettings;
import su.catlean.util.rotation.aim.DefaultAim;
import su.catlean.util.rotation.aim.HvhAim;
import su.catlean.util.rotation.aim.NoAim;
import su.catlean.util.rotation.aim.PolarAim;
import su.catlean.util.rotation.aim.SnapAim;
import su.catlean.util.rotation.aim.VulcanAim;
import su.catlean.zkm.ZkmKeys;

public final class AuraRotationMode
extends Enum {
    @NotNull
    private final AimMode aimMode;
    public static final AuraRotationMode SNAP;
    public static final AuraRotationMode DEFAULT;
    public static final AuraRotationMode NONE;
    public static final AuraRotationMode VULCAN;
    public static final AuraRotationMode HVH;
    public static final AuraRotationMode POLAR_SEP_25;
    public static final AuraRotationMode GRIM_SILENT;
    public static final AuraRotationMode ADVANCED;
    private static final AuraRotationMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static final long b;
    private static final long[] c;
    private static final Integer[] d;
    private static final Map e;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private AuraRotationMode() {
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.aimMode = var3_2;
    }

    @NotNull
    public final AimMode getAimMode() {
        return this.aimMode;
    }

    public static AuraRotationMode[] values() {
        return (AuraRotationMode[])$VALUES.clone();
    }

    public static AuraRotationMode valueOf(String string) {
        return Enum.valueOf(AuraRotationMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final AuraRotationMode[] $values() {
        AuraRotationMode[] auraRotationModeArray = new AuraRotationMode[]{SNAP, DEFAULT, NONE, VULCAN, HVH, POLAR_SEP_25, GRIM_SILENT, ADVANCED};
        return auraRotationModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AuraRotationMode.b = ZkmKeys.a(-3839967049292041836L, -4732449546217327548L, MethodHandles.lookup().lookupClass()).a(261132091508637L);
                        var20 = AuraRotationMode.b ^ 102687345393381L;
                        var12_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var13_2 = 1; var13_2 < 8; ++var13_2) {
                            v2 = v2;
                            v2[var13_2] = (byte)(var20 << var13_2 * 8 >>> 56);
                        }
                        var12_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var11_3 = new String[8];
                        var17_4 = 0;
                        var16_5 = "\u00d1\u009f\u00c1Zi\u0083\u00ce\u00ef\u0010<\u00c1\u0086\u00fdq`-\u00b4\u007f\bk\u00e0\u00b7\u0080/\b\u0010\u008b\u0004\u0087\u00a1\u00ae\u00b6\u00b4\u0094!\b:\u0003\u00cd_\u00bed\b\u00bbE\u00b4\u00ee\u0018\u00bf\u0018\u0081\u0010~\u000bi\u00c9\u00fd\u0014w4\u00a4\u00ba@\u00eb\u008f\u00ca\u00f7\u0001\b\u00c9\u00c3\u00f41\u0092i\u00c3Y";
                        var18_6 = 77;
                        var15_7 = 8;
                        var14_8 = -1;
lbl19:
                        // 2 sources

                        while (true) {
                            v3 = ++var14_8;
                            v4 = var16_5.substring(v3, v3 + var15_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl24:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = AuraRotationMode.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            var16_5 = "\"\u00ad\u00e2\u00dd\u00ca\u00a9\u00fb\u0089\b\u009b\u00c6\u00ec\u00cc\u00dcE\u00eb\u0090";
                            var18_6 = 17;
                            var15_7 = 8;
                            var14_8 = -1;
lbl33:
                            // 2 sources

                            while (true) {
                                v6 = ++var14_8;
                                v4 = var16_5.substring(v6, v6 + var15_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl38:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = AuraRotationMode.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var19_9 = var12_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl50:
                        // 1 sources

                        ** continue;
                    }
                }
                AuraRotationMode.e = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "6\u00ceYV\u00ff\u00d9\u00d3\u00b7\u00ed\u00d5\u00dd\u0014T\u00ca\u00d4\u009d\u00a4\u00de\u00e8\u00bbOMXA";
                var5_15 = 24;
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
lbl75:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u0003\u00c5\\\u00a7\u00c3)\u00e6\u00d5\u00ba\u0083%\"\u001a*\u009f\u00f4";
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
lbl88:
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
lbl101:
                // 1 sources

                ** continue;
            }
        }
        AuraRotationMode.c = var6_12;
        AuraRotationMode.d = new Integer[5];
        AuraRotationMode.SNAP = new AuraRotationMode(var11_3[6], 0, (AimMode)SnapAim.INSTANCE);
        AuraRotationMode.DEFAULT = new AuraRotationMode(var11_3[5], 1, (AimMode)DefaultAim.INSTANCE);
        AuraRotationMode.NONE = new AuraRotationMode(var11_3[0], 2, (AimMode)NoAim.INSTANCE);
        AuraRotationMode.VULCAN = new AuraRotationMode(var11_3[7], 3, (AimMode)VulcanAim.INSTANCE);
        AuraRotationMode.HVH = new AuraRotationMode(var11_3[3], 4, (AimMode)HvhAim.INSTANCE);
        AuraRotationMode.POLAR_SEP_25 = new AuraRotationMode(var11_3[2], 5, (AimMode)PolarAim.INSTANCE);
        AuraRotationMode.GRIM_SILENT = new AuraRotationMode(var11_3[1], 6, (AimMode)NoAim.INSTANCE);
        AuraRotationMode.ADVANCED = new AuraRotationMode(var11_3[4], 7, (AimMode)AimSettings.INSTANCE);
        AuraRotationMode.$VALUES = AuraRotationMode.$values();
        AuraRotationMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])AuraRotationMode.$VALUES);
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

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x259F;
        if (d[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = c[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])e.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/g9", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AuraRotationMode.d[n2] = n3;
        }
        return d[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AuraRotationMode.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/g9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AuraRotationMode.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

