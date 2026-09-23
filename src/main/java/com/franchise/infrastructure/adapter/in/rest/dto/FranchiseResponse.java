package com.franchise.infrastructure.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record FranchiseResponse(
        UUID id,
        String name,
        List<BranchResponse> branches
) {
}
