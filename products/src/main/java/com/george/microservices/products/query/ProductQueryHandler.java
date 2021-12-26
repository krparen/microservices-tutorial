package com.george.microservices.products.query;

import com.george.microservices.products.core.data.ProductEntity;
import com.george.microservices.products.core.data.ProductsRepository;
import com.george.microservices.products.query.rest.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductQueryHandler {

    private final ProductsRepository productsRepository;

    @QueryHandler
    private List<ProductRestModel> findProducts(FindProductsQuery query) {
        List<ProductEntity> entities = productsRepository.findAll();

        List<ProductRestModel> models = new ArrayList<>();
        entities.forEach(entity -> {
            ProductRestModel model = new ProductRestModel();
            BeanUtils.copyProperties(entity, model);
            models.add(model);
        });

        return models;
    }
}
