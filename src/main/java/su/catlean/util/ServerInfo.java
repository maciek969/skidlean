/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.misc.FastLatency;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ServerInfo
implements Manager {
    @NotNull
    public static final ServerInfo INSTANCE;
    @NotNull
    private static final ArrayDeque tickSamples;
    private static long s;
    private static float tps;
    private static int k;
    @NotNull
    private static List U;
    private static long H;
    private static long B;
    private static String X;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private ServerInfo() {
    }

    @NotNull
    public final List V() {
        return U;
    }

    public final void q(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)ServerInfo.zkmBootstrap("e", (int)8462, (long)(0x39D94A78C9D11336L ^ l))));
        U = list;
    }

    public final long B() {
        return H;
    }

    public final float getTps() {
        return new BigDecimal(tps).setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    public final float getTpsRounded() {
        return new BigDecimal(20.0 * (double)this.getServerAddress()).setScale(2, RoundingMode.HALF_UP).floatValue();
    }

    public final float getServerAddress() {
        Object object = Globals.getNetworkHandler().getServerInfo();
        return (object != null && (object = ((net.minecraft.client.network.ServerInfo)object).address) != null ? StringsKt.contains$default((CharSequence)object, "pvp.mioclient.me", false, 2, null) : false) ? 1.0f : 1000.0f / RangesKt.coerceAtLeast((float)s, 1.0f);
    }

    @Flow(priority=10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        U.clear();
        U.addAll(this.getBlockEntities());
        Object object = Globals.getMc().getNetworkHandler();
        k = object != null && (object = ((ClientPlayNetworkHandler)object).getPlayerListEntry(Globals.getPlayer().getUuid())) != null ? ((PlayerListEntry)object).getLatency() : 0;
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (receivePacket.getPacket() instanceof WorldTimeUpdateS2CPacket) {
            if (H != 0L) {
                s = Globals.now() - H;
                if (Globals.getMc().player != null && Globals.getPlayer().age < 20) {
                    tickSamples.clear();
                    return;
                }
                if (s > 0L) {
                    float f;
                    if (tickSamples.size() >= 20) {
                        tickSamples.removeFirst();
                    }
                    if (!Float.isNaN(f = 20.0f * this.getServerAddress()) && !Float.isInfinite(f)) {
                        tickSamples.add(Float.valueOf(f));
                    }
                    tps = (float)CollectionsKt.averageOfFloat(tickSamples);
                }
            }
            H = Globals.now();
        }
        B = Globals.now();
    }

    public final int getPing(short s, @NotNull PlayerEntity playerEntity) {
        PlayerListEntry playerListEntry;
        block10: {
            block9: {
                block8: {
                    int n;
                    CallSite callSite;
                    block7: {
                        int n2 = 0;
                        int n3 = 0;
                        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)s << 48 >>> 48) ^ a;
                        CallSite callSite2 = ZkmReferences.zkmBootstrap("\u00c5", (long)-612082057340078936L, (long)l);
                        Intrinsics.checkNotNullParameter(playerEntity, (String)((Object)ServerInfo.zkmBootstrap("e", (int)10345, (long)(0x754CA4EF349CD655L ^ l))));
                        callSite = callSite2;
                        n = Intrinsics.areEqual(playerEntity, Globals.getPlayer());
                        if (callSite == null) break block7;
                        if (n == 0) break block8;
                        n = FastLatency.INSTANCE.getEnabled();
                    }
                    if (callSite != null) {
                        n = n != 0 ? FastLatency.INSTANCE.j() : k;
                    }
                    return n;
                }
                if (s <= 0) break block9;
                ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
                Intrinsics.checkNotNull(clientPlayNetworkHandler);
                playerListEntry = clientPlayNetworkHandler.getPlayerListEntry(playerEntity.getUuid());
                if (playerListEntry != null) break block10;
            }
            return 0;
        }
        PlayerListEntry playerListEntry2 = playerListEntry;
        return playerListEntry2.getLatency();
    }

    public static /* synthetic */ int getPing$default(ServerInfo serverInfo, PlayerEntity playerEntity, long l, int n, char c, Object object) {
        long l2 = (l << 16 | (long)c << 48 >>> 48) ^ a;
        int n2 = (int)((l2 ^ 0x626D7C2AAEEDL) << 48 >>> 48);
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2063991099032166286L, (long)l2);
        int n3 = n & 1;
        if (callSite != null) {
            playerEntity = Globals.getPlayer();
            n3 = serverInfo.getPing((short)n2, playerEntity);
        }
        return n3;
    }

    public final boolean getDimensionName() {
        Object object = Globals.getMc().world;
        if (object == null || (object = ((World)object).getRegistryKey()) == null || (object = ((RegistryKey)object).getValue()) == null || (object = ((Identifier)object).getPath()) == null) {
            object = "";
        }
        return Intrinsics.areEqual(object, "the_nether");
    }

    private final List getBlockEntities() {
        List list = new ArrayList();
        for (WorldChunk worldChunk : this.getLoadedChunks()) {
            list.addAll(worldChunk.getBlockEntities().values());
        }
        return list;
    }

    @NotNull
    public final List getLoadedChunks() {
        List list = new ArrayList();
        Integer n = Globals.getMc().options.getViewDistance().getValue();
        Intrinsics.checkNotNullExpressionValue(n, "get(...)");
        int n2 = ((Number)n).intValue();
        int n3 = -n2;
        if (n3 <= n2) {
            while (true) {
                int n4;
                if ((n4 = -n2) <= n2) {
                    while (true) {
                        if (Globals.getWorld().getChunkManager().getWorldChunk((int)Globals.getPlayer().getX() / 16 + n3, (int)Globals.getPlayer().getZ() / 16 + n4) != null) {
                            WorldChunk worldChunk;
                            list.add(worldChunk);
                        }
                        if (n4 == n2) break;
                        ++n4;
                    }
                }
                if (n3 == n2) break;
                ++n3;
            }
        }
        return list;
    }

    @NotNull
    public final List getChunksInView() {
        List list = new ArrayList();
        int n = ((Number)Globals.getMc().options.getViewDistance().getValue()).intValue() + 2;
        int n2 = (int)Globals.getPlayer().getX() >> 4;
        int n3 = (int)Globals.getPlayer().getZ() >> 4;
        int n4 = -n;
        if (n4 <= n) {
            while (true) {
                int n5;
                if ((n5 = -n) <= n) {
                    while (true) {
                        list.add(new ChunkPos(n2 + n4, n3 + n5));
                        if (n5 == n) break;
                        ++n5;
                    }
                }
                if (n4 == n) break;
                ++n4;
            }
        }
        return list;
    }

    public final boolean isTpsSampling() {
        return tickSamples.size() < 20;
    }

    public final long getTimeSinceLastPacket() {
        return B - H;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ServerInfo.a = ZkmKeys.a(5547563520432500816L, -385423042536278456L, MethodHandles.lookup().lookupClass()).a(192092499423659L);
                        var20 = ServerInfo.a ^ 48061792740045L;
                        ServerInfo.d = new HashMap<K, V>(13);
                        ServerInfo.zkmSetFlag("siVOoc");
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
                        var18_3 = new String[5];
                        var16_4 = 0;
                        var15_5 = "\u00e5C\n\u00a6\u008c\u00b7\u008f\u001e\u008bpJ\b\u00b5%\u0018\u00a0\u0018\"\u009bn\u001f]\u0010o\u00c6\u00f1\u00bf~w\u00ba\rV\u001e\u008f\u001dI}\u0011E:\u008f =\u00c6\u00b7\u0017\u00ce\tS\u00c7\u00bf\u00a2\u00e8\u00d1\u00a03\u0018\u009e\u00cd\u00b0\u00e5\u0085*\u0086t\u00f6\u00d0\u0004\u0016zo|K\u0012";
                        var17_6 = 74;
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
                            var18_3[var16_4++] = ServerInfo.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b8\u00b2\u0011\u0093d\u00c7)\u008dIu\u008fmw\u00b6?\u00e4(`\u00d0\u00f5\u0000,;\u00c9\u0085\u00df8y\u0006\u0012%\u00ecc[\u00f8\u001ci#2ia\u00a2d\u00f5U\u00bf\u000bv\u0082\u001b\u00d2\u007f\u000f\u007f\u0080\u00af\u00ad";
                            var17_6 = 57;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = ServerInfo.zkmDecodeUtf8(var19_9).intern();
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
                ServerInfo.b = var18_3;
                ServerInfo.c = new String[5];
                ServerInfo.g = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "tx\u0007\u00ea\u00f2\u00f0rvI\u0013p\u00a7\u00c81Wp";
                var5_15 = 16;
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00c9\u0002\u0092\\\u0088K<V\u00b2\u00dd\u00fa\u00dcf\u00c4f\u00cd";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        ServerInfo.e = var6_12;
        ServerInfo.f = new Integer[4];
        ServerInfo.INSTANCE = new ServerInfo();
        ServerInfo.tickSamples = new ArrayDeque<E>(20);
        ServerInfo.s = 1L;
        ServerInfo.U = new ArrayList<E>();
    }

    public static void zkmSetFlag(String string) {
        X = string;
    }

    public static String zkmGetFlag() {
        return X;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x941;
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
                throw new RuntimeException("su/catlean/nf", exception);
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
            ServerInfo.c[n2] = ServerInfo.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ServerInfo.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x19A8;
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
                throw new RuntimeException("su/catlean/nf", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ServerInfo.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ServerInfo.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/nf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ServerInfo.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ServerInfo.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

