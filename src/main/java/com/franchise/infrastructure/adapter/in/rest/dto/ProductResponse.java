package com.franchise.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        int stock,
        UUID branchId
) {
}
