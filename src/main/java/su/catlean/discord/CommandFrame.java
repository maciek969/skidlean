/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.discord.ActivityPayload;
import su.catlean.discord.ActivityPayloadSerializer;
import su.catlean.discord.CommandFrame$$serializer;
import su.catlean.discord.CommandFrame$Companion;
import su.catlean.discord.IpcCommand;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class CommandFrame {
    @NotNull
    public static final CommandFrame$Companion Companion;
    @NotNull
    private final IpcCommand cmd;
    @NotNull
    private final ActivityPayload args;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public CommandFrame(@NotNull IpcCommand ipcCommand, @NotNull ActivityPayload activityPayload) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter((Object)ipcCommand, (String)((Object)CommandFrame.zkmBootstrap("s", (int)24956, (long)(0x5A116900B76983A8L ^ l))));
        Intrinsics.checkNotNullParameter(activityPayload, (String)((Object)CommandFrame.zkmBootstrap("s", (int)5697, (long)(0x24860DFD1B5AF494L ^ l))));
        this.cmd = ipcCommand;
        this.args = activityPayload;
    }

    @NotNull
    public final IpcCommand getCmd() {
        return this.cmd;
    }

    @NotNull
    public final ActivityPayload getArgs() {
        return this.args;
    }

    @NotNull
    public final IpcCommand getCmd2() {
        return this.cmd;
    }

    @NotNull
    public final ActivityPayload getArgs2() {
        return this.args;
    }

    @NotNull
    public final CommandFrame copy(@NotNull IpcCommand ipcCommand, @NotNull ActivityPayload activityPayload) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter((Object)ipcCommand, (String)((Object)CommandFrame.zkmBootstrap("s", (int)16825, (long)(0x78CF7AF0A3D37B0EL ^ l))));
        Intrinsics.checkNotNullParameter(activityPayload, (String)((Object)CommandFrame.zkmBootstrap("s", (int)17399, (long)(0x175020AA87F27941L ^ l))));
        return new CommandFrame(ipcCommand, activityPayload);
    }

    public static CommandFrame copy$default(CommandFrame commandFrame, IpcCommand ipcCommand, ActivityPayload activityPayload, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5925890632610215963L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                ipcCommand = commandFrame.cmd;
            }
            n2 = n & 2;
        }
        if (n2 != 0) {
            activityPayload = commandFrame.args;
        }
        return commandFrame.copy(ipcCommand, activityPayload);
    }

    @NotNull
    public String toString() {
        return "FramePayload(cmd=" + this.cmd + ", args=" + this.args + ")";
    }

    public int hashCode() {
        int n = this.cmd.hashCode();
        n = n * (int)e + this.args.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CommandFrame)) {
            return false;
        }
        CommandFrame commandFrame = (CommandFrame)object;
        if (this.cmd != commandFrame.cmd) {
            return false;
        }
        return Intrinsics.areEqual(this.args, commandFrame.args);
    }

    @JvmStatic
    public static final void write$Self(CommandFrame commandFrame, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeSerializableElement(serialDescriptor, 0, (SerializationStrategy)lazyArray[0].getValue(), commandFrame.cmd);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, ActivityPayloadSerializer.INSTANCE, commandFrame.args);
    }

    public CommandFrame(int n, IpcCommand ipcCommand, ActivityPayload activityPayload, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 3, CommandFrame$$serializer.INSTANCE.getDescriptor());
        }
        this.cmd = ipcCommand;
        this.args = activityPayload;
    }

    private static final KSerializer serializer() {
        return IpcCommand.Companion.serializer();
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    CommandFrame.a = ZkmKeys.a(165739871072492581L, 1490603611413866590L, MethodHandles.lookup().lookupClass()).a(120921302522548L);
                    CommandFrame.d = new HashMap<K, V>(13);
                    var5 = CommandFrame.a ^ 82618394672047L;
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
                    var11_5 = "\u00c0X\u00e6\u00fd\u00d8\u00f0\u001a\u0082\u0086s\u00e0\u009c\u00c9\u00fe\u00eb\u0006\u0010m4\u00c3R\u00ad\u00ea#\\\u007fG\u00c8\u0004\u00a4\u009bf\u00a1\u0010\u008dZ\u0086\u001e\u00e7\u00a8F\u0013p'~\u00b6\u00f1\u00f9V\u00e4\u0010\u00aa\u00ee\u00ad\u00c74c\u008c\u0091)\u00d9D\u00add\u00d7\u00f2\u0011";
                    var13_6 = 67;
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
                        var14_3[var12_4++] = CommandFrame.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "#G\u00e5\u0092\u00cc\n\u00fd\u008e\u0007t\u00fd\u0019M\u00f1\u008c\u00f6(5\u00bfE\u00f3 6\u009a\u00e3y\u00bc9S\u00c3\u00ff\u0093\u00f1(\u007f\u00e2\u008f\u00d8\u00d5\u00efo\u00cb\u00c1\u00e82\u009b\u00db\u00ab\u008cf\u0004Q*\u00ed\u009by\u00bc";
                        var13_6 = 57;
                        var10_7 = 16;
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
                        var14_3[var12_4++] = CommandFrame.zkmDecodeUtf8(var15_9).intern();
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
            CommandFrame.b = var14_3;
            CommandFrame.c = new String[6];
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
        var4_12 = var0_10.doFinal(new byte[]{105, 26, -6, 49, 84, 106, 9, -4});
        ** while (true)
        CommandFrame.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        CommandFrame.Companion = new CommandFrame$Companion(null);
        var16_13 = new Lazy[]{LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)()), null};
        CommandFrame.$childSerializers = var16_13;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x693E;
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
                throw new RuntimeException("su/catlean/f8", exception);
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
            CommandFrame.c[n2] = CommandFrame.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CommandFrame.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/f8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CommandFrame.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

