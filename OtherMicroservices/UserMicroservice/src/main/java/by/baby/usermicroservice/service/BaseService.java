package by.baby.usermicroservice.service;

import java.util.List;

public interface BaseService<T> {
    T update(T entity, Long id);
    List<T> findAll();
    T create(T entity);
    T findById(Long id);
}
