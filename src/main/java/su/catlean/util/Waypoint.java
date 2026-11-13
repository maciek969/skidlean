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
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.util.Waypoint$$serializer;
import su.catlean.util.Waypoint$Companion;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class Waypoint {
    @NotNull
    public static final Waypoint$Companion Companion;
    @NotNull
    private String id;
    private int x;
    private int y;
    private int z;
    @NotNull
    private String server;
    private int dimension;
    private boolean visible;
    private static String U;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public Waypoint(@NotNull String string, int n, int n2, int n3, @NotNull String string2, int n4, boolean bl) {
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        long l = ((long)n5 << 32 | (long)n6 << 40 >>> 32 | (long)n7 << 56 >>> 56) ^ b;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Waypoint.zkmBootstrap("f", (int)8435, (long)(0x5A0BFEBE6EA55DDCL ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)Waypoint.zkmBootstrap("f", (int)19178, (long)(0x278EDA24BB8E37C7L ^ l))));
        this.id = string;
        this.x = n;
        this.y = n2;
        this.z = n3;
        this.server = string2;
        this.dimension = n4;
        this.visible = bl;
    }

    public Waypoint(String string, int n, int n2, int n3, String string2, int n4, boolean bl, int n5, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n5 & 0x10) != 0) {
            string2 = "localhost";
        }
        if ((n5 & 0x20) != 0) {
            n4 = 0;
        }
        if ((n5 & 0x40) != 0) {
            bl = true;
        }
        this(string, n, n2, n3, string2, n4, bl);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final void setId(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.id = string;
    }

    public final int getX() {
        return this.x;
    }

    public final void setX(int n) {
        this.x = n;
    }

    public final int getY() {
        return this.y;
    }

    public final void setY(int n) {
        this.y = n;
    }

    public final int getZ() {
        return this.z;
    }

    public final void setZ(int n) {
        this.z = n;
    }

    @NotNull
    public final String getServer() {
        return this.server;
    }

    public final void setServer(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.server = string;
    }

    public final int getDimension() {
        return this.dimension;
    }

    public final void setDimension(int n) {
        this.dimension = n;
    }

    public final boolean isVisible() {
        return this.visible;
    }

    public final void setVisible(boolean bl) {
        this.visible = bl;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    public final int getX2() {
        return this.x;
    }

    public final int getY2() {
        return this.y;
    }

    public final int getZ2() {
        return this.z;
    }

    @NotNull
    public final String getServer2() {
        return this.server;
    }

    public final int getDimension2() {
        return this.dimension;
    }

    public final boolean isVisible2() {
        return this.visible;
    }

    @NotNull
    public final Waypoint copy(@NotNull String string, int n, int n2, int n3, @NotNull String string2, int n4, boolean bl) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Waypoint.zkmBootstrap("f", (int)28304, (long)(0x4989854E1C603213L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)Waypoint.zkmBootstrap("f", (int)29683, (long)(0x7E1F2CA4E4F2AF79L ^ l))));
        return new Waypoint(string, n, n2, n3, string2, n4, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static Waypoint copy$default(Waypoint waypoint, String string, int n, int n2, long l, int n3, String string2, int n4, boolean bl, int n5, Object object) {
        boolean bl2;
        block21: {
            boolean bl3;
            block20: {
                void var10_10;
                l = b ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2543550680115085117L, (long)l);
                bl3 = var10_10 & 1;
                if (callSite != null) {
                    if (bl3) {
                        string = waypoint.id;
                    }
                    bl3 = var10_10 & 2;
                }
                CallSite callSite2 = callSite;
                if (l >= 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            n = waypoint.x;
                        }
                        bl3 = var10_10 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            n2 = waypoint.y;
                        }
                        bl3 = var10_10 & Waypoint.zkmBootstrap2("l", (int)30935, (long)(0x728D62F236B964D0L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            n3 = waypoint.z;
                        }
                        bl3 = var10_10 & Waypoint.zkmBootstrap2("l", (int)3885, (long)(0x6762B803BF361324L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l >= 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            string2 = waypoint.server;
                        }
                        bl3 = var10_10 & Waypoint.zkmBootstrap2("l", (int)29100, (long)(0x2C14B5E9DFA6DA9L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l >= 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            n4 = waypoint.dimension;
                        }
                        bl3 = var10_10 & Waypoint.zkmBootstrap2("l", (int)3705, (long)(0x36B1B08FC67E1279L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == null) break block20;
                if (!bl3) break block21;
                bl3 = waypoint.visible;
            }
            bl2 = bl3;
        }
        return waypoint.copy(string, n, n2, n3, string2, n4, bl2);
    }

    @NotNull
    public String toString() {
        boolean bl = this.visible;
        int n = this.dimension;
        String string = this.server;
        int n2 = this.z;
        int n3 = this.y;
        int n4 = this.x;
        String string2 = this.id;
        return "WayPoint(id=" + string2 + ", x=" + n4 + ", y=" + n3 + ", z=" + n2 + ", server=" + string + ", dimension=" + n + ", visible=" + bl + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * 31 + Integer.hashCode(this.x);
        n = n * 31 + Integer.hashCode(this.y);
        n = n * 31 + Integer.hashCode(this.z);
        n = n * 31 + this.server.hashCode();
        n = n * 31 + Integer.hashCode(this.dimension);
        n = n * 31 + Boolean.hashCode(this.visible);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Waypoint)) {
            return false;
        }
        Waypoint waypoint = (Waypoint)object;
        if (!Intrinsics.areEqual(this.id, waypoint.id)) {
            return false;
        }
        if (this.x != waypoint.x) {
            return false;
        }
        if (this.y != waypoint.y) {
            return false;
        }
        if (this.z != waypoint.z) {
            return false;
        }
        if (!Intrinsics.areEqual(this.server, waypoint.server)) {
            return false;
        }
        if (this.dimension != waypoint.dimension) {
            return false;
        }
        return this.visible == waypoint.visible;
    }

    @JvmStatic
    public static final void write$Self(Waypoint waypoint, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block2: {
            compositeEncoder.encodeStringElement(serialDescriptor, 0, waypoint.id);
            compositeEncoder.encodeIntElement(serialDescriptor, 1, waypoint.x);
            compositeEncoder.encodeIntElement(serialDescriptor, 2, waypoint.y);
            compositeEncoder.encodeIntElement(serialDescriptor, 3, waypoint.z);
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) ? true : !Intrinsics.areEqual(waypoint.server, "localhost")) {
                compositeEncoder.encodeStringElement(serialDescriptor, 4, waypoint.server);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) ? true : waypoint.dimension != 0) {
                compositeEncoder.encodeIntElement(serialDescriptor, 5, waypoint.dimension);
            }
            if (!(compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) ? true : !waypoint.visible)) break block2;
            compositeEncoder.encodeBooleanElement(serialDescriptor, 6, waypoint.visible);
        }
    }

    public Waypoint(int n, String string, int n2, int n3, int n4, String string2, int n5, boolean bl, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (0xF & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 15, Waypoint$$serializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.x = n2;
        this.y = n3;
        this.z = n4;
        this.server = (n & 0x10) == 0 ? "localhost" : string2;
        this.dimension = (n & 0x20) == 0 ? 0 : n5;
        this.visible = (n & 0x40) == 0 ? true : bl;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Waypoint.b = ZkmKeys.a(5657386671542807604L, -4264056233285571399L, MethodHandles.lookup().lookupClass()).a(236359970585003L);
                        var20 = Waypoint.b ^ 118253021840231L;
                        Waypoint.e = new HashMap<K, V>(13);
                        Waypoint.zkmSetFlag("YM31G");
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
                        var18_3 = new String[15];
                        var16_4 = 0;
                        var15_5 = "\u008dSS;\u0015\u0015\u00c0N\u00cfgR\u000b\u0001CL\u00ca \u0003\u00f8LB[\u00c34*\u0082\u00dd\u00a7\u00cemK1z'8\u0086\u001d\u00c3\u00b8\u0002\u00e2\"mc+\u0018b\u00c2\u00c1 \u00c6=^\u0087y\u00cc\u0015\u00e2w\u008b\u009e\\{\u0001\u0010\u00a3\u00c5d\u007f\u0082\u009aFh\u0098S*c\u00ca\u0003\u00c2'\u00f0\u0010\u0013/?\u0015o\b\u009d\u0007\u00a8\u00e0i-\u007f\u000f6\u0019 \u008f\u00a0[\u00acG\u00f9 [\u00e6]9At:\u00f7\u008ed\u00f5\u0011\u009d\u0007\u001bQ\u00fc\u00feg\u00a0\u00b0\u00af$\u00e6\u00e7\u0010\u00b1\u00ce-\u00ce\u009d\u0086[\u00de\u00a6\u00f8\u0018\u0004B\u000b\u009c\u00a9\u0010~\u008f\u00e2\u0092o\u00d2^2\u00cb\u00e3V)0\u00fa\u00d2f\u0010\u00d6x\u008e\u0000o\u00ee\u00bb\u00bdv(@7\u009dk\u00ccH\u0010\u00b7\u0006\u00ac\u00bd\u009c\u00d3Baoim\u00e0\u00a8\u00ca3\u00bf\u0010\u00ca\u00f6x\f\u00f43R\u00a3t8\u009aX\u00ff\u0090\u00e1\u007f $(\u00ae>\u0083H\u00a8\u00d1\u00a9z\u00e1\u00ae)\u008d\u0086\u00a6\u00a3g\u00ba/G\u00b0\u00b7n\u0004\t\u00ff\u00bc\u00cb8\u00e7N \u0006\u009b4\u00c6\u00ad6R\u00efU\u001d\u0090\u00d2\u00e7\u00de\u0083\f\u00b2]\u00ecRX\\\u00f6 \u00b4\u0002\u00a7\u00c5\u00b8%O\u0098\u0010\u00a8\u00cb\u00b0\u00afC\\\u008bP\u00b9q\u00e30\u00a3\u00b8\u0096>";
                        var17_6 = 300;
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
                            var18_3[var16_4++] = Waypoint.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a2\u0099Nf\u001d\u00be\u00a8)A\u00e8\u001f\u0092\u00a5\u0005\u001ey\u00ea\u00cc\u00ea\u00ea\u00f2\u00892/\u00edi:\u00bcHK\u00d4\u00ad\u0010\u000e\u00b1\u0016\u0015zC\u0093\u009ew\u00d9\u00d9\u0019\u0093q\u00ad\u0091";
                            var17_6 = 49;
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
                            var18_3[var16_4++] = Waypoint.zkmDecodeUtf8(var19_9).intern();
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
                Waypoint.c = var18_3;
                Waypoint.d = new String[15];
                Waypoint.h = new HashMap<K, V>(13);
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
                var6_12 = new long[13];
                var3_13 = 0;
                var4_14 = "Zd\u00d6\u00e0w\u00c6\u00e9\u0085\u0081\u00c1\u00a5\u00b12]G\u00ee;\u0015\u00d9b\u00cc\u00f8C;\u0097\u0015\u001cQ\u00e9\u00134\u00a3\u0090\u008f\u0099\u00a8h\u00dfz\u008e\b\u00f1\u0012\u0083\u0007\u009a\u0091\u001eC1\u0082\u00f8\u00d0nr\u00c0\u00ae\u0085h\u009dp\u0003k\u0081\u009f~\u00d3\u00fb\u00e5{\u0087\u0015\u00e8\u00fc`\u0080\"\u00c24D+m\u00a0\u009c\u0092_\u0083\u00fc";
                var5_15 = 88;
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
                    var4_14 = "\u00d3\u00f7\u00b4V\u00f0\u00a6\u0019\u00cdC\u00965\u0097d*#]";
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
        Waypoint.f = var6_12;
        Waypoint.g = new Integer[13];
        Waypoint.Companion = new Waypoint$Companion(null);
    }

    public static void zkmSetFlag(String string) {
        U = string;
    }

    public static String zkmGetFlag() {
        return U;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x16B5;
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
                throw new RuntimeException("su/catlean/s4", exception);
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
            Waypoint.d[n2] = Waypoint.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Waypoint.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/s4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x411D;
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
                throw new RuntimeException("su/catlean/s4", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Waypoint.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Waypoint.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/s4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Waypoint.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Waypoint.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

