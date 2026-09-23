package com.franchise.application.port.in;

import com.franchise.domain.entity.Franchise;
import java.util.List;

public interface ListFranchisesPort {
    List<Franchise> execute();
}
