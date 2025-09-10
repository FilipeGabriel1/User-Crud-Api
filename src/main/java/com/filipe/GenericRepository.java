package com.filipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepository<T, ID> {
    private final Map<ID, T> store = new HashMap<>();

    public void create(ID id, T entity) {
        store.put(id, entity);
    }

    public T read(ID id) {
        return store.get(id);
    }

    public List<T> readAll() {
        return new ArrayList<>(store.values());
    }

    public void update(ID id, T entity) {
        store.put(id, entity);
    }

    public void delete(ID id) {
        store.remove(id);
    }
}
