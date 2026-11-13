/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.ModuleConfig;
import su.catlean.macro.ChatMacro$$serializer;
import su.catlean.macro.ChatMacro$Companion;
import su.catlean.macro.Macro;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class ChatMacro
implements Macro {
    @NotNull
    public static final ChatMacro$Companion Companion;
    @NotNull
    private String id;
    @NotNull
    private Bind bind;
    private boolean enabled;
    @NotNull
    private String message;
    private static String[] x;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public ChatMacro(@NotNull String string, @NotNull Bind bind, boolean bl, @NotNull String string2) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ChatMacro.zkmBootstrap("n", (int)488, (long)(0x93A0FA80F8079C9L ^ l))));
        Intrinsics.checkNotNullParameter(bind, (String)((Object)ChatMacro.zkmBootstrap("n", (int)1949, (long)(0x2BCD43E35BB97FB7L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)ChatMacro.zkmBootstrap("n", (int)18597, (long)(0x5D18083A67FFB083L ^ l))));
        this.id = string;
        this.bind = bind;
        this.enabled = bl;
        this.message = string2;
    }

    @Override
    @NotNull
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.id = string;
    }

    @Override
    @NotNull
    public Bind getBind() {
        return this.bind;
    }

    @Override
    public void setBind(@NotNull Bind bind) {
        Intrinsics.checkNotNullParameter(bind, "<set-?>");
        this.bind = bind;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean bl) {
        this.enabled = bl;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public final void setMessage(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.message = string;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final Bind getBind2() {
        return this.bind;
    }

    public final boolean isEnabled2() {
        return this.enabled;
    }

    @NotNull
    public final String getMessage2() {
        return this.message;
    }

    @NotNull
    public final ChatMacro copy(@NotNull String string, @NotNull Bind bind, boolean bl, @NotNull String string2) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ChatMacro.zkmBootstrap("n", (int)32653, (long)(0x4C48E207EBF479C7L ^ l))));
        Intrinsics.checkNotNullParameter(bind, (String)((Object)ChatMacro.zkmBootstrap("n", (int)16470, (long)(0x2E4ACEEAEE3BC610L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)ChatMacro.zkmBootstrap("n", (int)4881, (long)(0x546FDD9B5DF2955AL ^ l))));
        return new ChatMacro(string, bind, bl, string2);
    }

    public static ChatMacro copy$default(ChatMacro chatMacro, String string, Bind bind, long l, boolean bl, String string2, int n, Object object) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)824357180414766507L, (long)l);
        int n2 = n & 1;
        if (callSite == false) {
            if (n2 != 0) {
                string = chatMacro.id;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l >= 0L) {
            if (callSite2 == false) {
                if (n2 != 0) {
                    bind = chatMacro.bind;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (callSite2 == false) {
            if (n2 != 0) {
                bl = chatMacro.enabled;
            }
            n2 = n & ChatMacro.zkmBootstrap2("s", (int)8825, (long)(0x104DB18C32D809F6L ^ l));
        }
        if (n2 != 0) {
            string2 = chatMacro.message;
        }
        return chatMacro.copy(string, bind, bl, string2);
    }

    @NotNull
    public String toString() {
        String string = this.message;
        boolean bl = this.enabled;
        Bind bind = this.bind;
        String string2 = this.id;
        return "ChatMacro(id=" + string2 + ", bind=" + bind + ", enabled=" + bl + ", message=" + string + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * 31 + this.bind.hashCode();
        n = n * 31 + Boolean.hashCode(this.enabled);
        n = n * 31 + this.message.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        boolean bl = ModuleConfig.j();
        Object object2 = this;
        if (bl) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (bl) {
            if (!(object2 instanceof ChatMacro)) {
                return false;
            }
            object2 = object;
        }
        ChatMacro chatMacro = (ChatMacro)object2;
        boolean bl2 = Intrinsics.areEqual(this.id, chatMacro.id);
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.bind, chatMacro.bind);
        }
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = this.enabled;
        }
        if (bl) {
            if (bl2 != chatMacro.enabled) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.message, chatMacro.message);
        }
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = true;
        }
        return bl2;
    }

    @JvmStatic
    public static final void write$Self(ChatMacro chatMacro, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, chatMacro.getId());
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, BindSerializer.INSTANCE, chatMacro.getBind());
        compositeEncoder.encodeBooleanElement(serialDescriptor, 2, chatMacro.isEnabled());
        compositeEncoder.encodeStringElement(serialDescriptor, 3, chatMacro.message);
    }

    public ChatMacro(int n, String string, Bind bind, boolean bl, String string2, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (0xF & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 15, ChatMacro$$serializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.bind = bind;
        this.enabled = bl;
        this.message = string2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ChatMacro.a = ZkmKeys.a(-2978877010743915273L, -6167606304803040657L, MethodHandles.lookup().lookupClass()).a(154075658118742L);
                        var20 = ChatMacro.a ^ 80011171236132L;
                        ChatMacro.d = new HashMap<K, V>(13);
                        ChatMacro.zkmSetFlag(null);
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
                        var18_3 = new String[12];
                        var16_4 = 0;
                        var15_5 = "sB<\u00e9\u00de\u00ab\u00bb\u00deMlZ[K'\u00c8\u00ee\u0010w\u0000\u0097\u00bd!\u00a8G\u00c9o\u0004?@\u00d5@[4 \u009f\u00b5?Y\u0094+{Uk\u00ae\u0017\u00a5\u009e-\u00b2\u00c9b\u001b\u0014\u00e0\u00bc:Bp\u00c4\u00b4\u00dbCCMf\u00ec\u00188\u00d3\u00ba\u0082\u0013\u00f0\u00aa\u009b\u00c7\u0093Rrq6\u00b6\u00d9'\u009d\u00f0\u0085\u00df;\u00cc\u00d0\u0010\u00e9G\u000e\u001b \u00b5\u0013\u00be\u008cB\u00ac\u00a9\u00e6qI^\u0010o5\u00c4h<\f\u0016\u00d1X\u0084uk\u00f2\u00f3\t.\u0010\u00e0\f\u00ac\u00ce\u0098\\\u00b2\u00f9'\u00c7$\u0007@\u009ft\u00e7\u0010\u00d9\u0082\u0092Dt\u00af\u008f>\u00ca1+h\u00cb\u00c7\u00das\u0010\u000b\u0091\u000f\u00c8\u0082:\u009a/\u00c2\u00c8\u00b4\u00e7\u00c7\u0083\u00b0\u001a\u0010\u0099\"\u00a2<\u00a7]\u00c2MD\u00c4'\u009a0\u00ff-3";
                        var17_6 = 193;
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
                            var18_3[var16_4++] = ChatMacro.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b6\u00bel\u00efL\u0086P|\u00bb\u00b01\u0093\u008e\u00f7\u0084\u00a8J\u00a8v\u0097\u00a5\u00a1\u00f7(\u00d1m\u001a59z\u00b0z\u0010\u00f9!\u00dds\u001c\u0081*6b\u00ef\u00b40\u008a{\u008c\u00d6";
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
                            var18_3[var16_4++] = ChatMacro.zkmDecodeUtf8(var19_9).intern();
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
                ChatMacro.b = var18_3;
                ChatMacro.c = new String[12];
                ChatMacro.h = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u00e3\u00ee\u0093v0^W\u00ccQ=u\u00c3\u00b2\u00e4\u007f\u00bf\u00ed\u00eeGEG\u00db@^";
                var5_15 = 24;
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
                    var4_14 = "\u001dH\u00e1\u0088\u00dd\u008c\u00fc\u00d0Cp\u00b3\u00deb-\u008d{";
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
        ChatMacro.f = var6_12;
        ChatMacro.g = new Integer[5];
        ChatMacro.Companion = new ChatMacro$Companion(null);
    }

    public static void zkmSetFlag(String[] stringArray) {
        x = stringArray;
    }

    public static String[] zkmGetFlag() {
        return x;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x58D4;
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
                throw new RuntimeException("su/catlean/bp", exception);
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
            ChatMacro.c[n2] = ChatMacro.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ChatMacro.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/bp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x219E;
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
                throw new RuntimeException("su/catlean/bp", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ChatMacro.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ChatMacro.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/bp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ChatMacro.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ChatMacro.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

