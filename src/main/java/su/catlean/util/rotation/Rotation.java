/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.mixins.accessors.LocalPlayerAccessor;
import su.catlean.util.Globals;
import su.catlean.util.rotation.MoveFix;
import su.catlean.util.rotation.Rotation$Companion;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class Rotation {
    @NotNull
    public static final Rotation$Companion Companion;
    private float yaw;
    private float pitch;
    private boolean grim;
    @NotNull
    private MoveFix moveFix;
    private static boolean w;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public Rotation(float f, float f2, boolean bl, @NotNull MoveFix moveFix) {
        Intrinsics.checkNotNullParameter((Object)moveFix, "moveFix");
        this.yaw = f;
        this.pitch = f2;
        this.grim = bl;
        this.moveFix = moveFix;
    }

    public Rotation(float f, float f2, boolean bl, MoveFix moveFix, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 4) != 0) {
            bl = false;
        }
        if ((n & 8) != 0) {
            moveFix = MoveFix.NONE;
        }
        this(f, f2, bl, moveFix);
    }

    public final float getYaw() {
        return this.yaw;
    }

    public final void setYaw(float f) {
        this.yaw = f;
    }

    public final float getPitch() {
        return this.pitch;
    }

    public final void setPitch(float f) {
        this.pitch = f;
    }

    public final boolean isGrim() {
        return this.grim;
    }

    public final void setGrim(boolean bl) {
        this.grim = bl;
    }

    @NotNull
    public final MoveFix getMoveFix() {
        return this.moveFix;
    }

    public final void setMoveFix(@NotNull MoveFix moveFix) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter((Object)moveFix, (String)((Object)Rotation.zkmBootstrap("b", (int)6168, (long)(0x7A640010BA1D5804L ^ l))));
        this.moveFix = moveFix;
    }

    @NotNull
    public final Rotation fixSensitivity() {
        double d = Math.pow(((Number)Globals.getMc().options.getMouseSensitivity().getValue()).doubleValue() * 0.6 + 0.2, 3.0) * 8.0 * (double)0.15f;
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
        float f = MathHelper.wrapDegrees(this.yaw - ((LocalPlayerAccessor)((Object)clientPlayerEntity)).getLastYaw());
        ClientPlayerEntity clientPlayerEntity2 = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity2, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
        float f2 = MathHelper.wrapDegrees(this.pitch - ((LocalPlayerAccessor)((Object)clientPlayerEntity2)).getLastPitch());
        double d2 = (double)MathKt.roundToInt((double)f / d) * d;
        double d3 = (double)MathKt.roundToInt((double)f2 / d) * d;
        ClientPlayerEntity clientPlayerEntity3 = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity3, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
        this.yaw = ((LocalPlayerAccessor)((Object)clientPlayerEntity3)).getLastYaw() + (float)d2;
        ClientPlayerEntity clientPlayerEntity4 = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity4, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
        this.pitch = RangesKt.coerceIn(((LocalPlayerAccessor)((Object)clientPlayerEntity4)).getLastPitch() + (float)d3, -90.0f, 90.0f);
        return this;
    }

    @NotNull
    public final Rotation withMoveFix(@NotNull MoveFix moveFix) {
        Intrinsics.checkNotNullParameter((Object)moveFix, "v");
        this.moveFix = moveFix;
        return this;
    }

    @NotNull
    public final Rotation withGrim(boolean bl) {
        this.grim = bl;
        return this;
    }

    public final float component1() {
        return this.yaw;
    }

    public final float component2() {
        return this.pitch;
    }

    public final boolean component3() {
        return this.grim;
    }

    @NotNull
    public final MoveFix component4() {
        return this.moveFix;
    }

    @NotNull
    public final Rotation copy(float f, float f2, boolean bl, @NotNull MoveFix moveFix) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter((Object)moveFix, (String)((Object)Rotation.zkmBootstrap("b", (int)18945, (long)(0x357EBC3570C0A064L ^ l2))));
        return new Rotation(f, f2, bl, moveFix);
    }

    public static Rotation copy$default(long l, Rotation rotation, float f, float f2, boolean bl, MoveFix moveFix, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)6861740000058126017L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                f = rotation.yaw;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 == null) {
                if (n2 != 0) {
                    f2 = rotation.pitch;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (callSite2 == null) {
            if (n2 != 0) {
                bl = rotation.grim;
            }
            n2 = n & Rotation.zkmBootstrap2("g", (int)17976, (long)(0x114AF63E262CB0BAL ^ l));
        }
        if (n2 != 0) {
            moveFix = rotation.moveFix;
        }
        return rotation.copy(f, f2, bl, moveFix);
    }

    @NotNull
    public String toString() {
        MoveFix moveFix = this.moveFix;
        boolean bl = this.grim;
        float f = this.pitch;
        float f2 = this.yaw;
        return "Rotation(yaw=" + f2 + ", pitch=" + f + ", grim=" + bl + ", moveFix=" + moveFix + ")";
    }

    public int hashCode() {
        int n = Float.hashCode(this.yaw);
        n = n * 31 + Float.hashCode(this.pitch);
        n = n * 31 + Boolean.hashCode(this.grim);
        n = n * 31 + this.moveFix.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Rotation)) {
            return false;
        }
        Rotation rotation = (Rotation)object;
        if (Float.compare(this.yaw, rotation.yaw) != 0) {
            return false;
        }
        if (Float.compare(this.pitch, rotation.pitch) != 0) {
            return false;
        }
        if (this.grim != rotation.grim) {
            return false;
        }
        return this.moveFix == rotation.moveFix;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Rotation.a = ZkmKeys.a(-2844651197561317818L, 6619009265279355071L, MethodHandles.lookup().lookupClass()).a(77942568895954L);
                        var20 = Rotation.a ^ 28292539799969L;
                        Rotation.d = new HashMap<K, V>(13);
                        Rotation.zkmSetFlag(true);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "\u0098\u00fc\u00b7!\u0019ht\u001a\u00cf\u00df\u00cd\u0019\u00fe~\n\u00a6 \u0016\u00fd\u0097 >:\u0087\u00f8Z\u00a5m\u00bf=rakWW\u00fe\u00b91\u000e\u0090`^\f\u00f8\u00a0\u00a9\u0093\u00f3S\u0088\u00f2kk|jb\u0080\u0014\u009b\u0082\u00b4\u0099\u00e6Q\u0083Bh\u001duL\u00c7\u00c4\u001b1\u0005\\T\u009d\u009cB\u0094\u00be\u001f\u0003\"]\u00f9\u009b\u00ab;PU\u0080\u00c3\u001fl\u008d.3{R,\u008cH+\u00e9\u00a2 \u00e7v\u009fvtt7\u0089\u0016\u00c5\u0005\u00dbP]\u00ec\u009e__\u007f\u00aa4\u00ddg&\u0014\u00e0\u00fa|\u0086nN\u0012B]\u009e\u00b8\u000b\u001c\u0000\u00a0\u0016\u00fe{\u00066\u0095\u00d7\u00b0\u00d1a\u009cp\u0096Q1\u007f\u00e3\u0003\u00eb\u001b\u001e\u00a6E\u0007\\e\u0013\u0001\u0017\u00e4_\u00fd\u0010D}\u001cy\u00bd\u0090Y\u0087\u00a9U\u00f2>\u00fe\u00ad\u00c8\f\\\u00a4\u00ce\u0013\u00a0\u00efB\u0097\u0013C\u00f2^\u00ab\u008e5\u00d8\u00b1\u00b7\u00f7(V\u0081\u00e3A9\u00f7iKvs\u0096\u00b4f\u00e1\u009b[\u00e6\u008c\u009a\\\u00dcS0\u00d1n\u00d93\u00146\u00b7U1\u0018\u0003\u0099\u0014\u001b\u00dc\u0088\b\u00ca\u00da\u007f\u0086\u0017\u0003g\u0095\u00ac\u00aebi\u00dfw\u00ff\u00d8\u00b8\u0016\r\u00e0\n\u008bV\u00b1\u00a1\u009d\u00afZ\u00ae.\u009ejM\u008bJG\u00e4\u00f35\u00aa\u0086/\u001d\u00beH\u00e3\u0004\u00d1\u00e4ek\u00c2\u00b7f\fh'@\u00adsK\f-\n\u00a1\u00a6\u008b\u00f2\u000e\u0089\u00a4'\u008e\u00a3\u0018Z:\u00c6\u00c06<\u0010\t\u00df\u0088j;\u0019\u0089b\u0003\u00c8\u001b\u00d8\u0016\u0000s\u00bc\u00a1\u0010\u008c\u00990\u00f6P\u0089\u009a\u000b\u00ea\u0080v>\u00c7z`;\u0018\u00d1\u0082T\u0098\u0094\u00fc`\u00e1\u00e1\u00db1\u007f\u00aa\n\u00ba\u00e9\u00ca\u001bqZJ\u00e9W\u00dc";
                        var17_6 = 398;
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
                            var18_3[var16_4++] = Rotation.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "^{\u00c3\u0089\u00c6D\u009cQ\u00b6\u008d\u00a6Z5\u00e6\u00c1\u00af\u00106S\u0000\u0017\u00d4\u0012\u00c9x\u001b\u001d\u00d7\u0017\u008c\u00bf\u0095b";
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
                            var18_3[var16_4++] = Rotation.zkmDecodeUtf8(var19_9).intern();
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
                Rotation.b = var18_3;
                Rotation.c = new String[9];
                Rotation.h = new HashMap<K, V>(13);
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
                var4_14 = "\u00064\u00f7;\u00b6Q\u00d7\u001a\u0002b\u007f\u00f9hl\u00ec\u00d7";
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
                    var4_14 = "E\u00e2(\u00d5_\u008b\u0097\u008b\u00a2\u0090\u00a9\u00fc\u0011\u00c1\u00ad\u0087";
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
        Rotation.f = var6_12;
        Rotation.g = new Integer[4];
        Rotation.Companion = new Rotation$Companion(null);
    }

    public static void zkmSetFlag(boolean bl) {
        w = bl;
    }

    public static boolean zkmGetFlag() {
        return w;
    }

    public static boolean O() {
        return false;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x214C;
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
                throw new RuntimeException("su/catlean/_w", exception);
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
            Rotation.c[n2] = Rotation.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Rotation.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_w" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x28F0;
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
                throw new RuntimeException("su/catlean/_w", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Rotation.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Rotation.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_w" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Rotation.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Rotation.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

