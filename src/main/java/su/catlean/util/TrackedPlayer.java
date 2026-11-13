/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 */
package su.catlean.util;

import com.google.common.collect.ImmutableList;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.border.WorldBorder;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class TrackedPlayer {
    @NotNull
    private final PlayerEntity target;
    @NotNull
    private final ArrayList Y;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;

    public TrackedPlayer(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "target");
        this.target = playerEntity;
        this.Y = new ArrayList();
    }

    @NotNull
    public final PlayerEntity getTarget() {
        return this.target;
    }

    @NotNull
    public final synchronized ArrayList f() {
        return this.Y;
    }

    public final void clearHistory() {
        ArrayList arrayList = this.Y;
        synchronized (arrayList) {
            this.Y.clear();
            Ref.DoubleRef doubleRef = new Ref.DoubleRef();
            doubleRef.element = this.target.getX() - this.target.lastX;
            Ref.DoubleRef doubleRef2 = new Ref.DoubleRef();
            doubleRef2.element = this.target.getY() - this.target.lastY;
            Ref.DoubleRef doubleRef3 = new Ref.DoubleRef();
            doubleRef3.element = this.target.getZ() - this.target.lastZ;
            if (Intrinsics.areEqual(this.target, Globals.getPlayer())) {
                doubleRef.element = Globals.getPlayer().getVelocity().x;
                doubleRef2.element = Globals.getPlayer().getVelocity().y;
                doubleRef3.element = Globals.getPlayer().getVelocity().z;
            }
            double d = Math.hypot(doubleRef.element, doubleRef3.element);
            double d2 = MathHelper.clamp(d, this.target.isSneaking() ? 0.08511 : 0.2837, 0.45392000000000005);
            if (!(d == 0.0)) {
                doubleRef.element = doubleRef.element / d * d2;
                doubleRef3.element = doubleRef3.element / d * d2;
            }
            if (Globals.getWorld().isBlockSpaceEmpty(this.target, this.target.getBoundingBox().stretch(0.0, -0.1, 0.0)) && doubleRef2.element > 0.0) {
                doubleRef2.element = -0.0784;
            }
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            PlayerEntity playerEntity = this.target;
            objectRef.element = this.getLastBox(playerEntity);
            if (doubleRef2.element > 0.0) {
                doubleRef2.element = this.applyGravity(doubleRef2.element);
            }
            if (d < 0.056740000000000006) {
                doubleRef.element = 0.0;
                doubleRef3.element = 0.0;
            }
            if (d < 0.056740000000000006 && Math.abs(doubleRef2.element) < 0.1) {
                this.Y.addAll(Collections.nCopies(20, objectRef.element));
                return;
            }
            int n = 20;
            for (int i = 0; i < n; ++i) {
                Box box;
                Intrinsics.checkNotNullExpressionValue(((Box)objectRef.element).offset(0.0, -0.01, 0.0), "move(...)");
                PlayerEntity playerEntity2 = this.target;
                doubleRef2.element = this.collides(playerEntity2, box) ? -0.0784 : this.applyGravity(doubleRef2.element);
                objectRef.element = this.adjustMovement((Box)objectRef.element, doubleRef.element, doubleRef2.element, doubleRef3.element);
                this.Y.add(objectRef.element);
            }
        }
    }

    public final double applyGravity(double d) {
        return (d - 0.08) * 0.98;
    }

    public final boolean collides(@NotNull PlayerEntity playerEntity, @NotNull Box box) {
        Intrinsics.checkNotNullParameter(playerEntity, "player");
        Intrinsics.checkNotNullParameter(box, "box");
        return Globals.getWorld().getBlockCollisions(playerEntity, box).iterator().hasNext();
    }

    @NotNull
    public final Box getLastBox(@NotNull PlayerEntity playerEntity) {
        Intrinsics.checkNotNullParameter(playerEntity, "entity");
        Box box = playerEntity.getBoundingBox();
        Intrinsics.checkNotNullExpressionValue(box, "getBoundingBox(...)");
        Box box2 = box;
        Vec3d vec3d = new Vec3d(playerEntity.lastX, playerEntity.lastY, playerEntity.lastZ);
        if (vec3d.lengthSquared() == 0.0) {
            Box box3 = playerEntity.getBoundingBox();
            Intrinsics.checkNotNullExpressionValue(box3, "getBoundingBox(...)");
            return box3;
        }
        double d = box2.getLengthX() / 2.0;
        return new Box(-d + vec3d.x, vec3d.y, -d + vec3d.z, d + vec3d.x, box2.getLengthY() + vec3d.y, d + vec3d.z);
    }

    private final List getCollisions(Entity entity, List list, Box box) {
        ImmutableList.Builder builder = ImmutableList.builderWithExpectedSize((int)(list.size() + 1));
        Intrinsics.checkNotNullExpressionValue(builder, "builderWithExpectedSize(...)");
        ImmutableList.Builder builder2 = builder;
        if (!list.isEmpty()) {
            builder2.addAll((Iterable)list);
        }
        WorldBorder worldBorder = Globals.getWorld().getWorldBorder();
        Intrinsics.checkNotNullExpressionValue(worldBorder, "getWorldBorder(...)");
        WorldBorder worldBorder2 = worldBorder;
        boolean bl = worldBorder2.canCollide(entity, box);
        if (bl) {
            builder2.add((Object)worldBorder2.asVoxelShape());
        }
        ImmutableList immutableList = builder2.addAll(Globals.getWorld().getBlockCollisions(entity, box)).build();
        Intrinsics.checkNotNullExpressionValue(immutableList, "build(...)");
        return (List)immutableList;
    }

    @NotNull
    public final Box adjustMovement(@NotNull Box box, double d, double d2, double d3) {
        boolean bl;
        double d4;
        Intrinsics.checkNotNullParameter(box, "box");
        Box box2 = box.stretch(d, d2, d3);
        Intrinsics.checkNotNullExpressionValue(box2, "expandTowards(...)");
        Box box3 = box2;
        Entity entity = this.target;
        List<VoxelShape> list = Globals.getWorld().getEntityCollisions(this.target, box3);
        Intrinsics.checkNotNullExpressionValue(list, "getEntityCollisions(...)");
        Box box4 = box3;
        List<VoxelShape> list2 = list;
        Entity entity2 = entity;
        List list3 = this.getCollisions(entity2, list2, box4);
        Box box5 = box;
        double d5 = d;
        double d6 = d2;
        double d7 = d3;
        if (list3.isEmpty()) {
            Box box6 = box5.offset(d5, d6, d7);
            Intrinsics.checkNotNullExpressionValue(box6, "move(...)");
            return box6;
        }
        Box box7 = box5;
        if (!(d6 == 0.0)) {
            double d8;
            d6 = d4 = (d8 = VoxelShapes.calculateMaxOffset(Direction.Axis.Y, box5, list3, d6));
            if (!(d8 == 0.0)) {
                Box box8 = box5.offset(0.0, d6, 0.0);
                Intrinsics.checkNotNullExpressionValue(box8, "move(...)");
                box5 = box8;
            }
        }
        boolean bl2 = bl = Math.abs(d5) < Math.abs(d7);
        if (bl && !(d7 == 0.0)) {
            double d9;
            d7 = d9 = (d4 = VoxelShapes.calculateMaxOffset(Direction.Axis.Z, box5, list3, d7));
            if (!(d4 == 0.0)) {
                Box box9 = box5.offset(0.0, 0.0, d7);
                Intrinsics.checkNotNullExpressionValue(box9, "move(...)");
                box5 = box9;
            }
        }
        if (!(d5 == 0.0)) {
            d5 = VoxelShapes.calculateMaxOffset(Direction.Axis.X, box5, list3, d5);
            if (!bl && !(d5 == 0.0)) {
                Box box10 = box5.offset(d5, 0.0, 0.0);
                Intrinsics.checkNotNullExpressionValue(box10, "move(...)");
                box5 = box10;
            }
        }
        if (!bl && !(d7 == 0.0)) {
            d7 = VoxelShapes.calculateMaxOffset(Direction.Axis.Z, box5, list3, d7);
        }
        Box box11 = box7.offset(d5, d6, d7);
        Intrinsics.checkNotNullExpressionValue(box11, "move(...)");
        return box11;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        TrackedPlayer.a = ZkmKeys.a(-8236725112308872336L, 283751220734721062L, MethodHandles.lookup().lookupClass()).a(140134895162036L);
                        TrackedPlayer.e = new HashMap<K, V>(13);
                        var11 = TrackedPlayer.a ^ 117070300704913L;
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
                        var20_3 = new String[14];
                        var18_4 = 0;
                        var17_5 = "\u00a4\u00ef\u00e6\u0085\u00cb\u0082\u00abxim{\u00fa\u00ff\u00c3WC\u0018\u0084S\u00c7cU\u00d3C^\u00a8,\u00a4>\u0084\u00ce\u00cb\"<\r\u00f9\u0085\u00d7T0\u00a0\u0010\fN\u008et\u00a9 I\u0012\u00d5\u00cb\u001a\u00f5\u0003\u00aa\u0096\u00120Kv\u009c\u008d\u0098\u00c6\u0005 \u00aeJ\u00bd1GbQ>B7@+\u008f#\u0006\u00a4\u0081s)r\u00e0\u00bb\u008f\u0002\u00b1\u0082l!k\u0099Z\u000b\u00b9\u001c\u00d6J{\u00bad60\u00ff.`\u00cd\u00d6\u008fR\u00b5\u0001%\u00f5w2\u00a3\u00ba5\u00f3!\u0011\u00cck\u00a2\u0015\u009fk\neKb\u0001\u00bb\u00d0K\\1\u00ee\u00aa\u009bO]\u00aa\u00d8\u00e0\u00eb\u00deP\u00c5\u00c3 \u0091\u00c5\u00d6@c\u00a7\u008d0\u0016\u00bbF:i\u009d\u00ef\u008d|\u00b8$\u00ee\u0012\u009d7\u00d6~K\u00d1\u00d9\u00d8\u001d\u00e0\u0087(7\u0087V\u008a\u00ca\u00ebP\u00cc\u0019\b\u00aaO\u00d3gN\n9\u00e7\u00d5\u00fef\u00c6X\u00f0*\u00b4\u00079\u0098\u00ad\u00c2\u00e8\u00f9\u008a\u00d6A9\u0097\u00d6,\u0010\u0098\u00a3\u00e9\u00da\u00d8\u00c0\u0097\u0013\u008f)\u00f2\u00a2\u00a9\u00a5f\u0096\u0010\u00fd\"e\u00f4\u00f74\u00d6\u00c2\u00bf\u00e1Y\u00f6\u00f5\u0093\bv(\u00f92#1\u0095f\u0080\u00a9~\u00e6n\u00fd=\u00e2a\u001a?\u0090\u00fa\u00d52-\u00dcM\u00ad\u00d3o\u00d7\fC!\b\u0003\u001c\u0089\u00baQb\u00acN\u0018\u00e8gi\u00f8\u0000I!\u001b\u00fe\u00bd\u00b6\u008b\u00f7\u00eb\u00c6R\u009c\u00a5\u00e4\u00e8G^\u00d3\u00840\u00cd\u00d3\u00d9\u00b1t7\u009d\u00f7\u0004\u000f\u00f3\u009ej\u00ee\u00b0sC\u00e3b\u00df?\u00ab\u00b6\u00a5\n\u00b9=\u000fd\u00ec\u00e0\u00c5\u00e7'\u00e83\u00b8\u0084P4\u00c0\u00ee\u00d6)'\u00d7\u008b\u001e";
                        var19_6 = 379;
                        var16_7 = 16;
                        var15_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var15_8;
                            v4 = var17_5.substring(v3, v3 + var16_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = TrackedPlayer.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            var17_5 = "S\u00edc\u0095\u0088M\u0002\u0016!\u00ff?\u00d0L\u00c3\u00ddt\u00f2]\u00d9\b\u00deBz`\u00e3\u00d6\u0088\u0080}\u001f\u00f8\u00c0\u00a1\u00a4x8\u00e3c\u00ff\u001e\u0010\u00f0\u00f1\u00a3\u00ce\u00e5G\u00f7\u00f9Z\u0000\u00ad\u00d4@J\u008f\u009b";
                            var19_6 = 57;
                            var16_7 = 40;
                            var15_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var15_8;
                                v4 = var17_5.substring(v6, v6 + var16_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var20_3[var18_4++] = TrackedPlayer.zkmDecodeUtf8(var21_9).intern();
                            if ((var15_8 += var16_7) < var19_6) {
                                var16_7 = var17_5.charAt(var15_8);
                                ** continue;
                            }
                            break block14;
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
                TrackedPlayer.b = var20_3;
                TrackedPlayer.d = new String[14];
                TrackedPlayer.h = new HashMap<K, V>(13);
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
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "f!\u00baW\u00b3T|`O\u00d9\u00bf.\u008b\u00e4;\u00e7";
                var5_15 = 16;
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
        TrackedPlayer.f = var6_12;
        TrackedPlayer.g = new Integer[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x635A;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/x4", exception);
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
            TrackedPlayer.d[n2] = TrackedPlayer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TrackedPlayer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/x4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x77D7;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/x4", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            TrackedPlayer.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = TrackedPlayer.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/x4" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TrackedPlayer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(TrackedPlayer.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

