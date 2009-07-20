package net.vidageek.fluid.fixtures.output;

@net.vidageek.fluid.annotations.FluidClass(net.vidageek.fluid.fixtures.ComplexFixture.class)
public interface ComplexFixture<T> extends net.vidageek.fluid.FluidInterface<T> {

    @net.vidageek.fluid.annotations.FluidField("fixture")
    net.vidageek.fluid.fixtures.output.ClassFixture<ComplexFixture<T>> withClassFixture();

}
