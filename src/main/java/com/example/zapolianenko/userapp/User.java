package com.example.zapolianenko.userapp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private long id;
    @Email(message = "invalid email")
    private  String email;
    @NotBlank
    private  String name;
    @NotBlank
    private  String lastname;
    @Past
    private LocalDate birthDate;
    private String address;
    @Pattern(regexp = "^\\+380\\d+$", message = "Phone should contain only digits and should be in the format +380..")
    private String phone;

}

