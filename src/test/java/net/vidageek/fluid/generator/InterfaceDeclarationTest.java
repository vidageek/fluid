package net.vidageek.fluid.generator;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.ClassFixture;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class InterfaceDeclarationTest {

    @Test
    public void testThatCreatesHeader() {
        InterfaceDeclaration declaration = new InterfaceDeclaration(ClassFixture.class);
        Assert.assertEquals("public interface ClassFixture {", declaration.asString());
    }

}
