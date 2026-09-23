package com.franchise.infrastructure.adapter.in.rest.dto;

import static com.franchise.infrastructure.utils.constants.MessageConstants.NAME_MAX_LENGTH;
import static com.franchise.infrastructure.utils.constants.MessageConstants.NAME_REQUIRED;
import static com.franchise.infrastructure.utils.constants.MessageConstants.STOCK_NOT_NEGATIVE;
import static com.franchise.infrastructure.utils.constants.MessageConstants.STOCK_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Data required to add a product to a branch")
public record CreateProductRequest(
        @Schema(example = "Coca-Cola 400ml")
        @NotBlank(message = NAME_REQUIRED)
        @Size(max = 180, message = NAME_MAX_LENGTH)
        String name,

        @Schema(example = "50")
        @NotNull(message = STOCK_REQUIRED)
        @Min(value = 0, message = STOCK_NOT_NEGATIVE)
        Integer stock
) {
}
