package net.vidageek.fluid.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import net.vidageek.fluid.proxy.converter.BigDecimalToStringConverter;
import net.vidageek.fluid.proxy.converter.BigIntegerToStringConverter;
import net.vidageek.fluid.proxy.converter.DataConverterManager;
import net.vidageek.fluid.proxy.converter.EnumToStringConverter;
import net.vidageek.fluid.proxy.converter.NumberToStringConverter;
import net.vidageek.fluid.proxy.handler.BuildHandler;
import net.vidageek.fluid.proxy.handler.DifferentReturnListProxyHandler;
import net.vidageek.fluid.proxy.handler.DifferentReturnProxyHandler;
import net.vidageek.fluid.proxy.handler.GetInstanceHandler;
import net.vidageek.fluid.proxy.handler.MethodHandler;
import net.vidageek.fluid.proxy.handler.SameReturnListProxyHandler;
import net.vidageek.fluid.proxy.handler.SameReturnProxyHandler;

/**
 * @author jonasabreu
 * 
 */
final public class FluidInvocationHandler implements InvocationHandler {

    private final List<MethodHandler> handlers;

    public FluidInvocationHandler(final Object parentProxy, final Object watchedModel,
            final List<MethodHandler> userHandlers, final DataConverterManager manager) {

        registerDefaultConverters(manager);

        handlers = new ArrayList<MethodHandler>();

        handlers.addAll(userHandlers);

        handlers.add(new GetInstanceHandler(watchedModel));
        handlers.add(new BuildHandler(parentProxy));
        handlers.add(new SameReturnProxyHandler(watchedModel, manager));
        handlers.add(new DifferentReturnProxyHandler(watchedModel));
        handlers.add(new SameReturnListProxyHandler(watchedModel));
        handlers.add(new DifferentReturnListProxyHandler(watchedModel));
    }

    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        for (MethodHandler handler : handlers) {
            if (handler.accept(method)) {
                return handler.handle(proxy, method, args);
            }
        }
        throw new IllegalStateException("found method that could not be handled.");
    }

    private void registerDefaultConverters(final DataConverterManager manager) {
        manager.registerConverter(Integer.class, String.class, new NumberToStringConverter());
        manager.registerConverter(Long.class, String.class, new NumberToStringConverter());
        manager.registerConverter(BigDecimal.class, String.class, new BigDecimalToStringConverter());
        manager.registerConverter(BigInteger.class, String.class, new BigIntegerToStringConverter());
        manager.registerConverter(Enum.class, String.class, new EnumToStringConverter());
    }

}
