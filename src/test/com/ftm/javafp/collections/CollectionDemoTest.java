package com.ftm.javafp.collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.BDDAssertions.then;

class CollectionDemoTest {

    private final CollectionDemo demo = new CollectionDemo();

    @Test
    void assertAllJavaCollections() {
        should_throw_exception_when_adding_a_value_to_an_immutable_list(demo::anImmutableCollectionJava8);
        should_throw_exception_when_adding_a_value_to_an_immutable_list(demo::anImmutableCollectionJava16);
    }

    void should_throw_exception_when_adding_a_value_to_an_immutable_list(final Function<Number[], List<Number>> initializer) {
        // Given
        final Integer[] numbers = new Integer[]{1, 2, 3};

        // When
        final List<Number> actual = initializer.apply(numbers);

        // Then
        then(Assertions.catchThrowable(() -> actual.add(4)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void should_create_a_new_object_when_adding_a_value_to_an_immutable_list() {
        // Given
        final Integer[] numbers = new Integer[]{1, 2, 3};

        // When
        final io.vavr.collection.List<Number> actual = demo.anImmutableCollectionWithVavr(numbers);

        // Then
        final var newList = actual.append(4);
        then(actual).isNotEqualTo(newList);
        System.out.println(actual);
        System.out.println(newList);
    }
}
