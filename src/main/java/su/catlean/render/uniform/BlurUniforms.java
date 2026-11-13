/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render.uniform;

import com.mojang.blaze3d.buffers.Std140Builder;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.ByteBuffer;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gl.DynamicUniformStorage;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.module.esp.StorageEsp;
import su.catlean.render.RenderUtil;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BlurUniforms
implements DynamicUniformStorage.Uploadable {
    @NotNull
    private final Vec2f halfTexelSize;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public BlurUniforms(@NotNull Vec2f vec2f) {
        Intrinsics.checkNotNullParameter(vec2f, "halfTexelSize");
        this.halfTexelSize = vec2f;
    }

    @NotNull
    public final Vec2f getHalfTexelSize() {
        return this.halfTexelSize;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        Std140Builder.intoBuffer(byteBuffer).putVec2(this.halfTexelSize.x, this.halfTexelSize.y).putFloat(Math.abs(RenderUtil.INSTANCE.lerp((float)(Globals.getPlayer().age - 1) / 48.0f, (float)Globals.getPlayer().age / 48.0f, RenderUtil.INSTANCE.getTickDelta()))).putInt(StorageEsp.INSTANCE.getShaderMode().ordinal());
    }

    @NotNull
    public final Vec2f getHalfTexelSize2() {
        return this.halfTexelSize;
    }

    @NotNull
    public final BlurUniforms copy(@NotNull Vec2f vec2f) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 32 >>> 16 | (long)n3 << 48 >>> 48) ^ a;
        Intrinsics.checkNotNullParameter(vec2f, (String)((Object)BlurUniforms.zkmBootstrap("a", (int)15358, (long)(0x5446D380E41C3291L ^ l))));
        return new BlurUniforms(vec2f);
    }

    public static BlurUniforms copy$default(BlurUniforms blurUniforms, Vec2f vec2f, int n, Object object) {
        if ((n & 1) != 0) {
            vec2f = blurUniforms.halfTexelSize;
        }
        return blurUniforms.copy(vec2f);
    }

    @NotNull
    public String toString() {
        return "UniformData(halfTexelSize=" + this.halfTexelSize + ")";
    }

    public int hashCode() {
        return this.halfTexelSize.hashCode();
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x325C2F705BB9L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-5200986052390396948L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof BlurUniforms)) {
                return false;
            }
            object2 = object;
        }
        BlurUniforms blurUniforms = (BlurUniforms)object2;
        boolean bl = Intrinsics.areEqual(this.halfTexelSize, blurUniforms.halfTexelSize);
        if (callSite == null) {
            if (!bl) {
                return false;
            }
            bl = true;
        }
        return bl;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                BlurUniforms.a = ZkmKeys.a(-473017441815266072L, 7835919563790592020L, MethodHandles.lookup().lookupClass()).a(238673575230103L);
                BlurUniforms.d = new HashMap<K, V>(13);
                var0 = BlurUniforms.a ^ 49391403548669L;
                var2_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var0 >>> 56);
                for (var3_2 = 1; var3_2 < 8; ++var3_2) {
                    v2 = v2;
                    v2[var3_2] = (byte)(var0 << var3_2 * 8 >>> 56);
                }
                var2_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var9_3 = new String[4];
                var7_4 = 0;
                var6_5 = "\u00be\u00fd\u0083;\u00ad\u00cb\u001cf\u00e6\u0014\u0016@\u0011H27fP\u00df\u00ec*\u00bf\u00c0\u00b2\u00bd\u00af\u00ecr[m\\\u00dd\u0010\u0097\u00c7\u001e\u0005@)\u0007\u00f2^\u00a6\u000f(D\u00e8\u00d5)";
                var8_6 = 49;
                var5_7 = 32;
                var4_8 = -1;
lbl20:
                // 2 sources

                while (true) {
                    v3 = ++var4_8;
                    v4 = var6_5.substring(v3, v3 + var5_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl25:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = BlurUniforms.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "l\u00a6<\u00f4\u00e9kM\u00ef\u0015\u009c\u00e8\u008c\u009c\u00a7v\u0001\u008b\u00ba\u00c6\n\u00e6+\u00d0F}k\u00a8\u00b8-r\t\u00c48\u001f\u00df8\u00bd\u00d1\u00f2\u0018\u0096\u00f9\u00e0 \u009c\u00ca\u00d6\u0083H\u00ef?b\u000b\u00be\u00e1\u0083:\u00aa\u00f4u\u009f\nf\u00bc\u00cc\u0001@\u001d\u00be<>\u00c3\u00ebMl\u008f\u00f6\u0087\u0002D\u008ff\u00b3\u00b1\u0097\u00a5\u00d0u\u0014";
                    var8_6 = 89;
                    var5_7 = 32;
                    var4_8 = -1;
lbl34:
                    // 2 sources

                    while (true) {
                        v6 = ++var4_8;
                        v4 = var6_5.substring(v6, v6 + var5_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl39:
                // 1 sources

                while (true) {
                    var9_3[var7_4++] = BlurUniforms.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var10_9 = var2_1.doFinal(v4.getBytes("ISO-8859-1"));
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
        BlurUniforms.b = var9_3;
        BlurUniforms.c = new String[4];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x176D;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])d.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/z_", exception);
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
            BlurUniforms.c[n2] = BlurUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BlurUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/z_" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BlurUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

