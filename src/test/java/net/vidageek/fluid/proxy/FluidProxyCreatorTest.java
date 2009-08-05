package net.vidageek.fluid.proxy;

import java.lang.reflect.Type;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.modelo.output.Parent;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class FluidProxyCreatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNull() {
        new FluidProxyCreator<Object>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNotAnnotatedWithFluidClass() {
        new FluidProxyCreator<Type>(Type.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatThrowsExceptionIfClassIsNotInterface() {
        new FluidProxyCreator<String>(String.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testThatProxyReturnsSameInstance() {
        Parent<Parent> proxy = new FluidProxyCreator<Parent>(Parent.class).createProxy(null);
        Assert.assertTrue(proxy.addIntegers(2) == proxy.withField(""));

    }
}
