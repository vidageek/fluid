package net.vidageek.fluid.generator;

import net.vidageek.fluid.fixtures.ClassFixture;

/**
 * @author jonasabreu
 * 
 */
final public class Fluid {

    private final Class<ClassFixture> type;
    private final String packageName;

    public Fluid(final Class<ClassFixture> type, final String packageName) {
        this.packageName = packageName;
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }
        if (packageName == null) {
            throw new IllegalArgumentException("packageName cannot be null");
        }
        this.type = type;
    }

    public String generateInterface() {

        return new FluidInterface(type, packageName).asString();
    }

}
