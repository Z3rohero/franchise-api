package com.franchise.application.port.in;

import com.franchise.domain.entity.Branch;
import java.util.UUID;

public interface AddBranchToFranchisePort {
    Branch execute(UUID franchiseId, String name);
}
