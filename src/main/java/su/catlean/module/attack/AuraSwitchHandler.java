/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

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
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.item.TridentItem;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AuraSwitchMode;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.ItemUtil;
import su.catlean.util.SlotResult;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationHandler;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AuraSwitchHandler
implements RotationHandler {
    @NotNull
    public static final AuraSwitchHandler INSTANCE;
    private static int y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private AuraSwitchHandler() {
    }

    public final boolean canAttackWithHeldItem(boolean bl, @NotNull AuraSwitchMode auraSwitchMode) {
        Intrinsics.checkNotNullParameter((Object)auraSwitchMode, "switchMode");
        ItemStack itemStack = Globals.getPlayer().getMainHandStack();
        Intrinsics.checkNotNullExpressionValue(itemStack, "getMainHandItem(...)");
        ItemStack itemStack2 = itemStack;
        if (bl) {
            return auraSwitchMode == AuraSwitchMode.NONE ? ItemUtil.isSword(itemStack2) || itemStack2.getItem() instanceof AxeItem || itemStack2.getItem() instanceof TridentItem || itemStack2.getItem() instanceof MaceItem : InventoryUtil.INSTANCE.R().isFound() || InventoryUtil.INSTANCE.g().isFound();
        }
        return true;
    }

    public final void switchToWeapon(char c, boolean bl, char c2) {
        block7: {
            SlotResult slotResult;
            block9: {
                Object object;
                block8: {
                    CallSite callSite;
                    block6: {
                        SlotResult slotResult2;
                        block2: {
                            block3: {
                                block4: {
                                    SlotResult slotResult3;
                                    block5: {
                                        int n = 0;
                                        long l = ((long)c << 48 | (long)n << 32 >>> 16 | (long)c2 << 48 >>> 48) ^ a;
                                        slotResult3 = InventoryUtil.INSTANCE.R();
                                        callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)3385013693287187982L, (long)l);
                                        slotResult2 = slotResult3;
                                        if (callSite != false) break block2;
                                        if (!slotResult2.isFound()) break block3;
                                        if (c < '\u0000') break block4;
                                        if (!bl) break block5;
                                        slotResult3.select();
                                        if (callSite == false) break block4;
                                    }
                                    slotResult3.select();
                                }
                                return;
                            }
                            slotResult2 = InventoryUtil.INSTANCE.g();
                        }
                        slotResult = slotResult2;
                        object = slotResult.isFound();
                        if (c2 < '\u0000' || callSite != false) break block6;
                        if (!object) break block7;
                        object = bl;
                    }
                    if (c < '\u0000') break block8;
                    if (!object) break block9;
                    slotResult.select();
                    object = callSite;
                }
                if (!object) break block7;
            }
            slotResult.select();
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void switchToWeapon$default(AuraSwitchHandler auraSwitchHandler, long l, boolean bl, int n, Object object) {
        void var4_4;
        long l2 = (l = a ^ l) ^ 0x1BCA101D949L;
        int n2 = (int)(l2 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        boolean bl2 = ItemUtil.V();
        boolean bl3 = var4_4 & 1;
        if (bl2) {
            bl3 = false;
        }
        boolean bl4 = bl3;
        auraSwitchHandler.switchToWeapon((char)n2, bl4, (char)n3);
    }

    @Override
    public void preAttack(int n, long l) {
        block3: {
            int n2;
            int n3;
            block2: {
                long l2 = (long)n << 32 | l << 32 >>> 32;
                long l3 = l2 ^ 0x2ED3919DF9AEL;
                n3 = (int)(l3 >>> 48);
                n2 = (int)(l3 << 48 >>> 48);
                CallSite callSite = ZkmReferences.zkmBootstrap("\u00c5", (long)8282018332447608040L, (long)l2);
                if (callSite == false) break block2;
                if (Aura.INSTANCE.getSwitchMode() != AuraSwitchMode.SILENT) break block3;
                y = Globals.getPlayer().getInventory().getSelectedSlot();
            }
            this.switchToWeapon((char)n3, true, (char)n2);
        }
    }

    @Override
    public void postRotate(@NotNull Rotation rotation, short s, int n, int n2) {
        block0: {
            long l = (long)s << 48 | (long)n << 32 >>> 16 | (long)n2 << 48 >>> 48;
            Intrinsics.checkNotNullParameter(rotation, (String)((Object)AuraSwitchHandler.zkmBootstrap("z", (int)30554, (long)(0x46934DB8A3995E23L ^ l))));
            if (Aura.INSTANCE.getSwitchMode() != AuraSwitchMode.SILENT) break block0;
            this.restoreSlot();
        }
    }

    @Override
    public void reset() {
        this.restoreSlot();
    }

    public final void restoreSlot() {
        block3: {
            int n;
            block2: {
                boolean bl = ItemUtil.V();
                n = y;
                if (!bl) break block2;
                if (n == -1) break block3;
                InventoryUtil.INSTANCE.selectSlot(y);
                n = -1;
            }
            y = n;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-2519580931557730791L, -384122617207862177L, MethodHandles.lookup().lookupClass()).a(215894886613914L);
        d = new HashMap(13);
        long l = a ^ 0x3C8000253A8CL;
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
        String string = " \u00ea?/\u00e6\u001c=\u00a0I\u001d\u0013\u0005P\u00fd\u008aUo\u00c4\u00bb\u0091s;\u00e7T>:\u0005\u00fa\u00a3 \u00e6\u00d5\u0014z(\t\u00ed\"sK\u0018\u00b2\u0003\u00fc]I{\u0006\u00bd\u007fmB\\\u00b5\u00a4\u00c3\u00ae\u00e9K\u00d4\u008dUDKr\u0018\u0018=\u00b2\u00cc\u009a|\u00e6\u00d4\u00d7I\u00a9\u00f9\u0083\u00d4n|k\u0094\u00cc\u0002\r\u00e7\u008b\u0093";
        int n2 = 90;
        int n3 = 40;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = AuraSwitchHandler.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                INSTANCE = new AuraSwitchHandler();
                y = -1;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x70EC;
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
                throw new RuntimeException("su/catlean/a7", exception);
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
            AuraSwitchHandler.c[n2] = AuraSwitchHandler.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AuraSwitchHandler.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/a7" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AuraSwitchHandler.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

