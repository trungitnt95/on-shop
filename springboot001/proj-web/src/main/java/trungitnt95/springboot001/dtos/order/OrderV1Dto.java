package trungitnt95.springboot001.dtos.order;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;
import trungitnt95.springboot001.dtos.product.ProductV1Dto;
import trungitnt95.springboot001.entities.PaymentMethodEnum;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
public class OrderV1Dto {
    private Long id;
    private List<Pair<ProductV1Dto, Integer>> products;// pair of product and it's quantity
    private BigDecimal totalPrice;
    private PaymentMethodEnum paymentMethod;
}
