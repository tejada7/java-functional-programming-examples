package com.ftm.javafp.monads.tries;

import java.net.URI;
import java.util.function.Function;

import static io.vavr.API.unchecked;

public class TryDemo {

    public Function<String, URI> convertToUri() {
        return unchecked(string -> new URI(string));
    }
}
