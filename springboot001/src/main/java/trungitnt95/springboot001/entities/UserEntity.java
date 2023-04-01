package trungitnt95.springboot001.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "t_user")
@Entity
@Data
public class UserEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
