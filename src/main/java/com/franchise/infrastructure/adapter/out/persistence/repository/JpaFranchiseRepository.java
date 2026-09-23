package com.franchise.infrastructure.adapter.out.persistence.repository;

import com.franchise.domain.entity.Franchise;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaFranchiseRepository extends JpaRepository<Franchise, UUID> {

    /**
     * Solo trae las sucursales con fetch join. Los productos de cada sucursal se inicializan
     * aparte (ver FranchisePersistenceAdapter): Hibernate no permite un JOIN FETCH simultaneo
     * sobre dos colecciones de tipo List (MultipleBagFetchException).
     */
    @Query("SELECT DISTINCT f FROM Franchise f LEFT JOIN FETCH f.branches WHERE f.id = :id")
    Optional<Franchise> findByIdWithBranches(@Param("id") UUID id);
}
