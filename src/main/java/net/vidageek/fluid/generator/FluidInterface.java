package net.vidageek.fluid.generator;

import java.util.Set;

import net.vidageek.fluid.annotations.FluidClass;
import net.vidageek.fluid.annotations.FluidField;

/**
 * @author jonasabreu
 * 
 */
final public class FluidInterface {

    private final FluidType type;

    public FluidInterface(final Class<?> clazz, final String packageName, final Set<Class<?>> types) {
        type = new FluidType(clazz, packageName, types);
    }

    public String asString() {
        String code = "";
        code += "package " + type.getPackage() + ";\n\n";
        code += "@" + FluidClass.class.getName() + "(" + type.getModelName() + ".class)\n";
        code += "public interface " + type.getInterfaceName() + "<T> extends "
                + net.vidageek.fluid.FluidInterface.class.getName() + "<T> {\n\n";

        for (FluidMethod method : type.getMethods()) {
            code += "    @" + FluidField.class.getName() + "(\"" + method.getOriginalFieldName() + "\")\n";
            code += "    " + method.getReturnType() + " " + method.getName() + "(" + method.getParameter() + ");"
                    + "\n\n";
        }

        code += "}";
        return code;
    }

}
