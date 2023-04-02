package trungitnt95.springboot001.dtos.product;

import lombok.Getter;
import lombok.Setter;
import trungitnt95.springboot001.entities.CategoryEnum;

import java.math.BigDecimal;


@Getter
@Setter
public class AbstractProductDto {
    private Long id;
    private String name;
    private CategoryEnum categoryEnum;
    private BigDecimal price;
    private int total;
    private int version;
}
