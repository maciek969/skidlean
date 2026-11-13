/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.io.File;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import java.util.Map;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.config.ConfigFiles;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.Spammer$WhenMappings;
import su.catlean.module.misc.SpammerMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.ButtonSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.util.ActionHolder;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class Spammer
extends Module {
    @NotNull
    public static final Spammer INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ButtonSetting openFile$delegate;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final TextSetting whisperPrefix$delegate;
    @NotNull
    private static final BooleanSetting antiSpam$delegate;
    @NotNull
    private static final FloatSetting delay$delegate;
    @NotNull
    private static final Timer sendTimer;
    @NotNull
    private static List messages;
    private static long f;
    @NotNull
    private static final File messagesFile;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] g;
    private static final Map h;

    private Spammer() {
        super("spammer", Categories.getMisc(), null, null);
    }

    private final ActionHolder getOpenFile() {
        return (ActionHolder)openFile$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setOpenFile(ActionHolder actionHolder) {
        openFile$delegate.setValue(this, delegatedProperties[0], actionHolder);
    }

    private final SpammerMode getMode() {
        return (SpammerMode)((Object)mode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final void setMode(SpammerMode spammerMode) {
        mode$delegate.setValue(this, delegatedProperties[1], (Object)spammerMode);
    }

    private final String getWhisperPrefix() {
        return (String)whisperPrefix$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setWhisperPrefix(String string) {
        whisperPrefix$delegate.setValue(this, delegatedProperties[2], string);
    }

    private final boolean getAntiSpam() {
        return (Boolean)antiSpam$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setAntiSpam(boolean bl) {
        antiSpam$delegate.setValue(this, delegatedProperties[3], bl);
    }

    private final float getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[4])).floatValue();
    }

    private final void setDelay(float f) {
        delay$delegate.setValue(this, delegatedProperties[4], Float.valueOf(f));
    }

    @Override
    public void onEnable(long l) {
        messages = this.loadMessages();
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        block9: {
            long l;
            block8: {
                long l2 = a ^ 0x720173B67171L;
                l = l2 ^ 0x69F5302808C9L;
                Intrinsics.checkNotNullParameter(playerUpdateEvent, "event");
                if (Globals.getPlayer().age % 60 == 0 && messagesFile.exists() && messagesFile.lastModified() > f) {
                    messages = this.loadMessages();
                }
                if (!sendTimer.hasPassed((int)(this.getDelay() * 1000.0f)) || !(!((Collection)messages).isEmpty())) break block8;
                Object object = CollectionsKt.random(messages, Random.Default) + (String)(this.getAntiSpam() ? " " + this.randomSuffix() : "");
                if (StringsKt.startsWith$default((String)object, "/", false, 2, null)) {
                    String string = object;
                    int n = 0;
                    int n2 = 1;
                    object = ((Object)StringsKt.removeRange((CharSequence)string, n, n2)).toString();
                    Globals.getNetworkHandler().sendChatCommand((String)object);
                } else {
                    switch (Spammer$WhenMappings.t[this.getMode().ordinal()]) {
                        case 1: {
                            Globals.getNetworkHandler().sendChatMessage("!" + (String)object);
                            break;
                        }
                        case 2: {
                            Globals.getNetworkHandler().sendChatMessage((String)object);
                            break;
                        }
                        case 3: {
                            Globals.getNetworkHandler().sendChatCommand(this.getWhisperPrefix() + " " + this.getOnlinePlayerNames() + " " + (String)object);
                            break;
                        }
                        default: {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                }
                sendTimer.reset();
                break block9;
            }
            if (!messages.isEmpty()) break block9;
            this.disableWithMessage(ModuleMessages.i18n$default(this, "empty", new Object[0], false, 4, null), l);
        }
    }

    private final String getOnlinePlayerNames() {
        Collection<PlayerListEntry> collection = Globals.getNetworkHandler().getPlayerList();
        Intrinsics.checkNotNullExpressionValue(collection, "getOnlinePlayers(...)");
        return (String)CollectionsKt.randomOrNull((Collection)SequencesKt.toList(SequencesKt.filter(SequencesKt.map(CollectionsKt.asSequence((Iterable)collection), Spammer::getPlayerName), Spammer::isValidName)), Random.Default);
    }

    private final String randomSuffix() {
        return "[" + (char)(Random.Default.nextInt(26) + 97) + Random.Default.nextInt(10) + (char)(Random.Default.nextInt(26) + 97) + "]";
    }

    private final List loadMessages() {
        String string;
        Object t;
        if (messagesFile.createNewFile()) {
            return CollectionsKt.emptyList();
        }
        f = messagesFile.lastModified();
        Object object = messagesFile;
        Object object2 = object;
        if (!((File)object2).exists()) {
            ((File)object2).createNewFile();
        }
        Object object3 = object = (Iterable)FilesKt.readLines$default((File)object, null, 1, null);
        Collection collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(object, 10));
        Iterator iterator2 = object3.iterator();
        while (iterator2.hasNext()) {
            t = iterator2.next();
            string = (String)t;
            Collection collection2 = collection;
            collection2.add(((Object)StringsKt.trim((CharSequence)string)).toString());
        }
        object3 = object = (Iterable)((List)collection);
        collection = new ArrayList();
        iterator2 = object3.iterator();
        while (iterator2.hasNext()) {
            t = iterator2.next();
            string = (String)t;
            if (!(!StringsKt.isBlank(string))) continue;
            collection.add(t);
        }
        return (List)collection;
    }

    private static final Unit _clinit_$lambda$0() {
        File file;
        File file2 = file = messagesFile;
        Util.OperatingSystem operatingSystem = Util.getOperatingSystem();
        if (!file2.exists()) {
            file2.createNewFile();
        }
        operatingSystem.open(file);
        return Unit.INSTANCE;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == SpammerMode.WHISPERS;
    }

    private static final String getPlayerName(PlayerListEntry playerListEntry) {
        Intrinsics.checkNotNullParameter(playerListEntry, "it");
        return playerListEntry.getProfile().name();
    }

    private static final boolean isValidName(String string) {
        return Pattern.matches("^\\w{3,16}$", string);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Spammer.a = ZkmKeys.a(-7160817432951704485L, 4536008389300968231L, MethodHandles.lookup().lookupClass()).a(221338847224971L);
                        var20 = Spammer.a ^ 36452585727084L;
                        Spammer.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[23];
                        var16_4 = 0;
                        var15_5 = "\u00c3+\u00aa\u00e8%jL\u00cf\u008c\u001bF>%sdp\u00b0\u00a5LN\u00bcd\u0083\u0003\u0013F\u00f6\u008a\u00b0\u00e7\u00a2\u00e6 \u00c4\u00b2#\u00be\u00ed\u0086\u00b5\u0002\u00ed\u00d1AUGi\u00a9c \u0091\u00fc\u00eb\u0006\u00a7\u00d3S\u0013%\u00eb\u00b0ikg'\u0010\u0011\u00cc\u00a9\u0000h\\\u008f:\u0012\u00a6j6xBy\u009d\u0010L\u00be?.Ah\u00fag\u00e2\u00da\u00e3\u00f6\u0001\u0094\u00c81\u00100gV\u0016\u00c1\bB\u00a6r'\u00c7Z\"\u00a3\u00d6~\u0010\\Q\u00bbbC\u00be\u008ap\u00ae\u00c1\u0012\u0093^b\b.0\u0019qX\\l\u00b5\u00f4}\u00a8\u0011\u00e1\u00914\u00d4\u00d4Z\u00d8&pI\u0089\u00e6\u00d8\u0089\u00caD\u0087\u009c\u001c\u00b0\u009d\u0002BZB\u001a\u00af\u00cd\u0097\u008d\u00d2d\u00d1\u00e4d<\u00a7\u0091@\t.B\u00efY\u00d6hg\u00ee\u008e\u0080\u00b3\u00ab\u00ed\u00a1\u0087]\u001f\u001c\u0005Pw17t.LI\u0015U?\u00c0r\u00a4\u0085\u0002\u00b1\u00d8O\u00ef\u00cc\u00c4\u00ad\u00af^\u0081\u00c1Q\u00f4\u0011\u0006\u00c6\u00f4VU.\u0082j#[g\u00fbb\u00d6\u0018K-\u008drS\u00e8\u00fc\u00b3i$\u00e7\u001d\u008f\u009cN\u00f5\\\u00ba\u00b1g\u00a8\u0017\u008c\u0083\u0010\u00c5\u00b3__m(\u0016%\u00b4\u0016]b\u0082\u0014\u00a5Y8~\u00d4\u0091\u00d8f%\u0089\u00c4\u0087Z\u0089\u00cb\u0004\u0080R\u00b0\u00f1\u0096\u00b7\u0095h\rjCf\u009d\u00e8h\u00af\u00e4\u0086\u00a57#\t\u008b\u00c4%\u00d5\u001c\u0087Sc\u00e96ZD\f\f\u00f8\u001b\u00d6\u00bc\u00b8E\u00cb\u0010i\u0018\u00068Q#\u001f\u00b92<\u0083\u00bb\u009aSM\u00eb\u0010\u009b\u0084&y\u0098\u00fb\u00c2_E$\u00c8 \u0088\u00ff\u00fbw\u0010.\u00aa\u00e5\u0084\u00a0\u0085\u00a9\u00907A\u00d1\u00140\u009d\u00b7\u00a0 \u00a3\u009aF\u00dfS8@\u0010\u000eY\u008f\u00e9^\u00d9\u0088\u00d6Q\u00c3\u0088\u00d8\u0082\u00cf\u00b8\u00de(\u00fa\u00de\u00bc\u00be~\f\u00e9 8)\u000b\u00a1\u00ce\u00f5`\u0097\u00bc\u00fb\u009d5\u00c8\u00c7\u00fd=1\u00ef\u00ce\u0097?@\u00d4c\u0000\u00e5(V\u001b\u0001\u008d((J:4\u00f9\u008e\u008c9LT\u008c\u00a0\u0085'y\u00b4B7\u00e3l\u0094\u0006\u00dd\"\u00b2\u0003)\u00fa*\u00fb\u00b9\u00a0)\u00a2\u00d7\u00ee\u0014\u00ff\u00f3\u00a7\u00b8 d\u00e1\u001c\u00ed\u00b2\u00f1w\u00f8Ro}\u00a6\f\u0093\n\u0019\u0081$\u00f2?=\u0094\u001f\u00dd\b\u0086\u00b5\u00bc2W\u00d7\u0010\u0010\u0081O\u0081\u00b3\u00d0\u00df \u00f4c\u00b3\u00d5(T\u000eZ} \u0019F\u0085\u00aa\u00b8\u00aaj\u008dz\u0093\u00dd\u00fa\u00da\u00a7\u00a6Yl\u008f\u0004\u00b8\u00bb\u001f\u00e7\u0010\u00e8\u00fej]o\u00ad\u00d46\u0018\u00cc\u00f0e\u0005g)\u00bb\u001f&\u0004\u00b3K\u00aa\u008dv\u0088\u00a1^\u0084\u00b0\r.z\u00f4";
                        var17_6 = 612;
                        var14_7 = 32;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Spammer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "R\u0083\u00e3\u00d4\u001c\u00f7\u00d1*\u00cbm<cF\u00c5,a\u007f\u008e\u00bcEy\u00e4b\u00bb6\u00a10zICH-\u0010\u00e2K\u0088fe_\u0089\u00ad\u0006\u00a5\u0019h\u00064\u0099\u008c";
                            var17_6 = 49;
                            var14_7 = 32;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Spammer.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
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
                Spammer.b = var18_3;
                Spammer.c = new String[23];
                Spammer.h = new HashMap<K, V>(13);
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
                var6_12 = new long[11];
                var3_13 = 0;
                var4_14 = "\u00f5v4\u00a9m\f\u000f\u0015uQ\bG\u00be\u0010\u00a7\u00cb\u00ca\u00f9\u008e\u00f4\u00a1\u00fb\u00ed\u00af\u000e\u00ce\u00b8D\u0019\u00a9U\u000bo\u0019\u00ab\u00e1\u00b9\u00f0\u00f7\u00a1&,Dz\u00d6GG\u00da\u00995v\u00a3\u00a6\u00d8\u00da\u008e\u00cd>S\u001f\u00d2S\u00d4\u0097\u00b7\u00a9)\u00bb\u00dea\u00f9Q";
                var5_15 = 72;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "\u00d8p0,\u00df\u00ed{J\u0088W6r\u00ad\u00b5\u00bat";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl104:
                // 1 sources

                ** continue;
            }
        }
        Spammer.e = var6_12;
        Spammer.g = new Integer[11];
        var35_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Spammer.class, "openFile", "getOpenFile()Lsu/catlean/id;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Spammer.class, "mode", "getMode()Lsu/catlean/lw;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Spammer.class, "whisperPrefix", "getWhisperPrefix()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Spammer.class, "antiSpam", "getAntiSpam()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Spammer.class, "delay", "getDelay()F", 0))};
        Spammer.delegatedProperties = var35_20;
        Spammer.INSTANCE = new Spammer();
        Spammer.openFile$delegate = SettingFactory.buttonSetting$default(Spammer.INSTANCE, "open-file", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)(), null, null, 12, null);
        Spammer.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Spammer.INSTANCE, "mode", (Enum)SpammerMode.GLOBAL, null, null, 12, null);
        Spammer.whisperPrefix$delegate = SettingFactory.textSetting$default(Spammer.INSTANCE, "prefix", "msg", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Spammer.antiSpam$delegate = SettingFactory.booleanSetting$default((SettingContainer)Spammer.INSTANCE, "anti-spam", false, null, null, 12, null);
        Spammer.delay$delegate = SettingFactory.floatSetting$default(Spammer.INSTANCE, "delay", 5.0f, RangesKt.rangeTo(0.0f, 30.0f), null, null, 24, null);
        Spammer.sendTimer = new Timer();
        Spammer.messages = CollectionsKt.emptyList();
        Spammer.messagesFile = new File(ConfigFiles.getCtlDir(), "spammer.txt");
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x50B4;
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
                throw new RuntimeException("su/catlean/qz", exception);
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
            Spammer.c[n2] = Spammer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Spammer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x17F2;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qz", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Spammer.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Spammer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Spammer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Spammer.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

