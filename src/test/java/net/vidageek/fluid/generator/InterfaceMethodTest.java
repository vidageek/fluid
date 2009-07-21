package net.vidageek.fluid.generator;

import java.util.HashSet;

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
                new InterfaceName(ClassFixture.class), new HashSet<Class<?>>()).asString();

        Assert.assertTrue(code.contains("ClassFixture<T> withField(java.lang.String field);"));
    }

    @Test
    public void testThatGeneratesMethodForAnnotatedField() {
        String code = new InterfaceMethod(new Mirror().on(ClassFixtureNamed.class).reflect().field("field"),
                new InterfaceName(ClassFixtureNamed.class), new HashSet<Class<?>>()).asString();
        Assert.assertTrue(code.contains("AnyClassName<T> withStringField(java.lang.String stringField);"));
    }

    @Test
    public void testThatGeneratesMethodForFieldIntegers() {
        String code = new InterfaceMethod(new Mirror().on(ClassFixture.class).reflect().field("integers"),
                new InterfaceName(ClassFixture.class), new HashSet<Class<?>>()).asString();

        Assert.assertTrue(code.contains("ClassFixture<T> addIntegers(java.lang.Integer integers);"));
    }

    @Test
    public void testThatGeneratesAnnotationWithFieldName() {
        String code = new InterfaceMethod(new Mirror().on(ClassFixture.class).reflect().field("integers"),
                new InterfaceName(ClassFixture.class), new HashSet<Class<?>>()).asString();

        Assert.assertTrue("Should have annotation with field name",
                code.contains("@net.vidageek.fluid.annotations.FluidField(\"integers\")"));
    }

}
