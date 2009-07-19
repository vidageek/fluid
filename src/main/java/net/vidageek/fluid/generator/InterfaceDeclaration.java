package net.vidageek.fluid.generator;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceDeclaration implements FluidElement {

    private final Class<?> type;

    public InterfaceDeclaration(final Class<?> type) {
        this.type = type;
    }

    public String asString() {
        return "public interface " + type.getSimpleName() + " {";
    }

}
