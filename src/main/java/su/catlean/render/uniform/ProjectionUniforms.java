/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class ProjectionUniforms
implements DynamicUniformStorage.Uploadable {
    @NotNull
    private final Matrix4f projection;
    @Nullable
    private final Matrix4f view;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public ProjectionUniforms(@NotNull Matrix4f matrix4f, @Nullable Matrix4f matrix4f2) {
        Intrinsics.checkNotNullParameter(matrix4f, "projection");
        this.projection = matrix4f;
        this.view = matrix4f2;
    }

    @NotNull
    public final Matrix4f getProjection() {
        return this.projection;
    }

    @Nullable
    public final Matrix4f getView() {
        return this.view;
    }

    @Override
    public void write(@NotNull ByteBuffer byteBuffer) {
        long l = a ^ 0xD9A94587D15L;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-8103537578194082892L, (long)l);
        Intrinsics.checkNotNullParameter(byteBuffer, "buffer");
        CallSite callSite2 = callSite;
        Std140Builder std140Builder = Std140Builder.intoBuffer(byteBuffer).putMat4f((Matrix4fc)this.projection);
        Matrix4f matrix4f = this.view;
        if (callSite2 == null) {
            Matrix4f matrix4f2 = matrix4f;
            if (matrix4f == null) {
                matrix4f2 = new Matrix4f();
            }
            matrix4f = (Matrix4fc)matrix4f2;
        }
        std140Builder.putMat4f((Matrix4fc)matrix4f);
    }

    @NotNull
    public final Matrix4f getProjection2() {
        return this.projection;
    }

    @Nullable
    public final Matrix4f getView2() {
        return this.view;
    }

    @NotNull
    public final ProjectionUniforms copy(@NotNull Matrix4f matrix4f, @Nullable Matrix4f matrix4f2) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(matrix4f, (String)((Object)ProjectionUniforms.zkmBootstrap("p", (int)13176, (long)(0x3866FBDB57FB8E87L ^ l))));
        return new ProjectionUniforms(matrix4f, matrix4f2);
    }

    public static ProjectionUniforms copy$default(ProjectionUniforms projectionUniforms, Matrix4f matrix4f, Matrix4f matrix4f2, int n, Object object) {
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        long l = ((long)n2 << 32 | (long)n3 << 48 >>> 32 | (long)n4 << 48 >>> 48) ^ a;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)6734420288683290955L, (long)l);
        int n5 = n & 1;
        if (callSite == null) {
            if (n5 != 0) {
                matrix4f = projectionUniforms.projection;
            }
            n5 = n & 2;
        }
        if (n5 != 0) {
            matrix4f2 = projectionUniforms.view;
        }
        return projectionUniforms.copy(matrix4f, matrix4f2);
    }

    @NotNull
    public String toString() {
        return "UniformData(projection=" + this.projection + ", view=" + this.view + ")";
    }

    /*
     * Unable to fully structure code
     */
    public int hashCode() {
        var1_1 = ProjectionUniforms.a ^ 125868162296540L;
        var4_2 = this.projection.hashCode();
        var3_3 = ZkmReferences.zkmBootstrap("\u00c5", (long)-2863431320196598659L, (long)var1_1);
        v0 = var4_2 * (int)ProjectionUniforms.e;
        v1 = this.view;
        if (var3_3 != null) ** GOTO lbl11
        if (v1 == null) {
            v2 = 0;
        } else {
            v1 = this.view;
lbl11:
            // 2 sources

            v2 = v1.hashCode();
        }
        var4_2 = v0 + v2;
        return var4_2;
    }

    public boolean equals(@Nullable Object object) {
        long l = a ^ 0x4ED8F72E16ADL;
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-2003485267318536180L, (long)l);
        Object object2 = this;
        if (callSite == null) {
            if (object2 == object) {
                return true;
            }
            object2 = object;
        }
        if (callSite == null) {
            if (!(object2 instanceof ProjectionUniforms)) {
                return false;
            }
            object2 = object;
        }
        ProjectionUniforms projectionUniforms = (ProjectionUniforms)object2;
        boolean bl = Intrinsics.areEqual(this.projection, projectionUniforms.projection);
        if (callSite == null) {
            if (!bl) {
                return false;
            }
            bl = Intrinsics.areEqual(this.view, projectionUniforms.view);
        }
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
        block14: {
            block13: {
                block12: {
                    ProjectionUniforms.a = ZkmKeys.a(2645781390117145826L, 3786156340651080042L, MethodHandles.lookup().lookupClass()).a(262146235560138L);
                    ProjectionUniforms.d = new HashMap<K, V>(13);
                    var5 = ProjectionUniforms.a ^ 98798242784007L;
                    var7_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    v0 = SecretKeyFactory.getInstance("DES");
                    v1 = new byte[8];
                    v2 = v1;
                    v1[0] = (byte)(var5 >>> 56);
                    for (var8_2 = 1; var8_2 < 8; ++var8_2) {
                        v2 = v2;
                        v2[var8_2] = (byte)(var5 << var8_2 * 8 >>> 56);
                    }
                    var7_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                    var14_3 = new String[5];
                    var12_4 = 0;
                    var11_5 = "\u00ea\u008a\u0013Q\u00a0\f\u009ad\u00a1\u00d7\u00ec\u00bc-f\u00d4\u008d \u00ce\u00e4&\u0002~\u008e\u00b7\u008e\u00c5\u00f6\u0015[\u00e77\u008f\u00e5u\u0016E\u00b1\u0095\u00d8\u0018q\u00c7\u00eb\u0090&\u00f6(\u00ea\u00d3\u0018\u00a2k.$.=m\u00aa\u000fd\u00a7\u00c7\"{\u00deF\u00f2\u001d\u0086\u00b5i\u00cc\u008c\u0007";
                    var13_6 = 74;
                    var10_7 = 16;
                    var9_8 = -1;
lbl20:
                    // 2 sources

                    while (true) {
                        v3 = ++var9_8;
                        v4 = var11_5.substring(v3, v3 + var10_7);
                        v5 = -1;
                        break block12;
                        break;
                    }
lbl25:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = ProjectionUniforms.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        var11_5 = "\u0006|1Zv\u00c1\u00daS\u00d0\u00c0\r\u00b1-6'\u007f\u0089\u00e8\u00f9:\u00d6\u00b2\u00db\u009c(\u00b1\\8z\u00d4\u00cdp\u009f\u00a8\u00d7V$Hz>\u0010\u00fc\u0004\u00ff\u00aa\u00cc\u0013\u00ba\u00c9C!\u0089\u00f4;-\u0019\u009b";
                        var13_6 = 57;
                        var10_7 = 40;
                        var9_8 = -1;
lbl34:
                        // 2 sources

                        while (true) {
                            v6 = ++var9_8;
                            v4 = var11_5.substring(v6, v6 + var10_7);
                            v5 = 0;
                            break block12;
                            break;
                        }
                        break;
                    }
lbl39:
                    // 1 sources

                    while (true) {
                        var14_3[var12_4++] = ProjectionUniforms.zkmDecodeUtf8(var15_9).intern();
                        if ((var9_8 += var10_7) < var13_6) {
                            var10_7 = var11_5.charAt(var9_8);
                            ** continue;
                        }
                        break block13;
                        break;
                    }
                }
                var15_9 = var7_1.doFinal(v4.getBytes("ISO-8859-1"));
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
            ProjectionUniforms.b = var14_3;
            ProjectionUniforms.c = new String[5];
            var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
            v7 = SecretKeyFactory.getInstance("DES");
            v8 = new byte[8];
            v9 = v8;
            v8[0] = (byte)(var5 >>> 56);
            for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                v9 = v9;
                v9[var1_11] = (byte)(var5 << var1_11 * 8 >>> 56);
            }
            break block14;
lbl65:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
        var4_12 = var0_10.doFinal(new byte[]{106, -3, -39, 99, -126, -45, 127, -29});
        ** while (true)
        ProjectionUniforms.e = ((long)var4_12[0] & 255L) << 56 | ((long)var4_12[1] & 255L) << 48 | ((long)var4_12[2] & 255L) << 40 | ((long)var4_12[3] & 255L) << 32 | ((long)var4_12[4] & 255L) << 24 | ((long)var4_12[5] & 255L) << 16 | ((long)var4_12[6] & 255L) << 8 | (long)var4_12[7] & 255L;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1BDC;
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
                throw new RuntimeException("su/catlean/zd", exception);
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
            ProjectionUniforms.c[n2] = ProjectionUniforms.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ProjectionUniforms.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zd" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ProjectionUniforms.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

