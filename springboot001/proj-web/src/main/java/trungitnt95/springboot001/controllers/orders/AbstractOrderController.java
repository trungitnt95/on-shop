package trungitnt95.springboot001.controllers.orders;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trungitnt95.springboot001.controllers.AbstractController;
import trungitnt95.springboot001.entities.OrderEntity;
import trungitnt95.springboot001.entities.PaymentMethodEnum;
import trungitnt95.springboot001.services.OrderService;

public abstract class AbstractOrderController<T> extends AbstractController<T, OrderEntity> {

    protected OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public T startOrder(@RequestParam("uid") Long userId) {
        return getMapper().toDto(orderService.startOrderAndGetInfos(userId));
    }

    @PostMapping
    public ResponseEntity<String> orderAndProcessPayment(@RequestBody RequestConformOrderDto requestConformOrderDto) {
        orderService.processOrder(requestConformOrderDto.getOrderId(), requestConformOrderDto.getPaymentMethod());
        return ResponseEntity.ok("Order successfully.");
    }

    @Getter
    @Setter
    public static class RequestConformOrderDto {
        private Long orderId;
        private Long userId;
        private PaymentMethodEnum paymentMethod;
    }
}
