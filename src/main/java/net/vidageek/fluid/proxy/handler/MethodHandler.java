package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Method;

/**
 * @author jonasabreu
 * 
 */
public interface MethodHandler {

    boolean accept(Method method);

    Object handle(Object proxy, Method method, Object[] args);

}
