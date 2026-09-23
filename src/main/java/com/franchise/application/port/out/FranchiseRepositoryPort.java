package com.franchise.application.port.out;

import com.franchise.domain.entity.Franchise;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FranchiseRepositoryPort {
    Franchise save(Franchise franchise);

    Optional<Franchise> findById(UUID id);

    Optional<Franchise> findByIdWithBranchesAndProducts(UUID id);

    List<Franchise> findAll();

    boolean existsById(UUID id);
}
