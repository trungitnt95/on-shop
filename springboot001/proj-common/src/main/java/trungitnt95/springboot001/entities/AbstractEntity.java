package trungitnt95.springboot001.entities;


import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class AbstractEntity {
    @Version
    private int version = 0;

    public abstract Long getId();
    public abstract void setId(Long id);

}
