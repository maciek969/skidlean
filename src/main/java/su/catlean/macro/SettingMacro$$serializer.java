/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

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
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.macro.SettingMacro;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSerializer;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class SettingMacro$$serializer
implements GeneratedSerializer {
    @NotNull
    public static final SettingMacro$$serializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private SettingMacro$$serializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull SettingMacro settingMacro) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(settingMacro, "value");
        SerialDescriptor serialDescriptor = SettingMacro$$serializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        SettingMacro.write$Self(settingMacro, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final SettingMacro deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = SettingMacro$$serializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        String string = null;
        Bind bind = null;
        boolean bl2 = false;
        String string2 = null;
        String string3 = null;
        String string4 = null;
        String string5 = null;
        boolean bl3 = false;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        if (compositeDecoder.decodeSequentially()) {
            string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
            bind = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, BindSerializer.INSTANCE, bind);
            bl2 = compositeDecoder.decodeBooleanElement(serialDescriptor, 2);
            string2 = compositeDecoder.decodeStringElement(serialDescriptor, 3);
            string3 = compositeDecoder.decodeStringElement(serialDescriptor, 4);
            string4 = compositeDecoder.decodeStringElement(serialDescriptor, 5);
            string5 = compositeDecoder.decodeStringElement(serialDescriptor, 6);
            bl3 = compositeDecoder.decodeBooleanElement(serialDescriptor, 7);
            n = 255;
        } else {
            block11: while (bl) {
                int n2 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n2) {
                    case -1: {
                        bl = false;
                        continue block11;
                    }
                    case 0: {
                        string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
                        n |= 1;
                        continue block11;
                    }
                    case 1: {
                        bind = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, BindSerializer.INSTANCE, bind);
                        n |= 2;
                        continue block11;
                    }
                    case 2: {
                        bl2 = compositeDecoder.decodeBooleanElement(serialDescriptor, 2);
                        n |= 4;
                        continue block11;
                    }
                    case 3: {
                        string2 = compositeDecoder.decodeStringElement(serialDescriptor, 3);
                        n |= 8;
                        continue block11;
                    }
                    case 4: {
                        string3 = compositeDecoder.decodeStringElement(serialDescriptor, 4);
                        n |= 0x10;
                        continue block11;
                    }
                    case 5: {
                        string4 = compositeDecoder.decodeStringElement(serialDescriptor, 5);
                        n |= 0x20;
                        continue block11;
                    }
                    case 6: {
                        string5 = compositeDecoder.decodeStringElement(serialDescriptor, 6);
                        n |= 0x40;
                        continue block11;
                    }
                    case 7: {
                        bl3 = compositeDecoder.decodeBooleanElement(serialDescriptor, 7);
                        n |= 0x80;
                        continue block11;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new SettingMacro(n, string, bind, bl2, string2, string3, string4, string5, bl3, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArray = new KSerializer[]{StringSerializer.INSTANCE, BindSerializer.INSTANCE, BooleanSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, BooleanSerializer.INSTANCE};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (SettingMacro)object);
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
                        SettingMacro$$serializer.b = ZkmKeys.a(-6926252119342766053L, 669847585534603309L, MethodHandles.lookup().lookupClass()).a(172950349749411L);
                        var20 = SettingMacro$$serializer.b ^ 139980905481637L;
                        SettingMacro$$serializer.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[12];
                        var16_4 = 0;
                        var15_5 = "w\u00f8\u008a\u009br\u009at\u0005\u00cdL\u00b1g\u00feJ\u009a\u0099\u0010d:\u00b0\u00adc{\u001fxi\u00d2\u00b5m\u0003\u00a1\u00ec^\u0010I\u00f4\u000bJ\u0082\u00d6\u0086\u00bc\u0095^\u000b\u001b\u0003\u0089\u009b\\\u0010\u00ae#\"\u009d\u009e\u00bb\u00ffo\u00d5\u00a9\u00112\fN=\u00c3\u0010\u001e\u00d0\bIph\u0010a\u0088\u007f\u00e8+\u00bb\u00d3\u00c4\u0084\u0010\u00d4\b\u009c\u00df5>T\u00bem4\u00a3+\u00a22\u00a1\u00a7\u0010\u0082\u00b1\u00b2B\u00dd&\u00cb\u00a3\u0085\u0091U\u00a3\u00fb\u00f1\u00b4h\u0010\u0096\u00c2\u0000\u0097\u00c5\u00968\u00bbAB\u00c9\u009eG\u0098\u00c3m\u0018\u00c4x\u0091\u009fV\u0003\u00cb\u0004\u00a1\u000e)\u009b\u008e\u0087NXA\u00ce\u00c9\u00b0+J\u0085\u0088 \u0085\u008d#\u00b9\u00e8{\u00c9\u009d\u00ae\u0080\u0015\u00db\u00b1\u00b5}\u00be\u00b8\u00b2\"8\u00a0\u001c\u00df\u009e\u008d\u00f1\u00d8\u001b_\u0006\u008f\u00b2";
                        var17_6 = 193;
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
                            var18_3[var16_4++] = SettingMacro$$serializer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00d1<\u00ae\ta\u0090\u0086Rw\u00adc\u00ed\u0081!I\u000e\u0018\u00eb\u00e7\u009f|\u00f0Yo\u00a3\u00de\u00ca|Gw\u00dd\"\u001f7g\u001b\u00c3\u001eP\u00ec\u0092";
                            var17_6 = 41;
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
                            var18_3[var16_4++] = SettingMacro$$serializer.zkmDecodeUtf8(var19_9).intern();
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
                SettingMacro$$serializer.c = var18_3;
                SettingMacro$$serializer.d = new String[12];
                SettingMacro$$serializer.h = new HashMap<K, V>(13);
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
                var6_12 = new long[14];
                var3_13 = 0;
                var4_14 = "\u00af@\fhUpu\u00b3gSkt\u00ab> \u00abj\u0089<\u00bf\u00b1\\O\u008ea7\u00ea\u0093T\u0096:\u0080\u009bd\u00afL\u00df\u00ac\u001eA\u00d7\u00c1\u00ee\u00c5J\u00d5\u001d\u0098.\u00a4\u00804&\u009egb\u00af\u00db\u00de\u00bb\f\u00df\u00c4u\u00d9rl\u00f0\u0086\u000bK\u0013i\u00ac\u00ed\u0005\u00b2\u008eir\u0093i\t\u00b1*\u00cfrX\u00f60%\u00ce3g\u00fee";
                var5_15 = 96;
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
                    var4_14 = "h\u0080\u0096O\u00b1\u00e5\u00c9\tH\u00c8\u009aI\u00d6\u00cfJ\u00db";
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
        SettingMacro$$serializer.f = var6_12;
        SettingMacro$$serializer.g = new Integer[14];
        SettingMacro$$serializer.INSTANCE = new SettingMacro$$serializer();
        var22_20 = new PluginGeneratedSerialDescriptor("su.catlean.nh", SettingMacro$$serializer.INSTANCE, 8);
        var22_20.addElement("id", false);
        var22_20.addElement("bind", false);
        var22_20.addElement("enabled", false);
        var22_20.addElement("module", false);
        var22_20.addElement("setting", false);
        var22_20.addElement("pressValue", false);
        var22_20.addElement("releaseValue", false);
        var22_20.addElement("hold", false);
        SettingMacro$$serializer.serialDescriptor = var22_20;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x622A;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/rg", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            SettingMacro$$serializer.d[n2] = SettingMacro$$serializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingMacro$$serializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7B5F;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/rg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SettingMacro$$serializer.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SettingMacro$$serializer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/rg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingMacro$$serializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SettingMacro$$serializer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

