/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.account;

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
import kotlin.text.StringsKt;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.account.Account$Companion;
import su.catlean.account.AccountSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class Account {
    @NotNull
    public static final Account$Companion Companion;
    @NotNull
    private String id;
    @NotNull
    private String username;
    @NotNull
    private String avatar;
    private static String[] y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public Account(@NotNull String string, @NotNull String string2, @NotNull String string3) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(string2, "username");
        Intrinsics.checkNotNullParameter(string3, "avatar");
        this.id = string;
        this.username = string2;
        this.avatar = string3;
    }

    public Account(String string, String string2, String string3, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            string = "none";
        }
        if ((n & 2) != 0) {
            string2 = "none";
        }
        if ((n & 4) != 0) {
            string3 = "none";
        }
        this(string, string2, string3);
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    public final void setId(@NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Account.zkmBootstrap("h", (int)20781, (long)(0x42E8584C874D886FL ^ l))));
        this.id = string;
    }

    @NotNull
    public final String getUsername() {
        return this.username;
    }

    public final void setUsername(@NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Account.zkmBootstrap("h", (int)27312, (long)(0x27A17EB200258FD3L ^ l))));
        this.username = string;
    }

    @NotNull
    public final String getAvatar() {
        return this.avatar;
    }

    public final void setAvatar(@NotNull String string) {
        long l = 0L;
        int n = 0;
        long l2 = (l << 8 | (long)n << 56 >>> 56) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Account.zkmBootstrap("h", (int)27312, (long)(0x27A1464C974C4751L ^ l2))));
        this.avatar = string;
    }

    @NotNull
    public final String getAvatarUrl(int n, boolean bl) {
        String string;
        if (StringsKt.startsWith$default(this.avatar, "a_", false, 2, null) && !bl) {
            int n2 = n;
            String string2 = this.avatar;
            String string3 = this.id;
            string = "https://cdn.discordapp.com/avatars/" + string3 + "/" + string2 + ".gif?size=" + n2;
        } else {
            int n3 = n;
            String string4 = this.avatar;
            String string5 = this.id;
            string = "https://cdn.discordapp.com/avatars/" + string5 + "/" + string4 + ".png?size=" + n3;
        }
        return string;
    }

    public static String getAvatarUrl$default(Account account, boolean bl, Object object) {
        int n = 128;
        return account.getAvatarUrl(n, bl);
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final String getUsername2() {
        return this.username;
    }

    @NotNull
    public final String getAvatar2() {
        return this.avatar;
    }

    @NotNull
    public final Account copy(@NotNull String string, @NotNull String string2, @NotNull String string3) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)Account.zkmBootstrap("h", (int)26991, (long)(0x728BB3E9BEA4E925L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)Account.zkmBootstrap("h", (int)19716, (long)(0x92B306C483ECD4FL ^ l))));
        Intrinsics.checkNotNullParameter(string3, (String)((Object)Account.zkmBootstrap("h", (int)22999, (long)(0x3DE4030C1CC1D98CL ^ l))));
        return new Account(string, string2, string3);
    }

    public static Account copy$default(Account account, String string, String string2, String string3, int n, Object object) {
        long l = 0L;
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-7682463066896974012L, (long)l);
        int n2 = n & 1;
        if (callSite == null) {
            if (n2 != 0) {
                string = account.id;
            }
            n2 = n & 2;
        }
        if (callSite == null) {
            if (n2 != 0) {
                string2 = account.username;
            }
            n2 = n & 4;
        }
        if (n2 != 0) {
            string3 = account.avatar;
        }
        return account.copy(string, string2, string3);
    }

    @NotNull
    public String toString() {
        String string = this.avatar;
        String string2 = this.username;
        String string3 = this.id;
        return "IPCUser(id=" + string3 + ", username=" + string2 + ", avatar=" + string + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * 31 + this.username.hashCode();
        n = n * 31 + this.avatar.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Account)) {
            return false;
        }
        Account account = (Account)object;
        if (!Intrinsics.areEqual(this.id, account.id)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.username, account.username)) {
            return false;
        }
        return Intrinsics.areEqual(this.avatar, account.avatar);
    }

    @JvmStatic
    public static final void write$Self(Account account, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block2: {
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) ? true : !Intrinsics.areEqual(account.id, "none")) {
                compositeEncoder.encodeStringElement(serialDescriptor, 0, account.id);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) ? true : !Intrinsics.areEqual(account.username, "none")) {
                compositeEncoder.encodeStringElement(serialDescriptor, 1, account.username);
            }
            if (!(compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) ? true : !Intrinsics.areEqual(account.avatar, "none"))) break block2;
            compositeEncoder.encodeStringElement(serialDescriptor, 2, account.avatar);
        }
    }

    public Account(int n, String string, String string2, String string3, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & n) != 0) {
            PluginExceptionsKt.throwMissingFieldException(n, 0, AccountSerializer.INSTANCE.getDescriptor());
        }
        this.id = (n & 1) == 0 ? "none" : string;
        this.username = (n & 2) == 0 ? "none" : string2;
        this.avatar = (n & 4) == 0 ? "none" : string3;
    }

    public Account() {
        long l = 0L;
        int n = 0;
        long l2 = (l << 16 | (long)n << 48 >>> 48) ^ a;
        this(null, null, null, (int)Account.zkmBootstrap2("d", (int)27254, (long)(0x7E24E7A2DAF199B0L ^ l2)), null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Account.a = ZkmKeys.a(5140828051802494958L, -3215160988240386815L, MethodHandles.lookup().lookupClass()).a(214153308615503L);
                        var20 = Account.a ^ 61417325037449L;
                        Account.d = new HashMap<K, V>(13);
                        Account.zkmSetFlag(null);
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
                        var18_3 = new String[18];
                        var16_4 = 0;
                        var15_5 = "\u008cT\u00f9\u00ba\u009b\u008a\u00f9]vC\u00d4V\u00b8\u00c7\u00bc^@\u00cc\u0018M\u008eh\u008f\" \u00d4\u0090\u00d1n\u00e0\u009bX\u008d|\u00b5pbNb\u00d3\u00d6*\u00fd]/\u001bz\u00c5\u00d1\"G\u0019\u00af4!\u00e5\u00e7\u00ce\u00ab\u00da\u00f50\f\u001a\u00e8\u00e9\u00e2ii\u00f0\u008d\u008b\u00e0\u00bcX\u00d19\u00cd{x\u00be\u0010w\u0003\u001c.vS\u00c0\u00ed\u00f4\u00a5\u0005\u00ba\u00d3!\u00f8\u00ae\u0018\u001aI\u00f8\f\u00fb\u0019(5\u00b1\n\u00a8\u00e6\u007f\u0015\u008ar!k}>\u0001\u0098@M \u0019\u00e1\u00a4\u008f\u00fdW\u00ee\u009e\"\"3\u001dS30\u00f09\u00e2\u00ef\u00b2\u009e\u00cdP\u0088\u00fa\u00f6\u0000\u0080\u0019\u00d5\u0004\u0000\u0010>\u000bYH\u00ff\u000b\u001e\u00c8\u00b30\u00b4\u0087>\u00ae\u000e\u00db@\u0086PK#\u00e0\u0084\u00c5\u001b\u00bf\u00bej$7\u001a\t@\u00c6\u008d\u00f1,\u0098\u00bc:\u00a9\u00c8jd\u00a7C\u00d4\u0016?\u00ae\u007f\u00f3\u00cc+@A}\u007f\u00e9,\u00c6\u000f\u00fa\u00e3s(9\u008fb\u00f0\u00b7\u00a5\u00b8J\u00ee\u00feG\u00dd\f0\u0017 \u00b9\r/N\u0012\u00d7\u0000\u0012\u009d~\u00c56k\u0004\u00c6>\u00c5\u00fb\u00d4,J\u00d1=%=}\u00b2\u008fKS\u00b5\u00a4\u0010\u00f1>{\u00d9\u007f\u00c5\u00f6G\\\u00f9\u009a\u00ceH>xt \u00ab\u009eE\u0088\u00d06\u0096\b)\u0015_\u0082\u0012\rE\u00a8\u00dc\u00e9\u00ef\u009b\u001bB\u00b9_\u0005\u00ca:\u00e7\u00e8sy2\u0010{\u000eH8\u000e\u00ad\u00ce\u00d1\u0088\u00c7\r\u00ce\u0018T\u00c5\u00e7 Q\u0095\u008f\u00d7\u00ecP\u0094\u00ab\u000e\u0084\u00dc\u00acx*\u008d\u00d1+{\u00a0\u00fcA\u009a\u00a5\u00f3\u009e\t\u00c7\u0019\u00a5\u0002\u00b5\u00d6\u0010\u008c\u008d \n'5\u00b1\u0012\u000e\u0013]\u0006\u00ad\u00b0)\u00f7\u0018x\u00ff\u0089\u00eebt\u00e4G\u00ff\u00d4\u00eb\u00d3\u001a\u0012\u00d4\u0019x&\u00c9<\u00df\u00fe\u00b5\u00e7\u0010\u0005L\u00b5\u000e\u00e7'5\u00cc\u0083e\"D\u00f2\u00d3r \u0010\u00ce\u0005Z\u00f8!\u00e2\u00b8X\u00b3\u000b=B\u00dadj\u0097";
                        var17_6 = 447;
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
                            var18_3[var16_4++] = Account.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0014\u008b\u00e55\u0088\u008b\u00d2\u00a5\u00b9\u00b7\u0018\u0010\u0016\u00a0d\u00a7e\u009f\u001b\u00b2\u00d4\u0097\u0016\u00b5\u00db#\u00bb\u0086q\u00b6E\u00d2\u0010\u0093\u0095\u008ct`p\u00b0\u00a19\u00c7\u00f1\u001c\u0003\u00c4V\u00f9";
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
                            var18_3[var16_4++] = Account.zkmDecodeUtf8(var19_9).intern();
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
                Account.b = var18_3;
                Account.c = new String[18];
                Account.g = new HashMap<K, V>(13);
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
                var4_14 = "\u00fe\u00a3\u0006\u00d1;\u00b1\u00e4\u00d9\u00bf\u009f\u00f4\u009d\u00a7)\u001e\u00e9";
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
                    var4_14 = "\u00f5\u00ab?\u009b\u008d\u001ema\u001a(\u00d6 DHx*";
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
        Account.e = var6_12;
        Account.f = new Integer[4];
        Account.Companion = new Account$Companion(null);
    }

    public static void zkmSetFlag(String[] stringArray) {
        y = stringArray;
    }

    public static String[] zkmGetFlag() {
        return y;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3B32;
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
                throw new RuntimeException("su/catlean/f0", exception);
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
            Account.c[n2] = Account.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Account.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/f0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x257B;
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
                throw new RuntimeException("su/catlean/f0", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Account.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Account.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/f0" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Account.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Account.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

