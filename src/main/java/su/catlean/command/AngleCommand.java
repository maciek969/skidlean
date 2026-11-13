/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package su.catlean.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
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
import kotlin.text.StringsKt;
import net.minecraft.client.network.ClientPlayerEntity;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.Command;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class AngleCommand
extends Command {
    @NotNull
    public static final AngleCommand INSTANCE;
    private static final long b;
    private static final String[] g;
    private static final String[] h;
    private static final Map i;

    private AngleCommand() {
        super("angle");
    }

    @Override
    public void build(@NotNull LiteralArgumentBuilder literalArgumentBuilder) {
        Intrinsics.checkNotNullParameter(literalArgumentBuilder, "builder");
        StringArgumentType stringArgumentType = StringArgumentType.string();
        Intrinsics.checkNotNullExpressionValue(stringArgumentType, "string(...)");
        RequiredArgumentBuilder requiredArgumentBuilder = this.argument("yaw", (ArgumentType)stringArgumentType);
        StringArgumentType stringArgumentType2 = StringArgumentType.string();
        Intrinsics.checkNotNullExpressionValue(stringArgumentType2, "string(...)");
        ((LiteralArgumentBuilder)literalArgumentBuilder.then(requiredArgumentBuilder.then(this.argument("pitch", (ArgumentType)stringArgumentType2).executes(AngleCommand::build$lambda$0)))).executes(AngleCommand::build$lambda$1);
    }

    private static final float parseAngle(String string, float f) {
        float f2;
        if (StringsKt.startsWith$default(string, "~", false, 2, null)) {
            Float f3 = StringsKt.toFloatOrNull(StringsKt.removePrefix(string, (CharSequence)"~"));
            float f4 = f3 != null ? f3.floatValue() : 0.0f;
            f2 = f + f4;
        } else {
            Float f5 = StringsKt.toFloatOrNull(string);
            f2 = f5 != null ? f5.floatValue() : f;
        }
        return f2;
    }

    private static final int build$lambda$0(CommandContext commandContext) {
        String string = (String)commandContext.getArgument("yaw", String.class);
        String string2 = (String)commandContext.getArgument("pitch", String.class);
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Intrinsics.checkNotNull(string);
        clientPlayerEntity.setYaw(AngleCommand.parseAngle(string, Globals.getPlayer().getYaw()));
        ClientPlayerEntity clientPlayerEntity2 = Globals.getPlayer();
        Intrinsics.checkNotNull(string2);
        clientPlayerEntity2.setPitch(AngleCommand.parseAngle(string2, Globals.getPlayer().getPitch()));
        return 1;
    }

    private static final int build$lambda$1(CommandContext commandContext) {
        INSTANCE.sendMessage("angle <yaw> <pitch>");
        return 1;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                AngleCommand.b = ZkmKeys.a(-2463097534484928126L, -764143306516214348L, MethodHandles.lookup().lookupClass()).a(53855180085146L);
                var9 = AngleCommand.b ^ 132266376448068L;
                AngleCommand.i = new HashMap<K, V>(13);
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
                var4_5 = "%R\u00d1~u\u00ee^\u009f\u00a3~\u00a7z8\u0007\u00e5\u0093\u0094\u00dbW\u00fbH\u008bm_\u0018\u0089\u0081\u00e8\u00ef\u0013\u00ea\u00db\u00c5O\u0099\u00f6\u0098R\u0087\u0003\u0014V\u00df\u0014\u00c8I\u0082\u001fl\u0010&x\fX\u00b7\u0087_\u0012\u00cf\u00d3\u001ft\u009fs\u0015n\u0010\u00b7\u009d\u00ea\u00a8 n\u0090\u0007D\u00e5K1\u0097\u009bc\u008c\u0010\u0001\b\u00fa\u00bf\u00a3\u00ac\u00eb@\u00ec\u00a5\u00f0\u00db.h\\\u009f\u0010\u0005\u00e0\bI\u00c1\u001a\u00d1\u00fci=\u00c0\u00fd\u00ae\u0006\u00af\u00c0\u0010@'\u0083:!\u0096\u00cb\u00d8\u00b3\u009f7\u0016\u00b4\u00d0^\u00d2";
                var6_6 = 134;
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
                    var7_3[var5_4++] = AngleCommand.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\f5\u00b4\u009d\u00e4V\u00a2\u0007h\u0016\u00b0\u0004\u00b4\u0000E\u00d60\u00c4\u00e10\u0003\u0015\u00ef\"CN\u00e8_\u00d8*\u0096e\u00e9\u00a3\u00dc.\u00fc\u0085\u0010~\u00e6\u00f1L\u0099\u00a4`K\u0085\u0004\u00e9\r\u0018\u009a\u00c2\u00f2w^B\u00cef\u000b\u00de\u009bn\u00a9";
                    var6_6 = 65;
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
                    var7_3[var5_4++] = AngleCommand.zkmDecodeUtf8(var8_9).intern();
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
        AngleCommand.g = var7_3;
        AngleCommand.h = new String[9];
        AngleCommand.INSTANCE = new AngleCommand();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3EB2;
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
                throw new RuntimeException("su/catlean/ai", exception);
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
            AngleCommand.h[n2] = AngleCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AngleCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ai" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AngleCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

