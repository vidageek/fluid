package net.vidageek.fluid.proxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.fluid.annotations.FluidClass;
import net.vidageek.fluid.proxy.handler.MethodHandler;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class FluidProxyCreator<T> {

    private final Class<T> type;
    private final List<MethodHandler> userHandlers;

    public FluidProxyCreator(final Class<T> type, final List<MethodHandler> userHandlers) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (!type.isInterface()) {
            throw new IllegalArgumentException("type must be an interface");
        }
        if (!type.isAnnotationPresent(FluidClass.class)) {
            throw new IllegalArgumentException("interface " + type.getName() + " should be annotated by "
                    + FluidClass.class.getName());
        }
        if (userHandlers == null) {
            throw new IllegalArgumentException("userHandlers cannot be null");
        }
        this.userHandlers = userHandlers;
        this.type = type;
    }

    public FluidProxyCreator(final Class<T> type) {
        this(type, new ArrayList<MethodHandler>());
    }

    public T createProxy() {
        return createProxy(null, new Mirror().on(recoverType(type)).invoke().constructor().withoutArgs());
    }

    @SuppressWarnings("unchecked")
    public T createProxy(final Object parent, final Object watchedInstance) {

        return (T) Proxy.newProxyInstance(FluidProxyCreator.class.getClassLoader(), new Class<?>[] { type,
                ObjectCreator.class }, new FluidInvocationHandler(parent, watchedInstance, userHandlers));
    }

    private Class<?> recoverType(final Class<T> clazz) {
        return clazz.getAnnotation(FluidClass.class).value();
    }
}
