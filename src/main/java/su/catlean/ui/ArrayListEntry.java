/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui;

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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.util.Animation;
import su.catlean.util.Easing;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ArrayListEntry {
    @NotNull
    private final String name;
    @NotNull
    private final String bind;
    @NotNull
    private final String extra;
    @NotNull
    private final Animation animation;
    private boolean removing;
    private static boolean T;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;
    private static final long h;

    public ArrayListEntry(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull Animation animation, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "name");
        Intrinsics.checkNotNullParameter(string2, "bind");
        Intrinsics.checkNotNullParameter(string3, "extra");
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.name = string;
        this.bind = string2;
        this.extra = string3;
        this.animation = animation;
        this.removing = bl;
    }

    public /* synthetic */ ArrayListEntry(String string, String string2, String string3, Animation animation, boolean bl, int n, DefaultConstructorMarker defaultConstructorMarker) {
        long l = 0L;
        int n2 = 0;
        long l2 = (l << 8 | (long)n2 << 56 >>> 56) ^ a;
        if ((n & 4) != 0) {
            string3 = "";
        }
        if ((n & ArrayListEntry.zkmBootstrap2("j", (int)26501, (long)(0x3C08403383D6E979L ^ l2))) != 0) {
            animation = new Animation(Easing.OUT_QUINT, h);
        }
        if ((n & ArrayListEntry.zkmBootstrap2("j", (int)1702, (long)(0x4AE276B44782885BL ^ l2))) != 0) {
            bl = false;
        }
        this(string, string2, string3, animation, bl);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getBind() {
        return this.bind;
    }

    @NotNull
    public final String getExtra() {
        return this.extra;
    }

    @NotNull
    public final Animation getAnimation() {
        return this.animation;
    }

    public final boolean isRemoving() {
        return this.removing;
    }

    public final void setRemoving(boolean bl) {
        this.removing = bl;
    }

    @NotNull
    public final String getName2() {
        return this.name;
    }

    @NotNull
    public final String getBind2() {
        return this.bind;
    }

    @NotNull
    public final String getExtra2() {
        return this.extra;
    }

    @NotNull
    public final Animation getAnimation2() {
        return this.animation;
    }

    public final boolean isRemoving2() {
        return this.removing;
    }

    @NotNull
    public final ArrayListEntry copy(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull Animation animation, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ArrayListEntry.zkmBootstrap("c", (int)19101, (long)(0x4D68360C66B6AF38L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)ArrayListEntry.zkmBootstrap("c", (int)8524, (long)(0x256E325AB99AC4E2L ^ l))));
        Intrinsics.checkNotNullParameter(string3, (String)((Object)ArrayListEntry.zkmBootstrap("c", (int)30166, (long)(0x1E182167C4B81076L ^ l))));
        Intrinsics.checkNotNullParameter(animation, (String)((Object)ArrayListEntry.zkmBootstrap("c", (int)19021, (long)(0x7E0419CA7A1B2FE1L ^ l))));
        return new ArrayListEntry(string, string2, string3, animation, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static /* synthetic */ ArrayListEntry copy$default(ArrayListEntry arrayListEntry, long l, String string, String string2, String string3, Animation animation, boolean bl, int n, Object object) {
        boolean bl2;
        block15: {
            boolean bl3;
            block14: {
                void var8_8;
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)6117477582567038870L, (long)l);
                bl3 = var8_8 & 1;
                if (callSite != false) {
                    if (bl3) {
                        string = arrayListEntry.name;
                    }
                    bl3 = var8_8 & 2;
                }
                CallSite callSite2 = callSite;
                if (l > 0L) {
                    if (callSite2 != false) {
                        if (bl3) {
                            string2 = arrayListEntry.bind;
                        }
                        bl3 = var8_8 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 != false) {
                        if (bl3) {
                            string3 = arrayListEntry.extra;
                        }
                        bl3 = var8_8 & ArrayListEntry.zkmBootstrap2("j", (int)10832, (long)(0x7BEED771AFC928F4L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l >= 0L) {
                    if (callSite2 != false) {
                        if (bl3) {
                            animation = arrayListEntry.animation;
                        }
                        bl3 = var8_8 & ArrayListEntry.zkmBootstrap2("j", (int)11175, (long)(0x288BED68D7DAA904L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == false) break block14;
                if (!bl3) break block15;
                bl3 = arrayListEntry.removing;
            }
            bl2 = bl3;
        }
        return arrayListEntry.copy(string, string2, string3, animation, bl2);
    }

    @NotNull
    public String toString() {
        boolean bl = this.removing;
        Animation animation = this.animation;
        String string = this.extra;
        String string2 = this.bind;
        String string3 = this.name;
        return "KeyBind(name=" + string3 + ", bind=" + string2 + ", extra=" + string + ", animation=" + animation + ", removing=" + bl + ")";
    }

    public int hashCode() {
        int n = this.name.hashCode();
        n = n * 31 + this.bind.hashCode();
        n = n * 31 + this.extra.hashCode();
        n = n * 31 + this.animation.hashCode();
        n = n * 31 + Boolean.hashCode(this.removing);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ArrayListEntry)) {
            return false;
        }
        ArrayListEntry arrayListEntry = (ArrayListEntry)object;
        if (!Intrinsics.areEqual(this.name, arrayListEntry.name)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.bind, arrayListEntry.bind)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.extra, arrayListEntry.extra)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.animation, arrayListEntry.animation)) {
            return false;
        }
        return this.removing == arrayListEntry.removing;
    }

    public static void zkmSetFlag(boolean bl) {
        T = bl;
    }

    public static boolean zkmGetFlag() {
        return T;
    }

    public static boolean u() {
        return false;
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
                            ArrayListEntry.a = ZkmKeys.a(-3260252303748805447L, 557024287957997026L, MethodHandles.lookup().lookupClass()).a(35173398691507L);
                            var25 = ArrayListEntry.a ^ 89947385050920L;
                            ArrayListEntry.d = new HashMap<K, V>(13);
                            ArrayListEntry.zkmSetFlag(true);
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
                            var20_5 = "@\u00c6\u0084R<z\u00d9\u00ea\u0097\u00c1\u00d7\u009e\u001ay\u00bbX\u0018\u0088\u0019\u00e2 \u00a1\u00ab\u00d4\u00b4\u008d\u0081E_\u00fe\u00b1\u00bf>i\u00fb\u00f8\u00b2\u00e9\u00a3$\u00b0\u0010\u00a6\u00f5\u008a\u00be\u00c6\u00d8o%\u00ae\u001c\u0011\u00ef?k9\u00a3\u0018\u00952E\u00b1\u008bs\u00a7\u00fc\u0097mr\f_\u0001\u00ec\u00f2\u00b5\u00b5Z\u00f1_\u000e\u00c4\u00fe\u0018\u00b9\u00da\u00ae&\u00b4m\u008d\u001c\u00b4x\u00beF\u00cc\u00ac`&h\u00be\u009c\u0090\u00f4A\u00cd\u00dc 10\u00b4P4\u001e\u00fbG\u0084N\u00ff\u00b7\u00e6\u0018\u00fd\u00d7=\u00f7Y\u00abr?(\"\u00c0[\u001b\u0096\u00ca\u008a\u009a\u009b \u00fe7`\u00bd\b(\u00baI\u00e8\u00a6\u00aaC/\u00ea\u00fdQ\u00d3W,\u00d6\u0091c\u00f6\u00b2\u00fd\u008c\u0084v\u00b7\u00c4\u00df\u00f4\u0010\u00b4\u00da\u00d0\u0095\u0017\u00ae5\u00fd:\u00f3^\u0099\u00e7\u00af\u00ec\u00f8\u0010\u007f\u00d0\u0019\u00ff\u0005W\r\u00ceA\u00d8D\u0000\u00ces\u00bc\u00b5\u0010M\u00ff\u0098p\u00eb\u00f1\bWl%n\u00a4\u00ba%\b\u00a9\u00108\u00bai\u00bb\u00e7\u009f\u009ah\u00f0\u00e3hh\u00fe.\u008c\u00ae";
                            var22_6 = 242;
                            var19_7 = 16;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = ArrayListEntry.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u000f\u0092\u0015@9~\u009e\u00ee\u00e6\u00a3\u00a2\u00d1\u00c2\u00ef\u00bf\u0087\u00b1\u009acdk\u00be\u0016\u00bd\r\u0019kza\u00f2$F\u0010|H=\"\u00d8\u00e8 \u00dc\u00d5\u00c4P;&0\u0085i";
                                var22_6 = 49;
                                var19_7 = 32;
                                var18_8 = -1;
lbl35:
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
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = ArrayListEntry.zkmDecodeUtf8(var24_9).intern();
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
lbl52:
                            // 1 sources

                            ** continue;
                        }
                    }
                    ArrayListEntry.b = var23_3;
                    ArrayListEntry.c = new String[13];
                    ArrayListEntry.g = new HashMap<K, V>(13);
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
                    var11_12 = new long[6];
                    var8_13 = 0;
                    var9_14 = "\u00c2\u000fJ\u001e\u0000)/f\u0005\u0000\u00cd\u0000\u009e\u00f8\u00b51\u00d8\u00fcx^\u0019,2\u00b6\u001e\u00d6p@\u00c3#\\`";
                    var10_15 = 32;
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
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u00adE\u00f2fJ\u00e0\u009ay\u00aa8J\u0093\u00b7\u00a0Q\u0013";
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
lbl92:
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
lbl105:
                    // 1 sources

                    ** continue;
                }
            }
            ArrayListEntry.e = var11_12;
            ArrayListEntry.f = new Integer[6];
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
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{34, 119, 95, -58, 34, -29, -32, -32});
        ** while (true)
        ArrayListEntry.h = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7FDE;
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
                throw new RuntimeException("su/catlean/ww", exception);
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
            ArrayListEntry.c[n2] = ArrayListEntry.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ArrayListEntry.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ww" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x57F3;
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
                throw new RuntimeException("su/catlean/ww", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ArrayListEntry.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ArrayListEntry.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ww" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ArrayListEntry.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ArrayListEntry.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

