/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.api.event.events.client;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.api.event.Event;
import su.catlean.gofra.Gofra;

public final class InputEvent
extends Event {
    @NotNull
    public static final InputEvent INSTANCE = new InputEvent();
    public static Action action;
    public static Device device;
    private static int key;

    private InputEvent() {
    }

    @NotNull
    public final Action getAction() {
        Action action = InputEvent.action;
        if (action != null) {
            return action;
        }
        Intrinsics.throwUninitializedPropertyAccessException("action");
        return null;
    }

    public final void setAction(@NotNull Action action) {
        Intrinsics.checkNotNullParameter((Object)action, "<set-?>");
        InputEvent.action = action;
    }

    @NotNull
    public final Device getDevice() {
        Device device = InputEvent.device;
        if (device != null) {
            return device;
        }
        Intrinsics.throwUninitializedPropertyAccessException("device");
        return null;
    }

    public final void setDevice(@NotNull Device device) {
        Intrinsics.checkNotNullParameter((Object)device, "<set-?>");
        InputEvent.device = device;
    }

    public final int getKey() {
        return key;
    }

    public final void setKey(int n) {
        key = n;
    }

    public final boolean call(int key, @NotNull Device device, @NotNull Action action) {
        Intrinsics.checkNotNullParameter((Object)device, "device");
        Intrinsics.checkNotNullParameter((Object)action, "action");
        this.setCancelled(false);
        this.setDevice(device);
        this.setAction(action);
        InputEvent.key = key;
        Gofra.INSTANCE.drain(this);
        return this.getCancelled();
    }

    static {
        key = -1;
    }

    public static final class Action
    extends Enum<Action> {
        public static final /* enum */ Action Press = new Action();
        public static final /* enum */ Action Release = new Action();
        public static final /* enum */ Action Hold = new Action();
        private static final /* synthetic */ Action[] $VALUES;
        private static final /* synthetic */ EnumEntries $ENTRIES;

        public static Action[] values() {
            return (Action[])$VALUES.clone();
        }

        public static Action valueOf(String value) {
            return Enum.valueOf(Action.class, value);
        }

        @NotNull
        public static EnumEntries<Action> getEntries() {
            return $ENTRIES;
        }

        static {
            $VALUES = actionArray = new Action[]{Action.Press, Action.Release, Action.Hold};
            $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
        }
    }

    public static final class Device
    extends Enum<Device> {
        public static final /* enum */ Device Mouse = new Device();
        public static final /* enum */ Device Keyboard = new Device();
        private static final /* synthetic */ Device[] $VALUES;
        private static final /* synthetic */ EnumEntries $ENTRIES;

        public static Device[] values() {
            return (Device[])$VALUES.clone();
        }

        public static Device valueOf(String value) {
            return Enum.valueOf(Device.class, value);
        }

        @NotNull
        public static EnumEntries<Device> getEntries() {
            return $ENTRIES;
        }

        static {
            $VALUES = deviceArray = new Device[]{Device.Mouse, Device.Keyboard};
            $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
        }
    }
}

