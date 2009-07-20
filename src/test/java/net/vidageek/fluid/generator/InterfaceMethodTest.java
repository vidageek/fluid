package net.vidageek.fluid.generator;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.ClassFixture;
import net.vidageek.fluid.fixtures.ClassFixtureNamed;
import net.vidageek.mirror.dsl.Mirror;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceMethodTest {

    @Test
    public void testThatGeneratesMethodForFieldField() {
        String code = new InterfaceMethod(new Mirror().on(ClassFixture.class).reflect().field("field"),
                new InterfaceName(ClassFixture.class)).asString();

        Assert.assertTrue(code.contains("ClassFixture<T> withField(java.lang.String field);"));
    }

    @Test
    public void testThatGeneratesMethodForAnnotatedField() {
        String code = new InterfaceMethod(new Mirror().on(ClassFixtureNamed.class).reflect().field("field"),
                new InterfaceName(ClassFixtureNamed.class)).asString();
        Assert.assertTrue(code.contains("AnyClassName<T> withStringField(java.lang.String stringField);"));
    }

    @Test
    public void testThatGeneratesMethodForFieldIntegers() {
        String code = new InterfaceMethod(new Mirror().on(ClassFixture.class).reflect().field("integers"),
                new InterfaceName(ClassFixture.class)).asString();

        Assert.assertTrue(code.contains("ClassFixture<T> addIntegers(java.lang.Integer integers);"));
    }

    @Test
    public void testThatGeneratesAnnotationWithFieldName() {
        String code = new InterfaceMethod(new Mirror().on(ClassFixture.class).reflect().field("integers"),
                new InterfaceName(ClassFixture.class)).asString();

        Assert.assertTrue("Should have annotation with field name",
                code.contains("@net.vidageek.fluid.annotations.FluidField(\"integers\")"));
    }

    public static void main(final String[] args) {
        System.out.println(new Fluid(ClassFixture.class, "net.vidageek.fluid").generateInterface());
    }
}
