/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import su.catlean.setting.Bind;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class BindSerializer
implements GeneratedSerializer {
    @NotNull
    public static final BindSerializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static int[] s;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    private BindSerializer() {
    }

    public final void serialize(@NotNull Encoder encoder, int n, @NotNull Bind bind, char c, char c2) {
        long l = ((long)n << 32 | (long)c << 48 >>> 32 | (long)c2 << 48 >>> 48) ^ b;
        long l2 = l ^ 0x1BDEBB6330FAL;
        int n2 = (int)(l2 >>> 32);
        int n3 = (int)(l2 << 32 >>> 56);
        int n4 = (int)(l2 << 40 >>> 40);
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(bind, "value");
        SerialDescriptor serialDescriptor = BindSerializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        Bind.write$Self(bind, compositeEncoder, n2, (byte)n3, serialDescriptor, n4);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final Bind deserialize(long l, @NotNull Decoder decoder) {
        int n = (int)(((l = b ^ l) ^ 0x6E4538D7203FL) << 56 >>> 56);
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = BindSerializer.serialDescriptor;
        boolean bl = true;
        int n2 = 0;
        int n3 = 0;
        boolean bl2 = false;
        boolean bl3 = false;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        if (compositeDecoder.decodeSequentially()) {
            n3 = compositeDecoder.decodeIntElement(serialDescriptor, 0);
            bl2 = compositeDecoder.decodeBooleanElement(serialDescriptor, 1);
            bl3 = compositeDecoder.decodeBooleanElement(serialDescriptor, 2);
            n2 = 7;
        } else {
            block6: while (bl) {
                int n4 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n4) {
                    case -1: {
                        bl = false;
                        continue block6;
                    }
                    case 0: {
                        n3 = compositeDecoder.decodeIntElement(serialDescriptor, 0);
                        n2 |= 1;
                        continue block6;
                    }
                    case 1: {
                        bl2 = compositeDecoder.decodeBooleanElement(serialDescriptor, 1);
                        n2 |= 2;
                        continue block6;
                    }
                    case 2: {
                        bl3 = compositeDecoder.decodeBooleanElement(serialDescriptor, 2);
                        n2 |= 4;
                        continue block6;
                    }
                }
                throw new UnknownFieldException(n4);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new Bind(n2, n3, bl2, bl3, null, (byte)n);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        KSerializer[] kSerializerArray = new KSerializer[]{IntSerializer.INSTANCE, BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        long l = b ^ 0x57BFE3A754B2L;
        long l2 = l ^ 0x6E023995B031L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        this.serialize(encoder, n, (Bind)object, (char)n2, (char)n3);
    }

    @Override
    public Object deserialize(Decoder decoder) {
        long l = b ^ 0x1CC6817851D7L;
        long l2 = l ^ 0x229311372E37L;
        return this.deserialize(l2, decoder);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                BindSerializer.b = ZkmKeys.a(2048381505463177781L, 992294576496061884L, MethodHandles.lookup().lookupClass()).a(262362797778263L);
                var9 = BindSerializer.b ^ 96928580885022L;
                BindSerializer.e = new HashMap<K, V>(13);
                BindSerializer.zkmSetFlag(new int[2]);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[7];
                var5_4 = 0;
                var4_5 = "n;\u00bf\u00feHs\u00f4g\u00f5X\u00da\u0019\u00a1\u0017\u00bf\u00a4\u0010h6{\u00da\u0087\u00cb\u00b4\u00b8x\u0010\u00f9\u001c#f\u00fb\u0013\u0010c\u00ee\u00d7\u0092T\u00bc\u00c4\u00a1\u00d9rf\u008cc\u00a4\u00cb}\u0010\u0005A\u00b0\u0001\u00f3\u000f\u00a0\u00b9\u0086\u0085}\u00c8\u0097`\u0098T\u0010\"\u0088\u00b3\u00ba\u007f\u00e4\u00dc\u00a0\u00bb\u00fcR\u0001(\u001d\u008a\u00e3";
                var6_6 = 84;
                var3_7 = 16;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = BindSerializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "x\u0098f\u0091\u008fd\u0015,\u00fc%n\u0013\u00e5\u008a\u00de\u0007\u00aa\u00b9\u00ea\u000f\u00c3\u00cf\u00e9.<i\u007f\u0080Y:b\u0011\u0010!\u00c7\u00cb}\b\u00e0{\u00abC\u0007\u0018\u001f\u0094\u00c0\u0012\u00fc";
                    var6_6 = 49;
                    var3_7 = 32;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = BindSerializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        BindSerializer.c = var7_3;
        BindSerializer.d = new String[7];
        BindSerializer.INSTANCE = new BindSerializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.lj", BindSerializer.INSTANCE, 3);
        var11_10.addElement("key", true);
        var11_10.addElement("hold", true);
        var11_10.addElement("mouse", true);
        BindSerializer.serialDescriptor = var11_10;
    }

    public static void zkmSetFlag(int[] nArray) {
        s = nArray;
    }

    public static int[] zkmGetFlag() {
        return s;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x417B;
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
                throw new RuntimeException("su/catlean/ss", exception);
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
            BindSerializer.d[n2] = BindSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BindSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ss" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BindSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

