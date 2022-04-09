package com.ftm.javafp.collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

class CollectionDemoTest {

    private final CollectionDemo demo = new CollectionDemo<Integer>();

    @Test
    void assertAllJavaCollections_fromStream() {
        should_throw_exception_when_adding_a_value_to_an_immutable_list(demo::anImmutableCollectionJava8);
        should_throw_exception_when_adding_a_value_to_an_immutable_list(demo::anImmutableCollectionJava10);
        should_throw_exception_when_adding_a_value_to_an_immutable_list(demo::anImmutableCollectionJava16);
    }

    void should_throw_exception_when_adding_a_value_to_an_immutable_list(final Function<Stream<Integer>, List<Integer>> initializer) {
        // Given
        final Stream<Integer> numbers = Stream.of(1, 2, 3);

        // When
        final List<Integer> actual = initializer.apply(numbers);

        // Then
        then(Assertions.catchThrowable(() -> actual.add(4)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void should_create_a_new_object_when_adding_a_value_to_an_immutable_list() {
        // Given
        final Stream<Integer> numbers = Stream.of(1, 2, 3);

        // When
        final io.vavr.collection.List<Integer> actual = demo.anImmutableCollectionWithVavr(numbers);

        // Then
        final var newList = actual.append(4);
        then(actual).isNotEqualTo(newList);
        System.out.println(actual);
        System.out.println(newList);
    }

    // List("a", "b", "c") -> Map[(0, "a"), (1, "b"), (2, "c")]
    @Test
    void should_zip_index_to_element() {
        // Given
        final String[] args = {"a", "b", "c"};

        // When
        final Map<Integer, String> elementByIndex = demo.zipWithIndex(args);

        // Then
        IntStream.range(0, args.length)
                .forEach(index -> then(elementByIndex.get(index)).isEqualTo(args[index]));
    }
}
