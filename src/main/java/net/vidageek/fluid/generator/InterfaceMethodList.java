package net.vidageek.fluid.generator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceMethodList implements FluidElement {

    private final List<FluidElement> elements;

    public InterfaceMethodList(final Class<?> type, final InterfaceName interfaceName) {
        elements = new ArrayList<FluidElement>();
        for (Field field : new Mirror().on(type).reflectAll().fields()) {
            elements.add(new InterfaceMethod(field, interfaceName));
        }
    }

    public String asString() {
        String code = "";
        for (FluidElement element : elements) {
            code += element.asString();
        }
        return code;
    }

}
