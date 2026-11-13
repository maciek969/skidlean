/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package su.catlean.util;

import com.mojang.authlib.GameProfile;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.TeamS2CPacket;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringHelper;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.AfterReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.ui.hud.StaffWidget;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.util.JoinedPlayer;
import su.catlean.util.PlayerListUtil$onAfterReceivePacket$1;
import su.catlean.util.PlayerListUtil$onPlayerUpdate$1;
import su.catlean.util.ServerInfo;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class PlayerListUtil {
    @NotNull
    public static final PlayerListUtil INSTANCE;
    @NotNull
    private static final CopyOnWriteArrayList copyOnWriteArrayList;
    @NotNull
    private static List R;
    @NotNull
    private static final String[] h;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private PlayerListUtil() {
    }

    @NotNull
    public final List a() {
        return R;
    }

    public final void S(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)PlayerListUtil.zkmBootstrap("r", (int)15069, (long)(0x36CA44E42B6FD910L ^ l))));
        R = list;
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        if (!StaffWidget.k.getEnabled()) {
            return;
        }
        if (copyOnWriteArrayList.isEmpty()) {
            return;
        }
        copyOnWriteArrayList.removeIf(arg_0 -> PlayerListUtil.invokePredicate(PlayerListUtil::onPlayerUpdate$lambda$0, arg_0));
        Iterable iterable = copyOnWriteArrayList;
        R = CollectionsKt.toMutableList(CollectionsKt.sortedWith(iterable, new PlayerListUtil$onPlayerUpdate$1()));
    }

    @Flow
    private final void onAfterReceivePacket(AfterReceivePacket afterReceivePacket) {
        long l = a ^ 0x688935ADFE0L;
        if (!StaffWidget.k.getEnabled()) {
            return;
        }
        if (afterReceivePacket.getPacket() instanceof TeamS2CPacket) {
            Packet<?> packet = afterReceivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket");
            TeamS2CPacket teamS2CPacket = (TeamS2CPacket)packet;
            if (teamS2CPacket.getTeamOperation() == TeamS2CPacket.Operation.ADD) {
                for (String string : teamS2CPacket.getPlayerNames()) {
                    List list;
                    JoinedPlayer joinedPlayer = new JoinedPlayer(new GameProfile(UUID.fromString("66123666-622-6666-6666-666612366600"), string), true);
                    if (Intrinsics.areEqual(joinedPlayer.getProfile().name(), Globals.getPlayer().getGameProfile().name())) {
                        return;
                    }
                    String string2 = teamS2CPacket.getTeam().get().getPrefix().getString();
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    if (!(((CharSequence)string2).length() > 0)) continue;
                    String string3 = this.getDisplayName(joinedPlayer.o()).getString();
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                    Object object = string3;
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                    String string4 = ((String)object).toLowerCase(locale);
                    Intrinsics.checkNotNullExpressionValue(string4, "toLowerCase(...)");
                    String string5 = StringHelper.stripTextFormat(string4);
                    Intrinsics.checkNotNullExpressionValue(string5, "stripColor(...)");
                    Object object2 = string5;
                    object = new Regex(" ");
                    int n = 0;
                    object2 = ((Regex)object).split((CharSequence)object2, n);
                    if (!object2.isEmpty()) {
                        ListIterator listIterator = object2.listIterator(object2.size());
                        while (listIterator.hasPrevious()) {
                            String string6 = (String)listIterator.previous();
                            if (((CharSequence)string6).length() == 0) continue;
                            list = CollectionsKt.take((Iterable)object2, listIterator.nextIndex() + 1);
                            break;
                        }
                    } else {
                        list = CollectionsKt.emptyList();
                    }
                    object2 = list;
                    Object object3 = object2;
                    String[] stringArray = object3.toArray(new String[0]);
                    object2 = Async.INSTANCE;
                    int n2 = 250;
                    ((Async)object2).getExecutor().execute(new PlayerListUtil$onAfterReceivePacket$1(n2, joinedPlayer, stringArray));
                }
            }
        }
    }

    @NotNull
    public final MutableText getDisplayName(@NotNull PlayerListEntry playerListEntry) {
        MutableText mutableText;
        Intrinsics.checkNotNullParameter(playerListEntry, "entry");
        if (playerListEntry.getDisplayName() != null) {
            Text text = playerListEntry.getDisplayName();
            Intrinsics.checkNotNull(text);
            MutableText mutableText2 = text.copy();
            Intrinsics.checkNotNullExpressionValue(mutableText2, "copy(...)");
            mutableText = this.decorateName(playerListEntry, mutableText2);
        } else {
            MutableText mutableText3 = Team.decorateName(playerListEntry.getScoreboardTeam(), Text.literal(playerListEntry.getProfile().name()));
            Intrinsics.checkNotNullExpressionValue(mutableText3, "formatNameForTeam(...)");
            mutableText = this.decorateName(playerListEntry, mutableText3);
        }
        return mutableText;
    }

    private final MutableText decorateName(PlayerListEntry playerListEntry, MutableText mutableText) {
        MutableText mutableText2 = playerListEntry.getGameMode() == GameMode.SPECTATOR ? mutableText.formatted(Formatting.ITALIC) : mutableText;
        Intrinsics.checkNotNull(mutableText2);
        return mutableText2;
    }

    private final void onPlayerList(String[] stringArray) {
        if (stringArray.length < 2) {
            return;
        }
    }

    private final boolean normalizeName(String string) {
        String string2 = string;
        String string3 = ServerInfo.zkmGetFlag();
        String string4 = string2.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(string4, "toLowerCase(...)");
        String string5 = StringHelper.stripTextFormat(string4);
        Intrinsics.checkNotNullExpressionValue(string5, "stripColor(...)");
        string2 = string5;
        if (StringsKt.contains$default((CharSequence)string2, "\u00a7", false, 2, null)) {
            return false;
        }
        Object object22 = new String[]{" "};
        for (Object object22 : StringsKt.split$default((CharSequence)string2, (String[])object22, false, 0, 6, null)) {
            if (!Arrays.stream(h).anyMatch(arg_0 -> PlayerListUtil.invokePredicate2(arg_0 -> PlayerListUtil.startsWith((String)object22, arg_0), arg_0))) continue;
            return true;
        }
        for (Object object : Globals.getPlayer().networkHandler.getPlayerList()) {
            Intrinsics.checkNotNullExpressionValue(object, "next(...)");
            object22 = (PlayerListEntry)object;
            if (!Intrinsics.areEqual(((PlayerListEntry)object22).getProfile().name(), string2)) continue;
            return true;
        }
        return false;
    }

    @Nullable
    public final PlayerEntity findPlayer(int n, @NotNull String string) {
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 48 >>> 32 | (long)n3 << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-1598129414755210501L, (long)l);
        Intrinsics.checkNotNullParameter(string, (String)((Object)PlayerListUtil.zkmBootstrap("r", (int)26621, (long)(0x195F55F5992028C6L ^ l))));
        CallSite callSite2 = callSite;
        for (PlayerEntity playerEntity : Globals.getWorld().getPlayers()) {
            AbstractClientPlayerEntity abstractClientPlayerEntity;
            while (Intrinsics.areEqual(string, (abstractClientPlayerEntity = playerEntity).getName().getString())) {
                playerEntity = abstractClientPlayerEntity;
                if (n < 0 || callSite2 == null) continue;
                return playerEntity;
            }
        }
        return null;
    }

    /*
     * Unable to fully structure code
     */
    private final int getSortKey(JoinedPlayer var1_1) {
        v0 = var1_1.getDisplayName();
        Intrinsics.checkNotNull(v0);
        v1 = v0.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(v1, "toLowerCase(...)");
        v2 = StringHelper.stripTextFormat(v1);
        Intrinsics.checkNotNullExpressionValue(v2, "stripColor(...)");
        var6_2 = new char[]{' '};
        var5_4 = StringsKt.split$default((CharSequence)v2, var6_2, false, 0, 6, null);
        var6_3 = 11;
        var4_5 = ServerInfo.zkmGetFlag();
        var7_6 = var5_4.iterator();
        while (var7_6.hasNext()) {
            block39: {
                block38: {
                    block34: {
                        block36: {
                            block37: {
                                block29: {
                                    block33: {
                                        block35: {
                                            block32: {
                                                block31: {
                                                    block30: {
                                                        var10_9 = var8_7 = (String)var7_6.next();
                                                        switch (var10_9.hashCode()) {
                                                            case -446765093: {
                                                                if (!var10_9.equals("st.helper")) {
                                                                    ** break;
                                                                }
                                                                break block29;
                                                            }
                                                            case 1023145917: {
                                                                if (var10_9.equals("\u0430\u0434\u043c\u0438\u043d")) break;
                                                                ** break;
                                                            }
                                                            case -536475072: {
                                                                if (!var10_9.equals("ml.admin")) {
                                                                    ** break;
                                                                }
                                                                break block30;
                                                            }
                                                            case 92668751: {
                                                                if (var10_9.equals("admin")) break;
                                                                ** break;
                                                            }
                                                            case 1902965990: {
                                                                if (!var10_9.equals("gl.moder")) {
                                                                    ** break;
                                                                }
                                                                break block31;
                                                            }
                                                            case 404719092: {
                                                                if (!var10_9.equals("s.moder")) {
                                                                    ** break;
                                                                }
                                                                break block32;
                                                            }
                                                            case 1007120555: {
                                                                if (!var10_9.equals("j.moder")) {
                                                                    ** break;
                                                                }
                                                                break block33;
                                                            }
                                                            case -148051614: {
                                                                if (!var10_9.equals("st.moder")) {
                                                                    ** break;
                                                                }
                                                                break block32;
                                                            }
                                                            case 119920: {
                                                                if (!var10_9.equals("yt+")) {
                                                                    ** break;
                                                                }
                                                                break block34;
                                                            }
                                                            case 1113750001: {
                                                                if (!var10_9.equals("\u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440")) {
                                                                    ** break;
                                                                }
                                                                break block35;
                                                            }
                                                            case -1220931666: {
                                                                if (!var10_9.equals("helper")) {
                                                                    ** break;
                                                                }
                                                                break block36;
                                                            }
                                                            case 805824061: {
                                                                if (!var10_9.equals("helper+")) {
                                                                    ** break;
                                                                }
                                                                break block37;
                                                            }
                                                            case 3867: {
                                                                if (!var10_9.equals("yt")) {
                                                                    ** break;
                                                                }
                                                                break block38;
                                                            }
                                                            case 104069935: {
                                                                if (!var10_9.equals("moder")) {
                                                                    ** break;
                                                                }
                                                                break block35;
                                                            }
                                                        }
                                                        v3 = 0;
                                                        break block39;
                                                    }
                                                    v3 = 1;
                                                    break block39;
                                                }
                                                v3 = 2;
                                                break block39;
                                            }
                                            v3 = 3;
                                            break block39;
                                        }
                                        v3 = 4;
                                        break block39;
                                    }
                                    v3 = 5;
                                    break block39;
                                }
                                v3 = 6;
                                break block39;
                            }
                            v3 = 7;
                            break block39;
                        }
                        v3 = 8;
                        break block39;
                    }
                    v3 = 9;
                    break block39;
                }
                v3 = 10;
                break block39;
lbl101:
                // 15 sources

                v3 = 11;
            }
            if ((var9_8 = v3) >= var6_3) continue;
            var6_3 = var9_8;
        }
        return var6_3;
    }

    private static final boolean onPlayerUpdate$lambda$0(JoinedPlayer joinedPlayer) {
        return joinedPlayer.o().getScoreboardTeam() == null && joinedPlayer.isAnnounced();
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    private static final boolean startsWith(String string, String string2) {
        String string3 = string2;
        Intrinsics.checkNotNull(string3);
        return StringsKt.startsWith$default(string, string3, false, 2, null);
    }

    private static final boolean invokePredicate2(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    public static final int access$getSortKey(PlayerListUtil playerListUtil, JoinedPlayer joinedPlayer) {
        return playerListUtil.getSortKey(joinedPlayer);
    }

    public static final boolean access$normalizeName(PlayerListUtil playerListUtil, String string) {
        return playerListUtil.normalizeName(string);
    }

    public static final CopyOnWriteArrayList getCopyOnWriteArrayList() {
        return copyOnWriteArrayList;
    }

    public static final void access$onPlayerList(PlayerListUtil playerListUtil, String[] stringArray) {
        playerListUtil.onPlayerList(stringArray);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        PlayerListUtil.a = ZkmKeys.a(-7752995345005578566L, 168533173610472466L, MethodHandles.lookup().lookupClass()).a(277559414737122L);
                        var20 = PlayerListUtil.a ^ 14052665268301L;
                        PlayerListUtil.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[43];
                        var16_4 = 0;
                        var15_5 = "\u00c1qK\u0005\u00f9\u0096\u00ff\u00fe4V\u00f4\u0014\u008d\u00a3\u00ec\u0001\u009d\u00d3\u00bf\u00feD\u0002j\u00c8\u00108\u00ee1pM\u00c20_'J\u00caf\u00b9\u008aw\u00da@\u0099\u0016UZx\u0012\u00fe<\u0095)PX\u00d7}\u008eR\u00a7\u00ec\u009b\u0098\u008a\u00af6\u00df\u00a6\u00d2\u00f9\u001a~\u00c1\u00ab\u00a6d\u00dd\u00ee\u0001\u00ba<\u00ee*\u00e0\u00acX\u00826WlGy\u00f1x\u00de\\\u0007\u00da\u0086HM]\u00d3\u00f2\u00aa<t0\u00ca\u00936r\u0081\u00c7=\u0083\u00cd\u00e7f\u000f\u00b7\u00adp\u00a1r\u00ab\r\u00baj\u00e8\u0010\u00c0\u00d8r\"uh[\u00b5\u0007\u00ebF\u00c5\u00ff\u00e5\u0011\u0084\u00fd\u001b\u008dH\u008bQ*\u00a3\u0013\u0010\u007f\u00bf\u00c1M\u00e71\u00187\u0013tr{\u00b8\u00115\u00be \u000e\u0096\u00a8g\u00d4\u00bbz\u00a6i$\u00e8\u00bbT\b?\u00b3\u0010\u0017e*\u00d0\u00ddiy\u00b0\u0016\u00ef\u0001\u0099\u009a\u009e\u00d3\u0018-\u00196:\"\u0005\u00f9\u00bac\u0004\u00a4w~\u00e9L3dr\u008c\u00f3\u0017\u00b2\u00ad\u00cf\u0010\u00b5`3\u00e4\u00b9f'\u00f2\u001f\u0081\u00fd\u0086\u0089\u00f5\u001f\u00a7\u0010T\u0086\u00a1\u00a6oF\u0081\u00e1g\u0095\u00da(\u00a7\u00f3\u000f\u00aa\u0010;\nG\u008a\u00a4\u00fc\u00e5Ui\u00a7\u00abz\n\u009c>\u0012 \u001e@2\u0002\u00f2\b=\u00e8^S>\u00b9y\u00ed{f\u00963\u000b\u0098\u008fq\b\u0095\u00f4i\u0012\u00e7\u00b9G<\u008c \u00aa}[\u00bc(4\u00c3\u00b0\u00ad\u00926\u0096}2\u0097W\u00c1\u009a\u00cb\u0011X\fd\u00a3S\u0093T\u00da\u00c1\u00b7\u00cc\u00a8 \nd\u00ad\u008aG^\u0004=\u0090^\u00fb\u001d4\u00f7\u0000\u00b3i\u00897\u00e9\u00f6\u00fb\u00feB[\u0003\u0005\u00c8\u00bd\u00e1\u008e\u00ea\u0018\u0082db)\u00e4\u00ce\u00c2\u00d8SC!]7$\u001da\t\u00fc(D\u00c64i\u0018\u0018\u00fe\u00d7q\u00cdf\u009bsf\u00869p\u00be#\u00e9\u00d3\u00f5h;f\u009a*\u00bd\u00e5\u00dd\u0010\u001a\u00b9B\u00e4GG\u00ac*\u0013H\u00ff&ey\u00c85 \u00f4\u00be\u00afyP:]\u0016\u00c7\u00e2\u009b\u001cyL\u00dd\u0010uI\u00d6\u00fe\u00d8yw\u009d\u007f\u00e6w\u00ed\u001a\u0088E\r\u0018\u00bd\u0007\u00dfe7\u00ccZ\u00c1J\u00bec\u00e5>6\u00bb9<p\u000f\u00e6p\u00a5\u00cb\u00b2 /y\u0011\u0095\u001e\u00afOI.S\u0096Vz\u00dd'\u00d3\u00062\u00e7\u0003\f\u00d0\u00c4M\u00f3s7A\u00dft\u00b9\u009b\u0010\f\u001f6\u00c8d\u00b4X{\u00b7\u00d4\u00e2\u00c3\u00abE\u0089_(%\u00fb\u00d4|%uk\u00b3\u00b8da+a\u0082:OQ\u0090\u00a6\u0015\u0094\u00a7\u00c3W\u0001\u008f\u00d6\u00a2\u00a1\u0016\u00fdO\u00e5\u00adf\u0006,i-O(\u0085\u00e5G\u0080<e\u00cb\u00a99\u0090.\u00af\u00fa=\u001d\u00d6\u00a6\u00a6\u00f9\u009d`t\u00ac\u00d9\u0011\u0097\u00fc\u00c6\u00e2\u009c\u00f2\u0092~\u00a5\u0082zxE96\u0010\u00fb]\u00f6\u000e\u00db&\u0095\u0014\u00bf\u00b9\u0001n\u00be\u00d6\tZ\u0018A\u00c4\u008b:\u00d8\u00d0\u00f6\u0085)\u00bc;k\u00b9\u00bfL\u0098HR\u00b1\u00c9\u00edd4\u00d8\u0018B\u0014\u00c0\u00e3,>\u00bao\u00f3\u00b8\u0017\u00d1\u00d7\u00d9\u00c1?u\u009d\t>\"\u00a9\u00af\u00db\u0018\u00c7\u0087\u00aa\u0098\u00b80h\u008dg`\t\u00a8\u001d\u0099\u00f0\u00ab\u00a4p\u00d6&\u008f\u00ab3\u0003\u0010\u00b85e\u00eeK8jf\r\u00acG\u00d3\u00a8\u009b(c\u0010\u00ee\u00c8\u00ee\u0093g\u00d3\u00dd\u00f7\u009d%\u0080\u0002CC\u0084\u00c7\u0010\u00bb\u00c5!\u008b/\u00c23\u0092\u00f7\u0010\u000f\u00b8\u00be\u00ce\u00dd\u0000\u0010_\u0081~\u00d0\u00e9>\u0097\u0006O\u0015S\u009d\u00f8\u00e4)\u00d9 \u00c1\u0011\u0080\u00d7\u00d6>\u001e~pj-\u00e2\u00cf\u00a4u\u00eb>\u008c\u00ae\u00b6\u00af\u00fe\u00ab\u000b\u00bb\u001d;B\u007f\u0081\u00b54 Z\u0017\u00ae\u001e\u0083\u0094\f\u000f\u0083\u008eq\u001dr|\u008e\u00e2\u0087\u0005M[\u00e6\f\u00e9 ql\u001f\u00fb\u00e0\u00f1VF \u00053C\u008f\u00bem\u00d96\u00cew\u0085\u008e\u00c6\u001c\u0085\u00cb.\u0087\u001b\u0001\u0003m\u00dcp\u00b5\u00fa\u0085\u00df\u00d3e<&\u0010\u00b9\u00f6\u00c8\u009f\u009a\u00ea\u00e9\u00ebC\u00b5%\u00ee\u0014\u0017\t\u00c8\u0010\u00fd\u00ee\u00ea&\u0096dI\u00bdy{\u00a3\u0012\u00b5&\u00ef\u00fd \u00f7#\u009at\u001f~\u0085\u00f2L\u00f1\u0010\u0013:\u00d8\u00cb\u00b7\u00e9\u00de\u00cbw\u00b2\u00e7\u001am\u00e6\u000b|\u00f3\u00c7\u00b58\u008c\u0010\u0085|Y\u00ad3\u0001\b+8\u00ba\u00c8\u00b6^\u009d\u00e4(\u00a0\u0004nU\u00ed;\u00d2\u0015\u00dckd\u00a4\u000b\u00c2\u00acOZ\u00b0\u0014K\u0092\u00d1\\:\u00a9+\u00e7}\u0092R\u00f3\u009e\u00a3\u00d7\u00d1\u00c4\u00da\u001bY0:\u00e6\u00b5\u00d7\u00d4\u00e1q\u001a\u00ac+1\u000e\u00ba\u00d0\t\u00e6\u00ce\u0000lv.\u00d0s@j\u0088\u00d6Y\u00e42\u0080\u00e7\u00a7@\u00a5\u00ae\u00f8g:j\u00e5\u00e2-{\u00f4\u00aa\u0002BtI\r}\u0082\u00a8R,\u00ab\u00ed\u0006rf\u008dH0*\u00dcc\u00e1-(\u000f'(5?QO\u00c8\u00c8\u000b\u00db\u0006\u00eb\u00b1I\u00af\u009a?@\u0081Y\u0007\u0098\u00ab\u00b3C\u00e0\u00dfA\u0089\u0092\u00da\u00e5(\u00c2E\u001b\u00c4{\u00c0\u0018h#j7l\b\b}-20\u00df\u001cGr#\u009b\u008d\u00ecl\u0098\b\u00c4=\u001bt\u00c7\u00ccK\u00aa:\u00c8\u00a9l\u00c2D\u00c2\u0090\u0086ev8\u0005\u00a1\u00dd\u008de_\u00d9\u001f\u00af\u009c\u00dbMd\";\n\u00a8(\u00ef\u0099\u000e\u00b3?c \u009f3\u00b5JX\u001e\u00fa\u0013<\u0080lI3A|\u009b\u000bDz\u0089\u00eaX\u00d5\u0080\u0096\u00afD\u00ec\u00b0\u00b6\u001d\u00b5\u00d2\u0010g\u0018\u0091s\u001d\u00cf\u00f9\u00af\u0082a5\u00f1\u00f7?#\u00b2";
                        var17_6 = 1248;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = PlayerListUtil.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "P-\r\u00a4\u00baH+\u00dc\u00c2<\u00e4~\u00e4\u008dQf\u00f1!\u00e7\u0017x,\u00d9_ (\u001a\u00eb\f@Wi\u00af\u00aeEM\u0006 \u00fd\u00de\u00e9\u00021\u001a\u0016q\u000fd]YV\u00e0\u00bb\u0001&\u0088\u00ea";
                            var17_6 = 57;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = PlayerListUtil.zkmDecodeUtf8(var19_9).intern();
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
                PlayerListUtil.b = var18_3;
                PlayerListUtil.c = new String[43];
                PlayerListUtil.g = new HashMap<K, V>(13);
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
                var6_12 = new long[18];
                var3_13 = 0;
                var4_14 = "e\u00cb\u0094.\u00b5\u00b3>\u00c9\u008c\u00d9\u00f9\u0086|K,\u00f1\u001f\u0012\u009bO\u00e0\u0001e\u00c7\u00d7\u00a926\u00af4z8\u00ef^\u0016\u00edtb\u00fe\u00adS\rV\u00a0[\u00ae\u00d1Q\u0001\u00c4\u00ec\u0019A\u00f5\u00b2\u009diV\u0003\u00d7{Q\u0089?+adk\u00de4)=\u00af\u001a6\u00e8\u00df\u00b7\u00e0\u00b2\u00ccW\u00d1\u00d3N?\u00c7&\u00c5\u0000\u00d1\u00ca\u00c5\u00af\u00be;\u00c3\u00a2W\u00e3}2\u0018\u00d1\u0099\b\u0082\u00bf\u00c9\u0081B\u00de\u0091J\u00c4\u0089\u0000\u00f6\u009a\u0097\u0011\u00c0z\u00b9\u00ca\u00c7q\u00d4";
                var5_15 = 128;
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
                    var4_14 = "vG\u0005\u007fd#\u00f8d>\u0013.6`{q\u00cd";
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
        PlayerListUtil.e = var6_12;
        PlayerListUtil.f = new Integer[18];
        PlayerListUtil.INSTANCE = new PlayerListUtil();
        PlayerListUtil.copyOnWriteArrayList = new CopyOnWriteArrayList<E>();
        2405398651168645461L ^ var20;
        PlayerListUtil.R = new ArrayList<E>();
        var22_20 = new String[]{"\u0430\u0434\u043c\u0438\u043d", "admin", "ml.admin", "moder", "st.moder", "s.moder", "gl.moder", "j.moder", "\u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440", "\u043c\u043e\u0434\u0435\u0440", "st.helper", "\u0445\u0435\u043b\u043f\u0435\u0440", "helper", "helper", "yt"};
        PlayerListUtil.h = var22_20;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2617;
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
                throw new RuntimeException("su/catlean/s7", exception);
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
            PlayerListUtil.c[n2] = PlayerListUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlayerListUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/s7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x78D0;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/s7", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            PlayerListUtil.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = PlayerListUtil.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/s7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlayerListUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(PlayerListUtil.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

