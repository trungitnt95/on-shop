package trungitnt95.springboot001.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trungitnt95.springboot001.entities.CartEntity;
import trungitnt95.springboot001.entities.ProductEntity;
import trungitnt95.springboot001.entities.UserEntity;
import trungitnt95.springboot001.repositories.CartRepository;
import trungitnt95.springboot001.repositories.ProductRepository;
import trungitnt95.springboot001.services.CartService;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<CartEntity> getCart(Long userId) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        return cartRepository.findCartEntitiesByUserEntity(user);
    }

    @Override
    public void addProductToCart(Long userId, Long productId, int quantity) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Not found any product"));
        CartEntity cartEntity = new CartEntity();
        cartEntity.setUserEntity(userEntity);
        cartEntity.setProductEntity(productEntity);
        cartEntity.setQuantity(quantity);

        // TODO: product is existing in cart => increase quantity
        cartRepository.save(cartEntity);
    }

    @Override
    public void deteleCartProduct(List<CartEntity> productInCarts) {
        cartRepository.deleteAll(productInCarts);
    }
}
