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
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.Transient;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.util.Proxy$Companion;
import su.catlean.util.ProxySerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class Proxy {
    @NotNull
    public static final Proxy$Companion Companion;
    @NotNull
    private String id;
    @NotNull
    private String ip;
    private int port;
    @NotNull
    private String user;
    @NotNull
    private String password;
    private boolean socks4;
    private int ping;
    private static int V;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    public Proxy(@NotNull String string, @NotNull String string2, int n, @NotNull String string3, @NotNull String string4, boolean bl, int n2) {
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        long l = ((long)n3 << 32 | (long)n4 << 56 >>> 32 | (long)n5 << 40 >>> 40) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Proxy.zkmBootstrap("h", (int)30711, (long)(0x734E981385EF142BL ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)Proxy.zkmBootstrap("h", (int)29749, (long)(0x3CC3C554DE8597E5L ^ l))));
        Intrinsics.checkNotNullParameter(string3, (String)((Object)Proxy.zkmBootstrap("h", (int)15051, (long)(0x5969DDBD7189D911L ^ l))));
        Intrinsics.checkNotNullParameter(string4, (String)((Object)Proxy.zkmBootstrap("h", (int)21615, (long)(0x286323B0EC8F37BBL ^ l))));
        this.id = string;
        this.ip = string2;
        this.port = n;
        this.user = string3;
        this.password = string4;
        this.socks4 = bl;
        this.ping = n2;
    }

    public Proxy(String string, String string2, int n, String string3, String string4, boolean bl, int n2, int n3, DefaultConstructorMarker defaultConstructorMarker) {
        int n4 = 0;
        int n5 = 0;
        long l = ((long)n4 << 32 | (long)n5 << 32 >>> 32) ^ a;
        if ((n3 & Proxy.zkmBootstrap2("i", (int)13001, (long)(0x2186B90F1FA13EE8L ^ l))) != 0) {
            n2 = -1;
        }
        this(string, string2, n, string3, string4, bl, n2);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final void setId(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.id = string;
    }

    @NotNull
    public final String getIp() {
        return this.ip;
    }

    public final void setIp(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.ip = string;
    }

    public final int getPort() {
        return this.port;
    }

    public final void setPort(int n) {
        this.port = n;
    }

    @NotNull
    public final String getUser() {
        return this.user;
    }

    public final void setUser(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.user = string;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.password = string;
    }

    public final boolean isSocks4() {
        return this.socks4;
    }

    public final void setSocks4(boolean bl) {
        this.socks4 = bl;
    }

    public final int getPing() {
        return this.ping;
    }

    public final void setPing(int n) {
        this.ping = n;
    }

    @Transient
    public static void w() {
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final String getIp2() {
        return this.ip;
    }

    public final int getPort2() {
        return this.port;
    }

    @NotNull
    public final String getUser2() {
        return this.user;
    }

    @NotNull
    public final String getPassword2() {
        return this.password;
    }

    public final boolean isSocks42() {
        return this.socks4;
    }

    public final int getPing2() {
        return this.ping;
    }

    @NotNull
    public final Proxy copy(@NotNull String string, @NotNull String string2, int n, @NotNull String string3, @NotNull String string4, boolean bl, int n2) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Proxy.zkmBootstrap("h", (int)25788, (long)(0x22D1854E5F2BA8E4L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)Proxy.zkmBootstrap("h", (int)13109, (long)(0x1F02A1B66B2DFF6AL ^ l))));
        Intrinsics.checkNotNullParameter(string3, (String)((Object)Proxy.zkmBootstrap("h", (int)25061, (long)(0x60B329F7D17B2DB5L ^ l))));
        Intrinsics.checkNotNullParameter(string4, (String)((Object)Proxy.zkmBootstrap("h", (int)22374, (long)(0x2AA2E95C7AEA9B3DL ^ l))));
        return new Proxy(string, string2, n, string3, string4, bl, n2);
    }

    public static Proxy copy$default(Proxy proxy, String string, String string2, long l, int n, String string3, String string4, boolean bl, int n2, int n3, Object object) {
        block21: {
            int n4;
            block20: {
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4441649575345383063L, (long)l);
                n4 = n3 & 1;
                if (callSite == false) {
                    if (n4 != 0) {
                        string = proxy.id;
                    }
                    n4 = n3 & 2;
                }
                CallSite callSite2 = callSite;
                if (l > 0L) {
                    if (callSite2 == false) {
                        if (n4 != 0) {
                            string2 = proxy.ip;
                        }
                        n4 = n3 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 == false) {
                        if (n4 != 0) {
                            n = proxy.port;
                        }
                        n4 = n3 & Proxy.zkmBootstrap2("i", (int)23926, (long)(0x4ED30CC3D973A7CEL ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 == false) {
                        if (n4 != 0) {
                            string3 = proxy.user;
                        }
                        n4 = n3 & Proxy.zkmBootstrap2("i", (int)11964, (long)(0x72DA99FD95055402L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 == false) {
                        if (n4 != 0) {
                            string4 = proxy.password;
                        }
                        n4 = n3 & Proxy.zkmBootstrap2("i", (int)3999, (long)(0x559DFEDACD09F525L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 == false) {
                        if (n4 != 0) {
                            bl = proxy.socks4;
                        }
                        n4 = n3 & Proxy.zkmBootstrap2("i", (int)26527, (long)(0xD644DED3E2E9D23L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 != false) break block20;
                if (n4 == 0) break block21;
                n4 = proxy.ping;
            }
            n2 = n4;
        }
        return proxy.copy(string, string2, n, string3, string4, bl, n2);
    }

    @NotNull
    public String toString() {
        int n = this.ping;
        boolean bl = this.socks4;
        String string = this.password;
        String string2 = this.user;
        int n2 = this.port;
        String string3 = this.ip;
        String string4 = this.id;
        return "Proxy(id=" + string4 + ", ip=" + string3 + ", port=" + n2 + ", user=" + string2 + ", password=" + string + ", socks4=" + bl + ", ping=" + n + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * 31 + this.ip.hashCode();
        n = n * 31 + Integer.hashCode(this.port);
        n = n * 31 + this.user.hashCode();
        n = n * 31 + this.password.hashCode();
        n = n * 31 + Boolean.hashCode(this.socks4);
        n = n * 31 + Integer.hashCode(this.ping);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Proxy)) {
            return false;
        }
        Proxy proxy = (Proxy)object;
        if (!Intrinsics.areEqual(this.id, proxy.id)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.ip, proxy.ip)) {
            return false;
        }
        if (this.port != proxy.port) {
            return false;
        }
        if (!Intrinsics.areEqual(this.user, proxy.user)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.password, proxy.password)) {
            return false;
        }
        if (this.socks4 != proxy.socks4) {
            return false;
        }
        return this.ping == proxy.ping;
    }

    @JvmStatic
    public static final void write$Self(Proxy proxy, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, proxy.id);
        compositeEncoder.encodeStringElement(serialDescriptor, 1, proxy.ip);
        compositeEncoder.encodeIntElement(serialDescriptor, 2, proxy.port);
        compositeEncoder.encodeStringElement(serialDescriptor, 3, proxy.user);
        compositeEncoder.encodeStringElement(serialDescriptor, 4, proxy.password);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 5, proxy.socks4);
    }

    public Proxy(int n, String string, String string2, int n2, String string3, String string4, boolean bl, SerializationConstructorMarker serializationConstructorMarker) {
        if (63 != (0x3F & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 63, ProxySerializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.ip = string2;
        this.port = n2;
        this.user = string3;
        this.password = string4;
        this.socks4 = bl;
        this.ping = -1;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Proxy.a = ZkmKeys.a(-7652981070721862985L, -7688904333320191220L, MethodHandles.lookup().lookupClass()).a(244113080261655L);
                        var20 = Proxy.a ^ 89335643551130L;
                        Proxy.e = new HashMap<K, V>(13);
                        Proxy.zkmSetFlag(0);
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
                        var18_3 = new String[17];
                        var16_4 = 0;
                        var15_5 = "J\u0018\u00e5_p\u00d5\u007fj\u00cd\u0093\u00b1\u00ab\u000e\u00c2\u00ce\u00d2\u00bc\u00a3\u0086\u00e9\u00c9\u00fcWry\u0018\u00f1\u00f0\u00b0_\u000bj\u0010\u00bd\u00c6\u00afjM\u00d6\u0092\u00b2\u00c6\u0080i\u001f\u00d7\u00e4\u00186\u0010\u00ff\u00a9\u00a4\u00be\u00d4\u008f<f\b\u001f\u00ba\u00f1\u00b3\u00e3\u00f8\u00d7  u8eP\u00f4b\u00b7\u00834\u00ae\u00de\u00c7!x\u001b\u00b9%8\u0091\u00e9\u00fb\u009e\u000bg\u00d4\u008d\u0088\u001b{\u00d0\u00f9\u0010\u0085\u00bb&F\u00d7,\u00cfU\u00be\b1\u001bM\u0002P`\u0010SP\u0000gQn\u00c3\u0011A%{k|:\u00cdu\u0018\u00cdw\u00c6\u008f5(\f\u0019\u00ae.,+\u00e1E\u00fbZ\u00edm\u00c2\u0088P\u00cbw\u00a7\u0010\u0083@q\u00fa\u00e7\u0011w\u00f6\r\u009c\u00d3 \u0090\u00a6\u00f3\u0017\u0010\u00cc\n\u0011\u00e6\u00a8\u00e0\u00cf\u00c2Dk;\u00ab\u008c\u00c0\u00de-\u0010\u00cb`\u00e6\u00a7\u000bs\u0097H\u0013h<\u00da\u00f2\u00dc*\u0011\u0010\u000b>\u00da\u0007S\u00aa\u00d27\u009f6\u00ae\u00d9\u009f^\u0007/ \u000b0.\u00ef\u0080\u00c9\u0093\u00ce! \u00ec\u00d2\u00b2\u00e2\u009f\u00ab\u008f\u00d7\u00d7U\u00971S\u0019\u0081\u00de\u00f8\u00da\u00e2\u00f4fP \u0089\u00ef\u008fB\tW\u0011\u00aeXx@\u00a0\u00e0\u0002\u00ad\u009c\u0093\u001fFK P\u00b4P/\u00c9J\u00ae\u00f3D\u00f6\u00de\u0010!\u00c5\u0005\u0099u\r\u00d5\u00e1\u00e5\u00b7'L\u00cc\u00870\u009c\u0010\u00f8\u0092Z\u00da\u001bo\u00d1\u0081\u00b4\u009d?d\u00ca}\u008b\u0095";
                        var17_6 = 326;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = Proxy.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "}\u00e4\u00f4/\u0006\u0098`'wlB\u00e7\u0087\u0006`f\u00103U\u0092\u00a1\u00ca=\u0084\u00a0|4\u0017\r\u0007q2\u00a8";
                            var17_6 = 33;
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
                            var18_3[var16_4++] = Proxy.zkmDecodeUtf8(var19_9).intern();
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
                Proxy.b = var18_3;
                Proxy.d = new String[17];
                Proxy.i = new HashMap<K, V>(13);
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
                var6_12 = new long[9];
                var3_13 = 0;
                var4_14 = "2c\u0000A\u00b6\u0085G@\u00b7x\n\u00e0\u00e1\u00d7!\u000b\u00f7\u001b\u00dc\u0099x\u0002\u0014 \u00b1'\u00895P\u0095\u00b4d\u0000.\u00eaA\\\u0080]&QIb\u009b\u00ca\u00e1g\u00e0y\u00f2\u0080&Q^\b\u00dc";
                var5_15 = 56;
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
                    var4_14 = "A\u00fd\u00ea\u00a1\u0082\u009fz\u00ab\u00deB\u00bf\u00e7\u00a6\u00b9\u009e\u00c7";
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
        Proxy.g = var6_12;
        Proxy.h = new Integer[9];
        Proxy.Companion = new Proxy$Companion(null);
    }

    public static void zkmSetFlag(int n) {
        V = n;
    }

    public static int zkmGetFlag() {
        return V;
    }

    public static int zkmIntConstant() {
        return 71;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2712;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/c2", exception);
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
            Proxy.d[n2] = Proxy.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Proxy.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/c2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x465A;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/c2", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Proxy.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Proxy.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/c2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Proxy.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Proxy.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

