/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.PendingUpdateManager;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PacketUtil {
    private static int R;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public static final void sendPacket(@NotNull Packet packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        Globals.getNetworkHandler().sendPacket(packet);
    }

    /*
     * WARNING - void declaration
     */
    public static final void sendPackets(char c, @NotNull Packet[] packetArray, char c2) {
        block2: {
            void var7_6;
            int n = 0;
            long l = ((long)c << 48 | (long)c2 << 48 >>> 16 | (long)n << 32 >>> 32) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)1508911718877913000L, (long)l);
            Intrinsics.checkNotNullParameter(packetArray, (String)((Object)PacketUtil.zkmBootstrap("h", (int)16863, (long)(0x242EF4781EE5DA41L ^ l))));
            CallSite callSite2 = callSite;
            Packet[] packetArray2 = packetArray;
            int n2 = 0;
            int n3 = packetArray2.length;
            block0: while (n2 < n3) {
                Packet packet;
                Packet packet2 = packet = packetArray2[n2];
                Globals.getNetworkHandler().sendPacket(packet2);
                ++n2;
                while (c >= '\u0000' && callSite2 == false) {
                    if (callSite2 == false) continue block0;
                    if (c2 <= '\u0000') continue;
                }
                break block0;
            }
            if (c < '\u0000' || ZkmReferences.zkmBootstrap("\u00c5", (long)1496221886930618962L, (long)l) == null) break block2;
            ZkmReferences.zkmBootstrap("\u00c5", (int)(++var7_6), (long)1543598150584641248L, (long)l);
        }
    }

    public static final void sendSequencedPacket(@NotNull SequencedPacketCreator sequencedPacketCreator) {
        int n = PacketUtil.zkmIntConstant();
        Intrinsics.checkNotNullParameter(sequencedPacketCreator, "packetCreator");
        int n2 = n;
        AutoCloseable autoCloseable = Globals.getWorld().getPendingUpdateManager().incrementSequence();
        Throwable throwable = null;
        try {
            PendingUpdateManager pendingUpdateManager = (PendingUpdateManager)autoCloseable;
            Packet<ServerPlayPacketListener> packet = sequencedPacketCreator.predict(pendingUpdateManager.getSequence());
            Intrinsics.checkNotNullExpressionValue(packet, "predict(...)");
            PacketUtil.sendPacket(packet);
        }
        catch (Throwable throwable2) {
            throw throwable2;
        }
        finally {
            AutoCloseableKt.closeFinally(autoCloseable, throwable);
        }
        if (n2 == 0) {
            Module.zkmSetFlag(new Module[1]);
        }
    }

    public static final void sendCommand(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "command");
        Globals.getNetworkHandler().sendChatCommand(string);
    }

    public static void zkmSetFlag(int n) {
        R = n;
    }

    public static int zkmGetFlag() {
        return R;
    }

    public static int zkmIntConstant() {
        return 122;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                PacketUtil.a = ZkmKeys.a(3968893076603198003L, -6139546723945905808L, MethodHandles.lookup().lookupClass()).a(280235471804239L);
                var9 = PacketUtil.a ^ 100485939174887L;
                PacketUtil.d = new HashMap<K, V>(13);
                PacketUtil.zkmSetFlag(0);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[5];
                var5_4 = 0;
                var4_5 = "'\u00d4\u008dW\u00af\u0091%\u00d5>\u00ce\u00ed;w\u00ce|\u008a\u0002\u00a66\u00dd\u008e\u00941\u00a0;[\u00b0\u00e0\u00b6j\"\u00de\u0018\u00a6\u00e7\u00f2\u00f1\u0087\u00ff-,\u0095\u001f\u00ae\u00f5\u001f\u00c8\u0085\u00ea\u00b2\u00f6\u00f8\u0004\u00b6\u0011\u007f\u0014\u0010\u00f2x\u000b\u00f6\u00f5\u00c7\u00e7\u0019wR\u00b6\u00edf\u00fd\u00d9Z";
                var6_6 = 74;
                var3_7 = 32;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = PacketUtil.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00a2\u00cc\u009c\u001eh\u00cc-\u0087\u0085\u00c7S\u00db\u0080j\u00fe\u0092\u0010\u00a0\u0099\u000b\u0006\u00db\u00aavSnt\u00be\u0014pK\u0087:";
                    var6_6 = 33;
                    var3_7 = 16;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = PacketUtil.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        PacketUtil.b = var7_3;
        PacketUtil.c = new String[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xE08;
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
                throw new RuntimeException("su/catlean/_r", exception);
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
            PacketUtil.c[n2] = PacketUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PacketUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_r" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PacketUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

