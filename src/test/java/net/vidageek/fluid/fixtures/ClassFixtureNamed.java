package net.vidageek.fluid.fixtures;

import java.util.List;

import net.vidageek.fluid.annotations.FluidName;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("unused")
@FluidName("AnyClassName")
final public class ClassFixtureNamed {

    @FluidName("stringField")
    private String field;

    @FluidName("listOfInteger")
    private List<Integer> integers;

}
