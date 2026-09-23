package com.franchise.infrastructure.adapter.in.rest.mapper;

import com.franchise.domain.entity.Branch;
import com.franchise.infrastructure.adapter.in.rest.dto.BranchResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.BranchSummaryResponse;
import org.springframework.stereotype.Component;

@Component
public class BranchRestMapper {

    private final ProductRestMapper productRestMapper;

    public BranchRestMapper(ProductRestMapper productRestMapper) {
        this.productRestMapper = productRestMapper;
    }

    /**
     * Incluye el listado de productos: solo debe usarse con una sucursal cuya coleccion de
     * productos ya fue cargada (por ejemplo, al venir anidada dentro de un Franchise obtenido
     * con fetch join). Fuera de ese caso, la coleccion es un proxy perezoso de Hibernate y
     * recorrerla fuera de sesion lanza LazyInitializationException (open-in-view esta deshabilitado).
     */
    public BranchResponse toResponse(Branch branch) {
        return new BranchResponse(
                branch.getId(),
                branch.getName(),
                branch.getProducts().stream().map(productRestMapper::toResponse).toList()
        );
    }

    /**
     * Version sin la coleccion de productos, segura para usar con una sucursal obtenida por
     * findById sin fetch join (creacion o actualizacion de nombre).
     */
    public BranchSummaryResponse toSummaryResponse(Branch branch) {
        return new BranchSummaryResponse(branch.getId(), branch.getName(), branch.getFranchise().getId());
    }
}
