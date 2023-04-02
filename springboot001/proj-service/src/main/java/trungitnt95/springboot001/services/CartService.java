package trungitnt95.springboot001.services;

import trungitnt95.springboot001.entities.CartEntity;

import java.util.List;

public interface CartService {
    List<CartEntity> getCart(Long userId);


    void addProductToCart(Long userId, Long productId, int quantity);

    void deteleCartProduct(List<CartEntity> productInCarts);
}
