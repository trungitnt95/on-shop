package trungitnt95.springboot001.controllers.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trungitnt95.springboot001.dtos.order.OrderV1Dto;
import trungitnt95.springboot001.entities.OrderEntity;
import trungitnt95.springboot001.mappers.WebMapper;
import trungitnt95.springboot001.mappers.order.OrderV1Mapper;

@RestController
@RequestMapping("/api/v1/order")
public class OrderV1Controller extends AbstractOrderController<OrderV1Dto> {

    @Autowired
    private OrderV1Mapper orderV1Mapper;

    @Override
    public WebMapper<OrderV1Dto, OrderEntity> getMapper() {
        return orderV1Mapper;
    }
}
