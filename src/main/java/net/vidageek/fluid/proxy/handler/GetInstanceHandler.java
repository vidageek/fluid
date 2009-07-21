package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Method;

import net.vidageek.fluid.proxy.ObjectCreator;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class GetInstanceHandler implements MethodHandler {

    private final Object watchedInstance;

    private static final Method getInstance = new Mirror()
                                                          .on(ObjectCreator.class)
                                                          .reflect()
                                                          .method("getInstance")
                                                          .withoutArgs();

    public GetInstanceHandler(final Object watchedInstance) {
        this.watchedInstance = watchedInstance;
    }

    public boolean accept(final Method method) {
        return getInstance.equals(method);
    }

    public Object handle(final Object proxy, final Method method, final Object[] args) {
        return watchedInstance;
    }

}
