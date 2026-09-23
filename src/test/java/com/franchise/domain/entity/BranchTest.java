package com.franchise.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BranchTest {

    @Test
    void addsAProductAndLinksItBackToTheBranch() {
        Branch branch = new Branch("Downtown");
        Product product = new Product("Coca-Cola 400ml", 10);

        branch.addProduct(product);

        assertThat(branch.getProducts()).containsExactly(product);
        assertThat(product.getBranch()).isEqualTo(branch);
    }

    @Test
    void updatesNameOnlyWhenANewValueIsGiven() {
        Branch branch = new Branch("Downtown");

        branch.updateName(null);
        assertThat(branch.getName()).isEqualTo("Downtown");

        branch.updateName("Uptown");
        assertThat(branch.getName()).isEqualTo("Uptown");
    }

    @Test
    void assignsTheFranchiseItBelongsTo() {
        Branch branch = new Branch("Downtown");
        Franchise franchise = new Franchise("Fast Burger");

        branch.assignFranchise(franchise);

        assertThat(branch.getFranchise()).isEqualTo(franchise);
    }
}
