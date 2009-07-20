package net.vidageek.fluid.generator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import net.vidageek.fluid.annotations.FluidField;
import net.vidageek.fluid.annotations.FluidName;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceMethod implements FluidElement {

    private final String method;

    public InterfaceMethod(final Field field, final InterfaceName interfaceName) {
        String fieldName = field.getName();
        if (field.isAnnotationPresent(FluidName.class)) {
            fieldName = field.getAnnotation(FluidName.class).value();
        }

        if (Collection.class.isAssignableFrom(field.getType())) {
            Class<?> type = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];

            method = generateMethod(field.getName(), fieldName, interfaceName, type, "add");
        } else {
            method = generateMethod(field.getName(), fieldName, interfaceName, field.getType(), "with");
        }
    }

    public String asString() {
        return method;
    }

    private String generateMethod(final String originalFieldName, final String fieldName,
            final InterfaceName interfaceName, final Class<?> type, final String prefix) {

        String code = "";
        code += "    @" + FluidField.class.getName() + "(\"" + originalFieldName + "\")\n";
        code += "    " + interfaceName.asString() + " " + prefix + capitalize(fieldName) + "(" + type.getName() + " "
                + fieldName + ");\n\n";
        return code;
    }

    private String capitalize(final String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
