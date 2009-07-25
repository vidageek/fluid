package net.vidageek.fluid.proxy.converter;

/**
 * @author jonasabreu
 * 
 */
public interface DataConverter<F, T> {

    T convert(F object);

}
