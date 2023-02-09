package com.usuarios.demo.service;

import com.usuarios.demo.dto.UserDto;
import com.usuarios.demo.models.User;
import com.usuarios.demo.repository.UserRepository;
import com.usuarios.demo.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    private User userTmp;

    @Override
    public List<UserDto> findAllUser() {
        return userRepository.findAll()
                .stream()
                .map(this::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findUser(String iduser) {
        return this.isNull(userRepository.findById(iduser).orElse(null));
    }

    @Override
    public UserDto saveUser(UserDto user) {
        return this.isNull(this.userRepository.save(this.userDtoToUser(user)));
    }

    @Override
    public UserDto updateUser(UserDto user) {
        return this.isNull(this.userRepository.save(this.userDtoToUser(user)));
    }

    @Override
    public UserDto putUser(UserDto user) {
        return this.isNull(this.userRepository.save(this.userDtoToUser(user)));
    }

    @Override
    public boolean deleteUser(UserDto user) {
        this.userRepository.delete(this.userDtoToUser(user));
        return true;
    }

    @Override
    public boolean deleteUser(String iduser) {
        this.userRepository.deleteById(iduser);
        return true;
    }

}
