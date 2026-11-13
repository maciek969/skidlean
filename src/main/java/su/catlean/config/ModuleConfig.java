/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

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
import su.catlean.config.ModuleConfig$Companion;
import su.catlean.config.ModuleConfigSerializer;
import su.catlean.macro.Macro;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class ModuleConfig
implements Macro {
    @NotNull
    public static final ModuleConfig$Companion Companion;
    @NotNull
    private String id;
    @NotNull
    private Bind bind;
    private boolean enabled;
    @NotNull
    private String item;
    private boolean legit;
    private static boolean J;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    public ModuleConfig(@NotNull String string, @NotNull Bind bind, boolean bl, @NotNull String string2, boolean bl2) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(bind, "bind");
        Intrinsics.checkNotNullParameter(string2, "item");
        this.id = string;
        this.bind = bind;
        this.enabled = bl;
        this.item = string2;
        this.legit = bl2;
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
    public final String getItem() {
        return this.item;
    }

    public final void setItem(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.item = string;
    }

    public final boolean isLegit() {
        return this.legit;
    }

    public final void setLegit(boolean bl) {
        this.legit = bl;
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
    public final String getItem2() {
        return this.item;
    }

    public final boolean isLegit2() {
        return this.legit;
    }

    @NotNull
    public final ModuleConfig copy(@NotNull String string, @NotNull Bind bind, boolean bl, @NotNull String string2, boolean bl2) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ModuleConfig.zkmBootstrap("w", (int)10138, (long)(0x302ECA91EE0211ACL ^ l))));
        Intrinsics.checkNotNullParameter(bind, (String)((Object)ModuleConfig.zkmBootstrap("w", (int)28009, (long)(0x167215928F415B58L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)ModuleConfig.zkmBootstrap("w", (int)15942, (long)(0x62A5A7AAB5C00872L ^ l))));
        return new ModuleConfig(string, bind, bl, string2, bl2);
    }

    /*
     * WARNING - void declaration
     */
    public static ModuleConfig copy$default(ModuleConfig moduleConfig, String string, Bind bind, boolean bl, String string2, boolean bl2, int n, Object object, long l) {
        boolean bl3;
        block15: {
            boolean bl4;
            block14: {
                void var6_7;
                long l2;
                l2 = a ^ l2;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-54950078405289497L, (long)l2);
                bl4 = var6_7 & 1;
                if (callSite == false) {
                    if (bl4) {
                        string = moduleConfig.id;
                    }
                    bl4 = var6_7 & 2;
                }
                CallSite callSite2 = callSite;
                if (l2 >= 0L) {
                    if (callSite2 == false) {
                        if (bl4) {
                            bind = moduleConfig.bind;
                        }
                        bl4 = var6_7 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l2 >= 0L) {
                    if (callSite2 == false) {
                        if (bl4) {
                            bl = moduleConfig.enabled;
                        }
                        bl4 = var6_7 & ModuleConfig.zkmBootstrap2("q", (int)28575, (long)(0x4CD9008E971BDFBBL ^ l2));
                    }
                    callSite2 = callSite;
                }
                if (l2 > 0L) {
                    if (callSite2 == false) {
                        if (bl4) {
                            string2 = moduleConfig.item;
                        }
                        bl4 = var6_7 & ModuleConfig.zkmBootstrap2("q", (int)21336, (long)(0x581EE2AA01DA637FL ^ l2));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 != false) break block14;
                if (!bl4) break block15;
                bl4 = moduleConfig.legit;
            }
            bl3 = bl4;
        }
        return moduleConfig.copy(string, bind, bl, string2, bl3);
    }

    @NotNull
    public String toString() {
        boolean bl = this.legit;
        String string = this.item;
        boolean bl2 = this.enabled;
        Bind bind = this.bind;
        String string2 = this.id;
        return "ItemMacro(id=" + string2 + ", bind=" + bind + ", enabled=" + bl2 + ", item=" + string + ", legit=" + bl + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * 31 + this.bind.hashCode();
        n = n * 31 + Boolean.hashCode(this.enabled);
        n = n * 31 + this.item.hashCode();
        n = n * 31 + Boolean.hashCode(this.legit);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ModuleConfig)) {
            return false;
        }
        ModuleConfig moduleConfig = (ModuleConfig)object;
        if (!Intrinsics.areEqual(this.id, moduleConfig.id)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.bind, moduleConfig.bind)) {
            return false;
        }
        if (this.enabled != moduleConfig.enabled) {
            return false;
        }
        if (!Intrinsics.areEqual(this.item, moduleConfig.item)) {
            return false;
        }
        return this.legit == moduleConfig.legit;
    }

    @JvmStatic
    public static final void write$Self(ModuleConfig moduleConfig, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, moduleConfig.getId());
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, BindSerializer.INSTANCE, moduleConfig.getBind());
        compositeEncoder.encodeBooleanElement(serialDescriptor, 2, moduleConfig.isEnabled());
        compositeEncoder.encodeStringElement(serialDescriptor, 3, moduleConfig.item);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 4, moduleConfig.legit);
    }

    public ModuleConfig(int n, String string, Bind bind, boolean bl, String string2, boolean bl2, SerializationConstructorMarker serializationConstructorMarker) {
        if (31 != (0x1F & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 31, ModuleConfigSerializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.bind = bind;
        this.enabled = bl;
        this.item = string2;
        this.legit = bl2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ModuleConfig.a = ZkmKeys.a(8886840658067660929L, 5790751051605416259L, MethodHandles.lookup().lookupClass()).a(233490946805399L);
                        var20 = ModuleConfig.a ^ 65113441048867L;
                        ModuleConfig.d = new HashMap<K, V>(13);
                        ModuleConfig.zkmSetFlag(false);
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
                        var18_3 = new String[13];
                        var16_4 = 0;
                        var15_5 = "\u00cb\t\u00a0\u00daW+_\u0084\u001c\u001d\u00e0d\u00fa\u00cf\u0084\u0018\u0010\u000e\u00a3\u0090\u00e3\u00c6\u00a1\u00c7\u00f9\u008fD^\u008b\u0082Pp%\u0010\u00e9%\u00de\u0003\u0006I\u00d6\u0099j\u00170~\u0005E\u00d1\"\u0010{\u0094\u00bb\u00ffl\u008b''\n)\u0083\u0017\u000fE,.\u0018\u00c1\u00e0\u00d5=\u00f5\u00ba\u00a7U\u0095\u00cbL\u00fc\u0098\u0088\u00ae\u00bc)iv?A%K\u0092\u0010Xz\u0012\u009d4\u0089E\u00f4v\u00c3\u00c9\u00c9\u0015 \u0093i \u0098h]/}{\t\u00b67c6\u00b5\u00c1\u00cf\u001a\u0098\u00db\u0005\u00b2\u00a1j\u00f3S\u00af\u00fbv\u00c9\t\u008c\u00e7_\u00e7\u0010\u0097\u00b5\u00993\u00bak\u0083\u000b\u0088\u0084\u009f\t5\n\u00fc\u0018\u0010\u0082\u008c\u0011\u001a\u00d4\u001a\u0080\u00dc\u0001\u00e1\u00c5K\u00e6w\u0019\u00e4\u0010\u008d.x\u001b\u00cb/\u00ff,\u00da\u00ae\u0002>\u008c\u00b7f\u0016\u0018x\u001da\u00b3\u0086\u00a7sa\u008bp\u0013\u00fa\u00a7\u00c5\u00ec>K\u00a2\u0010\u00d1\u00ec\u00d5\u00a0\u00a7";
                        var17_6 = 218;
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
                            var18_3[var16_4++] = ModuleConfig.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0090\u0018\u00b4\u00c6\u0010wo\u00bcTWG\u00a7-\u000f?\u00a4\u0010w\u00ba\u0011\u00d9QK\u007fX7\u00ce\u0088q\u00a1\u00ce'\u00e8";
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
                            var18_3[var16_4++] = ModuleConfig.zkmDecodeUtf8(var19_9).intern();
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
                ModuleConfig.b = var18_3;
                ModuleConfig.c = new String[13];
                ModuleConfig.g = new HashMap<K, V>(13);
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
                var4_14 = "\u0017\n\u00aa\u00d9\u0011\u00d68W\u00f9]E\u00c9+\u00bc\u00f9B";
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
                    var4_14 = "\u0096\u0084\u0007`%i\u00c6~0dg\u00fe\u00bc\n\u0002\r";
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
        ModuleConfig.e = var6_12;
        ModuleConfig.f = new Integer[4];
        ModuleConfig.Companion = new ModuleConfig$Companion(null);
    }

    public static void zkmSetFlag(boolean bl) {
        J = bl;
    }

    public static boolean zkmGetFlag() {
        return J;
    }

    public static boolean j() {
        return true;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3DC2;
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
                throw new RuntimeException("su/catlean/ft", exception);
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
            ModuleConfig.c[n2] = ModuleConfig.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ModuleConfig.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ft" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4E78;
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
                throw new RuntimeException("su/catlean/ft", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ModuleConfig.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ModuleConfig.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ft" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ModuleConfig.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ModuleConfig.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

