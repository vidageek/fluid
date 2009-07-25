package net.vidageek.fluid.proxy.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonasabreu
 * 
 */
@SuppressWarnings("unchecked")
final public class DataConverterManager {

    private final List<Converter> converterList;

    public DataConverterManager() {
        converterList = new ArrayList<Converter>();
    }

    public <F, T> void registerConverter(final Class<? extends F> from, final Class<? extends T> to,
            final DataConverter<F, T> converter) {
        converterList.add(new Converter(from, to, converter));

    }

    public Object convert(final Object object, final Class<?> type) {
        return getConverter(object, type).convert(object);
    }

    private DataConverter getConverter(final Object from, final Class<?> to) {
        DataConverter converter = new NoOpConverter();
        for (Converter c : converterList) {
            if (c.accepts(from.getClass(), to)) {
                converter = c;
            }
        }
        return converter;
    }

    private static class Converter implements DataConverter {

        private final DataConverter converter;
        private final Class<?> from;
        private final Class<?> to;

        public Converter(final Class<?> from, final Class<?> to, final DataConverter converter) {
            this.from = from;
            this.to = to;
            this.converter = converter;
        }

        public Object convert(final Object object) {
            return converter.convert(object);
        }

        public boolean accepts(final Class<?> from, final Class<?> to) {
            return this.from.isAssignableFrom(from) && this.to.isAssignableFrom(to);
        }
    }

}
