/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.context.CommandContext
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.suggestion.Suggestions
 *  com.mojang.brigadier.suggestion.SuggestionsBuilder
 */
package su.catlean.command;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.command.CommandSource;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.PlayerNameArgumentType$Companion;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class PlayerNameArgumentType
implements ArgumentType {
    @NotNull
    public static final PlayerNameArgumentType$Companion Companion;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    @NotNull
    public String parse2(@NotNull StringReader stringReader) {
        Intrinsics.checkNotNullParameter(stringReader, "reader");
        String string = stringReader.readString();
        ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
        Intrinsics.checkNotNull(clientPlayNetworkHandler);
        PlayerListEntry playerListEntry = clientPlayNetworkHandler.getPlayerList().stream().filter(arg_0 -> PlayerNameArgumentType.invokePredicate(arg_0 -> PlayerNameArgumentType.parse2$lambda$0(string, arg_0), arg_0)).findFirst().orElse(null);
        if (playerListEntry == null) {
            Intrinsics.checkNotNull(string);
            return string;
        }
        String string2 = playerListEntry.getProfile().name();
        Intrinsics.checkNotNullExpressionValue(string2, "name(...)");
        return string2;
    }

    @NotNull
    public CompletableFuture listSuggestions(@NotNull CommandContext commandContext, @NotNull SuggestionsBuilder suggestionsBuilder) {
        Intrinsics.checkNotNullParameter(commandContext, "context");
        Intrinsics.checkNotNullParameter(suggestionsBuilder, "builder");
        ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
        Intrinsics.checkNotNull(clientPlayNetworkHandler);
        CompletableFuture<Suggestions> completableFuture = CommandSource.suggestMatching(clientPlayNetworkHandler.getPlayerList().stream().map(arg_0 -> PlayerNameArgumentType.listSuggestions$lambda$1(PlayerNameArgumentType::listSuggestions$lambda$0, arg_0)), suggestionsBuilder);
        Intrinsics.checkNotNullExpressionValue(completableFuture, "suggest(...)");
        return completableFuture;
    }

    @NotNull
    public List getExamples2() {
        ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
        Intrinsics.checkNotNull(clientPlayNetworkHandler);
        List<String> list = clientPlayNetworkHandler.getPlayerList().stream().map(arg_0 -> PlayerNameArgumentType.getExamples2$lambda$1(PlayerNameArgumentType::getExamples2$lambda$0, arg_0)).limit(e).toList();
        Intrinsics.checkNotNullExpressionValue(list, "toList(...)");
        return list;
    }

    private static final boolean parse2$lambda$0(String string, PlayerListEntry playerListEntry) {
        return Intrinsics.areEqual(string, playerListEntry.getProfile().name());
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final String listSuggestions$lambda$0(PlayerListEntry playerListEntry) {
        return playerListEntry.getProfile().name();
    }

    private static final String listSuggestions$lambda$1(Function1 function1, Object object) {
        return (String)function1.invoke(object);
    }

    private static final String getExamples2$lambda$0(PlayerListEntry playerListEntry) {
        return playerListEntry.getProfile().name();
    }

    private static final String getExamples2$lambda$1(Function1 function1, Object object) {
        return (String)function1.invoke(object);
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
                    PlayerNameArgumentType.a = ZkmKeys.a(-1729799360318192897L, 5627709146117729805L, MethodHandles.lookup().lookupClass()).a(197727308438276L);
                    PlayerNameArgumentType.d = new HashMap<K, V>(13);
                    var5 = PlayerNameArgumentType.a ^ 5251964461850L;
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
                    var11_5 = "e\u0086\u00bbq$\u00b7cN\u00b2\u00cb\u00cf\u00ea\u00cb[+\u00e0\u0010\u008f\u009f7\u0089\u0092\u0001pP\u00fb\u00d1\f\u00be\u0003\u00cb\u00b4\u0005\u0018*\u0087\u00c6:\u00e7(EaN\u00ed\u00f4\u009f\u001c\u00cb\u00e9,D\u00a2C\u00f0!\u00c1\u0085U\u0010\u0093P\u00a5%\u00b0\u0082\u009c\t?\u00f3\u0013f\u00a0Z<\u00e2";
                    var13_6 = 75;
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
                        var14_3[var12_4++] = PlayerNameArgumentType.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "2\u00be\u00e3\u00830\u00c7m\u00fe\u00e6\u0090\u00ad\u00a8T\u00d7\u00ca\u00ed\u00c4\u00b3l\u00b2SKvd\u00a8\u00b0\u00db=\u00076.\u00ca B\u00fc`;\u0091\u00ae\u0081\f\u00f1\u00e0|\u00af\u00f3\u00b8\u00925)\t\u00e0x\u001c\u00bb\u00e4\u00b3\u00a5\u00b99\f\u009f\u00ed\u00acB";
                        var13_6 = 65;
                        var10_7 = 32;
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
                        var14_3[var12_4++] = PlayerNameArgumentType.zkmDecodeUtf8(var15_9).intern();
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
            PlayerNameArgumentType.b = var14_3;
            PlayerNameArgumentType.c = new String[6];
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
        var4_12 = var0_10.doFinal(new byte[]{123, -1, 124, 15, 119, -122, -39, -17});
        ** while (true)
        PlayerNameArgumentType.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        PlayerNameArgumentType.Companion = new PlayerNameArgumentType$Companion(null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6603;
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
                throw new RuntimeException("su/catlean/mp", exception);
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
            PlayerNameArgumentType.c[n2] = PlayerNameArgumentType.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlayerNameArgumentType.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/mp" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlayerNameArgumentType.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

