/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.awt.Color;
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
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.render.ThemePreset;
import su.catlean.zkm.ZkmKeys;

@Deprecated(message="This synthesized declaration should not be used directly", level=DeprecationLevel.HIDDEN)
public final class ThemePresetSerializer
implements GeneratedSerializer {
    @NotNull
    public static final ThemePresetSerializer INSTANCE;
    @NotNull
    private static final SerialDescriptor serialDescriptor;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private ThemePresetSerializer() {
    }

    @Override
    public final void serialize(@NotNull Encoder encoder, @NotNull ThemePreset themePreset) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(themePreset, "value");
        SerialDescriptor serialDescriptor = ThemePresetSerializer.serialDescriptor;
        CompositeEncoder compositeEncoder = encoder.beginStructure(serialDescriptor);
        ThemePreset.write$Self(themePreset, compositeEncoder, serialDescriptor);
        compositeEncoder.endStructure(serialDescriptor);
    }

    @NotNull
    public final ThemePreset deserialize2(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = ThemePresetSerializer.serialDescriptor;
        boolean bl = true;
        int n = 0;
        String string = null;
        Color color = null;
        Color color2 = null;
        Color color3 = null;
        Color color4 = null;
        Color color5 = null;
        Color color6 = null;
        Color color7 = null;
        Color color8 = null;
        Color color9 = null;
        Color color10 = null;
        Color color11 = null;
        Color color12 = null;
        CompositeDecoder compositeDecoder = decoder.beginStructure(serialDescriptor);
        Lazy[] lazyArray = ThemePreset.get$childSerializers();
        if (compositeDecoder.decodeSequentially()) {
            string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
            color = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), color);
            color2 = compositeDecoder.decodeSerializableElement(serialDescriptor, 2, (DeserializationStrategy)lazyArray[2].getValue(), color2);
            color3 = compositeDecoder.decodeSerializableElement(serialDescriptor, 3, (DeserializationStrategy)lazyArray[3].getValue(), color3);
            color4 = compositeDecoder.decodeSerializableElement(serialDescriptor, 4, (DeserializationStrategy)lazyArray[4].getValue(), color4);
            color5 = compositeDecoder.decodeSerializableElement(serialDescriptor, 5, (DeserializationStrategy)lazyArray[5].getValue(), color5);
            color6 = compositeDecoder.decodeSerializableElement(serialDescriptor, 6, (DeserializationStrategy)lazyArray[6].getValue(), color6);
            color7 = compositeDecoder.decodeSerializableElement(serialDescriptor, 7, (DeserializationStrategy)lazyArray[7].getValue(), color7);
            color8 = compositeDecoder.decodeSerializableElement(serialDescriptor, 8, (DeserializationStrategy)lazyArray[8].getValue(), color8);
            color9 = compositeDecoder.decodeSerializableElement(serialDescriptor, 9, (DeserializationStrategy)lazyArray[9].getValue(), color9);
            color10 = compositeDecoder.decodeSerializableElement(serialDescriptor, 10, (DeserializationStrategy)lazyArray[10].getValue(), color10);
            color11 = compositeDecoder.decodeSerializableElement(serialDescriptor, 11, (DeserializationStrategy)lazyArray[11].getValue(), color11);
            color12 = compositeDecoder.decodeSerializableElement(serialDescriptor, 12, (DeserializationStrategy)lazyArray[12].getValue(), color12);
            n = 8191;
        } else {
            block16: while (bl) {
                int n2 = compositeDecoder.decodeElementIndex(serialDescriptor);
                switch (n2) {
                    case -1: {
                        bl = false;
                        continue block16;
                    }
                    case 0: {
                        string = compositeDecoder.decodeStringElement(serialDescriptor, 0);
                        n |= 1;
                        continue block16;
                    }
                    case 1: {
                        color = compositeDecoder.decodeSerializableElement(serialDescriptor, 1, (DeserializationStrategy)lazyArray[1].getValue(), color);
                        n |= 2;
                        continue block16;
                    }
                    case 2: {
                        color2 = compositeDecoder.decodeSerializableElement(serialDescriptor, 2, (DeserializationStrategy)lazyArray[2].getValue(), color2);
                        n |= 4;
                        continue block16;
                    }
                    case 3: {
                        color3 = compositeDecoder.decodeSerializableElement(serialDescriptor, 3, (DeserializationStrategy)lazyArray[3].getValue(), color3);
                        n |= 8;
                        continue block16;
                    }
                    case 4: {
                        color4 = compositeDecoder.decodeSerializableElement(serialDescriptor, 4, (DeserializationStrategy)lazyArray[4].getValue(), color4);
                        n |= 0x10;
                        continue block16;
                    }
                    case 5: {
                        color5 = compositeDecoder.decodeSerializableElement(serialDescriptor, 5, (DeserializationStrategy)lazyArray[5].getValue(), color5);
                        n |= 0x20;
                        continue block16;
                    }
                    case 6: {
                        color6 = compositeDecoder.decodeSerializableElement(serialDescriptor, 6, (DeserializationStrategy)lazyArray[6].getValue(), color6);
                        n |= 0x40;
                        continue block16;
                    }
                    case 7: {
                        color7 = compositeDecoder.decodeSerializableElement(serialDescriptor, 7, (DeserializationStrategy)lazyArray[7].getValue(), color7);
                        n |= 0x80;
                        continue block16;
                    }
                    case 8: {
                        color8 = compositeDecoder.decodeSerializableElement(serialDescriptor, 8, (DeserializationStrategy)lazyArray[8].getValue(), color8);
                        n |= 0x100;
                        continue block16;
                    }
                    case 9: {
                        color9 = compositeDecoder.decodeSerializableElement(serialDescriptor, 9, (DeserializationStrategy)lazyArray[9].getValue(), color9);
                        n |= 0x200;
                        continue block16;
                    }
                    case 10: {
                        color10 = compositeDecoder.decodeSerializableElement(serialDescriptor, 10, (DeserializationStrategy)lazyArray[10].getValue(), color10);
                        n |= 0x400;
                        continue block16;
                    }
                    case 11: {
                        color11 = compositeDecoder.decodeSerializableElement(serialDescriptor, 11, (DeserializationStrategy)lazyArray[11].getValue(), color11);
                        n |= 0x800;
                        continue block16;
                    }
                    case 12: {
                        color12 = compositeDecoder.decodeSerializableElement(serialDescriptor, 12, (DeserializationStrategy)lazyArray[12].getValue(), color12);
                        n |= 0x1000;
                        continue block16;
                    }
                }
                throw new UnknownFieldException(n2);
            }
        }
        compositeDecoder.endStructure(serialDescriptor);
        return new ThemePreset(n, string, color, color2, color3, color4, color5, color6, color7, color8, color9, color10, color11, color12, null);
    }

    @Override
    @NotNull
    public final SerialDescriptor getDescriptor() {
        return serialDescriptor;
    }

    @NotNull
    public final KSerializer[] childSerializers() {
        Lazy[] lazyArray = ThemePreset.get$childSerializers();
        KSerializer[] kSerializerArray = new KSerializer[]{StringSerializer.INSTANCE, lazyArray[1].getValue(), lazyArray[2].getValue(), lazyArray[3].getValue(), lazyArray[4].getValue(), lazyArray[5].getValue(), lazyArray[6].getValue(), lazyArray[7].getValue(), lazyArray[8].getValue(), lazyArray[9].getValue(), lazyArray[10].getValue(), lazyArray[11].getValue(), lazyArray[12].getValue()};
        return kSerializerArray;
    }

    @NotNull
    public KSerializer[] typeParametersSerializers() {
        return GeneratedSerializer.super.typeParametersSerializers();
    }

    @Override
    public void serialize(Encoder encoder, Object object) {
        this.serialize(encoder, (ThemePreset)object);
    }

    @Override
    public Object deserialize(Decoder decoder) {
        return this.deserialize2(decoder);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        ThemePresetSerializer.a = ZkmKeys.a(-2333090486536464334L, -4329924625922885147L, MethodHandles.lookup().lookupClass()).a(277569783145539L);
                        var20 = ThemePresetSerializer.a ^ 140354012465302L;
                        ThemePresetSerializer.d = new HashMap<K, V>(13);
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
                        var15_5 = "WJ\u001d1<t\u00d0\u001a\b\u00fa\u00c4\u00bc\u00f2&\u00dc|\u00b4\u00f0\u00b6\u00e1$\u0097\u0093Q\u0005\u0085\u00aa\u00deP\u0088\u001b\u00e0 \u00a11\u00e5Oc=D\u00a2\u00a9\u00b1\u00b9\u00ee\u001eR}FZ\u00b8\u00be\u00fa\u0005\u00a8\u00eb3M\u00a9|\u00f1\u00bdC\u0096\u00c0(jE<T\\\u00f4<T\u0080\u0089\u00ef\u00bc\u00c7\u00eb`\u000f\u001a\u0003\u00f6\u0019\u001ak\u00ad\u000e[\u0019\u0083\u0017HA\u00de\u00c30\u00a0\u00c6\u00b4\u00f9\u0093\u00c3D\u0010$\u0089\u0002\u00d6W\u00eb\u001e6J\u00c1`\u00b2\u0082d\u00fe\u00aa\u0018OD\u00d1m\u0088\u0088k\u0096^k\u00d1M\u00d7/\u0082\u00feTG\u0001\u00fa/\u00e5\u00bf\u008e(\u000e\u00bb \u008f\u00act\u000e8\u00a4?\u00c9\u00b5\u00bb_\u009b\u00e4c\u0082\u00d2\u00d0\u009c\u009fc\u00ee\u00fbN%M\u00f0\u00bb\u00a4\u00ef\u0004\bwQ\u0014\u00be\u0003\u00f9\u0018\u00f3\u00ffI\u00e5\u00a40\u00a5\u00b1_d\u00c26\u0005C'\u00daW\u00edw$\u00b8B\u00f1s0\u0092r\u00f4p\u00b9\u00c8\u00e4e\u0004\\\u00eb\u00fe\u00f1\u00a6\u000e\u0019\u001c\u009e\u00bd\u00a42TrM\u00f7\u00f1\u008b\u00f3)\u00ea\u001d3\u0011t\u00e6\u007f/ u\u00d2\u00c4\u00d4\u0087l,\u00f2o\u00a7\u0018\u000f\u00c9v\u0005\u00fb%%\u00aa\u0003\u00b1t\u0095!\u0088oq\u00dc\u00ae+\u009b$\u00d19\u000b b\u00d4\u00cc\u0087\u00e4V\u00c0\u00b6\u00f1\f\u0007)\u009a\u00dd-\u00b6j\u001fsu\u00f7\u00b8\u00a6A}A\u00aa\u00a0k\u00e7v\u008f \u00ed\u00dctU\u00ed\u0094T\u00b3\u0088A[\u00fc\u00c0\u00f3\u00ef\u0019\u0006xV\u00f3\u00fb\u00aeaPNZ\u00f1\u00cb\u00ef.U \u0010T#\u00f6(\u0005t\u00a9\u00c6Wy\u00da\u00bf<\u0017*\u00b9\u0010\u0087\u00a846w\u00f3c\u00e7,w\u00ca\u001c\u009e\u00b6l\u00b2 '\u008b\u009cz&\u00f04c\u00e2\u00ca\u00bb\\\u00a2<\u0093\u00fd\u0086\u0094\u0082\u0017\u00f10\u00b4,\u0093\u00e6\u0005\u00a6\u0092\u0093\u00f2!\u0010Dq\u0097\u00d6B\u00dd\u009f\u00a5A\u00ec\u0084]\u00b8W\u00ed\u0099";
                        var17_6 = 438;
                        var14_7 = 32;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ThemePresetSerializer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0098L\u00a7\u0014\u0007,v\u0095\u00ab\u0019\u00cb\u008c\u0011iY\u008f\u000f\u00f5\u001d\u00be\u00fe\u00aaw\u0093\u0003\u00ab\u00b8\u00af!0\u00ce\u00b7\u00eb\u00ae\u00b2~]\u008d\u00f1i \u00ac\u0093\u0001\u0091g\u00bb\u00e1N \u0014a\u00a81Pa\u0096\u00e5i\u00dd\u00ab\u00fa\u00fb$\u0017o\u00c7\u00b1H\u001b\u00d5\u00c9.";
                            var17_6 = 73;
                            var14_7 = 40;
                            var13_8 = -1;
