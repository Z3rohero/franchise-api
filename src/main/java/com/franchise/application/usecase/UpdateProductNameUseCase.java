package com.franchise.application.usecase;

import com.franchise.application.port.in.UpdateProductNamePort;
import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.entity.Product;
import com.franchise.domain.exception.ProductNotFoundException;
import java.util.UUID;

public class UpdateProductNameUseCase implements UpdateProductNamePort {

    private final ProductRepositoryPort productRepository;

    public UpdateProductNameUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(UUID productId, String name) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("We couldn't find the requested product."));
        product.updateName(name);
        return productRepository.save(product);
    }
}
