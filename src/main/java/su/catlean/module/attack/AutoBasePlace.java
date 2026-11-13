/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpongeBlock;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Category;
import su.catlean.module.RotatingModule;
import su.catlean.module.attack.AutoBasePlace$EntriesMappings;
import su.catlean.module.attack.AutoBasePlace$onPlayerUpdate$1;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.IntSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Async;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PlaceManager;
import su.catlean.util.TickTimer;
import su.catlean.zkm.ZkmKeys;

public final class AutoBasePlace
extends RotatingModule {
    @NotNull
    public static final AutoBasePlace INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting sponges$delegate;
    @NotNull
    private static final IntSetting spongeDelay$delegate;
    @NotNull
    private static final BooleanSetting onlyNoPositions$delegate;
    @NotNull
    private static final TickTimer i;
    @NotNull
    private static final TickTimer h;
    @Nullable
    private static BlockHitResult lastHit;
    @Nullable
    private static BlockPos targetPos;
    private static final long m;
    private static final String[] n;
    private static final String[] y;
    private static final Map I;
    private static final long[] O;
    private static final Integer[] S;
    private static final Map W;

    private AutoBasePlace() {
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 12;
        boolean bl = false;
        int n2 = 0;
        Category category = Categories.getAttack();
        super("auto-base-place", category, n2, bl, n, defaultConstructorMarker);
    }

    private final boolean getSponges() {
        return (Boolean)sponges$delegate.getValue(this, delegatedProperties[0]);
    }

    private final int getSpongeDelay() {
        return ((Number)spongeDelay$delegate.getValue(this, delegatedProperties[1])).intValue();
    }

    private final boolean getOnlyNoPositions() {
        return (Boolean)onlyNoPositions$delegate.getValue(this, delegatedProperties[2]);
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block13: {
            block16: {
                block14: {
                    block12: {
                        block15: {
                            v0 = var2_2 = AutoBasePlace.m ^ 45126901047242L;
                            var8_3 = (int)((v0 ^ 79952681383907L) >>> 48);
                            var13_4 = v0 ^ 79861641646033L;
                            var15_5 = v0 ^ 75942120910690L;
                            if (!AutoCrystal.INSTANCE.getEnabled() || AutoCrystal.INSTANCE.getTarget() == null) break block13;
                            if (!this.getSponges()) ** GOTO lbl-1000
                            var25_6 = new Block[1];
                            Intrinsics.checkNotNullExpressionValue(Blocks.SPONGE, "SPONGE");
                            if (InventoryUtil.INSTANCE.findBlockInHotbar((Block[])var25_6).isFound()) {
                                v1 = true;
                            } else lbl-1000:
                            // 2 sources

                            {
                                v1 = false;
                            }
                            var24_7 = v1;
                            var25_6 = Async.INSTANCE;
                            var26_8 = 0;
                            var25_6.getExecutor().execute(new AutoBasePlace$onPlayerUpdate$1(var26_8, var24_7));
                            if (AutoBasePlace.i.hasPassed((short)var8_3, 10)) {
                                v2 = AutoBasePlace.lastHit;
                                if (v2 != null) {
                                    var27_10 = v2;
                                    RotatingModule.s(AutoBasePlace.INSTANCE, CollectionsKt.listOf(var27_10.getBlockPos()), false, false, null, false, 30, var15_5, null);
                                    AutoBasePlace.targetPos = var27_10.getBlockPos();
                                    AutoBasePlace.i.reset();
                                }
                            }
                            if (!var24_7 || AutoBasePlace.targetPos == null || !AutoBasePlace.h.hasPassed((short)var8_3, var36_11 = this.getSpongeDelay())) break block13;
                            v3 = Globals.getWorld();
                            v4 = AutoBasePlace.targetPos;
                            Intrinsics.checkNotNull(v4);
                            v5 = v3.getBlockState(v4.up());
                            Intrinsics.checkNotNullExpressionValue(v5, "getBlockState(...)");
                            var25_6 = v5;
                            if (var25_6.isAir() || !var25_6.isReplaceable()) break block14;
                            var26_9 = AutoBasePlace$EntriesMappings.enumEntries;
                            if (!(var26_9 instanceof Collection) || !((Collection)var26_9).isEmpty()) break block15;
                            v6 = false;
                            break block12;
                        }
                        var28_12 = var26_9.iterator();
                        while (var28_12.hasNext()) {
                            var29_13 /* !! */  = var28_12.next();
                            var30_14 = (Direction)var29_13 /* !! */ ;
                            if (var30_14 == Direction.UP) ** GOTO lbl-1000
                            v7 = Globals.getWorld();
                            v8 = AutoBasePlace.targetPos;
                            Intrinsics.checkNotNull(v8);
                            if (v7.getBlockState(v8.offset((Direction)var30_14)).getBlock() instanceof SpongeBlock) {
                                v9 = true;
                            } else lbl-1000:
                            // 2 sources

                            {
                                v9 = false;
                            }
                            if (!v9) continue;
                            v6 = true;
                            break block12;
                        }
                        v6 = false;
                    }
                    if (!v6) break block16;
                }
                return;
            }
            var29_13 /* !! */  = var27_10 = (Iterable)AutoBasePlace$EntriesMappings.enumEntries;
            var30_14 = new ArrayList<E>();
            for (T var33_16 : var29_13 /* !! */ ) {
                var34_17 = (Direction)var33_16;
                if (!(var34_17 != Direction.UP)) continue;
                var30_14.add(var33_16);
            }
            for (Object var27_10 : (List)var30_14) {
                v10 = AutoBasePlace.targetPos;
                Intrinsics.checkNotNull(v10);
                Intrinsics.checkNotNullExpressionValue(v10.offset((Direction)var27_10), "relative(...)");
                if (!this.placeAt((BlockPos)var28_12, var13_4)) continue;
                RotatingModule.s(this, CollectionsKt.listOf(var28_12), false, false, CollectionsKt.listOf(Blocks.SPONGE), false, 22, var15_5, null);
                AutoBasePlace.h.reset();
                break;
            }
        }
    }

    public final boolean placeAt(@NotNull BlockPos blockPos, long l) {
        long l2 = (l = m ^ l) ^ 0x226C75FFCCEEL;
        Intrinsics.checkNotNullParameter(blockPos, "pos");
        Object var12_4 = null;
        return PlaceManager.INSTANCE.findPlaceData(blockPos, this.getInteract(), l2, this.getEntityCheck(), this.getRange(), this.getWallRange());
    }

    @Override
    @NotNull
    protected List getBlocks(long l) {
        return CollectionsKt.listOf(Blocks.OBSIDIAN);
    }

    private static final boolean settingVisible() {
        return INSTANCE.getSponges();
    }

    public static final void setLastHit(BlockHitResult blockHitResult) {
        lastHit = blockHitResult;
    }

    public static final boolean access$getOnlyNoPositions(AutoBasePlace autoBasePlace) {
        return autoBasePlace.getOnlyNoPositions();
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        AutoBasePlace.m = ZkmKeys.a(4274978275322558135L, -3186876596970392574L, MethodHandles.lookup().lookupClass()).a(39814049665642L);
                        var20 = AutoBasePlace.m ^ 5415785378670L;
                        AutoBasePlace.I = new HashMap<K, V>(13);
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
                        var15_5 = "\u00a2\u0080\u00a0\u00c1gso \u0095\u00af.@\u001a\u009dP\u00b8\u0015\u0097\u00ee9Nm\u000ba\u0018\u009e\u00f6`Ev\u0001P9\u00bc\u0093\u0014\u00ce\u0099\u0017\u00de\fl\u00f9(\u00fd\u00eb\u00e6\u00fd\u00f5 \u00ce\u00c2\u00aa\u00f3y\u008d\u0089\\\u00c3\u00c1M*\u00aa\u0018\u0086\t\u0098\u00f3J?\u00c7\u00c0X\u00b5'\u0014\u0012\u00ea7\u00a9K+\u0010\u001aR\f\u00d0>.A\u009e\u00b8\u00a6x[\u0012\u0091\u0087\u0094\u0010=\u00bb\u00f9\u0002\u00ba\u0098G\u0004[\u00fa6\u001c\u00da\u0081\u00ec^\u0010\u00eb\u0086J.)\u00ad\u00b3@\u0082\u0090I\u00f3\u001a\u00a5D\u009d L\u00d5\u00cbeJ\u00c8Y\u007ft\u0082&\u00ec\u00cc\u00ce\u0010\u0094\u00c4\u0082\u00e3\u00a77vb\u00e2\u00af\u0015\u00a4\u009e'\u00c3\u00d7O(\u009c9\u00c6\u00f3_|\u00af\u0011\u00acx\u00b7(\u00fd\u00aa\"\u009d#\u00eb\u00bb\u0089\u0084\u00e0F,\u00fa\u0096\u00ce\u00ab\u009e\u00ccL\u00f8\u00d1\u0005%[G\u00f0\u00b0[ 'kK\u009c\u00dc\u00c9c&\u00fb\"\u009a,\u00cd\u0014\u0099m\u0098\u00819<\u00a6fN\u00d7\u00de\u00e9(\u00d1\u00b2}d\u001f\u0018\u00ed\u00a099\u0098vs\u00b1\u001f\u009c+\u007f9\u00cf\u000bS\u00fc]\u00b4)9z\u00e6\u0091\u0010\u00bc1\u00b9\u001d\u009a\u00d0\u00fd\u00a3\u00fa\u000e\u0086VS)8\u00ac(F8\u00d5\u0011y\u0010\u009d5g\u00e6\u00df\u0088\u0094\u008d\u001cA~\u00ec\u0084h\u00cb\u00fe\f5\t\u00dbo\u001f\u00b6\u001epZMv\u00a8\u00a2\u0083y\u0001j";
                        var17_6 = 323;
                        var14_7 = 24;
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
                            var18_3[var16_4++] = AutoBasePlace.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00a1\u0010;ms|\u000b\u00a8\u00b8(F\u00b2\u007f\u00e8E\u0080@]\u007f):\u0080C\u00df\u00d0t\u00bb\u00f3\u00b8\u001e\u0083\u00f8}\u00fc\u00c5jQ\u00e2J7D\u00e0\u00f1\u00dd\u00ba\u00e7\u00c1\u0095(dJ\u00a9hu\u0094\u00c1\u0099#\u00ed\\D\u00ee\u00ec4a\u00c0\u00e7\n\u0015\u00e6i\u00b5ZO&\u00fc\u00baN?\u009e\u0098=\u001ea;\\\u00ce\u00e8\u00ce";
                            var17_6 = 89;
                            var14_7 = 48;
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
                            var18_3[var16_4++] = AutoBasePlace.zkmDecodeUtf8(var19_9).intern();
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
                AutoBasePlace.n = var18_3;
                AutoBasePlace.y = new String[14];
                AutoBasePlace.W = new HashMap<K, V>(13);
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
                var6_12 = new long[8];
                var3_13 = 0;
                var4_14 = "\u00c5\u0001h\u00cc\f3\u0087\u00f7[0\u00f8\u008e\u00faG\u00d71X\u00ab]H\u0080\u008a\u00ebE\t\u007f\u00a8\r\u0015\u00f4i`\u00fa\u008f\u00992\u0005\u00c5]q\u00cdb\u00fe\u009f\u00f6\u00fa\u00d4!";
                var5_15 = 48;
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
                    var4_14 = "\u001a\u0085\u00f9\u00cb\u0086\u0091\u0093\u0093\u00ab*#n\u00f3\u00db\u00f9\u00c5";
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
        AutoBasePlace.O = var6_12;
        AutoBasePlace.S = new Integer[8];
        var31_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(AutoBasePlace.class, "sponges", "getSponges()Z", 0)), Reflection.property1(new PropertyReference1Impl(AutoBasePlace.class, "spongeDelay", "getSpongeDelay()I", 0)), Reflection.property1(new PropertyReference1Impl(AutoBasePlace.class, "onlyNoPositions", "getOnlyNoPositions()Z", 0))};
        AutoBasePlace.delegatedProperties = var31_20;
        AutoBasePlace.INSTANCE = new AutoBasePlace();
        AutoBasePlace.sponges$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBasePlace.INSTANCE, "sponges", false, null, null, 12, null);
        AutoBasePlace.spongeDelay$delegate = SettingFactory.intSetting$default(AutoBasePlace.INSTANCE, "sponge-delay", 10, new IntRange(0, 60), null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 8, null);
        AutoBasePlace.onlyNoPositions$delegate = SettingFactory.booleanSetting$default((SettingContainer)AutoBasePlace.INSTANCE, "only-no-positions", false, null, null, 12, null);
        AutoBasePlace.i = new TickTimer();
        AutoBasePlace.h = new TickTimer();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4670;
        if (y[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])I.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    I.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_5", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = AutoBasePlace.n[n2].getBytes("ISO-8859-1");
            AutoBasePlace.y[n2] = AutoBasePlace.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return y[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AutoBasePlace.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x353C;
        if (S[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = O[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])W.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    W.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_5", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AutoBasePlace.S[n2] = n3;
        }
        return S[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AutoBasePlace.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AutoBasePlace.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(AutoBasePlace.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

