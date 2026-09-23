package com.franchise.application.usecase;

import com.franchise.application.port.in.GetTopStockProductPerBranchPort;
import com.franchise.application.port.in.TopStockProduct;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.domain.entity.Branch;
import com.franchise.domain.entity.Franchise;
import com.franchise.domain.entity.Product;
import com.franchise.domain.exception.FranchiseNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GetTopStockProductPerBranchUseCase implements GetTopStockProductPerBranchPort {

    private final FranchiseRepositoryPort franchiseRepository;

    public GetTopStockProductPerBranchUseCase(FranchiseRepositoryPort franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public List<TopStockProduct> execute(UUID franchiseId) {
        Franchise franchise = franchiseRepository.findByIdWithBranchesAndProducts(franchiseId)
                .orElseThrow(() -> new FranchiseNotFoundException("We couldn't find the requested franchise."));

        return franchise.getBranches().stream()
                .map(this::topStockProductOf)
                .flatMap(Optional::stream)
                .toList();
    }

    private Optional<TopStockProduct> topStockProductOf(Branch branch) {
        return branch.getProducts().stream()
                .max(Comparator.comparingInt(Product::getStock))
                .map(product -> new TopStockProduct(
                        branch.getId(), branch.getName(), product.getId(), product.getName(), product.getStock()));
    }
}
