/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.AutoLeavePlayersMode;
import su.catlean.module.misc.AutoLeavePlayersMode$WhenMappings;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.PacketUtil;
import su.catlean.util.Timer;
import su.catlean.zkm.ZkmKeys;

public final class AutoLeave
extends Module {
    @NotNull
    public static final AutoLeave INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting autoDisable$delegate;
    @NotNull
    private static final BooleanSetting fastLeave$delegate;
    @NotNull
    private static final BooleanSetting lowHp$delegate;
    @NotNull
    private static final BooleanSetting totems$delegate;
    @NotNull
    private static final IntSetting minTotems$delegate;
    @NotNull
    private static final FloatSetting minHp$delegate;
    @NotNull
    private static final ModeSetting players$delegate;
    @NotNull
    private static final TextSetting command$delegate;
    @NotNull
    private static final IntSetting distance$delegate;
    @NotNull
    private static final Timer leaveTimer;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map k;

    private AutoLeave() {
        super("auto-leave", Categories.getMisc(), null, null);
    }

    private final boolean getAutoDisable() {
        return (Boolean)autoDisable$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getFastLeave() {
        return (Boolean)fastLeave$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getLowHp() {
        return (Boolean)lowHp$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getTotems() {
        return (Boolean)totems$delegate.getValue(this, delegatedProperties[3]);
    }

    private final int getMinTotems() {
        return ((Number)minTotems$delegate.getValue(this, delegatedProperties[4])).intValue();
    }

    private final float getMinHp() {
        return ((Number)minHp$delegate.getValue(this, delegatedProperties[5])).floatValue();
    }

    private final AutoLeavePlayersMode getPlayers() {
        return (AutoLeavePlayersMode)((Object)players$delegate.getValue(this, delegatedProperties[6]));
    }

    private final String getCommand() {
        return (String)command$delegate.getValue(this, delegatedProperties[7]);
    }

    private final void setCommand(String string) {
        int n = 0;
        long l = 0L;
        long l2 = ((long)n << 48 | l << 16 >>> 16) ^ a;
        command$delegate.setValue(this, delegatedProperties[AutoLeave.zkmBootstrap2("x", (int)11952, (long)(0x73D72A4B2AB60AF3L ^ l2))], string);
    }

    private final int getDistance() {
        return ((Number)distance$delegate.getValue(this, delegatedProperties[8])).intValue();
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l;
        long l2 = l = a ^ 0x1BE92093ED2FL;
        long l3 = l2 ^ 0x3BC89D42173L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 32);
        int n3 = (int)(l3 << 48 >>> 48);
        long l4 = l2 ^ 0x6419950FC1E9L;
        block5: for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
            Entity entity;
            if (Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getPlayer())) continue;
            Intrinsics.checkNotNull(abstractClientPlayerEntity);
            if (Friends.getIsHomie(abstractClientPlayerEntity) || this.getPlayers() == AutoLeavePlayersMode.None || !(this.getPos(entity = (Entity)Globals.getPlayer()).distanceTo(this.getPos(abstractClientPlayerEntity)) <= (double)this.getDistance())) continue;
            switch (AutoLeavePlayersMode$WhenMappings.y[this.getPlayers().ordinal()]) {
                case 1: {
                    if (leaveTimer.hasPassed2(1000)) {
                        if (this.getAutoDisable()) {
                            this.disable(l4);
                        }
                        ModuleMessages.S(this, ModuleMessages.i18n$default(this, "player", new Object[0], false, 4, null), false, null);
                        Globals.getPlayer().networkHandler.sendChatCommand(StringsKt.replace$default(this.getCommand(), "/", "", false, 4, null));
                    }
                    return;
                }
                case 2: {
                    this.leave((char)n, n2, (short)n3, ModuleMessages.i18n$default(this, "player", new Object[0], false, 4, null));
                    return;
                }
                case 3: {
                    continue block5;
                }
            }
            throw new NoWhenBranchMatchedException();
        }
        if (this.getTotems()) {
            Item item = Items.TOTEM_OF_UNDYING;
            Intrinsics.checkNotNullExpressionValue(item, "TOTEM_OF_UNDYING");
            Object var44_10 = null;
            boolean bl = false;
            Item item2 = item;
            if (InventoryUtil.findItemSlot$default(InventoryUtil.INSTANCE, item2, bl, var44_10) <= this.getMinTotems()) {
                this.leave((char)n, n2, (short)n3, ModuleMessages.i18n$default(this, "totems", new Object[0], false, 4, null));
            }
        }
        if (Globals.getPlayer().getHealth() < this.getMinHp() && this.getLowHp()) {
            this.leave((char)n, n2, (short)n3, ModuleMessages.i18n$default(this, "hp", new Object[0], false, 4, null));
        }
    }

    private final void leave(char c, int n, short s, String string) {
        long l = ((long)c << 48 | (long)n << 32 >>> 16 | (long)s << 48 >>> 48) ^ a;
        long l2 = l ^ 0x615399A31D36L;
        if (this.getAutoDisable()) {
            this.disableWithMessage(string, l2);
        }
        if (this.getFastLeave()) {
            PacketUtil.sendPacket(new UpdateSelectedSlotC2SPacket(-1337));
        }
        Globals.getNetworkHandler().getConnection().disconnect(Text.of("[CatLean] " + string));
    }

    private static final boolean settingVisible() {
        return INSTANCE.getTotems();
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getLowHp();
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getPlayers() == AutoLeavePlayersMode.Command;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getPlayers() != AutoLeavePlayersMode.None;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoLeave.a = ZkmKeys.a(-2046311430517982632L, -7281699710445923995L, MethodHandles.lookup().lookupClass()).a(238112899842062L);
                        var20 = AutoLeave.a ^ 82909387092701L;
                        AutoLeave.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[34];
                        var16_4 = 0;
                        var15_5 = "\u0002P\u0010\u00b6X!\u009d\u000e)\u00a4c\u00d3A\u009c9\u0096(6RT\r\u00cc\u0095\u00d2D\u00bee\u00a2\u00be\u00b8\u001e\u000b\u0087p\u00be<\u001bj\u00c5\u00a3\u0003\u001d~\u0097\u0088\u000b_e \u00d7\u00c8t\u001b2<?\u00e2I.\u00a6\u0018'n\u00ba\u0081\u00ab\u00bd\u0012]\u00b3d1\u00bb\u0017\u00c0\u0000\"\u008b\u001b\u00824\u0010\u00e1\u00f6\u0091\u00a1[\u00cb\u00cb\u00011\u000f_\u00ca\u00e9p#G\u0010z\u0095N\u00106\u00b37e.\u00b3C\u00f9\u0010w\u00e4?\u0018k\u00feb\u00a7\u0089\u00ed\u0013e\u0007\u00dd\u009c\u0088e\u00c5/\u0094\u0019\u00b3\u0018\u00fe\u000f\u00b7\u0097\u00f0\u0010\u00e5\u00ac?\u008c\u0083\u00ec}\u00da\u00d5\u00e7P\u0088*\u00f8I\u0012\u0010\u0007\u0096oX\u009d\u0018*,\u00d5\u00f6\u0000\u00020\u00d9\u00e4\u00ac ]\u00a6\u00f9\u008eC\u00ed\u00f6\u0083<qI\u00a3\u00d0\u00c3\u0019\u0087r\u00ac\u0004\u0097\u0015\u00e4\u00ef\u0001B\u00de\u0005\u00c8\u00ce\u00cfo\u00f5 \u00f8\u000e\u00dc\u0091\u00ec\u009cl\u009eK\u00f0pKI\u00ef\u00a2)z\u00bd\u009d5z\u00acl\u00f1\u000f>ZGL\u001bS\u008d \u009eD_|\u0001\u00f1\u00e1D\u0096\u00ee\u00f9\u000e\u00e0\n\u00bdd\u0005\u00acj\u00f00\u00a2]9\u00a2\u00a0\u00fa/6%\u00f4B\u0010AH\u00d0\u0005\u00fa\u00b53\u008a\u0090\"\u00c6\u00dd\u00a2jL6 \u00b5\u0091\u00f8R\u00b8\u00d2|\u0012O\u0098\u00ad\u00d8T9\u00e8\u000e@v\u00c8\u00bf\u00e3\u00c0\u00bc<\u0010\u00b6E0:\u00ab\u0082\u00140Q\u00ec\u00b1/|\u00fc\u00da=\u00a4\u00d8\u0097\u00e7\u0087\u001e\u008b\u00fa\u0015\u0017\u00db\u0013mi\u0080n\u00a3\u00a6\u00ac'\u0092AF\u00a9\u0015x'd-\u00ce4\u00fb\u00c83;Y\u0093-\u000b\u0015 A\u00c5\u00d4\u00bc\u00f3\u001d\u000en\u0096\u0091\u00d8o\u0097{<p]\u001c\u0088y\u00c3\u001f\u00a6\u009a\u0083\u00b4Ho\u00a8^\u00b4\u00bb\u0010\u00f4\u00c1\u00c4\u0004Z/\u00fc>F\"\u00e8F\u00f3\u00b4R\u0000\u00103?M\u00cc\u0080\u00d6w\u00c9\u00da&\nU\u0017s=+\u0010\u00a5\u001b\u00a0'\u00d9\u0013\u00fb;F\u0097\u001a8:w\u00edx\u0010\u00ed\u001b\u0081]\u001f\u00a3\u0000jz\u001b\u00a2\u00f3N\u00a5\u00da~ |\u009e\"\u00f4\u0085:\u00f5\u00f37q\u009cY\n\u00a75L\u00bf\u00e78\u00d8\u000e\u0000\u00ebk\u00df\u00ae\u00a8lk\u0094\\\u00f7 C\u00f4\u00949u\u0016\u00e2e\b\u00ec\u001b,\u00b8\u00fdt\u00fb\u00a3\u00f5u\u00e3\u00cf\u00ba2\u0015a\fe\u00e2\u0086\u00dft\u00be \u008f\u0097v\u00a3\u00a87\u00ce\u00c5\u0084&\u00b4O\u00c4\u000b^%\u001d\u00f0^k\u00c77\u00b1*\u001c\u00ce\u00fb}\u009d\u00fcnc \u00a5 {\u00fa\u0006t\u00f9\u00fdO\u008a!\u00af\u00a3\u00fc]\u00ad\u00c8\u0013\u001b\u0017\u0097\u0012\u0010s\u00b1\u001f}X\u00ca\u00e3\u008cl \u0007\u00c7\u001c\u001bJ*\u00a3U\u00b23\u00ff\u00a5L\u00f895\u00ccQ\u001dx\u0091\\\\\u00e0\u0003\u009b\u0000\u00bb\u008f\u00d5\u00d3{\u00108\u0081-\u00b3\u0010'\u0089'\u0006\u00dbkf8\u00f8\"\r(\u0002\u00b83l\u00a7\u00e1:C 3x\u00a9?_\u009c\u00ec\u00d1?/)q\u00f7\u00aa\u00d6\u0013\u00f4\u00f4\u0001\u0083\u00c1%\u00ce)\u00e6\u00c9\u00cd\u00fe\u009d\u00019\u0010\u00a2N\u00e9\u00a6\u00ca2\u00e9\u0082?\u00a6\u00e4\u00fa<\u0094B\u00b3\u0010\u00f9\u00dc\u00ca-\u0080\u0013\u000e\u00a5\u0083\u0013\u0010\u00ca\u00d7\u00be\u00f3z M\u00e1\n\u0018\u00eex\u00ee\u0094\u00c2\u00f9\u0092!\u00eah\u00e5\u00b3\u00ed\u008a\u00eb^\u00962\u00f7\u00bf\u0012~\u001f\u0092\u00d6tn)\u0010\u0099\u00b7O\u008co\u00c2\u0085\u001b:\u00fd\u00cb9\u0018`o1\u0018\u00ee\nO\u00f5;J\u00e6\u009aI\u00f9d\u00de\u0011\u00b3;\u0088\u0083P\u0083\u0012\u0011\u0017\u00d1\u00d7\u0010e\u0016\u00calL^#W\u0014\u0010\u00f3\u0084O\u00c9\u00af/ \u008b\u00ff\u00d6\u0086\u00c5\u001f\u0082C\u008b\u00d5\u00a6\u00ddQ16\u00fe\u00cb}'\u0093\u0017,\u00beB\u0019 .H+S[1";
                        var17_6 = 855;
                        var14_7 = 48;
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
                            var18_3[var16_4++] = AutoLeave.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "V\u0091@\u00f4i\r={vIa\u00b9\u00ca\u00dd\u000f\u0097\u00aca\u008c4%]:*\u0091Tlzy\u0092\u00b4\u00bf\u00cf \u00bd\r0!\u00015\u0010\u00d2)\u0087\u001fQ=\u00d8\u00cebn\u001c\u00b8\u00c5Ao\u00b5";
                            var17_6 = 57;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = AutoLeave.zkmDecodeUtf8(var19_9).intern();
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
                AutoLeave.b = var18_3;
                AutoLeave.d = new String[34];
                AutoLeave.k = new HashMap<K, V>(13);
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
                var6_12 = new long[14];
                var3_13 = 0;
                var4_14 = "GZ\u00a0s\u00c8\u00b8\u000eY\u0082\u00deU\u0001\u00e9\u00a54'm\u0083'\u00d7\u00d3\u00c8\u00b5N\u00a1\u0089D\u00e0\u0001\u0091[\b6\u001e^\u00bc\u009e\u00f8\u00a6\u0013.\u00d5\u008a\u00ca4\u00a5\u0091\u00cf\u001b\u001c\"S\u0000q\u00f8\u00b7AnXz\u00c7h\u00bf}\u00ff\\c\u009al\u00e2~\u00e22\u00a0\t:#A\u00a1\u00f1\u00d6\u00c8\u00e4\u00ecs\u00a6Y\u00e9\u0004\u0000Y\u00e9\u00eb\u0004\u001c(";
                var5_15 = 96;
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
                    var4_14 = "\u00dd\u0097\u0087\u00c2X\u008bt\u009e\f\u009c\u00e4\u0089\u009e\u0016\u000f\u00b4";
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
        AutoLeave.g = var6_12;
        AutoLeave.h = new Integer[14];
        var34_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "autoDisable", "getAutoDisable()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "fastLeave", "getFastLeave()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "lowHp", "getLowHp()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "totems", "getTotems()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "minTotems", "getMinTotems()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "minHp", "getMinHp()F", 0)), Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "players", "getPlayers()Lsu/catlean/pv;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoLeave.class, "command", "getCommand()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(AutoLeave.class, "distance", "getDistance()I", 0))};
        AutoLeave.delegatedProperties = var34_20;
        AutoLeave.INSTANCE = new AutoLeave();
        AutoLeave.autoDisable$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoLeave.INSTANCE, "disable", true, null, null, 12, null);
        AutoLeave.fastLeave$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoLeave.INSTANCE, "InstantLeave", true, null, null, 12, null);
        AutoLeave.lowHp$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoLeave.INSTANCE, "hp", false, null, null, 12, null);
        AutoLeave.totems$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoLeave.INSTANCE, "totems", false, null, null, 12, null);
        AutoLeave.minTotems$delegate = SettingFactory.intSetting$default(AutoLeave.INSTANCE, "min-totems", 2, new IntRange(0, 10), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        AutoLeave.minHp$delegate = SettingFactory.floatSetting$default(AutoLeave.INSTANCE, "min-hp", 8.0f, RangesKt.rangeTo(1.0f, 20.0f), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 8, null);
        AutoLeave.players$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoLeave.INSTANCE, "players", (Enum)AutoLeavePlayersMode.Leave, null, null, 12, null);
        AutoLeave.command$delegate = SettingFactory.textSetting$default(AutoLeave.INSTANCE, "command", "hub", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)(), 4, null);
        AutoLeave.distance$delegate = SettingFactory.intSetting$default(AutoLeave.INSTANCE, "distance", 256, new IntRange(4, 256), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)(), 8, null);
        AutoLeave.leaveTimer = new Timer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x75B3;
        if (d[n2] == null) {
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
                throw new RuntimeException("su/catlean/q_", exception);
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
            AutoLeave.d[n2] = AutoLeave.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoLeave.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5E8D;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/q_", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoLeave.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoLeave.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoLeave.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoLeave.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

