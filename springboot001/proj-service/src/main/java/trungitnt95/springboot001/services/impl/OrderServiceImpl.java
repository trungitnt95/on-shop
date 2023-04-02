package trungitnt95.springboot001.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import trungitnt95.springboot001.entities.*;
import trungitnt95.springboot001.exceptions.NoOrderInCartException;
import trungitnt95.springboot001.repositories.OrderRepository;
import trungitnt95.springboot001.services.CartService;
import trungitnt95.springboot001.services.OrderService;
import trungitnt95.springboot001.services.payment.BankAccountHandlerImpl;
import trungitnt95.springboot001.services.payment.CashPaymentHandlerImpl;
import trungitnt95.springboot001.services.payment.MomoHandlerImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private CartService cartService;
    private static final Handler orderValidator;

    static {
        orderValidator = new ProductValidator();
        VoucherValidator voucherValidator = new VoucherValidator();
        orderValidator.setNextHandler(voucherValidator);
        PaymentValidator paymentValidator = new PaymentValidator();
        voucherValidator.setNextHandler(paymentValidator);

    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void processOrder(Long orderId, PaymentMethodEnum paymentMethod) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException("not found any order."));

        // execute check validate on order
        orderValidator.validateOrderData(orderEntity);// TODO: check for real data
        // update status to ORDERED
        setOrderStatus(orderEntity, OrderStatusEnum.ORDERED);
        orderRepository.save(orderEntity);

        // process payment
        var paySuccess = switch (paymentMethod) {
            case CASH -> new CashPaymentHandlerImpl();
            case MOMO -> new MomoHandlerImpl();
            case BANK -> new BankAccountHandlerImpl();
        };
        if (paySuccess instanceof BankAccountHandlerImpl)
            throw new UnsupportedOperationException("unsupport BankAccountHandlerImpl method");
        if (paySuccess.processPaymentForOrder(orderEntity)) {
            // update status PAID
            setOrderStatus(orderEntity, OrderStatusEnum.PAID);
        } else {
            // update status FAILED
            setOrderStatus(orderEntity, OrderStatusEnum.FAILED);
        }
        orderRepository.save(orderEntity);

        // delete products in cart
        List<CartEntity> productInCarts = cartService.getCart(orderEntity.getUserEntity().getId());
        cartService.deteleCartProduct(productInCarts);
    }

    @Override
    public OrderEntity startOrderAndGetInfos(Long userId) {
        var orderEntity = new OrderEntity();
        UserEntity user = new UserEntity();user.setId(1L);
        orderEntity.setUserEntity(user);

        // TODO: count totalPrice
        orderEntity.setTotalPrice(BigDecimal.valueOf(1000));

        addProductsToOrder(userId, orderEntity);
        setOrderStatus(orderEntity, OrderStatusEnum.ORDERING);
        return orderRepository.save(orderEntity);
    }

    private void addProductsToOrder(Long userId, OrderEntity orderEntity) {
        cartService.getCart(userId)
                .forEach(aProductInCart -> {
                    var orderProduct = new OrderProductEntity();
                    orderProduct.setOrderEntity(orderEntity);
                    orderProduct.setProductEntity(aProductInCart.getProductEntity());
                    orderProduct.setQuantity(aProductInCart.getQuantity());
                    orderEntity.getOrderProductEntities().add(orderProduct);
                });
        if (CollectionUtils.isEmpty(orderEntity.getOrderProductEntities())) {
            throw new NoOrderInCartException();
        }
    }

    private void setOrderStatus(OrderEntity orderEntity, OrderStatusEnum status) {
        var orderStatus = new OrderStatusEntity();
        orderStatus.setOrderEntity(orderEntity);
        orderStatus.setOrderStatusEnum(status);
        orderStatus.setDate(LocalDateTime.now());
        orderEntity.getOrderStatuses().add(orderStatus);
    }

    // Chain of Response pattern to handle validation for order process

    public interface Handler {
        void setNextHandler(Handler nextHandler);
        void validateOrderData(OrderEntity orderEntity);
    }

    public static class ProductValidator implements Handler {
        private Handler nextHandler;
        @Override
        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public void validateOrderData(OrderEntity orderEntity) {
            // execute check product of order
            Optional<OrderStatusEntity> last = orderEntity.getOrderStatuses().stream().sorted(((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId()))).findFirst();
            if (last.isPresent() && last.get().getOrderStatusEnum() != OrderStatusEnum.ORDERING) {
                throw new IllegalCallerException();
            }

            if (nextHandler != null) this.nextHandler.validateOrderData(orderEntity);
        }
    }
    public static class PaymentValidator implements Handler {
        private Handler nextHandler;
        @Override
        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public void validateOrderData(OrderEntity orderEntity) {
            // execute to check payment
            if (this.nextHandler != null) this.nextHandler.validateOrderData(orderEntity);
        }
    }
    public static class VoucherValidator implements Handler {
        private Handler nextHandler;
        @Override
        public void setNextHandler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public void validateOrderData(OrderEntity orderEntity) {
            // execute to check voucher is valid
            if (this.nextHandler != null) this.nextHandler.validateOrderData(orderEntity);
        }
    }
}
