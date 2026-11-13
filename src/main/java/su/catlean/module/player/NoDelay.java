/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.LivingEntityAccessor;
import su.catlean.mixins.accessors.MinecraftAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class NoDelay
extends Module {
    @NotNull
    public static final NoDelay INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final IntSetting delay$delegate;
    @NotNull
    private static final BooleanSetting jump$delegate;
    @NotNull
    private static final BooleanSetting blocks$delegate;
    @NotNull
    private static final BooleanSetting crystals$delegate;
    @NotNull
    private static final BooleanSetting xp$delegate;
    @NotNull
    private static final BooleanSetting all$delegate;
    private static final long a;
    private static final String[] c;
    private static final String[] d;
    private static final Map f;

    private NoDelay() {
        super("no-delay", Categories.getPlayer(), null, null);
    }

    private final int getDelay() {
        return ((Number)delay$delegate.getValue(this, delegatedProperties[0])).intValue();
    }

    private final boolean getJump() {
        return (Boolean)jump$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setJump(boolean bl) {
        jump$delegate.setValue(this, delegatedProperties[1], bl);
    }

    public final boolean getBlocks() {
        return (Boolean)blocks$delegate.getValue(this, delegatedProperties[2]);
    }

    public final void setBlocks(boolean bl) {
        blocks$delegate.setValue(this, delegatedProperties[2], bl);
    }

    private final boolean getCrystals() {
        return (Boolean)crystals$delegate.getValue(this, delegatedProperties[3]);
    }

    private final void setCrystals(boolean bl) {
        crystals$delegate.setValue(this, delegatedProperties[3], bl);
    }

    private final boolean getXp() {
        return (Boolean)xp$delegate.getValue(this, delegatedProperties[4]);
    }

    private final void setXp(boolean bl) {
        xp$delegate.setValue(this, delegatedProperties[4], bl);
    }

    public final boolean getAll() {
        return (Boolean)all$delegate.getValue(this, delegatedProperties[5]);
    }

    public final void setAll(boolean bl) {
        block0: {
            long l = 0L;
            l = a ^ l;
            ZkmReferences.zkmBootstrap("\u00c5", (long)7248091008064681807L, (long)l);
            all$delegate.setValue(this, delegatedProperties[5], bl);
            if (ZkmReferences.zkmBootstrap("\u00c5", (long)7289801739622382523L, (long)l) == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (Object)new int[3], (long)7340610017976819036L, (long)l);
        }
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Intrinsics.checkNotNull(clientPlayerEntity, "null cannot be cast to non-null type su.catlean.mixins.accessors.LivingEntityAccessor");
        if (((LivingEntityAccessor)((Object)clientPlayerEntity)).getLastJumpCooldown() > 0 && this.getJump()) {
            ClientPlayerEntity clientPlayerEntity2 = Globals.getPlayer();
            Intrinsics.checkNotNull(clientPlayerEntity2, "null cannot be cast to non-null type su.catlean.mixins.accessors.LivingEntityAccessor");
            ((LivingEntityAccessor)((Object)clientPlayerEntity2)).setLastJumpCooldown(0);
        }
        Item item = Globals.getPlayer().getMainHandStack().getItem();
        Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
        if (this.isAffectedItem(item)) {
            MinecraftClient minecraftClient = Globals.getMc();
            Intrinsics.checkNotNull(minecraftClient, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
            if (((MinecraftAccessor)((Object)minecraftClient)).getUseCooldown() > this.getDelay()) {
                MinecraftClient minecraftClient2 = Globals.getMc();
                Intrinsics.checkNotNull(minecraftClient2, "null cannot be cast to non-null type su.catlean.mixins.accessors.MinecraftAccessor");
                ((MinecraftAccessor)((Object)minecraftClient2)).setUseCooldown(this.getDelay());
            }
        }
    }

    private final boolean isAffectedItem(Item item) {
        return item instanceof BlockItem && this.getBlocks() || item == Items.END_CRYSTAL && this.getCrystals() || item == Items.EXPERIENCE_BOTTLE && this.getXp() || this.getAll();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        NoDelay.a = ZkmKeys.a(-3387130272169361882L, -2414784297375456455L, MethodHandles.lookup().lookupClass()).a(213877211474L);
                        var20 = NoDelay.a ^ 42569103263097L;
                        NoDelay.f = new HashMap<K, V>(13);
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
                        var15_5 = "\u00f6\u00d6\u008cYJ\u00f7\u00a1\u009f\u0013\u009f\u008d\u0099\u00ba\u0096\u001b\u00d9\u00e2!\u00dcT.>\u0015$\u00e42}\u00fa\u00a1N\u00fc\u00f5\u0088\u0002M9\u00985\u00aa\u00beD\u00a4\n\u00f7\r\u00e8\u0098_\u00ac\u00acx\u00f4\u00e8Z:\u00c9s~\u00d3\r\u00f9\b\u00c8\u0002\u00a9\u00f2|\u001f\u009b\u00f5\u0090,]Yb\u00e8\u00bc\u00ab\u000e7\u0003\u009e\\\u001a\u00f3\u00fc\u0086X\u00ae\u0015\u00a69WM\u00fd\u0015Io\u00bc\u00d6t\u00b1=\u0017\u000e<y\u00d7\u0001\u00ea\u0005\u007f\u00df\b\u00ac$\u00d0\u0089[\u00b7\u0016\u00058Z\u00ac\f<iTF\u00edl\u0088u\u00f0_v\u00ae<u\u0005\u00b6\u00e67b\u00d4\u0017V\u0000aV\u0004\u00c3\u00f4Q\u00d8\u0010\u00ba\u0088+\u00e0\u0098\u00a6\u0019]-L\u00e5\u00fa\u0010\u00c1\\\u00cb\u00fbO\u001cL\u00dd\u00d0\u000b\u0003Vj+\u00ad\u00f7\u00100\u00fa\u0098'O#\u00f8\u0086^\u00fc\u0098t\u00e5\u0016\u00fe\u008f\u00186\u0098w\u00f0A\u0000\u009b\u00de\u00f3f\u00c87\u00b5H\u00a4\u001a\u0081\u00c2\u0019\u00e1\u00d9\u00c9\u0087*\u0088\u00cbP\u0090\u00c8\u001b\u008c\u0002N\u00bd]\u00e3r\u009d8\u00f8\u00a2f\u00e5q\u00aeg\u000f\u0092J\u0090\u00f3R\u00ca\u008b\u0087Dp\u001c\u00c9=\u0097*\u008a\u00cetY\u00043\u00c9\bm\u00a5\u00f8\u00d9\u00fa\u00a2{\u00b25\u0002&\u00a74\u00a8\u000f\u00afP\u008a\u00be\u00dfm\u001d_\u009f?%k\u00cc\u00a9\u00c0.\u00fc\u00f36a\u00ed\u00d9e\u00cc\u00cem\u001b\u0097Ap\u0082l\u00b1@\u001f\u00a5c1\u009e\u00d8\u00a4\u00dc\u00c2\u00bb{\u00cc\u00ec\u00a5\u00fcgJ\u00e8'\u0087\u00baQ\u00e4\u008f;\u00c1ay0\u00ea\u0001;\u009e\u00a6x\u00bfZ7\u00bd\u00c6\u00bd\u00b6\u0010\u000f/\u00a7\u00cdb\u00fb9W7\u00a8\u00c4\u00fcz\u0002e\u00a2 \u00a70N\u00db\u0007\u00a6\u00e9i\u00ff\u00a6H\u00f6\u000bsd\u0012\u0010E\u0092!\u0004/\"a\u001c\u00a2\u00ef\u00da>;\u0016)\u0018\u00e7\u001e\u00b8\f?\u00ffy\u0099[L\u00f2\u00e4q\u00c9\u00ff\u00acd\u00dbI)\u00b7\u00ba\u0014/\u0010\u00fc\u00a4\u00e2\u0089\u00fc\u0013\u00d3\u0001\u009a\u00fa\u00b29\u000f\u00b7\u00ad0\u0010H\u0085\u00a7b\u00d9\u00f0\u00d7x\u00a3L\u00ec0e\u00f6\u00b9y \u00d2\u00ae\u00f1\u00d1\u00a5\u00a7\u008f\u00f3_4\u008e\u00a0\u00cd\u00b1\u00ed\u0088ZV\u0014\u00ee\u0085\u0002\u00cbG+\u00bbV\u0005\u00bd\u00bdEo\u0010-\u00a0gh\u00c4\u00bc\u00f8\u00bb\u0019\u00015\u00bdH0\u0004\u001a O\u00d4\u0097\u0099\u00d3*\u000eu\u00d1\u00e15\u00af g\u00eb\u00a2q\u00d7\u00c0\u001a\u00c1\u00d2\u001dK\u00fb\u00b9\u0015\u00ee\u00b7\u00ed@\u00b2\u0010\u00e4\u008f]xwb\u00b3\u001fAul\u00e9\u00e6|N\u0082\u0010\u0005g=\u00b8Gc\u00f3\u001d\u00e8\u00f72g\u0084\u00ee\u0014\u00f4\u0010&\u00fc\tO\u00fc%u\u0000\u0013\u00cb\u00b7\u00b2\u0007\u00d3\u0015\u00f6\u0088\u00e1$\u00cb;q\u0016Ejg\u00a6\u0095\u00fam9\u0015\u00e0\u00c9\u00fa\u00fa\u00f5\u0010\u00f1\u00c1m,\u00a5,\u00a0R\u00f0vK'\u0094\r\u00db@E\u00db\u009db\u00ca\u00c9\u00fd\u001a\u00ea6W\u00fd\u00f4\u009a\u00b2\u00cb\u0004\u0092\u00c4a\u00a9\t\u0087\u001dR\u00c7q\u009bxu\u00cb\u00d5\u008f}7Z|4\u00b1\u00df\u0011\u0089^&]\u00df\u00f4f\u00dd\u00b5}hVg\u0016\u00fd\u00b8\u00c4L\u001e\u00e3\u00c9\u0086b\u00e6\u0089\u00a7\u00d0\u00c2;\u0088\u00b3W}\u00fa\u00f6BW\u00d1\u0016\u00d6$\u00eb\u0090\u0001\u00b8\u001a\u00c5\u00a7g\u0081\u00e2\u00ce+\u00c6\u00a9t\u00ac: \u00d64\u00e2\u00b2\u00a9\u00b10\u0003\b\u001a\u00c9\u00ceo\u00a5,\\t\u00cd\u0018\u0094\u008c\u00fa\u0099L\u00a7#[\u00d7\u0084h\u00b9\u0017\u0018\u00ce\u0004v\u00c8\u00ac\u00c7\u00b6\u00a7\u0012n\u00cb\u008cZs\u00d9\u00ff\u00dfa\u0087\u009c\u00c6U\u001b\u00fa\u0018\u0001{\u00a1O\u00bb\u00d1\u00fa\u00c6\u0019iDU\u0089\u0004\u00d2\u00f9\u0003\\0t\t\u00f4\u00c3\u00ab\u0010\u00a8\u0096k\u00baJ\u0085\u00cam\u00d9\u008a[\u00c1|fL\u009d";
                        var17_6 = 845;
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
                            var18_3[var16_4++] = NoDelay.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0002$XSi\u00e5\u001dYz\u0012?\u00b1_\u0091\u0010\u001e\u0004=\u00c3\u00ea\u0083\u0097\u009cgX\u00ea*\u00c7`\u0019\u00e3\u0098\u001a \u00e6\u00f5Pt\u0093\u00bbT\u00da\f\u0019\u008a\u00aa\u00cd`\u00df\u0002\u00d0K\u0092y/\u00eb\u0014\u00e3\u009e\u00c0\u00bc\u00dbQm\u00da\u0091a3*\u00b0/\u00d3\u00b3s\u00e6JN\u00cfq\u001bt)\u00c5\u00db\u00e7\r\u0012\u00ec\u0095\u0001RP\u00d39\u00e4Y\u00fa3\u00a26\u00b5\n\u0084\u00b7\u00c5_\u009aD\u00ab\u00ed\u001e\u00db\u00ec\u00f2c\u00e4XK\u00ee\u009b\u00a7\u00e4f.\u00d3\u0017\u00d5\u00b7\u0018\u00c9\u00e6\u00d4\u00cc\u001cpt.\u00d8\u009d\u0011\u00d6,;\u00f3\u0011\u0084Km\u00ac\u00d6\u00e3\u0098U";
                            var17_6 = 153;
                            var14_7 = 128;
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
                            var18_3[var16_4++] = NoDelay.zkmDecodeUtf8(var19_9).intern();
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
                NoDelay.c = var18_3;
                NoDelay.d = new String[24];
                var1_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var2_11 = 1; var2_11 < 8; ++var2_11) {
                    v9 = v9;
                    v9[var2_11] = (byte)(var20 << var2_11 * 8 >>> 56);
                }
                var1_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var0_12 = new long[4];
                var4_13 = 0;
                var5_14 = "w\u00af\u00d3\u001c\u00be\u00e0DQ\u00074c^\u00ac+\u00fd^";
                var6_15 = 16;
                var3_16 = 0;
                while (true) {
                    var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                    v10 = var0_12;
                    v11 = var4_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl77:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    var5_14 = "d\u00e2S\u00c2\u0091\u009a\u00ed0\u00a7\u00af(\u00e8q\u00f2\u00d4\u00e9";
                    var6_15 = 16;
                    var3_16 = 0;
                    while (true) {
                        var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                        v10 = var0_12;
                        v11 = var4_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl90:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl103:
                // 1 sources

                ** continue;
            }
        }
        var28_20 = new KProperty[(int)var0_12[2]];
        var28_20[0] = Reflection.property1(new PropertyReference1Impl(NoDelay.class, "delay", "getDelay()I", 0));
        var28_20[1] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoDelay.class, "jump", "getJump()Z", 0));
        var28_20[2] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoDelay.class, "blocks", "getBlocks()Z", 0));
        var28_20[3] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoDelay.class, "crystals", "getCrystals()Z", 0));
        var28_20[4] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoDelay.class, "xp", "getXp()Z", 0));
        var28_20[5] = Reflection.mutableProperty1(new MutablePropertyReference1Impl(NoDelay.class, "all", "getAll()Z", 0));
        NoDelay.delegatedProperties = var28_20;
        NoDelay.INSTANCE = new NoDelay();
        NoDelay.delay$delegate = SettingFactory.intSetting$default(NoDelay.INSTANCE, "delay", 1, new IntRange(0, 4), null, null, (int)var0_12[3], null);
        NoDelay.jump$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoDelay.INSTANCE, "jump", false, null, null, (int)var0_12[1], null);
        NoDelay.blocks$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoDelay.INSTANCE, "blocks", false, null, null, (int)var0_12[0], null);
        NoDelay.crystals$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoDelay.INSTANCE, "crystals", false, null, null, (int)var0_12[0], null);
        NoDelay.xp$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoDelay.INSTANCE, "x-p", false, null, null, (int)var0_12[0], null);
        NoDelay.all$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoDelay.INSTANCE, "all", true, null, null, (int)var0_12[0], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x584A;
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
                throw new RuntimeException("su/catlean/ey", exception);
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
            NoDelay.d[n2] = NoDelay.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoDelay.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ey" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoDelay.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

