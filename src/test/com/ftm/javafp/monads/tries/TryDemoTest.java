package com.ftm.javafp.monads.tries;

import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class TryDemoTest {

    private final TryDemo tryDemo = new TryDemo();

    @Test
    void should_throw_exception_when_invalid_input() {
        // Given
        final String invalidInput = "http://|😱😱😱";

        // When
        final var actual = tryDemo.convertToUri().apply(invalidInput);

        // Then
        assertThat(actual).isFailure();
    }
}
