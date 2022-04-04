package com.ftm.javafp.validation;

import io.vavr.control.Validation;

public class User {

    private final Firstname firstname;
    private final Email email;

    private User(final Firstname firstname, final Email email) {
        this.firstname = firstname;
        this.email = email;
    }

    public static Validation<DomainError, User> of(final String firstname, final String email) {
        return Validation.combine(Firstname.of(firstname), Email.of(email))
                .ap(User::new)
                .mapError(errors -> new DomainError(errors.mkString(",")));
    }

    public static Validation<DomainError, User> of(final Firstname firstname, final Email email) {
        return Validation.combine(checkFirstName(firstname), checkEmail(email))
                .ap(User::new)
                .mapError(errors -> new DomainError(errors.mkString(",")));
    }

    private static Validation<DomainError, Email> checkEmail(final Email email) {
        return email == null
                ? Validation.invalid(new DomainError("email cannot be null"))
                : Validation.valid(email);
    }

    private static Validation<DomainError, Firstname> checkFirstName(final Firstname firstname) {
        return firstname == null
                ? Validation.invalid(new DomainError("firstname cannot be null"))
                : Validation.valid(firstname);
    }
}
