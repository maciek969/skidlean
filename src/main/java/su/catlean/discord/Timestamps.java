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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.discord.Timestamps$Companion;
import su.catlean.discord.TimestampsSerializer;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class Timestamps {
    @NotNull
    public static final Timestamps$Companion Companion;
    private final long start;
    @Nullable
    private final Long end;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public Timestamps(long l, @Nullable Long l2) {
        this.start = l;
        this.end = l2;
    }

    public Timestamps(long l, Long l2, DefaultConstructorMarker defaultConstructorMarker) {
        l2 = null;
        this(l, l2);
    }

    public final long getStart() {
        return this.start;
    }

    @Nullable
    public final Long getEnd() {
        return this.end;
    }

    public final long getStart2() {
        return this.start;
    }

    @Nullable
    public final Long getEnd2() {
        return this.end;
    }

    @NotNull
    public final Timestamps copy(long l, @Nullable Long l2) {
        return new Timestamps(l, l2);
    }

    public static Timestamps copy$default(Timestamps timestamps, long l, Long l2, int n, Object object) {
        long l3 = 0L;
        l3 = a ^ l3;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3361732830364849267L, (long)l3);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                l = timestamps.start;
            }
            n2 = n & 2;
        }
        if (n2 != 0) {
            l2 = timestamps.end;
        }
        return timestamps.copy(l, l2);
    }

    @NotNull
    public String toString() {
        Long l = this.end;
        long l2 = this.start;
        return "ActivityTimestamp(start=" + l2 + ", end=" + l + ")";
    }

    public int hashCode() {
        int n = Long.hashCode(this.start);
        n = n * (int)e + (this.end == null ? 0 : ((Object)this.end).hashCode());
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Timestamps)) {
            return false;
        }
        Timestamps timestamps = (Timestamps)object;
        if (this.start != timestamps.start) {
            return false;
        }
        return Intrinsics.areEqual(this.end, timestamps.end);
    }

    @JvmStatic
    public static final void write$Self(Timestamps timestamps, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block1: {
            long l = 0L;
            int n = 0;
            long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2085309356539864613L, (long)l2);
            compositeEncoder.encodeLongElement(serialDescriptor, 0, timestamps.start);
            CallSite callSite2 = callSite;
            boolean bl = compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1);
            if (callSite2 != null) {
                bl = bl ? true : timestamps.end != null;
            }
            if (!bl) break block1;
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 1, LongSerializer.INSTANCE, timestamps.end);
        }
    }

    public Timestamps(short s, int n, long l, Long l2, SerializationConstructorMarker serializationConstructorMarker) {
        block7: {
            block4: {
                Timestamps timestamps;
                block6: {
                    CallSite callSite;
                    block5: {
                        long l3 = 0L;
                        long l4 = (l3 << 16 | (long)s << 48 >>> 48) ^ a;
                        callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2449271169766939434L, (long)l4);
                        if (1 != (1 & n)) {
                            PluginExceptionsKt.throwMissingFieldException(n, 1, TimestampsSerializer.INSTANCE.getDescriptor());
                        }
                        timestamps = this;
                        if (callSite == null) break block4;
                        timestamps.start = l;
                        if (s <= 0) break block5;
                        if ((n & 2) != 0) break block6;
                        this.end = null;
                    }
                    if (callSite != null) break block7;
                }
                timestamps = this;
            }
            timestamps.end = l2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-5928698929605315017L, -7512739480564883346L, MethodHandles.lookup().lookupClass()).a(75149538775398L);
        d = new HashMap(13);
        long l = a ^ 0x395CEF2A654FL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[2];
        int n = 0;
        String string = "3}\u00d6\u00a1K+\u00cd@\u00bd\u00f0\u00f3W\u00a8\u0014\u00a1\u001c8\u00bb\u00bb\u0081\u00c6\r\u00a1\u0087\u00072\b\u0010\u0010\rC\u00029}ry\u00ad\u001c\u00f5\u0006\u00d38\u0084\u0091\u0000b9Mpi \u00a1\b\u0088o\u00bf\u001fm\u008e6\u0083\u00ab\u0017\u00a2\u00d2\u009bUE[=a\u0082m";
        int n2 = 73;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = Timestamps.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[2];
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n6 = 1;
        while (true) {
            if (n6 >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{-94, 12, 90, 16, -55, -122, -15, -46});
                e = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                Companion = new Timestamps$Companion(null);
                return;
            }
            byArray5 = byArray5;
            byArray5[n6] = (byte)(l << n6 * 8 >>> 56);
            ++n6;
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7FC7;
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
                throw new RuntimeException("su/catlean/pa", exception);
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
            Timestamps.c[n2] = Timestamps.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Timestamps.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pa" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Timestamps.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

