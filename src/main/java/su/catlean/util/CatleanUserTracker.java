/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.compress.utils.Lists
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.config.JsonFormats2;
import su.catlean.gofra.Flow;
import su.catlean.util.Async;
import su.catlean.util.CatleanUserTracker$onTick$1;
import su.catlean.util.CatleanUserTracker$onTick$2;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class CatleanUserTracker
implements Manager {
    @NotNull
    public static final CatleanUserTracker INSTANCE;
    @NotNull
    private static final String F;
    @NotNull
    private static final String O;
    private static int S;
    private static boolean j;
    private static boolean Q;
    @NotNull
    private static final Timer heartbeatTimer;
    @NotNull
    private static final Timer fetchTimer;
    @NotNull
    private static CopyOnWriteArrayList users;
    @NotNull
    private static ConcurrentHashMap cache;
    private static int[] l;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map g;
    private static final long[] h;
    private static final Integer[] i;
    private static final Map k;

    private CatleanUserTracker() {
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        block9: {
            int n;
            Async async;
            block8: {
                block7: {
                    block6: {
                        block5: {
                            block4: {
                                if (Q || Globals.getMc().getSession() == null || Globals.getMc().getSession().getUsername() == null) break block4;
                                String string = Globals.getMc().getSession().getUsername();
                                Intrinsics.checkNotNullExpressionValue(string, "getName(...)");
                                if (((CharSequence)string).length() > 0) break block5;
                            }
                            if (!fetchTimer.hasPassed2(150000)) break block6;
                        }
                        Q = true;
                        async = Async.INSTANCE;
                        n = 0;
                        async.getExecutor().execute(new CatleanUserTracker$onTick$1(n));
                    }
                    if (j || Globals.getMc().getSession() == null || Globals.getMc().getSession().getUsername() == null) break block7;
                    String string = Globals.getMc().getSession().getUsername();
                    Intrinsics.checkNotNullExpressionValue(string, "getName(...)");
                    if (((CharSequence)string).length() > 0) break block8;
                }
                if (!heartbeatTimer.hasPassed2(30000)) break block9;
            }
            j = true;
            async = Async.INSTANCE;
            n = 0;
            async.getExecutor().execute(new CatleanUserTracker$onTick$2(n));
        }
    }

    public final void reportUser(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        String string2 = "{\"value\":\"" + string + "\"}";
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://api.catlean.su/v1/add")).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(string2)).build();
        try {
            HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    @NotNull
    public final List fetchUsers() {
        Object object;
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("https://api.catlean.su/v1/online")).GET().build();
        try {
            List list;
            object = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (object.statusCode() == 200) {
                Json json = JsonFormats2.getJson();
                Object t = object.body();
                Intrinsics.checkNotNullExpressionValue(t, "body(...)");
                String string = (String)t;
                json.getSerializersModule();
                list = (List)json.decodeFromString((DeserializationStrategy)new ArrayListSerializer<String>(StringSerializer.INSTANCE), string);
            } else {
                list = new ArrayList();
            }
            object = list;
        }
        catch (Exception exception) {
            object = new ArrayList();
        }
        return object;
    }

    public final boolean isCatleanUser(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        Object var4_2 = null;
        String string2 = string + "1f";
        if (cache.contains(string2)) {
            Object v = cache.get(string2);
            Intrinsics.checkNotNull(v);
            return (Boolean)v;
        }
        String string3 = this.encode(string2);
        boolean bl = Lists.newArrayList(users.iterator()).contains(string3);
        ((Map)cache).put(string2, bl);
        return bl;
    }

    private final String encode(String string) {
        Object object = string;
        byte[] byArray = ((String)object).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byArray, "getBytes(...)");
        byte[] byArray2 = byArray;
        Object object2 = "sahur1337";
        byte[] byArray3 = ((String)object2).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byArray3, "getBytes(...)");
        object = byArray3;
        object2 = new byte[byArray2.length];
        Object var4_6 = null;
        int n = byArray2.length;
        for (int i = 0; i < n; ++i) {
            object2[i] = (byte)(byArray2[i] ^ object[i % ((Object)object).length]);
        }
        return this.toUrlSafeBase64((byte[])object2);
    }

    private final String toUrlSafeBase64(byte[] byArray) {
        String string = Base64.getEncoder().encodeToString(byArray);
        Intrinsics.checkNotNullExpressionValue(string, "encodeToString(...)");
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(string, '@', '(', false, 4, null), '*', ')', false, 4, null), '^', '%', false, 4, null);
    }

    public static final String access$encode(CatleanUserTracker catleanUserTracker, String string) {
        return catleanUserTracker.encode(string);
    }

    public static final int j() {
        return S;
    }

    public static final CopyOnWriteArrayList getUsers() {
        return users;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        CatleanUserTracker.a = ZkmKeys.a(-4189204353788490942L, -434379992115234740L, MethodHandles.lookup().lookupClass()).a(276125987312247L);
                        var20 = CatleanUserTracker.a ^ 70605707851705L;
                        CatleanUserTracker.g = new HashMap<K, V>(13);
                        CatleanUserTracker.zkmSetFlag(null);
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
                        var18_3 = new String[18];
                        var16_4 = 0;
                        var15_5 = "mM\u00a9~\u00b8\u00cfx\u00df\u00e5\u00a8a\u009c\u0004+\u00d4\u00e1 ip\u00a4\u00f8\"\u00a0 8\u008dk7f\u009a\u00fb\u00d4\u00fdq\u009fl\u00f9F\u00a3\u0082\u0018W\u00e6~\u00d8\u0085\u00b4\u0011k\u0010\n\u0089+\u00e1\u00a6\u008eY\u001c(\u00a1;\u0084\u00e3\u0087\n\r(\u00ab\u00eb\u00be\u00e8\u00fa\u0094=\u00d8:\u009fTy\\\u0094\u00a4>\u0001\u00dc\u00e2\u00d0\u00cd\u0000\u00e1\u00a8\u0097\u0090\u00c3\u0089\u00d6\u00e1'\u00eeC\u00b3\u00f2p3\u0080\u0016i\u0010\u00dc\u00ad\u0083R\u0097\u00c6^8\u0091\\\u007f\u0005\u0088:H\u00b5@\u000b\u00e2t\u00cc\u0096LK\u001d\u00f7=\u0013\u00a0)\u00d8\u00bd\u00fa\u00fda&\u0099\u00c9\u0015n\u00d4\b\r\rP\u008eC\u00108\u00e7\u00b9\u00a0\u00ab\u00b1\u00b6w\u00ca\u00dc.\u00b2\u00e7\u00f7h\u00f1\u00ac\u001d\u00b9\"\u00a1\u00ed`\u00a0\u00c2<fL\u00fe\u00fc\u00b7\u00db\n C\u008a\u00f8\\\u0081\u00d4\u00ac?(\u0010\u00c0\u009d\u00b6\u00ec\u009d\u0083>\u00bf\u00e5\u0095\u0089\u00ee\u001a3\u007f\u008f<\u0012\u00a9#\u00a4%\u0018O\u008b;;\u00c2\u00c3Df\u001eP\u0010\u00ea\u00a3\u0093\u0091_\u00bc(\u00ab@\u0014\u00b6\u0096\u0018\u0010\u00c4p5\u008f+\u0090\u00d4\u0093\u00bd\u0097\u00bd\u001c\rK\u0097\u00e7 \u009c\u0082\u00e0\u0001;\u00f0\u00f3|\u00c8<(\u00a5\u001fa\u00a7A-\u00a8B\u00c2s\n8\u00ec\u00b9\u00d3<\u00f7U\u001d\u0084Z(\u00cb\u0092\\\u00a79}UVX\u00f5\u00ee\u00f3+zg@\u0090\u00d6\u00b1~\u0002\u00d6\u00c9\u00cd~$r\u00ec\u00bf2\u0016\u00e0W\u009c\u00bc\u0088\u00c5\r_\t \u00d2\u0084\u0085\u00ddZ\u001d\u00f5\u00b0\u00ac\u00a3\u0085\u00c4F\u0001\u008d\u0007\nP\u00a9\u00a1\u00cdn2dKk\u00abB\u009d9]3\u0018\u007f\u00c2\u00cc0\r_\u00b9quQP\u00db/\u0010\u00a1_m=\u0090|4\u00dfU\u00b3 [\u00f1<\u0098\u0083\u00f4k\u00b8S\u00e6H\u00bc\u00e9\u0019i\u00a0\u00a8\u00ed\u00bf\u00b7M\u0003S\u0082i\u00e2\u00dc\f\u0085K\u0013+0\u001fExm\u0080\u00b7\u00cd\u0094\u00b3\u00f6\u00b68\u00a6C\u00c1\u00f61\u0097\u00a8\u00c9\u001d\u0011M\u0019E&Y\u00eb\u00fe\u00d3\u00cb\u00aa\u0082\u00fd~\u00fb%\u0086C\u00ff\u0082,\u00d0O\u00af\u00acS` \u00db\u0080S20\u00d7\u00cd\u0084%\u00c0\u00d7\u00110\u0005\u00fcd\u00a0\r\u00d9\u00edS\u00af%\u00a5H\u00c3\u0001?\u00b3\u000bGJ";
                        var17_6 = 511;
                        var14_7 = 16;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = CatleanUserTracker.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c9\u000b\u00cc\f\u0003P\u00ca\u00f6\u0094\u00a8\u0095'\u00c5\u00c5\u0088\u0093\u008e\u00dbE\u0098\u00e0\u00c1\u0094`\u00dc+\u00a3r{\u00bd\u00fe\u009f8\u00ab\u00ea\r\u00f1UaG\u001b\u00c4\u00c1(\u00dc\u00fc9\u009d.$|\u00df\u009dL\f\u00ea\u00a26\u00ee:\u00f8\nn\u00d8\u00f4\u00cb\u00a3\u00ec'\u009e\u00cd\u00db\u00d1WI\u00c1t\u00ef\u00dd\u009eh\u0019\u00ce]\u00f7\u00ee\u00ca\u0083\u00fc";
                            var17_6 = 89;
                            var14_7 = 32;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = CatleanUserTracker.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                CatleanUserTracker.b = var18_3;
                CatleanUserTracker.c = new String[18];
                CatleanUserTracker.O = "https://api.catlean.su/v1";
                CatleanUserTracker.F = "sahur1337";
                CatleanUserTracker.k = new HashMap<K, V>(13);
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
                var6_12 = new long[10];
                var3_13 = 0;
                var4_14 = "\u009a\t\u00d0\u0011z\u001bZ\u008a\u00acA\u0095\u008253t\u001fLv\u00ac\u0000\u00846\u0097\u00b3C.G+\u0080\u0094M\u00f0\u00caC\u00bc\u00a3\u00b1u\u00fd\u00cc\u00dc\u00fa=m\u00f9\"\u0000\u0013wA\u008d\u00e6\u00ef\u00efR\u00ac\u00c0\u00b9\u00b4t\u009dl\u00f3\u00d5";
                var5_15 = 64;
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
lbl81:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = ";\u00d59\u0013\u00fc\u0007\u00fc\u0087DYD\u00eb\u00ff\b5(";
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
lbl94:
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
lbl107:
                // 1 sources

                ** continue;
            }
        }
        CatleanUserTracker.h = var6_12;
        CatleanUserTracker.i = new Integer[10];
        CatleanUserTracker.INSTANCE = new CatleanUserTracker();
        CatleanUserTracker.S = -1;
        CatleanUserTracker.heartbeatTimer = new Timer();
        CatleanUserTracker.fetchTimer = new Timer();
        CatleanUserTracker.users = new CopyOnWriteArrayList<E>();
        CatleanUserTracker.cache = new ConcurrentHashMap<K, V>();
        CatleanUserTracker.S = Random.Default.nextInt(0, 99999999);
    }

    public static void zkmSetFlag(int[] nArray) {
        l = nArray;
    }

    public static int[] zkmGetFlag() {
        return l;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3C0C;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/rq", exception);
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
            CatleanUserTracker.c[n2] = CatleanUserTracker.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CatleanUserTracker.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4198;
        if (i[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = h[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/rq", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CatleanUserTracker.i[n2] = n3;
        }
        return i[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CatleanUserTracker.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/rq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CatleanUserTracker.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CatleanUserTracker.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

