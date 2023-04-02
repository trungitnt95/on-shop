package trungitnt95.springboot001.mappers.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import trungitnt95.springboot001.dtos.order.OrderV1Dto;
import trungitnt95.springboot001.entities.OrderEntity;
import trungitnt95.springboot001.mappers.WebMapper;
import trungitnt95.springboot001.mappers.product.ProductV1Mapper;

import java.util.stream.Collectors;

@Component
public class OrderV1Mapper extends WebMapper<OrderV1Dto, OrderEntity> {

    @Autowired
    private ProductV1Mapper productV1Mapper;

    @Override
    public OrderV1Dto toDto(OrderEntity entity) {
        if (entity == null) return null;
        var dto = new OrderV1Dto();
        dto.setId(entity.getId());
        dto.setPaymentMethod(entity.getPaymentMethodEnum());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setProducts(entity.getOrderProductEntities().stream()
                .map(t -> Pair.of(productV1Mapper.toDto(t.getProductEntity()), t.getQuantity()))
                .collect(Collectors.toList()));
        return dto;
    }
}
