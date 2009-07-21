package net.vidageek.fluid.generator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Set;

import net.vidageek.fluid.annotations.FluidField;
import net.vidageek.fluid.annotations.FluidName;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceMethod implements FluidElement {

    private final String method;

    public InterfaceMethod(final Field field, final InterfaceName interfaceName, final Set<Class<?>> types) {
        String fieldName = field.getName();
        if (field.isAnnotationPresent(FluidName.class)) {
            fieldName = field.getAnnotation(FluidName.class).value();
        }

        Class<?> type = field.getType();
        if (Collection.class.isAssignableFrom(type)) {
            type = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        }

        if (!types.contains(type)) {

            if (Collection.class.isAssignableFrom(field.getType())) {
                method = generateMethod(field.getName(), fieldName, interfaceName, type, "add");
            } else {
                method = generateMethod(field.getName(), fieldName, interfaceName, type, "with");
            }
        } else {
            // different declaring and returning types
            if (Collection.class.isAssignableFrom(field.getType())) {
                method = generateMethodWithDifferentReturnType(field.getName(), fieldName, interfaceName, type, "add");
            } else {
                method = generateMethodWithDifferentReturnType(field.getName(), fieldName, interfaceName, type, "with");
            }
        }
    }

    private String generateMethodWithDifferentReturnType(final String name, final String fieldName,
            final InterfaceName interfaceName, final Class<?> type, final String prefix) {
        String method = generateMethod(name, fieldName, interfaceName, type, prefix);
        String string = new InterfaceName(type).asString();

        method = method.replaceAll("\\([^\"]*?\\)", "()");

        return method.replace(interfaceName.asString(), string.substring(0, string.length() - 2)
                + interfaceName.asString() + ">");
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
