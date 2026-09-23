package com.franchise.infrastructure.adapter.in.rest.dto;

import static com.franchise.infrastructure.utils.constants.MessageConstants.STOCK_NOT_NEGATIVE;
import static com.franchise.infrastructure.utils.constants.MessageConstants.STOCK_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "New stock quantity for a product")
public record UpdateStockRequest(
        @Schema(example = "80")
        @NotNull(message = STOCK_REQUIRED)
        @Min(value = 0, message = STOCK_NOT_NEGATIVE)
        Integer stock
) {
}
