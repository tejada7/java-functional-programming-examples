package com.ftm.javafp.validation;

import io.vavr.control.Option;
import io.vavr.control.Validation;

public class Firstname {
    private final String value;

    private Firstname(final String value) {
        this.value = value;
    }

    public static Validation<DomainError, Firstname> of(final String value) {
        return Option.of(value)
                .map(com.ftm.javafp.validation.Firstname::new)
                .toValidation(() -> new DomainError("firstname cannot be missing"));
    }
}
