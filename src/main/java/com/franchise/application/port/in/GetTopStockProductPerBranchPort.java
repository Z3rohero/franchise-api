package com.franchise.application.port.in;

import java.util.List;
import java.util.UUID;

public interface GetTopStockProductPerBranchPort {
    List<TopStockProduct> execute(UUID franchiseId);
}
