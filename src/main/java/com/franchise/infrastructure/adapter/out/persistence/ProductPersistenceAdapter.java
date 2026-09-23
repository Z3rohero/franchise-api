package com.franchise.infrastructure.adapter.out.persistence;

import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.entity.Product;
import com.franchise.infrastructure.adapter.out.persistence.repository.JpaProductRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPersistenceAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaProductRepository;

    public ProductPersistenceAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Product save(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaProductRepository.findById(id);
    }

    @Override
    public boolean existsByIdAndBranchId(UUID id, UUID branchId) {
        return jpaProductRepository.existsByIdAndBranchId(id, branchId);
    }

    @Override
    public void deleteById(UUID id) {
        jpaProductRepository.deleteById(id);
    }
}
