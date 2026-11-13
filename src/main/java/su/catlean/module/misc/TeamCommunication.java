/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.misc;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.command.CommandManager;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.Bind;
import su.catlean.setting.BindSetting;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.ui.hud.HudManager;
import su.catlean.util.FriendManager;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class TeamCommunication
extends Module {
    @NotNull
    public static final TeamCommunication INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final TextSetting xorKey$delegate;
    @NotNull
    private static final BindSetting sendCoords$delegate;
    @NotNull
    private static final BindSetting tpHere$delegate;
    @NotNull
    private static final BooleanSetting marryTp$delegate;
    @NotNull
    private static final AtomicBoolean atomicBoolean;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] h;
    private static final Map i;

    private TeamCommunication() {
        super("team-communication", Categories.getMisc(), null, null);
    }

    private final String getXorKey() {
        return (String)xorKey$delegate.getValue(this, delegatedProperties[0]);
    }

    private final Bind getSendCoords() {
        return (Bind)sendCoords$delegate.getValue(this, delegatedProperties[1]);
    }

    private final Bind getTpHere() {
        return (Bind)tpHere$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getMarryTp() {
        return (Boolean)marryTp$delegate.getValue(this, delegatedProperties[3]);
    }

    @NotNull
    public final AtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        block5: {
            boolean bl;
            block4: {
                int n = HudManager.zkmIntConstant();
                bl = sendPacket.getPacket() instanceof ChatMessageC2SPacket;
                if (n == 0) break block4;
                if (!bl) break block5;
                bl = atomicBoolean.get();
            }
            if (bl) {
                return;
            }
            Packet<?> packet = sendPacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ServerboundChatPacket");
            ChatMessageC2SPacket chatMessageC2SPacket = (ChatMessageC2SPacket)packet;
            atomicBoolean.set(true);
            Module module = this;
            String string = chatMessageC2SPacket.chatMessage;
            Intrinsics.checkNotNullExpressionValue(string, "message");
            ModuleMessages.message(module, this.sendEncrypted(string), true);
            atomicBoolean.set(false);
            sendPacket.cancel();
        }
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        block2: {
            if (inputEvent.getAction() != InputEvent.Action.Press) break block2;
            if (inputEvent.getKey() == this.getTpHere().getKey()) {
                ModuleMessages.message(this, this.getMarryTp() ? "action_marry_tp" : "action_tp", true);
            }
            if (inputEvent.getKey() == this.getSendCoords().getKey()) {
                ModuleMessages.message(this, "xCoord" + (int)Globals.getPlayer().getX() + "zCoord" + (int)Globals.getPlayer().getZ() + "end", true);
            }
        }
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        block8: {
            if (!(receivePacket.getPacket() instanceof GameMessageS2CPacket)) break block8;
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundSystemChatPacket");
            GameMessageS2CPacket gameMessageS2CPacket = (GameMessageS2CPacket)packet;
            String string = gameMessageS2CPacket.comp_763().getString();
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = string;
            if (StringsKt.contains$default((CharSequence)string2, "cat_", false, 2, null) && StringsKt.contains$default((CharSequence)string2, "_lean", false, 2, null)) {
                String string3 = this.decrypt(StringsKt.substringBefore$default(StringsKt.substringAfter$default(string2, "cat_", null, 2, null), "_lean", null, 2, null));
                String string4 = this.findSender(string2);
                if (Intrinsics.areEqual(string4, Globals.getPlayer().getGameProfile().name())) {
                    return;
                }
                if (StringsKt.contains$default((CharSequence)string3, "xCoord", false, 2, null)) {
                    try {
                        int n = Integer.parseInt(StringsKt.substringBefore$default(StringsKt.substringAfter$default(string3, "xCoord", null, 2, null), "zCoord", null, 2, null));
                        int n2 = Integer.parseInt(StringsKt.substringBefore$default(StringsKt.substringAfter$default(string3, "zCoord", null, 2, null), "end", null, 2, null));
                        CommandManager.INSTANCE.setBlockPos(new BlockPos(n, 0, n2));
                    }
                    catch (Exception exception) {}
                    return;
                }
                if (StringsKt.contains$default((CharSequence)string3, "action_marry_tp", false, 2, null)) {
                    PacketUtil.sendCommand("marry tp " + string4);
                    return;
                }
                if (StringsKt.contains$default((CharSequence)string3, "action_tp", false, 2, null)) {
                    PacketUtil.sendCommand("tpa " + string4);
                    return;
                }
                ModuleMessages.S(this, string4 + ": " + string3, false, null);
                if (FriendManager.INSTANCE.isFriend(string4)) {
                    receivePacket.cancel();
                }
            }
        }
    }

    @NotNull
    public final String sendEncrypted(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "input");
        Object object = string;
        byte[] byArray = ((String)object).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byArray, "getBytes(...)");
        byte[] byArray2 = byArray;
        Object object2 = this.getXorKey();
        byte[] byArray3 = ((String)object2).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byArray3, "getBytes(...)");
        object = byArray3;
        object2 = new byte[byArray2.length];
        int n = byArray2.length;
        for (int i = 0; i < n; ++i) {
            object2[i] = (byte)(byArray2[i] ^ object[i % ((Object)object).length]);
        }
        return "!cat_" + this.encode((byte[])object2) + "_lean";
    }

    @NotNull
    public final String decrypt(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "input");
        byte[] byArray = this.decode(string);
        int n = HudManager.zkmIntConstant();
        Object object = this.getXorKey();
        byte[] byArray2 = ((String)object).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byArray2, "getBytes(...)");
        byte[] byArray3 = byArray2;
        object = new byte[byArray.length];
        int n2 = byArray.length;
        for (int i = 0; i < n2; ++i) {
            object[i] = (byte)(byArray[i] ^ byArray3[i % byArray3.length]);
            if (n != 0) continue;
        }
        return new String((byte[])object, Charsets.UTF_8);
    }

    @NotNull
    public final String encode(@NotNull byte[] byArray) {
        Intrinsics.checkNotNullParameter(byArray, "bytes");
        String string = Base64.getEncoder().encodeToString(byArray);
        Intrinsics.checkNotNullExpressionValue(string, "encodeToString(...)");
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(string, '+', '(', false, 4, null), '/', ')', false, 4, null), '=', '%', false, 4, null);
    }

    @NotNull
    public final byte[] decode(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        byte[] byArray = Base64.getDecoder().decode(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(string, '(', '+', false, 4, null), ')', '/', false, 4, null), '%', '=', false, 4, null));
        Intrinsics.checkNotNullExpressionValue(byArray, "decode(...)");
        return byArray;
    }

    @NotNull
    public final String findSender(@NotNull String string) {
        CharSequence charSequence;
        CharSequence charSequence2;
        block4: {
            int n = HudManager.zkmIntConstant();
            Intrinsics.checkNotNullParameter(string, "notSolved");
            AtomicReference<String> atomicReference = new AtomicReference<String>("FATAL ERROR");
            int n2 = n;
            Collection<PlayerListEntry> collection = Globals.getNetworkHandler().getListedPlayerListEntries();
            Intrinsics.checkNotNullExpressionValue(collection, "getListedOnlinePlayers(...)");
            Iterable iterable = collection;
            for (Object t : iterable) {
                PlayerListEntry playerListEntry = (PlayerListEntry)t;
                if (n2 != 0) {
                    charSequence2 = string;
                    String string2 = playerListEntry.getProfile().name();
                    Intrinsics.checkNotNullExpressionValue(string2, "name(...)");
                    charSequence = string2;
                    if (n2 == 0) break block4;
                    if (StringsKt.contains$default(charSequence2, charSequence, false, 2, null)) {
                        atomicReference.set(playerListEntry.getProfile().name());
                    }
                }
                if (n2 != 0) continue;
            }
            String string3 = atomicReference.get();
            charSequence = string3;
            charSequence2 = string3;
        }
        Intrinsics.checkNotNullExpressionValue(charSequence, "get(...)");
        return charSequence2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        TeamCommunication.a = ZkmKeys.a(-8572507584014296308L, -7513811025867688681L, MethodHandles.lookup().lookupClass()).a(95022117766332L);
                        var20 = TeamCommunication.a ^ 108478741258037L;
                        TeamCommunication.e = new HashMap<K, V>(13);
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
                        var18_3 = new String[49];
                        var16_4 = 0;
                        var15_5 = "!\u0007\u0004\u00a2p\u00d1\u00a4\u00ec\b\u00d2v\u009cr\u000bG\u00f3\u00e5j\u00bd\u0018e\u00ab\u00b9\u0015\u00825\u00fdV\u0085\u00e6\u00cd\u00dc\u0093\u00b3R\u00d8DAA\u00e8\u00cf\u00de\u00a6R\u00b7\u00b9\u0090\u0010\u0018\u00f0\u00b4\u0088\u00a6s\u0091\u008d\u00a0a\f\u00aa\u0094{\"\u00af\u00e7mv\u00f9\u009b?\u00c4\u00db\u001c \u0005\u00e2\u001c\u00b67i\u0088\u0012\u008b\u00ed\u00c0\u00d2\u00c7\u00c4\u00f5\u0006\u00fa;\t\u00a4_\u00bc\u0007\u0080\u0098\u00b1\u00861%\u00e7@Z\u0018m6\u00ae\u0006\u00ee!\n \u00ba\u00a4)~t\u0084G\u00d5\fq\u008f\u0085.\u0013N\u00e1\u0010#\u009e\u001c\u00f4\u0092\r9\u0097\u0080\u0089\u00b7\u0096m\u00fa\u00af\u00cd\u0010\u00ca\u00f3\u001dl7T\u00ac\u00bb\u00d7\u0094F\u00df\u008b\u008a\u00d8\u0092\u0010>\u0005{2\u00bdnsq\u00dc\u00d2\u00c1\u00e5\u000e\u008daI\u0010m\u001d\u0004P0\u00aa\n\u000f\u009a\u00c3\"\u0083\u00a9\u00a9\u00cf\u00cc\u0010\u00e9;7\u0092\u001b\u00ed\u00a9\u00ca\u008d\u00ef\u0017 \u00c5\u00ffR\u00d9\u0018\u0006\u00bf\u0013\u001di0\b~\u00bfNh/L\u0089\u00fd\u00ea4\u00ed-\u00d55(\u00e6\u00dd\u0010\u00a36\u001f\u0016GM\u00aa\u00fb\u00f8\u0086\u00bfJ=\u00b0\u00ce\u00e6\u0010\u0081 \u00c7r_\u00ebr\u0084\u00e0\u00cc\u00bbA^\u0098f\u00830\u00e78cz\u0086\u00d9'\u00fe,\u00bb\u00da~\u00b2Q\u0000\u008d\u00d5\u000f\u00df\u00c3\u00e7\u00e3\u0006\u00fd;\u009f\u00e77\u008e9[iRO8\u0011\u009f\u00c3\u008a\u00b3\u00c6\u00c5\u00e8\u00c1P\u0094\u0090\u00a68o\u00ac\u00b9\u0084A\u00f0\u00a7WK=\u00ec\u00a3\u0001B\u00e2(\u00e7\u00bd\u0097tf5\u00deQ\u00a7\u00ca\u00f5E\u009e\u00b1g\u00ed5\u000e\u00bdC\u00b4T0\u001c\u00efs\u000fd\u008c\u00fen\u0019\u001d\u00c6\u00f6-2O\u00f3\u00d0\u0010\u00e9p\u00e9H\u00ab\u00d5g\u0019~\u0088i:\u00da\u008b`\u0017\u0010\u00aa\u00d9I\u00c0L\u0015\u0013\u00c8>\u0090\u00b3\u0013\u0093\u00ebGO\u0018\u00dag1\u0088\u00edQE\u0013\u00ce\u0019\u00fa\u00ffR\u00ed\u00e0*\"8\u0098\u00d5\u00eb3\r| %+\u0088<\u0088%\u00fa\u00d5\u009e|3\u0007=yX\n$o\u00a1U,\u001f\u00d5Y,X\u0092\u00ad\u00aea\u00ba\u009f \u00cea\u00ee\u0083\u00fc\u00b3\u0093_\u0014\u00fe\u009d<\u00c60s\u00ed\u0017\u00efl\u009f<*;\u0099\u0010P\u00fd\u00c2\u009d\u0089?\u00bc\u00a0\u0002T#\u00ea\u00e2\u008bQ'\u00ac\u0090\u00b2]\u00bbPV@\u00a3\u00b6E\u0088\u00ed\u00b0\u00a0.\u00dab\u0089#h\u0080\u0089\u0085!\u00c7I\u000e\u0094\u00f5\";[\u00cf\u00e7t\u00a1 \u009f\u0092\u00c17\u00e50'8}\u000e\u0019\u00b4\u00c9\u00f9\u00d1I\u00b0\u00eb\u00de\u00a7&9\u0014\u0085[\u00f4\u008bW#\u0000J`a\u001d%j\u0003iO-\u0093\u00d1=D\u0097\u0017\u00df\u00f0\u00f0\u0082p\u0098\u00b6\u00d3\u0006d\u009b\u0094\r\u00ac\u00d1p=\u008a\u00d7\u000f\u00de\nQ\u00f6\u00db\u00af\u00d9\u0010\u0081xcS\u00ba\r\u00ea1\u001bO@~\u00ac\u0090tj\u00ba\t2X\u008c\u000eh\u00ee\u001cvo\u0086\u00c5\u00ff\u0080&Y\u00f6y\u009dg\u0084\u00af1\u0018<e&F\u00ed\u00f5\u009c\u0001\u00a4\u00a5\u0092'\u00ada\u00e9]\u0001\u00b2\u001fJ(\u0004\u00f5\u00de \u009f\u00f8\u00e2\u00a2\u00ef\u00bb_1\u001b\u00af\u00f1F\u00c4\u0098:\u008c\u00eb=\u0084\u00ac\u0007C\u001d5\u000f\u00cbA?\u0094\u00c1u\u001c\u0018\u0019\u00cfL\u00ec\u00ee~\u008fE\u00d3\u0004c\u00be\u0094\u0016)=q\u00b8\u00d3l\u00f5\u008d-\u008f\u0010\u0097\\GXI\u0002N\u0094\u0002\t\u00b3\u00c1gsw\u00a6\u0010\u0012\u0089\u00a7\u00c05\u00ce\\\u0084;a\nT\u00fe~6\u0018\u0010\u00d7\u0082\u0081j\u00db\u0081|/\u009f`t\u0092\u009b\\rO\u0010Xy\u00c6\u00d3\u0086-\u008bh\u00d8\u00a2U\u00fe0\u00db\u00dd\u00b8\u0018\u00ccu\u00cb\u00e0\u00b7\u0000\u00c5\u00ea\u0007\u0088\u0015\u0082\u00c6\u00e3#\u00fc\u00a3\u0090k^A\u00fa\u00fcT(\u00f3Z\u00f8\u00dba,\u009c\u00bb\u0085\u00c9i\u0013Z\u00c0C\u00fc\u00f0\u0014\u009fs\u0084\\\u00c4\u008b\u0092\u00b6{\u00c0\u00e1\u00e2\u0012\u0082\u0082dS\u00fdV\u009d\u00a8\u00a1\u0010\u008d)\u0086\u0002\u009cB,\u0098\u00f9\u00d2\u0098`\u0086\u00a0\u0080\u0006\u0010*\u000f\u00b5\u001a\u00ae\u00c5\u00c0\u00bbC\u0004\u00a05@5\u0083\u00f4 6,[\u00d4F\u0085D'\u00a8\u00a1\u0098h7\r\u00ec3\u00fd.\u00e9\u00a6\u00b1\rcr&\u00b6c\u00e9\u001d\u0080\u00bd\u000b0\u00ef\u0003\u000b\u00a9E,\u00c4T\u0005y\u00a9\u00d4\u00bak\u0083\u00e4\u00ac\u00b3\u00c8\u00f8\u00da\u00c4\u00dc\u00aam\u0005\u00fa\u008a~\u00afp\u00a2\u0012\u0001\u0089\u00b6\u0086=\u00f2\"4\u00dd\u00a3\"\u001a\u00fa\u00971\u0010\u00d0\fv9{\u00ca-\u00d3A\u00b7\u0091+)P\u000b\u0018\u0010\u0003\u00b9\u0095\u00b2q'\u009dL\u00d2\u009b\u0093\u008f\u00e0\f\u00ed\u009f E\u00ccm\u00c9M\u00acY*\u00f4lM\u00c2\u00a9t\u00ad\nkCO\u0000\u0093+\u000f=\u009b?\u008e\u0016\n\u00f8\u0004\u009e\u0010w>\u000b\u008e\u00b1\u008e\u008c\u00810|\u00fcS\u0087\u00e9qk\u0010\u0084k\u009ffjx\u00a3\u00891\u00f5wa\u0094{90\u0018Pu1\u0086\u00c9\u00ad\u000e{\u00e4\u00eag,\u00c2J\r/8\r\u00f9\u0093\u00fb\u00f3\u00ec\u00c3\u0090\u00b8\u00c5\u00d9\u000e\u00df\u00b72\u00ca\u00f9\b\u0013>\u00ae\u00f1\u00b0\u00dfE \u00fd\u00a71_\u00b4\u001en\u00e8l\u00f1\u00e0\u00f7\u0094\u00aayK\u0089\u0010`\u00eb\u00a4]{\u00f0J\u00c5}\u00c1g\u0011+Fn\u00cb=c5K\u00fd5\u00dd#9\u00ff\u00ef=\u00d8\u00a2\u00f2\u0092L<\u00ad\u00dbAU\u008d\u00c7\u00cb\u00e8\u009a\u00ae]\u001c\u00a8\u00f7lS\u00c8\u0015\u008e:0\u00c0\u0017\u0098\u000e\u00f0\\\u000e\u0017m.\u00f4'\u00b9&\u001f\u00f5\u00a5K\u000fs\u00efx*\u0081\u00fdh\u00e5\u00cbzB\u00daX\u0018y\t\u00ab\u00e4t\u00e7\u00c4n\u0080{\u00dbF\u0011\u0011\u00bc\u00eb\u0010\u0000\u00ec\u00ac\u0018GnG\u00e2\u001d\u0004\u00b6\u008d\u00c7\u001f\u00dbh\u00a8\u00bd\u0086\u008f\u009c^\u00ff\u00c5\u00ddW\u00a4\u0090 \u0080\u00f8\u0018\u00a7u\u00d2?\u0017\u00b3\u00e0\u00f1\u00b1\u00a2\u0080\u00a1\r\u00f7x\u00efP\u00ab\u00c4\u00ba\u001c\u0090\u00ee\u0000\u00c3\u0094/\u0012\u008f\u0010O\u00ce\u008b\u008d!\u009c\u00b4\u0091Xf\u00c4\u00fc\"\u00d2\u00a1\u0006\u0010\u00bf\u0013>\u000e\u00b9\u008d\u00eb\u00f8\u00fbKL\bx?\u00f4\n\u0018\u00b5g\u00ce\u00a1k+\u00afuwV\u001f\u00be\u0097\u0003R\u00d6\u00d0\u0001\u001f\u00e7\"\u00a4]\u00cd\u00100_\u0092\u0088u<g\u008aegcl)\u00e3\f\u008b\u0018\u00f1\u0004\u00b24&\u0001\u00ec\u00ced\u00f0\u00bc\u0084\u00ed\u0017S`\u008bT<\u00fah\u00d9/X";
                        var17_6 = 1430;
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
                            var18_3[var16_4++] = TeamCommunication.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "i\u0007\u00c9F\u008au\u008cq\u00cd\u008cyP6\u00e5?\u007fyl\u0094?\u0081\u00e6z+p?\u00a5s\u00ccxG\u008ap\u00d7\u009b\u00008}\u00e7C\u00e2\u00ec\u00aa\u00a7\u0000I\u00bbE\u00c2:\u0015\u00a8\u00b6\u00b1cz\u0010\u001c\u00e3\u00a1\u0095H<\u008as\u00ae\u0085\u00f0^p\u00f4\u00fd\u00af";
                            var17_6 = 73;
                            var14_7 = 56;
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
                            var18_3[var16_4++] = TeamCommunication.zkmDecodeUtf8(var19_9).intern();
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
                TeamCommunication.b = var18_3;
                TeamCommunication.c = new String[49];
                TeamCommunication.i = new HashMap<K, V>(13);
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
                var6_12 = new long[16];
                var3_13 = 0;
                var4_14 = "\u00d7\u008d#\u009a\u0016\u00a0&U\u0003\u00ec\"@\u00d5*|[#\u0005W\u00a2c\u00ae\u00a4\u00da\u00e3\u007f\u00e7\u0092\b\u0091\u0099\u00b8\u008d\u00df}#: \u009a\u0017\u0099\u00a5*\u00d6\u001d\u00d7\u00dc\u00b6\u009d!v\u00fe\u00ad\u00a3\u00f2\u0000/\u000f\u00e0\u00b9N\u0099\u00a6\u0005\u0082\u00b6\u001c\u00bd\b\u00d8)\u00f9Ds\u0010\u00c7\u001f\u008a:$\u0099\u00f2\u00bc\u0087(\u00c5\u0011g\u00c3J\u00df+:w,K\u00da\u00c5\b\u0006\u0002\u008a\u0099\u00887\u001a\u00b4\u00f1UI\u00049";
                var5_15 = 112;
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
                    var4_14 = "V\u0092\u001a\u00e9\u00f6?EG\u00bb\u0004\u00fd\u00a6\f\u00c8\u0000\u008a";
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
        TeamCommunication.f = var6_12;
        TeamCommunication.h = new Integer[16];
        var33_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(TeamCommunication.class, "xorKey", "getXorKey()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(TeamCommunication.class, "sendCoords", "getSendCoords()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(TeamCommunication.class, "tpHere", "getTpHere()Lsu/catlean/lj;", 0)), Reflection.property1(new PropertyReference1Impl(TeamCommunication.class, "marryTp", "getMarryTp()Z", 0))};
        TeamCommunication.delegatedProperties = var33_20;
        TeamCommunication.INSTANCE = new TeamCommunication();
        TeamCommunication.xorKey$delegate = SettingFactory.textSetting$default(TeamCommunication.INSTANCE, "key", "catlean", null, null, 12, null);
        TeamCommunication.sendCoords$delegate = SettingFactory.bindSetting$default(TeamCommunication.INSTANCE, "send-coords", new Bind(0, false, false, 7, null), null, null, 12, null);
        TeamCommunication.tpHere$delegate = SettingFactory.bindSetting$default(TeamCommunication.INSTANCE, "tp-here", new Bind(0, false, false, 7, null), null, null, 12, null);
        TeamCommunication.marryTp$delegate = SettingFactory.booleanSetting$default((SettingContainer)TeamCommunication.INSTANCE, "marry-tp", false, null, null, 12, null);
        TeamCommunication.atomicBoolean = new AtomicBoolean(false);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3DF9;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/es", exception);
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
            TeamCommunication.c[n2] = TeamCommunication.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TeamCommunication.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/es" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x65D8;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/es", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TeamCommunication.h[n2] = n3;
        }
        return h[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TeamCommunication.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/es" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TeamCommunication.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TeamCommunication.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

