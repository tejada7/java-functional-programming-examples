package com.ftm.javafp.monads.either;

public record Price(String currency, long unscaledAmount, int scale) {
}
