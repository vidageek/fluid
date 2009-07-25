package net.vidageek.fluid.proxy.converter;

import java.math.BigInteger;


/**
 * @author jonasabreu
 * 
 */
final public class BigIntegerToStringConverter implements DataConverter<BigInteger, String> {

    public String convert(final BigInteger object) {
        return object.toString();
    }

}
