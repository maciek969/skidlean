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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.Command;
import su.catlean.command.ModuleArgumentType;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.module.misc.UI;
import su.catlean.setting.Bind;
import su.catlean.util.I18n;
import su.catlean.util.KeyNames;
import su.catlean.util.TextFormatting;
import su.catlean.zkm.ZkmKeys;

public final class BindCommand
extends Command {
    @NotNull
    public static final BindCommand INSTANCE;
    private static final long b;
    private static final String[] g;
    private static final String[] h;
    private static final Map i;
    private static final long[] j;
    private static final Integer[] k;
    private static final Map l;

    private BindCommand() {
        super("bind");
    }

    @Override
    public void build(@NotNull LiteralArgumentBuilder literalArgumentBuilder) {
        Intrinsics.checkNotNullParameter(literalArgumentBuilder, "builder");
        RequiredArgumentBuilder requiredArgumentBuilder = this.argument("module", ModuleArgumentType.Companion.create());
        StringArgumentType stringArgumentType = StringArgumentType.word();
        Intrinsics.checkNotNullExpressionValue(stringArgumentType, "word(...)");
        literalArgumentBuilder.then(requiredArgumentBuilder.then(this.argument("key", (ArgumentType)stringArgumentType).executes(BindCommand::build$lambda$0)));
        literalArgumentBuilder.then(this.literal("list").executes(BindCommand::build$lambda$1));
        literalArgumentBuilder.then(this.literal("clear").executes(BindCommand::build$lambda$2));
        Object var6_2 = null;
        literalArgumentBuilder.then(this.literal("reset").executes(BindCommand::build$lambda$3));
        literalArgumentBuilder.executes(BindCommand::build$lambda$4);
    }

    private static final int build$lambda$0(CommandContext commandContext) {
        int n;
        Module module = (Module)commandContext.getArgument("module", Module.class);
        String string = (String)commandContext.getArgument("key", String.class);
        Object var20_3 = null;
        if (string == null) {
            Object[] objectArray = new Object[]{};
            String string2 = "commands.bind.no-such-button";
            INSTANCE.sendMessage(I18n.INSTANCE.translate(string2, objectArray));
            return 1;
        }
        if (StringsKt.equals(string, "none", true) || StringsKt.equals(string, "null", true)) {
            v0 = -1;
        } else {
            int n2;
            try {
                String string3 = string.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(string3, "toLowerCase(...)");
                n2 = InputUtil.fromTranslationKey("key.keyboard." + string3).getCode();
            }
            catch (NumberFormatException numberFormatException) {
                Object[] objectArray = new Object[]{};
                String string4 = "commands.bind.no-such-button";
                INSTANCE.sendMessage(I18n.INSTANCE.translate(string4, objectArray));
                return 1;
            }
            v0 = n = n2;
        }
        if (n == 0) {
            Object[] objectArray = new Object[]{};
            String string5 = "commands.bind.no-such-button";
            INSTANCE.sendMessage(I18n.INSTANCE.translate(string5, objectArray));
            return 1;
        }
        module.setBind(new Bind(n, false, !Intrinsics.areEqual(string, "M") && StringsKt.contains$default((CharSequence)string, "M", false, 2, null)));
        Object[] objectArray = new Object[]{};
        String string6 = "commands.bind.bound-to";
        MutableText mutableText = TextFormatting.white(TextFormatting.green(TextFormatting.emptyText(), module.getDisplayName()), I18n.INSTANCE.translate(string6, objectArray));
        String string7 = string.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string7, "toUpperCase(...)");
        INSTANCE.sendMessage(TextFormatting.gray(mutableText, string7));
        return 1;
    }

    private static final int build$lambda$1(CommandContext commandContext) {
        Object[] objectArray = new Object[]{};
        String string = "commands.bind.binds";
        StringBuilder stringBuilder = new StringBuilder(I18n.INSTANCE.translate(string, objectArray));
        Iterator iterator2 = ModuleManager.INSTANCE.L().iterator();
        Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
        Iterator iterator3 = iterator2;
        Object var12_5 = null;
        while (iterator3.hasNext()) {
            Object e = iterator3.next();
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            Module module = (Module)e;
            if (Intrinsics.areEqual(KeyNames.INSTANCE.getKeyName(module.getBind().getKey()), "None")) continue;
            stringBuilder.append("\n" + module.getDisplayName()).append("-> ").append(KeyNames.INSTANCE.getKeyName(module.getBind().getKey())).append(module.getBind().isHold() ? "[hold]" : "");
        }
        String string2 = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        INSTANCE.sendMessage(string2);
        return 1;
    }

    private static final int build$lambda$2(CommandContext commandContext) {
        Iterable iterable = ModuleManager.INSTANCE.L();
        Object var7_2 = null;
        Iterable iterable2 = iterable;
        Collection collection2 = new ArrayList();
        Iterator iterator2 = iterable2.iterator();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            Module module = (Module)t;
            if (!(!Intrinsics.areEqual(module, UI.INSTANCE))) continue;
            collection2.add(t);
        }
        iterable = (List)collection2;
        for (Collection collection2 : iterable) {
            Module module = (Module)((Object)collection2);
            module.setBind(new Bind(0, false, false, 7, null));
        }
        return 1;
    }

    private static final int build$lambda$3(CommandContext commandContext) {
        Iterable iterable = ModuleManager.INSTANCE.L();
        Iterable iterable2 = iterable;
        Collection collection2 = new ArrayList();
        Iterator iterator2 = iterable2.iterator();
        Object var7_5 = null;
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            Module module = (Module)t;
            if (!(!Intrinsics.areEqual(module, UI.INSTANCE))) continue;
            collection2.add(t);
        }
        iterable = (List)collection2;
        for (Collection collection2 : iterable) {
            Module module = (Module)((Object)collection2);
            module.setBind(new Bind(0, false, false, 7, null));
        }
        return 1;
    }

    private static final int build$lambda$4(CommandContext commandContext) {
        INSTANCE.sendMessage(Formatting.RED + "bind <id> <key> / bind list / bind clear");
        return 1;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        BindCommand.b = ZkmKeys.a(1810444873986116528L, 5457010188984326827L, MethodHandles.lookup().lookupClass()).a(193093427466040L);
                        var20 = BindCommand.b ^ 117398014637924L;
                        BindCommand.i = new HashMap<K, V>(13);
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
                        var18_3 = new String[26];
                        var16_4 = 0;
                        var15_5 = "\u00fb\u00c8\u00a4\u00d6\b\u009f\u00f7\u00d3\u00d0\u0091\u0005\u001c\u009cR\u00bd\u00fb(\u00e6\u009b\u0019i\u00ecp\n\u008d9\u00c3E\u00b3zA\u00db\u00d4z\u00e9\u00a1\u00cb\u00a6\u00a1\u0016A\u00b6\u00eax\u0016\u0011\u00ae\u00c2JL\u00c1\u00eae\u00f7\u0094\u0080\u0004\u0010\u00d1\u00e9\u00ca\u0089j=I\u00f9\u00a2FG{\u001fm\f\u00bd(2Fsnv\u00f9\u00cd\u00e5Wz?\u00fd\u0084\u00a9\u000f\u00c2\u00e0\u008f\u008e\u0001L\u00d7\u008c\u00176\u00ce\u0003\u00d5\u00c9\"KT}\u00d8\u001cv?\u008f\u00e9\u0003 8\u00db\u00c2\u0087\u000f\u001e\u00d8\u008bt\u0018\u00f7\u00b3k2\u009d\u00e2&\r\u00bc\u009c\u00aa\u00fb\"-\u00b8N\u00d9.\u00e4\u00e0\u00049\u0010\u00f0\u0003\u00c129\u00ad\u00ef\u0094\u00e3\u008d\u00e3\u0093s,\u0081\u00928\u009f\u00eb\u001f\u00faY\u00c42e\u00eeS\u00c7J\u00ffm\u00b0\u0096)\u0098\u00cd`V\u0096Inie\u00c4\u00fd\u0095uA\u0016F\u0012\u00f1>\u00c4S-\u0005\u00bd\u007f\u009bKh\u009dD\u009a\u00eb\u0011\u00c5\u00dcb\u00ec\u0006;\u00108;\u00baa\u00b8\u00a4\u00cc\u009e%tF\u00fa\u009f\u0094e\u0080\u0010\u00aa\u00d0s\u00f5~O!\n\u00dd\u00a3\u00caR\u009d\u00fa\u00c8@\u0010\u009f\u00c4>,\u00a3 X\u00cbxq\u0015+r\u00ae]D\u0010\u00a3\u00cd\u001c\u00f9\u00dcD\u00a8e=\u0087\u00cb\u0089\u0091e\u0081\u00068\u00132\u00ec\u00d3\b\u00dcr\tv\u001b\u0082o\u00ebV\u00e7p\u00c2\u00cc\u0086R\u00b8M\u00a3J\u00d2\u00dbJ\u0099\u0099\u0099[\u00cb,\u00bc\u00b9\u00eb\u00d7\u00c2\u0013\u00fb\u0004\u00d5\u0017\u001b\u0014\u0015Q=z\u0000\n|\u0011T\u00ce\u00aaH\u00f3\u009b\u00c4\u00cb\u00dc\u00feZ\u00e3\u00f3(\u00060\u00e5\u00d7\u00dbu[,\u0089x\u00a0\u00c3t\u00e4\u0013\u008a\u008eV\u00dd\u009fM^\u00b1\u00fc\u00da\u00f7\u0010\u009a\u0094\u0001h\u00f9N\u0016\u00ab\u00aa\u00cd2\u00d4,\u00b4n\u00a0\u00f8'\n\u00ef\u00c9\u00e0\u000f\u00ceg%\u0015\u00ca\u0090\u00cd'\u00f1\u00934\u0003\u0010\u009c\fa\u00b44\u00de\u00ae\u00d8'.\u00d9\u009b\u0003\u00f3\u00bfZ\u0010\u00cbVo\u00b1\u00f4e\u00cddJ\u00f1\u0086|dk\u00a6)\u0010\u00b9i\u0082u\u00dd\f\u00ed\u008di\u00e9d\u0014\u00e4\t\u00a0_ \u00aa\u0010\u0018\u00aa\u0087o\t\u0099E\u00a0\u00d63\u00e5\u00adz\u00a1\u00c0u\u008e6E\"d\u00eb\r\u00b1\u00a3\u00ba\u00a5x\u0004\u00ca A\u000b@Y\u00a0\u008eI\u00f2\u00e8J\u00eb\\ \"\u00cb\u00f2\u00b9\u00ce\u009f\u00b3FG\u0017\u001c\u00f8F\u00e0\u0006Y\u00ae\u00b0~\u0010d\u0098\u00b5\u00b3w\u00a9_)C'w\u00f2\u00b6\u00e8\u00a7\u0015(\u008d\u00d2\u0013\u00f9^\u00c2zi\u00b6T\u00ac_n\u000e\u00ea\u00a0}p\u00fc<p\u00b7\u000fo\u00bf=\u00bb\u009d5\u00e0\u00f2e\u00ab \u0013\u00e3\u0007F\u00e3\u00d9 >~d-A\u0094i\u00f6\u00c4\u001d\u00fa\u00d0m\u00f7\u00d5\u00fc\u00b4~\u00a5\u0098x1\u00bdz\u0019\u009f\u0018\u00ad\u0017\u00998\u00f9\u0010\u0091\u001ew\u00e3\u0003t\u0080\u00da9\u00ebp\u0000\u00c3\u009d0U(%\u0016\u00ba\u00d2=\u00f4r\u000e\u00cd\u0000\u0013\u0010\u00df\u00c4\u00f0\u00a0\u00e3\u0003<\u00c8G\u008b\u00d0\u00c3N\u009a\u008b\u00fd|K\u00b3b\u00fe\u00a1\u0012\u008d\u00f5\u0010\u00f2\u0084\u0010\u0094\u0007\u000b.k\u00ed\u00d5\t\u00f0;\u00c1\u00a9\u00c7\u00a6t\u0092";
                        var17_6 = 703;
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
                            var18_3[var16_4++] = BindCommand.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00cd\u0097\u0092\u008c;\u00b1\u00fd\u008a\u00ce\u0014\u0004\u00dc\u0002\u00dd]\u00cf \u00baFF\"\u00cct\u00a1\u0010\u00f1\r\tm\u00d4\u00c0\u00ca_\u001d\u00dcN\u0090z\u00de\u00c0\u00ee`U\u000e'!\u00d2\u00d08";
                            var17_6 = 49;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = BindCommand.zkmDecodeUtf8(var19_9).intern();
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
                BindCommand.g = var18_3;
                BindCommand.h = new String[26];
                BindCommand.l = new HashMap<K, V>(13);
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
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "e\u001c;!\u00a4\u0000\u00db\u00eb\\8A\u0090\u000fVI\u00bf";
                var5_15 = 16;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
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
        BindCommand.j = var6_12;
        BindCommand.k = new Integer[2];
        BindCommand.INSTANCE = new BindCommand();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x25BA;
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
                throw new RuntimeException("su/catlean/ad", exception);
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
            BindCommand.h[n2] = BindCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BindCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ad" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3E9F;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])BindCommand.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    BindCommand.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ad", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            BindCommand.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = BindCommand.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/ad" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BindCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(BindCommand.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

