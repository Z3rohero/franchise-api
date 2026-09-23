package com.franchise.application.port.in;

import com.franchise.domain.entity.Franchise;

public interface CreateFranchisePort {
    Franchise execute(String name);
}
