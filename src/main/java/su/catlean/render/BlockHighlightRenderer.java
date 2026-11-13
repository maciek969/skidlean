/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import su.catlean.render.AnimationStyle;
import su.catlean.render.BlockHighlight;
import su.catlean.render.ShapeRenderMode;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class BlockHighlightRenderer {
    @NotNull
    public static final BlockHighlightRenderer INSTANCE;
    @NotNull
    private static final Map g;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private BlockHighlightRenderer() {
    }

    @NotNull
    public final Map B() {
        return g;
    }

    public final void renderAll(@NotNull MatrixStack matrixStack) {
        Intrinsics.checkNotNullParameter(matrixStack, "matrixStack");
        Map map = g;
        Iterator iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry entry;
            Map.Entry entry2 = entry = iterator2.next();
            BlockHighlight blockHighlight = (BlockHighlight)entry2.getKey();
            long l = ((Number)entry2.getValue()).longValue();
            if ((float)(Globals.now() - l) > 300.0f) {
                g.remove(blockHighlight);
                continue;
            }
            blockHighlight.render(Globals.now() - l, matrixStack);
        }
    }

    public final void add(@NotNull BlockPos blockPos, @NotNull Color color, @NotNull Color color2, @NotNull AnimationStyle animationStyle, @NotNull ShapeRenderMode shapeRenderMode) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 48 | (long)n2 << 48 >>> 16 | (long)n3 << 32 >>> 32) ^ a;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)BlockHighlightRenderer.zkmBootstrap("x", (int)23910, (long)(0x2EEC7D4962E403BEL ^ l))));
        CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-235881279989216244L, (long)l);
        Intrinsics.checkNotNullParameter(color, (String)((Object)BlockHighlightRenderer.zkmBootstrap("x", (int)2232, (long)(0x10F2FFEC53B8D666L ^ l))));
        Intrinsics.checkNotNullParameter(color2, (String)((Object)BlockHighlightRenderer.zkmBootstrap("x", (int)24935, (long)(0x5B7A71023D7A3FBEL ^ l))));
        Intrinsics.checkNotNullParameter((Object)animationStyle, (String)((Object)BlockHighlightRenderer.zkmBootstrap("x", (int)15804, (long)(0x6E5964468DB8E366L ^ l))));
        CallSite callSite2 = callSite;
        Intrinsics.checkNotNullParameter((Object)shapeRenderMode, (String)((Object)BlockHighlightRenderer.zkmBootstrap("x", (int)16471, (long)(0x2D685BFC9B0C9E8BL ^ l))));
        Object object = shapeRenderMode;
        if (callSite2 != false) {
            if (object == ShapeRenderMode.NONE) {
                return;
            }
            object = g.put(new BlockHighlight(blockPos, color, color2, animationStyle, shapeRenderMode), Globals.now());
        }
    }

    public final boolean contains(@NotNull BlockPos blockPos) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(blockPos, (String)((Object)BlockHighlightRenderer.zkmBootstrap("x", (int)15235, (long)(0x78FA8DD09186E4ECL ^ l))));
        return g.keySet().stream().anyMatch(arg_0 -> BlockHighlightRenderer.invokePredicate(arg_0 -> BlockHighlightRenderer.contains$lambda$0(blockPos, arg_0), arg_0));
    }

    private static final boolean contains$lambda$0(BlockPos blockPos, BlockHighlight blockHighlight) {
        return Intrinsics.areEqual(blockHighlight.getPos(), blockPos);
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                BlockHighlightRenderer.a = ZkmKeys.a(-6607130875288731993L, -4856352360410586874L, MethodHandles.lookup().lookupClass()).a(157949681415734L);
                BlockHighlightRenderer.d = new HashMap<K, V>(13);
                var0 = BlockHighlightRenderer.a ^ 72630649767131L;
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
                var9_3 = new String[7];
                var7_4 = 0;
                var6_5 = "\u00b4\u00c3\u0003\u0095\u00e1\u00ba\u001d\u00b4R\u00fbs\u00d5t\u0088\u00ab\u0094\u009cj\u0004#}k\u00da[\u00e1\u00d6M8/\u00de\u00cb\u001b \u0093dvM:\u009e\u0095nKA\u00b0\u00d4f \u0019\u00de\u00d64c\u00df\n\u001c\u00d6k\u00c1[\u00d7t\u00e2\u008c+\u00c2\u00108+d3\u00df\u00f5\u00f3?\u00efA\u00c4\u00ca\u0083\u00cd\u0019\u00de\u0018D\u0005\u00c7\u00ef\u00cdl\u00bf\u00aa}V\u00bb\u0003\u00b7\u00b6!\u00d1hd\u00ea\u00b1\f\u00b8\u00d1\u00af \u00d3\u009c\u00ea`>\u00edR\u00da`eh&\u00ae[\u0016\u00f4\f\u00c4\u00b4\u0090\u00b6eY]\u00af\u001aU\u0085\u00e20\u00f1\u0016";
                var8_6 = 140;
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
                    var9_3[var7_4++] = BlockHighlightRenderer.zkmDecodeUtf8(var10_9).intern();
                    if ((var4_8 += var5_7) < var8_6) {
                        var5_7 = var6_5.charAt(var4_8);
                        ** continue;
                    }
                    var6_5 = "8\u0098\u00c1\u009b\u0005\u00d4\u00c9B\u00db\u00e78t\u0004\u00d4\u00a7\u00cb\u0018\u00a8*\u0017'\u00dcp\u00d8\u00be\u00d3Y}\u001f\u0092\u0082\u0000K'>,\u00e6\u000fp[\u0011";
                    var8_6 = 41;
                    var5_7 = 16;
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
                    var9_3[var7_4++] = BlockHighlightRenderer.zkmDecodeUtf8(var10_9).intern();
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
        BlockHighlightRenderer.b = var9_3;
        BlockHighlightRenderer.c = new String[7];
        BlockHighlightRenderer.INSTANCE = new BlockHighlightRenderer();
        BlockHighlightRenderer.g = new ConcurrentHashMap<K, V>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x23E4;
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
                throw new RuntimeException("su/catlean/nz", exception);
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
            BlockHighlightRenderer.c[n2] = BlockHighlightRenderer.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BlockHighlightRenderer.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nz" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BlockHighlightRenderer.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

