/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util.rotation;

import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.attack.Aura;
import su.catlean.module.attack.AuraWhileShieldingMode;
import su.catlean.util.Globals;
import su.catlean.util.InteractionUtil;
import su.catlean.util.rotation.Rotation;
import su.catlean.util.rotation.RotationHandler;
import su.catlean.zkm.ZkmKeys;
import su.catlean.zkm.ZkmReferences;

public final class AdvancedRotationHandler
implements RotationHandler {
    @NotNull
    public static final AdvancedRotationHandler INSTANCE;
    private static boolean M;
    private static final String a;

    private AdvancedRotationHandler() {
    }

    /*
     * Unable to fully structure code
     */
    @Override
    public void preAttack(int var1_1, long var2_2) {
        block3: {
            block5: {
                block4: {
                    block2: {
                        var4_3 = (long)var1_1 << 32 | var2_2 << 32 >>> 32;
                        var14_4 = ZkmReferences.zkmBootstrap("\u00c5", (long)8223014789551767705L, (long)var4_3);
                        v0 = Globals.getPlayer();
                        if (var14_4 == false) break block2;
                        if (!v0.isUsingItem()) ** GOTO lbl-1000
                        v0 = Globals.getPlayer();
                    }
                    if (v0.getActiveItem().getItem().getUseAction(Globals.getPlayer().getActiveItem()) == UseAction.BLOCK) {
                        v1 = true;
                    } else lbl-1000:
                    // 2 sources

                    {
                        v1 = AdvancedRotationHandler.M = false;
                    }
                    if (!AdvancedRotationHandler.M) break block3;
                    v2 = Aura.INSTANCE.B9();
                    v3 = AuraWhileShieldingMode.UNPRESS;
                    if (var14_4 == false) break block4;
                    if (v2 == v3) break block5;
                    v2 = Aura.INSTANCE.B9();
                    v3 = AuraWhileShieldingMode.LEGIT;
                }
                if (v2 != v3) break block3;
            }
            Globals.getInteractionManager().stopUsingItem(Globals.getPlayer());
        }
    }

    @Override
    public void postRotate(@NotNull Rotation rotation, short s, int n, int n2) {
        block0: {
            Intrinsics.checkNotNullParameter(rotation, a);
            if (!M || Aura.INSTANCE.B9() != AuraWhileShieldingMode.UNPRESS) break block0;
            InteractionUtil.useItem(Hand.OFF_HAND, rotation.getYaw(), rotation.getPitch());
        }
    }

    @Override
    public void reset() {
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        long l = ZkmKeys.a(202333040935328619L, 652114779646073318L, MethodHandles.lookup().lookupClass()).a(257937556679860L) ^ 0xB55BC8D15DEL;
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        byte[] byArray = new byte[8];
        byte[] byArray2 = byArray;
        byArray[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher.init(2, (Key)secretKeyFactory.generateSecret(new DESKeySpec(byArray2)), new IvParameterSpec(new byte[8]));
                byte[] byArray3 = cipher.doFinal("w\bBk2\u00b9\u00b4\u0095\u00d7Q8U\u00a2hc\u00d0".getBytes("ISO-8859-1"));
                a = AdvancedRotationHandler.zkmDecodeUtf8(byArray3).intern();
                INSTANCE = new AdvancedRotationHandler();
                return;
            }
            byArray2 = byArray2;
            byArray2[n] = (byte)(l << n * 8 >>> 56);
            ++n;
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
}

