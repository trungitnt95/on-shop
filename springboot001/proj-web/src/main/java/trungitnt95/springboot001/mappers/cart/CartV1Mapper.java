package trungitnt95.springboot001.mappers.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import trungitnt95.springboot001.dtos.cart.CartV1Dto;
import trungitnt95.springboot001.entities.CartEntity;
import trungitnt95.springboot001.mappers.WebMapper;
import trungitnt95.springboot001.mappers.product.ProductV1Mapper;


@Component
public class CartV1Mapper extends WebMapper<CartV1Dto, CartEntity> {

    private ProductV1Mapper productV1Mapper;

    @Autowired
    public void setProductV1Mapper(ProductV1Mapper productV1Mapper) {
        this.productV1Mapper = productV1Mapper;
    }

    @Override
    public CartV1Dto toDto(CartEntity entity) {
        if (entity == null) return null;
        var cartv1Dto = new CartV1Dto();
        cartv1Dto.setQuantity(entity.getQuantity());
        cartv1Dto.setProduct(productV1Mapper.toDto(entity.getProductEntity()));
        cartv1Dto.setId(entity.getId());
        return cartv1Dto;
    }
}
