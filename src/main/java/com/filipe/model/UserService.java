
package com.filipe.model;
import com.filipe.repository.GenericRepository;


// Serviço para gerenciar usuários, usando GenericRepository para operações CRUD.
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


    public java.util.List<User> getAllUsers() {
        return repository.findAll();
    }

  
    public void updateUser(Long id, String name, String email) {
        repository.save(new User(id, name, email));
    }

   
    public void deleteUser(Long id) {
        repository.delete(id);
    }
}
