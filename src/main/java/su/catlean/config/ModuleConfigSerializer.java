/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

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
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ModuleConfig;
import su.catlean.setting.BindSerializer;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class ModuleConfigSerializer
implements GeneratedSerializer {
    @NotNull
    public static final ModuleConfigSerializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private ModuleConfigSerializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull ModuleConfig moduleConfig) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(moduleConfig, "value");
        SerialDescriptor serialDescriptor = ModuleConfigSerializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        ModuleConfig.write$Self(moduleConfig, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    /*
     * Unable to fully structure code
     */
    @NotNull
    public final ModuleConfig deserialize2(@NotNull Decoder var1_1) {
        block9: {
            block12: {
                block11: {
                    block10: {
                        Intrinsics.checkNotNullParameter(var1_1, "decoder");
                        var5_2 = ModuleConfigSerializer.serialDescriptor;
                        var6_3 = 1;
                        var8_4 = 0;
                        var9_5 = null;
                        var10_6 = null;
                        var4_7 = ModuleConfig.j();
                        var11_8 = false;
                        var12_9 = null;
                        var13_10 = false;
                        v0 = var14_11 = var1_1.beginStructure(var5_2);
                        if (!var4_7) break block10;
                        if (!v0.decodeSequentially()) break block11;
                        v0 = var14_11;
                    }
                    var9_5 = v0.decodeStringElement(var5_2, 0);
                    var10_6 = var14_11.decodeSerializableElement(var5_2, 1, BindSerializer.INSTANCE, var10_6);
                    var11_8 = var14_11.decodeBooleanElement(var5_2, 2);
                    var12_9 = var14_11.decodeStringElement(var5_2, 3);
                    var13_10 = var14_11.decodeBooleanElement(var5_2, 4);
                    var8_4 = 31;
                    if (var4_7) break block12;
                }
                block8: while (var6_3 != 0) {
                    var7_12 = var14_11.decodeElementIndex(var5_2);
                    if (!var4_7) break block9;
                    v1 = var7_12;
                    if (!var4_7) ** GOTO lbl32
                    switch (v1) {
                        case -1: {
                            v1 = var6_3 = 0;
lbl32:
                            // 2 sources

                            if (var4_7) continue block8;
                        }
                        case 0: {
                            var9_5 = var14_11.decodeStringElement(var5_2, 0);
                            var8_4 |= 1;
                            if (var4_7) continue block8;
                        }
                        case 1: {
                            var10_6 = var14_11.decodeSerializableElement(var5_2, 1, BindSerializer.INSTANCE, var10_6);
                            var8_4 |= 2;
                            if (var4_7) continue block8;
                        }
                        case 2: {
                            var11_8 = var14_11.decodeBooleanElement(var5_2, 2);
                            var8_4 |= 4;
                            if (var4_7) continue block8;
                        }
                        case 3: {
                            var12_9 = var14_11.decodeStringElement(var5_2, 3);
                            var8_4 |= 8;
                            if (var4_7) continue block8;
                        }
                        case 4: {
                            var13_10 = var14_11.decodeBooleanElement(var5_2, 4);
                            var8_4 |= 16;
                            if (var4_7) ** break;
                        }
                    }
                    throw new UnknownFieldException(var7_12);
                }
            }
            var14_11.endStructure(var5_2);
        }
        return new ModuleConfig(var8_4, var9_5, var10_6, var11_8, var12_9, var13_10, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArray = new KSerializer[]{StringSerializer.INSTANCE, BindSerializer.INSTANCE, BooleanSerializer.INSTANCE, StringSerializer.INSTANCE, BooleanSerializer.INSTANCE};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (ModuleConfig)object);
    }

    @Override
    public Object deserialize(Decoder decoder) {
        return this.deserialize2(decoder);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ModuleConfigSerializer.a = ZkmKeys.a(-1028490024247149554L, -584545370306137975L, MethodHandles.lookup().lookupClass()).a(57316533129747L);
                        var20 = ModuleConfigSerializer.a ^ 44104586618854L;
                        ModuleConfigSerializer.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "\u00c3\u00c3{vv?}\u00aeR\u00a8\u00e7\u00ddw\f\u00a0\u001c\u0010yk\u0013+*K`\u00c4\u009a\u0094\u00ec@\u009bZ2k\u0010^\u00a6\u00a3\u00a4\u0090\\\u00c1\u00b3!\u0082\n\u00c6\u00f0\u00a9\u00ca\u0018\u0010\u008eKY\u00a8\u001e\u00a1\u0003\u00e8K\u00e7J*\u00d6\u00a7\f!\u0010sL\u0099\u00d2\u00d2\u0001~'\u0013\f\u00a9\u00db\u001d\u00ff\u008c& \r\u00f0(\u001e~\u00fbn\u00e8>\u0081V \u001aZ\u0000\u00e1\u00f0S\u001d37{\u00cfRh6I\u00cb\u000b\u008d$\b\u0010m\u0083\u00fb\u00cas\u00cc\u00a0y8\u00e3\u00f2\u00a4\u0015$\u000b\u001a";
                        var17_6 = 134;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = ModuleConfigSerializer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "Le\u00a9\u00faA\u00f0\u00a3\u00b8\u008ff\u00d5>\u0092!*\u0090\u0010\u0095\u00f1/\u0003\u00a7FjW\u00f63A\u0082\u00f9\u00a4=\u00ab";
                            var17_6 = 33;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = ModuleConfigSerializer.zkmDecodeUtf8(var19_9).intern();
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
                ModuleConfigSerializer.b = var18_3;
                ModuleConfigSerializer.c = new String[9];
                ModuleConfigSerializer.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "wv\u0085B\u0017\u0096%R\u00c1Nb\u0094\u00cb\u008f\u00bc\u00b2";
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
                    var4_14 = "\u00f3\u00a9g\u00a7\u0018\u009d\u00dd\u0083G<\u001b\u00c4\u00f1\u000e(\u00e0";
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
        ModuleConfigSerializer.e = var6_12;
        ModuleConfigSerializer.f = new Integer[4];
        ModuleConfigSerializer.INSTANCE = new ModuleConfigSerializer();
        var22_20 = new PluginGeneratedSerialDescriptor("su.catlean.ft", ModuleConfigSerializer.INSTANCE, 5);
        var22_20.addElement("id", false);
        var22_20.addElement("bind", false);
        var22_20.addElement("enabled", false);
        var22_20.addElement("item", false);
        var22_20.addElement("legit", false);
        ModuleConfigSerializer.serialDescriptor = var22_20;
    }

    private static UnknownFieldException zkmRethrow(UnknownFieldException unknownFieldException) {
        return unknownFieldException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xF51;
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
                throw new RuntimeException("su/catlean/ca", exception);
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
            ModuleConfigSerializer.c[n2] = ModuleConfigSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ModuleConfigSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ca" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6C8C;
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
                throw new RuntimeException("su/catlean/ca", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ModuleConfigSerializer.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ModuleConfigSerializer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ca" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ModuleConfigSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ModuleConfigSerializer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

