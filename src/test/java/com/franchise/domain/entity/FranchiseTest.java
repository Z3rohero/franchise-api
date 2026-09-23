package com.franchise.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FranchiseTest {

    @Test
    void addsABranchAndLinksItBackToTheFranchise() {
        Franchise franchise = new Franchise("Fast Burger");
        Branch branch = new Branch("Downtown");

        franchise.addBranch(branch);

        assertThat(franchise.getBranches()).containsExactly(branch);
        assertThat(branch.getFranchise()).isEqualTo(franchise);
    }

    @Test
    void updatesNameOnlyWhenANewValueIsGiven() {
        Franchise franchise = new Franchise("Fast Burger");

        franchise.updateName(null);
        assertThat(franchise.getName()).isEqualTo("Fast Burger");

        franchise.updateName("Fast Burger Co.");
        assertThat(franchise.getName()).isEqualTo("Fast Burger Co.");
    }
}
