/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.DisconnectEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.world.EntityRemove;
import su.catlean.gofra.Flow;
import su.catlean.module.basic.AntiKnockBack;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.TrackedPlayer;
import su.catlean.zkm.ZkmKeys;

public final class InterpolationTracker
implements Manager {
    @NotNull
    public static final InterpolationTracker INSTANCE;
    public static final double c = -0.0784;
    public static final double g = 0.2837;
    public static final double V = 0.08511;
    public static final double U = 0.45392000000000005;
    public static final double i = 0.056740000000000006;
    @NotNull
    private static final Map h;
    private static int[] d;
    private static final long a;
    private static final String[] b;
    private static final String[] e;
    private static final Map f;

    private InterpolationTracker() {
    }

    @NotNull
    public final Map K() {
        return h;
    }

    @Flow(priority=20)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        h.clear();
        for (AbstractClientPlayerEntity abstractClientPlayerEntity : Globals.getWorld().getPlayers()) {
            Integer n = abstractClientPlayerEntity.getId();
            Intrinsics.checkNotNull(abstractClientPlayerEntity);
            h.put(n, new TrackedPlayer(abstractClientPlayerEntity));
            TrackedPlayer trackedPlayer = (TrackedPlayer)h.get(abstractClientPlayerEntity.getId());
            if (trackedPlayer == null) continue;
            trackedPlayer.clearHistory();
        }
    }

    @Flow
    private final void onEntityRemove(EntityRemove entityRemove) {
        if (entityRemove.getEntity() != null) {
            Map map;
            Map map2 = map = h;
            synchronized (map2) {
                Entity entity = entityRemove.getEntity();
                Intrinsics.checkNotNull(entity);
            }
        }
    }

    @Flow
    private final void onDisconnect(DisconnectEvent disconnectEvent) {
        h.clear();
    }

    @NotNull
    public final synchronized Vec3d getPredictedCenter(@NotNull PlayerEntity playerEntity, int n) {
        Intrinsics.checkNotNullParameter(playerEntity, "player");
        Vec3d vec3d = this.getBox(playerEntity, n).getCenter().add(0.0, -playerEntity.getBoundingBox().getLengthY() / 2.0, 0.0);
        Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
        return vec3d;
    }

    @NotNull
    public final synchronized Box getBox(@NotNull PlayerEntity playerEntity, int n) {
        List list;
        Intrinsics.checkNotNullParameter(playerEntity, "player");
        if (!h.containsKey(playerEntity.getId()) || n == 0) {
            Box box = playerEntity.getBoundingBox();
            Intrinsics.checkNotNullExpressionValue(box, "getBoundingBox(...)");
            return box;
        }
        Object object = (TrackedPlayer)h.get(playerEntity.getId());
        List list2 = object != null && (object = ((TrackedPlayer)object).f()) != null ? (List)object : (list = CollectionsKt.emptyList());
        if (list.isEmpty()) {
            Box box = playerEntity.getBoundingBox();
            Intrinsics.checkNotNullExpressionValue(box, "getBoundingBox(...)");
            return box;
        }
        return (Box)list.get(Math.min(n, list.size() - 1));
    }

    @NotNull
    public final Vec3d predictPosition(@NotNull PlayerEntity playerEntity, int n) {
        Intrinsics.checkNotNullParameter(playerEntity, "entity");
        Vec3d vec3d = AntiKnockBack.INSTANCE.getPos(playerEntity);
        double d = playerEntity.getX() - playerEntity.lastX;
        double d2 = playerEntity.getZ() - playerEntity.lastZ;
        for (int i = 0; i < n; ++i) {
            float f;
            float f2 = d > 0.0 ? 0.3f : -0.3f;
            float f3 = f = d2 > 0.0 ? 0.3f : -0.3f;
            if (!Globals.getWorld().isAir(BlockPos.ofFloored(vec3d.add(d + (double)f2, 0.25, d2 + (double)f))) || !Globals.getWorld().isAir(BlockPos.ofFloored(vec3d.add(d + (double)f2, 1.0, d2 + (double)f)))) {
                d = 0.0;
                d2 = 0.0;
            }
            Intrinsics.checkNotNullExpressionValue(vec3d.add(d, 0.0, d2), "add(...)");
        }
        return vec3d;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                InterpolationTracker.a = ZkmKeys.a(7221140810902236983L, -1285106618695161538L, MethodHandles.lookup().lookupClass()).a(240828536777126L);
                var9 = InterpolationTracker.a ^ 2786623441804L;
                InterpolationTracker.f = new HashMap<K, V>(13);
                InterpolationTracker.zkmSetFlag(null);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[8];
                var5_4 = 0;
                var4_5 = "442qS;B\u008b\u00be\u0000\u00d2\n\u00d8\u00f0\u00a2\u0001\u00bb\u00a2\u00d8\u00033E\u00da\u0001\fL\u00e9\u00a1Fv\u0094+\n\u00b1+J\u00b1aj\u0099\u0010\u00f5E}\u008cV\u0085\u009c46(L\u00aft\u00e8EA(3b|w\u001f.{V\u0006\u00a8\u00d4\u00b6/W\u00c6,\u0011\u00ab\u00f4C\u008aP$_m\u00f7vc\u00d8\u00b63\u00fa\u009a\u0000\u001b\u00a1G1QI\u0018/\u0004v\u0087>\u008e5\u00ba$q\u0019\u00c6\u009dO\u001fYD\u001f\u0096d\u001au\u00ee(\u0018n\u00c6b5\u009d\u00d2\u009e\u00b1\u00ac\u00a7\u00aam\u00e8\u00df\t De\u00e5\u00fc\u00db\u00a9O\u00db(\u00cd%\u00ae\u00b0M \u00ed\u00a0\u009a\u00adS\u00c0\u0006w1\u00e5\u0016#\u00f0\u0007\u00efr\u0098P\u001e\u0018F\u00c1\u00027\u00eb\u00fc\u00ce\u00ad\u00efJY\u0083\u00d4g";
                var6_6 = 189;
                var3_7 = 40;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = InterpolationTracker.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u00b4\u0091\u0000\u00df\u0094\u00e8}.\u0017\u00a65\u00d0N\u0000 _\u0010\u00fa\u00e7\u00b8Z\u00ab\f'Dn\u0013\u00aa\u0082\u00ec\u00e6\u00ceL";
                    var6_6 = 33;
                    var3_7 = 16;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = InterpolationTracker.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        InterpolationTracker.b = var7_3;
        InterpolationTracker.e = new String[8];
        InterpolationTracker.INSTANCE = new InterpolationTracker();
        v7 = Collections.synchronizedMap(new HashMap<K, V>());
        Intrinsics.checkNotNullExpressionValue(v7, "synchronizedMap(...)");
        InterpolationTracker.h = v7;
    }

    public static void zkmSetFlag(int[] nArray) {
        d = nArray;
    }

    public static int[] zkmGetFlag() {
        return d;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6A42;
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
                throw new RuntimeException("su/catlean/lo", exception);
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
            InterpolationTracker.e[n2] = InterpolationTracker.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = InterpolationTracker.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/lo" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(InterpolationTracker.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

