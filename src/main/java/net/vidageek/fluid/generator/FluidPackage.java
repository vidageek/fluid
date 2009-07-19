package net.vidageek.fluid.generator;

/**
 * @author jonasabreu
 * 
 */
final public class FluidPackage implements FluidElement {

    private final String packageName;

    public FluidPackage(final String packageName) {
        this.packageName = packageName;
    }

    public String asString() {
        return "package " + packageName.trim() + ";\n\n";
    }

}
