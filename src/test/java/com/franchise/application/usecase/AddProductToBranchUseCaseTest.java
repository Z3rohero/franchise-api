package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.entity.Product;
import com.franchise.domain.exception.BranchNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class AddProductToBranchUseCaseTest {

    private final BranchRepositoryPort branchRepository = mock(BranchRepositoryPort.class);
    private final ProductRepositoryPort productRepository = mock(ProductRepositoryPort.class);
    private final AddProductToBranchUseCase useCase = new AddProductToBranchUseCase(branchRepository, productRepository);

    @Test
    void addsAProductToAnExistingBranch() {
        UUID branchId = UUID.randomUUID();
        Branch branch = new Branch("Downtown");
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(branch));
        when(productRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Product product = useCase.execute(branchId, "Coca-Cola 400ml", 10);

        assertThat(product.getName()).isEqualTo("Coca-Cola 400ml");
        assertThat(product.getStock()).isEqualTo(10);
        assertThat(product.getBranch()).isEqualTo(branch);
        assertThat(branch.getProducts()).containsExactly(product);
    }

    @Test
    void failsWhenTheBranchDoesNotExist() {
        UUID branchId = UUID.randomUUID();
        when(branchRepository.findById(branchId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(branchId, "Coca-Cola 400ml", 10))
                .isInstanceOf(BranchNotFoundException.class);
    }
}
