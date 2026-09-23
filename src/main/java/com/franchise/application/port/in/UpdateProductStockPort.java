package com.franchise.application.port.in;

import com.franchise.domain.entity.Product;
import java.util.UUID;

public interface UpdateProductStockPort {
    Product execute(UUID productId, int stock);
}
