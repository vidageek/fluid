package net.vidageek.fluid.proxy.handler;

import java.lang.reflect.Method;

import net.vidageek.fluid.FluidInterface;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class BuildHandler implements MethodHandler {

    private static final Method build = new Mirror().on(FluidInterface.class).reflect().method("build").withoutArgs();
    private final Object parent;

    public BuildHandler(final Object parent) {
        this.parent = parent;
    }

    public boolean accept(final Method method) {
        return build.equals(method);
    }

    public Object handle(final Object proxy, final Method method, final Object[] args) {
        return parent;
    }

}
