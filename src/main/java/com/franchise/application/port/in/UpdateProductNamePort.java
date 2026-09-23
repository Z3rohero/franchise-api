package com.franchise.application.port.in;

import com.franchise.domain.entity.Product;
import java.util.UUID;

public interface UpdateProductNamePort {
    Product execute(UUID productId, String name);
}
