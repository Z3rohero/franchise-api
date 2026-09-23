package com.franchise.application.port.in;

import java.util.UUID;

public record TopStockProduct(
        UUID branchId,
        String branchName,
        UUID productId,
        String productName,
        int stock
) {
}
