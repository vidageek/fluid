package net.vidageek.fluid.proxy;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.fluid.annotations.FluidClass;
import net.vidageek.fluid.proxy.converter.DataConverterManager;
import net.vidageek.fluid.proxy.handler.MethodHandler;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class FluidProxyCreator<T> {

    private final Class<T> type;
    private final List<MethodHandler> userHandlers;
    private final DataConverterManager manager;

    public FluidProxyCreator(final Class<T> type, final List<MethodHandler> userHandlers,
            final DataConverterManager manager) {
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
        if (manager == null) {
            throw new IllegalArgumentException("manager cannot be null.");
        }
        this.manager = manager;
        this.userHandlers = userHandlers;
        this.type = type;
    }

    public FluidProxyCreator(final Class<T> type) {
        this(type, new ArrayList<MethodHandler>(), new DataConverterManager());
    }

    public T createProxy(final Object parent) {
        return createProxy(parent, new Mirror().on(recoverType(type)).invoke().constructor().withoutArgs());
    }

    @SuppressWarnings("unchecked")
    public T createProxy(final Object parentModel, final Object watchedInstance) {

        return (T) Proxy.newProxyInstance(FluidProxyCreator.class.getClassLoader(), new Class<?>[] { type,
                ObjectCreator.class }, new FluidInvocationHandler(parentModel, watchedInstance, userHandlers, manager));
    }

    private Class<?> recoverType(final Class<T> clazz) {
        return clazz.getAnnotation(FluidClass.class).value();
    }
}
