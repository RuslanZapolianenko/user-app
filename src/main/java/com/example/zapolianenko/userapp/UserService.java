package com.example.zapolianenko.userapp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {
    @Value("${user.min.age}")
    private int minAge ;
    private final UserRepository userRepository;
    public User createUser(RegistrationRequest registrationRequest) {

        if (!isUserAdult(registrationRequest.getBirthDate())) {
            throw new IllegalArgumentException("User must be over 18");
        }

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        User newUser = new User();
        newUser.setName(registrationRequest.getName());
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setBirthDate(registrationRequest.getBirthDate());

        return userRepository.save(newUser);

    }
    public User update(UserUpdateRequest updateUser,Long id){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с таким идентификатором не найден"));

        existingUser.setName(updateUser.getName());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setLastname(updateUser.getLastname());
        existingUser.setAddress(updateUser.getAddress());
        existingUser.setPhone(updateUser.getPhone());
        existingUser.setBirthDate(updateUser.getBirthDate());

        return userRepository.save(existingUser);
    }
    public void  delete(Long id){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с таким идентификатором не найден"));
        userRepository.deleteById(id);


    }
    public List<User>rangeUserByAge(LocalDate from,LocalDate to){
        return userRepository.findAllByBirthDateBetween(from, to);

    }
    public boolean isUserAdult(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears() >= minAge;
    }
}

