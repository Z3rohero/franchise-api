package com.franchise.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record TopStockProductResponse(
        UUID branchId,
        String branchName,
        UUID productId,
        String productName,
        int stock
) {
}
