/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.protect;

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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockBreakingProgressS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.network.AfterReceivePacket;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.RotatingModule;
import su.catlean.module.protect.Blocker$EntriesMappings;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.BreakTarget;
import su.catlean.util.Globals;
import su.catlean.zkm.ZkmKeys;

public final class Blocker
extends RotatingModule {
    @NotNull
    public static final Blocker INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting triggerProgress$delegate;
    @NotNull
    private static final BooleanSetting surround$delegate;
    @NotNull
    private static final BooleanSetting head$delegate;
    @NotNull
    private static List breakTargets;
    private static final long c;
    private static final String[] i;
    private static final String[] o;
    private static final Map t;
    private static final long[] w;
    private static final Integer[] B;
    private static final Map I;

    private Blocker() {
        super("blocker", Categories.getProtect(), 200, false);
    }

    private final float getTriggerProgress() {
        return ((Number)triggerProgress$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    private final void setTriggerProgress(float f) {
        triggerProgress$delegate.setValue(this, delegatedProperties[0], Float.valueOf(f));
    }

    private final boolean getSurround() {
        return (Boolean)surround$delegate.getValue(this, delegatedProperties[1]);
    }

    private final void setSurround(boolean bl) {
        surround$delegate.setValue(this, delegatedProperties[1], bl);
    }

    private final boolean getHead() {
        return (Boolean)head$delegate.getValue(this, delegatedProperties[2]);
    }

    private final void setHead(boolean bl) {
        head$delegate.setValue(this, delegatedProperties[2], bl);
    }

    @Flow
    private final void onAfterReceivePacket(AfterReceivePacket afterReceivePacket) {
        if (afterReceivePacket.getPacket() instanceof BlockBreakingProgressS2CPacket) {
            Packet<?> packet = afterReceivePacket.getPacket();
            Intrinsics.checkNotNull(packet, "null cannot be cast to non-null type net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket");
            BlockBreakingProgressS2CPacket blockBreakingProgressS2CPacket = (BlockBreakingProgressS2CPacket)packet;
            List list = this.findBreakingBlocks();
            Entity entity = Globals.getPlayer();
            BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
            Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
            BlockPos blockPos2 = blockPos;
            if (blockBreakingProgressS2CPacket.getProgress() == 0) {
                if (this.getSurround() && list.contains(blockBreakingProgressS2CPacket.getPos())) {
                    BlockPos blockPos3 = blockBreakingProgressS2CPacket.getPos();
                    Intrinsics.checkNotNullExpressionValue(blockPos3, "getPos(...)");
                    breakTargets.add(new BreakTarget(blockPos3, 0, false, 6, null));
                }
                if (this.getHead() && (Intrinsics.areEqual(blockBreakingProgressS2CPacket.getPos(), blockPos2.up(2)) || Intrinsics.areEqual(blockBreakingProgressS2CPacket.getPos(), blockPos2.up(3)))) {
                    BlockPos blockPos4 = blockBreakingProgressS2CPacket.getPos();
                    Intrinsics.checkNotNullExpressionValue(blockPos4, "getPos(...)");
                    breakTargets.add(new BreakTarget(blockPos4, 0, true, 2, null));
                }
            }
        }
    }

    @Flow
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        long l = c ^ 0x4A79618570DDL;
        long l2 = l ^ 0x51616A31E361L;
        Iterator iterator2 = breakTargets.iterator();
        while (iterator2.hasNext()) {
            BreakTarget breakTarget = (BreakTarget)iterator2.next();
            int n = breakTarget.getProgress();
            breakTarget.setProgress(n + 1);
            if (!((float)breakTarget.getProgress() / 40.0f > this.getTriggerProgress())) continue;
            RotatingModule.s(this, this.getProtectPositions(breakTarget.getPos(), breakTarget.isHead()), false, breakTarget.isHead(), null, false, 26, l2, null);
            breakTarget.setProgress(Math.max(150, breakTarget.getProgress()));
        }
        breakTargets.removeIf(arg_0 -> Blocker.invokePredicate(Blocker::onPlayerUpdate$lambda$0, arg_0));
    }

    private final List getProtectPositions(BlockPos blockPos, boolean bl) {
        List list = new ArrayList();
        if (bl) {
            BlockPos blockPos2 = blockPos.up();
            Intrinsics.checkNotNullExpressionValue(blockPos2, "above(...)");
            list.add(blockPos2);
        } else {
            for (Direction direction : Blocker$EntriesMappings.enumEntries) {
                BlockPos blockPos3 = blockPos.offset(direction);
                Intrinsics.checkNotNullExpressionValue(blockPos3, "relative(...)");
                list.add(blockPos3);
            }
        }
        return list;
    }

    private final List findBreakingBlocks() {
        List list = new ArrayList();
        Entity entity = Globals.getPlayer();
        BlockPos blockPos = BlockPos.ofFloored(this.getPos(entity));
        Intrinsics.checkNotNullExpressionValue(blockPos, "containing(...)");
        BlockPos blockPos2 = blockPos;
        Object object3 = Blocker$EntriesMappings.enumEntries;
        Object object2 = object3;
        Collection collection = new ArrayList();
        Iterator iterator2 = object2.iterator();
        while (iterator2.hasNext()) {
            Object t = iterator2.next();
            Direction direction = (Direction)t;
            if (!(direction != Direction.UP && direction != Direction.DOWN)) continue;
            collection.add(t);
        }
        for (Object object3 : (List)collection) {
            BlockPos blockPos3;
            Intrinsics.checkNotNullExpressionValue(blockPos2.offset((Direction)object3), "relative(...)");
            Intrinsics.checkNotNullExpressionValue(Globals.getWorld().getBlockState(blockPos3), "getBlockState(...)");
            if (!Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object2).getBlock(), Blocks.OBSIDIAN) && !Intrinsics.areEqual(((AbstractBlock.AbstractBlockState)object2).getBlock(), Blocks.ENDER_CHEST)) continue;
            list.add(blockPos3);
        }
        return list;
    }

    private static final boolean onPlayerUpdate$lambda$0(BreakTarget breakTarget) {
        Intrinsics.checkNotNullParameter(breakTarget, "it");
        return breakTarget.getProgress() > 200;
    }

    private static final boolean invokePredicate(Function1 function1, Object object) {
        return (Boolean)function1.invoke(object);
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Blocker.c = ZkmKeys.a(-7110128185672644125L, 7063535348462981205L, MethodHandles.lookup().lookupClass()).a(178820473671855L);
                        var20 = Blocker.c ^ 133453671981249L;
                        Blocker.t = new HashMap<K, V>(13);
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
                        var18_3 = new String[20];
                        var16_4 = 0;
                        var15_5 = "u[\u00a1\u00cd\u00cc\u009b\u009c[\u009c\bT\u0082\u00f7P=\u0094e|\u00f5\u008b\u0015\u001e\u000b2\u00c5\u0097\u009e\u00d3\u00f3&/\u00fe_\u00c7;\u00c9\b\u0002\u0014\u00cc\u0010r\u00a1\u00cb\u00aa\u00d1=&\u0018Q{\u00c1\u00a1k\u00bf_\u0098 \u00d23\u0018\u0082Z\u009b\u0098\u00b3\u00a9;\u00f3B\u0011:Ud\u0098\u00c9f\u0004aQ\u00f5J_\u00adx\u00b8\u0095\u00dd\u00e4\u009f\u00a8\u00a9q\u00b6\u00d8\u00f1\u00e9!/\u00d8\u00ack\u00ecm\u0083\u00d4\u00fb'\u00db7#\u00b5\u00bet:b\u00d2$\u00ef\u0083\u0097\u00e8\u00b1T\u00adI\u00c9\u00cc\u0017\u0000\u00157\u00c9\n^\u00b2\u00c2h\u00b5\u000eJ\u00e55J\r\u00b9\u00b6\u00d9\u0085\u0092j\u0016W\u00f9\u00e6\u0015\u0081@\u0092\u00d6+x\u00b0\u0097e\u0005j\u00f5\u00c0\u00abW\u000f\u00d5\u00df\u00e3L\u00bb\u00c8\b\u00a0&eYa\u00cb\u00e2\u00dd\u0001\";\u00c9\u0007\u00b0\u00ae*\u000bg?P\u0000\u00de\u00af\u00a5q8[\n{\u0081<[\u00e1]\u00036I\u008e\u00eddy.q>\u00bf\u008d\u008a4b\u00c9\u00e1`\u00d4\u00a0d\u008c\u0081\u009a\u00b09c\u00fc\u00d3\u00c4\n\u00bf\u00b7\u00b1\u0015\u00cbHnG\u00bb\u0090\u00b34\u0002\u00b1\u00f9 \u0094Y\u00a2\u009a\u00b14\u0083|\u00fc\u0019h\u00ca\u001e%\u00a9L\u0019\u00893\u0017\u00d6^\u00b2\fR\u008d\u00e5aB\u000b/\u00af0\u00ef\u00f2\u00e2\u00db\u0089\u00d5\u0014#\u00c8\u00b8h\u0014\u00e5Q\u00fa\u0014F\u00de\u001b\u0002Qgt\u0005C\u00c9Su\u00fc\u001e<\u00a23\u0018P\u0014\u00b4\u00cb/8\u00e7\u0095T{\u0093\u00a5\t\u00ea \u00f0&\u0010\u008f\u0001[\u00ca\u0085q\u0001\u0015\u009a\u0004l\u00c48\u00b6o\u00ab]\u00fa\u00d0U\u008d<F\u008f\u0014u\u009d;\u0001 \u001f\u00fe\u0097\u008d\u00fc\u009b\u00ba>\u00d5\u008e[\u00c6\u0093Y9)\u00f9\u00da\n>\u00a6\u0012\u00de\u00b83JPn\u00a9\u00ef[\u00cd\u0018\u000f\u00bc\u0087\u00e2\u001c\"\u00e3\u00cdev\u0003\u00d3jl\u00c7i\u00c5\u00d7\u00a5\u00a57\u00ad\u0011\u00a7\u0018\u001ev*\u0014a\u00b5$\u008fY\u008dP\u00b5\u0017\u0017\u0080}\u00edq\u00c9\u00b6*z\u00be^\u0018\u00c3\u001f\u00f4\u008d\u00eb\u0087\u008d\u00d7\u00db\u00e7\u00fcv?\u00fc\u00c2boG\u00aa\u0096D!\u00c5\u00ca\u0010\u00e0\u0096\n\u0004\u00a6\u0005\u0018Z\u0015\u00de\u001d$\u00a1\u00bb\u008d\u00d3(i+ h\u008e\u001a\u00b9\u0096\u0097\u001b)g\u00be\u007f\u0017T\u00f7cf\u00fa4\u00f8~A7D\u00ee\u00aa\u00f9\u00a2F\u00fc\u00ec\u00908\u00c6g\u0082Ia\u0018=\u00c3\u00fbf\u00a4\f\u009f\u00ffTr\u0019\u00f9\u00a5\u0017\u00c60j\u0092\u00ee?\u00eb\u00c2\u00a8/\u0010\u00c0\u0003,\u00f0\u00ea\u00dc\u0001f\u00b3\u000e\"f\u00cb\u0014\u0004v\u0010\u00b5I.\u00bd\u00e3\u00bem\u00da\u00d3\u009a\u00c3\u0016\u009a\u00c9:\u00fa \u008c\u00f2\u00d4\tc\u00c4\u00c4S0\u001d\u0081\u0019E\u008b,\u00ad\u00a8Z\u008a\u00da\u00dd\u00c0\u0094\u00d2\u00e4\u0091\u00f1\u00d9.\u00d1C\u00cb \u0084QZ\u0081\u00c1\u0002Vk\u00a2xzK\u0010\u00aewP?x\u0083\n\u000b\u0086\u00b2\u00a2L\u00f3\u00d2I\u0080\u00ed\"[";
                        var17_6 = 665;
                        var14_7 = 40;
                        var13_8 = -1;
lbl20:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl25:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Blocker.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b8S\u0093\u00f1\u00f2^u\u00c5d\u00cf\u00a4_\u0005U\u00d9\u009f\u008a\u00c2Y\u00f7\u00fce\u000e\u00d7 3\u00db\u00b9R\u00fc\u00bd\u00e5\u001d\u00e7T\u00b9\u00a9\u00d3\u00f1m\u00f6\u0098\u0087f\u00b6uA|6\u0085\u000b\u00fc\u00d66\u008c\u00bd?";
                            var17_6 = 57;
                            var14_7 = 24;
                            var13_8 = -1;
lbl34:
                            // 2 sources

                            while (true) {
                                v6 = ++var13_8;
                                v4 = var15_5.substring(v6, v6 + var14_7);
                                v5 = 0;
                                break block18;
                                break;
                            }
                            break;
                        }
