package trungitnt95.springboot001.mappers.product;

import trungitnt95.springboot001.dtos.product.AbstractProductDto;
import trungitnt95.springboot001.mappers.WebMapper;
import trungitnt95.springboot001.entities.ProductEntity;

public abstract class CommonProductMapper<T, E> extends WebMapper<T, E> {

    protected void toCommonDto(AbstractProductDto productDto, ProductEntity entity) {
        productDto.setName(entity.getName());
        productDto.setId(entity.getId());
        productDto.setCategoryEnum(entity.getCategoryEnum());
        productDto.setPrice(entity.getPrice());
        productDto.setTotal(entity.getTotal());
    }
}
