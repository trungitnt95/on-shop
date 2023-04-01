package trungitnt95.springboot001.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Entity
@Table(name = "t_product")
public class ProductEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "category")
    private CategoryEnum categoryEnum;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "genre")
    private GenreEnum genreEnum;

    private String tag;
    private BigDecimal price;
    private int total;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
