package net.vidageek.fluid.generator;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;

import net.vidageek.fluid.annotations.FluidDataType;
import net.vidageek.fluid.annotations.FluidName;

/**
 * @author jonasabreu
 * 
 */
public class FluidMethod {

    private final String originalField;
    private final String returnType;
    private final String methodName;
    private final String param;

    public FluidMethod(final Class<?> model, final Field field, final HashSet<Class<?>> types) {
        originalField = field.getName();

        methodName = generatesMethodName(field);

        param = generatesParameter(field, types);

        returnType = generatesReturnType(model, field, types);
    }

    private String generatesMethodName(final Field field) {
        String name = List.class.isAssignableFrom(field.getType()) ? "add" : "with";
        return name + capitalize(getFieldName(field));
    }

    private String generatesReturnType(final Class<?> model, final Field field, final HashSet<Class<?>> types) {
        String ret = new FluidType(model, "", types).getInterfaceName() + "<T>";
        if (types.contains(field.getType())) {
            ret = new FluidType(field.getType(), "", types).getInterfaceName() + "<" + ret + ">";
        }
        return ret;
    }

    private String generatesParameter(final Field field, final HashSet<Class<?>> types) {
        String parameter = "";
        Class<?> type = field.getType();
        if (List.class.isAssignableFrom(type)) {
            type = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        }
        if (field.isAnnotationPresent(FluidDataType.class)) {
            type = field.getAnnotation(FluidDataType.class).value();
        }
        if (!types.contains(type)) {
            parameter = type.getName() + " " + getFieldName(field);
        }
        return parameter;
    }

    private String getFieldName(final Field field) {
        return field.isAnnotationPresent(FluidName.class) ? field.getAnnotation(FluidName.class).value()
                : originalField;
    }

    public String getOriginalFieldName() {
        return originalField;
    }

    public String getParameter() {
        return param;
    }

    public String getName() {
        return methodName;
    }

    public String getReturnType() {
        return returnType;
    }

    private String capitalize(final String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
