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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.GrassBlock;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Category;
import su.catlean.module.RotatingModule;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.module.attack.AutoSweetBerries$EntriesMappings;
import su.catlean.module.attack.AutoSweetBerriesTargetMode;
import su.catlean.setting.BooleanSetting;
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

public final class AutoSweetBerries
extends RotatingModule {
    @NotNull
    public static final AutoSweetBerries INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting targetMode$delegate;
    @NotNull
    private static final BooleanSetting directions$delegate;
    private static final long c;
    private static final String[] h;
    private static final String[] i;
    private static final Map l;
    private static final long[] m;
    private static final Integer[] n;
    private static final Map t;

    private AutoSweetBerries() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 12;
        boolean bl = false;
        int n2 = 0;
        Category category = Categories.getAttack();
        super("auto-sweet-berries", category, n2, bl, n, defaultConstructorMarker);
    }

    private final AutoSweetBerriesTargetMode getTargetMode() {
        return (AutoSweetBerriesTargetMode)((Object)targetMode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final void setTargetMode(AutoSweetBerriesTargetMode autoSweetBerriesTargetMode) {
        targetMode$delegate.setValue(this, delegatedProperties[0], (Object)autoSweetBerriesTargetMode);
    }

    private final boolean getDirections() {
        return (Boolean)directions$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setDirections(boolean bl) {
        directions$delegate.setValue(this, delegatedProperties[1], bl);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block17: {
            boolean bl;
            int n;
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
                                        long l3 = l2 = c ^ 0x221500C0EFDFL;
                                        long l4 = l3 ^ 0x5748FC059EC1L;
                                        int n2 = (int)(l4 >>> 48);
                                        int n3 = (int)(l4 << 16 >>> 32);
                                        int n4 = (int)(l4 << 48 >>> 48);
                                        long l5 = l3 ^ 0x45F8C0EA662DL;
                                        l = l5 >>> 16;
                                        n = (int)(l5 << 48 >>> 48);
                                        playerEntity2 = TargetUtil.Y((char)n2, TargetUtil.INSTANCE, this.getRange(), AutoCrystalTargetSortMode.FOV, false, false, 12, n3, (char)n4, null);
                                        bl = AutoSweetBerriesTargetMode.n();
                                        if (this.getTargetMode() != AutoSweetBerriesTargetMode.AUTO) break block10;
                                        bl2 = Aura.INSTANCE.getEnabled();
                                        if (bl) break block11;
                                        if (!bl2) break block12;
                                        entity = Aura.INSTANCE.getTarget();
                                        if (bl) break block13;
                                        if (entity == null) break block12;
                                        entity = Aura.INSTANCE.getTarget();
                                    }
                                    bl2 = entity instanceof PlayerEntity;
                                    if (bl) break block11;
                                    if (bl2) {
                                        Entity entity2 = Aura.INSTANCE.getTarget();
                                        Intrinsics.checkNotNull(entity2, "null cannot be cast to non-null type net.minecraft.world.entity.player.Player");
                                        playerEntity2 = (PlayerEntity)entity2;
                                    }
                                }
                                autoCrystal = AutoCrystal.INSTANCE;
                                if (bl) break block14;
                                bl2 = autoCrystal.getEnabled();
                            }
                            if (!bl2) break block15;
                            autoCrystal = AutoCrystal.INSTANCE;
                        }
                        playerEntity = autoCrystal.getTarget();
                        if (bl) break block16;
                        if (playerEntity != null) {
                            PlayerEntity playerEntity3 = AutoCrystal.INSTANCE.getTarget();
                            Intrinsics.checkNotNull(playerEntity3);
                            playerEntity2 = playerEntity3;
                        }
                    }
                    playerEntity = playerEntity2;
                }
                if (playerEntity == null || PingPredictor.INSTANCE.isInWeb(playerEntity2)) {
                    return;
                }
                this.placeBerriesAt(l, playerEntity2, (short)n);
                if (!bl) break block17;
            }
            for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
                block22: {
                    block19: {
                        int n5;
                        block21: {
                            block20: {
                                block18: {
                                    n5 = Intrinsics.areEqual(abstractClientPlayerEntity, Globals.getMc().player);
                                    if (bl) break block18;
                                    if (n5 != 0) break block19;
                                    Intrinsics.checkNotNull(abstractClientPlayerEntity);
                                    n5 = Friends.getIsHomie(abstractClientPlayerEntity) ? 1 : 0;
                                }
                                if (bl) break block20;
                                if (n5 != 0) break block19;
                                ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                                Intrinsics.checkNotNull(clientPlayerEntity);
                                float f = abstractClientPlayerEntity.distanceTo(clientPlayerEntity) - this.getRange();
                                n5 = f == 0.0f ? 0 : (f > 0.0f ? 1 : -1);
                            }
                            if (bl) break block21;
                            if (n5 > 0) break block19;
                            n5 = PingPredictor.INSTANCE.isInWeb(abstractClientPlayerEntity) ? 1 : 0;
                        }
                        if (n5 == 0) break block22;
                    }
                    if (!bl) continue;
                }
                this.placeBerriesAt(l, abstractClientPlayerEntity, (short)n);
                if (!bl) continue;
            }
        }
    }

    private final void placeBerriesAt(long l, PlayerEntity playerEntity, short s) {
        Entity entity;
        long l2;
        long l3 = l2 = (l << 16 | (long)s << 48 >>> 48) ^ c;
        long l4 = l3 ^ 0x386F74BB809AL;
        long l5 = l3 ^ 0x178046BCCFCFL;
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = BlockPos.ofFloored(InterpolationTracker.INSTANCE.predictPosition(playerEntity, PingPredictor.INSTANCE.getPredictionTicks(playerEntity, l4)));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        BlockState blockState = Globals.getWorld().getBlockState(blockPos2);
        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
        BlockState blockState2 = blockState;
        BlockState blockState3 = Globals.getWorld().getBlockState(blockPos2.down());
        Intrinsics.checkNotNullExpressionValue(blockState3, "getBlockState(...)");
        BlockState blockState4 = blockState3;
        if ((blockState4.getBlock() instanceof GrassBlock || Intrinsics.areEqual(blockState4.getBlock(), Blocks.DIRT)) && !(blockState2.getBlock() instanceof FluidBlock)) {
            ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
            Intrinsics.checkNotNull(clientPlayerEntity);
            entity = clientPlayerEntity;
            if (!Intrinsics.areEqual(blockPos2, BlockPos.ofFloored(this.getPos(entity)))) {
                arrayList.add(blockPos2);
            }
        }
        if (this.getDirections()) {
            Object object3;
            Object object2 = object3 = (Iterable)AutoSweetBerries$EntriesMappings.enumEntries;
            Collection collection = new ArrayList();
            Iterator iterator2 = object2.iterator();
            while (iterator2.hasNext()) {
                Object t = iterator2.next();
                Direction direction = (Direction)t;
                if (!(direction != Direction.UP && direction != Direction.DOWN)) continue;
                collection.add(t);
            }
            for (Object object3 : (List)collection) {
                BlockPos blockPos3;
                Intrinsics.checkNotNullExpressionValue(blockPos2.offset((Direction)object3), "relative(...)");
                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos3), "getBlockState(...)");
                Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos3.down()), "getBlockState(...)");
                if (!(((AbstractBlock.AbstractBlockState)((Object)collection)).getBlock() instanceof GrassBlock) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)((Object)collection)).getBlock(), Blocks.DIRT) || ((AbstractBlock.AbstractBlockState)object2).getBlock() instanceof FluidBlock) continue;
                ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
                Intrinsics.checkNotNull(clientPlayerEntity);
                entity = clientPlayerEntity;
                if (Intrinsics.areEqual(blockPos3, BlockPos.ofFloored(this.getPos(entity)))) continue;
                arrayList.add(blockPos3);
            }
        }
        RotatingModule.s(this, arrayList, false, false, null, false, 30, l5, null);
    }

    @Override
    @NotNull
    protected List getBlocks(long l) {
        return CollectionsKt.listOf(Blocks.SWEET_BERRY_BUSH);
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
        block16: {
            block15: {
                block14: {
                    block13: {
                        AutoSweetBerries.c = ZkmKeys.a(-1253310128162134313L, -5888801338452362260L, MethodHandles.lookup().lookupClass()).a(252527122700999L);
                        var20 = AutoSweetBerries.c ^ 128580885572689L;
                        AutoSweetBerries.l = new HashMap<K, V>(13);
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
                        var18_3 = new String[12];
                        var16_4 = 0;
                        var15_5 = "\u00f8Ek?\u00bc,J\u007f/\u00e1g\u009a\u00f4\u0004\u00a8{\u00e4\u0090\u00bd\u0013x5\b\u00d5(\u0087e6\u00cf\u008e.\fmYh\u0010z'\u0099S\u00faT\u00eb + \u00c3!\u00bb\u00d2\u0095\u00cc\u00fc\u00b6\u00da{\u00b6\u0081\u009fo\u00bd\u00fd\u0004\u0004x *\u00b8\u009ep\u0084\u00bf\u00f7\u0011_KE\u00cd\bFr(-\u00e4D?L%\n\u00c7\u00dfXh\u00bfFh\u00cd\u0011\u0080\u00af@\u00176\u00abO\u009f{2\u00a3\u00bd\u0090\u009dayPok\u00b6\u00d5<\u00b1\u00c8\u0083\u00c5IL)\u008aUZa\u00ad\u0094}z\u001d\u00ca\u00f1/\u00e69]8\u00ee\u00bdr\u00c6\u00ba\u001a\u00ee\u009a\u001e\u00de\u00d9\u009d\u00bb\u00bc\u0085\u00b12\u00f0J(\u00a6UG\u00be\u00d8QO\u0095\u00d5\u0011\u0016t\u0006\u00bf,\u0003;\u00adt\u00e3\u00canF\u00a4+\u00c0\u001d_\u00c3\u009e\u00b9\u0099N\u00d3\u00c7\u0003$\f\u0095i\u00f7\u00b6\u0012\u001a\u00ba\u00a7\u009c\u0018\u00f9\u008b\u0012x\u00c6W\u00b9\u00ad\u001e\u0003'\u0010\u00a7\u001b\u00d0\u009e0\u0089,\u00d1\f>\u00f4\u00a0\u009e\u00dfBK\u00b2\u0001\u008f\u0087\u0013L\u00e4B\u00fe\u00f0BO\u00da\u00b3\u008b\u001e\u008f\u0080\u00f1\u008f\u00c5\u00a4\u00fd\u0092\u0096\u00ed\u00f6F\u00d0\u0014\u00e5\u00ff`Fv\u0000m(\u00a9\u00a2\u0098\u00b3I\u00ad\u00fbaM\u009a3\u0090\u000b\u009c3\u00dcZ\u00d4y\u00f1*T\f\u0087\u0081\u00f9\u00151\u00a7U\u00beT(y\u00b3:G`\u00f7- n\u001bPc\u0013\u0005\u00d9!\u000e=Rt\u0007C.\u00dc\u00e1\u00f2\u00bb\u000b0VM\u009b\u008b\u00f5W9\u0006\"\u0019%(U\u00c3\u00cd)\u0013<\u00c8q2\u00fd\u00c5\u00e7\u00d4\u001fO\u00bc\u00d9u!\u0006\u0080W\u001c\u0006\u00dcK\u00a7\u00f6l#9\u00b3\u0016T \u00d8/\u00db\u008a\u0003 \u00d9\u008b]\u0010\u00beX\u00e9*\u00e1\u00b3n\u00e3Q\\9\u0089\u00e9\u0099\u001a\u0092\u00b4^\u00ef\u00e2\u000b\u00a8\u00ea\u0091o\u00fbN\u0096 \u00d8\u00fc2\u00eb\u00b1\u00e6}\u0083\u00b1==\u000e\nn\u0004*\u00e4\u00a3\u00b5j\u0010\u0091\u0012!_\u00a49B\u0019Q\u001fo";
                        var17_6 = 457;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = AutoSweetBerries.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "J4n\u0083\u0002\u0081\u00aa\u00a8\u00a9\u0003\u00ee\u00d5x\u00b57^[g\u00fc\u00b5X\u00b0\u00c3\u00f3\u00af]\u00ae\u0098h\u00d6\u00da\u0098\u00b0\u0013\u00184s+\u000eb w\u0016\u00fanF\u00cf\u00cd\u00dc\u00aa\u008b\u00f7\u0082M\u00afCR\u0012_Z++\u00f3\u00f7\u00bd\u00d6q\u00fa\u000fY+\u009e\u00e4";
                            var17_6 = 73;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = AutoSweetBerries.zkmDecodeUtf8(var19_9).intern();
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
                AutoSweetBerries.h = var18_3;
                AutoSweetBerries.i = new String[12];
                AutoSweetBerries.t = new HashMap<K, V>(13);
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
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u00bd\u00a0\u009dp\u0014\u00d4\u00c6N\"\u00ba~rJ\u009b\u00e5;\u00a2\u00faD0\u00b4\u00af\u0002\u00f3";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        AutoSweetBerries.m = var6_12;
        AutoSweetBerries.n = new Integer[3];
        var29_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoSweetBerries.class, "targetMode", "getTargetMode()Lsu/catlean/br;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(AutoSweetBerries.class, "directions", "getDirections()Z", 0))};
        AutoSweetBerries.delegatedProperties = var29_20;
        AutoSweetBerries.INSTANCE = new AutoSweetBerries();
        AutoSweetBerries.targetMode$delegate = SettingFactory.modeSetting$default((SettingContainer)AutoSweetBerries.INSTANCE, "target-mode", (Enum)AutoSweetBerriesTargetMode.AUTO, null, null, 12, null);
        AutoSweetBerries.directions$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoSweetBerries.INSTANCE, "directions", false, null, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7C8;
        if (i[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])AutoSweetBerries.l.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    AutoSweetBerries.l.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_y", exception);
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
            AutoSweetBerries.i[n2] = AutoSweetBerries.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return i[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoSweetBerries.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_y" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2198;
        if (AutoSweetBerries.n[n2] == null) {
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
                throw new RuntimeException("su/catlean/_y", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoSweetBerries.n[n2] = n3;
        }
        return AutoSweetBerries.n[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoSweetBerries.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_y" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoSweetBerries.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoSweetBerries.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

