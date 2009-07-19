package net.vidageek.fluid.generator.acceptance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import junit.framework.Assert;
import net.vidageek.fluid.fixtures.ClassFixture;
import net.vidageek.fluid.fixtures.ClassFixtureNamed;
import net.vidageek.fluid.generator.Fluid;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class GeneratorAcceptanceTest {

    @Test
    public void testThatGeneratorWorksWithoutNameAnnotations() throws Throwable {
        String code = new Fluid(ClassFixture.class, "net.vidageek.fluid.fixtures.output").generateInterface();

        String comparisonCode = readFileFor(net.vidageek.fluid.fixtures.output.ClassFixture.class);

        Assert.assertEquals("Fluid Generator isn't working without name annotations", comparisonCode, code);
    }

    @Test
    public void testThatGeneratorWorksWithNameAnnotations() throws Throwable {
        String code = new Fluid(ClassFixtureNamed.class, "net.vidageek.fluid.fixtures.output").generateInterface();

        String comparisonCode = readFileFor(net.vidageek.fluid.fixtures.output.AnyClassName.class);

        Assert.assertEquals("Fluid Generator isn't working with name annotations", comparisonCode, code);
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
