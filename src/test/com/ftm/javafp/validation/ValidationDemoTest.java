package com.ftm.javafp.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ValidationDemoTest {


    @Test
    void should_return_errors_when_invalid_firstname_and_email() {
        // Given
        final String invalidFirstname = null;
        final var invalidEmail = "invalidEmail";

        // When
        final var user = User.of(invalidFirstname, invalidEmail);

        // Then
        assertThat(user).isInvalid();
        System.out.println(user.getError());
    }
}
