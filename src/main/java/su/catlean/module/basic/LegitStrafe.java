/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.basic;

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
import kotlin.math.MathKt;
import net.minecraft.client.input.Input;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.KeyboardInputEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.ClientInputAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.util.Globals;
import su.catlean.util.InventoryUtil;
import su.catlean.util.MovementUtil;
import su.catlean.util.rotation.MoveFix;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class LegitStrafe
extends Module {
    @NotNull
    public static final LegitStrafe INSTANCE;
    private static boolean L;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private LegitStrafe() {
        super("legit-strafe", Categories.getBasic(), null, null);
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        boolean bl;
        long l = a ^ 0x54E068D76561L;
        int n = (int)((l ^ 0x2ED6DF876CB3L) >>> 32);
        boolean bl2 = bl = MovementUtil.INSTANCE.isMoving() && (!Globals.getMc().options.forwardKey.isPressed() || Globals.getMc().options.backKey.isPressed());
        if (bl) {
            RotationManager.INSTANCE.rotate(new Rotation(MovementUtil.INSTANCE.b(n), Globals.getPlayer().getPitch(), false, MoveFix.FORCE, 4, null), 0);
            L = true;
        }
    }

    @Flow(priority=10)
    private final void onKeyboardInput(KeyboardInputEvent keyboardInputEvent) {
        long l = a ^ 0x20FC06D541A9L;
        int n = (int)((l ^ 0x5ACAB185487BL) >>> 32);
        int n2 = InventoryUtil.zkmIntConstant();
        boolean bl = L;
        if (n2 != 0) {
            if (!bl) {
                return;
            }
            bl = false;
        }
        L = bl;
        float f = Globals.getPlayer().input.getMovementInput().y;
        float f2 = Globals.getPlayer().input.getMovementInput().x;
        float f3 = (Globals.getPlayer().getYaw() - MovementUtil.INSTANCE.b(n)) * ((float)Math.PI / 180);
        float f4 = (float)Math.cos(f3);
        float f5 = (float)Math.sin(f3);
        Input input = Globals.getPlayer().input;
        Intrinsics.checkNotNull(input, "null cannot be cast to non-null type su.catlean.mixins.accessors.ClientInputAccessor");
        ((ClientInputAccessor)((Object)input)).setMoveVector(new Vec2f(MathKt.roundToInt(f2 * f4 - f * f5), MathKt.roundToInt(f * f4 + f2 * f5)));
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-4175720866563902228L, 5721813900176972765L, MethodHandles.lookup().lookupClass()).a(179858694017357L);
        long l = a ^ 0x47E923BCCC08L;
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
        String string = "\f\u0098\u0097\u00db\u008e\u00fa8\u0000\u0092\u000erL\u00f9\u00d7\u008b\u001e\u008f\u0002\u0083r\u00c5\u0011\u00c9V\u0090\u001c\u00b9\u00b1\u00896\u0093\u009e\u00a7KU\u0095rA\u00f4u\u00e6\u00c6\u00bdpO\u00dd\u00ecpZ\u000e\u00da\u0092\u00baS8|D\u00b9,g\u00e47H\u0085`\u00e4\u00b4)2\u00a5\u0084\u00a12\u009f\u00f4\"\u00c5\u00fb \u009cm\u00d8\u0018o\u0004TJ'\u00f3-\u00f1\u0099?*Z*\u00f5\u00bc\u001ef\u00c0\u009b\u00e6\u00f2\u00f0\u00d3>F\u00f2\u0016\u00f3\u0004\u00fa\u00c2a\u00c8\u00a5m\u00d1Q5\u00b2\u0099\u00c1\u0082\u00a7m\u00fd\u00db\u00af\u00c3\u008e&\u00e5\u00f6\u00da\u00b6\u00a4\u00a0\u00c6\u00b2\u0096}\u000b\u00f9\u00c7\u00f3\u00ca\u00ae\u00b0\u00d8\u009fS\u00da\u00b9\u009b<\u00ec2*\u00f2\u00d0\u00a4\u00bd\u00e9@\u00f1\u00d3\u00a3";
        int n2 = 169;
        int n3 = 24;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = LegitStrafe.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[2];
                INSTANCE = new LegitStrafe();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x121D;
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
                throw new RuntimeException("su/catlean/_2", exception);
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
            LegitStrafe.c[n2] = LegitStrafe.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LegitStrafe.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LegitStrafe.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

