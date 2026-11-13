/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.util;

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
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import org.jetbrains.annotations.NotNull;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class KeyNames {
    @NotNull
    public static final KeyNames INSTANCE;
    @NotNull
    private static final Map V;
    private static Module[] y;
    private static final long a;
    private static final String[] b;
    private static final String[] c;
    private static final Map d;

    private KeyNames() {
    }

    @NotNull
    public final String getKeyName(int n) {
        String string = (String)V.get(n);
        if (string == null) {
            string = "None";
        }
        return string;
    }

    /*
     * Unable to fully structure code
     */
    static {
        block21: {
            block20: {
                block19: {
                    block18: {
                        KeyNames.a = ZkmKeys.a(-5597110487309279058L, -2503131986749033564L, MethodHandles.lookup().lookupClass()).a(277917742163372L);
                        var20 = KeyNames.a ^ 66330635280730L;
                        KeyNames.d = new HashMap<K, V>(13);
                        KeyNames.zkmSetFlag(null);
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
                        var15_5 = "8\u0096\u0084\u00d6iw\u009d\rVC\u00bc\u00a4\u00ee\u00fbe\u0088\u0010s@k\u0092\u00a7\u00ca\u00db5\u0095\u00a99\u00b26\u00e6\u00a01 \u0098\u0095\u0090*j%Ch\u00f0\u0080\u000b=\u00b2_\u0090\u009es\u0084\u00c5z\u001b\u0095Qw\b\n\u00fd\u00e5\u00bc\u00e1\u00b8a\u00106\u00bc\u0001\u00c7\u001cX\u00c2p7u\u00854aE\u00a0#\u0010\r\u00c1\u00f4\u00dc\u0082o\u00e3)\u00ca#\u00ae\u00a1\u00da\u0092\u00fb\u00d6\u0010\u001d\u00be\u00deB\u0094\u0094\u00dd\u00cb\u00a82\u0091WT\u009e\u0016\u00f5\u0010W\u00f4c\u00d3Lega)\u0099udRF\u00f5\u00cb\u0010\u00b2\u0010\u00d6\u00e7K'-\u00fd\u00b2\u0086\u0093\u0093\u009536 \u0010Br\u00ff\u0003'\u00ce\u00fd\u0090*XN\u00f8\u00ec\u000e\u00d3\u00b1 dU\u0013\u0003\u00ba\u00e0A \u00adj\u00ca\u0081ZJ&sCW\u00c3i\u00a7\u0097\u0000*\u0015-\u00c4\u0018\bZ(<\u0010\u008c\u008bf\u00f8\u00f8\u00ee\u000f\u00c0f%\u00df\u0082O%@\u0092\u0010o5\u00a6!\b\u00db\u0098 \u00d6\u00fb\u0014556\u00f0#\u0010\u00f1\u00b0\u00a7\u00ff\u00caO.:\u00a7\u001b\u00b52.\u00c9jI\u0010\u00de\u00f6\u00d8**\u00d15i\u008f\u00a1mq\u00d9\u0081+\u00fe\u0010y\u0005}\u00ff+\u0000\u00a1\u00a6\u007f\u00aa\u009fh\u00dfQ\u008d\u00ee\u0010x\u00b2uz\u00efnax\u00feoQ\u00a5\u00d5N\u00d70\u0010\u001c\u00fe:TL\u009a\u0003\u00a0W\u00b9\u00d2\u00eat{\u0096\u0016\u0010d\nC||\u00db\u00fc\u00a1\u0004\u00db\u00a5\u00fd\u0080\u0010H\u00ff\u00104\u00d5\u00d7\u00e1q\u00fc\u00d5\u00ec+h_\u0014\u00d5\u0010q\u00ef\u0010P\u00a6^\u00dd\u00c8O6\u00ea\u00f7k|\u00f0\u00fb&\u0099\u0013\u00105t\u009e\u0099\u00d9\u009cs\u008ds\u00d5\u00ad\u00cc\u0086\u00ceo\u0019\u0010\u00c0,G\u0018\u0015\u0007\u0097\u00a2z-\u0005\u0016\u00b7\u00d4!\u00f5\u0010\u00f5u\u00a7\u00d1\u0084\u0081\u00c0\u00ce\u00e48g\t.\u0088\u00dd\u0090\u0010y\u00cb\u0011\u00f1\u00d9mH\u00ff\u00ef\u0093\u00dc\u000b\u00d3\u00da\u00bc\u00ea\u0010\u00b4\u00b6_\u00beFKu\u00d7\u00f4:\u00d7\u00ab\u00c2\u0096\u00ecj\u0010C6\u00a7\u00ee\u000b\u001c\u00b1\u00fc\u00ad\u00ee\u0099=3?\u0012(\u0018\u0081\u00d3\u009e\u00b5\u00beG\f\u00f84\u009a\u001f\u00e7\u00f8\u00ff;Laq\u00e4f\u00ae\u009afQ\u0010\u009e\u00e54\u00ed\u0014@\u00cc<J\u00bd\u00c8\u0081\u00ac\u0002`\u00a0\u0010V\u000b\u00d4Va\u0084\u008b\u00c8\u00b1\u00f4\u0006\u008b\u00d4S\u00b7\u00b4\u0010\u0080E\u0003\u00ca\u00ff\u0018$\u00e7 \u0095\u00a6+h5\u0012\u0081\u0010Q!\u0087\u00c6,C\u008f\u000b\u00c7[\u00cd@\u00ac\u009f\\\u00a4\u0010\u008d\u0007\u00a2\u0082y\u00d9\u0018f\u00cc\u00ec\u00b7\u00f7\r\u0083G\u000b\u0010\u008b\f0}\u008f\u00e5\u00f5%M\u0006y\u0091\u00f87O\u0000\u0010\u00ab\u0010\u009c5\u0085\u00b8\u0085\u00fa\u00ac\u00df\u00e7\u007f\u00f3{\u0010.\u0010\u0000\u001b=\u00bc\u00d9\u0094\u001a'x\u008a\u00a1\u008b5\u00e5\u00de\u008a\u0010\u00b5|\u0000&:\u00b5i\u0093\u00b9apC\t9\u0000\u00d6\u0010\u00b5M]\u00ea\u00d8\u0014\u0098\u00fb\u0010\u00cc\u00f09\n\u0091G\u0002\u0010\u00e3\u0090\u000b\u00c6~\u00bd\u0006\u0004\u0003\f\u00cb\u00e7\u00cav][\u0010\\\u00f2\u0090\u00d9?\u00d8e\u00d3\u0011 \u00b1/YS\u0005\u00d2\u0010\u0098\u0081\u00fda%\u00bb\u00ab\u009d\u00b9\u00858?\u00b41\u0085\u00c4\u0010\u008d\u00d3\u00c0\u00eaLx\f*\u0084>\u0000\u00fd\u0086|\u00bd{\u0010\u00ae\u00e7N\u00fb\u00c98\u0099\u0001-\u009c\u0080\u00d5\u00e3\u0080A\b\u0010\u008a\u00f3 \u00db74\u00ec\u00f3\u001d\u00b1\u001e\u00c3oO\u00c3\u00e0\u0010\u00d6/OHV\u00aeYwf\u0092\u0095P\u00f0\u00c36\u00cf\u0010N\"%\u0088\u00a0\u00fd\u00aa:\u00c3\u0080\u00e0\u0080\u00aak\u0016o\u0010\u00f1\u00f1\u00ac\u00c3\u00d6T\u0017\u0084\u00f5\u009f\u00dd\u00b3\u00b0\u00bc\u0000\u00ef\u00102\u00c3\u00c4\u001f,S+t\u0019X\u0094]\u00a3:\u0099\u0007\u0010!\u00a9:\u00d4W\u001e\u00d1\u00ba\u00dd\u00eb\u00ed\u0094\u00ef\u0088\u001ex\u0010\r\u00be\u0000\u000f\u0015\u0005\u0010\u00ef\u009e\u0096\u00d3KO\u0005b\u00d9\u0010\u00d7w\u009dj\u0090\u00c7\u0012\u00a76\u007f<\u00f1c\u0085\u00d5=\u0010ZO\u00d7\u00d2FB!\u0091i\u0011+g\u00e8\u008e\u00ac\u00ca\u0010L6\u009b\u00faP\u0084K\u00f3\u00d2l\u00d9\u00d6\u0006\u00fbPe\u0010P\u00ef\u0081\u00a5\u0003\u00dc\u00a2+\u0080\u009aS\u00e3\u00f0a\\\u0000 \u00e0\u000e\u00e2\u00d4\rz\u00ab\u00e2\u0012\u009d\u0019\u0016\u0010{\u00ec\u009d\u00a5\u00c7\u00bf\u00ea\u00b7Y\u00e4\u00be\u0015\u00f2G\u00b7\u00f1\u0018\u00a7\u00da\u0010h\u00e1\u0001\u0090CGp\\<\u0018\u00ba\u00f1\u00e2\u00aa\u0004\u007f\u00103\r\u00c2\u00a3\u00cc\u00beY\u00a2\u00d8*h\u00af\u00bb\u00be\u0000\u00d0\u0010\u00d0e=%\u00fb4\u00d6\u00e5o\u00cbj\u009a\u00a9\u00a4\u0084\u00d9\u0010\u00ed\u00daa\u00c6x\u00851@\u00efp?\u00b8\u00fc\u00ee\u00a0\u001a\u0010Wc\u00a0\t\u0005up\u009fM\u00e9c\u009d&\u0084\u0007\u00dc\u0018'8c=\u00c3\u009c\u00a3LC\u00e9pP\u0090\u00b7\u00a02\u00a3n\u00ea\u000eh\u00df\u008e\u009c \u00ab}\u00cd\u00b6\u00a2L[\u00ffn\u00a2\u00f3\u0093\u00c6tpV\u007f\u0004=\u00e19\u00fc\u00ae\u00dd\u00b1\u0088\u0014N\u00e0\u00ebH\\\u00101?h\u0016\u00fa\u00cb\u00b4\u00fd\u00a1\u0015{)\u00f6\u00b9;\u0018\u0010\u009b\u00aa\u00f4;\u00df\u00f7\u000e}[\u008cF\u00aaw\u0098\u00b8\f\u0010\u00edW\u00c3w\u00feV\u0096\u009f\u00cfw|\u00e6b\u0019\u00ff\u00d0\u0018u\u00b0\u00fa!>\u00f7Qrlmy\u00a0\u0088\u00bc\u00d7|f\u00c8\u00b2\u00bb\u0014\u0092\u00a44\u0010\u00f8g\u009f\u00dc\u000fh\u00d8p\u0090\u00fd\u00ad&\u0090\u00c1\u000b\u008f\u0010\u0086\u0095\u00b1\u00eb\u00a7\u0092\u00b3\u00d5;\u00ff%7\u0098y\u00ef\u008c\u0010\u00daA\u00de\u00e8\u00a3\u00f1\u000b<\u0094\u00de\u00c0'\u00e3\u00e6\u0010C\u0010\u008fQ\u00cc\"}y\u00fa\u00d6-\u00b8\u00df*\u001a\r\u00cb\u00d2\u0010\u00a6\u00eb,\u0094\u00b3 \u00c1Y\u00a2G\u0080\u00e0\u0018\u00f6\u0085\u0003 \u00ce\u00b7A!\u00d3\u0092\u00b6\u0097\u00ba\u0081r\u00f0^2\u00b9\u00c1\u00f8\u00d65}|<c\u00af4\u00ce\u0018\u00d9\u00a9h\u00ae\u0005\u0010\u00a7Xp\u00ba\u00e7\u00e2\u00e6\u00e0\f\u0002m \u00c4\u0019\u0099\u00f0\u0010\u00c9\u00b5\u00a9U\u00f2\u008b\u00ad\u008e\u0087\u00d9\u00cb\u0016\u00ce\u0011\u001dx \u00f1C.\u00a0\u0086P\u00ab_\u001c\u00f5i\u0001e\u0085\u00c7\u00d0\u00ad\u00dd\u0007\t\u00db\u00f4\u0015O\u008bi\u001e.l\u00fd\u0001\u0098\u0010\u0092\u00ad\u00e5\u00f1\u00f7\u00ac\u0083\u0017\u0014\u00af \u00bf\u00c5u\u00a8\u0014\u0010EuD\u00c56\u00c89\u00ff\u00be\u008a\u00ae%/.\u0081\u00e6\u0010\u009a\u008d\u00ebS\u00cf\u00bdB\u0012\u0081\u00d4\u00c9\u0088)\u00d1\u00d1\u00da\u0010\u0014\u00c2q\u00c6y!\u00de\u00e2\u00c4u/D\u00c1\u00a3\u00bcU\u0010>\u00f7{\u00fcx\u0088LC2\u00fel{\u00f0\u00ca\u0080)\u0010\u000fx,\u00a1\u00d3H\u00a0\u00f9\u00c1\u00e3d\u0086\u00a2\u00b1\u00fe\u00eb \u00af\u00bb\u00f8M\u00b9d'\u001c\u009f\u00e4%\u0089l\u00c3yS\u0092\u0081\u008a{\u009e\u00c4\u0087\u008c6\u00edv\u0081v|\u00c5\u0002\u0010W\u008d\u0015\u00f5\u00e4\u0000i\u0090n\u00e1\u00fd~\u0090:\u00c3I\u0010<\u00b8\u001f\u00d9-\u00b2\u00883\u00dfOC\u000f\u00fe\u00f48\r\u0010^7\u0095G\u008fCu\\\u0014\u00b6\r\u00c6\u0096\u0083\u0002\u0095\u0010\u00e32;\u00c2D\u0099\u00de!\u0014'\u009aM\u00ec\u00e3\u0082x\u0010.\u0004\u00ee5V&*~\u00b7\u00f4\u00fb\u001e~<\u001b\u007f";
                        var17_6 = 1597;
                        var14_7 = 16;
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
                            var18_3[var16_4++] = KeyNames.zkmDecodeUtf8(var19_9).intern();
                            if ((var13_8 += var14_7) < var17_6) {
                                var14_7 = var15_5.charAt(var13_8);
                                ** continue;
                            }
                            var15_5 = "\u00ad\u009cy?\u0014\u00a5i\u00ba\u00a3\u00b5\u00e0\u00dd\u00f6\u00f54q\u0010\u00aa\u00f3\u00f1+\u00fd\u00ba\u00ed\nYI\u00f4;\u00e1\u0011\u009bE";
                            var17_6 = 33;
                            var14_7 = 16;
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
                            var18_3[var16_4++] = KeyNames.zkmDecodeUtf8(var19_9).intern();
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
                KeyNames.b = var18_3;
                KeyNames.c = new String[88];
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
                var0_12 = new long[236];
                var4_13 = 0;
                var5_14 = "\u00a3\u00a5\u0081\u00f9\u008aV\u001b\u00e9\u0086\u0088\u00a0\u0081\u00ea\u0084\f|\u001b\u00a3\u00a3\u00ca1$\u00ed\u00b2\u00eb:\u00d3\u00e8<\u0003oT\u00b8@\n\u0086x\u00a8\u00c4\u009f\u00b7\u00fd?\u0093L\u0093\u00bf\u00d32\u00dd\u00e3\u00a6\u00a7\u009eu*\u000f\u00e8F\u00b4M\u001fv\u009b\u00df\u0002\u0093\u00e4j\u0081\u00e1\u008a\u008f\u0097\u008f\u00e8\u00f2x\u00f0\u00c3r\u0082b%\bXJa\u0018\u00e0\u0013=1\u009c\u0082\\z\u00dd\u0099\u0097\u00b3\u00b2\u001b\u00b5\u00e8 \u00ec+\u0090\u008b\u00e0:\b\u00d5F\u0089\"\u0080~\u00a5\u00c4\r\u00ee\u0018\u00ddZ\u00c5W\u00ec\u0017R\u00d9\u00e4\u0099R\u0015\u0011\u00abyc\u00b3+{\u00dc\u00c0\u00b2\u0000\f <\u00e6\u0091\"\u00e4\u00c5Z\u0019o\u00a9\u00f5)c|\t>\u0000\u00cc\u00e2\u0090\u0013A\tTi\u0092P\u00d7\u00d4\"A\u00ae\u00d8?2\u00d6Pq\"\u00ea\u00fav2+(\u00db\u00f8w\u000b3%`w\u00d8|};\u001d\u00b1\u00910\n\u00d0\u00c1\u000b\u0082\u00ee#$\u0092r\u0099~\u00b9\u00c8\u00e43\u00adq\u00ee\u00f0\u00e4\u00ec\u0014n\u0085\u00f8\"o\u00a5\u00e0zci\u00b3i1\u00eb\u009f\f\u0098\u00b4\u0017Zu\u008b\u00e0z\u0092,\u0092l\u00ba\u0088\u00dcy\u0017y%\u00b6\u0010I\u00b2\u0085p\u008a\u00cbUU\u008b\u009f\u009e\u00e6\u00e4\u00f5z\u00d7l\u001c\u00be\u0019\u00db\u0010Y\u00f5?\u001c1}Y\u0089\u00b2\u0093O\u009dl0\u00b4'H!ZSZEh\u00a2\u00a5\u00c4\u00f6_On\nl\u0082\u00b4\u0083\u0088\u00fd\u00b2\u00e5\u00ca\u00f4F[\u00b2\u00bb\u0017\u0096?\u00f6E\u00ff\u00bf\u0081\u008c#\u00f7\u00ec4f>)!\u000e\u00fdv3\u00ecf\u009en\u0000\u00c1>\u00e1O\u00ec\u00f5i\u0084\u0003\u00dc`ul\u00cc+\u00f6\u00f7l\u00f4(1\u00d9\u00e5\u00ec\b\u00a5\u00bc\u00f1\u00ee\u00ce\u0096GhEbi\u00c0\u0017n\u00e4\u00bc\u00d1;\u00b5\u00b7\r\u008b\u00d1\u0003#\u00d4\u000fD\u00a85y \u00df/&\u009b\u00e3\u00101\u00b5\u00f1E\u00da![\u00fcJ\u0007\u008b\u009d\u00f7RAL\u00d9\u00f8\u00e4\u0002\u00c0\u001b\u00e8/\u0099t\u0082\u00c4=\u008a\u00d2\u00a6\u00b9\u0007,\u0012\u00a2I\u00f1\u00ed\u0011\u00b0v\u0085\u0007\u008b\u000f\u00cd8U\u00f6\u00ff\u0016L\u00c2+\u00b6\u0098\u00c7\u009f&\u00c4\u00b0p\u00d5KwX$\u009e\u00967\u00adn:\u007f\u0004d-\u00f7\u0091\u0018\u00f1\u00b1\u00c4\u0006\u00d1i\u00fc}TrX8\u00c6\u00e7\u00ef2\u00b0O\u00c1W\u00b0\u00af\u0003\u00d26i\u00f0\u009b\u00bc\u00d6R\u00c1\u0011\u00ff\u0017\u00f8+\u00b4\u00a3\u0003pw\u00a6/\u0081]\u001a3\u00e1\u00cb\"\u00fa\u00b2\n\u00ea\\\u00f8\u00b6\u00dc\u00f9\u0004\u001eGr|\u0099\u00ce\u0083\u0096W\u00e6\u0098\u00e0w\u00daR\u001f\u00e3\u0098\u00c3\u00d3\u00937\u0012\u0017\u00f3\"V\u001a\u0005Yh\u007fqO\u007f\u00fc\u00071\u001ePO\u00abL3\u0003\u00ec\u009ci\u0006\u009d\u00d1\u008d\u0002\u0080\u0091q\u0013j+\u00b6\u00959\u00ad\u00c1\u00b2\u00c2V\u0096\u00e4\u00fc\u0095\u00b1\u0085CI?\u009f\u0004F\r'/=\u00dcVh9\u000bh+\u00fb\u001a\u001f\u00bb\u00e8\u00a6`\"\u008f\u008a\u000eo\u00cd\u009c\u00a7\u001a\u00b9\u00fb\u00cc>\u00cd\u0000\u0006zx\u00fd\u00112W01\u00f11\u00d1vT\u00cf\u0010\u00a7\u0094\u0088\u0080\u00b1\u00d4\u0094\u000f\u00d7m\u00c0\u009b\u00d0\u00a6\u008d!\u0004\u008b\u001a\u0097V@(\u00a8{\u00f8\b\u00c9\u00fc\u0093\u00149\u0012\u00c4\u00bf%\u00d1P\u00d7zx\u001b\u008cO\u00ab#\u0013\u00f7Pq\u001eC\u00f2\u0001\u00c1x_\u00d7xz\u00e2\u00b9~\u0080-#\u00eb\u0080\u00ce\u009b\u0094\u008b@\u00ed\u00fct\u00caac\u00d2\u00c8\u00c4\u00c6|\u0006d\u00f72\rk\u0094:v\u00feVR@\u00b8d\u0086\u0082l\u00a8>\u00ae\u0090\u00a6\u008e\u009c\u0086\u009cn\t\u0092\u00ef\u0087\u0016\u00d0\u0012\u001f}\u0084\u00d6l\b\u00eb \u00cb\u00d6\u00b2\u0097\u00ec\u00a80\u00c2\u00d2\u00c7\u00ed\u008d6\u0082Je\u009e\u00b5\u00e3\u009d\u00cby\u0018f\u000f\u00ae\u00ca\u00c5\u0001*\u0082C\u0084\u008b\u00ca\u007f\u001a\u00ae\u008a^\u00bb\u00b0\u00ed\u00dcZ\u00d0\u00a9\u00fa\u00aa\u00d0\u00f5\u00e2\u0015\u00e5\u00fe@\u001c\u00f8zu\u00f8\u00a4\u00d6\u00ae(\u009c\u007f\u00c7\u00f8\u001cF\u00e5*\u001a\u00131\u009a\u00ab6~:\u00fc!\u00fc8f\u00feg\u009b\u00ae!S\u00f8:\u00b3\u008b\u00ae\u00adz\u00d8\u00d4\u0015\u001d\u001f\u00c5_\u00e8\u00dd\u00bb\u00bc\u00aeH\u0086\u008f\u0018\u00be\u00f6\u00e1\u00c8zT\u00b7\u0093\u008c\u0098R\u00a8\u00f2a\u008037H\u00a6\u0006\u00c05\u0012\u00dci_\u0085\u0084n\u00c5\u00b9]\u00ff\u007f\u009ak\u00aeS\u00da\u00cb\u00f3\u00e0\u0010V\u00ae\"\u00b5H<\u0014\u00f1\u00c6OT\u00c0\u00a9\u00e0\u00ba^\u00e8\u0007\u00f0\u0005\u00a87`]J*\u00f5t\u0004L\u00af\u00ce\u009f\u00ac\u0013\u00ff0\"\u00a2\u0087X\u0010\n\u00fc@\u00e2\u00b2\u0099\u001a\u009d\u00e3j\u0099\b|\u00ff\u00b4p\u00be_\u00c7\u008dVd\u0092\u00d8\u00d9\u00cd\u0000\u00c5\u00925\u00f6\nV'O\u00e6\u0093\u00d3Iu\u0001J_;z\u00a0)\u00d3L\u00e9\u00c4\u00ac\u00ec'_r\u00e6\u00f8\u0098D\u0014\u00a8\u00a4\u00d5*-\u00d8\u001dY\u0093\u00a1\u0016&\u00ff\u00d3\u00ca\u00a2\u000e\u00e8\u000b\u00d7\u001bc\u0082! $\u008a\u00f1-\u00d0\u001d\u00d0dWeA\u008aUH\u009b\u00a7\u00c4\u0096\u009e\u00df\u00d0\u0089,b\u0005/\u00b9\u00ae\u00ea\u00c1\u00a9\u00b3\u0005O\u0014-8O\u009f\u00bdZ\u0087\u00e4\u00ac\u008a\u00d6\u0014\u00e8\u00a0\u00cc\u00f1I\u001aj\u00b0\u0081J\u00e9\u001e\u0093L6My\u00ad\u00ff\u00ea\u0098\u0099\u00b2{\u00d0\u00fb\u00d6r\u00d6\u0090\u0091j}B\u008cs\u00f2\u008e\u0089\u0094/Yc8\u00e2\u009b\u00f7\u0010%I\u00f3\u008f\u000e\u00f5\u00c9\u008a8z\u0085\u00a4\u008a\u0080R\u00eaT_\u0018)\u0092\u0089!\u0091:\u00814|\u00d7\u00d0\u00ce\u00f0@\u008d\u00e1\u0099\u0088\u008f\u000bz\u00d2%K\u0092\u00a3\u0012\u00d3\u00da\u0084\u00ca\u00bfvN\u0000\u008fL\u00dc\u0012\u00ab.>\u0010\u0086\u009a\u00d9\u00dff\u0090qF\u00a8\u00c2\u00efg\u00c3\n\u00fa\u00b7%z\u0094b\u009f\u0010\u00ab\u00ded\u00f3p\u00d2v\u008b\b\u000f\u00bbq\u00d9?\u00e1\u00ab\u00a3\u00b8\u00f5k\u0010r\u007f?\u001d\u0096n<r\u0098\u0006}m\u0000uK#\u00cb']lU\u00b3\u0016\u00a0M\u00e9\u008b65^\u00a8\u00ac\u009e\u00dc\u00afab?$\u00df[&\u00e5\u00a2\u00beb^E\u00e5\u008c\u00bcT\u0002N\u00800\u00d8\u0016\u00f1 \u0089\u00dd\u0097^&a\"\f\u00df\u00f8BwJ\u00f4R\u00f9\u000b\u00c4\u00cd/\u0098\u00b4\u00cf\u00f6\u00a3\u008d\u001f\u0014\u00196\u00ad\u0086YC\u001cW\u00cc\f\u0099\u000e\u0012\u00ca1?{A\u00d3\u00b4\u0005\u0016p9\u00af\u0090\u0094t\u008e-\n\u00b5\u0019\u00a7\u0099\u00fc\u00ec\u0084w\u00ea\u00a7\u0087\u009bN'J\u0018V\u00ad\u0007\u00a7n\u00b6*\u00af\t'\u000f\t\u00b2\u0012\u008d\u00a9a:\u00fbV\u00dfP\u001c\u00f1\u00bc\u00c6\u001b\u000b}x\u00a8\u00da<\u00ba~\u00c9H\u00e9\u00e5&wn\u00f8\u0096\u0096\u00cdU\u00f9\u0012\u00ca\u00906\u00dflkt\u0097\u00bf\u0088o\u009e\u00ac\u00cd\u001b\"\u0087\u00db\u00e6v\u00c9\u008e\u00a8,\u0012{\u00bcT_\u00caW\u00ac<\u008a\u0089\u00fa\u00d9\u009a\u00f0q\u00be\u00f5\u00ed\u0083&\u0082\b\u00d4\u00ff\u009d\u00f3\u00ae\u0000\u0095\u0096\u0017\u00ceP\r\u0014\u00f4\u00da\u0083#p^\u000b\u00ca\u00899\u00ee\u009b.tZ\u001f\u00fd\u0019)\u00efb\u00e67}\u0015\u00d1\u00c5\u00a4X\u00f7\u0002\u0006U0O\u00daj\u00c0\u0091\u00b7\u009c\u00f5\u0087\u0018 \u00b0\u001c\u00f2\u00e4\u0086\u00c8\u00fd\u00bfj\u00f0\u00fbqo\u008d\u00fa4Q\u00b6\u00ea\u0083\u00d4b\u00f9\b\u00c5Kx\u00c9-\u008f\u00e0\u009ez\u00d4\u00b2\u00a3FAb+\u0001\u00c2\u009f\u00eb\u00ca]o^yV*\u00d9\u001e+8\u0010n\u0096^\u00ab>\u00875.\u00a9\u009c=\u0097\n\u00a9\u00eb\u00f9}\u00b5\u00ac\u0089Y\\$\u009f\u00da\u00cbcN\u0084\u0081\u00b2\u00ab\u00b12\u009cUq\u00a5+\f\u00b8\u0004#7\u009bN9\u00e9\u0080K\u00cc\u0002\u0095\u00dc\u00a4s-Xv+\u0094\u009aN={\u00d9\u00d8\u00c8\u008fr\u00b5\u00f73\u00fa\u0004\u0001\u00bc\u00b6j\u008aB[\u00d1&\u00b1G\u00a36\u00d7\u00a7WL8q\u00feI\u000eb\u00ab\u00ea\u00b3G5\u00f0F\u0081@qU\u00c0b\u0081\u00a7\u0093~\u00d1\u00ee\u00c2YR\t\u00f4Kk\u0018\u00d6\u00e1\u00c4\u0012\u00ef\u00ce\u009a\u00e8(~p\u00a1*\u00cawn\u00a1\u0013\u00b8~\u00ff\u00e2\u0019\u00aaz\u009f\u00ac\u00ad\u00ba\u0017\u00fb\u00f6\u00c3\u00e7\u0088\u0012\u0011\u00c0\u001a)\u00ec\r\u00f0I\u001cb6\u00cb\u00bet\u000e#,W\u00fe\u00a9\u0098\u008f\u0089kH\u00ff5\u009d\u00c3\u00eb\f\u00a0\u00b5v\u00c9h1\u009ef\u00a9~\u0092\u0012\u0019\u00ee\u0085\u0014\u00a2\u00ec\u00c1\u00b4\u001d#K\u00dc)";
                var6_15 = 1872;
                var3_16 = 0;
                while (true) {
                    var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                    v10 = var0_12;
                    v11 = var4_13++;
                    v12 = ((long)var7_17[0] & 255L) << 56 | ((long)var7_17[1] & 255L) << 48 | ((long)var7_17[2] & 255L) << 40 | ((long)var7_17[3] & 255L) << 32 | ((long)var7_17[4] & 255L) << 24 | ((long)var7_17[5] & 255L) << 16 | ((long)var7_17[6] & 255L) << 8 | (long)var7_17[7] & 255L;
                    v13 = -1;
                    break block20;
                    break;
                }
lbl78:
                // 1 sources

                while (true) {
                    v10[v11] = v14;
                    if (var3_16 < var6_15) ** continue;
                    var5_14 = "\u0096 \u00c5\u00ff\u00fb9/\u00e5\u0007\u00d8f\u00b5M\u009a\u00f1[";
                    var6_15 = 16;
                    var3_16 = 0;
                    while (true) {
                        var7_17 = var5_14.substring(var3_16, var3_16 += 8).getBytes("ISO-8859-1");
                        v10 = var0_12;
                        v11 = var4_13++;
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
                    if (var3_16 < var6_15) ** continue;
                    break block21;
                    break;
                }
            }
            var8_18 = v12;
            var10_19 = var1_10.doFinal(new byte[]{(byte)(var8_18 >>> 56), (byte)(var8_18 >>> 48), (byte)(var8_18 >>> 40), (byte)(var8_18 >>> 32), (byte)(var8_18 >>> 24), (byte)(var8_18 >>> 16), (byte)(var8_18 >>> 8), (byte)var8_18});
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
        KeyNames.INSTANCE = new KeyNames();
        var22_20 = new Pair[(int)var0_12[91]];
        var22_20[0] = TuplesKt.to(0, "LMB");
        var22_20[1] = TuplesKt.to(1, "RMB");
        var22_20[2] = TuplesKt.to(2, "MMB");
        var22_20[3] = TuplesKt.to(3, "Mouse 4");
        var22_20[4] = TuplesKt.to(4, "Mouse 5");
        var22_20[5] = TuplesKt.to((int)var0_12[8], "Space");
        var22_20[(int)var0_12[192]] = TuplesKt.to((int)var0_12[176], "Apostrophe");
        var22_20[(int)var0_12[54]] = TuplesKt.to((int)var0_12[171], "Comma");
        var22_20[(int)var0_12[104]] = TuplesKt.to((int)var0_12[205], "Minus");
        var22_20[(int)var0_12[121]] = TuplesKt.to((int)var0_12[124], "Period");
        var22_20[(int)var0_12[30]] = TuplesKt.to((int)var0_12[199], "Slash");
        var22_20[(int)var0_12[223]] = TuplesKt.to((int)var0_12[119], "0");
        var22_20[(int)var0_12[202]] = TuplesKt.to((int)var0_12[59], "1");
        var22_20[(int)var0_12[139]] = TuplesKt.to((int)var0_12[122], "2");
        var22_20[(int)var0_12[13]] = TuplesKt.to((int)var0_12[136], "3");
        var22_20[(int)var0_12[184]] = TuplesKt.to((int)var0_12[60], "4");
        var22_20[(int)var0_12[215]] = TuplesKt.to((int)var0_12[151], "5");
        var22_20[(int)var0_12[88]] = TuplesKt.to((int)var0_12[129], "6");
        var22_20[(int)var0_12[38]] = TuplesKt.to((int)var0_12[161], "7");
        var22_20[(int)var0_12[231]] = TuplesKt.to((int)var0_12[159], "8");
        var22_20[(int)var0_12[172]] = TuplesKt.to((int)var0_12[56], "9");
        var22_20[(int)var0_12[42]] = TuplesKt.to((int)var0_12[45], "SemiColon");
        var22_20[(int)var0_12[40]] = TuplesKt.to((int)var0_12[97], "Equal");
        var22_20[(int)var0_12[10]] = TuplesKt.to((int)var0_12[105], "A");
        var22_20[(int)var0_12[158]] = TuplesKt.to((int)var0_12[165], "B");
        var22_20[(int)var0_12[19]] = TuplesKt.to((int)var0_12[72], "C");
        var22_20[(int)var0_12[73]] = TuplesKt.to((int)var0_12[169], "D");
        var22_20[(int)var0_12[228]] = TuplesKt.to((int)var0_12[106], "E");
        var22_20[(int)var0_12[75]] = TuplesKt.to((int)var0_12[235], "F");
        var22_20[(int)var0_12[109]] = TuplesKt.to((int)var0_12[11], "G");
        var22_20[(int)var0_12[47]] = TuplesKt.to((int)var0_12[189], "H");
        var22_20[(int)var0_12[2]] = TuplesKt.to((int)var0_12[26], "I");
        var22_20[(int)var0_12[82]] = TuplesKt.to((int)var0_12[210], "J");
        var22_20[(int)var0_12[218]] = TuplesKt.to((int)var0_12[77], "K");
        var22_20[(int)var0_12[144]] = TuplesKt.to((int)var0_12[118], "L");
        var22_20[(int)var0_12[145]] = TuplesKt.to((int)var0_12[137], "M");
        var22_20[(int)var0_12[170]] = TuplesKt.to((int)var0_12[224], "N");
        var22_20[(int)var0_12[160]] = TuplesKt.to((int)var0_12[43], "O");
        var22_20[(int)var0_12[46]] = TuplesKt.to((int)var0_12[190], "P");
        var22_20[(int)var0_12[96]] = TuplesKt.to((int)var0_12[130], "Q");
        var22_20[(int)var0_12[98]] = TuplesKt.to((int)var0_12[89], "R");
        var22_20[(int)var0_12[57]] = TuplesKt.to((int)var0_12[141], "S");
        var22_20[(int)var0_12[55]] = TuplesKt.to((int)var0_12[179], "T");
        var22_20[(int)var0_12[131]] = TuplesKt.to((int)var0_12[140], "U");
        var22_20[(int)var0_12[148]] = TuplesKt.to((int)var0_12[3], "V");
        var22_20[(int)var0_12[68]] = TuplesKt.to((int)var0_12[126], "W");
        var22_20[(int)var0_12[35]] = TuplesKt.to((int)var0_12[193], "X");
        var22_20[(int)var0_12[36]] = TuplesKt.to((int)var0_12[229], "Y");
        var22_20[(int)var0_12[92]] = TuplesKt.to((int)var0_12[22], "Z");
        var22_20[(int)var0_12[9]] = TuplesKt.to((int)var0_12[128], "LeftBracket");
        var22_20[(int)var0_12[142]] = TuplesKt.to((int)var0_12[100], "BackSlash");
        var22_20[(int)var0_12[85]] = TuplesKt.to((int)var0_12[219], "RightBracket");
        var22_20[(int)var0_12[25]] = TuplesKt.to((int)var0_12[156], "Grave");
        var22_20[(int)var0_12[24]] = TuplesKt.to((int)var0_12[62], "Escape");
        var22_20[(int)var0_12[50]] = TuplesKt.to((int)var0_12[213], "Enter");
        var22_20[(int)var0_12[200]] = TuplesKt.to((int)var0_12[135], "Tab");
        var22_20[(int)var0_12[194]] = TuplesKt.to((int)var0_12[20], "BackSpace");
        var22_20[(int)var0_12[110]] = TuplesKt.to((int)var0_12[181], "Insert");
        var22_20[(int)var0_12[208]] = TuplesKt.to((int)var0_12[180], "Delete");
        var22_20[(int)var0_12[196]] = TuplesKt.to((int)var0_12[18], "Right");
        var22_20[(int)var0_12[195]] = TuplesKt.to((int)var0_12[93], "Left");
        var22_20[(int)var0_12[103]] = TuplesKt.to((int)var0_12[4], "Down");
        var22_20[(int)var0_12[27]] = TuplesKt.to((int)var0_12[101], "Up");
        var22_20[(int)var0_12[79]] = TuplesKt.to((int)var0_12[216], "PageUp");
        var22_20[(int)var0_12[127]] = TuplesKt.to((int)var0_12[86], "PageDown");
        var22_20[(int)var0_12[220]] = TuplesKt.to((int)var0_12[234], "Home");
        var22_20[(int)var0_12[63]] = TuplesKt.to((int)var0_12[178], "End");
        var22_20[(int)var0_12[83]] = TuplesKt.to((int)var0_12[80], "CapsLock");
        var22_20[(int)var0_12[111]] = TuplesKt.to((int)var0_12[206], "ScrollLock");
        var22_20[(int)var0_12[58]] = TuplesKt.to((int)var0_12[174], "NumLock");
        var22_20[(int)var0_12[0]] = TuplesKt.to((int)var0_12[74], "PrintScreen");
        var22_20[(int)var0_12[115]] = TuplesKt.to((int)var0_12[146], "Pause");
        var22_20[(int)var0_12[173]] = TuplesKt.to((int)var0_12[225], "F1");
        var22_20[(int)var0_12[5]] = TuplesKt.to((int)var0_12[120], "F2");
        var22_20[(int)var0_12[6]] = TuplesKt.to((int)var0_12[64], "F3");
        var22_20[(int)var0_12[207]] = TuplesKt.to((int)var0_12[65], "F4");
        var22_20[(int)var0_12[150]] = TuplesKt.to((int)var0_12[153], "F5");
        var22_20[(int)var0_12[31]] = TuplesKt.to((int)var0_12[53], "F6");
        var22_20[(int)var0_12[197]] = TuplesKt.to((int)var0_12[138], "F7");
        var22_20[(int)var0_12[44]] = TuplesKt.to((int)var0_12[67], "F8");
        var22_20[(int)var0_12[232]] = TuplesKt.to((int)var0_12[7], "F9");
        var22_20[(int)var0_12[39]] = TuplesKt.to((int)var0_12[132], "F10");
        var22_20[(int)var0_12[125]] = TuplesKt.to((int)var0_12[69], "F11");
        var22_20[(int)var0_12[51]] = TuplesKt.to((int)var0_12[52], "F12");
        var22_20[(int)var0_12[102]] = TuplesKt.to((int)var0_12[152], "F13");
        var22_20[(int)var0_12[90]] = TuplesKt.to((int)var0_12[95], "F14");
        var22_20[(int)var0_12[48]] = TuplesKt.to((int)var0_12[162], "F15");
        var22_20[(int)var0_12[164]] = TuplesKt.to((int)var0_12[230], "F16");
        var22_20[(int)var0_12[226]] = TuplesKt.to((int)var0_12[187], "F17");
        var22_20[(int)var0_12[1]] = TuplesKt.to((int)var0_12[222], "F18");
        var22_20[(int)var0_12[94]] = TuplesKt.to((int)var0_12[113], "F19");
        var22_20[(int)var0_12[32]] = TuplesKt.to((int)var0_12[37], "F20");
        var22_20[(int)var0_12[201]] = TuplesKt.to((int)var0_12[114], "F21");
        var22_20[(int)var0_12[221]] = TuplesKt.to((int)var0_12[157], "F22");
        var22_20[(int)var0_12[116]] = TuplesKt.to((int)var0_12[29], "F23");
        var22_20[(int)var0_12[134]] = TuplesKt.to((int)var0_12[81], "F24");
        var22_20[(int)var0_12[163]] = TuplesKt.to((int)var0_12[166], "F25");
        var22_20[(int)var0_12[188]] = TuplesKt.to((int)var0_12[198], "NUM 0");
        var22_20[(int)var0_12[84]] = TuplesKt.to((int)var0_12[211], "NUM 1");
        var22_20[(int)var0_12[182]] = TuplesKt.to((int)var0_12[204], "NUM 2");
        var22_20[(int)var0_12[78]] = TuplesKt.to((int)var0_12[217], "NUM 3");
        var22_20[(int)var0_12[16]] = TuplesKt.to((int)var0_12[155], "NUM 4");
        var22_20[(int)var0_12[143]] = TuplesKt.to((int)var0_12[17], "NUM 5");
        var22_20[(int)var0_12[14]] = TuplesKt.to((int)var0_12[15], "NUM 6");
        var22_20[(int)var0_12[203]] = TuplesKt.to((int)var0_12[21], "NUM 7");
        var22_20[(int)var0_12[168]] = TuplesKt.to((int)var0_12[76], "NUM 8");
        var22_20[(int)var0_12[117]] = TuplesKt.to((int)var0_12[227], "NUM 9");
        var22_20[(int)var0_12[61]] = TuplesKt.to((int)var0_12[123], "Deci");
        var22_20[(int)var0_12[70]] = TuplesKt.to((int)var0_12[99], "Div");
        var22_20[(int)var0_12[186]] = TuplesKt.to((int)var0_12[154], "Mul");
        var22_20[(int)var0_12[183]] = TuplesKt.to((int)var0_12[107], "Sub");
        var22_20[(int)var0_12[175]] = TuplesKt.to((int)var0_12[87], "Add");
        var22_20[(int)var0_12[28]] = TuplesKt.to((int)var0_12[167], "Enter");
        var22_20[(int)var0_12[112]] = TuplesKt.to((int)var0_12[41], "Equal");
        var22_20[(int)var0_12[34]] = TuplesKt.to((int)var0_12[191], "Shift");
        var22_20[(int)var0_12[149]] = TuplesKt.to((int)var0_12[108], "LCtrl");
        var22_20[(int)var0_12[212]] = TuplesKt.to((int)var0_12[49], "LAlt");
        var22_20[(int)var0_12[214]] = TuplesKt.to((int)var0_12[185], "LSuper");
        var22_20[(int)var0_12[66]] = TuplesKt.to((int)var0_12[71], "RShift");
        var22_20[(int)var0_12[209]] = TuplesKt.to((int)var0_12[12], "RCtrl");
        var22_20[(int)var0_12[177]] = TuplesKt.to((int)var0_12[23], "RAlt");
        var22_20[(int)var0_12[33]] = TuplesKt.to((int)var0_12[147], "RSuper");
        var22_20[(int)var0_12[133]] = TuplesKt.to((int)var0_12[233], "Menu");
        KeyNames.V = MapsKt.mapOf(var22_20);
    }

    public static void zkmSetFlag(Module[] moduleArray) {
        y = moduleArray;
    }

    public static Module[] zkmGetFlag() {
        return y;
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
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x7D56;
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
                throw new RuntimeException("su/catlean/rj", exception);
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
            KeyNames.c[n2] = KeyNames.zkmDecodeUtf8(((Cipher)objectArray[0]).doFinal(byArray2));
        }
        return c[n2];
    }

    private static Object zkmDispatch(MethodHandles.Lookup lookup, MutableCallSite mutableCallSite, String string, Object[] objectArray) {
        int n = (Integer)objectArray[0];
        long l = (Long)objectArray[1];
        String string2 = KeyNames.zkmDecryptString(n, l);
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
            throw new RuntimeException("su/catlean/rj" + " : " + string + " : " + methodType.toString(), exception);
        }
        return mutableCallSite;
    }

    /*
     * Works around MethodHandle LDC.
     */
    static MethodHandle cfr_ldc_0() {
        try {
            return MethodHandles.lookup().findStatic(KeyNames.class, "zkmDispatch", MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/invoke/MutableCallSite;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null));
        }
        catch (NoSuchMethodException | IllegalAccessException except) {
            throw new IllegalArgumentException(except);
        }
    }
}

