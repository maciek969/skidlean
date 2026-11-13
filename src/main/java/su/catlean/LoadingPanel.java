/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.loader.api.FabricLoader
 *  net.fabricmc.loader.api.ModContainer
 */
package su.catlean;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JPanel;
import kotlin.jvm.internal.Intrinsics;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.jetbrains.annotations.NotNull;
import su.catlean.ExcludeCommon;
import su.catlean.zkm.ZkmKeys;

@ExcludeCommon
public final class LoadingPanel
extends JPanel {
    @NotNull
    public static final LoadingPanel INSTANCE;
    @NotNull
    private static final ModContainer modContainer;
    private static final int c;
    private static final int Q;
    private static final long a;
    private static final String[] b;
    private static final String[] d;
    private static final Map e;
    private static final long[] f;
    private static final Integer[] g;
    private static final Map h;
    private static final long i;

    private LoadingPanel() {
    }

    @NotNull
    public final ModContainer getModContainer() {
        return modContainer;
    }

    private final Object readResolve() {
        return INSTANCE;
    }

    @Override
    protected void paintComponent(@NotNull Graphics graphics) {
        Intrinsics.checkNotNullParameter(graphics, "g");
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(Color.GREEN);
        graphics2D.setFont(new Font("Monospaced", 1, 12));
        graphics2D.drawString("  /$$$$$$              /$$     /$$                                    ", 10, 20);
        graphics2D.drawString(" /$$__  $$            | $$    | $$                                    \n", 10, 30);
        graphics2D.drawString("| $$  \\__/  /$$$$$$  /$$$$$$  | $$        /$$$$$$   /$$$$$$  /$$$$$$$ \n", 10, 40);
        graphics2D.drawString("| $$       |____  $$|_  $$_/  | $$       /$$__  $$ |____  $$| $$__  $$\n", 10, 50);
        graphics2D.drawString("| $$        /$$$$$$$  | $$    | $$      | $$$$$$$$  /$$$$$$$| $$  \\ $$\n", 10, 60);
        graphics2D.drawString("| $$    $$ /$$__  $$  | $$ /$$| $$      | $$_____/ /$$__  $$| $$  | $$\n", 10, 70);
        graphics2D.drawString("|  $$$$$$/|  $$$$$$$  |  $$$$/| $$$$$$$$|  $$$$$$$|  $$$$$$$| $$  | $$\n", 10, 80);
        graphics2D.drawString(" \\______/  \\_______/   \\___/  |________/ \\_______/ \\_______/|__/  |__/\n", 10, 90);
        int n = 250;
        int n2 = 110;
        graphics2D.drawString("v" + modContainer.getMetadata().getVersion(), 10, 110);
        graphics2D.drawString("Welcome back, " + System.getProperty("user.name") + "! =^.^=", 10, 210);
        if (System.currentTimeMillis() % 1000L > i) {
            graphics2D.drawString("                                   \n", n, n2);
            graphics2D.drawString("                                    \n", n, 120);
            graphics2D.drawString("                    @@@@@           \n", n, 130);
            graphics2D.drawString("                   @@@@@@@          \n", n, 140);
            graphics2D.drawString("                 @@@@@@@@           \n", n, 150);
            graphics2D.drawString("                @@@@@@@@@@          \n", n, 160);
            graphics2D.drawString("               @@@@@@@@@@@@@        \n", n, 170);
            graphics2D.drawString("                 @@@    @@@@@@@     \n", n, 180);
            graphics2D.drawString("                          @@@@@@@   \n", n, 190);
            graphics2D.drawString("                             @@@    \n", n, 200);
            graphics2D.drawString("                                    \n", n, 210);
            graphics2D.drawString("            @@@@@@@@@@@@@@@@@@@     \n", n, 220);
            graphics2D.drawString("      @@@@@@@%@%%%%%@@@@@@@         \n", n, 230);
            graphics2D.drawString("         %@@@@@@@@%@@@@=            \n", n, 240);
            graphics2D.drawString("              @@@@@@@@              \n", n, 250);
            graphics2D.drawString("               @@@@@@               \n", n, 260);
            graphics2D.drawString("               @@@@@@               \n", n, 270);
            graphics2D.drawString("              @@@@@@@@              \n", n, 280);
            graphics2D.drawString("           .@@@@-  @@@@@            ", n, 290);
        } else {
            graphics2D.drawString("                                    \n", n, n2);
            graphics2D.drawString("                                    \n", n, 120);
            graphics2D.drawString("                                    \n", n, 130);
            graphics2D.drawString("                                    \n", n, 140);
            graphics2D.drawString("                                    \n", n, 150);
            graphics2D.drawString("                  @@@@%             \n", n, 160);
            graphics2D.drawString("        &         @@@@@@            \n", n, 170);
            graphics2D.drawString("     %  '         @@@@@@@@@@@@@@@@  \n", n, 180);
            graphics2D.drawString("      %   .       @@@@@@@@@@@@@@@@  \n", n, 190);
            graphics2D.drawString("    %    &        @@@@@@            \n", n, 200);
            graphics2D.drawString("       %          @@@@@@            \n", n, 210);
            graphics2D.drawString("            @@@@@@@@@@@@@@@@@@@     \n", n, 220);
            graphics2D.drawString("      @@@@@@@%@%%%%%@@@@@@@         \n", n, 230);
            graphics2D.drawString("         %@@@@@@@@%@@@@=            \n", n, 240);
            graphics2D.drawString("              @@@@@@@@              \n", n, 250);
            graphics2D.drawString("               @@@@@@               \n", n, 260);
            graphics2D.drawString("               @@@@@@               \n", n, 270);
            graphics2D.drawString("              @@@@@@@@              \n", n, 280);
            graphics2D.drawString("           .@@@@-  @@@@@            ", n, 290);
        }
    }

    /*
     * Unable to fully structure code
     */
    static {
        block24: {
            block23: {
                block22: {
                    block21: {
                        block20: {
                            LoadingPanel.a = ZkmKeys.a(-3359610190115471231L, -4961148333626373299L, MethodHandles.lookup().lookupClass()).a(233866131837224L);
                            var25 = LoadingPanel.a ^ 56814517395555L;
                            LoadingPanel.e = new HashMap<K, V>(13);
                            var16_1 = Cipher.getInstance("DES/CBC/PKCS5Padding");
                            v0 = SecretKeyFactory.getInstance("DES");
                            v1 = new byte[8];
                            v2 = v1;
                            v1[0] = (byte)(var25 >>> 56);
                            for (var17_2 = 1; var17_2 < 8; ++var17_2) {
                                v2 = v2;
                                v2[var17_2] = (byte)(var25 << var17_2 * 8 >>> 56);
                            }
                            var16_1.init(2, (Key)v0.generateSecret(new DESKeySpec(v2)), new IvParameterSpec(new byte[8]));
                            var23_3 = new String[43];
                            var21_4 = 0;
                            var20_5 = "\u00f6\u008a!-\u00af\t\u00cc\u00e08\u00e1\u001fI\u00d3j\u0097\u00b9\\oi*\u00cdq\u00ab\u00f7_\u00bc\u00c6uBvF\u0089(J\u008e:\u008e\u00a4\u001e{>\u009f\u0086\u00be\u0095\u0016\u00f6\u00ec\u00f5\u00b7\u00af\u00da\u00daU\u00ce-\u00abO\u00b9\u00d4(\u008e\u0006\\@\u00c7`Ch\u0016\u0085\u0003i\u00e5\u0013n\u00c2C\fJ\u0080\u001cz\u0097\u000f\u00e8\u001f\u00de\u00d2C\u0004\u00a0:\u00ef<\u00ec)?\u0085\u00d0\u00dbB/\rQ\u00e5\u00d2\u00aeD\u0098\u00a6`\u008d\u008fK\u00d4s\u00ed\u001e\bit\u00b2\u0086\u008f\u008d\u009dL\u00bf@bh\u00a2[\u00c6;\u00a0\u00b3t\u000e\u00b2\u00f0\u0092\u007f\u00aa\u00da\u00b0\u00e1w\u0003 \u000b\u00d8\u00dbv\u00b4-\u00cd\u0018\u00c3\u00eb\u00994\u00ce\u001c\u00e8<\u001d\u00853\u00e2B\u00a8\u000b\u00b7m\u0015\"\u0006\u00e4\u00ea\u00cb\u00b1\u00f9qs\u0015q\u00cb\u00bdW2G\u00f5@\u00fd\u00d4\u0090\u001f\u0016\u00de\u0015:W0BM\u00b6\u0002\u00a1\u0088j\u00e0\u001d]\u0000\u0000\u00c8\u00e5\u00e4\u009a#\u0085\u0005\u007f\u00e9\u00bc\u0094\u00f5G\n\t-\u0080\u001e\u00a5\u000eE\u00cc\u0017L\u0012\u00ab\u00b7\u000e\u00fe?\u0080v\u00c0\u00a3\u000b#\u00be\n\u0013-\u00ad\u001c@n\u00cfw\u009e\u00b4=DFb\\\u00f6\u0095q\u0012''\u0011;\u00e7\u00ee\u0091\u00a4\u00f5\u001f\u0090\u0013O3\u00dc6\u008b\u00b1W\u00ee\u00b6\u0018,\u00ea]\u0001kH\u00cf=\u000eD\u00c3\u00c2M;\u00a6\u00b9\u00e5\u0001\u00ed\\X\u0082\u00a4\u00edT'\u0019\u00a4H\u0004\u008e\u00f2\u00a4\u00d5\u0091\u00d3\u00ef;\u00c0\u00e0g\u00c9z\u00c2(\u00b6+\u001e\u001d\u00b8@R#\u0017|\u0006\u00cf2\u0010\u0003\u001ec!\u00f7\u0010D^\u00e5\u0083\u00ca\u00a0R\u00b1H[\u0098\u00cen(\u00fa\u00edS\u00d5\u00e2\u0085X\u00dab\u00d7T\u0086\u00e7F\u00f9c\u00f2\u00ac[\u00f2\u0089~@\u00df\u00b2G\u00e8\u0011\u0003\u008c\"w\u00c7>\t\u001a\u00df\u0012\u009d\u00bc\u00f7\u00c1o`\u00b7\u00ec{\u00dd<\u00cd\u00c6Dhv0f/\u0002\u00ba\u0018\u00a8\u00f1\u00cd\u00a7\u00c6\u00f7\u0083\u001e^\u00fb\u0001\u0096\u0096\u0004\u009cFC\u0082\u008f\u00ba\u00d4,\u0088\u00a9\u0096\u00eb\u00c5@\\\u001e%\u00ac>.R\u00cc\u00ee\u00ad5\u008e\u00ba\u001e\u00a8\u00adv\u00d7n\u009a\u00d9I\u00a5B\u00b3Bx\u00ca\u00ddk\u0089Y\u00b6\\,\u0094i0\u00e3P\u00ef'\u00d0\u00d2\u00ebes\u00ca\u00cb\u00f3\u008f\u00b5y\u001dY\u00ca\u000ewdPj\u009f1\u00fd(\u00e7\u00c9\u001d\u001a\u00bd\u00b1\u00dat\u00ef+\u00f2\u00d4\u00c4L\u00f4\u00f4\u0088w\u0080\u0081\u0015\u00dc}\u00b7z\u0098\u009c0\u00d2}\u00a4\u00938\u00caIM\u001bKS+H\u00b7\u00dbV\u001e)\u00c3\u00bd\u001f\u00de\u00dc\u00d3\u009dR\u00d9\u0005$y\u00b7\u0004\u0099g\u0010\u00de\u0091!\u00bc\u0013cS\u0098@\u00ae\u00a6\u0005.\u00fb\u0082\u0012\u00bf\u00b4o\u00c9\u00e6\u008f\u0006^\u009f\u0000X&\u0081I\u008a\u009d\u0084KQ\u0013\u0090\u00bd\u00fc\u00d1\u00c9\u00a4c\u00a7+\u00cb\u00e6)\u00a33\u0018`6\u0017*\u00e4\u0081|\u00d3\u00c1W\u00c2\u009b5\u0005\u00b3\u00ae\u00e3\u00bcw28s\u0099\u0083@R8\u00c3\u00a2\u00c8\u00de\u00e7\u0011\u0096\u00c3H\u00abnM\u001d'\u00b3\u0085\f8_\u001d+0ai\u00d1}\u00f4J\u00fbV\u00af0\u000b\u0090N\u00d9\u00b7]\u00b2\u00db\u0013e\u00d4\u00f4\u000f\u00a1\u00ab\u0004\u001c0\u00d3\u00cf\u00d6\u00c9`\u0014F\u00a7\u00cfk\u009b$@7a\u008b\u0095,\u008d<gK\u00d0OZ<*I$_\n\u009c%^\u0017\u0014$\u00028~\u00b0u\"\u00d6g,uK\u0090\u0012\\\u0016\u00b6\u009dv\u00ea\u00d1\u00beY\u00e3\u00ec1\u0083\u00efO\"\u0096\u00d6^\u00ea\u00cb1g\u0015\u00e9\u00d1\u00aap\u0012\u00a1\u00e3\u00e7\u007f\u00ae\u0004\u00b2\u00a8br\u00d6\u00c4\u000bc\u00dfW\u00b5W\u00df\u00db\u00d4\u0088\u00c3\u00bfu\u00a4\u008b\u00aaP\u0096-;\u00d9\u00f7\u0006\u00d6->\u00cd\u00b2:\u00df\u00ae1\u00f5a%Y\u009f\u00d9<\u00ac\u00c2V(\u00f3\u00aaw\u0086\u00983\u00b7\u008c\b\u00f2T\u00be\u0019=\u00e8+\u0016V\u0007\u00cew@A)\u0005\u0082\u0001\u0088\u009a\u0006\u00a3\u0088\u008c\u00a7\u0083\u00192\u00ba\u00b1\u00a4\u00cbd5\u00a0\u009c]40\u00b6\u007f\u00b6$\u00fb6L\u00fe@\u001d-\u00a7\u001d\t\u0006\u00fd$\u00b20\u00a2\u00f8(3\\\u0019nm\u00ab\u000e\u00a4\u0083]bS\u00f1\u001f\u000f\u00ac\u00bcE\u008c\u00e7fh\u008a<yt\u00a93#\u00d1\u00103\u0090\u00cfW\u00c8\u00d8ZR\u00a37\u0083Muj\u00fa?\u00a7\u008c\u0098\u0000xA\u00ae\u00fe5\u000b\u009b~\u00f8\u00a8^\u00a3\u00a2\u00934q\u0003\u00b0\u00e4G\u00d0g\u0012\u00a2\u008e7y{O\u0096,\u00960%p\u00a6\u0086\u00e7DPm\u00c7\u0099\u00d7\u0000\u0082(\u0087\u00c6'\u00cbBV-|Ri\u00a9\u00d2t\u00dc\u00e7\nPV\u009c\u00cfh\u001a\u00d2N\u0010i\u00e4\u0017a\u00ba\u0087=nm\u00b6\u00d8`\u000f\u00eda\u0093\u00a5\u00fff\u00efp\u0010j|(\u0019\u00c8^\u00c6K\u0017\u0011c\u0002U\u00f2\u009d\u0012f\u00a0S\u001f\u0095\u00fc\u00f7\u0088I\u0093S@\u0019\u0004\u008e\u00af\u009bf\n\u00a9~\u0003'\u00cb\u008b\u00e6\u00f9m\u001c\u00cfl\u0094\u009fomS\u00990\u001bs$\u00b0\u00e9\u0013\u00fe\u00aes\u00aaF2\u00d6\u00b04\u00b2\u00e0k\u00ee\u0000\u0085\u0012\u00e9H^)\u0086\u00a6]\u0081v\u008d\u009b\u00b8@\u00bc\u00a7b@\u00ab\u000e2\u00f2\u00da\u00af_5\u0094sDb\u0017\u007f\u00feQ\u0010\u00bb\u0092!|\u00f9\u0019\u0085\u00b9\u00f2\u00df<O\u00b6\u00f8\u00c6E\u00e1\"\u00c8Y\u00857\u00b3\t\u001c\u009b\u00fe\u0015\u001c\u000f\u0000\u00efR\u008e\u00c2=\u000e8U\u0082[2=\u00efK\u0082\u00da@\u00bb\u0093\u0093N\u000ekJ\u009b\u0094Y\u0012F\u00f8\r\u009a\u00a1\u009e\u0085\u00f7\u00e4\u0017\u008d\u00cdX?\u00d2`\u00fb\u00d9p\u00c4\u00e9\u00b5B\u00c2\u00bc\u009c\u00ceT\u00b6\u00a0\u00a6z\u0097p\u00c9\u00cb\u00a8Io\u0089\u00bc\b\u00a49\u00e0\u00fb\u0080\u0099K\u000e\u001a\n\u0099@X\u00f2\u00b6DU\u00ac\u00a0\u00a7H\u008e>#\u001a?y\u001f\\\u00a5\u0084\u008d\u0014c\u00fd\u00fa\u00f3\u00c3\u0017\u00bd\u00aa,\u00bd\u000f\u00ae\u00f8\u00f5+(cI?R\u00d7c\u00c6\u0010W\u00eb\u00bc\u0006#\u0084\"4\u00c8\u001b9#\u00b6\u00bb\u00a1\u00e0\u00a7\u0002\u00dbH\t\u00e1\u0089\u00cb\u001ep7\u008c\u000f\u001cT\u0084\u0083\u008fbjWuf\u0090V\u00dfO4\u00eaB\u00d6c\u00c3\u00ae\u00af%B\u0005<\u00b4}\u001a\u0090A\u00ab\u00b6\u00b0\"V\u009b\u00bc/\u00bf\u0014\u00d4VKU\u00c7i\u0014\t\u001e\u00aa\u0097\u0018\u0096\u0019^\u007f\u009b\u00ad:#\u00f7\u00b2@\u00db\u00dc\u00a5Y\u00de6D\u0095\u0086\u0097\u00f6\u00d0\u00f8\u00dc\u001f\u0091o\u0091K\u00a0\u00cc\u0012 \u00fe>\u00a0\u00b7+\u00f3\u00e1\u00eb\u00e5r\u001c\u00b1g\u00ed\u00dc\u00a3uLz\u0089\u0011\u00c6;\u0097\u00e8\u0089N\u00b6&zt\u00f1\\\u00ae\u00d9\u00b4\u001em\u00cd\u0095\u00ed@q\u00cf\u00ae\u00e6\u00e1\u00e0\u00906\u00ceU\u00cc\u00ecm\u008c*\u00ba\f\u00a0\u0098\u00b6aCBO\u00fel\u00f6\u00a0\u0013\u00db\u0004)v2\u00fduh(G\u0080\u00b8\u00ddV\u00d3i\u00f1\u0090X[+\u00b6'e\u00c7\u000e\u0093\u0084\u00ce\u00f2O\u0010H\u008e&\u0018_\u0003k\u0098\u0098\u00c5Biw\u00a6\u00d0\u00e9\u00ffC\u0000H\u00ceS\u00f1j\u00b8(\u00b5\u00f6@JC\u00f6\u00cf\u0086\u00b0N\u00f3\u00e4'p\u00dbX\u00ccYY\u00c0;\u00a0\u0090{\u001b3\u00b3F\u00b7i\u00f0t\u00c4Y\u00c3\u00f9ZgU\u00af\u00c9\u00ed\u00b3\u00a1\u00d2n\u00ca\u00e4\u008f\u008a\u00c66\u00f0J\u00f0\u00d2cM\n9)\u00edls\u00f7\u00fd\\@*\u008f#\u00a9\u0010\u0000\u0080\u0089\b\u00c9\u00bb\u0099c\u0003\u008a\\\u00f6\t\u0081a\u00e7\u00d29\u0083\u00be\u00denK\u0001\b\u0007\u00e0g\u00b8\u00cbJ\u00b7\u00fdt\u00cbZX\u00c8\u001c\u00c3\u00aa\u009dM$\u0094\u00d7\u00df\u0013\u0000\u00c1J`7U\u00f7C|\u00a9\u00b5@\u0000\u008a\u007fF\u00c3X\f\u00a2\u00be@\u00be\u00dcu\u001d\u0012>\u00b9R\u00ee\u001b\u00d2\u00da\u00af\u00d3\u00881q\u0017\u008b~O#\u00c3\u0012\\to\u00b0\u00baZ\f9\u00eb\u00e5\u009f\u001e\u00ec\u0010\u0005\u008b=\u0002GGliP\u00c4\u00d9[V\u00a1\u00deT@\u00fa\u00a1\u0090\u00e1^YW\u00e2\f\u00d6\u00b5)\u00e1)\u00c1\u0015\u009b\u00ac\u00aa\u00ad\u008d\u00cc\u00f6fb\u00b6Bk\u00c5@\u008fJ=R\u0017c\u00d1sn\u00aa\u00dc[9v\u00f7\u009fPLf8\u00a3_\u00d5S\u0090H\u001c,\u00b3\u00bam_E\u00ff z%\u008e>\u0006d\u008e^\u001f_C\u009e]\u00a9\u00b3|^\u00fc\u00af\u00c4\u0098\u009a{n\u00e9\u00e4\u008c\u00f1T\u0087\u0096\u00fdH\u00dc\u00c5X\u007f\u008f\u00bcF8\u00f2\u000f\u00b7@\u00d2+\u0003#\u00ba\u009e\u00ec\u00d9\u00f6\u00fc+\u0084\n\u0003\u00c6\u009c\u00f4\u00c2\u00c9'\u0089#\u00ab\u00a2*A\u00b0A\u00ecw\u00a5yR\u00c87h\u00f8\u00f7\u00be[-:\u009f\u0000?j`\u00199\u00d1\u00c1\u0082\u0000%?\u00d1\u00b05\u0091Y@\u0084Y\u00bb}zl\u00d1\u00af\u00cf\u008d\u00ed\u00f3\u0016\u0011\u0012=\u00cc\u0013/q)ty\u00a7]\u0004rD\u000ed\u001f\u0017A\u00a1\u001f\u00fd\u00f0#|\u009b\b9\u0084m\u00e3'z\u00f8\u00aa\u00c0}6\u00a1\u00e6p\u0082H\u009f\u00c1\u00fd\u00c9c\u00d4\u0082p\u00e0\u008f\u00a3\u0017\u00b3\u00cc\u00fc&b\u009a\u0088Mr\u00e3\u00bd\u00b3_\u00a7\u00f4H\u00c9\f<sj\u009e\u0086m\u00e2\u00c0>\u007f\u00ce\u0094 %t\u0018\u00ee\u00b8(\u009a2\u0088\u00c7\u008aGuG-\u00e7\u008a\u00edj\u0010Nm\u00b4\u00a7w\u0000\f}\u001dhu\u00b1\u0098g\u0086\t\u00ee\u00f0\u00fb\u00ccie\u0016\u00a8O\u00e1\u00e5y\u00c4\u00dbI@\u00e21u3\u000bB0\u0018>\u008aA\u00efcB\u00f6h\u00b6\u00de\u00ceZ\u00ba-\u00e7X\u0091\u0010\u00fdF\u00ddFI\u00d4\u00af\u0017\u00cey/l\u00fa9\u00ffGx\u00fa\u0014\u00e3V\u00a9k\u001dV\u00f3\u00b5\u00b7\u00c6\u00c2\u00cf\u0018\u00afacj\u0087\u0007\u00f7/\u008d\u00cc\u0011+\u00f9\u0089p\u0005\u00aey\u00ae\u00c1\u001dpc\u00f0\u00b1\u0002+\u008d\u00b9\u00e4\u00d1\u00e4\u0014\u0088\u00c0\u00c5\u00ca\u00aa\u00c37 *\u00d16\u0015\u00b2\u009d\u00a7\u0010\u0003\u008ec4g]R\u008c\u00ad$\u00b4\u0003\u000b\u00c7<\u00ed\u0082 \u00c3`\u008e\u00f83\u00b1\u00f3\u0089\u00ae\u00b6C\u00c0\u000e\u00c3\u000e\u00cc?\u00f7~k\u008c0\u0098\u00f2\u0086\u0007\u00cbI\u00f4Da^\u00f9y\u00a7\u00a5j'pe\"\u00a8}\u0002\u00d1\u00b7\u00b1\u001a>\u008a\u0013\u00b5\u0007\u0092\u0097$4}\u00bc\u00f9\u00b5\u0091\u0093w\u00f4>\u00f4\u00e3\u0087\u0015\u0081\u00b6\u00d7\u000b\u001d\u00cc\u009b\u00e6C\u00b3\u00d5/n\u00a6{}\u00aa\u00da{N\u0013=<\u0010#\u00f0:%Dz\u008d\u0082\u00b0\u001c\u00de\u00c8\u008a\u00e7\u0016\u0084\u00f0\u0015[\u00a0n\u0087\u0087\u001b\u00ad\u00b3Jx.\u0091\u00e2\u00f9\u00ac\u0085z\u00ed\u00bb\u00cc\u00f3\u000eY\u001c\u00c0\u0083\u00a98\u00aa\u001d\u0094\u00a4\u0014\u00a08T\u00f9\u009c\u001dp\u0099\u0001\u00f0\u00b9uA\u0097\u00f6\u001cT\u009c\u00b5\u008a|r\u0092\u00de\u00e3\u00b4\u00cbl\u0006\u00d8\u00ebGJ24'\u00bd\u00e6\u0010+\u00fd\r\u0086A\u0095\u00e1\u0096HS\u00a5\u0095\u00df\u0091}\u00ea_\u00e4@\u00eb\u0082`\u00e7\u00d3\u0099\u00bf\u0000\u00ca\u00ecJ~\tP\u00bd0jBQ\u00af\u00c0\u008d\u0081\u0001\u00c5\u0014Z\u00e0\u0016)\u001a\u0082\u00a8\u00d9>\u000b.\u00d8\u00c7\u00adw\u001b\u00fa\u00a5\u00baT\u008d\u0014\u00e6}\u0005\u00da\u00cc\u00cc\u00c5\u0011\u0099\u0011.Q!\u0010\u0082\u00f9\u0007\u00c6\u00dfr\u00e5r$\u0085\u00c7\u00a5=\u00cbo\u00ae8+\u0084{\u00eaN[\u00aa\u0012\u00d0\u00ed\u008cX\u00cf\u00cb\r\u008c\u00f0\u00b2t\u00fd\u00afMr=\u0095q{\u009d\u00b409\u0016\u00e6\u00c7\u0007\u00ea\u00c0\b\u00c9\u00a8\u0006\u00f9\n\u00d4\u00cd\u00b5\u00d2m\u00f2\u00b9\u00bf\u00d9\u0006\u008eyZ@_^r\u00a4<9I\b$H\u00e0Z\u00a0\u00aa}B\u00f5W\u00e0:\u00cfZ\u00ff\u0015+\u00b2\u00ac\u00b5\u008f\u00f7O\u0091\u00ae\u00a8O\u00a8w\u00f6OzV'\u00b2\u00f1\u009bIe\u00c7\u001bn\u008e{xd\u00c0$#h\u00c7`\u00b6n\u0013\u00a3p\u00ac\u00f3r\u00deB\u00db\u00a0'.\u00adKC\u00b1\u00c5\u00b0@\u00b6X'Z[\u00b2N}\u0017U\u00ec>\u00f5\u00bc\u00b0\u00d9<4[\u0088\u00d7\u00b5\u00a1\\\u00e3>vm\u00f0\u00f3m0\u00cdPU\u0099a\u0011#\u00caC}\u00e9\u00e1i\u00fd \u00e6\u00c5\u00ed\u00cb\u0091\u0003.g}\u0013<\u0005\u00067u\u00d9\u00c2D\u00d4\u0007X\u00aa\u00a2K.f\u00a5\u0098&v\u009bf$&\u001e\u000b\u00fc \u00dc\u00ce\u0081a\u00d4\u0004p!\u00d1s\u00b5@\u008b\u0006H\u00e2\u00c5\u00df2\u00cd[\u00c1\u00eejb\u00bd\u00e3\u00bf\u0095;QhM\u000e\u00dd\u009d:\u0081(\u00f4\u001b\u00ca?\u001d\bJ\u0093\u00d5>\u00bd\u001b\u00ce\u009f!\u00cf>-B}i@'j\u00f3-\u00f3\u0016\u00ea\u00d78\u00ad\u00e8\u00c1\u001f\u00f9\u0019";
                            var22_6 = 2808;
                            var19_7 = 64;
                            var18_8 = -1;
lbl20:
                            // 2 sources

                            while (true) {
                                v3 = ++var18_8;
                                v4 = var20_5.substring(v3, v3 + var19_7);
                                v5 = -1;
                                break block20;
                                break;
                            }
lbl25:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = LoadingPanel.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                var20_5 = "\u009366\u00fd0n\u00e4\u0082\u0010\u00e65q\u00fc\u00a8N>\u00aea\"\u0094dC\u00eb\u0011@\u00b4\u00da3W\u00a8\u00d0i\u000f\u00c2/\u00c9\u00e1\u00b7^2\u00c7+D\u00f1\u00f8\u00dd\u00e4\u00db\u00daf{\u00c7\u0082Q,\u00b5p\u009c\u0094\u00ef|**P\u00d3L\u0099f-R\u0014\u009f\u00a6\u00c6\u009f(\u00d9\u009c+\u0000\u00c1\u00f8$B\u00d9}& T\u00c7`\u00fc\u00ac\u00e9\u00c2\u00aa\u00a6\u0085Hm\u00d79\t\u0015\u00af3SW\u0018\fauN\u0090>\u008b\u0092T1\u00bb\u00c3 \u00bf\u00e8\u001b\u00d4\u0099\u00a7L\u00c7F\u0094\u0088n\t\u001e\u009e\u0006=P\"D1y\u00c6_\u00aai\u009a\u008f\u00c8i\u00b0\u00deZ*\u0083y\u00ef\u00d4\u007f\u00e3\u00ff6\u0005Sr\u0081r";
                                var22_6 = 169;
                                var19_7 = 56;
                                var18_8 = -1;
lbl34:
                                // 2 sources

                                while (true) {
                                    v6 = ++var18_8;
                                    v4 = var20_5.substring(v6, v6 + var19_7);
                                    v5 = 0;
                                    break block20;
                                    break;
                                }
                                break;
                            }
lbl39:
                            // 1 sources

                            while (true) {
                                var23_3[var21_4++] = LoadingPanel.zkmDecodeUtf8(var24_9).intern();
                                if ((var18_8 += var19_7) < var22_6) {
                                    var19_7 = var20_5.charAt(var18_8);
                                    ** continue;
                                }
                                break block21;
                                break;
                            }
                        }
                        var24_9 = var16_1.doFinal(v4.getBytes("ISO-8859-1"));
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
                    LoadingPanel.b = var23_3;
                    LoadingPanel.d = new String[43];
                    LoadingPanel.h = new HashMap<K, V>(13);
                    var5_10 = Cipher.getInstance("DES/CBC/NoPadding");
                    v7 = SecretKeyFactory.getInstance("DES");
                    v8 = new byte[8];
                    v9 = v8;
                    v8[0] = (byte)(var25 >>> 56);
                    for (var6_11 = 1; var6_11 < 8; ++var6_11) {
                        v9 = v9;
                        v9[var6_11] = (byte)(var25 << var6_11 * 8 >>> 56);
                    }
                    var5_10.init(2, (Key)v7.generateSecret(new DESKeySpec(v9)), new IvParameterSpec(new byte[8]));
                    var11_12 = new long[44];
                    var8_13 = 0;
                    var9_14 = "\u0003]\n\u0085\u00ccu\"\u00ea\u0096\r\u00bc\u00bf\u00e2\u00bf\u0011\u000f\u00ed`\u00c0*\u00f6}>(\u00bbX\u00d3\u00b2\\\u00ce\u00c4\u0019q\u001f\u0090\u0097\u00ce-\u00f2\u00acr!J{\u00a8\u0015k\u0097\u00fe\u0089\u00e9\\\u00a0\u00e2\u001a\u00e7\u00c6\u00e0\u008f\u00d0\u0093L\u0094\u00c2v\u0014\u00c0\u0098\u00ee\u009f\u00a1Y-\u00fc\u0010a_\u0000N\u00e4\u000b]\u00fb\u00d2\u000f:\u000e\u00a8T\u00cd\u00c8G\u0010TjO*\u0089\u00a54\u00f2\u0089t\u00eb\u00a5vee\u00e6aV\u00c5)\u0019\u00dd\u0081\u00f1\u0085\u000f\u00ac\u009c\u00deV\u0003\u0094\u00ca\u00b5\u00e6Rt#\u0010C\u001e6jf\u00ba\u008a\u00b9\u00bf\f\u00f1:\u00ff)f]\u00b8/)\u00dfT\tv\u00b3\u00a5x\u00cf\u00d1\u00bcs\u007f+\u00cf\u00ca\u0001\u00c0z+B5T\u0086\u00d7\u00ccy\u0081\u00bd\u00e3W\u00e3=F\u00b1O\u00ba\u001d4\u00c3J\u00a77\u00a8\u00fcb\u00fc\u00dcg\u00b6\u0088Z\u0007.k%iJ\u00f3uK8W\u00c1\u00af\u0001\u0002\u00fe\u001e\u00e3\u00af\u000e\u00bdR\u001b\u0080\u00fd\u008c\u001d\u00a0\u00e4%|\u00eb\u0011\u008a\u00b7\u00f4\u00cb\u0017\u00be\u000b\u00a9uV\u00d2\b\u00fe.5\u00de\u00afx\u00cb\u00a5\u00caF\u00cd(\u00fb\u0006\u00fcat\"$\u0000\u00a7\u00e5\u001eo\u009a\u00d0\u0083|\u00e1\u00a4\u00e7\u00dd\u00d5\u0089lQ3\u0093\u0014\u00c1\u00d3bgZ\u0014\u009cC\u00b8 0T\u0083\u0092o#\u00e38|\u0012\u008e\u001f\u0010\u00cb]\u00bb^1P\u00ee\u00e4\u00b6\u0085KXt\u0099\u0001\u00a1\u00a7\u00b1\u008a\u0096k\u00be\b3\u0011T\u00dd";
                    var10_15 = 336;
                    var7_16 = 0;
                    while (true) {
                        var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                        v10 = var11_12;
                        v11 = var8_13++;
                        v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                        v13 = -1;
                        break block22;
                        break;
                    }
lbl78:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        var9_14 = "MvR\u00dd\u000f;\u00b0=\u008f\u00ff\u0017\u00c1r\u00933\u0016";
                        var10_15 = 16;
                        var7_16 = 0;
                        while (true) {
                            var12_17 = var9_14.substring(var7_16, var7_16 += 8).getBytes("ISO-8859-1");
                            v10 = var11_12;
                            v11 = var8_13++;
                            v12 = ((long)var12_17[0] & 255L) << 56 | ((long)var12_17[1] & 255L) << 48 | ((long)var12_17[2] & 255L) << 40 | ((long)var12_17[3] & 255L) << 32 | ((long)var12_17[4] & 255L) << 24 | ((long)var12_17[5] & 255L) << 16 | ((long)var12_17[6] & 255L) << 8 | (long)var12_17[7] & 255L;
                            v13 = 0;
                            break block22;
                            break;
                        }
                        break;
                    }
lbl91:
                    // 1 sources

                    while (true) {
                        v10[v11] = v14;
                        if (var7_16 < var10_15) ** continue;
                        break block23;
                        break;
                    }
                }
                var13_18 = v12;
                var15_19 = var5_10.doFinal(new byte[]{(byte)(var13_18 >>> 56), (byte)(var13_18 >>> 48), (byte)(var13_18 >>> 40), (byte)(var13_18 >>> 32), (byte)(var13_18 >>> 24), (byte)(var13_18 >>> 16), (byte)(var13_18 >>> 8), (byte)var13_18});
                v14 = ((long)var15_19[0] & 255L) << 56 | ((long)var15_19[1] & 255L) << 48 | ((long)var15_19[2] & 255L) << 40 | ((long)var15_19[3] & 255L) << 32 | ((long)var15_19[4] & 255L) << 24 | ((long)var15_19[5] & 255L) << 16 | ((long)var15_19[6] & 255L) << 8 | (long)var15_19[7] & 255L;
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
            LoadingPanel.f = var11_12;
            LoadingPanel.g = new Integer[44];
            LoadingPanel.c = 510;
            LoadingPanel.Q = 300;
            var0_20 = Cipher.getInstance("DES/CBC/NoPadding");
            v15 = SecretKeyFactory.getInstance("DES");
            v16 = new byte[8];
            v17 = v16;
            v16[0] = (byte)(var25 >>> 56);
            for (var1_21 = 1; var1_21 < 8; ++var1_21) {
                v17 = v17;
                v17[var1_21] = (byte)(var25 << var1_21 * 8 >>> 56);
            }
            break block24;
lbl120:
            // 1 sources

            while (true) {
                continue;
                break;
            }
        }
        var0_20.init(2, (Key)v15.generateSecret(new DESKeySpec(v17)), new IvParameterSpec(new byte[8]));
        var4_22 = var0_20.doFinal(new byte[]{-117, 110, -46, -99, 56, 17, -93, -57});
        ** while (true)
        LoadingPanel.i = ((long)var4_22[0] & 255L) << 56 | ((long)var4_22[1] & 255L) << 48 | ((long)var4_22[2] & 255L) << 40 | ((long)var4_22[3] & 255L) << 32 | ((long)var4_22[4] & 255L) << 24 | ((long)var4_22[5] & 255L) << 16 | ((long)var4_22[6] & 255L) << 8 | (long)var4_22[7] & 255L;
        LoadingPanel.INSTANCE = new LoadingPanel();
        v18 = FabricLoader.getInstance().getModContainer("catlean").orElseThrow();
        Intrinsics.checkNotNullExpressionValue(v18, "orElseThrow(...)");
        LoadingPanel.modContainer = (ModContainer)v18;
        LoadingPanel.INSTANCE.setPreferredSize(new Dimension(510, 300));
        LoadingPanel.INSTANCE.setBackground(new Color(10, 10, 10));
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x3B2B;
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
                throw new RuntimeException("su/catlean/LoadingPanel", exception);
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
            LoadingPanel.d[n2] = LoadingPanel.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return d[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = LoadingPanel.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/LoadingPanel" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    private static int zkmDecryptInt(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x5277;
        if (g[n2] == null) {
            byte[] byArray;
            byte[] byArray2 = new byte[]{(byte)(l >>> 56), (byte)(l >>> 48), (byte)(l >>> 40), (byte)(l >>> 32), (byte)(l >>> 24), (byte)(l >>> 16), (byte)(l >>> 8), (byte)l};
            long l2 = f[n2];
            byte[] byArray3 = new byte[]{(byte)(l2 >>> 56), (byte)(l2 >>> 48), (byte)(l2 >>> 40), (byte)(l2 >>> 32), (byte)(l2 >>> 24), (byte)(l2 >>> 16), (byte)(l2 >>> 8), (byte)l2};
            Long l3 = Thread.currentThread().threadId();
            Object[] objectArray = (Object[])h.get(l3);
            try {
                if (objectArray == null) {
                    objectArray = new Object[]{Cipher.getInstance("DES/CBC/NoPadding"), SecretKeyFactory.getInstance("DES"), new IvParameterSpec(new byte[8])};
                    h.put(l3, objectArray);
                }
                DESKeySpec dESKeySpec = new DESKeySpec(byArray2);
                SecretKey secretKey = ((SecretKeyFactory)objectArray[1]).generateSecret(dESKeySpec);
                Cipher cipher = (Cipher)objectArray[0];
                cipher.init(2, (Key)secretKey, (IvParameterSpec)objectArray[2]);
                byArray = cipher.doFinal(byArray3);
            }
            catch (Exception exception) {
                throw new RuntimeException("su/catlean/LoadingPanel", exception);
            }
            int n3 = (byArray[4] & 0xFF) << 24 | (byArray[5] & 0xFF) << 16 | (byArray[6] & 0xFF) << 8 | byArray[7] & 0xFF;
            LoadingPanel.g[n2] = n3;
        }
        return g[n2];
    }

    private static int b(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        int n2 = LoadingPanel.zkmDecryptInt(n, l);
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
            throw new RuntimeException("su/catlean/LoadingPanel" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(LoadingPanel.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
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
            return MethodHandles.lookup().findStatic(LoadingPanel.class, "b", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)I", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

