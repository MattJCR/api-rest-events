package com.example.apirestevents.controller;

import com.example.apirestevents.model.dtos.ExternalUserDTO;
import com.example.apirestevents.model.dtos.UserDTO;
import com.example.apirestevents.model.entities.User;
import com.example.apirestevents.service.ExternalUserService;
import com.example.apirestevents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ExternalUserService externalUserService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO user) {
        UserDTO newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/rest")
    public ResponseEntity<List<ExternalUserDTO>> getExternalUserRestTemplate() {
        List<ExternalUserDTO> externalUsers = externalUserService.fetchUsersWithRestTemplate();
        return new ResponseEntity<>(externalUsers, HttpStatus.CREATED);
    }

    @GetMapping("/web")
    public ResponseEntity<List<ExternalUserDTO>> getExternalUser() {
        List<ExternalUserDTO> externalUsers = externalUserService.fetchUsersWithWebClient();
        return new ResponseEntity<>(externalUsers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id,@Validated @RequestBody UserDTO user) {
        UserDTO updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
