package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.franchise.application.port.in.TopStockProduct;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.entity.Franchise;
import com.franchise.domain.entity.Product;
import com.franchise.domain.exception.FranchiseNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class GetTopStockProductPerBranchUseCaseTest {

    private final FranchiseRepositoryPort franchiseRepository = mock(FranchiseRepositoryPort.class);
    private final GetTopStockProductPerBranchUseCase useCase = new GetTopStockProductPerBranchUseCase(franchiseRepository);

    @Test
    void returnsTheProductWithTheMostStockForEachBranch() {
        UUID franchiseId = UUID.randomUUID();
        Franchise franchise = new Franchise("Fast Burger");

        Branch downtown = new Branch("Downtown");
        downtown.addProduct(new Product("Fries", 5));
        downtown.addProduct(new Product("Burger", 20));
        franchise.addBranch(downtown);

        Branch uptown = new Branch("Uptown");
        uptown.addProduct(new Product("Soda", 100));
        franchise.addBranch(uptown);

        Branch withoutProducts = new Branch("Mall");
        franchise.addBranch(withoutProducts);

        when(franchiseRepository.findByIdWithBranchesAndProducts(franchiseId)).thenReturn(Optional.of(franchise));

        List<TopStockProduct> result = useCase.execute(franchiseId);

        assertThat(result).hasSize(2);
        assertThat(result)
                .filteredOn(top -> top.branchName().equals("Downtown"))
                .singleElement()
                .satisfies(top -> assertThat(top.productName()).isEqualTo("Burger"));
        assertThat(result)
                .filteredOn(top -> top.branchName().equals("Uptown"))
                .singleElement()
                .satisfies(top -> assertThat(top.productName()).isEqualTo("Soda"));
    }

    @Test
    void failsWhenTheFranchiseDoesNotExist() {
        UUID franchiseId = UUID.randomUUID();
        when(franchiseRepository.findByIdWithBranchesAndProducts(franchiseId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(franchiseId)).isInstanceOf(FranchiseNotFoundException.class);
    }
}
