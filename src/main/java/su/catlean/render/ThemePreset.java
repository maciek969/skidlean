/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.config.ColorSerializer;
import su.catlean.module.Module;
import su.catlean.render.ThemePreset$Companion;
import su.catlean.render.ThemePresetSerializer;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

@Serializable
public final class ThemePreset {
    @NotNull
    public static final ThemePreset$Companion Companion;
    @NotNull
    private final String id;
    @NotNull
    private Color primaryColor;
    @NotNull
    private Color secondaryColor;
    @NotNull
    private Color blurColor;
    @NotNull
    private Color widgetBlurColor;
    @NotNull
    private Color outlineColor;
    @NotNull
    private Color textColor;
    @NotNull
    private Color nonactiveTextColor;
    @NotNull
    private Color slidersColor;
    @NotNull
    private Color nonActiveModuleColor;
    @NotNull
    private Color moduleColor;
    @NotNull
    private Color moduleOutlineColor;
    @NotNull
    private Color nonActiveOutlineModuleColor;
    @JvmField
    @NotNull
    private static final Lazy[] $childSerializers;
    private static String[] E;
    private static final long a;
    private static final String[] c;
    private static final String[] g;
    private static final Map h;
    private static final long[] k;
    private static final Integer[] m;
    private static final Map o;

    public ThemePreset(@NotNull String string, @NotNull Color color, @NotNull Color color2, @NotNull Color color3, @NotNull Color color4, @NotNull Color color5, @NotNull Color color6, @NotNull Color color7, @NotNull Color color8, @NotNull Color color9, @NotNull Color color10, @NotNull Color color11, @NotNull Color color12) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(color, "primaryColor");
        Intrinsics.checkNotNullParameter(color2, "secondaryColor");
        Intrinsics.checkNotNullParameter(color3, "blurColor");
        Intrinsics.checkNotNullParameter(color4, "widgetBlurColor");
        Intrinsics.checkNotNullParameter(color5, "outlineColor");
        Intrinsics.checkNotNullParameter(color6, "textColor");
        Intrinsics.checkNotNullParameter(color7, "nonactiveTextColor");
        Intrinsics.checkNotNullParameter(color8, "slidersColor");
        Intrinsics.checkNotNullParameter(color9, "nonActiveModuleColor");
        Intrinsics.checkNotNullParameter(color10, "moduleColor");
        Intrinsics.checkNotNullParameter(color11, "moduleOutlineColor");
        Intrinsics.checkNotNullParameter(color12, "nonActiveOutlineModuleColor");
        this.id = string;
        this.primaryColor = color;
        this.secondaryColor = color2;
        this.blurColor = color3;
        this.widgetBlurColor = color4;
        this.outlineColor = color5;
        this.textColor = color6;
        this.nonactiveTextColor = color7;
        this.slidersColor = color8;
        this.nonActiveModuleColor = color9;
        this.moduleColor = color10;
        this.moduleOutlineColor = color11;
        this.nonActiveOutlineModuleColor = color12;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final Color getPrimaryColor() {
        return this.primaryColor;
    }

