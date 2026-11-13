/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

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
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.RequestCommandCompletionsC2SPacket;
import net.minecraft.network.packet.s2c.play.CommandSuggestionsS2CPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.PostTasksProcessEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.FastLatencySpikeDetectorMode;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.ui.NotificationType;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.PacketUtil;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class FastLatency
extends Module {
    @NotNull
    public static final FastLatency INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final ModeSetting spikeDetector$delegate;
    @NotNull
    private static final IntSetting messageDelay$delegate;
    @NotNull
    private static final ArrayDeque samples;
    @NotNull
    private static final Timer pingTimer;
    @NotNull
    private static final Timer u;
    @NotNull
    private static final Timer n;
    private static long T;
    private static int U;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;
    private static final long j;

    private FastLatency() {
        super("fast-latency", Categories.getMisc(), null, null);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final FastLatencySpikeDetectorMode getSpikeDetector() {
        return (FastLatencySpikeDetectorMode)((Object)spikeDetector$delegate.getValue(this, delegatedProperties[1]));
    }

    private final int getMessageDelay() {
        return ((Number)messageDelay$delegate.getValue(this, delegatedProperties[2])).intValue();
    }

    public final int j() {
        return U;
    }

    public final void G(int n) {
        U = n;
    }

    @Flow
    private final void onPostTasksProcess(PostTasksProcessEvent postTasksProcessEvent) {
        if (Globals.getMc().player != null && Globals.getMc().world != null && pingTimer.hasPassed(5000) && u.hasPassed2(this.getDelay())) {
            PacketUtil.sendPacket(new RequestCommandCompletionsC2SPacket(1337, "w "));
            T = System.currentTimeMillis();
            pingTimer.reset();
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        long l = a ^ 0x4E95AB401D15L;
        int n = (int)((l ^ 0x9B852479456L) >>> 32);
        if (receivePacket.getPacket() instanceof CommandSuggestionsS2CPacket) {
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundCommandSuggestionsPacket");
            if (((CommandSuggestionsS2CPacket)packet).comp_2262() == 1337) {
                U = (int)RangesKt.coerceIn(System.currentTimeMillis() - T, new LongRange(0L, j));
                if (samples.size() >= 20) {
                    samples.removeFirst();
                }
                samples.add(U);
                if ((float)U > (float)CollectionsKt.averageOfInt(samples) * 1.15f && samples.size() >= 20 && FastLatency.n.hasPassed2(this.getMessageDelay())) {
                    Object[] objectArray = new Object[]{(int)CollectionsKt.averageOfInt(samples), U};
                    String string = ModuleMessages.i18n$default(this, "spike", objectArray, false, 4, null);
                    if (this.getSpikeDetector() == FastLatencySpikeDetectorMode.BOTH || this.getSpikeDetector() == FastLatencySpikeDetectorMode.NOTIFICATION) {
                        ModuleMessages.notify$default(this, string, n, NotificationType.WARNING, 0, null, 12, null);
                    }
                    if (this.getSpikeDetector() == FastLatencySpikeDetectorMode.BOTH || this.getSpikeDetector() == FastLatencySpikeDetectorMode.CHAT) {
                        ModuleMessages.S(this, string, false, null);
                    }
                }
                pingTimer.setTime(5000);
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            FastLatency.a = ZkmKeys.a(-6965801511463904666L, 3826201949402237327L, MethodHandles.lookup().lookupClass()).a(209371219023231L);
                            var25 = FastLatency.a ^ 110951889553780L;
                            FastLatency.d = new HashMap<K, V>(13);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[13];
                            var21_4 = 0;
                            var20_5 = "\u00c4\u0018\u0081\u0000\u00c7\u001e\u0005\u0082M\u00f9\u00e6W\u00dd@\u00aa\u00a2Q\u0082\u00c0\u000f9\u00d0C\u0082\u008b3!\u008fl\u00c8\u00e7\u007f \u0006\u009c\u009f\u0019\t\u0096\u00a3O7\u008dd2\u00cb>~D%\u00ac\u0015\nj\u00caQcX\n\u00bbU\u00f5\u00b8jA\u00a8\u00bbH~;\u0088a\u00f6e\u0010P1j\f\u00c7\u00d3\u00ac\u0091\u00ab\u00f5O\u00a6*\u00a2~\u0005^\u0085\u00b7|\u00c5\u009e\u009c\u00abG\u0081\u00e0Z\u001d\u00b0\u00ce\u00a5\u00d5\u00ff\u0000]\u0003S'\u008a\u0098\\\u008f\u00c2\u00ad|\u0093\u0098+\u00e1'\u00bc\u00b7>gp\u00eff2Y\u00fd_[2\u00bf\u0095t`m\u00d3\u0091h\u00a4\u00ee\t\u00e0\u00c2J\u00bcg\u00b6\u00a1\u0002\u00a0\u00e0\u009e\u0084\u0088\"_\u0000\u000e=b:\u0098\u009e\u00f0\u00dc\u00e0J'q\u00ed\u0000p\u00fe\u0086\u00ad\u0006\u00faJ\u00c9\u008e\u0015d\u00f6z\n\u00b7\u0093\u00b19\n\u0089\u001ad\u00c2\"\u0089 \u0015\u00a7o[\u0013\u00e7p\u0097\u00a9m\u00e0^i \u0092\u00ccl2\u00b5\u00c7:~\u0011P\u00c7M\u00c00(\u0003\u008b\u00e5\u0089z\u008cRp\u001c\u00bb\u00b9\u009b\u00b8=\u0090\u0014\u00b2\u00f7\u00b2)\u00f1\u008fyDz\u00fd\u00ad\u0092\u00af\u00a2\u0001\u00b3\u00ee\u008d\u001b\u00ff',T\u00f8\u0010we\u00fdAu\u00e1\u0019\u001e\u00d2U\u00bbB\u008b\u00e1^\u00cb \u0088\u007f*\u0006EN\u00a4\u00a4\u00ab\u00ea\u00b22\u00e3\u009f\u00fc\u00d5f\u00d88t\u00b1\u0004\u00c6i#\u001e\u00eblM\u00f7\u009f'\u0010/\u00f5>q{Cc\u0005\u0095\u0096]\n&\u00ee\u009f\u0080 \u00ab\u00f2\u0097\u00bf\u00b4\u0085$\u00b0M\u00a0/\u0012\u0086]\u009c\u00c5\u0080\u00bb@\u00ee\\;\u009dU\u00ea\u00c83\u008aI\u008d\"\u00d5\u0010s\u00a2\u001f9d\u00c0>\u00a07\u00b8\u0017\u00bcG\u0087\u00e0\u0089\u0010Fv\u0086\u0085Wp(\u0014%1\u00fe@}m\u00a6n\u0018\u0006\u00a3\u00d6\u00c4>\u00ea,\u0090?\u00b9\u00e2\u00f9>u^\u00f6\u00f4\u00fc\u00b7\u008du\u00bf\u00ec\u0092";
                            var22_6 = 434;
                            var19_7 = 32;
                            var18_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = FastLatency.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "6?\u0091A\u00e4\u00a5\u00b3\u00faf\u00e9U\u00a9\u008cD2\u008d\u00cd=\u00f0g\u000b\u0098>\u0081H\u00bbc\u00a1\u00c7\u0018\u009b\u00baH\u00d0\u0000\u0092\u00c8znbL)\u00a7\u00b8vs\u009dY\u00bbk\u00bc\u00aax\u00d1\u00a3x\u00c3\u001b7\u0018\u009a>\u009b\u00eb\u0095eT\u00e2G\u0094\u008d\u0015\u00d9\u001a\u00cb\u00f27\u008a[\\\u00fd|u\\\u00de\u00a1\u00eb\u0010\u00e9\u0011\u00cd\u0087U\u000b\u00f8\u00f73\u000fP\u000b\u00cb\u00fc\u00db\u0093\u00b9";
                                var22_6 = 105;
                                var19_7 = 32;
                                var18_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = FastLatency.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    FastLatency.b = var23_3;
                    FastLatency.c = new String[13];
                    FastLatency.h = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[14];
                    var8_13 = 0;
                    var9_14 = "n\u00b7\u009f\u00928\u00a8\u00c9\u00e5\u00fd\u00ef\u0016\u00a8\u00aa\u0003d\u00b1\u00e5I\u00ca\u0015[\u00f2<\u009d\u0085\u00f1Gz\u00b1\u00ebc8K^-vXW~\u008b\u0019\u0010\u00e4\u00ce\u00e4\u00b1B\u0001\u008c\u001dD9a\u00e0\u00fe\u00fa\u000e~1\u00ae\u00e0\u00da\u0090\u00e3\u00ed\u00df\u00a4:\u00d0\u00bf\u0081\u00c8\u0002\u00bd\u00f26\u00b0-\u001fTBd]u\u00b6\u008d\u00b2<o\u00b1R\u00a3OW\u00b8\u00c7";
                    var10_15 = 96;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u00f2M\u001d\u00c2 \u00f2T<\u00b52`t\u0017}\u009e\u00f7";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl91:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                switch (v13) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl104:
                    // 1 sources

                    ** continue;
                }
            }
            FastLatency.f = var11_12;
            FastLatency.g = new Integer[14];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl118:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{60, 3, -103, -25, -121, -49, -73, -11});
        ** while (true)
        FastLatency.j = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
        var34_23 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(FastLatency.class, "delay", "getDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(FastLatency.class, "spikeDetector", "getSpikeDetector()Lsu/catlean/yg;", 0)), Reflection.property1(new PropertyReference1Impl(FastLatency.class, "messageDelay", "getMessageDelay()I", 0))};
        FastLatency.delegatedProperties = var34_23;
        FastLatency.INSTANCE = new FastLatency();
        FastLatency.delay$delegate = SettingFactory.intSetting$default(FastLatency.INSTANCE, "delay", 80, new IntRange(0, 1000), null, null, 24, null);
        FastLatency.spikeDetector$delegate = SettingFactory.modeSetting$default((SettingContainer)FastLatency.INSTANCE, "spike-detector", (Enum)FastLatencySpikeDetectorMode.CHAT, null, null, 12, null);
        FastLatency.messageDelay$delegate = SettingFactory.intSetting$default(FastLatency.INSTANCE, "message-delay", 500, new IntRange(0, 10000), null, null, 24, null);
        FastLatency.samples = new ArrayDeque<E>(20);
        FastLatency.pingTimer = new Timer();
        FastLatency.u = new Timer();
        FastLatency.n = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x388B;
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
                throw new RuntimeException("su/catlean/qo", exception);
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
            FastLatency.c[n2] = FastLatency.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FastLatency.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x542F;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qo", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FastLatency.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FastLatency.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FastLatency.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FastLatency.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

