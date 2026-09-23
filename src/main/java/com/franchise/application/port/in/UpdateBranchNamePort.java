package com.franchise.application.port.in;

import com.franchise.domain.entity.Branch;
import java.util.UUID;

public interface UpdateBranchNamePort {
    Branch execute(UUID branchId, String name);
}
