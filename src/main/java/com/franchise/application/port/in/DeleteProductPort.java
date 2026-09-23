package com.franchise.application.port.in;

import java.util.UUID;

public interface DeleteProductPort {
    void execute(UUID branchId, UUID productId);
}
