package com.franchise.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record FranchiseSummaryResponse(
        UUID id,
        String name
) {
}
