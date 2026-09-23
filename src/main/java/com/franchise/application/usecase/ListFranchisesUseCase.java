package com.franchise.application.usecase;

import com.franchise.application.port.in.ListFranchisesPort;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Franchise;
import java.util.List;

public class ListFranchisesUseCase implements ListFranchisesPort {

    private final FranchiseRepositoryPort franchiseRepository;

    public ListFranchisesUseCase(FranchiseRepositoryPort franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public List<Franchise> execute() {
        return franchiseRepository.findAll();
    }
}
