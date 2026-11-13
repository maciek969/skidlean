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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.command.BindCommand;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.util.Globals;
import su.catlean.util.ServerInfo;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class TimedPosition {
    private long time;
    private int attempts;
    @NotNull
    private Vec3d pos;
    private boolean place;
    private double k;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map h;
    private static final long i;

    public TimedPosition(long l, int n, @NotNull Vec3d vec3d, boolean bl) {
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        this.time = l;
        this.attempts = n;
        this.pos = vec3d;
        this.place = bl;
        this.k = Globals.getPlayer().squaredDistanceTo(this.pos);
    }

    public final long getTime() {
        return this.time;
    }

    public final void setTime(long l) {
        this.time = l;
    }

    @NotNull
    public final Vec3d getPos() {
        return this.pos;
    }

    public final void setPos(@NotNull Vec3d vec3d) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)TimedPosition.zkmBootstrap("j", (int)12603, (long)(0x69ADAB342E5F5CC3L ^ l))));
        this.pos = vec3d;
    }

    public final boolean isPlace() {
        return this.place;
    }

    public final void setPlace(boolean bl) {
        this.place = bl;
    }

    public final double K() {
        return this.k;
    }

    public final void e(double d) {
        this.k = d;
    }

    public final boolean hasMoved(int n, short s, int n2) {
        long l = ((long)n << 32 | (long)s << 48 >>> 32 | (long)n2 << 48 >>> 48) ^ a;
        long l2 = l ^ 0x31172FB658DAL;
        Module[] moduleArray = new Module[]{};
        return Math.abs(this.k - Globals.getPlayer().squaredDistanceTo(this.pos)) >= (double)(this.hasFailed(l2) ? 1.0f : 10.0f) || Globals.now() - this.time > i;
    }

    public final void incrementAttempts(int n, long l) {
        long l2 = ((long)n << 32 | l << 32 >>> 32) ^ a;
        long l3 = l2 ^ 0x1C0252FAAEC3L;
        int n2 = this.attempts;
        Module[] moduleArray = new Module[]{};
        this.attempts = n2 + 1;
        if (AutoCrystal.INSTANCE.NG() && this.hasFailed(l3)) {
            BindCommand.INSTANCE.sendMessage(Formatting.RED + "[Auto Crystal] Failed to " + (this.place ? "place" : "break") + ", change your config!");
        }
    }

    public final boolean hasFailed(long l) {
        long l2 = (l = a ^ l) ^ 0x44BA4ABC3FE3L;
        long l3 = l2 >>> 16;
        int n = (int)(l2 << 48 >>> 48);
        Module[] moduleArray = new Module[]{};
        return this.attempts >= Math.max(5, (int)Math.ceil((float)ServerInfo.getPing$default(ServerInfo.INSTANCE, null, l3, 1, (char)n, null) / 25.0f));
    }

    public final long getTime2() {
        return this.time;
    }

    private final int getAttempts() {
        return this.attempts;
    }

    @NotNull
    public final Vec3d getPos2() {
        return this.pos;
    }

    public final boolean isPlace2() {
        return this.place;
    }

    @NotNull
    public final TimedPosition copy(long l, int n, @NotNull Vec3d vec3d, boolean bl) {
        int n2 = 0;
        long l2 = 0L;
        long l3 = ((long)n2 << 32 | l2 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)TimedPosition.zkmBootstrap("j", (int)26575, (long)(0x3E8B4B97538E6D83L ^ l3))));
        return new TimedPosition(l, n, vec3d, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ TimedPosition copy$default(TimedPosition timedPosition, long l, int n, Vec3d vec3d, boolean bl, long l2, int n2, Object object) {
        int n3;
        block12: {
            int n4;
            block11: {
                void var8_7;
                long l3;
                l3 = a ^ l3;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-1855676848590017987L, (long)l3);
                n4 = var8_7 & 1;
                if (callSite != null) {
                    if (n4 != 0) {
                        l = timedPosition.time;
                    }
                    n4 = var8_7 & 2;
                }
                CallSite callSite2 = callSite;
                if (l3 > 0L) {
                    if (callSite2 != null) {
                        if (n4 != 0) {
                            n = timedPosition.attempts;
                        }
                        n4 = var8_7 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l3 >= 0L) {
                    if (callSite2 != null) {
                        if (n4 != 0) {
                            vec3d = timedPosition.pos;
                        }
                        n4 = var8_7 & TimedPosition.zkmBootstrap2("t", (int)31889, (long)(0x1297D9E945D934BCL ^ l3));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == null) break block11;
                if (n4 == 0) break block12;
                n4 = timedPosition.place ? 1 : 0;
            }
            n3 = n4;
        }
        return timedPosition.copy(l, n, vec3d, n3 != 0);
    }

    @NotNull
    public String toString() {
        boolean bl = this.place;
        Vec3d vec3d = this.pos;
        int n = this.attempts;
        long l = this.time;
        return "Attempt(time=" + l + ", attempts=" + n + ", pos=" + vec3d + ", place=" + bl + ")";
    }

    public int hashCode() {
        int n = Long.hashCode(this.time);
        n = n * 31 + Integer.hashCode(this.attempts);
        n = n * 31 + this.pos.hashCode();
        n = n * 31 + Boolean.hashCode(this.place);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        Module[] moduleArray = new Module[]{};
        if (this == object) {
            return true;
        }
        if (!(object instanceof TimedPosition)) {
            return false;
        }
        TimedPosition timedPosition = (TimedPosition)object;
        if (this.time != timedPosition.time) {
            return false;
        }
        if (this.attempts != timedPosition.attempts) {
            return false;
        }
        if (!Intrinsics.areEqual(this.pos, timedPosition.pos)) {
            return false;
        }
        return this.place == timedPosition.place;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block19: {
            block18: {
                block17: {
                    block16: {
                        block15: {
                            TimedPosition.a = ZkmKeys.a(7108756093035470062L, -3097520588444956872L, MethodHandles.lookup().lookupClass()).a(214777698122585L);
                            TimedPosition.d = new HashMap<K, V>(13);
                            var16 = TimedPosition.a ^ 69379942212268L;
                            var18_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var16 >>> 56);
                            for (var19_2 = 1; var19_2 < 8; ++var19_2) {
                                v2 = v2;
                                v2[var19_2] = (byte)(var16 << var19_2 * 8 >>> 56);
                            }
                            var18_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var25_3 = new String[11];
                            var23_4 = 0;
                            var22_5 = ".\u00f7^2d\u00d5Ey\u00a82\u001a\u0011\u0093\u00a6\u00f34\u00109\u00afM\u000eW\u008e\u00df\u00cd\u0001\u00e9\u0019j\u00c0\u0085\u001fc\u0010\u0017G\u0083f\b\u0003f\u0095Z\u00acY\u00b9\u00f9\u00fa\u008f\u00d2\u0010\u00e7;%\u00bd\f\u00dd\u00c4\u00c5\u0085\u00ee0\u00edW\u00f2\u00d1W\u0010\u00f01\u00e3]D\u00aap9\u00f3\u0099\u00f6\u0016\u00bf)\u00ef\u00dc\u00184\u00e3.\u00ba\u0083\u00a3\u009c\u00dc\u0089\u00b3\u00db\u00cb\u00b8\u00f5\u00c04\u009c\u00c5\u00a0^\u0081\u009f\u00af7\u0010\u00f3]`*\u00e5\u00c9a\u00fe\u0091\u0098p\u0091\u008c\u00c5\u00bfH8\u00be\nq;\u00cbQ\u0017\u009e\u0015\u00cc\u00f6\u00e5\u00a3\u00d8}_\u00ac9\u00e4\u00c0\u00dc\u00e9\u008b\u00a1\u00f5b\u00f4f\u008eu\u0093\u0098O\u0010\u00bb\u00c5\u0006\u00b7\u00c0\u00f38\u008f\u0092!\u00e48\u00bf[\u0090\u0018\u0094|a\r\\\u00bb\u0018\u008c\u00c9\u0010\u00cd\u00b2\u00fa\u0007\u00ecX*\u00ae\\\u00e3\n\u001b\u0012\u00fa\u00afPv\u0092\u00b5\u00a5*";
                            var24_6 = 208;
                            var21_7 = 16;
                            var20_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var20_8;
                                v4 = var22_5.substring(v3, v3 + var21_7);
                                v5 = -1;
                                break block15;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = TimedPosition.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                var22_5 = "\u00fd\u00a0\u00da\u0000\u00f1\u00fa\u00a8\u00a6\u001a\u0085\u00f3\u00ccN\u00fd\u0091\u00fbJ\u00c0\u0094v\u00dec|Y\u0014\u00b3\u0019\u009b'P\u0087\u0088\u00ac1\u009e\u0012,\u00ed\u00bf\u00a8\u00ff\u00b2\u001f\u00c0c\u00a7\u0013\n \u0096\u00b5\u00ef\u00d8\u00b3\u00e6b+tS\u00ea\u0013#\u00ec\u00fe\u00a3{\u00ce\u00fb\u00f8n\u001bI\u00bf\u00a7\u00c5\u001ah\u001e@\u00de\u001a";
                                var24_6 = 81;
                                var21_7 = 48;
                                var20_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var20_8;
                                    v4 = var22_5.substring(v6, v6 + var21_7);
                                    v5 = 0;
                                    break block15;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var25_3[var23_4++] = TimedPosition.zkmDecodeUtf8(var26_9).intern();
                                if ((var20_8 += var21_7) < var24_6) {
                                    var21_7 = var22_5.charAt(var20_8);
                                    ** continue;
                                }
                                break block16;
                                break;
                            }
                        }
                        var26_9 = var18_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    TimedPosition.b = var25_3;
                    TimedPosition.c = new String[11];
                    TimedPosition.h = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var16 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var16 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[3];
                    var8_13 = 0;
                    var9_14 = "\u001a#7\u00d9\u0091E\u000b\u00fe\u008d%~\u0002\u00d6\u00fe.\u00d0\u0082\u00e4\u008c\u007fF\u00ff=A";
                    var10_15 = 24;
                    var7_16 = 0;
                    while (true) {
                        break block17;
                        break;
                    }
lbl73:
                    // 1 sources

                    while (true) {
                        var11_12[v10] = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                        if (var7_16 < var10_15) ** continue;
                        break block18;
                        break;
                    }
                }
                var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                v10 = var8_13++;
                var13_18 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                ** while (true)
            }
            TimedPosition.e = var11_12;
            TimedPosition.f = new Integer[3];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v11 = SecretKeyFactory.getInstance("DES");
            v12 = new byte[8];
            v13 = v12;
            v12[0] = (byte)(var16 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v13 = v13;
                v13[var1_21] = (byte)(var16 << var1_21 * 8 >>> 56);
            }
            break block19;
lbl96:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v11.generateSecret(new DESKeySpec(v13)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{42, -64, -106, -27, -20, 18, 65, -59});
        ** while (true)
        TimedPosition.i = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1DCF;
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
                throw new RuntimeException("su/catlean/je", exception);
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
            TimedPosition.c[n2] = TimedPosition.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TimedPosition.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/je" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2F24;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
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
                throw new RuntimeException("su/catlean/je", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TimedPosition.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TimedPosition.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/je" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TimedPosition.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TimedPosition.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

