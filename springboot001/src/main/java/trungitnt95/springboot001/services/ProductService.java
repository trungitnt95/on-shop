package trungitnt95.springboot001.services;

import trungitnt95.springboot001.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getProducts();

    ProductEntity getProductDetail(Long productId);
}
