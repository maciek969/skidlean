/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap
 */
package su.catlean.util.combat;

import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.Difficulty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.ExcludeFlow;
import su.catlean.ExcludeIndy;
import su.catlean.api.event.events.network.PostTasksProcessEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.attack.AutoCrystal;
import su.catlean.util.ArmorUtil;
import su.catlean.util.EnchantUtil;
import su.catlean.util.Globals;
import su.catlean.util.InterpolationTracker;
import su.catlean.util.Manager;
import su.catlean.util.PlaceManager;
import su.catlean.zkm.ZkmKeys;

@ExcludeFlow
@ExcludeIndy
public final class DamageUtil
implements Manager {
    @NotNull
    public static final DamageUtil INSTANCE;
    public static final double S = -1.0E-7;
    @NotNull
    private static final Long2ByteOpenHashMap long2ByteOpenHashMap;
    @NotNull
    private static final VoxelShape voxelShape;
    @Nullable
    private static DamageSource damageSource;
    private static boolean p;
    private static final long a;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;
    private static final long[] g;
    private static final Integer[] h;
    private static final Map i;

    private DamageUtil() {
    }

    public final boolean k() {
        return p;
    }

    public final void t(boolean bl) {
        p = bl;
    }

    @Flow
    private final void onPostTasksProcess(PostTasksProcessEvent postTasksProcessEvent) {
        long2ByteOpenHashMap.clear();
    }

    public final float getExplosionDamage(@NotNull Vec3d vec3d, @NotNull PlayerEntity playerEntity, int n, boolean bl, @NotNull List list, boolean bl2) {
        Box box;
        Intrinsics.checkNotNullParameter(vec3d, "explosionPos");
        Intrinsics.checkNotNullParameter(playerEntity, "target");
        Intrinsics.checkNotNullParameter(list, "ghost");
        if (n > 0) {
            box = InterpolationTracker.INSTANCE.getBox(playerEntity, n);
        } else {
            Box box2 = playerEntity.getBoundingBox();
            box = box2;
            Intrinsics.checkNotNullExpressionValue(box2, "getBoundingBox(...)");
        }
        Box box3 = box;
        double d = box3.getCenter().add(0.0, -box3.getLengthY() / 2.0, 0.0).distanceTo(vec3d) / 12.0;
        if (Globals.getWorld().getDifficulty() == Difficulty.PEACEFUL || d > 1.0 || playerEntity.isCreative()) {
            return 0.0f;
        }
        double d2 = this.getExposureFraction(vec3d, box3, bl, list, bl2);
        double d3 = d;
        return this.calcDamageAfterArmor(playerEntity, d3, d2);
    }

    public static float getExplosionDamage$default(DamageUtil damageUtil, Vec3d vec3d, PlayerEntity playerEntity, int n, boolean bl, List list, boolean bl2, int n2, Object object) {
        if ((n2 & 2) != 0) {
            playerEntity = Globals.getPlayer();
        }
        if ((n2 & 4) != 0) {
            n = 0;
        }
        if ((n2 & 8) != 0) {
            bl = false;
        }
        if ((n2 & 0x10) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((n2 & 0x20) != 0) {
            bl2 = false;
        }
        return damageUtil.getExplosionDamage(vec3d, playerEntity, n, bl, list, bl2);
    }

    private final float calcDamageAfterArmor(PlayerEntity playerEntity, double d, double d2) {
        if (damageSource == null) {
            damageSource = new DamageSource(Globals.getWorld().getDamageSources().registry.getOrThrow(DamageTypes.EXPLOSION), null, null);
        }
        float f = this.calcRawDamage(d, d2);
        if (Globals.getWorld().getDifficulty() == Difficulty.EASY) {
            f = (float)Math.min((double)f / 2.0 + 1.0, (double)f);
        } else if (Globals.getWorld().getDifficulty() == Difficulty.HARD) {
            f *= 1.5f;
        }
        LivingEntity livingEntity = playerEntity;
        DamageSource damageSource = DamageUtil.damageSource;
        Intrinsics.checkNotNull(damageSource);
        float f2 = playerEntity.getArmor();
        EntityAttributeInstance entityAttributeInstance = playerEntity.getAttributeInstance(EntityAttributes.ARMOR_TOUGHNESS);
        Intrinsics.checkNotNull(entityAttributeInstance);
        f = net.minecraft.entity.DamageUtil.getDamageLeft(livingEntity, f, damageSource, f2, (float)entityAttributeInstance.getValue());
        if (playerEntity.hasStatusEffect(StatusEffects.RESISTANCE)) {
            StatusEffectInstance statusEffectInstance = playerEntity.getStatusEffect(StatusEffects.RESISTANCE);
            Intrinsics.checkNotNull(statusEffectInstance);
            int n = 25 - (statusEffectInstance.getAmplifier() + 1) * 5;
            f = (float)Math.max((double)(f * (float)n / 25.0f), 0.0);
        }
        if (f <= 0.0f) {
            f = 0.0f;
        } else {
            float f3;
            float f4 = f3 = AutoCrystal.INSTANCE.getAssumeBestArmor() && !Intrinsics.areEqual(playerEntity, Globals.getPlayer()) ? this.sumFloats(ArmorUtil.getArmorItems(playerEntity)) : (float)this.sumInts(ArmorUtil.getArmorItems(playerEntity));
            if (f3 > 0.0f) {
                f = net.minecraft.entity.DamageUtil.getInflictedDamage(f, f3);
            }
        }
        return f;
    }

    private final float sumFloats(Iterable iterable) {
        float f = 0.0f;
        Iterable iterable2 = iterable;
        for (Object t : iterable2) {
            ItemStack itemStack = (ItemStack)t;
            if (itemStack.isEmpty()) continue;
            f += 8.0f;
        }
        return f;
    }

    private final float calcRawDamage(double d, double d2) {
        double d3 = (1.0 - d) * d2;
        return (float)((d3 * d3 + d3) / 2.0 * 7.0 * 12.0 + 1.0);
    }

    private final float getExposureFraction(Vec3d vec3d, Box box, boolean bl, List list, boolean bl2) {
        Pair pair = this.getExposure(vec3d, box, bl, list, bl2);
        if ((float)((Number)pair.getFirst()).intValue() / (float)((Number)pair.getSecond()).intValue() == 1.0f) {
            return 1.0f;
        }
        if ((float)((Number)pair.getFirst()).intValue() / (float)((Number)pair.getSecond()).intValue() == 0.0f) {
            return 0.0f;
        }
        double d = 1.0 / ((box.maxX - box.minX) * 2.0 + 1.0);
        double d2 = 1.0 / ((box.maxY - box.minY) * 2.0 + 1.0);
        double d3 = 1.0 / ((box.maxZ - box.minZ) * 2.0 + 1.0);
        double d4 = (1.0 - Math.floor(1.0 / d) * d) / 2.0;
        double d5 = (1.0 - Math.floor(1.0 / d3) * d3) / 2.0;
        if (d >= 0.0 && d2 >= 0.0 && d3 >= 0.0) {
            int n = ((Number)pair.getFirst()).intValue();
            int n2 = ((Number)pair.getSecond()).intValue();
            for (double d6 = 0.0; d6 <= 1.0; d6 += d) {
                for (double d7 = 0.0; d7 <= 1.0; d7 += d2) {
                    for (double d8 = 0.0; d8 <= 1.0; d8 += d3) {
                        double d9 = MathHelper.lerp(d6, box.minX, box.maxX);
                        double d10 = MathHelper.lerp(d7, box.minY, box.maxY);
                        double d11 = MathHelper.lerp(d8, box.minZ, box.maxZ);
                        if (this.getCornerPoints(d6, d7, d8)) continue;
                        if (this.raycastExplosion(new Vec3d(d9 + d4, d10, d11 + d5), vec3d, bl, list, bl2) == null) {
                            ++n;
                        }
                        ++n2;
                    }
                }
            }
            return (float)n / (float)n2;
        }
        return 0.0f;
    }

    static float getExposureFraction$default(DamageUtil damageUtil, Vec3d vec3d, Box box, boolean bl, List list, boolean bl2, int n, Object object) {
        long l = 0L;
        if ((n & DamageUtil.zkmBootstrap2("d", (int)18829, (long)(0x4A1049EA6FA6DF21L ^ (l = a ^ l)))) != 0) {
            list = CollectionsKt.emptyList();
        }
        return damageUtil.getExposureFraction(vec3d, box, bl, list, bl2);
    }

    private final boolean getCornerPoints(double d, double d2, double d3) {
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < 2; ++k) {
                    if (!(d == (double)i) || !(d2 == (double)j) || !(d3 == (double)k)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    private final Pair getExposure(Vec3d vec3d, Box box, boolean bl, List list, boolean bl2) {
        double d = 1.0 / ((box.maxX - box.minX) * 2.0 + 1.0);
        double d2 = 1.0 / ((box.maxZ - box.minZ) * 2.0 + 1.0);
        double d3 = (1.0 - Math.floor(1.0 / d) * d) / 2.0;
        double d4 = (1.0 - Math.floor(1.0 / d2) * d2) / 2.0;
        int n = 0;
        int n2 = 0;
        for (double d5 = 0.0; d5 <= 1.0; d5 += 1.0) {
            for (double d6 = 0.0; d6 <= 1.0; d6 += 1.0) {
                for (double d7 = 0.0; d7 <= 1.0; d7 += 1.0) {
                    double d8;
                    double d9;
                    double d10 = MathHelper.lerp(d5, box.minX, box.maxX);
                    Vec3d vec3d2 = new Vec3d(d10 + d3, d9 = MathHelper.lerp(d6, box.minY, box.maxY), (d8 = MathHelper.lerp(d7, box.minZ, box.maxZ)) + d4);
                    if (this.raycastExplosion(vec3d2, vec3d, bl, list, bl2) == null) {
                        ++n;
                    }
                    ++n2;
                }
            }
        }
        return new Pair<Integer, Integer>(n, n2);
    }

    static Pair getExposure$default(DamageUtil damageUtil, Vec3d vec3d, Box box, boolean bl, List list, boolean bl2, int n, Object object) {
        long l = 0L;
        if ((n & DamageUtil.zkmBootstrap2("d", (int)18829, (long)(0x4A1038949B9CEF7CL ^ (l = a ^ l)))) != 0) {
            list = CollectionsKt.emptyList();
        }
        return damageUtil.getExposure(vec3d, box, bl, list, bl2);
    }

    private final BlockHitResult getBlockShape(BlockPos blockPos, Vec3d vec3d, Vec3d vec3d2, boolean bl, List list, boolean bl2) {
        float f;
        VoxelShape voxelShape;
        if (list.contains(blockPos)) {
            return bl2 ? VoxelShapes.fullCube().raycast(vec3d, vec3d2, blockPos) : null;
        }
        long l = blockPos.asLong();
        byte by = long2ByteOpenHashMap.get(l);
        if (p && by != 0) {
            voxelShape = this.getShape(by);
            f = this.getResistance(by);
        } else {
            BlockState blockState = Globals.getWorld().getBlockState(blockPos);
            Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
            BlockState blockState2 = blockState;
            VoxelShape voxelShape2 = blockState2.getCollisionShape(Globals.getWorld(), blockPos);
            Intrinsics.checkNotNullExpressionValue(voxelShape2, "getCollisionShape(...)");
            voxelShape = voxelShape2;
            f = blockState2.getBlock().getBlastResistance();
            if (voxelShape.isEmpty() || Intrinsics.areEqual(blockState2.getBlock(), Blocks.ENDER_CHEST) || Intrinsics.areEqual(voxelShape, VoxelShapes.fullCube())) {
                Map map = (Map)long2ByteOpenHashMap;
                Long l2 = l;
                Block block = blockState2.getBlock();
                Intrinsics.checkNotNullExpressionValue(block, "getBlock(...)");
                Byte by2 = this.isBlastProof(block, voxelShape.isEmpty());
                map.put(l2, by2);
            }
        }
        if (f < 600.0f && bl) {
            return null;
        }
        return voxelShape.raycast(vec3d, vec3d2, blockPos);
    }

    static BlockHitResult getBlockShape$default(DamageUtil damageUtil, BlockPos blockPos, Vec3d vec3d, Vec3d vec3d2, boolean bl, List list, boolean bl2, int n, Object object) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
        if ((n & DamageUtil.zkmBootstrap2("d", (int)11171, (long)(0x5EF4580DE9397DCL ^ l))) != 0) {
            bl = false;
        }
        if ((n & DamageUtil.zkmBootstrap2("d", (int)19989, (long)(0x4A475C41A751F26CL ^ l))) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((n & DamageUtil.zkmBootstrap2("d", (int)26154, (long)(0x2F6B84C127805A50L ^ l))) != 0) {
            bl2 = true;
        }
        return damageUtil.getBlockShape(blockPos, vec3d, vec3d2, bl, list, bl2);
    }

    private final float getResistance(byte by) {
        byte by2 = by;
        return by2 == 1 || by2 == 2 ? 0.0f : 600.0f;
    }

    private final VoxelShape getShape(byte by) {
        byte by2 = by;
        VoxelShape voxelShape = by2 == 1 ? VoxelShapes.empty() : (by2 == 4 ? DamageUtil.voxelShape : VoxelShapes.fullCube());
        Intrinsics.checkNotNull(voxelShape);
        return voxelShape;
    }

    private final byte isBlastProof(Block block, boolean bl) {
        if (bl) {
            return 1;
        }
        if (Intrinsics.areEqual(block, Blocks.ENDER_CHEST)) {
            return 4;
        }
        if (block.getBlastResistance() >= 600.0f) {
            return 3;
        }
        return 2;
    }

    @Nullable
    public final BlockHitResult raycastExplosion(@NotNull Vec3d vec3d, @NotNull Vec3d vec3d2, boolean bl, @NotNull List list, boolean bl2) {
        int n;
        int n2;
        Intrinsics.checkNotNullParameter(vec3d, "start");
        Intrinsics.checkNotNullParameter(vec3d2, "end");
        Intrinsics.checkNotNullParameter(list, "ghost");
        if (Intrinsics.areEqual(vec3d, vec3d2)) {
            return null;
        }
        double d = MathHelper.lerp(-1.0E-7, vec3d2.x, vec3d.x);
        double d2 = MathHelper.lerp(-1.0E-7, vec3d2.y, vec3d.y);
        double d3 = MathHelper.lerp(-1.0E-7, vec3d2.z, vec3d.z);
        double d4 = MathHelper.lerp(-1.0E-7, vec3d.x, vec3d2.x);
        double d5 = MathHelper.lerp(-1.0E-7, vec3d.y, vec3d2.y);
        double d6 = MathHelper.lerp(-1.0E-7, vec3d.z, vec3d2.z);
        int n3 = MathHelper.floor(d4);
        BlockPos.Mutable mutable = new BlockPos.Mutable(n3, n2 = MathHelper.floor(d5), n = MathHelper.floor(d6));
        BlockHitResult blockHitResult = this.getBlockShape(mutable, vec3d, vec3d2, bl, list, bl2);
        if (blockHitResult != null) {
            return blockHitResult;
        }
        double d7 = d - d4;
        double d8 = d2 - d5;
        double d9 = d3 - d6;
        int n4 = MathHelper.sign(d7);
        int n5 = MathHelper.sign(d8);
        int n6 = MathHelper.sign(d9);
        double d10 = n4 == 0 ? Double.MAX_VALUE : (double)n4 / d7;
        double d11 = n5 == 0 ? Double.MAX_VALUE : (double)n5 / d8;
        double d12 = n6 == 0 ? Double.MAX_VALUE : (double)n6 / d9;
        double d13 = d10 * (n4 > 0 ? 1.0 - MathHelper.fractionalPart(d4) : MathHelper.fractionalPart(d4));
        double d14 = d11 * (n5 > 0 ? 1.0 - MathHelper.fractionalPart(d5) : MathHelper.fractionalPart(d5));
        double d15 = d12 * (n6 > 0 ? 1.0 - MathHelper.fractionalPart(d6) : MathHelper.fractionalPart(d6));
        while (d13 <= 1.0 || d14 <= 1.0 || d15 <= 1.0) {
            if (d13 < d14) {
                if (d13 < d15) {
                    n3 += n4;
                    d13 += d10;
                } else {
                    n += n6;
                    d15 += d12;
                }
            } else if (d14 < d15) {
                n2 += n5;
                d14 += d11;
            } else {
                n += n6;
                d15 += d12;
            }
            BlockPos.Mutable mutable2 = mutable.set(n3, n2, n);
            Intrinsics.checkNotNullExpressionValue(mutable2, "set(...)");
            BlockHitResult blockHitResult2 = this.getBlockShape(mutable2, vec3d, vec3d2, bl, list, bl2);
            if (blockHitResult2 == null) continue;
            BlockHitResult blockHitResult3 = blockHitResult2;
            return blockHitResult3;
        }
        return null;
    }

    public static BlockHitResult raycastExplosion$default(DamageUtil damageUtil, Vec3d vec3d, Vec3d vec3d2, boolean bl, List list, boolean bl2, int n, Object object) {
        if ((n & 4) != 0) {
            bl = false;
        }
        if ((n & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((n & 0x10) != 0) {
            bl2 = true;
        }
        return damageUtil.raycastExplosion(vec3d, vec3d2, bl, list, bl2);
    }

    public final boolean canSeeFromEyes(@NotNull Vec3d vec3d) {
        Intrinsics.checkNotNullParameter(vec3d, "point");
        Entity entity = Globals.getPlayer();
        Object object = DamageUtil.raycastExplosion$default(this, PlaceManager.INSTANCE.getEyePos(entity), vec3d, false, null, false, 28, null);
        if (object == null || (object = object.getType()) == null) {
            object = HitResult.Type.MISS;
        }
        return object == HitResult.Type.MISS;
    }

    private final int sumInts(Iterable iterable) {
        int n = 0;
        Iterable iterable2 = iterable;
        for (Object t : iterable2) {
            ItemStack itemStack = (ItemStack)t;
            n += INSTANCE.getBlastProtection(itemStack);
        }
        return n;
    }

    private final int getBlastProtection(ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty()) {
            return 0;
        }
        RegistryKey<Enchantment> registryKey = Enchantments.BLAST_PROTECTION;
        Intrinsics.checkNotNullExpressionValue(registryKey, "BLAST_PROTECTION");
        int n = EnchantUtil.getEnchantmentLevel(itemStack, registryKey);
        RegistryKey<Enchantment> registryKey2 = Enchantments.PROTECTION;
        Intrinsics.checkNotNullExpressionValue(registryKey2, "PROTECTION");
        int n2 = EnchantUtil.getEnchantmentLevel(itemStack, registryKey2);
        return n * 2 + n2;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        DamageUtil.a = ZkmKeys.a(926659411245365366L, -4733013729815131179L, MethodHandles.lookup().lookupClass()).a(255625429357492L);
                        var20 = DamageUtil.a ^ 8846302682020L;
                        DamageUtil.f = new HashMap<K, V>(13);
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
                        var18_3 = new String[15];
                        var16_4 = 0;
                        var15_5 = "\u007f\u00da_\u00d7\u00c4S\u0098\u0097?\u00acI\u00ee\u00a1\u0011Y\u00da\u0010\u00ff\u00d6\u00fa1\u009eC/\u00e9^V\u009c\r$5O\u0086(\u001by\u001f\u0085\u0088\u00f0\u00ba7\u001d4S\u00c7c\u00fb\u0084B\u0086\u00d8l&%i\u0088\u008c\u00eb\u00df\u009e\u00a7\u00c0|\u008e\u0086\u00f5j\u00d1\u00d3(\u00f1\u00da\u0006\u0018\u00a0\u001f\u0088\u0097Z\u0081T+\f\u009f\u0083F\\7\u00c7P\u00cd,\u00de5\u008c\u009c\u00feE\u0010\u008a\u00996\u0000RmJ\u00a7\u00b7\u00d4\u00d7\u009f\fJmt \\\u001f\u00b9\u0092\u0083\u009caO\u00b7\u00cfac\u00e5\u0016\u008d\u00cbuSC\u008cp\u00ea\u00b1\u009ak;\u00dd\u00d2!\u00b4\u0098\u00ae\u0018\u00d7\u00bc\u0097\u00b6\u00c14\u00e4b\u00fd\u00c2\u0084S\u00cb-\u00f7\u0017\u00acYLC5\u00f5\u0006\u00af(!p\u00b0\u00d59\u00d9o\u00be\u0096\\\u00d8\u0095L\u00ce\u00a9Q\u0094\u00c8\u00e6\u00c7\u00a4\u0095\u00ee\u00d5\u0017=5T~\u00e0\\\u0013\u00bf\u001e&H5\u0019\u00d6O(\u00dc\u0084\u00e57\u0087\u00a9\u00aav\u008a\u00eaG\u00d4\u0013v\u009c\\}\u001b@\u0099\u00b1\u00dd\u00b4M\u00cb\u0017\u000f\u00ec\u00b5\u0013\u00a1\u00ba\u008b\u00fc8RH\u0086\u0013\u00c9\u0010\u001927\u00f1?n\u00fe\u00ff\u0014\u0088\u00f6Q\u0000\u0089I\u0007\u0018\u00a0\"\u0013\u0089[xQ\u00a17K\u00d8\u00eajx\u00e3GX\u008b)6S\u00d7\u0098\u0081\u00101\u00c5\u00fa\u0089\u00aa\u00ae\u0004\u0083\u0011j\"\u0017L\u0011j\u00df \u00cd\u00b5:\u0097\u0019>\u00f9\u00e3'\u00bc\u00ad\u0003`US\u0088\f\u00f8\u0094\b\u00f0o\u00b3e\u00d2F\u0080\u00c9C\u0019<\u0091";
                        var17_6 = 348;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = DamageUtil.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\f\u00ab?U$*\u0093\u0013\u00cc\u00af\u0084\u00ee\u00b7\u0017\u00a6\u00bc(\u00c5\u00b8\u00d9+\u0090^m\u00d0\u00ae\u009f1Kw9\u00a9\u00db\u0004\u0089\u0094*\u00e7\u00af\u00ea!\u00d1\u00f55\u008a\u00aexDw\u0094lRJ\u0083\u00e1o\u00e3";
                            var17_6 = 57;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = DamageUtil.zkmDecodeUtf8(var19_9).intern();
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
                DamageUtil.d = var18_3;
                DamageUtil.e = new String[15];
                DamageUtil.i = new HashMap<K, V>(13);
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
                var4_14 = "\u00ad\u0098\u00bf*\u00f6\u00d7\u00d6\\\u00c3r\u00f7\u00a3VFB\u00f8\u00d5\u00ec=\u00aaI\u00af\u00c6\u00cf\u00f6\u0017\u00c1\u00b9\u0000Q\u0000\u00b8\u00fc\u00b1V\u00b3s\u00aeU\u00feo\u008187k\u00b6\u00c2z";
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
                    var4_14 = "u\u00e1dz\u00f6u\u00cb\u00caKj\u00a1\u00e3$\u0010\u0017b";
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
        DamageUtil.g = var6_12;
        DamageUtil.h = new Integer[8];
        DamageUtil.INSTANCE = new DamageUtil();
        DamageUtil.long2ByteOpenHashMap = new Long2ByteOpenHashMap();
        v15 = Block.createColumnShape(14.0, 0.0, 14.0);
        Intrinsics.checkNotNullExpressionValue(v15, "column(...)");
        DamageUtil.voxelShape = v15;
        DamageUtil.p = true;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x353F;
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
                throw new RuntimeException("su/catlean/la", exception);
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
            DamageUtil.e[n2] = DamageUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = DamageUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/la" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xDE9;
        if (h[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = g[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])i.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    i.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/la", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            DamageUtil.h[n2] = n3;
        }
        return h[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = DamageUtil.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/la" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(DamageUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(DamageUtil.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

