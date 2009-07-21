package net.vidageek.fluid.generator;

import java.util.HashSet;

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
                new InterfaceName(ClassFixture.class), new HashSet<Class<?>>()).asString().contains(
                "ClassFixture<T> withField(java.lang.String field);"));
        Assert.assertTrue("Should have method for field integers", new InterfaceMethodList(ClassFixture.class,
                new InterfaceName(ClassFixture.class), new HashSet<Class<?>>()).asString().contains(
                "ClassFixture<T> addIntegers(java.lang.Integer integers);"));
    }

}
