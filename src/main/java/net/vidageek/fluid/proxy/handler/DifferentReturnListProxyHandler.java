package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.fluid.annotations.FluidClass;
import net.vidageek.fluid.annotations.FluidField;
import net.vidageek.fluid.proxy.FluidProxyCreator;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class DifferentReturnListProxyHandler implements MethodHandler {

    private final Object watchedInstance;

    public DifferentReturnListProxyHandler(final Object watchedInstance) {
        this.watchedInstance = watchedInstance;
    }

    public boolean accept(final Method method) {
        return method.getName().startsWith("add") && !method.getDeclaringClass().equals(method.getReturnType());
    }

    public Object handle(final Object proxy, final Method method, final Object[] args) {

        String fieldName = method.getAnnotation(FluidField.class).value();

        List listField = (List) new Mirror().on(watchedInstance).get().field(fieldName);
        if (listField == null) {
            listField = new ArrayList();
            new Mirror().on(watchedInstance).set().field(fieldName).withValue(listField);
        }

        Object child = new Mirror()
            .on(method.getReturnType().getAnnotation(FluidClass.class).value())
            .invoke()
            .constructor()
            .withoutArgs();

        listField.add(child);

        return new FluidProxyCreator(method.getReturnType()).createProxy(proxy, child);
    }

}
