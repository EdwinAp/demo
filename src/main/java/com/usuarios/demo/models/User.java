package com.usuarios.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("users")
public class User {

    @Id
    private String iduser;
    private String firtsName;
    private String lastName;
    private int age;
    private Date birthDate;
    private String email;

    public User(){}

    public User(String iduser, String firtsName, String lastName, int age, Date birthDate, String email) {
        this.iduser = iduser;
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.email = email;
    }

    public User(String firtsName, String lastName, int age, Date birthDate, String email) {
        this.firtsName = firtsName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser='" + iduser + '\'' +
                ", firtsName='" + firtsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                '}';
    }
}
