/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.ScreenEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.SignEditScreenAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.cosmetic.HandChams;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.TextSetting;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ModuleMessages;
import su.catlean.util.PacketUtil;
import su.catlean.util.PlacementHelper;
import su.catlean.util.SlotResult;
import su.catlean.zkm.ZkmKeys;

public final class AutoSign
extends Module {
    @NotNull
    public static final AutoSign INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final TextSetting line1$delegate;
    @NotNull
    private static final TextSetting line2$delegate;
    @NotNull
    private static final TextSetting line3$delegate;
    @NotNull
    private static final TextSetting line4$delegate;
    @NotNull
    private static final TextSetting dateFormat$delegate;
    @NotNull
    private static final BooleanSetting glow$delegate;
    private static final long c;
    private static final String[] d;
    private static final String[] f;
    private static final Map h;

    private AutoSign() {
        super("auto-sign", Categories.getWorld(), null, null);
    }

    private final String getLine1() {
        return (String)line1$delegate.getValue(this, delegatedProperties[0]);
    }

    private final String getLine2() {
        return (String)line2$delegate.getValue(this, delegatedProperties[1]);
    }

    private final String getLine3() {
        return (String)line3$delegate.getValue(this, delegatedProperties[2]);
    }

    private final String getLine4() {
        return (String)line4$delegate.getValue(this, delegatedProperties[3]);
    }

    private final String getDateFormat() {
        return (String)dateFormat$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getGlow() {
        return (Boolean)glow$delegate.getValue(this, delegatedProperties[5]);
    }

    @Flow
    public final void onScreen(@NotNull ScreenEvent screenEvent) {
        Intrinsics.checkNotNullParameter(screenEvent, "e");
        if (screenEvent.getScreen() instanceof SignEditScreen) {
            Screen screen = screenEvent.getScreen();
            Intrinsics.checkNotNull(screen, "null cannot be cast to non-null type su.catlean.mixins.accessors.SignEditScreenAccessor");
            SignEditScreenAccessor signEditScreenAccessor = (SignEditScreenAccessor)((Object)screen);
            screenEvent.cancel();
            String string = this.getLine1();
            String string2 = this.getLine2();
            String string3 = this.getLine3();
            String string4 = this.getLine4();
            PacketUtil.sendPacket(new UpdateSignC2SPacket(signEditScreenAccessor.getSign().getPos(), signEditScreenAccessor.isIsFrontText(), this.formatDate(string), this.formatDate(string2), this.formatDate(string3), this.formatDate(string4)));
            if (this.getGlow() && !signEditScreenAccessor.getSign().getText(true).isGlowing()) {
                Item[] itemArray = new Item[1];
                Intrinsics.checkNotNullExpressionValue(Items.GLOW_INK_SAC, "GLOW_INK_SAC");
                SlotResult slotResult = InventoryUtil.INSTANCE.findInHotbar(itemArray);
                ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                Intrinsics.checkNotNull(clientPlayerEntity);
                boolean bl = clientPlayerEntity.getOffHandStack().getItem() == Items.GLOW_INK_SAC;
                ClientPlayerEntity clientPlayerEntity2 = Globals.getMc().player;
                Intrinsics.checkNotNull(clientPlayerEntity2);
                boolean bl2 = clientPlayerEntity2.isSneaking();
                if (slotResult.isFound() || bl) {
                    InventoryUtil.INSTANCE.saveSelectedSlot();
                    slotResult.select();
                    if (bl2) {
                        PlacementHelper.INSTANCE.setViaVersionFix(false);
                    }
                    Globals.getInteractionManager().interactBlock(Globals.getPlayer(), bl ? Hand.OFF_HAND : Hand.MAIN_HAND, new BlockHitResult(signEditScreenAccessor.getSign().getPos().toCenterPos().add(0.0, 0.5, 0.0), Direction.UP, signEditScreenAccessor.getSign().getPos(), false));
                    if (bl2) {
                        PlacementHelper.INSTANCE.setViaVersionFix(true);
                    }
                    PacketUtil.sendPacket(new HandSwingC2SPacket(bl ? Hand.OFF_HAND : Hand.MAIN_HAND));
                    InventoryUtil.INSTANCE.restoreSelectedSlot();
                }
            }
        }
    }

    private final String formatDate(String string) {
        String string2 = "dd/MM/yyyy";
        try {
            String string3 = new SimpleDateFormat(this.getDateFormat()).format(new Date());
            Intrinsics.checkNotNullExpressionValue(string3, "format(...)");
            string2 = string3;
        }
        catch (Exception exception) {
            ModuleMessages.S(this, Formatting.RED + "Your date format is wrong!", false, null);
        }
        String string4 = Globals.getMc().getSession().getUsername();
        Intrinsics.checkNotNullExpressionValue(string4, "getName(...)");
        return StringsKt.replace$default(StringsKt.replace$default(string, "<player>", string4, false, 4, null), "<date>", string2, false, 4, null);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private final boolean usesDate() {
        int n = HandChams.zkmIntConstant();
        boolean bl = StringsKt.contains$default((CharSequence)this.getLine1(), "<date>", false, 2, null);
        if (n != 0) return bl;
        if (bl) return true;
        bl = StringsKt.contains$default((CharSequence)this.getLine2(), "<date>", false, 2, null);
        if (n != 0) return bl;
        if (bl) return true;
        bl = StringsKt.contains$default((CharSequence)this.getLine3(), "<date>", false, 2, null);
        if (n != 0) return bl;
        if (bl) return true;
        bl = StringsKt.contains$default((CharSequence)this.getLine4(), "<date>", false, 2, null);
        if (n != 0) return bl;
        if (!bl) return false;
        return true;
    }

    private static final boolean settingVisible() {
        return INSTANCE.usesDate();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        AutoSign.c = ZkmKeys.a(6421587437488320030L, -3118933473525214565L, MethodHandles.lookup().lookupClass()).a(67492385117281L);
                        var20 = AutoSign.c ^ 78263619134787L;
                        AutoSign.h = new HashMap<K, V>(13);
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
                        var18_3 = new String[32];
                        var16_4 = 0;
                        var15_5 = "\u00ddn3\u001b\u0007\u0004\u0088|\u00c3\u00914L\u00abLM`\u00e3\u00cb\u0001\"\u0080\u00f5\u00d1<W\u009d\t6\u0012\u000e&\u0090\u0016}\u0098\fe\u00ac\u00fcHU\u00faK\u00c7hm\u00d1\u00eb\u001dKU\u00a6,\r-\u00e5\u0010g\u00b1\u00ef\u00a9\u00f3\u001ehj\u00d769\u008f<\u0015\u00d1\u00c4\u0018B\u008c` \u00f8\f\u00a1Z\u00ea\u008a\u00f31'\u0015\u00d7r\u00de\u00caa}\u008f.H\u00c88]\u009b{br\u0095[\u0098\u00a2\u0098W#(\u001aT\u00e3\u00e4\u008e\u00d0fq\u0084\u00ef-0Z\u0096\u00cd\u0011\u0086(\u0007\u0005H2\u0089g\t';\u000e|^\u0084\u008d\u0099\u0084\u00ad1k\u00fb\u00bc\u00ac\u00afC\u00f1 \u0010\u0095C&\n\u00ee\u00ce\u00fcn\u00de\u008fn\u00b3\u0097\n\u00dd\u0096\u00dfd\u0004\u001c\u0013\u0006\u0091\u0095\u00ea\u00d3\u0010J\u00df\r\u0083 \u0006\u00b9\u0083\u0013O3xe\u0081\u00ba\u00f9\u0016\u00c1\u008b\u00d5\u0096`4\u0086\u00aa\u00be\u008e=\u00d1\u0089\u00a7\u00d8\u0091L\u0093\u0005\u00a5\u00185\u00f8y1v\u00f73\u00e07{\u0017\u0099\u00c2F\u00a3\u00a9@\u00e3<w;\u00e2\u00bcs0\u00bd\u00c6\u00b2\u00fa\u00bb\u0088I\u00e4\u00de\u00fc\u0080\u0083\u009c\u0096\u00fa\u00a3\u001eM\u00e9\u0005\u00147P\u0099\u008aL\u001e\u009d-\u0089<>y\u00abO_`\u00c4k)\u00f7\u0092\u00cdgqd\u00fa\u0000 }\u008dU\u00b8\u0004M\u00bb(\u00d7*pH\\v\u007f%\u00ff\u00b5K\u009f\u0096t\fD\u00b1\u00f0\r!`\u00e4&\u0081\u0010'\u00b3P\u0010|&\u00fd\u008fa\u00c6\u00a4\u0090t\u0091z\u009d\u00106\u0016\u00d1\u0019p\"\u008fm\u00e1rTa\u00db\u008b\u00c8\u00fa Dn\u008fC\u00a3\u0098uKU{\u00f2E]\u009b\u009av+Z[\u00e1\u00d4q\u0015Z\u009d\u00af\u0090\u0011E~\u00ef\u001b %z\u00deH9\u0087\u00ab\u00f9Pq\u00d8\u00fc\u00fd'p\u00b1\u00f0n\u009e\u0011\u000e\u00a9\u009b\u009bTU\u000ec`\u00bfH\b\u0010\u00c7\u00fe\u00fc/\u00d0\u009a\u000f\u00de\u00d6p\u0081\u008e\u00ba\u009f/\u00ad\u0010\u00cc(\u00be\u00c6\u0082\u000bGB\u009dMS\u00e1\u00a6`\u00f5N8k\u00ac\u0089\u0099\u00a3\u00d4m\"j\u009bXM\u00bb\u00d7A\u00b9a\u00fa\u00e1\u00af\u00c0b[\u00f8\u00e38\u00fc\u0005\u000e\t\u008e\u008e\u00ac\u00caN\u00de\u00bf\u00f57no\u001c2\u00cbWo\u00c1uX\u008ew\n\u0086w\u0095x8\u00c7\u001e\u0090\u00db\u00e9\u00ac_\u0096\u00ceIU\u0081\u00c9\u00a5\u00b6sD\u00b4s\u0016\u009ej\u00fb\u00e4>M\u00acc\u00c8\u00b4.ly\u00f9:m\f\u0099\u00f5\u00e0\u00ad\u0015\u0098\u00c9N\u00d4?\u00da\u00c5\u00a3{\u00d8\u001f\u0083\u00b4\u0002\u0018\u0018w\u00df\u00a0\u00ae\u000e\u00af\u0098\u00f8\u0017\u009b\u00b2\u00e3\u00c1\u008au\u00a8\u00a4y\u009a\u00d3eab\u0010R\u00cb\u00ec\u000e\u0090\u0001s\u00d3\u0000r\u00c4\u0080\u00e4\u00b3\u0090\u0080 \u000f\u0097\f\u00f6\n\u00a7E[\u001a\u00e8\u00fem1\u00d5G\u00c8\u00b4\u00b0\u000e\u00d6\u00d9\u00a8\u00ea\u00cd_\u00f7l\u00fc\u00d1\u009f\u0095\u00dc\u0010\u00cb\u00c1\u00a0_+^\u0089]'\u001e\n\u008a\u0017}\u00e5\u00b2\u0010R\u00c9\u00f6\u00fb\u00fe\u0006X\u000b\u008fZ\u00d8\u00bb\u008ee$\u0007 \r\u00bfJO\u00fdi\u0005\u0002\fa\u00a8\u00be\u00a7\u0090^\u00d1\u00edU\u00d0 ,\u00b2\u00d9K\u00a3f9[\u00fbkbD\u0010\u008f\u00dd\u0089\u00d3:hF\\\u00d4h\u0092AW\u00c4\u00f4K \u0098\u00bas\u00ad\u00cf\u00fe\u00ff\u00b6;\u00cf7\u00df$\u009e\u00e0\u00e0\u008a\u00e1\u00e1\u001cK~\u0099s\u0086\u00f4\u00d0f[v\u00a6P\u0010\u00a3Z\u00aag\u00ca\u001c.\u00a1C\u00a9\u0006\b\u0088\u001a>\u00e6@\u00a48\u0011$G\u00b4\r\u00e6M\u000f\"$3\u008b\u00fd\u00e1\u00e0>\u00ec\u00c2\u001a\u00fdD[\u00b8}#z \u00da\u0002To\u00f5\u0084\u0082l\u0013\u00c1\u009f\\\u00d1C(\u00f3\u00d0\u0019d\u00afsP\u00c1\u00a5\u0080<u\u00e3\fWN<\u00fb\u00e8\u0086\u0010\u009dn\u0019Dcy\u00fe\u00ca`\u0080\u00b5\u001b\u00e6\u0087\u00c8O \u00fe\u0094\u00c9\u0090\u00de\u0017\u00c9h\u00ebA\u00b0\u0083\u00a96\u00ac\u00cf\u00ac\u00db\u00d0\u00c9\u00f6Wa\u00d1<\u00c6\u00eas\u0015k\u00cb\u00af\u0010\u0088\u0080\u009d\u00e9\b\u0083\\\u00db\u0094\u00b0]\u00dfvi\u0011\u00ad";
                        var17_6 = 917;
                        var14_7 = 56;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoSign.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "U(\u00ee\u00e7\u0004)U\u0007\u0087wp:\u00c4\u0081\u000f\u00d3}\u00e6\u00a3\u001d\u00f6@j:\u00eb\u00e9\u0094u\u00b3g\u007f\u0084-h\u00c7\u00d7\u00b2R\u00f9\u00dc\u00dd~X\u00adNP7&\u00d1\u00f2w\u0083\u0012\u0093\u008a\u00ca\u0012\u0016\u00a1\u00ab\u00d8\u0016yC\u00df\u00c6nkHNr\u00c7=\n\u00b3 4x\u007f\u00cbmB_\u008f\u0085\u00dd\u0002\u00a1\u00d2s5y\u00e9\u00a0\u00cd\u00d7Q\u0085\u00ba\u00ff\u00e6\u00a55\u00c6\u009c\u00e0W\u001a\u009dJ\u001d\u00bc\u00a7\u00a8d\u00c7\u0003\u0099)\u008bWd\u00ff\u00d6\u009a\u00b4\u001a\u00d0/\u00c4\u00c9\u00e1<B\u00dbc\u0018\u00032PdQ\u00da\u0019\u00c4u\u00d8\u00ac]\u008fE\u00f0]\r\u0099\u0081\u00ed\u0084\u008e\u0087\u00e5";
                            var17_6 = 161;
                            var14_7 = 136;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AutoSign.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                AutoSign.d = var18_3;
                AutoSign.f = new String[32];
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
                var0_12 = new long[3];
                var4_13 = 0;
                var5_14 = "\\\u00ab;{\u00b1\u00d8\u00f47\u0017\u0016\u0084e3\u00ca2\u0012$\u0088\u00ca\u00aeM\u0097\n\u00f4";
                var6_15 = 24;
                var3_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl72:
                // 1 sources

                while (true) {
                    var0_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var3_16 < var6_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
            v10 = var4_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        var28_20 = new KProperty[(int)var0_12[0]];
        var28_20[0] = Reflection.property1(new PropertyReference1Impl(AutoSign.class, "line1", "getLine1()Ljava/lang/String;", 0));
        var28_20[1] = Reflection.property1(new PropertyReference1Impl(AutoSign.class, "line2", "getLine2()Ljava/lang/String;", 0));
        var28_20[2] = Reflection.property1(new PropertyReference1Impl(AutoSign.class, "line3", "getLine3()Ljava/lang/String;", 0));
        var28_20[3] = Reflection.property1(new PropertyReference1Impl(AutoSign.class, "line4", "getLine4()Ljava/lang/String;", 0));
        var28_20[4] = Reflection.property1(new PropertyReference1Impl(AutoSign.class, "dateFormat", "getDateFormat()Ljava/lang/String;", 0));
        var28_20[5] = Reflection.property1(new PropertyReference1Impl(AutoSign.class, "glow", "getGlow()Z", 0));
        AutoSign.delegatedProperties = var28_20;
        AutoSign.INSTANCE = new AutoSign();
        AutoSign.line1$delegate = SettingFactory.textSetting$default(AutoSign.INSTANCE, "line1", "<player>", null, null, (int)var0_12[1], null);
        AutoSign.line2$delegate = SettingFactory.textSetting$default(AutoSign.INSTANCE, "line2", "was here", null, null, (int)var0_12[2], null);
        AutoSign.line3$delegate = SettingFactory.textSetting$default(AutoSign.INSTANCE, "line3", "<------------->", null, null, (int)var0_12[2], null);
        AutoSign.line4$delegate = SettingFactory.textSetting$default(AutoSign.INSTANCE, "line4", "<date>", null, null, (int)var0_12[2], null);
        AutoSign.dateFormat$delegate = SettingFactory.textSetting$default(AutoSign.INSTANCE, "date-format", "dd/MM/yyyy", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        AutoSign.glow$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoSign.INSTANCE, "glowing", false, null, null, (int)var0_12[2], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3F9C;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])h.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ef", exception);
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
            AutoSign.f[n2] = AutoSign.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoSign.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ef" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoSign.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

