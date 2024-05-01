package com.example.zapolianenko.userapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<User> createUser(@RequestBody RegistrationRequest registrationRequest) {
        User createdUser = userService.createUser(registrationRequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateRequest updateUser, @PathVariable Long id) {
        User updatedUser = userService.update(updateUser, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/range")
    public ResponseEntity<List<User>> getUsersByAgeRange(@RequestParam("from") LocalDate from,
                                                         @RequestParam("to") LocalDate to) {
        List<User> users = userService.rangeUserByAge(from, to);
        return ResponseEntity.ok(users);
    }
}
