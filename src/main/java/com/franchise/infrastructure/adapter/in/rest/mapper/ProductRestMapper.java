package com.franchise.infrastructure.adapter.in.rest.mapper;

import com.franchise.domain.entity.Product;
import com.franchise.infrastructure.adapter.in.rest.dto.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductRestMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getBranch().getId()
        );
    }
}
