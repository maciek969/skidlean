/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.discord.DiscordRpc;
import su.catlean.discord.ObservableSetting;
import su.catlean.discord.PresenceAssets;
import su.catlean.discord.PresenceCycler;
import su.catlean.discord.RichPresence;
import su.catlean.discord.Timestamps;
import su.catlean.module.Module;
import su.catlean.ui.Link;
import su.catlean.zkm.ZkmKeys;

public final class DiscordRpcSettings
extends DiscordRpc {
    @NotNull
    public static final DiscordRpcSettings INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final ReadWriteProperty appId$delegate;
    @NotNull
    private static final ReadWriteProperty details$delegate;
    @NotNull
    private static final ReadWriteProperty state$delegate;
    @NotNull
    private static final ReadWriteProperty largeImage$delegate;
    @NotNull
    private static final ReadWriteProperty smallImage$delegate;
    @NotNull
    private static final ReadWriteProperty button1$delegate;
    @NotNull
    private static final ReadWriteProperty button2$delegate;
    private static String[] s;
    private static final long b;
    private static final long[] m;
    private static final Integer[] n;
    private static final Map p;

    private DiscordRpcSettings() {
    }

    @Nullable
    public final Long getAppId() {
        return (Long)appId$delegate.getValue(this, delegatedProperties[0]);
    }

    public final void setAppId(@Nullable Long l) {
        appId$delegate.setValue(this, delegatedProperties[0], l);
    }

    @Nullable
    public final String getDetails() {
        return (String)details$delegate.getValue(this, delegatedProperties[1]);
    }

    public final void setDetails(@Nullable String string) {
        details$delegate.setValue(this, delegatedProperties[1], string);
    }

    @Nullable
    public final String getState() {
        return (String)state$delegate.getValue(this, delegatedProperties[2]);
    }

    public final void setState(@Nullable String string) {
        state$delegate.setValue(this, delegatedProperties[2], string);
    }

    @Nullable
    public final Pair getLargeImage() {
        return (Pair)largeImage$delegate.getValue(this, delegatedProperties[3]);
    }

    public final void setLargeImage(@Nullable Pair pair) {
        largeImage$delegate.setValue(this, delegatedProperties[3], pair);
    }

    @Nullable
    public final Pair getSmallImage() {
        return (Pair)smallImage$delegate.getValue(this, delegatedProperties[4]);
    }

    public final void setSmallImage(@Nullable Pair pair) {
        smallImage$delegate.setValue(this, delegatedProperties[4], pair);
    }

    @Nullable
    public final Pair getButton1() {
        return (Pair)button1$delegate.getValue(this, delegatedProperties[5]);
    }

    public final void setButton1(@Nullable Pair pair) {
        button1$delegate.setValue(this, delegatedProperties[5], pair);
    }

    @Nullable
    public final Pair getButton2() {
        return (Pair)button2$delegate.getValue(this, delegatedProperties[6]);
    }

    public final void setButton2(@Nullable Pair pair) {
        button2$delegate.setValue(this, delegatedProperties[6], pair);
    }

    @NotNull
    public final RichPresence getButtons() {
        PresenceAssets presenceAssets;
        Object object;
        List list = new ArrayList();
        Pair pair = this.getButton1();
        if (pair != null) {
            object = pair;
            list.add(new Link((String)((Pair)object).getFirst(), (String)((Pair)object).getSecond()));
        }
        Pair pair2 = this.getButton2();
        if (pair2 != null) {
            object = pair2;
            list.add(new Link((String)((Pair)object).getFirst(), (String)((Pair)object).getSecond()));
        }
        String string = this.getDetails();
        String string2 = this.getState();
        if (this.getLargeImage() != null || this.getSmallImage() != null) {
            Pair pair3 = this.getLargeImage();
            Pair pair4 = this.getLargeImage();
            Pair pair5 = this.getSmallImage();
            Pair pair6 = this.getSmallImage();
            presenceAssets = new PresenceAssets(pair3 != null ? (String)pair3.getFirst() : null, pair4 != null ? (String)pair4.getSecond() : null, pair5 != null ? (String)pair5.getFirst() : null, pair6 != null ? (String)pair6.getSecond() : null);
        } else {
            presenceAssets = null;
        }
        object = presenceAssets;
        DefaultConstructorMarker defaultConstructorMarker = null;
        Long l = null;
        long l2 = this.getEpochSecond();
        Timestamps timestamps = new Timestamps(l2, l, defaultConstructorMarker);
        return new RichPresence(string, string2, timestamps, (PresenceAssets)object, list);
    }

    private final ReadWriteProperty observable(boolean bl, Object object) {
        Object object2 = object;
        return new ObservableSetting(object2, bl);
    }

    static /* synthetic */ ReadWriteProperty observable$default(DiscordRpcSettings discordRpcSettings, boolean bl, Object object, int n, Object object2) {
        if ((n & 1) != 0) {
            bl = false;
        }
        if ((n & 2) != 0) {
            object = null;
        }
        return discordRpcSettings.observable(bl, object);
    }

    public final void enable() {
        PresenceCycler.INSTANCE.Z(0);
        PresenceCycler.INSTANCE.B(true);
    }

    public final void disable() {
        PresenceCycler.INSTANCE.B(false);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        DiscordRpcSettings.b = ZkmKeys.a(4218366314374231825L, 1441945716120668429L, MethodHandles.lookup().lookupClass()).a(199741603584196L);
                        var20 = DiscordRpcSettings.b ^ 120182687399445L;
                        var12_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var13_2 = 1; var13_2 < 8; ++var13_2) {
                            v2 = v2;
                            v2[var13_2] = (byte)(var20 << var13_2 * 8 >>> 56);
                        }
                        var12_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var11_3 = new String[14];
                        var17_4 = 0;
                        var16_5 = "\u0011\u00a1\u0099\u00f3$\u00eb\u00bc\u00d9\u0010\u00eaW\u00bd\u00b2\u0090\u00f5\u000b9\u00ab%s\u00d87\u00158\u00f1 \u00b5H\u00beu\u0091\u00e60~d\u00f5\u0086\u001c\u00a4\u00f1\u0011&\u00bd_D\u001f\u00be\u0012_\u00f0\u0012\u00f0\u00d0d\u00de\u00af\u00bc\u00d6 .\u00f3\u00d9_@a\u00d3\u00a8\u00de\u0005\u00f8\u0003\u001ai\u001c\u008e\u00bb\u009a\u0001\u00eavc\u000e\"ko\u0004\u0087*\u00aa\u00b6? \u00aa\u00c0 \u00fc\u00e2\u00e8\u00fd\f\u00bfe<\u0018\u00d8\u00d8${\u00a6U\u0094\u00abE\u0001]P\u00dcG\u00dehq\u00d5\u008a\u0007\b\u008fV\u00e5\u00e9\u00aag\n\u008c \u00bf+\u00c7\u00dc\u00c0\u00e3\u0097\u00b4\u0090k\u00b3\u0017V0\u0012\u00d4.\u00dft\u00e4\u00f3i\u00e2\u0011\u00e8\u00a2\u000b\u00ab\u0084]\u00b5\u00ea \u0016E'\u009fg\u0093\u00e4\u00fcbZ\\\u00f2\u001d\u00fe3\u00be\u0003\u00a2\u00a4\u00a1\u0083f\u0097~?\u00f9F@V\u00fd\u00b2\u00ba\u0010\u00d8\u00dd\u00a5\u0092\u009cf\u00d0\u00da\u0090\u00f4\u00ba=n\u00fc\u00be\u00fc \u00b5H\u00beu\u0091\u00e60~\u0014I\u009d\u00c9\u000e\u0013\u00bb\u00a0\u0087%\u00c0\u00af1\u00e9(\u00e4}\u00ba|f3\u008d\u008d\u0091 \u00d6a\u000f\f\u00a5\u001f5J(%\u0088+w_\u00e4\u00fdP\u0007\u0086\u00c6t\u0004?_S\u00f0lC\u0096\u0013\u00f8*\b\u00d0;\u00f9Q&\"\u00d9H";
                        var18_6 = 291;
                        var15_7 = 8;
                        var14_8 = -1;
lbl19:
                        // 2 sources

                        while (true) {
                            v3 = ++var14_8;
                            v4 = var16_5.substring(v3, v3 + var15_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl24:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = DiscordRpcSettings.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            var16_5 = "\u00ab\u0094\u00a8F\u00f1\t7\u00f2\b\u00c6@\u00d8\u00d0\u00e0\u0086\u0000\u00b0";
                            var18_6 = 17;
                            var15_7 = 8;
                            var14_8 = -1;
lbl33:
                            // 2 sources

                            while (true) {
                                v6 = ++var14_8;
                                v4 = var16_5.substring(v6, v6 + var15_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl38:
                        // 1 sources

                        while (true) {
                            var11_3[var17_4++] = DiscordRpcSettings.zkmDecodeUtf8(var19_9).intern();
                            if ((var14_8 += var15_7) < var18_6) {
                                var15_7 = var16_5.charAt(var14_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var19_9 = var12_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl50:
                        // 1 sources

                        ** continue;
                    }
                }
                DiscordRpcSettings.p = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u00ea\u00fc\u00fc\u0011z9\u00ee\u009eWI\u00f8\u00f0\u00fc\u008f\u0001\u008f$\u00eaB\u00af\u0006q\u00a02";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl70:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        DiscordRpcSettings.m = var6_12;
        DiscordRpcSettings.n = new Integer[3];
        var28_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(DiscordRpcSettings.class, var11_3[0], var11_3[4], 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DiscordRpcSettings.class, var11_3[12], var11_3[10], 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DiscordRpcSettings.class, var11_3[13], var11_3[3], 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DiscordRpcSettings.class, var11_3[8], var11_3[6], 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DiscordRpcSettings.class, var11_3[1], var11_3[7], 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DiscordRpcSettings.class, var11_3[5], var11_3[9], 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DiscordRpcSettings.class, var11_3[11], var11_3[2], 0))};
        DiscordRpcSettings.delegatedProperties = var28_20;
        DiscordRpcSettings.INSTANCE = new DiscordRpcSettings();
        DiscordRpcSettings.appId$delegate = DiscordRpcSettings.observable$default(DiscordRpcSettings.INSTANCE, true, null, 2, null);
        DiscordRpcSettings.details$delegate = DiscordRpcSettings.observable$default(DiscordRpcSettings.INSTANCE, false, null, 3, null);
        DiscordRpcSettings.state$delegate = DiscordRpcSettings.observable$default(DiscordRpcSettings.INSTANCE, false, null, 3, null);
        DiscordRpcSettings.largeImage$delegate = DiscordRpcSettings.observable$default(DiscordRpcSettings.INSTANCE, false, null, 3, null);
        DiscordRpcSettings.smallImage$delegate = DiscordRpcSettings.observable$default(DiscordRpcSettings.INSTANCE, false, null, 3, null);
        DiscordRpcSettings.button1$delegate = DiscordRpcSettings.observable$default(DiscordRpcSettings.INSTANCE, false, null, 3, null);
        DiscordRpcSettings.button2$delegate = DiscordRpcSettings.observable$default(DiscordRpcSettings.INSTANCE, false, null, 3, null);
    }

    public static void zkmSetFlag(String[] stringArray) {
        s = stringArray;
    }

    public static String[] java_lang_String_arr_zkmGetFlag() {
        return s;
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

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x77D6;
        if (DiscordRpcSettings.n[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = m[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])p.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    p.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/os", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            DiscordRpcSettings.n[n2] = n3;
        }
        return DiscordRpcSettings.n[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = DiscordRpcSettings.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/os" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DiscordRpcSettings.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

