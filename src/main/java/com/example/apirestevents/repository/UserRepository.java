package com.example.apirestevents.repository;

import com.example.apirestevents.model.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    // Métodos del repositorio
}
