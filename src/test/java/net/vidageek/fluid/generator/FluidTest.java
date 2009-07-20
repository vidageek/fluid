package net.vidageek.fluid.generator;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.ClassFixture;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class FluidTest {

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new Fluid(null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfPackageIsNull() {
        new Fluid(ClassFixture.class, null);
    }

    @Test
    public void testThatGeneratesInterfaceName() {
        String code = new Fluid(ClassFixture.class, "").generateInterface();
        Assert.assertTrue("code should hava interface declaration", code.contains("interface ClassFixture"));
    }

    @Test
    public void testThatAddsPackage() {
        String code = new Fluid(ClassFixture.class, "net.vidageek.test").generateInterface();
        Assert.assertTrue("code should have package declaration", code.startsWith("package net.vidageek.test;"));
    }

    @Test
    public void testThatAddsEndingCurlyBraces() {
        String code = new Fluid(ClassFixture.class, "net.vidageek.test").generateInterface();
        Assert.assertTrue("code should have ending curly braces", code.endsWith("}"));
    }
}
