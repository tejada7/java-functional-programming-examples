package com.ftm.javafp.monads.either;

public class ProductPriceAdapterImperative implements ProductPricePersistencePort {

    private final ProductDataSource dataSource = new ProductDataSource();

    @Override
    public ProductPrice getProductPrice(final SearchCriteria searchCriteria) {
        if (isInvalid(searchCriteria)) {
            throw new IllegalArgumentException("Invalid search criteria.");
        }
        return dataSource.get(searchCriteria);
    }

    private boolean isInvalid(final SearchCriteria searchCriteria) {
        return searchCriteria == null;
    }

    private static class ProductDataSource {

        private ProductPrice get(final SearchCriteria searchCriteria) {
            final var reference = searchCriteria.productReference().reference();
            if (reference.equals("123") || reference.equals("456")) {
                return validPrice(reference);
            } else if (reference.equals("789")) {
                throw new IllegalArgumentException("Product out of stock");
            }
            throw new IllegalArgumentException("Product price not found");
        }

        private ProductPrice validPrice(final String reference) {
            return new ProductPrice(new ProductReference(reference), new Price("EUR", 5095, 2));
        }
    }
}
