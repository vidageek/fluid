package net.vidageek.fluid.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author jonasabreu
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FluidField {

    String value();

}
