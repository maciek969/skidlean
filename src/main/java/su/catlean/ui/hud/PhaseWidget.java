/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Matrix3x2fStack
 */
package su.catlean.ui.hud;

import java.awt.Color;
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
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import su.catlean.render.RenderUtil;
import su.catlean.render.Theme;
import su.catlean.ui.ArrayListEntry;
import su.catlean.ui.hud.HudEditorScreen;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.EntityUtil;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class PhaseWidget
extends HudElement {
    @NotNull
    public static final PhaseWidget S;
    @Nullable
    private static BlockPos blockPos;
    private static boolean q;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;

    private PhaseWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x1F2645495985L;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "phase-widget", (char)n2);
    }

    @Nullable
    public final BlockPos getBlockPos() {
        return blockPos;
    }

    public final void setBlockPos(@Nullable BlockPos blockPos) {
        PhaseWidget.blockPos = blockPos;
    }

    public final boolean H() {
        return q;
    }

    public final void J(boolean bl) {
        q = bl;
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        long l2 = l ^ 0x79539B3B89EBL;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        float f = 50.0f;
        if (blockPos == null) {
            return;
        }
        double d = RenderUtil.INSTANCE.lerp(Globals.getPlayer().lastX, Globals.getPlayer().getX(), RenderUtil.INSTANCE.getTickDelta());
        double d2 = RenderUtil.INSTANCE.lerp(Globals.getPlayer().lastZ, Globals.getPlayer().getZ(), RenderUtil.INSTANCE.getTickDelta());
        BlockPos blockPos = PhaseWidget.blockPos;
        Intrinsics.checkNotNull(blockPos);
        float f2 = (float)blockPos.getX() + 0.5f;
        BlockPos blockPos2 = PhaseWidget.blockPos;
        Intrinsics.checkNotNull(blockPos2);
        float f3 = (float)blockPos2.getZ() + 0.5f;
        float f4 = ((float)d - f2 + 0.2f) * f;
        float f5 = ((float)d2 - f3 + 0.2f) * f;
        drawContext.getMatrices().pushMatrix();
        drawContext.getMatrices().rotateAbout((float)Math.toRadians((double)(-Globals.getPlayer().getYaw()) + 180.0), this.getX() + 25.0f, this.getY() + 25.0f);
        float f6 = this.getX() + f4;
        float f7 = this.getY() + f5;
        Color color = !q ? Theme.INSTANCE.getTextColor() : Color.RED;
        RenderUtil.INSTANCE.drawRect(drawContext.getMatrices(), this.getX(), this.getY(), f, f, l2, Theme.INSTANCE.getTextColor());
        Matrix3x2fStack matrix3x2fStack = drawContext.getMatrices();
        Intrinsics.checkNotNull(color);
        RenderUtil.INSTANCE.drawRect(matrix3x2fStack, f6, f7, 30.000002f, 30.000002f, l2, color);
        drawContext.getMatrices().popMatrix();
        this.setSize(f, f);
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void tick() {
        block5: {
            v0 = ArrayListEntry.u();
            v1 = Globals.getPlayer().getBoundingBox();
            Intrinsics.checkNotNullExpressionValue(v1, "getBoundingBox(...)");
            var10_1 = v1;
            PhaseWidget.q = false;
            var9_2 = v0;
            v2 = Globals.getMc().currentScreen instanceof ChatScreen;
            if (var9_2) break block5;
            if (v2) ** GOTO lbl-1000
            v2 = Globals.getMc().currentScreen instanceof HudEditorScreen;
        }
        if (v2) lbl-1000:
        // 2 sources

        {
            v3 = BlockPos.ofFloored(EntityUtil.getPos(Globals.getPlayer()));
        } else {
            v3 = null;
        }
        PhaseWidget.blockPos = v3;
        var13_3 = new BlockPos[4];
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(var10_1.minX, var10_1.minY + 0.3, var10_1.minZ), "containing(...)");
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(var10_1.minX, var10_1.minY + 0.3, var10_1.maxZ - 9.999999747378752E-6), "containing(...)");
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(var10_1.maxX - 9.999999747378752E-6, var10_1.minY + 0.3, var10_1.minZ), "containing(...)");
        Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(var10_1.maxX - 9.999999747378752E-6, var10_1.minY + 0.3, var10_1.maxZ - 9.999999747378752E-6), "containing(...)");
        var12_4 = CollectionsKt.listOf(var13_3);
        for (BlockPos var14_5 : var12_4) {
            v4 = Globals.getWorld().getBlockState(var14_5).isReplaceable();
            if (!var9_2) {
                if (!v4) {
                    PhaseWidget.blockPos = var14_5;
                    if (!var9_2) continue;
                }
                v4 = PhaseWidget.q = true;
            }
            if (!var9_2) continue;
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                PhaseWidget.a = ZkmKeys.a(5877506847525410344L, -5635231647439142756L, MethodHandles.lookup().lookupClass()).a(175790408166648L);
                var9 = PhaseWidget.a ^ 48118335932855L;
                var11_1 = var9 ^ 121267140827833L;
                PhaseWidget.f = new HashMap<K, V>(13);
                var0_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_3 = 1; var1_3 < 8; ++var1_3) {
                    v2 = v2;
                    v2[var1_3] = (byte)(var9 << var1_3 * 8 >>> 56);
                }
                var0_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_4 = new String[5];
                var5_5 = 0;
                var4_6 = "\u00a6b\u008a\u00a6\u00acF\u00af\u00c0%\u0086\u0082Fn'y\u00c9(TfkFG\u00f5\u00aa\u00cfi\u00d5\u00ea\u0086\u000f\u00ac\u00bfI=b\u00f4\u0016\u0005-u \u00c9\u00cf\u0086#\"\u00fa\u00b3\u00f2+\u008c\u008b\u00a1i\u00bf1j\u0018\u00011\u00bcW\u00c3G\u00adSr\u00e5P\u00c1\u008d4\u00aa4\u00ca\u00f57\u00dd+[\n'";
                var6_7 = 82;
                var3_8 = 16;
                var2_9 = -1;
