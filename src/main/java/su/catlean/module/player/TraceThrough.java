/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.player;

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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.world.RaycastContext;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.player.AttackEvent;
import su.catlean.api.event.events.player.UpdateCrosshairTarget;
import su.catlean.gofra.Flow;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.PlaceManager;
import su.catlean.zkm.ZkmKeys;

public final class TraceThrough
extends Module {
    @NotNull
    public static final TraceThrough INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final BooleanSetting attackEntities$delegate;
    @NotNull
    private static final BooleanSetting ignoreEntities$delegate;
    @NotNull
    private static final BooleanSetting openContainers$delegate;
    private static final long b;
    private static final String[] c;
    private static final String[] d;
    private static final Map e;

    private TraceThrough() {
        super("trace-through", Categories.getPlayer(), null, null);
    }

    private final boolean getAttackEntities() {
        return (Boolean)attackEntities$delegate.getValue(this, delegatedProperties[0]);
    }

    private final boolean getIgnoreEntities() {
        return (Boolean)ignoreEntities$delegate.getValue(this, delegatedProperties[1]);
    }

    private final boolean getOpenContainers() {
        return (Boolean)openContainers$delegate.getValue(this, delegatedProperties[2]);
    }

    @Flow
    private final void onUpdateCrosshairTarget(UpdateCrosshairTarget updateCrosshairTarget) {
        if (this.getIgnoreEntities()) {
            Entity entity;
            Profilers.get().pop();
            updateCrosshairTarget.cancel();
            MinecraftClient minecraftClient = Globals.getMc();
            Entity entity2 = Globals.getMc().getCameraEntity();
            Intrinsics.checkNotNull(entity2);
            minecraftClient.crosshairTarget = this.raycast(entity2, Globals.getPlayer().getBlockInteractionRange(), RenderState.INSTANCE.getTickDelta());
            MinecraftClient minecraftClient2 = Globals.getMc();
            if (Globals.getMc().crosshairTarget instanceof EntityHitResult) {
                HitResult hitResult = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(hitResult, "null cannot be cast to non-null type net.minecraft.world.phys.EntityHitResult");
                entity = ((EntityHitResult)hitResult).getEntity();
            } else {
                entity = null;
            }
            minecraftClient2.targetedEntity = entity;
        }
    }

    private final HitResult raycast(Entity entity, double d, float f) {
        HitResult hitResult = entity.raycast(d, f, false);
        Intrinsics.checkNotNullExpressionValue(hitResult, "pick(...)");
        HitResult hitResult2 = hitResult;
        Vec3d vec3d = entity.getCameraPosVec(f);
        Intrinsics.checkNotNullExpressionValue(vec3d, "getEyePosition(...)");
        Vec3d vec3d2 = vec3d;
        Vec3d vec3d3 = hitResult2.getPos();
        Intrinsics.checkNotNullExpressionValue(vec3d3, "getLocation(...)");
        Vec3d vec3d4 = vec3d3;
        if (!vec3d4.isInRange(vec3d2, d)) {
            Vec3d vec3d5 = hitResult2.getPos();
            Intrinsics.checkNotNullExpressionValue(vec3d5, "getLocation(...)");
            Vec3d vec3d6 = vec3d5;
            Direction direction = Direction.getFacing(vec3d6.x - vec3d2.x, vec3d6.y - vec3d2.y, vec3d6.z - vec3d2.z);
            Intrinsics.checkNotNullExpressionValue(direction, "getApproximateNearest(...)");
            Direction direction2 = direction;
            BlockHitResult blockHitResult = BlockHitResult.createMissed(vec3d6, direction2, BlockPos.ofFloored(vec3d6));
            Intrinsics.checkNotNullExpressionValue(blockHitResult, "miss(...)");
            return blockHitResult;
        }
        return hitResult2;
    }

    @Flow
    public final void onAttack(@NotNull AttackEvent attackEvent) {
        Entity entity;
        Intrinsics.checkNotNullParameter(attackEvent, "e");
        if (this.getAttackEntities() && Globals.getMc().crosshairTarget instanceof BlockHitResult && (entity = MovementUtil.INSTANCE.raycastEntity(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), (float)Globals.getPlayer().getEntityInteractionRange(), true, false)) != null) {
            Globals.getInteractionManager().attackEntity(Globals.getPlayer(), entity);
            Globals.getPlayer().swingHand(Hand.MAIN_HAND);
            attackEvent.cancel();
        }
    }

    @Flow
    public final void onInput(@NotNull InputEvent inputEvent) {
        Intrinsics.checkNotNullParameter(inputEvent, "e");
        if (this.getOpenContainers() && Globals.getMc().player != null && inputEvent.getDevice() == InputEvent.Device.Mouse && inputEvent.getAction() == InputEvent.Action.Press && inputEvent.getKey() == 1 && !Globals.getPlayer().isSneaking() && Globals.getMc().currentScreen == null) {
            int n;
            int n2;
            if (Globals.getMc().crosshairTarget instanceof BlockHitResult) {
                ClientWorld clientWorld = Globals.getWorld();
                HitResult hitResult = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(hitResult, "null cannot be cast to non-null type net.minecraft.world.phys.BlockHitResult");
                if (clientWorld.getBlockState(((BlockHitResult)hitResult).getBlockPos()).hasBlockEntity()) {
                    return;
                }
            }
            if ((n2 = 1) <= (n = (int)Globals.getPlayer().getBlockInteractionRange())) {
                while (true) {
                    BlockPos blockPos;
                    Intrinsics.checkNotNullExpressionValue(BlockPos.ofFloored(Globals.getPlayer().getEyePos().add(MovementUtil.INSTANCE.getLookVector(MovementUtil.INSTANCE.getLastPitch(), MovementUtil.INSTANCE.getLastYaw()).multiply(n2))), "containing(...)");
                    if (Globals.getWorld().getBlockState(blockPos).hasBlockEntity()) {
                        RaycastContext raycastContext = new RaycastContext(Globals.getPlayer().getEyePos(), blockPos.toCenterPos(), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, Globals.getPlayer());
                        BlockState blockState = Globals.getWorld().getBlockState(blockPos);
                        Intrinsics.checkNotNullExpressionValue(blockState, "getBlockState(...)");
                        BlockState blockState2 = blockState;
                        BlockPos blockPos2 = blockPos;
                        RaycastContext raycastContext2 = raycastContext;
                        BlockHitResult blockHitResult = PlaceManager.INSTANCE.raycastAgainst(raycastContext2, blockPos2, blockState2);
                        Globals.getInteractionManager().interactBlock(Globals.getPlayer(), Hand.MAIN_HAND, blockHitResult);
                        Globals.getPlayer().swingHand(Hand.MAIN_HAND);
                        return;
                    }
                    if (n2 == n) break;
                    ++n2;
                }
            }
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
                        TraceThrough.b = ZkmKeys.a(-5306137456776108652L, 4681884645453785999L, MethodHandles.lookup().lookupClass()).a(175136623383614L);
                        var20 = TraceThrough.b ^ 26320959174378L;
                        TraceThrough.e = new HashMap<K, V>(13);
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
                        var15_5 = "W\u0003\u00b8\u00b9[\u001b\u0011z\u0095Z\u00b2\t\tF[|ENo\u00cc\u00d9\u0090<\u00c2\u001d\t[\u00cc\u0014\u00f3[\u00c1\u00f7k,\u00f3\\t\u00d5,\u0018G\u0013Hd\u00e4\u009e\u00f2\u0091\u0018\u00a5\u00b8\u0095\u00bb\u00bc\u00ac\u008f\u00c6\u00c3T\u00b7|\u00b0v\u00ab \u001f\u0091`\u0082\u0081\u0084\u0090\u00b9\u00d7z\u0092\u00ee\u00c3C\u00ad\u00ee8\u00b1\u008b-\u0000:C\u0091\u008b\u0000T\u009al\u001c\u00bd\u00c4 xR\u00a6Q\u00f7[u\u00a0\u0006\u00de\u0089\u00c4[\u000f~v\u00a7\u001f5O\u00f1,\u0091\u00d9W\u0081\fy\u00cc\u00f4\u000b\u00c7 N\u00a3*NgEM#.\u00a7\u00d2\u00d1\u00ee4\u00b2\u00e8\u0007F\u00c6k\u0080\u00e2#\u00fce\u00d7a\u0082\u00e7\u00aa\u00c6\u00e8(\u00c9\u00a9\u00d95t\u00ac.)\u00b3\u00ef\u00ef\u009cd\u00e4;W\u00b4\u0091\u00a2\u0090>\u001d/`\u0099p\u00e0\u0082O\u00da\u00fd\u0090\u0093\u001e*\u00a2\u00feH\u0012E8\u0086\u00e3\u00ee\u0095\u00e3f\u001fH\u0018\u00b5e\u000b\u00e88J\u00cf\u000f?2$\u00c8\"\u00deE\u009fH\u00e6.lm\u0016D\u001a\u0087\u00a0i\u0096\u0080)\u0092lt\u008e\u00fe\u001c-\u00fe\u00ce\u0082k\u0019a\u008b >a(3&\u0016\u00f5\u0094\u00d2\u001b:\u00e8\u000475N#\u0094\u00b9&y\u0002\u00b6\u0000\u00a2;7\u0002\u0085\u00fb\u0086\u009d0 v\u00afd\u00cf\u0004\u00fc\u0003\u0087\u001e\u0018\u0003\u00afe/\\\u00d2A\u00b8\u00f6\u00cb(\u00a6\u00a1\u00ba3\u0016\u008c\u00e0\u00eco\\\u0004\u00a9\u00d3  \u00f1\u0088\u00df\u0011\u00cd\u00b5\u00b2_6\u00fbY\u00e6\"D\u0001\u008a\u0090w\u0017y\u00fc\u00b8\u0081\u0081\u00ab\u001c\u00df\u00c7\u00ae\u00fc\u00c7\u0018\u00f6\u00bb\u0010u\u00f6\u00ae\u00af<H\u00f2\u00e5\u00d3\u00e6\u00c5\u0099\u00b4\u0087\u00862x\u007f\u00e9\u00ca\u00ac\u0018\u00e8L f\u00b11\u00c5\u00a4\u00f7\u00d7n\u0001\u00d5\u00dbS>\u00f3<8\u009f\u00af\u00b0\u00b3\u0087\u0018\u0098\u0086`\u00a9^\u00acd\u008a\u00d5\u001f\u001dB.\u00db\u007f\u0080\u0092\u00f2\u00ee\u00859\u00e0\u00f7\u00f6(\u00fbQ2\u008c\u008b\u00bc\u00dd\u00ce\u0019U\u00ed\u00b1\u00c7HG\u008e\u00dc\u00e5V3%\u00e8\u00d8\u00e5)\u00b3\u00a9\u00d5U\u00d5*\u00a0\u0007gX7\u00a1\u00ea;\u00b0(k\u00c3\u00cc:\u0019=\u00a1\u009a\u0095\u00e1\u00a9\u00f9\u00a6+\u0098\u00bb\u00da\u0018\u00b1\u00f8\u00ac\u00c5\u00ca&\u00ed\u008a\u00f2L\u0088\u001c\u00c9\u00e5e^O9k\u000e\u00d1\b(\u0099\u00fe\u00b3\u0097;M?{\u0007C\u00d4\u0097;\u00f4\u009d\u00a3r\u0006\u00f7v>\u00d0\u009c\u00db\u0083\u00d9\u0014\u00a6\u0085\u0004q\u001c\u00bf\u00a2\n\u0091\u0082\u009f\u00ef< \u00e1t\u00bci\u0018\u00e5\u009a\u00db\"\u00fb\u00b4\u00cd\u00e6\u00e6\u008d\u00ee\u00b2\u00e4\u0014\u00a6\u0016\u0082\u00ea2p\u00c8\u00bf;\u00e9p\u00950x3\u00ce1RSk\u00fec\u00d3n\u00bcA\u0001t*\u001a\u00e1\u0097\u0019$\u00b1sg\u0088\u00af\u00b4\u001d\u00be\u00a2\u00cb\u001a\b\u00a0\u0097K\u00a4\u00ba_\u008f\u00c5l\u0013d\u0000\u0097\u008c\u00a2\u0007S,'\u0093(\u000e\"\u00d5z\u00ac\u00e3\u00eb\u00b2o\\\u00da\u009e`\u00b0\u0091\u00e4\u0093\u00b8j\u00cc(\u0012p\u00d1\u00c5\u00d9\u00e2\u009ai\u00fcL+\u0010'\u008f\u0096\u00fc\u009ab\u00c1\u00cea\u00eao\u00ab\u00f5`H\u00cd\u00d9\u00c3FT\u00a7\u00d3\u00fa\u00a4W'}\u0004\u0084:VP\u00f6\u00c2";
                        var17_6 = 713;
                        var14_7 = 40;
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
                            var18_3[var16_4++] = TraceThrough.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ad\u00c2\u00b2t\u00db(lc\u00c5=\b\u00d2e\u001dN>7m\u0012X\u0001\u0095mP\u00cd\u0010z\u0091~\u00cd7\u00f5\u0083\u0097M#\\\u00b2\u00b9d\u0080\u00a6D\u0000,\u0096/'\u00f0\u00e0r\u0001\u00b9\u0091F]\u00cdo5\u00d7\u00deqm{.wf\u0003T\u00c9\u00ce\u00bf=P\rB\f\u00b2<3l\u00b6\u00f0b\u00a7\u00bd\u0096\u00ef_\u0089\u008dW\u00ba\u009f\u0081\u00e6\u0087}\u00de\u0010\u0082\u00e0\u00a5&H\u00f1.\u00d5*_Qk\u00a9\u00af\u009e&\u00d2\u001d\u00d1\\o\u00ddB\u0004d\u00e8T\u00a3\u001d\u00fa'\u00b2\u00de\u007f\u00f2\u0084G\u001c]!\u0096$\u000b\u00c1-\u0007\u00adK\u00a4\u001d\u00f6OLpt\u00bf\u0089\u00ea\f\u00d9GL\u00de\u00ee\u00c1\u0083w\u0081D";
                            var17_6 = 169;
                            var14_7 = 40;
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
                            var18_3[var16_4++] = TraceThrough.zkmDecodeUtf8(var19_9).intern();
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
                TraceThrough.c = var18_3;
                TraceThrough.d = new String[20];
                var1_10 = Cipher.getInstance("DES/CBC/NoPadding");
                v7 = SecretKeyFactory.getInstance("DES");
                v8 = new byte[8];
                v9 = v8;
                v8[0] = (byte)(var20 >>> 56);
                for (var2_11 = 1; var2_11 < 8; ++var2_11) {
                    v9 = v9;
                    v9[var2_11] = (byte)(var20 << var2_11 * 8 >>> 56);
                }
                var1_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                var0_12 = new long[2];
                var4_13 = 0;
                var5_14 = "\u00b1\"\u00bdi\u00ef\u0014\u00b7\u00b6\u008b\u00ad\u00be\u00a5\u0005\u00b0\u0099;";
                var6_15 = 16;
                var3_16 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl72:
                // 1 sources

                while (true) {
                    var0_12[v10] = ((long)var10_19[0] & 255L) << 56 | ((long)var10_19[1] & 255L) << 48 | ((long)var10_19[2] & 255L) << 40 | ((long)var10_19[3] & 255L) << 32 | ((long)var10_19[4] & 255L) << 24 | ((long)var10_19[5] & 255L) << 16 | ((long)var10_19[6] & 255L) << 8 | (long)var10_19[7] & 255L;
                    if (var3_16 < var6_15) ** continue;
                    break block16;
                    break;
                }
            }
            var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
            v10 = var4_13++;
            var8_18 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
            ** while (true)
        }
        var27_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(TraceThrough.class, "attackEntities", "getAttackEntities()Z", 0)), Reflection.property1(new PropertyReference1Impl(TraceThrough.class, "ignoreEntities", "getIgnoreEntities()Z", 0)), Reflection.property1(new PropertyReference1Impl(TraceThrough.class, "openContainers", "getOpenContainers()Z", 0))};
        TraceThrough.delegatedProperties = var27_20;
        TraceThrough.INSTANCE = new TraceThrough();
        TraceThrough.attackEntities$delegate = SettingFactory.booleanSetting$default((SettingContainer)TraceThrough.INSTANCE, "attack-entities", false, null, null, (int)var0_12[1], null);
        TraceThrough.ignoreEntities$delegate = SettingFactory.booleanSetting$default((SettingContainer)TraceThrough.INSTANCE, "ignore-entities", true, null, null, (int)var0_12[0], null);
        TraceThrough.openContainers$delegate = SettingFactory.booleanSetting$default((SettingContainer)TraceThrough.INSTANCE, "open-containers", false, null, null, (int)var0_12[0], null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0xFEC;
        if (d[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])e.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    e.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/eu", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = c[n2].getBytes("ISO-8859-1");
            TraceThrough.d[n2] = TraceThrough.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = TraceThrough.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/eu" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(TraceThrough.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

