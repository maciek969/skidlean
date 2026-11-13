/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Message
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.DynamicCommandExceptionType
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package su.catlean.command;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.FriendArgumentType$Companion;
import su.catlean.config.ConfigStore;
import su.catlean.util.FriendManager;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class FriendArgumentType
implements ArgumentType {
    @NotNull
    public static final FriendArgumentType$Companion Companion;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    @NotNull
    public String parse2(@NotNull StringReader stringReader) {
        Intrinsics.checkNotNullParameter(stringReader, "reader");
        String string = stringReader.readString();
        Intrinsics.checkNotNull(string);
        if (!FriendManager.INSTANCE.isFriend(string)) {
            throw new DynamicCommandExceptionType(FriendArgumentType::parse2$lambda$0).create((Object)string);
        }
        return string;
    }

    @NotNull
    public CompletableFuture listSuggestions(@NotNull CommandContext commandContext, @NotNull SuggestionsBuilder suggestionsBuilder) {
        Intrinsics.checkNotNullParameter(commandContext, "context");
        Intrinsics.checkNotNullParameter(suggestionsBuilder, "builder");
        CompletableFuture<Suggestions> completableFuture = CommandSource.suggestMatching(ConfigStore.INSTANCE.getJsonListFile2().getData(), suggestionsBuilder);
        Intrinsics.checkNotNullExpressionValue(completableFuture, "suggest(...)");
        return completableFuture;
    }

    @NotNull
    public List getExamples2() {
        List list = ConfigStore.INSTANCE.getJsonListFile2().getData().stream().limit(e).toList();
        Intrinsics.checkNotNullExpressionValue(list, "toList(...)");
        return list;
    }

    private static final Message parse2$lambda$0(Object object) {
        Object[] objectArray = new Object[1];
        Intrinsics.checkNotNull(object);
        objectArray[0] = object;
        return Text.literal(I18n.INSTANCE.translate("commands.friend.not-exist", objectArray));
    }

    public Object parse(StringReader stringReader) {
        return this.parse2(stringReader);
    }

    public Collection getExamples() {
        return this.getExamples2();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    FriendArgumentType.a = ZkmKeys.a(6960106126537031823L, 4426036122259795337L, MethodHandles.lookup().lookupClass()).a(44125390255755L);
                    FriendArgumentType.d = new HashMap<K, V>(13);
                    var5 = FriendArgumentType.a ^ 84087809265051L;
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var5 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var5 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[6];
                    var12_4 = 0;
                    var11_5 = "\u00bc3\u00c5\u00ac\u00e5d\u0084\"]R\u00a1>N\"\u00fe\u00fb\u0018{\u00a0\u00f7\u00cb\u001d\u00f6S\u00e3^\u00ab\u001d\u00c5\u00beLP\u00c9c`\u008c\u0082\u008a g\u0099\u0010\u00cb\u0006p\u00f1\u001f\u001b\u00b2\u00e6\u00df\u00a4\u00ee\u00f2\u00b7\u000f'\u00c4\u0018\u00a0\u008e\u00b3\u00ce\u00105\u00cf\u0015\u00f4Z\u008a\u00ea\u00f8\u00af\u00b1\u0091NH\u00b1\u001at\u00b5F(";
                    var13_6 = 83;
                    var10_7 = 16;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = FriendArgumentType.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "X\u0016\u00ef\u009c\u00a02+\u001a)\u0011\u0098\u00e9~\u00d6]0\u008d\u00ad\u00fb\u00a3\u00e9m)\u00cd\u00c4\u00f89\u00f9\u00f9\t\u00d2E\u001f\u008b\u00c2\u0082Y\u0097t\u0012\u00f4K\u0098\u0095+\u00c6\u00c0\u0083\u0010?\u00f3q\u0086\u009b\u0014+?^\u0080\u008a\b\u0094d\u00d7<";
                        var13_6 = 65;
                        var10_7 = 48;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = FriendArgumentType.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            FriendArgumentType.b = var14_3;
            FriendArgumentType.c = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var5 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var5 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{-41, 7, -120, -106, -37, -111, 17, -66});
        ** while (true)
        FriendArgumentType.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        FriendArgumentType.Companion = new FriendArgumentType$Companion(null);
    }

    private static CommandSyntaxException zkmRethrow(CommandSyntaxException commandSyntaxException) {
        return commandSyntaxException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x633B;
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
                throw new RuntimeException("su/catlean/s5", exception);
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
            FriendArgumentType.c[n2] = FriendArgumentType.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FriendArgumentType.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/s5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FriendArgumentType.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

