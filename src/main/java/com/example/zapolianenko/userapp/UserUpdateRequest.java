package com.example.zapolianenko.userapp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.time.LocalDate;
@Getter

public class UserUpdateRequest {
    @Email(message = "invalid email")
    private  String email;
    private  String name;
    private  String lastname;
    @Past
    private LocalDate birthDate;
    private String address;
    @Pattern(regexp = "^\\+380\\d+$", message = "Phone should contain only digits and should be in the format +380..")
    private String phone;
}
