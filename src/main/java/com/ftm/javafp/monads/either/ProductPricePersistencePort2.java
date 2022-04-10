package com.ftm.javafp.monads.either;

import io.vavr.control.Either;

@FunctionalInterface
public interface ProductPricePersistencePort2 {

    Either<PriceError, ProductPrice> getProductPrice(final SearchCriteria searchCriteria);
}
