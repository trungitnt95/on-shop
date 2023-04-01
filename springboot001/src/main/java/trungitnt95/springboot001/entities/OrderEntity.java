package trungitnt95.springboot001.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_order")
@Data
public class OrderEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum paymentMethodEnum;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "orderEntity")
    private Set<OrderStatusEntity> orderStatuses = new HashSet<>();
}
