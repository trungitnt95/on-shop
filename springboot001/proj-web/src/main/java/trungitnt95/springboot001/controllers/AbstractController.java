package trungitnt95.springboot001.controllers;

import trungitnt95.springboot001.mappers.WebMapper;

public abstract class AbstractController<T, E> {
    public abstract WebMapper<T, E> getMapper();
}
