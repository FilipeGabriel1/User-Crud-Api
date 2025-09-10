package com.filipe;

public class UserService {
    private final GenericRepository<User, Long> repository;

    public UserService(GenericRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void createUser(Long id, String name, String email) {
        repository.create(id, new User(id, name, email));
    }

    public User getUser(Long id) {
        return repository.read(id);
    }

    public java.util.List<User> getAllUsers() {
        return repository.readAll();
    }

    public void updateUser(Long id, String name, String email) {
        repository.update(id, new User(id, name, email));
    }

    public void deleteUser(Long id) {
        repository.delete(id);
    }
}
