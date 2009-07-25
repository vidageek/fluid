package net.vidageek.fluid.proxy.converter;

import java.math.BigDecimal;

/**
 * @author jonasabreu
 * 
 */
final public class BigDecimalToStringConverter implements DataConverter<BigDecimal, String> {

    public String convert(final BigDecimal object) {
        return object.toPlainString();
    }

}
