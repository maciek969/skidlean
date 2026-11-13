/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

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
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.TripwireBlock;
import net.minecraft.block.TripwireHookBlock;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.CollisionEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Avoid
extends Module {
    @NotNull
    public static final Avoid INSTANCE;
    static final /* synthetic */ KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting unloaded$delegate;
    @NotNull
    private static final BooleanSetting voidAir$delegate;
    @NotNull
    private static final BooleanSetting cactus$delegate;
    @NotNull
    private static final BooleanSetting fire$delegate;
    @NotNull
    private static final BooleanSetting berryBush$delegate;
    @NotNull
    private static final BooleanSetting powderSnow$delegate;
    @NotNull
    private static final BooleanSetting lava$delegate;
    @NotNull
    private static final BooleanSetting trapString$delegate;
    @NotNull
    private static final BooleanSetting plate$delegate;
    @NotNull
    private static final BooleanSetting turtleEggs$delegate;
    private static final long c;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] j;
    private static final Map k;

    private Avoid() {
        super("avoid", Categories.getBasic(), null, null);
    }

    private final boolean getUnloaded() {
        return (Boolean)unloaded$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getVoidAir() {
        return (Boolean)voidAir$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getCactus() {
        return (Boolean)cactus$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getFire() {
        return (Boolean)fire$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getBerryBush() {
        return (Boolean)berryBush$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getPowderSnow() {
        return (Boolean)powderSnow$delegate.getValue(this, delegatedProperties[5]);
    }

    private final boolean getLava() {
        return (Boolean)lava$delegate.getValue(this, delegatedProperties[6]);
    }

    private final boolean getTrapString() {
        return (Boolean)trapString$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getPlate() {
        return (Boolean)plate$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getTurtleEggs() {
        return (Boolean)turtleEggs$delegate.getValue(this, delegatedProperties[9]);
    }

    @Flow
    private final void onCollision(CollisionEvent collisionEvent) {
        block0: {
            boolean bl;
            BlockState blockState = collisionEvent.getState();
            Entity entity = Globals.getPlayer();
            Entity entity2 = Globals.getPlayer();
            boolean bl2 = !Globals.getWorld().isChunkLoaded((int)INSTANCE.getPos(entity).getX() >> 4, (int)INSTANCE.getPos(entity2).getZ() >> 4) && INSTANCE.getUnloaded();
            boolean bl3 = collisionEvent.getPos().getY() < Globals.getWorld().getBottomY() && INSTANCE.getVoidAir();
            boolean bl4 = Intrinsics.areEqual(blockState.getBlock(), Blocks.CACTUS) && INSTANCE.getCactus();
            boolean bl5 = (Intrinsics.areEqual(blockState.getBlock(), Blocks.FIRE) || Intrinsics.areEqual(blockState.getBlock(), Blocks.SOUL_FIRE)) && INSTANCE.getFire();
            boolean bl6 = Intrinsics.areEqual(blockState.getBlock(), Blocks.SWEET_BERRY_BUSH) && INSTANCE.getBerryBush();
            boolean bl7 = Intrinsics.areEqual(blockState.getBlock(), Blocks.POWDER_SNOW) && INSTANCE.getPowderSnow();
            boolean bl8 = Intrinsics.areEqual(blockState.getBlock(), Blocks.LAVA) && INSTANCE.getLava();
            boolean bl9 = (blockState.getBlock() instanceof PressurePlateBlock || Intrinsics.areEqual(blockState.getBlock(), Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE) || Intrinsics.areEqual(blockState.getBlock(), Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)) && INSTANCE.getPlate();
            boolean bl10 = blockState.getBlock() instanceof TripwireBlock && (Boolean)blockState.get(TripwireHookBlock.ATTACHED) != false && INSTANCE.getTrapString();
            boolean bl11 = bl = blockState.getBlock() instanceof TurtleEggBlock && INSTANCE.getTurtleEggs();
            if (!bl2 && !bl5 && !bl4 && !bl8 && !bl6 && !bl7 && !bl9 && !bl10 && !bl3 && !bl) break block0;
            BlockState blockState2 = Blocks.DIRT.getDefaultState();
            Intrinsics.checkNotNullExpressionValue(blockState2, "defaultBlockState(...)");
            collisionEvent.setState(blockState2);
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
                        Avoid.c = ZkmKeys.a(6327424761388380338L, 1684494716744056318L, MethodHandles.lookup().lookupClass()).a(183769510647313L);
                        var20 = Avoid.c ^ 5687310803547L;
                        Avoid.f = new HashMap<K, V>(13);
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
                        var15_5 = "\u008b\u000fm\u00fc\u00c4\u009d&\u00e9\u00c6\u00b2P$Q\u000b`/^\u0015\u0086P /\u0017,y\u00b5\u00a4\u00a0\u00f1\u009c\u0017Mv/\u0096h\u0006\u00ab\u0015M\u0010\u00a9\u0092B\u0087\u00ac\u0094\u0014\u00b2\u00bb\u00fb \u00f0\u001bM\u0091\u00f1(\u00cbXn\u00bc\u009bD\u0084\u0018\u00c3\u00e5qR\u00d1y\u00c34\u0089?\u00eb4\u00fd\u0002\u00f0H\u0017\u00ed\u0014\u00dfo\u00c2/\u009f\u0004\t\u0016\u008d\b\r\u0014\u00ba\u0010\u0001!b\u00b4\u00f7o\u0001\u00dd\u00e3V\u0095\u00b2\u008b\u0083\u00e4\u0006 h\u0018\u00ce\u00b4\u00a0\u0001\u00b8\u00ca\u001f\u001b\u00e1{%\u00841\u0015\u00c3\u00e2\\;!\u00f3\u009a\u00e8P3\u00f2\u0091D\u00a7<\u0082\u0010\u00a4\u00ffB\u00aa\u00ab\u008ecN\u009e1_3\f\u00fc\u0084M\u0010\u00f9\u0087\u0094+\u00a4\u00c2\u00a3\u00bf\u0002\u00f9\u0096\u000e\u0096u\u00142 \u00a2\u0018\n;U#&I8\u00c4\u00f7\u000b\u001d\u00af\u0005\u0088N\u00ed\u00eb\u00ba\u00b8\u00a8\u0015u\u00f2W\u0091Rj\u00c00b \u0005\u00dcT\u00ac\u00c0\u0007\u00b4y@\u0096\u00fa\u001a\u00b6\u00ce\u00ac5\u00ca?z\u008e\u00e7\u00c7\u00f0&\u0000x\u0088\u0098XA~\u008a ^\u00cd\u0004\u0091\u00bd\u00d0E'\u009b\u00eaF\u0014\u0014\u001e\u00bfn\u00fcK\u0085\u00e0\u00de\u00eb\u00c0\u00edQ6\u00af\u001c5^\u0001+\u0010;\u0097\u009b\u0080\u00d9\u00e3\u00bf^g\u00ff\u0097\u00dd\u00b4]\u0094\u0096 CP\u00d0\u00f0\u00a6\u001d\u00f8C\u00e7\u0011\t\u00864q\u0014w\u0092\u0089w\u00a9\u0002$\u00c1\u00b1\u00ed\u00a0;\u00ba\u00976\u00baT\u0010\u00e4\u001d\u0016\u00d5\u009co\\\u00ce\u009f\u0000}L\u0002\u00ebn\u00e8\u0010\u00bb\u00de\u00d2+\u00a4\u00fe\u00de\t3\u00f5\u009f\u0082L3/S \u0005l*\u00e2\u00a53\u001a\u009a8\u00c5\u00e5\u00b8\u00d2\u00f7\u00d1[if\u008e\u0018u\u0014^)\u00ca\u00c2hidw\u00e0F\u0018\u00fb\u00a7\u0092\t\nh,\u00afG\u000b\u0096\u0086\u00adF\u00fb|\u00ea\u009f\u00e8\u00f1\u00ac\u00a9\u001b\u00d8(\u00c3\u0001\u00b7@\u00a9Ns\u0005-\u00e4 >\u00d0P\u0097\u0005\u00bb\u00f5J\u00c4\u00dc`lv}\u00e4-\u00ccS\u0010y\u00cb\u00a2A\u0000\u00a1f\u00b7\u0006\u001c\u0018\u00a8?\u00fd\u00e7\u00feV!\u00de\u0092\u00d4\u001b\u0099\u00f7\u00f5\u001aF\u00d0ji\u00f2\u00e2:\u00b7\u00b6\u0010\u0080\u008a\u0086\u00e3\u001f\u007f)O\u001a\u00d8\u00d7&\u00db\u00f9\u00c5\u00d5(\u0092\u000fg#\u00c3\r*\u00cc-\u0091K\u00b8X\u00ac\u00cb\u007f\u00e9'\b:}\u00b4\u00fc\bj\u00f6\u00bd>*a&\u00d7\u008d\u00dd\u00c0\t\u00b5\u00ccY\u00f1\u0018\u00bb|\u00f7\u00c4\u00f4\u009a\u0087jl\"L\u0081\u00d8~+\u00b8\u008f#\u00a2\u001d\u009b\u0002\u0092\u00a9\u0010\u00b9+\u000e\u0083t\u0081\u00d4+\u00c2\u00e8q=\u008c.\u00a6Y \u00bez\u0093Wt\u0081\u00e3lZ\u00b3\u0087g\u009f\u00d8\"a\u0091,\u00857\u00a6\u0013\u00c8\u00ce\u00e6\u0017[pvh\u009c^ \u00e74*?`!\u00ab\u00dfm}\u0086=\u0080\u0092\u00b1o+/\u00af\u0002\u00d9G\u00b2\u00b2]\u000e\u00f1\u00e0\u0093KZ\u008a Qk\u0082K\u00f9*Pyy\u0081\u0000\u0099\u00e5\u000b\u00a9\u00a6\u0012z\u00b9o\u00c0\u00ff#\u0014\u0098\u009c\u00da\u00aa\u00a3Y\u00ccl\u0010\u0098b\u00ac\u00a4q\\v\u0093-\u00fc\u00da\u00f1\u00edV\u00fa3\u0018\u00d7\u0007\u001e_\u0006\u00d8E\u00147\u00e1\u0083\u0000\u00e0*\u001a\u00c3\u00c9\u0085\u00f6~(\u00ab\u00e2v V\u00c9\u00ab\u00f6\u00b06u\u00afC\u0081;7\u00c0\u00f2\u00e5\u00e9\u009e2Ez^`\u0087!\u00acp#_\fkb\u00df \u00adE~\u00b40\u008e\u00f5\u009et\u0087y\u00e77\u00d5\u00de\u00926\u0097\\9\u00a1\u00cf~\u0095/\u00b69\u000e\u00a2\u00be\u00e0\u00e2 Ht\u00ff;\u001a#\u00a4\u00d8\u0097`\u00f064jYj1\u001b\u0093D\u0091\u0086\"\u00f9\u008c\u001aX\u00c4\u00a2v\u00f0'";
                        var17_6 = 829;
                        var14_7 = 40;
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
                            var18_3[var16_4++] = Avoid.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "n\u00f3\u0014\u001fTrH\u0089\td\u00a5mb]\u00df\u00ea\u008f\u00c14\u000e\u00d4P\"\u00a4 V\u00ef\u0018+\u00c4\u00a7JQA\u00bce\u0013\u00ec\u00034\u00c0\u00ab\u0082~'\b\u0016\u00eb\u0080\u0095\u00c7\u0080k\u0086\u00af}I";
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
                            var18_3[var16_4++] = Avoid.zkmDecodeUtf8(var19_9).intern();
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
                Avoid.d = var18_3;
                Avoid.e = new String[32];
                Avoid.k = new HashMap<K, V>(13);
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
                var6_12 = new long[11];
                var3_13 = 0;
                var4_14 = "\u00cf\u0004\u00b9\u0098\u00a0)S\u009f\u00e7\u0098o\u0099\u00dc|>\u0090\u00c2\u00d7K\u00f0\u008f_y\u00d9u\u00b7~(\u000f|\u00d7\u0000\u00d7\nX\u0096\u0001\u00a8Y0\u00c6ZfQ2\u001b\u00aa\u0003H\u00f6x\u00e9\u00d9\u00b1\u00f0`Y0\u00aa\u0003h\u0019\u00bc\u001e*\u0084i\u00a1\u0080\u008f)\u00fe";
                var5_15 = 72;
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
                    var4_14 = "\u00f4\u00f9\r\u0085\u0086A\u00ee\u00a7E\"\rka\u00ae\u0011\u00b4";
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
        Avoid.g = var6_12;
        Avoid.j = new Integer[11];
        var26_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Avoid.class, "unloaded", "getUnloaded()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "voidAir", "getVoidAir()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "cactus", "getCactus()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "fire", "getFire()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "berryBush", "getBerryBush()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "powderSnow", "getPowderSnow()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "lava", "getLava()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "trapString", "getTrapString()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "plate", "getPlate()Z", 0)), Reflection.property1(new PropertyReference1Impl(Avoid.class, "turtleEggs", "getTurtleEggs()Z", 0))};
        Avoid.delegatedProperties = var26_20;
        Avoid.INSTANCE = new Avoid();
        Avoid.unloaded$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "unloaded", false, null, null, 12, null);
        Avoid.voidAir$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "void-air", false, null, null, 12, null);
        Avoid.cactus$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "cactus", false, null, null, 12, null);
        Avoid.fire$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "fire", false, null, null, 12, null);
        Avoid.berryBush$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "berry-bush", false, null, null, 12, null);
        Avoid.powderSnow$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "powder-snow", false, null, null, 12, null);
        Avoid.lava$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "lava", false, null, null, 12, null);
        Avoid.trapString$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "trap-string", false, null, null, 12, null);
        Avoid.plate$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "plate", false, null, null, 12, null);
        Avoid.turtleEggs$delegate = SettingFactory.booleanSetting$default((SettingContainer)Avoid.INSTANCE, "turtle-eggs", false, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6538;
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
                throw new RuntimeException("su/catlean/__", exception);
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
            Avoid.e[n2] = Avoid.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Avoid.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/__" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x40CD;
        if (j[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])k.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/__", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Avoid.j[n2] = n3;
        }
        return j[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Avoid.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/__" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Avoid.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Avoid.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

