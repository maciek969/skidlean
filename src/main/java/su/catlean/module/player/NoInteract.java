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
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.block.TargetBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.InteractBlockEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.PlaceManager;
import su.catlean.zkm.ZkmKeys;

public final class NoInteract
extends Module {
    @NotNull
    public static final NoInteract INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting allBlocks$delegate;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private NoInteract() {
        super("no-interact", Categories.getPlayer(), null, null);
    }

    private final boolean getAllBlocks() {
        return (Boolean)allBlocks$delegate.getValue(this, delegatedProperties[0]);
    }

    @Flow
    private final void onInteractBlock(InteractBlockEvent interactBlockEvent) {
        Block block = Globals.getWorld().getBlockState(interactBlockEvent.getHitResult().getBlockPos()).getBlock();
        Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
        Block block2 = block;
        if (this.getAllBlocks() || PlaceManager.INSTANCE.isInteractable(block2) || block2 instanceof FenceBlock || block2 instanceof FenceGateBlock || block2 instanceof TrapdoorBlock || block2 instanceof DoorBlock || block2 instanceof TargetBlock || block2 instanceof RedstoneOreBlock) {
            interactBlockEvent.cancel();
        }
        if (Intrinsics.areEqual(Globals.getPlayer().getOffHandStack().getItem(), Items.PLAYER_HEAD) && interactBlockEvent.getHand() == Hand.OFF_HAND || Intrinsics.areEqual(Globals.getPlayer().getMainHandStack().getItem(), Items.PLAYER_HEAD) && interactBlockEvent.getHand() == Hand.MAIN_HAND) {
            interactBlockEvent.cancel();
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block14: {
            block13: {
                block12: {
                    NoInteract.a = ZkmKeys.a(5133760146891310689L, -7466767469422143719L, MethodHandles.lookup().lookupClass()).a(183746564738781L);
                    var16 = NoInteract.a ^ 134402172553499L;
                    NoInteract.d = new HashMap<K, V>(13);
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var16 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var16 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[5];
                    var12_4 = 0;
                    var11_5 = "o\u00c7\u00e3\u00bf\u001fS#\u00c6\u00da\u009f\u00c6\u00fel+\u000e\u00ef\u0000\u00f0\u00f8\u00c8\u0013\u00dc\u000f: B\u0096C\u00cc+\u00846\u0007V\u009d+,y\u0088`\u008c\u00a0\u009e)\u00cbK\u00c4\u0003\u00c3//g\u009b\u0018U\u00c0\u0091 r{$\u0099\u0002\u0099\u00a0\u008c\u00dcf\u00f5\u00d7\u00cekv\u00ce\u0012l\u00dbX>\u00d4V\u00d6\u00be*t X\u007f \u00f9";
                    var13_6 = 90;
                    var10_7 = 24;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = NoInteract.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u0089\u0017F9A\u000b\bJ\u00ff\u00ac\n\u0016\rej\u00bcS\u009d\u00aa\u00df\u00d2\u00cd\u00fa\u00dfE\u00d5G\u00b3E\u00a6\u001e\u00e7\u0018K\u0015I&q\u00d5>\u009d\u00a5K\u0018\u0080H\u00d8J-\u0007\u0004\u008b\u00a7\u00e8y\u0011?";
                        var13_6 = 57;
                        var10_7 = 32;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = NoInteract.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            NoInteract.b = var14_3;
            NoInteract.c = new String[5];
            var2_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var16 >>> 56);
            for (var3_11 = 1; var3_11 < 8; ++var3_11) {
                v9 = v9;
                v9[var3_11] = (byte)(var16 << var3_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var2_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var6_13 = var2_10.doFinal(new byte[]{123, -76, 97, -27, -3, -115, -85, 51});
        ** while (true)
        var0_12 = ((long)var6_13[0] & 255L) << 56 | ((long)var6_13[1] & 255L) << 48 | ((long)var6_13[2] & 255L) << 40 | ((long)var6_13[3] & 255L) << 32 | ((long)var6_13[4] & 255L) << 24 | ((long)var6_13[5] & 255L) << 16 | ((long)var6_13[6] & 255L) << 8 | (long)var6_13[7] & 255L;
        var22_14 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(NoInteract.class, "allBlocks", "getAllBlocks()Z", 0))};
        NoInteract.delegatedProperties = var22_14;
        NoInteract.INSTANCE = new NoInteract();
        NoInteract.allBlocks$delegate = SettingFactory.booleanSetting$default((SettingContainer)NoInteract.INSTANCE, "all-blocks", false, null, null, (int)var0_12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7935;
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
                throw new RuntimeException("su/catlean/ei", exception);
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
            NoInteract.c[n2] = NoInteract.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = NoInteract.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ei" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(NoInteract.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

