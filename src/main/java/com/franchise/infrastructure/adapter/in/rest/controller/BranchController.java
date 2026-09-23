package com.franchise.infrastructure.adapter.in.rest.controller;

import static com.franchise.infrastructure.utils.constants.MessageConstants.BRANCH_CREATED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.BRANCH_NAME_UPDATED;

import com.franchise.application.port.in.AddBranchToFranchisePort;
import com.franchise.application.port.in.UpdateBranchNamePort;
import com.franchise.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.BranchSummaryResponse;
import com.franchise.infrastructure.adapter.in.rest.dto.CreateBranchRequest;
import com.franchise.infrastructure.adapter.in.rest.dto.UpdateNameRequest;
import com.franchise.infrastructure.adapter.in.rest.mapper.BranchRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Branches", description = "Branch management for a franchise")
public class BranchController {

    private final AddBranchToFranchisePort addBranchToFranchiseUseCase;
    private final UpdateBranchNamePort updateBranchNameUseCase;
    private final BranchRestMapper mapper;

    @PostMapping("/api/franchises/{franchiseId}/branches")
    @Operation(summary = "Add a branch", description = "Adds a new branch to an existing franchise.")
    public ResponseEntity<ApiResponse<BranchSummaryResponse>> create(
            @PathVariable UUID franchiseId,
            @Valid @RequestBody CreateBranchRequest request
    ) {
        var branch = addBranchToFranchiseUseCase.execute(franchiseId, request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(BRANCH_CREATED, mapper.toSummaryResponse(branch)));
    }

    @PatchMapping("/api/branches/{id}/name")
    @Operation(summary = "Rename a branch", description = "Updates the name of an existing branch.")
    public ApiResponse<BranchSummaryResponse> updateName(@PathVariable UUID id, @Valid @RequestBody UpdateNameRequest request) {
        var branch = updateBranchNameUseCase.execute(id, request.name());
        return ApiResponse.ok(BRANCH_NAME_UPDATED, mapper.toSummaryResponse(branch));
    }
}
