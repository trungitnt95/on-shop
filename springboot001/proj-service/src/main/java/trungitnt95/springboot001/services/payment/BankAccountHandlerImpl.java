package trungitnt95.springboot001.services.payment;

import trungitnt95.springboot001.entities.OrderEntity;
import trungitnt95.springboot001.services.PaymentHandler;

public class BankAccountHandlerImpl implements PaymentHandler {
    @Override
    public boolean processPaymentForOrder(OrderEntity entity) {
        return false;
    }
}
