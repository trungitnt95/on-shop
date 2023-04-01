package trungitnt95.springboot001.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trungitnt95.springboot001.entities.ProductEntity;
import trungitnt95.springboot001.repositories.ProductRepository;
import trungitnt95.springboot001.services.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductDetail(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
