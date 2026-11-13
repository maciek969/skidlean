/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.viaversion.viaversion.api.Via
 *  com.viaversion.viaversion.api.connection.UserConnection
 *  com.viaversion.viaversion.api.protocol.packet.PacketType
 *  com.viaversion.viaversion.api.protocol.packet.PacketWrapper
 *  com.viaversion.viaversion.api.type.Type
 *  com.viaversion.viaversion.api.type.Types
 *  com.viaversion.viaversion.protocols.v1_21_4to1_21_5.packet.ServerboundPackets1_21_5
 *  com.viaversion.viaversion.protocols.v1_21_5to1_21_6.Protocol1_21_5To1_21_6
 *  net.fabricmc.loader.api.FabricLoader
 */
package su.catlean.util;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.packet.PacketType;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.type.Type;
import com.viaversion.viaversion.api.type.Types;
import com.viaversion.viaversion.protocols.v1_21_4to1_21_5.packet.ServerboundPackets1_21_5;
import com.viaversion.viaversion.protocols.v1_21_5to1_21_6.Protocol1_21_5To1_21_6;
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
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.input.Input;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import org.jetbrains.annotations.NotNull;
import su.catlean.util.Globals;
import su.catlean.util.InputUtil;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class PlacementHelper {
    @NotNull
    public static final PlacementHelper INSTANCE;
    private static String[] v;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private PlacementHelper() {
    }

    /*
     * Unable to fully structure code
     */
    public final void setViaVersionFix(boolean var1_1) {
        block20: {
            block19: {
                block24: {
                    block18: {
                        block23: {
                            block22: {
                                var13_2 = new String[]{};
                                if (!FabricLoader.getInstance().isModLoaded("viafabricplus")) ** GOTO lbl-1000
                                break block22;
                                catch (Throwable v0) {
                                    throw v0;
                                }
                            }
                            v1 = Via.getManager().getConnectionManager().getConnections();
                            Intrinsics.checkNotNullExpressionValue(v1, "getConnections(...)");
                            v2 = CollectionsKt.firstOrNull(v1);
                            v4 = (UserConnection)v2;
                            if (v4 == null) break block18;
                            if ((v4 = v4.getProtocolInfo()) == null) break block18;
                            break block23;
                            catch (Throwable v5) {
                                throw v5;
                            }
                        }
                        if ((v4 = v4.getPipeline()) == null) break block18;
                        v7 = v4.contains(Protocol1_21_5To1_21_6.class);
                        break block24;
                    }
                    v7 = false;
                }
                if (v7) {
                    v8 = true;
                } else lbl-1000:
                // 2 sources

                {
                    v8 = false;
                }
                var14_3 = v8;
                if (var14_3) break block19;
                v10 = Globals.getPlayer().input;
                Intrinsics.checkNotNullExpressionValue(v10, "input");
                var11_4 = var1_1;
                var12_5 = v10;
                PacketUtil.sendPacket(new PlayerInputC2SPacket(InputUtil.withSneak((Input)var12_5, (boolean)var11_4).playerInput));
                break block20;
            }
            var15_6 = this;
            try {
                block25: {
                    block21: {
                        var16_7 = var15_6;
                        v12 = (PacketType)ServerboundPackets1_21_5.PLAYER_COMMAND;
                        v13 = Via.getManager().getConnectionManager().getConnections();
                        Intrinsics.checkNotNullExpressionValue(v13, "getConnections(...)");
                        var18_8 = PacketWrapper.create((PacketType)v12, (UserConnection)((UserConnection)CollectionsKt.firstOrNull(v13)));
                        var18_8.write((Type)Types.VAR_INT, (Object)Globals.getPlayer().getId());
                        v14 = var18_8;
                        v15 = (Type)Types.VAR_INT;
                        if (!var1_1) break block21;
                        v17 = 0;
                        break block25;
                    }
                    v17 = 1;
                }
                v14.write(v15, (Object)v17);
                var18_8.write((Type)Types.VAR_INT, (Object)0);
                var18_8.scheduleSendToServer(Protocol1_21_5To1_21_6.class);
                var16_7 = Result.constructor-impl(Unit.INSTANCE);
            }
            catch (Throwable var17_9) {
                var16_7 = Result.constructor-impl(ResultKt.createFailure(var17_9));
            }
            Result.box-impl(var16_7);
        }
        Globals.getPlayer().setSneaking(var1_1);
        Globals.getMc().options.sneakKey.setPressed(var1_1);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block11: {
            block10: {
                PlacementHelper.a = ZkmKeys.a(4885336872334738754L, -2517205089667537492L, MethodHandles.lookup().lookupClass()).a(268589753063600L);
                var9 = PlacementHelper.a ^ 94152249705421L;
                PlacementHelper.d = new HashMap<K, V>(13);
                PlacementHelper.zkmSetFlag(new String[1]);
                var0_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                v0 = SecretKeyFactory.getInstance("DES");
                v1 = new byte[8];
                v2 = v1;
                v1[0] = (byte)(var9 >>> 56);
                for (var1_2 = 1; var1_2 < 8; ++var1_2) {
                    v2 = v2;
                    v2[var1_2] = (byte)(var9 << var1_2 * 8 >>> 56);
                }
                var0_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                var7_3 = new String[4];
                var5_4 = 0;
                var4_5 = "\u0084B0\u00b4\u0015\u00b7\u0098\u00fa|\u00e4Y\u00c7\u0018\u008e\u00df\u00fc\u0010j\r\u00f1 \u0091&\u00b6\u00c2='?:\u00ac\u0017y\u00efdWL\u00c3\u00cd\u0002\u0082 \u0010\u00ed\u00ed\u00d35\u00ba\u0017\u00f2\u0017\u00cf\u0014\u001e\u00ef\u00d1i\u0004$\u0010\u001d0H\u00c4\u008c\u0001\u00a5\u00e8m\u00c3\u00aa\u00ed\u009d\u00c1";
                var6_6 = 73;
                var3_7 = 40;
                var2_8 = -1;
lbl21:
                // 2 sources

                while (true) {
                    v3 = ++var2_8;
                    v4 = var4_5.substring(v3, v3 + var3_7);
                    v5 = -1;
                    break block10;
                    break;
                }
lbl26:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = PlacementHelper.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    var4_5 = "\u000e\t\u00f2\u00d1\u00d2\u00aa\u0013\u009d>\u00db\u0003\u00b0\u0093\u00a8\u009fN\u00b6[\u00d5$X\u00a1\u00bbWV5;\u0001\u00b0X+x\u00d6\u00f4Q\u0097\u0000 \u0000\u0082\u0010\u00a9\u00b6O\u000f\u00c2\"\u00dc'&\u00d1?I5\u00b5\u0015v";
                    var6_6 = 57;
                    var3_7 = 40;
                    var2_8 = -1;
lbl35:
                    // 2 sources

                    while (true) {
                        v6 = ++var2_8;
                        v4 = var4_5.substring(v6, v6 + var3_7);
                        v5 = 0;
                        break block10;
                        break;
                    }
                    break;
                }
lbl40:
                // 1 sources

                while (true) {
                    var7_3[var5_4++] = PlacementHelper.zkmDecodeUtf8(var8_9).intern();
                    if ((var2_8 += var3_7) < var6_6) {
                        var3_7 = var4_5.charAt(var2_8);
                        ** continue;
                    }
                    break block11;
                    break;
                }
            }
            var8_9 = var0_1.doFinal(v4.getBytes("ISO-8859-1"));
            switch (v5) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl52:
                // 1 sources

                ** continue;
            }
        }
        PlacementHelper.b = var7_3;
        PlacementHelper.c = new String[4];
        PlacementHelper.INSTANCE = new PlacementHelper();
    }

    public static void zkmSetFlag(String[] stringArray) {
        v = stringArray;
    }

    public static String[] zkmGetFlag() {
        return v;
    }

    private static Throwable zkmRethrow(Throwable throwable) {
        return throwable;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7AF;
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
                throw new RuntimeException("su/catlean/wu", exception);
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
            PlacementHelper.c[n2] = PlacementHelper.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = PlacementHelper.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/wu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(PlacementHelper.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

