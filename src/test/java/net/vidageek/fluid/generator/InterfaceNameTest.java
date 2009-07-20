package net.vidageek.fluid.generator;

import net.vidageek.fluid.fixtures.ClassFixture;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceNameTest {

    @Test
    public void testThatGeneratesInterfaceName() {
        Assert.assertEquals("ClassFixture<T>", new InterfaceName(ClassFixture.class).asString());
    }

}
