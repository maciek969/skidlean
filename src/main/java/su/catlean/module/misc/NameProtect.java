/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.network.PlayerListEntry;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.render.StringDecomposerEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.NameProtectReplaceMode;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.util.FriendManager;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class NameProtect
extends Module {
    @NotNull
    public static final NameProtect INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final TextSetting name$delegate;
    @NotNull
    private static final ModeSetting replace$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;

    private NameProtect() {
        super("name-protect", Categories.getMisc(), null, null);
    }

    @NotNull
    public final String getName() {
        return (String)name$delegate.getValue(this, delegatedProperties[0]);
    }

    @NotNull
    public final NameProtectReplaceMode getReplace() {
        return (NameProtectReplaceMode)((Object)replace$delegate.getValue(this, delegatedProperties[1]));
    }

    @Flow
    private final void onStringDecomposer(StringDecomposerEvent stringDecomposerEvent) {
        long l = a ^ 0x5CE3F406A48BL;
        int n = (int)((l ^ 0x75C5B108C22DL) >>> 48);
        String string = stringDecomposerEvent.getString();
        stringDecomposerEvent.setString(this.replaceName((char)n, string));
    }

    @NotNull
    public final String replaceName(char c, @NotNull String string) {
        String string2;
        block5: {
            int n = 0;
            int n2 = 0;
            long l = ((long)c << 48 | (long)n << 32 >>> 16 | (long)n2 << 48 >>> 48) ^ a;
            Intrinsics.checkNotNullParameter(string, "text");
            if (!this.isInGame() || !this.getEnabled()) {
                return string;
            }
            String string3 = Globals.getPlayer().getGameProfile().name();
            Intrinsics.checkNotNullExpressionValue(string3, (String)((Object)NameProtect.zkmBootstrap("h", (int)24411, (long)(0x61EA8A5D6C55E080L ^ l))));
            string2 = StringsKt.replace$default(string, string3, this.getName(), false, 4, null);
            if (this.getReplace() == NameProtectReplaceMode.ONLY_SELF) break block5;
            Collection<PlayerListEntry> collection = Globals.getNetworkHandler().getPlayerList();
            Intrinsics.checkNotNullExpressionValue(collection, (String)((Object)NameProtect.zkmBootstrap("h", (int)12653, (long)(0x323A8016AF2C8EB2L ^ l))));
            Iterable iterable = collection;
            for (Object t : iterable) {
                block7: {
                    PlayerListEntry playerListEntry;
                    block6: {
                        playerListEntry = (PlayerListEntry)t;
                        if (c < '\u0000') break;
                        if (INSTANCE.getReplace() == NameProtectReplaceMode.ALL) break block6;
                        String string4 = playerListEntry.getProfile().name();
                        Intrinsics.checkNotNullExpressionValue(string4, (String)((Object)NameProtect.zkmBootstrap("h", (int)7294, (long)(0x45E6C92F6CC4A3A6L ^ l))));
                        if (!FriendManager.INSTANCE.isFriend(string4)) break block7;
                    }
                    String string5 = playerListEntry.getProfile().name();
                    Intrinsics.checkNotNullExpressionValue(string5, (String)((Object)NameProtect.zkmBootstrap("h", (int)7294, (long)(0x45E6C92F6CC4A3A6L ^ l))));
                    string2 = StringsKt.replace$default(string2, string5, INSTANCE.getName(), false, 4, null);
                }
                if (c >= '\u0000') continue;
            }
        }
        return string2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        NameProtect.a = ZkmKeys.a(2878155593475320591L, -336301487634253425L, MethodHandles.lookup().lookupClass()).a(82147503996816L);
                        var20 = NameProtect.a ^ 125678748250339L;
                        NameProtect.e = new HashMap<K, V>(13);
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
                        var15_5 = "\u00a1\u0000{.\u00ec\u00c2+W\u0018\\\u00ce\u0084\u00d1\u0089e\u001a\u0018\u00ff\u00b1\u00dd\u0011>(\u0094\u008b0\u00ba\u0082T\u001a\u0007\u00e81\u00ed\u0090(^W9\u00b51\u0018\u0010\t\u00e7`|\u00e0\u009a\u0013\u0091>\u00db1!\u0092\u0082\u00f1\u000b\u00aa\u0096\u00acl\u0096f\u009e\u0010E\u00ed\u008b\u00ca#U.\u0013]\u00dc\u00aa\u00a1\u00d0G\u00d5\u00e6\u0010\u00c3\u00014\u009aU\u00fb\u00e0\u0007MIl6\u00d3#\u00b5\u00f7(4\u0099\u0092\u0015Oo\u0092\u00bfk)\u001cl\u0007sJ&\u00dd\u00ef\u000bVEC\u00ea{|~\u00cecc\u009b\u00b2\u00d7\u00af\u008e\u0092\u00e8v`s\u00e6 &?^\u0091\u00e9\u00a0H\u00ccW3\u00bd\u00ff\u00ca\u00d4Ua\u0095G)O\u00ea\u00cc\u00a3^;\u00ea\u0091\u00f7\u00a8\u0083\u0081\u00980\u00af\u0094.;\u0098\u00c9\u0010?\u0017\u00d1\u00cb\u00c9up\u00ed$\u00ee\u00c5\u00c46\u00f2k=\u00c6\u0085\u009c\u00a3m\u00cd\u00e5y\u00b2a6\u00d7\u001cb\u0017t\u00cb\u00ed\u00b0\u008a\u00c6\u0099\u0089Y\u00840d\u00d7\u0084M\u00fd\u00a5\u00f8\u00fe\u00dc\u00ad\u009dWG\u0087\u0011\u00ef\u001c<\u00ffm\u00d3\u00d5|\u00acq\u00e9\u0017\u00921\u00fe\u00b1\u00fe\u00e14J\u00fa\u00bd\u0006\u00cc}\u00af\u00e80C\u00fe\u00ed\u00ffM\u0010R\u009dJ\u00f2\u00f2\u00f1S\u0089\u0098?\u00b9AaA\u0002&";
                        var17_6 = 289;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = NameProtect.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c4.\u008d\u00ab\u00c7\u009b[\u00a3\u00c1cc\u00bb\u00f9.\"\u00f5t\u00b5\u00ebs\u0002\u00ea\u001e\u00b1>A\u00c6\u00a9\u0083\u00d8\u008d\u00c4\u0010\r\u00162\u0017lG\u00f6m\u009f\u00cb\u00b7\u00db\u0016@\u00c8P";
                            var17_6 = 49;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = NameProtect.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                NameProtect.b = var18_3;
                NameProtect.c = new String[12];
                var1_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var2_11 = 1; var2_11 < 8; ++var2_11) {
                    v9 = v9;
                    v9[var2_11] = (byte)(var20 << var2_11 * 8 >>> 56);
                }
                var1_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var0_12 = new long[2];
                var4_13 = 0;
                var5_14 = ",\u0082\u0000\u0083\u00862\u0015\u00b0\u00b4V&\u0087\u008c\u0012\u00b1\u00da";
                var6_15 = 16;
                var3_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl72:
                // 1 sources

                while (true) {
                    var0_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var3_16 < var6_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
            v10 = var4_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(NameProtect.class, "name", "getName()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(NameProtect.class, "replace", "getReplace()Lsu/catlean/b9;", 0))};
        NameProtect.delegatedProperties = var29_20;
        NameProtect.INSTANCE = new NameProtect();
        NameProtect.name$delegate = SettingFactory.textSetting$default(NameProtect.INSTANCE, "name", "catlean.su", null, null, (int)var0_12[1], null);
        NameProtect.replace$delegate = SettingFactory.modeSetting$default((SettingContainer)NameProtect.INSTANCE, "replace", (Enum)NameProtectReplaceMode.FRIENDS, null, null, (int)var0_12[0], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x597C;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/qa", exception);
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
            NameProtect.c[n2] = NameProtect.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NameProtect.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qa" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NameProtect.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

