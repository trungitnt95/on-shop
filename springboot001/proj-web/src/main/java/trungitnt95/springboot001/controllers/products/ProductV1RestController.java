package trungitnt95.springboot001.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trungitnt95.springboot001.dtos.product.ProductV1Dto;
import trungitnt95.springboot001.mappers.WebMapper;
import trungitnt95.springboot001.mappers.product.ProductV1Mapper;
import trungitnt95.springboot001.entities.ProductEntity;

@RestController
@RequestMapping( "/api/v1/products")
public class ProductV1RestController extends AbstractProductController<ProductV1Dto> {

    private ProductV1Mapper productV1Mapper;

    @Autowired
    public void setProductV1Mapper(ProductV1Mapper productV1Mapper) {
        this.productV1Mapper = productV1Mapper;
    }

    @Override
    protected WebMapper<ProductV1Dto, ProductEntity> getMapper() {
        return productV1Mapper;
    }
}
