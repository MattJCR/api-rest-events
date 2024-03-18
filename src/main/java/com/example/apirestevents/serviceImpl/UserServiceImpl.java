package com.example.apirestevents.serviceImpl;

import com.example.apirestevents.event.DatabaseCreatedEvent;
import com.example.apirestevents.event.DatabaseDeleteEvent;
import com.example.apirestevents.event.DatabaseUpdatedEvent;
import com.example.apirestevents.model.dtos.UserDTO;
import com.example.apirestevents.model.entities.User;
import com.example.apirestevents.model.mappers.UserMapper;
import com.example.apirestevents.repository.UserRepository;
import com.example.apirestevents.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional
    public UserDTO createUser(UserDTO user) {
        User userSaved = userRepository.save(userMapper.userDTOToUser(user));
        DatabaseCreatedEvent event = new DatabaseCreatedEvent(this, userSaved);
        eventPublisher.publishEvent(event);

        // Utiliza el mapper para convertir la entidad User guardada a UserDTO antes de retornarla
        return userMapper.userToUserDTO(userSaved);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User userDb = userOptional.get();
            userDb.setEmail(user.getEmail());
            userDb.setUsername(user.getUsername());
            userDb = userRepository.save(userDb);
            DatabaseUpdatedEvent event = new DatabaseUpdatedEvent(this,userDb);
            eventPublisher.publishEvent(event);
            return userMapper.userToUserDTO(userDb);
        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            User userDb = userOptional.get();
            userRepository.deleteById(id);
            DatabaseDeleteEvent event = new DatabaseDeleteEvent(this,userDb);
            eventPublisher.publishEvent(event);
            return true;
        }
        return false;
    }


}
