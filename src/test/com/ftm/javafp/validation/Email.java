package com.ftm.javafp.validation;

import io.vavr.control.Option;
import io.vavr.control.Validation;

import java.util.regex.Pattern;

public class Email {
    String value;

    private Email(final String value) {
        this.value = value;
    }

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

    public static Validation<DomainError, Email> of(final String value) {
        return Validation.combine(checkNotNull(value), checkPattern(value))
                .ap((v1, v2) -> new Email(v1))
                .mapError(errors -> new DomainError(errors.mkString(",")));
    }

    private static Validation<DomainError, String> checkPattern(final String email) {
        return Option.of(email)
                .filter(it -> EMAIL_PATTERN.matcher(it).matches())
                .toValidation(() -> new DomainError("email does not respect pattern."));
    }

    private static Validation<DomainError, String> checkNotNull(final String email) {
        return email == null
                ? Validation.invalid(new DomainError("email cannot be null"))
                : Validation.valid(email);
    }
}
