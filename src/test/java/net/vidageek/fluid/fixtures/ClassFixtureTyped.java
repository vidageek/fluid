package net.vidageek.fluid.fixtures;

import java.math.BigInteger;

import net.vidageek.fluid.annotations.FluidDataType;

/**
 * @author jonasabreu
 * 
 */
final public class ClassFixtureTyped {

    @SuppressWarnings("unused")
    @FluidDataType(BigInteger.class)
    private String field;

}
