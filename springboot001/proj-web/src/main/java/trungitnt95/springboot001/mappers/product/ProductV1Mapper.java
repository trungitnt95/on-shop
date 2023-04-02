package trungitnt95.springboot001.mappers.product;

import org.springframework.stereotype.Component;
import trungitnt95.springboot001.dtos.product.ProductV1Dto;
import trungitnt95.springboot001.entities.ProductEntity;


@Component
public class ProductV1Mapper extends CommonProductMapper<ProductV1Dto, ProductEntity> {
    @Override
    public ProductV1Dto toDto(ProductEntity entity) {
        if (entity == null) return null;
        var productV1Dto = new ProductV1Dto();
        toCommonDto(productV1Dto, entity);
        productV1Dto.setGenreEnum(entity.getGenreEnum());
        return productV1Dto;
    }
}
