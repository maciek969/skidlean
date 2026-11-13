/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.JsonFormats;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.discord.IpcConnection;
import su.catlean.discord.IpcFrame;
import su.catlean.discord.IpcOpcode;
import su.catlean.discord.IpcPacket;
import su.catlean.discord.WindowsIpcConnection;
import su.catlean.module.Module;
import su.catlean.util.ByteUtil;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class IpcPipe
extends IpcConnection {
    private final Selector selector;
    private final SocketChannel channel;
    @Nullable
    private IpcOpcode ipcOpcode;
    @Nullable
    private Integer pendingLength;
    @Nullable
    private ByteBuffer f;
    private final ByteBuffer headerBuffer;
    private static final long b;
    private static final String[] i;
    private static final String[] j;
    private static final Map k;
    private static final long m;

    public IpcPipe(@NotNull String string) {
        block0: {
            Intrinsics.checkNotNullParameter(string, "name");
            this.selector = Selector.open();
            this.channel = SocketChannel.open(UnixDomainSocketAddress.of(string));
            this.headerBuffer = ByteBuffer.allocate(4);
            boolean bl = WindowsIpcConnection.i();
            this.channel.configureBlocking(false);
            this.channel.register(this.selector, 1);
            ThreadsKt.thread$default(true, false, null, "Discord IPC - Socket thread", 0, () -> IpcPipe.access$connect(this), (int)m, null);
            if (bl) break block0;
            Module.zkmSetFlag(new Module[3]);
        }
    }

    private final void connect() {
        IpcPipe ipcPipe = this;
        try {
            IpcPipe ipcPipe2 = ipcPipe;
            while (ipcPipe2.selector.select() > 0) {
                ipcPipe2.selector.selectedKeys().removeIf(arg_0 -> IpcPipe.invokePredicate(arg_0 -> IpcPipe.onReadable(ipcPipe2, arg_0), arg_0));
            }
            Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Result.constructor-impl(ResultKt.createFailure(throwable));
        }
    }

    @Override
    protected void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        IpcPipe ipcPipe = this;
        try {
            IpcPipe ipcPipe2 = ipcPipe;
            Result.constructor-impl(ipcPipe2.channel.write(byteBuffer));
        }
        catch (Throwable throwable) {
            Result.constructor-impl(ResultKt.createFailure(throwable));
        }
    }

    @Override
    public void close() {
        IpcPipe ipcPipe = this;
        try {
            IpcPipe ipcPipe2 = ipcPipe;
            ipcPipe2.channel.close();
            ipcPipe2.selector.close();
            Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Result.constructor-impl(ResultKt.createFailure(throwable));
        }
    }

    private final IpcPacket readFrame(ReadableByteChannel readableByteChannel) {
        boolean bl = WindowsIpcConnection.i();
        IpcPipe ipcPipe = this;
        if (bl) {
            if (ipcPipe.ipcOpcode == null) {
                IpcPipe ipcPipe2 = this;
                ReadableByteChannel readableByteChannel2 = readableByteChannel;
                if (bl) {
                    if (!ipcPipe2.read(readableByteChannel2)) {
                        return null;
                    }
                    ipcPipe2 = this;
                    readableByteChannel2 = CollectionsKt.getOrNull(IpcOpcode.get$ENTRIES(), ByteUtil.getRewind(this.headerBuffer.getInt(0)));
                }
                ipcPipe2.ipcOpcode = (IpcOpcode)((Object)readableByteChannel2);
                this.headerBuffer.clear();
            }
            ipcPipe = this;
        }
        if (bl) {
            if (ipcPipe.pendingLength == null) {
                IpcPipe ipcPipe3 = this;
                if (bl) {
                    if (!ipcPipe3.read(readableByteChannel)) {
                        return null;
                    }
                    Integer n = this.pendingLength = Integer.valueOf(ByteUtil.getRewind(this.headerBuffer.getInt(0)));
                    Intrinsics.checkNotNull(n);
                    this.f = ByteBuffer.allocate(n);
                    ipcPipe3 = this;
                }
                ipcPipe3.headerBuffer.clear();
            }
            ByteBuffer byteBuffer = this.f;
            Intrinsics.checkNotNull(byteBuffer);
            readableByteChannel.read(byteBuffer);
            ipcPipe = this;
        }
        ByteBuffer byteBuffer = ipcPipe.f;
        ByteBuffer byteBuffer2 = byteBuffer;
        Intrinsics.checkNotNull(byteBuffer);
        if (bl) {
            if (byteBuffer2.hasRemaining()) {
                return null;
            }
            byteBuffer2 = this.f;
        }
        if (bl) {
            if (byteBuffer2 != null) {
                byteBuffer2 = byteBuffer2.flip();
            }
        }
        IpcOpcode ipcOpcode = this.ipcOpcode;
        Intrinsics.checkNotNull((Object)ipcOpcode);
        Json json = JsonFormats.getJson();
        ByteBuffer byteBuffer3 = this.f;
        Intrinsics.checkNotNull(byteBuffer3);
        String string = Charsets.UTF_8.decode(byteBuffer3).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String string2 = string;
        json.getSerializersModule();
        IpcFrame ipcFrame = (IpcFrame)json.decodeFromString(IpcFrame.Companion.serializer(), string2);
        IpcOpcode ipcOpcode2 = ipcOpcode;
        IpcPacket ipcPacket = new IpcPacket(ipcOpcode2, ipcFrame);
        this.ipcOpcode = null;
        this.pendingLength = null;
        this.f = null;
        return ipcPacket;
    }

    private final boolean read(ReadableByteChannel readableByteChannel) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ b;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)7644717831475327924L, (long)l);
        readableByteChannel.read(this.headerBuffer);
        CallSite callSite2 = callSite;
        boolean bl = this.headerBuffer.hasRemaining();
        if (callSite2 != false) {
            if (bl) {
                return false;
            }
            this.headerBuffer.flip();
            bl = true;
        }
        return bl;
    }

    private static final Unit access$connect(IpcPipe ipcPipe) {
        ipcPipe.connect();
        return Unit.INSTANCE;
    }

    private static final boolean onReadable(IpcPipe ipcPipe, SelectionKey selectionKey) {
        boolean bl;
        block2: {
            block3: {
                block5: {
                    IpcPacket ipcPacket;
                    block4: {
                        IpcPacket ipcPacket2;
                        boolean bl2 = WindowsIpcConnection.i();
                        bl = selectionKey.isReadable();
                        if (!bl2) break block2;
                        if (!bl) break block3;
                        SocketChannel socketChannel = ipcPipe.channel;
                        Intrinsics.checkNotNullExpressionValue(socketChannel, "socket");
                        ipcPacket = ipcPacket2 = ipcPipe.readFrame(socketChannel);
                        if (!bl2) break block4;
                        if (ipcPacket == null) break block5;
                        ipcPacket = ipcPacket2;
                    }
                    IpcPacket ipcPacket3 = ipcPacket;
                    DiscordRpcSettings.INSTANCE.handlePacket(ipcPacket3);
                    return true;
                }
                return false;
            }
            bl = false;
        }
        return bl;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    IpcPipe.b = ZkmKeys.a(-651979460711318947L, 3281717415083968930L, MethodHandles.lookup().lookupClass()).a(95314683144214L);
                    IpcPipe.k = new HashMap<K, V>(13);
                    var5 = IpcPipe.b ^ 21930880069382L;
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
                    var14_3 = new String[5];
                    var12_4 = 0;
                    var11_5 = "\u0091\u007f\u00ed\u00b3\u00bbi\u0086\u00dc2\u0096\u0093\u0005\u00be\u0004\u00ad\u00e5\u00df\u00d7\u00da\u00af\u0099!\u00e0\u00bd\u0010\u008d\u00a8\u009f\"\bD\u009b\u0087:b\u009d\u00e4\u0083\u009c{\"\u0010c{H\u00b9q6R5\u00aa.\u00ef\u000et\u00dd\u00adn";
                    var13_6 = 58;
                    var10_7 = 24;
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
                        var14_3[var12_4++] = IpcPipe.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u00e98\u00add\u00cak\u00a3\u0003\u001a\u00a8\u009f\u0005\u00ed@\u00de\u00faswB\u00c1\u00af\u0099\u0010\u00ef\u0087T=\"s\u00c3\u00ca\u00df\u00d8\u008b1\u008f\"\u00e6\u00b9\u0095\u00b1B\u00ae\u009e1\u001b\u0081\u00a4\u0010x,&W\u001eX\u00f67\u001bW\u0095\u00e8D\u00e5G\u00ba";
                        var13_6 = 65;
                        var10_7 = 48;
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
                        var14_3[var12_4++] = IpcPipe.zkmDecodeUtf8(var15_9).intern();
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
            IpcPipe.i = var14_3;
            IpcPipe.j = new String[5];
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
        var4_12 = var0_10.doFinal(new byte[]{73, -99, -112, -37, -106, -87, -40, 111});
        ** while (true)
        IpcPipe.m = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x29FC;
        if (j[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])k.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/nm", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = i[n2].getBytes("ISO-8859-1");
            IpcPipe.j[n2] = IpcPipe.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return j[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = IpcPipe.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nm" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(IpcPipe.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

