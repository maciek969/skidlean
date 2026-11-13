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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.command.CommandManager;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class MessageAppend
extends Module {
    @NotNull
    public static final MessageAppend INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final TextSetting prefix$delegate;
    @NotNull
    private static final TextSetting postfix$delegate;
    @NotNull
    private static final BooleanSetting emoji$delegate;
    @NotNull
    private static final String[] l;
    @Nullable
    private static String B;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private MessageAppend() {
        super("message-append", Categories.getMisc(), null, null);
    }

    private final String getPrefix() {
        return (String)prefix$delegate.getValue(this, delegatedProperties[0]);
    }

    private final String getPostfix() {
        return (String)postfix$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getEmoji() {
        return (Boolean)emoji$delegate.getValue(this, delegatedProperties[2]);
    }

    @NotNull
    public final String[] T() {
        return l;
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        if (Globals.getMc().player == null || Globals.getMc().world == null) {
            return;
        }
        if (sendPacket.getPacket() instanceof ChatMessageC2SPacket) {
            String string = ((ChatMessageC2SPacket)sendPacket.getPacket()).chatMessage;
            Intrinsics.checkNotNullExpressionValue(string, "message");
            String string2 = string;
            if (Intrinsics.areEqual(string2, B)) {
                return;
            }
            if (Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.FILLED_MAP) || Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FILLED_MAP)) {
                return;
            }
            if (string2.length() < 7) {
                CharSequence charSequence = string2;
                int n = 0;
                for (int i = 0; i < charSequence.length(); ++i) {
                    char c = charSequence.charAt(i);
                    char c2 = c;
                    if (!Character.isDigit(c2)) continue;
                    ++n;
                }
                if (n >= 3) {
                    return;
                }
            }
            if (StringsKt.startsWith$default(string2, "/", false, 2, null) || StringsKt.startsWith$default(string2, CommandManager.INSTANCE.Z(), false, 2, null)) {
                return;
            }
            boolean bl = false;
            if (StringsKt.startsWith$default(string2, "!", false, 2, null)) {
                String string3 = string2.substring(1);
                Intrinsics.checkNotNullExpressionValue(string3, "substring(...)");
                string2 = string3;
                bl = true;
            }
            B = (bl ? "!" : "") + this.getPrefix() + string2 + this.getPostfix() + (String)(this.getEmoji() ? " " + ArraysKt.random(l, (Random)Random.Default) : "");
            ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getPlayer().networkHandler;
            String string4 = B;
            Intrinsics.checkNotNull(string4);
            clientPlayNetworkHandler.sendChatMessage(string4);
            sendPacket.cancel();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        MessageAppend.a = ZkmKeys.a(-4900969985804732557L, 1757601403593165913L, MethodHandles.lookup().lookupClass()).a(87074221480792L);
                        var20 = MessageAppend.a ^ 80420481868730L;
                        MessageAppend.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[78];
                        var16_4 = 0;
                        var15_5 = "\u00b2\u00e2\r\u00b6\u0083o1\u00a5\u00f5\u000f\u00c4\u00952\u001d\u00f5\u00a7\u0010\f\u0005\u00df\fL\u0003}\u00bcrA\u00cb\u00f7\u00a0\u0013\u0016\u00130\u00e6\u00fd\u00e1\u00a2Q&\u008f\u00bc\u00ba\u0011\u00a8\u00a6@!?>\u00f6\u00ee\u00b2\fI[\u00f9^ohSl\u0086\u00f6v\u00f66*\u00dd\u00f2/\u0083C\u0091\u00928\u00c3\u00e9\u00a9\u0017y\u0080\u0010}\u001b\u00deg!\u00abH\u0097\u00b2{\u0018\u0084\u00d6\u0084\u0013U\u0010a\u00e0y'nY\u0007\u00a2\u008a\u0097\u0018\u00d9\u00b2\u00a6\u000bv\u0010`\u009d\u0016\u00c6\u00f62\u00fb\u00d2\u0093\u0092\u00ebF\u00a6c$]\u0010j\u008d*}\u00a6\u00af\u00c0\u00144\u00a5\u009cviiH}\u0010\u001d\u00f3\u00bd\u00f7\u0002\u00af\u00cdm\u00cb\u009d\u008a\u00cb\u00c3\u00fa\"m\u0010\u0005O\u0091Y:*m\u001eBwM\u0085d\u00d9\u00141\u0010\u0097=\u0080A?J6\u00b4\u00da\u001e\u00fb\u00ea\u00ce,D\u00c8\u0010\u007f\u007f,\rZb{\u00d5?\u001b}\u00df\u00b2\u00d9\u0013\u00ea\u0010\u00dd\n\u000e\u00c1\u00fb\u0019&Q\u00a3\u0013\u0091\\\u0013o\u0011\u00d3 \u0090\u00951;\u00ca\u0097\u009dR\b\u00c4\u00d7\"8\u000e\u0019\u00c1\u0093O\u00a9\u00b3\u00a3#\u00ab\u00d8I\u0095g\u0093\u0098L\u00e7\u009f\u0010J*2x\u00c4\u0092~\u00a3\u00ab\u00a7\u00e2\f8\u00b8\u00b5\u00e2\u0010[\u008a\u00d1\u00c6\u00fc\u0015\u008c\u00f8`u\u00d1\u00b0^\u0005\u00d3\u00eb\u0010{nG\u008e\u00c7\u00a5q?\u008b\u00f6&H\u000f\u00e3\u00e6| ;\u00fe\u0087\\\u00af\f\u00bal\u00a0\u0000\u00f7\u009fp\u00a4q{\u00f5@\u00bb\u00d8\u00ba\u0002\u001f7\u00ed4\b\u001b\u00d6a\u00dd\u00ae\u0010\u00e5\t\u00d8\u00f5 $%\u0088\u0089\u00df\u0001\u00ca\u0018Y\u00d8\u0016\u0010\u00fc\u00c3\u00b8\u00df\u0019Sa6\u0018\u00b4\u0090\u0082\u00ff\u00db\u0014M\u0010I\u0006\u001a\u00e6\u00ac6r\u001d\u0014\u00cd\u00ef8\u0085N\u0014\u0090\u0010\u0017s\u00b5M\u0005+\u00eb\u0096I$\u00bax\u00bc\u0089=\u0019\u0010\u00b48\u0015\u0012\u0003\u000b\u0093\u00a7\u00f5\u009e\u00f3\u00ca\u00cd\u00ee\u00ee\u0003\u0010H\u001b\u00c9\u0081\u00f2\u00f0\u00ef\u00d4\u0000I+\u00b9\u0019\u00c6\u00a4\u009f\u0010\u00cfe\u009d\u00a5T:\u0002P!/\f.\u00e3\u0006\u0087O\u0010\u00d6\u001c\u00f3it#\u00b1\u00b2\u00d4\u000f\u00c0 \u00b9[\u0010\u0012\u0010\u00d8pW\u00ba\u00dc\u008e\u00fb=\u00beQ\u00b2}\u0007*j\u00db\u0010\u0081\u00c3\u00f5\u0006\u00ad\u00b1\u0088dN\u00d0t%6\u008c\u00f7`\u0010V\u0084&\u00f2\u00a8\u00b4=B\u00be\u00fd\u0097\u0092olt\u00ef\u0010\u00bd7\u0096o2\u00bc\u0015\u001f\u000f\u007f\u00a5w\u00ed\u00d7\u00bb\u00c5\u0010m\u0086&\u00d7Q<\u00ac\u0014\u00ec4h\u00c5,\u00bc\u00af\u0081 \u001c\u00da\u0091\nF\u0099})\u0002&\u00cf\u00c4\u00cbb\u001a\u009a+#\u00b5$\u00c1\u00f9LS\u001c\u00f8\u00a6\r\u00e6^e\"\u0010\u00bd\u0014\u0091\u00a7\u00c8\rh|\u00f3V\u0093-\u00ae\u0089\u00ae\u00e8\u0010u\u009b\u00be\u00e516{\u00ea\u00d1X\u00d9\u00c0\u00ae\u00e9\u00fe\u00f4\u0010\u00d6h\u00b3\u00b9\u00d9$\u0099\u00ec\u00ba\u00cb\u00e7\fl?\u00ee(\u0010\u00e9\u0094\u00c7\u0080$C\u00b1?d\u0091du]\f`\"\u0010\"+]\u00e9\u00a5\u000e\u001c\u0082G\u00db\u0001\u0095\u00a7\u0093\u0084E\u0010\u00b2#g\u0003_fM\u00a1\u00ad\u00e7\u00ee\u001d\u00f3\u00e5\u00952\u0010\u0003\u00c1\u0015\u00d8\u00f4t5J\t\u001c\u00c4>\u00a3`2a\u0010[\u0002e\u00cc\u0001\u000b\u00c9\u00d9Y<\u00c3(\u0088\u00e88\u0005\u0010\u00d2\u0088\u00a8\u00a69\u0081\u008dT\u00a4\u00ae`\u0017\u00bbX\u00dc\u00ce\u0010i|\u00f7A~\u00d8Zu\u0086\u00e3\u0080\u00a9'] \u00a8\u0010\u00b0\u001a\u00ed\u001b7\u0003\u0099\u00b6\u009ewb=>\u00bb.E\u0010\u00c7#+*\u00e0\u0015\u00a6\u009a\u00ed\u00f2/p\u00fb\u00010>\u0010\u00d3\u00b3Y\u00cf\u0011!\u00d0\u00a4\u001d\u0014\u00d0\u00ff\u00e77f\u00b9 \u00b5\u00d3\u00e6D\u008c;4\u008f\u00a1^Xj\u00b4q\u00db\u008a\u001a\u0010R\u00e0\u00b5\u00de0,dx\u0010p\u00c7:\u00d7\u0010\u00108Qo\u001a\u00d3\u00bd\u00c0A!8\u0013\u00aa\u00ce0\u009b\u00f7\u0010\u00143\u00ec\u008f\u00ba\u00a1l8\u009c6\t\u00d0\u0002\u00c2^\u00f8\u0010\u00f3\u00b5V8b\u00ad\u00a14>\u00aa\u00fd\\Wk!?\u0010\u0007yd\u00be\u00c5\u001e\u008aJ\u00d8\u0015ew\u00e2\u00af7\u00c2\u0010\u00e6\u0019\u0086:\u00a2AEp\u00ca(x\u0091+Y\u000bA\u0010\u0000\u00a9(\u00fe\u000f\u0084F\u00a5\u0092hu\u00c9t\u0099@\u009c\u0010\r\u0014V\u00e8\u00b5/pz\u00c01`\u0017e\u00fd\u00ce\u0087\u0010\u0092\u009b[\u00a3\u00adr\u00e6\u00da\u00a5\\>\u00e5\u00abS\u007f\u00e1\u0010Vt=\u00d8\u00e4\u008b\u00cf\u0000D\u00c5\u00cdg\u00b5\u0088\u00a2\u00f0\u0010\u00c4\u0088m\u0018m\u00ef\u00b9My\u00e7\bwt\u00f8N\u00c6\u0010\u001c\u00c4+}w-\u0097\rI\u00c5\u00a3\u00cf\u0016S\u00a4\u009c\u0010\u00d7\u00c9@gCv{\u0085O:\u00a0\u00d7<]w\"\u0010\u008d\u00db\u0090\tD,j\u0091\u00c1\u00da\u00ec&\u00df\u0084\u00a4w\u0010\u00aa\u008b\u00cd'\u00c2g>\u00c0\u00e2\u00f5\f7\u00b07\u00eb\u008e\u0010\u0002\u00d8\u0004WBX[?\u00f6`,\u00fe\u0014\u0096\u0096\u00d6\u0010\u00b5Y\u00a8/\u00cf\u00e5\u008a{\u00ee\u0093u-g4I\u00c5\u0010\u00af\b\u00b9_\u001b\u00b1|\u00db\u0095\u00f6\u00bc\u00d4c\u00de\u0006a\u0010e\u00d7\u00be\u00da\u008d\u00aep\u00fc\u00e0z\u00df\u000e\u0003\u00f1\u00c2\u00a9\u0010\u00d0\u0005\u000b\u00a9m{U\\\u00d0ej\u00d9\u00f8\u00dd\u008b\u0000\u0010\u00fe\u00c4cR\"I\u0097[*<2\u0088\u0080\u00ed.\u0081\u0010\u0096\u00b0\u00c5\u00bat#\u001e\u00d4\u00d6FY\u00a1$\u0000\u0010E8\f^\u00bc\nqc\u00b9\u001c\u00fd\u00d6\u0088<\u0088b\u001d\u0098R\u001f.\u00f6\u0003\u00c8\u008c\u00dd\b\u00ca\u00dbY\u00e9+D3\u00c9\u0094@\u001f\u00d2\u00aa <\u00dd\u008a\u00e7\u00fb\u00c24\u001cS%\u00e0A\u00c5\u00e4\u00dc\u0015\b\u0010W=J\u0096\u0016\u00fe\u0010\u001dbh\u00a1\u00e6Ku\u001c\u0086\u0010\u00ff\u00f0zc\u0081\u00aa\u0096\u00847\u00c8/\u0007+\u0011]q\u0010\u0091\u00f3Q\u00b6\u0011\u00ac\u00fd\u001eCF9\u0015\u00a0\u00fa_\u00d1\u0010]7\u00e3(\u0001.\u0013m\u00806\u0095<\r\u00fb\u00e1%\u0010\u0001\u00d4\u00a1\u000f\u00ad\u00de`\u00ed\u0004.\u009b\u00a1\u00ff\u00af<C \u000b\u00cc3r\u0012\u00fd\u00b2\u00a5>?\u00b74%9\u001e\t\u00b8\u00a0\u001a}\u00a1\u00c0\u00c7\u0080\u000f~7)\u00ad\u00bd\u00d8\u0094\u0010n\u00f6\u0093`\u0091\u0091\u00af6\u00b0\u00a5f\u0099\u00aclv\u00b8\u0010m0\u00cc\u0083K\u00cc\u0013\u0095\u00fa\u0012\u00e6\u00ac\u0003/\u0096\u0094\u0010\u007f\u00d8\u0087\u00bf\u001co\u00a7\u00d87\u0018\u00b1\u00a2h~j\\";
                        var17_6 = 1443;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = MessageAppend.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00c7C\u00cab\u00ac\u0006\u00e9\u00b0*\u00a6\u0017l^B\u00d93\u0010\u008b\u009c/\u00f7\u00bf\u00a4\u00b7\u0015\u00a7\u00f3\u00afa\u00b5\u0014|?";
                            var17_6 = 33;
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
                            var18_3[var16_4++] = MessageAppend.zkmDecodeUtf8(var19_9).intern();
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
                MessageAppend.b = var18_3;
                MessageAppend.c = new String[78];
                MessageAppend.g = new HashMap<K, V>(13);
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
                var6_12 = new long[61];
                var3_13 = 0;
                var4_14 = "\"\u00c1\u00fb\u0093o\u0097wp\u00ddQ\b\u0000\u0098\u00e9F\u00b5\u00c4w\u00e1{\u00d3\u0010\u0002\u00d1]%\u00e4\u0018\u00f8\u00fd\u00c1\u00b3x\u0085\u009f\u00bb\u00d3\u00c1\u0092|y'\b;\u0019`\u00b2\u00a1kftN\u00d0\u001f\u009b\u00d1\u00e4\u00af\u008fI\u00ccB\u00f3\u0088 cZg_\u008a@\u00aa\u00d6\u0091\u00c2\u00b1H\u00ba\u00af\u00bb\u009f\u0089\u001dZ\u0088\u00fa\u0007\u001f\u00fd\u00e1&\u00874\u00bc6\u008f\u00fe\u008e\u0086\u00f3:\u00e3\u0010:\u00a5\u0084A\u00f4!Opj])m\u00a7\r\u00d97\u00b3\u00b5|9\u001c]\u001c \u008a\u00f2\u00f12\u001d\u00ce\u00db8?v\u00d9\u00b6\u008d\u0085N\u00e1\u000f\u00ae\u00a0f\u0083A\u00b0B\u00bd\u00d200\u008e\u00a57C\u0010\u001b\u0004\u00f1\u00a3i\u0086C\u00e8\u00f2\u00d5\u009a-\u000br\u00e5\u00ac\u0006\u00afCr\u0016\u00e9P\u00e6P\u00c3\u00ba_,\u00f5\u00f0\b\u00ba\u00c2\u00cd\u008ev\u0082\u00e4\u008e80\u00dc4W\u0016E\u00c7^O\u00f9\u00c4S\u00ba\u00b5\u0015\u00d4\u00ea\u00d8\u008e\u00ec\u00c2I\u0090J.\u0012xh\u00da\u00e6\u00cd\u009bd\u0004g\u00ff\u0098\u00bag\u000e\u0097\u00a9\u0091~+\u0001\u00c4\u00d6r\u00c3\u009d\u0016E\u00b6\u00c8d\u00e4\u009b\u00c1\u009e\u00d8\u00e6q\u00e0\u0086TbQ\u00d4'\u00degqz)d\u00b5\u00a2\u00d7-\u0091m1\u00e0\u00d8\u00c5\u00c60\u00d6\u00e4\u00cdQ\u008e\b57\u009dy\u0081\u00ab \u00f2\u00ae#@a\u00dfc\u00c9\u0018\u00a9\u00a1h\u00c0\u00a3w\u0088V\u009f\"G\u009af\u00d2\u008bc\u00f2\u00e7\u00f9\u00e5\u00e6\u00e9 \u00c6\u00b8\u001e\u00d9\u00c7VY\u00b5T\u009d\u00eb\u00e2,\u00e0YC1@\u0013\r\u00f9\u00e7\u00a5i\u00b2\u00cab\u00eaxp\u001a\u00c5O\u00b1\u0093\u00ce2\u00e6\u00b6x\u00a6~\u0094c\u00a1\u0088_W\u00bdk\u009d\u00e1\u000f\u00d2\u009c1CE\u0016\u00f3\u0012\u0087\u00dd\u00dds\b\u00bc\u00fe\u00c8\u00b8G\u00ebP)w\u00ed\u00fa\u008c\u00e4|\u00f0t\u00bf\u00f2\u00ab\\\u0092\u00fa\u0082`\u00c1O\u00ac@\u00ebi\u008bdvN\u008c\u00a7{\u00aaa\u00f0\u0095\u0018e\u00fa\u0001\u00d8\u009fRw\u0002:\u00e4\u00a3\u00f7\u00ab\u00adyfkK9%\u00a0WA8\u009b\u0002\u0016u\u0087z";
                var5_15 = 472;
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
                    var4_14 = "\u00de)>\u0007\u00df\u00b3\u0014G\u0084\u0003\u00fbo\u00f7S\n\u00af";
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
        MessageAppend.e = var6_12;
        MessageAppend.f = new Integer[61];
        var29_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(MessageAppend.class, "prefix", "getPrefix()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(MessageAppend.class, "postfix", "getPostfix()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(MessageAppend.class, "emoji", "getEmoji()Z", 0))};
        MessageAppend.delegatedProperties = var29_20;
        MessageAppend.INSTANCE = new MessageAppend();
        MessageAppend.prefix$delegate = SettingFactory.textSetting$default(MessageAppend.INSTANCE, "prefix", "> ", null, null, 12, null);
        MessageAppend.postfix$delegate = SettingFactory.textSetting$default(MessageAppend.INSTANCE, "postfix", " [CatLean]", null, null, 12, null);
        MessageAppend.emoji$delegate = SettingFactory.booleanSetting$default((SettingContainer)MessageAppend.INSTANCE, "emoji", true, null, null, 12, null);
        var29_20 = new String[]{"^_^", "^-^", "^o^", "^O^", "o_o", "O_O", "0_0", "x_x", ">_<", "-_-", ";_;", "T_T", "=_=", "v_v", "xD", "o.O", "O.o", "._.", "o_o;", "O_O;", ">:(", ":)", ":D", ":P", ":o", ":O", ":3", ":|", ":/", ":*", ";)", ">:D", ">:3c", "XD", "xP", "^_^", "(^-^)", "(>_<)", "(T_T)", "(U3O)", "o_o", "O_O", "0_0", "(v_v)", "(x_x)", "(^o^)", "(^O^)", "(;_;)", "(=_=)", "(^3^)", "(^w^)", "UwU", "OwO", ">w<", "xD", ";D", "(>o<)", "(._.)", "('~')", "(*_*)", "(\u00ac_\u00ac)", "(\u2022_\u2022)", "(\u00b0_\u00b0)", "(>_<)b"};
        MessageAppend.l = var29_20;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x605B;
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
                throw new RuntimeException("su/catlean/q2", exception);
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
            MessageAppend.c[n2] = MessageAppend.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MessageAppend.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/q2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7674;
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
                throw new RuntimeException("su/catlean/q2", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MessageAppend.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MessageAppend.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/q2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MessageAppend.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(MessageAppend.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

