package net.vidageek.fluid.proxy.converter;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("unchecked")
final public class NoOpConverter implements DataConverter {

    public Object convert(final Object object) {
        return object;
    }

}
