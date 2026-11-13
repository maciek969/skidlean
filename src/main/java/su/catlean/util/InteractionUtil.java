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
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class InteractionUtil {
    private static String h;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long e;

    public static final void clickSlot(int n, int n2, @NotNull SlotActionType slotActionType, boolean bl) {
        block1: {
            Intrinsics.checkNotNullParameter((Object)slotActionType, "type");
            ClientPlayerInteractionManager clientPlayerInteractionManager = Globals.getMc().interactionManager;
            if (clientPlayerInteractionManager != null) {
                clientPlayerInteractionManager.clickSlot(Globals.getPlayer().currentScreenHandler.syncId, n, n2, slotActionType, Globals.getPlayer());
            }
            if (!bl) break block1;
            PacketUtil.sendPacket(new CloseHandledScreenC2SPacket(Globals.getPlayer().currentScreenHandler.syncId));
        }
    }

    public static /* synthetic */ void clickSlot$default(int n, int n2, SlotActionType slotActionType, boolean bl, int n3, Object object) {
        int n4 = PacketUtil.zkmIntConstant();
        int n5 = n3 & 2;
        if (n4 != 0) {
            if (n5 != 0) {
                n2 = 0;
            }
            n5 = n3 & 4;
        }
        if (n4 != 0) {
            if (n5 != 0) {
                slotActionType = SlotActionType.PICKUP;
            }
            n5 = n3 & (int)e;
        }
        if (n4 != 0) {
            if (n5 != 0) {
                bl = false;
            }
            n5 = n;
        }
        InteractionUtil.clickSlot(n5, n2, slotActionType, bl);
    }

    @NotNull
    public static final ActionResult useItem(@NotNull Hand hand, float f, float f2) {
        Intrinsics.checkNotNullParameter((Object)hand, "hand");
        ActionResult actionResult = Globals.getInteractionManager().interactItem(Globals.getPlayer(), hand);
        Intrinsics.checkNotNullExpressionValue(actionResult, "useItem(...)");
        ActionResult actionResult2 = actionResult;
        return actionResult2;
    }

    public static /* synthetic */ ActionResult useItem$default(Hand hand, float f, float f2, int n, Object object) {
        int n2 = PacketUtil.zkmIntConstant();
        int n3 = n & 2;
        if (n2 != 0) {
            if (n3 != 0) {
                f = -1337.0f;
            }
            n3 = n & 4;
        }
        if (n3 != 0) {
            f2 = -1337.0f;
        }
        return InteractionUtil.useItem(hand, f, f2);
    }

    public static void zkmSetFlag(String string) {
        h = string;
    }

    public static String zkmGetFlag() {
        return h;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(5964778084651047137L, 7846755280579591988L, MethodHandles.lookup().lookupClass()).a(8826777607296L);
        long l = a ^ 0x33B9F59A84F4L;
        d = new HashMap(13);
        InteractionUtil.zkmSetFlag("FG0P1b");
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
        String string = "x1;\u00c4\u008c\u0012*\u00ba\u00ee\u00e2\u001f\u00e8\u00d6\u00117\u00b1\u00f1L\u008f\u00bb\u00ae\u00ba@\u009c\u0010\u001e%\u0099\u00a8\u008a{\u00cfg9>K\u00cbJ\u009e\u0096\u00a4\u0010\u00cf*\u0002\u00a0\u00ffIub\u00c0\u00aa\u008f7\u0001Rl\u0006";
        int n2 = 58;
        int n3 = 24;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = InteractionUtil.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) break;
            n3 = string.charAt(n4);
        }
        b = stringArray;
        c = new String[3];
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n6 = 1;
        while (true) {
            if (n6 >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{84, -106, -54, -62, -120, -24, -32, -75});
                e = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                return;
            }
            byArray5 = byArray5;
            byArray5[n6] = (byte)(l << n6 * 8 >>> 56);
            ++n6;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x690E;
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
                throw new RuntimeException("su/catlean/ag", exception);
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
            InteractionUtil.c[n2] = InteractionUtil.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = InteractionUtil.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ag" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(InteractionUtil.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

