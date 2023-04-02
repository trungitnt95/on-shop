package trungitnt95.springboot001.dtos.product;

import lombok.Getter;
import lombok.Setter;
import trungitnt95.springboot001.entities.GenreEnum;

@Getter
@Setter
public class ProductV1Dto extends AbstractProductDto {

    private GenreEnum genreEnum;
}
