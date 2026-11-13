/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
 *  org.apache.commons.lang3.SystemUtils
 */
package su.catlean;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import kotlin.Metadata;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.apache.commons.lang3.SystemUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.ExcludeCommon;
import su.catlean.LoadingPanel;
import su.catlean.zkm.ZkmKeys;

@ExcludeCommon
@Metadata(mv={2, 2, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0005\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0003R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2={"Lsu/catlean/PreLaunch;", "Lnet/fabricmc/loader/api/entrypoint/PreLaunchEntrypoint;", "PreLaunch", "()V", "", "L", "onPreLaunch", "Ljavax/swing/JFrame;", "G", "Ljavax/swing/JFrame;", "catlean"})
public final class PreLaunch
implements PreLaunchEntrypoint {
    @NotNull
    public static final PreLaunch INSTANCE;
    @Nullable
    private static JFrame jFrame;
    private static final long a;
    private static final String b;
    private static final long c;

    private PreLaunch() {
    }

    public final void L() {
        block0: {
            JFrame jFrame = PreLaunch.jFrame;
            if (jFrame == null) break block0;
            jFrame.setVisible(false);
        }
    }

    public void onPreLaunch() {
        if (!SystemUtils.IS_OS_WINDOWS) {
            return;
        }
        SwingUtilities.invokeLater(PreLaunch::onPreLaunch$lambda$0);
    }

    private static final void onPreLaunch$lambda$0$lambda$0(ActionEvent actionEvent) {
        block1: {
            JFrame jFrame = PreLaunch.jFrame;
            boolean bl = jFrame != null ? jFrame.isVisible() : false;
            if (!bl) break block1;
            JFrame jFrame2 = PreLaunch.jFrame;
            if (jFrame2 != null) {
                jFrame2.repaint();
            }
        }
    }

    private static final void onPreLaunch$lambda$0() {
        Serializable serializable;
        JFrame jFrame = serializable = new JFrame(b);
        jFrame.setDefaultCloseOperation(2);
        jFrame.getContentPane().add(LoadingPanel.INSTANCE);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLayout(new GridLayout());
        jFrame.setLocationRelativeTo(null);
        PreLaunch.jFrame = serializable;
        jFrame = serializable = new Timer((int)c, PreLaunch::onPreLaunch$lambda$0$lambda$0);
        ((Timer)((Object)jFrame)).setRepeats(true);
        ((Timer)((Object)jFrame)).start();
    }

    /*
     * Enabled aggressive block sorting
     */
    static {
        a = ZkmKeys.a(-739656789674311063L, -5153100477730215871L, MethodHandles.lookup().lookupClass()).a(274915510026693L);
        long l = a ^ 0x6025D1067C84L;
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
        byte[] byArray3 = cipher.doFinal("\u00818\u00e8\u000b&\u0005\u00b6\u00ba".getBytes("ISO-8859-1"));
        b = PreLaunch.zkmDecodeUtf8(byArray3).intern();
        Cipher cipher2 = Cipher.getInstance("DES/CBC/NoPadding");
        SecretKeyFactory secretKeyFactory2 = SecretKeyFactory.getInstance("DES");
        byte[] byArray4 = new byte[8];
        byte[] byArray5 = byArray4;
        byArray4[0] = (byte)(l >>> 56);
        int n = 1;
        while (true) {
            if (n >= 8) {
                cipher2.init(2, (Key)secretKeyFactory2.generateSecret(new DESKeySpec(byArray5)), new IvParameterSpec(new byte[8]));
                byte[] byArray6 = cipher2.doFinal(new byte[]{126, -74, 102, 62, -51, 118, -78, -105});
                c = ((long)byArray6[0] & 0xFFL) << 56 | ((long)byArray6[1] & 0xFFL) << 48 | ((long)byArray6[2] & 0xFFL) << 40 | ((long)byArray6[3] & 0xFFL) << 32 | ((long)byArray6[4] & 0xFFL) << 24 | ((long)byArray6[5] & 0xFFL) << 16 | ((long)byArray6[6] & 0xFFL) << 8 | (long)byArray6[7] & 0xFFL;
                INSTANCE = new PreLaunch();
                return;
            }
            byArray5 = byArray5;
            byArray5[n] = (byte)(l << n * 8 >>> 56);
            ++n;
        }
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

