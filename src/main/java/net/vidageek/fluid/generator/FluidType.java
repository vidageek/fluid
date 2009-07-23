package net.vidageek.fluid.generator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.vidageek.fluid.annotations.FluidName;
import net.vidageek.mirror.dsl.Mirror;

/**
 * @author jonasabreu
 * 
 */
final public class FluidType {

    private final Class<?> model;
    private final String packageName;
    private final Set<Class<?>> types;

    public FluidType(final Class<?> model, final String packageName, final Set<Class<?>> types2) {
        this.model = model;
        this.packageName = packageName;
        types = types2;
    }

    public String getPackage() {
        return packageName;
    }

    public String getModelName() {
        return model.getName();
    }

    public String getInterfaceName() {
        String name = model.getSimpleName();
        if (model.isAnnotationPresent(FluidName.class)) {
            name = model.getAnnotation(FluidName.class).value();
        }
        return name;
    }

    public List<FluidMethod> getMethods() {
        List<FluidMethod> list = new ArrayList<FluidMethod>();
        for (Field field : new Mirror().on(model).reflectAll().fields()) {
            list.add(new FluidMethod(model, field, types));
        }
        return list;
    }

}
