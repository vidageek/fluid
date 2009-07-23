package net.vidageek.fluid.generator;

import java.util.Set;

/**
 * @author jonasabreu
 * 
 */
final public class Fluid {

    private final Class<?> type;
    private final String packageName;

    public Fluid(final Class<?> type, final String packageName) {
        this.packageName = packageName;
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (packageName == null) {
            throw new IllegalArgumentException("packageName cannot be null");
        }
        this.type = type;
    }

    public String generateInterface(final Set<Class<?>> types) {

        return new FluidInterface(type, packageName, types).asString();
    }

}
