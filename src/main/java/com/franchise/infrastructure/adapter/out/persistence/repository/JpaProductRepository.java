package com.franchise.infrastructure.adapter.out.persistence.repository;

import com.franchise.domain.entity.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByIdAndBranchId(UUID id, UUID branchId);
}
