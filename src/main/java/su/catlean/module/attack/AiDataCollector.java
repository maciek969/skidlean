/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.attack;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.AutoCrystalTargetSortMode;
import su.catlean.util.Globals;
import su.catlean.util.ModuleMessages;
import su.catlean.util.MovementUtil;
import su.catlean.util.combat.TargetUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.zkm.ZkmKeys;

public final class AiDataCollector
extends Module {
    @NotNull
    public static final AiDataCollector INSTANCE;
    @NotNull
    private static final ConcurrentLinkedQueue dataQueue;
    private static float k;
    private static float o;
    private static int z;
    private static File dataFile;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;
    private static final long[] e;
    private static final Integer[] f;
    private static final Map g;

    private AiDataCollector() {
        super("ai-data-collector", Categories.getAttack(), null, null);
    }

    public final int I() {
        return z;
    }

    public final void D(int n) {
        z = n;
    }

    @Override
    public void onEnable(long l) {
        block1: {
            String[] stringArray = new String[]{".ctl", "ai", "data.csv"};
            String string = ((Object)Paths.get(System.getProperty("user.home"), stringArray)).toString();
            dataFile = new File(string);
            File file = dataFile;
            if (file == null) {
                Intrinsics.throwUninitializedPropertyAccessException("outputFile");
                file = null;
            }
            if (file.exists()) break block1;
            this.addSample("delta_yaw,delta_pitch,desired_yaw1,desired_pitch1,desired_yaw2,desired_pitch2,desired_yaw3,desired_pitch3,delta_x,delta_y,delta_z,fallDistance,aimed,distanceFeet,distanceEyes,target_vel_x,target_vel_y,target_vel_z,my_vel_x,my_vel_y,my_vel_z,cd");
        }
    }

    /*
     * Unable to fully structure code
     */
    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent var1_1) {
        block3: {
            var2_2 = AiDataCollector.a ^ 135101329779759L;
            v0 = var2_2 ^ 23336960337924L;
            var11_3 = (int)(v0 >>> 48);
            var12_4 = (int)(v0 << 16 >>> 32);
            var13_5 = (int)(v0 << 48 >>> 48);
            v1 = TargetUtil.Y((char)var11_3, TargetUtil.INSTANCE, 6.0f, AutoCrystalTargetSortMode.DISTANCE, false, false, 12, var12_4, (char)var13_5, null);
            if (v1 == null) {
                return;
            }
            var22_6 = v1;
            if (!(Globals.getMc().crosshairTarget instanceof EntityHitResult)) ** GOTO lbl-1000
            v2 = Globals.getMc().crosshairTarget;
            Intrinsics.checkNotNull(v2, "null cannot be cast to non-null type net.minecraft.world.phys.EntityHitResult");
            if (Intrinsics.areEqual(((EntityHitResult)v2).getEntity(), var22_6)) {
                v3 = 1.0f;
            } else lbl-1000:
            // 2 sources

            {
                v3 = 0.0f;
            }
            var23_7 = v3;
            v4 = var22_6.getEyePos();
            Intrinsics.checkNotNullExpressionValue(v4, "getEyePosition(...)");
            var24_8 = Rotation.Companion.to(v4);
            var25_9 = (var24_8.getYaw() - Globals.getPlayer().getYaw()) / 180.0f;
            var26_10 = (var24_8.getPitch() - Globals.getPlayer().getPitch()) / 90.0f;
            v5 = var22_6.getBoundingBox().getCenter();
            Intrinsics.checkNotNullExpressionValue(v5, "getCenter(...)");
            var27_11 = Rotation.Companion.to(v5);
            var28_12 = (var27_11.getYaw() - Globals.getPlayer().getYaw()) / 180.0f;
            var29_13 = (var27_11.getPitch() - Globals.getPlayer().getPitch()) / 90.0f;
            var30_14 = Rotation.Companion.to(this.getPos(var22_6));
            var31_15 = (var30_14.getYaw() - Globals.getPlayer().getYaw()) / 180.0f;
            var32_16 = (var30_14.getPitch() - Globals.getPlayer().getPitch()) / 90.0f;
            var33_17 = Globals.getPlayer().getX() - var22_6.getX();
            var35_18 = Globals.getPlayer().getY() - var22_6.getY();
            var37_19 = Globals.getPlayer().getZ() - var22_6.getZ();
            var39_20 = (Globals.getPlayer().getYaw() - AiDataCollector.k) / 180.0f;
            var40_21 = (Globals.getPlayer().getPitch() - AiDataCollector.o) / 90.0f;
            AiDataCollector.k = Globals.getPlayer().getYaw();
            AiDataCollector.o = Globals.getPlayer().getPitch();
            var41_22 = Globals.getPlayer().getEyePos().distanceTo(var22_6.getEyePos()) / 6.0;
            var43_23 = Globals.getPlayer().getEyePos().distanceTo(this.getPos(var22_6)) / 6.0;
            var45_24 = new Vec3d(var22_6.getX() - var22_6.lastX, var22_6.getY() - var22_6.lastY, var22_6.getZ() - var22_6.lastZ);
            v6 = Globals.getPlayer().getVelocity();
            Intrinsics.checkNotNullExpressionValue(v6, "getDeltaMovement(...)");
            var46_25 = v6;
            var47_26 = var39_20 + "," + var40_21 + "," + var25_9 + "," + var26_10 + "," + var28_12 + "," + var29_13 + "," + var31_15 + "," + var32_16 + "," + var33_17 + "," + var35_18 + "," + var37_19 + "," + MovementUtil.INSTANCE.getFallDistance() + "," + var23_7 + "," + var43_23 + "," + var41_22 + "," + var45_24.x + "," + var45_24.y + "," + var45_24.z + "," + var46_25.x + "," + var46_25.y + "," + var46_25.z + "," + Globals.getPlayer().getAttackCooldownProgress(0.5f);
            this.addSample(var47_26);
            var48_27 = AiDataCollector.z;
            AiDataCollector.z = var48_27 + 1;
            if (Globals.getPlayer().age % 100 != 0) break block3;
            ModuleMessages.S(this, "saved " + AiDataCollector.z + " lines", false, null);
        }
    }

    private final void addSample(String string) {
        block0: {
            dataQueue.add(string);
            if (dataQueue.size() < 100) break block0;
            this.flush();
        }
    }

    private final void flush() {
        FileWriter fileWriter;
        FileWriter fileWriter2 = fileWriter;
        FileWriter fileWriter3 = fileWriter;
        File file = dataFile;
        if (file == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outputFile");
            file = null;
        }
        fileWriter2(file, true);
        Closeable closeable = fileWriter3;
        Throwable throwable = null;
        try {
            FileWriter fileWriter4 = (FileWriter)closeable;
            while (true) {
                if (!(!((Collection)dataQueue).isEmpty())) break;
                fileWriter4.append((CharSequence)dataQueue.poll());
                fileWriter4.append("\n");
            }
        }
        catch (Throwable throwable2) {
            throw throwable2;
        }
        finally {
            CloseableKt.closeFinally(closeable, throwable);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block16: {
            block15: {
                block14: {
                    block13: {
                        AiDataCollector.a = ZkmKeys.a(-3861804502198670543L, 1664545703830537933L, MethodHandles.lookup().lookupClass()).a(196643281548153L);
                        var20 = AiDataCollector.a ^ 27147447471585L;
                        AiDataCollector.d = new HashMap<K, V>(13);
                        var11_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                        v0 = SecretKeyFactory.getInstance("DES");
                        v1 = new byte[8];
                        v2 = v1;
                        v1[0] = (byte)(var20 >>> 56);
                        for (var12_2 = 1; var12_2 < 8; ++var12_2) {
                            v2 = v2;
                            v2[var12_2] = (byte)(var20 << var12_2 * 8 >>> 56);
                        }
                        var11_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                        var18_3 = new String[15];
                        var16_4 = 0;
                        var15_5 = "q\u00a8g\u00d8\u00b2\u00a3\u00e5m\u00d8\u00c0xoI\f\u00fe\u009b$'\u00cc2%\u00b7\u00a2\u008e\u0099\u00cbz\u00a2\u0007\u009f\u009e7\u0010N\u00eb\u00d0\u00b3}\u008c=\u00d0\u00e4P\u0093\u00d52\n\u00b8\u00fa\u0018L\u00ec\u00a9P4\u0095_f\u001e)\u00bb\u00ac\u00e3\u00a0?\u00b95\u00f3o\u00eb\u00ce\u0083\u0006b@\u00d2R#v#a\u008b\u00ee?wL\t\u007f\u00fe$\u00a0R\"\u0019Q\u009aK\u001d\u0089\u00f0\u0006*\u00dc\u00a0\t\u001d#\u00e4o#\u00edgj\u00df\u0090\u00d8\r\u00eb\u009dD\u00ec\u00f2]`@jF\b\u00c7\u00ed\u0086\u00f9\u00a9Y\u00b1\u00ff\u00d9\u008d\u00a5 H\u00dc\u0002^N\u00dd\u00bai\u00fdD\u00bf\u00d8\u00bei\u009e=\u00e2\u00df\u000b\u0099\u00cbS!\u0004\u0017\u00ab\u001c\u001cd\u009e\u00a2/(\u0000\u0089S\u0086\u00ad\u0017\u00dagTmTj\u00f7Yr\u00c7\u00ab\u0012\u00dcjM\u00ce\u00b1K\f\u0099,TM-\u008c\u000f\u00f1\u00aa\u00f7\u00ed%)0V\u0010s\u00af\u00fd\u00c6\u00d9\u00f4c\u008a\u009f=/tg\u009f{r(\u0014?\u00c6\u00a5\u00a5\u0011B\u00ec\u00fc\u00a5\u009c\u00c2=C\u001aR\u009b\u008e.~S\u00fa\u00e47\u00f4A\u0000\u00fd\u000b,\u00b1\u00b0\u0004\u00d3a\u00c5\u00c7\u00b0\rU\u0010\u00fc6+X\u00a6}z7\u0094F\u00cca\u000f\u00ee\u009b\u00cd(\u0098\u00d39\u009c\u00a4p\u00a0N\f5\u00ed\u0007R}T\u00be>\u00b0\u00bb\u00a98\u00c6\u0084\u009c\u0006\u00a3J\u00bc~\u00f8\u001c#\u00b0B\u00b5\u008a\u0090pl\u00dd \u0083\u0085\u00a0\u001a\u0007IQ\u00abH]\u00c6\u00b5\f\u00da\u008c\u00f0\u00f8/@\nB\u00ef\u00b2\u0086\u00c5<CY.x\u00e6\u0095\u0170q6\u00ed`>\u0011l\"`#\u0095\u00c3L{\u00c1\u001a,M\u00dcT\u0014W\u00bfr\u0098?*\u0094\u00e0\u0082\u001b4\u0017(\u00a32\u00e2\u00d7\u00fc\u009a\u0099\u00a9\u00e5e\u00f5\u0001U\u00a9\u00d6]%\u0006\u0086!3D\u00ef\u00fa\u00f7\u00bb\u00d0W\u00be\u00b6\u00f7MR\u00cb\u00fc]\u00fe\u009c`\u00eb\u00b7\u00bc\u000b\u0005\u0018\u00fa\u0002\u00e6nv\u00056\u00f5\u009eE\u00d6\u00d23v\u00ea/\u00b7\u00e8\u001f\u008c\n\u00d1\u00f2\u008b\u00d7>\u0002\"\u0093\u0003\u00c2'\u00a6\u00e1\u0080\u00c4L\u0099.^\u0017\u00dc\u0094K\u00a3\u00845\u008f\u0011\u00d4[\u00b0\u00d92\u00b7\f$$/5\u0080\u00f7Q\u00d5,k\u00c7\u00c9-Q\u00f9|[\u00f2\"\u0010\u00e3\u0016\u00c2\u0003S\u00bd\u00820\u00d0\u0017\u0096+\u008b\u00ac\u00e6S\u00b8\u00ac\u00daNh\u00be\u00e6\u00a7\u00f1\u00f3\rk\u00d3\u00cd\u00e0\u00f8k\u00ce\u00c7H\u0088\u00f7\u00cci\u00e0\u00d8T)\u00c3\u00eb\f\u00d5\u00c8E\u0012\u0085\u0019ts\u00a6\u009c\u00a4\u00ad<\u00f0\u0019\u00d8\u00fd\u001d\u00a3\u00cd\u008d\u0083Z>`/d\u00b0t\u00bfAg\u00dc\u008d-\u00d3F}^\u0088\u00a9~\u00e5\t\u000b\u00ce\u00fdM\u0092\u00b5\u001cG|aL^\u0014\u00a7\u00a2\u0096\f\u00ad@\u0018\u00eb\u00e1\u00ebW\u00b3\u00ff\u00df=S\u00ba\rS\u00c6\u00c3\u0095)3\u00a5?\u0091\u0083\u00d4\u00d7-\u0088\u0007\u00a8j\u00d9\u001bZ-d\u00b8Y\u00a3f\u00c3\u0080~\u00f9\u00c6\u000e\u00ab\u00fb\u0090\u0007YX\\?|\u00cc!\u00c7\u00ac\u00b6\u008e\u00d4\u00ca\u00f25 \u0092\u00c2\u00edx\u00b2\u00ec\u00c4\u00f1}B{\u00cc\u0000\u008d\u001f0\u00c4\u00f7\u00ad\u00a4\f\u00a0\u00fc\u00c2\u0016\u0086\u0085\u0005\u0096D\u00f6\u00bc\u00ce~O\u00ef\u00c0\u00ad#\u0010\u00d0_zzw,p\u0095\u0082\u00c6E9\u0005\u00f3\u00ba?";
                        var17_6 = 748;
                        var14_7 = 32;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block13;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AiDataCollector.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "o\u00d5*C`\u00c7\u00d9\u00bc\u000e\u00ff\u00a5\u0082\u00c8\u0098\u001d\u008c\u008eq\u00d8g\u00a8\u0010\u0092\u00c2\u00c9\u0080\u0017*\u0003W\u00cf\u0095\u00ecLCB\u008e\u00c4\u00d4/.\u0011h}*\u00f4\u00b8\u00c3\u0098E\u00fdZ\u007f\u00b7Z\u0013\u00fa\u00b9\u00c0ZX\u0084\u00c9\u00ce\u00c3-=\u0002b9\u00f5K\u00dc\u0092-\u00f7\u001eIG\u00c0\u00dd\\\u00a3\u00f8U\u00f8\u00d9\u00ed\u0006C\u008e>\u00b6?\u00ec\u0089\u00f5\u0017Y\u00ab\u009f1\u00b2rN\u0098\u00f3I\u0099qE\u00a7Z'z\u00ea0\u00d8\u00b3k \u00ee\u0097v\u0085n\u00b2\u00dbi\u00a7\u009d\u00e0\u0085\u009e\u00bc.\u0094\u009f\u00eej\u00e4\u00d7\u00c9\u00905\u0096\u00963\u0099\u00c5B;\\";
                            var17_6 = 153;
                            var14_7 = 120;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block13;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = AiDataCollector.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block14;
                            break;
                        }
                    }
                    var19_9 = var11_1.doFinal(v4.getBytes("ISO-8859-1"));
                    switch (v5) {
                        default: {
                            ** continue;
                        }
                        ** case 0:
lbl51:
                        // 1 sources

                        ** continue;
                    }
                }
                AiDataCollector.b = var18_3;
                AiDataCollector.c = new String[15];
                AiDataCollector.g = new HashMap<K, V>(13);
                var0_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var1_11 = 1; var1_11 < 8; ++var1_11) {
                    v9 = v9;
                    v9[var1_11] = (byte)(var20 << var1_11 * 8 >>> 56);
                }
                var0_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var6_12 = new long[3];
                var3_13 = 0;
                var4_14 = "\u0091\u00db(\u009at\f\u00c9\u00a7\u00c2F\n\\\u0094\u001c\u00af=\u00e0\u00f58\u00bb\tZ94";
                var5_15 = 24;
                var2_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl73:
                // 1 sources

                while (true) {
                    var6_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var2_16 < var5_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
            v10 = var3_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        AiDataCollector.e = var6_12;
        AiDataCollector.f = new Integer[3];
        AiDataCollector.INSTANCE = new AiDataCollector();
        AiDataCollector.dataQueue = new ConcurrentLinkedQueue<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3737;
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
                throw new RuntimeException("su/catlean/u9", exception);
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
            AiDataCollector.c[n2] = AiDataCollector.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = AiDataCollector.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x9CF;
        if (f[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = e[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])g.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u9", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            AiDataCollector.f[n2] = n3;
        }
        return f[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = AiDataCollector.zkmDecryptInt(n, l);
        MethodHandle methodHandle = MethodHandles.constant(Integer.TYPE, n2);
        mutableCallSite.setTarget(MethodHandles.dropArguments(methodHandle, 0, new Class[]{Integer.TYPE, Long.TYPE}));
        return n2;
    }

    private static CallSite zkmBootstrap2(MethodHandles.Lookup lookup, String string, MethodType methodType) {
        MutableCallSite mutableCallSite = new MutableCallSite(methodType);
        try {
            mutableCallSite.setTarget(MethodHandles.explicitCastArguments(MethodHandles.insertArguments(cfr_ldc_1().asCollector(Object[].class, methodType.parameterCount()), 0, lookup, mutableCallSite, string), methodType));
        }
        catch (Exception exception) {
            throw new RuntimeException("su/catlean/u9" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(AiDataCollector.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_1() {
        try {
            return MethodHandles.lookup().findStatic(AiDataCollector.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

