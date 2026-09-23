package com.franchise.application.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.entity.Product;
import com.franchise.domain.exception.ProductNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UpdateProductNameUseCaseTest {

    private final ProductRepositoryPort productRepository = mock(ProductRepositoryPort.class);
    private final UpdateProductNameUseCase useCase = new UpdateProductNameUseCase(productRepository);

    @Test
    void updatesTheProductName() {
        UUID id = UUID.randomUUID();
        Product product = new Product("Coca-Cola 400ml", 10);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updated = useCase.execute(id, "Coca-Cola 500ml");

        assertThat(updated.getName()).isEqualTo("Coca-Cola 500ml");
    }

    @Test
    void failsWhenTheProductDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.execute(id, "Coca-Cola 500ml")).isInstanceOf(ProductNotFoundException.class);
    }
}
