package com.franchise.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record BranchSummaryResponse(
        UUID id,
        String name,
        UUID franchiseId
) {
}
