package com.ftm.javafp.monads.either;

@FunctionalInterface
public interface ProductPricePersistencePort {

    ProductPrice getProductPrice(final SearchCriteria searchCriteria) throws IllegalArgumentException;
}
