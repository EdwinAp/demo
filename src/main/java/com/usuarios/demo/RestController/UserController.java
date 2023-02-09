package com.usuarios.demo.RestController;

import com.usuarios.demo.dto.ResponseError;
import com.usuarios.demo.dto.UserDto;
import com.usuarios.demo.service.interfaces.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private String message;

    @Autowired
    private IUserService iUserService;

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        String message = UserDto.isInvalidAnyFieldCreate(userDto);
        if (message.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(iUserService.saveUser(userDto));
        }
        return exampleResponse(true);
    }

    @GetMapping(
            value = "all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getAllUser() {
        return ResponseEntity.ok(this.iUserService.findAllUser());
    }

    @GetMapping(
            value = "{iduser}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity getUser(@PathVariable("iduser") String idUser) {
        return ResponseEntity.ok(this.iUserService.findUser(idUser));
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestBody UserDto userDto){
        message = UserDto.isInvalidAnyFieldUpdate(userDto);
        if (userDto.getIduser() != null && !userDto.getIduser().isEmpty()){
            this.iUserService.deleteUser(userDto);
            return ResponseEntity.ok().build();
        }
        return exampleResponse(false);
    }

    @DeleteMapping( value = "{iduser}")
    public ResponseEntity deleteUserById(@PathVariable("iduser") String idUser){
        if (idUser != null && !idUser.isEmpty()){
            this.iUserService.deleteUser(idUser);
            return ResponseEntity.ok().build();
        }
        message = "Tienes que agregar en url path el is usuario ejemplo: users/ID-USUARIO";
        return exampleResponse(false);
    }

    @PutMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        message = UserDto.isInvalidAnyFieldUpdate(userDto);
        if (message.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(iUserService.updateUser(userDto));
        }
        return exampleResponse(false);
    }

    @PatchMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity pathUpdateUser(@RequestBody UserDto userDto){
        message = UserDto.isInvalidAnyFieldUpdate(userDto);
        if (message.isEmpty()){
            return ResponseEntity.status(HttpStatus.CREATED).body(iUserService.putUser(userDto));
        }
        return exampleResponse(false);
    }

    private ResponseEntity exampleResponse(boolean isCreate){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseError.builder()
                        .codeError(HttpStatus.BAD_REQUEST.value())
                        .messageError(this.message)
                        .requestBodyExample(UserDto.builder()
                                .iduser(isCreate ? "Omitir este campo" : "Introducir este campo obligatoriamente")
                                .firtsName("Escribe un nombre")
                                .lastName("Escribe un apellido")
                                .birthDate(new Date())
                                .age(18)
                                .email("correo@dominio.org")
                                .build())
                        .build());
    }
}
