package com.franchise.application.usecase;

import com.franchise.application.port.in.DeleteProductPort;
import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.exception.ProductNotFoundException;
import java.util.UUID;

public class DeleteProductUseCase implements DeleteProductPort {

    private final ProductRepositoryPort productRepository;

    public DeleteProductUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void execute(UUID branchId, UUID productId) {
        if (!productRepository.existsByIdAndBranchId(productId, branchId)) {
            throw new ProductNotFoundException("We couldn't find the requested product in this branch.");
        }
        productRepository.deleteById(productId);
    }
}
