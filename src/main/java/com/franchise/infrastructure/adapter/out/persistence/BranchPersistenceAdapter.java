package com.franchise.infrastructure.adapter.out.persistence;

import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.infrastructure.adapter.out.persistence.repository.JpaBranchRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class BranchPersistenceAdapter implements BranchRepositoryPort {

    private final JpaBranchRepository jpaBranchRepository;

    public BranchPersistenceAdapter(JpaBranchRepository jpaBranchRepository) {
        this.jpaBranchRepository = jpaBranchRepository;
    }

    @Override
    public Branch save(Branch branch) {
        return jpaBranchRepository.save(branch);
    }

    @Override
    public Optional<Branch> findById(UUID id) {
        return jpaBranchRepository.findById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaBranchRepository.existsById(id);
    }
}
