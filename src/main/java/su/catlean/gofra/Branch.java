/*
 * Decompiled with CFR 0.152.
 */
package su.catlean.gofra;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import su.catlean.gofra.Flow;

public final class Branch {
    @NotNull
    private final Class<?> eventTarget;
    private final byte priority;
    @NotNull
    private Consumer<Object> consumer;
    private Method privateLookupInMethod;

    public Branch(@NotNull Class<?> klass, @NotNull Object obj, @NotNull Method method) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        Intrinsics.checkNotNullParameter(obj, "obj");
        Intrinsics.checkNotNullParameter(method, "method");
        Class<?> clazz = method.getParameters()[0].getType();
        Intrinsics.checkNotNullExpressionValue(clazz, "getType(...)");
        this.eventTarget = clazz;
        this.priority = method.getAnnotation(Flow.class).priority();
        Class[] classArray = new Class[]{Class.class, MethodHandles.Lookup.class};
        this.privateLookupInMethod = MethodHandles.class.getDeclaredMethod("privateLookupIn", classArray);
        Object[] objectArray = new Object[]{klass, MethodHandles.lookup()};
        Object object = this.privateLookupInMethod.invoke(null, objectArray);
        Intrinsics.checkNotNull(object, "null cannot be cast to non-null type java.lang.invoke.MethodHandles.Lookup");
        MethodHandles.Lookup l = (MethodHandles.Lookup)object;
        MethodType mT = MethodType.methodType(Void.TYPE, method.getParameters()[0].getType());
        MethodHandle mH = l.findVirtual(klass, method.getName(), mT);
        MethodType iT = MethodType.methodType(Consumer.class, klass);
        this.consumer = LambdaMetafactory.metafactory(l, "accept", iT, MethodType.methodType(Void.TYPE, Object.class), mH, mT).getTarget().invoke(obj);
    }

    @NotNull
    public final Class<?> getEventTarget() {
        return this.eventTarget;
    }

    public final byte getPriority() {
        return this.priority;
    }

    public final void call(@NotNull Object event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.consumer.accept(event);
    }
}

