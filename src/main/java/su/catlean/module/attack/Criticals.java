/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.Unpooled
 */
package su.catlean.module.attack;

import io.netty.buffer.Unpooled;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
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
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.api.event.events.network.SendPacket;
import su.catlean.gofra.Flow;
import su.catlean.mixins.accessors.LocalPlayerAccessor;
import su.catlean.module.Categories;
import su.catlean.module.Module;
import su.catlean.module.attack.Criticals$WhenMappings;
import su.catlean.module.attack.CriticalsMode;
import su.catlean.module.attack.CriticalsNcpMode;
import su.catlean.setting.BooleanSetting;
import su.catlean.setting.FloatSetting;
import su.catlean.setting.ModeSetting;
import su.catlean.setting.SettingContainer;
import su.catlean.setting.SettingFactory;
import su.catlean.setting.SettingGroup;
import su.catlean.setting.SettingParent;
import su.catlean.util.Globals;
import su.catlean.util.PacketUtil;
import su.catlean.zkm.ZkmKeys;

public final class Criticals
extends Module {
    @NotNull
    public static final Criticals INSTANCE;
    static final KProperty[] delegatedProperties;
    @NotNull
    private static final ModeSetting mode$delegate;
    @NotNull
    private static final ModeSetting ncpMode$delegate;
    @NotNull
    private static final SettingGroup packet1$delegate;
    @NotNull
    private static final BooleanSetting sendPacket1$delegate;
    @NotNull
    private static final BooleanSetting ground1$delegate;
    @NotNull
    private static final BooleanSetting full1$delegate;
    @NotNull
    private static final FloatSetting offset1$delegate;
    @NotNull
    private static final SettingGroup packet2$delegate;
    @NotNull
    private static final BooleanSetting sendPacket2$delegate;
    @NotNull
    private static final BooleanSetting ground2$delegate;
    @NotNull
    private static final BooleanSetting full2$delegate;
    @NotNull
    private static final FloatSetting offset2$delegate;
    @NotNull
    private static final SettingGroup packet3$delegate;
    @NotNull
    private static final BooleanSetting sendPacket3$delegate;
    @NotNull
    private static final BooleanSetting ground3$delegate;
    @NotNull
    private static final BooleanSetting full3$delegate;
    @NotNull
    private static final FloatSetting offset3$delegate;
    @NotNull
    private static final SettingGroup packet4$delegate;
    @NotNull
    private static final BooleanSetting sendPacket4$delegate;
    @NotNull
    private static final BooleanSetting ground4$delegate;
    @NotNull
    private static final BooleanSetting full4$delegate;
    @NotNull
    private static final FloatSetting offset4$delegate;
    @NotNull
    private static final SettingGroup packet5$delegate;
    @NotNull
    private static final BooleanSetting sendPacket5$delegate;
    @NotNull
    private static final BooleanSetting ground5$delegate;
    @NotNull
    private static final BooleanSetting full5$delegate;
    @NotNull
    private static final FloatSetting offset5$delegate;
    private static String[] t;
    private static final long b;
    private static final String[] h;
    private static final String[] i;
    private static final Map k;
    private static final long[] z;
    private static final Integer[] A;
    private static final Map B;

    private Criticals() {
        super("criticals", Categories.getAttack(), null, null);
    }

    @NotNull
    public final CriticalsMode getMode() {
        return (CriticalsMode)((Object)mode$delegate.getValue(this, delegatedProperties[0]));
    }

    private final CriticalsNcpMode getNcpMode() {
        return (CriticalsNcpMode)((Object)ncpMode$delegate.getValue(this, delegatedProperties[1]));
    }

    private final SettingParent getPacket1() {
        return (SettingParent)packet1$delegate.getValue(this, delegatedProperties[2]);
    }

    private final boolean getSendPacket1() {
        return (Boolean)sendPacket1$delegate.getValue(this, delegatedProperties[3]);
    }

    private final boolean getGround1() {
        return (Boolean)ground1$delegate.getValue(this, delegatedProperties[4]);
    }

    private final boolean getFull1() {
        return (Boolean)full1$delegate.getValue(this, delegatedProperties[5]);
    }

    private final float getOffset1() {
        return ((Number)offset1$delegate.getValue(this, delegatedProperties[6])).floatValue();
    }

    private final SettingParent getPacket2() {
        return (SettingParent)packet2$delegate.getValue(this, delegatedProperties[7]);
    }

    private final boolean getSendPacket2() {
        return (Boolean)sendPacket2$delegate.getValue(this, delegatedProperties[8]);
    }

    private final boolean getGround2() {
        return (Boolean)ground2$delegate.getValue(this, delegatedProperties[9]);
    }

    private final boolean getFull2() {
        return (Boolean)full2$delegate.getValue(this, delegatedProperties[10]);
    }

    private final float getOffset2() {
        return ((Number)offset2$delegate.getValue(this, delegatedProperties[11])).floatValue();
    }

    private final SettingParent getPacket3() {
        return (SettingParent)packet3$delegate.getValue(this, delegatedProperties[12]);
    }

    private final boolean getSendPacket3() {
        return (Boolean)sendPacket3$delegate.getValue(this, delegatedProperties[13]);
    }

    private final boolean getGround3() {
        return (Boolean)ground3$delegate.getValue(this, delegatedProperties[14]);
    }

    private final boolean getFull3() {
        return (Boolean)full3$delegate.getValue(this, delegatedProperties[15]);
    }

    private final float getOffset3() {
        return ((Number)offset3$delegate.getValue(this, delegatedProperties[16])).floatValue();
    }

    private final SettingParent getPacket4() {
        return (SettingParent)packet4$delegate.getValue(this, delegatedProperties[17]);
    }

    private final boolean getSendPacket4() {
        return (Boolean)sendPacket4$delegate.getValue(this, delegatedProperties[18]);
    }

    private final boolean getGround4() {
        return (Boolean)ground4$delegate.getValue(this, delegatedProperties[19]);
    }

    private final boolean getFull4() {
        return (Boolean)full4$delegate.getValue(this, delegatedProperties[20]);
    }

    private final float getOffset4() {
        return ((Number)offset4$delegate.getValue(this, delegatedProperties[21])).floatValue();
    }

    private final SettingParent getPacket5() {
        return (SettingParent)packet5$delegate.getValue(this, delegatedProperties[22]);
    }

    private final boolean getSendPacket5() {
        return (Boolean)sendPacket5$delegate.getValue(this, delegatedProperties[23]);
    }

    private final boolean getGround5() {
        return (Boolean)ground5$delegate.getValue(this, delegatedProperties[24]);
    }

    private final boolean getFull5() {
        return (Boolean)full5$delegate.getValue(this, delegatedProperties[25]);
    }

    private final float getOffset5() {
        return ((Number)offset5$delegate.getValue(this, delegatedProperties[26])).floatValue();
    }

    @Flow
    private final void onSendPacket(SendPacket sendPacket) {
        if (sendPacket.getPacket() instanceof PlayerInteractEntityC2SPacket && this.isAttackPacket((PlayerInteractEntityC2SPacket)sendPacket.getPacket())) {
            Entity entity = this.getTargetEntity((PlayerInteractEntityC2SPacket)sendPacket.getPacket());
            if (entity == null || entity instanceof EndCrystalEntity) {
                return;
            }
            this.doCritical();
        }
    }

    private final void doCritical() {
        if ((Globals.getPlayer().isOnGround() || Globals.getPlayer().getAbilities().flying) && !Globals.getPlayer().isInLava() && !Globals.getPlayer().isSubmergedInWater()) {
            switch (Criticals$WhenMappings.K[this.getMode().ordinal()]) {
                case 1: {
                    this.doNcpCritical();
                    break;
                }
                case 2: {
                    this.sendCustomPackets();
                    break;
                }
                case 3: {
                    this.doNcpCritical();
                    break;
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }
    }

    private final void sendCustomPackets() {
        if (this.getSendPacket1()) {
            this.sendOffsetPacket(this.getOffset1(), this.getFull1(), this.getGround1());
        }
        if (this.getSendPacket2()) {
            this.sendOffsetPacket(this.getOffset2(), this.getFull2(), this.getGround2());
        }
        if (this.getSendPacket3()) {
            this.sendOffsetPacket(this.getOffset3(), this.getFull3(), this.getGround3());
        }
        if (this.getSendPacket4()) {
            this.sendOffsetPacket(this.getOffset4(), this.getFull4(), this.getGround4());
        }
        if (this.getSendPacket5()) {
            this.sendOffsetPacket(this.getOffset5(), this.getFull5(), this.getGround5());
        }
    }

    private final void doNcpCritical() {
        switch (Criticals$WhenMappings.h[this.getNcpMode().ordinal()]) {
            case 1: {
                Criticals.sendOffsetPacket$default(this, 1.058293536E-5, false, false, 6, null);
                Criticals.sendOffsetPacket$default(this, 9.16580235E-6, false, false, 6, null);
                Object var5_1 = null;
                int n = 6;
                boolean bl = false;
                boolean bl2 = false;
                double d = 1.0371854E-7;
                Criticals criticals = this;
                Criticals.sendOffsetPacket$default(criticals, d, bl2, bl, n, var5_1);
                break;
            }
            case 2: {
                Criticals.sendOffsetPacket$default(this, 0.0625, false, false, 4, null);
                Criticals.sendOffsetPacket$default(this, 0.0, false, false, 4, null);
                break;
            }
            case 3: {
                Criticals.sendOffsetPacket$default(this, 2.71875E-7, false, false, 6, null);
                Criticals.sendOffsetPacket$default(this, 0.0, false, false, 6, null);
                break;
            }
            case 4: {
                Criticals.sendOffsetPacket$default(this, 0.062600301692775, false, false, 6, null);
                Criticals.sendOffsetPacket$default(this, 0.07260029960661, false, false, 6, null);
                Criticals.sendOffsetPacket$default(this, 0.0, false, false, 6, null);
                Criticals.sendOffsetPacket$default(this, 0.0, false, false, 6, null);
                break;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final void sendOffsetPacket(double d, boolean bl, boolean bl2) {
        ClientPlayerEntity clientPlayerEntity = Globals.getPlayer();
        Object var11_5 = null;
        if (!bl) {
            PacketUtil.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(clientPlayerEntity.getX(), clientPlayerEntity.getY() + d, clientPlayerEntity.getZ(), bl2, clientPlayerEntity.horizontalCollision));
        } else {
            double d2 = clientPlayerEntity.getX();
            double d3 = clientPlayerEntity.getY() + d;
            double d4 = clientPlayerEntity.getZ();
            ClientPlayerEntity clientPlayerEntity2 = Globals.getMc().player;
            Intrinsics.checkNotNull(clientPlayerEntity2, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
            float f = ((LocalPlayerAccessor)((Object)clientPlayerEntity2)).getLastYaw();
            ClientPlayerEntity clientPlayerEntity3 = Globals.getMc().player;
            Intrinsics.checkNotNull(clientPlayerEntity3, "null cannot be cast to non-null type su.catlean.mixins.accessors.LocalPlayerAccessor");
            PacketUtil.sendPacket(new PlayerMoveC2SPacket.Full(d2, d3, d4, f, ((LocalPlayerAccessor)((Object)clientPlayerEntity3)).getLastPitch(), bl2, clientPlayerEntity.horizontalCollision));
        }
    }

    static void sendOffsetPacket$default(Criticals criticals, double d, boolean bl, boolean bl2, int n, Object object) {
        if ((n & 2) != 0) {
            bl = false;
        }
        if ((n & 4) != 0) {
            bl2 = false;
        }
        criticals.sendOffsetPacket(d, bl, bl2);
    }

    @Nullable
    public final Entity getTargetEntity(@NotNull PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket) {
        Intrinsics.checkNotNullParameter(playerInteractEntityC2SPacket, "packet");
        Object var4_2 = null;
        PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
        playerInteractEntityC2SPacket.write(packetByteBuf);
        return Globals.getWorld().getEntityById(packetByteBuf.readVarInt());
    }

    @NotNull
    public final PlayerInteractEntityC2SPacket.InteractType getInteractType(@NotNull PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket) {
        Intrinsics.checkNotNullParameter(playerInteractEntityC2SPacket, "packet");
        PacketByteBuf packetByteBuf = new PacketByteBuf(Unpooled.buffer());
        playerInteractEntityC2SPacket.write(packetByteBuf);
        packetByteBuf.readVarInt();
        Object var4_3 = null;
        PlayerInteractEntityC2SPacket.InteractType interactType = packetByteBuf.readEnumConstant(PlayerInteractEntityC2SPacket.InteractType.class);
        Intrinsics.checkNotNullExpressionValue((Object)interactType, "readEnum(...)");
        return interactType;
    }

    public final boolean isAttackPacket(@NotNull PlayerInteractEntityC2SPacket playerInteractEntityC2SPacket) {
        Intrinsics.checkNotNullParameter(playerInteractEntityC2SPacket, "<this>");
        return this.getInteractType(playerInteractEntityC2SPacket) == PlayerInteractEntityC2SPacket.InteractType.ATTACK;
    }

    private static final boolean settingVisible() {
        return INSTANCE.getMode() == CriticalsMode.NCP;
    }

    private static final boolean settingVisible2() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible3() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible4() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible5() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible6() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible7() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible8() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible9() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible10() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible11() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible12() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible13() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible14() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible15() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible16() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible17() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible18() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible19() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible20() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible21() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible22() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible23() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible24() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible25() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    private static final boolean settingVisible26() {
        return INSTANCE.getMode() == CriticalsMode.CUSTOM;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        Criticals.b = ZkmKeys.a(-6652710521872399585L, 1385954775312616339L, MethodHandles.lookup().lookupClass()).a(73846163564032L);
                        var20 = Criticals.b ^ 52150109950988L;
                        Criticals.k = new HashMap<K, V>(13);
                        Criticals.zkmSetFlag(new String[5]);
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
                        var18_3 = new String[88];
                        var16_4 = 0;
                        var15_5 = "\u00b0\u00a5)\u00f8Z\u00c0F\u00ec \u00c7\u0011\r\u0011\u00dc\u001d\u00d9\u009f)-*\u00cev\u0092D\u0010\u00f0\u00d4T\u009a/\bg\u00c0\u0091.|\u00f6\u00a2Z\u001aD\u0018jS\u00d3\u0083\b\u00b4/\u00c0\u0080c\u00eb\u00e7fN\u001f\u00ec\u009d\u0011\u0085\u00a3\u007f\u000b\u00cc\u0090  q\u00ba\u00bb\u00a6=Hn\u00b5e\u001d\u0092\u009fI\u00cdgp-\u008d3}\u008e\u0099\u009c\"\u00abL&\u00fcA,\u00ae \u0015\u0005\u00f0\u00c2\u00fe\u000b]#\u008a\u00faH\u00dd_~L\u0012\u00c8\u0005o\u00eb\u00ceK\u00d2\u0007 \u00caN\u009c\u00a4\u0000\u00fc\u0083 \u00b0y\u00c3\u00ac\u00be\u00f5B\u00e6\u00ce\f\u00d9\u0014\u00f6\u00e87\u00a7`\u00f7\u0085\u009eJ\u00beK\u0085\u00bd\u00d1Y\u00b0\u0080\u00cb\u00a7\u001f \u0002\u00d9L\u001b\u0000\u0018\u0007f\u00ad\ns\u00fb5`\u009c\u00e8d\b\u00ca\u00fb\u0093$\u00de\u00f9\u008c\u00ddf\u0083#W6j(\u00ec\u00fa\u00f9d\u00db\u00d2\u00c9\u00f9\u00a4\u0004\u0092!m\u008b\u00a4\u0094j6\u00ac\u00a8\u00b3\u00d4Yz\u00ec\u00b3\u009d\u00a2\u0002`\u00ff\u00bexFO\u009d\u0080\u00a1\u00ed\u00b2\u0010F\u00c6&\u00b8\u00d0gv^a\u0016\u0088x<\u007f\u00ed\u0000 +O?\u0018\u00e5#\u0018\u008e\u00ef}\u0001\u0010\u00ad\u0019\u009b\u001a]\u00e2\u009bN\u0004N\u00bc\u00af\u00c0s\u00ee\u0098|\u00f1r#\u0010\u00c2p\u00ab\u00fa\u00f2%m\u0080\u001c\u0019+9jn\u0011\u00fd\u0010\u0017\u00bb\u00807E\u001da\u00d5d(\u00e8\u00aa\u0005!\u00df\u009c\u0010\u0007\u00d9\u0088\u00f1\u00f2)\u008a\u0015\u00c0\u000eFP.\u00f5\u00f2\u009b \u00d7\u00cd\u00f5\u00c3\u00ad_\u00ee#Vc5)\u00e53\u00c8\u00fb{02!e\u00ed.\u00d3\u00b46X\u008d0\u00dd\u00bc\u0015\u0010O\u00a5G\u0003\u0091\u00e8\u00bf\u009a!1Yj\u008d\u00cd\u0098# \u0099\t\u0081I\u00c8\u00eb'w\u008bD\u00b2\u009da\u00f2\u00b7\u00ef\u0019Z\u00afJ\u00cb\u00eaw\u001e\u00a9\u00dbz\u00c9\u00c9\u00dd\u001d\u001d \u00ba\u00cd\u00bc\u0085\u009a\u00c3r\u0010T0\u00b4\u00f5GY\u0097j\u0000\u008a\u00c1&\u00b1\u0099\u0094q\u00eb<\u00a1\u00ca\u001f\u0018\u0083\u007f07\u008a\u001d\u0003\u00ba^\u00ce\u0006\u00ed~=\t[!\u000e\u00d4\u00bf\u00b5\u00c0\u00e0l\u00bac\u00cd\u00a6\u00b7Xr\u0089\u0003iKS\u00bb\u00b1\u00b5\u00d1\u000f\u00f5\u00f4\u0099_1\u00d0\u009c\u009d1U\u0018\u008f8\u00d6\nI\u009a\u00d9-\u0018\n\f\u00c8\u00e5\u001f+q\u00e8\"c*\u00a18\u0001\u00a6 \u00f0BL\u00d1\u00b2\u0016%\u00eb\u0017r\u0099O\u00cc\u00b3\fK\u00cd\u00ef\u00e6\u008a&T\u0019\u0013\u00f9\"*\u00fb\u0017\u00b3\u0098\u008f\u0010\u00ec\u00b5z\u00f9\u00a4\u00a3\u00fbM\u00ecg\u00d1%^p\u00fa\u00da\u0010g\u0080\u00d1\u00f1s0|\u00ab\u00b1\u00eeSoH\u00e2\u00d1 \u0010\u00c9{\u0087\u00db\u008f_W\u00ca3\u00d8\u00b1\u00b2\u009e\u00cd\u0096\u008e \u00ceQ=\u0082h\u001f\u00abw\b\u0091\u001b\u00e8)\u00dceSKV_\u0016\u00e8\u0098\u00e0\u00fe?a\u00f2W9\b\u00a1\u00ba \u00f2\u00aa\u00f7\u00f4\u00f3\u00c3\u00d2`\u0098-\u00b76\u008a\\\u00f2\u00fd$\u009f#5\u00a4\u0016L\u0018'\u00e1w\u00a9J\u00bf\u00a1\u0017\u0010\u00f5\u00a2\u00b7\u001e\u00c3\u00e8\u008a\u0086\u0004\u00bdKe\u00f7\u00e8\u0082\u00a0\u0018,-\u00ad\u00db\u0018\u0094\u0082X\u00c2\u00eb\u00ac\u0014\u0013\u00fbp\u0005\u001c\u0010\u0014\u00cb8S\u00de6 \u00e3\u00db\u00d7\u00063\u00db\u009e?r\b\u00fe\u00d7q\u0092f\u0097\u0019h\u001f\u000bBG\u00a7.\u00c9\u0017{\u00e8\u00bd?\u009a\u00d5 \u0096j\u00a7G\u00f8V\u00f01s\u00e5\u00ea\bB\u009d\u00f9\u00e3\u001f\u00efUU,\u00fey\u00e1\u00c8%\u0098j8\u008c\u009c=\u0010\u00f7\u00e2\u00b6\t\u008b\u0019p\u00a1\u00d8\u00b2\u00ff\u00c0*Q\f\u00e0\u0010\u00ef>lj\u0081(U\u00eb\u0099E\t%\u00ac#\u00d6\u00e78ev\u0083\u001f5!\u00c6\u00cf\u00c4\u00f1\u00a4\u00a9\u00b4z\u001e^n\u00f0\u0097\u00b2_g\u00b48x\u00a3)\u0095\u008e\u00d0\u00c64n\u00ae\u00ae^x\u00edXi v\u00c8\u00a7\f\u00d9M6\u0086kz:\u009aE\u00f4N8mu\u00ab\u00f5_\u00ef&2\u0088\u00dfn\u008d\u00a7\u00ce\u00d7\u00a0\u00b8T\u00d5\u0011\u00f9\u00e2d\u00fb\u0004n{6\u00a3w`S\u0090\u00a7\u00f5z\u009c\u008f\u0002z\u00d1\u00b7V\r\u00be\u0000\u0087\u00a5Q\u009e\u00c3\u00f6 <\u0012^\u0010\u00b3\u0083w\u0081\u00bc`\u008ao\u00aa\u00ea\u008a0\u00c7\u0013b-\u0010\u001a!57\u00c6\u00bd\u00bf\u00c9\u00c0\u0002\u00afK;\u0018\u0012\u0000\u0010\u0099\u00be\u00a6\u00d5\u00af3D\u0005\u0087\\\u008e\u00aeGg\t\u0090 u\u008f[-\u00ea\u00d5Os\u00b4\u00f6C\u0017\u00b0\u00cf\u00cbY\u00c6\u0015\u00f2\u009a\u0014q\u00e2\u001e2\u00ec\u00b6bM\u00ec.'\u0010\u00cc\u00e3'\u0090\u0016\u0019\u00ba\u00b78\u00b4\u00a9\u0084\u00b5N\u00bb\u0089\u0010\u00f6[\u00be{\u001c\u00da\u008e\u009c\u00a6S\u0014\u0014\u00e9\u00deI) YT\u00014t\u0010`e\u00ab\u00e3\u00e8\u00ac\u00fde\u00f5\u0097\u00cfE\u0011\f\u00b4\u0095\u00a8]:\u007fr\u0011t\u0081\u00c4\u00a6 \u0012\u00c5P\u00a8R\u00c2\u00f6V\u00eao\u00f9\u00e0N]E(+\u00e2g\u00c8\u00d0>\u0006\u00c8\u0081\u00dc3;\u00e2\"\u009c\u00de \u0092+\r\u0013\u00b8\u009a\u0095\u00ad\r\u00c815X?\u00f1)H\u00f8\u008c`\u00ce\u00da\u00fc;5\u00ca\u009c\u001d$\u00d2\u00e2=\u0010\u00db\u00e4S\f. \u00f2\u00e31\u00d8\u00eb4\u008e\u0083\u00cb\u0095 %-\u0007\u0001\u00e7q\u00d7X|VC\u009c\u00ee'\\\u00db\u0017\u00ac\u008dFA7\u00b6\u0018m\u000f\u001fI\u00d1\u00fc\u00db& ;^\u0006\u00167(\u009c\fVe9\u00e6<9\u00d4\u00bff\u0090\u00f7\u001c\u00c6\u0006M\u00a4\u0004\u009d\u0000\u00cc\u00bcl-O\u0018'\u0016\u00ff\u0093\u00f7\u0097I\u00b83\u00e4\u00b0\u00d9\u00f6\u0010\u00b2O[\u00a9\u00c4*}\u00bb\u0005_ \u001c\u00f3T\u00a5\u00b0\u00f5;\u009f\u0092Pr\u0016\u00b9 \u009f?\\\u0090\u00a6\u0010c+\u00fc\u00bc\u00d4\u009d'<\u00e9\u00fbN\u0082 e\u00b4\u0013\u009f\u0003\u0012\u00b7$\u001c\u007f\u001cCF\u00b8\u00a4i=\u0089\u0006z\u0084\u0016\u00ef\u00cb\u0000\u0013\u00c8\u00ee7\u00ccm\u00d2\u0018J\u0090\u00ea\u00b3\u00ee\u0096i\u00a0\u007f\u0087\u00e4\u00fad\u00b9\u00d0\u00e3J\u00ccn`\u00d7\u0098\u0084\u00dd\u0018\u0099D\u00f8\u0099\u00c0\u0089) e\u00c5\u00e3Y\u00a2,\b\t\u00df\u00b3\u0092\u0012\t<\u00f8\u0011 \u00f0\u0012\u000b\u00a76N\u00fa\u009d\u001eh/$\u00a6\u0098\u00e5\u00c7$\u00d8Od\u00b4\u00ecw\u00b84\u00e1A\u0099\u00b0\u0096\u0084\u00e5\u0010\u00b8\u0002J\u00c2\u00c5\u0088\n\u00de\u00c0\u00c6]SZ\u00d9p\u008a\u0088G\u00b4\u00e9\u001e\u00cb\u00ed\n>\u00ca\u00ff\u00cd\u00b0\u00cfJ\u00f1\u0002G\u0000\u00d6$\u00a3A\u00b8Eb\u009d\u00ac\u00f0\u00fb\u00c0\u00ed\u00a4JS\u0006\u00f0\u00d3\u009d\u00d7\u00e8\u0083\u00c6(\u00b8\u0090\u0080\u0007\u00df\u007f?\u000e\u00bc\u00c28hF\u0090\u00ea.\u0007l3\u0005X@\u00be\u0090\u00c0\u001c~\u00be\u00df\u009c\u0018#\u009a\u0005\u00c4\u00f3eP\n\"}\u00d4\r\u00ef\u0006\u0095\u00dd\u0094\u0083\u0089\u000b\u00db\u00bcM\u00c3\u008b\u00c2!~\u00a8\bAp-\u00ccze\u00199\u0019\u0003:\u00d8\fc\u00cc3#\u0012\\^\u00b9[b\u009f\u00ae7&uv\u009ak\u00f5\u0010\u00cd86_\u00bb\u00b9@{?`hv\u00e7\u0097f\u00fa8\u00d6\u0001\u00fe\u000f\u0002^q\u00e9\u0096{\u00c5NB\u00ac\u00ae\u00e5\u00fb\\VC\u0080\u00c6\u00d1/\u00d1`\u00ee$\u00a1\u00bb}\nd\u00da\u00d1\u008f\u0015O*\u0090\u001b\u0000\u009ej\u00a2\u00f5\u0007tfoZ\u00de\u0095Ih!(\u00ea`WHL{\\q(\u00b7n1\u00b4\u0092'\u001e\u00d1\u00bc\u008c\u00f9\u00c7xv\u00ac\u00ff\u000b\u001d\u007f\u00b5\u0007\u00a1\u00f4#\u000eO|\u0084`\u0007\u00e98{\u0092\u00fb\t9\u00b9g^=\u00b9l\u00d9\u00fdf\u0015\u0088\u009c\u0012\u00af\u00c2\u00833\\$\u0095\u00db\u0098\u00a4\u00cb\u00eey<:\u0097ov@.\\\u00ad\bB\u00ea\u00aa\u0004\b\u0004\u001f#\u00a9\u00f8\u0000GFw\u00d2\u0010o\u0095\f\u0089p\u00b2\u00dc\u0089E\u00bc\u00df\u0010\u00a2\u00fa\u001aI\u0088\u00d6\u0084oH\u007f\u00fd\u00a1\u00a6\\\u0016\u00f6\u0099\u00f9+\u00ac\u00b3\u00d7\u00a5)_\u00e5\u009c\u00e6\\70%\u00d7S\u001e\u0095\u008b*\u008e\u00c6\u00f3\u00c1\u0013<\u00d9\u00bcL\u00aba\u008e \u00e5\u00a0\u0099h\u008f\u00eb9\u00bfq\u00d3S\n\u0084\u0015\u00ebW\u0096\u00a2\u00ce\u0013\u0005$\u00a4F\u00d0=f\u008d#\u00c3\u00c8\u000b\u0086I\u00be\u000e\u00acOrO<\r\u00ba\u0015\u0091\u00b6\u00a1\u00a67\u00af?\u00ef\u00bfP\u00a3\u0082b\u00a6\u00cb\u00ed\t\u0093Y\u000e\u0097\u00a6\u009b\u0082J\u00d3\u00c1\u0081<Z\nWM&iZ\u00b1\u00bb\u0011pU\u00de\u00cb\u00ca\u00c9v8\u00ad\u008b\u00c2\u00cb\u00fb\u009c\u00ae\u00a9\u00d5\f\u00a1a\f4\u00c5\u00d8{\u00dd\u00ac\u0096\u00f5q\u00e3\u00fb*x#'\u00fdS$tJ\u00ba\u0082\u0096\u000b/H\u008b\u00a3\u0017\u0018\u001f\u00d0c|&\u00d2Po<\u00ce\u00ee\u00da\u00ac\u0018\u00e2gq\u0099\u00fc]\u00ea\u00ef;\u00fe\u008f3\u00c4\u001e_k\u00ae\u001e\u00a0\u0098\u001aI\u00ef\u00d8 \u0018QV\u009c\u00b4\u0094\u00c1\u0081\u008c\u00e9g\u00f96N\u008dH\u00f6OY\u00d4\u0017B\u0099\u00dd48\u00bb\fn\u0085\u00d2| t\u00e5\u00a5/\u00ce*\u00a8{\u00e7\u00bb\u00ab-\u00fb\u0005\u00a1\u00c0\u00d3j.\u00e2bY5\u00b0M\u00c7\u00aev8VC\u009b \u00cc\u008aY\u0016eN\u00e1\u00d4Rc6\u0094\u00ca\u00a5\u0088\u00de\u0014\u009d^1\u0094\u00a4\u00b4un\u00c2\u00d8*e\u008a\r\u00a7\u0010\u00c4\u00e8T\u00cc`\u00da\u00af\u00aa\u0084C%\u00fc\u00f2Bf\u00a0\u0018\u0010l\u00bfw\u00f1\u007fU[\u008d\u00b6}\u0098\u00e6\u00fcl1'\u00b1S:)\u00f0\u00cf\u009e\u0010\u00ea\u00cf\u00de|\u00c8\u00852\u0089f`\n*\u009b\u0082NI\u0010\u0095\u00b2Y\u008aY8\u0084R\u00be7B\u008c\u00bc\u00e1\u00d1\u008a \u00d3_\u00c7\u001a\u00d43\u00ab(WK\u00fa\u00b3l\u00fa\u00a9\u00c2\u0000\u0013Q\u00ab3U\u0014\u00fe\u00b67\u00c6\u0095\u00ff\u0002Z\u0004\u0010\u0083\u009f\u00a2\u001dz\u00ec\u00b8\u00cf\u00da\u00ac/=V\u0082Kl(\u000f\u00f7\u0097\u001d\u0083J\u00c5w\u00d5\u00dd\u00dfg>\u00ab\u0004y\u0083z\u00e4\u00ef\"=\u008c\u00bb\u00f2 \u008b}\u00a76\u00eb\u00c5\u00d2\u009d\u001eG\u00b40'\u00f7\u0010\u0003\u00e47\u00c2\u00ec\u00c9p\u00cc\u00af\u00da\u00bf\u0097\u0012m\u00a3\u0017\u0010\u009e\u00f5\u0000\u0091\u001d\u0007\u00c0\u00ce\u00be\u00a5Tg\u00ba\u001c$\u00f6\u0018\u00cb\u007f\u0095\u00a1\u0014\u00ee\u009a\u00b8\u00ec\u00b5\u00aefO\u0080\u00e1r\u00bb\u00a3Z[\u00d0\u00a1Ih\u0018\u00a4\u0006\t\r\u00e5Rz \u0090 U\u007f\"\u008cUm\u00f9*O\u00fb`17\u009b \u009c3\\DA\u0016\u0094\u00f6\u00e8\u008dt\u0019\u00c5\u00d5\u00f9\u0013yq-O:1\u00f6\u0005Z\u00cb\u00e2a\u000e)\u00b4\u000b\u0018\u008c\u00c2\u00d7\u00bf\u00b1{[\u00d7\u00fe##.\u00c5W\tn\u00ba\u00c4\u00d6pD\u0093,G\u0010r\u000ecp\u00f3lz\u0014*\u0082\u00a1i\u00e0\u00ef\u0015\u00c0\u0010\u0016\u00dd>\u00f1\u00fcYf\u00806+\u00dc\u007f\u00e3\u000f\u00af0 I\u00b6\u0082\u00b2KEq\u0016=\u001a5\u00d6\u0007\u00b2B\u00c3\u00a4\u00a2\u000f\u0018\u00ed\u0017\f\u00b8\u0011\u00d8zv\u00dd\u00d80k\u0010y\u00ae6\u00e6\u00bc \u00acT\u00fcC\u009b\u00ff\u00fd\u0091`h\u0010\f\u00bc\u00dc\u001c4\u0085h5z+\u00b8\u00e8D6\u00b1\u000b \u0082\u00c3\u00cb\u001a \\\n\u00867j\u0083{%?|\u0091\u008a|\u00b8\u00e4p\u008a\u00c1\u0005\u0091B\rv\u001a\u00fb\u009c\u00b1 8kx\u00db\u00f62\n\u00ad</\u0089\u00b1\n\u00a6\u00a0@\u00db\u0012\u0003\u0086\u00e4\"\u001ak\u00133v\u009dQ\u0090\u0092^ (4\u0016\u00fd'\u009a$`;\u00b7\u00ac\u0081\u00c79\u00c8\u00eb\u00bd\u00e3\u008dPa\u0014\u007f\u00b3z%\u0013\u00a2X\u00bf\u0086\u00d5 \u009bC\u00f5\t\u0084u\u00bd\u00a6\u00ae\u00f1\u008e\u0011\u00f5\u00b49\u0007>\u00df\u009d\u0087\u0097~!\"|\u009b\u00c0~\u00a6\u00c5U\u00c5";
                        var17_6 = 2613;
                        var14_7 = 24;
                        var13_8 = -1;
lbl21:
                        // 2 sources

                        while (true) {
                            v3 = ++var13_8;
                            v4 = var15_5.substring(v3, v3 + var14_7);
                            v5 = -1;
                            break block18;
                            break;
                        }
lbl26:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Criticals.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "*\u001a\u00d9}\u0006ZW\u00b3\u00d8\u00b7?\u0017zuAfy\u00f3LP\f\u00f1\u007f\b\u0017|\b\u0080\u0006\u001c\u00c1{\u0003;\u00b5\u00e2\u009e\u00f6\u0014U8T=\u00b9\u008f3\u00ee\u00fc\u00d5\u000e:\u00f6hffs^j\u009c\u0094OI\u00f2vd\u00ffn\u00bc>R\u00d0s\b\u00aaGN~\u00e3.\u00a9\u00fbg\u00f1J(x\u0099\u0087\u00a4U\u0088\u0082\u0011\u0093J\u00c6]";
                            var17_6 = 97;
                            var14_7 = 40;
                            var13_8 = -1;
lbl35:
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
lbl40:
                        // 1 sources

                        while (true) {
                            var18_3[var16_4++] = Criticals.zkmDecodeUtf8(var19_9).intern();
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
lbl52:
                        // 1 sources

                        ** continue;
                    }
                }
                Criticals.h = var18_3;
                Criticals.i = new String[88];
                Criticals.B = new HashMap<K, V>(13);
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
                var6_12 = new long[43];
                var3_13 = 0;
                var4_14 = "\u009c\u00bc\u0002\u00fc\u00fd\u00e2!\u00ae\u0011?\u00ca\u00ad\u00e0l7l\u00c5\t9\u00bc\u0091\u00bf\u009b\r\u00b4\u0006(\u0018\u00a7i\u008e\u00e8{\u00b9P\u00e8^\tH\u00e4\u00a5\u001f\u00f9\u009f\u0013\u0094\u00f1\u0006w\u008f\u00b0\u00ae\u00e3\u008e\u00aeWTn\u0012\f\u00b3\u00f9\u00f6\u00c8O\u00fe\u00b44\u0004\u0005\u0083c\u00a1(\u001a\u0093v\u00ac.\u0000\u00c7j,\u00a7\u009b\u00c7\u008bR3y\u00b1\u00dc\u001a\u00b8\u00d1\u000b\u00f19\u00a5:Z\u00e5ewRI\u00ce\u008b\u0018\u00f9\u00ee\u00a5\u00f6\u00bf\u00d2oV\u00f5\u0015l\u00c6t\u009d\u00ca\u00aavE\u00dc\u0099qv\\\u00e3\u00e4\u0015s\u00d5\fd\u0087b\u00cf\u00fa\u00ab~\u00ae\u00c5\u0016\u0096\u0016*\u00d3\u0002\u00aa\u00ddyv@#\u00c3\u00a1\u00cb\u00b6\n\u00bf\u0099\u00b5\u00d6~\u0017\u00c51E9o\u00ee\u00ad\u00a8\u00de\u008a\u00bf\u00f2\u000e\u0007\u00ab\u0012\u0096\u00f3\u0086\u00df\u000faX)\u0004\u00d3\u00a9\u00e1)\u00f7\u001c\u00f7*\u008b\tN\u00c9\u00c9\u00ff\\\u001d@Q\u0014?K\u0084\u00e3H\u00f4\u0012\u0098\u00dfT\u0019\u00d3\u0092y\u00f0\u0018 ;Q\u00e5\u000b\u0019\u0014\u00bc\u00c0\u00ce\u009b\u008a\u00b7`\u0092,\u00f03h4\u00ce+\u0096\\\u00ddm\u00d7\u00c6\u00a3\u0080\u0006S\u0010K51#M\u00b9Y\u00d9f)bR\u00fesd\u00f2R\u00c3\u00b9\u00d8'\u001a\u009a\u00d6$\u0085\n\u00dcr\u00f2\u009a\u00b1\u00c0A\u00fc\u00eeIf\u00a7\u00bb\u00bc\u00a9Q\u00ac\u00e7\u00c4.3\u00c6\u008a\u00b3v\u00c6\u00ed+\u00dd\u0085\u00f4u4\u00d5\u00eb\u00d9\u00d3\u00fb";
                var5_15 = 328;
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
lbl79:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var2_16 < var5_15) ** continue;
                    var4_14 = "n\u00b6rz\u00c6\f\u00c7\u00a9K[\u0095f\u00a0\u009aQ\u00b1";
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
lbl92:
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
lbl105:
                // 1 sources

                ** continue;
            }
        }
        Criticals.z = var6_12;
        Criticals.A = new Integer[43];
        var44_20 = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Criticals.class, "mode", "getMode()Lsu/catlean/p6;", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "ncpMode", "getNcpMode()Lsu/catlean/wn;", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "packet1", "getPacket1()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "sendPacket1", "getSendPacket1()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "ground1", "getGround1()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "full1", "getFull1()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "offset1", "getOffset1()F", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "packet2", "getPacket2()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "sendPacket2", "getSendPacket2()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "ground2", "getGround2()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "full2", "getFull2()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "offset2", "getOffset2()F", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "packet3", "getPacket3()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "sendPacket3", "getSendPacket3()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "ground3", "getGround3()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "full3", "getFull3()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "offset3", "getOffset3()F", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "packet4", "getPacket4()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "sendPacket4", "getSendPacket4()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "ground4", "getGround4()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "full4", "getFull4()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "offset4", "getOffset4()F", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "packet5", "getPacket5()Lsu/catlean/h;", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "sendPacket5", "getSendPacket5()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "ground5", "getGround5()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "full5", "getFull5()Z", 0)), Reflection.property1(new PropertyReference1Impl(Criticals.class, "offset5", "getOffset5()F", 0))};
        Criticals.delegatedProperties = var44_20;
        Criticals.INSTANCE = new Criticals();
        Criticals.mode$delegate = SettingFactory.modeSetting$default((SettingContainer)Criticals.INSTANCE, "mode", (Enum)CriticalsMode.NCP, null, null, 12, null);
        Criticals.ncpMode$delegate = SettingFactory.modeSetting$default((SettingContainer)Criticals.INSTANCE, "ncp-mode", (Enum)CriticalsNcpMode.NEW, null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible(), ()Ljava/lang/Boolean;)(), 4, null);
        Criticals.packet1$delegate = SettingFactory.settingGroup$default(Criticals.INSTANCE, "packet-1", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible2(), ()Ljava/lang/Boolean;)(), 2, null);
        var45_21 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible3(), ()Ljava/lang/Boolean;)();
        var46_22 = Criticals.INSTANCE.getPacket1();
        var47_23 = true;
        var48_24 = "send-packet-1";
        var49_25 = Criticals.INSTANCE;
        Criticals.sendPacket1$delegate = SettingFactory.booleanSetting(var49_25, var48_24, var47_23, var46_22, var45_21);
        var52_26 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible4(), ()Ljava/lang/Boolean;)();
        var53_27 = Criticals.INSTANCE.getPacket1();
        var54_28 = true;
        var55_29 = "ground-1";
        var56_30 = Criticals.INSTANCE;
        Criticals.ground1$delegate = SettingFactory.booleanSetting(var56_30, var55_29, var54_28, var53_27, var52_26);
        var59_31 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible5(), ()Ljava/lang/Boolean;)();
        var60_32 = Criticals.INSTANCE.getPacket1();
        var61_33 = true;
        var62_34 = "full-1";
        var63_35 = Criticals.INSTANCE;
        Criticals.full1$delegate = SettingFactory.booleanSetting(var63_35, var62_34, var61_33, var60_32, var59_31);
        Criticals.offset1$delegate = SettingFactory.floatSetting((SettingContainer)Criticals.INSTANCE, "offset-1", 0.0f, RangesKt.rangeTo(0.0f, 1.5f), Criticals.INSTANCE.getPacket1(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible6(), ()Ljava/lang/Boolean;)());
        Criticals.packet2$delegate = SettingFactory.settingGroup$default(Criticals.INSTANCE, "packet-2", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible7(), ()Ljava/lang/Boolean;)(), 2, null);
        var66_36 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible8(), ()Ljava/lang/Boolean;)();
        var67_37 = Criticals.INSTANCE.getPacket2();
        var68_38 = true;
        var69_39 = "send-packet-2";
        var70_40 = Criticals.INSTANCE;
        Criticals.sendPacket2$delegate = SettingFactory.booleanSetting(var70_40, var69_39, var68_38, var67_37, var66_36);
        var73_41 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible9(), ()Ljava/lang/Boolean;)();
        var74_42 = Criticals.INSTANCE.getPacket2();
        var75_43 = false;
        var76_44 = "ground-2";
        var77_45 = Criticals.INSTANCE;
        Criticals.ground2$delegate = SettingFactory.booleanSetting(var77_45, var76_44, var75_43, var74_42, var73_41);
        var80_46 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible10(), ()Ljava/lang/Boolean;)();
        var81_47 = Criticals.INSTANCE.getPacket2();
        var82_48 = true;
        var83_49 = "full-2";
        var84_50 = Criticals.INSTANCE;
        Criticals.full2$delegate = SettingFactory.booleanSetting(var84_50, var83_49, var82_48, var81_47, var80_46);
        Criticals.offset2$delegate = SettingFactory.floatSetting((SettingContainer)Criticals.INSTANCE, "offset-2", 0.0625f, RangesKt.rangeTo(0.0f, 1.5f), Criticals.INSTANCE.getPacket2(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible11(), ()Ljava/lang/Boolean;)());
        Criticals.packet3$delegate = SettingFactory.settingGroup$default(Criticals.INSTANCE, "packet-3", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible12(), ()Ljava/lang/Boolean;)(), 2, null);
        var87_51 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible13(), ()Ljava/lang/Boolean;)();
        var88_52 = Criticals.INSTANCE.getPacket3();
        var89_53 = true;
        var90_54 = "send-packet-3";
        var91_55 = Criticals.INSTANCE;
        Criticals.sendPacket3$delegate = SettingFactory.booleanSetting(var91_55, var90_54, var89_53, var88_52, var87_51);
        var94_56 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible14(), ()Ljava/lang/Boolean;)();
        var95_57 = Criticals.INSTANCE.getPacket3();
        var96_58 = false;
        var97_59 = "ground-3";
        var98_60 = Criticals.INSTANCE;
        Criticals.ground3$delegate = SettingFactory.booleanSetting(var98_60, var97_59, var96_58, var95_57, var94_56);
        var101_61 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible15(), ()Ljava/lang/Boolean;)();
        var102_62 = Criticals.INSTANCE.getPacket3();
        var103_63 = true;
        var104_64 = "full-3";
        var105_65 = Criticals.INSTANCE;
        Criticals.full3$delegate = SettingFactory.booleanSetting(var105_65, var104_64, var103_63, var102_62, var101_61);
        Criticals.offset3$delegate = SettingFactory.floatSetting((SettingContainer)Criticals.INSTANCE, "offset-3", 0.04535f, RangesKt.rangeTo(0.0f, 1.5f), Criticals.INSTANCE.getPacket3(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible16(), ()Ljava/lang/Boolean;)());
        Criticals.packet4$delegate = SettingFactory.settingGroup$default(Criticals.INSTANCE, "packet-4", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible17(), ()Ljava/lang/Boolean;)(), 2, null);
        var108_66 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible18(), ()Ljava/lang/Boolean;)();
        var109_67 = Criticals.INSTANCE.getPacket4();
        var110_68 = false;
        var111_69 = "send-packet-4";
        var112_70 = Criticals.INSTANCE;
        Criticals.sendPacket4$delegate = SettingFactory.booleanSetting(var112_70, var111_69, var110_68, var109_67, var108_66);
        var115_71 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible19(), ()Ljava/lang/Boolean;)();
        var116_72 = Criticals.INSTANCE.getPacket4();
        var117_73 = false;
        var118_74 = "ground-4";
        var119_75 = Criticals.INSTANCE;
        Criticals.ground4$delegate = SettingFactory.booleanSetting(var119_75, var118_74, var117_73, var116_72, var115_71);
        var122_76 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible20(), ()Ljava/lang/Boolean;)();
        var123_77 = Criticals.INSTANCE.getPacket4();
        var124_78 = false;
        var125_79 = "full-4";
        var126_80 = Criticals.INSTANCE;
        Criticals.full4$delegate = SettingFactory.booleanSetting(var126_80, var125_79, var124_78, var123_77, var122_76);
        Criticals.offset4$delegate = SettingFactory.floatSetting((SettingContainer)Criticals.INSTANCE, "offset-4", 0.0f, RangesKt.rangeTo(0.0f, 1.5f), Criticals.INSTANCE.getPacket4(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible21(), ()Ljava/lang/Boolean;)());
        Criticals.packet5$delegate = SettingFactory.settingGroup$default(Criticals.INSTANCE, "packet-5", null, (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible22(), ()Ljava/lang/Boolean;)(), 2, null);
        var129_81 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible23(), ()Ljava/lang/Boolean;)();
        var130_82 = Criticals.INSTANCE.getPacket5();
        var131_83 = false;
        var132_84 = "send-packet-5";
        var133_85 = Criticals.INSTANCE;
        Criticals.sendPacket5$delegate = SettingFactory.booleanSetting(var133_85, var132_84, var131_83, var130_82, var129_81);
        var136_86 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible24(), ()Ljava/lang/Boolean;)();
        var137_87 = Criticals.INSTANCE.getPacket5();
        var138_88 = false;
        var139_89 = "ground-5";
        var140_90 = Criticals.INSTANCE;
        Criticals.ground5$delegate = SettingFactory.booleanSetting(var140_90, var139_89, var138_88, var137_87, var136_86);
        var143_91 = (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible25(), ()Ljava/lang/Boolean;)();
        var144_92 = Criticals.INSTANCE.getPacket5();
        var145_93 = false;
        var146_94 = "full-5";
        var147_95 = Criticals.INSTANCE;
        Criticals.full5$delegate = SettingFactory.booleanSetting(var147_95, var146_94, var145_93, var144_92, var143_91);
        Criticals.offset5$delegate = SettingFactory.floatSetting((SettingContainer)Criticals.INSTANCE, "offset-5", 0.0f, RangesKt.rangeTo(0.0f, 1.5f), Criticals.INSTANCE.getPacket5(), (Function0<Boolean>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, settingVisible26(), ()Ljava/lang/Boolean;)());
    }

    public static void zkmSetFlag(String[] stringArray) {
        t = stringArray;
    }

    public static String[] java_lang_String_arr_zkmGetFlag() {
        return t;
    }

    private static NoWhenBranchMatchedException zkmRethrow(NoWhenBranchMatchedException noWhenBranchMatchedException) {
        return noWhenBranchMatchedException;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x4BDF;
        if (i[n2] == null) {
            Object[] objectArray;
            try {
                Long l2 = Thread.currentThread().threadId();
                objectArray = (Object[])k.get(l2);
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/PKCS5Padding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    k.put(l2, objectArray);
                }
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u2", exception);
            }
            byte[] byArray = new byte[8];
            byArray[0] = (byte)(l >>> 56);
            for (int i = 1; i < 8; ++i) {
                byArray[i] = (byte)(l << i * 8 >>> 56);
            }
            DESKeySpec dESKeySpec = new DESKeySpec(byArray);
            SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
            ((Cipher)objectArray[0]).init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
            byte[] byArray2 = h[n2].getBytes("ISO-8859-1");
            Criticals.i[n2] = Criticals.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return i[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = Criticals.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/u2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x22FB;
        if (A[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = z[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])B.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    B.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/u2", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            Criticals.A[n2] = n3;
        }
        return A[n2];
    }

    private static int c(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = Criticals.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/u2" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(Criticals.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(Criticals.class, "c", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

