/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.setting;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.zkm.ZkmKeys;

public final class ItemListValue {
    @NotNull
    private final List items;
    private static String[] C;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public ItemListValue(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "items");
        this.items = list;
    }

    public /* synthetic */ ItemListValue(List list, DefaultConstructorMarker defaultConstructorMarker) {
        list = new ArrayList();
        this(list);
    }

    @NotNull
    public final List getItems() {
        return this.items;
    }

    @NotNull
    public final List getItems2() {
        return this.items;
    }

    @NotNull
    public final ItemListValue copy(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)ItemListValue.zkmBootstrap("c", (int)23141, (long)(0x59FC9B849248F32CL ^ l))));
        return new ItemListValue(list);
    }

    public static /* synthetic */ ItemListValue copy$default(ItemListValue itemListValue, List list, int n, Object object) {
        if ((n & 1) != 0) {
            list = itemListValue.items;
        }
        return itemListValue.copy(list);
    }

    @NotNull
    public String toString() {
        return "ItemsValue(items=" + this.items + ")";
    }

    public int hashCode() {
        return ((Object)this.items).hashCode();
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ItemListValue)) {
            return false;
        }
        ItemListValue itemListValue = (ItemListValue)object;
        return Intrinsics.areEqual(this.items, itemListValue.items);
    }

    public ItemListValue() {
        this(null, null);
    }

    public static void zkmSetFlag(String[] stringArray) {
        C = stringArray;
    }

    public static String[] zkmGetFlag() {
        return C;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(7960820287677168425L, -1311547587862281449L, MethodHandles.lookup().lookupClass()).a(232416033468705L);
        long l = a ^ 0x4D06A5D781A7L;
        d = new HashMap(13);
        ItemListValue.zkmSetFlag(new String[2]);
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
        String string = "\u00e8\u00ca\u00b7\u007f34\u00a1\u00c0\u00de;\u00ee\u00f2\u00aa.-?\u0010\u0082I}\u0094\u00ee\u00ea\u008c\u00df`\u008b\u00fa\u0010J\u00db@\u0098(\u00f2\u00d6!\u0002\u00c5U\u00c4\t\u00a1m\u008a\u00a1I\\F\u00c4\u0080\u00c9k\u008b\u0004#Qw\u00c3z\u00d6\f\u001a\t\u00ba\u00f1\u00be\u00f9qtT\u00fb\u00ccr";
        int n2 = 74;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = ItemListValue.zkmDecodeUtf8(byArray3).intern();
            if ((n4 += n3) >= n2) {
                b = stringArray;
                c = new String[3];
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x1918;
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
                throw new RuntimeException("su/catlean/nv", exception);
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
            ItemListValue.c[n2] = ItemListValue.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ItemListValue.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/nv" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ItemListValue.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