lbl39:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Blocker.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            break block19;
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
                Blocker.i = var18_3;
                Blocker.o = new String[20];
                Blocker.I = new HashMap<K, V>(13);
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
                var6_12 = new long[8];
                var3_13 = 0;
                var4_14 = "\u00137\u0002\t\u00fe\u0080\u001e~\u00b1\u0006('\u00b4\u00af\u00f4\u00c3lsY?\u00e4G\u00b0D\u00d589\f\u00a5\u00f8o\u00a0\u00c7\u0012\u00d8b\u00b6\u00a9\u00ecJ,c\u00cc\u0019\u001d\u00af\u00b2X";
                var5_15 = 48;
                var2_16 = 0;
                while (true) {
                    var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                    v10 = var6_12;
                    v11 = var3_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "4U\u0080\u009bn\u00aa3\u0091\u0001\u0086\u00cf\u00bc\u0005\u0087\u0018\u00c1";
                    var5_15 = 16;
                    var2_16 = 0;
                    while (true) {
                        var7_17 = var4_14.substring(var2_16, var2_16 += 8).getBytes("ISO-8859-1");
                        v10 = var6_12;
                        v11 = var3_13++;
                        v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                        v13 = 0;
                        break block20;
                        break;
                    }
                    break;
                }
lbl91:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var0_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            v14 = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
            switch (v13) {
                default: {
                    ** continue;
                }
                ** case 0:
lbl104:
                // 1 sources

                ** continue;
            }
        }
        Blocker.w = var6_12;
        Blocker.B = new Integer[8];
        var28_20 = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Blocker.class, "triggerProgress", "getTriggerProgress()F", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Blocker.class, "surround", "getSurround()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Blocker.class, "head", "getHead()Z", 0))};
        Blocker.delegatedProperties = var28_20;
        Blocker.INSTANCE = new Blocker();
        Blocker.triggerProgress$delegate = SettingFactory.floatSetting$default(Blocker.INSTANCE, "trigger-progress", 0.8f, RangesKt.rangeTo(0.0f, 0.9f), null, null, 24, null);
        Blocker.surround$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blocker.INSTANCE, "surround", true, null, null, 12, null);
        Blocker.head$delegate = SettingFactory.booleanSetting$default((SettingContainer)Blocker.INSTANCE, "head", true, null, null, 12, null);
        Blocker.breakTargets = new ArrayList<E>();
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x77F2;
        if (o[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])t.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    t.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_k", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = i[n2].getBytes("ISO-8859-1");
            Blocker.o[n2] = Blocker.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return o[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Blocker.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/_k" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5F7F;
        if (B[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = w[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])I.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    I.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/_k", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Blocker.B[n2] = n3;
        }
        return B[n2];
    }

    private static int f(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Blocker.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/_k" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Blocker.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Blocker.class, "f", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

