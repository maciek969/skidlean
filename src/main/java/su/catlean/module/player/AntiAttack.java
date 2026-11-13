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
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Criticals;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class AntiAttack
extends Module {
    @NotNull
    public static final AntiAttack INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting friends$delegate;
    @NotNull
    private static final BooleanSetting villagers$delegate;
    @NotNull
    private static final BooleanSetting zoglins$delegate;
    private static final long c;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;

    private AntiAttack() {
        super("anti-attack", Categories.getPlayer(), null, null);
    }

    private final boolean getFriends() {
        return (Boolean)friends$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getVillagers() {
        return (Boolean)villagers$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getZoglins() {
        return (Boolean)zoglins$delegate.getValue(this, delegatedProperties[2]);
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket;
        Packet<?> packet = sendPacket.getPacket();
        Packet<?> packet2 = packet;
        if (packet2 instanceof PlayerInteractEntityC2SPacket && Criticals.INSTANCE.getInteractType(playerInteractEntityC2SPacket = (PlayerInteractEntityC2SPacket)packet2) == PlayerInteractEntityC2SPacket.InteractType.ATTACK) {
            Entity entity = Criticals.INSTANCE.getTargetEntity((PlayerInteractEntityC2SPacket)packet2);
            try {
                if (INSTANCE.getFriends() && entity instanceof PlayerEntity && Friends.getIsHomie((PlayerEntity)entity) || INSTANCE.getVillagers() && entity instanceof VillagerEntity || INSTANCE.getZoglins() && entity instanceof ZombifiedPiglinEntity && !Intrinsics.areEqual(((ZombifiedPiglinEntity)entity).getTarget(), Globals.getPlayer())) {
                    sendPacket.cancel();
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        AntiAttack.c = ZkmKeys.a(-3423907817071014292L, 6768823869000227701L, MethodHandles.lookup().lookupClass()).a(230204006281530L);
                        var20 = AntiAttack.c ^ 30100406237468L;
                        AntiAttack.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[10];
                        var16_4 = 0;
                        var15_5 = "\u00cc\u008e\u00c4\u00ce \u00d1\u008a\u0018\u0085\u009c\u00c2\u0003fI\u00eb\u00db\u0081\u00be\u0081\u00d3\u00e3\u0014*4\fk\u00cd8\u00fe!\u0001X\u0010\u00a0GE#\u00aa\u0082y;\u00e6\u00832\u00e5\u000bn\u000eq\u0010z\u00c3\u0085\u007f\u0086*\u009d\u00fb\u00c6\u00ce\u00a0\u00df\u001c\u00be^)\u0018\u00ec\u0005\u0005\u00823\u009ak\u00d8{h1\u00b0i4\u00b9\u00f2<I\u0087\u001f\u00a7\u00c9q\u00ae\u0010\u001a\u0091\u00f6\u00b9d\u00cc\u008d\u00aa\u009f\u00c7Q\u0016</\u0004\u0088 \u001d&\u0002\u00afl\u00e4T\n\u0084 A\u0097\u0015\u00d5'\u009dj\u00c8\rZ5\u0089vQ\u00e6]@,\u00fb\u0083\u00e2\u0000 \u009b\u0082}'\u00a9\u00c2\u009c?\u00ffd\u00c7\u00e6\u0018\u0093\u00f8\u00bd\u008bBly3\u001cm\u0099\u00d9\u00d8\u00ec\u0096\u00bahY) 6\u0007W\u001f,\u00a5\u00cf[\u0097}\u00ba\u0019\u00ef\u00d2\u00165\u0007Zp\u00d0\u00a8\u00ce\u00c0\u0004\u00a5\u0098\u00a0\u00dc0\u0001\u00e2\u00e2";
                        var17_6 = 207;
                        var14_7 = 32;
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
                            var18_3[var16_4++] = AntiAttack.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00f8\u008bn,\u00ea\u0001\u00f8\u00b7\u00ae\u00d8\u00c9Fk\u0000\u000b\u0082\u00ad\u00da\u00d3\u00be\u00a8\u00fb\u00e1!\u0010\u00da\u00eb\u0092\u00f5nF\u0080\u00bf/\u00b8\u00d8-\u00f7\u00bd\u00a4\u009c";
                            var17_6 = 41;
                            var14_7 = 24;
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
                            var18_3[var16_4++] = AntiAttack.zkmDecodeUtf8(var19_9).intern();
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
                AntiAttack.d = var18_3;
                AntiAttack.e = new String[10];
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
                var0_12 = new long[2];
                var4_13 = 0;
                var5_14 = "o5\u0088\u00cfL\u00b3N\u007f\u00a2\f\u00a8G\u0080\u0082\t\u00af";
                var6_15 = 16;
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
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AntiAttack.class, "friends", "getFriends()Z", 0)), Reflection.property1(new PropertyReference1Impl(AntiAttack.class, "villagers", "getVillagers()Z", 0)), Reflection.property1(new PropertyReference1Impl(AntiAttack.class, "zoglins", "getZoglins()Z", 0))};
        AntiAttack.delegatedProperties = var26_20;
        AntiAttack.INSTANCE = new AntiAttack();
        AntiAttack.friends$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiAttack.INSTANCE, "friends", true, null, null, (int)var0_12[0], null);
        AntiAttack.villagers$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiAttack.INSTANCE, "villagers", true, null, null, (int)var0_12[1], null);
        AntiAttack.zoglins$delegate = SettingFactory.booleanSetting$default((SettingContainer)AntiAttack.INSTANCE, "zoglins", true, null, null, (int)var0_12[1], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x65FD;
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
                throw new RuntimeException("su/catlean/er", exception);
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
            AntiAttack.e[n2] = AntiAttack.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AntiAttack.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/er" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AntiAttack.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

