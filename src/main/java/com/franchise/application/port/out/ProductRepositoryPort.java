package com.franchise.application.port.out;

import com.franchise.domain.entity.Product;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product save(Product product);

    Optional<Product> findById(UUID id);

    boolean existsByIdAndBranchId(UUID id, UUID branchId);

    void deleteById(UUID id);
}
