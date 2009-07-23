package net.vidageek.fluid.fixtures.output;

@net.vidageek.fluid.annotations.FluidClass(net.vidageek.fluid.fixtures.ClassFixtureTyped.class)
public interface ClassFixtureTyped<T> extends net.vidageek.fluid.FluidInterface<T> {

    @net.vidageek.fluid.annotations.FluidField("field")
    ClassFixtureTyped<T> withField(java.math.BigInteger field);

}