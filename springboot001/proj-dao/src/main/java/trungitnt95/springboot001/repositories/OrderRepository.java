package trungitnt95.springboot001.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trungitnt95.springboot001.entities.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
