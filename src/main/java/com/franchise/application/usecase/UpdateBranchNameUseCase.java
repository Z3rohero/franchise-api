package com.franchise.application.usecase;

import com.franchise.application.port.in.UpdateBranchNamePort;
import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.exception.BranchNotFoundException;
import java.util.UUID;

public class UpdateBranchNameUseCase implements UpdateBranchNamePort {

    private final BranchRepositoryPort branchRepository;

    public UpdateBranchNameUseCase(BranchRepositoryPort branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch execute(UUID branchId, String name) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException("We couldn't find the requested branch."));
        branch.updateName(name);
        return branchRepository.save(branch);
    }
}
