/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

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
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class MultiTask
extends Module {
    @NotNull
    public static final MultiTask INSTANCE;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private MultiTask() {
        super("multi-task", Categories.getPlayer(), null, null);
    }

    @Flow
    public final void onPlayerUpdate(@NotNull PlayerUpdateEvent playerUpdateEvent) {
        block3: {
            HitResult hitResult;
            String[] stringArray = new String[]{};
            Intrinsics.checkNotNullParameter(playerUpdateEvent, "e");
            String[] stringArray2 = stringArray;
            if (Globals.getMc().crosshairTarget instanceof BlockHitResult) {
                HitResult hitResult2 = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(hitResult2, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
                hitResult = (BlockHitResult)hitResult2;
                if (Globals.getMc().options.attackKey.isPressed() && Globals.getMc().options.useKey.isPressed() && !Globals.getWorld().getBlockState(((BlockHitResult)hitResult).getBlockPos()).isAir()) {
                    boolean bl = Globals.getInteractionManager().isBreakingBlock() ? Globals.getInteractionManager().updateBlockBreakingProgress(((BlockHitResult)hitResult).getBlockPos(), ((BlockHitResult)hitResult).getSide()) : Globals.getInteractionManager().attackBlock(((BlockHitResult)hitResult).getBlockPos(), ((BlockHitResult)hitResult).getSide());
                    Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                }
            }
            if (!(Globals.getMc().crosshairTarget instanceof EntityHitResult)) break block3;
            HitResult hitResult3 = Globals.getMc().crosshairTarget;
            Intrinsics.checkNotNull(hitResult3, "null cannot be cast to non-null type net.minecraft.world.phys.EntityHitResult");
            hitResult = (EntityHitResult)hitResult3;
            if (Globals.getMc().options.attackKey.isPressed() && Globals.getMc().options.useKey.isPressed() && Globals.getPlayer().getAttackCooldownProgress(0.5f) > 0.9f) {
                Globals.getInteractionManager().attackEntity(Globals.getPlayer(), ((EntityHitResult)hitResult).getEntity());
                Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(2183204535370178327L, 4000594027204175459L, MethodHandles.lookup().lookupClass()).a(204200164338358L);
        long l = a ^ 0x3D5F32FE316AL;
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
        String[] stringArray = new String[3];
        int n = 0;
        String string = "\u00a6\u00a2\u00a2\u008dKc\u00a5\u008cf\u001a\u00c3\u00b0\u001aj\u00d9\u00f3\u00b3~\u00e9\u0092yg\u00b6T\u00b4\u00d3~S\u008e\u0000a\u001c\u007f\u00f1k\u001b\u00fdI\u0081\u0007Su\u009a\u0001\u0089\u00e4&\u0092\u009fb\u00afn\u0004Li 5\u008c\u00b0\u00ce3\u000f\u00c6\u00ea\u00be\u0013\u00c9h\u0096\u008c0-h\u00966\u00dc\u0012\u0003\u00d7\u00d1\u00cf \u008ev6R\u008d\u008f\u00b1\u001eJ\u0012\u00aaG\u008f\u00ca\u00f9\u00d5B\u00fa\u00bbg~\u0098&\u0085\u0018\u0019\u0086\u0003\u00d9H\u00a2{\u00ef\u001eB%6l\u00abS\u001c\u00ca\u001f\u0097\u00b7\u00ca \u00db04lM\u00af$\u00da\\q\u009c\u00de~?\u0013\u00adL\u0001\u00c7\u00bb\rk\u00c7\u0087n\u00e1\u00f0\u00cc\nL\u00bc\u00a5x\u00bfF\u00e7\u0098\u00f3\u00d7\u00dc\u000b\u00f1\u00034\u0095\u00e5I\u00ba\u00ae\u001c\u001a\u0007K\u00a09\u00e7K\u00d1\u00ca\u00fc\u00c6\u00fb\u00a0B\u007f\u00d7\u00e1\u00837\u0010%\u00c6\u00c3d@q\u00e5\u00c9\u0018VA\u0093\u00bd\u00f1e\u007f\u000b\u00a7\u00c8Dh\\\u00de\u00ec\u00dd\u009b\u000e\u0084^\u00d5^\f2]\u00b2\u00af\u00d8\u00e9\u00e5Y\u0011\u00a8\\H$\u00c0\u0088E\u00b0\u0095\u00d9\u00b9\u00e2I\u0007\u00ba\u0097\u0087\u00ab*\u00c5\u00d1>]\u00fe\u009e\u00c9/\u00fe\u00d9\u00c5\u00e8i*\u00a8%F\u00d3\u00fa\u00d7Z\u0087\u00e5";
        int n2 = 282;
        int n3 = 128;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = MultiTask.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                INSTANCE = new MultiTask();
                return;
            }
            n3 = string.charAt(n4);
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x183E;
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
                throw new RuntimeException("su/catlean/e5", exception);
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
            MultiTask.c[n2] = MultiTask.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = MultiTask.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/e5" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MultiTask.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

