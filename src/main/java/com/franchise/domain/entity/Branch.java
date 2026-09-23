package com.franchise.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 180)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "franchise_id", nullable = false)
    private Franchise franchise;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    protected Branch() {
    }

    public Branch(String name) {
        this.name = name;
    }

    public void assignFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.assignBranch(this);
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

    public Franchise getFranchise() {
        return franchise;
    }

    public List<Product> getProducts() {
        return products;
    }
}
