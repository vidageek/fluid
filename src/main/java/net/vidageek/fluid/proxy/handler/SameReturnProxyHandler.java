package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Method;

import net.vidageek.fluid.annotations.FluidField;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class SameReturnProxyHandler implements MethodHandler {

    private final Object watchedInstance;

    public SameReturnProxyHandler(final Object watchedInstance) {
        this.watchedInstance = watchedInstance;
    }

    public boolean accept(final Method method) {
        return method.getName().startsWith("with") && method.getDeclaringClass().equals(method.getReturnType());
    }

    public Object handle(final Object proxy, final Method method, final Object[] args) {
        new Mirror().on(watchedInstance).set().field(method.getAnnotation(FluidField.class).value()).withValue(args[0]);
        return proxy;
    }

}
