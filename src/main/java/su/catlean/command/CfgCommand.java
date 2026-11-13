/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 */
package su.catlean.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import su.catlean.command.Command;
import su.catlean.command.ConfigArgumentType;
import su.catlean.config.ConfigIO;
import su.catlean.config.ConfigStore;
import su.catlean.config.ConfigType;
import su.catlean.config.ProfileConfig;
import su.catlean.module.Module;
import su.catlean.module.ModuleManager;
import su.catlean.setting.Setting;
import su.catlean.util.I18n;
import su.catlean.zkm.ZkmKeys;

public final class CfgCommand
extends Command {
    @NotNull
    public static final CfgCommand INSTANCE;
    private static final long b;
    private static final String[] g;
    private static final String[] h;
    private static final Map i;

    private CfgCommand() {
        super("cfg");
    }

    @Override
    public void build(@NotNull LiteralArgumentBuilder literalArgumentBuilder) {
        Intrinsics.checkNotNullParameter(literalArgumentBuilder, "builder");
        literalArgumentBuilder.then(this.literal("list").executes(CfgCommand::build$lambda$0));
        literalArgumentBuilder.then(this.literal("folder").executes(CfgCommand::build$lambda$1));
        literalArgumentBuilder.then(this.literal("reset").executes(CfgCommand::build$lambda$2));
        LiteralArgumentBuilder literalArgumentBuilder2 = this.literal("save");
        StringArgumentType stringArgumentType = StringArgumentType.word();
        Intrinsics.checkNotNullExpressionValue(stringArgumentType, "word(...)");
        literalArgumentBuilder.then(literalArgumentBuilder2.then(this.argument("name", (ArgumentType)stringArgumentType).executes(CfgCommand::build$lambda$3)));
        literalArgumentBuilder.then(this.literal("load").then(this.argument("name", ConfigArgumentType.Companion.create()).executes(CfgCommand::build$lambda$4)));
        literalArgumentBuilder.then(this.literal("remove").then(this.argument("name", ConfigArgumentType.Companion.create()).executes(CfgCommand::build$lambda$5)));
        Object var6_2 = null;
        literalArgumentBuilder.executes(CfgCommand::build$lambda$6);
    }

    private static final int build$lambda$0(CommandContext commandContext) {
        Object[] objectArray = new Object[]{};
        String string = "commands.config.configs";
        StringBuilder stringBuilder = new StringBuilder(I18n.INSTANCE.translate(string, objectArray));
        Object var12_4 = null;
        for (ProfileConfig profileConfig : ConfigStore.INSTANCE.getProfileConfigFile().getData()) {
            stringBuilder.append(Formatting.RESET).append("\n" + (Intrinsics.areEqual(profileConfig, ConfigStore.INSTANCE.getProfileConfigFile().getCurrent()) ? Formatting.GREEN : "")).append(profileConfig.getId()).append(Formatting.RESET);
        }
        String string2 = stringBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        INSTANCE.sendMessage(string2);
        return 1;
    }

    private static final int build$lambda$1(CommandContext commandContext) {
        try {
            File file;
            File file2 = file = ConfigStore.INSTANCE.getProfileConfigFile().U();
            Util.OperatingSystem operatingSystem = Util.getOperatingSystem();
            if (!file2.exists()) {
                file2.createNewFile();
            }
            operatingSystem.open(file);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return 1;
    }

    private static final int build$lambda$2(CommandContext commandContext) {
        long l = b ^ 0x46EAD38A48AL;
        long l2 = l ^ 0x44E153B043EEL;
        Iterator iterator2 = ModuleManager.INSTANCE.L().iterator();
        Intrinsics.checkNotNullExpressionValue(iterator2, "iterator(...)");
        Iterator iterator3 = iterator2;
        Object var7_4 = null;
        while (iterator3.hasNext()) {
            Object e = iterator3.next();
            Intrinsics.checkNotNullExpressionValue(e, "next(...)");
            Module module = (Module)e;
            if (module.getEnabled()) {
                module.disable(l2);
            }
            for (Setting setting2 : module.getSettings()) {
                setting2.reset();
            }
        }
        return 1;
    }

    private static final int build$lambda$3(CommandContext commandContext) {
        long l = b ^ 0x4407648B21FFL;
        long l2 = l ^ 0x7AF4A163D38BL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 16 >>> 32);
        int n3 = (int)(l2 << 48 >>> 48);
        String string = (String)commandContext.getArgument("name", String.class);
        Intrinsics.checkNotNull(string);
        ConfigIO.save(string, ConfigType.MODULES, (char)n, n2, (short)n3);
        Object[] objectArray = new Object[]{string};
        INSTANCE.sendMessage(I18n.INSTANCE.translate("commands.config.saved", objectArray));
        return 1;
    }

    private static final int build$lambda$4(CommandContext commandContext) {
        long l = b ^ 0x4729FB8D20B8L;
        long l2 = l ^ 0x23CFB6593F9CL;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        String string = (String)commandContext.getArgument("name", String.class);
        Intrinsics.checkNotNull(string);
        ConfigIO.load(string, ConfigType.MODULES, n, (short)n2, n3);
        Object[] objectArray = new Object[]{string};
        INSTANCE.sendMessage(I18n.INSTANCE.translate("commands.config.loaded", objectArray));
        return 1;
    }

    private static final int build$lambda$5(CommandContext commandContext) {
        long l = b ^ 0x6EC7512BA079L;
        long l2 = l ^ 0x1E096FA591F0L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 56);
        int n3 = (int)(l2 << 40 >>> 40);
        String string = (String)commandContext.getArgument("name", String.class);
        Intrinsics.checkNotNull(string);
        ConfigIO.delete(n, (byte)n2, string, ConfigType.MODULES, n3);
        Object[] objectArray = new Object[]{string};
        INSTANCE.sendMessage(I18n.INSTANCE.translate("commands.config.removed", objectArray));
        return 1;
    }

    private static final int build$lambda$6(CommandContext commandContext) {
        INSTANCE.sendMessage(Formatting.RED + "cfg <load/remove/save> <id> / cfg list");
        return 1;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                CfgCommand.b = ZkmKeys.a(-39453276538997297L, 1984806815644247958L, MethodHandles.lookup().lookupClass()).a(78635923953557L);
                var9 = CfgCommand.b ^ 124459526698068L;
                CfgCommand.i = new HashMap<K, V>(13);
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
                var7_3 = new String[19];
                var5_4 = 0;
                var4_5 = "\u00c9\f\u00da\u00c4\u00ef\u00ebp\u0087n\u00d8\u00b8/\u00a2m\u00a6\u00d6\u00a1\u00cf\u0010\u00f8-\u00b8\\\u00c0\u0081Ja8\u00b2\u00db\u00f9\u00a9\u0010\u00ea\u00fd9\u0011S\u00c4\u001fj\u00ba\u00e4fs\u0099 \u001d\u001f\u0010S,\u0004[\u00d7\u00c1\u00aa\u001b\u0015\u00d4Jja\u0006W\u0095\u0010\u009fqX\u00eb\u00f7\u0083\u00e6r\u00a0\u0087\u00e3>\u000bx\u00bbY\u0010\u00df?\u00d6\u00d3\u00f8h\u001c\u00f8n\u00b8\u00f8.h\\\u0085%\u0010\u00dc!zwwxS\u00c2Z\u0004P\u001dO\u00f8\u0011i(2\u00f4\u009b\u00b9\u00ac\u001e\u00e9\u00f2\u0091 \u00de\u00ecl\u00cfZz\u008fR6K\u00e85&V0\u009d\u0003#\u00b4\u00f8D\u00c7\u0013\u00e6\u0081\n\u00ec\u0099{J@ \r\u00dd\u009e\u00e68\u00cc\u00aad+X\u00c0(w>\u00a8D<\u00b6\u0001j\u00f1\u0000\u00a4\u00c13\u00ea\u0099\u00f9\u0098\u00fd\u001e@\u00f7!|\u00ab*c\u008e\u00af\u0085\fn\u00f1\u0000`\u00f1\u00b5)\u008cs\u00e0\u00f1\u0005\u00cc\u00e3\u00ec\u0001\u0083r\u001d\u0016\u0096\u0010\u00d6\u00db\u00ab\u00cf/\u00fa\u00ca7Ii\u001a\u00ee\u00d6\u00b9\u00d1\u000e\u0010\u00ddq\u00feYE\u00fd:(n\u00bd\u00fe\u00d3\u00b1\u00a6\u00b9\u00bb(\u00f9\u00d4o/Y\u009c\u0082\u00f4P\u00a8\u00be[\f\u00d9-\u00d6.\u0014\u009c\u00a9\u00b1s\u00b0[\u00ee\u00a9\u00d70\u00fd\u00f0\u00e9\u0002\u00f6\u0017\u00a2j\u0097\u00ecBF \u00c2\u00ad\u009f\t\u009cE\u00f9\u0097\u00a5\u00ab\u0010\u00b6\u008c\u0089\u00a8%\u00afv'\u00d0\u0080\u009b\u009dmno\u00ee\u00a7\u001aev\u00de\u0018gy\u009a\u0094+qt\u00ed\u0007\u00dd\u00f5\u00ff\u009e\u0015\u00fc\u00f2\u00a2|\u0095\u0083W\\\u009b\u0098([\u00aaj\u00e9\u00d2\u0011*\u00db\u008c1\t\u00ce\u00ebS\u00b7\u008b\u00bc%pd\u009d\u00d0\u00aeU1\u00d5\u0090\u00b6\u00ca\u00ba\u00a6!\u009a\u00045[\\\u00c54\u00b2(\u0015\u001e\u00c7\u0017\u00fc\u008fz\u00b0\u00c6\u00a3\u0082I\u00b5\u00de\u0019\u00d1\u00a5o\u00cc\u00a9\u008c[\u00a6\u0005\u00ca0\u0095\u0015\u00f4\u00f3WS0\u00efr\u00f1\u0096\u00b9\u0084\u00aa\u0010\u00a7+\u00e6\u00d70.\u008c\u0016U@\u00c5\u00e8F\u00b3j\u0004\u0010<u\u000f\u0089\u0093\u00dc\u00fe\u00d6=\u00b1\u00b6\u00f7\u00b0\u008a\u00b6\f";
                var6_6 = 472;
                var3_7 = 32;
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
                    var7_3[var5_4++] = CfgCommand.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "j\u0091\u00ec\u00d0\u00ed\u001f\u0002\ro\u0016\u0091\u00fe\u00ac\u0086r\u009a[W\u001c\u00b4\u0084\u007f\u0000>w\u000b\u009f\u00fczv@\u000e\u0010\u0099\u00b1\n\u00bd\u00cek\u00e1\u00a7\u00aa\u0085\u00f1\u00c1T\u00f5\r\u00ea";
                    var6_6 = 49;
                    var3_7 = 32;
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
                    var7_3[var5_4++] = CfgCommand.zkmDecodeUtf8(var8_9).intern();
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
        CfgCommand.g = var7_3;
        CfgCommand.h = new String[19];
        CfgCommand.INSTANCE = new CfgCommand();
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4D7;
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
                throw new RuntimeException("su/catlean/ac", exception);
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
            CfgCommand.h[n2] = CfgCommand.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return h[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CfgCommand.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ac" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CfgCommand.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