lbl34:
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
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = ThemePresetSerializer.zkmDecodeUtf8(var19_9).intern();
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
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                ThemePresetSerializer.b = var18_3;
                ThemePresetSerializer.c = new String[17];
                ThemePresetSerializer.g = new HashMap<K, V>(13);
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
                var6_12 = new long[34];
                var3_13 = 0;
                var4_14 = "\u00ea$.\u00a9\u00fc\u00bf+\u0005\u00ba{\u00a7\u00c6\u0092\u00c0*Q\u00c55SJ\u00d8\u008d\u00d3\u00cd\u00a2\u00fa\\j\u009c\u00e0p\t\u00d2J\u00c0\u0017X<\u00f2\u0096=\u00864\u00c4$>\u00bd\u00dbg,lY\u00dfI\u00a9|D\u00e3$\u00f0\u0083T\u00f6d\u0006\u00c9\b\u0085\u00ff\u00a5\u00f1\u00ee0ff\u000b\u00cb\u00dc1\u0094\u00d2O\u00c1e\u00b0Z5\u00bc\u0085@!i\u0003\u00cd\u0087(\u00d9\u00d0nV\u00be\u00cdQ\u00d5#\u00de:P\b\u00f5\u00f3\u001a~\u007fdR\u00fb\u00ca\u00dd\u00dc\u00da\u0092\u0010\u0093\\J\u00bd\u0096R\u00b3}.\u00f4\u00e7\u00e4\u00fe\u00c8%2\u000f\u00b2\u001d7\u00e2\u00ae\u00c2\u0080@\u00b5\u001f\u00d2S\u00d0\u00da\u00f1\u0085\u00f2\u008eW;\u00aaa\u00e9\u0095\u0014\r*\u009eV\u00ec\u0019\u00c1\u00d35\u00cc\u00f1\u00de\u0096\u009b\u00af\u0002a\u00f5\u00b8\u00af*\u009e:\u0096PN}\u00a3Rk\u0086\u0094\u00e7|\u0005\u0096\u00d0\u00bcS\u00db'.5\u00a4d.\u001eM\u00a0z\u0090$\u00b6\u00fcx\u00f2#\u009e\u00da\u0000\u00d8\u00c7\u00ad\u00192\u00b6Q/\u00b7\u00d8\u00f7#P\u008a\u00f1CO\u00ec-\u00b8\u0000\u0002\u00fa(\u00ca\u00ee\u00ec\u00b2\u001d\u00be\u0094";
                var5_15 = 256;
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
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00ec\u00d4\u008b^p\u00dd\u00e4N\u00bc\u00cdu\u0016Il\u0099D";
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
lbl91:
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
lbl104:
                // 1 sources

                ** continue;
            }
        }
        ThemePresetSerializer.e = var6_12;
        ThemePresetSerializer.f = new Integer[34];
        ThemePresetSerializer.INSTANCE = new ThemePresetSerializer();
        var22_20 = new PluginGeneratedSerialDescriptor("su.catlean.o_", ThemePresetSerializer.INSTANCE, 13);
        var22_20.addElement("id", false);
        var22_20.addElement("primaryColor", false);
        var22_20.addElement("secondaryColor", false);
        var22_20.addElement("blurColor", false);
        var22_20.addElement("widgetBlurColor", false);
        var22_20.addElement("outlineColor", false);
        var22_20.addElement("textColor", false);
        var22_20.addElement("nonactiveTextColor", false);
        var22_20.addElement("slidersColor", false);
        var22_20.addElement("nonActiveModuleColor", false);
        var22_20.addElement("moduleColor", false);
        var22_20.addElement("moduleOutlineColor", false);
        var22_20.addElement("nonActiveOutlineModuleColor", false);
        ThemePresetSerializer.serialDescriptor = var22_20;
    }

    private static UnknownFieldException zkmRethrow(UnknownFieldException unknownFieldException) {
        return unknownFieldException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5EF7;
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
                throw new RuntimeException("su/catlean/jx", exception);
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
            ThemePresetSerializer.c[n2] = ThemePresetSerializer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ThemePresetSerializer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/jx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x60FB;
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
                throw new RuntimeException("su/catlean/jx", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            ThemePresetSerializer.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = ThemePresetSerializer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/jx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ThemePresetSerializer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(ThemePresetSerializer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

