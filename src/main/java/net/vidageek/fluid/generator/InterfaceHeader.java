package net.vidageek.fluid.generator;

import net.vidageek.fluid.FluidInterface;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceHeader implements FluidElement {

    private final InterfaceName interfaceName;
    private final Class<?> originalType;

    public InterfaceHeader(final InterfaceName interfaceName, final Class<?> originalType) {
        this.interfaceName = interfaceName;
        this.originalType = originalType;
    }

    public String asString() {
        String code = "";
        code += "@net.vidageek.fluid.annotations.FluidClass(" + originalType.getName() + ".class)\n";
        code += "public interface " + interfaceName.asString() + " extends " + FluidInterface.class.getName()
                + "<T> {\n\n";
        return code;
    }

}
