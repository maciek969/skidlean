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
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.ItemPreset$$serializer;
import su.catlean.config.ItemPreset$Companion;
import su.catlean.config.ItemSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class ItemPreset {
    @NotNull
    public static final ItemPreset$Companion Companion;
    @NotNull
    private String id;
    @NotNull
    private Map items;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static String d;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long f;

    public ItemPreset(@NotNull String string, @NotNull Map map) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(map, "items");
        this.id = string;
        this.items = map;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final void setId(@NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ItemPreset.zkmBootstrap("r", (int)1079, (long)(0x1BB65DD1611CB959L ^ l))));
        this.id = string;
    }

    @NotNull
    public final Map getItems() {
        return this.items;
    }

    public final void setItems(@NotNull Map map) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(map, (String)((Object)ItemPreset.zkmBootstrap("r", (int)5245, (long)(0x287B7459597C2E66L ^ l))));
        this.items = map;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final Map getItems2() {
        return this.items;
    }

    @NotNull
    public final ItemPreset copy(@NotNull String string, @NotNull Map map) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ItemPreset.zkmBootstrap("r", (int)11372, (long)(0x4199795B39B76744L ^ l))));
        Intrinsics.checkNotNullParameter(map, (String)((Object)ItemPreset.zkmBootstrap("r", (int)23630, (long)(0x3FB2AED5FA449765L ^ l))));
        return new ItemPreset(string, map);
    }

    public static ItemPreset copy$default(ItemPreset itemPreset, String string, Map map, int n, Object object) {
        long l = 0L;
        int n2 = 0;
        long l2 = (l << 32 | (long)n2 << 32 >>> 32) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-9040459091408113672L, (long)l2);
        int n3 = n & 1;
        if (callSite != null) {
            if (n3 != 0) {
                string = itemPreset.id;
            }
            n3 = n & 2;
        }
        if (n3 != 0) {
            map = itemPreset.items;
        }
        return itemPreset.copy(string, map);
    }

    @NotNull
    public String toString() {
        return "InventorySnapshot(id=" + this.id + ", items=" + this.items + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * (int)f + ((Object)this.items).hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ItemPreset)) {
            return false;
        }
        ItemPreset itemPreset = (ItemPreset)object;
        if (!Intrinsics.areEqual(this.id, itemPreset.id)) {
            return false;
        }
        return Intrinsics.areEqual(this.items, itemPreset.items);
    }

    @JvmStatic
    public static final void write$Self(ItemPreset itemPreset, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, itemPreset.id);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, (SerializationStrategy)lazyArray[1].getValue(), itemPreset.items);
    }

    public ItemPreset(int n, String string, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 3, ItemPreset$$serializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.items = map;
    }

    private static final KSerializer serializer() {
        return new LinkedHashMapSerializer(IntSerializer.INSTANCE, new ItemSerializer());
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
                    ItemPreset.a = ZkmKeys.a(-1073044233864713857L, -8253338650984321951L, MethodHandles.lookup().lookupClass()).a(203440635290575L);
                    var14 = ItemPreset.a ^ 122410228165598L;
                    ItemPreset.e = new HashMap<K, V>(13);
                    ItemPreset.zkmSetFlag("tCVCw");
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
                    var12_3 = new String[8];
                    var10_4 = 0;
                    var9_5 = "\u00b8\u00b4\u00e6\u00b9\u00e0\u00ba\u00d4:\u00c8\u00d8G1b\u00c9=\u00a0\u0010\u00eeq5Z\u00d71}\u00bcA \u0006\u00db\u00d5\u00a4l\u008a\u0010X\u00bb|N\u00e0\u0010\u001fI\u00dab\u00ff\u0080\u00190P,\u0010\u00e5\u007f\u00a70:U\u000b\u00bb\u0081h\u00de-\u00a4\u0089O\u00c7\u0010\u0000e\u00b8\u00d1\u00e6\u0003\u008fzBR\u00ef\u00d4\u00db\u0095GY\u0010\u00f1\u0098\u0090c\u0081$\u00da\u00c3\u00f9\u00b4\u00aa\u00d7O\u0080\tW";
                    var11_6 = 101;
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
                        var12_3[var10_4++] = ItemPreset.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00b68/\u00c6\u00e0Z\u00bbG(\u0002\u00dd\u0003\u00e0{F\u00c2\u00d3\u00bc\u00d45^^\u0006\u00d5X\u00e6A4D0\u008b^(S\u0001\u00eeH\u00ba\u00fc\u0090\u00bf@r\u00e5\u001es%\u00a4\u00af\u00cfbc\u00ac\u00d2\u009f1}*y\u00a4\u000e\u00cd\u00d8\u009eX'\u00a1\u00f5\u008agN*\u001c";
                        var11_6 = 73;
                        var8_7 = 32;
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
                        var12_3[var10_4++] = ItemPreset.zkmDecodeUtf8(var13_9).intern();
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
            ItemPreset.b = var12_3;
            ItemPreset.c = new String[8];
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
        var4_12 = var0_10.doFinal(new byte[]{-7, 11, 32, -15, 29, 47, 36, -44});
        ** while (true)
        ItemPreset.f = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        ItemPreset.Companion = new ItemPreset$Companion(null);
        var16_13 = new Lazy[]{null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)())};
        ItemPreset.$childSerializers = var16_13;
    }

    public static void zkmSetFlag(String string) {
        d = string;
    }

    public static String zkmGetFlag() {
        return d;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7FC6;
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
                throw new RuntimeException("su/catlean/mh", exception);
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
            ItemPreset.c[n2] = ItemPreset.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemPreset.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/mh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemPreset.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