    public final void setPrimaryColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.primaryColor = color;
    }

    @NotNull
    public final Color getSecondaryColor() {
        return this.secondaryColor;
    }

    public final void setSecondaryColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.secondaryColor = color;
    }

    @NotNull
    public final Color getBlurColor() {
        return this.blurColor;
    }

    public final void setBlurColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.blurColor = color;
    }

    @NotNull
    public final Color getWidgetBlurColor() {
        return this.widgetBlurColor;
    }

    public final void setWidgetBlurColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.widgetBlurColor = color;
    }

    @NotNull
    public final Color getOutlineColor() {
        return this.outlineColor;
    }

    public final void setOutlineColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.outlineColor = color;
    }

    @NotNull
    public final Color getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.textColor = color;
    }

    @NotNull
    public final Color getNonactiveTextColor() {
        return this.nonactiveTextColor;
    }

    public final void setNonactiveTextColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.nonactiveTextColor = color;
    }

    @NotNull
    public final Color getSlidersColor() {
        return this.slidersColor;
    }

    public final void setSlidersColor(@NotNull Color color) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)ThemePreset.zkmBootstrap("a", (int)2689, (long)(0x391E38729AC1F2FFL ^ l))));
        this.slidersColor = color;
    }

    @NotNull
    public final Color getNonActiveModuleColor() {
        return this.nonActiveModuleColor;
    }

    public final void setNonActiveModuleColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.nonActiveModuleColor = color;
    }

    @NotNull
    public final Color getModuleColor() {
        return this.moduleColor;
    }

    public final void setModuleColor(@NotNull Color color) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 40 >>> 32 | (long)n3 << 56 >>> 56) ^ a;
        Intrinsics.checkNotNullParameter(color, (String)((Object)ThemePreset.zkmBootstrap("a", (int)21450, (long)(0x15FBBA2F0ECF1846L ^ l))));
        this.moduleColor = color;
    }

    @NotNull
    public final Color getModuleOutlineColor() {
        return this.moduleOutlineColor;
    }

    public final void setModuleOutlineColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.moduleOutlineColor = color;
    }

    @NotNull
    public final Color getNonActiveOutlineModuleColor() {
        return this.nonActiveOutlineModuleColor;
    }

    public final void setNonActiveOutlineModuleColor(@NotNull Color color) {
        Intrinsics.checkNotNullParameter(color, "<set-?>");
        this.nonActiveOutlineModuleColor = color;
    }

    @NotNull
    public final String getId2() {
        return this.id;
    }

    @NotNull
    public final Color getPrimaryColor2() {
        return this.primaryColor;
    }

    @NotNull
    public final Color getSecondaryColor2() {
        return this.secondaryColor;
    }

    @NotNull
    public final Color getBlurColor2() {
        return this.blurColor;
    }

    @NotNull
    public final Color getWidgetBlurColor2() {
        return this.widgetBlurColor;
    }

    @NotNull
    public final Color getOutlineColor2() {
        return this.outlineColor;
    }

    @NotNull
    public final Color getTextColor2() {
        return this.textColor;
    }

    @NotNull
    public final Color getNonactiveTextColor2() {
        return this.nonactiveTextColor;
    }

    @NotNull
    public final Color getSlidersColor2() {
        return this.slidersColor;
    }

    @NotNull
    public final Color getNonActiveModuleColor2() {
        return this.nonActiveModuleColor;
    }

    @NotNull
    public final Color getModuleColor2() {
        return this.moduleColor;
    }

    @NotNull
    public final Color getModuleOutlineColor2() {
        return this.moduleOutlineColor;
    }

    @NotNull
    public final Color getNonActiveOutlineModuleColor2() {
        return this.nonActiveOutlineModuleColor;
    }

    @NotNull
    public final ThemePreset copy(@NotNull String string, @NotNull Color color, @NotNull Color color2, @NotNull Color color3, @NotNull Color color4, @NotNull Color color5, @NotNull Color color6, @NotNull Color color7, @NotNull Color color8, @NotNull Color color9, @NotNull Color color10, @NotNull Color color11, @NotNull Color color12) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)ThemePreset.zkmBootstrap("a", (int)4713, (long)(0x568F34712B145BF2L ^ l))));
        Intrinsics.checkNotNullParameter(color, (String)((Object)ThemePreset.zkmBootstrap("a", (int)19978, (long)(0x3E338F6830F907ABL ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)ThemePreset.zkmBootstrap("a", (int)13295, (long)(0x3BD0FC3604A07A7EL ^ l))));
        Intrinsics.checkNotNullParameter(color3, (String)((Object)ThemePreset.zkmBootstrap("a", (int)19372, (long)(0x649539A2BE8021EL ^ l))));
        Intrinsics.checkNotNullParameter(color4, (String)((Object)ThemePreset.zkmBootstrap("a", (int)32667, (long)(0x407305D7653B626L ^ l))));
        Intrinsics.checkNotNullParameter(color5, (String)((Object)ThemePreset.zkmBootstrap("a", (int)23299, (long)(0x2C0CDF4001F112BAL ^ l))));
        Intrinsics.checkNotNullParameter(color6, (String)((Object)ThemePreset.zkmBootstrap("a", (int)11488, (long)(0x426279F24F896557L ^ l))));
        Intrinsics.checkNotNullParameter(color7, (String)((Object)ThemePreset.zkmBootstrap("a", (int)26174, (long)(0x1C2663612186AF8BL ^ l))));
        Intrinsics.checkNotNullParameter(color8, (String)((Object)ThemePreset.zkmBootstrap("a", (int)18729, (long)(0x4394F397FD9D0084L ^ l))));
        Intrinsics.checkNotNullParameter(color9, (String)((Object)ThemePreset.zkmBootstrap("a", (int)2634, (long)(0xD258B9CF273C3D7L ^ l))));
        Intrinsics.checkNotNullParameter(color10, (String)((Object)ThemePreset.zkmBootstrap("a", (int)5399, (long)(0x3967ADAEBFD25CB2L ^ l))));
        Intrinsics.checkNotNullParameter(color11, (String)((Object)ThemePreset.zkmBootstrap("a", (int)14197, (long)(0x3423E1D0C5427EEBL ^ l))));
        Intrinsics.checkNotNullParameter(color12, (String)((Object)ThemePreset.zkmBootstrap("a", (int)10395, (long)(0x5A726654C517E127L ^ l))));
        return new ThemePreset(string, color, color2, color3, color4, color5, color6, color7, color8, color9, color10, color11, color12);
    }

    public static ThemePreset copy$default(ThemePreset themePreset, String string, Color color, Color color2, Color color3, Color color4, Color color5, Color color6, Color color7, Color color8, Color color9, Color color10, Color color11, Color color12, int n, Object object, long l) {
        l = a ^ l;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-535889680630956456L, (long)l);
        int n2 = n & 1;
        if (callSite != null) {
            if (n2 != 0) {
                string = themePreset.id;
            }
            n2 = n & 2;
        }
        CallSite callSite2 = callSite;
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color = themePreset.primaryColor;
                }
                n2 = n & 4;
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color2 = themePreset.secondaryColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)5600, (long)(0x3B2E72583F3486AEL ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color3 = themePreset.blurColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)22770, (long)(0x21024A70EC3ACBA4L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color4 = themePreset.widgetBlurColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)27325, (long)(0x262B940A228CF9E8L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color5 = themePreset.outlineColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)13246, (long)(0x66AF5931B88A0EEL ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color6 = themePreset.textColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)19343, (long)(0x694CFCF9D13DD8D4L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color7 = themePreset.nonactiveTextColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)28121, (long)(0x26CE1DAE08E8FE95L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color8 = themePreset.slidersColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)19162, (long)(0x7BFD6C8CCF315995L ^ l));
            }
            callSite2 = callSite;
        }
        if (l >= 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color9 = themePreset.nonActiveModuleColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)4242, (long)(0x3DFF92938D2B03C5L ^ l));
            }
            callSite2 = callSite;
        }
        if (l > 0L) {
            if (callSite2 != null) {
                if (n2 != 0) {
                    color10 = themePreset.moduleColor;
                }
                n2 = n & ThemePreset.zkmBootstrap2("m", (int)27460, (long)(0x20704B3C2BC4F817L ^ l));
            }
            callSite2 = callSite;
        }
        if (callSite2 != null) {
            if (n2 != 0) {
                color11 = themePreset.moduleOutlineColor;
            }
            n2 = n & ThemePreset.zkmBootstrap2("m", (int)10944, (long)(0x7DD78E6A35C8399CL ^ l));
        }
        if (n2 != 0) {
            color12 = themePreset.nonActiveOutlineModuleColor;
        }
        return themePreset.copy(string, color, color2, color3, color4, color5, color6, color7, color8, color9, color10, color11, color12);
    }

    @NotNull
    public String toString() {
        Color color = this.nonActiveOutlineModuleColor;
        Color color2 = this.moduleOutlineColor;
        Color color3 = this.moduleColor;
        Color color4 = this.nonActiveModuleColor;
        Color color5 = this.slidersColor;
        Color color6 = this.nonactiveTextColor;
        Color color7 = this.textColor;
        Color color8 = this.outlineColor;
        Color color9 = this.widgetBlurColor;
        Color color10 = this.blurColor;
        Color color11 = this.secondaryColor;
        Color color12 = this.primaryColor;
        String string = this.id;
        return "ThemeConfig(id=" + string + ", primaryColor=" + color12 + ", secondaryColor=" + color11 + ", blurColor=" + color10 + ", widgetBlurColor=" + color9 + ", outlineColor=" + color8 + ", textColor=" + color7 + ", nonactiveTextColor=" + color6 + ", slidersColor=" + color5 + ", nonActiveModuleColor=" + color4 + ", moduleColor=" + color3 + ", moduleOutlineColor=" + color2 + ", nonActiveOutlineModuleColor=" + color + ")";
    }

    public int hashCode() {
        int n = this.id.hashCode();
        n = n * 31 + this.primaryColor.hashCode();
        n = n * 31 + this.secondaryColor.hashCode();
        n = n * 31 + this.blurColor.hashCode();
        n = n * 31 + this.widgetBlurColor.hashCode();
        n = n * 31 + this.outlineColor.hashCode();
        n = n * 31 + this.textColor.hashCode();
        n = n * 31 + this.nonactiveTextColor.hashCode();
        n = n * 31 + this.slidersColor.hashCode();
        n = n * 31 + this.nonActiveModuleColor.hashCode();
        n = n * 31 + this.moduleColor.hashCode();
        n = n * 31 + this.moduleOutlineColor.hashCode();
        n = n * 31 + this.nonActiveOutlineModuleColor.hashCode();
        return n;
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ThemePreset)) {
            return false;
        }
        ThemePreset themePreset = (ThemePreset)object;
        if (!Intrinsics.areEqual(this.id, themePreset.id)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.primaryColor, themePreset.primaryColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.secondaryColor, themePreset.secondaryColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.blurColor, themePreset.blurColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.widgetBlurColor, themePreset.widgetBlurColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.outlineColor, themePreset.outlineColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.textColor, themePreset.textColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.nonactiveTextColor, themePreset.nonactiveTextColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.slidersColor, themePreset.slidersColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.nonActiveModuleColor, themePreset.nonActiveModuleColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.moduleColor, themePreset.moduleColor)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.moduleOutlineColor, themePreset.moduleOutlineColor)) {
            return false;
        }
        return Intrinsics.areEqual(this.nonActiveOutlineModuleColor, themePreset.nonActiveOutlineModuleColor);
    }

    @JvmStatic
    public static final void write$Self(ThemePreset themePreset, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        Lazy[] lazyArray = $childSerializers;
        compositeEncoder.encodeStringElement(serialDescriptor, 0, themePreset.id);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 1, (SerializationStrategy)lazyArray[1].getValue(), themePreset.primaryColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 2, (SerializationStrategy)lazyArray[2].getValue(), themePreset.secondaryColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 3, (SerializationStrategy)lazyArray[3].getValue(), themePreset.blurColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 4, (SerializationStrategy)lazyArray[4].getValue(), themePreset.widgetBlurColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 5, (SerializationStrategy)lazyArray[5].getValue(), themePreset.outlineColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 6, (SerializationStrategy)lazyArray[6].getValue(), themePreset.textColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 7, (SerializationStrategy)lazyArray[7].getValue(), themePreset.nonactiveTextColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 8, (SerializationStrategy)lazyArray[8].getValue(), themePreset.slidersColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 9, (SerializationStrategy)lazyArray[9].getValue(), themePreset.nonActiveModuleColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 10, (SerializationStrategy)lazyArray[10].getValue(), themePreset.moduleColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 11, (SerializationStrategy)lazyArray[11].getValue(), themePreset.moduleOutlineColor);
        compositeEncoder.encodeSerializableElement(serialDescriptor, 12, (SerializationStrategy)lazyArray[12].getValue(), themePreset.nonActiveOutlineModuleColor);
    }

    public ThemePreset(int n, String string, Color color, Color color2, Color color3, Color color4, Color color5, Color color6, Color color7, Color color8, Color color9, Color color10, Color color11, Color color12, SerializationConstructorMarker serializationConstructorMarker) {
        if (8191 != (0x1FFF & n)) {
            PluginExceptionsKt.throwMissingFieldException(n, 8191, ThemePresetSerializer.INSTANCE.getDescriptor());
        }
        this.id = string;
        this.primaryColor = color;
        this.secondaryColor = color2;
        this.blurColor = color3;
        this.widgetBlurColor = color4;
        this.outlineColor = color5;
        this.textColor = color6;
        this.nonactiveTextColor = color7;
        this.slidersColor = color8;
        this.nonActiveModuleColor = color9;
        this.moduleColor = color10;
        this.moduleOutlineColor = color11;
        this.nonActiveOutlineModuleColor = color12;
    }

    private static final KSerializer serializer() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer2() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer3() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer4() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer5() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer6() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer7() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer8() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer9() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer10() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer11() {
        return new ColorSerializer();
    }

    private static final KSerializer serializer12() {
        return new ColorSerializer();
    }

    public static final Lazy[] get$childSerializers() {
        return $childSerializers;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ThemePreset.a = ZkmKeys.a(-6889449822042566940L, -4261506346066865672L, MethodHandles.lookup().lookupClass()).a(79524301000234L);
                        var20 = ThemePreset.a ^ 63985561554119L;
                        ThemePreset.h = new HashMap<K, V>(13);
                        ThemePreset.zkmSetFlag(null);
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
                        var18_3 = new String[41];
                        var16_4 = 0;
                        var15_5 = "sm\u00e8\u0091\u00a4\u00fb\u00e6\u00d8>\u0084@_\u00ce\u008b?\u00d6\u009fJ\u0015\u00bb:\u00e6\u00e3m\u00b3\u00fb\u00b2\u00ce\u000f~\u009e\u00b4(\u00eb\b\u0093\u00dd5\u0090\u0005\u00ca\u00aa\u00f8\u00cc+B\u00b8\u00f50\u00c5\u009ejvX\u0018=\u008df\u00c8!=\u00a9\u00d5]\u00c83\"\u00ce\u00d5Z+\u00f4g(:\u00d8\u0085\u00a3\u0013K\u0095%`$\u001f\u0005\u00eeX\u009f>\u00d5\u00cfob\u00f0Yv\u00ac\u0006\u009c\u00e8\u000f\u00df\n\u00b1\u001a\u00a4\u00a9\u0016\n\u0095b\u00fe)\u0018\u00f2y\u0011+\u00a9\u00b3vM;Bl\u009e\u00f5\u009b\u0000\u0010\u00ea\u00e2\u001b\u009eq\\R\u001c\u0018\u001d\u0015\u00f8\u0005\u00d0}\u009a\u007f_fb8\u00ce\u00ba\\X\u0091\u00a2\u00ea\u00f3\u0003]\u0080\u00f08e\u0015\u0085,\u00e7\u0012\u0013\u00aaDA\u0093Q\u001c<\u0097$\u00da\u00b1\u0003\u00b9\u00e3r\u0086v\u00f9\u00ee.\u0013O\u0018\u0082NN\u00bc\u00b2\u00be\u00a7_5\u0014\u00e0\u00dc\u009f\u00c2\u00bb\u00df\u00bb\u00b5K\u00e1\u00cd*\u00c4\u00b4\u00a2r\u0018\u00e7`U\u00b7\u00e4[u\r\u00db\u00fd\u00c6(]ZI\u00a9\u0007\u00d2\u00d6\u00d3+)\u0006\u0091\u0018\u00163; U<Aw\u001fv\u009f\u00f4\u0082\u00f55I%\u00c6\u00db\u00f3j\u008fq\u00e8\u0018)j\u00d9\u008b\u001a\u0084\u00a4\u001a\u00ab\u00da\u00fa>\u00a5\u0085&\u001d\u001d\u0098n\u00a1\u0090>\n\u00fa \u00a8\u00c7\u00c5\u00c2D\u00d8\u00bb56\u008f{\"\u0007\u00c3\u0096\u00e6\u00c4u\u00cb\r\u00b77\u00f3gb\u00f7\u00f9s\u0089\u0088\"\u008d\u0018\u00b8\b\f\u00f2s\u00ed\u00d1G\u00c8\u00b2\u0018\u00ff\u0094W\u0094z\u00be\u00a3\\<\u0095\u0082Dn\u0018\u00a3\"\u00a5#\u008b\u0084^\nY\u00ce\u008bu\u00cex\u00bd\u0098P\u0018p\u00fb\u00ad\u008e\u000fP(\\Q\u00ba\t\u0087\u00c9Z\"\u00a4!S\u008f\u001e_H\u001e\u00f8\u00c7f\u00e6\u00dfQ\u0087\u007f\u00e4\u0013\u00b3BL\u00c5\u0091\u0095\u00ea\u007f\bHa'\u00be\u00d6 F7\u00fae\u00a8\u0094\u00c3(\u0098Zog\u00e6\u00e9\u00fd_=\u0083#\u001b/\u00e1\u00c22d\u00c72@\u00dc\u00f47E\u0018\u0094\u00ec\b\u00be\u00e1\u0086\u00f4\u00efu\u0010l[U\u00a3%$\u0014{\u008f\u00dc\u007f\u00e5:o(\u00c7\u00b0\u0081_\u00e7\u00cf\u0090\u00d6\u00b9\u0010\u00ef\u00c5\u00a3\u00c5L\u009c\u00fc\u00dce \u00ab\u0019\u00f6yj*\u00f7\u0016\u009ft#u\u00ae\u00e2\u0002\u00ea\u0084\u008cy\u00f4 x\u00c4Y\u00bb8\u00f7F\u00b1\u00b0T\u00fc\u0007\u00b2\u0018\u0087_\u00c7\u00ef\u00bf3\tP\u00b4\u00d2I8\u00f5\u00da\u00e0\u00e8\u00f8z\u0018b\u009a!\u00d7\u0089Yy\u00f8\u00d3\u0001\u008d\u0093my\u0098\u00b5h\u00a9g\u00f5\u0083\u00d1\u001598(L\u00c6.\u00b1\u00a6\u00a4<\u009d\u00a8\u0099B\u00c0\u00d2wHv5\u001b|\u00b2\u00c9\u00ac\u00f5\u00f3qM\u00d9\u0004\u00c2\u0093\u00c8!Nl\u00169*\u0095\u0098\u001c\u00ca\u0096\u0081\u00a1\r\u000b\u0081|7\u00b8`#\u0011\u0017o\u0018\u00b0M\u00e8@!\u00d5\u00c6Z\u0001[m\u00e2\u00ad>\u00e3\u0018S;\f\u00e2\u00ff\u00c4\u00e0L \u001d\u0013\u00f3\u00a7\u00cd\u00ea\u00f8\u00df\u00fdq\u001eu3c'\u0099\u00ce}*\u00b3\u0097\u0004\u001c#\u00abV\u00e7\u00a4<\u00b6<\u00fa 0U\u001c\u00dc,\u00c3\u00834v\u0018\u00ff:\u001d\u0084\u00afg.\u00fac\u00ad\u0083\u00b34P\"\u00a8\u00a6#\u00b8\u00ffj\u0091\u0010\u00b8\u009fa\u0000mv\u00c0\u00f6^\u00c71\u00b0\u00c8\u0019\u009a\\0Y\\\u0015\u00e2\u00b9\rR\u00ca\u0092\u00a7B\u0012\u00dd\u00dc\u00b5\u00d8\u00ba8\u00ec\b\u009aE~\u0000\u0004H^\u00a5\u00ff\u0019\u0004i\u00a57\u0098}v[-\u0082U\u0080\u0094\u00aa\u00d3\u007f=0 \u00ca\u0015\u00c5\u00cf\u0003\u00e5\u009e\u00eadi\u0007\u0016-L\u00e8\u001f\u00c6\u00ef\u00dc\u00e1z\u0016\u0093c\u00e7\u00d4\u00ee\u00f9\u0092\u00a0\u009d\u00980\u00d2\u00d5\u00f4\u00d6c\u00bb\u00e1\u00f6\u00d4]\u00ac\u00a6\u008c\u0089r\u0084\u00c2C\u00a1r\"\u0003\u00e7\u0005\u0000$\u008dy\u00f6N\t\u00dd\u008a.\u0092 d\u00bc\u00af\u00bd\u0087%L\u0089a\u00046M0\u001e3W\u00abS\u0087&e6\"\u0012[P\u008b\u0088\u0091\u0011\u00f43\u00dc`\u00f6\u00f6.\u008fw\u0096\u009eo\u00b4\u001e\u00ce-4\u0087\u00a5A\u00fb\u00d7\u001b\u009a\u00a4t\f\u00d9|\u00c6\u009c\u0018N\u00cd\u0001O\u000e\u00bb\u0013\u008ci\\\u00f8\u00fd\u00c0\u00c5\u007fw\u00c8'\u00c7\u0004\u00d6\u0012\b\u0086\u0018\u00e8 \u00f4W\u000b\u00f3\u00b4\u0006\u00b4T\u0007)\u0083\u00d6\u00d5\"\u008f\u00d2\u00d0<\u00d4\u00b3qE \u00c3#\u00df\u00a2O<\u00bc\u001c\u0083\u00c3\u00b3\u00f3\u001f\u008cCF\u00b6\u00b7\u00a4SQ1\nZ\u0015\u0018\u00bcj&\u00a6\u00fa$\u0010LhP\u00aa\u00c9\r\u00bf\u0094\u00bd=X\u00f7#S\r\u00ad\u0018Q\u00d8\u0083\u000fu\u00c0@\n\u0004\u00bd\u00ba\u00eb\u00df\u0014C\u00dc\u00fa*l\u0095\u00e58Y\u008a(\u00a0\u00b76z\u0093\u00b2&\u001d\u0081{T%r\u00e6\u001b\u00d5\u00bfA\u0098\u00e9\u00f1\u00b1\u0000\u00db\u00bc\u00b8Z*\u00f9\u00ee\u00e4O1\u00afP]\u00bb\u0099\u00a2\u00ea(?m\u007f\u0016\u00a5\u0083N\u00ff\u00d8+*2\u00aeh\u00f1\u00c6\u00fd\u00cf\u00a8\u009c\u0092\u00ae\u00c6\u0003o\u0006\u009fJrv}\u009c\u00e9\u00ae\u00f38|'\u00f3\u00c7\u0010/\u00deI\\\u00c9\u00bc\u00e1e2\u00f7$f\u00ddK!\u00ce\u0010@\u00e2]\u00f5\u00fb\u00dc/:a\u008fv\u00a3\u00ce\u00de\u0007\u0091(I\u00ba\u00feC|\u00b2\u00ce\u00de|\u0095\u00ce\u00db\u00cf\u0003\u00b2\u0092b\u00b0\u001d\u000f6\u00ca\u0001\u00fa\u00e4\u00b5R\u000e\u00e5\u008c\u0017\u00d4\u00a5\u00f1\u00af\u0010|\u0003&q\u0018\u0017\u007f\u0095\u00d7\u00de\u00d8\u00e81)0\u00d1;_2\u009d\u00a1\u0089\u00f8vO.\u0089\u00d52\u0018\u00edv\u00c2P}\u000f+\u009c\u00d2\u00c8\u0004\u00cd4N[\rO\u0092\u00f2B\u00e8\u00c9\u00a0r";
                        var17_6 = 1254;
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
                            var18_3[var16_4++] = ThemePreset.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00f9\u000e3\u0015_\u009d\u00dd}\u00e8\u00cc\\\u00c1\u008f\u0088\u00ad\u00bb\u00a4\u00129\u00a5\u0085\u00ceI\u0096JC\u0095\u00b0a\u0004\u0097\u0096\u00efq\be6\u00e4\u009f\u0002 RA\u00c2\\\u00ca\u00cbBU[\u00e4\"E\u00a44.$Vm\u00cc\u00a4\u00d8\n\u0002\u000fNt\u00bd\u00a5l\u00c6\u0005\u00a5";
                            var17_6 = 73;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = ThemePreset.zkmDecodeUtf8(var19_9).intern();
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
                ThemePreset.c = var18_3;
                ThemePreset.g = new String[41];
                ThemePreset.o = new HashMap<K, V>(13);
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
                var6_12 = new long[28];
                var3_13 = 0;
                var4_14 = "F\u00b2m*\u001e\u00afa\u0083{v\u001dD\u009e\u00ec\u0018`\u00ed|\u00fd\u0003~~\u0092[\u0096Z\u00ef\u008f7\u0092\u00ea\u00d2\u00ba\u00e1\u00a1\u00c9M\u00b1\u00ec\u00d2\u00e7\u00fc\u008b\u00c2\u00f1\u0088\u00d7\u00aa[\u00a4\u0087\u00eaN\u00cc\u00b3\u009e\u008d\u00df\u00a6\r\u00d9\u00ae>q\u009f\u00f1\u00bb\nN\u00f6\u000b\u00b1\u00ff\u00e3\u00aa\u00fct\u009f\u00c1\u00bf\u00af\u0097\u0099\u0098\u0019\u0081\u0004\u00d2B qk\u0098*\u00d1\u00f2\u00cfQ\r\u00ee\u001f\u00ed'C\u009f\u00e6\u00dd\u0091\u008b8KE,\u0090Kh4\u008bz\u009b\u00e1\u00ecvIQZ\u00c0\u00e1\u000f\u00f9\u00ed9\u0014\u000f=\u009a;I\u00e5\u00f4\u00b5\u009b\u008d[\u00a4\u00d2'a\u009aQa\u0089\fWb\u00cbC%\u00ab+\u00c6\u00c5\u0086T\u0081(\u00e2O\u00ae\u001a\u0007\u00bd\u00101\u00f9\u00ffx\u008c\u00cf\u0086\u00f4*\u00b51\u00c0\u00ce\u00ec\u00e6c\u001fo\t\u00b8 \u009f,\u00cc\u00b2}\u00b9C\u00f5\r\u0081xb\u00e5g";
                var5_15 = 208;
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
                    var4_14 = "\u00f24\u0090\u00a2\u00f2\u001bW o_W\u00f6\u001e\u00c75\u0087";
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
        ThemePreset.k = var6_12;
        ThemePreset.m = new Integer[28];
        ThemePreset.Companion = new ThemePreset$Companion(null);
        var22_20 = new Lazy[]{null, LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer2(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer3(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer4(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer5(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer6(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer7(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer8(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer9(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer10(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer11(), ()Lkotlinx/serialization/KSerializer;)()), LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0<KSerializer>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, serializer12(), ()Lkotlinx/serialization/KSerializer;)())};
        ThemePreset.$childSerializers = var22_20;
    }

    public static void zkmSetFlag(String[] stringArray) {
        E = stringArray;
    }

    public static String[] zkmGetFlag() {
        return E;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x58A4;
        if (g[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/o_", exception);
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
            ThemePreset.g[n2] = ThemePreset.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return g[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ThemePreset.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/o_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6ABD;
        if (m[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = k[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])o.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    o.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/o_", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ThemePreset.m[n2] = n3;
        }
        return m[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ThemePreset.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/o_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ThemePreset.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ThemePreset.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

