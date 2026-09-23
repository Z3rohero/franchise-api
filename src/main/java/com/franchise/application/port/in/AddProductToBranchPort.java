package com.franchise.application.port.in;

import com.franchise.domain.entity.Product;
import java.util.UUID;

public interface AddProductToBranchPort {
    Product execute(UUID branchId, String name, int stock);
}
