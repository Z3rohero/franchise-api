package com.franchise.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void createsAProductWithNameAndStock() {
        Product product = new Product("Coca-Cola 400ml", 10);

        assertThat(product.getName()).isEqualTo("Coca-Cola 400ml");
        assertThat(product.getStock()).isEqualTo(10);
    }

    @Test
    void failsWhenInitialStockIsNegative() {
        assertThatThrownBy(() -> new Product("Coca-Cola 400ml", -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updatesStockToANewNonNegativeValue() {
        Product product = new Product("Coca-Cola 400ml", 10);

        product.updateStock(25);

        assertThat(product.getStock()).isEqualTo(25);
    }

    @Test
    void failsToUpdateStockWithANegativeValue() {
        Product product = new Product("Coca-Cola 400ml", 10);

        assertThatThrownBy(() -> product.updateStock(-5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updatesNameOnlyWhenANewValueIsGiven() {
        Product product = new Product("Coca-Cola 400ml", 10);

        product.updateName(null);
        assertThat(product.getName()).isEqualTo("Coca-Cola 400ml");

        product.updateName("Coca-Cola 500ml");
        assertThat(product.getName()).isEqualTo("Coca-Cola 500ml");
    }

    @Test
    void assignsTheBranchItBelongsTo() {
        Product product = new Product("Coca-Cola 400ml", 10);
        Branch branch = new Branch("Downtown");

        product.assignBranch(branch);

        assertThat(product.getBranch()).isEqualTo(branch);
    }
}
