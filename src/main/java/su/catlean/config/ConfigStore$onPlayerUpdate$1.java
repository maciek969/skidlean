/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.config;

import java.io.File;
import java.lang.invoke.MethodHandles;
import su.catlean.config.ConfigFiles;
import su.catlean.config.ConfigStore;
import su.catlean.gui.ConfigPanel;
import su.catlean.module.Module;
import su.catlean.zkm.ZkmKeys;

public final class ConfigStore$onPlayerUpdate$1
implements Runnable {
    final int i;
    private static final long a = ZkmKeys.a(-2108138520625534466L, 4052978590936760661L, MethodHandles.lookup().lookupClass()).a(182986827396611L);

    public ConfigStore$onPlayerUpdate$1(int n) {
        this.i = n;
    }

    @Override
    public final void run() {
        long l = a ^ 0x180AF6D5D2A5L;
        long l2 = l ^ 0x15B1C25A6221L;
        Thread.sleep(this.i);
        ConfigStore.INSTANCE.save(l2);
        File[] fileArray = ConfigFiles.l();
        int n = fileArray.length;
        for (int i = 0; i < n; ++i) {
            File file;
            File file2 = file = fileArray[i];
            file2.mkdirs();
        }
        ConfigStore.INSTANCE.getProfileConfigFile().save();
        ConfigStore.INSTANCE.getHudConfigFile().save();
        ConfigPanel.INSTANCE.open();
    }

    private static NumberFormatException zkmRethrow(NumberFormatException numberFormatException) {
        return numberFormatException;
    }
}

