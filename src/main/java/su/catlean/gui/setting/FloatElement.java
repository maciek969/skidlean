/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui.setting;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.math.BigDecimal;
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
import org.jetbrains.annotations.NotNull;
import su.catlean.gui.setting.SliderElement;
import su.catlean.module.Module;
import su.catlean.setting.Setting;
import su.catlean.zkm.ZkmKeys;

public final class FloatElement
extends SliderElement {
    private static final long b = ZkmKeys.a(3792817161690927398L, 5854561959595799312L, MethodHandles.lookup().lookupClass()).a(63577411702596L);
    private static final String[] k;
    private static final String[] l;
    private static final Map m;

    public FloatElement(@NotNull Setting setting2) {
        Intrinsics.checkNotNullParameter(setting2, "setting");
        super(setting2);
    }

    @Override
    public void setFromMouse(int n) {
        if (this.isDragging()) {
            float f = Math.clamp(((float)n - this.getX()) / (this.getWidth() - 7.0f), 0.0f, 1.0f);
            float f2 = ((Number)this.getFloatRange().getStart()).floatValue() + (((Number)this.getFloatRange().getEndInclusive()).floatValue() - ((Number)this.getFloatRange().getStart()).floatValue()) * f;
            float f3 = ((Number)this.getFloatRange().getEndInclusive()).floatValue() > 1.0f ? 10.0f : 100.0f;
            this.getSetting().setValue(Float.valueOf((float)MathKt.roundToInt(f2 * f3) / f3));
        }
    }

    @Override
    public float getProgress() {
        return Math.clamp((((Number)this.getSetting().getValue()).floatValue() - ((Number)this.getFloatRange().getStart()).floatValue()) / (((Number)this.getFloatRange().getEndInclusive()).floatValue() - ((Number)this.getFloatRange().getStart()).floatValue()), 0.0f, 1.0f);
    }

    @NotNull
    public Float parse(@NotNull String string) {
        long l = 0L;
        l = b ^ l;
        Intrinsics.checkNotNullParameter(string, (String)((Object)FloatElement.zkmBootstrap("b", (int)9362, (long)(0x7B7D221C5962E66BL ^ l))));
        return Float.valueOf(Float.parseFloat(string));
    }

    @Override
    public void close() {
        this.setDragging(false);
    }

    @NotNull
    public Float step(float f, int n) {
        return Float.valueOf(new BigDecimal(String.valueOf((double)n * 0.1)).add(new BigDecimal(String.valueOf(((Number)this.getSetting().getValue()).floatValue()))).floatValue());
    }

    @Override
    public Number parseNumber(String string) {
        return this.parse(string);
    }

    @Override
    public Number round(Number number, int n) {
        return this.step(number.floatValue(), n);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        m = new HashMap(13);
        long l = b ^ 0x2323F212E8B8L;
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
        String string = "X\u001a3\u00c1\u0013k\u00f6%\u00b6\u0093\u00b577fma\u0010Wq\u00e7aM\u0090\u00ef@\u00ea\u000en\u00c1\u00b4\u00d9\u00a8\u00fe";
        int n2 = 33;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = FloatElement.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                k = stringArray;
                FloatElement.l = new String[2];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x40C3;
        if (FloatElement.l[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])m.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    m.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ra", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = k[n2].getBytes("ISO-8859-1");
            FloatElement.l[n2] = FloatElement.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return FloatElement.l[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FloatElement.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ra" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FloatElement.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

