package uk.co.ticklethepanda.gallery.service.transformers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by czn83431 on 2017-05-03.
 */
public interface Transformer<I, O> {

    public O convert(I input);

    default List<O> convertList(List<I> inputs) {
        return inputs.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