lbl22:
                // 2 sources

                while (true) {
                    v3 = ++var2_9;
                    v4 = var4_6.substring(v3, v3 + var3_8);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl27:
                // 1 sources

                while (true) {
                    var7_4[var5_5++] = PhaseWidget.zkmDecodeUtf8(var8_10).intern();
                    if ((var2_9 += var3_8) < var6_7) {
                        var3_8 = var4_6.charAt(var2_9);
                        ** continue;
                    }
                    var4_6 = "\u00f0gX\u00d61Z\u00b8\u00cc3\u001a\u009b'b\u00a6\u0084\u00d7\u00954\u00bc\u0085\u0084\u009c\u008e\u00cd8\u001a&?#YK\u00cf \u00b9q\u0097\b\u00dc4\u00d43\\tg{\u0098\u0012\u009f\u00ff2\u00d0N\u00ceVn\u0010\u00ed\u00b1\u00e4M\u00b7\u0017P\u0083Z";
                    var6_7 = 65;
                    var3_8 = 32;
                    var2_9 = -1;
lbl36:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_9;
                        v4 = var4_6.substring(v6, v6 + var3_8);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl41:
                // 1 sources

                while (true) {
                    var7_4[var5_5++] = PhaseWidget.zkmDecodeUtf8(var8_10).intern();
                    if ((var2_9 += var3_8) < var6_7) {
                        var3_8 = var4_6.charAt(var2_9);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_10 = var0_2.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl53:
                // 1 sources

                ** continue;
            }
        }
        PhaseWidget.b = var7_4;
        PhaseWidget.c = new String[5];
        PhaseWidget.S = new PhaseWidget(var11_1);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1D51;
        if (c[n2] == null) {
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
                throw new RuntimeException("su/catlean/p8", exception);
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
            PhaseWidget.c[n2] = PhaseWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PhaseWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/p8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PhaseWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

