/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.bootstrap.Bootstrap
 *  io.netty.channel.ChannelFuture
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  io.netty.channel.EventLoopGroup
 *  io.netty.channel.nio.NioEventLoopGroup
 *  io.netty.channel.socket.nio.NioSocketChannel
 */
package su.catlean.util;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.net.InetSocketAddress;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.NetworkSide;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.AddHandlersEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.config.ConfigStore;
import su.catlean.gofra.Flow;
import su.catlean.gui.PanelScreen;
import su.catlean.util.Globals;
import su.catlean.util.Proxy;
import su.catlean.util.ProxyChannelInitializer;
import su.catlean.util.TickTimer;
import su.catlean.zkm.ZkmKeys;

public final class ProxyManager {
    @NotNull
    public static final ProxyManager INSTANCE;
    @Nullable
    private static Proxy proxy;
    @NotNull
    private static TickTimer tickTimer;
    private static int[] O;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    private ProxyManager() {
    }

    @NotNull
    public final List getProxies() {
        return ConfigStore.INSTANCE.getProxyListFile().getData().getProxies();
    }

    @Nullable
    public final Proxy getProxy() {
        return proxy;
    }

    public final void setProxy(@Nullable Proxy proxy) {
        ProxyManager.proxy = proxy;
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = a ^ 0xDCC420CCF5AL;
        int n = (int)((l ^ 0x64DC8F48487EL) >>> 48);
        if (Globals.getMc().currentScreen instanceof PanelScreen && tickTimer.hasPassed((short)n, 600)) {
            this.getActiveProxy();
            tickTimer.reset();
        }
    }

    @Flow
    private final void onAddHandlers(AddHandlersEvent addHandlersEvent) {
        block1: {
            AddHandlersEvent addHandlersEvent2;
            Proxy proxy;
            block2: {
                int n = Proxy.zkmIntConstant();
                if (n == 0) break block1;
                Proxy proxy2 = ProxyManager.proxy;
                if (proxy2 == null) break block1;
                Proxy proxy3 = proxy2;
                Proxy proxy4 = ProxyManager.proxy;
                Intrinsics.checkNotNull(proxy4);
                proxy = proxy4;
                if (n == 0 || addHandlersEvent.getSide() != NetworkSide.CLIENTBOUND) break block1;
                addHandlersEvent2 = addHandlersEvent;
                if (n == 0) break block2;
                if (addHandlersEvent2.getLocal()) break block1;
                addHandlersEvent2 = addHandlersEvent.getPipeline().get();
            }
            ChannelPipeline channelPipeline = (ChannelPipeline)addHandlersEvent2;
            if (channelPipeline != null) {
                ChannelHandler[] channelHandlerArray = new ChannelHandler[]{proxy.isSocks4() ? (ProxyHandler)new Socks4ProxyHandler(new InetSocketAddress(proxy.getIp(), proxy.getPort()), proxy.getUser()) : (ProxyHandler)new Socks5ProxyHandler(new InetSocketAddress(proxy.getIp(), proxy.getPort()), proxy.getUser(), proxy.getPassword())};
                channelPipeline.addFirst(channelHandlerArray);
            }
        }
    }

    public final boolean removeProxy(@NotNull Proxy proxy) {
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        return this.getProxies().remove(proxy);
    }

    public final boolean addProxy(@NotNull Proxy proxy) {
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        return this.getProxies().add(proxy);
    }

