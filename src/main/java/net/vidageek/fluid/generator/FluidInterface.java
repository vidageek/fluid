package net.vidageek.fluid.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonasabreu
 * 
 */
final public class FluidInterface implements FluidElement {

    private final List<FluidElement> elements;

    public FluidInterface(final Class<?> type, final String packageName) {
        elements = new ArrayList<FluidElement>();
        elements.add(new FluidPackage(packageName));
        elements.add(new InterfaceDeclaration(type));
    }

    public String asString() {
        String code = "";
        for (FluidElement element : elements) {
            code += element.asString();
        }
        return code;
    }

}
