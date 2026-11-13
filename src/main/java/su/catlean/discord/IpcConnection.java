/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.JsonFormats;
import su.catlean.discord.IpcOpcode;
import su.catlean.util.ByteUtil;
import su.catlean.zkm.ZkmKeys;

public abstract class IpcConnection {
    private static int r;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map g;
    private static final long l;

    public final void send(@NotNull IpcOpcode ipcOpcode, @NotNull JsonElement jsonElement) {
        ByteBuffer byteBuffer;
        Intrinsics.checkNotNullParameter((Object)ipcOpcode, "opcode");
        Intrinsics.checkNotNullParameter(jsonElement, "element");
        Map<String, JsonPrimitive> map = MapsKt.toMutableMap(JsonElementKt.getJsonObject(jsonElement));
        map.put("nonce", JsonElementKt.JsonPrimitive(UUID.randomUUID().toString()));
        Object object = JsonFormats.getJson();
        Object object2 = new JsonObject(map);
        ((Json)object).getSerializersModule();
        object = ((Json)object).encodeToString((SerializationStrategy)JsonObject.Companion.serializer(), object2);
        byte[] byArray = ((String)object).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byArray, "getBytes(...)");
        object2 = object = (Object)byArray;
        ByteBuffer byteBuffer2 = byteBuffer = ByteBuffer.allocate(((Object)object2).length + (int)l);
        IpcConnection ipcConnection = this;
        byteBuffer2.putInt(ByteUtil.getRewind(ipcOpcode.ordinal()));
        byteBuffer2.putInt(ByteUtil.getRewind(((Object)object2).length));
        byteBuffer2.put((byte[])object2);
        byteBuffer2.flip();
        ByteBuffer byteBuffer3 = byteBuffer;
        Intrinsics.checkNotNullExpressionValue(byteBuffer3, "apply(...)");
        ipcConnection.write(byteBuffer3);
    }

    protected abstract void write(@NotNull ByteBuffer var1);

    public abstract void close();

    public static void zkmSetFlag(int n) {
        r = n;
    }

    public static int int_zkmGetFlag() {
        return r;
    }

    public static int zkmIntConstant() {
        return 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    IpcConnection.a = ZkmKeys.a(2034843016353345246L, 2812540783205301726L, MethodHandles.lookup().lookupClass()).a(62909548747673L);
                    var14 = IpcConnection.a ^ 128915959592254L;
                    IpcConnection.g = new HashMap<K, V>(13);
                    IpcConnection.zkmSetFlag(109);
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
                    var12_3 = new String[5];
                    var10_4 = 0;
                    var9_5 = "\u00ad_n\u00d3\u00ab\u00a6\u008f\u00a9\u00df\u00ad\u0090\u00b1\u00dc5\u00e3\u00c1 \u00a6\u0091\u00a2\u0002\u00daB\u00ad\u00ed}\u00d4Iv4j\u00f6e\u008c\u00b8\u00f1\u00a6c\u0092ZX\u00f3\u00f1\u00c1\u00b7\u00f8\u00aaiH\u0010d\u00ec\u00ac\u00b34Z\u00cd\u0012\u00af`[\u00c0W\t\u00ff\u009b";
                    var11_6 = 66;
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
                        var12_3[var10_4++] = IpcConnection.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u00f3|\u00a0\u0085\u00e2\u0013$[\u00f9\u00f0\u00ac\u00d0\u00e5\u0007^\" \u00cd\u001b\u00ff\u00e9\u001a\u00a9F\u00d2\u001d\u009e?:\u00b3\u00c7RG\u00e0\u0091\u00b2-b(qT47=1\u00c3\u00af\u0083\u0006";
                        var11_6 = 49;
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
                        var12_3[var10_4++] = IpcConnection.zkmDecodeUtf8(var13_9).intern();
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
            IpcConnection.c = var12_3;
            IpcConnection.d = new String[5];
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
        var4_12 = var0_10.doFinal(new byte[]{-92, -41, 109, 9, 73, 85, -99, -29});
        ** while (true)
        IpcConnection.l = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6946;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/n9", exception);
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
            IpcConnection.d[n2] = IpcConnection.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = IpcConnection.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/n9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(IpcConnection.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

