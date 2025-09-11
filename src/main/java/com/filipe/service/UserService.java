package com.filipe.service;

import com.filipe.model.User;
import com.filipe.repository.GenericRepository;
import java.util.List;

public class UserService {
    private final GenericRepository<User, Long> repository;

    public UserService(GenericRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void createUser(Long id, String name, String email) {
        repository.save(new User(id, name, email));
    }

    public User getUser(Long id) {
        return repository.findById(id);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void updateUser(Long id, String name, String email) {
        User user = repository.findById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            repository.save(user);
        }
    }

    public void deleteUser(Long id) {
        repository.delete(id);
    }
}
