/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.ui.hud;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.gui.DrawContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.ui.hud.HudElement;
import su.catlean.util.Globals;
import su.catlean.util.ServerInfo;
import su.catlean.zkm.ZkmKeys;

public final class ChestCounterWidget
extends HudElement {
    @NotNull
    public static final ChestCounterWidget N;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map f;

    private ChestCounterWidget(long l) {
        long l2 = (l = a ^ l) ^ 0x4F2217675B1FL;
        int n = (int)(l2 >>> 48);
        int n2 = (int)(l2 << 48 >>> 48);
        super((char)n, "chest-counter-widget", (char)n2);
    }

    @Override
    public void render(long l, @NotNull DrawContext drawContext) {
        long l2 = l ^ 0x6A47001394F1L;
        Intrinsics.checkNotNullParameter(drawContext, "context");
        Pair pair = this.countChests();
        DrawContext drawContext2 = drawContext;
        ChestCounterWidget chestCounterWidget = this;
        String string = "chests: S:" + pair.getFirst() + " D:" + pair.getSecond();
        chestCounterWidget.drawText(drawContext2, l2, string);
    }

    private final Pair countChests() {
        int n = 0;
        int n2 = 0;
        try {
            Object object;
            Iterable iterable = ServerInfo.INSTANCE.V();
            Iterable iterable2 = iterable;
            Collection collection2 = new ArrayList();
            Iterator iterator2 = iterable2.iterator();
            while (iterator2.hasNext()) {
                object = iterator2.next();
                if (!(object instanceof ChestBlockEntity)) continue;
                collection2.add(object);
            }
            iterable = (List)collection2;
            for (Collection collection2 : iterable) {
                int n3;
                ChestBlockEntity chestBlockEntity = (ChestBlockEntity)((Object)collection2);
                Object t = Globals.getWorld().getBlockState(chestBlockEntity.getPos()).get(ChestBlock.CHEST_TYPE);
                Intrinsics.checkNotNullExpressionValue(t, "getValue(...)");
                object = (ChestType)t;
                if (object == ChestType.SINGLE) {
                    n3 = n;
                    n = n3 + 1;
                    continue;
                }
                n3 = n2;
                n2 = n3 + 1;
            }
        }
        catch (Exception exception) {}
        return new Pair<Integer, Integer>(n, n2 / 2);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                ChestCounterWidget.a = ZkmKeys.a(-7882222852425971598L, -7117920506270352489L, MethodHandles.lookup().lookupClass()).a(72985151591069L);
                var9 = ChestCounterWidget.a ^ 76141645160754L;
                var11_1 = var9 ^ 88843832291494L;
                ChestCounterWidget.f = new HashMap<K, V>(13);
                var0_2 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_3 = 1; var1_3 < 8; ++var1_3) {
                    v2 = v2;
                    v2[var1_3] = (byte)(var9 << var1_3 * 8 >>> 56);
                }
                var0_2.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_4 = new String[5];
                var5_5 = 0;
                var4_6 = "\u00d0j\u00cd\u00f1\u00c1\u0096>\u00d6\u00e7\u00d8\u00f0UN\u0005\u0096\u00cfk\u00c6\u00bb0(@\u008aa\u00b2\u0006F\u00b5\u009e\u008b\u00ffp\u0010\u009dm\u0082\u00c7\u0006\u000bs\u0086z\u00ab\u00b9\u00ce\u00e6\u00bf\u00df^ :\u00b6\u00ee\u00b7\rfg\u0017\u0016fm\u00e4\u0096\u009e3F!X\u00eac\u00f2\u00e5tM\u00f91\u00cf=\u008fxs;";
                var6_7 = 82;
                var3_8 = 32;
                var2_9 = -1;
lbl22:
                // 2 sources

                while (true) {
                    v3 = ++var2_9;
                    v4 = var4_6.substring(v3, v3 + var3_8);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl27:
                // 1 sources

                while (true) {
                    var7_4[var5_5++] = ChestCounterWidget.zkmDecodeUtf8(var8_10).intern();
                    if ((var2_9 += var3_8) < var6_7) {
                        var3_8 = var4_6.charAt(var2_9);
                        ** continue;
                    }
                    var4_6 = "\u00b2?\u0013\u00caR[4I|[\u008c\u0080\u0089\u00da/ ds\u001a\u00f6\u001d\u00d8\u0019\u0086\u0010\u0014[)\u00a4m\u00f9\u0082\u00f7\u00ee\u00ff\u0003\u00cb\u0011\u00d3\u00c6\u00e4";
                    var6_7 = 41;
                    var3_8 = 24;
                    var2_9 = -1;
lbl36:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_9;
                        v4 = var4_6.substring(v6, v6 + var3_8);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl41:
                // 1 sources

                while (true) {
                    var7_4[var5_5++] = ChestCounterWidget.zkmDecodeUtf8(var8_10).intern();
                    if ((var2_9 += var3_8) < var6_7) {
                        var3_8 = var4_6.charAt(var2_9);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_10 = var0_2.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl53:
                // 1 sources

                ** continue;
            }
        }
        ChestCounterWidget.b = var7_4;
        ChestCounterWidget.c = new String[5];
        ChestCounterWidget.N = new ChestCounterWidget(var11_1);
    }

    private static Exception zkmRethrow(Exception exception) {
        return exception;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x989;
        if (c[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])f.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    f.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/g2", exception);
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
            ChestCounterWidget.c[n2] = ChestCounterWidget.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = ChestCounterWidget.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/g2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(ChestCounterWidget.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

