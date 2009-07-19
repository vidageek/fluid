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
        Assert.assertTrue(new FluidPackage("net.vidageek.test").asString().startsWith("package net.vidageek.test;"));
    }

    @Test
    public void testThatBreaksLineAfterDeclaration() {
        Assert.assertEquals("package net.vidageek.test;\n\n", new FluidPackage("net.vidageek.test").asString());
    }

    @Test
    public void testThatTrimsPackage() {
        Assert.assertEquals("package net.vidageek.test;\n\n", new FluidPackage("  net.vidageek.test  ").asString());
    }
}
