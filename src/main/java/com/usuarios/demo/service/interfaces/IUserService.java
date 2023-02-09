package com.usuarios.demo.service.interfaces;

import com.usuarios.demo.dto.UserDto;
import com.usuarios.demo.models.User;

import java.util.List;

public interface IUserService {

    List<UserDto> findAllUser();

    UserDto findUser(String iduser);

    UserDto saveUser(UserDto user);

    UserDto updateUser(UserDto user);

    UserDto putUser(UserDto user);

    boolean deleteUser(UserDto user);

    boolean deleteUser(String iduser);

    default UserDto userToUserDto(User user){
        return UserDto.builder()
                .iduser(user.getIduser())
                .firtsName(user.getFirtsName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .birthDate(user.getBirthDate())
                .email(user.getEmail())
                .build();
    }

    default User userDtoToUser(UserDto userDto){
        return new User(
                userDto.getIduser(),
                userDto.getFirtsName(),
                userDto.getLastName(),
                userDto.getAge(),
                userDto.getBirthDate(),
                userDto.getEmail()
        );
    }

    default UserDto isNull(User user){
        if (user != null){
            return this.userToUserDto(user);
        }
        return null;
    }
}
