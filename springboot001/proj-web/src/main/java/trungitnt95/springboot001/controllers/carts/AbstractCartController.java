package trungitnt95.springboot001.controllers.carts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trungitnt95.springboot001.controllers.AbstractController;
import trungitnt95.springboot001.entities.CartEntity;
import trungitnt95.springboot001.services.CartService;

import java.util.List;


public abstract class AbstractCartController<T> extends AbstractController<T, CartEntity> {

    protected CartService cartService;
    @Autowired
    protected void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<T> getCartOfUser(@RequestParam("uId") Long userId) {
        return getMapper().toDtos(cartService.getCart(userId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> addProductToCart(@RequestBody AddingProductDto dto, @PathVariable("id") String cartId) {
        cartService.addProductToCart(dto.getUserId(), dto.getProductId(), dto.getQuantity());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Add product to cart successfully.");
    }

    @Getter
    @Setter
    public static class AddingProductDto {
        private Long productId;
        private Long userId;
        private int quantity;
    }
}
