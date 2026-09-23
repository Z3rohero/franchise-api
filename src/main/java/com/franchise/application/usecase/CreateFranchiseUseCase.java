package com.franchise.application.usecase;

import com.franchise.application.port.in.CreateFranchisePort;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Franchise;

public class CreateFranchiseUseCase implements CreateFranchisePort {

    private final FranchiseRepositoryPort franchiseRepository;

    public CreateFranchiseUseCase(FranchiseRepositoryPort franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise execute(String name) {
        return franchiseRepository.save(new Franchise(name));
    }
}
