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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.ConfigData$Companion;
import su.catlean.config.ConfigDataSerializer;
import su.catlean.module.Module;
import su.catlean.render.CurrentTheme;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class ConfigData {
    @NotNull
    public static final ConfigData$Companion Companion;
    @NotNull
    private String modulesConfig;
    @NotNull
    private String widgetsConfig;
    @NotNull
    private String inventoriesConfig;
    @NotNull
    private String themeConfig;
    private boolean rpcAsked;
    private static String[] Y;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    public ConfigData(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull String string4, boolean bl) {
        Intrinsics.checkNotNullParameter(string, "modulesConfig");
        Intrinsics.checkNotNullParameter(string2, "widgetsConfig");
        Intrinsics.checkNotNullParameter(string3, "inventoriesConfig");
        Intrinsics.checkNotNullParameter(string4, "themeConfig");
        this.modulesConfig = string;
        this.widgetsConfig = string2;
        this.inventoriesConfig = string3;
        this.themeConfig = string4;
        this.rpcAsked = bl;
    }

    public ConfigData(String string, String string2, String string3, String string4, boolean bl, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            string = "default";
        }
        if ((n & 2) != 0) {
            string2 = "default";
        }
        if ((n & 4) != 0) {
            string3 = "default";
        }
        if ((n & 8) != 0) {
            string4 = CurrentTheme.getThemePreset().getId();
        }
        if ((n & 0x10) != 0) {
            bl = false;
        }
        this(string, string2, string3, string4, bl);
    }

    @NotNull
    public final String getModulesConfig() {
        return this.modulesConfig;
    }

    public final void setModulesConfig(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.modulesConfig = string;
    }

    @NotNull
    public final String getWidgetsConfig() {
        return this.widgetsConfig;
    }

    public final void setWidgetsConfig(@NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ConfigData.zkmBootstrap("s", (int)1441, (long)(0x654BE5BC4AAD64B6L ^ l))));
        this.widgetsConfig = string;
    }

    @NotNull
    public final String getInventoriesConfig() {
        return this.inventoriesConfig;
    }

    public final void setInventoriesConfig(@NotNull String string) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ConfigData.zkmBootstrap("s", (int)3474, (long)(0x798519C915A5C99EL ^ l))));
        this.inventoriesConfig = string;
    }

    @NotNull
    public final String getThemeConfig() {
        return this.themeConfig;
    }

    public final void setThemeConfig(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.themeConfig = string;
    }

    public final boolean isRpcAsked() {
        return this.rpcAsked;
    }

    public final void setRpcAsked(boolean bl) {
        this.rpcAsked = bl;
    }

    @NotNull
    public final String getModulesConfig2() {
        return this.modulesConfig;
    }

    @NotNull
    public final String getWidgetsConfig2() {
        return this.widgetsConfig;
    }

    @NotNull
    public final String getInventoriesConfig2() {
        return this.inventoriesConfig;
    }

    @NotNull
    public final String getThemeConfig2() {
        return this.themeConfig;
    }

    public final boolean isRpcAsked2() {
        return this.rpcAsked;
    }

    @NotNull
    public final ConfigData copy(@NotNull String string, @NotNull String string2, @NotNull String string3, @NotNull String string4, boolean bl) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ConfigData.zkmBootstrap("s", (int)29727, (long)(0x5CF7DAAA92F570F3L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)ConfigData.zkmBootstrap("s", (int)1136, (long)(0x681EC1B91414809FL ^ l))));
        Intrinsics.checkNotNullParameter(string3, (String)((Object)ConfigData.zkmBootstrap("s", (int)3817, (long)(0x89D69A266D98A0BL ^ l))));
        Intrinsics.checkNotNullParameter(string4, (String)((Object)ConfigData.zkmBootstrap("s", (int)1005, (long)(0x65ABD59F32D90706L ^ l))));
        return new ConfigData(string, string2, string3, string4, bl);
    }

    /*
     * WARNING - void declaration
     */
    public static ConfigData copy$default(long l, ConfigData configData, String string, String string2, String string3, String string4, boolean bl, int n, Object object) {
        boolean bl2;
        block15: {
            boolean bl3;
            block14: {
                void var8_8;
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)545534931738247514L, (long)l);
                bl3 = var8_8 & 1;
                if (callSite != null) {
                    if (bl3) {
                        string = configData.modulesConfig;
                    }
                    bl3 = var8_8 & 2;
                }
                CallSite callSite2 = callSite;
                if (l >= 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            string2 = configData.widgetsConfig;
                        }
                        bl3 = var8_8 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            string3 = configData.inventoriesConfig;
                        }
                        bl3 = var8_8 & ConfigData.zkmBootstrap2("r", (int)27939, (long)(0x23E4D975E796DC01L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l >= 0L) {
                    if (callSite2 != null) {
                        if (bl3) {
                            string4 = configData.themeConfig;
                        }
                        bl3 = var8_8 & ConfigData.zkmBootstrap2("r", (int)22779, (long)(0x5344A156F149E9DDL ^ l));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == null) break block14;
                if (!bl3) break block15;
                bl3 = configData.rpcAsked;
            }
            bl2 = bl3;
        }
        return configData.copy(string, string2, string3, string4, bl2);
    }

    @NotNull
    public String toString() {
        boolean bl = this.rpcAsked;
        String string = this.themeConfig;
        String string2 = this.inventoriesConfig;
        String string3 = this.widgetsConfig;
        String string4 = this.modulesConfig;
        return "SharedConfig(modulesConfig=" + string4 + ", widgetsConfig=" + string3 + ", inventoriesConfig=" + string2 + ", themeConfig=" + string + ", rpcAsked=" + bl + ")";
    }

    public int hashCode() {
        int n = this.modulesConfig.hashCode();
        n = n * 31 + this.widgetsConfig.hashCode();
        n = n * 31 + this.inventoriesConfig.hashCode();
        n = n * 31 + this.themeConfig.hashCode();
        n = n * 31 + Boolean.hashCode(this.rpcAsked);
        return n;
    }

    public boolean equals(@Nullable Object object) {
        Module[] moduleArray = new Module[]{};
        if (this == object) {
            return true;
        }
        if (!(object instanceof ConfigData)) {
            return false;
        }
        ConfigData configData = (ConfigData)object;
        if (!Intrinsics.areEqual(this.modulesConfig, configData.modulesConfig)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.widgetsConfig, configData.widgetsConfig)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.inventoriesConfig, configData.inventoriesConfig)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.themeConfig, configData.themeConfig)) {
            return false;
        }
        return this.rpcAsked == configData.rpcAsked;
    }

    @JvmStatic
    public static final void write$Self(ConfigData configData, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        block4: {
            Module[] moduleArray = new Module[]{};
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) ? true : !Intrinsics.areEqual(configData.modulesConfig, "default")) {
                compositeEncoder.encodeStringElement(serialDescriptor, 0, configData.modulesConfig);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) ? true : !Intrinsics.areEqual(configData.widgetsConfig, "default")) {
                compositeEncoder.encodeStringElement(serialDescriptor, 1, configData.widgetsConfig);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) ? true : !Intrinsics.areEqual(configData.inventoriesConfig, "default")) {
                compositeEncoder.encodeStringElement(serialDescriptor, 2, configData.inventoriesConfig);
            }
            if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) ? true : !Intrinsics.areEqual(configData.themeConfig, CurrentTheme.getThemePreset().getId())) {
                compositeEncoder.encodeStringElement(serialDescriptor, 3, configData.themeConfig);
            }
            if (!(compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) ? true : configData.rpcAsked)) break block4;
            compositeEncoder.encodeBooleanElement(serialDescriptor, 4, configData.rpcAsked);
        }
    }

    public ConfigData(int n, String string, String string2, String string3, String string4, boolean bl, SerializationConstructorMarker serializationConstructorMarker) {
        Module[] moduleArray = new Module[]{};
        if ((0 & n) != 0) {
            PluginExceptionsKt.throwMissingFieldException(n, 0, ConfigDataSerializer.INSTANCE.getDescriptor());
        }
        this.modulesConfig = (n & 1) == 0 ? "default" : string;
        this.widgetsConfig = (n & 2) == 0 ? "default" : string2;
        this.inventoriesConfig = (n & 4) == 0 ? "default" : string3;
        this.themeConfig = (n & 8) == 0 ? CurrentTheme.getThemePreset().getId() : string4;
        this.rpcAsked = (n & 0x10) == 0 ? false : bl;
    }

    public ConfigData() {
        long l = 0L;
        int n = 0;
        long l2 = (l << 32 | (long)n << 32 >>> 32) ^ a;
        this(null, null, null, null, false, (int)ConfigData.zkmBootstrap2("r", (int)30840, (long)(0x305E78795D15E24FL ^ l2)), null);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ConfigData.a = ZkmKeys.a(-3841110263315610045L, 4920367180720787619L, MethodHandles.lookup().lookupClass()).a(42694016176783L);
                        var20 = ConfigData.a ^ 99423844893040L;
                        ConfigData.e = new HashMap<K, V>(13);
                        ConfigData.zkmSetFlag(new String[1]);
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
                        var15_5 = "\u00cf-\u00be\u0085\u00ae\u00da\u00a2\u0098o{\u00db LkP\u0017 i\u00f4\u00fb\u00e1\u00a4j>\u0095\u00a6\u00ba\u00a5\u0000.es\u007f\u00ae\u00e0z\u00e0\u00c2\u00d4\u00c8\u0012\u00a3\u00ca}\u001d\u0099\u00faF\u0005\u0018\u00ae?\u00a8\u00e0\u00daC\u00e3\u001bs\u00b1\u00f0\u00ac6\u00a5\u00da\rZ\u00b9\u008f\u00d6J\u00b6\u00e0\u00d3(\u00d9\u0097L^\u0082+R a\u00f9T\u00aag\u00f9\u00a5\u00b2\u00c1\u00e8\u0019\u001bq\u00a9s08\u0002\u00d9\u00d8\u00dbm\u00fd\u000f\u00e3\u0011\u0090'#+%C(\u0018\u00b5 \u00f8\u00e7\u00a02:\u0000\u00fb}\t\u0083\u00a0\u00a0\u00de\u00b3\u00dd\u00c0\u009bT\u0010NG&\u001d\u009c\u00d5\u00cf\u00b1\u00c2\u00d6^kY\u00c7\u00c7;\u00d9\n0\u00f4s\u00bc\u009ayrVT\u0002_\u009a\u000b\u00beF\u0084)f\u00fb\u00aa\u0095\u00d5\u009cz\b\u0006e\u00b4#=\u0007D\u00e0v\u0001f%M\u00eeh\u00aa\u00e4\u009c\u00c1\u009dl\u0003]\u000f\u0010>v\u00c2#\u000f\u008a\u008c\u00a5G\u00e3\u00b0\u00fb2\u00c5{\" \u0080\u00df\u00f4b\u0095\u00ad.hSi\u00d1-K\u009d\u00bb_\u00f7\u00ffg\u0093z\u00ff\u00ef\u00cf[V=\u00b5>\u00e1\u0082?\u0010\u00d1\u00fc=\u0082\u00c8\u00d3\u00969\u00b4\u00b2z;0U'\u00fe\u0018\u00c3D\u00c4\u001cE\u00b5\u00dc2\u0017O\u0098\u00fbr6\u00e3\u008f\u00f6>J\u00ce\u0011o|\u00ca \u001c\u008dyX\u00ef8\u00ed\u00fb\u00a2#\u00eca\u00e3\"\u0088\u00a8\u00e0Ny\u00b2\bSK7y1\u00a5C\u00d9\u00fd\u00933\u0010\u009b\u00c9\u00e1\u0014\u0006\u00f8\u0010\u001f\u00f4\u00ff\u00e2\u00cb\u0091\u00ae\u00aa|(\u0000\u00afu\u00d6\u00da/\u00e1\u0093%t\u0096+\u00b2\u00c0\u0012v\u0014\u00da\u008c\u0005#o\u00c8tE\"\u008e\u008av\u0003\u00cb\u00b5+\r\u00df\u00e3\u0006K\ft %muJ\u00c5M\u0085\u0087K\u00c4S\u000f.*0\u00fb\u0001\u00fe<\u0016b(\u00c1\u00ce\u00ad\u00ca\u00ca\u00aa\u0019\u0005 \u0087(l\u00f5\u00ae3%\u00a0S\u000fX\u00a7h<U\b\u0002U\u00fdj\u00ec\u00ab\u00fb\u009f\u0085\u0016f\u00d4~\u00e5\u00fe\u00a0 \u0014g\u00ea\u00df\u009a\u0080\u0003k\u00e9";
                        var17_6 = 462;
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
                            var18_3[var16_4++] = ConfigData.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u008eDC\u00e3\u00ba\u00da\u00f0{\u00a7Gk\u0000\u008e\u00a3\u00ba\u00a0\u00fd\u00d9\u0084\u00fe\u00ab{\u001f\u0003\u00ce\u00a1\u0088\u00d6\u00158\u00baM \u00cd\u0004V)\u00f0\u00ce\u0013;+V\u00d4D\u0007\u0091\b\u0014\u008c\u00f8\u00e5\u0000g\u0088\u001b\u00bf\u001d\u00fd\u0084D\u0091\u0006\u00d6y";
                            var17_6 = 65;
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
                            var18_3[var16_4++] = ConfigData.zkmDecodeUtf8(var19_9).intern();
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
                ConfigData.b = var18_3;
                ConfigData.d = new String[17];
                ConfigData.i = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "\u009c\u00ad\u00a2\u00c2n/h\u00e5\u008d_\u00cat6\u00a5\u008f\u007f\u0015\u00aam~\u0087@\u00d3\u0012\u001a\u00cd\u0012\u001ec\u0084)\u00a4";
                var5_15 = 32;
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
                    var4_14 = "B\u00b5\u007fC\u0088\u00d2Y\u00d5\u0092\u00d5\u00e4{oN\u008b\u00d8";
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
        ConfigData.f = var6_12;
        ConfigData.h = new Integer[6];
        ConfigData.Companion = new ConfigData$Companion(null);
    }

    public static void zkmSetFlag(String[] stringArray) {
        Y = stringArray;
    }

    public static String[] zkmGetFlag() {
        return Y;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x37AF;
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
                throw new RuntimeException("su/catlean/sl", exception);
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
            ConfigData.d[n2] = ConfigData.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ConfigData.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/sl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x37C3;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/sl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ConfigData.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ConfigData.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/sl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ConfigData.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ConfigData.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

