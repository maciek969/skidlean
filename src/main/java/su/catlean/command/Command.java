/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 */
package su.catlean.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
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
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.I18n;
import su.catlean.util.TextFormatting;
import su.catlean.zkm.ZkmKeys;

public abstract class Command {
    @NotNull
    private final String name;
    @NotNull
    private final String d;
    private static Module[] H;
    private static final long a;
    private static final String[] c;
    private static final String[] e;
    private static final Map f;

    public Command(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        this.name = string;
        Object[] objectArray = new Object[]{};
        String string2 = "commands.descriptions." + this.name;
        this.d = I18n.INSTANCE.translate(string2, objectArray);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public abstract void build(@NotNull LiteralArgumentBuilder var1);

    public final void register(@NotNull CommandDispatcher commandDispatcher) {
        Intrinsics.checkNotNullParameter(commandDispatcher, "dispatcher");
        LiteralArgumentBuilder literalArgumentBuilder = LiteralArgumentBuilder.literal((String)this.name);
        Intrinsics.checkNotNull(literalArgumentBuilder);
        this.build(literalArgumentBuilder);
        commandDispatcher.register(literalArgumentBuilder);
    }

    @NotNull
    public final RequiredArgumentBuilder argument(@NotNull String string, @NotNull ArgumentType argumentType) {
        Intrinsics.checkNotNullParameter(string, "name");
        Intrinsics.checkNotNullParameter(argumentType, "type");
        RequiredArgumentBuilder requiredArgumentBuilder = RequiredArgumentBuilder.argument((String)string, (ArgumentType)argumentType);
        Intrinsics.checkNotNullExpressionValue(requiredArgumentBuilder, "argument(...)");
        return requiredArgumentBuilder;
    }

    @NotNull
    public final LiteralArgumentBuilder literal(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "name");
        LiteralArgumentBuilder literalArgumentBuilder = LiteralArgumentBuilder.literal((String)string);
        Intrinsics.checkNotNullExpressionValue(literalArgumentBuilder, "literal(...)");
        return literalArgumentBuilder;
    }

    public final void sendMessage(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "msg");
        this.sendMessage(TextFormatting.getText(string));
    }

    public final void sendMessage(@NotNull Text text) {
        Intrinsics.checkNotNullParameter(text, "msg");
        MutableText mutableText = TextFormatting.gray(TextFormatting.green(TextFormatting.emptyText(), ">"), " ");
        mutableText.append(text);
        if (Globals.getMc().isOnThread()) {
            Globals.getPlayer().sendMessage(mutableText, false);
        } else {
            Globals.getMc().executeSync(() -> Command.sendRaw(mutableText));
        }
    }

    private static final void sendRaw(MutableText mutableText) {
        Globals.getPlayer().sendMessage(mutableText, false);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        H = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return H;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                Command.a = ZkmKeys.a(-3293937965191506912L, -6601232492868737155L, MethodHandles.lookup().lookupClass()).a(11271304481541L);
                var9 = Command.a ^ 56869212652252L;
                Command.f = new HashMap<K, V>(13);
                Command.zkmSetFlag(new Module[4]);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[9];
                var5_4 = 0;
                var4_5 = "\u00fa\u00e7\u00a3\u00e7\u00be\u00b7\u00a89H\u0005~w\u00b4\u0005\u0016\u00ea\u00cd\u00d2\u00ae\u00b0\u0099\u00c1^\u00a8\u00f6{Y\u0099K\u0088=\u00bf\u0018\u0007\u008cf\u00ac\u009f\u00ceb\u00a9\u0003E\u009au^h\u00b4m\u0081\u00a7\u00f8\u00e7K\u00ed\u0001Z\u0018\b\u0088\u00c3z\u0089\u0095\u00efOI\u00c1\u0004\u0099\u00b6g\u009a\u00f5[%Q\u00afyD\u00f8\u00fa\u0010\u00ad6b}\u0007\"/x\u00ffH\u001b\u00bfo\u00ecU%\u0010^\u00e9\u001b\u0098\u0001!\u00a1\u0094\u00e0\u009f\u00e7\u0084~_\u00c4\u00ec\u0010\u00f9_e\u008e.3\b\u0088|\u00ae\u008b{b\u0090\u00c6\u009e\u0010]\u00ff \u00934\u00de\u00c8#\u0090!j\u00b1\u000e\u00e9\u00bf\u000b";
                var6_6 = 150;
                var3_7 = 32;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = Command.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00eb9\u0011<*\u00eb\u00f6\u00f7\u00ba\u009d\u009c\u00b7N@?q(\u00a9\u00e0\u00dcO\u0013\u00119\u0010\u0085E\u00afu1\u00b2\u00e3\u00fc\u00c4F\u00dbn\u009dc\u00a11\u00d0*\u009e\u00beS\u00d9\u0083\u00fc)\u009d\u00f48\u00fe\u0098\u00a2G";
                    var6_6 = 57;
                    var3_7 = 16;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = Command.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        Command.c = var7_3;
        Command.e = new String[9];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7B65;
        if (e[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/a3", exception);
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
            Command.e[n2] = Command.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Command.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/a3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Command.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

