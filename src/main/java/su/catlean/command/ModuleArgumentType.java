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
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.command.CommandSource;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.ModuleArgumentType$Companion;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class ModuleArgumentType
implements ArgumentType {
    @NotNull
    public static final ModuleArgumentType$Companion Companion;
    @NotNull
    private static final Collection P;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    @NotNull
    public Module parse2(@NotNull StringReader stringReader) {
        Intrinsics.checkNotNullParameter(stringReader, "reader");
        String string = stringReader.readString();
        Intrinsics.checkNotNullExpressionValue(string, "readString(...)");
        Module module = ModuleManager.INSTANCE.R(string);
        if (module == null) {
            throw new DynamicCommandExceptionType(ModuleArgumentType::parse2$lambda$0).create((Object)stringReader.readString());
        }
        return module;
    }

    @NotNull
    public CompletableFuture listSuggestions(@NotNull CommandContext commandContext, @NotNull SuggestionsBuilder suggestionsBuilder) {
        Intrinsics.checkNotNullParameter(commandContext, "context");
        Intrinsics.checkNotNullParameter(suggestionsBuilder, "builder");
        CompletableFuture<Suggestions> completableFuture = CommandSource.suggestMatching(ModuleManager.INSTANCE.L().stream().map(arg_0 -> ModuleArgumentType.listSuggestions$lambda$1(ModuleArgumentType::listSuggestions$lambda$0, arg_0)), suggestionsBuilder);
        Intrinsics.checkNotNullExpressionValue(completableFuture, "suggest(...)");
        return completableFuture;
    }

    @NotNull
    public Collection getExamples() {
        return P;
    }

    private static final Message parse2$lambda$0(Object object) {
        Object[] objectArray = new Object[1];
        Intrinsics.checkNotNull(object);
        objectArray[0] = object;
        return Text.literal(I18n.INSTANCE.translate("commands.bind.not-exist", objectArray));
    }

    private static final String listSuggestions$lambda$0(Module module) {
        return module.getId();
    }

    private static final String listSuggestions$lambda$1(Function1 function1, Object object) {
        return (String)function1.invoke(object);
    }

    private static final String _clinit_$lambda$0(Module module) {
        return module.getId();
    }

    private static final String _clinit_$lambda$1(Function1 function1, Object object) {
        return (String)function1.invoke(object);
    }

    public Object parse(StringReader stringReader) {
        return this.parse2(stringReader);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    ModuleArgumentType.a = ZkmKeys.a(5789928076481872052L, 2748609643139066356L, MethodHandles.lookup().lookupClass()).a(176405931892900L);
                    var16 = ModuleArgumentType.a ^ 67424223862816L;
                    ModuleArgumentType.d = new HashMap<K, V>(13);
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var16 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var16 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[7];
                    var12_4 = 0;
                    var11_5 = "v\u001877\u001d\u0002\u00a2\u00eb\u00b6D\u0087\u00d3\u00d6\u00e9\u00a6k\u0018\u00e5\u0084\u0018&\u00aa\u00dd\u00d0\u0000!0D\u00f0E\u00b7+\u00ff\u00c6\u00ae\u00ab\u009c\u00f0)\u00d5\u0089\u0010\u00b5\u00e0s\u00f8,S\u0006u43s\u00bdW\u00bd\u00d4\u0018 \u00184\u00c9=\u00a9\u0086zi\u00b8e\u009bC\u0090cKz\u0000\u00fc\u00c6\u00d2\u00e5\u0090Li\u008c\u00c6\u00e0\u0086\u00ee-\u00d8<\u0010\u00cd=VT!\u000b\u000f\u00fbb\u0088\u00aa/\u008e\u00ceUN";
                    var13_6 = 108;
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
                        var14_3[var12_4++] = ModuleArgumentType.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u00dfn\u00d2\u00b8\u0006[\u0095P<b\u0080\u00d8\u0015\u008dn\u000f[\u008e<Vp\u00ac.\u0002w\u00d7\u000b\u00c0~H3\u00e1(\u009b#j\u00cb\u00bf\u00f2\u00ca\u00c2\u00d11\u0082\u008a\u00e7q\u00b7h$[\u00fe\u00c7\u00dc\u00a7\u00a5\u00ec\u00d3kq\u00faz\u00aek\u0096\u0082VH\u0006-3\n\u000f";
                        var13_6 = 73;
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
                        var14_3[var12_4++] = ModuleArgumentType.zkmDecodeUtf8(var15_9).intern();
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
            ModuleArgumentType.b = var14_3;
            ModuleArgumentType.c = new String[7];
            var2_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var16 >>> 56);
            for (var3_11 = 1; var3_11 < 8; ++var3_11) {
                v9 = v9;
                v9[var3_11] = (byte)(var16 << var3_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var6_13 = var2_10.doFinal(new byte[]{122, -54, -55, -114, 123, 29, -94, 113});
        ** while (true)
        var0_12 = ((long)var6_13[0] & 255L) << 56 | ((long)var6_13[1] & 255L) << 48 | ((long)var6_13[2] & 255L) << 40 | ((long)var6_13[3] & 255L) << 32 | ((long)var6_13[4] & 255L) << 24 | ((long)var6_13[5] & 255L) << 16 | ((long)var6_13[6] & 255L) << 8 | (long)var6_13[7] & 255L;
        ModuleArgumentType.Companion = new ModuleArgumentType$Companion(null);
        var18_14 = ModuleManager.INSTANCE.L().stream().map((Function<Object, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$1(kotlin.jvm.functions.Function1 java.lang.Object ), (Ljava/lang/Object;)Ljava/lang/String;)((Function1<Module, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, _clinit_$lambda$0(su.catlean.module.Module ), (Lsu/catlean/module/Module;)Ljava/lang/String;)())).limit(var0_12).toList();
        Intrinsics.checkNotNullExpressionValue(var18_14, "toList(...)");
        ModuleArgumentType.P = var18_14;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6DBA;
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
                throw new RuntimeException("su/catlean/fk", exception);
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
            ModuleArgumentType.c[n2] = ModuleArgumentType.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ModuleArgumentType.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/fk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ModuleArgumentType.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

