package com.franchise.infrastructure.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record BranchResponse(
        UUID id,
        String name,
        List<ProductResponse> products
) {
}
