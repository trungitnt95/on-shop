package trungitnt95.springboot001.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trungitnt95.springboot001.entities.CartEntity;
import trungitnt95.springboot001.entities.UserEntity;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findCartEntitiesByUserEntity(UserEntity userEntity);
}
