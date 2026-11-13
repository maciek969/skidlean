/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.ModuleConfigSerializer;
import su.catlean.macro.ChatMacro$$serializer;
import su.catlean.macro.Macros$Companion;
import su.catlean.macro.MacrosSerializer;
import su.catlean.macro.SettingMacro$$serializer;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class Macros {
    @NotNull
    public static final Macros$Companion Companion;
    @NotNull
    private final List settingMacros;
    @NotNull
    private final List chatMacros;
    @NotNull
    private final List itemMacros;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public Macros(@NotNull List list, @NotNull List list2, @NotNull List list3) {
        Intrinsics.checkNotNullParameter(list, "settingMacros");
        Intrinsics.checkNotNullParameter(list2, "chatMacros");
        Intrinsics.checkNotNullParameter(list3, "itemMacros");
        this.settingMacros = list;
        this.chatMacros = list2;
        this.itemMacros = list3;
    }

    public Macros(List list, List list2, List list3, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            list = new ArrayList();
        }
        if ((n & 2) != 0) {
            list2 = new ArrayList();
        }
        if ((n & 4) != 0) {
            list3 = new ArrayList();
        }
        this(list, list2, list3);
    }

    @NotNull
    public final List getSettingMacros() {
        return this.settingMacros;
    }

    @NotNull
    public final List getChatMacros() {
        return this.chatMacros;
    }

    @NotNull
    public final List getItemMacros() {
        return this.itemMacros;
    }

    @NotNull
    public final List getSettingMacros2() {
        return this.settingMacros;
    }

    @NotNull
    public final List getChatMacros2() {
        return this.chatMacros;
    }

    @NotNull
    public final List getItemMacros2() {
        return this.itemMacros;
    }

    @NotNull
    public final Macros copy(@NotNull List list, @NotNull List list2, @NotNull List list3) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)Macros.zkmBootstrap("x", (int)25230, (long)(0x5070D763364C27E5L ^ l))));
        Intrinsics.checkNotNullParameter(list2, (String)((Object)Macros.zkmBootstrap("x", (int)671, (long)(0x1DBF09627E5347F1L ^ l))));
        Intrinsics.checkNotNullParameter(list3, (String)((Object)Macros.zkmBootstrap("x", (int)32562, (long)(0x30C5E74BBE403A5DL ^ l))));
        return new Macros(list, list2, list3);
    }

    public static Macros copy$default(Macros macros, List list, List list2, List list3, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5013290779134722907L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                list = macros.settingMacros;
            }
            n2 = n & 2;
        }
        if (callSite != null) {
            if (n2 != 0) {
                list2 = macros.chatMacros;
            }
            n2 = n & 4;
        }
        if (n2 != 0) {
            list3 = macros.itemMacros;
        }
        return macros.copy(list, list2, list3);
    }

    @NotNull
    public String toString() {
        List list = this.itemMacros;
        List list2 = this.chatMacros;
        List list3 = this.settingMacros;
        return "MacroConfig(settingMacros=" + list3 + ", chatMacros=" + list2 + ", itemMacros=" + list + ")";
    }

    public int hashCode() {
        int n = ((Object)this.settingMacros).hashCode();
        n = n * 31 + ((Object)this.chatMacros).hashCode();
        n = n * 31 + ((Object)this.itemMacros).hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Macros)) {
            return false;
        }
        Macros macros = (Macros)object;
        if (!Intrinsics.areEqual(this.settingMacros, macros.settingMacros)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.chatMacros, macros.chatMacros)) {
            return false;
        }
        return Intrinsics.areEqual(this.itemMacros, macros.itemMacros);
    }

    @JvmStatic
    public static final void write$Self(Macros macros, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block2: {
            Lazy[] lazyArray = $childSerializers;
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) ? true : !Intrinsics.areEqual(macros.settingMacros, new ArrayList())) {
                compositeEncoder.encodeSerializableElement(serialDescriptor, 0, (SerializationStrategy)lazyArray[0].getValue(), macros.settingMacros);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) ? true : !Intrinsics.areEqual(macros.chatMacros, new ArrayList())) {
                compositeEncoder.encodeSerializableElement(serialDescriptor, 1, (SerializationStrategy)lazyArray[1].getValue(), macros.chatMacros);
            }
            if (!(compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) ? true : !Intrinsics.areEqual(macros.itemMacros, new ArrayList()))) break block2;
            compositeEncoder.encodeSerializableElement(serialDescriptor, 2, (SerializationStrategy)lazyArray[2].getValue(), macros.itemMacros);
        }
    }

    public Macros(int n, List list, List list2, List list3, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & n) != 0) {
            PluginExceptionsKt.throwMissingFieldException(n, 0, MacrosSerializer.INSTANCE.getDescriptor());
        }
        this.settingMacros = (n & 1) == 0 ? (List)new ArrayList() : list;
        this.chatMacros = (n & 2) == 0 ? (List)new ArrayList() : list2;
        this.itemMacros = (n & 4) == 0 ? (List)new ArrayList() : list3;
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(SettingMacro$$serializer.INSTANCE);
    }

    private static final KSerializer serializer2() {
        return new ArrayListSerializer(ChatMacro$$serializer.INSTANCE);
    }

    private static final KSerializer serializer3() {
        return new ArrayListSerializer(ModuleConfigSerializer.INSTANCE);
    }

    public Macros() {
        long l = 0L;
        l = a ^ l;
        this(null, null, null, (int)Macros.zkmBootstrap2("k", (int)6702, (long)(0x77D19DC19F3B052CL ^ l)), null);
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        Macros.a = ZkmKeys.a(7138261453855312611L, -4179142141675755360L, MethodHandles.lookup().lookupClass()).a(13126021563988L);
                        Macros.d = new HashMap<K, V>(13);
                        var11 = Macros.a ^ 110154041240267L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[9];
                        var18_4 = 0;
                        var17_5 = "\u00e1RN\u000eq\u00b0\u00fc\u0092\fy\u00a0\u00d3\u00b7\u00b7b\u00ed\u0005\u0019\u0016\u008a&\r\u0097o\u0018H\u0011\u0002n\u001b\u0095[\u000b\u008c\u00b5}g\u00a7r\u00f7\u00d7\u00f8Nx\u0089w\u0001\u00a8q \u00cc\u00d3\u0004\u00af\u0094\u000b\u00f9\u00d2\u00a7\u00d3G+\u00170\u00e6q\u00fa\u00da\u00ec>\tl{?\u0080(\u000e\u00ed\u0016\u001fiR \u0007\u00f3QS\u00f6\u00a08\u008e\u0081l\u001c\u00a2\u00d6\u00f6\u0003e1\u009b7\u001c\u0088\u0002h\u00c9/\u00b6\u00a0\u00f8\u00a0\u0015\u00f7\u0089 -\u0092\u00c3\u00d1`M\u00e3h\u00d3\u00d4@\u001e\u0082\u00cc\u00ea\u00ad=D\u000b:\u00f1\u00fb\u0089\u00ff\u00cf\u00b7g\u00b5\u00fd\u00da\u00f0\u00c6\u0018\u009f\u00c0$\u00b0t,A\u0002\u00a4\u00b1\u009eP,\u00c9\u000b\u0083*X\u009bz'\u008a\u0097\u00a7 \u00b8\u00a6L?\u00e0'\u008e\u008a\u0017\u00bc\u00e0'\u00f1\u00ac\u00b7u\u008a\u00e3\u00abT)\u0011\u00ea\u00b0\u00fd\u009aG\u00ad\u008e/\u00ca\u00bc";
                        var19_6 = 206;
                        var16_7 = 24;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = Macros.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u0013[\u00de\u0094`\u0013\u00a2#^T\u00db\"xM\u0097\u00ec\u0017\u0082\u008d\u0088u\u008d!\u0005\u0098\u00ef\u00b3\u00c7\"\u00de7\u00ab0~\u00b2rX\u0002F\u00cc\u00d7*l.\u00d4j\u0014\u00c3\u00a5\u00ff\u00f6\\\u0099\u00b0M\u0082\u0014\u00fc,\u0004g\u00c0 \nM\u0005\u00d3W\u0097\u0003\u0089\u0018\u0004\u00ae!A\u00dc\u0081\u0083\u0099m";
                            var19_6 = 81;
                            var16_7 = 32;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = Macros.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                Macros.b = var20_3;
                Macros.c = new String[9];
                Macros.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\"\u00e4\u00aa\u00b8\u00f5A\u0010\u0003\u00dc\u0099\u008fX\u000e\u007f\u00f6D\u00d1\u00b9\u0094\u0088\u00cb\u00dd\u00f4\u00dd";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
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
        Macros.e = var6_12;
        Macros.f = new Integer[3];
        Macros.Companion = new Macros$Companion(null);
        var22_20 = new Lazy[]{LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer2(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer3(), ()Lkotlinx/serialization/KSerializer;)())};
        Macros.$childSerializers = var22_20;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2226;
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
                throw new RuntimeException("su/catlean/m", exception);
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
            Macros.c[n2] = Macros.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Macros.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/m" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x69E6;
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
                throw new RuntimeException("su/catlean/m", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Macros.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Macros.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/m" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Macros.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Macros.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

