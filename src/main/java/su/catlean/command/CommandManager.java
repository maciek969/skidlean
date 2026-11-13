/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  org.joml.Matrix3x2fStack
 *  org.joml.Quaternionfc
 */
package su.catlean.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.awt.Color;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix3x2fStack;
import org.joml.Quaternionfc;
import su.catlean.api.event.events.client.InputSuggestorEvent;
import su.catlean.api.event.events.network.SendMessageEvent;
import su.catlean.api.event.events.render.Render2DEvent;
import su.catlean.command.AngleCommand;
import su.catlean.command.BindCommand;
import su.catlean.command.CfgCommand;
import su.catlean.command.Command;
import su.catlean.command.EasingsdebugCommand;
import su.catlean.command.FixallCommand;
import su.catlean.command.FriendCommand;
import su.catlean.command.GpsCommand;
import su.catlean.gofra.Flow;
import su.catlean.module.esp.Tracers;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.render.Shapes2D;
import su.catlean.render.Theme;
import su.catlean.render.font.FontRenderer;
import su.catlean.render.font.Fonts;
import su.catlean.util.Globals;
import su.catlean.util.Manager;
import su.catlean.util.MovementUtil;
import su.catlean.zkm.ZkmKeys;

public final class CommandManager
implements Manager {
    @NotNull
    public static final CommandManager INSTANCE;
    @NotNull
    private static String o;
    @Nullable
    private static BlockPos blockPos;
    @NotNull
    private static final List R;
    @NotNull
    private static final CommandDispatcher commandDispatcher;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private CommandManager() {
    }

    @NotNull
    public final String Z() {
        return o;
    }

    public final void execute(@NotNull String string) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        long l = ((long)n << 32 | (long)n2 << 56 >>> 32 | (long)n3 << 40 >>> 40) ^ a;
        Intrinsics.checkNotNullParameter(string, (String)((Object)CommandManager.zkmBootstrap("d", (int)17865, (long)(0x66AB705A9A6E4130L ^ l))));
        o = string;
    }

    @Nullable
    public final BlockPos getBlockPos() {
        return blockPos;
    }

    public final void setBlockPos(@Nullable BlockPos blockPos) {
        CommandManager.blockPos = blockPos;
    }

    @NotNull
    public final List d() {
        return R;
    }

    @NotNull
    public final CommandDispatcher getCommandDispatcher() {
        return commandDispatcher;
    }

    private final void add(Command command) {
        command.register(commandDispatcher);
        R.add(command);
    }

    @Flow
    private final void onSendMessage(SendMessageEvent sendMessageEvent) {
        if (StringsKt.startsWith$default(sendMessageEvent.getMessage(), o, false, 2, null)) {
            try {
                String string = sendMessageEvent.getMessage().substring(o.length());
                Intrinsics.checkNotNullExpressionValue(string, "substring(...)");
                ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
                Intrinsics.checkNotNull(clientPlayNetworkHandler);
                commandDispatcher.execute(string, (Object)clientPlayNetworkHandler.getCommandSource());
            }
            catch (CommandSyntaxException commandSyntaxException) {}
            sendMessageEvent.cancel();
        }
    }

    @Flow
    private final void onInputSuggestor(InputSuggestorEvent inputSuggestorEvent) {
        ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
        Intrinsics.checkNotNull(clientPlayNetworkHandler);
        inputSuggestorEvent.setSource(clientPlayNetworkHandler.getCommandSource());
        inputSuggestorEvent.setPrefix(o);
        inputSuggestorEvent.setDispatcher((CommandDispatcher<CommandSource>)commandDispatcher);
        inputSuggestorEvent.cancel();
    }

    @Flow
    private final void onRender2D(Render2DEvent render2DEvent) {
        long l;
        long l2 = l = a ^ 0x42F344234A8AL;
        long l3 = l2 ^ 0x255C3473FB8L;
        int n = (int)(l3 >>> 48);
        int n2 = (int)(l3 << 16 >>> 48);
        int n3 = (int)(l3 << 32 >>> 32);
        long l4 = l2 ^ 0x6CE8D30C8239L;
        long l5 = l2 ^ 0x7D7D7B9F5FCDL;
        long l6 = l2 ^ 0x257FB77580BCL;
        int n4 = (int)(l6 >>> 32);
        int n5 = (int)(l6 << 32 >>> 40);
        int n6 = (int)(l6 << 56 >>> 56);
        if (blockPos != null) {
            float f = (float)Globals.getMc().getWindow().getScaledWidth() / 2.0f;
            float f2 = 40.0f;
            MatrixStack matrixStack = new MatrixStack();
            matrixStack.push();
            matrixStack.translate(f, f2, 0.0f);
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(90.0f / Math.abs(90.0f / Math.clamp(55.0f, Globals.getPlayer().getPitch(), 90.0f)) - 102.0f));
            matrixStack.translate(-f, -40.0f, 0.0f);
            matrixStack.push();
            double d = RenderUtil.INSTANCE.lerp(Globals.getPlayer().lastX, Globals.getPlayer().getX(), (double)RenderState.INSTANCE.getTickDelta());
            double d2 = RenderUtil.INSTANCE.lerp(Globals.getPlayer().lastZ, Globals.getPlayer().getZ(), (double)RenderState.INSTANCE.getTickDelta());
            BlockPos blockPos = CommandManager.blockPos;
            Intrinsics.checkNotNull(blockPos);
            double d3 = (double)blockPos.getZ() - d2;
            BlockPos blockPos2 = CommandManager.blockPos;
            Intrinsics.checkNotNull(blockPos2);
            float f3 = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(d3, (double)blockPos2.getX() - d)) - 90.0) - Globals.getPlayer().getYaw();
            matrixStack.translate(f, f2, 0.0f);
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(f3));
            matrixStack.translate(-f, -40.0f, 0.0f);
            Tracers.INSTANCE.drawTracer(n4, n5, matrixStack, f, (byte)n6, f2, 32.0f, Theme.INSTANCE.getGradientColor(0));
            matrixStack.translate(f, f2, 0.0f);
            matrixStack.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(-f3));
            matrixStack.translate(-f, -40.0f, 0.0f);
            matrixStack.pop();
            matrixStack.pop();
            BlockPos blockPos3 = CommandManager.blockPos;
            Intrinsics.checkNotNull(blockPos3);
            double d4 = blockPos3.toCenterPos().x;
            BlockPos blockPos4 = CommandManager.blockPos;
            Intrinsics.checkNotNull(blockPos4);
            String string = (int)MovementUtil.INSTANCE.horizontalDistance(d4, blockPos4.toCenterPos().z) + "m";
            float f4 = Fonts.getSF_M().getWidth(l4, string);
            Matrix3x2fStack matrix3x2fStack = render2DEvent.getContext().getMatrices();
            Intrinsics.checkNotNullExpressionValue(matrix3x2fStack, "pose(...)");
            Shapes2D.drawShape$default(matrix3x2fStack, (char)n, f - f4 / 2.0f - 2.0f, 56.0f, f4 + 4.0f, 11.0f, 3.0f, 1.5f, 0.0f, 1.0f, Theme.INSTANCE.getWidgetBlurColor(), Theme.INSTANCE.getOutlineColor(), 0.0f, (short)n2, 0.0f, n3, 0.0f, 14336, null);
            FontRenderer fontRenderer = Fonts.getSF_M();
            DrawContext drawContext = render2DEvent.getContext();
            Color color = Color.WHITE;
            Intrinsics.checkNotNullExpressionValue(color, "WHITE");
            fontRenderer.drawStringCentered(drawContext, string, f, 60.0f, color, l5);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        CommandManager.a = ZkmKeys.a(-121892667972105778L, 725839728142484046L, MethodHandles.lookup().lookupClass()).a(180540112065643L);
                        var20 = CommandManager.a ^ 138495540677814L;
                        CommandManager.d = new HashMap<K, V>(13);
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
                        var18_3 = new String[4];
                        var16_4 = 0;
                        var15_5 = "\u00f5V\u0095\u00fb\u001a\u0010\u00a8\u009c9\u0016\u0086\u00f0P\u00ee\u00d0K\u0018 pZ\u00b6}x\u009e:\u00ca1\u0013\u00e7\u000fq\u000b\u00d9g\u00d2]EjE\u001d\u00c7";
                        var17_6 = 41;
                        var14_7 = 16;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = CommandManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00f9y\u00ad\u00c9CvfZ\u00d8a\u00cc\u00df\u0086\u00d0\u00e3i Y\f\u00f6\u0080]f~#-\u00c7\u008f\u00de\u0007v\u0084\n\u00e6\u0014\u009c\u0096\u00ab\u00ecqdSux\u00bd@\u00a4\u008a\u00c3";
                            var17_6 = 49;
                            var14_7 = 16;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = CommandManager.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
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
                CommandManager.b = var18_3;
                CommandManager.c = new String[4];
                CommandManager.g = new HashMap<K, V>(13);
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
                var6_12 = new long[2];
                var3_13 = 0;
                var4_14 = "\u0001q\u0098%D?\u00a9'X(L\u00be\u00fb\u00e7\u00e4\u00f7";
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
        CommandManager.e = var6_12;
        CommandManager.f = new Integer[2];
        CommandManager.INSTANCE = new CommandManager();
        CommandManager.o = "^";
        CommandManager.R = new ArrayList<E>();
        CommandManager.commandDispatcher = new CommandDispatcher();
        CommandManager.INSTANCE.add(BindCommand.INSTANCE);
        CommandManager.INSTANCE.add(FriendCommand.INSTANCE);
        CommandManager.INSTANCE.add(CfgCommand.INSTANCE);
        CommandManager.INSTANCE.add(GpsCommand.INSTANCE);
        CommandManager.INSTANCE.add(FixallCommand.INSTANCE);
        CommandManager.INSTANCE.add(AngleCommand.INSTANCE);
        CommandManager.INSTANCE.add(EasingsdebugCommand.INSTANCE);
    }

    private static CommandSyntaxException zkmRethrow(CommandSyntaxException commandSyntaxException) {
        return commandSyntaxException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7F4B;
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
                throw new RuntimeException("su/catlean/pf", exception);
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
            CommandManager.c[n2] = CommandManager.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = CommandManager.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/pf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5FC3;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/pf", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            CommandManager.f[n2] = n3;
        }
        return f[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = CommandManager.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/pf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(CommandManager.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(CommandManager.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

