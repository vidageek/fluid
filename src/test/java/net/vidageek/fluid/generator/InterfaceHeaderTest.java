package net.vidageek.fluid.generator;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.ClassFixture;
import net.vidageek.fluid.fixtures.ClassFixtureNamed;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceHeaderTest {

    @Test
    public void testThatCreatesInterfaceDeclaration() {
        InterfaceHeader declaration = new InterfaceHeader(new InterfaceName(ClassFixture.class), ClassFixture.class);
        Assert.assertTrue(declaration.asString().contains("public interface ClassFixture<T>"));
    }

    @Test
    public void testThatCreatesInterfaceDeclarationConsideringNameAnnotation() {
        InterfaceHeader declaration = new InterfaceHeader(new InterfaceName(ClassFixtureNamed.class),
                ClassFixtureNamed.class);
        Assert.assertTrue(declaration.asString().contains("public interface AnyClassName<T>"));
    }

    @Test
    public void testThatAddsAnnotationWithOriginalClass() {
        InterfaceHeader declaration = new InterfaceHeader(new InterfaceName(ClassFixture.class), ClassFixture.class);
        Assert.assertTrue("Should have annotation FluidClass", declaration.asString().contains(
                "@net.vidageek.fluid.annotations.FluidClass(net.vidageek.fluid.fixtures.ClassFixture.class)"));
    }

    @Test
    public void testThatInterfaceExtendsFluidInterface() {
        InterfaceHeader declaration = new InterfaceHeader(new InterfaceName(ClassFixture.class), ClassFixture.class);
        Assert.assertTrue("Should extends FluidInterface", declaration.asString().contains(
                "extends net.vidageek.fluid.FluidInterface<T>"));
    }

}
