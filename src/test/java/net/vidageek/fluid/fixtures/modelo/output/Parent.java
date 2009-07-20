package net.vidageek.fluid.fixtures.modelo.output;

@net.vidageek.fluid.annotations.FluidClass(net.vidageek.fluid.fixtures.modelo.Parent.class)
public interface Parent<T> extends net.vidageek.fluid.FluidInterface<T> {

    @net.vidageek.fluid.annotations.FluidField("field")
    Parent<T> withField(java.lang.String field);

    @net.vidageek.fluid.annotations.FluidField("integers")
    Parent<T> addIntegers(java.lang.Integer integers);

}
