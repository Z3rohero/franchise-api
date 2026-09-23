package com.franchise.application.port.out;

import com.franchise.domain.entity.Branch;
import java.util.Optional;
import java.util.UUID;

public interface BranchRepositoryPort {
    Branch save(Branch branch);

    Optional<Branch> findById(UUID id);

    boolean existsById(UUID id);
}
