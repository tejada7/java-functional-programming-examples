package com.ftm.javafp.monads.either;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenIllegalArgumentException;

class ProductPriceAdapterImperativeTest {

    private final ProductPriceAdapterImperative adapter = new ProductPriceAdapterImperative();

    @Test
    void should_return_product_price_for_valid_references() {
        // Given
        final var searchCriteria = aValidSearchCriteria();
        final var expected = aValidProductPrice();

        // When
        final var productPrice = adapter.getProductPrice(searchCriteria);

        // Then
        then(productPrice).isEqualTo(expected);
    }

    @Test
    void should_throw_exception_when_product_price_not_found() {
        // Given
        final var searchCriteria = aValidSearchCriteriaWithUnknownReference();

        // When
        final ThrowingCallable throwable = () -> adapter.getProductPrice(searchCriteria);

        // Then
        thenIllegalArgumentException().isThrownBy(throwable);
    }

    @Test
    void should_throw_exception_when_product_out_of_stock() {
        // Given
        final var searchCriteria = aValidSearchCriteriaWithOutOfStockReference();

        // When
        final ThrowingCallable throwable = () -> adapter.getProductPrice(searchCriteria);

        // Then
        thenIllegalArgumentException().isThrownBy(throwable);
    }

    @Test
    void should_throw_exception_when_invalid_search_criteria() {
        // Given
        final SearchCriteria invalidSearchCriteria = null;

        // When
        final ThrowingCallable throwable = () -> adapter.getProductPrice(invalidSearchCriteria);

        // Then
        thenIllegalArgumentException().isThrownBy(throwable);
    }

    private SearchCriteria aValidSearchCriteriaWithOutOfStockReference() {
        return new SearchCriteria(new ProductReference("789"));
    }

    private ProductPrice aValidProductPrice() {
        return new ProductPrice(aValidProductReference(), aValidPrice());
    }

    private Price aValidPrice() {
        return new Price("EUR", 5095, 2);
    }

    private SearchCriteria aValidSearchCriteria() {
        return new SearchCriteria(aValidProductReference());
    }

    private SearchCriteria aValidSearchCriteriaWithUnknownReference() {
        return new SearchCriteria(anUnknownProductReference());
    }

    private ProductReference anUnknownProductReference() {
        return new ProductReference("???");
    }

    private ProductReference aValidProductReference() {
        return new ProductReference("123");
    }
}
