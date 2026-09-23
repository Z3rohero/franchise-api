package com.franchise.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 180)
    private String name;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    protected Product() {
    }

    public Product(String name, int stock) {
        requireNonNegativeStock(stock);
        this.name = name;
        this.stock = stock;
    }

    public void assignBranch(Branch branch) {
        this.branch = branch;
    }

    public void updateName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void updateStock(int stock) {
        requireNonNegativeStock(stock);
        this.stock = stock;
    }

    private static void requireNonNegativeStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public Branch getBranch() {
        return branch;
    }
}
