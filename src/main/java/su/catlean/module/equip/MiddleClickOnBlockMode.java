/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.equip;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.equip.MiddleClick;
import su.catlean.zkm.ZkmKeys;

final class MiddleClickOnBlockMode
extends Enum {
    @NotNull
    private final Function0 action;
    @NotNull
    private final Function0 condition;
    public static final MiddleClickOnBlockMode FIREWORK;
    public static final MiddleClickOnBlockMode FRIEND;
    public static final MiddleClickOnBlockMode PEARL;
    public static final MiddleClickOnBlockMode XP;
    public static final MiddleClickOnBlockMode NONE;
    private static final MiddleClickOnBlockMode[] $VALUES;
    private static final EnumEntries $ENTRIES;
    private static final long a;

    /*
     * WARNING - void declaration
     */
    private MiddleClickOnBlockMode() {
        void var4_1;
        void var3_2;
        void var2_-1;
        void var1_-1;
        this.action = var3_2;
        this.condition = var4_1;
    }

    MiddleClickOnBlockMode(DefaultConstructorMarker defaultConstructorMarker) {
        Function0<Unit> function03 = MiddleClickOnBlockMode::_init_$lambda$0;
        this((String)((Object)function0), (int)function02, (Function0)((Object)defaultConstructorMarker), function03);
    }

    @NotNull
    public final Function0 getAction() {
        return this.action;
    }

    @NotNull
    public final Function0 getCondition() {
        return this.condition;
    }

    public static MiddleClickOnBlockMode[] values() {
        return (MiddleClickOnBlockMode[])$VALUES.clone();
    }

    public static MiddleClickOnBlockMode valueOf(String string) {
        return Enum.valueOf(MiddleClickOnBlockMode.class, string);
    }

    @NotNull
    public static EnumEntries get$ENTRIES() {
        return $ENTRIES;
    }

    private static final Unit _init_$lambda$0() {
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$0() {
        long l = a ^ 0x62859C5C480BL;
        long l2 = l ^ 0x516AA562324L;
        int n = (int)(l2 >>> 32);
        int n2 = (int)(l2 << 32 >>> 48);
        int n3 = (int)(l2 << 48 >>> 48);
        MiddleClick.access$onMiddleClickAir(n, MiddleClick.INSTANCE, (short)n2, n3);
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$1() {
        MiddleClick.access$onMiddleClickBlock(MiddleClick.INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$2() {
        long l = a ^ 0x2D0983F6A8E6L;
        long l2 = l ^ 0xAC137A6B654L;
        MiddleClick middleClick = MiddleClick.INSTANCE;
        if (!MiddleClick.access$getPearlOnRelease(middleClick)) {
            MiddleClick.access$throwPearl(MiddleClick.INSTANCE, l2);
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$3() {
        long l = a ^ 0x30C8EC6D418FL;
        long l2 = l ^ 0x1700583D5F3DL;
        MiddleClick middleClick = MiddleClick.INSTANCE;
        if (MiddleClick.access$getPearlOnRelease(middleClick)) {
            MiddleClick.access$throwPearl(MiddleClick.INSTANCE, l2);
        }
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$4() {
        long l = a ^ 0x3DD58685F78CL;
        long l2 = l ^ 0x5F71D386872EL;
        MiddleClick.access$useRotated(MiddleClick.INSTANCE, l2);
        return Unit.INSTANCE;
    }

    private static final Unit _clinit_$lambda$5() {
        return Unit.INSTANCE;
    }

    private static final MiddleClickOnBlockMode[] $values() {
        MiddleClickOnBlockMode[] middleClickOnBlockModeArray = new MiddleClickOnBlockMode[]{FIREWORK, FRIEND, PEARL, XP, NONE};
        return middleClickOnBlockModeArray;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                MiddleClickOnBlockMode.a = ZkmKeys.a(6600132333390343143L, -8488503337729565593L, MethodHandles.lookup().lookupClass()).a(128603587947717L);
                var9 = MiddleClickOnBlockMode.a ^ 54689810113001L;
                var1_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var2_2 = 1; var2_2 < 8; ++var2_2) {
                    v2 = v2;
                    v2[var2_2] = (byte)(var9 << var2_2 * 8 >>> 56);
                }
                var1_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var0_3 = new String[5];
                var6_4 = 0;
                var5_5 = "\u00ffw\u00d7j~\u00155\u00c3\u000fe\"\u0001\u00e7\u00b9\u009bg\b\u00da\u00c2\u00b4\u00d9@D\u00c3\u0001\bO\u00a7\u000b\u00f8\u0090\u00ba\u0015\u00bc";
                var7_6 = 34;
                var4_7 = 16;
                var3_8 = -1;
lbl19:
                // 2 sources

                while (true) {
                    v3 = ++var3_8;
                    v4 = var5_5.substring(v3, v3 + var4_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl24:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = MiddleClickOnBlockMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    var5_5 = "\u000eD\u008c\u001f\u009d\u00f6\u00c4b\b0h\u00e6\u001e0y\u000e\u0081";
                    var7_6 = 17;
                    var4_7 = 8;
                    var3_8 = -1;
lbl33:
                    // 2 sources

                    while (true) {
                        v6 = ++var3_8;
                        v4 = var5_5.substring(v6, v6 + var4_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl38:
                // 1 sources

                while (true) {
                    var0_3[var6_4++] = MiddleClickOnBlockMode.zkmDecodeUtf8(var8_9).intern();
                    if ((var3_8 += var4_7) < var7_6) {
                        var4_7 = var5_5.charAt(var3_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var1_1.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl50:
                // 1 sources

                ** continue;
            }
        }
        MiddleClickOnBlockMode.FIREWORK = new MiddleClickOnBlockMode(var0_3[0], 0, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$0(), ()Lkotlin/Unit;)(), null, null);
        MiddleClickOnBlockMode.FRIEND = new MiddleClickOnBlockMode(var0_3[2], 1, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$1(), ()Lkotlin/Unit;)(), null, null);
        MiddleClickOnBlockMode.PEARL = new MiddleClickOnBlockMode(var0_3[3], 2, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$2(), ()Lkotlin/Unit;)(), (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$3(), ()Lkotlin/Unit;)());
        MiddleClickOnBlockMode.XP = new MiddleClickOnBlockMode(var0_3[1], 3, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$4(), ()Lkotlin/Unit;)(), null, null);
        MiddleClickOnBlockMode.NONE = new MiddleClickOnBlockMode(var0_3[4], 4, (Function0<Unit>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, _clinit_$lambda$5(), ()Lkotlin/Unit;)(), null, null);
        MiddleClickOnBlockMode.$VALUES = MiddleClickOnBlockMode.$values();
        MiddleClickOnBlockMode.$ENTRIES = EnumEntriesKt.enumEntries((Enum[])MiddleClickOnBlockMode.$VALUES);
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
}

