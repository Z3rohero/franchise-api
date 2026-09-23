package com.franchise.infrastructure.adapter.out.persistence.repository;

import com.franchise.domain.entity.Branch;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBranchRepository extends JpaRepository<Branch, UUID> {
}
