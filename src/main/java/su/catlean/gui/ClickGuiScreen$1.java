/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gui;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import su.catlean.gui.CategoryPanel;
import su.catlean.gui.ClickGuiScreen;
import su.catlean.gui.ModuleButton;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class ClickGuiScreen$1
implements Runnable {
    final int D;
    final Module module;
    private static String x;
    private static final long a;

    public ClickGuiScreen$1(int n, Module module) {
        this.D = n;
        this.module = module;
    }

    @Override
    public final void run() {
        String string = ClickGuiScreen$1.zkmGetFlag();
        Thread.sleep(this.D);
        String string2 = string;
        Iterable iterable = ClickGuiScreen.getCategoryPanels();
        Iterator iterator2 = iterable.iterator();
        block0: while (true) {
            boolean bl = iterator2.hasNext();
            block1: while (bl) {
                Object t = iterator2.next();
                CategoryPanel categoryPanel = (CategoryPanel)t;
                Iterable iterable2 = categoryPanel.getModuleButtons();
                if (string2 == null) break block0;
                for (Object t2 : iterable2) {
                    ModuleButton moduleButton = (ModuleButton)t2;
                    if (string2 != null) {
                        bl = Intrinsics.areEqual(moduleButton.getModule(), this.module);
                        if (string2 == null) continue block1;
                        if (bl) {
                            moduleButton.j();
                        }
                    }
                    if (string2 != null) continue;
                }
                if (string2 != null) continue block0;
            }
            break;
        }
    }

    public static void zkmSetFlag(String string) {
        x = string;
    }

    public static String zkmGetFlag() {
        return x;
    }

    static {
        a = ZkmKeys.a(-1457104587333393916L, 4982747470262936036L, MethodHandles.lookup().lookupClass()).a(208906287101498L);
        if (ClickGuiScreen$1.zkmGetFlag() == null) {
            ClickGuiScreen$1.zkmSetFlag("SMzMUb");
        }
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

