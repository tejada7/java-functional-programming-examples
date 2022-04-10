package com.ftm.javafp.monads.either;

import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.function.Function;
import java.util.function.Supplier;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;
import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class ProductPriceAdapterFunctional implements ProductPricePersistencePort2 {

    private final ProductDataSource dataSource = new ProductDataSource();

    @Override
    public Either<PriceError, ProductPrice> getProductPrice(final SearchCriteria searchCriteria) {
        return Option.of(searchCriteria)
                .toEither(supplyLazyInvalidSearchCriteriaError())
                .flatMap(dataSource::get);
    }

    private Supplier<PriceError> supplyLazyInvalidSearchCriteriaError() {
        return () -> new PriceError("Invalid search criteria.");
    }

    private static class ProductDataSource {

        public Either<PriceError, ProductPrice> get(final SearchCriteria searchCriteria) {
            return Match(searchCriteria.productReference().reference()).of(
                    Case($(isIn("123", "456")), validProductPrice()),
                    Case($("789"), () -> left(new PriceError("Product out of stock"))),
                    Case($(), () -> left(new PriceError("product price not found"))));
        }

        private Function<String, Either<PriceError, ProductPrice>> validProductPrice() {
            return ref -> right(new ProductPrice(new ProductReference(ref), new Price("EUR", 5095, 2)));
        }
    }
}
