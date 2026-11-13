/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.discord;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.management.ManagementFactory;
import java.security.Key;
import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.account.Account;
import su.catlean.config.JsonFormats;
import su.catlean.discord.ActivityPayload;
import su.catlean.discord.CommandFrame;
import su.catlean.discord.DiscordRpc$WhenMappings;
import su.catlean.discord.Handshake;
import su.catlean.discord.IpcCommand;
import su.catlean.discord.IpcConnection;
import su.catlean.discord.IpcEventType;
import su.catlean.discord.IpcOpcode;
import su.catlean.discord.IpcPacket;
import su.catlean.discord.IpcPipe;
import su.catlean.discord.PresenceCycler;
import su.catlean.discord.RichPresence;
import su.catlean.discord.WindowsIpcConnection;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public class DiscordRpc {
    @NotNull
    private final String[] L;
    @Nullable
    private IpcConnection ipcConnection;
    private boolean h;
    @Nullable
    private RichPresence richPresence;
    private final long epochSecond;
    @NotNull
    private final Account r;
    @NotNull
    private Account c;
    private static Module[] j;
    private static final long a;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] k;
    private static final Map l;
    private static final long q;

    public DiscordRpc() {
        String[] stringArray = new String[]{"XDG_RUNTIME_DIR", "TMPDIR", "TMP", "TEMP"};
        this.L = stringArray;
        this.epochSecond = Instant.now().getEpochSecond();
        this.c = this.r = new Account(null, null, null, 7, null);
    }

    public final long getEpochSecond() {
        return this.epochSecond;
    }

    @NotNull
    public final Account G() {
        return this.r;
    }

    @NotNull
    public final Account Q() {
        return this.c;
    }

    public final boolean isConnected() {
        return this.ipcConnection != null;
    }

    private final int getPid() {
        long l = 0L;
        l = a ^ l;
        String string = ManagementFactory.getRuntimeMXBean().getName();
        Intrinsics.checkNotNullExpressionValue(string, (String)((Object)DiscordRpc.zkmBootstrap("y", (int)18712, (long)(0x5A95A058B4ADA61CL ^ l))));
        return Integer.parseInt(StringsKt.substringBefore$default(string, (char)DiscordRpc.zkmBootstrap2("j", (int)222, (long)(0x2E4DEF9F798DE198L ^ l)), null, 2, null));
    }

    protected final void reconnect() {
        if (this.h) {
            this.clearPresence();
            PresenceCycler.INSTANCE.cycleDraft();
        }
    }

    protected final void update(long l) {
        block1: {
            if (this.ipcConnection != null) {
                this.sendPresence();
            }
            IpcConnection ipcConnection = this.ipcConnection = this.getPipePath();
            if (ipcConnection == null) break block1;
            IpcConnection ipcConnection2 = ipcConnection;
            Json json = JsonFormats.getJson();
            Handshake handshake = new Handshake(1, String.valueOf(l));
            json.getSerializersModule();
            JsonElement jsonElement = json.encodeToJsonElement(Handshake.Companion.serializer(), handshake);
            IpcOpcode ipcOpcode = IpcOpcode.HANDSHAKE;
            ipcConnection2.send(ipcOpcode, jsonElement);
            this.startUpdateThread();
        }
    }

    protected final void setRichPresence(@NotNull RichPresence richPresence) {
        Intrinsics.checkNotNullParameter(richPresence, "presence");
        this.richPresence = richPresence;
    }

    @Nullable
    public final Unit sendPresence() {
        Unit unit;
        IpcConnection ipcConnection = this.ipcConnection;
        if (ipcConnection != null) {
            IpcConnection ipcConnection2 = ipcConnection;
            ipcConnection2.close();
            this.ipcConnection = null;
            this.h = false;
            this.richPresence = null;
            this.c = this.r;
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit;
    }

    private final void clearPresence() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)4235408913247518739L, (long)l);
        if (callSite != null) {
            IpcConnection ipcConnection = this.ipcConnection;
            if (ipcConnection != null) {
                IpcConnection ipcConnection2 = ipcConnection;
                if (callSite != null) {
                    RichPresence richPresence = this.richPresence;
                    if (richPresence != null) {
                        RichPresence richPresence2 = richPresence;
                        Json json = JsonFormats.getJson();
                        ActivityPayload activityPayload = new ActivityPayload(this.getPid(), richPresence2);
                        IpcCommand ipcCommand = IpcCommand.SET_ACTIVITY;
                        CommandFrame commandFrame = new CommandFrame(ipcCommand, activityPayload);
                        json.getSerializersModule();
                        JsonElement jsonElement = json.encodeToJsonElement(CommandFrame.Companion.serializer(), commandFrame);
                        IpcOpcode ipcOpcode = IpcOpcode.FRAME;
                        ipcConnection2.send(ipcOpcode, jsonElement);
                    }
                }
            }
        }
        this.richPresence = null;
    }

    @Nullable
    public final Unit handlePacket(@NotNull IpcPacket ipcPacket) {
        Intrinsics.checkNotNullParameter(ipcPacket, "packet");
        return switch (DiscordRpc$WhenMappings.y[ipcPacket.getOpcode().ordinal()]) {
            case 1 -> {
                if (ipcPacket.getData().getEvt() == IpcEventType.ERROR) {
                    System.out.println((Object)("Discord IPC error " + ipcPacket.getData().getData().getCode() + " with message: " + ipcPacket.getData().getData().getMessage()));
                    yield Unit.INSTANCE;
                }
                if (ipcPacket.getData().getCmd() == IpcCommand.DISPATCH) {
                    this.h = true;
                    Account v1 = ipcPacket.getData().getData().getUser();
                    Intrinsics.checkNotNull(v1);
                    this.c = v1;
                    if (this.richPresence != null) {
                        this.clearPresence();
                        yield Unit.INSTANCE;
                    }
                    yield null;
                }
                yield Unit.INSTANCE;
            }
            case 2 -> {
                System.out.println((Object)("Discord IPC error " + ipcPacket.getData().getData().getCode() + " with message: " + ipcPacket.getData().getData().getMessage()));
                yield this.sendPresence();
            }
            default -> Unit.INSTANCE;
        };
    }

    /*
     * Loose catch block
     */
    private final IpcConnection getPipePath() {
        IpcPipe ipcPipe;
        String string;
        String string2;
        block14: {
            block13: {
                String string3 = System.getProperty("os.name");
                Intrinsics.checkNotNullExpressionValue(string3, "getProperty(...)");
                string2 = string3;
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String string4 = string2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(string4, "toLowerCase(...)");
                String string5 = string4;
                if (!StringsKt.contains$default((CharSequence)string5, "win", false, 2, null)) break block13;
                DiscordRpc discordRpc = this;
                return new WindowsIpcConnection("\\\\?\\pipe\\discord-ipc-" + 0);
            }
            for (String string6 : this.L) {
                String string7 = System.getenv(string6);
                string = string7;
                if (string7 == null) {
                    continue;
                }
                break block14;
                catch (Throwable throwable) {
                    throw throwable;
                }
            }
            string = null;
        }
        string2 = string;
        DiscordRpc discordRpc = this;
        IpcPipe ipcPipe2 = ipcPipe;
        IpcPipe ipcPipe3 = ipcPipe;
        String string8 = string2;
        if (string8 == null) {
            string8 = "/tmp";
        }
        ipcPipe2(string8 + "/discord-ipc-" + 0);
        return ipcPipe3;
    }

    private final void startUpdateThread() {
        ThreadsKt.thread$default(true, true, null, "Discord IPC - Update thread", 0, () -> DiscordRpc.startUpdateThread$lambda$0(this), 20, null);
    }

    private static final Unit startUpdateThread$lambda$0(DiscordRpc discordRpc) {
        while (discordRpc.isConnected()) {
            DiscordRpc discordRpc2 = discordRpc;
            try {
                DiscordRpc discordRpc3 = discordRpc2;
                discordRpc3.reconnect();
                Result.constructor-impl(Unit.INSTANCE);
            }
            catch (Throwable throwable) {
                Result.constructor-impl(ResultKt.createFailure(throwable));
            }
            Thread.sleep(q);
        }
        return Unit.INSTANCE;
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        j = moduleArray;
    }

    public static Module[] su_catlean_module_Module_arr_zkmGetFlag() {
        return j;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            DiscordRpc.a = ZkmKeys.a(-5969397597461897445L, -7301030769494708056L, MethodHandles.lookup().lookupClass()).a(250527013677580L);
                            var25 = DiscordRpc.a ^ 4144190544258L;
                            DiscordRpc.f = new HashMap<K, V>(13);
                            DiscordRpc.zkmSetFlag(new Module[3]);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[20];
                            var21_4 = 0;
                            var20_5 = "L\u00a4$[\u00cf\u00cf\u00c4X\u0004{&\"R31x\u0010\u00b9\u009aLw\u0006\u00a1!Z\u0015A\u00f0\u00b7\u00fe\u001d8\u009f\u0018\u0088\u0089\u00b6X\u00ecS\u00e3\u0001R\u0093\u008f\u00d9,\u00a1N\u008b\u00d7_\u00d0\u00e0\u00f2p\u0087\u009c(x\u00d8\u00d7\u00f0\u00d3\u0099\u00ceo|y\u00fb\u00d5\u00c9\u0087\u00ba~\u00d8e\u00e0jw\u0092\u00df\u00ff\u009f\u00ca0)\u009c\u00d8\u00b9\u0017\u00d6\u00d0L\u00e2-\u00e4\u00a9\u00c3\u0018\u0089V\u0095\u001dejcr\u00aciE\u0007\u0003\u00c4\u00f4\u0016f\u00aa7\u0092\u001fV\u00f8F(P\u00e4`R\u0085V&\u00c9\u0016\u00ac\u00d7\u008e\u00b3\u001c\u0087W\u00d5m\u009b\u009f\u00fd\u0010\u0007\u00deDp&(\u00cbl\u00e306\u00e5\u00a9\u0014{8y!\u0010\no^\u000e+(\u00c4d\u00d6\u00df-\u00b4\u00fc\u001b\u00c2\u000b \u0011\u00f2\\\u00be\u0097\u0092\u00bf\u00fe_\u00c5\u0015\u00c91\u00f2\u00d3S\u0090q\u0013\u00d1\u00b4\u00beJ}\u00c9\u00b6\u00d8\u0088_\u00e8\u00bc\u00ec(\u00d3j\u0084?\u00a0\u00df\u00a6\u0000\u00e7\u0096m\u0003\u00ce\u00dd\u0005BY\u001cv|\u00cd\u00bcs\u00e1\u00140\u0000\u00f3A\u00aa\u008a\u0005%\u00a1\u00fa\u000fj\u00f7\u0016I8\u0087\u008d\u0091I\u008f\u00a3\tn\u0087\u0013(\u0092\u00e2B\u00dc^\u00c8\u0085\u00ee8\u00a1\u00be\u00e6\u00f9sD\u009b1\u00a7\u00caA\u000e\u00ac\u00d0P\u00bd\u001e\u0013BJ@\u0094\u00b8\u008c;3\u00be\u00ba>\u00cc|e\rpg\u00b6(I-\u0098\u008f\u00ba\u00c6\u00d7/<\n!\u00b6k~\u00c2\u00bf\u0086\u0001\u00cd/\u001b\u00d5D\u00ff\u00c8\u0081\u00bd]B3\u0092X\u007f\u001a\u00c8\u001c\u009a\u00fff\u0090\u00181\u00f4y\u0098\u00e2\u00ce\u00c5)o)J\u00bdl]\u00872\u0080n2~v\u0099o1 \u0088\u0092\u00ae\u00dd2\u00cfBXH\u009d\u00a8E\u00dc\u008e\u00bf\u00cb]\u00fd\u0003\bi[i\u0089b\u009fEQ\u0000U\u00b5\u0081\u0018\u00a6\u00a0nS@\u0004\u00eer\u00c0\u00f4tE)5\u00ca\u00a7\u008e\u00f8\u00ad\u0016\u00a3\u00d3\u0010\u00a2\u0010\u00f3rk\u00f7\u00b0\u00a6\u0086\u00de\u0004r2\u0014]\u00bc\u00a9\"\u0010yb\u001f&\u0096v>\u00fc\u00d7\u0092%a=\u00b3N(\u0010\u00c3\u00d4@\f\u00d4Z\u00c7C\u00d1(\u007f\u00c8m\u0086\u00e1\u00ce( \u00ab\u00bb\u00f4+\u0011\u00af\u00a0\u00fcbB\u0090\u00a0\u0016\u0087\u00afH\u007f\u0016\u00bd\n/\u00cd\u0096\u00af\u0087,\u000e\u00f4\u00d2\u00e6\u00b3\u00c6\u00e6\u00d7Z\u001cI\u00a4N";
                            var22_6 = 529;
                            var19_7 = 16;
                            var18_8 = -1;
lbl21:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl26:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = DiscordRpc.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "A\u009ap\u0098\u0093\u0087S\u0001!\u009cT\u00b6\u00ac\u00a3^\u0007 \u007f3A\u00af<,\u00e1}\rh\u00ca\u009dH\u00a8\u0091\u00cc\u0080N\u00faB\u00c48]\u00b0G_\u00da\u00e3K\u00ca\u00d9\u00eb";
                                var22_6 = 49;
                                var19_7 = 16;
                                var18_8 = -1;
lbl35:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl40:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = DiscordRpc.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
                        switch (v5) {
                            default: {
                                ** continue;
                            }
                            ** case 0:
lbl52:
                            // 1 sources

                            ** continue;
                        }
                    }
                    DiscordRpc.d = var23_3;
                    DiscordRpc.e = new String[20];
                    DiscordRpc.l = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[5];
                    var8_13 = 0;
                    var9_14 = "\u00d1OFZ\bd]|\u00f8\u009e\u00ee6\u00d7E\u0089\u00ad\u00a2\u00de\u00a3\r\u00c2\u00b4\u009b\u00e7";
                    var10_15 = 24;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl79:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "\u00aa\u00fc\u00d7\u001c9g\u00f4\u0016ZC\u00f1\u0017\u00e6\u0013\u0095\u00bf";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl92:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
                switch (v13) {
                    default: {
                        ** continue;
                    }
                    ** case 0:
lbl105:
                    // 1 sources

                    ** continue;
                }
            }
            DiscordRpc.g = var11_12;
            DiscordRpc.k = new Integer[5];
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl119:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-124, -4, -72, -116, 50, 92, 122, -46});
        ** while (true)
        DiscordRpc.q = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7F3E;
        if (e[n2] == null) {
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
                throw new RuntimeException("su/catlean/oq", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            DiscordRpc.e[n2] = DiscordRpc.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DiscordRpc.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/oq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7178;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])DiscordRpc.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    DiscordRpc.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/oq", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            DiscordRpc.k[n2] = n3;
        }
        return k[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = DiscordRpc.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/oq" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DiscordRpc.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(DiscordRpc.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

