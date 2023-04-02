package trungitnt95.springboot001.services;

import trungitnt95.springboot001.entities.OrderEntity;

public interface PaymentHandler {
    boolean processPaymentForOrder(OrderEntity entity);
}
