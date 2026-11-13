/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelInitializer
 *  io.netty.channel.ChannelPipeline
 *  io.netty.channel.socket.SocketChannel
 *  io.netty.handler.flow.FlowControlHandler
 *  io.netty.handler.timeout.ReadTimeoutHandler
 */
package su.catlean.util;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.flow.FlowControlHandler;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.net.InetSocketAddress;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.network.handler.NetworkStateTransitions;
import su.catlean.module.Module;
import su.catlean.util.Proxy;
import su.catlean.util.ProxyChannelHandler;
import su.catlean.zkm.ZkmKeys;

public final class ProxyChannelInitializer
extends ChannelInitializer {
    final Proxy proxy;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    ProxyChannelInitializer(Proxy proxy) {
        this.proxy = proxy;
    }

    protected void initChannel(SocketChannel socketChannel) {
        block0: {
            int n = Proxy.zkmIntConstant();
            Intrinsics.checkNotNullParameter(socketChannel, "ch");
            int n2 = n;
            ChannelHandler[] channelHandlerArray = new ChannelHandler[]{new FlowControlHandler()};
            ChannelPipeline channelPipeline = socketChannel.pipeline().addLast(channelHandlerArray).addLast("timeout_proxy_checker_", (ChannelHandler)new ReadTimeoutHandler((int)e)).addLast("inbound_proxy_checker_", (ChannelHandler)new NetworkStateTransitions.InboundConfigurer()).addLast("outbound_proxy_checker_", (ChannelHandler)new NetworkStateTransitions.OutboundConfigurer());
            channelHandlerArray = new ChannelHandler[]{this.proxy.isSocks4() ? (ProxyHandler)new Socks4ProxyHandler(new InetSocketAddress(this.proxy.getIp(), this.proxy.getPort()), this.proxy.getUser()) : (ProxyHandler)new Socks5ProxyHandler(new InetSocketAddress(this.proxy.getIp(), this.proxy.getPort()), this.proxy.getUser(), this.proxy.getPassword())};
            ChannelPipeline channelPipeline2 = channelPipeline.addLast(channelHandlerArray);
            channelHandlerArray = new ChannelHandler[]{new ProxyChannelHandler()};
            channelPipeline2.addLast(channelHandlerArray);
            if (n2 != 0) break block0;
            Module.zkmSetFlag(new Module[4]);
        }
    }

    public void initChannel(Channel channel) {
        this.initChannel((SocketChannel)channel);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ProxyChannelInitializer.a = ZkmKeys.a(-2338562900836437302L, -2077347238200514781L, MethodHandles.lookup().lookupClass()).a(172626381861222L);
                    ProxyChannelInitializer.d = new HashMap<K, V>(13);
                    var5 = ProxyChannelInitializer.a ^ 132803744690406L;
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
                    var14_3 = new String[4];
                    var12_4 = 0;
                    var11_5 = "\u0083\u001a\u00c8s.\u00a9\u007f\u0089\u00b0\u0006\u0004B@\u00cb\u0088\u0005D\u001b\u007fx\u0088\u00b2>V\u0082\u00c9\u00add\u00af\u00cb9<\u00ee\u00aa\u008a\u008d:\u00e9-\u00b6\u0010G\u00e8\u00cb\u00a7\u0090\u00da\u00b1b\u007fU\u00c4\u00de\u00ae\u00bb\u00d2\u0093";
                    var13_6 = 57;
                    var10_7 = 40;
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
                        var14_3[var12_4++] = ProxyChannelInitializer.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "|\u0011\u00c2qSL\u00d7\u008e\u00f0\u00b8\u00a0\u00abI\u00df\u0085\u0083T\u00a3\u00a1\u00d2\u00bc;\u00b0\u0081\u00f8\u00a1\bk\u00d3}\u0001|\u00c4\u0099\u00dd\u00a6\u00f5\u00eeV\u0002(\u0085V\u00ba:\u00e1W\u0097\u0012Mi\u00fd\u00a1(\u00d2\u00ea\u0016\u001fU\u0097\u00e3\u00ad\u0093\u00ef\u00e3x\u00fcI\u00bc\u00ff\u0090\u00a2\u00bb\u00f1\u00a1'\u00d2\u00ab\u0001yZ";
                        var13_6 = 81;
                        var10_7 = 40;
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
                        var14_3[var12_4++] = ProxyChannelInitializer.zkmDecodeUtf8(var15_9).intern();
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
            ProxyChannelInitializer.b = var14_3;
            ProxyChannelInitializer.c = new String[4];
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
        var4_12 = var0_10.doFinal(new byte[]{81, 117, 18, -89, -74, -100, 120, -68});
        ** while (true)
        ProxyChannelInitializer.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xB82;
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
                throw new RuntimeException("su/catlean/g3", exception);
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
            ProxyChannelInitializer.c[n2] = ProxyChannelInitializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProxyChannelInitializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/g3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProxyChannelInitializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

