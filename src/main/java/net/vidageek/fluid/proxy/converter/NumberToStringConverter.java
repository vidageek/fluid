package net.vidageek.fluid.proxy.converter;

/**
 * @author jonasabreu
 * 
 */
final public class NumberToStringConverter implements DataConverter<Number, String> {

    public String convert(final Number object) {
        return object.toString();
    }

}
