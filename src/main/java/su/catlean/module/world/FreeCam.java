/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.module.world;

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
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.gui.screen.ReconfiguringScreen;
import net.minecraft.client.gui.screen.world.LevelLoadingScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profilers;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.events.client.InputEvent;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.api.event.events.player.FreecamStateEvent;
import su.catlean.api.event.events.player.KeyboardInputEvent;
import su.catlean.api.event.events.player.MoveEvent;
import su.catlean.api.event.events.player.PlayerUpdateEvent;
import su.catlean.api.event.events.player.UpdateCrosshairTarget;
import su.catlean.api.event.events.render.SetupTerrainEvent;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.KeyMappingAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.render.RenderState;
import su.catlean.render.RenderUtil;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.util.Globals;
import su.catlean.util.MovementUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationManager;
import su.catlean.zkm.ZkmKeys;

public final class FreeCam
extends Module {
    @NotNull
    public static final FreeCam INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final FloatSetting speed$delegate;
    @NotNull
    private static final FloatSetting hspeed$delegate;
    @NotNull
    private static final BooleanSetting freeze$delegate;
    private static float savedYaw;
    private static float savedPitch;
    private static float prevSavedYaw;
    private static float prevSavedPitch;
    @NotNull
    private static Vec3d cameraPos;
    @NotNull
    private static Vec3d prevCameraPos;
    private static float forwardInput;
    private static float strafeInput;
    private static float verticalSpeed;
    @NotNull
    private static Vec3d L;
    @NotNull
    private static Rotation savedRotation;
    private static final long b;
    private static final String[] d;
    private static final String[] f;
    private static final Map g;
    private static final long[] j;
    private static final Integer[] k;
    private static final Map l;

    private FreeCam() {
        super("free-cam", Categories.getWorld(), null, null);
    }

    private final float getSpeed() {
        return ((Number)speed$delegate.getValue(this, delegatedProperties[0])).floatValue();
    }

    private final float getHspeed() {
        return ((Number)hspeed$delegate.getValue(this, delegatedProperties[1])).floatValue();
    }

    private final boolean getFreeze() {
        return (Boolean)freeze$delegate.getValue(this, delegatedProperties[2]);
    }

    @Override
    public void onEnable(long l) {
        if (Globals.getMc().world == null || Globals.getMc().player == null) {
            return;
        }
        Globals.getMc().chunkCullingEnabled = false;
        savedPitch = Globals.getPlayer().getPitch();
        savedYaw = Globals.getPlayer().getYaw();
        prevSavedPitch = savedPitch;
        prevSavedYaw = savedYaw;
        cameraPos = new Vec3d(Globals.getPlayer().getX(), Globals.getPlayer().getY() + (double)Globals.getPlayer().getEyeHeight(Globals.getPlayer().getPose()), Globals.getPlayer().getZ());
        prevCameraPos = new Vec3d(Globals.getPlayer().getX(), Globals.getPlayer().getY(), Globals.getPlayer().getZ());
        Vec3d vec3d = Globals.getPlayer().getVelocity();
        Intrinsics.checkNotNullExpressionValue(vec3d, "getDeltaMovement(...)");
        L = vec3d;
        savedRotation = new Rotation(Globals.getPlayer().getYaw(), Globals.getPlayer().getPitch(), false, null, 12, null);
    }

    @Override
    public void onDisable(long l) {
        if (!this.isInGame()) {
            return;
        }
        Globals.getMc().chunkCullingEnabled = true;
        if (this.getFreeze()) {
            Globals.getPlayer().setVelocity(L);
            Globals.getPlayer().rotate(savedRotation.getYaw(), false, savedRotation.getPitch(), false);
        }
    }

    @Flow(priority=10)
    private final void onPlayerUpdate(PlayerUpdateEvent playerUpdateEvent) {
        block3: {
            long l = b ^ 0x5956D0CA8B8EL;
            long l2 = l ^ 0xE31BE74AB97L;
            if (Globals.getMc().world == null || Globals.getMc().player == null) {
                this.disable(l2);
                return;
            }
            if (Globals.getMc().currentScreen instanceof LevelLoadingScreen || Globals.getMc().currentScreen instanceof ReconfiguringScreen) {
                this.disable(l2);
            }
            prevSavedYaw = savedYaw;
            prevSavedPitch = savedPitch;
            savedYaw = Globals.getPlayer().getYaw();
            savedPitch = Globals.getPlayer().getPitch();
            double[] dArray = MovementUtil.getMovement$default(MovementUtil.INSTANCE, this.getSpeed(), false, forwardInput, strafeInput, 0.0f, 16, null);
            prevCameraPos = cameraPos;
            Vec3d vec3d = cameraPos.add(dArray[0], verticalSpeed, dArray[1]);
            Intrinsics.checkNotNullExpressionValue(vec3d, "add(...)");
            cameraPos = vec3d;
            if (Globals.getMc().crosshairTarget == null) break block3;
            HitResult hitResult = Globals.getMc().crosshairTarget;
            Intrinsics.checkNotNull(hitResult);
            if (hitResult.getPos() != null && !MovementUtil.INSTANCE.isMoving()) {
                HitResult hitResult2 = Globals.getMc().crosshairTarget;
                Intrinsics.checkNotNull(hitResult2);
                Vec3d vec3d2 = hitResult2.getPos();
                Intrinsics.checkNotNullExpressionValue(vec3d2, "getLocation(...)");
                RotationManager.INSTANCE.rotate(Rotation.Companion.to(vec3d2), 0);
            }
        }
    }

    @Flow
    private final void onSetupTerrain(SetupTerrainEvent setupTerrainEvent) {
        long l = b ^ 0x7F919CCFC993L;
        long l2 = l ^ 0x28F6F271E98AL;
        if (Globals.getMc().world == null || Globals.getMc().player == null) {
            this.disable(l2);
            return;
        }
        setupTerrainEvent.cancel();
    }

    @Flow
    private final void onFreecamState(FreecamStateEvent freecamStateEvent) {
        long l = b ^ 0x71D4E9F863B5L;
        long l2 = l ^ 0x26B3874643ACL;
        if (Globals.getMc().world == null || Globals.getMc().player == null) {
            this.disable(l2);
            return;
        }
        freecamStateEvent.cancel();
        freecamStateEvent.setX(this.getInterpolatedX());
        freecamStateEvent.setY(this.getInterpolatedY());
        freecamStateEvent.setZ(this.getInterpolatedZ());
        freecamStateEvent.setYaw(this.getInterpolatedYaw());
        freecamStateEvent.setPitch(this.getInterpolatedPitch());
    }

    @Flow
    private final void onUpdateCrosshairTarget(UpdateCrosshairTarget updateCrosshairTarget) {
        long l = b ^ 0x512BBE7E8A80L;
        long l2 = l ^ 0x64CD0C0AA99L;
        if (Globals.getMc().world == null || Globals.getMc().player == null) {
            this.disable(l2);
            return;
        }
        Profilers.get().pop();
        updateCrosshairTarget.cancel();
        Globals.getMc().crosshairTarget = MovementUtil.INSTANCE.raycastFrom(this.getInterpolatedYaw(), this.getInterpolatedPitch(), this.getInterpolatedX(), this.getInterpolatedY(), this.getInterpolatedZ());
    }

    @Flow
    private final void onInput(InputEvent inputEvent) {
        block16: {
            boolean bl;
            long l = b ^ 0x338111EF0730L;
            long l2 = l ^ 0x64E67F512729L;
            if (Globals.getMc().world == null || Globals.getMc().player == null) {
                this.disable(l2);
                return;
            }
            if (inputEvent.getKey() == -1) {
                return;
            }
            KeyBinding keyBinding = Globals.getMc().options.forwardKey;
            Intrinsics.checkNotNull(keyBinding, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
            boolean bl2 = ((KeyMappingAccessor)((Object)keyBinding)).getKey().getCode() == inputEvent.getKey();
            KeyBinding keyBinding2 = Globals.getMc().options.backKey;
            Intrinsics.checkNotNull(keyBinding2, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
            boolean bl3 = ((KeyMappingAccessor)((Object)keyBinding2)).getKey().getCode() == inputEvent.getKey();
            KeyBinding keyBinding3 = Globals.getMc().options.leftKey;
            Intrinsics.checkNotNull(keyBinding3, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
            boolean bl4 = ((KeyMappingAccessor)((Object)keyBinding3)).getKey().getCode() == inputEvent.getKey();
            KeyBinding keyBinding4 = Globals.getMc().options.rightKey;
            Intrinsics.checkNotNull(keyBinding4, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
            boolean bl5 = ((KeyMappingAccessor)((Object)keyBinding4)).getKey().getCode() == inputEvent.getKey();
            KeyBinding keyBinding5 = Globals.getMc().options.jumpKey;
            Intrinsics.checkNotNull(keyBinding5, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
            boolean bl6 = ((KeyMappingAccessor)((Object)keyBinding5)).getKey().getCode() == inputEvent.getKey();
            KeyBinding keyBinding6 = Globals.getMc().options.sneakKey;
            Intrinsics.checkNotNull(keyBinding6, "null cannot be cast to non-null type su.catlean.mixins.accessors.KeyMappingAccessor");
            boolean bl7 = bl = ((KeyMappingAccessor)((Object)keyBinding6)).getKey().getCode() == inputEvent.getKey();
            if (inputEvent.getAction() == InputEvent.Action.Press || inputEvent.getAction() == InputEvent.Action.Hold) {
                if (bl2) {
                    forwardInput = 1.0f;
                }
                if (bl3) {
                    forwardInput = -1.0f;
                }
                if (bl4) {
                    strafeInput = 1.0f;
                }
                if (bl5) {
                    strafeInput = -1.0f;
                }
                if (bl6) {
                    verticalSpeed = this.getHspeed();
                }
                if (bl) {
                    verticalSpeed = -this.getHspeed();
                }
            }
            if (inputEvent.getAction() == InputEvent.Action.Release) {
                if (bl2) {
                    forwardInput = 0.0f;
                }
                if (bl3) {
                    forwardInput = 0.0f;
                }
                if (bl4) {
                    strafeInput = 0.0f;
                }
                if (bl5) {
                    strafeInput = 0.0f;
                }
                if (bl6) {
                    verticalSpeed = 0.0f;
                }
                if (bl) {
                    verticalSpeed = 0.0f;
                }
            }
            MovementUtil.INSTANCE.releaseMovementKeys();
            if (!bl2 && !bl3 && !bl4 && !bl5 && !bl6 && !bl) break block16;
            inputEvent.cancel();
        }
    }

    @Flow
    public final void onKeyboardInput(@NotNull KeyboardInputEvent keyboardInputEvent) {
        Intrinsics.checkNotNullParameter(keyboardInputEvent, "e");
        keyboardInputEvent.cancel();
    }

    @Flow(priority=-10)
    private final void onMove(MoveEvent moveEvent) {
        block1: {
            long l = b ^ 0x6D539E3262D4L;
            long l2 = l ^ 0x3A34F08C42CDL;
            if (Globals.getMc().world == null || Globals.getMc().player == null) {
                this.disable(l2);
                return;
            }
            if (!this.getFreeze()) break block1;
            moveEvent.setX(0.0);
            moveEvent.setY(0.0);
            moveEvent.setZ(0.0);
            moveEvent.cancel();
        }
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        if (Globals.getMc().player != null && Globals.getMc().world != null && this.getFreeze() && (sendPacket.getPacket() instanceof PlayerMoveC2SPacket || sendPacket.getPacket() instanceof PlayerInputC2SPacket)) {
            sendPacket.cancel();
        }
    }

    private final float getInterpolatedYaw() {
        return RenderUtil.INSTANCE.lerp(prevSavedYaw, savedYaw, RenderState.INSTANCE.getTickDelta());
    }

    private final float getInterpolatedPitch() {
        return RenderUtil.INSTANCE.lerp(prevSavedPitch, savedPitch, RenderState.INSTANCE.getTickDelta());
    }

    private final double getInterpolatedX() {
        return RenderUtil.INSTANCE.lerp(FreeCam.prevCameraPos.x, FreeCam.cameraPos.x, (double)RenderState.INSTANCE.getTickDelta());
    }

    private final double getInterpolatedY() {
        return RenderUtil.INSTANCE.lerp(FreeCam.prevCameraPos.y, FreeCam.cameraPos.y, (double)RenderState.INSTANCE.getTickDelta());
    }

    private final double getInterpolatedZ() {
        return RenderUtil.INSTANCE.lerp(FreeCam.prevCameraPos.z, FreeCam.cameraPos.z, (double)RenderState.INSTANCE.getTickDelta());
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        FreeCam.b = ZkmKeys.a(4967011047434778548L, -7256421303548544253L, MethodHandles.lookup().lookupClass()).a(179285947458093L);
                        var20 = FreeCam.b ^ 47627167865818L;
                        FreeCam.g = new HashMap<K, V>(13);
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
                        var18_3 = new String[17];
                        var16_4 = 0;
                        var15_5 = "\u009f\u0000\u009d\u00de\u009cU3{\r0\u00e2\u0014\u0003\u00ed\rH\u0010\u0019\u0085V)`\u00c9\u0082\u008b\u00a4W\u00beV\u0000_\u0092\u0085\u0010:r%\u00de\u009e#\u000f\u00fc%>\u0013\u00ee\u0002\u008eh\u00ef \u00d6\u0002m\u00d6\u008c\u00a2\u00ac\u000b\b\u00e4*\u00f1\u0096\u001cP\u00dfJ\u00c4\u0090\u00b7\u00db\u00e8Xt.\u00cb\u0011\u00da\u001f\u0086Tr\u0010\u00fb\\o\u00cd\u0017\u00d0`\u0090\u0007)\u00ff\u001b\u00b5\u00b3e\b l\u0094\u00d1j\u00f0>\u00c1\u008c\u00bf\u00c4\u00cf\u000fI\u001a\u0094\u00c5\u008b\u001ar\u00e1\u00e05\u008a\u00feY\u00cc\f8\u00b8\u00cf04 \u00bdk\u00a7\u00d4\u0010(X\u0091\u000b\u00e1s\u009cf=&\u001e\u00f8|#\u009f\u00c5]\u0003\u0083]\u00c1\u00dbO\u00c4I\u000f\u0087 S\u00d3\u008f\u00a4\u00bbX\u0014\u0093!\u00f8\u00f9\u00f9\u00f1\u00b4\u001a*\u00e6\u00e6Q\u00a4\u00b9\u0091\u00f4\u0082\u00c28\u00c6\u00c1\u0091\u00ac\u0088{ \u00ae\u0089\u0081\u00b0\u00f5\u008dl\u0083\u00f5\u00b0\u00f92\u0098\u0098V\u0096\u00ae|n\u0001\u00e9\u00cd\u00d4\u00a7\u0099(8/\u00a85\u007f\u00d0\u0010,/\u0087 \u00a6\u00fc\u00c4r\u009e\u00ed\u00a2L\u0014\u0098\u00ab] w\u00b2}N\u001cXq\u000fH\u00ef\u008e$\u00c6\u0006\u00a2\u00d2\u00e3\u00d7\u00877\u00b4{\u0015|\u00e5\u0098\u00a5\u000b\u009f\u00f9A:(\u009c\u001e\u00d50jP,\u00f72\u00c6\u00ba\u00d6\u008d\u008c\u00b6\u00b9b\u0001g\u00acU.\u00f2W<\u007f\u00b6#\u0089\u00fa\u0017\u00a4\u00d8\u00136o\u00cafg.\u0088\u00b0\u00ff\u00d1\u001f\u00a3L\u0096K\u00ba\u00fcX\u00af|ke\"#K\u0005%\u00fb\u00eb]\u00e5k,f\u000b\u00dc\u00e9\u00ca\u00d0\u00dc\u00e1t;\u009c\u00fc\u00a7\u0082\u00d6\u0012\u00d2\u0098a\u0090Y\u00d9\u00f0\u0082\u00b3\u000f!\u0084\u00b3\u00e0!\u00a8\u00d3W\u0018\u008a\u00d3r<\u00d45\u0005f\u00a5\u0015\u001cc\u00fab\u00d6\u00a0\u00ad\u00f3f\u00fd\u00eab\u001c?\u00ac\u0090\u00e0\u0099\u00c7q\u00e1E\u0085vdq\u00ff|`C\t\u00e8\u00a1_\u00eb\u00c5Xu\u00c9ox!\u00f3?\u00d2\u00a2\\}\u0017f:\u00929$8p\u00e3\u009ff\u001d\u00b4\u00a6s\u00e1\u00eb\u0088\u00a7\u00f1\u00d4A\u0084.L\u009b\u0080a\u0015\u0001\u00f3,F\u00e7!T\u00acm|\u00cc\u00bcn\u001f5\u001cTb\u00a3\u0083Ta\b\u00cf\u00beP\r%\u00d0(\u00fe\u00cb\u00e7,U\u00b4\n\u001d\u00e3ih\u0088\u00c0\u00cc,\u00d1\u00a1\u00e1\u008d\u00dfOK\u00ad\u00ec\u00b9\u00b9\u001f*)\u00e5Y-\u000e\u0086i\u00a6Ui/\u00b6G\u00a1\u0083\u009c\u00be\u00d3\u0081b\u0006\b#\u00ea\u0086\u0018\u00e2\u0007\u00a4\u009c\u0086!\u00f2_\u00df}(\u0087\u00e4{\u00be\f\u00b2\u0006\u0080\u00c5b3\u00ba\u001c?\u00b77&\u00a9)>\u00adR\u009c\u00d1\u00ce\t/\u00ab\u00a7\u00ae\u0010r\u008a+\u00c6\u0017;\u00f3\u0004K\u00bb$\u00baq\u00ab\u00ea\u00be";
                        var17_6 = 614;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = FreeCam.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00b9\u0018\u000bMy\u00a8\u0018^\u00bd\u00ca\u00bc(\u00c9\u00f1\u00d6\u001a\u0010`\u00e4\u000b\u009b\u00f5\u00ef\u00d3\u009e\u00b8!YI\u009b\u000f\u00da3";
                            var17_6 = 33;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = FreeCam.zkmDecodeUtf8(var19_9).intern();
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
                FreeCam.d = var18_3;
                FreeCam.f = new String[17];
                FreeCam.l = new HashMap<K, V>(13);
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
                var6_12 = new long[5];
                var3_13 = 0;
                var4_14 = "\u00dc[\u009d\u00ab\u0000\u00adj\u001e\u0002\u00ef\u0098\u00b1\u001a\u00aa\u00baa\u00ba= \u00de_\u00e0\u00d3\u00ca";
                var5_15 = 24;
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
                    var4_14 = "\u009e\u0012\u00c0bX\u00ac\u00a4\u0016F\u00f5\u00a7i \u00a0ja";
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
        FreeCam.j = var6_12;
        FreeCam.k = new Integer[5];
        var31_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(FreeCam.class, "speed", "getSpeed()F", 0)), Reflection.property1(new PropertyReference1Impl(FreeCam.class, "hspeed", "getHspeed()F", 0)), Reflection.property1(new PropertyReference1Impl(FreeCam.class, "freeze", "getFreeze()Z", 0))};
        FreeCam.delegatedProperties = var31_20;
        FreeCam.INSTANCE = new FreeCam();
        FreeCam.speed$delegate = SettingFactory.floatSetting$default(FreeCam.INSTANCE, "h-speed", 1.0f, RangesKt.rangeTo(0.1f, 3.0f), null, null, 24, null);
        FreeCam.hspeed$delegate = SettingFactory.floatSetting$default(FreeCam.INSTANCE, "v-speed", 0.42f, RangesKt.rangeTo(0.1f, 3.0f), null, null, 24, null);
        FreeCam.freeze$delegate = SettingFactory.booleanSetting$default((SettingContainer)FreeCam.INSTANCE, "freeze", true, null, null, 12, null);
        v15 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v15, "ZERO");
        FreeCam.cameraPos = v15;
        v16 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v16, "ZERO");
        FreeCam.prevCameraPos = v16;
        v17 = Vec3d.ZERO;
        Intrinsics.checkNotNullExpressionValue(v17, "ZERO");
        FreeCam.L = v17;
        FreeCam.savedRotation = new Rotation(0.0f, 0.0f, false, null, 12, null);
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x122D;
        if (f[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])g.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    g.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ea", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = d[n2].getBytes("ISO-8859-1");
            FreeCam.f[n2] = FreeCam.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return f[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = FreeCam.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/ea" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7EB9;
        if (k[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = j[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])FreeCam.l.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    FreeCam.l.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/ea", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            FreeCam.k[n2] = n3;
        }
        return k[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = FreeCam.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/ea" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(FreeCam.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(FreeCam.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

