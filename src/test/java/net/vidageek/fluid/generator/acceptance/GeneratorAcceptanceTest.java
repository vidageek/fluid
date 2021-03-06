package net.vidageek.fluid.generator.acceptance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.ClassFixture;
import net.vidageek.fluid.fixtures.ClassFixtureNamed;
import net.vidageek.fluid.fixtures.ComplexFixture;
import net.vidageek.fluid.fixtures.modelo.Parent;
import net.vidageek.fluid.generator.Fluid;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class GeneratorAcceptanceTest {

    @Test
    public void testThatGeneratorWorksWithoutNameAnnotations() throws Throwable {
        String code = new Fluid(ClassFixture.class, "net.vidageek.fluid.fixtures.output")
                                                                                         .generateInterface(new HashSet<Class<?>>());

        String comparisonCode = readFileFor(net.vidageek.fluid.fixtures.output.ClassFixture.class);

        Assert.assertEquals("Fluid Generator isn't working without name annotations", comparisonCode, code);
    }

    @Test
    public void testThatGeneratorWorksWithNameAnnotations() throws Throwable {
        String code = new Fluid(ClassFixtureNamed.class, "net.vidageek.fluid.fixtures.output")
                                                                                              .generateInterface(new HashSet<Class<?>>());

        String comparisonCode = readFileFor(net.vidageek.fluid.fixtures.output.AnyClassName.class);

        Assert.assertEquals("Fluid Generator isn't working with name annotations", comparisonCode, code);
    }

    @Test
    public void testThatGeneratorWorksWithDataTypeAnnotations() throws Throwable {
        String code = new Fluid(net.vidageek.fluid.fixtures.ClassFixtureTyped.class,
                "net.vidageek.fluid.fixtures.output").generateInterface(new HashSet<Class<?>>());

        String comparisonCode = readFileFor(net.vidageek.fluid.fixtures.output.ClassFixtureTyped.class);

        Assert.assertEquals("Fluid Generator isn't working with data type annotations", comparisonCode, code);
    }

    @Test
    public void testThatGeneratorWorksWithDifferentTypes() throws Throwable {
        HashSet<Class<?>> types = new HashSet<Class<?>>();
        types.add(ComplexFixture.class);
        types.add(ClassFixture.class);

        String code = new Fluid(ComplexFixture.class, "net.vidageek.fluid.fixtures.output").generateInterface(types);

        String comparisonCode = readFileFor(net.vidageek.fluid.fixtures.output.ComplexFixture.class);

        Assert.assertEquals("Fluid Generator isn't working for complex types", comparisonCode, code);
    }

    @Test
    public void testThatTestInterfacesAreCorrect() throws Throwable {
        String code = new Fluid(Parent.class, "net.vidageek.fluid.fixtures.modelo.output")
                                                                                          .generateInterface(new HashSet<Class<?>>());
        String comparisonCode = readFileFor(net.vidageek.fluid.fixtures.modelo.output.Parent.class);

        Assert.assertEquals("Some interface was broken. ", comparisonCode, code);
    }

    private String readFileFor(final Class<?> type) throws FileNotFoundException {
        String pathToClass = GeneratorAcceptanceTest.class.getResource(".").toString();

        String baseDir = pathToClass.substring(0, pathToClass.indexOf("/target"));

        Scanner scanner = new Scanner(new File(baseDir.substring(5) + "/src/test/java/"
                + type.getName().replaceAll("\\.", "/") + ".java"));

        String comparisonCode = scanner.useDelimiter("$$").next();
        return comparisonCode;
    }

}
