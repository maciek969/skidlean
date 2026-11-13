/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

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
import kotlin.Unit;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.Hand;
import su.catlean.module.Module;
import su.catlean.module.basic.GuiMove;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.InventoryUtil;
import su.catlean.util.PacketUtil;
import su.catlean.util.rotation.ActionRotationRequest;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class MiddleClickThrowThread
extends Thread {
    private int epSlot;
    private int originalSlot;
    private int delay;
    private boolean inv;
    private static int C;
    private static final long a;
    private static final long[] b;
    private static final Integer[] c;
    private static final Map d;

    /*
     * WARNING - void declaration
     */
    public MiddleClickThrowThread(int n, int n2, int n3, boolean bl) {
        block0: {
            void var7_9;
            int n4 = 0;
            long l = 0L;
            long l2 = ((long)n4 << 32 | l << 32 >>> 32) ^ a;
            CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)-4720816755052586112L, (long)l2);
            CallSite callSite2 = callSite;
            this.epSlot = n;
            this.originalSlot = n2;
            this.delay = n3;
            this.inv = bl;
            if (ZkmReferences.zkmBootstrap("\u00c5", (long)-4721520624835518232L, (long)l2) == null) break block0;
            ZkmReferences.zkmBootstrap("\u00c5", (int)(++var7_9), (long)-4693267007781537182L, (long)l2);
        }
    }

    @Override
    public void run() {
        block9: {
            int n;
            block7: {
                block8: {
                    int n2;
                    block6: {
                        int n3;
                        block4: {
                            block5: {
                                int n4 = MiddleClickThrowThread.zkmIntConstant();
                                GuiMove.INSTANCE.setMacroActive(true);
                                n2 = n4;
                                n3 = this.inv;
                                if (n2 != 0) break block4;
                                if (n3 != 0) break block5;
                                InventoryUtil.INSTANCE.selectSlot(this.epSlot);
                                if (n2 == 0) break block6;
                                Module.zkmSetFlag(new Module[3]);
                            }
                            n3 = this.epSlot;
                        }
                        InteractionUtil.clickSlot$default(n3, this.originalSlot, SlotActionType.SWAP, false, 8, null);
                    }
                    GuiMove.INSTANCE.setMacroActive(false);
                    Thread.sleep(this.delay);
                    RotationManager.INSTANCE.submit(new ActionRotationRequest(new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null), 200, MiddleClickThrowThread::run$lambda$0));
                    Thread.sleep(this.delay);
                    GuiMove.INSTANCE.setMacroActive(true);
                    n = this.inv;
                    if (n2 != 0) break block7;
                    if (n != 0) break block8;
                    InventoryUtil.INSTANCE.selectSlot(this.originalSlot);
                    if (n2 == 0) break block9;
                }
                n = this.epSlot;
            }
            InteractionUtil.clickSlot$default(n, this.originalSlot, SlotActionType.SWAP, false, 8, null);
        }
        GuiMove.INSTANCE.setMacroActive(false);
        super.run();
    }

    private static final Unit run$lambda$0() {
        InteractionUtil.useItem$default(Hand.MAIN_HAND, 0.0f, 0.0f, 6, null);
        PacketUtil.sendPacket(new HandSwingC2SPacket(Hand.MAIN_HAND));
        return Unit.INSTANCE;
    }

    public static void zkmSetFlag(int n) {
        C = n;
    }

    public static int zkmGetFlag() {
        return C;
    }

    public static int zkmIntConstant() {
        return 0;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block9: {
            block8: {
                MiddleClickThrowThread.a = ZkmKeys.a(7571245220821462297L, -4748880409182059764L, MethodHandles.lookup().lookupClass()).a(187703699128839L);
                var11 = MiddleClickThrowThread.a ^ 12731612378305L;
                MiddleClickThrowThread.d = new HashMap<K, V>(13);
                MiddleClickThrowThread.zkmSetFlag(50);
                var0_1 = Cipher.getInstance("DES/CBC/NoPadding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var11 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var11 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var6_3 = new long[5];
                var3_4 = 0;
                var4_5 = "\u00b2a\u00e5\u00dd\u0090\u00db\u0094\u0015>\u00ed\u0019n\u00a0\u0091vV\u00a6r0\u00a5\u009f\u00f4#I";
                var5_6 = 24;
                var2_7 = 0;
                while (true) {
                    var7_8 = var4_5.substring(var2_7, var2_7 += 8).getBytes("ISO-8859-1");
                    v3 = var6_3;
                    v4 = var3_4++;
                    v5 = ((long)var7_8[0] & 255L) << 56 | ((long)var7_8[1] & 255L) << 48 | ((long)var7_8[2] & 255L) << 40 | ((long)var7_8[3] & 255L) << 32 | ((long)var7_8[4] & 255L) << 24 | ((long)var7_8[5] & 255L) << 16 | ((long)var7_8[6] & 255L) << 8 | (long)var7_8[7] & 255L;
                    v6 = -1;
                    break block8;
                    break;
                }
lbl27:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var2_7 < var5_6) ** continue;
                    var4_5 = "\u0083\u00ef{\u00ca\u00f8\u00c2~\u0002\u00a3E\u0080\u00bb\u0007+\u00a2\u00fc";
                    var5_6 = 16;
                    var2_7 = 0;
                    while (true) {
                        var7_8 = var4_5.substring(var2_7, var2_7 += 8).getBytes("ISO-8859-1");
                        v3 = var6_3;
                        v4 = var3_4++;
                        v5 = ((long)var7_8[0] & 255L) << 56 | ((long)var7_8[1] & 255L) << 48 | ((long)var7_8[2] & 255L) << 40 | ((long)var7_8[3] & 255L) << 32 | ((long)var7_8[4] & 255L) << 24 | ((long)var7_8[5] & 255L) << 16 | ((long)var7_8[6] & 255L) << 8 | (long)var7_8[7] & 255L;
                        v6 = 0;
                        break block8;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    v3[v4] = v7;
                    if (var2_7 < var5_6) ** continue;
                    break block9;
                    break;
                }
            }
            var8_9 = v5;
            var10_10 = var0_1.doFinal(new byte[]{(byte)(var8_9 >>> 56), (byte)(var8_9 >>> 48), (byte)(var8_9 >>> 40), (byte)(var8_9 >>> 32), (byte)(var8_9 >>> 24), (byte)(var8_9 >>> 16), (byte)(var8_9 >>> 8), (byte)var8_9});
            v7 = ((long)var10_10[0] & 255L) << 56 | ((long)var10_10[1] & 255L) << 48 | ((long)var10_10[2] & 255L) << 40 | ((long)var10_10[3] & 255L) << 32 | ((long)var10_10[4] & 255L) << 24 | ((long)var10_10[5] & 255L) << 16 | ((long)var10_10[6] & 255L) << 8 | (long)var10_10[7] & 255L;
            switch (v6) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl53:
                // 1 sources

                ** continue;
            }
        }
        MiddleClickThrowThread.b = var6_3;
        MiddleClickThrowThread.c = new Integer[5];
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3629;
        if (c[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = b[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])d.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    d.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/i3", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            MiddleClickThrowThread.c[n2] = n3;
        }
        return c[n2];
    }

    private static int a(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = MiddleClickThrowThread.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_0().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/i3" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(MiddleClickThrowThread.class, "a", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

