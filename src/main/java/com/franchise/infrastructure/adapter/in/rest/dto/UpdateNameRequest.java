package com.franchise.infrastructure.adapter.in.rest.dto;

import static com.franchise.infrastructure.utils.constants.MessageConstants.NAME_MAX_LENGTH;
import static com.franchise.infrastructure.utils.constants.MessageConstants.NAME_REQUIRED;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "New name for a franchise, branch or product")
public record UpdateNameRequest(
        @Schema(example = "Fast Burger Co.")
        @NotBlank(message = NAME_REQUIRED)
        @Size(max = 180, message = NAME_MAX_LENGTH)
        String name
) {
}
