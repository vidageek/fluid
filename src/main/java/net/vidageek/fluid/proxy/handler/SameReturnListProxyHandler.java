package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.fluid.annotations.FluidField;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class SameReturnListProxyHandler implements MethodHandler {

    private final Object watchedInstance;

    public SameReturnListProxyHandler(final Object watchedInstance) {
        this.watchedInstance = watchedInstance;
    }

    public boolean accept(final Method method) {
        return method.getName().startsWith("add") && method.getDeclaringClass().equals(method.getReturnType());
    }

    public Object handle(final Object proxy, final Method method, final Object[] args) {
        String fieldName = method.getAnnotation(FluidField.class).value();

        List listField = (List) new Mirror().on(watchedInstance).get().field(fieldName);
        if (listField == null) {
            listField = new ArrayList();
            new Mirror().on(watchedInstance).set().field(fieldName).withValue(listField);
        }

        listField.add(args[0]);

        return proxy;
    }

}
