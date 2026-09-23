package com.franchise.infrastructure.adapter.in.rest.mapper;

import com.franchise.domain.entity.Franchise;
import com.franchise.infrastructure.adapter.in.rest.dto.FranchiseResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.FranchiseSummaryResponse;
import org.springframework.stereotype.Component;

@Component
public class FranchiseRestMapper {

    private final BranchRestMapper branchRestMapper;

    public FranchiseRestMapper(BranchRestMapper branchRestMapper) {
        this.branchRestMapper = branchRestMapper;
    }

    /**
     * Incluye sucursales y productos: solo debe usarse con un Franchise recien creado (colecciones
     * vacias en memoria) o cargado con fetch join (findByIdWithBranchesAndProducts).
     */
    public FranchiseResponse toResponse(Franchise franchise) {
        return new FranchiseResponse(
                franchise.getId(),
                franchise.getName(),
                franchise.getBranches().stream().map(branchRestMapper::toResponse).toList()
        );
    }

    /**
     * Version sin sucursales, segura para un Franchise obtenido por findById sin fetch join
     * (listado y actualizacion de nombre).
     */
    public FranchiseSummaryResponse toSummaryResponse(Franchise franchise) {
        return new FranchiseSummaryResponse(franchise.getId(), franchise.getName());
    }
}
