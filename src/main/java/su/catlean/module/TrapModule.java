/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module;

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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.Category;
import su.catlean.module.RotatingModule;
import su.catlean.module.TrapModule$1;
import su.catlean.module.TrapModule$2;
import su.catlean.module.TrapModule$EntriesMappings;
import su.catlean.setting.BlockListSetting;
import su.catlean.setting.BlockListValue;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingParent;
import su.catlean.util.EntityCheck;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.combat.DamageUtil;
import su.catlean.zkm.ZkmKeys;

public class TrapModule
extends RotatingModule {
    static final KProperty[] delegatedProperties;
    @NotNull
    private final BlockListSetting selectedBlocks$delegate;
    @NotNull
    private final BooleanSetting onlyStanding$delegate;
    private static String W;
    private static final long h;
    private static final String[] t;
    private static final String[] w;
    private static final Map y;
    private static final long[] B;
    private static final Integer[] F;
    private static final Map I;

    public TrapModule(@NotNull String string, @NotNull Category category) {
        Intrinsics.checkNotNullParameter(string, "id");
        Intrinsics.checkNotNullParameter(category, "category");
        super(string, category, 131, true);
        SettingContainer settingContainer = this;
        Block[] blockArray = new Block[1];
        Intrinsics.checkNotNullExpressionValue(Blocks.OBSIDIAN, "OBSIDIAN");
        Object var11_4 = null;
        Function0 function0 = null;
        SettingParent settingParent = null;
        BlockListValue blockListValue = new BlockListValue(CollectionsKt.mutableListOf(blockArray));
        this.selectedBlocks$delegate = SettingFactory.blockListSetting(settingContainer, "blocks", blockListValue, settingParent, function0, var11_4);
        this.onlyStanding$delegate = SettingFactory.booleanSetting$default((SettingContainer)this, "only-standing", true, null, null, 12, null);
    }

    private final BlockListValue getSelectedBlocks() {
        return (BlockListValue)this.selectedBlocks$delegate.getValue(this, delegatedProperties[0]);
    }

    private final void setSelectedBlocks(BlockListValue blockListValue) {
        this.selectedBlocks$delegate.setValue(this, delegatedProperties[0], blockListValue);
    }

    public final boolean getOnlyStanding() {
        return (Boolean)this.onlyStanding$delegate.getValue(this, delegatedProperties[1]);
    }

    public final void trapPlayer(int n, @Nullable PlayerEntity playerEntity, int n2) {
        long l;
        long l2 = l = ((long)n << 32 | (long)n2 << 32 >>> 32) ^ h;
        long l3 = l2 ^ 0x7E84A41EB750L;
        long l4 = l2 ^ 0x270F74D6D171L;
        if (Intrinsics.areEqual(playerEntity, Globals.getPlayer()) && Globals.getPlayer().isInSwimmingPose() && this.getOnlyStanding()) {
            return;
        }
        if (playerEntity != null) {
            List list;
            double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, 0.5f, true, 0.0f, 0.0f, 0.0f, 28, null);
            double d = playerEntity.getBoundingBox().minX;
            double d2 = playerEntity.getBoundingBox().minY;
            double d3 = playerEntity.getBoundingBox().minZ;
            double d4 = playerEntity.getBoundingBox().maxX;
            double d5 = playerEntity.getBoundingBox().maxY;
            double d6 = playerEntity.getBoundingBox().maxZ;
            if (!playerEntity.isOnGround()) {
                playerEntity.setBoundingBox(new Box(d, d2, d3, d4, d5 - 0.5, d6));
            }
            BlockPos blockPos = BlockPos.ofFloored(this.getPos(playerEntity));
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            List list2 = CollectionsKt.toList(this.getTrapPositions(blockPos, l3, new LinkedHashSet()));
            if (Intrinsics.areEqual(playerEntity, Globals.getPlayer())) {
                var35_15 = list2;
                list = CollectionsKt.sortedWith(var35_15, new TrapModule$1(dArray));
            } else {
                var35_15 = list2;
                list = CollectionsKt.sortedWith(var35_15, new TrapModule$2());
            }
            list2 = list;
            RotatingModule.s(this, list2, false, false, null, false, 30, l4, null);
            if (!playerEntity.isOnGround()) {
                playerEntity.setBoundingBox(new Box(d, d2, d3, d4, d5, d6));
            }
        }
    }

    @Override
    protected boolean shouldSkipEntity(@NotNull Entity entity) {
        Intrinsics.checkNotNullParameter(entity, "crystal");
        return DamageUtil.getExplosionDamage$default(DamageUtil.INSTANCE, this.getPos(entity), Globals.getPlayer(), 0, false, null, false, 60, null) + 4.0f < Globals.getPlayer().getHealth() + Globals.getPlayer().getAbsorptionAmount();
    }

    private final List getTrapPositions(BlockPos blockPos, long l, Set set) {
        long l2 = l = h ^ l;
        long l3 = l2 ^ 0x30457C346D39L;
        long l4 = l2 ^ 0x15B1F94D9824L;
        List list = new ArrayList();
        block0: for (Direction direction : TrapModule$EntriesMappings.enumEntries) {
            BlockState blockState;
            BlockPos blockPos2;
            Intrinsics.checkNotNullExpressionValue(blockPos.offset(direction), "relative(...)");
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos2), "getBlockState(...)");
            if (set.contains(blockPos2) || !blockState.isReplaceable() && !blockState.getCollisionShape(Globals.getWorld(), blockPos2).isEmpty()) continue;
            set.add(blockPos2);
            if (this.isBlockedByEntity(blockPos2)) {
                list.addAll(this.getTrapPositions(blockPos2, l3, set));
                continue;
            }
            BlockHitResult blockHitResult = PlaceManager.INSTANCE.calcPlaceData(blockPos2, l4, -1, this.getInteract(), EntityCheck.ONLY_CRYSTALS, this.getRange(), this.getWallRange(), list);
            if (blockHitResult == null) {
                Object object3;
                Object object2 = object3 = (Iterable)TrapModule$EntriesMappings.enumEntries;
                Collection collection = new ArrayList();
                Iterator iterator2 = object2.iterator();
                while (iterator2.hasNext()) {
                    Object t = iterator2.next();
                    Direction direction2 = (Direction)t;
                    if (!(direction2 != Direction.UP)) continue;
                    collection.add(t);
                }
                for (Object object3 : (List)collection) {
                    BlockPos blockPos3;
                    Intrinsics.checkNotNullExpressionValue(blockPos2.offset((Direction)object3), "relative(...)");
                    if (set.contains(blockPos3) || !Globals.getWorld().getBlockState(blockPos3).isReplaceable()) continue;
                    set.add(blockPos3);
                    object2 = PlaceManager.INSTANCE.calcPlaceData(blockPos3, l4, -1, this.getInteract(), EntityCheck.ONLY_CRYSTALS, this.getRange(), this.getWallRange(), list);
                    if (object2 == null) continue;
                    list.add(blockPos3);
                    list.add(blockPos2);
                    continue block0;
                }
                continue;
            }
            list.add(blockPos2);
        }
        return list;
    }

    @Override
    @NotNull
    protected List getBlocks(long l) {
        return this.getSelectedBlocks().getBlocks();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        TrapModule.h = ZkmKeys.a(-8127311394969466936L, 6317689863304216677L, MethodHandles.lookup().lookupClass()).a(182394309137271L);
                        var20 = TrapModule.h ^ 96205042373378L;
                        TrapModule.y = new HashMap<K, V>(13);
                        TrapModule.zkmSetFlag("dqgH3");
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
                        var18_3 = new String[14];
                        var16_4 = 0;
                        var15_5 = "\u00f8\u00bc7HC\u00c6N\u00c2\u00b3\u0006(\u00b6\u00d4NK\u00b9\u00c1'Y\u0093\u000es`S MY\u00c4+\u0098o\u00c0\u00efx\u00ca\u0080\u00c6(<\u008a\u0087\u0019X\u00a2\u00be\u001d\u00cd\u00c0\u00be\u00f7\u00cf<\u00cdF\u00d4\u00b5\u000e\u0018\u0099 \u007f\u00bc\u00ee\u008c\u00dc\u0011t\u00a85)\u0091K7p\u00edg's\u00d8}\u00ca\u009b \u00de2\u00f2\u00bd\"%\u00c9\\l\u00f1\u00ff\u009f\u0091G[\u0011 \u00b0\u00c8_\u00baJ\u00b8B1W\u00c1\u00886\u00c2\u0000\u00ea\u0018\u008a\u00e0\u00df!Y\u00a8uF\u00d5\u00a3ExB\u00da\u00fbGl>\n\u0080\u00df\u0002\u00e1Z \u0002\u00b7\u0012\u00e8&\u000b\u00c2v`\u001f?\u00d4|q~\u009f\u00b4\u008c\u00f6K\u0087\u00f1K\u00c5H\u0000S\u00d4t\u00f5\u008a\u0090 W\u0082\u00d1\u00c5S\u00dc\u0086[\u00ec\u00f4\u00b9\u00f1\u0014\u00bf^\b\u0019\u00fdL\u00b8\u00d5G\u008bey[\u0012\u00ad\u0085\u00c4hQ\u0010\u0012\u00c4=$t\u00e0\u009e\u00a4Z[\u001c\u0018\u0094\u00f56\u008d\u0010\u00beB\u0013\u00da\u00f04,~\u00cb\u00d5*T(\u009e\u00eb\u00f5(I\u001e\u00f5\u00e1\u00bd\u0019\u00a4O=\u00f3\u00ec\u008b\u00ac\u00d3\u00d7Ciz<\u00b3\u00a3\u00c4\u00a9\u0010\u00f9\u00fb\u00dc\u00f5\u00dc2\u00d0\u00c2\u0093\u00b8\u00fap\u00dd~\u00c6w\u0010\u00a3p\r\u00d7\u00dc\u00de\u00d9*\u00f2\u00cd\f\u0098\u00a7\u008c5\u00cb\u0018\u00a6ir\u00c6'~\u00ec\u00ae\u00d4\u00a6\u0080b\u00d7m\u0094\u00e8J\u00f1\u00d4\u00d1\u00d4\u00aa M";
                        var17_6 = 323;
                        var14_7 = 24;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = TrapModule.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u009c\u00e0\u0090z\u00cb\u000e\u0089=\u00b9\u0087\u0082RD\u00a2\b.\u00dd\u0086\u00c7\u00c0.\u008c\u001f]\u00e6x\u0002\u0085t>\u00f8\u00c9~\u008e\u00b4\u00d0\u000b\u008c\u0013\u00a2^bM\u0097\u00e6\u0016\u00a5YBA\u0014,\u00e4\u00b8Y\u00a6/\u00fe\u0081:\u00b9\u00ed\u00c8\u00c00\u00bf\u0096\u00d7\u0007M\b\u00f9}\u00f2o\u00cf\u00daA\u00a6@\u00c2\u00c6\u001fz\u00ff\u00eb\u00a2\u00a7\u0016\u00e1\u00ec9W\u00e1\u00f1Q\u00cbj\nh+\u00ce\u008bf\u0098x\u0012`5\u00f5\u00d3Io";
                            var17_6 = 113;
                            var14_7 = 64;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = TrapModule.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                TrapModule.t = var18_3;
                TrapModule.w = new String[14];
                TrapModule.I = new HashMap<K, V>(13);
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
                var6_12 = new long[6];
                var3_13 = 0;
                var4_14 = "\u0007IBvm)\nxT\u0014;\u00e6\u00a1\u0013)5)o+^\u00f0f\u001a\u00e9\u00b1\u00cc\u00b0\u00f35:7\u00ed";
                var5_15 = 32;
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "f\u00fbo\u00ec_\u001f\u00e0\u00bd\u00d5\u00ees\u00fas\u00bb\u009f\u00d5";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        TrapModule.B = var6_12;
        TrapModule.F = new Integer[6];
        var22_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(TrapModule.class, "selectedBlocks", "getSelectedBlocks()Lsu/catlean/dg;", 0)), Reflection.property1(new PropertyReference1Impl(TrapModule.class, "onlyStanding", "getOnlyStanding()Z", 0))};
        TrapModule.delegatedProperties = var22_20;
    }

    public static void zkmSetFlag(String string) {
        W = string;
    }

    public static String java_lang_String_zkmGetFlag() {
        return W;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x615D;
        if (w[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])y.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    y.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_d", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = t[n2].getBytes("ISO-8859-1");
            TrapModule.w[n2] = TrapModule.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return w[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TrapModule.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_d" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x676B;
        if (F[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = B[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])I.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    I.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_d", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TrapModule.F[n2] = n3;
        }
        return F[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TrapModule.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_d" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TrapModule.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TrapModule.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

