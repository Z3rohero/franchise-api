package com.franchise.infrastructure.config;

import com.franchise.application.port.in.AddBranchToFranchisePort;
import com.franchise.application.port.in.AddProductToBranchPort;
import com.franchise.application.port.in.CreateFranchisePort;
import com.franchise.application.port.in.DeleteProductPort;
import com.franchise.application.port.in.GetFranchisePort;
import com.franchise.application.port.in.GetTopStockProductPerBranchPort;
import com.franchise.application.port.in.ListFranchisesPort;
import com.franchise.application.port.in.UpdateBranchNamePort;
import com.franchise.application.port.in.UpdateFranchiseNamePort;
import com.franchise.application.port.in.UpdateProductNamePort;
import com.franchise.application.port.in.UpdateProductStockPort;
import com.franchise.application.port.out.BranchRepositoryPort;
import com.franchise.application.port.out.FranchiseRepositoryPort;
import com.franchise.application.port.out.ProductRepositoryPort;
import com.franchise.application.usecase.AddBranchToFranchiseUseCase;
import com.franchise.application.usecase.AddProductToBranchUseCase;
import com.franchise.application.usecase.CreateFranchiseUseCase;
import com.franchise.application.usecase.DeleteProductUseCase;
import com.franchise.application.usecase.GetFranchiseUseCase;
import com.franchise.application.usecase.GetTopStockProductPerBranchUseCase;
import com.franchise.application.usecase.ListFranchisesUseCase;
import com.franchise.application.usecase.UpdateBranchNameUseCase;
import com.franchise.application.usecase.UpdateFranchiseNameUseCase;
import com.franchise.application.usecase.UpdateProductNameUseCase;
import com.franchise.application.usecase.UpdateProductStockUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateFranchisePort createFranchisePort(FranchiseRepositoryPort franchiseRepository) {
        return new CreateFranchiseUseCase(franchiseRepository);
    }

    @Bean
    public ListFranchisesPort listFranchisesPort(FranchiseRepositoryPort franchiseRepository) {
        return new ListFranchisesUseCase(franchiseRepository);
    }

    @Bean
    public GetFranchisePort getFranchisePort(FranchiseRepositoryPort franchiseRepository) {
        return new GetFranchiseUseCase(franchiseRepository);
    }

    @Bean
    public UpdateFranchiseNamePort updateFranchiseNamePort(FranchiseRepositoryPort franchiseRepository) {
        return new UpdateFranchiseNameUseCase(franchiseRepository);
    }

    @Bean
    public AddBranchToFranchisePort addBranchToFranchisePort(FranchiseRepositoryPort franchiseRepository,
                                                               BranchRepositoryPort branchRepository) {
        return new AddBranchToFranchiseUseCase(franchiseRepository, branchRepository);
    }

    @Bean
    public UpdateBranchNamePort updateBranchNamePort(BranchRepositoryPort branchRepository) {
        return new UpdateBranchNameUseCase(branchRepository);
    }

    @Bean
    public AddProductToBranchPort addProductToBranchPort(BranchRepositoryPort branchRepository,
                                                           ProductRepositoryPort productRepository) {
        return new AddProductToBranchUseCase(branchRepository, productRepository);
    }

    @Bean
    public DeleteProductPort deleteProductPort(ProductRepositoryPort productRepository) {
        return new DeleteProductUseCase(productRepository);
    }

    @Bean
    public UpdateProductStockPort updateProductStockPort(ProductRepositoryPort productRepository) {
        return new UpdateProductStockUseCase(productRepository);
    }

    @Bean
    public UpdateProductNamePort updateProductNamePort(ProductRepositoryPort productRepository) {
        return new UpdateProductNameUseCase(productRepository);
    }

    @Bean
    public GetTopStockProductPerBranchPort getTopStockProductPerBranchPort(FranchiseRepositoryPort franchiseRepository) {
        return new GetTopStockProductPerBranchUseCase(franchiseRepository);
    }
}
