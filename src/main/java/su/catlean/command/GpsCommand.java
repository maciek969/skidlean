/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package su.catlean.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
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
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.Command;
import su.catlean.command.CommandManager;
import su.catlean.zkm.ZkmKeys;

public final class GpsCommand
extends Command {
    @NotNull
    public static final GpsCommand INSTANCE;
    private static final long b;
    private static final String[] g;
    private static final String[] h;
    private static final Map i;

    private GpsCommand() {
        super("gps");
    }

    @Override
    public void build(@NotNull LiteralArgumentBuilder literalArgumentBuilder) {
        Intrinsics.checkNotNullParameter(literalArgumentBuilder, "builder");
        literalArgumentBuilder.then(this.literal("off").executes(GpsCommand::build$lambda$0));
        IntegerArgumentType integerArgumentType = IntegerArgumentType.integer();
        Intrinsics.checkNotNullExpressionValue(integerArgumentType, "integer(...)");
        RequiredArgumentBuilder requiredArgumentBuilder = this.argument("x", (ArgumentType)integerArgumentType);
        IntegerArgumentType integerArgumentType2 = IntegerArgumentType.integer();
        Intrinsics.checkNotNullExpressionValue(integerArgumentType2, "integer(...)");
        literalArgumentBuilder.then(requiredArgumentBuilder.then(this.argument("z", (ArgumentType)integerArgumentType2).executes(GpsCommand::build$lambda$1)));
        literalArgumentBuilder.executes(GpsCommand::build$lambda$2);
    }

    private static final int build$lambda$0(CommandContext commandContext) {
        CommandManager.INSTANCE.setBlockPos(null);
        return 1;
    }

    private static final int build$lambda$1(CommandContext commandContext) {
        Integer n = (Integer)commandContext.getArgument("x", Integer.TYPE);
        Integer n2 = (Integer)commandContext.getArgument("z", Integer.TYPE);
        Intrinsics.checkNotNull(n);
        int n3 = n;
        Intrinsics.checkNotNull(n2);
        CommandManager.INSTANCE.setBlockPos(new BlockPos(n3, 0, n2));
        return 1;
    }

    private static final int build$lambda$2(CommandContext commandContext) {
        INSTANCE.sendMessage("gps <off / x z>");
        return 1;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                GpsCommand.b = ZkmKeys.a(-1270856413440841061L, -3374900414026391986L, MethodHandles.lookup().lookupClass()).a(14006675627272L);
                var9 = GpsCommand.b ^ 124824873267617L;
                GpsCommand.i = new HashMap<K, V>(13);
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
                var7_3 = new String[6];
                var5_4 = 0;
                var4_5 = "(\u00fb\u00b8$\u00dc\u0091\u00d4k\u00cc*\u00dbD\u00bf\u00a1\u0094\u00efs\u00eb<2\u0018\u00e9\u008b8 {\u00a0\u0002U\u00dau\u0084I\u0081\u008c@\u0096\u00bd\u001a\u00df@ko\u00ca\u00c5\u000e\u00f5\u00f57$\"F\u00e3Q~m[\u0010\u00e2T\u0085\u00ad\u00c6\u0084+\u0001\u00d0\u0080N\u001d\u00c5T{9 \u00f5)\u009f\u00aa\u000b\u009f\u008b\u000e\u00c29\u00ba\u0081\u0011\u00d5\u009e\u0098\u009b\u0012\u00c4}.\u00dd`\u00ab\u00cc\u00a2*\u00b9\u0011\u00c2|p";
                var6_6 = 107;
                var3_7 = 24;
                var2_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = GpsCommand.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u0005\u00a4\u000bSCg\u00ff\u00cd\u0019\u0087\u00a3\f\u0086bgu\u0010\u0003TI\u00d0\u0012\u007fP\u00aaN\u008a2\u009c[\u00cc<h";
                    var6_6 = 33;
                    var3_7 = 16;
                    var2_8 = -1;
lbl34:
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
lbl39:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = GpsCommand.zkmDecodeUtf8(var8_9).intern();
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
lbl51:
                // 1 sources

                ** continue;
            }
        }
        GpsCommand.g = var7_3;
        GpsCommand.h = new String[6];
        GpsCommand.INSTANCE = new GpsCommand();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x9F0;
        if (h[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])i.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ay", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = g[n2].getBytes("ISO-8859-1");
            GpsCommand.h[n2] = GpsCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = GpsCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ay" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(GpsCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

