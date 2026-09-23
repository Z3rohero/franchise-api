package com.franchise.application.usecase;

import com.franchise.application.port.in.AddBranchToFranchisePort;
import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.entity.Franchise;
import com.franchise.domain.exception.FranchiseNotFoundException;
import java.util.UUID;

public class AddBranchToFranchiseUseCase implements AddBranchToFranchisePort {

    private final FranchiseRepositoryPort franchiseRepository;
    private final BranchRepositoryPort branchRepository;

    public AddBranchToFranchiseUseCase(FranchiseRepositoryPort franchiseRepository, BranchRepositoryPort branchRepository) {
        this.franchiseRepository = franchiseRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch execute(UUID franchiseId, String name) {
        Franchise franchise = franchiseRepository.findById(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException("We couldn't find the requested franchise."));

        Branch branch = new Branch(name);
        franchise.addBranch(branch);
        return branchRepository.save(branch);
    }
}
