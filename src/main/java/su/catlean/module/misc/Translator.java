/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.StringEscapeUtils
 */
package su.catlean.module.misc;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStreamReader;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.net.URI;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.ReceivePacket;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.misc.Translator$onReceivePacket$1;
import su.catlean.module.misc.Translator$onSendPacket$1;
import su.catlean.module.misc.TranslatorUrLangMode;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Translator
extends Module {
    @NotNull
    public static final Translator INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting urLang$delegate;
    @NotNull
    private static final ModeSetting outMessages$delegate;
    @NotNull
    private static final List b;
    @Nullable
    private static String E;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    private Translator() {
        super("translator", Categories.getMisc(), null, null);
    }

    private final TranslatorUrLangMode getUrLang() {
        return (TranslatorUrLangMode)((Object)urLang$delegate.getValue(this, delegatedProperties[0]));
    }

    private final TranslatorUrLangMode getOutMessages() {
        return (TranslatorUrLangMode)((Object)outMessages$delegate.getValue(this, delegatedProperties[1]));
    }

    @Flow
    private final void onReceivePacket(ReceivePacket receivePacket) {
        if (receivePacket.getPacket() instanceof GameMessageS2CPacket) {
            Packet<?> packet = receivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundSystemChatPacket");
            GameMessageS2CPacket gameMessageS2CPacket = (GameMessageS2CPacket)packet;
            String string = gameMessageS2CPacket.comp_763().getString();
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = string;
            for (String string3 : b) {
                if (!StringsKt.contains$default((CharSequence)string2, string3, false, 2, null)) continue;
                return;
            }
            Async async = Async.INSTANCE;
            int n = 0;
            async.getExecutor().execute(new Translator$onReceivePacket$1(n, string2));
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        if (sendPacket.getPacket() instanceof ChatMessageC2SPacket) {
            Packet<?> packet = sendPacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ServerboundChatPacket");
            ChatMessageC2SPacket chatMessageC2SPacket = (ChatMessageC2SPacket)packet;
            if (Objects.equals(chatMessageC2SPacket.comp_945(), E)) {
                return;
            }
            if (Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.FILLED_MAP) || Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.FILLED_MAP)) {
                return;
            }
            String string = chatMessageC2SPacket.comp_945();
            Intrinsics.checkNotNullExpressionValue(string, "message(...)");
            if (StringsKt.startsWith$default(string, "/", false, 2, null)) {
                return;
            }
            Async async = Async.INSTANCE;
            int n = 0;
            async.getExecutor().execute(new Translator$onSendPacket$1(n, chatMessageC2SPacket));
            sendPacket.cancel();
        }
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private final String translate(String var1_1, String var2_2) {
        block35: {
            block30: {
                var4_3 = new StringBuilder();
                var8_4 = "https://translate.google.com/m?hl=en&sl=auto&tl=%s&ie=UTF-8&prev=_m&q=%s";
                var9_5 = new Object[2];
                var9_5[0] = var2_2;
                var10_8 /* !! */  = var1_1;
                var21_9 = 1;
                var20_10 = var9_5;
                var12_11 = var10_8 /* !! */ ;
                var14_12 = 0;
                var15_13 = var12_11.length() - 1;
                var16_14 = false;
                while (var14_12 <= var15_13) {
                    block27: {
                        block28: {
                            block34: {
                                block33: {
                                    block32: {
                                        block26: {
                                            block31: {
                                                block25: {
                                                    if (var16_14) break block25;
                                                    v1 = var14_12;
                                                    break block31;
                                                }
                                                v1 = var15_13;
                                            }
                                            var17_15 = v1;
                                            var18_16 = var12_11.charAt(var17_15);
                                            if (Intrinsics.compare(var18_16, 32) > 0) break block26;
                                            v3 = '\u0001';
                                            break block32;
                                        }
                                        v3 = '\u0000';
                                    }
                                    var18_16 = v3;
                                    if (var16_14) break block27;
                                    break block33;
                                    catch (Throwable v4) {
                                        throw v4;
                                    }
                                }
                                break block34;
                                catch (Throwable v5) {
                                    throw v5;
                                }
                            }
                            if (var18_16 != '\u0000') break block28;
                            var16_14 = true;
                            continue;
                        }
                        ++var14_12;
                        continue;
                    }
                    if (var18_16 != '\u0000') ** GOTO lbl66
                    break;
                    catch (Throwable v7) {
                        throw v7;
                    }
lbl66:
                    // 1 sources

                    --var15_13;
                }
                var22_17 = var12_11.subSequence(var14_12, var15_13 + 1).toString();
                var20_10[var21_9] = URLEncoder.encode(var22_17, "UTF-8");
                v9 = String.format((String)var8_4, Arrays.copyOf(var9_5, var9_5.length));
                Intrinsics.checkNotNullExpressionValue(v9, "format(...)");
                v10 = URI.create(v9);
                Intrinsics.checkNotNullExpressionValue(v10, "create(...)");
                var5_18 = v10;
                var6_19 = new BufferedReader(new InputStreamReader(var5_18.toURL().openStream(), "UTF-8"));
                var7_20 = null;
                try {
                    var8_4 = (BufferedReader)var6_19;
                    while (true) {
                        var11_21 = var8_4.readLine();
                        var12_11 = var11_21;
                        Intrinsics.checkNotNull(var12_11);
                        var10_8 /* !! */  = var12_11;
                        if (var11_21 == null) break;
                        var4_3.append(var10_8 /* !! */  + "\n");
                    }
                }
                catch (Throwable var9_6) {
                    throw var9_6;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)var6_19, var7_20);
                }
                var6_19 = Pattern.compile("class=\"result-container\">([^<]*)</div>", 8).matcher(var4_3);
                var6_19.find();
                var7_20 = var6_19.group(1);
                Intrinsics.checkNotNull(var7_20);
                if (((CharSequence)var7_20).length() != 0) break block30;
                v13 = true;
                break block35;
            }
            v13 = false;
        }
        if (v13) {
            return "translation failed";
        }
        v14 = StringEscapeUtils.unescapeHtml4((String)var7_20);
        Intrinsics.checkNotNullExpressionValue(v14, "unescapeHtml4(...)");
        return v14;
    }

    public static final String access$translate(Translator translator, String string, String string2) {
        return translator.translate(string, string2);
    }

    public static final TranslatorUrLangMode access$getUrLang(Translator translator) {
        return translator.getUrLang();
    }

    public static final TranslatorUrLangMode access$getOutMessages(Translator translator) {
        return translator.getOutMessages();
    }

    public static final void q(String string) {
        E = string;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Translator.a = ZkmKeys.a(-5716957839514280275L, -8665533439793339563L, MethodHandles.lookup().lookupClass()).a(3944602796895L);
                        var20 = Translator.a ^ 70474523897252L;
                        Translator.e = new HashMap<K, V>(13);
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
                        var15_5 = "r,L|\u009e;_\u00b8\\E\u000e}\u00d9\u00e65{N\u00a8\u00aa`W\u009fa\u00cb\u0001eq\u00cb\u0007\u00fa\r\u009eE\u001aP\u00b1\u00b39\u0081jC\u00c9\u0098\u00a0\u00fa/\u0001\u00fc\u008b\u0081\u001f\bMk\u001fS(N\u00c1:0D\u00dc/\u0005\\3\u00110\u009b\u00ech\u00e7!\u00b1\u00ca\u00e6\u0094\u00c0S\u0097t\u00adA\u00c3z\u00e7\u001d\u00ae\u0080j\u00d4u\u00d5\u0093\u0092\u00f9(6\u00a3V\u00c0vH\u000f\u00ab\u00c2:\t\u00e1\u00dc%\u00bd\u00a0>\u00af\u00ad\u00b01\u00dc\u001e\u0016\u0097:E@\u00ad\u009a\u001a\u000e\u0099u\u00c47\u00a7H\u000e\u00f8\u0018/\u0012\u00ca\u008d\u0088\u00a0\u000f\u0097G\u001d\n\u00e4=L\\3\u008f\u00b6\u0011\u0086\u00d6*\u00a8\u00f5(\u00e4\u009f\u00c5\u00ef\u00e5\u00e5#\u00c5:f\u00ed\u0095%V,\u0083X\u00a2\u00e1\u0099\u0095\u00d46\u00bcLXv\f\u00f3\u00a4\u00a4\u00a4\u00cf\u00ad\u00d6\u00d8%z\u00f4\u00cd0\u00ee*\u00ff~\u00b8vAR\u00f0$\u0017:-\u00d8\u00ac\"\u0003\u00bc\u00aaC\u00b1'\u00f3~\u00c0R\u0010}|\u0013\u00f4V6\u0017\u00a1\u001cENaO\u00fd  H\u009a\u001f\u00c7E \u009el\u009aC\u00a2\u0013\u00d5\u00dc,\u00f9\u0084\u00df\"\u00e8\u00e6v\u0012;f\u0083\u0091\u0015\u0081~\u009f\u00fb\u00e0\u00f8+A\u00b0\u00c6\u0098\u009c\u0093:\u0001y\u0002\f\u0010\u00b5\u00af\u00fb\f\u00d51*1K\u00fd{\u00ce=\u0014P\b\u00bbN48\u0095\u00af\u00a6\u009f\u00e6\u00e2\u00dcd!*\u00fcpT\u0089;4\u00bf\u00e2\u00f1\u0010A1&\u00f0\u00c0\u00a6\u009f\u0015\u00d9\u008a\u00b4\u00d3WB\u00d3\u00af\u00da`\u001e\u00e8=\u009b\u00e5^\u00a1\u008e\u00f45\u00c7\u001d\u0090\u0087\u00cd/\u0000\u00e8\u008e\f\u0003`V\u00e0\u0015]\u0006\u00f0+>\u00d3\u0099\u009br\"\u009e+\u00aa\tHp7\u001a\u00ff\u00c9KM\u000b\u00dfB>X\u00e9\u00ea\u00eb\u009f\\\u00ef\u00ec\u00e2\u00c4\u009e\u009a \u00a7+\u008c7\u009f\u00b4\u00b5\u00ab'C\u009d\u0010YH\u00ee\u00816\u00a4\u009e)\u00b2n\u00184;\u00bak\u00db\u0080\u0092\u00c0\u00dc\u0090\u00c5\u00a2:v\u0086\"\u0093Qt\u00faM\u0005c] bW\f'9:\u00cd\u00e9V\u001c\u00e3\u00f4wv\u00d5b\u00b8j\\\u00f1\u0000\u0005\u0007\u00d0v\u00eb\te\u00e5\u00e8r\u00c4(NS\u00fb\u0011\b\u00f1Yz\u009d\u00c2/]\u00e1\u0012\u009b\u00b201co_\u008b\u00bfR\u0006`\u00c4\u0096\u00fa\u00a5\u00ce\f\u0091?\u007fk\u00d1\u00cc*\u00e3 \u00e5\u00cat\u00e9\u00b8TD\u00f4\u008b\u00ddf,(\u0097\u00fd\u009cvCy!$|\u0005F&h\u0092\u001e\u0082\u00d5\u001f\u00cc\u0090\u0098;\u00ea\u001a\u00b2b\u00fde\u0094?z@\u00de\u00a4PJ\u0091\u0087xf\u00fe\u008bwD\u009agC\u00afv^\u009b\u00ff\u000f\u00dd]1]\u00fc\u00a1\u00b2\u008dL\u00c5,\u008c\u009d\u00e8\u00ff\u0015\u00f8\u00c2zm\u00d5\u00db\u0092\n$\u00b7\u009a\u00b3F\n5mt\u0085\u001b\u00aa\u00f5\u00c9\u0019~\u00f2\u00efk;\u00f2xV\u00c5\u00b5s\u0081\u00cc.D\u00cb\u00efg\u00e9\u00de\u00b6N\u0084\\\u00b3Q\u0097\u00b4d\u00e6,w\u00d9a\u008a\u0006\u00fb\u00b9\u0004\u00ea\u0091\u00fa\u0005wL\u00002`\u00bb\u0093\u0098F\bC4h\u008e\u00a3$=D\u00d0\u00cb\u00fc\u001f`{\u0019g\u0094\u00e2\u00ab\u0010y\u00cdh\u00cb\u00cb\u0096\u00c7\u0002\u0096\u001aV\u00ec\u009a\u00b8\u00d5\u00d3@\u00fbz\u0099`\u00df\u00a8p\u0080\u00ce|\u00abO\u0018$I\u00826\u00ab,\u0005\u0007\u00be\u00c6\u00d5\u001b\u0002\u0088*-UE&\u00b7\u00fa\u00f8\u00f9\u00f5\u00a7~\u00f4\u00a7\u0005N\u000f\u00e5\u00c06h\u000b[\u00f7xX\u00b1\u00db\u00d9t\u0094e0\u0017\u00f8X\u0093x\u00ea\u00e6E\u00b7\u00d5\u0010\u00a2\u00b1\u00e0m\u00b4\u0080\u00a2\u0010\u00e0\u0085\u00af(\u00e3\u00b1\u00b6\u00ee\bN5Li\u0015\u008f\u0098D\u00fd\u0006\u00bep\u00f6\u0099OF\u0006\u00815\u00f1\u00bb|N\u00c1g\u00f4\u0011\u001d\u0015?\u0013Y\u00b7vxs7NP\r\u0099\u00de'\u00f2\u0092\u0011\u00e3Zu\u00df\u00ff\u001b\u0003\u0094\u0082\u00c3@m\u0092Z\u00d0\u0080\u00d5\u00a8\u00ea\u0017\u001d\u00f6\u00e3\u001e\u009f/\u00e0\u00a1\u00cfe\u0001@i\u0084\u008c\u008ba\u00ce\u00b4B\u00b2\u00041\u00fb\u00acL^\u00eb\u00fe\u00ccc\u0088sq\u009d/\u001cW\u00b5\u0081\u00bap\u00b4\u0095\u00b0\u00ac\u00d8\u0000\u0084\u00aa\u00b5\u0015\u00f8\u00e7\u00e5F\u00c8\u00d7,\u0012\u00e4\u0012;9\u00dab?I\u0087{\u00e1\u001b#\u00ed\u0000g\u0000!`\u00f7m?\u00ffV\u00fa}\u00b7\u00ab\u00131cq\u0092+\u001a\u00d8&O\u000f\u00d7\u00bd (\u00f1[\u00d9O_3j;$o\u00b6\u0095_ls\u008d\u00f7\u00cc\u00efw\u0010\u00c9\u00d5\u00ed\u00df6#Q\u00a3\u008e\u001c\u00fb\u0017\u00f7\u00d2\u0093q\u00ab\u00de_,,\u00a7\u00f0\u00ae\u00c9\u0098s/La\u00ba~\u0084\u0002\u00d7Q\u0090Yn\u001b\u00fa\u00b0\u00d2j\u0018=\u001b<\u00d28\u00a7\u00f0p7\u00b8\u00bd\u008c\u00a3I\u00f4\u00fa\u00eb\u00d2\u00b8\u00b1\u000f\u00ad\u00b3\u0099\u0018\u0094\u0017i\u00ab\u00d3\u0016\u00cfU\u00e3\u00c0\u00de\u00a0\u00fc\u0081\u009d\u0017[\u0085\u00eb8\u00c0\u00ab\u00e7\u00f9 6\u001eJq\b\u0099.\u00f9\u0098\u00f8\u001b\u0081\u0090\u00be\u008b\u00ed\u00a1C\u00b9\u00c1\u0080\u00a6\u008f\u00e3\u0089b\u0081l-\u00e0\u0007\u0016 \u001dgf\nA\u00ef\u00af+U\u00ea\u00fe\u00ff'\u00b1mE\u008fd)\u00bd\u00a5|dH`R\u00ac`\u008758\u00e6";
                        var17_6 = 1172;
                        var14_7 = 56;
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
                            var18_3[var16_4++] = Translator.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u009d\u008b\u00b3P@j\u00ac\u00d5\u00c42+2\u00b5\u0007B\u0002\u0010\u001ck\u0007\u00ab\u00d6i\u0086EF\u0086\u00caOh@\n\u0005";
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
                            var18_3[var16_4++] = Translator.zkmDecodeUtf8(var19_9).intern();
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
                Translator.c = var18_3;
                Translator.d = new String[23];
                Translator.h = new HashMap<K, V>(13);
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
                var6_12 = new long[4];
                var3_13 = 0;
                var4_14 = "\u00ae\u0089\u008a\r\u00ab\u00c89C\u0004\u00c6\u0086\u00ff\u00e3uJz";
                var5_15 = 16;
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
                    var4_14 = "\u000e\u00a4\u00fduR\u00944\u008c\u00f1\u00e0c!\u00dd\u00d6\u0000\u00c5";
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
        Translator.f = var6_12;
        Translator.g = new Integer[4];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Translator.class, "urLang", "getUrLang()Lsu/catlean/client/features/modules/utility/misc/translator/Translator$Lang;", 0)), Reflection.property1(new PropertyReference1Impl(Translator.class, "outMessages", "getOutMessages()Lsu/catlean/iy;", 0))};
        Translator.delegatedProperties = var26_20;
        Translator.INSTANCE = new Translator();
        Translator.urLang$delegate = SettingFactory.modeSetting$default((SettingContainer)Translator.INSTANCE, "your-language", (Enum)TranslatorUrLangMode.RU, null, null, 12, null);
        Translator.outMessages$delegate = SettingFactory.modeSetting$default((SettingContainer)Translator.INSTANCE, "out-messages", (Enum)TranslatorUrLangMode.UK, null, null, 12, null);
        var26_20 = new String[]{"was killed by", "was destroyed by", "has joined a game", "has left the game"};
        Translator.b = CollectionsKt.listOf(var26_20);
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5443;
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
                throw new RuntimeException("su/catlean/ej", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            Translator.d[n2] = Translator.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Translator.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ej" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x52D7;
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
                throw new RuntimeException("su/catlean/ej", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Translator.g[n2] = n3;
        }
        return g[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Translator.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ej" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Translator.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Translator.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

