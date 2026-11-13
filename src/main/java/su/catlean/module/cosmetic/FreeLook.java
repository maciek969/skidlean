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
import net.minecraft.client.option.Perspective;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.profiler.Profilers;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.EventPlayerTravel;
import su.catlean.api.event.events.player.EventPostPlayerTravel;
import su.catlean.api.event.events.player.JumpEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.UpdateCrosshairTarget;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class FreeLook
extends Module {
    @NotNull
    public static final FreeLook INSTANCE;
    @NotNull
    private static Rotation cameraRotation;
    @NotNull
    private static Rotation savedRotation;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private FreeLook() {
        super("free-look", Categories.getCosmetic(), null, null);
    }

    @NotNull
    public final Rotation getCameraRotation() {
        return cameraRotation;
    }

    public final void setCameraRotation(@NotNull Rotation rotation) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(rotation, (String)((Object)FreeLook.zkmBootstrap("g", (int)3476, (long)(0x191A7FB087487FB8L ^ l))));
        cameraRotation = rotation;
    }

    @Override
    public void onEnable(long l) {
        long l2 = l ^ 0x1313C19FC590L;
        if (Globals.getMc().player == null || Globals.getMc().world == null) {
            this.disable(l2);
            return;
        }
        cameraRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
        Globals.getMc().options.setPerspective(Perspective.THIRD_PERSON_BACK);
    }

    @Override
    public void onDisable(long l) {
        if (Globals.getMc().player == null || Globals.getMc().world == null) {
            return;
        }
        Globals.getMc().options.setPerspective(Perspective.FIRST_PERSON);
        Globals.getPlayer().setYaw(cameraRotation.getYaw());
        Globals.getPlayer().setPitch(cameraRotation.getPitch());
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        RotationManager.INSTANCE.rotate(cameraRotation, 0);
    }

    @Flow
    private final void onJump(JumpEvent jumpEvent) {
        jumpEvent.setYaw(cameraRotation.getYaw());
        jumpEvent.cancel();
    }

    @Flow
    public final void onPlayerTravel(@NotNull EventPlayerTravel eventPlayerTravel) {
        Intrinsics.checkNotNullParameter(eventPlayerTravel, "e");
        savedRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
        Globals.getPlayer().setYaw(cameraRotation.getYaw());
        Globals.getPlayer().setPitch(cameraRotation.getPitch());
    }

    @Flow
    public final void onPostPlayerTravel(@NotNull EventPostPlayerTravel eventPostPlayerTravel) {
        Intrinsics.checkNotNullParameter(eventPostPlayerTravel, "e");
        Globals.getPlayer().setYaw(savedRotation.getYaw());
        Globals.getPlayer().setPitch(savedRotation.getPitch());
    }

    @Flow
    private final void onUpdateCrosshairTarget(UpdateCrosshairTarget updateCrosshairTarget) {
        Profilers.get().pop();
        updateCrosshairTarget.cancel();
        if (Globals.getMc().getCameraEntity() != null && Globals.getMc().world != null && Globals.getMc().player != null) {
            HitResult hitResult;
            Globals.getMc().crosshairTarget = hitResult = MovementUtil.INSTANCE.canReach(Globals.getPlayer().getBlockInteractionRange(), Globals.getPlayer().getEntityInteractionRange(), cameraRotation.getYaw(), cameraRotation.getPitch());
            Globals.getMc().targetedEntity = hitResult instanceof EntityHitResult ? ((EntityHitResult)hitResult).getEntity() : null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(2960989270622517210L, 1313841314002512854L, MethodHandles.lookup().lookupClass()).a(74626525402603L);
        long l = a ^ 0x6477E8D50461L;
        d = new HashMap(13);
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
        String[] stringArray = new String[2];
        int n = 0;
        String string = "%\u009c\u00f7\u00c8a\u0080\u00e2\u00ea2\u0003\u00df\u00e2\u00a2\u00a2J\u009b\u009eZ\\upP0\u0005\u0010]\u00f8\u00b4\u0097\u00b6$\u00af\u001a0\u00de\u00c5T\u00ef2\u00ea\u007f";
        int n2 = 41;
        int n3 = 24;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = FreeLook.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[2];
        g = new HashMap(13);
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        for (int i = 1; i < 8; ++i) {
            byArray5 = byArray5;
            byArray5[i] = (byte)(l << i * 8 >>> 56);
        }
        cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
        long[] lArray = new long[2];
        int n6 = 0;
        String string2 = "\u00e9\u00dd\u00ac\u0086\u00f7>0pM\u0011\u000bE\u001cg\u00e4\u00c4";
        int n7 = 16;
        int n8 = 0;
        do {
            byte[] byArray6 = string2.substring(n8, n8 += 8).getBytes("ISO-8859-1");
            int n9 = n6++;
            long l2 = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
            byte[] byArray7 = cipher2.doFinal(new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2});
            lArray[n9] = ((long)byArray7[0] & 0xFFL) << 56 | ((long)byArray7[1] & 0xFFL) << 48 | ((long)byArray7[2] & 0xFFL) << 40 | ((long)byArray7[3] & 0xFFL) << 32 | ((long)byArray7[4] & 0xFFL) << 24 | ((long)byArray7[5] & 0xFFL) << 16 | ((long)byArray7[6] & 0xFFL) << 8 | (long)byArray7[7] & 0xFFL;
        } while (n8 < n7);
        e = lArray;
        f = new Integer[2];
        INSTANCE = new FreeLook();
        cameraRotation = new Rotation(0.0f, 0.0f, false, null, 12, null);
        savedRotation = new Rotation(0.0f, 0.0f, false, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x79;
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
                throw new RuntimeException("su/catlean/kl", exception);
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
            FreeLook.c[n2] = FreeLook.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FreeLook.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/kl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x6B2F;
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
                throw new RuntimeException("su/catlean/kl", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FreeLook.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FreeLook.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/kl" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FreeLook.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FreeLook.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

