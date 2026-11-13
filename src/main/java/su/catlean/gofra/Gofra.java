/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gofra;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.catlean.gofra.Branch;
import su.catlean.gofra.Flow;

public final class Gofra {
    @NotNull
    public static final Gofra INSTANCE = new Gofra();
    @NotNull
    private static final ConcurrentHashMap<Object, List<Branch>> cachedBranches = new ConcurrentHashMap();
    @NotNull
    private static final ConcurrentHashMap<Object, List<Branch>> branches = new ConcurrentHashMap();

    private Gofra() {
    }

    @Nullable
    public final Unit drain(@NotNull Object event) {
        Unit unit;
        Intrinsics.checkNotNullParameter(event, "event");
        List<Branch> list = branches.get(event.getClass());
        if (list != null) {
            List<Branch> it = list;
            Iterable $this$forEach$iv = it;
            for (Object element$iv : $this$forEach$iv) {
                Branch m = (Branch)element$iv;
                m.call(event);
            }
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit;
    }

    public final void plugAll(Object ... listeners) {
        Intrinsics.checkNotNullParameter(listeners, "listeners");
        Object[] $this$forEach$iv = listeners;
        int n = $this$forEach$iv.length;
        for (int i = 0; i < n; ++i) {
            Object element$iv;
            Object it = element$iv = $this$forEach$iv[i];
            INSTANCE.plug(it);
        }
    }

    public final void plug(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        Iterable $this$forEach$iv = this.getBranches(obj);
        for (Object element$iv : $this$forEach$iv) {
            Branch it = (Branch)element$iv;
            List list = branches.computeIfAbsent(it.getEventTarget(), arg_0 -> Gofra.plug$lambda$5$lambda$4(Gofra::plug$lambda$5$lambda$3, arg_0));
            Intrinsics.checkNotNullExpressionValue(list, "computeIfAbsent(...)");
            INSTANCE.insert(list, it);
        }
    }

    public final void unplug(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        Iterable $this$forEach$iv = this.getBranches(obj);
        for (Object element$iv : $this$forEach$iv) {
            Branch it = (Branch)element$iv;
            List<Branch> list = branches.get(it.getEventTarget());
            if (list == null) continue;
            list.remove(it);
        }
    }

    /*
     * WARNING - void declaration
     */
    private final void insert(List<Branch> bl, Branch b) {
        void it;
        int n;
        int n2;
        List<Branch> list;
        block2: {
            void $this$indexOfFirst$iv;
            List<Branch> list2 = bl;
            list = bl;
            int index$iv = 0;
            for (Object item$iv : $this$indexOfFirst$iv) {
                Branch it2 = (Branch)item$iv;
                if (b.getPriority() > it2.getPriority()) {
                    n2 = index$iv;
                    break block2;
                }
                ++index$iv;
            }
            n2 = -1;
        }
        int $i$f$indexOfFirst = n = n2;
        n = it == -1 ? bl.size() : it;
        list.add(n, b);
    }

    private final List<Branch> getBranches(Object obj) {
        List list = cachedBranches.computeIfAbsent(obj, arg_0 -> Gofra.getBranches$lambda$11(arg_0 -> Gofra.getBranches$lambda$10(obj, arg_0), arg_0));
        Intrinsics.checkNotNullExpressionValue(list, "computeIfAbsent(...)");
        return list;
    }

    private final void getBranchesRecursive(List<Branch> listeners, Class<?> klass, Object obj) {
        Iterator<Method> iterator2 = ArrayIteratorKt.iterator(klass.getDeclaredMethods());
        while (iterator2.hasNext()) {
            Method method = iterator2.next();
            if (!method.isAnnotationPresent(Flow.class)) continue;
            Intrinsics.checkNotNull(method);
            listeners.add(new Branch(klass, obj, method));
        }
        if (klass.getSuperclass() != null) {
            Class<?> clazz = klass.getSuperclass();
            Intrinsics.checkNotNullExpressionValue(clazz, "getSuperclass(...)");
            this.getBranchesRecursive(listeners, clazz, obj);
        }
    }

    private static final List plug$lambda$5$lambda$3(Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new CopyOnWriteArrayList();
    }

    private static final List plug$lambda$5$lambda$4(Function1 $tmp0, Object p0) {
        return (List)$tmp0.invoke(p0);
    }

    private static final List getBranches$lambda$10(Object $obj, Object it) {
        CopyOnWriteArrayList copyOnWriteArrayList;
        Intrinsics.checkNotNullParameter(it, "it");
        CopyOnWriteArrayList $this$getBranches_u24lambda_u2410_u24lambda_u249 = copyOnWriteArrayList = new CopyOnWriteArrayList();
        INSTANCE.getBranchesRecursive($this$getBranches_u24lambda_u2410_u24lambda_u249, $obj.getClass(), $obj);
        return copyOnWriteArrayList;
    }

    private static final List getBranches$lambda$11(Function1 $tmp0, Object p0) {
        return (List)$tmp0.invoke(p0);
    }
}

