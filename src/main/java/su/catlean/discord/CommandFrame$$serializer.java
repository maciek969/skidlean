/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

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
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;
import su.catlean.discord.ActivityPayload;
import su.catlean.discord.ActivityPayloadSerializer;
import su.catlean.discord.CommandFrame;
import su.catlean.discord.IpcCommand;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class CommandFrame$$serializer
implements GeneratedSerializer {
    @NotNull
    public static final CommandFrame$$serializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private CommandFrame$$serializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull CommandFrame commandFrame) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(commandFrame, "value");
        SerialDescriptor serialDescriptor = CommandFrame$$serializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        CommandFrame.write$Self(commandFrame, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final CommandFrame deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = CommandFrame$$serializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        IpcCommand ipcCommand = null;
        Object var4_6 = null;
        ActivityPayload activityPayload = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArray = CommandFrame.get$childSerializers();
        if (compositeDecoder.decodeSequentially()) {
            ipcCommand = compositeDecoder.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), ipcCommand);
            activityPayload = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, ActivityPayloadSerializer.INSTANCE, activityPayload);
            n = 3;
        } else {
            block5: while (bl) {
                int n2 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n2) {
                    case -1: {
                        bl = false;
                        continue block5;
                    }
                    case 0: {
                        ipcCommand = compositeDecoder.decodeSerializableElement(serialDescriptor, 0, (DeserializationStrategy)lazyArray[0].getValue(), ipcCommand);
                        n |= 1;
                        continue block5;
                    }
                    case 1: {
                        activityPayload = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, ActivityPayloadSerializer.INSTANCE, activityPayload);
                        n |= 2;
                        continue block5;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new CommandFrame(n, ipcCommand, activityPayload, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        Lazy[] lazyArray = CommandFrame.get$childSerializers();
        KSerializer[] kSerializerArray = new KSerializer[]{lazyArray[0].getValue(), ActivityPayloadSerializer.INSTANCE};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (CommandFrame)object);
    }

    @Override
    public Object deserialize(Decoder decoder) {
        return this.deserialize2(decoder);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                CommandFrame$$serializer.a = ZkmKeys.a(-543419065803847783L, 4523165465334040761L, MethodHandles.lookup().lookupClass()).a(11719665911028L);
                var9 = CommandFrame$$serializer.a ^ 107347792807755L;
                CommandFrame$$serializer.d = new HashMap<K, V>(13);
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
                var7_3 = new String[6];
                var5_4 = 0;
                var4_5 = "S\u00e2n\u0014\u00c8\u0017E\u0016\u00c5)yrw\u00c2\u00cd5\u0010\u00c3.g{\u00bc\u00867\u00bd\u00af\u00a3$\r\u00ed\u0014\u001e\u0091\u0010\u0004\u0087\u00ed'\u00aby.\u00fd]\u0005\u00d75\u00e1/P\u00ed\u0010\u00ee\u0096`\u00b8\u000fp\u0015\u0089\u00c0L\u0097\u00da\u00fc\u00b3?\u00a7";
                var6_6 = 67;
                var3_7 = 16;
                var2_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = CommandFrame$$serializer.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00a0tC|\u00cb9\u00d8qV0S\u0085G\u0016\u009a\u00d7 \u00f2\u0005\u00c3\u00c4\u0098>I\u0014o\u00ad\u00fd\u0092=\u0091\u00ddu\u0085\u00fdd0t,\u0000\u0096O\u00ccG\u00ef\u000eKx\u009c";
                    var6_6 = 49;
                    var3_7 = 16;
                    var2_8 = -1;
lbl34:
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
lbl39:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = CommandFrame$$serializer.zkmDecodeUtf8(var8_9).intern();
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
lbl51:
                // 1 sources

                ** continue;
            }
        }
        CommandFrame$$serializer.b = var7_3;
        CommandFrame$$serializer.c = new String[6];
        CommandFrame$$serializer.INSTANCE = new CommandFrame$$serializer();
        var11_10 = new PluginGeneratedSerialDescriptor("su.catlean.f8", CommandFrame$$serializer.INSTANCE, 2);
        var11_10.addElement("cmd", false);
        var11_10.addElement("args", false);
        CommandFrame$$serializer.serialDescriptor = var11_10;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xF91;
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
                throw new RuntimeException("su/catlean/wr", exception);
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
            CommandFrame$$serializer.c[n2] = CommandFrame$$serializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CommandFrame$$serializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wr" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CommandFrame$$serializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

