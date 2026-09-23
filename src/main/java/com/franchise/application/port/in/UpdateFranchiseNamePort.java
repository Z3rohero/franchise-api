package com.franchise.application.port.in;

import com.franchise.domain.entity.Franchise;
import java.util.UUID;

public interface UpdateFranchiseNamePort {
    Franchise execute(UUID id, String name);
}
