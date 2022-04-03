package com.ftm.javafp.monads.tries;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.function.Function;

@Slf4j
public class TryDemo {

    public Function<String, Try<URI>> convertToUri() { // Success, Failure
        return string -> Try.of(() -> new URI(string))
                .onFailure(exception -> log.error("could not convert"))
                .onSuccess(uri -> log.info("Successfully converted to URI"));
    }
}
