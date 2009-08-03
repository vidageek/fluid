package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Method;

import net.vidageek.fluid.annotations.FluidClass;
import net.vidageek.fluid.annotations.FluidField;
import net.vidageek.fluid.proxy.FluidProxyCreator;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class DifferentReturnProxyHandler implements MethodHandler {

    private final Object watchedInstance;

    public DifferentReturnProxyHandler(final Object watchedInstance) {
        this.watchedInstance = watchedInstance;
    }

    public boolean accept(final Method method) {
        return method.getName().startsWith("with") && !method.getDeclaringClass().equals(method.getReturnType());
    }

    @SuppressWarnings("unchecked")
    public Object handle(final Object proxy, final Method method, final Object[] args) {

        Object child = new Mirror()
            .on(method.getReturnType().getAnnotation(FluidClass.class).value())
            .invoke()
            .constructor()
            .withoutArgs();

        new Mirror().on(watchedInstance).set().field(method.getAnnotation(FluidField.class).value()).withValue(child);

        return new FluidProxyCreator(method.getReturnType()).createProxy(proxy, child);
    }
}
