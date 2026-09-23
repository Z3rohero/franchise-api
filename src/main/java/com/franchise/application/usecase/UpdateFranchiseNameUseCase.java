package com.franchise.application.usecase;

import com.franchise.application.port.in.UpdateFranchiseNamePort;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Franchise;
import com.franchise.domain.exception.FranchiseNotFoundException;
import java.util.UUID;

public class UpdateFranchiseNameUseCase implements UpdateFranchiseNamePort {

    private final FranchiseRepositoryPort franchiseRepository;

    public UpdateFranchiseNameUseCase(FranchiseRepositoryPort franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise execute(UUID id, String name) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() -> new FranchiseNotFoundException("We couldn't find the requested franchise."));
        franchise.updateName(name);
        return franchiseRepository.save(franchise);
    }
}
