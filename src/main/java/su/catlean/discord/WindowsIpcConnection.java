/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.io.RandomAccessFile;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
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
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.JsonFormats;
import su.catlean.discord.DiscordRpcSettings;
import su.catlean.discord.IpcConnection;
import su.catlean.discord.IpcFrame;
import su.catlean.discord.IpcOpcode;
import su.catlean.discord.IpcPacket;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class WindowsIpcConnection
extends IpcConnection {
    @NotNull
    private final RandomAccessFile C;
    private static boolean U;
    private static final long b;
    private static final String[] e;
    private static final String[] f;
    private static final Map h;
    private static final long i;
    private static final long j;

    public WindowsIpcConnection(@NotNull String string) {
        WindowsIpcConnection.i();
        Intrinsics.checkNotNullParameter(string, "name");
        this.C = new RandomAccessFile(string, "rw");
        ThreadsKt.thread$default(true, false, null, "Discord IPC - Pipe thread", 0, () -> WindowsIpcConnection.access$readFrame(this), (int)i, null);
    }

    @Override
    protected void write(@NotNull ByteBuffer byteBuffer) {
        block2: {
            Object object;
            Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
            Object object2 = this;
            try {
                object = object2;
                ((WindowsIpcConnection)object).C.write(byteBuffer.array());
                object = Result.constructor-impl(Unit.INSTANCE);
            }
            catch (Throwable throwable) {
                object = Result.constructor-impl(ResultKt.createFailure(throwable));
            }
            object2 = object;
            if (Result.exceptionOrNull-impl(object2) == null) break block2;
            DiscordRpcSettings.INSTANCE.sendPresence();
        }
    }

    private final void readFrame(short s, short s2, int n) {
        long l = ((long)s << 48 | (long)s2 << 48 >>> 16 | (long)n << 32 >>> 32) ^ b;
        int n2 = (int)((l ^ 0x2C4EC458743DL) << 32 >>> 48);
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        while (true) {
            String string;
            Intrinsics.checkNotNull(byteBuffer);
            this.write(n2, byteBuffer);
            IpcOpcode ipcOpcode = (IpcOpcode)((Object)IpcOpcode.get$ENTRIES().get(Integer.reverseBytes(byteBuffer.getInt(0))));
            this.write(n2, byteBuffer);
            int n3 = Integer.reverseBytes(byteBuffer.getInt(0));
            ByteBuffer byteBuffer2 = ByteBuffer.allocate(n3);
            Intrinsics.checkNotNull(byteBuffer2);
            this.write(n2, byteBuffer2);
            Intrinsics.checkNotNullExpressionValue(Charset.defaultCharset().decode(byteBuffer2.rewind()).toString(), "toString(...)");
            Json json = JsonFormats.getJson();
            String string2 = string;
            json.getSerializersModule();
            DiscordRpcSettings.INSTANCE.handlePacket(new IpcPacket(ipcOpcode, (IpcFrame)json.decodeFromString(IpcFrame.Companion.serializer(), string2)));
        }
    }

    private final void write(int n, ByteBuffer byteBuffer) {
        block4: {
            int n2 = 0;
            int n3 = 0;
            long l = ((long)n2 << 32 | (long)n << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ b;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)6942685841496046074L, (long)l);
            byteBuffer.rewind();
            CallSite callSite2 = callSite;
            block0: while (this.C.length() < (long)byteBuffer.remaining()) {
                Thread.onSpinWait();
                Thread.sleep(j);
                do {
                    CallSite callSite3 = callSite2;
                    if (n >= 0) {
                        if (callSite3 == false) break block4;
                        callSite3 = callSite2;
                    }
                    if (callSite3 != false) continue block0;
                } while (n < 0);
            }
            while (byteBuffer.hasRemaining()) {
                this.C.getChannel().read(byteBuffer);
                if (callSite2 != false) continue;
            }
        }
    }

    @Override
    public void close() {
        WindowsIpcConnection windowsIpcConnection = this;
        try {
            WindowsIpcConnection windowsIpcConnection2 = windowsIpcConnection;
            windowsIpcConnection2.C.close();
            Result.constructor-impl(Unit.INSTANCE);
        }
        catch (Throwable throwable) {
            Result.constructor-impl(ResultKt.createFailure(throwable));
        }
    }

    private static final Unit access$readFrame(WindowsIpcConnection windowsIpcConnection) {
        long l = b ^ 0x370943B9DFEFL;
        long l2 = l ^ 0x7643CC53601L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 48);
        int n3 = (int)(l2 << 32 >>> 32);
        windowsIpcConnection.readFrame((short)n, (short)n2, n3);
        return Unit.INSTANCE;
    }

    public static void zkmSetFlag(boolean bl) {
        U = bl;
    }

    public static boolean boolean_zkmGetFlag() {
        return U;
    }

    public static boolean i() {
        return true;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block17: {
            block16: {
                block15: {
                    block14: {
                        WindowsIpcConnection.b = ZkmKeys.a(-7187884488579481971L, 3040166209400938466L, MethodHandles.lookup().lookupClass()).a(55644735014189L);
                        var19 = WindowsIpcConnection.b ^ 54702639518118L;
                        WindowsIpcConnection.h = new HashMap<K, V>(13);
                        WindowsIpcConnection.zkmSetFlag(false);
                        var10_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var19 >>> 56);
                        for (var11_2 = 1; var11_2 < 8; ++var11_2) {
                            v2 = v2;
                            v2[var11_2] = (byte)(var19 << var11_2 * 8 >>> 56);
                        }
                        var10_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var17_3 = new String[5];
                        var15_4 = 0;
                        var14_5 = "*NX\u00bc\u0005\r\\/\u00fc\u008f\u001ar\u00ca\u00e1\u008eG .E\u00e0\u00efm\u00c9\u00b0Trr\u00e4\u00cf\u00de\u0097PE\u00c9_AD\u00dbG\u00a0I\\\u00a4\u00a5\u00db\u00f5*%/\u0010\u001a'\u008eI'\u00e5\u00a5\u00a8t\u00a2N\u00fd\u00ed\u009f\u0091\u00af";
                        var16_6 = 66;
                        var13_7 = 16;
                        var12_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var12_8;
                            v4 = var14_5.substring(v3, v3 + var13_7);
                            v5 = -1;
                            break block14;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var17_3[var15_4++] = WindowsIpcConnection.zkmDecodeUtf8(var18_9).intern();
                            if ((var12_8 += var13_7) < var16_6) {
                                var13_7 = var14_5.charAt(var12_8);
                                ** continue;
                            }
                            var14_5 = "\u00e2t\u0093\u00e1\u0001Y\u0083\u00c3=w\u00c1K\u0017h8]F\u00a0\u00da\u00d5\u00e9\u00d5\u00a6\u0001\u0089\u00db%6\bS\u008e\u00eb\u0089\u0006\u00fd\u00b5Y`<$.G\u00ff\u00b1Q_O)\u0010RUY\u00c3\u00b7\u008f\u00fc\u0088\u00dd\u0010\u0082\u00ee\u009e\u0095\u00e8\u00a8";
                            var16_6 = 65;
                            var13_7 = 48;
                            var12_8 = -1;
lbl35:
                            // 2 sources

                            while (true) {
                                v6 = ++var12_8;
                                v4 = var14_5.substring(v6, v6 + var13_7);
                                v5 = 0;
                                break block14;
                                break;
                            }
                            break;
                        }
lbl40:
                        // 1 sources

                        while (true) {
                            var17_3[var15_4++] = WindowsIpcConnection.zkmDecodeUtf8(var18_9).intern();
                            if ((var12_8 += var13_7) < var16_6) {
                                var13_7 = var14_5.charAt(var12_8);
                                ** continue;
                            }
                            break block15;
                            break;
                        }
                    }
                    var18_9 = var10_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                WindowsIpcConnection.e = var17_3;
                WindowsIpcConnection.f = new String[5];
                var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var19 >>> 56);
                for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                    v9 = v9;
                    v9[var6_11] = (byte)(var19 << var6_11 * 8 >>> 56);
                }
                break block16;
