package com.example.apirestevents.service;

import com.example.apirestevents.model.dtos.UserDTO;
import com.example.apirestevents.model.entities.User;

public interface UserService {

    public UserDTO createUser(UserDTO user);

    public UserDTO updateUser(Long id, UserDTO user);

    public Boolean deleteUser(Long id);

}
