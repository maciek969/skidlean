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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import su.catlean.zkm.ZkmKeys;

public final class NotifierHelper {
    @NotNull
    public static final NotifierHelper INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private NotifierHelper() {
    }

    @NotNull
    public final ItemStack getColorName(int n) {
        Text text;
        List<StatusEffectInstance> list;
        long l = 0L;
        l = a ^ l;
        switch (n) {
            case -3407872: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[2];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.RESISTANCE, 3600, 0);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.STRENGTH, 1800, 3);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a74\u00a7l[\u2605]\u00a7r\u00a7c\u00a7l \u0417\u0435\u043b\u044c\u0435 \u041a\u0438\u043b\u043b\u0435\u0440\u0430\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            case -16711681: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[2];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.JUMP_BOOST, 3600, 0);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.SPEED, 3600, 2);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a73\u00a7l[\u2605]\u00a7r\u00a7b\u00a7l \u041c\u043e\u0447\u0430 \u0424\u043b\u0435\u0448\u0430\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            case -65281: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[2];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 900, 2);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.REGENERATION, 900, 2);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a75\u00a7l[\u2605]\u00a7r\u00a7d\u00a7l \u0417\u0435\u043b\u044c\u0435 \u041c\u0435\u0434\u0438\u043a\u0430\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            case -256: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[5];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 18000, 0);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.HASTE, 3600, 0);
                statusEffectInstanceArray[2] = new StatusEffectInstance(StatusEffects.INVISIBILITY, 18000, 0);
                statusEffectInstanceArray[3] = new StatusEffectInstance(StatusEffects.SPEED, 18000, 2);
                statusEffectInstanceArray[4] = new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 2);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a76\u00a7l[\u2605]\u00a7r\u00a7e\u00a7l \u0417\u0435\u043b\u044c\u0435 \u0430\u0433\u0435\u043d\u0442\u0430\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            case -16711936: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[4];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 3600, 1);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.INVISIBILITY, 18000, 0);
                statusEffectInstanceArray[2] = new StatusEffectInstance(StatusEffects.REGENERATION, 1200, 1);
                statusEffectInstanceArray[3] = new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 0);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a72\u00a7l[\u2605]\u00a7r\u00a7a\u00a7l \u0417\u0435\u043b\u044c\u0435 \u041f\u043e\u0431\u0435\u0434\u0438\u0442\u0435\u043b\u044f\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            case -39424: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[5];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 0);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.GLOWING, 3600, 0);
                statusEffectInstanceArray[2] = new StatusEffectInstance(StatusEffects.HUNGER, 1800, 9);
                statusEffectInstanceArray[3] = new StatusEffectInstance(StatusEffects.SLOWNESS, 3600, 2);
                statusEffectInstanceArray[4] = new StatusEffectInstance(StatusEffects.WITHER, 600, 4);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a7c\u00a7l[\u2605]\u00a7r\u00a76\u00a7l \u0417\u0435\u043b\u044c\u0435 \u041e\u0442\u0440\u044b\u0436\u043a\u0438\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            case -6684877: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[4];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.POISON, 1000, 1);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.SLOWNESS, 1800, 3);
                statusEffectInstanceArray[2] = new StatusEffectInstance(StatusEffects.WEAKNESS, 1800, 2);
                statusEffectInstanceArray[3] = new StatusEffectInstance(StatusEffects.WITHER, 600, 4);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a72\u00a7l[\u2605]\u00a7r\u00a7a\u00a7l \u0421\u0435\u0440\u043d\u0430\u044f \u043a\u0438\u0441\u043b\u043e\u0442\u0430\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            case -1: {
                StatusEffectInstance[] statusEffectInstanceArray = new StatusEffectInstance[2];
                statusEffectInstanceArray[0] = new StatusEffectInstance(StatusEffects.BLINDNESS, 400, 0);
                statusEffectInstanceArray[1] = new StatusEffectInstance(StatusEffects.GLOWING, 4800, 0);
                list = CollectionsKt.listOf(statusEffectInstanceArray);
                MutableText mutableText = Text.literal("\u00a76\u00a7l[\u2605]\u00a7r\u00a7e\u00a7l \u0412\u0441\u043f\u044b\u0448\u043a\u0430\u00a7r");
                Intrinsics.checkNotNullExpressionValue(mutableText, "literal(...)");
                text = mutableText;
                break;
            }
            default: {
                ItemStack itemStack = new ItemStack(Items.SPLASH_POTION);
                itemStack.set(DataComponentTypes.CUSTOM_NAME, Text.literal("\u00a77\u041d\u0435\u0438\u0437\u0432\u0435\u0441\u0442\u043d\u043e\u0435 \u0437\u0435\u043b\u044c\u0435"));
                return itemStack;
            }
        }
        ItemStack itemStack = new ItemStack(Items.SPLASH_POTION);
        itemStack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(Optional.empty(), Optional.empty(), list, Optional.empty()));
        itemStack.set(DataComponentTypes.CUSTOM_NAME, text);
        return itemStack;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        NotifierHelper.a = ZkmKeys.a(-1305285687398579513L, -289758182078537564L, MethodHandles.lookup().lookupClass()).a(151652320121607L);
                        NotifierHelper.d = new HashMap<K, V>(13);
                        var11 = NotifierHelper.a ^ 121706793940229L;
                        var13_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var11 >>> 56);
                        for (var14_2 = 1; var14_2 < 8; ++var14_2) {
                            v2 = v2;
                            v2[var14_2] = (byte)(var11 << var14_2 * 8 >>> 56);
                        }
                        var13_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var20_3 = new String[11];
                        var18_4 = 0;
                        var17_5 = "\u00db\u00b5\u00a3yc9E\u001e\u008ev\u0004a\u00ad\u00ffk\u00fe\u0004\u00b8[\u00af\u000b9\u0016$\u00edU\u00cc\u00e0\u001b~Ym\u00ff\u00d8\u00b8\u008b\u00d3\u00ae\u00da\u0098\u00f9=\u00ce\u00e1\u00ac\u0099\u0017yR\f3 \u00a8\u00c1\u00e2\u0092\u00cd\u0094\u00a5S\u001c\u00e2/X\u0092\u0014\u007f\u00aa\u0001=t\u0094b\u00a0\u00f4\u00ba\u0099\u008ahw\u00b9F:\u000e\u00c7\u0088\f\u0013X6\u00051\n\u00b9\u0095%3\t\u00be-\u00d5\u0012\u0097\"\u00bc\u00fd=JDa\u008d\u0015l\u0093|\u00db\u009aV_&\u000b\u00d5\u00df\u00ee2\u00edrCU\u0005\u00c4\u0097\u000e\u000b\u00be2}\u00e0{\u0089\u00b6\u00ea\u00dc<7\u00be3\u0016\u0097\u00ed\u0092l;\u00aa\u00fb\u0011\u00a77\u00b1c\u00c7\u00d6q\u00af\u009c\u0093@\u00ea\u008bS\u00ef,\u00d4\u0010_O\u0088Hqy'\u00fd9\u00faO\u00ac\u000e\u00a7\u0090\u0003\u0093_D\u0087\u00c9\u00def\u008e\u0085\u0087q\u00dc.F\u00cfZU\u00de\u00f9\u00abVy%\u0084Z'\u00cd\bjRF\u00e9o\u00f6k\u0011p\u0082U\u0092\u00d6\u00d71\u00d9\u00c4Z{`\u00aeez%VQ'\u00bd\u001c\u0015Z\u0082@W\u00c8\u00f8K\u00af\u00f4(\u00c6\u00f6\u001a\u00b7\u00bc\u0090\u00cd\u00cfw\u008c\u00c7\u00cb\u0093\u0007\u0080eZ\u00aer\u00eeF\u00a1\u001c\u00ac\u00d0\u00bbj`Bw\u00c1b\u0010w-\u0014=\b\u00d0\u00c2\u00ac\u000eF\u007f\u00ab\u000f\u00f2\u00b7\u00ad9W\u0086\u00fb\u00fe\u000b\u00ae\u00c2`\t)\u009dM\u000ex\f\u008d\u00f3P\u00e6\u00ff\u0011\u00a5/<\u000fq\n\u001a\u00be_ `\u0000\u00d3\u00f2|\u00e75\u00c5\u00a8\u00bcj~\u00b4f\u0084\u00cd\u00d1x\u008c\u0089/E\u0099\u00aa^\u00f1>\u00f1]\u0004\u00e3\u009bx\u00ac\u00d4\u008b\u00c5\u00a1\u00cc\u00b8\u0094\nZ[?\u0003\u00bcw\u0084M\u0084\u008b\u00edx\u0094\u00bf7?\u00ce\u00c1\u00f5n\u00bbhFU$gEa\u0083\u00b4\u00b8 \np\u008f\u00b2\u00e8\u00c6Ew\u00a7\u00ce \u00ef\u0001\u00a7={L\u00d5#\u0098\u00f0oS).:\u0080\u00b0l\u00f9.\u00c8`V\u00b9\u009c\u00e2\u0087_\u008b\u00f9\u00bc\u008e\u0090\u00e4\u0092\u000b\u00dbj\u00ef\u001at\u00ac\u00f0\u00d2\u00d5l\u00aa5\u00cb\u00fd\u00f2\u00a9w(9\r@,5b\u00b9\u00ff?\u00e1W\u0018\u00d6\u0098\u00c4\u00bb\u00c1\u00e8d\u00e4\u0090\u00d7(\u00b4\u00f5[\u009bd+\u00e6\u0093\u00aaeW^\u00cc\u0004\u0015\u001f\u0019\u00e0\u00ba\u00fcH:\u0081\u00fb\u008f1\u00ff~\u0006\rX\u00dd\u00bfMj\u00c6\u00c6\u001b\u00d1\u00acf \u00ce)o\u008e]\u00da\u00fb\u00ec\u009a\u00ad\u0011\u001f\u00d9\u00cb\u0084V\u00e5\u00e4\u00b5\u00abq\u0087\u00af`LL3\u0083\u008c\u00fb\u00d9\u00ccHdq\u00ea)\u0012i\u000e\u0007\u00acP\\\u00fcq\u00ff\u00c0P\u0019\u009a\u00aeU\u0093\u00e2\tO`f\u001e\u009fn\u0093\u00ab\u0012.\u00c9\u00bf\u00bfr\u001e\u00caQ\u00ae\u009cn\u00d4\u00fb\u00be&8\u00ed\u00dd\u000ef)\u00c2>\u00a93%\u007f\u00a24\u00ea\u00f8\u0080$\u0004\u00d3\u009e\u00c9T\u00b1\u00a7";
                        var19_6 = 648;
                        var16_7 = 88;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = NotifierHelper.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "\u00b9\u0090\u00c6\u00cc\u00cd\u00fd\u0002l\u00f8\u00e7\u00bc\u00b0;n#\u0098\u00ef\u00a2y\u00f5\u00d47m\u00ed\u00f0o\u0000\u00cbOi\u00ae(\u0011L\u00c9[\u0093$\u00fc;\u00e23\u008d\u0092|MZ\fg\u0002d\u00aa\u0019e0\u001aU\t:R9b\u00c8_Hc\u009f=\u00c0\u00e7P0m\u00ac\n\u00c3\u00f3\u00de\u00c7\u0086\u008a\u00f5\u00a3\u0099y\u008c\u0092\t\u0097Q\u00ef\u00d2\u00c4|\"\u001a\u00a2\u00a2\u0096\u001129k\u001e\u00ea+\u000e\tI\u00b23.\u00d4\u00fd\u00b7R\u0019h\u0082\u00df[\u00143\u0004\u00b0|\u00a5\u00ee\u00a3\u0084<e&\u008c\u00a5\u00ecj";
                            var19_6 = 137;
                            var16_7 = 64;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = NotifierHelper.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block19;
                            break;
                        }
                    }
                    var21_9 = var13_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                NotifierHelper.b = var20_3;
                NotifierHelper.c = new String[11];
                NotifierHelper.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var11 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var11 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[18];
                var3_13 = 0;
                var4_14 = "\u00f8\u00ee\u00e3\u00a6Q\u00a9\u001a.\u0098\u00dd\u001eqg\u00f4\u0095\u00c6\u0097j>\u00c8W\u00eb\r4\u0014YFz\u0018\u00ba\u00cdH/,\u00fdO\u00d8|\u00f1\u00d5k(\u00d6\u008e\u00b7\u00d3\u00b5\u0087\u00fd\u000bQX\u00e6\u00dfA\u00ed\u00b1\u001d\u00c3&Y\u009a\u0090\u009eL\u00c9\u0010\u00e6c=~l\u008c\tuS\u00f5?\u00b8\u001a\u0019\u00e9\u00e3\u0010\u00e2\u00c9\u00b5nYT\u00ccFN\u00f4\u00ac\u0084\u00a3\u00a4\u00a4\u0099\u00d0 \u009em\u001fM\u00d6\u00f0\u0091\u0093s\u00d0\u0014#h\u008a\u00a5\u0003\u00aad\u00c3W9tfV\u00a62";
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
                    var4_14 = "!\u00e6w\u00e9%\u00a9\u00e0\u00da[M1|a\u00c7w^";
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
        NotifierHelper.e = var6_12;
        NotifierHelper.f = new Integer[18];
        NotifierHelper.INSTANCE = new NotifierHelper();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1D2F;
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
                throw new RuntimeException("su/catlean/wi", exception);
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
            NotifierHelper.c[n2] = NotifierHelper.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NotifierHelper.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x26E5;
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
                throw new RuntimeException("su/catlean/wi", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            NotifierHelper.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = NotifierHelper.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/wi" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NotifierHelper.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(NotifierHelper.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

