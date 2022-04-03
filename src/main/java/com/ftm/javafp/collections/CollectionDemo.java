package com.ftm.javafp.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionDemo<T extends Number> {

    public List<T> anImmutableCollectionJava8(T... args) {
        return Arrays.stream(args)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                                                      Collections::unmodifiableList));
    }

    public List<Number> anImmutableCollectionJava16(Number... args) {
        return Arrays.stream(args).toList();
    }

    public io.vavr.collection.List<Number> anImmutableCollectionWithVavr(Number... args) {
        return io.vavr.collection.List.of(args);
    }
}
