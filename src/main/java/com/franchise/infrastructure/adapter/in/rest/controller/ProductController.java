package com.franchise.infrastructure.adapter.in.rest.controller;

import static com.franchise.infrastructure.utils.constants.MessageConstants.PRODUCT_CREATED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.PRODUCT_DELETED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.PRODUCT_NAME_UPDATED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.PRODUCT_STOCK_UPDATED;

import com.franchise.application.port.in.AddProductToBranchPort;
import com.franchise.application.port.in.DeleteProductPort;
import com.franchise.application.port.in.UpdateProductNamePort;
import com.franchise.application.port.in.UpdateProductStockPort;
import com.franchise.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.CreateProductRequest;
import com.franchise.infrastructure.adapter.in.rest.dto.ProductResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.UpdateNameRequest;
import com.franchise.infrastructure.adapter.in.rest.dto.UpdateStockRequest;
import com.franchise.infrastructure.adapter.in.rest.mapper.ProductRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product management for a branch: add, remove, and update name/stock")
public class ProductController {

    private final AddProductToBranchPort addProductToBranchUseCase;
    private final DeleteProductPort deleteProductUseCase;
    private final UpdateProductStockPort updateProductStockUseCase;
    private final UpdateProductNamePort updateProductNameUseCase;
    private final ProductRestMapper mapper;

    @PostMapping("/api/branches/{branchId}/products")
    @Operation(summary = "Add a product", description = "Adds a new product to an existing branch.")
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @PathVariable UUID branchId,
            @Valid @RequestBody CreateProductRequest request
    ) {
        var product = addProductToBranchUseCase.execute(branchId, request.name(), request.stock());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(PRODUCT_CREATED, mapper.toResponse(product)));
    }

    @DeleteMapping("/api/branches/{branchId}/products/{productId}")
    @Operation(summary = "Remove a product", description = "Removes a product from a branch.")
    public ApiResponse<Void> delete(@PathVariable UUID branchId, @PathVariable UUID productId) {
        deleteProductUseCase.execute(branchId, productId);
        return ApiResponse.ok(PRODUCT_DELETED, null);
    }

    @PatchMapping("/api/products/{id}/stock")
    @Operation(summary = "Update stock", description = "Sets a new stock quantity for a product.")
    public ApiResponse<ProductResponse> updateStock(@PathVariable UUID id, @Valid @RequestBody UpdateStockRequest request) {
        var product = updateProductStockUseCase.execute(id, request.stock());
        return ApiResponse.ok(PRODUCT_STOCK_UPDATED, mapper.toResponse(product));
    }

    @PatchMapping("/api/products/{id}/name")
    @Operation(summary = "Rename a product", description = "Updates the name of an existing product.")
    public ApiResponse<ProductResponse> updateName(@PathVariable UUID id, @Valid @RequestBody UpdateNameRequest request) {
        var product = updateProductNameUseCase.execute(id, request.name());
        return ApiResponse.ok(PRODUCT_NAME_UPDATED, mapper.toResponse(product));
    }
}
