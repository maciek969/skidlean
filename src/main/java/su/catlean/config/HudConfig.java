/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
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
import kotlin.collections.CollectionsKt;
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
import su.catlean.config.HudConfig$Companion;
import su.catlean.config.HudConfigSerializer;
import su.catlean.config.SettingsConfigSerializer;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class HudConfig {
    @NotNull
    public static final HudConfig$Companion Companion;
    @NotNull
    private final String id;
    @NotNull
    private List widgets;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static Module[] M;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public HudConfig(@NotNull String string, @NotNull List list) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)HudConfig.zkmBootstrap("r", (int)704, (long)(0x3B34BD839F3A9522L ^ l2))));
        Intrinsics.checkNotNullParameter(list, (String)((Object)HudConfig.zkmBootstrap("r", (int)23805, (long)(0x4252D80D260CB1DL ^ l2))));
        this.id = string;
        this.widgets = list;
    }

    public HudConfig(String string, List list, DefaultConstructorMarker defaultConstructorMarker) {
        list = CollectionsKt.emptyList();
        this(string, list);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final List getWidgets() {
        return this.widgets;
    }

    public final void setWidgets(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.widgets = list;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final List getWidgets2() {
        return this.widgets;
    }

    @NotNull
    public final HudConfig copy(@NotNull String string, @NotNull List list) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)HudConfig.zkmBootstrap("r", (int)12868, (long)(0x7A8D19FC41DA9D5BL ^ l))));
        Intrinsics.checkNotNullParameter(list, (String)((Object)HudConfig.zkmBootstrap("r", (int)18478, (long)(0x866741E6DFA6737L ^ l))));
        return new HudConfig(string, list);
    }

    public static HudConfig copy$default(HudConfig hudConfig, String string, List list, int n, Object object) {
        long l = 0L;
        int n2 = 0;
        long l2 = (l << 32 | (long)n2 << 32 >>> 32) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-7207414037805081294L, (long)l2);
        int n3 = n & 1;
        if (callSite != null) {
            if (n3 != 0) {
                string = hudConfig.id;
            }
            n3 = n & 2;
        }
        if (n3 != 0) {
            list = hudConfig.widgets;
        }
        return hudConfig.copy(string, list);
    }

    @NotNull
    public String toString() {
        return "WidgetConfig(id=" + this.id + ", widgets=" + this.widgets + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * (int)e + ((Object)this.widgets).hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof HudConfig)) {
            return false;
        }
        HudConfig hudConfig = (HudConfig)object;
        if (!Intrinsics.areEqual(this.id, hudConfig.id)) {
            return false;
        }
        return Intrinsics.areEqual(this.widgets, hudConfig.widgets);
    }

    @JvmStatic
    public static final void write$Self(HudConfig hudConfig, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block0: {
            Lazy[] lazyArray = $childSerializers;
            compositeEncoder.encodeStringElement(serialDescriptor, 0, hudConfig.id);
            if (!(compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) ? true : !Intrinsics.areEqual(hudConfig.widgets, CollectionsKt.emptyList()))) break block0;
            compositeEncoder.encodeSerializableElement(serialDescriptor, 1, (SerializationStrategy)lazyArray[1].getValue(), hudConfig.widgets);
        }
    }

    public HudConfig(int n, String string, char c, List list, SerializationConstructorMarker serializationConstructorMarker, int n2) {
        if (1 != (1 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 1, HudConfigSerializer.INSTANCE.getDescriptor());
        }
        if (n2 > 0) {
            this.id = string;
        }
        if (c >= '\u0000') {
            this.widgets = (n & 2) == 0 ? CollectionsKt.emptyList() : list;
        }
    }

    private static final KSerializer serializer() {
        return new ArrayListSerializer(SettingsConfigSerializer.INSTANCE);
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
                    HudConfig.a = ZkmKeys.a(-5104864904788312624L, -1317072020451654299L, MethodHandles.lookup().lookupClass()).a(230134003881481L);
                    var14 = HudConfig.a ^ 23968858558582L;
                    HudConfig.d = new HashMap<K, V>(13);
                    HudConfig.zkmSetFlag(new Module[4]);
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
                    var12_3 = new String[7];
                    var10_4 = 0;
                    var9_5 = "\u009a3\u00e5s\u00ed\u00fd\u00fa\u00ac\n\u00b2\u00fb\u001du\u0007\u008cIJ\u00d5\u00b5\u00c7\u001f\u008c\u0089\u0014\u00ad\u00a2\u009b\u0093(\u00ab\u00ef\u00c2\u0010\u000f\u00cb\u0013< \"#}\u00f1Qf`\u001b\u008e\u0090\u001b(\u0081a\u00f9\u00e7N\u009c\u00be$+\u000f\u00e3P\u00a7\u0097\u00c7\u00ff\u00e1\u00f7\u00a87\u007fA$\u00c9\u009e\u00f0\u00cdjr\u00f8\u00e8\u00a5\u00efG\u00f60\u0002\u00f6\u00b5$\u0010\u00a1a\u00a1O\u00aeR\u00aa{\u00e9\u00e5\u00b2\u00a6\u008f\u0000\u0089\f\u0010\u00d15\u00df\u0099;\u00fb\u00b6\u00aavD\u00e8P!]\u00cd\u00ac";
                    var11_6 = 124;
                    var8_7 = 32;
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
                        var12_3[var10_4++] = HudConfig.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00b8\u009a:\u008a\u00d4\b\u0089\u00d9v\u00d1\u0001r\u008a4\\\u001e\u0010\u00a1\u00ba\u008b\u0084g\u009c\u00e9\u00fdrTrH\\\u00e0(\u0096";
                        var11_6 = 33;
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
                        var12_3[var10_4++] = HudConfig.zkmDecodeUtf8(var13_9).intern();
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
            HudConfig.b = var12_3;
            HudConfig.c = new String[7];
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
        var4_12 = var0_10.doFinal(new byte[]{-79, 115, -68, 11, -84, -33, -87, 98});
        ** while (true)
        HudConfig.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        HudConfig.Companion = new HudConfig$Companion(null);
        var16_13 = new Lazy[]{null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)())};
        HudConfig.$childSerializers = var16_13;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        M = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return M;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7946;
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
                throw new RuntimeException("su/catlean/jn", exception);
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
            HudConfig.c[n2] = HudConfig.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = HudConfig.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/jn" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(HudConfig.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

