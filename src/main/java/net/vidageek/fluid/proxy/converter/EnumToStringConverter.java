package net.vidageek.fluid.proxy.converter;

/**
 * @author jonasabreu
 * 
 */
final public class EnumToStringConverter implements DataConverter<Enum, String> {

    public String convert(final Enum object) {
        return object.toString();
    }

}
