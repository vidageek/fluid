package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.vidageek.fluid.annotations.FluidClass;
import net.vidageek.fluid.annotations.FluidField;
import net.vidageek.fluid.proxy.converter.DataConverterManager;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class SameReturnProxyHandler implements MethodHandler {

    private final Object watchedInstance;
    private final DataConverterManager manager;

    public SameReturnProxyHandler(final Object watchedInstance, final DataConverterManager manager) {
        this.watchedInstance = watchedInstance;
        this.manager = manager;
    }

    public boolean accept(final Method method) {
        return method.getName().startsWith("with") && method.getDeclaringClass().equals(method.getReturnType());
    }

    public Object handle(final Object proxy, final Method method, final Object[] args) {
        Class<?> type = getFieldType(method);

        new Mirror().on(watchedInstance).set().field(method.getAnnotation(FluidField.class).value()).withValue(
                manager.convert(args[0], type));
        return proxy;
    }

    private Class<?> getFieldType(final Method method) {
        Class<?> model = method.getDeclaringClass().getAnnotation(FluidClass.class).value();
        Field field = new Mirror().on(model).reflect().field(method.getAnnotation(FluidField.class).value());
        return field.getType();
    }

}
