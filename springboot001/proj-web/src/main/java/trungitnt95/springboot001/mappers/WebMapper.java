package trungitnt95.springboot001.mappers;


import java.util.List;
import java.util.stream.Collectors;

public abstract class WebMapper<T, E> {

    public abstract T toDto(E entity);


    public List<T> toDtos(List<E> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
