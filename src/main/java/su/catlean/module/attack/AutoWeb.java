/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Category;
import su.catlean.module.Module;
import su.catlean.module.RotatingModule;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.module.attack.AutoWebFaceMode;
import su.catlean.module.attack.AutoWebTargetMode;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.EntityCheck;
import su.catlean.util.Friends;
import su.catlean.util.Globals;
import su.catlean.util.InterpolationTracker;
import su.catlean.util.PingPredictor;
import su.catlean.util.combat.TargetUtil;
import su.catlean.zkm.ZkmKeys;

public final class AutoWeb
extends RotatingModule {
    @NotNull
    public static final AutoWeb INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting targetMode$delegate;
    @NotNull
    private static final ModeSetting face$delegate;
    private static final long c;
    private static final String[] h;
    private static final String[] i;
    private static final Map l;
    private static final long[] m;
    private static final Integer[] o;
    private static final Map t;

    private AutoWeb() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 8;
        boolean bl = false;
        int n2 = 132;
        Category category = Categories.getAttack();
        super("auto-web", category, n2, bl, n, defaultConstructorMarker);
    }

    private final AutoWebTargetMode getTargetMode() {
        return (AutoWebTargetMode)((Object)targetMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setTargetMode(AutoWebTargetMode autoWebTargetMode) {
        targetMode$delegate.setValue(this, delegatedProperties[0], (Object)autoWebTargetMode);
    }

    private final AutoWebFaceMode getFace() {
        return (AutoWebFaceMode)((Object)face$delegate.getValue(this, delegatedProperties[1]));
    }

    private final void setFace(AutoWebFaceMode autoWebFaceMode) {
        face$delegate.setValue(this, delegatedProperties[1], (Object)autoWebFaceMode);
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        block17: {
            boolean bl;
            long l;
            block10: {
                PlayerEntity playerEntity;
                PlayerEntity playerEntity2;
                block16: {
                    block15: {
                        AutoCrystal autoCrystal;
                        block14: {
                            boolean bl2;
                            block11: {
                                block12: {
                                    Entity entity;
                                    block13: {
                                        long l2;
                                        long l3 = l2 = c ^ 0x64955A592B99L;
                                        long l4 = l3 ^ 0x1660818A96C8L;
                                        int n = (int)(l4 >>> 48);
                                        int n2 = (int)(l4 << 16 >>> 32);
                                        int n3 = (int)(l4 << 48 >>> 48);
                                        l = l3 ^ 0x7D4EA30290CAL;
                                        Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
                                        playerEntity2 = TargetUtil.Y((char)n, TargetUtil.INSTANCE, this.getRange(), AutoCrystalTargetSortMode.FOV, false, false, 12, n2, (char)n3, null);
                                        bl = AutoWebTargetMode.R();
                                        if (this.getTargetMode() != AutoWebTargetMode.AUTO) break block10;
                                        bl2 = Aura.INSTANCE.getEnabled();
                                        if (!bl) break block11;
                                        if (!bl2) break block12;
                                        entity = Aura.INSTANCE.getTarget();
                                        if (!bl) break block13;
                                        if (entity == null) break block12;
                                        entity = Aura.INSTANCE.getTarget();
                                    }
                                    bl2 = entity instanceof PlayerEntity;
                                    if (!bl) break block11;
                                    if (bl2) {
                                        Entity entity2 = Aura.INSTANCE.getTarget();
                                        Intrinsics.checkNotNull(entity2, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                                        playerEntity2 = (PlayerEntity)entity2;
                                    }
                                }
                                autoCrystal = AutoCrystal.INSTANCE;
                                if (!bl) break block14;
                                bl2 = autoCrystal.getEnabled();
                            }
                            if (!bl2) break block15;
                            autoCrystal = AutoCrystal.INSTANCE;
                        }
                        playerEntity = autoCrystal.getTarget();
                        if (!bl) break block16;
                        if (playerEntity != null) {
                            PlayerEntity playerEntity3 = AutoCrystal.INSTANCE.getTarget();
                            Intrinsics.checkNotNull(playerEntity3);
                            playerEntity2 = playerEntity3;
                        }
                    }
                    playerEntity = playerEntity2;
                }
                if (playerEntity == null || PingPredictor.INSTANCE.isStandingOn(playerEntity2, this.getFace() != AutoWebFaceMode.OFF)) {
                    return;
                }
                this.getWebPositions(l, playerEntity2);
                if (bl) break block17;
                Module.zkmSetFlag(new Module[2]);
            }
            for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
                block21: {
                    block19: {
                        int n;
                        block20: {
                            block18: {
                                n = Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getMc().player);
                                if (!bl) break block18;
                                if (n != 0) break block19;
                                Intrinsics.checkNotNull(abstractClientPlayerEntity);
                                n = Friends.getIsHomie(abstractClientPlayerEntity) ? 1 : 0;
                            }
                            if (!bl) break block20;
                            if (n != 0) break block19;
                            ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                            Intrinsics.checkNotNull(clientPlayerEntity);
                            float f = abstractClientPlayerEntity.distanceTo(clientPlayerEntity) - this.getRange();
                            n = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                        }
                        if (n <= 0 && !PingPredictor.INSTANCE.isStandingOn(abstractClientPlayerEntity, this.getFace() != AutoWebFaceMode.OFF)) break block21;
                    }
                    if (bl) continue;
                }
                this.getWebPositions(l, abstractClientPlayerEntity);
                if (bl) continue;
            }
        }
    }

    private final void getWebPositions(long l, PlayerEntity playerEntity) {
        long l2 = l = c ^ l;
        long l3 = l2 ^ 0x46594DCAB23BL;
        long l4 = l2 ^ 0x69B67FCDFD6EL;
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = BlockPos.ofFloored(InterpolationTracker.INSTANCE.predictPosition(playerEntity, PingPredictor.INSTANCE.getPredictionTicks(playerEntity, l3)));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        if (this.getFace() != AutoWebFaceMode.ONLY && !(Globals.getWorld().getBlockState(blockPos2).getBlock() instanceof FluidBlock)) {
            ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
            Intrinsics.checkNotNull(clientPlayerEntity);
            Entity entity = clientPlayerEntity;
            if (!Intrinsics.areEqual(blockPos2, BlockPos.ofFloored(this.getPos(entity)))) {
                arrayList.add(blockPos2);
            }
        }
        if (this.getFace() != AutoWebFaceMode.OFF && !(Globals.getWorld().getBlockState(blockPos2.up()).getBlock() instanceof FluidBlock)) {
            BlockPos blockPos3 = blockPos2.up();
            ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
            Intrinsics.checkNotNull(clientPlayerEntity);
            if (!Intrinsics.areEqual(blockPos3, BlockPos.ofFloored(clientPlayerEntity.getEyePos()))) {
                arrayList.add(blockPos2.up());
            }
        }
        RotatingModule.s(this, arrayList, false, false, null, false, 30, l4, null);
    }

    @Override
    @NotNull
    protected List getBlocks(long l) {
        return CollectionsKt.listOf(Blocks.COBWEB);
    }

    @Override
    @NotNull
    protected EntityCheck getEntityCheck() {
        return EntityCheck.NONE;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoWeb.c = ZkmKeys.a(-4224850375420772444L, -5886361660559173948L, MethodHandles.lookup().lookupClass()).a(71599147379572L);
                        var20 = AutoWeb.c ^ 50200778028891L;
                        AutoWeb.l = new HashMap<K, V>(13);
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
                        var18_3 = new String[9];
                        var16_4 = 0;
                        var15_5 = "|\u00ff\u00bd&\u00eb\u00cc\u009cC\t>\u0096\u00f7L\u0082\u00a1UjI\u00a24\u0093\u009e\u00e9<\u0081g\u00eb\u0099\u00c0\u00a6K\u0081)C8\u00e8\u008b\u00ec\u00c0\u00128'\u0086\u00b1\u00baK(A\u0082\u0090\u0005\u00ac~\u00b2\u00ee\u00fcb[\u0089'\u00df\u001aj1\r\u0092^\u00da\u00d6\u00e6\u00ff?'\u0089\u009d\u009aD\u00a7+\u0012\u00e8\u00ba\u00f3\u00e7\u00e6B\u00dd\u00e3s\u00c6\u0003\u0098V\u009e\b\u00ad\r \u00054KFY\u00e5+H\u00ec\u00ed\u00b4X\u00cb\u00e5x\u0085\u008c\u00de\u00df\u0093x\u00a1\u0013\u00e9\u00b8;\u0011\u00b6\"\u0091x\u00f1\u0080\u009a\u00b1\u00acA\f\u00dfi^\u00ce\u00c8\u0019P\u001b\u00a6>v\u0096m\u00c7\u0086=\u00fa*j\u00b0CZHH\u00e6\u0093\u0019\u00b0\u00e3\u00fbq\u00ec=\u00f8\u00f1\u00c7\u00abdZ\u008d]f\u00db\u00bc\u00ba=\u00ff\u0085\u0080\u00c3\u0080\u0094\u008a\u00e8\u00e8\u00eb\u00cd.~]\u00aa\u00af,\u00d5\u00e1U3\u00ab\u0013\u00f1p\u00ee\u0006A\u007fY\u000f\u0014\u008c\u00e9\u0084\u0091\u00f4\u00e7\u00a1\u00d6O\u00d1\u00b0\u00990\u00f6\u00eb\u00d4\u009ew\u00d7/P\u0081gpn><\u00a6N\u0099\n\u0081\u00bf\u00d9\u00f8\u007f\u00e3\u00ec\u0084\u00ac\u00d3 i\u00d2\t\u0010*\u00a4\u00af\u00f8\u000e\u00ceh\u00a8\u00dab\u0095\u00a0\u0012uF\u009a\u0010\u00c4-\u00fe\u00a7\u0002y\u00bf?\u0004\u00aa\u00b6\u00dcJ>\u00c3\u00c7 O\u0013\u001f\u0000\u00ddO`V\u008e3\u0089\u00c6^\u00906\u008d{.OjG\r\u001c\u00d5\u0003 \u00ac\u0006*\u00a7\u008a\u009d";
                        var17_6 = 326;
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
                            var18_3[var16_4++] = AutoWeb.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u0018\u00da,E\u00be2\u00e9\u00d9\u00b6\u008b\u00d7\u001c\u008c\u0088x5\u00b2s\u00ed\u00a3\u00f7O\u0088\u00cb\u00dc\u00cb\u0087\u00e7\u00cf%\u00fbB\u0018\u00cf=E\u0001\u007f\u00c22\u0003&\u0007=\u00bc\u00ca\u0089\u0088\u008d\u00f1C\u00b4\b\u008f\u007f\u00d7f";
                            var17_6 = 57;
                            var14_7 = 32;
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
                            var18_3[var16_4++] = AutoWeb.zkmDecodeUtf8(var19_9).intern();
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
                AutoWeb.h = var18_3;
                AutoWeb.i = new String[9];
                AutoWeb.t = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u00dc}\u00c2!\u00b6\u001b\u00ca'\u00dc\u0091\u00cf\u00f1l\u0098\u00e2$\u00e9\u0015\u0089\\\u0085\u00af\u008d\u009e";
                var5_15 = 24;
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
                    var4_14 = "\u00f6M\u0007yH\u00c6\u0095\u00cc\u00d1\u00b3_\u00a9 \u00c1\u00e7\u0084";
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
        AutoWeb.m = var6_12;
        AutoWeb.o = new Integer[5];
        var26_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWeb.class, "targetMode", "getTargetMode()Lsu/catlean/wa;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoWeb.class, "face", "getFace()Lsu/catlean/i;", 0))};
        AutoWeb.delegatedProperties = var26_20;
        AutoWeb.INSTANCE = new AutoWeb();
        AutoWeb.targetMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoWeb.INSTANCE, "target-mode", (Enum)AutoWebTargetMode.AUTO, null, null, 12, null);
        AutoWeb.face$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoWeb.INSTANCE, "face", (Enum)AutoWebFaceMode.ON, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4ED;
        if (i[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])AutoWeb.l.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    AutoWeb.l.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_u", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = h[n2].getBytes("ISO-8859-1");
            AutoWeb.i[n2] = AutoWeb.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return i[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoWeb.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_u" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4ADA;
        if (o[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = m[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])t.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    t.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_u", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoWeb.o[n2] = n3;
        }
        return o[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoWeb.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_u" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoWeb.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoWeb.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

