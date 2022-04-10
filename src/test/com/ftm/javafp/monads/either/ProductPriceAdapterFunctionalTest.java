package com.ftm.javafp.monads.either;

import org.junit.jupiter.api.Test;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class ProductPriceAdapterFunctionalTest {

    private final ProductPriceAdapterFunctional adapter = new ProductPriceAdapterFunctional();

    @Test
    void should_return_product_price_for_valid_references() {
        // Given
        final var searchCriteria = aValidSearchCriteria();
        final var expected = aValidProductPrice();

        // When
        final var eitherProductPriceOrPriceError = adapter.getProductPrice(searchCriteria);

        // Then
        assertThat(eitherProductPriceOrPriceError).isRight();
        assertThat(eitherProductPriceOrPriceError).containsOnRight(expected);
    }

    @Test
    void should_return_left_error_when_invalid_search_criteria() {
        // Given
        final SearchCriteria invalidSearchCriteria = null;

        // When
        final var eitherPriceOrError = adapter.getProductPrice(invalidSearchCriteria);

        // Then
        assertThat(eitherPriceOrError).isLeft();
        assertThat(eitherPriceOrError).containsOnLeft(new PriceError("Invalid search criteria."));
    }

    @Test
    void should_throw_exception_when_product_price_not_found() {
        // Given
        final var searchCriteria = aValidSearchCriteriaWithUnknownReference();

        // When
        final var eitherPriceOrError = adapter.getProductPrice(searchCriteria);

        // Then
        assertThat(eitherPriceOrError).isLeft();
        assertThat(eitherPriceOrError).containsOnLeft(new PriceError("product price not found"));
    }

    @Test
    void should_return_error_when_product_out_of_stock() {
        // Given
        final var searchCriteria = aValidSearchCriteriaWithOutOfStockProduct();

        // When
        final var eitherPriceOrError = adapter.getProductPrice(searchCriteria);

        // Then
        assertThat(eitherPriceOrError).containsOnLeft(new PriceError("Product out of stock"));
    }

    private SearchCriteria aValidSearchCriteriaWithOutOfStockProduct() {
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
