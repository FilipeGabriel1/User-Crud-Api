package com.filipe.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Repositório genérico para operações CRUD em memória, usando um Map para armazenar entidades.
// T é o tipo da entidade e ID é o tipo do identificador.
public class GenericRepository<T, ID> {
    private final Map<ID, T> store = new HashMap<>();

    public void save(T entity) {
        try {
            ID id = (ID) entity.getClass().getMethod("getId").invoke(entity);
            store.put(id, entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save entity", e);
        }
    }

    public T findById(ID id) {
        return store.get(id);
    }

    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    public void delete(ID id) {
        store.remove(id);
    }
}
