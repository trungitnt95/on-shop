package trungitnt95.springboot001.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trungitnt95.springboot001.dtos.product.ProductV2Dto;
import trungitnt95.springboot001.mappers.WebMapper;
import trungitnt95.springboot001.mappers.product.ProductV2Mapper;
import trungitnt95.springboot001.entities.ProductEntity;


@RestController
@RequestMapping(value = "/api/v2/products")
public class ProductV2RestController extends AbstractProductController<ProductV2Dto> {

    private ProductV2Mapper productV2Mapper;

    @Autowired
    public void setProductV1Mapper(ProductV2Mapper productV2Mapper) {
        this.productV2Mapper = productV2Mapper;
    }

    @Override
    public WebMapper<ProductV2Dto, ProductEntity> getMapper() {
        return productV2Mapper;
    }
}
