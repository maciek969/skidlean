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
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.Util;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.TickEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.config.ConfigFiles;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.misc.AutoEz$WhenMappings;
import su.catlean.module.misc.AutoEzMode;
import su.catlean.module.misc.TeamCommunication;
import su.catlean.setting.ButtonSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.StringListSetting;
import su.catlean.setting.TextSetting;
import su.catlean.setting.TextValue;
import su.catlean.util.ActionHolder;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.Timer;
import su.catlean.util.combat.TargetUtil;
import su.catlean.zkm.ZkmKeys;

public final class AutoEz
extends Module {
    @NotNull
    public static final AutoEz INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final File messagesFile;
    @NotNull
    private static final File messagesFile2;
    @NotNull
    private static final StringListSetting notation$delegate;
    @NotNull
    private static final ButtonSetting openFile$delegate;
    @NotNull
    private static final ButtonSetting openFileTotem$delegate;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final TextSetting whisperPrefix$delegate;
    @NotNull
    private static final Timer sendTimer;
    private static long A;
    private static long n;
    @NotNull
    private static List messages2;
    @NotNull
    private static List messages;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private AutoEz() {
        super("auto-ez", Categories.getMisc(), null, null);
    }

    private final TextValue getNotation() {
        return (TextValue)notation$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setNotation(TextValue textValue) {
        notation$delegate.setValue(this, delegatedProperties[0], textValue);
    }

    private final ActionHolder getOpenFile() {
        return (ActionHolder)openFile$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setOpenFile(ActionHolder actionHolder) {
        openFile$delegate.setValue(this, delegatedProperties[1], actionHolder);
    }

    private final ActionHolder getOpenFileTotem() {
        return (ActionHolder)openFileTotem$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setOpenFileTotem(ActionHolder actionHolder) {
        openFileTotem$delegate.setValue(this, delegatedProperties[2], actionHolder);
    }

    private final AutoEzMode getMode() {
        return (AutoEzMode)((Object)mode$delegate.getValue(this, delegatedProperties[3]));
    }

    private final void setMode(AutoEzMode autoEzMode) {
        mode$delegate.setValue(this, delegatedProperties[3], (Object)autoEzMode);
    }

    private final String getWhisperPrefix() {
        return (String)whisperPrefix$delegate.getValue(this, delegatedProperties[4]);
    }

    private final void setWhisperPrefix(String string) {
        whisperPrefix$delegate.setValue(this, delegatedProperties[4], string);
    }

    @Override
    public void onEnable(long l) {
        messages = this.loadMessages(messagesFile);
        messages2 = this.loadMessages(messagesFile2);
    }

    @Flow
    private final void onTick(TickEvent tickEvent) {
        if (!this.isInGame()) {
            return;
        }
        if (Globals.getPlayer().age % 60 == 0 && messagesFile.exists() && messagesFile.lastModified() > A) {
            messages = this.loadMessages(messagesFile);
            A = messagesFile.lastModified();
        }
        if (Globals.getPlayer().age % 60 == 0 && messagesFile2.exists() && messagesFile2.lastModified() > n) {
            messages2 = this.loadMessages(messagesFile2);
            n = messagesFile2.lastModified();
        }
        for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
            if (!Intrinsics.areEqual(abstractClientPlayerEntity, AutoCrystal.INSTANCE.getTarget()) && !Intrinsics.areEqual(abstractClientPlayerEntity, Aura.INSTANCE.getTarget()) || !abstractClientPlayerEntity.isDead() && !(abstractClientPlayerEntity.getHealth() <= 0.0f)) continue;
            String string = abstractClientPlayerEntity.getGameProfile().name();
            Intrinsics.checkNotNullExpressionValue(string, "name(...)");
            this.sendMessage(string, messages);
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block4: {
            Entity entity;
            if (!(receivePacket.getPacket() instanceof EntityStatusS2CPacket) || !this.isInGame()) break block4;
            if (((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus() == 3 || ((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus() == 60) {
                Entity entity2 = ((EntityStatusS2CPacket)receivePacket.getPacket()).getEntity(Globals.getWorld());
                if (entity2 != null && (Intrinsics.areEqual(entity = entity2, AutoCrystal.INSTANCE.getTarget()) || Intrinsics.areEqual(entity, Aura.INSTANCE.getTarget())) && entity instanceof PlayerEntity) {
                    INSTANCE.sendMessage(String.valueOf(((PlayerEntity)entity).getName().getLiteralString()), messages);
                }
            }
            if (((EntityStatusS2CPacket)receivePacket.getPacket()).getStatus() == 35) {
                Entity entity3 = ((EntityStatusS2CPacket)receivePacket.getPacket()).getEntity(Globals.getWorld());
                if (entity3 != null && (Intrinsics.areEqual(entity = entity3, AutoCrystal.INSTANCE.getTarget()) || Intrinsics.areEqual(entity, Aura.INSTANCE.getTarget()))) {
                    INSTANCE.sendMessage(String.valueOf(entity.getName().getLiteralString()), messages2);
                }
            }
        }
    }

    private final void sendMessage(String string, List list) {
        TeamCommunication.INSTANCE.getAtomicBoolean().set(true);
        if (sendTimer.hasPassed(450) && !((Collection)list).isEmpty()) {
            String string2 = StringsKt.replace$default(StringsKt.replace$default((String)CollectionsKt.random(list, Random.Default), "%name%", string, false, 4, null), "%count%", String.valueOf(TargetUtil.INSTANCE.getPopsByName(string)), false, 4, null);
            if (StringsKt.startsWith$default(string2, "/", false, 2, null)) {
                String string3 = string2;
                int n = 0;
                int n2 = 1;
                string2 = ((Object)StringsKt.removeRange((CharSequence)string3, n, n2)).toString();
                Globals.getNetworkHandler().sendChatCommand(string2);
            } else {
                switch (AutoEz$WhenMappings.A[this.getMode().ordinal()]) {
                    case 1: {
                        Globals.getNetworkHandler().sendChatMessage("!" + string2);
                        break;
                    }
                    case 2: {
                        Globals.getNetworkHandler().sendChatMessage(string2);
                        break;
                    }
                    case 3: {
                        Globals.getNetworkHandler().sendChatCommand(this.getWhisperPrefix() + " " + string + " " + string2);
                        break;
                    }
                    case 4: {
                        ModuleMessages.S(this, string2, false, null);
                        break;
                    }
                    default: {
                        throw new NoWhenBranchMatchedException();
                    }
                }
            }
            sendTimer.reset();
        } else if (list.isEmpty()) {
            ModuleMessages.S(this, ModuleMessages.i18n$default(this, "empty", new Object[0], false, 4, null), false, null);
        }
        TeamCommunication.INSTANCE.getAtomicBoolean().set(false);
    }

    private final List loadMessages(File file) {
        String string;
        Object t;
        if (file.createNewFile()) {
            return CollectionsKt.emptyList();
        }
        Object object = file;
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

    private static final Unit _clinit_$lambda$1() {
        File file;
        File file2 = file = messagesFile2;
        Util.OperatingSystem operatingSystem = Util.getOperatingSystem();
        if (!file2.exists()) {
            file2.createNewFile();
        }
        operatingSystem.open(file);
        return Unit.INSTANCE;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == AutoEzMode.WHISPERS;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoEz.a = ZkmKeys.a(-2121401685907135450L, -8491782006441770304L, MethodHandles.lookup().lookupClass()).a(69608118360521L);
                        var20 = AutoEz.a ^ 90648174625199L;
                        AutoEz.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[24];
                        var16_4 = 0;
                        var15_5 = "+\u0090\u00ea\u000e\u00f9S\u008d>_-\u008c\u00bfj6AA\u0006\u00d4\u00d1L\u00d6\u009f\u00dfo%4\u00ca)\u00c8+\u00eaN\u00ad\u00eb\u0082/ZG\u00d1\u0019\u00ab\u00a0Y&\u0098[4n\u00c1o\u00d6^\u00ab\u008c\u0094\u00edm\u00df*dQ|\u001b\u00c9\u00fb\u009b\u00e0\u00c6Oe,R\u0018\u008aL\u00ea\u00a0\u00c4\u00be\u00cb\u009d\t\u0004\u00c79\u00baH+\u0097\u0089\u00ba\u001b\u00d3\u0018\u0084G\u00aa\u0010S\u00a6\u0015\u009e\u00c3\u00ce4U\u0098\u001d;\u00b6\u0010m\u0086q@\u00a9\u000eK\u0014\u0013V\u00da\u00de\nx*\b\u00bbjP\u007f\u00a5\u009f\u00ebYQC\u00cfS|\u00d4+\u00c5U\u000eZ=\u00af\u0080\u0093\u00f9\u00c7\u00db\u00dc\u00e8\u00c0Ik\u009a>^]\u00f0i\u0006\u00f0\u00cc\u00d8\u00b5r\u00d8\u00da\u0091\u00e8\u00c74\u00bd\tD8\u00c2>\u00fdOp>B*\u0006\u008b\u00d9vH=0\u00e2\u001c\u00c3\u00e2\u0012\u00b2\u00e6\u00cf\u00d1@\b\u00df\u00f8b\u0091\u0007\u009c\u00b9,,^\u001e\u00fc\u00c1(1\u0089\u00fe\u00e4*\u00aa|h\u00a4)\u00fd-\u00cf\u0081_\u00a48\u00a0\u00cd\u008c\u0087\u009d\u0089\u00eb\u00fb\u00f4A\u00ee\u00b0\u00f8q\u0096\u0005\u0085\u00c2\u00a27%\u00c9+U\u001cc!\u00c1\u001aY\u001c4\u00f4\u008e\u00cd\bJ\u0097\u00ee\u009f\u00e1\u00b6\u00d1\u0006\n\u00931p\u0090\u00b2\u00c5\u00e8;$\u00e7;\u0010\u0014\u00b1Iu-?\u00db\u00f8\u00d7\u0085\u00eep6c\u00ddO\u0018\u00d5\u0084\u00cb\u00dbM\u00af\u00f57m\u00c5\u00c3\u009e-\u00a4H\u009c*\"\u0088o\u0013t\u00e9~\u0010R\u00ba\u009c\u00f0@[h\u0016\u0010\u00b8>\u00c8<w\u00a1\u0010\u0010\u00bcW;\u00d4;\u000ef\u0004`\u0093k{\u00d5A\u0082\u0084\u00181\u00a7\u00bf#c\u00d0#\b\u0091MF#mU\u00bfW\t\u00a0?\u0013D\u00e5^C(\u0005V#\u00fc\u0095\u0080\u00f9:\u00bfOu\u0093v\u00dbo\u00c2L\u0095D\u00fes\u009cU\u00d6_\u0092\u00a3\u00bf\u0084=\u00aa\u00a7\u0088\u00favo\u00dc\u001e\u00b8\u0093\u0010~\u00f2\u00c8\u0084\u00e3\u001f+\u0007\u00ebGhE\u00a2\u0007\u00f3\u00dc \fI\u00a36\u00aa\u0093\u00e7\u00bb\u00fcA\u0082k~:|\u00a1<\u00aa\u00c2\u00d8\u00b9\b\u00ccUt]\u00da\u00d8\u0003\u00e1/}\u0018x\u009a\u00f7\u009fQDO\u00b2\u0080\u00b3\u00ae\u0003d\u0005{pp?v\u0007\u00e8\u00b0b\u0093\u0010\u00a1\u00ed\u0007\u00db\u00c3\u00ce\u00d6\u000et0/\u00f7\u00fe\u0081=\u00be 7V\u008b\u0010u\u0096`r\u00b5\u00b0\u009e(\u0006\u00817\u00cc\u00156\b\u0086_\u00d3\u00afrd\u00ee\u0099p(\u0002\u00a5\u0085\u0010\u00f4>Q\u0085L4Q'M\u00dc\"\u0091\u00d5\b7\n Z\u00e9\u0015G\u00c4\u00dc\u008b\u00e4\u00cb\u0003Q\u00e6\u00d6\u00d9\u00afrx\u001fg\u009b\u00de\u00e9\u00ef;\u00db\u00b5\u009b!\u00cdz\u00ae\u00e0@\u00ed\u00a5l\u00c07\u0010F\u00fd\u009dE\u000fz\u00e9\u00ce3\u00e2h\u00a8\u00f7 m^\r#;i:\u00d3\u00c2\u0010O\u00dfL?3\u00a6K\u0097\u0091>\u00a0\nP{U\u00f71\u00e36\u0089q\u00fc\u0005o\u00fe\u00b7\u00e2O#\u00d6n\u00e1QX G\u00d9\u00c5\u0018\u0014}\u00b8\u0006\u00dak\u0017;Ve\u00b1\u00af\u0001\u009a\u008d\u0082\u008e\u00f1\u00a4\u000b\u00c4I\u00c2\u00a1\u00e0\u00e3\u00d6\u00b0\u0018\u0089\u0089\u00d6\u000f\u00a3\u0019\u00c2~\u00f5\u0004c~\u00aewz\u00ba\n\u0002\u00a6CW;\u00ac1";
                        var17_6 = 733;
                        var14_7 = 72;
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
                            var18_3[var16_4++] = AutoEz.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u001b\r\u0098\u001f\u000bJ'\u0016\u00aa}\u00e38T\u00a3\u0000\u009b8F\u001a7JJo!\u00fb\u00fal\u00e0\u007fV8\u008e\u00cb\u00aa<\u00f8\u00a0\u00aa\u00c8\u0002\u0098\u00bc\u0095\u00a1\u0080\u0013B9\u00d6Z\u001e\t\u009f\u00c3\u001b'\u0092\u00a6\u00f58(5B\u00c3\u0019\u00a6\u00883f\u0084\u00a8Rr";
                            var17_6 = 73;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = AutoEz.zkmDecodeUtf8(var19_9).intern();
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
                AutoEz.b = var18_3;
                AutoEz.d = new String[24];
                AutoEz.h = new HashMap<K, V>(13);
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
                var6_12 = new long[7];
                var3_13 = 0;
                var4_14 = " \u00b5\u0099)\u00ff\u000e\u00b4E6\u00c2\"\u00f9\u0016\u001b\u00b7\u00e8^\"l2G\u00a1\u00daR\u0001\u00cc\u000bn\u00da\u00d2\u00b1\u00fcet\u00ed2\u00dd\u00d8\u00a0\u00f9";
                var5_15 = 40;
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
                    var4_14 = "IC\u00cc\u000b&d\u00f5\u00e2\u0019\u009da\u0010\u008e:|\u00ad";
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
        AutoEz.f = var6_12;
        AutoEz.g = new Integer[7];
        var39_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoEz.class, "notation", "getNotation()Lsu/catlean/oi;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoEz.class, "openFile", "getOpenFile()Lsu/catlean/id;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoEz.class, "openFileTotem", "getOpenFileTotem()Lsu/catlean/id;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoEz.class, "mode", "getMode()Lsu/catlean/ak;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoEz.class, "whisperPrefix", "getWhisperPrefix()Ljava/lang/String;", 0))};
        AutoEz.delegatedProperties = var39_20;
        AutoEz.INSTANCE = new AutoEz();
        AutoEz.messagesFile = new File(ConfigFiles.getCtlDir(), "autoez.txt");
        AutoEz.messagesFile2 = new File(ConfigFiles.getCtlDir(), "autoez_totem.txt");
        var34_21 = null;
        var35_22 = 12;
        var36_23 = null;
        var37_24 = null;
        var38_25 = new TextValue("%name% will be replaced with player name");
        AutoEz.notation$delegate = SettingFactory.stringListSetting$default(AutoEz.INSTANCE, "notation", var38_25, var37_24, var36_23, var35_22, var34_21);
        AutoEz.openFile$delegate = SettingFactory.buttonSetting$default(AutoEz.INSTANCE, "open-death", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)(), null, null, 12, null);
        AutoEz.openFileTotem$delegate = SettingFactory.buttonSetting$default(AutoEz.INSTANCE, "open-totem-pop", (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$1(), ()Lkotlin/Unit;)(), null, null, 12, null);
        AutoEz.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoEz.INSTANCE, "mode", (Enum)AutoEzMode.GLOBAL, null, null, 12, null);
        AutoEz.whisperPrefix$delegate = SettingFactory.textSetting$default(AutoEz.INSTANCE, "prefix", "msg", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        AutoEz.sendTimer = new Timer();
        AutoEz.messages2 = CollectionsKt.emptyList();
        AutoEz.messages = CollectionsKt.emptyList();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3C58;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/qx", exception);
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
            AutoEz.d[n2] = AutoEz.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoEz.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/qx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x441A;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
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
                throw new RuntimeException("su/catlean/qx", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoEz.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoEz.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/qx" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoEz.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoEz.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

