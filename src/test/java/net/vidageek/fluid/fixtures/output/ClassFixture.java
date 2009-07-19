package net.vidageek.fluid.fixtures.output;

@net.vidageek.fluid.annotations.FluidClass(net.vidageek.fluid.fixtures.ClassFixture.class)
public interface ClassFixture<T> extends net.vidageek.fluid.FluidInterface<T> {

    @net.vidageek.fluid.annotations.FluidField("field")
    ClassFixture<T> withField(java.lang.String field);

    @net.vidageek.fluid.annotations.FluidField("integers")
    ClassFixture<T> addIntegers(java.lang.Integer integers);

}
