package com.franchise.application.usecase;

import com.franchise.application.port.in.UpdateProductStockPort;
import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.entity.Product;
import com.franchise.domain.exception.ProductNotFoundException;
import java.util.UUID;

public class UpdateProductStockUseCase implements UpdateProductStockPort {

    private final ProductRepositoryPort productRepository;

    public UpdateProductStockUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(UUID productId, int stock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("We couldn't find the requested product."));
        product.updateStock(stock);
        return productRepository.save(product);
    }
}