lbl66:
                // 1 sources

                while (true) {
                    continue;
                    break;
                }
            }
            var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
            var9_12 = var5_10.doFinal(new byte[]{-39, -73, -96, 42, -71, 69, -88, 19});
            ** while (true)
            WindowsIpcConnection.i = ((long)var9_12[0] & 255L) << 56 | ((long)var9_12[1] & 255L) << 48 | ((long)var9_12[2] & 255L) << 40 | ((long)var9_12[3] & 255L) << 32 | ((long)var9_12[4] & 255L) << 24 | ((long)var9_12[5] & 255L) << 16 | ((long)var9_12[6] & 255L) << 8 | (long)var9_12[7] & 255L;
            var0_13 = Cipher.getInstance("DES/CBC/NoPadding");
            v10 = SecretKeyFactory.getInstance("DES");
            v11 = new byte[8];
            v12 = v11;
            v11[0] = (byte)(var19 >>> 56);
            for (var1_14 = 1; var1_14 < 8; ++var1_14) {
                v12 = v12;
                v12[var1_14] = (byte)(var19 << var1_14 * 8 >>> 56);
            }
            break block17;
lbl83:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_13.init(2, (Key)v10.generateSecret(new DESKeySpec(v12)), new IvParameterSpec(new byte[8]));
        var4_15 = var0_13.doFinal(new byte[]{93, -107, 117, -28, -29, -114, -90, -72});
        ** while (true)
        WindowsIpcConnection.j = ((long)var4_15[0] & 255L) << 56 | ((long)var4_15[1] & 255L) << 48 | ((long)var4_15[2] & 255L) << 40 | ((long)var4_15[3] & 255L) << 32 | ((long)var4_15[4] & 255L) << 24 | ((long)var4_15[5] & 255L) << 16 | ((long)var4_15[6] & 255L) << 8 | (long)var4_15[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1321;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/nx", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = e[n2].getBytes("ISO-8859-1");
            WindowsIpcConnection.f[n2] = WindowsIpcConnection.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = WindowsIpcConnection.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(WindowsIpcConnection.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

