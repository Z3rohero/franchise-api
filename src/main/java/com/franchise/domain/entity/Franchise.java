package com.franchise.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "franchises")
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 180)
    private String name;

    @OneToMany(mappedBy = "franchise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Branch> branches = new ArrayList<>();

    protected Franchise() {
    }

    public Franchise(String name) {
        this.name = name;
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
        branch.assignFranchise(this);
    }

    public void updateName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}
