package com.ftm.javafp.monads.tries;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

class TryDemoTest {

    private final TryDemo tryDemo = new TryDemo();

    @Test
    void should_throw_exception_when_invalid_input() {
        // Given
        final String invalidInput = "http://|😱😱😱";

        // When
        final var throwable = catchThrowable(() -> tryDemo.convertToUri().apply(invalidInput));

        // Then
        then(throwable).isInstanceOf(URISyntaxException.class);
    }
}
