package com.franchise.infrastructure.adapter.in.rest.controller;

import static com.franchise.infrastructure.utils.constants.MessageConstants.FRANCHISE_CREATED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.FRANCHISE_LIST_RETRIEVED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.FRANCHISE_NAME_UPDATED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.FRANCHISE_RETRIEVED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.TOP_STOCK_PRODUCTS_RETRIEVED;

import com.franchise.application.port.in.CreateFranchisePort;
import com.franchise.application.port.in.GetFranchisePort;
import com.franchise.application.port.in.GetTopStockProductPerBranchPort;
import com.franchise.application.port.in.ListFranchisesPort;
import com.franchise.application.port.in.TopStockProduct;
import com.franchise.application.port.in.UpdateFranchiseNamePort;
import com.franchise.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.CreateFranchiseRequest;
import com.franchise.infrastructure.adapter.in.rest.dto.FranchiseResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.FranchiseSummaryResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.TopStockProductResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.UpdateNameRequest;
import com.franchise.infrastructure.adapter.in.rest.mapper.FranchiseRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/franchises")
@RequiredArgsConstructor
@Tag(name = "Franchises", description = "Franchise management: create, rename, list and consult the top-stock product per branch")
public class FranchiseController {

    private final CreateFranchisePort createFranchiseUseCase;
    private final ListFranchisesPort listFranchisesUseCase;
    private final GetFranchisePort getFranchiseUseCase;
    private final UpdateFranchiseNamePort updateFranchiseNameUseCase;
    private final GetTopStockProductPerBranchPort getTopStockProductPerBranchUseCase;
    private final FranchiseRestMapper mapper;

    @PostMapping
    @Operation(summary = "Create a franchise", description = "Creates a new franchise with no branches yet.")
    public ResponseEntity<ApiResponse<FranchiseResponse>> create(@Valid @RequestBody CreateFranchiseRequest request) {
        var franchise = createFranchiseUseCase.execute(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(FRANCHISE_CREATED, mapper.toResponse(franchise)));
    }

    @GetMapping
    @Operation(summary = "List franchises", description = "Lists all the registered franchises.")
    public ApiResponse<List<FranchiseSummaryResponse>> list() {
        var franchises = listFranchisesUseCase.execute().stream().map(mapper::toSummaryResponse).toList();
        return ApiResponse.ok(FRANCHISE_LIST_RETRIEVED, franchises);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a franchise", description = "Gets a franchise with its branches and their products.")
    public ApiResponse<FranchiseResponse> get(@PathVariable UUID id) {
        return ApiResponse.ok(FRANCHISE_RETRIEVED, mapper.toResponse(getFranchiseUseCase.execute(id)));
    }

    @PatchMapping("/{id}/name")
    @Operation(summary = "Rename a franchise", description = "Updates the name of an existing franchise.")
    public ApiResponse<FranchiseSummaryResponse> updateName(@PathVariable UUID id, @Valid @RequestBody UpdateNameRequest request) {
        var franchise = updateFranchiseNameUseCase.execute(id, request.name());
        return ApiResponse.ok(FRANCHISE_NAME_UPDATED, mapper.toSummaryResponse(franchise));
    }

    @GetMapping("/{id}/top-stock-products")
    @Operation(
            summary = "Top-stock product per branch",
            description = "Returns, for the given franchise, the product with the highest stock in each of its branches."
    )
    public ApiResponse<List<TopStockProductResponse>> topStockProducts(@PathVariable UUID id) {
        List<TopStockProductResponse> response = getTopStockProductPerBranchUseCase.execute(id).stream()
                .map(this::toResponse)
                .toList();
        return ApiResponse.ok(TOP_STOCK_PRODUCTS_RETRIEVED, response);
    }

    private TopStockProductResponse toResponse(TopStockProduct topStockProduct) {
        return new TopStockProductResponse(
                topStockProduct.branchId(),
                topStockProduct.branchName(),
                topStockProduct.productId(),
                topStockProduct.productName(),
                topStockProduct.stock()
        );
    }
}
