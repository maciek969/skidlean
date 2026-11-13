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
import su.catlean.macro.Macro;
import su.catlean.macro.SettingMacro$$serializer;
import su.catlean.macro.SettingMacro$Companion;
import su.catlean.module.Module;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class SettingMacro
implements Macro {
    @NotNull
    public static final SettingMacro$Companion Companion;
    @NotNull
    private String id;
    @NotNull
    private Bind bind;
    private boolean enabled;
    @NotNull
    private String module;
    @NotNull
    private String setting;
    @NotNull
    private String pressValue;
    @NotNull
    private String releaseValue;
    private boolean hold;
    private static Module[] J;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    public SettingMacro(@NotNull String string, @NotNull Bind bind, boolean bl, @NotNull String string2, @NotNull String string3, @NotNull String string4, @NotNull String string5, boolean bl2) {
        ModuleConfig.j();
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(bind, "bind");
        Intrinsics.checkNotNullParameter(string2, "module");
        Intrinsics.checkNotNullParameter(string3, "setting");
        Intrinsics.checkNotNullParameter(string4, "pressValue");
        Intrinsics.checkNotNullParameter(string5, "releaseValue");
        this.id = string;
        this.bind = bind;
        this.enabled = bl;
        this.module = string2;
        this.setting = string3;
        this.pressValue = string4;
        this.releaseValue = string5;
        this.hold = bl2;
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
    public final String getModule() {
        return this.module;
    }

    public final void setModule(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.module = string;
    }

    @NotNull
    public final String getSetting() {
        return this.setting;
    }

    public final void setSetting(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.setting = string;
    }

    @NotNull
    public final String getPressValue() {
        return this.pressValue;
    }

    public final void setPressValue(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.pressValue = string;
    }

    @NotNull
    public final String getReleaseValue() {
        return this.releaseValue;
    }

    public final void setReleaseValue(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "<set-?>");
        this.releaseValue = string;
    }

    public final boolean isHold() {
        return this.hold;
    }

    public final void setHold(boolean bl) {
        this.hold = bl;
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
    public final String getModule2() {
        return this.module;
    }

    @NotNull
    public final String getSetting2() {
        return this.setting;
    }

    @NotNull
    public final String getPressValue2() {
        return this.pressValue;
    }

    @NotNull
    public final String getReleaseValue2() {
        return this.releaseValue;
    }

    public final boolean isHold2() {
        return this.hold;
    }

    @NotNull
    public final SettingMacro copy(@NotNull String string, @NotNull Bind bind, boolean bl, @NotNull String string2, @NotNull String string3, @NotNull String string4, @NotNull String string5, boolean bl2) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)SettingMacro.zkmBootstrap("u", (int)8410, (long)(0x4B137F08847B0A22L ^ l))));
        Intrinsics.checkNotNullParameter(bind, (String)((Object)SettingMacro.zkmBootstrap("u", (int)27327, (long)(0x41F3708E1F6E4053L ^ l))));
        Intrinsics.checkNotNullParameter(string2, (String)((Object)SettingMacro.zkmBootstrap("u", (int)21090, (long)(0x200945948B85F882L ^ l))));
        Intrinsics.checkNotNullParameter(string3, (String)((Object)SettingMacro.zkmBootstrap("u", (int)7437, (long)(0x27F77CEEEAEB7E8L ^ l))));
        Intrinsics.checkNotNullParameter(string4, (String)((Object)SettingMacro.zkmBootstrap("u", (int)25748, (long)(0xDCF6660C570CE77L ^ l))));
        Intrinsics.checkNotNullParameter(string5, (String)((Object)SettingMacro.zkmBootstrap("u", (int)5162, (long)(0x361C23938EF6BED7L ^ l))));
        return new SettingMacro(string, bind, bl, string2, string3, string4, string5, bl2);
    }

    /*
     * WARNING - void declaration
     */
    public static SettingMacro copy$default(SettingMacro settingMacro, long l, String string, Bind bind, boolean bl, String string2, String string3, String string4, String string5, boolean bl2, int n, Object object) {
        boolean bl3;
        block24: {
            boolean bl4;
            block23: {
                void var11_11;
                l = a ^ l;
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)191154838269232057L, (long)l);
                bl4 = var11_11 & 1;
                if (callSite != false) {
                    if (bl4) {
                        string = settingMacro.id;
                    }
                    bl4 = var11_11 & 2;
                }
                CallSite callSite2 = callSite;
                if (l > 0L) {
                    if (callSite2 != false) {
                        if (bl4) {
                            bind = settingMacro.bind;
                        }
                        bl4 = var11_11 & 4;
                    }
                    callSite2 = callSite;
                }
                if (l >= 0L) {
                    if (callSite2 != false) {
                        if (bl4) {
                            bl = settingMacro.enabled;
                        }
                        bl4 = var11_11 & SettingMacro.zkmBootstrap2("l", (int)1259, (long)(0x2EC91E4EC1AFC7ECL ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 != false) {
                        if (bl4) {
                            string2 = settingMacro.module;
                        }
                        bl4 = var11_11 & SettingMacro.zkmBootstrap2("l", (int)18625, (long)(0x6441B39DDEE38BC2L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 != false) {
                        if (bl4) {
                            string3 = settingMacro.setting;
                        }
                        bl4 = var11_11 & SettingMacro.zkmBootstrap2("l", (int)26219, (long)(0xF84B4248AF0256FL ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l >= 0L) {
                    if (callSite2 != false) {
                        if (bl4) {
                            string4 = settingMacro.pressValue;
                        }
                        bl4 = var11_11 & SettingMacro.zkmBootstrap2("l", (int)10782, (long)(0x6A63050F1052E91CL ^ l));
                    }
                    callSite2 = callSite;
                }
                if (l > 0L) {
                    if (callSite2 != false) {
                        if (bl4) {
                            string5 = settingMacro.releaseValue;
                        }
                        bl4 = var11_11 & SettingMacro.zkmBootstrap2("l", (int)15737, (long)(0x1C83E0507F9E7E79L ^ l));
                    }
                    callSite2 = callSite;
                }
                if (callSite2 == false) break block23;
                if (!bl4) break block24;
                bl4 = settingMacro.hold;
            }
            bl3 = bl4;
        }
        return settingMacro.copy(string, bind, bl, string2, string3, string4, string5, bl3);
    }

    @NotNull
    public String toString() {
        boolean bl = this.hold;
        String string = this.releaseValue;
        String string2 = this.pressValue;
        String string3 = this.setting;
        String string4 = this.module;
        boolean bl2 = this.enabled;
        Bind bind = this.bind;
        String string5 = this.id;
        return "SettingMacro(id=" + string5 + ", bind=" + bind + ", enabled=" + bl2 + ", module=" + string4 + ", setting=" + string3 + ", pressValue=" + string2 + ", releaseValue=" + string + ", hold=" + bl + ")";
    }

    public int hashCode() {
        int n;
        block0: {
            n = this.id.hashCode();
            n = n * 31 + this.bind.hashCode();
            n = n * 31 + Boolean.hashCode(this.enabled);
            n = n * 31 + this.module.hashCode();
            boolean bl = ModuleConfig.j();
            n = n * 31 + this.setting.hashCode();
            n = n * 31 + this.pressValue.hashCode();
            n = n * 31 + this.releaseValue.hashCode();
            n = n * 31 + Boolean.hashCode(this.hold);
            if (bl) break block0;
            Module.zkmSetFlag(new Module[5]);
        }
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
            if (!(object2 instanceof SettingMacro)) {
                return false;
            }
            object2 = object;
        }
        SettingMacro settingMacro = (SettingMacro)object2;
        boolean bl2 = Intrinsics.areEqual(this.id, settingMacro.id);
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.bind, settingMacro.bind);
        }
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = this.enabled;
        }
        if (bl) {
            if (bl2 != settingMacro.enabled) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.module, settingMacro.module);
        }
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.setting, settingMacro.setting);
        }
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.pressValue, settingMacro.pressValue);
        }
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = Intrinsics.areEqual(this.releaseValue, settingMacro.releaseValue);
        }
        if (bl) {
            if (!bl2) {
                return false;
            }
            bl2 = this.hold;
        }
        if (bl) {
            if (bl2 != settingMacro.hold) {
                return false;
            }
            bl2 = true;
        }
        return bl2;
    }

    @JvmStatic
    public static final void write$Self(SettingMacro settingMacro, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        compositeEncoder.encodeStringElement(serialDescriptor, 0, settingMacro.getId());
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, BindSerializer.INSTANCE, settingMacro.getBind());
        compositeEncoder.encodeBooleanElement(serialDescriptor, 2, settingMacro.isEnabled());
        compositeEncoder.encodeStringElement(serialDescriptor, 3, settingMacro.module);
        compositeEncoder.encodeStringElement(serialDescriptor, 4, settingMacro.setting);
        compositeEncoder.encodeStringElement(serialDescriptor, 5, settingMacro.pressValue);
        compositeEncoder.encodeStringElement(serialDescriptor, 6, settingMacro.releaseValue);
        compositeEncoder.encodeBooleanElement(serialDescriptor, 7, settingMacro.hold);
    }

    public SettingMacro(int n, String string, Bind bind, boolean bl, String string2, String string3, String string4, String string5, boolean bl2, SerializationConstructorMarker serializationConstructorMarker) {
        if (255 != (0xFF & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 255, SettingMacro$$serializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.bind = bind;
        this.enabled = bl;
        this.module = string2;
        this.setting = string3;
        this.pressValue = string4;
        this.releaseValue = string5;
        this.hold = bl2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        SettingMacro.a = ZkmKeys.a(-6448266468659516814L, -5397245568266820198L, MethodHandles.lookup().lookupClass()).a(167200558413576L);
                        var20 = SettingMacro.a ^ 135068977646307L;
                        SettingMacro.d = new HashMap<K, V>(13);
                        SettingMacro.zkmSetFlag(null);
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
                        var18_3 = new String[22];
                        var16_4 = 0;
                        var15_5 = "U=?\u00c0\u009e \r\u0017*\u00c7/\u009a\u00e20\u00a0\b\u0018U\u00f0\u00e2jF\u00de\u009d\rF`h\u0007Q\u000f\u0006z\u00f3\u00d3|\u00fa\u00f7x\u001ag\u0010\u0006H\u00d2Y\u001e\u00f6\u00e0\u00fe\u00e5\u00aa\u0094\u00d8\u0017\u00ee\u00faK\u0010q\u00a1^v\u0007\b\u00fb\u00e0Y?\u00d7NwA\u0006q \u0086\u00cb\u00e4\u00d2\u00ecC\u00f37\u0090\u008fe\u00e2\u0092\u00a7[\u001a\u00a5\u00e1\u0084F\u0010\u00ca\u0082\u00e0\nze\u0099L(\u00ec\u00b4\u0010X\u00e2\u0080\u00a4|.9\u00d3\u00f8\u0018\u009eT\u00ae\u00b4\u00f4- \u00b2\b\u008f`\u00c2\u00eb\u00fd\n\u008a\u00f5$\u001cu\u00dd\u0080\u00d0a3\u00e6\u0082\u00d5N\u00ee\"\tW0P\u0003\u00bb_\u00c1\u0010\u00ee\u00cb\u0001\u00c6Fa\u00a2\u00e4\u00a2\u00d9\u00fcM\u00a9&0\u008a\u00109\u00d1z\u0093=3\u00f83\u0091\u00c5t\u00f51\u009e\u00da\u00d4\u0010_\u000b\u00fd\u00f4grDs\u00af\u0089Wbe8\u00d1\u00ec\u0018\u00d4\u00ff\u009a\u0091\u00ee\"\u00f1t\u00cc\u0091K\u00a2\u00acGqCQ#\u00b3@\t)\u00d1\u00b5 \u00bf/\u00d5\u0013\u00db\u00f7\u00c9\u0013q\u0005\u008a\u00b4\u009f5\u00ccdwJ[\u00fe)\u00b6\u001d\u00b4\r\u00df\u00d3\u0017\u00c5\u0010U'\u0010\u00c1;=*5Vdw\u0000\u00f6\u00a0\u00d6|\u0093'\u00c9 \u00e6\u00c6\u00beD\t\u0000\f\u008e\u0012\u00bf\r\u00ea,\u009cg\u00d7\u008b\u00b4\u00cf\u00cb$\u00ffS2\u00e8i\u008bc\u00d43\u0005\u00c8 U\u00e6zph0\u001b\u00ecZ\u00c0E\u00c94e\u00e1\u00cd\u0006Z\u00c4=>\u00b2L\u00b0v\u00f4\u00e0v\u00a3F\u00b2\u0087\u0018/\u0096\u00f5\u00f0\u00ef\u00fc\u000f\u0089\u00c4f\u00dc4\u00df\b\u00d5 v\u0096^\u001b\u00bf\u00cfz)(]F\u008d=\u00c2\u008c\u00ecG\u0015\u0000\u0086\u0085O\u0080\u0090BAb\u00bbHHj\u00fd\r\u00c8WF\u00ac\u0099\u00b1\u00c6\u00ec5\u00ab\u00a1\u00e8 ]\u00f1\u00f7\u0018\u00b6\u00ee\u007f\u00ecQ\u008a\u00d7\u00fd\u0006(\u00dd\u0001n\u0010\u00bbJ\u0088\u00bd+\u00a4\u007fWA&\u0010\u00f3\u00afk=%\u00afe\u00a4I\u00e1\u00c8E\u0094O\u00d2G\u0010\u00b7:\u00962z\u00f7\u008a\u00e1?\u0082\u0096)\"0\u00df\u00e7";
                        var17_6 = 475;
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
                            var18_3[var16_4++] = SettingMacro.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "^A\u0003fJ]f\u00a9L\u00b8\u000fDGH-{\u008c\u0092\u0092\u00dbFD\u00a8\u00c8\u0010\u00ad\\\\Z\f\u0007\u00f7o\u00d9]\u00cd\u00fe\u008c\u0090\u0015h";
                            var17_6 = 41;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = SettingMacro.zkmDecodeUtf8(var19_9).intern();
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
                SettingMacro.b = var18_3;
                SettingMacro.c = new String[22];
                SettingMacro.h = new HashMap<K, V>(13);
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
                var6_12 = new long[11];
                var3_13 = 0;
                var4_14 = "A\u00ac\u00cbSc\u00be\u00cf\u000bLjm\u008f\u00e5\u00e9\u00b2\u008c\u00c4\u00c5g\u00b4\u00ee\u00b5w\bA\u001f'~i\u008f\u00e2\u0082.\u00a6\u000b\u00fa\u00fb\u00b8L\u00a3\u00b6:\u00aec\u00bb\u00e4\u00c7O\u0094\u0011+\u00adP\u00e7&\u00d8\u00afm<\u00c2j\u00d9\t\u00f7\u008c\u00db\u008f\u00f0\u00eaz|\u00ff";
                var5_15 = 72;
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
                    var4_14 = "\u0096c};d\u00ecZ\u00c8\"\u0011\u00d4 \u009c{\u00f4\u0081";
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
        SettingMacro.e = var6_12;
        SettingMacro.g = new Integer[11];
        SettingMacro.Companion = new SettingMacro$Companion(null);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        J = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return J;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x507C;
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
                throw new RuntimeException("su/catlean/nh", exception);
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
            SettingMacro.c[n2] = SettingMacro.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = SettingMacro.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x406A;
        if (g[n2] == null) {
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
                throw new RuntimeException("su/catlean/nh", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            SettingMacro.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = SettingMacro.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/nh" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(SettingMacro.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(SettingMacro.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

