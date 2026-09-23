package com.franchise.application.usecase;

import com.franchise.application.port.in.AddProductToBranchPort;
import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.entity.Product;
import com.franchise.domain.exception.BranchNotFoundException;
import java.util.UUID;

public class AddProductToBranchUseCase implements AddProductToBranchPort {

    private final BranchRepositoryPort branchRepository;
    private final ProductRepositoryPort productRepository;

    public AddProductToBranchUseCase(BranchRepositoryPort branchRepository, ProductRepositoryPort productRepository) {
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Product execute(UUID branchId, String name, int stock) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new BranchNotFoundException("We couldn't find the requested branch."));

        Product product = new Product(name, stock);
        branch.addProduct(product);
        return productRepository.save(product);
    }
}
