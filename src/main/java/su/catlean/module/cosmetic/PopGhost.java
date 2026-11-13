/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.cosmetic;

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
import kotlin.ranges.RangesKt;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.zkm.ZkmKeys;

public final class PopGhost {
    @NotNull
    private final PlayerEntityModel model;
    @NotNull
    private final PlayerEntityRenderState state;
    @NotNull
    private Vec3d pos;
    @NotNull
    private final Identifier texture;
    private float alpha;
    private float prevAlpha;
    @NotNull
    private Vec3d prevPos;
    private static String[] N;
    private static final long c;
    private static final String[] d;
    private static final String[] e;
    private static final Map f;

    public PopGhost(@NotNull PlayerEntityModel playerEntityModel, @NotNull PlayerEntityRenderState playerEntityRenderState, @NotNull Vec3d vec3d, @NotNull Identifier identifier, float f) {
        Intrinsics.checkNotNullParameter(playerEntityModel, "model");
        Intrinsics.checkNotNullParameter(playerEntityRenderState, "state");
        Intrinsics.checkNotNullParameter(vec3d, "pos");
        Intrinsics.checkNotNullParameter(identifier, "texture");
        this.model = playerEntityModel;
        this.state = playerEntityRenderState;
        this.pos = vec3d;
        this.texture = identifier;
        this.prevAlpha = this.alpha = f;
        this.prevPos = this.pos;
    }

    @NotNull
    public final PlayerEntityModel getModel() {
        return this.model;
    }

    @NotNull
    public final PlayerEntityRenderState getState() {
        return this.state;
    }

    @NotNull
    public final Vec3d getPos() {
        return this.pos;
    }

    public final void setPos(@NotNull Vec3d vec3d) {
        long l = 0L;
        l = c ^ l;
        Intrinsics.checkNotNullParameter(vec3d, (String)((Object)PopGhost.zkmBootstrap("o", (int)13694, (long)(0x634D250856EAE759L ^ l))));
        this.pos = vec3d;
    }

    @NotNull
    public final Identifier getTexture() {
        return this.texture;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final boolean update(float f, float f2) {
        if (this.alpha <= 0.0f) {
            return true;
        }
        this.prevAlpha = this.alpha;
        this.alpha -= f;
        this.prevPos = this.pos;
        this.pos = new Vec3d(this.pos.x, this.pos.y + (double)f2, this.pos.z);
        return false;
    }

    public final float getInterpolatedAlpha() {
        return RangesKt.coerceIn(RenderUtil.INSTANCE.lerp(this.prevAlpha, this.alpha, RenderState.INSTANCE.getTickDelta()), 0.0f, 255.0f) / 255.0f;
    }

    @NotNull
    public final Vec3d getInterpolatedPos() {
        float f = RenderState.INSTANCE.getTickDelta();
        Vec3d vec3d = this.pos;
        Vec3d vec3d2 = this.prevPos;
        return RenderState.INSTANCE.lerpVec(vec3d2, vec3d, f);
    }

    public static void zkmSetFlag(String[] stringArray) {
        N = stringArray;
    }

    public static String[] zkmGetFlag() {
        return N;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                PopGhost.c = ZkmKeys.a(-2108292055991162960L, -4210892576235190131L, MethodHandles.lookup().lookupClass()).a(40802403328362L);
                var9 = PopGhost.c ^ 3360678718998L;
                PopGhost.f = new HashMap<K, V>(13);
                PopGhost.zkmSetFlag(new String[1]);
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
                var7_3 = new String[5];
                var5_4 = 0;
                var4_5 = "k\u0016\u00aa\u00f9b\u00b1\u00a9\u00f0f\u00f4\u00f8\u00cd\u0083R\u00d88\u0010\u00f2 \u0011[=h\u0086\u00b1^\u001c\u00dcj\u00d5\u00c4u\u00ce\u0010}\u001c\u00da\u0093N\u00dc\u0012\u00d8\u00e6\u009c\u008a\u00ba\u00dd\u00c1(\u0087";
                var6_6 = 50;
                var3_7 = 16;
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
                    var7_3[var5_4++] = PopGhost.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "y\u00b6<\u0082\u00f9t;\u00a2\u0095\u00f8U\u00b8\u00a9\u0083\u0005\u0084\u0010\u000fw\u00adQ\u00a8\u00a4Z\u00d2\u00ad\u00f3\u00b4\u001c\u0002\u0001\u000fm";
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
                    var7_3[var5_4++] = PopGhost.zkmDecodeUtf8(var8_9).intern();
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
        PopGhost.d = var7_3;
        PopGhost.e = new String[5];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6BE0;
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
                throw new RuntimeException("su/catlean/tk", exception);
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
            PopGhost.e[n2] = PopGhost.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return e[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PopGhost.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/tk" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PopGhost.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

