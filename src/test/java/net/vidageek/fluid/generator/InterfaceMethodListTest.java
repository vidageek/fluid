package net.vidageek.fluid.generator;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.ClassFixture;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceMethodListTest {

    @Test
    public void testThatGeneratesMethods() {
        Assert.assertTrue("Should have method for field field", new InterfaceMethodList(ClassFixture.class,
                new InterfaceName(ClassFixture.class)).asString().contains(
                "ClassFixture<T> withField(java.lang.String field);"));
        Assert.assertTrue("Should have method for field integers", new InterfaceMethodList(ClassFixture.class,
                new InterfaceName(ClassFixture.class)).asString().contains(
                "ClassFixture<T> addIntegers(java.lang.Integer integers);"));
    }

}
