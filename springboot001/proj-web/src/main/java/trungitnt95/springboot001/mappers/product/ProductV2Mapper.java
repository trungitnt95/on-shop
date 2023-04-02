package trungitnt95.springboot001.mappers.product;

import org.springframework.stereotype.Component;
import trungitnt95.springboot001.dtos.product.ProductV2Dto;
import trungitnt95.springboot001.entities.ProductEntity;

@Component
public class ProductV2Mapper extends CommonProductMapper<ProductV2Dto, ProductEntity> {
    @Override
    public ProductV2Dto toDto(ProductEntity entity) {
        if (entity == null) return null;
        var productV2Dto = new ProductV2Dto();
        toCommonDto(productV2Dto, entity);
        productV2Dto.setTag(entity.getTag());
        return productV2Dto;
    }
}
