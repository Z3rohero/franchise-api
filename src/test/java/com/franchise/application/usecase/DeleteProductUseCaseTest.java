package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.exception.ProductNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class DeleteProductUseCaseTest {

    private final ProductRepositoryPort productRepository = mock(ProductRepositoryPort.class);
    private final DeleteProductUseCase useCase = new DeleteProductUseCase(productRepository);

    @Test
    void deletesAProductThatBelongsToTheBranch() {
        UUID branchId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        when(productRepository.existsByIdAndBranchId(productId, branchId)).thenReturn(true);

        useCase.execute(branchId, productId);

        verify(productRepository).deleteById(productId);
    }

    @Test
    void failsWhenTheProductDoesNotBelongToTheBranch() {
        UUID branchId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        when(productRepository.existsByIdAndBranchId(productId, branchId)).thenReturn(false);

        assertThatThrownBy(() -> useCase.execute(branchId, productId)).isInstanceOf(ProductNotFoundException.class);
        verify(productRepository, never()).deleteById(productId);
    }
}
