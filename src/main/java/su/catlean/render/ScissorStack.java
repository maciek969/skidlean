/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.render;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.render.Rect;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class ScissorStack {
    @NotNull
    public static final ScissorStack INSTANCE;
    @NotNull
    private static final ArrayDeque arrayDeque;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private ScissorStack() {
    }

    public final void push(@NotNull Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "scissor");
        Window window = Globals.getMc().getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "getWindow(...)");
        Window window2 = window;
        int n = window2.getScaleFactor();
        int n2 = window2.getFramebufferHeight();
        float f = (float)MathKt.roundToInt(rect.getX() * (float)n) - 1.0f;
        float f2 = (float)MathKt.roundToInt((float)n2 - rect.getY1() * (float)n) - 1.0f;
        float f3 = (float)MathKt.roundToInt((rect.getX1() - rect.getX()) * (float)n) + 1.0f;
        float f4 = (float)MathKt.roundToInt((rect.getY1() - rect.getY()) * (float)n) + 1.0f;
        arrayDeque.addLast(new Rect(f, f2, f3, f4));
    }

    @NotNull
    public final Rect pop() {
        Object e = arrayDeque.removeLast();
        Intrinsics.checkNotNullExpressionValue(e, "removeLast(...)");
        return (Rect)e;
    }

    @Nullable
    public final Rect peek() {
        return (Rect)arrayDeque.peekLast();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(8980837311664535047L, -5844576313151723954L, MethodHandles.lookup().lookupClass()).a(69118081339174L);
        d = new HashMap(13);
        long l = a ^ 0x65C3B19E5550L;
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
        String string = "\u00db\u001b>JJ\u00ce\u00e7\u0088\u00feO q\u009e\u001b\u00b9* nG\u009d\u00a5\u0083\u00f9\u00c8=\u00d3U$G>\u0092Xb7GZ\u00cf\u0004\u00df\u00a2\u00a4(|\u0096*W.JA\u0018\u00af5=\u00cc\u00c6\u00e8\u000b\u00ac\u00cd\u00f47\u0014c\\?\u001a\u009a\u00f3\u00d3%\u00dc\u00b5.2";
        int n2 = 74;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ScissorStack.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
                INSTANCE = new ScissorStack();
                arrayDeque = new ArrayDeque();
                return;
            }
            n3 = string.charAt(n4);
        }
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x593D;
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
                throw new RuntimeException("su/catlean/a8", exception);
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
            ScissorStack.c[n2] = ScissorStack.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ScissorStack.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/a8" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ScissorStack.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

