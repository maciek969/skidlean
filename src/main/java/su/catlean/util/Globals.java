/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package su.catlean.util;

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
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import su.catlean.zkm.ZkmKeys;

public final class Globals {
    @NotNull
    private static final Logger logger;
    private static boolean X;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    @NotNull
    public static final MinecraftClient getMc() {
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        Intrinsics.checkNotNullExpressionValue(minecraftClient, "getInstance(...)");
        return minecraftClient;
    }

    @NotNull
    public static final ClientPlayerEntity getPlayer() {
        ClientPlayerEntity clientPlayerEntity = Globals.getMc().player;
        Intrinsics.checkNotNull(clientPlayerEntity);
        return clientPlayerEntity;
    }

    @NotNull
    public static final ClientWorld getWorld() {
        ClientWorld clientWorld = Globals.getMc().world;
        Intrinsics.checkNotNull(clientWorld);
        return clientWorld;
    }

    @NotNull
    public static final ClientPlayerInteractionManager getInteractionManager() {
        ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getMc().interactionManager;
        Intrinsics.checkNotNull(clientPlayerInteractionManager);
        return clientPlayerInteractionManager;
    }

    @NotNull
    public static final ClientPlayNetworkHandler getNetworkHandler() {
        ClientPlayNetworkHandler clientPlayNetworkHandler = Globals.getMc().getNetworkHandler();
        Intrinsics.checkNotNull(clientPlayNetworkHandler);
        return clientPlayNetworkHandler;
    }

    public static final long now() {
        return System.currentTimeMillis();
    }

    @NotNull
    public static final Logger getLogger() {
        return logger;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(333958358984929967L, -4704986976773013367L, MethodHandles.lookup().lookupClass()).a(201317353123063L);
        long l = a ^ 0x2FC671E57476L;
        d = new HashMap(13);
        Globals.zkmSetFlag(false);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray2 = byArray2;
            byArray2[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
        String[] stringArray = new String[3];
        int n = 0;
        String string = "\u008c\u0013s\u008f\u009e\u00b1\u001c\u0085\u00c6?~\u00f9\u00d4\u0006\u0092)v\u00afC\u007fL\u00ec\u00c9]\u00ed\u007f\u00caA)PDi\u0010\u00a8M\u00ad\u00f1K\u001fcea\u00ffzd\u00a7(_\u00a80\u00f8D\u00f0\u0093%\u0098\u00d6\u009d\u00bb!=\u00f5|\u0091\u00ab\u00c19\r`\u00a9\u00dd\u00ddT\u001b\n>G\u00a6\u00fb\u0012s$ \u00ee]T\u00edZ@\u0091~2\u009f\u0098D}a\u0012";
        int n2 = 98;
        int n3 = 32;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = Globals.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                Logger logger = LoggerFactory.getLogger((String)"catlean");
                Intrinsics.checkNotNullExpressionValue(logger, "getLogger(...)");
                Globals.logger = logger;
                return;
            }
            n3 = string.charAt(n4);
        }
    }

    public static void zkmSetFlag(boolean bl) {
        X = bl;
    }

    public static boolean zkmGetFlag() {
        return X;
    }

    public static boolean c() {
        return true;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x61BD;
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
                throw new RuntimeException("su/catlean/zf", exception);
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
            Globals.c[n2] = Globals.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Globals.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/zf" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Globals.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

