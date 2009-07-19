package net.vidageek.fluid.fixtures.output;

@net.vidageek.fluid.annotations.FluidClass(net.vidageek.fluid.fixtures.ClassFixtureNamed.class)
public interface AnyClassName<T> extends net.vidageek.fluid.FluidInterface<T> {

    @net.vidageek.fluid.annotations.FluidField("field")
    ClassFixture<T> withStringField(java.lang.String stringField);

    @net.vidageek.fluid.annotations.FluidField("integers")
    ClassFixture<T> addListOfInteger(java.lang.Integer listOfInteger);

}
