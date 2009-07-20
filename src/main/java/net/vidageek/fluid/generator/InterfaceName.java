package net.vidageek.fluid.generator;

import net.vidageek.fluid.annotations.FluidName;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceName implements FluidElement {

    private final Class<?> type;

    public InterfaceName(final Class<?> type) {
        this.type = type;
    }

    public String asString() {
        String name = type.getSimpleName();
        if (type.isAnnotationPresent(FluidName.class)) {
            name = type.getAnnotation(FluidName.class).value();
        }
        return name + "<T>";
    }
}
