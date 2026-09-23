package com.franchise.infrastructure.adapter.out.persistence;

import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Franchise;
import com.franchise.infrastructure.adapter.out.persistence.repository.JpaFranchiseRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FranchisePersistenceAdapter implements FranchiseRepositoryPort {

    private final JpaFranchiseRepository jpaFranchiseRepository;

    public FranchisePersistenceAdapter(JpaFranchiseRepository jpaFranchiseRepository) {
        this.jpaFranchiseRepository = jpaFranchiseRepository;
    }

    @Override
    public Franchise save(Franchise franchise) {
        return jpaFranchiseRepository.save(franchise);
    }

    @Override
    public Optional<Franchise> findById(UUID id) {
        return jpaFranchiseRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Franchise> findByIdWithBranchesAndProducts(UUID id) {
        Optional<Franchise> franchise = jpaFranchiseRepository.findByIdWithBranches(id);
        // Fuerza la inicializacion perezosa de los productos de cada sucursal mientras la
        // transaccion (y la sesion de Hibernate) sigue abierta; ver JpaFranchiseRepository.
        franchise.ifPresent(f -> f.getBranches().forEach(branch -> branch.getProducts().size()));
        return franchise;
    }

    @Override
    public List<Franchise> findAll() {
        return jpaFranchiseRepository.findAll();
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaFranchiseRepository.existsById(id);
    }
}
