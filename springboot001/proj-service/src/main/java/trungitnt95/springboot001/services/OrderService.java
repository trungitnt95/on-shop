package trungitnt95.springboot001.services;

import trungitnt95.springboot001.entities.OrderEntity;
import trungitnt95.springboot001.entities.PaymentMethodEnum;

public interface OrderService {
    OrderEntity startOrderAndGetInfos(Long userId);

    void processOrder(Long orderId, PaymentMethodEnum paymentMethod);
}