    public final void getActiveProxy() {
        Iterable iterable = this.getProxies();
        int n = 0;
        for (Object t : iterable) {
            int n2;
            if ((n2 = n++) < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Proxy proxy = (Proxy)t;
            int n3 = n2;
            INSTANCE.pingProxy(proxy, n3);
        }
    }

    private final void pingProxy(Proxy proxy, int n) {
        long l = System.currentTimeMillis();
        proxy.setPing(-2);
        new Thread(() -> ProxyManager.pingProxy$lambda$0(n, proxy, l)).start();
    }

    private static final void pingProxy$lambda$0(int n, Proxy proxy, long l) {
        Thread.sleep(h * (long)n);
        try {
            NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            ((Bootstrap)((Bootstrap)bootstrap.group((EventLoopGroup)nioEventLoopGroup)).channel(NioSocketChannel.class)).handler((ChannelHandler)new ProxyChannelInitializer(proxy));
            ChannelFuture channelFuture = bootstrap.connect("crystalpvp.cc", 25565).sync();
            channelFuture.await();
            if (channelFuture.isSuccess()) {
                proxy.setPing((int)(Globals.now() - l));
            } else {
                proxy.setPing(-1);
            }
            nioEventLoopGroup.shutdownGracefully();
        }
        catch (Exception exception) {
            proxy.setPing(-1);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-4494532007215379709L, -5932098511889885743L, MethodHandles.lookup().lookupClass()).a(147149580336207L);
        long l = a ^ 0x4F1CA38337AAL;
        d = new HashMap(13);
        ProxyManager.zkmSetFlag(null);
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
        String[] stringArray = new String[3];
        int n = 0;
        String string = "\u008d\u00f9< \u00ee\u000f=\u00a5\u00bd\t@\u0017<\b\u00a9\u00cf \u0080\u00fb\u00f0?Lo\u000fRr0\u00f5\u00b0\u00c7\u0084\u00e5\u000f\u0093:\u00fa`\u00f1\u00a0\u00dap\u00d7mqI\u00c8V\u00fc^\u0010Wd\u0011\u00d7\u008d\u0007\u00b0\u0001\u00b0\u00f3N\u00af\u00e8\u00ee\u00bd\u0083";
        int n2 = 66;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ProxyManager.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[3];
        g = new HashMap(13);
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray5 = byArray5;
            byArray5[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
        long[] lArray = new long[3];
        int n6 = 0;
        String string2 = "\u00fb\u009e\u00b1\u008c\u00be\u00f0\u00e3\u00c7Y\u0005\u0092:\u00f1\u00ab\u009b*\u00b06=\\\r$\u00ff\u00aa";
        int n7 = 24;
        int n8 = 0;
        do {
            byte[] byArray6 = string2.substring(n8, n8 += 8).getBytes("ISO-8859-1");
            int n9 = n6++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n9] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n8 < n7);
        e = lArray;
        f = new Integer[3];
        Cipher cipher3 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory3 = SecretKeyFactory.getInstance("DES");
        byte[] byArray8 = new byte[8];
        byte[] byArray9 = byArray8;
        byArray8[0] = (byte)(l >>> 56);
        int n10 = 1;
        while (true) {
            if (n10 >= 8) {
                cipher3.init(2, (Key)secretKeyFactory3.generateSecret(new DESKeySpec(byArray9)), new IvParameterSpec(new byte[8]));
                byte[] byArray10 = cipher3.doFinal(new byte[]{24, -21, 20, 49, -65, -64, -1, 21});
                h = ((long)byArray10[0] & 0xFFL) << 56 | ((long)byArray10[1] & 0xFFL) << 48 | ((long)byArray10[2] & 0xFFL) << 40 | ((long)byArray10[3] & 0xFFL) << 32 | ((long)byArray10[4] & 0xFFL) << 24 | ((long)byArray10[5] & 0xFFL) << 16 | ((long)byArray10[6] & 0xFFL) << 8 | (long)byArray10[7] & 0xFFL;
                INSTANCE = new ProxyManager();
                tickTimer = new TickTimer();
                return;
            }
            byArray9 = byArray9;
            byArray9[n10] = (byte)(l << n10 * 8 >>> 56);
            ++n10;
        }
    }

    public static void zkmSetFlag(int[] nArray) {
        O = nArray;
    }

    public static int[] zkmGetFlag() {
        return O;
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x59B5;
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
                throw new RuntimeException("su/catlean/jg", exception);
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
            ProxyManager.c[n2] = ProxyManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProxyManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/jg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x9D5;
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
                throw new RuntimeException("su/catlean/jg", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ProxyManager.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ProxyManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/jg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProxyManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ProxyManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

