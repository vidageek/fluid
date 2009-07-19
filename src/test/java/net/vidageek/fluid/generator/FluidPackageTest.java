package net.vidageek.fluid.generator;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class FluidPackageTest {

    @Test
    public void testThatCreatesPackageDeclaration() {
        Assert.assertEquals("package net.vidageek.test;", new FluidPackage("net.vidageek.test").asString());
    }
}
