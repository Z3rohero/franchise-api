package com.franchise.application.port.in;

import com.franchise.domain.entity.Franchise;
import java.util.UUID;

public interface GetFranchisePort {
    Franchise execute(UUID id);
}
