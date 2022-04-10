package com.ftm.javafp.collections;

import io.vavr.Tuple2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public Map<Integer, String> zipWithIndex(final String[] args) {
        return IntStream
                .range(0, args.length)
                .mapToObj(index -> Map.entry(index, args[index]))
                .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, String> zipWithIndexUsingVavr(final String[] args) {
        return io.vavr.collection.List.of(args)
                .zipWithIndex()
                .toJavaMap(HashMap::new, Tuple2::_2, Tuple2::_1);
//                .toMap(Tuple2::_2, Tuple2::_1)
//                .toJavaMap();
    }
}
