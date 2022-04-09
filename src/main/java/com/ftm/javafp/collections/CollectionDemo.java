package com.ftm.javafp.collections;

import io.vavr.Tuple2;
import org.apache.commons.collections4.list.UnmodifiableList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionDemo<T extends Number> {

    public List<T> anImmutableCollectionJava8(final T... args) {
        return new UnmodifiableList<>(new ArrayList<>(Arrays.asList(args)));
    }

    public List<T> anImmutableCollectionJava8(final Stream<T> args) {
        return args.collect(Collectors.collectingAndThen(Collectors.toList(),
                                                         Collections::unmodifiableList));
    }

    public List<T> anImmutableCollectionJava9(final T... args) {
        return List.of(args);
    }

    public List<T> anImmutableCollectionJava10(final Stream<T> args) {
        return args.collect(Collectors.toUnmodifiableList());
    }


    public List<T> anImmutableCollectionJava16(final Stream<T> args) {
        return args.toList();
    }

    public io.vavr.collection.List<T> anImmutableCollectionWithVavr(final T... args) {
        return io.vavr.collection.List.of(args);
    }

    public io.vavr.collection.List<T> anImmutableCollectionWithVavr(final Stream<T> args) {
        return io.vavr.collection.Stream.ofAll(args).toList();
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
