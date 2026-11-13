/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package su.catlean.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
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
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.Command;
import su.catlean.command.FixallCommand$1;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class FixallCommand
extends Command {
    @NotNull
    public static final FixallCommand INSTANCE;
    private static final long b;
    private static final String[] g;
    private static final String[] h;
    private static final Map i;
    private static final long j;

    private FixallCommand() {
        super("fixall");
    }

    @Override
    public void build(@NotNull LiteralArgumentBuilder literalArgumentBuilder) {
        Intrinsics.checkNotNullParameter(literalArgumentBuilder, "builder");
        IntegerArgumentType integerArgumentType = IntegerArgumentType.integer();
        Intrinsics.checkNotNullExpressionValue(integerArgumentType, "integer(...)");
        literalArgumentBuilder.then(this.argument("delay", (ArgumentType)integerArgumentType).executes(FixallCommand::build$lambda$0));
        literalArgumentBuilder.executes(FixallCommand::build$lambda$1);
    }

    public final void fixAll(long l) {
        Globals.getNetworkHandler().sendChatCommand("fix hand");
        Globals.getInteractionManager().clickSlot(Globals.getPlayer().currentScreenHandler.syncId, 1, Globals.getPlayer().getInventory().getSelectedSlot(), SlotActionType.SWAP, Globals.getPlayer());
        Async async = Async.INSTANCE;
        int n = 0;
        async.getExecutor().execute(new FixallCommand$1(n, l));
    }

    private static final int build$lambda$0(CommandContext commandContext) {
        long l = ((Number)commandContext.getArgument("delay", Integer.TYPE)).intValue();
        INSTANCE.fixAll(l);
        return 1;
    }

    private static final int build$lambda$1(CommandContext commandContext) {
        INSTANCE.fixAll(j);
        return 1;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    FixallCommand.b = ZkmKeys.a(-1330683896654346504L, 8113014598360714461L, MethodHandles.lookup().lookupClass()).a(216145712880945L);
                    var14 = FixallCommand.b ^ 27667551736319L;
                    FixallCommand.i = new HashMap<K, V>(13);
                    var5_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var14 >>> 56);
                    for (var6_2 = 1; var6_2 < 8; ++var6_2) {
                        v2 = v2;
                        v2[var6_2] = (byte)(var14 << var6_2 * 8 >>> 56);
                    }
                    var5_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var12_3 = new String[6];
                    var10_4 = 0;
                    var9_5 = "s\u00b9`\u00b6\u00f9:n\u008f\u0095\u00b7\u009cg;\u001fu\u0087\u00c1hFEK\u00be\\rn9{\u00f1\u0019\u00feD\u00f7\u0010\u00c0p\u0004\u00c4\u00a2\u009d\u0085j\u0088s}B\u00fa\u00e2\u0089x\u0010&S\u00d7\u0096L\u00e3H\u0013\u00db\u00c2\u00b1${{\u00ec>\u0010_\u009b\u00c5Jrn\u0013\u00eb\u00bd\u0086f\u00aa\u00f6\u0083\u00d4\u00da";
                    var11_6 = 83;
                    var8_7 = 32;
                    var7_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var7_8;
                        v4 = var9_5.substring(v3, v3 + var8_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = FixallCommand.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        var9_5 = "\u0004\u00e1\u00abs\u0096PK\u001b\u0006\u00d9\u008a\u00d2\u00a6\u0091\u00f3] h\"t?{c\u00a4\u0088\u00a2\u0094uu\b\u00fa\u00d8SB\u0006\b\u00e6\u00f9\u00cb\u0007\u00cf\u001aZ\u00a6Bf\u00be\u0013\u00fa";
                        var11_6 = 49;
                        var8_7 = 16;
                        var7_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var7_8;
                            v4 = var9_5.substring(v6, v6 + var8_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var12_3[var10_4++] = FixallCommand.zkmDecodeUtf8(var13_9).intern();
                        if ((var7_8 += var8_7) < var11_6) {
                            var8_7 = var9_5.charAt(var7_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var13_9 = var5_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            FixallCommand.g = var12_3;
            FixallCommand.h = new String[6];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var14 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var14 << var1_11 * 8 >>> 56);
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
        var4_12 = var0_10.doFinal(new byte[]{-12, 11, 29, -66, 49, -4, -74, 46});
        ** while (true)
        FixallCommand.j = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
        FixallCommand.INSTANCE = new FixallCommand();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3D9;
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
                throw new RuntimeException("su/catlean/ae", exception);
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
            FixallCommand.h[n2] = FixallCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FixallCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ae" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FixallCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

