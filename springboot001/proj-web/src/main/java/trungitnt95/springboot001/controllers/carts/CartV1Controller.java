package trungitnt95.springboot001.controllers.carts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trungitnt95.springboot001.dtos.cart.CartV1Dto;
import trungitnt95.springboot001.entities.CartEntity;
import trungitnt95.springboot001.mappers.WebMapper;
import trungitnt95.springboot001.mappers.cart.CartV1Mapper;

@RestController
@RequestMapping("/api/v1/cart")
public class CartV1Controller extends AbstractCartController<CartV1Dto> {

    private CartV1Mapper cartV1Mapper;

    @Autowired
    public void setCartV1Mapper(CartV1Mapper cartV1Mapper) {
        this.cartV1Mapper = cartV1Mapper;
    }

    @Override
    public WebMapper<CartV1Dto, CartEntity> getMapper() {
        return cartV1Mapper;
    }

}
