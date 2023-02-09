package com.usuarios.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
public class UserDto implements Serializable {

    @JsonProperty(value = "")
    private String iduser;
    private String firtsName;
    private String lastName;
    private Integer age;
    private Date birthDate;
    private String email;

    public static String isInvalidAnyFieldCreate(UserDto userDto){
        return (userDto.iduser == null ? "" : (userDto.iduser.isEmpty() ? "" : "Campo iduser debe ser null o estar vacio - "))
                + (userDto.firtsName == null ? "Campo firtsName es obligatorio - " : (userDto.firtsName.isEmpty() ? "Campo firtsName no debe estar vacio - " : ""))
                + (userDto.lastName == null ? "Campo lastName es obligatorio - " : (userDto.lastName.isEmpty() ? "Campo lastName no debe estar vacio - " : ""))
                + (userDto.age == null ? "Campo age es obligatorio - " : (userDto.age <= 0 ? "Campo age debe ser mayor a 0 - " : ""))
                + (userDto.birthDate == null ? "Campo birthDate es obligatorio - " : "")
                + (userDto.email == null ? "Campo email es obligatorio" : (userDto.email.isEmpty() ? "Campo email no debe estar vacio" : ""));
    }

    public static String isInvalidAnyFieldUpdate(UserDto userDto){
        return (userDto.iduser == null ? "Campo iduser es obligatorio - " : (userDto.iduser.isEmpty() ? "Campo iduser no debe estar vacio - " : ""))
                + (userDto.firtsName == null ? "Campo firtsName es obligatorio - " : (userDto.firtsName.isEmpty() ? "Campo firtsName no debe estar vacio - " : ""))
                + (userDto.lastName == null ? "Campo lastName es obligatorio - " : (userDto.lastName.isEmpty() ? "Campo lastName no debe estar vacio - " : ""))
                + (userDto.age == null ? "Campo age es obligatorio - " : (userDto.age <= 0 ? "Campo age debe ser mayor a 0 - " : ""))
                + (userDto.birthDate == null ? "Campo birthDate es obligatorio - " : "")
                + (userDto.email == null ? "Campo email es obligatorio" : (userDto.email.isEmpty() ? "Campo email no debe estar vacio" : ""));
    }

}
