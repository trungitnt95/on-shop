package trungitnt95.springboot001.dtos.cart;

import lombok.Getter;
import lombok.Setter;
import trungitnt95.springboot001.dtos.product.ProductV1Dto;

@Setter
@Getter
public class CartV1Dto {
    private Long id;
    private ProductV1Dto product;
    private int quantity;
}
