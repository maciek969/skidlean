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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

final class ItemSerializer
implements KSerializer {
    @NotNull
    private final SerialDescriptor z = SerialDescriptorsKt.PrimitiveSerialDescriptor("Item", PrimitiveKind.STRING.INSTANCE);
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    @Override
    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.z;
    }

    @NotNull
    public Item deserialize2(@NotNull Decoder decoder) {
        Item item;
        block2: {
            Object v1;
            block1: {
                Intrinsics.checkNotNullParameter(decoder, "decoder");
                String string = decoder.decodeString();
                DefaultedRegistry<Item> defaultedRegistry = Registries.ITEM;
                Intrinsics.checkNotNullExpressionValue(defaultedRegistry, "ITEM");
                Iterable iterable = defaultedRegistry;
                for (Object t : iterable) {
                    Item item2 = (Item)t;
                    if (!Intrinsics.areEqual(item2.getTranslationKey(), string)) continue;
                    v1 = t;
                    break block1;
                }
                v1 = null;
            }
            if ((item = (Item)v1) != null) break block2;
            Item item3 = Items.AIR;
            item = item3;
            Intrinsics.checkNotNullExpressionValue(item3, "AIR");
        }
        return item;
    }

    @Override
    public void serialize(@NotNull Encoder encoder, @NotNull Item item) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(item, "value");
        String string = item.getTranslationKey();
        Intrinsics.checkNotNullExpressionValue(string, "getDescriptionId(...)");
        encoder.encodeString(string);
    }

    @Override
    public Object deserialize(Decoder decoder) {
        return this.deserialize2(decoder);
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (Item)object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                ItemSerializer.a = ZkmKeys.a(8496499282509846615L, 4758659017180141203L, MethodHandles.lookup().lookupClass()).a(43132678878233L);
                ItemSerializer.d = new HashMap<K, V>(13);
                var0 = ItemSerializer.a ^ 50942194544098L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[7];
                var7_4 = 0;
                var6_5 = "3\\,-[=\u0001I\u00cb\u00a9c\u00b8\u00f3\u00d1\u00a8\u00bf\u0010\u0083\u00f7\u009c\u00bd@\u0089HH\u0006\u0002X\u00a3E\u00db*5(G*\u0092m\u00c3\u00bf\u00d1c\u009d\u00ad\u009d\u0012\u0006&\u00b3\u0018F\u00ff|\u00a6\u00b2\u00d9\u0016\u00c3X\u00e0\u00a0f\u00de\u00e2\u0017*\u00e6\r\u00ffG\u0017\u0087\u008e\u00bd\u0010\u00f7\u008b\u00a1w\u00fbx\u00e4\u00cb\u0010\u00db\u00f3\u009d?\u00a5Y\u000e\u0010\u00cf\u001a\u00e6\u00f40\b\t;4\u0080v\u0011/|}d";
                var8_6 = 108;
                var5_7 = 16;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = ItemSerializer.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "i\u00fei\u00fc\u00f6\u0017\u008c\u00d3iL\u00e1\u00e41@^\u00a5\u0010\u0085\u00c6\u00b9\u00bak\u0000\u0081\u00bc\u00cc\u00cf\u00eb\u00ee\u000e\u00f7\u00ed\u00ee";
                    var8_6 = 33;
                    var5_7 = 16;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = ItemSerializer.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        ItemSerializer.b = var9_3;
        ItemSerializer.c = new String[7];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7D1F;
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
                throw new RuntimeException("su/catlean/ls", exception);
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
            ItemSerializer.c[n2] = ItemSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ls" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

