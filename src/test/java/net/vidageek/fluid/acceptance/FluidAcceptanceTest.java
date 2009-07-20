package net.vidageek.fluid.acceptance;

import net.vidageek.fluid.fixtures.modelo.output.Parent;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class FluidAcceptanceTest {

    private Parent<Object> parent;

    @Test(expected = NullPointerException.class)
    public void testThatGeneratedInterfaceWorks() {
        @SuppressWarnings("unused")
        Object object = parent
                    .addIntegers(2)
                    .addIntegers(2)
                    .withField("string")
                    .build();
    }

}
