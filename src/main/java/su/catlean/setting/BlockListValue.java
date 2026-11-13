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
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class BlockListValue {
    @NotNull
    private final List blocks;
    private static Module[] y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    public BlockListValue(@NotNull List list) {
        Intrinsics.checkNotNullParameter(list, "blocks");
        this.blocks = list;
    }

    public /* synthetic */ BlockListValue(List list, DefaultConstructorMarker defaultConstructorMarker) {
        list = new ArrayList();
        this(list);
    }

    @NotNull
    public final List getBlocks() {
        return this.blocks;
    }

    @NotNull
    public final List getBlocks2() {
        return this.blocks;
    }

    @NotNull
    public final BlockListValue copy(@NotNull List list) {
        long l = 0L;
        l = a ^ l;
        Intrinsics.checkNotNullParameter(list, (String)((Object)BlockListValue.zkmBootstrap("f", (int)13096, (long)(0x255D6606DE8503BBL ^ l))));
        return new BlockListValue(list);
    }

    public static /* synthetic */ BlockListValue copy$default(BlockListValue blockListValue, List list, int n, Object object) {
        if ((n & 1) != 0) {
            list = blockListValue.blocks;
        }
        return blockListValue.copy(list);
    }

    @NotNull
    public String toString() {
        return "BlocksValue(blocks=" + this.blocks + ")";
    }

    public int hashCode() {
        return ((Object)this.blocks).hashCode();
    }

    public boolean equals(@Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BlockListValue)) {
            return false;
        }
        BlockListValue blockListValue = (BlockListValue)object;
        return Intrinsics.areEqual(this.blocks, blockListValue.blocks);
    }

    public BlockListValue() {
        this(null, null);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        y = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return y;
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(2900813170904534241L, -3589362230789960133L, MethodHandles.lookup().lookupClass()).a(129499264381080L);
        long l = a ^ 0x2208C414BEF7L;
        d = new HashMap(13);
        BlockListValue.zkmSetFlag(null);
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
        String string = "\u0019\u00c6\u00b1\u00ed\u00ec\u00dd:-\u00ecw\u008dl\u00e7gkL E\u00cd\u0002\ff_\u0097hybv\u000b\u0006*\u00b7\u00cf\u0086\u00ef\u00b8I\t\u00c4<\u00cc\u0087\u00bb\u00a2rd\u00b1O\u00e7\u0010\u00c7\u00a3\u00bc\u00b9\u00bfV\u0097\u00e1\"l\u00ce\u001f\"\u001e\u00f9\u009d";
        int n2 = 66;
        int n3 = 16;
        int n4 = -1;
        while (true) {
            int n5 = ++n4;
            byte[] byArray3 = cipher.doFinal(string.substring(n5, n5 + n3).getBytes("ISO-8859-1"));
            stringArray[n++] = BlockListValue.zkmDecodeUtf8(byArray3).intern();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x2A1B;
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
                throw new RuntimeException("su/catlean/dg", exception);
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
            BlockListValue.c[n2] = BlockListValue.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = BlockListValue.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/dg" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(BlockListValue.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

