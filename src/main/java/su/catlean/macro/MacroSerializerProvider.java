/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.macro;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SealedClassSerializer;
import org.jetbrains.annotations.NotNull;
import su.catlean.config.ModuleConfig;
import su.catlean.config.ModuleConfigSerializer;
import su.catlean.macro.ChatMacro;
import su.catlean.macro.ChatMacro$$serializer;
import su.catlean.macro.Macro;
import su.catlean.macro.SettingMacro;
import su.catlean.macro.SettingMacro$$serializer;
import su.catlean.zkm.ZkmKeys;

public final class MacroSerializerProvider {
    static final MacroSerializerProvider INSTANCE;
    private static final long a;
    private static final String b;

    private MacroSerializerProvider() {
    }

    @NotNull
    public final KSerializer serializer() {
        Object[] objectArray = new KClass[]{Reflection.getOrCreateKotlinClass(ChatMacro.class), Reflection.getOrCreateKotlinClass(ModuleConfig.class), Reflection.getOrCreateKotlinClass(SettingMacro.class)};
        KClass[] kClassArray = objectArray;
        objectArray = new KSerializer[]{ChatMacro$$serializer.INSTANCE, ModuleConfigSerializer.INSTANCE, SettingMacro$$serializer.INSTANCE};
        return new SealedClassSerializer(b, Reflection.getOrCreateKotlinClass(Macro.class), kClassArray, (KSerializer<? extends T>[])objectArray, new Annotation[0]);
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-5830574947048430428L, 299825577381671109L, MethodHandles.lookup().lookupClass()).a(69705668784920L);
        long l = a ^ 0x582BF7F65BE5L;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("Z\u0004g\u00c4P0\u00ed\u00eb\u0007\u00d0\u00aa{2A\u00feO".getBytes("ISO-8859-1"));
                b = MacroSerializerProvider.zkmDecodeUtf8(byArray3).intern();
                INSTANCE = new MacroSerializerProvider();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
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
}

