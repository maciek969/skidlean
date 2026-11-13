/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package su.catlean.command;

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
import kotlin.text.StringsKt;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.Command;
import su.catlean.command.FriendArgumentType;
import su.catlean.command.PlayerNameArgumentType;
import su.catlean.config.ConfigStore;
import su.catlean.util.FriendManager;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class FriendCommand
extends Command {
    @NotNull
    public static final FriendCommand INSTANCE;
    private static final long b;
    private static final String[] g;
    private static final String[] h;
    private static final Map i;

    private FriendCommand() {
        super("friend");
    }

    @Override
    public void build(@NotNull LiteralArgumentBuilder literalArgumentBuilder) {
        Intrinsics.checkNotNullParameter(literalArgumentBuilder, "builder");
        literalArgumentBuilder.then(this.literal("clear").executes(FriendCommand::build$lambda$0));
        literalArgumentBuilder.then(this.literal("add").then(this.argument("player", PlayerNameArgumentType.Companion.create()).executes(FriendCommand::build$lambda$1)));
        literalArgumentBuilder.then(this.literal("remove").then(this.argument("player", FriendArgumentType.Companion.create()).executes(FriendCommand::build$lambda$2)));
        literalArgumentBuilder.then(this.literal("list").executes(FriendCommand::build$lambda$3));
        literalArgumentBuilder.executes(FriendCommand::build$lambda$4);
    }

    private static final int build$lambda$0(CommandContext commandContext) {
        FriendManager.INSTANCE.clearFriends();
        Object[] objectArray = new Object[]{};
        String string = "commands.friend.reset";
        INSTANCE.sendMessage(I18n.INSTANCE.translate(string, objectArray));
        return 1;
    }

    private static final int build$lambda$1(CommandContext commandContext) {
        String string = (String)commandContext.getArgument("player", String.class);
        Intrinsics.checkNotNull(string);
        FriendManager.INSTANCE.addFriend(string);
        Object[] objectArray = new Object[]{string};
        INSTANCE.sendMessage(I18n.INSTANCE.translate("commands.friend.add", objectArray));
        return 1;
    }

    private static final int build$lambda$2(CommandContext commandContext) {
        String string = (String)commandContext.getArgument("player", String.class);
        Intrinsics.checkNotNull(string);
        FriendManager.INSTANCE.removeFriend(string);
        Object[] objectArray = new Object[]{string};
        INSTANCE.sendMessage(I18n.INSTANCE.translate("commands.friend.remove", objectArray));
        return 1;
    }

    private static final int build$lambda$3(CommandContext commandContext) {
        if (ConfigStore.INSTANCE.getJsonListFile2().getData().isEmpty()) {
            Object[] objectArray = new Object[]{};
            String string = "commands.friend.empty";
            INSTANCE.sendMessage(I18n.INSTANCE.translate(string, objectArray));
        } else {
            Object[] objectArray = new Object[]{};
            String string = "commands.friend.friends";
            StringBuilder stringBuilder = new StringBuilder(I18n.INSTANCE.translate(string, objectArray));
            for (String string2 : ConfigStore.INSTANCE.getJsonListFile2().getData()) {
                stringBuilder.append(string2).append(", ");
            }
            String string3 = stringBuilder.toString();
            Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
            INSTANCE.sendMessage(StringsKt.dropLast(string3, 2));
        }
        return 1;
    }

    private static final int build$lambda$4(CommandContext commandContext) {
        INSTANCE.sendMessage(Formatting.RED + "friend add <name> / friend remove <name> / friend list / friend clear");
        return 1;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                FriendCommand.b = ZkmKeys.a(-5802770135694742385L, -8426882597775477875L, MethodHandles.lookup().lookupClass()).a(110483891582363L);
                var9 = FriendCommand.b ^ 119496610384123L;
                FriendCommand.i = new HashMap<K, V>(13);
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
                var7_3 = new String[16];
                var5_4 = 0;
                var4_5 = "\u0004\u0011%=\u00832\u00d5\u00ee[-n\u00dd[D[\u0087\u0010c!\u00b4\u00e6\u00fc\u00b4\u0019\u00b3\u00fa\u0081PC\u001d1X\u0088\u0018\u000e3\u0082X\u001d\u009f\u00cd\u0094\u0092\u009f\u00a53p\u00b2\u0001lq\u00ef\u0095\u00e8a=\u008a\u001b(E\u009d\u0095\u00ef\u0088#c\"L|\u00fa\u00a1\u009awG\u0092\u00b0\u00e4\u00c8\u00ba1jo\u00d8\u001e\u00c0e\u00f8\u00bc\u00b9[\u00db\u00f0[\u00a2\u00da\tb>k\u0010\u00e8\u00ce\u00aft\u001f-S\u00cc\u00bf\u00d3\u00d9c\u00ffJK\u00e7(\u00eaz\u00e7uL\u00f8\u00c8\u0007\u009b\u00de\u00ccLV\u0019\u00b8]\u00beQ\u0012F\u00fc\u0085\u00db\u0091\u00a9\u0006\u00a1n\u00b3=\u0007\u000fU\u0002\u001f_\"+\u00eac\u0010`L\u00bc%\u00e1\u001c\u00f6\u00d98~}\n\u00d6o\u00c5;(\u000e\u00ed\u00ecs\u009f?\n\u00ec\u00fb|V\u00ee8\u008eT@\u00e7(\u0091n\u00e2\u00f6\u00f7&\u00ff\u00felLw\u0099\u0014r\u00db\u0003\n\u0003\u00cc\u00a8\u00a1\u008b\u0010O\u00f1\u000eo\u00ce&\u0015-c\u00b3\u008e\"\u00a6\u00dah\u0016(\u00df\u00f6\u00dbq\u00bd\u0087@\u00ecv\u0095\u00b7\u00f9\u00c8\u00e7\u00cf\u00e9\u00fc\u00c0$\u00e9;k\u00d0\u00ff\u00fb\u008f\u0087h\u00da\u0015\u00f1\u00f4`\u00c6z0Z\u00d0\u0093,p\u00fe\u0080q;\u00a5xw\u001b\u0085hI=\u00ef\u00d8LP\n\u00c4\u0085\u00c8\u0000\u001d\u009b\u0098\u00aa\u0015\u00cf\u00c5\u0090\u0003\u000bc\u00e3(\u00a4\u0091\f\u00b9\u00a7o\u00f8J\u0016\u008c\u00bd\u00f3\u00db\u00aa\u009bb\u00e2\u00d0^\u00aa\u0016\u008eTw\u0002\u00b5X\b\u0016W9\u00fe\u0001\u008e\u00cft\u001e'-\u00cc\u0080{\u0092eY\u00bby`\u00d7Ls{\u00a2\u0013\u00f9\u00c9\u0092#\u00fd\u009c\u0091\u00d5\u00be\r\u00eeF:\u00d4\u00dd\u0095\u00a6\u00d3\u00a1j\u00ad\fH5\u0010\u0013\u0087\u00da\u008cF%\u00fa_\u008e\u00e5\u00d7\u00be4B\u007f\u0095\u0010\u0082\u00ff\u00ecr\u00aa8z\u0085\u00a27\u009cL|B\u00ee\u0084\u0010\u0005\u0097I\f\u00f1\u00aam\u00af\u0096\u0019\u00dc\fa\u009cS\u001a";
                var6_6 = 437;
                var3_7 = 16;
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
                    var7_3[var5_4++] = FriendCommand.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "H@\u0081\u00c8\u00b3\u00fd,x_\u00d04\u00f3\u00d6a\u00d1\u00ee(\u001e\u0090gL\u00d9H\u0083h\u00d6X%\u00a4\u0097\u0003\u00b1\u00cd'2i\u00dd@s\u00ee\u00f7\u00dc\u00ebfD>\u00faWq\u008e\u00cd\u000e\u00d3\u000e\u00ad\u0006\u00d2";
                    var6_6 = 57;
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
                    var7_3[var5_4++] = FriendCommand.zkmDecodeUtf8(var8_9).intern();
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
        FriendCommand.g = var7_3;
        FriendCommand.h = new String[16];
        FriendCommand.INSTANCE = new FriendCommand();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x465;
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
                throw new RuntimeException("su/catlean/a5", exception);
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
            FriendCommand.h[n2] = FriendCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FriendCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/a5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FriendCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

