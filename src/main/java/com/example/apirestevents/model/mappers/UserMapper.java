package com.example.apirestevents.model.mappers;

import com.example.apirestevents.model.dtos.UserDTO;
import com.example.apirestevents.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);

    Set<UserDTO> usersToUserDTOs(Set<User> users);

    @Mapping(target = "password", ignore = true) // Omitir la contraseña para seguridad
    User userDTOToUser(UserDTO userDTO);

    List<User> userDTOsToUsers(List<UserDTO> userDTOs);

    Set<User> userDTOsToUsers(Set<UserDTO> userDTOs);
}
